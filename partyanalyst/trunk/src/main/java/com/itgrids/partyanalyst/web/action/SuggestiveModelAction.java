package com.itgrids.partyanalyst.web.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.ISuggestiveModelService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class SuggestiveModelAction  implements ServletRequestAware {
	
	private HttpServletRequest request;
	private ISuggestiveModelService suggestiveModelService;
	private HttpSession session;
	private String task;
	JSONObject jObj;
	private OptionVO optionVO;
	private List<SelectOptionVO> constituencies;
	private List<SelectOptionVO> electionsYears;
	private List<SelectOptionVO> partyList;
	private IStaticDataService staticDataService;
	private List<PartyPositionVO> partyPositionVOList;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private List userAccessConstituencyList;
	
	
	
	private static final Logger log = Logger.getLogger(SuggestiveModelAction.class);
	
	
	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public ISuggestiveModelService getSuggestiveModelService() {
		return suggestiveModelService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setSuggestiveModelService(
			ISuggestiveModelService suggestiveModelService) {
		this.suggestiveModelService = suggestiveModelService;
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
	public OptionVO getOptionVO() {
		return optionVO;
	}

	public void setOptionVO(OptionVO optionVO) {
		this.optionVO = optionVO;
	}
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	public List<SelectOptionVO> getConstituencies() {
		return constituencies;
	}
	public void setConstituencies(List<SelectOptionVO> constituencies) {
		this.constituencies = constituencies;
	}		
	public List<SelectOptionVO> getElectionsYears() {
		return electionsYears;
	}
	public void setElectionsYears(List<SelectOptionVO> electionsYears) {
		this.electionsYears = electionsYears;
	}
	
	public List<PartyPositionVO> getPartyPositionVOList() {
		return partyPositionVOList;
	}

	public void setPartyPositionVOList(List<PartyPositionVO> partyPositionVOList) {
		this.partyPositionVOList = partyPositionVOList;
	}
	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	public List getUserAccessConstituencyList() {
		return userAccessConstituencyList;
	}

	public void setUserAccessConstituencyList(List userAccessConstituencyList) {
		this.userAccessConstituencyList = userAccessConstituencyList;
	}


	public String execute(){
		
		return Action.SUCCESS;
	}
	
	public String getPartyPerformanceForSelectedLocation()
	{
		try{
		session = request.getSession();
		jObj = new JSONObject(getTask());
		
		 if(jObj.getString("task").equalsIgnoreCase("getPartyPerformanceReport"))
		 partyPositionVOList = suggestiveModelService.getPartyPerformenceReport(jObj.getLong("constituencyId"),jObj.getLong("partyId"),jObj.getLong("locationId"),jObj.getString("locationType"),jObj.getLong("electionId"),jObj.getString("tempVar"));
			
		}catch (Exception e) {
		 e.printStackTrace();
		 Log.error(" Exception Occured in getPartyPerformanceForSelectedLocation() method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
	
	public String getConstituenciesList(){
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute(IConstants.USER);
		try {
			jObj = new JSONObject(getTask());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
		Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
		userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(regVO.getRegistrationID(),electionYear,electionTypeId);
		constituencies = suggestiveModelService.getConstituenciesForUserAccessByStateId(userAccessConstituencyList,electionTypeId,electionYear);
		constituencies.add(0, new SelectOptionVO(0L,"Select Constituency"));
		return Action.SUCCESS;
	}
	
	
	public String getPartyDetails(){
		try{
			jObj = new JSONObject(getTask());
		}catch(Exception e){
			e.printStackTrace();
		}
		Long tehsilId= jObj.getLong("mandalId");
		partyList = suggestiveModelService.getPartyDetailsByMandal(tehsilId);
		return Action.SUCCESS;
	}
}
