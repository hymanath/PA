package com.itgrids.partyanalyst.service.impl;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.itgrids.partyanalyst.service.IAlertRenderService;
import com.itgrids.partyanalyst.service.IZohoAlertService;
import com.itgrids.partyanalyst.service.IZohoWebServiceHandlerService;

public class ZohoWebServiceHandlerService implements IZohoWebServiceHandlerService{

	private static final Logger LOG = Logger.getLogger(ZohoWebServiceHandlerService.class);
	
	
	private IZohoAlertService zohoAlertService;
	private IAlertRenderService alertRenderService;
	

	public void setAlertRenderService(IAlertRenderService alertRenderService) {
		this.alertRenderService = alertRenderService;
	}

	public void setZohoAlertService(IZohoAlertService zohoAlertService) {
		this.zohoAlertService = zohoAlertService;
	}




	public String testMethod()
	{
		try{
			return "Success";
			
		}catch(Exception e)
		{
			LOG.error(e);
		}
		return null;
	}

	public String getMobileNoByMemberShip(String memberShipId) {
		try {
			return zohoAlertService.getMobileNoByMemberShip(memberShipId);
		} catch (Exception e) {
			LOG.error("Exception raised at getMobileNoByMemberShip method in ZohoWebServiceHandlerService Class", e);
		}
		return null;
	}




	public JSONObject mobileOtpVerification(JSONObject jobj) {
		try {
			return zohoAlertService.checkOTPDetails(jobj);
		} catch (Exception e) {
			LOG.error("Exception raised at mobileOtpVerification method in ZohoWebServiceHandlerService Class", e);
		}
		return null;
	}
	public String generateJwtForZoho(String userToken) {
		try {
			return zohoAlertService.generateJwtForZoho(userToken);
		} catch (Exception e) {
			LOG.error("Exception raised at mobileOtpVerification method in ZohoWebServiceHandlerService Class", e);
		}
		return null;
	}

	public JSONObject sendSms(JSONObject jobj) {
		try {
			return zohoAlertService.sendSms(jobj.getString("phoneNumber"), jobj.getString("message"));
		} catch (Exception e) {
			LOG.error("Exception raised at sendSms method in ZohoWebServiceHandlerService Class", e);
		}
		return null;
	}

	public JSONObject getZohoWebHookDetails(JSONArray jArry) {
		try {
			return alertRenderService.getJsonStructureOfAlertFromZoho(jArry);
		} catch (Exception e) {
			LOG.error("Exception raised at getZohoWebHookDetails method in ZohoWebServiceHandlerService Class", e);
		}
		return null;
	}
}
