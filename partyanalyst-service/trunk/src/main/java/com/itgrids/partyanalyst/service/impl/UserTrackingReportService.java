package com.itgrids.partyanalyst.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IUserLoginDetailsDAO;
import com.itgrids.partyanalyst.model.UserLoginDetails;
import com.itgrids.partyanalyst.service.IUserTrackingReportService;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserTrackingReportService implements IUserTrackingReportService{
	private static final Logger log = Logger.getLogger(UserTrackingReportService.class);
	
	private IUserLoginDetailsDAO userLoginDetailsDAO;
	private TransactionTemplate transactionTemplate;

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IUserLoginDetailsDAO getUserLoginDetailsDAO() {
		return userLoginDetailsDAO;
	}

	public void setUserLoginDetailsDAO(IUserLoginDetailsDAO userLoginDetailsDAO) {
		this.userLoginDetailsDAO = userLoginDetailsDAO;
	}

	public String saveUserLogOutDetails(Date fromDate, Date toDate, Boolean isToday)
	{
		try{
			List<Object[]> list = null;
			if(isToday)
				list = userLoginDetailsDAO.getSessionIdsAndLogoutTimeOfTodaysUsers(fromDate);
			else
				list = userLoginDetailsDAO.getSessionIdsAndLogoutTimeInWithinDates(fromDate,toDate);
			
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					final UserLoginDetails userLoginDetails = userLoginDetailsDAO.getBySessionId(params[0].toString());
					final Date logoutTime = (Date)params[1];
					if(userLoginDetails != null)
					{
						transactionTemplate.execute(new TransactionCallbackWithoutResult() {
							public void doInTransactionWithoutResult(TransactionStatus status) {
								userLoginDetails.setLogoutTime(logoutTime);
								userLoginDetailsDAO.save(userLoginDetails);
							}});
					}
				}
			}
			return IConstants.SUCCESS;
			
		}catch(Exception e){
			log.debug("Entered into saveUserLogOutDetails() exception is"+e);
			return null;
		}
	}
	
}
