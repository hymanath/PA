package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITrainingCampCadreAchievementDAO;
import com.itgrids.partyanalyst.model.TrainingCampCadreAchievement;

public class TrainingCampCadreAchievementDAO extends GenericDaoHibernate<TrainingCampCadreAchievement, Long> implements ITrainingCampCadreAchievementDAO{

	public TrainingCampCadreAchievementDAO() {
		super(TrainingCampCadreAchievement.class);
		
	}

}
