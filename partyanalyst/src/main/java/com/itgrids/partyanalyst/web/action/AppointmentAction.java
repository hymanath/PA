package com.itgrids.partyanalyst.web.action;

import java.io.InputStream;

import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AppointmentBasicInfoVO;
import com.itgrids.partyanalyst.dto.AppointmentCandidateVO;
import com.itgrids.partyanalyst.dto.AppointmentDetailsVO;
import com.itgrids.partyanalyst.dto.AppointmentSlotsVO;
import com.itgrids.partyanalyst.dto.AppointmentUpdateStatusVO;
import com.itgrids.partyanalyst.dto.AppointmentInputVO;
import com.itgrids.partyanalyst.dto.AppointmentScheduleVO;
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
import com.itgrids.partyanalyst.service.impl.VoterAddressVO;
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
	private VoterAddressVO voterAddressVO;
	private List<AppointmentCandidateVO> candidatesList;
	private  List<AppointmentDetailsVO> apptDetailsList;
	private List<LabelStatusVO> labelStatusVOList;
	private List<AppointmentVO> appointmentVOList;
	private LabelStatusVO labelStatusVO;
	private AppointmentSlotsVO appointmentSlotsVO;
	private List<AppointmentScheduleVO> searchList;
	
	private InputStream inputStream;
	
	public AppointmentSlotsVO getAppointmentSlotsVO() {
		return appointmentSlotsVO;
	}

	public void setAppointmentSlotsVO(AppointmentSlotsVO appointmentSlotsVO) {
		this.appointmentSlotsVO = appointmentSlotsVO;
	}

	public List<AppointmentScheduleVO> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<AppointmentScheduleVO> searchList) {
		this.searchList = searchList;
	}

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
	public VoterAddressVO getVoterAddressVO() {
		return voterAddressVO;
	}

	public void setVoterAddressVO(VoterAddressVO voterAddressVO) {
		this.voterAddressVO = voterAddressVO;
	}
    
	public List<AppointmentCandidateVO> getCandidatesList() {
		return candidatesList;
	}

	public void setCandidatesList(List<AppointmentCandidateVO> candidatesList) {
		this.candidatesList = candidatesList;
	}

	public void setApptDetailsList(List<AppointmentDetailsVO> apptDetailsList) {
		this.apptDetailsList = apptDetailsList;
	}
	public void setLabelStatusVOList(List<LabelStatusVO> labelStatusVOList) {
		this.labelStatusVOList = labelStatusVOList;
	}
	public List<AppointmentVO> getAppointmentVOList() {
		return appointmentVOList;
	}

	public void setAppointmentVOList(List<AppointmentVO> appointmentVOList) {
		this.appointmentVOList = appointmentVOList;
	}	
	
	public LabelStatusVO getLabelStatusVO() {
		return labelStatusVO;
	}

	public void setLabelStatusVO(LabelStatusVO labelStatusVO) {
		this.labelStatusVO = labelStatusVO;
	}

	public String execute(){
		return Action.SUCCESS;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String saveAppointment(){
		try {
			final HttpSession session = request.getSession();
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
			
			resultStatus = appointmentService.saveAppointment(appointmentVO,user.getRegistrationID());
			
			if(resultStatus != null && resultStatus.getExceptionMsg().equalsIgnoreCase("success")){
				inputStream = new StringBufferInputStream("success");
			}else{
	        	 inputStream = new StringBufferInputStream("fail");
	        }
			
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
			/*String date = jObj.getString("date");*/
			
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			Long appointmentuserId=jObj.getLong("aptuserId");
			
			resultStatus = appointmentService.createAppointmentLeble(labelName,user.getRegistrationID(),fromDateStr,toDateStr,appointmentuserId);
			
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
	public String getAllAppointmentDetails(){
		
		try{
			LOG.info("Entered into getAllAppointmentDetails() method of AppointmentAction");
			jObj = new JSONObject(getTask());
			appointmentUserDtlsList=appointmentService.getAllAppointmentDetails(jObj.getInt("startIndex"),jObj.getInt("maxIndex"));
		}catch(Exception e){
			LOG.error("Exception raised at getAllAppointmentDetails() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String getAppointmentsBySearchCriteria(){
		
		try{
			jObj = new JSONObject(getTask());
			Long designationId      =   jObj.getLong("designationId");
			Long priorityId         =   jObj.getLong("priorityId");
			Long statusId           =   jObj.getLong("statusId");
			Long districtId         =   jObj.getLong("districtId");
			Long constituencyid     =   jObj.getLong("constituencyid");
			Long appointmentlabelId =   jObj.getLong("appointmentlabelId");
			String fromDateStr      =   jObj.getString("fromDate");
			String toDateStr        =   jObj.getString("toDate");
			
			
			apptDetailsList =appointmentService.getAppointmentsBySearchCriteria(designationId,priorityId,statusId,districtId,constituencyid,appointmentlabelId,fromDateStr,toDateStr);
		}catch(Exception e){
			LOG.error("Exception raised at getAppointmentsBySearchCriteria() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String addAppointmentstoLabel(){
		try {
			final HttpSession session = request.getSession();
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
			
			jObj = new JSONObject(getTask());
			
			List<Long> appointmentIds=new ArrayList<Long>();
			
			JSONArray appointmentsArray=jObj.getJSONArray("appointmentsArray");
			if(appointmentsArray!=null &&  appointmentsArray.length()>0){
				
				for( int i=0;i<appointmentsArray.length();i++){
					
					String appointmentString = appointmentsArray.getString(i);
					if(appointmentString!=null && appointmentString.trim().length()>0){
						appointmentIds.add(Long.valueOf(appointmentString));
					}
				}
			}
			
			Long apptLabelId =  jObj.getLong("apptLabelId");
			
			resultStatus = appointmentService.addAppointmentstoLabel(apptLabelId,appointmentIds,user.getRegistrationID());
			
		} catch (Exception e) {
			LOG.error("Exception raised at saveAppointment", e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getAppntmntSearchDetails(){
		
		try {
			jObj = new JSONObject(getTask());
			candidatesList =appointmentService.searchApptRequestedMembers(jObj.getString("searchType"),jObj.getString("searchValue"));
		} catch (Exception e) {
			LOG.error("Exception raised at getAppntmntSearchDetails() method of AppointmentAction", e);
		}
	
	return Action.SUCCESS;
	}
	public String getAppntmntAdvancedSearchDetails(){
		
		try {
			jObj = new JSONObject(getTask());
			candidatesList =appointmentService.advancedSearchApptRequestedMembers(jObj.getString("searchType"),jObj.getString("searchValue"));
		} catch (Exception e) {
			LOG.error("Exception raised at advancedSearchApptRequestedMembers() method of AppointmentAction", e);
		}
	
	return Action.SUCCESS;
	}
	
public String getCandidateWiseDetails(){
		
		try {
			jObj = new JSONObject(getTask());
			voterAddressVO =appointmentService.getMemberDetails(jObj.getString("candidateType"),jObj.getLong("id"));
		} catch (Exception e) {
			LOG.error("Exception raised at getAppntmntSearchDetails() method of AppointmentAction", e);
		}
	
	return Action.SUCCESS;
	}
	
	public String getAppointmentsOfALableForUpdate(){
		try {
			jObj = new JSONObject(getTask());
			appointmentVOList = appointmentService.getAppointmentsOfALableForUpdate(jObj.getLong("labelId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getAppointmentsOfALableForUpdate", e);
		}
		return Action.SUCCESS;
	}
	
	public String getStatusWiseCountsOfAppointments(){
		try{			
			jObj = new JSONObject(getTask());			
			labelStatusVO = appointmentService.getStatusWiseCountsOfAppointments(jObj.getLong("aptUserId"));
			
		}catch (Exception e) {
			LOG.error("Exception raised at getStatusWiseCountsOfAppointments", e);
		}
		return Action.SUCCESS;
	}
	public String  getAppointmentCreatedUsers(){
		try{
			
			idNameVOList = appointmentService.getAppointmentCreatedUsers();
			
		}catch (Exception e) {
			LOG.error("Exception raised at getAppointmentCreatedUsers", e);
		}
		return Action.SUCCESS;
	}
	public String getAppointmentSearchDetails()
	{
		try{
			jObj = new JSONObject(getTask());
			AppointmentInputVO inputVo = new AppointmentInputVO();
			inputVo.setCreatedBy(jObj.getLong("createdBy"));
			inputVo.setUserId(jObj.getLong("appointmentUserId"));
			inputVo.setName(jObj.getString("searchStr"));
			inputVo.setStrDate(jObj.getString("strDate"));
			inputVo.setEndDate(jObj.getString("endDate"));
			searchList = appointmentService.getAppointmentSearchDetails(inputVo);
			
		}
		catch (Exception e) {
			LOG.error("Exception raised at getAppointmentSearchDetails", e);
		}
		return Action.SUCCESS;
	}
	
	public String viewAppointmentsOfALable(){
		try {
			jObj = new JSONObject(getTask());
			apptDetailsList = appointmentService.viewAppointmentsOfALable(jObj.getLong("labelId"));
		} catch (Exception e) {
			LOG.error("Exception riased at viewAppointmentsOfALable", e);
		}
		return Action.SUCCESS;
	}
	public String getTimeSlotsDetails(){
		try{
			jObj = new JSONObject(getTask());
			appointmentSlotsVO = appointmentService.getTimeSlotsDetails(jObj.getLong("appointmentLabelId"));
		}catch (Exception e) {
			LOG.error("Exception raised at getTimeSlotsDetails()", e);
		}
		return Action.SUCCESS;
	}
	public String getAppointmentLabels(){
		try {			
			jObj = new JSONObject(getTask());
			idNameVOList = appointmentService.getAppointmentLabels(jObj.getLong("aptUserId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getAppointmentLabels() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getAppointmentsLabelStatus(){
		try {
			idNameVOList = appointmentService.getAppointmentsLabelStatus();
		} catch (Exception e) {
			LOG.error("Exception raised at getAppointmentsLabelStatus() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String updateAppointmentsLabelStatus(){
		
		try{
			jObj = new JSONObject(getTask());
			 resultStatus=appointmentService.updateAppointmentsLabelStatus(jObj.getLong("labelId"),jObj.getLong("labelstatusId"));
		}catch(Exception e){
			LOG.error("Exception raised at updateAppointmentsLabelStatus() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String updateMemberAppointmentsStatus(){
		
		try{
			jObj = new JSONObject(getTask());
			 resultStatus=appointmentService.updateMemberAppointmentsStatus(jObj.getLong("memberAppntId"),jObj.getLong("updateAppntStatusId"));
		}catch(Exception e){
			LOG.error("Exception raised at updateMemberAppointmentsStatus() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}

	public String setTimeSlotForAppointment(){
		try {
			
			final HttpSession session = request.getSession();
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
			
			jObj = new JSONObject(getTask());
			
			resultStatus = appointmentService.setTimeSlotForAppointment(jObj.getLong("appointmentId"),jObj.getString("date"),jObj.getString("fromTime"),jObj.getString("toTime"),user.getRegistrationID());
		} catch (Exception e) {
			LOG.error("Exception raised at setTimeSlotForAppointment", e);
		}
		return Action.SUCCESS;
	}
	public String getViewAppointmentsOfALable(){
		try {
			jObj = new JSONObject(getTask());
			apptDetailsList = appointmentService.getViewAppointmentsOfALable(jObj.getLong("labelId"));
		} catch (Exception e) {
			LOG.error("Exception riased at getViewAppointmentsOfALable", e);
		}
		return Action.SUCCESS;
	}
	
	public String updateAppointmentStatus(){
		try {
			
			final HttpSession session = request.getSession();
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
			
			jObj = new JSONObject(getTask());
			AppointmentUpdateStatusVO inputVO = new AppointmentUpdateStatusVO();
			inputVO.setAppointmentId(jObj.getLong("appointmentId"));
			inputVO.setTime(jObj.getString("time"));
			inputVO.setDate(jObj.getString("date"));
			inputVO.setIssmsChecked(jObj.getBoolean("smsCheck"));
			inputVO.setSmsText(jObj.getString("smsText"));
			inputVO.setStatusId(jObj.getLong("statusId"));
			resultStatus = appointmentService.updateAppointmentStatus(inputVO,user.getRegistrationID());
		} catch (Exception e) {
			LOG.error("Exception raised at updateAppointmentStatus", e);
		}
		return Action.SUCCESS;
	}
	
	
	public String sendSmsForAppointment(){
		try {
			
			final HttpSession session = request.getSession();
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
			
			jObj = new JSONObject(getTask());
			AppointmentUpdateStatusVO inputVO = new AppointmentUpdateStatusVO();
			inputVO.setAppointmentId(jObj.getLong("appointmentId"));
			inputVO.setSmsText(jObj.getString("smsText"));
		    resultStatus = appointmentService.sendSmsForAppointment(inputVO);
		} catch (Exception e) {
			LOG.error("Exception raised at sendSmsForAppointment", e);
		}
		return Action.SUCCESS;
	}
	
	public String updateAllAppointmentStatus(){
		try {
			
			final HttpSession session = request.getSession();
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
			
			jObj = new JSONObject(getTask());
			AppointmentUpdateStatusVO statusVo = new AppointmentUpdateStatusVO();
			//statusVo.setTime(jObj.getString("time"));
			//statusVo.setDate(jObj.getString("date"));
			//statusVo.setIssmsChecked(jObj.getBoolean("smsCheck"));
			//statusVo.setSmsText(jObj.getString("smsText"));
			statusVo.setStatusId(jObj.getLong("statusId"));
			statusVo.setAppointmentType(jObj.getString("appointmentType"));
			
			AppointmentInputVO inputVo = new AppointmentInputVO();
			inputVo.setCreatedBy(jObj.getLong("createdBy"));
			inputVo.setUserId(jObj.getLong("appointmentUserId"));
			inputVo.setName(jObj.getString("searchStr"));
			inputVo.setStrDate(jObj.getString("strDate"));
			inputVo.setEndDate(jObj.getString("endDate"));
			
			resultStatus = appointmentService.updateAllAppointmentStatusByType(statusVo,inputVo,user.getRegistrationID());
		} catch (Exception e) {
			LOG.error("Exception raised at updateAppointmentStatus", e);
		}
		return Action.SUCCESS;
	}
	
	public String deleteAppointmentsOfLabel(){
		try {
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
			
			jObj = new JSONObject(getTask());
			JSONArray jsonArray = jObj.getJSONArray("idsArr");
			
			List<Long> ids = new ArrayList<Long>(0);
			for (int i = 0; i < jsonArray.length(); i++) 
			{
				ids.add(Long.parseLong(jsonArray.get(i).toString()));
			}
			
			resultStatus = appointmentService.deleteAppointmentsOfLabel(ids,jObj.getLong("labelId"),user.getRegistrationID());
		} catch (Exception e) {
			LOG.error("Exception raised at deleteAppointmentsOfLabel",e);
		}
		return Action.SUCCESS;
	}
}
