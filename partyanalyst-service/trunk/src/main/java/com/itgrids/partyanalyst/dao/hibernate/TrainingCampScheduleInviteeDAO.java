package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampScheduleInviteeDAO;
import com.itgrids.partyanalyst.model.TrainingCampScheduleInvitee;

public class TrainingCampScheduleInviteeDAO extends GenericDaoHibernate<TrainingCampScheduleInvitee, Long> implements ITrainingCampScheduleInviteeDAO{

	public TrainingCampScheduleInviteeDAO() {
		super(TrainingCampScheduleInvitee.class);
	}

	public List<Object[]> getCampusWiseBatchWiseMembersDetails(String membersType, String searchType, Date startDate, Date endDate)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select TCSI.trainingCampSchedule.trainingCamp.campName,TCSI.trainingCampSchedule.trainingCampScheduleCode ");
		queryStr.append(" ,TCSI.scheduleInviteeStatus.scheduleInviteeStatusId,TCSI.scheduleInviteeStatus.status ,count(distinct TCSI.tdpCadreId) ");
		
		queryStr.append(" from TrainingCampScheduleInvitee TCSI,TrainingCampBatch TCB where TCSI.trainingCampSchedule.trainingCampId = TCB.trainingCampBatchId ");
		
		if(startDate != null && endDate != null)
		{
			queryStr.append(" and (date(TCSI.trainingCampSchedule.fromDate) >=:startDate and date(TCSI.trainingCampSchedule.fromDate) <=:endDate) ");
		}
		if(searchType != null && searchType.equalsIgnoreCase("notStarted"))
		{
			queryStr.append(" and TCSI.trainingCampSchedule.status ='Not Started' ");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("running"))
		{
			queryStr.append(" and TCSI.trainingCampSchedule.status ='Progress' ");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("completed"))
		{
			queryStr.append(" and TCSI.trainingCampSchedule.status ='Completed' ");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("cancelled"))
		{
			queryStr.append(" and TCSI.trainingCampSchedule.status ='Cancelled' ");
		}
		queryStr.append(" group by TCSI.trainingCampSchedule.trainingCamp.trainingCampId,TCSI.trainingCampSchedule.trainingCampScheduleCode,TCSI.scheduleInviteeStatus.scheduleInviteeStatusId order by " +
				" TCSI.trainingCampSchedule.trainingCamp.trainingCampId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(startDate != null && endDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	
	public List<Long> getInvitedCandidatesListByScheduleIdAndCount(Long scheduleId,List<Long> alreadyInvitedMemberIdsList,int membersCount)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct TCSI.trainingCampScheduleInviteeId from TrainingCampScheduleInvitee TCSI where TCSI.scheduleInviteeStatusId =1  ");
		if(alreadyInvitedMemberIdsList != null && alreadyInvitedMemberIdsList.size()>0)
			queryStr.append(" and TCSI.trainingCampScheduleInviteeId not in (:alreadyInvitedMemberIdsList) ");
		queryStr.append("order by TCSI.trainingCampScheduleInviteeId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(alreadyInvitedMemberIdsList != null && alreadyInvitedMemberIdsList.size()>0)
			query.setParameterList("alreadyInvitedMemberIdsList", alreadyInvitedMemberIdsList);
		query.setFirstResult(0);
		query.setMaxResults(membersCount);
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
				" and model.scheduleInviteeStatus.status like '%"+status+"%' ");	
		
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
	
	public List<Object[]> getBatchWiseProgramWiseInterestedMembersDetails(String membersType, String searchType, Date startDate, Date endDate)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select TCSI.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, TCSI.trainingCampSchedule.trainingCampProgram.programName, ");
		queryStr.append(" TCSI.trainingCampSchedule.trainingCamp.trainingCampId, TCSI.trainingCampSchedule.trainingCamp.campName, date(TCB.fromDate), ");
		queryStr.append(" date(TCB.toDate), count(distinct TCSI.trainingCampScheduleInviteeId) ");
		
		queryStr.append(" from TrainingCampScheduleInvitee TCSI, TrainingCampBatch TCB  where TCSI.trainingCampSchedule.trainingCampScheduleId = TCB.trainingCampBatchId ");
		queryStr.append(" and TCSI.scheduleInviteeStatus.status like '%"+membersType+"%' ");
		
		if(startDate != null && endDate != null)
		{
			queryStr.append(" and (date(TCB.fromDate) >=:startDate and date(TCB.toDate) <=:endDate) ");
		}
		/*if(membersType != null && membersType.equalsIgnoreCase("interested"))
		{
			queryStr.append(" and TCSI.scheduleInviteeStatus.status = 'Interested' ");
		}*/
		if(searchType != null && searchType.equalsIgnoreCase("notStarted"))
		{
			queryStr.append(" and TCSI.trainingCampSchedule.status = 'Not Started' ");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("running"))
		{
			queryStr.append(" and TCSI.trainingCampSchedule.status = 'Progress' ");
		}
		else if(searchType != null && searchType.equalsIgnoreCase("completed"))
		{
			queryStr.append(" and TCSI.trainingCampSchedule.status = 'Completed' ");
		}
		
		//queryStr.append(" group by TCB.trainingCampBatchId, TCSI.trainingCampSchedule.trainingCamp.trainingCampId, TCSI.trainingCampSchedule.trainingCampProgram.trainingCampProgramId ");
		queryStr.append(" group by TCSI.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, TCSI.trainingCampSchedule.trainingCamp.trainingCampId, TCB.trainingCampBatchId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(startDate != null && endDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
		return query.list();
	}
	
}
