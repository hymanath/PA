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
import org.springframework.stereotype.Component;

import com.itgrids.partyanalyst.dto.EffectedBoothsResponse;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;
import com.itgrids.partyanalyst.dto.WSResultVO;

import com.itgrids.partyanalyst.webservice.android.abstractservice.IWebServiceHandlerService1;
import com.itgrids.partyanalyst.webservice.utils.VoterTagVO;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLoginVO;

@Component
@Path("/android/fieldData1")
public class WebServiceHandler1 {
	
	//@Autowired
	private IWebServiceHandlerService1  webServiceHandlerService1;
	
	
	@POST
	@Path("/loginFieldDataUser1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void loginFieldDataUser (UserLoginVO inputs)
	{
		Map<String,String> userDetails= new HashMap<String, String>();
		userDetails.put("","");
		try{
			
		}
		catch(Exception e)
		{
			
		}
	}
	
	
}
