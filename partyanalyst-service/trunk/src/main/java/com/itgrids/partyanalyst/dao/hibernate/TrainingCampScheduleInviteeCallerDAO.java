package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampScheduleInviteeCallerDAO;
import com.itgrids.partyanalyst.dto.TraingCampDataVO;
import com.itgrids.partyanalyst.model.TrainingCampScheduleInviteeCaller;

public class TrainingCampScheduleInviteeCallerDAO extends GenericDaoHibernate<TrainingCampScheduleInviteeCaller, Long> implements ITrainingCampScheduleInviteeCallerDAO{

	public TrainingCampScheduleInviteeCallerDAO() {
		super(TrainingCampScheduleInviteeCaller.class);
	}
	
	public List<Object[]> getCallerWiseAssignedCalls(List<Long> userIds,Date startDate,Date endDate,String type,String agentType){
		
		StringBuilder str=new StringBuilder();
		
		str.append(" select model.trainingCampUser.userId,count(model.trainingCampScheduleInviteeCallerId),model.trainingCampUser.lastName from  TrainingCampScheduleInviteeCaller model " +
				" where  ");
		
		if(startDate !=null && endDate !=null){
			str.append(" (date(model.updatedTime)>=:startDate and date(model.updatedTime)<=:endDate) ");
		}
		if(userIds !=null && userIds.size()>0){
			str.append(" and model.trainingCampUser.userId in (:userIds) ");
		}
		
		if(agentType !=null && agentType.equalsIgnoreCase("Invitation")){
			str.append(" and model.campCallPurpose.campCallPurpose =1 ");
		}
		else if(agentType !=null && agentType.equalsIgnoreCase("Confirmation")){
			str.append(" and model.campCallPurpose.campCallPurpose =2 ");
		}
		
		
		if(type !=null){
			if(type.equalsIgnoreCase("completedCount")){//dialed calls
				str.append(" and model.callStatusId is not null ");
			}
			else if(type.equalsIgnoreCase("pendingCount")){//pending calls
				str.append(" and model.callStatusId is null ");
			}
			else if(type.equalsIgnoreCase("dialedCalls")){
				str.append(" and model.callStatusId is not null ");
			}
		}
		
		str.append(" group by  model.trainingCampUser.userId order by model.trainingCampUser.userId ");
		
		Query query=getSession().createQuery(str.toString());
		
		
		if(startDate !=null && endDate !=null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(userIds !=null && userIds.size()>0){
			query.setParameterList("userIds",userIds);
		}
		
		return query.list();
	}
	public Long getAllCallersCount(Date startDate,Date endDate,String type){
		
		StringBuilder str = new StringBuilder();
		
		str.append("select count(model.trainingCampScheduleInviteeCallerId) from  TrainingCampScheduleInviteeCaller model  where  ");
		
		if(type.equalsIgnoreCase("totalCallers")){
			str.append(" (date(model.updatedTime)>=:startDate and date(model.updatedTime)<=:endDate) ");
		}
		else if(type.equalsIgnoreCase("todayCallers")){
			str.append(" date(model.updatedTime) =:startDate ");
		}
		
		Query query=getSession().createQuery(str.toString());
		
		if(startDate !=null && endDate !=null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		else if(startDate !=null){
			query.setDate("startDate", startDate);
		}
		return (Long) query.uniqueResult();
	}
	
	
	/*select TCSIC.training_camp_caller_id,SIS.schedule_invitee_status_id,count(TCSI.training_camp_schedule_invitee_id) from training_camp_schedule_invitee_caller TCSIC,training_camp_user TCU,training_camp_schedule_invitee TCSI
	,schedule_invitee_status SIS
	where  
	TCSIC.training_camp_caller_id = TCU.training_camp_user_id
	and TCSI.training_camp_schedule_invitee_id = TCSIC.training_camp_schedule_invitee_id
	and SIS.schedule_invitee_status_id=TCSI.schedule_invitee_status_id
	 and SIS.schedule_invitee_status_id is not null 
	 group by TCSIC.training_camp_caller_id,SIS.schedule_invitee_status_id;*/
	
	public List<Object[]> getCallStatusContsOfInvitees(List<Long> userIds,Date startDate,Date endDate,String agentType){
		
		StringBuilder str=new StringBuilder();
		
		str.append(" select model.trainingCampUser.userId,model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId,model.trainingCampScheduleInvitee.scheduleInviteeStatus.status,count(model.trainingCampScheduleInvitee.trainingCampScheduleInviteeId),model.trainingCampUser.lastName " +
				" from  TrainingCampScheduleInviteeCaller model " +
				" where model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId is not null ");
		
		if(agentType.equalsIgnoreCase("Invitation")){
			str.append(" and model.campCallPurpose.campCallPurpose =1 ");
		}
		else if(agentType.equalsIgnoreCase("Confirmation")){
			str.append(" and model.campCallPurpose.campCallPurpose =2 ");
		}
		
		if(startDate !=null && endDate !=null){
			str.append(" and (date(model.updatedTime)>=:startDate and date(model.updatedTime)<=:endDate) ");
		}
		if(userIds !=null && userIds.size()>0){
			str.append(" and model.trainingCampUser.userId in (:userIds) ");
		}
		str.append(" group by model.trainingCampUser.userId,model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId ");
		
		Query query=getSession().createQuery(str.toString());
		
		if(startDate !=null && endDate !=null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(userIds !=null && userIds.size()>0){
			query.setParameterList("userIds",userIds);
		}
		return query.list();
	}
	
	public List<Object[]> getScheduleWiseCallStatusCount(Long callerId,Long callPurposeId)
	{
		StringBuilder str = new StringBuilder();
		
		str.append("select count(model.trainingCampScheduleInviteeCallerId)," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.programName," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.campName," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleCode," +
				" campCallStatus.campCallStatusId,campCallStatus.status," +
				" model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId" +
				
				" from TrainingCampScheduleInviteeCaller model left join model.campCallStatus campCallStatus " +
				" where model.trainingCampCallerId = :callerId and model.callPurposeId = :callPurposeId  " +
				" group by model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId,model.campCallStatus.campCallStatusId,model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("callerId", callerId);
		query.setParameter("callPurposeId", callPurposeId);
		return query.list();
		
	}
	
	
	public List<Object[]> getBatchWiseWiseCallStatusCount(Long callerId,Long callPurposeId)
	{
		StringBuilder str = new StringBuilder();
		
		str.append("select count(model.trainingCampScheduleInviteeCallerId)," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.programName," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.campName," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleCode," +
				" campCallStatus.campCallStatusId,campCallStatus.status," +
				" model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId," +
				" model.trainingCampScheduleInvitee.trainingCampBatch.trainingCampBatchId," +
				" model.trainingCampScheduleInvitee.trainingCampBatch.trainingCampBatchName" +
				" from TrainingCampScheduleInviteeCaller model left join model.campCallStatus campCallStatus " +
				" where model.trainingCampCallerId = :callerId and model.callPurposeId= :callPurposeId " +
				" group by model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId,model.trainingCampScheduleInvitee.trainingCampBatch.trainingCampBatchId,model.campCallStatus.campCallStatusId,model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("callerId", callerId);
		query.setParameter("callPurposeId", callPurposeId);
		return query.list();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getMembersCountByBatchStatusAndCallerId(Long callerId,String batchStatus)
	{
		Query query = getSession().createQuery(" select count(distinct model.trainingCampScheduleInvitee.tdpCadre.tdpCadreId)," +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.programName, " +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.campName, " +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId,model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleCode," +
				" model.trainingCampScheduleInvitee.trainingCampBatch.trainingCampBatchId,model.trainingCampScheduleInvitee.trainingCampBatch.trainingCampBatchName from TrainingCampScheduleInviteeCaller model " +
				" where model.trainingCampCallerId =:callerId and model.trainingCampScheduleInvitee.trainingCampBatch.batchStatus.status =:batchStatus " +
				" group by model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId," +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId," +
				"model.trainingCampScheduleInvitee.trainingCampBatch.trainingCampBatchId ");
		
		query.setParameter("callerId", callerId);
		query.setParameter("batchStatus", batchStatus);
		return query.list();
	}

	public List<Object[]> getScheduleWisememberDetailsCount(TraingCampDataVO inputVo,List<Long> statusIds,String statusType,String status,Date toDayDate)
	{
		StringBuilder str = new StringBuilder();
		
		str.append("select model.trainingCampScheduleInvitee.tdpCadre.tdpCadreId," +
				" model.trainingCampScheduleInvitee.tdpCadre.firstname," +
				" model.trainingCampScheduleInvitee.tdpCadre.lastname," +
				" model.trainingCampScheduleInvitee.tdpCadre.mobileNo," +
				" model.trainingCampScheduleInvitee.tdpCadre.image," +
				" model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId,model.trainingCampScheduleInvitee.scheduleInviteeStatus.status," +
				"  model.trainingCampScheduleInvitee.tdpCadre.age,model.trainingCampScheduleInvitee.tdpCadre.userAddress.district.districtName," +
				" model.trainingCampScheduleInvitee.trainingCampScheduleInviteeId,model.trainingCampScheduleInviteeCallerId," +
				" model.trainingCampScheduleInvitee.remarks," +
				" model.trainingCampScheduleInvitee.tdpCadre.userAddress.constituency.name " +
				" from TrainingCampScheduleInviteeCaller model left join model.campCallStatus campCallStatus " +
				" where model.trainingCampCallerId = :callerId " +
				" and model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId = :programId" +
				" and model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId =:campId" +
				" and model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId =:scheduleId");
				//" and model.trainingCampScheduleInvitee.trainingCampBatch.batchStatus.batchStatusId in(1,2) ");
		if(status.equalsIgnoreCase("undialed"))
			str.append(" and campCallStatus.campCallStatusId is null");
		if((statusIds != null && statusIds.size() > 0) && statusType.equalsIgnoreCase("callStatus"))
					str.append(" and campCallStatus.campCallStatusId in(:statusIds)");
		if((statusIds != null && statusIds.size() > 0) && statusType.equalsIgnoreCase("scheduleCallStatus"))
			str.append(" and model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId in(:statusIds)");
		if(inputVo.getBatchId() > 0)
			str.append(" and model.trainingCampScheduleInvitee.trainingCampBatch.trainingCampBatchId = :batchId " );
		str.append(" and model.callPurposeId = :callPurposeId");
		
		if(toDayDate != null)
		 str.append(" and date(model.trainingCampScheduleInvitee.callBackTime) =:toDayDate ");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("callerId", inputVo.getUserId());
		query.setParameter("callPurposeId", inputVo.getPurposeId());
		query.setParameter("programId", inputVo.getProgramId());
		query.setParameter("campId", inputVo.getCampId());
		query.setParameter("scheduleId", inputVo.getScheduleId());
		if(statusIds != null && statusIds.size() > 0)
		query.setParameterList("statusIds", statusIds);
		if(inputVo.getBatchId() > 0)
		query.setParameter("batchId", inputVo.getBatchId());	
		
		if(toDayDate != null)
		 query.setDate("toDayDate", toDayDate);
		
		return query.list();
	}
	
	
	
	/*public List<Object[]> getTodayScheduleWiseCallStatusCount(Long callerId,Long callPurposeId,Date fromDate,Date toDate)
	{
		StringBuilder str = new StringBuilder();
		
		str.append("select count(model.trainingCampScheduleInviteeCallerId)," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.programName," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.campName," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleCode," +
				" campCallStatus.campCallStatusId,campCallStatus.status," +
				" model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId" +
				
				" from TrainingCampScheduleInviteeCaller model left join model.campCallStatus campCallStatus " +
				" where model.trainingCampCallerId = :callerId and model.callPurposeId = :callPurposeId");
		if(fromDate != null && toDate != null)
			str.append(" 	
		str.append(" group by model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId,model.campCallStatus.campCallStatusId");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("callerId", callerId);
		query.setParameter("callPurposeId", callPurposeId);
		return query.list();
		
	}*/
	
	public List<Object[]> getScheduleAndConfirmationCallsOfCallerToAgent(List<Long> userIds,Date startDate,Date endDate,String type){
		
		/*select CCP.camp_call_purpose,CCP.purpose,count(training_camp_schedule_invitee_caller_id)
		from training_camp_schedule_invitee_caller TCSI,camp_call_purpose CCP,training_camp_user tcu  
		where 
		TCSI.call_purpose_id = CCP.camp_call_purpose
		and TCSI.training_camp_caller_id = tcu.training_camp_user_id
		and  tcu.training_camp_user_id in (1)
		group by CCP.camp_call_purpose;*/
		
		StringBuilder scheduleAndConfirmationCalls = new StringBuilder();
		
		scheduleAndConfirmationCalls.append(" select model.campCallPurpose.campCallPurpose,model.campCallPurpose.purpose,count(model.trainingCampScheduleInviteeCallerId)" +
											" from TrainingCampScheduleInviteeCaller model where " );
		
		if(startDate !=null && endDate !=null){
			scheduleAndConfirmationCalls.append(" (date(model.updatedTime)>=:startDate and date(model.updatedTime)<=:endDate)");
		}
		if(userIds !=null && userIds.size()>0){
			scheduleAndConfirmationCalls.append(" and model.trainingCampUser.userId in (:userIds) ");
		}
		
		if(type.equalsIgnoreCase("dialedCalls")){
			scheduleAndConfirmationCalls.append(" and model.campCallStatus.campCallStatusId is not null ");
		}
		
		scheduleAndConfirmationCalls.append(" group by model.campCallPurpose.campCallPurpose ");
		
		Query scheduleAndConfirmationCallsQuery = getSession().createQuery(scheduleAndConfirmationCalls.toString());
		
		if(startDate !=null && endDate !=null){
			scheduleAndConfirmationCallsQuery.setDate("startDate", startDate);
			scheduleAndConfirmationCallsQuery.setDate("endDate", endDate);
		}
		if(userIds !=null && userIds.size()>0){
			scheduleAndConfirmationCallsQuery.setParameterList("userIds", userIds);
		}
		
		return scheduleAndConfirmationCallsQuery.list();
	}
	
	public List<Object[]> getStatusWiseCount(List<Long> userIds,Date startDate,Date endDate,String searchType){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId," +
				" model.trainingCampScheduleInvitee.scheduleInviteeStatus.status,count(model.trainingCampScheduleInvitee.trainingCampScheduleInviteeId) " +
				" from  TrainingCampScheduleInviteeCaller model " +
				" where model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId is not null ");
		if(startDate !=null && endDate !=null){
			queryStr.append(" and (date(model.updatedTime)>=:startDate and date(model.updatedTime)<=:endDate) ");
		}
		if(userIds != null && userIds.size()>0){
			queryStr.append(" and model.trainingCampUser.userId in (:userIds) ");
		}
		if(searchType != null && searchType.equalsIgnoreCase("notStarted"))
		{
			queryStr.append(" and model.trainingCampScheduleInvitee.trainingCampSchedule.status ='Not Started' ");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("running"))
		{
			queryStr.append(" and model.trainingCampScheduleInvitee.trainingCampSchedule.status ='Progress' ");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("completed"))
		{
			queryStr.append(" and model.trainingCampScheduleInvitee.trainingCampSchedule.status ='Completed' ");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("cancelled"))
		{
			queryStr.append(" and model.trainingCampScheduleInvitee.trainingCampSchedule.status ='Cancelled' ");
		}
		
		queryStr.append(" group by model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId ");
		Query query=getSession().createQuery(queryStr.toString());
		
		
		if(startDate !=null && endDate !=null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(userIds !=null && userIds.size()>0){
			query.setParameterList("userIds", userIds);
		}
		
		
		return query.list();
	}
	
	
public List<Object[]> getBatchConfirmedMemberDetails(List<Long> userIds,Date startDate,Date endDate,String searchType,String purpose){
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select distinct model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.campName," +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.programName," +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleCode, " +
				" model.campCallPurpose.campCallPurpose," +
				" model.campCallPurpose.purpose," +
				" count(model.trainingCampScheduleInvitee.trainingCampScheduleInviteeId) " +
				" from  TrainingCampScheduleInviteeCaller model " +
				" where model.campCallPurpose.campCallPurpose is not null ");
		if(startDate !=null && endDate !=null){
			queryStr.append(" and (date(model.updatedTime)>=:startDate and date(model.updatedTime)<=:endDate) ");
		}
		if(userIds != null && userIds.size()>0){
			queryStr.append(" and model.trainingCampUser.userId in (:userIds) ");
		}
		if(searchType != null && searchType.equalsIgnoreCase("notStarted"))
		{
			queryStr.append(" and model.trainingCampScheduleInvitee.trainingCampSchedule.status ='Not Started' ");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("running"))
		{
			queryStr.append(" and model.trainingCampScheduleInvitee.trainingCampSchedule.status ='Progress' ");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("completed"))
		{
			queryStr.append(" and model.trainingCampScheduleInvitee.trainingCampSchedule.status ='Completed' ");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("cancelled"))
		{
			queryStr.append(" and model.trainingCampScheduleInvitee.trainingCampSchedule.status ='Cancelled' ");
		}
		if(purpose != null && !purpose.isEmpty())
			queryStr.append(" and model.campCallPurpose.purpose like '%"+purpose+"%'");
		
		queryStr.append(" group by model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.programName," +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId, model.campCallPurpose.campCallPurpose ");
		Query query=getSession().createQuery(queryStr.toString());
		
		
		if(startDate !=null && endDate !=null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(userIds !=null && userIds.size()>0){
			query.setParameterList("userIds", userIds);
		}
		
		
		return query.list();
	}

	public List<Long> getAlreadyInvitedMembersInviteeIdsListByScheduleId(Long scheduleId,Long callPurposeId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.trainingCampScheduleInvitee.trainingCampScheduleInviteeId  from TrainingCampScheduleInviteeCaller model where " +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId =:scheduleId and model.callPurposeId =:callPurposeId " +
				" and model.campCallPurpose.purpose !='Confirmation' " +
				" order by  model.trainingCampScheduleInvitee.trainingCampScheduleInviteeId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("scheduleId", scheduleId);
		query.setParameter("callPurposeId", callPurposeId);
		return query.list();
	}
	
	public List<Long> getInterestedAndInvitedMembersListForBatchConfirmation(List<Long> callerIdsList,Long scheduleId,Long batchId,String callPurposeStr,String memberTypeStr)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.trainingCampScheduleInvitee.trainingCampScheduleInviteeId  from TrainingCampScheduleInviteeCaller model where " +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId =:scheduleId and model.campCallPurpose.purpose like '%"+callPurposeStr+"%'  and " +
				"  model.trainingCampScheduleInvitee.scheduleInviteeStatus.status like '%"+memberTypeStr+"%' ");
		if(batchId != null && batchId.longValue()>0L)
			queryStr.append(" and model.trainingCampScheduleInvitee.attendingBatchId =:batchId ");
		
		if(callerIdsList != null && callerIdsList.size() >0L)
			queryStr.append(" and model.trainingCampCallerId  in( :callerIdsList) ");
		
		queryStr.append(" order by  model.trainingCampScheduleInvitee.trainingCampScheduleInviteeId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("scheduleId", scheduleId);
		if(callerIdsList != null && callerIdsList.size() >0L)
			query.setParameterList("callerIdsList", callerIdsList);
		if(batchId != null && batchId.longValue()>0L)
			query.setParameter("batchId", batchId);
		return query.list();
	}	
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCallStatusCountByTrainingCampCallerId(Long trainingCampCallerId)
	{
		Query query = getSession().createQuery(" select count(model.trainingCampScheduleInviteeCallerId),campCallStatus.status,campCallStatus.campCallStatusId from TrainingCampScheduleInviteeCaller model " +
				" left join model.campCallStatus campCallStatus  where " +
				" model.trainingCampCallerId =:trainingCampCallerId group by campCallStatus.campCallStatusId ");
		
		query.setParameter("trainingCampCallerId", trainingCampCallerId);
		return query.list();
				
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getInterestedMembersCountByCampCallerId(Long trainingCampCallerId)
	{
		Query query = getSession().createQuery(" select count(distinct model.trainingCampScheduleInvitee.tdpCadre.tdpCadreId),model.trainingCampScheduleInvitee.scheduleInviteeStatus.status " +
				" from TrainingCampScheduleInviteeCaller model where model.trainingCampScheduleInvitee.scheduleInviteeStatus.status in ('Interested','Not Interested','Not Now') and " +
				" model.trainingCampCallerId =:trainingCampCallerId group by model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId ");
		
		query.setParameter("trainingCampCallerId", trainingCampCallerId);
		return query.list();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getScheduleConfirmationCallBackDetails(Long campCallerId,Long callPurposeId,Date todayDate,List<Long> scheduleInviteeStatusIdsList)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.trainingCampScheduleInviteeCallerId),model.trainingCampScheduleInvitee.scheduleInviteeStatus.status from TrainingCampScheduleInviteeCaller model " +
				" where model.trainingCampCallerId =:campCallerId and model.campCallPurpose.campCallPurpose =:callPurposeId and" +
				"  model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId in (:scheduleInviteeStatusIdsList) ");
		
		if(todayDate != null)
		 str.append(" and date(model.trainingCampScheduleInvitee.callBackTime) =:todayDate ");
		
		str.append(" group by model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId ");
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("campCallerId", campCallerId);
		query.setParameter("callPurposeId", callPurposeId);
		query.setParameterList("scheduleInviteeStatusIdsList", scheduleInviteeStatusIdsList);
		if(todayDate != null)
			query.setDate("todayDate", todayDate);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getBatchConfirmationCallBackDetails(Long campCallerId,Long callPurposeId,Date todayDate,List<Long> scheduleInviteeStatusIdsList)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.trainingCampScheduleInviteeCallerId),model.trainingCampScheduleInvitee.scheduleInviteeStatus.status from TrainingCampScheduleInviteeCaller model " +
				" where model.trainingCampCallerId =:campCallerId and model.campCallPurpose.campCallPurpose =:callPurposeId and" +
				"  model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId in (:scheduleInviteeStatusIdsList) and " +
				" model.trainingCampScheduleInvitee.attendingBatchId is not null ");
		
		if(todayDate != null)
		 str.append(" and date(model.trainingCampScheduleInvitee.callBackTime) =:todayDate ");
		
		str.append(" group by model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId ");
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("campCallerId", campCallerId);
		query.setParameter("callPurposeId", callPurposeId);
		query.setParameterList("scheduleInviteeStatusIdsList", scheduleInviteeStatusIdsList);
		if(todayDate != null)
			query.setDate("todayDate", todayDate);
		return query.list();
	}
	public Long getCallDetailsOfCaller(List<Long> userIds,Date startDate,Date endDate,String type,String agentType){
		StringBuilder str = new StringBuilder();
		
		str.append(" select count(model.trainingCampScheduleInviteeCallerId) from  TrainingCampScheduleInviteeCaller model " +
				" where  ");
		
		if(startDate !=null && endDate !=null){
			str.append(" (date(model.updatedTime)>=:startDate and date(model.updatedTime)<=:endDate) ");
		}
		if(userIds !=null && userIds.size()>0){
			str.append(" and model.trainingCampUser.userId in (:userIds) ");
		}
		if(agentType !=null){
			if(agentType.equalsIgnoreCase("Invitation")){
				str.append(" and model.campCallPurpose.campCallPurpose =1 ");
			}
			else if(agentType.equalsIgnoreCase("Confirmation")){
				str.append(" and model.campCallPurpose.campCallPurpose =2 ");
			}
		}
		
		if(type !=null){
			if(type.equalsIgnoreCase("dialedCalls")){
				str.append(" and model.callStatusId is not null ");
			}
			else if(type.equalsIgnoreCase("notDialed")){
				str.append(" and model.callStatusId is null ");
			}
		}
		
		Query query=getSession().createQuery(str.toString());
		
		if(startDate !=null && endDate !=null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(userIds !=null && userIds.size()>0){
			query.setParameterList("userIds",userIds);
		}
		
		return  (Long) query.uniqueResult();
		
	}
	
	public List<Object[]> getScheduleWiseDayWiseCallBackCount(Long callerId,Long callPurposeId,Date date)
	{
		StringBuilder str = new StringBuilder();
		
		str.append("select count(model.trainingCampScheduleInviteeCallerId)," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.programName," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.campName," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleCode," +
				" campCallStatus.campCallStatusId,campCallStatus.status," +
				" model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId" +
				
				
				" from TrainingCampScheduleInviteeCaller model left join model.campCallStatus campCallStatus " +
				" where model.trainingCampCallerId = :callerId and model.callPurposeId = :callPurposeId  " +
				" and date(model.trainingCampScheduleInvitee.callBackTime) = :date and model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId in(6,7) " +
				" group by model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId,model.campCallStatus.campCallStatusId,model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("callerId", callerId);
		query.setParameter("callPurposeId", callPurposeId);
		query.setDate("date", date);
		return query.list();
		
	}
	
	public List<Object[]> getCallDetailsOfCallerByStatus(List<Long> userIds,Date startDate,Date endDate,String agentType){
		
		StringBuilder str=new StringBuilder();
		
		str.append(" select model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId,model.trainingCampScheduleInvitee.scheduleInviteeStatus.status,count(model.trainingCampScheduleInvitee.trainingCampScheduleInviteeId) " +
				" from  TrainingCampScheduleInviteeCaller model " +
				" where model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId is not null ");
		
		if(agentType !=null){
			if(agentType.equalsIgnoreCase("Invitation")){
				str.append(" and model.campCallPurpose.campCallPurpose =1 ");
			}
			else if(agentType.equalsIgnoreCase("Confirmation")){
				str.append(" and model.campCallPurpose.campCallPurpose =2 ");
			}
		}
		
		if(startDate !=null && endDate !=null){
			str.append(" and (date(model.updatedTime)>=:startDate and date(model.updatedTime)<=:endDate) ");
		}
		if(userIds !=null && userIds.size()>0){
			str.append(" and model.trainingCampUser.userId in (:userIds) ");
		}
		str.append(" group by model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId ");
		
		Query query=getSession().createQuery(str.toString());
		
		if(startDate !=null && endDate !=null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		if(userIds !=null && userIds.size()>0){
			query.setParameterList("userIds",userIds);
		}
		return query.list();
	}
	
	public List<Object[]> getBatchWiseDayWiseCallBackCount(Long callerId,Long callPurposeId,Date date)
	{
		StringBuilder str = new StringBuilder();
		
		str.append("select count(model.trainingCampScheduleInviteeCallerId)," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.programName," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.campName," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId," +
				"model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleCode," +
				" campCallStatus.campCallStatusId,campCallStatus.status," +
				" model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId," +
				" model.trainingCampScheduleInvitee.trainingCampBatch.trainingCampBatchId," +
				" model.trainingCampScheduleInvitee.trainingCampBatch.trainingCampBatchName" +
				" from TrainingCampScheduleInviteeCaller model left join model.campCallStatus campCallStatus " +
				" where model.trainingCampCallerId = :callerId and model.callPurposeId= :callPurposeId " +
				" and date(model.trainingCampScheduleInvitee.callBackTime) = :date and model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId in(6,7) " +
				" group by model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId,model.trainingCampScheduleInvitee.trainingCampBatch.trainingCampBatchId,model.campCallStatus.campCallStatusId,model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("callerId", callerId);
		query.setParameter("callPurposeId", callPurposeId);
		query.setDate("date", date);
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSchduleBatchConfirmationCallBackDetails(Long campCallerId,Long callPurposeId,Date todayDate,List<Long> scheduleInviteeStatusIdsList,List<Long> batchStatusIdsList)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.trainingCampScheduleInviteeCallerId),model.trainingCampScheduleInvitee.scheduleInviteeStatus.status from TrainingCampScheduleInviteeCaller model " +
				" where model.trainingCampCallerId =:campCallerId and model.campCallPurpose.campCallPurpose =:callPurposeId and" +
				"  model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId in (:scheduleInviteeStatusIdsList) and " +
				" model.trainingCampScheduleInvitee.trainingCampBatch.batchStatus.batchStatusId in (:batchStatusIdsList) ");
		
		if(todayDate != null)
		 str.append(" and date(model.trainingCampScheduleInvitee.callBackTime) =:todayDate ");
		
		str.append(" group by model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId ");
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("campCallerId", campCallerId);
		query.setParameter("callPurposeId", callPurposeId);
		query.setParameterList("scheduleInviteeStatusIdsList", scheduleInviteeStatusIdsList);
		query.setParameterList("batchStatusIdsList", batchStatusIdsList);
		if(todayDate != null)
			query.setDate("todayDate", todayDate);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Long> getAllUpcomingTrainingCampScheduleDetails(List<Long> scheduleIds,Date fromDate,Date toDate,String type){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select distinct model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId from TrainingCampScheduleInviteeCaller model " +
				" where  ");
		
		if(fromDate !=null && toDate !=null){
			queryStr.append("  (date(model.trainingCampScheduleInvitee.trainingCampSchedule.fromDate)>=:fromDate and date(model.trainingCampScheduleInvitee.trainingCampSchedule.toDate)<=:toDate) ");
		}
		if(scheduleIds !=null && scheduleIds.size() > 0){
			queryStr.append( " and model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId in (:scheduleIds) " );
		}
		if(type !=null){
			queryStr.append(" and model.campCallPurpose = '"+type+"' ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(scheduleIds !=null  && scheduleIds.size() > 0){
			query.setParameterList("scheduleIds", scheduleIds);
		}
		if(fromDate !=null && toDate !=null){
			query.setParameter("fromDate",fromDate);
			query.setParameter("toDate", toDate);
		}
		return query.list();
	}
	
	public List<Long> getAllocatedCountForConfirmation(Date fromDate,Date toDate,String type,Long callPurpose){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select distinct model.trainingCampScheduleInvitee.attendingBatchId  from TrainingCampScheduleInviteeCaller model " +
				" where (date(model.trainingCampScheduleInvitee.trainingCampSchedule.fromDate)>=:fromDate and date(model.trainingCampScheduleInvitee.trainingCampSchedule.toDate)<=:todate) ");
		if(type !=null){
			queryStr.append(" and model.trainingCampScheduleInvitee.trainingCampSchedule.status = '"+type+"' ");
		}
		queryStr.append(" and model.trainingCampScheduleInvitee.attendingBatchId is not null ");
		if(callPurpose !=null && callPurpose !=0l){
			queryStr.append(" and model.campCallPurpose.campCallPurpose = :callPurpose ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("fromDate", fromDate);
		query.setParameter("todate",toDate);
		
		if(callPurpose !=null && callPurpose !=0l){
			query.setParameter("callPurpose",callPurpose);
		}
		
		return query.list();
		
	}
	
	public List<Object[]> getCallerDistricts(Long userId)
	{
		Query query = getSession().createQuery("select distinct model.trainingCampScheduleInvitee.tdpCadre.userAddress.district.districtId," +
				" model.trainingCampScheduleInvitee.tdpCadre.userAddress.district.districtName " +
				" from TrainingCampScheduleInviteeCaller model" +
				" where model.trainingCampCallerId =:userId  ");
		query.setParameter("userId", userId);
		return query.list();
		
	}
	
	public List<Object[]> getCallerConstituenciesByDistrict(Long userId,Long districtId)
	{
		Query query = getSession().createQuery("select distinct constituency.constituencyId," +
				"  constituency.name " +
				" from TrainingCampScheduleInviteeCaller model" +
				" left join model.trainingCampScheduleInvitee.tdpCadre.userAddress.constituency constituency" +
				" where model.trainingCampCallerId =:userId and " +
				" model.trainingCampScheduleInvitee.tdpCadre.userAddress.district.districtId = :districtId");
		query.setParameter("userId", userId);
		query.setParameter("districtId", districtId);
		return query.list();
		
	}
	
	public List<Object[]> getCallerAgentMandalsByConstituency(Long userId,Long constituencyId)
	{
		Query query = getSession().createQuery("select distinct tehsil.tehsilId," +
				"  tehsil.tehsilName " +
				" from TrainingCampScheduleInviteeCaller model" +
				" left join model.trainingCampScheduleInvitee.tdpCadre.userAddress.tehsil tehsil" +
				" where model.trainingCampCallerId =:userId and " +
				" model.trainingCampScheduleInvitee.tdpCadre.userAddress.constituency.constituencyId = :constituencyId");
		query.setParameter("userId", userId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
		
	}
	
	public List<Object[]> getCallerAgentVillagesByMandal(Long userId,Long mandalId)
	{
		Query query = getSession().createQuery("select distinct panchayat.panchayatId," +
				"  panchayat.panchayatName " +
				" from TrainingCampScheduleInviteeCaller model" +
				" left join model.trainingCampScheduleInvitee.tdpCadre.userAddress.panchayat panchayat" +
				" where model.trainingCampCallerId =:userId and " +
				" model.trainingCampScheduleInvitee.tdpCadre.userAddress.tehsil.tehsilId = :mandalId");
		query.setParameter("userId", userId);
		query.setParameter("mandalId", mandalId);
		return query.list();
		
	}
	
	public List<Object[]> getScheduleWisememberDetailsCountForSearch(TraingCampDataVO inputVo,List<Long> statusIds,String statusType,String status,Date toDayDate)
	{
		StringBuilder str = new StringBuilder();
		
		str.append("select model.trainingCampScheduleInvitee.tdpCadre.tdpCadreId," +
				" model.trainingCampScheduleInvitee.tdpCadre.firstname," +
				" model.trainingCampScheduleInvitee.tdpCadre.lastname," +
				" model.trainingCampScheduleInvitee.tdpCadre.mobileNo," +
				" model.trainingCampScheduleInvitee.tdpCadre.image," +
				" model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId,model.trainingCampScheduleInvitee.scheduleInviteeStatus.status," +
				"  model.trainingCampScheduleInvitee.tdpCadre.age,model.trainingCampScheduleInvitee.tdpCadre.userAddress.district.districtName," +
				" model.trainingCampScheduleInvitee.trainingCampScheduleInviteeId,model.trainingCampScheduleInviteeCallerId," +
				" model.trainingCampScheduleInvitee.remarks," +
				" model.trainingCampScheduleInvitee.tdpCadre.userAddress.constituency.name " +
				" from TrainingCampScheduleInviteeCaller model left join model.campCallStatus campCallStatus ");
				if(inputVo.getCommitteeLevelId() != null && inputVo.getCommitteeLevelId() > 0)
				str.append(" ,TdpCommitteeMember model1 ");
				str.append(" where model.trainingCampCallerId = :callerId " +
				" and model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId = :programId" +
				" and model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId =:campId" +
				" and model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId =:scheduleId");
				if(inputVo.getCommitteeLevelId() != null && inputVo.getCommitteeLevelId() > 0)
				{
					str.append(" and model.trainingCampScheduleInvitee.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId" +
							" and model1.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId =:committeeLevelId ");
				}
		if(status.equalsIgnoreCase("undialed"))
			str.append(" and campCallStatus.campCallStatusId is null");
		if((statusIds != null && statusIds.size() > 0) && statusType.equalsIgnoreCase("callStatus"))
					str.append(" and campCallStatus.campCallStatusId in(:statusIds)");
		if((statusIds != null && statusIds.size() > 0) && statusType.equalsIgnoreCase("scheduleCallStatus"))
			str.append(" and model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId in(:statusIds)");
		if(inputVo.getBatchId() > 0)
			str.append(" and model.trainingCampScheduleInvitee.trainingCampBatch.trainingCampBatchId = :batchId " );
		str.append(" and model.callPurposeId = :callPurposeId");
		
		if(toDayDate != null)
		 str.append(" and date(model.trainingCampScheduleInvitee.callBackTime) =:toDayDate ");
		if(inputVo.getDistrictId() != null && inputVo.getDistrictId()  > 0)
		{
			 str.append(" and model.trainingCampScheduleInvitee.tdpCadre.userAddress.district.districtId =:districtId ");
		}
		if(inputVo.getConstituencyId() != null && inputVo.getConstituencyId() > 0)
		{
			 str.append(" and model.trainingCampScheduleInvitee.tdpCadre.userAddress.constituency.constituencyId =:constituencyId ");
		}
		if(inputVo.getMandalId() != null && inputVo.getMandalId() > 0)
		{
			 str.append(" and model.trainingCampScheduleInvitee.tdpCadre.userAddress.tehsil.tehsilId =:tehsilId ");
		}
		if(inputVo.getVillageId() != null && inputVo.getVillageId() > 0)
		{
			 str.append(" and model.trainingCampScheduleInvitee.tdpCadre.userAddress.panchayat.panchayatId =:panchayatId ");
		}
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("callerId", inputVo.getUserId());
		query.setParameter("callPurposeId", inputVo.getPurposeId());
		query.setParameter("programId", inputVo.getProgramId());
		query.setParameter("campId", inputVo.getCampId());
		query.setParameter("scheduleId", inputVo.getScheduleId());
		if(statusIds != null && statusIds.size() > 0)
		query.setParameterList("statusIds", statusIds);
		if(inputVo.getBatchId() > 0)
		query.setParameter("batchId", inputVo.getBatchId());	
		
		if(toDayDate != null)
		 query.setDate("toDayDate", toDayDate);
		if(inputVo.getDistrictId() != null && inputVo.getDistrictId() > 0)
			query.setParameter("districtId", inputVo.getDistrictId());
		if(inputVo.getConstituencyId() != null && inputVo.getConstituencyId() > 0)
			query.setParameter("constituencyId", inputVo.getConstituencyId());
		if(inputVo.getMandalId() != null && inputVo.getMandalId() > 0)
			query.setParameter("tehsilId", inputVo.getMandalId());
		if(inputVo.getVillageId() != null && inputVo.getVillageId() > 0)
			query.setParameter("panchayatId", inputVo.getVillageId());
		if(inputVo.getCommitteeLevelId() != null && inputVo.getCommitteeLevelId() > 0)
		{
			query.setParameter("committeeLevelId", inputVo.getCommitteeLevelId());
		}	
		
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAgentCallDetailsByCampCallerId(Long campCallerId,Long callPurposeId,Date toDayDate,List<Long> batchStatusIdsList)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.trainingCampScheduleInviteeCallerId),campCallStatus.status,campCallStatus.campCallStatusId," +
				" model.trainingCampScheduleInvitee.scheduleInviteeStatus.status,model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId" +
				" from TrainingCampScheduleInviteeCaller model " +
				" left join model.campCallStatus campCallStatus where model.trainingCampCallerId = :campCallerId ");
		
		if(callPurposeId != null)
		 str.append(" and model.campCallPurpose.campCallPurpose =:callPurposeId ");
		
		if(batchStatusIdsList != null && batchStatusIdsList.size() > 0)
		 str.append(" and model.trainingCampScheduleInvitee.trainingCampBatch.batchStatus.batchStatusId in(:batchStatusIdsList) ");
		
		if(toDayDate != null)
		 str.append(" and date(model.trainingCampScheduleInvitee.callBackTime) =:toDayDate ");
		
		str.append(" group by campCallStatus.campCallStatusId,model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId ");
			
		Query query = getSession().createQuery(str.toString());
		query.setParameter("campCallerId", campCallerId);
		
		if(callPurposeId != null)
		 query.setParameter("callPurposeId", callPurposeId);
		
		if(batchStatusIdsList != null && batchStatusIdsList.size() > 0)
			 query.setParameterList("batchStatusIdsList", batchStatusIdsList);
		
		if(toDayDate != null)
		 query.setDate("toDayDate", toDayDate);
		
		return query.list();
	}
	
	public List<Object[]> getAllocatedCallsForBatchConfirmationDetails(String searchType, Date startDate, Date endDate)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(TCSIC.trainingCampScheduleInviteeCallerId), count(TCSIC.callStatusId), TCB.trainingCampBatchId, TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.programName, ");
		str.append(" TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.campName, date(TCB.fromDate), date(TCB.toDate) ");
		
		str.append(" from TrainingCampScheduleInviteeCaller TCSIC, TrainingCampBatch TCB where TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId = TCB.trainingCampBatchId and TCSIC.callStatusId is not null ");
		
		if(startDate != null && endDate != null)
		{
			str.append(" and (date(TCB.fromDate) >=:startDate and date(TCB.toDate) <=:endDate) ");
		}
		if(searchType != null && searchType.equalsIgnoreCase("planned"))
		{
			str.append(" and TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.status = 'Not Started' ");
		}
		
		str.append(" group by TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, TCSIC.trainingCampScheduleInvitee.trainingCampSchedule.trainingCamp.trainingCampId, TCB.trainingCampBatchId ");
		
		Query query = getSession().createQuery(str.toString());
		if(startDate != null && endDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
		return query.list();
	}
	

	@SuppressWarnings("unchecked")
	public List<Object[]> getAgentsByCampCallerAdminId(Long campCallerAdminId)
	{
		Query query = getSession().createQuery(" select distinct model.trainingCampUser.userId,model.trainingCampUser.firstName,model.trainingCampUser.lastName" +
				" from TrainingCampScheduleInviteeCaller model where model.trainingCampCallerAdminId =:campCallerAdminId ");
		
		query.setParameter("campCallerAdminId", campCallerAdminId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getScheduleWiseDetailsCount(List<Long> callerIdsList,Date fromDate,Date toDate,String dataType,String searchType,Date todayDate){
		
		StringBuilder str = new StringBuilder();
		boolean isDatesNull = false;
		str.append("select TCSI.trainingCampSchedule.trainingCamp.campName," +
				"TCSI.trainingCampSchedule.trainingCampProgram.programName," +
				"TCSI.trainingCampSchedule.trainingCampScheduleCode, " +
				"TCSI.trainingCampSchedule.trainingCampScheduleId,count(TCSIC.trainingCampScheduleInviteeCallerId) ");
		
		str.append(" from TrainingCampScheduleInviteeCaller TCSIC,TrainingCampScheduleInvitee TCSI,TrainingCampBatch TCB where " +
				" TCSIC.trainingCampScheduleInvitee.trainingCampScheduleInviteeId = TCSI.trainingCampScheduleInviteeId and TCSI.trainingCampSchedule.trainingCampId = TCB.trainingCampBatchId ");
		
		if(fromDate != null && toDate != null)
		{
			str.append(" and (date(TCSIC.updatedTime) >=:fromDate and date(TCSIC.updatedTime) <=:toDate) ");
			
			if(searchType !=null && searchType.equalsIgnoreCase("notStarted")){
				str.append(" and date(TCSI.trainingCampSchedule.fromDate) >:toDate ");
			}
			else if(searchType !=null && searchType.equalsIgnoreCase("running")){
				str.append(" and :toDate between date(TCSI.trainingCampSchedule.fromDate) and date(TCSI.trainingCampSchedule.toDate) ");
			}
			else if(searchType !=null && searchType.equalsIgnoreCase("completed")){
				str.append(" and date(TCSI.trainingCampSchedule.toDate) < :fromDate  ");
			}
			else if(searchType !=null && searchType.equalsIgnoreCase("cancelled")){
				str.append(" and TCSI.trainingCampSchedule.status ='Cancelled' ");
			}
		}
		else
		{
			isDatesNull = true;
			if(searchType !=null && searchType.equalsIgnoreCase("notStarted")){
				str.append(" and date(TCSI.trainingCampSchedule.fromDate) >:todayDate ");
			}
			else if(searchType !=null && searchType.equalsIgnoreCase("running")){
				str.append(" and :todayDate between date(TCSI.trainingCampSchedule.fromDate) and date(TCSI.trainingCampSchedule.toDate) ");
			}
			else if(searchType !=null && searchType.equalsIgnoreCase("completed")){
				str.append(" and date(TCSI.trainingCampSchedule.toDate) < :todayDate  ");
			}
			else if(searchType !=null && searchType.equalsIgnoreCase("cancelled")){
				str.append(" and TCSI.trainingCampSchedule.status ='Cancelled' ");
			}
		}
		
		if(callerIdsList !=null && callerIdsList.size()>0){
			str.append(" and  TCSIC.trainingCampUser.userId in (:callerIdsList) ");
		}
		if(dataType !=null && dataType.equalsIgnoreCase("dialedCalls")){
			str.append(" and TCSIC.campCallStatus.campCallStatusId is not null ");
		}
		else if(dataType !=null && dataType.equalsIgnoreCase("notDialed")){
			str.append( " and TCSIC.campCallStatus.campCallStatusId is null " );
		}
		
		str.append(" group by TCSI.trainingCampSchedule.trainingCamp.trainingCampId,TCSI.trainingCampSchedule.trainingCampScheduleId " +
				"  order by " +
				" TCSI.trainingCampSchedule.trainingCamp.trainingCampId ");
		
		Query query = getSession().createQuery(str.toString());
		if(isDatesNull){
			if(!searchType.equalsIgnoreCase("cancelled"))
				query.setDate("todayDate", todayDate);
		}
		else
		{
			if(searchType !=null && searchType.equalsIgnoreCase("notStarted")){
				query.setDate("toDate", toDate);
			}
			else if(searchType !=null && searchType.equalsIgnoreCase("running")){
				query.setDate("toDate", toDate);
			}
			else if(searchType !=null && searchType.equalsIgnoreCase("completed")){
				query.setDate("fromDate", fromDate);
			}
			/*else if(searchType !=null && searchType.equalsIgnoreCase("cancelled")){
				str.append(" and TCSI.trainingCampSchedule.status ='Cancelled' ");
			}*/
			
		}
		if(callerIdsList !=null && callerIdsList.size()>0){
			query.setParameterList("callerIdsList",callerIdsList);
		}
		
		if(fromDate !=null && toDate !=null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
	
	
	public List<Object[]> getCallerWiseAssignedCount(String searchType,List<Long> callerIdsList)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.trainingCampCallerId,model.trainingCampUser.firstName,count(model.trainingCampScheduleInviteeCallerId) " );
		queryStr.append(" from TrainingCampScheduleInviteeCaller model left join model.campCallStatus campCallStatus ");
		queryStr.append(" where ");
		if(searchType != null && searchType.equalsIgnoreCase("schedule"))
		{
			queryStr.append(" model.callPurposeId = 1 ");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("batch"))
		{
			queryStr.append(" model.callPurposeId = 2  ");
		}
		if(callerIdsList != null && callerIdsList.size()>0)
		{
			queryStr.append(" and model.trainingCampCallerId in (:callerIdsList) ");
		}
		
		
		queryStr.append(" group by model.trainingCampCallerId " +
				" order by  model.trainingCampCallerId  ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("callerIdsList", callerIdsList);
		return query.list();
	}
	
	
	public List<Object[]> getCallStatusWiseCountDetailsForCallers(String searchType,List<Long> callerIdsList)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.trainingCampCallerId, campCallStatus.campCallStatusId,campCallStatus.status, count(model.trainingCampScheduleInviteeCallerId)" +
				"  ,model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId" );
		queryStr.append(" from TrainingCampScheduleInviteeCaller model left join model.campCallStatus campCallStatus ");
		queryStr.append(" where  ");
		if(searchType != null && searchType.equalsIgnoreCase("schedule"))
		{
			queryStr.append(" model.callPurposeId = 1 ");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("batch"))
		{
			queryStr.append(" model.callPurposeId = 2  ");
		}
		if(callerIdsList != null && callerIdsList.size()>0)
		{
			queryStr.append(" and model.trainingCampCallerId in (:callerIdsList) ");
		}
	
		queryStr.append(" group by model.trainingCampCallerId, model.campCallStatus.campCallStatusId " +
				" order by  model.trainingCampCallerId , campCallStatus.status ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("callerIdsList", callerIdsList);
		return query.list();
	}
	
}
