package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.MahanaduVisitVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IMahanaduDashBoardService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class MahanaduDashBoardAction implements ServletRequestAware {
	private HttpServletRequest request;
	private IMahanaduDashBoardService mahanaduDashBoardService;
	private List<MahanaduVisitVO> mahanaduVisitList;
	private String status;
	private static final Logger LOG = Logger.getLogger(MahanaduDashBoardAction.class); 

	private String task;
	private JSONObject jObj;
	private Long eventId;
	private HttpSession session;
	private EntitlementsHelper entitlementsHelper;
	
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
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
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String execute(){
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"MAHANADU_MAIN_DASHBOARD_ENTITLEMENT") ||
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"MAHANADU_MAIN_DASHBOARD_ADMIN_ENTITLEMENT") ){
			return Action.SUCCESS;
		}
		else if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
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
			/*String fromDateStr = request.getParameter("fromDate");
			String toDateStr = request.getParameter("toDate");
			Date fromDate = null;
			Date toDate = null;
			if(fromDateStr != null && fromDateStr.length() > 0 && toDateStr != null && toDateStr.length() > 0){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}*/
			
			jObj = new JSONObject(getTask());
			
			mahanaduVisitList = mahanaduDashBoardService.getTodayTotalAndCurrentUsersInfoList(jObj.getLong("eventId"));
		}catch(Exception e){
			LOG.error("Exception rised in getTodayTotalAndCurrentUsersInfo()",e);
		}
		return Action.SUCCESS;
	}
	public String getMahanaduCadreVisitNewInfo(){
		return Action.SUCCESS;
	}
}
