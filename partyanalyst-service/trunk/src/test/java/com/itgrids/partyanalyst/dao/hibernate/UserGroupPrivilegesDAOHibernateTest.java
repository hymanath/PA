package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IUserGroupPrivilegesDAO;
import com.itgrids.partyanalyst.model.UserGroupPrivileges;

public class UserGroupPrivilegesDAOHibernateTest extends BaseDaoTestCase {

	private IUserGroupPrivilegesDAO userGroupPrivilegesDAO;

	
	public IUserGroupPrivilegesDAO getUserGroupPrivileges() {
		 System.out.println("66666666666666666666666666666666666");
		return userGroupPrivilegesDAO;
	}
   
    
	public void setUserGroupPrivileges(IUserGroupPrivilegesDAO userGroupPrivilegesDAO) {
		this.userGroupPrivilegesDAO = userGroupPrivilegesDAO;
	}
   
	public void testFindByUserGroupPrivilegeId()
	{
		System.out.println("wwwwwwwwwwwwwwwwww");
    List<UserGroupPrivileges>result=userGroupPrivilegesDAO.findByUserGroupPrivilegeId(401L);
    for(UserGroupPrivileges userPrivilage:result)
    {
    System.out.println("read write user " +userPrivilage.getReadRightUserId().toString());
    System.out.println("write write user" +userPrivilage.getWriteRightUserId().toString());
    System.out.println("personal user group" +userPrivilage.getPersonalUserGroup().toString());
    }
    assertEquals(2,result.size());
	}
}
