package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EventType;

public interface IEventTypeDAO extends GenericDao<EventType, Long>{
	public List<Object[]> getEventTypeDetails();
}
