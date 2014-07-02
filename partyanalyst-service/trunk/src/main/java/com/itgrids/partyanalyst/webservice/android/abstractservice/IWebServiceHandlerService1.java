package com.itgrids.partyanalyst.webservice.android.abstractservice;

import java.util.List;

import com.itgrids.partyanalyst.dto.EffectedBoothsResponse;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;
import com.itgrids.partyanalyst.dto.WSResultVO;
import com.itgrids.partyanalyst.webservice.utils.VoterTagVO;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLoginVO;

public interface IWebServiceHandlerService1 {
	
	public String checkForUserAuthentication(UserLoginVO inputvo);
	
/*	public ResultStatus checkUserAuthenticationAndUpdateAuthorisedTime(String userId,String macAdressId);
*/	


}
