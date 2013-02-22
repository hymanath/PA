package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dao.IProblemDAO;
import com.itgrids.partyanalyst.dao.hibernate.ProblemDAO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Problem;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ProblemAssigningAction extends ActionSupport implements ServletRequestAware,ServletContextAware  {

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
	private List<File> userImage = new ArrayList<File>();
	private List<String> userImageContentType = new ArrayList<String>();
	private List<String> userImageFileName = new ArrayList<String>();
	private List<String> problemFilepath;
	private List<String> contentType=new ArrayList<String>();
	private List<String> fileTitle;
	private List<String> fileDescription;
    private HttpServletRequest servletRequest;
    private ServletContext context;
    private List<String>tempFileName;
    ProblemBeanVO problemBeanVO = new ProblemBeanVO();
    FileVO fileVO=new FileVO();
    private Long problemHistoryId;
    private InputStream inputStream;
    private String uploadResult;
    private IProblemDAO problemDAO;
    private Boolean hasFreeUserRole;
	private Boolean hasPartyAnalystUserRole;
    
	
	public String getUploadResult() {
		return uploadResult;
	}

	public void setUploadResult(String uploadResult) {
		this.uploadResult = uploadResult;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public Long getProblemHistoryId() {
		return problemHistoryId;
	}

	public void setProblemHistoryId(Long problemHistoryId) {
		this.problemHistoryId = problemHistoryId;
	}
	
	public List<File> getUserImage() {
		return userImage;
	}

	public void setUserImage(List<File> userImage) {
		this.userImage = userImage;
	}

	public List<String> getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(List<String> userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public List<String> getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(List<String> userImageFileName) {
		this.userImageFileName = userImageFileName;
	}

	public List<String> getProblemFilepath() {
		return problemFilepath;
	}

	public void setProblemFilepath(List<String> problemFilepath) {
		this.problemFilepath = problemFilepath;
	}

	public List<String> getContentType() {
		return contentType;
	}

	public void setContentType(List<String> contentType) {
		this.contentType = contentType;
	}

	public List<String> getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(List<String> fileTitle) {
		this.fileTitle = fileTitle;
	}

	public List<String> getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(List<String> fileDescription) {
		this.fileDescription = fileDescription;
	}

	public ServletContext getContext() {
		return context;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	public List<String> getTempFileName() {
		return tempFileName;
	}

	public void setTempFileName(List<String> tempFileName) {
		this.tempFileName = tempFileName;
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

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

	public IProblemDAO getProblemDAO() {
		return problemDAO;
	}

	public void setProblemDAO(IProblemDAO problemDAO) {
		this.problemDAO = problemDAO;
	}
	
	public Boolean getHasFreeUserRole() {
		return hasFreeUserRole;
	}

	public void setHasFreeUserRole(Boolean hasFreeUserRole) {
		this.hasFreeUserRole = hasFreeUserRole;
	}

	public Boolean getHasPartyAnalystUserRole() {
		return hasPartyAnalystUserRole;
	}

	public void setHasPartyAnalystUserRole(Boolean hasPartyAnalystUserRole) {
		this.hasPartyAnalystUserRole = hasPartyAnalystUserRole;
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
		
		if(user != null){		
			try {
				jObj = new JSONObject(getTask());
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
			
			if(jObj.getString("task").equalsIgnoreCase("changeProblemStatus")){
				Long problemHistoryId = jObj.getLong("pHistoryId");
				this.problemHistoryId = problemHistoryId;
				Long userId=user.getRegistrationID();
				String problemStatus  = jObj.getString("status");
				
				//resultStatus = problemManagementService.updateProblemStatusData(problemHistoryId, problemStatus);
				resultStatus = problemManagementService.updateStatusOfProblem(problemHistoryId, userId, problemStatus);
				resultStatus.setResultCode(1);
			}
			else if(jObj.getString("task").equalsIgnoreCase("sendSMS"))	{
				String message = jObj.getString("message");
				String [] phoneNumbers = {jObj.getString("MobileNo")};
				resultStatus = problemManagementService.sendSMS(user.getRegistrationID(),message,IConstants.PROBLEM_MANAGEMENT,phoneNumbers);
			}
		}
		else{
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
		//Long problemId=jObj.getLong("problemId");
		Long userId=user.getRegistrationID();
		String classification = jObj.getString("typeValue");
		String typeValue  = jObj.getString("status");
		
		String status = "";
		if(typeValue.equalsIgnoreCase("Assign"))
			status = IConstants.PROBLEM_TYPE_ADD;
		else if(typeValue.equalsIgnoreCase("Change"))
			status = IConstants.PROBLEM_TYPE_MODIFY;
	
		//resultStatus = problemManagementService.updateProblemClassificationData(problemHistoryId, classification, status);
		resultStatus = problemManagementService.updateClassificationOfProblem(problemHistoryId, userId, classification, status);
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
        	resultStatus = problemManagementService.updateAssignedCadre(problemHistoryId, 0L, status,user.getRegistrationID());
            resultStatus.setResultCode(1);
        	}
        else{
        	resultStatus = problemManagementService.updateAssignedCadre(problemHistoryId, cadreId, status,user.getRegistrationID());
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
		//String titleValue = jObj.getString("titleValue");
		//String descriptionValue = jObj.getString("descriptionValue");
		//String fileValue = jObj.getString("fileValue");
		
		hasFreeUserRole = (Boolean)session.getAttribute("hasFreeUserRole");
		hasPartyAnalystUserRole = (Boolean)session.getAttribute("hasPartyAnalystUserRole");
		ProblemBeanVO problemBeanVO = new ProblemBeanVO();
		problemBeanVO.setHasFreeUserRole(hasFreeUserRole);
		problemBeanVO.setHasPartyAnalystUserRole(hasPartyAnalystUserRole);
		problemBeanVO.setProblemId(jObj.getLong("pHistoryId"));
		problemBeanVO.setDescription(jObj.getString("comments"));
		problemBeanVO.setStatus(IConstants.PROBLEM_COMMENTS_ADD);
		problemBeanVO.setUserID(user.getRegistrationID());
		
	   // resultStatus = problemManagementService.updateProblemComments(problemHistoryId, comments, IConstants.PROBLEM_COMMENTS_ADD);
	    
	    resultStatus = problemManagementService.saveProblemRelatedComments(problemBeanVO);
	    
	    resultStatus.setResultCode(1);
	}else{
		resultStatus = new ResultStatus();
		resultStatus.setResultCode(0);
		} 
     return Action.SUCCESS;
   }
   
   	public String postImagesAndFiles()
   	{
	   
   		session = request.getSession();
   		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		hasFreeUserRole = (Boolean)session.getAttribute("hasFreeUserRole");
		hasPartyAnalystUserRole = (Boolean)session.getAttribute("hasPartyAnalystUserRole");
		
   		if(user!=null)
   		{		
			if(user.getUserStatus().equals(IConstants.PARTY_ANALYST_USER))
				problemBeanVO.setProblemPostedBy(IConstants.PARTY_ANALYST_USER);
			
			problemBeanVO.setProblemHistoryId(getProblemHistoryId());
			fileVO.setFileName(getUserImageFileName());
			fileVO.setFileTitle(getFileTitle());
			fileVO.setFileDescription(getFileDescription());
			
			try {
				
				String fileName = null;
				String filePath = null;
				problemFilepath = new ArrayList<String>();
				tempFileName = new ArrayList<String>();
				String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
				
				if(request.getRequestURL().toString().contains(IConstants.PARTYANALYST_SITE))
					filePath = IWebConstants.STATIC_CONTENT_FOLDER_URL + IConstants.UPLOADED_FILES + pathSeperator + "Problem_Files" + pathSeperator;
				else
					filePath = context.getRealPath("/")+IConstants.UPLOADED_FILES + pathSeperator + IWebConstants.PROBLEM_FILES + pathSeperator;
				
				for (int i = 0; i < userImage.size(); i++)
				{
					Long systime = System.currentTimeMillis();
					StringTokenizer st = new StringTokenizer(userImageContentType.get(i), "/");
					
					while(st.hasMoreTokens())
					{
						String key = st.nextToken();
						String val = st.nextToken();
						Random random = new Random();
						
						if(userImageContentType.get(i).equalsIgnoreCase("text/plain"))
							fileName = systime.toString()+random.nextInt(10000000)+"."+key;
						else
							fileName = systime.toString()+random.nextInt(10000000)+"."+val;
						
						tempFileName.add(fileName);
						
						String problemFilePath = IWebConstants.UPLOADED_FILES+"/"+IWebConstants.PROBLEM_FILES+"/"+fileName;
						problemFilepath.add(problemFilePath);
						
						File fileToCreate = new File(filePath, fileName);
						FileUtils.copyFile(userImage.get(i), fileToCreate);
						contentType.add(val);
					}
				}
				fileVO.setFileContentType(getContentType());
				fileVO.setFileName(tempFileName);
				
			} catch (Exception e) {
				e.printStackTrace();
				addActionError(e.getMessage());
			}
			
			fileVO.setFilePath(problemFilepath);
			problemBeanVO.setFileVO(fileVO);
			problemBeanVO.setProblemStatus("PROBLEM_FILE_ADD");
			problemBeanVO.setHasFreeUserRole(hasFreeUserRole);
			problemBeanVO.setHasPartyAnalystUserRole(hasPartyAnalystUserRole);
			if(user.getParentUserId() == null || user.getParentUserId() == 0)
			{
				problemBeanVO.setUserID(user.getRegistrationID());
				problemBeanVO.setSubUserId(user.getRegistrationID());
			}
			else
			{
				problemBeanVO.setUserID(user.getMainAccountId());
				problemBeanVO.setSubUserId(user.getRegistrationID());
			}
			
			//problemManagementService.saveProblemRelatedFiles(problemBeanVO,null);
			problemManagementService.addProblemRelatedFiles(problemBeanVO);
			 
			return "redirectToJSP";
		
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
			resultStatus = problemManagementService.updateProblemDepartment(problemHistoryId, 0L, 0L, 0L, pbStatus,user.getRegistrationID());
		else
			resultStatus = problemManagementService.updateProblemDepartment(problemHistoryId, departmentId, scopeId, regionId, pbStatus,user.getRegistrationID());
	   
	   return Action.SUCCESS;
   }
   
   public String deleteProblemFile()
   {
	 
	 		try {
	 			jObj = new JSONObject(getTask());
	 		} catch (ParseException e) {
	 			e.printStackTrace();
	 		}
	 		resultStatus = problemManagementService.deleteProblemFile(jObj.getLong("problemFileId"));
			return Action.SUCCESS;
   }

   public String saveAbusedCommentsToProblem()
   {
	   session = request.getSession();
	   RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
	   if(user == null)
		   return ERROR;
	   try
	   {
		 jObj = new JSONObject(getTask());
	   }catch (Exception e) {
		e.printStackTrace();
	}
	   
	   resultStatus = problemManagementService.saveAbusedCommentsToProblem(jObj.getLong("commentId"),user.getRegistrationID());
	   return Action.SUCCESS;
   }

 }
