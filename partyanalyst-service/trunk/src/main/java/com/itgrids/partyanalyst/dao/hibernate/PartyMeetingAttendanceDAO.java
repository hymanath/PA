package com.itgrids.partyanalyst.dao.hibernate;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;
import com.itgrids.partyanalyst.dto.PartyMeetingsInputVO;
import com.itgrids.partyanalyst.model.PartyMeetingAttendance;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartyMeetingAttendanceDAO extends GenericDaoHibernate<PartyMeetingAttendance,Long> implements IPartyMeetingAttendanceDAO{

	public PartyMeetingAttendanceDAO()
	{
		super(PartyMeetingAttendance.class);
	}
	
	public List<Object[]> getPartyMeetingsAttendenceDetailsByCadreId(List<Long> tdpCadreIdsList,Date toDayDate)
	{
		StringBuilder queryStr = new StringBuilder();
		/*queryStr.append(" select distinct  PMA.partyMeeting.partyMeetingLevel.partyMeetingLevelId, PMA.partyMeeting.partyMeetingLevel.level, " +
				" PMA.partyMeeting.partyMeetingType.partyMeetingTypeId, PMA.partyMeeting.partyMeetingType.type, count( PMA.attendance.tdpCadreId)  from PartyMeetingAttendance PMA  where " +
				" date(PMA.partyMeeting.startDate) <= :toDayDate ");
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			queryStr.append("  and PMA.attendance.tdpCadreId in (:tdpCadreIdsList) ");
		queryStr.append(" group by PMA.partyMeeting.partyMeetingType.partyMeetingTypeId order by PMA.attendance.tdpCadreId ");*/
		
		queryStr.append(" select ");
		queryStr.append(" pm.party_meeting_level_id, ");
		queryStr.append(" pml.`level`, ");
		queryStr.append(" pm.party_meeting_type_id, ");
		queryStr.append(" pmt.type, ");
		queryStr.append(" count(DISTINCT CONCAT(pma.party_meeting_id,'-',a.tdp_cadre_id)) ");
		queryStr.append(" from  ");
		queryStr.append(" party_meeting_attendance pma,  ");
		queryStr.append(" attendance a , ");
		queryStr.append(" party_meeting pm, ");
		queryStr.append(" party_meeting_level pml, ");
		queryStr.append(" party_meeting_type pmt ");
		queryStr.append(" where  ");
		queryStr.append(" pm.party_meeting_type_id = pmt.party_meeting_type_id and  ");
		queryStr.append(" pm.party_meeting_level_id = pml.party_meeting_level_id and  ");
		queryStr.append(" pma.attendance_id = a.attendance_id and  ");
		queryStr.append(" a.tdp_cadre_id in (:tdpCadreIdsList) and  ");
		queryStr.append(" pm.party_meeting_id = pma.party_meeting_id   and pm.is_active='Y' ");
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			queryStr.append(" and date(pm.start_date)  <= :toDayDate ");
		queryStr.append(" group  by pm.party_meeting_type_id ");
		queryStr.append(" ORDER BY pmt.type ");
		
		Query query = getSession().createSQLQuery(queryStr.toString());
		 query.setDate("toDayDate", toDayDate);
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		return query.list();
	}
	
	public List<Object[]> getTotalAttendedDetailsForCadreIds(List<Long> tdpCadreIdsList,Long partyMeetingTypeId,Date todayDate)
	{
		boolean isSetWhere = false;
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct PMA.partyMeeting.partyMeetingId, PMA.partyMeeting.meetingName,PMA.partyMeeting.partyMeetingLevelId,PMA.partyMeeting.partyMeetingLevel.level, " +
				" PMA.partyMeeting.locationValue ,PMA.partyMeeting.partyMeetingType.partyMeetingTypeId, PMA.partyMeeting.partyMeetingType.type," +
				" date(PMA.partyMeeting.startDate),date(PMA.partyMeeting.endDate),  count(distinct PMA.attendance.tdpCadreId),PMA.partyMeeting.meetingAddress.localArea ," +
				" partyMeetingSession.partyMeetingSessionId,sessionType.type,partyMeetingSession.lateTime,PMA.attendance.attendedTime,partyMeetingSession.startTime " +
				" from PartyMeetingAttendance PMA " +
				" left join PMA.partyMeetingSession partyMeetingSession " +
				" left join partyMeetingSession.sessionType sessionType  ");
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
		
		queryStr.append(" and PMA.partyMeeting.isActive='Y' group by PMA.partyMeeting.partyMeetingId,partyMeetingSession.partyMeetingSessionId,PMA.attendance.tdpCadreId order by PMA.partyMeeting.partyMeetingId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0L)
			query.setParameter("partyMeetingTypeId", partyMeetingTypeId);		
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		if(todayDate != null )
			query.setDate("todayDate", todayDate);
		return query.list();
	}
	
	public List<Object[]> getAbsentMemberDeails(List<Long> tdpCadreIdsList , Long partyMeetingTypeId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct PMA.partyMeeting.partyMeetingId, PMA.partyMeeting.meetingName, count(distinct PMA.attendance.tdpCadreId) " +
				" from PartyMeetingAttendance PMA ,PartyMeetingInvitee PMI where PMA.partyMeeting.partyMeetingId = PMI.partyMeeting.partyMeetingId and " +
				" PMI.tdpCadreId = PMA.attendance.tdpCadreId ");
		if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0L){
			queryStr.append(" and PMA.partyMeeting.partyMeetingType.partyMeetingTypeId=:partyMeetingTypeId ");
		}
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			queryStr.append(" and  PMA.attendance.tdpCadreId in (:tdpCadreIdsList)  ");
		
		queryStr.append(" and PMA.partyMeeting.isActive='Y' group by PMA.partyMeeting.partyMeetingId order by PMA.partyMeeting.partyMeetingId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0L)
			query.setParameter("partyMeetingTypeId", partyMeetingTypeId);		
		if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		return query.list();
	}
	
	public List<Object[]> getAttendenceForCadre(Long tdpCadreId,Date todayDate)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct PMA.partyMeeting.partyMeetingId, PMA.partyMeeting.meetingName, count(PMA.attendance.tdpCadreId) " +
				" from PartyMeetingAttendance PMA where PMA.attendance.tdpCadreId =:tdpCadreId and date(PMA.partyMeeting.startDate) <= :todayDate ");
		queryStr.append("  and PMA.partyMeeting.isActive='Y'  group by PMA.partyMeeting.partyMeetingId order by PMA.partyMeeting.partyMeetingId ");
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
				" and model.attendance.tdpCadre.isDeleted='N'" +
				" and model.attendance.tdpCadre.enrollmentYear=:enrollmentYear and  model.partyMeeting.isActive='Y'  " +
				" group by model.partyMeeting.partyMeetingId");
		query.setParameterList("partyMeetingIds", partyMeetingIds);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
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
				" and model.attendance.tdpCadre.isDeleted='N' " +
				" and model.attendance.tdpCadre.enrollmentYear=:enrollmentYear and  model.partyMeeting.isActive='Y'  " +
				" group by model.partyMeeting.partyMeetingId");
		
		query.setParameterList("partyMeetingIds", partyMeetingIds);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAttendanceForMeetings(List<Long> partyMeetingsList)
	{
		Query query = getSession().createQuery("SELECT model.partyMeeting.partyMeetingId,model.attendance.tdpCadre.tdpCadreId FROM PartyMeetingAttendance model where model.partyMeeting.partyMeetingId in (:partyMeetingsList) "+
 		" and  model.partyMeeting.isActive='Y' group by model.partyMeeting.partyMeetingId,model.attendance.tdpCadre.tdpCadreId order by model.partyMeeting.partyMeetingId,model.attendance.tdpCadre.tdpCadreId ");
		query.setParameterList("partyMeetingsList",partyMeetingsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidateAttendanceForMeetings(List<Long> partyMeetingsList)
	{
		Query query = getSession().createQuery("SELECT model.partyMeeting.partyMeetingId,model.attendance.tdpCadre.tdpCadreId FROM PartyMeetingAttendance model,TdpCadreCandidate model2 where " +
				" model.attendance.tdpCadre.tdpCadreId = model2.tdpCadre.tdpCadreId and model.partyMeeting.partyMeetingId in (:partyMeetingsList) and model.partyMeeting.isActive='Y'  "+
 		" group by model.partyMeeting.partyMeetingId,model.attendance.tdpCadre.tdpCadreId order by model.partyMeeting.partyMeetingId,model.attendance.tdpCadre.tdpCadreId ");
		query.setParameterList("partyMeetingsList",partyMeetingsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCommitteeMemberAttendanceForMeetings(List<Long> partyMeetingsList)
	{
		Query query = getSession().createQuery("SELECT model.partyMeeting.partyMeetingId,model.attendance.tdpCadre.tdpCadreId FROM PartyMeetingAttendance model,TdpCommitteeMember model2 where " +
				"model.attendance.tdpCadre.tdpCadreId = model2.tdpCadre.tdpCadreId and model2.isActive = 'Y' and model.partyMeeting.partyMeetingId in (:partyMeetingsList) and model.partyMeeting.isActive='Y'  "+
 		" group by model.partyMeeting.partyMeetingId,model.attendance.tdpCadre.tdpCadreId order by model.partyMeeting.partyMeetingId,model.attendance.tdpCadre.tdpCadreId ");
		query.setParameterList("partyMeetingsList",partyMeetingsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getConductedMeetings(List<Long> partyMeetingsList)
	{
		Query query = getSession().createQuery("SELECT model.partyMeeting.partyMeetingId FROM PartyMeetingAttendance model where model.partyMeeting.partyMeetingId in (:partyMeetingsList) and model.attendance.tdpCadre is not null and " +
				" model.partyMeeting.isActive='Y' group by model.partyMeeting.partyMeetingId");
		query.setParameterList("partyMeetingsList",partyMeetingsList);
		return query.list();
	}
	

	 public BigInteger getLocationWiseTotalMeetingsCount(Long committeeLevelId,List<Long> committeeLevelValueList,Date fromDate,Date toDate)
		{
			StringBuilder queryStr = new StringBuilder();

				queryStr.append(" select count(DISTINCT model.party_meeting_id) from party_meeting_attendance model2,party_meeting model where model2.party_meeting_id = model.party_meeting_id and  model.is_active='Y' ");
				
				if(committeeLevelId != null && committeeLevelId.longValue()>0L)
				{
					if(committeeLevelId.longValue() == IConstants.VILLAGE_COMMITTEE_LEVEL_ID)
					{ 
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.VILLAGE_PARTY_MEETING_LEVEL_ID+" and  model.party_meeting_type_id = "+IConstants.MONTHLY_VILLAGEorWARD_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.WARD_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.WARD_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_VILLAGEorWARD_MEETING_ID+" ");
					}
					else if(committeeLevelId.longValue() == IConstants.MANDAL_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.MANDAL_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.TOWN_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.TOWN_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.DIVISION_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.DIVISION_PARTY_MEETING_LEVEL_ID+"  and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.CONSTITUENCY_COMMITTEE_LEVEL_ID) // inchargers
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.CONSTITUENCY_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_CONSTITUENCY_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.DISTRICT_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.DISTRICT_PARTY_MEETING_LEVEL_ID+"  and model.party_meeting_type_id = "+IConstants.MONTHLY_DISTRICT_MEETING_ID+"   ");
					}
					else if(committeeLevelId.longValue() == IConstants.STATE_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.STATE_PARTY_MEETING_LEVEL_ID+"  and model.party_meeting_type_id = "+IConstants.MONTHLY_STATE_MEETING_ID+"  ");
					}
				}
				
				if(committeeLevelValueList != null && committeeLevelValueList.size()>0)
					queryStr.append(" and model.location_value in (:committeeLevelValueList) ");
				
				if(fromDate != null && toDate != null)
				{
					queryStr.append(" and (model.start_date >= :fromDate and model.end_date <= :toDate) ");
				}
				
				
			Query query = getSession().createSQLQuery(queryStr.toString());
			if(committeeLevelValueList != null && committeeLevelValueList.size()>0)
				query.setParameterList("committeeLevelValueList", committeeLevelValueList);
			if(fromDate != null && toDate != null)
			{
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);
			}
			
			return (BigInteger) query.uniqueResult();
		}
	    
	 
	    public List<Object[]> getMontlyWiseMeetingsDetails(Long committeeLevelId,List<Long> committeeLevelValueList,Date fromDate,Date toDate,List<String> searchDatesList)
		{
			StringBuilder queryStr = new StringBuilder();
			
				queryStr.append(" select date(model.start_date) , count( DISTINCT model.party_meeting_id) from party_meeting_attendance model2,party_meeting model where model2.party_meeting_id = model.party_meeting_id and  model.is_active='Y' ");
				queryStr.append("");
				if(committeeLevelId != null && committeeLevelId.longValue()>0L)
				{
					if(committeeLevelId.longValue() == IConstants.VILLAGE_COMMITTEE_LEVEL_ID)
					{ 
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.VILLAGE_PARTY_MEETING_LEVEL_ID+" and  model.party_meeting_type_id = "+IConstants.MONTHLY_VILLAGEorWARD_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.WARD_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.WARD_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_VILLAGEorWARD_MEETING_ID+" ");
					}
					else if(committeeLevelId.longValue() == IConstants.MANDAL_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.MANDAL_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.TOWN_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.TOWN_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.DIVISION_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.DIVISION_PARTY_MEETING_LEVEL_ID+"  and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.CONSTITUENCY_COMMITTEE_LEVEL_ID) // inchargers
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.CONSTITUENCY_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_CONSTITUENCY_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.DISTRICT_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.DISTRICT_PARTY_MEETING_LEVEL_ID+"  and model.party_meeting_type_id = "+IConstants.MONTHLY_DISTRICT_MEETING_ID+"   ");
					}
					else if(committeeLevelId.longValue() == IConstants.STATE_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.STATE_PARTY_MEETING_LEVEL_ID+"  and model.party_meeting_type_id = "+IConstants.MONTHLY_STATE_MEETING_ID+"  ");
					}
				}
				
				if(committeeLevelValueList != null && committeeLevelValueList.size()>0)
					queryStr.append(" and model.location_value in (:committeeLevelValueList) ");
				
				if(searchDatesList != null && searchDatesList.size()>0)					
					queryStr.append(" and  date(model.start_date) in (:searchDatesList) ");
				else if(fromDate != null && toDate != null)
				{
					queryStr.append(" and (model.start_date >= :fromDate and model.end_date <= :toDate) ");
				}
				//queryStr.append(" and model2.is_conducted_by_ivr = 'Y' or model2.is_conducted_by_pc ='Y' ");
				queryStr.append(" group by   date(model.start_date) ");
							
			Query query = getSession().createSQLQuery(queryStr.toString());
			if(committeeLevelValueList != null && committeeLevelValueList.size()>0)
				query.setParameterList("committeeLevelValueList", committeeLevelValueList);
			if(searchDatesList != null && searchDatesList.size()>0)	
				query.setParameterList("searchDatesList", searchDatesList);
			else if(fromDate != null && toDate != null)
			{
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);
			}

			return query.list();
		}
	    
	    public List<Long> getAttendedCadreIdsByPartyMeetingId(Long partyMeetingId){
	    	
	    	Query query = getSession().createQuery(" select distinct model.attendance.tdpCadre.tdpCadreId " +
	    						" from PartyMeetingAttendance model " +
	    						" where model.partyMeeting.partyMeetingId = :partyMeetingId  and  model.attendance.tdpCadre.isDeleted='N' and model.partyMeeting.isActive='Y'  and " +
	    						"  model.attendance.tdpCadre.enrollmentYear ="+IConstants.CADRE_ENROLLMENT_NUMBER+" ");
	    	query.setParameter("partyMeetingId", partyMeetingId);
	    	
	    	return query.list();
	    }
	    
	  //attended count.
		public List<Object[]> getAttendedCountForPartyMeetingTypeIds(PartyMeetingsInputVO inputVO){
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(" select  PMT.party_meeting_type_id as partyMeetingId , PMT.type as type ,COUNT(DISTINCT CONCAT(PMA.party_meeting_id,'-',A.tdp_cadre_id)) as count " +
					 "  from    party_meeting_attendance PMA,attendance A,party_meeting PM,party_meeting_type PMT,party_meeting_main_type PMMT,tdp_cadre TC,user_address UA  " +
					 "  where   PMA.attendance_id = A.attendance_id and " +
					 "          A.tdp_cadre_id = TC.tdp_cadre_id and " +
					 "          PMA.party_meeting_id = PM.party_meeting_id and " +
					 "          PM.party_meeting_type_id = PMT.party_meeting_type_id and " +
					 "          PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and PM.is_active='Y' and " +
					 "          PM.meeting_address_id = UA.user_address_id and " +//////
					 "          PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
					 "          TC.is_deleted = 'N' and TC.enrollment_year = 2014 ");

	 		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
				 sb.append(" and date(PM.start_date) between :startDate and :endDate ");	 
			}
	 		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
	 			sb.append(" and UA.state_id = :stateId ");/////
			}
			if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			   sb.append(" and PMT.party_meeting_type_id in (:partyMeetingTypeIds) ");	
			}
			if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size()>0){
				sb.append(" and PM.party_meeting_id in (:partyMeetingIds) ");
			}
			sb.append(" GROUP BY PMT.party_meeting_type_id");////
			
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
		  //attended count.
public List<Object[]> getDistrictWiseAttendedCountForPartyMeetingTypeIds(PartyMeetingsInputVO inputVO){
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(" select  PMT.party_meeting_type_id as partyMeetingId , PMT.type as type ,D.district_id as districtId,D.district_name as districtName,COUNT(DISTINCT CONCAT(PMA.party_meeting_id,'-',A.tdp_cadre_id)) as count " +
					 "  from    party_meeting_attendance PMA,attendance A,party_meeting PM,party_meeting_type PMT,party_meeting_main_type PMMT,tdp_cadre TC,user_address UA,district D  " +
					 "  where   PMA.attendance_id = A.attendance_id and " +
					 "          A.tdp_cadre_id = TC.tdp_cadre_id and " +
					 "          PMA.party_meeting_id = PM.party_meeting_id and " +
					 "          PM.party_meeting_type_id = PMT.party_meeting_type_id and " +
					 "          PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and " +
					 "          TC.address_id = UA.user_address_id and " +//////
					 "          UA.district_id = D.district_id and " +
					 "          PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and PM.is_active='Y' and " +
					 "          TC.is_deleted = 'N' and TC.enrollment_year = 2014 ");

	 		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
				 sb.append(" and date(PM.start_date) between :startDate and :endDate ");	 
			}
	 		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
	 			if(inputVO.getStateId().longValue()==1l){
					sb.append(" and D.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");	
				}else if(inputVO.getStateId().longValue()==36l){
					sb.append(" and D.district_id in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
				}
			}
			if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			   sb.append(" and PMT.party_meeting_type_id in (:partyMeetingTypeIds) ");	
			}
			sb.append(" GROUP BY PMT.party_meeting_type_id,D.district_id order by PMT.party_meeting_type_id,D.district_id asc");////
			
			Query query = getSession().createSQLQuery(sb.toString())
			.addScalar("partyMeetingId",Hibernate.LONG)
			.addScalar("type",Hibernate.STRING)
			.addScalar("districtId",Hibernate.LONG)
		    .addScalar("districtName",Hibernate.STRING)
			.addScalar("count",Hibernate.LONG);
			
			if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
				query.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
			}
			if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
				query.setDate("startDate",inputVO.getStartDate());
				query.setDate("endDate",inputVO.getEndDate());	 
			}
			query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
		   return query.list();
			
		}	

public List<Object[]> getAttendedCadreCountForMeetingsByCommitteeWise(PartyMeetingsInputVO inputVO){
	
	StringBuilder sb = new StringBuilder();
	
	sb.append("select tcl.tdp_committee_level_id as tdpCommitteeLevelId , tcl.tdp_committee_level as tdpcommitteeLevel," +
			"         COUNT(DISTINCT CONCAT(pm.party_meeting_id,'-',cadre.tdp_cadre_id)) as count " +
			 " from   party_meeting_attendance pma join attendance a on pma.attendance_id = a.attendance_id  " +
			 "        join tdp_cadre cadre on a.tdp_cadre_id = cadre.tdp_cadre_id " +
			 "        join party_meeting pm on pma.party_meeting_id = pm.party_meeting_id " +
			 "        join user_address ua on pm.meeting_address_id = ua.user_address_id " +
			 "        join party_meeting_type pmt on pm.party_meeting_type_id = pmt.party_meeting_type_id " +
			 "        join party_meeting_main_type pmmt on pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id " +
			 "" +
			 "        join tdp_committee_member tcm  on tcm.tdp_cadre_id = cadre.tdp_cadre_id " +
			 "        join tdp_committee_role   tcr  on tcm.tdp_committee_role_id = tcr.tdp_committee_role_id " +
			 "        join tdp_committee        tc   on tcr.tdp_committee_id = tc.tdp_committee_id " +
			 "        join tdp_committee_level  tcl  on tc.tdp_committee_level_id = tcl.tdp_committee_level_id " +
			 " where  pmmt.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
			 "        cadre.is_deleted = 'N' and cadre.enrollment_year = 2014 and pm.is_active='Y' ");
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

public List<Object[]> getAttendedCadreCountForMeetingsByByPublicRepresentativeWise(PartyMeetingsInputVO inputVO){
 StringBuilder sb = new StringBuilder();
 
 sb.append(" select   prt.public_representative_type_id as representativeTypeId,prt.position as position ,COUNT(DISTINCT CONCAT(pm.party_meeting_id,'-',cadre.tdp_cadre_id)) as count " +
 		"    from     party_meeting_attendance pma join attendance a on pma.attendance_id = a.attendance_id " +
 		"             join tdp_cadre cadre on a.tdp_cadre_id = cadre.tdp_cadre_id " +
 		"             join party_meeting pm on pma.party_meeting_id = pm.party_meeting_id " +
 		"             join user_address ua on pm.meeting_address_id = ua.user_address_id " +
 		"             join party_meeting_type pmt on pm.party_meeting_type_id = pmt.party_meeting_type_id" +
 		"             join party_meeting_main_type pmmt on pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id " +
 		"" +
 		"             join tdp_cadre_candidate tcc  on tcc.tdp_cadre_id = cadre.tdp_cadre_id" +
 		"             join candidate c on tcc.candidate_id = c.candidate_id " +
 		"             join public_representative pr on c.candidate_id = pr.candidate_id " +
 		"             join public_representative_type prt on pr.public_representative_type_id = prt.public_representative_type_id " +
 		"   where     pmmt.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
 		"             cadre.is_deleted = 'N' and cadre.enrollment_year = 2014 and pm.is_active='Y'  ");
 
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
public List<Long> getAttendedMemberCadreId(PartyMeetingsInputVO inputVO){
	
	StringBuilder sb = new StringBuilder();     
	
	sb.append(" select  distinct TC.tdp_cadre_id as id " +
			 "  from    party_meeting_attendance PMA,attendance A,party_meeting PM,party_meeting_type PMT,party_meeting_main_type PMMT,tdp_cadre TC,user_address UA  " +
			 "  where   PMA.attendance_id = A.attendance_id and " +
			 "          A.tdp_cadre_id = TC.tdp_cadre_id and " +
			 "          PMA.party_meeting_id = PM.party_meeting_id and " +
			 "          PM.party_meeting_type_id = PMT.party_meeting_type_id and " +
			 "          PMT.party_meeting_main_type_id = PMMT.party_meeting_main_type_id and " +
			 "          PM.meeting_address_id = UA.user_address_id and " +//////
			 "          PMMT.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
			 "          TC.is_deleted = 'N' and TC.enrollment_year = 2014 and PM.is_active='Y' ");

		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
		 sb.append(" and date(PM.start_date) between :startDate and :endDate ");	 
	}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			sb.append(" and UA.state_id = :stateId ");/////
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
//santosh
public List<Object[]> getCommitteeWiseAttendedCadreCountForMeeting(PartyMeetingsInputVO inputVO){
	
	StringBuilder sb = new StringBuilder();
	
	sb.append("select tcl.tdp_committee_level_id as tdpCommitteeLevelId ,tcl.tdp_committee_level as tdpcommitteeLevel,st.session_type_id as sessionTypeId,cadre.tdp_cadre_id as tdpCadre,min(time(a.attended_time)) attendedTime " +
			 " from   party_meeting_attendance pma join attendance a on pma.attendance_id = a.attendance_id  " +
			 "        join tdp_cadre cadre on a.tdp_cadre_id = cadre.tdp_cadre_id " +
			 "        join party_meeting pm on pma.party_meeting_id = pm.party_meeting_id " +
			 "        join user_address ua on pm.meeting_address_id = ua.user_address_id " +
			 "        join party_meeting_type pmt on pm.party_meeting_type_id = pmt.party_meeting_type_id " +
			 "        join party_meeting_main_type pmmt on pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id " +
			 "		  join party_meeting_session pms on pms.party_meeting_session_id = pma.party_meeting_session_id"+ 
	         "		  join session_type st on pms.session_type_id = st.session_type_id"+
			 
	    	 "" +
			 "        join tdp_committee_member tcm  on tcm.tdp_cadre_id = cadre.tdp_cadre_id " +
			 "        join tdp_committee_role   tcr  on tcm.tdp_committee_role_id = tcr.tdp_committee_role_id " +
			 "        join tdp_committee        tc   on tcr.tdp_committee_id = tc.tdp_committee_id " +
			 "        join tdp_committee_level  tcl  on tc.tdp_committee_level_id = tcl.tdp_committee_level_id " +
			 " where  pmmt.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
			 "        cadre.is_deleted = 'N' and cadre.enrollment_year = 2014 and pm.is_active='Y' ");
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
	sb.append(" group by tcl.tdp_committee_level_id,concat(st.session_type_id,'-',cadre.tdp_cadre_id) ");
	
	Query query = getSession().createSQLQuery(sb.toString())
	.addScalar("tdpCommitteeLevelId",Hibernate.LONG)
	.addScalar("tdpcommitteeLevel",Hibernate.STRING)
	.addScalar("sessionTypeId",Hibernate.LONG)
	.addScalar("tdpCadre",Hibernate.LONG)
	.addScalar("attendedTime",Hibernate.STRING);  

	
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

//srishailam
public List<Object[]> getWithoutSessionCommitteeWiseAttendedCadreCountForMeeting(PartyMeetingsInputVO inputVO){
	
	StringBuilder sb = new StringBuilder();
	
	sb.append("select tcl.tdp_committee_level_id as tdpCommitteeLevelId ,tcl.tdp_committee_level as tdpcommitteeLevel,pma.party_meeting_session_id as sessionTypeId,cadre.tdp_cadre_id as tdpCadre,min(time(a.attended_time)) attendedTime " +
			 " from   party_meeting_attendance pma join attendance a on pma.attendance_id = a.attendance_id  " +
			 "        join tdp_cadre cadre on a.tdp_cadre_id = cadre.tdp_cadre_id " +
			 "        join party_meeting pm on pma.party_meeting_id = pm.party_meeting_id " +
			 "        join user_address ua on pm.meeting_address_id = ua.user_address_id " +
			 "        join party_meeting_type pmt on pm.party_meeting_type_id = pmt.party_meeting_type_id " +
			 "        join party_meeting_main_type pmmt on pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id " +
			// "		  join party_meeting_session pms on pms.party_meeting_session_id = pma.party_meeting_session_id"+ 
	       //  "		  join session_type st on pms.session_type_id = st.session_type_id"+
			 
	    	// "" +
			 "        join tdp_committee_member tcm  on tcm.tdp_cadre_id = cadre.tdp_cadre_id " +
			 "        join tdp_committee_role   tcr  on tcm.tdp_committee_role_id = tcr.tdp_committee_role_id " +
			 "        join tdp_committee        tc   on tcr.tdp_committee_id = tc.tdp_committee_id " +
			 "        join tdp_committee_level  tcl  on tc.tdp_committee_level_id = tcl.tdp_committee_level_id " +
			 " where  pmmt.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
			 "        cadre.is_deleted = 'N' and cadre.enrollment_year = 2014 and pm.is_active='Y' and pma.party_meeting_session_id is null ");
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
	sb.append(" group by tcl.tdp_committee_level_id,concat(pm.party_meeting_id,'-',cadre.tdp_cadre_id) ");
	
	Query query = getSession().createSQLQuery(sb.toString())
	.addScalar("tdpCommitteeLevelId",Hibernate.LONG)
	.addScalar("tdpcommitteeLevel",Hibernate.STRING)
	.addScalar("sessionTypeId",Hibernate.LONG)
	.addScalar("tdpCadre",Hibernate.LONG)
	.addScalar("attendedTime",Hibernate.STRING);  

	
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
public List<Object[]> getPublicRepresentativeWiseAttendedCadreCountForMeeting(PartyMeetingsInputVO inputVO){
	 StringBuilder sb = new StringBuilder();
	 
	 sb.append(" select   prt.public_representative_type_id as representativeTypeId,prt.position as position ,st.session_type_id as sessionTypeId,cadre.tdp_cadre_id as tdpCadre,min(time(a.attended_time)) attendedTime " +
	 		"    from     party_meeting_attendance pma join attendance a on pma.attendance_id = a.attendance_id " +
	 		"             join tdp_cadre cadre on a.tdp_cadre_id = cadre.tdp_cadre_id " +
	 		"             join party_meeting pm on pma.party_meeting_id = pm.party_meeting_id " +
	 		"             join user_address ua on pm.meeting_address_id = ua.user_address_id " +
	 		"             join party_meeting_type pmt on pm.party_meeting_type_id = pmt.party_meeting_type_id" +
	 		"             join party_meeting_main_type pmmt on pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id " +
			"		      join party_meeting_session pms on pms.party_meeting_session_id = pma.party_meeting_session_id"+ 
	        "		      join session_type st on pms.session_type_id = st.session_type_id"+
	
	 		"" +
	 		"             join tdp_cadre_candidate tcc  on tcc.tdp_cadre_id = cadre.tdp_cadre_id" +
	 		"             join candidate c on tcc.candidate_id = c.candidate_id " +
	 		"             join public_representative pr on c.candidate_id = pr.candidate_id " +
	 		"             join public_representative_type prt on pr.public_representative_type_id = prt.public_representative_type_id " +
	 		"   where     pmmt.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
	 		"             cadre.is_deleted = 'N' and cadre.enrollment_year = 2014 and pm.is_active='Y'  ");
	 
	/* if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
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
	if(inputVO.getCategory().equalsIgnoreCase("Representative")){
		sb.append(" and prt.public_representative_type_id in (:publicRepresentativeTypeIds) ");
	}else if(inputVO.getCategory().equalsIgnoreCase("other")){
		sb.append(" and prt.public_representative_type_id not in (:publicRepresentativeTypeIds) ");
	}
	sb.append(" group by prt.public_representative_type_id,concat(st.session_type_id,'-',cadre.tdp_cadre_id) ");

	Query query = getSession().createSQLQuery(sb.toString())
	.addScalar("representativeTypeId",Hibernate.LONG)
	.addScalar("position",Hibernate.STRING)
	.addScalar("sessionTypeId",Hibernate.LONG)
	.addScalar("tdpCadre",Hibernate.LONG)
	.addScalar("attendedTime",Hibernate.STRING);

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


public List<Object[]> getWithioutPublicRepresentativeWiseAttendedCadreCountForMeeting(PartyMeetingsInputVO inputVO){
	 StringBuilder sb = new StringBuilder();
	 
	 sb.append(" select   prt.public_representative_type_id as representativeTypeId,prt.position as position ,pma.party_meeting_session_id as sessionTypeId,cadre.tdp_cadre_id as tdpCadre,min(time(a.attended_time)) attendedTime " +
	 		"    from     party_meeting_attendance pma join attendance a on pma.attendance_id = a.attendance_id " +
	 		"             join tdp_cadre cadre on a.tdp_cadre_id = cadre.tdp_cadre_id " +
	 		"             join party_meeting pm on pma.party_meeting_id = pm.party_meeting_id " +
	 		"             join user_address ua on pm.meeting_address_id = ua.user_address_id " +
	 		"             join party_meeting_type pmt on pm.party_meeting_type_id = pmt.party_meeting_type_id" +
	 		"             join party_meeting_main_type pmmt on pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id " +
			//"		      join party_meeting_session pms on pms.party_meeting_session_id = pma.party_meeting_session_id"+ 
	      //  "		      join session_type st on pms.session_type_id = st.session_type_id"+
	
	 		"" +
	 		"             join tdp_cadre_candidate tcc  on tcc.tdp_cadre_id = cadre.tdp_cadre_id" +
	 		"             join candidate c on tcc.candidate_id = c.candidate_id " +
	 		"             join public_representative pr on c.candidate_id = pr.candidate_id " +
	 		"             join public_representative_type prt on pr.public_representative_type_id = prt.public_representative_type_id " +
	 		"   where     pmmt.party_meeting_main_type_id = :partyMeetingMainTypeId and " +
	 		"             cadre.is_deleted = 'N' and cadre.enrollment_year = 2014 and pm.is_active='Y' and pma.party_meeting_session_id is null ");
	 
	/* if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
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
	if(inputVO.getCategory().equalsIgnoreCase("Representative")){
		sb.append(" and prt.public_representative_type_id in (:publicRepresentativeTypeIds) ");
	}else if(inputVO.getCategory().equalsIgnoreCase("other")){
		sb.append(" and prt.public_representative_type_id not in (:publicRepresentativeTypeIds) ");
	}
	sb.append(" group by prt.public_representative_type_id,concat(pm.party_meeting_id,'-',cadre.tdp_cadre_id) ");

	Query query = getSession().createSQLQuery(sb.toString())
	.addScalar("representativeTypeId",Hibernate.LONG)
	.addScalar("position",Hibernate.STRING)
	.addScalar("sessionTypeId",Hibernate.LONG)
	.addScalar("tdpCadre",Hibernate.LONG)
	.addScalar("attendedTime",Hibernate.STRING);

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
public List<Object[]> getSpecialMeetingsSessionWiseAttendence(List<Long> partyMeetingIdsList,Long mainTypeId){
	StringBuilder queryStr = new StringBuilder();
	queryStr.append(" SELECT "+	
			" pmt.party_meeting_type_id,pm.party_meeting_id,pms.party_meeting_session_id,st.type,a.tdp_cadre_id ,a.attended_time,min(time(a.attended_time))," +
			" pms.late_time,st.late_time  "+
			" from  "+
			" party_meeting pm, party_meeting_session pms, "+
			" party_meeting_attendance pma, "+
			" attendance a , "+
			" session_type st, "+
			" party_meeting_type pmt, "+
			" party_meeting_main_type pmmt "+
			" where  "+
			" pm.party_meeting_type_id = pmt.party_meeting_type_id and  "+
			" pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id and  "+
			" pm.party_meeting_id = pms.party_meeting_id and  "+
			" pms.party_meeting_session_id = pma.party_meeting_session_id and  "+
			" pma.party_meeting_id = pm.party_meeting_id and  "+
			" a.attendance_id = pma.attendance_id and  "+
			" pms.session_type_id = st.session_type_id   "+
			" and pmmt.party_meeting_main_type_id = :mainTypeId and pm.is_active='Y'  ");
	
	if(partyMeetingIdsList != null && partyMeetingIdsList.size()>0){
		queryStr.append(" and pm.party_meeting_id in (:partyMeetingIds) ");
	}
	queryStr.append(" GROUP BY  "+
			" concat(pms.party_meeting_session_id,'-',a.tdp_cadre_id) "+
			" order by  "+
			" pm.party_meeting_id,pms.party_meeting_session_id,st.type,a.tdp_cadre_id  ");
	
	Query query = getSession().createSQLQuery(queryStr.toString());
	if(partyMeetingIdsList != null && partyMeetingIdsList.size()>0){
		query.setParameterList("partyMeetingIds",partyMeetingIdsList);
	}
	query.setParameter("mainTypeId",mainTypeId);
	
	return query.list();
}

public List<Object[]> getNoSesstionSpecialMeetingsSessionWiseAttendence(List<Long> partyMeetingIdsList,Long mainTypeId){
	StringBuilder queryStr = new StringBuilder();
	queryStr.append(" SELECT "+	
			" pmt.party_meeting_type_id,pm.party_meeting_id,'0','',a.tdp_cadre_id ,a.attended_time,min(time(a.attended_time))," +
			" '',''  "+
			" from  "+
			" party_meeting pm," +
			//" party_meeting_session pms, "+
			" party_meeting_attendance pma, "+
			" attendance a , "+
			//" session_type st, "+
			" party_meeting_type pmt, "+
			" party_meeting_main_type pmmt "+
			" where  "+
			" pm.party_meeting_type_id = pmt.party_meeting_type_id and  "+
			" pmt.party_meeting_main_type_id = pmmt.party_meeting_main_type_id and  "+
			//" pm.party_meeting_id = pms.party_meeting_id and  "+
			//" pms.party_meeting_session_id = pma.party_meeting_session_id and  "+
			" pma.party_meeting_id = pm.party_meeting_id and  "+
			" a.attendance_id = pma.attendance_id and  "+
			//" pms.session_type_id = st.session_type_id   "+
			"  pmmt.party_meeting_main_type_id = :mainTypeId  and pm.is_active='Y'  ");
	
	if(partyMeetingIdsList != null && partyMeetingIdsList.size()>0){
		queryStr.append(" and pm.party_meeting_id in (:partyMeetingIds) ");
	}
	queryStr.append(" GROUP BY  "+
			" concat(pm.party_meeting_id,'-',a.tdp_cadre_id) "+
			" order by  "+
			" pm.party_meeting_id,a.tdp_cadre_id  ");
	
	Query query = getSession().createSQLQuery(queryStr.toString());
	if(partyMeetingIdsList != null && partyMeetingIdsList.size()>0){
		query.setParameterList("partyMeetingIds",partyMeetingIdsList);
	}
	query.setParameter("mainTypeId",mainTypeId);
	return query.list();
}
	
	public List<Object[]> getPartyMeetingAttendanceSummary(List<Long> cadreIds){
		Query query = getSession().createQuery(" select model.attendance.tdpCadreId,count(distinct model.partyMeetingId) " +
				" from PartyMeetingAttendance model " +
				" where model.attendance.tdpCadreId in (:cadreIds) " +
				" group by model.attendance.tdpCadreId ");
		
		query.setParameterList("cadreIds", cadreIds);
		
		return query.list();
	}
	public List<Object[]> getAttendedCadresMeetingWise(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet){  
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
				" model.partyMeeting.partyMeetingId, " +
				" model.partyMeeting.meetingName, " +
				" model.partyMeetingSession.partyMeetingSessionId, " +
				" model.attendance.tdpCadre.tdpCadreId, " +
				" model.attendance.attendedTime, " +
				" date(model.attendance.attendedTime), " +
				" min(time(model.attendance.attendedTime)) " +
				" from " +
				" PartyMeetingAttendance model " +
				" where " +
				" model.attendance.tdpCadre.isDeleted = 'N' " +
				" and model.partyMeeting.isActive = 'Y' " +
				" and model.partyMeetingSession.isDeleted = 'N' ");
		if(inputVO.getPartyMeetingMainTypeId() != null && inputVO.getPartyMeetingMainTypeId().longValue() > 0L){
			queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetingMainTypeId " );
    	}  
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeIdList) " );
    	}
    	if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
    		queryStr.append(" and date(model.partyMeeting.startDate) between :startDate and :endDate " );
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
    	
		queryStr.append(" group by model.partyMeeting.partyMeetingId,model.partyMeetingSession.partyMeetingSessionId,model.attendance.tdpCadre.tdpCadreId ");
		
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
	public List<Object[]> getAttendedCadresInMeeting(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet){  
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
				" model.partyMeeting.partyMeetingId, " +
				" model.partyMeeting.meetingName, " +
				" model.partyMeetingSession.partyMeetingSessionId, " +
				" model.attendance.tdpCadre.tdpCadreId, " +
				" model.attendance.attendedTime, " +
				" date(model.attendance.attendedTime), " +
				" min(time(model.attendance.attendedTime)) " +
				" from " +
				" PartyMeetingAttendance model,PartyMeetingGroupsMappingInfo model1  " +
				" where " +
				" model.attendance.tdpCadre.isDeleted = 'N' " +
				" and model.partyMeeting.isActive = 'Y' " +
				" and model.partyMeetingSession.isDeleted = 'N'" +
				" and model1.partyMeeting.partyMeetingId = model.partyMeeting.partyMeetingId  ");
		if(inputVO.getPartyMeetingMainTypeId() != null && inputVO.getPartyMeetingMainTypeId().longValue() > 0L){
			queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetingMainTypeId " );
    	}  
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeIdList) " );
    	}
    	if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
    		queryStr.append(" and date(model.partyMeeting.startDate) between :startDate and :endDate " );
    	}
    	if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0l){
			queryStr.append(" and model1.partyMeetingGroup.partyMeetingGroupId = :partyMeetngGrpId ");
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
		queryStr.append(" group by model.partyMeeting.partyMeetingId,model.partyMeetingSession.partyMeetingSessionId,model.attendance.tdpCadre.tdpCadreId ");
		
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
	public List<Object[]> getAttendedCadresMeetingWiseForLevel(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet,List<Long> locLevelIdList){  
		StringBuilder queryStr = new StringBuilder();   
		queryStr.append(" select " +
				" model.partyMeeting.partyMeetingId, " +//0
				" model.partyMeeting.meetingName, " +//1
				" model.partyMeetingSession.sessionType.sessionTypeId, " +//2
				" model.attendance.tdpCadre.tdpCadreId, " +//3
				" model.attendance.attendedTime, " +//4
				" date(model.attendance.attendedTime), " +//5
				" min(time(model.attendance.attendedTime)), " +//6
				" model.partyMeeting.meetingAddress.district.districtId, " +//7
				" model.partyMeeting.partyMeetingLevelId,model3.sessionType.sessionTypeId " +//8
				" from " +
				" PartyMeetingAttendance model, PartyMeetingGroupsMappingInfo model2,PartyMeetingSession model3  " +
				" where " +
				" model.attendance.tdpCadre.isDeleted = 'N' " +
				" and model.attendance.tdpCadre.enrollmentYear = 2014 " +
				" and model.partyMeeting.isActive = 'Y' " +
				" and model.partyMeetingSession.isDeleted = 'N' " +
				" and model3.isDeleted = 'N' and model3.partyMeeting.partyMeetingId = model.partyMeeting.partyMeetingId " +
				" and model.partyMeeting.partyMeetingId = model2.partyMeeting.partyMeetingId and model.partyMeeting.partyMeetingLevelId = 2 ");
		if(inputVO.getPartyMeetingMainTypeId() != null && inputVO.getPartyMeetingMainTypeId().longValue() > 0L){
			queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetingMainTypeId " );
    	}  
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeIdList) " );
    	}
    	if(inputVO.getSessionId() != null && inputVO.getSessionId().longValue() > 0L){
			queryStr.append(" and model3.sessionType.sessionTypeId = :sessionTypeId " );
    	}
    	if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
    		queryStr.append(" and date(model.partyMeeting.startDate) between :startDate and :endDate " );
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
    	if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0L){
    		queryStr.append(" and model2.partyMeetingGroup.partyMeetingGroupId = :partyMeetingGroupId ");
    	}
    	if(locLevelIdList != null && locLevelIdList.size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingLevelId in (:locLevelIdList) ");
    	}
    	if(inputVO.getStateId().longValue() == 1L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");	
    	}else if(inputVO.getStateId().longValue() == 36L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
    	}    
		queryStr.append(" group by model.partyMeeting.partyMeetingId,model.partyMeetingSession.sessionType.sessionTypeId,model.attendance.tdpCadre.tdpCadreId ");
		
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
    	if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0L){
    		query.setParameter("partyMeetingGroupId", inputVO.getPartyMeetingGroupId());
    	} 
    	if(locLevelIdList != null && locLevelIdList.size() > 0){
    		query.setParameterList("locLevelIdList",locLevelIdList);
    	}
    	if(inputVO.getSessionId() != null && inputVO.getSessionId().longValue() > 0L){
    		query.setParameter("sessionTypeId", inputVO.getSessionId());
    	}
    	return query.list();
	}
	public List<Object[]> getAttendedCadresOfCommitteeMeetingWiseForLevel(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet,List<Long> locLevelIdList,String isRequired){  
		StringBuilder queryStr = new StringBuilder();   
		queryStr.append(" select  " +
				" model.partyMeeting.partyMeetingId, " +//0 
				" model.partyMeeting.meetingName, " +//1
				" model.partyMeetingSession.sessionType.sessionTypeId, " +//2 
				" model.attendance.tdpCadre.tdpCadreId, " +//3
				" model.attendance.attendedTime, " +//4
				" date(model.attendance.attendedTime), " +//5
				" min(time(model.attendance.attendedTime)), " +//6
				" model3.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId, " +//7
				" model3.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel " +//8  
				" from " +
				" PartyMeetingAttendance model, PartyMeetingGroupsMappingInfo model2, TdpCommitteeMember model3  " +
				" where " +
				" model.attendance.tdpCadre.isDeleted = 'N' " +
				" and model.attendance.tdpCadre.enrollmentYear = 2014 " +
				" and model.partyMeeting.isActive = 'Y' " +
				" and model.partyMeetingSession.isDeleted = 'N' " +
				" and model.partyMeeting.partyMeetingId = model2.partyMeeting.partyMeetingId " +
				" " +
				" and model.attendance.tdpCadre.tdpCadreId = model3.tdpCadre.tdpCadreId ");
		if(isRequired != null && isRequired.equalsIgnoreCase("true")){
        	queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in ("+IConstants.REQUIRED_COMMITTEE_LEVEL_IDS+") ");
    	}else{
        	queryStr.append(" and model3.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in ("+IConstants.REMAINING_COMMITTEE_LEVEL_IDS+") ");
    	}
		if(inputVO.getPartyMeetingMainTypeId() != null && inputVO.getPartyMeetingMainTypeId().longValue() > 0L){
			queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetingMainTypeId " );
    	}  
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeIdList) " );
    	}
    	if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
    		queryStr.append(" and date(model.partyMeeting.startDate) between :startDate and :endDate " );
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
    	if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0L){
    		queryStr.append(" and model2.partyMeetingGroup.partyMeetingGroupId = :partyMeetingGroupId ");
    	}
    	if(locLevelIdList != null && locLevelIdList.size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingLevelId in (:locLevelIdList) ");
    	}
    	if(inputVO.getStateId().longValue() == 1L){
    		queryStr.append(" and model.attendance.tdpCadre.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");	
    	}else if(inputVO.getStateId().longValue() == 36L){
    		queryStr.append(" and model.attendance.tdpCadre.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
    	}    
		queryStr.append(" group by model.partyMeeting.partyMeetingId,model.partyMeetingSession.sessionType.sessionTypeId,model.attendance.tdpCadre.tdpCadreId ");
		
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
    	if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0L){
    		query.setParameter("partyMeetingGroupId", inputVO.getPartyMeetingGroupId());
    	} 
    	if(locLevelIdList != null && locLevelIdList.size() > 0){
    		query.setParameterList("locLevelIdList",locLevelIdList);
    	}
    	return query.list();
	}
	public List<Object[]> getAttendedCadresOfPublicRepresentativeMeetingWiseForLevel(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet,List<Long> locLevelIdList,String isRequired){  
		StringBuilder queryStr = new StringBuilder();   
		queryStr.append(" select " +
				" model.partyMeeting.partyMeetingId, " +//0
				" model.partyMeeting.meetingName, " +//1
				" model.partyMeetingSession.sessionType.sessionTypeId, " +//2 
				" model.attendance.tdpCadre.tdpCadreId, " +//3
				" model.attendance.attendedTime, " +//4
				" date(model.attendance.attendedTime), " +//5
				" min(time(model.attendance.attendedTime)), " +//6
				" model4.publicRepresentativeType.publicRepresentativeTypeId, " +//7
				" model4.publicRepresentativeType.type " +//8  
				" from " +
				" PartyMeetingAttendance model, PartyMeetingGroupsMappingInfo model2, TdpCadreCandidate model3, PublicRepresentative model4  " +
				" where " +
				" model.attendance.tdpCadre.isDeleted = 'N' " +
				" and model.attendance.tdpCadre.enrollmentYear = 2014 " +
				" and model.partyMeeting.isActive = 'Y' " +
				" and model.partyMeetingSession.isDeleted = 'N' " +
				" and model.partyMeeting.partyMeetingId = model2.partyMeeting.partyMeetingId " +
				" " +
				" and model.attendance.tdpCadre.tdpCadreId = model3.tdpCadre.tdpCadreId " +
				" and model3.candidate.candidateId = model4.candidate.candidateId ");
		if(isRequired != null && isRequired.equalsIgnoreCase("true")){
    		queryStr.append(" and model4.publicRepresentativeType.publicRepresentativeTypeId in ("+IConstants.REQUIRED_PUBLIC_REPRESENTATIVE_TYPE_IDS+") ");
    	}else{
    		queryStr.append(" and model4.publicRepresentativeType.publicRepresentativeTypeId in ("+IConstants.REMAINING_PUBLIC_REPRESENTATIVE_TYPE_IDS+") ");
    	} 
		if(inputVO.getPartyMeetingMainTypeId() != null && inputVO.getPartyMeetingMainTypeId().longValue() > 0L){
			queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetingMainTypeId " );
    	}  
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeIdList) " );
    	}
    	if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
    		queryStr.append(" and date(model.partyMeeting.startDate) between :startDate and :endDate " );
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
    	if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0L){
    		queryStr.append(" and model2.partyMeetingGroup.partyMeetingGroupId = :partyMeetingGroupId ");
    	}
    	if(locLevelIdList != null && locLevelIdList.size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingLevelId in (:locLevelIdList) ");
    	}
    	if(inputVO.getStateId().longValue() == 1L){
    		queryStr.append(" and model.attendance.tdpCadre.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");	
    	}else if(inputVO.getStateId().longValue() == 36L){
    		queryStr.append(" and model.attendance.tdpCadre.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
    	}    
		queryStr.append(" group by model.partyMeeting.partyMeetingId,model.partyMeetingSession.sessionType.sessionTypeId,model.attendance.tdpCadre.tdpCadreId ");
		
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
    	if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0L){
    		query.setParameter("partyMeetingGroupId", inputVO.getPartyMeetingGroupId());
    	} 
    	if(locLevelIdList != null && locLevelIdList.size() > 0){
    		query.setParameterList("locLevelIdList",locLevelIdList);
    	}
    	return query.list();
	}
	
	public Long getConductedCount(Long partyMeetnMainTypId,Long userAccessLevelId,Set<Long> locationValuesSet,
			Date fromDate,Date toDate,Long stateId,Long partyMeetingLevelId,Long partyMeetngGrpId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct model.partyMeeting.partyMeetingId)" +
				" from PartyMeetingAttendance model,PartyMeetingGroupsMappingInfo model1 where model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetnMainTypId " +
				" and  model.partyMeeting.partyMeetingType.isActive = 'Y' and model.partyMeeting.isActive='Y' and " +
				" model1.partyMeeting.partyMeetingId = model.partyMeeting.partyMeetingId  ");
		
		if(partyMeetngGrpId != null && partyMeetngGrpId.longValue() > 0l){
			queryStr.append(" and model1.partyMeetingGroup.partyMeetingGroupId = :partyMeetngGrpId ");
		}
		
		if(partyMeetingLevelId != null && partyMeetingLevelId.longValue() > 0l){
			queryStr.append(" and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId = :partyMeetingLevelId ");
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
		
		//queryStr.append(" group by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model1.partyMeetingGroup.partyMeetingGroupId ");
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
		 if(partyMeetingLevelId != null && partyMeetingLevelId.longValue() > 0l){
			 query.setParameter("partyMeetingLevelId", partyMeetingLevelId); 
		 }
		query.setParameter("partyMeetnMainTypId", partyMeetnMainTypId);
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getAttendeeDetails(Long partyMeetnMainTypId,Long userAccessLevelId,Set<Long> locationValuesSet,
			Date fromDate,Date toDate,Long stateId,Long partyMeetingLevelId,Long partyMeetngGrpId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select  model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.partyMeetingId," +
				"model.attendance.tdpCadre.tdpCadreId,model.attendance.attendedTime,model.partyMeetingSession.sessionTypeId , " +
				" concat(model.attendance.tdpCadre.tdpCadreId,'-',model.partyMeetingSession.sessionTypeId),model.partyMeetingSession.sessionType.type " +
				", model.partyMeeting.partyMeetingType.partyMeetingTypeId from PartyMeetingAttendance model,PartyMeetingGroupsMappingInfo model1 where model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetnMainTypId " +
				" and  model.partyMeeting.partyMeetingType.isActive = 'Y' and model.partyMeeting.isActive='Y' and " +
				" model1.partyMeeting.partyMeetingId = model.partyMeeting.partyMeetingId  ");
		
		if(partyMeetngGrpId != null && partyMeetngGrpId.longValue() > 0l){
			queryStr.append(" and model1.partyMeetingGroup.partyMeetingGroupId = :partyMeetngGrpId ");
		}
		
		if(partyMeetingLevelId != null && partyMeetingLevelId.longValue() > 0l){
			queryStr.append(" and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId = :partyMeetingLevelId ");
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
		
		queryStr.append(" group by concat(model.attendance.tdpCadre.tdpCadreId,'-',model.partyMeetingSession.sessionTypeId) ");
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
		 if(partyMeetingLevelId != null && partyMeetingLevelId.longValue() > 0l){
			 query.setParameter("partyMeetingLevelId", partyMeetingLevelId); 
		 }
		query.setParameter("partyMeetnMainTypId", partyMeetnMainTypId);
		return query.list();
	}
	
	public List<Object[]> getPartyLevelIdWiseMeetingAttendanceDetails(Long partyMeetnMainTypId,Long  userAccessLevelId,Set<Long> userAccessLevelValues, 
			 Date fromDateStr,Date toDateStr, Long stateId, List<Long> levelIdsList,Long  partyMeetngGrpId,Long sessionTypId,Long partyMeetingId,Long locationValId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" SELECT ");
		queryStr.append(" st.session_type_id as session_type_id, " +
				" st.type as type,a.tdp_cadre_id as tdp_cadre_id, " +
				" a.attended_time as attended_time, " +
				" DATE_FORMAT(a.attended_time,'%H:%i:%s') as time ," +
				" tc.first_name as first_name ," +
				" tc.membership_id as membership_id, " +
				" tc.mobile_no as mobile_no,tc.image as image,d.district_name as districtName ");
		queryStr.append(" FROM  ");
		queryStr.append(" party_meeting pm, ");
		queryStr.append(" party_meeting_session pms, ");
		queryStr.append(" session_type st, ");
		if(partyMeetngGrpId != null && partyMeetngGrpId.longValue() > 0l)
			queryStr.append(" party_meeting_groups_mapping_info pmg, ");
		queryStr.append(" party_meeting_attendance pma,  ");
		queryStr.append(" attendance a, ");
		queryStr.append(" tdp_cadre tc , ");
		queryStr.append(" party_meeting_type pmt,  ");
		queryStr.append(" party_meeting_main_type pmmt, user_address ua ");
		queryStr.append(" left join district d on ua.district_id = d.district_id ");
		queryStr.append(" where ");
		queryStr.append(" pm.meeting_address_id = ua.user_address_id and pm.is_active='Y' and  ");
		if(partyMeetngGrpId != null && partyMeetngGrpId.longValue() > 0l){
			queryStr.append(" pmg.party_meeting_group_id = :party_meeting_group_id and  ");
			queryStr.append(" pmg.party_meeting_id = pm.party_meeting_id and  pmg.is_deleted='N' and  ");
		}
		queryStr.append(" pma.party_meeting_id = pm.party_meeting_id and  ");
		queryStr.append(" pma.attendance_id = a.attendance_id and  ");
		queryStr.append(" a.tdp_cadre_id = tc.tdp_cadre_id and  ");
		queryStr.append(" tc.enrollment_year = '2014' and  ");
		queryStr.append(" tc.is_deleted='N' and  ");
		queryStr.append(" pm.party_meeting_type_id = pmt.party_meeting_type_id and  ");
		queryStr.append(" pmt.party_meeting_main_type_id= pmmt.party_meeting_main_type_id AND ");
		queryStr.append(" pms.party_meeting_id=pm.party_meeting_id and  ");
		queryStr.append(" pms.session_type_id = st.session_type_id and  ");
		queryStr.append("  pma.party_meeting_session_id = pms.party_meeting_session_id and ");
		if(partyMeetingId != null && partyMeetingId.longValue() > 0l)
			queryStr.append(" pm.party_meeting_id = :partyMeetingId and ");
		
		if(levelIdsList != null && levelIdsList.size()>0L)
			queryStr.append(" pm.party_meeting_level_id  in (:levelIdsList) and ");
		
		if(locationValId != null && locationValId.longValue() > 0l){
			queryStr.append("  ua.district_id = :locationValId  and "); 
		}
		if(partyMeetnMainTypId != null && partyMeetnMainTypId.longValue()>0L)
			queryStr.append(" pmmt.party_meeting_main_type_id =:party_meeting_main_type_id and ");
		
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			   queryStr.append("  ua.state_id in (:userAccessLevelValues) and ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			   queryStr.append("  ua.district_id in (:userAccessLevelValues) and ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		        queryStr.append("  ua.parliament_constituency_id in (:userAccessLevelValues) and  ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		        queryStr.append("  ua.constituency_id in (:userAccessLevelValues)  and ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			    queryStr.append("  ua.tehsil_id in (:userAccessLevelValues) and ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			    queryStr.append("  ua.local_election_body in (:userAccessLevelValues) and "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			    queryStr.append("  ua.panchayat_id in (:userAccessLevelValues) and "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			    queryStr.append("  ua.ward_id in (:userAccessLevelValues) and "); 
			 }
		//if(sessionTypId != null && sessionTypId.longValue()>0L)
		//	queryStr.append(" st.session_type_id =:sessionTypId and  ");
		
		queryStr.append(" pms.is_deleted='N'  group by a.tdp_cadre_id,st.session_type_id   ");
		queryStr.append(" ORDER BY a.tdp_cadre_id  ");
		
		Query query =getSession().createSQLQuery(queryStr.toString())
				.addScalar("session_type_id",Hibernate.LONG)
				.addScalar("type",Hibernate.STRING)
				.addScalar("tdp_cadre_id",Hibernate.LONG)
				.addScalar("attended_time",Hibernate.STRING)
				.addScalar("time",Hibernate.STRING)
				.addScalar("first_name",Hibernate.STRING)
				.addScalar("membership_id",Hibernate.STRING)
				.addScalar("mobile_no",Hibernate.STRING)
				.addScalar("image",Hibernate.STRING)
				.addScalar("districtName",Hibernate.STRING);
		
		
		query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		query.setParameter("party_meeting_main_type_id", partyMeetnMainTypId);
		if(partyMeetngGrpId != null && partyMeetngGrpId.longValue() > 0l)
			query.setParameter("party_meeting_group_id", partyMeetngGrpId);
		query.setParameterList("levelIdsList", levelIdsList);
		if(partyMeetingId != null && partyMeetingId.longValue() > 0l)
			query.setParameter("partyMeetingId", partyMeetingId);
		if(locationValId != null && locationValId.longValue() > 0l){
			 query.setParameter("locationValId", locationValId);
		 }
		//if(sessionTypId != null && sessionTypId.longValue()>0L)
		//	query.setParameter("sessionTypId", sessionTypId);
		
		return query.list();
	}
	
	public List<Object[]> getMeetingsBasedConductedCountForDiffLevels(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet,List<Long> locLevelIdList){  
		StringBuilder queryStr = new StringBuilder();   
		queryStr.append(" select " +
				" count(distinct model.partyMeeting.partyMeetingId), " +//0
				" model.partyMeeting.meetingAddress.district.districtId, " +//7
				" model.partyMeeting.partyMeetingLevelId,model.partyMeetingSession.sessionType.sessionTypeId " +//8
				" from " +
				" PartyMeetingAttendance model, PartyMeetingGroupsMappingInfo model2 " +
				" where " +
				"  model.partyMeeting.isActive = 'Y' " +
				" and model.partyMeetingSession.isDeleted = 'N' " +
				" and model.partyMeeting.partyMeetingId = model2.partyMeeting.partyMeetingId ");
		
		if(inputVO.getSessionId() != null && inputVO.getSessionId().longValue() > 0L){
			queryStr.append(" and model.partyMeetingSession.sessionType.sessionTypeId = :sessionTypeId " );
    	}
		if(inputVO.getPartyMeetingMainTypeId() != null && inputVO.getPartyMeetingMainTypeId().longValue() > 0L){
			queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetingMainTypeId " );
    	}  
    	if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeIdList) " );
    	}
    	if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
    		queryStr.append(" and date(model.partyMeeting.startDate) between :startDate and :endDate " );
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
    	if(inputVO.getPartyMeetingGroupId() != null && inputVO.getPartyMeetingGroupId().longValue() > 0L){
    		queryStr.append(" and model2.partyMeetingGroup.partyMeetingGroupId = :partyMeetingGroupId ");
    	}
    	if(locLevelIdList != null && locLevelIdList.size() > 0){
    		queryStr.append(" and model.partyMeeting.partyMeetingLevelId in (:locLevelIdList) ");
    	}
    	if(inputVO.getStateId().longValue() == 1L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");	
    	}else if(inputVO.getStateId().longValue() == 36L){
    		queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
    	}    
		queryStr.append(" group by model.partyMeeting.meetingAddress.district.districtId,model.partyMeeting.partyMeetingLevelId ");
		
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
    	if(locLevelIdList != null && locLevelIdList.size() > 0){
    		query.setParameterList("locLevelIdList",locLevelIdList);
    	}
    	if(inputVO.getSessionId() != null && inputVO.getSessionId().longValue() > 0L){
    		query.setParameter("sessionTypeId", inputVO.getSessionId());
    	}
    	return query.list();
	}
	/*public List<String> getPartyMeetingInviteesDetailsAttendence(Long partyMeetingId){
    	
    	Query query = getSession().createQuery(" select distinct model.attendance.tdpCadre.memberShipNo " +
    						" from PartyMeetingAttendance model " +
    						" where model.partyMeeting.partyMeetingId = :partyMeetingId  and  model.attendance.tdpCadre.isDeleted='N' and model.partyMeeting.isActive='Y' " );
    	query.setParameter("partyMeetingId", partyMeetingId);
    	
    	return query.list();
    }
	public List<String> getPartyMeetingInviteesDetailsAttendence(Long partyMeetingId){
    	
    	Query query = getSession().createQuery(" select distinct model.attendance.tdpCadre.memberShipNo " +
    						" from PartyMeetingAttendance model " +
    						" where model.partyMeeting.partyMeetingId = :partyMeetingId  and  model.attendance.tdpCadre.isDeleted='N' and model.partyMeeting.isActive='Y' " );
    	query.setParameter("partyMeetingId", partyMeetingId);
    	
    	return query.list();
    }
	*/
	
	
	public List<String> getPartyMeetingInviteesDetailsAttendence(Long partyMeetingId,Long sessionId){
	      StringBuilder sb=new StringBuilder();
	      sb.append("select distinct model.attendance.tdpCadre.memberShipNo ");
	      sb.append(" from PartyMeetingAttendance model ");
	      sb.append(" where model.attendance.tdpCadre.isDeleted='N' and model.partyMeeting.isActive='Y' ");
	      sb.append("and model.partyMeeting.partyMeetingId = :partyMeetingId  ");
	      if(sessionId !=null && sessionId.longValue() >0){
	        sb.append("and model.partyMeetingSessionId = :sessionId ");
	      }
	      Query query = getSession().createQuery(sb.toString());
	      query.setParameter("partyMeetingId", partyMeetingId);
	      if(sessionId !=null && sessionId.longValue() >0){
	        query.setParameter("sessionId", sessionId);
	      }
	      
	      return query.list();
	}
	public List<Object[]> getAttendedList(Long locationLevel,List<Long> locationIds,Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct " +
						" model.partyMeeting.partyMeetingType.partyMeetingTypeId, " +
						" model.partyMeeting.partyMeetingType.type, " +
						" model.partyMeeting.partyMeetingId, " +
						" model.attendance.tdpCadre.tdpCadreId " +
						" from " +
						" PartyMeetingAttendance model " +
						" where " +
						" model.partyMeeting.isActive = 'Y' " +
						" and model.partyMeeting.partyMeetingType.isActive = 'Y' " +
						" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId ="+IConstants.SPECIAL_MEETINGS+" " +
						" and model.attendance.tdpCadre.isDeleted = 'N' ");
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
	public List<Object[]> getAttendedListForMeeting(Long locationLevel,List<Long> locationIds,Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct " +
						" model.partyMeeting.partyMeetingId, " +
						" model.attendance.tdpCadre.tdpCadreId " +
						" from " +
						" PartyMeetingAttendance model " +
						" where " +
						" model.partyMeeting.isActive = 'Y' " +
						" and model.partyMeeting.partyMeetingType.isActive = 'Y' " +
						" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId ="+IConstants.SPECIAL_MEETINGS+" " +
						" and model.attendance.tdpCadre.isDeleted = 'N' ");
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
	
	public List<Object[]> getLocationWiseStateMeetingAttendees(List<Long> locationValues,Long locationTypeId,Date fromDate,Date toDate,Long partyMeetingMainTypeid,Long partyMeetingTypeId,Long partyMeetingId
			,Set<Long> inviteeIds,Long sessionTypeId){
	       
	       //0-meetingStatus,1-levelId,2-level,3-count
	       StringBuilder sb = new StringBuilder();
	       
	       sb.append(" select model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId,model.partyMeeting.partyMeetingType.partyMeetingMainType.meetingType," +
	          "model.partyMeeting.partyMeetingType.partyMeetingTypeId,model.partyMeeting.partyMeetingType.type,model.attendance.tdpCadre.tdpCadreId" +
	          ",model.partyMeeting.partyMeetingId,sessionType.sessionTypeId,sessionType.type,model.attendance.attendedTime,partyMeetingSession.lateTime,model.partyMeeting.meetingName," +
	          "model.partyMeeting.startDate  from PartyMeetingAttendance model left join  model.partyMeetingSession partyMeetingSession  " +
	          "left join partyMeetingSession.sessionType sessionType where model.partyMeeting.isActive = 'Y' and partyMeetingSession.isDeleted = 'N' and ");
	       
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
	       
	       if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() >0l){
	    	   sb.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId = :partyMeetingTypeId ");
	       }
	       if(sessionTypeId != null && sessionTypeId.longValue() > 0l){
	    	   sb.append(" and sessionType.sessionTypeId = :sessionTypeId ");
	       }
	       if(partyMeetingMainTypeid != null && partyMeetingMainTypeid.longValue() > 0l){
	    	   sb.append(" and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetingMainTypeid ");
	       }
	       if(inviteeIds != null && inviteeIds.size() >0){
	    	   sb.append(" and model.attendance.tdpCadre.tdpCadreId in (:inviteeIds) ");
	       }
	       if(fromDate != null && toDate != null){
	    	   sb.append(" and date(model.partyMeeting.startDate) between :fromDate and :toDate ");
	       }
	       if(partyMeetingId != null && partyMeetingId.longValue() >0){
	    	   sb.append(" and model.partyMeeting.partyMeetingId = :partyMeetingId " );
	       }
	       sb.append(" order by model.partyMeeting.startDate desc ");
	       
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
	       if(partyMeetingId != null && partyMeetingId.longValue() >0){
	    	   query.setParameter("partyMeetingId", partyMeetingId);
	       }
	       if(inviteeIds != null && inviteeIds.size() >0){
	    	   query.setParameterList("inviteeIds", inviteeIds); 
	       }
	       if(sessionTypeId != null && sessionTypeId.longValue() > 0l){
	    	   query.setParameter("sessionTypeId", sessionTypeId);
	       }
	       return query.list();
	     }
	
	public List<Object[]> getNonInviteeAttendanceCountByMeetingId(List<Long> partyMeetingIds){
		//0 partyMeetingId,1 sessionId,2 sessionMainId,3 sessionName,4 count
		StringBuilder sb = new StringBuilder();
		
		sb.append("select pma.party_meeting_id as partyMeetingId,pma.party_meeting_session_id as sessionId,st.session_type_id as sessionMainId,");
		sb.append("st.type as sessionName,count(pma.attendance_id) as count from party_meeting_attendance pma,");
		sb.append("attendance a,party_meeting_session pms, session_type st  ");
		sb.append("where pma.attendance_id = a.attendance_id  ");
		sb.append("and	pma.party_meeting_session_id = pms.party_meeting_session_id ");
		sb.append("and pms.session_type_id =st.session_type_id ");
		sb.append("and pma.party_meeting_id in (:partyMeetingIds)  group by pms.party_meeting_session_id ");
		SQLQuery query = getSession().createSQLQuery(sb.toString())
				.addScalar("partyMeetingId",Hibernate.LONG)
				.addScalar("sessionId",Hibernate.LONG)
				.addScalar("sessionMainId",Hibernate.LONG)
				.addScalar("sessionName",Hibernate.STRING)
				.addScalar("count",Hibernate.LONG);
		
		if(partyMeetingIds != null && partyMeetingIds.size() > 0l){
	    	   query.setParameterList("partyMeetingIds", partyMeetingIds);
	       }
		 return query.list();
	}
}
