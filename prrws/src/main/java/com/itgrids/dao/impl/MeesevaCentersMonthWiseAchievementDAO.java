package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IMeesevaCentersMonthWiseAchievementDAO;
import com.itgrids.model.MeesevaCentersMonthWiseAchievement;

@Repository
public class MeesevaCentersMonthWiseAchievementDAO extends GenericDaoHibernate<MeesevaCentersMonthWiseAchievement, Long> implements IMeesevaCentersMonthWiseAchievementDAO{

	public MeesevaCentersMonthWiseAchievementDAO() {
		super(MeesevaCentersMonthWiseAchievement.class);
	}

}
