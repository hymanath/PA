package com.itgrids.partyanalyst.service;

import org.codehaus.jettison.json.JSONObject;

public interface IZohoAlertService {
	
	public String getMobileNoByMemberShip(String memberShipId);
	public String sendOtp(String phoneNumber,Long cadreId,String membershipId);
	public String checkOTPDetails(JSONObject jObj);

}
