package com.itgrids.electoralconnect.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.electoralconnect.dao.IUserLoginDAO;
import com.itgrids.electoralconnect.model.UserLogin;


public class UserLoginDAO extends GenericDaoHibernate<UserLogin,Long> implements IUserLoginDAO{
	public UserLoginDAO()
	{
		super(UserLogin.class);
	}
	
}
