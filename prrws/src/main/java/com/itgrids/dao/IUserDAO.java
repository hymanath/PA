package com.itgrids.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.User;

public interface IUserDAO extends GenericDao<User,Long>{
	
	public User loginAuthentication(String userName,String password);
	public Object[] getUrlForMatchedCredentials(String userName, String password);
	
}
