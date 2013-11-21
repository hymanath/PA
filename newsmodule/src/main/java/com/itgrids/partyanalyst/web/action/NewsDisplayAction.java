package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.INewsMonitoringService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.opensymphony.xwork2.Action;

public class NewsDisplayAction implements ServletRequestAware{
	
	private static final Logger log = Logger.getLogger(NewsDisplayAction.class);
	private INewsMonitoringService newsMonitoringService;
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	private JSONObject jObj;
	private List<FileVO> returnVal;
	private DateUtilService dateUtilService = new DateUtilService();
	private ResultStatus resultStatus;
	private FileVO savedDetails,fileVO;
	private List<FileVO> fileVOs;
	private String reportUrl;
	
	public FileVO getSavedDetails() {
		return savedDetails;
	}

	public void setSavedDetails(FileVO savedDetails) {
		this.savedDetails = savedDetails;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public INewsMonitoringService getNewsMonitoringService() {
		return newsMonitoringService;
	}

	public void setNewsMonitoringService(
			INewsMonitoringService newsMonitoringService) {
		this.newsMonitoringService = newsMonitoringService;
	}
    
	public List<FileVO> getReturnVal() {
		return returnVal;
	}

	public void setReturnVal(List<FileVO> returnVal) {
		this.returnVal = returnVal;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}	
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String execute(){
		
		return Action.SUCCESS;
	}
	
	
	public List<FileVO> getFileVOs() {
		return fileVOs;
	}

	public FileVO getFileVO() {
		return fileVO;
	}

	public void setFileVO(FileVO fileVO) {
		this.fileVO = fileVO;
	}

	public void setFileVOs(List<FileVO> fileVOs) {
		this.fileVOs = fileVOs;
	}

	public String getReportUrl() {
		return reportUrl;
	}

	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}

	public String getNews(){
		return Action.SUCCESS;
		/*
	 try{
		 jObj = new JSONObject(getTask());
		 session = request.getSession();
		 RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(jObj.getString("queryType").trim().equalsIgnoreCase("getNews"))
		{	
		    FileVO fileVO = new FileVO();
			if(jObj.getString("task").trim().equalsIgnoreCase("byTodayDate"))
			{
			   fileVO.setExistingFrom(dateUtilService.getCurrentDateAndTime());
			   fileVO.setIdentifiedOn(dateUtilService.getCurrentDateAndTime());
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("byThisWeek"))
			{
			   fileVO.setExistingFrom(getStartDayOfWeek());
			   fileVO.setIdentifiedOn(dateUtilService.getCurrentDateAndTime());
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("byThisMonth"))
			{
			   fileVO.setExistingFrom(getStartDayOfMonth());
			   fileVO.setIdentifiedOn(dateUtilService.getCurrentDateAndTime());
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("betweendates"))
			{
			   if(jObj.getString("fromDate").trim().length() > 0)
			     fileVO.setExistingFrom(getDate(jObj.getString("fromDate").trim()));
			   if(jObj.getString("toDate").trim().length() > 0)
			     fileVO.setIdentifiedOn(getDate(jObj.getString("toDate").trim()));
			}
			
			if(regVO!=null)
				fileVO.setCandidateId(regVO.getRegistrationID());
			    fileVO.setFileType(jObj.getString("fileType"));
			
		    if(jObj.getString("sourceId") !=null && jObj.getString("sourceId").trim().length()>0)
				fileVO.setSourceId(jObj.getLong("sourceId"));
		    if(jObj.getString("languegeId") !=null && jObj.getString("languegeId").trim().length()>0)
			    fileVO.setLanguegeId(jObj.getLong("languegeId"));
		    if(jObj.getString("categoryId") !=null && jObj.getString("categoryId").trim().length()>0)
			    fileVO.setCategoryId(jObj.getLong("categoryId"));
		    if(jObj.getString("newsImportanceId") !=null && jObj.getString("newsImportanceId").trim().length()>0)
			    fileVO.setNewsImportanceId(jObj.getLong("newsImportanceId"));
		    if(jObj.getString("locationScope") !=null && jObj.getString("locationScope").trim().length()>0)
			    fileVO.setLocationScope(jObj.getLong("locationScope"));
		    if(jObj.getString("location") !=null && jObj.getString("location").trim().length()>0)
			    fileVO.setLocation(jObj.getLong("location"));
			
			
			//returnVal = newsMonitoringService.getNewsForRegisterUsers(fileVO);
		    returnVal = newsMonitoringService.getNewsForRegisterUsers1(fileVO);
		}
		
		
		else if(jObj.getString("queryType").trim().equalsIgnoreCase("getCount"))
		{
			FileVO fileVO = new FileVO();
			if(jObj.getString("sourceId") !=null && jObj.getString("sourceId").trim().length()>0)
				fileVO.setSourceId(jObj.getLong("sourceId"));
		    if(jObj.getString("languegeId") !=null && jObj.getString("languegeId").trim().length()>0)
			    fileVO.setLanguegeId(jObj.getLong("languegeId"));
		    if(jObj.getString("categoryId") !=null && jObj.getString("categoryId").trim().length()>0)
			    fileVO.setCategoryId(jObj.getLong("categoryId"));
		    if(jObj.getString("newsImportanceId") !=null && jObj.getString("newsImportanceId").trim().length()>0)
		    	fileVO.setNewsImportanceId(jObj.getLong("newsImportanceId"));
		    
			if(jObj.getString("task").trim().equalsIgnoreCase("byTodayDate"))
			{
			   returnVal = newsMonitoringService.getAllCountDetails(dateUtilService.getCurrentDateAndTime(),dateUtilService.getCurrentDateAndTime(),jObj.getString("fileType"),regVO.getRegistrationID(),fileVO);
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("byThisWeek"))
			{
			   returnVal = newsMonitoringService.getAllCountDetails(getStartDayOfWeek(),dateUtilService.getCurrentDateAndTime(),jObj.getString("fileType"),regVO.getRegistrationID(),fileVO);
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("byThisMonth"))
			{
			   returnVal = newsMonitoringService.getAllCountDetails(getStartDayOfMonth(),dateUtilService.getCurrentDateAndTime(),jObj.getString("fileType"),regVO.getRegistrationID(),fileVO);
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("betweendates"))
			{
				Date from = null;
				Date to = null;
			   if(jObj.getString("fromDate").trim().length() > 0)
			     from = getDate(jObj.getString("fromDate").trim());
			   if(jObj.getString("toDate").trim().length() > 0)
			     to = getDate(jObj.getString("toDate").trim());
			   returnVal = newsMonitoringService.getAllCountDetails(from,to,jObj.getString("fileType"),regVO.getRegistrationID(),fileVO);
			}
			
		}
		else if(jObj.getString("queryType").trim().equalsIgnoreCase("getAllSourceDetails"))
		{
			returnVal = newsMonitoringService.getAllSourceDetails();
		}
		else if(jObj.getString("queryType").trim().equalsIgnoreCase("getAllCategoryDetails"))
		{
			returnVal = newsMonitoringService.getAllCategoryDetails();
		}
		else if(jObj.getString("queryType").trim().equalsIgnoreCase("getAllSourceLanguageDetails"))
		{
			returnVal = newsMonitoringService.getAllSourceLanguageDetails();
		}
		
	 }
	 catch(Exception e){
		 e.printStackTrace();
	 }
	 return Action.SUCCESS;
	*/}
	public Date getCurrentDate(){
		try {
		java.util.Date now = new java.util.Date();
        String DATE_FORMAT = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        String strDateNew = sdf.format(now);        
			now = sdf.parse(strDateNew);
			System.out.println("todayDate = "+now);
			return now;
		} catch (ParseException e) {
			log.error("Exception rised in getCurrentDate() ",e);
			return null;
		}
	}
  public Date getStartDayOfWeek(){
	 try{
	  Date currentDate = dateUtilService.getCurrentDateAndTime();
	  Calendar cal = Calendar.getInstance(); 
	  cal.setTime(currentDate);
	  int currentDOW = cal.get(Calendar.DAY_OF_WEEK);
	   cal.add(Calendar.DAY_OF_YEAR, (currentDOW * -1)+1);
	   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	   return sdf.parse(sdf.format(cal.getTime()));
	 } catch (ParseException e) {
		 log.error("Exception rised in getStartDayOfWeek() ",e);
			return null;
		}
  }
  public Date getStartDayOfMonth(){
		 try{
		  Date currentDate = dateUtilService.getCurrentDateAndTime();
		  Calendar cal = Calendar.getInstance(); 
		  cal.setTime(currentDate);
		  int currentDOW = cal.get(Calendar.DAY_OF_MONTH);
		   cal.add(Calendar.DAY_OF_YEAR, (currentDOW * -1)+1);
		   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		   return sdf.parse(sdf.format(cal.getTime()));
		 } catch (ParseException e) {
			 log.error("Exception rised in getStartDayOfMonth() ",e);
				return null;
			}
	  }
  public Date getDate(String dateStr){
	  String[] dateArray =  dateStr.split("-");
	  Calendar cal = Calendar.getInstance(); 
	  cal.set(Integer.parseInt(dateArray[0]),Integer.parseInt(dateArray[1])-1, Integer.parseInt(dateArray[2]));
	  return cal.getTime();
  }
  public String getDetails(){/*
		if(log.isDebugEnabled())
		log.debug("Enter into getGraphDetails Method of NewsDisplayAction ");
	  try{ 
		 jObj = new JSONObject(getTask());
		 session = request.getSession();
		 RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		 Date fromDate = null;
		 Date toDate = null;
		 String fileType  = jObj.getString("fileType");
		 Long regId  = regVO.getRegistrationID();
		 FileVO fileVO = new FileVO();
		 
		    if(jObj.getString("task").trim().equalsIgnoreCase("byTodayDate"))
			{
		    	fromDate = dateUtilService.getCurrentDateAndTime();
		    	toDate = dateUtilService.getCurrentDateAndTime();
			  
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("byThisWeek"))
			{
				fromDate = getStartDayOfWeek();
		    	toDate = dateUtilService.getCurrentDateAndTime();
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("byThisMonth"))
			{
				fromDate = getStartDayOfMonth();
		    	toDate = dateUtilService.getCurrentDateAndTime();
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("betweendates"))
			{
				
			   if(jObj.getString("fromDate").trim().length() > 0)
				   fromDate = getDate(jObj.getString("fromDate").trim());
			   if(jObj.getString("toDate").trim().length() > 0)
				   toDate = getDate(jObj.getString("toDate").trim());
			   
			}
		   
			if(jObj.getString("sourceId") != null && jObj.getString("sourceId").trim().length()>0)
				fileVO.setSourceId(jObj.getLong("sourceId"));
			
		    if(jObj.getString("languegeId") !=null && jObj.getString("languegeId").trim().length()>0)
			    fileVO.setLanguegeId(jObj.getLong("languegeId"));
		    
		    if(jObj.getString("categoryId") !=null && jObj.getString("categoryId").trim().length()>0)
			    fileVO.setCategoryId(jObj.getLong("categoryId"));
		    
		    if(jObj.getString("newsImportanceId") !=null && jObj.getString("newsImportanceId").trim().length()>0)
		    	fileVO.setNewsImportanceId(jObj.getLong("newsImportanceId"));
		    
		 if(jObj.getString("queryType").trim().equalsIgnoreCase("categoryDetailsForGraph")){
			 returnVal = newsMonitoringService.getCategoryCountDetailsForGraph(fromDate,toDate,fileType,regId,fileVO);
		 }
		 else if(jObj.getString("queryType").trim().equalsIgnoreCase("sourceDetailsForGraph")){
			 returnVal = newsMonitoringService.getSourceCountDetailsForGraph(fromDate,toDate,fileType,regId,fileVO);
		 }
		 else if(jObj.getString("queryType").trim().equalsIgnoreCase("languageDetailsForGraph")){
			 returnVal = newsMonitoringService.getLanguageCountDetailsForGraph(fromDate,toDate,fileType,regId,fileVO); 
		 }
         else if(jObj.getString("queryType").trim().equalsIgnoreCase("newsImpDetailsForGraph")){
        	 returnVal = newsMonitoringService.getNewsImpCountDetailsForGraph(fromDate,toDate,fileType,regId,fileVO);
		 }
	  }
	  catch(Exception e){
		  log.error("Exception rised in getGraphDetails Method of NewsDisplayAction ",e); 
	  }
		 return Action.SUCCESS;
	 */
	  return Action.SUCCESS;
	  }
   public String updateDeleteNews(){
	   if(log.isDebugEnabled())
			log.debug("Enter into updateDeleteNews Method of NewsDisplayAction ");
	   try{ 
		    jObj = new JSONObject(getTask()); 
		    
		     session = request.getSession();
			 RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			 
		   FileVO fileVO = new FileVO();
		   if(jObj.getString("task").trim().equalsIgnoreCase("Update"))
		   { 
		    fileVO.setTitle(jObj.getString("title"));
		    fileVO.setDescription(jObj.getString("description"));
		    fileVO.setSourceId(jObj.getLong("sourceId"));
		    fileVO.setLanguegeId(jObj.getLong("languegeId"));
		    fileVO.setCategoryId(jObj.getLong("categoryId"));
		    fileVO.setNewsImportanceId(jObj.getLong("newsImportanceId"));
		    
		    
		    fileVO.setGallaryId(jObj.getLong("gallaryId"));
		    fileVO.setKeywords(jObj.getString("keywords"));
		    fileVO.setFileDate(jObj.getString("fileDate"));
		    fileVO.setLocationScope(jObj.getLong("locationScopeId"));
		    fileVO.setRegionValue(jObj.getLong("locationScopeValue"));
		    fileVO.setVisibility(jObj.getString("visibility"));
		    fileVO.setFileGallaryId(jObj.getLong("fileGallaryId"));
		    fileVO.setFlagSet(jObj.getString("flagInd"));
		    fileVO.setNewsDescription(jObj.getString("newsDescription"));
		    fileVO.setUserId(regVO.getRegistrationID());
		   
		    
		   } 
		   List<FileVO> sourceFilesList = new ArrayList<FileVO>();
		   List<FileVO> languageFilesList = new ArrayList<FileVO>();
		    String sourceString = jObj.getString("sourceData");
		    String languageString = jObj.getString("languageData");
		    if(jObj.getString("task").trim().equalsIgnoreCase("Update"))
		   {
		    String[] sourceStrArr = sourceString.split("-");
		    for(String source : sourceStrArr){
		    	String[] sourceAr = source.split(",");
		    	FileVO  sourceFileVO = new FileVO();
		    	sourceFileVO.setSourceId(new Long(sourceAr[0]));
		    	sourceFileVO.setFileSourceLanguageId(new Long(sourceAr[1]));
		    	sourceFilesList.add(sourceFileVO);
		    }
		    
		    String[] languageStrArr = languageString.split("-");
		    for(String language : languageStrArr){
		    	String[] languageAr = language.split(",");
		    	FileVO  languageFileVO = new FileVO();
		    	languageFileVO.setLanguegeId(new Long(languageAr[0]));
		    	languageFileVO.setFileSourceLanguageId(new Long(languageAr[1]));
		    	languageFilesList.add(languageFileVO);
		    }
		   } 
		    fileVO.setFileId(jObj.getLong("fileId"));
		    resultStatus = newsMonitoringService.updateDeleteNews(fileVO,jObj.getString("task").trim(),sourceFilesList,languageFilesList);
		    
		    fileVO.setResultStatus(resultStatus);
		    
		    savedDetails =  fileVO;
	    }
	   catch(Exception e){
		   log.error("Exception rised in updateDeleteNews Method of NewsDisplayAction ",e); 
		   resultStatus = new ResultStatus();
		   resultStatus.setResultCode(ResultCodeMapper.FAILURE);
	   }
	   return Action.SUCCESS;
  }
   
   /**
    * This Method Is Used For Handling Ajax Calls For Getting Flaged News And Noted News
    * @return String
    * @date 01-04-2013
    */
   public String getFlagOrNotesNews()
   {
	   return Action.SUCCESS;/*
	   if(log.isDebugEnabled())
			log.debug("Enter into getFlagOrNotesNews Method of NewsDisplayAction ");
	  try
	  {
		  jObj = new JSONObject(getTask()); 
		    
		     session = request.getSession();
			 RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			 FileVO fileVO = new FileVO();
				if(jObj.getString("task").trim().equalsIgnoreCase("today"))
				{
				   fileVO.setExistingFrom(dateUtilService.getCurrentDateAndTime());
				   fileVO.setIdentifiedOn(dateUtilService.getCurrentDateAndTime());
				}
				else if(jObj.getString("task").trim().equalsIgnoreCase("thisweek"))
				{
				   fileVO.setExistingFrom(getStartDayOfWeek());
				   fileVO.setIdentifiedOn(dateUtilService.getCurrentDateAndTime());
				}
				else if(jObj.getString("task").trim().equalsIgnoreCase("thismonth"))
				{
				   fileVO.setExistingFrom(getStartDayOfMonth());
				   fileVO.setIdentifiedOn(dateUtilService.getCurrentDateAndTime());
				}
				else if(jObj.getString("task").trim().equalsIgnoreCase("betweendates"))
				{
				   if(jObj.getString("fromDate").trim().length() > 0)
				     fileVO.setExistingFrom(getDate(jObj.getString("fromDate").trim()));
				   if(jObj.getString("toDate").trim().length() > 0)
				     fileVO.setIdentifiedOn(getDate(jObj.getString("toDate").trim()));
				}
				
				if(regVO!=null)
					fileVO.setCandidateId(regVO.getRegistrationID());
				    fileVO.setFileType(jObj.getString("fileType"));
				    fileVO.setTitle(jObj.getString("type"));
				    fileVOs = newsMonitoringService.getNewsForFlagedAndNoted(fileVO);
	  } 
	  catch (Exception e)
	  {
		log.error("Exception raised in getFlagOrNotesNews in NewsDisplatAction", e);
	  } 
		    
	   return Action.SUCCESS; 
   */}
   
   public String getAllNewsForAUser()
   {
	   
	   try	   
	   {
		   session = request.getSession();
		   RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		   if(regVO == null)
			 return null;
		   
		   FileVO fileVO = new FileVO();
		   fileVO.setUserId(regVO.getRegistrationID());
		 //  fileVO.setUserId(regVO.getRegistrationID());
		   //fileVO.setUserId(1L);
		   
		   jObj = new JSONObject(getTask());
		   
		   if(jObj.getString("queryType").trim().equalsIgnoreCase("getAllNews"))
		   {
			   fileVO.setFromDateStr(jObj.getString("fromDate"));
			   fileVO.setToDateStr(jObj.getString("toDate"));
			   returnVal = newsMonitoringService.getNewsForAuser(fileVO);
			}
		   
		   else if(jObj.getString("queryType").trim().equalsIgnoreCase("getAllSourceDetails"))
			{
				returnVal = newsMonitoringService.getAllSourceDetails();
			}
		    else if(jObj.getString("queryType").trim().equalsIgnoreCase("getAllCategoryDetails"))
			{
				returnVal = newsMonitoringService.getAllCategoryDetails();
			}
		    else if(jObj.getString("queryType").trim().equalsIgnoreCase("getAllNewsImportanceDetails"))
			{
				returnVal = newsMonitoringService.getAllNewsImportanceDetails();
			}
		    else if(jObj.getString("queryType").trim().equalsIgnoreCase("getAllSourceLanguageDetails"))
			{
				returnVal = newsMonitoringService.getAllSourceLanguageDetails();
			}
		   
	   }catch(Exception e){
		   log.error("Exception rised in getAllNewsForAUser() ",e);
	   }
	   
	   return Action.SUCCESS;
	   
   }
   
   public String getTotalNews()
   {
	 try{
	 
		 session = request.getSession();
		 RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		 if(regVO == null)
		  return null;
		 
		 
		 FileVO fileVO = new FileVO();
		 fileVO.setUserId(regVO.getRegistrationID());
		 
		 Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
		 Integer maxIndex = Integer.parseInt(request.getParameter("results"));
		 
		 String fromDate = request.getParameter("fromDate");
		 String toDate = request.getParameter("toDate");
		 
		 fileVO.setFromDateStr(fromDate);
		 fileVO.setToDateStr(toDate);
		 fileVO.setStartIndex(startIndex);
		 fileVO.setMaxResult(maxIndex);
		   
		 savedDetails = newsMonitoringService.getTotalNews(fileVO);
		 
	 }catch (Exception e) {
	  
	  log.error(" Exception Occured in getTotalNews() method, Exception - "+e);
	}
	 return Action.SUCCESS;
   }
   
   public String saveSourceDetails()
   {
	   try {
		   session = request.getSession();
		   RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		   /*if(regVO == null)
			   return Action.ERROR;*/
		   jObj = new JSONObject(getTask());
		   String name = jObj.getString("name");
		   resultStatus = newsMonitoringService.storeSourceDetails(name);
	} catch (Exception e) {
		log.error("Exception rised in saveSourceDetails() ",e);
	}
	   return Action.SUCCESS;  
   }
   
   public String getNewsForAUser()
   {
	   try{
		   jObj = new JSONObject(getTask());
		  session = request.getSession();
		  RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
		  if(regVo == null)
			  return Action.ERROR;
		  Long userId = regVo.getRegistrationID();
		  FileVO fileVO = new FileVO();
		  fileVO.setUserId(userId);
		  fileVO.setFromDateStr(jObj.getString("fromDate"));
		  fileVO.setToDateStr(jObj.getString("toDate"));
		  fileVO.setLatest(true);
		  fileVO.setRegionValue(jObj.getLong("regionLevel"));
		  fileVO.setImportanceId(jObj.getLong("importance"));
		  fileVO.setFileType(jObj.getString("type"));
		  fileVO.setLocationId(jObj.getLong("reportRegionLevel"));
		  fileVO.setLocationVal(jObj.getLong("reportRegionLevelVal"));
		  returnVal = newsMonitoringService.getAllNewsDetails(fileVO);
		  
	   }
	   catch (Exception e) {
		   log.error("Exception rised in getNewsForAUser() ",e);
	}
	return Action.SUCCESS;
   }
   
   public String saveNewsForAUser()
   {
	   try{
		   List<Long> fileGallaryIds = new ArrayList<Long>();
		   jObj = new JSONObject(getTask());
			  session = request.getSession();
			  RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
			  if(regVo == null)
				  return Action.ERROR;
			  Long userId = regVo.getRegistrationID();  
			  JSONArray arr = jObj.getJSONArray("fileGallaryIds");
			  for(int i=0;i<arr.length();i++)
				  fileGallaryIds.add(new Long(arr.get(i).toString())); 
			  resultStatus = newsMonitoringService.saveNewsReport(fileGallaryIds,userId,jObj.getString("description"));
	   }
	   catch (Exception e) {
		   log.error("Exception rised in saveNewsForAUser() ",e);
	}
	return Action.SUCCESS;
   }
   
   public String updateGallaryKeyword()
   {
	   try{
		  jObj = new JSONObject(getTask());
		  session = request.getSession();
		  RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
		  if(regVo == null)
			  return Action.ERROR;
		   Long userId = regVo.getRegistrationID();  
		 
		   if(jObj.getString("task").equalsIgnoreCase("updateGallaryKeyword"))
		   {
			   List<Long> gallaryIds = new ArrayList<Long>();
			   List<Long> keywords = new ArrayList<Long>();
			   JSONArray arr = jObj.getJSONArray("gallariesArr");
			   JSONArray arr1 = jObj.getJSONArray("keywordsArr");
			   for(int i=0;i<arr.length();i++)
				   gallaryIds.add(new Long(arr.get(i).toString())); 
			   for(int i=0;i<arr1.length();i++)
				   keywords.add(new Long(arr1.get(i).toString()));
		   resultStatus = newsMonitoringService.updateGallaryKeyword(gallaryIds,keywords,userId);
		   }
		   else if(jObj.getString("task").equalsIgnoreCase("updatexistingKeyword"))
		   {
			   List<Long> checkedgallaryIds = new ArrayList<Long>();
			   List<Long> uncheckedgallaryIds = new ArrayList<Long>();
			   JSONArray checked = jObj.getJSONArray("checkedgallariesArr");
			   JSONArray unchecked = jObj.getJSONArray("unCheckedgallariesArr");
			   for(int i=0;i<checked.length();i++)
				   checkedgallaryIds.add(new Long(checked.get(i).toString())); 
			   for(int i=0;i<unchecked.length();i++)
				   uncheckedgallaryIds.add(new Long(unchecked.get(i).toString()));
			   //resultStatus = newsMonitoringService.updateExistingGallaryKeyword(checkedgallaryIds,uncheckedgallaryIds,jObj.getLong("keyWord"),userId); 
			   resultStatus = newsMonitoringService.updateExistingGallaryKeyword(checkedgallaryIds,uncheckedgallaryIds,jObj.getLong("keyWord"),userId);
		   }
	   }
	   catch (Exception e) {
		   log.error("Exception rised in updateGallaryKeyword() ",e);
	}
	return Action.SUCCESS;
   }
   public String getAllNewsReports()
   {
	   try{
		//jObj =new JSONObject(getTask());
		 session = request.getSession();
		  RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
		  if(regVo == null)
			  return Action.ERROR;
		   Long userId = regVo.getRegistrationID();  
		   	//if(regVo.getUserAccessType().equalsIgnoreCase("Admin"))
		   		//userId = 0l;
			//if(jObj.getString("task").trim().equalsIgnoreCase("getAllNewsReports"))
		    //{
		    	fileVO = newsMonitoringService.getNewsReports(userId,Integer.parseInt(request.getParameter("startIndex")),Integer.parseInt(request.getParameter("results")));
		    //}
	   }
	   catch (Exception e) {
		   log.error("Exception rised in getAllNewsReports() ",e);
	}
	   return Action.SUCCESS;
   }
   public String generateUrlForReport()
   {
	   try{
		jObj =new JSONObject(getTask());
		 session = request.getSession();
		  RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
		  if(regVo == null)
			  return Action.ERROR;
		   Long userId = regVo.getRegistrationID();  
		   String url = request.getRequestURL().toString().replace("generateReportKeyAction.action","createReportAction.action?");
		   reportUrl = newsMonitoringService.generateUrlForNewsReport(jObj.getLong("reportId"), userId,url);
	   }
	   catch (Exception e) {
		   log.error("Exception rised in generateUrlForReport() ",e);
	}
	   return Action.SUCCESS;
   }
}
