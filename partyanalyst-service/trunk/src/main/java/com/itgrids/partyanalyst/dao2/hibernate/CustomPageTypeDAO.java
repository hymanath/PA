package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.ICustomPageTypeDAO;
import com.itgrids.partyanalyst.model.CustomPageType;

public class CustomPageTypeDAO extends GenericDaoHibernate<CustomPageType,Long> implements ICustomPageTypeDAO{

	public CustomPageTypeDAO()
	{
		super(CustomPageType.class);
	}
}
