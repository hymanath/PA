package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserGroupPrivilegesDAO;
import com.itgrids.partyanalyst.model.UserGroupPrivileges;

public class UserGroupPrivilegesDAOHibernateTest extends BaseDaoTestCase {

	private IUserGroupPrivilegesDAO userGroupPrivilegesDAO;

	
	public IUserGroupPrivilegesDAO getUserGroupPrivileges() {
		return userGroupPrivilegesDAO;
	}
   
    
	public void setUserGroupPrivileges(IUserGroupPrivilegesDAO userGroupPrivilegesDAO) {
		this.userGroupPrivilegesDAO = userGroupPrivilegesDAO;
	}
   
	public void testFindByUserGroupPrivilegeId()
	{
		List<UserGroupPrivileges> result = userGroupPrivilegesDAO.findByUserGroupPrivilegeId(401L);
		assertEquals(2,result.size());
	}
}
