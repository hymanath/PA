package com.itgrids.partyanalyst.web.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.transaction.JOnASTransactionManagerLookup;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemCompleteDetailsVO;
import com.itgrids.partyanalyst.dto.ProblemManagementChartVO;
import com.itgrids.partyanalyst.dto.ProblemManagementDataVO;
import com.itgrids.partyanalyst.dto.ProblemStatusDataVO;
import com.itgrids.partyanalyst.dto.ProblemsOfUserVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;


public class ProblemManagementAction extends ActionSupport implements ServletRequestAware{
	
	private final static Logger LOG = Logger.getLogger(ProblemManagementAction.class);
	
	private static final long serialVersionUID = 1L;
	
	private List<SelectOptionVO> problemSources;
	private HttpServletRequest request;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	private Long pHistoryId; 
	private String requestFrom;
	private boolean isRequest =false;
	private List<SelectOptionVO> stateListForProb;
	private List<SelectOptionVO> districtListForProb;
	private List<SelectOptionVO> constituencyListForProb;
	private List<SelectOptionVO> mandalListForProb;
	private List<SelectOptionVO> villagesListForProb;
	private RegionServiceDataImp regionServiceDataImp;
	private CadreManagementService cadreManagementService;
	private IStaticDataService staticDataService;
	private IProblemManagementService problemManagementService;
	private ProblemManagementDataVO problemManagementDataVO;
	private ProblemManagementChartVO problemManagementChartVO;
	private ProblemCompleteDetailsVO problemCompleteDetailsVO;
	private ProblemStatusDataVO problemStatusDataVO;
	private List<ProblemStatusDataVO> problemStatusDataVOList;
	private ProblemBeanVO problemBeanVO;
	private List<FileVO> uploadFilesList;
	private ResultStatus resultStatus;
	private ResultStatus actvtyState;
	private ResultStatus prblmStatus;
	
	
	
	public ResultStatus getPrblmStatus() {
		return prblmStatus;
	}

	public void setPrblmStatus(ResultStatus prblmStatus) {
		this.prblmStatus = prblmStatus;
	}

	public ResultStatus getActvtyState() {
		return actvtyState;
	}

	public void setActvtyState(ResultStatus actvtyState) {
		this.actvtyState = actvtyState;
	}

	public void setUploadFilesList(List<FileVO> uploadFilesList) {
		this.uploadFilesList = uploadFilesList;
	}

	public List<FileVO> getUploadFilesList() {
		return uploadFilesList;
	}

	public void setProblemBeanVO(ProblemBeanVO problemBeanVO) {
		this.problemBeanVO = problemBeanVO;
	}

	public ProblemBeanVO getProblemBeanVO() {
		return problemBeanVO;
	}

	public void setRequest(boolean isRequest) {
		this.isRequest = isRequest;
	}

	public boolean isRequest() {
		return isRequest;
	}

	public ProblemStatusDataVO getProblemStatusDataVO() {
		return problemStatusDataVO;
	}

	public void setProblemStatusDataVO(ProblemStatusDataVO problemStatusDataVO) {
		this.problemStatusDataVO = problemStatusDataVO;
	}

	public List<ProblemStatusDataVO> getProblemStatusDataVOList() {
		return problemStatusDataVOList;
	}

	public void setProblemStatusDataVOList(
			List<ProblemStatusDataVO> problemStatusDataVOList) {
		this.problemStatusDataVOList = problemStatusDataVOList;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}

	public List<SelectOptionVO> getProblemSources() {
		return problemSources;
	}

	public void setProblemSources(List<SelectOptionVO> problemSources) {
		this.problemSources = problemSources;
	}
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public ProblemCompleteDetailsVO getProblemCompleteDetailsVO() {
		return problemCompleteDetailsVO;
	}

	public void setProblemCompleteDetailsVO(
			ProblemCompleteDetailsVO problemCompleteDetailsVO) {
		this.problemCompleteDetailsVO = problemCompleteDetailsVO;
	}

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}
	
	public ProblemManagementDataVO getProblemManagementDataVO() {
		return problemManagementDataVO;
	}

	public void setProblemManagementDataVO(
			ProblemManagementDataVO problemManagementDataVO) {
		this.problemManagementDataVO = problemManagementDataVO;
	}	
	
	public ProblemManagementChartVO getProblemManagementChartVO() {
		return problemManagementChartVO;
	}

	public void setProblemManagementChartVO(
			ProblemManagementChartVO problemManagementChartVO) {
		this.problemManagementChartVO = problemManagementChartVO;
	}

	public List<SelectOptionVO> getStateListForProb() {
		return stateListForProb;
	}

	public void setStateListForProb(List<SelectOptionVO> stateListForProb) {
		this.stateListForProb = stateListForProb;
	}

	public List<SelectOptionVO> getDistrictListForProb() {
		return districtListForProb;
	}

	public void setDistrictListForProb(List<SelectOptionVO> districtListForProb) {
		this.districtListForProb = districtListForProb;
	}

	public List<SelectOptionVO> getConstituencyListForProb() {
		return constituencyListForProb;
	}

	public void setConstituencyListForProb(
			List<SelectOptionVO> constituencyListForProb) {
		this.constituencyListForProb = constituencyListForProb;
	}

	public List<SelectOptionVO> getMandalListForProb() {
		return mandalListForProb;
	}

	public void setMandalListForProb(List<SelectOptionVO> mandalListForProb) {
		this.mandalListForProb = mandalListForProb;
	}

	public List<SelectOptionVO> getVillagesListForProb() {
		return villagesListForProb;
	}

	public void setVillagesListForProb(List<SelectOptionVO> villagesListForProb) {
		this.villagesListForProb = villagesListForProb;
	}

	public RegionServiceDataImp getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(RegionServiceDataImp regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public Long getPHistoryId() {
		return pHistoryId;
	}

	public void setPHistoryId(Long historyId) {
		pHistoryId = historyId;
	}

	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}

	public String getRequestFrom() {
		return requestFrom;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String execute() throws Exception{
			problemManagementDataVO = new ProblemManagementDataVO();
			ProblemBeanVO problemBeanVO = new ProblemBeanVO();
			
			session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user==null)
				return ERROR;
				
			String param = null;
			param = getTask();
			
			try {
				jObj = new JSONObject(param);
				LOG.info(jObj);
			} catch (ParseException e) {
				e.printStackTrace();
			}	
			LOG.debug("Task::"+jObj.getString("task"));
			if(jObj.getString("task").equalsIgnoreCase("addNewProblem"))
			{
				problemBeanVO = new ProblemBeanVO();
				problemBeanVO.setUserID(user.getRegistrationID());
				problemBeanVO.setProblem(jObj.getString("problem"));
				problemBeanVO.setDescription(jObj.getString("description"));
				problemBeanVO.setState(jObj.getString("state"));
				problemBeanVO.setDistrict(jObj.getString("district"));
				problemBeanVO.setConstituency(jObj.getString("constituency"));
				problemBeanVO.setTehsil(jObj.getString("tehsil"));
				problemBeanVO.setVillage(jObj.getString("village"));
				problemBeanVO.setHamlet(jObj.getString("hamlet"));
				problemBeanVO.setReportedDate(jObj.getString("reportedDate"));
				problemBeanVO.setExistingFrom(jObj.getString("existingFrom"));
				problemBeanVO.setProbSourceId(Long.valueOf(jObj.getString("probSource").trim()));
				problemBeanVO.setName(jObj.getString("name"));
				problemBeanVO.setMobile(jObj.getString("mobile"));
				problemBeanVO.setPhone(jObj.getString("phone"));
				problemBeanVO.setEmail(jObj.getString("email"));
				problemBeanVO.setProblemStatusId(Long.valueOf(jObj.getString("status")));
				problemBeanVO.setAddress(jObj.getString("address"));
				problemBeanVO.setYear(IConstants.PRESENT_YEAR);
							
				 LOG.debug(problemBeanVO.getProblem());
				 LOG.debug(problemBeanVO.getDescription());
				 LOG.debug(problemBeanVO.getState());
				 LOG.debug(problemBeanVO.getDistrict());
				 LOG.debug(problemBeanVO.getConstituency());
				 LOG.debug(problemBeanVO.getTehsil());
				 LOG.debug(problemBeanVO.getHamlet());
				 LOG.debug(problemBeanVO.getReportedDate());
				 LOG.debug(problemBeanVO.getExistingFrom());
				 LOG.debug(problemBeanVO.getProbSource());
				 LOG.debug(problemBeanVO.getName());
				 LOG.debug(problemBeanVO.getMobile());
				 LOG.debug(problemBeanVO.getPhone());
				 LOG.debug(problemBeanVO.getEmail());
				 LOG.debug(problemBeanVO.getAddress());
				 problemManagementDataVO.setProblemBeanVO(problemManagementService.saveNewProblemData(problemBeanVO));
				 return Action.SUCCESS;
			} 
			if(jObj.getString("task").equalsIgnoreCase("newProblemsByUserID"))
			{
				//ProblemsOfUserVO obj = problemManagementService.getNewProblemsForUser(user.getRegistrationID(), Long.valueOf(1));
				
				String startIndex    = request.getParameter("startIndex");
				String resultsCount  = request.getParameter("resultsCount");
				
				ProblemsOfUserVO obj = problemManagementService.getUserProblemsInDifferentStagesByDate(user.getRegistrationID(), Integer.parseInt(startIndex), Integer.parseInt(resultsCount));
				
				problemManagementDataVO.setTotalResultsCount(obj.getTotalResultsCount());
				problemManagementDataVO.setProblemsOfUserVO(obj);
			}
			if(jObj.getString("task").equalsIgnoreCase("classifyProblem"))
			{
				LOG.debug("Task::"+jObj.getString("task"));
				JSONArray classifiedProblems = jObj.getJSONArray("prob");
				List<ProblemBeanVO> clsfdProbsList = new ArrayList<ProblemBeanVO>();
				int clsfyProbsSize = classifiedProblems.length();
				for(int i = 0; i< clsfyProbsSize; i++)
				{
					JSONObject clsfyProbsObj = classifiedProblems.getJSONObject(i);
					
					String problemTitle = clsfyProbsObj.getString("problem");
					String description = clsfyProbsObj.getString("description");
					String identifiedDate = clsfyProbsObj.getString("reportedDate");
					String existingFrom = clsfyProbsObj.getString("existingFrom");
					String locationId = clsfyProbsObj.getString("locationId");
					//String hamletId = clsfyProbsObj.getString("hamletId");
					//String location = clsfyProbsObj.getString("location");
					String probSource = clsfyProbsObj.getString("probSource");
					String probType = clsfyProbsObj.getString("probType");
					String probScope = clsfyProbsObj.getString("probScope");
					String status = clsfyProbsObj.getString("status");
					String problemHistoryId = clsfyProbsObj.getString("probHistoryId");
					problemBeanVO = new ProblemBeanVO();
					//problemBeanVO.setProblemId(Long.valueOf(probID));
					problemBeanVO.setProblem(problemTitle);
					problemBeanVO.setDescription(description);
					problemBeanVO.setReportedDate(identifiedDate);
					problemBeanVO.setExistingFrom(existingFrom);
					//problemBeanVO.setHamletId(Long.valueOf(hamletId));
					problemBeanVO.setProblemLocationId(Long.valueOf(locationId));
					problemBeanVO.setProbSource(probSource);
					problemBeanVO.setProblemSourceScope(probScope);
					problemBeanVO.setProblemClassification(probType);
					problemBeanVO.setProblemStatusId(Long.valueOf(status));
					problemBeanVO.setProblemHistoryId(Long.valueOf(problemHistoryId));
					
					clsfdProbsList.add(problemBeanVO);
					 LOG.debug(problemBeanVO.getProblemId());
					 LOG.debug(problemBeanVO.getProblem());
					 LOG.debug(problemBeanVO.getDescription());
					 LOG.debug(problemBeanVO.getHamlet());
					 LOG.debug(problemBeanVO.getReportedDate());
					 LOG.debug(problemBeanVO.getExistingFrom());
					 LOG.debug(problemBeanVO.getProbSource());
					 LOG.debug(problemBeanVO.getHamletId());
					 LOG.debug(problemBeanVO.getProblemLocationId());
					 LOG.debug(problemBeanVO.getProbSource());
					 LOG.debug(problemBeanVO.getProblemSourceScope());
					 LOG.debug(problemBeanVO.getProblemType());
					 //LOG.debug(problemBeanVO.getStatus());
				}		
				List<ProblemBeanVO> obj1 = problemManagementService.updateAndGetClassifiedProblemDataIntoDB(clsfdProbsList);
				LOG.debug("obj-------------------------------->>>>>>>>>>>>>>>>>>>>>>"+obj1.size()+"<<<<<<<<<<-------------------"+obj1.size()+"\n\n\n\n");
				problemManagementDataVO.setClassifiedProblems(obj1);
			} 
			if(jObj.getString("task").equalsIgnoreCase("classifiedProblemsByUserID"))
			{	
				String statusID = jObj.getString("status"); 
				List <ProblemBeanVO> obj = problemManagementService.getClassifiedProblemsOfUser(user.getRegistrationID(), Long.valueOf(statusID));
				problemManagementDataVO.setClassifiedProblems(obj);
			} 
			if(jObj.getString("task").equalsIgnoreCase("departmentsByScope"))
			{
				String problemScope = jObj.getString("scope");
				problemManagementDataVO.setProbConcernedDepts(problemManagementService.getDepartmentsForProblemScope(problemScope));
			}
			if(jObj.getString("task").equalsIgnoreCase("assignProblem"))
			{
				LOG.debug("Task::"+jObj.getString("task"));
				JSONArray assignedProblems = jObj.getJSONArray("assignedProblemsArray");
				List<ProblemBeanVO> assignedProbsList = new ArrayList<ProblemBeanVO>();
				int assignedProbsSize= assignedProblems.length();
				for(int i = 0; i< assignedProbsSize; i++)
				{
					JSONObject assignedProbsObj = assignedProblems.getJSONObject(i);
					
					String problemTitle = assignedProbsObj.getString("title");
					String identifiedDate = assignedProbsObj.getString("identifiedDate");
					String scope = assignedProbsObj.getString("scope");
					String problemType = assignedProbsObj.getString("problemType");
					String statusId = assignedProbsObj.getString("status");
					String problemHistoryId = assignedProbsObj.getString("problemHistoryId");
					String department = assignedProbsObj.getString("department");
					String departmentId = assignedProbsObj.getString("departmentId");
					problemBeanVO = new ProblemBeanVO();
					problemBeanVO.setProblem(problemTitle);
					problemBeanVO.setReportedDate(identifiedDate);
					problemBeanVO.setProblemSourceScope(scope);
					problemBeanVO.setProblemType(problemType);
					problemBeanVO.setProblemStatusId(Long.valueOf(statusId));
					problemBeanVO.setProblemHistoryId(Long.valueOf(problemHistoryId));
					problemBeanVO.setDepartment(department);
					problemBeanVO.setProbAssignedDeptId(Long.valueOf(departmentId));
					assignedProbsList.add(problemBeanVO);					
				}
				List<ProblemBeanVO> obj2 = problemManagementService.updateAndGetAssignedProblems(assignedProbsList);
				problemManagementDataVO.setAssignedProblems(obj2);;
				
			}
			if(jObj.getString("task").equalsIgnoreCase("assignedProblemsByUserID"))
			{
				LOG.debug("Task::"+jObj.getString("task"));
				String statusID = jObj.getString("status"); 
				List <ProblemBeanVO> obj = problemManagementService.getAssignedProblems(user.getRegistrationID(), Long.valueOf(statusID));
				problemManagementDataVO.setAssignedProblems(obj);
			}
			if(jObj.getString("task").equalsIgnoreCase("moveAssignedProblemsToProgress"))
			{
				LOG.debug("Task::"+jObj.getString("task"));
				JSONArray progressProblems = jObj.getJSONArray("progressProblemsArray");
				List<ProblemBeanVO> progressProblemsList = new ArrayList<ProblemBeanVO>();
				int progressProblemsSize = progressProblems.length();
				for(int i = 0;i< progressProblemsSize; i++)
				{
					JSONObject progressProblemsObj = progressProblems.getJSONObject(i);
					
					String problem = progressProblemsObj.getString("title");
					String concernedDepartment = progressProblemsObj.getString("concernedDepartment");
					String assignedOfficial = progressProblemsObj.getString("assignedOfficial");
					String name = progressProblemsObj.getString("name");
					String contactNumber = progressProblemsObj.getString("contactNumber");
					String comments = progressProblemsObj.getString("comments");
					String assignedProblemProgressId = progressProblemsObj.getString("assignedProblemProgressId");
					String status = progressProblemsObj.getString("status");
					
					problemBeanVO = new ProblemBeanVO();
					
					problemBeanVO.setProblem(problem);
					problemBeanVO.setDepartment(concernedDepartment);
					problemBeanVO.setDesignation(assignedOfficial);
					problemBeanVO.setDepartmentConcernedPersonName(name);
					problemBeanVO.setDepartmentConcernedPersonPhoneNumber(contactNumber);
					problemBeanVO.setComments(comments);
					problemBeanVO.setAssignedProblemProgressId(Long.valueOf(assignedProblemProgressId));
					problemBeanVO.setProblemStatusId(Long.valueOf(status));	
					
					progressProblemsList.add(problemBeanVO);
				}
				List<ProblemBeanVO> obj = problemManagementService.updateAndGetProblemsUnderProgress(progressProblemsList);
				problemManagementDataVO.setProgressedProblems(obj);
				
			}
			if(jObj.getString("task").equalsIgnoreCase("progressedProblemsByUserID"))
			{
				LOG.debug("Task::"+jObj.getString("task"));
				String statusID = jObj.getString("status"); 
				List <ProblemBeanVO> obj = problemManagementService.getProblemsUnderProgress(user.getRegistrationID(), Long.valueOf(statusID));
				problemManagementDataVO.setProgressedProblems(obj);
				
			}
			if(jObj.getString("task").equalsIgnoreCase("moveProgressProblemsToPending"))
			{
				LOG.debug("Task::"+jObj.getString("task"));
				JSONArray pendingProblems = jObj.getJSONArray("pendingProbArray");
				List<ProblemBeanVO> pendingProblemsList = new ArrayList<ProblemBeanVO>();
				
				for(int i = 0;i< pendingProblems.length(); i++)
				{
					JSONObject pendingProblemsObj = pendingProblems.getJSONObject(i);
					
					problemBeanVO = new ProblemBeanVO();
					
					problemBeanVO.setProblem(pendingProblemsObj.getString("title"));
					problemBeanVO.setDepartment(pendingProblemsObj.getString("concernedDepartment"));
					problemBeanVO.setDesignation(pendingProblemsObj.getString("assignedOfficial"));
					problemBeanVO.setDepartmentConcernedPersonName(pendingProblemsObj.getString("name"));
					problemBeanVO.setDepartmentConcernedPersonPhoneNumber(pendingProblemsObj.getString("contactNumber"));
					problemBeanVO.setReasonForPending(pendingProblemsObj.getString("comments"));
					problemBeanVO.setAssignedProblemProgressId(Long.valueOf(pendingProblemsObj.getString("assignedProblemProgressId")));
					problemBeanVO.setProblemStatusId(Long.valueOf(pendingProblemsObj.getString("status")));	
					problemBeanVO.setReportedDate(pendingProblemsObj.getString("identifiedDate"));
					problemBeanVO.setUpdatedDate(pendingProblemsObj.getString("updatedDate"));
					problemBeanVO.setComments(pendingProblemsObj.getString("comments"));
					pendingProblemsList.add(problemBeanVO);
				}
				List<ProblemBeanVO> obj = problemManagementService.updateAndGetProblemsUnderPending(pendingProblemsList);
				problemManagementDataVO.setPendingProblems(obj);
			}
			if(jObj.getString("task").equalsIgnoreCase("moveProgressProblemsToFixed"))
			{
				LOG.debug("Task::"+jObj.getString("task"));
				JSONArray fixedProblems = jObj.getJSONArray("fixedProblemsArray");
				List<ProblemBeanVO> fixedProblemsList = new ArrayList<ProblemBeanVO>();
				
				for(int i = 0;i< fixedProblems.length(); i++)
				{
					JSONObject fixedProblemsObj = fixedProblems.getJSONObject(i);
									 
					
					problemBeanVO = new ProblemBeanVO();
					
					problemBeanVO.setProblem(fixedProblemsObj.getString("title"));
					problemBeanVO.setDepartment(fixedProblemsObj.getString("concernedDepartment"));
					problemBeanVO.setDesignation(fixedProblemsObj.getString("assignedOfficial"));
					problemBeanVO.setDepartmentConcernedPersonName(fixedProblemsObj.getString("name"));
					problemBeanVO.setDepartmentConcernedPersonPhoneNumber(fixedProblemsObj.getString("contactNumber"));
					problemBeanVO.setComments(fixedProblemsObj.getString("comments"));
					problemBeanVO.setAssignedProblemProgressId(Long.valueOf(fixedProblemsObj.getString("assignedProblemProgressId")));
					problemBeanVO.setProblemStatusId(Long.valueOf(fixedProblemsObj.getString("status")));	
					problemBeanVO.setReportedDate(fixedProblemsObj.getString("identifiedDate"));
					problemBeanVO.setUpdatedDate(fixedProblemsObj.getString("updatedDate"));
					
					fixedProblemsList.add(problemBeanVO);
				}
				List<ProblemBeanVO> obj = problemManagementService.updateAndGetProblemsUnderFixed(fixedProblemsList);
				problemManagementDataVO.setFixedProblems(obj);
	
			} 
			if(jObj.getString("task").equalsIgnoreCase("pendingProblemsByUserID"))
			{
				LOG.debug("Task::"+jObj.getString("task"));
				String statusID = jObj.getString("status"); 
				List <ProblemBeanVO> obj = problemManagementService.getPendingProblemsForAnUser(user.getRegistrationID(), Long.valueOf(statusID));
				problemManagementDataVO.setPendingProblems(obj);
			}
			if(jObj.getString("task").equalsIgnoreCase("fixedProblemsByUserID"))
			{
				LOG.debug("Task::"+jObj.getString("task"));
				String statusID = jObj.getString("status"); 
				List <ProblemBeanVO> obj = problemManagementService.getFixedProblemsForUser(user.getRegistrationID(), Long.valueOf(statusID));
				problemManagementDataVO.setFixedProblems(obj);
			}
			if(jObj.getString("task").equalsIgnoreCase("movePendingProblemsToProgress"))
			{
				LOG.debug("Task::"+jObj.getString("task"));
				JSONArray pendingToProgressProblems = jObj.getJSONArray("progressedProblemsArray");
				List<ProblemBeanVO> progressProblemsList = new ArrayList<ProblemBeanVO>();
				
				for(int i = 0;i< pendingToProgressProblems.length(); i++)
				{
					JSONObject pendingToProgressProblemsObj = pendingToProgressProblems.getJSONObject(i);
					
					String problem = pendingToProgressProblemsObj.getString("title");
					String concernedDepartment = pendingToProgressProblemsObj.getString("concernedDepartment");
					String assignedOfficial = pendingToProgressProblemsObj.getString("assignedOfficial");
					String name = pendingToProgressProblemsObj.getString("name");
					String contactNumber = pendingToProgressProblemsObj.getString("contactNumber");
					String comments = pendingToProgressProblemsObj.getString("comments");
					String assignedProblemProgressId = pendingToProgressProblemsObj.getString("assignedProblemProgressId");
					String status = pendingToProgressProblemsObj.getString("status");
					//String updatedDate = pendingToProgressProblemsObj.getString("updatedDate");
					
					
					problemBeanVO = new ProblemBeanVO();
					
					problemBeanVO.setProblem(problem);
					problemBeanVO.setDepartment(concernedDepartment);
					problemBeanVO.setDesignation(assignedOfficial);
					problemBeanVO.setDepartmentConcernedPersonName(name);
					problemBeanVO.setDepartmentConcernedPersonPhoneNumber(contactNumber);
					problemBeanVO.setComments(comments);
					problemBeanVO.setAssignedProblemProgressId(Long.valueOf(assignedProblemProgressId)); //check this line......
					problemBeanVO.setProblemStatusId(Long.valueOf(status));	
					
					progressProblemsList.add(problemBeanVO);
				}
				List<ProblemBeanVO> obj = problemManagementService.updateAndGetProblemsUnderProgress(progressProblemsList);
				problemManagementDataVO.setProgressedProblems(obj);
			}
			
	 	return Action.SUCCESS;
	}	
	
	public String getProblemsByFilterView()
	{
		
		problemManagementDataVO = new ProblemManagementDataVO();
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user==null)
			return ERROR;
		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			LOG.info(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		
		String sDate = jObj.getString("startText");
		String eDate = jObj.getString("endText");
		Long statusId = jObj.getLong("statusValue");
		
		String startIndex = request.getParameter("startIndex");
		String resultsCount = request.getParameter("resultsCount");
		
		DateFormat format = 
			 new SimpleDateFormat("yyyy-MM-dd");
		try {
			
			Date startDate = null;
			Date endDate = null;
			if(sDate != null && !sDate.equalsIgnoreCase(""))
				startDate = (Date)format.parse(sDate);
			if(eDate != null && !eDate.equalsIgnoreCase(""))
				endDate =  (Date)format.parse(eDate);
			
			ProblemsOfUserVO obj = problemManagementService.getUserProblemsInDifferentStagesByFilters(user.getRegistrationID(), statusId, startDate, endDate, Integer.parseInt(startIndex), Integer.parseInt(resultsCount));
			
			problemManagementDataVO.setTotalResultsCount(obj.getTotalResultsCount());
			problemManagementDataVO.setProblemsOfUserVO(obj);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	public String getProblemDetailsAndStatus()
	{
		session = request.getSession();
		Long userId = null;
		pHistoryId = Long.parseLong(request.getParameter("pHistoryId"));
		requestFrom = request.getParameter("requestFrom");
		if(requestFrom!=null && !requestFrom.equalsIgnoreCase("")){
			if(requestFrom.equalsIgnoreCase("callCenter"))
			  setRequest(true);
		}
		else 
			setRequest(false);
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		
		if(regVO==null)
			return ERROR;
		
		if(regVO != null)
			userId = regVO.getRegistrationID();
		
		String accessType =regVO.getAccessType();
		Long accessValue= Long.valueOf(regVO.getAccessValue());
		
		stateListForProb        = new ArrayList<SelectOptionVO>(0);
		districtListForProb     = new ArrayList<SelectOptionVO>(0);
		constituencyListForProb = new ArrayList<SelectOptionVO>(0);
		mandalListForProb       = new ArrayList<SelectOptionVO>(0);
		villagesListForProb     = new ArrayList<SelectOptionVO>(0);
		
		if("MLA".equals(accessType))
		{
			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByConstituencyID(accessValue);
			stateListForProb.add(list.get(0));			
			districtListForProb.add(list.get(1));
			constituencyListForProb.add(list.get(2));
			mandalListForProb = regionServiceDataImp.getSubRegionsInConstituency(accessValue, IConstants.PRESENT_YEAR, null);
			mandalListForProb.add(0,new SelectOptionVO(0l,"Select Location"));
		}
		else if("DISTRICT".equals(accessType))
		{
			List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByDistrictID(accessValue);
			
			stateListForProb.add(list.get(0));
			districtListForProb.add(list.get(1));
			
			constituencyListForProb = regionServiceDataImp.getConstituenciesByDistrictID(accessValue);
			constituencyListForProb.add(0,new SelectOptionVO(0l,"Select Constituency"));
		}
		else if("STATE".equals(accessType))
		{
			String name = cadreManagementService.getStateName(accessValue);
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(accessValue);
			selectOptionVO.setName(name);
			stateListForProb.add(selectOptionVO);
			districtListForProb = staticDataService.getDistricts(accessValue);
			districtListForProb.add(0,new SelectOptionVO(0l,"Select District"));
		}
		
		session.setAttribute(ISessionConstants.STATES_PROB, stateListForProb);
		session.setAttribute(ISessionConstants.DISTRICTS_PROB,districtListForProb);
		session.setAttribute(ISessionConstants.CONSTITUENCIES_PROB,constituencyListForProb);
		session.setAttribute(ISessionConstants.MANDALS_PROB,mandalListForProb);	
		session.setAttribute(ISessionConstants.VILLAGES_PROB,villagesListForProb);
		
		problemCompleteDetailsVO = problemManagementService.getProblemCompleteInformationByProblemHistory(userId , pHistoryId);
		
		if(problemCompleteDetailsVO == null)
			return ERROR;
		
		return Action.SUCCESS;
			
		
	}
	
	public String getProblemCompleteDetails(){
		problemManagementDataVO = new ProblemManagementDataVO();
		ProblemBeanVO problemBeanVO = new ProblemBeanVO();
		
		session = request.getSession();
					
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			LOG.info(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		LOG.debug("Task::"+jObj.getString("task"));
		Long problemHistoryId = jObj.getLong("problemHistoryId");
		problemBeanVO = problemManagementService.getProblemCompleteInfo(problemHistoryId);
		return Action.SUCCESS;
	}
	
	
	public String getProblemsPostedByUserToBuildCharts(){
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user==null)
			return ERROR;
		
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			LOG.info(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		
		Long problemStatusId = jObj.getLong("statusId");
		String problemStatus = jObj.getString("statusName");
		String problemsBy    = jObj.getString("dateType");
		String startIndex    = jObj.getString("startIndex");
		String maxResults    = jObj.getString("maxResults");
		
		String getProblemsBy = "";
		
		if(problemsBy.equalsIgnoreCase("date"))
			getProblemsBy = IConstants.PROBLEMS_BY_DATE;
		else if(problemsBy.equalsIgnoreCase("month"))
			getProblemsBy = IConstants.PROBLEMS_BY_MONTH;
			
		
		if(problemStatusId == null || problemStatusId.equals(0L))
			problemManagementChartVO = problemManagementService.getProblemsDataForAUserToBuildChart(user.getRegistrationID(), Integer.parseInt(startIndex), Integer.parseInt(maxResults), getProblemsBy);
		else
			problemManagementChartVO = problemManagementService.getProblemsDataForAUserToBuildChart(user.getRegistrationID(), problemStatusId, Integer.parseInt(startIndex), Integer.parseInt(maxResults), getProblemsBy);
		
	 return Action.SUCCESS;
	}
	
	public String getProblemsPostedDataByUserToBuildCharts()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		Long userId = null;
		
		if(user==null)
			return ERROR;
		
		/*if(user.getParentUserId() == null)
			userId = user.getRegistrationID();
		else
			userId = user.getMainAccountId();*/
		
		//problemManagementChartVO = problemManagementService.getOverallProblemsCountInDifferentLifeCycleStagesPostedByUser(userId);
		problemManagementChartVO = problemManagementService.getOverallProblemsCountInDifferentLifeCycleStagesPostedByUser(user.getRegistrationID());
		return Action.SUCCESS;
	}
	
	public String getProblemPresentStatus()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);			
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		Long userId = null;
		
		if(user==null)
			return ERROR;
		
			userId = user.getRegistrationID();
		problemStatusDataVO = problemManagementService.getProblemRecentDetailsByProblemId(jObj.getLong("pHistoryId"),userId);
		
		return Action.SUCCESS;
	}
	
	public String getProblemRecentActivities()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);			
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		Long userId = null;
		
		if(user==null)
			return ERROR;
		
			userId = user.getRegistrationID();
		
		problemStatusDataVOList = problemManagementService.getAllProblemRecentActivityDetails(jObj.getLong("pHistoryId"),userId);
		
		return Action.SUCCESS;
	}
	public String getProblemRelatedImages(){
		
		try  {
		jObj = new JSONObject(getTask());
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		Long userId = null;
		
		if(user==null)
			return ERROR;
		
			userId = user.getRegistrationID();
		Long problemId = jObj.getLong("pHistoryId");
		uploadFilesList = problemManagementService.getAllProblemRelatedImages(problemId,userId);
		}catch(ParseException e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String freeUserProblemAssignedToCustomer()
	{
		try{
			Long userId = null;
			jObj = new JSONObject(getTask());
			RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
			if(regVO == null)
				return ERROR;
			if(regVO != null && regVO.getRegistrationID() > 0)
			{
				userId = regVO.getRegistrationID();
				resultStatus = problemManagementService.freeUserProblemAssignedToCustomer(userId,jObj.getString("problemVisibility"),jObj.getLong("problemId"));
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in freeUserProblemAssignedToCustomer() Method, Exception - "+e);
		}
		return SUCCESS;
	}
	
	public String changeProblemActivity(){
		try{
			jObj=new JSONObject(getTask());
			Long prblmPrgrssId=jObj.getLong("prblmPrgrssId");
			
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user==null)
			return ERROR;
		
			String task=jObj.getString("task");
			actvtyState = problemManagementService.changeActivityState(prblmPrgrssId,task);
					
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String makeProblemPublic(){
		try{
			jObj=new JSONObject(getTask());
			Long problemId = jObj.getLong("pHistoryId");
			
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user==null)
			return ERROR;
			
			String task=jObj.getString("task");
			prblmStatus= problemManagementService.changeProblemToPublic(problemId,task);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
