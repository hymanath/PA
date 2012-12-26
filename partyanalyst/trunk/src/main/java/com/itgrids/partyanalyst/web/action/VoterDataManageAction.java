package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.opensymphony.xwork2.ActionSupport;

public class VoterDataManageAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = 64316195191123277L;
	
	private HttpServletRequest request;
	private Long constituencyId;
	private Long publicationDateId;
	private Integer startIndex;
	private Integer maxResults;
	private IVotersAnalysisService votersAnalysisService;
	
	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public Long getPublicationDateId() {
		return publicationDateId;
	}

	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public String execute()
	{
		votersAnalysisService.insertVoterData(constituencyId,publicationDateId,startIndex,maxResults);
		return ActionSupport.SUCCESS;
	}

}
