package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserGroupPrivilegesDAO;
import com.itgrids.partyanalyst.model.UserGroupPrivileges;

public class UserGroupPrivilegesDAO extends GenericDaoHibernate<UserGroupPrivileges, Long>
		implements IUserGroupPrivilegesDAO {

	
	public UserGroupPrivilegesDAO()
	{
		super(UserGroupPrivileges.class);
		System.out.println("hello");
	}
	@SuppressWarnings("unchecked")
	public List<UserGroupPrivileges> findByUserGroupPrivilegeId(
			Long userGroupPrivilegeId) {
		
		List<UserGroupPrivileges>userGroupPrivileges=getHibernateTemplate().find("from UserGroupPrivileges model where model.userGroupPrivilegeId= ?",userGroupPrivilegeId);
		return userGroupPrivileges;
	}

	@Override
	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserGroupPrivileges get(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserGroupPrivileges> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserGroupPrivileges save(UserGroupPrivileges arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
