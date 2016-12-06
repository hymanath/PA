package com.itgrids.cardprint.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.cardprint.dao.IUserDAO;
import com.itgrids.cardprint.model.User;

public class UserDAO extends GenericDaoHibernate<User, Long> implements IUserDAO{

	public UserDAO()
	{
		super(User.class);
	}
	
	public User checkUserHashKeyExists(String userHashKey)
	{
		Query query = getSession().createQuery("SELECT model from User model where model.usernameHashKey = :userHashKey");
		query.setParameter("userHashKey", userHashKey);
		return (User) query.uniqueResult();
	}
}
