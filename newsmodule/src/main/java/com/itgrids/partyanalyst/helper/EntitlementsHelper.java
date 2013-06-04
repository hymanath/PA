package com.itgrids.partyanalyst.helper;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.utils.IConstants;

public class EntitlementsHelper {
	
	private ILoginService loginService;
	
	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public Boolean checkForEntitlementToViewReport(RegistrationVO registrationVO, String reportEntitlement){
		if(loginService.getDefaultEntitlements(IConstants.DEFAULT_ENTITLEMENTS_GROUP).contains(reportEntitlement) || 
				(registrationVO != null && registrationVO.getEntitlements().contains(reportEntitlement)))
			return true;
		return false;
	}
	
	public Boolean checkForRegionToViewReport(RegistrationVO registrationVO, String regionType, Long regionId){
		return loginService.checkForRegionToViewReport(registrationVO, regionType, regionId);
	}
	
}
