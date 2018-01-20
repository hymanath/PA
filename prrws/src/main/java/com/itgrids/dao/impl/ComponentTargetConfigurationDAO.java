package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IComponentTargetConfigurationDAO;
import com.itgrids.model.ComponentTargetConfiguration;

@Repository
public class ComponentTargetConfigurationDAO extends GenericDaoHibernate<ComponentTargetConfiguration, Long> implements IComponentTargetConfigurationDAO{

	public ComponentTargetConfigurationDAO() {
		super(ComponentTargetConfiguration.class);
		
	}

	public List<Object[]> getRangeWiseVillagesCounts(){
		Query query = getSession().createQuery("select model.componentTargetId,model.componentTarget.target,count(model.componentTargetConfigurationId)"
				+ " from ComponentTargetConfiguration model"
				+ " where model.isDeleted = 'N' and model.nregaComponentId = 1"
				+ " group by model.componentTargetId order by model.componentTargetId");
		return query.list();
	}
	
	public List<Object[]> getRangeWiseVillageDetails(String type){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.componentTargetId,model.componentTarget.target,model.componentTargetConfigurationId,model.scopeValue,");
		if(type != null && type.equalsIgnoreCase("TOT"))
			sb.append(" model.totalExpenditure");
		else if(type != null && type.equalsIgnoreCase("WAGE"))
			sb.append(" model.wage");
		else if(type != null && type.equalsIgnoreCase("MAT"))
			sb.append(" model.material");
		sb.append(" from ComponentTargetConfiguration model"
				+ " where model.isDeleted = 'N' and model.nregaComponentId = 1");
				//+ " order by model.componentTargetId");
		Query query = getSession().createQuery(sb.toString());
		return query.list();
	}
}
