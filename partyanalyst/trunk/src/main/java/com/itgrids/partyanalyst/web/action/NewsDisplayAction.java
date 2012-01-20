package com.itgrids.partyanalyst.web.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	@Override
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
	
	
	public String execute(){
		
		return Action.SUCCESS;
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
			
			
			returnVal = newsMonitoringService.getNewsForRegisterUsers(fileVO);
		}
		else if(jObj.getString("queryType").trim().equalsIgnoreCase("getCount"))
		{
			if(jObj.getString("task").trim().equalsIgnoreCase("byTodayDate"))
			{
			   returnVal = newsMonitoringService.getAllCountDetails(dateUtilService.getCurrentDateAndTime(),dateUtilService.getCurrentDateAndTime(),jObj.getString("fileType"),regVO.getRegistrationID());
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("byThisWeek"))
			{
			   returnVal = newsMonitoringService.getAllCountDetails(getStartDayOfWeek(),dateUtilService.getCurrentDateAndTime(),jObj.getString("fileType"),regVO.getRegistrationID());
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("byThisMonth"))
			{
			   returnVal = newsMonitoringService.getAllCountDetails(getStartDayOfMonth(),dateUtilService.getCurrentDateAndTime(),jObj.getString("fileType"),regVO.getRegistrationID());
			}
			else if(jObj.getString("task").trim().equalsIgnoreCase("betweendates"))
			{
				Date from = null;
				Date to = null;
			   if(jObj.getString("fromDate").trim().length() > 0)
			     from = getDate(jObj.getString("fromDate").trim());
			   if(jObj.getString("toDate").trim().length() > 0)
			     to = getDate(jObj.getString("toDate").trim());
			   returnVal = newsMonitoringService.getAllCountDetails(from,to,jObj.getString("fileType"),regVO.getRegistrationID());
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
			System.out.println("todayDate = "+now);
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
	  Date date = null; 
	  DateFormat formatter = null;
	 try {	  
	  formatter = new SimpleDateFormat("yyyy-mm-dd");
	  date = (Date)formatter.parse(dateStr);  
	 } catch (ParseException e)
	  {System.out.println("Exception :"+e);  }  
	 return date;
  }
}
