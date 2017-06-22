package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IWebserviceDAO;
import com.itgrids.model.Webservice;

@Repository
public class WebserviceDAO extends GenericDaoHibernate<Webservice,Long> implements IWebserviceDAO{

	public WebserviceDAO()
	{
		super(Webservice.class);
	}
}
