package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.PublicationDate;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.xpath.internal.Arg;

public class VotersAnalysisAction extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
	
	private HttpSession session;
	
	private String task;
	JSONObject jObj;
	
	private RegionServiceDataImp regionServiceDataImp;
	
	private IVotersAnalysisService votersAnalysisService;
	
	private List<SelectOptionVO> namesList;
	
	private static final Logger log = Logger.getLogger(VotersAnalysisAction.class);

	public List<SelectOptionVO> getNamesList() {
		return namesList;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public void setNamesList(List<SelectOptionVO> namesList) {
		this.namesList = namesList;
	}


	
	private ICrossVotingEstimationService crossVotingEstimationService;
	
	private List<SelectOptionVO> constituencyList;
	
	public RegionServiceDataImp getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(RegionServiceDataImp regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}	
	private List<SelectOptionVO> publicationData;
	
	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}

	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
		ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
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
	
	public void setServletRequest(HttpServletRequest arg0) {
		
		this.request=arg0;
	}

	
	
	public List<SelectOptionVO> getPublicationData() {
		return publicationData;
	}

	public void setPublicationData(List<SelectOptionVO> publicationData) {
		this.publicationData = publicationData;
	}
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	
	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public String execute() throws Exception
	{
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
			return ERROR;
		Long userID = user.getRegistrationID();
		
		Long electionYear = 2009l;
		Long electionTypeId = 2l;
		constituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
		return SUCCESS;
		
	}
	
	public String AjaxHandler()
	{
		String param;
		param = getTask();
		
		try{
			jObj = new JSONObject(param);	
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getRequestMessagesForUser() Method,Exception is- "+e);
		}
		if(jObj.getString("task").equalsIgnoreCase("getMandalList"))
		{
			String selectedVal=jObj.getString("selected");
			
			//List<SelectOptionVO> mandals=regionServiceDataImp.getMandalsByConstituencyID(new Long(selectedVal));
			
			namesList = regionServiceDataImp.getSubRegionsInConstituency(new Long(selectedVal), IConstants.PRESENT_YEAR, null);
			SelectOptionVO obj = new SelectOptionVO(0L,"Select Mandal");
			namesList.add(0, obj);
			
			
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getPublicationDate"))
		{
			Long constituencyId = jObj.getLong("selected");
			namesList = votersAnalysisService.publicationDetailsBasedOnConstituency(constituencyId);
			namesList.add(0, new SelectOptionVO(0L,"Select Publication Date"));
		}
		return Action.SUCCESS;
	}
	
}
