package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.User;

public interface IRegistrationService {/*

	 public String saveRegistration(RegistrationVO values,String userType);

	 public EntitlementVO getAllRegisterdUsers();
	 
	 public RegistrationVO getDetailsOfUserByUserId(Long userId);
	 
	 public ResultStatus checkForUserNameAvalilability(String userName);
	 
	 public ResultStatus checkForUserNameAvailabilityForFreashUser(String userName);
	 
	 public Integer updateRegisteredUserDetailsToUserNameToEmail(Long userId,String userName);
	 
	 public List<String> getRoleTypeForUser();
	 
	 public ResultStatus saveDataInToUserRolesTable(User registration,RegistrationVO values);
	 
	 //public ResultStatus saveDataInToUserRolesTable(User user, RegistrationVO values);
	 
*/
	public Boolean saveDataIntoUser(final RegistrationVO regVo,final String userType);
	
	public ResultStatus checkForUserNameAvailabilityForFreashUser(String userName);
	
}
