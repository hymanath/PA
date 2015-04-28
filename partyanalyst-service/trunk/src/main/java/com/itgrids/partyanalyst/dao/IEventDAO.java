package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Event;

public interface IEventDAO extends GenericDao<Event, Long> {
	public List<Object[]> getEventsForUser(Long userId);
	public Event checkIsExistEvent(String eventName);
}
