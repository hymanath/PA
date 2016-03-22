package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AppointmentBasicInfoVO;
import com.itgrids.partyanalyst.dto.AppointmentDetailsVO;
import com.itgrids.partyanalyst.dto.AppointmentVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LabelStatusVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IAppointmentService;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.IMobileService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AppointmentAction extends ActionSupport implements ServletRequestAware{
	
	private final static Logger LOG = Logger.getLogger(AppointmentAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	private JSONObject jObj;
	private IAppointmentService appointmentService; 
	private ResultStatus resultStatus;
	private AppointmentVO appointmentVO;
	private RegistrationVO regVO = new RegistrationVO();
	private List<IdNameVO> idNameVOList;
	private IMobileService mobileService;
	private List<SelectOptionVO> SelectOptionVOList;
	private ICadreCommitteeService cadreCommitteeService;
	private List<LocationWiseBoothDetailsVO> locationWiseBoothDetailsVOList;
	private List<AppointmentBasicInfoVO> appointmentUserDtlsList;
	private  List<AppointmentDetailsVO> apptDetailsList;
	private List<LabelStatusVO> labelStatusVOList; 
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public List<AppointmentBasicInfoVO> getAppointmentUserDtlsList() {
		return appointmentUserDtlsList;
	}

	public void setAppointmentUserDtlsList(
			List<AppointmentBasicInfoVO> appointmentUserDtlsList) {
		this.appointmentUserDtlsList = appointmentUserDtlsList;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
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

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public IAppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(IAppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	public AppointmentVO getAppointmentVO() {
		return appointmentVO;
	}

	public void setAppointmentVO(AppointmentVO appointmentVO) {
		this.appointmentVO = appointmentVO;
	}
	
	public RegistrationVO getRegVO() {
		return regVO;
	}

	public void setRegVO(RegistrationVO regVO) {
		this.regVO = regVO;
	}
	
	public List<IdNameVO> getIdNameVOList() {
		return idNameVOList;
	}

	public void setIdNameVOList(List<IdNameVO> idNameVOList) {
		this.idNameVOList = idNameVOList;
	}
	public IMobileService getMobileService() {
		return mobileService;
	}

	public void setMobileService(IMobileService mobileService) {
		this.mobileService = mobileService;
	}
	public List<SelectOptionVO> getSelectOptionVOList() {
		return SelectOptionVOList;
	}

	public void setSelectOptionVOList(List<SelectOptionVO> selectOptionVOList) {
		SelectOptionVOList = selectOptionVOList;
	}
	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}

	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}

	public List<LocationWiseBoothDetailsVO> getLocationWiseBoothDetailsVOList() {
		return locationWiseBoothDetailsVOList;
	}

	public void setLocationWiseBoothDetailsVOList(
			List<LocationWiseBoothDetailsVO> locationWiseBoothDetailsVOList) {
		this.locationWiseBoothDetailsVOList = locationWiseBoothDetailsVOList;
	}
    

	public List<AppointmentDetailsVO> getApptDetailsList() {
		return apptDetailsList;
	}
	public List<LabelStatusVO> getLabelStatusVOList() {
		return labelStatusVOList;
	}

	public void setApptDetailsList(List<AppointmentDetailsVO> apptDetailsList) {
		this.apptDetailsList = apptDetailsList;
	}
	public void setLabelStatusVOList(List<LabelStatusVO> labelStatusVOList) {
		this.labelStatusVOList = labelStatusVOList;
	}

	
	public String execute(){
		return Action.SUCCESS;
	}
	
	public String saveAppointment(){
		try {
			final HttpSession session = request.getSession();
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
			
			resultStatus = appointmentService.saveAppointment(appointmentVO,user.getRegistrationID());
			
		} catch (Exception e) {
			LOG.error("Exception raised at saveAppointment", e);
		}
		
		return Action.SUCCESS;
	}
	public String getAppointmentStatus(){
		try{
			LOG.info("Entered into getAppointmentStatus() method of AppointmentAction");
			idNameVOList = appointmentService.getAppointmentStatusList();
			
		}catch(Exception e){
			LOG.error("Exception raised at getAppointmentStatus() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String getAppCandidateDesigList(){
		try{
			LOG.info("Entered into getAppCandidateDesigList() method of AppointmentAction");
			idNameVOList = appointmentService.getAppCandidateDesigList();
			
		}catch(Exception e){
			LOG.error("Exception raised at getAppCandidateDesigList() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String getAppointmentPriorityList(){
		try{
			LOG.info("Entered into getAppointmentPriorityList() method of AppointmentAction");
			idNameVOList = appointmentService.getAppointmentPriorityList();
			
		}catch(Exception e){
			LOG.error("Exception raised at getAppointmentPriorityList() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String getAppmntLblStatusList(){
		try{
			LOG.info("Entered into getAppmntLblStatusList() method of AppointmentAction");
			idNameVOList = appointmentService.getAppmntLblStatusList();
			
		}catch(Exception e){
			LOG.error("Exception raised at getAppmntLblStatusList() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String createAppointmentLeble(){
		try{
			LOG.info("Entered into createAppointmentLeble() method of AppointmentAction");
			
			final HttpSession session = request.getSession();
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
			
			jObj = new JSONObject(getTask());
			String labelName = jObj.getString("labelName");
			String date = jObj.getString("date");
			resultStatus = appointmentService.createAppointmentLeble(labelName,user.getRegistrationID(),date);
			
		}catch(Exception e){
			LOG.error("Exception raised at getAppmntLblStatusList() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String getAppointmentUsersDtls(){
		try{
			LOG.info("Entered into getAppointmentUsersDtls() method of AppointmentAction");
			session = request.getSession();
			final RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if(registrationVO == null || registrationVO.getRegistrationID() == null){
				return ERROR;
			}
		
			if(registrationVO!=null){
				appointmentUserDtlsList=appointmentService.getAppointmentUsersDtlsByUserId(registrationVO.getRegistrationID());
			}
		}catch(Exception e){
		 LOG.error("Exception raised at getAppointmentUsersDtls() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String getLabelDtls(){
       try{
    	   LOG.info("Entered into getLabelDtls() method of AppointmentAction");
    	     jObj = new JSONObject(getTask());
    	     labelStatusVOList=appointmentService.getLabelDtslByDate(jObj.getString("currentDate"),jObj.getLong("apptmntUsrId"));
       }catch(Exception e){
    	   LOG.error("Exception raised at getLabelDtls() method of AppointmentAction", e);
       }
		return Action.SUCCESS;
	}

	public String getDistrictsForAppointments(){
		try {
			SelectOptionVOList = mobileService.getDistrictsList(1l);
		} catch (Exception e) {
			LOG.error("Exception raised at getDistrictsForAppointments() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getMandamMuncipalties(){
		try {
			jObj = new JSONObject(getTask());
			List<Long> tempList = new ArrayList<Long>(0);
			tempList.add(jObj.getLong("constId"));
			locationWiseBoothDetailsVOList = cadreCommitteeService.getMandalMunicCorpDetailsOfConstituencies(tempList);
		} catch (Exception e) {
			LOG.error("Exception raised at getMandamMuncipalties() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getVillageWard(){
		try {
			jObj = new JSONObject(getTask());
			idNameVOList = appointmentService.getVillageWard(jObj.getLong("tehsilId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getMandamMuncipalties() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getConstituenciesForADistrict(){
		try {
			jObj = new JSONObject(getTask());
			idNameVOList =appointmentService.getConstituenciesForADistrict(jObj.getLong("districtId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getConstituenciesForADistrict() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String deleteAppointmentLabel(){
		
		try{
			jObj = new JSONObject(getTask());
			 resultStatus=appointmentService.deleteAppointmentLabel(jObj.getLong("labelId"),jObj.getString("remarks"));
		}catch(Exception e){
			LOG.error("Exception raised at deleteAppointmentLabel() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String getTotalAppointmentStatus(){
		
		try{
			 idNameVOList=appointmentService.getTotalAppointmentStatus();
		}catch(Exception e){
			LOG.error("Exception raised at getTotalAppointmentStatus() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String getAppointmentsCandidateDetails(){
		
		try{
			LOG.info("Entered into getAppointsDetails() method of AppointmentAction");
			jObj = new JSONObject(getTask());
			appointmentUserDtlsList=appointmentService.getAppointmentsCandidateDetails(jObj.getLong("candidateDsgntnId"),jObj.getLong("appntmntPrprtyId"),jObj.getLong("appntmntSttsId"),jObj.getString("currentMonth"),jObj.getString("anyDate"));  
		}catch(Exception e){
			LOG.error("Exception raised at getAppointsDetails() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String getTotalAppointmentStatusForToday(){
	
	try{
		 idNameVOList=appointmentService.getTotalAppointmentStatusForToday();
	}catch(Exception e){
		LOG.error("Exception raised at getTotalAppointmentStatusForToday() method of AppointmentAction", e);
	}
	return Action.SUCCESS;
	}
	
	public String getAppointmentsBySearchCriteria(){
		
		try{
			jObj = new JSONObject(getTask());
			Long designationId    =   jObj.getLong("designationId");
			Long priorityId       =   jObj.getLong("priorityId");
			Long statusId         =   jObj.getLong("statusId");
			Long districtId       =   jObj.getLong("districtId");
			Long constituencyid   =   jObj.getLong("constituencyid");
			
			apptDetailsList =appointmentService.getAppointmentsBySearchCriteria(designationId,priorityId,statusId,districtId,constituencyid);
		}catch(Exception e){
			LOG.error("Exception raised at getAppointmentsBySearchCriteria() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
}
