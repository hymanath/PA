package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AlertCommentVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertDataVO;
import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.dto.AlertOverviewVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.StatusTrackingVO;
import com.itgrids.partyanalyst.service.IAlertService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CreateAlertAction extends ActionSupport implements ServletRequestAware, ServletContextAware{
	private HttpSession session;
	private HttpServletRequest request;
	private String task = null;
	JSONObject jObj = null;	
	private IAlertService alertService;
	private InputStream inputStream;
	private String status;
	private AlertVO alertVO;
	
	private List<BasicVO> basicVO;
	private List<AlertDataVO> alertDataList;
	private  List<StatusTrackingVO> statusTrackingVOList;
	private ResultStatus resultStatus;
	private Long alertId;
	private List<IdNameVO> idNameVOList;
	private List<AlertVO> alertVOs;
	private List<AlertCommentVO> alertCommentVOs;
	private List<AlertCoreDashBoardVO> alertCoreDashBoardVOs;
	private AlertOverviewVO alertOverviewVO;
	private List<AlertOverviewVO> resultList;
	
	private List<File> imageForDisplay = new ArrayList<File>();
	private List<String> imageForDisplayContentType = new ArrayList<String>();
	private List<String> imageForDisplayFileName = new ArrayList<String>();
	private Long clarificationStatusId;
	private String clarificationComments;
	private String clarificationRadioName;
	
	
	
	public String getClarificationRadioName() {
		return clarificationRadioName;
	}

	public void setClarificationRadioName(String clarificationRadioName) {
		this.clarificationRadioName = clarificationRadioName;
	}

	public String getClarificationComments() {
		return clarificationComments;
	}

	public void setClarificationComments(String clarificationComments) {
		this.clarificationComments = clarificationComments;
	}

	public Long getClarificationStatusId() {
		return clarificationStatusId;
	}

	public void setClarificationStatusId(Long clarificationStatusId) {
		this.clarificationStatusId = clarificationStatusId;
	}

	public List<File> getImageForDisplay() {
		return imageForDisplay;
	}

	public void setImageForDisplay(List<File> imageForDisplay) {
		this.imageForDisplay = imageForDisplay;
	}

	public List<String> getImageForDisplayContentType() {
		return imageForDisplayContentType;
	}

	public void setImageForDisplayContentType(
			List<String> imageForDisplayContentType) {
		this.imageForDisplayContentType = imageForDisplayContentType;
	}

	public List<String> getImageForDisplayFileName() {
		return imageForDisplayFileName;
	}

	public void setImageForDisplayFileName(List<String> imageForDisplayFileName) {
		this.imageForDisplayFileName = imageForDisplayFileName;
	}

	public List<IdNameVO> getIdNameVOList() {
		return idNameVOList;
	}

	public void setIdNameVOList(List<IdNameVO> idNameVOList) {
		this.idNameVOList = idNameVOList;
	}

	public Long getAlertId() {
		return alertId;
	}

	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	private static final Logger LOG = Logger.getLogger(CreateAlertAction.class);
	
	
	
	public List<StatusTrackingVO> getStatusTrackingVOList() {
		return statusTrackingVOList;
	}

	public void setStatusTrackingVOList(List<StatusTrackingVO> statusTrackingVOList) {
		this.statusTrackingVOList = statusTrackingVOList;
	}

	public List<BasicVO> getBasicVO() {
		return basicVO;
	}

	public void setBasicVO(List<BasicVO> basicVO) {
		this.basicVO = basicVO;
	}
	
	public AlertVO getAlertVO() {
		return alertVO;
	}

	public void setAlertVO(AlertVO alertVO) {
		this.alertVO = alertVO;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
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

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public IAlertService getAlertService() {
		return alertService;
	}

	public void setAlertService(IAlertService alertService) {
		this.alertService = alertService;
	}

	
	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	
	
	

	public List<AlertDataVO> getAlertDataList() {
		return alertDataList;
	}

	public void setAlertDataList(List<AlertDataVO> alertDataList) {
		this.alertDataList = alertDataList;
	}
	
	public List<AlertVO> getAlertVOs() {
		return alertVOs;
	}

	public void setAlertVOs(List<AlertVO> alertVOs) {
		this.alertVOs = alertVOs;
	}
	
	public List<AlertCommentVO> getAlertCommentVOs() {
		return alertCommentVOs;
	}

	public void setAlertCommentVOs(List<AlertCommentVO> alertCommentVOs) {
		this.alertCommentVOs = alertCommentVOs;
	}
	
	public List<AlertCoreDashBoardVO> getAlertCoreDashBoardVOs() {
		return alertCoreDashBoardVOs;
	}
    public AlertOverviewVO getAlertOverviewVO() {
		return alertOverviewVO;
	}

	public void setAlertOverviewVO(AlertOverviewVO alertOverviewVO) {
		this.alertOverviewVO = alertOverviewVO;
	}
	public List<AlertOverviewVO> getResultList() {
		return resultList;
	}
	public void setAlertCoreDashBoardVOs(
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs) {
		this.alertCoreDashBoardVOs = alertCoreDashBoardVOs;
	}
	public void setResultList(List<AlertOverviewVO> resultList) {
		this.resultList = resultList;
	}

	public String execute()
	{
		session = request.getSession();
		RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
		if(regVo.getEntitlements() != null && regVo.getEntitlements().contains(IConstants.CREATE_ALERT_ENTITLEMENT) || 
				 regVo.getEntitlements().contains("ALERT_DASHBOARD_USER_ENTITLEMENT") || 
				  regVo.getEntitlements().contains("ALERT_DASHBOARD_ADMIN_ENTITLEMENT"))  
			return Action.SUCCESS;
		else
			return Action.ERROR;	
	}
	public String alertDashboardExe()
	{
		session = request.getSession();
		RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
		if(regVo.getEntitlements() != null && regVo.getEntitlements().contains("ALERT_DASHBOARD_USER_ENTITLEMENT") || 
				 regVo.getEntitlements().contains("ALERT_DASHBOARD_ADMIN_ENTITLEMENT"))  
			return Action.SUCCESS;
		else
			return Action.ERROR;  	
	}
	
	public String getCandidatesByName(){
		try{
			jObj = new JSONObject(getTask());
			basicVO = alertService.getCandidatesByName(jObj.getString("CandidateName"));
			}
		catch(Exception e){
			LOG.error("Exception Raised in getCandidatesByName() -- createAlertAction" + e); 
		}
		return Action.SUCCESS;
		
	}
	
	public String createAlert()
	{
		try{
		session = request.getSession();
		RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
		status = alertService.createAlert(alertVO,regVo.getRegistrationID());
		inputStream = new StringBufferInputStream(status);
			
		}
		catch (Exception e) {
			LOG.error("Exception rised in raiseComplaint",e);
		}
		return Action.SUCCESS;	
	}
	
	public String getLocationLevelWiseAlerts()
	{
		try{
			session = request.getSession();


			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			jObj = new JSONObject(getTask());
			String fromDate=jObj.getString("fromDate");
			String toDate=jObj.getString("toDate");
			basicVO = alertService.getLocationLevelWiseAlerts(regVo.getRegistrationID(),fromDate,toDate);
			
		}
		catch (Exception e) {
			LOG.error("Exception rised in getLocationLevelWiseAlerts",e);
		}
		return Action.SUCCESS;	
	}
	public String getLocationLevelWiseAlertsData()
	{
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			AlertInputVO inputVO = new AlertInputVO();
			inputVO.setLevelId(jObj.getLong("levelId"));
			inputVO.setStatusId(jObj.getLong("statusId"));
			inputVO.setFromDate(jObj.getString("fromDate"));
			inputVO.setToDate(jObj.getString("toDate")); 
			
			inputVO.setLevelValue(jObj.getLong("levelValue"));
			inputVO.setCategoryId(jObj.getLong("categoryId"));
			inputVO.setAssignId(jObj.getLong("assignId"));
			inputVO.setSearchTypeStr(jObj.getString("task"));
			inputVO.setAlertTypeId(jObj.getLong("alertTypeId"));
			inputVO.setAlertImpactScopeId(jObj.getLong("impactScopeId"));
			
			alertDataList = alertService.getLocationLevelWiseAlertsData(regVo.getRegistrationID(),inputVO);
			
		}
		catch (Exception e) {
			LOG.error("Exception rised in getLocationLevelWiseAlertsData",e);
		}
		return Action.SUCCESS;	
	}
	
	public String getLocationFilterAlertData()
	{
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			LocationVO inputVO = new LocationVO();
			inputVO.setFromDate(jObj.getString("fromDate"));
			inputVO.setToDate(jObj.getString("toDate"));
			inputVO.setStateId(jObj.getLong("stateId"));
			inputVO.setDistrictId(jObj.getLong("districtId"));
			inputVO.setConstituencyId(jObj.getLong("constituencyId"));
			inputVO.setTehsilId(jObj.getLong("mandalId"));
			inputVO.setVillageId(jObj.getLong("panchayatId"));
			inputVO.setLocationType(jObj.getString("mandalType"));
			inputVO.setId(jObj.getLong("alertTypeId"));
			inputVO.setStatusId(jObj.getLong("statusId"));
			inputVO.setCategoryId(jObj.getLong("categoryId"));
			
			alertDataList = alertService.getLocationWiseFilterAlertData(regVo.getRegistrationID(),inputVO,jObj.getLong("assignedCadreId"));
			
		}
		catch (Exception e) {
			LOG.error("Exception rised in getLocationFilterAlertData",e);
		}
		return Action.SUCCESS;	
	}
	
	
	public String getAlertsData()
	{
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			alertDataList = alertService.getAlertsData(jObj.getLong("alertId"));
			
		}
		catch (Exception e) {
			LOG.error("Exception rised in getLocationLevelWiseAlertsData",e);
		}
		return Action.SUCCESS;	
	}
	
	public String getAlertCandidatesData()
	{
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			
			alertDataList = alertService.getAlertCandidatesData(jObj.getLong("alertId"));
			
		}
		catch (Exception e) {
			LOG.error("Exception rised in getLocationLevelWiseAlertsData",e);
		}
		return Action.SUCCESS;	
	}
	

	public String updateAlertStatus()
	{
		try{
			session = request.getSession();

			
			jObj = new JSONObject(getTask());
			AlertVO inputVO = new AlertVO();
			inputVO.setId(jObj.getLong("alertId"));
			inputVO.setDesc(jObj.getString("comments"));
			inputVO.setStatusId(jObj.getLong("alertStatusId"));
			
			JSONArray arr = jObj.getJSONArray("tdpCadreId");
			
			List<IdNameVO>  assignCadreIds = new ArrayList<IdNameVO>();
			for(int i=0;i<arr.length();i++)
			{
				IdNameVO vo = new IdNameVO();
				vo.setId((Long.parseLong(arr.getString(i))));
				assignCadreIds.add(vo);
			}
			inputVO.setAssignList(assignCadreIds);
			//inputVO.setTdpCadreId(jObj.getLong("tdpCadreId"));
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			status = alertService.updateAlertStatus(regVo.getRegistrationID(),inputVO);
		}
		catch (Exception e) {
			LOG.error("Exception rised in UpdateAlertStatus",e);
		}
		return Action.SUCCESS;	
	}
	public String getAlertStatusCommentsTrackingDetails(){
		try{
			
			jObj = new JSONObject(getTask());
			Long alertId = jObj.getLong("alertId");
			
			alertCommentVOs = alertService.getAlertStatusCommentsTrackingDetails(alertId);
			
		}catch(Exception e) {
			LOG.error("Exception occured in getAlertStatusCommentsTrackingDetails() of AppointmentAction",e);
		}
		return Action.SUCCESS;  
	}
	public String getAlertType(){
		try{
			
			jObj = new JSONObject(getTask());
			
			basicVO = alertService.getAlertType();
			
		}catch(Exception e) {
			LOG.error("Exception occured in getAlertType()",e);
		}
		return Action.SUCCESS;
	}
	public String getAlertSourceForUser(){
		try{
			
			jObj = new JSONObject(getTask());
			
			basicVO = alertService.getAlertSourceForUser(jObj.getLong("userId"));
			
		}catch(Exception e) {
			LOG.error("Exception occured in getAlertSourceForUser()",e);
		}
		return Action.SUCCESS;
	}
	
	public String saveAlertAssignedUser()
	{
		try{
		session = request.getSession();
		RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
		jObj = new JSONObject(getTask());
		//List<Long> ids = (List<Long>) jObj.getJSONArray("tdpCadreIds");
		JSONArray jArray = jObj.getJSONArray("tdpCadreIds");
		List<IdNameVO> cadreList = new ArrayList<IdNameVO>();
		 for (int i = 0; i < jArray.length(); i++) 
			{
			 IdNameVO vo = new IdNameVO();
			 vo.setId((Long.parseLong(jArray.getString(i))));
			 cadreList.add(vo);
			}
		AlertVO alertVO = new AlertVO();
		alertVO.setIdNamesList(cadreList);
		alertVO.setAlertTypeId(jObj.getLong("alertId"));
		resultStatus = alertService.saveAlertAssignedUser(alertVO,regVo.getRegistrationID());
		}
		catch (Exception e) {
			LOG.error("Exception rised in saveAlertAssignedUser",e);
		}
		return Action.SUCCESS;	
	}
	
	public String getMemberTypes()
	{
		try{
		
			idNameVOList = alertService.getMemberTypesList();
		}
		catch (Exception e) {
			LOG.error("Exception rised in getMemberTypesList",e);
		}
		return Action.SUCCESS;	
	}
	public String getAlertAssignedCandidates()
	{
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			
			alertDataList = alertService.getAlertAssignedCandidates(jObj.getLong("alertId"));
			
		}
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception rised in getAlertAssignedCandidates",e);
		}
		return Action.SUCCESS;	
	}
	public String deleteAlertAssignedCandidates()
	{
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			status = alertService.deleteAlertAssignedCandidates(jObj.getLong("alertId"),jObj.getLong("tdpCadreId"));
		}
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception rised in deleteAlertAssignedCandidates",e);
		}
		return Action.SUCCESS;	
	}
	public String getAlertAssignedCandidate(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long alertId = jObj.getLong("alertId");
			statusTrackingVOList = alertService.getAlertAssignedCandidate(alertId);
		}catch(Exception e) {
			LOG.error("Exception occured in getAlertAssignedCandidate() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getTotalAlertGroupByStatus(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long alertyTypeId = jObj.getLong("alertyTypeId");
			alertVOs = alertService.getTotalAlertGroupByStatus(fromDate, toDate, stateId,alertyTypeId);
		}catch(Exception e) {
			LOG.error("Exception occured in getTotalAlertGroupByStatus() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getTotalAlertGroupByStatusThenCategory(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long alertyTypeId = jObj.getLong("alertyTypeId");
			alertVOs = alertService.getTotalAlertGroupByStatusThenCategory(fromDate, toDate, stateId,alertyTypeId);
		}catch(Exception e) {
			LOG.error("Exception occured in getTotalAlertGroupByStatus() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getAlertCountGroupByLocationThenStatus(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long alertyTypeId = jObj.getLong("alertyTypeId");
			alertVOs = alertService.getAlertCountGroupByLocationThenStatus(fromDate, toDate, stateId,alertyTypeId);
		}catch(Exception e) {
			LOG.error("Exception occured in getTotalAlertGroupByStatus() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getTotalAlertGroupByStatusThenCategoryLocationWise(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			String Location = jObj.getString("Location"); 
			Long alertyTypeId = jObj.getLong("alertyTypeId");
			alertVOs = alertService.getTotalAlertGroupByStatusThenCategoryLocationWise(fromDate, toDate, stateId, Location,alertyTypeId);
		}catch(Exception e) {
			LOG.error("Exception occured in getTotalAlertGroupByStatusThenCategoryLocationWise() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getTotalAlertGroupByLocationThenCategory(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			String group = jObj.getString("group");
			Long activityMemberId = jObj.getLong("activityMemberId");
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			List<Long> scopeIdList = new ArrayList<Long>();    
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}  
			alertVOs = alertService.getTotalAlertGroupByLocationThenCategory(fromDate, toDate, stateId, scopeIdList, activityMemberId,group);    
		}catch(Exception e) {  
			LOG.error("Exception occured in getTotalAlertGroupByStatusThenCategoryLocationWise() of CreateAlertAction",e);
		}
		return Action.SUCCESS;    
	}
	public String getTotalAlertGroupByLocationThenStatus(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			String group = jObj.getString("group");
			Long activityMemberId = jObj.getLong("activityMemberId");
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}  
			alertVOs = alertService.getTotalAlertGroupByLocationThenStatus(fromDate, toDate, stateId, scopeIdList, activityMemberId,group);    
		}catch(Exception e) {  
			LOG.error("Exception occured in getTotalAlertGroupByStatusThenCategoryLocationWise() of CreateAlertAction",e);
		}
		return Action.SUCCESS;  
	}
	public String getAlertOverviewDetails(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long alertType = jObj.getLong("alertType");
			Long editionType = jObj.getLong("editionType");
			alertOverviewVO = alertService.getAlertOverviewDetails(activityMemberId,stateId,fromDate,toDate,alertType,editionType);    
		}catch(Exception e) {  
			LOG.error("Exception occured in getAlertOverviewDetails() of CreateAlertAction",e);    
		}
		return Action.SUCCESS;  
	}
	public String getAlertCategoryDtlsLocationWise(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long activityMemberId = jObj.getLong("activityMemberId");
			resultList = alertService.getAlertCategoryDtlsLocationWise(activityMemberId,stateId,fromDate,toDate);    
		}catch(Exception e) {  
			LOG.error("Exception occured in getAlertCategoryDtlsLocationWise() of CreateAlertAction",e);
		}
		return Action.SUCCESS;  
	}
	public String getTotalAlertGroupByDist(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long activityMemberId = jObj.getLong("activityMemberId");
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}  
			alertCommentVOs = alertService.getTotalAlertGroupByDist(fromDate, toDate, stateId, scopeIdList, activityMemberId);   
		}catch(Exception e) {  
			LOG.error("Exception occured in getTotalAlertGroupByStatusThenCategoryLocationWise() of CreateAlertAction",e);
		}
		return Action.SUCCESS;  
	}
	public String getAlertDtls(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			Long alertTypeId = jObj.getLong("alertTypeId");
			Long alertStatusId = jObj.getLong("alertStatusId");
			Long alertCategoryId = jObj.getLong("alertCategoryId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");  
			Long activityMemberId = jObj.getLong("activityMemberId");   
			
			alertCoreDashBoardVOs = alertService.getAlertDtls(fromDate, toDate, stateId, alertTypeId, alertStatusId, alertCategoryId, activityMemberId);   
		}catch(Exception e) {  
			LOG.error("Exception occured in getTotalAlertGroupByStatusThenCategoryLocationWise() of CreateAlertAction",e);
		}
		return Action.SUCCESS;  
	}
	public String getAlertImpactScope(){
		try{
			
			jObj = new JSONObject(getTask());
			
			basicVO = alertService.getAlertImpactScope();
			
		}catch(Exception e) {
			LOG.error("Exception occured in getAlertImpactScope()",e);
		}
		return Action.SUCCESS;
	}
	public String getAssignGroupTypeAlertDtlsByImpactLevelWise(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long activityMemberId = jObj.getLong("activityMemberId");
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}  
			resultList = alertService.getAssignGroupTypeAlertDtlsByImpactLevelWise(activityMemberId,stateId,fromDate,toDate,scopeIdList);   
		}catch(Exception e) {  
			LOG.error("Exception occured in getAssignGroupTypeAlertDtlsByImpactLevelWise() of CreateAlertAction",e);
		}
		return Action.SUCCESS;  
	}
	public String getTotalAlertGroupByPubRepThenStatus(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long publicRepresentativeTypeId = jObj.getLong("publicRepresentativeTypeId");
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}
			
			JSONArray commitLvlIdArr = jObj.getJSONArray("commitLvlIdArr");
			List<Long> commitLvlIdList = new ArrayList<Long>();
			for (int i = 0; i < commitLvlIdArr.length(); i++){
				commitLvlIdList.add(Long.parseLong(commitLvlIdArr.getString(i)));  
			}
			String groupAssignType = jObj.getString("groupAssignType");
			if(groupAssignType.equalsIgnoreCase("Party Committee")){
				alertVOs = alertService.getTotalAlertGroupByPubRepThenStatus(fromDate, toDate, stateId, scopeIdList, activityMemberId, publicRepresentativeTypeId,commitLvlIdList,groupAssignType,"",null);
			}else{
				alertVOs = alertService.getTotalAlertGroupByPubRepThenStatus(fromDate, toDate, stateId, scopeIdList, activityMemberId, publicRepresentativeTypeId,null,groupAssignType,"",null);
			}
			   
		}catch(Exception e) {  
			LOG.error("Exception occured in getTotalAlertGroupByStatusThenCategoryLocationWise() of CreateAlertAction",e);
		}
		return Action.SUCCESS;    
	}
	public String getDesigWiseTdpCommitAlertCount(){  
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long designationId = jObj.getLong("designationId");
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}
			
			JSONArray commitLvlIdArr = jObj.getJSONArray("commitLvlIdArr");
			List<Long> commitLvlIdList = new ArrayList<Long>();
			for (int i = 0; i < commitLvlIdArr.length(); i++){
				commitLvlIdList.add(Long.parseLong(commitLvlIdArr.getString(i)));  
			}
			String groupAssignType = jObj.getString("groupAssignType");
			String position = jObj.getString("position");
			alertVOs = alertService.getTotalAlertGroupByPubRepThenStatus(fromDate, toDate, stateId, scopeIdList, activityMemberId, null,commitLvlIdList,groupAssignType,position,designationId);
			
		}catch(Exception e) {  
			LOG.error("Exception occured in getTotalAlertGroupByStatusThenCategoryLocationWise() of CreateAlertAction",e);
		}
		return Action.SUCCESS;    
	}
	public String updateCandidateStatusOfAlert(){
		try{
			
			jObj = new JSONObject(getTask());
			
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			
			status = alertService.updateCandidateStatusOfAlert(jObj.getLong("alertId"),regVo.getRegistrationID());
			
		}catch(Exception e) {
			LOG.error("Exception occured in updateCandidateStatusOfAlert()",e);
		}
		return Action.SUCCESS;	
	}
	public String getOtherAndPrgrmCmmtteeTypeAlertCndtDtls(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long activityMemberId = jObj.getLong("activityMemberId");
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			String resultType = jObj.getString("resultType");
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}  
			resultList = alertService.getOtherAndPrgrmCmmtteeTypeAlertCndtDtls(activityMemberId,stateId,fromDate,toDate,scopeIdList,resultType);   
		}catch(Exception e) {  
			LOG.error("Exception occured in getOtherAndPrgrmCmmtteeTypeAlertCndtDtls() of CreateAlertAction",e);
		}
		return Action.SUCCESS;  
	}
	public String getStateImpactLevelAlertDtlsCnt(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long activityMemberId = jObj.getLong("activityMemberId");
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}  
			alertOverviewVO = alertService.getStateImpactLevelAlertDtlsCnt(activityMemberId,stateId,fromDate,toDate,scopeIdList);   
		}catch(Exception e) {  
			LOG.error("Exception occured in getStateImpactLevelAlertDtlsCnt() of CreateAlertAction",e);
		}
		return Action.SUCCESS;  
	}
	public String getAlertDtlsForPubRep(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long publicRepresentativeTypeId = jObj.getLong("publicRepresentativeTypeId");
			Long cadreId = jObj.getLong("cadreId");
			Long statusId = jObj.getLong("statusId");
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}   
			alertCoreDashBoardVOs = alertService.getAlertDtlsForPubRep(fromDate,toDate,stateId,scopeIdList,activityMemberId,publicRepresentativeTypeId,cadreId,statusId);   
		}catch(Exception e) {    
			LOG.error("Exception occured in getAlertDtlsForPubRep() of CreateAlertAction",e);
		}
		return Action.SUCCESS;    
	}
	public String getAlertDetailsTdpCadreWise(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long cadreId = jObj.getLong("cadreId");
			Long statusId = jObj.getLong("statusId");
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}   
			String resultType = jObj.getString("resultType");
			alertCoreDashBoardVOs = alertService.getAlertDetailsTdpCadreWise(fromDate,toDate,stateId,scopeIdList,activityMemberId,cadreId,statusId,resultType);   
		}catch(Exception e) {    
			LOG.error("Exception occured in getAlertDetailsTdpCadreWise() of CreateAlertAction",e);
		}
		return Action.SUCCESS;    
	}
	public String getDistrictAndStateImpactLevelWiseAlertDtls(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long districtId = jObj.getLong("districtId");
			Long catId = jObj.getLong("catId");
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}   
			alertCoreDashBoardVOs = alertService.getDistrictAndStateImpactLevelWiseAlertDtls(fromDate,toDate,stateId,scopeIdList,activityMemberId,districtId,catId);   
		}catch(Exception e) {    
			LOG.error("Exception occured in getDistrictAndStateImpactLevelWiseAlertDtls() of CreateAlertAction",e);
		}
		return Action.SUCCESS;    
	}
	public String getMemForPartyCommitDesg(){    
		try{
			session = request.getSession(); 
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long commitTypeId = jObj.getLong("commitTypeId");
			Long designationId = jObj.getLong("designationId");
			
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}
			
			JSONArray commitLvlIdArr = jObj.getJSONArray("commitLvlIdArr");
			List<Long> commitLvlIdList = new ArrayList<Long>();
			for (int i = 0; i < commitLvlIdArr.length(); i++){
				commitLvlIdList.add(Long.parseLong(commitLvlIdArr.getString(i)));    
			}
			alertVOs = alertService.getMemForPartyCommitDesg(fromDate,toDate,stateId,scopeIdList,activityMemberId,commitLvlIdList,commitTypeId,designationId);   
		}catch(Exception e) {      
			LOG.error("Exception occured in getAlertDtlsForPubRep() of CreateAlertAction",e);  
		}
		return Action.SUCCESS;    
	}
	public String getAlertDtlsAssignedByPartyCommite(){    
		try{
			session = request.getSession(); 
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long commitTypeId = jObj.getLong("commitTypeId");
			Long designationId = jObj.getLong("designationId");
			Long cadreId = jObj.getLong("cadreId");
			Long statusId = jObj.getLong("statusId");  
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}
			
			JSONArray commitLvlIdArr = jObj.getJSONArray("commitLvlIdArr");  
			List<Long> commitLvlIdList = new ArrayList<Long>();
			for (int i = 0; i < commitLvlIdArr.length(); i++){
				commitLvlIdList.add(Long.parseLong(commitLvlIdArr.getString(i)));        
			}      
			alertCoreDashBoardVOs = alertService.getAlertDtlsAssignedByPartyCommite(fromDate,toDate,stateId,scopeIdList,activityMemberId,commitLvlIdList,commitTypeId,designationId,cadreId,statusId);   
		}catch(Exception e) {        
			LOG.error("Exception occured in getAlertDtlsForPubRep() of CreateAlertAction",e);  
		}
		return Action.SUCCESS;    
	}
	public String getAlertLastUpdatedTime(){
		try{
			status = alertService.getAlertLastUpdatedTime();
		}catch(Exception e){
			LOG.error("Exception raised at getAlertLastUpdatedTime() method of CoreDashBoard", e);	
		}
		return Action.SUCCESS;
	}
	public String getCadreAlertDetails(){
		return Action.SUCCESS;
	}
	
	public String alertClarificationDashboard(){
		try {
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			if(regVo.getEntitlements() != null &&
				(regVo.getEntitlements().contains("ALERT_CLARIFICATION_DASHBOARD_ENTITLEMENT") || regVo.getEntitlements().contains("ALERT_CLARIFICATION_DASHBOARD_ADMIN_ENTITLEMENT")) ) 
				return Action.SUCCESS;
			else
				return Action.ERROR;
		} catch (Exception e) {
			LOG.error("Exception occured in alertClarificationDashboard() of CreateAlertAction",e);  
		}
		return Action.SUCCESS;
	}
	/*public String getAlertClarificationStatus(){
		try{
			jObj = new JSONObject(getTask());
			alertVOs = alertService.getAlertClarificationStatus(jObj.getLong("alertId"));
		}catch(Exception e){
			LOG.error("Exception raised at getAlertClarificationStatus() method of CoreDashBoard", e);	
		}
		return Action.SUCCESS;
	}*/
	
	public String uploadAlertsDoc(){
		try{
			 String imageName=null;
			 
			 RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			 
			 if(regVO!=null){
				 Long userId = regVO.getRegistrationID();
				 
				 List<String> fileNamesList = new ArrayList<String>(0);
				 
				 for(int i=0;i<imageForDisplay.size();i++){
		        	  String fileType = imageForDisplayContentType.get(i).substring(imageForDisplayContentType.get(i).indexOf("/")+1, imageForDisplayContentType.get(i).length());
			        	 
			          imageName= UUID.randomUUID().toString()+"_"+imageForDisplayFileName.get(i);
			          fileNamesList.add(IConstants.TOUR_DOCUMENTS+"/"+imageName);
			          
			          String filePath=IConstants.STATIC_CONTENT_FOLDER_PATH+"/"+IConstants.TOUR_DOCUMENTS;
			        	 
			          File fileToCreate = new File(filePath,imageName);
					  FileUtils.copyFile(imageForDisplay.get(i), fileToCreate);
					  
					  inputStream = new StringBufferInputStream("success");
		          }
				 alertService.saveAlertClarificationDetails(userId,alertId,clarificationStatusId,clarificationComments,clarificationRadioName,fileNamesList);
			 }else{
				 inputStream = new StringBufferInputStream("login failed");
			 }
		      
		}catch(Exception e){
			inputStream = new StringBufferInputStream("failed");
			LOG.error(e);
		}
		
		return Action.SUCCESS;	         
	}
	
}//public List<AlertVO> getMemForPartyCommitDesg(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId,List<Long> commitLvlIdArr,Long commitTypeId,Long designationId);