package com.itgrids.partyanalyst.web.action;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.HamletsAndBoothsVO;
import com.itgrids.partyanalyst.dto.LocationwiseProblemStatusInfoVO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemHistoryVO;
import com.itgrids.partyanalyst.dto.ProblemsCountByStatus;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;


public class ProblemManagementReportAction extends ActionSupport implements
		ServletRequestAware, ServletContextAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -500281545950721654L;
	private static final Logger log = Logger.getLogger(ProblemManagementReportAction.class);
	private IProblemManagementReportService problemManagementReportService;
	private HttpServletRequest request;
	private HttpSession session;
	private List<ProblemBeanVO> problemBean;
	private String task = null;
	JSONObject jObj = null;
	private IStaticDataService staticDataService;
	private List<SelectOptionVO> result;
	private List<MandalVO> mandals;
	private HamletsAndBoothsVO hamletsAndBoothsVO; 
	private IRegionServiceData regionServiceDataImp;
	private Long locationId = null;
	private Long problemlocationId = null;
	private String status=null;
	private String taskType=null;
	private List<ProblemHistoryVO> problemHistory;
	private LocationwiseProblemStatusInfoVO locationwiseProblemStatusInfoVO;
	private ServletContext context;
	private String accessType;
	private Long accessValue;
	private List<SelectOptionVO> statesList, districtsList, constituenciesList; 
		
	public Long getProblemlocationId() {
		return problemlocationId;
	}
	public void setProblemlocationId(Long problemlocationId) {
		this.problemlocationId = problemlocationId;
	}
	public List<ProblemHistoryVO> getProblemHistory() {
		return problemHistory;
	}
	public void setProblemHistory(List<ProblemHistoryVO> problemHistory) {
		this.problemHistory = problemHistory;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	
	public List<ProblemBeanVO> getProblemBean() {
		return problemBean;
	}
	public void setProblemBean(List<ProblemBeanVO> problemBean) {
		this.problemBean = problemBean;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}
	public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}

	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public void setResult(List<SelectOptionVO> result) {
		this.result = result;
	}
	public List<SelectOptionVO> getResult() {
		return result;
	}
	public List<MandalVO> getMandals() {
		return mandals;
	}

	public void setMandals(List<MandalVO> mandals) {
		this.mandals = mandals;
	}
	public HamletsAndBoothsVO getHamletsAndBoothsVO() {
		return hamletsAndBoothsVO;
	}

	public void setHamletsAndBoothsVO(HamletsAndBoothsVO hamletsAndBoothsVO) {
		this.hamletsAndBoothsVO = hamletsAndBoothsVO;
	}	
	
	public LocationwiseProblemStatusInfoVO getLocationwiseProblemStatusInfoVO() {
		return locationwiseProblemStatusInfoVO;
	}
	public void setLocationwiseProblemStatusInfoVO(
			LocationwiseProblemStatusInfoVO locationwiseProblemStatusInfoVO) {
		this.locationwiseProblemStatusInfoVO = locationwiseProblemStatusInfoVO;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;		
	}	
	
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public Long getAccessValue() {
		return accessValue;
	}
	public void setAccessValue(Long accessValue) {
		this.accessValue = accessValue;
	}	
	
	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}
	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}
	public List<SelectOptionVO> getDistrictsList() {
		return districtsList;
	}
	public void setDistrictsList(List<SelectOptionVO> districtsList) {
		this.districtsList = districtsList;
	}
	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}
	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}
	public String execute() throws Exception
	{	
		log.debug("In Action");
		session=request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
		if(user==null)
			return ERROR;
		
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				log.debug("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(jObj.getString("task").equals("getStates")){
				result = new ArrayList<SelectOptionVO>(2);
				result.add(0,new SelectOptionVO(0l,"Select State"));
				result.add(1,new SelectOptionVO(1l,"Andhra Pradesh"));
			}
			if(jObj.getString("task").equals("getDistricts")){
				result = staticDataService.getDistricts(new Long(jObj.getString("locationId")));
				result.add(0,new SelectOptionVO(0l,"Select District"));
			}
			else if(jObj.getString("task").equals("getConstituencies")){
					result = regionServiceDataImp.getConstituenciesByDistrictID(new Long(jObj.getString("locationId")));
				result.add(0,new SelectOptionVO(0l,"Select Constituencies"));
			}
			else if(jObj.getString("task").equals("getMandals")){
				result = regionServiceDataImp.getMandalsByConstituencyID(new Long(jObj.getLong("locationId")));
				result.add(0,new SelectOptionVO(0l,"Select Mandal"));
			}	
			else if(jObj.getString("task").equals("getTowhships")){
				result = staticDataService.findTownshipsByTehsilID(new Long(jObj.getLong("locationId")));
				result.add(0,new SelectOptionVO(0l,"Select Mandal"));
			}
			else if(jObj.getString("task").equals("getVillages")){
				result = staticDataService.getHamletsForTownship(new Long(jObj.getLong("locationId")));
				result.add(0,new SelectOptionVO(0l,"Select Hamlet"));
			}
			else if(jObj.getString("task").equals("findVoters")){
				taskType =  jObj.getString("taskType");
				locationId = new Long(jObj.getLong("locationId"));
				status = findTaskType(taskType);
				problemBean = problemManagementReportService.getHamletProblemsInfo(new Long(locationId), user.getRegistrationID(),status);
			}
			else if(jObj.getString("task").equals("findTownshipVoters")){
				taskType =  jObj.getString("taskType");
				locationId = new Long(jObj.getLong("locationId"));
				status = findTaskType(taskType);
				problemBean = problemManagementReportService.getTehsilProblemsInfo(new Long(locationId), user.getRegistrationID(),status);
			}
			else if(jObj.getString("task").equals("findConstituencyVoters")){
				taskType =  jObj.getString("taskType");
				locationId = new Long(jObj.getLong("locationId"));
				status = findTaskType(taskType);
				problemBean = problemManagementReportService.getConstituencyProblemsInfo(new Long(locationId), user.getRegistrationID(),status);
			}	
		}
		return SUCCESS;    
	}	
	
	public String findTaskType(String taskType){
		if(taskType.equalsIgnoreCase("new")){
			return "new";
		}
		else if(taskType.equalsIgnoreCase("classify")){
			return "classify";
		}
		else if(taskType.equalsIgnoreCase("assigned")){
			return "assigned";
		}
		else if(taskType.equalsIgnoreCase("progress")){
			return "progress";
		}
		else if(taskType.equalsIgnoreCase("pending")){
			return "pending";
		}
		else if(taskType.equalsIgnoreCase("fixed")){
			return "fixed";
		}
		else if(taskType.equalsIgnoreCase("deo")){
			return "deo";
		}
		else if(taskType.equalsIgnoreCase("collectorate")){
			return "collectorate";
		}
		else if(taskType.equalsIgnoreCase("mro")){
			return "mro";
		}
		else if(taskType.equalsIgnoreCase("VILLAGE SECRETARY")){
			return "VILLAGE SECRETARY";
		}
		else{
		return "";
		}
	}
	
	public String getProblemHistoryData(){
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				System.out.println("Result From JSON:"+jObj);
				problemlocationId = new Long(jObj.getLong("locationId"));
				problemHistory = problemManagementReportService.getCompleteDetailsForAProblem(problemlocationId);
			}catch(Exception e){
				e.printStackTrace();
			}
		}			
		return SUCCESS;
	}
	
	public String getProblemsCountByStatusBasedOnAccessLevel(){
		if(log.isDebugEnabled())
			log.debug("Entered in to getProblemsCountByStatusBasedOnAccessLevel in problem mgmt report");
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		session=request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		locationwiseProblemStatusInfoVO = new LocationwiseProblemStatusInfoVO();
		if(user != null)
		{
			accessType = user.getAccessType();
			accessValue = new Long(user.getAccessValue());
			locationwiseProblemStatusInfoVO = problemManagementReportService.getProblemsStatusCount(accessType, accessValue);
			locationwiseProblemStatusInfoVO.setProblemsPostedInLastTenDays("10");
			locationwiseProblemStatusInfoVO.setProblemsSolvedInLastTenDays("3");
			locationwiseProblemStatusInfoVO.setProblemsPostedInLastThirtyDays("20");
			locationwiseProblemStatusInfoVO.setProblemsSolvedInLastThirtyDays("5");
					
		}	
		if(locationwiseProblemStatusInfoVO.getProblemsCountByStatus() != null && locationwiseProblemStatusInfoVO.getProblemsCountByStatus().size()>0)
		{
			String problemsStatusPieChartName = createProblemsPieChart(locationwiseProblemStatusInfoVO.getLocationId(),locationwiseProblemStatusInfoVO.getTotalProblemsCount(),locationwiseProblemStatusInfoVO.getProblemsCountByStatus());
			String lastTenDaysProbsBarChartName = createLastTenDaysBarChart(locationwiseProblemStatusInfoVO);
				
			locationwiseProblemStatusInfoVO.setLastTenDaysProblemsDetailsBarChartName(lastTenDaysProbsBarChartName);
			locationwiseProblemStatusInfoVO.setProblemsStatusChartName(problemsStatusPieChartName);
		}
		
		return SUCCESS;
	}
	
	public String createProblemsPieChart(Long locationId, int totalProblemsCount, List<ProblemsCountByStatus>problemsStatusList)	
	{
		log.debug("Entered in to createProblemsPieChart method in ProblemManagementReportAction");
		String chartName = ""+locationId+"_"+totalProblemsCount+"piechart"+".png";
		String chartPath = context.getRealPath("/") + "charts\\" + chartName;
		final DefaultPieDataset dataset = new DefaultPieDataset();
		Color[] colors = new Color[10];

		for(int i=0; i<problemsStatusList.size();i++)
		{
			if(!IConstants.FIXED.equals(problemsStatusList.get(i).getStatus()))
				{
				
				dataset.setValue(problemsStatusList.get(i).getStatus(), new BigDecimal(problemsStatusList.get(i).getCount()*100.0/totalProblemsCount).setScale(2, BigDecimal.ROUND_HALF_UP) );
				/*
				if(problemsStatusList.get(i).getStatus().equals(IConstants.NEW))
					colors[i]=IConstants.NEW_COLOR;
				if(problemsStatusList.get(i).getStatus().equals(IConstants.CLASSIFY))
					colors[i]=IConstants.CLASSIFY_COLOR;
				if(problemsStatusList.get(i).getStatus().equals(IConstants.ASSIGNED))
					colors[i]=IConstants.ASSIGNED_COLOR;
				if(problemsStatusList.get(i).getStatus().equals(IConstants.PROGRESS))
					colors[i]=IConstants.PROGRESS_COLOR;
				if(problemsStatusList.get(i).getStatus().equals(IConstants.PENDING))
					colors[i]=IConstants.PENDING_COLOR;*/
				}			
		}
		log.debug("size::::::::::::::::::::::::::::::::::::"+problemsStatusList.size());
		ChartProducer.createProblemsPieChart("", dataset, chartPath);
		return chartName ;
	}
	
	public String createLastTenDaysBarChart(LocationwiseProblemStatusInfoVO lastTenDaysProblemStatusInfoVO)
	{
		String chartName = "RecentProblemsDetailsBarChart"+session.getId()+".png";
		String chartPath = context.getRealPath("/") + "charts\\" + chartName;
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(new BigDecimal(lastTenDaysProblemStatusInfoVO.getProblemsPostedInLastTenDays()),"New","Last 10 Days" );
		dataset.addValue(new BigDecimal(lastTenDaysProblemStatusInfoVO.getProblemsSolvedInLastTenDays()),"Fixed","Last 10 Days" );
		
		dataset.addValue(new BigDecimal(lastTenDaysProblemStatusInfoVO.getProblemsPostedInLastThirtyDays()),"New","Last 30 Days" );
		dataset.addValue(new BigDecimal(lastTenDaysProblemStatusInfoVO.getProblemsSolvedInLastThirtyDays()),"Fixed","Last 30 Days" );
				/*String title,String reportType,String category,String value,String party,CategoryDataset 
				 * dataset,String fileName,int width,int height*/
		ChartProducer.createProblems3DBarChart("", null,"", "No. Of Problems","", dataset, chartPath, 400, 200);
		return chartName;
	}
	
	public String problemsByDateBasedOnStatusAction()
	{
		if(log.isDebugEnabled())
			log.debug("Entered in to problemsByDateBasedOnStatusAction in problem mgmt report service");
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		String fromDate = jObj.getString("fromDate");
		String toDate =  jObj.getString("toDate");
		Long statusId = jObj.getLong("status");
		String accessType = jObj.getString("");
		Long accessValue = new Long(jObj.getString("locationId"));
		
		problemBean = problemManagementReportService.getProblemsPostedByStatusAndDates(fromDate, toDate, statusId, accessType, accessValue);
		return SUCCESS;
		
	}
	
	
}	
		

