package com.itgrids.partyanalyst.service.impl;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dto.AmsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IZohoAlertService;
import com.itgrids.partyanalyst.service.IZohoWebServiceHandlerService;

public class ZohoWebServiceHandlerService implements IZohoWebServiceHandlerService{

	private static final Logger LOG = Logger.getLogger(ZohoWebServiceHandlerService.class);
	
	
	private IZohoAlertService zohoAlertService;


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




	@Override
	public ResultStatus mobileOtpVerification(AmsVO vo) {
		try {
			return zohoAlertService.checkOTPDetails(vo);
		} catch (Exception e) {
			LOG.error("Exception raised at mobileOtpVerification method in ZohoWebServiceHandlerService Class", e);
		}
		return null;
	}




	/*@Override
	public ResultStatus sendOTPAtLoginTime(String message, boolean isEnglish, String... phoneNumbers) {
		try {
			return zohoAlertService.sendOtp(message, isEnglish, phoneNumbers);
		} catch (Exception e) {
			LOG.error("Exception raised at sendOTPAtLoginTime method in ZohoWebServiceHandlerService Class", e);
		}
		return null;
	}*/
}
