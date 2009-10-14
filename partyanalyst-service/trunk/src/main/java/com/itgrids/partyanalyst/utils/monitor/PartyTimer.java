package com.itgrids.partyanalyst.utils.monitor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author Suda
 *
 */
public class PartyTimer {

	private static Logger log = Logger.getLogger(PartyTimer.class);
	private String className, methodName;
	private AbstractTimer timer;
	private long start;
	public static final SimpleDateFormat sdf = new SimpleDateFormat(
			"MMM-dd-yyyy hh:mm:ss:sss");

	/**
	 * Sets up a timer for this class and method. Automatically makes the call
	 * to start().
	 * 
	 * @param className
	 * @param methodName
	 */
	public PartyTimer(String className, String methodName) {
		this.className = className;
		this.methodName = methodName;
		timer = TimerPool.getTimer(className);
		start();
	}

	/**
	 * Logs the entry since construction of this instance, or the last call to
	 * stop(), or the last call to start(). We can query the database and sort
	 * by class, method, time, etc.
	 */
	// TODO: DB integration using log4j appender
	public long stop() {
		long stop = System.currentTimeMillis();
		long ret = stop - start;
		if (log.isInfoEnabled())
			log.info(logString(start, stop, className, methodName));
		timer.addTimerStat(methodName, 0, ret);
		//start();
		return ret;
	}

	/** Changes the methodName and then calls stop(); */
	public long stop(String method) {
		this.methodName = method;
		return stop();
	}

	public void start() {
		start = System.currentTimeMillis();
	}

	public String getMethodName() {
		return methodName;
	}

	public static String logString(long start, long stop, String classname,
			String methodName) {
		StringBuilder sb = new StringBuilder("Class: ");
		sb.append(classname);
		sb.append(", Method: ");
		sb.append(methodName);
		sb.append(", Start: ");
		sb.append(sdf.format(new Date(start)));
		sb.append(", Stop: ");
		sb.append(sdf.format(new Date(stop)));
		sb.append(", Duration: ");
		sb.append(stop - start);
		return sb.toString();
	}

	public static void main(String[] args) {
		for(int i=1;i<=5;i++){
			callTester1(1000*i);
			callTester2(500*i);
		}
		TimerPool.report();
	}

	private static void callTester1(int loopLength) {
		int i = 0;
		PartyTimer partyTimer = new PartyTimer(PartyTimer.class.getName(),"callTester1");
		for (; i < 10000; i++) {			
			System.out.println("");//something to do to spend time
		}
		partyTimer.stop();
	}
	
	private static void callTester2(int loopLength) {
		int i = 0;
		PartyTimer partyTimer = new PartyTimer(PartyTimer.class.getName(),"callTester2");
		for (; i < 10000; i++) {			
			System.out.println("");//something to do to spend time
		}
		partyTimer.stop();
	}

}
