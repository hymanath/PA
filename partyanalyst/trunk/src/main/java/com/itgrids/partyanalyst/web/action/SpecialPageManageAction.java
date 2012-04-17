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

import org.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dao.columns.enums.RegistrationColumnNames;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.MetaInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SpecialPageVO;
import com.itgrids.partyanalyst.service.ISpecialPageService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class SpecialPageManageAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware,ServletContextAware{

	private static final long serialVersionUID = -957791701984246754L;
	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private JSONObject jobj;
	private List<SpecialPageVO> specialPageList;
	private String task=null;
	private ResultStatus result;
	private ISpecialPageService specialPageService;
	private SpecialPageVO specialPageVO;
	private GallaryVO gallaryVO;
	private HttpSession session;
	private List<SelectOptionVO> selectOptionList;
	private String profileType;
	private String profileId;
	private String profileGalleryType;
	private String userImageContentType;
	private long specialPageId;
	private String fileDescription;
	private Long electionId;
	private Long language;
	private Long locationValue;
	private File userImage;
	private InputStream inputStream;
	private List<SelectOptionVO> specialPages;
	
	public List<SelectOptionVO> getSpecialPages() {
		return specialPages;
	}

	public void setSpecialPages(List<SelectOptionVO> specialPages) {
		this.specialPages = specialPages;
	}

	public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public Long getLanguage() {
		return language;
	}

	public void setLanguage(Long language) {
		this.language = language;
	}

	public Long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}

	public long getSpecialPageId() {
		return specialPageId;
	}

	public void setSpecialPageId(long specialPageId) {
		this.specialPageId = specialPageId;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

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

	public List<SelectOptionVO> getSelectOptionList() {
		return selectOptionList;
	}

	public void setSelectOptionList(List<SelectOptionVO> selectOptionList) {
		this.selectOptionList = selectOptionList;
	}

	public GallaryVO getGallaryVO() {
		return gallaryVO;
	}

	public void setGallaryVO(GallaryVO gallaryVO) {
		this.gallaryVO = gallaryVO;
	}

	public SpecialPageVO getSpecialPageVO() {
		return specialPageVO;
	}

	public void setSpecialPageVO(SpecialPageVO specialPageVO) {
		this.specialPageVO = specialPageVO;
	}

	public ResultStatus getResult() {
		return result;
	}

	public void setResult(ResultStatus result) {
		this.result = result;
	}

	public JSONObject getJobj() {
		return jobj;
	}

	public void setJobj(JSONObject jobj) {
		this.jobj = jobj;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	
	public List<SpecialPageVO> getSpecialPageList() {
		return specialPageList;
	}

	public void setSpecialPageList(List<SpecialPageVO> specialPageList) {
		this.specialPageList = specialPageList;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	

	public ISpecialPageService getSpecialPageService() {
		return specialPageService;
	}

	public void setSpecialPageService(ISpecialPageService specialPageService) {
		this.specialPageService = specialPageService;
	}

	public String execute()throws Exception
	{
		RegistrationVO registrationVO  = (RegistrationVO) request.getSession().getAttribute(IConstants.USER);
		if(registrationVO !=null && registrationVO.getIsAdmin().equalsIgnoreCase("true"))
		{
			specialPages = specialPageService.getSpecialPageIdsList();
			return SUCCESS;
		}
		else
			return IConstants.NOT_LOGGED_IN;
	}
	public String getEventDescription(){
		try{
			jobj=new JSONObject(getTask());
		}catch(ParseException e){
			e.printStackTrace();
		}
		specialPageList = specialPageService.getEventProfileInfo(jobj.getLong("specialPageId"));
		return Action.SUCCESS; 
	}
	
	public String deleteEventDescription()
	{
		try{
			jobj=new JSONObject(getTask());
		}catch(ParseException e){
			e.printStackTrace();
		
		}
		result=specialPageService.deleteEventProfileDescById(jobj.getLong("specialPageDescriptionId"));
		return Action.SUCCESS;
		
	}
	
	public String saveEventDescription(){
		
		try{
			jobj = new JSONObject(getTask());
		}catch(ParseException e){
			e.printStackTrace();
		}
		specialPageVO = new SpecialPageVO();
		specialPageVO.setSpecialPageId(jobj.getLong("specialPageId"));
		specialPageVO.setDescription(jobj.getString("fileDesc"));
		result = specialPageService.saveDescription(specialPageVO);
		return Action.SUCCESS;
	}

	public String updateEventDescription(){
		try{
			jobj = new JSONObject(getTask());
		}catch(ParseException e){
			e.printStackTrace();
		}
		List<Long> orderNo = new ArrayList<Long>(0);
		List<String> description = new ArrayList<String>(0);
		List<Long> profileDescriptionId = new ArrayList<Long>(0);
		specialPageList = new ArrayList<SpecialPageVO>();
		JSONArray jorderNo = jobj.getJSONArray("orderNoArr");
		JSONArray jdescription = jobj.getJSONArray("descriptionArr");
		JSONArray jprofileDescriptionId = jobj.getJSONArray("profDescIdArr");
		Long specialPageId = jobj.getLong("specialPageId");
		for(int j=0; j<jorderNo.length(); j++)
		{
		orderNo.add(new Long(jorderNo.getString(j).toString()));
		description.add(jdescription.getString(j).toString());
		profileDescriptionId.add(new Long(jprofileDescriptionId.getString(j).toString()));
		}
		for(int j=0; j<jorderNo.length(); j++)
		{
			specialPageVO = new SpecialPageVO();
			specialPageVO.setOrderNo(orderNo.get(j));
			specialPageVO.setDescription(description.get(j));
			specialPageVO.setSpecialPageDescriptionId(profileDescriptionId.get(j));
			specialPageList.add(specialPageVO);
			
		}
		
		result = specialPageService.updateEventProfileDescripton(specialPageList,specialPageId);
		return Action.SUCCESS;
	}
	
	public String AjaxHandler()
	{
		try {
			jobj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO != null)
		if(jobj.getString("task").equalsIgnoreCase("createVideoNewGallary") || jobj.getString("task").equalsIgnoreCase("createEventNewGallary") || jobj.getString("task").equalsIgnoreCase("createphotoGallary")) 
				{
			gallaryVO = new GallaryVO();
			gallaryVO.setDescription(jobj.getString("desc"));
			gallaryVO.setVisibility(jobj.getString("visibility"));
			gallaryVO.setGallaryName(jobj.getString("name"));
			gallaryVO.setCandidateId(jobj.getLong("specialPageId"));
			gallaryVO.setUserId(regVO.getRegistrationID());
			
			if(jobj.getString("createOrUpdate").trim().equalsIgnoreCase("Create"))
			{
				gallaryVO.setContentType(jobj.getString("contentType"));
			}
			if(jobj .getString("createOrUpdate").trim().equalsIgnoreCase("Update"))
			{
				gallaryVO.setGallaryId(jobj.getLong("gallaryId"));
			}
			result = specialPageService.createNewGallaryOrUpdateGallary(gallaryVO,jobj.getString("createOrUpdate").trim());
				}
		else if(jobj.getString("task").equalsIgnoreCase("getSource")){
			selectOptionList = specialPageService.getSource();	
		}
		else if(jobj.getString("task").equalsIgnoreCase("getLanguage")){
			selectOptionList = specialPageService.getLanguage();	
		}
		else if(jobj.getString("task").equalsIgnoreCase("eventVideoGallariesForUpload"))
		{
			FileVO fileVOObj = new FileVO();
			fileVOObj.setPath(jobj.getString("path"));
			fileVOObj.setContentType("video");
			fileVOObj.setTitle(jobj.getString("videoTitle"));
			fileVOObj.setDescription(jobj.getString("videoDescription"));
			fileVOObj.setKeywords(jobj.getString("keywords"));
			fileVOObj.setSourceId(jobj.getLong("sourceId"));
			fileVOObj.setLanguegeId(jobj.getLong("languageId"));
			fileVOObj.setFileDate(jobj.getString("fileDate"));
			fileVOObj.setGallaryId(jobj.getLong("gallaryId"));
			fileVOObj.setVisibility(jobj.getString("visibility"));
			
			result = specialPageService.uploadAFile(fileVOObj);
		}
		else if(jobj.getString("task").equalsIgnoreCase("partyGallariesForUplaod") || jobj.getString("task").equalsIgnoreCase("photoGallariesForUplaod") ||jobj.getString("task").equalsIgnoreCase("photoGallariesForUpdataPhoto")){
			selectOptionList = specialPageService.getEventGallarySelectList(jobj.getLong("specialPageId"),jobj.getString("contentType"));
		}
		else if(jobj.getString("task").equalsIgnoreCase("eventGallariesForUplaod"))
		{
			selectOptionList = specialPageService.getPartyGallarySelectList(jobj.getLong("specialPageId"),jobj.getString("contentType"));
		}
		
		else if(jobj.getString("task").equalsIgnoreCase("createNewSpecialPage"))
		{
			GallaryVO gallary = new GallaryVO();
			gallary.setGallaryName(jobj.getString("name"));
			gallary.setVisibility(jobj.getString("title"));
			gallary.setContentType(jobj.getString("heading"));
			
			result = specialPageService.createNewSpecialPage(gallary);
		}
		
		else if(jobj.getString("task").equalsIgnoreCase("addMetaInformation"))
		{
			MetaInfoVO metaInfoVO = new MetaInfoVO();
			metaInfoVO.setSpecialPageId(jobj.getLong("specialPageId"));
			metaInfoVO.setKeywords(jobj.getString("keywords"));
			metaInfoVO.setDescription(jobj.getString("description"));
			
			result = specialPageService.saveMetaInfoForASpecialPage(metaInfoVO);
		}
		
		return Action.SUCCESS;
	} 
}
	
	
	
	