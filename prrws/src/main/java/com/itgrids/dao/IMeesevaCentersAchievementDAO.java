package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.MeesevaCentersAchievement;

public interface IMeesevaCentersAchievementDAO extends GenericDao<MeesevaCentersAchievement, Long> {

	public List<Object[]> getMeesevaTargetAchieveCounts();
}
