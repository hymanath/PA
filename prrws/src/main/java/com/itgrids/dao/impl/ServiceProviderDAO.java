package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.dao.IServiceProviderDAO;
import com.itgrids.model.ServiceProvider;

public class ServiceProviderDAO extends GenericDaoHibernate<ServiceProvider,Long> implements IServiceProviderDAO{

	public ServiceProviderDAO()
	{
		super(ServiceProvider.class);
	}
}
