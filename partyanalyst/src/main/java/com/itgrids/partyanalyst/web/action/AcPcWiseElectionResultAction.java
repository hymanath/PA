package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CasteWiseResultVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IAcPcWiseElectionResultService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AcPcWiseElectionResultAction extends ActionSupport implements ServletRequestAware  {

	
	
	private static final long serialVersionUID = -8021304666056628316L;
	transient private HttpServletRequest request;
	transient private HttpSession session;
	private static final Logger LOG = Logger.getLogger(AcPcWiseElectionResultAction.class);
	private String task;
	transient private JSONObject jObj;
	private List<BasicVO> resultList;
	@Autowired
	transient private IAcPcWiseElectionResultService acPcWiseElectionResultService;
	private List<SelectOptionVO> resultLists; 
	private IStaticDataService staticDataService;
	private List<SelectOptionVO> returnList;
	private List<GenericVO> returnList1;
	private EntitlementsHelper entitlementsHelper;
	private List<CasteWiseResultVO> casteData;
	private List<com.itgrids.survey.soa.endpoints.GenericVO> genderWiseDetails;
	private static final String TRUE = "true";
	private static final String PARTIES = "parties";
	private static final String STATEID = "stateId";
	private static final String ELECTIONID = "electionId";
	private static final String ELECTIONSCOPEID = "electionScopeId";
	
	public List<CasteWiseResultVO> getCasteData() {
		return casteData;
	}


	public void setCasteData(final List<CasteWiseResultVO> casteData) {
		this.casteData = casteData;
	}


	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}


	public void setEntitlementsHelper(final EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}


	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}


	public void setStaticDataService(final IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}


	public List<SelectOptionVO> getResultLists() {
		return resultLists;
	}


	public void setResultLists(final List<SelectOptionVO> resultLists) {
		this.resultLists = resultLists;
	}


	public void setServletRequest(final HttpServletRequest request)
	{
		this.request = request;
	}
	
	
	public String getTask() {
		return task;
	}


	public void setTask(final String task) {
		this.task = task;
	}


	public List<BasicVO> getResultList() {
		return resultList;
	}


	public void setResultList(final List<BasicVO> resultList) {
		this.resultList = resultList;
	}

	

	public List<SelectOptionVO> getReturnList() {
		return returnList;
	}


	public void setReturnList(final List<SelectOptionVO> returnList) {
		this.returnList = returnList;
	}


	

	public List<GenericVO> getReturnList1() {
		return returnList1;
	}


	public void setReturnList1(final List<GenericVO> returnList1) {
		this.returnList1 = returnList1;
	}


	


	public List<com.itgrids.survey.soa.endpoints.GenericVO> getGenderWiseDetails() {
		return genderWiseDetails;
	}


	public void setGenderWiseDetails(final 
			List<com.itgrids.survey.soa.endpoints.GenericVO> genderWiseDetails) {
		this.genderWiseDetails = genderWiseDetails;
	}


	public String execute()
	{
		try
		{
			LOG.debug("Entered Into execute method in AcPcWiseElectionResultAction Action");
			session = request.getSession();
			final RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			List<String> entitlements = null;
			if(registrationVO.getEntitlements() != null && registrationVO.getEntitlements().size()>0){
				entitlements = registrationVO.getEntitlements();
				if(registrationVO == null && !entitlements.contains(IConstants.NEW_LIVE_RESULTS.trim())){
					return INPUT;
				}
			if(!entitlements.contains(IConstants.NEW_LIVE_RESULTS)){
					return ERROR;
				}
			
			/*if(session.getAttribute(IConstants.USER) == null && 
					!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.NEW_LIVE_RESULTS)){
				return INPUT;
			}
			if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.NEW_LIVE_RESULTS)){
				return ERROR;
			}*/
			
			if (!registrationVO.getIsAdmin().equals(TRUE)){
			  return ERROR;
			}
			
			resultLists = staticDataService.getAllParliaments();
			
		} 
		}
		catch (Exception e)
		{
			LOG.error("Exception Raised In execute method in AcPcWiseElectionResultAction Action", e);
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	public String getElectionResults()
	{
		try
		{
			LOG.debug("Entered Into getElectionResults method in AcPcWiseElectionResultAction Action");
			session = request.getSession();
			final RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO == null) 
			{
				return ERROR;
			} 
			else{
				if (!registrationVO.getIsAdmin().equals(TRUE)){
				   return ERROR;
				}
			}
			
			jObj = new JSONObject(getTask());
						
			final JSONArray parties = jObj.getJSONArray(PARTIES);
			final List<Long> partyIds = new ArrayList<Long>();
			for(int i = 0 ; i < parties.length() ; i++)
			{
				partyIds.add(Long.valueOf(parties.get(i).toString()));
			}
			resultList = acPcWiseElectionResultService.getPartyWiseComperassionResult(jObj.getLong(STATEID),jObj.getLong(ELECTIONID),partyIds,jObj.getLong(ELECTIONSCOPEID),jObj.getString("scope"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised In getElectionResults method in AcPcWiseElectionResultAction Action", e);
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	public String cbnOrModiEffect()
	{
		try
		{
			LOG.debug("Entered Into cbnOrModiEffect method in AcPcWiseElectionResultAction Action");
			session = request.getSession();
			final RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO == null) 
			{
				return ERROR;
			} 
			else{
				if (!registrationVO.getIsAdmin().equals(TRUE)){
				   return ERROR;
				}
			}
			
			jObj = new JSONObject(getTask());
						
			final JSONArray parties = jObj.getJSONArray(PARTIES);
			final List<Long> partyIds = new ArrayList<Long>();
			for(int i = 0 ; i < parties.length() ; i++)
			{
				partyIds.add(Long.valueOf(parties.get(i).toString()));
			}
			returnList = acPcWiseElectionResultService.cbnOrModiEffect(jObj.getLong(STATEID),jObj.getLong(ELECTIONID),partyIds,jObj.getLong(ELECTIONSCOPEID));
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised In cbnOrModiEffect method in AcPcWiseElectionResultAction Action", e);
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	public String getCbnEffect()
	{
		try
		{
			LOG.debug("Entered Into getCbnEffect method in AcPcWiseElectionResultAction Action");
			session = request.getSession();
			final RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO == null) 
			{
				return ERROR;
			} 
			else{
				if (!registrationVO.getIsAdmin().equals(TRUE)){
				   return ERROR;
				}
			}
			
			returnList1 = acPcWiseElectionResultService.cbnEffectCalucation();
			
		}
		catch (Exception e)
		{
			LOG.error("Exception Raised In getCbnEffect method in AcPcWiseElectionResultAction Action", e);
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}

	public String filterAndGetElectionResults()
	{
		try
		{
			LOG.debug("Entered Into filterAndGetElectionResults method in AcPcWiseElectionResultAction Action");
			session = request.getSession();
			final RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO == null) 
			{
				return ERROR;
			} 
			else{
				if (!registrationVO.getIsAdmin().equals(TRUE)){
				   return ERROR;
				}
			}
			
			jObj = new JSONObject(getTask());
						
			final JSONArray parties = jObj.getJSONArray(PARTIES);
			final List<Long> partyIds = new ArrayList<Long>();
			for(int i = 0 ; i < parties.length() ; i++)
			{
				partyIds.add(Long.valueOf(parties.get(i).toString()));
			}
			
			final JSONArray regions = jObj.getJSONArray("regionIds");
			final List<Long> regionsIds = new ArrayList<Long>();
			for(int i = 0 ; i < regions.length() ; i++)
			{
				regionsIds.add(Long.valueOf(regions.get(i).toString()));
			}
			
			resultList = acPcWiseElectionResultService.filterToGetPartyWiseComperassionResult(jObj.getLong(STATEID),jObj.getLong(ELECTIONID),partyIds,jObj.getLong(ELECTIONSCOPEID),jObj.getString("scope"),regionsIds);
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised In filterAndGetElectionResults method in AcPcWiseElectionResultAction Action", e);
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	public String searchAndGetElectionResults()
	{
		try
		{
			LOG.debug("Entered Into searchAndGetElectionResults method in AcPcWiseElectionResultAction Action");
			session = request.getSession();
			final RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO == null) 
			{
				return ERROR;
			} 
			else{
				if (!registrationVO.getIsAdmin().equals(TRUE)){
				   return ERROR;
				}
			}
			
			jObj = new JSONObject(getTask());
						
			final JSONArray parties = jObj.getJSONArray(PARTIES);
			final List<Long> partyIds = new ArrayList<Long>();
			for(int i = 0 ; i < parties.length() ; i++)
			{
				partyIds.add(Long.valueOf(parties.get(i).toString()));
			}
			
			final JSONArray regions = jObj.getJSONArray("regionIds");
			final List<Long> regionsIds = new ArrayList<Long>();
			for(int i = 0 ; i < regions.length() ; i++)
			{
				regionsIds.add(Long.valueOf(regions.get(i).toString()));
			}
			final String searchName  = jObj.getString("searchName");
			resultList = acPcWiseElectionResultService.searchPartyWiseComparissionResult(jObj.getLong(STATEID),jObj.getLong(ELECTIONID),partyIds,jObj.getLong(ELECTIONSCOPEID),jObj.getString("scope"),regionsIds,searchName);
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised In searchAndGetElectionResults method in AcPcWiseElectionResultAction Action", e);
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	public String getCasteWiseData()
	{
		try
		{
			LOG.debug("Entered Into getCasteWiseData method in AcPcWiseElectionResultAction Action");
			session = request.getSession();
			final RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO == null) 
			{
				return ERROR;
			} 
			else{
				if (!registrationVO.getIsAdmin().equals(TRUE)){
				   return ERROR;
				}
			}
			
			jObj = new JSONObject(getTask());
						
			
			casteData = acPcWiseElectionResultService.getCasteWiseDataForElection(jObj.getLong(ELECTIONID));
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised In getCasteWiseData method in AcPcWiseElectionResultAction Action", e);
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	public String getGenderWiseReport()
	{
		try
		{
			LOG.debug("Entered Into getGenderWiseReport method in AcPcWiseElectionResultAction Action");
			session = request.getSession();
			final RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO == null) 
			{
				return ERROR;
			} 
			else{
				if (!registrationVO.getIsAdmin().equals(TRUE)){
				   return ERROR;
				}
			}
			
			jObj = new JSONObject(getTask());
			final List<Long> surveyIds = new ArrayList<Long>();
			final JSONArray surveyArray = jObj.getJSONArray("surveyIds");
			
			for(int i = 0 ; i < surveyArray.length() ; i++)
			{
				surveyIds.add(Long.valueOf(surveyArray.get(i).toString()));
			}
			
			genderWiseDetails = acPcWiseElectionResultService.getGenderWiseSurveyReport(jObj.getLong("partyId"),jObj.getLong("constituencyId"),surveyIds);
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised In getGenderWiseReport method in AcPcWiseElectionResultAction Action", e);
			return Action.ERROR;
		}
		return Action.SUCCESS;
		
	}

	
}
