package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dao.columns.enums.RegistrationColumnNames;
import com.itgrids.partyanalyst.model.AnanymousUser;
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
	
	public List<Registration> findByUserRegistrationId(Long registrationId);
	
	public List<Registration> getAllRegisteredUsers();
	
	public List<Registration> checkForUserNameAvailabiity(String userName);
	
	public Integer saveUserNameTOEmail(Long userId,String userName);
   
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSubusersByParentUserId(Long parentUserId);

}
