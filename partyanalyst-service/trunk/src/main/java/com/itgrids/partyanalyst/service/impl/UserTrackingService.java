package com.itgrids.partyanalyst.service.impl;

import org.apache.log4j.Logger;
import com.itgrids.partyanalyst.dao.IUserTrackingDAO;
import com.itgrids.partyanalyst.dto.UserTrackingVO;
import com.itgrids.partyanalyst.model.UserTracking;
import com.itgrids.partyanalyst.service.IUserTrackingService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class UserTrackingService implements IUserTrackingService{
	private IUserTrackingDAO userTrackingDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	public static Logger log = Logger.getLogger(UserTrackingService.class);
	
	public IUserTrackingDAO getUserTrackingDAO() {
		return userTrackingDAO;
	}

	public void setUserTrackingDAO(IUserTrackingDAO userTrackingDAO) {
		this.userTrackingDAO = userTrackingDAO;
	}

	public String saveUserTrackingDetails(UserTrackingVO userTrackingVO)
	{
		try
		{
		UserTracking userTracking = new UserTracking();
		userTracking.setIpAddress(userTrackingVO.getRemoteAddress());
		userTracking.setUrlName(userTrackingVO.getRequestURI());
		userTracking.setUserType(userTrackingVO.getUserType());
		userTracking.setUserId(userTrackingVO.getUserId());
		userTracking.setTime(dateUtilService.getCurrentDateAndTime());
		userTrackingDAO.save(userTracking);
		}
		catch(Exception e)
		{
			log.error("Error occured in saving User Tracking Details - "+e);
		}
		return "Success";
		}

	
	
	
	
}
