package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.model.User;

public class UserDAO extends GenericDaoHibernate<User,Long> implements IUserDAO{

	public UserDAO()
	{
		super(User.class);
	}
}
