package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISpecialPageDAO;
import com.itgrids.partyanalyst.model.SpecialPage;

public class SpecialPageDAO extends GenericDaoHibernate<SpecialPage,Long> implements ISpecialPageDAO{
	
	public SpecialPageDAO()
	{
		super(SpecialPage.class);
	}

}
