package com.itgrids.partyanalyst.webservice.android.abstractservice;

import com.itgrids.partyanalyst.dto.SurveyResponceVO;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLoginVO;

public interface IWebServiceHandlerService1 {
	
	public Object checkForUserAuthentication(UserLoginVO inputvo);
	public Object saveSurveyFieldUsers(SurveyResponceVO inputResponse);

/*	public ResultStatus checkUserAuthenticationAndUpdateAuthorisedTime(String userId,String macAdressId);
*/	


}
