package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserTracking;


public interface IUserTrackingDAO extends GenericDao<UserTracking, Long>{
	public Object getUniqueVisitorsBetweenDates(Date fromDate, Date toDate, String userType);
	
	public List<Object> getLoginTimeBetweenDates(Date fromDate, Date toDate, String userType);
	
	public List<Object> getLogoutTimeBetweenDates(Date fromDate, Date toDate, String userType);
	
	public Object getNoOfPagesAccessedBetweenDates(Date fromDate, Date toDate, String userType);
}
