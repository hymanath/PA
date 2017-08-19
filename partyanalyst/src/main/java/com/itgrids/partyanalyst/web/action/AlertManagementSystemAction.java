package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AlertAssigningVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertTrackingVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.AlertsSummeryVO;
import com.itgrids.partyanalyst.dto.DistrictOfficeViewAlertVO;
import com.itgrids.partyanalyst.dto.FilterSectionVO;
import com.itgrids.partyanalyst.dto.GovtDepartmentVO;
import com.itgrids.partyanalyst.dto.GrievanceAlertVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationAlertVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IAlertManagementSystemService;
import com.itgrids.partyanalyst.service.ICccDashboardService;
import com.itgrids.partyanalyst.utils.IConstants;
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
	private InputStream inputStream;
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
	private List<List<AlertTrackingVO>> listOfalertTrackingVOList;
	private List<AlertCoreDashBoardVO> alertCoreDashBoardVo;
	private Long subTaskId;
	private List<KeyValueVO> keyValueVOList;
	
	private List<File> imageForDisplay = new ArrayList<File>();
	private List<String> imageForDisplayContentType = new ArrayList<String>();
	private List<String> imageForDisplayFileName = new ArrayList<String>();
	private List<GrievanceAlertVO> grievanceAlertVo;
	private FilterSectionVO filterDetilsList;
	private String officerNameAnddesgnationName;
	private List<IdAndNameVO> socailMediaTypeList;
	private List<IdAndNameVO> alertCallCenterTypeIdList;
	private Long alertDataId;
	private List<IdNameVO> idNameVOList;
	private List<IdAndNameVO> alertSeverityList;
	private List<IdAndNameVO> alertStatusList;
	private List<IdAndNameVO> govtAlertSubTaksStatusList;
	private List<AlertsSummeryVO> alertsSummeryVOList;
	private List<IdAndNameVO> mondayGrievanceTypeList;
	private List<IdAndNameVO> janmabhoomiTypeList;
	private List<IdAndNameVO> specialGrievanceTypeList;
	private List<IdAndNameVO> generalGrievanceTypeList;
	private String officerMobileNo;
	private LocationAlertVO locationAlertVO;
	
	public List<IdAndNameVO> getMondayGrievanceTypeList() {
		return mondayGrievanceTypeList;
	}

	public void setMondayGrievanceTypeList(List<IdAndNameVO> mondayGrievanceTypeList) {
		this.mondayGrievanceTypeList = mondayGrievanceTypeList;
	}

	public List<IdAndNameVO> getJanmabhoomiTypeList() {
		return janmabhoomiTypeList;
	}

	public void setJanmabhoomiTypeList(List<IdAndNameVO> janmabhoomiTypeList) {
		this.janmabhoomiTypeList = janmabhoomiTypeList;
	}

	public List<IdAndNameVO> getSpecialGrievanceTypeList() {
		return specialGrievanceTypeList;
	}

	public void setSpecialGrievanceTypeList(
			List<IdAndNameVO> specialGrievanceTypeList) {
		this.specialGrievanceTypeList = specialGrievanceTypeList;
	}

	public List<IdAndNameVO> getGeneralGrievanceTypeList() {
		return generalGrievanceTypeList;
	}

	public void setGeneralGrievanceTypeList(
			List<IdAndNameVO> generalGrievanceTypeList) {
		this.generalGrievanceTypeList = generalGrievanceTypeList;
	}

	public List<AlertsSummeryVO> getAlertsSummeryVOList() {
		return alertsSummeryVOList;
	}

	public void setAlertsSummeryVOList(List<AlertsSummeryVO> alertsSummeryVOList) {
		this.alertsSummeryVOList = alertsSummeryVOList;
	}

	public List<IdNameVO> getIdNameVOList() {
		return idNameVOList;
	}

	public void setIdNameVOList(List<IdNameVO> idNameVOList) {
		this.idNameVOList = idNameVOList;
	}

	public List<List<AlertTrackingVO>> getListOfalertTrackingVOList() {
		return listOfalertTrackingVOList;
	}

	public void setListOfalertTrackingVOList(
			List<List<AlertTrackingVO>> listOfalertTrackingVOList) {
		this.listOfalertTrackingVOList = listOfalertTrackingVOList;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String getOfficerNameAnddesgnationName() {
		return officerNameAnddesgnationName;
	}
	public void setOfficerNameAnddesgnationName(String officerNameAnddesgnationName) {
		this.officerNameAnddesgnationName = officerNameAnddesgnationName;
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
	
	public List<GrievanceAlertVO> getGrievanceAlertVo() {
		return grievanceAlertVo;
	}
	public void setGrievanceAlertVo(List<GrievanceAlertVO> grievanceAlertVo) {
		this.grievanceAlertVo = grievanceAlertVo;
	}
	public FilterSectionVO getFilterDetilsList() {
		return filterDetilsList;
	}
	public void setFilterDetilsList(FilterSectionVO filterDetilsList) {
		this.filterDetilsList = filterDetilsList;
	}
	public List<IdAndNameVO> getSocailMediaTypeList() {
		return socailMediaTypeList;
	}

	public void setSocailMediaTypeList(List<IdAndNameVO> socailMediaTypeList) {
		this.socailMediaTypeList = socailMediaTypeList;
	}
	public Long getAlertDataId() {
		return alertDataId;
	}

	public void setAlertDataId(Long alertDataId) {
		this.alertDataId = alertDataId;
	}
	public List<IdAndNameVO> getAlertCallCenterTypeIdList() {
		return alertCallCenterTypeIdList;
	}

	public void setAlertCallCenterTypeIdList(
			List<IdAndNameVO> alertCallCenterTypeIdList) {
		this.alertCallCenterTypeIdList = alertCallCenterTypeIdList;
	}
    public List<IdAndNameVO> getAlertSeverityList() {
		return alertSeverityList;
	}

	public void setAlertSeverityList(List<IdAndNameVO> alertSeverityList) {
		this.alertSeverityList = alertSeverityList;
	}

	public List<IdAndNameVO> getAlertStatusList() {
		return alertStatusList;
	}

	public void setAlertStatusList(List<IdAndNameVO> alertStatusList) {
		this.alertStatusList = alertStatusList;
	}

	public List<IdAndNameVO> getGovtAlertSubTaksStatusList() {
		return govtAlertSubTaksStatusList;
	}

	public void setGovtAlertSubTaksStatusList(
			List<IdAndNameVO> govtAlertSubTaksStatusList) {
		this.govtAlertSubTaksStatusList = govtAlertSubTaksStatusList;
	}
	public String getOfficerMobileNo() {
		return officerMobileNo;
	}

	public void setOfficerMobileNo(String officerMobileNo) {
		this.officerMobileNo = officerMobileNo;
	}

	public LocationAlertVO getLocationAlertVO() {
		return locationAlertVO;
	}

	public void setLocationAlertVO(LocationAlertVO locationAlertVO) {
		this.locationAlertVO = locationAlertVO;
	}
	
	public String execute(){
		    session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			
			 officerNameAnddesgnationName = alertManagementSystemService.getOfficernameDesignationForUser(userId);
		      String[] usrDesLocArr = officerNameAnddesgnationName.split("/");
		      session.setAttribute("officerName", usrDesLocArr[0]);
		      session.setAttribute("designationAndLocation", usrDesLocArr[1]);
		      
			newsPaperList = cccDashboardService.getNewsPapaerList();
			chanelListNew = cccDashboardService.getChannelList();
			chanelList = cccDashboardService.getChannelListForUser(userId);  
			deptListNew = cccDashboardService.getDeptList(); 
			deptList = alertManagementSystemService.getDeptListForUser(userId);
			socailMediaTypeList = alertManagementSystemService.getSocialMediaTypeList();
			alertCallCenterTypeIdList = alertManagementSystemService.getAlertCallCenterType();
			mondayGrievanceTypeList = alertManagementSystemService.getMondayGrievanceTypeList();
			janmabhoomiTypeList = alertManagementSystemService.getJanmabhoomiTypeList();
			specialGrievanceTypeList = alertManagementSystemService.getSpecialGrievanceTypeList();
			generalGrievanceTypeList = alertManagementSystemService.getGeneralGrievanceTypeList();
		return Action.SUCCESS;
		
	  }
	public String getDepartmentDetails(){
		 try{
		      session = request.getSession();
		         RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
		      Long userId = regVo.getRegistrationID();
		      
		      officerNameAnddesgnationName = alertManagementSystemService.getOfficernameDesignationForUser(userId);
		      String[] usrDesLocArr = officerNameAnddesgnationName.split("/");
		      session.setAttribute("officerName", usrDesLocArr[0]);
		      session.setAttribute("designationAndLocation", usrDesLocArr[1]);
		      
		      newsPaperList = cccDashboardService.getNewsPapaerList();
		      chanelListNew = cccDashboardService.getChannelList();
		      socailMediaTypeList = alertManagementSystemService.getSocialMediaTypeList();
		      alertCallCenterTypeIdList = alertManagementSystemService.getAlertCallCenterType();
		      alertSeverityList = alertManagementSystemService.getAlertSeverity();
		      alertStatusList = alertManagementSystemService.getAlertStatus();
		      govtAlertSubTaksStatusList = alertManagementSystemService.getSubTaskAlertStatus();
		      mondayGrievanceTypeList = alertManagementSystemService.getMondayGrievanceTypeList();
		      janmabhoomiTypeList = alertManagementSystemService.getJanmabhoomiTypeList();
		      specialGrievanceTypeList = alertManagementSystemService.getSpecialGrievanceTypeList();
		      generalGrievanceTypeList = alertManagementSystemService.getGeneralGrievanceTypeList();
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			}
			
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}
           alertVOs = alertManagementSystemService.getStatusWiseAlertOverviewcnt(fromDate, toDate, stateId, paperIdList, chanelIdList,deptIdList,userId,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			} 
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			alertVOs = alertManagementSystemService.getLevelWiseAlertOverviewCnt(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,userId,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
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
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			} 
			String resultType = jObj.getString("resultType");
			
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			} 
           
			alertVOs = alertManagementSystemService.getDepartmentWiseAlertOverviewCnt(fromDate,toDate,stateId,paperIdList,chanelIdList,deptIdList,userId,alertStatusIds,deptScopeLevelIds,resultType,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);;
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			}
			
			String startDate =jObj.getString("startDate");
			String endDate =jObj.getString("endDate");
			
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
			List<Long> alertSeverityIds = new ArrayList<Long>();
			if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
				for (int i = 0; i < alertSeverityIdsArr.length(); i++){
					alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
				} 
			}
			
			JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
			List<Long> alertStatusIds = new ArrayList<Long>();
			if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
				for (int i = 0; i < alertStatusIdsArr.length(); i++){
					alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
				} 
			}
			
			JSONArray alertSubTaskStatusIdsArr = jObj.getJSONArray("alertSubTaskStatusIdsArr");  
			List<Long> subTaskAlertStatusIds = new ArrayList<Long>();
			if(alertSubTaskStatusIdsArr != null && alertSubTaskStatusIdsArr.length() > 0){
				for (int i = 0; i < alertSubTaskStatusIdsArr.length(); i++){
					subTaskAlertStatusIds.add(Long.parseLong(alertSubTaskStatusIdsArr.getString(i)));        
				} 
			}
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}
		   	districtOfficeViewAlertVO = alertManagementSystemService.getDistrictOfficerAlertsCountView(userId,startDate,endDate,paperIdList,chanelIdList,calCntrIdList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			}
			//srujana
			JSONArray impactLevelIdArr = jObj.getJSONArray("impactLevelArr");  
			List<Long> impactLevelIdList = new ArrayList<Long>();
			for (int i = 0; i < impactLevelIdArr.length(); i++){
				impactLevelIdList.add(Long.parseLong(impactLevelIdArr.getString(i)));        
			}
			JSONArray priorityIdArr = jObj.getJSONArray("priorityArr");  
			List<Long> priorityIdList = new ArrayList<Long>();
			for (int i = 0; i < priorityIdArr.length(); i++){
				priorityIdList.add(Long.parseLong(priorityIdArr.getString(i)));        
			}
			JSONArray alertSourceIdArr = jObj.getJSONArray("alertSourceArr");  
			List<Long> alertSourceIdList = new ArrayList<Long>();
			for (int i = 0; i < alertSourceIdArr.length(); i++){
				alertSourceIdList.add(Long.parseLong(alertSourceIdArr.getString(i)));        
			}
			JSONArray printMediaIdArr = jObj.getJSONArray("printMediaArr");  
			List<Long> printMediaIdList = new ArrayList<Long>();
			for (int i = 0; i < printMediaIdArr.length(); i++){
				printMediaIdList.add(Long.parseLong(printMediaIdArr.getString(i)));        
			}
			JSONArray electronicMediaIdArr = jObj.getJSONArray("electronicMediaArr");  
			List<Long> electronicMediaIdList = new ArrayList<Long>();
			for (int i = 0; i < electronicMediaIdArr.length(); i++){
				electronicMediaIdList.add(Long.parseLong(electronicMediaIdArr.getString(i)));        
			}
			
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			if(statusId != null && statusId.longValue() == 1L){//pending
				alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,null,calCntrIdList,impactLevelIdList,priorityIdList,alertSourceIdList,printMediaIdList,electronicMediaIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				/*List<AlertCoreDashBoardVO> pendingAlertList = alertManagementSystemService.getTotalAlertByOtherStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,userId,null,null,calCntrIdList,impactLevelIdList,priorityIdList,alertSourceIdList,printMediaIdList,electronicMediaIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//taking pending alert from alert Assigned officer new
				 if(pendingAlertList != null && pendingAlertList.size() > 0){
					 alertCoreDashBoardVOs.addAll(pendingAlertList);
				 }*/
				alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
			}else if(statusId != null && statusId.longValue() > 1L){//other than pending
				alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByOtherStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,userId,null,null,calCntrIdList,impactLevelIdList,priorityIdList,alertSourceIdList,printMediaIdList,electronicMediaIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
			}else{
				alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,1L,null,calCntrIdList,null,null,null,null,null,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				List<AlertCoreDashBoardVO> list1 = new ArrayList<AlertCoreDashBoardVO>();
				if(alertCoreDashBoardVOs != null){
					list1.addAll(alertCoreDashBoardVOs);
				}
				alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByOtherStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,userId,null,null,calCntrIdList,null,null,null,null,null,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			} 
			Long govtDeptScopeId = jObj.getLong("govtDeptScopeId");
			
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByOtherStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,userId,govtDeptScopeId,null,calCntrIdList,null,null,null,null,null,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			} 
			Long deptId = jObj.getLong("deptId");
			
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			if(statusId != null && statusId.longValue() == 1L){//pending
				alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,deptId,calCntrIdList,null,null,null,null,null,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				/*List<AlertCoreDashBoardVO> pendingAlertList = alertManagementSystemService.getTotalAlertByOtherStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,userId,null,deptId,calCntrIdList,null,null,null,null,null,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//taking pending alert from alert assigned officer new 
				if(pendingAlertList != null && pendingAlertList.size() > 0){
					alertCoreDashBoardVOs.addAll(pendingAlertList);	
				}*/
				alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
			}else if(statusId != null && statusId.longValue() > 1L){//other than pending
				alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByOtherStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,userId,null,deptId,calCntrIdList,null,null,null,null,null,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
			}else{
				alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,1L,deptId,calCntrIdList,null,null,null,null,null,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				List<AlertCoreDashBoardVO> list1 = new ArrayList<AlertCoreDashBoardVO>();
				if(alertCoreDashBoardVOs != null){
					list1.addAll(alertCoreDashBoardVOs);
				}
				alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByOtherStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,userId,null,deptId,calCntrIdList,null,null,null,null,null,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			}  
			Long govtDeptScopeId = jObj.getLong("govtDeptScopeId");      
			Long deptId = jObj.getLong("deptId");
			
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByOtherStatus(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusId,userId,govtDeptScopeId,deptId,calCntrIdList,null,null,null,null,null,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
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
			//Long deptId = jObj.getLong("deptId");
			String sortingType = jObj.getString("sortingType");
			Long levelId = jObj.getLong("levelId");
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			}  
			JSONArray deptArr = jObj.getJSONArray("deptArr");  
			List<Long> deptIdList = new ArrayList<Long>();
			for (int i = 0; i < deptArr.length(); i++){
				deptIdList.add(Long.parseLong(deptArr.getString(i)));        
			}  
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
			List<Long> alertSeverityIds = new ArrayList<Long>();
			if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
				for (int i = 0; i < alertSeverityIdsArr.length(); i++){
					alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
				} 
			}
			
			JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
			List<Long> alertStatusIds = new ArrayList<Long>();
			if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
				for (int i = 0; i < alertStatusIdsArr.length(); i++){
					alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
				} 
			}
			
			JSONArray alertSubTaskStatusIdsArr = jObj.getJSONArray("alertSubTaskStatusIdsArr");  
			List<Long> subTaskAlertStatusIds = new ArrayList<Long>();
			if(alertSubTaskStatusIdsArr != null && alertSubTaskStatusIdsArr.length() > 0){
				for (int i = 0; i < alertSubTaskStatusIdsArr.length(); i++){
					subTaskAlertStatusIds.add(Long.parseLong(alertSubTaskStatusIdsArr.getString(i)));        
				} 
			}
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			alertVOs = alertManagementSystemService.getDistrictLevelDeptWiseStatusOverView(scopeId,startDateStr,fromDateStr,type,deptIdList,sortingType,levelId,paperIdList,chanelIdList,calCntrIdList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			
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
			//Long deptId = jObj.getLong("deptId");
			String sortingType = jObj.getString("sortingType");
			
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			}  
			JSONArray deptArr = jObj.getJSONArray("deptArr");  
			List<Long> deptIdList = new ArrayList<Long>();
			for (int i = 0; i < deptArr.length(); i++){
				deptIdList.add(Long.parseLong(deptArr.getString(i)));        
			}  
			JSONArray subLevelArr = jObj.getJSONArray("subLevelArr");  
			List<Long> deptScopeList = new ArrayList<Long>();
			for (int i = 0; i < subLevelArr.length(); i++){
				deptScopeList.add(Long.parseLong(subLevelArr.getString(i)));        
			}  
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
			List<Long> alertSeverityIds = new ArrayList<Long>();
			if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
				for (int i = 0; i < alertSeverityIdsArr.length(); i++){
					alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
				} 
			}
			
			JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
			List<Long> alertStatusIds = new ArrayList<Long>();
			if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
				for (int i = 0; i < alertStatusIdsArr.length(); i++){
					alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
				} 
			}
			
			JSONArray alertSubTaskStatusIdsArr = jObj.getJSONArray("alertSubTaskStatusIdsArr");  
			List<Long> subTaskAlertStatusIds = new ArrayList<Long>();
			if(alertSubTaskStatusIdsArr != null && alertSubTaskStatusIdsArr.length() > 0){
				for (int i = 0; i < alertSubTaskStatusIdsArr.length(); i++){
					subTaskAlertStatusIds.add(Long.parseLong(alertSubTaskStatusIdsArr.getString(i)));        
				} 
			}
			String resultType = jObj.getString("resultType");
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			alertVOs = alertManagementSystemService.getDistrictLevelDeptWiseLocationLevelView(scopeId,startDateStr,fromDateStr,type,deptIdList,sortingType,paperIdList,chanelIdList,calCntrIdList,resultType,deptScopeList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception occured in getDistrictLevelDeptWiseLocationLevelView() of alertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getGovtDepartmentDetails(){
		try {
			//jObj = new JSONObject(getTask());
			session = request.getSession();
		   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			alertVOs = alertManagementSystemService.getGovtDepartmentDetails(userId);
		} catch (Exception e) {
			LOG.error("Exception occured in getGovtDepartmentDetails() of alertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String getGovtDeptScopeDetails(){
		try {
			jObj = new JSONObject(getTask());
			session = request.getSession();
		   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			JSONArray deptArr = jObj.getJSONArray("deptArr");  
			List<Long> deptList = new ArrayList<Long>();
			if(deptArr != null && deptArr.length() > 0){
				for (int i = 0; i < deptArr.length(); i++){
					deptList.add(Long.parseLong(deptArr.getString(i)));        
				} 
			} 
			alertVOs = alertManagementSystemService.getGovtDeptScopeDetails(deptList,userId);
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
		   	
		   	resultStatus = alertManagementSystemService.updateAlertStatusComment(jObj.getLong("alertId"),jObj.getLong("statusId"),jObj.getString("comment"),regVo.getRegistrationID(),jObj.getLong("proposalCategoryId"),jObj.getString("proposalAmount"),jObj.getLong("rejoinderActionTypeId"));
			
		} catch (Exception e) {
			LOG.error("Exception occured in updateAlertStatusComment() of updateAlertStatusComment",e);
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
			listOfalertTrackingVOList = alertManagementSystemService.viewAlertHistoryNew(jObj.getLong("alertId"),jObj.getString("task"));
			
			
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			} 
			Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");      
			Long govtDepartmentId = jObj.getLong("govtDepartmentId");
			String sortType = jObj.getString("sortType");
			String order = jObj.getString("order");
			Long districtWorkLocationId = jObj.getLong("districtWorkLocationId");
			Long divisionWorkLocationId = jObj.getLong("divisionWorkLocationId");
			Long subDivisionWorkLocationId = jObj.getLong("subDivisionWorkLocationId");
			String alertType =  jObj.getString("alertType");
			
			String group = jObj.getString("group");
			JSONArray subLevelsArr = jObj.getJSONArray("subLevels");  
			List<Long> sublevels = new ArrayList<Long>();
			if(subLevelsArr != null && subLevelsArr.length() > 0){
				for (int i = 0; i < subLevelsArr.length(); i++){
					sublevels.add(Long.parseLong(subLevelsArr.getString(i)));          
				}  
			}
			String searchType = jObj.getString("searchType");
			Long filterParentScopeId = jObj.getLong("filterParentScopeId");
			Long filterScopeValue = jObj.getLong("filterScopeValue");
			
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
			List<Long> alertSeverityIds = new ArrayList<Long>();
			if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
				for (int i = 0; i < alertSeverityIdsArr.length(); i++){
					alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
				} 
			}
			
			JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
			List<Long> alertStatusIds = new ArrayList<Long>();
			if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
				for (int i = 0; i < alertStatusIdsArr.length(); i++){
					alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
				} 
			}
			
			JSONArray alertSubTaskStatusIdsArr = jObj.getJSONArray("alertSubTaskStatusIdsArr");  
			List<Long> subTaskAlertStatusIds = new ArrayList<Long>();
			if(alertSubTaskStatusIdsArr != null && alertSubTaskStatusIdsArr.length() > 0){
				for (int i = 0; i < alertSubTaskStatusIdsArr.length(); i++){
					subTaskAlertStatusIds.add(Long.parseLong(alertSubTaskStatusIdsArr.getString(i)));        
				} 
			}
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}

			alertCoreDashBoardVOs = alertManagementSystemService.getLocationWiseDepartmentOverviewAlertCount(fromDate,
					toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,sortType,
					order,alertType,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,group,calCntrIdList,sublevels,filterParentScopeId,filterScopeValue,searchType,socialMediaTypeIds,alertSeverityIds,alertStatusIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
	
			//alertCoreDashBoardVOs = alertManagementSystemService.getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewDynamic(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,sortType,order,alertType,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,group,calCntrIdList,sublevels);
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
			//Long statusId = jObj.getLong("statusId");
			String type = jObj.getString("type");
			String formDateStr = jObj.getString("startDate");
			String endDateStr = jObj.getString("endDate");
			Long desigDeptOfficerId = jObj.getLong("desigDeptOfficerId");
			Long officerId = jObj.getLong("officerId");
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			}
			Long alertCategoryId = jObj.getLong("alertCategoryId");
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
			List<Long> alertSeverityIds = new ArrayList<Long>();
			if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
				for (int i = 0; i < alertSeverityIdsArr.length(); i++){
					alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
				} 
			}
			
			JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
			List<Long> alertStatusIds = new ArrayList<Long>();
			if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
				for (int i = 0; i < alertStatusIdsArr.length(); i++){
					alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
				} 
			}
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}
		
			alertCoreDashBoardVOs = alertManagementSystemService.getDistrictLevelDeptWiseFlterClick(scopeId,deptId,levelId,alertStatusIds,type,formDateStr,endDateStr,desigDeptOfficerId,officerId,paperIdList,chanelIdList,calCntrIdList,alertCategoryId,socialMediaTypeIds,alertSeverityIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
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
			   if(successMsg != null && !successMsg.isEmpty())
				   inputStream = new StringBufferInputStream(successMsg);
			   
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
		 try {
		       session = request.getSession();
		           RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
		        Long userId = regVo.getRegistrationID();
		        
		        officerNameAnddesgnationName = alertManagementSystemService.getOfficernameDesignationForUser(userId);
		        String[] usrDesLocArr = officerNameAnddesgnationName.split("/");
			      session.setAttribute("officerName", usrDesLocArr[0]);
			      session.setAttribute("designationAndLocation", usrDesLocArr[1]);
			     
			      newsPaperList = cccDashboardService.getNewsPapaerList();
			      chanelListNew = cccDashboardService.getChannelList();
			      socailMediaTypeList = alertManagementSystemService.getSocialMediaTypeList();
			      alertCallCenterTypeIdList = alertManagementSystemService.getAlertCallCenterType();
			      alertSeverityList = alertManagementSystemService.getAlertSeverity();
			      alertStatusList = alertManagementSystemService.getAlertStatus();
			      govtAlertSubTaksStatusList = alertManagementSystemService.getSubTaskAlertStatus();
			      mondayGrievanceTypeList = alertManagementSystemService.getMondayGrievanceTypeList();
			      janmabhoomiTypeList = alertManagementSystemService.getJanmabhoomiTypeList();
			      specialGrievanceTypeList = alertManagementSystemService.getSpecialGrievanceTypeList();
			      generalGrievanceTypeList = alertManagementSystemService.getGeneralGrievanceTypeList();
			    
		    } catch (Exception e) {
		      LOG.error("Exception Occured in alertDistManagement() method, Exception - ",e);
		    }
		    return Action.SUCCESS;
		  }
	 
	 public String alertDistOfficeManagement(){
	     
	      session = request.getSession();
	         RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
	      Long userId = regVo.getRegistrationID();
	      
	      officerNameAnddesgnationName = alertManagementSystemService.getOfficernameDesignationForUser(userId);
	      String[] usrDesLocArr = officerNameAnddesgnationName.split("/");
	      session.setAttribute("officerName", usrDesLocArr[0]);
	      session.setAttribute("designationAndLocation", usrDesLocArr[1]);
	     
	      newsPaperList = cccDashboardService.getNewsPapaerList();
	      chanelListNew = cccDashboardService.getChannelList();
	      socailMediaTypeList = alertManagementSystemService.getSocialMediaTypeList();
	      alertCallCenterTypeIdList = alertManagementSystemService.getAlertCallCenterType();
	      alertSeverityList = alertManagementSystemService.getAlertSeverity();
	      alertStatusList = alertManagementSystemService.getAlertStatus();
	      govtAlertSubTaksStatusList = alertManagementSystemService.getSubTaskAlertStatus();
	      mondayGrievanceTypeList = alertManagementSystemService.getMondayGrievanceTypeList();
	      janmabhoomiTypeList = alertManagementSystemService.getJanmabhoomiTypeList();
	      specialGrievanceTypeList = alertManagementSystemService.getSpecialGrievanceTypeList();
	      generalGrievanceTypeList = alertManagementSystemService.getGeneralGrievanceTypeList();
	     return Action.SUCCESS; 
	   }
	 public String getDeptListForMultiLvl(){    
		 try{
			 session = request.getSession();
			 RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			 Long userId = regVo.getRegistrationID();
			 jObj = new JSONObject(getTask());
			 JSONArray deptIdArr = jObj.getJSONArray("deptIdArr");  
				Set<Long> deptSet = new HashSet<Long>();
				if(deptIdArr != null && deptIdArr.length() > 0){
					for (int i = 0; i < deptIdArr.length(); i++){
						deptSet.add(Long.parseLong(deptIdArr.getString(i)));        
					} 
				}
			 idnameVoList = alertManagementSystemService.getDeptListForMultiLvl(userId,deptSet);
			
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
				
				JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
				List<Long> calCntrIdList = new ArrayList<Long>();
				for (int i = 0; i < calCntrIdArr.length(); i++){
					calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
				}
				
				JSONArray govtDepDesigOffcrIds = jObj.getJSONArray("govtDepDesigOffcrIds");  
				List<Long> govtDeptDesiOffIdList = new ArrayList<Long>();
				if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.length() > 0){
					for (int i = 0; i < govtDepDesigOffcrIds.length(); i++){
						govtDeptDesiOffIdList.add(Long.parseLong(govtDepDesigOffcrIds.getString(i)));          
					}
				}
				
				JSONArray govtOfficerIds = jObj.getJSONArray("govtOfficerIds");  
				List<Long> govtOffcrIdList = new ArrayList<Long>();
				for (int i = 0; i < govtOfficerIds.length(); i++){
					govtOffcrIdList.add(Long.parseLong(govtOfficerIds.getString(i)));        
				}
				
				String countType = jObj.getString("countType");
				String alertType = jObj.getString("alertType");
				String fromDate = jObj.getString("fromDate");
				String toDate = jObj.getString("toDate");
				
				JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
				List<Long> socialMediaTypeIds = new ArrayList<Long>();
				if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
					for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
						socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
					} 
				}
				
				JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
				List<Long> alertSeverityIds = new ArrayList<Long>();
				if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
					for (int i = 0; i < alertSeverityIdsArr.length(); i++){
						alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
					} 
				}
				
				JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
				List<Long> alertStatusIds = new ArrayList<Long>();
				if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
					for (int i = 0; i < alertStatusIdsArr.length(); i++){
						alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
					} 
				}
				
				JSONArray alertSubTaskStatusIdsArr = jObj.getJSONArray("alertSubTaskStatusIdsArr");  
				List<Long> subTaskAlertStatusIds = new ArrayList<Long>();
				if(alertSubTaskStatusIdsArr != null && alertSubTaskStatusIdsArr.length() > 0){
					for (int i = 0; i < alertSubTaskStatusIdsArr.length(); i++){
						subTaskAlertStatusIds.add(Long.parseLong(alertSubTaskStatusIdsArr.getString(i)));        
					} 
				}	
				JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
				List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
				if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
						mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
					}
				}
				JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
				List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
				if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
					for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
						janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
					} 
				}
				JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
				List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
				if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
						specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
					} 
				}
				
				JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
				List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
				if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
						generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
					} 
				}
				alertCoreDashBoardVOs = alertManagementSystemService.getDistrictOfficerAlertDetails(govtDeptDesiOffIdList,govtOffcrIdList,countType,alertType,paperIdList,chanelIdList,calCntrIdList,fromDate,toDate,socialMediaTypeIds,alertSeverityIds,alertStatusIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				
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
			   	JSONArray parentLevelValueArr = jObj.getJSONArray("parentLevelValueArr");
			   	Long levelId = jObj.getLong("levelId");
			   	
				List<Long> parentLevelValues = new ArrayList<Long>();
				if(parentLevelValueArr != null && parentLevelValueArr.length() > 0){
					for (int i = 0; i < parentLevelValueArr.length(); i++){
						parentLevelValues.add(Long.parseLong(parentLevelValueArr.getString(i)));        
					} 
				}
			   				   			   
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
				
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				if(regVo == null)
					return null;
				List<String> entitlements = regVo.getEntitlements();
				jObj = new JSONObject(getTask());
				Long alertId = jObj.getLong("alertId");
				Long levelValue = jObj.getLong("levelValue");
				
				//idnameVoList = alertManagementSystemService.getStatusCompletionInfo(alertId,levelValue,jObj.getLong("designationId"),jObj.getLong("levelId"),regVo.getRegistrationID());
				idnameVoList = alertManagementSystemService.getStatusCompletionInfoNew(alertId,levelValue,jObj.getLong("designationId"),jObj.getLong("levelId"),regVo.getRegistrationID(),entitlements);
				
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
				alertTrackingVOList = alertManagementSystemService.getSubTaskInfoForAlert(jObj.getLong("alertId"),regVo.getRegistrationID());
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
				
				JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
				List<Long> calCntrIdList = new ArrayList<Long>();
				if(calCntrIdArr != null && calCntrIdArr.length() > 0){
					for (int i = 0; i < calCntrIdArr.length(); i++){
						calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
					}
				}
				
				JSONArray subLevelsArr = jObj.getJSONArray("subLevels");  
				List<Long> sublevels = new ArrayList<Long>();
				if(subLevelsArr != null && subLevelsArr.length() > 0){
					for (int i = 0; i < subLevelsArr.length(); i++){
						sublevels.add(Long.parseLong(subLevelsArr.getString(i)));          
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
				Long statusId = jObj.getLong("statusId");
				Long govtDeprtMentScopeId = jObj.getLong("govtDeprtMentScopeId");
				alertCoreDashBoardVOs = alertManagementSystemService.getDistrictLevelWiseClick(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,sortType,order,alertType,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,group,searchType,statusId,govtDeprtMentScopeId,calCntrIdList,sublevels);
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
				
				JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
				List<Long> calCntrIdList = new ArrayList<Long>();
				if(calCntrIdArr != null && calCntrIdArr.length() > 0){
					for (int i = 0; i < calCntrIdArr.length(); i++){
						calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
					}
				}
				JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
				List<Long> socialMediaTypeIds = new ArrayList<Long>();
				if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
					for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
						socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
					} 
				}
				JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
				List<Long> alertSeverityIds = new ArrayList<Long>();
				if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
					for (int i = 0; i < alertSeverityIdsArr.length(); i++){
						alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
					} 
				}
				
				JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
				List<Long> alertStatusIds = new ArrayList<Long>();
				if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
					for (int i = 0; i < alertStatusIdsArr.length(); i++){
						alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
					} 
				}
				JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
				List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
				if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
						mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
					}
				}
				JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
				List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
				if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
					for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
						janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
					} 
				}
				JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
				List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
				if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
						specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
					} 
				}
				
				JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
				List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
				if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
						generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
					} 
				}
				
				alertVOs = alertManagementSystemService.stateLevelDeptOfficerStatusOverview(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,userId,calCntrIdList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			}catch(Exception e){
				LOG.error("Exception occured in stateLevelDeptOfficerStatusOverview() of AlertManagementSystemAction",e);
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
				
				JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
				List<Long> calCntrIdList = new ArrayList<Long>();
				if(calCntrIdArr != null && calCntrIdArr.length() > 0){
					for (int i = 0; i < calCntrIdArr.length(); i++){
						calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
					}
				}
				JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
				List<Long> socialMediaTypeIds = new ArrayList<Long>();
				if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
					for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
						socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
					} 
				}
				JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
				List<Long> alertSeverityIds = new ArrayList<Long>();
				if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
					for (int i = 0; i < alertSeverityIdsArr.length(); i++){
						alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
					} 
				}
				
				JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
				List<Long> alertStatusIds = new ArrayList<Long>();
				if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
					for (int i = 0; i < alertStatusIdsArr.length(); i++){
						alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
					} 
				}
				
				JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
				List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
				if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
						mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
					}
				}
				JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
				List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
				if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
					for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
						janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
					} 
				}
				JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
				List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
				if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
						specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
					} 
				}
				
				JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
				List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
				if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
						generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
					} 
				}
				alertVOs = alertManagementSystemService.stateLevelDeptOfficerLocationLevelOverview(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,userId,calCntrIdList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
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
				String startDateStr = jObj.getString("fromDate");
				String endDateStr = jObj.getString("toDate");
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
				
				JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
				List<Long> calCntrIdList = new ArrayList<Long>();
				for (int i = 0; i < calCntrIdArr.length(); i++){
					calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
				}
				JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
				List<Long> socialMediaTypeIds = new ArrayList<Long>();
				if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
					for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
						socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
					} 
				}
				JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
				List<Long> alertSeverityIds = new ArrayList<Long>();
				if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
					for (int i = 0; i < alertSeverityIdsArr.length(); i++){
						alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
					} 
				}
				
				JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
				List<Long> alertStatusIds = new ArrayList<Long>();
				if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
					for (int i = 0; i < alertStatusIdsArr.length(); i++){
						alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
					} 
				}
				JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
				List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
				if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
						mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
					}
				}
				JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
				List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
				if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
					for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
						janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
					} 
				}
				JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
				List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
				if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
						specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
					} 
				}
				
				JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
				List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
				if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
						generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
					} 
				}
				
			   	districtOfficeViewAlertVO = alertManagementSystemService.getIASOfficerMyAlertsCountView(userId,startDateStr,endDateStr,paperIdList,chanelIdList,calCntrIdList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
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
				String startDateStr = jObj.getString("fromDate");
				String endDateStr = jObj.getString("toDate");
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
				
				JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
				List<Long> calCntrIdList = new ArrayList<Long>();
				for (int i = 0; i < calCntrIdArr.length(); i++){
					calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
				}
				JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
				List<Long> socialMediaTypeIds = new ArrayList<Long>();
				if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
					for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
						socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
					} 
				}
				JSONArray alertSubTaskStatusIdsArr = jObj.getJSONArray("alertSubTaskStatusIdsArr");  
				List<Long> subTaskAlertStatusIds = new ArrayList<Long>();
				if(alertSubTaskStatusIdsArr != null && alertSubTaskStatusIdsArr.length() > 0){
					for (int i = 0; i < alertSubTaskStatusIdsArr.length(); i++){
						subTaskAlertStatusIds.add(Long.parseLong(alertSubTaskStatusIdsArr.getString(i)));        
					} 
				}	
				JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
				List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
				if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
						mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
					}
				}
				JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
				List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
				if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
					for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
						janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
					} 
				}
				JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
				List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
				if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
						specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
					} 
				}
				
				JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
				List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
				if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
						generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
					} 
				}
			   	districtOfficeViewAlertVO = alertManagementSystemService.getIASOfficerMySubTasksCountView(userId,startDateStr,endDateStr,paperIdList,chanelIdList,calCntrIdList,socialMediaTypeIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				
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
				String startDateStr = jObj.getString("fromDate");
				String endDateStr = jObj.getString("toDate");
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
				
				JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
				List<Long> calCntrIdList = new ArrayList<Long>();
				for (int i = 0; i < calCntrIdArr.length(); i++){
					calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
				}
				JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
				List<Long> socialMediaTypeIds = new ArrayList<Long>();
				if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
					for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
						socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
					} 
				}
				JSONArray alertSubTaskStatusIdsArr = jObj.getJSONArray("alertSubTaskStatusIdsArr");  
				List<Long> subTaskAlertStatusIds = new ArrayList<Long>();
				if(alertSubTaskStatusIdsArr != null && alertSubTaskStatusIdsArr.length() > 0){
					for (int i = 0; i < alertSubTaskStatusIdsArr.length(); i++){
						subTaskAlertStatusIds.add(Long.parseLong(alertSubTaskStatusIdsArr.getString(i)));        
					} 
				}	
				JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
				List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
				if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
						mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
					}
				}
				JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
				List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
				if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
					for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
						janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
					} 
				}
				JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
				List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
				if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
						specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
					} 
				}
				
				JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
				List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
				if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
						generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
					} 
				}
			   	districtOfficeViewAlertVO = alertManagementSystemService.getIASOfficerMyAssignedSubTasksCountView(userId,startDateStr,endDateStr,paperIdList,chanelIdList,calCntrIdList,socialMediaTypeIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Exception occured in getIASOfficerMyAssignedSubTasksCountView() of AlertManagementSystemAction",e);
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
				
				JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
				List<Long> calCntrIdList = new ArrayList<Long>();
				for (int i = 0; i < calCntrIdArr.length(); i++){
					calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
				}
				Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");      
				Long govtDepartmentId = jObj.getLong("govtDepartmentId");
				String group = jObj.getString("group");
				String alertType = jObj.getString("alertType");
				String searchType = jObj.getString("searchType");
				idnameVoList = alertManagementSystemService.getDistIdListForDistFilter(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,group,alertType,searchType,calCntrIdList);
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
				
				JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
				List<Long> calCntrIdList = new ArrayList<Long>();
				if(calCntrIdArr != null && calCntrIdArr.length() > 0){
					for (int i = 0; i < calCntrIdArr.length(); i++){
						calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
					}
				}
				Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");      
				Long govtDepartmentId = jObj.getLong("govtDepartmentId");
				String group = jObj.getString("group");
				String alertType = jObj.getString("alertType");
				String searchType = jObj.getString("searchType");
				idnameVoList = alertManagementSystemService.getDistIdListForDivisionFilter(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,group,alertType,searchType,calCntrIdList);
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
				
				JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
				List<Long> calCntrIdList = new ArrayList<Long>();
				if(calCntrIdArr != null && calCntrIdArr.length() > 0){
					for (int i = 0; i < calCntrIdArr.length(); i++){
						calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
					}
				}
				Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");      
				Long govtDepartmentId = jObj.getLong("govtDepartmentId");
				Long districtWorkLocationId = jObj.getLong("districtWorkLocationId");
				String group = jObj.getString("group");
				String alertType = jObj.getString("alertType");
				String searchType = jObj.getString("searchType");
				idnameVoList = alertManagementSystemService.getDivisionIdListForDivisionFilter(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,districtWorkLocationId,group,alertType,searchType,calCntrIdList);
			} catch (Exception e) {
				LOG.error("Exception Occured in getDivisionIdListForDivisionFilter() method, Exception - ",e); 
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
				
				JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
				List<Long> calCntrIdList = new ArrayList<Long>();
				if(calCntrIdArr != null && calCntrIdArr.length() > 0){
					for (int i = 0; i < calCntrIdArr.length(); i++){
						calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
					}
				}
				Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");      
				Long govtDepartmentId = jObj.getLong("govtDepartmentId");
				String group = jObj.getString("group");
				String alertType = jObj.getString("alertType");
				String searchType = jObj.getString("searchType");
				idnameVoList = alertManagementSystemService.getDistrictIdListForSubDivisionFilter(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,group,alertType,searchType,calCntrIdList);
			} catch (Exception e) {
				LOG.error("Exception Occured in getDistrictIdListForSubDivisionFilter() method, Exception - ",e); 
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
				
				JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
				List<Long> calCntrIdList = new ArrayList<Long>();
				if(calCntrIdArr != null && calCntrIdArr.length() > 0){
					for (int i = 0; i < calCntrIdArr.length(); i++){
						calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
					}
				}
				Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");      
				Long govtDepartmentId = jObj.getLong("govtDepartmentId");
				Long districtWorkLocationId = jObj.getLong("districtWorkLocationId");
				String group = jObj.getString("group");
				String alertType = jObj.getString("alertType");
				String searchType = jObj.getString("searchType");
				idnameVoList = alertManagementSystemService.getDivisionIdListForSubDivisionFilter(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,districtWorkLocationId,group,alertType,searchType,calCntrIdList);
			} catch (Exception e) {
				LOG.error("Exception Occured in getDivisionIdListForSubDivisionFilter() method, Exception - ",e); 
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
				
				JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
				List<Long> calCntrIdList = new ArrayList<Long>();
				if(calCntrIdArr != null && calCntrIdArr.length() > 0){
					for (int i = 0; i < calCntrIdArr.length(); i++){
						calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
					}
				}
				Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");      
				Long govtDepartmentId = jObj.getLong("govtDepartmentId");
				Long districtWorkLocationId = jObj.getLong("districtWorkLocationId");
				Long divisionWorkLocationId = jObj.getLong("divisionWorkLocationId");
				String group = jObj.getString("group");
				String alertType = jObj.getString("alertType");
				String searchType = jObj.getString("searchType");
				idnameVoList = alertManagementSystemService.getSubDivisionIdListForSubDivisionFilter(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,districtWorkLocationId,divisionWorkLocationId,group,alertType,searchType,calCntrIdList);
			} catch (Exception e) {
				LOG.error("Exception Occured in getSubDivisionIdListForSubDivisionFilter() method, Exception - ",e); 
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
				alertVOs = alertManagementSystemService.getAllDistrictDetails(jObj.getLong("departmentId"));
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
				JSONArray subLevelsArr = jObj.getJSONArray("subLevels"); 
				List<Long> sublevels = new ArrayList<Long>();
				if(subLevelsArr != null && subLevelsArr.length() > 0){
					for (int i = 0; i < subLevelsArr.length(); i++){
						sublevels.add(Long.parseLong(subLevelsArr.getString(i)));          
					}  
				}
				
				JSONArray callCenterArr = jObj.getJSONArray("callCenterArr");  
				List<Long> callCenterIds = new ArrayList<Long>();
				if(callCenterArr != null && callCenterArr.length() > 0){
					for (int i = 0; i < callCenterArr.length(); i++){
						callCenterIds.add(Long.parseLong(callCenterArr.getString(i)));          
					}  
				}
			
				/*if(searchType.equalsIgnoreCase("statusWise"))
				 alertCoreDashBoardVOs = alertManagementSystemService.getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,sortType,order,alertType,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,group,callCenterIds,sublevels);
				else if(searchType.equalsIgnoreCase("scopeWise"))
				  alertCoreDashBoardVOs=alertManagementSystemService.getWorkLocationWiseThenGovtDeptScopeWiseAlertCount(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,sortType,order,alertType,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,group,searchType,sublevels,callCenterIds);
					*/			
				Long filterParentScopeId = jObj.getLong("filterParentScopeId");
				Long filterScopeValue = jObj.getLong("filterScopeValue");
				
				JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
				List<Long> socialMediaTypeIds = new ArrayList<Long>();
				if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
					for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
						socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
					} 
				}
				/*alertCoreDashBoardVOs = alertManagementSystemService.getLocationWiseDepartmentOverviewAlertCount(fromDate,
						toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,sortType,
						order,alertType,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,group,callCenterIds,sublevels,filterParentScopeId,filterScopeValue,searchType,socialMediaTypeIds,null,null,null,null,null,null);*/
		
			} catch (Exception e) {  
				LOG.error("Exception Occured in getDistrictOfficeGraphicalView() method, Exception - ",e); 
			}
			return Action.SUCCESS;	  
		}
		public String getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverview(){
			try {
				session = request.getSession();
				RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				Long userId = regVo.getRegistrationID();
				
				jObj = new JSONObject(getTask());
				String fromDateStr = jObj.getString("fromDateStr");
				String toDateStr = jObj.getString("toDateStr");
				Long stateId = jObj.getLong("stateId");
				
				JSONArray printIdArr = null;
				JSONArray electronicIdArr = null;
				
				List<Long> printIdList = new ArrayList<Long>(0);
				List<Long> electronicIdList = new ArrayList<Long>();
				
				try{
					printIdArr = jObj.getJSONArray("printIdArr");  
				}catch(Exception e)
				{
					LOG.error(e);
				}
				
				if(printIdArr != null && printIdArr.length() > 0){
					for (int i = 0; i < printIdArr.length(); i++){
						printIdList.add(Long.parseLong(printIdArr.getString(i)));        
					} 
				}
				
				try{
					electronicIdArr = jObj.getJSONArray("electronicIdArr");
				}catch(Exception e)
				{
					LOG.error(e);
				}
				
				if(electronicIdArr != null && electronicIdArr.length() > 0){
					for (int i = 0; i < electronicIdArr.length(); i++){
						electronicIdList.add(Long.parseLong(electronicIdArr.getString(i)));        
					} 
				}
				
				Long govtDepartmentId = jObj.getLong("govtDepartmentId");
				Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");
				String sortingType = jObj.getString("sortingType");
				String order = jObj.getString("order");
				String alertType = jObj.getString("alertType");
				Long districtWorkLocationId = jObj.getLong("districtWorkLocationId");
				Long divisionWorkLocationId = jObj.getLong("divisionWorkLocationId");
				Long subDivisionWorkLocationId = jObj.getLong("subDivisionWorkLocationId");
				String group = jObj.getString("group");
				JSONArray subLevelsArr = jObj.getJSONArray("subLevels");  
				List<Long> sublevels = new ArrayList<Long>();
				if(subLevelsArr != null && subLevelsArr.length() > 0){
					for (int i = 0; i < subLevelsArr.length(); i++){
						sublevels.add(Long.parseLong(subLevelsArr.getString(i)));          
					}  
				}
				
				JSONArray callCenterIdArr = jObj.getJSONArray("chanelIdArr");  
				List<Long> callCenterIdList = new ArrayList<Long>();
				if(callCenterIdArr != null && callCenterIdArr.length() > 0){
					for (int i = 0; i < callCenterIdArr.length(); i++){
						callCenterIdList.add(Long.parseLong(callCenterIdArr.getString(i)));        
					} 
				}
				
				String searchType = jObj.getString("searchType");
				Long filterParentScopeId = jObj.getLong("filterParentScopeId");
				Long filterScopeValue = jObj.getLong("filterScopeValue");
				
				JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
				List<Long> socialMediaTypeIds = new ArrayList<Long>();
				if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
					for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
						socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
					} 
				}
				
				JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
				List<Long> alertSeverityIds = new ArrayList<Long>();
				if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
					for (int i = 0; i < alertSeverityIdsArr.length(); i++){
						alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
					} 
				}
				
				JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
				List<Long> alertStatusIds = new ArrayList<Long>();
				if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
					for (int i = 0; i < alertStatusIdsArr.length(); i++){
						alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
					} 
				}
				
				JSONArray alertSubTaskStatusIdsArr = jObj.getJSONArray("alertSubTaskStatusIdsArr");  
				List<Long> subTaskAlertStatusIds = new ArrayList<Long>();
				if(alertSubTaskStatusIdsArr != null && alertSubTaskStatusIdsArr.length() > 0){
					for (int i = 0; i < alertSubTaskStatusIdsArr.length(); i++){
						subTaskAlertStatusIds.add(Long.parseLong(alertSubTaskStatusIdsArr.getString(i)));        
					} 
				}
				JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
				List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
				if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
						mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
					}
				}
				JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
				List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
				if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
					for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
						janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
					} 
				}
				JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
				List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
				if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
						specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
					} 
				}
				
				JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
				List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
				if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
						generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
					} 
				}

				/*alertCoreDashBoardVOs = alertManagementSystemService.getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewDynamic(fromDateStr,
						toDateStr,stateId,printIdList,electronicIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,sortingType,
						order,alertType,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,group,chanelIdList,sublevels);*/
				        alertCoreDashBoardVOs = alertManagementSystemService.getLocationWiseDepartmentOverviewAlertCount(fromDateStr,
						toDateStr,stateId,printIdList,electronicIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,sortingType,
						order,alertType,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,group,callCenterIdList,sublevels,filterParentScopeId,filterScopeValue,searchType,socialMediaTypeIds,alertSeverityIds,alertStatusIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
		
				
			} catch (Exception e) {
				LOG.error("Exception Occured in getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverview() method, Exception - ",e);
			}
			return Action.SUCCESS;	
		}
	 public String stateLevelDeptOfficerDepartmentWiseAlertsView(){
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
				
				JSONArray callCenterArr = jObj.getJSONArray("callCenterArr");  
				List<Long> callCenterIds = new ArrayList<Long>();
				if(callCenterArr != null && callCenterArr.length() > 0){
					for (int i = 0; i < callCenterArr.length(); i++){
						callCenterIds.add(Long.parseLong(callCenterArr.getString(i)));          
					}  
				}
				JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
				List<Long> socialMediaTypeIds = new ArrayList<Long>();
				if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
					for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
						socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
					} 
				}
				
				JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
				List<Long> alertStatusIds = new ArrayList<Long>();
				if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
					for (int i = 0; i < alertStatusIdsArr.length(); i++){
						alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
					} 
				}
				JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
				List<Long> alertSeverityIds = new ArrayList<Long>();
				if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
					for (int i = 0; i < alertSeverityIdsArr.length(); i++){
						alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
					} 
				}
				JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
				List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
				if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
						mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
					}
				}
				JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
				List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
				if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
					for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
						janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
					} 
				}
				JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
				List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
				if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
						specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
					} 
				}
				
				JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
				List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
				if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
						generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
					} 
				}
				alertVOs = alertManagementSystemService.stateLevelDeptOfficerDepartmentWiseAlertsView(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,userId,callCenterIds,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			}catch(Exception e){
				LOG.error("Exception occured in getStatusWiseAlertOverviewCnt() of AlertManagementSystemAction",e);
			}
			return Action.SUCCESS;
		}
		public String getDistrictLevelDeptWiseAlertClick(){
			try{
				session = request.getSession();
			   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				Long userId = regVo.getRegistrationID();
				jObj = new JSONObject(getTask());
				
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
				
				JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
				List<Long> calCntrIdList = new ArrayList<Long>();
				for (int i = 0; i < calCntrIdArr.length(); i++){
					calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
				}
				
				JSONArray govtDepDesigOffcrIds = jObj.getJSONArray("govtDepDesigOffcrIds");  
				List<Long> govtDeptDesiOffIdList = new ArrayList<Long>();
				if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.length() > 0){
					for (int i = 0; i < govtDepDesigOffcrIds.length(); i++){
						govtDeptDesiOffIdList.add(Long.parseLong(govtDepDesigOffcrIds.getString(i)));          
					}
				}
				
				JSONArray govtOfficerIds = jObj.getJSONArray("govtOfficerIds");  
				List<Long> govtOffcrIdList = new ArrayList<Long>();
				if(govtOfficerIds != null && govtOfficerIds.length() > 0){
					for (int i = 0; i < govtOfficerIds.length(); i++){
						govtOffcrIdList.add(Long.parseLong(govtOfficerIds.getString(i)));        
					}
				}
				
				//Long statusId = jObj.getLong("statusId");
				String formDateStr = jObj.getString("formDate");
				String toDateStr = jObj.getString("toDate");
				String clickType =  jObj.getString("clickType");
				JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
				List<Long> socialMediaTypeIds = new ArrayList<Long>();
				if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
					for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
						socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
					} 
				}
				JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
				List<Long> alertStatusIds = new ArrayList<Long>();
				if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
					for (int i = 0; i < alertStatusIdsArr.length(); i++){
						alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
					} 
				}
				JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
				List<Long> alertSeverityIds = new ArrayList<Long>();
				if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
					for (int i = 0; i < alertSeverityIdsArr.length(); i++){
						alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
					} 
				}
				JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
				List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
				if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
						mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
					}
				}
				JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
				List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
				if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
					for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
						janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
					} 
				}
				JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
				List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
				if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
						specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
					} 
				}
				
				JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
				List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
				if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
					for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
						generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
					} 
				}
			
				alertCoreDashBoardVOs = alertManagementSystemService.getDistrictLevelDeptWiseAlertClick(userId,govtOffcrIdList,alertStatusIds,formDateStr,toDateStr,clickType,paperIdList,chanelIdList,calCntrIdList,socialMediaTypeIds,alertSeverityIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Exception occured in getDistrictLevelDeptWiseAlertClick() of alertManagementSystemAction",e);
			}
			return Action.SUCCESS;
		}
		 public String stateLevelDeptOfficerLocationLevelOverviewBySubTasks(){
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
					
					JSONArray callCenterIdsArr = jObj.getJSONArray("callCenterIdsArr");  
					List<Long> callCenterIdsList = new ArrayList<Long>(0);
					for (int i = 0; i < callCenterIdsArr.length(); i++){
						callCenterIdsList.add(Long.parseLong(callCenterIdsArr.getString(i)));        
					}
					JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
					List<Long> socialMediaTypeIds = new ArrayList<Long>();
					if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
						for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
							socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
						} 
					}
					JSONArray alertSubTaskStatusIdsArr = jObj.getJSONArray("alertSubTaskStatusIdsArr");  
					List<Long> subTaskAlertStatusIds = new ArrayList<Long>();
					if(alertSubTaskStatusIdsArr != null && alertSubTaskStatusIdsArr.length() > 0){
						for (int i = 0; i < alertSubTaskStatusIdsArr.length(); i++){
							subTaskAlertStatusIds.add(Long.parseLong(alertSubTaskStatusIdsArr.getString(i)));        
						} 
					}
					JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
					List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
					if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
							mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
						}
					}
					JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
					List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
					if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
						for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
							janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
						} 
					}
					JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
					List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
					if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
							specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
						} 
					}
					
					JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
					List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
					if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
							generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
						} 
					}
					alertVOs = alertManagementSystemService.stateLevelDeptOfficerLocationLevelOverviewBySubTasks(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,userId,callCenterIdsList,socialMediaTypeIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				}catch(Exception e){
					LOG.error("Exception occured in stateLevelDeptOfficerLocationLevelOverviewBySubTasks() of AlertManagementSystemAction",e);
				}
				return Action.SUCCESS;
			}
		 public String stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick(){
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
					
					JSONArray callCenterIdsArr = jObj.getJSONArray("callCenterIdsArr");  
					List<Long> callCenterIdsList = new ArrayList<Long>();
					for (int i = 0; i < callCenterIdsArr.length(); i++){
						callCenterIdsList.add(Long.parseLong(callCenterIdsArr.getString(i)));        
					}
					JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
					List<Long> socialMediaTypeIds = new ArrayList<Long>();
					if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
						for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
							socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
						} 
					}
					JSONArray alertSubTaskStatusIdsArr = jObj.getJSONArray("alertSubTaskStatusIdsArr");  
					List<Long> subTaskAlertStatusIds = new ArrayList<Long>();
					if(alertSubTaskStatusIdsArr != null && alertSubTaskStatusIdsArr.length() > 0){
						for (int i = 0; i < alertSubTaskStatusIdsArr.length(); i++){
							subTaskAlertStatusIds.add(Long.parseLong(alertSubTaskStatusIdsArr.getString(i)));        
						} 
					}
					JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
					List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
					if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
							mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
						}
					}
					JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
					List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
					if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
						for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
							janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
						} 
					}
					JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
					List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
					if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
							specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
						} 
					}
					
					JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
					List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
					if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
							generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
						} 
					}
					alertVOs = alertManagementSystemService.stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,userId,callCenterIdsList,socialMediaTypeIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				}catch(Exception e){
					LOG.error("Exception occured in stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick() of AlertManagementSystemAction",e);
				}
				return Action.SUCCESS;
			}
		public String getGovtGrievanceAlertDetails(){
			try{
				//session = request.getSession();
			   	//RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				//Long scopeId = regVo.getRegistrationID();
				jObj = new JSONObject(getTask());
				String mobileNo = jObj.getString("mobileNo");
				String locatoinType = jObj.getString("locatoinType");
				Long locationId = jObj.getLong("locationId");
				String startDate = jObj.getString("fromDate");
				String endDate = jObj.getString("toDate");
				Long alertStatusId = jObj.getLong("alertStatusId");
				Long deptId = jObj.getLong("deptId");
				grievanceAlertVo = alertManagementSystemService.getGovtGrievanceAlertDetails(mobileNo,locatoinType,locationId,startDate,endDate,alertStatusId,deptId);
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Exception occured in getGovtGrievanceAlertDetails() of alertManagementSystemAction",e);
			}
			return Action.SUCCESS;
		} 
		public String getFilterSectionAlertDetails(){
			try{
				session = request.getSession();
			   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
				Long userId = regVo.getRegistrationID();
				jObj = new JSONObject(getTask());
				JSONArray deptIdArr = jObj.getJSONArray("deptIdArr");  
				List<Long> deptIdList = new ArrayList<Long>();
				for (int i = 0; i < deptIdArr.length(); i++){
					deptIdList.add(Long.parseLong(deptIdArr.getString(i)));        
				}  
				filterDetilsList = alertManagementSystemService.getFilterSectionAlertDetails(userId,deptIdList);
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Exception occured in getFilterSectionAlertDetails() of alertManagementSystemAction",e);
			}
			return Action.SUCCESS;
		}
		 public String getDeptDetails(){
				try{
					session = request.getSession();
					RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
					Long userId = regVo.getRegistrationID();
					jObj = new JSONObject(getTask());
				   	districtOfficeViewAlertVO = alertManagementSystemService.getDeptDetails(userId);
					
				}catch(Exception e){
					e.printStackTrace();
					LOG.error("Exception occured in getDeptDetails() of AlertManagementSystemAction",e);
				}
				return Action.SUCCESS;
			}
			public String getStateLevelAlertclickView(){
				try{
					session = request.getSession();
				  	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
					Long userId = regVo.getRegistrationID();
					jObj = new JSONObject(getTask());
					
					JSONArray deptIdsArr = jObj.getJSONArray("deptIdsArr");  
					List<Long> deptList = new ArrayList<Long>();
					for (int i = 0; i < deptIdsArr.length(); i++){
						deptList.add(Long.parseLong(deptIdsArr.getString(i)));        
					}
					
					//Long statusId = jObj.getLong("statusId");
					String type = jObj.getString("type");
					String searchType = jObj.getString("searchType");
					String startDate = jObj.getString("fromDate");
					String endDate = jObj.getString("toDate");
					JSONArray officerIdsArr = jObj.getJSONArray("officerIdsArr");  
					List<Long> officerIdList = new ArrayList<Long>();
					for (int i = 0; i < officerIdsArr.length(); i++){
						officerIdList.add(Long.parseLong(officerIdsArr.getString(i)));        
					} 
					
					JSONArray desigDeptOfficerIdsArr = jObj.getJSONArray("desigDeptOfficerIdsArr");  
					List<Long> desigDeptOfficerIds = new ArrayList<Long>();
					for (int i = 0; i < desigDeptOfficerIdsArr.length(); i++){
						desigDeptOfficerIds.add(Long.parseLong(desigDeptOfficerIdsArr.getString(i)));        
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
					
					JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
					List<Long> calCntrIdList = new ArrayList<Long>();  
					if(calCntrIdArr != null && calCntrIdArr.length() > 0){
						for (int i = 0; i < calCntrIdArr.length(); i++){
							calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
						}
					}
					JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
					List<Long> socialMediaTypeIds = new ArrayList<Long>();
					if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
						for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
							socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
						} 
					}
					
					JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
					List<Long> alertSeverityIds = new ArrayList<Long>();
					if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
						for (int i = 0; i < alertSeverityIdsArr.length(); i++){
							alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
						} 
					}
					
					JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
					List<Long> alertStatusIds = new ArrayList<Long>();
					if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
						for (int i = 0; i < alertStatusIdsArr.length(); i++){
							alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
						} 
					}
					JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
					List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
					if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
							mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
						}
					}
					JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
					List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
					if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
						for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
							janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
						} 
					}
					JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
					List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
					if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
							specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
						} 
					}
					
					JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
					List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
					if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
							generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
						} 
					}
					
					alertCoreDashBoardVOs = alertManagementSystemService.getStateLevelAlertclickView(deptList,alertStatusIds,type,desigDeptOfficerIds,officerIdList,searchType,startDate,endDate,paperIdList,chanelIdList,calCntrIdList,socialMediaTypeIds,alertSeverityIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
					alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
				}catch(Exception e){
					e.printStackTrace();
					LOG.error("Exception occured in getStateLevelAlertclickView() of alertManagementSystemAction",e);
				}
				return Action.SUCCESS;
			}
			public String getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverviewForClick(){
				try{
					session = request.getSession();
					RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
					Long userId = regVo.getRegistrationID();
					jObj = new JSONObject(getTask());
					String fromDate = jObj.getString("fromDate");
					String toDate = jObj.getString("toDate");
					Long stateId = jObj.getLong("stateId");
					
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
					
					JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
					List<Long> calCntrIdList = new ArrayList<Long>();  
					if(calCntrIdArr != null && calCntrIdArr.length() > 0){
						for (int i = 0; i < calCntrIdArr.length(); i++){
							calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
						}
					}
					
					Long govtDepartmentId = jObj.getLong("govtDepartmentId");
					Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");
					Long deptScopeId = jObj.getLong("deptScopeId");
					Long statusId = jObj.getLong("statusId");
					alertCoreDashBoardVOs = alertManagementSystemService.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverviewForClick(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeId,statusId,calCntrIdList);
					alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
				}catch(Exception e){
					e.printStackTrace();
					LOG.error("Exception occured in getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverviewForClick() of alertManagementSystemAction",e);
				}
				return Action.SUCCESS;
			}
			public String getStatusCompletionInfoForSubTask(){
				try{
					session = request.getSession();
				   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
					Long userId = regVo.getRegistrationID();
					jObj = new JSONObject(getTask());
					
					Long alertId = jObj.getLong("alertId");
					Long subTaskId = jObj.getLong("subTaskId");
					idnameVoList = alertManagementSystemService.getStatusCompletionInfoForSubTask(alertId,subTaskId,userId);
				}catch(Exception e){
					e.printStackTrace();
					LOG.error("Exception occured in getStatusCompletionInfoForSubTask() of alertManagementSystemAction",e);
				}
				return Action.SUCCESS;
			}
			public String getStateLevelDeptWiseFlterClick(){
				try{
					session = request.getSession();
				   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
					Long scopeId = regVo.getRegistrationID();
					jObj = new JSONObject(getTask());
					JSONArray departmentIdsArr = jObj.getJSONArray("departmentIdsArr");  
					List<Long> deptIdList = new ArrayList<Long>();  
					if(departmentIdsArr != null && departmentIdsArr.length() > 0){
						for (int i = 0; i < departmentIdsArr.length(); i++){
							deptIdList.add(Long.parseLong(departmentIdsArr.getString(i)));        
						}
					}
					Long levelId = jObj.getLong("levelId");
				//	Long statusId = jObj.getLong("statusId");
					String type = jObj.getString("type");
					String formDateStr = jObj.getString("startDate");
					String endDateStr = jObj.getString("endDate");
					Long desigDeptOfficerId = jObj.getLong("desigDeptOfficerId");
					Long officerId = jObj.getLong("officerId");
					
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
					
					JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
					List<Long> calCntrIdList = new ArrayList<Long>();  
					if(calCntrIdArr != null && calCntrIdArr.length() > 0){
						for (int i = 0; i < calCntrIdArr.length(); i++){
							calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
						}
					}
					Long stateId = jObj.getLong("stateId");
					String levelType = jObj.getString("levelType");
					String assignType = jObj.getString("alertType");
					
					JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
					List<Long> socialMediaTypeIds = new ArrayList<Long>();
					if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
						for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
							socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
						} 
					}
					
					JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
					List<Long> alertSeverityIds = new ArrayList<Long>();
					if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
						for (int i = 0; i < alertSeverityIdsArr.length(); i++){
							alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
						} 
					}
					
					JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
					List<Long> alertStatusIds = new ArrayList<Long>();
					if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
						for (int i = 0; i < alertStatusIdsArr.length(); i++){
							alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
						} 
					}
					JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
					List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
					if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
							mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
						}
					}
					JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
					List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
					if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
						for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
							janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
						} 
					}
					JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
					List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
					if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
							specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
						} 
					}
					
					JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
					List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
					if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
							generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
						} 
					}
					alertCoreDashBoardVOs = alertManagementSystemService.getStateLevelDeptWiseFlterClick(scopeId,deptIdList,levelId,alertStatusIds,type,formDateStr,endDateStr,desigDeptOfficerId,officerId,paperIdList,chanelIdList,calCntrIdList,stateId,levelType,assignType,socialMediaTypeIds,alertSeverityIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
					alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
				}catch(Exception e){
					e.printStackTrace();
					LOG.error("Exception occured in getStateLevelDeptWiseFlterClick() of alertManagementSystemAction",e);
				}
				return Action.SUCCESS;
			}
			
			public String getGovtAllDepartmentDetails(){
				try {					
					idnameVoList = alertManagementSystemService.getGovtAllDepartmentDetails();									
				} catch (Exception e) {
					e.printStackTrace();
					LOG.error("Exception occured in getGovtAllDepartmentDetails() of alertManagementSystemAction",e);
				}
				return Action.SUCCESS;
			}
			public String getLocationBasedOnDepartmentLevel(){
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
					
					JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
					List<Long> calCntrIdList = new ArrayList<Long>();
					for (int i = 0; i < calCntrIdArr.length(); i++){
						calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
					}
					Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");      
					Long govtDepartmentId = jObj.getLong("govtDepartmentId");
					String alertType = jObj.getString("alertType");
					
					JSONArray subLevelArr = jObj.getJSONArray("subLevelArr");  
					List<Long> deptScopeList = new ArrayList<Long>();
					for (int i = 0; i < subLevelArr.length(); i++){
						deptScopeList.add(Long.parseLong(subLevelArr.getString(i)));        
					}  
					JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
					List<Long> socialMediaTypeIds = new ArrayList<Long>();
					if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
						for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
							socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
						} 
					}
					
					JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
					List<Long> alertSeverityIds = new ArrayList<Long>();
					if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
						for (int i = 0; i < alertSeverityIdsArr.length(); i++){
							alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
						} 
					}
					
					JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
					List<Long> alertStatusIds = new ArrayList<Long>();
					if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
						for (int i = 0; i < alertStatusIdsArr.length(); i++){
							alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
						} 
					}
					
					JSONArray alertSubTaskStatusIdsArr = jObj.getJSONArray("alertSubTaskStatusIdsArr");  
					List<Long> subTaskAlertStatusIds = new ArrayList<Long>();
					if(alertSubTaskStatusIdsArr != null && alertSubTaskStatusIdsArr.length() > 0){
						for (int i = 0; i < alertSubTaskStatusIdsArr.length(); i++){
							subTaskAlertStatusIds.add(Long.parseLong(alertSubTaskStatusIdsArr.getString(i)));        
						} 
					}
					JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
					List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
					if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
							mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
						}
					}
					JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
					List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
					if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
						for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
							janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
						} 
					}
					JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
					List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
					if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
							specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
						} 
					}
					
					JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
					List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
					if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
							generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
						} 
					}
					String reportType = jObj.getString("reportType");
					Long childLevelId = jObj.getLong("childLevelId");
					idnameVoList = alertManagementSystemService.getLocationBasedOnDepartmentLevel(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,alertType,calCntrIdList,deptScopeList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds,reportType,childLevelId);
				} catch (Exception e) {
					LOG.error("Exception Occured in getLocationBasedOnDepartmentLevel() method, Exception - ",e); 
				}
				return Action.SUCCESS;	
			}
			public String getChildLocationBasedOnParentLocation(){
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
					
					JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
					List<Long> calCntrIdList = new ArrayList<Long>();
					for (int i = 0; i < calCntrIdArr.length(); i++){
						calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
					}
					Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");      
					Long parentGovtDepartmentScopeValue = jObj.getLong("parentGovtDepartmentScopeValue");
					Long govtDepartmentId = jObj.getLong("govtDepartmentId");
					Long childLevelId = jObj.getLong("childLevelId");
					String alertType = jObj.getString("alertType");
					
					JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
					List<Long> socialMediaTypeIds = new ArrayList<Long>();
					if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
						for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
							socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
						} 
					}
					
					JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
					List<Long> alertSeverityIds = new ArrayList<Long>();
					if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
						for (int i = 0; i < alertSeverityIdsArr.length(); i++){
							alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
						} 
					}
					
					JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
					List<Long> alertStatusIds = new ArrayList<Long>();
					if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
						for (int i = 0; i < alertStatusIdsArr.length(); i++){
							alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
						} 
					}
					
					JSONArray alertSubTaskStatusIdsArr = jObj.getJSONArray("alertSubTaskStatusIdsArr");  
					List<Long> subTaskAlertStatusIds = new ArrayList<Long>();
					if(alertSubTaskStatusIdsArr != null && alertSubTaskStatusIdsArr.length() > 0){
						for (int i = 0; i < alertSubTaskStatusIdsArr.length(); i++){
							subTaskAlertStatusIds.add(Long.parseLong(alertSubTaskStatusIdsArr.getString(i)));        
						} 
					}
					JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
					List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
					if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
							mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
						}
					}
					JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
					List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
					if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
						for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
							janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
						} 
					}
					JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
					List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
					if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
							specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
						} 
					}
					
					JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
					List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
					if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
							generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
						} 
					}
					String reportType = jObj.getString("reportType");
					Long resultLevelDeptScopeId = jObj.getLong("resultLevelDeptScopeId");
					idnameVoList = alertManagementSystemService.getChildLocationBasedOnParentLocation(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,parentGovtDepartmentScopeValue,childLevelId,alertType ,calCntrIdList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds,reportType,resultLevelDeptScopeId);
				} catch (Exception e) {
					LOG.error("Exception Occured in getChildLocationBasedOnParentLocation() method, Exception - ",e); 
				}
				return Action.SUCCESS;	
			}
			public String getAlertDetailsBasedOnLocation(){
				try{
					session = request.getSession();
					RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
					Long userId = regVo.getRegistrationID();
					jObj = new JSONObject(getTask());
					String fromDate = jObj.getString("fromDate");
					String toDate = jObj.getString("toDate");
					Long stateId = jObj.getLong("stateId");
					
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
					
					JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
					List<Long> calCntrIdList = new ArrayList<Long>();  
					if(calCntrIdArr != null && calCntrIdArr.length() > 0){
						for (int i = 0; i < calCntrIdArr.length(); i++){
							calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
						}
					}
					JSONArray subLevels = jObj.getJSONArray("subLevels");  
					List<Long> deptSubLevelIds = new ArrayList<Long>();
					if(subLevels != null && subLevels.length() > 0){
						for (int i = 0; i < subLevels.length(); i++){
							deptSubLevelIds.add(Long.parseLong(subLevels.getString(i)));        
						}
					}
					
					Long govtDepartmentId = jObj.getLong("govtDepartmentId");
					Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");
					Long deptScopeId = jObj.getLong("deptScopeId");
					Long govtOfficerId = jObj.getLong("govtOfficerId");
					Long locationValue = jObj.getLong("locationValue");
					String alertType = jObj.getString("alertType");
					Long alertCategoryId = jObj.getLong("alertCategoryId");
					
					JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
					List<Long> socialMediaTypeIds = new ArrayList<Long>();
					if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
						for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
							socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
						} 
					}
					
					JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
					List<Long> alertSeverityIds = new ArrayList<Long>();
					if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
						for (int i = 0; i < alertSeverityIdsArr.length(); i++){
							alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
						} 
					}
					
					JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
					List<Long> alertStatusIds = new ArrayList<Long>();
					if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
						for (int i = 0; i < alertStatusIdsArr.length(); i++){
							alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
						} 
					}
					JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
					List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
					if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
							mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
						}
					}
					JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
					List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
					if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
						for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
							janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
						} 
					}
					JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
					List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
					if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
							specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
						} 
					}
					
					JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
					List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
					if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
						for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
							generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
						} 
					}
					alertCoreDashBoardVOs = alertManagementSystemService.getAlertDetailsBasedOnLocation(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeId,alertStatusIds,calCntrIdList,locationValue,alertType,alertCategoryId,deptSubLevelIds,socialMediaTypeIds,alertSeverityIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds,govtOfficerId);
					alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
				}catch(Exception e){
					e.printStackTrace();
					LOG.error("Exception occured in getAlertDetailsBasedOnLocation() of alertManagementSystemAction",e);
				}
				return Action.SUCCESS;
			}
			 public String getDepartmentDetailsByDepartment(){    
				 try{
					 session = request.getSession();
					 RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
					 Long userId = regVo.getRegistrationID();
					 jObj = new JSONObject(getTask());
					 Long departmentId = jObj.getLong("departmentId");
					 String designationType= jObj.getString("designationType");
					 idnameVoList = alertManagementSystemService.getDepartmentDetailsByDepartmentId(userId,departmentId,designationType);
					 
				 }catch(Exception e){
					 LOG.error("Exception Occured in getDepartmentDetailsByDepartmentId() method, Exception - ",e); 
				 }
				 return Action.SUCCESS;
			 } 
			 
			 public String getDepartmentDetailsOfAlert(){				 
				try {
					jObj = new JSONObject(getTask());
					 
					Long alertId= jObj.getLong("alertId");
					idnameVoList = alertManagementSystemService.getDepartmentDetailsOfAlert(alertId);
					
				}catch (Exception e) {
					LOG.error("Exception Occured in getDepartmentDetailsOfAlert() method, Exception - ",e);
				}
				return Action.SUCCESS;
			 }
			 public String getLevelsForDepartmnt(){
					try {
						session = request.getSession();
						RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
						Long userId = regVo.getRegistrationID();
						
						jObj = new JSONObject(getTask());
						Long departmentId = jObj.getLong("departmentId");
						
						idnameVoList = alertManagementSystemService.getLvlsForDepatmnt(userId,departmentId);
						
						
					} catch (Exception e) {
						LOG.error("Exception occured in getDepartmentLevels() of AlertManagementSystemAction",e);
					}
					return Action.SUCCESS;
				}
				public String getStatusByType(){
					try {
						
						jObj = new JSONObject(getTask());
						String type = jObj.getString("type");
						
						idnameVoList = alertManagementSystemService.getStatusByType(type);
						
						
					} catch (Exception e) {
						LOG.error("Exception occured in getStatusByType() of AlertManagementSystemAction",e);
					}
					return Action.SUCCESS;
				}
				
				public String getSubOrdinateFilterAlertsOverview(){
					try {
						session = request.getSession();
					   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
						Long userId = regVo.getRegistrationID();
						jObj = new JSONObject(getTask());			
						//Long userId = jObj.getLong("userId");
						String fromDateStr = jObj.getString("fromDate");
						String toDateStr = jObj.getString("toDateStr");
						
						
						JSONArray govtScopeIds = jObj.getJSONArray("govtLevelIds");  
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
						
						JSONArray statusIdsArr = jObj.getJSONArray("statusIds");  
						List<Long> statusIds = new ArrayList<Long>();
						if(statusIdsArr != null && statusIdsArr.length() > 0){
							for (int i = 0; i < statusIdsArr.length(); i++){
								statusIds.add(Long.parseLong(statusIdsArr.getString(i)));        
							} 
						} 
						
						JSONArray deptIdsArr = jObj.getJSONArray("deptIds");  
						List<Long> deptIds = new ArrayList<Long>();
						if(deptIdsArr != null && deptIdsArr.length() > 0){
							for (int i = 0; i < deptIdsArr.length(); i++){
								deptIds.add(Long.parseLong(deptIdsArr.getString(i)));        
							} 
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
						
						JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
						List<Long> calCntrIdList = new ArrayList<Long>();  
						if(calCntrIdArr != null && calCntrIdArr.length() > 0){
							for (int i = 0; i < calCntrIdArr.length(); i++){
								calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
							}
						}
						Long priorityId = jObj.getLong("priorityId");
						Long lagStartCnt = jObj.getLong("lagStartCnt");
						Long lagEndCnt = jObj.getLong("lagEndCnt");
						String alertType = jObj.getString("alertType");
						String isMoreThanYrChkd = jObj.getString("isMoreThanYrChkd");
						String isLagChkd = jObj.getString("isLagChkd");
						Long childLevelId = jObj.getLong("childLevelId");
						
						JSONArray childLevelValsArr = jObj.getJSONArray("childLevelVals");  
						List<Long> childLevelVals = new ArrayList<Long>();
						if(childLevelValsArr != null && childLevelValsArr.length() > 0){
							for (int i = 0; i < childLevelValsArr.length(); i++){
								childLevelVals.add(Long.parseLong(childLevelValsArr.getString(i)));        
							} 
						} 
						
						JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
						List<Long> socialMediaTypeIds = new ArrayList<Long>();
						if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
							for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
								socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
							} 
						}
						JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
						List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
						if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
							for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
								mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
							}
						}
						JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
						List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
						if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
							for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
								janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
							} 
						}
						JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
						List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
						if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
							for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
								specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
							} 
						}
						
						JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
						List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
						if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
							for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
								generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
							} 
						}
						districtOfficeViewAlertVOList = alertManagementSystemService.getSubOrdinateFilterAlertsOverview(userId,fromDateStr,toDateStr,govtScopeIdsList,locationValuesList,desigIdsList,priorityId,statusIds,deptIds,
								lagStartCnt,lagEndCnt,alertType,isMoreThanYrChkd,isLagChkd,paperIdList,chanelIdList,calCntrIdList,childLevelVals,childLevelId,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
					} catch (Exception e) {
						LOG.error("Exception occured in getSubOrdinateFilterAlertsOverview() of alertManagementSystemAction",e);
					}
					return Action.SUCCESS;
				}
				public String getTotalAlertByStatusNew(){
					try{
						session = request.getSession();
						RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
						Long userId = regVo.getRegistrationID();
						jObj = new JSONObject(getTask());
						String fromDate = jObj.getString("fromDate");
						String toDate = jObj.getString("toDate");
						Long stateId = jObj.getLong("stateId");
						//Long statusId = jObj.getLong("statusId");
						JSONArray statusIdArr = jObj.getJSONArray("statusId");  
						List<Long> statusIdList = new ArrayList<Long>();
						if(statusIdArr != null && statusIdArr.length() > 0){
							for (int i = 0; i < statusIdArr.length(); i++){
								statusIdList.add(Long.parseLong(statusIdArr.getString(i)));        
							} 
						}
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
						
						JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
						List<Long> calCntrIdList = new ArrayList<Long>();
						for (int i = 0; i < calCntrIdArr.length(); i++){
							calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
						}
						JSONArray impactLevelIdArr = jObj.getJSONArray("impactLevelArr");  
						List<Long> impactLevelIdList = new ArrayList<Long>();
						for (int i = 0; i < impactLevelIdArr.length(); i++){
							impactLevelIdList.add(Long.parseLong(impactLevelIdArr.getString(i)));        
						}
						JSONArray priorityIdArr = jObj.getJSONArray("priorityArr");  
						List<Long> priorityIdList = new ArrayList<Long>();
						for (int i = 0; i < priorityIdArr.length(); i++){
							priorityIdList.add(Long.parseLong(priorityIdArr.getString(i)));        
						}
						JSONArray alertSourceIdArr = jObj.getJSONArray("alertSourceArr");  
						List<Long> alertSourceIdList = new ArrayList<Long>();
						for (int i = 0; i < alertSourceIdArr.length(); i++){
							alertSourceIdList.add(Long.parseLong(alertSourceIdArr.getString(i)));        
						}
						JSONArray printMediaIdArr = jObj.getJSONArray("printMediaArr");  
						List<Long> printMediaIdList = new ArrayList<Long>();
						for (int i = 0; i < printMediaIdArr.length(); i++){
							printMediaIdList.add(Long.parseLong(printMediaIdArr.getString(i)));        
						}
						JSONArray electronicMediaIdArr = jObj.getJSONArray("electronicMediaArr");  
						List<Long> electronicMediaIdList = new ArrayList<Long>();
						for (int i = 0; i < electronicMediaIdArr.length(); i++){
							electronicMediaIdList.add(Long.parseLong(electronicMediaIdArr.getString(i)));        
						}
						Long startDay = jObj.getLong("startDay");
						Long endDay = jObj.getLong("endDay");
						Long scopeId =jObj.getLong("levelId");
						JSONArray locationIdArr = jObj.getJSONArray("levelValues");  
						List<Long> locationIdList = new ArrayList<Long>();
						for (int i = 0; i < locationIdArr.length(); i++){
							locationIdList.add(Long.parseLong(locationIdArr.getString(i)));        
						}
						JSONArray subTaskStatusIdArr = jObj.getJSONArray("subTaskStatusIdList");  
						List<Long> subTaskStatusIdList = new ArrayList<Long>();
						for (int i = 0; i < subTaskStatusIdArr.length(); i++){
							subTaskStatusIdList.add(Long.parseLong(subTaskStatusIdArr.getString(i)));        
						}
						String isMoreThanYrChkd =jObj.getString("isMoreThanYrChkd");
						String isLagChkd =jObj.getString("isLagChkd");
						JSONArray filterSocialMediaIdsArr = jObj.getJSONArray("filterSocialMediaIdsArr");  
						List<Long> filterSocialMediaIds = new ArrayList<Long>();
						if(filterSocialMediaIdsArr != null && filterSocialMediaIdsArr.length() > 0){
							for (int i = 0; i < filterSocialMediaIdsArr.length(); i++){
								filterSocialMediaIds.add(Long.parseLong(filterSocialMediaIdsArr.getString(i)));        
							} 
						}
						JSONArray filterCallCenterArr = jObj.getJSONArray("filterCallCenterArr");  
						List<Long> filterCallCenterIdList = new ArrayList<Long>();
						for (int i = 0; i < filterCallCenterArr.length(); i++){
							filterCallCenterIdList.add(Long.parseLong(filterCallCenterArr.getString(i)));        
						}
						JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
						List<Long> socialMediaTypeIds = new ArrayList<Long>();
						if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
							for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
								socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
							} 
						}
						
						JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
						List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
						if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
							for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
								mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
							}
						}
						JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
						List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
						if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
							for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
								janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
							} 
						}
						JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
						List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
						if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
							for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
								specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
							} 
						}
						
						JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
						List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
						if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
							for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
								generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
							} 
						}
						
						
						List<AlertCoreDashBoardVO> list1 = new ArrayList<AlertCoreDashBoardVO>();
						List<AlertCoreDashBoardVO> list2 = new ArrayList<AlertCoreDashBoardVO>();
						List<AlertCoreDashBoardVO> list3 = new ArrayList<AlertCoreDashBoardVO>();
						List<AlertCoreDashBoardVO> list4 = new ArrayList<AlertCoreDashBoardVO>();
						if(statusIdList.isEmpty() && subTaskStatusIdList.isEmpty()){
							alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertBySubTaskStatusNew(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusIdList,userId,null,null,calCntrIdList,impactLevelIdList,priorityIdList,alertSourceIdList,printMediaIdList,electronicMediaIdList,startDay,endDay,scopeId,locationIdList,subTaskStatusIdList,isMoreThanYrChkd,isLagChkd,filterSocialMediaIds,filterCallCenterIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
							//alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
							if(alertCoreDashBoardVOs != null){
								list1.addAll(alertCoreDashBoardVOs);
							}
						}
						if(subTaskStatusIdList != null && subTaskStatusIdList.size()>0 && !subTaskStatusIdList.isEmpty()){
							alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByOtherStatusNew(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusIdList,userId,null,null,calCntrIdList,impactLevelIdList,priorityIdList,alertSourceIdList,printMediaIdList,electronicMediaIdList,startDay,endDay,scopeId,locationIdList,subTaskStatusIdList,isMoreThanYrChkd,isLagChkd,filterSocialMediaIds,filterCallCenterIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
							//alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
							if(alertCoreDashBoardVOs != null){
								list2.addAll(alertCoreDashBoardVOs);
							}
						}
						if(statusIdList != null && statusIdList.contains(1L) && !statusIdList.isEmpty()){
							alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByStatusNew(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusIdList,null,calCntrIdList,impactLevelIdList,priorityIdList,alertSourceIdList,printMediaIdList,electronicMediaIdList,startDay,endDay,scopeId,locationIdList,subTaskStatusIdList,isMoreThanYrChkd,isLagChkd,filterSocialMediaIds,filterCallCenterIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
							//alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
							statusIdList.remove(1L);
							if(alertCoreDashBoardVOs != null){
								list3.addAll(alertCoreDashBoardVOs);
							}
						}
						if(statusIdList != null && !statusIdList.contains(1L) && !statusIdList.isEmpty()){
							alertCoreDashBoardVOs = alertManagementSystemService.getTotalAlertByOtherStatusNew(fromDate, toDate, stateId, paperIdList, chanelIdList, deptIdList,statusIdList,userId,null,null,calCntrIdList,impactLevelIdList,priorityIdList,alertSourceIdList,printMediaIdList,electronicMediaIdList,startDay,endDay,scopeId,locationIdList,subTaskStatusIdList,isMoreThanYrChkd,isLagChkd,filterSocialMediaIds,filterCallCenterIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
							//alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
							if(alertCoreDashBoardVOs != null){
								list4.addAll(alertCoreDashBoardVOs);
							}
						}
						alertCoreDashBoardVOs.clear();
						alertCoreDashBoardVOs.addAll(list1);
						alertCoreDashBoardVOs.addAll(list2);
						alertCoreDashBoardVOs.addAll(list3);
						alertCoreDashBoardVOs.addAll(list4);
						alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
					}catch(Exception e){
						LOG.error("Exception occured in getAlertDetailsForEdit() of CreateAlertAction",e);
					}
					return Action.SUCCESS;
				}
				public String getFilterSectionAlertNewDetails(){
					try{
						session = request.getSession();
					   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
						Long userId = regVo.getRegistrationID();
						jObj = new JSONObject(getTask());
						JSONArray deptIdArr = jObj.getJSONArray("deptIdArr");  
						List<Long> deptIdList = new ArrayList<Long>();
						for (int i = 0; i < deptIdArr.length(); i++){
							deptIdList.add(Long.parseLong(deptIdArr.getString(i)));        
						} 
						filterDetilsList = alertManagementSystemService.getFilterSectionAlertNewDetails(userId,deptIdList);
					}catch(Exception e){
						e.printStackTrace();
						LOG.error("Exception occured in getFilterSectionAlertDetails() of alertManagementSystemAction",e);
					}
					return Action.SUCCESS;
			 }
			 public String getBellowDistrictOfficerAlertsCountView(){
					try{
						session = request.getSession();
					   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
						Long userId = regVo.getRegistrationID();
						jObj = new JSONObject(getTask());
						String fromDate = jObj.getString("fromDate");
						String toDate = jObj.getString("toDate");
						String task = jObj.getString("task");
						JSONArray paperIdArr = jObj.getJSONArray("paperIdArr"); 
						String sortingType = jObj.getString("sortingType");
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
						
						JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
						List<Long> calCntrIdList = new ArrayList<Long>();
						for (int i = 0; i < calCntrIdArr.length(); i++){
							calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
						}
						JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
						List<Long> socialMediaTypeIds = new ArrayList<Long>();
						if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
							for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
								socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
							} 
						}
						JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
						List<Long> alertSeverityIds = new ArrayList<Long>();
						if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
							for (int i = 0; i < alertSeverityIdsArr.length(); i++){
								alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
							} 
						}
						
						JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
						List<Long> alertStatusIds = new ArrayList<Long>();
						if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
							for (int i = 0; i < alertStatusIdsArr.length(); i++){
								alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
							} 
						}
						
						JSONArray alertSubTaskStatusIdsArr = jObj.getJSONArray("alertSubTaskStatusIdsArr");  
						List<Long> subTaskAlertStatusIds = new ArrayList<Long>();
						if(alertSubTaskStatusIdsArr != null && alertSubTaskStatusIdsArr.length() > 0){
							for (int i = 0; i < alertSubTaskStatusIdsArr.length(); i++){
								subTaskAlertStatusIds.add(Long.parseLong(alertSubTaskStatusIdsArr.getString(i)));        
							} 
						}
						
						JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
						List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
						if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
							for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
								mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
							}
						}
						JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
						List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
						if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
							for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
								janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
							} 
						}
						JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
						List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
						if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
							for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
								specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
							} 
						}
						
						JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
						List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
						if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
							for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
								generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
							} 
						}
						
						districtOfficeViewAlertVOList = alertManagementSystemService.getBellowDistrictOfficerAlertsCountView(userId,fromDate,toDate,paperIdList,chanelIdList,calCntrIdList,task,sortingType,socialMediaTypeIds,alertSeverityIds,alertStatusIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
					}catch(Exception e){
						e.printStackTrace();
						LOG.error("Exception occured in getFilterSectionAlertDetails() of alertManagementSystemAction",e);
					}
					return Action.SUCCESS;
			 }
public String getAlertSourceWiseAlert(){
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			} 
			String userType = jObj.getString("userType");
			
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
			List<Long> alertSeverityIds = new ArrayList<Long>();
			if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
				for (int i = 0; i < alertSeverityIdsArr.length(); i++){
					alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
				} 
			}
			
			JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
			List<Long> alertStatusIds = new ArrayList<Long>();
			if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
				for (int i = 0; i < alertStatusIdsArr.length(); i++){
					alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
				} 
			}
			
			JSONArray alertSubTaskStatusIdsArr = jObj.getJSONArray("alertSubTaskStatusIdsArr");  
			List<Long> subTaskAlertStatusIds = new ArrayList<Long>();
			if(alertSubTaskStatusIdsArr != null && alertSubTaskStatusIdsArr.length() > 0){
				for (int i = 0; i < alertSubTaskStatusIdsArr.length(); i++){
					subTaskAlertStatusIds.add(Long.parseLong(alertSubTaskStatusIdsArr.getString(i)));        
				} 
			}
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			alertVOs = alertManagementSystemService.getAlertSourceWiseAlert(fromDate,toDate,stateId,paperIdList,chanelIdList,deptIdList,userId,calCntrIdList,userType,socialMediaTypeIds,alertSeverityIds,alertStatusIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
		}catch(Exception e){
			LOG.error("Exception occured in getAlertSourceWiseAlert() of AlertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String getAlertDtlsByAlertSource(){
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			} 
			Long alertCategoryId = jObj.getLong("alertCategoryId");
			String userType = jObj.getString("userType");
			//Long alertStatusId = jObj.getLong("alertStatusId");
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
			List<Long> alertStatusIds = new ArrayList<Long>();
			if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
				for (int i = 0; i < alertStatusIdsArr.length(); i++){
					alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
				} 
			}
			JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
			List<Long> alertSeverityIds = new ArrayList<Long>();
			if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
				for (int i = 0; i < alertSeverityIdsArr.length(); i++){
					alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
				} 
			}
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}
     	    alertCoreDashBoardVOs = alertManagementSystemService.getAlertDtlsByAlertSource(fromDate,toDate,stateId,paperIdList,chanelIdList,deptIdList,userId,calCntrIdList,alertCategoryId,userType,alertStatusIds,socialMediaTypeIds,alertSeverityIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
		}catch(Exception e){
			LOG.error("Exception occured in getAlertDtlsByAlertSource() of AlertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String getBellowDistrictOfficerAlertsDtls(){
		try{
			session = request.getSession();
		   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			jObj = new JSONObject(getTask());
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			String task = jObj.getString("task");
			Long statusId = jObj.getLong("statusId");
			Long desigDeptOfficerId = jObj.getLong("desigDeptOfficerId");
			Long officerId = jObj.getLong("officerId");
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			}
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
			List<Long> alertSeverityIds = new ArrayList<Long>();
			if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
				for (int i = 0; i < alertSeverityIdsArr.length(); i++){
					alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
				} 
			}
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			alertCoreDashBoardVOs = alertManagementSystemService.getBellowDistrictOfficerAlertsDtls(fromDate,toDate,paperIdList,chanelIdList,calCntrIdList,task,statusId,desigDeptOfficerId,officerId,socialMediaTypeIds,alertSeverityIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception occured in getBellowDistrictOfficerAlertsDtls() of alertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String officerWiseAlertReport(){
		try{
			session = request.getSession();
		   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			idNameVOList = alertManagementSystemService.getDeptListForGreivanceReport(userId);  
			return Action.SUCCESS;
		}catch(Exception e){
			LOG.error("Excpetion raised at getGrievanceReport Method",e);
		}
		return Action.SUCCESS;
	}
	public String getOfficerLocationWiseDepartmentOverviewAlertCount(){
		try {
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			
			jObj = new JSONObject(getTask());
			String fromDateStr = jObj.getString("fromDateStr");
			String toDateStr = jObj.getString("toDateStr");
			Long stateId = jObj.getLong("stateId");
			
			JSONArray printIdArr = null;
			JSONArray electronicIdArr = null;
			
			List<Long> printIdList = new ArrayList<Long>(0);
			List<Long> electronicIdList = new ArrayList<Long>();
			
			printIdArr = jObj.getJSONArray("printIdArr");  
			
			if(printIdArr != null && printIdArr.length() > 0){
				for (int i = 0; i < printIdArr.length(); i++){
					printIdList.add(Long.parseLong(printIdArr.getString(i)));        
				} 
			}
			
			
			electronicIdArr = jObj.getJSONArray("electronicIdArr");
			
			if(electronicIdArr != null && electronicIdArr.length() > 0){
				for (int i = 0; i < electronicIdArr.length(); i++){
					electronicIdList.add(Long.parseLong(electronicIdArr.getString(i)));        
				} 
			}
			
			Long govtDepartmentId = jObj.getLong("govtDepartmentId");
			Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");
			String sortingType = jObj.getString("sortingType");
			String order = jObj.getString("order");
			String alertType = jObj.getString("alertType");
			
			String group = jObj.getString("group");
			JSONArray subLevelsArr = jObj.getJSONArray("subLevels");  
			List<Long> sublevels = new ArrayList<Long>();
			if(subLevelsArr != null && subLevelsArr.length() > 0){
				for (int i = 0; i < subLevelsArr.length(); i++){
					sublevels.add(Long.parseLong(subLevelsArr.getString(i)));          
				}  
			}
			
			JSONArray callCenterIdArr = jObj.getJSONArray("chanelIdArr");  
			List<Long> callCenterIdList = new ArrayList<Long>();
			if(callCenterIdArr != null && callCenterIdArr.length() > 0){
				for (int i = 0; i < callCenterIdArr.length(); i++){
					callCenterIdList.add(Long.parseLong(callCenterIdArr.getString(i)));        
				} 
			}
			
			String searchType = jObj.getString("searchType");
			Long filterParentScopeId = jObj.getLong("filterParentScopeId");
			Long filterScopeValue = jObj.getLong("filterScopeValue");
			Long sourceId = jObj.getLong("source");
	        alertCoreDashBoardVOs = alertManagementSystemService.getOfficerLocationWiseDepartmentOverviewAlertCount(fromDateStr,
			toDateStr,stateId,printIdList,electronicIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,sortingType,
			order,alertType,group,callCenterIdList,sublevels,filterParentScopeId,filterScopeValue,searchType,sourceId);
	        
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverview() method, Exception - ",e);
		}
		return Action.SUCCESS;	
	}
	public String getLocationFilterClickDetails(){
		try {
			session = request.getSession();
		   	RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			jObj = new JSONObject(getTask());			
			//Long userId = jObj.getLong("userId");
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDateStr");
			
			
			JSONArray govtScopeIds = jObj.getJSONArray("govtLevelIds");  
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
			
			JSONArray statusIdsArr = jObj.getJSONArray("statusIds");  
			List<Long> statusIds = new ArrayList<Long>();
			if(statusIdsArr != null && statusIdsArr.length() > 0){
				for (int i = 0; i < statusIdsArr.length(); i++){
					statusIds.add(Long.parseLong(statusIdsArr.getString(i)));        
				} 
			} 
			
			JSONArray deptIdsArr = jObj.getJSONArray("deptIds");  
			List<Long> deptIds = new ArrayList<Long>();
			if(deptIdsArr != null && deptIdsArr.length() > 0){
				for (int i = 0; i < deptIdsArr.length(); i++){
					deptIds.add(Long.parseLong(deptIdsArr.getString(i)));        
				} 
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();  
			if(calCntrIdArr != null && calCntrIdArr.length() > 0){
				for (int i = 0; i < calCntrIdArr.length(); i++){
					calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
				}
			}
			Long priorityId = jObj.getLong("priorityId");
			Long lagStartCnt = jObj.getLong("lagStartCnt");
			Long lagEndCnt = jObj.getLong("lagEndCnt");
			String alertType = jObj.getString("alertType");
			String isMoreThanYrChkd = jObj.getString("isMoreThanYrChkd");
			String isLagChkd = jObj.getString("isLagChkd");
			Long childLevelId = jObj.getLong("childLevelId");
			
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			alertCoreDashBoardVOs = alertManagementSystemService.getLocationFilterClickDetails(userId,fromDateStr,toDateStr,govtScopeIdsList,locationValuesList,desigIdsList,priorityId,statusIds,deptIds,
					lagStartCnt,lagEndCnt,alertType,isMoreThanYrChkd,isLagChkd,paperIdList,chanelIdList,calCntrIdList,childLevelId,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
		} catch (Exception e) {
			LOG.error("Exception occured in getLocationFilterClickDetails() of alertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String getSearchAlertsDtls(){
		 try{
		      session = request.getSession();
		         RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
		      Long userId = regVo.getRegistrationID();
		      jObj = new JSONObject(getTask());
				Long alertId = jObj.getLong("alertId");
				alertDataId = alertManagementSystemService.getSearchAlertsDtls(userId,alertId);
		    }catch(Exception e){
		      e.printStackTrace();
		      LOG.error("Exception occured in getSearchAlertsDtls() of alertManagementSystemAction",e);
		    }
		    return Action.SUCCESS;
		  }
	
	public String getAlertDetailsForGrievanceReportClick(){
		try {
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			
			jObj = new JSONObject(getTask());
			String fromDateStr = jObj.getString("fromDateStr");
			String toDateStr = jObj.getString("toDateStr");
			Long stateId = jObj.getLong("stateId");
			
			JSONArray printIdArr = null;
			JSONArray electronicIdArr = null;
			
			List<Long> printIdList = new ArrayList<Long>(0);
			List<Long> electronicIdList = new ArrayList<Long>();
			
			printIdArr = jObj.getJSONArray("printIdArr");  
			
			if(printIdArr != null && printIdArr.length() > 0){
				for (int i = 0; i < printIdArr.length(); i++){
					printIdList.add(Long.parseLong(printIdArr.getString(i)));        
				} 
			}
			
			
			electronicIdArr = jObj.getJSONArray("electronicIdArr");
			
			if(electronicIdArr != null && electronicIdArr.length() > 0){
				for (int i = 0; i < electronicIdArr.length(); i++){
					electronicIdList.add(Long.parseLong(electronicIdArr.getString(i)));        
				} 
			}
			
			Long govtDepartmentId = jObj.getLong("govtDepartmentId");
			Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");
			String sortingType = jObj.getString("sortingType");
			String order = jObj.getString("order");
			String alertType = jObj.getString("alertType");
			
			String group = jObj.getString("group");
			JSONArray subLevelsArr = jObj.getJSONArray("subLevels");  
			List<Long> sublevels = new ArrayList<Long>();
			if(subLevelsArr != null && subLevelsArr.length() > 0){
				for (int i = 0; i < subLevelsArr.length(); i++){
					sublevels.add(Long.parseLong(subLevelsArr.getString(i)));          
				}  
			}
			
			JSONArray callCenterIdArr = jObj.getJSONArray("chanelIdArr");  
			List<Long> callCenterIdList = new ArrayList<Long>();
			if(callCenterIdArr != null && callCenterIdArr.length() > 0){
				for (int i = 0; i < callCenterIdArr.length(); i++){
					callCenterIdList.add(Long.parseLong(callCenterIdArr.getString(i)));        
				} 
			}
			
			String searchType = jObj.getString("searchType");
			Long filterParentScopeId = jObj.getLong("filterParentScopeId");
			Long filterScopeValue = jObj.getLong("filterScopeValue");
			Long statusId = jObj.getLong("statusId");
			Long sourseId = jObj.getLong("sourseId");
	        alertCoreDashBoardVOs = alertManagementSystemService.getAlertDetailsForGrievanceReportClick(fromDateStr,
			toDateStr,stateId,printIdList,electronicIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,sortingType,
			order,alertType,group,callCenterIdList,sublevels,filterParentScopeId,filterScopeValue,searchType,statusId,sourseId);
	        alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getAlertDetailsForGrievanceReportClick() method, Exception - ",e);
		}
		return Action.SUCCESS;	
	}
	public String getAllMainDepartments(){
		try {
			
			idnameVoList = alertManagementSystemService.getAllMainDepartments();
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getAllMainDepartments() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	
	public String changeDepartmentStatusToAlert(){
		try{
			
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");			
			if(regVo == null)
				return null;
			
			Long userId = regVo.getRegistrationID();
			
			jObj = new JSONObject(getTask());
			Long alertId = jObj.getLong("alertId");
			Long newDeptId = jObj.getLong("newDeptId");
			
			successMsg = alertManagementSystemService.changeDepartmentStatusToAlert(alertId,newDeptId,userId);
			
		} catch (Exception e) {
			LOG.error("Exception Occured in changeDepartmentStatusToAlert() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	public String getCadreGreivienceEfficiency(){
		try {
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			
			jObj = new JSONObject(getTask());
			String fromDateStr = jObj.getString("fromDateStr");
			String toDateStr = jObj.getString("toDateStr");
			Long stateId = jObj.getLong("stateId");
			
			JSONArray printIdArr = null;
			JSONArray electronicIdArr = null;
			
			List<Long> printIdList = new ArrayList<Long>(0);
			List<Long> electronicIdList = new ArrayList<Long>();
			
			Long govtDepartmentId = jObj.getLong("govtDepartmentId");
			Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");
			
			JSONArray subLevelsArr = jObj.getJSONArray("subLevels");  
			List<Long> sublevels = new ArrayList<Long>();
			if(subLevelsArr != null && subLevelsArr.length() > 0){
				for (int i = 0; i < subLevelsArr.length(); i++){
					sublevels.add(Long.parseLong(subLevelsArr.getString(i)));          
				}  
			}
			JSONArray alertstatusIdsArr = jObj.getJSONArray("alertstatusIds");
			List<Long> alertstatusIds = new ArrayList<Long>();
			for (int i = 0; i < alertstatusIdsArr.length(); i++) {
				alertstatusIds.add(Long.parseLong(alertstatusIdsArr.getString(i)));
			}
			Long sourceId = jObj.getLong("source");
			int rangeValue = jObj.getInt("rangeValue");
			alertsSummeryVOList = alertManagementSystemService.getCadreGreivienceEfficiency(fromDateStr,toDateStr,stateId,printIdList,electronicIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,null,null,sublevels,sourceId,alertstatusIds,rangeValue);
	        
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverview() method, Exception - ",e);
		}
		return Action.SUCCESS;	
	}
	public String getSubDeptsFrParentDept(){
		try{
			jObj = new JSONObject(getTask());
			
			idNameVOList = alertManagementSystemService.getSubDeptsFrParentDept(jObj.getLong("parntDeptId"));
		}catch(Exception e){
			LOG.error("Exception Occured in getSubDeptsFrParentDept() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	public String getPresentAssignedDepartmentOfAlert(){
		try {
			jObj = new JSONObject(getTask());
			idNameVOList = alertManagementSystemService.getPresentAssignedDepartmentOfAlert(jObj.getLong("alertId"));
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getPresentAssignedDepartmentOfAlert() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	/*public String saveProposalStatusDetails(){
		try{
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			
			jObj = new JSONObject(getTask());
			
			resultStatus = alertManagementSystemService.saveProposalStatusDetails(userId,jObj.getLong("alertId"),jObj.getLong("proposalCategoryId"),jObj.getString("proposalAmount"));
		}catch(Exception e){
			LOG.error("Exception Occured in saveProposalStatusDetails() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}*/
	public String updateProposalStatusFrAlert(){
		try{
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			
			jObj = new JSONObject(getTask());
			
			successMsg = alertManagementSystemService.updateProposalStatusFrAlert(userId,jObj.getLong("alertId"),jObj.getLong("proposalStatusId"),jObj.getString("comment"),jObj.getString("approvedAmount"));
			//successMsg = alertManagementSystemService.updateProposalStatusFrAlert(userId,jObj.getLong("alertId"),jObj.getLong("proposalStatusId"),jObj.getString("comment"));
		}catch(Exception e){
			LOG.error("Exception Occured in updateProposalStatusFrAlert() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	/*public String getAllProposalCategories(){
		try{
			
			idNameVOList = alertManagementSystemService.getAllProposalCategories();
			
		}catch(Exception e){
			LOG.error("Exception Occured in getAllProposalCategories() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}*/
	public String getFinancialAssistanceAlertCntCategoryWise(){
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			} 
			
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
			List<Long> alertSeverityIds = new ArrayList<Long>();
			if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
				for (int i = 0; i < alertSeverityIdsArr.length(); i++){
					alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
				} 
			}
			
			JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
			List<Long> alertStatusIds = new ArrayList<Long>();
			if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
				for (int i = 0; i < alertStatusIdsArr.length(); i++){
					alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
				} 
			}
			
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			alertVOs = alertManagementSystemService.getFinancialAssistanceAlertCntCategoryWise(fromDate,toDate,stateId,paperIdList,chanelIdList,deptIdList,userId, calCntrIdList, socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
		}catch(Exception e){
			LOG.error("Exception occured in getFinancialAssistanceAlertCntCategoryWise() of AlertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String getFinancialAssistanceAlertCntDtls(){
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			} 
			
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
			List<Long> alertStatusIds = new ArrayList<Long>();
			if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
				for (int i = 0; i < alertStatusIdsArr.length(); i++){
					alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
				} 
			}
			JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
			List<Long> alertSeverityIds = new ArrayList<Long>();
			if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
				for (int i = 0; i < alertSeverityIdsArr.length(); i++){
					alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
				} 
			}
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			Long propasalCategoryId = jObj.getLong("propasalCategoryId");
			Long propasalStatusId = jObj.getLong("propasalStatusId");
			
     	    alertCoreDashBoardVOs = alertManagementSystemService.getFinancialAssistanceAlertCntDtls(fromDate,toDate,stateId,paperIdList,chanelIdList,deptIdList,userId, calCntrIdList, socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds,propasalCategoryId,propasalStatusId);
			alertCoreDashBoardVOs = alertManagementSystemService.groupAlertsTimeWise(alertCoreDashBoardVOs);
		}catch(Exception e){
			LOG.error("Exception occured in getFinancialAssistanceAlertCntDtls() of AlertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String alertDeptmentExistInLogin(){
		try{
			session = request.getSession();
			RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();
			jObj = new JSONObject(getTask());
			
			successMsg = alertManagementSystemService.alertDeptmentExistInLogin(jObj.getLong("alertId"),userId);
		}catch(Exception e){
			LOG.error("Exception occured in alertDeptmentExistInLogin() of AlertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String getDepartmentWiseProposalAlertCnt(){
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			} 
			
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
			List<Long> alertSeverityIds = new ArrayList<Long>();
			if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
				for (int i = 0; i < alertSeverityIdsArr.length(); i++){
					alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
				} 
			}
			
			JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
			List<Long> alertStatusIds = new ArrayList<Long>();
			if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
				for (int i = 0; i < alertStatusIdsArr.length(); i++){
					alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
				} 
			}
			
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			alertVOs = alertManagementSystemService.getDepartmentWiseProposalAlertCnt(fromDate,toDate,stateId,paperIdList,chanelIdList,deptIdList,userId, calCntrIdList, socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
		}catch(Exception e){
			LOG.error("Exception occured in getDepartmentWiseProposalAlertCnt() of AlertManagementSystemAction",e);
		}
		return Action.SUCCESS;
	}
	public String getOfficerWiseAlertCntBasedOnDepartmentScopeLevel(){
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
			
			JSONArray calCntrIdArr = jObj.getJSONArray("callCenterArr");  
			List<Long> calCntrIdList = new ArrayList<Long>();
			for (int i = 0; i < calCntrIdArr.length(); i++){
				calCntrIdList.add(Long.parseLong(calCntrIdArr.getString(i)));        
			} 
			Long parentGovtDepartmentScopeId = jObj.getLong("parentGovtDepartmentScopeId");      
			Long govtDepartmentId = jObj.getLong("govtDepartmentId");
			String sortType = jObj.getString("sortType");
			String order = jObj.getString("order");
			String alertType =  jObj.getString("alertType");
			
			JSONArray subLevelsArr = jObj.getJSONArray("subLevels");  
			List<Long> sublevels = new ArrayList<Long>();
			if(subLevelsArr != null && subLevelsArr.length() > 0){
				for (int i = 0; i < subLevelsArr.length(); i++){
					sublevels.add(Long.parseLong(subLevelsArr.getString(i)));          
				}  
			}
			Long filterParentScopeId = jObj.getLong("filterParentScopeId");
			Long filterScopeValue = jObj.getLong("filterScopeValue");
			
			JSONArray socialMediaTypeIdsArr = jObj.getJSONArray("socialMediaTypeIdsArr");  
			List<Long> socialMediaTypeIds = new ArrayList<Long>();
			if(socialMediaTypeIdsArr != null && socialMediaTypeIdsArr.length() > 0){
				for (int i = 0; i < socialMediaTypeIdsArr.length(); i++){
					socialMediaTypeIds.add(Long.parseLong(socialMediaTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray alertSeverityIdsArr = jObj.getJSONArray("alertSeverityIdsArr");  
			List<Long> alertSeverityIds = new ArrayList<Long>();
			if(alertSeverityIdsArr != null && alertSeverityIdsArr.length() > 0){
				for (int i = 0; i < alertSeverityIdsArr.length(); i++){
					alertSeverityIds.add(Long.parseLong(alertSeverityIdsArr.getString(i)));        
				} 
			}
			
			JSONArray alertStatusIdsArr = jObj.getJSONArray("alertStatusIdsArr");  
			List<Long> alertStatusIds = new ArrayList<Long>();
			if(alertStatusIdsArr != null && alertStatusIdsArr.length() > 0){
				for (int i = 0; i < alertStatusIdsArr.length(); i++){
					alertStatusIds.add(Long.parseLong(alertStatusIdsArr.getString(i)));        
				} 
			}
			
			JSONArray alertSubTaskStatusIdsArr = jObj.getJSONArray("alertSubTaskStatusIdsArr");  
			List<Long> subTaskAlertStatusIds = new ArrayList<Long>();
			if(alertSubTaskStatusIdsArr != null && alertSubTaskStatusIdsArr.length() > 0){
				for (int i = 0; i < alertSubTaskStatusIdsArr.length(); i++){
					subTaskAlertStatusIds.add(Long.parseLong(alertSubTaskStatusIdsArr.getString(i)));        
				} 
			}
			JSONArray mondayGrievanceTypeIdsArr = jObj.getJSONArray("mondayGrievanceTypeIdsArr");  
			List<Long> mondayGrievanceTypeIds = new ArrayList<Long>();
			if(mondayGrievanceTypeIdsArr != null && mondayGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < mondayGrievanceTypeIdsArr.length(); i++){
					mondayGrievanceTypeIds.add(Long.parseLong(mondayGrievanceTypeIdsArr.getString(i)));        
				}
			}
			JSONArray janmabhoomiTypeIdsArr = jObj.getJSONArray("janmabhoomiTypeIdsArr");  
			List<Long> janmabhoomiTypeIds = new ArrayList<Long>();
			if(janmabhoomiTypeIdsArr != null && janmabhoomiTypeIdsArr.length() > 0){
				for (int i = 0; i < janmabhoomiTypeIdsArr.length(); i++){
					janmabhoomiTypeIds.add(Long.parseLong(janmabhoomiTypeIdsArr.getString(i)));        
				} 
			}
			JSONArray specialGrievanceTypeIdsArr = jObj.getJSONArray("specialGrievanceTypeIdsArr");  
			List<Long> specialGrievanceTypeIds = new ArrayList<Long>();
			if(specialGrievanceTypeIdsArr != null && specialGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < specialGrievanceTypeIdsArr.length(); i++){
					specialGrievanceTypeIds.add(Long.parseLong(specialGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			
			JSONArray generalGrievanceTypeIdsArr = jObj.getJSONArray("generalGrievanceTypeIdsArr");  
			List<Long> generalGrievanceTypeIds = new ArrayList<Long>();
			if(generalGrievanceTypeIdsArr != null && generalGrievanceTypeIdsArr.length() > 0){
				for (int i = 0; i < generalGrievanceTypeIdsArr.length(); i++){
					generalGrievanceTypeIds.add(Long.parseLong(generalGrievanceTypeIdsArr.getString(i)));        
				} 
			}
			alertCoreDashBoardVOs = alertManagementSystemService.getOfficerWiseAlertCntBasedOnDepartmentScopeLevel(fromDate,toDate,stateId,paperIdList,chanelIdList,userId,govtDepartmentId,parentGovtDepartmentScopeId,sortType,order,alertType,calCntrIdList,sublevels,filterParentScopeId,filterScopeValue,socialMediaTypeIds,
					alertSeverityIds,alertStatusIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
		} catch (Exception e) {
			LOG.error("Exception Occured in getOfficerWiseAlertCntBasedOnDepartmentScopeLevel() method, Exception - ",e); 
		}
		return Action.SUCCESS;	
	}
	public String uploadDocumentsForRejoinderStatus(){
		try{
			
			Long userId = 0l;
			
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVo!=null && regVo.getRegistrationID()!=null){
				userId = regVo.getRegistrationID();
			}
			
			 String imageName=null;
			 Long alertId = alertVO.getAlertId();
				
				for(int i=0;i<imageForDisplay.size();i++){
		        	  String fileType = imageForDisplayContentType.get(i).substring(imageForDisplayContentType.get(i).indexOf("/")+1, imageForDisplayContentType.get(i).length());
			        	 
			          imageName= UUID.randomUUID().toString()+"_"+imageForDisplayFileName.get(i);
			          //Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
			          
			          String staticPath = IConstants.STATIC_CONTENT_FOLDER_URL;
				  		 
				  		Calendar calendar = Calendar.getInstance();
						calendar.setTime(new Date());
						int year = calendar.get(Calendar.YEAR);
						SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_VALUE);
						String dateStr = sdf.format(new Date());
						 String yearStr = String.valueOf(year);
						 
						 String filePath = staticPath+"images/"+IConstants.ALERTS_ATTACHMENTS+"/"+year+"/"+dateStr;
				        
				        // String filePath=IConstants.STATIC_CONTENT_FOLDER_URL+"images/"+IConstants.ALERTS_ATTACHMENTS+"/"+year+"/"+dateStr;//STATIC_CONTENT_FOLDER_URL/images/IConstants.ALERTS_ATTACHMENTS/year/datestr
			        	 
			          File fileToCreate = new File(filePath,imageName);
			          FileUtils.copyFile(imageForDisplay.get(i), fileToCreate);
					  
					  StringBuilder pathBuilder = new StringBuilder();
					  pathBuilder.append("alerts_attachments/").append(yearStr).append("/").append(dateStr).append("/").append(imageName);
					  resultStatus = alertManagementSystemService.uploadDocumentsForRejoinderStatus(pathBuilder,alertId,userId);
				 }
		}catch (Exception e) {
			LOG.error("Exception Occured in reportUploadForm() method, Exception - ",e); 
		}
		return Action.SUCCESS;	
	
	}
	 public String loadUpdateMobileNoJsp(){
		    Long userId = 0l;
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVo!=null && regVo.getRegistrationID()!=null){
				userId = regVo.getRegistrationID();
			}
		    officerMobileNo = alertManagementSystemService.getOfficerMobilenNo(userId);
	    	return Action.SUCCESS;
	 }	
	 public String generateAndSendOTPSms(){
		 try{
			 jObj = new JSONObject(getTask());
			 successMsg = alertManagementSystemService.generatingAndSavingOTPDetails(jObj.getString("mobileNo"));
		 }catch(Exception e){
			 LOG.error("Exception Occured in generateAndSendOTPSms() method, Exception - ",e);
			 successMsg = "failure";
		 }
		 return Action.SUCCESS;
	 }
	 public String validateOTP(){
		 try{
			 jObj = new JSONObject(getTask());
			 String mobileNo = jObj.getString("mobileNo").trim();
			 String otp = jObj.getString("otp").trim();
			 successMsg  = alertManagementSystemService.validateOTP(mobileNo,otp);
		 }catch(Exception e){
			 successMsg = "failure";
			 LOG.error("Exception Occured in validateOTP() method, Exception - ",e);
		 }
		 return Action.SUCCESS;
	 }
	 public String updateMobileNo(){
		 try{
			 Long userId = 0l;
			  RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
				if(regVo!=null && regVo.getRegistrationID()!=null){
					userId = regVo.getRegistrationID();
				}
				jObj = new JSONObject(getTask());
				String otp = jObj.getString("otp").trim();
				String mobileNo = jObj.getString("mobileNo").trim();
				successMsg = alertManagementSystemService.updateMobileNo(userId,otp,mobileNo);
		 }catch(Exception e){
			 successMsg = "failure";
			 LOG.error("Exception Occured in updateMobileNo() method, Exception - ",e);
		 }
		 return Action.SUCCESS;
	 }
	 public String getTotalAlertDetailsForConstituencyInfo(){
		 try{
			  RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
				if(regVo!=null && regVo.getRegistrationID()!=null){
					Long userId = regVo.getRegistrationID();
				}
				jObj = new JSONObject(getTask());
				String fromDateStr = jObj.getString("fromDateStr");
				String year = jObj.getString("year");
				String toDateStr = jObj.getString("toDateStr");
				Long locationTypeId = jObj.getLong("locationTypeId");
				JSONArray alertTypeIdsStr = jObj.getJSONArray("alertTypeIdsStr");  
				List<Long> alertTypeIds = new ArrayList<Long>();
				if(alertTypeIdsStr != null && alertTypeIdsStr.length() > 0){
					for (int i = 0; i < alertTypeIdsStr.length(); i++){
						alertTypeIds.add(Long.parseLong(alertTypeIdsStr.getString(i)));        
					} 
				}
				JSONArray locationValuesArr = jObj.getJSONArray("locationValuesArr");  
				List<Long> locationValues = new ArrayList<Long>();
				if(locationValuesArr != null && locationValuesArr.length() > 0){
					for (int i = 0; i < locationValuesArr.length(); i++){
						locationValues.add(Long.parseLong(locationValuesArr.getString(i)));        
					} 
				}
				locationAlertVO = alertManagementSystemService.getTotalAlertDetailsForConstituencyInfo(fromDateStr,toDateStr,locationValues,alertTypeIds,locationTypeId,year);
				
		 }catch(Exception e){
			 successMsg = "failure";
			 LOG.error("Exception Occured in updateMobileNo() method, Exception - ",e);
		 }
		 return Action.SUCCESS;
	 }

}
