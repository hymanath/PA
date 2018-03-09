package com.itgrids.partyanalyst.service;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.codehaus.jettison.json.JSONObject;

import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IZohoAlertService {
	
	public String getMobileNoByMemberShip(String memberShipId);
	public String sendOtp(String phoneNumber,Long cadreId,String membershipId);
	public JSONObject checkOTPDetails(JSONObject jObj);
	public String generateJwtForZoho(String userToken)throws UnsupportedEncodingException;
	public JSONObject sendSms(String phoneNumber,String message);
	public File getSamlXmlFile(String memberShipId,String successMsg,String firstName,String lastName);
	public ResultStatus sendSamlResponseToZoho(String membershipId);
}
