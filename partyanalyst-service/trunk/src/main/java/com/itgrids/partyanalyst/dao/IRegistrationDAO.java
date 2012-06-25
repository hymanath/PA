package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dao.columns.enums.RegistrationColumnNames;
import com.itgrids.partyanalyst.model.Registration;


public interface IRegistrationDAO extends GenericDao<Registration, Long>{
	
	/**
	 * Find all Registration entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Registration property to query
	 * @param value
	 *            the property value to match
	 * @return List<Registration> found by query
	 */
	
	public List<Registration> findByProperty(RegistrationColumnNames propertyName, Object value);
	
	public List<Registration> findByFirstName(Object firstName);
	
	public List<Registration> findByUserName(Object firstName);
	
	public List<Registration> findByMiddleName(Object middleName);
	
	public List<Registration> findByLastName(Object lastName);
	
	public List<Registration> findByCountry(Object country);
	
	public List<Registration> findByEmail(Object email);
	
	public List<String> findUserName();
	
	public List<String> findPasswords();
	
	public List<Registration> findByUserNameAndPassword(String userName, String password);
	
	public List<Registration> findByUserRegistrationId(Long userId);
	
	public List<Registration> getAllRegisteredUsers();
	
	public List<Registration> checkForUserNameAvailabiity(String userName);
	
	public Integer saveUserNameTOEmail(Long userId,String userName);
   
	public List<Registration> checkForUserNameAvailabiityForEmail(String userName);
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSubusersByParentUserId(Long parentUserId);
	
	public Integer saveUserProfileImageNameToDB(Long userId, String imageName);
	
	public Integer changeUserPassword(String password,Long userId,String status,Date date);
	
	public List<Registration> checkUserPassword(String password,Long userId);
	
	public List<Object[]> getUserEmailByUserId(Long userId);

	public List<Object> getAllUsersInSelectedLocations(List<Long> locationIds,String locationType,Long retrivalCount,Long startIndex,String nameString);
	
	public Long getAllUsersCountInSelectedLocations(List<Long> locationIds,String locationType, String nameStr);
	
	public List<Registration> getDetailsForUsers(List<Long> userIds);
	
	public List<Object> getNotConnectedUsersInSelectedLocations(Long userId,List<Long> locationIds,String locationType,List<Long> otherUsers,Long retrivalCount,Long startIndex,String nameString) ;
	
	public Long getNotConnectedUsersCountForAUserInAFilterView(Long userId, List<Long> locationIds,String locationType, String nameStr, List<Long> otherUsers);
	
	public List<Object> getUserIdByUserName(String userName);
}
