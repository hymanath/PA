package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserGroupRelationDAO;
import com.itgrids.partyanalyst.model.UserGroupRelation;

public class UserGroupRelationDAOHibernateTest extends BaseDaoTestCase{

	private IUserGroupRelationDAO userGroupRelationDAO;

	public IUserGroupRelationDAO getUserGroupRelationDAO() {
		return userGroupRelationDAO;
	}

	public void setUserGroupRelationDAO(IUserGroupRelationDAO userGroupRelationDAO) {
		this.userGroupRelationDAO = userGroupRelationDAO;
	}
	
	public void testGetAll(){
		List<UserGroupRelation> list = userGroupRelationDAO.getAll();
		assertEquals(true, list.size() >= 0);
	}
	
	public void testUserIdPresence(){
		List  list = userGroupRelationDAO.checkTheRelationBetweenUserAndGroup(2l);
		assertEquals(true, list.size() >= 0);
	}
}
