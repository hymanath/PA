package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampBatchDAO;
import com.itgrids.partyanalyst.model.TrainingCampBatch;

public class TrainingCampBatchDAO extends GenericDaoHibernate<TrainingCampBatch, Long> implements ITrainingCampBatchDAO{

	public TrainingCampBatchDAO() {
		super(TrainingCampBatch.class);
		// TODO Auto-generated constructor stub
	}
	public List<Object[]> getBatchesForSchedule(Long scheduleId)
	{
		Query query = getSession().createQuery("select distinct model.trainingCampBatchId,model.trainingCampBatchName from TrainingCampBatch model where model.trainingCampSchedule.trainingCampScheduleId =:scheduleId");
		query.setParameter("scheduleId", scheduleId);
		return query.list();
	}
	public List<Object[]> getAllBatchesForSchedules(List<Long> scheduleIds){
		
		Query query = getSession().createQuery("select distinct model.trainingCampBatchId,model.trainingCampBatchName from TrainingCampBatch model where model.trainingCampSchedule.trainingCampScheduleId in (:scheduleIds) ");
		query.setParameterList("scheduleId", scheduleIds);
		return query.list();
		
	}
	/*public List<Long> getUpcomingBatchConfirmation(Date fromDate,Date toDate,String status){
		
		StringBuilder str=new StringBuilder();
		
		str.append(" select distinct model.trainingCampBatchId from TrainingCampBatch model " +
				" where ");
		
		if(fromDate !=null && toDate !=null){
			str.append(" (date(model.fromDate)>=:fromDate and date(model.toDate)<=:toDate) ");
		}
		
		if(status !=null){
			str.append( " and model.trainingCampSchedule.status = '"+status+"'  ");//Not Started
		}
		str.append(" and model.batchStatus.status = 'Confirmed'  ");
		
		Query query = getSession().createQuery(str.toString());
		
		return query.list();
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTrainingCampBatchesOfSchedule(Long trainingCampScheduleId)
	{
		Query query = getSession().createQuery("SELECT model.trainingCampBatchId,model.trainingCampBatchName,model.trainingCampBatchCode,model.fromDate,model.toDate from TrainingCampBatch model " +
				" where model.trainingCampSchedule.trainingCampScheduleId = :trainingCampScheduleId");
		query.setParameter("trainingCampScheduleId",trainingCampScheduleId);
		return query.list();
	}
	
	public List<Object[]> getCompletedBatchIds(Date startDate,Date endDate,String type,Long stateId){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select distinct model.trainingCampBatchId,model.trainingCampBatchName, " +// 0 - batchId, 1 - batchName
				" model.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampSchedule.trainingCamp.campName, " +// 2 - campId,3 - campName
				" model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,model.trainingCampSchedule.trainingCampProgram.programName, " +// 4 - progId,5 - progName
				" model.trainingCampSchedule.trainingCampScheduleId,model.trainingCampSchedule.trainingCampScheduleCode," +//6-scheduleId,7-scheduleCode
				" date(model.fromDate),date(model.toDate), " +//8-trainingBatchfromdate,9-todate
				" date(model.trainingCampSchedule.fromDate),date(model.trainingCampSchedule.toDate) ");//10-schedulefromdate,11-todate
		sb.append(" from TrainingCampBatch model,TrainingCampDistrict model1 ");
		sb.append(" where model.trainingCampSchedule.trainingCampId=model1.trainingCampId ");
		
		if(stateId==1l){
			sb.append(" and model1.districtId between 11 and 23 ");
		}else if(stateId==2l){
			sb.append(" and model1.districtId  between 1 and 10  ");
		}
		
		if(type.equalsIgnoreCase("completed")){
			sb.append(" and date(model.toDate) between (:startDate) and (:endDate) ");
		}
		else if(type.equalsIgnoreCase("running")){
			sb.append(" and date(model.fromDate) between (:startDate) and (:endDate) and  date(model.toDate) > (:endDate) ");
		}
		else if(type.equalsIgnoreCase("upcoming")){
			sb.append(" and date(model.fromDate)>:endDate ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		
		if(type.equalsIgnoreCase("completed")){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		else if(type.equalsIgnoreCase("running")){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		else if(type.equalsIgnoreCase("upcoming")){
			query.setParameter("endDate", endDate);
		}
		
		return query.list();
	} 
}
