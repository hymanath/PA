package com.itgrids.partyanalyst.webservice.android.components;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.LoginResponceVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
import com.itgrids.partyanalyst.webservice.android.abstractservice.IWebServiceHandlerService1;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLocationTrackingVo;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLoginUtils;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserResponseVO;

@Component
@Path("/android/fieldData")
public class WebServiceHandler2 {
	
	//@Autowired
	//@Qualifier("web1")
	private final static Logger LOG = Logger.getLogger(WebServiceHandler2.class);

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
	public Object loginFieldDataUser (UserLoginUtils inputs)
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
		LOG.debug("inside save field data");
		LOG.debug(inputs.toString());
		
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
		
		LOG.debug("inside save field data");
		LOG.debug(userLocationTrackingVo.toString());
		
		
		ResultStatus out=null;

	
				
		String imeiNo=userLocationTrackingVo.getImeiNo();
		
		if(imeiNo==null || imeiNo.isEmpty() || imeiNo.equalsIgnoreCase("Null"))
		{
			LOG.debug("imei number null please check");
			 return "{\"status\":\"Failure\"}";
		}
		
		try{ 
			out=(ResultStatus) webServiceHandlerService1.saveUserTrackingLocation(userLocationTrackingVo);
			
			//out=(UserResponseVO) webServiceHandlerService1.checkForUserAuthentication(inputs);
		}
		catch(Exception e)
		{
			
			LOG.error("Exception raised in saveSurveyUserTrackingDetails service in SurveyDataDetailsService", e);

		}
		
		if(out.getMessage()!=null&&!out.getMessage().equalsIgnoreCase("Success"))
			  return "{\"status\":\"Failure\"}";
		
		 else return "{\"status\":\"Success\"}";
	}
	
	
	
	@POST
	@Path("/loginFieldDataUserForCadre")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object loginFieldDataUserForCadre (UserLoginUtils inputs)
	{
		LoginResponceVO out=null;
		Map<String,String> userDetails= new HashMap<String, String>();
		userDetails.put("","");
		try{
			
			out=(LoginResponceVO) webServiceHandlerService1.checkForUserAuthenticationForCadre(inputs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(out==null)
			return "{\"status\":\"login failure\"}";
		//out.setStatus("Success");
		out.setStatusMsg("Success");

		 return out;
	}
	
	
	
	@POST
	@Path("/saveFieldDataForCadre")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object saveFieldDataForCadre (CadreRegistrationVO inputs)
	{
		LOG.debug("inside save field data");
		LOG.debug(inputs.toString());
		ResultStatus out=null;
		Map<String,String> userDetails= new HashMap<String, String>();
		userDetails.put("","");
		try{ 
			out=(ResultStatus) webServiceHandlerService1.saveSurveyFieldUsersForCadre(inputs);
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
