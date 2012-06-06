package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserRolesDAO;
import com.itgrids.partyanalyst.model.UserRoles;

public class UserRolesDAO extends GenericDaoHibernate<UserRoles,Long> implements IUserRolesDAO{

	public UserRolesDAO()
	{
		super(UserRoles.class);
	}
}
