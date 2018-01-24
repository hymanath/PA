package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IComponentWiseAchievementConfigurationTempDAO;
import com.itgrids.model.ComponentWiseAchievementConfigurationTemp;

@Repository
public class ComponentWiseAchievementConfigurationTempDAO extends GenericDaoHibernate<ComponentWiseAchievementConfigurationTemp, Long> implements IComponentWiseAchievementConfigurationTempDAO{

	public ComponentWiseAchievementConfigurationTempDAO() {
		super(ComponentWiseAchievementConfigurationTemp.class);
	}
	
	public int deleteRecrdsFrmTable(){
		Query query = getSession().createQuery("delete from ComponentWiseAchievementConfigurationTemp");
		
		return query.executeUpdate();
	}

}
