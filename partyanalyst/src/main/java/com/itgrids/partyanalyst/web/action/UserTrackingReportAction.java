package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AccessedPageLoginTimeVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UserTrackingReportVO;
import com.itgrids.partyanalyst.dto.UserTrackingListVO;

import com.itgrids.partyanalyst.notification.service.ISchedulerService;
import com.itgrids.partyanalyst.service.IUserTrackingReportService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class UserTrackingReportAction extends ActionSupport implements ServletRequestAware , ServletContextAware{
	
	private static final long serialVersionUID = 5437549265219239930L;
	private static final Logger log = Logger.getLogger(UserTrackingReportAction.class);
	private HttpServletRequest request;
	private ServletContext context;
	private HttpSession session;
	private DateUtilService dateUtilService = new DateUtilService();
	private IUserTrackingReportService userTrackingReportService;
	private JSONObject jObj;

	private String task;
	private UserTrackingListVO userTrackingListVO;
	private List<UserTrackingReportVO> userTrackingReportVOList;
	private ISchedulerService schedulerService;
	private ResultStatus resultStatus;
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public ISchedulerService getSchedulerService() {
		return schedulerService;
	}

	public void setSchedulerService(ISchedulerService schedulerService) {
		this.schedulerService = schedulerService;
	}

	public List<UserTrackingReportVO> getUserTrackingReportVOList() {
		return userTrackingReportVOList;
	}

	public void setUserTrackingReportVOList(
			List<UserTrackingReportVO> userTrackingReportVOList) {
		this.userTrackingReportVOList = userTrackingReportVOList;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public IUserTrackingReportService getUserTrackingReportService() {
		return userTrackingReportService;
	}

	public void setUserTrackingReportService(
			IUserTrackingReportService userTrackingReportService) {
		this.userTrackingReportService = userTrackingReportService;
	}

	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public UserTrackingListVO getUserTrackingListVO() {
		return userTrackingListVO;
	}

	public void setUserTrackingListVO(UserTrackingListVO userTrackingListVO) {
		this.userTrackingListVO = userTrackingListVO;
	}

	public String execute(){
		
		session = request.getSession();

		return Action.SUCCESS;
	}
	public String getTotalUniqueVisitors(){
		try{ 
			 jObj = new JSONObject(getTask());
			 session = request.getSession();
			 Date fromDate = null;
			 Date toDate = null;
			 
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
			    if(jObj.getString("url").trim().equalsIgnoreCase("getUniqueVisitorsAction.action?")){
			    	userTrackingReportVOList = userTrackingReportService.getTotalUniqueVisitorDetails(fromDate,toDate);			    	
			    }	
			    if(jObj.getString("url").trim().equalsIgnoreCase("deleteUnusedRecordsAction.action?")){
			    	resultStatus = schedulerService.deleteSearchEngineAccessedURLsFromUserTracking(fromDate,toDate);			    	
			    }	
		  }
		  catch(Exception e){
			  log.error("Exception rised in getTotalUniqueVisitors Method of UserTrackingReportAction ",e); 
		  }
		 return Action.SUCCESS;
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
			 } 
			 catch (ParseException e) {
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
 
	public String ajaxCallHandlerForVisitor(){
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		try{
			Date fromDate = null;
			Date toDate = null;
			
			/*Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
			Integer maxResult = Integer.parseInt(request.getParameter("results"));
			Long totalRecs=Long.parseLong(request.getParameter("totalRecords"));
			
			String task=request.getParameter("task");
			
			if(task.equalsIgnoreCase("todayVisitorsDetails")){
				fromDate = dateUtilService.getCurrentDateAndTime();
				toDate = dateUtilService.getCurrentDateAndTime();			
			}
			else if(task.equalsIgnoreCase("thisWeekVisitorsDetails")){
				fromDate = getStartDayOfWeek();
				toDate = dateUtilService.getCurrentDateAndTime();
			}
			else if(task.equalsIgnoreCase("thisMonthVisitorsDetails")){
				fromDate = getStartDayOfMonth();
				toDate = dateUtilService.getCurrentDateAndTime();			
			}
			else if(task.equalsIgnoreCase("betweendatesVisitorsDetails")){
				if(request.getParameter("fromDate").trim().length() > 0)
				   fromDate = getDate(request.getParameter("fromDate"));
				if(request.getParameter("toDate").trim().length() > 0)
				   toDate = getDate(request.getParameter("toDate")); 
			}*/
			
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").trim().equalsIgnoreCase("todayVisitorsDetails"))
			{
				fromDate = dateUtilService.getCurrentDateAndTime();
				toDate = dateUtilService.getCurrentDateAndTime();
				
			}
			else if(jObj.getString("task").equalsIgnoreCase("thisWeekVisitorsDetails"))
			{
				fromDate = getStartDayOfWeek();
				toDate = dateUtilService.getCurrentDateAndTime();
			}
			else if(jObj.getString("task").equalsIgnoreCase("thisMonthVisitorsDetails"))
			{
				fromDate = getStartDayOfMonth();
				toDate = dateUtilService.getCurrentDateAndTime();
			}
			else if(jObj.getString("task").equalsIgnoreCase("betweendatesVisitorsDetails"))
			{
				if(jObj.getString("fromDate").trim().length() > 0)
				    fromDate = getDate(jObj.getString("fromDate"));
				if(jObj.getString("toDate").trim().length() > 0)
					toDate = getDate(jObj.getString("toDate"));
			}
			
			userTrackingReportVOList = userTrackingReportService.getHostNameAndNoOfPagesForAVisitor(fromDate ,toDate);
			/*
			userTrackingListVO=new UserTrackingListVO();
			userTrackingListVO.setTotalRowCount(totalRecs);
			userTrackingListVO.setUserTrackingReportVOList(userTrackingReportVOList);
			 */
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
		
public String ajaxCallHandler(){
	
   session = request.getSession();
   RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
	try{
		Date fromDate = null;
		Date toDate = null;
		String userType = null;
		
		/*Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
		Integer maxResult = Integer.parseInt(request.getParameter("results"));
		Long totalRecs=Long.parseLong(request.getParameter("totalRecords"));
		
		String task=request.getParameter("task");
		
		if(task.equalsIgnoreCase("todayUserDetails")){
			fromDate = dateUtilService.getCurrentDateAndTime();
			toDate = dateUtilService.getCurrentDateAndTime();							
		}
		else if(task.equalsIgnoreCase("thisWeekUserDetails")){
			fromDate = getStartDayOfWeek();
			toDate = dateUtilService.getCurrentDateAndTime();
		}
		else if(task.equalsIgnoreCase("thisMonthUserDetails")){
			fromDate = getStartDayOfMonth();
			toDate = dateUtilService.getCurrentDateAndTime();			
		}
		else if(task.equalsIgnoreCase("betweendatesUserDetails")){
			if(request.getParameter("fromDate").trim().length() > 0)
			   fromDate = getDate(request.getParameter("fromDate"));
			if(request.getParameter("toDate").trim().length() > 0)
			   toDate = getDate(request.getParameter("toDate")); 
		}
		if(!request.getParameter("userType").equalsIgnoreCase("null")){
			userType=request.getParameter("userType");
		}*/
		
	
		jObj = new JSONObject(getTask());
		if(jObj.getString("task").equalsIgnoreCase("todayUserDetails"))
		{
			fromDate = dateUtilService.getCurrentDateAndTime();
			toDate = dateUtilService.getCurrentDateAndTime();
			if(!jObj.getString("userType").equalsIgnoreCase("null"))
				userType = jObj.getString("userType");
			
		}
		else if(jObj.getString("task").equalsIgnoreCase("thisWeekUserDetails"))
		{
			fromDate = getStartDayOfWeek();
			toDate = dateUtilService.getCurrentDateAndTime();
			if(!jObj.getString("userType").equalsIgnoreCase("null"))
			userType = jObj.getString("userType");
		}
		else if(jObj.getString("task").equalsIgnoreCase("thisMonthUserDetails"))
		{
			fromDate = getStartDayOfMonth();
			toDate = dateUtilService.getCurrentDateAndTime();
			if(!jObj.getString("userType").equalsIgnoreCase("null"))
			userType = jObj.getString("userType");
		}
		else if(jObj.getString("task").equalsIgnoreCase("betweendatesUserDetails"))
		{
			if(jObj.getString("fromDate").trim().length() > 0)
			   fromDate = getDate(jObj.getString("fromDate"));
			if(jObj.getString("toDate").trim().length() > 0)
			   toDate = getDate(jObj.getString("toDate")); 
			if(!jObj.getString("userType").equalsIgnoreCase("null"))
				userType = jObj.getString("userType");
		}
		if(jObj.getString("innerTask").equalsIgnoreCase("null"))
			userTrackingReportVOList = userTrackingReportService.getHostNameAndNoOfPagesForAUser(fromDate ,toDate ,userType);
		
		userTrackingListVO=new UserTrackingListVO();
		//userTrackingListVO.setTotalRowCount(totalRecs);
		userTrackingListVO.setUserTrackingReportVOList(userTrackingReportVOList);
		
		if(jObj.getString("innerTask").trim().equalsIgnoreCase("getUserPageFlowAjax")){
			String sessionId="";
			if(jObj.getString("sessionId").trim().length()>0)
				sessionId=jObj.getString("sessionId");			
	    	List<AccessedPageLoginTimeVO> urlTimeVOList= userTrackingReportService.getUrlTimeVOList(fromDate, toDate, userType, sessionId);
	    	userTrackingListVO.setUrlTimeVOList(urlTimeVOList);
	    }
	}catch (Exception e) {
		e.printStackTrace();
	}
	return Action.SUCCESS;
}		
}
