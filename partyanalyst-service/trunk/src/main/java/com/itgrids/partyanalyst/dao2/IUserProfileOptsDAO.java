package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserProfileOpts;

public interface IUserProfileOptsDAO extends GenericDao<UserProfileOpts, Long>{

	public Integer removeOptsOfExistingUser(Long userId);
	
}
