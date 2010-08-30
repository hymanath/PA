package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserAccessInfoDAO;
import com.itgrids.partyanalyst.model.UserAccessInfo;

public class UserAccessInfoDAOHibernateTest extends BaseDaoTestCase{

	private IUserAccessInfoDAO userAccessInfoDAO;

	public IUserAccessInfoDAO getUserAccessInfoDAO() {
		return userAccessInfoDAO;
	}

	public void setUserAccessInfoDAO(IUserAccessInfoDAO userAccessInfoDAO) {
		this.userAccessInfoDAO = userAccessInfoDAO;
	}
	
	public void testGetAll(){
		List<UserAccessInfo> list = userAccessInfoDAO.getAll();
		assertEquals(list.size(), 0);
	}
	
}
