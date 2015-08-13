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
	
	public List<Object[]> getCallerWiseAssignedCalls(List<Long> userIds,Date startDate,Date endDate,String type){
		
		StringBuilder str=new StringBuilder();
		
		str.append(" select model.trainingCampUser.user.userId,count(model.trainingCampScheduleInviteeCallerId),model.trainingCampUser.user.lastName from  TrainingCampScheduleInviteeCaller model " +
				" where  ");
		
		if(startDate !=null && endDate !=null){
			str.append(" (date(model.updatedTime)>=:startDate and date(model.updatedTime)<=:endDate) ");
		}
		if(userIds !=null && userIds.size()>0){
			str.append(" and model.trainingCampUser.userId in (:userIds) ");
		}
		
		if(type !=null){
			if(type.equalsIgnoreCase("completedCount")){
				str.append(" and model.callStatusId is not null ");
				str.append(" and model.callStatusId =1 ");
			}
			else if(type.equalsIgnoreCase("pendingCount")){
				str.append(" and (model.callStatusId is null or model.callStatusId !=1) ");
			}
			else if(type.equalsIgnoreCase("dialedCalls")){
				str.append(" and model.callStatusId is not null ");
			}
		}
		
		str.append(" group by  model.trainingCampUser.userId order by model.trainingCampUser.userId ");
		
		Query query=getSession().createQuery(str.toString());
		
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
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
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		else if(startDate !=null){
			query.setParameter("startDate", startDate);
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
	
	public List<Object[]> getCallStatusContsOfInvitees(List<Long> userIds,Date startDate,Date endDate){
		
		StringBuilder str=new StringBuilder();
		
		str.append(" select model.trainingCampUser.user.userId,model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId,model.trainingCampScheduleInvitee.scheduleInviteeStatus.status,count(model.trainingCampScheduleInvitee.trainingCampScheduleInviteeId),model.trainingCampUser.user.lastName " +
				" from  TrainingCampScheduleInviteeCaller model " +
				" where model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId is not null ");
		
		if(startDate !=null && endDate !=null){
			str.append(" and (date(model.updatedTime)>=:startDate and date(model.updatedTime)<=:endDate) ");
		}
		if(userIds !=null && userIds.size()>0){
			str.append(" and model.trainingCampUser.userId in (:userIds) ");
		}
		str.append(" group by model.trainingCampUser.userId,model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId ");
		
		Query query=getSession().createQuery(str.toString());
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
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
				" where model.trainingCampCallerId = :callerId and model.callPurposeId = :callPurposeId and model.trainingCampScheduleInvitee.trainingCampBatch.batchStatus.batchStatusId in(1,2) " +
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
				" where model.trainingCampCallerId = :callerId and model.callPurposeId= :callPurposeId and model.trainingCampScheduleInvitee.trainingCampBatch.batchStatus.batchStatusId in(1,2) " +
				" group by model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId,model.trainingCampScheduleInvitee.trainingCampBatch.trainingCampBatchId,model.campCallStatus.campCallStatusId");
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
				" and model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId =:scheduleId" +
				" and model.trainingCampScheduleInvitee.trainingCampBatch.batchStatus.batchStatusId in(1,2) ");
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
			scheduleAndConfirmationCallsQuery.setParameter("startDate", startDate);
			scheduleAndConfirmationCallsQuery.setParameter("endDate", endDate);
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
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
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
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
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
	
	public List<Long> getInterestedAndInvitedMembersListForBatchConfirmation(Long callerId,Long scheduleId,String callPurposeStr,String memberTypeStr)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.trainingCampScheduleInvitee.trainingCampScheduleInviteeId  from TrainingCampScheduleInviteeCaller model where " +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId =:scheduleId and model.campCallPurpose.purpose like '%"+callPurposeStr+"%'  and " +
				"  model.trainingCampScheduleInvitee.scheduleInviteeStatus.status like '%"+memberTypeStr+"%' ");
		if(callerId != null && callerId.longValue() >0L)
			queryStr.append(" and model.trainingCampCallerId =:callerId ");
		
		queryStr.append(" order by  model.trainingCampScheduleInvitee.trainingCampScheduleInviteeId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("scheduleId", scheduleId);
		if(callerId != null && callerId.longValue() >0L)
			query.setParameter("callerId", callerId);
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
				" where model.trainingCampCallerId = :callerId and model.callPurposeId = :callPurposeId and model.trainingCampScheduleInvitee.trainingCampBatch.trainingCampBatchId in(1,2) " +
				" and date(model.trainingCampScheduleInvitee.callBackTime) = :date and model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId in(6,7) " +
				" group by model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId,model.campCallStatus.campCallStatusId");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("callerId", callerId);
		query.setParameter("callPurposeId", callPurposeId);
		query.setDate("date", date);
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
				" where model.trainingCampCallerId = :callerId and model.callPurposeId= :callPurposeId and model.trainingCampScheduleInvitee.trainingCampBatch.trainingCampBatchId in(1,2) " +
				" and date(model.trainingCampScheduleInvitee.callBackTime) = :date and model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId in(6,7) " +
				" group by model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId,model.trainingCampScheduleInvitee.trainingCampBatch.trainingCampBatchId,model.campCallStatus.campCallStatusId");
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

	
}
