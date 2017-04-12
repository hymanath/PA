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
import java.util.regex.Pattern;

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

import com.itgrids.partyanalyst.dto.AlertClarificationVO;
import com.itgrids.partyanalyst.dto.AlertCommentVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertDataVO;
import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.dto.AlertOverviewVO;
import com.itgrids.partyanalyst.dto.AlertTrackingVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.AlertVerificationVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.ClarificationDetailsCountVO;
import com.itgrids.partyanalyst.dto.GovtDepartmentVO;
import com.itgrids.partyanalyst.dto.GrievanceAlertVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.StatusTrackingVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.IAlertService;
import com.itgrids.partyanalyst.service.ICccDashboardService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CreateAlertAction extends ActionSupport implements ServletRequestAware, ServletContextAware{
	private HttpSession session;
	private HttpServletRequest request;
	private String task = null;
	JSONObject jObj = null;	
	private IAlertService alertService;
	private ICccDashboardService cccDashboardService;
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
	private AlertClarificationVO alertClarificationVO;
	private List<ClarificationDetailsCountVO> clarificationDetailsCountVOList;
	private AlertVerificationVO alertVerificationVO;
	private List<AlertVerificationVO> alertVerificationList;
	private List<KeyValueVO> keyValueVOList = new ArrayList<KeyValueVO>(0);
	private List<Long> cadreIds=new ArrayList<Long>(0);
	private List<UserTypeVO> activityMembersList;
	private GrievanceAlertVO grievanceAlertVO;
	private List<AlertTrackingVO> alertTrackingVOList;
	private List<GovtDepartmentVO> govtDeptVoList = new ArrayList<GovtDepartmentVO>(0);
	
	
	public List<GovtDepartmentVO> getGovtDeptVoList() {
		return govtDeptVoList;
	}

	public void setGovtDeptVoList(List<GovtDepartmentVO> govtDeptVoList) {
		this.govtDeptVoList = govtDeptVoList;
	}

	public List<AlertTrackingVO> getAlertTrackingVOList() {
		return alertTrackingVOList;
	}

	public void setAlertTrackingVOList(List<AlertTrackingVO> alertTrackingVOList) {
		this.alertTrackingVOList = alertTrackingVOList;
	}

	public GrievanceAlertVO getGrievanceAlertVO() {
		return grievanceAlertVO;
	}

	public void setGrievanceAlertVO(GrievanceAlertVO grievanceAlertVO) {
		this.grievanceAlertVO = grievanceAlertVO;
	}

	public ICccDashboardService getCccDashboardService() {
		return cccDashboardService;
	}

	public void setCccDashboardService(ICccDashboardService cccDashboardService) {
		this.cccDashboardService = cccDashboardService;
	}

	public List<Long> getCadreIds() {
		return cadreIds;
	}

	public void setCadreIds(List<Long> cadreIds) {
		this.cadreIds = cadreIds;
	}

	public List<KeyValueVO> getKeyValueVOList() {
		return keyValueVOList;
	}

	public void setKeyValueVOList(List<KeyValueVO> keyValueVOList) {
		this.keyValueVOList = keyValueVOList;
	}

	public List<ClarificationDetailsCountVO> getClarificationDetailsCountVOList() {
		return clarificationDetailsCountVOList;
	}

	public void setClarificationDetailsCountVOList(
			List<ClarificationDetailsCountVO> clarificationDetailsCountVOList) {
		this.clarificationDetailsCountVOList = clarificationDetailsCountVOList;
	}

	public AlertClarificationVO getAlertClarificationVO() {
		return alertClarificationVO;
	}

	public void setAlertClarificationVO(AlertClarificationVO alertClarificationVO) {
		this.alertClarificationVO = alertClarificationVO;
	}

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
    public List<AlertVerificationVO> getAlertVerificationList() {
		return alertVerificationList;
	}

	public void setAlertVerificationList(
			List<AlertVerificationVO> alertVerificationList) {
		this.alertVerificationList = alertVerificationList;
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
   public AlertVerificationVO getAlertVerificationVO() {
		return alertVerificationVO;
	}

	public void setAlertVerificationVO(AlertVerificationVO alertVerificationVO) {
		this.alertVerificationVO = alertVerificationVO;
	}
   public List<UserTypeVO> getActivityMembersList() {
		return activityMembersList;
	}

	public void setActivityMembersList(List<UserTypeVO> activityMembersList) {
		this.activityMembersList = activityMembersList;
	}

	public String execute()	{
		session = request.getSession();
		RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
		if(regVo.getEntitlements() != null && regVo.getEntitlements().contains(IConstants.CREATE_ALERT_ENTITLEMENT) || 
				 regVo.getEntitlements().contains("ALERT_DASHBOARD_USER_ENTITLEMENT") || 
				  regVo.getEntitlements().contains("ALERT_DASHBOARD_ADMIN_ENTITLEMENT") ||
				  regVo.getEntitlements().contains("TDP_CADRE_LOGIN_ENTITLEMENT"))  
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
			Map<File,String> mapfiles = new HashMap<File,String>();
			MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
			Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
			int i = 0;
			while(fileParams.hasMoreElements()){
				String key = fileParams.nextElement();
				File[] files = multiPartRequestWrapper.getFiles(key);
				if(files != null && files.length > 0){
					for(File f : files){
						String fileName  =multiPartRequestWrapper.getFileNames(key)[i];
						//fileName = StringEscapeUtils.escapeJava(fileName);
						mapfiles.put(f,fileName);  
						i++;
					}
				}
			}
			if(alertVO.getAlertId() != null && alertVO.getAlertId().longValue() > 0L){
				status = alertService. editAlert(alertVO,regVo.getRegistrationID(),mapfiles);
			}else{
				status = alertService.createAlert(alertVO,regVo.getRegistrationID(),mapfiles);
			}
			
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
			inputVO.setActionTypeStatusId(jObj.getLong("actionTypeStatusId")); 
			inputVO.setTask(jObj.getString("task"));
			inputVO.setFromDate2(jObj.getString("fromDate2"));
			inputVO.setToDate2(jObj.getString("toDate2"));
			alertDataList = alertService.getLocationWiseFilterAlertData(regVo.getRegistrationID(),inputVO,jObj.getLong("assignedCadreId"),jObj.getLong("involvedCadreId"),jObj.getLong("impactId"));
			
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
			LOG.error("Exception rised in getAlertsData",e);
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
			LOG.error("Exception rised in getAlertCandidatesData",e);
		}
		return Action.SUCCESS;	
	}
	

	/*public String updateAlertStatus()
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
	}*/
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
	public String getAlertAssignedCandidateForDashboard(){     
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long alertId = jObj.getLong("alertId");
			Long stateId = jObj.getLong("stateId");
			Long alertTypeId = jObj.getLong("alertTypeId");
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			statusTrackingVOList = alertService.getAlertAssignedCandidateForDashBoard(alertId,stateId,alertTypeId,fromDateStr,toDateStr);
		}catch(Exception e) {
			LOG.error("Exception occured in getAlertAssignedCandidate() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	//public List<StatusTrackingVO> getAlertInvolvedCandidate(Long alertId)
	public String getAlertInvolvedCandidate(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long cadreId = jObj.getLong("cadreId");
			Long stateId = jObj.getLong("stateId");
			Long alertTypeId = jObj.getLong("alertTypeId");
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			statusTrackingVOList = alertService.getAlertInvolvedCandidate(cadreId,stateId,alertTypeId,fromDateStr,toDateStr);        
		}catch(Exception e) {  
			LOG.error("Exception occured in getAlertAssignedCandidate() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getTotalAlertGroupByStatus(){
		try{
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
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
			Long activityMemberId = jObj.getLong("activityMemberId");//aaaaa
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			List<Long> scopeIdList = new ArrayList<Long>();    
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}  
			Long alertId = jObj.getLong("alertIds");
			Long editionId = jObj.getLong("editionIds");
			alertVOs = alertService.getTotalAlertGroupByLocationThenCategory(fromDate, toDate, stateId, scopeIdList, activityMemberId,group,alertId,editionId);    
		}catch(Exception e) {  
			LOG.error("Exception occured in getTotalAlertGroupByLocationThenCategory() of CreateAlertAction",e);
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
			Long alertId = jObj.getLong("alertIds");
			Long editionId = jObj.getLong("editionIds");
			String filterType = jObj.getString("filterType");
			Long locationValue = jObj.getLong("locationValue");
			List<Long> alertStatusIds = new ArrayList<Long>(0);
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			String sortingType = jObj.getString("sortingType");
			Long disctrictId = jObj.getLong("districtId");
			resultList = alertService.getTotalAlertGroupByLocationThenStatus(fromDate, toDate, stateId, scopeIdList, activityMemberId,group,alertId,editionId,filterType,locationValue,alertStatusIds,sortingType,disctrictId);    
		}catch(Exception e) {  
			LOG.error("Exception occured in getTotalAlertGroupByLocationThenStatus() of CreateAlertAction",e);
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
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}
			List<Long> alertStatusIds = new ArrayList<Long>(0);
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			alertOverviewVO = alertService.getAlertOverviewDetails(activityMemberId,stateId,fromDate,toDate,alertType,editionType,scopeIdList,alertStatusIds);    
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
			Long alertId = jObj.getLong("alertIds");
			Long editionId = jObj.getLong("editionIds");
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}  
			List<Long> alertStatusIds = new ArrayList<Long>();
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			resultList = alertService.getAlertCategoryDtlsLocationWise(activityMemberId,stateId,fromDate,toDate,alertId,editionId,scopeIdList,alertStatusIds);    
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
			Long alertId = jObj.getLong("alertIds");
			Long editionId = jObj.getLong("editionIds");
			alertCommentVOs = alertService.getTotalAlertGroupByDist(fromDate, toDate, stateId, scopeIdList, activityMemberId,alertId,editionId);   
		}catch(Exception e) {  
			LOG.error("Exception occured in getTotalAlertGroupByDist() of CreateAlertAction",e);
		}
		return Action.SUCCESS;  
	}
	public String getAlertDtls(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			Long alertTypeId = jObj.getLong("alertTypeId");
			List<Long> alertStatusIds = new ArrayList<Long>();
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			Long alertCategoryId = jObj.getLong("alertCategoryId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");  
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long editionIds = jObj.getLong("editionIds"); 
			String isActionType = jObj.getString("isActionType");
			Long alertActionTypeId = jObj.getLong("actionTypeId");
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}
			Long alertVerificationStatusId = jObj.getLong("alertVerificationStatusId");
			alertCoreDashBoardVOs = alertService.getAlertDtls(fromDate, toDate, stateId, alertTypeId, alertStatusIds, alertCategoryId, activityMemberId,editionIds,isActionType,alertActionTypeId,scopeIdList,alertVerificationStatusId);   
		}catch(Exception e) {  
			LOG.error("Exception occured in getAlertDtls() of CreateAlertAction",e);
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
			Long alertTypeId = jObj.getLong("alertTypeId");
			Long editionTypeId = jObj.getLong("editionTypeId");
			Long districtId = jObj.getLong("districtId");
			List<Long> alertStatusIds = new ArrayList<Long>();
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			resultList = alertService.getAssignGroupTypeAlertDtlsByImpactLevelWise(activityMemberId,stateId,fromDate,toDate,scopeIdList,alertTypeId,editionTypeId,districtId,alertStatusIds);   
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
			 Long alertTypeId = jObj.getLong("alertTypeId");
			Long editionTypeId = jObj.getLong("editionTypeId");
			
			String groupAssignType = jObj.getString("groupAssignType");
			Long districtId = jObj.getLong("districtId");
			List<Long> alertStatusIds = new ArrayList<Long>();
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			if(groupAssignType.equalsIgnoreCase("Party Committee")){
				alertVOs = alertService.getTotalAlertGroupByPubRepThenStatus(fromDate, toDate, stateId, scopeIdList, activityMemberId, publicRepresentativeTypeId,commitLvlIdList,groupAssignType,"",null,alertTypeId,editionTypeId,districtId,alertStatusIds);
			}else{
				alertVOs = alertService.getTotalAlertGroupByPubRepThenStatus(fromDate, toDate, stateId, scopeIdList, activityMemberId, publicRepresentativeTypeId,null,groupAssignType,"",null,alertTypeId,editionTypeId,districtId,alertStatusIds);
			}
			   
		}catch(Exception e) {  
			LOG.error("Exception occured in getTotalAlertGroupByPubRepThenStatus() of CreateAlertAction",e);
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
			Long alertTypeId = jObj.getLong("alertTypeId");
			Long editionTypeId = jObj.getLong("editionTypeId");
			Long districtId = jObj.getLong("districtId");
			List<Long> alertStatusIds = new ArrayList<Long>();
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			alertVOs = alertService.getTotalAlertGroupByPubRepThenStatus(fromDate, toDate, stateId, scopeIdList, activityMemberId, null,commitLvlIdList,groupAssignType,position,designationId,alertTypeId,editionTypeId,districtId,alertStatusIds);
			
		}catch(Exception e) {  
			LOG.error("Exception occured in getDesigWiseTdpCommitAlertCount() of CreateAlertAction",e);
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
		try{//sfd
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
			Long alertTypeId = jObj.getLong("alertTypeId");
			Long editionTypeId = jObj.getLong("editionTypeId");
			Long districtId = jObj.getLong("districtId");
			List<Long> alertStatusIds = new ArrayList<Long>();
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			resultList = alertService.getOtherAndPrgrmCmmtteeTypeAlertCndtDtls(activityMemberId,stateId,fromDate,toDate,scopeIdList,resultType,alertTypeId,editionTypeId,districtId,alertStatusIds);   
		}catch(Exception e) {  
			LOG.error("Exception occured in getOtherAndPrgrmCmmtteeTypeAlertCndtDtls() of CreateAlertAction",e);
		}
		return Action.SUCCESS;  
	}
	public String getStateOrGhmcImpactLevelAlertStatusWise(){
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
			Long alertId = jObj.getLong("alertIds");
			Long editionId = jObj.getLong("editionIds");
			List<Long> alertStatusIds = new ArrayList<Long>();
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			Long districtId = jObj.getLong("districtId");
			alertOverviewVO = alertService.getStateOrGhmcImpactLevelAlertStatusWise(activityMemberId,stateId,fromDate,toDate,scopeIdList,alertId,editionId,alertStatusIds,districtId);   
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
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}   
			List<Long> alertStatusIds = new ArrayList<Long>();
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			Long alertTypeId = jObj.getLong("alertTypeId");
			Long editionTypeId = jObj.getLong("editionTypeId");
			Long districtId = jObj.getLong("districtId");
			alertCoreDashBoardVOs = alertService.getAlertDtlsForPubRep(fromDate,toDate,stateId,scopeIdList,activityMemberId,publicRepresentativeTypeId,cadreId,alertStatusIds,alertTypeId,editionTypeId,districtId);   
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
			List<Long> alertStatusIds = new ArrayList<Long>();
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			} 
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}   
			String resultType = jObj.getString("resultType");
			Long alertTypeId = jObj.getLong("alertTypeId");
			Long editionTypeId = jObj.getLong("editionTypeId");
			Long districtId = jObj.getLong("districtId");
			alertCoreDashBoardVOs = alertService.getAlertDetailsTdpCadreWise(fromDate,toDate,stateId,scopeIdList,activityMemberId,cadreId,alertStatusIds,resultType,alertTypeId,editionTypeId,districtId);   
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
			List<Long> districtIds = new ArrayList<Long>(0);
			JSONArray distArr = jObj.getJSONArray("districtIdArr");
			for (int i = 0; i < distArr.length(); i++){
				districtIds.add(Long.parseLong(distArr.getString(i)));
			} 
			Long catId = jObj.getLong("catId");
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			} 
			Long alertTypeId = jObj.getLong("alertTypeId");
			Long editionId = jObj.getLong("editionId");
			Long constituencyId = jObj.getLong("constituencyId");
			String locationLevel = jObj.getString("locationLevel");
			String isPublication = jObj.getString("isPublication");
			String publicationIdStr = jObj.getString("publicationId");
			Long localElectionBodyId = jObj.getLong("locationElectionBodyId");
			List<Long> alertStatusIds = new ArrayList<Long>();
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			alertCoreDashBoardVOs = alertService.getDistrictAndStateImpactLevelWiseAlertDtls(fromDate,toDate,stateId,scopeIdList,activityMemberId,districtIds,catId,alertTypeId,editionId,constituencyId,alertStatusIds,locationLevel,isPublication,publicationIdStr,localElectionBodyId);   
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
			Long alertTypeId = jObj.getLong("alertTypeId");
			Long editionId = jObj.getLong("editionTypeId");
			Long districtId = jObj.getLong("districtId");
			List<Long> alertStatusIds = new ArrayList<Long>();
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			alertVOs = alertService.getMemForPartyCommitDesg(fromDate,toDate,stateId,scopeIdList,activityMemberId,commitLvlIdList,commitTypeId,designationId,alertTypeId,editionId,districtId,alertStatusIds);   
		}catch(Exception e) {      
			LOG.error("Exception occured in getMemForPartyCommitDesg() of CreateAlertAction",e);  
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
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}
			List<Long> alertStatusIds = new ArrayList<Long>();
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			JSONArray commitLvlIdArr = jObj.getJSONArray("commitLvlIdArr");  
			List<Long> commitLvlIdList = new ArrayList<Long>();
			for (int i = 0; i < commitLvlIdArr.length(); i++){
				commitLvlIdList.add(Long.parseLong(commitLvlIdArr.getString(i)));        
			}      
			Long alertTypeId = jObj.getLong("alertTypeId");
			Long editionId = jObj.getLong("editionTypeId");
			Long districtId = jObj.getLong("districtId");
			alertCoreDashBoardVOs = alertService.getAlertDtlsAssignedByPartyCommite(fromDate,toDate,stateId,scopeIdList,activityMemberId,commitLvlIdList,commitTypeId,designationId,cadreId,alertStatusIds,alertTypeId,editionId,districtId);   
		}catch(Exception e) {        
			LOG.error("Exception occured in getAlertDtlsAssignedByPartyCommite() of CreateAlertAction",e);  
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
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Map<File,String> mapfiles = new HashMap<File,String>();
			MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
			Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
			
			while(fileParams.hasMoreElements()){
				String key = fileParams.nextElement();
				File[] files = multiPartRequestWrapper.getFiles(key);
				if(files != null && files.length > 0){
					int index = 0;
					for(File f : files){
						String fileName  =multiPartRequestWrapper.getFileNames(key)[index];
						String ext = "";
						index = index + 1;
						mapfiles.put(f,fileName);  
					}
				}  
			}
			status = alertService.saveAlertDocument(alertId,regVo.getRegistrationID(),mapfiles);
			inputStream = new StringBufferInputStream(status);
			
		
		
		}catch(Exception e){
			inputStream = new StringBufferInputStream("failed");
			LOG.error(e);
		}
		
		return Action.SUCCESS;	         
	}
	
	public String getClarificationDetails(){
		try {
			jObj = new JSONObject(getTask());
			
			alertClarificationVO = alertService.getClarificationDetails(jObj.getLong("alertId"));
		} catch (Exception e) {
			LOG.error("Excpetion raised at getClarificationDetails",e);
		}
		return Action.SUCCESS;
	}
	
	public String saveClarificationRequiredStatus(){
		try {
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			if(regVo != null)
				status = alertService.saveClarificationRequiredStatus(regVo.getRegistrationID(),jObj.getString("statusStr"),jObj.getLong("alertId"),jObj.getString("remark")); 
		} catch (Exception e) {
			LOG.error("Excpetion raised at saveClarificationRequiredStatus",e);
		}
		return Action.SUCCESS;
	}
	
	public String removeAlertComment(){
		try {
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			if(regVo != null)
				status = alertService.removeAlertComment(jObj.getLong("commentId"));
		} catch (Exception e) {
			LOG.error("Excpetion raised at removeAlertComment",e);
		}
		return Action.SUCCESS;
	}
	
	public String removeAlertDocument(){
		try {
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			if(regVo != null)
				status = alertService.removeAlertDocument(jObj.getLong("docId"));
		} catch (Exception e) {
			LOG.error("Excpetion raised at removeAlertComment",e);
		}
		return Action.SUCCESS;
	}
	
	public String getStatusAndCategoryWiseAlertsCount(){//ClarificationDetailsCountVO
		try {
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			if(regVo != null)
				clarificationDetailsCountVOList =  alertService.getStatusAndCategoryWiseAlertsCount(jObj.getLong("stateId"),jObj.getString("fromDate"),jObj.getString("toDate"),jObj.getLong("alertTypeId"));
				
		} catch (Exception e) {  
			LOG.error("Excpetion raised at getStatusAndCategoryWiseAlertsCount",e);
		}
		return Action.SUCCESS;
	}
	
	public String getLocationLevelAlertClarificationData(){
		try {
			jObj = new JSONObject(getTask());
			session = request.getSession();
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
			
			alertDataList = alertService.getLocationLevelAlertClarificationData(regVo.getRegistrationID(),inputVO);
		} catch (Exception e) {
			LOG.error("Excpetion raised at getLocationLevelAlertClarificationData",e);
		}
		return Action.SUCCESS;
	}
	
	public String getDocumentsForAlert(){
		try {
			jObj = new JSONObject(getTask());
			keyValueVOList = alertService.getDocumentsForAlert(jObj.getLong("alertId"));
		} catch (Exception e) {
			LOG.error("Excpetion raised at getDocumentsForAlert",e);
		}
		return Action.SUCCESS;
	}
	
	public String uploadAlertsStatusDoc(){
		try {
			String imageName=null;
			 
			 RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			 
			 if(regVO!=null){
				 Long userId = regVO.getRegistrationID();
				 List<String> fileNamesList = new ArrayList<String>(0);
				 
				 for(int i=0;i<imageForDisplay.size();i++){
		        	  String fileType = imageForDisplayContentType.get(i).substring(imageForDisplayContentType.get(i).indexOf("/")+1, imageForDisplayContentType.get(i).length());
		        	  String fileName = imageForDisplayFileName.get(i);
		        	  String[] extName = imageForDisplayFileName.get(i).split("\\.");
		        	  if(Pattern.matches(".*[a-zA-Z]+.*", extName[0])){
		        		  imageName= UUID.randomUUID().toString()+"_"+imageForDisplayFileName.get(i);
				          fileNamesList.add(IConstants.TOUR_DOCUMENTS+"/"+imageName);
		        	  }else{
		        		  imageName= UUID.randomUUID().toString()+"."+extName[1];
				          fileNamesList.add(IConstants.TOUR_DOCUMENTS+"/"+imageName);
		        	  }
			          String filePath=IConstants.STATIC_CONTENT_FOLDER_PATH+"/Reports/"+IConstants.TOUR_DOCUMENTS;
			        	 
			          File fileToCreate = new File(filePath,imageName);
					  FileUtils.copyFile(imageForDisplay.get(i), fileToCreate);
					  
					  inputStream = new StringBufferInputStream("success");
		          }
				 
				 	AlertVO inputVO = new AlertVO();
					inputVO.setId(alertId);
					inputVO.setDesc(clarificationComments);
					inputVO.setStatusId(clarificationStatusId);
					
					if(cadreIds != null && cadreIds.size() > 0){
						for (Long long1 : cadreIds) {
							IdNameVO vo = new IdNameVO();
							vo.setId(long1);
							inputVO.getAssignList().add(vo);
						}
					}
					
					session = request.getSession();
					RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
					status = alertService.updateAlertStatus(regVo.getRegistrationID(),inputVO,fileNamesList);
					
				 
			 }else{
				 inputStream = new StringBufferInputStream("login failed");
			 }
		} catch (Exception e) {
			LOG.error("Exception raised while uploading alert status documents", e);
		}
		return Action.SUCCESS;	
		
	}
	public String getAllAlertsWithoutFilter(){   
		try {
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			AlertInputVO inputVO = new AlertInputVO();
			inputVO.setActionTypeId(jObj.getLong("actionTypeId"));
			inputVO.setActionTypeStatusId(jObj.getLong("actionTypeStatusId"));  
			inputVO.setFromDate(jObj.getString("fromDate"));
			inputVO.setToDate(jObj.getString("toDate"));
			inputVO.setLevelValue(jObj.getLong("levelValue"));
			inputVO.setCategoryId(jObj.getLong("categoryId"));
			inputVO.setAlertTypeId(jObj.getLong("alertTypeId"));
			inputVO.setAlertImpactScopeId(jObj.getLong("impactScopeId"));
			inputVO.setStatusId(jObj.getLong("statusId"));
			inputVO.setFromDate2(jObj.getString("fromDate2"));
			inputVO.setToDate2(jObj.getString("toDate2"));
			
			
			alertDataList = alertService.getAllAlertsWithoutFilter(regVo.getRegistrationID(),inputVO);
		} catch (Exception e) {
			LOG.error("Excpetion raised at getLocationLevelAlertClarificationData",e);
		}
		return Action.SUCCESS;
	}
	public String updateAlertVerficationStaus(){
		try{
			 RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			 if(regVO!=null){
				 Long userId = regVO.getRegistrationID();
				 Map<File,String> mapfiles = new HashMap<File,String>();
					MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
					Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
					
					while(fileParams.hasMoreElements()){
						String key = fileParams.nextElement();
						File[] files = multiPartRequestWrapper.getFiles(key);
						if(files != null && files.length > 0){
							int index = 0;
							for(File f : files){
								String[] extension  = multiPartRequestWrapper.getFileNames(key)[index].split("\\.");
								String ext = "";
								if(extension.length > 1){
									ext = extension[extension.length-1];
									mapfiles.put(f,ext);
								}
								index = index+1;
							}
						}
					}
					status = alertService.updateVerificationStatus(alertId,clarificationComments,clarificationStatusId,userId,mapfiles);
					inputStream = new StringBufferInputStream(status);
			 }else{
				 inputStream = new StringBufferInputStream("login failed");
			 }
		}catch(Exception e){
			inputStream = new StringBufferInputStream("failed");
			LOG.error(e);
		}
		return Action.SUCCESS;	         
	}
	public String getAlertVerificationDetails(){
		try{
			jObj = new JSONObject(getTask());
			Long alertId = jObj.getLong("alertId");
			alertVerificationVO = alertService.getAlertVerificationDtls(alertId);	
		}catch(Exception e){
			LOG.error("Excpetion raised at getAlertVerificationDetails",e);	
		}
		return Action.SUCCESS;
	}
	public String getAlertVerificationStatus(){
		try{
			jObj = new JSONObject(getTask());
			Long actionTypeId = jObj.getLong("actionTypeId");
			alertVerificationList = alertService.getAlertTypeActionStatus(actionTypeId);	
		}catch(Exception e){
			LOG.error("Excpetion raised at getAlertVerificationStatus",e);	
		}
		return Action.SUCCESS;
	}
	public String getAlertDetatilsByAlertType(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			Long alertTypeId = jObj.getLong("alertTypeId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");  
			Long activityMemberId = jObj.getLong("activityMemberId");
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}
			List<Long> alertStatusIds = new ArrayList<Long>();
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			Long editionId = jObj.getLong("editionId");
			alertCoreDashBoardVOs = alertService.getAlertDetailsByAlertType(fromDate,toDate,stateId,alertTypeId,activityMemberId,scopeIdList,alertStatusIds,editionId);   
		}catch(Exception e) {  
			LOG.error("Exception occured in getAlertDetatilsByAlertType() of CreateAlertAction",e);
		}
		return Action.SUCCESS;  
	}
	public String centralPartyMembersAlertDashBoard(){
		session = request.getSession();
		RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
		if(regVo.getEntitlements() != null && regVo.getEntitlements().contains("TDP_CADRE_LOGIN_ENTITLEMENT"))  
			return Action.SUCCESS;
		else
			return Action.ERROR;	
		//return Action.SUCCESS;
	}
	
	public String getTotalAlertGroupByStatusForCentralMembers(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long alertyTypeId = jObj.getLong("alertyTypeId");
			Long tdpCadreId = jObj.getLong("tdpCadreId");
			alertVOs = alertService.getTotalAlertGroupByStatusForCentralMembers(fromDate, toDate, stateId,alertyTypeId,tdpCadreId);
		}catch(Exception e) {
			LOG.error("Exception occured in getTotalAlertGroupByStatusForCentralMembers() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getTotalAlertGroupByStatusThenCategoryForCentralMembers(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long alertyTypeId = jObj.getLong("alertyTypeId");
			Long tdpCadreId = jObj.getLong("tdpCadreId");
			alertVOs = alertService.getTotalAlertGroupByStatusThenCategoryForCentralMembers(fromDate, toDate, stateId,alertyTypeId,tdpCadreId);
		}catch(Exception e) {
			LOG.error("Exception occured in getTotalAlertGroupByStatusThenCategoryForCentralMembers() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAlertAssignedCandidatesForCentralMembers()
	{
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			
			alertDataList = alertService.getAlertAssignedCandidatesForCentralMembers(jObj.getLong("tdpCadreId"));
			
		}
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception rised in getAlertAssignedCandidatesForCentralMembers",e);
		}
		return Action.SUCCESS;	
	}
	
	public String getLocationLevelWiseAlertsDataForCentralMembers()
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
			
			alertDataList = alertService.getLocationLevelWiseAlertsDataForCentralMembers(regVo.getRegistrationID(),inputVO);
			
		}
		catch (Exception e) {
			LOG.error("Exception rised in getLocationLevelWiseAlertsDataForCentralMembers",e);
		}
		return Action.SUCCESS;	
	}
	
	public String getAllAlertsWithoutFilterForCentralMembers(){   
		try {
			jObj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			AlertInputVO inputVO = new AlertInputVO();
			inputVO.setActionTypeId(jObj.getLong("actionTypeId"));
			inputVO.setActionTypeStatusId(jObj.getLong("actionTypeStatusId"));  
			inputVO.setFromDate(jObj.getString("fromDate"));
			inputVO.setToDate(jObj.getString("toDate"));
			inputVO.setLevelValue(jObj.getLong("levelValue"));
			inputVO.setCategoryId(jObj.getLong("categoryId"));
			inputVO.setAlertTypeId(jObj.getLong("alertTypeId"));
			inputVO.setAlertImpactScopeId(jObj.getLong("impactScopeId"));
			inputVO.setStatusId(jObj.getLong("statusId"));
			inputVO.setFromDate2(jObj.getString("fromDate2"));
			inputVO.setToDate2(jObj.getString("toDate2"));
			inputVO.setAssignId(jObj.getLong("assignId"));
			
			alertDataList = alertService.getAllAlertsWithoutFilterForCentralMembers(regVo.getRegistrationID(),inputVO);
		} catch (Exception e) {
			LOG.error("Excpetion raised at getLocationLevelAlertClarificationData",e);
		}
		return Action.SUCCESS;
	}
	public String getAlertDetailsForEdit(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long alertId = jObj.getLong("alertId");
			alertVO = alertService.getAlertDetailsForEdit(alertId);
		}catch(Exception e){
			LOG.error("Exception occured in getAlertDetailsForEdit() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String deleteAlert(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long alertId = jObj.getLong("alertId");
			status = alertService.deleteAlert(alertId);
		}catch(Exception e){
			LOG.error("Exception occured in deleteAlert() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getTotalAlertGroupByStatusForGovt(){
		try{
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			jObj = new JSONObject(getTask());
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long stateId = jObj.getLong("stateId");
			
			JSONArray deptIdArr = jObj.getJSONArray("deptIdArr");  
			List<Long> deptIdList = new ArrayList<Long>();
			for (int i = 0; i < deptIdArr.length(); i++){
				deptIdList.add(Long.parseLong(deptIdArr.getString(i)));        
			}  
			
			JSONArray paperIdArr = jObj.getJSONArray("paperIdArr");  
			List<Long> paperIdList = new ArrayList<Long>();
			for (int i = 0; i < paperIdArr.length(); i++){
				paperIdList.add(Long.parseLong(paperIdArr.getString(i)));        
			} 
			
			JSONArray chanelIdArr = jObj.getJSONArray("chanelIdArr");  
			List<Long> chanelIdList = new ArrayList<Long>();
			for (int i = 0; i < chanelIdArr.length(); i++){
				chanelIdList.add(Long.parseLong(chanelIdArr.getString(i)));        
			}
			
			alertVOs = cccDashboardService.getTotalAlertGroupByStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,userId);
		}catch(Exception e){
			LOG.error("Exception occured in getAlertDetailsForEdit() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getTotalAlertGroupByStatusThenDepartment(){
		try{
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			jObj = new JSONObject(getTask());
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long stateId = jObj.getLong("stateId");
			String sortingType = jObj.getString("sortingType");
			JSONArray deptIdArr = jObj.getJSONArray("deptIdArr");  
			List<Long> deptIdList = new ArrayList<Long>();
			for (int i = 0; i < deptIdArr.length(); i++){
				deptIdList.add(Long.parseLong(deptIdArr.getString(i)));        
			}  
			
			JSONArray paperIdArr = jObj.getJSONArray("paperIdArr");  
			List<Long> paperIdList = new ArrayList<Long>();
			for (int i = 0; i < paperIdArr.length(); i++){
				paperIdList.add(Long.parseLong(paperIdArr.getString(i)));        
			} 
			
			JSONArray chanelIdArr = jObj.getJSONArray("chanelIdArr");  
			List<Long> chanelIdList = new ArrayList<Long>();
			for (int i = 0; i < chanelIdArr.length(); i++){
				chanelIdList.add(Long.parseLong(chanelIdArr.getString(i)));        
			}
			
			alertVOs = cccDashboardService.getTotalAlertGroupByStatusThenDepartment(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,userId,sortingType);
		}catch(Exception e){
			LOG.error("Exception occured in getAlertDetailsForEdit() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getTotalAlertByStatus(){
		try{
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			jObj = new JSONObject(getTask());
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long stateId = jObj.getLong("stateId");
			Long statusId = jObj.getLong("statusId");
			
			JSONArray deptIdArr = jObj.getJSONArray("deptIdArr");  
			List<Long> deptIdList = new ArrayList<Long>();
			if(deptIdArr != null && deptIdArr.length() > 0){
				for (int i = 0; i < deptIdArr.length(); i++){
					deptIdList.add(Long.parseLong(deptIdArr.getString(i)));        
				} 
			}
			 
			JSONArray paperIdArr = jObj.getJSONArray("paperIdArr");  
			List<Long> paperIdList = new ArrayList<Long>();
			if(paperIdArr != null && paperIdArr.length() > 0){
				for (int i = 0; i < paperIdArr.length(); i++){
					paperIdList.add(Long.parseLong(paperIdArr.getString(i)));        
				} 
			}
			
			JSONArray chanelIdArr = jObj.getJSONArray("chanelIdArr");  
			List<Long> chanelIdList = new ArrayList<Long>();
			if(chanelIdArr != null && chanelIdArr.length() > 0){
				for (int i = 0; i < chanelIdArr.length(); i++){
					chanelIdList.add(Long.parseLong(chanelIdArr.getString(i)));          
				}
			}
			
			if(statusId != null && statusId.longValue() == 1L){//pending
				alertCoreDashBoardVOs = cccDashboardService.getTotalAlertByStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId);
			}else if(statusId != null && statusId.longValue() > 1L){//other than pending
				alertCoreDashBoardVOs = cccDashboardService.getTotalAlertByOtherStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,userId);
			}else{
				alertCoreDashBoardVOs = cccDashboardService.getTotalAlertByStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,1L);
				List<AlertCoreDashBoardVO> list1 = new ArrayList<AlertCoreDashBoardVO>();
				if(alertCoreDashBoardVOs != null){
					list1.addAll(alertCoreDashBoardVOs);
				}
				alertCoreDashBoardVOs = cccDashboardService.getTotalAlertByOtherStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,userId);
				List<AlertCoreDashBoardVO> list2 = new ArrayList<AlertCoreDashBoardVO>();
				if(alertCoreDashBoardVOs != null ){
					list2.addAll(alertCoreDashBoardVOs);
				}
				alertCoreDashBoardVOs.clear();
				alertCoreDashBoardVOs.addAll(list1);
				alertCoreDashBoardVOs.addAll(list2);     
			}
			
		}catch(Exception e){
			LOG.error("Exception occured in getAlertDetailsForEdit() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getAlertStatusByAlertType(){
		try{
			  jObj = new JSONObject(getTask());
			  alertVOs = alertService.getAlertStatusByAlertTypeId(jObj.getLong("alertTypeId"),jObj.getLong("alertId"));
		}catch(Exception e){
			LOG.error("Exception occured in getAlertStatusByAlertType() of CreateAlertAction",e);	
		}
		return Action.SUCCESS;
	}
	public String getTotalAlertByStatusForDeptWiseClick(){
		try{
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			jObj = new JSONObject(getTask());
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long stateId = jObj.getLong("stateId");
			Long statusId = jObj.getLong("statusId");
			
			JSONArray deptIdArr = jObj.getJSONArray("deptIdArr");  
			List<Long> deptIdList = new ArrayList<Long>();
			for (int i = 0; i < deptIdArr.length(); i++){
				deptIdList.add(Long.parseLong(deptIdArr.getString(i)));        
			}  
			
			JSONArray paperIdArr = jObj.getJSONArray("paperIdArr");  
			List<Long> paperIdList = new ArrayList<Long>();
			for (int i = 0; i < paperIdArr.length(); i++){
				paperIdList.add(Long.parseLong(paperIdArr.getString(i)));        
			} 
			
			JSONArray chanelIdArr = jObj.getJSONArray("chanelIdArr");  
			List<Long> chanelIdList = new ArrayList<Long>();
			for (int i = 0; i < chanelIdArr.length(); i++){
				chanelIdList.add(Long.parseLong(chanelIdArr.getString(i)));        
			}
			String type = jObj.getString("type");
			alertCoreDashBoardVOs = cccDashboardService.getTotalAlertByStatusForDeptWiseClick(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,type,userId);
		}catch(Exception e){
			LOG.error("Exception occured in getAlertDetailsForEdit() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getDistrictListByStateId(){
		try{
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long userTypeId = jObj.getLong("userTypeId");
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			Long alertTypeId = jObj.getLong("alertTypeId");
			Long editionId = jObj.getLong("editionId");
			resultList = alertService.getDistrictListByStateId(stateId,activityMemberId,userTypeId,fromDateStr,toDateStr,alertTypeId,editionId);
		}catch(Exception e){
			LOG.error("Exception occured in getDistrictListByStateId() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getAlertStatus(){
		try{
			jObj = new JSONObject(getTask());
			resultList = alertService.getAlertStatus(jObj.getLong("alertTypeId"));
		}catch(Exception e){
			LOG.error("Exception occured in getAlertStatus() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getPublicationWiseAlert(){
		try{
			session = request.getSession();
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			List<Long> alertStatusIds = new ArrayList<Long>();
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			Long activityMemberId = jObj.getLong("activityMemberId");
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}
			Long alertId = jObj.getLong("alertIds");
			Long editionId = jObj.getLong("editionIds");
			String filterType = jObj.getString("filterType");
			Long locationValue = jObj.getLong("locationValue");
			String sortingType = jObj.getString("sortingType");
			Long disctrictId = jObj.getLong("disctrictId");
			resultList = alertService.getPublicationWiseAlert(fromDate, toDate, stateId, scopeIdList, activityMemberId,alertStatusIds,alertId,editionId,filterType,locationValue,sortingType,disctrictId);    
		}catch(Exception e) {  
			LOG.error("Exception occured in getPublicationWiseAlert() of CreateAlertAction",e);
		}
		return Action.SUCCESS;  
	}
	public String getStateImpactandItsSubLevelAlert(){
		try{
			session = request.getSession();			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long activityMemberId = jObj.getLong("activityMemberId");
			JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
			List<Long> scopeIdList = new ArrayList<Long>();
			for (int i = 0; i < jArray.length(); i++){
				scopeIdList.add(Long.parseLong(jArray.getString(i)));
			}  
			List<Long> alertStatusIds = new ArrayList<Long>();
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			Long alertTypeId = jObj.getLong("alertIds");
			Long editionId = jObj.getLong("editionIds");
			alertOverviewVO = alertService.getStateImpactandItsSubLevelAlert(activityMemberId,stateId,fromDate,toDate,scopeIdList,alertTypeId,editionId,alertStatusIds);   
		}catch(Exception e) {  
			LOG.error("Exception occured in getStateImpactandItsSubLevelAlert() of CreateAlertAction",e);
		}
		return Action.SUCCESS;  
	  }
	public String getDistrictOrConstituencyImpactandItsSubLevelAlert(){
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
			List<Long> alertStatusIds = new ArrayList<Long>();
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			Long alertTypeId = jObj.getLong("alertIds");
			Long editionId = jObj.getLong("editionIds");
			Long locationValue = jObj.getLong("locationValue");
			String sortyingType = jObj.getString("sortingType");
			String resultType = jObj.getString("resultType");
			Long disctrictId = jObj.getLong("districtId");
			alertOverviewVO = alertService.getDistrictOrConstituencyImpactandItsSubLevelAlert(activityMemberId,stateId,fromDate,toDate,scopeIdList,alertTypeId,editionId,alertStatusIds,locationValue,sortyingType,resultType,disctrictId);   
		}catch(Exception e) {  
			LOG.error("Exception occured in getDistrictImpactandItsSubLevelAlert() of CreateAlertAction",e);
		}
		return Action.SUCCESS;  
	  }
	public String getCorpGMCAlert(){
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
			List<Long> alertStatusIds = new ArrayList<Long>();
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			Long alertTypeId = jObj.getLong("alertIds");
			Long editionId = jObj.getLong("editionIds");
			Long districtId = jObj.getLong("districtId");
			alertOverviewVO = alertService.getCorpGMCAlert(activityMemberId,stateId,fromDate,toDate,scopeIdList,alertTypeId,editionId,alertStatusIds,districtId);   
		}catch(Exception e) {  
			LOG.error("Exception occured in getDistrictImpactandItsSubLevelAlert() of CreateAlertAction",e);
		}
		return Action.SUCCESS;  
	  }
	public String getAlertByUserTypeBasedOnAccessLevel(){
		try{
			 
			    jObj = new JSONObject(getTask());
			 	Long parentActivityMemberId = jObj.getLong("parentActivityMemberId");
			 	List<Long> childUserTypeIds=new ArrayList<Long>();
				JSONArray childUserTypeIdsArray=jObj.getJSONArray("childUserTypeIdsArray");
				if(childUserTypeIdsArray!=null &&  childUserTypeIdsArray.length()>0){
					for( int i=0;i<childUserTypeIdsArray.length();i++){
						childUserTypeIds.add(Long.valueOf(childUserTypeIdsArray.getString(i)));
					}
				}
				String reportType = jObj.getString("reportType");
				Long stateId = jObj.getLong("stateId");
				String fromDate = jObj.getString("fromDate");
				String toDate = jObj.getString("toDate");
				JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
				List<Long> scopeIdList = new ArrayList<Long>();
				for (int i = 0; i < jArray.length(); i++){
					scopeIdList.add(Long.parseLong(jArray.getString(i)));
				}  
				List<Long> alertStatusIds = new ArrayList<Long>();
				JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
				for (int i = 0; i < statusArray.length(); i++){
					alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
				}  
				Long alertTypeId = jObj.getLong("alertIds");
				Long editionId = jObj.getLong("editionIds");
				activityMembersList = alertService.getAlertByUserTypeBasedOnAccessLevel(parentActivityMemberId,childUserTypeIds,reportType,stateId,fromDate,toDate,scopeIdList,alertTypeId,editionId,alertStatusIds);
		 }catch(Exception e){
			 LOG.error("Exception raised at getSelectedChildTypeMembersForTrainingProgram() method of CoreDashBoardAction", e); 
		 }
		 return Action.SUCCESS;
	}
	public String getDirectChildActivityAlertDetails(){
		try{
			 
			    jObj = new JSONObject(getTask());
			 	Long activityMemberId = jObj.getLong("activityMemberId");
			 	List<Long> childUserTypeIds=new ArrayList<Long>();
				JSONArray childUserTypeIdsArray=jObj.getJSONArray("childUserTypeIdsArray");
				if(childUserTypeIdsArray!=null &&  childUserTypeIdsArray.length()>0){
					for( int i=0;i<childUserTypeIdsArray.length();i++){
						childUserTypeIds.add(Long.valueOf(childUserTypeIdsArray.getString(i)));
					}
				}
				String reportType = jObj.getString("reportType");
				Long stateId = jObj.getLong("stateId");
				String fromDate = jObj.getString("fromDate");
				String toDate = jObj.getString("toDate");
				JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
				List<Long> scopeIdList = new ArrayList<Long>();
				for (int i = 0; i < jArray.length(); i++){
					scopeIdList.add(Long.parseLong(jArray.getString(i)));
				}  
				List<Long> alertStatusIds = new ArrayList<Long>();
				JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
				for (int i = 0; i < statusArray.length(); i++){
					alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
				}  
				Long alertTypeId = jObj.getLong("alertIds");
				Long editionId = jObj.getLong("editionIds");
				activityMembersList = alertService.getAlertByUserTypeBasedOnAccessLevel(activityMemberId,childUserTypeIds,reportType,stateId,fromDate,toDate,scopeIdList,alertTypeId,editionId,alertStatusIds);
		 }catch(Exception e){
			 LOG.error("Exception raised at getDirectChildActivityAlertDetails() method of CoreDashBoardAction", e); 
		 }
		 return Action.SUCCESS;
	}
	public String getDirectChildMemberAlertStatusWise(){
		try{
			 
			    jObj = new JSONObject(getTask());
			 	Long activityMemberId = jObj.getLong("activityMemberId");
				Long stateId = jObj.getLong("stateId");
				String fromDate = jObj.getString("fromDate");
				String toDate = jObj.getString("toDate");
				JSONArray jArray = jObj.getJSONArray("scopeIdsArr");
				List<Long> scopeIdList = new ArrayList<Long>();
				for (int i = 0; i < jArray.length(); i++){
					scopeIdList.add(Long.parseLong(jArray.getString(i)));
				}  
				List<Long> alertStatusIds = new ArrayList<Long>();
				JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
				for (int i = 0; i < statusArray.length(); i++){
					alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
				}  
				Long alertTypeId = jObj.getLong("alertIds");
				Long editionId = jObj.getLong("editionIds");
				resultList = alertService.getDirectChildMemberAlertStatusWise(activityMemberId,stateId,fromDate,toDate,scopeIdList,alertTypeId,editionId,alertStatusIds);
		 }catch(Exception e){
			 LOG.error("Exception raised at getDirectChildMemberAlertStatusWiseAction() method of CoreDashBoardAction", e); 
		 }
		 return Action.SUCCESS;
	}
	public String getStateOrGHMCImpcatLevelAlertCntPublicationWise(){
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
			List<Long> alertStatusIds = new ArrayList<Long>();
			JSONArray statusArray = jObj.getJSONArray("alertStatusArr"); 
			for (int i = 0; i < statusArray.length(); i++){
				alertStatusIds.add(Long.parseLong(statusArray.getString(i)));
			}  
			Long alertTypeId = jObj.getLong("alertIds");
			Long editionId = jObj.getLong("editionIds");
			Long disctrictId = jObj.getLong("districtId");
			alertOverviewVO = alertService.getStateOrGHMCImpcatLevelAlertCntPublicationWise(activityMemberId,fromDate,toDate,stateId,scopeIdList,alertTypeId,editionId,alertStatusIds,disctrictId);    
		}catch(Exception e) {  
			LOG.error("Exception occured in getStateOrGHMCImpcatLevelAlertCntPublicationWise() of CreateAlertAction",e);
		}
		return Action.SUCCESS;  
	}
	
	public String createGrievance(){
		session = request.getSession();
		RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
		if(regVo != null)
			return Action.SUCCESS;
		else
			return Action.ERROR;
	}
	
	public String getAlertIssueTypes(){
		try{
			jObj = new JSONObject(getTask());
			
			keyValueVOList = alertService.getAlertIssueTypes();
		}catch(Exception e){
			LOG.error("Exception occured in getAlertIssueTypes() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getHamletsForPanchayat(){
		try{
			jObj = new JSONObject(getTask());
			Long panchyatId = jObj.getLong("panchayatId");
			keyValueVOList = alertService.getHamletsForPanchayat(panchyatId);
		}catch(Exception e){
			LOG.error("Exception occured in getHamletsForPanchayat() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAlertCallerTypes(){
		try{
			jObj = new JSONObject(getTask());
			
			keyValueVOList = alertService.getAlertCallerTypes();
		}catch(Exception e){
			LOG.error("Exception occured in getAlertCallerTypes() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAlertCallerDetailsByMobileNo(){
		try {
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			if(regVo == null)
				return null;
			
			jObj = new JSONObject(getTask());
			String startDate = jObj.getString("startDate");
			String endDate = jObj.getString("endDate");
			String status = jObj.getString("status");
			String mobileNo = jObj.getString("mobileNo");
			Long deptId = jObj.getLong("deptId");
			
			alertTrackingVOList = alertService.getAlertCallerDetailsByMobileNo(regVo.getRegistrationID(),startDate,endDate,status,mobileNo,deptId);
		} catch (Exception e) {
			LOG.error("Exception Occured in getCommentsForAlert() method, Exception - ",e); 
		}
		return Action.SUCCESS;	
		
	}
	
	public String createGrievanceAlert()
	{
		try{
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Map<File,String> mapfiles = new HashMap<File,String>();
			MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
			Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
			int i = 0;
			while(fileParams.hasMoreElements()){
				String key = fileParams.nextElement();
				File[] files = multiPartRequestWrapper.getFiles(key);
				if(files != null && files.length > 0){
					for(File f : files){
						String fileName  =multiPartRequestWrapper.getFileNames(key)[i];
						//fileName = StringEscapeUtils.escapeJava(fileName);
						mapfiles.put(f,fileName);  
						i++;
					}
				}
			}
			status = alertService.createGrievanceAlert(grievanceAlertVO,regVo.getRegistrationID(),mapfiles);
			
			inputStream = new StringBufferInputStream(status);
			
		}
		catch (Exception e) {
			LOG.error("Exception rised in raiseComplaint",e);
		}
		return Action.SUCCESS;	
	}
	
	public String getPanchayatiesByMandalOrMuncipality(){
		
		try{
			jObj = new JSONObject(getTask());
			idNameVOList = alertService.getPanchayatDetailsByMandalId(jObj.getLong("mandalId"),jObj.getString("type"));
		}
		catch(Exception e){	
			LOG.error("Exception occured in getPanchayatiesByMandalOrMuncipality()",e);
		}
		
		return Action.SUCCESS;	
	}
	
	public String getAllMandalsByDistrictID()
	{
		try{
			jObj = new JSONObject(getTask());
			idNameVOList = alertService.getAllMandalsByDistrictID(jObj.getLong("districtId"));			
		}
		catch (Exception e) {
			LOG.error("Exception rised in getAllMandalsByDistrictID",e);
		}
		return Action.SUCCESS;
	}
	public String getAlertDetailsByStatusId(){
		try{
			jObj = new JSONObject(getTask());
			
			alertVOs = alertService.getAlertDetailsByStatusId(jObj.getLong("alertStatusId"),jObj.getString("mobileNo"),jObj.getString("fromDate"),jObj.getString("toDate"));    
		}catch(Exception e) {
			LOG.error("Exception occured in getAlertDetailsByStatusId() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getAlertCallerDetails(){
		try{
			jObj = new JSONObject(getTask());
			
			alertVOs = alertService.getAlertCallerDetails(jObj.getLong("alertId"));    
		}catch(Exception e) {
			LOG.error("Exception occured in getAlertCallerDetails() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String saveAlertStatus()
	{
		try{
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();

			
			jObj = new JSONObject(getTask());
			AlertVO alertVO = new AlertVO();
			alertVO.setAlertId(jObj.getLong("alertId"));
			alertVO.setComment(jObj.getString("comment"));
			alertVO.setStatusId(jObj.getLong("alertStatusId"));
			alertVO.setFeedBackStatusId(jObj.getLong("alertFeedBackStatusId"));
			alertVO.setAlertSourceId(jObj.getLong("alertSourceId"));
			
			status = alertService.saveAlertStatusDetails(alertVO,userId);
		}
		catch (Exception e) {
			LOG.error("Exception rised in saveAlertStatus",e);
		}
		return Action.SUCCESS;	
	}
	public String getAlertFeedBackStatusDetails(){
		try{
			
			alertVOs = alertService.getFeedbackStatusDetails();    
		}catch(Exception e) {
			LOG.error("Exception occured in getAlertFeedBackStatusDetails() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getDesignationsByDepartmentNew(){
	   try {
			jObj = new JSONObject(getTask());
			Long departmentId = jObj.getLong("departmentId");
			Long levelId = jObj.getLong("levelId");
			Long levelValue = jObj.getLong("levelValue");
		
			govtDeptVoList = alertService.getDesignationsByDepartment(departmentId,levelId,levelValue);
	   } catch (Exception e) {
		   LOG.error("Exception Raised in getDesignationsByDepartment() in AlertManagementSystemAction",e);
		}
		   return Action.SUCCESS;
	}
	
	public String getOfficersByDesignationAndLevelNew(){
		   try {
				jObj = new JSONObject(getTask());
				Long levelId = jObj.getLong("levelId");
				Long levelValue = jObj.getLong("levelValue");
				Long designationId = jObj.getLong("designationId");
			
				govtDeptVoList = alertService.getOfficersByDesignationAndLevel(levelId,levelValue,designationId);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getOfficersByDesignationAndLevel() in AlertManagementSystemAction",e);
			}
			   return Action.SUCCESS;
	}
	
	public String getAlertIssueSubTypes(){
		   try {
				jObj = new JSONObject(getTask());
				Long issueTypeId = jObj.getLong("issueTypeId");
				
				keyValueVOList = alertService.getAlertIssueSubTypes(issueTypeId);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getAlertIssueSubTypes() in CreateAlertAction",e);
			}
			   return Action.SUCCESS;
	}
}

//getTotalAlertGroupByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList)
