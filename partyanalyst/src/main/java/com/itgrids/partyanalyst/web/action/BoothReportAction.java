package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.BoothAddressVO;
import com.itgrids.partyanalyst.dto.BoothInchargeDetailsVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.service.IBoothDataValidationService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class BoothReportAction extends ActionSupport implements ServletRequestAware {

	
	private static final Logger LOG = Logger.getLogger(BoothReportAction.class);
	
	private HttpServletRequest request;
	private HttpSession 	   session;
	private String task;
	private JSONObject jObj;
	private List<BoothInchargeDetailsVO> boothInchargeDetailsList;
	private IBoothDataValidationService boothDataValidationService;
	private BoothInchargeDetailsVO resultVO;
	private List<BoothAddressVO> boothDtlsList;
	private List<IdAndNameVO> idAndNameVOList;
	private List<BoothInchargeDetailsVO> boothIncbhargeVOList;
	
	
	public List<IdAndNameVO> getIdAndNameVOList() {
		return idAndNameVOList;
	}
	public void setIdAndNameVOList(List<IdAndNameVO> idAndNameVOList) {
		this.idAndNameVOList = idAndNameVOList;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public List<BoothInchargeDetailsVO> getBoothInchargeDetailsList() {
		return boothInchargeDetailsList;
	}
	public void setBoothInchargeDetailsList(List<BoothInchargeDetailsVO> boothInchargeDetailsList) {
		this.boothInchargeDetailsList = boothInchargeDetailsList;
	}
	public void setBoothDataValidationService(IBoothDataValidationService boothDataValidationService) {
		this.boothDataValidationService = boothDataValidationService;
	}
	
	public List<BoothAddressVO> getBoothDtlsList() {
		return boothDtlsList;
	}
	public void setBoothDtlsList(List<BoothAddressVO> boothDtlsList) {
		this.boothDtlsList = boothDtlsList;
	}
	public BoothInchargeDetailsVO getResultVO() {
		return resultVO;
	}
	public void setResultVO(BoothInchargeDetailsVO resultVO) {
		this.resultVO = resultVO;
	}
	
	public List<BoothInchargeDetailsVO> getBoothIncbhargeVOList() {
		return boothIncbhargeVOList;
	}
	public void setBoothIncbhargeVOList(
			List<BoothInchargeDetailsVO> boothIncbhargeVOList) {
		this.boothIncbhargeVOList = boothIncbhargeVOList;
	}
	@Override
	public String execute() throws Exception {
		LOG.debug("BoothReportAction.execute()... started");
		String strBoothID = request.getParameter("boothID");
		String strPartNo = request.getParameter("partNo");
		LOG.debug("strBoothID:::"+strBoothID);
		LOG.debug("strPartNo:::"+strPartNo);
		if(strBoothID==null)
			return ERROR;
		Long boothID = Long.valueOf(strBoothID);
		
		return SUCCESS;
	}
	public String getBoothInchargeDashboard(){
		
		return Action.SUCCESS;
	}
	
	public String getLocationLevelWiseBoothCount(){
		LOG.info("Entered into getLocationLevelWiseBoothCount()  of BoothReportAction ");
		try{
			jObj = new JSONObject(getTask());
			InputVO inputVO = new InputVO();
			inputVO.setLocationLevel(jObj.getString("locationLevel"));
			inputVO.setFilterLevel(jObj.getString("filterLevel"));
			JSONArray filterValueArr =jObj.getJSONArray("filterValueArr");
			if(filterValueArr!=null &&  filterValueArr.length()>0){
				for( int i=0;i<filterValueArr.length();i++){
					inputVO.getFilterValueList().add(Long.valueOf(filterValueArr.getString(i)));
				}
			}
			JSONArray boothRoleIdArr =jObj.getJSONArray("boothRoleIdArr");
			if(boothRoleIdArr!=null &&  boothRoleIdArr.length()>0){
				for( int i=0;i<boothRoleIdArr.length();i++){
					inputVO.getBoothRoleIds().add(Long.valueOf(boothRoleIdArr.getString(i)));
				}
			}
			
			inputVO.setBoothInchargeEnrollmentId(jObj.getLong("boothInchargeEnrollmentId"));
			inputVO.setFromDateStr(jObj.getString("startDate"));
			inputVO.setToDateStr(jObj.getString("endDate"));
			boothInchargeDetailsList = boothDataValidationService.getLocationLevelWiseBoothCount(inputVO);
		}catch(Exception e){
			LOG.error("Exception raised at getLocationLevelWiseBoothCount() method of BoothReportAction", e);
		}
		return Action.SUCCESS;
	}
	public String getLocationBasedOnSelection(){
		LOG.info("Entered into getLocationLevelWiseBoothCount()  of BoothReportAction ");
		try{
			jObj = new JSONObject(getTask());
			InputVO inputVO = new InputVO();
			inputVO.setLocationLevel(jObj.getString("locationLevel"));
			inputVO.setFilterLevel(jObj.getString("filterLevel"));
			JSONArray filterValueArr =jObj.getJSONArray("filterValueArr");
			if(filterValueArr!=null &&  filterValueArr.length()>0){
				for( int i=0;i<filterValueArr.length();i++){
					inputVO.getFilterValueList().add(Long.valueOf(filterValueArr.getString(i)));
				}
			}
			JSONArray boothRoleIdArr =jObj.getJSONArray("boothRoleIdArr");
			if(boothRoleIdArr!=null &&  boothRoleIdArr.length()>0){
				for( int i=0;i<boothRoleIdArr.length();i++){
					inputVO.getBoothRoleIds().add(Long.valueOf(boothRoleIdArr.getString(i)));
				}
			}
			inputVO.setBoothInchargeEnrollmentId(jObj.getLong("boothInchargeEnrollmentId"));
			boothInchargeDetailsList = boothDataValidationService.getLocationBasedOnSelection(inputVO);
		}catch(Exception e){
			LOG.error("Exception raised at getLocationLevelWiseBoothCount() method of BoothReportAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getLocationLevelWiseBoothDetails(){
		LOG.info("Entered into getLocationLevelWiseBoothCount()  of BoothReportAction ");
		try{
			jObj = new JSONObject(getTask());
			InputVO inputVO = new InputVO();
			inputVO.setFilterLevel(jObj.getString("filterType"));
			JSONArray filterValueArr =jObj.getJSONArray("filterValueArr");
			if(filterValueArr!=null &&  filterValueArr.length()>0){
				for( int i=0;i<filterValueArr.length();i++){
					inputVO.getFilterValueList().add(Long.valueOf(filterValueArr.getString(i)));
				}
			}
			JSONArray boothRoleIdArr =jObj.getJSONArray("boothRoleIdArr");
			if(boothRoleIdArr!=null &&  boothRoleIdArr.length()>0){
				for( int i=0;i<boothRoleIdArr.length();i++){
					inputVO.getBoothRoleIds().add(Long.valueOf(boothRoleIdArr.getString(i)));
				}
			}
			inputVO.setBoothInchargeEnrollmentId(jObj.getLong("boothEnrollementYearId"));
			inputVO.setFromDateStr(jObj.getString("fromDate"));
			inputVO.setToDateStr(jObj.getString("toDate"));
			inputVO.setResultType(jObj.getString("resultType"));
			boothDtlsList = boothDataValidationService.getLocationLevelWiseBoothDetails(inputVO);
		}catch(Exception e){
			LOG.error("Exception raised at getLocationLevelWiseBoothDetails() method of BoothReportAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getBoothInchargeRoles(){
		LOG.info("Entered into getLocationLevelWiseBoothCount()  of BoothReportAction ");
		try{
			jObj = new JSONObject(getTask());
			Long boothId = jObj.getLong("boothId");
			
			JSONArray enrollmentYrIdArr = jObj.getJSONArray("enrollmentYrIds");
			List<Long> enrollmentYrIds = new ArrayList<Long>(0);
			if(enrollmentYrIdArr != null && enrollmentYrIdArr.length()>0){
				for (int i = 0; i < enrollmentYrIdArr.length(); i++) {
					enrollmentYrIds.add(Long.valueOf(enrollmentYrIdArr.get(i).toString().trim()));
				}
			}
			idAndNameVOList = boothDataValidationService.getBoothInchargeRoles(boothId,enrollmentYrIds);
		}catch(Exception e){
			LOG.error("Exception raised at getBoothInchargeRoles() method of BoothReportAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String validateBoothToMakeConfirm(){
		LOG.info("Entered into getLocationLevelWiseBoothCount()  of BoothReportAction ");
		try{
			jObj = new JSONObject(getTask());
			Long boothId = jObj.getLong("boothId");
			Long boothInchargeEnrollmentId = jObj.getLong("boothInchargeEnrollmentId");
			resultVO = boothDataValidationService.validateBoothToMakeConfirm(boothId,boothInchargeEnrollmentId);
		}catch(Exception e){
			LOG.error("Exception raised at validateBoothToMakeConfirm() method of BoothReportAction", e);
		}
		return Action.SUCCESS;
	}
	public String gettingBoothInchargeRoleDetails(){
		LOG.info("Entered into gettingBoothInchargeRoleDetails()  of BoothReportAction ");
		try{
			jObj = new JSONObject(getTask());
			Long boothId = jObj.getLong("boothId");
			Long boothInchargeEnrollmentId = jObj.getLong("boothInchargeEnrollmentId");
			Long locationValue = jObj.getLong("constituencyId");
			boothIncbhargeVOList = boothDataValidationService.gettingBoothInchargeRoleDetails(boothId,boothInchargeEnrollmentId,locationValue);
		}catch(Exception e){
			LOG.error("Exception raised at gettingBoothInchargeRoleDetails() method of BoothReportAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String updateRangeIdsOfBoothIncharge(){
		LOG.info("Entered into updateRangeIdsOfBoothIncharge()  of BoothReportAction ");
		try{
			jObj = new JSONObject(getTask());
			Long boothId = jObj.getLong("boothId");
			Long boothIncgRoleId = jObj.getLong("boothIncgRoleId");
			
			JSONArray enrollmentYrIdArr = jObj.getJSONArray("enrollmentYrIds");
			List<Long> enrollmentYrIds = new ArrayList<Long>(0);
			if(enrollmentYrIdArr != null && enrollmentYrIdArr.length()>0){
				for (int i = 0; i < enrollmentYrIdArr.length(); i++) {
					enrollmentYrIds.add(Long.valueOf(enrollmentYrIdArr.get(i).toString().trim()));
				}
			}
			task = boothDataValidationService.updateRangeIdsOfBoothIncharge(boothId,boothIncgRoleId,enrollmentYrIds);
		}catch(Exception e){
			LOG.error("Exception raised at updateRangeIdsOfBoothIncharge() method of BoothReportAction", e);
		}
		return Action.SUCCESS;
	}
	public String deleteRoleMemberDetails(){
		LOG.info("Entered into deleteRoleMemberDetails()  of BoothReportAction ");
		try{
			jObj = new JSONObject(getTask());
			Long boothInchargeMappingId = jObj.getLong("boothInchargeMappingId");
			Long boothInchargeId = jObj.getLong("boothInchargeId");
			
			task = boothDataValidationService.deleteRoleMemberDetails(boothInchargeMappingId,boothInchargeId);
		}catch(Exception e){
			LOG.error("Exception raised at deleteRoleMemberDetails() method of BoothReportAction", e);
		}
		return Action.SUCCESS;  
	}
}
