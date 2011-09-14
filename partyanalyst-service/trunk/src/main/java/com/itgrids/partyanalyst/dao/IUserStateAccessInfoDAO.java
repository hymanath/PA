package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserStateAccessInfo;

public interface IUserStateAccessInfoDAO extends GenericDao<UserStateAccessInfo, Long>{
	
	public List findByUser(Long userId);
	
	public Integer deleteAllStateAccess(Long userId);
}
