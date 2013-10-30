package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Calendar; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dao.ProblemSearchFilterOptionsVO;
import com.itgrids.partyanalyst.dto.CompleteProblemDetailsVO;
import com.itgrids.partyanalyst.dto.MyProblemsCountVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemManagementChartVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICompleteProblemDetailsSearchService;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class CompleteProblemDetailsSearchAction implements ServletRequestAware {
	private HttpServletRequest request;
	private HttpSession session;
	private final static Logger Log = Logger.getLogger(CompleteProblemDetailsSearchAction.class);
	JSONObject jObj = null;
	private String task = null;
	private ProblemManagementChartVO problemManagementChartVO;
	private IProblemManagementService problemManagementService;
	private List<SelectOptionVO> statusList;
	private IProblemManagementReportService problemManagementReportService;
	private List<SelectOptionVO> deptScopes;
	private List<SelectOptionVO> selectOptions;
	private ICompleteProblemDetailsSearchService completeProblemDetailsSearchService;
	private SelectOptionVO userLoginDetails;
	private List<CompleteProblemDetailsVO> problemDetails;
	private CompleteProblemDetailsVO completeProblemDetailsVO;
	private List<ProblemBeanVO> deptwiseprobcount;
	private MyProblemsCountVO myProblemsCountVO;
	

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public ProblemManagementChartVO getProblemManagementChartVO() {
		return problemManagementChartVO;
	}

	public void setProblemManagementChartVO(
			ProblemManagementChartVO problemManagementChartVO) {
		this.problemManagementChartVO = problemManagementChartVO;
	}

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}

	public List<SelectOptionVO> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<SelectOptionVO> statusList) {
		this.statusList = statusList;
	}

	public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}

	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}

	public List<SelectOptionVO> getDeptScopes() {
		return deptScopes;
	}

	public void setDeptScopes(List<SelectOptionVO> deptScopes) {
		this.deptScopes = deptScopes;
	}

	public List<SelectOptionVO> getSelectOptions() {
		return selectOptions;
	}

	public void setSelectOptions(List<SelectOptionVO> selectOptions) {
		this.selectOptions = selectOptions;
	}

	public ICompleteProblemDetailsSearchService getCompleteProblemDetailsSearchService() {
		return completeProblemDetailsSearchService;
	}

	public void setCompleteProblemDetailsSearchService(
			ICompleteProblemDetailsSearchService completeProblemDetailsSearchService) {
		this.completeProblemDetailsSearchService = completeProblemDetailsSearchService;
	}

	public SelectOptionVO getUserLoginDetails() {
		return userLoginDetails;
	}

	public void setUserLoginDetails(SelectOptionVO userLoginDetails) {
		this.userLoginDetails = userLoginDetails;
	}

	public List<CompleteProblemDetailsVO> getProblemDetails() {
		return problemDetails;
	}

	public void setProblemDetails(List<CompleteProblemDetailsVO> problemDetails) {
		this.problemDetails = problemDetails;
	}
	
	public CompleteProblemDetailsVO getCompleteProblemDetailsVO() {
		return completeProblemDetailsVO;
	}

	public void setCompleteProblemDetailsVO(
			CompleteProblemDetailsVO completeProblemDetailsVO) {
		this.completeProblemDetailsVO = completeProblemDetailsVO;
	}

	public List<ProblemBeanVO> getDeptwiseprobcount() {
		return deptwiseprobcount;
	}

	public void setDeptwiseprobcount(List<ProblemBeanVO> deptwiseprobcount) {
		this.deptwiseprobcount = deptwiseprobcount;
	}
	
	public MyProblemsCountVO getMyProblemsCountVO() {
		return myProblemsCountVO;
	}

	public void setMyProblemsCountVO(MyProblemsCountVO myProblemsCountVO) {
		this.myProblemsCountVO = myProblemsCountVO;
	}

	public String execute(){
		if(Log.isDebugEnabled()){
			Log.debug("Enter into execute method ");
		}
	  try{
		statusList = problemManagementReportService.getAllProblemStatusInfo();
		userLoginDetails =  getUserStatus();
		myProblemsCountVO = new MyProblemsCountVO();
		myProblemsCountVO.setUserType(userLoginDetails.getName());
		myProblemsCountVO.setUserId(userLoginDetails.getId());
		myProblemsCountVO = completeProblemDetailsSearchService.getUserRelatedProblemsCount(myProblemsCountVO);
		
	  }catch(Exception e){
		  Log.error("Exception rised in execute method ",e);
	  }
		return Action.SUCCESS;
	}
	
	public String getRequiredDetails(){
		if(Log.isDebugEnabled()){
			Log.debug("Enter into getRequiredDetails method ");
		}
		try{
			SelectOptionVO  selectOptionVO = getUserStatus();
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("getOverViewGraph")){
				if(selectOptionVO.getId() != null && selectOptionVO.getName() != null && (selectOptionVO.getName().equalsIgnoreCase(IConstants.PARTY_ANALYST_USER) || selectOptionVO.getName().equalsIgnoreCase(IConstants.BOTH))){
					problemManagementChartVO = problemManagementService.getOverallProblemsCountInDifferentLifeCycleStagesPostedByUser(selectOptionVO.getId());
				}
			}
		}catch(Exception e){
			Log.error("Exception rised in getRequiredDetails method ",e);
		}
		return Action.SUCCESS;
	}
	
	public SelectOptionVO getUserStatus(){
		
		SelectOptionVO selectOptionVO = new SelectOptionVO();
	  try{
		 RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
	     Long userId = null;
	     String userStatus = null;
		 if(regVO != null && regVO.getRegistrationID() != null){
			userId = regVO.getRegistrationID();
			if(session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE) != null && (Boolean)session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE))
				userStatus = IConstants.PARTY_ANALYST_USER;
			if(session.getAttribute(IWebConstants.FREE_USER_ROLE) != null && (Boolean)session.getAttribute(IWebConstants.FREE_USER_ROLE))
				userStatus = IConstants.FREE_USER;
			if(session.getAttribute(IWebConstants.FREE_USER_ROLE) != null && session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE) != null && (Boolean)session.getAttribute(IWebConstants.FREE_USER_ROLE) && (Boolean)session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE))
				userStatus = IConstants.BOTH;
			
		 }else{
			userStatus = IConstants.NOT_LOGGED_IN;
		 }
		 selectOptionVO.setId(userId);
		 selectOptionVO.setName(userStatus);
	    }catch(Exception e){
		  Log.error("Exception rised in getUserStatus method ",e);
	    }
		return selectOptionVO;
	 }
   public String getGraphDetails(){
	   if(Log.isDebugEnabled()){
			Log.debug("Enter into getGraphDetails method ");
		}
	   try{
	   jObj = new JSONObject(getTask());
	   SelectOptionVO  selectOptionVO = getUserStatus();
         if(jObj.getString("task").equalsIgnoreCase("status")){
        	 statusList = completeProblemDetailsSearchService.getStatusWiseProblemCount(setValuesToVO());
        	 
        	 return Action.SUCCESS;
		}
         else if(jObj.getString("task").equalsIgnoreCase("cadreanddept") && selectOptionVO.getId() != null && selectOptionVO.getName() != null && (selectOptionVO.getName().equalsIgnoreCase(IConstants.PARTY_ANALYST_USER) || selectOptionVO.getName().equalsIgnoreCase(IConstants.BOTH))){
        	 completeProblemDetailsVO = completeProblemDetailsSearchService.getCadreAndDeptWiseCounts(setValuesToVO());
        	 return "cadreanddept";
         }else if(jObj.getString("task").equalsIgnoreCase("cadre") && selectOptionVO.getId() != null && selectOptionVO.getName() != null && (selectOptionVO.getName().equalsIgnoreCase(IConstants.PARTY_ANALYST_USER) || selectOptionVO.getName().equalsIgnoreCase(IConstants.BOTH))){
 			 statusList = completeProblemDetailsSearchService.getCadreWiseProblemCount(setValuesToVO());
 			 return "cadre";
         }else if(jObj.getString("task").equalsIgnoreCase("department") && selectOptionVO.getId() != null && selectOptionVO.getName() != null && (selectOptionVO.getName().equalsIgnoreCase(IConstants.PARTY_ANALYST_USER) || selectOptionVO.getName().equalsIgnoreCase(IConstants.BOTH))){
 			deptwiseprobcount = completeProblemDetailsSearchService.getDepartmentWiseProblemCount(setValuesToVO());
 			return "department";
         }
         
	   }catch(Exception e){
		   Log.error("Exception rised in getGraphDetails method ",e);
	   }
	   return Action.SUCCESS;
   }
   public String getDetailsForProblems(){
	   if(Log.isDebugEnabled()){
			Log.debug("Enter into getDetailsForProblems method ");
		}
	   try{
		   jObj = new JSONObject(getTask());
		   if(jObj.getString("task").trim().equalsIgnoreCase("getProblemsContainStates")){
			   SelectOptionVO  selectOptionVO = getUserStatus();
			   selectOptions = completeProblemDetailsSearchService.getProblemsContainStates(selectOptionVO.getId(),setValuesToVO());
		   }
		   else if(jObj.getString("task").trim().equalsIgnoreCase("getUserDetailsForProblems")){
		   SelectOptionVO  selectOptionVO = getUserStatus();
		       selectOptions = completeProblemDetailsSearchService.getProblemPostedUserDetails(selectOptionVO.getId());
		   }else if(jObj.getString("task").trim().equalsIgnoreCase("getStateDetailsForDept")){
			   selectOptions = completeProblemDetailsSearchService.getStatesForDepartments();
		   }else if(jObj.getString("task").trim().equalsIgnoreCase("getDepartmentDetails")){
			   selectOptions = completeProblemDetailsSearchService.getDepartmentsByStateId(jObj.getLong("stateId"));
		   }
		 }catch(Exception e){
		   Log.error("Exception rised in getDetailsForProblems method ",e);
	   }
	   return Action.SUCCESS;
   }
   
   public String getLocationDetails(){
	   if(Log.isDebugEnabled()){
			Log.debug("Enter into getLocationDetails method ");
		}
	   try{
		   jObj = new JSONObject(getTask());
		   if(jObj.getString("task").trim().equalsIgnoreCase("getimpactregionlevels")){
			   SelectOptionVO  selectOptionVO = getUserStatus();
			   selectOptions =  completeProblemDetailsSearchService.getProblemImpactRegions(jObj.getLong("stateId"),selectOptionVO.getName(),setValuesToVO());
		   }else if(jObj.getString("task").trim().equalsIgnoreCase("getdistricts")){
			   selectOptions =  completeProblemDetailsSearchService.getAllProblemContainDistricts(jObj.getLong("stateId"),setValuesToVO());
		   }else if(jObj.getString("task").trim().equalsIgnoreCase("getconstituencies")){
			   selectOptions =  completeProblemDetailsSearchService.getAllProblemContainConstituencies(jObj.getLong("districtId"),setValuesToVO()); 
		   }else if(jObj.getString("task").trim().equalsIgnoreCase("getmandals")){
			   selectOptions =  completeProblemDetailsSearchService.getAllProblemContainMandals(jObj.getLong("constituencyId"),setValuesToVO());
		   }else if(jObj.getString("task").trim().equalsIgnoreCase("getvillages")){
			   selectOptions =  completeProblemDetailsSearchService.getAllProblemContainVillages(jObj.getLong("mandalId"),setValuesToVO());
		   }else if(jObj.getString("task").trim().equalsIgnoreCase("getbooths")){
			   selectOptions =  completeProblemDetailsSearchService.getAllProblemContainBooths(jObj.getLong("mandalId"),setValuesToVO());
		   }else if(jObj.getString("task").trim().equalsIgnoreCase("getmuncpcorpgmc")){
			   selectOptions =  completeProblemDetailsSearchService.getAllProblemContainMuncpCorpGmc(jObj.getLong("constituencyId"),setValuesToVO());
		   }else if(jObj.getString("task").trim().equalsIgnoreCase("getwards")){
			   selectOptions =  completeProblemDetailsSearchService.getAllProblemContainWards(jObj.getLong("localElection"),setValuesToVO());
		   }
		 }catch(Exception e){
		   Log.error("Exception rised in getLocationDetails method ",e);
	   }
	   return Action.SUCCESS;
   }
   
   public String getProblemsByFilterInputs(){
	   if(Log.isDebugEnabled()){
			Log.debug("Enter into getProblemsByFilterInputs method ");
		}
	   try{		   
		   problemDetails = completeProblemDetailsSearchService.getProblemsByFilterOptions(setValuesToVO());
		   
		 }catch(Exception e){
		   Log.error("Exception rised in getProblemsByFilterInputs method ",e);
	   }
	   return Action.SUCCESS;
   }
   
   private ProblemSearchFilterOptionsVO setValuesToVO(){
	 ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO = new ProblemSearchFilterOptionsVO(); 
	 try{
	   jObj = new JSONObject(getTask());
	   SelectOptionVO  selectOptionVO = getUserStatus();
	   
	   if(jObj.getString("locationId").trim().length() > 0)
		   problemSearchFilterOptionsVO.setLocationId(jObj.getLong("locationId"));
	   if(jObj.getString("locationValue").trim().length() > 0)
		   problemSearchFilterOptionsVO.setLocationValue(jObj.getLong("locationValue"));
	   if(jObj.getString("statusId").trim().length() > 0)
		   problemSearchFilterOptionsVO.setStatusId(jObj.getLong("statusId"));
	   if(jObj.getString("problemTypeId").trim().length() > 0)
		   problemSearchFilterOptionsVO.setProblemTypeId(jObj.getLong("problemTypeId"));
	   if(jObj.getString("departmentId").trim().length() > 0)
		   problemSearchFilterOptionsVO.setDepartmentId(jObj.getLong("departmentId"));
	   if(jObj.getString("selectedUserId").trim().length() > 0)
		   problemSearchFilterOptionsVO.setSelectedUserid(jObj.getLong("selectedUserId"));
	   if(jObj.getString("cadre").trim().equalsIgnoreCase("true"))
		   problemSearchFilterOptionsVO.setCadreReq(true);
	   if(jObj.getString("onlyUserProb").trim().equalsIgnoreCase("true"))
		   problemSearchFilterOptionsVO.setOnlyUserProb(true);
	   
	   problemSearchFilterOptionsVO.setUserId(selectOptionVO.getId());
	   
	   setReqFetchRecordsSizeToVO(problemSearchFilterOptionsVO);
	   
	   setDateConditionsToVO(problemSearchFilterOptionsVO);
	   
	   setInitialConditionsToVO(problemSearchFilterOptionsVO);
	   
	 }catch(Exception e){
		   Log.error("Exception rised in setValuesToVO method ",e);
	  }
	   return problemSearchFilterOptionsVO;
   }
   
   private void setReqFetchRecordsSizeToVO(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
	   if(jObj.getString("firstResult").trim().length() > 0)
		  problemSearchFilterOptionsVO.setFirstResult(jObj.getInt("firstResult"));
	   if(jObj.getString("maxResult").trim().length() > 0)
		  problemSearchFilterOptionsVO.setMaxResult(jObj.getInt("maxResult"));
   }
   
   private void setDateConditionsToVO(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
	try{
	   if(jObj.getString("fromDate").trim().length() > 0){
		   String[] fromdatearry = jObj.getString("fromDate").trim().split("/");
		   Calendar calendar = Calendar.getInstance();
		   calendar.set(Integer.parseInt(fromdatearry[2]), Integer.parseInt(fromdatearry[0])-1, Integer.parseInt(fromdatearry[1]));
		   problemSearchFilterOptionsVO.setFromDate( calendar.getTime());
	   }
	   if(jObj.getString("toDate").trim().length() > 0){
		   String[] toDatearry = jObj.getString("toDate").trim().split("/");
		   Calendar calendar = Calendar.getInstance();
		   calendar.set(Integer.parseInt(toDatearry[2]), Integer.parseInt(toDatearry[0])-1, Integer.parseInt(toDatearry[1]));
		   problemSearchFilterOptionsVO.setToDate(calendar.getTime());
	   }
	}catch(Exception e){
		Log.error("Exception rised in setDateConditions method ",e);
	}
   }
   
   private void setInitialConditionsToVO(ProblemSearchFilterOptionsVO problemSearchFilterOptionsVO){
	   
	   if(jObj.getString("myPrivateProb").trim().equalsIgnoreCase("true"))
		   problemSearchFilterOptionsVO.setMyPrivateProb(true);
	   if(jObj.getString("myPublicProb").trim().equalsIgnoreCase("true"))
		   problemSearchFilterOptionsVO.setMyPublicProb(true);
	   if(jObj.getString("takenByMe").trim().equalsIgnoreCase("true"))
		   problemSearchFilterOptionsVO.setTakenUpProb(true);
	   if(jObj.getString("commntByMe").trim().equalsIgnoreCase("true"))
		   problemSearchFilterOptionsVO.setCommentByMeProb(true);
	   if(jObj.getString("allPublicProb").trim().equalsIgnoreCase("true"))
		   problemSearchFilterOptionsVO.setAllPublicProb(true);
	   if(jObj.getString("postedByMe").trim().equalsIgnoreCase("true"))
		   problemSearchFilterOptionsVO.setPostedByMeProb(true);
	   if(jObj.getString("impactLvl").trim().length() > 0)
	   problemSearchFilterOptionsVO.setImpactLevel(jObj.getLong("impactLvl"));
   }
   
}
