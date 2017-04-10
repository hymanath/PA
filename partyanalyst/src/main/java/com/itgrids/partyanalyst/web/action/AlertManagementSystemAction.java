package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AlertAssigningVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertTrackingVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.DistrictOfficeViewAlertVO;
import com.itgrids.partyanalyst.dto.GovtDepartmentVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IAlertManagementSystemService;
import com.itgrids.partyanalyst.service.ICccDashboardService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Teja
 *
 */
public class AlertManagementSystemAction extends ActionSupport implements ServletRequestAware{

	private final static Logger LOG = Logger.getLogger(AlertManagementSystemAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private JSONObject jObj;
	private String task;
	private DistrictOfficeViewAlertVO districtOfficeViewAlertVO;
	private ICccDashboardService cccDashboardService;
	private List<IdAndNameVO> newsPaperList;
	private List<IdAndNameVO> chanelList;
	private List<IdAndNameVO> chanelListNew;
	private List<IdAndNameVO> deptList;
	private List<IdAndNameVO> deptListNew;
	private List<IdAndNameVO> locationLevelList;
	private IAlertManagementSystemService alertManagementSystemService;
	private List<AlertVO> alertVOs;
	private List<IdNameVO> idnameVoList;
	private List<GovtDepartmentVO> govtDeptVoList = new ArrayList<GovtDepartmentVO>(0);
	
	private String successMsg;
	private AlertAssigningVO alertAssigningVO;	
	private IdNameVO idNameVO;
	private ResultStatus resultStatus;
	private List<AlertCoreDashBoardVO> alertCoreDashBoardVOs;
	private AlertVO alertVO;
	private List<DistrictOfficeViewAlertVO> districtOfficeViewAlertVOList;
	private Long alertId;
	private List<AlertTrackingVO> alertTrackingVOList;
	
	
	public List<AlertTrackingVO> getAlertTrackingVOList() {
		return alertTrackingVOList;
	}
	public void setAlertTrackingVOList(List<AlertTrackingVO> alertTrackingVOList) {
		this.alertTrackingVOList = alertTrackingVOList;
	}
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	public List<DistrictOfficeViewAlertVO> getDistrictOfficeViewAlertVOList() {
		return districtOfficeViewAlertVOList;
	}
	public void setDistrictOfficeViewAlertVOList(
			List<DistrictOfficeViewAlertVO> districtOfficeViewAlertVOList) {
		this.districtOfficeViewAlertVOList = districtOfficeViewAlertVOList;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public List<AlertCoreDashBoardVO> getAlertCoreDashBoardVOs() {
		return alertCoreDashBoardVOs;
	}
	public void setAlertCoreDashBoardVOs(
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs) {
		this.alertCoreDashBoardVOs = alertCoreDashBoardVOs;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public ICccDashboardService getCccDashboardService() {
		return cccDashboardService;
	}
	public IAlertManagementSystemService getAlertManagementSystemService() {
		return alertManagementSystemService;
	}
	public AlertVO getAlertVO() {
		return alertVO;
	}
	public void setAlertVO(AlertVO alertVO) {
		this.alertVO = alertVO;
	}
	public IdNameVO getIdNameVO() {
		return idNameVO;
	}
	public void setIdNameVO(IdNameVO idNameVO) {
		this.idNameVO = idNameVO;
	}
	public AlertAssigningVO getAlertAssigningVO() {
		return alertAssigningVO;
	}
	public void setAlertAssigningVO(AlertAssigningVO alertAssigningVO) {
		this.alertAssigningVO = alertAssigningVO;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	public List<GovtDepartmentVO> getGovtDeptVoList() {
		return govtDeptVoList;
	}
	public void setGovtDeptVoList(List<GovtDepartmentVO> govtDeptVoList) {
		this.govtDeptVoList = govtDeptVoList;
	}
	public List<IdNameVO> getIdnameVoList() {
		return idnameVoList;
	}
	public void setIdnameVoList(List<IdNameVO> idnameVoList) {
		this.idnameVoList = idnameVoList;
	}
	public DistrictOfficeViewAlertVO getDistrictOfficeViewAlertVO() {
		return districtOfficeViewAlertVO;
	}
	public void setDistrictOfficeViewAlertVO(
			DistrictOfficeViewAlertVO districtOfficeViewAlertVO) {
		this.districtOfficeViewAlertVO = districtOfficeViewAlertVO;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;    
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
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
   	public void setCccDashboardService(ICccDashboardService cccDashboardService) {
		this.cccDashboardService = cccDashboardService;
	}
    public List<IdAndNameVO> getNewsPaperList() {
		return newsPaperList;
	}
	public void setNewsPaperList(List<IdAndNameVO> newsPaperList) {
		this.newsPaperList = newsPaperList;
	}
	public List<IdAndNameVO> getChanelList() {
		return chanelList;
	}
	public void setChanelList(List<IdAndNameVO> chanelList) {
		this.chanelList = chanelList;
	}
	public List<IdAndNameVO> getChanelListNew() {
		return chanelListNew;
	}
	public void setChanelListNew(List<IdAndNameVO> chanelListNew) {
		this.chanelListNew = chanelListNew;
	}
	public List<IdAndNameVO> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<IdAndNameVO> deptList) {
		this.deptList = deptList;
	}
	public List<IdAndNameVO> getDeptListNew() {
		return deptListNew;
	}
	public void setDeptListNew(List<IdAndNameVO> deptListNew) {
		this.deptListNew = deptListNew;
	}
	public List<IdAndNameVO> getLocationLevelList() {
		return locationLevelList;
	}
	public void setLocationLevelList(List<IdAndNameVO> locationLevelList) {
		this.locationLevelList = locationLevelList;
	}
	public void setAlertManagementSystemService(
			IAlertManagementSystemService alertManagementSystemService) {
		this.alertManagementSystemService = alertManagementSystemService;
	}
	public List<AlertVO> getAlertVOs() {
		return alertVOs;
	}
	public void setAlertVOs(List<AlertVO> alertVOs) {
		this.alertVOs = alertVOs;
	}
	public String execute(){
		    session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			newsPaperList = cccDashboardService.getNewsPapaerList();
			chanelListNew = cccDashboardService.getChannelList();
			chanelList = cccDashboardService.getChannelListForUser(userId);  
			deptListNew = cccDashboardService.getDeptList(); 
			deptList = alertManagementSystemService.getDeptListForUser(userId);
		return Action.SUCCESS;
		  
	  }
	public String getDepartmentDetails(){
		try{
			session = request.getSession();
		   	/*RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();*/
			
			//officerName = cccDashboardService.getDesignationForUser(userId);
			//session.setAttribute("officerName", officerName);
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception occured in getDepartmentDetails() of CccDashboardAction",e);
		}
		return Action.SUCCESS;
	}
	public String getStatusWiseAlertOverviewCnt(){
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
			
			alertVOs = alertManagementSystemService.getStatusWiseAlertOverviewcnt(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,userId);
		}catch(Exception e){
			LOG.error("Exception occured in getStatusWiseAlertOverviewCnt() of AlertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String getLevelWiseAlertOverviewCnt(){
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
			
			alertVOs = alertManagementSystemService.getLevelWiseAlertOverviewCnt(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,userId);
		}catch(Exception e){
			LOG.error("Exception occured in getLevelWiseAlertOverviewCnt() of AlertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String getDepartmentWiseAlertOverviewCnt(){
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
			JSONArray alertStatusIdArr = jObj.getJSONArray("alertStatusIdArr");  
			List<Long> alertStatusIds = new ArrayList<Long>();
			for (int i = 0; i < alertStatusIdArr.length(); i++){
				alertStatusIds.add(Long.parseLong(alertStatusIdArr.getString(i)));        
			}
			JSONArray deptScopeLevelIdArr = jObj.getJSONArray("deptScopeLevelIdArr");  
			List<Long> deptScopeLevelIds = new ArrayList<Long>();
			for (int i = 0; i < deptScopeLevelIdArr.length(); i++){
				deptScopeLevelIds.add(Long.parseLong(deptScopeLevelIdArr.getString(i)));        
			}
			String resultType = jObj.getString("resultType");
			alertVOs = alertManagementSystemService.getDepartmentWiseAlertOverviewCnt(fromDate,toDate,stateId,paperIdList,chanelIdList,deptIdList,userId,alertStatusIds,deptScopeLevelIds,resultType);;
		}catch(Exception e){
			LOG.error("Exception occured in getDepartmentWiseAlertOverviewCnt() of AlertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String getDepartmentStatus(){
		try{
			alertVOs = alertManagementSystemService.getDepartmentStatus();
		}catch(Exception e){
			LOG.error("Exception occured in getDepartmentStatus() of AlertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String getDepartmentScope(){
		try{
			alertVOs = alertManagementSystemService.getDepartmentScope();
		}catch(Exception e){
			LOG.error("Exception occured in getDepartmentScope() of AlertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String getDistrictOfficerAlertsCountView(){
		try{
			/*session = request.getSession();
		   	RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		   	
		   	Long userId = null;
		   	if(user != null && user.getRegistrationID() != null){
		   		userId = user.getRegistrationID();
			}*/
			jObj = new JSONObject(getTask());
			Long userId = jObj.getLong("userId");
		   	districtOfficeViewAlertVO = alertManagementSystemService.getDistrictOfficerAlertsCountView(userId);
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception occured in getDistrictOfficerAlertsCountView() of AlertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String updateComment(){
		try {
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			if(regVo == null)
				return null;
			
			Long userId = regVo.getRegistrationID();
			
			jObj = new JSONObject(getTask());
			
			resultStatus = alertManagementSystemService.updateComment(jObj.getLong("alertId"),jObj.getString("comment"),userId);
		} catch (Exception e) {
			LOG.error("Exception occured in updateComment() of CccDashboardAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String updateAlertPriority(){
		try {
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			if(regVo == null)
				return null;
			
			Long userId = regVo.getRegistrationID();
			
			jObj = new JSONObject(getTask());
			
			resultStatus = alertManagementSystemService.updateAlertPriority(jObj.getLong("alertId"),jObj.getLong("priorityId"),userId);
		} catch (Exception e) {
			LOG.error("Exception occured in updateComment() of CccDashboardAction",e);
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
				alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,null);
				alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
			}else if(statusId != null && statusId.longValue() > 1L){//other than pending
				alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByOtherStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,userId,null,null);
				alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
			}else{
				alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,1L,null);
				List<AlertCoreDashBoardVO> list1 = new ArrayList<AlertCoreDashBoardVO>();
				if(alertCoreDashBoardVOs != null){
					list1.addAll(alertCoreDashBoardVOs);
				}
				alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByOtherStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,userId,null,null);
				List<AlertCoreDashBoardVO> list2 = new ArrayList<AlertCoreDashBoardVO>();
				if(alertCoreDashBoardVOs != null ){
					list2.addAll(alertCoreDashBoardVOs);
				}
				alertCoreDashBoardVOs.clear();
				alertCoreDashBoardVOs.addAll(list1);
				alertCoreDashBoardVOs.addAll(list2);
				alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
			}
			
		}catch(Exception e){
			LOG.error("Exception occured in getAlertDetailsForEdit() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getDistrictLevelDeptWiseFilterViewDetails(){
		try{
			session = request.getSession();
		   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long scopeId = regVo.getRegistrationID();
			jObj = new JSONObject(getTask());
			String startDateStr = jObj.getString("startDate");
			String fromDateStr = jObj.getString("fromDate");
			String type = jObj.getString("type");
			
			alertVOs = alertManagementSystemService.getDistrictLevelDeptWiseFilterView(scopeId,startDateStr,fromDateStr,type);
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception occured in getDistrictLevelDeptWiseFilterView() of alertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String getTotalAlertBylocationLvl(){
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
			Long govtDeptScopeId = jObj.getLong("govtDeptScopeId");
			
			alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByOtherStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,userId,govtDeptScopeId,null);
			alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
		}catch(Exception e){
			LOG.error("Exception occured in getAlertDetailsForEdit() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getTotalAlertByStatusThenDept(){
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
			Long deptId = jObj.getLong("deptId");
			if(statusId != null && statusId.longValue() == 1L){//pending
				alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,deptId);
				alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
			}else if(statusId != null && statusId.longValue() > 1L){//other than pending
				alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByOtherStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,userId,null,deptId);
				alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
			}else{
				alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,1L,deptId);
				List<AlertCoreDashBoardVO> list1 = new ArrayList<AlertCoreDashBoardVO>();
				if(alertCoreDashBoardVOs != null){
					list1.addAll(alertCoreDashBoardVOs);
				}
				alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByOtherStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,userId,null,deptId);
				List<AlertCoreDashBoardVO> list2 = new ArrayList<AlertCoreDashBoardVO>();
				if(alertCoreDashBoardVOs != null ){
					list2.addAll(alertCoreDashBoardVOs);
				}
				alertCoreDashBoardVOs.clear();
				alertCoreDashBoardVOs.addAll(list1);
				alertCoreDashBoardVOs.addAll(list2);
				alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
			}
			
		}catch(Exception e){
			LOG.error("Exception occured in getAlertDetailsForEdit() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getTotalAlertBylocationLvlThenDept(){
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
			Long govtDeptScopeId = jObj.getLong("govtDeptScopeId");      
			Long deptId = jObj.getLong("deptId");
			alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByOtherStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,userId,govtDeptScopeId,deptId);
			alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
		}catch(Exception e){
			LOG.error("Exception occured in getAlertDetailsForEdit() of CreateAlertAction",e);
		}
		return Action.SUCCESS;
	}
	public String getDistrictLevelDeptWiseStatusOverView(){
		try{
			session = request.getSession();
		   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long scopeId = regVo.getRegistrationID();
			jObj = new JSONObject(getTask());
			
			String startDateStr = jObj.getString("startDate");
			String fromDateStr = jObj.getString("fromDate");
			String type = jObj.getString("type");
			Long deptId = jObj.getLong("deptId");
			String sortingType = jObj.getString("sortingType");
			alertVOs = alertManagementSystemService.getDistrictLevelDeptWiseStatusOverView(scopeId,startDateStr,fromDateStr,type,deptId,sortingType);
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception occured in getDistrictLevelDeptWiseStatusOverView() of alertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String getDistrictLevelDeptWiseLocationLevelView(){
		try{
			session = request.getSession();
		   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long scopeId = regVo.getRegistrationID();
			jObj = new JSONObject(getTask());
			
			String startDateStr = jObj.getString("startDate");
			String fromDateStr = jObj.getString("fromDate");
			String type = jObj.getString("type");
			Long deptId = jObj.getLong("deptId");
			String sortingType = jObj.getString("sortingType");
			alertVOs = alertManagementSystemService.getDistrictLevelDeptWiseLocationLevelView(scopeId,startDateStr,fromDateStr,type,deptId,sortingType);
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception occured in getDistrictLevelDeptWiseStatusOverView() of alertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getGovtDepartmentDetails(){
		try {
			//jObj = new JSONObject(getTask());
			alertVOs = alertManagementSystemService.getGovtDepartmentDetails();
		} catch (Exception e) {
			LOG.error("Exception occured in getGovtDepartmentDetails() of alertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String getGovtDeptScopeDetails(){
		try {
			//jObj = new JSONObject(getTask());
			alertVOs = alertManagementSystemService.getGovtDeptScopeDetails();
		} catch (Exception e) {
			LOG.error("Exception occured in getGovtDeptScopeDetails() of alertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String getSubOrdinateAlertsOverview(){
		try {
			jObj = new JSONObject(getTask());
			
			Long userId = jObj.getLong("userId");
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDateStr");
			
			
			JSONArray govtScopeIds = jObj.getJSONArray("govtScopeIds");  
			List<Long> govtScopeIdsList = new ArrayList<Long>();
			if(govtScopeIds != null && govtScopeIds.length() > 0){
				for (int i = 0; i < govtScopeIds.length(); i++){
					govtScopeIdsList.add(Long.parseLong(govtScopeIds.getString(i)));        
				} 
			} 
			JSONArray locationValues = jObj.getJSONArray("locationValues");  
			List<Long> locationValuesList = new ArrayList<Long>();
			if(locationValues != null && locationValues.length() > 0){
				for (int i = 0; i < locationValues.length(); i++){
					locationValuesList.add(Long.parseLong(locationValues.getString(i)));        
				} 
			} 
			JSONArray desigIds = jObj.getJSONArray("desigIds");  
			List<Long> desigIdsList = new ArrayList<Long>();
			if(desigIds != null && desigIds.length() > 0){
				for (int i = 0; i < desigIds.length(); i++){
					desigIdsList.add(Long.parseLong(desigIds.getString(i)));        
				} 
			} 
			Long priorityId = jObj.getLong("priorityId");			
			districtOfficeViewAlertVOList = alertManagementSystemService.getSubOrdinateAlertsOverview(userId,fromDateStr,toDateStr,govtScopeIdsList,locationValuesList,desigIdsList,priorityId);
		} catch (Exception e) {
			LOG.error("Exception occured in getSubOrdinateAlertsOverview() of alertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String getSubOrdinateLevels(){
		try{
			session = request.getSession();
		   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long scopeId = regVo.getRegistrationID();
			jObj = new JSONObject(getTask());
			
			Long designationId = jObj.getLong("designationId");
			deptListNew = alertManagementSystemService.getSubOrdinateLevels(designationId);
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception occured in getSubOrdinateLevels() of alertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String updateAlertDueDate(){
		try {
			session = request.getSession();
		   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
		   	
		   	if(regVo == null)
		   		return null;
		   	
		   	jObj = new JSONObject(getTask());
		   	
			resultStatus = alertManagementSystemService.updateAlertDueDate(jObj.getLong("alertId"),jObj.getString("date"),regVo.getRegistrationID());
		} catch (Exception e) {
			LOG.error("Exception occured in updateAlertDueDate() of alertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String updateAlertStatusComment(){
		try {
			session = request.getSession();
		   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
		   	
		   	if(regVo == null)
		   		return null;
		   	
		   	jObj = new JSONObject(getTask());
		   	
		   	resultStatus = alertManagementSystemService.updateAlertStatusComment(jObj.getLong("alertId"),jObj.getLong("statusId"),jObj.getString("comment"),regVo.getRegistrationID());
			
		} catch (Exception e) {
			LOG.error("Exception occured in updateAlertStatusComment() of alertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	} 
	
	public String uploadDocumentsForAlert(){
		try{
			
			Long userId = 0l;
			
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVo!=null && regVo.getRegistrationID()!=null){
				userId = regVo.getRegistrationID();
			}
			
			Map<File,String> mapfiles = new HashMap<File,String>();
			MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
		       Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
		       String fileUrl = "" ;
		       List<String> filePaths = null;
		   		while(fileParams.hasMoreElements())
		   		{
		   			String key = fileParams.nextElement();
		   			
				   			File[] files = multiPartRequestWrapper.getFiles(key);
				   			filePaths = new ArrayList<String>();
				   			if(files != null && files.length > 0)
				   			for(File f : files)
				   			{
				   				String[] extension  =multiPartRequestWrapper.getFileNames(key)[0].split("\\.");
				   	            String ext = "";
				   	            if(extension.length > 1){
				   	            	ext = extension[extension.length-1];
				   	            	mapfiles.put(f,ext);
				   	            }
				   	        
				   			}	
		   		}
		     
		   		resultStatus = alertManagementSystemService.uploadDocumentsForAlert(mapfiles,alertId ,userId);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in reportUploadForm() method, Exception - ",e); 
		}
		return Action.SUCCESS;	
	
	}
	
	public String viewAlertHistory(){
		try {
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVo == null)
				return null;
			
			jObj = new JSONObject(getTask());
			alertTrackingVOList = alertManagementSystemService.viewAlertHistory(jObj.getLong("alertId"),regVo.getRegistrationID());
			
			
		} catch (Exception e) {
			LOG.error("Exception Occured in viewAlertHistory() method, Exception - ",e); 
		}
		return Action.SUCCESS;	
	}
	public String getStateThenGovtDeptScopeWiseAlertCount(){
		try {
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			jObj = new JSONObject(getTask());
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long stateId = jObj.getLong("stateId");
			
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
			Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");      
			Long govtDepartmentId = jObj.getLong("govtDepartmentId");
			alertCoreDashBoardVOs = alertManagementSystemService.getStateThenGovtDeptScopeWiseAlertCount(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId);
		} catch (Exception e) {
			LOG.error("Exception Occured in getStateThenGovtDeptScopeWiseAlertCount() method, Exception - ",e); 
		}
		return Action.SUCCESS;	
	}
	
	public String getDistrictLevelDeptWiseFlterClick(){
		try{
			session = request.getSession();
		   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long scopeId = regVo.getRegistrationID();
			jObj = new JSONObject(getTask());
			Long deptId = jObj.getLong("deptId");
			Long levelId = jObj.getLong("levelId");
			Long statusId = jObj.getLong("statusId");
			alertCoreDashBoardVOs = alertManagementSystemService.getDistrictLevelDeptWiseFlterClick(scopeId,deptId,levelId,statusId);
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception occured in getDistrictLevelDeptWiseFlterClick() of alertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getDepartmentLevels(){
		try {
			
			jObj = new JSONObject(getTask());
			Long departmentId = jObj.getLong("departmentId");
			
			idnameVoList = alertManagementSystemService.getDepartmentLevels(departmentId);
			
			
		} catch (Exception e) {
			LOG.error("Exception occured in getDepartmentLevels() of AlertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getParentLevelsOfLevel(){
		try {
			
			jObj = new JSONObject(getTask());
			Long departmentId = jObj.getLong("departmentId");
			Long levelId = jObj.getLong("levelId");
			
			idnameVoList = alertManagementSystemService.getParentLevelsOfLevel(departmentId,levelId);
			
			
		} catch (Exception e) {
			LOG.error("Exception occured in getParentLevelsOfLevel() of AlertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getDesignationsByDepartmentNew(){
		   try {
				jObj = new JSONObject(getTask());
				Long departmentId = jObj.getLong("departmentId");
				Long levelId = jObj.getLong("levelId");
				Long levelValue = jObj.getLong("levelValue");
			
				govtDeptVoList = alertManagementSystemService.getDesignationsByDepartment(departmentId,levelId,levelValue);
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
			
				govtDeptVoList = alertManagementSystemService.getOfficersByDesignationAndLevel(levelId,levelValue,designationId);
		   } catch (Exception e) {
			   LOG.error("Exception Raised in getOfficersByDesignationAndLevel() in AlertManagementSystemAction",e);
			}
			   return Action.SUCCESS;
	}
	
	public String assigningAlertToOfficerNew(){
		   try {
			   session = request.getSession();
			   RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			   if(regVo == null)
				   successMsg = "failure";
			   Long userId = regVo.getRegistrationID();
			   alertAssigningVO.setUserId(userId);
			 
			   
			   successMsg = alertManagementSystemService.assigningAlertToOfficer(alertAssigningVO);
			   
			} catch (Exception e) {
				LOG.error("Exception Raised in assigningAlertToOfficerNew() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
	public String assigningSubTaskToOfficer(){
		   try {
			   session = request.getSession();
			   RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			   if(regVo == null)
				   successMsg = "failure";
			   Long userId = regVo.getRegistrationID();
			   alertAssigningVO.setUserId(userId);
			 
			   
			   successMsg = alertManagementSystemService.assigningSubTaskToOfficer(alertAssigningVO);
			   
			} catch (Exception e) {
				LOG.error("Exception Raised in assigningSubTaskToOfficer() in CccDashboardAction",e);
			}
			   return Action.SUCCESS;
		}
	
		public String getGovtSubLevelInfo(){
			   try {
				   jObj = new JSONObject(getTask());
				   	Long departmentId = jObj.getLong("departmentId");
					Long levelId = jObj.getLong("levelId");
					Long levelValue = jObj.getLong("levelValue");
				   
					idNameVO = alertManagementSystemService.getGovtSubLevelInfo(departmentId,levelId,levelValue);
				   
				} catch (Exception e) {
					LOG.error("Exception Raised in getGovtSubLevelInfo() in CccDashboardAction",e);
				}
				   return Action.SUCCESS;
			}
		
	 public String alertDistManagement(){
		 return Action.SUCCESS;
	 }
	 
	 public String alertDistOfficeManagement(){
		 return Action.SUCCESS; 
	 }
}
