package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.context.ServletContextAware;

import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IDateService;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.impl.DateService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class ProblemManagementAdminAction extends ActionSupport implements ServletRequestAware, ServletContextAware{

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ProblemManagementAdminAction.class);
	
	private HttpServletRequest request;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	private ServletContext context;
	
	private String fromDate;
	private String toDate;
	private String problemsInAWeekGraphDataAvailability,problemsForCurrentDayGraphDataAvailability; 
	private NavigationVO allProblemsCount,currentDayProblemsCount,result ;
	private String chartNameForAllProblemsCount,chartNameForCurrentDayProblemsCount;
	private IProblemManagementReportService problemManagementReportService;
	private IDateService dateService;
	
	
	public String getProblemsInAWeekGraphDataAvailability() {
		return problemsInAWeekGraphDataAvailability;
	}
	public void setProblemsInAWeekGraphDataAvailability(
			String problemsInAWeekGraphDataAvailability) {
		this.problemsInAWeekGraphDataAvailability = problemsInAWeekGraphDataAvailability;
	}
	public String getProblemsForCurrentDayGraphDataAvailability() {
		return problemsForCurrentDayGraphDataAvailability;
	}
	public void setProblemsForCurrentDayGraphDataAvailability(
			String problemsForCurrentDayGraphDataAvailability) {
		this.problemsForCurrentDayGraphDataAvailability = problemsForCurrentDayGraphDataAvailability;
	}
	public String getChartNameForAllProblemsCount() {
		return chartNameForAllProblemsCount;
	}
	public void setChartNameForAllProblemsCount(String chartNameForAllProblemsCount) {
		this.chartNameForAllProblemsCount = chartNameForAllProblemsCount;
	}
	public String getChartNameForCurrentDayProblemsCount() {
		return chartNameForCurrentDayProblemsCount;
	}
	public void setChartNameForCurrentDayProblemsCount(
			String chartNameForCurrentDayProblemsCount) {
		this.chartNameForCurrentDayProblemsCount = chartNameForCurrentDayProblemsCount;
	}
	public NavigationVO getResult() {
		return result;
	}
	public void setResult(NavigationVO result) {
		this.result = result;
	}	
	public IDateService getDateService() {
		return dateService;
	}
	public void setDateService(IDateService dateService) {
		this.dateService = dateService;
	}
	
	public NavigationVO getAllProblemsCount() {
		return allProblemsCount;
	}
	public void setAllProblemsCount(NavigationVO allProblemsCount) {
		this.allProblemsCount = allProblemsCount;
	}
	public NavigationVO getCurrentDayProblemsCount() {
		return currentDayProblemsCount;
	}
	public void setCurrentDayProblemsCount(NavigationVO currentDayProblemsCount) {
		this.currentDayProblemsCount = currentDayProblemsCount;
	}
	public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}
	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}
	
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	
	
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getToDate() {
		return toDate;
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
	public JSONObject getJObj() {
		return jObj;
	}
	public void setJObj(JSONObject obj) {
		jObj = obj;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}
	
	public String execute () throws Exception 
	{
		session=request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
			return ERROR;
		
		Date currentDate = dateService.getPresentPreviousAndCurrentDayDate(IConstants.DATE_PATTERN,0,IConstants.PRESENT_DAY);
		Date dateAfterAWeek = dateService.getPresentPreviousAndCurrentDayDate(IConstants.DATE_PATTERN,7,IConstants.PREVIOUS_DAY);
		
		if(user != null)
		{			
			 allProblemsCount = problemManagementReportService.getProblemsCountInAWeek(dateAfterAWeek,currentDate,IConstants.NEW,IConstants.FALSE);
			 if(allProblemsCount!=null  && allProblemsCount.getProblemsCount()!=null ){
				 if(allProblemsCount.getProblemsCount().size()!=0){
					 chartNameForAllProblemsCount = "lastOneWeekProblemsGraph.png";
			         String chartPath = context.getRealPath("/")+ "charts\\" + chartNameForAllProblemsCount;				
					 ChartProducer.create3DBarChart("Problems for the past 7 days", "Range",createDataset(allProblemsCount.getProblemsCount()), chartPath);
					 problemsInAWeekGraphDataAvailability = "dataAvailable";
				 }				
			 }else{
				 problemsInAWeekGraphDataAvailability = "dataUnAvailable";
			 }
			 
			 currentDayProblemsCount = problemManagementReportService.getCountOfAllNonApprovedProblemsByLocationWiseForCurrentDate(currentDate,IConstants.NEW,IConstants.FALSE);
			 if(currentDayProblemsCount!=null && currentDayProblemsCount.getProblemsCount()!=null){
				 if(currentDayProblemsCount.getProblemsCount().size()!=0){
					 chartNameForAllProblemsCount = "allUnApprovedProblemsTillDayGraph.png";
			         String chartPath = context.getRealPath("/")+ "charts\\" + chartNameForAllProblemsCount;				
					 ChartProducer.create3DBarChart("All Non-Approved Problems By Location Wise ", "Range",createDataset(currentDayProblemsCount.getProblemsCount()), chartPath);
					 problemsForCurrentDayGraphDataAvailability = "dataAvailable";
				 }
			 }else{
				 problemsForCurrentDayGraphDataAvailability = "dataUnAvailable";
			 }
 		}
		return SUCCESS;
	}
	private CategoryDataset createDataset(List<SelectOptionVO> result) {       
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(SelectOptionVO selectOptionVO: result){        
        		dataset.addValue(new BigDecimal(selectOptionVO.getId()), selectOptionVO.getName(), selectOptionVO.getName());       	
        }
        return dataset;
    }
	
	public String getAllProblemsNeededToBeApprovalForAdmin(){
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				
				if(jObj.getString("task").equals("betweenDates")){	
					result = problemManagementReportService.getAllApprovalProblemsBetweenTheDates(jObj.getString("fromDate"),jObj.getString("toDate"),IConstants.NEW,jObj.getString("choice"));
				}
				
				else if(jObj.getString("task").equals("currentDate")){	
					result = problemManagementReportService.getAllApprovalProblemsForTheCurrentDay(IConstants.NEW,IConstants.FALSE);
				}
				
				else if(jObj.getString("task").equals("selectedDate")){
					Date selectedDate = DateService.convertStringToDate(jObj.getString("selectedDate"),IConstants.DATE_PATTERN);
					result = problemManagementReportService.getAllApprovalProblemsForSelectedDate(selectedDate,IConstants.NEW,jObj.getString("choice"));
				}
				
				else if(jObj.getString("task").equals("performDeletionOrAcceptenceProblems")){	
					JSONArray selectedProblemIds = jObj.getJSONArray("selectedProblemHistoryIds");
					Integer problemIds[] = new Integer[selectedProblemIds.length()];					
					for(int i=0; i<selectedProblemIds.length(); i++){
						problemIds[i] = (Integer)selectedProblemIds.get(i);
					}
					if(jObj.get("choice").equals("accept")){						
						problemManagementReportService.acceptSelectedProblemsByAdmin(problemIds);
					}			
					else{						
						problemManagementReportService.deleteSelectedProblemsByAdmin(problemIds);
					}
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
}
