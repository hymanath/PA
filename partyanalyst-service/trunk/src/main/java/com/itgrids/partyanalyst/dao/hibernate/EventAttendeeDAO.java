package com.itgrids.partyanalyst.dao.hibernate;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

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
			str.append(" ,date(model.attendedTime) from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId and ");	
		
		str.append(" date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime)");
		str.append(" and model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.district.districtId,date(model.attendedTime) order by model.tdpCadre.userAddress.constituency.district.districtId");
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.constituencyId,date(model.attendedTime) order by model.event.eventId,model.tdpCadre.userAddress.constituency.constituencyId,date(model.attendedTime)");
		Query query = getSession().createQuery(str.toString());
		query.setDate("currentDate", currentDate);
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	public List<Object[]> getRequiredEventAttendeeInfo(String locationType,String inviteeType,Date startDate,Date endDate,List<Long> eventIds)
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
			str.append(" ,date(model.attendedTime) from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId  and ");	
		
		
		str.append("  date(model.attendedTime) between :startDate and :endDate ");
		if(eventIds != null && eventIds.size() > 0){
			str.append(" and model.event.eventId in  (:eventIds)");
		}
		
		str.append(" and model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.district.districtId,date(model.attendedTime) order by model.tdpCadre.userAddress.constituency.district.districtId");
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.constituencyId,date(model.attendedTime) order by model.event.eventId,model.tdpCadre.userAddress.constituency.constituencyId,date(model.attendedTime)");
		
		Query query = getSession().createQuery(str.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		if(eventIds != null && eventIds.size() > 0){
			query.setParameterList("eventIds", eventIds);
		}
		
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	
	public List<Object[]> getEventAttendeeInfoByLocation(String queryString,Date startDate,Date endDate,List<Long> eventIds)
	{	
		
		Query query = getSession().createQuery(queryString);
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		if(eventIds != null && eventIds.size() > 0){
			query.setParameterList("eventIds", eventIds);
		}
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	
	
	public List checkUserExist(Long tdpCadreId,Long eventId,Date date)
	{
		
		Query query = getSession().createQuery("select model.eventAttendeeId from EventAttendee model where model.tdpCadreId = :tdpCadreId" +
				" and model.eventId = :eventId and date(model.insertedTime) = :date  and model.event.isActive =:isActive ");
		query.setParameter("tdpCadreId", tdpCadreId);
		query.setParameter("eventId", eventId);
		query.setParameter("date", date);
		query.setParameter("isActive", IConstants.TRUE);
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
			str.append(" ,date(model.attendedTime) from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y'  and model.event.parentEventId = model1.event.parentEventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId and ");	
		
		str.append(" date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime)  and model.event.isActive =:isActive ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.district.districtId,date(model.attendedTime) order by model.tdpCadre.userAddress.constituency.district.districtId");
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.constituencyId,date(model.attendedTime) order by model.tdpCadre.userAddress.constituency.constituencyId");
		Query query = getSession().createQuery(str.toString());
		query.setDate("currentDate", currentDate);
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	
	public List<Object[]> getStateWiseEventAttendeeInfo(String inviteeType,Date currentDate,Long stateId)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select model.event.eventId,count(distinct model.tdpCadre.tdpCadreId) ");
	
		if(inviteeType.equalsIgnoreCase("attendee"))
			str.append(" ,date(model.attendedTime) from EventAttendee model where ");
		else if(inviteeType.equalsIgnoreCase("invitee"))
			str.append(" ,date(model.attendedTime) from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y'  and model.event.parentEventId = model1.event.parentEventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId and ");	
		
		str.append(" date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime) and ");
		if(stateId == 1)
			str.append(" ( model.tdpCadre.userAddress.constituency.district.districtId between 11 and 23 ) and");
		else 
			str.append(" (model.tdpCadre.userAddress.constituency.district.districtId between 1 and 10) and ");
		str.append("   model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' group by model.event.eventId,date(model.attendedTime) ");
		Query query = getSession().createQuery(str.toString());
		query.setDate("currentDate", currentDate);
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	public List<Object[]> getRequiredStateWiseEventAttendeeInfo(String inviteeType,Date startDate,Date endDate,List<Long> eventIds,Long stateId)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select model.event.eventId,count(distinct model.tdpCadre.tdpCadreId) ");
	
		if(inviteeType.equalsIgnoreCase("attendee"))
			str.append(" from EventAttendee model where ");
		else if(inviteeType.equalsIgnoreCase("invitee"))
			str.append("  from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y'  and model.event.parentEventId = model1.event.parentEventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId  and");	
		
		str.append(" date(model.attendedTime) between :startDate and :endDate  ");
		
		if(stateId == 1)
			str.append(" and ( model.tdpCadre.userAddress.constituency.district.districtId between 11 and 23 ) ");
		else 
			str.append(" and (model.tdpCadre.userAddress.constituency.district.districtId between 1 and 10)  ");
		
		if(eventIds != null && eventIds.size() > 0){
			str.append(" and model.event.eventId in  (:eventIds)");
		}
		
		str.append("   and model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 " +
				   "  group by model.event.eventId");
		
		
		Query query = getSession().createQuery(str.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		if(eventIds != null && eventIds.size() > 0){
			query.setParameterList("eventIds", eventIds);
		}
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	public Long getUniqueVisitorsAttendedCount(Long parentEventId,Date startDate,Date endDate,List<Long> subeventIds)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select count(distinct model.tdpCadre.tdpCadreId) " +
				"   from EventAttendee model " +
				"   where model.event.parentEventId =:parentEventId and " +
				"         model.event.eventId in(:subeventIds) and " +
				"         model.event.isActive =:isActive and " +
				"         model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 and " +
				"         model.event.isVisible =:isVisible ");
		
		if(startDate != null && endDate != null){
			str.append(" and date(model.attendedTime) >= :startDate and date(model.attendedTime) <= :endDate "); 
		}
		Query query = getSession().createQuery(str.toString());
		if((startDate != null && endDate != null)){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);	
		}
		query.setParameterList("subeventIds", subeventIds);
		query.setParameter("parentEventId", parentEventId);
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameter("isVisible", IConstants.IS_VISIBLE);
		return (Long)query.uniqueResult();
	}
	public Long getUniqueInviteeVisitorsAttendedcount(Long parentEventId,Date startDate,Date endDate,List<Long> subeventIds)
	{
		
		
		StringBuilder str = new StringBuilder();
		str.append("select count(distinct model.tdpCadre.tdpCadreId) " +
				"   from   EventAttendee model,EventInvitee model1 " +
				"   where  model.event.parentEventId = model1.event.eventId and  model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId " +
				"          and model.event.isInviteeExist = 'Y' and model.event.parentEventId =:parentEventId and model.event.eventId in(:subeventIds) " +
				"          and model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 " +
				"          and model.event.isVisible =:isVisible " );
		
		if(startDate != null && endDate != null){	
			str.append(" and date(model.attendedTime) >= :startDate and date(model.attendedTime) <= :endDate "); 
		}
	
		Query query = getSession().createQuery(str.toString());
		if((startDate != null && endDate != null)){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		query.setParameterList("subeventIds", subeventIds);
		query.setParameter("parentEventId", parentEventId);
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameter("isVisible", IConstants.IS_VISIBLE);
		return (Long)query.uniqueResult();
	}
public List<Object[]> getHourWiseVisitorsCount(Long parentEventId,Date date,List<Long> subeventIds){
		
		StringBuilder str = new StringBuilder();
		str.append(" select  count(distinct model.tdpCadre.tdpCadreId),model.event.eventId,max(hour(model.attendedTime)),model.event.name from EventAttendee model where " +
				" model.event.parentEventId = :parentEventId and date(model.attendedTime) = :date and model.event.eventId in(:subeventIds) ");
		str.append(" and  model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and  model.event.isVisible =:isVisible group by model.tdpCadre.tdpCadreId,model.event.eventId ");
		Query query = getSession().createQuery(str.toString());
		query.setDate("date", date);
		query.setParameterList("subeventIds", subeventIds);
		query.setParameter("parentEventId",parentEventId);
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameter("isVisible", IConstants.IS_VISIBLE);
		return query.list();
		
	}
	
	public List<Object[]> getEventCountsByParentEventId(Long parentEventId,List<Long> subeventIds,Date startDate,Date endDate)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select model.event.eventId,count(distinct model.tdpCadre.tdpCadreId) ");
		str.append(" from EventAttendee model where ");
		str.append("  model.event.parentEventId = :parentEventId and model.event.eventId in(:subeventIds) and  model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and  model.event.isVisible =:isVisible ");
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
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameter("isVisible", IConstants.IS_VISIBLE);
		return query.list();
	}
	
	public List<Object[]> getUnionMembersForEvent(Long eventId,Long compareEventId,Date startDate,Date endDate)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select model1.event.name,count(distinct model.tdpCadre.tdpCadreId),date(model.attendedTime) ");
		str.append(" from EventAttendee model,EventAttendee model1 where model.event.eventId = :eventId and model1.event.eventId = :compareEventId");
		str.append(" and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId and  model.event.isActive =:isActive " +
				   " and model.tdpCadre.isDeleted = 'N' and model1.tdpCadre.isDeleted = 'N' and  model.event.isVisible =:isVisible ");
		if((startDate != null && endDate != null))
		{
			if(startDate.equals(endDate))
			str.append(" and date(model1.attendedTime) = :startDate and date(model.attendedTime) = :startDate group by date(model.attendedTime)"); 
			else
			str.append(" and date(model1.attendedTime) >= :startDate and date(model1.attendedTime) <= :endDate and date(model.attendedTime) >= :startDate and date(model.attendedTime) <= :endDate  group by date(model.attendedTime) "); 
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
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameter("isVisible", IConstants.IS_VISIBLE);
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
		str.append(" and  model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and  model.event.isVisible =:isVisible" +
				   " group by model.event.eventId,date(model.attendedTime) ");
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
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameter("isVisible", IConstants.IS_VISIBLE);
		return query.list();
		
	}
	
	public List<Object[]> getUnionMembersForEventSQL(Long eventId,Long compareEventId,Date startDate,Date endDate)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select e2.name , count(distinct model.tdp_cadre_id), date(model.attended_time) " +
				"   from   event_attendee model, event_attendee model1, event e2, event e3 where model1.event_id=e2.event_id and model.event_id=e3.event_id and model.event_id=:eventId and model1.event_id=:compareEventId and model.tdp_cadre_id=model1.tdp_cadre_id and e3.is_active=:isActive");
	
		if((startDate != null && endDate != null))
		{
			if(startDate.equals(endDate))
			str.append(" and date(model.attended_time) = :startDate group by date(model.attended_time)"); 
			else
			str.append(" and date(model.attended_time) >= :startDate and date(model.attended_time) <= :endDate  group by date(model.attended_time) "); 
		}
		Query query = getSession().createSQLQuery(str.toString());
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
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	public List<Object[]> getStateWiseEventAttendeeCounts(Long parentEventId,Date startDate,Date endDate,List<Long> subeventIds)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select  model.event.eventId,date(model.attendedTime),count(distinct model.tdpCadre.tdpCadreId) " +
				"   from    EventAttendee model " +
				"   where   model.event.parentEventId =:parentEventId and model.event.eventId in(:subeventIds)");
		
		if((startDate != null && endDate != null)){	
			str.append(" and date(model.attendedTime) >= :startDate and date(model.attendedTime) <= :endDate "); 
		}
		str.append("     and  model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.event.isVisible=:isVisible  " +
				  " group by model.event.eventId,date(model.attendedTime) ");
		
		Query query = getSession().createQuery(str.toString());
		if((startDate != null && endDate != null)){	
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		query.setParameterList("subeventIds", subeventIds);
		query.setParameter("parentEventId", parentEventId);
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameter("isVisible", IConstants.IS_VISIBLE);
		return query.list();
	}

	public List<Object[]> getMembersDetailsBySubEvent(Long eventId,Date startDate,Date endDate,Integer startIndex,Integer maxIndex)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.tdpCadre.tdpCadreId,model.tdpCadre.firstname,model.tdpCadre.userAddress.constituency.name,model.tdpCadre.mobileNo,model.tdpCadre.image ");
		str.append(" from EventAttendee model where ");
		str.append(" model.event.eventId = :eventId and  model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' ");
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

		query.setParameter("eventId", eventId);
		if(startIndex!=null)
			query.setFirstResult(startIndex);
		if(maxIndex != null)
			query.setMaxResults(maxIndex);
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
		
	public Long getTodayTotalVisitors(Date todayDate,Long parentEventId,Long entryId){
		Query query=getSession().createQuery("select count(distinct tdpCadreId) from EventAttendee model where date(model.attendedTime) =:todayDate " +
				//" and model.event.parentEventId =:parentEventId " +
				" and model.eventId = :entryId");
		query.setDate("todayDate", todayDate);
		//query.setParameter("parentEventId", parentEventId);
		query.setParameter("entryId", entryId);
		
		 return (Long)query.uniqueResult();
	}
	
	public BigInteger getCurrentVisitors(Date todayDate,Long entryEventId,Long exitEventId){
		Query query=getSession().createSQLQuery("select count(distinct ea1.tdp_cadre_id) from event_attendee ea1 inner join " +
				        " (select tdp_cadre_id as cadre_id, max(attended_time) as max_time from event_attendee " +
						" where date(attended_time) =:todayDate and (event_id =:entryEventId or event_id =:exitEventId) group by tdp_cadre_id) as ea2 " +
						" ON ea1.tdp_cadre_id =  ea2.cadre_id and ea1.attended_time = ea2.max_time where " +
				        " date(ea1.attended_time) =:todayDate and ea1.event_id =:entryEventId order by tdp_cadre_id;");
		query.setDate("todayDate", todayDate);
		query.setParameter("exitEventId", exitEventId);
		query.setParameter("entryEventId", entryEventId);
		
		return (BigInteger)query.uniqueResult();
	}
	
	public BigInteger getCurrentInviteeVisitors(Date todayDate,Long entryEventId,Long exitEventId){
		Query query=getSession().createSQLQuery("select count(distinct ea1.tdp_cadre_id) from event e,event ee,event_invitee ei,event_attendee ea1 inner join " +
				        " (select tdp_cadre_id as cadre_id, max(attended_time) as max_time from event_attendee " +
						" where date(attended_time) =:todayDate and (event_id =:entryEventId or event_id =:exitEventId) group by tdp_cadre_id) as ea2 " +
						" ON ea1.tdp_cadre_id =  ea2.cadre_id and ea1.attended_time = ea2.max_time " +
						" where " +
						" ea1.event_id = e.event_id " +
						" and ei.event_id = ee.event_id " +
						" and e.is_invitee_exist ='Y' " +
						" and e.parent_event_id = ee.event_id " +
				        " and date(ea1.attended_time) =:todayDate and ea1.event_id =:entryEventId and ea1.tdp_cadre_id = ei.tdp_cadre_id order by ei.tdp_cadre_id;");
		query.setDate("todayDate", todayDate);
		query.setParameter("exitEventId", exitEventId);
		query.setParameter("entryEventId", entryEventId);
		
		return (BigInteger)query.uniqueResult();
	}
	
	/*public List<Object[]> getCadreVisitInfo(Date todayDate,Long entryEventId,Long exitEventId){
		//0eventId, 1attendedTime, 2tdpCadreId
		Query query=getSession().createQuery("select model.eventId,model.attendedTime,model.tdpCadreId from EventAttendee model where " +
				" date(model.attendedTime) =:todayDate and (model.eventId =:entryEventId or model.eventId =:exitEventId) and model.attendedTime is not null and " +
				"model.tdpCadreId is not null order by model.tdpCadreId, model.attendedTime");
		query.setDate("todayDate", todayDate);
		query.setParameter("entryEventId",entryEventId);
		query.setParameter("exitEventId",exitEventId);
		return query.list();event_invitee
	}*/
	
	public List<Object[]> getCadreVisitInfo(Date todayDate,Long entryEventId,Long exitEventId){
		//0eventId, 1attendedTime, 2tdpCadreId,3invitee tdpCadreId
		Query query=getSession().createSQLQuery("select ea.event_id,ea.attended_time,ea.tdp_cadre_id,ei.event_invitee_id from event_attendee ea left join  event_invitee  ei on ea.tdp_cadre_id = ei.tdp_cadre_id " +
				" where date(ea.attended_time) =:todayDate and (ea.event_id =:entryEventId or ea.event_id =:exitEventId) and ea.attended_time is not null and " +
				" ea.tdp_cadre_id is not null order by ea.tdp_cadre_id, ea.attended_time");
		query.setDate("todayDate", todayDate);
		query.setParameter("entryEventId",entryEventId);
		query.setParameter("exitEventId",exitEventId);
		return query.list();
	}
	
	
	
	public List<Object[]> getEventAttendeeInfoDynamicIndiDates(String locationType,Date eventStartDate,Date eventEndDate,List<Long> subEventIds){
		StringBuilder str = new StringBuilder();
		str.append("select model.event.eventId,count(distinct model.tdpCadre.tdpCadreId), ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
			str.append(" model.tdpCadre.userAddress.constituency.district.districtId,");
		}
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			str.append(" model.tdpCadre.userAddress.constituency.constituencyId,");
		}
		str.append(" date(model.attendedTime)");
		str.append(" from EventAttendee model ");
		str.append(" where model.event.eventId in(:subEventIds) ");
		str.append(" and (date(model.attendedTime) between :eventStartDate  and :eventEndDate) ");
		str.append(" and model.event.isActive =:isActive " +
				   " and model.tdpCadre.isDeleted = 'N' ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.district.districtId," +
					" date(model.attendedTime) order by model.event.eventId," +
					" model.tdpCadre.userAddress.constituency.district.districtId,date(model.attendedTime)");
		}
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.constituencyId," +
					" date(model.attendedTime) order by model.event.eventId," +
					" model.tdpCadre.userAddress.constituency.constituencyId,date(model.attendedTime)");
		}else{
			str.append(" group by model.event.eventId," +
					" date(model.attendedTime) order by model.event.eventId," +
					" date(model.attendedTime)");
		}
		Query query = getSession().createQuery(str.toString());
		query.setDate("eventStartDate", eventStartDate);
		query.setDate("eventEndDate",eventEndDate);
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameterList("subEventIds", subEventIds);
		return query.list();
	}
	
	public List<Object[]> getEventAttendeesSummary(String locationType,Date eventStartDate,Date eventEndDate,List<Long> subEventIds){
		
		StringBuilder str = new StringBuilder();
		str.append("select model.tdpCadre.tdpCadreId," +
				" count(model.tdpCadre.tdpCadreId), ");
		str.append(" date(model.attendedTime) ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
			str.append(" ,model.tdpCadre.userAddress.constituency.district.districtId ");
		}
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			str.append(" ,model.tdpCadre.userAddress.constituency.constituencyId ");
		}
		
		str.append(" from EventAttendee model ");
		str.append(" where model.event.eventId in(:subEventIds) ");
		str.append(" and date(model.attendedTime) between :eventStartDate and :eventEndDate ");
		str.append(" and model.event.isActive =:isActive " +
				   " and model.tdpCadre.isDeleted = 'N' ");
		
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
			str.append(" group by model.tdpCadre.tdpCadreId," +
					" model.tdpCadre.userAddress.constituency.district.districtId," +
					" date(model.attendedTime) order by model.tdpCadre.tdpCadreId," +
					" model.tdpCadre.userAddress.constituency.district.districtId,date(model.attendedTime)");
		}
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			str.append(" group by model.tdpCadre.tdpCadreId, " +
					" model.tdpCadre.userAddress.constituency.constituencyId," +
					" date(model.attendedTime) order by model.tdpCadre.tdpCadreId," +
					" model.tdpCadre.userAddress.constituency.constituencyId,date(model.attendedTime)");
		}else{
			str.append(" group by model.tdpCadre.tdpCadreId, " +
					" date(model.attendedTime) order by model.tdpCadre.tdpCadreId," +
					" date(model.attendedTime)");
		}
		Query query = getSession().createQuery(str.toString());
		query.setDate("eventStartDate", eventStartDate);
		query.setDate("eventEndDate", eventEndDate);
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameterList("subEventIds", subEventIds);
		return query.list();
	}
	
	public List<Object[]> getOtherStateDeligatesCount(Long parentEventId,Date startDate,Date endDate,List<Long> subeventIds)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select model.event.eventId,count(distinct model.tdpCadre.tdpCadreId),model.tdpCadre.userAddress.state.stateId,model.tdpCadre.userAddress.state.stateName from EventAttendee model where model.event.parentEventId =:parentEventId and model.event.eventId in(:subeventIds)");
		
		if((startDate != null && endDate != null))
		{
			if(startDate.equals(endDate))
			str.append(" and date(model.attendedTime) = :startDate "); 
			else
			str.append(" and date(model.attendedTime) >= :startDate and date(model.attendedTime) <= :endDate "); 
		}
		str.append(" and  model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'T' group by model.event.eventId,model.tdpCadre.userAddress.state.stateId,date(model.attendedTime) ");
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
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	
	public List<Object[]> getStateCount(Long eventId,Date startDate,Date endDate)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select model.tdpCadre.userAddress.constituency.district.districtId,count(distinct model.tdpCadre.tdpCadreId),date(model.attendedTime) ");
		str.append("  from EventAttendee model where model.event.eventId = :eventId");
		if((startDate != null && endDate != null))
		{
			if(startDate.equals(endDate))
			str.append(" and date(model.attendedTime) = :startDate "); 
			else
			str.append(" and date(model.attendedTime) >= :startDate and date(model.attendedTime) <= :endDate "); 
		}
		
		str.append(" and model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' group by date(model.attendedTime),model.tdpCadre.userAddress.constituency.district.districtId ");
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
		query.setParameter("eventId", eventId);
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	
	public List<Object[]> getOtherStateCount(Long eventId,Date startDate,Date endDate)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select count(distinct model.tdpCadre.tdpCadreId),model.tdpCadre.userAddress.state.stateId,model.tdpCadre.userAddress.state.stateName,date(model.attendedTime) from EventAttendee model where model.event.eventId =:eventId");
		
		if((startDate != null && endDate != null))
		{
			if(startDate.equals(endDate))
			str.append(" and date(model.attendedTime) = :startDate "); 
			else
			str.append(" and date(model.attendedTime) >= :startDate and date(model.attendedTime) <= :endDate "); 
		}
		str.append(" and  model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'T' group by model.tdpCadre.userAddress.state.stateId,date(model.attendedTime) ");
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
		
		query.setParameter("eventId", eventId);
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
		
	public List<Object[]> getEventAttendeeInfoDynamicIndiDatesForInvities(String locationType,Date eventStartDate,Date eventEndDate,List<Long> subEventIds){
		StringBuilder str = new StringBuilder();
		str.append("select model.event.eventId,count(distinct model.tdpCadre.tdpCadreId), ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
			str.append(" model.tdpCadre.userAddress.constituency.district.districtId,");
		}
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			str.append(" model.tdpCadre.userAddress.constituency.constituencyId,");
		}
		str.append(" date(model.attendedTime)");
		str.append(" from EventAttendee model,EventInvitee model1 ");
		str.append(" where model.event.eventId in(:subEventIds) and model.tdpCadreId = model1.tdpCadreId ");
		str.append(" and date(model.attendedTime) between :eventStartDate and  :eventEndDate ");
		str.append(" and model.event.isActive =:isActive " +
				   " and model.tdpCadre.isDeleted = 'N' ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.district.districtId," +
					" date(model.attendedTime) order by model.event.eventId," +
					" model.tdpCadre.userAddress.constituency.district.districtId,date(model.attendedTime)");
		}
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.constituencyId," +
					" date(model.attendedTime) order by model.event.eventId," +
					" model.tdpCadre.userAddress.constituency.constituencyId,date(model.attendedTime)");
		}else{
			str.append(" group by model.event.eventId," +
					" date(model.attendedTime) order by model.event.eventId," +
					" date(model.attendedTime)");
		}
		Query query = getSession().createQuery(str.toString());
		query.setDate("eventStartDate", eventStartDate);
		query.setDate("eventEndDate", eventEndDate);
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameterList("subEventIds", subEventIds);
		return query.list();
	}
	
public List<Object[]> getEventAttendeesSummaryForInvities(String locationType,Date eventStartDate,Date eventEndDate,List<Long> subEventIds){
		
		StringBuilder str = new StringBuilder();
		str.append("select model.tdpCadre.tdpCadreId," +
				" count(model.tdpCadre.tdpCadreId), ");
		str.append(" date(model.attendedTime) ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
			str.append(" ,model.tdpCadre.userAddress.constituency.district.districtId ");
		}
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			str.append(" ,model.tdpCadre.userAddress.constituency.constituencyId ");
		}
		
		str.append(" from EventAttendee model,EventInvitee model1  ");
		str.append(" where model.event.eventId in(:subEventIds)  and model.tdpCadreId = model1.tdpCadreId ");
		str.append(" and date(model.attendedTime) between :eventStartDate and :eventEndDate ");
		str.append(" and model.event.isActive =:isActive " +
				   " and model.tdpCadre.isDeleted = 'N' ");
		
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
			str.append(" group by model.tdpCadre.tdpCadreId," +
					" model.tdpCadre.userAddress.constituency.district.districtId," +
					" date(model.attendedTime) order by model.tdpCadre.tdpCadreId," +
					" model.tdpCadre.userAddress.constituency.district.districtId,date(model.attendedTime)");
		}
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			str.append(" group by model.tdpCadre.tdpCadreId, " +
					" model.tdpCadre.userAddress.constituency.constituencyId," +
					" date(model.attendedTime) order by model.tdpCadre.tdpCadreId," +
					" model.tdpCadre.userAddress.constituency.constituencyId,date(model.attendedTime)");
		}else{
			str.append(" group by model.tdpCadre.tdpCadreId, " +
					" date(model.attendedTime) order by model.tdpCadre.tdpCadreId," +
					" date(model.attendedTime)");
		}
		Query query = getSession().createQuery(str.toString());
		query.setDate("eventStartDate", eventStartDate);
		query.setDate("eventEndDate", eventEndDate);
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameterList("subEventIds", subEventIds);
		return query.list();
	}

	public List<Object[]> getEventDetailsOfCadre(Long cadreId){
		
		/*select ea.tdp_cadre_id,e.event_id,e.name,e.description,e.parent_event_id,
		count(distinct date(ea.attended_time)) from event e, event_attendee ea where 
		e.event_id = ea.event_id and
		ea.tdp_cadre_id=8795444 
		group by e.event_id;*/
		/*Query queryStr=getSession().createQuery(" select COUNT(distinct date(model.attendedTime)),model.tdpCadre.tdpCadreId,model.event.eventId,model.event.name," +
				" model.event.description,model.event.parentEventId " +
				" from  EventAttendee model" +
				" where model.tdpCadre.tdpCadreId =:tdpCadreId " +
				" group by model.event.eventId ");*/
		Query queryStr=getSession().createSQLQuery("select ea.tdp_cadre_id,e.event_id,e.name,e.description,e.parent_event_id, count(distinct date(ea.attended_time)),e.event_type_id " +
				" from event e, event_attendee ea where " +
				" e.event_id = ea.event_id and ea.tdp_cadre_id = :tdpCadreId and e.is_active ='true'  group by e.event_id ");
		queryStr.setParameter("tdpCadreId", cadreId);
	return queryStr.list();	
	}
	public List<Object[]> getEventDetailsOfCadre1(Long cadreId){
		
		Query queryStr=getSession().createQuery("select model.tdpCadre.tdpCadreId,model.event.eventId,model.event.name," +
				" model.event.description,model.event.parentEventId,count(date(model.attendedTime)) from  EventAttendee model" +
				" where model.tdpCadre.tdpCadreId ="+cadreId+" " +
						" group by model.event.eventId ");
		
		return queryStr.list();
	}
	
	public List checkEventsyncData(String rfid,String imei,Long eventId,String uniqueKey)
	{
		Query queryStr=getSession().createQuery("select model.eventAttendeeId from EventAttendee model" +
				" where model.event.eventId=:eventId and model.imei =:imei and model.rfid =:rfid" +
				" and model.uniqueKey =:uniqueKey");
		queryStr.setParameter("rfid", rfid);
		queryStr.setParameter("imei", imei);
		queryStr.setParameter("eventId", eventId);
		queryStr.setParameter("uniqueKey", uniqueKey);
		return queryStr.list();
	}
	
	public List<Object[]> getAttendedEventsCountforCandidate(Long tdpCadreId)
	{
		Query query = getSession().createQuery(" select model.eventId,model.event.name, count(distinct model.tdpCadreId)  from EventAttendee model" +
				" where model.tdpCadreId =:tdpCadreId and " +
				" model.event.isActive ='true' and model.event.eventTypeId != 2 group by model.event.parentEventId  ");
		query.setParameter("tdpCadreId", tdpCadreId);
		return  query.list(); 	
}
	
	public Long getTodayTotalInviteeVisitors(Date todayDate,Long parentEventId,Long entryEventId){
		Query query=getSession().createQuery("select count(distinct model.tdpCadreId) from EventAttendee model,EventInvitee model1 where date(model.attendedTime) =:todayDate " +
				//" and model.event.parentEventId =:parentEventId " +
				" and model.eventId =:entryEventId " +
				" and model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId " +				
				" and model1.tdpCadreId = model.tdpCadreId " +
				" and model.tdpCadre.isDeleted = 'N' " +
				" and model.tdpCadre.enrollmentYear =:enrollmentYear" );
		query.setDate("todayDate", todayDate);
		//query.setParameter("parentEventId", parentEventId);
		query.setParameter("entryEventId", entryEventId);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_YEAR);
		
		 return (Long)query.uniqueResult();
	}
	
	//Only For Today Total Visitors
	public List<Object[]> getHourWiseTotalVisitorsCount1(Long parentEventId,Date date,List<Long> subeventIds,String type){
		
		StringBuilder str = new StringBuilder();
		str.append(" select  count(distinct model.tdpCadre.tdpCadreId),max(hour(model.attendedTime)) from EventAttendee model " );
		
		if(type !=null && type.equalsIgnoreCase("Invitee")){//For Invited Count
			str.append(" ,EventInvitee model1 ");
		}
		
		str.append(" where " +
				" model.event.parentEventId = :parentEventId and date(model.attendedTime) = :date");
		
		if(type !=null && type.equalsIgnoreCase("Invitee")){//For Invited Count
			str.append(" and model.tdpCadreId = model1.tdpCadreId ");
		}
		
		str.append(" and model.tdpCadre.isDeleted = 'N'" +
				" group by hour(model.attendedTime) ");
		Query query = getSession().createQuery(str.toString());
		query.setDate("date", date);			
		query.setParameter("parentEventId",parentEventId);
	//	query.setParameter("isActive", IConstants.TRUE);
	//	query.setParameter("isVisible", IConstants.IS_VISIBLE);
		return query.list();
		
	}
	
	public List<Object[]> getHourWiseCurrentVisitorsCount(Date todayDate,Long entryEventId,Long exitEventId,String type){
		
			StringBuilder str = new StringBuilder();
		
			str.append("select count(distinct ea1.tdp_cadre_id) as count,hour(ea1.attended_time) as hour from event_attendee ea1 inner join " +
				        " (select tdp_cadre_id as cadre_id, max(attended_time) as max_time from event_attendee " +
						" where date(attended_time) =:todayDate and (event_id =:entryEventId or event_id =:exitEventId) group by tdp_cadre_id) as ea2 " +
						" ON ea1.tdp_cadre_id =  ea2.cadre_id and ea1.attended_time = ea2.max_time ");
		
			if(type !=null && type.equalsIgnoreCase("Invitee")){
				str.append(" ,event_invitee ei ");
			}	
			str.append(" where " +
			        " date(ea1.attended_time) =:todayDate and ea1.event_id =:entryEventId" );
			
			if(type !=null && type.equalsIgnoreCase("Invitee")){
				str.append(" and ea1.tdp_cadre_id = ei.tdp_cadre_id  ");
			}
			str.append(" group by hour(ea1.attended_time) order by hour(ea1.attended_time) ");
		
		Query query = getSession().createSQLQuery(str.toString())
				.addScalar("count",Hibernate.LONG)
				.addScalar("hour",Hibernate.LONG);
		
		query.setDate("todayDate", todayDate);
		query.setParameter("exitEventId", exitEventId);
		query.setParameter("entryEventId", entryEventId);
		
		return query.list();
	}
	
	//Invited,non-invited and Total for Today Visitors
	public List<Object[]> getHourWiseTotalVisitorsCount(Long parentEventId,Date date,List<Long> subeventIds,String type){
		
		StringBuilder str = new StringBuilder();
		
		/*str.append(" select count(distinct EA.tdp_cadre_id) as TOTAL,count(distinct EI.tdp_cadre_id) as INVITES ," +
				" (count(distinct EA.tdp_cadre_id)-count(distinct EI.tdp_cadre_id)) as NONINVITEES " +
				" ,hour(EA.attended_time)  as HOUR from  event E, " +
				" event_attendee EA left outer join event_invitee EI " +
				" on EA.tdp_cadre_id = EI.tdp_cadre_id " +
				" and EI.event_id=:parentEventId " +
				" where" +
				" EA.event_id = E.event_id" +
				" and date(EA.attended_time) = :date " +
				" and E.event_id = :parentEventId " +
				" group by  hour(EA.attended_time) order by hour(EA.attended_time);");*/
		str.append("select count(tdp_cadre_id) as TOTAL,Hours as HOUR" +
				" from (select distinct tdp_cadre_id,max(hour(attended_time)) Hours from event_attendee " +
				" where event_id=:parentEventId and date(attended_time)=:date group by tdp_cadre_id) sadf group by Hours");
		
		Query query = getSession().createSQLQuery(str.toString())
				.addScalar("TOTAL",Hibernate.LONG)
				.addScalar("HOUR",Hibernate.LONG);
		
		query.setDate("date", date);			
		query.setParameter("parentEventId",parentEventId);
		
		return query.list();
		
	}
	public Long getTotalAttendedCountOfEvent(Long eventId){
	    Query query = getSession().createQuery("select count(distinct model.tdpCadreId) from EventAttendee model" +
	        " where" +
	        " model.event.parentEventId = :eventId " );
	    
	    query.setParameter("eventId",eventId);
	    
	    return (Long)query.uniqueResult();
	  }
	
	public List<Object[]> getDistrictWiseCurrentCadreInCampus(Date todayDate,Long entryEventId,Long exitEventId,String districtQueryStr){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select count(distinct EA.tdp_cadre_id) as currentCadre,d.district_id as districtId " +
				" from " +
				" tdp_cadre TC,user_address UA,district d,event_attendee EA inner join " +
				" (select tdp_cadre_id as cadre_id, max(attended_time) as max_time" +
				" from event_attendee " +
				" where " +
				" date(attended_time) =:todayDate " +
				" and (event_id =:entryEventId or event_id =:exitEventId) group by tdp_cadre_id) as EA2" +
				" ON EA.tdp_cadre_id =  EA2.cadre_id and EA.attended_time = EA2.max_time" +
				" where " +
				" date(EA.attended_time) =:todayDate and EA.event_id =:entryEventId " +
				" and EA.tdp_cadre_id = TC.tdp_cadre_id" +
				" and TC.address_id = UA.user_address_id " +
				" and UA.district_id = d.district_id " );
		
			if(districtQueryStr !=null && !districtQueryStr.isEmpty()){
				str.append(districtQueryStr);
			}
		
				str.append(" group by UA.district_id"); 
		
		Query query = getSession().createSQLQuery(str.toString())
				.addScalar("currentCadre",Hibernate.LONG)
				.addScalar("districtId",Hibernate.LONG);
		
		query.setParameter("entryEventId", entryEventId);
		query.setParameter("exitEventId", exitEventId);
		query.setDate("todayDate", todayDate);
		
				
		return query.list();
	}
	
	public List<Object[]> getConstituencyWiseCurrentCadreInCampus(Date todayDate,Long entryEventId,Long exitEventId,String constituecnyQueryStr){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select count(distinct EA.tdp_cadre_id) as currentCadre,c.constituency_id as constituecnyId " +
				" from " +
				" tdp_cadre TC,user_address UA,constituency c,event_attendee EA inner join " +
				" (select tdp_cadre_id as cadre_id, max(attended_time) as max_time" +
				" from event_attendee " +
				" where " +
				" date(attended_time) =:todayDate " +
				" and (event_id =:entryEventId or event_id =:exitEventId) group by tdp_cadre_id) as EA2" +
				" ON EA.tdp_cadre_id =  EA2.cadre_id and EA.attended_time = EA2.max_time" +
				" where " +
				" date(EA.attended_time) =:todayDate and EA.event_id =:entryEventId " +
				" and EA.tdp_cadre_id = TC.tdp_cadre_id" +
				" and TC.address_id = UA.user_address_id " +
				" and UA.constituency_id=c.constituency_id" );
		
			if(constituecnyQueryStr !=null && !constituecnyQueryStr.isEmpty()){
				str.append(constituecnyQueryStr);
			}
		
				str.append(" group by UA.constituency_id"); 
		
		Query query = getSession().createSQLQuery(str.toString())
				.addScalar("currentCadre",Hibernate.LONG)
				.addScalar("constituecnyId",Hibernate.LONG);
		
		query.setParameter("entryEventId", entryEventId);
		query.setParameter("exitEventId", exitEventId);
		query.setDate("todayDate", todayDate);
		
				
		return query.list();
	}
	
	public List<Object[]> getDistrictWiseTotalInvitedAndNonInvitedCount(Long entryEventId,String districtQueryStr,Date toDayDate){
		
		StringBuilder str = new StringBuilder();
		
		/*str.append("select count(distinct EA.tdp_cadre_id) as total,count(distinct EI.tdp_cadre_id) as invitees," +
				"(count(distinct EA.tdp_cadre_id)-count(distinct EI.tdp_cadre_id)) as NonInvitees,d.district_id as districtId,d.district_name as districtName" +
				" from " +
				" tdp_cadre TC,user_address UA,district d ,event E,event_attendee EA left outer join" +
				" event_invitee EI on EA.tdp_cadre_id = EI.tdp_cadre_id and EI.event_id = :eventId " +
				" where " +
				" EA.event_id = E.event_id " +
				" and EA.tdp_cadre_id = TC.tdp_cadre_id " +
				" and TC.address_id = UA.user_address_id " +
				" and UA.district_id = d.district_id " +
				" and E.parent_event_id = :eventId " );*/
		/*str.append("select count(distinct EA.tdp_cadre_id) as total,d.district_id as districtId,d.district_name as districtName" +
				" from " +
				" tdp_cadre TC,user_address UA,district d ,event E,event_attendee EA " +
				" where " +
				" EA.event_id = E.event_id " +
				" and EA.tdp_cadre_id = TC.tdp_cadre_id " +
				" and TC.address_id = UA.user_address_id " +
				" and UA.district_id = d.district_id " +
				" and E.parent_event_id = :eventId " +
				" and date(EA.attended_time) = :toDayDate " );
		
		if(districtQueryStr !=null && !districtQueryStr.isEmpty()){
			str.append(districtQueryStr);
		}
		
		str.append(" group by d.district_id");
		
		Query query = getSession().createSQLQuery(str.toString())
				.addScalar("total",Hibernate.LONG)
				.addScalar("districtId",Hibernate.LONG)
				.addScalar("districtName",Hibernate.STRING);*/
		
		str.append(" select count(distinct model.tdpCadreId),model.tdpCadre.userAddress.constituency.district.districtId,model.tdpCadre.userAddress.constituency.district.districtName " +
				" from EventAttendee model " +
				" where model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = :ernrolYear ");
			if(districtQueryStr !=null && !districtQueryStr.isEmpty()){
					str.append(districtQueryStr);
			}
		str.append(" and date(model.attendedTime)=:toDayDate and model.eventId=:entryEventId group by model.tdpCadre.userAddress.constituency.district.districtId");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("entryEventId", entryEventId);
		query.setDate("toDayDate", toDayDate);
		query.setParameter("ernrolYear", IConstants.CADRE_ENROLLMENT_NUMBER);
		
		return query.list();
	}
	
	public List<Object[]> getConstituencyWiseTotalInvitedAndNonInvitedCount(Long entryEventId,String constituencyQueryStr,Date toDayDate){
		
		StringBuilder str = new StringBuilder();
		str.append(" select count(distinct model.tdpCadreId),model.tdpCadre.userAddress.constituency.constituencyId,model.tdpCadre.userAddress.constituency.name " +
				" from EventAttendee model " +
				" where model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = :ernrolYear ");
			if(constituencyQueryStr !=null && !constituencyQueryStr.isEmpty()){
					str.append(constituencyQueryStr);
			}
		str.append(" and date(model.attendedTime)=:toDayDate and model.eventId=:entryEventId group by model.tdpCadre.userAddress.constituency.constituencyId");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("entryEventId", entryEventId);
		query.setDate("toDayDate", toDayDate);
		query.setParameter("ernrolYear", IConstants.CADRE_ENROLLMENT_NUMBER);
		
		return query.list();
	}

	public List<Object[]>  locationWiseEventAttendeeCountsQuery(String locationType,String inviteeType,Date startDate,Date endDate,List<Long> eventIds,String queryString){
		
		StringBuilder sbS =  new StringBuilder();
		StringBuilder sbM =  new StringBuilder();
		StringBuilder sbE =  new StringBuilder();
		
		sbS.append("select ");
		
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
			sbS.append("  model.tdpCadre.userAddress.constituency.district.districtId,model.tdpCadre.userAddress.constituency.district.districtName ");
			
			sbE.append("  group by model.tdpCadre.userAddress.constituency.district.districtId" +
					   "  order by count(distinct model.tdpCadre.tdpCadreId) desc");
		}
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			sbS.append("  model.tdpCadre.userAddress.constituency.constituencyId,model.tdpCadre.userAddress.constituency.name ");
			
			sbE.append("  group by model.tdpCadre.userAddress.constituency.constituencyId " +
					   "  order by count(distinct model.tdpCadre.tdpCadreId) desc ");
		}
		sbS.append(" ,count(distinct model.tdpCadre.tdpCadreId) ");
		if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			sbS.append(" ,model.tdpCadre.userAddress.constituency.district.districtName ");	
		}
		
		if(inviteeType.equalsIgnoreCase("attendee")){
			sbM.append(" from EventAttendee model where ");
		}
		if(inviteeType.equalsIgnoreCase("invitee")){
			sbM.append(" from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId  and ");
		}
		
		sbM.append(" model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 ");
		if(eventIds != null && eventIds.size() > 0){
			sbM.append(" and model.event.eventId in  (:eventIds)");
		}
		sbM.append("  and date(model.attendedTime) between :startDate and :endDate ");
		
		if(queryString != null && !queryString.isEmpty()){
			sbM.append( queryString );
		}
		
		StringBuilder sq = new StringBuilder();
        sq.append(sbS.toString()).append(sbM.toString()).append(sbE.toString());
        
        Query query = getSession().createQuery(sq.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		if(eventIds != null && eventIds.size() > 0){
			query.setParameterList("eventIds", eventIds);
		}
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	public List<Object[]> locationWiseEventAttendeeCountsByDateQuery(String locationType,String inviteeType,Date startDate,Date endDate,List<Long> eventIds,String queryString){
			
			StringBuilder sbS =  new StringBuilder();
			StringBuilder sbM =  new StringBuilder();
			StringBuilder sbE =  new StringBuilder();
			
			sbS.append("select ");
			
			if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
				sbS.append("  model.tdpCadre.userAddress.constituency.district.districtId,model.tdpCadre.userAddress.constituency.district.districtName ");
				
				sbE.append("  group by model.tdpCadre.userAddress.constituency.district.districtId,date(model.attendedTime) " +
						   "  order by model.tdpCadre.userAddress.constituency.district.districtId,date(model.attendedTime)");
			}
			else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				sbS.append("  model.tdpCadre.userAddress.constituency.constituencyId,model.tdpCadre.userAddress.constituency.name ");
				
				sbE.append("  group by model.tdpCadre.userAddress.constituency.constituencyId,date(model.attendedTime) " +
						   "  order by model.tdpCadre.userAddress.constituency.name,date(model.attendedTime) ");
			}
			sbS.append(" ,date(model.attendedTime),count(distinct model.tdpCadre.tdpCadreId) ");
			
			
			if(inviteeType.equalsIgnoreCase("attendee")){
				sbM.append(" from EventAttendee model where ");
			}
			if(inviteeType.equalsIgnoreCase("invitee")){
				sbM.append(" from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId  and ");
			}
			
			sbM.append(" model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 ");
			if(eventIds != null && eventIds.size() > 0){
				sbM.append(" and model.event.eventId in  (:eventIds)");
			}
			sbM.append("  and date(model.attendedTime) between :startDate and :endDate ");
			
			if(queryString != null && !queryString.isEmpty()){
				sbM.append( queryString );
			}
			
			StringBuilder sq = new StringBuilder();
	        sq.append(sbS.toString()).append(sbM.toString()).append(sbE.toString());
	        
	        Query query = getSession().createQuery(sq.toString());
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
			if(eventIds != null && eventIds.size() > 0){
				query.setParameterList("eventIds", eventIds);
			}
			query.setParameter("isActive", IConstants.TRUE);
			return query.list();
	   }
	
	public Long stateWiseEventAttendeeCounts(String inviteeType,Date startDate,Date endDate,List<Long> eventIds,Long stateId ,String statesType){
		
		StringBuilder sb =  new StringBuilder();
		
		sb.append("select count(distinct model.tdpCadre.tdpCadreId) ");
				
		if(inviteeType.equalsIgnoreCase("attendee")){
			sb.append(" from EventAttendee model where ");
		}
		if(inviteeType.equalsIgnoreCase("invitee")){
			sb.append(" from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId  and ");
		}
		sb.append(" model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 ");
		if(eventIds != null && eventIds.size() > 0){
			sb.append(" and model.event.eventId in  (:eventIds)");
		}
		sb.append("  and date(model.attendedTime) between :startDate and :endDate ");
		
		if( statesType.equalsIgnoreCase("overall")){
			sb.append(" and model.tdpCadre.userAddress.state.stateId is not null ");
		}else{
			if(stateId.longValue() == 1l){
				sb.append(" and model.tdpCadre.userAddress.constituency.district.districtId between 11 and 23 ");
			}else if(stateId.longValue() == 36l){
				sb.append(" and model.tdpCadre.userAddress.constituency.district.districtId between 1 and 10 ");
			}else if(stateId.longValue() == 0l){
				sb.append(" and model.tdpCadre.userAddress.state.stateId !=1 and model.tdpCadre.userAddress.state.stateId is not null ");
			}
		}
        
        Query query = getSession().createQuery(sb.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		if(eventIds != null && eventIds.size() > 0){
			query.setParameterList("eventIds", eventIds);
		}
		query.setParameter("isActive", IConstants.TRUE);
		return (Long)query.uniqueResult();
	}
	 public List<Object[]> stateWiseEventAttendeeCountsByDates(String inviteeType,Date startDate,Date endDate,List<Long> eventIds,Long stateId ,String statesType){
			
			StringBuilder sb =  new StringBuilder();
			
			sb.append("select date(model.attendedTime),count(distinct model.tdpCadre.tdpCadreId)");
			if(inviteeType.equalsIgnoreCase("attendee")){
				sb.append(" from EventAttendee model where ");
			}
			if(inviteeType.equalsIgnoreCase("invitee")){
				sb.append(" from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId  and ");
			}
			sb.append(" model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 ");
			if(eventIds != null && eventIds.size() > 0){
				sb.append(" and model.event.eventId in  (:eventIds)");
			}
			sb.append(" and date(model.attendedTime) between :startDate and :endDate ");
			
			if( statesType.equalsIgnoreCase("overall")){
				sb.append(" and model.tdpCadre.userAddress.state.stateId is not null ");
			}else{
				if(stateId.longValue() == 1l){
					sb.append(" and model.tdpCadre.userAddress.constituency.district.districtId between 11 and 23 ");
				}else if(stateId.longValue() == 36l){
					sb.append(" and model.tdpCadre.userAddress.constituency.district.districtId between 1 and 10 ");
				}else if(stateId.longValue() == 0l){
					sb.append(" and model.tdpCadre.userAddress.state.stateId !=1 and model.tdpCadre.userAddress.state.stateId is not null ");
				}
			}	
			sb.append(" group by date(model.attendedTime) order by date(model.attendedTime)" );
	        
	        Query query = getSession().createQuery(sb.toString());
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
			if(eventIds != null && eventIds.size() > 0){
				query.setParameterList("eventIds", eventIds);
			}
			query.setParameter("isActive", IConstants.TRUE);
			return query.list();
	   }
	 
	public List<Object[]> getOtherStateDistrictWiseCurrentCadreInCampus(Date todayDate,Long entryEventId,Long exitEventId,String queryStr){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select count(distinct EA.tdp_cadre_id) as currentCadre,d.district_id as districtId " +
				" from " +
				" tdp_cadre TC,user_address UA,constituency constituency,district d,event_attendee EA inner join " +
				" (select tdp_cadre_id as cadre_id, max(attended_time) as max_time" +
				" from event_attendee " +
				" where " +
				" date(attended_time) =:todayDate " +
				" and (event_id =:entryEventId or event_id =:exitEventId) group by tdp_cadre_id) as EA2" +
				" ON EA.tdp_cadre_id =  EA2.cadre_id and EA.attended_time = EA2.max_time" +
				" where " +
				" date(EA.attended_time) =:todayDate and EA.event_id =:entryEventId " +
				" and EA.tdp_cadre_id = TC.tdp_cadre_id" +
				" and TC.address_id = UA.user_address_id " +
				" and UA.constituency_id = constituency.constituency_id " +
				" and constituency.district_id = d.district_id " );
		
			if(queryStr !=null && !queryStr.isEmpty()){
				str.append(queryStr);
			}
		
				str.append(" group by d.district_id"); 
		
		Query query = getSession().createSQLQuery(str.toString())
				.addScalar("currentCadre",Hibernate.LONG)
				.addScalar("districtId",Hibernate.LONG);
		
		query.setParameter("entryEventId", entryEventId);
		query.setParameter("exitEventId", exitEventId);
		query.setDate("todayDate", todayDate);
		
				
		return query.list();
	}
	
	public List<Object[]> getOtherStateConstituencyWiseCurrentCadreInCampus(Date todayDate,Long entryEventId,Long exitEventId,String queryStr){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select count(distinct EA.tdp_cadre_id) as currentCadre,c.constituency_id as constituencyId " +
				" from " +
				" tdp_cadre TC,user_address UA,constituency c,event_attendee EA inner join " +
				" (select tdp_cadre_id as cadre_id, max(attended_time) as max_time" +
				" from event_attendee " +
				" where " +
				" date(attended_time) =:todayDate " +
				" and (event_id =:entryEventId or event_id =:exitEventId) group by tdp_cadre_id) as EA2" +
				" ON EA.tdp_cadre_id =  EA2.cadre_id and EA.attended_time = EA2.max_time" +
				" where " +
				" date(EA.attended_time) =:todayDate and EA.event_id =:entryEventId " +
				" and EA.tdp_cadre_id = TC.tdp_cadre_id" +
				" and TC.address_id = UA.user_address_id " +
				" and UA.constituency_id = c.constituency_id " );
		
			if(queryStr !=null && !queryStr.isEmpty()){
				str.append(queryStr);
			}
		
				str.append(" group by c.constituency_id"); 
		
		Query query = getSession().createSQLQuery(str.toString())
				.addScalar("currentCadre",Hibernate.LONG)
				.addScalar("constituencyId",Hibernate.LONG);
		
		query.setParameter("entryEventId", entryEventId);
		query.setParameter("exitEventId", exitEventId);
		query.setDate("todayDate", todayDate);
		
				
		return query.list();
	}
	
	public List<Object[]> getOtherStatesDistrictWiseTotalInvitedAndNonInvitedCount(Long entryEventId,String queryStr,Date todateDate){
		
		StringBuilder str = new StringBuilder();
		
		/*str.append("select count(distinct EA.tdp_cadre_id) as total,count(distinct EI.tdp_cadre_id) as invitees," +
				"(count(distinct EA.tdp_cadre_id)-count(distinct EI.tdp_cadre_id)) as NonInvitees,d.district_id as districtId,d.district_name as districtName" +
				" from " +
				" tdp_cadre TC,user_address UA,state state,constituency constituency,district d ,event E,event_attendee EA left outer join" +
				" event_invitee EI on EA.tdp_cadre_id = EI.tdp_cadre_id and EI.event_id = :eventId " +
				" where " +
				" EA.event_id = E.event_id " +
				" and EA.tdp_cadre_id = TC.tdp_cadre_id " +
				" and TC.address_id = UA.user_address_id " +
				" and UA.constituency_id = constituency.constituency_id " +
				" and constituency.district_id = d.district_id " +
				" and E.parent_event_id = :eventId " );*/
		/*str.append("select count(distinct EA.tdp_cadre_id) as total,d.district_id as districtId,d.district_name as districtName" +
				" from " +
				" tdp_cadre TC,user_address UA,state state,constituency constituency,district d ,event E,event_attendee EA" +
				" where " +
				" EA.event_id = E.event_id " +
				" and EA.tdp_cadre_id = TC.tdp_cadre_id " +
				" and TC.address_id = UA.user_address_id " +
				" and UA.constituency_id = constituency.constituency_id " +
				" and constituency.district_id = d.district_id " +
				" and E.parent_event_id = :eventId " +
				" and date(EA.attended_time)=:todateDate " );
		
		if(queryStr !=null && !queryStr.isEmpty()){
			str.append(queryStr);
		}
		
		str.append(" group by d.district_id");
		
		Query query = getSession().createSQLQuery(str.toString())
				.addScalar("total",Hibernate.LONG)
				.addScalar("districtId",Hibernate.LONG)
				.addScalar("districtName",Hibernate.STRING);*/
		str.append(" select count(distinct model.tdpCadreId),model.tdpCadre.userAddress.constituency.district.districtId,model.tdpCadre.userAddress.constituency.district.districtName " +
				"from EventAttendee model " +
				" where model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = :ernrolYear ");
			if(queryStr !=null && !queryStr.isEmpty()){
					str.append(queryStr);
			}
		str.append(" and date(model.attendedTime)=:toDayDate and model.eventId=:entryEventId group by model.tdpCadre.userAddress.constituency.district.districtId");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("entryEventId", entryEventId);
		query.setDate("toDayDate", todateDate);
		query.setParameter("ernrolYear", IConstants.CADRE_ENROLLMENT_YEAR);
		
		return query.list();
	}
	
	public List<Object[]> getOtherStatesConstituencyWiseTotalInvitedAndNonInvitedCount(Long entryEventId,String queryStr,Date todayDate){
		StringBuilder str = new StringBuilder();
		
		str.append(" select count(distinct model.tdpCadreId),model.tdpCadre.userAddress.constituency.constituencyId,model.tdpCadre.userAddress.constituency.name " +
				"from EventAttendee model " +
				" where model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = :ernrolYear ");
			if(queryStr !=null && !queryStr.isEmpty()){
					str.append(queryStr);
			}
		str.append(" and date(model.attendedTime)=:toDayDate and model.eventId=:entryEventId group by model.tdpCadre.userAddress.constituency.constituencyId");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("entryEventId", entryEventId);
		query.setDate("toDayDate", todayDate);
		query.setParameter("ernrolYear", IConstants.CADRE_ENROLLMENT_YEAR);
		
		return query.list();
		
	}
	
	/*public List<Object[]> getPublicReprestinviteeCount(List<Long> publicRepresentIds){
		
	}*/
	
	public List<Long> getAttendenceDetails(List<Long> cadreIds,Date date,Long eventId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.tdpCadreId " +
				" from EventAttendee model where model.tdpCadreId in (:cadreIds) and model.event.parentEventId=:eventId" +
				" and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 ");
		if(date != null){
			sb.append(" and date(model.attendedTime)=:date");
		}
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("cadreIds", cadreIds);
		query.setParameter("eventId", eventId);
		if(date != null){
			query.setDate("date", date);
		}
		return query.list();
	}
	
	public List<Long> getCadreIdsForAttendees(Long eventId,Date date,Long designationId){
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.tdpCadreId " +
				" from EventAttendee model,TdpCadreCandidate tcc,PublicRepresentative pr " +
				" where " +
				" model.tdpCadreId = tcc.tdpCadreId " +
				" and tcc.candidateId=pr.candidateId " +
				" and  pr.publicRepresentativeTypeId=:designationId " +
				" and model.event.parentEventId=:eventId " +
				"  and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014");
		if(date != null)
		str.append("and date(model.attendedTime)=:date");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("eventId", eventId);
		if(date != null)
		query.setDate("date", date);
		query.setParameter("designationId", designationId);
		
		return query.list();
	}
	
	public List<Object[]> getEventAttendedDetails(Long cadreId,Long eventId){
		Query query = getSession().createQuery("select date(model.attendedTime)," +
								" count(model.eventAttendeeId)," +
								" model.attendedTime" +
								" from EventAttendee model" +
								" where model.tdpCadreId = :cadreId" +
								" and model.event.eventId = :eventId and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014" +
								" group by date(model.attendedTime)");
		query.setParameter("cadreId", cadreId);
		query.setParameter("eventId", eventId);
		
		return query.list();
	}
	
	public List<Object[]> getEventAttendedInfoForCadre(Long cadreId,Long eventId){
		Query query = getSession().createQuery("select date(model.attendedTime)," +
									" model.attendedTime" +
									" from EventAttendee model" +
									" where model.tdpCadreId = :cadreId" +
									" and model.event.eventId = :eventId and model.tdpCadre.isDeleted = 'N' " +
									" and model.tdpCadre.enrollmentYear = 2014 order by date(model.attendedTime) ");
		query.setParameter("cadreId", cadreId);
		query.setParameter("eventId", eventId);
		
		return query.list();
	}
	
	public List<Long> getCadreIdsForAttendeesForCommitteeLevel(Long eventId,Date date,Long committeeLevelId){
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.tdpCadreId " +
				" from EventAttendee model,TdpCommitteeMember TCM ,EventInvitee ei" +
				" where " +
				"  ei.tdpCadreId=TCM.tdpCadreId  and model.event.parentEventId = ei.event.eventId and model.tdpCadre.tdpCadreId = ei.tdpCadre.tdpCadreId" +
				" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId=:committeeLevelId " +
				" and model.event.parentEventId=:eventId and model.event.isInviteeExist = 'Y' and  model.event.isActive =:isActive" +
				"  and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014");
		if(date != null)
		str.append("and date(model.attendedTime)=:date");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("eventId", eventId);
		if(date != null)
		query.setDate("date", date);
		query.setParameter("committeeLevelId", committeeLevelId);
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	
	public List<Long> getCadreIdsForAttendeesForCommitteeRole(Long eventId,Date date,Long committeeRoleId,String committeeLevel){
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.tdpCadreId " +
				" from EventAttendee model,TdpCommitteeMember TCM,EventInvitee ei " +
				"  where model.event.parentEventId=:eventId " +
				" and ei.tdpCadreId=TCM.tdpCadreId and model.event.parentEventId = ei.event.eventId and model.tdpCadre.tdpCadreId = ei.tdpCadre.tdpCadreId " +
				" and TCM.tdpCommitteeRole.tdpRoles.tdpRolesId=:committeeRoleId " +
				" and TCM.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId = 1" +
				"  and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014" +
				" and model.event.isInviteeExist = 'Y' and  model.event.isActive =:isActive");
		if(date != null)
		str.append("and date(model.attendedTime)=:date");
		if(committeeLevel.equalsIgnoreCase("District"))
			str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId = 11 ");
		if(committeeLevel.equalsIgnoreCase("Mandal"))
			str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId = 5 ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("eventId", eventId);
		query.setParameter("committeeRoleId", committeeRoleId);
		if(date != null)
		query.setDate("date", date);
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	
	public List<Long> getCadreIdsForAttendeesForAffliatedCommitteeRole(Long eventId,Date date,Long committeeRoleId,String committeeLevel){
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.tdpCadreId " +
				" from EventAttendee model,TdpCommitteeMember TCM,EventInvitee ei " +
				"  where model.event.parentEventId=:eventId " +
				" and ei.tdpCadreId=TCM.tdpCadreId and model.event.parentEventId = ei.event.eventId and model.tdpCadre.tdpCadreId = ei.tdpCadre.tdpCadreId " +
				" and TCM.tdpCommitteeRole.tdpRoles.tdpRolesId=:committeeRoleId " +
				" and TCM.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId != 1" +
				"  and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014" +
				" and model.event.isInviteeExist = 'Y' and  model.event.isActive =:isActive");
		if(date != null)
		str.append("and date(model.attendedTime)=:date");
		if(committeeLevel.equalsIgnoreCase("District"))
			str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId = 11 ");
		if(committeeLevel.equalsIgnoreCase("Mandal"))
			str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId = 5 ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("eventId", eventId);
		query.setParameter("committeeRoleId", committeeRoleId);
		if(date != null)
		query.setDate("date", date);
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	
	public List<Object[]> getAttendenceDetailsForCadre(List<Long> cadreIds,Long eventId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.tdpCadreId,date(model.attendedTime) " +
				" from EventAttendee model where model.tdpCadreId in (:cadreIds) and model.event.parentEventId=:eventId" +
				" and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 ");
		sb.append(" group by model.tdpCadreId,date(model.attendedTime)");
		Query query = getSession().createQuery(sb.toString());
		query.setParameterList("cadreIds", cadreIds);
		query.setParameter("eventId", eventId);
		return query.list();
	}
	////Caste wise.
   public List<Object[]>  casteWiseEventAttendeeCountsQuery(String inviteeType,Date startDate,Date endDate,List<Long> eventIds){
		
		StringBuilder sb =  new StringBuilder();	
		sb.append("select model.tdpCadre.casteState.caste.casteId,model.tdpCadre.casteState.caste.casteName,count(distinct model.tdpCadre.tdpCadreId) ");	
		
		if(inviteeType.equalsIgnoreCase("attendee")){
			sb.append(" from EventAttendee model where ");
		}
		if(inviteeType.equalsIgnoreCase("invitee")){
			sb.append(" from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId  and ");
		}
		sb.append(" model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 ");
		if(eventIds != null && eventIds.size() > 0){
			sb.append(" and model.event.eventId in  (:eventIds)");
		}
		sb.append("  and date(model.attendedTime) between :startDate and :endDate ");
		sb.append(" group by model.tdpCadre.casteState.caste.casteId " +
				  " order by count(distinct model.tdpCadre.tdpCadreId) desc ");
        
        Query query = getSession().createQuery(sb.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		if(eventIds != null && eventIds.size() > 0){
			query.setParameterList("eventIds", eventIds);
		}
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
   public List<Object[]> casteWiseEventAttendeeCountsByDateQuery(String inviteeType,Date startDate,Date endDate,List<Long> eventIds){
		
		StringBuilder sb =  new StringBuilder();
		
		sb.append(" select model.tdpCadre.casteState.caste.casteId,model.tdpCadre.casteState.caste.casteName,");
		sb.append(" date(model.attendedTime),count(distinct model.tdpCadre.tdpCadreId) ");
		
		if(inviteeType.equalsIgnoreCase("attendee")){
			sb.append(" from EventAttendee model where ");
		}
		if(inviteeType.equalsIgnoreCase("invitee")){
			sb.append(" from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId  and ");
		}
		
		sb.append(" model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 ");
		if(eventIds != null && eventIds.size() > 0){
			sb.append(" and model.event.eventId in  (:eventIds)");
		}
		sb.append("  and date(model.attendedTime) between :startDate and :endDate ");
		sb.append("  group by model.tdpCadre.casteState.caste.casteId,date(model.attendedTime)" +
				  "  order by model.tdpCadre.casteState.caste.casteName,date(model.attendedTime) ");
		
       Query query = getSession().createQuery(sb.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		if(eventIds != null && eventIds.size() > 0){
			query.setParameterList("eventIds", eventIds);
		}
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
  }
   
   //age wise
   
   public List<Object[]>  ageWiseEventAttendeeCountsQuery(String inviteeType,Date startDate,Date endDate,List<Long> eventIds){
		
		StringBuilder sb =  new StringBuilder();	
		sb.append("select ageRange.voterAgeRangeId,ageRange.ageRange,count(distinct model.tdpCadre.tdpCadreId) ");	
		
		if(inviteeType.equalsIgnoreCase("attendee")){
			sb.append(" from EventAttendee model,VoterAgeRange ageRange where model.tdpCadre.age>= ageRange.minValue and model.tdpCadre.age<= ageRange.maxValue ");
		}
		if(inviteeType.equalsIgnoreCase("invitee")){
			sb.append(" from EventAttendee model,EventInvitee model1,VoterAgeRange ageRange where model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId  and model.tdpCadre.age>= ageRange.minValue and model.tdpCadre.age<= ageRange.maxValue  ");
		}
		sb.append("  and model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 ");
		sb.append("  and ageRange.voterAgeRangeId != 7 ");
		if(eventIds != null && eventIds.size() > 0){
			sb.append(" and model.event.eventId in  (:eventIds)");
		}
		sb.append("  and date(model.attendedTime) between :startDate and :endDate ");
		sb.append(" group by ageRange.voterAgeRangeId " +
				  " order by count(distinct model.tdpCadre.tdpCadreId) desc ");
       
       Query query = getSession().createQuery(sb.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		if(eventIds != null && eventIds.size() > 0){
			query.setParameterList("eventIds", eventIds);
		}
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
   public List<Object[]> ageWiseEventAttendeeCountsByDateQuery(String inviteeType,Date startDate,Date endDate,List<Long> eventIds){
		
		StringBuilder sb =  new StringBuilder();
	
		sb.append("select ageRange.voterAgeRangeId,ageRange.ageRange,date(model.attendedTime),count(distinct model.tdpCadre.tdpCadreId) ");	
		
		if(inviteeType.equalsIgnoreCase("attendee")){
			sb.append(" from EventAttendee model,VoterAgeRange ageRange where model.tdpCadre.age>= ageRange.minValue and model.tdpCadre.age<= ageRange.maxValue ");
		}
		if(inviteeType.equalsIgnoreCase("invitee")){
			sb.append(" from EventAttendee model,EventInvitee model1,VoterAgeRange ageRange where model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId  and model.tdpCadre.age>= ageRange.minValue and model.tdpCadre.age<= ageRange.maxValue  ");
		}
		
		sb.append(" and model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 ");
		sb.append("  and ageRange.voterAgeRangeId != 7 ");
		if(eventIds != null && eventIds.size() > 0){
			sb.append(" and model.event.eventId in  (:eventIds)");
		}
		sb.append("  and date(model.attendedTime) between :startDate and :endDate ");
		sb.append("  group by ageRange.voterAgeRangeId,date(model.attendedTime)" +
				  "  order by ageRange.ageRange,date(model.attendedTime) ");
		
      Query query = getSession().createQuery(sb.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		if(eventIds != null && eventIds.size() > 0){
			query.setParameterList("eventIds", eventIds);
		}
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
 }
   //gender wise
   public List<Object[]>  genderWiseEventAttendeeCountsQuery(String inviteeType,Date startDate,Date endDate,List<Long> eventIds){
		
		StringBuilder sb =  new StringBuilder();	
		sb.append("select model.tdpCadre.gender,count(distinct model.tdpCadre.tdpCadreId) ");	
		
		if(inviteeType.equalsIgnoreCase("attendee")){
			sb.append(" from EventAttendee model where  ");
		}
		if(inviteeType.equalsIgnoreCase("invitee")){
			sb.append(" from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId  and ");
		}
		sb.append(" model.tdpCadre.gender is not null and model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 ");
		
		if(eventIds != null && eventIds.size() > 0){
			sb.append(" and model.event.eventId in  (:eventIds)");
		}
		sb.append("  and date(model.attendedTime) between :startDate and :endDate ");
		sb.append(" group by model.tdpCadre.gender " +
				  " order by count(distinct model.tdpCadre.tdpCadreId) desc ");
      
      Query query = getSession().createQuery(sb.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		if(eventIds != null && eventIds.size() > 0){
			query.setParameterList("eventIds", eventIds);
		}
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
   public List<Object[]> genderWiseEventAttendeeCountsByDateQuery(String inviteeType,Date startDate,Date endDate,List<Long> eventIds){
		
		StringBuilder sb =  new StringBuilder();
		
		sb.append(" select model.tdpCadre.gender,date(model.attendedTime),count(distinct model.tdpCadre.tdpCadreId)");
		
		if(inviteeType.equalsIgnoreCase("attendee")){
			sb.append(" from EventAttendee model where ");
		}
		if(inviteeType.equalsIgnoreCase("invitee")){
			sb.append(" from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId  and ");
		}
		
		sb.append(" model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 ");
		if(eventIds != null && eventIds.size() > 0){
			sb.append(" and model.event.eventId in  (:eventIds)");
		}
		sb.append("  and date(model.attendedTime) between :startDate and :endDate ");
		sb.append("  group by model.tdpCadre.gender,date(model.attendedTime)" );
				  
		
      Query query = getSession().createQuery(sb.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		if(eventIds != null && eventIds.size() > 0){
			query.setParameterList("eventIds", eventIds);
		}
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
 }
   //castecategory wise
   public List<Object[]>  casteCategoryWiseEventAttendeeCountsExcludingMinorities(String inviteeType,Date startDate,Date endDate,List<Long> eventIds){
		
		StringBuilder sb =  new StringBuilder();	
		sb.append("select model.tdpCadre.casteState.casteCategoryGroup.casteCategory.casteCategoryId," +
				"         model.tdpCadre.casteState.casteCategoryGroup.casteCategory.categoryName," +
				"         count(distinct model.tdpCadre.tdpCadreId) ");	
		
		if(inviteeType.equalsIgnoreCase("attendee")){
			sb.append(" from EventAttendee model where ");
		}
		if(inviteeType.equalsIgnoreCase("invitee")){
			sb.append(" from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId  and ");
		}
		sb.append(" model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 " +
				  " and  model.tdpCadre.casteState.casteStateId not in("+IConstants.NEW_MINORITY_CASTE_IDS+") ");
		if(eventIds != null && eventIds.size() > 0){
			sb.append(" and model.event.eventId in  (:eventIds)");
		}
		sb.append("  and date(model.attendedTime) between :startDate and :endDate ");
		sb.append(" group by model.tdpCadre.casteState.casteCategoryGroup.casteCategory.casteCategoryId " +
				  " order by count(distinct model.tdpCadre.tdpCadreId) desc ");
       
       Query query = getSession().createQuery(sb.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		if(eventIds != null && eventIds.size() > 0){
			query.setParameterList("eventIds", eventIds);
		}
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
   
   public Long  casteCategoryWiseEventAttendeeCountsForMinorities(String inviteeType,Date startDate,Date endDate,List<Long> eventIds){
		
		StringBuilder sb =  new StringBuilder();	
		sb.append(" select count(distinct model.tdpCadre.tdpCadreId) ");
		
		if(inviteeType.equalsIgnoreCase("attendee")){
			sb.append(" from EventAttendee model where ");
		}
		if(inviteeType.equalsIgnoreCase("invitee")){
			sb.append(" from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId  and ");
		}
		sb.append(" model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 " +
				  " and  model.tdpCadre.casteState.casteStateId  in("+IConstants.NEW_MINORITY_CASTE_IDS+") ");
		if(eventIds != null && eventIds.size() > 0){
			sb.append(" and model.event.eventId in  (:eventIds)");
		}
		sb.append("  and date(model.attendedTime) between :startDate and :endDate ");
      
        Query query = getSession().createQuery(sb.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		if(eventIds != null && eventIds.size() > 0){
			query.setParameterList("eventIds", eventIds);
		}
		query.setParameter("isActive", IConstants.TRUE);
		return (Long)query.uniqueResult();
	}
   
   public List<Object[]> casteCategoryWiseEventAttendeeCountsByDateExcludingMinorities(String inviteeType,Date startDate,Date endDate,List<Long> eventIds){
		
		StringBuilder sb =  new StringBuilder();
		
		sb.append(" select model.tdpCadre.casteState.casteCategoryGroup.casteCategory.casteCategoryId,model.tdpCadre.casteState.casteCategoryGroup.casteCategory.categoryName,");
		sb.append("        date(model.attendedTime),count(distinct model.tdpCadre.tdpCadreId) ");
		
		if(inviteeType.equalsIgnoreCase("attendee")){
			sb.append(" from EventAttendee model where ");
		}
		if(inviteeType.equalsIgnoreCase("invitee")){
			sb.append(" from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId  and ");
		}
		
		sb.append(" model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 " +
				 "  and model.tdpCadre.casteState.casteStateId not in("+IConstants.NEW_MINORITY_CASTE_IDS+")");
		if(eventIds != null && eventIds.size() > 0){
			sb.append(" and model.event.eventId in  (:eventIds)");
		}
		sb.append("  and date(model.attendedTime) between :startDate and :endDate ");
		sb.append("  group by model.tdpCadre.casteState.casteCategoryGroup.casteCategory.casteCategoryId,date(model.attendedTime)");
				  
		
      Query query = getSession().createQuery(sb.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		if(eventIds != null && eventIds.size() > 0){
			query.setParameterList("eventIds", eventIds);
		}
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
   }
   public List<Object[]> casteCategoryWiseEventAttendeeCountsByDateForMinorities(String inviteeType,Date startDate,Date endDate,List<Long> eventIds){
		
		StringBuilder sb =  new StringBuilder();
		
		sb.append(" select date(model.attendedTime),count(distinct model.tdpCadre.tdpCadreId) ");
		
		if(inviteeType.equalsIgnoreCase("attendee")){
			sb.append(" from EventAttendee model where ");
		}
		if(inviteeType.equalsIgnoreCase("invitee")){
			sb.append(" from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId  and ");
		}
		
		sb.append(" model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 " +
				 "  and model.tdpCadre.casteState.casteStateId  in("+IConstants.NEW_MINORITY_CASTE_IDS+")");
		if(eventIds != null && eventIds.size() > 0){
			sb.append(" and model.event.eventId in  (:eventIds)");
		}
		sb.append("  and date(model.attendedTime) between :startDate and :endDate ");
		sb.append("  group by date(model.attendedTime)");
				  
		
    Query query = getSession().createQuery(sb.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		if(eventIds != null && eventIds.size() > 0){
			query.setParameterList("eventIds", eventIds);
		}
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
 }
   
   public List<Object[]> getLocationWiseAttendeesCount(String locationType, Long locationId, Long eventId , String inviteeType,String searchType,Date startDate, Date endDate,String mandalData){
	   StringBuilder sbS =  new StringBuilder();
		StringBuilder sbM =  new StringBuilder();
		StringBuilder sbE =  new StringBuilder();
		
		sbS.append("select ");
		
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
			if(searchType.equalsIgnoreCase(IConstants.DISTRICT)){
				sbS.append("  model.tdpCadre.userAddress.constituency.district.districtId,model.tdpCadre.userAddress.constituency.district.districtName ");
			
				sbE.append(" and model.tdpCadre.userAddress.constituency.district.districtId =:locationId group by model.tdpCadre.userAddress.constituency.district.districtId" +
							   "  order by count(distinct model.tdpCadre.tdpCadreId) desc");
					
			}else if(searchType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				sbS.append("  model.tdpCadre.userAddress.constituency.constituencyId,model.tdpCadre.userAddress.constituency.name ");
				
				sbE.append(" and model.tdpCadre.userAddress.constituency.district.districtId =:locationId group by model.tdpCadre.userAddress.constituency.constituencyId " +
							   "  order by count(distinct model.tdpCadre.tdpCadreId) desc ");
			}
		}
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			if(searchType.equalsIgnoreCase(IConstants.MANDAL) && mandalData != null && mandalData.equalsIgnoreCase(IConstants.MANDAL)){
			sbS.append("  model.tdpCadre.userAddress.tehsil.tehsilId,model.tdpCadre.userAddress.tehsil.tehsilName ");
			
			sbE.append(" and model.tdpCadre.userAddress.constituency.constituencyId =:locationId and model.tdpCadre.userAddress.localElectionBody is null group by model.tdpCadre.userAddress.tehsil.tehsilId " +
						   "  order by count(distinct model.tdpCadre.tdpCadreId) desc ");
			}else if(searchType.equalsIgnoreCase(IConstants.MANDAL) && mandalData != null && mandalData.equalsIgnoreCase(IConstants.MUNCPAL_CORP)){
				sbS.append(" model.tdpCadre.userAddress.localElectionBody.localElectionBodyId,model.tdpCadre.userAddress.localElectionBody.name ");
				
				sbE.append(" and model.tdpCadre.userAddress.constituency.constituencyId =:locationId group by model.tdpCadre.userAddress.localElectionBody.localElectionBodyId " +
							   "  order by count(distinct model.tdpCadre.tdpCadreId) desc ");
			}
		}else if(locationType.equalsIgnoreCase(IConstants.MUNCPAL_CORP) && searchType.equalsIgnoreCase(IConstants.WARD)){
			sbS.append(" model.tdpCadre.userAddress.ward.constituencyId,model.tdpCadre.userAddress.ward.name ");
			
			sbE.append(" and model.tdpCadre.userAddress.localElectionBody.localElectionBodyId =:locationId group by model.tdpCadre.userAddress.ward.constituencyId " +
						   "  order by count(distinct model.tdpCadre.tdpCadreId) desc ");	
		}else if(locationType.equalsIgnoreCase(IConstants.MANDAL) && searchType.equalsIgnoreCase(IConstants.PANCHAYAT)){
			sbS.append("  model.tdpCadre.userAddress.panchayat.panchayatId,model.tdpCadre.userAddress.panchayat.panchayatName ");
			
			sbE.append(" and model.tdpCadre.userAddress.tehsil.tehsilId =:locationId and model.tdpCadre.userAddress.localElectionBody is null group by model.tdpCadre.userAddress.panchayat.panchayatId " +
						   "  order by count(distinct model.tdpCadre.tdpCadreId) desc ");		
		}
		sbS.append(" ,count(distinct model.tdpCadre.tdpCadreId) ");
		
		if(inviteeType.equalsIgnoreCase("attendee")){
			sbM.append(" from EventAttendee model where ");
		}
		if(inviteeType.equalsIgnoreCase("invitee")){
			sbM.append(" from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId  and ");
		}
		
		sbM.append(" model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 ");
		if(eventId != null && eventId.longValue() > 0l){
			sbM.append(" and model.event.parentEventId =:eventId ");
		}
		if(startDate != null &&  endDate != null)
		sbM.append("  and date(model.attendedTime) between :startDate and :endDate ");
	
		
		StringBuilder sq = new StringBuilder();
       sq.append(sbS.toString()).append(sbM.toString()).append(sbE.toString());
       
       Query query = getSession().createQuery(sq.toString());
       if(startDate != null &&  endDate != null){
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
       }
		if(eventId != null && eventId.longValue() > 0l){
			query.setParameter("eventId", eventId);
		}
		if(locationId != null && locationId.longValue() > 0l){
			query.setParameter("locationId", locationId);
		}
		query.setParameter("isActive", IConstants.TRUE);
		return query.list(); 
   }
   
   public List<Object[]> cadreLocationWiseEventAttendeeCountsByDateQuery(String locationType,String inviteeType,Date startDate,Date endDate,Long eventId,Long locationId,String searchType,String mandalType){
		
		StringBuilder sbS =  new StringBuilder();
		StringBuilder sbM =  new StringBuilder();
		StringBuilder sbE =  new StringBuilder();
		
		sbS.append("select ");
		
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
				if(searchType.equalsIgnoreCase(IConstants.DISTRICT)){
					sbS.append("  model.tdpCadre.userAddress.constituency.district.districtId,model.tdpCadre.userAddress.constituency.district.districtName ");
					
					sbE.append(" and model.tdpCadre.userAddress.constituency.district.districtId =:locationId group by model.tdpCadre.userAddress.constituency.district.districtId,date(model.attendedTime) " +
							   "  order by model.tdpCadre.userAddress.constituency.district.districtId,date(model.attendedTime)");
				}else if(searchType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
					sbS.append("  model.tdpCadre.userAddress.constituency.constituencyId,model.tdpCadre.userAddress.constituency.name ");
					
					sbE.append(" and model.tdpCadre.userAddress.constituency.district.districtId =:locationId group by model.tdpCadre.userAddress.constituency.constituencyId,date(model.attendedTime) " +
							   "  order by model.tdpCadre.userAddress.constituency.name,date(model.attendedTime) ");
				}
		}
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				if(searchType.equalsIgnoreCase(IConstants.MANDAL) && mandalType != null && mandalType.equalsIgnoreCase(IConstants.MANDAL)){
					sbS.append("   model.tdpCadre.userAddress.tehsil.tehsilId,model.tdpCadre.userAddress.tehsil.tehsilName ");
					
					sbE.append(" and model.tdpCadre.userAddress.constituency.constituencyId =:locationId and model.tdpCadre.userAddress.localElectionBody is null group by  model.tdpCadre.userAddress.tehsil.tehsilId,date(model.attendedTime) " +
								   "  order by model.tdpCadre.userAddress.tehsil.tehsilName,date(model.attendedTime) ");
				}else if(searchType.equalsIgnoreCase(IConstants.MANDAL) && mandalType != null && mandalType.equalsIgnoreCase(IConstants.MUNCPAL_CORP)){
					sbS.append("  model.tdpCadre.userAddress.localElectionBody.localElectionBodyId,model.tdpCadre.userAddress.localElectionBody.name ");
					
					sbE.append(" and model.tdpCadre.userAddress.constituency.constituencyId =:locationId group by model.tdpCadre.userAddress.localElectionBody.localElectionBodyId,date(model.attendedTime) " +
								   "  order by model.tdpCadre.userAddress.localElectionBody.name,date(model.attendedTime) ");
				}
		}
		else if(locationType.equalsIgnoreCase(IConstants.MANDAL) && searchType.equalsIgnoreCase(IConstants.PANCHAYAT))
		{
				sbS.append("  model.tdpCadre.userAddress.panchayat.panchayatId,model.tdpCadre.userAddress.panchayat.panchayatName ");
				
				sbE.append(" and model.tdpCadre.userAddress.tehsil.tehsilId =:locationId and model.tdpCadre.userAddress.localElectionBody is null group by model.tdpCadre.userAddress.panchayat.panchayatId,date(model.attendedTime) " +
							   "  order by model.tdpCadre.userAddress.panchayat.panchayatName,date(model.attendedTime) ");
		}
		sbS.append(" ,date(model.attendedTime),count(distinct model.tdpCadre.tdpCadreId) ");
		
		if(inviteeType.equalsIgnoreCase("attendee"))
		{
			sbM.append(" from EventAttendee model where ");
		}
		if(inviteeType.equalsIgnoreCase("invitee"))
		{
			sbM.append(" from EventAttendee model,EventInvitee model1 where model.event.isInviteeExist = 'Y' and model.event.parentEventId = model1.event.eventId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId  and ");
		}
		
		sbM.append(" model.event.isActive =:isActive and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 ");
		if(eventId != null && eventId.longValue() > 0l){
			sbM.append(" and model.event.parentEventId =:eventId ");
		}
		if(startDate != null &&  endDate != null)
		sbM.append("  and date(model.attendedTime) between :startDate and :endDate ");
		
		
		StringBuilder sq = new StringBuilder();
       sq.append(sbS.toString()).append(sbM.toString()).append(sbE.toString());
       
       Query query = getSession().createQuery(sq.toString());
       if(startDate != null &&  endDate != null){
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
       }
		if(eventId != null && eventId.longValue() > 0){
			query.setParameter("eventId", eventId);
		}
		if(locationId != null && locationId.longValue() > 0l){
			query.setParameter("locationId", locationId);
		}
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
  }
   public Long getUniqueVisitorsAttendedCountForCadre(Long eventId,Date startDate,Date endDate)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select count(distinct model.tdpCadre.tdpCadreId) " +
				"   from EventAttendee model " +
				"   where model.event.parentEventId =:eventId and " +
				"         model.event.isActive =:isActive and " +
				"         model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 and " +
				"         model.event.isVisible =:isVisible ");
		
		if(startDate != null && endDate != null){
			str.append(" and date(model.attendedTime) >= :startDate and date(model.attendedTime) <= :endDate "); 
		}
		Query query = getSession().createQuery(str.toString());
		if((startDate != null && endDate != null)){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);	
		}
		
		query.setParameter("eventId", eventId);
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameter("isVisible", IConstants.IS_VISIBLE);
		return (Long)query.uniqueResult();
	}
   public List<Object[]> getEventIdAndPresentedCadreIdList(Date fromDate, Date toDate){
	   StringBuilder str = new StringBuilder();
	   str.append(" select distinct model.eventId, date(model.attendedTime), model.tdpCadreId,po.officeName, min(model.attendedTime) " +
	   			  " from " +
	   			  " EventAttendee model, PartyOffice po, Employee emp" +    
	   			  " where " +
	   			  " date(model.attendedTime) between :fromDate and :toDate and " +
	   			  " model.event.eventId = po.event.eventId and " +
	   			  " model.tdpCadre.tdpCadreId = emp.tdpCadre.tdpCadreId and emp.isDeleted = 'N' and emp.isActive = 'Y' " +    
	   			  " group by model.tdpCadre.tdpCadreId " +
	   			  " order by model.event.eventId");
	   Query query = getSession().createQuery(str.toString());
	   query.setDate("fromDate", fromDate);
	   query.setDate("toDate", toDate);
	   return query.list();
   }
   public List<Object[]> getEventAttendedCountByEvent(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> eventIds){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.event.parentEventId,count(distinct model.tdpCadre.tdpCadreId) ");
		
		queryStr.append(" from EventAttendee model where  model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014  ");
		if(eventIds != null && eventIds.size() >0){
			queryStr.append(" and model.event.parentEventId in (:eventIds) " );	
		}
		 if(stateId != null && stateId.longValue() > 0){
				if(stateId.longValue()==1l){
				  queryStr.append(" and model.tdpCadre.userAddress.district.districtId > 10 and  model.tdpCadre.userAddress.state.stateId = 1 ");	
				}else if(stateId.longValue()==36l){
					queryStr.append(" and model.tdpCadre.userAddress.district.districtId < 11 ");
				}
		 }
		  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		    queryStr.append(" and model.tdpCadre.userAddress.state.stateId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		        queryStr.append(" and model.tdpCadre.userAddress.district.districtId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" and model.tdpCadre.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		     queryStr.append(" and model.tdpCadre.userAddress.constituency.constituencyId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		        queryStr.append(" and model.tdpCadre.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		        queryStr.append(" and model.tdpCadre.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		        queryStr.append(" and model.tdpCadre.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		        queryStr.append(" and model.tdpCadre.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		 }
		  queryStr.append(" group by model.event.parentEventId ");
		 
		  Query query = getSession().createQuery(queryStr.toString());
		
		if(eventIds != null && eventIds.size() >0){
			query.setParameterList("eventIds", eventIds);	
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		
		return query.list();
	}

   public List<Object[]> getEventInviteeAttendedCountByEvent(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> eventIds){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model1.event.parentEventId,count(distinct model.tdpCadre.tdpCadreId) ");
		
		queryStr.append(" from EventInvitee model,EventAttendee model1,Event event  where  model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014  ");
		queryStr.append(" and model1.event.eventId = event.eventId and model.event.eventId=event.parentEventId  and model1.tdpCadre.tdpCadreId = model.tdpCadre.tdpCadreId  ");
		if(eventIds != null && eventIds.size() >0){
			queryStr.append(" and model1.event.parentEventId in (:eventIds) " );	
		}
		 if(stateId != null && stateId.longValue() > 0){
			/*	if(stateId.longValue()==1l){
					queryStr.append(" and model.tdpCadre.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");	
				}else if(stateId.longValue()==36l){
					queryStr.append(" and model.tdpCadre.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
				}*/
				if(stateId.longValue()==1l){
				queryStr.append(" and model.tdpCadre.userAddress.district.districtId > 10 and  model.tdpCadre.userAddress.state.stateId = 1 ");	
				}else if(stateId.longValue()==36l){
					queryStr.append(" and model.tdpCadre.userAddress.district.districtId < 11 ");
				}
		 }
		
		  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		    queryStr.append(" and model.tdpCadre.userAddress.state.stateId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		        queryStr.append(" and model.tdpCadre.userAddress.district.districtId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" and model.tdpCadre.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		     queryStr.append(" and model.tdpCadre.userAddress.constituency.constituencyId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		        queryStr.append(" and model.tdpCadre.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		        queryStr.append(" and model.tdpCadre.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		        queryStr.append(" and model.tdpCadre.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		        queryStr.append(" and model.tdpCadre.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		 }
		  queryStr.append(" group by model1.event.parentEventId ");
		 
		  Query query = getSession().createQuery(queryStr.toString());
	 
		  if(eventIds != null && eventIds.size() >0){
			query.setParameterList("eventIds", eventIds);	
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		
		return query.list();
	}
   public List<Object[]> getEventAttendedCntByEventAndLocationBasedOnUserType(Long userType,Long stateId,List<Long> eventIds,Long userAccessLevelId,List<Long> userAccessLevelValues,String levelType){
		
		  StringBuilder queryStr = new StringBuilder();
		
		  queryStr.append(" select model.event.parentEventId,");
		  
		  if(userType != null && userType.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userType.longValue()==IConstants.STATE_TYPE_USER_ID || userType.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
		    queryStr.append("model.tdpCadre.userAddress.district.districtId,");
		    queryStr.append("model.tdpCadre.userAddress.district.districtName,"); 
	      }else if(userType != null && userType.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
		   || userType.longValue()==IConstants.MP_USER_TYPE_ID){
		 	 queryStr.append("model.tdpCadre.userAddress.constituency.constituencyId,");
	    	 queryStr.append("model.tdpCadre.userAddress.constituency.name,"); 
	     }else if(userType != null && userType.longValue()==IConstants.MLA_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
		     if(levelType != null && levelType.equalsIgnoreCase("tehsil")){
		     queryStr.append(" model.tdpCadre.userAddress.tehsil.tehsilId,");
		     queryStr.append(" model.tdpCadre.userAddress.tehsil.tehsilName,");
		     }else if(levelType != null && levelType.equalsIgnoreCase("townDivision")){
		     queryStr.append(" model.tdpCadre.userAddress.localElectionBody.localElectionBodyId,");
		     queryStr.append(" model.tdpCadre.userAddress.localElectionBody.name,");
		     }
	    }
		queryStr.append(" count(distinct model.tdpCadre.tdpCadreId) ");
		
		queryStr.append(" from EventAttendee model where  model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014  ");
	  	
		if(eventIds != null && eventIds.size() >0){
			queryStr.append(" and model.event.parentEventId in (:eventIds) " );	
		}
		 if(stateId != null && stateId.longValue() > 0){
				if(stateId.longValue()==1l){
					queryStr.append(" and model.tdpCadre.userAddress.district.districtId > 10 and  model.tdpCadre.userAddress.state.stateId = 1 ");	
				}else if(stateId.longValue()==36l){
					queryStr.append(" and model.tdpCadre.userAddress.district.districtId < 11 ");
				}
		 }
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		    queryStr.append(" and model.tdpCadre.userAddress.state.stateId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		        queryStr.append(" and model.tdpCadre.userAddress.district.districtId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" and model.tdpCadre.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		     queryStr.append(" and model.tdpCadre.userAddress.constituency.constituencyId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		        queryStr.append(" and model.tdpCadre.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		        queryStr.append(" and model.tdpCadre.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		        queryStr.append(" and model.tdpCadre.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		        queryStr.append(" and model.tdpCadre.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		 }
		  queryStr.append(" group by model.event.parentEventId"); 
		if(userType != null && userType.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userType.longValue()==IConstants.STATE_TYPE_USER_ID || userType.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
		     queryStr.append(" ,model.tdpCadre.userAddress.district.districtId");
	    }else if(userType != null && userType.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
		   || userType.longValue()==IConstants.MP_USER_TYPE_ID){
		 	 queryStr.append(" ,model.tdpCadre.userAddress.constituency.constituencyId");
	   }else if(userType != null && userType.longValue()==IConstants.MLA_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
		     if(levelType != null && levelType.equalsIgnoreCase("tehsil")){
		     queryStr.append(" ,model.tdpCadre.userAddress.tehsil.tehsilId");
		     }else if(levelType != null && levelType.equalsIgnoreCase("townDivision")){
		     queryStr.append(" ,model.tdpCadre.userAddress.localElectionBody.localElectionBodyId");
		     }
	   }
	   queryStr.append(" order by model.event.parentEventId ");
	   Query query = getSession().createQuery(queryStr.toString());
	   if(eventIds != null && eventIds.size() >0){
			query.setParameterList("eventIds", eventIds);	
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		  query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		return query.list();
	}
   public List<Object[]> getEventInviteeAttendedCntByEventAndLocationBasedOnUserType(Long userType,Long stateId,List<Long> eventIds,Long userAccessLevelId,List<Long> userAccessLevelValues,String levelType){
		
		  StringBuilder queryStr = new StringBuilder();
		
		  queryStr.append(" select model1.event.parentEventId,");
		  
		  if(userType != null && userType.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userType.longValue()==IConstants.STATE_TYPE_USER_ID || userType.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
		    queryStr.append("model.tdpCadre.userAddress.district.districtId,");
		    queryStr.append("model.tdpCadre.userAddress.district.districtName,"); 
	      }else if(userType != null && userType.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
		   || userType.longValue()==IConstants.MP_USER_TYPE_ID){
		 	 queryStr.append("model.tdpCadre.userAddress.constituency.constituencyId,");
	    	 queryStr.append("model.tdpCadre.userAddress.constituency.name,"); 
	     }else if(userType != null && userType.longValue()==IConstants.MLA_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
		     if(levelType != null && levelType.equalsIgnoreCase("tehsil")){
		     queryStr.append(" model.tdpCadre.userAddress.tehsil.tehsilId,");
		     queryStr.append(" model.tdpCadre.userAddress.tehsil.tehsilName,");
		     }else if(levelType != null && levelType.equalsIgnoreCase("townDivision")){
		     queryStr.append(" model.tdpCadre.userAddress.localElectionBody.localElectionBodyId,");
		     queryStr.append(" model.tdpCadre.userAddress.localElectionBody.name,");
		     }
	    }
		queryStr.append(" count(distinct model.tdpCadre.tdpCadreId) ");
		
		queryStr.append(" from EventInvitee model,EventAttendee model1,Event event  where  model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014  ");
		queryStr.append(" and model1.event.eventId = event.eventId and model.event.eventId=event.parentEventId  and model1.tdpCadre.tdpCadreId = model.tdpCadre.tdpCadreId  ");
		
		if(eventIds != null && eventIds.size() >0){
			queryStr.append(" and model1.event.parentEventId in (:eventIds) " );	
		}
		 if(stateId != null && stateId.longValue() > 0){
				if(stateId.longValue()==1l){
					queryStr.append(" and model.tdpCadre.userAddress.district.districtId > 10 and  model.tdpCadre.userAddress.state.stateId = 1 ");	
				}else if(stateId.longValue()==36l){
					queryStr.append(" and model.tdpCadre.userAddress.district.districtId < 11 ");
				}
		 }
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		       queryStr.append(" and model.tdpCadre.userAddress.state.stateId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		        queryStr.append(" and model.tdpCadre.userAddress.district.districtId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" and model.tdpCadre.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		     queryStr.append(" and model.tdpCadre.userAddress.constituency.constituencyId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		        queryStr.append(" and model.tdpCadre.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		        queryStr.append(" and model.tdpCadre.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		        queryStr.append(" and model.tdpCadre.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		        queryStr.append(" and model.tdpCadre.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		 }
		  queryStr.append(" group by model1.event.parentEventId"); 
		if(userType != null && userType.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userType.longValue()==IConstants.STATE_TYPE_USER_ID || userType.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
		          queryStr.append(" ,model.tdpCadre.userAddress.district.districtId");
	    }else if(userType != null && userType.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
		   || userType.longValue()==IConstants.MP_USER_TYPE_ID){
		 	      queryStr.append(" ,model.tdpCadre.userAddress.constituency.constituencyId");
	   }else if(userType != null && userType.longValue()==IConstants.MLA_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
		     if(levelType != null && levelType.equalsIgnoreCase("tehsil")){
		     queryStr.append(" ,model.tdpCadre.userAddress.tehsil.tehsilId");
		     }else if(levelType != null && levelType.equalsIgnoreCase("townDivision")){
		     queryStr.append(" ,model.tdpCadre.userAddress.localElectionBody.localElectionBodyId");
		     }
	   }
	   queryStr.append(" order by model1.event.parentEventId ");
	   Query query = getSession().createQuery(queryStr.toString());
	   if(eventIds != null && eventIds.size() >0){
			query.setParameterList("eventIds", eventIds);	
		}
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		  query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		return query.list();
	}
   public List<Object[]> getEventAttendedCountLocationWise(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> eventIds){
		
		StringBuilder queryStr = new StringBuilder();
		
		     queryStr.append("select");
		     
		    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		       queryStr.append("  UA.state_id as stateId,");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			     queryStr.append(" UA.district_id as districtId,");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			     queryStr.append(" UA.parliament_constituency_id as parliamentConstituencyId,");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			     queryStr.append(" UA.constituency_id as assemblyId,");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			        queryStr.append(" UA.tehsil_id as tehsilId,");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			        queryStr.append(" UA.local_election_body as localElectionBoydId,"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			        queryStr.append(" UA.panchayat_id as panchayatId,"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			        queryStr.append(" UA.ward as wardId,"); 
			 }
		  
		  queryStr.append(" COUNT(DISTINCT CONCAT(E.parent_event_id,'-',TC.tdp_cadre_id)) as count ");
		
		  queryStr.append(" FROM event_attendee EA," +
		  		          " event E,tdp_cadre TC," +
		  		          " user_address UA "+
		                  " WHERE EA.event_id = E.event_id AND "+
			              " EA.tdp_cadre_id = TC.tdp_cadre_id AND " +
						  " TC.address_id = UA.user_address_id AND "+
						  " TC.is_deleted = 'N' AND "+
						  " TC.enrollment_year = 2014 ");
			if(eventIds != null && eventIds.size() >0){
				queryStr.append(" AND E.parent_event_id in(:eventIds)" );	
			}
		    if(stateId != null && stateId.longValue() > 0){
					if(stateId.longValue()==1l){
						//queryStr.append(" and UA.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");	
						queryStr.append(" and UA.district_id > 10 and UA.state_id = 1 ");
					}else if(stateId.longValue()==36l){
						//queryStr.append(" and UA.district_id in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
                        queryStr.append(" and UA.district_id < 11 ");
					}
			 }
		    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		       queryStr.append("  group by UA.state_id");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			     queryStr.append(" group by UA.district_id");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			     queryStr.append(" group by UA.parliament_constituency_id");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			     queryStr.append("  group by UA.constituency_id");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			        queryStr.append(" group by UA.tehsil_id");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			        queryStr.append(" group by UA.local_election_body"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			        queryStr.append(" group by UA.panchayat_id"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			        queryStr.append(" group by UA.ward"); 
			 }
	        Session session = getSession();
		     SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
		    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			 sqlQuery.addScalar("stateId",Hibernate.LONG); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
				 sqlQuery.addScalar("districtId",Hibernate.LONG);
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
				 sqlQuery.addScalar("parliamentConstituencyId",Hibernate.LONG); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
				 sqlQuery.addScalar("assemblyId",Hibernate.LONG);
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
				 sqlQuery.addScalar("tehsilId",Hibernate.LONG);  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
				 sqlQuery.addScalar("localElectionBoydId",Hibernate.LONG);
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
				 sqlQuery.addScalar("panchayatId",Hibernate.LONG);
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
				 sqlQuery.addScalar("wardId",Hibernate.LONG);
			 }
			   sqlQuery.addScalar("count",Hibernate.LONG);
			if(eventIds != null && eventIds.size() >0){
				sqlQuery.setParameterList("eventIds", eventIds);	
			}
		return sqlQuery.list();
	}
   public List<Object[]> getEventInviteeAttendedCountLocationWise(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> eventIds){
		
		StringBuilder queryStr = new StringBuilder();
		
		     queryStr.append("select");
		     
		     if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		        queryStr.append("  UA.state_id as stateId,");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			     queryStr.append(" UA.district_id as districtId,");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			     queryStr.append(" UA.parliament_constituency_id as parliamentConstituencyId,");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			     queryStr.append("  UA.constituency_id as assemblyId,");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			        queryStr.append(" UA.tehsil_id as tehsilId,");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			        queryStr.append(" UA.local_election_body as localElectionBoydId,"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			        queryStr.append(" UA.panchayat_id as panchayatId,"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			        queryStr.append(" UA.ward as wardId,"); 
			 }
		  
		     queryStr.append(" COUNT(DISTINCT CONCAT(E.parent_event_id,'-',TC.tdp_cadre_id)) as count ");
		
		     queryStr.append(" FROM event_attendee EA,event_invitee EI ," +
		  		             " event E,tdp_cadre TC," +
		  		             " user_address UA "+
			                 " WHERE EA.event_id = E.event_id AND "+
				             " EA.tdp_cadre_id = EI.tdp_cadre_id AND " +
				             " E.parent_event_id=EI.event_id AND " +
				             " EA.tdp_cadre_id =TC.tdp_cadre_id AND " +
				             " TC.address_id = UA.user_address_id AND "+
							 " TC.is_deleted = 'N' AND "+
							 " TC.enrollment_year = 2014 ");
			if(eventIds != null && eventIds.size() >0){
				queryStr.append(" AND E.parent_event_id in(:eventIds)" );	
			}
		 	 if(stateId != null && stateId.longValue() > 0){
					if(stateId.longValue()==1l){
						queryStr.append(" and UA.district_id > 10 and UA.state_id = 1 ");
					}else if(stateId.longValue()==36l){
						queryStr.append(" and UA.district_id < 11 ");
					}
			 }
		    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		         queryStr.append(" group by UA.state_id");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			     queryStr.append(" group by UA.district_id");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			     queryStr.append(" group by UA.parliament_constituency_id");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			     queryStr.append(" group by UA.constituency_id");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			     queryStr.append(" group by UA.tehsil_id");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			     queryStr.append(" group by UA.local_election_body"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			     queryStr.append(" group by UA.panchayat_id"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			     queryStr.append(" group by UA.ward"); 
			 }
	        Session session = getSession();
		   SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
		    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			     sqlQuery.addScalar("stateId",Hibernate.LONG); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
				 sqlQuery.addScalar("districtId",Hibernate.LONG);
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
				 sqlQuery.addScalar("parliamentConstituencyId",Hibernate.LONG); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
				 sqlQuery.addScalar("assemblyId",Hibernate.LONG);
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
				 sqlQuery.addScalar("tehsilId",Hibernate.LONG);  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
				 sqlQuery.addScalar("localElectionBoydId",Hibernate.LONG);
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
				 sqlQuery.addScalar("panchayatId",Hibernate.LONG);
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
				 sqlQuery.addScalar("wardId",Hibernate.LONG);
			 }
			    sqlQuery.addScalar("count",Hibernate.LONG);
			if(eventIds != null && eventIds.size() >0){
				sqlQuery.setParameterList("eventIds", eventIds);	
			}
		return sqlQuery.list();
	}
   public List<Object[]> getLocationWiseEventAttendedCntBasedOnUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> eventIds,String locationType){
		
       StringBuilder queryStr = new StringBuilder();

        queryStr.append("select");

            if(locationType != null && locationType.equalsIgnoreCase("District")){
	         queryStr.append(" d.district_id as districtId,"); //1
	         queryStr.append(" d.district_name as districtName,"); //2
	        }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
	         queryStr.append(" c.constituency_id as assemblyId,"); //3
	  	     queryStr.append(" c.name as assemblyName,"); //4
	  	    }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
	         queryStr.append(" t.tehsil_id as tehsilId,");
	         queryStr.append(" t.tehsil_name as tehsilName,");
	        }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
	        queryStr.append(" p.panchayat_id as panchayatId,");
	        queryStr.append(" p.panchayat_name as panchayatName,");
	        }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
  	        queryStr.append(" c.constituency_id as wardId,");
  	        queryStr.append(" c.name as wardName,");
  	        }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
  	        queryStr.append(" leb.local_election_body_id as localElectionBoydId,");
  	        queryStr.append(" leb.name as localElectionBoydName,");
  	       }
	        
       
           queryStr.append(" COUNT(DISTINCT CONCAT(E.parent_event_id,'-',TC.tdp_cadre_id)) as count ");

           queryStr.append(" FROM event_attendee EA," +
		                   " event E,tdp_cadre TC," +
		                   " user_address UA ");

           if(locationType != null && locationType.equalsIgnoreCase("District")){
	         queryStr.append(",district d "); 
	        }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
	         queryStr.append(",constituency c "); 
	  	    }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
	         queryStr.append(",tehsil t ");
	        }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
	        queryStr.append(",panchayat p ");
	        }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
 	        queryStr.append(",constituency c ");
 	        }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
 	        queryStr.append(",local_election_body leb ");
 	       }
		     
		     queryStr.append(" WHERE EA.event_id = E.event_id AND "+
				              " EA.tdp_cadre_id = TC.tdp_cadre_id AND " +
							  " TC.address_id = UA.user_address_id AND "+
							  " TC.is_deleted = 'N' AND "+
							  " TC.enrollment_year = 2014 ");

		    if(eventIds != null && eventIds.size() >0){
			 queryStr.append(" AND E.parent_event_id in(:eventIds)" );	
		    }
		   if(locationType != null && locationType.equalsIgnoreCase("District")){
		      queryStr.append(" and UA.district_id=d.district_id");
		   }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
		      queryStr.append("  and UA.constituency_id=c.constituency_id ");
		   }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
		     	queryStr.append(" and UA.tehsil_id=t.tehsil_id ");  
		   }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
		 	   queryStr.append(" and UA.panchayat_id=p.panchayat_id "); 
		   }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
		 	   queryStr.append(" and UA.ward=c.constituency_id"); 
		   }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
		 	   queryStr.append(" and UA.local_election_body=leb.local_election_body_id "); 
		   }
		    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			    queryStr.append(" and UA.state_id in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			        queryStr.append(" and UA.district_id in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			     queryStr.append(" and UA.parliament_constituency_id in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			     queryStr.append(" and UA.constituency_id in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			        queryStr.append(" and UA.tehsil_id in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			        queryStr.append(" and UA.local_election_body in (:userAccessLevelValues)"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			        queryStr.append(" and UA.panchayat_id in (:userAccessLevelValues)"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			        queryStr.append(" and UA.ward in (:userAccessLevelValues)"); 
			 }
		    if(stateId != null && stateId.longValue() > 0){
				if(stateId.longValue()==1l){
					queryStr.append(" and UA.district_id > 10 and UA.state_id = 1 ");
				}else if(stateId.longValue()==36l){
					queryStr.append(" and UA.district_id < 11 ");
				}
		    }
		    if(locationType != null && locationType.equalsIgnoreCase("District")){
		     queryStr.append(" group by d.district_id "); 
		    }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
		     queryStr.append(" group by c.constituency_id "); 
			}else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
		     queryStr.append(" group by t.tehsil_id ");
		    }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
		    queryStr.append(" group by p.panchayat_id ");
		    }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
		       queryStr.append(" group by c.constituency_id ");
		    }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
		       queryStr.append(" group by leb.local_election_body_id");
		    }
		
		Session session = getSession();
		SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
		
		if(locationType != null && locationType.equalsIgnoreCase("District")){
			 sqlQuery.addScalar("districtId",Hibernate.LONG); 
			 sqlQuery.addScalar("districtName",Hibernate.STRING); 
		   }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
		    sqlQuery.addScalar("assemblyId",Hibernate.LONG); 
		    sqlQuery.addScalar("assemblyName",Hibernate.STRING); 
		   }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
		    sqlQuery.addScalar("tehsilId",Hibernate.LONG); 
			 sqlQuery.addScalar("tehsilName",Hibernate.STRING); 
		   }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
		    sqlQuery.addScalar("panchayatId",Hibernate.LONG); 
			 sqlQuery.addScalar("panchayatName",Hibernate.STRING); 
		   }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
			 sqlQuery.addScalar("wardId",Hibernate.LONG); 
			 sqlQuery.addScalar("wardName",Hibernate.STRING); 
		    } else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
			  sqlQuery.addScalar("localElectionBoydId",Hibernate.LONG); 
			  sqlQuery.addScalar("localElectionBoydName",Hibernate.STRING); 
		  }
		    sqlQuery.addScalar("count",Hibernate.LONG);
			
		 if(eventIds != null && eventIds.size() >0){
		 	sqlQuery.setParameterList("eventIds", eventIds);	
		 }
		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			sqlQuery.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		 return sqlQuery.list();
}
	public List<Object[]> getLocationWiseEventInviteeAttendedCntBasedOnUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> eventIds,String locationType){
				
		       StringBuilder queryStr = new StringBuilder();
		
		        queryStr.append("select");
		
		          if(locationType != null && locationType.equalsIgnoreCase("District")){
			         queryStr.append(" d.district_id as districtId,"); //1
			         queryStr.append(" d.district_name as districtName,"); //2
			        }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
			         queryStr.append(" c.constituency_id as assemblyId,"); //3
			  	     queryStr.append(" c.name as assemblyName,"); //4
			  	    }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
			         queryStr.append(" t.tehsil_id as tehsilId,");
			         queryStr.append(" t.tehsil_name as tehsilName,");
			        }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
			        queryStr.append(" p.panchayat_id as panchayatId,");
			        queryStr.append(" p.panchayat_name as panchayatName,");
			        }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
		  	        queryStr.append(" c.constituency_id as wardId,");
		  	        queryStr.append(" c.name as wardName,");
		  	       }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
		  	        queryStr.append(" leb.local_election_body_id as localElectionBoydId,");
		  	        queryStr.append(" leb.name as localElectionBoydName,");
		  	       }
			        
		       
			       queryStr.append(" COUNT(DISTINCT CONCAT(E.parent_event_id,'-',TC.tdp_cadre_id)) as count ");
			
			       queryStr.append(" FROM event_attendee EA,event_invitee EI," +
					               " event E,tdp_cadre TC," +
					               " user_address UA");
		
		          if(locationType != null && locationType.equalsIgnoreCase("District")){
			         queryStr.append(",district d"); 
			        }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
			         queryStr.append(",constituency c"); 
			  	    }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
			         queryStr.append(",tehsil t");
			        }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
			        queryStr.append(",panchayat p");
			        }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
		 	        queryStr.append(",constituency c");
		 	        }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
		 	        queryStr.append(",local_election_body leb");
		 	       }
				     
				     queryStr.append(" WHERE EA.event_id = E.event_id AND "+
		             " EA.tdp_cadre_id = EI.tdp_cadre_id AND " +
		             " E.parent_event_id=EI.event_id AND " +
		             " EA.tdp_cadre_id =TC.tdp_cadre_id AND " +
		             " TC.address_id = UA.user_address_id AND "+
					 " TC.is_deleted = 'N' AND "+
					 " TC.enrollment_year = 2014 ");
		
		  if(eventIds != null && eventIds.size() >0){
			queryStr.append(" AND E.parent_event_id in(:eventIds)" );	
		  }
		  if(locationType != null && locationType.equalsIgnoreCase("District")){
		        queryStr.append(" and UA.district_id=d.district_id");
		   }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
		        queryStr.append("  and UA.constituency_id=c.constituency_id ");
			  }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
		     	queryStr.append(" and UA.tehsil_id=t.tehsil_id ");  
		   }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
		 	   queryStr.append(" and UA.panchayat_id=p.panchayat_id "); 
		   }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
		 	   queryStr.append(" and UA.ward=c.constituency_id"); 
		      }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
		 	   queryStr.append(" and UA.local_election_body=leb.local_election_body_id "); 
		      }
		  
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			    queryStr.append(" and UA.state_id in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			        queryStr.append(" and UA.district_id in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			     queryStr.append(" and UA.parliament_constituency_id in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			     queryStr.append(" and UA.constituency_id in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			        queryStr.append(" and UA.tehsil_id in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			        queryStr.append(" and UA.local_election_body in (:userAccessLevelValues)"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			        queryStr.append(" and UA.panchayat_id in (:userAccessLevelValues)"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			        queryStr.append(" and UA.ward in (:userAccessLevelValues)"); 
			 }
		 if(stateId != null && stateId.longValue() > 0){
				if(stateId.longValue()==1l){
				   queryStr.append(" and UA.district_id > 10 and UA.state_id = 1 ");
				}else if(stateId.longValue()==36l){
					queryStr.append(" and UA.district_id < 11 ");
				}
		 }
		   if(locationType != null && locationType.equalsIgnoreCase("District")){
		       queryStr.append(" group by d.district_id "); 
		    }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
		       queryStr.append(" group by c.constituency_id "); 
			}else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
		       queryStr.append(" group by t.tehsil_id ");
		    }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
		       queryStr.append(" group by p.panchayat_id ");
		    }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
		       queryStr.append(" group by c.constituency_id ");
		    }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
		       queryStr.append(" group by leb.local_election_body_id");
		    }
		
		Session session = getSession();
		SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
		
		if(locationType != null && locationType.equalsIgnoreCase("District")){
			 sqlQuery.addScalar("districtId",Hibernate.LONG); 
			 sqlQuery.addScalar("districtName",Hibernate.STRING); 
		   }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
		    sqlQuery.addScalar("assemblyId",Hibernate.LONG); 
		    sqlQuery.addScalar("assemblyName",Hibernate.STRING); 
		   }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
		    sqlQuery.addScalar("tehsilId",Hibernate.LONG); 
			 sqlQuery.addScalar("tehsilName",Hibernate.STRING); 
		   }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
		    sqlQuery.addScalar("panchayatId",Hibernate.LONG); 
			sqlQuery.addScalar("panchayatName",Hibernate.STRING); 
		   }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
			 sqlQuery.addScalar("wardId",Hibernate.LONG); 
			 sqlQuery.addScalar("wardName",Hibernate.STRING); 
		    } else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
			  sqlQuery.addScalar("localElectionBoydId",Hibernate.LONG); 
			  sqlQuery.addScalar("localElectionBoydName",Hibernate.STRING); 
		  }
		    sqlQuery.addScalar("count",Hibernate.LONG);
			
		 if(eventIds != null && eventIds.size() >0){
		 	sqlQuery.setParameterList("eventIds", eventIds);	
		 }
		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			sqlQuery.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		return sqlQuery.list();
		}
	
	public List<Object[]> getEventAttendeeSummary(List<Long> cadreIds,List<Long> eventIds){
		Query query = getSession().createQuery(" select model.eventId-1,model.tdpCadreId " +
				" from EventAttendee model " +
				" where model.eventId in (8,31) " +
				" and model.tdpCadreId in (:cadreIds) " +
				" group by  model.eventId  ");
		
		//query.setParameterList("eventIds", eventIds);
		query.setParameterList("cadreIds", cadreIds);
		
		return query.list();
	}
}
