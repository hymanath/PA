package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.RegistrationVO;

public interface ILoginService {
	
	public RegistrationVO checkForValidUser(String userName,String password);
	
	public RegistrationVO checkForValidNormalUser(String userName,String password);
	
	public List<String> getDefaultEntitlements(String defaultEntitlementsGroup);
	
	public Boolean checkForRegionToViewReport(RegistrationVO registrationVO, final String regionType, Long regionId);
}
