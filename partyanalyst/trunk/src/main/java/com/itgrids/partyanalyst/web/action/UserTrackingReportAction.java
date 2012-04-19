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

import com.itgrids.partyanalyst.dto.UserTrackingReportVO;
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
	private List<UserTrackingReportVO> usrTrckRprtVO;
	private String task;
	
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
	public List<UserTrackingReportVO> getUsrTrckRprtVO() {
		return usrTrckRprtVO;
	}

	public void setUsrTrckRprtVO(List<UserTrackingReportVO> usrTrckRprtVO) {
		this.usrTrckRprtVO = usrTrckRprtVO;
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
			 UserTrackingReportVO UserTrackingReportVO = new UserTrackingReportVO();
			 
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
			    if(jObj.getString("url").trim().equalsIgnoreCase("getUniqueVisitorsAction.action?"))
			    	usrTrckRprtVO = userTrackingReportService.getTotalUniqueVisitorDetails(fromDate,toDate);			
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
}
