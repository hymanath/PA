package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IAcPcWiseElectionResultService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AcPcWiseElectionResultAction extends ActionSupport implements ServletRequestAware  {

	
	
	private static final long serialVersionUID = -8021304666056628316L;
	HttpServletRequest request;
	private HttpSession session;
	private static final Logger LOG = Logger.getLogger(AcPcWiseElectionResultAction.class);
	
	@Autowired
	IAcPcWiseElectionResultService acPcWiseElectionResultService;
	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}
	
	
	public String execute()
	{
		try
		{
			LOG.debug("Entered Into execute method in AcPcWiseElectionResultAction Action");
			session = request.getSession();
			RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO != null) 
			{
				if (!registrationVO.getIsAdmin().equals("true"))
					  return ERROR;
			} 
			else
				return ERROR;
			
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised In execute method in AcPcWiseElectionResultAction Action", e);
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}

}
