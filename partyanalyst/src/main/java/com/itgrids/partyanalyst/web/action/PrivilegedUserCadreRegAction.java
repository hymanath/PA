package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreDashboardVO;
import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.itgrids.partyanalyst.service.ICoreDashboardCadreRegistrationService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PrivilegedUserCadreRegAction extends ActionSupport implements ServletRequestAware {
	private final static Logger LOG = Logger.getLogger(PrivilegedUserCadreRegAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private JSONObject jObj;
	private String task;
	
	
	private ICoreDashboardCadreRegistrationService coreDashboardCadreRegistrationService;
	private ICadreDashBoardService cadreDashBoardService;
	
	
	private List<List<CadreDashboardVO>> listOfListOfCadreDashboardVO;
	
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpSession getSession() {
		return session; 
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;  
	}
	public ICoreDashboardCadreRegistrationService getCoreDashboardCadreRegistrationService() {
		return coreDashboardCadreRegistrationService;
	}
	public void setCoreDashboardCadreRegistrationService(ICoreDashboardCadreRegistrationService coreDashboardCadreRegistrationService) {
		this.coreDashboardCadreRegistrationService = coreDashboardCadreRegistrationService;
	}
	public ICadreDashBoardService getCadreDashBoardService() {
		return cadreDashBoardService;
	}
	public void setCadreDashBoardService(
			ICadreDashBoardService cadreDashBoardService) {
		this.cadreDashBoardService = cadreDashBoardService;
	}
	public List<List<CadreDashboardVO>> getListOfListOfCadreDashboardVO() {
		return listOfListOfCadreDashboardVO;
	}
	public void setListOfListOfCadreDashboardVO(
			List<List<CadreDashboardVO>> listOfListOfCadreDashboardVO) {
		this.listOfListOfCadreDashboardVO = listOfListOfCadreDashboardVO;
	}
	//Business method
	public String execute(){
		return Action.SUCCESS;
	}
	public String get2016LocationWiseRegisteredCountsForPreviligedUser(){
		try{
			LOG.info("Entered into get2016LocationWiseRegisteredCounts() of PrivilegedUserCadreRegAction{}");
			jObj = new JSONObject(getTask()); 
			Long userId = jObj.getLong("userId");
			String locationType = jObj.getString("locationType");   
			String type = jObj.getString("type");
			listOfListOfCadreDashboardVO = cadreDashBoardService.get2016LocationWiseRegisteredCountsForPreviligedUser(userId, locationType, type);  
		}catch(Exception e){
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

}
