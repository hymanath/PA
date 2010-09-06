package com.itgrids.partyanalyst.helper;

import com.itgrids.partyanalyst.dto.RegistrationVO;

public class EntitlementsHelper {
	
	public static Boolean checkForEntitlementToViewReport(RegistrationVO registrationVO, String reportEntitlement){
		if(registrationVO.getEntitlements().contains(reportEntitlement))
			return true;
		return false;
	}

}
