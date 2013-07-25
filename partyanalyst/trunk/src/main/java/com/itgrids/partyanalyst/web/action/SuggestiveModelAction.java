package com.itgrids.partyanalyst.web.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.ISuggestiveModelService;
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
	private IStaticDataService staticDataService;
	private ICrossVotingEstimationService crossVotingEstimationService;
	
	private static final Logger log = Logger.getLogger(SuggestiveModelAction.class);
	
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
	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}
	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
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
	public String execute(){
		
		return Action.SUCCESS;
	}
	
	public String getPartyPerformanceForSelectedLocation()
	{
		try{
		session = request.getSession();
		jObj = new JSONObject(getTask());
		
		optionVO = suggestiveModelService.getPartyPerformanceForSelectedLocation(jObj.getLong("constituencyId"),jObj.getLong("electionId"),jObj.getLong("partyId"),jObj.getLong("locationId"),jObj.getString("locationType"));	
			
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
		Long electionYear= jObj.getLong("electionYear");
		Long electionTypeId = jObj.getLong("electionType");
		Long partyId = jObj.getLong("partyId");
		Long stateId = jObj.getLong("stateId");
		constituencies =  crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccessByStateId(electionTypeId,stateId,regVO.getRegistrationID(),electionYear);	
		return Action.SUCCESS;
	}
	
	public String getElectionYears(){

			try{
				jObj = new JSONObject(getTask());
				System.out.println("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			String elecType = jObj.getString("elecTypeId");
			Long partyId = new Long(jObj.getString("partyId"));
			Long stateId = new Long(jObj.getString("stateId"));
			
			Long countryId = 1l;
			String electionType = null;
			List<SelectOptionVO> yearsList = null;
			if(elecType.equalsIgnoreCase("Parliament"))
				electionType = IConstants.PARLIAMENT_ELECTION_TYPE;
			else 
				electionType = IConstants.ASSEMBLY_ELECTION_TYPE;
			
			Long electionScope = staticDataService.getElectionScopeForAElection(stateId, electionType, countryId);
			if(electionScope != null)
				electionsYears = staticDataService.getElectionIdsAndYearsByElectionScopeId(electionScope,partyId);

		return Action.SUCCESS;
	}
}
