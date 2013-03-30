package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;


import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SmsVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.ISendUpdatesService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVoterReportService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.VotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.SmsResultVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
public class SendUpdatesBySMSAction  extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
	private List<SelectOptionVO> userAccessConstituencyList;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private IStaticDataService staticDataService;
	private HttpSession session;
	private List<SelectOptionVO> designationsList;
	private IRegionServiceData regionServiceDataImp;
	private SmsResultVO smsResultVO;
	private CadreManagementService cadreManagementService;
	private ISendUpdatesService sendUpdatesService;
	private IVotersAnalysisService votersAnalysisService;
	private ResultStatus result;
	private String task;
	JSONObject jObj;
	private VoterHouseInfoVO voterHouseInfoVO;
	private List<VoterHouseInfoVO> voterHouseInfoVOList;
	private String resultStr;
	private IVoterReportService voterReportService;
	private ConstituencyManagementVO constituencyManagementVO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private static final Logger log = Logger.getLogger(SendUpdatesBySMSAction.class);
	private VoterVO voterVO;
	private EntitlementsHelper entitlementsHelper;
	
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public List<VoterHouseInfoVO> getVoterHouseInfoVOList() {
		return voterHouseInfoVOList;
	}

	public void setVoterHouseInfoVOList(List<VoterHouseInfoVO> voterHouseInfoVOList) {
		this.voterHouseInfoVOList = voterHouseInfoVOList;
	}

	public VoterVO getVoterVO() {
		return voterVO;
	}

	public void setVoterVO(VoterVO voterVO) {
		this.voterVO = voterVO;
	}

	public IVoterReportService getVoterReportService() {
		return voterReportService;
	}

	public void setVoterReportService(IVoterReportService voterReportService) {
		this.voterReportService = voterReportService;
	}

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public ConstituencyManagementVO getConstituencyManagementVO() {
		return constituencyManagementVO;
	}

	public void setConstituencyManagementVO(
			ConstituencyManagementVO constituencyManagementVO) {
		this.constituencyManagementVO = constituencyManagementVO;
	}

	public VoterHouseInfoVO getVoterHouseInfoVO() {
		return voterHouseInfoVO;
	}

	public void setVoterHouseInfoVO(VoterHouseInfoVO voterHouseInfoVO) {
		this.voterHouseInfoVO = voterHouseInfoVO;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public ResultStatus getResult() {
		return result;
	}

	public void setResult(ResultStatus result) {
		this.result = result;
	}

	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	
	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public ISendUpdatesService getSendUpdatesService() {
		return sendUpdatesService;
	}

	public void setSendUpdatesService(ISendUpdatesService sendUpdatesService) {
		this.sendUpdatesService = sendUpdatesService;
	}

	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}
	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}
	public SmsResultVO getSmsResultVO() {
		return smsResultVO;
	}
	public void setSmsResultVO(SmsResultVO smsResultVO) {
		this.smsResultVO = smsResultVO;
	}
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	public List<SelectOptionVO> getDesignationsList() {
		return designationsList;
	}
	public void setDesignationsList(List<SelectOptionVO> designationsList) {
		this.designationsList = designationsList;
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
	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}
	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}
	public List<SelectOptionVO> getUserAccessConstituencyList() {
		return userAccessConstituencyList;
	}
	public void setUserAccessConstituencyList(
			List<SelectOptionVO> userAccessConstituencyList) {
		this.userAccessConstituencyList = userAccessConstituencyList;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}
	public String execute()
	{
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return INPUT;
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.ADMIN_PAGE ))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ADMIN_PAGE ))
			return ERROR;
		Long userID = user.getRegistrationID();
		Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
		Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
		userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
		return SUCCESS;
	}
	
	public String sendSMS(){
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String type=jObj.getString("type");
		String scope=jObj.getString("scope");
		String scopeId=jObj.getString("scopeId");
		String content=jObj.getString("content");
		result = sendUpdatesService.sendSMSToSelectedPeople(user.getRegistrationID(),scope,new Long(scopeId),content,type);
		 return SUCCESS;
	}
	
	public String ajaxHandler()
	{
		String param=null;
		param=getTask();
		try {
			jObj=new JSONObject(param);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	 if(jObj.getString("task").equalsIgnoreCase("getPanchayat"))
		{
			String selectedVal=jObj.getString("selected");
			String checkedVal =jObj.getString("checkedele"); 
			if(checkedVal.equalsIgnoreCase("panchayat"))
			{
			designationsList = staticDataService.getPanchayatiesByMandalId(new Long(selectedVal));
			designationsList.add(0,new SelectOptionVO(0l,"Select Panchayat"));
			}
		}
	  else if(jObj.getString("task").equalsIgnoreCase("getWards"))
			{
				Long locationId = jObj.getLong("id");
				if(locationId !=0){
					Long publicationDateId = sendUpdatesService.getLatestPublicationDateId();
					designationsList = voterReportService.getWardsInMunicipality(new Long(locationId.toString().substring(1)),publicationDateId);
					designationsList.add(0,new SelectOptionVO(0l,"Select Ward"));
				}
			}
	  else if(jObj.getString("task").equalsIgnoreCase("getBooths"))
		{
			Long locationId = jObj.getLong("id");
			Long constituencyId = jObj.getLong("constituencyId");
			Long mandalId = jObj.getLong("mandalId");
			Long publicationDateId = sendUpdatesService.getLatestPublicationDateId();
			if(locationId !=0 && constituencyId != 0){
				if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("1")){//if muncipality select
					designationsList = sendUpdatesService.getBoothsForWardId(locationId,publicationDateId);
					if(designationsList.size()>0)
					designationsList.add(0,new SelectOptionVO(0l,"Select Booth"));
				}
				if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("2")){//if mandal select
					designationsList = votersAnalysisService.getBoothsByPanchayatId(locationId,publicationDateId);
					if(designationsList.size()>0)
					designationsList.add(0,new SelectOptionVO(0l,"Select Booth"));
				}
			}
		}
	 //for voter search page
	  else if(jObj.getString("task").equalsIgnoreCase("getAllVotersDetails"))
		{
		  org.json.JSONArray jSONArray = jObj.getJSONArray("selectedVoters");
		  List<Long> categories = new ArrayList<Long>();
		  org.json.JSONArray votersJSONArray = jObj.getJSONArray("votersIds");
		  List<VoterHouseInfoVO> votersList = new ArrayList<VoterHouseInfoVO>();
			VoterHouseInfoVO voterHouseInfoVO = null;
			for(int i = 0;i<votersJSONArray.length();i++){
				JSONObject jSONObject= votersJSONArray.getJSONObject(i);
				voterHouseInfoVO = new VoterHouseInfoVO();
				voterHouseInfoVO.setVoterId(jSONObject.getLong("voterId"));
				voterHouseInfoVO.setBoothId(jSONObject.getLong("boothId"));
				votersList.add(voterHouseInfoVO);
			}
			voterHouseInfoVOList = voterReportService.getVoterInfoByBIdandVId(votersList,jObj.getLong("publicationId"));
		}
	
	 return SUCCESS;
}
	

	public String getVoterDetailsBasedOnBoothId(){
	
	try {
		String param = null;
		param = getTask();
		jObj = new JSONObject(param);	
	} catch (ParseException e) {
		e.printStackTrace();
	}
	session = request.getSession();
	RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
	Long userId = regVO.getRegistrationID();
 if(jObj.getString("task").equalsIgnoreCase("getVotersDetails"))
	{
	 try{
		 constituencyManagementVO = new ConstituencyManagementVO();
			List<VoterVO> votersList = null;
			
		votersList = voterReportService.getVoterDetailsForAdminEdit(jObj.getLong("id"),userId,jObj.getLong("startIndex"),jObj.getLong("endIndex"));

		if(votersList.size() > 0 && votersList != null){
		constituencyManagementVO.setVoterDetails(votersList);
		}	

		if(votersList.size() > 0 )
			constituencyManagementVO.setVoterDetailsCount(votersList.get(0).getTotalVoters());

	}catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in getVoterDetailsBasedOnBoothId() Method,Exception is- "+e);
		}
	}
	return Action.SUCCESS;

	}
	
	public String saveVoterEditData(){
		
		try {
			 jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		    session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			Long userId = null;
			if(user != null && user.getRegistrationID() != null)
			    userId = user.getRegistrationID();
		
			 try{
				 org.json.JSONArray jSONArray = jObj.getJSONArray("selectedVoters");
			   if(jObj.getString("task").equalsIgnoreCase("allVotersEditInfo")){
				  
				   List<VoterVO> votersList = new ArrayList<VoterVO>();
				   for(int i = 0;i<jSONArray.length();i++){
					   JSONObject jSONObject= jSONArray.getJSONObject(i);
					   voterVO = new VoterVO();
					   if(jSONObject != null && jSONObject.length() > 0)
					   {
					   voterVO.setFirstName(jSONObject.getString("firstName"));
					   voterVO.setVoterIDCardNo(jSONObject.getString("voterIDCardNo"));
					   voterVO.setGender(jSONObject.getString("gender"));
					   voterVO.setAge(jSONObject.getLong("age"));
					   voterVO.setHouseNo(jSONObject.getString("houseNo"));
					   voterVO.setRelativeFirstName(jSONObject.getString("relativeFirstName"));
					   voterVO.setRelationshipType(jSONObject.getString("relationshipType"));
					   voterVO.setSerialNo(jSONObject.getLong("serialNo"));
					   voterVO.setMobileNo(jSONObject.getString("mobileNo"));
					   voterVO.setVoterId(jSONObject.getString("voterIds"));
					   votersList.add(voterVO);
					   }
				   }
				   voterVO = voterReportService.saveVoterDetailsList(votersList,userId,jObj.getLong("boothId"));
			   }
			   else if(jObj.getString("task").equalsIgnoreCase("allVotersSearchInfo")){
				   List<VoterVO> votersList = new ArrayList<VoterVO>(); 
				   for(int i = 0;i<jSONArray.length();i++){
					   JSONObject jSONObject= jSONArray.getJSONObject(i);
					   voterVO = new VoterVO();
					   if(jSONObject != null && jSONObject.length() > 0)
					   {
					   voterVO.setFirstName(jSONObject.getString("firstName"));
					   voterVO.setVoterIDCardNo(jSONObject.getString("voterIDCardNo"));
					   voterVO.setGender(jSONObject.getString("gender"));
					   voterVO.setAge(jSONObject.getLong("age"));
					   voterVO.setHouseNo(jSONObject.getString("houseNo"));
					   voterVO.setRelativeFirstName(jSONObject.getString("relativeFirstName"));
					   voterVO.setRelationshipType(jSONObject.getString("relationshipType"));
					   voterVO.setSerialNo(jSONObject.getLong("serialNo"));
					   voterVO.setMobileNo(jSONObject.getString("mobileNo"));
					   voterVO.setVoterId(jSONObject.getString("voterIds"));
					   voterVO.setBoothId(jSONObject.getLong("boothIds"));
					   votersList.add(voterVO);
					   }
				   }
				   voterVO = voterReportService.saveVoterSearchDetailsList(votersList,userId);
			   }
		}catch (Exception e) {
				Log.error("Exception Occured in getMultipleFamilesInfoForEdit() Method, Exception - ",e);
			}
			 return Action.SUCCESS;
	}
}

