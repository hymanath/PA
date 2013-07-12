package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.AccessRestrictedSession;

public interface IAccessRestrictedSessionDAO extends GenericDao<AccessRestrictedSession,Long>{
	
	public String verifySessionIdAvailability(String sessionId);
	
	public Integer deleteSessionsFromAccessRestrictedSession(String sessionId);

}
