package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

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
				" date(PMI.partyMeeting.startDate) <= :toDayDate and PMI.partyMeeting.isActive='Y' ");
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
				" date(PMI.partyMeeting.startDate),date(PMI.partyMeeting.endDate),count(PMI.tdpCadreId),PMI.partyMeeting.meetingAddress.localArea ,PMI.absenteeRemark   from PartyMeetingInvitee PMI where ");
		queryStr.append(" date(PMI.partyMeeting.startDate) <= :todayDate and PMI.partyMeeting.isActive='Y' ");
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
				" and model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear="+IConstants.CADRE_ENROLLMENT_NUMBER+" and model.partyMeeting.isActive='Y' ");
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
				" and model.tdpCadre.enrollmentYear=:enrollmentYear and model.partyMeeting.isActive='Y' " +
				" group by model.partyMeeting.partyMeetingId " +
				" order by model.partyMeeting.partyMeetingId desc ");
		
		query.setParameterList("partyMeetingIds", partyMeetingIds);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getInviteesForPartyMeetings(List<Long> partyMeetingsList)
	{
		if(partyMeetingsList != null && partyMeetingsList.size()>0){
		Query query = getSession().createQuery("SELECT model.partyMeeting.partyMeetingId,model.tdpCadre.tdpCadreId from PartyMeetingInvitee model where model.partyMeeting.partyMeetingId in(:partyMeetingsList)" +
				" and model.partyMeeting.isActive='Y' " +
				" group by model.partyMeeting.partyMeetingId,model.tdpCadre.tdpCadreId");
		
		query.setParameterList("partyMeetingsList",partyMeetingsList);
		return query.list();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPublicRepresentativeInviteesForPartyMeetings(List<Long> partyMeetingsList)
	{
		Query query = getSession().createQuery("SELECT model.partyMeeting.partyMeetingId,model.tdpCadre.tdpCadreId from PartyMeetingInvitee model,TdpCadreCandidate model2 where " +
				" model.tdpCadre.tdpCadreId = model2.tdpCadre.tdpCadreId and model.partyMeeting.partyMeetingId in(:partyMeetingsList) and model.partyMeeting.isActive='Y' " +
				" group by model.partyMeeting.partyMeetingId,model.tdpCadre.tdpCadreId");
		
		query.setParameterList("partyMeetingsList",partyMeetingsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCommitteeMemberInviteesForPartyMeetings(List<Long> partyMeetingsList)
	{
		Query query = getSession().createQuery("SELECT model.partyMeeting.partyMeetingId,model.tdpCadre.tdpCadreId from PartyMeetingInvitee model,TdpCommitteeMember model2 where " +
				" model.tdpCadre.tdpCadreId = model2.tdpCadre.tdpCadreId and model.partyMeeting.partyMeetingId in(:partyMeetingsList) and model2.isActive = 'Y' and model.partyMeeting.isActive='Y' " +
				" group by model.partyMeeting.partyMeetingId,model.tdpCadre.tdpCadreId");
		
		query.setParameterList("partyMeetingsList",partyMeetingsList);
		return query.list();
	}
	
	public List<Long> getInvitedCadreIdsByPartyMeetingId(Long partyMeetingId){
		
		Query query = getSession().createQuery(" select distinct model.tdpCadre.tdpCadreId " +
						" from PartyMeetingInvitee model " +
						" where model.partyMeeting.partyMeetingId = :partyMeetingId and model.partyMeeting.isActive='Y' and  model.tdpCadre.isDeleted='N' and  model.tdpCadre.enrollmentYear ="+IConstants.CADRE_ENROLLMENT_NUMBER+" ");
		query.setParameter("partyMeetingId", partyMeetingId);
		
		return query.list();
	}
	
	
	// coredashboard meetings.
	
	//invited count
	public List<Object[]> getInvitedCountForPartyMeetingTypeIds(PartyMeetingsInputVO inputVO){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select PMT.party_meeting_type_id as partyMeetingId ,PMT.type as type ," +//1
				"          COUNT(DISTINCT CONCAT(PMI.party_meeting_id,'-',PMI.tdp_cadre_id)) as count " +//2
				  " from   party_meeting_invitee PMI,party_meeting PM,party_meeting_type PMT,party_meeting_main_type PMMT,tdp_cadre TC,user_address UA " +
				  " where  PMI.tdp_cadre_id = TC.tdp_cadre_id and " +
				  "        PMI.party_meeting_id = PM.party_meeting_id and " +
				  "        PM.party_meeting_type_id = PMT.party_meeting_type_id and " +
				  "        PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and " +
				  "        PM.meeting_address_id = UA.user_address_id and " +
				  "        PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
				  "        TC.is_deleted = 'N' and TC.enrollment_year = 2014 and PM.is_active='Y' ");
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			 sb.append(" and date(PM.start_date) between :startDate and :endDate ");	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			sb.append(" and UA.state_id = :stateId ");
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			sb.append(" and PMT.party_meeting_type_id in (:partyMeetingTypeIds) ");	
		}
		
		if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size()>0){
			sb.append(" and PM.party_meeting_id in (:partyMeetingIds) ");
		}
		
		sb.append(" group by  PMT.party_meeting_type_id ");
		
		Query query = getSession().createSQLQuery(sb.toString())
		.addScalar("partyMeetingId",Hibernate.LONG)
		.addScalar("type",Hibernate.STRING)
		.addScalar("count",Hibernate.LONG);
		
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			query.setDate("startDate",inputVO.getStartDate());
			query.setDate("endDate",inputVO.getEndDate());	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			query.setParameter("stateId",inputVO.getStateId());
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			query.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
		}
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
		if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size()>0){
			query.setParameterList("partyMeetingIds",inputVO.getPartyMeetingIds());
		}
	   return query.list();
	}
	
public List<Object[]> getInvitedPartyMeetingdtlsForPartyMeetingTypeIds(PartyMeetingsInputVO inputVO){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct  PMT.party_meeting_type_id as partyMeetingTypeId ,PMI.party_meeting_id as partyMeetingId,PM.meeting_name as meetingName" +//2
				  " from   party_meeting_invitee PMI,party_meeting PM,party_meeting_type PMT,party_meeting_main_type PMMT,tdp_cadre TC,user_address UA " +
				  " where  PMI.tdp_cadre_id = TC.tdp_cadre_id and " +
				  "        PMI.party_meeting_id = PM.party_meeting_id and " +
				  "        PM.party_meeting_type_id = PMT.party_meeting_type_id and " +
				  "        PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and " +
				  "        PM.meeting_address_id = UA.user_address_id and " +
				  "        PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
				  "        TC.is_deleted = 'N' and TC.enrollment_year = 2014 and PM.is_active='Y' ");
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			 sb.append(" and date(PM.start_date) between :startDate and :endDate ");	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			sb.append(" and UA.state_id = :stateId ");
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			sb.append(" and PMT.party_meeting_type_id in (:partyMeetingTypeIds) ");	
		}
		
		if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size()>0){
			sb.append(" and PM.party_meeting_id in (:partyMeetingIds) ");
		}
		
		sb.append(" order by  PMT.party_meeting_type_id ");
		
		Query query = getSession().createSQLQuery(sb.toString())		
		.addScalar("partyMeetingTypeId",Hibernate.LONG)
		.addScalar("partyMeetingId",Hibernate.LONG)
		.addScalar("meetingName",Hibernate.STRING);
		
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			query.setDate("startDate",inputVO.getStartDate());
			query.setDate("endDate",inputVO.getEndDate());	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			query.setParameter("stateId",inputVO.getStateId());
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			query.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
		}
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
		if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size()>0){
			query.setParameterList("partyMeetingIds",inputVO.getPartyMeetingIds());
		}
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
				"           TC.is_deleted = 'N' and TC.enrollment_year = 2014 and PM.is_active='Y' " );
		
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			 sb.append(" and date(PM.start_date) between :startDate and :endDate ");	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			sb.append(" and UA.state_id = :stateId ");
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			sb.append(" and PMT.party_meeting_type_id in (:partyMeetingTypeIds) ");	
		}
		if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size()>0){
			sb.append(" and PM.party_meeting_id in (:partyMeetingIds) ");
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
		if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size()>0){
			query.setParameterList("partyMeetingIds",inputVO.getPartyMeetingIds());
		}
	   return query.list();
	}

	
	public List<Object[]> getInvitedCadreCountForMeetingsByCommitteeWise(PartyMeetingsInputVO inputVO){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select  tcl.tdp_committee_level_id as tdpCommitteeLevelId,tcl.tdp_committee_level as tdpcommitteeLevel," +
				  "         COUNT(DISTINCT CONCAT(pm.party_meeting_id,'-',cadre.tdp_cadre_id)) as count " +
				  " from    party_meeting_invitee pmi " +
				  "         join party_meeting pm on pmi.party_meeting_id = pm.party_meeting_id " +
				  "         join tdp_cadre cadre on pmi.tdp_cadre_id = cadre.tdp_cadre_id " +
				  "         join user_address ua on pm.meeting_address_id = ua.user_address_id " +
				  "         join party_meeting_type pmt on pm.party_meeting_type_id = pmt.party_meeting_type_id " +
				  "         join party_meeting_main_type pmmt on pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id " +
				  "" +
				  "         join tdp_committee_member tcm  on tcm.tdp_cadre_id = cadre.tdp_cadre_id " +
				  "         join tdp_committee_role   tcr  on tcm.tdp_committee_role_id = tcr.tdp_committee_role_id " +
				  "         join tdp_committee        tc   on tcr.tdp_committee_id = tc.tdp_committee_id" +
				  "         join tdp_committee_level  tcl  on tc.tdp_committee_level_id = tcl.tdp_committee_level_id " +
				  " where   pmmt.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
				  "         cadre.is_deleted = 'N' and cadre.enrollment_year = 2014 and pm.is_active='Y' ");
		
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			 sb.append(" and date(pm.start_date) between :startDate and :endDate ");	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			sb.append(" and ua.state_id = :stateId ");
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			sb.append(" and pmt.party_meeting_type_id in (:partyMeetingTypeIds) ");	
		}
		sb.append(" group by tcl.tdp_committee_level_id ");
		
		Query query = getSession().createSQLQuery(sb.toString())
		.addScalar("tdpCommitteeLevelId",Hibernate.LONG)
		.addScalar("tdpcommitteeLevel",Hibernate.STRING)
		.addScalar("count",Hibernate.LONG);
		
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			query.setDate("startDate",inputVO.getStartDate());
			query.setDate("endDate",inputVO.getEndDate());	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			query.setParameter("stateId",inputVO.getStateId());
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			query.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
		}
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
	   return query.list();
		
	}
	
	public List<Object[]> getInvitteeAttendedCadreCountForMeetingsByCommitteeWise(PartyMeetingsInputVO inputVO){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select  tcl.tdp_committee_level_id as tdpCommitteeLevelId ,tcl.tdp_committee_level as tdpcommitteeLevel ," +
				"           COUNT(DISTINCT CONCAT(pm.party_meeting_id,'-',cadre.tdp_cadre_id)) as count " +
				"   from    party_meeting_attendance pma  " +
				"           join attendance a on pma.attendance_id = a.attendance_id " +
				"           join tdp_cadre cadre on a.tdp_cadre_id = cadre.tdp_cadre_id " +
				"           join party_meeting pm on pma.party_meeting_id = pm.party_meeting_id " +
				"           join party_meeting_invitee pmi on pmi.party_meeting_id = pm.party_meeting_id and pmi.tdp_cadre_id = cadre.tdp_cadre_id " +
				"           join user_address ua on pm.meeting_address_id = ua.user_address_id " +
				"           join party_meeting_type pmt on pm.party_meeting_type_id = pmt.party_meeting_type_id " +
				"           join party_meeting_main_type pmmt on pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id" +
				"" +
				"           join tdp_committee_member tcm  on tcm.tdp_cadre_id = cadre.tdp_cadre_id " +
				"           join tdp_committee_role   tcr  on tcm.tdp_committee_role_id = tcr.tdp_committee_role_id " +
				"           join tdp_committee        tc   on tcr.tdp_committee_id = tc.tdp_committee_id " +
				"           join tdp_committee_level  tcl  on tc.tdp_committee_level_id = tcl.tdp_committee_level_id " +
				" where     pmmt.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
				"           cadre.is_deleted = 'N' and cadre.enrollment_year = 2014 and pm.is_active='Y' ");
		
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			 sb.append(" and date(pm.start_date) between :startDate and :endDate ");	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			sb.append(" and ua.state_id = :stateId ");
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			sb.append(" and pmt.party_meeting_type_id in (:partyMeetingTypeIds) ");	
		}
		sb.append(" group by tcl.tdp_committee_level_id ");
		
		Query query = getSession().createSQLQuery(sb.toString())
		.addScalar("tdpCommitteeLevelId",Hibernate.LONG)
		.addScalar("tdpcommitteeLevel",Hibernate.STRING)
		.addScalar("count",Hibernate.LONG);
		
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			query.setDate("startDate",inputVO.getStartDate());
			query.setDate("endDate",inputVO.getEndDate());	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			query.setParameter("stateId",inputVO.getStateId());
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			query.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
		}
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
	   return query.list();
		
	}
	
	
	public List<Object[]> getInvitedCadreCountForMeetingsByPublicRepresentativeWise(PartyMeetingsInputVO inputVO){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select  prt.public_representative_type_id as representativeTypeId,prt.position as position," +
				"           COUNT(DISTINCT CONCAT(pm.party_meeting_id,'-',cadre.tdp_cadre_id)) as count " +
				"   from    party_meeting_invitee pmi " +
				"           join party_meeting pm on pmi.party_meeting_id = pm.party_meeting_id" +
				"           join tdp_cadre cadre on pmi.tdp_cadre_id = cadre.tdp_cadre_id" +
				"           join user_address ua on pm.meeting_address_id = ua.user_address_id" +
				"           join party_meeting_type pmt on pm.party_meeting_type_id = pmt.party_meeting_type_id" +
				"           join party_meeting_main_type pmmt on pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id" +
				"" +
				"           join tdp_cadre_candidate tcc  on tcc.tdp_cadre_id = cadre.tdp_cadre_id" +
				"           join candidate c on tcc.candidate_id = c.candidate_id" +
				"           join public_representative pr on c.candidate_id = pr.candidate_id" +
				"           join public_representative_type prt on pr.public_representative_type_id = prt.public_representative_type_id " +
				"  where    pmmt.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
				"           cadre.is_deleted = 'N' and cadre.enrollment_year = 2014 and pm.is_active='Y' ");
		
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			 sb.append(" and date(pm.start_date) between :startDate and :endDate ");	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			sb.append(" and ua.state_id = :stateId ");
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			sb.append(" and pmt.party_meeting_type_id in (:partyMeetingTypeIds) ");	
		}
		sb.append(" group by prt.public_representative_type_id ");
		
		Query query = getSession().createSQLQuery(sb.toString())
		.addScalar("representativeTypeId",Hibernate.LONG)
		.addScalar("position",Hibernate.STRING)
		.addScalar("count",Hibernate.LONG);
		
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			query.setDate("startDate",inputVO.getStartDate());
			query.setDate("endDate",inputVO.getEndDate());	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			query.setParameter("stateId",inputVO.getStateId());
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			query.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
		}
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
	   return query.list();
	}
	public List<Object[]> getInvitteeAttendedCadreCountForMeetingsByPublicRepresentativeWise(PartyMeetingsInputVO inputVO){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select    prt.public_representative_type_id as representativeTypeId ,prt.position as position," +
				"             COUNT(DISTINCT CONCAT(pm.party_meeting_id,'-',cadre.tdp_cadre_id)) as count " +
				"   from      party_meeting_attendance pma " +
				"             join attendance a on pma.attendance_id = a.attendance_id " +
				"             join tdp_cadre cadre on a.tdp_cadre_id = cadre.tdp_cadre_id " +
				"             join party_meeting pm on pma.party_meeting_id = pm.party_meeting_id " +
				"             join party_meeting_invitee pmi on pmi.party_meeting_id = pm.party_meeting_id and pmi.tdp_cadre_id = cadre.tdp_cadre_id " +
				"             join user_address ua on pm.meeting_address_id = ua.user_address_id" +
				"             join party_meeting_type pmt on pm.party_meeting_type_id = pmt.party_meeting_type_id " +
				"             join party_meeting_main_type pmmt on pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id" +
				"" +
				"             join tdp_cadre_candidate tcc  on tcc.tdp_cadre_id = cadre.tdp_cadre_id" +
				"             join candidate c on tcc.candidate_id = c.candidate_id" +
				"             join public_representative pr on c.candidate_id = pr.candidate_id " +
				"             join public_representative_type prt on pr.public_representative_type_id = prt.public_representative_type_id " +
				"   where     pmmt.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
				"             cadre.is_deleted = 'N' and cadre.enrollment_year = 2014 and pm.is_active='Y' ");
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			 sb.append(" and date(pm.start_date) between :startDate and :endDate ");	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			sb.append(" and ua.state_id = :stateId ");
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			sb.append(" and pmt.party_meeting_type_id in (:partyMeetingTypeIds) ");	
		}
		sb.append(" group by prt.public_representative_type_id ");
		
		Query query = getSession().createSQLQuery(sb.toString())
		.addScalar("representativeTypeId",Hibernate.LONG)
		.addScalar("position",Hibernate.STRING)
		.addScalar("count",Hibernate.LONG);
		
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			query.setDate("startDate",inputVO.getStartDate());
			query.setDate("endDate",inputVO.getEndDate());	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			query.setParameter("stateId",inputVO.getStateId());
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			query.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
		}
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
	   return query.list();
	}
public List<Object[]> getDistrictWiseInvitedCountForPartyMeetingTypeIds(PartyMeetingsInputVO inputVO, String status){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		if(status.equalsIgnoreCase("details")){
			sb.append(" distinct ");
		}
		
		sb.append(" PMT.party_meeting_type_id as partyMeetingId ,PMT.type as type ,D.district_id as districtId,D.district_name as districtName,");
		if(status.equalsIgnoreCase("count")){
			sb.append(" COUNT(DISTINCT CONCAT(PMI.party_meeting_id,'-',PMI.tdp_cadre_id)) as count ");
		}else{
			sb.append(" CONCAT(PMI.party_meeting_id,'-',PMI.tdp_cadre_id)  as count ");
		}	 
		sb.append(" from   party_meeting_invitee PMI,party_meeting PM,party_meeting_type PMT,party_meeting_main_type PMMT,tdp_cadre TC,user_address UA,district D " +
				  " where  PMI.tdp_cadre_id = TC.tdp_cadre_id and " +
				  "        PMI.party_meeting_id = PM.party_meeting_id and " +
				  "        PM.party_meeting_type_id = PMT.party_meeting_type_id and " +
				  "        PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and " +
				  "        TC.address_id = UA.user_address_id and " +
				  "        UA.district_id = D.district_id and " +
				  "        PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
				  "        TC.is_deleted = 'N' and TC.enrollment_year = 2014 and PM.is_active='Y' ");
		if(status.equalsIgnoreCase("details")){
			sb.append(" and  D.district_id = (:distId) ");
		}
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			 sb.append(" and date(PM.start_date) between :startDate and :endDate ");	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l && status.equalsIgnoreCase("count")){
			if(inputVO.getStateId().longValue()==1l){
				sb.append(" and D.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");	
			}else if(inputVO.getStateId().longValue()==36l){
				sb.append(" and D.district_id in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
			}
			
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			sb.append(" and PMT.party_meeting_type_id in (:partyMeetingTypeIds) ");	
		}
		if(status.equalsIgnoreCase("count")){
			sb.append(" group by  PMT.party_meeting_type_id,D.district_id order by PMT.party_meeting_type_id,D.district_id asc");
		}
		Session session = getSession();
		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
		//Query query = getSession().createSQLQuery(sb.toString())
		sqlQuery.addScalar("partyMeetingId",Hibernate.LONG);
		sqlQuery.addScalar("type",Hibernate.STRING);
		sqlQuery.addScalar("districtId",Hibernate.LONG);
		sqlQuery.addScalar("districtName",Hibernate.STRING);
		if(status.equalsIgnoreCase("details")){
			sqlQuery.addScalar("count",Hibernate.STRING);           
		}else{
			sqlQuery.addScalar("count",Hibernate.LONG);  
		}
		
		
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			sqlQuery.setDate("startDate",inputVO.getStartDate());
			sqlQuery.setDate("endDate",inputVO.getEndDate());	 
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			sqlQuery.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
		}
		sqlQuery.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
		if(status.equalsIgnoreCase("details")){
			sqlQuery.setParameter("distId", inputVO.getDistId());
		}
	   return sqlQuery.list();  
	}  
//Invited attended
public List<Object[]> getDistrictWiseInvitteeAttendedCountForPartyMeetingTypeIds(PartyMeetingsInputVO inputVO, String status){
	
	StringBuilder sb = new StringBuilder();
	sb.append(" select ");
	if(status.equalsIgnoreCase("details")){
		sb.append(" distinct ");
	}
	
	sb.append(" PMT.party_meeting_type_id as partyMeetingId ,PMT.type as type ,D.district_id as districtId,D.district_name as districtName,");
	if(status.equalsIgnoreCase("count")){
		sb.append(" COUNT(DISTINCT CONCAT(PMI.party_meeting_id,'-',PMI.tdp_cadre_id)) as count ");
	}else{
		sb.append(" CONCAT(PMI.party_meeting_id,'-',PMI.tdp_cadre_id)  as count, PM.meeting_name as meetingName ");
	}	   
	
	sb.append(" from " +
		      " party_meeting_attendance PMA,attendance A,party_meeting_invitee PMI,party_meeting PM,party_meeting_type PMT,party_meeting_main_type PMMT,tdp_cadre TC,user_address UA,district D " +
	    	  " where " +
	    	  " PMA.attendance_id = A.attendance_id and " +
			  " A.tdp_cadre_id = PMI.tdp_cadre_id and " +
			  " PMI.tdp_cadre_id = TC.tdp_cadre_id and " +  
			  " PMI.party_meeting_id = PMA.party_meeting_id and " +
			  " PMA.party_meeting_id = PM.party_meeting_id and " +
              " PM.party_meeting_type_id = PMT.party_meeting_type_id and " +
			  " PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and " +
			  " TC.address_id = UA.user_address_id and " +///
			  " UA.district_id = D.district_id and " +
			  " PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
			  " TC.is_deleted = 'N' and TC.enrollment_year = 2014 and PM.is_active='Y' " );
	if(status.equalsIgnoreCase("details")){
		sb.append(" and  D.district_id = (:distId) ");  
	}
	if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
		 sb.append(" and date(PM.start_date) between :startDate and :endDate ");	 
	}
	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l && status.equalsIgnoreCase("count")){
		if(inputVO.getStateId().longValue()==1l){
			sb.append(" and D.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");	
		}else if(inputVO.getStateId().longValue()==36l){
			sb.append(" and D.district_id in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
		}
	}
	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
		sb.append(" and PMT.party_meeting_type_id in (:partyMeetingTypeIds) ");	
	}
	if(status.equalsIgnoreCase("count")){
		sb.append(" group by PMT.party_meeting_type_id,D.district_id order by PMT.party_meeting_type_id,D.district_id asc ");////
	}
	Session session = getSession();
	SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
	//Query query = getSession().createSQLQuery(sb.toString())
	sqlQuery.addScalar("partyMeetingId",Hibernate.LONG);
	sqlQuery.addScalar("type",Hibernate.STRING);
	sqlQuery.addScalar("districtId",Hibernate.LONG);
	sqlQuery.addScalar("districtName",Hibernate.STRING);
	if(status.equalsIgnoreCase("details")){
		sqlQuery.addScalar("count",Hibernate.STRING); 
		sqlQuery.addScalar("meetingName",Hibernate.STRING);
	}else{
		sqlQuery.addScalar("count",Hibernate.LONG);    
	}
	
	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
		sqlQuery.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
	}
	if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
		sqlQuery.setDate("startDate",inputVO.getStartDate());
		sqlQuery.setDate("endDate",inputVO.getEndDate());	 
	}
	sqlQuery.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
	if(status.equalsIgnoreCase("details")){
		sqlQuery.setParameter("distId", inputVO.getDistId());
	}
   return sqlQuery.list();
}
public List<Long> getInvitedMemberCadreId(PartyMeetingsInputVO inputVO){
	
	StringBuilder sb = new StringBuilder();
	sb.append(" select distinct TC.tdp_cadre_id as id " +//2
			  " from   party_meeting_invitee PMI,party_meeting PM,party_meeting_type PMT,party_meeting_main_type PMMT,tdp_cadre TC,user_address UA " +
			  " where  PMI.tdp_cadre_id = TC.tdp_cadre_id and " +
			  "        PMI.party_meeting_id = PM.party_meeting_id and " +
			  "        PM.party_meeting_type_id = PMT.party_meeting_type_id and " +
			  "        PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and " +
			  "        PM.meeting_address_id = UA.user_address_id and " +
			  "        PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
			  "        TC.is_deleted = 'N' and TC.enrollment_year = 2014 and PM.is_active='Y' ");
	if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
		 sb.append(" and date(PM.start_date) between :startDate and :endDate ");	 
	}
	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
		sb.append(" and UA.state_id = :stateId ");
	}
	if(inputVO.getDistId() != null && inputVO.getDistId() > 0l){
		sb.append(" and PMT.party_meeting_type_id = :meetingId ");
				 
	}
	
	Query query = getSession().createSQLQuery(sb.toString())  
	.addScalar("id",Hibernate.LONG);
	
	if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
		query.setDate("startDate",inputVO.getStartDate());
		query.setDate("endDate",inputVO.getEndDate());	 
	}
	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
		query.setParameter("stateId",inputVO.getStateId());
	}
	if(inputVO.getDistId() != null && inputVO.getDistId() > 0l){
		query.setParameter("meetingId",inputVO.getDistId());
	}
	query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
	return query.list();
}
public List<Long> getAttendedMemberCadreId(PartyMeetingsInputVO inputVO){
	
	StringBuilder sb = new StringBuilder();       
	
	sb.append(" select  distinct TC.tdp_cadre_id as id " +
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
			"           TC.is_deleted = 'N' and TC.enrollment_year = 2014 and PM.is_active='Y' " );
	
	if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
		 sb.append(" and date(PM.start_date) between :startDate and :endDate ");	 
	}
	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
		sb.append(" and UA.state_id = :stateId ");
	}
	if(inputVO.getDistId() != null && inputVO.getDistId() > 0){
		   sb.append(" and PMT.party_meeting_type_id = :meetingId ");	
	}
	
	Query query = getSession().createSQLQuery(sb.toString())
	.addScalar("id",Hibernate.LONG);
	
	if(inputVO.getDistId() != null && inputVO.getDistId() > 0){  
		query.setParameter("meetingId",inputVO.getDistId());
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
	public List<Object[]> getDistrictWiseInvitedCountForPartyMeetingId(PartyMeetingsInputVO inputVO){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct ");
		
		sb.append(" PM.party_meeting_id as partyMeetingId ," +//0
				  " D.district_id as districtId," +//1
				  " D.district_name as districtName," +//2
				  " TC.tdp_cadre_id as cadreId");//3
		 
		sb.append(" from   " +
				  " party_meeting_invitee PMI," +
				  " party_meeting PM," +
				  " party_meeting_type PMT," +
				  " party_meeting_main_type PMMT," +  
				  " tdp_cadre TC,user_address UA,district D " +
				  " where  PMI.tdp_cadre_id = TC.tdp_cadre_id and " +
				  " PMI.party_meeting_id = PM.party_meeting_id and " +
				  " PM.party_meeting_type_id = PMT.party_meeting_type_id and " +
				  " PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and " +
				  " TC.address_id = UA.user_address_id and " +
				  " UA.district_id = D.district_id and " +
				  " PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and PM.is_active='Y' and " +
				  " TC.is_deleted = 'N' and TC.enrollment_year = 2014 ");
		
		/*if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			sb.append(" and date(PM.start_date) between :startDate and :endDate ");	 
		}*/
		
		if(inputVO.getStateId().longValue()==1l){
			sb.append(" and D.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");	
		}else if(inputVO.getStateId().longValue()==36l){
			sb.append(" and D.district_id in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
		}
		
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			sb.append(" and PMT.party_meeting_type_id in (:partyMeetingTypeIds) ");	
		}
		sb.append(" and PM.party_meeting_id = :partyMeetingId ");	
		sb.append(" order by  D.district_id ");	
		Session session = getSession();
		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
		sqlQuery.addScalar("partyMeetingId",Hibernate.LONG);
		sqlQuery.addScalar("districtId",Hibernate.LONG);
		sqlQuery.addScalar("districtName",Hibernate.STRING);
		sqlQuery.addScalar("cadreId",Hibernate.LONG);  
		
		/*if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			sqlQuery.setDate("startDate",inputVO.getStartDate());
			sqlQuery.setDate("endDate",inputVO.getEndDate());	 
		}*/
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			sqlQuery.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
		}
		sqlQuery.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
		sqlQuery.setParameter("partyMeetingId",inputVO.getPartyMeetingId()); 
		
		return sqlQuery.list();  
	}  
	public List<Object[]> getDistrictWiseInvitteeAttendedCountForPartyMeetingId(PartyMeetingsInputVO inputVO){
		StringBuilder sb = new StringBuilder();     
		sb.append(" select  distinct " +
				  " D.district_id as districtId, " +//0
				  " D.district_name as districtName, " +//1
				  " TC.tdp_cadre_id as cadreId, " +//2
				  " TC.first_name as name, " +//3    
				  " min(time(A.attended_time)) as time, " +//4
				  " PMS.session_type_id as sessionId");//5
		sb.append(" from ");
		sb.append(" party_meeting_attendance PMA,attendance A,");
		sb.append(" party_meeting_invitee PMI,party_meeting PM,");
		sb.append(" party_meeting_type PMT,party_meeting_main_type PMMT,");
		sb.append(" tdp_cadre TC,user_address UA,district D, ");
		sb.append(" party_meeting_session PMS ");
		sb.append(" where ");
		sb.append(" PMA.attendance_id = A.attendance_id and ");
		sb.append(" A.tdp_cadre_id = PMI.tdp_cadre_id and ");
		sb.append(" PMI.tdp_cadre_id = TC.tdp_cadre_id and ");
		sb.append(" PMI.party_meeting_id = PMA.party_meeting_id and ");
		sb.append(" PMA.party_meeting_id = PM.party_meeting_id and ");
		sb.append(" PMA.party_meeting_session_id = PMS.party_meeting_session_id and ");
		sb.append(" PM.party_meeting_type_id = PMT.party_meeting_type_id and ");
		sb.append(" PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and ");
		sb.append(" TC.address_id = UA.user_address_id and ");
		sb.append(" UA.district_id = D.district_id and PM.is_active='Y' and  ");
		sb.append(" PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and ");
		sb.append(" TC.is_deleted = 'N' and TC.enrollment_year = 2014 and ");
		sb.append(" date(PM.start_date) between :fromDate and :toDate and ");
		if(inputVO.getStateId().longValue() == 1L){
			sb.append(" D.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") and ");
		}else{
			sb.append(" D.district_id in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") and ");
		}
		sb.append(" PMT.party_meeting_type_id in (:partyMeetingTypeId) and ");
		sb.append(" PM.party_meeting_id = :partyMeetingId ");
		sb.append(" group by  PMS.session_type_id,TC.tdp_cadre_id ");
		sb.append(" order by PMS.session_type_id,D.district_id; ");
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("districtId", Hibernate.LONG)
				.addScalar("districtName", Hibernate.STRING)
				.addScalar("cadreId", Hibernate.LONG)
				.addScalar("name", Hibernate.STRING)
				.addScalar("time", Hibernate.STRING)
				.addScalar("sessionId", Hibernate.LONG);
		query.setDate("fromDate",inputVO.getStartDate());
		query.setDate("toDate",inputVO.getEndDate());
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
		query.setParameterList("partyMeetingTypeId",inputVO.getPartyMeetingTypeIds());
		query.setParameter("partyMeetingId",inputVO.getPartyMeetingId());
		return query.list();
	}
	public List<Object[]> getDistrictWiseAttendedCountForPartyMeetingId(PartyMeetingsInputVO inputVO){
		StringBuilder sb = new StringBuilder();     
		sb.append(" select  distinct " +
				  " D.district_id as districtId, " +//0
				  " D.district_name as districtName, " +//1
				  " TC.tdp_cadre_id as cadreId, " +//2
				  " TC.first_name as name, " +//3
				  " min(time(A.attended_time)) as time, " +//4
				  " PMS.session_type_id as sessionId");//5
		sb.append(" from ");
		sb.append(" party_meeting_attendance PMA,attendance A,");
		sb.append(" party_meeting PM,");
		sb.append(" party_meeting_type PMT,party_meeting_main_type PMMT,");
		sb.append(" tdp_cadre TC,user_address UA,district D, ");
		sb.append(" party_meeting_session PMS ");
		sb.append(" where ");
		sb.append(" PMA.attendance_id = A.attendance_id and ");
		sb.append(" A.tdp_cadre_id = TC.tdp_cadre_id and ");
		sb.append(" PMA.party_meeting_id = PM.party_meeting_id and ");
		sb.append(" PMA.party_meeting_session_id = PMS.party_meeting_session_id and ");
		sb.append(" PM.party_meeting_type_id = PMT.party_meeting_type_id and ");
		sb.append(" PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and ");
		sb.append(" TC.address_id = UA.user_address_id and ");
		sb.append(" UA.district_id = D.district_id and PM.is_active='Y' and ");
		sb.append(" PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and ");
		sb.append(" TC.is_deleted = 'N' and TC.enrollment_year = 2014 and ");
		sb.append(" date(PM.start_date) between :fromDate and :toDate and ");
		if(inputVO.getStateId().longValue() == 1L){
			sb.append(" D.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") and ");
		}else{
			sb.append(" D.district_id in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") and ");
		}
		sb.append(" PMT.party_meeting_type_id in (:partyMeetingTypeId) and ");
		sb.append(" PM.party_meeting_id = :partyMeetingId ");
		sb.append(" group by  PMS.session_type_id,TC.tdp_cadre_id ");
		sb.append(" order by PMS.session_type_id,D.district_id; ");
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("districtId", Hibernate.LONG)
				.addScalar("districtName", Hibernate.STRING)
				.addScalar("cadreId", Hibernate.LONG)
				.addScalar("name", Hibernate.STRING)
				.addScalar("time", Hibernate.STRING)
				.addScalar("sessionId", Hibernate.LONG);
		query.setDate("fromDate",inputVO.getStartDate());
		query.setDate("toDate",inputVO.getEndDate());
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
		query.setParameterList("partyMeetingTypeId",inputVO.getPartyMeetingTypeIds());
		query.setParameter("partyMeetingId",inputVO.getPartyMeetingId());
		return query.list();  
	}
	
	public List<Object[]> getDistrictWiseInvitteeAttendedCountForWithoutSessionPartyMeetingId(PartyMeetingsInputVO inputVO){
		StringBuilder sb = new StringBuilder();     
		sb.append(" select  distinct " +
				  " D.district_id as districtId, " +//0
				  " D.district_name as districtName, " +//1
				  " TC.tdp_cadre_id as cadreId, " +//2
				  " TC.first_name as name, " +//3    
				  " min(time(A.attended_time)) as time, " +//4
				  " '0' as sessionId");//5
		sb.append(" from ");
		sb.append(" party_meeting_attendance PMA,attendance A,");
		sb.append(" party_meeting_invitee PMI,party_meeting PM,");
		sb.append(" party_meeting_type PMT,party_meeting_main_type PMMT,");
		sb.append(" tdp_cadre TC,user_address UA,district D ");
	//	sb.append(" party_meeting_session PMS ");
		sb.append(" where ");
		sb.append(" PMA.attendance_id = A.attendance_id and ");
		sb.append(" A.tdp_cadre_id = PMI.tdp_cadre_id and ");
		sb.append(" PMI.tdp_cadre_id = TC.tdp_cadre_id and ");
		sb.append(" PMI.party_meeting_id = PMA.party_meeting_id and ");
		sb.append(" PMA.party_meeting_id = PM.party_meeting_id and ");
		//sb.append(" PMA.party_meeting_session_id = PMS.party_meeting_session_id and ");
		sb.append(" PM.party_meeting_type_id = PMT.party_meeting_type_id and ");
		sb.append(" PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and ");
		sb.append(" TC.address_id = UA.user_address_id and ");
		sb.append(" UA.district_id = D.district_id and ");
		sb.append(" PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and ");
		sb.append(" TC.is_deleted = 'N' and TC.enrollment_year = 2014 and ");
		sb.append(" (date(PM.start_date) between :fromDate and :toDate) and PM.is_active='Y' and ");
		if(inputVO.getStateId().longValue() == 1L){
			sb.append(" D.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") and ");
		}else{
			sb.append(" D.district_id in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") and ");
		}
		sb.append(" PMT.party_meeting_type_id in (:partyMeetingTypeId) and ");
		sb.append(" PM.party_meeting_id = :partyMeetingId ");
		sb.append(" group by  PM.party_meeting_id,TC.tdp_cadre_id ");
		sb.append(" order by PM.party_meeting_id,D.district_id; ");
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("districtId", Hibernate.LONG)
				.addScalar("districtName", Hibernate.STRING)
				.addScalar("cadreId", Hibernate.LONG)
				.addScalar("name", Hibernate.STRING)
				.addScalar("time", Hibernate.STRING)
				.addScalar("sessionId", Hibernate.LONG);
		query.setDate("fromDate",inputVO.getStartDate());
		query.setDate("toDate",inputVO.getEndDate());
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
		query.setParameterList("partyMeetingTypeId",inputVO.getPartyMeetingTypeIds());
		query.setParameter("partyMeetingId",inputVO.getPartyMeetingId());
		return query.list();
	}
	public List<Object[]> getDistrictWiseAttendedCountForWithoutSessionPartyMeetingId(PartyMeetingsInputVO inputVO){
		StringBuilder sb = new StringBuilder();     
		sb.append(" select  distinct " +
				  " D.district_id as districtId, " +//0
				  " D.district_name as districtName, " +//1
				  " TC.tdp_cadre_id as cadreId, " +//2
				  " TC.first_name as name, " +//3
				  " min(time(A.attended_time)) as time, " +//4
				  " '0' as sessionId");//5
		sb.append(" from ");
		sb.append(" party_meeting_attendance PMA,attendance A,");
		sb.append(" party_meeting PM,");
		sb.append(" party_meeting_type PMT,party_meeting_main_type PMMT,");
		sb.append(" tdp_cadre TC,user_address UA,district D ");
		//sb.append(" party_meeting_session PMS ");
		sb.append(" where ");
		sb.append(" PMA.attendance_id = A.attendance_id and ");
		sb.append(" A.tdp_cadre_id = TC.tdp_cadre_id and ");
		sb.append(" PMA.party_meeting_id = PM.party_meeting_id and ");
		//sb.append(" PMA.party_meeting_session_id = PMS.party_meeting_session_id and ");
		sb.append(" PM.party_meeting_type_id = PMT.party_meeting_type_id and ");
		sb.append(" PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and ");
		sb.append(" TC.address_id = UA.user_address_id and ");
		sb.append(" UA.district_id = D.district_id and ");
		sb.append(" PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and ");
		sb.append(" TC.is_deleted = 'N' and TC.enrollment_year = 2014 and ");
		sb.append(" (date(PM.start_date) between :fromDate and :toDate) and PM.is_active='Y' and ");
		if(inputVO.getStateId().longValue() == 1L){
			sb.append(" D.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") and ");
		}else{
			sb.append(" D.district_id in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") and ");
		}
		sb.append(" PMT.party_meeting_type_id in (:partyMeetingTypeId) and ");
		sb.append(" PM.party_meeting_id = :partyMeetingId ");
		sb.append(" group by  PM.party_meeting_id,TC.tdp_cadre_id ");
		sb.append(" order by PM.party_meeting_id,D.district_id; ");
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("districtId", Hibernate.LONG)
				.addScalar("districtName", Hibernate.STRING)
				.addScalar("cadreId", Hibernate.LONG)
				.addScalar("name", Hibernate.STRING)
				.addScalar("time", Hibernate.STRING)
				.addScalar("sessionId", Hibernate.LONG);
		query.setDate("fromDate",inputVO.getStartDate());
		query.setDate("toDate",inputVO.getEndDate());
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
		query.setParameterList("partyMeetingTypeId",inputVO.getPartyMeetingTypeIds());
		query.setParameter("partyMeetingId",inputVO.getPartyMeetingId());
		return query.list();  
	}
	
//santosh
public List<Object[]> getCommitteeWiseInvitedCadreCountForMeeting(PartyMeetingsInputVO inputVO){
	
	StringBuilder sb = new StringBuilder();
	sb.append(" select  distinct tcl.tdp_committee_level_id as tdpCommitteeLevelId,tcl.tdp_committee_level as tdpcommitteeLevel," +
			  "          cadre.tdp_cadre_id as count " +
			  " from    party_meeting_invitee pmi " +
			  "         join party_meeting pm on pmi.party_meeting_id = pm.party_meeting_id " +
			  "         join tdp_cadre cadre on pmi.tdp_cadre_id = cadre.tdp_cadre_id " +
			  "         join user_address ua on pm.meeting_address_id = ua.user_address_id " +
			  "         join party_meeting_type pmt on pm.party_meeting_type_id = pmt.party_meeting_type_id " +
			  "         join party_meeting_main_type pmmt on pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id " +
			  "" +
			  "         join tdp_committee_member tcm  on tcm.tdp_cadre_id = cadre.tdp_cadre_id " +
			  "         join tdp_committee_role   tcr  on tcm.tdp_committee_role_id = tcr.tdp_committee_role_id " +
			  "         join tdp_committee        tc   on tcr.tdp_committee_id = tc.tdp_committee_id" +
			  "         join tdp_committee_level  tcl  on tc.tdp_committee_level_id = tcl.tdp_committee_level_id " +
			  " where   pmmt.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
			  "         cadre.is_deleted = 'N' and cadre.enrollment_year = 2014 and pm.is_active='Y' ");
	
	/*if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
		 sb.append(" and date(pm.start_date) between :startDate and :endDate ");	 
		 
	}*/
	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
		sb.append(" and ua.state_id = :stateId ");
	}
	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
		sb.append(" and pmt.party_meeting_type_id in (:partyMeetingTypeIds) ");	
	}
	if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size() > 0l){
		sb.append(" and pm.party_meeting_id in(:partyMeetingIds) ");
	}
	
	if(inputVO.getCategory().equalsIgnoreCase("Committees")){
		sb.append(" and tcl.tdp_committee_level_id in (:tdpCommitteeLevelIds) ");
	}else if(inputVO.getCategory().equalsIgnoreCase("other")){
		sb.append(" and tcl.tdp_committee_level_id not in (:tdpCommitteeLevelIds) ");
	} 
	
	Query query = getSession().createSQLQuery(sb.toString())
	.addScalar("tdpCommitteeLevelId",Hibernate.LONG)
	.addScalar("tdpcommitteeLevel",Hibernate.STRING)
	.addScalar("count",Hibernate.LONG);
	
	/*if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
		query.setDate("startDate",inputVO.getStartDate());
		query.setDate("endDate",inputVO.getEndDate());	 
	}*/
	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){  
		query.setParameter("stateId",inputVO.getStateId());
	}
	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
		query.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
	}
	if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size() > 0l){
		query.setParameterList("partyMeetingIds",inputVO.getPartyMeetingIds());
	}
	query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
	if(inputVO.getCategory().equalsIgnoreCase("Committees")){
		query.setParameterList("tdpCommitteeLevelIds",inputVO.getCategoryIdList());
	}else if(inputVO.getCategory().equalsIgnoreCase("other")){
		query.setParameterList("tdpCommitteeLevelIds",inputVO.getCategoryIdList());
	}  
   return query.list();	  
}
public List<Object[]> getCommitteeWiseInvitteeAttendedCadreCountForMeeting(PartyMeetingsInputVO inputVO){
	
	StringBuilder sb = new StringBuilder();
	
	sb.append(" select  distinct tcl.tdp_committee_level_id as tdpCommitteeLevelId ,tcl.tdp_committee_level as tdpcommitteeLevel,st.session_type_id as sessionTypeId" +
			"             ,cadre.tdp_cadre_id as tdpCadre,min(time(a.attended_time)) as attendedTime " +
			"   from    party_meeting_attendance pma  " +
			"           join attendance a on pma.attendance_id = a.attendance_id " +
			"           join tdp_cadre cadre on a.tdp_cadre_id = cadre.tdp_cadre_id " +
			"           join party_meeting pm on pma.party_meeting_id = pm.party_meeting_id " +
			"           join party_meeting_invitee pmi on pmi.party_meeting_id = pm.party_meeting_id and pmi.tdp_cadre_id = cadre.tdp_cadre_id " +
			"           join user_address ua on pm.meeting_address_id = ua.user_address_id " +
			"           join party_meeting_type pmt on pm.party_meeting_type_id = pmt.party_meeting_type_id " +
			"           join party_meeting_main_type pmmt on pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id" +
		    "			join party_meeting_session pms on pms.party_meeting_session_id = pma.party_meeting_session_id"+ 
            "			join session_type st on pms.session_type_id = st.session_type_id"+
      	"" +
			"           join tdp_committee_member tcm  on tcm.tdp_cadre_id = cadre.tdp_cadre_id " +
			"           join tdp_committee_role   tcr  on tcm.tdp_committee_role_id = tcr.tdp_committee_role_id " +
			"           join tdp_committee        tc   on tcr.tdp_committee_id = tc.tdp_committee_id " +
			"           join tdp_committee_level  tcl  on tc.tdp_committee_level_id = tcl.tdp_committee_level_id " +
			" where     pmmt.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
			"           cadre.is_deleted = 'N' and cadre.enrollment_year = 2014 and pm.is_active='Y' ");
	
	if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
		 sb.append(" and date(pm.start_date) between :startDate and :endDate ");	 
	}
	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
		sb.append(" and ua.state_id = :stateId ");
	}
	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
		sb.append(" and pmt.party_meeting_type_id in (:partyMeetingTypeIds) ");	
	}
	if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size() > 0l){
		sb.append(" and pm.party_meeting_id in(:partyMeetingIds) ");
	}
	
	sb.append(" group by tcl.tdp_committee_level_id,concat(st.session_type_id,'-',cadre.tdp_cadre_id) ");
	
	Query query = getSession().createSQLQuery(sb.toString())
	.addScalar("tdpCommitteeLevelId",Hibernate.LONG)
	.addScalar("tdpcommitteeLevel",Hibernate.STRING)
	.addScalar("sessionTypeId",Hibernate.LONG)
	.addScalar("tdpCadre",Hibernate.LONG)
	.addScalar("attendedTime",Hibernate.STRING);
	
	if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
		query.setDate("startDate",inputVO.getStartDate());
		query.setDate("endDate",inputVO.getEndDate());	 
	}
	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
		query.setParameter("stateId",inputVO.getStateId());
	}
	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
		query.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
	}
	if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size() > 0l){
		query.setParameterList("partyMeetingIds",inputVO.getPartyMeetingIds());
	}
	query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
   return query.list();
	
}


public List<Object[]> getWithoutCommitteeWiseInvitteeAttendedCadreCountForMeeting(PartyMeetingsInputVO inputVO){
	
	StringBuilder sb = new StringBuilder();
	
	sb.append(" select  distinct tcl.tdp_committee_level_id as tdpCommitteeLevelId ,tcl.tdp_committee_level as tdpcommitteeLevel,0 as sessionTypeId" +
			"             ,cadre.tdp_cadre_id as tdpCadre,min(time(a.attended_time)) as attendedTime " +
			"   from    party_meeting_attendance pma  " +
			"           join attendance a on pma.attendance_id = a.attendance_id " +
			"           join tdp_cadre cadre on a.tdp_cadre_id = cadre.tdp_cadre_id " +
			"           join party_meeting pm on pma.party_meeting_id = pm.party_meeting_id " +
			"           join party_meeting_invitee pmi on pmi.party_meeting_id = pm.party_meeting_id and pmi.tdp_cadre_id = cadre.tdp_cadre_id " +
			"           join user_address ua on pm.meeting_address_id = ua.user_address_id " +
			"           join party_meeting_type pmt on pm.party_meeting_type_id = pmt.party_meeting_type_id " +
			"           join party_meeting_main_type pmmt on pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id" +
		  //  "			join party_meeting_session pms on pms.party_meeting_session_id = pma.party_meeting_session_id"+ 
          //  "			join session_type st on pms.session_type_id = st.session_type_id"+
			"           join tdp_committee_member tcm  on tcm.tdp_cadre_id = cadre.tdp_cadre_id " +
			"           join tdp_committee_role   tcr  on tcm.tdp_committee_role_id = tcr.tdp_committee_role_id " +
			"           join tdp_committee        tc   on tcr.tdp_committee_id = tc.tdp_committee_id " +
			"           join tdp_committee_level  tcl  on tc.tdp_committee_level_id = tcl.tdp_committee_level_id " +
			" where     pmmt.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
			"           cadre.is_deleted = 'N' and cadre.enrollment_year = 2014 and pm.is_active='Y' and pma.party_meeting_session_id is null  ");
	
	if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
		 sb.append(" and date(pm.start_date) between :startDate and :endDate ");	 
	}
	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
		sb.append(" and ua.state_id = :stateId ");
	}
	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
		sb.append(" and pmt.party_meeting_type_id in (:partyMeetingTypeIds) ");	
	}
	if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size() > 0l){
		sb.append(" and pm.party_meeting_id in(:partyMeetingIds) ");
	}
	
	sb.append(" group by tcl.tdp_committee_level_id,concat(pm.party_meeting_id,'-',cadre.tdp_cadre_id) ");
	
	Query query = getSession().createSQLQuery(sb.toString())
	.addScalar("tdpCommitteeLevelId",Hibernate.LONG)
	.addScalar("tdpcommitteeLevel",Hibernate.STRING)
	.addScalar("sessionTypeId",Hibernate.LONG)
	.addScalar("tdpCadre",Hibernate.LONG)
	.addScalar("attendedTime",Hibernate.STRING);
	
	if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
		query.setDate("startDate",inputVO.getStartDate());
		query.setDate("endDate",inputVO.getEndDate());	 
	}
	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
		query.setParameter("stateId",inputVO.getStateId());
	}
	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
		query.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
	}
	if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size() > 0l){
		query.setParameterList("partyMeetingIds",inputVO.getPartyMeetingIds());
	}
	query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
   return query.list();
	
}

public List<Object[]> getPublicRepresentativeWiseInvitedCadreCountForMeeting(PartyMeetingsInputVO inputVO){
	
	StringBuilder sb = new StringBuilder();
	
	sb.append(" select  distinct prt.public_representative_type_id as representativeTypeId,prt.position as position," +
			"            cadre.tdp_cadre_id as count " +
			"   from    party_meeting_invitee pmi " +
			"           join party_meeting pm on pmi.party_meeting_id = pm.party_meeting_id" +
			"           join tdp_cadre cadre on pmi.tdp_cadre_id = cadre.tdp_cadre_id" +
			"           join user_address ua on pm.meeting_address_id = ua.user_address_id" +
			"           join party_meeting_type pmt on pm.party_meeting_type_id = pmt.party_meeting_type_id" +
			"           join party_meeting_main_type pmmt on pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id" +
			"" +
			"           join tdp_cadre_candidate tcc  on tcc.tdp_cadre_id = cadre.tdp_cadre_id" +
			"           join candidate c on tcc.candidate_id = c.candidate_id" +
			"           join public_representative pr on c.candidate_id = pr.candidate_id" +
			"           join public_representative_type prt on pr.public_representative_type_id = prt.public_representative_type_id " +
			"  where    pmmt.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
			"           cadre.is_deleted = 'N' and cadre.enrollment_year = 2014 and pm.is_active='Y' ");
	
	/*if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
		 sb.append(" and date(pm.start_date) between :startDate and :endDate ");	 
	}*/
	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
		sb.append(" and ua.state_id = :stateId ");
	}
	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
		sb.append(" and pmt.party_meeting_type_id in (:partyMeetingTypeIds) ");	
	}
	if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size() > 0l){
		sb.append(" and pm.party_meeting_id in(:partyMeetingIds) ");
	}
	//sb.append(" group by prt.public_representative_type_id,cadre.tdp_cadre_id ");
	if(inputVO.getCategory().equalsIgnoreCase("Representative")){
		sb.append(" and prt.public_representative_type_id in (:publicRepresentativeTypeIds) ");
	}else if(inputVO.getCategory().equalsIgnoreCase("other")){
		sb.append(" and prt.public_representative_type_id not in (:publicRepresentativeTypeIds) ");
	} 
	Query query = getSession().createSQLQuery(sb.toString())
	.addScalar("representativeTypeId",Hibernate.LONG)
	.addScalar("position",Hibernate.STRING)
	.addScalar("count",Hibernate.LONG);
	
	/*if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
		query.setDate("startDate",inputVO.getStartDate());
		query.setDate("endDate",inputVO.getEndDate());	 
	}*/
	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
		query.setParameter("stateId",inputVO.getStateId());
	}
	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
		query.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
	}
	if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size() > 0l){
		query.setParameterList("partyMeetingIds",inputVO.getPartyMeetingIds());
	}
	query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
	if(inputVO.getCategory().equalsIgnoreCase("Representative")){
		query.setParameterList("publicRepresentativeTypeIds",inputVO.getCategoryIdList());
	}else if(inputVO.getCategory().equalsIgnoreCase("other")){
		query.setParameterList("publicRepresentativeTypeIds",inputVO.getCategoryIdList());
	}
   return query.list();
}
    public List<Object[]> getPublicRepresentativeWiseInvitteeAttendedCadreCountForMeetings(PartyMeetingsInputVO inputVO){
	
	StringBuilder sb = new StringBuilder();
	sb.append(" select    prt.public_representative_type_id as representativeTypeId ,prt.position as position," +
			"             st.session_type_id as sessionTypeId," +
			"             cadre.tdp_cadre_id as tdpCadre,min(time(a.attended_time)) as attendedTime " +
			"   from      party_meeting_attendance pma " +
			"             join attendance a on pma.attendance_id = a.attendance_id " +
			"             join tdp_cadre cadre on a.tdp_cadre_id = cadre.tdp_cadre_id " +
			"             join party_meeting pm on pma.party_meeting_id = pm.party_meeting_id " +
			"             join party_meeting_invitee pmi on pmi.party_meeting_id = pm.party_meeting_id and pmi.tdp_cadre_id = cadre.tdp_cadre_id " +
			"             join user_address ua on pm.meeting_address_id = ua.user_address_id" +
			"             join party_meeting_type pmt on pm.party_meeting_type_id = pmt.party_meeting_type_id " +
			"             join party_meeting_main_type pmmt on pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id" +
			"		      join party_meeting_session pms on pms.party_meeting_session_id = pma.party_meeting_session_id"+ 
	        "		      join session_type st on pms.session_type_id = st.session_type_id"+
		
			"" +
			"             join tdp_cadre_candidate tcc  on tcc.tdp_cadre_id = cadre.tdp_cadre_id" +
			"             join candidate c on tcc.candidate_id = c.candidate_id" +
			"             join public_representative pr on c.candidate_id = pr.candidate_id " +
			"             join public_representative_type prt on pr.public_representative_type_id = prt.public_representative_type_id " +
			"   where     pmmt.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
			"             cadre.is_deleted = 'N' and cadre.enrollment_year = 2014 and pm.is_active='Y' ");
	if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
		 sb.append(" and date(pm.start_date) between :startDate and :endDate ");	 
	}
	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
		sb.append(" and ua.state_id = :stateId ");
	}
	if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size() > 0l){
		sb.append(" and pm.party_meeting_id in(:partyMeetingIds) ");
	}
	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
		sb.append(" and pmt.party_meeting_type_id in (:partyMeetingTypeIds) ");	
	}
	sb.append(" group by prt.public_representative_type_id,concat(st.session_type_id,'-',cadre.tdp_cadre_id) ");
	
	Query query = getSession().createSQLQuery(sb.toString())
	.addScalar("representativeTypeId",Hibernate.LONG)
	.addScalar("position",Hibernate.STRING)
	.addScalar("sessionTypeId",Hibernate.LONG)
	.addScalar("tdpCadre",Hibernate.LONG)
	.addScalar("attendedTime",Hibernate.STRING);
	
	if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
		query.setDate("startDate",inputVO.getStartDate());
		query.setDate("endDate",inputVO.getEndDate());	 
	}
	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
		query.setParameter("stateId",inputVO.getStateId());
	}
	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
		query.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
	}
	if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size() > 0l){
		query.setParameterList("partyMeetingIds",inputVO.getPartyMeetingIds());
	}
	query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
   return query.list();
}
    
    public List<Object[]> getWithoutSessionPublicRepresentativeWiseInvitteeAttendedCadreCountForMeetings(PartyMeetingsInputVO inputVO){
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(" select    prt.public_representative_type_id as representativeTypeId ,prt.position as position," +
    			"             pma.party_meeting_session_id as sessionTypeId," +
    			"             cadre.tdp_cadre_id as tdpCadre,min(time(a.attended_time)) as attendedTime " +
    			"   from      party_meeting_attendance pma " +
    			"             join attendance a on pma.attendance_id = a.attendance_id " +
    			"             join tdp_cadre cadre on a.tdp_cadre_id = cadre.tdp_cadre_id " +
    			"             join party_meeting pm on pma.party_meeting_id = pm.party_meeting_id " +
    			"             join party_meeting_invitee pmi on pmi.party_meeting_id = pm.party_meeting_id and pmi.tdp_cadre_id = cadre.tdp_cadre_id " +
    			"             join user_address ua on pm.meeting_address_id = ua.user_address_id" +
    			"             join party_meeting_type pmt on pm.party_meeting_type_id = pmt.party_meeting_type_id " +
    			"             join party_meeting_main_type pmmt on pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id" +
    			//"		      join party_meeting_session pms on pms.party_meeting_session_id = pma.party_meeting_session_id"+ 
    	       // "		      join session_type st on pms.session_type_id = st.session_type_id"+
    		
    			"" +
    			"             join tdp_cadre_candidate tcc  on tcc.tdp_cadre_id = cadre.tdp_cadre_id" +
    			"             join candidate c on tcc.candidate_id = c.candidate_id" +
    			"             join public_representative pr on c.candidate_id = pr.candidate_id " +
    			"             join public_representative_type prt on pr.public_representative_type_id = prt.public_representative_type_id " +
    			"   where     pmmt.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
    			"             cadre.is_deleted = 'N' and cadre.enrollment_year = 2014 and pm.is_active='Y' and pma.party_meeting_session_id is null  ");
    	if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
    		 sb.append(" and date(pm.start_date) between :startDate and :endDate ");	 
    	}
    	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
    		sb.append(" and ua.state_id = :stateId ");
    	}
    	if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size() > 0l){
    		sb.append(" and pm.party_meeting_id in(:partyMeetingIds) ");
    	}
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
    		sb.append(" and pmt.party_meeting_type_id in (:partyMeetingTypeIds) ");	
    	}
    	sb.append(" group by prt.public_representative_type_id,concat(pm.party_meeting_id,'-',cadre.tdp_cadre_id) ");
    	
    	Query query = getSession().createSQLQuery(sb.toString())
    	.addScalar("representativeTypeId",Hibernate.LONG)
    	.addScalar("position",Hibernate.STRING)
    	.addScalar("sessionTypeId",Hibernate.LONG)
    	.addScalar("tdpCadre",Hibernate.LONG)
    	.addScalar("attendedTime",Hibernate.STRING);
    	
    	if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
    		query.setDate("startDate",inputVO.getStartDate());
    		query.setDate("endDate",inputVO.getEndDate());	 
    	}
    	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
    		query.setParameter("stateId",inputVO.getStateId());
    	}
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
    		query.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
    	}
    	if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size() > 0l){
    		query.setParameterList("partyMeetingIds",inputVO.getPartyMeetingIds());
    	}
    	query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
       return query.list();
    }
    
    public List<Object[]> getDistrictWiseInvitedCountForPartyMeetingIdForSession(PartyMeetingsInputVO inputVO){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct ");
		
		sb.append(" PM.party_meeting_id as partyMeetingId ," +//0
				  " '' as districtId ," +//1
				  " '' as districtName ," +//2
				  " TC.tdp_cadre_id as cadreId");//3
		
		sb.append(" from   " +
				  " party_meeting_invitee PMI," +
				  " party_meeting PM," +
				  " party_meeting_type PMT," +
				  " party_meeting_main_type PMMT," +  
				  " tdp_cadre TC,user_address UA, district D " +
				  " where  PMI.tdp_cadre_id = TC.tdp_cadre_id and " +
				  " PMI.party_meeting_id = PM.party_meeting_id and " +
				  " PM.party_meeting_type_id = PMT.party_meeting_type_id and " +
				  " PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and " +
				  " TC.address_id = UA.user_address_id and " +
				  " UA.district_id = D.district_id and " +
				  " PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
				  " TC.is_deleted = 'N' and TC.enrollment_year = 2014 and PM.is_active='Y' and ");
	
		if(inputVO.getDistId().longValue() > 0L){
			sb.append(" D.district_id = :districtId ");
			if(inputVO.getStateId().longValue()==1l){
				sb.append(" and UA.state_id = 1 ");	
			}else if(inputVO.getStateId().longValue()==36l){
				sb.append(" and UA.state_id = 36 ");	
			}
		}else{
			if(inputVO.getStateId().longValue()==1l){
				sb.append(" D.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
			}else if(inputVO.getStateId().longValue()==36l){
				sb.append(" UA.state_id = 36 ");		
			}
		}
		
		
		
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			sb.append(" and PMT.party_meeting_type_id in (:partyMeetingTypeIds) ");	
		}
		sb.append(" and PM.party_meeting_id = :partyMeetingId ");
		Session session = getSession();
		SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
		sqlQuery.addScalar("partyMeetingId",Hibernate.LONG);
		sqlQuery.addScalar("districtId",Hibernate.LONG);
		sqlQuery.addScalar("districtName",Hibernate.STRING);
		sqlQuery.addScalar("cadreId",Hibernate.LONG); 
		
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			sqlQuery.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
		}
		sqlQuery.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
		sqlQuery.setParameter("partyMeetingId",inputVO.getPartyMeetingId()); 
		if(inputVO.getDistId().longValue() > 0L){
			sqlQuery.setParameter("districtId",inputVO.getDistId()); 
		}
		return sqlQuery.list();  
	}//sb.append(" UA.district_id = D.district_id and ");
    public List<Object[]> getDistrictWiseInvitteeAttendedCountForPartyMeetingIdForSession(PartyMeetingsInputVO inputVO){
		StringBuilder sb = new StringBuilder();     
		sb.append(" select  distinct " +
				  " '' as districtId, " +//0
				  " '' as districtName, " +//1
				  " TC.tdp_cadre_id as cadreId, " +//2
				  " TC.first_name as name, " +//3    
				  " min(time(A.attended_time)) as time, " +//4
				  " PMS.party_meeting_session_id as sessionId");//5
		sb.append(" from ");
		sb.append(" party_meeting_attendance PMA,attendance A,");
		sb.append(" party_meeting_invitee PMI,party_meeting PM,");
		sb.append(" party_meeting_type PMT,party_meeting_main_type PMMT,");
		sb.append(" tdp_cadre TC,user_address UA, ");
		sb.append(" party_meeting_session PMS, district D ");
		sb.append(" where ");
		sb.append(" PMA.attendance_id = A.attendance_id and ");
		sb.append(" A.tdp_cadre_id = PMI.tdp_cadre_id and ");
		sb.append(" PMI.tdp_cadre_id = TC.tdp_cadre_id and ");
		sb.append(" PMI.party_meeting_id = PMA.party_meeting_id and ");
		sb.append(" PMA.party_meeting_id = PM.party_meeting_id and ");
		sb.append(" PMA.party_meeting_session_id = PMS.party_meeting_session_id and ");
		sb.append(" PM.party_meeting_type_id = PMT.party_meeting_type_id and ");
		sb.append(" PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and ");
		sb.append(" TC.address_id = UA.user_address_id and UA.district_id = D.district_id and ");
		sb.append(" PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and ");
		sb.append(" TC.is_deleted = 'N' and TC.enrollment_year = 2014 and ");
		sb.append(" date(PM.start_date) between :fromDate and :toDate and PM.is_active='Y' and ");
		if(inputVO.getDistId().longValue() > 0L){
			sb.append(" D.district_id = :districtId ");
			if(inputVO.getStateId().longValue()==1l){
				sb.append(" and UA.state_id = 1 and ");	
			}else if(inputVO.getStateId().longValue()==36l){
				sb.append(" and UA.state_id = 36 and ");	
			}
		}else{
			if(inputVO.getStateId().longValue()==1l){
				sb.append(" D.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") and ");
			}else if(inputVO.getStateId().longValue()==36l){
				sb.append(" UA.state_id = 36 and ");		
			}
		}
		if(inputVO.getPartyMeetingTypeIds() != null && !inputVO.getPartyMeetingTypeIds().isEmpty())
			sb.append(" PMT.party_meeting_type_id in (:partyMeetingTypeId) and ");
		sb.append(" PM.party_meeting_id = :partyMeetingId ");
		sb.append(" group by  PMS.session_type_id,TC.tdp_cadre_id ");
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("districtId", Hibernate.LONG)
				.addScalar("districtName", Hibernate.STRING)
				.addScalar("cadreId", Hibernate.LONG)
				.addScalar("name", Hibernate.STRING)
				.addScalar("time", Hibernate.STRING)
				.addScalar("sessionId", Hibernate.LONG);
		query.setDate("fromDate",inputVO.getStartDate());
		query.setDate("toDate",inputVO.getEndDate());
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
		if(inputVO.getPartyMeetingTypeIds() != null && !inputVO.getPartyMeetingTypeIds().isEmpty())
			query.setParameterList("partyMeetingTypeId",inputVO.getPartyMeetingTypeIds());
		query.setParameter("partyMeetingId",inputVO.getPartyMeetingId());
		if(inputVO.getDistId().longValue() > 0L){
			query.setParameter("districtId",inputVO.getDistId()); 
		}
		return query.list();
	}
    
    
    public List<Object[]> getWithoutSessionDistrictWiseInvitteeAttendedCountForPartyMeetingIdForSession(PartyMeetingsInputVO inputVO){
		StringBuilder sb = new StringBuilder();     
		sb.append(" select  distinct " +
				  " '' as districtId, " +//0
				  " '' as districtName, " +//1
				  " TC.tdp_cadre_id as cadreId, " +//2
				  " TC.first_name as name, " +//3    
				  " min(time(A.attended_time)) as time, " +//4
				  " 0 as sessionId");//5
		sb.append(" from ");
		sb.append(" party_meeting_attendance PMA,attendance A,");
		sb.append(" party_meeting_invitee PMI,party_meeting PM,");
		sb.append(" party_meeting_type PMT,party_meeting_main_type PMMT,");
		sb.append(" tdp_cadre TC,user_address UA, ");
		sb.append(" district D ");
		sb.append(" where ");
		sb.append(" PMA.attendance_id = A.attendance_id and ");
		sb.append(" A.tdp_cadre_id = PMI.tdp_cadre_id and ");
		sb.append(" PMI.tdp_cadre_id = TC.tdp_cadre_id and ");
		sb.append(" PMI.party_meeting_id = PMA.party_meeting_id and ");
		sb.append(" PMA.party_meeting_id = PM.party_meeting_id and ");
		sb.append(" PM.party_meeting_type_id = PMT.party_meeting_type_id and ");
		sb.append(" PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and ");
		sb.append(" TC.address_id = UA.user_address_id and UA.district_id = D.district_id and ");
		sb.append(" PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and ");
		sb.append(" TC.is_deleted = 'N' and TC.enrollment_year = 2014 and ");
		sb.append(" date(PM.start_date) between :fromDate and :toDate and PM.is_active='Y' and ");
		if(inputVO.getDistId().longValue() > 0L){
			sb.append(" D.district_id = :districtId ");
			if(inputVO.getStateId().longValue()==1l){
				sb.append(" and UA.state_id = 1 and ");	
			}else if(inputVO.getStateId().longValue()==36l){
				sb.append(" and UA.state_id = 36 and ");	
			}
		}else{
			if(inputVO.getStateId().longValue()==1l){
				sb.append(" D.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") and ");
			}else if(inputVO.getStateId().longValue()==36l){
				sb.append(" UA.state_id = 36 and ");		
			}
		}
		sb.append(" PMT.party_meeting_type_id in (:partyMeetingTypeId) and ");
		sb.append(" PM.party_meeting_id = :partyMeetingId ");
		sb.append(" group by  PM.party_meeting_id,TC.tdp_cadre_id ");
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("districtId", Hibernate.LONG)
				.addScalar("districtName", Hibernate.STRING)
				.addScalar("cadreId", Hibernate.LONG)
				.addScalar("name", Hibernate.STRING)
				.addScalar("time", Hibernate.STRING)
				.addScalar("sessionId", Hibernate.LONG);
		query.setDate("fromDate",inputVO.getStartDate());
		query.setDate("toDate",inputVO.getEndDate());
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
		query.setParameterList("partyMeetingTypeId",inputVO.getPartyMeetingTypeIds());
		query.setParameter("partyMeetingId",inputVO.getPartyMeetingId());
		if(inputVO.getDistId().longValue() > 0L){
			query.setParameter("districtId",inputVO.getDistId()); 
		}
		return query.list();
	}
    public List<Object[]> getDistrictWiseAttendedCountForPartyMeetingIdForSession(PartyMeetingsInputVO inputVO){
		StringBuilder sb = new StringBuilder();     
		sb.append(" select  distinct " +
				  " '' as districtId, " +//0
				  " '' as districtName, " +//1
				  " TC.tdp_cadre_id as cadreId, " +//2
				  " TC.first_name as name, " +//3
				  " min(time(A.attended_time)) as time, " +//4
				  " PMS.party_meeting_session_id as sessionId, " +//5
				  " date(A.attended_time) as date, " +//6
				  " PMS.session_type_id as sessionTypeId ");  //7
		sb.append(" from ");
		sb.append(" party_meeting_attendance PMA,attendance A,");
		sb.append(" party_meeting PM,");
		sb.append(" party_meeting_type PMT,party_meeting_main_type PMMT,");
		sb.append(" tdp_cadre TC,user_address UA, ");
		sb.append(" party_meeting_session PMS, district D ");
		sb.append(" where ");
		sb.append(" PMA.attendance_id = A.attendance_id and ");
		sb.append(" A.tdp_cadre_id = TC.tdp_cadre_id and ");
		sb.append(" PMA.party_meeting_id = PM.party_meeting_id and ");
		sb.append(" PMA.party_meeting_session_id = PMS.party_meeting_session_id and ");
		sb.append(" PM.party_meeting_type_id = PMT.party_meeting_type_id and ");
		sb.append(" PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and ");
		sb.append(" TC.address_id = UA.user_address_id and UA.district_id = D.district_id and ");
		sb.append(" PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and ");
		sb.append(" TC.is_deleted = 'N' and TC.enrollment_year = 2014 and ");
		sb.append(" date(PM.start_date) between :fromDate and :toDate and PM.is_active='Y' and ");
		if(inputVO.getDistId().longValue() > 0L){
			sb.append(" D.district_id = :districtId ");
			if(inputVO.getStateId().longValue()==1l){
				sb.append(" and UA.state_id = 1 and ");	
			}else if(inputVO.getStateId().longValue()==36l){
				sb.append(" and UA.state_id = 36 and ");	
			}
		}else{
			if(inputVO.getStateId().longValue()==1l){
				sb.append(" D.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") and ");
			}else if(inputVO.getStateId().longValue()==36l){
				sb.append(" UA.state_id = 36 and "); 		
			}
		}
		if(inputVO.getPartyMeetingTypeIds() != null && !inputVO.getPartyMeetingTypeIds().isEmpty())
			sb.append(" PMT.party_meeting_type_id in (:partyMeetingTypeId) and ");
		sb.append(" PM.party_meeting_id = :partyMeetingId ");      
		sb.append(" group by  PMS.session_type_id,TC.tdp_cadre_id ");
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("districtId", Hibernate.LONG)
				.addScalar("districtName", Hibernate.STRING)
				.addScalar("cadreId", Hibernate.LONG)
				.addScalar("name", Hibernate.STRING)
				.addScalar("time", Hibernate.STRING)
				.addScalar("sessionId", Hibernate.LONG)
				.addScalar("date", Hibernate.STRING)
				.addScalar("sessionTypeId", Hibernate.LONG);  
		query.setDate("fromDate",inputVO.getStartDate());
		query.setDate("toDate",inputVO.getEndDate());
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
		if(inputVO.getPartyMeetingTypeIds() != null && !inputVO.getPartyMeetingTypeIds().isEmpty())
			query.setParameterList("partyMeetingTypeId",inputVO.getPartyMeetingTypeIds());
		query.setParameter("partyMeetingId",inputVO.getPartyMeetingId());
		if(inputVO.getDistId().longValue() > 0L){
			query.setParameter("districtId",inputVO.getDistId());   
		}
		return query.list();  
	}
    
    public List<Object[]> getWithoutSessionDistrictWiseAttendedCountForPartyMeetingIdForSession(PartyMeetingsInputVO inputVO){
		StringBuilder sb = new StringBuilder();     
		sb.append(" select  distinct " +
				  " '' as districtId, " +//0
				  " '' as districtName, " +//1
				  " TC.tdp_cadre_id as cadreId, " +//2
				  " TC.first_name as name, " +//3
				  " min(time(A.attended_time)) as time, " +//4
				  " 0 as sessionId, " +//5
				  " date(A.attended_time) as date, " +//6
				  " 0 as sessionTypeId ");  //7
		sb.append(" from ");
		sb.append(" party_meeting_attendance PMA,attendance A,");
		sb.append(" party_meeting PM,");
		sb.append(" party_meeting_type PMT,party_meeting_main_type PMMT,");
		sb.append(" tdp_cadre TC,user_address UA, ");
		sb.append(" district D ");
		sb.append(" where ");
		sb.append(" PMA.attendance_id = A.attendance_id and ");
		sb.append(" A.tdp_cadre_id = TC.tdp_cadre_id and ");
		sb.append(" PMA.party_meeting_id = PM.party_meeting_id and ");
		//sb.append(" PMA.party_meeting_session_id = PMS.party_meeting_session_id and ");
		sb.append(" PM.party_meeting_type_id = PMT.party_meeting_type_id and ");
		sb.append(" PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and ");
		sb.append(" TC.address_id = UA.user_address_id and UA.district_id = D.district_id and ");
		sb.append(" PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and ");
		sb.append(" TC.is_deleted = 'N' and TC.enrollment_year = 2014 and ");
		sb.append(" date(PM.start_date) between :fromDate and :toDate and PM.is_active='Y' and ");
		if(inputVO.getDistId().longValue() > 0L){
			sb.append(" D.district_id = :districtId ");
			if(inputVO.getStateId().longValue()==1l){
				sb.append(" and UA.state_id = 1 and ");	
			}else if(inputVO.getStateId().longValue()==36l){
				sb.append(" and UA.state_id = 36 and ");	
			}
		}else{
			if(inputVO.getStateId().longValue()==1l){
				sb.append(" D.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") and ");
			}else if(inputVO.getStateId().longValue()==36l){
				sb.append(" UA.state_id = 36 and "); 		
			}
		}
		sb.append(" PMT.party_meeting_type_id in (:partyMeetingTypeId) and ");
		sb.append(" PM.party_meeting_id = :partyMeetingId ");      
		sb.append(" group by  PM.party_meeting_id,TC.tdp_cadre_id ");
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("districtId", Hibernate.LONG)
				.addScalar("districtName", Hibernate.STRING)
				.addScalar("cadreId", Hibernate.LONG)
				.addScalar("name", Hibernate.STRING)
				.addScalar("time", Hibernate.STRING)
				.addScalar("sessionId", Hibernate.LONG)
				.addScalar("date", Hibernate.STRING)
				.addScalar("sessionTypeId", Hibernate.LONG);  
		query.setDate("fromDate",inputVO.getStartDate());
		query.setDate("toDate",inputVO.getEndDate());
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
		query.setParameterList("partyMeetingTypeId",inputVO.getPartyMeetingTypeIds());
		query.setParameter("partyMeetingId",inputVO.getPartyMeetingId());
		if(inputVO.getDistId().longValue() > 0L){
			query.setParameter("districtId",inputVO.getDistId());   
		}
		return query.list();  
	}
    
    public List<Object[]> getDistrictWiseInvitteeAttendedCountForPartyMeetingIdForWithoutSession(PartyMeetingsInputVO inputVO){
		StringBuilder sb = new StringBuilder();     
		sb.append(" select  distinct " +
				  " '' as districtId, " +//0
				  " '' as districtName, " +//1
				  " TC.tdp_cadre_id as cadreId, " +//2
				  " TC.first_name as name, " +//3    
				  " min(time(A.attended_time)) as time, " +//4
				  " '0' as sessionId");//5
		sb.append(" from ");
		sb.append(" party_meeting_attendance PMA,attendance A,");
		sb.append(" party_meeting_invitee PMI,party_meeting PM,");
		sb.append(" party_meeting_type PMT,party_meeting_main_type PMMT,");
		sb.append(" tdp_cadre TC,user_address UA, ");
		sb.append(" district D ");
		sb.append(" where ");
		sb.append(" PMA.attendance_id = A.attendance_id and ");
		sb.append(" A.tdp_cadre_id = PMI.tdp_cadre_id and ");
		sb.append(" PMI.tdp_cadre_id = TC.tdp_cadre_id and ");
		sb.append(" PMI.party_meeting_id = PMA.party_meeting_id and ");
		sb.append(" PMA.party_meeting_id = PM.party_meeting_id and ");
		//sb.append(" PMA.party_meeting_session_id = PMS.party_meeting_session_id and ");
		sb.append(" PM.party_meeting_type_id = PMT.party_meeting_type_id and ");
		sb.append(" PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and ");
		sb.append(" TC.address_id = UA.user_address_id and UA.district_id = D.district_id and ");
		sb.append(" PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and ");
		sb.append(" TC.is_deleted = 'N' and TC.enrollment_year = 2014 and ");
		sb.append(" ( date(PM.start_date) between :fromDate and :toDate) and PM.is_active='Y' and ");
		if(inputVO.getDistId().longValue() > 0L){
			sb.append(" D.district_id = :districtId ");
			if(inputVO.getStateId().longValue()==1l){
				sb.append(" and UA.state_id = 1 and ");	
			}else if(inputVO.getStateId().longValue()==36l){
				sb.append(" and UA.state_id = 36 and ");	
			}
		}else{
			if(inputVO.getStateId().longValue()==1l){
				sb.append(" D.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") and ");
			}else if(inputVO.getStateId().longValue()==36l){
				sb.append(" UA.state_id = 36 and ");		
			}
		}
		if(inputVO.getPartyMeetingTypeIds() != null && !inputVO.getPartyMeetingTypeIds().isEmpty())
			sb.append(" PMT.party_meeting_type_id in (:partyMeetingTypeId) and ");
		sb.append(" PM.party_meeting_id = :partyMeetingId ");
		sb.append(" group by  PM.party_meeting_id,TC.tdp_cadre_id ");
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("districtId", Hibernate.LONG)
				.addScalar("districtName", Hibernate.STRING)
				.addScalar("cadreId", Hibernate.LONG)
				.addScalar("name", Hibernate.STRING)
				.addScalar("time", Hibernate.STRING)
				.addScalar("sessionId", Hibernate.LONG);
		query.setDate("fromDate",inputVO.getStartDate());
		query.setDate("toDate",inputVO.getEndDate());
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
		if(inputVO.getPartyMeetingTypeIds() != null && !inputVO.getPartyMeetingTypeIds().isEmpty())
			query.setParameterList("partyMeetingTypeId",inputVO.getPartyMeetingTypeIds());
		query.setParameter("partyMeetingId",inputVO.getPartyMeetingId());
		if(inputVO.getDistId().longValue() > 0L){
			query.setParameter("districtId",inputVO.getDistId()); 
		}
		return query.list();
	}
    public List<Object[]> getDistrictWiseAttendedCountForPartyMeetingIdForWithoutSession(PartyMeetingsInputVO inputVO){
		StringBuilder sb = new StringBuilder();     
		sb.append(" select  distinct " +
				  " '' as districtId, " +//0
				  " '' as districtName, " +//1
				  " TC.tdp_cadre_id as cadreId, " +//2
				  " TC.first_name as name, " +//3
				  " min(time(A.attended_time)) as time, " +//4
				  " '0' as sessionId, " +//5
				  " date(A.attended_time) as date ");//6  
		sb.append(" from ");
		sb.append(" party_meeting_attendance PMA,attendance A,");
		sb.append(" party_meeting PM,");
		sb.append(" party_meeting_type PMT,party_meeting_main_type PMMT,");
		sb.append(" tdp_cadre TC,user_address UA, ");
		sb.append("  district D ");
		sb.append(" where ");
		sb.append(" PMA.attendance_id = A.attendance_id and ");
		sb.append(" A.tdp_cadre_id = TC.tdp_cadre_id and ");
		sb.append(" PMA.party_meeting_id = PM.party_meeting_id and ");
		//sb.append(" PMA.party_meeting_session_id = PMS.party_meeting_session_id and ");
		sb.append(" PM.party_meeting_type_id = PMT.party_meeting_type_id and ");
		sb.append(" PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and ");
		sb.append(" TC.address_id = UA.user_address_id and UA.district_id = D.district_id and ");
		sb.append(" PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and ");
		sb.append(" TC.is_deleted = 'N' and TC.enrollment_year = 2014 and ");
		sb.append(" ( date(PM.start_date) between :fromDate and :toDate ) and PM.is_active='Y' and ");
		if(inputVO.getDistId().longValue() > 0L){
			sb.append(" D.district_id = :districtId ");
			if(inputVO.getStateId().longValue()==1l){
				sb.append(" and UA.state_id = 1 and ");	
			}else if(inputVO.getStateId().longValue()==36l){
				sb.append(" and UA.state_id = 36 and ");	
			}
		}else{
			if(inputVO.getStateId().longValue()==1l){
				sb.append(" D.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") and ");
			}else if(inputVO.getStateId().longValue()==36l){
				sb.append(" UA.state_id = 36 and "); 		
			}
		}
		if(inputVO.getPartyMeetingTypeIds() != null && !inputVO.getPartyMeetingTypeIds().isEmpty())
			sb.append(" PMT.party_meeting_type_id in (:partyMeetingTypeId) and ");
		sb.append(" PM.party_meeting_id = :partyMeetingId ");      
		sb.append(" group by  PM.party_meeting_id,TC.tdp_cadre_id ");
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("districtId", Hibernate.LONG)
				.addScalar("districtName", Hibernate.STRING)
				.addScalar("cadreId", Hibernate.LONG)
				.addScalar("name", Hibernate.STRING)
				.addScalar("time", Hibernate.STRING)
				.addScalar("sessionId", Hibernate.LONG)
				.addScalar("date", Hibernate.STRING);
		query.setDate("fromDate",inputVO.getStartDate());
		query.setDate("toDate",inputVO.getEndDate());
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
		if(inputVO.getPartyMeetingTypeIds() != null && !inputVO.getPartyMeetingTypeIds().isEmpty())
			query.setParameterList("partyMeetingTypeId",inputVO.getPartyMeetingTypeIds());
		query.setParameter("partyMeetingId",inputVO.getPartyMeetingId());
		if(inputVO.getDistId().longValue() > 0L){
			query.setParameter("districtId",inputVO.getDistId());   
		}
		return query.list();  
	}
    
    @SuppressWarnings("unchecked")
    public List<Object[]> getObsentReasonList(Long partyMeetingId){
        StringBuilder sb = new StringBuilder();
        sb.append("select distinct model.tdpCadreId, model.absenteeRemark from PartyMeetingInvitee model where model.partyMeetingId = :partyMeetingId and model.partyMeeting.isActive='Y'");
        Query query = getSession().createQuery(sb.toString());
        query.setParameter("partyMeetingId", partyMeetingId);
        return query.list();
    /*StringBuilder sb = new StringBuilder();
    	sb.append("select model.tdpCadreId, model.absenteeRemark from PartyMeetingInvitee model where model.partyMeetingId = 479287");
    	Query query = getSession().createQuery(sb.toString());
    	return query.list();*/	
    }
    
    public List<Object[]> getPartyMeetingInviteeSummary(List<Long> cadreIds){
    	Query query = getSession().createQuery(" select model.tdpCadreId, count(distinct model.partyMeetingId) " +
    			" from PartyMeetingInvitee model " +
    			" where model.tdpCadreId in (:cadreIds) and model.partyMeeting.isActive='Y' " +
    			" group by model.tdpCadreId ");
    	
    	query.setParameterList("cadreIds", cadreIds);
    	
    	return query.list();
    }
    public List<Object[]> meetingWiseInviteeCadreList(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet){
    	StringBuilder queryStr = new StringBuilder();
    	queryStr.append(" select distinct " +
    			" model.partyMeeting.partyMeetingId, model.tdpCadre.tdpCadreId " +
    			" from PartyMeetingInvitee model " +
    			" where " +
    			" model.tdpCadre.isDeleted = 'N' " +
    			" and model.partyMeeting.isActive = 'Y' " +
    			" and model.partyMeeting.partyMeetingType.isActive = 'Y' ");
    	if(inputVO.getPartyMeetingMainTypeId() != null && inputVO.getPartyMeetingMainTypeId().longValue() > 0L){
    		queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetingMainTypeId ");
    	}
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeIdList) ");
    	}
    	if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
    		queryStr.append(" and date(model.partyMeeting.startDate) between :startDate and :endDate ");
    	}
    	if(inputVO.getStateId() != null && inputVO.getStateId().longValue() > 0L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId = :stateId ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 2L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:locationValuesSet) ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 3L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:locationValuesSet) ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 4L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:locationValuesSet) ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 5L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:locationValuesSet) ");
    	}
    	
    	Query query = getSession().createQuery(queryStr.toString());
    	
    	if(inputVO.getPartyMeetingMainTypeId() != null && inputVO.getPartyMeetingMainTypeId().longValue() > 0L){
    		query.setParameter("partyMeetingMainTypeId", inputVO.getPartyMeetingMainTypeId());
    	}
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size() > 0){
    		query.setParameterList("partyMeetingTypeIdList", inputVO.getPartyMeetingTypeIds());
    	}
    	if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
    		query.setDate("startDate",inputVO.getStartDate());
    		query.setDate("endDate",inputVO.getEndDate());
    	}
    	if(inputVO.getStateId() != null && inputVO.getStateId().longValue() > 0L){
    		query.setParameter("stateId", inputVO.getStateId());
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 ){
    		query.setParameterList("locationValuesSet",locationValuesSet);
    	}
    	
    	return query.list();
    }  
    
    public List<PartyMeetingInvitee> getInviteesByPartyMeetingAndCadreId(Long partyMeetingId,Long tdpCadreId){
    	Query query = getSession().createQuery("select model" +
    											" from PartyMeetingInvitee model" +
    											" where model.partyMeeting.partyMeetingId = :partyMeetingId" +
    											" and model.tdpCadre.tdpCadreId = :tdpCadreId");
    	query.setParameter("partyMeetingId", partyMeetingId);
    	query.setParameter("tdpCadreId", tdpCadreId);
    	return query.list();
    }
    public List<Object[]> plannedMeetingWiseInviteeCadreList(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet){
    	StringBuilder queryStr = new StringBuilder();
    	queryStr.append(" select distinct " +
    			" model.partyMeeting.partyMeetingId, model.tdpCadre.tdpCadreId " +
    			" from PartyMeetingInvitee model,PartyMeetingGroupsMappingInfo model1  " +
    			" where " +
    			" model.tdpCadre.isDeleted = 'N' " +
    			" and model.partyMeeting.isActive = 'Y' " +
    			" and model.partyMeeting.partyMeetingType.isActive = 'Y' " +
    			"and model1.partyMeeting.partyMeetingId = model.partyMeeting.partyMeetingId ");
    	if(inputVO.getPartyMeetingMainTypeId() != null && inputVO.getPartyMeetingMainTypeId().longValue() > 0L){
    		queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetingMainTypeId ");
    	}
    	if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0l){
			queryStr.append(" and model1.partyMeetingGroup.partyMeetingGroupId = :partyMeetngGrpId ");
		}
    	
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeIdList) ");
    	}
    	if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
    		queryStr.append(" and date(model.partyMeeting.startDate) between :startDate and :endDate ");
    	}
    	if(inputVO.getStateId() != null && inputVO.getStateId().longValue() > 0L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId = :stateId ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 2L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:locationValuesSet) ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 3L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:locationValuesSet) ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 4L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:locationValuesSet) ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 5L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:locationValuesSet) ");
    	}
    	if(inputVO.getCategoryIdList() != null && inputVO.getCategoryIdList().size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingLevelId in (:partyMeetingLevelIds) ");
    	}
    	Query query = getSession().createQuery(queryStr.toString());
    	
    	if(inputVO.getPartyMeetingMainTypeId() != null && inputVO.getPartyMeetingMainTypeId().longValue() > 0L){
    		query.setParameter("partyMeetingMainTypeId", inputVO.getPartyMeetingMainTypeId());
    	}
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size() > 0){
    		query.setParameterList("partyMeetingTypeIdList", inputVO.getPartyMeetingTypeIds());
    	}
    	if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
    		query.setDate("startDate",inputVO.getStartDate());
    		query.setDate("endDate",inputVO.getEndDate());
    	}
    	if(inputVO.getStateId() != null && inputVO.getStateId().longValue() > 0L){
    		query.setParameter("stateId", inputVO.getStateId());
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 ){
    		query.setParameterList("locationValuesSet",locationValuesSet);
    	}
    	if(inputVO.getCategoryIdList() != null && inputVO.getCategoryIdList().size() > 0){
    		query.setParameterList("partyMeetingLevelIds",inputVO.getCategoryIdList());
    	}
    	if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0l){
			 query.setParameter("partyMeetngGrpId", inputVO.getPartyMeetingGroupId()); 
		 }
    	return query.list();
    } 
    public List<Object[]> meetingWiseInviteeCadreListForLevelWise(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet,List<Long> locLevelIdList){
    	StringBuilder queryStr = new StringBuilder();
    	queryStr.append(" select distinct " +
    			" model.partyMeeting.partyMeetingId, " +
    			" model.tdpCadre.tdpCadreId, " +
    			"model.partyMeeting.meetingAddress.district.districtId, " +
    			" model.partyMeeting.partyMeetingLevelId,model3.sessionType.sessionTypeId " +
    			" from PartyMeetingInvitee model, PartyMeetingGroupsMappingInfo model2,PartyMeetingSession model3 " +
    			" where " +
    			" model.tdpCadre.isDeleted = 'N' " +
    			" and model.tdpCadre.enrollmentYear = 2014 " +  
    			" and model.partyMeeting.isActive = 'Y' " +
    			" and model.partyMeeting.partyMeetingType.isActive = 'Y' " +
    			" and model.partyMeeting.partyMeetingId = model2.partyMeeting.partyMeetingId " +
    			" and model3.isDeleted = 'N' and model3.partyMeeting.partyMeetingId = model.partyMeeting.partyMeetingId ");
    	if(inputVO.getPartyMeetingMainTypeId() != null && inputVO.getPartyMeetingMainTypeId().longValue() > 0L){
    		queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetingMainTypeId ");
    	}
    	if(inputVO.getSessionId() != null && inputVO.getSessionId().longValue() > 0L){
			queryStr.append(" and model3.sessionType.sessionTypeId = :sessionTypeId " );
    	}
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeIdList) ");
    	}
    	
    	if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
    		queryStr.append(" and date(model.partyMeeting.startDate) between :startDate and :endDate ");
    	}
    	if(inputVO.getStateId() != null && inputVO.getStateId().longValue() > 0L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId = :stateId ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 2L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:locationValuesSet) ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 3L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:locationValuesSet) ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 4L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:locationValuesSet) ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 5L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:locationValuesSet) ");
    	}
    	if(locLevelIdList != null && locLevelIdList.size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingLevelId in (:locLevelIdList) ");
    	}
    	if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0L){
    		queryStr.append(" and model2.partyMeetingGroup.partyMeetingGroupId = :partyMeetingGroupId ");
    	}
    	if(inputVO.getStateId().longValue() == 1L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");	
    	}else if(inputVO.getStateId().longValue() == 36L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
    	}
    	
    	Query query = getSession().createQuery(queryStr.toString());
    	
    	if(inputVO.getPartyMeetingMainTypeId() != null && inputVO.getPartyMeetingMainTypeId().longValue() > 0L){
    		query.setParameter("partyMeetingMainTypeId", inputVO.getPartyMeetingMainTypeId());
    	}
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size() > 0){
    		query.setParameterList("partyMeetingTypeIdList", inputVO.getPartyMeetingTypeIds());
    	}
    	if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
    		query.setDate("startDate",inputVO.getStartDate());
    		query.setDate("endDate",inputVO.getEndDate());
    	}
    	if(inputVO.getStateId() != null && inputVO.getStateId().longValue() > 0L){
    		query.setParameter("stateId", inputVO.getStateId());
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 ){
    		query.setParameterList("locationValuesSet",locationValuesSet);
    	}
    	if(locLevelIdList != null && locLevelIdList.size() > 0){
    		query.setParameterList("locLevelIdList",locLevelIdList);  
    	}
    	if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0L){
    		query.setParameter("partyMeetingGroupId", inputVO.getPartyMeetingGroupId());
    	} 
    	if(inputVO.getSessionId() != null && inputVO.getSessionId().longValue() > 0L){
    		query.setParameter("sessionTypeId", inputVO.getSessionId());
    	}
    	return query.list();
    } 
    public List<Object[]> getCommitteeWiseInvitedCadreCountForMultiLocationMeeting(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet,List<Long> locLevelIdList,String isRequired){
    	StringBuilder queryStr = new StringBuilder();
    	queryStr.append(" select distinct " +
    			" model3.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId, " +
    			" model3.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel, " +
    			" model.tdpCadre.tdpCadreId " +
    			" from PartyMeetingInvitee model, PartyMeetingGroupsMappingInfo model2, TdpCommitteeMember model3 " +
    			" where " +
    			" model.tdpCadre.isDeleted = 'N' " +
    			" and model.tdpCadre.enrollmentYear = 2014 " +
    			" and model.partyMeeting.isActive = 'Y' " +
    			" and model.partyMeeting.partyMeetingType.isActive = 'Y' " +
    			" and model.partyMeeting.partyMeetingId = model2.partyMeeting.partyMeetingId " +
    			" " +
    			" and model.tdpCadre.tdpCadreId = model3.tdpCadre.tdpCadreId ");
    	if(isRequired != null && isRequired.equalsIgnoreCase("true")){
        	queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in ("+IConstants.REQUIRED_COMMITTEE_LEVEL_IDS+") ");
    	}else{
        	queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in ("+IConstants.REMAINING_COMMITTEE_LEVEL_IDS+") ");
    	}
    	if(inputVO.getPartyMeetingMainTypeId() != null && inputVO.getPartyMeetingMainTypeId().longValue() > 0L){
    		queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetingMainTypeId ");
    	}
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeIdList) ");
    	}
    	if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
    		queryStr.append(" and date(model.partyMeeting.startDate) between :startDate and :endDate ");
    	}
    	if(inputVO.getStateId() != null && inputVO.getStateId().longValue() > 0L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId = :stateId ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 2L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:locationValuesSet) ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 3L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:locationValuesSet) ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 4L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:locationValuesSet) ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 5L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:locationValuesSet) ");
    	}
    	if(locLevelIdList != null && locLevelIdList.size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingLevelId in (:locLevelIdList) ");
    	}
    	if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0L){
    		queryStr.append(" and model2.partyMeetingGroup.partyMeetingGroupId = :partyMeetingGroupId ");
    	}
    	if(inputVO.getStateId().longValue() == 1L){
    		queryStr.append(" and model.tdpCadre.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");	
    	}else if(inputVO.getStateId().longValue() == 36L){
    		queryStr.append(" and model.tdpCadre.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
    	}
    	
    	Query query = getSession().createQuery(queryStr.toString());
    	
    	if(inputVO.getPartyMeetingMainTypeId() != null && inputVO.getPartyMeetingMainTypeId().longValue() > 0L){
    		query.setParameter("partyMeetingMainTypeId", inputVO.getPartyMeetingMainTypeId());
    	}
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size() > 0){
    		query.setParameterList("partyMeetingTypeIdList", inputVO.getPartyMeetingTypeIds());
    	}
    	if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
    		query.setDate("startDate",inputVO.getStartDate());
    		query.setDate("endDate",inputVO.getEndDate());
    	}
    	if(inputVO.getStateId() != null && inputVO.getStateId().longValue() > 0L){
    		query.setParameter("stateId", inputVO.getStateId());
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 ){
    		query.setParameterList("locationValuesSet",locationValuesSet);
    	}
    	if(locLevelIdList != null && locLevelIdList.size() > 0){
    		query.setParameterList("locLevelIdList",locLevelIdList);  
    	}
    	if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0L){
    		query.setParameter("partyMeetingGroupId", inputVO.getPartyMeetingGroupId());
    	}  
    	return query.list();
    	/*StringBuilder sb = new StringBuilder();
    	sb.append(" select  distinct tcl.tdp_committee_level_id as tdpCommitteeLevelId,tcl.tdp_committee_level as tdpcommitteeLevel," +
    			  "          cadre.tdp_cadre_id as count " +
    			  " from    party_meeting_invitee pmi " +
    			  "         join party_meeting pm on pmi.party_meeting_id = pm.party_meeting_id " +
    			  "         join tdp_cadre cadre on pmi.tdp_cadre_id = cadre.tdp_cadre_id " +
    			  "         join user_address ua on pm.meeting_address_id = ua.user_address_id " +
    			  "         join party_meeting_type pmt on pm.party_meeting_type_id = pmt.party_meeting_type_id " +
    			  "         join party_meeting_main_type pmmt on pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id " +
    			  "" +
    			  "         join tdp_committee_member tcm  on tcm.tdp_cadre_id = cadre.tdp_cadre_id " +
    			  "         join tdp_committee_role   tcr  on tcm.tdp_committee_role_id = tcr.tdp_committee_role_id " +
    			  "         join tdp_committee        tc   on tcr.tdp_committee_id = tc.tdp_committee_id" +
    			  "         join tdp_committee_level  tcl  on tc.tdp_committee_level_id = tcl.tdp_committee_level_id " +
    			  " where   pmmt.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
    			  "         cadre.is_deleted = 'N' and cadre.enrollment_year = 2014 and pm.is_active='Y' ");
    	
    	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
    		sb.append(" and ua.state_id = :stateId ");
    	}
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
    		sb.append(" and pmt.party_meeting_type_id in (:partyMeetingTypeIds) ");	
    	}
    	if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size() > 0l){
    		sb.append(" and pm.party_meeting_id in(:partyMeetingIds) ");
    	}
    	
    	if(inputVO.getCategory().equalsIgnoreCase("Committees")){
    		sb.append(" and tcl.tdp_committee_level_id in (:tdpCommitteeLevelIds) ");
    	}else if(inputVO.getCategory().equalsIgnoreCase("other")){
    		sb.append(" and tcl.tdp_committee_level_id not in (:tdpCommitteeLevelIds) ");
    	} 
    	
    	Query query = getSession().createSQLQuery(sb.toString())
    	.addScalar("tdpCommitteeLevelId",Hibernate.LONG)
    	.addScalar("tdpcommitteeLevel",Hibernate.STRING)
    	.addScalar("count",Hibernate.LONG);
    	
    	if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
    		query.setDate("startDate",inputVO.getStartDate());
    		query.setDate("endDate",inputVO.getEndDate());	 
    	}
    	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){  
    		query.setParameter("stateId",inputVO.getStateId());
    	}
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
    		query.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
    	}
    	if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size() > 0l){
    		query.setParameterList("partyMeetingIds",inputVO.getPartyMeetingIds());
    	}
    	query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
    	if(inputVO.getCategory().equalsIgnoreCase("Committees")){
    		query.setParameterList("tdpCommitteeLevelIds",inputVO.getCategoryIdList());
    	}else if(inputVO.getCategory().equalsIgnoreCase("other")){
    		query.setParameterList("tdpCommitteeLevelIds",inputVO.getCategoryIdList());
    	}  
       return query.list();	  */
    }
    public List<Object[]> getPublicRepresentativeWiseInvitedCadreCountForMultiLocationWiseMeeting(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet,List<Long> locLevelIdList,String isRequired){
    	StringBuilder queryStr = new StringBuilder();
    	queryStr.append(" select distinct " +
    			" model4.publicRepresentativeType.publicRepresentativeTypeId , " +
    			" model4.publicRepresentativeType.type, " +
    			" model.tdpCadre.tdpCadreId " +
    			" from PartyMeetingInvitee model, PartyMeetingGroupsMappingInfo model2, TdpCadreCandidate model3, PublicRepresentative model4 " + 
    			" where " +
    			" model.tdpCadre.isDeleted = 'N' " +
    			" and model.tdpCadre.enrollmentYear = 2014 " +  
    			" and model.partyMeeting.isActive = 'Y' " +
    			" and model.partyMeeting.partyMeetingType.isActive = 'Y' " +
    			" and model.partyMeeting.partyMeetingId = model2.partyMeeting.partyMeetingId " +
    			"" +
    			" and model.tdpCadre.tdpCadreId = model3.tdpCadre.tdpCadreId " +
    			" and model3.candidate.candidateId = model4.candidate.candidateId ");
    	if(isRequired != null && isRequired.equalsIgnoreCase("true")){
    		queryStr.append(" and model4.publicRepresentativeType.publicRepresentativeTypeId in ("+IConstants.REQUIRED_PUBLIC_REPRESENTATIVE_TYPE_IDS+") ");
    	}else{
    		queryStr.append(" and model4.publicRepresentativeType.publicRepresentativeTypeId in ("+IConstants.REMAINING_PUBLIC_REPRESENTATIVE_TYPE_IDS+") ");
    	}
    	
    	if(inputVO.getPartyMeetingMainTypeId() != null && inputVO.getPartyMeetingMainTypeId().longValue() > 0L){
    		queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetingMainTypeId ");
    	}
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeIdList) ");
    	}
    	if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
    		queryStr.append(" and date(model.partyMeeting.startDate) between :startDate and :endDate ");
    	}
    	if(inputVO.getStateId() != null && inputVO.getStateId().longValue() > 0L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId = :stateId ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 2L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:locationValuesSet) ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 3L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:locationValuesSet) ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 4L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:locationValuesSet) ");
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 5L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:locationValuesSet) ");
    	}
    	if(locLevelIdList != null && locLevelIdList.size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingLevelId in (:locLevelIdList) ");
    	}
    	if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0L){
    		queryStr.append(" and model2.partyMeetingGroup.partyMeetingGroupId = :partyMeetingGroupId ");
    	}
    	if(inputVO.getStateId().longValue() == 1L){
    		queryStr.append(" and model.tdpCadre.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");	
    	}else if(inputVO.getStateId().longValue() == 36L){
    		queryStr.append(" and model.tdpCadre.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
    	}
    	
    	Query query = getSession().createQuery(queryStr.toString());
    	
    	if(inputVO.getPartyMeetingMainTypeId() != null && inputVO.getPartyMeetingMainTypeId().longValue() > 0L){
    		query.setParameter("partyMeetingMainTypeId", inputVO.getPartyMeetingMainTypeId());
    	}
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size() > 0){
    		query.setParameterList("partyMeetingTypeIdList", inputVO.getPartyMeetingTypeIds());
    	}
    	if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
    		query.setDate("startDate",inputVO.getStartDate());
    		query.setDate("endDate",inputVO.getEndDate());
    	}
    	if(inputVO.getStateId() != null && inputVO.getStateId().longValue() > 0L){
    		query.setParameter("stateId", inputVO.getStateId());
    	}
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 ){
    		query.setParameterList("locationValuesSet",locationValuesSet);
    	}
    	if(locLevelIdList != null && locLevelIdList.size() > 0){
    		query.setParameterList("locLevelIdList",locLevelIdList);  
    	}
    	if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0L){
    		query.setParameter("partyMeetingGroupId", inputVO.getPartyMeetingGroupId());
    	}  
    	return query.list();
    	/*StringBuilder sb = new StringBuilder();
    	
    	sb.append(" select  distinct prt.public_representative_type_id as representativeTypeId,prt.position as position," +
    			"            cadre.tdp_cadre_id as count " +
    			"   from    party_meeting_invitee pmi " +
    			"           join party_meeting pm on pmi.party_meeting_id = pm.party_meeting_id" +
    			"           join tdp_cadre cadre on pmi.tdp_cadre_id = cadre.tdp_cadre_id" +
    			"           join user_address ua on pm.meeting_address_id = ua.user_address_id" +
    			"           join party_meeting_type pmt on pm.party_meeting_type_id = pmt.party_meeting_type_id" +
    			"           join party_meeting_main_type pmmt on pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id" +
    			"" +
    			"           join tdp_cadre_candidate tcc  on tcc.tdp_cadre_id = cadre.tdp_cadre_id" +
    			"           join candidate c on tcc.candidate_id = c.candidate_id" +
    			"           join public_representative pr on c.candidate_id = pr.candidate_id" +
    			"           join public_representative_type prt on pr.public_representative_type_id = prt.public_representative_type_id " +
    			"  where    pmmt.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
    			"           cadre.is_deleted = 'N' and cadre.enrollment_year = 2014 and pm.is_active='Y' ");
    	
    	if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
    		 sb.append(" and date(pm.start_date) between :startDate and :endDate ");	 
    	}
    	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
    		sb.append(" and ua.state_id = :stateId ");
    	}
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
    		sb.append(" and pmt.party_meeting_type_id in (:partyMeetingTypeIds) ");	
    	}
    	if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size() > 0l){
    		sb.append(" and pm.party_meeting_id in(:partyMeetingIds) ");
    	}
    	//sb.append(" group by prt.public_representative_type_id,cadre.tdp_cadre_id ");
    	if(inputVO.getCategory().equalsIgnoreCase("Representative")){
    		sb.append(" and prt.public_representative_type_id in (:publicRepresentativeTypeIds) ");
    	}else if(inputVO.getCategory().equalsIgnoreCase("other")){
    		sb.append(" and prt.public_representative_type_id not in (:publicRepresentativeTypeIds) ");
    	} 
    	Query query = getSession().createSQLQuery(sb.toString())
    	.addScalar("representativeTypeId",Hibernate.LONG)
    	.addScalar("position",Hibernate.STRING)
    	.addScalar("count",Hibernate.LONG);
    	
    	if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
    		query.setDate("startDate",inputVO.getStartDate());
    		query.setDate("endDate",inputVO.getEndDate());	 
    	}
    	if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
    		query.setParameter("stateId",inputVO.getStateId());
    	}
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
    		query.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
    	}
    	if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size() > 0l){
    		query.setParameterList("partyMeetingIds",inputVO.getPartyMeetingIds());
    	}
    	query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
    	if(inputVO.getCategory().equalsIgnoreCase("Representative")){
    		query.setParameterList("publicRepresentativeTypeIds",inputVO.getCategoryIdList());
    	}else if(inputVO.getCategory().equalsIgnoreCase("other")){
    		query.setParameterList("publicRepresentativeTypeIds",inputVO.getCategoryIdList());
    	}
       return query.list();*/
    }
    
    public List<Object[]> getInvitteeDetails(Long partyMeetnMainTypId,Long userAccessLevelId,Set<Long> locationValuesSet,
			Date fromDate,Date toDate,Long stateId,List<Long> levelIdsList,Long partyMeetngGrpId,Long partyMeetingId,Long locationValId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select  model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.partyMeetingId," +
				"model.tdpCadre.tdpCadreId, model.partyMeeting.partyMeetingType.partyMeetingTypeId,'', model.absenteeRemark  " +
				"from PartyMeetingInvitee model ");
		if(partyMeetngGrpId != null && partyMeetngGrpId.longValue() > 0l){
			queryStr.append(" ,PartyMeetingGroupsMappingInfo model1");
		}
		
		queryStr.append(" where model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetnMainTypId and  model.partyMeeting.isActive='Y' ");
		if(partyMeetingId != null && partyMeetingId.longValue() > 0l)
			queryStr.append(" and model.partyMeeting.partyMeetingId = :partyMeetingId");
		
		if(partyMeetngGrpId != null && partyMeetngGrpId.longValue() > 0l){
			queryStr.append(" and model1.partyMeeting.partyMeetingId = model.partyMeeting.partyMeetingId  and model1.partyMeetingGroup.partyMeetingGroupId = :partyMeetngGrpId ");
		}
		
		if(levelIdsList != null && levelIdsList.size() > 0l){
			queryStr.append(" and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId in (:levelIdsList) ");
		}
		
		if(locationValId != null && locationValId.longValue() > 0l){
			queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId = :locationValId "); 
		}
		if(stateId != null && stateId.longValue() > 0){
			 queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId=:stateId");
	  }
	  if(fromDate!= null && toDate!=null){
		  queryStr.append(" and date(model.partyMeeting.startDate) between :fromDate and :toDate ");	 
	 }
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			   queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			   queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		        queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		        queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			    queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			    queryStr.append(" and model.partyMeeting.meetingAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			    queryStr.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			    queryStr.append(" and model.partyMeeting.meetingAddress.ward.constituencyId in (:userAccessLevelValues)"); 
			 }
		
		//queryStr.append(" group by model.partyMeetingLevel.partyMeetingLevelId,model1.partyMeetingGroup.partyMeetingGroupId ");
		Query query = getSession().createQuery(queryStr.toString());
		 if(locationValuesSet != null && locationValuesSet.size() > 0){
			   query.setParameterList("userAccessLevelValues", locationValuesSet);
		 }
		 if(fromDate!= null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		 }
		 if(stateId != null && stateId.longValue() > 0){
			 query.setParameter("stateId", stateId);
		 }
		 if(partyMeetngGrpId != null && partyMeetngGrpId.longValue() > 0l){
			 query.setParameter("partyMeetngGrpId", partyMeetngGrpId); 
		 }
		 if(levelIdsList != null && levelIdsList.size() > 0l){
			 query.setParameterList("levelIdsList", levelIdsList); 
		 }
		 if(locationValId != null && locationValId.longValue() > 0l){
			 query.setParameter("locationValId", locationValId);
		 }
		 if(partyMeetingId != null && partyMeetingId.longValue() > 0l)
			 query.setParameter("partyMeetingId", partyMeetingId);
		query.setParameter("partyMeetnMainTypId", partyMeetnMainTypId);
		return query.list();
	}
    
	public List<Object[]> getPartyMeetingInviteeDetaisByPartyMeetingId(Long meetingId){
		StringBuilder sb = new StringBuilder();
    	sb.append("select model.partyMeetingId,model.tdpCadre.tdpCadreId,");//meetingId, tdpCadreId  ,0,1
    	sb.append("model.tdpCadre.memberShipNo,model.partyMeetingInviteeId " +
    			"from  PartyMeetingInvitee model ");//2 memmershipid  //3 partyMeetingInviteeId
		sb.append("where model.tdpCadre.isDeleted='N' " );
		 if(meetingId != null && meetingId.longValue() > 0l)
			 sb.append("and model.partyMeetingId=:meetingId");
		Query query = getSession().createQuery(sb.toString());
		 if(meetingId != null && meetingId.longValue() > 0l){
			 query.setParameter("meetingId", meetingId);
		 }
		return query.list();
    }

	public int updateAbsenteeRemark(Long cadreId, Long partyMeetingId,String comment) {
		StringBuilder queryStr = new StringBuilder();
	     queryStr.append(" update PartyMeetingInvitee model set model.absenteeRemark=:comment"+
	              " where " +
	              " model.partyMeetingId=:partyMeetingId and  model.tdpCadreId=:cadreId ");
	     Query query = getSession().createQuery(queryStr.toString());
	      query.setParameter("cadreId", cadreId);
	      query.setParameter("partyMeetingId", partyMeetingId);
	      query.setParameter("comment", comment);
	      return query.executeUpdate();
	}
	
	public List<Object[]> getPartyMeetingInvitteesMembershipNo(Long partyMeetingId)
	{
		Query query = getSession().createQuery("SELECT DISTINCT model.tdpCadre.memberShipNo,model.absenteeRemark FROM PartyMeetingInvitee model where model.partyMeeting.partyMeetingId = :partyMeetingId  " +
				" and model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear="+IConstants.CADRE_ENROLLMENT_NUMBER+" and model.partyMeeting.isActive='Y' ");
		query.setParameter("partyMeetingId",partyMeetingId);
		return query.list();
	}
	public List<Object[]> getInviteeList(Long locationLevel,List<Long> locationIds,Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct " +
						" model.partyMeeting.partyMeetingType.partyMeetingTypeId, " +
						" model.partyMeeting.partyMeetingType.type, " +
						" model.partyMeeting.partyMeetingId, " +
						" model.tdpCadre.tdpCadreId " +
						" from " +
						" PartyMeetingInvitee model " +
						" where " +
						" model.partyMeeting.isActive = 'Y' " +
						" and model.partyMeeting.partyMeetingType.isActive = 'Y' " +
						" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId ="+IConstants.SPECIAL_MEETINGS+" " +
						" and model.tdpCadre.isDeleted='N' ");
		if(locationIds != null && locationIds.size() > 0){
			if(locationLevel != null && locationLevel.longValue()==IConstants.PARTY_MEETING_STATE_LEVEL_ID){
				queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:locationIds)");  
			}else if(locationLevel != null && locationLevel.longValue()==IConstants.PARTY_MEETING_DISTRICT_LEVEL_ID){
				queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:locationIds)");  
			}else if(locationLevel != null && locationLevel.longValue()==IConstants.PARTY_MEETING_PARLIAMENT_LEVEL_ID){
				queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:locationIds) ");  
			}else if(locationLevel != null && locationLevel.longValue()==IConstants.PARTY_MEETING_CONSTITUENCY_LEVEL_ID){
				queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:locationIds) ");  
			}else if(locationLevel != null && locationLevel.longValue()==IConstants.PARTY_MEETING_MANDAL_LEVEL_ID){
				queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:locationIds)");  
			}else if(locationLevel != null && locationLevel.longValue()==IConstants.PARTY_MEETING_MUNICIPALITY_LEVEL_ID){ //  town/division
				queryStr.append(" and model.partyMeeting.meetingAddress.userAddress.localElectionBody.localElectionBodyId in (:locationIds)"); 
			}else if(locationLevel != null && locationLevel.longValue()==IConstants.PARTY_MEETING_PANCHAYAT_LEVEL_ID){ 
				queryStr.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:locationIds)"); 
			}
		}
		if(fromDate != null && toDate != null){
			queryStr.append(" and (date(model.partyMeeting.startDate) between :fromDate and :toDate) ");
		}
		Query query = getSession().createQuery(queryStr.toString());
		if(locationIds != null && locationIds.size() > 0){
			query.setParameterList("locationIds", locationIds);
		}
		if(fromDate != null && toDate != null){
			query.setDate("fromDate",fromDate);
			query.setDate("toDate",toDate);
			
		}
		return query.list();
	}
	
	public List<Object[]> getInviteeListForMeeting(Long locationLevel,List<Long> locationIds,Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct " +
						" model.partyMeeting.partyMeetingId, " +
						" model.tdpCadre.tdpCadreId, " +
						" model.partyMeeting.meetingName, " +
						" model.partyMeeting.startDate," +
						" model.partyMeeting.partyMeetingLevel.level " +
						" from " +
						" PartyMeetingInvitee model " +
						" where " +
						" model.partyMeeting.isActive = 'Y' " +
						" and model.partyMeeting.partyMeetingType.isActive = 'Y' " +
						" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId ="+IConstants.SPECIAL_MEETINGS+" " +
						" and model.tdpCadre.isDeleted='N' ");
		if(locationIds != null && locationIds.size() > 0){
			if(locationLevel != null && locationLevel.longValue()==IConstants.PARTY_MEETING_STATE_LEVEL_ID){
				queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:locationIds)");  
			}else if(locationLevel != null && locationLevel.longValue()==IConstants.PARTY_MEETING_DISTRICT_LEVEL_ID){
				queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:locationIds)");  
			}else if(locationLevel != null && locationLevel.longValue()==IConstants.PARTY_MEETING_PARLIAMENT_LEVEL_ID){
				queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:locationIds) ");  
			}else if(locationLevel != null && locationLevel.longValue()==IConstants.PARTY_MEETING_CONSTITUENCY_LEVEL_ID){
				queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:locationIds) ");  
			}else if(locationLevel != null && locationLevel.longValue()==IConstants.PARTY_MEETING_MANDAL_LEVEL_ID){
				queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:locationIds)");  
			}else if(locationLevel != null && locationLevel.longValue()==IConstants.PARTY_MEETING_MUNICIPALITY_LEVEL_ID){ //  town/division
				queryStr.append(" and model.partyMeeting.meetingAddress.userAddress.localElectionBody.localElectionBodyId in (:locationIds)"); 
			}else if(locationLevel != null && locationLevel.longValue()==IConstants.PARTY_MEETING_PANCHAYAT_LEVEL_ID){ 
				queryStr.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:locationIds)"); 
			}
		}
		if(fromDate != null && toDate != null){
			queryStr.append(" and (date(model.partyMeeting.startDate) between :fromDate and :toDate) ");
		}
		Query query = getSession().createQuery(queryStr.toString());
		if(locationIds != null && locationIds.size() > 0){
			query.setParameterList("locationIds", locationIds);
		}
		if(fromDate != null && toDate != null){
			query.setDate("fromDate",fromDate);
			query.setDate("toDate",toDate);
			
		}
		return query.list();
	}
	
	public List<Object[]> getLocationWiseStateMeetingInvitees(List<Long> locationValues,Long locationTypeId,Date fromDate,Date toDate,Long partyMeetingMainTypeid,Long partyMeetingTypeId){
	       
	       //0-meetingStatus,1-levelId,2-level,3-count
	       StringBuilder sb = new StringBuilder();
	       	
	       sb.append(" select model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId,model.partyMeeting.partyMeetingType.partyMeetingMainType.meetingType," +
	          "model.partyMeeting.partyMeetingType.partyMeetingTypeId,model.partyMeeting.partyMeetingType.type,model.tdpCadre.tdpCadreId," +
	          "model.partyMeeting.partyMeetingId,model.partyMeeting.meetingName from PartyMeetingInvitee model where model.partyMeeting.isActive = 'Y' and ");
	       
	       if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){ 
	    	   if(locationTypeId == 2l){
		          sb.append("  model.partyMeeting.meetingAddress.state.stateId in (:locationValues) ");
		        }else if(locationTypeId == 4l){
	              sb.append("  model.partyMeeting.meetingAddress.constituency.constituencyId in (:locationValues) ");
	            }else if(locationTypeId == 3l){
	              sb.append("  model.partyMeeting.meetingAddress.district.districtId in (:locationValues)");
	            }else if(locationTypeId == 5l){
	              sb.append("  model.partyMeeting.meetingAddress.tehsil.tehsilId in (:locationValues)"); 
	            }else if(locationTypeId == 6l){
	              sb.append("  model.partyMeeting.meetingAddress.panchayat.panchayatId in (:locationValues)"); 
	            }else if(locationTypeId==10l){
	              sb.append("  model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:locationValues) "); 
	            }else if(locationTypeId == 7l){
	              sb.append("  model.partyMeeting.meetingAddress.localElectionBody.localElectionBodyId in (:locationValues)");
	            }else if(locationTypeId == 8l){
	              sb.append("  model.partyMeeting.meetingAddress.ward.constituencyId in (:locationValues)"); 
	            }
	        }
	       if(partyMeetingMainTypeid != null && partyMeetingMainTypeid.longValue() > 0l){
	    	   sb.append(" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetingMainTypeid ");
	       }
	       if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0l){
	    	   sb.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId = :partyMeetingTypeId ");
	       }
	       if(fromDate != null && toDate != null){
	    	   sb.append(" and date(model.partyMeeting.startDate) between :fromDate and :toDate ");
	       }
	      // sb.append(" group by model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId,moedl.partyMeetingType.partyMeetingTypeId ");
	       
	       Query query = getSession().createQuery(sb.toString());
	       
	       if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){
	         query.setParameterList("locationValues", locationValues);
	       }
	       if(fromDate != null && toDate != null){
	         query.setDate("fromDate", fromDate);
	            query.setDate("toDate", toDate); 
	       }
	       if(partyMeetingMainTypeid != null && partyMeetingMainTypeid.longValue() > 0l){
	    	   query.setParameter("partyMeetingMainTypeid", partyMeetingMainTypeid);
	       }
	       if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0l){
	    	   query.setParameter("partyMeetingTypeId", partyMeetingTypeId);
	       }
	       return query.list();
	     }
	
	public List<Object[]> getLocationWiseMeetingInviteeMembers(List<Long> locationValues,Long locationTypeId,Date fromDate,Date toDate,Long partyMeetingMainTypeid
			,Long partyMeetingTypeId,Long partyMeetingId,Set<Long> inviteeIds){
	       
	        StringBuilder sb = new StringBuilder();
	       	
	       sb.append(" SELECT TC.tdp_cadre_id,TC.first_name,PRT.position," +
	       		"TR.role,TCL.tdp_committee_level,TC.mobile_no,  D.district_name , PRT.public_representative_type_id ,PM.remarks,PM.party_meeting_id " +
	          " from party_meeting_invitee model" +
	          "  LEFT OUTER JOIN party_meeting PM ON PM.party_meeting_id=model.party_meeting_id and PM.is_active = 'Y' " +
	          " LEFT OUTER JOIN party_meeting_type  PMT ON PMT.party_meeting_type_id = PM.party_meeting_type_id " +
	          "  LEFT OUTER JOIN party_meeting_main_type PMMT ON PMMT.party_meeting_main_type_id=PMT.party_meeting_main_type_id " +
	          " LEFT OUTER JOIN user_address meetinAddress  ON meetinAddress.user_address_id =  PM.meeting_address_id " +
	          " LEFT OUTER JOIN tdp_cadre TC ON model.tdp_cadre_id = TC.tdp_cadre_id  "+
	                   " LEFT OUTER JOIN tdp_cadre_candidate TCC ON TC.tdp_cadre_id = TCC.tdp_cadre_id "+
	                   " LEFT OUTER JOIN public_representative  PR ON TCC.candidate_id = PR.candidate_id "+
	                   " LEFT OUTER JOIN public_representative_type PRT ON PR.public_representative_type_id = PRT.public_representative_type_id "+
	                   " LEFT OUTER JOIN tdp_committee_member TCM ON TC.tdp_cadre_id = TCM.tdp_cadre_id AND TCM.is_active = 'Y' "+
	                   " LEFT OUTER JOIN tdp_committee_role TCR ON TCM.tdp_committee_role_id = TCR.tdp_committee_role_id "+
	                   " LEFT OUTER JOIN tdp_roles TR ON TCR.tdp_roles_id = TR.tdp_roles_id "+
	                   " LEFT OUTER JOIN tdp_committee TCT ON TCR.tdp_committee_id = TCT.tdp_committee_id "+
	                   " LEFT OUTER JOIN tdp_committee_level TCL ON TCT.tdp_committee_level_id = TCL.tdp_committee_level_id " +
	                   " LEFT OUTER JOIN user_address UA ON UA.user_address_id =  TC.address_id " +
	                   " LEFT OUTER JOIN district D ON D.district_id = UA.district_id " +
	          " where  ");
	       
	       if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){ 
	    	   if(locationTypeId == 2l){
		          sb.append("  meetinAddress.state_id in (:locationValues) ");
		        }else if(locationTypeId == 4l){
	              sb.append("  meetinAddress.constituency_id in (:locationValues) ");
	            }else if(locationTypeId == 3l){
	              sb.append("  meetinAddress.district_id in (:locationValues)");
	            }else if(locationTypeId == 5l){
	              sb.append("  meetinAddress.tehsil_id in (:locationValues)"); 
	            }else if(locationTypeId == 6l){
	              sb.append("   meetinAddress.panchayat_id in (:locationValues)"); 
	            }else if(locationTypeId==10l){
	              sb.append("  model.partyMeeting.meetingAddress.parliament_constituency_id in (:locationValues) "); 
	            }else if(locationTypeId == 7l){
	              sb.append("  meetinAddress.local_election_body in (:locationValues)");
	            }else if(locationTypeId == 8l){
	              sb.append("  meetinAddress.ward (:locationValues)"); 
	            }
	        }
	       
	       if(inviteeIds != null && inviteeIds.size() >0){
	    	   sb.append(" and TC.tdp_cadre_id in (:inviteeIds) ");
	       }
	       if(partyMeetingMainTypeid != null && partyMeetingMainTypeid.longValue() > 0l){
	    	   sb.append(" and PMMT.party_meeting_main_type_id = :partyMeetingMainTypeid ");
	       }
	       if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0l){
	    	   sb.append(" and PMT.party_meeting_type_id = :partyMeetingTypeId ");
	       }
	       if(fromDate != null && toDate != null){
	    	   sb.append(" and date(PM.start_date) between :fromDate and :toDate ");
	       }
	       if(partyMeetingId != null && partyMeetingId.longValue() > 0l){
	    	   sb.append(" and PM.party_meeting_id = :partyMeetingId ");
	       }
	      // sb.append(" group by model.partyMeeting.partyMeetingId order by model.partyMeeting.startDate desc ");
	       
	       SQLQuery query = getSession().createSQLQuery(sb.toString());
	       
	       if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){
	         query.setParameterList("locationValues", locationValues);
	       }
	       if(fromDate != null && toDate != null){
	         query.setDate("fromDate", fromDate);
	            query.setDate("toDate", toDate); 
	       }
	       if(partyMeetingMainTypeid != null && partyMeetingMainTypeid.longValue() > 0l){
	    	   query.setParameter("partyMeetingMainTypeid", partyMeetingMainTypeid);
	       }
	       if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0l){
	    	   query.setParameter("partyMeetingTypeId", partyMeetingTypeId);
	       }
	       if(partyMeetingId != null && partyMeetingId.longValue() > 0l){
	    	   query.setParameter("partyMeetingId", partyMeetingId);
	       }
	       
	       if(inviteeIds != null && inviteeIds.size() >0){
	    	   query.setParameterList("inviteeIds", inviteeIds); 
	       }
	       return query.list();
	     }
	
	public List<Object[]> getTdpCadreIdsByMeetingId(Long partyMeetingId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct pmi.tdp_cadre_id from party_meeting_invitee pmi where pmi.party_meeting_id =:partyMeetingId ");
		
		SQLQuery query = getSession().createSQLQuery(sb.toString());
		
		if(partyMeetingId != null && partyMeetingId.longValue() > 0l){
	    	   query.setParameter("partyMeetingId", partyMeetingId);
	       }
		 return query.list();
	}
}


