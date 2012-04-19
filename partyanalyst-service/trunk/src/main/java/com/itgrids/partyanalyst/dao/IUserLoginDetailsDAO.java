package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.UserLoginDetails;

public interface IUserLoginDetailsDAO extends GenericDao<UserLoginDetails, Long>{
	
	public UserLoginDetails getBySessionId(String sessionId);
	
	public List<Object[]> getSessionIdsAndLogoutTimeInWithinDates(Date fromDate,Date toDate);
	
	public List<Object[]> getSessionIdsAndLogoutTimeOfTodaysUsers(Date today);
	
	public List<Object> getLandingPageAndExitPageForAUser(String sessionId);
	
}
