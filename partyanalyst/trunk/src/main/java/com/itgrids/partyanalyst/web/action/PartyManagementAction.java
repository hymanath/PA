package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IPartyDetailsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;


public class PartyManagementAction extends ActionSupport implements ServletRequestAware, ServletResponseAware,ServletContextAware {

	
	private static final long serialVersionUID = 1L;
	private List<SelectOptionVO> partyList;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private IPartyDetailsService partyDetailsService;
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

	private String task = null;
	JSONObject jObj = null;
	

	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}

	@Override
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
	
 public String execute()
 {
	 partyList= partyDetailsService.getAllPartysNames();
	 return SUCCESS;
	 
 }

 @Override
 public void setServletContext(ServletContext context) {
 	// TODO Auto-generated method stub
 	context=context;
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
		else if(jObj.getString("task").equalsIgnoreCase("partyGallariesForUplaod"))
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
		return Action.SUCCESS;
} 
}
