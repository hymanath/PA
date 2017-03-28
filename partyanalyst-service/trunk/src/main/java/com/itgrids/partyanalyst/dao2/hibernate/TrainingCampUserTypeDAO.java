package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITrainingCampUserTypeDAO;
import com.itgrids.partyanalyst.model.TrainingCampUserType;

public class TrainingCampUserTypeDAO extends GenericDaoHibernate<TrainingCampUserType, Long> implements ITrainingCampUserTypeDAO{

	public TrainingCampUserTypeDAO() {
		super(TrainingCampUserType.class);
		// TODO Auto-generated constructor stub
	}

}
