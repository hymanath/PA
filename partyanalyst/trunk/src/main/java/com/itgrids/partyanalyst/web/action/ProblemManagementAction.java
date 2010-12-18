package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemManagementDataVO;
import com.itgrids.partyanalyst.dto.ProblemsOfUserVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;


public class ProblemManagementAction extends ActionSupport implements ServletRequestAware{
	
	private final static Logger log = Logger.getLogger(ProblemManagementAction.class);
	
	private static final long serialVersionUID = 1L;
	
	private List<SelectOptionVO> problemSources;
	private HttpServletRequest request;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	private IProblemManagementService problemManagementService;
	private ProblemManagementDataVO problemManagementDataVO;
	
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
				System.out.println(jObj);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			log.debug("Task::"+jObj.getString("task"));
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
				problemBeanVO.setProbSourceId(new Long(jObj.getString("probSource").trim()));
				problemBeanVO.setName(jObj.getString("name"));
				problemBeanVO.setMobile(jObj.getString("mobile"));
				problemBeanVO.setPhone(jObj.getString("phone"));
				problemBeanVO.setEmail(jObj.getString("email"));
				problemBeanVO.setProblemStatusId(new Long(jObj.getString("status")));
				problemBeanVO.setAddress(jObj.getString("address"));
				problemBeanVO.setYear(IConstants.PRESENT_YEAR);
							
				 log.debug(problemBeanVO.getProblem());
				 log.debug(problemBeanVO.getDescription());
				 log.debug(problemBeanVO.getState());
				 log.debug(problemBeanVO.getDistrict());
				 log.debug(problemBeanVO.getConstituency());
				 log.debug(problemBeanVO.getTehsil());
				 log.debug(problemBeanVO.getHamlet());
				 log.debug(problemBeanVO.getReportedDate());
				 log.debug(problemBeanVO.getExistingFrom());
				 log.debug(problemBeanVO.getProbSource());
				 log.debug(problemBeanVO.getName());
				 log.debug(problemBeanVO.getMobile());
				 log.debug(problemBeanVO.getPhone());
				 log.debug(problemBeanVO.getEmail());
				 log.debug(problemBeanVO.getAddress());
				 problemManagementDataVO.setProblemBeanVO(problemManagementService.saveNewProblemData(problemBeanVO));
				 return Action.SUCCESS;
			} 
			if(jObj.getString("task").equalsIgnoreCase("newProblemsByUserID"))
			{
				ProblemsOfUserVO obj = problemManagementService.getNewProblemsForUser(user.getRegistrationID(), new Long(1));
				log.debug("New Problems By User ID>>>>>>>>>>>>>>>>>>>>>>"+obj.getProblemRegionScopes().size()+"<<<<<<<<<<-------------------"+obj.getProblemTypes().size());
				problemManagementDataVO.setProblemsOfUserVO(obj);
			}
			if(jObj.getString("task").equalsIgnoreCase("classifyProblem"))
			{
				log.debug("Task::"+jObj.getString("task"));
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
					//problemBeanVO.setProblemId(new Long(probID));
					problemBeanVO.setProblem(problemTitle);
					problemBeanVO.setDescription(description);
					problemBeanVO.setReportedDate(identifiedDate);
					problemBeanVO.setExistingFrom(existingFrom);
					//problemBeanVO.setHamletId(new Long(hamletId));
					problemBeanVO.setProblemLocationId(new Long(locationId));
					problemBeanVO.setProbSource(probSource);
					problemBeanVO.setProblemSourceScope(probScope);
					problemBeanVO.setProblemClassification(probType);
					problemBeanVO.setProblemStatusId(new Long(status));
					problemBeanVO.setProblemHistoryId(new Long(problemHistoryId));
					
					clsfdProbsList.add(problemBeanVO);
					 log.debug(problemBeanVO.getProblemId());
					 log.debug(problemBeanVO.getProblem());
					 log.debug(problemBeanVO.getDescription());
					 log.debug(problemBeanVO.getHamlet());
					 log.debug(problemBeanVO.getReportedDate());
					 log.debug(problemBeanVO.getExistingFrom());
					 log.debug(problemBeanVO.getProbSource());
					 log.debug(problemBeanVO.getHamletId());
					 log.debug(problemBeanVO.getProblemLocationId());
					 log.debug(problemBeanVO.getProbSource());
					 log.debug(problemBeanVO.getProblemSourceScope());
					 log.debug(problemBeanVO.getProblemType());
					 //log.debug(problemBeanVO.getStatus());
				}		
				List<ProblemBeanVO> obj1 = problemManagementService.updateAndGetClassifiedProblemDataIntoDB(clsfdProbsList);
				log.debug("obj-------------------------------->>>>>>>>>>>>>>>>>>>>>>"+obj1.size()+"<<<<<<<<<<-------------------"+obj1.size()+"\n\n\n\n");
				problemManagementDataVO.setClassifiedProblems(obj1);
			} 
			if(jObj.getString("task").equalsIgnoreCase("classifiedProblemsByUserID"))
			{	
				String statusID = jObj.getString("status"); 
				List <ProblemBeanVO> obj = problemManagementService.getClassifiedProblemsOfUser(user.getRegistrationID(), new Long(statusID));
				problemManagementDataVO.setClassifiedProblems(obj);
			} 
			if(jObj.getString("task").equalsIgnoreCase("departmentsByScope"))
			{
				String problemScope = jObj.getString("scope");
				problemManagementDataVO.setProbConcernedDepts(problemManagementService.getDepartmentsForProblemScope(problemScope));
			}
			if(jObj.getString("task").equalsIgnoreCase("assignProblem"))
			{
				log.debug("Task::"+jObj.getString("task"));
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
					problemBeanVO.setProblemStatusId(new Long(statusId));
					problemBeanVO.setProblemHistoryId(new Long(problemHistoryId));
					problemBeanVO.setDepartment(department);
					problemBeanVO.setProbAssignedDeptId(new Long(departmentId));
					assignedProbsList.add(problemBeanVO);					
				}
				List<ProblemBeanVO> obj2 = problemManagementService.updateAndGetAssignedProblems(assignedProbsList);
				problemManagementDataVO.setAssignedProblems(obj2);;
				
			}
			if(jObj.getString("task").equalsIgnoreCase("assignedProblemsByUserID"))
			{
				log.debug("Task::"+jObj.getString("task"));
				String statusID = jObj.getString("status"); 
				List <ProblemBeanVO> obj = problemManagementService.getAssignedProblems(user.getRegistrationID(), new Long(statusID));
				problemManagementDataVO.setAssignedProblems(obj);
			}
			if(jObj.getString("task").equalsIgnoreCase("moveAssignedProblemsToProgress"))
			{
				log.debug("Task::"+jObj.getString("task"));
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
					problemBeanVO.setAssignedProblemProgressId(new Long(assignedProblemProgressId));
					problemBeanVO.setProblemStatusId(new Long(status));	
					
					progressProblemsList.add(problemBeanVO);
				}
				List<ProblemBeanVO> obj = problemManagementService.updateAndGetProblemsUnderProgress(progressProblemsList);
				problemManagementDataVO.setProgressedProblems(obj);
				
			}
			if(jObj.getString("task").equalsIgnoreCase("progressedProblemsByUserID"))
			{
				log.debug("Task::"+jObj.getString("task"));
				String statusID = jObj.getString("status"); 
				List <ProblemBeanVO> obj = problemManagementService.getProblemsUnderProgress(user.getRegistrationID(), new Long(statusID));
				problemManagementDataVO.setProgressedProblems(obj);
				
			}
			if(jObj.getString("task").equalsIgnoreCase("moveProgressProblemsToPending"))
			{
				log.debug("Task::"+jObj.getString("task"));
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
					problemBeanVO.setAssignedProblemProgressId(new Long(pendingProblemsObj.getString("assignedProblemProgressId")));
					problemBeanVO.setProblemStatusId(new Long(pendingProblemsObj.getString("status")));	
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
				log.debug("Task::"+jObj.getString("task"));
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
					problemBeanVO.setAssignedProblemProgressId(new Long(fixedProblemsObj.getString("assignedProblemProgressId")));
					problemBeanVO.setProblemStatusId(new Long(fixedProblemsObj.getString("status")));	
					problemBeanVO.setReportedDate(fixedProblemsObj.getString("identifiedDate"));
					problemBeanVO.setUpdatedDate(fixedProblemsObj.getString("updatedDate"));
					
					fixedProblemsList.add(problemBeanVO);
				}
				List<ProblemBeanVO> obj = problemManagementService.updateAndGetProblemsUnderFixed(fixedProblemsList);
				problemManagementDataVO.setFixedProblems(obj);
	
			} 
			if(jObj.getString("task").equalsIgnoreCase("pendingProblemsByUserID"))
			{
				log.debug("Task::"+jObj.getString("task"));
				String statusID = jObj.getString("status"); 
				List <ProblemBeanVO> obj = problemManagementService.getPendingProblemsForAnUser(user.getRegistrationID(), new Long(statusID));
				problemManagementDataVO.setPendingProblems(obj);
			}
			if(jObj.getString("task").equalsIgnoreCase("fixedProblemsByUserID"))
			{
				log.debug("Task::"+jObj.getString("task"));
				String statusID = jObj.getString("status"); 
				List <ProblemBeanVO> obj = problemManagementService.getFixedProblemsForUser(user.getRegistrationID(), new Long(statusID));
				problemManagementDataVO.setFixedProblems(obj);
			}
			if(jObj.getString("task").equalsIgnoreCase("movePendingProblemsToProgress"))
			{
				log.debug("Task::"+jObj.getString("task"));
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
					problemBeanVO.setAssignedProblemProgressId(new Long(assignedProblemProgressId)); //check this line......
					problemBeanVO.setProblemStatusId(new Long(status));	
					
					progressProblemsList.add(problemBeanVO);
				}
				List<ProblemBeanVO> obj = problemManagementService.updateAndGetProblemsUnderProgress(progressProblemsList);
				problemManagementDataVO.setProgressedProblems(obj);
			}
			
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
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		log.debug("Task::"+jObj.getString("task"));
		Long problemHistoryId = jObj.getLong("problemHistoryId");
		problemBeanVO = problemManagementService.getProblemCompleteInfo(problemHistoryId);
		return Action.SUCCESS;
	}
	
	
}
