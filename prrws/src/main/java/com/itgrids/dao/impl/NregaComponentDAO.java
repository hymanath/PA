package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.INregaComponentDAO;
import com.itgrids.model.NregaComponent;

@Repository
public class NregaComponentDAO extends GenericDaoHibernate<NregaComponent, Long> implements INregaComponentDAO{

	public NregaComponentDAO() {
		super(NregaComponent.class);
	}
	
	public List<Object[]> getComponentByConvergType(Long convergenceTypeId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.nregaComponentId,"
				+ " model.componentName"
				+ " from NregaComponent model");
		if(convergenceTypeId != null && convergenceTypeId.longValue() > 0l){
			sb.append(" where model.convergenceType.convergenceTypeId = :convergenceTypeId");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(convergenceTypeId != null && convergenceTypeId.longValue() > 0l)
			query.setParameter("convergenceTypeId", convergenceTypeId);
		
		return query.list();
		
	}
	
	public Long getComponentIdByComponentName(String componentName){
		Query query = getSession().createQuery("select model.nregaComponentId from NregaComponent model where model.componentName = :componentName");
		query.setParameter("componentName", componentName);
		return (Long) query.uniqueResult();
	}
}
