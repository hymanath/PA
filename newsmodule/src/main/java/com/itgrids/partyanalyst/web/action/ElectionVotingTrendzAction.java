package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;



public class ElectionVotingTrendzAction extends ActionSupport implements ServletRequestAware{
	
	private List<SelectOptionVO> stateList;
	private String task = null;
	JSONObject jObj = null;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> mandalList;
	private List<SelectOptionVO> constituenciesList;
	private List<SelectOptionVO> electionType;
	private List<String> electionYears;	
	private IStaticDataService staticDataService;
	private HttpSession session;
	private HttpServletRequest request;
	
	/*
	private List<MandalVO> mandalVO;
	private RegistrationVO regVO = null;
	private IElectionTrendzService electionTrendzService; 
	private IRegistrationService registrationService;
	private MandalCompleteElectionTrendzVO mandalCompleteElectionTrendzVO;
	private ElectionTrendzReportVO electionTrendzReportVO;
	private IMailService mailService;

	private Long resultStr;
	private Integer resultValue;
	*/
	
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}
	public List<SelectOptionVO> getStateList() {
		return stateList;
	}

	public void setStateList(List<SelectOptionVO> stateList) {
		this.stateList = stateList;
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

	public List<SelectOptionVO> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<SelectOptionVO> districtList) {
		this.districtList = districtList;
	}

	public List<SelectOptionVO> getMandalList() {
		return mandalList;
	}

	public void setMandalList(List<SelectOptionVO> mandalList) {
		this.mandalList = mandalList;
	}

	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}

	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}

	public List<SelectOptionVO> getElectionType() {
		return electionType;
	}

	public void setElectionType(List<SelectOptionVO> electionType) {
		this.electionType = electionType;
	}

	public List<String> getElectionYears() {
		return electionYears;
	}

	public void setElectionYears(List<String> electionYears) {
		this.electionYears = electionYears;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String execute() throws Exception{
		
		Log.debug("Entered into election voting trendz action");
		
		stateList = new ArrayList<SelectOptionVO>(2);
		stateList.add(0,new SelectOptionVO(0l,"Select State"));
		stateList.add(1,new SelectOptionVO(1l,"Andra Pradesh"));
		
		districtList = new ArrayList<SelectOptionVO>();
		districtList.add(0,new SelectOptionVO(0l,"Select District"));	
		
		mandalList = new ArrayList<SelectOptionVO>();
		mandalList.add(0,new SelectOptionVO(0l,"Select Mandal"));	
		
		constituenciesList = new ArrayList<SelectOptionVO>();
		constituenciesList.add(0,new SelectOptionVO(0l,"Select Constituency"));
				
		electionType = new ArrayList<SelectOptionVO>();
		electionType.add(0,new SelectOptionVO(0L,"Select"));
		electionType.add(1,new SelectOptionVO(1L,"Parliament"));
		electionType.add(2,new SelectOptionVO(2L,"Assembly"));		
		
		electionYears = new ArrayList<String>();
		electionYears.add("Select");
		electionYears.add("2009");
		electionYears.add("2004");
		
		return Action.SUCCESS;
	}
	
	public String getConstituenciesByElectionTypeIdAndStateId() throws Exception
	{
		String param = null;
		String electionTypeId = "";
		String stateId = "";
		param = getTask();
		
		try {
			jObj = new JSONObject(param);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("getConstituencies"))
		{
			electionTypeId = jObj.getString("electionTypeId");
			stateId = jObj.getString("stateId");
		}
		
		constituenciesList = staticDataService.getConstituenciesByElectionTypeAndStateId(new Long(electionTypeId), new Long(stateId)).getConstituencies();
		if(constituenciesList!=null && constituenciesList.size()>1){
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(0l);
			selectOptionVO.setName("Select Constituency");
			constituenciesList.add(0,selectOptionVO);
		}
		return Action.SUCCESS;
	}

	
	
	/*
	public String getElectionYearsByElectionId() throws Exception
	{
		String param = null;
		String electionTypeId = "";
		param = getTask();
		
		try {
			jObj = new JSONObject(param);			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(jObj.getString("electionType") != null && jObj.getString("electionType").length() != 0)
			electionTypeId = jObj.getString("electionType");
		
		//staticDataService.getElection
		return Action.SUCCESS;
	}
	
	public String getDistrictsByStateId() throws Exception
	{
		String param = null;
		String stateId = "";
		param = getTask();
		
		try {
			jObj = new JSONObject(param);			
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(jObj.getString("stateId") != null && jObj.getString("task").equalsIgnoreCase("getDistricts"))
		{
			stateId = jObj.getString("stateId");		
			districtList = staticDataService.getDistricts(new Long(stateId));
		}
		return Action.SUCCESS;
	}
	
	
	public String checkForRegisteredUserNameAvailability()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		resultStr = new Long(registrationService.checkForUserNameAvalilability(jObj.getString("userName")).getResultCode());
		return SUCCESS;
	}
	
	public String checkForUserNameAvailabilityForFreashUser(){

		try {
			jObj = new JSONObject(getTask());
			System.out.println(jObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resultStr =  new Long(registrationService.checkForUserNameAvailabilityForFreashUser(jObj.getString("userName")).getResultCode());
		 
		return SUCCESS;
	}
	
	public String changeRegisteredUserNameToEmail(){
		session = request.getSession();
		regVO = new RegistrationVO();
		regVO=(RegistrationVO)session.getAttribute("USER");
		Long userId=regVO.getRegistrationID();
		
		try {
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("changeRegisteredUserNameToEmail")){
				
				resultValue=registrationService.updateRegisteredUserDetailsToUserNameToEmail(userId,jObj.getString("userName"));	
				return SUCCESS;
			}
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
	
	public String getMandalsByDistrictId() throws Exception
	{
		String param = null;
		String districtId = "";
		param = getTask();
		
		try {
			jObj = new JSONObject(param);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("districtId") != null && jObj.getString("task").equalsIgnoreCase("getMandals"))
			districtId = jObj.getString("districtId");
		
		mandalVO = staticDataService.getMandalsForDistrict(new Long(districtId));
		return Action.SUCCESS;
	}
	
	public String getVotingTrendz() throws Exception
	{
		String param = null;		
		param = getTask();
		
		try {
			jObj = new JSONObject(param);	
			System.out.println("jObj================"+jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String stateId = jObj.getString("stateValue");
		String constituencyId = jObj.getString("constituencyVal");
		String electionTypeId = jObj.getString("electionTypeId");	
		
		electionTrendzReportVO = new ElectionTrendzReportVO();
		electionTrendzReportVO = electionTrendzService.getVotingTrendzForAnElection(new Long(0), new Long(electionTypeId), "0", new Long(stateId), new Long(1), new Long(constituencyId), new Long(0), new Long(0));
		
		
		return Action.SUCCESS;
	}

	*/
	
}
