package com.itgrids.partyanalyst.service;

import org.codehaus.jettison.json.JSONObject;

import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IZohoWebServiceHandlerService {

	public String getMobileNoByMemberShip(String memberShipId);
//	public ResultStatus sendOTPAtLoginTime(String message, boolean isEnglish, String... phoneNumbers);
	public JSONObject mobileOtpVerification(JSONObject jObj);
	public String generateJwtForZoho(String userToken);
	public String testMethod();
	public JSONObject createAlertApi(JSONObject jobj);
}
