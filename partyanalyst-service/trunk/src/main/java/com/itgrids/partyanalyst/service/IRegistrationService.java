package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;

public interface IRegistrationService {

	 public String saveRegistration(RegistrationVO regData);

	 public EntitlementVO getAllRegisterdUsers();
	 
	 public RegistrationVO getDetailsOfUserByUserId(Long registrationId);
	 
}
