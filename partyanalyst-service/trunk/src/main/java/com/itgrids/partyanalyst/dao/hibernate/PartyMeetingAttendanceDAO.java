package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;
import com.itgrids.partyanalyst.model.PartyMeetingAttendance;

public class PartyMeetingAttendanceDAO extends GenericDaoHibernate<PartyMeetingAttendance,Long> implements IPartyMeetingAttendanceDAO{

	public PartyMeetingAttendanceDAO()
	{
		super(PartyMeetingAttendance.class);
	}
	
	public List<Object[]> getPartyMeetingsAttendenceDetailsByCadreId(List<Long> tdpCadreIdsList,Date toDayDate)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  PMA.partyMeeting.partyMeetingLevel.partyMeetingLevelId, PMA.partyMeeting.partyMeetingLevel.level, " +
				" PMA.partyMeeting.partyMeetingType.partyMeetingTypeId, PMA.partyMeeting.partyMeetingType.type, count(PMA.attendance.tdpCadreId)  from PartyMeetingAttendance PMA  where " +
				" date(PMA.partyMeeting.startDate) <= :toDayDate ");
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			queryStr.append("  and PMA.attendance.tdpCadreId in (:tdpCadreIdsList) ");
		queryStr.append(" group by PMA.partyMeeting.partyMeetingType.partyMeetingTypeId order by PMA.attendance.tdpCadreId ");
		Query query = getSession().createQuery(queryStr.toString());
		 query.setDate("toDayDate", toDayDate);
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		return query.list();
	}
	
	public List<Object[]> getTotalAttendedDetailsForCadreIds(List<Long> tdpCadreIdsList,Long partyMeetingTypeId)
	{
		boolean isSetWhere = false;
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct PMA.partyMeeting.partyMeetingId, PMA.partyMeeting.meetingName,PMA.partyMeeting.partyMeetingLevelId,PMA.partyMeeting.partyMeetingLevel.level, " +
				" PMA.partyMeeting.locationValue ,PMA.partyMeeting.partyMeetingType.partyMeetingTypeId, PMA.partyMeeting.partyMeetingType.type," +
				" date(PMA.partyMeeting.startDate),date(PMA.partyMeeting.endDate),  count(distinct PMA.attendance.tdpCadreId),PMA.partyMeeting.meetingAddress.localArea  from PartyMeetingAttendance PMA ");
		if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0L){
			queryStr.append(" where PMA.partyMeeting.partyMeetingType.partyMeetingTypeId=:partyMeetingTypeId ");
			isSetWhere = true;
		}
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
		{
			if(!isSetWhere)
				queryStr.append(" where PMA.attendance.tdpCadreId in (:tdpCadreIdsList) ");
			else
				queryStr.append(" and PMA.attendance.tdpCadreId in (:tdpCadreIdsList) ");
		}
		queryStr.append(" group by PMA.partyMeeting.partyMeetingId order by PMA.partyMeeting.partyMeetingId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0L)
			query.setParameter("partyMeetingTypeId", partyMeetingTypeId);		
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		
		return query.list();
	}
	
	public List<Object[]> getAbsentMemberDeails(Long partyMeetingTypeId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct PMA.partyMeeting.partyMeetingId, PMA.partyMeeting.meetingName, count(distinct PMA.attendance.tdpCadreId) " +
				" from PartyMeetingAttendance PMA ,PartyMeetingInvitee PMI where PMA.partyMeeting.partyMeetingId = PMI.partyMeeting.partyMeetingId and " +
				" PMI.tdpCadreId = PMA.attendance.tdpCadreId ");
		if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0L){
			queryStr.append("  PMA.partyMeeting.partyMeetingType.partyMeetingTypeId=:partyMeetingTypeId ");
		}
		
		queryStr.append(" group by PMA.partyMeeting.partyMeetingId order by PMA.partyMeeting.partyMeetingId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0L)
			query.setParameter("partyMeetingTypeId", partyMeetingTypeId);		
		
		return query.list();
	}
	
	public List<Object[]> getAttendenceForCadre(Long tdpCadreId,Date todayDate)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct PMA.partyMeeting.partyMeetingId, PMA.partyMeeting.meetingName, count(distinct PMA.attendance.tdpCadreId) " +
				" from PartyMeetingAttendance PMA where PMA.attendance.tdpCadreId =:tdpCadreId and date(PMA.partyMeeting.startDate) <= :todayDate ");
		queryStr.append(" group by PMA.partyMeeting.partyMeetingId order by PMA.partyMeeting.partyMeetingId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("tdpCadreId", tdpCadreId);	
		query.setDate("todayDate", todayDate);
		return query.list();
	}
	
	
	/*
	 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
	 * @since 21-AUG-2015
	 * This DAO Call is to Get Total Attendents Of Meeting
	 * @param List<Long> partyMeetingIds
	 * @return List<Object[]>  of PartyMeetingId, LocationValue, Count of Attendents
	 */
	public List<Object[]> getTotalAttendentsOfMeetings(List<Long> partyMeetingIds){
		Query query = getSession().createQuery(" select model.partyMeeting.partyMeetingId," +
				" model.partyMeeting.locationValue," +
				" count(distinct model.attendance.tdpCadreId) " +
				" from PartyMeetingAttendance model" +
				" where model.partyMeeting.partyMeetingId in(:partyMeetingIds)" +
				" group by model.partyMeeting.partyMeetingId");
		query.setParameterList("partyMeetingIds", partyMeetingIds);
		return query.list();
	}
	
	
	/*
	 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
	 * @since 21-AUG-2015
	 * This DAO Call is to Get Total Invitee Attendents Of Meeting
	 * @param List<Long> partyMeetingIds
	 * @return List<Object[]>  of PartyMeetingId, LocationValue, Count of Invitee Attendents
	 */
	public List<Object[]> getInviteesAttendedCountOfMeetings(List<Long> partyMeetingIds){
		Query query = getSession().createQuery(" select model.partyMeeting.partyMeetingId," +
				" model.partyMeeting.locationValue," +
				" count(distinct model.attendance.tdpCadreId) " +
				" from PartyMeetingAttendance model, PartyMeetingInvitee model1 " +
				" where model.partyMeeting.partyMeetingId = model1.partyMeeting.partyMeetingId" +
				" and model.attendance.tdpCadreId = model1.tdpCadre.tdpCadreId " +
				" and model.partyMeeting.partyMeetingId in(:partyMeetingIds)" +
				" group by model.partyMeeting.partyMeetingId");
		query.setParameterList("partyMeetingIds", partyMeetingIds);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAttendanceForMeetings(List<Long> partyMeetingsList)
	{
		Query query = getSession().createQuery("SELECT model.partyMeeting.partyMeetingId,model.attendance.tdpCadre.tdpCadreId FROM PartyMeetingAttendance model where model.partyMeeting.partyMeetingId in (:partyMeetingsList) "+
 		" group by model.partyMeeting.partyMeetingId,model.attendance.tdpCadre.tdpCadreId order by model.partyMeeting.partyMeetingId,model.attendance.tdpCadre.tdpCadreId ");
		query.setParameterList("partyMeetingsList",partyMeetingsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidateAttendanceForMeetings(List<Long> partyMeetingsList)
	{
		Query query = getSession().createQuery("SELECT model.partyMeeting.partyMeetingId,model.attendance.tdpCadre.tdpCadreId FROM PartyMeetingAttendance model,TdpCadreCandidate model2 where " +
				"model.attendance.tdpCadre.tdpCadreId = model2.tdpCadre.tdpCadreId and model.partyMeeting.partyMeetingId in (:partyMeetingsList) "+
 		" group by model.partyMeeting.partyMeetingId,model.attendance.tdpCadre.tdpCadreId order by model.partyMeeting.partyMeetingId,model.attendance.tdpCadre.tdpCadreId ");
		query.setParameterList("partyMeetingsList",partyMeetingsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCommitteeMemberAttendanceForMeetings(List<Long> partyMeetingsList)
	{
		Query query = getSession().createQuery("SELECT model.partyMeeting.partyMeetingId,model.attendance.tdpCadre.tdpCadreId FROM PartyMeetingAttendance model,TdpCommitteeMember model2 where " +
				"model.attendance.tdpCadre.tdpCadreId = model2.tdpCadre.tdpCadreId and model2.isActive = 'Y' and model.partyMeeting.partyMeetingId in (:partyMeetingsList) "+
 		" group by model.partyMeeting.partyMeetingId,model.attendance.tdpCadre.tdpCadreId order by model.partyMeeting.partyMeetingId,model.attendance.tdpCadre.tdpCadreId ");
		query.setParameterList("partyMeetingsList",partyMeetingsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getConductedMeetings(List<Long> partyMeetingsList)
	{
		Query query = getSession().createQuery("SELECT model.partyMeeting.partyMeetingId FROM PartyMeetingAttendance model where model.partyMeeting.partyMeetingId in (:partyMeetingsList) and model.attendance.tdpCadre is not null group by model.partyMeeting.partyMeetingId");
		query.setParameterList("partyMeetingsList",partyMeetingsList);
		return query.list();
	}
	
}
