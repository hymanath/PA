package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ElectionBasicCommentsVO;
import com.itgrids.partyanalyst.dto.ElectionResultPartyVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyElectionResultsAction extends ActionSupport implements ServletRequestAware,ServletContextAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(PartyElectionResultsAction.class);
	private HttpServletRequest request;	
	private ServletContext context;
	private IStaticDataService staticDataService;
	private Long electionId;
	private Long partyId;
	private Long rank;
	private String partyName;
	private String electionType;
	private String stateName;
	private String electionYear;
	private ElectionResultPartyVO electionResultPartyVO;
	private String task = null;
	JSONObject jObj = null;
	private List<SelectOptionVO> electionYears;
	private Long electionTypeId;
	private String windowTask;
	private Long clickIndex;
	private String resultStatus;
	private String locationId;
	private String reportLevel;
	private Long stateId;
		

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public Long getClickIndex() {
		return clickIndex;
	}

	public void setClickIndex(Long clickIndex) {
		this.clickIndex = clickIndex;
	}

	public String getWindowTask() {
		return windowTask;
	}

	public void setWindowTask(String windowTask) {
		this.windowTask = windowTask;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public void setServletContext(ServletContext context) {
		this.context = context;		
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}	
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}	

	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public void setElectionResultPartyVO(ElectionResultPartyVO electionResultPartyVO) {
		this.electionResultPartyVO = electionResultPartyVO;
	}

	public ElectionResultPartyVO getElectionResultPartyVO() {
		return electionResultPartyVO;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getElectionType() {
		return electionType;
	}	
	
	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getReportLevel() {
		return reportLevel;
	}

	public void setReportLevel(String reportLevel) {
		this.reportLevel = reportLevel;
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

	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}	

	public List<SelectOptionVO> getElectionYears() {
		return electionYears;
	}

	public void setElectionYears(List<SelectOptionVO> electionYears) {
		this.electionYears = electionYears;
	}

	public Long getElectionTypeId() {
		return electionTypeId;
	}

	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}

	public String execute () throws Exception 
	{
		return Action.SUCCESS;
	}
public String ajaxCallHandler() throws Exception{
		
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(log.isDebugEnabled())
				log.debug(jObj);			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("getConstituencyResults"))
		{
			Long electionId = new Long(jObj.getString("electionId"));
			Long partyId = new Long(jObj.getString("partyId"));
			Long rank = new Long(jObj.getString("rank"));
			Long stateId = new Long(jObj.getString("stateId"));
			if(log.isInfoEnabled())
			{
				log.debug("Entered in to Action");
				log.debug("electionId::::::::::::"+electionId);
				log.debug("partyId:::::::::::"+partyId);
				log.debug("rank:::::::::::"+rank);
				
			}
			
			electionResultPartyVO = new ElectionResultPartyVO();
			electionResultPartyVO = staticDataService.getElectionResultForAPartyInAnElection(electionId, partyId, rank,stateId);
		}		
		
		return Action.SUCCESS;
	}

public String getAllElectionYears() throws Exception	{
	String param = null;
	param = getTask();
	try {
		jObj = new JSONObject(param);
		if(log.isDebugEnabled())
			log.debug(jObj);			
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		String electionType = jObj.getString("electionType");
		Long electionTypeId = new Long(jObj.getString("electionTypeId"));
		Long stateID = new Long(jObj.getString("stateID"));
		if(electionType.equals(IConstants.ASSEMBLY_ELECTION_TYPE) || electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE))
		{
			try{
				electionYears = staticDataService.getElectionIdsAndYearsInfo(electionTypeId,new Long(stateID));
				electionYears.add(0, new SelectOptionVO(0l,"Select Year"));
				
			}catch(Exception e){
				electionYears = null;
				log.debug("Error occured in retriving the data in ElectionDetailsReportAction ");
			}	
		} else 	if(electionType.equals(IConstants.ZPTC) || electionType.equals(IConstants.MPTC))
		{
			try{
				electionYears = staticDataService.getAllElectionYearsForATeshil(electionTypeId);
				electionYears.add(0, new SelectOptionVO(0l,"Select Year"));
				
			}catch(Exception e){
				electionYears = null;
				log.debug("Error occured in retriving the data in ElectionDetailsReportAction ");
			}
		}
	
	return Action.SUCCESS;	
}
	
	

}
