package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserGroupsDAO;
import com.itgrids.partyanalyst.model.UserGroups;

public class UserGroupsDAOHibernateTest extends BaseDaoTestCase{

	private IUserGroupsDAO userGroupsDAO;

	public IUserGroupsDAO getUserGroupsDAO() {
		return userGroupsDAO;
	}

	public void setUserGroupsDAO(IUserGroupsDAO userGroupsDAO) {
		this.userGroupsDAO = userGroupsDAO;
	}
	
	public void testGetAll(){
		List<UserGroups> list = userGroupsDAO.getAll();
		assertEquals(true, list.size() >= 0);
	}
	
}
