package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.DebateCandidateCharcVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IDebateAnalysisService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DebateAnalysisAction extends ActionSupport implements ServletRequestAware {


	private static final long serialVersionUID = 303202624274758166L;


	private static final Logger 		LOG = Logger.getLogger(DebateAnalysisAction.class); 
	
	private HttpServletRequest 			request;
	private HttpSession 				session;

	private JSONObject 					jObj;
	private String 						task;
	private DebateCandidateCharcVO candidateCharcDetails;	
	private IDebateAnalysisService debateAnalysisService;
	
	
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
		
	public DebateCandidateCharcVO getCandidateCharcDetails() {
		return candidateCharcDetails;
	}

	public void setCandidateCharcDetails(
			DebateCandidateCharcVO candidateCharcDetails) {
		this.candidateCharcDetails = candidateCharcDetails;
	}	

	public IDebateAnalysisService getDebateAnalysisService() {
		return debateAnalysisService;
	}

	public void setDebateAnalysisService(
			IDebateAnalysisService debateAnalysisService) {
		this.debateAnalysisService = debateAnalysisService;
	}

	public String execute()
	{
		return Action.SUCCESS;
	}

	
	public String getDebateCandidateCharacteristicsDetailsByParty()
	{
		try{
			LOG.info(" Entered into  getDebateCandidateCharacteristicsDetailsByParty(). ");
			HttpSession session = request.getSession();
			RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
			if(regVo == null)
				return Action.ERROR;
			
			jObj = new JSONObject(getTask());
			candidateCharcDetails = debateAnalysisService.getPartyWiseCandidateCharacteristicsDetails();
		}
		catch (Exception e){
			LOG.error(" Exception occured in getDebateCandidateCharacteristicsDetailsByParty() ",e);
		}
		return Action.SUCCESS;
	}
	
	
}
