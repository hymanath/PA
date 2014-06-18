package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IProblemManagementService;

import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

public class AddNewProblemSubmitAction extends ActionSupport implements ServletRequestAware,ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AddNewProblemSubmitAction.class);
	private HttpServletRequest request;
	private IProblemManagementService problemManagementService;
	private HttpSession session;
	private ProblemBeanVO problemBeanFromDB;
	private Boolean isSuccessfullyInserted;
	private ProblemBeanVO problemBeanVO = new ProblemBeanVO();
	transient final private FileVO fileVO=new FileVO();
	
	//form inputs
	private String problem;
	private String description;
	private String state;	
	private String district;
	private String parliamentConstituency;
	private String constituency;
	private String mandal;
	private String village;
	private String booth;
	private String reportedDate;
	private String existingFrom;
	private String probSource;
	private String name;
	private String mobile;
	private String phone;
	private String email;
	private String address;
	private Long status;
	private Long problemScope;
	private Long problemLocationId;
	private String defaultScopeId;
	private String defaultStateId;
	private String defaultDistId;
	private String defaultConstId;
	public Boolean isParliament;
	private Long pConstituencyId;
	private Long cadreId;
	private String callTracking;
	private Long problemTypeId;
	private List<File> userImage = new ArrayList<File>();
	private List<String> userImageContentType = new ArrayList<String>();
	private List<String> userImageFileName = new ArrayList<String>();
	private List<String> problemFilePathList;
	private List<String> contentType=new ArrayList<String>();
	private List<String> fileTitle;
	private List<String> fileDescription;
    private HttpServletRequest servletRequest;
    private ServletContext context;
    private List<String> fileNameList;
    private String problemVisibility;
	private Boolean hasFreeUserRole;
	private Boolean hasPartyAnalystUserRole;
	private Long problemSourceScopeId;
	private Long problemScopeId;
	private IProblemManagementReportService problemManagementReportService;
    
	public Boolean getHasFreeUserRole() {
		return hasFreeUserRole;
	}

	public void setHasFreeUserRole(final Boolean hasFreeUserRole) {
		this.hasFreeUserRole = hasFreeUserRole;
	}

	public Boolean getHasPartyAnalystUserRole() {
		return hasPartyAnalystUserRole;
	}

	public void setHasPartyAnalystUserRole(final Boolean hasPartyAnalystUserRole) {
		this.hasPartyAnalystUserRole = hasPartyAnalystUserRole;
	}

	public List<String> getFileNameList() {
		return fileNameList;
	}

	public void setFileNameList(final List<String> fileNameList) {
		this.fileNameList = fileNameList;
	}

	public void setServletContext(final ServletContext context) {
		this.context = context;
	}

	public ServletContext getContext() {
		return context;
	}
	
	public String getWindowTask() {
		return problemBeanVO.getWindowTask();
	}

	public void setWindowTask(final String windowTask) {
		problemBeanVO.setWindowTask(windowTask);
	}

	public Long getProblemId() {
		return problemBeanVO.getProblemId();
	}

	public void setProblemId(final Long announcementId) {
		problemBeanVO.setProblemId(announcementId);
	}

	public List<File> getUserImage() {
		return userImage;
	}

	public void setUserImage(final List<File> userImage) {
		this.userImage = userImage;
	}

	public List<String> getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(final List<String> userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public List<String> getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(final List<String> userImageFileName) {
		this.userImageFileName = userImageFileName;
	}

	public List<String> getContentType() {
		return contentType;
	}

	public List<String> getProblemFilePathList() {
		return problemFilePathList;
	}

	public void setProblemFilePathList(final List<String> problemFilePathList) {
		this.problemFilePathList = problemFilePathList;
	}

	public void setContentType(final List<String> contentType) {
		this.contentType = contentType;
	}

	public List<String> getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(final List<String> fileTitle) {
		this.fileTitle = fileTitle;
	}

	public List<String> getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(final List<String> fileDescription) {
		this.fileDescription = fileDescription;
	}

	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;		
	}	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(final HttpServletRequest request) {
		this.request = request;
	}
	
	public ProblemBeanVO getProblemBeanVO() {
		return problemBeanVO;
	}

	public void setProblemBeanVO(final ProblemBeanVO problemBeanVO) {
		this.problemBeanVO = problemBeanVO;
	}

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(final 
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(final HttpSession session) {
		this.session = session;
	}
	
	public ProblemBeanVO getProblemBeanFromDB() {
		return problemBeanFromDB;
	}

	public void setProblemBeanFromDB(final ProblemBeanVO problemBeanFromDB) {
		this.problemBeanFromDB = problemBeanFromDB;
	}
	
	public Boolean getIsSuccessfullyInserted() {
		return isSuccessfullyInserted;
	}

	public void setIsSuccessfullyInserted(final Boolean isSuccessfullyInserted) {
		this.isSuccessfullyInserted = isSuccessfullyInserted;
	}

	public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}

	public void setProblemManagementReportService(final 
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}


	//form input setters and getters
	
	public String getProblemVisibility() {
		return problemVisibility;
	}

	public void setProblemVisibility(final String problemVisibility) {
		this.problemVisibility = problemVisibility;
	}
	
	public String getProblem() {
		return problemBeanVO.getProblem();
	}
	
	public void setProblem(final String problem) {
		this.problemBeanVO.setProblem(problem);
	}

	public String getDescription() {
		return problemBeanVO.getDescription();
	}
	
	@StringLengthFieldValidator(type = ValidatorType.FIELD, message = "Description Should be below 500 characters ", maxLength = "500")	
	public void setDescription(final String description) {
		this.problemBeanVO.setDescription(description);
	}

	public String getState() {
		return problemBeanVO.getState();
	}
	
	public void setState(final String state) {
		this.problemBeanVO.setState(state);
	}

	public String getDistrict() {
		return problemBeanVO.getDistrict();
	}	
	
	public void setDistrict(final String district) {
		this.problemBeanVO.setDistrict(district);
	}

	public String getParliamentConstituency() {
		return parliamentConstituency;
	}
	
	public void setParliamentConstituency(final String parliamentConstituency) {
		this.parliamentConstituency = parliamentConstituency;
	}

	public String getConstituency() {
		return problemBeanVO.getConstituency();
	}	
	
	public void setConstituency(final String constituency) {
		this.problemBeanVO.setConstituency(constituency);
	}

	public String getMandal() {
		return problemBeanVO.getTehsil();
	}	
	
	public void setMandal(final String mandal) {
		this.problemBeanVO.setTehsil(mandal);
	}

	public String getVillage() {
		return problemBeanVO.getVillage();
	}	
	
	public void setVillage(final String village) {
		this.problemBeanVO.setVillage(village);
	}
	
	public String getBooth() {
		return problemBeanVO.getBooth();
	}

	public void setBooth(final String booth) {
		this.problemBeanVO.setBooth(booth);
	}

	public String getReportedDate() {
		return problemBeanVO.getReportedDate();
	}

	public void setReportedDate(final String reportedDate) {
		this.problemBeanVO.setReportedDate(reportedDate);
	}
	
	public String getExistingFrom() {
		return problemBeanVO.getExistingFrom();
	}

	public void setExistingFrom(final String existingFromDate) {
		this.problemBeanVO.setExistingFrom(existingFromDate);
	}

	public String getProblemSourceScopeId() {
		return probSource;
	}
	
	public void setProblemSourceScopeId(final Long problemSourceScopeId) {
		this.problemSourceScopeId = problemSourceScopeId;
		this.problemBeanVO.setProbSourceId(problemSourceScopeId);
	}
	

	public String getName() {
		return name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	public String getMobile() {
		return mobile;
	}
	

	public void setMobile(final String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(final String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}	
	
	public Long getStatus() {
		return problemBeanVO.getProblemStatusId();
	}

	public void setStatus(final Long status) {
		this.problemBeanVO.setProblemStatusId(status);
	}	

	
	//@RequiredStringValidator(type = ValidatorType.FIELD, message = "Problem Scope field is mandatory",shortCircuit=true)
	public Long getProblemScope() {
		return problemBeanVO.getProblemImpactLevelId();
	}

	public void setProblemScope(final Long problemScopeId) {
		this.problemBeanVO.setProblemImpactLevelId(problemScopeId);
	}
	
	public void setProblemTypeId(final Long problemTypeId) {
		this.problemBeanVO.setProblemTypeId(problemTypeId);
	}

	public Long getProblemTypeId() {
		return problemBeanVO.getProblemTypeId();
	}

	public String getDefaultStateId() {
		return defaultStateId;
	}

	public void setDefaultStateId(final String defaultStateId) {
		this.defaultStateId = defaultStateId;
	}

	public String getDefaultDistId() {
		return defaultDistId;
	}

	public void setDefaultDistId(final String defaultDistId) {
		this.defaultDistId = defaultDistId;
	}

	public String getDefaultConstId() {
		return defaultConstId;
	}

	public void setDefaultConstId(final String defaultConstId) {
		this.defaultConstId = defaultConstId;
	}

	public String getDefaultState() {
		if(this.problemBeanVO.getState() != null && !this.problemBeanVO.getState().isEmpty()){
			return this.problemBeanVO.getState();
		}
		return getDefaultStateId();
	}
	
	public String getDefaultDistrict() {
		if(this.problemBeanVO.getDistrict() != null && !this.problemBeanVO.getDistrict().isEmpty()){
			return this.problemBeanVO.getDistrict();		
		}
		return getDefaultDistId();
	}	

	public String getDefaultConstituency() {
		if(this.problemBeanVO.getConstituency() != null && !this.problemBeanVO.getConstituency().isEmpty()){
			return this.problemBeanVO.getConstituency();
		}
		return getDefaultConstId();
	}

	public Long getProblemLocationId() {
		return problemBeanVO.getProblemImpactLevelValue();
	}
	
	@RegexFieldValidator(type = ValidatorType.FIELD, expression = "^[1-9]+[0-9]*$", message = "Select valid Problem Location")	
	public void setProblemLocationId(final Long problemLocationId) {
		this.problemBeanVO.setProblemImpactLevelValue(problemLocationId);
	}	

	public String getDefaultScopeId() {
		return defaultScopeId;
	}

	public void setDefaultScopeId(final String defaultScopeId) {
		this.defaultScopeId = defaultScopeId;
	}
	
	public String getDefaultScope()
	{
		if(this.problemBeanVO.getProblemImpactLevelId() != null){
			return problemBeanVO.getProblemImpactLevelId().toString();
		}
		return this.defaultScopeId;
	}	

	public Boolean getIsParliament() {
		return isParliament;
	}

	public void setIsParliament(final Boolean isParliament) {
		this.isParliament = isParliament;
	}	

	public Long getPConstituencyId() {
		return pConstituencyId;
	}

	public void setPConstituencyId(final Long constituencyId) {
		pConstituencyId = constituencyId;
	}
	
	public Long getCadreId() {
		return 	this.problemBeanVO.getCadreId();
	}

	public void setCadreId(final Long cadreId) {
		this.problemBeanVO.setCadreId(cadreId);
	}

	public Long getDefaultPConstituency()
	{
		return pConstituencyId; 
	}
	
	public String getCallTracking() {
		return callTracking;
	}

	public void setCallTracking(final String callTracking) {
		this.callTracking = callTracking;
	}
	
	public String execute()
	{
		session = request.getSession();
		final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		hasFreeUserRole = (Boolean)session.getAttribute("hasFreeUserRole"); 
		hasPartyAnalystUserRole = (Boolean)session.getAttribute("hasPartyAnalystUserRole");
		
		
		if(user==null){
			return ERROR;
		}
		problemBeanVO.setHasFreeUserRole(hasFreeUserRole);
		problemBeanVO.setHasPartyAnalystUserRole(hasPartyAnalystUserRole);
		
	
		problemBeanVO.setUserID(user.getRegistrationID());
		problemBeanVO.setSubUserId(user.getRegistrationID());
		
		
		if(user.getUserStatus().equals(IConstants.PARTY_ANALYST_USER)){
			problemBeanVO.setProblemPostedBy(IConstants.PARTY_ANALYST_USER);
		}else
		{
			problemBeanVO.setProblemPostedBy(IConstants.FREE_USER);
			problemBeanVO.setProbSourceId(0L);
		}
		problemBeanVO.setIsParliament(isParliament);
				
		if(problemBeanVO.getProbSourceId() == 2L || problemBeanVO.getProbSourceId() == 3L)
		{
			problemBeanVO.setName(getName());
			problemBeanVO.setMobile(getMobile());
			problemBeanVO.setPhone(getPhone());
			problemBeanVO.setEmail(getEmail());			
			problemBeanVO.setAddress(getAddress());
		}
		fileVO.setFileName(getUserImageFileName());
		fileVO.setFileTitle(getFileTitle());
		fileVO.setFileDescription(getFileDescription());
		
		try {
			copyFiles();
		} catch (Exception e) {
			addActionError(e.getMessage());
			LOG.error("Exception rised in execute ",e);
		}
		fileVO.setFilePath(problemFilePathList);
		problemBeanVO.setFileVO(fileVO);
		problemBeanVO.setYear(IConstants.PRESENT_YEAR);
		problemBeanVO.setDescription(problemBeanVO.getDescription().replace("\r\n"," "));	
		problemBeanVO.setProblemVisibility(getProblemVisibility());
		
		
		if(problemBeanVO.getWindowTask().equalsIgnoreCase(IConstants.UPDATE_EXISTING) 
				&& (problemBeanVO.getProblemId() == null || problemBeanVO.getProblemId().equals(1L)))
		{
			problemBeanVO.setWindowTask(IConstants.NEW);
			session.setAttribute(ISessionConstants.WINDOW_TASK,IConstants.NEW);
		}
		problemBeanFromDB =  problemManagementService.saveNewProblemData(problemBeanVO);
		
		 if(problemBeanFromDB.getExceptionEncountered() == null)
		 {
			 isSuccessfullyInserted = true;
			 problemManagementService.sendSuccessMsgToMobile(problemBeanFromDB.getUserProblemId());
			 
			 if(user.getUserType() == IConstants.FREE_USER)
			 {
				 //problemManagementService.sendEmailToFreeUserAfterProblemAdded(problemBeanFromDB.getUserProblemId());
				 problemManagementService.sendEmailToFreeUserAfterProblemAdded(problemBeanFromDB);
				 problemManagementReportService.sendEmailToConnectedUsersAfterProblemAdded(problemBeanFromDB);
			 }
			 problemBeanVO = new ProblemBeanVO();
			 
		 } else{ 
			 isSuccessfullyInserted = false;
		 }
		 request.setAttribute("callTracking", getCallTracking());
		return SUCCESS;
	}
	
	private void copyFiles() throws Exception{

		String fileName;
		
		final String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
		
		final String filePath = IWebConstants.STATIC_CONTENT_FOLDER_URL + IConstants.UPLOADED_FILES +pathSeperator+"Problem_Files"+pathSeperator;
		
		problemFilePathList = new ArrayList<String>();
		
		fileNameList = new ArrayList<String>();
		final Random random = new Random();
		for (int i = 0; i < userImage.size(); i++) 
		{
			
			final int randomNumber = random.nextInt(10000000);
			final Long systime = System.currentTimeMillis();
			
			final StringTokenizer tokens = new StringTokenizer(userImageContentType.get(i), "/");
			
			while(tokens.hasMoreTokens()) 
			{
			final String key = tokens.nextToken();
			final String val = tokens.nextToken();
			
			if(userImageContentType.get(i).equalsIgnoreCase("text/plain")){
				fileName = systime.toString()+randomNumber+"."+key;
			}else{
			  fileName = systime.toString()+randomNumber+"."+val;
			}
			fileNameList.add(fileName);
			problemFilePathList.add(IWebConstants.UPLOADED_FILES+"/Problem_Files/"+fileName);
			
			final File fileToCreate = new File(filePath, fileName);
			FileUtils.copyFile(userImage.get(i), fileToCreate);
			contentType.add(val);
			}
		}
		
		fileVO.setFileContentType(getContentType());
		fileVO.setFileName(fileNameList);
		
	
	}
	public void validate()
	{
		session = request.getSession();
		final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user.getUserStatus().equals(IConstants.PARTY_ANALYST_USER))
		{
			if(problemBeanVO.getProbSourceId() == 0){
				addFieldError("sourceInput","Please Select Problem Source.");	
			}else if(problemBeanVO.getProbSourceId() == 2 || problemBeanVO.getProbSourceId() == 3){
				validateDetails();
			}else if(problemBeanVO.getProbSourceId() == 4 && (problemBeanVO.getCadreId() == null ||
					problemBeanVO.getCadreId() == 0l))
			{
				addFieldError("cadreInput","Please Select Cadre From cadre Search.");
			}
		}
		
	 }
	
	private void validateDetails(){
		if(getName() == null || getName().trim().length() == 0){
			addFieldError("nameInput","Please Enter Name in Complained Person Details.");
		}
		if(getMobile().equalsIgnoreCase("")){
			addFieldError("mobileInput","Please Enter Mobile Number.");
		}
		if(getAddress() == null || getAddress().trim().length() == 0){
			addFieldError("addressInput","Please Enter Address.");
		}
		if(getEmail() != null && getEmail().trim().length() > 0)
		{
			if(getEmail().contains("@") && getEmail().contains(".") )
			{													
				final int atIndec = getEmail().lastIndexOf("@");
				final int dotIndec = getEmail().lastIndexOf(".");
				 if(dotIndec<atIndec || (atIndec+1)== dotIndec ){
					addFieldError("emailInput","Please Enter Valid Email in Complained Person Details.");
				 }
			}
			else
			{
				 addFieldError("emailInput","Please Enter Valid Email in Complained Person Details.");
			}
		}
	}
}
