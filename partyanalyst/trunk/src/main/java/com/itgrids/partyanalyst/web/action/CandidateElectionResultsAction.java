/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 2, 2009
 */
package com.itgrids.partyanalyst.web.action;

import java.util.List;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.CandidateProfileInfoVO;
import com.itgrids.partyanalyst.dto.CandidateElectionProfileVO;
import com.itgrids.partyanalyst.dto.FileVO;

import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class CandidateElectionResultsAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long candidateId;
	private List<CandidateDetailsVO> candidateElectionDetails;
	private CandidateVO candidateVO;
	private CandidateProfileInfoVO candidateProfileInfoVO;
	private String candidateURLString;
	private JSONObject jObj;
	private String task;
	private List<FileVO> fileVO;
	

	public String getCandidateURLString() {
		return candidateURLString;
	}

	public void setCandidateURLString(String candidateURLString) {
		this.candidateURLString = candidateURLString;
	}

	public CandidateProfileInfoVO getCandidateProfileInfoVO() {
		return candidateProfileInfoVO;
	}

	public void setCandidateProfileInfoVO(
			CandidateProfileInfoVO candidateProfileInfoVO) {
		this.candidateProfileInfoVO = candidateProfileInfoVO;
	}

	public CandidateVO getCandidateVO() {
		return candidateVO;
	}

	public void setCandidateVO(CandidateVO candidateVO) {
		this.candidateVO = candidateVO;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public List<CandidateDetailsVO> getCandidateElectionDetails() {
		return candidateElectionDetails;
	}

	public void setCandidateElectionDetails(
			List<CandidateDetailsVO> candidateElectionDetails) {
		this.candidateElectionDetails = candidateElectionDetails;
	}
	public List<FileVO> getFileVO() {
		return fileVO;
	}

	public void setFileVO(List<FileVO> fileVO) {
		this.fileVO = fileVO;
	}
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ICandidateDetailsService candidateDetailsService;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String execute(){
		request.setAttribute("candidateId",candidateId);
		//candidateProfileInfoVO.setCandidateElectionProfile(candidateElectionProfile);
		
		candidateVO = candidateDetailsService.getCandidateDetails(candidateId);
		
		candidateElectionDetails = candidateDetailsService.getCandidateElectionDetails(candidateId);		
		
		StringBuffer candidateURLStringBuffer = new StringBuffer(IConstants.CANDIDATE_STATIC_PAGE_URL);
		
		if(candidateVO.getCandidateName().equalsIgnoreCase("Y S RAJASEKHAR REDDY") ||
				candidateVO.getCandidateName().equalsIgnoreCase("Nara Chandrababu Naidu"))
		{
			candidateURLStringBuffer.append(candidateVO.getCandidateName().replace(' ', '_'));
		}
		else
		{
			candidateURLStringBuffer.append("Default_Candidate");
		}
	
		candidateURLString = candidateURLStringBuffer.toString();
		
		System.out.println("candidateURLString = "+candidateURLString);
		
		request.setAttribute("candidateURLString", candidateURLString);
		
		/* ---- Dummy Information ------- */
		
		candidateProfileInfoVO = new CandidateProfileInfoVO();
		CandidateElectionProfileVO electionPrf1 = new CandidateElectionProfileVO();
		electionPrf1.setPositionTitle("MLA");
		electionPrf1.setConstituency("Chandragiri");
		electionPrf1.setDistrict("Chittor");
		electionPrf1.setState("Andhra Pradesh");
		electionPrf1.setStartDuration("1976");
		electionPrf1.setEndDuration("");
		electionPrf1.setParty("Telugu Desam");
		
		CandidateElectionProfileVO electionPrf2 = new CandidateElectionProfileVO();
		electionPrf2.setPositionTitle("MLA");
		electionPrf2.setConstituency("Chandragiri");
		electionPrf2.setDistrict("Chittor");
		electionPrf2.setState("Andhra Pradesh");
		electionPrf2.setStartDuration("1976");
		electionPrf2.setEndDuration("");
		electionPrf2.setParty("Telugu Desam");
		
		
		if(candidateElectionDetails != null)
			return SUCCESS;
		else
			return ERROR;
	}
	
	public String getCandidatesPhotoGallaryDetail(){
		try  {
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("getCandidatePhotoGallaryDetail"))
			{
			    fileVO = candidateDetailsService.getCandidatesPhotoGallaryDetail(jObj.getLong("candidateId"),IConstants.PHOTO_GALLARY);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getPhotosInAGallary"))
			{
				fileVO = candidateDetailsService.getCandidatesPhotosInAGallary(jObj.getLong("gallaryId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getCandidateNewsGallaryDetail"))
			{
				fileVO = candidateDetailsService.getCandidatesPhotoGallaryDetail(jObj.getLong("candidateId"),IConstants.NEWS_GALLARY);
			}
			}catch(ParseException e){
				e.printStackTrace();
			}
		
		
		return SUCCESS;
	}


}
