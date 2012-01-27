package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
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
    private HttpSession session;
    private Integer resultValue;
        
   	public Integer getResultValue() {
		return resultValue;
	}
	public void setResultValue(Integer resultValue) {
		this.resultValue = resultValue;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public RegistrationVO getRegVO() {
		return regVO;
	}
	public void setRegVO(RegistrationVO regVO) {
		this.regVO = regVO;
	}

	private String email;
    
    private IRegionServiceData regionServiceDataImp;
	
	private IAnanymousUserService  ananymousUserService;
	private IStaticDataService staticDataService;
	private IMailService mailService;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
	
	public IMailService getMailService() {
		return mailService;
	}
	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public String execute(){
		
		if(prepopulate)
			return Action.SUCCESS;
		
		if(gender.size() == 0){
			gender.add("Male");
			gender.add("Female");
		}		
		
		List<SelectOptionVO> profileOpts = ananymousUserService.findAllProfileOptsAvailableInDB();
		//List<SelectOptionVO> states = regionServiceDataImp.getStatesByCountry(1l);
		List<SelectOptionVO> states = staticDataService.getParticipatedStatesForAnElectionType(new Long(2));
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
	
	public String checkForUserNameAvailabilityForFreashUser(){

		try {
			jObj = new JSONObject(getTask());
			System.out.println(jObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		result =  new Long(ananymousUserService.checkForUserNameAvailabilityForFreashUser(jObj.getString("userName")).getResultCode());
		 
		return SUCCESS;
	}
	
	public String recoverPassword(){
		
		regVO = new RegistrationVO();
		try {
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("forgotPassword")){
				
				regVO = ananymousUserService.getUserDetailsToRecoverPassword(jObj.getString("userName"));	
				if(regVO.getEmail()== null || regVO.getEmail() == " "){
				return SUCCESS;
				}
				else{
				 email = regVO.getEmail();
				 System.out.println("email"+email);
					String requestURL= request.getRequestURL().toString();
					String requestFrom = "";
					if(requestURL.contains("www.partyanalyst.com"))
						requestFrom = IConstants.SERVER;
					else
						requestFrom = IConstants.LOCALHOST;
					
					ResultStatus rs = mailService.sendMailToUserToRecoverPassword(regVO,requestFrom);
				 
				 if(rs.getResultCode() == 1){
					 regVO = null;
				 }
				return SUCCESS;
			}
			}
			System.out.println(jObj);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String changeUserNameToEmail(){
		regVO = new RegistrationVO();
		regVO=(RegistrationVO)session.getAttribute("USER");
		Long userId=regVO.getRegistrationID();
		
		try {
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("changeAnanymousUserNameToEmail")){
				
				resultValue=ananymousUserService.saveUserDetailsToChangeUserNameToEmail(userId,jObj.getString("userName"));	
				regVO = ananymousUserService.getUserDetailsToRecoverPassword(jObj.getString("userName"));
				 email = regVO.getEmail();
				 System.out.println("email"+email);
					String requestURL= request.getRequestURL().toString();
					String requestFrom = "";
					if(requestURL.contains("www.partyanalyst.com"))
						requestFrom = IConstants.SERVER;
					else
						requestFrom = IConstants.LOCALHOST;
					
					ResultStatus rs = mailService.sendMailToUserToRecoverPassword(regVO,requestFrom);
				 
				 if(rs.getResultCode() == 1){
					 regVO = null;
				 }
				return SUCCESS;
			}
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	
	}
	
	public void prepare() throws Exception {
		
		registrationId = 0l;
		session = request.getSession();
		regVO = (RegistrationVO) session.getAttribute("USER");
		
		if(regVO != null)
			registrationId = regVO.getRegistrationID();
		
        if( registrationId.intValue() == 0) 
        	regVO = new RegistrationVO();
        else 
		{	
        	regVO = ananymousUserService.getDetailsOfUserByUserId(registrationId);
        	
        	if(regVO != null)
        	{
        		prepopulate = true;
        		prepopulateLocations(regVO);
        	}
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
		//List<SelectOptionVO> districts = regionServiceDataImp.getDistrictsByStateID(Long.parseLong(regVO.getState()));
	    List<SelectOptionVO> constituencies = staticDataService.getConstituenciesByElectionTypeAndStateId(new Long(2), Long.parseLong(regVO.getState())).getConstituencies();
		
		gender.add("Male");
		gender.add("Female");
		
    	session.setAttribute("profileOpts", profileOpts);
		session.setAttribute("gender", gender);
		session.setAttribute("states", states);
		//session.setAttribute("districts", districts);
		session.setAttribute("constituencies", constituencies);
    }
}
