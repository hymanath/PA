package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITrainingCampDAO;
import com.itgrids.partyanalyst.model.TrainingCamp;

public class TrainingCampDAO extends GenericDaoHibernate<TrainingCamp, Long> implements ITrainingCampDAO{

	public TrainingCampDAO() {
		super(TrainingCamp.class);
		// TODO Auto-generated constructor stub
	}

}
