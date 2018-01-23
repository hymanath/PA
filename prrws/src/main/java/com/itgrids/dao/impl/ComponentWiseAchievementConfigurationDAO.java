package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IComponentWiseAchievementConfigurationDAO;
import com.itgrids.model.ComponentWiseAchievementConfiguration;

@Repository
public class ComponentWiseAchievementConfigurationDAO extends GenericDaoHibernate<ComponentWiseAchievementConfiguration, Long> implements IComponentWiseAchievementConfigurationDAO{

	public ComponentWiseAchievementConfigurationDAO() {
		super(ComponentWiseAchievementConfiguration.class);
	}

}
