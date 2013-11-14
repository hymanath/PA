package com.itgrids.partyanalyst.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itgrids.partyanalyst.dto.ResultStatus;

import com.itgrids.partyanalyst.service.IWebServiceHandlerService;
@Component
@Path("/ValidateUserAccessKey")
public class ValidateUserAccessKey {
	@Autowired
	private IWebServiceHandlerService  webServiceHandlerService;
	private ResultStatus resultStatus;
	
	public IWebServiceHandlerService getWebServiceHandlerService() {
		return webServiceHandlerService;
	}
	public void setWebServiceHandlerService(
			IWebServiceHandlerService webServiceHandlerService) {
		this.webServiceHandlerService = webServiceHandlerService;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	@GET
    @Path("/validateUserAccessKey/{uniqueCode}/{pwd}/{accessKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkForUserAuthentication(@PathParam("uniqueCode") String uniqueCode , @PathParam("pwd") String pwd, @PathParam("accessKey") String accessKey)
    {
		try{
		 resultStatus = webServiceHandlerService.updatePassword(uniqueCode,pwd,accessKey);
		if(resultStatus.getResultCode() == 0)
		 return "Update Successfully";
		else
			return "Failure";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Failure";
		}
    }
	
}
