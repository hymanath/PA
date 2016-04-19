package com.itgrids.partyanalyst.service.impl;


import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgrids.partyanalyst.dao.IAppointmentCandidateDAO;
import com.itgrids.partyanalyst.dao.IAppointmentCandidateDesignationDAO;
import com.itgrids.partyanalyst.dao.IAppointmentCandidateRelationDAO;
import com.itgrids.partyanalyst.dao.IAppointmentCandidateTypeDAO;
import com.itgrids.partyanalyst.dao.IAppointmentDAO;
import com.itgrids.partyanalyst.dao.IAppointmentLabelDAO;
import com.itgrids.partyanalyst.dao.IAppointmentLabelStatusDAO;
import com.itgrids.partyanalyst.dao.IAppointmentManageUserDAO;
import com.itgrids.partyanalyst.dao.IAppointmentPreferableDateDAO;
import com.itgrids.partyanalyst.dao.IAppointmentPriorityDAO;
import com.itgrids.partyanalyst.dao.IAppointmentSmsHistoryDAO;
import com.itgrids.partyanalyst.dao.IAppointmentSmsSettingDAO;
import com.itgrids.partyanalyst.dao.IAppointmentStatusDAO;
import com.itgrids.partyanalyst.dao.IAppointmentTimeSlotDAO;
import com.itgrids.partyanalyst.dao.IAppointmentTrackingDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILabelAppointmentDAO;
import com.itgrids.partyanalyst.dao.ILabelAppointmentHistoryDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.hibernate.AppointmentCommentDAO;
import com.itgrids.partyanalyst.dto.AppHistoryVO;
import com.itgrids.partyanalyst.dto.AppointmentBasicInfoVO;
import com.itgrids.partyanalyst.dto.AppointmentCandidateVO;
import com.itgrids.partyanalyst.dto.AppointmentCountVO;
import com.itgrids.partyanalyst.dto.AppointmentCountsVO;
import com.itgrids.partyanalyst.dto.AppointmentDetailsVO;
import com.itgrids.partyanalyst.dto.AppointmentInputVO;
import com.itgrids.partyanalyst.dto.AppointmentScheduleVO;
import com.itgrids.partyanalyst.dto.AppointmentSlotsVO;
import com.itgrids.partyanalyst.dto.AppointmentStatusVO;
import com.itgrids.partyanalyst.dto.AppointmentUpdateStatusVO;
import com.itgrids.partyanalyst.dto.AppointmentVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LabelStatusVO;
import com.itgrids.partyanalyst.dto.LocationInputVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Appointment;
import com.itgrids.partyanalyst.model.AppointmentCandidate;
import com.itgrids.partyanalyst.model.AppointmentCandidateRelation;
import com.itgrids.partyanalyst.model.AppointmentComment;
import com.itgrids.partyanalyst.model.AppointmentLabel;
import com.itgrids.partyanalyst.model.AppointmentPreferableDate;
import com.itgrids.partyanalyst.model.AppointmentSmsHistory;
import com.itgrids.partyanalyst.model.AppointmentStatus;
import com.itgrids.partyanalyst.model.AppointmentTimeSlot;
import com.itgrids.partyanalyst.model.AppointmentTracking;
import com.itgrids.partyanalyst.model.LabelAppointment;
import com.itgrids.partyanalyst.model.LabelAppointmentHistory;
import com.itgrids.partyanalyst.model.SmsHistory;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IAppointmentService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ISmsSenderService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.LocationService;
import com.itgrids.partyanalyst.utils.ParagraphBorder;




public class AppointmentService implements IAppointmentService{
	
	private static Logger LOG = Logger.getLogger(AppointmentService.class);
	private TransactionTemplate transactionTemplate;
	private DateUtilService dateUtilService = new DateUtilService();
	private IAppointmentDAO appointmentDAO;
	private IAppointmentStatusDAO appointmentStatusDAO;
	private IAppointmentCandidateDesignationDAO candidateDesignationDAO;
	private IAppointmentPriorityDAO appointmentPriorityDAO;
	private IAppointmentLabelStatusDAO appointmentLabelStatusDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private ITehsilDAO tehsilDAO;
	private IUserAddressDAO userAddressDAO;
	private IVoterDAO voterDAO;
	private ITdpCadreDAO tdpCadreDAO;
	private IAppointmentCandidateDAO appointmentCandidateDAO;
	private IAppointmentManageUserDAO appointmentManageUserDAO;
	private IAppointmentLabelDAO appointmentLabelDAO;
	private IRegionScopesDAO regionScopesDAO;
	private IPanchayatDAO panchayatDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IStateDAO stateDAO;
	private IAppointmentPreferableDateDAO 		appointmentPreferableDateDAO;
	private IBoothPublicationVoterDAO     		boothPublicationVoterDAO;
	private RtcUnionService               		rtcUnionService;  
	private IAppointmentCandidateRelationDAO 	appointmentCandidateRelationDAO;
	private ILabelAppointmentDAO labelAppointmentDAO;
	private ILabelAppointmentHistoryDAO labelAppointmentHistoryDAO;
	private IAppointmentTimeSlotDAO appointmentTimeSlotDAO;
	private ICadreRegistrationService cadreRegistrationService;
	private RegionServiceDataImp regionServiceDataImp;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private LocationService locationService;
	private IAppointmentCandidateTypeDAO appointmentCandidateTypeDAO;
	private IAppointmentTrackingDAO appointmentTrackingDAO;
	private AppointmentCommentDAO appointmentCommentDAO;
	private IAppointmentSmsSettingDAO appointmentSmsSettingDAO;
	private IAppointmentSmsHistoryDAO appointmentSmsHistoryDAO;
	private ISmsSenderService smsSenderService;
	
	public IAppointmentSmsSettingDAO getAppointmentSmsSettingDAO() {
		return appointmentSmsSettingDAO;
	}
	public void setAppointmentSmsSettingDAO(
			IAppointmentSmsSettingDAO appointmentSmsSettingDAO) {
		this.appointmentSmsSettingDAO = appointmentSmsSettingDAO;
	}
	public IAppointmentSmsHistoryDAO getAppointmentSmsHistoryDAO() {
		return appointmentSmsHistoryDAO;
	}
	public void setAppointmentSmsHistoryDAO(
			IAppointmentSmsHistoryDAO appointmentSmsHistoryDAO) {
		this.appointmentSmsHistoryDAO = appointmentSmsHistoryDAO;
	}
	public ISmsSenderService getSmsSenderService() {
		return smsSenderService;
	}
	public void setSmsSenderService(ISmsSenderService smsSenderService) {
		this.smsSenderService = smsSenderService;
	}
	
	public AppointmentCommentDAO getAppointmentCommentDAO() {
		return appointmentCommentDAO;
	}
	public void setAppointmentCommentDAO(AppointmentCommentDAO appointmentCommentDAO) {
		this.appointmentCommentDAO = appointmentCommentDAO;
	}
	public IAppointmentTrackingDAO getAppointmentTrackingDAO() {
		return appointmentTrackingDAO;
	}
	public void setAppointmentTrackingDAO(
			IAppointmentTrackingDAO appointmentTrackingDAO) {
		this.appointmentTrackingDAO = appointmentTrackingDAO;
	}
	public LocationService getLocationService() {
		return locationService;
	}
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}
	public ICadreRegistrationService getCadreRegistrationService() {
		return cadreRegistrationService;
	}
	public void setCadreRegistrationService(ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}
	
	public IAppointmentPreferableDateDAO getAppointmentPreferableDateDAO() {
		return appointmentPreferableDateDAO;
	}
	public void setAppointmentPreferableDateDAO(
			IAppointmentPreferableDateDAO appointmentPreferableDateDAO) {
		this.appointmentPreferableDateDAO = appointmentPreferableDateDAO;
	}
	public IStateDAO getStateDAO() {
		return stateDAO;
	}
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}
	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	public IAppointmentManageUserDAO getAppointmentManageUserDAO() {
		return appointmentManageUserDAO;
	}
	public void setAppointmentManageUserDAO(
			IAppointmentManageUserDAO appointmentManageUserDAO) {
		this.appointmentManageUserDAO = appointmentManageUserDAO;
	}
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IAppointmentDAO getAppointmentDAO() {
		return appointmentDAO;
	}
	public void setAppointmentDAO(IAppointmentDAO appointmentDAO) {
		this.appointmentDAO = appointmentDAO;
	}
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}
	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}
	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}
	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}
	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}
	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	public IAppointmentCandidateDAO getAppointmentCandidateDAO() {
		return appointmentCandidateDAO;
	}
	public void setAppointmentCandidateDAO(
			IAppointmentCandidateDAO appointmentCandidateDAO) {
		this.appointmentCandidateDAO = appointmentCandidateDAO;
	}
	
	public void setAppointmentStatusDAO(IAppointmentStatusDAO appointmentStatusDAO) {
		this.appointmentStatusDAO = appointmentStatusDAO;
	}
	public IAppointmentCandidateDesignationDAO getCandidateDesignationDAO() {
		return candidateDesignationDAO;
	}
	public void setCandidateDesignationDAO(
			IAppointmentCandidateDesignationDAO candidateDesignationDAO) {
		this.candidateDesignationDAO = candidateDesignationDAO;
	}
	public IAppointmentPriorityDAO getAppointmentPriorityDAO() {
		return appointmentPriorityDAO;
	}
	public void setAppointmentPriorityDAO(
			IAppointmentPriorityDAO appointmentPriorityDAO) {
		this.appointmentPriorityDAO = appointmentPriorityDAO;
	}
	public IAppointmentLabelStatusDAO getAppointmentLabelStatusDAO() {
		return appointmentLabelStatusDAO;
	}
	public void setAppointmentLabelStatusDAO(
			IAppointmentLabelStatusDAO appointmentLabelStatusDAO) {
		this.appointmentLabelStatusDAO = appointmentLabelStatusDAO;
	}
	public IAppointmentStatusDAO getAppointmentStatusDAO() {
		return appointmentStatusDAO;
	}
	
	public IAppointmentLabelDAO getAppointmentLabelDAO() {
		return appointmentLabelDAO;
	}
	public void setAppointmentLabelDAO(IAppointmentLabelDAO appointmentLabelDAO) {
		this.appointmentLabelDAO = appointmentLabelDAO;
	}
	
	public IRegionScopesDAO getRegionScopesDAO() {
		return regionScopesDAO;
	}
	public void setRegionScopesDAO(IRegionScopesDAO regionScopesDAO) {
		this.regionScopesDAO = regionScopesDAO;
	}
	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}
	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}
	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}
	public RtcUnionService getRtcUnionService() {
		return rtcUnionService;
	}
	public void setRtcUnionService(RtcUnionService rtcUnionService) {
		this.rtcUnionService = rtcUnionService;
	}
	public IAppointmentCandidateRelationDAO getAppointmentCandidateRelationDAO() {
		return appointmentCandidateRelationDAO;
	}
	public void setAppointmentCandidateRelationDAO(
			IAppointmentCandidateRelationDAO appointmentCandidateRelationDAO) {
		this.appointmentCandidateRelationDAO = appointmentCandidateRelationDAO;
	}
	public ILabelAppointmentDAO getLabelAppointmentDAO() {
		return labelAppointmentDAO;
	}
	public void setLabelAppointmentDAO(ILabelAppointmentDAO labelAppointmentDAO) {
		this.labelAppointmentDAO = labelAppointmentDAO;
	}
	
	public ILabelAppointmentHistoryDAO getLabelAppointmentHistoryDAO() {
		return labelAppointmentHistoryDAO;
	}
	public void setLabelAppointmentHistoryDAO(
			ILabelAppointmentHistoryDAO labelAppointmentHistoryDAO) {
		this.labelAppointmentHistoryDAO = labelAppointmentHistoryDAO;
	}
	public IAppointmentTimeSlotDAO getAppointmentTimeSlotDAO() {
		return appointmentTimeSlotDAO;
	}
	public void setAppointmentTimeSlotDAO(
			IAppointmentTimeSlotDAO appointmentTimeSlotDAO) {
		this.appointmentTimeSlotDAO = appointmentTimeSlotDAO;
	}
	
	
	
	public RegionServiceDataImp getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	public void setRegionServiceDataImp(RegionServiceDataImp regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}
	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
	
	public IAppointmentCandidateTypeDAO getAppointmentCandidateTypeDAO() {
		return appointmentCandidateTypeDAO;
	}
	public void setAppointmentCandidateTypeDAO(IAppointmentCandidateTypeDAO appointmentCandidateTypeDAO) {
		this.appointmentCandidateTypeDAO = appointmentCandidateTypeDAO;
	}
	public ResultStatus saveAppointment(final AppointmentVO appointmentVO,final Long loggerUserId){
		ResultStatus rs = new ResultStatus();
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
		        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
		        	
		        	Appointment appointment = new Appointment();
		        	appointment.setAppointmentUserId(appointmentVO.getAppointmentUserId());
		        	appointment.setAppointmentPriorityId(appointmentVO.getAppointmentPriorityId());
		        	appointment.setReason(appointmentVO.getReason());
		        	appointment.setAppointmentStatusId(IConstants.APPOINTMENT_STATUS_WAITING);
		        	
		        	if(appointmentVO.getUniqueCode()!=null && !appointmentVO.getUniqueCode().trim().equalsIgnoreCase("")){
		        		String temp[] = appointmentVO.getUniqueCode().split("_");
		        		appointment.setAppointmentUserId(Long.parseLong(temp[1]));
			        	//appointment.setAppointmentUniqueId(appointmentVO.getUniqueCode());
		        	}
		        	
		        	if(appointmentVO.getAppointmentPreferableTimeType().equalsIgnoreCase("multipleDates")){
		        		appointment.setAppointmentPreferableTimeId(1l);
		        	}else if(appointmentVO.getAppointmentPreferableTimeType().equalsIgnoreCase("nextWeek")){
		        		appointment.setAppointmentPreferableTimeId(2l);
		        	}else if(appointmentVO.getAppointmentPreferableTimeType().equalsIgnoreCase("nextMonth")){
		        		appointment.setAppointmentPreferableTimeId(3l);
		        	}else if(appointmentVO.getAppointmentPreferableTimeType().equalsIgnoreCase("thisWeek")){
		        		appointment.setAppointmentPreferableTimeId(4l);
		        	} 
		        	appointment.setCreatedBy(loggerUserId);
		        	appointment.setUpdatedBy(loggerUserId);
		        	appointment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
		        	appointment.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
		        	appointment.setIsDeleted("N");
		        	appointment.setIsLabelled("N");
		        	appointment = appointmentDAO.save(appointment);
		        	
		        	if(appointmentVO.getUniqueCode()!=null && !appointmentVO.getUniqueCode().trim().equalsIgnoreCase("") && appointment != null && appointment.getAppointmentId() != null && appointment.getAppointmentId()>0l){
		        		String temp[] = appointmentVO.getUniqueCode().split("_");
		        		appointmentDAO.updateUniquesIdForAppointment(temp[0]+"_"+appointment.getAppointmentId(),appointment.getAppointmentId());
		        	}
		        	
		        	List<Date> datesList = new ArrayList<Date>(0);
		        	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		        	if(appointmentVO.getAppointmentPreferableTimeType().equalsIgnoreCase("multipleDates")){
		        		if(appointmentVO.getAppointmentDates() != null && appointmentVO.getAppointmentDates().trim() != ""){
		        			String temp[] = appointmentVO.getAppointmentDates().split(",");
		        			for(int i=0;i<temp.length;i++){
		        				try {
									datesList.add(sdf.parse(temp[i].trim()));
								} catch (ParseException e) {
									e.printStackTrace();
								}
			
		        			}
		        		} 
		        	}else if(appointmentVO.getAppointmentPreferableTimeType().equalsIgnoreCase("nextWeek")){
		        		appointment.setAppointmentPreferableTimeId(2l);
		        		datesList = dateUtilService.getDatesOfWeekAfterCurrentWeek();
		        	}else if(appointmentVO.getAppointmentPreferableTimeType().equalsIgnoreCase("nextMonth")){
		        		appointment.setAppointmentPreferableTimeId(3l);
		        		datesList = dateUtilService.getDatesOfNextMonth();
		        	}else if(appointmentVO.getAppointmentPreferableTimeType().equalsIgnoreCase("thisWeek")){
		        		appointment.setAppointmentPreferableTimeId(4l);
		        		datesList = dateUtilService.getDatesOfCurrentWeek();
		        	}
		        	
		        	if(datesList != null && datesList.size() > 0){
		        		Long order = 1l;
		        		for (Date date : datesList) {
		        			AppointmentPreferableDate appointmentPreferableDate = new AppointmentPreferableDate();
		        			
		        			appointmentPreferableDate.setAppointmentId(appointment.getAppointmentId());
		        			appointmentPreferableDate.setAppointmentDate(date);
		        			appointmentPreferableDate.setOrderNo(order);
		        			
		        			appointmentPreferableDate = appointmentPreferableDateDAO.save(appointmentPreferableDate);
		        			order++;
						}
		        	}
		        	
		        	if(appointmentVO.getBasicInfoList() != null && appointmentVO.getBasicInfoList().size() > 0){
		        		//get voterIds for voter card Numbers
		        		List<String> voterCardNumList = new ArrayList<String>(0);
		        		Map<String,Long> voterCardIdsMap = new HashMap<String, Long>(0);
		        		for (AppointmentBasicInfoVO basicInfo : appointmentVO.getBasicInfoList()) {
		        			if(basicInfo != null && basicInfo.getVoterCardNo() != null && !basicInfo.getVoterCardNo().isEmpty()){
		        				voterCardNumList.add(basicInfo.getVoterCardNo());
		        			}	
		        		}
		        		if(voterCardNumList != null && voterCardNumList.size() > 0){
		        			List<Object[]> voterIdsObjList = voterDAO.getVoterIdsByCardNos(voterCardNumList);
		        			if(voterIdsObjList != null && voterIdsObjList.size() > 0){
		        				for (Object[] objects : voterIdsObjList) {
		        					voterCardIdsMap.put(objects[1].toString(), (Long)objects[0]);
								}
		        				
		        			}
		        		}
		        		
		        		//gettdpcadre Ids for membership nums
		        		List<String> membershipNoList = new ArrayList<String>(0);
		        		Map<String,Long> cadreIdsMap = new HashMap<String, Long>(0);
		        		for (AppointmentBasicInfoVO basicInfo : appointmentVO.getBasicInfoList()) {
		        			if(basicInfo != null && basicInfo.getMembershipNum() != null && !basicInfo.getMembershipNum().isEmpty()){
		        				membershipNoList.add(basicInfo.getMembershipNum());
		        			}
		        		}
		        		if(membershipNoList != null && membershipNoList.size() > 0){
		        			List<Object[]> cadreIdsObjList = tdpCadreDAO.getTdpCadreIdForMemberShipNums(membershipNoList);
		        			if(cadreIdsObjList != null && cadreIdsObjList.size() > 0){
		        				for (Object[] objects : cadreIdsObjList) {
		        					cadreIdsMap.put(objects[1].toString(),Long.valueOf(objects[0].toString()));
								}
		        			}
		        		}
		        		
		        		
		        		for (AppointmentBasicInfoVO basicInfo : appointmentVO.getBasicInfoList()) {
		        			if(basicInfo != null){
		        				if(basicInfo.getAppointCandidateId() == null){
			        				//AppointmentCandidate appCandi = new AppointmentCandidate();
				        			//appCandi.setAppointmentId(appointment.getAppointmentId());
			        				
			        				
			        				String memberShipId = basicInfo.getMembershipNum();
			        				
			        				AppointmentCandidate appCandi =null;
			        				if(memberShipId !=null && !memberShipId.isEmpty()){		        					
			        					List<AppointmentCandidate> aptModelList = appointmentCandidateDAO.getAppointmentCandidateObjByMemship(memberShipId);	
			        					
			        					if(aptModelList !=null && aptModelList.size()>0){
			        						appCandi = aptModelList.get(0);
			        					}	
			        					
			        					if(appCandi ==null){//Saving
			        						appCandi = new AppointmentCandidate();
			        					}
			        					
			        					//saving && Updation
			        					appCandi = candidateDetailsSaving(appCandi,basicInfo,voterCardIdsMap,cadreIdsMap,loggerUserId);
			        					
			        				}else{//Other Scenario
			        					appCandi = new AppointmentCandidate();		        					
			        					appCandi = candidateDetailsSaving(appCandi,basicInfo,voterCardIdsMap,cadreIdsMap,loggerUserId);
			        				}
			        				
				        			/*appCandi.setName(basicInfo.getName());
				        			appCandi.setDesignationId(basicInfo.getDesignationId());
				        			appCandi.setMobileNo(basicInfo.getMobileNo());
				        			appCandi.setLocationScopeId(basicInfo.getLocationScopeId());
				        			if(basicInfo.getLocationScopeId().longValue() == 3l){			 		//dist
				        				appCandi.setLocationValue(basicInfo.getDistrictId());
				        			}
				        			else if(basicInfo.getLocationScopeId().longValue() == 4l){				//const
				        				appCandi.setLocationValue(basicInfo.getConstituencyId());
				        			}
				        			else if(basicInfo.getLocationScopeId().longValue() == 5l || basicInfo.getLocationScopeId().longValue() == 7l){		//tehsil || Muncipality
				        				Long id = Long.valueOf(basicInfo.getTehsilId().toString().substring(1));
				        				appCandi.setLocationValue(id);
				        			}
				        			else if(basicInfo.getLocationScopeId().longValue() == 6l || basicInfo.getLocationScopeId().longValue() == 8l){		//Village || Ward
				        				//Long id = Long.valueOf(basicInfo.getVillageId().toString().substring(1));
				        				appCandi.setLocationValue(basicInfo.getVillageId());
				        			}
				        			
				        			//user addres saving logic
				        			UserAddress userAddress = new UserAddress();
				        			if(basicInfo.getDistrictId() !=null && basicInfo.getDistrictId()>10){
				        				userAddress.setState(stateDAO.get(1l));
				        			}else if(basicInfo.getDistrictId() !=null && basicInfo.getDistrictId()<=10){
				        				userAddress.setState(stateDAO.get(36l));
				        			}
				        			
				        			if(basicInfo.getDistrictId() > 0l)
				        			userAddress.setDistrict(districtDAO.get(basicInfo.getDistrictId()));
				        			if(basicInfo.getConstituencyId() > 0l)
				        			userAddress.setConstituency(constituencyDAO.get(basicInfo.getConstituencyId()));
				        			
				        			if(basicInfo.getTehsilId() != null && basicInfo.getTehsilId() > 0l && basicInfo.getTehsilId().toString().substring(0, 1).equalsIgnoreCase("4")){
				        				userAddress.setTehsil(tehsilDAO.get(Long.valueOf(basicInfo.getTehsilId().toString().substring(1))));
				        				if(basicInfo.getVillageId() != null && basicInfo.getVillageId() > 0l)
				        					userAddress.setPanchayatId(basicInfo.getVillageId());
				        			}
				        			else if(basicInfo.getTehsilId() != null && basicInfo.getTehsilId() > 0l && basicInfo.getTehsilId().toString().substring(0, 1).equalsIgnoreCase("5")){
				        				userAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.valueOf(basicInfo.getTehsilId().toString().substring(1))));
				        				if(basicInfo.getVillageId() != null && basicInfo.getVillageId() > 0l)
				        					//userAddress.setWard(constituencyDAO.get(Long.parseLong(basicInfo.getVillageId().toString().substring(1))));
				        					userAddress.setWard(constituencyDAO.get(basicInfo.getVillageId()));
				        			}
				        			
				        			userAddress = userAddressDAO.save(userAddress);
				        			
				        			appCandi.setAddressId(userAddress.getUserAddressId());
				        			appCandi.setVoterIdCardNo(basicInfo.getVoterCardNo());
				        			appCandi.setVoterId(voterCardIdsMap.get(basicInfo.getVoterCardNo()));
				        			appCandi.setMembershipId(basicInfo.getMembershipNum());
				        			appCandi.setTdpCadreId(cadreIdsMap.get(basicInfo.getMembershipNum()));
				        			appCandi.setCreatedBy(loggerUserId);
				        			appCandi.setUpdatedBy(loggerUserId);
				        			appCandi.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				        			appCandi.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				        			appCandi.setImageURL(basicInfo.getCandiImageUrl());
				        			appCandi.setAppointmentCandidateTypeId(basicInfo.getCandidateTypeId());
				        			appCandi = appointmentCandidateDAO.save(appCandi);
				        			*/
				        			
				        			AppointmentCandidateRelation acr = new AppointmentCandidateRelation();
				        			acr.setAppointmentId(appointment.getAppointmentId());
				        			acr.setAppointmentCandidateId(appCandi.getAppointmentCandidateId());
				        			appointmentCandidateRelationDAO.save(acr);
			        			}else{
			        				AppointmentCandidateRelation acr = new AppointmentCandidateRelation();
				        			acr.setAppointmentId(appointment.getAppointmentId());
				        			acr.setAppointmentCandidateId(basicInfo.getAppointCandidateId());
				        			appointmentCandidateRelationDAO.save(acr);
			        			}
		        			}
		        			
		        		}
		        	}
		            //saveAppointmentTrackingDetails(appointment.getAppointmentId(),1l,1l,loggerUserId,"");
		        }
		    });
			rs.setExceptionMsg("success");
			rs.setResultCode(0);
		} catch (Exception e) {
			LOG.error("Exception raised at saveAppointment", e);
			rs.setExceptionMsg("failure");
			rs.setResultCode(1);
		}
		return rs;
	}
	public List<IdNameVO> getAppointmentStatusList(){
		List<IdNameVO> appointmentStatusList = new ArrayList<IdNameVO>(0);
		try{
			LOG.info("Entered into getAppointmentStatusList() method of AppointmentService");
			List<Object[]>  appStatusLst = appointmentStatusDAO.getAppointmentStatusList();
			appointmentStatusList = setDataToVO(appStatusLst);
		}catch(Exception e){
			LOG.error("Exception raised at getAppointmentStatusList() method of AppointmentService", e);
		}
		return appointmentStatusList;
	}
	//get appointmentCandidateDesignationList
	public List<IdNameVO> getAppCandidateDesigList(){
		//appCndDesigList->appointmentCandidateDesignationList
		List<IdNameVO> appCndDesigList = new ArrayList<IdNameVO>(0);
		try{
			LOG.info("Entered into getAppCandidateDesigList() method of AppointmentService");
			List<Object[]>  objList = candidateDesignationDAO.getAppCandidateDesigList();
			if(objList != null && objList.size() > 0){
				for (Object[] objects : objList) {
					IdNameVO vo = new IdNameVO();
					vo.setId((Long)objects[0]);
					vo.setName(objects[1].toString());
					vo.setOrderId((Long)objects[2]);
					appCndDesigList.add(vo);
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getAppCandidateDesigList() method of AppointmentService", e);
		}
		return appCndDesigList;
	}
	public List<IdNameVO> getAppointmentPriorityList(){
		//appPriorityList->appointmentPriorityList
		List<IdNameVO> appPriorityList = new ArrayList<IdNameVO>(0);
		try{
			LOG.info("Entered into getAppointmentPriorityList() method of AppointmentService");
			List<Object[]>  appPrLst = appointmentPriorityDAO.getAppointmentPriorityList();
			appPriorityList = setDataToVO(appPrLst);
		}catch(Exception e){
			LOG.error("Exception raised at getAppointmentPriorityList() method of AppointmentService", e);
		}
		return appPriorityList;
	}
	//getAppmntLblStatusList->getAppointmentLabelStatusList
	public List<IdNameVO> getAppmntLblStatusList(){
		//appLblStatusList->appointmentLabelStatusList
		List<IdNameVO> appLblStatusList = new ArrayList<IdNameVO>(0);
		try{
			LOG.info("Entered into getAppmntLblStatusList() method of AppointmentService");
			List<Object[]>  appLblStatusLst = appointmentLabelStatusDAO.getAppmntLblStatusList();
			appLblStatusList = setDataToVO(appLblStatusLst);
		}catch(Exception e){
			LOG.error("Exception raised at getAppmntLblStatusList() method of AppointmentService", e);
		}
		return appLblStatusList;
	}
	
	
	public List<IdNameVO> setDataToVO(List<Object[]> objList){
		List<IdNameVO> voList = new ArrayList<IdNameVO>();
		if(objList != null && objList.size() > 0){
			for (Object[] objects : objList) {
				IdNameVO vo = new IdNameVO();
				vo.setId((Long)objects[0]);
				vo.setName(objects[1].toString());
				voList.add(vo);
			}
		}
		return voList;
	}
	
	@Override
	public List<AppointmentBasicInfoVO> getAppointmentUsersDtlsByUserId(Long userId) {
		List<AppointmentBasicInfoVO> appntmntUsrDtlsLst=new ArrayList<AppointmentBasicInfoVO>(0);
		try{
			LOG.info("Entered into getAppointmentUsersDtlsByUserId() method of AppointmentService");
			
			List<Object[]> appntmtnUsrDtlsLst=appointmentManageUserDAO.getAppointmentUsersDtlsByUserId(userId);
			if(appntmtnUsrDtlsLst!=null && !appntmtnUsrDtlsLst.isEmpty()){
				for(Object[] param:appntmtnUsrDtlsLst){
					AppointmentBasicInfoVO appntmntUsrVO=new AppointmentBasicInfoVO();
					appntmntUsrVO.setAppointmentUserId((Long)param[0]);
					appntmntUsrVO.setName(param[1]!=null?param[1].toString():"");
					appntmntUsrVO.setMobileNo(param[2]!=null?param[2].toString():"");
					appntmntUsrVO.setDate(param[3]!=null?param[3].toString():"");
					appntmntUsrDtlsLst.add(appntmntUsrVO);
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised at getAppointmentUsersDtlsByUserId() method of AppointmentService", e);
		}
		return appntmntUsrDtlsLst;
	}
	public ResultStatus createAppointmentLeble(String labelName,Long insertedBy,String fromDateStr,String toDateStr,Long aptUserId){
		DateUtilService dateUtilService = new DateUtilService();
		ResultStatus resultStatus = new ResultStatus();
		try{
			LOG.info("Entered into createAppointmentLeble() method of AppointmentService");
			Date insertedDate = dateUtilService.getCurrentDateAndTime();
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			
			Date fromDate =null;
			Date toDate =null;
			if(fromDateStr !=null && toDateStr !=null){
				fromDate = format.parse(fromDateStr);
				toDate = format.parse(toDateStr);
			}
		
			AppointmentLabel appointmentLabel = new AppointmentLabel();
			appointmentLabel.setLabelName(labelName);
			appointmentLabel.setLabelFromDate(fromDate);
			appointmentLabel.setLabelToDate(toDate);			
			appointmentLabel.setAppointmentLabelStatusId(1l);
			appointmentLabel.setIsDeleted("N");
			appointmentLabel.setInsertedTime(insertedDate);
			appointmentLabel.setUpdatedTime(insertedDate);
			appointmentLabel.setInsertedBy(insertedBy);
			appointmentLabel.setUpdatedBy(insertedBy);
			appointmentLabel.setAppointmentUserId(aptUserId);
			appointmentLabelDAO.save(appointmentLabel);
			resultStatus.setResultCode(1);
			resultStatus.setMessage("Appointment Label created...");
		}catch(Exception e){
			LOG.error("Exception raised at createAppointmentLeble() method of AppointmentService", e);
		}
		return resultStatus;
	}
	@Override
	public List<LabelStatusVO> getLabelDtslByDate(String slctdDate,Long appntmntUsrId,Long statusId) {
		
		List<LabelStatusVO> finalVoList=new ArrayList<LabelStatusVO>(0);
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		try{
			LOG.info("Entered into getLabelDtslByDate() method of AppointmentService");
			Date date=null;
			if(slctdDate != null && !slctdDate.isEmpty()){
				 date=sdf.parse(slctdDate);
			}
			
			SimpleDateFormat timeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			List<AppointmentStatus> asList = appointmentStatusDAO.getAll();
			
			List<Object[]> allLablesObjList = appointmentLabelDAO.getAllLabels(date,appntmntUsrId,statusId);
			Map<Long,LabelStatusVO> tempMap = new HashMap<Long, LabelStatusVO>(0);
			if(allLablesObjList != null && allLablesObjList.size() > 0){
				for (Object[] objects : allLablesObjList) {
					LabelStatusVO vo = new LabelStatusVO();
					vo.setLabelId((Long)objects[0]);
					vo.setLabelName(objects[1].toString());
					vo.setStatusId((Long)objects[2]);
					vo.setStatus(objects[3].toString());
					vo.setDateTime(timeDate.parse(objects[4].toString()));
					tempMap.put((Long)objects[0], vo);
					
					if(asList != null && asList.size()>0){
						for ( AppointmentStatus  as: asList) {
							if(as.getAppointmentStatusId() != 3 && as.getAppointmentStatusId() != 4){
								LabelStatusVO invo = new LabelStatusVO();
								invo.setStatusId(as.getAppointmentStatusId());
								invo.setStatus(as.getStatus());
								vo.getStatusList().add(invo);
							}
						}
					}
				}
			}
			
			
			
			List<Object[]> objList = labelAppointmentDAO.getLableDetailsWithStatusWiseCounts(date,appntmntUsrId,statusId);
			Map<Long,LabelStatusVO> finalMap = new HashMap<Long, LabelStatusVO>(0);
			if(objList != null && objList.size() > 0){
				for (Object[] objects : objList) {
					if(finalMap.get((Long)objects[0]) == null){
						LabelStatusVO vo = new LabelStatusVO();
						vo.setLabelId((Long)objects[0]);
						vo.setLabelName(objects[1].toString());
						vo.setStatusId((Long)objects[2]);
						vo.setStatus(objects[3].toString());
						vo.setDateTime(timeDate.parse(objects[7].toString()));
						finalMap.put((Long)objects[0],vo);
					}
					
					LabelStatusVO labelVO = finalMap.get((Long)objects[0]);
					if(labelVO.getStatusList() == null || labelVO.getStatusList().size() <= 0){
						if(asList != null && asList.size()>0){
							for ( AppointmentStatus  as: asList) {
								if(as.getAppointmentStatusId() != 3 && as.getAppointmentStatusId() != 4){
									LabelStatusVO invo = new LabelStatusVO();
									invo.setStatusId(as.getAppointmentStatusId());
									invo.setStatus(as.getStatus());
									labelVO.getStatusList().add(invo);
								}
							}
						}
					}
					
					if((Long)objects[4]==3l || (Long)objects[4]==4l){//consider attended and not attended status as fixed status
						objects[4]=2l;
					}
					LabelStatusVO matchedStatusVO = getMatchedStatusVO(labelVO.getStatusList(),(Long)objects[4]);
					if(matchedStatusVO != null){
						matchedStatusVO.setTotalCount(matchedStatusVO.getTotalCount()+(Long)objects[6]);
					}else{
						LabelStatusVO vo = new LabelStatusVO();
						vo.setStatusId((Long)objects[4]);
						vo.setStatus(objects[5].toString());
						vo.setTotalCount(vo.getTotalCount()+(Long)objects[6]);
						labelVO.getStatusList().add(vo);
					}
					
				}
			}
			

			if(tempMap != null && tempMap.size()>0){
				for (Entry<Long, LabelStatusVO> entry : tempMap.entrySet()) {
					if(finalMap.get(entry.getKey())==null){
						finalVoList.add(tempMap.get(entry.getKey()));
					}else{
						finalVoList.add(finalMap.get(entry.getKey()));
					}
				}
			}
			
			/*if(finalMap != null && finalMap.size() > 0){
				for (Entry<Long, LabelStatusVO> entry : finalMap.entrySet()) {
					finalVoList.add(entry.getValue());
				}
			}*/
			
			
			if(finalVoList !=null && finalVoList.size()>0){
				Collections.sort(finalVoList,comparedLabelName);
			}
			
			if(finalVoList != null && finalVoList.size() > 0){
				finalVoList.get(0).setStaticStatusList(asList);
			}
		}catch(Exception e){
			LOG.error("Exception raised at getLabelDtslByDate() method of AppointmentService", e);
		}
		return finalVoList;
	}
	
	public static Comparator<LabelStatusVO> comparedLabelName = new Comparator<LabelStatusVO>()
			{

				public int compare(LabelStatusVO cstVO1, LabelStatusVO cstVO2)
				{
					 return cstVO2.getDateTime().compareTo(cstVO1.getDateTime());
				}
			};
	
	public LabelStatusVO getMatchedStatusVO(List<LabelStatusVO> voList,Long statusId){
		if(voList != null && voList.size()>0){
			for (LabelStatusVO labelStatusVO : voList) {
				if(labelStatusVO.getStatusId().equals(statusId)){
					return labelStatusVO;
				}
			}
		}
		return null;
	}
		
	public List<IdNameVO> getVillageWard(Long mandalId){
		List<IdNameVO> voList = new ArrayList<IdNameVO>(0);
		try {
			List<Long> tempList = new ArrayList<Long>(0);
			tempList.add(Long.parseLong(mandalId.toString().substring(1).trim()));
			List<Object[]> rsultObjList = null;
			if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("4")){
				rsultObjList = panchayatDAO.getAllPanchayatsInMandals(tempList);
			}else if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("5")){
				rsultObjList = constituencyDAO.getWardsInLocalElectionBody(tempList);
			}
			if(rsultObjList != null && rsultObjList.size() > 0){
				for (Object[] objects : rsultObjList) {
					IdNameVO vo = new IdNameVO();
					vo.setId((Long)objects[0]);
					vo.setName(objects[1].toString());
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getVillageWard(-) method of AppointmentService", e);
		}
		return voList;
	}
	
	public List<IdNameVO> getConstituenciesForADistrict(Long distId){
		List<IdNameVO> voList = new ArrayList<IdNameVO>(0);
		try {
			List<Object[]> returnObjList = constituencyDAO.getConstituenciesForADistrict(distId,1l);//districtId,stateId
			if(returnObjList != null && returnObjList.size() > 0){
				for (Object[] objects : returnObjList) {
					IdNameVO vo = new IdNameVO();
					vo.setId((Long)objects[0]);
					vo.setName(objects[1].toString());
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getConstituenciesForADistrict(-) method of AppointmentService", e);
		}
		return voList;
	}
	@Override
	public ResultStatus deleteAppointmentLabel(Long appointmentLabelId,String remarks) {
	   
		ResultStatus status=new ResultStatus();
		try{
			LOG.info("Entered into deleteAppointmentLabel() method of AppointmentService");
			if(appointmentLabelId!=null && appointmentLabelId>0l){
			 Integer deletedCount=appointmentLabelDAO.deleteAppointmentLabel(appointmentLabelId,remarks);	
			 
			 //update status of appts to waiting
			 List<Long> apptIDs = labelAppointmentDAO.getAppointmentsForALabel(appointmentLabelId);
			 if(apptIDs != null && apptIDs.size() > 0){
				 changeAppointmentStatus(apptIDs,IConstants.APPOINTMENT_STATUS_WAITING);
			 }
			 
			 if(deletedCount!=null && deletedCount>0){
				 status.setMessage("success");
			 }else{
				 status.setMessage("fail");
			 }
			}
		}catch(Exception e){
			LOG.error("Exception raised at deleteAppointmentLabel() method of AppointmentService", e);
		}
		return status;
	}
	@Override
	public List<AppointmentBasicInfoVO> getAppointmentsCandidateDetails(Long candidateDsgntnId, Long appntmntPrrtyId, Long appntmntSttsId,String currentMonth,String anyDate) {
		
		List<AppointmentBasicInfoVO> fnlAppntCnddtSttsList=new ArrayList<AppointmentBasicInfoVO>(0);
		try{
			LOG.info("Entered into getAppointmentsCandidateDetails() method of AppointmentService");
			if(candidateDsgntnId!=null && candidateDsgntnId>0l && appntmntPrrtyId!=null && appntmntPrrtyId>0l && appntmntSttsId!=null && appntmntSttsId>0l){
				
				  Calendar cal = Calendar.getInstance();
				  Integer crrntMnth=(Integer) (cal.get(Calendar.MONTH) + 1);
				  List<Object[]> returnAppCndidateDtlsList=null;
				if(currentMonth!=null && currentMonth.equalsIgnoreCase("currentMonth")){
				   returnAppCndidateDtlsList=appointmentCandidateDAO.getAppointmentCandidateDetails(candidateDsgntnId, appntmntPrrtyId, appntmntSttsId,crrntMnth);
				}else if(anyDate!=null && anyDate.equalsIgnoreCase("anyDate")){
					returnAppCndidateDtlsList=appointmentCandidateDAO.getAppointmentCandidateDetails(candidateDsgntnId, appntmntPrrtyId, appntmntSttsId,null);
				}
				  
			if(returnAppCndidateDtlsList!=null && !returnAppCndidateDtlsList.isEmpty()){
			
				for(Object[] param:returnAppCndidateDtlsList){
					
				 AppointmentBasicInfoVO appntmntCnddtVO=new AppointmentBasicInfoVO();		
					
					Long tdpCadreId=param[7]!=null ?(Long)param[7]:0l;
					String mobileNo=param[2]!=null ? param[2].toString():" ";
					appntmntCnddtVO.setAppointCandidateId((Long)param[0]);
					appntmntCnddtVO.setName(param[1]!=null ?param[1].toString():" ");
					appntmntCnddtVO.setMobileNo(param[2]!=null ? param[2].toString():" ");
					appntmntCnddtVO.setDesignation(param[3]!=null ? param[3].toString():"");
					appntmntCnddtVO.setConstituencyName(param[4]!=null ?param[4].toString():"");
					appntmntCnddtVO.setReason(param[5]!=null ? param[5].toString():" ");
					appntmntCnddtVO.setPriority(param[6]!=null ? param[6].toString(): "");
					
					List<Object[]> rtrnAppSttsCntList=null;
					if(tdpCadreId!=null && tdpCadreId>0l){
						if(currentMonth!=null && currentMonth.equalsIgnoreCase("currentMonth")){
						   rtrnAppSttsCntList=appointmentCandidateDAO.getAppCandidatePreviousCountDetails(tdpCadreId, null,crrntMnth);
						}else if(anyDate!=null && anyDate.equalsIgnoreCase("anyDate")){
						  rtrnAppSttsCntList=appointmentCandidateDAO.getAppCandidatePreviousCountDetails(tdpCadreId, null,null);
						}
					}else if(mobileNo!=null && !mobileNo.isEmpty()){
						if(currentMonth!=null && currentMonth.equalsIgnoreCase("currentMonth")){
							rtrnAppSttsCntList=appointmentCandidateDAO.getAppCandidatePreviousCountDetails(null, mobileNo,crrntMnth);	
						}else if(anyDate!=null && anyDate.equalsIgnoreCase("anyDate")){
							rtrnAppSttsCntList=appointmentCandidateDAO.getAppCandidatePreviousCountDetails(null, mobileNo,null);	
						}
					}
					
					List<AppointmentStatusVO> sttsCntVOList=new ArrayList<AppointmentStatusVO>(0);
					
					if(rtrnAppSttsCntList!=null && !rtrnAppSttsCntList.isEmpty()){
						for(Object[] obj:rtrnAppSttsCntList){
							AppointmentStatusVO sttsCntVO=new AppointmentStatusVO();
							 sttsCntVO.setAppointmentStatusId((Long)obj[0]);
							 sttsCntVO.setStatus(obj[1]!=null ? obj[1].toString():" ");
							 sttsCntVO.setStatusCount(obj[2]!=null? (Long)obj[2]:0l);
							 sttsCntVOList.add(sttsCntVO);
						}
					}
					appntmntCnddtVO.setAppointStatusCountList(sttsCntVOList);
					List<Object[]> rtrnSttsRqstdList=null;
					if(tdpCadreId!=null && tdpCadreId>0l){
						if(currentMonth!=null && currentMonth.equalsIgnoreCase("currentMonth")){
							rtrnSttsRqstdList=appointmentCandidateDAO.getAppCandidatePreviousRequestedDetails(tdpCadreId, null,crrntMnth);
						}else if(anyDate!=null && anyDate.equalsIgnoreCase("anyDate")){
							rtrnSttsRqstdList=appointmentCandidateDAO.getAppCandidatePreviousRequestedDetails(tdpCadreId, null,null);
						}
					}else if(mobileNo!=null && !mobileNo.isEmpty()){
						if(currentMonth!=null && currentMonth.equalsIgnoreCase("currentMonth")){
						  rtrnSttsRqstdList=appointmentCandidateDAO.getAppCandidatePreviousRequestedDetails(null,mobileNo,crrntMnth);
						}else if(anyDate!=null && anyDate.equalsIgnoreCase("anyDate")){
						  rtrnSttsRqstdList=appointmentCandidateDAO.getAppCandidatePreviousRequestedDetails(null,mobileNo,null);
						}
					}
					
					List<AppointmentStatusVO> sttsRqustdList=new ArrayList<AppointmentStatusVO>(0);
					
					if(rtrnSttsRqstdList!=null && !rtrnSttsRqstdList.isEmpty()){
						for(Object[] obj:rtrnSttsRqstdList){
							AppointmentStatusVO sttRqstVO=new AppointmentStatusVO();
							  sttRqstVO.setUpdatedTime(obj[0]!=null?obj[0].toString().split(" ")[0]:" ");
							  sttRqstVO.setStatus(obj[1]!=null?obj[1].toString():"");
							  sttsRqustdList.add(sttRqstVO);
						}  
					}
					if(sttsRqustdList!=null && !sttsRqustdList.isEmpty()){
						Object latestDate=null;
						if(tdpCadreId!=null && tdpCadreId>0l){
							if(currentMonth!=null && currentMonth.equalsIgnoreCase("currentMonth")){
								latestDate=appointmentCandidateDAO.getMaxDate(tdpCadreId,null,crrntMnth);	
							}else if(anyDate!=null && anyDate.equalsIgnoreCase("anyDate")){
								latestDate=appointmentCandidateDAO.getMaxDate(tdpCadreId,null,null);	
							}
						}else if(mobileNo!=null && !mobileNo.isEmpty()){
							if(currentMonth!=null && currentMonth.equalsIgnoreCase("currentMonth")){
								latestDate=appointmentCandidateDAO.getMaxDate(null,mobileNo,crrntMnth);
							}else if(anyDate!=null && anyDate.equalsIgnoreCase("anyDate")){
								latestDate=appointmentCandidateDAO.getMaxDate(null,mobileNo,null);
							}
						}
						appntmntCnddtVO.setDate(latestDate.toString().split(" ")[0]);
					}
				     appntmntCnddtVO.setAppointStatusRequestedList(sttsRqustdList);   
					 fnlAppntCnddtSttsList.add(appntmntCnddtVO);
				}
			}
		 }
		}catch(Exception e){
			LOG.error("Exception raised at getAppointmentsCandidateDetails() method of AppointmentService", e);
		}
		return fnlAppntCnddtSttsList;
	}
	public List<IdNameVO> getTotalAppointmentStatus(){
		List<IdNameVO> totalAppointmentStatusList = new ArrayList<IdNameVO>();
		IdNameVO idNameVO = null;
		try{
			LOG.info("Entered into getTotalAppointmentStatus() method of AppointmentService");
			List<Object[]> appointmentStatusList = appointmentStatusDAO.getAppointmentStatusList();
			//appDtlsStatusList->Appointment Details Status List
			List<Object[]> appDtlsStatusList = appointmentDAO.getTotalAppointmentStatus();
			for(Object[] appointmentStatus:appointmentStatusList){
				idNameVO = new IdNameVO();
				idNameVO.setId((Long)appointmentStatus[0]);
				idNameVO.setName(appointmentStatus[1].toString());
				if(appDtlsStatusList.size()>0){
					if(((Long)appointmentStatus[0]).equals((Long)appDtlsStatusList.get(0)[0])){
						idNameVO.setAvailableCount((Long)appDtlsStatusList.get(0)[1]);
						appDtlsStatusList.remove(0);
					}else{
						idNameVO.setAvailableCount(0l);
					}
				}
				else{
					idNameVO.setAvailableCount(0l);
				}
				totalAppointmentStatusList.add(idNameVO);
			}
		}catch(Exception e){
			LOG.error("Exception raised at getTotalAppointmentStatus() method of AppointmentService", e);
			return null;
		}
		return totalAppointmentStatusList;
	}
	public List<IdNameVO> getTotalAppointmentStatusForToday(){
		List<IdNameVO> totalAppointmentStatusList = new ArrayList<IdNameVO>();
		IdNameVO idNameVO = null;
		Date today = dateUtilService.getCurrentDateAndTime();
		try{
			LOG.info("Entered into getTotalAppointmentStatus() method of AppointmentService");
			List<Object[]> appointmentStatusList = appointmentStatusDAO.getAppointmentStatusList();
			//appDtlsStatusList->Appointment Details Status List
			List<Object[]> appDtlsStatusList = appointmentDAO.getTotalAppointmentStatusForToday(today);
			for(Object[] appointmentStatus:appointmentStatusList){
				idNameVO = new IdNameVO();
				idNameVO.setId((Long)appointmentStatus[0]);
				idNameVO.setName(appointmentStatus[1].toString());
				if(appDtlsStatusList.size()>0){
					if(((Long)appointmentStatus[0]).equals((Long)appDtlsStatusList.get(0)[0])){
						idNameVO.setAvailableCount((Long)appDtlsStatusList.get(0)[1]);
						appDtlsStatusList.remove(0);
					}else{
						idNameVO.setAvailableCount(0l);
					}
				}
				else{
					idNameVO.setAvailableCount(0l);
				}
				totalAppointmentStatusList.add(idNameVO);
			}
		}catch(Exception e){
			LOG.error("Exception raised at getTotalAppointmentStatus() method of AppointmentService", e);
			return null;
		}
		return totalAppointmentStatusList;
	}
	
	//search
	public  List<AppointmentCandidateVO> searchApptRequestedMembers(String searchType,String searchValue,Long aptUserId){
		 List<AppointmentCandidateVO>  finalList = null;
		 
		 try {
			      List<Object[]> membersList = null;
			      List<Long> tdpCadreIds = new ArrayList<Long>();
			     /* membersList = appointmentCandidateDAO.searchAppointmentRequestedMember(searchType,searchValue);
			      if(membersList != null && membersList.size()>0){
			    	  finalList = new ArrayList<AppointmentCandidateVO>(); 
			    	 for(Object[] obj:membersList){
			    		  AppointmentCandidateVO vo =new AppointmentCandidateVO();
			    		  vo.setId(obj[0]!=null?(Long)obj[0]:0l);
			    		  vo.setCandidateType("appointmentCandidate");
			    		  vo.setName(obj[1]!=null?obj[1].toString():"");
			    		  if(obj[2]!=null && (Long)obj[2]>0){
			    			  vo.setCadre(true);
			    		  }
			    		  vo.setMobileNo(obj[3]!=null?obj[3].toString():"");
			    		  vo.setDesignation(obj[4]!=null?obj[4].toString():"");
			    		  vo.setConstituency(obj[5]!=null?obj[5].toString():"");
			    		  vo.setMemberShipId(obj[6]!=null?obj[6].toString():"");
			    		  vo.setVoterCardNo(obj[7]!=null?obj[7].toString():"");
			    		  vo.setDesignationId(obj[8]!=null?(Long)obj[8]:0l);
			    		  vo.setCandidateTypeId(obj[9]!=null?(Long)obj[9]:0l);
			    		  finalList.add(vo);
			    	  }
			      }
			      else{*/
			    	  membersList = tdpCadreDAO.searchMemberByCriteria(searchType,searchValue,null);
			    	  if(membersList!=null && membersList.size()>0){
			    		  finalList = new ArrayList<AppointmentCandidateVO>(); 
			    		  for(Object[] obj: membersList){
			    			  AppointmentCandidateVO vo =new AppointmentCandidateVO();
				    		  vo.setId(obj[0]!=null?(Long)obj[0]:0l);
				    		  vo.setCandidateType("cadre");
				    		  vo.setName(obj[1]!=null?obj[1].toString():"");
				    		  vo.setCadre(true);
				    		  vo.setMobileNo(obj[2]!=null?obj[2].toString():"");
				    		  vo.setConstituency(obj[3]!=null?obj[3].toString():"");
				    		  vo.setMemberShipId(obj[4]!=null?obj[4].toString():"");
				    		  vo.setVoterCardNo(obj[5]!=null?obj[5].toString():"");
				    		  vo.setImageURL(obj[6]!=null?"images/cadre_images/"+obj[6].toString():null);
				    		  if(!tdpCadreIds.contains(vo.getId()))
				    			  tdpCadreIds.add(vo.getId());
				    		  finalList.add(vo);
			    		  }
			    	 // }
			    	  
			      }
			      
			      if(membersList==null ||  membersList.size()==0 && searchType.equalsIgnoreCase("votercardno")){
			    	  membersList = boothPublicationVoterDAO.getVoterDetailsVoterId(searchValue);
			    	  if(membersList!=null && membersList.size()>0){
			    		  finalList = new ArrayList<AppointmentCandidateVO>();
			    		  for(Object[] obj: membersList){
			    			  AppointmentCandidateVO vo =new AppointmentCandidateVO();
				    		  vo.setId(obj[0]!=null?(Long)obj[0]:0l);
				    		  vo.setCandidateType("voter");
				    		  vo.setName(obj[1]!=null?obj[1].toString():"");
				    		  vo.setMobileNo(obj[2]!=null?obj[2].toString():"");
				    		  vo.setConstituency(obj[3]!=null?obj[3].toString():"");
				    		  vo.setVoterCardNo(searchValue);
				    		  finalList.add(vo);
				    		
			    		  }
			    		  
			    	  }
			    	  
			      }
			  	List<Object[]> list = appointmentCandidateDAO.getAppointmentCandidateIdForCadreIds(tdpCadreIds,aptUserId);
				 if(list != null && list.size() > 0)
				 {
					 for(Object[] params : list)
					 {
						 AppointmentCandidateVO vo = getMatchedVO(finalList,(Long)params[0]);
						 if(vo != null)
						 {
							 vo.setAppointmentCandidateId((Long)params[1]);
						 }
					 }
				 } 
			 	
		} catch (Exception e) {
			LOG.error("Exception raised at searchApptRequestedMembers() method of AppointmentService", e);
		}
		 return finalList;
	 }
	
//Advanced Search
public  List<AppointmentCandidateVO> advancedSearchApptRequestedMembers(String searchType,String searchValue,LocationInputVO inputVo){
		 List<AppointmentCandidateVO>  finalList = new ArrayList<AppointmentCandidateVO>(); 
		 try {
			     LocationInputVO locationVo = locationService.getCandidateLocationDetails(inputVo);
			     locationVo.setStateId(inputVo.getStateId());
			 	 if(searchType.equalsIgnoreCase("CadreCommittee"))
				    {
				    if(inputVo.getLevelId() == 5l)//Mandal,Town,Div Levels 
						    {
						    	  List<Object[]> mandalMemList = null;
						    	  List<Object[]> townMemList = null;
						    	  List<Object[]> divisonMemList =null;
						    	if(locationVo.getTehsilIdsList() != null && locationVo.getTehsilIdsList().size() > 0)
									{
							    	/* mandalMemList = appointmentCandidateDAO.advancedSearchAppointmentMembersForCadreCommittee(searchType,locationVo,"mandal",inputVo); 
							    	 if(mandalMemList != null && mandalMemList.size()>0){
							    		  setDataMembers(mandalMemList,finalList);
							    	  }
							    	  else
							    	  {
								    		  mandalMemList = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,locationVo,"mandal",inputVo);  
								    		  setDataMembersForCadreRole(mandalMemList,finalList);
							    	  }*/
						    		mandalMemList = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,locationVo,"mandal",inputVo);
						    		if(mandalMemList != null && mandalMemList.size()>0)
						    		  setDataMembersForCadreRole(mandalMemList,finalList,inputVo.getAptUserId());
						    		
						    	}
						    	if(locationVo.getTownIdsList() != null && locationVo.getTownIdsList().size() > 0)
						    	{
						    		/*townMemList = appointmentCandidateDAO.advancedSearchAppointmentMembersForCadreCommittee(searchType,locationVo,"town",inputVo); 
							    	
							    	 if(townMemList != null && townMemList.size()>0){
							    		  setDataMembers(townMemList,finalList);
							    	  }
							    	  else
							    	  {
							    		  townMemList = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,locationVo,"town",inputVo);  
							    		  setDataMembersForCadreRole(townMemList,finalList);
							    	  }*/
						    		
						    		 townMemList = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,locationVo,"town",inputVo);
						    		 if(townMemList != null && townMemList.size()>0)
						    		  setDataMembersForCadreRole(townMemList,finalList,inputVo.getAptUserId());
						    		
						    	}
						    	if(locationVo.getDivisionIdsList() != null && locationVo.getDivisionIdsList().size() > 0){
						    	  /* divisonMemList = appointmentCandidateDAO.advancedSearchAppointmentMembersForCadreCommittee(searchType,locationVo,"division",inputVo); 
							    	
							    	 if(divisonMemList != null && divisonMemList.size()>0){
							    		  setDataMembers(divisonMemList,finalList);
							    	  }
							    	  else
							    	  {
							    		  divisonMemList = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,locationVo,"division",inputVo);  
							    		  setDataMembersForCadreRole(divisonMemList,finalList);
							    	  }*/
						    	 	
						    	 	divisonMemList = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,locationVo,"division",inputVo);
						    	 	if(divisonMemList != null && divisonMemList.size()>0)
					    		 	setDataMembersForCadreRole(divisonMemList,finalList,inputVo.getAptUserId());
						    	}
						    	
						   }
						    
						    else if(inputVo.getLevelId() == 6l)//Village,Ward Levels 
						    {
						    	List<Object[]> panchayatMemList = null;
						    	List<Object[]> wardMemList = null;
						    	if(locationVo.getVillageIdsList() != null && locationVo.getVillageIdsList().size() > 0){
						    		/*panchayatMemList = appointmentCandidateDAO.advancedSearchAppointmentMembersForCadreCommittee(searchType,locationVo,"panchayat",inputVo); 
							    	
							    	 if(panchayatMemList != null && panchayatMemList.size()>0){
							    		  setDataMembers(panchayatMemList,finalList);
							    	  }
							    	  else
							    	  {
							    		  panchayatMemList = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,locationVo,"panchayat",inputVo);  
							    		  setDataMembersForCadreRole(panchayatMemList,finalList);
							    	  }*/
						    			
						    		  panchayatMemList = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,locationVo,"panchayat",inputVo);
						    		  if(panchayatMemList != null && panchayatMemList.size()>0)
						    		  setDataMembersForCadreRole(panchayatMemList,finalList,inputVo.getAptUserId());
						    		
						    	}
						    	if(locationVo.getWardIdsList() != null && locationVo.getWardIdsList().size() > 0){
						    		
						    		/*wardMemList = appointmentCandidateDAO.advancedSearchAppointmentMembersForCadreCommittee(searchType,locationVo,"ward",inputVo); 
							    	
							    	 if(wardMemList != null && wardMemList.size()>0){
							    		  setDataMembers(wardMemList,finalList);
							    	  }
							    	  else
							    	  {
							    		  wardMemList = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,locationVo,"ward",inputVo);  
							    		  setDataMembersForCadreRole(wardMemList,finalList);
							    	  }*/
						    		
						    		  wardMemList = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,locationVo,"ward",inputVo);
						    		  if(wardMemList != null && wardMemList.size()>0)
						    		  setDataMembersForCadreRole(wardMemList,finalList,inputVo.getAptUserId());
						    		
						    	}
						   }
						    
						    else if(inputVo.getLevelId() == 10l || inputVo.getLevelId() == 11l)//State ,District Levels 
						    {
						    	if(locationVo.getStateIdsList() == null) 
						    	{
						    		locationVo.setStateIdsList(new ArrayList<Long>());
						    	}
						    	
						    	if(locationVo.getLevelId() == 10l)
						    	{
						    		locationVo.setStateIdsList(new ArrayList<Long>());
						    		if(inputVo.getStateId() == 0l)
						    		{
						    			locationVo.getStateIdsList().add(1l);locationVo.getStateIdsList().add(36l);	
						    		}
						    		else
						    		{
						    			locationVo.getStateIdsList().add(inputVo.getStateId());		
						    		}
						    		
						    	}
						    	/*List<Object[]> memList = appointmentCandidateDAO.advancedSearchAppointmentMembersForCadreCommittee(searchType,locationVo,"",inputVo); 
						    	
						    	 if(memList != null && memList.size()>0){
						    		  setDataMembers(memList,finalList);
						    	  }
						    	  else
						    	  {
						    		   memList  = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,locationVo,"",inputVo);  
						    		   setDataMembersForCadreRole(memList,finalList);
						    	  }*/
						    	
						    	   List<Object[]> memList  = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,locationVo,"",inputVo);
						    	   if(memList != null && memList.size()>0){
					    		   setDataMembersForCadreRole(memList,finalList,inputVo.getAptUserId());
						    	   }
						    	
						    }
						    else // All
						   {
						    	List<Object[]> memList1 = null;
						    
						    	/* memList1 = appointmentCandidateDAO.advancedSearchAppointmentMembersForCadreCommittee(searchType,null,"",inputVo); 
						    	
						    	 if(memList1 != null && memList1.size()>0){
						    		  setDataMembers(memList1,finalList);
						    	  }
						    	  else
						    	  {
						    		 memList1  = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,null,"",inputVo);  
						    		 setDataMembersForCadreRole(memList1,finalList);
						    	  }*/
						    	
						    	 memList1  = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,null,"",inputVo);
						    	 if(memList1 != null && memList1.size()>0){
					    		 setDataMembersForCadreRole(memList1,finalList,inputVo.getAptUserId());
						    	 }
						    	
						    }
				    }
				    
			      
			 	 else if(searchType.equalsIgnoreCase("name"))
			      {
			    	  List<Object[]> nameList = null;
			    	  /*nameList = appointmentCandidateDAO.searchAppointmentRequestedMember(searchType,searchValue);
			    	  if(nameList != null && nameList.size()>0){
			    		  setDataMembers(nameList,finalList);
			    	  }
			    	  else
			    	  {
			    		  nameList = tdpCadreDAO.searchMemberByCriteria(searchType,searchValue);  
			    		  setDataMembersForCadre(nameList,finalList);
			    	  }*/
			    	  if(inputVo.getLevelId() == 0l) //All
			    		{
			    	  nameList = tdpCadreDAO.searchMemberByCriteria(searchType,searchValue,null);  
			    		}
			    	  else if(inputVo.getLevelId() == 10l || inputVo.getLevelId() == 11l)//State ,District Levels 
					    {
					    	if(locationVo.getStateIdsList() == null) 
					    	{
					    		locationVo.setStateIdsList(new ArrayList<Long>());
					    	}
					    	if(locationVo.getLevelId() == 10l)
					    	{
					    		locationVo.setStateIdsList(new ArrayList<Long>());
					    		if(inputVo.getStateId() == 0l)
					    		{
					    			locationVo.getStateIdsList().add(1l);locationVo.getStateIdsList().add(36l);	
					    		}
					    		else
					    		{
					    			locationVo.getStateIdsList().add(inputVo.getStateId());		
					    		}
					    		
					    	}
					    	 nameList = tdpCadreDAO.searchMemberByCriteria(searchType,searchValue,locationVo);   
					    	 if(nameList != null && nameList.size()>0){
					    		  setDataMembersForCadre(nameList,finalList,inputVo.getAptUserId());
						    	  }
					    	}
				    	  else
				    	  {
				    		  nameList = tdpCadreDAO.searchMemberByCriteria(searchType,searchValue,locationVo);  
				    		  if(nameList != null && nameList.size()>0){
					    		  setDataMembersForCadre(nameList,finalList,inputVo.getAptUserId());
						    	  }
				    	  }
			     }
			      else if(searchType.equalsIgnoreCase("publicRepresentative"))
			      {
			    	  List<Object[]> prList = null;
			    	  
			    	  if(inputVo.getLevelId() == 0l) //All
			    		{
			    		 /* prList = appointmentCandidateDAO.advancedSearchAppointmentRequestedMembersForPublicRepresentative(searchType,null,inputVo);
					    	if(prList != null && prList.size()>0){
					    		  setDataMembers(prList,finalList);
					    	  }
					    	  else
					    	  {
					    		  prList = tdpCadreDAO.advancedSearchMemberForPublicRepresentative(searchType,null,inputVo);  
					    		  setDataMembersForCadreRole(prList,finalList);
					    	  }*/
			    		  
			    		 
			    		  prList = tdpCadreDAO.advancedSearchMemberForPublicRepresentative(searchType,null,inputVo);
			    		  if(prList != null && prList.size()>0)
			    		  {
			    		  setDataMembersForCadreRole(prList,finalList,inputVo.getAptUserId());
			    		  }
			    		}
			    		else
			    		{
			    			 /* prList = appointmentCandidateDAO.advancedSearchAppointmentRequestedMembersForPublicRepresentative(searchType,locationVo,inputVo);
						    	if(prList != null && prList.size()>0){
						    		  setDataMembers(prList,finalList);
						    	  }
						    	  else
						    	  {
						    		  prList = tdpCadreDAO.advancedSearchMemberForPublicRepresentative(searchType,locationVo,inputVo);  
						    		  setDataMembersForCadreRole(prList,finalList);
						    	  }*/
			    			
			    			  prList = tdpCadreDAO.advancedSearchMemberForPublicRepresentative(searchType,locationVo,inputVo);
			    			  if(prList != null && prList.size()>0){
				    		  setDataMembersForCadreRole(prList,finalList,inputVo.getAptUserId());
			    			}
			    		}
			      }
		 		}
		 catch (Exception e) {
		LOG.error("Exception raised at advancedSearchApptRequestedMembers() method of AppointmentService", e);
		 }
		 
		 
	 return finalList;
 }
public void setDataMembers(List<Object[]> membersList, List<AppointmentCandidateVO>  finalList,Long aptUserId)
{
  if(membersList != null && membersList.size()>0){
	  List<Long> tdpCadreIds = new ArrayList<Long>();
  	  for(Object[] obj:membersList){
  		  AppointmentCandidateVO vo =new AppointmentCandidateVO();
  		  vo.setId(obj[0]!=null?(Long)obj[0]:0l);
  		  vo.setCandidateType("appointmentCandidate");
  		  vo.setName(obj[1]!=null?obj[1].toString():"");
  		  if(obj[2]!=null && (Long)obj[2]>0){
  			  vo.setCadre(true);
  		  }
  		  vo.setMobileNo(obj[3]!=null?obj[3].toString():"");
  		  vo.setDesignation(obj[4]!=null?obj[4].toString():"");
  		  vo.setConstituency(obj[5]!=null?obj[5].toString():"");
  		  vo.setMemberShipId(obj[6]!=null?obj[6].toString():"");
  		  vo.setVoterCardNo(obj[7]!=null?obj[7].toString():"");
  		  vo.setDesignationId(obj[8]!=null?(Long)obj[8]:0l);
  		  vo.setImageURL(obj[10]!=null?obj[10].toString():"");
  		  finalList.add(vo);
  		 if(!tdpCadreIds.contains(vo.getId()))
  	  		  tdpCadreIds.add(vo.getId());
  	  }
  	List<Object[]> list = appointmentCandidateDAO.getAppointmentCandidateIdForCadreIds(tdpCadreIds,aptUserId);
	 if(list != null && list.size() > 0)
	 {
		 for(Object[] params : list)
		 {
			 AppointmentCandidateVO vo = getMatchedVO(finalList,(Long)params[0]);
			 if(vo != null)
			 {
				 vo.setAppointmentCandidateId((Long)params[1]);
			 }
		 }
	 }
    }
}


public void setDataMembersForCadreRole(List<Object[]> membersList, List<AppointmentCandidateVO>  finalList,Long aptUserId)
{
	List<Long> tdpCadreIds = new ArrayList<Long>();
	if(membersList!=null && membersList.size()>0){
		 for(Object[] obj: membersList){
			  AppointmentCandidateVO vo =new AppointmentCandidateVO();
  		  vo.setId(obj[0]!=null?(Long)obj[0]:0l);
  		  vo.setCandidateType("cadre");
  		  vo.setName(obj[1]!=null?obj[1].toString():"");
  		  vo.setCadre(true);
  		  vo.setMobileNo(obj[2]!=null?obj[2].toString():"");
  		  vo.setConstituency(obj[3]!=null?obj[3].toString():"");
  		  vo.setMemberShipId(obj[4]!=null?obj[4].toString():"");
  		  vo.setVoterCardNo(obj[5]!=null?obj[5].toString():"");
  		  vo.setImageURL(obj[7]!=null?"images/cadre_images/"+obj[7].toString():null);
  		  vo.setDesignation(obj[6]!=null?obj[6].toString():"");
  		  finalList.add(vo);
  		  if(!tdpCadreIds.contains(vo.getId()))
  		  tdpCadreIds.add(vo.getId());
		  }
		 List<Object[]> list = appointmentCandidateDAO.getAppointmentCandidateIdForCadreIds(tdpCadreIds,aptUserId);
		 if(list != null && list.size() > 0)
		 {
			 for(Object[] params : list)
			 {
				 AppointmentCandidateVO vo = getMatchedVO(finalList,(Long)params[0]);
				 if(vo != null)
				 {
					 vo.setAppointmentCandidateId((Long)params[1]);
				 }
			 }
		 }
	 }
}

public AppointmentCandidateVO getMatchedVO(List<AppointmentCandidateVO>  finalList,Long id)
{
	if(finalList == null || finalList.size() == 0)
		return null;
	for(AppointmentCandidateVO vo : finalList)
	{
		if(vo.getId().longValue() == id.longValue())
			return vo;
	}
	return null;
}
public void setDataMembersForCadre(List<Object[]> membersList, List<AppointmentCandidateVO>  finalList,Long aptUserId)
{
	List<Long> tdpCadreIds = new ArrayList<Long>();
	if(membersList!=null && membersList.size()>0){
		 
		  for(Object[] obj: membersList){
			  AppointmentCandidateVO vo =new AppointmentCandidateVO();
  		  vo.setId(obj[0]!=null?(Long)obj[0]:0l);
  		  vo.setCandidateType("cadre");
  		  vo.setName(obj[1]!=null?obj[1].toString():"");
  		  vo.setCadre(true);
  		  vo.setMobileNo(obj[2]!=null?obj[2].toString():"");
  		  vo.setConstituency(obj[3]!=null?obj[3].toString():"");
  		  vo.setMemberShipId(obj[4]!=null?obj[4].toString():"");
  		  vo.setVoterCardNo(obj[5]!=null?obj[5].toString():"");
  		  vo.setImageURL(obj[6]!=null?"images/cadre_images/"+obj[6].toString():null);
  		  finalList.add(vo);
  		 if(!tdpCadreIds.contains(vo.getId()))
  	  		  tdpCadreIds.add(vo.getId());
		  }
		  List<Object[]> list = appointmentCandidateDAO.getAppointmentCandidateIdForCadreIds(tdpCadreIds,aptUserId);
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params : list)
				 {
					 AppointmentCandidateVO vo = getMatchedVO(finalList,(Long)params[0]);
					 if(vo != null)
					 {
						 vo.setAppointmentCandidateId((Long)params[1]);
					 }
				 }
			 }
	  }
}

	public VoterAddressVO getMemberDetails(String candidateType,Long id){
		
		VoterAddressVO addressVO = null;
		try {
			  if(candidateType.equalsIgnoreCase("appointmentCandidate")){
				  addressVO=getVoterWorkAddressDetailsByCadreId(id,candidateType);
			  }else if(candidateType.equalsIgnoreCase("cadre")){
				  addressVO=getVoterWorkAddressDetailsByCadreId(id,candidateType);
			  }else if(candidateType.equalsIgnoreCase("voter")){
				  List<Object[]> list = boothPublicationVoterDAO.getVoterAddressDetailsVoterId(id);
				  if(list!=null && list.size()>0){
					  Object[] obj= list.get(0);
					  if(obj!=null){
						  addressVO = new VoterAddressVO();
						  if(obj[0]!=null){
							  addressVO.setDistrictId(obj[0]!=null?(Long)obj[0]:0l);
							  addressVO.setLocationScopeId(3l);
						  }
						  if(obj[1]!=null){
							  addressVO.setConstituencyId(obj[1]!=null?(Long)obj[1]:0l);
							  addressVO.setLocationScopeId(4l);
						  }
						  if(obj[2]!=null){
							  addressVO.setTehsilId(obj[2]!=null?Long.valueOf("4"+obj[2].toString()):0l);
							  addressVO.setLocationScopeId(5l);
						  }
						  if(obj[3]!=null){
							  addressVO.setLocalElectionBodyId(obj[3]!=null?Long.valueOf("5"+obj[3].toString()):0l);
							  addressVO.setLocationScopeId(7l);
						  }
						  if(obj[4]!=null){
							  addressVO.setVillageId(obj[4]!=null?Long.valueOf("7"+obj[4].toString()):0l);
							  addressVO.setLocationScopeId(6l);
						  }
						  if(obj[5]!=null){
							  addressVO.setWardId(obj[5]!=null?Long.valueOf("8"+obj[5].toString()):0l);
							  addressVO.setLocationScopeId(8l);
						  }
						  
					  }
				  }
			  }
			  if(addressVO!=null){
					 
					 if(addressVO.getDistrictId()!=null){
						 addressVO.setConstList(rtcUnionService.getConstituenciesForDistrict(addressVO.getDistrictId()));
					 }
					 if(addressVO.getConstituencyId()!=null){
						 addressVO.setTehLebDivList(rtcUnionService.getLocationsOfSublevelConstituencyMandal(addressVO.getConstituencyId(),null,4l));
					 }
					 if(addressVO.getTehsilId()!=null){
						 addressVO.setVillWardList(rtcUnionService.getLocationsOfSublevelConstituencyMandal(null,addressVO.getTehsilId().toString(),5l));
					 }
					 if(addressVO.getLocalElectionBodyId()!=null){
						addressVO.setVillWardList(rtcUnionService.getLocationsOfSublevelConstituencyMandal(null,addressVO.getLocalElectionBodyId().toString(),5l));
					 }
			 }
			
		} catch (Exception e) {
			LOG.error("Exception riased at getMemberDetails", e);
		}
		return addressVO;
	}
	
     public VoterAddressVO getVoterWorkAddressDetailsByCadreId(Long id,String candidateType){
		
		VoterAddressVO addressVO=new VoterAddressVO();
		
		try{
			List<UserAddress> userAddressList=null;
			if(candidateType.equalsIgnoreCase("appointmentCandidate")){
				userAddressList = appointmentCandidateDAO.getUserWorkAddress(id);
			}else if(candidateType.equalsIgnoreCase("cadre")){
				userAddressList=tdpCadreDAO.getUserAddress(id);
			}
			
			
			if(userAddressList!=null && userAddressList.size()>0){
				
				UserAddress address=userAddressList.get(0);
				
				if(address!=null){
					
					
					if(address.getDistrict()!=null){
						addressVO.setDistrictId(address.getDistrict().getDistrictId()!=null?address.getDistrict().getDistrictId():0l);
						addressVO.setLocationScopeId(3l);
					}
					
					if(address.getConstituency()!=null){
						addressVO.setConstituencyId(address.getConstituency().getConstituencyId()!=null?address.getConstituency().getConstituencyId():0l);
						addressVO.setLocationScopeId(4l);
					}
					
					if(address.getTehsil()!=null){
						addressVO.setTehsilId(address.getTehsil().getTehsilId()!=null?Long.valueOf("4"+address.getTehsil().getTehsilId().toString()):0l);
						addressVO.setLocationScopeId(5l);
					}
					
					if(address.getLocalElectionBody()!=null){
						addressVO.setLocalElectionBodyId(address.getLocalElectionBody().getLocalElectionBodyId()!=null?Long.valueOf("5"+address.getLocalElectionBody().getLocalElectionBodyId().toString()):0l);
						addressVO.setLocationScopeId(7l);
					}
					
					if(address.getPanchayat()!=null){
						addressVO.setVillageId(address.getPanchayat().getPanchayatId()!=null?Long.valueOf("7"+address.getPanchayat().getPanchayatId().toString()):0l);
						addressVO.setLocationScopeId(6l);
					}
					if(address.getWard()!=null){
						addressVO.setWardId(address.getWard().getConstituencyId()!=null?Long.valueOf("8"+address.getWard().getConstituencyId().toString()):0l);
						addressVO.setLocationScopeId(8l);
					}
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception riased at getVoterAddressDetailsByCadreId", e);
		}
		return addressVO;
	}
     public List<AppointmentBasicInfoVO> getAllAppointmentDetails(int startIndex,int maxIndex,Long aptUserId){
    	 List<AppointmentBasicInfoVO> appointmentBasicInfoVOs = new ArrayList<AppointmentBasicInfoVO>(0);
    	 AppointmentBasicInfoVO appointmentBasicInfoVO = null;
    	 try{
 			LOG.info("Entered into getAllAppointmentDetails() method of AppointmentService");
 			List<Object[]> list = appointmentCandidateRelationDAO.getAllAppointmentDetails(startIndex,maxIndex,aptUserId);
 			if(list!=null && list.size()>0){
 				for(Object[] obj:list){
 					appointmentBasicInfoVO = new AppointmentBasicInfoVO();
 					appointmentBasicInfoVO.setName(obj[0]!=null?obj[0].toString():"");
 					appointmentBasicInfoVO.setMembershipNum(obj[1]!=null?obj[1].toString():"");
 					appointmentBasicInfoVO.setDate(obj[2]!=null?obj[2].toString().substring(0,19):"");
 					appointmentBasicInfoVO.setUniqueId(obj[3]!=null?obj[3].toString():"");
 					appointmentBasicInfoVO.setDesignation(obj[4]!=null?obj[4].toString():"");
 					appointmentBasicInfoVOs.add(appointmentBasicInfoVO);
 				}
 				if(startIndex==0){
 					List<Object[]> list1 = appointmentCandidateRelationDAO.countAppointmentDetails();
 					appointmentBasicInfoVOs.get(0).setCount(list1.size());
				}
 			}
    	 }catch(Exception e){
 			LOG.error("Exception riased at getAllAppointmentDetails() method of AppointmentService", e);
 			return null;
    	 }
    	 return appointmentBasicInfoVOs;
     }
 	
     // Appointments search criteria.
     public List<AppointmentDetailsVO> getAppointmentsBySearchCriteria(Long designationId,Long priorityId,Long statusId,Long districtId,Long constituencyid,Long appointmentlabelId,String fromDateStr,String toDateStr,Long selUserID,
    		 Long cndTypeId,Long dateTypeValue,Long apptUserId){
		   List<AppointmentDetailsVO> finalList = new ArrayList<AppointmentDetailsVO>(0);
		   SimpleDateFormat sdf =  new SimpleDateFormat("MM/dd/yyyy");
		   SimpleDateFormat sdf1 = new SimpleDateFormat("dd MMM yyyy h:mm a");
		   SimpleDateFormat prefer = new SimpleDateFormat("dd MMM yyyy");
		try {
			
			 Date fromDate = null;
			 Date toDate   = null;
			 if(fromDateStr!=null && fromDateStr.trim().length()>0){
				 fromDate = sdf.parse(fromDateStr);
			 }
			 if(toDateStr!=null && toDateStr.trim().length()>0){
				 toDate = sdf.parse(toDateStr);
			 }
			 
			Set<Long> appointmentIds = new HashSet<Long>(0);
			Set<Long> candidateIds = new HashSet<Long>(0);
			
			List<Object[]> statList = appointmentStatusDAO.getAppointmentStatusList();
			
			Map<Long,AppointmentDetailsVO> appointmentsMap = null;
			
			List<Object[]>   list = appointmentCandidateRelationDAO.getAppointmentsBySearchCriteria(designationId,priorityId,statusId,districtId,constituencyid,fromDate,toDate,selUserID,cndTypeId,dateTypeValue);
			if(list !=null && list.size()>0){
				
				appointmentsMap = new LinkedHashMap<Long, AppointmentDetailsVO>();
				
				for(Object[]  obj: list){
					
					if(obj[5]!=null && (Long)obj[5]>0){//checking wether the appointment is already for any label or not
						
						Long apptLabelId = (Long)obj[5];
						
						if(apptLabelId.equals(appointmentlabelId)){//if assigned label is same label
							AppointmentDetailsVO appointment =new AppointmentDetailsVO();
							appointment.setAppointmentId(obj[0]!=null?(Long)obj[0]:0l);
							appointment.setSubject(obj[1]!=null?obj[1].toString():"");
							appointment.setPriority(obj[2]!=null?obj[2].toString():"");
							appointment.setStatus(obj[3]!=null?obj[3].toString():"");
							appointment.setDateString(obj[4]!=null?obj[4].toString():"");
							appointment.setAptUniqueCode(obj[6]!=null?obj[6].toString():"");
							appointmentsMap.put(appointment.getAppointmentId(),appointment);
							
							//appointmentIds
							appointmentIds.add(appointment.getAppointmentId());
						}
					}else{//adding not assigned appointments to any label
						AppointmentDetailsVO appointment =new AppointmentDetailsVO();
						appointment.setAppointmentId(obj[0]!=null?(Long)obj[0]:0l);
						appointment.setSubject(obj[1]!=null?obj[1].toString():"");
						appointment.setPriority(obj[2]!=null?obj[2].toString():"");
						appointment.setStatus(obj[3]!=null?obj[3].toString():"");
						appointment.setDateString(obj[4]!=null?obj[4].toString():"");
						appointment.setAptUniqueCode(obj[6]!=null?obj[6].toString():"");
						appointmentsMap.put(appointment.getAppointmentId(),appointment);
						
						//appointmentIds
						appointmentIds.add(appointment.getAppointmentId());
					}
					
				}
			}
			
			
			List<Long> appointments = null;
			if(appointmentIds!=null && appointmentIds.size()>0){
				appointments = new ArrayList<Long>(appointmentIds);
			}
			
			//get dates for appointments.
			if(appointments!=null && appointments.size()>0){
				
				List<Object[]>  apptDateslist = appointmentPreferableDateDAO.getMultipleDatesforAppointments(appointments);
				if(apptDateslist!=null && apptDateslist.size()>0){
					for(Object[] obj : apptDateslist){
						AppointmentDetailsVO   appointmentVO = appointmentsMap.get((Long)obj[0]);
						appointmentVO.setDateTypeId((Long)obj[2]);
						appointmentVO.setDateType(obj[3].toString());
						if((Long)obj[2]==1l){
							if(appointmentVO.getApptpreferableDates()==null){
								appointmentVO.setApptpreferableDates(obj[1]!=null?obj[1].toString():"");
							}else{
								appointmentVO.setApptpreferableDates(appointmentVO.getApptpreferableDates() + " , " + (obj[1]!=null?obj[1].toString():"") );
							}
							
						}else{
							if(appointmentVO.getMinDateCheck() == 0l){
								appointmentVO.setMinDate(obj[1]!=null?obj[1].toString():"");
								appointmentVO.setMaxDate(obj[1]!=null?obj[1].toString():"");
							}else{
								appointmentVO.setMaxDate(obj[1]!=null?obj[1].toString():"");
							}
							appointmentVO.setMinDateCheck(appointmentVO.getMinDateCheck()+1l);
							
						}
					}
				}
			}
			
			
			
			//appointment related candidates.
			
			if(appointments!=null && appointments.size()>0){
				List<Object[]> candiList = appointmentCandidateRelationDAO.getAppointmentRelatedCandidates(appointments);
				
				if(candiList !=null && candiList.size()>0){
					for(Object[] obj : candiList){
						AppointmentDetailsVO appointmentVO = appointmentsMap.get((Long)obj[0]);
						
						if(appointmentVO!=null){
							
							if(appointmentVO.getSubMap()==null){
								appointmentVO.setSubMap(new HashMap<Long,AppointmentDetailsVO>(0));
							}
							AppointmentDetailsVO candidateVO = new AppointmentDetailsVO();
							candidateVO.setCandidateId(obj[1]!=null?(Long)obj[1]:0l);
							candidateVO.setName(obj[2]!=null?obj[2].toString():"");
							if(obj[3]!=null){
								candidateVO.setCadre(true);
							}
							candidateVO.setMobileNo(obj[4]!=null?obj[4].toString():"");
							candidateVO.setDesignation(obj[5]!=null?obj[5].toString():"");
							candidateVO.setConstituency(obj[6]!=null?obj[6].toString():"");
							candidateVO.setImageUrl(obj[7]!=null?obj[7].toString():"");
							
							candidateVO.setStatusList(setStatusList(statList));
							appointmentVO.getSubMap().put(candidateVO.getCandidateId(),candidateVO);
							
							//candidateIds
							candidateIds.add(candidateVO.getCandidateId());
						}
					}
				}
			}
			
			//candidate prevoius info.
			List<Long> candidates = null;
			if(candidateIds!=null && candidateIds.size()>0){
				candidates = new ArrayList<Long>(candidateIds);
			}
			
			if(candidates!=null && candidates.size()>0){
				List<Object[]> candidPreviousDetails =appointmentCandidateRelationDAO.getCandidatePreviousApptDetails1(candidates,apptUserId);
				if(candidPreviousDetails !=null && candidPreviousDetails.size()>0){
					
					for(Object[] obj : candidPreviousDetails){
						
						Long candidateId  = obj[0]!=null?(Long)obj[0]:0l;
						Long appointmentId = obj[1]!=null?(Long)obj[1]:0l;
						Long status      = obj[3]!=null?(Long)obj[3]:0l;
						
						if(candidateId != 0l){
							
							//set the data to candidate.
							for (Map.Entry<Long, AppointmentDetailsVO> entry : appointmentsMap.entrySet()) {
								
									AppointmentDetailsVO appointmentVO = entry.getValue();
									
									if (appointmentVO.getSubMap()!=null && appointmentVO.getSubMap().size()>0){
											AppointmentDetailsVO candidateVO = appointmentVO.getSubMap().get(candidateId);
											if(candidateVO !=null){
											
												if(!appointmentId.equals(entry.getKey().longValue())){
													
													if(candidateVO.getSubList() == null){
												    	candidateVO.setSubList(new ArrayList<AppointmentDetailsVO>());
												    }
													AppointmentDetailsVO apptvo = new AppointmentDetailsVO();
													apptvo.setAppointmentId(appointmentId);
													
													Date dateStr = obj[2]!=null?(Date)obj[2]:null;
													if(dateStr !=null){
														apptvo.setDateString(prefer.format(dateStr));
													}													
													apptvo.setStatus(obj[4]!=null?obj[4].toString():"");
													apptvo.setAptUniqueCode(obj[9]!=null?obj[9].toString():"");
													
													List<Long> aptmnts = new ArrayList<Long>();
													aptmnts.add(apptvo.getAppointmentId());
													
													//Prefer Dates Scenario For History start
													
													apptvo = setPreferebleDatesToAppointment(aptmnts,apptvo);
													
													/*List<Object[]>  apptDates = appointmentPreferableDateDAO.getMultipleDatesforAppointments(aptmnts);
													if(apptDates!=null && apptDates.size()>0){
														for(Object[] object : apptDates){
															//AppointmentDetailsVO   appointmentVO1 = new AppointmentDetailsVO();
															apptvo.setDateTypeId((Long)object[2]);
															apptvo.setDateType(object[3].toString());
															if((Long)object[2]==1l){
																if(apptvo.getApptpreferableDates()==null){
																	
																	Date preferDate = object[1]!=null?(Date)object[1]:null;
																	if(preferDate !=null){
																		apptvo.setApptpreferableDates(prefer.format(preferDate));
																	}
																	
																}else{
																	
																	Date preferDate = object[1]!=null?(Date)object[1]:null;
																	if(preferDate !=null){
																		apptvo.setApptpreferableDates(apptvo.getApptpreferableDates() + " , " + (prefer.format(preferDate)) );
																	}
																	
																}
																
															}else{
																
																if(apptvo.getMinDateCheck() == 0l){	
																	Date preferDate = object[1]!=null?(Date)object[1]:null;
																	if(preferDate !=null){
																		apptvo.setMinDate(prefer.format(preferDate));
																		apptvo.setMaxDate(prefer.format(preferDate));
																	}
																}else{
																	Date preferDate = object[1]!=null?(Date)object[1]:null;
																	apptvo.setMaxDate(prefer.format(preferDate));
																}
																apptvo.setMinDateCheck(apptvo.getMinDateCheck()+1l);
																
															}
															
														}
													}*/
													
													
													//Prefer Dates Scenario For History End
													
													
													if(obj[7]!=null){
														
														Date startDate = (Date)obj[7];
														Date  endDate=   obj[8]!=null?(Date)obj[8]:null;
														String startDateStr = sdf1.format(startDate);
														String endDateStr   = sdf1.format(endDate);
														if(status==2l){
															apptvo.setApptStatus("Appt Fixed on "+startDateStr +" to "+endDateStr.split(" ")[3]+" "+endDateStr.split(" ")[4]);
														}else if(status==3l){
															apptvo.setApptStatus("Attended at "+startDateStr);
														}else if(status==4l){
															apptvo.setApptStatus("Not Attended at "+startDateStr);
														}
														
													}else if(obj[5]!=null){
														Date startDate = (Date)obj[5];
														Date  endDate=   obj[6]!=null?(Date)obj[6]:null;
														String startDateStr = sdf1.format(startDate);
														String endDateStr   = sdf1.format(endDate);
														
														if(status==1){
															apptvo.setApptStatus(" waiting from "+startDateStr);
														}else if(status==5l){
															apptvo.setApptStatus(" rescheduled at "+endDateStr);
														}else if(status==6l){
															apptvo.setApptStatus(" cancelled on "+endDateStr);
														}
														
													}
													
													candidateVO.getSubList().add(apptvo);
													
													IdNameVO statusVO = getMatchedVo(candidateVO.getStatusList(),status);
													if(statusVO!=null){
														statusVO.setActualCount(statusVO.getActualCount() + 1l);
													}
													candidateVO.setRequestCount(candidateVO.getRequestCount()+1l);
												}
											}
									 }
							  }
						}
						
					}
				}
			}
			
			//does label has already elements.
			if (appointmentlabelId!=null && appointmentlabelId>0l && appointments!=null && appointments.size()>0){
				
				List<Object[]> labelAppointmentsList = labelAppointmentDAO.checkLabelWithAppointment(appointmentlabelId,appointments);
				
				if(labelAppointmentsList!=null && labelAppointmentsList.size()>0){
					
					for(Object[] obj : labelAppointmentsList){
						
						if(obj[2]!=null){
							AppointmentDetailsVO appointmentVO = appointmentsMap.get((Long)obj[2]);
							if(appointmentVO!=null){
								appointmentVO.setLabeled(true);	
							}
						}
					}
					
				}
				
			}
			
			//get last visits by candidates.
			if(candidates!=null && candidates.size()>0){
				List<Object[]> lastVisitList = appointmentCandidateRelationDAO.getLastVisitsByCandidates(candidates,apptUserId);
				if(lastVisitList!=null && lastVisitList.size()>0){
					for(Object[] obj : lastVisitList){
						Long candidateId  = obj[0]!=null?(Long)obj[0]:0l;
						
						for (Map.Entry<Long, AppointmentDetailsVO> entry : appointmentsMap.entrySet()) {
							
								AppointmentDetailsVO appointmentVO = entry.getValue();
								
								if (appointmentVO.getSubMap()!=null && appointmentVO.getSubMap().size()>0){
										AppointmentDetailsVO candidateVO = appointmentVO.getSubMap().get(candidateId);
										if(candidateVO !=null){
										
											candidateVO.setLastVisit(obj[1]!=null?sdf1.format((Date)obj[1]):"");
										}
								 }
						  }
					}
				}
			}
			
			
			
			if(appointmentsMap!=null && appointmentsMap.size()>0){
				for (Map.Entry<Long, AppointmentDetailsVO> entry : appointmentsMap.entrySet()) {
					
					AppointmentDetailsVO appointmentVO = entry.getValue();
					if(appointmentVO.getSubMap()!=null && appointmentVO.getSubMap().size()>0){
						appointmentVO.setSubList(new ArrayList<AppointmentDetailsVO>(appointmentVO.getSubMap().values()));
						appointmentVO.getSubMap().clear();
					}
				}
				
				finalList.addAll(appointmentsMap.values());
				appointmentsMap.clear();
			}
			
			
		} catch (Exception e) {
			LOG.error("Exception raised at getAppointmentsBySearchCriteria",e);
		}
		return finalList;
	}
	
	public List<IdNameVO> setStatusList(List<Object[]> list){
		
		List<IdNameVO> idNameVOList = new ArrayList<IdNameVO>();
		if(list!=null && list.size()>0){
			for(Object[] obj: list){
				IdNameVO idNameVO = new IdNameVO();
				idNameVO.setId(obj[0]!=null?(Long)obj[0]:0l);
				idNameVO.setName(obj[1]!=null?obj[1].toString():"");
				idNameVOList.add(idNameVO);
			}
		}
		return idNameVOList;
	}
	public IdNameVO getMatchedVo(List<IdNameVO> resultList,Long id)
	{
		if(resultList == null || resultList.size() == 0)
			return null;
		for(IdNameVO vo : resultList)
		{
			if(id!= null && vo.getId().longValue() == id.longValue())
			{
			return vo;	
			}
		}
		return null;
	}
	
	public ResultStatus addAppointmentstoLabel(final Long apptLabelId,final List<Long> appointmentIds,final Long loggerUserId){
		final ResultStatus rs = new ResultStatus();
		final DateUtilService dateUtilService = new DateUtilService();
		
		try {
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
		        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
		        	
			        	if(apptLabelId !=null && apptLabelId>0l){
			        		
			        		if(appointmentIds!=null && appointmentIds.size()>0){
			        			
			        			
			        			List<Long> labeledAppointmentIds = labelAppointmentDAO.getAppointmentsForALabel(apptLabelId);
			        			
			        			
			        			List<Long> savingAppointmentIds   =  new ArrayList<Long>(appointmentIds);
			        			List<Long> updatingAppointmentIds =  null;
			        			List<Long> deletedAppointmentIds  =  null;
			        			
			        			if(labeledAppointmentIds!=null && labeledAppointmentIds.size()>0){
			        				/*updatingAppointmentIds = new ArrayList<Long>(labeledAppointmentIds);
			        				deletedAppointmentIds  = new ArrayList<Long>(labeledAppointmentIds);
			        				
			        				updatingAppointmentIds.retainAll(appointmentIds);
			        				deletedAppointmentIds.removeAll(appointmentIds); */
			        				savingAppointmentIds.removeAll(labeledAppointmentIds);
			        			}
			        			
			        			List<Long> updateAndDeletedAppointmentIds = new ArrayList<Long>();
			        			
			        			if(updatingAppointmentIds!=null && updatingAppointmentIds.size()>0){
			        				int updatedCount = labelAppointmentDAO.updateLabeledAppointments(apptLabelId,updatingAppointmentIds,loggerUserId,dateUtilService.getCurrentDateAndTime());
			        				updateAndDeletedAppointmentIds.addAll(updatingAppointmentIds);
			        			}
			        			
			        			if(deletedAppointmentIds!=null && deletedAppointmentIds.size()>0){
			        				int deletedCount = labelAppointmentDAO.deleteLabeledAppointments(apptLabelId,deletedAppointmentIds);
			        				int apptsLabeligStausCount  = appointmentDAO.updateLabelingStatusToAppts(deletedAppointmentIds,"N");
			        				updateAndDeletedAppointmentIds.addAll(deletedAppointmentIds);
			        			}
			        			
			        			
			        			if(updateAndDeletedAppointmentIds!=null && updateAndDeletedAppointmentIds.size()>0){
			        				
			        				List<LabelAppointment>  deletedAndupdatedList = labelAppointmentDAO.getDetailsOfLabelledAppointments(apptLabelId,updateAndDeletedAppointmentIds);
				        			if(deletedAndupdatedList!=null && deletedAndupdatedList.size()>0){
				        				for(LabelAppointment labelAppointment :deletedAndupdatedList){
				        					LabelAppointmentHistory history = new LabelAppointmentHistory();
						        			history.setLabelAppointmentId(labelAppointment.getLabelAppointmentId());
						        			history.setAppointmentLabelId(labelAppointment.getAppointmentLabelId());
						        			history.setAppointmentId(labelAppointment.getAppointmentId());
						        			history.setLabelStatusId(labelAppointment.getAppointmentLabel()!=null? labelAppointment.getAppointmentLabel().getAppointmentLabelStatusId()!=null?labelAppointment.getAppointmentLabel().getAppointmentLabelStatusId():null :null);
						        			history.setCreatedBy(labelAppointment.getCreatedBy());
						        			history.setUpdatedBy(labelAppointment.getUpdatedBy());
						        			history.setInsertedTime(labelAppointment.getInsertedTime());
						        			history.setUpdatedTime(labelAppointment.getUpdatedTime());
						        			history.setIsDeleted(labelAppointment.getIsDeleted());
						        			
						        			labelAppointmentHistoryDAO.save(history);
				        				}
				        			}
			        			}
			        			
			        		
			        			//saving.
			        			if(savingAppointmentIds!=null && savingAppointmentIds.size()>0){
			        				for(Long appointmentId : savingAppointmentIds){
				        				
				        				LabelAppointment labelAppointment = new LabelAppointment();
					        			labelAppointment.setAppointmentLabelId(apptLabelId);
					        			labelAppointment.setAppointmentId(appointmentId);
					        			labelAppointment.setCreatedBy(loggerUserId);
					        			labelAppointment.setUpdatedBy(loggerUserId);
					        			labelAppointment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					        			labelAppointment.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					        			labelAppointment.setIsDeleted("N");
					        			
					        			labelAppointment = labelAppointmentDAO.save(labelAppointment);
					        			
					        			
					        			//move to history.
					        			LabelAppointmentHistory history = new LabelAppointmentHistory();
					        			history.setLabelAppointmentId(labelAppointment.getLabelAppointmentId());
					        			history.setAppointmentLabelId(labelAppointment.getAppointmentLabelId());
					        			history.setAppointmentId(labelAppointment.getAppointmentId());
					        			history.setLabelStatusId(labelAppointment.getAppointmentLabel()!=null? labelAppointment.getAppointmentLabel().getAppointmentLabelStatusId()!=null?labelAppointment.getAppointmentLabel().getAppointmentLabelStatusId():null :null);
					        			history.setCreatedBy(labelAppointment.getCreatedBy());
					        			history.setUpdatedBy(labelAppointment.getUpdatedBy());
					        			history.setInsertedTime(labelAppointment.getInsertedTime());
					        			history.setUpdatedTime(labelAppointment.getUpdatedTime());
					        			history.setIsDeleted(labelAppointment.getIsDeleted());
					        			
					        			labelAppointmentHistoryDAO.save(history);
					        			
				        			}
			        				//int apptsLabeligStausCount  = appointmentDAO.updateLabelingStatusToAppts(savingAppointmentIds,"Y");
			        				changeAppointmentStatus(savingAppointmentIds, IConstants.APPOINTMENT_STATUS_LABELED);
			        			}
			        			
			        			
			        			
			        		}
			        	}
			        	rs.setResultCode(1);
			        	rs.setMessage("success");
		         }
		    });
			
		} catch (Exception e) {
			LOG.error("Exception raised at addAppointmentstoLabel", e);
			rs.setResultCode(0);
			rs.setMessage("failure");
		}
		return rs;
	}
	public List<AppointmentVO> getAppointmentsOfALableForUpdate(Long lableId){
		List<AppointmentVO> finalVOList = new ArrayList<AppointmentVO>(0); 
		try {
			List<Long> assignedAppointmentIds = new ArrayList<Long>(0);
			Map<Long,AppointmentVO> map = new HashMap<Long, AppointmentVO>(0);
			List<Object[]> assignedAppointmentsObjList = labelAppointmentDAO.getAppointmentsOfALableForUpdate(lableId,null);
			
			if(assignedAppointmentsObjList != null && assignedAppointmentsObjList.size() > 0){
				for (Object[] objects : assignedAppointmentsObjList) {
					assignedAppointmentIds.add((Long)objects[0]);
					AppointmentVO vo = new AppointmentVO();
					vo.setAppointmentId((Long)objects[0]);
					vo.setAppointmentPriorityId((Long)objects[1]);
					vo.setPriority(objects[2].toString());
					vo.setReason(objects[3].toString());
					vo.setAppointmentStatusId((Long)objects[4]);
					vo.setStatus(objects[5].toString());
					vo.setUserId((Long)objects[6]);
					vo.setUserName(objects[7].toString());
					map.put((Long)objects[0], vo);
				}
			}
			
			//get appointment candidate Details
			if(assignedAppointmentIds != null && assignedAppointmentIds.size() > 0){
				
				List<Object[]> candidateDetails = appointmentCandidateRelationDAO.getAppointmentCandidateDetails(assignedAppointmentIds);
				
				if(candidateDetails != null && candidateDetails.size() > 0){
					for (Object[] objects : candidateDetails) {
						if(map.get((Long)objects[5])!=null){
							AppointmentVO matchedVo = map.get((Long)objects[5]);
							
							AppointmentBasicInfoVO vo = new AppointmentBasicInfoVO();
							vo.setId((Long)objects[0]);
							vo.setName(objects[1].toString());
							vo.setMobileNo(objects[2].toString());
							vo.setDesignation(objects[3].toString());
							vo.setMembershipNum(objects[4]!=null?objects[4].toString():null);
							matchedVo.getBasicInfoList().add(vo);
						}
					}
				}
				
			}
			
			if(map != null && map.size() > 0){
				finalVOList.addAll(map.values());
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getAppointmentsOfALableForUpdate", e);
		}
		
		return finalVOList;
	}
	
	public LabelStatusVO getLabelAndStatuswiseCountsOfAppointments(){
		
		LabelStatusVO finalVo = new LabelStatusVO();
		
		try{			
			//toDay Block
			
					DateUtilService dt = new DateUtilService();
					Date curentDateTime = dt.getCurrentDateAndTime();
					
					List<Object[]> totalTodayObjList = new ArrayList<Object[]>();
					List<Object[]> totalObjList = new ArrayList<Object[]>();
					
							//Fixed Status
					List<Object[]> inProgreeList = labelAppointmentDAO.getLabelAppointmentsForFixed(curentDateTime,"Inprogress","ToDay");
					List<Object[]> upcomingList  = labelAppointmentDAO.getLabelAppointmentsForFixed(curentDateTime,"Upcoming","ToDay");
					List<Object[]> completedList  = labelAppointmentDAO.getLabelAppointmentsForFixed(curentDateTime,"Completed","ToDay");
					
					if(inProgreeList !=null && inProgreeList.size()>0){
						inProgreeList = setStatusOfObjectList(inProgreeList,"Inprogress");	
						totalTodayObjList.addAll(inProgreeList);
					}if(upcomingList !=null && upcomingList.size()>0){
						upcomingList = setStatusOfObjectList(upcomingList,"Upcoming");
						totalTodayObjList.addAll(upcomingList);
					}
					if(completedList !=null && completedList.size()>0){
						completedList = setStatusOfObjectList(completedList,"Completed");
						totalTodayObjList.addAll(completedList);
					}
								
							//Status Wise
					
					List<Object[]> statusObjList = labelAppointmentDAO.getStatusLabelAppointments(curentDateTime,"ToDay");
					
					if(statusObjList !=null && statusObjList.size()>0){
						totalTodayObjList.addAll(statusObjList);
					}
					
					finalVo = settingStausDetailsDataToFinalVo(finalVo,totalTodayObjList,"toDay");
			
			//OverAll Block
					
					List<Object[]> inProgreeOverAllList = labelAppointmentDAO.getLabelAppointmentsForFixed(curentDateTime,"Inprogress","overall");
					List<Object[]> upcomingOverAllList  = labelAppointmentDAO.getLabelAppointmentsForFixed(curentDateTime,"Upcoming","overall");
					List<Object[]> completedOverAllList  = labelAppointmentDAO.getLabelAppointmentsForFixed(curentDateTime,"Completed","overall");
					
					
					if(inProgreeOverAllList !=null && inProgreeOverAllList.size()>0){
						inProgreeOverAllList = setStatusOfObjectList(inProgreeOverAllList,"Inprogress");						
						totalObjList.addAll(inProgreeOverAllList);
					}if(upcomingOverAllList !=null && upcomingOverAllList.size()>0){
						upcomingOverAllList = setStatusOfObjectList(upcomingOverAllList,"Upcoming");
						totalObjList.addAll(upcomingOverAllList);
					}
					if(completedOverAllList !=null && completedOverAllList.size()>0){
						completedOverAllList =setStatusOfObjectList(completedOverAllList,"Completed");
						totalObjList.addAll(completedOverAllList);
					}
								
							//Status Wise
					
					List<Object[]> statusObjOverAllList = labelAppointmentDAO.getStatusLabelAppointments(curentDateTime,"overall");
					
					if(statusObjOverAllList !=null && statusObjOverAllList.size()>0){
						totalObjList.addAll(statusObjOverAllList);
					}
					
					finalVo = settingStausDetailsDataToFinalVo(finalVo,totalObjList,"overAll");
			
					
		}catch (Exception e) {
			LOG.error("Exception raised at getLabelAndStatuswiseCountsOfAppointments", e);
		}		
		return finalVo;
	}
	
	public LabelStatusVO settingStausDetailsDataToFinalVo(LabelStatusVO finalVo,List<Object[]> objList,String type){
		try{
			
			//labelId
			Map<Long,LabelStatusVO> labelStatusMap = new HashMap<Long, LabelStatusVO>();
			
			if(objList !=null && objList.size()>0){
				for (Object[] objects : objList) {					
					LabelStatusVO labelVo = labelStatusMap.get(objects[0] !=null ? (Long)objects[0]:0l);	
					
					if(labelVo == null){
						labelVo = new LabelStatusVO();
						labelVo.setLabelId(objects[0] !=null ? (Long)objects[0]:0l);
						labelVo.setLabelName(objects[1] !=null ? objects[1].toString():"");	
						//assigning LabelVo to Map
						labelStatusMap.put((Long)objects[0], labelVo);
					}else{
						List<LabelStatusVO> statusList = labelVo.getStatusList();						
						if(statusList !=null){
							statusList = setValuesToStatusList(statusList,objects);						
						}						
					}
					
				}
			}
			
			if(labelStatusMap !=null && labelStatusMap.size()>0){
				List<LabelStatusVO> list = new ArrayList<LabelStatusVO>(labelStatusMap.values());
				if(type !=null && type.toString().trim().equalsIgnoreCase("toDay")){					
					finalVo.setStatusList(list);
				}else{
					finalVo.setOverAllStatusList(list);
				}
			}
			
		}catch (Exception e) {
			LOG.error("Exception raised at settingStausDetailsDataToFinalVo", e);
		}
		return finalVo;
	}
	public List<LabelStatusVO> setValuesToStatusList(List<LabelStatusVO> statusList,Object[] objects){
		
		try{
			if(statusList !=null && statusList.size()>0){				
				for (LabelStatusVO param : statusList) {						
					if(!param.getStatus().trim().equalsIgnoreCase(objects[3].toString())){						
						LabelStatusVO vo = new LabelStatusVO();
						vo.setStatus(objects[3].toString());
						vo.setStatusId((Long)objects[2]);
						vo.setTotalCount(objects[4] !=null ? (Long)objects[4]:0l);
						vo.setLabelId((Long)objects[0]);
						vo.setLabelName(objects[1].toString());
						
						statusList.add(vo);
					}					
				}
			}
			
		}catch (Exception e) {
			LOG.error("Exception raised at setValuesToStatusList", e);
		}		
		return statusList;
	}	
	public List<Object[]> setStatusOfObjectList(List<Object[]> fixedList,String type){
		try{
				if(fixedList !=null && fixedList.size()>0){
					for (Object[] objects : fixedList) {
						if(objects[3] !=null && !objects[3].toString().trim().isEmpty()){																			
							objects[3] = type;								
						}
					}
				}
			
		}catch(Exception e){
			LOG.error("Exception raised at setStatusOfObjectList", e);
		}
		return fixedList;
	}
	
	
	public LabelStatusVO getStatusWiseCountsOfAppointments(Long aptUserId){
		LabelStatusVO finalVo = new LabelStatusVO();
		
		try{
			
			DateUtilService dt = new DateUtilService();
			Date curentDateTime = dt.getCurrentDateAndTime();
			
			List<Object[]> totalTodayObjList = new ArrayList<Object[]>();
			List<Object[]> totalObjList = new ArrayList<Object[]>();
			
			//toDay Block
				//Fixed Status
						//0.statusId,1.status,2.count
						List<Object[]> inProgreeList = labelAppointmentDAO.getLabelAppointmentsForFixedSatus(curentDateTime,"Inprogress","ToDay",aptUserId);
						List<Object[]> upcomingList  = labelAppointmentDAO.getLabelAppointmentsForFixedSatus(curentDateTime,"Upcoming","ToDay",aptUserId);
						List<Object[]> completedList  = labelAppointmentDAO.getLabelAppointmentsForFixedSatus(curentDateTime,"Completed","ToDay",aptUserId);
						
						if(inProgreeList !=null && inProgreeList.size()>0){
							inProgreeList = setStatusOfObjectList1(inProgreeList,"Inprogress");	
							totalTodayObjList.addAll(inProgreeList);
						}if(upcomingList !=null && upcomingList.size()>0){
							upcomingList = setStatusOfObjectList1(upcomingList,"Upcoming");
							totalTodayObjList.addAll(upcomingList);
						}
						if(completedList !=null && completedList.size()>0){
							completedList = setStatusOfObjectList1(completedList,"Completed");
							totalTodayObjList.addAll(completedList);
						}
									
								//Status Wise
						
						List<Object[]> statusObjList = labelAppointmentDAO.getLabelAppointmentsStatus(curentDateTime,"ToDay",aptUserId);
						
						if(statusObjList !=null && statusObjList.size()>0){
							totalTodayObjList.addAll(statusObjList);
						}
						
						//default Status List
						List<LabelStatusVO> statusList = new ArrayList<LabelStatusVO>();
						//setting default statuses
						statusList = setStatusListOfAppointments(statusList);
						
						statusList = settingStausDetailsDataToFinalVo1(finalVo,totalTodayObjList,statusList);
						if(statusList !=null && statusList.size()>0){
							finalVo.setStatusList(statusList);
						}
				
			/*//OverAll Scenario
						List<Object[]> inProgreeOverAllList = labelAppointmentDAO.getLabelAppointmentsForFixedSatus(curentDateTime,"Inprogress","overall",aptUserId);
						List<Object[]> upcomingOverAllList  = labelAppointmentDAO.getLabelAppointmentsForFixedSatus(curentDateTime,"Upcoming","overall",aptUserId);
						List<Object[]> completedOverAllList  = labelAppointmentDAO.getLabelAppointmentsForFixedSatus(curentDateTime,"Completed","overall",aptUserId);
						
						
						if(inProgreeOverAllList !=null && inProgreeOverAllList.size()>0){
							inProgreeOverAllList = setStatusOfObjectList1(inProgreeOverAllList,"Inprogress");						
							totalObjList.addAll(inProgreeOverAllList);
						}if(upcomingOverAllList !=null && upcomingOverAllList.size()>0){
							upcomingOverAllList = setStatusOfObjectList1(upcomingOverAllList,"Upcoming");
							totalObjList.addAll(upcomingOverAllList);
						}
						if(completedOverAllList !=null && completedOverAllList.size()>0){
							completedOverAllList =setStatusOfObjectList1(completedOverAllList,"Completed");
							totalObjList.addAll(completedOverAllList);
						}
									
								//Status Wise
						
						List<Object[]> statusObjOverAllList = labelAppointmentDAO.getLabelAppointmentsStatus(curentDateTime,"overall",aptUserId);
						
						if(statusObjOverAllList !=null && statusObjOverAllList.size()>0){
							totalObjList.addAll(statusObjOverAllList);
						}
						
						//clearing if any values
						List<LabelStatusVO> overAllstatusList = new ArrayList<LabelStatusVO>();	
						//setting default statuses
						overAllstatusList = setStatusListOfAppointments(overAllstatusList);
						
						overAllstatusList = settingStausDetailsDataToFinalVo1(finalVo,totalObjList,overAllstatusList);
				
						if(overAllstatusList !=null && overAllstatusList.size()>0){
							finalVo.setOverAllStatusList(overAllstatusList);
						}*/
						
			
		}catch(Exception e){
			LOG.error("Exception raised at getStatusWiseCountsOfAppointments", e);
		}
		return finalVo;
	}
	
	public List<AppointmentStatusVO> getAppointmentStatusCounts(Long apptUserId){
	    
	    List<AppointmentStatusVO> finalList = new ArrayList<AppointmentStatusVO>(0);
	    try{
	       
	       List<String> statusList = appointmentStatusDAO.getAllStatus();
	      /* if(statusList!=null && statusList.size()>0){
	         statusList.add(1,"Labelled");
	       }*/
	       
	       if(statusList!=null && statusList.size()>0){
	        for (String status : statusList) {
	          
	          AppointmentStatusVO VO = new AppointmentStatusVO();
	          VO.setStatus(status);
	          VO.setStatusCount(0l);
	          VO.setMembersCount(0l);
	          finalList.add(VO);
	        }
	       }
	        
	       List<Object[]> countList = appointmentCandidateRelationDAO.getApptAndMembersCountsByStatus(apptUserId);
	       if(countList!=null && countList.size()>0){
	         for(Object[] obj:countList){
	           
	           String status = obj[1]!=null?obj[1].toString():"";
	           AppointmentStatusVO statusvo = getMatchedStatus(finalList,status);
	           if(statusvo!=null  && !statusvo.getStatus().equalsIgnoreCase("Fixed") && !statusvo.getStatus().equalsIgnoreCase("Attended")){
	            
	             statusvo.setStatusCount(obj[2]!=null?(Long)obj[2]:0l);
	             statusvo.setMembersCount(obj[3]!=null?(Long)obj[3]:0l);
	           }
	         }
	       }
	       
	      /* List<Object[]> waitingCounts = appointmentCandidateRelationDAO.getLabelledAndNonLabelledApptIdsForWaitingStatus(apptUserId,"N",IConstants.WAITING_APPOINTMENT_STATUS_ID);
	       List<Object[]> labelledWithWaitingCounts = appointmentCandidateRelationDAO.getLabelledAndNonLabelledApptIdsForWaitingStatus(apptUserId,"Y",IConstants.WAITING_APPOINTMENT_STATUS_ID);
	       setData(finalList,waitingCounts,"Waiting");
	       setData(finalList,labelledWithWaitingCounts,"Labelled");*/
	      
	       DateUtilService dts = new DateUtilService();
	       
	       List<Object[]> fixedCounts= appointmentCandidateRelationDAO.getOnlyFixedStatusCounts(apptUserId,dts.getCurrentDateAndTime(),IConstants.APPOINTMENT_STATUS_FIXED);
	       List<Object[]> attendedCounts= appointmentCandidateRelationDAO.getAttendedStatusCounts(apptUserId,dts.getCurrentDateAndTime(),IConstants.APPOINTMENT_STATUS_ATTENDED,IConstants.APPOINTMENT_STATUS_FIXED);
	       setData(finalList,fixedCounts,"Fixed");
	       setData(finalList,attendedCounts,"Attended");
	    
	    }catch(Exception e){
	    	LOG.error("Exception raised at getAppointmentStatusCounts", e);
	    }
	    return finalList;
	  }
	  public void setData(List<AppointmentStatusVO> finalList,List<Object[]> countsList,String status){
	    
	    if(countsList!=null && countsList.size()>0){
	       Object[] obj = countsList.get(0);
	       AppointmentStatusVO statusvo = getMatchedStatus(finalList,status);
	       if(statusvo!=null){
	         statusvo.setStatusCount(obj[0]!=null?(Long)obj[0]:0l);
	         statusvo.setMembersCount(obj[1]!=null?(Long)obj[1]:0l); 
	       }
	     }
	  }
	  
	  
	  public AppointmentStatusVO getMatchedStatus(List<AppointmentStatusVO> finalList, String status){
	    
	    try{
	      
	      if(finalList !=null && finalList.size()>0){        
	        for (AppointmentStatusVO statusVO : finalList) {          
	          String stts = status.trim();        
	          if(statusVO.getStatus().trim().equalsIgnoreCase(stts)){
	            return statusVO;
	          }          
	        }
	      }
	      
	    }catch(Exception e){
	    	LOG.error("Exception raised at getMatchedStatus", e);
	    }
	    return null;
	  }
	
	public List<Object[]> setStatusOfObjectList1(List<Object[]> fixedList,String type){
		try{
				if(fixedList !=null && fixedList.size()>0){
					for (Object[] objects : fixedList) {
						if(objects[1] !=null && !objects[1].toString().trim().isEmpty()){																			
							objects[1] = type;								
						}
					}
				}
			
		}catch(Exception e){
			LOG.error("Exception raised at setStatusOfObjectList1", e);
		}
		return fixedList;
	}
	
	public List<LabelStatusVO> settingStausDetailsDataToFinalVo1(LabelStatusVO finalVo,List<Object[]> objList,List<LabelStatusVO> statusList){		
		try{
			
			if(objList !=null && objList.size()>0){
			//	List<LabelStatusVO> statusList = new ArrayList<LabelStatusVO>();
				
				for (Object[] objects : objList) {					
					//Matching VO returning
					LabelStatusVO vo = getMatchedVoOfStatus(statusList,objects[1].toString());
					if(vo !=null){
						vo.setTotalCount(objects[2] !=null ? (Long)objects[2]:0l);	
					}			
				}				
			}
			
		}catch (Exception e) {
			LOG.error("Exception raised at settingStausDetailsDataToFinalVo1", e);
		}
		return statusList;
		
	}
	
	public List<LabelStatusVO> setStatusListOfAppointments(List<LabelStatusVO> labelStatusList){
		
		try{
			
			LabelStatusVO vo = null;
			
			vo = new LabelStatusVO();			
			vo.setStatusId(4l);
			vo.setStatus("Not Attended");
			vo.setTotalCount(0l);
			labelStatusList.add(vo);
			
			vo = new LabelStatusVO();
			vo.setStatusId(5l);
			vo.setStatus("Reschedule");
			vo.setTotalCount(0l);
			labelStatusList.add(vo);
			
			vo = new LabelStatusVO();
			vo.setStatusId(6l);
			vo.setStatus("Cancelled");
			vo.setTotalCount(0l);
			labelStatusList.add(vo);
			
			vo = new LabelStatusVO();			
			vo.setStatusId(2l);
			vo.setStatus("Completed");
			vo.setTotalCount(0l);
			labelStatusList.add(vo);
			
			vo = new LabelStatusVO();			
			vo.setStatusId(2l);
			vo.setStatus("InProgress");
			vo.setTotalCount(0l);
			labelStatusList.add(vo);
			
			vo = new LabelStatusVO();			
			vo.setStatusId(2l);
			vo.setStatus("UpComing");
			vo.setTotalCount(0l);
			labelStatusList.add(vo);
			
		}catch(Exception e){
			LOG.error("Exception raised at setStatusListOfAppointments", e);
		}
		return labelStatusList;		
	}
	
	public LabelStatusVO getMatchedVoOfStatus(List<LabelStatusVO> labelStatusList,String status){
		
		try{
			
			if(labelStatusList !=null && labelStatusList.size()>0){				
				for (LabelStatusVO labelStatusVO : labelStatusList) {					
					String stts = status.trim();				
					if(labelStatusVO.getStatus().trim().equalsIgnoreCase(stts)){
						return labelStatusVO;
					}					
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getMatchedVoOfStatus", e);
		}
		return null;
	}
	public AppointmentSlotsVO getTimeSlotsDetails(long appointmentLabelId){
		AppointmentSlotsVO appointmentSlotsVO = new AppointmentSlotsVO();
		List<String> timePair = null;
		List<List<String>> timePairsPerDay = new ArrayList<List<String>>(0);
		List<List<String>> timePairsPerDay1 = null;
		Collection<List<List<String>>> listOfTimePairsPerDate=null;
		List<String> dateList = new ArrayList<String>();
		Map<String,List<List<String>>> timeSlotsMap = new HashMap<String,List<List<String>>>();
		String date;
		
		try{
			LOG.info("Entered into getTimeSlotsDetails() method of AppointmentService");
			List<Object[]> list = labelAppointmentDAO.getTimeSlotsDetails(appointmentLabelId);
			if(list.isEmpty() && list.size()==0){
				appointmentSlotsVO.setLabelDate(appointmentLabelDAO.get(appointmentLabelId).getLabelFromDate().toString());
			}else{
				//if(list!=null && list.size()>0){
					for(Object[] object:list){
						timePair = new ArrayList<String>(0);
						timePair.add(object[3]!=null?object[3].toString():"");
						timePair.add(object[4]!=null?object[4].toString():"");
						date = object[2]!=null?object[2].toString():"";
						timePairsPerDay1 = new ArrayList<List<String>>(0);
						timePairsPerDay1.add(timePair);
						timePairsPerDay = timeSlotsMap.get(date);
						if(timePairsPerDay==null){
							dateList.add(date);
							timeSlotsMap.put(date, timePairsPerDay1);
						}else{
							timePairsPerDay.add(timePair);
							timeSlotsMap.put(date, timePairsPerDay);
						}
					}
					listOfTimePairsPerDate = new ArrayList<List<List<String>>>();
					listOfTimePairsPerDate =(Collection<List<List<String>>>) timeSlotsMap.values();
					appointmentSlotsVO.setDateList(dateList);
					appointmentSlotsVO.setListOfTimePairPerDate(listOfTimePairsPerDate);
				}
			//}
		}catch(Exception e){
			LOG.error("Exception raised at getTimeSlotsDetails() method of AppointmentService", e);
		}
		return appointmentSlotsVO;
	}
	public List<IdNameVO> getAppointmentLabels(Long aptUserId){
		List<IdNameVO> labelList = new ArrayList<IdNameVO>();
		try{
			List<Object[]> list=appointmentLabelDAO.getAppointmentLabels(aptUserId);
			labelList = setDataToVO(list);
		}catch(Exception e){
			LOG.error("Exception raised at getTimeSlotsDetails() method of AppointmentService",e);
		}
		return labelList;
	}
	
	public List<AppointmentScheduleVO> getAppointmentSearchDetails(AppointmentInputVO inputVo)
	{
		List<AppointmentScheduleVO> resultList = null;
		try{
			
			String searchType = null;
			Date strDate = null;
			Date endDate = null;
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			if(inputVo.getStrDate() != null && !inputVo.getStrDate().isEmpty())
			{
				strDate = format.parse(inputVo.getStrDate());
				endDate = format.parse(inputVo.getEndDate());
			}
			 if(inputVo.getName() != null && !inputVo.getName().isEmpty())
			 {
				 if(inputVo.getName().matches("\\d+"))
				 searchType = "mobile";
				 else
				searchType = "name"; 
			}
			 
				List<Object[]> list = appointmentCandidateRelationDAO.getAppointmentSearchDetails(strDate,endDate,inputVo,searchType);
				if(list != null && list.size() > 0)
				{
					resultList = new ArrayList<AppointmentScheduleVO>();
					for(Object[] params : list)
					{
						
						AppointmentScheduleVO vo = getMatchedAppointment(resultList,(Long)params[11]);
						if(vo == null)
						{
							vo = new AppointmentScheduleVO();
							vo.setAppointmentId((Long)params[11]);
							if(params[8] != null && !params[8].toString().isEmpty() && params[12] != null && !params[12].toString().isEmpty())
							{
								Date date= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(params[8].toString().substring(0, 19));
								String convertDate = new SimpleDateFormat("hh:mm a").format(date);
								
								Date date1= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(params[12].toString().substring(0, 19));
								String convertDate1 = new SimpleDateFormat("hh:mm a").format(date1);
								vo.setScheduleType(getAppointmentSchedule(params[8].toString().substring(0, 19),params[12].toString().substring(0, 19)));
								vo.setSubject(params[4] != null ? params[4].toString() : "");
								vo.setStatusId(params[9] != null ? (Long)params[9] : null);
								vo.setAppointmentStatus(params[10] != null ? params[10].toString() : "");
								vo.setTime(convertDate);
								vo.setToTime(convertDate1);
								vo.setFromDate(params[8].toString());
								vo.setToDate(params[12].toString());
								vo.setDate(params[14].toString().split(" ")[0]);
								vo.setAppointmentUniqueId(params[13]!=null?params[13].toString():"");
								vo.setImageUrl(params[15]!=null?"images/cadre_images/"+params[15].toString():null);
								
							}
							resultList.add(vo);
						}
						AppointmentScheduleVO candidateVo = new AppointmentScheduleVO();
						candidateVo.setName(params[1] != null ? params[1].toString() :"");
						candidateVo.setMobileNo(params[2] != null ? params[2].toString() : "");
						
						candidateVo.setDesignation(params[3] != null ? params[3].toString() : "");
						String fname = params[6] != null ? params[6].toString() : "";
						String lname = params[7] != null ?params[7].toString() : "";
						candidateVo.setCreatedBy(fname+" "+lname);
					
						vo.getSubList().add(candidateVo);
					}
				}
			   
		}catch(Exception e){
			LOG.error("Exception raised at getAppointmentSearchDetails", e);
		}
		return resultList;
		
	}
	
	public AppointmentScheduleVO getMatchedAppointment(List<AppointmentScheduleVO> resultList,Long id)
	{
		if(resultList == null || resultList.size() == 0)
			return null;
		for(AppointmentScheduleVO vo : resultList)
		{
			if(vo.getAppointmentId().longValue() == id.longValue())
				return vo;
		}
		return null;
	}
	public String getAppointmentSchedule(String fromDate,String toDate)
	{
		String scheduleType = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		 try {
			Date fromDate1 = format.parse(fromDate);
			Date toDate1 = format.parse(toDate);
			Date date2 = new DateUtilService().getCurrentDateAndTime();
			
			if (toDate1.compareTo(date2) < 0)
			{
					scheduleType = "Completed";
			}
			else if (fromDate1.compareTo(date2) > 0)
			{
				scheduleType = "UpCome";
			}
			else
			{
				scheduleType = "InProgress";
			}
		 } catch (ParseException e) {
				
				e.printStackTrace();
			}
		return scheduleType;
	}
	
	//view apointments for label
	 public List<AppointmentDetailsVO> viewAppointmentsOfALable(Long labelId,String callFrom,Long apptUserId,String labelName){
		   List<AppointmentDetailsVO> finalList = new ArrayList<AppointmentDetailsVO>(0);
		   SimpleDateFormat sdf1 = new SimpleDateFormat("dd MMM yyyy h:mm a");
		   SimpleDateFormat prefer = new SimpleDateFormat("dd MMM yyyy");
		try {
			
			Set<Long> appointmentIds = new HashSet<Long>(0);
			Set<Long> candidateIds = new HashSet<Long>(0);
			
			List<Object[]> statList = appointmentStatusDAO.getAppointmentStatusList();
			
			Map<Long,AppointmentDetailsVO> appointmentsMap = null;
			List<Object[]> list = null;
			//List<Object[]>   list = appointmentCandidateRelationDAO.getAppointmentsBySearchCriteria(designationId,priorityId,statusId,districtId,constituencyid);
			list = labelAppointmentDAO.getAppointmentsOfALableForUpdate(labelId,callFrom);
			if(list !=null && list.size()>0){
				
				appointmentsMap = new LinkedHashMap<Long, AppointmentDetailsVO>();
				
				for(Object[]  obj: list){
					AppointmentDetailsVO appointment =new AppointmentDetailsVO();
					appointment.setAppointmentId(obj[0]!=null?(Long)obj[0]:0l);
					appointment.setSubject(obj[3]!=null?obj[3].toString():"");
					appointment.setPriority(obj[2]!=null?obj[2].toString():"");
					appointment.setStatus(obj[5]!=null?obj[5].toString():"");
					appointment.setDateString(obj[8]!=null?obj[8].toString().substring(0,10):"");
					appointment.setAptUniqueCode(obj[9]!=null?obj[9].toString():"");
					appointmentsMap.put(appointment.getAppointmentId(),appointment);
					
					//appointmentIds
					appointmentIds.add(appointment.getAppointmentId());
				}
			}
			
			
			List<Long> appointments = null;
			if(appointmentIds!=null && appointmentIds.size()>0){
				appointments = new ArrayList<Long>(appointmentIds);
			}
			
			//get dates for appointments.
			if(appointments!=null && appointments.size()>0){
				
				List<Object[]>  apptDateslist = appointmentPreferableDateDAO.getMultipleDatesforAppointments(appointments);
				if(apptDateslist!=null && apptDateslist.size()>0){
					for(Object[] obj : apptDateslist){
						AppointmentDetailsVO   appointmentVO = appointmentsMap.get((Long)obj[0]);
						appointmentVO.setDateTypeId((Long)obj[2]);
						appointmentVO.setDateType(obj[3].toString());
						if((Long)obj[2]==1l){
							if(appointmentVO.getApptpreferableDates()==null){
								appointmentVO.setApptpreferableDates(obj[1]!=null?obj[1].toString():"");
							}else{
								appointmentVO.setApptpreferableDates(appointmentVO.getApptpreferableDates() + " , " + (obj[1]!=null?obj[1].toString():"") );
							}
							
						}else{
							if(appointmentVO.getMinDateCheck() == 0l){
								appointmentVO.setMinDate(obj[1]!=null?obj[1].toString():"");
								appointmentVO.setMaxDate(obj[1]!=null?obj[1].toString():"");
							}else{
								appointmentVO.setMaxDate(obj[1]!=null?obj[1].toString():"");
							}
							appointmentVO.setMinDateCheck(appointmentVO.getMinDateCheck()+1l);
							
						}
					}
				}
			}
			
			
			
			//appointment related candidates.
			
			if(appointments!=null && appointments.size()>0){
				List<Object[]> candiList = appointmentCandidateRelationDAO.getAppointmentRelatedCandidates(appointments);
				
				if(candiList !=null && candiList.size()>0){
					for(Object[] obj : candiList){
						AppointmentDetailsVO appointmentVO = appointmentsMap.get((Long)obj[0]);
						
						if(appointmentVO!=null){
							
							if(appointmentVO.getSubMap()==null){
								appointmentVO.setSubMap(new HashMap<Long,AppointmentDetailsVO>(0));
							}
							AppointmentDetailsVO candidateVO = new AppointmentDetailsVO();
							candidateVO.setCandidateId(obj[1]!=null?(Long)obj[1]:0l);
							candidateVO.setName(obj[2]!=null?obj[2].toString():"");
							if(obj[3]!=null){
								candidateVO.setCadre(true);
							}
							candidateVO.setMobileNo(obj[4]!=null?obj[4].toString():"");
							candidateVO.setDesignation(obj[5]!=null?obj[5].toString():"");
							candidateVO.setConstituency(obj[6]!=null?obj[6].toString():"");
							candidateVO.setStatusList(setStatusList(statList));
							appointmentVO.getSubMap().put(candidateVO.getCandidateId(),candidateVO);
							candidateVO.setImageUrl(obj[7]!=null?obj[7].toString():"");
							
							//candidateIds
							candidateIds.add(candidateVO.getCandidateId());
						}
					}
				}
			}
			
			//candidate prevoius info.
			List<Long> candidates = null;
			if(candidateIds!=null && candidateIds.size()>0){
				candidates = new ArrayList<Long>(candidateIds);
			}
			
			if(candidates!=null && candidates.size()>0){
				List<Object[]> candidPreviousDetails =appointmentCandidateRelationDAO.getCandidatePreviousApptDetails1(candidates,apptUserId);
				if(candidPreviousDetails !=null && candidPreviousDetails.size()>0){
					
					for(Object[] obj : candidPreviousDetails){
						
						Long candidateId  = obj[0]!=null?(Long)obj[0]:0l;
						Long appointmentId = obj[1]!=null?(Long)obj[1]:0l;
						Long status      = obj[3]!=null?(Long)obj[3]:0l;
						
						if(candidateId != 0l){
							
							//set the data to candidate.
							for (Map.Entry<Long, AppointmentDetailsVO> entry : appointmentsMap.entrySet()) {
								
									AppointmentDetailsVO appointmentVO = entry.getValue();
									
									if (appointmentVO.getSubMap()!=null && appointmentVO.getSubMap().size()>0){
											AppointmentDetailsVO candidateVO = appointmentVO.getSubMap().get(candidateId);
											if(candidateVO !=null){
											
												if(!appointmentId.equals(entry.getKey().longValue())){
													
													if(candidateVO.getSubList() == null){
												    	candidateVO.setSubList(new ArrayList<AppointmentDetailsVO>());
												    }
													AppointmentDetailsVO apptvo = new AppointmentDetailsVO();
													apptvo.setAppointmentId(appointmentId);
													Date dateStr = obj[2]!=null?(Date)obj[2]:null;
														if(dateStr !=null){
															apptvo.setDateString(prefer.format(dateStr));
														}		
													apptvo.setStatus(obj[4]!=null?obj[4].toString():"");
													apptvo.setAptUniqueCode(obj[9]!=null?obj[9].toString():"");
													
													List<Long> aptmnts = new ArrayList<Long>();
													aptmnts.add(apptvo.getAppointmentId());
													
													//Prefer Dates Scenario For History start
													
														apptvo = setPreferebleDatesToAppointment(aptmnts,apptvo);
													
													//Prefer Dates Scenario For History End
													
													if(obj[7]!=null){
														
														Date startDate = (Date)obj[7];
														Date  endDate=   obj[8]!=null?(Date)obj[8]:null;
														String startDateStr = sdf1.format(startDate);
														String endDateStr   = sdf1.format(endDate);
														if(status==2l){
															apptvo.setApptStatus("Appt Fixed on "+startDateStr +" to "+endDateStr.split(" ")[3]+" "+endDateStr.split(" ")[4]);
														}else if(status==3l){
															apptvo.setApptStatus("Attended at "+startDateStr);
														}else if(status==4l){
															apptvo.setApptStatus("Not Attended at "+startDateStr);
														}
														
													}else if(obj[5]!=null){
														Date startDate = (Date)obj[5];
														Date  endDate=   obj[6]!=null?(Date)obj[6]:null;
														String startDateStr = sdf1.format(startDate);
														String endDateStr   = sdf1.format(endDate);
														
														if(status==1){
															apptvo.setApptStatus(" waiting from "+startDateStr);
														}else if(status==5l){
															apptvo.setApptStatus(" rescheduled at "+endDateStr);
														}else if(status==6l){
															apptvo.setApptStatus(" cancelled on "+endDateStr);
														}
														
													}
													
													candidateVO.getSubList().add(apptvo);
													
													IdNameVO statusVO = getMatchedVo(candidateVO.getStatusList(),status);
													if(statusVO!=null){
														statusVO.setActualCount(statusVO.getActualCount() + 1l);
													}
													candidateVO.setRequestCount(candidateVO.getRequestCount()+1l);
												}
											}
									 }
							  }
						}
						
					}
				}
			}
			
			//does label has already elements.
			if (labelId != null && labelId > 0l && appointments!=null && appointments.size()>0){
				
				List<Object[]> labelAppointmentsList = labelAppointmentDAO.checkLabelWithAppointment(labelId,appointments);
				
				if(labelAppointmentsList!=null && labelAppointmentsList.size()>0){
					
					for(Object[] obj : labelAppointmentsList){
						
						if(obj[2]!=null){
							AppointmentDetailsVO appointmentVO = appointmentsMap.get((Long)obj[2]);
							if(appointmentVO!=null){
								appointmentVO.setLabeled(true);	
							}
						}
					}
					
				}
				
			}
			
			//get last visits by candidates.
			if(candidates!=null && candidates.size()>0){
				List<Object[]> lastVisitList = appointmentCandidateRelationDAO.getLastVisitsByCandidates(candidates,apptUserId);
				if(lastVisitList!=null && lastVisitList.size()>0){
					for(Object[] obj : lastVisitList){
						Long candidateId  = obj[0]!=null?(Long)obj[0]:0l;
						
						for (Map.Entry<Long, AppointmentDetailsVO> entry : appointmentsMap.entrySet()) {
							
								AppointmentDetailsVO appointmentVO = entry.getValue();
								
								if (appointmentVO.getSubMap()!=null && appointmentVO.getSubMap().size()>0){
										AppointmentDetailsVO candidateVO = appointmentVO.getSubMap().get(candidateId);
										if(candidateVO !=null){
										
											candidateVO.setLastVisit(obj[1]!=null?sdf1.format((Date)obj[1]):"");
										}
								 }
						  }
					}
				}
			}
			
			
			
			if(appointmentsMap!=null && appointmentsMap.size()>0){
				for (Map.Entry<Long, AppointmentDetailsVO> entry : appointmentsMap.entrySet()) {
					
					AppointmentDetailsVO appointmentVO = entry.getValue();
					if(appointmentVO.getSubMap()!=null && appointmentVO.getSubMap().size()>0){
						appointmentVO.setSubList(new ArrayList<AppointmentDetailsVO>(appointmentVO.getSubMap().values()));
						appointmentVO.getSubMap().clear();
					}
				}
				
				finalList.addAll(appointmentsMap.values());
				appointmentsMap.clear();
			}
			
			if(callFrom.equalsIgnoreCase("timeSlot")){
				removeTimeSlotExistedAppointments(labelId,finalList,appointmentsMap);
			}
			
			if(callFrom.equalsIgnoreCase("print") && finalList != null && finalList.size() > 0)
			{
				String pdfPath = pdfViewForAppointment(finalList,labelName);
				finalList.get(0).setPdfPath(pdfPath);
			}
		} catch (Exception e) {
			LOG.error("Exception raised at viewAppointmentsOfALable",e);
		}
		return finalList;
	}
	 
	 public String pdfViewForAppointment(List<AppointmentDetailsVO> resultList,String labelName)
	 {
		 try{
			 if(resultList != null && resultList.size() > 0)
			 {
				 String randomNum =  UUID.randomUUID().toString();
				 String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
				 String PdfReports = "appointmentPdf";
				 String destPath = IConstants.STATIC_CONTENT_FOLDER_URL+pathSeperator + PdfReports + pathSeperator + randomNum+".pdf";
				 File destFile = new File(destPath);
					 if (!destFile.exists()) 
						 destFile.createNewFile();
					FileOutputStream out = new FileOutputStream(destPath);
					Document document = new Document();
					PdfWriter writer = PdfWriter.getInstance(document, out);
					ParagraphBorder border = new ParagraphBorder();
				    writer.setPageEvent(border);
					document.open();
					
					
					 Paragraph titleParagraph = new Paragraph();
					 Font font = new Font(FontFamily.TIMES_ROMAN, 8, Font.BOLD, new BaseColor(0, 0, 0)); 
					 titleParagraph.add(new Chunk(labelName +" Appointment Members"));
					 titleParagraph.setAlignment(Element.ALIGN_CENTER);
					 titleParagraph.setFont(font);
					 titleParagraph.setSpacingAfter(10);
					 titleParagraph.setFont(font);
					 document.add(titleParagraph);
					 int tableWidth = 5;
					 float[] columnWidths = new float[tableWidth];
					 
					 int rowspan = 0;
					 int colSpan = 0;
					 columnWidths = new float[]{4f, 3f, 3f, 3f,3f};
					 
					 for(AppointmentDetailsVO vo : resultList)
					 {
						 	
						 	
						 	border.setActive(true);
						 	Paragraph p = new Paragraph();
						  
						   //document.add(new Paragraph(p));
						  
						   Font f = new Font(FontFamily.TIMES_ROMAN, 1, Font.NORMAL, new BaseColor(0, 0, 0));
						   p.add("Appointment ID : "+vo.getAptUniqueCode()  + " ");
						   p.add(new Chunk("  "));
						   p.add(new Chunk("  "));
						   
						   if(vo.getPriority() != null && vo.getPriority().length() > 0)
							   p.add(new Chunk("Priority : "+vo.getPriority() + " "));
						   else
							   p.add(new Chunk("Priority : -") + " ");  
						   
						   p.add(new Chunk("  "));p.add(new Chunk("  "));
						   
						   if(vo.getDateString() != null && vo.getDateString().length() > 0)
							   p.add(new Chunk("Requested Date : "+vo.getDateString()+ " "));
						   else
							   p.add(new Chunk("Requested Date : - ")+ " ");  
						   
						   if(vo.getStatus() != null && vo.getStatus().length() > 0)
							   p.add(new Chunk("Status : "+vo.getStatus()+ " "));
						   else
							   p.add(new Chunk("Status : - ")+ " ");  
						   p.add(Chunk.NEWLINE);
						  
						   if(vo.getSubject() != null && vo.getSubject().length() > 0)
							   p.add(new Chunk("Purpose : "+vo.getSubject()));
						   else
							   p.add(new Chunk("Purpose : -")); 
						   p.setFont(f);
						  
						  // document.add(p);
						  // border.setActive(false);
						  if(vo.getSubList() != null && vo.getSubList().size() > 0)
						  {
						  for(AppointmentDetailsVO subVo : vo.getSubList())
						 {
							   PdfPTable table = new PdfPTable(columnWidths);
							
								 //special font sizes
								   Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 8, Font.BOLD, new BaseColor(0, 0, 0)); 
								   Font bf12 = new Font(FontFamily.TIMES_ROMAN, 6); 
								   document.add(Chunk.NEWLINE );
							 //insert column headings
							   
							   insertCell(table, "", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan); 
							   insertCell(table, "APPOINTMENT ID", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan);
							   insertCell(table, "CREATED DATE", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan);
							   insertCell(table, "APPOINTMENT PREFERABLE DATES", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan); 
							   insertCell(table, "STATUS", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan); 
							 StringBuffer sb = new StringBuffer();
							 if(subVo.isCadre() == true)
							 sb.append(subVo.getName() +"- Cadre  "+"\n");
							 else
							  sb.append(subVo.getName()+"\n");
							 if(subVo.getMobileNo() != null && subVo.getMobileNo().length() > 0)
							 sb.append("Contact Number: " +subVo.getMobileNo()+"\n"); 
							 else
								 sb.append("Contact Number: - "+"\n" );  
							 if(subVo.getDesignation() != null && subVo.getDesignation().length() > 0)
								 sb.append("Designation: " +subVo.getDesignation()+"\n"); 
								 else
									 sb.append("Designation: - "+"\n" );  
							 if(subVo.getConstituency() != null && subVo.getConstituency().length() > 0)
								 sb.append("Constituency : " +subVo.getConstituency()+"\n"); 
								 else
							 sb.append("Constituency : - "+"\n" ); 
							 sb.append(subVo.getName()+"\n");
							 if(subVo.getSubList() != null && subVo.getSubList().size() > 0)
								 insertCell(table, sb.toString(), Element.ALIGN_CENTER, colSpan, bf12,subVo.getSubList().size());
							   else
							   {
								   insertCell(table, sb.toString(), Element.ALIGN_CENTER, colSpan, bf12,0); 
								   // add a couple of blank lines
									 insertCell(table, "", Element.ALIGN_CENTER, colSpan, bf12,0);
									 insertCell(table, "", Element.ALIGN_CENTER, colSpan, bf12,0);
									 insertCell(table, "", Element.ALIGN_CENTER, colSpan, bf12,0);
									 insertCell(table, "", Element.ALIGN_CENTER, colSpan, bf12,0);
									
							   }
							 if(subVo.getSubList() != null && subVo.getSubList().size() > 0)
							 {
								 for(AppointmentDetailsVO aptVo : subVo.getSubList())
								 {
									 // add a couple of blank lines
									 insertCell(table, aptVo.getAptUniqueCode(), Element.ALIGN_CENTER, colSpan, bf12,0);
									 insertCell(table, aptVo.getDateString(), Element.ALIGN_CENTER, colSpan, bf12,0);
									 if(aptVo.getDateTypeId() != null && aptVo.getDateTypeId() > 1)
										 insertCell(table, aptVo.getDateType() +":" + aptVo.getMinDate() +"-"+aptVo.getMaxDate(), Element.ALIGN_CENTER, colSpan, bf12,0);
									 if(aptVo.getDateTypeId() != null && aptVo.getDateTypeId() == 1)
										 insertCell(table, aptVo.getApptpreferableDates(), Element.ALIGN_CENTER, colSpan, bf12,0);
									 else
									 insertCell(table, "", Element.ALIGN_CENTER, colSpan, bf12,0);
									 insertCell(table, aptVo.getStatus(), Element.ALIGN_CENTER, colSpan, bf12,0);
									 
								 }
							 }
							 p.add(table);
							 document.add(p); 
							// document.add(table); 
							
						 }
						  }
						  else
						  {
							  PdfPTable table = new PdfPTable(columnWidths);
							//  border.setActive(true);
								 //special font sizes
								   Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 8, Font.BOLD, new BaseColor(0, 0, 0)); 
								   Font bf12 = new Font(FontFamily.TIMES_ROMAN, 6); 
								   document.add(Chunk.NEWLINE );
							 //insert column headings
							   
							   insertCell(table, "", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan); 
							   insertCell(table, "APPOINTMENT ID", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan);
							   insertCell(table, "CREATED DATE", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan);
							   insertCell(table, "APPOINTMENT PREFERABLE DATES", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan); 
							   insertCell(table, "STATUS", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan); 
							   insertCell(table, "No Data Available ", Element.ALIGN_CENTER, 3, bf12,0);
							   //document.add(table); 
							   p.add(table);
							   document.add(p); 
						  }
						  //border.setActive(false);
					 }
					 
						border.setActive(false);
					 document.close();
					  return randomNum+".pdf"; 
					 
				 
			 }
		 }
		 catch(Exception e)
		 {
			 LOG.error("Exception raised at pdfViewForAppointment",e); 
		 }
		return null;
	 }
	 
	 private void insertCell(PdfPTable table, String text, int align, int colspan, Font font,int rowspan){
		  
		  //create a new cell with the specified Text and Font
		  PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
		  
		  if(rowspan > 0)
		   cell.setRowspan(rowspan);
		  //set the cell alignment
		  cell.setHorizontalAlignment(align);
		  //set the cell column span in case you want to merge two or more cells
		  if(colspan > 0)
		   cell.setColspan(colspan);
		  //in case there is no text and you wan to create an empty row
		  if(text.trim().equalsIgnoreCase("")){
		   cell.setMinimumHeight(10f);
		  }
		  //add the call to the table
		  table.addCell(cell);
		  
		 }
		public void removeTimeSlotExistedAppointments(Long labelId,List<AppointmentDetailsVO> finalList,Map<Long, AppointmentDetailsVO> appointmentsMap){
			//Getting appointments which are allocating to the TimeSlot 
			List<Object[]> timeSlotAptList = labelAppointmentDAO.getViewAppointmentsOfALable(labelId);
			
			List<Long> timeSlotApntmenttList = new ArrayList<Long>();
			if(timeSlotAptList !=null && timeSlotAptList.size()>0){				
				appointmentsMap = new LinkedHashMap<Long, AppointmentDetailsVO>();				
				for(Object[]  obj: timeSlotAptList){				
					timeSlotApntmenttList.add(obj[0]!=null?(Long)obj[0]:0l);				
				}
			}
			
			//removing element from final List If it's already allocated to time slot 
			if(finalList !=null && finalList.size()>0){
				
				List<AppointmentDetailsVO> duplicateList = new ArrayList<AppointmentDetailsVO>();
				
				duplicateList.addAll(finalList);								
				
				for (AppointmentDetailsVO aptntmnt : finalList) {					
					if(timeSlotAptList !=null && timeSlotAptList.size()>0){						
						for(Long apt :timeSlotApntmenttList){							
							if(aptntmnt.getAppointmentId().equals(apt)){
								duplicateList.remove(aptntmnt);
							}							
						}
						
					}					
				}
				finalList.clear();
				finalList.addAll(duplicateList);
			}
		} 
			
		public List<IdNameVO> getAppointmentsLabelStatus(){
			List<IdNameVO> labelList = new ArrayList<IdNameVO>();
			try{
				List<Object[]> list=appointmentLabelStatusDAO.getAppmntLblStatusList();
				labelList = setDataToVO(list);
			}catch(Exception e){
				LOG.error("Exception raised at getAppointmentsLabelStatus() method of AppointmentService",e);
			}
			return labelList;
		}
		public List<IdNameVO> getAppointmentCreatedUsers(){
			List<IdNameVO> idNameVoList = new ArrayList<IdNameVO>();
			try{
				List<Object[]> list=appointmentDAO.getAppointmentCreatedUsers();
				if(list != null && list.size() > 0)
				{
					for(Object[] params :list)
						
					{
					IdNameVO vo = new IdNameVO();
					vo.setId((Long)params[0]);
					String fname = params[1] != null ? params[1].toString() : "";
					String lname = params[2] != null ? params[2].toString() : "";
					vo.setName(fname +""+lname);
					idNameVoList.add(vo);
					}
				}
				//labelList = setDataToVO(list);
			}catch(Exception e){
				
				LOG.error("Exception raised at getAppointmentCreatedUsers() method of AppointmentService",e);
			}
			return idNameVoList;
		}
		
		
		public ResultStatus updateAppointmentsLabelStatus(Long labelId,Long labelstatusId) {
			   
				ResultStatus status=new ResultStatus();
				try{
					
					 Integer updateCount=appointmentLabelDAO.updateAppointmentsLabelStatus(labelId,labelstatusId);	
					 if(updateCount!=null && updateCount>0){
						 status.setMessage("success");
					 }else{
						 status.setMessage("fail");
					 }
					
				}catch(Exception e){
					LOG.error("Exception raised at updateAppointmentsLabelStatus() method of AppointmentService", e);
				}
				return status;
			}
		 
		 	public ResultStatus updateMemberAppointmentsStatus(final Long apptId,final Long statusId) {
			   
				ResultStatus status=new ResultStatus();
				try{

					transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				        	appointmentLabelDAO.updateMemberAppointmentsStatus(apptId,statusId);
				        	//saveAppointmentTrackingDetails(apptId,4l,statusId,appointmentDAO.get(apptId).getAppointmentUserId(),"");
				        }
				   });
					
					status.setMessage("success");
					status.setResultCode(0);
				}catch(Exception e){
					 status.setMessage("fail");
					 status.setResultCode(1);
					LOG.error("Exception raised at updateAppointmentsLabelStatus() method of AppointmentService", e);
				}
				return status;
			}
			@SuppressWarnings("unused")
			  public ResultStatus setTimeSlotForAppointment(Long appointmentId,String dateStr,String fromTime,String toTime,Long userId,String type,Long timeSlotId,String commentTxt){
			    ResultStatus rs = new ResultStatus();
			    try {
			      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			      Date date = sdf.parse(dateStr);
			      
			       SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
			           SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
			           
			           SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			        
			           
			           Date dateFromTime = null;
			           Date dateToTime =null;
			           
			           Date appointmentFromTime = null;
			           Date appointmentToTime =null;
			           
			           if(fromTime !=null && !fromTime.isEmpty()){
			             dateFromTime = parseFormat.parse(fromTime);
			             fromTime = dateStr + " " +displayFormat.format(dateFromTime);
			           }
			           if(toTime !=null && !toTime.isEmpty()){
			             dateToTime = parseFormat.parse(toTime);
			             toTime = dateStr + " " +displayFormat.format(dateToTime);
			           }
			       
			           if(fromTime !=null && toTime !=null){
			              appointmentFromTime = sdf1.parse(fromTime);
			              appointmentToTime = sdf1.parse(toTime);
			           }
			      
			           AppointmentTimeSlot timeSlot = null;
			           if(type !=null && type.equalsIgnoreCase("update")){
			             timeSlot = appointmentTimeSlotDAO.get(timeSlotId);
			           }else{
			             timeSlot = new AppointmentTimeSlot();
			             timeSlot.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			           }
			           
				       timeSlot.setAppointmentId(appointmentId);
				       timeSlot.setDate(date);
				       timeSlot.setFromDate(appointmentFromTime);
				       timeSlot.setToDate(appointmentToTime);
				       timeSlot.setInsertedBy(userId);
				       timeSlot.setUpdatedBy(userId);
				       timeSlot.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				       timeSlot.setIsDeleted("N");
			      
				       //timeSlot=appointmentTimeSlotDAO.save(timeSlot);
				       
				       AppointmentTimeSlot appointmentTimeSlot = appointmentTimeSlotDAO.save(timeSlot);
				       
				       getappointmentComments(appointmentTimeSlot.getAppointmentId(),IConstants.APPOINTMENT_STATUS_FIXED,commentTxt,userId);
				       
				       Integer updtdSts = appointmentDAO.updateAppntmntStatusById(appointmentTimeSlot.getAppointmentId(), dateUtilService.getCurrentDateAndTime() );
				       
				       List<String>  mobilenos = appointmentCandidateRelationDAO.getAppointmentIdsforSendSms(appointmentTimeSlot.getAppointmentId());
				       if(mobilenos !=null && mobilenos.size()>0){
				    	   for(String obj : mobilenos){
				    		   	 cadreRegistrationService.sendSMS(obj,"Your Appointment Fixed on " +" "+new SimpleDateFormat("yyyy-MM-dd").format(appointmentTimeSlot.getDate())+" " +"From"+" " +new SimpleDateFormat("HH:mm").format(appointmentTimeSlot.getFromDate())+" " +"To"+" "+new SimpleDateFormat("HH:mm").format(appointmentTimeSlot.getToDate()));
				    		  	
				    	   }
				       }
				       
				      /*if(type !=null && type.equalsIgnoreCase("update")){
			        	  saveAppointmentTrackingDetails(timeSlot.getAppointmentId(),6l,appointmentDAO.get(appointmentId).getAppointmentStatusId(),userId,"");
			         }else{
			          	  saveAppointmentTrackingDetails(timeSlot.getAppointmentId(),5l,appointmentDAO.get(appointmentId).getAppointmentStatusId(),userId,"");
			         }*/
				      
				      
				     //void appt status
				       List<Object[]> candiList=appointmentCandidateRelationDAO.getApptCandidIdsAndInsertedTime(appointmentId);
					      
					      List<Long> apptCandiIds = null;
					      Date insertedDate = null;
					      if(candiList!=null && candiList.size()>0){
					    	  insertedDate = candiList.get(0)[1]!=null?(Date)candiList.get(0)[1]:null;
					    	  apptCandiIds = new ArrayList<Long>();
					    	  for(Object[] obj : candiList) {
					    		  apptCandiIds.add(obj[0]!=null ?(Long)obj[0]:0l);
					    	  }
					      }
					      
					      Map<Long,List<Long>> apptIdsMap = new HashMap<Long,List<Long>>();
					      
						 if(apptCandiIds!=null && apptCandiIds.size()>0){
							 
							int apptCandicount = apptCandiIds.size();
							
							List<Object[]> apptWithCandiIdsList = appointmentCandidateRelationDAO.checkApptsAsVoid(insertedDate,IConstants.APPOINTMENT_STATUS_WAITING,apptCandiIds);
							if(apptWithCandiIdsList!=null && apptWithCandiIdsList.size()>0){
								
								for(Object[] obj : apptWithCandiIdsList){
									
									List<Long> candiIds= apptIdsMap.get((Long)obj[0]);
									
									if(candiIds==null){
										candiIds = new ArrayList<Long>();
										candiIds.add((Long)obj[1]);
										apptIdsMap.put((Long)obj[0], candiIds);
									}else{
										candiIds.add((Long)obj[1]);
									}
								}
							}
							
							List<Long> apptIds = new ArrayList<Long>();
							if(apptIdsMap!=null && apptIdsMap.size()>0){
								
								for (Map.Entry<Long,List<Long>> entry : apptIdsMap.entrySet())
								{
									List<Long>  candiIds = entry.getValue();
									if(candiIds!=null && candiIds.size()>0 ){
										
										if( apptCandiIds.containsAll(candiIds) && apptCandicount==candiIds.size()){
											apptIds.add(entry.getKey());
										}
										
									}
								}
							}
							
							if(apptIds!=null && apptIds.size()>0){
								appointmentDAO.updateApptStatusbyApptIds(apptIds,new DateUtilService().getCurrentDateAndTime(),IConstants.APPOINTMENT_STATUS_VOID);
							}
						 }
				      
				      
			      rs.setExceptionMsg("success");
			      rs.setResultCode(0);
			      
			    } catch (Exception e) {
			      LOG.error("Exception raised in setTimeSlotForAppointment", e);
			      rs.setExceptionMsg("failure");
			      rs.setResultCode(1);
			    }
			    return rs;
			  }
		 public List<AppointmentDetailsVO> getViewAppointmentsOfALable(Long labelId){
			   List<AppointmentDetailsVO> finalList = new ArrayList<AppointmentDetailsVO>(0);
			try {
				
				Set<Long> appointmentIds = new HashSet<Long>(0);
				Set<Long> candidateIds = new HashSet<Long>(0);
				
				List<Object[]> statList = appointmentStatusDAO.getAppointmentStatusList();
				
				Map<Long,AppointmentDetailsVO> appointmentsMap = null;
				
				//List<Object[]>   list = appointmentCandidateRelationDAO.getAppointmentsBySearchCriteria(designationId,priorityId,statusId,districtId,constituencyid);
				List<Object[]> list = labelAppointmentDAO.getViewAppointmentsOfALable(labelId);
				if(list !=null && list.size()>0){
					
					appointmentsMap = new LinkedHashMap<Long, AppointmentDetailsVO>();
					
					for(Object[]  obj: list){
						AppointmentDetailsVO appointment =new AppointmentDetailsVO();
						appointment.setAppointmentId(obj[0]!=null?(Long)obj[0]:0l);
						appointment.setSubject(obj[3]!=null?obj[3].toString():"");
						appointment.setPriority(obj[2]!=null?obj[2].toString():"");
						appointment.setStatus(obj[5]!=null?obj[5].toString():"");
						//appointment.setDateString(obj[8]!=null?obj[8].toString():"");
						appointment.setFromDateStr(obj[8]!=null?obj[8].toString():"");
						appointment.setToDateStr(obj[9]!=null?obj[9].toString():"");
						appointment.setUserName(obj[10] !=null ? obj[10].toString():"");
						appointment.setTimeSlotId(obj[11]!=null?(Long)obj[11]:0l);
						appointmentsMap.put(appointment.getAppointmentId(),appointment);
						
						//appointmentIds
						appointmentIds.add(appointment.getAppointmentId());
					}
				}
				
				
				List<Long> appointments = null;
				if(appointmentIds!=null && appointmentIds.size()>0){
					appointments = new ArrayList<Long>(appointmentIds);
				}
				
				//get dates for appointments.
				if(appointments!=null && appointments.size()>0){
					
					List<Object[]>  apptDateslist = appointmentPreferableDateDAO.getMultipleDatesforAppointments(appointments);
					if(apptDateslist!=null && apptDateslist.size()>0){
						for(Object[] obj : apptDateslist){
							AppointmentDetailsVO   appointmentVO = appointmentsMap.get((Long)obj[0]);
							if(appointmentVO.getApptpreferableDates()==null){
								appointmentVO.setApptpreferableDates(obj[1]!=null?obj[1].toString():"");
							}else{
								appointmentVO.setApptpreferableDates(appointmentVO.getApptpreferableDates() + " , " + (obj[1]!=null?obj[1].toString():"") );
							}
						}
					}
				}
				
				
				
				//appointment related candidates.
				
				if(appointments!=null && appointments.size()>0){
					List<Object[]> candiList = appointmentCandidateRelationDAO.getAppointmentRelatedCandidates(appointments);
					
					if(candiList !=null && candiList.size()>0){
						for(Object[] obj : candiList){
							AppointmentDetailsVO appointmentVO = appointmentsMap.get((Long)obj[0]);
							
							if(appointmentVO!=null){
								
								if(appointmentVO.getSubMap()==null){
									appointmentVO.setSubMap(new HashMap<Long,AppointmentDetailsVO>(0));
								}
								AppointmentDetailsVO candidateVO = new AppointmentDetailsVO();
								candidateVO.setCandidateId(obj[1]!=null?(Long)obj[1]:0l);
								candidateVO.setName(obj[2]!=null?obj[2].toString():"");
								if(obj[3]!=null){
									candidateVO.setCadre(true);
								}
								candidateVO.setMobileNo(obj[4]!=null?obj[4].toString():"");
								candidateVO.setDesignation(obj[5]!=null?obj[5].toString():"");
								candidateVO.setConstituency(obj[6]!=null?obj[6].toString():"");
								candidateVO.setStatusList(setStatusList(statList));
								appointmentVO.getSubMap().put(candidateVO.getCandidateId(),candidateVO);
								candidateVO.setImageUrl(obj[7]!=null?obj[7].toString():"");
								
								//candidateIds
								candidateIds.add(candidateVO.getCandidateId());
							}
						}
					}
				}
				
				//candidate prevoius info.
				List<Long> candidates = null;
				if(candidateIds!=null && candidateIds.size()>0){
					candidates = new ArrayList<Long>(candidateIds);
				}
				
				if(candidates!=null && candidates.size()>0){
					List<Object[]> candidPreviousDetails =appointmentCandidateRelationDAO.getCandidatePreviousApptDetails(candidates);
					if(candidPreviousDetails !=null && candidPreviousDetails.size()>0){
						
						for(Object[] obj : candidPreviousDetails){
							
							Long candidateId  = obj[0]!=null?(Long)obj[0]:0l;
							Long appointmentId = obj[1]!=null?(Long)obj[1]:0l;
							Long status      = obj[3]!=null?(Long)obj[3]:0l;
							
							if(candidateId != 0l){
								
								//set the data to candidate.
								for (Map.Entry<Long, AppointmentDetailsVO> entry : appointmentsMap.entrySet()) {
									
										AppointmentDetailsVO appointmentVO = entry.getValue();
										
										if (appointmentVO.getSubMap()!=null && appointmentVO.getSubMap().size()>0){
												AppointmentDetailsVO candidateVO = appointmentVO.getSubMap().get(candidateId);
												if(candidateVO !=null){
												
													if(!appointmentId.equals(entry.getKey().longValue())){
														
														if(candidateVO.getSubList() == null){
													    	candidateVO.setSubList(new ArrayList<AppointmentDetailsVO>());
													    }
														AppointmentDetailsVO apptvo = new AppointmentDetailsVO();
														apptvo.setAppointmentId(appointmentId);
														apptvo.setDateString(obj[2]!=null?obj[2].toString():"");
														apptvo.setStatus(obj[4]!=null?obj[4].toString():"");
														candidateVO.getSubList().add(apptvo);
														
														IdNameVO statusVO = getMatchedVo(candidateVO.getStatusList(),status);
														if(statusVO!=null){
															statusVO.setActualCount(statusVO.getActualCount() + 1l);
														}
														candidateVO.setRequestCount(candidateVO.getRequestCount()+1l);
													}
												}
										 }
								  }
							}
							
						}
					}
				}
				
				//does label has already elements.
				if (labelId != null && labelId > 0l && appointments!=null && appointments.size()>0){
					
					List<Object[]> labelAppointmentsList = labelAppointmentDAO.checkLabelWithAppointment(labelId,appointments);
					
					if(labelAppointmentsList!=null && labelAppointmentsList.size()>0){
						
						for(Object[] obj : labelAppointmentsList){
							
							if(obj[2]!=null){
								AppointmentDetailsVO appointmentVO = appointmentsMap.get((Long)obj[2]);
								if(appointmentVO!=null){
									appointmentVO.setLabeled(true);	
								}
							}
						}
						
					}
					
				}
				
				
				if(appointmentsMap!=null && appointmentsMap.size()>0){
					for (Map.Entry<Long, AppointmentDetailsVO> entry : appointmentsMap.entrySet()) {
						
						AppointmentDetailsVO appointmentVO = entry.getValue();
						if(appointmentVO.getSubMap()!=null && appointmentVO.getSubMap().size()>0){
							appointmentVO.setSubList(new ArrayList<AppointmentDetailsVO>(appointmentVO.getSubMap().values()));
							appointmentVO.getSubMap().clear();
						}
					}
					
					finalList.addAll(appointmentsMap.values());
					appointmentsMap.clear();
				}
				
				
				
				
			} catch (Exception e) {
				LOG.error("Exception raised in getViewAppointmentsOfALable", e);
			}
			return finalList;
		}
	
	
	public ResultStatus updateAppointmentStatus(AppointmentUpdateStatusVO inputVO,Long userId)
	{
		ResultStatus result = new ResultStatus();
		try{
			
			Appointment appointment = appointmentDAO.get(inputVO.getAppointmentId());
			appointment.setAppointmentStatusId(inputVO.getStatusId());;
			appointment.setUpdatedBy(userId);
			appointmentDAO.save(appointment);
			if(inputVO.isIssmsChecked())
			{
				 List<Object[]> list = appointmentCandidateRelationDAO.getAppointmentCandidateMobileNos(inputVO.getAppointmentId());
				 if(list != null && list.size() > 0)
				 {
					 for(Object[] params : list)
					 {
						 if(params[2] != null && !params[2].toString().isEmpty())
						 cadreRegistrationService.sendSMS(params[2].toString(), inputVO.getSmsText()); 
					 }
				 }
				
			}
			
			getappointmentComments(inputVO.getAppointmentId(),IConstants.APPOINTMENT_STATUS_FIXED,inputVO.getCommented(),userId);
			
			result.setMessage("success");
		}
		catch (Exception e) {
			LOG.error("Exception raised in updateAppointmentStatus", e);
			result.setMessage("fail");
			
		}
		return result;
	}
	
	public ResultStatus sendSmsForAppointment(AppointmentUpdateStatusVO inputVO)
	{
		ResultStatus result = new ResultStatus();
		try{
			     List<Object[]> list = appointmentCandidateRelationDAO.getAppointmentCandidateMobileNos(inputVO.getAppointmentId());
				 if(list != null && list.size() > 0)
				 {
					 for(Object[] params : list)
					 {
						 if(params[2] != null && !params[2].toString().isEmpty())
						 cadreRegistrationService.sendSMS(params[2].toString(), inputVO.getSmsText()); 
					 }
				 }
				
			
			result.setMessage("success");
		}
		catch (Exception e) {
			LOG.error("Exception raised in sendSmsForAppointment", e);
			result.setMessage("fail");
			
		}
		return result;
	}
	
	public ResultStatus updateAllAppointmentStatusByType(AppointmentUpdateStatusVO statusinputVo,AppointmentInputVO inputVo,Long userId)
	{
		ResultStatus result = new ResultStatus();
		try{
			
			List<AppointmentScheduleVO> list =  getAppointmentSearchDetails(inputVo);
			if(list != null && list.size() > 0)
			{
				for(AppointmentScheduleVO vo : list)
				{
					if(vo.getScheduleType().equalsIgnoreCase(statusinputVo.getAppointmentType()))
					{
						Appointment appointment = appointmentDAO.get(vo.getAppointmentId());
						appointment.setAppointmentStatusId(statusinputVo.getStatusId());;
						appointment.setUpdatedBy(userId);
						appointmentDAO.save(appointment);
					}
				}
			}
			result.setMessage("success");
		}
		catch (Exception e) {
			LOG.error("Exception raised in updateAppointmentStatus", e);
			result.setMessage("fail");
			
		}
		return result;
	}
	
	public ResultStatus deleteAppointmentsOfLabel(final List<Long> ids,final Long labelId,final Long registrationId){
		ResultStatus rs = new ResultStatus();
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
		        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
		        	if(ids != null && ids.size() > 0 && labelId != null && labelId > 0l){
						//move the previous recoeds to history
						List<LabelAppointment> laObjList = labelAppointmentDAO.getAppointmentsOfLabel(ids,labelId);
						if(laObjList != null && laObjList.size() > 0){
							for (LabelAppointment labelAppointment : laObjList) {
								LabelAppointmentHistory lah = new LabelAppointmentHistory();
								lah.setLabelAppointmentId(labelAppointment.getLabelAppointmentId());
								lah.setAppointmentLabelId(labelAppointment.getAppointmentLabelId());
								lah.setAppointmentId(labelAppointment.getAppointmentId());
								lah.setLabelStatusId(labelAppointment.getLabelAppointmentId());
								lah.setCreatedBy(labelAppointment.getCreatedBy());
								lah.setUpdatedBy(labelAppointment.getUpdatedBy());
								lah.setInsertedTime(labelAppointment.getInsertedTime());
								lah.setUpdatedTime(labelAppointment.getUpdatedTime());
								lah.setIsDeleted(labelAppointment.getIsDeleted());
								labelAppointmentHistoryDAO.save(lah);
							}
						}
						
						labelAppointmentDAO.updateIsDeletedStatus(ids,labelId,registrationId,dateUtilService.getCurrentDateAndTime());
						
						for (Long long1 : ids) {
							
							//saveAppointmentTrackingDetails(long1,3l,appointmentDAO.get(long1).getAppointmentStatusId(),registrationId,"");
						}
						
						//changing appt status to waiting
						changeAppointmentStatus(ids,IConstants.APPOINTMENT_STATUS_WAITING);
				        
					}
		        }
			});
			rs.setExceptionMsg("success");
			rs.setResultCode(0);
		} catch (Exception e) {
			rs.setExceptionMsg("failure");
			rs.setResultCode(1);
			LOG.error("Exception raised at deleteAppointmentsOfLabel", e);
		}
		return rs;
	}
	
	public List<LocationWiseBoothDetailsVO> getMandalMunicCorpDetailsOfConstituencies(List<Long> constituencyIds,Long locationScopeId){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		LocationWiseBoothDetailsVO vo = null;
		List<Long> greaterCorpIds = new ArrayList<Long>();
		if(locationScopeId == 6l || locationScopeId == 5l){
			List<SelectOptionVO> locations = regionServiceDataImp.getAllMandalsByAllConstituencies(constituencyIds);
			for(SelectOptionVO location:locations){
		       	vo = new LocationWiseBoothDetailsVO();
		       	vo.setLocationId(Long.valueOf("4"+location.getId()));
		       	vo.setLocationName(location.getName()+" Mandal");
		       	locationsList.add(vo);
		    }
		}else if(locationScopeId == 8l || locationScopeId == 7l){
			List<Object[]> localBodies = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituencyList(constituencyIds);
			   
	        for(Object[] localBodi:localBodies){
	        	Long localBdyId = (Long)localBodi[0];
	        	if(!(localBdyId.longValue() == 20l ||  localBdyId.longValue() == 124l || localBdyId.longValue() == 119l)){
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("5"+localBodi[0].toString()));
		        	vo.setLocationName(localBodi[1].toString() +" "+ localBodi[2].toString());
		        	locationsList.add(vo);
	        	}else{
	        		if(!greaterCorpIds.contains(localBdyId)){
	        			greaterCorpIds.add(localBdyId);
	        			vo = new LocationWiseBoothDetailsVO();
			        	vo.setLocationId(Long.valueOf("5"+localBodi[0].toString()));
			        	vo.setLocationName(localBodi[4].toString());
			        	locationsList.add(vo);
	        		}
	        	}
	        }
		}
		
		return locationsList;
	} 
	 public List<IdNameVO> getDistrictsList(){
			
			List<IdNameVO> districtsList = null;
			try{
				List<Object[]> result = districtDAO.getDistrictsList();
				if(result != null && result.size()  > 0){
					districtsList = new ArrayList<IdNameVO>();
					for(Object[] obj :result){
						IdNameVO vo = new IdNameVO();
						vo.setId((Long)obj[0]);
						vo.setName(obj[1] != null?obj[1].toString():"");	
						districtsList.add(vo);
					}			
				}
			}
			catch (Exception e) {
				LOG.error("Exception in getDistrictsList()",e);	
			}
			return districtsList;
		}
	public List<IdNameVO> getConstituenciesByDistrict(Long districtId)
	{
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try{
			 List<Object[]> list = constituencyDAO.getConstituenciesByDistrictId(districtId);	
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params : list)
				 {
					 IdNameVO vo = new IdNameVO();
					 vo.setId((Long)params[0]);
					 vo.setName(params[1].toString());
					 returnList.add(vo);
				 }
			 }
		}
		catch (Exception e) {
			LOG.error("Exception in getConstituenciesByDistrict()",e);	
		}
		return returnList;
	}
	
	
	public List<IdNameVO> getAllMandalsByConstituencyID(Long constituencyID){

		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		List<Object[]> mandals = constituencyDAO.getTehsilsByConstituency(constituencyID);
		if(mandals != null && mandals.size() > 0)
		{
			for(Object[] obj : mandals){
				IdNameVO objVO = new IdNameVO();
				objVO.setId((Long)obj[0]);
				objVO.setName(obj[1].toString() +" " +"Mandal");
				returnList.add(objVO);
			}
		}
		List<Object[]> localbodies = constituencyDAO.getLocalElectionBodiesByconstituency(constituencyID);
		if(localbodies != null && localbodies.size() > 0)
		{
				for(Object[] obj : localbodies){
					IdNameVO objVO = new IdNameVO();
					objVO.setId((Long)obj[0]);
					objVO.setName(obj[1].toString());
					returnList.add(objVO);
				}
		}
		
		
		return returnList;
	}
	
	
	public List<IdNameVO> getPanchayatDetailsByMandalId(Long tehsilId,String type){
		
		List<IdNameVO> panachatiesList = new ArrayList<IdNameVO>();
		List<Object[]> panchayties=null;
		if(tehsilId !=null ){
			if(type.equalsIgnoreCase("mandal")){
				 panchayties = constituencyDAO.getPanchayatsByTehsilId(Long.valueOf(tehsilId));
			}
			if(type.equalsIgnoreCase("muncipality")){
				 panchayties = constituencyDAO.getWardIdAndName(Long.valueOf(tehsilId));
			}
			if(panchayties !=null ){
				for (Object[] list : panchayties) {
					IdNameVO panchayaties = new IdNameVO();
					panchayaties.setId(Long.valueOf(list[0].toString()));
					panchayaties.setName(list[1].toString());
					panachatiesList.add(panchayaties);
				}
				return panachatiesList;
			}
			else{	
				return null;
			}
		}
		return panachatiesList;
	}
	
	public List<IdNameVO> getAllCandidateTypes(){
		List<IdNameVO> fnlList = new ArrayList<IdNameVO>();
		try{
			
			List<Object[]> candidateTypes = appointmentCandidateTypeDAO.getAllCandidateTypes();
			if(candidateTypes !=null && candidateTypes.size()>0){
				fnlList = setToIdNameList(candidateTypes,fnlList);
			}
			
		}catch (Exception e) {
			LOG.error("Exception in getAllCandidateTypes()",e);	
		}
		return fnlList;
	}
	public List<IdNameVO> setToIdNameList(List<Object[]> listObj,List<IdNameVO> fnlList){
		try{
			
			for (Object[] obj : listObj) {
				IdNameVO vo = new IdNameVO();
				vo.setId(obj[0] !=null ? (Long)obj[0]:0l);
				vo.setName(obj[1] !=null ? obj[1].toString():"");
				fnlList.add(vo);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return fnlList;
	}
	//get appointmentCandidateDesignationList
	public List<IdNameVO> getAppCandidateDesigListByType(Long typeId){
		//appCndDesigList->appointmentCandidateDesignationList
		List<IdNameVO> appCndDesigList = new ArrayList<IdNameVO>(0);
		try{
			LOG.info("Entered into getAppCandidateDesigListByType() method of AppointmentService");
			List<Object[]>  objList = candidateDesignationDAO.getAppCandidateDesigListByType(typeId);
			if(objList != null && objList.size() > 0){
				for (Object[] objects : objList) {
					IdNameVO vo = new IdNameVO();
					vo.setId((Long)objects[0]);
					vo.setName(objects[1].toString());
					vo.setOrderId((Long)objects[2]);
					appCndDesigList.add(vo);
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getAppCandidateDesigList() method of AppointmentService", e);
		}
		return appCndDesigList;
	}
	public  List<IdNameVO> getAppointmentStatusOverview(Long appointmentUserId)
	{
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try{
			
			List<Object[]> list = appointmentCandidateRelationDAO.getAppointmentStatusOverview(appointmentUserId);
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					IdNameVO vo = new IdNameVO();
					vo.setId((Long)params[1]);
					vo.setName(params[2].toString());
					vo.setAvailableCount((Long)params[0]);
					returnList.add(vo);
				}
			}
			
		}
		catch(Exception e)
		{
			LOG.error("Exception raised at getAppointmentStatusOverview() method of AppointmentService", e);
		}
		return returnList;
	}
	public String saveAppointmentTrackingDetails(Long appointmentId,Long appointmentActionId,Long appointmentStatusId,Long userId,String remarks){
		
		AppointmentTracking appointmentTracking=new AppointmentTracking();
		try{
			if(appointmentId!=null && appointmentId>0l){
				appointmentTracking.setAppointmentId(appointmentId);
			}
			if(appointmentStatusId!=null && appointmentActionId>0l){
				appointmentTracking.setAppointmentStatusId(appointmentStatusId);
			}
			if(userId!=null && userId>0l){
				appointmentTracking.setUserId(userId);
			}
			 appointmentTracking.setActionTime(dateUtilService.getCurrentDateAndTime());
			 appointmentTracking.setAppointmentActionId(appointmentActionId);
			if(remarks!=null && !remarks.isEmpty()){
				appointmentTracking.setRemarks(remarks);
			}
			 appointmentTrackingDAO.save(appointmentTracking);
		}catch(Exception e){
			LOG.error("Error occured  in saveAppointmentTrackingDetails()",e);	
			return null;
		}
		return "success";
	}
	
public AppointmentDetailsVO setPreferebleDatesToAppointment(List<Long> aptmnts,AppointmentDetailsVO apptvo){
		
		try{
			
			SimpleDateFormat prefer = new SimpleDateFormat("dd MMM yyyy");
			
			List<Object[]>  apptDates = appointmentPreferableDateDAO.getMultipleDatesforAppointments(aptmnts);
			if(apptDates!=null && apptDates.size()>0){
				for(Object[] object : apptDates){
					//AppointmentDetailsVO   appointmentVO1 = new AppointmentDetailsVO();
					apptvo.setDateTypeId((Long)object[2]);
					apptvo.setDateType(object[3].toString());
					if((Long)object[2]==1l){
						if(apptvo.getApptpreferableDates()==null){
							
							Date preferDate = object[1]!=null?(Date)object[1]:null;
							if(preferDate !=null){
								apptvo.setApptpreferableDates(prefer.format(preferDate));
							}
							
						}else{
							
							Date preferDate = object[1]!=null?(Date)object[1]:null;
							if(preferDate !=null){
								apptvo.setApptpreferableDates(apptvo.getApptpreferableDates() + " , " + (prefer.format(preferDate)) );
							}
							
						}
						
					}else{
						
						if(apptvo.getMinDateCheck() == 0l){	
							Date preferDate = object[1]!=null?(Date)object[1]:null;
							if(preferDate !=null){
								apptvo.setMinDate(prefer.format(preferDate));
								apptvo.setMaxDate(prefer.format(preferDate));
							}
						}else{
							Date preferDate = object[1]!=null?(Date)object[1]:null;
							apptvo.setMaxDate(prefer.format(preferDate));
						}
						apptvo.setMinDateCheck(apptvo.getMinDateCheck()+1l);
						
					}
					
					//prferList.add(appointmentVO1);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return apptvo;
	}
	public List<AppHistoryVO> getAppointmentHistoryForCandidate(Long appointmentCandidateId,Long apptUserId){
		List<AppHistoryVO> historyVoList = new ArrayList<AppHistoryVO>();
		List<Long> appointmentIds = new ArrayList<Long>();
		 List<Object[]> list = appointmentCandidateRelationDAO.getAppointmentHistoryDetailsByCandidateId(appointmentCandidateId,apptUserId);
		 if(list != null && list.size() > 0)
		 {
			 for(Object[] params : list)
			 {
				 AppHistoryVO vo = new AppHistoryVO();
				 vo.setUniqueCode(params[0] != null ? params[0].toString() : "");
				 vo.setPurpose(params[1] != null ? params[1].toString() : "");
				 vo.setCreatedOn(params[2] != null ? params[2].toString() : "");
				 vo.setStatusId(params[3] != null ? (Long)params[3] :0l);
				 vo.setStatus(params[4] != null ? params[4].toString() : "");
				 vo.setId(params[5] != null ? (Long)params[5] : 0l);
				 vo.setConfirmedDate("");
				 vo.setPreferredDate("");
				 if(!appointmentIds.contains(vo.getId()));
				 appointmentIds.add(vo.getId());
				 historyVoList.add(vo);
			 }
			 if(appointmentIds != null && appointmentIds.size() > 0)
			 {
					 List<Object[]> list1 = appointmentTimeSlotDAO.getAppointmentConfirmDates(appointmentIds);
					 if(list1 != null && list1.size()> 0)
					 {
						 for(Object[] params1 : list1)
						 {
							 AppHistoryVO vo = getMathedAppointmentVO(historyVoList,(Long)params1[0]);
							 if(vo != null)
							 {
								 vo.setConfirmedDate(params1[1] != null ? params1[1].toString() : "");
							 }
							 
						 }
						 
					 }
			 		 
			  List<Object[]> list2 =  appointmentPreferableDateDAO.getPreferableDatesforAppointments(appointmentIds);
			  if(list2 != null && list2.size() > 0)
			  {
				  for(Object[] params2 : list2)
					 {
						 AppHistoryVO vo = getMathedAppointmentVO(historyVoList,(Long)params2[0]);
						 if(vo != null)
						 {
							 String minDate = "";
							 String maxDate = "";
							 if(params2[1]  != null)
								  minDate = params2[1].toString();
							 if(params2[2]  != null)
								 maxDate = params2[2].toString();
							 if(minDate.equalsIgnoreCase(maxDate))
							 vo.setPreferredDate(minDate);
							 else
								 vo.setPreferredDate(minDate +" to "+ maxDate);	 
						 }
						 
					 }
			  }
			} 
			 
		 }
		 
		 List<AppHistoryVO> commentlist = new ArrayList<AppHistoryVO>(0);
		
		 if(appointmentIds !=null && appointmentIds.size()>0){
			 
			List<Object[]> appCommentsList =appointmentCommentDAO.getAppointmentCommentsForViewHistory(appointmentIds);
			for(Object[] obj:appCommentsList){
				 AppHistoryVO vo = new AppHistoryVO();
				 vo.setId(obj[0] != null ? (Long)obj[0] : 0l);
				 vo.setPurpose(obj[1] != null ? obj[1].toString() : "");
				 vo.setUniqueCode(obj[2] != null ? obj[2].toString() : "");
				 vo.setStatusId(obj[3] != null ? (Long)obj[3] : 0l);
				 vo.setStatus(obj[4] != null ? obj[4].toString() : "");
				 vo.setComment(obj[5] != null ? obj[5].toString() : "");
				 vo.setCreatedOn(obj[6] != null ? obj[6].toString() : "");
				 vo.setUser(obj[7] != null ? obj[7].toString() : "");
				 commentlist.add(vo);
				 
			}
		 }
		if(historyVoList!=null && historyVoList.size()>0 && commentlist!=null && commentlist.size()>0){
			historyVoList.get(0).setCommentlist(commentlist);
		}
		 
		return historyVoList;
	}
	
	public List<IdNameVO> getApointmentStatusOvrviwforCandidte(Long apointmntcandidteId,Long apptUserId){
		List<IdNameVO> candidteStusLst=null;
		try {
			List<AppointmentStatus> statusList=appointmentStatusDAO.getAll();
			if(statusList!=null && statusList.size()>0){
				candidteStusLst=new ArrayList<IdNameVO>();
				for(AppointmentStatus appintstts:statusList){
					IdNameVO appointVO=new IdNameVO();
					appointVO.setId(appintstts.getAppointmentStatusId());
					appointVO.setName(appintstts.getStatus());
					appointVO.setAvailableCount(0l);
					candidteStusLst.add(appointVO);
				}
			}
			
			
			List<Object[]> candidteStusCnt=appointmentCandidateRelationDAO.getAppointStatusOverviewforCandidate(apointmntcandidteId,apptUserId);
			if(candidteStusCnt != null && candidteStusCnt.size() > 0)
			{
				for(Object[] params : candidteStusCnt)
				{
					IdNameVO vo = getMathedStatsVO(candidteStusLst,(Long)params[1]);
					if(vo != null)
						vo.setAvailableCount((Long)params[0]);
				}
				
			}
			 List<Object[]> list1 = appointmentCandidateRelationDAO.getFixedAttendedCount(apointmntcandidteId,dateUtilService.getCurrentDateAndTime(),apptUserId);
			 Long fixedAttendedCnt =0l;
			 if(list1 != null && list1.size() > 0)
			 {
				 for(Object[] params : list1)
					{
					 fixedAttendedCnt = fixedAttendedCnt + (Long)params[0];
					} 
			 }
			 if(fixedAttendedCnt != null && fixedAttendedCnt > 0)
			 {
				 for(IdNameVO vo : candidteStusLst)
				 {
					 if(vo.getId().longValue() == IConstants.APPOINTMENT_STATUS_FIXED) //Fixed
					 {
						 vo.setAvailableCount(vo.getAvailableCount() - fixedAttendedCnt);
					 }
					 if(vo.getId().longValue() == IConstants.APPOINTMENT_STATUS_ATTENDED) //Attended 
					 {
						 vo.setAvailableCount(vo.getAvailableCount() + fixedAttendedCnt);
					 }
				 }
			 }
			
		}catch(Exception e){
			LOG.error("Error occured  in getApointmentStatusOvrviwforCandidte() method of AppointmentService",e);
		}
		return candidteStusLst;
	}

	public IdNameVO getMathedStatsVO(List<IdNameVO> candidteStusLst,Long id){

		if(candidteStusLst!=null && candidteStusLst.size()>0) {
			for(IdNameVO vo:candidteStusLst){
				if(vo.getId().longValue() == id.longValue()){
					return vo;
				}
				
			}
		}
		return null;
	}
	
	public AppointmentCandidate candidateDetailsSaving(AppointmentCandidate appCandi,AppointmentBasicInfoVO basicInfo,
			Map<String,Long> voterCardIdsMap,Map<String,Long> cadreIdsMap,Long loggerUserId){
		
		try{
			appCandi.setName(basicInfo.getName());
			appCandi.setDesignationId(basicInfo.getDesignationId());
			appCandi.setMobileNo(basicInfo.getMobileNo());
			appCandi.setLocationScopeId(basicInfo.getLocationScopeId());
			if(basicInfo.getLocationScopeId().longValue() == 3l){			 		//dist
				appCandi.setLocationValue(basicInfo.getDistrictId());
			}
			else if(basicInfo.getLocationScopeId().longValue() == 4l){				//const
				appCandi.setLocationValue(basicInfo.getConstituencyId());
			}
			else if(basicInfo.getLocationScopeId().longValue() == 5l || basicInfo.getLocationScopeId().longValue() == 7l){		//tehsil || Muncipality
				Long id = Long.valueOf(basicInfo.getTehsilId().toString().substring(1));
				appCandi.setLocationValue(id);
			}
			else if(basicInfo.getLocationScopeId().longValue() == 6l || basicInfo.getLocationScopeId().longValue() == 8l){		//Village || Ward
				Long id = Long.valueOf(basicInfo.getVillageId().toString().substring(1));
				appCandi.setLocationValue(id);
			}
			
			//user addres saving logic
			UserAddress userAddress = new UserAddress();
			if(basicInfo.getDistrictId() !=null && basicInfo.getDistrictId()>10){
				userAddress.setState(stateDAO.get(1l));
			}else if(basicInfo.getDistrictId() !=null && basicInfo.getDistrictId()<=10){
				userAddress.setState(stateDAO.get(36l));
			}
			
			if(basicInfo.getDistrictId() > 0l)
			userAddress.setDistrict(districtDAO.get(basicInfo.getDistrictId()));
			if(basicInfo.getConstituencyId() > 0l)
			userAddress.setConstituency(constituencyDAO.get(basicInfo.getConstituencyId()));
			
			if(basicInfo.getTehsilId() != null && basicInfo.getTehsilId() > 0l && basicInfo.getTehsilId().toString().substring(0, 1).equalsIgnoreCase("4")){
				userAddress.setTehsil(tehsilDAO.get(Long.valueOf(basicInfo.getTehsilId().toString().substring(1))));
				if(basicInfo.getVillageId() != null && basicInfo.getVillageId() > 0l)
					userAddress.setPanchayatId(Long.valueOf(basicInfo.getVillageId().toString().substring(1)));
			}
			else if(basicInfo.getTehsilId() != null && basicInfo.getTehsilId() > 0l && basicInfo.getTehsilId().toString().substring(0, 1).equalsIgnoreCase("5")){
				userAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.valueOf(basicInfo.getTehsilId().toString().substring(1))));
				if(basicInfo.getVillageId() != null && basicInfo.getVillageId() > 0l)
					//userAddress.setWard(constituencyDAO.get(Long.parseLong(basicInfo.getVillageId().toString().substring(1))));
					userAddress.setWard(constituencyDAO.get(Long.parseLong(basicInfo.getVillageId().toString().substring(1))));
			}
			
			userAddress = userAddressDAO.save(userAddress);
			
			appCandi.setAddressId(userAddress.getUserAddressId());
			appCandi.setVoterIdCardNo(basicInfo.getVoterCardNo());
			appCandi.setVoterId(voterCardIdsMap.get(basicInfo.getVoterCardNo()));
			appCandi.setMembershipId(basicInfo.getMembershipNum());
			appCandi.setTdpCadreId(cadreIdsMap.get(basicInfo.getMembershipNum()));
			appCandi.setCreatedBy(loggerUserId);
			appCandi.setUpdatedBy(loggerUserId);
			appCandi.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			appCandi.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			appCandi.setImageURL(basicInfo.getCandiImageUrl());
			appCandi.setAppointmentCandidateTypeId(basicInfo.getCandidateTypeId());
			appCandi = appointmentCandidateDAO.save(appCandi);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return appCandi;
	}

	public AppHistoryVO getMathedAppointmentVO(List<AppHistoryVO> resultList,Long id){

		if(resultList!=null && resultList.size()>0) {
			for(AppHistoryVO vo:resultList){
				if(vo.getId().longValue() == id.longValue()){
					return vo;
				}
				
			}
		}
		return null;
	}
	public ResultStatus sendSms(AppointmentUpdateStatusVO inputVO)
	{
		ResultStatus result = new ResultStatus();
		try{
			StringBuffer mobileNos = new StringBuffer();
			boolean isEnglish = false;
			     List<Object[]> list = appointmentCandidateRelationDAO.getAppointmentCandidateMobileNos(inputVO.getAppointmentId());
			     int lastEle = list.size() - 1;
				 if(list != null && list.size() > 0)
				 {
					 for(Object[] params : list)
					 {
						 if(params[2] != null && !params[2].toString().isEmpty())
						 {
							 mobileNos.append(params[2].toString());
							 if(lastEle!=list.size()-1)
							 mobileNos.append(",");
						 } 
					 }
				 }
				 
				 Long statusId = appointmentDAO.getAppointmentStatusId(inputVO.getAppointmentId());
				List<Object[]> objs = appointmentSmsSettingDAO.getContentTypeAndSmsContent(statusId);
				SmsHistory smsHistory = null;
				if(objs != null && objs.size() > 0){
					for(Object[] obj : objs){
						if(obj[0].toString().equalsIgnoreCase("STATIC")){
							smsHistory =	smsSenderService.sendSMS(inputVO.getUserId(), "Appointment", isEnglish, obj[1].toString(), mobileNos.toString());
						}else{
						    smsHistory =	smsSenderService.sendSMS(inputVO.getUserId(), "Appointment", isEnglish,inputVO.getSmsText(), mobileNos.toString());
						}
					}
				}
				
				AppointmentSmsHistory appointmentSmsHistory = new AppointmentSmsHistory();
				appointmentSmsHistory.setAppointmentId(inputVO.getAppointmentId());
				appointmentSmsHistory.setSmsHistory(smsHistory);
				appointmentSmsHistory.setAppointmentStatusId(statusId);
				appointmentSmsHistory.setSentBy(inputVO.getUserId());
				appointmentSmsHistory.setSentTime(dateUtilService.getCurrentDateAndTime());
				appointmentSmsHistoryDAO.save(appointmentSmsHistory);
			
			result.setMessage("success");
		}
		catch (Exception e) {
			LOG.error("Exception raised in sendSms", e);
			result.setMessage("fail");
			
		}
		return result;
	}
	
	
	public void getappointmentComments(Long appointmentId,Long statusId,String commentTxt,Long userId){
		
		try{
			 if(commentTxt !=null && commentTxt.length()>0 && commentTxt.trim() != ""){
		    	   AppointmentComment ac = new AppointmentComment();
		    	      ac.setAppointmentId(appointmentId);
		    	      ac.setAppointmentStatusId(statusId);
		    	      ac.setComment(commentTxt);
		    	      ac.setInsertedBy(userId);
		    	      ac.setUpdatedBy(userId);
		    	      ac.setInsertedTime(dateUtilService.getCurrentDateAndTime());
		    	      ac.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
		    	      appointmentCommentDAO.save(ac);
		    	    
		    	    }
			
		}catch (Exception e) {
			LOG.error("Error occured  in getappointmentComments() method of AppointmentService",e);
		}
	}
	
	public void changeAppointmentStatus(List<Long> ids,Long statusId){
		if(ids != null && ids.size() > 0 && statusId != null && statusId > 0l){
			appointmentDAO.updatedAppointmentStatus(ids,statusId);
			
		}
	}
	
	public AppointmentCountsVO getCandidCountsByStates(String startDateString,String endDateString,Long appointmentUserId){
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		AppointmentCountsVO finalVO = new AppointmentCountsVO();
		
		try{
			 Date startDate = null;
			 Date endDate   = null;
			 
			 if(startDateString != null && startDateString.trim().length() > 0){
				 startDate = sdf.parse(startDateString);
			 }
			 if(endDateString != null && endDateString.trim().length() > 0){
				 endDate = sdf.parse(endDateString);
			 }
			 
			 List<Object[]> candidTypes = appointmentCandidateTypeDAO.getAllCandidateTypes();
			 if(candidTypes!=null && candidTypes.size()>0){
				 setCandidateTypes(candidTypes,"total",finalVO);
				 setCandidateTypes(candidTypes,"scheduled",finalVO);
				 setCandidateTypes(candidTypes,"waiting",finalVO);
			 }
			 
			 List<Object[]>  list= appointmentCandidateRelationDAO.getCandidCountsByStatesAndStatus(appointmentUserId,null,startDate,endDate);
			 setDataToList(list,"total",finalVO);
			 
			 List<Long>      scheduledList =Arrays.asList(IConstants.APPOINTMENT_STATUS_SCHEDULED_LIST);
			 List<Object[]>  scheduledCounts= appointmentCandidateRelationDAO.getCandidCountsByStatesAndStatus(appointmentUserId,scheduledList,startDate,endDate);
			 setDataToList(scheduledCounts,"scheduled",finalVO);
			 
			 List<Long>     waitingList =Arrays.asList(IConstants.APPOINTMENT_STATUS_WAITING_LIST);
			 List<Object[]> waitingCounts= appointmentCandidateRelationDAO.getCandidCountsByStatesAndStatus(appointmentUserId,waitingList,startDate,endDate);
			 setDataToList(waitingCounts,"waiting",finalVO);
			 
			 finalVO.setResultMsg("success");
			
		}catch(Exception e){
			finalVO.setResultMsg("error");
			e.printStackTrace();
		}
		return finalVO;
	}
	
	public void setDataToList(List<Object[]> list,String type,AppointmentCountsVO finalVO){
		
		List<AppointmentCountsVO> subList = null;
		if(type.equalsIgnoreCase("total")){
			subList = finalVO.getTotalCountsVOList();
		}else if(type.equalsIgnoreCase("scheduled")){
			subList = finalVO.getScheduledCountsVOList();
		}else if(type.equalsIgnoreCase("waiting")){
			subList = finalVO.getWaitingCountsVOList();
		}
		
		
		if(list!=null && list.size()>0){
			
			for(Object[] obj : list){
				
				 
				 Long candiType =  obj[0]!=null?(Long)obj[0]:0l;
				 
				 AppointmentCountsVO matchedVO = getMatchedCandiType(subList,candiType);
				 if(matchedVO != null){
					 
					 Long districtId = obj[1]!=null?((Long)obj[1]).longValue():0l;
					 if( districtId>10l && districtId <=23l){
						 
						 matchedVO.setApCount(  matchedVO.getApCount()+ (obj[3]!=null?(Long)obj[3]:0l));
						 matchedVO.setUniqueApCount(matchedVO.getUniqueApCount()+ (obj[4]!=null?(Long)obj[4]:0l));
						 
					 }else if( districtId >= 1l && districtId<=10l){
						 
						 matchedVO.setTsCount(  matchedVO.getTsCount()+ (obj[3]!=null?(Long)obj[3]:0l));
						 matchedVO.setUniqueTsCount(matchedVO.getUniqueTsCount()+ (obj[4]!=null?(Long)obj[4]:0l));
					 }
					 
					 matchedVO.setCount( matchedVO.getCount() + (obj[3]!=null?(Long)obj[3]:0l));
					 matchedVO.setUniqueCount( matchedVO.getUniqueCount() + (obj[4]!=null?(Long)obj[4]:0l));
				 }
				 
				
			 }
		}
		
	}
	public AppointmentCountsVO getMatchedCandiType(List<AppointmentCountsVO> finalList,Long id)
	{
		if(finalList == null || finalList.size() == 0)
			return null;
		for(AppointmentCountsVO vo : finalList)
		{
			if(vo.getId().longValue() == id.longValue())
				return vo;
		}
		return null;
	}
	public void setCandidateTypes(List<Object[]> candiList,String type,AppointmentCountsVO finalVO){
		
		List<AppointmentCountsVO> list = new ArrayList<AppointmentCountsVO>(0);
		
		for(Object[] obj : candiList){
			
			AppointmentCountsVO vo = new AppointmentCountsVO();
			vo.setId(obj[0]!=null?(Long)obj[0]:0l);
			vo.setName(obj[1]!=null?obj[1].toString():"");
			
			list.add(vo);
		}
		
		if(type.equalsIgnoreCase("total")){
			finalVO.setTotalCountsVOList(list);
		}else if(type.equalsIgnoreCase("scheduled")){
			finalVO.setScheduledCountsVOList(list);
		}else if(type.equalsIgnoreCase("waiting")){
			finalVO.setWaitingCountsVOList(list);
		}
	}
	
	public List<AppointmentCountVO> getPublicRepresentativeWiseAppointmentCnt()
	{
		List<AppointmentCountVO> returnList = new ArrayList<AppointmentCountVO>();
		try{
			 List<Object[]> list = appointmentCandidateDAO.getPublicRepresentativeWiseAppointmentCnt(null,"total"); //Total
			 if(list != null && list.size() > 0)
				for(Object[] params : list)
				{
					AppointmentCountVO vo = getMatchedRole(returnList,(Long)params[1]);
					if(vo == null)
					{
						vo = new AppointmentCountVO();
						vo.setRoleId((Long)params[1]);
						vo.setRole(params[2] != null ? params[2].toString() : "");
						returnList.add(vo);
					}
						vo.setTotal(vo.getTotal() +(Long)params[0]);
				}
			 
			 List<Object[]> list1 = appointmentCandidateDAO.getPublicRepresentativeWiseAppointmentCnt(null,"unique"); //Total
			 if(list1 != null && list1.size() > 0)
				for(Object[] params : list1)
				{
					AppointmentCountVO vo = getMatchedRole(returnList,(Long)params[1]);
					if(vo == null)
					{
						vo = new AppointmentCountVO();
						vo.setRoleId((Long)params[1]);
						vo.setRole(params[2] != null ? params[2].toString() : "");
						returnList.add(vo);
					}
						vo.setUniquecnt(vo.getUniquecnt() +(Long)params[0]);
				}
			 Long[] schedul =IConstants.APPOINTMENT_STATUS_SCHEDULED_LIST;
			 List<Object[]> list2 = appointmentCandidateDAO.getPublicRepresentativeWiseAppointmentCnt(Arrays.asList(schedul),"Schedule");
			 if(list2 != null && list2.size() > 0)
				for(Object[] params : list2)
				{
					com.itgrids.partyanalyst.dto.AppointmentCountVO vo = getMatchedRole(returnList,(Long)params[1]);
					if(vo == null)
					{
						vo = new AppointmentCountVO();
						vo.setRoleId((Long)params[1]);
						vo.setRole(params[2] != null ? params[2].toString() : "");
						returnList.add(vo);
					}
					vo.setScheduledCnt((Long)params[0] + vo.getScheduledCnt());
				
				}
			 
			
			 List<Object[]> list3 = appointmentCandidateDAO.getPublicRepresentativeWiseAppointmentCnt(Arrays.asList(schedul),"unique");
			 if(list3 != null && list3.size() > 0)
				for(Object[] params : list3)
				{
					com.itgrids.partyanalyst.dto.AppointmentCountVO vo = getMatchedRole(returnList,(Long)params[1]);
					if(vo == null)
					{
						vo = new AppointmentCountVO();
						vo.setRoleId((Long)params[1]);
						vo.setRole(params[2] != null ? params[2].toString() : "");
						returnList.add(vo);
					}
					vo.setUniqueScheduledCnt((Long)params[0] + vo.getUniqueScheduledCnt());
				
				}
			 Long[] req =IConstants.APPOINTMENT_STATUS_WAITING_LIST;
			 List<Object[]> list4 = appointmentCandidateDAO.getPublicRepresentativeWiseAppointmentCnt(Arrays.asList(req),"Request");
			 if(list4 != null && list4.size() > 0)
				for(Object[] params : list4)
				{
					com.itgrids.partyanalyst.dto.AppointmentCountVO vo = getMatchedRole(returnList,(Long)params[1]);
					if(vo == null)
					{
						vo = new AppointmentCountVO();
						vo.setRoleId((Long)params[1]);
						vo.setRole(params[2] != null ? params[2].toString() : "");
						returnList.add(vo);
					}
					vo.setRequestedCnt((Long)params[0] + vo.getRequestedCnt());
					
				}
			 
			 List<Object[]> list5 = appointmentCandidateDAO.getPublicRepresentativeWiseAppointmentCnt(Arrays.asList(req),"unique");
			 if(list5 != null && list5.size() > 0)
				for(Object[] params : list5)
				{
					com.itgrids.partyanalyst.dto.AppointmentCountVO vo = getMatchedRole(returnList,(Long)params[1]);
					if(vo == null)
					{
						vo = new AppointmentCountVO();
						vo.setRoleId((Long)params[1]);
						vo.setRole(params[2] != null ? params[2].toString() : "");
						returnList.add(vo);
					}
					vo.setUniqueRequestedCnt((Long)params[0] + vo.getUniqueRequestedCnt());
					
				}
			 
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
	}
	
	public AppointmentCountVO getMatchedRole(List<AppointmentCountVO> returnList,Long roleId)
	{
		if(returnList == null || returnList.size() == 0)
			return null;
		for(com.itgrids.partyanalyst.dto.AppointmentCountVO  vo : returnList)
		{
			if(vo.getRoleId().longValue() == roleId.longValue())
				return vo;
		}
		return null;
	}
	public List<AppointmentCountVO> getCommitteeLevelAppointments()
	{
		List<AppointmentCountVO> returnList = new ArrayList<AppointmentCountVO>();
		try{
			 List<Object[]> list =  appointmentCandidateRelationDAO.getCommitteeLevelAppointments(null,"total"); //Total
			 if(list != null && list.size() > 0)
				for(Object[] params : list)
				{
					AppointmentCountVO vo = getMatchedRole(returnList,(Long)params[1]);
					if(vo == null)
					{
						vo = new AppointmentCountVO();
						vo.setRoleId((Long)params[1]);
						vo.setRole(params[2] != null ? params[2].toString() : "");
						returnList.add(vo);
					}
						vo.setTotal(vo.getTotal() +(Long)params[0]);
				}
			 
			 List<Object[]> list1 = appointmentCandidateRelationDAO.getCommitteeLevelAppointments(null,"unique"); //Total
			 if(list1 != null && list1.size() > 0)
				for(Object[] params : list1)
				{
					AppointmentCountVO vo = getMatchedRole(returnList,(Long)params[1]);
					if(vo == null)
					{
						vo = new AppointmentCountVO();
						vo.setRoleId((Long)params[1]);
						vo.setRole(params[2] != null ? params[2].toString() : "");
						returnList.add(vo);
					}
						vo.setUniquecnt(vo.getUniquecnt() +(Long)params[0]);
				}
			 Long[] schedul =IConstants.APPOINTMENT_STATUS_SCHEDULED_LIST;
			 List<Object[]> list2 = appointmentCandidateRelationDAO.getCommitteeLevelAppointments(Arrays.asList(schedul),"Schedule");
			 if(list2 != null && list2.size() > 0)
				for(Object[] params : list2)
				{
					com.itgrids.partyanalyst.dto.AppointmentCountVO vo = getMatchedRole(returnList,(Long)params[1]);
					if(vo == null)
					{
						vo = new AppointmentCountVO();
						vo.setRoleId((Long)params[1]);
						vo.setRole(params[2] != null ? params[2].toString() : "");
						returnList.add(vo);
					}
					vo.setScheduledCnt((Long)params[0] + vo.getScheduledCnt());
				}
			 
			List<Object[]> list3 = appointmentCandidateRelationDAO.getCommitteeLevelAppointments(Arrays.asList(schedul),"unique");
			 if(list3 != null && list3.size() > 0)
				for(Object[] params : list3)
				{
					com.itgrids.partyanalyst.dto.AppointmentCountVO vo = getMatchedRole(returnList,(Long)params[1]);
					if(vo == null)
					{
						vo = new AppointmentCountVO();
						vo.setRoleId((Long)params[1]);
						vo.setRole(params[2] != null ? params[2].toString() : "");
						returnList.add(vo);
					}
					vo.setUniqueScheduledCnt((Long)params[0] + vo.getUniqueScheduledCnt());
				
				}
			 Long[] req =IConstants.APPOINTMENT_STATUS_WAITING_LIST;
			 List<Object[]> list4 = appointmentCandidateRelationDAO.getCommitteeLevelAppointments(Arrays.asList(req),"Request");
			 if(list4 != null && list4.size() > 0)
				for(Object[] params : list4)
				{
					com.itgrids.partyanalyst.dto.AppointmentCountVO vo = getMatchedRole(returnList,(Long)params[1]);
					if(vo == null)
					{
						vo = new AppointmentCountVO();
						vo.setRoleId((Long)params[1]);
						vo.setRole(params[2] != null ? params[2].toString() : "");
						returnList.add(vo);
					}
					vo.setRequestedCnt((Long)params[0] + vo.getRequestedCnt());
				}
			 
			 List<Object[]> list5 = appointmentCandidateRelationDAO.getCommitteeLevelAppointments(Arrays.asList(req),"unique");
			 if(list5 != null && list5.size() > 0)
				for(Object[] params : list5)
				{
					com.itgrids.partyanalyst.dto.AppointmentCountVO vo = getMatchedRole(returnList,(Long)params[1]);
					if(vo == null)
					{
						vo = new AppointmentCountVO();
						vo.setRoleId((Long)params[1]);
						vo.setRole(params[2] != null ? params[2].toString() : "");
						returnList.add(vo);
					}
					vo.setUniqueRequestedCnt((Long)params[0] + vo.getUniqueRequestedCnt());
				}
			 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
		
	}
}
