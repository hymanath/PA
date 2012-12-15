package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFavoriteLinkPageDAO;

public class FavoriteLinkPageDAOHibernateTest  extends BaseDaoTestCase{
	
	private IFavoriteLinkPageDAO favoriteLinkPageDAO;

	public void setFavoriteLinkPageDAO(IFavoriteLinkPageDAO favoriteLinkPageDAO) {
		this.favoriteLinkPageDAO = favoriteLinkPageDAO;
	}

}
