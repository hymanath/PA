package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

public class UserConnectedtoDAOHibernateTest extends BaseDaoTestCase {

	private UserConnectedtoDAO userConnectedtoDAO;

	public UserConnectedtoDAO getUserConnectedtoDAO() {
		return userConnectedtoDAO;
	}

	public void setUserConnectedtoDAO(UserConnectedtoDAO userConnectedtoDAO) {
		this.userConnectedtoDAO = userConnectedtoDAO;
	}
	
	public void test(){
		userConnectedtoDAO.getAll();
	}
}
