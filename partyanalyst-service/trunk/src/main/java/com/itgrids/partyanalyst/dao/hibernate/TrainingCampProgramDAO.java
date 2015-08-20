package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;


import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITrainingCampProgramDAO;
import com.itgrids.partyanalyst.model.TrainingCampProgram;

public class TrainingCampProgramDAO extends GenericDaoHibernate<TrainingCampProgram, Long> implements ITrainingCampProgramDAO{

	public TrainingCampProgramDAO() {
		super(TrainingCampProgram.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Object[]> getPrograms()
	{
	Query query = getSession().createQuery("select distinct model.trainingCampProgramId,model.programName from TrainingCampProgram model order by model.programName asc");
	return query.list();
	}

}
