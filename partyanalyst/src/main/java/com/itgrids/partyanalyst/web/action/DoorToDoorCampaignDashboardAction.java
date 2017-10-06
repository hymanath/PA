package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.DoorCampaignDashboardVO;
import com.itgrids.partyanalyst.dto.DoorToDoorInputVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IDoorToDoorCampaignDashboardService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DoorToDoorCampaignDashboardAction extends ActionSupport implements ServletRequestAware{

	private static final Logger         		LOG = Logger.getLogger(DoorToDoorCampaignDashboardAction.class);
	private HttpServletRequest         			request;
	private HttpSession 						session;
	private String task = null;
	JSONObject jObj = null;	
	private IDoorToDoorCampaignDashboardService doorToDoorCampaignDashboardService;
	private DoorCampaignDashboardVO doorCampaignDashboardVO;
	private List<DoorCampaignDashboardVO> doorCampaignDashboardVOList;
	
	
	public DoorCampaignDashboardVO getDoorCampaignDashboardVO() {
		return doorCampaignDashboardVO;
	}
	public void setDoorCampaignDashboardVO(
			DoorCampaignDashboardVO doorCampaignDashboardVO) {
		this.doorCampaignDashboardVO = doorCampaignDashboardVO;
	}
	public List<DoorCampaignDashboardVO> getDoorCampaignDashboardVOList() {
		return doorCampaignDashboardVOList;
	}
	public void setDoorCampaignDashboardVOList(
			List<DoorCampaignDashboardVO> doorCampaignDashboardVOList) {
		this.doorCampaignDashboardVOList = doorCampaignDashboardVOList;
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
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	
	public IDoorToDoorCampaignDashboardService getDoorToDoorCampaignDashboardService() {
		return doorToDoorCampaignDashboardService;
	}
	public void setDoorToDoorCampaignDashboardService(
			IDoorToDoorCampaignDashboardService doorToDoorCampaignDashboardService) {
		this.doorToDoorCampaignDashboardService = doorToDoorCampaignDashboardService;
	}
	
	public String execute()
	{	
		session = request.getSession();
		RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
		boolean noaccess = false;
		if(regVo==null){
			return "input";
		}
		if(regVo.getEntitlements() != null && regVo.getEntitlements().contains("ITDP_DOOR_TO_DOOR_DASHBOARD_USER_ENTITLEMENT"))  
			return Action.SUCCESS;
		else
			return Action.ERROR;
	}
	
	
	public String getUsersCountsByLocation(){
		try{
			jObj = new JSONObject(getTask());
			JSONArray jArray = jObj.getJSONArray("levelIds");
			List<Long> levelIdList = new ArrayList<Long>();    
			for (int i = 0; i < jArray.length(); i++){
				levelIdList.add(Long.parseLong(jArray.getString(i)));
			} 
			JSONArray levelValueArr = jObj.getJSONArray("levelValues");
			List<Long> levelValuesList = new ArrayList<Long>();    
			for (int i = 0; i < levelValueArr.length(); i++){
				levelValuesList.add(Long.parseLong(levelValueArr.getString(i)));
			}
			
			String fromDate = jObj.getString("startDateStr");
			String toDate = jObj.getString("endDateStr");
			DoorToDoorInputVO inputvo = new DoorToDoorInputVO();
			inputvo.setLevelIds(levelIdList);
			inputvo.setLevelValues(levelValuesList);
			inputvo.setStartDateStr(fromDate);
			inputvo.setEndDateStr(toDate);
			
			doorCampaignDashboardVO = doorToDoorCampaignDashboardService.getUsersCountsByLocation(inputvo);
		}catch (Exception e) {
			LOG.error("Exception rised in getUsersCountsByLocation",e);
		}
		
		return Action.SUCCESS; 
	}
	
	public String getHouseHoldsCounts(){
		try{
			jObj = new JSONObject(getTask());
			JSONArray jArray = jObj.getJSONArray("levelIds");
			List<Long> levelIdList = new ArrayList<Long>();    
			for (int i = 0; i < jArray.length(); i++){
				levelIdList.add(Long.parseLong(jArray.getString(i)));
			} 
			JSONArray levelValueArr = jObj.getJSONArray("levelValues");
			List<Long> levelValuesList = new ArrayList<Long>();    
			for (int i = 0; i < levelValueArr.length(); i++){
				levelValuesList.add(Long.parseLong(levelValueArr.getString(i)));
			}
			
			String fromDate = jObj.getString("startDateStr");
			String toDate = jObj.getString("endDateStr");
			DoorToDoorInputVO inputvo = new DoorToDoorInputVO();
			inputvo.setLevelIds(levelIdList);
			inputvo.setLevelValues(levelValuesList);
			inputvo.setStartDateStr(fromDate);
			inputvo.setEndDateStr(toDate);
			
			doorCampaignDashboardVO = doorToDoorCampaignDashboardService.getHouseHoldsCounts(inputvo);
		}catch (Exception e) {
			LOG.error("Exception rised in getUsersCountsByLocation",e);
		}
		
		return Action.SUCCESS; 
	}
	
	public String getGrievancesCounts(){
		try{
			jObj = new JSONObject(getTask());
			JSONArray jArray = jObj.getJSONArray("levelIds");
			List<Long> levelIdList = new ArrayList<Long>();    
			for (int i = 0; i < jArray.length(); i++){
				levelIdList.add(Long.parseLong(jArray.getString(i)));
			} 
			JSONArray levelValueArr = jObj.getJSONArray("levelValues");
			List<Long> levelValuesList = new ArrayList<Long>();    
			for (int i = 0; i < levelValueArr.length(); i++){
				levelValuesList.add(Long.parseLong(levelValueArr.getString(i)));
			}
			
			String fromDate = jObj.getString("startDateStr");
			String toDate = jObj.getString("endDateStr");
			DoorToDoorInputVO inputvo = new DoorToDoorInputVO();
			inputvo.setLevelIds(levelIdList);
			inputvo.setLevelValues(levelValuesList);
			inputvo.setStartDateStr(fromDate);
			inputvo.setEndDateStr(toDate);
			
			doorCampaignDashboardVO = doorToDoorCampaignDashboardService.getGrievancesCounts(inputvo);
		}catch (Exception e) {
			LOG.error("Exception rised in getUsersCountsByLocation",e);
		}
		
		return Action.SUCCESS; 
	}
	
	public String getRecentImagesList(){
		try{
			jObj = new JSONObject(getTask());
			JSONArray jArray = jObj.getJSONArray("levelIds");
			List<Long> levelIdList = new ArrayList<Long>();    
			for (int i = 0; i < jArray.length(); i++){
				levelIdList.add(Long.parseLong(jArray.getString(i)));
			} 
			JSONArray levelValueArr = jObj.getJSONArray("levelValues");
			List<Long> levelValuesList = new ArrayList<Long>();    
			for (int i = 0; i < levelValueArr.length(); i++){
				levelValuesList.add(Long.parseLong(levelValueArr.getString(i)));
			}
			
			String fromDate = jObj.getString("startDateStr");
			String toDate = jObj.getString("endDateStr");
			String imageType = jObj.getString("imageType");
			
			DoorToDoorInputVO inputvo = new DoorToDoorInputVO();
			inputvo.setLevelIds(levelIdList);
			inputvo.setLevelValues(levelValuesList);
			inputvo.setStartDateStr(fromDate);
			inputvo.setEndDateStr(toDate);
			inputvo.setImageType(imageType);
			
			doorCampaignDashboardVO = doorToDoorCampaignDashboardService.getRecentImagesList(inputvo);
		}catch (Exception e) {
			LOG.error("Exception rised in getRecentImagesList",e);
		}
		
		return Action.SUCCESS; 
	}
	
	public String getLocationWiseCountDetails(){
		try{
			jObj = new JSONObject(getTask());
			String locationType = jObj.getString("locationType");
			
			JSONArray jArray = jObj.getJSONArray("levelIds");
			List<Long> levelIdList = new ArrayList<Long>();    
			for (int i = 0; i < jArray.length(); i++){
				levelIdList.add(Long.parseLong(jArray.getString(i)));
			} 
			JSONArray levelValueArr = jObj.getJSONArray("levelValues");
			List<Long> levelValuesList = new ArrayList<Long>();    
			for (int i = 0; i < levelValueArr.length(); i++){
				levelValuesList.add(Long.parseLong(levelValueArr.getString(i)));
			}
			
			String fromDate = jObj.getString("startDateStr");
			String toDate = jObj.getString("endDateStr");
			DoorToDoorInputVO inputvo = new DoorToDoorInputVO();
			inputvo.setLevelIds(levelIdList);
			inputvo.setLevelValues(levelValuesList);
			inputvo.setStartDateStr(fromDate);
			inputvo.setEndDateStr(toDate);
			inputvo.setLocationType(locationType);
			
			doorCampaignDashboardVOList = doorToDoorCampaignDashboardService.getLocationWiseCountDetails(inputvo);
		}catch (Exception e) {
			LOG.error("Exception rised in getRecentImagesList",e);
		}
		
		return Action.SUCCESS; 
	}
	
	public String getDepartmentWiseGrievanceCounts(){
		try{
			jObj = new JSONObject(getTask());
			JSONArray jArray = jObj.getJSONArray("levelIds");
			List<Long> levelIdList = new ArrayList<Long>();    
			for (int i = 0; i < jArray.length(); i++){
				levelIdList.add(Long.parseLong(jArray.getString(i)));
			} 
			JSONArray levelValueArr = jObj.getJSONArray("levelValues");
			List<Long> levelValuesList = new ArrayList<Long>();    
			for (int i = 0; i < levelValueArr.length(); i++){
				levelValuesList.add(Long.parseLong(levelValueArr.getString(i)));
			}
			
			String fromDate = jObj.getString("startDateStr");
			String toDate = jObj.getString("endDateStr");
			DoorToDoorInputVO inputvo = new DoorToDoorInputVO();
			inputvo.setLevelIds(levelIdList);
			inputvo.setLevelValues(levelValuesList);
			inputvo.setStartDateStr(fromDate);
			inputvo.setEndDateStr(toDate);
			
			doorCampaignDashboardVOList = doorToDoorCampaignDashboardService.getDepartmentWiseGrievanceCounts(inputvo);
		}catch (Exception e) {
			LOG.error("Exception rised in getDepartmentWiseGrievanceCounts",e);
		}
		
		return Action.SUCCESS; 
	}
	public String getDepartmentIssueWiseGrievanceCounts(){
		try{
			jObj = new JSONObject(getTask());
			JSONArray jArray = jObj.getJSONArray("levelIds");
			List<Long> levelIdList = new ArrayList<Long>();    
			for (int i = 0; i < jArray.length(); i++){
				levelIdList.add(Long.parseLong(jArray.getString(i)));
			} 
			JSONArray levelValueArr = jObj.getJSONArray("levelValues");
			List<Long> levelValuesList = new ArrayList<Long>();    
			for (int i = 0; i < levelValueArr.length(); i++){
				levelValuesList.add(Long.parseLong(levelValueArr.getString(i)));
			}
			
			String fromDate = jObj.getString("startDateStr");
			String toDate = jObj.getString("endDateStr");
			DoorToDoorInputVO inputvo = new DoorToDoorInputVO();
			inputvo.setLevelIds(levelIdList);
			inputvo.setLevelValues(levelValuesList);
			inputvo.setStartDateStr(fromDate);
			inputvo.setEndDateStr(toDate);
			
			doorCampaignDashboardVOList = doorToDoorCampaignDashboardService.getDepartmentIssueWiseGrievanceCounts(inputvo);
		}catch (Exception e) {
			LOG.error("Exception rised in getDepartmentIssueWiseGrievanceCounts",e);
		}
		
		return Action.SUCCESS; 
	}
	
	public String getDepartmentSubIssueWiseGrievanceCounts(){
		try{
			jObj = new JSONObject(getTask());
			JSONArray jArray = jObj.getJSONArray("levelIds");
			List<Long> levelIdList = new ArrayList<Long>();    
			for (int i = 0; i < jArray.length(); i++){
				levelIdList.add(Long.parseLong(jArray.getString(i)));
			} 
			JSONArray levelValueArr = jObj.getJSONArray("levelValues");
			List<Long> levelValuesList = new ArrayList<Long>();    
			for (int i = 0; i < levelValueArr.length(); i++){
				levelValuesList.add(Long.parseLong(levelValueArr.getString(i)));
			}
			
			String fromDate = jObj.getString("startDateStr");
			String toDate = jObj.getString("endDateStr");
			DoorToDoorInputVO inputvo = new DoorToDoorInputVO();
			inputvo.setLevelIds(levelIdList);
			inputvo.setLevelValues(levelValuesList);
			inputvo.setStartDateStr(fromDate);
			inputvo.setEndDateStr(toDate);
			
			doorCampaignDashboardVOList = doorToDoorCampaignDashboardService.getDepartmentSubIssueWiseGrievanceCounts(inputvo);
		}catch (Exception e) {
			LOG.error("Exception rised in getDepartmentSubIssueWiseGrievanceCounts",e);
		}
		
		return Action.SUCCESS; 
	}
	
	public String getLevelWiseCount(){
		try{
			jObj = new JSONObject(getTask());
			JSONArray jArray = jObj.getJSONArray("levelIds");
			List<Long> levelIdList = new ArrayList<Long>();    
			for (int i = 0; i < jArray.length(); i++){
				levelIdList.add(Long.parseLong(jArray.getString(i)));
			} 
			JSONArray levelValueArr = jObj.getJSONArray("levelValues");
			List<Long> levelValuesList = new ArrayList<Long>();    
			for (int i = 0; i < levelValueArr.length(); i++){
				levelValuesList.add(Long.parseLong(levelValueArr.getString(i)));
			}
			
			String fromDate = jObj.getString("startDateStr");
			String toDate = jObj.getString("endDateStr");
			String locationType = jObj.getString("locationType");
			DoorToDoorInputVO inputvo = new DoorToDoorInputVO();
			inputvo.setLevelIds(levelIdList);
			inputvo.setLevelValues(levelValuesList);
			inputvo.setStartDateStr(fromDate);
			inputvo.setEndDateStr(toDate);
			inputvo.setLocationType(locationType);
			
			doorCampaignDashboardVO = doorToDoorCampaignDashboardService.getLevelWiseCount(inputvo);
		}catch (Exception e) {
			LOG.error("Exception rised in getLevelWiseCount",e);
		}
		
		return Action.SUCCESS; 
	}
	
	public String getCampaignCountFrMandalPancMuncip(){
		try{
			jObj = new JSONObject(getTask());
			JSONArray jArray = jObj.getJSONArray("levelIds");
			List<Long> levelIdList = new ArrayList<Long>();    
			for (int i = 0; i < jArray.length(); i++){
				levelIdList.add(Long.parseLong(jArray.getString(i)));
			} 
			JSONArray levelValueArr = jObj.getJSONArray("levelValues");
			List<Long> levelValuesList = new ArrayList<Long>();    
			for (int i = 0; i < levelValueArr.length(); i++){
				levelValuesList.add(Long.parseLong(levelValueArr.getString(i)));
			}
			
			String fromDate = jObj.getString("startDateStr");
			String toDate = jObj.getString("endDateStr");
			String locationType = jObj.getString("locationType");
			DoorToDoorInputVO inputvo = new DoorToDoorInputVO();
			inputvo.setLevelIds(levelIdList);
			inputvo.setLevelValues(levelValuesList);
			inputvo.setStartDateStr(fromDate);
			inputvo.setEndDateStr(toDate);
			inputvo.setLocationType(locationType);
			
			doorCampaignDashboardVO = doorToDoorCampaignDashboardService.getCampaignCountFrMandalPancMuncip(inputvo);
		}catch (Exception e) {
			LOG.error("Exception rised in getCampaignCountFrMandalPancMuncip",e);
		}
		
		return Action.SUCCESS; 
	}
	
	public String getUserAccessLevelIdsAndValues(){
		try{
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			DoorToDoorInputVO inputvo = new DoorToDoorInputVO();
			if(regVo != null)
				inputvo.setId(regVo.getRegistrationID());
			doorCampaignDashboardVO = doorToDoorCampaignDashboardService.getUserAccessLevelIdsAndValues(inputvo);
		}catch (Exception e) {
			LOG.error("Exception rised in getUserAccessLevelIdsAndValues",e);
		}
		
		return Action.SUCCESS; 
	}
	
	public String getUserWiseCountsFrLoginUser(){
		try{
			jObj = new JSONObject(getTask());
			//String locationType = jObj.getString("locationType");
			
			JSONArray jArray = jObj.getJSONArray("levelIds");
			List<Long> levelIdList = new ArrayList<Long>();    
			for (int i = 0; i < jArray.length(); i++){
				levelIdList.add(Long.parseLong(jArray.getString(i)));
			} 
			JSONArray levelValueArr = jObj.getJSONArray("levelValues");
			List<Long> levelValuesList = new ArrayList<Long>();    
			for (int i = 0; i < levelValueArr.length(); i++){
				levelValuesList.add(Long.parseLong(levelValueArr.getString(i)));
			}
			
			String fromDate = jObj.getString("startDateStr");
			String toDate = jObj.getString("endDateStr");
			DoorToDoorInputVO inputvo = new DoorToDoorInputVO();
			inputvo.setLevelIds(levelIdList);
			inputvo.setLevelValues(levelValuesList);
			inputvo.setStartDateStr(fromDate);
			inputvo.setEndDateStr(toDate);
			//inputvo.setLocationType(locationType);
			
			doorCampaignDashboardVOList = doorToDoorCampaignDashboardService.getUserWiseCountsFrLoginUser(inputvo);
		}catch (Exception e) {
			LOG.error("Exception rised in getRecentImagesList",e);
		}
		
		return Action.SUCCESS; 
	}
	
	public String getAssignedConstituenciesForUser(){
		try{
			jObj = new JSONObject(getTask());
			//String locationType = jObj.getString("locationType");
			
			JSONArray jArray = jObj.getJSONArray("levelIds");
			List<Long> levelIdList = new ArrayList<Long>();    
			for (int i = 0; i < jArray.length(); i++){
				levelIdList.add(Long.parseLong(jArray.getString(i)));
			} 
			JSONArray levelValueArr = jObj.getJSONArray("levelValues");
			List<Long> levelValuesList = new ArrayList<Long>();    
			for (int i = 0; i < levelValueArr.length(); i++){
				levelValuesList.add(Long.parseLong(levelValueArr.getString(i)));
			}
			
			//String fromDate = jObj.getString("startDateStr");
			//String toDate = jObj.getString("endDateStr");
			DoorToDoorInputVO inputvo = new DoorToDoorInputVO();
			inputvo.setLevelIds(levelIdList);
			inputvo.setLevelValues(levelValuesList);
			//inputvo.setStartDateStr(fromDate);
			//inputvo.setEndDateStr(toDate);
			//inputvo.setLocationType(locationType);
			
			doorCampaignDashboardVOList = doorToDoorCampaignDashboardService.getAssignedConstituenciesForUser(inputvo);
		}catch (Exception e) {
			LOG.error("Exception rised in getAssignedConstituenciesForUser",e);
		}
		
		return Action.SUCCESS; 
	}
}
