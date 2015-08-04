package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITrainingCampUserDAO;
import com.itgrids.partyanalyst.model.TrainingCampUser;

public class TrainingCampUserDAO extends GenericDaoHibernate<TrainingCampUser, Long> implements ITrainingCampUserDAO{

	public TrainingCampUserDAO() {
		super(TrainingCampUser.class);
		// TODO Auto-generated constructor stub
	}

}
