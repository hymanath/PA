package com.itgrids.partyanalyst.webservice.android.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.itgrids.partyanalyst.dto.EffectedBoothsResponse;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;
import com.itgrids.partyanalyst.dto.WSResultVO;
import com.itgrids.partyanalyst.webservice.android.abstractservice.IWebServiceHandlerService1;
import com.itgrids.partyanalyst.webservice.android.concreteservice.WebServiceHandlerService1;
import com.itgrids.partyanalyst.webservice.utils.VoterTagVO;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLocationTrackingVo;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLoginVO;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserResponseVO;

@Component
@Path("/android/fieldData")
public class WebServiceHandler2 {
	
	//@Autowired
	//@Qualifier("web1")
	private IWebServiceHandlerService1  webServiceHandlerService1;
	
	
	public IWebServiceHandlerService1 getWebServiceHandlerService1() {
		return webServiceHandlerService1;
	}


	public void setWebServiceHandlerService1(
			IWebServiceHandlerService1 webServiceHandlerService1) {
		this.webServiceHandlerService1 = webServiceHandlerService1;
	}


	@POST
	@Path("/loginFieldDataUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object loginFieldDataUser (UserLoginVO inputs)
	{
		UserResponseVO out=null;
		Map<String,String> userDetails= new HashMap<String, String>();
		userDetails.put("","");
		try{
			
			out=(UserResponseVO) webServiceHandlerService1.checkForUserAuthentication(inputs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(out==null)
			return "{\"status\":\"login failure\"}";
		out.setStatus("Success");

		 return out;
	}
	
	@POST
	@Path("/saveFieldData")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object saveFieldData (SurveyResponceVO inputs)
	{
		ResultStatus out=null;
		Map<String,String> userDetails= new HashMap<String, String>();
		userDetails.put("","");
		try{ 
			out=(ResultStatus) webServiceHandlerService1.saveSurveyFieldUsers(inputs);
			
			//out=(UserResponseVO) webServiceHandlerService1.checkForUserAuthentication(inputs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(!out.getMessage().equalsIgnoreCase("Success"))
			return "{\"status\":\"Failure\"}";
		
		 else return "{\"status\":\"Success\"}";
	}
	
	
	@POST
	@Path("/saveUserTrackingDetails")
	@Consumes(MediaType.APPLICATION_JSON)
	public Object saveUserLocations (UserLocationTrackingVo  userLocationTrackingVo)
	{
		ResultStatus out=null;
		
		try{ 
			out=(ResultStatus) webServiceHandlerService1.saveUserTrackingLocation(userLocationTrackingVo);
			
			//out=(UserResponseVO) webServiceHandlerService1.checkForUserAuthentication(inputs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(!out.getMessage().equalsIgnoreCase("Success"))
			return "{\"status\":\"Failure\"}";
		
		 else return "{\"status\":\"Success\"}";
	}
	
	
	
}
