package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.dao.IServiceModuleDAO;
import com.itgrids.model.ServiceModule;

public class ServiceModuleDAO extends GenericDaoHibernate<ServiceModule,Long> implements IServiceModuleDAO{

	public ServiceModuleDAO()
	{
		super(ServiceModule.class);
	}
}
