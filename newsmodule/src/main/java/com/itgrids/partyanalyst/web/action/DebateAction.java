package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.DebateDetailsVO;
import com.itgrids.partyanalyst.dto.DebateVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IDebateService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DebateAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(DebateAction.class); 
	private HttpServletRequest request;
	private HttpSession session;
	private IDebateService debateService;
	private ResultStatus resultStatus;
	private DebateVO debateVO;
	
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	public void setDebateService(IDebateService debateService) {
		this.debateService = debateService;
	}

	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}


	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	

	public DebateVO getDebateVO() {
		return debateVO;
	}


	public void setDebateVO(DebateVO debateVO) {
		this.debateVO = debateVO;
	}


	public String execute()
	{
		try {
			LOG.info("Entered into execute methon in DebateAction Class");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
			{
				return Action.ERROR;
			}
		} catch (Exception e) {
			LOG.error("Exception occured in execute methon in DebateAction Class",e);
		}
		
		return Action.SUCCESS;
	}
	
	
	
	
	
	public String saveDebateDetial()
	{
		try 
		{
			LOG.info("Entered into saveDebateDetial methon in DebateAction Class");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
			{
				return Action.ERROR;
			}
			 DebateDetailsVO debateDetailsVO = new DebateDetailsVO();
			 resultStatus = debateService.saveDebateDetails(debateDetailsVO);
		} 
		catch (Exception e)
		{
			LOG.error("Exception occured in saveDebateDetial methon in DebateAction Class",e);
		}
		return Action.SUCCESS;
	}
	
	public String retriveDebateDetails()
	{
		try 
		{
			LOG.info("Entered into saveDebateDetial methon in DebateAction Class");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
			{
				return Action.ERROR;
			}
			debateVO = debateService.getDebateDetailsForSelected(Long.parseLong(request.getParameter("debateId")));
		} 
		catch (Exception e)
		{
			LOG.error("Exception occured in saveDebateDetial methon in DebateAction Class",e);
		}
		return Action.SUCCESS;
	}

}
