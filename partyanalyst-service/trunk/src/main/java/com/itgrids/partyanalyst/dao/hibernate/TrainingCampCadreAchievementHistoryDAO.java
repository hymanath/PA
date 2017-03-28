package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITrainingCampCadreAchievementHistoryDAO;
import com.itgrids.partyanalyst.model.TrainingCampCadreAchievementHistory;

public class TrainingCampCadreAchievementHistoryDAO extends GenericDaoHibernate<TrainingCampCadreAchievementHistory,Long> implements ITrainingCampCadreAchievementHistoryDAO {
	
	public TrainingCampCadreAchievementHistoryDAO() {
		super(TrainingCampCadreAchievementHistory.class);
	}

}
