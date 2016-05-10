package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Event;

public interface IEventDAO extends GenericDao<Event, Long> {
	public List<Object[]> getEventsForUser(Long userId);
	public Event checkIsExistEvent(String eventName);
	public List<Object[]> getEventNames(List<Long> eventIds);
	public List<Object[]> getSubEventsByParentEvent(Long eventId);
	public List<Object[]> getParentEvents(Date currentDate);
	public List<Object[]> getEventsByUserAndParentIds(Date currentDate,List<Long> parentEventIds);
	public List getParentEventId(Long eventId);
	public String getEventName(Long eventId);
	public List<Object[]> getVisibleEventNames(List<Long> eventIds);
}
