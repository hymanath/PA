/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 2, 2009
 */
package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CandidateElectionProfileVO;
import com.itgrids.partyanalyst.dto.CandidateProfileInfoVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CandidateElectionResultsAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware,ServletContextAware {

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
	private ResultStatus result;
	private GallaryVO gallaryVO;
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ICandidateDetailsService candidateDetailsService;
	private File userImage;
	private String userImageContentType;
	private String userImageFileName;
	private String problemFilePathList;
	private String contentType;
	private String fileTitle;
	private String fileDescription;
    private HttpServletRequest servletRequest;
    private ServletContext context;
    private String fileNameList;
    private String problemFilepath;
    private String tempFileName;
	private String visibility;
	private Long gallaryId;
	private List<String> descriptions;
	
	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

	public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}

	public String getProblemFilePathList() {
		return problemFilePathList;
	}

	public void setProblemFilePathList(String problemFilePathList) {
		this.problemFilePathList = problemFilePathList;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public String getFileNameList() {
		return fileNameList;
	}

	public void setFileNameList(String fileNameList) {
		this.fileNameList = fileNameList;
	}

	public String getProblemFilepath() {
		return problemFilepath;
	}

	public void setProblemFilepath(String problemFilepath) {
		this.problemFilepath = problemFilepath;
	}

	public String getTempFileName() {
		return tempFileName;
	}

	public void setTempFileName(String tempFileName) {
		this.tempFileName = tempFileName;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public Long getGallaryId() {
		return gallaryId;
	}

	public void setGallaryId(Long gallaryId) {
		this.gallaryId = gallaryId;
	}

	public ResultStatus getResult() {
		return result;
	}

	public void setResult(ResultStatus result) {
		this.result = result;
	}

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

	public ServletContext getContext() {
		return context;
	}

	
	public void setContext(ServletContext context) {
		this.context = context;
	}

	public void setFileVO(List<FileVO> fileVO) {
		this.fileVO = fileVO;
	}
	
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
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public String execute(){
		request.setAttribute("candidateId",candidateId);
		//candidateProfileInfoVO.setCandidateElectionProfile(candidateElectionProfile);
		descriptions= candidateDetailsService.getCandidateProfileDescriptionByCandidateID(candidateId);
		
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
			else if(jObj.getString("task").equalsIgnoreCase("getNewsInAGallary"))
			{
				fileVO = candidateDetailsService.getCandidatesPhotosInAGallary(jObj.getLong("gallaryId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getCandidateDevelopmentGallaryDetail"))
			{
				fileVO = candidateDetailsService.getCandidatesPhotoGallaryDetail(jObj.getLong("candidateId"),IConstants.DEVELOPEMENT_ACTIVITIES);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getDevelopmentsInAGallary"))
			{
				fileVO = candidateDetailsService.getCandidatesPhotosInAGallary(jObj.getLong("gallaryId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getScopesForNewSearch"))
			{
				fileVO = candidateDetailsService.getScopesForNewSearch();
			}
			else if(jObj.getString("task").equalsIgnoreCase("getStates"))
			{
				fileVO = candidateDetailsService.getStateDetails();
			}
			else if(jObj.getString("task").equalsIgnoreCase("getDistrictsByStateId"))
			{
				fileVO = candidateDetailsService.getDistrictDetailsByStateId(jObj.getLong("stateId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("searchNewsDetails"))
			{    
				  FileVO inputs = new FileVO();
				      inputs.setCandidateId(jObj.getLong("candidateId"));			  
				  if(jObj.getString("keywords")!= null && jObj.getString("keywords").trim().length()>0 )
				      inputs.setKeywords(jObj.getString("keywords"));
				  if(jObj.getString("locationScope")!= null && jObj.getString("locationScope").trim().length()>0 )
					  inputs.setLocationScope(jObj.getLong("locationScope"));  
				  if(jObj.getString("location")!= null && jObj.getString("location").trim().length()>0 )
					  inputs.setLocation(jObj.getLong("location"));
				fileVO = candidateDetailsService.searchNewsDetails(inputs);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getFirstFourNewsRecordsToDisplay"))
			{
				fileVO = candidateDetailsService.getFirstFourNewsRecordsToDisplay(jObj.getLong("candidateId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getNewsToDisplay"))
			{
				fileVO = candidateDetailsService.getNewsToDisplay(jObj.getLong("candidateId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getFileByFileId"))
			{
				fileVO = candidateDetailsService.getFileByFileId(jObj.getLong("fileId"));
			}
			
			}catch(Exception e){
				e.printStackTrace();
			}
		
		
		return SUCCESS;
	}
	
	public String AjaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		
		if(jObj.getString("task").equalsIgnoreCase("createNewGallary"))
		{
			gallaryVO = new GallaryVO();
			gallaryVO.setCandidateId(jObj.getLong("candidateId"));
			gallaryVO.setUserId(regVO.getRegistrationID());
			gallaryVO.setGallaryName(jObj.getString("name"));
			gallaryVO.setDescription(jObj.getString("desc"));
			gallaryVO.setVisibility(jObj.getString("visibility"));
			gallaryVO.setContentType(jObj.getString("contentType"));
			
			
			result = candidateDetailsService.createNewGallary(gallaryVO);
		}
		return Action.SUCCESS;
	}
	
	public String uploadFiles()
	{
		  session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
		if(user!=null)
		{		
			String fileName="";
			FileVO fileVO=new FileVO();
			gallaryVO = new GallaryVO();
			fileVO.setName(getUserImageFileName()) ;
			fileVO.setTitle(getFileTitle());
			fileVO.setDescription(getFileDescription());
			// i think Visibility variable may in fileVO
			gallaryVO.setVisibility(getVisibility());
			try {
				String filePath1 = context.getRealPath("/");
				String filePath = filePath1 + "/uploaded_files";
					Long systime = System.currentTimeMillis();
					StringTokenizer st = new StringTokenizer(userImageContentType, "/");
					while(st.hasMoreTokens()) {
					String key = st.nextToken();
					String val = st.nextToken();
					if(userImageContentType.equalsIgnoreCase("text/plain")){
						fileName = systime.toString()+"."+key;
					}
					else
					  fileName = systime.toString()+"."+val;
					problemFilepath=filePath+"/"+fileName;
					File fileToCreate = new File(filePath, fileName);
					FileUtils.copyFile(userImage, fileToCreate);
					System.out.println("contenyt type.."
							+ userImageContentType);
					System.out.println(key + "\t" + val);
				    fileVO.setContentType(val);
				    fileVO.setName(fileName);
					}
			 } catch (Exception e) {
				e.printStackTrace();
				addActionError(e.getMessage());
			}
			 fileVO.setPathOfFile(problemFilepath);
			 System.out.println();
			 System.out.println("title         := "+getFileTitle());
			 System.out.println("description   := "+getFileDescription());
			 System.out.println("path          := "+problemFilepath);
			 System.out.println("file name     := "+fileName);
			 System.out.println("content type  := "+fileVO.getContentType());
			 System.out.println("visibility    := "+getVisibility());
			 System.out.println();
			return "redirectToJSP";
			
			} 
	     return Action.SUCCESS;
	
	}


}
