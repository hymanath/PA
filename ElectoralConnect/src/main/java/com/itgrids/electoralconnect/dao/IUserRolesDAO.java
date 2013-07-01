package com.itgrids.electoralconnect.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.electoralconnect.model.UserRoles;

public interface IUserRolesDAO  extends GenericDao<UserRoles, Long> {
	
	public List<Object[]> checkForValidUser(String userName,String password);
}
