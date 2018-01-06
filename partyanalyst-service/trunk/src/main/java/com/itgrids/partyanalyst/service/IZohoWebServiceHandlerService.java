package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.AmsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IZohoWebServiceHandlerService {

	public String getMobileNoByMemberShip(String memberShipId);
//	public ResultStatus sendOTPAtLoginTime(String message, boolean isEnglish, String... phoneNumbers);
	public ResultStatus mobileOtpVerification(AmsVO vo);
	
	public String testMethod();
}
