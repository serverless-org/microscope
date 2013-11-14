package com.vipshop.microscope.mysql.report;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TraceReport {
	
	private int year;
	private int month;
	private int week;
	private int day;
	private int hour;
	
	private String app;
	private String ipAdress;
	private String type;
	private String name;
	private long totalCount;
	private long failureCount;
	private float failurePrecent;
	private float min;
	private float max;
	private float avg;
	private float tps;
	
	private long sum;
	private long startTime;
	private long endTime;
	
	public static float makeTPS(TraceReport report) {
		BigDecimal count = new BigDecimal(report.getTotalCount());
		BigDecimal time = new BigDecimal((report.getEndTime() - report.getStartTime()) / 1000);
		return count.divide(time, 3, RoundingMode.HALF_DOWN).floatValue();
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public String getType() {
		return type;
	}

	public void setType(String name) {
		this.type = name;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getFailureCount() {
		return failureCount;
	}

	public void setFailureCount(long failureCount) {
		this.failureCount = failureCount;
	}

	public float getFailurePrecent() {
		return failurePrecent;
	}

	public void setFailurePrecent(float failurePrecent) {
		this.failurePrecent = failurePrecent;
	}

	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public float getAvg() {
		return avg;
	}

	public void setAvg(float avg) {
		this.avg = avg;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public float getTps() {
		return tps;
	}

	public void setTps(float tps) {
		this.tps = tps;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getSum() {
		return sum;
	}

	public void setSum(long sum) {
		this.sum = sum;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	@Override
	public String toString() {
		return "TraceReport [year=" + year + ", month=" + month + ", week=" + week + ", day=" + day + ", hour=" + hour + ", app=" + app + ", type=" + type + ", name=" + name
				+ ", totalCount=" + totalCount + ", failureCount=" + failureCount + ", failurePrecent=" + failurePrecent + ", min=" + min + ", max=" + max + ", avg=" + avg + ", tps=" + tps + ", sum="
				+ sum + ", startTime=" + startTime + ", endTime=" + "]";
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}
	
}
