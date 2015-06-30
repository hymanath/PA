package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEventAttendeeErrorDAO;
import com.itgrids.partyanalyst.model.EventAttendeeError;

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
}
