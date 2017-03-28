package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampScheduleInviteeDAO;
import com.itgrids.partyanalyst.model.TrainingCampScheduleInvitee;
import com.itgrids.partyanalyst.utils.IConstants;

public class TrainingCampScheduleInviteeDAO extends GenericDaoHibernate<TrainingCampScheduleInvitee, Long> implements ITrainingCampScheduleInviteeDAO{

	public TrainingCampScheduleInviteeDAO() {
		super(TrainingCampScheduleInvitee.class);
	}

	public List<Object[]> getCampusWiseScheduleWiseMembersDetails(String searchType, Date startDate, Date endDate,Date todayDate)
	{
		StringBuilder queryStr = new StringBuilder();
		boolean isDatesNull = false;
		queryStr.append("select TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.campName," +
				"TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.programName," +
				"TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleCode ");
		queryStr.append(" ,TCSIC.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId," +
				"TCSIC.trainingCampScheduleInvitee.scheduleInviteeStatus.status ," +
				"count(distinct TCSIC.trainingCampScheduleInvitee.tdpCadreId)," +
				"TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId ");
		
		queryStr.append(" from TrainingCampScheduleInviteeCaller TCSIC,TrainingCampBatch TCB where  TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId = TCB.trainingCampSchedule.trainingCamp.trainingCampId and " +
				" TCSIC.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId is not null and TCB.isCancelled = 'false' "); //not in (1) " ); // except invitee status
		
		if(startDate != null && endDate != null)
		{
			//queryStr.append(" and (date(TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.fromDate) >=:startDate and date(TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.toDate) <=:endDate) ");
			
			if(searchType !=null && searchType.equalsIgnoreCase("notStarted")){
				queryStr.append(" and date(TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.fromDate) >:endDate ");
			}
			else if(searchType !=null && searchType.equalsIgnoreCase("running")){
				queryStr.append(" and  (:startDate BETWEEN date(TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.fromDate)  AND date(TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.toDate) OR " +
						" :endDate  BETWEEN  date(TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.fromDate)  AND date(TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.toDate)) ");
			}
			else if(searchType !=null && searchType.equalsIgnoreCase("completed")){
				queryStr.append(" and date(TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.toDate) < :startDate  ");
			}
			else if(searchType !=null && searchType.equalsIgnoreCase("cancelled")){
				queryStr.append(" and TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.status ='Cancelled' ");
			}
		}
		else
		{
			isDatesNull = true;
			if(searchType !=null && searchType.equalsIgnoreCase("notStarted")){
				queryStr.append(" and date(TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.fromDate) >:todayDate ");
			}
			else if(searchType !=null && searchType.equalsIgnoreCase("running")){
				queryStr.append(" and :todayDate BETWEEN date(TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.fromDate) AND  date(TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.toDate) ");
			}
			else if(searchType !=null && searchType.equalsIgnoreCase("completed")){
				queryStr.append(" and date(TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.toDate) < :todayDate  ");
			}
			else if(searchType !=null && searchType.equalsIgnoreCase("cancelled")){
				queryStr.append(" and TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.status ='Cancelled' ");
			}
		}
		
		queryStr.append("  group by TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId,TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId," +
				" TCSIC.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId order by " +
				" TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(isDatesNull){
			if(!searchType.equalsIgnoreCase("cancelled"))
				query.setDate("todayDate", todayDate);
		}
		else
		{
			if(searchType !=null && searchType.equalsIgnoreCase("notStarted")){
				query.setDate("endDate", endDate);
			}
			else if(searchType !=null && searchType.equalsIgnoreCase("running")){
				query.setDate("startDate", startDate);
				query.setDate("endDate", endDate);
			}
			else if(searchType !=null && searchType.equalsIgnoreCase("completed")){
				query.setDate("startDate", startDate);
			}
		}
		
		return query.list();
	}
	
	public List<Object[]> getCampusWiseBatchWiseMembersDetails(String searchType, Date startDate, Date endDate)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select  TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.campName," +
				" TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.programName," +
				"TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleCode," +
				" TCB.trainingCampBatchName,date(TCB.fromDate),date(TCB.toDate),TCB.batchStatus.status," +
				" count(TCSIC.trainingCampScheduleInvitee.trainingCampScheduleInviteeId)," +
				" TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId ");
		queryStr.append(" from TrainingCampScheduleInviteeCaller TCSIC,TrainingCampBatch TCB where TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId = TCB.trainingCampSchedule.trainingCamp.trainingCampId  ");
		queryStr.append(" and TCSIC.campCallPurpose.purpose='Confirmation' and TCSIC.trainingCampScheduleInvitee.scheduleInviteeStatus.status ='Interested' and TCB.isCancelled = 'false' ");
		if(startDate != null && endDate != null)
		{
			queryStr.append(" and (date(TCB.fromDate) >=:startDate and date(TCB.fromDate) <=:endDate) ");
		}
		if(searchType != null && searchType.equalsIgnoreCase("notStarted"))
		{
			queryStr.append(" and TCB.batchStatus.status ='Not Started' ");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("running"))
		{
			queryStr.append(" and TCB.batchStatus.status ='Progress' ");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("completed"))
		{
			queryStr.append(" and TCB.batchStatus.status ='Completed' ");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("cancelled"))
		{
			queryStr.append(" and  ( TCB.batchStatus.status ='Cancelled' or TCB.batchStatus.status ='Postponed' ) ");
		}
		
		queryStr.append("  group by TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId,TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId" +
				" ,TCB.batchStatus.status order by TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(startDate != null && endDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	
	public List<Long> getInvitedCandidatesListByScheduleIdAndCount(List<Long> callerIdsList , Long scheduleId,List<Long> alreadyInvitedMemberIdsList,int membersCount,Long batchId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct TCSIC.trainingCampScheduleInvitee.trainingCampScheduleInviteeId from TrainingCampScheduleInviteeCaller TCSIC where TCSIC.trainingCampScheduleInvitee.scheduleInviteeStatusId = 4 " +
				" and TCSIC.callPurposeId = 1 and TCSIC.trainingCampScheduleInvitee.trainingcampScheduleId =:scheduleId ");
		if(alreadyInvitedMemberIdsList != null && alreadyInvitedMemberIdsList.size()>0)
			queryStr.append(" and TCSIC.trainingCampScheduleInvitee.trainingCampScheduleInviteeId not in (:alreadyInvitedMemberIdsList) ");
		if(callerIdsList != null && callerIdsList.size()>0)
			queryStr.append(" and TCSIC.trainingCampCallerId in (:callerIdsList) ");
		if(batchId != null && batchId.longValue()>0L)
			queryStr.append(" and TCSIC.trainingCampScheduleInvitee.attendingBatchId =:batchId ");
		
		queryStr.append("order by TCSIC.trainingCampScheduleInvitee.trainingCampScheduleInviteeId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("scheduleId", scheduleId);
		if(alreadyInvitedMemberIdsList != null && alreadyInvitedMemberIdsList.size()>0)
			query.setParameterList("alreadyInvitedMemberIdsList", alreadyInvitedMemberIdsList);
		if(callerIdsList != null && callerIdsList.size()>0)
			query.setParameterList("callerIdsList", callerIdsList);
		if(batchId != null && batchId.longValue()>0L)
			query.setParameter("batchId", batchId);
		
		return query.list();
	}
	
	public List<Object[]> getTrainingProgramMembersBatchCount(Date startDate,Date endDate,String status,String type){
		
		StringBuilder str =new StringBuilder();
		
		if(type.equalsIgnoreCase("program")){
			str.append(" select  model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,model.trainingCampSchedule.trainingCampProgram.programName ");
		}else if(type.equalsIgnoreCase("camp")){
			str.append(" select  model.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampSchedule.trainingCamp.campName ");
		}
		
		str.append(" ,count(distinct model.trainingCampScheduleInviteeId),count(distinct model1.trainingCampBatchId) " +
				"  from TrainingCampScheduleInvitee model,TrainingCampBatch model1" +
				" where model.trainingCampSchedule.trainingCampScheduleId = model1.trainingCampSchedule.trainingCampScheduleId " +
				" and model.scheduleInviteeStatus.status like '%"+status+"%' and model1.isCancelled = 'false' ");	
		
		if(startDate !=null && endDate !=null){
			str.append(" and (date(model.trainingCampSchedule.fromDate)>=:startDate and date(model.trainingCampSchedule.toDate)<=:endDate)  ");
		}
		
		if(type.equalsIgnoreCase("program")){
			str.append(" group by model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId ");
		}else if(type.equalsIgnoreCase("camp")){
			str.append(" group by model.trainingCampSchedule.trainingCamp.trainingCampId ");
		}
	
		Query query = getSession().createQuery(str.toString());
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		
		return query.list();
	}
	
	public List getTrainingCampScheduleInviteeId(Long tdpCadreId,Long programId,Long campId,Long scheduleId)
	{
		Query query = getSession().createQuery("select model.trainingCampScheduleInviteeId from " +
				" TrainingCampScheduleInvitee model where model.tdpCadre.tdpCadreId = :tdpCadreId" +
				" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId =:programId" +
				" and model.trainingCampSchedule.trainingCamp.trainingCampId =:campId" +
				"and model.trainingCampSchedule.trainingcampScheduleId =:scheduleId ");
		query.setParameter("tdpCadreId", tdpCadreId);
		query.setParameter("programId", programId);
		query.setParameter("campId", campId);
		query.setParameter("scheduleId", scheduleId);
		return query.list();
	}

	public Long getAvailableInvitedCandidatesListByScheduleIdAndCount(Long scheduleId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(TCSI.trainingCampScheduleInviteeId) from TrainingCampScheduleInvitee TCSI where TCSI.scheduleInviteeStatusId =1 and TCSI.trainingcampScheduleId =:scheduleId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("scheduleId", scheduleId);
		return (Long) query.uniqueResult();
	}
	
	public Long getAreadyAssignedCandidatesListByScheduleIdAndCount(Long scheduleId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(TCSIC.trainingCampScheduleInvitee.trainingCampScheduleInviteeId) from TrainingCampScheduleInviteeCaller TCSIC where TCSIC.trainingCampScheduleInvitee.trainingcampScheduleId =:scheduleId and " +
				" TCSIC.campCallPurpose.purpose='Invitation' ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("scheduleId", scheduleId);
		return (Long) query.uniqueResult();
	}
	public List<Long> getUpcomingBatchConfirmation(Date fromDate,Date toDate,String type,Date todayDate){
		
		StringBuilder queryStr = new StringBuilder();
		boolean isDateNull = false;
		queryStr.append(" select distinct model.attendingBatchId  from TrainingCampScheduleInvitee model " +
				" where  ");
		if(fromDate !=null && toDate !=null){
			queryStr.append("  (date(model.trainingCampSchedule.fromDate)>=:fromDate and date(model.trainingCampSchedule.toDate)<=:todate) ");
			queryStr.append(" or date(model.trainingCampSchedule.fromDate) > :todate ");
		}
		else
		{
			isDateNull = true;
			queryStr.append(" date(model.trainingCampSchedule.fromDate)>:todayDate ");
		}
		
		if(type !=null){
			queryStr.append(" and model.scheduleInviteeStatus.status = '"+type+"' ");
		}
		
		queryStr.append(" and model.attendingBatchId is not null");
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate !=null && toDate !=null){
		query.setParameter("fromDate", fromDate);
		query.setParameter("todate",toDate);
		}
		if(isDateNull)
		{
			query.setParameter("todayDate", todayDate);
		}
		return query.list();
	}
	
	
	
	
	public List<Object[]> getBatchWiseConformationDetails(String searchType, Date startDate, Date endDate,Date todayDate)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.programName, ");
		queryStr.append(" TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId, TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.campName, TCB.trainingCampBatchId, date(TCB.fromDate), ");
		queryStr.append(" date(TCB.toDate), count(distinct TCSIC.trainingCampScheduleInvitee.trainingCampScheduleInviteeId), TCSIC.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId, TCSIC.trainingCampScheduleInvitee.scheduleInviteeStatus.status ");
		
		queryStr.append(" from TrainingCampScheduleInviteeCaller TCSIC, TrainingCampBatch TCB  where TCSIC.trainingCampScheduleInvitee.attendingBatchId = TCB.trainingCampBatchId and TCSIC.campCallPurpose.purpose = 'Confirmation' " +
				" and TCSIC.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId is not null and TCB.isCancelled = 'false' "); //not in (1) " );
		  
		if(startDate != null && endDate != null){
			queryStr.append(" and (date(TCSIC.updatedTime) >=:startDate and date(TCSIC.updatedTime) <=:endDate) ");
			//queryStr.append(" or date(TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.fromDate) >:endDate ");
		}
		//else
		//	queryStr.append(" and date(TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.fromDate) >=:todayDate ");
		
		queryStr.append(" group by TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId,TCSIC.trainingCampScheduleInvitee.attendingBatchId , TCSIC.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(startDate != null && endDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		//else
			//query.setDate("todayDate", todayDate);
		
		return query.list();
	}
	public List<Long> getScheduleWiseInviteesListByLocationIdLocationType(Long scheduleId,Long locationTypeId,List<Long> locationIdsList)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.trainingCampScheduleInviteeId from TrainingCampScheduleInvitee model where model.scheduleInviteeStatusId = 1 and model.attendingBatchId is null ");
		if(scheduleId != null && scheduleId.longValue()>0L)
			queryStr.append(" and model.trainingcampScheduleId = :scheduleId ");
		if(locationIdsList != null && locationIdsList.size()>0)
		{
			if(locationTypeId != null && locationTypeId.longValue() == IConstants.DISTRICT_SCOPE_ID)
				queryStr.append(" and model.tdpCadre.userAddress.district.districtId  in (:locationIdsList)  ");
			else if(locationTypeId != null && locationTypeId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_SCOPE_ID)
				queryStr.append(" and model.tdpCadre.userAddress.parliamentConstituency.constituencyId  in  (:locationIdsList)  ");
			else if(locationTypeId != null && locationTypeId.longValue() == IConstants.CONSTITUENCY_SCOPE_ID)
				queryStr.append(" and model.tdpCadre.userAddress.constituency.constituencyId  in  (:locationIdsList)  ");
			else if(locationTypeId != null && locationTypeId.longValue() == IConstants.TEHSIL_SCOPE_ID)
				queryStr.append(" and model.tdpCadre.userAddress.tehsil.tehsilId  in  (:locationIdsList) ");
			else if(locationTypeId !=null && locationTypeId.longValue() == IConstants.MUNICIPAL_CORP_GMC_SCOPE_ID)
				queryStr.append(" and model.tdpCadre.userAddress.localElectionBody.localElectionBodyId  in  (:locationIdsList) ");
		}
		Query query = getSession().createQuery(queryStr.toString());
		if(scheduleId != null && scheduleId.longValue()>0L)
			query.setParameter("scheduleId", scheduleId);
		if(locationIdsList != null && locationIdsList.size()>0)
			query.setParameterList("locationIdsList", locationIdsList);
		return query.list();
	}
	public List<Object[]> getScheduleAvailableCallsCountLocationWiseInfo(Long campId,Long programId,Long scheduleId,Long scheduleStatusId,List<Long> inviteeIdsList)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct TCSI.tdp_cadre_id) as count,UA.district_id as distId,Dist.district_name as distName,UA.constituency_id as constId" +
				",const.name as constName,UA.tehsil_id as tehsilId" +
				",tehsil.tehsil_name as tehsilName,UA.local_election_body as localElectionBodyId " +
				" from training_camp_schedule_invitee TCSI" +
				",tdp_cadre TC ,training_camp_schedule TCS" +
				",district Dist,constituency const,training_camp_district TCD ,user_address UA" +
				" left outer join tehsil tehsil on UA.tehsil_id = tehsil.tehsil_id" +
				" left outer join local_election_body LEB on UA.local_election_body = LEB.local_election_body_id " +
				" where " +
				" TCS.training_camp_id = TCD.training_camp_id and  TCD.district_id = UA.district_id and  TC.tdp_cadre_id = TCSI.tdp_cadre_id and " +
				" UA.user_address_id = TC.address_id and " +
				" UA.district_id = Dist.district_id and " +
				" UA.constituency_id = const.constituency_id and " +
				
				" TCS.training_camp_schedule_id = TCSI.training_camp_schedule_id and " +
				" TCSI.schedule_invitee_status_id =:scheduleStatusId " +
				" and TCS.training_camp_id = :campId  " );
		queryStr.append(" and TCSI.training_camp_schedule_invitee_id not in ( select distinct training_camp_schedule_invitee_id from training_camp_schedule_invitee_caller TCSIC ) ");
		queryStr.append(" and TCSI.training_camp_schedule_id =:scheduleId and TCSI.attending_batch_id is null group by UA.district_id,UA.constituency_id,UA.tehsil_id,UA.local_election_body");
		
		Query query = getSession().createSQLQuery(queryStr.toString()).addScalar("count", Hibernate.LONG)
				.addScalar("distId",Hibernate.LONG)
				.addScalar("distName",Hibernate.STRING)
				.addScalar("constId",Hibernate.LONG)
				.addScalar("constName",Hibernate.STRING)
				.addScalar("tehsilId",Hibernate.LONG)
				.addScalar("tehsilName",Hibernate.STRING)
				.addScalar("localElectionBodyId",Hibernate.LONG);;
		
		query.setParameter("scheduleStatusId", scheduleStatusId);
		
		query.setParameter("campId", campId);
		
		//query.setParameter("programId", programId);
		
		query.setParameter("scheduleId", scheduleId);
		
		
		return query.list();
		
	}
	
	
	public List<Object[]> getScheduleAvailableCallsCountParliamentWiseInfo(Long campId,Long programId,Long scheduleId,Long scheduleStatusId,List<Long> inviteeIdsList)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct TCSI.tdp_cadre_id) as count,UA.parliament_constituency_id as parlId,parlConst.name as parlName,UA.constituency_id as constId" +
				",const.name as constName,UA.tehsil_id as tehsilId" +
				",tehsil.tehsil_name as tehsilName,UA.local_election_body as localElectionBodyId " +
				" from training_camp_schedule_invitee TCSI" +
				",tdp_cadre TC ,training_camp_schedule TCS" +
				",district Dist,constituency const,training_camp_district TCD ,constituency parlConst,user_address UA " +
				" left outer join tehsil tehsil on UA.tehsil_id = tehsil.tehsil_id" +
				" left outer join local_election_body LEB on UA.local_election_body = LEB.local_election_body_id " +
				" where " +
				" TCS.training_camp_id = TCD.training_camp_id and  TCD.district_id = UA.district_id and  TC.tdp_cadre_id = TCSI.tdp_cadre_id and " +
				" UA.user_address_id = TC.address_id and " +
				" UA.district_id = Dist.district_id and " +
				" UA.constituency_id = const.constituency_id and " +
				" UA.parliament_constituency_id = parlConst.constituency_id and " +
				" TCS.training_camp_schedule_id = TCSI.training_camp_schedule_id and " +
				" TCSI.schedule_invitee_status_id =:scheduleStatusId " +
				" and TCS.training_camp_id = :campId  " );
		queryStr.append(" and TCSI.training_camp_schedule_invitee_id not in ( select distinct training_camp_schedule_invitee_id from training_camp_schedule_invitee_caller TCSIC ) ");
		queryStr.append(" and TCSI.training_camp_schedule_id =:scheduleId and TCSI.attending_batch_id is null group by UA.parliament_constituency_id,UA.constituency_id,UA.tehsil_id,UA.local_election_body");
		
		Query query = getSession().createSQLQuery(queryStr.toString()).addScalar("count", Hibernate.LONG)
				.addScalar("parlId",Hibernate.LONG)
				.addScalar("parlName",Hibernate.STRING)
				.addScalar("constId",Hibernate.LONG)
				.addScalar("constName",Hibernate.STRING)
				.addScalar("tehsilId",Hibernate.LONG)
				.addScalar("tehsilName",Hibernate.STRING)
				.addScalar("localElectionBodyId",Hibernate.LONG);;
		
		query.setParameter("scheduleStatusId", scheduleStatusId);
		
		query.setParameter("campId", campId);
		
		//query.setParameter("programId", programId);
		
		query.setParameter("scheduleId", scheduleId);
		
		
		return query.list();
		
	} 
	
	public List<Object[]> getAvailableCallCountsForBatch(Long campId,Long programId,Long scheduleId,Long scheduleStatusId,Long batchId)
	{
		Query query = getSession().createSQLQuery("select count(distinct TCSI.tdp_cadre_id) as count,TCSIC.training_camp_caller_id as callerId from training_camp_schedule_invitee TCSI," +
				" training_camp_schedule_invitee_caller TCSIC,tdp_cadre TC, training_camp_schedule TCS" +
				" where TC.tdp_cadre_id = TCSI.tdp_cadre_id  and" +
				" TCSIC.training_camp_schedule_invitee_id = TCSI.training_camp_schedule_invitee_id and" +
				" TCS.training_camp_schedule_id = TCSI.training_camp_schedule_id " +
				" and TCSI.schedule_invitee_status_id = :scheduleStatusId and TCSIC.call_purpose_id =1 " +
				" and TCSI.training_camp_schedule_id = :scheduleId and  TCS.training_camp_id = :campId " +
				" and TCSI.training_camp_schedule_invitee_id not in(select distinct TCSIC1.training_camp_schedule_invitee_id from training_camp_schedule_invitee_caller TCSIC1 " +
				" where TCSIC1.call_purpose_id = 2) and TCSI.attending_batch_id =:batchId  group by TCSIC.training_camp_caller_id ")
				.addScalar("count", Hibernate.LONG)
				.addScalar("callerId",Hibernate.LONG);
		query.setParameter("scheduleStatusId", scheduleStatusId);
		query.setParameter("campId", campId);
		//query.setParameter("programId", programId);
		
		query.setParameter("scheduleId", scheduleId);
		query.setParameter("batchId", batchId);
		return query.list();	
	}
	public Long getAllCallersCount(Date startDate,Date endDate,String type){
		
		StringBuilder str = new StringBuilder();
		
		boolean flag =false;
		str.append("select count(model.trainingCampScheduleInviteeId) from  TrainingCampScheduleInvitee model  where ");
		
		
		if(type.equalsIgnoreCase("totalCallers")){
			if(startDate !=null && endDate !=null){
				flag=true;
				str.append(" (date(model.insertedTime)>=:startDate and date(model.insertedTime)<=:endDate) ");
			}
		}
		else if(type.equalsIgnoreCase("todayCallers")){
			flag =true;
			str.append(" date(model.insertedTime) =:startDate ");
		}
		
		Query query=null;
		if(flag){
			query=getSession().createQuery(str.toString());
		}
		else{
			 query=getSession().createQuery(str.substring(0,str.length()-6).trim());
		}
		
		if(startDate !=null && endDate !=null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		else if(startDate !=null){
			query.setDate("startDate", startDate);
		}
		return (Long) query.uniqueResult();
	}
	
	
	public Long getBatchMembersCountByStatus(Long batchId,Long statusId,String callpurpose)
	{
		/*Query query = getSession().createSQLQuery("select count(distinct TCSI.tdp_cadre_id) as cnt" +
				" from training_camp_schedule_invitee_caller TCSIC ,training_camp_schedule_invitee TCSI where " +
				" TCSIC.training_camp_schedule_invitee_id = TCSI.training_camp_schedule_invitee_id and TCSI.schedule_invitee_status_id = :statusId" +
				" and TCSI.attending_batch_id =:batchId and TCSIC.call_purpose_id = 2")*/
		StringBuilder str =new StringBuilder(); 
		
		str.append("select count(distinct TCSI.tdp_cadre_id) as cnt" +
				" from training_camp_schedule_invitee_caller TCSIC ,training_camp_schedule_invitee TCSI where" +
				" TCSI.attending_batch_id =:batchId ");
		
		if(statusId !=null && statusId !=0l)
		{
			str.append(" and TCSI.schedule_invitee_status_id = :statusId ");
		}
		if(callpurpose.equalsIgnoreCase("confirmation")){
			str.append(" and TCSIC.call_purpose_id = 2 ");
		}
		else if(callpurpose.equalsIgnoreCase("invitation")){
			str.append(" and TCSIC.call_purpose_id = 1 ");
		}
		
		Query query = getSession().createSQLQuery(str.toString())
		
		.addScalar("cnt",Hibernate.LONG);
		query.setParameter("batchId", batchId);
		query.setParameter("statusId", statusId);
		
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getCallBackLaterMembersCount(Long campId, Date startDate, Date endDate)
	{
		Query query = getSession().createQuery(" select model.trainingCampSchedule.trainingCamp.trainingCampId, model.trainingCampSchedule.trainingCamp.campName, " +
						" date(model.laterCallBackTime), count(model.trainingCampScheduleInviteeId) from TrainingCampScheduleInvitee model " +
						" where model.trainingCampSchedule.trainingCamp.trainingCampId = :campId " + 
						" and (date(model.laterCallBackTime)>=:startDate and date(model.laterCallBackTime)<=:endDate) " +
						" and model.laterCallBackTime is not null group by date(model.laterCallBackTime), model.trainingCampSchedule.trainingCamp.trainingCampId ");
		
		query.setParameter("campId", campId);
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		
		return query.list();
	}
	
	public List<Object[]> getInviteeCountOfCadreProgramWise(Long cadreId){
		
	/*	Query query = getSession().createQuery(" select model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, " +
				" count(model.trainingCampScheduleInviteeId),model.trainingCampSchedule.trainingCampProgram.programName " +
				" from TrainingCampScheduleInvitee model " +
				" where model.tdpCadre.tdpCadreId =:cadreId and model.trainingCampBatch.attendeeTypeId = 1 " +
				" group by model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId ");
		*/
		
		Query query = getSession().createQuery(" select model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, " +
				" count(model.trainingCampBatchAttendeeId),model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.programName " +
				" from TrainingCampBatchAttendee model " +
				" where model.tdpCadre.tdpCadreId =:cadreId and model.isDeleted='false'  " +
				" group by model.trainingCampBatch.trainingCampSchedule.trainingCampProgram.trainingCampProgramId ");
		
		query.setParameter("cadreId",cadreId);
		
		return query.list();
	}
	public List<Object[]> getLatestRemarkOfCandidateOfProgram(Long programId,Long cadreId){
		
		Query query = getSession().createQuery(" select model.trainingCampScheduleInviteeId," +
				" model.remarks,model.updatedTime " +
				" from TrainingCampScheduleInvitee model " +
				" where " +
				" model.tdpCadre.tdpCadreId =:tdpCadreId " +
				" and model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId =:programId " +
				" and model.remarks is not null ");
		
		query.setParameter("programId",programId);
		query.setParameter("tdpCadreId",cadreId);
		
		return query.list();
	}
	
}
