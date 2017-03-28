package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserLocationTracking;

public interface IUserLocationTrackingDAO extends GenericDao<UserLocationTracking,Long>{
	
	public List<UserLocationTracking> getUserLocationByUserIdAndUniqueId(
			Long userId, String uniqueId);
	
	 public List<UserLocationTracking> getUserTrackingDetails(Long userId,Date surveyDate);
	 
	 public List<Object[]> getLatestUserTrackingDetals(Date date);

	 public List<Object[]> getUserTrackingDetails(Long userId);
}
