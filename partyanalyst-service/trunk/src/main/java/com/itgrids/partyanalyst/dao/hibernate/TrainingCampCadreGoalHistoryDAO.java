package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITrainingCampCadreGoalHistoryDAO;
import com.itgrids.partyanalyst.model.TrainingCampCadreGoalHistory;

public class TrainingCampCadreGoalHistoryDAO extends GenericDaoHibernate<TrainingCampCadreGoalHistory,Long> implements ITrainingCampCadreGoalHistoryDAO {
	
	public TrainingCampCadreGoalHistoryDAO() {
		super(TrainingCampCadreGoalHistory.class);
	}

}
