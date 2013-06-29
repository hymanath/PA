package com.itgrids.electoralconnect.dao.hibernate;

import java.util.List;

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
	
	@SuppressWarnings("unchecked")
	public List<Object[]> checkForValidUser(String userName,String Passward)
	{
		Object[] parms = {userName , Passward};
		return getHibernateTemplate().find("select model.userProfile,model.userLogin from User model " +
				" where model.userLogin.userName = ? and model.userLogin.password = ? ", parms);
	}
}
