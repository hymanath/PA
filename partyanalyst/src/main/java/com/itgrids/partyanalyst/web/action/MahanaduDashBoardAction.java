package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.MahanaduVisitVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IMahanaduDashBoardService;
import com.opensymphony.xwork2.Action;

public class MahanaduDashBoardAction implements ServletRequestAware {
	private HttpServletRequest request;
	private IMahanaduDashBoardService mahanaduDashBoardService;
	private MahanaduVisitVO mahanaduVisitVO;
	private String status;
	private static final Logger LOG = Logger.getLogger(MahanaduDashBoardAction.class); 
	
	public void setServletRequest(HttpServletRequest request) {		
		this.request = request;
	}
	
	public void setMahanaduDashBoardService(
			IMahanaduDashBoardService mahanaduDashBoardService) {
		this.mahanaduDashBoardService = mahanaduDashBoardService;
	}

	public MahanaduVisitVO getMahanaduVisitVO() {
		return mahanaduVisitVO;
	}

	public void setMahanaduVisitVO(MahanaduVisitVO mahanaduVisitVO) {
		this.mahanaduVisitVO = mahanaduVisitVO;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String execute(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
	    if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
	    	 return Action.SUCCESS;
		}else{
			return "error";
		}
	     
		
	}
	
	public String populateLatestInfo(){
		try{
		   mahanaduDashBoardService.getTodayTotalVisitors();
		}catch(Exception e){
			LOG.error("Exception rised in populateLatestInfo()",e);
		}
		return Action.SUCCESS;
		
	}
	
	public String getTodayTotalAndCurrentUsersInfo(){
		try{
		   mahanaduVisitVO = mahanaduDashBoardService.getTodayTotalAndCurrentUsersInfo();
		}catch(Exception e){
			LOG.error("Exception rised in getTodayTotalAndCurrentUsersInfo()",e);
		}
		return Action.SUCCESS;
	}
}
