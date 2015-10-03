package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEventUserDAO;
import com.itgrids.partyanalyst.model.EventUser;
import com.itgrids.partyanalyst.utils.IConstants;

public class EventUserDAO extends GenericDaoHibernate<EventUser, Long> implements IEventUserDAO{

	public EventUserDAO() {
		super(EventUser.class);
	}
	public List<Object[]> getParentEventByUser(Long userId,Date currentDate)
	{
		Query query = getSession().createQuery("select model.event.eventId,model.event.name,model.event.eventStartTime,model.event.eventEndTime," +
				" model.event.startTime, model.event.endTime,model.event.syncType,model.event.isInviteeExist  from EventUser model where date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime) and model.userId =:userId and  model.event.parentEventId is null " +
				" and model.event.isActive =:isActive order by model.event.orderId asc ");
		query.setDate("currentDate", currentDate);
		query.setParameter("userId", userId);
		query.setParameter("isActive", IConstants.TRUE);
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
		Query query = getSession().createQuery("select model.event.eventId,model.event.name,model.event.parentEventId,model.event.description,model.event.startTime,model.event.endTime,model.event.isInviteeExist," +
				" model.event.entryLimit, model.event.serverWorkMode, model.event.tabWorkMode,date(model.event.eventStartTime),date(model.event.eventEndTime),model.event.orderId,model.event.isActive,model.event.syncType  from EventUser model where  " +
				" date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime) and model.userId =:userId and  model.event.parentEventId in(:parentEventIds)  and model.event.isActive =:isActive order by model.event.orderId asc ");
		query.setDate("currentDate", currentDate);
		query.setParameter("userId", userId);
		query.setParameterList("parentEventIds", parentEventIds);
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	public List<Object[]> getEventsDataByUserAndParentIds(Long userId,Date currentDate,List<Long> parentEventIds)
	{
		Query query = getSession().createQuery("select model.event.eventId,model.event.name,model.event.parentEventId," +
				" model.event.syncType  from EventUser model where  " +
				" date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime) and model.userId =:userId and  model.event.parentEventId in(:parentEventIds)  and model.event.isActive =:isActive order by model.event.orderId asc ");
		query.setDate("currentDate", currentDate);
		query.setParameter("userId", userId);
		query.setParameterList("parentEventIds", parentEventIds);
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	
}
