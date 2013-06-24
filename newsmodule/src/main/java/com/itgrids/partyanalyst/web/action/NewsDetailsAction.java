package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.service.IContentManagementService;

public class NewsDetailsAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	private static final org.apache.log4j.Logger LOG = Logger.getLogger(NewsDetailsAction.class); 
	
	private HttpServletRequest request;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	private List<FileVO> fileVOsList;
	private ICandidateDetailsService candidateDetailsService ;
	private ServletContext context;
	private Long contentId;
	private GallaryVO gallaryVO,mainArticleVO;
	private IContentManagementService contentManagementService;
	private Long responseContentId;
	private String fromDate;
	private String toDate;
	private ResultStatus resultStatus;
	
	public Long getResponseContentId() {
		return responseContentId;
	}
	public void setResponseContentId(Long responseContentId) {
		this.responseContentId = responseContentId;
	}
	public void setServletContext(ServletContext context) {
		
		this.context = context;
	}
	public void setServletRequest(HttpServletRequest request) {
		
		this.request = request;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public List<FileVO> getFileVOsList() {
		return fileVOsList;
	}
	public void setFileVOsList(List<FileVO> fileVOsList) {
		this.fileVOsList = fileVOsList;
	}
	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}
	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	
	public Long getContentId() {
		return contentId;
	}
	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
	
	public GallaryVO getGallaryVO() {
		return gallaryVO;
	}
	public void setGallaryVO(GallaryVO gallaryVO) {
		this.gallaryVO = gallaryVO;
	}
	public IContentManagementService getContentManagementService() {
		return contentManagementService;
	}
	public void setContentManagementService(
			IContentManagementService contentManagementService) {
		this.contentManagementService = contentManagementService;
	}
	public GallaryVO getMainArticleVO() {
		return mainArticleVO;
	}
	public void setMainArticleVO(GallaryVO mainArticleVO) {
		this.mainArticleVO = mainArticleVO;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public String execute()
	{	
		session = request.getSession();
        RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
        if(user == null)
         return ERROR;
        else
         return Action.SUCCESS;
	}
	
	public String ajaxHandler()
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO == null)
			return null;
		Long userId = regVO.getRegistrationID();
		
		try{
		jObj = new JSONObject(getTask());
		 if(jObj.getString("task").equalsIgnoreCase("getLatestNews"))
		 {
			 String newsType = "public"; 
			 if(regVO.getUserAccessType()!=null)
				 if(regVO.getUserAccessType().equals("Admin"))
					 newsType = "";
		     fileVOsList = candidateDetailsService.getAllNews(jObj.getInt("firstResult"), jObj.getInt("maxResult"), "News Gallary",872L,newsType);
		 }
		 else if(jObj.getString("task").equalsIgnoreCase("getResponseGallaryDetails"))
			  gallaryVO = contentManagementService.getResponseGallariesForSelectedGallary(jObj.getLong("fileGallaryId"), jObj.getInt("firstResult"), jObj.getInt("maxResult"));
		 else if(jObj.getString("task").equalsIgnoreCase("getMainArticleDetails"))
			 mainArticleVO = contentManagementService.getMainArticlesDetails(jObj.getLong("fileGallaryId"), 0, 3);
		
		 else if(jObj.getString("task").equalsIgnoreCase("getSelectedNewsBetweenDates"))
		 {
			 String newsType ="public";
			 if(regVO.getUserAccessType() != null && regVO.getUserAccessType().equalsIgnoreCase("Admin"))
				 newsType = "";
			 fileVOsList = candidateDetailsService.getNewsBetweenSelectedDates(jObj.getString("fromDate"),jObj.getString("toDate"),jObj.getInt("firstResult"),jObj.getInt("maxResult"),newsType);
		 }
		 
		 else if(jObj.getString("task").equalsIgnoreCase("createUserNewsCategory"))
			resultStatus = candidateDetailsService.createUserNewsCategory(jObj.getString("name"),jObj.getString("visibility"),userId);
		 
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception Occured in ajaxHandler() method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
	
	public String getVideosForGallery(){
		try{
			jObj = new JSONObject(getTask());
			 Long galleryId=jObj.getLong("galId");
			 int maxResult=jObj.getInt("maxRecords");
			 int startRecord=jObj.getInt("startingRecord");
			 fileVOsList = candidateDetailsService.getVideosForGalleryId(galleryId,maxResult,startRecord);
			
			}catch (Exception e) {
				e.printStackTrace();
				Log.error("Exception Occured in ajaxHandler() method, Exception - "+e);
			}
		return Action.SUCCESS;
	}
	
	public String showNewsResponseAction()
	{
		session = request.getSession();
        RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
        if(user == null)
         return ERROR;
        else
		return Action.SUCCESS;
		
	}
	
	public String getCompleteDetailsOfANewsResponse()
	{
		try{
			jObj = new JSONObject(getTask());
			
			Long resonseContentId = jObj.getLong("resonseContentId");
			fileVOsList = contentManagementService.getResponseTrackingNews(resonseContentId);
			
			}catch (Exception e) {
				e.printStackTrace();
				Log.error("Exception Occured in ajaxHandler() method, Exception - "+e);
			}
		return Action.SUCCESS;
	}
	
}
