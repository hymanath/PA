package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IAnanymousUserService {

	public Boolean saveAnonymousUserDetails(RegistrationVO userDetails);
	
	public RegistrationVO checkAnonymousUserLogin(String anonymousUserId,String password);
	
	public ResultStatus checkForUserNameAvalilability(String userName);
	
	public DataTransferVO getAllRegisteredAnonymousUserBasedOnLocation(List<Long> locationIds,String locationType,String retrivalCount);
}
