package com.itgrids.partyanalyst.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itgrids.partyanalyst.dto.AmsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IZohoWebServiceHandlerService;

@Component
@Path("/Zoho")
public class ZohoWebServiceHandler {

	private static final Logger LOG = Logger.getLogger(ZohoWebServiceHandler.class);
	@Autowired
	private IZohoWebServiceHandlerService zohoWebServiceHandlerService;
	
	@GET
    @Path("/getURL")
	@Produces(MediaType.TEXT_PLAIN)
	public String getURL()
    {
		System.out.println(zohoWebServiceHandlerService.testMethod());
		return "http://mytdp.com/WebService/Zoho/";
    }
	
	@GET 
	@Path("/getMobileNoByMemberShipId/{memberShipId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getMobileNoByMemberShipId(@PathParam("memberShipId") String memberShipId){
		try {
			return zohoWebServiceHandlerService.getMobileNoByMemberShip(memberShipId);
		} catch (Exception e) {
			LOG.error("Exception Occured in getMobileNoByMemberShipId() Method in ZohoWebServiceHandler ",e);
		}
		return null;
	}
	
	@POST 
	@Path("/otpVerification")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultStatus otpVerification(AmsVO vo){
		try {
			return zohoWebServiceHandlerService.mobileOtpVerification(vo);
		} catch (Exception e) {
			LOG.error("Exception Occured in getMobileNoByMemberShipId() Method in ZohoWebServiceHandler ",e);
		}
		return null;
	}
	/*
	@POST 
	@Path("/sendOTP")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String sendOTPAtLoginTime(){
		try {
			return zohoWebServiceHandlerService.sendOTPAtLoginTime(memberShipId);
		} catch (Exception e) {
			LOG.error("Exception Occured in getMobileNoByMemberShipId() Method in ZohoWebServiceHandler ",e);
		}
		return null;
	}*/
}
