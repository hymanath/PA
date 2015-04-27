package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.model.EventAttendee;
import com.itgrids.partyanalyst.utils.IConstants;

public class EventAttendeeDAO extends GenericDaoHibernate<EventAttendee, Long> implements IEventAttendeeDAO{

	public EventAttendeeDAO() {
		super(EventAttendee.class);
	}
	
	public List<Object[]> getEventAttendeeInfo(String locationType,String inviteeType,Date currentDate)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select model.event.eventId,count(distinct model.tdpCadre.tdpCadreId), ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" model.tdpCadre.userAddress.constituency.district.districtId");
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" model.tdpCadre.userAddress.constituency.constituencyId");
		
		if(inviteeType.equalsIgnoreCase("invitee"))
			str.append(" ,date(model.attendedTime) from EventAttendee model,EventInvitee model1 where model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId ");	
		else
			str.append(" ,date(model.attendedTime) from EventAttendee model where model.tdpCadre.tdpCadreId not in(select model1.tdpCadre.tdpCadreId from EventInvitee model1)");
		str.append(" and date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime)");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.district.districtId,date(model.attendedTime) order by model.tdpCadre.userAddress.constituency.district.districtId");
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.constituencyId,date(model.attendedTime) order by model.tdpCadre.userAddress.constituency.constituencyId");
		Query query = getSession().createQuery(str.toString());
		query.setDate("currentDate", currentDate);
		return query.list();
	}

	
	public List checkUserExist(Long tdpCadreId,Long eventId,Date date)
	{
		
		Query query = getSession().createQuery("select model.eventAttendeeId from EventAttendee model where model.tdpCadreId = :tdpCadreId" +
				" and model.eventId = :eventId and date(model.insertedTime) = :date");
		query.setParameter("tdpCadreId", tdpCadreId);
		query.setParameter("eventId", eventId);
		query.setParameter("date", date);
		return query.list();
		
	}
	
	
	public List<Object[]> getMultipleVistitedInfo(String locationType,String inviteeType,Date currentDate)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select model.event.eventId,count(distinct model.tdpCadre.tdpCadreId), ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" model.tdpCadre.userAddress.constituency.district.districtId");
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" model.tdpCadre.userAddress.constituency.constituencyId");
		
		if(inviteeType.equalsIgnoreCase("invitee"))
			str.append(" ,date(model.attendedTime) from EventAttendee model,EventInvitee model1 where model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId ");	
		else
			str.append(" ,date(model.attendedTime) from EventAttendee model where model.tdpCadre.tdpCadreId not in(select model1.tdpCadre.tdpCadreId from EventInvitee model1)");
		str.append(" and date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime)");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.district.districtId,date(model.attendedTime) order by model.tdpCadre.userAddress.constituency.district.districtId");
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.constituencyId,date(model.attendedTime) order by model.tdpCadre.userAddress.constituency.constituencyId");
		Query query = getSession().createQuery(str.toString());
		query.setDate("currentDate", currentDate);
		return query.list();
	}
	
	public List<Object[]> getStateWiseEventAttendeeInfo(String inviteeType,Date currentDate,Long stateId)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select model.event.eventId,count(distinct model.tdpCadre.tdpCadreId) ");
	
		if(inviteeType.equalsIgnoreCase("invitee"))
			str.append(" ,date(model.attendedTime) from EventAttendee model,EventInvitee model1 where model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId ");	
		else
			str.append(" ,date(model.attendedTime) from EventAttendee model where model.tdpCadre.tdpCadreId not in(select model1.tdpCadre.tdpCadreId from EventInvitee model1)");
		str.append(" and date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime)");
		if(stateId == 1)
			str.append(" and model.tdpCadre.userAddress.constituency.district.districtId between 11 and 23");
		else
			str.append(" and model.tdpCadre.userAddress.constituency.district.districtId between 1 and 10");
		str.append(" group by model.event.eventId,date(model.attendedTime) ");
		Query query = getSession().createQuery(str.toString());
		query.setDate("currentDate", currentDate);
		return query.list();
	}
}
