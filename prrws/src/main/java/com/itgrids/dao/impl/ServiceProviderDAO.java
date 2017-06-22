package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IServiceProviderDAO;
import com.itgrids.model.ServiceProvider;

@Repository
public class ServiceProviderDAO extends GenericDaoHibernate<ServiceProvider,Long> implements IServiceProviderDAO{

	public ServiceProviderDAO()
	{
		super(ServiceProvider.class);
	}
}
