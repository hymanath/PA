package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserFavoriteLinksDAO;

public class UserFavoriteLinksDAOHibernateTest  extends BaseDaoTestCase{
	

	private IUserFavoriteLinksDAO userFavoriteLinksDAO;
	

	public void setUserFavoriteLinksDAO(IUserFavoriteLinksDAO userFavoriteLinksDAO) {
		this.userFavoriteLinksDAO = userFavoriteLinksDAO;
	}
}
