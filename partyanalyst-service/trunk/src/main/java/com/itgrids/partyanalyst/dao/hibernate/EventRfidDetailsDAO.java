package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEventRfidDetailsDAO;
import com.itgrids.partyanalyst.model.EventRfidDetails;

public class EventRfidDetailsDAO extends GenericDaoHibernate<EventRfidDetails,Long> implements IEventRfidDetailsDAO{

	public EventRfidDetailsDAO()
	{
		super(EventRfidDetails.class);
	}
	
	public List<Object[]> getEventRFIDDetailsByEventIds(List<Long> eventIds)
	
	{
		Query query = getSession().createQuery(" select model.event.eventId, model.rfidOperation, model.regText, model.sectorNo, model.blockNo, model.orderNo from EventRfidDetails model where model.event.eventId in (:eventIds) order by model.orderNo ");
		query.setParameterList("eventIds", eventIds);
		return query.list();
	}
	
	
	public int deleteEventRFIDDetailsByEventIds(List<Long> eventIds)
	
	{
		Query query = getSession().createQuery(" delete model from EventRfidDetails model where model.event.eventId in (:eventIds) ");
		query.setParameterList("eventIds", eventIds);
		int count = query.executeUpdate();
		return count;		
	}
	public List<Object> getPrePopulatingValuesOfEvents(Long eventId){
		
		Query query = getSession().createQuery(" select model from EventRfidDetails model where  model.event.eventId=:eventId ");
	
		query.setParameter("eventId", eventId);
		return query.list();
	}
	
}
