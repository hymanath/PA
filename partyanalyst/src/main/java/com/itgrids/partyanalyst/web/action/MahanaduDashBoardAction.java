package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
	private List<MahanaduVisitVO> mahanaduVisitList;
	private String status;
	private static final Logger LOG = Logger.getLogger(MahanaduDashBoardAction.class); 
	
	public void setServletRequest(HttpServletRequest request) {		
		this.request = request;
	}
	
	public void setMahanaduDashBoardService(
			IMahanaduDashBoardService mahanaduDashBoardService) {
		this.mahanaduDashBoardService = mahanaduDashBoardService;
	}

	public List<MahanaduVisitVO> getMahanaduVisitList() {
		return mahanaduVisitList;
	}

	public void setMahanaduVisitList(List<MahanaduVisitVO> mahanaduVisitList) {
		this.mahanaduVisitList = mahanaduVisitList;
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
		   mahanaduDashBoardService.getTodayTotalVisitorsForSchedular();
		}catch(Exception e){
			LOG.error("Exception rised in populateLatestInfo()",e);
		}
		return Action.SUCCESS;
		
	}
	
	public String getTodayTotalVisitorsForWeb(){
		try{
		   mahanaduDashBoardService.getTodayTotalVisitorsForWeb();
		}catch(Exception e){
			LOG.error("Exception rised in getTodayTotalVisitorsForWeb()",e);
		}
		return Action.SUCCESS;
		
	}
	
	public String getTodayTotalAndCurrentUsersInfo(){
		try{
			String fromDateStr = request.getParameter("fromDate");
			String toDateStr = request.getParameter("toDate");
			Date fromDate = null;
			Date toDate = null;
			if(fromDateStr != null && fromDateStr.length() > 0 && toDateStr != null && toDateStr.length() > 0){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			mahanaduVisitList = mahanaduDashBoardService.getTodayTotalAndCurrentUsersInfo(fromDate,toDate);
		}catch(Exception e){
			LOG.error("Exception rised in getTodayTotalAndCurrentUsersInfo()",e);
		}
		return Action.SUCCESS;
	}
}
