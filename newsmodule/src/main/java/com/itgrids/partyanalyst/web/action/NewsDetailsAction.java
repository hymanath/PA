package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CandidateNewsCountVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.NewsCountVO;
import com.itgrids.partyanalyst.dto.NewsDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IContentManagementService;
import com.itgrids.partyanalyst.service.INewsMonitoringService;
import com.itgrids.partyanalyst.util.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

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
	private List<SelectOptionVO> selectOptionVOList,galleriesList,newsList;
	private List<CandidateNewsCountVO> newsCountVOsList;
	private INewsMonitoringService newsMonitoringService; 
	private Long candidateId;
	private String locationScope;
	private String categoryIds;
	private String galleryIds;
	private NewsCountVO newsCountVO;
	private String locationIdsList;
	private Long partyId;
	private String newsType;
	private String tempVarForParty;
	
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
	public List<SelectOptionVO> getSelectOptionVOList() {
		return selectOptionVOList;
	}
	public void setSelectOptionVOList(List<SelectOptionVO> selectOptionVOList) {
		this.selectOptionVOList = selectOptionVOList;
	}
	public List<SelectOptionVO> getGalleriesList() {
		return galleriesList;
	}
	public void setGalleriesList(List<SelectOptionVO> galleriesList) {
		this.galleriesList = galleriesList;
	}
	public List<SelectOptionVO> getNewsList() {
		return newsList;
	}
	public void setNewsList(List<SelectOptionVO> newsList) {
		this.newsList = newsList;
	}
	
	public List<CandidateNewsCountVO> getNewsCountVOsList() {
		return newsCountVOsList;
	}
	public void setNewsCountVOsList(List<CandidateNewsCountVO> newsCountVOsList) {
		this.newsCountVOsList = newsCountVOsList;
	}
	
	public INewsMonitoringService getNewsMonitoringService() {
		return newsMonitoringService;
	}
	public void setNewsMonitoringService(
			INewsMonitoringService newsMonitoringService) {
		this.newsMonitoringService = newsMonitoringService;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	
	public String getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(String locationScope) {
		this.locationScope = locationScope;
	}
	
	public String getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}
	public String getGalleryIds() {
		return galleryIds;
	}
	public void setGalleryIds(String galleryIds) {
		this.galleryIds = galleryIds;
	}
	
	public NewsCountVO getNewsCountVO() {
		return newsCountVO;
	}
	public void setNewsCountVO(NewsCountVO newsCountVO) {
		this.newsCountVO = newsCountVO;
	}
	public String getLocationIdsList() {
		return locationIdsList;
	}
	public void setLocationIdsList(String locationIdsList) {
		this.locationIdsList = locationIdsList;
	}
	
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	
	public String getTempVarForParty() {
		return tempVarForParty;
	}
	public void setTempVarForParty(String tempVarForParty) {
		this.tempVarForParty = tempVarForParty;
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
		 else if(jObj.getString("task").equalsIgnoreCase("getLatestResponsefiles"))
		 {
			 fileVOsList = candidateDetailsService.getCandidateNewsResponseNews(jObj.getInt("firstResult"), jObj.getInt("maxResult")); 
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
		 
		 else if(jObj.getString("task").equalsIgnoreCase("getCandidateRelatedGallaries"))
		 {
			 String newsType ="public";
			 //if(regVO.getUserAccessType() != null && regVO.getUserAccessType().equalsIgnoreCase("Admin"))
				// newsType = "";
			 selectOptionVOList = candidateDetailsService.getCandidateRelatedGallaries(jObj.getLong("candidateId"),jObj.getString("fromDate"),jObj.getString("toDate"),IConstants.TDPID,newsType);
		 }
		 
		 else if(jObj.getString("task").equalsIgnoreCase("getCandidateRelatedCategories"))
			selectOptionVOList = candidateDetailsService.getCandidateRelatedCategories(jObj.getLong("candidateId"),jObj.getString("fromDate"),jObj.getString("toDate"),IConstants.TDPID);
		 else if(jObj.getString("task").equalsIgnoreCase("getGallariesForSelectedCategory"))
		 {
			 List<Long> categoryIdsList = new ArrayList<Long>(0);
			 JSONArray categoryIds = jObj.getJSONArray("categoryIds");
			 for(int i=0;i<categoryIds.length();i++)
			  categoryIdsList.add(new Long(categoryIds.get(i).toString()));
			 galleriesList = candidateDetailsService.getGallariesForSelectedCategories(categoryIdsList,jObj.getLong("candidateId"));
		 }
			 
			 
		 
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
				Log.error("Exception Occured in getVideosForGallery() method, Exception - "+e);
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
				Log.error("Exception Occured in getCompleteDetailsOfANewsResponse() method, Exception - "+e);
			}
		return Action.SUCCESS;
	}
	
  public String getNewsForACandidate()
  {
	  try{
		jObj = new JSONObject(getTask());
		if(jObj.getString("task").equalsIgnoreCase("getNewsTitlesForACandidate"))
		  selectOptionVOList = candidateDetailsService.getNewsTitlesForACandidateByGalleryId(jObj.getLong("candidateId"),jObj.getLong("gallaryId"),jObj.getString("fromDate"),jObj.getString("toDate"));
		
		else if(jObj.getString("task").equalsIgnoreCase("getNewsForACandidateByCategoryId"))
		 selectOptionVOList = candidateDetailsService.getNewsForACandidateByCategoryId(jObj.getLong("candidateId"),jObj.getLong("categoryId"),jObj.getString("fromDate"),jObj.getString("toDate"));
		else if(jObj.getString("task").equalsIgnoreCase("getGalleryListForAParty"))
		{
		  List<Long> locationIdsList = new ArrayList<Long>(0);
		  JSONArray locationIdsArray = jObj.getJSONArray("locationIdsArray");
		  if(locationIdsArray != null && locationIdsArray.length() > 0)
		  {
		    for(int i=0;i<locationIdsArray.length();i++)
			 locationIdsList.add(Long.parseLong(locationIdsArray.get(i).toString()));
		  }
		  String locationScope = jObj.getString("locationScope");
		  selectOptionVOList = candidateDetailsService.getGalleryListForAParty(jObj.getString("fromDate"),jObj.getString("toDate"),locationIdsList,locationScope);
		}
		else if(jObj.getString("task").equalsIgnoreCase("getNewsByGalleryId"))
		 newsList = candidateDetailsService.getNewsByGalleryId(jObj.getLong("gallaryId"),jObj.getString("fromDate"),jObj.getString("toDate"));
		
		else if(jObj.getString("task").equalsIgnoreCase("assignResToCandidateOrAGallary")){
			String tempVar = jObj.getString("tempVar");
			Long resFileGalId =jObj.getLong("resFileGalId");			
		 resultStatus = candidateDetailsService.assignResToCandidateOrAGallary(jObj.getLong("candidateId"),jObj.getLong("fileGalleryId"),tempVar == "assignToCandidate"?resFileGalId=0L:resFileGalId,tempVar);
		}
		
	 }catch (Exception e) {
		 e.printStackTrace();
		 Log.error("Exception Occured in getNewsForACandidate() method, Exception - "+e);
		}
		return Action.SUCCESS;
	}
  
  
   public String getLocationWiseNewsDetails()
   {
	   try{
		  session = request.getSession();
	      RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
	      if(user == null)
	       return ERROR;
	      
		jObj = new JSONObject(getTask());
		if(jObj.getString("task").equalsIgnoreCase("getLocationWiseNewsCountForACandidate"))
		{
		 List<Long> categoryIdsList = new ArrayList<Long>(0);
		 List<Long> galleryIdsList = new ArrayList<Long>(0);
		 List<Long> locationIdsList = new ArrayList<Long>(0);
		 
		 JSONArray categoryIdsArray = jObj.getJSONArray("categoryIdsArray");
		 if(categoryIdsArray != null && categoryIdsArray.length() > 0)
		 {
		  for(int i=0;i<categoryIdsArray.length();i++)
			 categoryIdsList.add(Long.parseLong(categoryIdsArray.get(i).toString()));
		 }
		 
		 JSONArray galleryIdsArray = jObj.getJSONArray("galleryIdsArray");
		 if(galleryIdsArray != null && galleryIdsArray.length() > 0)
		 {
          for(int i=0;i<galleryIdsArray.length();i++)
        	galleryIdsList.add(Long.parseLong(galleryIdsArray.get(i).toString()));
		 }
		 
		 JSONArray locationValuesList = jObj.getJSONArray("locationValuesList");
		 if(locationValuesList != null && locationValuesList.length() > 0)
		 {
			for(int i=0;i<locationValuesList.length();i++)
			 locationIdsList.add(Long.parseLong(locationValuesList.get(i).toString()));
		 }
		 
		 
		 String fromDateStr = jObj.getString("fromDate");
		 String toDateStr = jObj.getString("toDate");
		 String tempVar = jObj.getString("tempVar");
		 String locationScope = jObj.getString("locationScope");
		 
		 newsCountVOsList = newsMonitoringService.getNewsCountForACandidate(fromDateStr,toDateStr,categoryIdsList,galleryIdsList,locationIdsList,tempVar,locationScope);
		}
		else if(jObj.getString("task").equalsIgnoreCase("getLocationWiseNewsDetailsForACandidate"))
		{
			Long candidateId = jObj.getLong("candidateId");
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			Integer startIndex = jObj.getInt("firstResult");
			Integer maxIndex = jObj.getInt("maxResult");
			String locationScope = jObj.getString("locationScope");
			String galleryIdsStr = jObj.getString("galleryIdsList");
			String categoryIdsStr = jObj.getString("categoryIdsList");
			
		 fileVOsList = newsMonitoringService.getLocationWiseNewsDetailsForACandidate(candidateId,fromDateStr,toDateStr,locationScope,startIndex,maxIndex,galleryIdsStr,categoryIdsStr);
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getCategoryList"))
		{
		  List<Long> locationIdsList = new ArrayList<Long>(0);
		  String locationScope = jObj.getString("locationScope");
		  JSONArray locationIdsArray = jObj.getJSONArray("locationIdsArray");
		  if(locationIdsArray != null && locationIdsArray.length() > 0)
		  {
			for(int i=0;i<locationIdsArray.length();i++)
			 locationIdsList.add(Long.parseLong(locationIdsArray.getString(i).toString()));
		  }
		 
		  selectOptionVOList = newsMonitoringService.getCategoryList(jObj.getString("fromDate"),jObj.getString("toDate"),locationScope,locationIdsList);
		}
		else if(jObj.getString("task").equalsIgnoreCase("getGalleryListForSelectedCategory"))
		{
		  JSONArray categoryIdsList = jObj.getJSONArray("categoryIdsList");
		  List<Long> categoryIds = new ArrayList<Long>(0);
		  for(int i=0;i<categoryIdsList.length();i++)
			categoryIds.add(Long.parseLong(categoryIdsList.get(i).toString()));
		 galleriesList = newsMonitoringService.getGalleryListForSelectedCategory(categoryIds);
		}
		else if(jObj.getString("task").equalsIgnoreCase("getLocationsListByScopeId"))
		 selectOptionVOList = newsMonitoringService.getLocationsListByScopeId(jObj.getString("locationScope"), jObj.getString("fromDate"),jObj.getString("toDate"));
		
		
	   }catch (Exception e) {
		 e.printStackTrace();
		 Log.error("Exception Occured in getLocationWiseNewsDetails() method, Exception - "+e);
	}
	  return Action.SUCCESS; 
   }
   
   public String getNewsCountForAParty()
   {
	   try{
		  session = request.getSession();
		  RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
		  if(user == null)
		   return ERROR;
		      
		 jObj = new JSONObject(getTask());
		 if(jObj.getString("task").equalsIgnoreCase("getRespondedAndNotRespondedNewsCount"))
		 {
		  String fromDateStr = jObj.getString("fromDate");
		  String toDateStr = jObj.getString("toDate");
		  String locationScope = jObj.getString("locationScope");
		  String tempVar = jObj.getString("tempVar");
		  List<Long> galleryIdsList = new ArrayList<Long>(0);
		  List<Long> categoryIdsList = new ArrayList<Long>(0);
		  List<Long> locationIdsList = new ArrayList<Long>(0);
		  
		  JSONArray galleryIdsArray = jObj.getJSONArray("galleryIdsArray");
		  if(galleryIdsArray != null && galleryIdsArray.length() > 0)
		  {
			for(int i=0;i<galleryIdsArray.length();i++)
			 galleryIdsList.add(Long.parseLong(galleryIdsArray.getString(i).toString()));
		  }
		  
		  JSONArray categoryIdsArray = jObj.getJSONArray("categoryIdsArray");
		  if(categoryIdsArray != null && categoryIdsArray.length() > 0)
		  {
			for(int i=0;i<categoryIdsArray.length();i++)
			 categoryIdsList.add(Long.parseLong(categoryIdsArray.getString(i).toString()));
		  }
		  
		  JSONArray locationValuesList = jObj.getJSONArray("locationValuesList");
		  if(locationValuesList != null && locationValuesList.length() > 0)
		  {
			for(int i=0;i<locationValuesList.length();i++)
			 locationIdsList.add(Long.parseLong(locationValuesList.getString(i).toString()));
		  }
		  
		  newsCountVO = newsMonitoringService.getRespondedAndNotRespondedNewsCount(fromDateStr, toDateStr, categoryIdsList, galleryIdsList, locationIdsList, locationScope, tempVar);
		 }
		 
	   }catch (Exception e) {
		 e.printStackTrace();
		Log.error(" Exception Occured in getNewsCountForAParty() method, Exception - "+e);   
	}
	   return Action.SUCCESS;
   }
   
  public String getNewsDetails()
  {
	  try{
		  session = request.getSession();
		  RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
		  if(user == null)
		   return ERROR;
		      
		 jObj = new JSONObject(getTask());
		 NewsDetailsVO newsDetailsVO = new NewsDetailsVO();
		 
		 newsDetailsVO.setLocationScope(jObj.getString("locationScope"));
		 newsDetailsVO.setFromDateStr(jObj.getString("fromDate"));
		 newsDetailsVO.setToDateStr(jObj.getString("toDate"));
		 newsDetailsVO.setNewsType(jObj.getString("newsType"));
		 newsDetailsVO.setTempVar(jObj.getString("tempVar"));
		 newsDetailsVO.setSelectedPartyId(jObj.getLong("partyId"));
		 newsDetailsVO.setCategoryIdsList(jObj.getString("categoryIds"));
		 newsDetailsVO.setGalleryIdsList(jObj.getString("galleryIds"));
		 newsDetailsVO.setLocationIdsList(jObj.getString("locationIdsList"));
		 newsDetailsVO.setStartIndex(jObj.getInt("firstResult"));
		 newsDetailsVO.setMaxIndex(jObj.getInt("maxResult"));
		 newsDetailsVO.setCandidateId(jObj.getLong("candidateId"));
		 newsDetailsVO.setTempVarForParty(jObj.getString("tempVarForParty"));
		 
		 fileVOsList = newsMonitoringService.getNewsDetailsForAParty(newsDetailsVO);
		  
	  }catch (Exception e) {
		e.printStackTrace();
		Log.error(" Exception Occured in getNewsDetails() method, Exception - "+e);
	}
	  
	return Action.SUCCESS;
  }
  
  
  public String getCandidateCritiesNewsDetails()
  {
	  try{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
		 return ERROR;
		
		jObj = new JSONObject(getTask());
		newsCountVOsList = newsMonitoringService.getCandidateCritiesNewsDetails(jObj.getString("fromDate"), jObj.getString("toDate"));//,jObj.getString("tempVar")
		  
	  }catch (Exception e) {
		  e.printStackTrace();
		  Log.error(" Exception Occured in getCandidateCritiesNewsDetails() method, Exception - "+e);
	}
	  return Action.SUCCESS;
  }
   
   
}
