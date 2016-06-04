package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.ConstituenciesStatusVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ConstituencyElectionReportAction extends ActionSupport implements
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JSONObject jObj = null;
	private String task = null;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	private final static Logger LOG = Logger.getLogger(ConstituencyElectionReportAction.class);
	private CandidateDetailsVO candidateResults = null;
	private IStaticDataService staticDataService = null;	
	private Long locationId,electionType;
	private String taskType = null;
	private List<SelectOptionVO> result;
    private IRegionServiceData regionServiceDataImp;
    private EntitlementsHelper entitlementsHelper;
    
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	public List<SelectOptionVO> getResult() {
		return result;
	}
	public void setResult(List<SelectOptionVO> result) {
		this.result = result;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}	
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public Long getElectionType() {
		return electionType;
	}
	public void setElectionType(Long electionType) {
		this.electionType = electionType;
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
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public CandidateDetailsVO getCandidateResults() {
		return candidateResults;
	}
	public void setCandidateResults(CandidateDetailsVO candidateResults) {
		this.candidateResults = candidateResults;
	}
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	public String execute() throws Exception
	{
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		
		/*if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.CONSTITUENCY_RESULTS_ENTITLEMENT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.CONSTITUENCY_RESULTS_ENTITLEMENT))
			return ERROR;*/
		List<String> entitlements = null;
		if(user.getEntitlements() != null && user.getEntitlements().size()>0){
			entitlements = user.getEntitlements();
			if(user == null && !entitlements.contains(IConstants.CONSTITUENCY_RESULTS_ENTITLEMENT)){
				return INPUT;
			}
			if(!entitlements.contains(IConstants.CONSTITUENCY_RESULTS_ENTITLEMENT))
				return ERROR;
		
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				LOG.info("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(jObj.getString("task").equals("getStates")){			
					candidateResults = staticDataService.getAllStatesInCountry();
			}
			else if(jObj.getString("task").equals("getConstituencies")){
				locationId = Long.valueOf(jObj.getLong("locationId"));
				electionType = 1l;
				candidateResults =  staticDataService.getLatestConstituenciesForAssemblyAndParliamentForAllElectionYears(electionType,locationId);
			}
			else if(jObj.getString("task").equals("getConstituenciesForDistrict")){
				locationId = Long.valueOf(jObj.getLong("locationId"));
				ConstituenciesStatusVO convo = staticDataService.getConstituenciesForDistrict(locationId,Long.parseLong(IConstants.PRESENT_ELECTION_YEAR),IConstants.ASSEMBLY_ELECTION_TYPE);
				List<SelectOptionVO> selVo = new ArrayList<SelectOptionVO>(0);
				selVo.addAll(convo.getNewConstituencies());
				selVo.addAll(convo.getExistConstituencies());
				CandidateDetailsVO  canVo = new CandidateDetailsVO();
				canVo.setLatestConstituencies(selVo);
				candidateResults = canVo;
			}
			else if(jObj.getString("task").equals("getAssemblyConstituencysForAState")){
				locationId = Long.valueOf(jObj.getLong("locationId"));
				CandidateDetailsVO  canVo = new CandidateDetailsVO();
				canVo.setLatestConstituencies(staticDataService.getLatestConstituenciesByStateId(locationId));
				candidateResults = canVo;
			}
			
			else if(jObj.getString("task").equals("getCandidateDetails")){
				taskType = jObj.getString("taskType");
				if(taskType.equalsIgnoreCase("assembly")){
					electionType = 2l;
					locationId = Long.valueOf(jObj.getLong("locationId"));
					candidateResults = staticDataService.getElectionResultsForADistrictForAllYears(locationId);
				}else{
					electionType = 1l;
					locationId = Long.valueOf(jObj.getLong("locationId"));
					candidateResults = staticDataService.getElectionResultsForAConstituencyForAllYears(locationId);
				}
				
			}
		}
	}
		return Action.SUCCESS;	
	
	}
	
	public String getDistrictAndConstituencyDetailsAction(){
		
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				LOG.info("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
		if(jObj.getString("task").equals("getDistricts")){	
				result = staticDataService.getDistricts(Long.valueOf(jObj.getString("locationId")));
				result.add(0,new SelectOptionVO(0l,"Select District"));
		}
		}
		return SUCCESS;
	}
	
}
