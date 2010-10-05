package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IAnanymousUserService {

	public Boolean saveAnonymousUserDetails(RegistrationVO userDetails);
	
	public RegistrationVO checkAnonymousUserLogin(String anonymousUserId,String password);
	
	public ResultStatus checkForUserNameAvalilability(String userName);
}
