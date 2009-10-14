package com.itgrids.partyanalyst.utils.monitor;

import java.io.Serializable;

/**
 * Will be used to track performance throughout the day - will record method name, status, average times,
 * and implicitly track performance.  Depending on how often TimerPool decides to log the messages
 * the frequency will be recorded.
 * 
 * @author Suda
 * 
 */
public class TimerStat implements Serializable {

	private static final long serialVersionUID = 1L;
	private String methodName, name;
	private int status;
	private int numCalls;
	private long time;
	
	public TimerStat(String method, int status, long time) {
		this.methodName = method;
		this.status = status;
		this.time = time;
	}
	public TimerStat(String name, String method, int status, long time) {
		this.name = name;
		this.methodName = method;
		this.status = status;
		this.time = time;
	}
	//Constructor used for 'summary'
	public TimerStat(String name, String method, int status, long time, int numCalls) {
		this.name = name;
		this.methodName = method;
		this.status = status;
		this.time = time;
		this.numCalls = numCalls;
	}
	public String hash() {
		return methodName+status;
	}
	public long getTime() {return time;}
	public String getMethodName() {
		return methodName;
	}
	public int getStatus() {
		return status;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public int getNumCalls() {
		return numCalls;
	}
}
