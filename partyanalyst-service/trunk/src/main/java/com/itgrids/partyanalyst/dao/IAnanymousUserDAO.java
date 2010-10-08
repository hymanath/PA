package com.itgrids.partyanalyst.dao;


import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AnanymousUser;


public interface IAnanymousUserDAO extends GenericDao<AnanymousUser, Long>{

	public List<AnanymousUser> checkAnonymousUserLogin(String anonymousUserId,String password);
	
	public List<AnanymousUser> checkForUserNameAvailabiity(String userName);
	
	public List<Object> getAllUsersInSelectedLocations(List<Long> locationIds,String locationType);
}
