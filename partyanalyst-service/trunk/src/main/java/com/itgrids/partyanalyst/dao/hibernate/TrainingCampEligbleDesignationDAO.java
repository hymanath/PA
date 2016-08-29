package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITrainingCampEligbleDesignationDAO;
import com.itgrids.partyanalyst.model.TrainingCampEligbleDesignation;


public class TrainingCampEligbleDesignationDAO extends GenericDaoHibernate<TrainingCampEligbleDesignation, Long> implements
		ITrainingCampEligbleDesignationDAO {

	public TrainingCampEligbleDesignationDAO() {
		super(TrainingCampEligbleDesignation.class);
	}
}
