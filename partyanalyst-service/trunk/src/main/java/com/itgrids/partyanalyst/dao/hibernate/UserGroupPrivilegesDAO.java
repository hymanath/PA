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
	}
	
	@SuppressWarnings("unchecked")
	public List<UserGroupPrivileges> findByUserGroupPrivilegeId(Long userGroupPrivilegeId)
	{
		return getHibernateTemplate().find("from UserGroupPrivileges model where model.userGroupPrivilegeId= ?",userGroupPrivilegeId);
	}

}
