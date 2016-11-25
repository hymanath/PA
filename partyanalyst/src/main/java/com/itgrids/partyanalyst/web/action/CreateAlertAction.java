package com.itgrids.partyanalyst.web.action;

import java.io.InputStream;

import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AlertDataVO;
import com.itgrids.partyanalyst.dto.AlertInputVO;
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

	public String execute()
	{
		session = request.getSession();
		RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
		if(regVo.getEntitlements() != null && regVo.getEntitlements().contains(IConstants.CREATE_ALERT_ENTITLEMENT))
		return Action.SUCCESS;
		else
			return Action.ERROR;	
	}
	public String alertDashboardExe()
	{
		session = request.getSession();
		RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
		if(regVo.getEntitlements() != null && regVo.getEntitlements().contains(IConstants.ALERT_DASHBOARD_ENTITLEMENT))
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
			
			statusTrackingVOList = alertService.getAlertStatusCommentsTrackingDetails(alertId);
			
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
			alertVOs = alertService.getTotalAlertGroupByStatus(fromDate, toDate, stateId);
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
			alertVOs = alertService.getTotalAlertGroupByStatusThenCategory(fromDate, toDate, stateId);
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
			alertVOs = alertService.getAlertCountGroupByLocationThenStatus(fromDate, toDate, stateId);
		}catch(Exception e) {
			LOG.error("Exception occured in getTotalAlertGroupByStatus() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	
}
