package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.dao.IWebserviceDAO;
import com.itgrids.model.Webservice;

public class WebserviceDAO extends GenericDaoHibernate<Webservice,Long> implements IWebserviceDAO{

	public WebserviceDAO()
	{
		super(Webservice.class);
	}
}
