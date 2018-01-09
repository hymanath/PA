package com.itgrids.partyanalyst.service;

import org.codehaus.jettison.json.JSONObject;

import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IZohoAlertService {
	
	public String getMobileNoByMemberShip(String memberShipId);
	public String sendOtp(String phoneNumber,Long cadreId,String membershipId);
	public JSONObject checkOTPDetails(JSONObject jObj);

}
