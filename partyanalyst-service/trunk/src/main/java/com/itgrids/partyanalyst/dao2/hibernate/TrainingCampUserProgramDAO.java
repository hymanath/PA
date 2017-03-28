package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampUserProgramDAO;
import com.itgrids.partyanalyst.model.TrainingCampUserProgram;

public class TrainingCampUserProgramDAO extends GenericDaoHibernate<TrainingCampUserProgram, Long> implements ITrainingCampUserProgramDAO{

	public TrainingCampUserProgramDAO() {
		super(TrainingCampUserProgram.class);
	}
	
	public List<Object[]> getProgramsByUser(Long userId){
		
		Query query=getSession().createQuery(" select distinct model.trainingCampProgramId,model.trainingCampProgram.programName " +
		" from TrainingCampUserProgram model where model.userId=:userId " +
		" order by model.trainingCampProgram.programName asc");
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<Object[]> getCampsByProgramAndUser(Long campProgramId,Long userId){
		Query query=getSession().createQuery(" select distinct model.trainingCampId,model.trainingCamp.campName" +
		"  from TrainingCampUserProgram model where model.userId=:userId and model.trainingCampProgramId=:trainingCampProgramId " +
		"  order by model.trainingCamp.campName asc ");
		query.setParameter("trainingCampProgramId", campProgramId);
		query.setParameter("userId", userId);
		return query.list();
	}
}
