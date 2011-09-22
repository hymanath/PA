package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ProblemAssigningAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = -2691859423522316985L;
	private HttpServletRequest request;
	JSONObject jObj = null;
	private String task;
	private Long probHistoryId;
	private Long problemType;
	private Long resolvingDeptScope;
	private Long resolvingDeptScopeValue;
	private Long deptCategory;
	private Long dept;
	private Long cadreId;
	private String comments;
	private Long state;
	private Long district;
	private Long constituency;
	private Long mandal;
	private Long village;
	private String isSubmit;
	private String statusToChange;
	
	private List<SelectOptionVO> deptScopesList;
	private ResultStatus resultStatus;
	
	private HttpSession session;
	
	private IProblemManagementService problemManagementService;
	
	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}
	
	public void setHttpServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public Long getProbHistoryId() {
		return probHistoryId;
	}

	public void setProbHistoryId(Long probHistoryId) {
		this.probHistoryId = probHistoryId;
	}

	public Long getCadreId() {
		return cadreId;
	}

	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}

	public Long getDept() {
		return dept;
	}

	public void setDept(Long dept) {
		this.dept = dept;
	}

	public Long getProblemType() {
		return problemType;
	}

	public void setProblemType(Long problemType) {
		this.problemType = problemType;
	}

	public List<SelectOptionVO> getDeptScopesList() {
		return deptScopesList;
	}

	public void setDeptScopesList(List<SelectOptionVO> deptScopesList) {
		this.deptScopesList = deptScopesList;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Long getResolvingDeptScope() {
		return resolvingDeptScope;
	}

	public void setResolvingDeptScope(Long resolvingDeptScope) {
		this.resolvingDeptScope = resolvingDeptScope;
	}

	public Long getDeptCategory() {
		return deptCategory;
	}

	public void setDeptCategory(Long deptCategory) {
		this.deptCategory = deptCategory;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public Long getDistrict() {
		return district;
	}

	public void setDistrict(Long district) {
		this.district = district;
	}

	public Long getConstituency() {
		return constituency;
	}

	public void setConstituency(Long constituency) {
		this.constituency = constituency;
	}

	public Long getMandal() {
		return mandal;
	}

	public void setMandal(Long mandal) {
		this.mandal = mandal;
	}

	public Long getVillage() {
		return village;
	}

	public void setVillage(Long village) {
		this.village = village;
	}

	public Long getResolvingDeptScopeValue() {
		return resolvingDeptScopeValue;
	}

	public void setResolvingDeptScopeValue(Long resolvingDeptScopeValue) {
		this.resolvingDeptScopeValue = resolvingDeptScopeValue;
	}

	public String getIsSubmit() {
		return isSubmit;
	}

	public void setIsSubmit(String isSubmit) {
		this.isSubmit = isSubmit;
	}

	public String getStatusToChange() {
		return statusToChange;
	}

	public void setStatusToChange(String statusToChange) {
		this.statusToChange = statusToChange;
	}

	public String execute() throws Exception{
		
		if(resolvingDeptScope == 1)
			resolvingDeptScopeValue = state;
		else if(resolvingDeptScope == 2)
			resolvingDeptScopeValue = district;
		else if(resolvingDeptScope >=3 &&  resolvingDeptScope <= 6)
			resolvingDeptScopeValue = mandal;
		else if(resolvingDeptScope == 7 || resolvingDeptScope == 8)
			resolvingDeptScopeValue = village;
		
		System.out.println("========================");
		System.out.println("probHistoryId---"+probHistoryId);
		System.out.println("problemType---"+problemType);
		System.out.println("resolvingDeptScope---"+resolvingDeptScope);
		System.out.println("resolvingDeptScopeValue---"+resolvingDeptScopeValue);
		System.out.println("dept---"+dept);
		System.out.println("cadreId---"+cadreId);
		System.out.println("comments---"+comments);
		System.out.println("========================");
		cadreId = cadreId != null? cadreId:0l;
		
		problemManagementService.changePostedProblemStatusForAnUser(probHistoryId, problemType, resolvingDeptScope, dept, cadreId, resolvingDeptScopeValue, comments, statusToChange);
		return SUCCESS;
	}
	
	public void validate()
	{
		if(isSubmit != null && isSubmit.equalsIgnoreCase("true"))
		{
			boolean DeptScopeFlag = false;
			if(resolvingDeptScope == 1 && state < 1)
				DeptScopeFlag = true;
			else if(resolvingDeptScope == 2 && district < 1)
				DeptScopeFlag = true;
			else if((resolvingDeptScope >=3 &&  resolvingDeptScope <= 6) && mandal < 1)
				DeptScopeFlag = true;
			else if((resolvingDeptScope == 7 || resolvingDeptScope == 8) && village < 1)
				DeptScopeFlag = true;
			
			if(DeptScopeFlag)
			addFieldError("resolvingDeptScope","Please Select All Required Fields in Problem Resolving Dept Area.");
		}
	}
	
	public String ajaxCallHandler()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
	if(user != null)
	{
		
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		deptScopesList = new ArrayList<SelectOptionVO>(0);
		
		if(jObj.getString("task").equalsIgnoreCase("getProblemType"))
		{
			//Here retrive the problem types
			deptScopesList = problemManagementService.getProblemsDefaultClassifications();
		}
		else if(jObj.getString("task").equalsIgnoreCase("getProblemResolvingDeptScopes"))
		{
			//Here retrive the dept scopes
			deptScopesList = problemManagementService.getDepartmentScopesForAnUser(user.getRegistrationID());
			deptScopesList.add(0,new SelectOptionVO(0l,"Select Scope"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("getDepartmentCategories"))
		{
			//here retrive the Department Categories
			Long scopeId = jObj.getLong("scopeId");
			deptScopesList = problemManagementService.getDepartmentsForADepartmentResolvingAreaScope(scopeId);
			deptScopesList.add(0,new SelectOptionVO(0l,"Select Dept Category"));
		}
		else if(jObj.getString("task").equalsIgnoreCase("getDepartments"))
		{
			//here retrive the Departments
			Long scopeId = jObj.getLong("scopeId");
			Long deptCategoryId = jObj.getLong("deptCategoryId");
						
			deptScopesList = problemManagementService.getDepartmentOrganisationsForADeptOfScope(deptCategoryId, scopeId);
			deptScopesList.add(0,new SelectOptionVO(0l,"Select Dept"));
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("getCadreProblemDetailsForSms"))
		{
			Long cadreId = jObj.getLong("cadreId");
			Long pHistoryId = jObj.getLong("pHistoryId");
			
			deptScopesList = problemManagementService.getCadreProblemDetailsForSms(user.getRegistrationID(),cadreId,pHistoryId);
		}
	}	
		return SUCCESS;
	}
	
	
	public String changeProblemStatus(){
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
	if(user != null)
	{		
		
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("changeProblemStatus"))
		{
			Long problemHistoryId = jObj.getLong("pHistoryId");
			String problemStatus  = jObj.getString("status");
			
			resultStatus = problemManagementService.updateProblemStatusData(problemHistoryId, problemStatus);
			resultStatus.setResultCode(1);
		}
		else if(jObj.getString("task").equalsIgnoreCase("sendSMS"))
		{
			String message = jObj.getString("message");
			String [] phoneNumbers = {jObj.getString("MobileNo")};
			resultStatus = problemManagementService.sendSMS(user.getRegistrationID(),message,IConstants.PROBLEM_MANAGEMENT,phoneNumbers);
		}
	}else{
		resultStatus = new ResultStatus();
		resultStatus.setResultCode(0);
	    }	
	 return Action.SUCCESS;
	}
	
    public String changeProbClassification(){
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
	if(user!=null)
			
	{	
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long problemHistoryId = jObj.getLong("pHistoryId");
		String classification = jObj.getString("typeValue");
		String typeValue  = jObj.getString("status");
		
		String status = "";
		if(typeValue.equalsIgnoreCase("Assign"))
			status = IConstants.PROBLEM_TYPE_ADD;
		else if(typeValue.equalsIgnoreCase("Change"))
			status = IConstants.PROBLEM_TYPE_MODIFY;
		
		resultStatus = problemManagementService.updateProblemClassificationData(problemHistoryId, classification, status);
		resultStatus.setResultCode(1);
	}else{
		
		resultStatus = new ResultStatus();
		resultStatus.setResultCode(0);
	}	
	 return Action.SUCCESS;
	}
    
   public String addCadreToProblem(){
		
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
	if(user!=null)
	{
		
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long problemHistoryId = jObj.getLong("pHistoryId");
		Long cadreId = jObj.getLong("cadreId");
		String typeValue  = jObj.getString("cadreClickType");
		
        String status = "";
        
        if(typeValue.equalsIgnoreCase("Assign"))
			status = IConstants.CADRE_ADD;
		else if(typeValue.equalsIgnoreCase("Change"))
			status = IConstants.CADRE_MODIFY;
		else if(typeValue.equalsIgnoreCase("Delete"))
			status = IConstants.CADRE_DELETE;
        
        if(status.equalsIgnoreCase(IConstants.CADRE_DELETE))
        	{
        	resultStatus = problemManagementService.updateAssignedCadre(problemHistoryId, 0L, status);
            resultStatus.setResultCode(1);
        	}
        else{
        	resultStatus = problemManagementService.updateAssignedCadre(problemHistoryId, cadreId, status);
        	resultStatus.setResultCode(1);
            }
	}else{
		resultStatus = new ResultStatus();
		resultStatus.setResultCode(0);
		
	     }	
	 return Action.SUCCESS;
	}
   
   public String postCommentsToProblem(){
	   
	   session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
	if(user!=null)
	{		
		
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long problemHistoryId = jObj.getLong("pHistoryId");
		String comments = jObj.getString("comments");
		
	    resultStatus = problemManagementService.updateProblemComments(problemHistoryId, comments, IConstants.PROBLEM_COMMENTS_ADD); 
	    resultStatus.setResultCode(1);
	}else{
		resultStatus = new ResultStatus();
		resultStatus.setResultCode(0);
		} 
     return Action.SUCCESS;
   }
   
   public String addDepartmentToProblem(){
	   
	   session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user==null)
			return ERROR;
		
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long problemHistoryId = jObj.getLong("pHistoryId");
		Long scopeId = jObj.getLong("deptScopeId");
		Long departmentId = jObj.getLong("deptId");
		Long regionId = jObj.getLong("regionId");
		String status = jObj.getString("status");
		
		String pbStatus = "";
		if(status.equalsIgnoreCase("Assign"))
			pbStatus = IConstants.DEPARTMENT_ADD;
		else if(status.equalsIgnoreCase("Change"))
			pbStatus = IConstants.DEPARTMENT_MODIFY;
		else if(status.equalsIgnoreCase("Delete"))
			pbStatus = IConstants.DEPARTMENT_DELETE;
		
		if(pbStatus.equals(IConstants.DEPARTMENT_DELETE))
			resultStatus = problemManagementService.updateProblemDepartment(problemHistoryId, 0L, 0L, 0L, pbStatus);
		else
			resultStatus = problemManagementService.updateProblemDepartment(problemHistoryId, departmentId, scopeId, regionId, pbStatus);
	   
	   return Action.SUCCESS;
   }
 }
