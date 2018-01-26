package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.ComponentWiseAchievementConfiguration;

public interface IComponentWiseAchievementConfigurationDAO extends GenericDao<ComponentWiseAchievementConfiguration, Long>{

	public List<Object[]> getComponentWiseMandalAchievementPercentage(String componentName,String locationType,String locationIdStr);
}
