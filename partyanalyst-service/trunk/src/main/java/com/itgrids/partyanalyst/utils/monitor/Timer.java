package com.itgrids.partyanalyst.utils.monitor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Suda
 *
 */
public class Timer extends AbstractTimer implements Serializable {

	private static final long serialVersionUID = 1L;
	private static String SEP = "|";
	public Timer(String name) {
		super(name);
	}

	@Override
	public List<String> getStash() {
		ArrayList<String> results = new ArrayList<String>(); 
		//Thinking of returning the format
		//name|method|status|averageTime|NumCalls for each method and status
		Enumeration<ArrayList<TimerStat>> lists = stats.elements();
		while (lists.hasMoreElements()) {
			ArrayList<TimerStat> list = lists.nextElement();
			long time = 0;
			for (TimerStat timerStat : list) {
				time += timerStat.getTime();
			}
			if (list.size() > 0)
				time /= list.size();
			TimerStat timerStat = list.get(0);
			results.add(getFormattedString(timerStat.getMethodName(), timerStat.getStatus(), time, list.size()));
		}
		//If nothing to report... report that:
		if (results.size() == 0) {
			results.add(getFormattedString("NONE", 0, 0, 0));
		}
		return results;
	}
	private String getFormattedString(String method, int status, long time, int numCalls) {
		StringBuilder str = new StringBuilder(name);
		str.append(SEP);
		str.append(method);
		str.append(SEP);
		str.append(status);
		str.append(SEP);
		str.append(time);
		str.append(SEP);
		str.append(numCalls);
		return str.toString();
	}
	
}
