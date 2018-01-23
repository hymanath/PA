package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IComponentWiseAchievementConfigurationTempDAO;
import com.itgrids.model.ComponentWiseAchievementConfigurationTemp;

@Repository
public class ComponentWiseAchievementConfigurationTempDAO extends GenericDaoHibernate<ComponentWiseAchievementConfigurationTemp, Long> implements IComponentWiseAchievementConfigurationTempDAO{

	public ComponentWiseAchievementConfigurationTempDAO() {
		super(ComponentWiseAchievementConfigurationTemp.class);
	}

}
