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
import com.itgrids.partyanalyst.dto.KeyValueVO;
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
	
	private List<GovtDepartmentVO> govtDeptVoList1;
	private String successMsg;
	private AlertAssigningVO alertAssigningVO;	
	private IdNameVO idNameVO;
	private ResultStatus resultStatus;
	private List<AlertCoreDashBoardVO> alertCoreDashBoardVOs;
	private AlertVO alertVO;
	private List<DistrictOfficeViewAlertVO> districtOfficeViewAlertVOList;
	private Long alertId;
	private List<AlertTrackingVO> alertTrackingVOList;
	private List<AlertCoreDashBoardVO> alertCoreDashBoardVo;
	private Long subTaskId;
	private List<KeyValueVO> keyValueVOList;
	
	private List<File> imageForDisplay = new ArrayList<File>();
	private List<String> imageForDisplayContentType = new ArrayList<String>();
	private List<String> imageForDisplayFileName = new ArrayList<String>();
	
	
	
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
	public List<KeyValueVO> getKeyValueVOList() {
		return keyValueVOList;
	}
	public void setKeyValueVOList(List<KeyValueVO> keyValueVOList) {
		this.keyValueVOList = keyValueVOList;
	}
	public Long getSubTaskId() {
		return subTaskId;
	}
	public void setSubTaskId(Long subTaskId) {
		this.subTaskId = subTaskId;
	}
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
	
	public List<AlertCoreDashBoardVO> getAlertCoreDashBoardVo() {
		return alertCoreDashBoardVo;
	}
	public void setAlertCoreDashBoardVo(
			List<AlertCoreDashBoardVO> alertCoreDashBoardVo) {
		this.alertCoreDashBoardVo = alertCoreDashBoardVo;
	}
	
	public List<GovtDepartmentVO> getGovtDeptVoList1() {
		return govtDeptVoList1;
	}
	public void setGovtDeptVoList1(List<GovtDepartmentVO> govtDeptVoList1) {
		this.govtDeptVoList1 = govtDeptVoList1;
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
			session = request.getSession();
		   	RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		   	
		   	Long userId = null;
		   	if(user != null && user.getRegistrationID() != null){
		   		userId = user.getRegistrationID();
			}
			jObj = new JSONObject(getTask());
			//Long userId = jObj.getLong("userId");
			String startDate =jObj.getString("startDate");
			String endDate =jObj.getString("endDate");
		   	districtOfficeViewAlertVO = alertManagementSystemService.getDistrictOfficerAlertsCountView(userId,startDate,endDate);
			
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
			Long levelId = jObj.getLong("levelId");
			alertVOs = alertManagementSystemService.getDistrictLevelDeptWiseStatusOverView(scopeId,startDateStr,fromDateStr,type,deptId,sortingType,levelId);
			
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
			session = request.getSession();
		   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			jObj = new JSONObject(getTask());			
			//Long userId = jObj.getLong("userId");
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
			alertTrackingVOList = alertManagementSystemService.viewAlertHistory(jObj.getLong("alertId"));
			
			
		} catch (Exception e) {
			LOG.error("Exception Occured in viewAlertHistory() method, Exception - ",e); 
		}
		return Action.SUCCESS;	
	}
	/*
	 * overview
	 */
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
			String sortType = jObj.getString("sortType");
			String order = jObj.getString("order");
			Long districtWorkLocationId = jObj.getLong("districtWorkLocationId");
			Long divisionWorkLocationId = jObj.getLong("divisionWorkLocationId");
			Long subDivisionWorkLocationId = jObj.getLong("subDivisionWorkLocationId");
			String alertType = null;
			String group = jObj.getString("group");
			alertCoreDashBoardVOs = alertManagementSystemService.getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,sortType,order,alertType,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,group);
		} catch (Exception e) {
			LOG.error("Exception Occured in getStateThenGovtDeptScopeWiseAlertCount() method, Exception - ",e); 
		}
		return Action.SUCCESS;	
	}
	/*	getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverview(String fromDateStr, String toDateStr, Long stateId, 
		List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
			Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
			Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId)*/
	
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
			alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
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
				LOG.error("Exception Raised in assigningAlertToOfficerNew() in AlertManagementSystemAction",e);
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
				LOG.error("Exception Raised in assigningSubTaskToOfficer() in AlertManagementSystemAction",e);
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
					LOG.error("Exception Raised in getGovtSubLevelInfo() in AlertManagementSystemAction",e);
				}
				   return Action.SUCCESS;
			}
		public String getStateThenGovtDeptScopeWiseAlertCountStatusWise(){
			try {
				Long userId =  null;
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				 userId = regVo.getRegistrationID();
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
				String sortType = jObj.getString("sortType");
				String order = jObj.getString("order");
				String searchType = jObj.getString("alertType");
				alertCoreDashBoardVOs = alertManagementSystemService.getStateThenGovtDeptScopeWiseAlertCountStatusWise(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,sortType,order,searchType);
			} catch (Exception e) {
				LOG.error("Exception Occured in getStateThenGovtDeptScopeWiseAlertCount() method, Exception - ",e); 
			}
			return Action.SUCCESS;	
		}
		
		
	 public String alertDistManagement(){
		 return Action.SUCCESS;
	 }
	 
	 public String alertDistOfficeManagement(){
		 return Action.SUCCESS; 
	 }
	 public String getDeptListForMultiLvl(){    
		 try{
			 session = request.getSession();
			 RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			 Long userId = regVo.getRegistrationID();
			 idnameVoList = alertManagementSystemService.getDeptListForMultiLvl(userId);
			 
		 }catch(Exception e){
			 LOG.error("Exception Occured in getDeptListForMultiLvl() method, Exception - ",e); 
		 }
		 return Action.SUCCESS;
	 }
		public String getDistrictOfficerAlertDetails(){
			try{
				session = request.getSession();
			   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				Long scopeId = regVo.getRegistrationID();
				jObj = new JSONObject(getTask());
				JSONArray alertIdArr = jObj.getJSONArray("alertIdArr");  
				List<Long> alertIdList = new ArrayList<Long>();
				for (int i = 0; i < alertIdArr.length(); i++){
					alertIdList.add(Long.parseLong(alertIdArr.getString(i)));        
				}
				alertCoreDashBoardVOs = alertManagementSystemService.getDistrictOfficerAlertDetails(alertIdList);
				
				alertCoreDashBoardVOs =alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Exception occured in getDistrictOfficeralertDetails() of alertManagementSystemAction",e);
			}
			return Action.SUCCESS;
		}
		public String getAlertStatusHistory(){
			try {
				jObj = new JSONObject(getTask());
				alertTrackingVOList = alertManagementSystemService.getAlertStatusHistory(jObj.getLong("alertId"));
			} catch (Exception e) {
				LOG.error("Exception Raised in getAlertStatusHistory() in alertManagementSystemAction",e);
			}
			return Action.SUCCESS;
		}   
		public String getStateThenGovtDeptScopeWiseAlertCountStatus(){
			try {
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				Long userId = regVo.getRegistrationID();
				jObj = new JSONObject(getTask());
				String fromDate = jObj.getString("fromDate");
				String toDate = jObj.getString("toDate");  
				Long stateId = jObj.getLong("stateId");
				
				JSONArray paperIdArr = jObj.getJSONArray("paperIdArr");  
			
				Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");      
				Long govtDepartmentId = jObj.getLong("govtDepartmentId");
				String sortType = jObj.getString("sortType");
				String order = jObj.getString("order");
				String searchType =jObj.getString("searchType");
				String alertType = jObj.getString("alertType");
				if(searchType.equalsIgnoreCase("status"))
				 alertCoreDashBoardVOs = alertManagementSystemService.getStateThenGovtDeptScopeWiseAlertCountStatusWise(fromDate,toDate,stateId,null,null,userId,govtDepartmentId,parentGovtDepartmentScopeId,sortType,order,alertType);
				else if(searchType.equalsIgnoreCase("scopes"))
				  alertCoreDashBoardVOs=alertManagementSystemService.getDistrictOfficerScopesWiseAlerts(fromDate,toDate,stateId,userId,govtDepartmentId,parentGovtDepartmentScopeId,sortType,order,alertType);
					
			} catch (Exception e) {
				LOG.error("Exception Occured in getStateThenGovtDeptScopeWiseAlertCount() method, Exception - ",e); 
			}
			return Action.SUCCESS;	
		}
		public String getAssignedOfficersDetails(){
			   try {
					jObj = new JSONObject(getTask());
					Long alertId = jObj.getLong("alertId");
				
					govtDeptVoList1 = alertManagementSystemService.getAssignedOfficersDetails(alertId);
			   } catch (Exception e) {
				   LOG.error("Exception Raised in getAssignedOfficersDetails() in CccDashboardAction",e);
				}
				   return Action.SUCCESS;
			}
	 
	 public String getDepartmentSubLevels(){
		 try {
			 jObj = new JSONObject(getTask());
			   	Long departmentId = jObj.getLong("departmentId");
			   	Long parentLevelId = jObj.getLong("levelId");
			   	
			   	idnameVoList = alertManagementSystemService.getDepartmentSubLevels(departmentId,parentLevelId);
			 
		} catch (Exception e) {
			LOG.error("Exception Raised in getDepartmentSubLevels() in CccDashboardAction",e);
		}
		 return Action.SUCCESS;
	 }
	 
	 public String getChildLevelValuesForSubTask(){
		 try {
			 jObj = new JSONObject(getTask());
			   	Long departmentId = jObj.getLong("departmentId");
			   	Long parentLevelId = jObj.getLong("parentLevelId");
			   	Long parentLevelValue = jObj.getLong("parentLevelValue");
			   	Long levelId = jObj.getLong("levelId");
			   	
			   	List<Long> parentLevelValues = new ArrayList<Long>();
			   	parentLevelValues.add(parentLevelValue);
			   	
			   	idnameVoList = alertManagementSystemService.getChildLevelValuesForSubTask(departmentId,parentLevelId,parentLevelValues,levelId);
			 
		} catch (Exception e) {
			LOG.error("Exception Raised in getChildLevelValuesForSubTask() in CccDashboardAction",e);
		}
		 return Action.SUCCESS;
	 }
	 public String getStateThenGovtDeptScopeWiseAlertCountOnClick(){
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
				Long locationId = jObj.getLong("locationId");
				Long childLocationId = jObj.getLong("childLocationId");  
				String category = jObj.getString("category");
				alertCoreDashBoardVOs = alertManagementSystemService.getStateThenGovtDeptScopeWiseAlertCountOnClick(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,locationId,childLocationId,category);
				alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
			} catch (Exception e) {
				LOG.error("Exception Occured in getStateThenGovtDeptScopeWiseAlertCount() method, Exception - ",e); 
			}
			return Action.SUCCESS;	     
		}
	 
	 public String updateSubTaskComment(){
			try {
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				if(regVo == null)
					return null;
				
				jObj = new JSONObject(getTask());
				
				resultStatus = alertManagementSystemService.updateSubTaskComment(jObj.getLong("subTaskId"),jObj.getString("comment"),regVo.getRegistrationID());
			} catch (Exception e) {
				LOG.error("Exception Raised in updateSubTaskComment() in alertManagementSystemAction",e);
			}
			return Action.SUCCESS;
		}

		public String updateSubTaskStatusComment(){
			try {
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				if(regVo == null)
					return null;
				
				jObj = new JSONObject(getTask());
				
				resultStatus = alertManagementSystemService.updateSubTaskStatusComment(jObj.getLong("subTaskId"),jObj.getLong("statusId"),jObj.getString("comment"),regVo.getRegistrationID());
			} catch (Exception e) {
				LOG.error("Exception Raised in updateSubTaskStatusComment() in alertManagementSystemAction",e);
			}
			return Action.SUCCESS;
			
		}

		public String updateSubTaskPriority(){
			try {
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				if(regVo == null)
					return null;
				
				jObj = new JSONObject(getTask());
				
				resultStatus = alertManagementSystemService.updateSubTaskPriority(jObj.getLong("subTaskId"),jObj.getLong("priorityId"),jObj.getLong("userId"));
			} catch (Exception e) {
				LOG.error("Exception Raised in updateSubTaskPriority() in alertManagementSystemAction",e);
			}
			return Action.SUCCESS;
		}

		public String updateSubTaskDueDate(){
			try {
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				if(regVo == null)
					return null;
				
				jObj = new JSONObject(getTask());
				
				resultStatus = alertManagementSystemService.updateSubTaskDueDate(jObj.getLong("subTaskId"),jObj.getString("dueDate"),regVo.getRegistrationID());
			} catch (Exception e) {
				LOG.error("Exception Raised in updateSubTaskDueDate() in alertManagementSystemAction",e);
			}
			return Action.SUCCESS;
		}

		public String uploadDocumentsForSubTask(){
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
				 
					resultStatus = alertManagementSystemService.uploadDocumentsForSubTask(mapfiles,subTaskId ,userId);
				
			}catch (Exception e) {
				LOG.error("Exception Occured in uploadDocumentsForSubTask() method, Exception - ",e); 
			}
			return Action.SUCCESS;	

		}

		public String viewSubTaskHistory(){
			try {
				
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				if(regVo == null)
					return null;
				
				jObj = new JSONObject(getTask());
				
				alertTrackingVOList = alertManagementSystemService.viewSubTaskHistory(jObj.getLong("subTaskId"));
			} catch (Exception e) {
				LOG.error("Exception Occured in viewSubTaskHistory() method, Exception - ",e); 
			}
			return Action.SUCCESS;	
		}

		public String getSubTaskStatusHistory(){
			try {
				
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				if(regVo == null)
					return null;
				
				jObj = new JSONObject(getTask());
				
				alertTrackingVOList = alertManagementSystemService.getSubTaskStatusHistory(jObj.getLong("subTaskId"));
				
			} catch (Exception e) {
				LOG.error("Exception Occured in getSubTaskStatusHistory() method, Exception - ",e); 
			}
			return Action.SUCCESS;	
		}
		public String getStatusCompletionInfo(){
			try {
				
				jObj = new JSONObject(getTask());
				Long alertId = jObj.getLong("alertId");
				Long levelValue = jObj.getLong("levelValue");
				
				idnameVoList = alertManagementSystemService.getStatusCompletionInfo(alertId,levelValue);
				
			} catch (Exception e) {
				LOG.error("Exception Occured in getStatusCompletionInfo() method, Exception - ",e);
			}
			return Action.SUCCESS;	
		}
		
		public String getSubTaskInfoForAlert(){
			try {
				
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				if(regVo == null)
					return null;
				
				jObj = new JSONObject(getTask());
				alertTrackingVOList = alertManagementSystemService.getSubTaskInfoForAlert(jObj.getLong("alertId"));
			} catch (Exception e) {
				LOG.error("Exception Occured in getSubTaskInfoForAlert() method, Exception - ",e); 
			}
			return Action.SUCCESS;	
		}
		public String getCommentsForAlert(){
			try {
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				if(regVo == null)
					return null;
				
				jObj = new JSONObject(getTask());
				alertTrackingVOList = alertManagementSystemService.getCommentsForAlert(jObj.getLong("alertId"));
			} catch (Exception e) {
				LOG.error("Exception Occured in getCommentsForAlert() method, Exception - ",e); 
			}
			return Action.SUCCESS;	
			
		}
		
		public String getSubTaskFullDetails(){
			try {
				jObj = new JSONObject(getTask());
				alertVO = alertManagementSystemService.getSubTaskFullDetails(jObj.getLong("subTaskId"));
			} catch (Exception e) {
				LOG.error("Exception Occured in getSubTaskFullDetails() method, Exception - ",e); 
			}
			return Action.SUCCESS;	
		}
		public String getDistrictLevelWiseClick(){
			try{
				session = request.getSession();
			   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				Long userId = regVo.getRegistrationID();
				jObj = new JSONObject(getTask());		
				Long stateId = jObj.getLong("stateId");
				Long govtDepartmentId = jObj.getLong("govtDepartmentId");
				Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");
				Long statusId = jObj.getLong("statusId");
				Long childGovtScopeId = jObj.getLong("childGovtScopeId");
				String fromDateStr = jObj.getString("fromDate");
				String toDateStr = jObj.getString("toDate");
				//Long levelId = jObj.getLong("levelId");
				/*JSONArray levelValuesArr = jObj.getJSONArray("levelValues");  
					List<Long> levelValuesIdList = new ArrayList<Long>();
				 for (int i = 0; i < levelValuesArr.length(); i++){
					levelValuesIdList.add(Long.parseLong(levelValuesArr.getString(i)));        
				 }*/		 
				String status =jObj.getString("status");
				Long govtDeptWorkLocId = jObj.getLong("govtDeptWorkLocId");
				
				alertCoreDashBoardVOs = alertManagementSystemService.getDistrictLevelWiseClick(userId,fromDateStr,toDateStr,stateId,govtDepartmentId,parentGovtDepartmentScopeId,govtDeptWorkLocId,statusId,childGovtScopeId,status);
				alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Exception occured in getDistrictLevelWiseClick() of alertManagementSystemAction",e);
			}
			return Action.SUCCESS;
		}
	 public String stateLevelDeptOfficerStatusOverview(){
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
				
				alertVOs = alertManagementSystemService.stateLevelDeptOfficerStatusOverview(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,userId);
			}catch(Exception e){
				LOG.error("Exception occured in getStatusWiseAlertOverviewCnt() of AlertManagementSystemAction",e);
			}
			return Action.SUCCESS;
		}
	 public String stateLevelDeptOfficerLocationLevelOverview(){
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
				
				alertVOs = alertManagementSystemService.stateLevelDeptOfficerLocationLevelOverview(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,userId);
			}catch(Exception e){
				LOG.error("Exception occured in stateLevelDeptOfficerLocationLevelOverview() of AlertManagementSystemAction",e);
			}
			return Action.SUCCESS;
		}
	 public String getIASOfficerMyAlertsCountMainView(){
			try{
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				Long userId = regVo.getRegistrationID();
				jObj = new JSONObject(getTask());
			   	districtOfficeViewAlertVO = alertManagementSystemService.getIASOfficerMyAlertsCountView(userId);
				
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Exception occured in getIASOfficerMyAlertsCountView() of AlertManagementSystemAction",e);
			}
			return Action.SUCCESS;
		}
	 public String getIASOfficerMySubTasksCountView(){
			try{
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				Long userId = regVo.getRegistrationID();
				jObj = new JSONObject(getTask());
			   	districtOfficeViewAlertVO = alertManagementSystemService.getIASOfficerMySubTasksCountView(userId);
				
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Exception occured in getIASOfficerMySubTasksCountView() of AlertManagementSystemAction",e);
			}
			return Action.SUCCESS;
		}
	 public String getIASOfficerMyAssignedSubTasksCountView(){
			try{
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				Long userId = regVo.getRegistrationID();
				jObj = new JSONObject(getTask());
			   	districtOfficeViewAlertVO = alertManagementSystemService.getIASOfficerMyAssignedSubTasksCountView(userId);
				
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Exception occured in getIASOfficerMySubTasksCountView() of AlertManagementSystemAction",e);
			}
			return Action.SUCCESS;
		}
	 public String getDistIdListForDistFilter(){
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
				String group = jObj.getString("group");
				idnameVoList = alertManagementSystemService.getDistIdListForDistFilter(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,group);
			} catch (Exception e) {
				LOG.error("Exception Occured in getStateThenGovtDeptScopeWiseAlertCount() method, Exception - ",e); 
			}
			return Action.SUCCESS;	
		}
	 public String getDistIdListForDivisionFilter(){
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
				String group = jObj.getString("group");
				idnameVoList = alertManagementSystemService.getDistIdListForDivisionFilter(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,group);
			} catch (Exception e) {
				LOG.error("Exception Occured in getStateThenGovtDeptScopeWiseAlertCount() method, Exception - ",e); 
			}
			return Action.SUCCESS;	
		}
	 public String getDivisionIdListForDivisionFilter(){
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
				Long districtWorkLocationId = jObj.getLong("districtWorkLocationId");
				String group = jObj.getString("group");
				idnameVoList = alertManagementSystemService.getDivisionIdListForDivisionFilter(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,districtWorkLocationId,group);
			} catch (Exception e) {
				LOG.error("Exception Occured in getStateThenGovtDeptScopeWiseAlertCount() method, Exception - ",e); 
			}
			return Action.SUCCESS;	
		}
	 public String getDistrictIdListForSubDivisionFilter(){
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
				String group = jObj.getString("group");
				idnameVoList = alertManagementSystemService.getDistrictIdListForSubDivisionFilter(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,group);
			} catch (Exception e) {
				LOG.error("Exception Occured in getStateThenGovtDeptScopeWiseAlertCount() method, Exception - ",e); 
			}
			return Action.SUCCESS;	
		}
	 public String getDivisionIdListForSubDivisionFilter(){
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
				Long districtWorkLocationId = jObj.getLong("districtWorkLocationId");
				String group = jObj.getString("group");
				idnameVoList = alertManagementSystemService.getDivisionIdListForSubDivisionFilter(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,districtWorkLocationId,group);
			} catch (Exception e) {
				LOG.error("Exception Occured in getStateThenGovtDeptScopeWiseAlertCount() method, Exception - ",e); 
			}
			return Action.SUCCESS;	
		}
	 public String getSubDivisionIdListForSubDivisionFilter(){
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
				Long districtWorkLocationId = jObj.getLong("districtWorkLocationId");
				Long divisionWorkLocationId = jObj.getLong("divisionWorkLocationId");
				String group = jObj.getString("group");
				idnameVoList = alertManagementSystemService.getSubDivisionIdListForSubDivisionFilter(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,districtWorkLocationId,divisionWorkLocationId,group);
			} catch (Exception e) {
				LOG.error("Exception Occured in getStateThenGovtDeptScopeWiseAlertCount() method, Exception - ",e); 
			}
			return Action.SUCCESS;	
		}
		
		public String getAllDivisionDetails(){
			try {
				jObj = new JSONObject(getTask());
				Long districtId = jObj.getLong("districtId");
				alertVOs = alertManagementSystemService.getAllDivisionDetails(districtId);
			} catch (Exception e) {
				LOG.error("Exception Raised in getAllDivisionDetails() in AlertManagementSystemAction",e);
			}
			return Action.SUCCESS;
		}
		public String getAllSubDivisionDetails(){
			try {
				jObj = new JSONObject(getTask());				
				Long divisionId = jObj.getLong("divisionId");
				alertVOs = alertManagementSystemService.getAllSubDivisionDetails(divisionId);
			} catch (Exception e) {
				LOG.error("Exception Raised in getAllSubDivisionDetails() in AlertManagementSystemAction",e);
			}
			return Action.SUCCESS;
		}
		public String getAllDistrictDetails(){
			try {
				jObj = new JSONObject(getTask());
				alertVOs = alertManagementSystemService.getAllDistrictDetails();
			} catch (Exception e) {
				LOG.error("Exception Raised in getAllDistrictDetails() in AlertManagementSystemAction",e);
			}
			return Action.SUCCESS;
		}
		public String getDocumentsForAlert(){
			try {
				jObj = new JSONObject(getTask());
				keyValueVOList = alertManagementSystemService.getDocumentsForAlert(jObj.getLong("alertId"));
			} catch (Exception e) {
				LOG.error("Exception Occured in getDocumentsForAlert() method, Exception - ",e); 
			}
			return Action.SUCCESS;	
		}
		public String getDistrictOfficeGraphicalView(){
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
				String sortType = jObj.getString("sortType");
				String order = jObj.getString("order");
				Long districtWorkLocationId = jObj.getLong("districtWorkLocationId");
				Long divisionWorkLocationId = jObj.getLong("divisionWorkLocationId");
				Long subDivisionWorkLocationId = jObj.getLong("subDivisionWorkLocationId");
				String alertType = jObj.getString("alertType");
				String searchType = jObj.getString("searchType");
				String group = jObj.getString("group");
				if(searchType.equalsIgnoreCase("statusWise"))
				 alertCoreDashBoardVOs = alertManagementSystemService.getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,sortType,order,alertType,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,group);
				else if(searchType.equalsIgnoreCase("scopeWise"))
				  alertCoreDashBoardVOs=alertManagementSystemService.getWorkLocationWiseThenGovtDeptScopeWiseAlertCount(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,sortType,order,alertType,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,group,searchType);
					
			} catch (Exception e) {
				LOG.error("Exception Occured in getDistrictOfficeGraphicalView() method, Exception - ",e); 
			}
			return Action.SUCCESS;	
		}
}
