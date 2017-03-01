package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

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
			"             ,cadre.tdp_cadre_id as tdpCadre,time(a.attended_time) as attendedTime " +
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
			"             ,cadre.tdp_cadre_id as tdpCadre,time(a.attended_time) as attendedTime " +
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
			"             cadre.tdp_cadre_id as tdpCadre,time(a.attended_time) as attendedTime " +
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
    			"             cadre.tdp_cadre_id as tdpCadre,time(a.attended_time) as attendedTime " +
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
    			" where model.tdpCadreId in (:cadreIds) " +
    			" group by model.tdpCadreId ");
    	
    	query.setParameterList("cadreIds", cadreIds);
    	
    	return query.list();
    }
}  
