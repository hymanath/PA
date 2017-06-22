package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.dao.IWebserviceCallDetailsDAO;
import com.itgrids.model.WebserviceCallDetails;

public class WebserviceCallDetailsDAO extends GenericDaoHibernate<WebserviceCallDetails,Long> implements IWebserviceCallDetailsDAO{

	public WebserviceCallDetailsDAO()
	{
		super(WebserviceCallDetails.class);
	}
}
