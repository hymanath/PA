package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEventUserDAO;
import com.itgrids.partyanalyst.model.EventUser;

public class EventUserDAO extends GenericDaoHibernate<EventUser, Long> implements IEventUserDAO{

	public EventUserDAO() {
		super(EventUser.class);
	}
	public List<Object[]> getParentEventByUser(Long userId,Date currentDate)
	{
		Query query = getSession().createQuery("select model.event.eventId,model.event.name from EventUser model where date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime) and model.userId =:userId  and model.event.parentEventId is null");
		query.setDate("currentDate", currentDate);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	/*public List<Object[]> getEventsByUser(Long userId,Date startDate)
	{
		Query query = getSession().createQuery("select model.event.eventId,model.event.name from EventUser model where date(model.event.eventStartTime) >=:startDate " +
				"and model.userId =:userId and  model.event.parentEventId = 1");
		query.setDate("startDate", startDate);
		query.setParameter("userId", userId);
		return query.list();
	}*/
	
	
	
	public List<Object[]> getEventsByUserAndParentIds(Long userId,Date currentDate,List<Long> parentEventIds)
	{
		Query query = getSession().createQuery("select model.event.eventId,model.event.name,model.event.parentEventId from EventUser model where  " +
				" date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime) and model.userId =:userId and  model.event.parentEventId in(:parentEventIds)");
		query.setDate("currentDate", currentDate);
		query.setParameter("userId", userId);
		query.setParameterList("parentEventIds", parentEventIds);
		return query.list();
	}


}
