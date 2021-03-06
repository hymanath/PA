package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IPartyDetailsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.service.impl.CandidateDetailsService;
import com.itgrids.partyanalyst.service.impl.PartyDetailsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;


public class PartyManagementAction extends ActionSupport implements ServletRequestAware, ServletResponseAware,ServletContextAware {

	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(PartyManagementAction.class);
	private List<SelectOptionVO> partyList = new ArrayList<SelectOptionVO>(0);
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private IPartyDetailsService partyDetailsService;
	private IStaticDataService staticDataService;
	private GallaryVO gallaryVO;
	private ResultStatus result;
	private List<GallaryVO> gallaryList;
	private List<FileVO> fileVO;
	private List<SelectOptionVO> selectOptionList;
	private ServletContext context;
	private String userImageContentType;
	private String fileTitle;
	private String fileDescription;
	private String visibility;
	private Long gallaryId;
	private Long source;
	private Long language;
	private String keywords;
	private Long locationScope;
	private Long locationValue;
	private String fileDate;
	private File userImage;
	private InputStream inputStream;
	private ICandidateDetailsService candidateDetailsService;
    private Long partyId;
    private Long electionId;
    private String task = null;
	JSONObject jObj = null;
	private List<SelectOptionVO> electionTypesList;
	private List<SelectOptionVO> electionYearsList;
	private String profileType;
	private String profileId;
	private String profileGalleryType;
	
	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getProfileGalleryType() {
		return profileGalleryType;
	}

	public void setProfileGalleryType(String profileGalleryType) {
		this.profileGalleryType = profileGalleryType;
	}

	public void setElectionYearsList(List<SelectOptionVO> electionYearsList) {
		this.electionYearsList = electionYearsList;
	}

	public List<SelectOptionVO> getElectionYearsList() {
		return electionYearsList;
	}

	public void setElectionTypesList(List<SelectOptionVO> electionTypesList) {
		this.electionTypesList = electionTypesList;
	}

	public List<SelectOptionVO> getElectionTypesList() {
		return electionTypesList;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	
	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
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

	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

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

	public Long getSource() {
		return source;
	}

	public void setSource(Long source) {
		this.source = source;
	}

	public Long getLanguage() {
		return language;
	}

	public void setLanguage(Long language) {
		this.language = language;
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

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public List<SelectOptionVO> getSelectOptionList() {
		return selectOptionList;
	}

	public void setSelectOptionList(List<SelectOptionVO> selectOptionList) {
		this.selectOptionList = selectOptionList;
	}

	public List<FileVO> getFileVO() {
		return fileVO;
	}

	public void setFileVO(List<FileVO> fileVO) {
		this.fileVO = fileVO;
	}

	public List<GallaryVO> getGallaryList() {
		return gallaryList;
	}

	public void setGallaryList(List<GallaryVO> gallaryList) {
		this.gallaryList = gallaryList;
	}

	public ResultStatus getResult() {
		return result;
	}

	public void setResult(ResultStatus result) {
		this.result = result;
	}

	public GallaryVO getGallaryVO() {
		return gallaryVO;
	}

	public void setGallaryVO(GallaryVO gallaryVO) {
		this.gallaryVO = gallaryVO;
	}

	public IPartyDetailsService getPartyDetailsService() {
		return partyDetailsService;
	}

	public void setPartyDetailsService(IPartyDetailsService partyDetailsService) {
		this.partyDetailsService = partyDetailsService;
	}

	

	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	
	public void setServletResponse(HttpServletResponse response){
		this.response =  response;
	}
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}
	
    public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}	

	
	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

public String execute()
 {
	session = request.getSession();
	RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
	if (registrationVO != null) 
	{
	 partyList= partyDetailsService.getAllPartysNamesByUser(registrationVO.getRegistrationID());
	 return Action.SUCCESS;
	}
	else
		return IConstants.NOT_LOGGED_IN;
	 
 }

// @Override
 public void setServletContext(ServletContext context) {
 	// TODO Auto-generated method stub
 	this.context=context;
 }
 public String ajaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(jObj.getString("task").equalsIgnoreCase("saveDiscription"))
		{
			gallaryVO = new GallaryVO();
			gallaryVO.setCandidateId(jObj.getLong("partyId"));
			gallaryVO.setDescription(jObj.getString("fileDesc"));
			result = partyDetailsService.saveDescription(gallaryVO);
		}
		else if(jObj.getString("task").equalsIgnoreCase("getPartyPhotoGallaryDetail"))
		{
		    fileVO = partyDetailsService.getPartyPhotoGallaryDetail(jObj.getLong("partyId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),IConstants.PHOTO_GALLARY);
		}
		else if(jObj.getString("task").equalsIgnoreCase("createNewGallary") || 
				jObj.getString("task").equalsIgnoreCase("createVideoNewGallary"))
		{
			gallaryVO = new GallaryVO();
			gallaryVO.setCandidateId(jObj.getLong("partyId"));
			gallaryVO.setUserId(regVO.getRegistrationID());
			gallaryVO.setGallaryName(jObj.getString("name"));
			gallaryVO.setDescription(jObj.getString("desc"));
			gallaryVO.setVisibility(jObj.getString("visibility"));
			if(jObj.getString("createOrUpdate").trim().equalsIgnoreCase("Create"))
			{
				gallaryVO.setContentType(jObj.getString("contentType"));
			}
			if(jObj.getString("createOrUpdate").trim().equalsIgnoreCase("Update"))
			{
				gallaryVO.setGallaryId(jObj.getLong("gallaryId"));
			}
			result = partyDetailsService.createNewGallaryOrUpdateGallary(gallaryVO,jObj.getString("createOrUpdate").trim());
		}
		else if(jObj.getString("task").trim().equalsIgnoreCase("partyGallariesForUplaod"))
		{
			selectOptionList = partyDetailsService.getPartyGallarySelectList(jObj.getLong("partyId"),jObj.getString("contentType"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("partyVideoGallariesForUplaod"))
		{
			FileVO fileVOObj = new FileVO();
			fileVOObj.setPath(jObj.getString("path"));
			fileVOObj.setContentType("video");
			fileVOObj.setTitle(jObj.getString("videoTitle"));
			fileVOObj.setDescription(jObj.getString("videoDescription"));
			fileVOObj.setKeywords(jObj.getString("keywords"));
			fileVOObj.setSourceId(jObj.getLong("sourceId"));
			fileVOObj.setLanguegeId(jObj.getLong("languageId"));
			fileVOObj.setFileDate(jObj.getString("fileDate"));
			fileVOObj.setGallaryId(jObj.getLong("gallaryId"));
			fileVOObj.setVisibility(jObj.getString("visibility"));
			
			result = candidateDetailsService.uploadAFile(fileVOObj);
		}
		else if(jObj.getString("task").equalsIgnoreCase("getElectionType"))
		{
			fileVO = partyDetailsService.getElectionType();
		}
		else if(jObj.getString("task").equalsIgnoreCase("partyDescriptionUpdate"))
		{
			gallaryList=partyDetailsService.getPartyProfileInfo(jObj.getLong("partyId"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("deleteDiscription"))
		 {
			result= partyDetailsService.deleteProfileDescById(jObj.getLong("profDescId"));
		 }
		else if(jObj.getString("task").equalsIgnoreCase("partyVideoVisibility"))
		{
			String value = partyDetailsService.getPartyVisibility(jObj.getLong("gallaryId"));
			GallaryVO gallaryvo = new GallaryVO();
			gallaryvo.setIsPrivate(value);
			gallaryVO = gallaryvo;
			
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("partyNewsVisibility"))
		{
			String value = partyDetailsService.getPartyVisibility(jObj.getLong("gallaryId"));
			GallaryVO gallaryvo = new GallaryVO();
			gallaryvo.setIsPrivate(value);
			gallaryVO = gallaryvo;
			
		}
		else if(jObj.getString("task").equalsIgnoreCase("partyPhotoVisibility"))
		{
			String value = partyDetailsService.getPartyVisibility(jObj.getLong("gallaryId"));
			GallaryVO gallaryvo = new GallaryVO();
			gallaryvo.setIsPrivate(value);
			gallaryVO = gallaryvo;
			
		}
		
		
		else if(jObj.getString("task").equalsIgnoreCase("updateProfileDiscription"))
		{
			List<Long> orderNo = new ArrayList<Long>();
			List<String> description = new ArrayList<String>();
			List<Long> condiProfDescId = new ArrayList<Long>();
			gallaryList =new ArrayList<GallaryVO>();
			JSONArray jOrderNo = jObj.getJSONArray("orderNoArr");
			JSONArray jDescription = jObj.getJSONArray("descriptionArr");
			JSONArray jprofDescId = jObj.getJSONArray("profDescIdArr");
			Long partyId = jObj.getLong("partyId");
			try{
			    
			    for (int i = 0; i < jOrderNo.length(); i++) {
				    orderNo.add(Long.valueOf(jOrderNo.get(i).toString()));
				    description.add(jDescription.get(i).toString());
				    condiProfDescId.add(Long.valueOf(jprofDescId.get(i).toString()));
			      }
			    for (int i = 0; i < jOrderNo.length(); i++) {
				   GallaryVO gallary = new GallaryVO();
				   gallary.setOrderNo(orderNo.get(i));
				   gallary.setDescription(description.get(i));
				   gallary.setPartyProfileDescriptionId(condiProfDescId.get(i));
				   gallaryList.add(gallary);
			      }
			      result = partyDetailsService.updateProfileDescription(gallaryList,partyId);
			 }
			
			
			catch(Exception e)
			       {
				e.printStackTrace();
			    }
		}
		else if(jObj.getString("task").equalsIgnoreCase("getParties"))
		{
			selectOptionList = partyDetailsService.getAllPartysNames();
		}
		
		return Action.SUCCESS;
} 
 public String getPartyGallaryDescription(){
	 
	 try {
		jObj = new JSONObject(getTask());
		if(jObj.getString("task").equalsIgnoreCase("UpdatePartyGallary")){
			Long galleryId = Long.valueOf(jObj.getString("gallaryId"));
			Long partyId = Long.valueOf(jObj.getString("partyId"));
			gallaryVO = partyDetailsService.getPartyGalleryDetails(galleryId,partyId); 
		}
		
		
		
		
	} catch (ParseException e) {
		
		e.printStackTrace();
	}
	 return Action.SUCCESS;
 }
  public String getElectionIdsAndYears()
  {
	  Long stateId = null;
	  String electionType = null;
	  try {
			jObj = new JSONObject(getTask());
			
				electionType = jObj.getString("electionType");
			 if(jObj.getString("stateId") != null && jObj.getString("stateId").trim().length()>0)
				stateId = jObj.getLong("stateId");
			
			selectOptionList = staticDataService.electionYearsForstateAndElectionType(stateId,electionType);
	  } 
	  catch (ParseException e) {
			e.printStackTrace();
		}
	  return Action.SUCCESS;
  }
  
  public String uploadFiles()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
		String fileName = null;
		String filePath = null;
		FileVO fileVO = new FileVO();
		String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
		
		if(request.getRequestURL().toString().contains(IConstants.PARTYANALYST_SITE))
			filePath = IWebConstants.STATIC_CONTENT_FOLDER_URL + IConstants.UPLOADED_FILES + pathSeperator;
		else
			filePath = context.getRealPath("/")+IConstants.UPLOADED_FILES + pathSeperator;
		
		if(profileType != null && profileId != null && profileGalleryType != null)
		{
			filePath = filePath + profileType + pathSeperator + profileId + pathSeperator + profileGalleryType + pathSeperator;
		}
		
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
			if(fileType.equals("pjpeg"))
				fileType = "jpeg";
			fileVO.setName(fileName);
			fileVO.setDescription(getFileDescription());
			fileVO.setContentType(fileType);
			fileVO.setLanguegeId(getLanguage());
			fileVO.setIds(getPartyId());//setting party id
			fileVO.setElectionId(getElectionId());
			fileVO.setStateId(getLocationValue());
			
			if(profileType != null && profileId != null && profileGalleryType != null)
				fileVO.setPath(IWebConstants.UPLOADED_FILES+"/"+profileType+"/"+profileId+"/"+profileGalleryType+"/"+fileName);
			else
				fileVO.setPath(IWebConstants.UPLOADED_FILES+"/"+fileName);
			
			/* Here We are saving the to uploaded_files folder */
			File fileToCreate = new File(filePath, fileName);
			FileUtils.copyFile(userImage, fileToCreate);
			
			result = partyDetailsService.uploadPartyManifesto(fileVO);
			
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
  
  public String getElectionTypes(){
	  
	  try {
		jObj = new JSONObject(getTask());
		Long stateId = Long.valueOf(jObj.getString("stateId"));
		Long partyId = Long.valueOf(jObj.getString("partyId"));
		electionTypesList = partyDetailsService.getElectionTypesBasedOnStateIdAndPartyId(partyId , stateId);
		
		
	} catch (ParseException e) {
		e.printStackTrace();
	}
	  
	  return Action.SUCCESS;
  }
  
  public String getElectionYearsBasedOnElecTypeIdPartyIdAndStateId(){
	  
	  try {
		jObj = new JSONObject(getTask());
		Long electionTypeId = Long.valueOf(jObj.getString("electionTypeId"));
		Long stateId = Long.valueOf(jObj.getString("stateId"));
		Long partyId = Long.valueOf(jObj.getString("partyId"));
		electionYearsList = partyDetailsService.getElectionYearsBasedOnElectionTypeIdAndPartyId(electionTypeId, partyId, stateId);
	} 
	  catch (ParseException e) {
		e.printStackTrace();
	}
	  
	  return Action.SUCCESS;
  }
  
 /* public String getPartyRelatedManifestoFileBasedOnElectionYear(){
	  try {
		jObj = new JSONObject(getTask());
		Long electionId = Long.valueOf(jObj.getString("electionId"));
		Long partyId = Long.valueOf(jObj.getString("partyId"));
		Long stateId = Long.valueOf(jObj.getString("stateId"));
		fileVO = partyDetailsService.getPartyRelatedManifestoBasedOnYear(electionId,partyId,stateId);
			
		
	} catch (ParseException e) {
		e.printStackTrace();
	}
	  return Action.SUCCESS;
  }*/
  
}
