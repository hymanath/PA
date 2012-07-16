package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.CompleteProblemDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ICompleteProblemDetailsService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;

 public class CompleteProblemDetailsAction implements ServletRequestAware{
   private final static Logger log = Logger.getLogger(CompleteProblemDetailsAction.class);
   private Long problemId;
   private HttpServletRequest request;
   private HttpSession session;
   private ICompleteProblemDetailsService completeProblemDetailsService;
   private CompleteProblemDetailsVO completeProblemDetailsVO;
   
   public Long getProblemId() {
	return problemId;
   }
   public void setProblemId(Long problemId) {
	this.problemId = problemId;
   }
   public ICompleteProblemDetailsService getCompleteProblemDetailsService() {
	return completeProblemDetailsService;
   }
   public void setCompleteProblemDetailsService(
		ICompleteProblemDetailsService completeProblemDetailsService) {
	this.completeProblemDetailsService = completeProblemDetailsService;
   }
   
   public CompleteProblemDetailsVO getCompleteProblemDetailsVO() {
	return completeProblemDetailsVO;
   }
   
   public void setCompleteProblemDetailsVO(
		CompleteProblemDetailsVO completeProblemDetailsVO) {
	this.completeProblemDetailsVO = completeProblemDetailsVO;
   }
   
   public String execute(){
	   if(log.isDebugEnabled())
		   log.debug("Enter into execute method");
	   try{
	     RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
	     Long userId = null;
	     String userStatus = null;
		if(regVO != null){
			userId = regVO.getRegistrationID();
			if((Boolean)session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE))
				userStatus = IConstants.PARTY_ANALYST_USER;
			if((Boolean)session.getAttribute(IWebConstants.FREE_USER_ROLE))
				userStatus = IConstants.FREE_USER;
			if((Boolean)session.getAttribute(IWebConstants.FREE_USER_ROLE) && (Boolean)session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE))
				userStatus = IConstants.BOTH;
				
		}else{
			userStatus = IConstants.NOT_LOGGED_IN;
		}
		completeProblemDetailsVO = completeProblemDetailsService.getProblemCompleteDetails(problemId, userId, userStatus);
	   }catch(Exception e){
		   log.error("Exception rised in execute method ",e);
	   }
	   return Action.SUCCESS;
   }

 @Override
 public void setServletRequest(HttpServletRequest request) {
	    this.request = request;
		this.session = request.getSession();
	
 }
  
}
