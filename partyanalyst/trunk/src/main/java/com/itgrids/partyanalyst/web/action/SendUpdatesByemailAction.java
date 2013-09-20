package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;
import org.springframework.web.context.ServletContextAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.ISendUpdatesService;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.social.service.ISocialService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class SendUpdatesByemailAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private ISendUpdatesService sendUpdatesService;
	private ISmsService smsCountrySmsService;
	private List<RegistrationVO> regVO = null;
	private JSONObject jObj;
	private String task=null;
	private ResultStatus resultstatus;
	//private List<String>  userEmailId;
	//private String textArea;
	//private String subject;
	private	List<SelectOptionVO> list;
    private List<RegistrationVO> regVoForEmail;
	private ISocialService socialService;
    private IRegionServiceData regionServiceDataImp;
    private String result;
    
    private String description;
    private String userIds;
    private String subject;
    
	private String startIndex;
	private String dir;
	private String sort;
	private String selectedState;
	private String selectedDistrict;
	private String selectedConstituency;
	private String userType;
	private String locationScope;
	private String freuserType;
	private RegistrationVO registrationVO = null;
    
	public RegistrationVO getRegistrationVO() {
		return registrationVO;
	}
	public void setRegistrationVO(RegistrationVO registrationVO) {
		this.registrationVO = registrationVO;
	}
	public String getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getSelectedState() {
		return selectedState;
	}
	public void setSelectedState(String selectedState) {
		this.selectedState = selectedState;
	}
	public String getSelectedDistrict() {
		return selectedDistrict;
	}
	public void setSelectedDistrict(String selectedDistrict) {
		this.selectedDistrict = selectedDistrict;
	}
	public String getSelectedConstituency() {
		return selectedConstituency;
	}
	public void setSelectedConstituency(String selectedConstituency) {
		this.selectedConstituency = selectedConstituency;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(String locationScope) {
		this.locationScope = locationScope;
	}
	public String getFreuserType() {
		return freuserType;
	}
	public void setFreuserType(String freuserType) {
		this.freuserType = freuserType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/*  public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}*/
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	/*public List<String> getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(List<String> userEmailId) {
		this.userEmailId = userEmailId;
	}
	public String getTextArea() {
		return textArea;
	}
	public void setTextArea(String textArea) {
		this.textArea = textArea;
	}*/

	

    
	public List<SelectOptionVO> getList() {
		return list;
	}
	public void setList(List<SelectOptionVO> list) {
		this.list = list;
	}
	public List<RegistrationVO> getRegVoForEmail() {
		return regVoForEmail;
	}
	public void setRegVoForEmail(List<RegistrationVO> regVoForEmail) {
		this.regVoForEmail = regVoForEmail;
	}
	public ISocialService getSocialService() {
		return socialService;
	}
	public void setSocialService(ISocialService socialService) {
		this.socialService = socialService;
	}
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
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
	public ResultStatus getResultstatus() {
		return resultstatus;
	}
	public void setResultstatus(ResultStatus resultstatus) {
		this.resultstatus = resultstatus;
	}
	public ISmsService getSmsCountrySmsService() {
		return smsCountrySmsService;
	}
	public void setSmsCountrySmsService(ISmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}
	public List<RegistrationVO> getRegVO() {
		return regVO;
	}
	public void setRegVO(List<RegistrationVO> regVO) {
		this.regVO = regVO;
	}
	public ISendUpdatesService getSendUpdatesService() {
		return sendUpdatesService;
	}
	public void setSendUpdatesService(ISendUpdatesService sendUpdatesService) {
		this.sendUpdatesService = sendUpdatesService;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	
	
	public String execute()
	{
		
		return Action.SUCCESS;
		
	}
	
	public String callAjaxforSendemails(){
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
			
			if(jObj.getString("task").equalsIgnoreCase("getStateNames"))
			{
				list = socialService.getAllStateDetails();
			}	
			
			else if(jObj.getString("task").equalsIgnoreCase("getDistrictNames")){
				list = regionServiceDataImp.getDistrictsByStateID(jObj.getLong("stateId"));
			}
			
			else if(jObj.getString("task").equalsIgnoreCase("getConstituencyNames")){
				list= regionServiceDataImp.getConstituenciesByDistrictIDs(jObj.getLong("districtId"));
			}
			
		/*	else if(jObj.getString("task").equalsIgnoreCase("getUsersForSendingEmails")){
				regVoForEmail=sendUpdatesService.getUsersForSendingEmails(jObj.getLong("selectedState"),jObj.getLong("selectedDistrict"),jObj.getLong("selectedConstituency"),jObj.getLong("userType"),jObj.getLong("locationScope"));
			}
		*/	
			/*else if(jObj.getString("task").equalsIgnoreCase("sendEmailsForUserIds")){
				userEmailId=new ArrayList<String>();
				textArea=jObj.getString("txtAreaValue");
				subject=jObj.getString("subject");
				JSONArray values = jObj.getJSONArray("selectedElmts");
				for (int i = 0; i < values.length(); i++) {
					userEmailId.add(values.getString(i));
				}*/
			/*else {
				List<String> userEmailIdsList = new ArrayList<String>();
				String[] strArr = null;
				userIds = userIds.substring(0,userIds.length()-1);
				strArr = userIds.split(",");
				
				for(String emailStr : strArr){
					userEmailIdsList.add(emailStr);
				}
				
				result=sendUpdatesService.sendEmailsFromAdminToUsers(userEmailIdsList,description,subject);
				
			}*/
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
		
	public String getUsersForemail(){
		
		try{
		registrationVO = new RegistrationVO();
		Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
		String order = request.getParameter("dir");
		String sortBy = request.getParameter("sort");
		Integer maxRecords = Integer.parseInt(request.getParameter("results"));
		
		RegistrationVO regiVO = new RegistrationVO();
		regiVO.setStateId(Long.valueOf(selectedState.trim()));
		regiVO.setDistrictId(Long.valueOf(selectedDistrict.trim()));
		regiVO.setConstituencyId(Long.valueOf(selectedConstituency.trim()));
		regiVO.setAccessType(userType.trim());
		regiVO.setAccessValue(locationScope.trim());
		regiVO.setUserType(freuserType.trim());
		
		regVoForEmail=sendUpdatesService.getUsersForSendingEmail(regiVO,order,sortBy,startIndex,maxRecords);
		
		if(regVoForEmail != null && regVoForEmail.size() >0){	
		registrationVO.setTotalCount(regVoForEmail.get(0).getTotalCount());
		regVoForEmail.remove(0);
		registrationVO.setRegisteredUsersList(regVoForEmail);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	public String ajaxForSendingEmail(){
		try {
		List<String> userEmailIdsList = new ArrayList<String>();
		String[] strArr = null;
		userIds = userIds.substring(0,userIds.length()-1);
		strArr = userIds.split(",");
		
		for(String emailStr : strArr){
			userEmailIdsList.add(emailStr);
		}
		
		result=sendUpdatesService.sendEmailsFromAdminToUsers(userEmailIdsList,description,subject);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
}
