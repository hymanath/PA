package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateSearchService;
import com.itgrids.partyanalyst.service.IConstituencySearchService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;


public class AjaxSearchAction implements ServletRequestAware{

	private List<String> namesList;
	private String task = null;
	private IConstituencySearchService constituencySearchService;
	private ICandidateSearchService candidateSearchService;
	transient private HttpServletRequest request;
	private String stateId;
	private String searchCriteria;
	private String constituencyType;
	private String letters;
	private List<SelectOptionVO> resultNames;	

	public String getLetters() {
		return letters;
	}

	public void setLetters(final String letters) {
		this.letters = letters;
	}

	public List<SelectOptionVO> getResultNames() {
		return resultNames;
	}

	public void setResultNames(final List<SelectOptionVO> resultNames) {
		this.resultNames = resultNames;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(final String stateId) {
		this.stateId = stateId;
	}

	public String getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(final String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public String getConstituencyType() {
		return constituencyType;
	}

	public void setConstituencyType(final String constituencyType) {
		this.constituencyType = constituencyType;
	}

	public ICandidateSearchService getCandidateSearchService() {
		return candidateSearchService;
	}

	public void setCandidateSearchService(final 
			ICandidateSearchService candidateSearchService) {
		this.candidateSearchService = candidateSearchService;
	}
	
	public IConstituencySearchService getConstituencySearchService() {
		return constituencySearchService;
	}

	public void setConstituencySearchService(final 
			IConstituencySearchService constituencySearchService) {
		this.constituencySearchService = constituencySearchService;
	}
	
	public List<String> getNamesList() {
		return namesList;
	}

	public void setNamesList(final List<String> namesList) {
		this.namesList = namesList;
	}

	public String getTask() {
		return task;
	}

	public void setTask(final String task) {
		this.task = task;
	}
	
	//JSONObject jObj;
	
	public String execute() 
	{
		/*session = request.getSession();
		String param=null;
		
		param=getTask();
		LOG.info("param:"+param);
		
		
		
		try {
			jObj=new JSONObject(param);
			LOG.info("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("searchCriteria").equalsIgnoreCase("Candidate"))
		{
			String electionType = null;
			if("MLA".equalsIgnoreCase(jObj.getString("constituencyType")))
				electionType = IConstants.ASSEMBLY_ELECTION_TYPE;
			if("MP".equalsIgnoreCase(jObj.getString("constituencyType")))
				electionType = IConstants.PARLIAMENT_ELECTION_TYPE;
			Long stateId = jObj.getLong("stateId");	
			List<SelectOptionVO> candidateNamesAndIds = candidateSearchService.getCandidateNamesAndIds(electionType, stateId);
			setNamesList(getNamesFromSelectOptionVos(candidateNamesAndIds));	
			session.setAttribute("candidateNamesAndIds", candidateNamesAndIds);
		}
		else if (jObj.getString("searchCriteria").equalsIgnoreCase("Constituency"))
		{
			Long electionTypeId= 0l;
			if("MLA".equalsIgnoreCase(jObj.getString("constituencyType")))
				electionTypeId = 2l;
			if("MP".equalsIgnoreCase(jObj.getString("constituencyType")))
				electionTypeId = 1l;
			Long stateId = jObj.getLong("stateId");	
			
				List<SelectOptionVO> constituencyNamesAndIds = constituencySearchService.getConstituencyNamesAndIds(electionTypeId,stateId);
				setNamesList(getNamesFromSelectOptionVos(constituencyNamesAndIds));					
		}	*/
		
		 return Action.SUCCESS;
	}
	
	public String getNames()
	{
		final HttpSession session = request.getSession();
		String electionType = null;
		if(searchCriteria.equalsIgnoreCase("Candidate"))
		{
			if("MLA".equalsIgnoreCase(constituencyType)){
				electionType = IConstants.ASSEMBLY_ELECTION_TYPE;
			}
			if("MP".equalsIgnoreCase(constituencyType)){
				electionType = IConstants.PARLIAMENT_ELECTION_TYPE;
			}
			
			resultNames = candidateSearchService.getCandidateNamesAndIds(electionType, Long.valueOf(stateId),letters);
			session.setAttribute("candidateNamesAndIds", resultNames);
		}
		else if(searchCriteria.equalsIgnoreCase("Constituency"))
		{
			Long electionTypeId= 0l;
			if("MLA".equalsIgnoreCase(constituencyType)){
				electionTypeId = 2l;
			}if("MP".equalsIgnoreCase(constituencyType)){
				electionTypeId = 1l;
			}
			resultNames = constituencySearchService.getConstituencyNamesAndIds(electionTypeId,Long.valueOf(stateId),letters);
		}
		
		return Action.SUCCESS; 
	}

	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;
	}

		
}
