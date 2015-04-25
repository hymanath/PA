package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EventGroup;

public interface IEventGroupDAO extends GenericDao<EventGroup, Long>{
	public List<Object[]> getEventGroups(Long userId);
}
