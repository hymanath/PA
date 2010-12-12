package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SearchListVO;
import com.itgrids.partyanalyst.service.ICandidateSearchService;
import com.itgrids.partyanalyst.service.IConstituencySearchService;
import com.itgrids.partyanalyst.utils.IConstants;
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
	private SearchListVO searchListVO ;
	private ConstituencyVO constituencyVO;
	private Long id;
	private String constType;
	private Long totalSearchCount;
	private Long state;
	
	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

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

	public String getConstType() {
		return constType;
	}

	public void setConstType(String constType) {
		this.constType = constType;
	}

	public SearchListVO getSearchListVO() {
		return searchListVO;
	}

	public void setSearchListVO(SearchListVO searchListVO) {
		this.searchListVO = searchListVO;
	}

	public Long getTotalSearchCount() {
		return totalSearchCount;
	}

	public void setTotalSearchCount(Long totalSearchCount) {
		this.totalSearchCount = totalSearchCount;
	}

	@SuppressWarnings("unchecked")
	public String execute() {
		session = request.getSession();		
		
		if(getSearchName() != null && getSearchName().equals("Constituency"))
		{	
			String electionType = null;
			if(getConstType().equalsIgnoreCase(IConstants.MLA))
				electionType = IConstants.ASSEMBLY_ELECTION_TYPE;
			if(getConstType().equalsIgnoreCase(IConstants.MP))
				electionType = IConstants.PARLIAMENT_ELECTION_TYPE;
			
			totalSearchCount = constituencySearchService.getTotalConstituencySearchCount(getSearchText(),electionType,getState());
			
			if(totalSearchCount == 1)
			{
				constituencySearchList = constituencySearchService.getConstituencyDetails(getSearchText(), electionType);
				id = constituencySearchList.get(0).getId();
				return "redirectToConstituencyPage";
			}
			
	     }
					
		else if(getSearchName() != null && getSearchName().equals("Candidate"))
		{			
			List<SelectOptionVO> candidateNamesAndIds = (List<SelectOptionVO>)session.getAttribute("candidateNamesAndIds");
			SelectOptionVO selectOptionVO = getNameAndIdForSearchText(candidateNamesAndIds, getSearchText());
			
			if(selectOptionVO.getId() == 0)
			{
				totalSearchCount = candidateSearchService.getTotalSearchCount(getSearchText(),getConstType(),getState());
			}
			
			if(selectOptionVO.getId() != 0)
			{
				candidatateSearchList = candidateSearchService.getCandidatesDetails(selectOptionVO.getId(), selectOptionVO.getName());
				id = candidatateSearchList.get(0).getId();
				return "redirectToCandidatePage";
			}
		}	
		return SUCCESS;	
	}
	
	public String cncSearchAjax()
	{
		searchListVO = new SearchListVO();
		String searchText = request.getParameter("searchText");
		String constType = request.getParameter("constType");
		String sortOption = request.getParameter("sort");
		String order = request.getParameter("dir");
		String stateId= request.getParameter("state");
		Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
		Integer maxResult = Integer.parseInt(request.getParameter("results"));
		Long staId = Long.parseLong(stateId);
		
		candidatateSearchList = candidateSearchService.getCandidatesDetails(searchText,sortOption,order,
																		startIndex,maxResult,constType,staId);
		
		searchListVO.setCandidateVOList(candidatateSearchList);
		searchListVO.setTotalSearchCount(candidatateSearchList.get(0).getTotalSearchCount());
		
		return SUCCESS;
	}
	public String constituencysearch()
	{
		searchListVO = new SearchListVO();
		String searchText=request.getParameter("searchText");
		String constType=request.getParameter("constType");
		String sortOption = request.getParameter("sort");
		String order = request.getParameter("dir");
		String stateId= request.getParameter("state");
		Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
		Integer maxResult = Integer.parseInt(request.getParameter("results"));
		Long staId = Long.parseLong(stateId);
		
		constituencySearchList = constituencySearchService.getConstituencyInformation(searchText,constType,staId,sortOption,order,startIndex,maxResult);
		
		if(constituencySearchList.size() > 0)
		{
			searchListVO.setConstituencyVOList(constituencySearchList);
			searchListVO.setTotalSearchCount(constituencySearchList.get(0).getTotalPolledVotes());
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