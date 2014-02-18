package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IRegistrationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IUserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class AnanymousUserAction extends ActionSupport implements
ServletRequestAware, ModelDriven<RegistrationVO>, Preparable  {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(AnanymousUserAction.class);
	
	private HttpServletRequest request;
	private String task;
	private JSONObject jObj;
	private HttpSession session;
	private Long registrationId;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private RegistrationVO regVO = new RegistrationVO();
	private IRegistrationService registrationService;
	private IUserService userService;
	private Long result;
	private Boolean savedSuccessfully;
	private String userType;
	private String constituencyId;
	private String electionType; // parliament Type or assembly Type
	private List<SelectOptionVO> userTypeList;
	private List<SelectOptionVO> constituenciesList;
	private IStaticDataService staticDataService;
	private ConstituencyInfoVO constituencyInfoVO = null;
	private ConstituencyInfoVO parliamantConstis;
	private List<SelectOptionVO> districtsList;
	private Long districtId;
	private Long parliamentId;
	private Long assemblyId;
	private Long accesslevel;
	
	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}

	public ConstituencyInfoVO getConstituencyInfoVO() {
		return constituencyInfoVO;
	}

	public void setConstituencyInfoVO(ConstituencyInfoVO constituencyInfoVO) {
		this.constituencyInfoVO = constituencyInfoVO;
	}

	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}

	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public List<SelectOptionVO> getUserTypeList() {
		return userTypeList;
	}

	public void setUserTypeList(List<SelectOptionVO> userTypeList) {
		this.userTypeList = userTypeList;
	}

	public Boolean getSavedSuccessfully() {
		return savedSuccessfully;
	}


	public void setSavedSuccessfully(Boolean savedSuccessfully) {
		this.savedSuccessfully = savedSuccessfully;
	}


	public Long getResult() {
		return result;
	}


	public void setResult(Long result) {
		this.result = result;
	}


	public RegistrationVO getRegVO() {
		return regVO;
	}


	public void setRegVO(RegistrationVO regVO) {
		this.regVO = regVO;
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


	public IRegistrationService getRegistrationService() {
		return registrationService;
	}


	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public HttpSession getSession() {
		return session;
	}


	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getEmail() {
		return regVO.getEmail();
	}


	public void setEmail(String email) {
		this.regVO.setEmail(email);
	}


	public Long getRegistrationId() {
		return regVO.getRegistrationID();
	}


	public void setRegistrationId(Long registrationId) {
		this.regVO.setRegistrationID(registrationId);
	}


	public String getUserName() {
		return regVO.getUserName();
	}


	public void setUserName(String userName) {
		this.regVO.setUserName(userName);
	}


	public String getFirstName() {
		return this.regVO.getFirstName();
	}


	public void setFirstName(String firstName) {
		 this.regVO.setFirstName(firstName);
	}


	public String getLastName() {
		return regVO.getLastName();
	}


	public void setLastName(String lastName) {
		this.regVO.setLastName(lastName);
	}


	public String getPassword() {
		return regVO.getPassword();
	}


	public void setPassword(String password) {
		this.regVO.setPassword(password);
	}


	//@Override
	public void prepare() throws Exception {
		
	}


	//@Override
	public RegistrationVO getModel() {
	
		return null;
	}


	//@Override
	public void setServletRequest(HttpServletRequest arg) {
	this.request = arg;
	}
   

	public IUserService getUserService() {
		return userService;
	}


	public void setUserService(IUserService userService) {
		this.userService = userService;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}

	public ConstituencyInfoVO getParliamantConstis() {
		return parliamantConstis;
	}

	public void setParliamantConstis(ConstituencyInfoVO parliamantConstis) {
		this.parliamantConstis = parliamantConstis;
	}

	public List<SelectOptionVO> getDistrictsList() {
		return districtsList;
	}

	public void setDistrictsList(List<SelectOptionVO> districtsList) {
		this.districtsList = districtsList;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getParliamentId() {
		return parliamentId;
	}

	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
	}

	public Long getAssemblyId() {
		return assemblyId;
	}

	public void setAssemblyId(Long assemblyId) {
		this.assemblyId = assemblyId;
	}

	public Long getAccesslevel() {
		return accesslevel;
	}

	public void setAccesslevel(Long accesslevel) {
		this.accesslevel = accesslevel;
	}

	public String execute()
	{
		/*HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		userType = userService.checkForUserType(user.getRegistrationID());
		
		if(userType.equalsIgnoreCase("admin"))
		{
			return Action.SUCCESS;
		}
		else {
			return Action.ERROR;
		}*/
		
		return Action.SUCCESS;
	}
	
	public String saveUserData()
	{
		String userType = "";
		String type = "";
		//String registeredUserType="";
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user != null)
		{
			regVO.setRegistrationID(user.getRegistrationID());
			userType = user.getUserType();
			type     = user.getUserAccessType();
			if(getUserType() != null){
			    if(getUserType().equalsIgnoreCase("1")){
				  regVO.setUserAccessType("Admin");
				 	
				  regVO.setAccessType("STATE");
				  regVO.setAccessValue("1");
				  
			    }
				if(getUserType().equalsIgnoreCase("2") || getUserType().equalsIgnoreCase("4") ||  getUserType().equalsIgnoreCase("3")){
					if(getUserType().equalsIgnoreCase("2"))
					{
						regVO.setUserAccessType("SubUser");
					}
					if(getUserType().equalsIgnoreCase("4"))
					{
						regVO.setUserAccessType("pfb");
					}
					if(getUserType().equalsIgnoreCase("3"))
					{
						regVO.setUserAccessType("debate");
					}
					if(accesslevel.longValue() == 1l){
						regVO.setAccessType("STATE");
						regVO.setAccessValue("1");
					}else if(accesslevel.longValue() == 2l){
						regVO.setAccessType("DISTRICT");
						regVO.setAccessValue(districtId.toString());
					}else if(accesslevel.longValue() == 3l){
						regVO.setAccessType("MP");
						regVO.setAccessValue(parliamentId.toString());
					}else if(accesslevel.longValue() == 4l){
						regVO.setAccessType("MLA");
						regVO.setAccessValue(assemblyId.toString());
					}
				}
			}
		}
		if(!type.equalsIgnoreCase("admin"))
		{
			return Action.ERROR;
		}
		if(regVO.getEmail() != null)
			savedSuccessfully = registrationService.saveDataIntoUser(regVO,userType);
		else
			savedSuccessfully = false;
		constituencyInfoVO = new ConstituencyInfoVO();		
		constituencyInfoVO = staticDataService.getConstituenciesByElectionTypeAndStateId(2L,1L);
		//constituencyInfoVO.getConstituencies().add(0,new SelectOptionVO(0L,"Select Assembly Constituency"));
		parliamantConstis = staticDataService.getConstituenciesByElectionTypeAndStateId(1L,1L);
		//parliamantConstis.getConstituencies().add(0,new SelectOptionVO(0L,"Select Parliament Constituency"));
		districtsList =  staticDataService.getDistricts(1l);
		//districtsList.add(0,new SelectOptionVO(0L,"Select District"));
		userTypeList = new ArrayList<SelectOptionVO>();		
		userTypeList.add(0,new SelectOptionVO(0l,"Select User Type"));
		userTypeList.add(1,new SelectOptionVO(1l,"Admin"));
		userTypeList.add(2,new SelectOptionVO(2l,"SubUser"));
		userTypeList.add(3,new SelectOptionVO(3l,"debate"));
		userTypeList.add(4,new SelectOptionVO(4l,"pfb"));
        return Action.SUCCESS;
			
	}
	
	public String checkForUserNameAvailabilityForFreashUser(){

		try {
			jObj = new JSONObject(getTask());
			System.out.println(jObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		result =  new Long(registrationService.checkForUserNameAvailabilityForFreashUser(jObj.getString("userName")).getResultCode());
		 
		return SUCCESS;
	}

	public String getConstituenciesByElectionType(){
		
		log.debug(" entered into AnanymousUserAction getConstituenciesByElectionType method : ");
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		constituencyInfoVO= new ConstituencyInfoVO();
		if(user == null)
			return Action.ERROR;
			
		try{
				jObj = new JSONObject(getTask());
				if(jObj.getString("task").equalsIgnoreCase("getCosntituencesByElectionType")){
				Long electionId = Long.valueOf(jObj.getString("electionTypeId"));
				Long stateId = Long.valueOf(jObj.getString("stateId"));
					constituencyInfoVO = staticDataService.getConstituenciesByElectionTypeAndStateId(electionId,stateId);
				}
				
			}catch(Exception e){
			log.debug(" exception occured in AnanymousUserAction getConstituenciesByElectionType method : ",e);
		}			
		return Action.SUCCESS;
	}
}
