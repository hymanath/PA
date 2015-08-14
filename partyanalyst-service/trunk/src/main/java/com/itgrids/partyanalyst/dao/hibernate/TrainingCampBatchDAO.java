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
}
