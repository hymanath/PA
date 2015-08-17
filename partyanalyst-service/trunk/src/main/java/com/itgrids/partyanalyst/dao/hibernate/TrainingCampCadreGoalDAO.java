package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITrainingCampCadreGoalDAO;
import com.itgrids.partyanalyst.model.TrainingCampCadreGoal;

public class TrainingCampCadreGoalDAO extends GenericDaoHibernate<TrainingCampCadreGoal, Long> implements ITrainingCampCadreGoalDAO{

	public TrainingCampCadreGoalDAO() {
		super(TrainingCampCadreGoal.class);
		
	}

}
