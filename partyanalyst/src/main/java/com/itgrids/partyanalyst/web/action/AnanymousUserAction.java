package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
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

	private static final Logger LOG = Logger.getLogger(AnanymousUserAction.class);
	private Long registrationId;
	private HttpServletRequest request;
	private String task;
	private JSONObject jObj;
	private List<String> gender = new ArrayList<String>();
	private Long result;
	private ResultStatus results;
	//for problem management login
	transient private Boolean prepopulate = false;
    private String redirectLoc = null;
    private String name = null;
    private Long stateId = null;
    private Long districtId = null;
    private Long localBodyId = null;
    private Long constituencyId = null;
    private Long localBodyElectionTypeId = null;
    private RegistrationVO regVO = null;
    transient private HttpSession session;
    private Integer resultValue;
    private String tempVar;
    public final static String TASK = "task";
    public final static String USERNAME = "userName";
        
   	public ResultStatus getResults() {
		return results;
	}
	public void setResults(final ResultStatus results) {
		this.results = results;
	}
	public Integer getResultValue() {
		return resultValue;
	}
	public void setResultValue(final Integer resultValue) {
		this.resultValue = resultValue;
	}
	public void setSession(final HttpSession session) {
		this.session = session;
	}
	public RegistrationVO getRegVO() {
		return regVO;
	}
	public void setRegVO(final RegistrationVO regVO) {
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
	public void setRegistrationId(final Long registrationId) {
		this.registrationId = registrationId;
	}
	public void setResult(final Long result) {
		this.result = result;
	}
	
	public IAnanymousUserService getAnanymousUserService() {
		return ananymousUserService;
	}
	public void setAnanymousUserService(final IAnanymousUserService ananymousUserService) {
		this.ananymousUserService = ananymousUserService;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(final HttpServletRequest request) {
		this.request = request;
	}
	
	public String getTask() {
		return task;
	}
	public void setTask(final String task) {
		this.task = task;
	}
	public org.json.JSONObject getJObj() {
		return jObj;
	}
	public void setJObj(final org.json.JSONObject obj) {
		jObj = obj;
	}
	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;
	}

	public List<String> getGender() {
		return gender;
	}
	public void setGender(final List<String> gender) {
		this.gender = gender;
	}
	
	public String getRedirectLoc() {
		return redirectLoc;
	}
	public void setRedirectLoc(final String redirectLoc) {
		this.redirectLoc = redirectLoc;
	}
	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(final Long stateId) {
		this.stateId = stateId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(final Long districtId) {
		this.districtId = districtId;
	}
	public Long getLocalBodyId() {
		return localBodyId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(final String email) {
		this.email = email;
	}
	public void setLocalBodyId(final Long localBodyId) {
		this.localBodyId = localBodyId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(final Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getLocalBodyElectionTypeId() {
		return localBodyElectionTypeId;
	}
	public void setLocalBodyElectionTypeId(final Long localBodyElectionTypeId) {
		this.localBodyElectionTypeId = localBodyElectionTypeId;
	}
	
	
	public IMailService getMailService() {
		return mailService;
	}
	public void setMailService(final IMailService mailService) {
		this.mailService = mailService;
	}
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	
	public void setRegionServiceDataImp(final IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(final IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	public String getTempVar() {
		return tempVar;
	}
	public void setTempVar(final String tempVar) {
		this.tempVar = tempVar;
	}
	public String execute(){
		
		if(prepopulate){
			return Action.SUCCESS;
		}
		if(gender.isEmpty()){
			gender.add("Male");
			gender.add("Female");
		}		
		
		final List<SelectOptionVO> profileOpts = ananymousUserService.findAllProfileOptsAvailableInDB();
		final List<SelectOptionVO> states = staticDataService.getParticipatedStatesForAnElectionType(2l);
		final List<SelectOptionVO> districts = new ArrayList<SelectOptionVO>(0);
		final List<SelectOptionVO> constituencies = new ArrayList<SelectOptionVO>(0);
		
		final HttpSession session = request.getSession();
		session.setAttribute("gender", gender);
		session.setAttribute("profileOpts", profileOpts);
		session.setAttribute("states", states);
		session.setAttribute("districts", districts);
		session.setAttribute("constituencies", constituencies);
		
		return Action.SUCCESS;
	}
	
	
	public String ajaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			LOG.error("Exception rised in ajaxHandler",e);
		}
		
		if(jObj.getString(TASK).equalsIgnoreCase("saveUserEmailAndSendPwd"))
		{
			results = ananymousUserService.saveEmailForAUser(jObj.getString(USERNAME),jObj.getString("email"));
		}
		else if(jObj.getString(TASK).equalsIgnoreCase("saveUserEmailAndsetAsUserName"))
		{
			results = ananymousUserService.saveEmailAndSetAsUserName(jObj.getString(USERNAME),jObj.getString("email"));
		}
		
		
		return SUCCESS;
	}
	public String checkForUserNameAvailability(){

		try {
			jObj = new JSONObject(getTask());
			LOG.info(jObj);
		} catch (Exception e) {
			LOG.error("Exception rised in checkForUserNameAvailability",e);
		}
	
		result = Long.valueOf(ananymousUserService.changeForUserNameAsEmail(jObj.getString(USERNAME)).getResultCode());
		return SUCCESS;
	}
	
	public String checkForUserNameAvailabilityForFreashUser(){

		try {
			jObj = new JSONObject(getTask());
			LOG.info(jObj);
		} catch (Exception e) {
			LOG.error("Exception rised in checkForUserNameAvailabilityForFreashUser",e);
		}
		
		result =  Long.valueOf(ananymousUserService.checkForUserNameAvailabilityForFreashUser(jObj.getString(USERNAME)).getResultCode());
		 
		return SUCCESS;
	}
	public String recoverPasswordUsingLink(){
		regVO = new RegistrationVO();
		try {
			jObj = new JSONObject(getTask());
			if(jObj.getString(TASK).equalsIgnoreCase("forgotPassword"))
			{
				
				regVO = ananymousUserService.getUserDetailsToRecoverPassword(jObj.getString(USERNAME));	
				
				if(regVO.getEmail()== null || regVO.getEmail().equals(" ")){
					return SUCCESS;				
				}else
				{
					email = regVO.getEmail();
				 	final String requestURL= request.getRequestURL().toString();
					String requestFrom = null;
					
					if(requestURL.contains(IConstants.PARTYANALYST_SITE)){
						requestFrom = IConstants.SERVER;
					}else{
						requestFrom = IConstants.LOCALHOST;
					}
					final ResultStatus resultStatus = mailService.sendMailToUserToRecoverPassword(regVO,requestFrom);
				 
					if(resultStatus.getResultCode() == 1){
					   regVO = null;
					}
					return SUCCESS;
				}
			}
		}
		catch (Exception e) {
			LOG.error("Exception rised in recoverPasswordUsingLink",e);
		}
		return SUCCESS;
		
	}
	public String recoverPassword(){
		
		regVO = new RegistrationVO();
		try {
			jObj = new JSONObject(getTask());
			if(jObj.getString(TASK).equalsIgnoreCase("forgotPassword"))
			{
				
				regVO = ananymousUserService.getUserDetailsToRecoverPassword(jObj.getString(USERNAME));	
				
				if(regVO.getEmail()== null || regVO.getEmail().equals(" ")){
					return SUCCESS;				
				}else
				{
					email = regVO.getEmail();
				 	final String requestURL= request.getRequestURL().toString();
					String requestFrom = null;
					
					if(requestURL.contains(IConstants.PARTYANALYST_SITE)){
						requestFrom = IConstants.SERVER;
					}else{
						requestFrom = IConstants.LOCALHOST;
					}
					final ResultStatus resultStatus = mailService.sendMailToUserToRecoverPassword(regVO,requestFrom);
				 
					if(resultStatus.getResultCode() == 1){
					 regVO = null;
					}
					return SUCCESS;
				}
			}
		}
		catch (Exception e) {
			LOG.error("Exception rised in recoverPassword",e);
		}
		return SUCCESS;
	}
	
	
	
		public String recoverPasswordForUser(){
		
		regVO = new RegistrationVO();
		try {
			jObj = new JSONObject(getTask());
			if(jObj.getString(TASK).equalsIgnoreCase("recoverPassword"))
			{
				
				regVO = ananymousUserService.getUserDetailsToRecoverPassword(jObj.getString(USERNAME));	
				
				if(regVO.getEmail()== null || regVO.getEmail().equals(" ")){
					return SUCCESS;
				
				}else
				{
					email = regVO.getEmail();
					final String requestURL= request.getRequestURL().toString();
					String requestFrom = null;
					
					if(requestURL.contains(IConstants.PARTYANALYST_SITE)){
						requestFrom = IConstants.SERVER;
					}else{
						requestFrom = IConstants.LOCALHOST;
					}
					final ResultStatus resultStatus = mailService.sendMailToUserToRecoverPassword(regVO,requestFrom);
				 
					if(resultStatus.getResultCode() == 1){
					 regVO = null;
					}
					return SUCCESS;
				}
			}
		}
		catch (Exception e) {
			LOG.error("Exception rised in recoverPasswordForUser",e);
		}
		return SUCCESS;
	}
	
	
	public String changeUserNameToEmail(){
		regVO = new RegistrationVO();
		regVO=(RegistrationVO)session.getAttribute("USER");
		final Long userId=regVO.getRegistrationID();
		
		try {
			jObj = new JSONObject(getTask());
			if(jObj.getString(TASK).equalsIgnoreCase("changeAnanymousUserNameToEmail")){
				
				resultValue=ananymousUserService.saveUserDetailsToChangeUserNameToEmail(userId,jObj.getString(USERNAME));	
				regVO = ananymousUserService.getUserDetailsToRecoverPassword(jObj.getString(USERNAME));
				 email = regVO.getEmail();
				 LOG.info("email"+email);
					final String requestURL= request.getRequestURL().toString();
					String requestFrom = "";
					if(requestURL.contains("www.partyanalyst.com")){
						requestFrom = IConstants.SERVER;
					}else{
						requestFrom = IConstants.LOCALHOST;
					}
					final ResultStatus resultStatus = mailService.sendMailToUserToRecoverPassword(regVO,requestFrom);
				 
				 if(resultStatus.getResultCode() == 1){
					 regVO = null;
				 }
				return SUCCESS;
			}
			
			
		}
		catch (Exception e) {
			LOG.error("Exception rised in changeUserNameToEmail",e);
		}
		return SUCCESS;
	
	}
	
	public void prepare() {
		
		registrationId = 0l;
		session = request.getSession();
		regVO = (RegistrationVO) session.getAttribute("USER");
		
		if(regVO != null){
			registrationId = regVO.getRegistrationID();
		}
        if( registrationId.intValue() == 0) {
        	regVO = new RegistrationVO();
        }else 
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
		     
    public void prepopulateLocations(final RegistrationVO  regVO){
    	
    	final HttpSession session = request.getSession();
    	
    	final List<String> gender = new ArrayList<String>();
    	final List<SelectOptionVO> profileOpts = ananymousUserService.findAllProfileOptsAvailableInDB(); 
    	final List<SelectOptionVO> states = regionServiceDataImp.getStatesByCountry(1l);
		//List<SelectOptionVO> districts = regionServiceDataImp.getDistrictsByStateID(Long.parseLong(regVO.getState()));
		List<SelectOptionVO> constituencies = new ArrayList<SelectOptionVO>();
		if(regVO.getState() != null){
	     constituencies = staticDataService.getConstituenciesByElectionTypeAndStateId(2l, Long.parseLong(regVO.getState())).getConstituencies();		
		}
		gender.add("Male");
		gender.add("Female");
		
    	session.setAttribute("profileOpts", profileOpts);
		session.setAttribute("gender", gender);
		session.setAttribute("states", states);
		//session.setAttribute("districts", districts);
		session.setAttribute("constituencies", constituencies);
    }
    
    public String removeCadreImage(){
    	LOG.debug("entered into removeCadreImage() business method in AnanymousUserAction class");
    	session = request.getSession();
    	final RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
  
    	 final String param = getTask();
		try{
			jObj = new JSONObject(param);
			if(jObj.getString(TASK).equalsIgnoreCase("removeCadreImage")){
				prepopulate = staticDataService.removeCadreImage(jObj.getLong("cadreId"),regVO.getRegistrationID());
			}
		}catch(Exception e){
			LOG.debug("error occured in removeCadreImage() business method in AnanymousUserAction class");
			LOG.error("Exception rised in removeCadreImage",e);	
		}
    	return Action.SUCCESS;
    }
}
