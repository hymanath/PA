package com.itgrids.dao.impl;

import java.util.List;

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

	public List<Object[]> getComponentWiseMandalAchievementPercentage(String componentName){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.scopeValue,model.achievedPercentage"
				+ " from ComponentWiseAchievementConfigurationTemp model"
				+ " where model.isDeleted = 'N' and model.nregaComponent.componentName = :componentName");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("componentName", componentName);
		return query.list();
	}
}
