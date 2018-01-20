package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IComponentTargetConfigurationTempDAO;
import com.itgrids.model.ComponentTargetConfigurationTemp;
@Repository
public class ComponentTargetConfigurationTempDAO extends GenericDaoHibernate<ComponentTargetConfigurationTemp, Long> implements IComponentTargetConfigurationTempDAO{

	public ComponentTargetConfigurationTempDAO() {
		super(ComponentTargetConfigurationTemp.class);
		
	}
	
	public List<Object[]> getPanchayatTargetDetails(String type){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.scopeValue");
		if(type != null && type.equalsIgnoreCase("TOT"))
			sb.append(",model.totalExpenditure");
		else if(type != null && type.equalsIgnoreCase("WAGE"))
			sb.append(",model.wage");
		else if(type != null && type.equalsIgnoreCase("MAT"))
			sb.append(",model.material");
		sb.append(" from ComponentTargetConfigurationTemp model"
				+ " where model.isDeleted = 'N'"
				+ " and model.nregaComponentId = 1");
		Query query = getSession().createQuery(sb.toString());
		return query.list();
	}

	public int updateoldData(){
		Query query = getSession().createQuery("update ComponentTargetConfigurationTemp model set model.isDeleted = 'Y' where model.isDeleted = 'N'");
		return query.executeUpdate();
	}
}
