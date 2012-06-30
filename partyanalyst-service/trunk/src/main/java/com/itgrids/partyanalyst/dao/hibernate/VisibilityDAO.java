package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVisibilityDAO;
import com.itgrids.partyanalyst.model.Visibility;

public class VisibilityDAO extends GenericDaoHibernate<Visibility,Long> implements IVisibilityDAO{

	public VisibilityDAO()
	{
		super(Visibility.class);
	}
}
