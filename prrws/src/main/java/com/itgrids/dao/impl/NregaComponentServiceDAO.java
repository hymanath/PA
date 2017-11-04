package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.INregaComponentServiceDAO;
import com.itgrids.model.NregaComponentService;

@Repository
public class NregaComponentServiceDAO extends GenericDaoHibernate<NregaComponentService, Long> implements INregaComponentServiceDAO{

	public NregaComponentServiceDAO() {
		super(NregaComponentService.class);
	}

	public List<Object[]> getComponentUrlsByComponentIds(List<Long> componentIds,String serviceType){
		Query query = getSession().createQuery("select distinct model.nregaComponent.nregaComponentId,"
											+ " model.nregaComponent.componentName,"
											+ " model.webService.url,"
											+ " model.webService.webserviceId"
											+ " from NregaComponentService model"
											+ " where model.nregaComponent.nregaComponentId in (:componentIds)"
											+ " and model.serviceType = :serviceType");
		query.setParameterList("componentIds", componentIds);
		query.setParameter("serviceType", serviceType);
		return query.list();
	}
}
