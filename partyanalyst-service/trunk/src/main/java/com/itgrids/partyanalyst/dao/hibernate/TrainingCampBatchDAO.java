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
		Query query = getSession().createQuery("select distinct model.trainingCampBatchId,model.trainingCampBatchName from TrainingCampBatch model where model.trainingCampSchedule.trainingCampScheduleId =:scheduleId and model.isCancelled = 'false' ");
		query.setParameter("scheduleId", scheduleId);
		return query.list();
	}
	public List<Object[]> getAllBatchesForSchedules(List<Long> scheduleIds){
		
		Query query = getSession().createQuery("select distinct model.trainingCampBatchId,model.trainingCampBatchName from TrainingCampBatch model where model.trainingCampSchedule.trainingCampScheduleId in (:scheduleIds) and model.isCancelled = 'false' ");
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
				" where model.trainingCampSchedule.trainingCampScheduleId = :trainingCampScheduleId and model.isCancelled = 'false' ");
		query.setParameter("trainingCampScheduleId",trainingCampScheduleId);
		return query.list();
	}
	
	public List<Object[]> getCompletedBatchIds(Date currDate,String type,List<Long> batchIds){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select distinct model.trainingCampBatchId,model.trainingCampBatchName, " +// 0 - batchId, 1 - batchName
				" model.trainingCampSchedule.trainingCamp.trainingCampId,model.trainingCampSchedule.trainingCamp.campName, " +// 2 - campId,3 - campName
				" model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId,model.trainingCampSchedule.trainingCampProgram.programName, " +// 4 - progId,5 - progName
				" model.trainingCampSchedule.trainingCampScheduleId,model.trainingCampSchedule.trainingCampScheduleCode," +//6-scheduleId,7-scheduleCode
				" date(model.fromDate),date(model.toDate), " +//8-trainingBatchfromdate,9-todate
				" date(model.trainingCampSchedule.fromDate),date(model.trainingCampSchedule.toDate) ");//10-schedulefromdate,11-todate
		sb.append(" from TrainingCampBatch model ");
		sb.append(" where model.trainingCampBatchId in (:batchIds) ");
		
		if(type.equalsIgnoreCase("completed")){
			sb.append(" and date(model.fromDate) < :currDate and date(model.toDate) < :currDate ");
		}
		else if(type.equalsIgnoreCase("running")){
			sb.append(" and date(model.fromDate) <= :currDate and  date(model.toDate) >= :currDate ");
		}
		else if(type.equalsIgnoreCase("upcoming")){
			sb.append(" and date(model.fromDate) > :currDate and date(model.toDate) > :currDate ");
		}
		sb.append(" and model.isCancelled = 'false' ");
		
		Query query = getSession().createQuery(sb.toString());
		
		
		query.setParameter("currDate", currDate);
		query.setParameterList("batchIds", batchIds);
		
		return query.list();
	}
	
	public Long getMaxNumbersForBacth(Long batchId)
	{
		Query query = getSession().createQuery("select model.maxMembers from TrainingCampBatch model where model.trainingCampBatchId = :batchId and model.isCancelled = 'false' ");
		query.setParameter("batchId", batchId);
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getCampDistrictsByBatchId(Long batchId){
		
		Query query=getSession().createQuery(" select model1.district.districtId,model1.district.districtName" +
		" from TrainingCampBatch model,TrainingCampDistrict model1" +
		" where model.trainingCampSchedule.trainingCamp.trainingCampId =model1.trainingCampId and model.trainingCampBatchId=:trainingCampBatchId and model.isCancelled = 'false' " +
		" order by model1.district.districtId asc");
		query.setParameter("trainingCampBatchId",batchId);
		return query.list();
	}
	public Object[] getBatchDates(Long batchId,Date fromDate,Date toDate){
		Query query=getSession().createQuery("select date(model.fromDate),date(model.toDate) from  TrainingCampBatch model where model.trainingCampBatchId =:trainingCampBatchId and date(model.fromDate) >= :fromDate and date(model.toDate) <= :toDate and model.isCancelled = 'false' ");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		query.setParameter("trainingCampBatchId",batchId);
		return (Object[])query.uniqueResult();
	}
	
	public List<Object[]> getCentersAndBatchCountByProgram(Long programId,Date fromDate,Date toDate){
		
		Query query=getSession().createQuery("" +
		" select   model.trainingCampSchedule.trainingCampId,model.trainingCampSchedule.trainingCamp.campName,count(distinct model.trainingCampBatchId) " +
		" from     TrainingCampBatch model " +
		" where    model.trainingCampSchedule.trainingCampProgramId=:trainingCampProgramId and date(model.fromDate) >= :fromDate and date(model.toDate) <= :toDate and model.isCancelled = 'false' " +
		" group by model.trainingCampSchedule.trainingCampId " +
		" order by model.trainingCampSchedule.trainingCamp.campName");
		query.setParameter("fromDate",fromDate);
		query.setParameter("toDate",toDate);
		query.setParameter("trainingCampProgramId",programId);
		return query.list();
	}
    public Object[] getBatchCountByCamp(Long programId,Long campId,Date fromDate,Date toDate){
		
		Query query=getSession().createQuery("" +
		" select model.trainingCampSchedule.trainingCampId,model.trainingCampSchedule.trainingCamp.campName,count(distinct model.trainingCampBatchId) " +
		" from   TrainingCampBatch model " +
		" where  model.trainingCampSchedule.trainingCampProgramId=:trainingCampProgramId and " +
		"        model.trainingCampSchedule.trainingCampId=:trainingCampId and date(model.fromDate) >= :fromDate and date(model.toDate) <= :toDate and model.isCancelled = 'false' ");
		query.setParameter("trainingCampProgramId",programId);
		query.setParameter("fromDate",fromDate);
		query.setParameter("toDate",toDate);
		query.setParameter("trainingCampId",campId);
		return (Object[])query.uniqueResult();
	}

	public List<Long> getBatchIds(Date startDate,Date endDate,Long stateId){
		StringBuilder sb = new StringBuilder();
		
		sb.append("select distinct model.trainingCampBatchId from TrainingCampBatch model,TrainingCampDistrict model1 " +
				" where date(model.fromDate)>=:startDate and date(model.toDate)<=:endDate and model.trainingCampSchedule.trainingCampId=model1.trainingCampId and model.isCancelled = 'false' ");
		
		if(stateId==1l){
			sb.append(" and model1.districtId between 11 and 23 ");
		}else if(stateId==2l){
			sb.append(" and model1.districtId  between 1 and 10  ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		
		return query.list();
	}
	public Object[] getBatchDatesWithOutDates(Long batchId){
		Query query=getSession().createQuery("select date(model.fromDate),date(model.toDate) from  TrainingCampBatch model where model.trainingCampBatchId =:trainingCampBatchId and model.isCancelled = 'false' ");
		
		query.setParameter("trainingCampBatchId",batchId);
		return (Object[])query.uniqueResult();
	}
	public List<Object[]> getBatchesInfoByProgramAndCamp(Long programId,Long campId){
		
		Query query = getSession().createQuery("select model.trainingCampBatchId,model.trainingCampBatchName " +
				" from TrainingCampBatch model " +
				" where model.trainingCampSchedule.trainingCampProgram.trainingCampProgramId =:programId" +
				" and  model.trainingCampSchedule.trainingCamp.trainingCampId =:campId and model.isCancelled = 'false' "); 
		
		query.setParameter("programId",programId);
		query.setParameter("campId",campId);
		return query.list();
	}
	
	public List<TrainingCampBatch> getAllRecordsByBatchId(Long batchId)
	{
		Query query = getSession().createQuery(" select model from TrainingCampBatch model where model.trainingCampBatchId > :batchId and model.isCancelled = 'false' order by model.trainingCampBatchId ");
		
		query.setParameter("batchId", batchId);
		
		return query.list();
	}
}
