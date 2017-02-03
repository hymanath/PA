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
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AppHistoryVO;
import com.itgrids.partyanalyst.dto.AppointmentBasicInfoVO;
import com.itgrids.partyanalyst.dto.AppointmentCandidateVO;
import com.itgrids.partyanalyst.dto.AppointmentCountVO;
import com.itgrids.partyanalyst.dto.AppointmentCountsVO;
import com.itgrids.partyanalyst.dto.AppointmentDetailsVO;
import com.itgrids.partyanalyst.dto.AppointmentFieldsVO;
import com.itgrids.partyanalyst.dto.AppointmentInputVO;
import com.itgrids.partyanalyst.dto.AppointmentLocVO;
import com.itgrids.partyanalyst.dto.AppointmentMemberInputVO;
import com.itgrids.partyanalyst.dto.AppointmentMembersDataVO;
import com.itgrids.partyanalyst.dto.AppointmentScheduleVO;
import com.itgrids.partyanalyst.dto.AppointmentSlotsVO;
import com.itgrids.partyanalyst.dto.AppointmentStatusFlowVO;
import com.itgrids.partyanalyst.dto.AppointmentStatusVO;
import com.itgrids.partyanalyst.dto.AppointmentUpdateStatusVO;
import com.itgrids.partyanalyst.dto.AppointmentVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LabelStatusVO;
import com.itgrids.partyanalyst.dto.LocationInputVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.StatusTrackingVO;
import com.itgrids.partyanalyst.service.IAppointmentReportDashBoardService;
import com.itgrids.partyanalyst.service.IAppointmentService;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.IMobileService;
import com.itgrids.partyanalyst.service.IRtcUnionService;
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
	private IRtcUnionService rtcUnionService;
	
	private InputStream inputStream;
	
	private List<BasicVO> basicvoList;
	private List<AppointmentStatusVO> appointmentStatusVOList;
	private List<AppHistoryVO> historyList;
	private AppointmentCountsVO appointmentCountsVO;
	private IAppointmentReportDashBoardService appointmentReportDashBoardService;
	private List<AppointmentLocVO> appointmentLocVOList;
	private  List<AppointmentCountVO> appointmentCountVOList;
	private  List<StatusTrackingVO> statusTrackingVOList;
	
	private static List<AppointmentStatusFlowVO> appointmentStatusFlowVOList;
	private  List<AppointmentSlotsVO> apptSlotList;
	private  AppointmentStatusFlowVO appointmentStatusFlowVO;
	private  AppointmentFieldsVO  appointmentFieldsVO;
	private List<IdNameVO> smsStatusEnabledList;
	private List<AppointmentMembersDataVO> membersList;
	
	private AppointmentScheduleVO appointmentScheduleVO;
	
	public List<AppointmentMembersDataVO> getMembersList() {
		return membersList;
	}

	public void setMembersList(List<AppointmentMembersDataVO> membersList) {
		this.membersList = membersList;
	}
	public AppointmentStatusFlowVO getAppointmentStatusFlowVO() {
		return appointmentStatusFlowVO;
	}

	public void setAppointmentStatusFlowVO(
			AppointmentStatusFlowVO appointmentStatusFlowVO) {
		this.appointmentStatusFlowVO = appointmentStatusFlowVO;
	}


	public List<StatusTrackingVO> getStatusTrackingVOList() {
		return statusTrackingVOList;
	}

	public void setStatusTrackingVOList(List<StatusTrackingVO> statusTrackingVOList) {
		this.statusTrackingVOList = statusTrackingVOList;
	}

	public List<AppointmentCountVO> getAppointmentCountVOList() {
		return appointmentCountVOList;
	}

	public void setAppointmentCountVOList(List<AppointmentCountVO> appointmentCountVOList) {
		this.appointmentCountVOList = appointmentCountVOList;
	}

	public List<AppHistoryVO> getHistoryList() {
		return historyList;
	}

	public void setHistoryList(List<AppHistoryVO> historyList) {
		this.historyList = historyList;
	}

	public List<BasicVO> getBasicvoList() {
		return basicvoList;
	}

	public void setBasicvoList(List<BasicVO> basicvoList) {
		this.basicvoList = basicvoList;
	}

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
	
	public IRtcUnionService getRtcUnionService() {
		return rtcUnionService;
	}

	public void setRtcUnionService(IRtcUnionService rtcUnionService) {
		this.rtcUnionService = rtcUnionService;
	}

	
	public AppointmentCountsVO getAppointmentCountsVO() {
		return appointmentCountsVO;
	}

	public void setAppointmentCountsVO(AppointmentCountsVO appointmentCountsVO) {
		this.appointmentCountsVO = appointmentCountsVO;
	}
	
	public static List<AppointmentStatusFlowVO> getAppointmentStatusFlowVOList() {
		return appointmentStatusFlowVOList;
	}

	public static void setAppointmentStatusFlowVOList(
			List<AppointmentStatusFlowVO> appointmentStatusFlowVOList) {
		AppointmentAction.appointmentStatusFlowVOList = appointmentStatusFlowVOList;
	}
	
	public AppointmentFieldsVO getAppointmentFieldsVO() {
		return appointmentFieldsVO;
	}

	public void setAppointmentFieldsVO(AppointmentFieldsVO appointmentFieldsVO) {
		this.appointmentFieldsVO = appointmentFieldsVO;
	}
	
	
	public List<IdNameVO> getSmsStatusEnabledList() {
		return smsStatusEnabledList;
	}
     
	public AppointmentScheduleVO getAppointmentScheduleVO() {
		return appointmentScheduleVO;
	}

	public void setAppointmentScheduleVO(AppointmentScheduleVO appointmentScheduleVO) {
		this.appointmentScheduleVO = appointmentScheduleVO;
	}

	public void setSmsStatusEnabledList(List<IdNameVO> smsStatusEnabledList) {
		this.smsStatusEnabledList = smsStatusEnabledList;
	}

	public String execute(){
		
		//appointmentStatusFlowVOList = new ArrayList<AppointmentStatusFlowVO>(0);
		
		ServletContext context = request.getSession().getServletContext();		
		
		if(appointmentStatusFlowVOList ==null){
			appointmentStatusFlowVOList = appointmentService.getApplicationContextWiseSatuses();
		}
		
		context.setAttribute("appointmentStatusList", appointmentStatusFlowVOList);
		
		smsStatusEnabledList = appointmentService.getSMSEnablingDetailsForAllStatus();

		return Action.SUCCESS;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public List<AppointmentStatusVO> getAppointmentStatusVOList() {
		return appointmentStatusVOList;
	}

	public void setAppointmentStatusVOList(
			List<AppointmentStatusVO> appointmentStatusVOList) {
		this.appointmentStatusVOList = appointmentStatusVOList;
	}
    
	public IAppointmentReportDashBoardService getAppointmentReportDashBoardService() {
		return appointmentReportDashBoardService;
	}

	public void setAppointmentReportDashBoardService(
			IAppointmentReportDashBoardService appointmentReportDashBoardService) {
		this.appointmentReportDashBoardService = appointmentReportDashBoardService;
	}
    
	public List<AppointmentLocVO> getAppointmentLocVOList() {
		return appointmentLocVOList;
	}

	public void setAppointmentLocVOList(List<AppointmentLocVO> appointmentLocVOList) {
		this.appointmentLocVOList = appointmentLocVOList;
	}
	
	public List<AppointmentSlotsVO> getApptSlotList() {
		return apptSlotList;
	}

	public void setApptSlotList(List<AppointmentSlotsVO> apptSlotList) {
		this.apptSlotList = apptSlotList;
	}

	public String saveAppointment(){
		try {
			final HttpSession session = request.getSession();
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
			
			resultStatus = appointmentService.saveAppointment(appointmentVO,user.getRegistrationID());
			if(resultStatus!=null){
				if(resultStatus.getResultCode() == 0){
					inputStream = new StringBufferInputStream("success");
				}else if(resultStatus.getResultCode() == 1){
					inputStream = new StringBufferInputStream("fail");
				}else if(resultStatus.getResultCode() == 2){
					inputStream = new StringBufferInputStream("not eligible");
				}
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
	public String getAppCandidateDesigListByType(){
		try{
			LOG.info("Entered into getAppCandidateDesigListByType() method of AppointmentAction");
			jObj = new JSONObject(getTask());
			idNameVOList = appointmentService.getAppCandidateDesigListByType(jObj.getLong("typeId"));
			
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
    	     
    	     labelStatusVOList=appointmentService.getLabelDtslByDate(jObj.getString("currentDate"),jObj.getLong("apptmntUsrId"),jObj.getLong("status"));
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
			locationWiseBoothDetailsVOList = appointmentService.getMandalMunicCorpDetailsOfConstituencies(tempList,jObj.getLong("locationScopeId"));
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
			session = request.getSession();
			final RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if(registrationVO == null || registrationVO.getRegistrationID() == null){
				return ERROR;
			}
		
			if(registrationVO!=null){
				resultStatus=appointmentService.deleteAppointmentLabel(jObj.getLong("labelId"),jObj.getString("remarks"),registrationVO.getRegistrationID());
			}
			 
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
			appointmentUserDtlsList=appointmentService.getAllAppointmentDetails(jObj.getInt("startIndex"),jObj.getInt("maxIndex"),jObj.getLong("aptUserId"));
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
			Long selUserId 			= 	jObj.getLong("selUserId");
			Long cndTypId			=	jObj.getLong("candidateTypeId");
			Long dateType			=	jObj.getLong("dateType");
			
			
			apptDetailsList =appointmentService.getAppointmentsBySearchCriteria(designationId,priorityId,statusId,districtId,constituencyid,appointmentlabelId,fromDateStr,toDateStr,selUserId,
					cndTypId,dateType,jObj.getLong("apptUserId"));
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
			candidatesList =appointmentService.searchApptRequestedMembers(jObj.getString("searchType"),jObj.getString("searchValue"),jObj.getLong("aptUserId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getAppntmntSearchDetails() method of AppointmentAction", e);
		}
	
	return Action.SUCCESS;
	}
	
	public String getsearchRequestedMembers(){
		
		try {
			jObj = new JSONObject(getTask());
			candidatesList =appointmentService.searchRequestedMembers(jObj.getString("searchType"),jObj.getString("searchValue"));
		} catch (Exception e) {
			LOG.error("Exception raised at getsearchRequestedMembers() method of AppointmentAction", e);
		}
	
	return Action.SUCCESS;
	}
	
	
	public String getAppntmntAdvancedSearchDetails(){
		
		try {
			jObj = new JSONObject(getTask());
				// Location Inputs
				LocationInputVO locationVo = new LocationInputVO();
						List<Long> states = new ArrayList<Long>();
						List<Long> dists = new ArrayList<Long>();
						List<Long> consties = new ArrayList<Long>();
						List<Long> mandals = new ArrayList<Long>();
						List<Long> panchayats = new ArrayList<Long>();
						String levelStr = jObj.getString("levelStr");
						Long districtId = jObj.getLong("districtId");
						Long constituencyId = jObj.getLong("constituencyId");
						Long mandalId = jObj.getLong("mandalId");
						Long panchayatId = jObj.getLong("panchayatId");
						Long levelId = jObj.getLong("levelId");
						Long committeeId = jObj.getLong("committeeId");
						Long stateId = jObj.getLong("stateId");
						
						JSONArray designations = jObj.getJSONArray("designations");
						locationVo.setAptUserId(jObj.getLong("aptUserId"));
						locationVo.setCommitteeId(committeeId);
						locationVo.setLevelStr(levelStr);
						locationVo.setLevelId(levelId);
						List<Long> designationIds = new ArrayList<Long>();
						if(designations != null && designations.length() > 0)
						{
							for (int i = 0; i < designations.length(); i++) {
								//String desgId = sttsArr.get(i).toString();
								//designations.add(desgId);
								designationIds.add(new Long(designations.get(i).toString()));
							}
							locationVo.setDesignationIds(designationIds);
						}
						locationVo.setStateId(stateId);
						if(levelStr.equalsIgnoreCase("state") && districtId == 0l)
						{
							
							locationVo.setLocalStateIds(states);
						}
						if(districtId > 0l)
						{
							dists.add(districtId);
							locationVo.setLocalDistrictIds(dists);
						}
						
						if(constituencyId > 0l)
						{
							consties.add(constituencyId);
							locationVo.setLocalConstituencyIds(consties);
						}
						
						if(mandalId > 0l)
						{
							mandals.add(mandalId);
							locationVo.setLocalMandalIds(mandals);
						}
						
						if(panchayatId > 0l)
						{
							panchayats.add(panchayatId);
							locationVo.setLocalPanchayatIds(panchayats);
						}
			candidatesList =appointmentService.advancedSearchApptRequestedMembers(jObj.getString("searchType"),jObj.getString("searchValue"),locationVo);
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
	
	public String getAppointmentStatusCounts(){
		try{			
			jObj = new JSONObject(getTask());			
			appointmentStatusVOList = appointmentService.getAppointmentStatusCounts(jObj.getLong("aptUserId"));
			
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
	public String getAppointmentHistoryForCandidate(){
		try{
			jObj = new JSONObject(getTask());
			historyList = appointmentService.getAppointmentHistoryForCandidate(jObj.getLong("appointmentCandidateId"),jObj.getLong("apptUserId"));
			
		}catch (Exception e) {
			LOG.error("Exception raised at getAppointmentHistoryForCandidate", e);
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
			inputVo.setType(jObj.getString("type"));
			
			JSONArray  jsonArray = jObj.getJSONArray("statusArray");
			
			List<Long> ids = new ArrayList<Long>(0);
			for (int i = 0; i < jsonArray.length(); i++) 
			{
				ids.add(Long.parseLong(jsonArray.get(i).toString()));
			}
			
			inputVo.setStatusIds(ids);
			
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
			String labelName = "";
			if(jObj.getString("callFrom") != null && jObj.getString("callFrom").equalsIgnoreCase("print"))
				labelName = jObj.getString("labelName");
			apptDetailsList = appointmentService.viewAppointmentsOfALable(jObj.getLong("labelId"),jObj.getString("callFrom"),jObj.getLong("apptuserId"),labelName);
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
			resultStatus=appointmentService.updateMemberAppointmentsStatus(jObj.getLong("apptId"),jObj.getLong("statusId"));
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
	      
	      Long timeSlotId = jObj.getLong("timeSlotId");
	      if(timeSlotId ==null){
	        timeSlotId =0l;
	      }
	      resultStatus = appointmentService.setTimeSlotForAppointment(jObj.getLong("appointmentId"),jObj.getString("date"),jObj.getString("fromTime"),jObj.getString("toTime"),user.getRegistrationID()
	          ,jObj.getString("type"),timeSlotId,jObj.getString("commentTxt"),jObj.getLong("apptUserId"));
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
			inputVO.setCommented(jObj.getString("commentTxt"));
			inputVO.setApptUserId(jObj.getLong("apptuserId"));
			inputVO.setCurrentStatusId(jObj.getLong("currentStatusId"));
			
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
			inputVO.setUserId(user.getRegistrationID());
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
	public String getDistrictsList()
	{
		try{
			idNameVOList = appointmentService.getDistrictsList();			
		}
		catch (Exception e) {
			LOG.error("Exception rised in getDistrictsList",e);
		}
		return Action.SUCCESS;
	}
	public String getConstituenciesByDistrict()
	{
		try{
			jObj = new JSONObject(getTask());
			idNameVOList = appointmentService.getConstituenciesByDistrict(jObj.getLong("districtId"));
		}
		catch (Exception e) {
			LOG.error("Exception rised in getConstituenciesByDistrict",e);
		}
		return Action.SUCCESS;
	}
	public String getMandalsByConstituency()
	{
		try{
			jObj = new JSONObject(getTask());
			idNameVOList = appointmentService.getAllMandalsByConstituencyID(jObj.getLong("constituencyId"));			
		}
		catch (Exception e) {
			LOG.error("Exception rised in getMandalsByConstituency",e);
		}
		return Action.SUCCESS;
	}
	
public String getPanchayatiesByMandalOrMuncipality(){
		
		try{
			jObj = new JSONObject(getTask());
			idNameVOList = appointmentService.getPanchayatDetailsByMandalId(jObj.getLong("mandalId"),jObj.getString("type"));
		}
		catch(Exception e){	
			LOG.error("Exception occured in getPanchayatiesByMandalOrMuncipality()",e);
		}
		
		return Action.SUCCESS;	
	}
	public String getAllCandidateTypes(){
		try{
			
			idNameVOList = appointmentService.getAllCandidateTypes();
			
		}catch (Exception e) {
			LOG.error("Exception occured in getAllCandidateTypes()",e);
		}
		return Action.SUCCESS;
	}
	
	public String getStatusCountForAppointment(){
		try{
			jObj = new JSONObject(getTask());
			Long appointmentUserId = jObj.getLong("appointmentUserId");
			idNameVOList = appointmentService.getAppointmentStatusOverview(appointmentUserId);
			
		}catch (Exception e) {
			LOG.error("Exception occured in getAppointmentStatusOverview()",e);
		}
		return Action.SUCCESS;
	}

	
	public String getAppointStatusOverviewforCandidate(){
		try{
			jObj = new JSONObject(getTask());
			Long apointmntcandidteId=jObj.getLong("appointmentCandidateId");
			idNameVOList = appointmentService.getApointmentStatusOvrviwforCandidte(apointmntcandidteId,jObj.getLong("apptUserId"));
			
		}catch(Exception e) {
			LOG.error("Exception occured in getAppointStatusOverviewforCandidate() of AppointmentAction",e);
	}
		return Action.SUCCESS;
}
	
	public String sendSms(){
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
			inputVO.setAppointmentStatusId(jObj.getLong("statusId"));
			inputVO.setUserId(user.getRegistrationID());
		    resultStatus = appointmentService.sendSms(inputVO);
		} catch (Exception e) {
			LOG.error("Exception raised at sendSmsForAppointment", e);
		}
		return Action.SUCCESS;
	}
	
	public String getCandidCountsByStates(){
		try{
			jObj = new JSONObject(getTask());
			
			String startDateString = jObj.getString("startDateString");
			String endDateString   = jObj.getString("endDateString");
			Long   appointmentUserId = jObj.getLong("appointmentUserId"); 
			
			appointmentCountsVO = appointmentService.getCandidCountsByStates(startDateString,endDateString,appointmentUserId);
			
		}catch(Exception e) {
			LOG.error("Exception occured in getCandidCountsByStates() of AppointmentAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getCandiCountsByLocations(){
		
		try {
			  jObj = new JSONObject(getTask());
			  
			  String startDateString   = jObj.getString("startDateString");
			  String endDateString     = jObj.getString("endDateString");
			  Long   appointmentUserId = jObj.getLong("appointmentUserId"); 
			  String locationType      = jObj.getString("locationType");
			  Long   stateId           = jObj.getLong("stateId");
			  
			  List<Long> candiTypeIds   = null;
			  List<String> requestedTypes =null;
			  
			  JSONArray candidateTypeArray = jObj.getJSONArray("candidateTypeArray");
			  if(candidateTypeArray != null && candidateTypeArray.length() > 0){
				    candiTypeIds = new ArrayList<Long>(); 
					for (int i = 0; i < candidateTypeArray.length(); i++) {
						candiTypeIds.add(Long.parseLong(candidateTypeArray.get(i).toString()));
					}
			   }
			  
			  JSONArray requestedTypeArray = jObj.getJSONArray("requestedTypeArray");
			  if(requestedTypeArray != null && requestedTypeArray.length() > 0){
				  requestedTypes = new ArrayList<String>(); 
					for (int i = 0; i < requestedTypeArray.length(); i++) {
						requestedTypes.add(requestedTypeArray.get(i).toString());
					}
			   }

			appointmentLocVOList=appointmentReportDashBoardService.getCandiCountsByLocations(startDateString,endDateString,appointmentUserId,locationType,stateId,candiTypeIds,requestedTypes);
		} catch (Exception e) {
			LOG.error("Exception raised at getCandiCountsByLocations() method of AppointmentAction", e);
		}
	
	return Action.SUCCESS;
	}
	public String getPublicRepresentativeWiseAppointmentCnt(){
		try{
			jObj = new JSONObject(getTask());
			Long apointmntCandateId=jObj.getLong("apontntCnditeId");
		
			appointmentCountVOList = appointmentService.getPublicRepresentativeWiseAppointmentCnt(apointmntCandateId);
			
		}catch(Exception e) {
			LOG.error("Exception occured in getPublicRepresentativeWiseAppointmentCnt() of AppointmentAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAppointmentMembersByScheduleType(){
		try{
			jObj = new JSONObject(getTask());
			AppointmentMemberInputVO vo = new AppointmentMemberInputVO();
			vo.setMemberType(jObj.getString("memberType"));
			vo.setRoleId(jObj.getLong("roleId"));
			vo.setScheduleType(jObj.getString("scheduleType"));
			vo.setCntType(jObj.getString("cntType"));
			vo.setAptUserId(jObj.getLong("aptUserId"));
			if(vo.getMemberType().equalsIgnoreCase("CommitteeMemberRole"))
				vo.setLevelId(jObj.getLong("levelId"));
			membersList = appointmentService.getAppointmentMembersByScheduleType(vo);
			
		}catch(Exception e) {
			LOG.error("Exception occured in getAppointmentMembersByScheduleType() of AppointmentAction",e);
		}
		return Action.SUCCESS;
	}
	
	
	public String getCommitteeLevelAppointments(){
		try{
			jObj = new JSONObject(getTask());
			Long appointCandidteId=jObj.getLong("appointmentCndiateId");
			appointmentCountVOList = appointmentService.getCommitteeLevelAppointments(appointCandidteId);
			
		}catch(Exception e) {
			LOG.error("Exception occured in getCommitteeLevelAppointments() of AppointmentAction",e);
		}
		return Action.SUCCESS;
	}
	public String getLevelWiseCount(){
		try{
			jObj = new JSONObject(getTask());
			  
			  Long levelId   = jObj.getLong("levelId");
			  Long aptUserId = jObj.getLong("aptUserId");
			 
			appointmentCountVOList = appointmentService.getLevelWiseCount(levelId,aptUserId);
			
		}catch(Exception e) {
			LOG.error("Exception occured in getLevelWiseCount() of AppointmentAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAppointmentStatusByUserId(){
		try{
			
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
			Long userId = user.getRegistrationID();
			idNameVOList = appointmentService.getAppointmentStatusByUserId(userId);
			
		}catch(Exception e) {
			LOG.error("Exception occured in getLevelWiseCount() of AppointmentAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getUpdatedStatusForaAppointment(){
		try{
			
			jObj = new JSONObject(getTask());
			Long userTypeId = jObj.getLong("userTypeId");
			Long currentStatusId = jObj.getLong("currentStatusId");
			
			idNameVOList = appointmentService.getUpdatedStatusForaAppointment(userTypeId,currentStatusId);
			
		}catch(Exception e) {
			LOG.error("Exception occured in getUpdatedStatusForaAppointment() of AppointmentAction",e);
		}
		return Action.SUCCESS;
	}
	public String getAppointmentStatusTrackingDetails(){
		try{
			
			jObj = new JSONObject(getTask());
			Long appntmntId = jObj.getLong("appntmntId");
			
			statusTrackingVOList = appointmentService.getAppointmentStatusTrackingDetails(appntmntId);
			
		}catch(Exception e) {
			LOG.error("Exception occured in getAppointmentStatusTrackingDetails() of AppointmentAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getTimeSlotsForADayByAppytUserId(){
		try{
			
			jObj = new JSONObject(getTask());
			Long apptUserId = jObj.getLong("appntmntId");
			String dateStr  = jObj.getString("dateStr");
			
			apptSlotList = appointmentService.getTimeSlotsForADayByAppytUserId(apptUserId,dateStr);
			
		}catch(Exception e) {
			LOG.error("Exception occured in getAppointmentStatusTrackingDetails() of AppointmentAction",e);
		}
		return Action.SUCCESS;
	}
	public String getAllScheduledApptsByDate(){
		try{
			
			jObj = new JSONObject(getTask());
			Long apptUserId = jObj.getLong("apptUserId");
			String dateStr  = jObj.getString("dateStr");
			
			searchList = appointmentService.getAllScheduledApptsByDate(apptUserId,dateStr);
			
		}catch(Exception e) {
			LOG.error("Exception occured in getAppointmentStatusTrackingDetails() of AppointmentAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAppointmentStatusCommentsTrackingDetails(){
		try{
			
			jObj = new JSONObject(getTask());
			Long appntmntId = jObj.getLong("appntmntId");
			
			statusTrackingVOList = appointmentService.getAppointmentStatusCommentsTrackingDetails(appntmntId);
			
		}catch(Exception e) {
			LOG.error("Exception occured in getAppointmentStatusCommentsTrackingDetails() of AppointmentAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String saveDesignationForOtherCandidate(){
		try{
			
			jObj = new JSONObject(getTask());
			String designation = jObj.getString("designation");
			Long candidateTypeId  = jObj.getLong("candidateTypeId");
			
			
			resultStatus = appointmentService.saveDesignationForOtherCandidate(designation,candidateTypeId);
			
		}catch (Exception e) {
			LOG.error("Exception occured in updateDesignationForOtherCandidate() of AppointmentAction",e);
		}
		return Action.SUCCESS;
	}
	
	public String getLoginUserAppointmentUserType(){
		try{
			
			session = request.getSession();
			final RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if(registrationVO == null || registrationVO.getRegistrationID() == null){
				return ERROR;
			}
		
			if(registrationVO!=null){
				appointmentStatusFlowVO= appointmentService.getLoginUserAppointmentUserType(registrationVO.getRegistrationID());
			}
		}catch(Exception e){
		 LOG.error("Exception raised at getAppointmentUsersDtls() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getTotalAppointmentDetails(){
		try{
			
			jObj = new JSONObject(getTask());
			Long appointmentId  =    jObj.getLong("appointmentId");
			
			appointmentFieldsVO = appointmentReportDashBoardService.getTotalAppointmentDetails(appointmentId);
			
		}catch(Exception e){
		 LOG.error("Exception raised at getAppointmentUsersDtls() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String updateAppointmentReason(){
		try{
			
			final HttpSession session = request.getSession();
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
			
			jObj = new JSONObject(getTask());
			Long appointmentId  =    jObj.getLong("appointmentId");
			String reason = jObj.getString("updatedReason");
			
			resultStatus = appointmentService.updateAppointmentReason(appointmentId,reason,user.getRegistrationID());
			
		}catch (Exception e) {
			LOG.error("Exception raised at updateAppointmentReason() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String getRescheduledsCounts(){
		
		try{
			jObj = new JSONObject(getTask());
			appointmentScheduleVO=appointmentService.getRescheduledAppsCounts(jObj.getLong("aptUserId"));
		}catch(Exception e){
			LOG.error("Exception raised at getRescheduledsCounts() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
				
	}
	public String getRescheduledsAppointmentsDtls(){
		
		try {
			jObj = new JSONObject(getTask());
			Long appointmentUserId = jObj.getLong("aptUserId");
			searchList = appointmentService.getRescheduledAppointmentsDetails(appointmentUserId);
		} catch (Exception e) {
			LOG.error("Exception raised at getRescheduledsAppointmentsDtls() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
	public String getRescheduledMembersApptDetails(){
		
		try{
			jObj = new JSONObject(getTask());
			Long appointmentUserId = jObj.getLong("aptUserId");
			searchList = appointmentService.getRescheduledMembersApptDetails(appointmentUserId);
		}catch (Exception e){
			LOG.error("Exception raised at getRescheduledMembersApptDetails() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
  public String rescheduledCandidate(){
	  return Action.SUCCESS;
  }
  public String getOverviewSummaryOfRescheduledCandidates(){
	  try{
			jObj = new JSONObject(getTask());
			Long appointmentUserId = jObj.getLong("aptUserId");
			searchList = appointmentService.overviewSummaryOfRescheduledCandidates(appointmentUserId);
		}catch (Exception e){
			LOG.error("Exception raised at getOverviewSummaryOfRescheduledCandidates() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
  }
  
  public String getApptRescheduledDetialsByCandidate(){
		
		try{
			jObj = new JSONObject(getTask());
			Long appointmentUserId = jObj.getLong("aptUserId");
			Long apptCandidateId = jObj.getLong("apptCandidateId");
			searchList = appointmentService.getApptRescheduledDetialsByCandidate(appointmentUserId,apptCandidateId);
		}catch (Exception e){
			LOG.error("Exception raised at getApptRescheduledDetialsByCandidate() method of AppointmentAction", e);
		}
		return Action.SUCCESS;
	}
  
  
	public String getAdvancedSearchDetails(){
		
		try {
			jObj = new JSONObject(getTask());
				// Location Inputs
				LocationInputVO locationVo = new LocationInputVO();
						List<Long> states = new ArrayList<Long>();
						List<Long> dists = new ArrayList<Long>();
						List<Long> consties = new ArrayList<Long>();
						List<Long> mandals = new ArrayList<Long>();
						List<Long> panchayats = new ArrayList<Long>();
						String levelStr = jObj.getString("levelStr");
						Long districtId = jObj.getLong("districtId");
						Long constituencyId = jObj.getLong("constituencyId");
						Long mandalId = jObj.getLong("mandalId");
						Long panchayatId = jObj.getLong("panchayatId");
						Long levelId = jObj.getLong("levelId");
						Long committeeId = jObj.getLong("committeeId");
						Long stateId = jObj.getLong("stateId");
						
						JSONArray designations = jObj.getJSONArray("designations");
						locationVo.setAptUserId(jObj.getLong("aptUserId"));
						locationVo.setCommitteeId(committeeId);
						locationVo.setLevelStr(levelStr);
						locationVo.setLevelId(levelId);
						List<Long> designationIds = new ArrayList<Long>();
						if(designations != null && designations.length() > 0)
						{
							for (int i = 0; i < designations.length(); i++) {
								//String desgId = sttsArr.get(i).toString();
								//designations.add(desgId);
								designationIds.add(new Long(designations.get(i).toString()));
							}
							locationVo.setDesignationIds(designationIds);
						}
						
						if(levelStr.equalsIgnoreCase("central")){
							locationVo.getCounrtyIds().add(1l);
						}
						
						locationVo.setStateId(stateId);
						
						if(levelStr.equalsIgnoreCase("state") && districtId == 0l)
						{
							
							locationVo.setLocalStateIds(states);
						}
						if(districtId > 0l)
						{
							dists.add(districtId);
							locationVo.setLocalDistrictIds(dists);
						}
						
						if(constituencyId > 0l)
						{
							consties.add(constituencyId);
							locationVo.setLocalConstituencyIds(consties);
						}
						
						if(mandalId > 0l)
						{
							mandals.add(mandalId);
							locationVo.setLocalMandalIds(mandals);
						}
						
						if(panchayatId > 0l)
						{
							panchayats.add(panchayatId);
							locationVo.setLocalPanchayatIds(panchayats);
						}
			candidatesList =appointmentService.advancedSearchApptRequestedMembers(jObj.getString("searchType"),jObj.getString("searchValue"),locationVo);
		} catch (Exception e) {
			LOG.error("Exception raised at getAdvancedSearchDetails() method of AppointmentAction", e);
		}
	
	return Action.SUCCESS;
	}
	
public String getNewCadreSearchBySearchType(){
		
		try {
			jObj = new JSONObject(getTask());
			String searchType = jObj.getString("searchType");
			Long searchVal = jObj.getLong("searchValue");
			String locationType = jObj.getString("locationType");
			Long locationVal = jObj.getLong("locationVal");
			String gender = jObj.getString("gender");
			
			candidatesList = appointmentService.getNewCadreSearchBySearchType(searchType,searchVal,locationType,locationVal,gender);
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNewCadreSearchBySearchType() method of AppointmentAction", e);
		}
	
	return Action.SUCCESS;
	}
}
