package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserAccessRegionVO;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IRegistrationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IUserAccessRegionService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class UserAccessRegionAction extends ActionSupport implements ServletContextAware, ServletRequestAware{
	
	public HttpServletRequest request;
    public ServletContext context;
    HttpSession session;
    private IRegistrationService registrationService;
    private EntitlementVO allRegisteredUsersData;
    private IUserAccessRegionService userAccessRegionService;
    private UserAccessRegionVO userAccessRegionVO;
    JSONObject jObj = null;
    private String task;
    private IStaticDataService staticDataService;
    private IRegionServiceData regionServiceDataImp;
    private List<SelectOptionVO> constituenciesList;
    
    
    public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}
	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}
    public UserAccessRegionVO getUserAccessRegionVO() {
		return userAccessRegionVO;
	}
	public void setUserAccessRegionVO(UserAccessRegionVO userAccessRegionVO) {
		this.userAccessRegionVO = userAccessRegionVO;
	}
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public IRegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	public EntitlementVO getAllRegisteredUsersData() {
		return allRegisteredUsersData;
	}

	public void setAllRegisteredUsersData(EntitlementVO allRegisteredUsersData) {
		this.allRegisteredUsersData = allRegisteredUsersData;
	}
	
	public IUserAccessRegionService getUserAccessRegionService() {
		return userAccessRegionService;
	}
	public void setUserAccessRegionService(
			IUserAccessRegionService userAccessRegionService) {
		this.userAccessRegionService = userAccessRegionService;
	}
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	
	
	public String execute(){
		
		session = request.getSession();
		RegistrationVO	registrationVO = (RegistrationVO)session.getAttribute(IConstants.USER);
		 if(registrationVO!= null){
		 if(registrationVO.getIsAdmin().equals("true"))
		   allRegisteredUsersData = registrationService.getAllRegisterdUsers();
		}
		else
			return "error";
		return Action.SUCCESS;
	}
	
	public String getAllAccessRegionDetails(){

		session = request.getSession();
		RegistrationVO	registrationVO = (RegistrationVO)session.getAttribute(IConstants.USER);
		 if(registrationVO!= null){
		 if(registrationVO.getIsAdmin().equals("true")){
		
		try {
			jObj=new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    if(jObj.getString("task").equalsIgnoreCase("getAllAccessRegionDetails"))
	    {	
		     userAccessRegionVO = userAccessRegionService.getAccessDetailsByUserId(jObj.getLong("userId"));
	    }     
	    else if(jObj.getString("task").equalsIgnoreCase("getCountryDetails"))
	    {
	    	 userAccessRegionVO = userAccessRegionService.getCountryDetailsByUserId(jObj.getLong("userId"));
	    }	
	    else if(jObj.getString("task").equalsIgnoreCase("getStateDetails"))
	    {
	    	 userAccessRegionVO = userAccessRegionService.getStateDetailsByUserId(jObj.getLong("userId"));
	    }
	    else if(jObj.getString("task").equalsIgnoreCase("getStateDetailForDistrict") || jObj.getString("task").equalsIgnoreCase("getStateDetailForConstituency"))
	    {
	    	userAccessRegionVO = userAccessRegionService.getStateDetailForDistrictAndAssConstituency(jObj.getLong("userId"));
	    }
	    else if(jObj.getString("task").equalsIgnoreCase("getAllDistrictDetailByUserIdStateId"))
	    {
	    	userAccessRegionVO = userAccessRegionService.getDistrictDetailsByStateIdUserId(jObj.getLong("stateId"),jObj.getLong("userId"));
	    }
	    else if(jObj.getString("task").equalsIgnoreCase("getAssemblyConsDetailsByStateIdUserId"))
	    {
	    	userAccessRegionVO = userAccessRegionService.getAssemblyConsDetailsByStateIdUserId(jObj.getLong("stateId"),jObj.getLong("userId"));
	    }
	    else if(jObj.getString("task").equalsIgnoreCase("getParliConsDetailsByUserId"))
	    {
	    	userAccessRegionVO = userAccessRegionService.getParliConsDetailsByUserId(jObj.getLong("userId"));
	    }
	    
	  }
   } 
				
		 
		return Action.SUCCESS;
	}
	
	public String saveUserAccessRegionDetail(){
		session = request.getSession();
		RegistrationVO	registrationVO = (RegistrationVO)session.getAttribute(IConstants.USER);
		 if(registrationVO!= null){
		 if(registrationVO.getIsAdmin().equals("true")){
		
		try {
			jObj=new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(jObj.getString("task").equalsIgnoreCase("saveCountryAccessDetails"))
		{
			userAccessRegionVO = userAccessRegionService.saveUserCountryAccessDetail(jObj.getLong("userId"),jObj.getString("countryIds"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("saveStateAccessDetails"))
		{
			userAccessRegionVO = userAccessRegionService.saveUserStateAccessDetail(jObj.getLong("userId"),jObj.getString("stateIds"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("saveDistrictAccessDetails"))
		{
			userAccessRegionVO = userAccessRegionService.saveUserDistrictAccessDetail(jObj.getLong("userId"),jObj.getLong("stateId"),jObj.getString("districtIds"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("saveAssmbConstAccessDetails"))
		{
			userAccessRegionVO = userAccessRegionService.saveUserAssemblyConstituencyAccessDetail(jObj.getLong("userId"),jObj.getLong("stateId"),jObj.getString("constituencyIds"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("saveParlConstAccessDetails"))
		{
			userAccessRegionVO = userAccessRegionService.saveUserParliamentConstituencyAccessDetail(jObj.getLong("userId"),jObj.getString("constituencyIds"));
		}
		
	    
	 }
	} 
		return Action.SUCCESS;
	}
	

}
