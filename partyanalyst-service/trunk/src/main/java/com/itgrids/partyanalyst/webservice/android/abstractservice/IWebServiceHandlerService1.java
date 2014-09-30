package com.itgrids.partyanalyst.webservice.android.abstractservice;

import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.LoginResponceVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLocationTrackingVo;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLoginVO;

public interface IWebServiceHandlerService1 {
	
	public Object checkForUserAuthentication(UserLoginVO inputvo);
	public Object saveSurveyFieldUsers(SurveyResponceVO inputResponse);
	public ResultStatus saveUserTrackingLocation(UserLocationTrackingVo userLocationTrackingVo);

	public LoginResponceVO checkForUserAuthenticationForCadre(UserLoginVO inputvo);
	public Object saveSurveyFieldUsersForCadre(CadreRegistrationVO inputResponse);

/*	public ResultStatus checkUserAuthenticationAndUpdateAuthorisedTime(String userId,String macAdressId);
*/	


}
