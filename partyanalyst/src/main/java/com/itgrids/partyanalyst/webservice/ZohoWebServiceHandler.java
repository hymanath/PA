package com.itgrids.partyanalyst.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itgrids.partyanalyst.service.IZohoWebServiceHandlerService;

@Component
@Path("/Zoho/")
public class ZohoWebServiceHandler {

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
}
