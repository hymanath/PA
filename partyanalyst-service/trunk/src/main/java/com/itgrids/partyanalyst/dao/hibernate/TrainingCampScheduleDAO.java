package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampScheduleDAO;
import com.itgrids.partyanalyst.model.TrainingCampSchedule;

public class TrainingCampScheduleDAO extends GenericDaoHibernate<TrainingCampSchedule, Long> implements ITrainingCampScheduleDAO{

	public TrainingCampScheduleDAO() {
		super(TrainingCampSchedule.class);
	}
	public List<Object[]> getSchedules(Long campId)
	{
		Query query = getSession().createQuery("select distinct model.trainingCampScheduleId,model.trainingCampScheduleCode from TrainingCampSchedule model" +
				" where model.trainingCampId =:campId ");
		query.setParameter("campId", campId);
		return query.list();
	}
	
	public List<Object[]> getCampsForProgram(Long programId)
	{
		Query query = getSession().createQuery("select distinct model.trainingCamp.trainingCampId,model.trainingCamp.campName from TrainingCampSchedule model" +
				" where  model.trainingCampProgram.trainingCampProgramId =:programId");
		query.setParameter("programId", programId);
		return query.list();
	}

	public List<Object[]> getProgrammesListByCampsList(List<Long> campsList)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select distinct model.trainingCampProgram.trainingCampProgramId, model.trainingCampProgram.programName from TrainingCampSchedule model where " +
				" model.status ='Not Started' ");
		if(campsList != null && campsList.size()>0)
			queryStr.append("  and model.trainingCamp.trainingCampId in (:campsList)");
		queryStr.append(" order by model.trainingCampProgram.trainingCampProgramId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(campsList != null && campsList.size()>0)
			query.setParameterList("campsList", campsList);
		return query.list();
	}
	
	public List<Object[]> getScheduledDetailsByProgrammes(List<Long> programmesList)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select distinct model.trainingCampScheduleId, model.trainingCampScheduleCode from TrainingCampSchedule model where " +
				" model.status ='Not Started' ");
		if(programmesList != null && programmesList.size()>0)
			queryStr.append("  and  model.trainingCampProgram.trainingCampProgramId in (:programmesList)");
		queryStr.append(" order by model.trainingCampScheduleId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(programmesList != null && programmesList.size()>0)
			query.setParameterList("programmesList", programmesList);
		return query.list();
	}
	public List<Long> getAllUpcomingTrainingCampSchedules(Date fromDate,Date toDate,String type){
		
		StringBuilder scheduleStr = new StringBuilder();
		
		scheduleStr.append("select distinct model.trainingCampScheduleId from TrainingCampSchedule model where ");
				
		if(fromDate !=null && toDate !=null){
			scheduleStr.append(" (date(model.fromDate)>=:fromDate and date(model.toDate)<=:toDate) ");
		}
		
		if(type !=null){
			scheduleStr.append(" and model.status = '"+type+"' ");
		}
		
		Query query = getSession().createQuery(scheduleStr.toString());
		
		if(fromDate !=null && toDate !=null){
			query.setParameter("fromDate",fromDate);
			query.setParameter("toDate", toDate);
		}
		
		return query.list();
	}
	
}
