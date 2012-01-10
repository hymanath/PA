package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICustomPageDAO;
import com.itgrids.partyanalyst.model.CustomPage;

public class CustomPageDAO extends GenericDaoHibernate<CustomPage, Long> implements ICustomPageDAO{
	
	public CustomPageDAO()
	{
		super(CustomPage.class);
	}

}
