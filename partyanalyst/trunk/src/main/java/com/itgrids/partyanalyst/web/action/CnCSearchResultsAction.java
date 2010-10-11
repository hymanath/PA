package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateSearchService;
import com.itgrids.partyanalyst.service.IConstituencySearchService;
import com.opensymphony.xwork2.ActionSupport;

public class CnCSearchResultsAction extends ActionSupport implements ServletRequestAware{
	
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	private String searchName;
	private String searchText;
	private IConstituencySearchService constituencySearchService; 
	private ICandidateSearchService candidateSearchService ; 
	List<ConstituencyVO> constituencySearchList ;
	List<CandidateVO> candidatateSearchList ;
	private Long id;
	
	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	
	public List<ConstituencyVO> getConstituencySearchList() {
		return constituencySearchList;
	}

	public void setConstituencySearchList(
			List<ConstituencyVO> constituencySearchList) {
		this.constituencySearchList = constituencySearchList;
	}

	public List<CandidateVO> getCandidatateSearchList() {
		return candidatateSearchList;
	}
	public void setCandidatateSearchList(List<CandidateVO> candidatateSearchList) {
		this.candidatateSearchList = candidatateSearchList;
	}
	
	public IConstituencySearchService getConstituencySearchService() {
		return constituencySearchService;
	}
	public void setConstituencySearchService(
			IConstituencySearchService constituencySearchService) {
		this.constituencySearchService = constituencySearchService;
	}

	public ICandidateSearchService getCandidateSearchService() {
		return candidateSearchService;
	}
	public void setCandidateSearchService(
			ICandidateSearchService candidateSearchService) {
		this.candidateSearchService = candidateSearchService;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@SuppressWarnings("unchecked")
	public String execute() {
		session = request.getSession();		
		
		if(getSearchName() != null && getSearchName().equals("Constituency"))
		{			
			constituencySearchList = constituencySearchService.getConstituencyDetails(getSearchText());
			if(constituencySearchList.size() == 1)
			{
				id = constituencySearchList.get(0).getId();
				return "redirectToConstituencyPage";
			}
		}
		else if(getSearchName() != null && getSearchName().equals("Candidate"))
		{			
			List<SelectOptionVO> candidateNamesAndIds = (List<SelectOptionVO>)session.getAttribute("candidateNamesAndIds");
			SelectOptionVO selectOptionVO = getNameAndIdForSearchText(candidateNamesAndIds, getSearchText());
			candidatateSearchList = candidateSearchService.getCandidatesDetails(selectOptionVO.getId(), selectOptionVO.getName());
			if(candidatateSearchList.size() == 1)
			{
				id = candidatateSearchList.get(0).getId();
				return "redirectToCandidatePage";
			}
		}	
		return SUCCESS;	
	}
		
	private SelectOptionVO getNameAndIdForSearchText(List<SelectOptionVO> namesAndIds, String searchText){
		SelectOptionVO selectOptionVO = null;
		for(SelectOptionVO nameAndId:namesAndIds)
			if(nameAndId.getName().equalsIgnoreCase(searchText))
				return nameAndId;
		SelectOptionVO notExist = new SelectOptionVO();
		notExist.setId(new Long(0));
		notExist.setName(searchText);
		selectOptionVO = notExist;	
		return selectOptionVO;	
	}

	
}