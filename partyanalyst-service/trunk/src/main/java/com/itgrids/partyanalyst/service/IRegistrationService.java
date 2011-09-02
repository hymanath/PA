package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IRegistrationService {

	 public String saveRegistration(RegistrationVO values,String userType);

	 public EntitlementVO getAllRegisterdUsers();
	 
	 public RegistrationVO getDetailsOfUserByUserId(Long registrationId);
	 
	 public ResultStatus checkForUserNameAvalilability(String userName);
	 public Integer updateRegisteredUserDetailsToUserNameToEmail(Long userId,String userName);
	 
}
