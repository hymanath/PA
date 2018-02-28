package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IWebserviceCallTypeDAO;
import com.itgrids.model.WebserviceCallType;

@Repository
public class WebserviceCallTypeDAO extends GenericDaoHibernate<WebserviceCallType,Long> implements IWebserviceCallTypeDAO{
	
	public WebserviceCallTypeDAO()
	{
		super(WebserviceCallType.class);
	}
}
