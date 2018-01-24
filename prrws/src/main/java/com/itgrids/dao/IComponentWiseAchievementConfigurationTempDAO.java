package com.itgrids.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.ComponentWiseAchievementConfigurationTemp;

public interface IComponentWiseAchievementConfigurationTempDAO extends GenericDao<ComponentWiseAchievementConfigurationTemp, Long>{
	public int deleteRecrdsFrmTable();

}
