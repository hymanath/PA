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
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IRegistrationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IElectionTrendzService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.BoothTotalVotesVO;
import com.itgrids.partyanalyst.dto.ConstituencyWiseBoothsInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyWisePartyResultsForMandal;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.MandalCompleteElectionTrendzVO;
import com.itgrids.partyanalyst.dto.MandalElectionTrendzVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionTrendzReportVO;
import com.itgrids.partyanalyst.dto.ElectionTrendzInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;



public class ElectionVotingTrendzAction extends ActionSupport implements ServletRequestAware{
	
	
	private HttpServletRequest request;
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> mandalList;
	private List<SelectOptionVO> constituenciesList;
	private List<SelectOptionVO> electionType;
	private List<String> electionYears;
	private List<MandalVO> mandalVO;
	private RegistrationVO regVO = null;
	private IStaticDataService staticDataService;
	private IElectionTrendzService electionTrendzService; 
	private IRegistrationService registrationService;
	private String task = null;
	JSONObject jObj = null;
	private MandalCompleteElectionTrendzVO mandalCompleteElectionTrendzVO;
	private ElectionTrendzReportVO electionTrendzReportVO;
	private IMailService mailService;
	private HttpSession session;
	private Long resultStr;
	private Integer resultValue;
		
	
	
	public IMailService getMailService() {
		return mailService;
	}

	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}

	public HttpSession getSession() {
		return session;
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

	public Integer getResultValue() {
		return resultValue;
	}

	public void setResultValue(Integer resultValue) {
		this.resultValue = resultValue;
	}

	public org.json.JSONObject getJObj() {
		return jObj;
	}
	public void setJObj(org.json.JSONObject obj) {
		jObj = obj;
	}
	
	public Long getResultStr() {
		return resultStr;
	}

	public void setResultStr(Long resultStr) {
		this.resultStr = resultStr;
	}

	public IRegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	public IElectionTrendzService getElectionTrendzService() {
		return electionTrendzService;
	}

	public void setElectionTrendzService(
			IElectionTrendzService electionTrendzService) {
		this.electionTrendzService = electionTrendzService;
	}
	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}

	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}
	public ElectionTrendzReportVO getElectionTrendzReportVO() {
		return electionTrendzReportVO;
	}

	public void setElectionTrendzReportVO(
			ElectionTrendzReportVO electionTrendzReportVO) {
		this.electionTrendzReportVO = electionTrendzReportVO;
	}
	public MandalCompleteElectionTrendzVO getMandalCompleteElectionTrendzVO() {
		return mandalCompleteElectionTrendzVO;
	}

	public void setMandalCompleteElectionTrendzVO(
			MandalCompleteElectionTrendzVO mandalCompleteElectionTrendzVO) {
		this.mandalCompleteElectionTrendzVO = mandalCompleteElectionTrendzVO;
	}

	public List<SelectOptionVO> getElectionType() {
		return electionType;
	}

	public void setElectionType(List<SelectOptionVO> electionType) {
		this.electionType = electionType;
	}
	public List<MandalVO> getMandalVO() {
		return mandalVO;
	}

	public void setMandalVO(List<MandalVO> mandalVO) {
		this.mandalVO = mandalVO;
	}
	public List<String> getElectionYears() {
		return electionYears;
	}

	public void setElectionYears(List<String> electionYears) {
		this.electionYears = electionYears;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	public List<SelectOptionVO> getStateList() {
		return stateList;
	}

	public void setStateList(List<SelectOptionVO> stateList) {
		this.stateList = stateList;
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
		
	public void setServletRequest(HttpServletRequest request) {
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
			/*		String requestURL= request.getRequestURL().toString();
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
			}*/
			
			
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

	
	
}
