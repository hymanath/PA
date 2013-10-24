package com.itgrids.partyanalyst.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itgrids.partyanalyst.service.IWebServiceHandlerService;

@Component
@Path("/")
public class WebServiceHandler {
	
	@Autowired
	private IWebServiceHandlerService  webServiceHandlerService;
	
	

	public IWebServiceHandlerService getWebServiceHandlerService() {
		return webServiceHandlerService;
	}



	public void setWebServiceHandlerService(
			IWebServiceHandlerService webServiceHandlerService) {
		this.webServiceHandlerService = webServiceHandlerService;
	}



	/*@GET
    @Path("{userName}/{passWord}")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkForUserAuthentication(  @PathParam("userName")  String userName ,  @PathParam("passWord")  String passWord )
    {
		return webServiceHandlerService.checkForUserAuthentication(userName , passWord);
    }*/
	
	
	@GET
    @Path("/getMobileAppAuthorizationURL")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMobileAppAuthorizationURL()
    {
		return "http://192.168.4.21:8080/PartyAnalyst/WebService/appAuthorization";
    }
	
	
	@GET
    @Path("/appAuthorization/{userId}/{macId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkForUserAuthentication(@PathParam("userId") String userId , @PathParam("macId") String macId )
    {
		if(userId.equalsIgnoreCase("userId" ) && macId.equalsIgnoreCase("macId"))
		 return "true";
		else
			return "false";
    }
}
