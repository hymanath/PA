package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.model.UserRoles;

public interface IUserRolesDAO extends GenericDao<UserRoles,Long>{

	public List<Object[]> getUserRoles(Long userId);
	
	public List<Object[]> getAllFreeuser();
	
	public List<String> getUserRolesOfAUser(Long userId);
	
	public List<Object[]> getAllFreeusertoSendSms();
	public List<Object[]> getAllUsersMobile(String roleType);
	
	public List<Object> getAllMobilenosAsUnique();
	//public List<Object[]> getUsersForSendingEmails(Long selectedState,Long selectedDistrict, Long selectedConstituency, Long userType,Long locationScope,String freeUserType);
	public List<Object[]> getUsersForSendingEmail(RegistrationVO regiVO,String order,String sortBy,int startIndex,int maxIndex,boolean iscount);
	
	public List<Object[]> getAllRestrictedUsers();
	
	public List<Object[]> getAllUsers();
	
	public List<Object[]> getAllUsersByRoleType(String roleType);
	
	public Long checkForUserPublicStreeming(Long userId);
	
}