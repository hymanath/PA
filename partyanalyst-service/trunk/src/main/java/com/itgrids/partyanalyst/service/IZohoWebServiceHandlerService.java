package com.itgrids.partyanalyst.service;

import org.codehaus.jettison.json.JSONObject;

public interface IZohoWebServiceHandlerService {

	public String getMobileNoByMemberShip(String memberShipId);
//	public ResultStatus sendOTPAtLoginTime(String message, boolean isEnglish, String... phoneNumbers);
	public String mobileOtpVerification(JSONObject jObj);
	
	public String testMethod();
}
