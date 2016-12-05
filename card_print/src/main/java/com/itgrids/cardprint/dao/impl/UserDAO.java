package com.itgrids.cardprint.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.cardprint.dao.IUserDAO;
import com.itgrids.cardprint.model.User;

public class UserDAO extends GenericDaoHibernate<User, Long> implements IUserDAO{

	public UserDAO()
	{
		super(User.class);
	}
	
}
