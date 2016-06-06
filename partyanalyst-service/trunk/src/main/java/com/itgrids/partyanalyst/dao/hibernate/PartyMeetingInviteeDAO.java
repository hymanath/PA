package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingInviteeDAO;
import com.itgrids.partyanalyst.model.PartyMeetingInvitee;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartyMeetingInviteeDAO extends GenericDaoHibernate<PartyMeetingInvitee,Long> implements IPartyMeetingInviteeDAO{

	public PartyMeetingInviteeDAO()
	{
		super(PartyMeetingInvitee.class);
	}
	
	public List<Object[]> getPartyMeetingsInvitationsDetailsByCadreIds(List<Long> tdpCadreIdsList,Date toDayDate)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct PMI.partyMeeting.partyMeetingLevel.partyMeetingLevelId, PMI.partyMeeting.partyMeetingLevel.level," +
				" PMI.partyMeeting.partyMeetingType.partyMeetingTypeId,  PMI.partyMeeting.partyMeetingType.type, count( distinct  PMI.partyMeeting.partyMeetingId)  from PartyMeetingInvitee PMI where " +
				" date(PMI.partyMeeting.startDate) <= :toDayDate  ");
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			queryStr.append(" and  PMI.tdpCadreId in (:tdpCadreIdsList) ");
		queryStr.append(" group by PMI.partyMeeting.partyMeetingType.partyMeetingTypeId ,  PMI.tdpCadreId order by PMI.partyMeeting.partyMeetingLevel.partyMeetingLevelId ");
		Query query = getSession().createQuery(queryStr.toString());
		 query.setDate("toDayDate", toDayDate);
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		return query.list();
	}
	/*
	 * public List<Object[]> getTotalAttendedDetailsForCadreIds(List<Long> tdpCadreIdsList,Long partyMeetingTypeId,Date todayDate)
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
		if(todayDate != null )
			queryStr.append(" and date(PMA.partyMeeting.startDate) <=:todayDate ");
		
		queryStr.append(" group by PMA.partyMeeting.partyMeetingId order by PMA.partyMeeting.partyMeetingId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0L)
			query.setParameter("partyMeetingTypeId", partyMeetingTypeId);		
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		if(todayDate != null )
			query.setDate("todayDate", todayDate);
		return query.list();
	}
	*/
	public List<Object[]> getPartyMeetingsInvitationDetlsByCadreIds(List<Long> tdpCadreIdsList,Long partyMeetingTypeId,Date todayDate)
	{
		StringBuilder queryStr = new StringBuilder();
		boolean isSetWhere = false;
		queryStr.append(" select distinct PMI.partyMeeting.partyMeetingId,PMI.partyMeeting.meetingName,PMI.partyMeeting.partyMeetingLevelId,PMI.partyMeeting.partyMeetingLevel.level," +
				" PMI.partyMeeting.locationValue ,PMI.partyMeeting.partyMeetingType.partyMeetingTypeId, PMI.partyMeeting.partyMeetingType.type," +
				" date(PMI.partyMeeting.startDate),date(PMI.partyMeeting.endDate),count(PMI.tdpCadreId),PMI.partyMeeting.meetingAddress.localArea   from PartyMeetingInvitee PMI where ");
		queryStr.append(" date(PMI.partyMeeting.startDate) <= :todayDate ");
		if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0L){
			queryStr.append(" and  PMI.partyMeeting.partyMeetingType.partyMeetingTypeId=:partyMeetingTypeId ");
			isSetWhere = true;
		}
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
		{
			if(!isSetWhere)
				queryStr.append(" and PMI.tdpCadreId in (:tdpCadreIdsList) ");
			else
				queryStr.append(" and PMI.tdpCadreId in (:tdpCadreIdsList) ");
		}

		queryStr.append(" group by PMI.partyMeeting.partyMeetingId order by PMI.partyMeeting.partyMeetingId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setDate("todayDate", todayDate);
		if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0L)
			query.setParameter("partyMeetingTypeId", partyMeetingTypeId);
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getPartyMeetingInvittees(Long partyMeetingId)
	{
		Query query = getSession().createQuery("SELECT DISTINCT model.tdpCadre.memberShipNo FROM PartyMeetingInvitee model where model.partyMeeting.partyMeetingId = :partyMeetingId  " +
				" and model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear="+IConstants.CADRE_ENROLLMENT_NUMBER+" ");
		query.setParameter("partyMeetingId",partyMeetingId);
		return query.list();
	}

	/*
	 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
	 * @since 21-AUG-2015
	 * This DAO Call is to Get Total Invitees Of Meetings
	 * @param List<Long> partyMeetingIds
	 * @return List<Object[]>  of PartyMeetingId, LocationValue, Count of Invitee Attendents
	 */
	public List<Object[]> getPartyMeetingInviteesForMeetings(List<Long> partyMeetingIds){
		Query query = getSession().createQuery(" select model.partyMeeting.partyMeetingId," +
				" model.partyMeeting.locationValue," +
				" count(distinct model.tdpCadre.tdpCadreId)" +
				" from PartyMeetingInvitee model" +
				" where " +
				" model.partyMeeting.partyMeetingId in(:partyMeetingIds) " +
				" and model.tdpCadre.isDeleted='N'" +
				" and model.tdpCadre.enrollmentYear=:enrollmentYear " +
				" group by model.partyMeeting.partyMeetingId " +
				" order by model.partyMeeting.partyMeetingId desc ");
		
		query.setParameterList("partyMeetingIds", partyMeetingIds);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getInviteesForPartyMeetings(List<Long> partyMeetingsList)
	{
		Query query = getSession().createQuery("SELECT model.partyMeeting.partyMeetingId,model.tdpCadre.tdpCadreId from PartyMeetingInvitee model where model.partyMeeting.partyMeetingId in(:partyMeetingsList) " +
				" group by model.partyMeeting.partyMeetingId,model.tdpCadre.tdpCadreId");
		
		query.setParameterList("partyMeetingsList",partyMeetingsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPublicRepresentativeInviteesForPartyMeetings(List<Long> partyMeetingsList)
	{
		Query query = getSession().createQuery("SELECT model.partyMeeting.partyMeetingId,model.tdpCadre.tdpCadreId from PartyMeetingInvitee model,TdpCadreCandidate model2 where " +
				" model.tdpCadre.tdpCadreId = model2.tdpCadre.tdpCadreId and model.partyMeeting.partyMeetingId in(:partyMeetingsList) " +
				" group by model.partyMeeting.partyMeetingId,model.tdpCadre.tdpCadreId");
		
		query.setParameterList("partyMeetingsList",partyMeetingsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCommitteeMemberInviteesForPartyMeetings(List<Long> partyMeetingsList)
	{
		Query query = getSession().createQuery("SELECT model.partyMeeting.partyMeetingId,model.tdpCadre.tdpCadreId from PartyMeetingInvitee model,TdpCommitteeMember model2 where " +
				" model.tdpCadre.tdpCadreId = model2.tdpCadre.tdpCadreId and model.partyMeeting.partyMeetingId in(:partyMeetingsList) and model2.isActive = 'Y'" +
				" group by model.partyMeeting.partyMeetingId,model.tdpCadre.tdpCadreId");
		
		query.setParameterList("partyMeetingsList",partyMeetingsList);
		return query.list();
	}
	
	public List<Long> getInvitedCadreIdsByPartyMeetingId(Long partyMeetingId){
		
		Query query = getSession().createQuery(" select distinct model.tdpCadre.tdpCadreId " +
						" from PartyMeetingInvitee model " +
						" where model.partyMeeting.partyMeetingId = :partyMeetingId and  model.tdpCadre.isDeleted='N' and  model.tdpCadre.enrollmentYear ="+IConstants.CADRE_ENROLLMENT_NUMBER+" ");
		query.setParameter("partyMeetingId", partyMeetingId);
		
		return query.list();
	}
}
