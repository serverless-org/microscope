package com.vipshop.microscope.test.mock;

import java.util.concurrent.TimeUnit;

import com.vipshop.micorscope.framework.span.Category;
import com.vipshop.microscope.collector.server.CollectorServer;
import com.vipshop.microscope.trace.Tracer;

public class MockData {
	
	public static final int size = 1;
	
	public static void main(String[] args) throws InterruptedException {
		
		new Thread(new CollectorServer()).start();
		
		TimeUnit.SECONDS.sleep(3);
		
		for (int i = 0; i < size; i++)  {
			Tracer.cleanContext();
			Tracer.clientSend("http://www.vipshop.com", Category.URL);
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
				Tracer.clientSend("getNew@newService", Category.SERVICE);
				TimeUnit.MILLISECONDS.sleep(400);
				Tracer.clientSend("get@DB", Category.DAO);
				TimeUnit.MILLISECONDS.sleep(100);
				Tracer.clientReceive();
				Tracer.clientReceive();
				
				Tracer.clientSend("buyNew@buyService", Category.SERVICE);
				TimeUnit.MILLISECONDS.sleep(200);
				Tracer.clientSend("buy@Cache", Category.CACHE);
				TimeUnit.MILLISECONDS.sleep(10);
				Tracer.clientReceive();
				Tracer.clientReceive();
			} catch (Exception e) {
				Tracer.setResultCode("EXCEPTION");
			} finally {
				Tracer.clientReceive();
			}
		}
		
	}
}