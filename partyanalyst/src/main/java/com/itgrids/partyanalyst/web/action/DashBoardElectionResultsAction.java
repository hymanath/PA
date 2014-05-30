package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.DashBoardResultsVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.service.IDashBoardElectionResultsService;
import com.itgrids.survey.soa.endpoints.OptionVO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DashBoardElectionResultsAction extends ActionSupport implements ServletRequestAware {
	
	private HttpServletRequest request;
	private String task;
	private JSONObject jObj;
	private IDashBoardElectionResultsService dashBoardElectionResultsService;
	private DashBoardResultsVO resultVO;
	private List<Object[]> constituencyDetails;
	private List<DashBoardResultsVO> matrixReport;
	private List<DashBoardResultsVO> subReport;
	private List<DashBoardResultsVO> dashBoardConstiResults;
	private List<GenericVO> partiesList;
	private List<PartyResultVO> constiList;
	private DashBoardResultsVO partyWiseCountDetails;
	private GenericVO genericVO;
	private List<OptionVO> casteResult;
	
	
	public GenericVO getGenericVO() {
		return genericVO;
	}

	public void setGenericVO(GenericVO genericVO) {
		this.genericVO = genericVO;
	}

	public DashBoardResultsVO getPartyWiseCountDetails() {
		return partyWiseCountDetails;
	}

	public void setPartyWiseCountDetails(
			DashBoardResultsVO partyWiseCountDetails) {
		this.partyWiseCountDetails = partyWiseCountDetails;
	}

	public List<PartyResultVO> getConstiList() {
		return constiList;
	}

	public void setConstiList(List<PartyResultVO> constiList) {
		this.constiList = constiList;
	}

	public List<GenericVO> getPartiesList() {
		return partiesList;
	}

	public void setPartiesList(List<GenericVO> partiesList) {
		this.partiesList = partiesList;
	}

	public List<DashBoardResultsVO> getDashBoardConstiResults() {
		return dashBoardConstiResults;
	}

	public void setDashBoardConstiResults(
			List<DashBoardResultsVO> dashBoardConstiResults) {
		this.dashBoardConstiResults = dashBoardConstiResults;
	}

	public List<DashBoardResultsVO> getSubReport() {
		return subReport;
	}

	public void setSubReport(List<DashBoardResultsVO> subReport) {
		this.subReport = subReport;
	}

	public List<DashBoardResultsVO> getMatrixReport() {
		return matrixReport;
	}

	public void setMatrixReport(List<DashBoardResultsVO> matrixReport) {
		this.matrixReport = matrixReport;
	}

	public List<Object[]> getConstituencyDetails() {
		return constituencyDetails;
	}

	public void setConstituencyDetails(List<Object[]> constituencyDetails) {
		this.constituencyDetails = constituencyDetails;
	}

	public DashBoardResultsVO getResultVO() {
		return resultVO;
	}

	public void setResultVO(DashBoardResultsVO resultVO) {
		this.resultVO = resultVO;
	}

	public IDashBoardElectionResultsService getDashBoardElectionResultsService() {
		return dashBoardElectionResultsService;
	}

	public void setDashBoardElectionResultsService(
			IDashBoardElectionResultsService dashBoardElectionResultsService) {
		this.dashBoardElectionResultsService = dashBoardElectionResultsService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
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
	
	public List<OptionVO> getCasteResult() {
		return casteResult;
	}

	public void setCasteResult(List<OptionVO> casteResult) {
		this.casteResult = casteResult;
	}

	public String execute()
	{
		return Action.SUCCESS;
		
	}
	
	public String getElectionResultsSummary()
	{
		resultVO = dashBoardElectionResultsService.getElectionResultsSummary();
		return Action.SUCCESS;
	}
	
	public String getConstituenciesDetailsForSubReport()
	{
		
		constituencyDetails = dashBoardElectionResultsService
				.getConstituenciesDetailsForSubReport(
						request.getParameter("type"),
						Long.parseLong(request.getParameter("partyId")),
						Long.parseLong(request.getParameter("locationId")),
						Long.parseLong(request.getParameter("scopeId")));
		return Action.SUCCESS;
		
	}
	
	public String getMatrixReportForElectionResult()
	{
		try
		{
			jObj = new JSONObject(getTask());
			
			
			Long electionId = jObj.getLong("electionId");
			Long scopeId = jObj.getLong("scopeId");
			
			 JSONArray jArray = jObj.getJSONArray("locationIds");
			 List<Long> locationIds = new ArrayList<Long>();
			 
			   for (int i = 0; i < jArray.length(); i++) 
			   {
				   locationIds.add(new Long(jArray.get(i).toString()));
			   }
			   
			matrixReport = dashBoardElectionResultsService.getMatrixReportForElectionResult(electionId,locationIds,scopeId);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return Action.SUCCESS;
	}
	
	
	public String getWonAndLeadCountPartyWise()
	{
		try
		{
			jObj = new JSONObject(getTask());
			
			
			Long electionId = jObj.getLong("electionId");
			Long scopeId = jObj.getLong("scopeId");
			Long electionScopeId = null;
			
			if(scopeId.longValue() == 4L){
				electionScopeId = jObj.getLong("electionScopeId");
			}
			
			 JSONArray jArray = jObj.getJSONArray("locationIds");
			 List<Long> locationIds = new ArrayList<Long>();
			 
			   for (int i = 0; i < jArray.length(); i++) 
			   {
				   locationIds.add(new Long(jArray.get(i).toString()));
			   }
			   
			matrixReport = dashBoardElectionResultsService.getWonAndLeadCountPartyWise(electionId,locationIds,scopeId,electionScopeId);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return Action.SUCCESS;
	}
	
	public String getSubReportForElectionResultByConstituencyReservationType()
	{
		try
		{
			jObj = new JSONObject(getTask());
			
			
			Long electionId = jObj.getLong("electionId");
			Long scopeId = jObj.getLong("scopeId");
			
			 JSONArray jArray = jObj.getJSONArray("locationIds");
			 List<Long> locationIds = new ArrayList<Long>();
			 
			   for (int i = 0; i < jArray.length(); i++) 
			   {
				   locationIds.add(new Long(jArray.get(i).toString()));
			   }
			   
			   subReport = dashBoardElectionResultsService.getSubReportForElectionResultByConstituencyReservationType(electionId,locationIds,scopeId);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return Action.SUCCESS;
	}
	
	public String getSubReportForElectionResultByConstituencyType()
	{
		try
		{
			jObj = new JSONObject(getTask());
			
			
			Long electionId = jObj.getLong("electionId");
			Long scopeId = jObj.getLong("scopeId");
			
			 JSONArray jArray = jObj.getJSONArray("locationIds");
			 List<Long> locationIds = new ArrayList<Long>();
			 
			   for (int i = 0; i < jArray.length(); i++) 
			   {
				   locationIds.add(new Long(jArray.get(i).toString()));
			   }
			   
			   subReport = dashBoardElectionResultsService.getSubReportForElectionResultByConstituencyType(electionId,locationIds,scopeId);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return Action.SUCCESS;
	}
	public String getdashBoardConstituencyWiseResults(){
		try{
			jObj = new JSONObject(getTask());
			Long electionId= jObj.getLong("electionId");
			
			 JSONArray jArray = jObj.getJSONArray("constituencyIds");
			 List<Long> locationIds = new ArrayList<Long>();
			 
			   for (int i = 0; i < jArray.length(); i++) 
			   {
				   locationIds.add(new Long(jArray.get(i).toString()));
			   }
			
			dashBoardConstiResults  = dashBoardElectionResultsService.getConstituencyWiseLiveResults(electionId,locationIds);
		} catch (Exception e) {
			LOG.error("Exception occured in getConstituencyWiseResults() ",e);
			return Action.ERROR;
		}		
		return Action.SUCCESS;
	}
	
	public String getPartiesInConsituenciesOfElection(){
		try{
			jObj = new JSONObject(getTask());
			Long electionId= jObj.getLong("electionId");
			
			 JSONArray jArray = jObj.getJSONArray("constituencyIds");
			 List<Long> locationIds = new ArrayList<Long>();
			 
			   for (int i = 0; i < jArray.length(); i++) 
			   { 
				   locationIds.add(new Long(jArray.get(i).toString()));
			   }
			
			   partiesList  = dashBoardElectionResultsService.getPartiesInConsituenciesOfElection(electionId,locationIds);
		} catch (Exception e) {
			LOG.error("Exception occured in getConstituencyWiseResults() ",e);
			return Action.ERROR;
		}		
		return Action.SUCCESS;
	}
	
	public String partysVotesShareInConstituenciesOfElection(){
		try{
			jObj = new JSONObject(getTask());
			Long electionId= jObj.getLong("electionId");
			
			 JSONArray jArray = jObj.getJSONArray("constituencyIds");
			 List<Long> locationIds = new ArrayList<Long>();
			 
			   for (int i = 0; i < jArray.length(); i++) 
			   { 
				   locationIds.add(new Long(jArray.get(i).toString()));
			   }
			   
			   JSONArray jArrayPrty = jObj.getJSONArray("partyIds");
				 List<Long> partyIds = new ArrayList<Long>();
				 
				   for (int i = 0; i < jArrayPrty.length(); i++) 
				   { 
					   partyIds.add(new Long(jArrayPrty.get(i).toString()));
				   }
			
			   constiList  = dashBoardElectionResultsService.partysVotesShareInConstituenciesOfElection(electionId,locationIds,partyIds);
		} catch (Exception e) {
			LOG.error("Exception occured in getConstituencyWiseResults() ",e);
			return Action.ERROR;
		}		
		return Action.SUCCESS;
	}
	
	public String getPartyWiseWinningSeatsCount()
	{
		LOG.debug("Entered into the getPartyWiseWinningSeatsCount method");
		try
		{
			
			jObj = new JSONObject(getTask());

			Long electionId = jObj.getLong("electionId");
			Long scopeId = jObj.getLong("scopeId");
			Long partyId = jObj.getLong("partyId");
			Long percent = jObj.getLong("percent");
			
			 JSONArray jArray = jObj.getJSONArray("locationIds");
			 List<Long> locationIds = new ArrayList<Long>();
			 
			   for (int i = 0; i < jArray.length(); i++) 
			   {
				   locationIds.add(new Long(jArray.get(i).toString()));
			   }
			   
			   partyWiseCountDetails = dashBoardElectionResultsService.getPartyWiseWinningSeatsCount(
					electionId,locationIds, scopeId,
					percent,partyId);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getPartyWiseWinningSeatsCount method",e);

		}
		return Action.SUCCESS;
	}
	
	public String getPartyWiseWinningSeatsPercentage()
	{
		LOG.debug("Entered into the getPartyWiseWinningSeatsPercentage method");
		try{
			jObj = new JSONObject(getTask());
			Long electionId = jObj.getLong("electionId");
			Long scopeId = jObj.getLong("scopeId");
			Long partyId = jObj.getLong("partyId");
			Long percent = jObj.getLong("percent");
			JSONArray jArray = jObj.getJSONArray("locationIds");
			List<Long> locationIds = new ArrayList<Long>();
			for(int i=0;i<jArray.length();i++)
			{
				locationIds.add(new Long(jArray.get(i).toString()));	
			}
			 
			  partyWiseCountDetails = dashBoardElectionResultsService.getPartyWiseWinningSeatsPercentage(
						electionId,locationIds, scopeId,
						percent,partyId);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in getPartyWiseWinningSeatsPercentage method");
			LOG.error("Exception raised in getPartyWiseWinningSeatsCount method",e);

		}
		return Action.SUCCESS;
	}
	
	
	public String getParticiaptedPartyList()
	{
		LOG.debug("Entered into the getParticiaptedPartyList method");
		try
		{
			
			jObj = new JSONObject(getTask());

			Long electionId = jObj.getLong("electionId");
			Long scopeId = jObj.getLong("scopeId");
			Long electionScopeId = jObj.getLong("electionScopeId");
			 JSONArray jArray = jObj.getJSONArray("locationIds");
			 List<Long> locationIds = new ArrayList<Long>();
			 
			   for (int i = 0; i < jArray.length(); i++) 
			   {
				   locationIds.add(new Long(jArray.get(i).toString()));
			   }
			   
			   genericVO = dashBoardElectionResultsService.getparticipatedPartiesInLocation(electionId,locationIds,electionScopeId,scopeId);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getPartyWiseWinningSeatsCount method");

		}
		return Action.SUCCESS;
	}

	public String getReservedConstiList()
	{
		LOG.debug("Entered into the getParticiaptedPartyList method");
		try
		{
			
			jObj = new JSONObject(getTask());

			Long electionId = jObj.getLong("electionId");
			Long scopeId = jObj.getLong("scopeId");
			Long electionScopeId = jObj.getLong("electionScopeId");
			 JSONArray jArray = jObj.getJSONArray("locationIds");
			 List<Long> locationIds = new ArrayList<Long>();
			 
			   for (int i = 0; i < jArray.length(); i++) 
			   {
				   locationIds.add(new Long(jArray.get(i).toString()));
			   }
			   
			   genericVO = dashBoardElectionResultsService.getReservedConstiList(electionId,locationIds,electionScopeId,scopeId);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getPartyWiseWinningSeatsCount method");

		}
		return Action.SUCCESS;
	}
	
	
	public String getTop5CastePeopleOpnionOnParty(){
			try{
			jObj = new JSONObject(getTask());
			String[] surveyIds = jObj.getString("surveyIds").split(",");
			List<Long> ids = new ArrayList<Long>();
			for(int i =0;i<surveyIds.length;i++){
				ids.add(Long.parseLong(surveyIds[i]));
			}
			 casteResult = dashBoardElectionResultsService.getTop5CastePeopleOpnionOnParty( jObj.getLong("constituencyId") ,ids);
		}catch(Exception e){
			LOG.error("Exception raised in getTop5CastePeopleOpnionOnParty method",e);
		}
			return Action.SUCCESS;
	}
}
