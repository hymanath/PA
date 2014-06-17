package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserTrackingVO;

public interface ILoginService {
	
	public RegistrationVO checkForValidUser(String userName,String password);
	
	public RegistrationVO checkForValidNormalUser(String userName,String password);
	
	public List<String> getDefaultEntitlements(String defaultEntitlementsGroup);
	
	public Boolean checkForRegionToViewReport(RegistrationVO registrationVO, final String regionType, Long regionId);
	
	public String saveUserSessionDetails(UserTrackingVO userTrackingVO);
	
	public ResultStatus changePasswordOfANewUser(final String crntpassword,final String newpassword,final String userName);
	
	//public String changePasswordOfANewUser(String crntpassword,String newpassword,String userName);
	
	public String checkUserCurrentPassword(String crntpassword,String userName);
	
	public RegistrationVO getUserBasicDetails(Long userId);
	
	public SelectOptionVO getUserNameAndPWDByUserId(Long userId);
	
	public Boolean checkForUserAccessIPAddress(Long userId, String ipAddress);
	
	public void sendMailToAdminGroup(final RegistrationVO registrationVO ,final String ipAddress);
	
	public List<String> deActivateAllOtherSimultaneousSessions(Long userId);
	

	
}
