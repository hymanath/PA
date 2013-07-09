package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserLoginSession;

public interface IUserLoginSessionDAO extends GenericDao<UserLoginSession,Long>{
	public List<String> getSessionIds(Long userId,String sessionId);
	
	public Integer deleteSessionsFromUserLoginSession(String sessionId);

}
