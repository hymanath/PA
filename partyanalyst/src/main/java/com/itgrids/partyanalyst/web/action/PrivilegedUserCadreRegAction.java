package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreDashboardVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.RegistrationCountVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.itgrids.partyanalyst.service.ICadreRegistrationServiceNew;
import com.itgrids.partyanalyst.service.ICoreDashboardCadreRegistrationService;
import com.itgrids.partyanalyst.utils.IConstants;
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
	private ICadreRegistrationServiceNew cadreRegistrationServiceNew;
	
	
	private List<List<CadreDashboardVO>> listOfListOfCadreDashboardVO;
	private List<RegistrationCountVO> registrationCountVOs;
	private List<IdAndNameVO> idAndNameVOs;
	private IdAndNameVO idAndNameVO;
	
	private Long userId;
	private String stateName;
	
	
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<RegistrationCountVO> getRegistrationCountVOs() {
		return registrationCountVOs;
	}
	public void setRegistrationCountVOs(
			List<RegistrationCountVO> registrationCountVOs) {
		this.registrationCountVOs = registrationCountVOs;
	}
	
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public List<IdAndNameVO> getIdAndNameVOs() {
		return idAndNameVOs;
	}
	public void setIdAndNameVOs(List<IdAndNameVO> idAndNameVOs) {
		this.idAndNameVOs = idAndNameVOs;
	}
	
	public ICadreRegistrationServiceNew getCadreRegistrationServiceNew() {
		return cadreRegistrationServiceNew;
	}
	public void setCadreRegistrationServiceNew(
			ICadreRegistrationServiceNew cadreRegistrationServiceNew) {
		this.cadreRegistrationServiceNew = cadreRegistrationServiceNew;
	}
	
	public IdAndNameVO getIdAndNameVO() {
		return idAndNameVO;
	}
	public void setIdAndNameVO(IdAndNameVO idAndNameVO) {
		this.idAndNameVO = idAndNameVO;
	}
	//Business method
	public String execute(){
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute(IConstants.USER);
		List<String> entitlements = null;
		if(user != null && user.getEntitlements() != null && user.getEntitlements().size()>0){
			entitlements = user.getEntitlements();
			userId = user.getRegistrationID();
			stateName = user.getStateName();
			if(!(entitlements.contains("ACCESS_USERS_CADRE_REGISTRATION_2016_DASHBOARD") || entitlements.contains("ACCESS_USERS_CADRE_REGISTRATION_2016_ADMIN_DASHBOARD_ENTITLEMENT"))){
				return Action.ERROR;    
			}   
		}
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
			LOG.info("Entered into get2016LocationWiseRegisteredCounts() of PrivilegedUserCadreRegAction{}");
		}
		return Action.SUCCESS;       
	}
	public String getRegistrationCountDtls(){      
		try{
			LOG.info("Entered into get2016LocationWiseRegisteredCounts() of getRegistrationCountDtls{}");
			jObj = new JSONObject(getTask()); 
			String scope = jObj.getString("scope");
			Long constituencyId = jObj.getLong("constituencyId");
			String location = jObj.getString("location");
			registrationCountVOs = cadreDashBoardService.getRegistrationCountDtls(location,constituencyId,scope);
		}catch(Exception e){
			e.printStackTrace();
		}
		return Action.SUCCESS;       
	}
	public String getCadreRegistrationCountByConstituency(){  
	    try{
	    	jObj = new JSONObject(getTask()); 
	    	String fromDate = jObj.getString("fromDate");
	    	String toDate = jObj.getString("toDate");
	    	Long constituencyId = jObj.getLong("constituencyId");
	    	idAndNameVOs = cadreDashBoardService.getCadreRegistrationCountByConstituency(constituencyId,fromDate,toDate);  
	      
	    }catch(Exception e){  
	      LOG.error("Exception raised at getCadreRegistrationCountByConstituency() method of CoreDashBoard", e);
	    }
	    return Action.SUCCESS;
	  }
}  
