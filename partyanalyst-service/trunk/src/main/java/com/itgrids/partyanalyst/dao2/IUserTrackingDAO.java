package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserTracking;


public interface IUserTrackingDAO extends GenericDao<UserTracking, Long>{
	public Object getUniqueVisitorsBetweenDates(Date fromDate, Date toDate, String userType);
	
	public List<Object[]> getLoginLogoutTimeBetweenDates(Date fromDate, Date toDate, String userType);
	
	public Object getNoOfPagesAccessedBetweenDates(Date fromDate, Date toDate, String userType);
	
	public List<Object> getLandingPageBetweenDatesBySessionId(Date fromDate, Date toDate);

	public List<Object> getExitPageBetweenDatesBySessionId(Date fromDate, Date toDate);
	
	public List<Object[]> getHostNameAndNoOfPagesForAVisitor(Date fromDate , Date toDate);
	
	public List<Object[]> getHostNameAndNoOfPagesForAUser(Date fromDate , Date toDate , String userType);
	
	public Object getTotalSessionCountBetweenDates(Date fromDate, Date toDate);
	
	public List<Object> getPageFlowOfUserBetweenDates(Date fromDate, Date toDate, String userType, String sessionId);
	
	public Integer deleteSearchEngineAccessedURLsFromUserTracking(List<String> ipList,Date fromDate, Date toDate);
		
}
