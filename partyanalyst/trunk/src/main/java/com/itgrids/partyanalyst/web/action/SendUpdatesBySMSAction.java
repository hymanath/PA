package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gdata.util.parser.Action;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SmsVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.ISendUpdatesService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.VotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.SmsResultVO;
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
					designationsList = getRegionServiceDataImp().getHamletsOrWards(locationId, IConstants.PRESENT_YEAR);
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
					designationsList = sendUpdatesService.getBoothsForWardId(new Long(locationId.toString().substring(1)),publicationDateId);
					if(designationsList.size()>0)
					designationsList.add(0,new SelectOptionVO(0l,"Select Booth"));
				}
				if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("2")){//if mandal select
					designationsList = votersAnalysisService.getBoothsByPanchayatId(new Long(locationId.toString().substring(1)),publicationDateId);
					if(designationsList.size()>0)
					designationsList.add(0,new SelectOptionVO(0l,"Select Booth"));
				}
			}
		}
	 
	 return SUCCESS;
}
}
