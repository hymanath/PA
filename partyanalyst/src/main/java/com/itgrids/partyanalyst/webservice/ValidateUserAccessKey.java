package com.itgrids.partyanalyst.webservice;

import javax.ws.rs.Path;

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
	
	
}
