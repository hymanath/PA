package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEventAttendeeErrorDAO;
import com.itgrids.partyanalyst.model.EventAttendeeError;
import com.itgrids.partyanalyst.utils.IConstants;

public class EventAttendeeErrorDAO extends GenericDaoHibernate<EventAttendeeError, Long> implements IEventAttendeeErrorDAO{

	public EventAttendeeErrorDAO() {
		super(EventAttendeeError.class);
		// TODO Auto-generated constructor stub
	}

	public List checkEventsyncDataInError(String rfid,String imei,Long eventId,String uniqueKey)
	{
		Query queryStr=getSession().createQuery("select model.eventAttendeeErrorId from EventAttendeeError model" +
				" where model.event.eventId=:eventId and model.imei =:imei and model.rfid =:rfid" +
				" and model.uniqueKey =:uniqueKey");
		queryStr.setParameter("rfid", rfid);
		queryStr.setParameter("imei", imei);
		queryStr.setParameter("eventId", eventId);
		queryStr.setParameter("uniqueKey", uniqueKey);
		return queryStr.list();
	}
	
	
	public Long getPreEntryInvalidCount(Long preEnytryEventId,Date startDate,Date endDate)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("  select count(model.eventAttendeeErrorId) " +
				   "  from   EventAttendeeError model " +
				   "  where  model.event.eventId =:preEnytryEventId " +
				   "         and  model.event.isActive =:isActive " +
				   "         and  model.event.isVisible =:isVisible");
		
		if((startDate != null && endDate != null)){
			str.append(" and date(model.attendedTime) >= :startDate and date(model.attendedTime) <= :endDate "); 
		}
		
		Query query = getSession().createQuery(str.toString());
		
		if((startDate != null && endDate != null)){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);	
		}
		
		query.setParameter("preEnytryEventId", preEnytryEventId);
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameter("isVisible", IConstants.IS_VISIBLE);
		return (Long)query.uniqueResult();
	}
	
	
}
