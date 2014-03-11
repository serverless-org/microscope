package com.vipshop.microscope.common.logentry;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;

import com.vipshop.microscope.common.trace.Span;

public class Codec {

	private static final TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();
	private static final Base64 base64 = new Base64();

	public static LogEntry encodeToLogEntry(Span span) {
		final ByteArrayOutputStream buf = new ByteArrayOutputStream();
		final TProtocol proto = protocolFactory.getProtocol(new TIOStreamTransport(buf));
		try {
			span.write(proto);
		} catch (TException e) {
			return null;
		}
		String spanAsString = base64.encodeToString(buf.toByteArray());
		LogEntry logEntry = new LogEntry(LogEntryCategory.TRACE, spanAsString);
		return logEntry;
	}

	public static Span decodeToSpan(final String msg) {
		byte[] tmp = Base64.decodeBase64(msg);
		final ByteArrayInputStream buf = new ByteArrayInputStream(tmp);
		final TProtocol proto = protocolFactory.getProtocol(new TIOStreamTransport(buf));
		Span span = new Span();
		try {
			span.read(proto);
		} catch (TException e) {
			return null;
		}
		span.setResultSize(msg.length());
		return span;
	}

	public static LogEntry encodeToLogEntry(HashMap<String, Object> map) {
		byte[] bytes = SerializationUtils.serialize((Serializable) map);
		String message = Base64.encodeBase64String(bytes);
		LogEntry logEntry = new LogEntry(LogEntryCategory.EXCEP, message);
		return logEntry;
	}
	
	public static String encodeToString(HashMap<String, Object> map) {
		byte[] bytes = SerializationUtils.serialize((Serializable) map);
		return Base64.encodeBase64String(bytes);
	}

	public static HashMap<String, Object> decodeToMap(final String msg) {
		byte[] bytes = Base64.decodeBase64(msg);
		@SuppressWarnings("unchecked")
		HashMap<String, Object> map = (HashMap<String, Object>) SerializationUtils.deserialize(bytes);
		return map;
	}

	public static LogEntry encodeToLogEntry(String msg) {
		byte[] bytes = SerializationUtils.serialize(msg);
		String message = Base64.encodeBase64String(bytes);
		LogEntry logEntry = new LogEntry(LogEntryCategory.METRICS, message);
		return logEntry;
	}
	
	public static String decodeToString(String msg) {
		byte[] bytes = Base64.decodeBase64(msg);
		String result = (String) SerializationUtils.deserialize(bytes);
		return result;
	}

}