package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EventUser;

public interface IEventUserDAO extends GenericDao<EventUser, Long>{
	//public List<Object[]> getEventsByUser(Long userId,Date startDate);
	public List<Object[]> getParentEventByUser(Long userId,Date startDate);

	public List<Object[]> getEventsByUserAndParentIds(Long userId,Date startDate,List<Long> parentEventIds);
	public List<Object[]> getEventsDataByUserAndParentIds(Long userId,Date currentDate,List<Long> parentEventIds);


}
