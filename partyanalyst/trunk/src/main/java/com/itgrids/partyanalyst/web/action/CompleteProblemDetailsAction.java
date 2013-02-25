package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CompleteProblemDetailsVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserCommentsInfoVO;
import com.itgrids.partyanalyst.service.ICompleteProblemDetailsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;

 public class CompleteProblemDetailsAction implements ServletRequestAware{
   private final static Logger log = Logger.getLogger(CompleteProblemDetailsAction.class);
   private Long problemId;
   private HttpServletRequest request;
   private HttpSession session;
   private ICompleteProblemDetailsService completeProblemDetailsService;
   private CompleteProblemDetailsVO completeProblemDetailsVO;
   private List<SelectOptionVO> stateListForProb;
   private List<SelectOptionVO> districtListForProb;
   private List<SelectOptionVO> constituencyListForProb;
   private List<SelectOptionVO> mandalListForProb;
   private List<SelectOptionVO> villagesListForProb;
   private RegionServiceDataImp regionServiceDataImp;
   private CadreManagementService cadreManagementService;
   private IStaticDataService staticDataService;
   private List<UserCommentsInfoVO> userCommentsInfoVOList;
   JSONObject jObj = null;
   private String task = null;
   private FileVO fileVO;
   private ResultStatus resultStatus;
   
   
   public Long getProblemId() {
	return problemId;
   }
   public void setProblemId(Long problemId) {
	this.problemId = problemId;
   }
   public ICompleteProblemDetailsService getCompleteProblemDetailsService() {
	return completeProblemDetailsService;
   }
   public void setCompleteProblemDetailsService(
		ICompleteProblemDetailsService completeProblemDetailsService) {
	this.completeProblemDetailsService = completeProblemDetailsService;
   }
   
   public CompleteProblemDetailsVO getCompleteProblemDetailsVO() {
	return completeProblemDetailsVO;
   }
   
   public void setCompleteProblemDetailsVO(
		CompleteProblemDetailsVO completeProblemDetailsVO) {
	this.completeProblemDetailsVO = completeProblemDetailsVO;
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
   public String getTask() {
	return task;
  }
  public void setTask(String task) {
	this.task = task;
  }
  
  public List<UserCommentsInfoVO> getUserCommentsInfoVOList() {
	return userCommentsInfoVOList;
  }
  public void setUserCommentsInfoVOList(
		List<UserCommentsInfoVO> userCommentsInfoVOList) {
	this.userCommentsInfoVOList = userCommentsInfoVOList;
  }
  public FileVO getFileVO() {
		return fileVO;
  }
  public void setFileVO(FileVO fileVO) {
	this.fileVO = fileVO;
   }
  
  public ResultStatus getResultStatus() {
	return resultStatus;
  }
  public void setResultStatus(ResultStatus resultStatus) {
	this.resultStatus = resultStatus;
  }
  
  public String execute(){
	   if(log.isDebugEnabled())
		   log.debug("Enter into execute method");
	   try{
	     RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
	     Long userId = null;
	     String userStatus = null;
		if(regVO != null){
			userId = regVO.getRegistrationID();
			if(session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE) != null && (Boolean)session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE))
				userStatus = IConstants.PARTY_ANALYST_USER;
			if(session.getAttribute(IWebConstants.FREE_USER_ROLE) != null && (Boolean)session.getAttribute(IWebConstants.FREE_USER_ROLE))
				userStatus = IConstants.FREE_USER;
			if(session.getAttribute(IWebConstants.FREE_USER_ROLE) != null && session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE) != null && (Boolean)session.getAttribute(IWebConstants.FREE_USER_ROLE) && (Boolean)session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE))
				userStatus = IConstants.BOTH;

            if(regVO.getAccessType() != null && regVO.getAccessValue() != null)
			getLocationAccess( regVO.getAccessType(),new Long(regVO.getAccessValue()));
			
		}else{
			userStatus = IConstants.NOT_LOGGED_IN;
		}
		completeProblemDetailsVO = completeProblemDetailsService.getProblemCompleteDetails(problemId, userId, userStatus,null);
	   }catch(Exception e){
		   log.error("Exception rised in execute method ",e);
	   }
	   return Action.SUCCESS;
   }

 public void setServletRequest(HttpServletRequest request) {
	    this.request = request;
		this.session = request.getSession();
	
 }
  public void getLocationAccess( String accessType,Long accessValue){
		
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
  }
  public String getProblemComments(){
	  try {
		  
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	  RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
	     Long userId = null;
		if(regVO != null){
			userId = regVO.getRegistrationID();
		}
	  userCommentsInfoVOList = completeProblemDetailsService.getPostedComments(jObj.getLong("problemId"),userId);
	  return Action.SUCCESS;
  }
  public String getProblemUpdatedDetails(){
	  try{
		  jObj = new JSONObject(getTask());
		  RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		     Long userId = null;
		     String userStatus = null;
			if(regVO != null){
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
			String reqTask = null;
		  if(jObj.getString("task").equalsIgnoreCase("getactivitydetails")){
			  reqTask ="getactivitydetails";
		  }else if(jObj.getString("task").equalsIgnoreCase("getphotodetails")){
			  reqTask ="getphotodetails";
		  }else if(jObj.getString("task").equalsIgnoreCase("getotheractvdetails")){
			  reqTask ="getotheractvdetails";
		  }else if(jObj.getString("task").equalsIgnoreCase("getstatustypedetails")){
			  reqTask ="getstatustypedetails";
		  }
		  completeProblemDetailsVO = completeProblemDetailsService.getProblemCompleteDetails(jObj.getLong("problemId"), userId, userStatus,reqTask);
	  }catch(Exception e){
		  log.error("Exception rised in getProblemUpdatedDetails method ",e);
	  }
	  return Action.SUCCESS;
  }
  
  public String getProbleFileDetails()
  {
	  String param;
	  param = getTask();
	  try{
		  jObj = new JSONObject(param);
	  }catch (Exception e) {
		e.printStackTrace();
		log.error("Exception Occured in editProbleFileDetails() Method, Exception - "+e);
	}
	  fileVO = completeProblemDetailsService.getProbleFileDetailsByProblemFileId(jObj.getLong("problemFileId"));
	  return Action.SUCCESS;
  }
  
  public String upDateProbleFileDetails()
  {
	  String param;
	  param = getTask();
	  try{
		  jObj = new JSONObject(param);
		  resultStatus = completeProblemDetailsService.upDateProbleFileDetails(jObj.getLong("fileId"),jObj.getString("fileTitle"),jObj.getString("fileDescription"));
	  }catch (Exception e) {
		e.printStackTrace();
		Log.error("Exception Occured in saveProbleFileDetailsAction() Method, Exception - "+e);
	}
	  return Action.SUCCESS;
  }
  
}
