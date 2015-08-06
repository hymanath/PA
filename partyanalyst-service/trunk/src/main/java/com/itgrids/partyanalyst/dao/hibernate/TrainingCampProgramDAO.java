package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;


import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITrainingCampProgramDAO;
import com.itgrids.partyanalyst.model.TrainingCampProgram;

public class TrainingCampProgramDAO extends GenericDaoHibernate<TrainingCampProgram, Long> implements ITrainingCampProgramDAO{

	public TrainingCampProgramDAO() {
		super(TrainingCampProgram.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Object[]> getPrograms()
	{
		return getHibernateTemplate().find("select model.trainingCampProgramId,model.programName TrainingCampProgram model");
	}

}
