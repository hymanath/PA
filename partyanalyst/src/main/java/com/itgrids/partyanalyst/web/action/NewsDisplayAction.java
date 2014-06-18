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
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.INewsMonitoringService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.opensymphony.xwork2.Action;

public class NewsDisplayAction implements ServletRequestAware{
	
	private static final Logger LOG = Logger.getLogger(NewsDisplayAction.class);
	private INewsMonitoringService newsMonitoringService;
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	private JSONObject jObj;
	private List<FileVO> returnVal;
	private DateUtilService dateUtilService = new DateUtilService();
	private ResultStatus resultStatus;
	private FileVO savedDetails;
	private List<FileVO> fileVOs;
	private int startIndex;
	private int results;
	private FileVO fileVO;
	
	public FileVO getFileVO() {
		return fileVO;
	}

	public void setFileVO(FileVO fileVO) {
		this.fileVO = fileVO;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getResults() {
		return results;
	}

	public void setResults(int results) {
		this.results = results;
	}

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

	public void setFileVOs(List<FileVO> fileVOs) {
		this.fileVOs = fileVOs;
	}

	public String getNews(){
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
		else if(jObj.getString("queryType").trim().equalsIgnoreCase("getAllNewsImportanceDetails"))
		{
			returnVal = newsMonitoringService.getAllNewsImportanceDetails();
		}
	 }
	 catch(Exception e){
		 e.printStackTrace();
	 }
	 return Action.SUCCESS;
	}
	public Date getCurrentDate(){
		try {
		java.util.Date now = new java.util.Date();
        String DATE_FORMAT = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        String strDateNew = sdf.format(now);        
			now = sdf.parse(strDateNew);
			LOG.info("todayDate = "+now);
			return now;
		} catch (ParseException e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
				e.printStackTrace();
				return null;
			}
	  }
  public Date getDate(String dateStr){
	  String[] dateArray =  dateStr.split("-");
	  Calendar cal = Calendar.getInstance(); 
	  cal.set(Integer.parseInt(dateArray[0]),Integer.parseInt(dateArray[1])-1, Integer.parseInt(dateArray[2]));
	  return cal.getTime();
  }
  public String getDetails(){
		if(LOG.isDebugEnabled())
		LOG.debug("Enter into getGraphDetails Method of NewsDisplayAction ");
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
		  LOG.error("Exception rised in getGraphDetails Method of NewsDisplayAction ",e); 
	  }
		 return Action.SUCCESS;
	 }
   public String updateDeleteNews(){
	   if(LOG.isDebugEnabled())
			LOG.debug("Enter into updateDeleteNews Method of NewsDisplayAction ");
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
		    	sourceFileVO.setSourceId(Long.valueOf(sourceAr[0]));
		    	sourceFileVO.setFileSourceLanguageId(Long.valueOf(sourceAr[1]));
		    	sourceFilesList.add(sourceFileVO);
		    }
		    
		    String[] languageStrArr = languageString.split("-");
		    for(String language : languageStrArr){
		    	String[] languageAr = language.split(",");
		    	FileVO  languageFileVO = new FileVO();
		    	languageFileVO.setLanguegeId(Long.valueOf(languageAr[0]));
		    	languageFileVO.setFileSourceLanguageId(Long.valueOf(languageAr[1]));
		    	languageFilesList.add(languageFileVO);
		    }
		   } 
		    fileVO.setFileId(jObj.getLong("fileId"));
		    resultStatus = newsMonitoringService.updateDeleteNews(fileVO,jObj.getString("task").trim(),sourceFilesList,languageFilesList);
		    
		    fileVO.setResultStatus(resultStatus);
		    
		    savedDetails =  fileVO;
	    }
	   catch(Exception e){
		   LOG.error("Exception rised in updateDeleteNews Method of NewsDisplayAction ",e); 
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
	   if(LOG.isDebugEnabled())
			LOG.debug("Enter into getFlagOrNotesNews Method of NewsDisplayAction ");
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
		LOG.error("Exception raised in getFlagOrNotesNews in NewsDisplatAction", e);
	  } 
		    
	   return Action.SUCCESS; 
   }
   
   public String getNews1(){
	   fileVO = new FileVO();
	   try{
		
		   session = request.getSession();
		   RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		   
		   if(request.getParameter("queryType").equalsIgnoreCase("getNews")){
			 
			 
			 if(request.getParameter("task").trim().equalsIgnoreCase("byTodayDate"))
				{
				   fileVO.setExistingFrom(dateUtilService.getCurrentDateAndTime());
				   fileVO.setIdentifiedOn(dateUtilService.getCurrentDateAndTime());
				}
			 else if(request.getParameter("task").trim().equalsIgnoreCase("byThisWeek"))
				{
				   fileVO.setExistingFrom(getStartDayOfWeek());
				   fileVO.setIdentifiedOn(dateUtilService.getCurrentDateAndTime());
				}
				else if(request.getParameter("task").trim().equalsIgnoreCase("byThisMonth"))
				{
				   fileVO.setExistingFrom(getStartDayOfMonth());
				   fileVO.setIdentifiedOn(dateUtilService.getCurrentDateAndTime());
				}
				else if(request.getParameter("task").trim().equalsIgnoreCase("betweendates"))
				{
				   if(request.getParameter("fromDate").trim().length() > 0)
				     fileVO.setExistingFrom(getDate(request.getParameter("fromDate").trim()));
				   if(request.getParameter("toDate").trim().length() > 0)
				     fileVO.setIdentifiedOn(getDate(request.getParameter("toDate").trim()));
				}
				
				if(regVO!=null)
					fileVO.setCandidateId(regVO.getRegistrationID());
				    fileVO.setFileType(request.getParameter("fileType"));
				
			    if(request.getParameter("sourceId") !=null && request.getParameter("sourceId").trim().length()>0)
					fileVO.setSourceId(Long.valueOf(request.getParameter("sourceId")));
			    if(request.getParameter("languegeId") !=null && request.getParameter("languegeId").trim().length()>0)
				    fileVO.setLanguegeId(Long.valueOf(request.getParameter("languegeId")));
			    if(request.getParameter("categoryId") !=null && request.getParameter("categoryId").trim().length()>0)
				    fileVO.setCategoryId(Long.valueOf(request.getParameter("categoryId")));
			    if(request.getParameter("newsImportanceId") !=null && request.getParameter("newsImportanceId").trim().length()>0)
				    fileVO.setNewsImportanceId(Long.valueOf(request.getParameter("newsImportanceId")));
			    if(request.getParameter("locationScope") !=null && request.getParameter("locationScope").trim().length()>0)
				    fileVO.setLocationScope(Long.valueOf(request.getParameter("locationScope")));
			    if(request.getParameter("location") !=null && request.getParameter("location").trim().length()>0)
				    fileVO.setLocation(Long.valueOf(request.getParameter("location")));
			    
				 String direction =   request.getParameter("dir");
				 String columnName = request.getParameter("sort");
				 int startIndex = Integer.parseInt(request.getParameter("startIndex"));
				 int endIndex = Integer.parseInt(request.getParameter("results"));

				
			returnVal = newsMonitoringService.getNewsForRegisterUsers2(fileVO,direction,columnName,startIndex,endIndex);
			fileVO.setFileVOList(returnVal);
		   }
	   }
	   catch(Exception e){
			  LOG.error("Exception rised in getGraphDetails Method of NewsDisplayAction ",e); 
		  }
			 return Action.SUCCESS;
   }
}
