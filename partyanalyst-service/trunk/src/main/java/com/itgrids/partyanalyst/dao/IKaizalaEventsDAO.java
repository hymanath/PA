package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.KaizalaEvents;

public interface IKaizalaEventsDAO extends GenericDao<KaizalaEvents, Long> {
	
	public Long getEventId(String eventType);
}
