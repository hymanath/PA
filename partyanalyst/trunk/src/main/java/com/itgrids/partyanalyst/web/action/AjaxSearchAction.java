package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateSearchService;
import com.itgrids.partyanalyst.service.IConstituencySearchService;
import com.opensymphony.xwork2.Action;


public class AjaxSearchAction implements ServletRequestAware{

	private List<String> namesList;
	JSONObject jObj = null,respObj=null;
	private String task = null;
	private IConstituencySearchService constituencySearchService;
	private ICandidateSearchService candidateSearchService;
	private HttpServletRequest request;
	private HttpSession session;

	public ICandidateSearchService getCandidateSearchService() {
		return candidateSearchService;
	}

	public void setCandidateSearchService(
			ICandidateSearchService candidateSearchService) {
		this.candidateSearchService = candidateSearchService;
	}
	
	public IConstituencySearchService getConstituencySearchService() {
		return constituencySearchService;
	}

	public void setConstituencySearchService(
			IConstituencySearchService constituencySearchService) {
		this.constituencySearchService = constituencySearchService;
	}
	
	public List<String> getNamesList() {
		return namesList;
	}

	public void setNamesList(List<String> namesList) {
		this.namesList = namesList;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	//JSONObject jObj;
	
	public String execute() 
	{
		session = request.getSession();
		String param=null;
		
		param=getTask();
		System.out.println("param:"+param);
		
		
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("searchCriteria").equalsIgnoreCase("Candidate"))
		{
			List<SelectOptionVO> candidateNamesAndIds = candidateSearchService.getCandidateNamesAndIds();
			setNamesList(getNamesFromSelectOptionVos(candidateNamesAndIds));	
			session.setAttribute("candidateNamesAndIds", candidateNamesAndIds);
		}
		else if (jObj.getString("searchCriteria").equalsIgnoreCase("Constituency"))
		{
				List<SelectOptionVO> constituencyNamesAndIds = constituencySearchService.getConstituencyNamesAndIds();
				setNamesList(getNamesFromSelectOptionVos(constituencyNamesAndIds));					
		}	
		
		 return Action.SUCCESS;
	}
	
	private List<String> getNamesFromSelectOptionVos(List<SelectOptionVO> namesAndIds){
			List<String> names = new ArrayList<String>();
			for(SelectOptionVO nameAndId:namesAndIds)
				names.add(nameAndId.getName());
			return names;
		}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

		
}
