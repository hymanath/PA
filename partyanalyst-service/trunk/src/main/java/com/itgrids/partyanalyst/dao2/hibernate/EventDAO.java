package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEventDAO;
import com.itgrids.partyanalyst.model.Event;
import com.itgrids.partyanalyst.utils.IConstants;

public class EventDAO extends GenericDaoHibernate<Event, Long> implements IEventDAO{

	public EventDAO( ) {
		super(Event.class);
	}

	public List<Object[]> getEventsForUser(Long userId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  model.eventId, model.name from Event model where model.parentEventId is null and model.isActive =:isActive order by model.orderId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	
	public Event checkIsExistEvent(String eventName)
	{
		Query query = getSession().createQuery(" select model from Event model where model.namel like '"+eventName.trim()+"' and model.isActive =:isActive  ");
		query.setParameter("isActive", IConstants.TRUE);
		return (Event) query.uniqueResult();
	}
	
	public List<Object[]> getEventNames(List<Long> eventIds)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  model.eventId, model.name from Event model where  model.eventId in(:eventIds)  and model.isActive =:isActive order by model.orderId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("eventIds", eventIds);
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	
	public List<Object[]> getSubEventsByParentEvent(Long eventId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.eventId, model.name from Event model where  model.parentEventId = :eventId  and model.isActive =:isActive order by model.orderId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("eventId", eventId);
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	public List<Object[]> getVisibleSubEventsByParentEvent(Long eventId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.eventId, model.name from Event model " +
				        " where  model.parentEventId = :eventId  and model.isActive =:isActive and model.isVisible =:isVisible " +
				        " order by model.orderId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("eventId", eventId);
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameter("isVisible", IConstants.IS_VISIBLE);
		return query.list();
	}
	public List<Object[]> getParentEvents(Date currentDate)
	{
		Query query = getSession().createQuery("select model.eventId,model.name,model.eventStartTime,model.eventEndTime," +
				" model.startTime, model.endTime  from Event model where date(:currentDate) between date(model.eventStartTime) and date(model.eventEndTime) and  model.parentEventId is null " +
				" and model.isActive =:isActive order by model.orderId asc ");
		query.setDate("currentDate", currentDate);
		
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	public List<Object[]> getEventsByUserAndParentIds(Date currentDate,List<Long> parentEventIds)
	{
		Query query = getSession().createQuery("select model.eventId,model.name,model.parentEventId,model.description,model.startTime,model.endTime,model.isInviteeExist," +
				" model.entryLimit, model.serverWorkMode, model.tabWorkMode,date(model.eventStartTime),date(model.eventEndTime),model.orderId,model.isActive from Event model where  " +
				" date(:currentDate) between date(model.eventStartTime) and date(model.eventEndTime) and  model.parentEventId in(:parentEventIds)  and model.isActive =:isActive order by model.orderId asc ");
		query.setDate("currentDate", currentDate);
		
		query.setParameterList("parentEventIds", parentEventIds);
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	public List getParentEventId(Long eventId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  model.parentEventId from Event model where  model.isActive =:isActive and model.eventId =:eventId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("eventId", eventId);
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	public String getEventName(Long eventId){

		Query query = getSession().createQuery(" select  model.name from Event model  " +
				" where model.eventId =:eventId  ");
		
		query.setParameter("eventId", eventId);
		
		return (String) query.uniqueResult();
	}
	public List<Object[]> getVisibleEventNames(List<Long> eventIds)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  model.eventId, model.name from Event model where  model.eventId in(:eventIds)  and model.isActive =:isActive and model.isVisible=:isVisible  order by model.orderId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("eventIds", eventIds);
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameter("isVisible", IConstants.IS_VISIBLE);
		return query.list();
	}
	
	public Object[] getEventDates(Long eventId){
		
		Query query = getSession().createQuery(" select date(model.eventStartTime),date(model.eventEndDate) from Event model" +
				" where model.eventId =:eventId ");		
		query.setParameter("eventId", eventId);
		
		return (Object[])query.uniqueResult();		
	}
	
	public List<Object[]> getVisibleParentEvents(Date currentDate)
	{
		Query query = getSession().createQuery("select model.eventId,model.name,date(model.eventStartTime),model.eventEndTime," +
				" model.startTime, model.endTime,date(model.eventEndDate)  from Event model where date(:currentDate) between date(model.eventStartTime) and date(model.eventEndTime) and  model.parentEventId is null " +
				" and model.isActive =:isActive and model.isVisible =:isVisible order by model.orderId asc ");
		query.setDate("currentDate", currentDate);
		
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameter("isVisible", IConstants.IS_VISIBLE);
		
		return query.list();
	}
	public List<Object[]> getVisibleEventsByUserAndParentIds(Date currentDate,List<Long> parentEventIds)
	{
		Query query = getSession().createQuery("select model.eventId,model.name,model.parentEventId,model.description,model.startTime,model.endTime,model.isInviteeExist," +
				" model.entryLimit, model.serverWorkMode, model.tabWorkMode,date(model.eventStartTime),date(model.eventEndTime),model.orderId,model.isActive from Event model where  " +
				" date(:currentDate) between date(model.eventStartTime) and date(model.eventEndTime) and  model.parentEventId in(:parentEventIds)  and model.isActive =:isActive " +
				" and  model.isVisible =:isVisible order by model.orderId asc ");
		query.setDate("currentDate", currentDate);
		
		query.setParameterList("parentEventIds", parentEventIds);
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameter("isVisible", IConstants.IS_VISIBLE);
		return query.list();
	}
	public List<Object[]> getParentEventsAndItsChildEvents()
	{
		Query query = getSession().createQuery("select model.eventId,model.parentEventId " +
				"  from Event model where  " +
				"   model.isActive =:isActive " +
				"   and  model.isVisible =:isVisible order by model.orderId asc ");
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameter("isVisible", IConstants.IS_VISIBLE);
		
		return query.list();
	}
	public List<Object[]> getSubEventsByParentNewEvent(Long eventId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.eventId, model.name from Event model where  model.parentEventId = :eventId  and model.isActive =:isActive order by model.orderId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("eventId", eventId);
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	public List<Long> getSubEventsByParentEventId(Long eventId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.eventId from Event model where  model.parentEventId = :eventId  and model.isActive =:isActive and model.isVisible=:isVisible order by model.orderId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("eventId", eventId);
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameter("isVisible", IConstants.IS_VISIBLE);
		return query.list();
	}
	
	public Object[] getEventDatesByEventId(Long eventId){
		
		Query query = getSession().createQuery(" select date(model.eventStartTime),date(model.eventEndTime) from Event model" +
				" where model.eventId =:eventId and model.isActive = 'true' and model.isVisible = 'Y'");		
		query.setParameter("eventId", eventId);
		
		return (Object[])query.uniqueResult();		
	}
	
	public Long getParentEventIdBySubEvent(Long subEventId){
		Query query = getSession().createQuery("select model.parentEventId from Event model where model.eventId = :subEventId" +
									" and model.isActive = 'true' and model.isVisible = 'Y'");
		query.setParameter("subEventId", subEventId);
		
		return (Long) query.uniqueResult();
	}
	
	public Long getSubEventIdByParentId(Long eventId){
		Query query = getSession().createQuery("select model.eventId from Event model where model.parentEventId = :eventId and model." +
									" and model.isActive = 'true' and model.isVisible = 'Y' and model.name like '%exit%'");
		query.setParameter("eventId", eventId);
		
		return (Long) query.uniqueResult();
	}

	
	public Object[] getEventStartAndEndDate(Long eventId){
		
		Query query=getSession().createQuery(" select date(model.eventStartTime),date(model.eventEndDate) from Event model " +
				" where model.isActive =:isActive and model.isVisible =:isVisible and model.eventId=:eventId ");
			query.setParameter("isActive", IConstants.TRUE);
			query.setParameter("isVisible", IConstants.IS_VISIBLE);
			query.setParameter("eventId", eventId);
			return (Object[]) query.uniqueResult();
	}
	public List<Object[]> getEventSubEventByParentEventId(Long parentEventId)
	{
		Query query = getSession().createQuery(" select model.eventId,model.name " +
				" from Event model where model.parentEventId is not null " +
				" and model.isActive =:isActive and model.isVisible =:isVisible and model.parentEventId=:parentEventId order by model.orderId asc ");
		
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameter("isVisible", IConstants.IS_VISIBLE);
		query.setParameter("parentEventId", parentEventId);
		return query.list();
	}
	
	public List<Object[]> getMainEvents(){
		Query query = getSession().createQuery(" select model.eventId,model.name " +
				" from Event model where model.parentEventId is null " +
				" and model.isActive =:isActive and model.isVisible =:isVisible order by model.orderId desc ");
		
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameter("isVisible", IConstants.IS_VISIBLE);
		return query.list();
	}
}
