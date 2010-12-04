package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class AnanymousUserAction extends ActionSupport implements
ServletRequestAware, ModelDriven<RegistrationVO>, Preparable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(AnanymousUserAction.class);
	private Long registrationId;
	private HttpServletRequest request;
	private String task;
	private JSONObject jObj;
	private List<String> gender = new ArrayList<String>();
	private Long result;
	
	//for problem management login
	private Boolean prepopulate = false;
    private String redirectLoc = null;
    private String name = null;
    private Long stateId = null;
    private Long districtId = null;
    private Long localBodyId = null;
    private Long constituencyId = null;
    private Long localBodyElectionTypeId = null;
    private RegistrationVO regVO = null;
    
    private IRegionServiceData regionServiceDataImp;
	
	private IAnanymousUserService  ananymousUserService;
	
	public Long getResult() {
		return result;
	}
	public Long getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(Long registrationId) {
		this.registrationId = registrationId;
	}
	public void setResult(Long result) {
		this.result = result;
	}
	
	public IAnanymousUserService getAnanymousUserService() {
		return ananymousUserService;
	}
	public void setAnanymousUserService(IAnanymousUserService ananymousUserService) {
		this.ananymousUserService = ananymousUserService;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public org.json.JSONObject getJObj() {
		return jObj;
	}
	public void setJObj(org.json.JSONObject obj) {
		jObj = obj;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<String> getGender() {
		return gender;
	}
	public void setGender(List<String> gender) {
		this.gender = gender;
	}
	
	public String getRedirectLoc() {
		return redirectLoc;
	}
	public void setRedirectLoc(String redirectLoc) {
		this.redirectLoc = redirectLoc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getLocalBodyId() {
		return localBodyId;
	}
	public void setLocalBodyId(Long localBodyId) {
		this.localBodyId = localBodyId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getLocalBodyElectionTypeId() {
		return localBodyElectionTypeId;
	}
	public void setLocalBodyElectionTypeId(Long localBodyElectionTypeId) {
		this.localBodyElectionTypeId = localBodyElectionTypeId;
	}
	
	
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	
	public String execute(){
		
		if(prepopulate)
			return Action.SUCCESS;
		
		if(gender.size() == 0){
			gender.add("Male");
			gender.add("Female");
		}		
		
		List<SelectOptionVO> profileOpts = ananymousUserService.findAllProfileOptsAvailableInDB();
		List<SelectOptionVO> states = regionServiceDataImp.getStatesByCountry(1l);
		List<SelectOptionVO> districts = new ArrayList<SelectOptionVO>(0);
		List<SelectOptionVO> constituencies = new ArrayList<SelectOptionVO>(0);
		
		HttpSession session = request.getSession();
		session.setAttribute("gender", gender);
		session.setAttribute("profileOpts", profileOpts);
		session.setAttribute("states", states);
		session.setAttribute("districts", districts);
		session.setAttribute("constituencies", constituencies);
		
		return Action.SUCCESS;
	}
	
	public String checkForUserNameAvailability(){

		try {
			jObj = new JSONObject(getTask());
			System.out.println(jObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		result = new Long(ananymousUserService.checkForUserNameAvalilability(jObj.getString("userName")).getResultCode());
		 
		return SUCCESS;
	}
	
	public void prepare() throws Exception {
		Long registrationId = Long.parseLong(request.getParameter("userId") != null?request.getParameter("userId"):"0");
		
        if( registrationId.intValue() == 0) 
        	regVO = new RegistrationVO();
        else 
        {	
        	regVO = ananymousUserService.getDetailsOfUserByUserId(registrationId);
        	this.registrationId = registrationId;
        	prepopulate = true;
        	prepopulateLocations(regVO);
        }        
	}    

    public RegistrationVO getModel() {
        return regVO;
    }
		     
    public void prepopulateLocations(RegistrationVO  regVO){
    	
    	HttpSession session = request.getSession();
    	
		List<String> gender = new ArrayList<String>();
		List<SelectOptionVO> profileOpts = ananymousUserService.findAllProfileOptsAvailableInDB(); 
		List<SelectOptionVO> states = regionServiceDataImp.getStatesByCountry(1l);
		List<SelectOptionVO> districts = regionServiceDataImp.getDistrictsByStateID(Long.parseLong(regVO.getState()));
		List<SelectOptionVO> constituencies = regionServiceDataImp.getConstituenciesByDistrictID(Long.parseLong(regVO.getDistrict()));
		
		gender.add("Male");
		gender.add("Female");
		
    	session.setAttribute("profileOpts", profileOpts);
		session.setAttribute("gender", gender);
		session.setAttribute("states", states);
		session.setAttribute("districts", districts);
		session.setAttribute("constituencies", constituencies);
    }
}
