package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IUserGallaryDAO;

public class UserGallaryDAOHibernateTest extends BaseDaoTestCase{
	
	private IUserGallaryDAO userGallaryDAO;

	public void setUserGallaryDAO(IUserGallaryDAO userGallaryDAO) {
		this.userGallaryDAO = userGallaryDAO;
	}
	
	public void test()
	{
		userGallaryDAO.getAll();
	}
}
