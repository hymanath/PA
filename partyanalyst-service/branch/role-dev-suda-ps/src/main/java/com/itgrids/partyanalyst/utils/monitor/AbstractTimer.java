package com.itgrids.partyanalyst.utils.monitor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 * @author Suda
 *
 */
public abstract class AbstractTimer implements Serializable {

	private static final long serialVersionUID = 1L;
	String name;
	protected Hashtable<String, ArrayList<TimerStat>> stats = new Hashtable<String, ArrayList<TimerStat>>();

	public String getName() {
		return name;
	}

	public AbstractTimer(String name) {
		this.name = name;
		TimerPool.join(this);
	}

	public void addTimerStat(String method, int status, long time) {
		this.addTimerStat(new TimerStat(method, status, time));
	}

	public ArrayList<TimerStat> getTimerStatSummary() {
		ArrayList<TimerStat> ret = new ArrayList<TimerStat>();
		Enumeration<ArrayList<TimerStat>> lists = stats.elements();
		while (lists.hasMoreElements()) {
			// each list is a list of single name+method.
			ArrayList<TimerStat> list = lists.nextElement();
			long time = 0;
			for (TimerStat timerStat : list) {
				time += timerStat.getTime();
			}
			if (list.size() > 0)
				time /= list.size();
			TimerStat timerStat = list.get(0);
			ret.add(new TimerStat(name, timerStat.getMethodName(), 0, time,
					list.size()));
		}
		return ret;
	}

	/**
	 * Will maintain a list of execution status and time
	 * 
	 * @param method
	 * @param Status
	 * @param time
	 */
	public void addTimerStat(TimerStat timerStat) {
		if (timerStat == null)
			return;
		timerStat.setName(name);
		String key = timerStat.hash();
		ArrayList<TimerStat> timerStatList = null;
		if (stats.containsKey(key)) {
			timerStatList = stats.get(key);
		} else
			timerStatList = new ArrayList<TimerStat>();
		timerStatList.add(timerStat);
		stats.put(timerStat.hash(), timerStatList);
	}

	public void clear() {
		stats = new Hashtable<String, ArrayList<TimerStat>>();
	}

	/**
	 * Method should return a list of messages to log based on all the
	 * timerStats contained. Abstract so that the implementation class can
	 * control the format of the messages. These can later be queried from the
	 * DB.
	 * 
	 * @return
	 */
	public abstract List<String> getStash();
}
