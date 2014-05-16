package com.itgrids.partyanalyst.web.action;

import java.util.Properties;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IConstituencyWiseElectionResultsService;
import com.itgrids.partyanalyst.service.impl.ConstituencyWiseElectionResultsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.MultiThreadRunner;
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
    
  @Autowired	
  private  MultiThreadRunner multiThreadRunner; 
	
	
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
		  /* Long stateNo=0l;
		   if(statNo.trim().length()!=0)
		   {
			   stateNo=Long.parseLong(statNo);
			   
		   }*/
		    
		    Long Level=Long.parseLong(request.getParameter("Level"));
		    Long constituencyNo=Long.parseLong(request.getParameter("constituencyNo"));
		    String description=request.getParameter("description");
		   
		    constituencyWiseElectionResultsService.constituencyResults(statNo,Level,constituencyNo,description);
		    
		    
		
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
			String takenPassword="rak@start";
			Long status=Long.parseLong(request.getParameter("status"));
			Long level=Long.parseLong(request.getParameter("level"));
			
			if(givenPassword.equalsIgnoreCase(takenPassword))
			{
				/* runner.startTask(2, 5,"parliament");
			        Thread.sleep(10000);
			        //timer.cancel();
			        System.out.println("now");
			        //timer=new Timer();
			        
			        runner.startTaskForParliament(2, 5,"hello");
			        Thread.sleep(10000);
			        timer.cancel();*/
				
			Properties prop=	ConstituencyWiseElectionResultsService.loadProperties();
			int period=Integer.valueOf(prop.get("periodInterval").toString()) ;
			int period1=Integer.valueOf(prop.get("periodIntervalForParliament").toString()) ;
				if(level.equals(1L)) //assembly
				{
					if(status.equals(1L))
					{ 
						multiThreadRunner.timer=new Timer();
						multiThreadRunner.startTask(2, period,"Constituency");
					}else if(status.equals(2L))
					{
						multiThreadRunner.timer.cancel();
					}
				
				}else if(level.equals(2l))//parliament
				{
					
					if(status.equals(1L))
					{   multiThreadRunner.timer1=new Timer();
						multiThreadRunner.startTaskForParliament(2, period1,"Parliament");
					}else if(status.equals(2L))
					{
						multiThreadRunner.timer1.cancel();
					}
				}
				
				
				
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
