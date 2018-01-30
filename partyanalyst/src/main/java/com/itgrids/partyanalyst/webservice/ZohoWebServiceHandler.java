package com.itgrids.partyanalyst.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	@Path("/getMobileNoByMemberShipId/{memberShipId}") // generate OTP BY MembershipId 
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
	@Path("/userAuthenticator")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JSONObject otpVerification(JSONObject jObj){
		try {
			return zohoWebServiceHandlerService.mobileOtpVerification(jObj);
		} catch (Exception e) {
			LOG.error("Exception Occured in getMobileNoByMemberShipId() Method in ZohoWebServiceHandler ",e);
		}
		return null;
	}
	
	@GET
    @Path("/getJwt")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJwtByUserToken(@QueryParam("user_token")String user_token)
    {
		return zohoWebServiceHandlerService.generateJwtForZoho(user_token);
    }
	
	
	@POST 
	@Path("/sendSms")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JSONObject sendSms(JSONObject json){
		try {
			 return zohoWebServiceHandlerService.sendSms(json);
		} catch (Exception e) {
			LOG.error("Exception Occured in createAlertApi() Method in ZohoWebServiceHandler ",e);
		}
		return null;
	}
	
	
	@POST 
	@Path("/getZohoWebHookDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JSONObject getZohoWebHookDetails(JSONArray jArray){
		try {
			LOG.error("getZohoWebHookDetails Json Object is : "+jArray+" - in String Format It Is : "+jArray.toString());
			 return zohoWebServiceHandlerService.getZohoWebHookDetails(jArray);
		} catch (Exception e) {
			LOG.error("Exception Occured in getZohoWebHookDetails() Method in ZohoWebServiceHandler ",e);
		}
		return null;
	}
	
	
}
