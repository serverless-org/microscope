package com.vipshop.microscope.mysql.factory;

import com.vipshop.microscope.mysql.repository.MsgReportRepository;
import com.vipshop.microscope.mysql.repository.TraceReportRepository;

public class MySQLRepositorys {
	
	public static TraceReportRepository TRACE_REPORT = new TraceReportRepository();
	public static MsgReportRepository MSG_REPORT = new MsgReportRepository();
	
}
