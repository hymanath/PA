package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IWebServiceHandlerService {
	
	public String checkForUserAuthentication(String userName , String passWord);
	public ResultStatus checkUserAuthenticationAndUpdateAuthorisedTime(String userId,String macAdressId);

}
