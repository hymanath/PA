package com.itgrids.partyanalyst.dao;


import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AnanymousUser;


public interface IAnanymousUserDAO extends GenericDao<AnanymousUser, Long>{

	public List<AnanymousUser> checkAnonymousUserLogin(String anonymousUserId,String password);
	
	public List<AnanymousUser> checkForUserNameAvailabiity(String userName);
	
	public List<Object> getAllUsersInSelectedLocations(List<Long> locationIds,String locationType,Long retrivalCount,Long startIndex, String nameString);
	
	public String getAllUsersCountInSelectedLocations(List<Long> locationIds,String locationType);
	
	public List<AnanymousUser> getDetailsForUsers(List<Long> userIds);
	
	public List getAnanymousUserLocationDetailsByIds(List<Long> userIds);
	
	public List getConnectedUsersCount(Long locationId,String locationType);
	
	public Integer saveUserProfileImageNameToDB(Long userId, String imageName);
	
	public List getUserProfileImageNameByUserId(Long userId);
	
	public List getUserDetails(String userName);
	
	public Integer saveUserNameTOEmail(Long userId,String userName);
	
}
