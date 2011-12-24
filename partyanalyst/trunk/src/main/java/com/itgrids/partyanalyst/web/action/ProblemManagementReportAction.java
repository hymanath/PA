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
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.HamletsAndBoothsVO;
import com.itgrids.partyanalyst.dto.LocationwiseProblemStatusInfoVO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemClassificationVO;
import com.itgrids.partyanalyst.dto.ProblemHistoryVO;
import com.itgrids.partyanalyst.dto.ProblemsCountByStatus;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
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
	private ProblemBeanVO problemBeanVO;
	private LocationwiseProblemStatusInfoVO locationwiseProblemStatusInfoVO;
	private ServletContext context;
	private String accessType;
	private Long accessValue;
	private List<SelectOptionVO> statesList, districtsList, constituenciesList; 
	private IInfluencingPeopleService influencingPeopleService;
	private List<SelectOptionVO> statusList;
	private List<SelectOptionVO> problemScopes;
	private List<SelectOptionVO> deptScopes;
	private IProblemManagementService problemManagementService;
	private Long scope;
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> pConstituencyList;
	private List<SelectOptionVO> mandalList;
	private Long defaultStateId = 0l;
	private Long defaultDistrictId = 0l;
	private Long defaultConstituencyId = 0l;
	private List<ProblemClassificationVO> problemsGropedByDeptOrCadre;
	private EntitlementsHelper entitlementsHelper;
	private List<SelectOptionVO> probCountList;
	private String chartProducerURL="/var/www/vsites/partyanalyst.com/httpdocs/charts/";
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
	
	public List<SelectOptionVO> getProbCountList() {
		return probCountList;
	}
	public void setProbCountList(List<SelectOptionVO> probCountList) {
		this.probCountList = probCountList;
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
	public ProblemBeanVO getProblemBeanVO() {
		return problemBeanVO;
	}
	public void setProblemBeanVO(ProblemBeanVO problemBeanVO) {
		this.problemBeanVO = problemBeanVO;
	}
	public IInfluencingPeopleService getInfluencingPeopleService() {
		return influencingPeopleService;
	}
	public void setInfluencingPeopleService(
			IInfluencingPeopleService influencingPeopleService) {
		this.influencingPeopleService = influencingPeopleService;
	}	
	
	public List<SelectOptionVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<SelectOptionVO> statusList) {
		this.statusList = statusList;
	}
	public List<SelectOptionVO> getProblemScopes() {
		return problemScopes;
	}
	public void setProblemScopes(List<SelectOptionVO> problemScopes) {
		this.problemScopes = problemScopes;
	}	
	
	public List<SelectOptionVO> getDeptScopes() {
		return deptScopes;
	}
	public void setDeptScopes(List<SelectOptionVO> deptScopes) {
		this.deptScopes = deptScopes;
	}
	
	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}
	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}	
	
	public Long getScope() {
		return scope;
	}
	public void setScope(Long scope) {
		this.scope = scope;
	}	
	
	public List<SelectOptionVO> getStateList() {
		return stateList;
	}
	public void setStateList(List<SelectOptionVO> stateList) {
		this.stateList = stateList;
	}
	public List<SelectOptionVO> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(List<SelectOptionVO> districtList) {
		this.districtList = districtList;
	}
	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}
	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	public List<SelectOptionVO> getPConstituencyList() {
		return pConstituencyList;
	}
	public void setPConstituencyList(List<SelectOptionVO> constituencyList) {
		pConstituencyList = constituencyList;
	}
	public List<SelectOptionVO> getMandalList() {
		return mandalList;
	}
	public void setMandalList(List<SelectOptionVO> mandalList) {
		this.mandalList = mandalList;
	}	
	
	public Long getDefaultStateId() {
		return defaultStateId;
	}
	
	public void setDefaultStateId(Long defaultStateId) {
		this.defaultStateId = defaultStateId;
	}
	
	public Long getDefaultDistrictId() {
		return defaultDistrictId;
	}
	
	public void setDefaultDistrictId(Long defaultDistrictId) {
		this.defaultDistrictId = defaultDistrictId;
	}
	
	public Long getDefaultConstituencyId() {
		return defaultConstituencyId;
	}
	
	public void setDefaultConstituencyId(Long defaultConstituencyId) {
		this.defaultConstituencyId = defaultConstituencyId;
	}
	
	public Long getDefaultRegionScope() {
		return this.scope;
	}	
	
	public Long getDefaultState() {
		return this.defaultStateId;
	}
	
	public Long getDefaultDistrict() {
		return this.defaultDistrictId;
	}	

	public Long getDefaultConstituency() {
		return this.defaultConstituencyId;
	}
	
	public List<ProblemClassificationVO> getProblemsGropedByDeptOrCadre() {
		return problemsGropedByDeptOrCadre;
	}
	public void setProblemsGropedByDeptOrCadre(
			List<ProblemClassificationVO> problemsGropedByDeptOrCadre) {
		this.problemsGropedByDeptOrCadre = problemsGropedByDeptOrCadre;
	}
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	public String execute() throws Exception
	{	
		log.debug("In Action");
		session=request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		Long stateId = 0l;
		stateList = new ArrayList<SelectOptionVO>();
		districtList = new ArrayList<SelectOptionVO>();
		constituencyList = new ArrayList<SelectOptionVO>();
		mandalList = new ArrayList<SelectOptionVO>();
		
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.PROBLEM_MANAGEMENT_ENTITLEMENT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.PROBLEM_MANAGEMENT_ENTITLEMENT))
			return ERROR;
		
		accessType = user.getAccessType();
		accessValue = new Long(user.getAccessValue());
		if("MLA".equals(accessType))
		{
			log.debug("Access Type = MLA ****");
			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByConstituencyID(accessValue);	
			//stateList = regionServiceDataImp.getStatesByCountry(1l);			
			//districtList = regionServiceDataImp.getDistrictsByStateID(list.get(0).getId());  			
			constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(list.get(1).getId());
			
			stateId = list.get(0).getId();
			setDefaultStateId(stateId);
			setDefaultDistrictId(list.get(1).getId());
			setDefaultConstituencyId(list.get(2).getId());
			setScope(4l);
		}
		else if("STATE".equals(accessType)){
			log.debug("Access Type = State ****");			
			stateId = accessValue;	
			setScope(2l);
			setDefaultStateId(stateId);
			//stateList = regionServiceDataImp.getStatesByCountry(1l);			
			
		}else if("DISTRICT".equals(accessType)){
			log.debug("Access Type = District ****");			
			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByDistrictID(accessValue);
			stateId = list.get(0).getId();
			setScope(3l);
		//	stateList = regionServiceDataImp.getStatesByCountry(1l);			
		//	districtList = regionServiceDataImp.getDistrictsByStateID(list.get(0).getId());
			setDefaultStateId(stateId);
			setDefaultDistrictId(list.get(1).getId());
			
			
		} else if("MP".equals(accessType)){
			List<SelectOptionVO> list = regionServiceDataImp.getStateByParliamentConstituencyID(accessValue);
			stateId = list.get(0).getId();	
			setScope(4l);
			//stateList = regionServiceDataImp.getStatesByCountry(1l);
			ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
			pConstituencyList = staticDataService.getConstituenciesByElectionTypeAndStateId(1l,stateId).getConstituencies();
						
		}
		stateList = regionServiceDataImp.getUserStateList(accessType,accessValue);
		districtList = regionServiceDataImp.getDistrictsByStateID(stateList.get(0).getId());  	
		problemScopes = regionServiceDataImp.getAllRegionScopesForModule(IConstants.ADD_NEW_PROBLEM, stateId);
		statusList = problemManagementReportService.getAllProblemStatusInfo();
		deptScopes = problemManagementService.getAllDepartmentScopes(0L);
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
				//Should Be Refactored For Constituency Type Bellow
				//problemBean = problemManagementReportService.getConstituencyProblemsInfo(new Long(locationId), user.getRegistrationID(),status, IConstants.ASSEMBLY_ELECTION_TYPE);
			}else if(jObj.getString("task").equals("getParties")){
				result = staticDataService.getStaticParties();
			}else if(jObj.getString("task").equals("getPositions")){
				result = influencingPeopleService.getAllInfluencePeoplePositions();
			}else if(jObj.getString("task").equals("getInfluencingRange")){
				result = influencingPeopleService.getInfluenceRange();
			}else if(jObj.getString("task").equals("getProblemsBySelection")){
				
				Long locationId =  jObj.getLong("selectedLocation");
				Long selectedStatus = jObj.getLong("selectedStatus");
				Long selectedDept = jObj.getLong("selectedDept");
				Long selectedProblemScope = jObj.getLong("selectedProblemScope");
				
				if(selectedDept != null && selectedDept > 0L)
					problemBean = problemManagementReportService.getProblemsInfoBasedOnLocation(locationId, user.getRegistrationID(), 3L,  selectedProblemScope, selectedDept);
				else if(selectedDept == null || selectedDept == 0L)
					problemBean = problemManagementReportService.getProblemsInfoBasedOnLocation(locationId, user.getRegistrationID(), selectedStatus,  selectedProblemScope, selectedDept);
				
			} else if(jObj.getString("task").equals("getProblemsGroupedBySelection")){
				
				Long locationId =  jObj.getLong("selectedLocation");
				Long selectedStatus = jObj.getLong("selectedStatus");
				//Long selectedDept = jObj.getLong("selectedDept");
				Long selectedProblemScope = jObj.getLong("selectedProblemScope");
				Boolean groupByDept= jObj.getBoolean("groupByDept");
				Boolean groupByCadre = jObj.getBoolean("groupByCadre");
				
				//problemsGropedByDeptOrCadre = problemManagementReportService.getProblemsInfoBasedOnLocation(locationId, user.getRegistrationID(), selectedStatus,  selectedProblemScope, 0L,groupByCadre,groupByDept);
				
				problemsGropedByDeptOrCadre = problemManagementReportService.getProblemsInfoBasedOnLocation(locationId, user.getRegistrationID(), 3L,  selectedProblemScope, 0L,groupByCadre,groupByDept);
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
	
	public String getProblemHistoryDataNew(){
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				System.out.println("Result From JSON:"+jObj);
				problemlocationId = new Long(jObj.getLong("locationId"));
				problemBeanVO = problemManagementReportService.getProblemHistoryInfo(problemlocationId);
			}catch(Exception e){
				e.printStackTrace();
			}
		}			
		return SUCCESS;
	}
	
	public String getProblemsCountByStatusBasedOnAccessLevel(){
		String cPath = request.getContextPath();
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
			locationwiseProblemStatusInfoVO = problemManagementReportService.getProblemsStatusCount(user.getRegistrationID(),accessType, accessValue, 10);
					
			List<ProblemsCountByStatus> problemsCountbyStatus = locationwiseProblemStatusInfoVO.getProblemsCountByStatusForChart();
			
			String chartName = "allProblemsInfoByStatusAndDate_"+accessType+"_"+accessValue+".png";
			String chartPath = "";
			
			if(cPath.contains("PartyAnalyst"))
				chartPath = context.getRealPath("/")+ "charts\\" + chartName;
			else
				chartPath = chartProducerURL + chartName;
			ChartProducer.createLineChart("Problems That Are Fixed And Posted From Last 10 Days", "Days", "No. Of Problems",createDataset(problemsCountbyStatus), chartPath,260,550, null,false );
					
			locationwiseProblemStatusInfoVO.setLineChartPath(chartName);
		}	
		if(locationwiseProblemStatusInfoVO.getProblemsCountByStatus() != null && locationwiseProblemStatusInfoVO.getProblemsCountByStatus().size()>0)
		{
			String problemsStatusPieChartName = createProblemsPieChart(locationwiseProblemStatusInfoVO.getLocationId(),locationwiseProblemStatusInfoVO.getTotalProblemsCount(),locationwiseProblemStatusInfoVO.getProblemsCountByStatus());
			locationwiseProblemStatusInfoVO.setProblemsStatusChartName(problemsStatusPieChartName);
		}
		
		return SUCCESS;
	}
	
	public String createProblemsPieChart(Long locationId, int totalProblemsCount, List<ProblemsCountByStatus>problemsStatusList)	
	{
		String cPath = request.getContextPath();
		log.debug("Entered in to createProblemsPieChart method in ProblemManagementReportAction");
		String chartName = ""+locationId+"_"+totalProblemsCount+"piechart"+".png";
		String chartPath = "";
		
		if(cPath.contains("PartyAnalyst"))
			chartPath = context.getRealPath("/") + "charts\\" + chartName;
		else
			chartPath = chartProducerURL + chartName;
		final DefaultPieDataset dataset = new DefaultPieDataset();
		Color[] colors = new Color[problemsStatusList.size()];

		for(int i=0; i<problemsStatusList.size();i++)
		{
			if(!IConstants.FIXED.equals(problemsStatusList.get(i).getStatus()))
				{
				
					dataset.setValue(problemsStatusList.get(i).getStatus(), new BigDecimal(problemsStatusList.get(i).getCount()*100.0/totalProblemsCount).setScale(2, BigDecimal.ROUND_HALF_UP) );
					
					if(problemsStatusList.get(i).getStatus().equals(IConstants.NEW))
						colors[i]=IConstants.NEW_COLOR;
					if(problemsStatusList.get(i).getStatus().equals(IConstants.CLASSIFY))
						colors[i]=IConstants.CLASSIFY_COLOR;
					if(problemsStatusList.get(i).getStatus().equals(IConstants.ASSIGNED))
						colors[i]=IConstants.ASSIGNED_COLOR;
					if(problemsStatusList.get(i).getStatus().equals(IConstants.PROGRESS))
						colors[i]=IConstants.PROGRESS_COLOR;
					if(problemsStatusList.get(i).getStatus().equals(IConstants.PENDING))
						colors[i]=IConstants.PENDING_COLOR;
					
				}			
		}
		log.debug("size::::::::::::::::::::::::::::::::::::"+problemsStatusList.size());
		ChartProducer.createProblemsPieChart("", dataset, chartPath , colors,false,250,220);
		return chartName ;
	}
	
	public String problemsByDateBasedOnStatusAction()
	{
		if(log.isDebugEnabled())
			log.debug("Entered in to problemsByDateBasedOnStatusAction in problem mgmt report service");
		
		session=request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
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
		//String accessType = jObj.getString("accessType");
		//Long accessValue = jObj.getLong("accessValue");
		
		problemBean = problemManagementReportService.getProblemsPostedByStatusAndDates(user.getRegistrationID(),fromDate, toDate, statusId, accessType, accessValue);
		return SUCCESS;
		
	}

	private CategoryDataset createDataset(List<ProblemsCountByStatus> problemsCountbyStatus) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();        
        for(ProblemsCountByStatus graphInfo:problemsCountbyStatus)
        	dataset.addValue(new BigDecimal(graphInfo.getCount()), graphInfo.getStatus(),
           			graphInfo.getDate());	
           	
        return dataset;
    }
	
	public String getTotalProblemsCount()
	{
		session=request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		Long userId = null;
		
		if(user.getParentUserId() == null)
			userId = user.getRegistrationID();
		else 
			userId = user.getMainAccountId();
		
		if(task != null){
			try{
				jObj = new JSONObject(getTask());				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(jObj.getString("task").equalsIgnoreCase("getTotalProblemsCount"))
		{
			Long problemScope = jObj.getLong("selectedProblemScope");
			Long locationId = problemManagementReportService.getLocationValue(problemScope,jObj.getLong("selectedLocation"));
			probCountList = problemManagementReportService.getTotalProblemsCountForAnUserInARegion(userId,problemScope,locationId);
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getStatusWiseProblems"))
		{
			Long problemScope = jObj.getLong("selectedProblemScope");
			Long locationId   = problemManagementReportService.getLocationValue(problemScope,jObj.getLong("selectedLocation"));
			String status 	  = jObj.getString("status");
			problemBean = problemManagementReportService.getStatusWiseProblemsForAnUserInARegion(userId,problemScope,locationId,status);
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getTotalProblemsStatus"))
		{
			probCountList = problemManagementReportService.getTotalProblemsStatusForAnUser(userId);
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getStatusWiseAllProblems"))
		{
			String status 	  = jObj.getString("status");
			problemBean = problemManagementReportService.getStatusWiseProblemsForAnUser(userId,status);
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getCadreProblemsCountInARegion"))
		{
			Long problemScope = jObj.getLong("selectedProblemScope");
			Long locationId   = problemManagementReportService.getLocationValue(problemScope,jObj.getLong("selectedLocation"));
			probCountList = problemManagementReportService.getCadreProblemsCountInARegion(userId,problemScope,locationId);
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getCadreProblemsInARegion"))
		{
			Long problemScope = jObj.getLong("selectedProblemScope");
			Long locationId   = problemManagementReportService.getLocationValue(problemScope,jObj.getLong("selectedLocation"));
			String status 	  = jObj.getString("status");
			String location   = jObj.getString("sLocation");
			
			if(location.equalsIgnoreCase("cadre"))
				problemBean = problemManagementReportService.getCadreProblemsInARegion(userId,null,null,status);
			else if(location.equalsIgnoreCase("place"))
				problemBean = problemManagementReportService.getCadreProblemsInARegion(userId,problemScope,locationId,status);
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getCadreDetailsForProblemsInARegion"))
		{
			Long problemScope = jObj.getLong("selectedProblemScope");
			Long locationId   = problemManagementReportService.getLocationValue(problemScope,jObj.getLong("selectedLocation"));
			String status 	  = jObj.getString("status");
			String location   = jObj.getString("sLocation");
			
			if(location.equalsIgnoreCase("cadre"))
				problemBean = problemManagementReportService.getCadreDetailsForProblemsInARegion(userId,null,null,status);
			else if(location.equalsIgnoreCase("place"))
				problemBean = problemManagementReportService.getCadreDetailsForProblemsInARegion(userId,problemScope,locationId,status);
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getCadreProblemsCountForAnUser"))
		{
			probCountList = problemManagementReportService.getCadreProblemsCountInARegion(userId,null,null);
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getDeptWiseProblemsCountForAnUser"))
		{
			Long deptScopeId = jObj.getLong("deptScopeId");
			problemBean = problemManagementReportService.getDeptWiseProblemsCountForAnUser(userId,deptScopeId);
			
			if(problemBean != null && problemBean.size() > 0)
				problemBean.get(0).setProblemSourceScopeId(deptScopeId);
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getDepartmentWiseProblems"))
		{
			Long deptId 	= jObj.getLong("deptId");
			String status 	= jObj.getString("status");
			problemBean = problemManagementReportService.getDepartmentWiseProblemsBasedOnStatus(userId,deptId,status);
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getProblemsInADeptScopeBasedOnScope"))
		{
			Long scopeId 	= jObj.getLong("scopeId");
			String status 	= jObj.getString("status");
			problemBean = problemManagementReportService.getProblemsInADeptScopeBasedOnScope(userId,scopeId,status);
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getDeptWiseProblemsStatusInALocation"))
		{
			Long impactedRegionId = jObj.getLong("selectedProblemScope");
			Long locationId   = problemManagementReportService.getLocationValue(impactedRegionId,jObj.getLong("selectedLocation"));
			
			problemBean = problemManagementReportService.getDeptWiseProblemsCountInALocation(userId,impactedRegionId,locationId);
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getDeptProblemsBasedOnStatusInARegion"))
		{
			Long impactedRegionId = jObj.getLong("selectedProblemScope");
			Long locationId   = problemManagementReportService.getLocationValue(impactedRegionId,jObj.getLong("selectedLocation"));
			String status 	= jObj.getString("status");
			
			problemBean = problemManagementReportService.getDeptProblemsBasedOnStatusInARegion(userId,impactedRegionId,locationId,null,status);
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getDeptWiseProblemsBasedOnStatusInARegion"))
		{
			Long impactedRegionId = jObj.getLong("selectedProblemScope");
			Long locationId   = problemManagementReportService.getLocationValue(impactedRegionId,jObj.getLong("selectedLocation"));
			Long deptId 	= jObj.getLong("deptId");
			String status 	= jObj.getString("status");
			
			problemBean = problemManagementReportService.getDeptProblemsBasedOnStatusInARegion(userId,impactedRegionId,locationId,deptId,status);
		}
		
		return SUCCESS;
	}
}	
		

