package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyBoothResult1Action extends ActionSupport  implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(PartyBoothResult1Action.class);
	private String task = null;
	private String partyName;
	private String electionType;
	private String electionYear;
	private String constituencyName;
	private PartyBoothPerformanceVO result;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private List<SelectOptionVO> constituencyVOs;
	private HttpServletResponse response;
	private HttpSession session;
	private HttpServletRequest request;
	private List<SelectOptionVO> options;
	JSONObject jObj = null;
	
	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	
	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public List<SelectOptionVO> getConstituencyVOs() {
		return constituencyVOs;
	}

	public void setConstituencyVOs(List<SelectOptionVO> constituencyVOs) {
		this.constituencyVOs = constituencyVOs;
	}

	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	public PartyBoothPerformanceVO getResult() {
		return result;
	}

	public void setResult(PartyBoothPerformanceVO result) {
		this.result = result;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletResponse getServletResponse() {
		return response;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<SelectOptionVO> getOptions() {
		return options;
	}

	public void setOptions(List<SelectOptionVO> options) {
		this.options = options;
	}

	public String execute() throws Exception {

		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		
		System.out.println("In execute = "+electionType+" ,election year =  "+electionYear);
		constituencyVOs = crossVotingEstimationService.getConstituenciesForElectionYearAndScopeForBoothData(electionYear, new Long(electionType));
		//constituencyVOs = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(regVO.getRegistrationID(),new Long(electionYear), new Long(electionType));

		return Action.SUCCESS;
	}
	
	public String getParty() throws Exception{
	
		System.out.println("In get party = ");
		constituencyVOs = crossVotingEstimationService.getPartiesForConstituencyAndElectionYearForBoothData(new Long(constituencyName), electionYear);
		return Action.SUCCESS;
	}

	public String getAllOptions(){
		try{
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		    jObj=new JSONObject(task);
		    if(jObj.getString("task").trim().equalsIgnoreCase("states")){
				
		    	  //options = crossVotingEstimationService.getAllOptions(jObj.getString("task").trim(),null,null,null);
				
		    	options = crossVotingEstimationService.getUserAccessStateList(regVO.getRegistrationID());
				
			  }else if(jObj.getString("task").trim().equalsIgnoreCase("years")){
				
				  List<Long> constituencyIds = null;
				  Long electionType = jObj.getLong("electionType");
				  if(electionType.equals(1L))
					constituencyIds = (List<Long>)session.getAttribute("userAccessParConsIds");
				  else
					constituencyIds = (List<Long>)session.getAttribute("userAccessAssembConsIds");
					  
					options = crossVotingEstimationService.getUserAccessElectionYears(electionType,jObj.getLong("stateId"),constituencyIds);  
				  //options = crossVotingEstimationService.getAllOptions(jObj.getString("task").trim(),jObj.getLong("stateId"),jObj.getLong("electionType"),null);
				
			  }else if(jObj.getString("task").trim().equalsIgnoreCase("constituencies")){
				
				  List<Long> constituencyIds = null;
				  Long electionType = jObj.getLong("electionType");
				  if(electionType.equals(1L))
					constituencyIds = (List<Long>)session.getAttribute("userAccessParConsIds");
				  else
					constituencyIds = (List<Long>)session.getAttribute("userAccessAssembConsIds");
				  
				  options = crossVotingEstimationService.getConstituencyListByConstituenciesIds(jObj.getLong("electionId"),constituencyIds);
				  
				  //options = crossVotingEstimationService.getAllOptions(jObj.getString("task").trim(),null,null,jObj.getLong("electionId"));
				  
				
			  }
		}catch(Exception e){
			
			log.error("Exception rised in getAllOptions action method"+e);
			
		}
		return Action.SUCCESS;
	}
	
}
