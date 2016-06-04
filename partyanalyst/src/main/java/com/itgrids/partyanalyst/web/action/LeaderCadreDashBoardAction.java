package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreAmountDetailsVO;
import com.itgrids.partyanalyst.dto.CadreDataAnalysisVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.TabRecordsStatusVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ILeaderCadreDashBoardService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;

public class LeaderCadreDashBoardAction implements ServletRequestAware {

	private static Logger LOG = Logger.getLogger(LeaderCadreDashBoardAction.class);
	
	private HttpServletRequest request;
	
	private EntitlementsHelper entitlementsHelper;
	private JSONObject jObj;
	private String task;
	private ILeaderCadreDashBoardService leaderCadreDashBoardService;
	private List<CadreAmountDetailsVO> amountDetails;
	private CadreAmountDetailsVO amountDetailsVO;
	private List<CadreDataAnalysisVO> resultList;
	private TabRecordsStatusVO  status;
	
		public List<CadreDataAnalysisVO> getResultList() {
		return resultList;
	}


	public void setResultList(List<CadreDataAnalysisVO> resultList) {
		this.resultList = resultList;
	}


		public CadreAmountDetailsVO getAmountDetailsVO() {
		return amountDetailsVO;
	}


	public void setAmountDetailsVO(CadreAmountDetailsVO amountDetailsVO) {
		this.amountDetailsVO = amountDetailsVO;
	}


		public String getTask() {
		return task;
	}

		
	public List<CadreAmountDetailsVO> getAmountDetails() {
			return amountDetails;
		}


		public void setAmountDetails(List<CadreAmountDetailsVO> amountDetails) {
			this.amountDetails = amountDetails;
		}


	public ILeaderCadreDashBoardService getLeaderCadreDashBoardService() {
			return leaderCadreDashBoardService;
		}

		public void setLeaderCadreDashBoardService(
				ILeaderCadreDashBoardService leaderCadreDashBoardService) {
			this.leaderCadreDashBoardService = leaderCadreDashBoardService;
		}

	public void setTask(String task) {
		this.task = task;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
 
	public TabRecordsStatusVO getStatus() {
		return status;
	}


	public void setStatus(TabRecordsStatusVO status) {
		this.status = status;
	}


	public String execute(){
		LOG.info("Entered into execute() in LeaderCadreDashBoardActioon class");
		leaderCadreDashBoardService.testMethod();
		return Action.SUCCESS;
	}
	
	public String getLoationWiseLeaderCadreInfo()
	{
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			jObj = new JSONObject(getTask());
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Date reqFromDate = null;
			Date reqToDate = null;
			if(fromDate.trim().length() > 0){
				reqFromDate = sdf.parse(fromDate);
			}
			if(toDate.trim().length() > 0){
				reqToDate = sdf.parse(toDate);
			}
			if(jObj.getString("task").equalsIgnoreCase("mainLevel")){
			    amountDetails = leaderCadreDashBoardService.getLoationWiseLeaderCadreDetails(jObj.getString("type"),jObj.getLong("stateId"),regVO.getAccessType(),regVO.getAccessValue(),reqFromDate,reqToDate);
			}if(jObj.getString("task").equalsIgnoreCase("subLevel")){
				amountDetails = leaderCadreDashBoardService.getSubLevelLoationWiseLeaderCadreDetails(jObj.getString("type"),jObj.getLong("id"),regVO.getAccessType(),regVO.getAccessValue(),reqFromDate,reqToDate);
			}
		}
		catch (Exception e) {
			LOG.info("Entered into getLoationLeaderCadreInfo() in LeaderCadreDashBoardActioon class");
		}
		return Action.SUCCESS;
	}
	
	public String getLocationWiseAsOfNowDetails(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO!=null){
			String accessType = regVO.getAccessType();
			Long accessValue = Long.valueOf(regVO.getAccessValue());
			
			try{
				jObj = new JSONObject(getTask());
				if(jObj.getString("task").equalsIgnoreCase("locationWiseInfoForState"))
				{
					amountDetails = leaderCadreDashBoardService.getLocationWiseAsOfNowDetailsInfo(jObj.getString("type"),jObj.getLong("stateId"),accessType,accessValue);
				}
				else
				{
				amountDetails = leaderCadreDashBoardService.getLocationWiseAsOfNowDetails(jObj.getString("type"),jObj.getLong("stateId"),accessType,accessValue);
				}
				
			}
			catch (Exception e) {
				LOG.info("Entered into getLocationWiseAsOfNowDetails() in LeaderCadreDashBoardActioon class");
			}
		}
		
		return Action.SUCCESS;
	}
	
	public String getLocationWiseToDayDetails()	{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO!=null){
			String accessType = regVO.getAccessType();
			Long accessValue = Long.valueOf(regVO.getAccessValue());
			
			try{
				jObj = new JSONObject(getTask());
				String task = jObj.getString("fromTask");
				if(task.equalsIgnoreCase("today")){
					amountDetails = leaderCadreDashBoardService.getLocationWiseToDayDetails(jObj.getString("type"),jObj.getLong("stateId"),"today",accessType,accessValue);
				}else{
					amountDetails = leaderCadreDashBoardService.getLocationWiseToDayDetails(jObj.getString("type"),jObj.getLong("stateId"),"asOfToday",accessType,accessValue);
				}
			}
			catch (Exception e) {
				LOG.info("Entered into getLocationWiseToDayDetails() in LeaderCadreDashBoardActioon class");
			}
		}
		return Action.SUCCESS;
	}
	
	public String duplicateUserExecute()
	{
		
		return Action.SUCCESS;
	}
	public String getDuplicateUsersInLocation()
	{
		try {
			jObj = new JSONObject(getTask());
		
		if(jObj.getString("task").equalsIgnoreCase("usersData"))
		{
			Long  userId = jObj.getLong("userId");
			Long locationId = jObj.getLong("locationId");
			Long constituencyId = jObj.getLong("constituencyId");
			String type = jObj.getString("type");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Date reqFromDate = null;
			Date reqToDate = null;
			if(fromDate.trim().length() > 0){
				reqFromDate = sdf.parse(fromDate);
			}
			if(toDate.trim().length() > 0){
				reqToDate = sdf.parse(toDate);
			}
			amountDetailsVO = leaderCadreDashBoardService.getUsersInLocation(userId,reqFromDate,reqToDate,locationId,constituencyId,type);
		}
		else if(jObj.getString("task").equalsIgnoreCase("getUsers"))
		{

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Date reqFromDate = null;
			Date reqToDate = null;
			if(fromDate.trim().length() > 0){
				reqFromDate = sdf.parse(fromDate);
			}
			if(toDate.trim().length() > 0){
				reqToDate = sdf.parse(toDate);
			}
			amountDetailsVO = leaderCadreDashBoardService.getDuplicateUsersInLocation(reqFromDate,reqToDate);
		}
	
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getYouthAndMahilaInfo(){
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			jObj = new JSONObject(getTask());
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Date reqFromDate = null;
			Date reqToDate = null;
			if(fromDate.trim().length() > 0){
				reqFromDate = sdf.parse(fromDate);
			}
			if(toDate.trim().length() > 0){
				reqToDate = sdf.parse(toDate);
			}
			if(jObj.getString("task").equalsIgnoreCase("mainLevel")){
			    amountDetails = leaderCadreDashBoardService.getYouthMahilaInfo(jObj.getString("locationType"),jObj.getLong("locationId"),regVO.getAccessType(),regVO.getAccessValue(),reqFromDate,reqToDate);
			}if(jObj.getString("task").equalsIgnoreCase("subLevel")){
				amountDetails = leaderCadreDashBoardService.getSubLevelLoationWiseYouthMahilaInfo(jObj.getString("locationType"),jObj.getLong("locationId"),regVO.getAccessType(),regVO.getAccessValue(),reqFromDate,reqToDate);
			}
		}
		catch (Exception e) {
			LOG.info("Entered into getLoationLeaderCadreInfo() in LeaderCadreDashBoardActioon class");
		}
		return Action.SUCCESS;
	}
	
	public String getCasteWiseInfo()
	{
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			jObj = new JSONObject(getTask());
			
			if(jObj.getString("task").equalsIgnoreCase("mainLevel")){
				amountDetailsVO = leaderCadreDashBoardService.getCasteWiseDetails(jObj.getLong("stateId"),jObj.getString("locationtype"));
			}if(jObj.getString("task").equalsIgnoreCase("subLevel")){
				amountDetailsVO = leaderCadreDashBoardService.getSubLoctaionCasteWiseDetails(jObj.getLong("stateId"),jObj.getString("locationtype"),jObj.getLong("Id"));
			}
		}
		catch (Exception e) {
			LOG.info("Entered into getLoationLeaderCadreInfo() in LeaderCadreDashBoardActioon class");
		}
		return Action.SUCCESS;
	}
	
	public String cadreBoothAnalysisExecute()
	{
		try{
			
		}
		catch(Exception e)
		{
			
		}
		return Action.SUCCESS;
	}
	public String getCadreBoothAnalysisInfo()
	{
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			List<Long> boothIds = new ArrayList<Long>();
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("constituencyInfo"))
			resultList = leaderCadreDashBoardService.getCadreBoothAnalysisReport(jObj.getLong("stateId"));
			if(jObj.getString("task").equalsIgnoreCase("boothInfo"))
			{
				String[] Ids = jObj.getString("ids").split(",");
				for(String boothId : Ids)
				boothIds.add(Long.parseLong(boothId));
				resultList = leaderCadreDashBoardService.getBoothInfo(boothIds,jObj.getLong("id"));
			}
			
		}
		catch (Exception e) {
			LOG.info("Entered into getCadreBoothAnalysisInfo() in LeaderCadreDashBoardActioon class");
		}
		return Action.SUCCESS;
	}
	
	public String getBoothWiseDetails(){
		try{
			jObj = new JSONObject(getTask());
			amountDetails = leaderCadreDashBoardService.getBoothWiseDetails(jObj.getLong("constituencyId"));
		}
		catch (Exception e) {
			LOG.error("Entered into getBoothWiseDetails() in LeaderCadreDashBoardAction class",e);
		}
		return Action.SUCCESS;
	}
	
	public String getMISReport(){
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			boolean noaccess = false;
			if(regVO==null){
				return "input";
			}
			 List<String> entitlements = null;
				if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
					entitlements = regVO.getEntitlements();
					if(!entitlements.contains("MISREPORT")){
						noaccess = true ;
					}
			/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"MISREPORT")){
				noaccess = true ;
			}*/
			if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
				noaccess = false;
			}
			if(noaccess){
				status = new TabRecordsStatusVO();
				status.setName("refresh");
				return Action.SUCCESS;
			}
			jObj = new JSONObject(getTask());
			status = leaderCadreDashBoardService.getMISReport(jObj.getString("batchCode"),null,null);
		}
	}
		catch (Exception e) {
			LOG.error("Entered into getMISReport() in LeaderCadreDashBoardAction class",e);
		}
		return Action.SUCCESS;
	}
	
	public String getMISReportForLocation()
	{
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			boolean noaccess = false;
			if(regVO==null){
				return "input";
			}
			 List<String> entitlements = null;
				if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
					entitlements = regVO.getEntitlements();
					if(!entitlements.contains("MISREPORT")){
						noaccess = true ;
					}
			/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"MISREPORT")){
				noaccess = true ;
			}*/
			if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
				noaccess = false;
			}
			if(noaccess){
				status = new TabRecordsStatusVO();
				status.setName("refresh");
				return Action.SUCCESS;
			}
			jObj = new JSONObject(getTask());
			status = leaderCadreDashBoardService.getMISReport(jObj.getString("batchCode"),jObj.getLong("Id"),jObj.getString("type"));
		}
	}
		catch (Exception e) {
			LOG.error("Entered into getMISReport() in LeaderCadreDashBoardAction class",e);
		}
		return Action.SUCCESS;
	}
	public String misReport(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}
		 List<String> entitlements = null;
			if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
				entitlements = regVO.getEntitlements();
				if(!entitlements.contains("MISREPORT")){
					noaccess = true ;
				}
		/*if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"MISREPORT")){
			noaccess = true ;
		}*/
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		
		if(noaccess){
			return "error";
		}
	}
		return Action.SUCCESS;
	}
}