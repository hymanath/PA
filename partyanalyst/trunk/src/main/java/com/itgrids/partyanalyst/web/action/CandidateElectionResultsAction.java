/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 2, 2009
 */
package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Random;

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
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.util.IWebConstants;
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
	private InputStream inputStream;
	private List<SelectOptionVO> selectOptionList;
	private List<String> descriptions;
	private String keywords;
	private Long locationScope;
	private Long locationValue;
	private String fileDate;
	
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Long getLocationScope() {
		return locationScope;
	}

	public void setLocationScope(Long locationScope) {
		this.locationScope = locationScope;
	}

	public Long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}

	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}
	

	public List<SelectOptionVO> getSelectOptionList() {
		return selectOptionList;
	}

	public void setSelectOptionList(List<SelectOptionVO> selectOptionList) {
		this.selectOptionList = selectOptionList;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
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

	public String execute()
	{
		request.setAttribute("candidateId",candidateId);
		
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
		
		request.setAttribute("candidateURLString", candidateURLString);
		
		fileVO = candidateDetailsService.getCandidateLatestVideos(candidateId,0,20);
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
			    fileVO = candidateDetailsService.getCandidatesPhotoGallaryDetail(jObj.getLong("candidateId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),IConstants.PHOTO_GALLARY);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getPhotosInAGallary"))
			{
				fileVO = candidateDetailsService.getCandidatesPhotosInAGallary(jObj.getLong("gallaryId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getCandidateNewsGallaryDetail"))
			{
				fileVO = candidateDetailsService.getCandidatesPhotoGallaryDetail(jObj.getLong("candidateId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),IConstants.NEWS_GALLARY);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getNewsInAGallary"))
			{
				fileVO = candidateDetailsService.getCandidatesPhotosInAGallary(jObj.getLong("gallaryId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getCandidateDevelopmentGallaryDetail"))
			{
				fileVO = candidateDetailsService.getCandidatesPhotoGallaryDetail(jObj.getLong("candidateId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),IConstants.DEVELOPEMENT_ACTIVITIES);
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
			else if(jObj.getString("task").equalsIgnoreCase("getFirstThreeImagesToDisplay"))
			{
				fileVO = candidateDetailsService.getFirstThreeImagesToDisplay(jObj.getLong("fileId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getFirstThreePhotoGallaryDetail"))
			{
				fileVO = candidateDetailsService.getFirstThreePhotoGallaryDetail(jObj.getLong("candidateId"));
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
		
		if(jObj.getString("task").equalsIgnoreCase("createNewGallary") || 
				jObj.getString("task").equalsIgnoreCase("createVideoNewGallary"))
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
		else if(jObj.getString("task").equalsIgnoreCase("candiadteGallariesForUplaod"))
		{
			selectOptionList = candidateDetailsService.getCandidateGallarySelectList(jObj.getLong("candidateId"),jObj.getString("contentType"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("getCandidateLatestVideos"))
		{
			fileVO = candidateDetailsService.getCandidateLatestVideos(jObj.getLong("candidateId"),jObj.getInt("startIndex"),jObj.getInt("maxRecords"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("candiadteVideoGallariesForUplaod"))
		{
			FileVO fileVOObj = new FileVO();
			fileVOObj.setPath(jObj.getString("path"));
			fileVOObj.setContentType("video");
			fileVOObj.setTitle(jObj.getString("videoTitle"));
			fileVOObj.setDescription(jObj.getString("videoDescription"));
			fileVOObj.setKeywords(jObj.getString("keywords"));
			fileVOObj.setSource(jObj.getString("source"));
			fileVOObj.setFileDate(jObj.getString("fileDate"));
			fileVOObj.setGallaryId(jObj.getLong("gallaryId"));
			fileVOObj.setVisibility(jObj.getString("visibility"));
			
			result = candidateDetailsService.uploadAFile(fileVOObj);
		}
		else if(jObj.getString("task").equalsIgnoreCase("saveDiscription"))
		{
			gallaryVO = new GallaryVO();
			gallaryVO.setCandidateId(jObj.getLong("candidateId"));
			gallaryVO.setDescription(jObj.getString("fileDesc"));
			result = candidateDetailsService.saveDescription(gallaryVO);
		}
		return Action.SUCCESS;
	}
	
	@SuppressWarnings("deprecation")
	public String uploadFiles()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
		String fileName = null;
		String filePath = null;
		FileVO fileVO = new FileVO();
		String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
		
		if(request.getRequestURL().toString().contains(IConstants.PARTYANALYST_SITE))
			filePath = pathSeperator + "var" + pathSeperator + "www" + pathSeperator + "vsites" + pathSeperator + "partyanalyst.com" + pathSeperator + "httpdocs" + pathSeperator + IConstants.UPLOADED_FILES + pathSeperator;
		else
			filePath = context.getRealPath("/")+IConstants.UPLOADED_FILES+"\\";
		
		try 
		{
			String fileType = null;
			Long systime = System.currentTimeMillis();
			Random random = new Random();
			if(userImageContentType.equalsIgnoreCase("text/plain"))
			{
				fileType = userImageContentType.substring(0,userImageContentType.indexOf("/"));
				fileName = systime.toString()+random.nextInt(10000000)+"."+fileType;
			}
			else
			{
				fileType = userImageContentType.substring(userImageContentType.indexOf("/")+1,userImageContentType.length());
				fileName = systime.toString()+random.nextInt(IWebConstants.FILE_RANDOM_NO)+"."+fileType;
			}
			
			fileVO.setName(fileName);
			fileVO.setTitle(getFileTitle());
			fileVO.setDescription(getFileDescription());
			fileVO.setContentType(fileType);
			fileVO.setPath(filePath+pathSeperator+fileName);
			fileVO.setVisibility(getVisibility());
			fileVO.setGallaryId(getGallaryId());
			fileVO.setKeywords(getKeywords());
			fileVO.setLocationScope(getLocationScope());
			fileVO.setLocationValue(getLocationValue().toString());
			fileVO.setFileDate(getFileDate());
			
			/* Here We are saving the to uploaded_files folder */
			File fileToCreate = new File(filePath, fileName);
			FileUtils.copyFile(userImage, fileToCreate);
			
			result = candidateDetailsService.uploadAFile(fileVO);
			
			if(result.getResultCode() == ResultCodeMapper.SUCCESS)
				inputStream = new StringBufferInputStream(SUCCESS);
			else
				inputStream = new StringBufferInputStream("fail");
						
		}
		catch (Exception e) {
			inputStream = new StringBufferInputStream("fail");
	}
		return Action.SUCCESS;
	}


}
