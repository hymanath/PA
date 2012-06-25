package com.itgrids.partyanalyst.dao;


import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AnanymousUser;


public interface IAnanymousUserDAO extends GenericDao<AnanymousUser, Long>{

	public List<AnanymousUser> checkAnonymousUserLogin(String anonymousUserId,String password);
	
	public List<AnanymousUser> checkForUserNameAvailabiity(String userName);
	
	public List<Object> getAllUsersInSelectedLocations(List<Long> locationIds,String locationType,Long retrivalCount,Long startIndex, String nameString);
	
	public Long getAllUsersCountInSelectedLocations(List<Long> locationIds,String locationType, String nameString);
	
	public List<AnanymousUser> getDetailsForUsers(List<Long> userIds);
	
	public List getAnanymousUserLocationDetailsByIds(List<Long> userIds);
	
	public List getConnectedUsersCount(Long locationId,String locationType);
	
	public Integer saveUserProfileImageNameToDB(Long userId, String imageName);
	
	public List getUserProfileImageNameByUserId(Long userId);
	
	public List getUserDetails(String userName);
	
	public Integer saveUserNameTOEmail(Long userId,String userName);
	
	public List<AnanymousUser> checkForUserNameAvailabiityForEmail(String userName); 
	
	public List<AnanymousUser>  getPassword(String password);
	
	public Integer changeUserPassword(String password,Long userId,String status,Date date);
	
	public List<AnanymousUser> checkUserPassword(String password,Long userId);
	
	public List<AnanymousUser> getUserByUserName(String userName);
	
	public List<AnanymousUser> changeUserNameAsEmail(String email);
	
	public List<Object[]> getUserEmail(Long userId);
	
	public AnanymousUser getAnanymousUserByUserId(Long userId);
	
	public List<Object[]> getPasswordNotUpdatdUsersList();
	
	public List<Object> getNotConnectedUsersInSelectedLocations(Long userId,List<Long> locationIds,String locationType,List<Long> otherUsers,Long retrivalCount,Long startIndex,String nameString);
	
	public Long getNotConnectedUsersCountForAUserInAFilterView(Long userId, List<Long> locationIds,String locationType, String nameStr, List<Long> otherUsers);
	
	public List<Object[]> getAllUsersMobile();
	
	public List<Object> getAllMobilenosAsUnique();

}
