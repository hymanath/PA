package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IServiceModuleDAO;
import com.itgrids.model.ServiceModule;

@Repository
public class ServiceModuleDAO extends GenericDaoHibernate<ServiceModule,Long> implements IServiceModuleDAO{

	public ServiceModuleDAO()
	{
		super(ServiceModule.class);
	}
}
