package com.itgrids.partyanalyst.helper;

import com.itgrids.partyanalyst.dto.RegistrationVO;

public class EntitlementsHelper {
	
	public static Boolean checkForEntitlementToViewReport(Object registrationVO, String reportEntitlement){
		if(registrationVO != null){
			RegistrationVO regVO = (RegistrationVO)registrationVO;
			if(regVO.getEntitlements().contains(reportEntitlement))
				return true;
		}
		
		return false;
	}

}
