package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.ComponentWiseAchievementConfigurationTemp;

public interface IComponentWiseAchievementConfigurationTempDAO extends GenericDao<ComponentWiseAchievementConfigurationTemp, Long>{
	
	public int deleteRecrdsFrmTable();
	public List<Object[]> getComponentWiseMandalAchievementPercentage(String componentName);
}
