package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityTabUser;

public interface IActivityTabUserDAO extends GenericDao<ActivityTabUser, Long>{
	
	public List<Long> getUserByUserNameAndPassword(String userName , String password);

}
