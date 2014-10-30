package com.itgrids.partyanalyst.webservice.android.abstractservice;

import java.util.List;

import com.itgrids.partyanalyst.dto.AppDbDataVO;
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
	public Object saveSurveyFieldUsersForCadre(List<CadreRegistrationVO> inputResponse);
	
	public Object getVCadreDataByPanchayatId(Long panchayatId);
	
	public LoginResponceVO databaseCheckForCadreUser(UserLoginVO inputvo);

/*	public ResultStatus checkUserAuthenticationAndUpdateAuthorisedTime(String userId,String macAdressId);
*/	
	public AppDbDataVO getAllVersionsOfAnApp(String appName,Double currentVerson,boolean includeTest);
	
	public AppDbDataVO getAllUpdatesByVersion(String appName,Double version);
}
