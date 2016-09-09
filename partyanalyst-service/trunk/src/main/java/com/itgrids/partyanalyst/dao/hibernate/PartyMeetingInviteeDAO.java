package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingInviteeDAO;
import com.itgrids.partyanalyst.dto.PartyMeetingsInputVO;
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
	
	
	// coredashboard meetings.
	
	//invited count
	public List<Object[]> getInvitedCountForPartyMeetingTypeIds(PartyMeetingsInputVO inputVO){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select  PMT.party_meeting_type_id as partyMeetingId , PMT.type as type ,COUNT(DISTINCT CONCAT(PMA.party_meeting_id,'-',A.tdp_cadre_id)) as count " +
				 "  from    party_meeting_attendance PMA,attendance A,party_meeting PM,party_meeting_type PMT,party_meeting_main_type PMMT,tdp_cadre TC,user_address UA  " +
				 "  where   PMA.attendance_id = A.attendance_id and " +
				 "          A.tdp_cadre_id = TC.tdp_cadre_id and " +
				 "          PMA.party_meeting_id = PM.party_meeting_id and " +
				 "          PM.party_meeting_type_id = PMT.party_meeting_type_id and " +
				 "          PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and " +
				 "          PM.meeting_address_id = UA.user_address_id and " +
				 "          PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
				 "          TC.is_deleted = 'N' and TC.enrollment_year = 2014 ");

 		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			 sb.append(" and date(PM.start_date) between :startDate and :endDate ");	 
		}
 		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
 			sb.append(" and UA.state_id = :stateId ");
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
		   sb.append(" and PMT.party_meeting_type_id in (:partyMeetingTypeIds) ");	
		}
		sb.append(" GROUP BY PMT.party_meeting_type_id");
		
		Query query = getSession().createSQLQuery(sb.toString())
		.addScalar("partyMeetingId",Hibernate.LONG)
		.addScalar("type",Hibernate.STRING)
		.addScalar("count",Hibernate.LONG);
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			query.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
		}
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			query.setDate("startDate",inputVO.getStartDate());
			query.setDate("endDate",inputVO.getEndDate());	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			query.setParameter("stateId",inputVO.getStateId());
		}
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
	   return query.list();
	}
	
	//invited attended count.
	public List<Object[]> getInvitteeAttendedCountForPartyMeetingTypeIds(PartyMeetingsInputVO inputVO){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select  PMT.party_meeting_type_id as partyMeetingId , PMT.type as type," +
				"           COUNT(DISTINCT CONCAT(PMA.party_meeting_id,'-',A.tdp_cadre_id)) as count" +
				"   from    party_meeting_attendance PMA,attendance A,party_meeting_invitee PMI,party_meeting PM,party_meeting_type PMT,party_meeting_main_type PMMT,tdp_cadre TC,user_address UA " +
				"   where   PMA.attendance_id = A.attendance_id and " +
				"           A.tdp_cadre_id = PMI.tdp_cadre_id and " +
				"           PMI.tdp_cadre_id = TC.tdp_cadre_id and " +
				"           PMI.party_meeting_id = PMA.party_meeting_id and " +
				"           PMA.party_meeting_id = PM.party_meeting_id and " +
				"           PM.party_meeting_type_id = PMT.party_meeting_type_id and " +
				"           PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and " +
				"           PM.meeting_address_id = UA.user_address_id and " +
				"           PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
				"           TC.is_deleted = 'N' and TC.enrollment_year = 2014 " );
		
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			 sb.append(" and date(PM.start_date) between :startDate and :endDate ");	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			sb.append(" and UA.state_id = :stateId ");
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			sb.append(" and PMT.party_meeting_type_id in (:partyMeetingTypeIds) ");	
		}
		sb.append(" group by PMT.party_meeting_type_id ");
		Query query = getSession().createSQLQuery(sb.toString())
		.addScalar("partyMeetingId",Hibernate.LONG)
		.addScalar("type",Hibernate.STRING)
		.addScalar("count",Hibernate.LONG);
		
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			query.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
		}
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			query.setDate("startDate",inputVO.getStartDate());
			query.setDate("endDate",inputVO.getEndDate());	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			query.setParameter("stateId",inputVO.getStateId());
		}
		
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
	   return query.list();
	}

	
	
	
	
	
	
}
