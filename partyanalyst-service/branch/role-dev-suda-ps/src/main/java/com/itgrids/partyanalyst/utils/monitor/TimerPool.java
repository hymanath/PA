package com.itgrids.partyanalyst.utils.monitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author Suda
 *
 */
public class TimerPool {
	static Logger log = Logger.getLogger(TimerPool.class);

	static Hashtable<String, AbstractTimer> members = new Hashtable<String, AbstractTimer>();
	static ArrayList<TimerStat> lastMeasure = new ArrayList<TimerStat>();

	public static AbstractTimer getTimer(String name) {
		if (members.containsKey(name))
			return members.get(name);
		AbstractTimer timer = new Timer(name);
		join(timer);
		return timer;
	}

	public static void join(AbstractTimer timer) {
		if (timer != null) {
			members.put(timer.getName(), timer);
			log.debug("Adding Timer " + timer + " to the pool");
		}
	}

	public static ArrayList<TimerStat> getLast() {
		return lastMeasure;
	}

	public static void report() {
		if (log.isDebugEnabled())
			log.debug(members.size() + " Timers Report!");
		lastMeasure = new ArrayList<TimerStat>();
		Collection<AbstractTimer> timers = members.values();
		for (AbstractTimer timer : timers) {
			List<String> reports = timer.getStash();
			lastMeasure.addAll(timer.getTimerStatSummary());
			for (String str : reports) {
				log.info(str);
				System.out.println(str); //TODO: Remove this 
			}
			timer.clear();
		}
	}

}
