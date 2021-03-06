package com.itgrids.electoralconnect.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.electoralconnect.model.User;

public interface IUserDAO  extends GenericDao<User, Long> {
	
	public List<Object[]> checkForValidUser(String userName,String Passward);
	
	public int updatePassword(String password,Long userId);
	
	public User getUserDetailsByUserName(String username);
	
	public String getPasswordByUser(Long userId);
}
