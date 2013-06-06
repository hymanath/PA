package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dao.columns.enums.RegistrationColumnNames;
import com.itgrids.partyanalyst.model.User;

public interface IUserDAO extends GenericDao<User,Long>{/*
	
	public List<User> checkForUserNameAvailabiity(String userName);
	
	public List<User> checkForUserNameAvailabiityForEmail(String userName);
	
	public List<User> checkUserPassword(String password,Long userId); 
	
	public Integer changeUserPassword(String password,Long userId,String status,Date date);
	
	public List<Object> getUserIdByUserName(String userName);
	
	public User findByUserNameAndPassword(String userName, String password);
	
	public String getUserProfileImageNameByUserId(Long userId);
	
	public List<Object[]> getUserLocationDetailsByUserIds(List<Long> userIds);
	
	public User getUserByUserId(Long userId);
	
	public List getConnectedUsersCount(Long locationId,String locationType);
	
	public Integer saveUserProfileImageNameToDB(Long userId, String imageName);
	
	public List<Object[]> allRegisteredUsersData();
	
	public List<Object[]> getUserEmail(Long userId);

	public List<User> getDetailsForUsers(List<Long> userIds);
	
	public List<Object> getAllUsersInSelectedLocations(List<Long> locationIds,String locationType,Long retrivalCount,Long startIndex,String nameString);
	
	public List<Object> getNotConnectedUsersInSelectedLocations(Long userId,List<Long> locationIds,String locationType,List<Long> otherUsers,Long retrivalCount,Long startIndex,String nameString);
	
	public Long getAllUsersCountInSelectedLocations(List<Long> locationIds,String locationType, String nameStr);

	public Long getNotConnectedUsersCountForAUserInAFilterView(Long userId, List<Long> locationIds,String locationType, String nameStr, List<Long> otherUsers);
	
	public List<User> getUserByUserName(String userName);
	
	public List<User> changeUserNameAsEmail(String email);
	
	public Integer saveUserNameTOEmail(Long userId,String email);
	
	public List getUserDetails(String userName);
	
	public List<User> checkAnonymousUserLogin(String userName, String password);
	
	public List<User> getPassword(String password);
	
	public List<Object[]> getPasswordNotUpdatdUsersList();
	
	public List<Object[]> getAllUsersMobile();
	
	public List<Object> getAllMobilenosAsUnique();
	
	public List<User> findByUserRegistrationId(Long registrationId);
	
	public List<User> getAllRegisteredUsers(); 
	
	public List<Object[]> getSubusersByParentUserId(Long parentUserId);
	
	public List<User> findByProperty(RegistrationColumnNames propertyName, Object value);
	
	public List<User> findByFirstName(Object firstName);
	
	public List<User> findByUserName(Object userName);
	
	public List<User> findByMiddleName(Object middleName);
	
	public List<User> findByLastName(Object lastName);
	
	public List<User> findByCountry(Object country);
	
	public List<User> findByEmail(Object email);
	
	public List<String> findUserName();
	
	public List<String> findPasswords();
	
	public List getAnanymousUserLocationDetailsByIds(List<Long> userIds);
	
	public List<Object[]> getUserEmailByUserId(Long userId);
	
	public List<Object[]> findByUserId(Long userId);
	
	public List<Object[]> getUserBasicDetailsForProfile(List<Long> userId);
	
	public List<Long> getTotalUsersCount();
	
	public List<Object[]> getUserNameAndPwdByUserId(Long userId);
	
	
	public List<String> getEncryptedKeyByUserName(String userName);
	
	public List<User> checkUsernameAndEncryptedPasswordForUser(String userName, String encryptedPassword);
	public List<User> updateAllUsersPasswords();

*/
	public Long checkForUserNameAndPasswordAvaliablity(String userName,String password);
}
