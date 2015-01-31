package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeReportVO;
import com.itgrids.partyanalyst.dto.CommitteeSummaryVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CommitteeDashBoardAction extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest         			request;
	private HttpSession 						session;
	private EntitlementsHelper 					entitlementsHelper;
	private JSONObject							jObj;
	private String 								task;
	private LocationWiseBoothDetailsVO          locationWiseBoothDetailsVO;
	private ICadreCommitteeService				cadreCommitteeService;
	private static final Logger         		LOG = Logger.getLogger(CommitteeDashBoardAction.class);
	
	private CadreCommitteeReportVO          cadreCommitteeReportVO;
	private List<CommitteeSummaryVO>    districtWiseSummaryList,constiWiseSummaryList;
	private List<CadreCommitteeReportVO> cadreCommitteeReportVOList;
	private List<CadreCommitteeMemberVO> cadreCommitteeMemberVOList;
	
	
	public List<CommitteeSummaryVO> getConstiWiseSummaryList() {
		return constiWiseSummaryList;
	}
	public void setConstiWiseSummaryList(
			List<CommitteeSummaryVO> constiWiseSummaryList) {
		this.constiWiseSummaryList = constiWiseSummaryList;
	}
	public List<CommitteeSummaryVO> getDistrictWiseSummaryList() {
		return districtWiseSummaryList;
	}
	public void setDistrictWiseSummaryList(
			List<CommitteeSummaryVO> districtWiseSummaryList) {
		this.districtWiseSummaryList = districtWiseSummaryList;
	}
	public List<CadreCommitteeReportVO> getCadreCommitteeReportVOList() {
		return cadreCommitteeReportVOList;
	}
	public void setCadreCommitteeReportVOList(
			List<CadreCommitteeReportVO> cadreCommitteeReportVOList) {
		this.cadreCommitteeReportVOList = cadreCommitteeReportVOList;
	}
	public LocationWiseBoothDetailsVO getLocationWiseBoothDetailsVO() {
		return locationWiseBoothDetailsVO;
	}
	public void setLocationWiseBoothDetailsVO(
			LocationWiseBoothDetailsVO locationWiseBoothDetailsVO) {
		this.locationWiseBoothDetailsVO = locationWiseBoothDetailsVO;
	}
	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}
	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public String getTask() {
		return task;
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

	public CadreCommitteeReportVO getCadreCommitteeReportVO() {
		return cadreCommitteeReportVO;
	}
	public void setCadreCommitteeReportVO(
			CadreCommitteeReportVO cadreCommitteeReportVO) {
		this.cadreCommitteeReportVO = cadreCommitteeReportVO;
	}
	
	
	public List<CadreCommitteeMemberVO> getCadreCommitteeMemberVOList() {
		return cadreCommitteeMemberVOList;
	}
	public void setCadreCommitteeMemberVOList(
			List<CadreCommitteeMemberVO> cadreCommitteeMemberVOList) {
		this.cadreCommitteeMemberVOList = cadreCommitteeMemberVOList;
	}
	public String execute(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"TDP_COMMITTEE_ADMIN")){
			noaccess = true ;
		}
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		return Action.SUCCESS;
	}
	
	
	public String getDashBoardLocationWiseDetailsAction(){
		try{
			jObj = new JSONObject(getTask());
			JSONArray levelIdsArr = jObj.getJSONArray("levelIdsArr");			
			List<Long> levelIds = new ArrayList<Long>();
				
			if(levelIdsArr !=null && levelIdsArr.length() >0){
				for(int i=0; i<levelIdsArr.length(); i++ ){
					levelIds.add(Long.valueOf(levelIdsArr.get(i).toString().trim()));
				}
			}
			String state =jObj.getString("state");
			String startDate=jObj.getString("startDate");
			String endDate=jObj.getString("endDate");
			
			cadreCommitteeReportVO = cadreCommitteeService.getCommitteeDetailsByLocation(state,levelIds,startDate,endDate);
		
			
		}catch(Exception e){
			LOG.error("Exception Occured In getDashBoardLocationWiseDetailsAction method "+e);
		}
		return Action.SUCCESS;
	}
	
	
	public String getTotalCommitteeCntsByState(){
		try{
			jObj = new JSONObject(getTask());
			
			String state =jObj.getString("state");
			cadreCommitteeReportVO = cadreCommitteeService.getTotalCommitteeDetailsByLocation(state);
		
			
		}catch(Exception e){
			LOG.error("Exception Occured In getDashBoardLocationWiseDetailsAction method "+e);
		}
		return Action.SUCCESS;
	}
	
	public String getDistrictWiseCommittesSummary(){
		LOG.debug(" Entered Into getDistrictWiseCommittesSummary");
		try{
			jObj = new JSONObject(getTask());
			String state =jObj.getString("state");
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			
			districtWiseSummaryList = cadreCommitteeService.getDistrictWiseCommittesSummary(state, startDate, endDate);
		}catch (Exception e) {
			LOG.error(" Exception Raised In getDistrictWiseCommittesSummary" +e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getStartedAffliCommitteesCountByLocation(){
		try{
			jObj = new JSONObject(getTask());
			JSONArray levelIdsArr = jObj.getJSONArray("levelIdsArr");			
			List<Long> levelIds = new ArrayList<Long>();
				
			if(levelIdsArr !=null && levelIdsArr.length() >0){
				for(int i=0; i<levelIdsArr.length(); i++ ){
					levelIds.add(Long.valueOf(levelIdsArr.get(i).toString().trim()));
				}
			}
			String state =jObj.getString("state");
			String startdate=jObj.getString("startDate");
			String endDate=jObj.getString("endDate");
			
			cadreCommitteeReportVOList = cadreCommitteeService.getStartedAffliCommitteesCountByLocation(state,levelIds,startdate,endDate);
		
			
		}catch(Exception e){
			LOG.error("Exception Occured In getDashBoardLocationWiseDetailsAction method "+e);
		}
		return Action.SUCCESS;
	}
	
	public String getMembersRangeCountByLocation(){
		try{
			jObj = new JSONObject(getTask());
			JSONArray levelIdsArr = jObj.getJSONArray("levelIdsArr");			
			List<Long> levelIds = new ArrayList<Long>();
				
			if(levelIdsArr !=null && levelIdsArr.length() >0){
				for(int i=0; i<levelIdsArr.length(); i++ ){
					levelIds.add(Long.valueOf(levelIdsArr.get(i).toString().trim()));
				}
			}
			String state =jObj.getString("state");
			Long committeeId = jObj.getLong("committeeId");
			String startdate=jObj.getString("startDate");
			String endDate=jObj.getString("endDate");
			cadreCommitteeReportVOList = cadreCommitteeService.getMembersRangeCountByLocation(state,levelIds,committeeId,startdate,endDate);
		
			
		}catch(Exception e){
			LOG.error("Exception Occured In getDashBoardLocationWiseDetailsAction method "+e);
		}
		return Action.SUCCESS;
	}
	
	public String getConstituencyWiseCommittesSummary(){
		LOG.debug(" Entered Into getConstituencyWiseCommittesSummary");
		try{
			jObj = new JSONObject(getTask());
			String state =jObj.getString("state");
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			
			constiWiseSummaryList = cadreCommitteeService.getConstituencyWiseCommittesSummary(state, startDate, endDate);
		}catch (Exception e) {
			LOG.error(" Exception Raised In getConstituencyWiseCommittesSummary" +e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getSummaryDetailsPopUp(){
		try{
			jObj = new JSONObject(getTask());
			districtWiseSummaryList = cadreCommitteeService.getSummaryDetails(jObj.getString("constituencyId"));
		}catch(Exception e){
			LOG.error("Exception Occured In getSummaryDetailsPopUp method "+e);			
		}
		return Action.SUCCESS;
	}
	public String gettingMandalAndMuncipalAndDivisonSummaryPopUp(){
		try{
			jObj = new JSONObject(getTask());
			districtWiseSummaryList= cadreCommitteeService.gettingMandalAndMuncipalAndDivisonSummary(jObj.getString("constituencyId"));
		}catch(Exception e){
			LOG.error("Exception Occured In gettingMandalAndMuncipalAndDivisonSummary method "+e);			
		}
		return Action.SUCCESS;
	}
public String getCommitteeDetailsByStatusPopUp(){

		
		try{
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("memberCnt"))
			cadreCommitteeMemberVOList = cadreCommitteeService.getCommitteeDetailsByStatus(jObj.getLong("basicCommitteetypeId"),jObj.getString("status"),jObj.getLong("levelId"),jObj.getString("constituencyId"));
			else if(jObj.getString("task").equalsIgnoreCase("memberInfo"))
				cadreCommitteeMemberVOList = cadreCommitteeService.getCommitteeMemberDetails(jObj.getLong("basicCommitteetypeId"),jObj.getLong("locationId"),jObj.getLong("levelId"),jObj.getString("status"));
			else if(jObj.getString("task").equalsIgnoreCase("committeComplete"))
				cadreCommitteeMemberVOList = cadreCommitteeService.setCommitteConfirmation(jObj.getLong("basicCommitteetypeId"),jObj.getLong("locationId"),jObj.getLong("levelId"));
			else if(jObj.getString("task").equalsIgnoreCase("deleterole"))
				cadreCommitteeMemberVOList = cadreCommitteeService.deleteCadreRole(jObj.getLong("tdpcommitteeMemberId"));
		}catch(Exception e){
			LOG.error("Exception occured in getCommitteeDetailsByStatus() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	
}
