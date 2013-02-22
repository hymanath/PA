package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class VoterDataManageAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 64316195191123277L;
	private static final Logger log = Logger.getLogger(VoterDataManageAction.class);
	private HttpServletRequest request;
	private Long constituencyId;
	private Long publicationDateId;
	private Integer startIndex;
	private Integer maxResults;
	private IVotersAnalysisService votersAnalysisService;
	private List<SelectOptionVO> constituenciesList,publicationDateList;
	private String task;
	JSONObject jObj;
	private ResultStatus resultStatus;
	private HttpSession session;
	private List<SelectOptionVO> constituenciesListForVoterChanges;
	
	public List<SelectOptionVO> getConstituenciesListForVoterChanges() {
		return constituenciesListForVoterChanges;
	}

	public void setConstituenciesListForVoterChanges(
			List<SelectOptionVO> constituenciesListForVoterChanges) {
		this.constituenciesListForVoterChanges = constituenciesListForVoterChanges;
	}

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public Long getPublicationDateId() {
		return publicationDateId;
	}

	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}

	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}

	public List<SelectOptionVO> getPublicationDateList() {
		return publicationDateList;
	}

	public void setPublicationDateList(List<SelectOptionVO> publicationDateList) {
		this.publicationDateList = publicationDateList;
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

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String execute()
	{
		session = request.getSession();
	
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user == null || ((RegistrationVO)user).getRegistrationID() == null){
			log.error(" No User Log In .....");			
			return "error";
		}
		
		votersAnalysisService.insertVoterData(constituencyId,publicationDateId,startIndex,maxResults);
		
		constituenciesList = votersAnalysisService.getConstituenciesList();
		
		publicationDateList = votersAnalysisService.getAllPublicationDates();
		
		constituenciesListForVoterChanges = votersAnalysisService.getConstituenciesToBeMappedForVoterChanges();
		
		return ActionSupport.SUCCESS;
	}
	
	public String AjaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("insertVoterData"))
		{
			resultStatus = votersAnalysisService.insertVoterData(jObj.getLong("constituencyId"),jObj.getLong("publicationDateId"),jObj.getInt("startIndex"),jObj.getInt("maxResults"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("insertVoterModificationData"))
		{
			resultStatus = votersAnalysisService.moveVotersModificationDataFromTempToMainTable(jObj.getLong("constituencyId"), jObj.getLong("publicationDateId"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("InsertmapVoterData"))
		{
			resultStatus = votersAnalysisService.mapVoterDataFromOnePublicationToAnotherPublication(jObj.getLong("constituencyId"),jObj.getLong("frompublicationDateId"),jObj.getLong("topublicationDateId"),jObj.getBoolean("boothCreateflag"));
		}
		return Action.SUCCESS;
	}

}
