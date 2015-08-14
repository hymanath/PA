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

	public List<Object[]> getCampusWiseScheduleWiseMembersDetails(String searchType, Date startDate, Date endDate)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select TCSI.trainingCampSchedule.trainingCamp.campName," +
				"TCSI.trainingCampSchedule.trainingCampProgram.programName," +
				"TCSI.trainingCampSchedule.trainingCampScheduleCode ");
		queryStr.append(" ,TCSI.scheduleInviteeStatus.scheduleInviteeStatusId," +
				"TCSI.scheduleInviteeStatus.status ," +
				"count(distinct TCSI.tdpCadreId)," +
				"TCSI.trainingCampSchedule.trainingCampScheduleId ");
		
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
		queryStr.append(" group by TCSI.trainingCampSchedule.trainingCamp.trainingCampId,TCSI.trainingCampSchedule.trainingCampScheduleId," +
				" TCSI.scheduleInviteeStatus.scheduleInviteeStatusId order by " +
				" TCSI.trainingCampSchedule.trainingCamp.trainingCampId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(startDate != null && endDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
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
		queryStr.append(" and TCSIC.campCallPurpose.purpose='Confirmation' and TCSIC.trainingCampScheduleInvitee.scheduleInviteeStatus.status ='Interested' ");
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
	
	public List<Long> getInvitedCandidatesListByScheduleIdAndCount(Long scheduleId,List<Long> alreadyInvitedMemberIdsList,int membersCount)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct TCSI.trainingCampScheduleInviteeId from TrainingCampScheduleInvitee TCSI where TCSI.scheduleInviteeStatusId =1 and TCSI.trainingcampScheduleId =:scheduleId ");
		if(alreadyInvitedMemberIdsList != null && alreadyInvitedMemberIdsList.size()>0)
			queryStr.append(" and TCSI.trainingCampScheduleInviteeId not in (:alreadyInvitedMemberIdsList) ");
		queryStr.append("order by TCSI.trainingCampScheduleInviteeId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("scheduleId", scheduleId);
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
	
	public List<Object[]> getBatchWiseConformationDetails(String searchType, Date startDate, Date endDate)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select TCSI.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, TCSI.trainingCampSchedule.trainingCampProgram.programName, ");
		queryStr.append(" TCSI.trainingCampSchedule.trainingCamp.trainingCampId, TCSI.trainingCampSchedule.trainingCamp.campName, TCB.trainingCampBatchId, date(TCB.fromDate), ");
		queryStr.append(" date(TCB.toDate), count(distinct TCSI.trainingCampScheduleInviteeId), TCSI.scheduleInviteeStatus.scheduleInviteeStatusId, TCSI.scheduleInviteeStatus.status ");
		
		queryStr.append(" from TrainingCampScheduleInvitee TCSI, TrainingCampBatch TCB  where TCSI.trainingCampSchedule.trainingCampScheduleId = TCB.trainingCampBatchId ");
		
		if(startDate != null && endDate != null)
		{
			queryStr.append(" and (date(TCB.fromDate) >=:startDate and date(TCB.toDate) <=:endDate) ");
		}
		if(searchType != null && searchType.equalsIgnoreCase("planned"))
		{
			queryStr.append(" and TCSI.trainingCampSchedule.status = 'Not Started' ");
		}
		
		queryStr.append(" group by TCSI.trainingCampSchedule.trainingCampProgram.trainingCampProgramId, TCSI.trainingCampSchedule.trainingCamp.trainingCampId, TCB.trainingCampBatchId, TCSI.scheduleInviteeStatus.scheduleInviteeStatusId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(startDate != null && endDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
		return query.list();
	}
}
