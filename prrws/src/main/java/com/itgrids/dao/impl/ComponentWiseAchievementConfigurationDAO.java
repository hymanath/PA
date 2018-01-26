package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IComponentWiseAchievementConfigurationDAO;
import com.itgrids.model.ComponentWiseAchievementConfiguration;

@Repository
public class ComponentWiseAchievementConfigurationDAO extends GenericDaoHibernate<ComponentWiseAchievementConfiguration, Long> implements IComponentWiseAchievementConfigurationDAO{

	public ComponentWiseAchievementConfigurationDAO() {
		super(ComponentWiseAchievementConfiguration.class);
	}

	public List<Object[]> getComponentWiseMandalAchievementPercentage(String componentName,String locationType,String locationIdStr){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.scopeValue,model.achievedPercentage"
				+ " from ComponentWiseAchievementConfiguration model"
				+ " where model.isDeleted = 'N' and model.nregaComponent.componentName = :componentName");
		if(locationType != null && locationType.equalsIgnoreCase("district") && locationIdStr != null)
			sb.append(" and model.locationAddress.district.prDistrict.districtCode = :locationIdStr");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("componentName", componentName);
		if(locationType != null && locationType.equalsIgnoreCase("district") && locationIdStr != null)
			query.setParameter("locationIdStr", locationIdStr);
		return query.list();
	}
}
