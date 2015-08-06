package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampScheduleInviteeCallerDAO;
import com.itgrids.partyanalyst.model.TrainingCampScheduleInviteeCaller;

public class TrainingCampScheduleInviteeCallerDAO extends GenericDaoHibernate<TrainingCampScheduleInviteeCaller, Long> implements ITrainingCampScheduleInviteeCallerDAO{

	public TrainingCampScheduleInviteeCallerDAO() {
		super(TrainingCampScheduleInviteeCaller.class);
	}
	
	public List<Object[]> getCallerWiseAssignedCalls(List<Long> userIds,Date startDate,Date endDate,String type){
		
		StringBuilder str=new StringBuilder();
		
		str.append(" select model.trainingCampUser.userId,count(model.trainingCampScheduleInviteeCallerId) from  TrainingCampScheduleInviteeCaller model " +
				" where model.trainingCampCallerId in (:userIds)  ");
		
		if(startDate !=null && endDate !=null){
			str.append(" and (date(model.updatedTime)>=:startDate and date(model.updatedTime)<=:endDate) ");
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
				str.append(" and model.callStatusId is null ");
				str.append(" and model.callStatusId !=1 ");
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
		
		str.append(" select model.trainingCampUser.userId,model.trainingCampScheduleInvitee.scheduleInviteeStatus.scheduleInviteeStatusId,model.trainingCampScheduleInvitee.scheduleInviteeStatus.status,count(model.trainingCampScheduleInvitee.trainingCampScheduleInviteeId) " +
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
				" where model.trainingCampCallerId = :callerId and model.callPurposeId = :callPurposeId" +
				" group by model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId,model.campCallStatus.campCallStatusId");
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
				" where model.trainingCampCallerId = :callerId and model.callPurposeId = :callPurposeId" +
				" group by model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampProgram.trainingCampProgramId," +
				" model.trainingCampScheduleInvitee.trainingCampSchedule.trainingCampScheduleId,model.trainingCampScheduleInvitee.trainingCampBatch.trainingCampBatchId,model.campCallStatus.campCallStatusId");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("callerId", callerId);
		query.setParameter("callPurposeId", callPurposeId);
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
	

}
