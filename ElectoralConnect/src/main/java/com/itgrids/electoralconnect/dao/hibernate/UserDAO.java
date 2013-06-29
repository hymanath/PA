package com.itgrids.electoralconnect.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.electoralconnect.dao.IUserDAO;
import com.itgrids.electoralconnect.dao.IUserLoginDAO;
import com.itgrids.electoralconnect.model.User;
import com.itgrids.electoralconnect.model.UserLogin;


public class UserDAO extends GenericDaoHibernate<User,Long> implements IUserDAO{
	public UserDAO()
	{
		super(User.class);
	}
	
}
