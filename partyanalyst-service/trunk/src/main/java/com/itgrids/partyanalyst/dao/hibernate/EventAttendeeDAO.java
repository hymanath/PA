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
		if(inviteeType.equalsIgnoreCase("attendee"))
			str.append(" ,date(model.attendedTime) from EventAttendee model where ");
		if(inviteeType.equalsIgnoreCase("invitee"))
			str.append(" ,date(model.attendedTime) from EventAttendee model,EventInvitee model1 where model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId and ");	
		
		str.append(" date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime)");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.district.districtId,date(model.attendedTime) order by model.tdpCadre.userAddress.constituency.district.districtId");
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.constituencyId,date(model.attendedTime) order by model.event.eventId,model.tdpCadre.userAddress.constituency.constituencyId,date(model.attendedTime)");
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
		if(inviteeType.equalsIgnoreCase("attendee"))
			str.append(" ,date(model.attendedTime) from EventAttendee model where ");
		if(inviteeType.equalsIgnoreCase("invitee"))
			str.append(" ,date(model.attendedTime) from EventAttendee model,EventInvitee model1 where model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId and ");	
		
		str.append(" date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime)");
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
	
		if(inviteeType.equalsIgnoreCase("attendee"))
			str.append(" ,date(model.attendedTime) from EventAttendee model where ");
		else if(inviteeType.equalsIgnoreCase("invitee"))
			str.append(" ,date(model.attendedTime) from EventAttendee model,EventInvitee model1 where model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId and ");	
		
		str.append(" date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime) and ");
		if(stateId == 1)
			str.append(" model.tdpCadre.userAddress.constituency.district.districtId between 11 and 23");
		else
			str.append(" model.tdpCadre.userAddress.constituency.district.districtId between 1 and 10");
		str.append(" group by model.event.eventId,date(model.attendedTime) ");
		Query query = getSession().createQuery(str.toString());
		query.setDate("currentDate", currentDate);
		return query.list();
	}
	
	public List<Object[]> getTotlaVisitsCount(Long parentEventId,Date startDate,Date endDate,List<Long> subeventIds)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select model.event.eventId,count(distinct model.tdpCadre.tdpCadreId) from EventAttendee model where model.event.parentEventId =:parentEventId and model.event.eventId in(:subeventIds) ");
		
		if((startDate != null && endDate != null))
		{
			if(startDate.equals(endDate))
			str.append(" and date(model.attendedTime) = :startDate "); 
			else
			str.append(" and date(model.attendedTime) >= :startDate and date(model.attendedTime) <= :endDate "); 
		}
		Query query = getSession().createQuery(str.toString());
		if((startDate != null && endDate != null))
		{
			if(startDate.equals(endDate))
			query.setDate("startDate", startDate);
			else
			{
				query.setDate("startDate", startDate);
				query.setDate("endDate", endDate);	
			}
		}
		query.setParameterList("subeventIds", subeventIds);
		query.setParameter("parentEventId", parentEventId);
		return query.list();
	}

public List<Object[]> getHourWiseVisitorsCount(Long parentEventId,Date date,List<Long> subeventIds){
		
		StringBuilder str = new StringBuilder();
		str.append(" select  count(distinct model.tdpCadre.tdpCadreId),model.event.eventId,max(hour(model.attendedTime)),model.event.name from EventAttendee model where " +
				" model.event.parentEventId = :parentEventId and date(model.attendedTime) = :date and model.event.eventId in(:subeventIds) ");
		str.append(" group by model.tdpCadre.tdpCadreId,model.event.eventId ");
		Query query = getSession().createQuery(str.toString());
		query.setDate("date", date);
		query.setParameterList("subeventIds", subeventIds);
		query.setParameter("parentEventId",parentEventId);
		return query.list();
		
	}
	
	public List<Object[]> getEventCountsByParentEventId(Long parentEventId,List<Long> subeventIds,Date startDate,Date endDate)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select model.event.eventId,count(distinct model.tdpCadre.tdpCadreId) ");
		str.append(" from EventAttendee model where ");
		str.append("  model.event.parentEventId = :parentEventId and model.event.eventId in(:subeventIds)");
		if((startDate != null && endDate != null))
		{
			if(startDate.equals(endDate))
			str.append(" and date(model.attendedTime) = :startDate group by model.event.eventId,date(model.attendedTime)"); 
			else
			str.append(" and date(model.attendedTime) >= :startDate and date(model.attendedTime) <= :endDate  group by model.event.eventId,date(model.attendedTime) "); 
		}
		
		Query query = getSession().createQuery(str.toString());
		if((startDate != null && endDate != null))
		{
			if(startDate.equals(endDate))
			query.setDate("startDate", startDate);
			else
			{
				query.setDate("startDate", startDate);
				query.setDate("endDate", endDate);	
			}
		}
		query.setParameterList("subeventIds", subeventIds);
		query.setParameter("parentEventId", parentEventId);
		return query.list();
	}
	
	public List<Object[]> getUnionMembersForEvent(Long eventId,Long compareEventId,Date startDate,Date endDate)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select model1.event.name,count(distinct model.tdpCadre.tdpCadreId),date(model.attendedTime) ");
		str.append(" from EventAttendee model,EventAttendee model1 where model.event.eventId = :eventId and model1.event.eventId = :compareEventId");
		str.append(" and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId ");
		if((startDate != null && endDate != null))
		{
			if(startDate.equals(endDate))
			str.append(" and date(model.attendedTime) = :startDate group by date(model.attendedTime)"); 
			else
			str.append(" and date(model.attendedTime) >= :startDate and date(model.attendedTime) <= :endDate  group by date(model.attendedTime) "); 
		}
		Query query = getSession().createQuery(str.toString());
		if((startDate != null && endDate != null))
		{
			if(startDate.equals(endDate))
			query.setDate("startDate", startDate);
			else
			{
				query.setDate("startDate", startDate);
				query.setDate("endDate", endDate);	
			}	
		}
		query.setParameter("compareEventId", compareEventId);
		query.setParameter("eventId", eventId);
		return query.list();
	}
	public List<Object[]> getDayWiseVisitorsCount(Long parentEventId,List<Long> subeventIds,Date startDate,Date endDate){
		
		StringBuilder str = new StringBuilder();
		str.append(" select  count(distinct model.tdpCadre.tdpCadreId),model.event.eventId,date(model.attendedTime),model.event.name from EventAttendee model where " +
				" model.event.parentEventId = :parentEventId  and model.event.eventId in(:subeventIds) ");
		if((startDate != null && endDate != null))
		{
			if(startDate.equals(endDate))
			str.append(" and date(model.attendedTime) = :startDate "); 
			else
			str.append(" and date(model.attendedTime) >= :startDate and date(model.attendedTime) <= :endDate "); 
		}
		str.append(" group by model.event.eventId,date(model.attendedTime) ");
		Query query = getSession().createQuery(str.toString());
		if((startDate != null && endDate != null))
		{
			if(startDate.equals(endDate))
			query.setDate("startDate", startDate);
			else
			{
				query.setDate("startDate", startDate);
				query.setDate("endDate", endDate);	
			}	
		}
		query.setParameterList("subeventIds", subeventIds);
		query.setParameter("parentEventId",parentEventId);
		return query.list();
		
	}
}
