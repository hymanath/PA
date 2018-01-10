package com.itgrids.partyanalyst.service;

import java.io.UnsupportedEncodingException;

import org.codehaus.jettison.json.JSONObject;

public interface IZohoAlertService {
	
	public String getMobileNoByMemberShip(String memberShipId);
	public String sendOtp(String phoneNumber,Long cadreId,String membershipId);
	public JSONObject checkOTPDetails(JSONObject jObj);
	public String generateJwtForZoho(String userToken)throws UnsupportedEncodingException;

}
