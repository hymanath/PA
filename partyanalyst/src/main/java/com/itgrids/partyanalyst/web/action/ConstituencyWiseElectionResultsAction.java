package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IConstituencyWiseElectionResultsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ConstituencyWiseElectionResultsAction extends ActionSupport implements ServletRequestAware{
	
    private static final long serialVersionUID = -8021304666056628316L;
	private static final Logger LOG = Logger.getLogger(ConstituencyWiseElectionResultsAction.class);
	private HttpSession session;
	HttpServletRequest request=null;

	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
	
    IConstituencyWiseElectionResultsService constituencyWiseElectionResultsService; 
		

	
	
	public void setConstituencyWiseElectionResultsService(
			IConstituencyWiseElectionResultsService constituencyWiseElectionResultsService) {
		this.constituencyWiseElectionResultsService = constituencyWiseElectionResultsService;
	}

	public String gettingConstituencyResults()
	{
	  try
	 {	LOG.debug("Entered Into gettingConstituencyResults method in ConstituencyWiseElectionResultsAction ");
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
	  catch(Exception e)
	  {
		  LOG.error("Exception Raised In gettingConstituencyResults  method in ConstituencyWiseElectionResultsAction ", e);
			return Action.ERROR;
		  
	  }
	 return "success";	
		
	}
	
	public String execute()
	{
		try
		{
			LOG.debug("Entered Into execute method in ConstituencyWiseElectionResultsAction Action");
			session = request.getSession();
			RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO != null) 
			{
				if (!registrationVO.getIsAdmin().equals("true"))
					  return ERROR;
			} 
			else
				return ERROR;
		   String statNo=request.getParameter("stateNo");
		   Long stateNo=0l;
		   if(statNo.trim().length()!=0)
		   {
			   stateNo=Long.parseLong(statNo);
			   
		   }
		    
		    Long Level=Long.parseLong(request.getParameter("Level"));
		    Long constituencyNo=Long.parseLong(request.getParameter("constituencyNo"));
		    String description=request.getParameter("description");
		   
		    constituencyWiseElectionResultsService.constituencyResults(stateNo,Level,constituencyNo,description);
		    
		    
		
		}
		catch(Exception e)
		{
			LOG.error("Exception Raised In execute method in ConstituencyWiseElectionResultsAction Action", e);
			return Action.ERROR;
			
		}
		return Action.SUCCESS;
	
}

	public String checkingPassword()
	{
		
		try{
			
			LOG.debug("Entered Into checkingPassword method in ConstituencyWiseElectionResultsAction Action");
			session = request.getSession();
			RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO != null) 
			{
				if (!registrationVO.getIsAdmin().equals("true"))
					  return ERROR;
			} 
			else
				return ERROR;
			String givenPassword=request.getParameter("password");
			String takenPassword="ssssss";
			Long status=Long.parseLong(request.getParameter("status"));
			Long level=Long.parseLong(request.getParameter("level"));
			
			if(givenPassword.equalsIgnoreCase(takenPassword))
			{
				
				
				
			}
			else{
				
				
			}
			
		}catch(Exception e)
		{
			
			LOG.error("Exception Raised In checkingPassword method in ConstituencyWiseElectionResultsAction Action", e);
			e.printStackTrace();
		}
		 return Action.SUCCESS;
		
		
		
		
	}
	
	
	
	
}	
