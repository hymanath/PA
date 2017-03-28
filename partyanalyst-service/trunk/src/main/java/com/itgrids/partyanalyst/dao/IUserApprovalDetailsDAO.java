package com.itgrids.partyanalyst.dao;


import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.UserApprovalDetails;

public interface IUserApprovalDetailsDAO extends GenericDao<UserApprovalDetails, Long> {
	
	
	@SuppressWarnings("unchecked")
	//getNewApprovalsOnCurrentDate
	//public List findUserApprovalStatus(Date currentDate);
	//@SuppressWarnings("unchecked")
	// getNewApprovalsBetweenSelectedDates
	//public List findUserApprovalStatustodate(Date fromDate, Date toDate);

public List findUserApprovalStatusbetweendates(Date fromDate, Date toDate);
	
}