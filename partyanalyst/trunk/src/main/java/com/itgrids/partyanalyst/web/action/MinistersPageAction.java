package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateMinistriesVO;
import com.itgrids.partyanalyst.dto.ElectionGoverningBodyVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.MetaInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.ActionSupport;

public class MinistersPageAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	private EntitlementsHelper entitlementsHelper;
	private ICandidateDetailsService candidateDetailsService;
	private IStaticDataService staticDataService;
	private List<FileVO> fileVO;
	private Long electionId;
	private ElectionGoverningBodyVO electionGoverningBodyVO;
	private List<ElectionGoverningBodyVO> ministerDetails;
	private JSONObject jObj;
	private String task;
	private List<SelectOptionVO> years;
	private List<SelectOptionVO> statesList;
	private List<CandidateMinistriesVO> candidateMinistriesVO;
	private MetaInfoVO metaInfoVO;
	
	public MetaInfoVO getMetaInfoVO() {
		return metaInfoVO;
	}

	public void setMetaInfoVO(MetaInfoVO metaInfoVO) {
		this.metaInfoVO = metaInfoVO;
	}

	public ElectionGoverningBodyVO getElectionGoverningBodyVO() {
		return electionGoverningBodyVO;
	}

	public void setElectionGoverningBodyVO(
			ElectionGoverningBodyVO electionGoverningBodyVO) {
		this.electionGoverningBodyVO = electionGoverningBodyVO;
	}

	public List<SelectOptionVO> getYears() {
		return years;
	}

	public void setYears(List<SelectOptionVO> years) {
		this.years = years;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public List<CandidateMinistriesVO> getCandidateMinistriesVO() {
		return candidateMinistriesVO;
	}

	public void setCandidateMinistriesVO(
			List<CandidateMinistriesVO> candidateMinistriesVO) {
		this.candidateMinistriesVO = candidateMinistriesVO;
	}

	public List<ElectionGoverningBodyVO> getMinisterDetails() {
		return ministerDetails;
	}

	public void setMinisterDetails(List<ElectionGoverningBodyVO> ministerDetails) {
		this.ministerDetails = ministerDetails;
	}

	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}

	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}
	
	public String execute()
	{
		fileVO = candidateDetailsService.getStateDetails();
		
		electionGoverningBodyVO = candidateDetailsService.getElectionDetailsForMinister(electionId);
		
		statesList = staticDataService.getParticipatedStatesForAnElectionType(2l);
		
		candidateMinistriesVO = candidateDetailsService.getAllMinistersDetailsForAnElection(electionId);
		
		metaInfoVO = candidateDetailsService.getMetaInfoOfMinistersPage(electionGoverningBodyVO,candidateMinistriesVO);
			
		return SUCCESS;
	}
	
	public String AjaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("getMinistryYears"))
		{
			years = candidateDetailsService.getMinistryYearsForAState(jObj.getString("electionType"),jObj.getLong("stateId"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("getAllYearsAndElecIdsForParliament"))
		{
			years = candidateDetailsService.getMinistryYearsForAState(jObj.getString("electionType"),jObj.getLong("stateId"));
		}
	
		return SUCCESS;
	}
	
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
	}
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	
	

}
