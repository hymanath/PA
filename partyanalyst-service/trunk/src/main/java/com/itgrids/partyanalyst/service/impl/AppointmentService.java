package com.itgrids.partyanalyst.service.impl;


import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
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
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
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
import com.itextpdf.text.Rectangle;
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
import com.itgrids.partyanalyst.dao.IAppointmentStatusFlowDAO;
import com.itgrids.partyanalyst.dao.IAppointmentTimeSlotDAO;
import com.itgrids.partyanalyst.dao.IAppointmentTrackingDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictConstituenciesDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILabelAppointmentDAO;
import com.itgrids.partyanalyst.dao.ILabelAppointmentHistoryDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INominatedPostAgeRangeDAO;
import com.itgrids.partyanalyst.dao.INominatedPostDAO;
import com.itgrids.partyanalyst.dao.INominationPostCandidateDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentYearDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITdpMemberDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserAppointmentUserDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.hibernate.AppointmentCommentDAO;
import com.itgrids.partyanalyst.dto.AppHistoryVO;
import com.itgrids.partyanalyst.dto.AppointmentBasicInfoVO;
import com.itgrids.partyanalyst.dto.AppointmentCandidateVO;
import com.itgrids.partyanalyst.dto.AppointmentCountDetailsVO;
import com.itgrids.partyanalyst.dto.AppointmentCountVO;
import com.itgrids.partyanalyst.dto.AppointmentCountsVO;
import com.itgrids.partyanalyst.dto.AppointmentDetailsVO;
import com.itgrids.partyanalyst.dto.AppointmentInputVO;
import com.itgrids.partyanalyst.dto.AppointmentMemberInputVO;
import com.itgrids.partyanalyst.dto.AppointmentMembersDataVO;
import com.itgrids.partyanalyst.dto.AppointmentScheduleVO;
import com.itgrids.partyanalyst.dto.AppointmentSlotsVO;
import com.itgrids.partyanalyst.dto.AppointmentStatusFlowVO;
import com.itgrids.partyanalyst.dto.AppointmentStatusVO;
import com.itgrids.partyanalyst.dto.AppointmentUpdateStatusVO;
import com.itgrids.partyanalyst.dto.AppointmentVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LabelStatusVO;
import com.itgrids.partyanalyst.dto.LocationInputVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.PashiAppNoCadreVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.StatusTrackingVO;
import com.itgrids.partyanalyst.model.Appointment;
import com.itgrids.partyanalyst.model.AppointmentCandidate;
import com.itgrids.partyanalyst.model.AppointmentCandidateDesignation;
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
import com.itgrids.partyanalyst.model.NominatedPostAgeRange;
import com.itgrids.partyanalyst.model.SmsHistory;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IAppointmentService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ISmsSenderService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ImageAndStringConverter;
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
	private IUserAppointmentUserDAO userAppointmentUserDAO;
	private IAppointmentStatusFlowDAO appointmentStatusFlowDAO;
	private ITdpCommitteeMemberDAO tdpCommitteeMemberDAO;
	private ITdpMemberDAO tdpMemberDAO;
	private ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO;
	private INominatedPostAgeRangeDAO nominatedPostAgeRangeDAO;
	private INominationPostCandidateDAO nominationPostCandidateDAO;
	private IDistrictConstituenciesDAO districtConstituenciesDAO;
	private ImageAndStringConverter imageAndStringConverter = new ImageAndStringConverter();
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private INominatedPostDAO nominatedPostDAO;
	
	public INominatedPostDAO getNominatedPostDAO() {
		return nominatedPostDAO;
	}

	public void setNominatedPostDAO(INominatedPostDAO nominatedPostDAO) {
		this.nominatedPostDAO = nominatedPostDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {  
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	public ImageAndStringConverter getImageAndStringConverter() {
		return imageAndStringConverter;
	}
	public void setImageAndStringConverter(
			ImageAndStringConverter imageAndStringConverter) {
		this.imageAndStringConverter = imageAndStringConverter;
	}
	public INominationPostCandidateDAO getNominationPostCandidateDAO() {
		return nominationPostCandidateDAO;
	}
	public void setNominationPostCandidateDAO(
			INominationPostCandidateDAO nominationPostCandidateDAO) {
		this.nominationPostCandidateDAO = nominationPostCandidateDAO;
	}
	public INominatedPostAgeRangeDAO getNominatedPostAgeRangeDAO() {
		return nominatedPostAgeRangeDAO;
	}
	public void setNominatedPostAgeRangeDAO(
			INominatedPostAgeRangeDAO nominatedPostAgeRangeDAO) {
		this.nominatedPostAgeRangeDAO = nominatedPostAgeRangeDAO;
	}
	public ITdpCadreEnrollmentYearDAO getTdpCadreEnrollmentYearDAO() {
		return tdpCadreEnrollmentYearDAO;
	}
	public void setTdpCadreEnrollmentYearDAO(
			ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO) {
		this.tdpCadreEnrollmentYearDAO = tdpCadreEnrollmentYearDAO;
	}
	public ITdpMemberDAO getTdpMemberDAO() {
		return tdpMemberDAO;
	}
	public void setTdpMemberDAO(ITdpMemberDAO tdpMemberDAO) {
		this.tdpMemberDAO = tdpMemberDAO;
	}
	public ITdpCommitteeMemberDAO getTdpCommitteeMemberDAO() {
		return tdpCommitteeMemberDAO;
	}
	public void setTdpCommitteeMemberDAO(
			ITdpCommitteeMemberDAO tdpCommitteeMemberDAO) {
		this.tdpCommitteeMemberDAO = tdpCommitteeMemberDAO;
	}
	public IAppointmentStatusFlowDAO getAppointmentStatusFlowDAO() {
		return appointmentStatusFlowDAO;
	}
	public void setAppointmentStatusFlowDAO(
			IAppointmentStatusFlowDAO appointmentStatusFlowDAO) {
		this.appointmentStatusFlowDAO = appointmentStatusFlowDAO;
	}
	public IUserAppointmentUserDAO getUserAppointmentUserDAO() {
		return userAppointmentUserDAO;
	}
	public void setUserAppointmentUserDAO(
			IUserAppointmentUserDAO userAppointmentUserDAO) {
		this.userAppointmentUserDAO = userAppointmentUserDAO;
	}
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
	
	public IDistrictConstituenciesDAO getDistrictConstituenciesDAO() {
		return districtConstituenciesDAO;
	}
	public void setDistrictConstituenciesDAO(
			IDistrictConstituenciesDAO districtConstituenciesDAO) {
		this.districtConstituenciesDAO = districtConstituenciesDAO;
	}
	public boolean checkisEligibleForAppt(List<String> membershipNoList,Long appointmentUserId){
		
		boolean flag = false;
		
		List<Long>  apptCreationStatusList =Arrays.asList(IConstants.APPOINTMENT_STATUS_CREATION_LIST);
		List<Object[]> list = appointmentCandidateRelationDAO.checkIsAppointmentEligible(membershipNoList,apptCreationStatusList,appointmentUserId);
		if(list!=null && list.size()>0){
			flag = true;
		}
		return flag;
	}
	
	
	public void saveApptDetailsInFatalLog(AppointmentVO appointmentVO)
	{
		try{
			
			Date currentDateAndTime = new DateUtilService().getCurrentDateAndTime();
			
			LOG.fatal("-----------------"+currentDateAndTime+"-----------------------------------");
			
			LOG.fatal(" apptUserId - "+appointmentVO.getAppointmentUserId()+"\tapptPriorityId - "+appointmentVO.getAppointmentPriorityId()+
					  " \tapptReason - "+appointmentVO.getReason()+"\tapptPreferableTime - "+appointmentVO.getAppointmentPreferableTimeType()+"\tapptDates - "+appointmentVO.getAppointmentDates());
			
			if(appointmentVO.getBasicInfoList() != null && appointmentVO.getBasicInfoList().size() > 0){
				for (AppointmentBasicInfoVO basicInfo : appointmentVO.getBasicInfoList()) {					
					if(basicInfo !=null){
						LOG.fatal(" candiname - "+basicInfo.getName()+"\tdesignationId - "+basicInfo.getDesignationId()+
								  " \tmobileNo - "+basicInfo.getMobileNo()+"\tlocationScopeId - "+basicInfo.getLocationScopeId()+"\tdistrictId - "+basicInfo.getDistrictId()+
								  "\tconstId -"+basicInfo.getConstituencyId() + "\ttehsilId - "+ basicInfo.getTehsilId() +"\tvillageId - "+basicInfo.getVillageId()+
								  "\tvotercardno - "+basicInfo.getVoterCardNo() +"\tmembershipno - "+basicInfo.getMembershipNum() +"\tcandidateTypeId - "+basicInfo.getCandidateTypeId()	);
					    
					}
				}
			}
			LOG.fatal("----------------------------------------------------");
		}catch(Exception e)
		{
			LOG.error("Exception occured in saveApptDetailsInFatalLog() - ",e);
		}
	}
	
	
	public ResultStatus saveAppointment(final AppointmentVO appointmentVO,final Long loggerUserId){
		 ResultStatus status = new ResultStatus();
		try {
			 saveApptDetailsInFatalLog(appointmentVO);
			 
			status = (ResultStatus)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					
					 ResultStatus rs = new ResultStatus();
					 
		        	List<String> membershipNoList = new ArrayList<String>(0);
	        		
	        		for (AppointmentBasicInfoVO basicInfo : appointmentVO.getBasicInfoList()) {
	        			if(basicInfo != null && basicInfo.getMembershipNum() != null && !basicInfo.getMembershipNum().isEmpty()){
	        				membershipNoList.add(basicInfo.getMembershipNum());
	        			}
	        		}
	        		
	        		if(membershipNoList!=null && membershipNoList.size() >0 ){
	        			
	        			boolean apptCreationFlag = checkisEligibleForAppt(membershipNoList,appointmentVO.getAppointmentUserId());
			        	if(apptCreationFlag){
			        		
			        		rs.setExceptionMsg("Not Eligible To Create Appointment.");
			    			rs.setResultCode(2);
			        		return rs;
			        	}
	        		}
		        	
		        	
		        	Appointment appointment = new Appointment();
		        	appointment.setAppointmentUserId(appointmentVO.getAppointmentUserId());
		        	
		        	if(appointmentVO.getAppointmentPriorityId() != null  && appointmentVO.getAppointmentPriorityId()>0){
		        		appointment.setAppointmentPriorityId(appointmentVO.getAppointmentPriorityId());
		        	}		 
		        	if(appointmentVO.getReason() !=null && !appointmentVO.getReason().isEmpty()){
		        		appointment.setReason(appointmentVO.getReason());
		        	}
		        	
		        	appointment.setAppointmentStatusId(IConstants.APPOINTMENT_STATUS_WAITING);
		        	
		        	/*if(appointmentVO.getUniqueCode()!=null && !appointmentVO.getUniqueCode().trim().equalsIgnoreCase("")){
		        		String temp[] = appointmentVO.getUniqueCode().split("_");
		        		appointment.setAppointmentUserId(Long.parseLong(temp[1]));
		        	}*/
		        	
		        	if(appointmentVO.getAppointmentPreferableTimeType() !=null && appointmentVO.getAppointmentPreferableTimeType().equalsIgnoreCase("multipleDates")){
		        		appointment.setAppointmentPreferableTimeId(1l);
		        	}else if(appointmentVO.getAppointmentPreferableTimeType() !=null && appointmentVO.getAppointmentPreferableTimeType().equalsIgnoreCase("nextWeek")){
		        		appointment.setAppointmentPreferableTimeId(2l);
		        	}else if(appointmentVO.getAppointmentPreferableTimeType() !=null && appointmentVO.getAppointmentPreferableTimeType().equalsIgnoreCase("nextMonth")){
		        		appointment.setAppointmentPreferableTimeId(3l);
		        	}else if(appointmentVO.getAppointmentPreferableTimeType() !=null && appointmentVO.getAppointmentPreferableTimeType().equalsIgnoreCase("thisWeek")){
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
		        	
		        	//dates
		        	List<Date> datesList = new ArrayList<Date>(0);
		        	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		        	
		        	if(appointmentVO.getAppointmentPreferableTimeType() !=null && appointmentVO.getAppointmentPreferableTimeType().equalsIgnoreCase("multipleDates")){
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
		        	}else if(appointmentVO.getAppointmentPreferableTimeType() !=null && appointmentVO.getAppointmentPreferableTimeType().equalsIgnoreCase("nextWeek")){
		        		appointment.setAppointmentPreferableTimeId(2l);
		        		datesList = dateUtilService.getDatesOfWeekAfterCurrentWeek();
		        	}else if(appointmentVO.getAppointmentPreferableTimeType() !=null && appointmentVO.getAppointmentPreferableTimeType().equalsIgnoreCase("nextMonth")){
		        		appointment.setAppointmentPreferableTimeId(3l);
		        		datesList = dateUtilService.getDatesOfNextMonth();
		        	}else if(appointmentVO.getAppointmentPreferableTimeType() !=null && appointmentVO.getAppointmentPreferableTimeType().equalsIgnoreCase("thisWeek")){
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
		        		
		        		Map<String,Long> cadreIdsMap = new HashMap<String, Long>(0);
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
		            saveAppointmentTrackingDetails(appointment.getAppointmentId(),IConstants.APPOINTMENT_ACTION_STATUS_CHANGE,null,
		            		IConstants.APPOINTMENT_STATUS_WAITING,loggerUserId,appointmentVO.getReason());
		        	rs.setExceptionMsg("success");
					rs.setResultCode(0);
					return rs;
		        }
		    });
			
		} catch (Exception e) {
			LOG.error("Exception raised at saveAppointment", e);
			status.setExceptionMsg("failure");
			status.setResultCode(1);
		}
		return status;
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

	public ResultStatus deleteAppointmentLabel(Long appointmentLabelId,String remarks,Long userId) {
	   
		ResultStatus status=new ResultStatus();
		try{
			LOG.info("Entered into deleteAppointmentLabel() method of AppointmentService");
			if(appointmentLabelId!=null && appointmentLabelId>0l){
			 Integer deletedCount=appointmentLabelDAO.deleteAppointmentLabel(appointmentLabelId,remarks);	
			 
			 //update status of appts to waiting
			 List<Long> apptIDs = labelAppointmentDAO.getAppointmentsForALabel(appointmentLabelId);
			 if(apptIDs != null && apptIDs.size() > 0){
				 changeAppointmentStatus(apptIDs,IConstants.APPOINTMENT_STATUS_WAITING,userId,dateUtilService.getCurrentDateAndTime());
				 labelAppointmentDAO.updateIsDeletedStatus(apptIDs,appointmentLabelId,userId,dateUtilService.getCurrentDateAndTime());
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
				    		  vo.setConstituency(obj[4] != null?obj[4].toString():"");
				    		  vo.setVoterCardNo(searchValue);
				    		  finalList.add(vo);
				    		
			    		  }
			    		  
			    	  }
			    	  
			      }
			      if(tdpCadreIds!=null && tdpCadreIds.size()>0){
			    	  
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
						 setConstituencyForPR(tdpCadreIds,finalList);
						 checkisEligibleForApptCadre(tdpCadreIds,aptUserId,finalList);
						 getDesignationsForCadre(tdpCadreIds,finalList);
						
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
						    	
						    }else if(inputVo.getLevelId() == 12l){//central
						    	locationVo.getCounrtyIds().add(1l);
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
public void setConstituencyForPR(List<Long> tdpCadreIds,List<AppointmentCandidateVO>  finalList)
{
	try{
	List<Object[]> list = tdpCadreDAO.getPRConstituenciesByCadreIds(tdpCadreIds);
	if(list != null && list.size() > 0)
	{
		for(Object[] params : list)
		{
			AppointmentCandidateVO vo = getMatchedVO(finalList,(Long)params[0]);
			 if(vo != null)
			 {
				  vo.setConstituency(params[1]!=null?params[1].toString():"");
			 }
		}
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
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
  		  if(obj.length > 8){
	  		  vo.setVoterId(obj[8]!=null?(Long)obj[8]:0l);
	  		  vo.setDistrictName(obj[9]!=null?obj[9].toString():"");//newly added for janmabhoomi committee dashboard
  		  }
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
		 setConstituencyForPR(tdpCadreIds,finalList);
		 checkisEligibleForApptCadre(tdpCadreIds,aptUserId,finalList);
		
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
			 checkisEligibleForApptCadre(tdpCadreIds,aptUserId,finalList);
			 getDesignationsForCadre(tdpCadreIds,finalList);
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
		List<Long> tdpCadreIds = new ArrayList<Long>();
		try{
			List<UserAddress> userAddressList=null;
			if(candidateType.equalsIgnoreCase("appointmentCandidate")){
				userAddressList = appointmentCandidateDAO.getUserWorkAddress(id);
			}else if(candidateType.equalsIgnoreCase("cadre")){
				tdpCadreIds.add(id);
				userAddressList = tdpCadreDAO.getUserAddressForPR(tdpCadreIds);
				if(userAddressList == null || userAddressList.size() == 0)
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
     public List<AppointmentDetailsVO> getAppointmentsBySearchCriteria1(Long designationId,Long priorityId,Long statusId,Long districtId,Long constituencyid,Long appointmentlabelId,String fromDateStr,String toDateStr,Long selUserID,
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
			LOG.error("Exception raised at getAppointmentsBySearchCriteria1",e);
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
			        				changeAppointmentStatus(savingAppointmentIds, IConstants.APPOINTMENT_STATUS_LABELED,loggerUserId,dateUtilService.getCurrentDateAndTime());
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
	
	
	public LabelStatusVO getStatusWiseCountsOfAppointments1(Long aptUserId){
		
		LabelStatusVO finalVo = new LabelStatusVO();
		
		try{  
			  
			  List<Long>  apptStatusList =Arrays.asList(IConstants.TODAY_APPOINTMENTS_STATUS_LIST); 
			  
			  Map<Long,LabelStatusVO>  finalMap = new LinkedHashMap<Long,LabelStatusVO>();
			  
			  
			  Map<Long,LabelStatusVO>  approvedMap = new LinkedHashMap<Long,LabelStatusVO>();
			  List<Long> approvedlist = new ArrayList<Long>();
			  approvedlist.add(IConstants.APPOINTMENT_STATUS_APPROVED);
			  approvedlist.add(IConstants.APPOINTMENT_STATUS_SCHEDULED);
			  
			  
			  List<Object[]> statusList = appointmentStatusDAO.getStatusDetailsByIds(apptStatusList);
			  if(statusList!=null && statusList.size()>0){
				
				  for(Object[] obj : statusList){
					  
					  Long statusId = obj[0]!=null?(Long)obj[0]:0l;
					  
					  if( approvedlist.contains(statusId) ){
		        			
						     LabelStatusVO statusVO = new LabelStatusVO();
						     statusVO.setStatusId(obj[0]!=null?(Long)obj[0]:0l);
							 statusVO.setStatus(obj[0]!=null?obj[1].toString():"");
							 statusVO.setTotalCount(0l);
							  
							 statusVO.setClickIds(new ArrayList<Long>(0));
							 statusVO.getClickIds().add(statusId);
							  
			   	             approvedMap.put(statusId,statusVO);
		        	  }
					  
					  
					  
					  if( !approvedlist.contains(statusId) || ( approvedlist.contains(statusId) && statusId == IConstants.APPOINTMENT_STATUS_APPROVED.longValue())){
						  
						  LabelStatusVO statusVO = new LabelStatusVO();
						  statusVO.setStatusId(obj[0]!=null?(Long)obj[0]:0l);
						  statusVO.setStatus(obj[0]!=null?obj[1].toString():"");
						  statusVO.setTotalCount(0l);
						  
						  statusVO.setClickIds(new ArrayList<Long>(0));
						  statusVO.getClickIds().add(statusId);
						  if(statusId == 2l){
							  statusVO.getClickIds().add(IConstants.APPOINTMENT_STATUS_SCHEDULED);
						  }
						  
						  finalMap.put(statusId, statusVO);
					  }
					  
				  }
			  }
			  
			  
			  List<Object[]> countsList = appointmentDAO.eachStatusApptCountByDateAndApptUser(aptUserId,apptStatusList,new DateUtilService().getCurrentDateAndTime());
			  
			  if( countsList != null  && countsList.size() >0){
				  
				  for( Object[] obj : countsList){
					  
					  Long statusId = obj[0]!=null?(Long)obj[0]:0l;
					  
					  LabelStatusVO statusVO = null;
					  
					  if(statusId.longValue() == IConstants.APPOINTMENT_STATUS_SCHEDULED.longValue() ){
						  statusVO = finalMap.get(IConstants.APPOINTMENT_STATUS_APPROVED);
					  }else{
						  statusVO = finalMap.get(statusId);
					  }
					  
					  if(statusVO != null){
						  statusVO.setTotalCount(  statusVO.getTotalCount() +( obj[2]!=null?(Long)obj[2]:0l));
					  }
					  
					  LabelStatusVO approvedIndividualListVO = null;
					  approvedIndividualListVO = approvedMap.get(statusId);
					  if(approvedIndividualListVO !=null){
						  approvedIndividualListVO.setTotalCount(obj[2]!=null?(Long)obj[2]:0l);
					  }
				  }
			  }
			  
			  if(finalMap!=null && finalMap.size()>0){
				  
				  LabelStatusVO statusvo =  finalMap.get(IConstants.APPOINTMENT_STATUS_APPROVED.longValue());
		    	  if(statusvo != null){
		    		  statusvo.setSubList(new ArrayList<LabelStatusVO>(approvedMap.values()));
		    	  }
				  finalVo.setStatusList(new ArrayList<LabelStatusVO>(finalMap.values()));
			  }
			  
		}catch(Exception e){
			LOG.error("Exception raised at getStatusWiseCountsOfAppointments", e);
		}
		return finalVo;
	}
	
	public List<AppointmentStatusVO> getAppointmentStatusCounts(Long apptUserId){
    
    List<AppointmentStatusVO> finalList = new ArrayList<AppointmentStatusVO>(0);
    
    try{
       
       List<Object[]> statusList = appointmentStatusDAO.getAllAppointmentStatus();
       
       List<Long> approvedlist = new ArrayList<Long>();
       approvedlist.add(IConstants.APPOINTMENT_STATUS_APPROVED);
       approvedlist.add(IConstants.APPOINTMENT_STATUS_NOTATTENDED);
       approvedlist.add(IConstants.APPOINTMENT_STATUS_CANCELLED);
       approvedlist.add(IConstants.APPOINTMENT_STATUS_RESCHEDULED);
       
       
       
       Map<Long,AppointmentStatusVO> finalMap = new LinkedHashMap<Long,AppointmentStatusVO>();
       Map<Long,AppointmentStatusVO> approvedMap = new LinkedHashMap<Long,AppointmentStatusVO>();
       
       if(statusList!=null && statusList.size()>0){
        for (Object[] obj : statusList) {
           
        	Long statusId = obj[0]!=null?(Long)obj[0]:0l;
        	
        	//if( statusId.longValue() != IConstants.APPOINTMENT_STATUS_WITHDRAWN.longValue()){
        		
        		if( approvedlist.contains(statusId) ){
        			
        			 AppointmentStatusVO VO = new AppointmentStatusVO();
	        		 VO.setAppointmentStatusId(statusId);
	        		 VO.setStatus(obj[1]!=null?obj[1].toString():"");
	   	             VO.setStatusCount(0l);
	   	             VO.setMembersCount(0l);
	   	             
	   	             VO.setClickIds(new ArrayList<Long>());
	   	             VO.getClickIds().add(statusId);
	   	             approvedMap.put(statusId,VO);
        		}
        		
        		if( !approvedlist.contains(statusId) || ( approvedlist.contains(statusId) && statusId == IConstants.APPOINTMENT_STATUS_APPROVED.longValue())){
        			 AppointmentStatusVO VO = new AppointmentStatusVO();
	        		 VO.setAppointmentStatusId(statusId);
	        		 VO.setStatus(obj[1]!=null?obj[1].toString():"");
	   	             VO.setStatusCount(0l);
	   	             VO.setMembersCount(0l);
	   	             
	   	             VO.setClickIds(new ArrayList<Long>());
	   	             VO.getClickIds().add(statusId);
	   	             finalMap.put(statusId,VO);
        		}
       // }
        }
       }
       
       List<Long>  apptBasicCountsList =Arrays.asList(IConstants.REQUIRED_APPOINTMENTS_LIST);
       List<Object[]> countList = appointmentCandidateRelationDAO.eachStatusApptCountAndUniqueMemCount(apptUserId,apptBasicCountsList);
       if(countList!=null && countList.size()>0){
         for(Object[] obj:countList){
        	 
           Long statusId = obj[0]!=null?(Long)obj[0]:0l;
           
           AppointmentStatusVO statusvo = finalMap.get(statusId);
           if(statusvo!=null){
             statusvo.setStatusCount(obj[2]!=null?(Long)obj[2]:0l);
             statusvo.setMembersCount(obj[3]!=null?(Long)obj[3]:0l);
           }
           AppointmentStatusVO individualInApprovedListVO = approvedMap.get(statusId);
           if(individualInApprovedListVO!=null){
        	   individualInApprovedListVO.setStatusCount(obj[2]!=null?(Long)obj[2]:0l);
        	   individualInApprovedListVO.setMembersCount(obj[3]!=null?(Long)obj[3]:0l);
           }
           
         }
       }
      
       
       Object[] approvedObj =appointmentCandidateRelationDAO.combinedStatusApptAndUniqueMemCount(apptUserId,approvedlist);
       setData(finalMap,approvedObj,IConstants.APPOINTMENT_STATUS_APPROVED,approvedlist);
       
       
       if(finalMap!=null && finalMap.size()>0){
    	   
    	  AppointmentStatusVO statusvo =  finalMap.get(IConstants.APPOINTMENT_STATUS_APPROVED.longValue());
    	  if(statusvo != null){
    		  statusvo.setSubList(new ArrayList<AppointmentStatusVO>(approvedMap.values()));
    	  }
    	   
    	   finalList.addAll(finalMap.values());
       }
       
    }catch(Exception e){
    	LOG.error("Exception raised at getAppointmentStatusCounts", e);
    }
    return finalList;
  }

	public void setData(Map<Long,AppointmentStatusVO> finalMap,Object[] objArray,Long statusId,List<Long> clickList){
	
		if(objArray!=null && objArray.length>0){
		   
		   AppointmentStatusVO statusvo = finalMap.get(statusId.longValue());
		   if(statusvo!=null){
			 statusvo.setClickIds(clickList);
		     statusvo.setStatusCount(objArray[0]!=null?(Long)objArray[0]:0l);
		     statusvo.setMembersCount(objArray[1]!=null?(Long)objArray[1]:0l); 
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
		List<Long> candidateList=new ArrayList<Long>(0);
		List<Long> tdpCadreIdList=new ArrayList<Long>(0);
		try{
			
			SimpleDateFormat prefer = new SimpleDateFormat("dd MMM yyyy");
			
			String searchType = null;
			Date strDate = null;
			Date endDate = null;
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			
			DateUtilService dt = new DateUtilService();
			
			if(inputVo.getStrDate() != null && !inputVo.getStrDate().isEmpty())
			{
				strDate = format.parse(inputVo.getStrDate());
				endDate = format.parse(inputVo.getEndDate());
			}else{
				if(inputVo.getType() !=null && !inputVo.getType().isEmpty() && inputVo.getType().equalsIgnoreCase("today")){
					Date date = dt.getCurrentDateAndTime();					
					strDate = date;
					endDate = date;					
				}
			}
			 if(inputVo.getName() != null && !inputVo.getName().isEmpty())
			 {
				 if(inputVo.getName().matches("\\d+"))
				 searchType = "mobile";
				 else
				searchType = "name"; 
			}
			 
			  
				List<Long> partyCommiteeDesigCadreIds = new ArrayList<Long>();
				List<Long> cadreIds = new ArrayList<Long>();
				Map<Long,String> publicRepresLocaMap = new HashMap<Long,String>();
				Map<Long,Long> designationMap = new HashMap<Long, Long>();
				List<Long> PrCadreIds = new ArrayList<Long>();
				Set<Long> setAptmnts = new HashSet<Long>();
				
				List<Object[]> list = appointmentCandidateRelationDAO.getAppointmentSearchDetailsForStatus(strDate,endDate,inputVo,searchType);
				if(list != null && list.size() > 0){
				
					resultList = new ArrayList<AppointmentScheduleVO>();
					for(Object[] params : list){
						
						AppointmentScheduleVO vo = getMatchedAppointment(resultList,(Long)params[11]);
						if(vo == null){
						
							vo = new AppointmentScheduleVO();
							vo.setAppointmentId((Long)params[11]);							
							Date date=null;
							Date date1 = null;
							String convertDate = null;
							String convertDate1 =null;
							if(params[8] !=null){
								 date= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(params[8].toString().substring(0, 19));
								 convertDate = new SimpleDateFormat("hh:mm a").format(date);
							}
							if(params[12] !=null){
								date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(params[12].toString().substring(0, 19));
								convertDate1 = new SimpleDateFormat("hh:mm a").format(date1);
							}								
								vo.setSubject(params[4] != null ? params[4].toString() : "");
								vo.setStatusId(params[9] != null ? (Long)params[9] : null);
								vo.setAppointmentStatus(params[10] != null ? params[10].toString() : "");
								vo.setAppointmentStatusColor(params[16]!=null ? params[16].toString():"");
								vo.setTime(convertDate);
								vo.setToTime(convertDate1);
								vo.setFromDate(params[8]!=null ? params[8].toString():"");
								vo.setToDate(params[12]!=null ? params[12].toString():"");
								vo.setDate(params[14]!=null ? params[14].toString().split(" ")[0]:"");
								vo.setAppointmentUniqueId(params[13]!=null?params[13].toString():"");
								
								// Preferable Dates && requestred Date Start 
								
									List<Long> aptmnts  = new ArrayList<Long>();									
									aptmnts.add(vo.getAppointmentId());		
									
									//vo = setPreferDatesToAppointment(aptmnts,vo);
									
									Date dateStr = params[18]!=null ? (Date)params[18]:null;
									if(dateStr !=null){
										vo.setRequestedDate(prefer.format(dateStr));										
									}
									
									setAptmnts.add(vo.getAppointmentId());
									
								
								//Preferable Dates && requestred Dates End
								
								
							resultList.add(vo);
						}
						AppointmentScheduleVO candidateVo = new AppointmentScheduleVO();
						candidateVo.setId(params[0] != null ? Long.valueOf(params[0].toString()) :0l);
						candidateVo.setName(params[1] != null ? params[1].toString() :"");
						candidateVo.setMobileNo(params[2] != null ? params[2].toString() : "");
						candidateVo.setImageUrl(params[15]!=null?params[15].toString():null);
						candidateVo.setTdpCadreId(params[17] != null ? (Long)params[17] : null);
						candidateVo.setDesignation(params[3] != null ? params[3].toString() : "");
						String fname = params[6] != null ? params[6].toString() : "";
						String lname = params[7] != null ?params[7].toString() : "";
						candidateVo.setCreatedBy(fname+" "+lname);
						
						Long apptcanditype = params[19] !=null ?(Long)params[19]:null;
						Long tdpcadreId =    params[21] !=null ?(Long)params[21]:null;
						candidateVo.setAppointmentUniqueId(params[13]!=null?params[13].toString():"");
					
						if(tdpcadreId!=null && tdpcadreId>0l){
							candidateVo.setTdpCadreId(tdpcadreId);
							tdpCadreIdList.add(tdpcadreId);
						}
						
						candidateList.add(params[0] != null ? Long.valueOf(params[0].toString()) :0l);
						 //candidates designation based on appt cand type
						if(apptcanditype != null){
							candidateVo.setApptCandiTypeId(apptcanditype);
							if(apptcanditype.longValue() == 4l){
								
								candidateVo.setCandDesignation(params[20] !=null ?params[20].toString():"");
								candidateVo.setConstituency(params[22] !=null ?WordUtils.capitalize(params[22].toString().toLowerCase())+" Constituency":"");
								candidateVo.setAddressConstituency(params[22] !=null ? params[22].toString().trim():"");
								
							}else if(apptcanditype.longValue() == 3l){
								candidateVo.setCandDesignation(params[20] !=null ?params[20].toString():"");
								cadreIds.add(tdpcadreId);
							}else if(apptcanditype.longValue() == 2l && tdpcadreId!=null){
								partyCommiteeDesigCadreIds.add(tdpcadreId);
							}else if(apptcanditype.longValue() == 1l && tdpcadreId!=null && params[23]!=null){
								candidateVo.setCandDesignation(params[3] != null ? params[3].toString() : "");
								
								designationMap.put(tdpcadreId, (Long)params[23]);
								PrCadreIds.add(tdpcadreId);								
							}
						}
						
						vo.getSubList().add(candidateVo);
					}
					
					List<Long> aptmntsIdsList = new ArrayList<Long>(setAptmnts);
					//Public Representative Contested Location Scenario && Preferable Dates Scenario For Aptmnts
					if(PrCadreIds !=null && PrCadreIds.size()>0){
						publicRepresLocaMap = locationService.getLocationMapForDesignation(designationMap,PrCadreIds);
					}
					if(aptmntsIdsList !=null && aptmntsIdsList.size()>0){
						resultList = setPreferDatesToApptmnt(aptmntsIdsList,resultList);
					}
					
				}
				
				if(cadreIds!=null && cadreIds.size()>0){
					setConstituenciesforTdpcadreIds(resultList,cadreIds);
				}
				if(partyCommiteeDesigCadreIds!=null && partyCommiteeDesigCadreIds.size()>0){
					setPartyPositionDesignationsforTdpcadreIds(resultList,partyCommiteeDesigCadreIds);
				}
				if(publicRepresLocaMap!=null && publicRepresLocaMap.size()>0){
					setPublicRepresenativeLocations(resultList,publicRepresLocaMap);
				}
				
				//FOR EXPORT TO EXCEL BLOCK REQUIRED FIELDS
				if(resultList !=null && resultList.size()>0){
					 //Candidates last visit date 
					List<Object[]> rtrnCnddtLstSttsLst=appointmentCandidateRelationDAO.getCandidateLastVisitedDtl(candidateList,inputVo.getUserId(),4l);
					if(rtrnCnddtLstSttsLst!=null && rtrnCnddtLstSttsLst.size()>0){
						for (Object[] objects : rtrnCnddtLstSttsLst) {
							AppointmentScheduleVO candidateVO=getCandidateMatchVO(resultList,(Long)objects[0]);
							 if(candidateVO!=null){
								 candidateVO.setCandidateLastVisitDate(objects[3]!=null ? objects[3].toString(): " ");
							 }
						}
					}
					
					//Candidates last status
					 List<Object[]> rtrnCnddtLstVstList=appointmentCandidateRelationDAO.getCandidateLastVisitedDtl(candidateList, inputVo.getUserId(),null);
					 
					 if(rtrnCnddtLstVstList!=null && rtrnCnddtLstVstList.size()>0){
						 for (Object[] objects : rtrnCnddtLstVstList) {
							 AppointmentScheduleVO candidateVO=getCandidateMatchVO(resultList,(Long)objects[0]);
							 if(candidateVO!=null){
								 candidateVO.setCandidateLastUpdatedStatus(objects[2]!=null ? objects[2].toString(): " ");
							 }
						}
					 }
					 //Total requested appointment counts by appointment candidates;
						List<Object[]> rtrnTtlAppntmntsCuntLst=appointmentCandidateRelationDAO.getTotalAppointmentsForCandiates(candidateList, inputVo.getUserId(),null);
						
						if(rtrnTtlAppntmntsCuntLst!=null && rtrnTtlAppntmntsCuntLst.size()>0){
							
							for (Object[] objects : rtrnTtlAppntmntsCuntLst) {
								 AppointmentScheduleVO candidateVO=getCandidateMatchVO(resultList,(Long)objects[0]);
								  if(candidateVO!=null){
									  candidateVO.setTotalRequestedAppCount(objects[1]!=null ? (Long)objects[1]: 0l);
								  }
							}
						}
						 //Total completed appointment counts by appointment candidates;
						List<Object[]> rtrnTtlCmpltdAppCntLst=appointmentCandidateRelationDAO.getTotalAppointmentsForCandiates(candidateList, inputVo.getUserId(),4l);
						
						if(rtrnTtlCmpltdAppCntLst!=null && rtrnTtlCmpltdAppCntLst.size()>0){
							for (Object[] objects : rtrnTtlCmpltdAppCntLst) {
								 AppointmentScheduleVO candidateVO=getCandidateMatchVO(resultList,(Long)objects[0]);
								  if(candidateVO!=null){
									  candidateVO.setTotalCompletedAppCount(objects[1]!=null ? (Long)objects[1]: 0l);
								  }
							}
						}
						
					List<Object[]> rtrncnddtCnsttuncyLst =null;
					if(tdpCadreIdList !=null && tdpCadreIdList.size()>0){
						rtrncnddtCnsttuncyLst = tdpCadreDAO.getCandidatesConstituency(tdpCadreIdList);
					}
					
					if(rtrncnddtCnsttuncyLst!=null && rtrncnddtCnsttuncyLst.size()>0){
						for (Object[] obj : rtrncnddtCnsttuncyLst) {
							 AppointmentScheduleVO candidateVO=getTdpCadreMatchVO(resultList,(Long)obj[0]);
							 if(candidateVO!=null){
								 candidateVO.setAddressConstituency(obj[2]!=null?obj[2].toString():" ");
							 }
						}
					}
				}
		}catch(Exception e){
			LOG.error("Exception raised at getAppointmentSearchDetails", e);
		}
		return resultList;
		
	}
	public AppointmentScheduleVO getCandidateMatchVO(List<AppointmentScheduleVO> resultList,Long candidateId){
		
		if(resultList!=null && resultList.size()>0){
			for (AppointmentScheduleVO appointmentScheduleVO : resultList) {
				for(AppointmentScheduleVO vo:appointmentScheduleVO.getSubList()){
					if(vo.getId().equals(candidateId)){
					   return vo;
					}
				}
			}
		}
		return null;
	}
public AppointmentScheduleVO getTdpCadreMatchVO(List<AppointmentScheduleVO> resultList,Long tdpCadreId){
		
		if(resultList!=null && resultList.size()>0){
			for (AppointmentScheduleVO appointmentScheduleVO : resultList) {
				for(AppointmentScheduleVO vo:appointmentScheduleVO.getSubList()){
					if(vo.getTdpCadreId()!=null){
						if(vo.getTdpCadreId().equals(tdpCadreId)){
							   return vo;
							}
					}
				}
			}
		}
		return null;
	}
	public void setPublicRepresenativeLocations(List<AppointmentScheduleVO> resultList,Map<Long,String> publicRepresLocaMap){
		
		try{
			
			if(publicRepresLocaMap!=null && publicRepresLocaMap.size()>0){
				
				for( AppointmentScheduleVO appt :resultList){
					
					if(appt.getSubList()!=null && appt.getSubList().size()>0){
						
						for(AppointmentScheduleVO candi :appt.getSubList()){
							
							if(candi.getApptCandiTypeId()!=null && candi.getApptCandiTypeId().longValue() == 1l){
								
								if(candi.getTdpCadreId()!=null){
									String location = publicRepresLocaMap.get(candi.getTdpCadreId());
									if(location!=null && !location.isEmpty()){
										candi.setConstituency(WordUtils.capitalize(location.toLowerCase()));
									}else{
										candi.setConstituency(location);
									}
									
								}
							}
						}
					}
				}
			}
			
		}catch (Exception e) {
			LOG.error("Exception raised at setPublicRepresenativeLocations", e);
		}
		
	}
	
	public void setPartyPositionDesignationsforTdpcadreIds(List<AppointmentScheduleVO> resultList,List<Long> cadreIds){
		
		try{
			
			Map<Long,String> partyCommiteeDesignationsMap = new HashMap<Long, String>();
			 List<Object[]> partyPositionDetails= tdpCommitteeMemberDAO.getPartyPositionsBycadreIdsList(cadreIds);
			 
			 if(partyPositionDetails !=null && partyPositionDetails.size()>0)
			 {
				 CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
				 
				 for (Object[] partyPosition : partyPositionDetails) {

					 String level=partyPosition[0] != null ? partyPosition[0].toString() : "" ;
					 String role=partyPosition[1] != null ? partyPosition[1].toString() : "";
					 
					 String state = commonMethodsUtilService.getStringValueForObject(partyPosition[6]);
					 
					 String commiteestr=partyPosition[2] != null ? partyPosition[2].toString() : "";
					 
					 if(level != null && !level.isEmpty()&&level.equalsIgnoreCase("state"))
					 {
						 level = state+" "+level;
					 }
					 String partyPositionStr = level +" " +role+" ( "+commiteestr+" )";
					 if(!partyPositionStr.isEmpty())
					 { 
						 partyCommiteeDesignationsMap.put((Long)partyPosition[5],partyPositionStr);
					 }
				 }
			 }
			
			if(partyCommiteeDesignationsMap!=null && partyCommiteeDesignationsMap.size()>0){
				
				for( AppointmentScheduleVO appt :resultList){
					
					if(appt.getSubList()!=null && appt.getSubList().size()>0){
						
						for(AppointmentScheduleVO candi :appt.getSubList()){
							
							if(candi.getApptCandiTypeId()!=null && candi.getApptCandiTypeId().longValue() == 2l){
								
								if(candi.getTdpCadreId()!=null){
									candi.setCandDesignation(partyCommiteeDesignationsMap.get(candi.getTdpCadreId()));
								}
							}
						}
					}
				}
			}
			
		}catch(Exception e) {
			LOG.error("Exception raised at setPartyPositionDesignationsforTdpcadreIds", e);
		}
		 
	}
	
	public void setConstituenciesforTdpcadreIds(List<AppointmentScheduleVO> resultList,List<Long> cadreIds){
		
		try{
			Map<Long,String> constMap = new HashMap<Long,String>();
			if(cadreIds!=null && cadreIds.size() >0 ){
				List<Object[]> constlist = tdpCadreDAO.getConstituencyForCadreIds(cadreIds);
				if(constlist!=null && constlist.size()>0){
					for(Object[] obj :constlist){
						constMap.put((Long)obj[0],obj[1].toString());
					}
				}
			}
			if(constMap!=null && constMap.size()>0){
				
				for( AppointmentScheduleVO appt :resultList){
					
					if(appt.getSubList()!=null && appt.getSubList().size()>0){
						
						for(AppointmentScheduleVO candi :appt.getSubList()){
							
							if(candi.getApptCandiTypeId()!=null && candi.getApptCandiTypeId().longValue() == 3l){
								if(candi.getTdpCadreId()!=null){
									String location = constMap.get(candi.getTdpCadreId());
									if(location == null || location.isEmpty()){
										candi.setConstituency("");
									}else{
										candi.setConstituency(WordUtils.capitalize(location.toLowerCase())+" Constituency");
									}
									
								}
							}
						}
					}
				}
			}
		}catch (Exception e) {
			LOG.error("Exception raised at setConstituenciesforTdpcadreIds", e);
		}
		
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
	 
	 /*public String pdfViewForAppointment(List<AppointmentDetailsVO> resultList,String labelName)
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
					 Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0)); 
					 titleParagraph.add(new Chunk(labelName +" Appointment Members"));
					 titleParagraph.setAlignment(Element.ALIGN_CENTER);
					 titleParagraph.setFont(font);
					 titleParagraph.setSpacingAfter(5);
					 titleParagraph.setFont(font);
					 document.add(titleParagraph);
					 int tableWidth = 5;
					 float[] columnWidths = new float[tableWidth];
					 
					 int rowspan = 0;
					 int colSpan = 0;
					 columnWidths = new float[]{5f, 3f, 3f, 5f,3f};
					 
					 for(AppointmentDetailsVO vo : resultList)
					 {
							border.setActive(true);
						 	Paragraph p = new Paragraph();
						 	p.setAlignment(Element.ALIGN_CENTER);
						 
						   Font f = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0));
						   p.add(new Chunk("Appointment ID : "+vo.getAptUniqueCode(),f));
						   p.add(new Chunk("  ",f));
						  
						   if(vo.getPriority() != null && vo.getPriority().length() > 0)
							   p.add(new Chunk("Priority : "+vo.getPriority(),f));
						   else
							   p.add(new Chunk("Priority : -") + " ");  
						   
						   p.add(new Chunk("  "));
						
						   if(vo.getDateString() != null && vo.getDateString().length() > 0)
							   p.add(new Chunk("Requested Date : "+vo.getDateString(),f));
						   else
							   p.add(new Chunk("Requested Date : - ")+ " ");  
						   p.add(new Chunk("  "));
						   if(vo.getStatus() != null && vo.getStatus().length() > 0)
							   p.add(new Chunk("Status : "+vo.getStatus(),f));
						   else
							   p.add(new Chunk("Status : - ")+ " ");  
						   p.add(Chunk.NEWLINE);
						  
						   if(vo.getSubject() != null && vo.getSubject().length() > 0)
							   p.add(new Chunk("Purpose : "+vo.getSubject(),f));
						   else
							   p.add(new Chunk("Purpose : -")); 
						 
						   Paragraph p1 = new Paragraph();
						  if(vo.getSubList() != null && vo.getSubList().size() > 0)
						  {
						  for(AppointmentDetailsVO subVo : vo.getSubList())
						 {
							  border.setActive(true);
							  p1.add(new Chunk(""));  
							 // 
							 
							   PdfPTable table = new PdfPTable(columnWidths);
							  
								 //special font sizes
								   Font bfBold12 = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0)); 
								   Font bf12 = new Font(FontFamily.HELVETICA, 6); 
								
							 //insert column headings
							   
							   insertCell(table, "", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan); 
							   insertCell(table, "APPOINTMENT ID", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan);
							   insertCell(table, "CREATED DATE", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan);
							   insertCell(table, "APPOINTMENT PREFERABLE DATES", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan); 
							   insertCell(table, "STATUS", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan); 
							   
							   StringBuffer sb = new StringBuffer();
							   
							  sb.append(subVo.getName()+"\nContact Number: - ");
							  
							  if(subVo.getMobileNo() != null && subVo.getMobileNo().length() > 0)
								  sb.append(""+subVo.getMobileNo()+"\n"); 
							  else
								 sb.append("\n");
							  
							  if(subVo.getDesignation() != null && subVo.getDesignation().length() > 0)
								  sb.append("Designation: " +subVo.getDesignation()+"\n"); 
							  else
								  sb.append("Designation: - "+"\n" );  
							  
							  if(subVo.getConstituency() != null && subVo.getConstituency().length() > 0)
								  sb.append("Constituency : " +subVo.getConstituency()+"\n"); 
							  else
								  sb.append("Constituency : - "+"\n" ); 
							 
							 if(subVo.getSubList() != null && subVo.getSubList().size() > 0)
								 insertCell(table, sb.toString(), Element.ALIGN_LEFT, colSpan, bf12,subVo.getSubList().size());
							 else
							 {
								   insertCell(table, sb.toString(), Element.ALIGN_LEFT, colSpan, bf12,0); 
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
										 insertCell(table, aptVo.getDateType() +":" + aptVo.getMinDate() +"-"+aptVo.getMaxDate(), Element.ALIGN_LEFT, colSpan, bf12,0);
									 else if(aptVo.getDateTypeId() != null && aptVo.getDateTypeId() == 1)
										 insertCell(table, aptVo.getApptpreferableDates(), Element.ALIGN_LEFT, colSpan, bf12,0);
									 else
									 insertCell(table, "", Element.ALIGN_LEFT, colSpan, bf12,0);
									 
									 insertCell(table, aptVo.getStatus(), Element.ALIGN_CENTER, colSpan, bf12,0);
								 }
							 }
							 table.spacingBefore();    
							 table.spacingAfter();      

							 p1.add(table);
							 p1.add(Chunk.NEWLINE);
						 
						 }
						  }
						  else
						  {
							  border.setActive(true);
							 p1.add(new Chunk(""));
							  
							  PdfPTable table = new PdfPTable(columnWidths);
						
								 //special font sizes
								   Font bfBold12 = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0)); 
								   Font bf12 = new Font(FontFamily.HELVETICA, 6); 
								
							 //insert column headings
							   
							   insertCell(table, "", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan); 
							   insertCell(table, "APPOINTMENT ID", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan);
							   insertCell(table, "CREATED DATE", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan);
							   insertCell(table, "APPOINTMENT PREFERABLE DATES", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan); 
							   insertCell(table, "STATUS", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan); 
							   insertCell(table, "No Data Available ", Element.ALIGN_CENTER, 3, bf12,0);
							  
							   p1.add(table);
							  p1.add(Chunk.NEWLINE);
						  }
						  
						  Paragraph p2 = new Paragraph();
						  border.setActive(true);
						  System.out.println("Kamal");
						  Font bf12 = new Font(FontFamily.HELVETICA, 8); 
						  p2.add(new Chunk(" REQUESTED DATES :",bf12));
						  if(vo.getApptpreferableDates() != null && vo.getDateTypeId() == 1)
						  {
							p2.add(new Chunk(vo.getApptpreferableDates(),bf12))	;
						  }
						  else if(vo.getApptpreferableDates() == null && vo.getDateTypeId() > 1)
						  {
							  p2.add(new Chunk(vo.getDateType() + '('+vo.getMinDate()+" to " +vo.getMaxDate()+')',bf12))	;
						  }
						  else
							  p2.add(new Chunk(""));
						
						    border.setActive(true);
						  Paragraph p3 = new Paragraph();
						  float[] columnWidths2 = new float[]{6f,3f,3f};
						  PdfPTable table3 = new PdfPTable(columnWidths2);
						  table3.setWidthPercentage(100);
						  //special font sizes
						   Font bfBold12 = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0)); 
						  
						  insertCell(table3, "APPOINTMENT COMMENTS", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan); 
						  insertCell(table3, "APPOINTMENT DATE", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan);
						  insertCell(table3, "TIME", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan);
						  
						  insertCell(table3, "", Element.ALIGN_CENTER, colSpan, bf12,0);
						  insertCell(table3, "", Element.ALIGN_CENTER, colSpan, bf12,0);
						  insertCell(table3, "", Element.ALIGN_CENTER, colSpan, bf12,0);
						  p2.add(table3);
						 document.add(p); 
						 document.add(p1);
						 document.add(p2);
						
						 document.add(Chunk.NEWLINE);
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
	 }*/
	 
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
					 Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0)); 
					 titleParagraph.add(new Chunk(labelName +" Appointment Members"));
					 document.add(Chunk.NEWLINE);
					 titleParagraph.setAlignment(Element.ALIGN_CENTER);
					 titleParagraph.setFont(font);
					 titleParagraph.setSpacingAfter(5);
					 titleParagraph.setFont(font);
					 document.add(titleParagraph);
					 
					 int tableWidth = 5;
					 float[] columnWidths = new float[tableWidth];
					 
					 int rowspan = 0;
					 int colSpan = 0;
					 columnWidths = new float[]{5f, 3f, 3f, 5f,3f};
					 
					 for(AppointmentDetailsVO vo : resultList)
					 {
						 PdfPTable table = new PdfPTable(columnWidths);
						 table.setWidthPercentage(100);
						 table.setKeepTogether(true);
						 Font bfBold12 = new Font(FontFamily.HELVETICA, 7, Font.BOLD, new BaseColor(0, 0, 0)); 
						 Font bf12 = new Font(FontFamily.HELVETICA, 7);
						 
						 StringBuffer sb1 = new StringBuffer();
						 sb1.append("Appointment ID : "+vo.getAptUniqueCode()+"                                      ");
						 sb1.append("Priority : "+vo.getPriority()+"                                      ");
						 sb1.append("Requested Date : "+vo.getDateString()+"                                      ");
						 sb1.append("Status : "+vo.getStatus()+"                                      ");
						 sb1.append("\n\n");
						 sb1.append("Purpose : "+vo.getSubject()+" ");
						 
						 insertCell(table,sb1.toString(), Element.ALIGN_LEFT,5, bfBold12,rowspan);
						 int index = 0;
						 
						 for(AppointmentDetailsVO subVo : vo.getSubList())
						 {
							 if(index != 0)
								 insertCell(table,"", Element.ALIGN_CENTER,5, bfBold12,rowspan); 
							 index++;
							 
							 insertCell(table, "", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan); 
							 insertCell(table, "APPOINTMENT ID", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan);
							 insertCell(table, "CREATED DATE", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan);
							 insertCell(table, "PREFERABLE DATES", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan); 
							 insertCell(table, "STATUS", Element.ALIGN_CENTER, colSpan, bfBold12,rowspan); 
							 
							 StringBuffer sb = new StringBuffer();
							   
							 sb.append(subVo.getName()+"\nContact Number : ");
							  
							 if(subVo.getMobileNo() != null && subVo.getMobileNo().length() > 0)
								  sb.append(""+subVo.getMobileNo()+"\n"); 
							 else
								 sb.append("\n");
							  
							 if(subVo.getDesignation() != null && subVo.getDesignation().length() > 0)
								 sb.append("Designation : " +subVo.getDesignation()+"\n"); 
							 else
								 sb.append("Designation : - "+"\n" );  
							  
							 if(subVo.getConstituency() != null && subVo.getConstituency().length() > 0)
								 sb.append("Constituency : " +subVo.getConstituency()+"\n"); 
							 else
								 sb.append("Constituency : - "+"\n" ); 
							 
							 if(subVo.getSubList() != null && subVo.getSubList().size() > 0)
								 insertCell(table, sb.toString(), Element.ALIGN_LEFT, colSpan, bf12,subVo.getSubList().size());
							 else
							 {
								   insertCell(table, sb.toString(), Element.ALIGN_LEFT, colSpan, bf12,0); 
								   insertCell(table, "", Element.ALIGN_CENTER, colSpan, bf12,0);
								   insertCell(table, "", Element.ALIGN_CENTER, colSpan, bf12,0);
								   insertCell(table, "", Element.ALIGN_CENTER, colSpan, bf12,0);
								   insertCell(table, "", Element.ALIGN_CENTER, colSpan, bf12,0);
									
							 }
							 
							 if(subVo.getSubList() != null && subVo.getSubList().size() > 0)
							 {
								 for(AppointmentDetailsVO aptVo : subVo.getSubList())
								 {
									 insertCell(table, aptVo.getAptUniqueCode(), Element.ALIGN_CENTER, colSpan, bf12,0);
									 insertCell(table, aptVo.getDateString(), Element.ALIGN_CENTER, colSpan, bf12,0);
									 if(aptVo.getDateTypeId() != null && aptVo.getDateTypeId() > 1)
										 insertCell(table, aptVo.getDateType() +":" + aptVo.getMinDate() +"-"+aptVo.getMaxDate(), Element.ALIGN_LEFT, colSpan, bf12,0);
									 else if(aptVo.getDateTypeId() != null && aptVo.getDateTypeId() == 1)
										 insertCell(table, aptVo.getApptpreferableDates(), Element.ALIGN_LEFT, colSpan, bf12,0);
									 else
									 insertCell(table, "", Element.ALIGN_LEFT, colSpan, bf12,0);
									 
									 insertCell(table, aptVo.getStatus(), Element.ALIGN_CENTER, colSpan, bf12,0);
								 }
							 }
							 
						 }
						 
						 insertCell(table,"REQUESTED DATES :"+(vo.getApptpreferableDates() != null ? vo.getApptpreferableDates() : ""), Element.ALIGN_LEFT,5,bfBold12,rowspan);
						 
						 insertCell(table,"APPOINTMENT COMMENTS", Element.ALIGN_LEFT,3,bfBold12,rowspan);
						 insertCell(table,"APPOINTMENT DATE", Element.ALIGN_CENTER,1,bfBold12,rowspan);
						 insertCell(table,"TIME", Element.ALIGN_CENTER,1,bfBold12,rowspan);
						 
						 StringBuffer sb2 = new StringBuffer();
						 sb2.append("  \n  ");
						 sb2.append("  \n  ");
						 
						 insertNoBorderCell(table,"", Element.ALIGN_LEFT,3,bfBold12,rowspan);
						 insertNoBorderCell(table,"", Element.ALIGN_LEFT,1,bfBold12,rowspan);
						 insertNoBorderCell(table,"", Element.ALIGN_LEFT,1,bfBold12,rowspan);
						 
						 insertNoBorderCell2(table,"", Element.ALIGN_LEFT,3,bfBold12,rowspan);
						 insertNoBorderCell2(table,"", Element.ALIGN_LEFT,1,bfBold12,rowspan);
						 insertNoBorderCell2(table,"", Element.ALIGN_LEFT,1,bfBold12,rowspan);
						 
						 document.add(table);
						 document.add(Chunk.NEWLINE);
					 }
					 
					
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
	 	private void insertNoBorderCell(PdfPTable table, String text, int align, int colspan, Font font,int rowspan){
		  
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
		  cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
		  //add the call to the table
		  table.addCell(cell);
		 }
	 	
	 	private void insertNoBorderCell2(PdfPTable table, String text, int align, int colspan, Font font,int rowspan){
			  
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
			  cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
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
			  public ResultStatus setTimeSlotForAppointment(Long appointmentId,String dateStr,String fromTime,String toTime,Long userId,String type,Long timeSlotId,String commentTxt,Long apptUserId){
			    ResultStatus rs = new ResultStatus();
			    try {
			    	
			    	  //getPresent Status
				       Long currentStatusId =  appointmentDAO.getCurrentAppointmentStatus(appointmentId);
			    				    	
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
			        	   timeSlot = appointmentTimeSlotDAO.getAppointmentTimeSlotByAppointmentId(appointmentId);
			        	   if(timeSlot == null)
			        	   {
			        		   timeSlot = new AppointmentTimeSlot();
			        		   timeSlot.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			        	   }
			           }
			           
				       timeSlot.setAppointmentId(appointmentId);
				       timeSlot.setDate(date);
				       timeSlot.setFromDate(appointmentFromTime);
				       timeSlot.setToDate(appointmentToTime);
				       timeSlot.setInsertedBy(userId);
				       timeSlot.setUpdatedBy(userId);
				       timeSlot.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				       timeSlot.setIsDeleted("N");
			      
				   //TimeSlot Booking For An Appointment
				       AppointmentTimeSlot appointmentTimeSlot = appointmentTimeSlotDAO.save(timeSlot);
				       
				       //Allocating To 'Schedule' Status Of An Appointment
				       Integer updtdSts = appointmentDAO.updateAppntmntStatusById(appointmentTimeSlot.getAppointmentId(), dateUtilService.getCurrentDateAndTime() );
				       
				     //Sending Sms To TimeSlot Booked Candidates
				       
				       if (checkIsValidForSendingSMS(IConstants.APPOINTMENT_STATUS_SCHEDULED)){
				    	   
				    	   AppointmentUpdateStatusVO inputVO = new AppointmentUpdateStatusVO();
					       inputVO.setAppointmentId(appointmentTimeSlot.getAppointmentId());  
						   //inputVO.setSmsText("Your Appointment Fixed on " +" "+new SimpleDateFormat("yyyy-MM-dd").format(appointmentTimeSlot.getDate())+" " +"From"+" " +new SimpleDateFormat("HH:mm").format(appointmentTimeSlot.getFromDate())+" " +"To"+" "+new SimpleDateFormat("HH:mm").format(appointmentTimeSlot.getToDate()));
					       inputVO.setSmsText( "Your Appointment Fixed on " +" "+new SimpleDateFormat("yyyy-MM-dd").format(appointmentTimeSlot.getDate()) );
					       
					       inputVO.setUserId(userId);
						   inputVO.setAppointmentStatusId(IConstants.APPOINTMENT_STATUS_SCHEDULED);
						   sendSms(inputVO);
				       }
				       
				       
				       //Appointment Tracking Saving
				       if(type !=null && type.equalsIgnoreCase("update")){
				    	   Long apptActionId =1l;
				    	   if(commentTxt!=null && !commentTxt.isEmpty()){
				    		   apptActionId =2l;
				    	   }
				    	   saveAppointmentTrackingDetails(appointmentTimeSlot.getAppointmentId(),apptActionId,currentStatusId,
					       			IConstants.APPOINTMENT_STATUS_SCHEDULED,userId,commentTxt);
				       }else{
				    	   saveAppointmentTrackingDetails(appointmentTimeSlot.getAppointmentId(),IConstants.APPOINTMENT_ACTION_STATUS_CHANGE,currentStatusId,
					       			IConstants.APPOINTMENT_STATUS_SCHEDULED,userId,commentTxt);
				       }
				      
				      
				     //void appt status
				     /*List<Long> apptIds = getAppointmentIdsForVoid(appointmentId,IConstants.APPOINTMENT_STATUS_WAITING,apptUserId);
				     if(apptIds!=null && apptIds.size()>0){
				    	 appointmentDAO.updateApptStatusbyApptIds(apptIds,dateUtilService.getCurrentDateAndTime(),IConstants.APPOINTMENT_STATUS_VOID,userId);
				     }*/
				      
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
			
			//status updation.
			
			Long currentStatusId = inputVO.getCurrentStatusId();
			Long toStatusId = null;
			Long appointmentActionId = null;
			
			boolean issaveTracking = false;
			
			if( inputVO.getCommented() != null  && inputVO.getCommented().trim().length() > 0){
				
				issaveTracking =true;
				
				appointmentActionId = 2l;
				toStatusId = currentStatusId;
			}
			
			if( inputVO.getStatusId()!=null && inputVO.getStatusId() > 0l ){
				issaveTracking =true;
				
				appointmentActionId = 1l;
				toStatusId = inputVO.getStatusId();
				appointmentDAO.updateApptStatusbyApptId(inputVO.getAppointmentId(),dateUtilService.getCurrentDateAndTime(),inputVO.getStatusId(),userId);
			}
			
			if( issaveTracking ){
				saveAppointmentTrackingDetails(inputVO.getAppointmentId(),appointmentActionId,currentStatusId,toStatusId,userId,inputVO.getCommented());
			}
			
			
			//SMS sending
			if(inputVO.isIssmsChecked())
			{
				AppointmentUpdateStatusVO smsVO = new AppointmentUpdateStatusVO();
				smsVO.setAppointmentId(inputVO.getAppointmentId());
				smsVO.setSmsText(inputVO.getSmsText());
				smsVO.setUserId(userId);
				
				if ( toStatusId!= null && toStatusId > 0l ){
					smsVO.setAppointmentStatusId(toStatusId);
				}else{
					smsVO.setAppointmentStatusId(currentStatusId);
				}
				result = sendSmsForAppointment(smsVO);
				
			}else{
				
				result.setResultCode(0);
				result.setMessage("success");
			}
			
			/*//unvoid old appointments
			if(inputVO.getStatusId().equals(IConstants.APPOINTMENT_STATUS_RESCHEDULED) || inputVO.getStatusId().equals(IConstants.APPOINTMENT_STATUS_CANCELLED) || inputVO.getStatusId() == IConstants.APPOINTMENT_STATUS_RESCHEDULED || inputVO.getStatusId() == IConstants.APPOINTMENT_STATUS_CANCELLED){
				List<Long> apptIds =getAppointmentIdsForVoid(inputVO.getAppointmentId(),IConstants.APPOINTMENT_STATUS_VOID,inputVO.getApptUserId());
				
				if(apptIds!=null && apptIds.size()>0){
			    	 appointmentDAO.updateApptStatusbyApptIds(apptIds,dateUtilService.getCurrentDateAndTime(),IConstants.APPOINTMENT_STATUS_WAITING,userId);
			     }
			}*/
			
		}
		catch (Exception e) {
			LOG.error("Exception raised in updateAppointmentStatus", e);
			result.setResultCode(2);
			result.setMessage("fail");
			
		}
		return result;
	}
	
	/*public ResultStatus sendSmsForAppointment(AppointmentUpdateStatusVO inputVO)
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
	}*/
	
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
						changeAppointmentStatus(ids,IConstants.APPOINTMENT_STATUS_WAITING,registrationId,dateUtilService.getCurrentDateAndTime());
				        
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
		        	vo.setLocationName(localBodi[4].toString() +" "+ localBodi[2].toString());
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
	public List<IdNameVO> getConstituenciesByDistrict(Long districtId)//swadhin
	{
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try{
			List<Object[]> list = null;
			List<Long> constituencyList = null;
			List<Long> oldConstituencyList = null;    
			if(districtId.longValue() == 517L || districtId.longValue() == 518L){
				constituencyList = districtConstituenciesDAO.getConstituenciesOfDistrictById(districtId);
				list = constituencyDAO.getConstituenciesNamesByIds(constituencyList);
			}else if(districtId.longValue() == 13L){
				constituencyList = districtConstituenciesDAO.getConstituenciesOfDistrictById(517L);
				oldConstituencyList = constituencyDAO.getConstituenciesInADistrict(districtId);
				oldConstituencyList.removeAll(constituencyList);
				list = constituencyDAO.getConstituenciesNamesByIds(oldConstituencyList);
			}else{  
				list = constituencyDAO.getConstituenciesByDistrictId(districtId);
			}    
			Map<Long,Long> constIdAndNoMap = new HashMap<Long,Long>();
			 List<Long> constIds = new ArrayList<Long>();
			 if(list != null && list.size() > 0)
			 {
				 
				 for(Object[] params : list)
				 {
					 constIds.add((Long)params[0]);  
					 IdNameVO vo = new IdNameVO();
					 vo.setId((Long)params[0]);
					 vo.setName(params[1].toString());
					 returnList.add(vo);
				 }
				 List<Object[]> constOrderList = delimitationConstituencyDAO.getConstituencyNumbersForConstituenctIds(constIds);
				 if(constOrderList != null && constOrderList.size() > 0){
						for(Object[] param : constOrderList){
							constIdAndNoMap.put(param[0] != null ? (Long)param[0] : 0L, param[1] != null ? (Long)param[1] : 0L);
						}
				}
				 if(returnList != null && returnList.size() > 0){
					 for(IdNameVO param : returnList){
						 if(constIdAndNoMap.get(param.getId()) != null){
							 param.setConstituencyNo(constIdAndNoMap.get(param.getId()));
						 }
					 }       
				 }
				 Collections.sort(returnList,new Comparator<IdNameVO>() {
						public int compare(IdNameVO o1, IdNameVO o2) {
							if(o1.getConstituencyNo() != null && o1.getConstituencyNo() > 0L && o2.getConstituencyNo() != null && o2.getConstituencyNo() > 0L)
								return o1.getConstituencyNo().compareTo(o2.getConstituencyNo());
							else
								return 0;      
						}
					});
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
	
	public ResultStatus saveAppointmentTrackingDetails(final Long appointmentId,final Long appointmentActionId,final Long fromApptStatusId,final Long toApptStatusId,final Long userId,final String remarks){
		
		ResultStatus rs = new ResultStatus();
		try {
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
		        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
		        	
		        	Date currentDateAndTime  = dateUtilService.getCurrentDateAndTime();
					
		        	AppointmentComment appointmentComment = null;
					if( remarks!= null && remarks.trim().length() > 0){
						
						appointmentComment = new AppointmentComment();
						
						appointmentComment.setAppointmentId(appointmentId);
						appointmentComment.setAppointmentStatusId(toApptStatusId);
						appointmentComment.setComment(remarks);
						appointmentComment.setInsertedBy(userId);
						appointmentComment.setUpdatedBy(userId);
						appointmentComment.setInsertedTime(currentDateAndTime);
						appointmentComment.setUpdatedTime(currentDateAndTime);
						
						appointmentComment = appointmentCommentDAO.save(appointmentComment);
					}
					
					AppointmentTracking appointmentTracking=new AppointmentTracking();
					
					if(appointmentId!=null && appointmentId>0l){
						appointmentTracking.setAppointmentId(appointmentId);
					}
					
					if(appointmentActionId!=null && appointmentActionId>0l){
						appointmentTracking.setAppointmentActionId(appointmentActionId);
						
					}
					
					if(fromApptStatusId!=null && fromApptStatusId>0l){
						appointmentTracking.setFromAppointmentStatusId(fromApptStatusId);
					}
					if(toApptStatusId!=null && toApptStatusId>0l){
						appointmentTracking.setAppointmentStatusId(toApptStatusId);
					}
					
					if(appointmentComment!=null){
						appointmentTracking.setAppointmentCommentId(appointmentComment.getAppointmentCommentId());
					}
					
					if(userId!=null && userId>0l){
						appointmentTracking.setUserId(userId);
					}
					appointmentTracking.setActionTime(currentDateAndTime);
					
					appointmentTrackingDAO.save(appointmentTracking);
		        }
			});
			
			rs.setExceptionMsg("success");
			rs.setResultCode(0);
			
		} catch (Exception e) {
			
			rs.setExceptionMsg("failure");
			rs.setResultCode(1);
			LOG.error("Exception raised at saveAppointmentTrackingDetails() in AppointmentService class ", e);
		}
		
		return rs;
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
			 
			List<Object[]> appCommentsList = appointmentCommentDAO.getAppointmentCommentsForViewHistory(appointmentIds);
			
			if(appCommentsList != null && appCommentsList.size() > 0)
			for(Object[] obj : appCommentsList)
			{
				 AppHistoryVO vo = new AppHistoryVO();
				 vo.setId(obj[0] != null ? (Long)obj[0] : 0l);
				 vo.setPurpose(obj[1] != null ? obj[1].toString() : "");
				 vo.setUniqueCode(obj[2] != null ? obj[2].toString() : "");
				 vo.setStatusId(obj[3] != null ? (Long)obj[3] : 0l);
				 vo.setStatus(obj[4] != null ? obj[4].toString() : "");
				 vo.setComment(obj[5] != null ? obj[5].toString() : "");
				 vo.setCreatedOn(obj[6] != null ? obj[6].toString().substring(0,19) : "");
				 vo.setUser(((obj[7] != null ? obj[7].toString() : "") + " "+ (obj[8] != null ? obj[8].toString() : "")).trim());
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
					
					Long  statusId = appintstts.getAppointmentStatusId();
					
					if( statusId !=  IConstants.APPOINTMENT_STATUS_WITHDRAWN.longValue() && 
						statusId !=  IConstants.APPOINTMENT_STATUS_CANCELLED.longValue() &&
						statusId !=  IConstants.APPOINTMENT_STATUS_NOTATTENDED.longValue() && 
						statusId !=  IConstants.APPOINTMENT_STATUS_RESCHEDULED.longValue() ){
					
						IdNameVO appointVO=new IdNameVO();
						appointVO.setId(appintstts.getAppointmentStatusId());
						appointVO.setName(appintstts.getStatus());
						appointVO.setAvailableCount(0l);
						candidteStusLst.add(appointVO);
				    }
			     }
			}
			
			List<Object[]> candidteStusCnt=appointmentCandidateRelationDAO.getAppointStatusOverviewforCandidate(apointmntcandidteId,apptUserId);
			if(candidteStusCnt != null && candidteStusCnt.size() > 0)
			{
				for(Object[] params : candidteStusCnt)
				{
					IdNameVO vo = null;
					
					Long statusId =params[1]!=null? (Long)params[1]:0l;
					if( statusId == IConstants.APPOINTMENT_STATUS_CANCELLED.longValue() ||
					    statusId == IConstants.APPOINTMENT_STATUS_RESCHEDULED.longValue() ||
					    statusId == IConstants.APPOINTMENT_STATUS_NOTATTENDED.longValue() ){
						
						vo  = getMathedStatsVO(candidteStusLst,IConstants.APPOINTMENT_STATUS_APPROVED);
					}else{
						vo = getMathedStatsVO(candidteStusLst,statusId);
					}
					
					if(vo != null){
						vo.setAvailableCount(vo.getAvailableCount() + (Long)params[0]);
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
			
			appCandi.setName(basicInfo.getName() != null ? basicInfo.getName().trim() : "");
			
			if(basicInfo.getDesignationId() != null && basicInfo.getDesignationId()>0){
				appCandi.setDesignationId(basicInfo.getDesignationId());
			}
				
			if(basicInfo.getMobileNo() != null && !basicInfo.getMobileNo().isEmpty()){
				appCandi.setMobileNo(basicInfo.getMobileNo().trim());
			}
			
			if(basicInfo.getLocationScopeId() !=null && basicInfo.getLocationScopeId() > 0)
			{
				appCandi.setLocationScopeId(basicInfo.getLocationScopeId());
			
				if(basicInfo.getLocationScopeId().longValue() == 3l && basicInfo.getDistrictId() != null && basicInfo.getDistrictId() > 0)			 		//dist
					appCandi.setLocationValue(basicInfo.getDistrictId());
				
				else if(basicInfo.getLocationScopeId().longValue() == 4l && basicInfo.getConstituencyId() != null && basicInfo.getConstituencyId() > 0)				//const
					appCandi.setLocationValue(basicInfo.getConstituencyId());
				
				else if((basicInfo.getLocationScopeId().longValue() == 5l || basicInfo.getLocationScopeId().longValue() == 7l) && (basicInfo.getTehsilId() != null && basicInfo.getTehsilId() > 0))
				{		//tehsil || Muncipality
					Long id = Long.valueOf(basicInfo.getTehsilId().toString().substring(1));
					appCandi.setLocationValue(id);
				}
				else if((basicInfo.getLocationScopeId().longValue() == 6l || basicInfo.getLocationScopeId().longValue() == 8l) && (basicInfo.getVillageId() != null && basicInfo.getVillageId() > 0))
				{		//Village || Ward
					Long id = Long.valueOf(basicInfo.getVillageId().toString());
					appCandi.setLocationValue(id);
				}
			
				//user address saving logics
				
				UserAddress userAddress = null;
				if( basicInfo.getDistrictId() != null && basicInfo.getDistrictId() > 0l){
					
					userAddress = new UserAddress();//Minimum should know the District,then create the object for UserAddress
					
					userAddress.setDistrict(districtDAO.get(basicInfo.getDistrictId()));
				}
				
				if(basicInfo.getDistrictId() !=null && basicInfo.getDistrictId()>10){
					userAddress.setState(stateDAO.get(1l));
				}else if(basicInfo.getDistrictId() !=null && basicInfo.getDistrictId() >0 && basicInfo.getDistrictId()<=10){
					userAddress.setState(stateDAO.get(36l));
				}
					
				if(basicInfo.getConstituencyId() != null && basicInfo.getConstituencyId() > 0l)
					userAddress.setConstituency(constituencyDAO.get(basicInfo.getConstituencyId()));
				
				if(basicInfo.getTehsilId() != null && basicInfo.getTehsilId() > 0l && basicInfo.getTehsilId().toString().substring(0, 1).equalsIgnoreCase("4")){
					userAddress.setTehsil(tehsilDAO.get(Long.valueOf(basicInfo.getTehsilId().toString().substring(1))));
					if(basicInfo.getVillageId() != null && basicInfo.getVillageId() > 0l){
						//userAddress.setPanchayatId(Long.valueOf(basicInfo.getVillageId().toString().substring(1)));
						userAddress.setPanchayatId(Long.valueOf(basicInfo.getVillageId().toString()));
					}
						
				}
				else if(basicInfo.getTehsilId() != null && basicInfo.getTehsilId() > 0l && basicInfo.getTehsilId().toString().substring(0, 1).equalsIgnoreCase("5")){
					userAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.valueOf(basicInfo.getTehsilId().toString().substring(1))));
					if(basicInfo.getVillageId() != null && basicInfo.getVillageId() > 0l)
					{
						//userAddress.setWard(constituencyDAO.get(Long.parseLong(basicInfo.getVillageId().toString().substring(1))));
						//userAddress.setWard(constituencyDAO.get(Long.parseLong(basicInfo.getVillageId().toString().substring(1))));
						userAddress.setWard(constituencyDAO.get(Long.parseLong(basicInfo.getVillageId().toString())));
					}
				}
				
				if(userAddress != null){
					userAddress = userAddressDAO.save(userAddress);
				}
				
				if(userAddress != null){
					appCandi.setUserAddress(userAddress);
				}
			}
			
			
			if(basicInfo.getVoterCardNo() !=null && !basicInfo.getVoterCardNo().isEmpty()){
				appCandi.setVoterIdCardNo(basicInfo.getVoterCardNo().trim());
				appCandi.setVoterId(voterCardIdsMap.get(basicInfo.getVoterCardNo().trim()));
			}
			if(basicInfo.getMembershipNum() !=null && !basicInfo.getMembershipNum().isEmpty()){
				appCandi.setMembershipId(basicInfo.getMembershipNum().trim());
				appCandi.setTdpCadreId(cadreIdsMap.get(basicInfo.getMembershipNum().trim()));
			}
			if(basicInfo.getCandiImageUrl() !=null && !basicInfo.getCandiImageUrl().isEmpty()){
				appCandi.setImageURL(basicInfo.getCandiImageUrl().trim());
			}
			if(basicInfo.getCandidateTypeId() !=null && basicInfo.getCandidateTypeId()>0){
				appCandi.setAppointmentCandidateTypeId(basicInfo.getCandidateTypeId());
			}
				
			
			appCandi.setCreatedBy(loggerUserId);
			appCandi.setUpdatedBy(loggerUserId);
			appCandi.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			appCandi.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			
			appCandi = appointmentCandidateDAO.save(appCandi);
			
		}catch (Exception e) {
			LOG.error("Error occured  in candidateDetailsSaving() method of AppointmentService",e);
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
	
	public List<IdNameVO> getSMSEnablingDetailsForAllStatus(){
		
		List<IdNameVO> finalList = null;
		
		List<Object[]> list = appointmentSmsSettingDAO.getSMSEnablingDetailsForAllStatus();
		
		if( list != null && list.size() > 0){
			
			finalList = new ArrayList<IdNameVO>();
			
			for( Object[] obj : list){
				
				IdNameVO statusVO = new IdNameVO();
				statusVO.setId(obj[0] != null ? (Long)obj[0] :0l);
				statusVO.setName(obj[1] != null ? obj[1].toString() :"");
				
				finalList.add(statusVO);
			}
			
		}
		
		return finalList;
	}
	public boolean checkIsValidForSendingSMS(Long appointmentStatusId){
		
		boolean isenabled = false;
		String enabledStatus= appointmentSmsSettingDAO.checkIsValidForSendingSMS(appointmentStatusId);
		 
		if(enabledStatus.equalsIgnoreCase("Y")){
			isenabled = true;
		}
		
		return isenabled;
	}
	
	
	public ResultStatus sendSmsForAppointment(AppointmentUpdateStatusVO inputVO)
	{
		ResultStatus result = new ResultStatus();
		try{   
			    
				if(inputVO.getAppointmentStatusId() == null){
					inputVO.setAppointmentStatusId(appointmentDAO.getAppointmentStatusId(inputVO.getAppointmentId()));
				}
			    
				if( checkIsValidForSendingSMS( inputVO.getAppointmentStatusId() )){
					
					sendSms(inputVO);
					
					result.setResultCode(0);
					result.setMessage("success");
					
				}else{
					result.setResultCode(1);
					result.setMessage("Not Configured To Send SMS");
				}
			
		}
		catch (Exception e) {
			LOG.error("Exception raised in sendSmsForAppointment", e);
			result.setResultCode(2);
			result.setMessage("fail");
			
		}
		return result;
	}
	
	
	public ResultStatus sendSms(AppointmentUpdateStatusVO inputVO)
	{
		ResultStatus result = new ResultStatus();
		try{
			StringBuffer mobileNos = new StringBuffer();
			boolean isEnglish = false;
			List<Object[]> list = appointmentCandidateRelationDAO.getAppointmentCandidateMobileNos(inputVO.getAppointmentId());

			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					 if(params[2] != null && !params[2].toString().isEmpty() && params[2].toString().trim().length() == 10)
						 mobileNos.append(params[2].toString()+",");
				}
				
				if(mobileNos.length() > 0)
				{
					String mobileNoStr = mobileNos.substring(0,mobileNos.length()-1);
					SmsHistory smsHistory = null;
					Long statusId = inputVO.getAppointmentStatusId();
					
					if(inputVO.getSmsText().trim().length() > 0)
						smsHistory = smsSenderService.sendSMS(inputVO.getUserId(),IConstants.SMS_MODULE_APPOINTMENT, isEnglish,inputVO.getSmsText(), mobileNoStr);
					else
					{
						if(statusId == null)
							statusId = appointmentDAO.getAppointmentStatusId(inputVO.getAppointmentId());
						List<Object[]> contentList = appointmentSmsSettingDAO.getContentTypeAndSmsContent(statusId);
						if(contentList != null && contentList.size() > 0 && 
								contentList.get(0)[0].toString().trim().equalsIgnoreCase(IConstants.STATIC_STR) && contentList.get(0)[1].toString().trim().length() > 0)
						{
							smsHistory = smsSenderService.sendSMS(inputVO.getUserId(),IConstants.SMS_MODULE_APPOINTMENT,isEnglish,contentList.get(0)[1].toString().trim(),mobileNoStr);
						}
					}
					
					if(smsHistory != null)
					{
						AppointmentSmsHistory appointmentSmsHistory = new AppointmentSmsHistory();
						appointmentSmsHistory.setAppointmentId(inputVO.getAppointmentId());
						appointmentSmsHistory.setSmsHistory(smsHistory);
						appointmentSmsHistory.setAppointmentStatusId(statusId);
						appointmentSmsHistory.setSentBy(inputVO.getUserId());
						appointmentSmsHistory.setSentTime(dateUtilService.getCurrentDateAndTime());
						appointmentSmsHistoryDAO.save(appointmentSmsHistory);
					}
				}
			}
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
	
	public void changeAppointmentStatus(List<Long> ids,Long statusId,Long userId,Date date){
		if(ids != null && ids.size() > 0 && statusId != null && statusId > 0l){
			appointmentDAO.updateApptStatusbyApptIds(ids, date,statusId,userId);
			
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
			 
			 //total Appointment Status Details
			 List<Object[]> statusList = appointmentStatusDAO.getAllAppointmentStatus();
			  
			 List<Object[]> candidTypes = appointmentCandidateTypeDAO.getAllCandidateTypes();
			 
			 
			 //Scheduled and Waiting Status Types List
			 List<Long> scheduledList =Arrays.asList(IConstants.APPOINTMENT_SCHEDULED_LIST);
			 List<Long> waitingList =Arrays.asList(IConstants.APPOINTMENT_WAITING_LIST);
			 
			 
			 //Setting All CandidateTypes To every Status including to All/Total
			 
			 if(candidTypes!=null && candidTypes.size()>0){
				 setCandidateTypes(candidTypes,"total",finalVO);
				 setCandidateTypes(candidTypes,"scheduledTotal",finalVO);
				 setCandidateTypes(candidTypes,"waitingTotal",finalVO);
			 }
			 
			 List<AppointmentCountsVO> defaultStatusList = new ArrayList<AppointmentCountsVO>();
			 List<Long> totalStatusIds = new ArrayList<Long>(0);
			 
			 if(statusList !=null && statusList.size()>0){
				 for (Object[] obj : statusList) {			
					 
					 AppointmentCountsVO statusVO = new AppointmentCountsVO();
					 
					 statusVO.setId(obj[0]!=null?(Long)obj[0]:0l);//statusId
					 statusVO.setName(obj[1]!=null?obj[1].toString():"");//statusName
					 //setting default candidate types List
					 setCandidateTypes(candidTypes,statusVO.getId().toString(),statusVO);
					
					 defaultStatusList.add(statusVO);
					 totalStatusIds.add(statusVO.getId());
				 }
			}
			 finalVO.setAppointmentCountsVOList(defaultStatusList);
			 
			 
			 List<Object[]>  list= appointmentCandidateRelationDAO.getCandidCountsByStatesAndStatus(appointmentUserId,null,startDate,endDate);
			 setDataToList(list,"total",finalVO);
			 			 
			 List<Object[]>  scheduledCounts= appointmentCandidateRelationDAO.getCandidCountsByStatesAndStatus(appointmentUserId,scheduledList,startDate,endDate);
			 setDataToList(scheduledCounts,"scheduledTotal",finalVO);
			 
			
			 List<Object[]> waitingCounts= appointmentCandidateRelationDAO.getCandidCountsByStatesAndStatus(appointmentUserId,waitingList,startDate,endDate);
			 setDataToList(waitingCounts,"waitingTotal",finalVO);
			 
			 
			 //Scheduled && Waiting Statuses Wise Counts
			 List<Object[]> overAllResultStatusList = appointmentCandidateRelationDAO.getCandidateCountsOfStatesByStatuses(appointmentUserId,totalStatusIds,startDate,endDate);
			 setDataToStatusList(overAllResultStatusList,finalVO);
			 
			 /*//Waiting Statuses Wise Counts
			 List<Object[]> waitingStatusList = appointmentCandidateRelationDAO.getCandidateCountsOfStatesByStatuses(appointmentUserId,waitingList,startDate,endDate);
			 setDataToStatusList(waitingStatusList,waitingList,finalVO,"waiting");*/
			 
			 
			//if(finalVO.getScheduledCountsVOList() !=null && finalVO.getScheduledCountsVOList().size()>0)
			 
			 List<AppointmentCountsVO> scheduledStatusCountsVOList = new ArrayList<AppointmentCountsVO>(0);
			 List<AppointmentCountsVO> waitingStatusCountsVOList = new ArrayList<AppointmentCountsVO>(0);
				
				if(finalVO.getAppointmentCountsVOList() !=null && finalVO.getAppointmentCountsVOList().size()>0){
					
					for (AppointmentCountsVO VO : finalVO.getAppointmentCountsVOList()) {
						
						if(VO.getScheduledStatusCountsVOList() !=null && VO.getScheduledStatusCountsVOList().size()>0){
							scheduledStatusCountsVOList.add(VO);
						}else if(VO.getWaitingStatusCountsVOList() !=null && VO.getWaitingStatusCountsVOList().size()>0){
							waitingStatusCountsVOList.add(VO);
						}
					}					
				}
				
				
				if(scheduledStatusCountsVOList !=null && scheduledStatusCountsVOList.size()>0){
					finalVO.setScheduledStatusCountsVOList(scheduledStatusCountsVOList);
				}
				if(waitingStatusCountsVOList !=null && waitingStatusCountsVOList.size()>0){
					finalVO.setWaitingStatusCountsVOList(waitingStatusCountsVOList);
				}
			 
			 finalVO.setResultMsg("success");
			
		}catch(Exception e){
			finalVO.setResultMsg("error");
			e.printStackTrace();
		}
		return finalVO;
	}
	
	
	//Need to move for another place
	
	public void setDataToStatusList(List<Object[]> resultStatusList,AppointmentCountsVO finalVO){
		
		try{
			
			
			//Scheduled and Waiting Status Types List
			 List<Long> scheduledList =Arrays.asList(IConstants.APPOINTMENT_SCHEDULED_LIST);
			 List<Long> waitingList =Arrays.asList(IConstants.APPOINTMENT_WAITING_LIST);
			
			
			List<AppointmentCountsVO> internalList = new ArrayList<AppointmentCountsVO>(0);
			
			internalList = finalVO.getAppointmentCountsVOList();
			
			if(resultStatusList !=null && resultStatusList.size()>0){
				for (Object[] obj : resultStatusList) {
					AppointmentCountsVO returnStatusVo=getMatchedCandiType(internalList,(Long)obj[5]);//return StatusVO if Status Match 					
					if(returnStatusVo !=null){
						
						AppointmentCountsVO matchedVO =null;	
												
						if(scheduledList.contains((Long)obj[5])){
							matchedVO = getMatchedCandiType(returnStatusVo.getScheduledStatusCountsVOList(),obj[0] !=null ? (Long)obj[0]:0l);
						}else if(waitingList.contains((Long)obj[5])){
							matchedVO = getMatchedCandiType(returnStatusVo.getWaitingStatusCountsVOList(),obj[0] !=null ? (Long)obj[0]:0l);
						}
						
						if(matchedVO !=null){
							matchedVO = getValuesIntoMatchedVO(obj,matchedVO);
						}
					}					
				}
			}
		}catch (Exception e) {
			LOG.error("Error occured  in setDateToStatusList() method of AppointmentService class",e);
		}
		
	}
	
	public AppointmentCountsVO getValuesIntoMatchedVO(Object[] obj,AppointmentCountsVO matchedVO){
		try{
			
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
			
			
		}catch (Exception e) {
			LOG.error("Error occured  in getMatchedStatus() method of AppointmentService class",e);
		}
		return null;
	}
	
	public void setDataToList(List<Object[]> list,String type,AppointmentCountsVO finalVO){
		
		List<AppointmentCountsVO> subList = null;
		if(type.equalsIgnoreCase("total")){
			subList = finalVO.getTotalCountsVOList();
		}else if(type.equalsIgnoreCase("scheduledTotal")){
			subList = finalVO.getScheduledCountsVOList();
		}else if(type.equalsIgnoreCase("waitingTotal")){
			subList = finalVO.getWaitingCountsVOList();
		}
		
		
		if(list!=null && list.size()>0){
			
			for(Object[] obj : list){
				
				 
				 Long candiType =  obj[0]!=null?(Long)obj[0]:0l;
				 
				 AppointmentCountsVO matchedVO = getMatchedCandiType(subList,candiType);
				 if(matchedVO != null){
					 matchedVO = getValuesIntoMatchedVO(obj,matchedVO);
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
		
		 //Scheduled and Waiting Status Types List
		 List<Long> scheduledList =Arrays.asList(IConstants.APPOINTMENT_SCHEDULED_LIST);
		 List<Long> waitingList =Arrays.asList(IConstants.APPOINTMENT_WAITING_LIST);
		
		for(Object[] obj : candiList){
			
			AppointmentCountsVO vo = new AppointmentCountsVO();
			vo.setId(obj[0]!=null?(Long)obj[0]:0l);
			vo.setName(obj[1]!=null?obj[1].toString():"");
			
			list.add(vo);
		}
		
		if(type.equalsIgnoreCase("total")){
			finalVO.setTotalCountsVOList(list);
		}else if(type.equalsIgnoreCase("scheduledTotal")){
			finalVO.setScheduledCountsVOList(list);
		}else if(type.equalsIgnoreCase("waitingTotal")){
			finalVO.setWaitingCountsVOList(list);
		}else if(scheduledList.contains(Long.valueOf(type))){
			finalVO.setScheduledStatusCountsVOList(list);
		}else if(waitingList.contains(Long.valueOf(type))){
			finalVO.setWaitingStatusCountsVOList(list);
		}
	}
	
	public List<AppointmentCountVO> getPublicRepresentativeWiseAppointmentCnt(Long apointmntCandateId)
	{
		List<AppointmentCountVO> returnList = new ArrayList<AppointmentCountVO>();
		try{
			 List<Object[]> list = appointmentCandidateDAO.getPublicRepresentativeWiseAppointmentCnt(null,"total",apointmntCandateId); //Total
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
			 
			 List<Object[]> list1 = appointmentCandidateDAO.getPublicRepresentativeWiseAppointmentCnt(null,"unique",apointmntCandateId); //Total
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
			 List<Object[]> list2 = appointmentCandidateDAO.getPublicRepresentativeWiseAppointmentCnt(Arrays.asList(schedul),"Schedule",apointmntCandateId);
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
			 
			
			 List<Object[]> list3 = appointmentCandidateDAO.getPublicRepresentativeWiseAppointmentCnt(Arrays.asList(schedul),"unique",apointmntCandateId);
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
			 List<Object[]> list4 = appointmentCandidateDAO.getPublicRepresentativeWiseAppointmentCnt(Arrays.asList(req),"Request",apointmntCandateId);
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
			 
			 List<Object[]> list5 = appointmentCandidateDAO.getPublicRepresentativeWiseAppointmentCnt(Arrays.asList(req),"unique",apointmntCandateId);
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
	
	public List<AppointmentMembersDataVO> getAppointmentMembersByScheduleType(AppointmentMemberInputVO inputVO)
	{
		List<AppointmentMembersDataVO> returnList = new ArrayList<AppointmentMembersDataVO>();
		List<Long> statusIds = new ArrayList<Long>();
		 Long[] longArr = null;
		 List<Object[]> list= null;
		try{
			
			if(inputVO.getScheduleType() != null && inputVO.getScheduleType().toString().equalsIgnoreCase("Schedule"))
			{
				longArr =IConstants.APPOINTMENT_STATUS_SCHEDULED_LIST;
				statusIds =Arrays.asList(longArr);
			}
			
			else if(inputVO.getScheduleType() != null && inputVO.getScheduleType().toString().equalsIgnoreCase("Request"))
			{
				longArr =IConstants.APPOINTMENT_STATUS_WAITING_LIST;
				statusIds =Arrays.asList(longArr);
			}
			else
				statusIds = null;
			if(inputVO.getMemberType().equalsIgnoreCase("PR"))
			{
			 list = appointmentCandidateDAO.getPublicRepresentativeWiseAppointmentMembers(statusIds,inputVO.getCntType(),inputVO.getRoleId(),inputVO.getAptUserId()); //Total
			}else if(inputVO.getMemberType().equalsIgnoreCase("CommitteeMemberRole")){
				 list = appointmentCandidateDAO.getCommitteeMemROleWiseAppointmentMembers(statusIds,inputVO.getCntType(),inputVO.getRoleId(),inputVO.getAptUserId(),inputVO.getLevelId()); //Committee Members Roles
			}else
			{
				list = appointmentCandidateDAO.getCommitteeWiseAppointmentMembers(statusIds,inputVO.getCntType(),inputVO.getRoleId(),inputVO.getAptUserId());//Committee Members
			}
			if(list != null && list.size() > 0)
			setAppointmentMembersData(list,returnList,inputVO);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
	}
	
 public void setAppointmentMembersData(List<Object[]> list,List<AppointmentMembersDataVO> returnList,AppointmentMemberInputVO inputVO)
 {
	 List<Long> cadreIds = new ArrayList<Long>();
	 List<Long> appointmentCandidateIdsList=new ArrayList<Long>(0);
	 try{
		 if(list != null && list.size() > 0)
		 {
			 for(Object[] params : list)
			 {
				 AppointmentMembersDataVO vo = new AppointmentMembersDataVO();
				 vo.setId(params[6] != null ?(Long)params[6]:null);
				 vo.setName(params[1] != null ? params[1].toString() : "");
				 vo.setDesignationId(params[2] != null ?(Long)params[2]:null);
				 vo.setDesignation(params[3] != null ?params[3].toString() : "");
				 vo.setImageUrl(params[5] != null ?params[5].toString() : "");
				 vo.setMobile(params[4] != null ?params[4].toString() : "");
				 vo.setTdpCadreId((Long)params[0]);
				 if(inputVO.getMemberType().equalsIgnoreCase("PR"))
				 {
					 vo.setLocation(locationService.getLocationForDesignation(vo.getTdpCadreId(), vo.getDesignationId()));
				 }
				 cadreIds.add(vo.getTdpCadreId());
				 appointmentCandidateIdsList.add(params[6] != null ?(Long)params[6]:null);
				 returnList.add(vo);
			 }
			 if(inputVO.getMemberType().equalsIgnoreCase("CommitteeMember"))
			 {
				 List<Object[]> constlist = tdpCadreDAO.getAddressForCadreIds(cadreIds);
				 if(constlist != null && constlist.size() > 0)
				 {
					 
					 setLocationForCadre(constlist,returnList,inputVO);
				 }
			 }
		 }
		 //Total requested appointment counts by appointment candidates;
		List<Object[]> rtrnTtlAppntmntsCuntLst=appointmentCandidateRelationDAO.getTotalAppointmentsForCandiates(appointmentCandidateIdsList, inputVO.getAptUserId(),null);
		
		if(rtrnTtlAppntmntsCuntLst!=null && rtrnTtlAppntmntsCuntLst.size()>0){
			
			for (Object[] objects : rtrnTtlAppntmntsCuntLst) {
				AppointmentMembersDataVO membersDataVO=getAppCandidateMatchVO(returnList,(Long)objects[0]);
				  if(membersDataVO!=null){
					  membersDataVO.setTotalRequestedAppCount(objects[1]!=null ? (Long)objects[1]: 0l);
				  }
			}
		}
		 //Total completed appointment counts by appointment candidates;
		List<Object[]> rtrnTtlCmpltdAppCntLst=appointmentCandidateRelationDAO.getTotalAppointmentsForCandiates(appointmentCandidateIdsList, inputVO.getAptUserId(),4l);
		
		if(rtrnTtlCmpltdAppCntLst!=null && rtrnTtlCmpltdAppCntLst.size()>0){
			for (Object[] objects : rtrnTtlCmpltdAppCntLst) {
				AppointmentMembersDataVO membersDataVO=getAppCandidateMatchVO(returnList,(Long)objects[0]);
				  if(membersDataVO!=null){
					  membersDataVO.setTotalCompletedAppCount(objects[1]!=null ? (Long)objects[1]: 0l);
				  }
			}
		}
	   //Candidates last visit date 
		List<Object[]> rtrnCnddtLstSttsLst=appointmentCandidateRelationDAO.getCandidateLastVisitedDtl(appointmentCandidateIdsList, inputVO.getAptUserId(),4l);
		
		if(rtrnCnddtLstSttsLst!=null && rtrnCnddtLstSttsLst.size()>0){
			for (Object[] objects : rtrnCnddtLstSttsLst) {
				 AppointmentMembersDataVO membersDataVO=getAppCandidateMatchVO(returnList,(Long)objects[0]);
				 if(membersDataVO!=null){
					 membersDataVO.setCandidateLastVisitDate(objects[3]!=null ? objects[3].toString(): " ");
				 }
			}
		}
		//Candidates last status
		 List<Object[]> rtrnCnddtLstVstList=appointmentCandidateRelationDAO.getCandidateLastVisitedDtl(appointmentCandidateIdsList, inputVO.getAptUserId(),null);
		 
		 if(rtrnCnddtLstVstList!=null && rtrnCnddtLstVstList.size()>0){
			 for (Object[] objects : rtrnCnddtLstVstList) {
				 AppointmentMembersDataVO membersDataVO=getAppCandidateMatchVO(returnList,(Long)objects[0]);
				 if(membersDataVO!=null){
					 membersDataVO.setCandidateLastUpdatedStatus(objects[2]!=null ? objects[2].toString(): " ");
				 }
			}
		 }
	 }
	 catch(Exception e)
	 {
		e.printStackTrace(); 
	 }
 }
 
 public AppointmentMembersDataVO getAppCandidateMatchVO(List<AppointmentMembersDataVO> returnList,Long appCandidateId){
	 
	 if(returnList!=null && returnList.size()>0){
		 for (AppointmentMembersDataVO vo : returnList) {
			  if(vo.getId().equals(appCandidateId)){
				  return vo;
			  }
		}
		 return null;
	 }
	 return null;
 }
 
 public void setLocationForCadre(List<Object[]> constlist,List<AppointmentMembersDataVO> returnList,AppointmentMemberInputVO inputVO)
 {
	 try{
		 Map<Long,UserAddress> constiMap = new HashMap<Long, UserAddress>();
				 if(constlist!=null && constlist.size()>0){
						for(Object[] obj :constlist){
							UserAddress userAddress = (UserAddress) obj[1];
							constiMap.put((Long)obj[0],userAddress);
						}
					}
				 
				 for(AppointmentMembersDataVO vo : returnList)
				 {
					 UserAddress address = constiMap.get(vo.getTdpCadreId().longValue());
					 if(address != null)
					 {
						 if(inputVO.getRoleId().longValue() == 10L)
							 vo.setState(address.getState().getStateName());
						 else if(inputVO.getRoleId().longValue() == 11L)
						 {
							 vo.setState(address.getState()!= null ? address.getState().getStateName() : "");	
							 vo.setDistrict(address.getDistrict() != null ? address.getDistrict().getDistrictName() : "");
						 }
						 else if(inputVO.getRoleId().longValue() == 5L)
						 {
							 vo.setState(address.getState()!= null ? address.getState().getStateName() : "");	
							 vo.setDistrict(address.getDistrict() != null ? address.getDistrict().getDistrictName() : "");
							 vo.setConstituency(address.getConstituency() != null ? address.getConstituency().getName() : "");
							 vo.setMandal(address.getTehsil() != null ? address.getTehsil().getTehsilName() : "");
						 }
								
						 else if(inputVO.getRoleId().longValue() == 6L)
						 {
							 vo.setState(address.getState()!= null ? address.getState().getStateName() : "");	
							 vo.setDistrict(address.getDistrict() != null ? address.getDistrict().getDistrictName() : "");
							 vo.setMandal(address.getTehsil() != null ? address.getTehsil().getTehsilName() : "");
							 vo.setConstituency(address.getConstituency() != null ? address.getConstituency().getName() : "");
							 vo.setVillage(address.getPanchayat() != null ?address.getPanchayat().getPanchayatName() : "" );
						 }
						 else if(inputVO.getRoleId().longValue() == 8L || inputVO.getRoleId().longValue() == 9L)
						 {
							 vo.setState(address.getState()!= null ? address.getState().getStateName() : "");	
							 vo.setDistrict(address.getDistrict() != null ? address.getDistrict().getDistrictName() : "");
							 vo.setMandal(address.getTehsil() != null ? address.getTehsil().getTehsilName() : "");
							 vo.setConstituency(address.getConstituency() != null ? address.getConstituency().getName() : "");
							 vo.setVillage(address.getWard() != null ?address.getWard().getName() : "" );
						 }
						 else if(inputVO.getRoleId().longValue() == 7L)
						 {
							 vo.setState(address.getState()!= null ? address.getState().getStateName() : "");	
							 vo.setDistrict(address.getDistrict() != null ? address.getDistrict().getDistrictName() : "");
							 vo.setConstituency(address.getConstituency() != null ? address.getConstituency().getName() : "");
							 vo.setMandal(address.getLocalElectionBody() != null ? address.getLocalElectionBody().getName() : "");
							
						 }
						 else
						 {
							 vo.setState(address.getState()!= null ? address.getState().getStateName() : ""); 
							 vo.setDistrict(address.getDistrict() != null ? address.getDistrict().getDistrictName() : "");
						 }
					 }
						
				 }
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
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
	public List<AppointmentCountVO> getCommitteeLevelAppointments(Long appointCandidteId)
	{
		List<AppointmentCountVO> returnList = new ArrayList<AppointmentCountVO>();
		try{
			 List<Object[]> list =  appointmentCandidateRelationDAO.getCommitteeLevelAppointments(null,"total",appointCandidteId); //Total
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
			 
			 List<Object[]> list1 = appointmentCandidateRelationDAO.getCommitteeLevelAppointments(null,"unique",appointCandidteId); //Total
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
			 List<Object[]> list2 = appointmentCandidateRelationDAO.getCommitteeLevelAppointments(Arrays.asList(schedul),"Schedule",appointCandidteId);
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
			 
			List<Object[]> list3 = appointmentCandidateRelationDAO.getCommitteeLevelAppointments(Arrays.asList(schedul),"unique",appointCandidteId);
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
			 List<Object[]> list4 = appointmentCandidateRelationDAO.getCommitteeLevelAppointments(Arrays.asList(req),"Request",appointCandidteId);
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
			 
			 List<Object[]> list5 = appointmentCandidateRelationDAO.getCommitteeLevelAppointments(Arrays.asList(req),"unique",appointCandidteId);
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
	
	public List<Long> getAppointmentIdsForVoid(Long appointmentId,Long statusId,Long apptUserId){
		List<Long> apptIds = new ArrayList<Long>();
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
			
	    	  List<Object[]> apptWithCandiIdsList = appointmentCandidateRelationDAO.checkApptsAsVoid(insertedDate,statusId,apptCandiIds,apptUserId);
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
	      }
	      return apptIds;
	}
	public List<AppointmentCountVO> getLevelWiseCount(Long levelId,Long aptUserId)
	{
		List<AppointmentCountVO> returnList = new ArrayList<AppointmentCountVO>();
		try{
			 List<Object[]> list =  appointmentCandidateRelationDAO.getLevelWiseCount(null,"total",levelId,aptUserId); //Total
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
			 
			 List<Object[]> list1 = appointmentCandidateRelationDAO.getLevelWiseCount(null,"unique",levelId,aptUserId); //Total
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
			 List<Object[]> list2 = appointmentCandidateRelationDAO.getLevelWiseCount(Arrays.asList(schedul),"Schedule",levelId,aptUserId);
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
			 
			List<Object[]> list3 = appointmentCandidateRelationDAO.getLevelWiseCount(Arrays.asList(schedul),"unique",levelId,aptUserId);
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
			 List<Object[]> list4 = appointmentCandidateRelationDAO.getLevelWiseCount(Arrays.asList(req),"Request",levelId,aptUserId);
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
			 
			 List<Object[]> list5 = appointmentCandidateRelationDAO.getLevelWiseCount(Arrays.asList(req),"unique",levelId,aptUserId);
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
	// Appointment Status Tracking Details
	
	public List<StatusTrackingVO> getAppointmentStatusTrackingDetails(Long appointmentId)
	{
		LOG.info("Entered in getAppointmentStatusTrackingDetails() method");
		List<StatusTrackingVO> resultList = null;
		try{
			
			List<Object[]> list = appointmentTrackingDAO.getAppointmentTrackingDetails(appointmentId);
				resultList = getStatusTrackingList(list);
			}
		catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Entered in getAppointmentStatusTrackingDetails() method");
		}
		return resultList;
	}
	
	
	// Appointment Status Flow Tracking Details
	
		public List<StatusTrackingVO> getAppointmentStatusCommentsTrackingDetails(Long appointmentId)
		{
			LOG.info("Entered in getAppointmentStatusTrackingDetails() method");
			List<StatusTrackingVO> resultList = null;
			try{
				
				List<Object[]> list = appointmentTrackingDAO.getAppointmentTrackingDetails(appointmentId);
				
						resultList = getStatusCommentsList1(list);
						/*setTimeDiffForTracking(orderStatusList);
						if(resultList != null && resultList.size() > 0)
						{
							resultList.get(0).setFlowList(orderStatusList);
						}*/
					}
			catch(Exception e)
			{
				e.printStackTrace();
				LOG.error("Entered in getAppointmentStatusFlowTrackingDetails() method");
			}
			return resultList;
		}
	
	public void setTimeDiffForTracking(List<StatusTrackingVO> list)
	{
		try{
			SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
			for(int i=0;i<list.size();i++){
				
				Date d2=null;
				Date d1=null;
				if(list.get(i).getStatus()!=null &&list.get(i).isCurrent() == true){
					d2=new DateUtilService().getCurrentDateAndTime();  
					d1=  format.parse(list.get(i).getDate());
				}else{
					if(i!=(list.size()-1)){
						d2=format.parse(list.get(i+1).getDate());
						d1=format.parse(list.get(i).getDate());
					}
				}
				if(d2!=null && d1!=null){
				 /*long diff = d2.getTime() - d1.getTime();
				 long diffDays = diff / (24 * 60 * 60 * 1000);*/
					// Get msec from each, and subtract.
				 	long diff = d2.getTime() - d1.getTime();
			        long diffSeconds = diff / 1000 % 60;
			        long diffMinutes = diff / (60 * 1000) % 60;
			        long diffHours = diff / (60 * 60 * 1000);
			        long diffInDays = diff / (24 * 60 * 60 * 1000);

			        if (diffInDays > 1) {
			            /*System.err.println("Difference in number of days (2) : " + diffInDays);
			            return false;*/
			            list.get(i).setTime(diffInDays+" days");
			        } /*else if (diffHours > 24) {
			        	System.err.println(">24");
			            return false;
			        }*/
			        else if(diffHours>0 && diffHours < 24){
			        	 list.get(i).setTime(diffInDays+" hour");
			        }
			        else if ((diffHours == 24) && (diffMinutes >= 1)) {
			          list.get(i).setTime(diffMinutes+" minutes");
			       }
			        
				 
				// list.get(i).setTime(diffDays+" days");
				}
		}
		}
		catch(Exception e)
		{
			LOG.error("Entered in setTimeDiffForTracking() method");
		}
		
	}
	
	public List<StatusTrackingVO> getOrderStatusList()
	{
		List<StatusTrackingVO>  orderList = new ArrayList<StatusTrackingVO>();
		List<AppointmentStatus> list = appointmentStatusDAO.getAll();
		for(AppointmentStatus params : list)
		{
			StatusTrackingVO vo = new StatusTrackingVO();
			vo.setId(params.getAppointmentStatusId());
			vo.setStatus(params.getStatus());
			orderList.add(vo);
		}
		return orderList;
		
	}
	public List<StatusTrackingVO> getStatusTrackingList(List<Object[]> list)
	{
		List<StatusTrackingVO>  resultList = new ArrayList<StatusTrackingVO>();
		try{
			
			for(Object[] params : list)
			{
				
				StatusTrackingVO vo = new StatusTrackingVO();
				vo.setId((Long)params[0]);
				vo.setStatus(params[1] != null ? params[1].toString() : "");
				vo.setUserId((Long)params[2]);
				String fname = params[3] != null ? params[3].toString() : "";
				String lname = params[4] != null ? params[4].toString() : "";
				vo.setUname(fname+" "+lname);
				vo.setComments(params[8] != null ? params[8].toString() : "");
				vo.setDate(params[6] != null ? params[6].toString().substring(0, 19) : "");
				if(params[9] != null)
				{
					vo.setFromStatusId((Long)params[9]);
					vo.setFromStatus(params[10].toString());
				}
				
				resultList.add(vo);
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Entered in getStatusFlowList() method");
		}
		return resultList;
	}
	public List<StatusTrackingVO> getStatusCommentsList(List<Object[]> list)
	{
		List<StatusTrackingVO>  resultList = new ArrayList<StatusTrackingVO>();
		try{
			for(int i=0;i<list.size();i++)
			{
				Object[] params = list.get(i);
				StatusTrackingVO vo = new StatusTrackingVO();
				vo.setId((Long)params[0]);
				vo.setStatus(params[1] != null ? params[1].toString() : "");
				vo.setUserId((Long)params[2]);
				String fname = params[3] != null ? params[3].toString() : "";
				String lname = params[4] != null ? params[4].toString() : "";
				vo.setUname(fname+" "+lname);
				if(vo.getCommentsList() == null || vo.getCommentsList().size() > 0)
				{
					vo.setCommentsList(new ArrayList<String>());
					vo.getCommentsList().add(params[8] != null ? params[8].toString() : "");
				}
				vo.setDate(params[6] != null ? params[6].toString().substring(0, 19) : "");
				if(params[9] != null)
				{
					vo.setFromStatusId((Long)params[9]);
					vo.setFromStatus(params[10].toString());
				}
				
				  for(int j=i+1;j<list.size();j++)
				  {
					  Object[] params1 = list.get(j);
					//  Long l = (Long)params[0];
					  if(params[1].toString().equalsIgnoreCase(params1[1].toString()))
					  {
						  vo.getCommentsList().add(params1[8] != null ? params1[8].toString() : "");
						  i++;
					  }
					  else
					  {
						  break;
					  }
					  
				  }
				resultList.add(vo);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Entered in getStatusCommentsList() method");
		}
		return resultList;
	}
	
	public List<StatusTrackingVO> getStatusCommentsList1(List<Object[]> list)
	{
		List<StatusTrackingVO>  resultList = new ArrayList<StatusTrackingVO>();
		try{
			for(int i=0;i<list.size();i++)
			{
				
				
				/*model.appointmentStatus.appointmentStatusId,0
						model.appointmentStatus.status,1
						model.user.userId 2 ,model.user.firstName 3,model.user.lastName 4,model.remarks 5,model.actionTime 6,
						appointmentComment.appointmentCommentId 7,appointmentComment.comment 8,fromAppointmentStatus.appointmentStatusId 9 ,fromAppointmentStatus.status 10*/
				
				
				Object[] params = list.get(i);
				StatusTrackingVO vo = new StatusTrackingVO();
				vo.setId((Long)params[0]);
				vo.setStatus(params[1] != null ? params[1].toString() : "");
				vo.setUserId((Long)params[2]);
				String fname = params[3] != null ? params[3].toString() : "";
				String lname = params[4] != null ? params[4].toString() : "";
				vo.setUname(fname+" "+lname);
			/*	if(vo.getCommentsList() == null || vo.getCommentsList().size() > 0)
				{
					vo.setCommentsList(new ArrayList<String>());
					vo.getCommentsList().add(params[8] != null ? params[8].toString() : "");
				}*/
				
				vo.setComments(params[8] != null ? params[8].toString() : "");
				
				vo.setDate(params[6] != null ? params[6].toString().substring(0, 19) : "");
				if(params[9] != null)
				{
					vo.setFromStatusId((Long)params[9]);
					vo.setFromStatus(params[10].toString());
				}
				
				if(params[11] !=null){
					vo.setActionId((Long)params[11]);
					vo.setActionStatus(params[12] !=null ? params[12].toString():"");
				}
				
				/*
				  for(int j=i+1;j<list.size();j++)
				  {
					  Object[] params1 = list.get(j);
					//  Long l = (Long)params[0];
					  if(params[1].toString().equalsIgnoreCase(params1[1].toString()))
					  {
						  vo.getCommentsList().add(params1[8] != null ? params1[8].toString() : "");
						  i++;
					  }
					  else
					  {
						  break;
					  }
					  
				  }*/
				resultList.add(vo);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Entered in getStatusCommentsList1() method");
		}
		return resultList;
	}
	
	public StatusTrackingVO getMatchedStatusVo(List<StatusTrackingVO> resultList,Long id)
	{
		try{
			
			if(resultList == null || resultList.size() == 0)
				return null;
			for(StatusTrackingVO vo : resultList)
			{
				if(vo.getId().longValue() == id.longValue())
					return vo;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Entered in getStatusFlowList() method");
		}
		
		return null;
		
	}
	
	public List<IdNameVO> getAppointmentStatusByUserId(Long userId){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		
		try {
			
			List<Object[]> list = userAppointmentUserDAO.getAppointmentStatusByUserId(userId);
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					IdNameVO vo = new IdNameVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					vo.setDistrictid(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));   //appointmentUserTypeId
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getAppointmentStatusByUserId() method of AppointmentService", e);
		}
		return returnList;
	}
	
	public List<IdNameVO> getUpdatedStatusForaAppointment(Long userTypeId,Long currentStatusId){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		
		try {
			
			List<Object[]> list = appointmentStatusFlowDAO.getUpdatedStatusForaAppointment(userTypeId, currentStatusId);
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					IdNameVO vo = new IdNameVO();
					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getUpdatedStatusForaAppointment() method of AppointmentService", e);
		}
		return returnList;
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
											
							AppointmentDetailsVO appointment =new AppointmentDetailsVO();
							appointment.setAppointmentId(obj[0]!=null?(Long)obj[0]:0l);
							appointment.setSubject(obj[1]!=null?obj[1].toString():"");
							appointment.setPriority(obj[2]!=null?obj[2].toString():"");
							appointment.setStatus(obj[3]!=null?obj[3].toString():"");
							appointment.setDateString(obj[4]!=null?obj[4].toString():"");
							appointment.setAptUniqueCode(obj[5]!=null?obj[5].toString():"");
							appointmentsMap.put(appointment.getAppointmentId(),appointment);
							
							//appointmentIds
							appointmentIds.add(appointment.getAppointmentId());
						}
			
			List<Long> appointments = null;
			if(appointmentIds!=null && appointmentIds.size()>0){
				appointments = new ArrayList<Long>(appointmentIds);
			}
			
			//get Preferable Dates For Appointments
			if(appointments!=null && appointments.size()>0){
				
				List<Object[]>  apptDateslist = appointmentPreferableDateDAO.getMultipleDatesforAppointments(appointments);
				if(apptDateslist!=null && apptDateslist.size()>0){
					for(Object[] obj : apptDateslist){
						AppointmentDetailsVO   appointmentVO = appointmentsMap.get((Long)obj[0]);						
						appointmentVO = settingSomeDataForPrefferableDates(appointmentVO,obj);						
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
							candidateVO.setCandidateTypeId(obj[8]!=null?(Long)obj[8]:0l);
							
							candidateVO.setStatusList(setStatusList(statList));
							appointmentVO.getSubMap().put(candidateVO.getCandidateId(),candidateVO);
							
							//candidateIds
							candidateIds.add(candidateVO.getCandidateId());
						}
					}
				}
			}
			
			//candidate previous info.
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
													
													//Prefer Dates Scenario For History of Each Candidate start
													
														apptvo = setPreferebleDatesToAppointment(aptmnts,apptvo);
													
													//Prefer Dates Scenario For History of Each Candidate End
													
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
		
		 }
		
		} catch (Exception e) {
			LOG.error("Exception raised at getAppointmentsBySearchCriteria",e);
		}
		return finalList;
	}
    
    public AppointmentDetailsVO settingSomeDataForPrefferableDates(AppointmentDetailsVO  apptvo,Object[] object){
    	try{
    		
    		SimpleDateFormat prefer = new SimpleDateFormat("dd MMM yyyy");
    		
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
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return apptvo;
    }
    public List<AppointmentSlotsVO>  getTimeSlotsForADayByAppytUserId(Long apptUserId,String dateStr){
    		
    		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
    		
    		List<AppointmentSlotsVO> finalList = null;
    	try{
    		  Date date = null;
    		  if(dateStr !=null && !dateStr.isEmpty()){
    			  date = sdf.parse(dateStr);
    		  }
    		     
    			List<Object[]> dates = appointmentTimeSlotDAO.getAppointmentConfirmDates(date,apptUserId,IConstants.APPOINTMENT_STATUS_SCHEDULED);
    			Date givenDate=null;
    			
    			if(dates != null && dates.size() >0){
    				finalList = new ArrayList<AppointmentSlotsVO>();
    				for(Object[] obj : dates){
    					
    					AppointmentSlotsVO vo = new AppointmentSlotsVO();
    					vo.setStartDate(obj[1]!=null?obj[1].toString():"");
    					vo.setEndDate(obj[2]!=null?obj[2].toString():"");
    					givenDate = obj[0] !=null?(Date)obj[0]:null;
    					finalList.add(vo);
    				}
    				if(finalList != null && finalList.size() >0 ){
    					finalList.get(0).setDate(sdf1.format(givenDate));
    				}
    				
    			}
    		
		}catch(Exception e){
			LOG.error("Exception raised at getTimeSlotsForADayByAppytUserId",e);
		}
    	return finalList;
    }
public void checkisEligibleForApptCadre(List<Long> cadreNoList,Long appointmentUserId,List<AppointmentCandidateVO>  finalList){
	
	boolean flag = false;
	
	List<Long>  apptCreationStatusList =Arrays.asList(IConstants.APPOINTMENT_STATUS_CREATION_LIST);
	List<Object[]> list = appointmentCandidateRelationDAO.checkIsAppointmentEligibleCadre(cadreNoList,apptCreationStatusList,appointmentUserId);
	if(list != null && list.size() > 0)
	{
		for(Object[] params : list)
		{
			AppointmentCandidateVO vo = getMatchedVO(finalList,(Long)params[3]);
			 if(vo != null)
			 {
				vo.setAptExists(true);
				vo.setAptId((Long)params[0]);
				vo.setAptName(params[1] != null ? params[1].toString() : "");
				vo.setAptStatus(params[5] != null ? params[5].toString() : "");
			 }
		}
	}
}
    public List<AppointmentStatusFlowVO> getApplicationContextWiseSatuses(){
		List<AppointmentStatusFlowVO> returnList = new ArrayList<AppointmentStatusFlowVO>();
		
		try {
			
			List<Object[]> list = appointmentStatusFlowDAO.getApplicationContextWiseSatuses();
			if(list != null && list.size() > 0){				
				returnList = setApptStatusValuesToFlowList(list,returnList);								
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getUpdatedStatusForaAppointment() method of AppointmentService", e);
		}
		return returnList;
	}
    
    public List<AppointmentStatusFlowVO> setApptStatusValuesToFlowList(List<Object[]> list ,List<AppointmentStatusFlowVO> returnList){
    	try{
    		
    		for (Object[] obj : list) {
				AppointmentStatusFlowVO vo = new AppointmentStatusFlowVO();
				
				vo.setTypeId(obj[0] !=null ? (Long)obj[0]:0l);
				vo.setType(obj[1] !=null ? obj[1].toString():"");
				
				vo.setStatusId(obj[2] !=null ? (Long)obj[2]:0l);
				vo.setStatus(obj[3] !=null ? obj[3].toString():"");
				
				vo.setToStatusId(obj[4] !=null ? (Long)obj[4]:0l);
				vo.setToStatus(obj[5] !=null ? obj[5].toString():"");
				
				returnList.add(vo);
			}
    		    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return returnList;
    }
    public ResultStatus saveDesignationForOtherCandidate(String designation,Long candidateTypeId){
    	ResultStatus result = new ResultStatus();    	
    	try{
    		
    		 String isDesignationExist=candidateDesignationDAO.checkDesignationExistOrNot(candidateTypeId, designation);
    		 
    		 if(isDesignationExist!=null){
    			 result.setExceptionMsg("exist");
    			 return result;
    		 }
    		AppointmentCandidateDesignation aptDesignation = new AppointmentCandidateDesignation();
    		if(designation !=null && !designation.isEmpty()){
    			aptDesignation.setDesignation(designation);
    		}
    		if(candidateTypeId !=null && candidateTypeId>0){
    			aptDesignation.setAppointmentCandidateTypeId(candidateTypeId);
    		}
    		
    		candidateDesignationDAO.save(aptDesignation);
    		result.setExceptionMsg("Success");
    		result.setResultCode(0);
    		
    	}catch (Exception e) {
    		result.setExceptionMsg("failure");
    		result.setResultCode(1);
    		LOG.error("Exception raised at saveDesignationForOtherCandidate() method of AppointmentService", e);
		}
    	return result;
    }
    public List<AppointmentScheduleVO> getAllScheduledApptsByDate(Long apptUserId,String dateStr)
	{
		List<AppointmentScheduleVO> resultList = null;
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		
		try{
			
			Date dates = null;
			if(dateStr != null && !dateStr.isEmpty()){
				dates = format.parse(dateStr);
			}
			 
				List<Object[]> list = appointmentCandidateRelationDAO.getAllScheduledApptsByDate(apptUserId,dates,IConstants.APPOINTMENT_STATUS_SCHEDULED);
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
							Date date=null;
							Date date1 = null;
							String convertDate = null;
							String convertDate1 =null;
							if(params[8] !=null){
								 date= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(params[8].toString().substring(0, 19));
								 convertDate = new SimpleDateFormat("hh:mm a").format(date);
							}
							
							if(params[12] !=null){
								date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(params[12].toString().substring(0, 19));
								convertDate1 = new SimpleDateFormat("hh:mm a").format(date1);
							}								
								vo.setSubject(params[4] != null ? params[4].toString() : "");
								vo.setStatusId(params[9] != null ? (Long)params[9] : null);
								vo.setAppointmentStatus(params[10] != null ? params[10].toString() : "");
								vo.setAppointmentStatusColor(params[16]!=null ? params[16].toString():"");
								vo.setTime(convertDate);
								vo.setToTime(convertDate1);
								vo.setFromDate(params[8]!=null ? params[8].toString():"");
								vo.setToDate(params[12]!=null ? params[12].toString():"");
								vo.setDate(params[14]!=null ? params[14].toString().split(" ")[0]:"");
								vo.setFormatDate(params[14]!=null ? format.format((Date)params[14]):"");
								vo.setAppointmentUniqueId(params[13]!=null?params[13].toString():"");
								vo.setApptTimeSlotId(params[17]!=null?(Long)params[17]:0l);
							resultList.add(vo);
						}
						AppointmentScheduleVO candidateVo = new AppointmentScheduleVO();
						candidateVo.setId(params[0] != null ? Long.valueOf(params[0].toString()) :0l);
						candidateVo.setName(params[1] != null ? params[1].toString() :"");
						candidateVo.setMobileNo(params[2] != null ? params[2].toString() : "");
						candidateVo.setImageUrl(params[15]!=null?params[15].toString():"");
						
						//candidateVo.setDesignation(params[3] != null ? params[3].toString() : "");
						String fname = params[6] != null ? params[6].toString() : "";
						String lname = params[7] != null ?params[7].toString() : "";
						candidateVo.setCreatedBy(fname+" "+lname);
					
						vo.getSubList().add(candidateVo);
					}
				}
			   
		}catch(Exception e){
			LOG.error("Exception raised at getAllScheduledApptsByDate", e);
		}
		return resultList;
		
	}
    public void getDesignationsForCadre(List<Long> tdpCadreIds,List<AppointmentCandidateVO> finalList)
    {
    	try{
    	List<Long> filterCadreIds = new ArrayList<Long>();
    	List<Long> cadreIds = tdpCadreIds;
    	if(cadreIds != null && cadreIds.size() > 0){
    	List<Object[]> representativeList = tdpCommitteeMemberDAO.getDesignationsForPublicRepresentative(cadreIds);
    	if(representativeList != null && representativeList.size() > 0)
    	{
    		for(Object[] params : representativeList)
    		{
    			AppointmentCandidateVO vo = getMatchedVO(finalList,(Long)params[0]);
    			 if(vo != null)
    			 {
    				  vo.setDesignation(params[2]!=null?params[2].toString():"");
    				 if(!filterCadreIds.contains((Long)params[0]))
    					 filterCadreIds.add((Long)params[0]);
    			 }
    		}
    	}
    	if(filterCadreIds != null && filterCadreIds.size() > 0)
    		cadreIds.removeAll(filterCadreIds);
    	}
    	
    	if(cadreIds != null && cadreIds.size() > 0)
    	{
    	List<Object[]> comittteeList = tdpCommitteeMemberDAO.getDesignationsForCadreCommittee(cadreIds);
    	if(comittteeList != null && comittteeList.size() > 0)
    	{
    		for(Object[] params : comittteeList)
    		{
    			AppointmentCandidateVO vo = getMatchedVO(finalList,(Long)params[0]);
    			 if(vo != null)
    			 {
    				
    				  vo.setDesignation(params[2]!=null?params[2].toString():"");
    			 }
    		}
    	}
    	}
    }
    	 catch(Exception e)
    	 {
    		 LOG.error("Exception raised at getDesignationsForCadre", e);
    	 }
    }
   
    
    
	public AppointmentStatusFlowVO getLoginUserAppointmentUserType(Long userId) {
		
		AppointmentStatusFlowVO appointmentStatusFlowVO=new AppointmentStatusFlowVO();
		
		try{
			
			List<Long> apptUserTypeIds=userAppointmentUserDAO.getLoginUserAppointmentUserType(userId);
			
			if(apptUserTypeIds!=null && apptUserTypeIds.size() >0 ){
				
				Long apptUserTypeId = apptUserTypeIds.get(0);
				
				appointmentStatusFlowVO.setAppointmentUserTypeId(apptUserTypeId);
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getLoginUserAppointmentUserType() method of AppointmentService", e);
		}
		return appointmentStatusFlowVO;
	}
	
	public List<AppointmentScheduleVO> setPreferDatesToApptmnt(List<Long> aptmnts,List<AppointmentScheduleVO> apptvoList){
		
		try{
			
			SimpleDateFormat prefer = new SimpleDateFormat("dd MMM yyyy");
			
			List<Object[]>  apptDates = appointmentPreferableDateDAO.getMultipleDatesforAppointments(aptmnts);
			if(apptDates!=null && apptDates.size()>0){
				for(Object[] object : apptDates){
					
					AppointmentScheduleVO apptvo = getMatchedVoForAptment((Long)object[0],apptvoList);
					
				if(apptvo !=null){
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
					
				}
			}
			
		}catch (Exception e) {
			LOG.error("Exception raised at setPreferDatesToApptmnt() method of AppointmentService", e);
		}
		return apptvoList;
	}
	
	public ResultStatus updateAppointmentReason(Long appointmentId,String reason,Long userId){
		ResultStatus result = new ResultStatus();
		try{
			
			Date presentDate = dateUtilService.getCurrentDateAndTime();
			
			int record = appointmentDAO.updateAppointmentReason(appointmentId,reason,presentDate,userId);
			result.setExceptionMsg("success");
			result.setResultCode(0);
			result.setMessage(reason);
			
		}catch (Exception e) {
			result.setExceptionMsg("failure");
			result.setResultCode(1);
			LOG.error("Exception raised at updateAppointmentReason() method of AppointmentService", e);
		}
		return result;
	}
	
	public LabelStatusVO getStatusWiseCountsOfAppointments(Long aptUserId){
		
		LabelStatusVO finalVo = new LabelStatusVO();
		
		try{  
			  
			  List<Long>  apptStatusList =Arrays.asList(IConstants.TODAY_APPOINTMENTS_STATUS_LIST); 
			  
			  Map<Long,LabelStatusVO>  finalMap = new LinkedHashMap<Long,LabelStatusVO>();
			  
			  List<Object[]> statusList = appointmentStatusDAO.getStatusDetailsByIds(apptStatusList);
			  
			  if(statusList!=null && statusList.size()>0){
				
				  for(Object[] obj : statusList){
					  
					  Long statusId = obj[0]!=null?(Long)obj[0]:0l;
					  
					  LabelStatusVO statusVO = new LabelStatusVO();
					  statusVO.setStatusId(obj[0]!=null?(Long)obj[0]:0l);
					  statusVO.setStatus(obj[0]!=null?obj[1].toString():"");
					  statusVO.setTotalCount(0l);
					  
					  statusVO.setClickIds(new ArrayList<Long>(0));
					  statusVO.getClickIds().add(statusId);
						  
						finalMap.put(statusId, statusVO);
				  }
					  
			  }
			  
			  //By Created Date
			  //List<Object[]> countsList = appointmentDAO.eachStatusApptCountByDateAndApptUser(aptUserId,apptStatusList,new DateUtilService().getCurrentDateAndTime());
			  //By Scheduled Date
			  List<Object[]> countsList = appointmentDAO.eachStatusApptCountByDateAndApptUserNew(aptUserId,apptStatusList,new DateUtilService().getCurrentDateAndTime());
			  
			  if( countsList != null  && countsList.size() >0){
				  
				  for( Object[] obj : countsList){
					  
					  Long statusId = obj[0]!=null?(Long)obj[0]:0l;
					  
					  LabelStatusVO statusVO = null;
					  
					  statusVO = finalMap.get(statusId);
					  
					  if(statusVO != null){
						  statusVO.setTotalCount(  statusVO.getTotalCount() +( obj[2]!=null?(Long)obj[2]:0l));
					  }
					
				  }
			  }
			  
			  if(finalMap!=null && finalMap.size()>0){
				  finalVo.setStatusList(new ArrayList<LabelStatusVO>(finalMap.values()));
			  }
			  
		}catch(Exception e){
			LOG.error("Exception raised at getStatusWiseCountsOfAppointments", e);
		}
		return finalVo;
	}
	public AppointmentScheduleVO getMatchedVoForAptment(Long aptMentId,List<AppointmentScheduleVO> apptvoList){
		try{			
			if(apptvoList !=null && apptvoList.size()>0){
				for (AppointmentScheduleVO appointmentScheduleVO : apptvoList) {
					if(aptMentId !=null){						
						if(appointmentScheduleVO.getAppointmentId().equals(aptMentId)){
							return  appointmentScheduleVO;
						}
					}
				}
			}
			
		}catch (Exception e) {
			LOG.error("Exception raised at getMatchedVoForAptment", e);
		}
		return null;
	}
	public AppointmentScheduleVO setPreferDatesToAppointment(List<Long> aptmnts,AppointmentScheduleVO apptvo){
		
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
					
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return apptvo;
	}
	public AppointmentScheduleVO getRescheduledAppsCounts(Long appUserId){
		 
		AppointmentScheduleVO rescheduledVO=new AppointmentScheduleVO();
		
		try {
		 Long rschdldUnquCnt=appointmentTrackingDAO.getAllRescheduledApptCounts(appUserId);
		 Long rschdldCnddtUnquCnt=appointmentTrackingDAO.getAllRescheduledCandiCounts(appUserId);
		 List<AppointmentScheduleVO> appointmentScheduleVOList =  getRescheduledAppointmentsDetails(appUserId);
		 Long totalReschedules = 0l;
		 if(appointmentScheduleVOList != null && appointmentScheduleVOList.size() > 0){
			 for (AppointmentScheduleVO asvo : appointmentScheduleVOList) {
				if(asvo.getRescheduledList() != null && asvo.getRescheduledList().size() > 0 && asvo.getSubList() != null && asvo.getSubList().size() > 0){
					totalReschedules = totalReschedules+(asvo.getRescheduledList().size() * asvo.getSubList().size());
				}
			}
		 }
		  
		 rescheduledVO.setTotalRequestedAppCount(totalReschedules);
		 rescheduledVO.setTotalRescheduledCount(rschdldUnquCnt!=null?rschdldUnquCnt:0l);
		 rescheduledVO.setTotalReschedCandidateCount(rschdldCnddtUnquCnt!=null?rschdldCnddtUnquCnt:0l);
		 
		} catch (Exception e) {
			LOG.error("Error occured at getRescheduledAppsCounts() in AppointmentService",e);
		}
		return rescheduledVO;
	}
	/**
	   *   @author    : Sreedhar
	   *   Description:This Service is used to get the rescheduled appointwise details
	   *   inputs: apptUserId
	   *   output: List<AppointmentScheduleVO>
	   *   
	  */
	public List<AppointmentScheduleVO> getRescheduledAppointmentsDetails(Long apptUserId){
		
		List<AppointmentScheduleVO> finalList = new ArrayList<AppointmentScheduleVO>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		try{
			 
			Map<Long,AppointmentScheduleVO>  finalMap = new LinkedHashMap<Long,AppointmentScheduleVO>();
			Set<Long> tdpCadreIds = new HashSet<Long>();
			List<Long> cadreIds = new ArrayList<Long>();
			List<Long> partyCommiteeDesigCadreIds = new ArrayList<Long>();
			Map<Long,Long> designationMap = new HashMap<Long, Long>();
			List<Long> PrCadreIds = new ArrayList<Long>();
			
			List<Object[]> rescheduledApptAndCandiList = appointmentTrackingDAO.getAllRescheduledApptAndCandiDetails(apptUserId);
			
			List<Object[]> rescheduledDatesAndCommentsList = appointmentTrackingDAO.getAllRescheduledCommentsAndrescheduledDates(apptUserId);
			
			if( rescheduledApptAndCandiList != null && rescheduledApptAndCandiList.size() > 0){
				
				for( Object[] obj : rescheduledApptAndCandiList ){
					
					AppointmentScheduleVO apptVO = finalMap.get((Long)obj[0]);
					boolean isApptExist = true;
					
					if(apptVO == null){
						isApptExist = false;
						apptVO = new AppointmentScheduleVO();
						apptVO.setAppointmentId((Long)obj[0]);
						apptVO.setAppointmentUniqueId(obj[1]!=null?obj[1].toString():"");
						apptVO.setDate(obj[2]!=null?sdf.format((Date)obj[2]):"");
						apptVO.setPresentStatus(obj[3]!=null?obj[3].toString():"");
					}
					if(apptVO.getSubMap() == null){
						apptVO.setSubMap(new LinkedHashMap<Long, AppointmentScheduleVO>());
					}
					AppointmentScheduleVO candiVO = new AppointmentScheduleVO();
					candiVO.setId(obj[4]!= null ? (Long)obj[4]:0l);
					candiVO.setName(obj[5]!= null ?obj[5].toString():"");
					candiVO.setImageUrl(obj[6]!= null ?obj[6].toString():"");
					candiVO.setMobileNo(obj[7]!= null ?obj[7].toString():"");
					candiVO.setTdpCadreId(obj[8]!= null ?(Long)obj[8]:0l);
					candiVO.setDesignation(obj[11] != null ? obj[11].toString() : "");
					
					Long apptcanditype = obj[12] !=null ?(Long)obj[12]:null;
					
					//get All tdpcadreids
					tdpCadreIds.add(candiVO.getTdpCadreId());
					apptVO.getSubMap().put(candiVO.getId(),candiVO);
					
					 //candidates designation based on appt cand type
					Long tdpcadreId =    obj[8] !=null ?(Long)obj[8]:null;
					
					if(apptcanditype != null){
						candiVO.setApptCandiTypeId(apptcanditype);
						if(apptcanditype.longValue() == 4l){
							
							candiVO.setCandDesignation(obj[13] !=null ?obj[13].toString():"");
							candiVO.setConstituency(obj[9] !=null ?WordUtils.capitalize(obj[9].toString().toLowerCase())+" Constituency":"");
							candiVO.setAddressConstituency(obj[9] !=null ? obj[9].toString().trim():"");
							
						}else if(apptcanditype.longValue() == 3l){
							candiVO.setCandDesignation(obj[13] !=null ?obj[13].toString():"");
							cadreIds.add(tdpcadreId);
						}else if(apptcanditype.longValue() == 2l && tdpcadreId!=null){
							partyCommiteeDesigCadreIds.add(tdpcadreId);
						}else if(apptcanditype.longValue() == 1l && tdpcadreId!=null && obj[10]!=null){
							candiVO.setCandDesignation(obj[11] != null ? obj[11].toString() : "");
							designationMap.put(tdpcadreId, (Long)obj[10]);
							PrCadreIds.add(tdpcadreId);								
						}
					}
					
					if(!isApptExist){
						finalMap.put((Long)obj[0], apptVO);
					}
				}
			}
			
			
			if(rescheduledDatesAndCommentsList != null && rescheduledDatesAndCommentsList.size() > 0){
				for(Object[] obj : rescheduledDatesAndCommentsList){
					AppointmentScheduleVO apptVO = finalMap.get((Long)obj[0]);
					if( apptVO != null){
						
						if (((Long)obj[4]).longValue() == 1l )
						{
							
							AppointmentScheduleVO rescheduledVO = new AppointmentScheduleVO();
							rescheduledVO.setName(obj[3]!=null?obj[3].toString():"");
							rescheduledVO.setDate(obj[1]!=null?sdf.format((Date)obj[1]):"");
							if(rescheduledVO.getSubject() == null)
							{
								rescheduledVO.setSubject(obj[2]!=null?obj[2].toString():null);	
							}else{
								rescheduledVO.setSubject(obj[2]!=null? rescheduledVO.getSubject()+","+ obj[2].toString():null);
							}
							if( apptVO.getRescheduledList()==null){
								apptVO.setRescheduledList(new ArrayList<AppointmentScheduleVO>());
							}
							apptVO.getRescheduledList().add(rescheduledVO);
							
						}else{
							
							if(apptVO.getRescheduledList() != null && apptVO.getRescheduledList().size() > 0){
								
								AppointmentScheduleVO rescheduledVO = apptVO.getRescheduledList().get(apptVO.getRescheduledList().size() - 1);
								if( rescheduledVO != null){
									if(rescheduledVO.getSubject() == null)
									{
										rescheduledVO.setSubject(obj[2]!=null?obj[2].toString():null);	
									}else{
										rescheduledVO.setSubject(obj[2]!=null? rescheduledVO.getSubject()+","+ obj[2].toString():null);
									}
								}
							}
						}
						
						
						/*AppointmentScheduleVO rescheduledVO = new AppointmentScheduleVO();
						rescheduledVO.setDate(obj[1]!=null?sdf.format((Date)obj[1]):"");
						rescheduledVO.setSubject(obj[2]!=null?obj[2].toString():"");
						rescheduledVO.setName(obj[3]!=null?obj[3].toString():"");
						
						if( apptVO.getRescheduledList()==null){
							apptVO.setRescheduledList(new ArrayList<AppointmentScheduleVO>());
						}
						apptVO.getRescheduledList().add(rescheduledVO);*/
					}
				}
			}
			
			//Map iteration
			if( finalMap != null && finalMap.size() > 0){
				for (Map.Entry<Long, AppointmentScheduleVO> entry : finalMap.entrySet()) {
					
					if( entry.getValue().getSubMap() != null && entry.getValue().getSubMap().size() > 0){
						entry.getValue().getSubList().addAll(entry.getValue().getSubMap().values());
						entry.getValue().getSubMap().clear();
					}
				}
				finalList.addAll(finalMap.values());
			}
			
			Map<Long,String> publicRepresLocaMap = new HashMap<Long,String>();
			if(PrCadreIds !=null && PrCadreIds.size()>0){
				publicRepresLocaMap = locationService.getLocationMapForDesignation(designationMap,PrCadreIds);
			}
			if(cadreIds!=null && cadreIds.size()>0){
				setConstituenciesforTdpcadreIds(finalList,cadreIds);
			}
			if(partyCommiteeDesigCadreIds!=null && partyCommiteeDesigCadreIds.size()>0){
				setPartyPositionDesignationsforTdpcadreIds(finalList,partyCommiteeDesigCadreIds);
			}
			if(publicRepresLocaMap!=null && publicRepresLocaMap.size()>0){
				setPublicRepresenativeLocations(finalList,publicRepresLocaMap);
			}
			
			
		}catch(Exception e){
			LOG.error("Error occured at getRescheduledAppointmentsDetalis() in AppointmentService",e);
		}
	    return finalList;
	}
	
	/**
	   *   @author    : Sreedhar
	   *   Description:This Service is used to get the rescheduled member wise appointments.
	   *   inputs: apptUserId
	   *   output: List<AppointmentScheduleVO>
	   *   
	  */
 public List<AppointmentScheduleVO> getRescheduledMembersApptDetails(Long apptUserId){
		
		List<AppointmentScheduleVO> finalList = new ArrayList<AppointmentScheduleVO>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		try{
			 
			Map<Long,AppointmentScheduleVO>  finalMap = new LinkedHashMap<Long,AppointmentScheduleVO>();
			
			//for candidate desig and constituencies.
			List<Long> cadreIds = new ArrayList<Long>();
			List<Long> partyCommiteeDesigCadreIds = new ArrayList<Long>();
			List<Long> PrCadreIds = new ArrayList<Long>();
			Map<Long,Long> designationMap = new HashMap<Long, Long>();
			
			
			List<Object[]> candiApptsList = appointmentTrackingDAO.getCandiWiseRescheduledAppts(apptUserId);
			if( candiApptsList != null && candiApptsList.size() > 0){
				
				//set candidates and appointments.
				for( Object[] obj : candiApptsList){
	 				
	 				boolean isCandiExist = true;
	 				
	 				AppointmentScheduleVO candiVO = finalMap.get((Long)obj[0]);
	 				
	 				if( candiVO == null){
	 					
	 					isCandiExist = false;
	 					candiVO = new AppointmentScheduleVO();
	 					candiVO.setId(obj[0]!=null ?(Long)obj[0] : 0l);
	 					candiVO.setName(obj[1]!= null ?obj[1].toString():"");
						candiVO.setImageUrl(obj[2]!= null ?obj[2].toString():"");
						candiVO.setMobileNo(obj[3]!= null ?obj[3].toString():"");
						candiVO.setTdpCadreId(obj[8]!= null ?(Long)obj[8]:0l);
						candiVO.setDesignation(obj[5] != null ? obj[5].toString() : "");
						
						//for cand desig and const.
						Long tdpcadreId =    obj[8] !=null ?(Long)obj[8]:null;
						Long apptcanditype = obj[6] !=null ?(Long)obj[6]:null;
						
						if(apptcanditype != null){
							candiVO.setApptCandiTypeId(apptcanditype);
							if(apptcanditype.longValue() == 4l){
								
								candiVO.setCandDesignation(obj[7] !=null ?obj[7].toString():"");//Other
								candiVO.setConstituency(obj[9] !=null ?WordUtils.capitalize(obj[9].toString().toLowerCase())+" Constituency":"");
								
							}else if(apptcanditype.longValue() == 3l){
								candiVO.setCandDesignation(obj[7] !=null ?obj[7].toString():"");//Cadre
								cadreIds.add(tdpcadreId);
							}else if(apptcanditype.longValue() == 2l && tdpcadreId!=null){
								partyCommiteeDesigCadreIds.add(tdpcadreId);
							}else if(apptcanditype.longValue() == 1l && tdpcadreId!=null && obj[4]!=null){
								candiVO.setCandDesignation(obj[5] != null ? obj[5].toString() : "");//MP,MLA...
								designationMap.put(tdpcadreId, (Long)obj[4]);
								PrCadreIds.add(tdpcadreId);								
							}
						}
						
	 				}
	 				AppointmentScheduleVO apptVO = new AppointmentScheduleVO();
	 				apptVO.setAppointmentId((Long)obj[10]);
					apptVO.setAppointmentUniqueId(obj[11]!=null?obj[11].toString():"");
					apptVO.setDate(obj[12]!=null?sdf.format((Date)obj[12]):"");
					apptVO.setPresentStatus(obj[13]!=null?obj[13].toString():"");
	 				
					if(candiVO.getSubMap() == null){
						candiVO.setSubMap(new LinkedHashMap<Long, AppointmentScheduleVO>(0));
					}
					candiVO.getSubMap().put(apptVO.getAppointmentId(),apptVO);
					
	 				if(!isCandiExist){
	 					finalMap.put((Long)obj[0], candiVO);
	 				}
	 			}
				
				List<Object[]> rescheduledDates = appointmentTrackingDAO.getMeberWiseRescheduledAppts(apptUserId);
				if( rescheduledDates != null && rescheduledDates.size() > 0){
					setCandiApptsRescheduledDates(rescheduledDates,finalMap);
				}
			}
			
			//Map iteration
			if( finalMap != null && finalMap.size() > 0){
				for (Map.Entry<Long, AppointmentScheduleVO> entry : finalMap.entrySet()) {
					
					if( entry.getValue().getSubMap() != null && entry.getValue().getSubMap().size() > 0){
						entry.getValue().getSubList().addAll(entry.getValue().getSubMap().values());
						entry.getValue().getSubMap().clear();
					}
				}
				finalList.addAll(finalMap.values());
			}
			
			if(cadreIds!=null && cadreIds.size()>0){
				Map<Long,String> constMap = getConstforTdpcadreIds(cadreIds);
				setConstforTdpcadreIds(finalList,constMap);
			}
			if(partyCommiteeDesigCadreIds!=null && partyCommiteeDesigCadreIds.size()>0){
				Map<Long,String> partyCommiteeDesignationsMap = getPartyPositionDesignationMap(partyCommiteeDesigCadreIds);
				setPartyPositionDesignations(finalList,partyCommiteeDesignationsMap);
			}
			if(PrCadreIds !=null && PrCadreIds.size()>0){
				Map<Long,String> publicRepresLocaMap  = locationService.getLocationMapForDesignation(designationMap,PrCadreIds);
				setPublicRepresenatLocations(finalList,publicRepresLocaMap);
			}
			
		}catch(Exception e){
			LOG.error("Error occured at getRescheduledAppointmentsDetalis() in AppointmentService",e);
			e.printStackTrace();
		}
	    return finalList;
	}
 	public void setPublicRepresenatLocations(List<AppointmentScheduleVO> resultList,Map<Long,String> publicRepresLocaMap){
		try{
			
			if(publicRepresLocaMap!=null && publicRepresLocaMap.size()>0)
			{	
				for( AppointmentScheduleVO candi :resultList)
				{			
					if(candi.getApptCandiTypeId()!=null && candi.getApptCandiTypeId().longValue() == 1l)
					{	
						if(candi.getTdpCadreId()!=null)
						{
							String location = publicRepresLocaMap.get(candi.getTdpCadreId());
							if(location!=null && !location.isEmpty()){
								candi.setConstituency(WordUtils.capitalize(location.toLowerCase()));
							}else{
								candi.setConstituency(location);
							}
						}
					}
				}
			}
			
		}catch (Exception e) {
			LOG.error("Exception raised at setPublicRepresenatLocations", e);
		}
	}
 	public Map<Long,String> getPartyPositionDesignationMap(List<Long> cadreIds){
 		Map<Long,String> partyCommiteeDesignationsMap = new HashMap<Long, String>();
 		try{
 			List<Object[]> partyPositionDetails= tdpCommitteeMemberDAO.getPartyPositionsBycadreIdsList(cadreIds);
			 
			 if(partyPositionDetails !=null && partyPositionDetails.size()>0)
			 {
				 CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
				 
				 for (Object[] partyPosition : partyPositionDetails) {

					 String level=partyPosition[0] != null ? partyPosition[0].toString() : "" ;
					 String role=partyPosition[1] != null ? partyPosition[1].toString() : "";
					 
					 String state = commonMethodsUtilService.getStringValueForObject(partyPosition[6]);
					 
					 String commiteestr=partyPosition[2] != null ? partyPosition[2].toString() : "";
					 
					 if(level != null && !level.isEmpty()&&level.equalsIgnoreCase("state"))
					 {
						 level = state+" "+level;
					 }
					 String partyPositionStr = level +" " +role+" ( "+commiteestr+" )";
					 if(!partyPositionStr.isEmpty())
					 { 
						 partyCommiteeDesignationsMap.put((Long)partyPosition[5],partyPositionStr);
					 }
				 }
			 }
		}catch(Exception e){
			LOG.error("Error occured at getPartyPositionDesignationMap() in AppointmentService",e);
		}
 		return partyCommiteeDesignationsMap;
 	}
 
   public void setPartyPositionDesignations(List<AppointmentScheduleVO> resultList,Map<Long,String> partyCommiteeDesignationsMap){
		try{
			if(partyCommiteeDesignationsMap!=null && partyCommiteeDesignationsMap.size()>0){
				for( AppointmentScheduleVO candi :resultList){
					if(candi.getApptCandiTypeId()!=null && candi.getApptCandiTypeId().longValue() == 2l){
						if(candi.getTdpCadreId()!=null){
							candi.setCandDesignation(partyCommiteeDesignationsMap.get(candi.getTdpCadreId()));
						}
					}
				}
			}
		}catch(Exception e) {
			LOG.error("Exception raised at setPartyPositionDesignations", e);
		}
	}
    public Map<Long,String> getConstforTdpcadreIds(List<Long> cadreIds){
    	Map<Long,String> constMap = new HashMap<Long,String>();
    	try{
			if(cadreIds!=null && cadreIds.size() >0 ){
				List<Object[]> constlist = tdpCadreDAO.getConstituencyForCadreIds(cadreIds);
				if(constlist!=null && constlist.size()>0){
					for(Object[] obj :constlist){
						constMap.put((Long)obj[0],obj[1].toString());
					}
				}
			}
		}catch(Exception e){
			LOG.error("Error occured at getConstforTdpcadreIds() in AppointmentService",e);
		}
    	return constMap;
    }
 	public void setConstforTdpcadreIds(List<AppointmentScheduleVO> resultList,Map<Long,String> constMap){
		try{
			if(constMap!=null && constMap.size()>0)
			{
				for( AppointmentScheduleVO candi :resultList)
				{			
					if(candi.getApptCandiTypeId()!=null && candi.getApptCandiTypeId().longValue() == 3l)
					{
						if(candi.getTdpCadreId()!=null){
							String location = constMap.get(candi.getTdpCadreId());
							if(location == null || location.isEmpty()){
								candi.setConstituency("");
							}else{
								candi.setConstituency(WordUtils.capitalize(location.toLowerCase())+" Constituency");
							}
						}
					}
						
				}
			}
		}catch (Exception e) {
			LOG.error("Exception raised at setConstituenciesforTdpcadreIds", e);
		}
	}
 	public void setCandiApptsRescheduledDates(List<Object[]> candiApptsList,Map<Long,AppointmentScheduleVO>  finalMap){
 		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
 		try{
			for( Object[] obj : candiApptsList){
				
				AppointmentScheduleVO candiVO = finalMap.get((Long)obj[0]);
				
				if( candiVO != null)
				{	
					if (candiVO.getSubMap() != null && candiVO.getSubMap().size() > 0)
					{
						
						AppointmentScheduleVO apptVO = candiVO.getSubMap().get((Long)obj[1]);
						
						if( apptVO != null)
						{
							
							if(((Long)obj[2]).longValue() == 1l)
							{
								
								if(apptVO.getSubList() == null || apptVO.getSubList().size() == 0)
								{
									apptVO.setSubList(new ArrayList<AppointmentScheduleVO>(0));
								}
								AppointmentScheduleVO rescheduledVO = new AppointmentScheduleVO();
								rescheduledVO.setDate(obj[3]!=null?sdf.format((Date)obj[3]):"");
								
								if(rescheduledVO.getSubject() == null)
								{
									rescheduledVO.setSubject(obj[4]!=null?obj[4].toString():null);	
								}else{
									rescheduledVO.setSubject(obj[4]!=null? rescheduledVO.getSubject()+","+ obj[4].toString():null);
								}
								apptVO.getSubList().add(rescheduledVO);
							}else{
								
								if(apptVO.getSubList() != null && apptVO.getSubList().size() > 0){
									
									AppointmentScheduleVO rescheduledVO = apptVO.getSubList().get(apptVO.getSubList().size() - 1);
									if( rescheduledVO != null){
										if(rescheduledVO.getSubject() == null)
										{
											rescheduledVO.setSubject(obj[4]!=null?obj[4].toString():null);	
										}else{
											rescheduledVO.setSubject(obj[4]!=null? rescheduledVO.getSubject()+","+ obj[4].toString():null);
										}
									}
								}
							}
						}
					}
				}
			}
 			
		}catch(Exception e){
			LOG.error("Exception raised at setCandiApptsRescheduledDates", e);
		}
 	}
 	
 	/**
	   *   @author    : Sreedhar
	   *   Description:This Service is used to get the rescheduled member wise Overview Summary
	   *   inputs: apptUserId
	   *   output: List<AppointmentScheduleVO>
	   *   
	  */
 		public List<AppointmentScheduleVO> overviewSummaryOfRescheduledCandidates(Long apptUserId){
 			
 			List<AppointmentScheduleVO> finalList = new ArrayList<AppointmentScheduleVO>(0);
 			try{
 				
 				Map<Long,AppointmentScheduleVO>  finalMap = new LinkedHashMap<Long,AppointmentScheduleVO>();
 				//for candidate desig and constituencies.
 				List<Long> cadreIds = new ArrayList<Long>();
 				List<Long> partyCommiteeDesigCadreIds = new ArrayList<Long>();
 				List<Long> PrCadreIds = new ArrayList<Long>();
 				Map<Long,Long> designationMap = new HashMap<Long, Long>();
 				
 				Set<Long> candidateIds = new HashSet<Long>(0);
 				
 				List<Object[]> candiApptsList = appointmentTrackingDAO.overviewSummaryOfRescheduledCandidates(apptUserId);
 				if( candiApptsList != null && candiApptsList.size() > 0){
 					
 					//set candidates and appointments.
 					for( Object[] obj : candiApptsList){
 		 				
 		 				AppointmentScheduleVO candiVO = new AppointmentScheduleVO();
 		 					
	 					candiVO.setId(obj[0]!=null ?(Long)obj[0] : 0l);
	 					candiVO.setName(obj[1]!= null ?obj[1].toString():"");
						candiVO.setImageUrl(obj[2]!= null ?obj[2].toString():"");
						candiVO.setMobileNo(obj[3]!= null ?obj[3].toString():"");
						candiVO.setTdpCadreId(obj[8]!= null ?(Long)obj[8]:0l);
						candiVO.setDesignation(obj[5] != null ? obj[5].toString() : "");
						candiVO.setRescheduledApptsCount(obj[10]!= null ?(Long)obj[10]:0l);
						candiVO.setRescheduledCount(obj[11]!= null ?(Long)obj[11]:0l);
						
						candidateIds.add(candiVO.getId());
						
						//for cand desig and const.
						Long tdpcadreId =    obj[8] !=null ?(Long)obj[8]:null;
						Long apptcanditype = obj[6] !=null ?(Long)obj[6]:null;
 							
						if(apptcanditype != null){
							candiVO.setApptCandiTypeId(apptcanditype);
							if(apptcanditype.longValue() == 4l){
								
								candiVO.setCandDesignation(obj[7] !=null ?obj[7].toString():"");//Other
								candiVO.setConstituency(obj[9] !=null ?WordUtils.capitalize(obj[9].toString().toLowerCase())+" Constituency":"");
								
							}else if(apptcanditype.longValue() == 3l){
								candiVO.setCandDesignation(obj[7] !=null ?obj[7].toString():"");//Cadre
								cadreIds.add(tdpcadreId);
							}else if(apptcanditype.longValue() == 2l && tdpcadreId!=null){
								partyCommiteeDesigCadreIds.add(tdpcadreId);
							}else if(apptcanditype.longValue() == 1l && tdpcadreId!=null && obj[4]!=null){
								candiVO.setCandDesignation(obj[5] != null ? obj[5].toString() : "");//MP,MLA...
								designationMap.put(tdpcadreId, (Long)obj[4]);
								PrCadreIds.add(tdpcadreId);								
							}
						}
 		 				 finalMap.put((Long)obj[0], candiVO);
 		 			}
 				}
 				//Map iteration
 				if( finalMap != null && finalMap.size() > 0){
 					finalList.addAll(finalMap.values());
 				}
 				
 				if(cadreIds!=null && cadreIds.size()>0){
 					Map<Long,String> constMap = getConstforTdpcadreIds(cadreIds);
 					setConstforTdpcadreIds(finalList,constMap);
 				}
 				if(partyCommiteeDesigCadreIds!=null && partyCommiteeDesigCadreIds.size()>0){
 					Map<Long,String> partyCommiteeDesignationsMap = getPartyPositionDesignationMap(partyCommiteeDesigCadreIds);
 					setPartyPositionDesignations(finalList,partyCommiteeDesignationsMap);
 				}
 				if(PrCadreIds !=null && PrCadreIds.size()>0){
 					Map<Long,String> publicRepresLocaMap  = locationService.getLocationMapForDesignation(designationMap,PrCadreIds);
 					setPublicRepresenatLocations(finalList,publicRepresLocaMap);
 				}
 				
 				//total appts taken by candidates.
 				if(candidateIds != null && candidateIds.size() > 0){
 					List<Object[]> list = appointmentCandidateRelationDAO.getTotalApptsByCandiates(new ArrayList<Long>(candidateIds),apptUserId);
 					if( list != null && list.size() > 0){
 						for( Object[] obj : list){
 							AppointmentScheduleVO candiVO = finalMap.get((Long)obj[0]);
 							if(candiVO != null){
 								candiVO.setTotalApptsTaken(obj[1]!=null?(Long)obj[1]:0l);
 							}
 						}
 					}
 				}
 				
			}catch(Exception e){
				LOG.error("Exception raised at overviewSummaryOfRescheduledCandidates in Appointment service",e);
				e.printStackTrace();
			}
 			return finalList;
 		}
 		
 		/**
 		   *   @author    : Sreedhar
 		   *   Description:This Service is used to get A Candidate rescheduled appt details. 
 		   *   inputs: apptUserId
 		   *   output: List<AppointmentScheduleVO>
 		   *   
 		  */
 		public List<AppointmentScheduleVO> getApptRescheduledDetialsByCandidate(Long apptUserId,Long appointmentCandidateId){
 			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
 			Map<Long,AppointmentScheduleVO> apptsMap = new LinkedHashMap<Long, AppointmentScheduleVO>(0);
 			List<AppointmentScheduleVO> finalList = new ArrayList<AppointmentScheduleVO>(0);
 			try{
 				
 				//Query
 				List<Long> appointmentCandidateIds = new ArrayList<Long>();
 				appointmentCandidateIds.add(appointmentCandidateId);
 				List<Object[]> rescheduledDates = appointmentTrackingDAO.getMeberWiseRescheduledAppts(apptUserId,appointmentCandidateIds);
 				
				if( rescheduledDates != null && rescheduledDates.size() > 0)
				{
					
					for( Object[] obj : rescheduledDates)
					{
	 					
						AppointmentScheduleVO apptVO = apptsMap.get((Long)obj[1]);
						boolean isApptExist = true;
						
						if( apptVO == null)
						{
							isApptExist = false;
							apptVO = new AppointmentScheduleVO();
							apptVO.setAppointmentId((Long)obj[1]);
							apptVO.setAppointmentUniqueId(obj[5]!=null?obj[5].toString():"");
							apptVO.setPresentStatus(obj[6]!=null?obj[6].toString():"");
						}
							
						if(((Long)obj[2]).longValue() == 1l)
						{
							AppointmentScheduleVO rescheduledVO = new AppointmentScheduleVO();
							rescheduledVO.setDate(obj[3]!=null?sdf.format((Date)obj[3]):"");
							
							if(rescheduledVO.getSubject() == null)
							{
								rescheduledVO.setSubject(obj[4]!=null?obj[4].toString():null);	
							}else{
								rescheduledVO.setSubject(obj[4]!=null? rescheduledVO.getSubject()+","+ obj[4].toString():null);
							}
							
							if(apptVO.getSubList() == null || apptVO.getSubList().size() == 0)
							{
								apptVO.setSubList(new ArrayList<AppointmentScheduleVO>(0));
							}
							apptVO.getSubList().add(rescheduledVO);
							
							//appt rescheduled count.
							apptVO.setRescheduledCount(apptVO.getRescheduledCount().longValue() + 1l);
							
						}else{
							
							if(apptVO.getSubList() != null && apptVO.getSubList().size() > 0){
								
								AppointmentScheduleVO rescheduledVO = apptVO.getSubList().get(apptVO.getSubList().size() - 1);
								if( rescheduledVO != null){
									if(rescheduledVO.getSubject() == null)
									{
										rescheduledVO.setSubject(obj[4]!=null?obj[4].toString():null);	
									}else{
										rescheduledVO.setSubject(obj[4]!=null? rescheduledVO.getSubject()+","+ obj[4].toString():null);
									}
								}
							}
						}
						
						if(!isApptExist){
							apptsMap.put((Long)obj[1], apptVO);
						}
	 				}
				}
				//Map iteration
 				if( apptsMap != null && apptsMap.size() > 0){
 					finalList.addAll(apptsMap.values());
 				}
			}catch(Exception e){
				LOG.error("Exception raised at getApptRescheduledDetialsByCandidate in Appointment service", e);
				e.printStackTrace();
			}
 			return finalList;
 		}
 		
 		
 		//Advanced Search
 		public  List<AppointmentCandidateVO> advancedSearchRequestedMembers(String searchType,String searchValue,LocationInputVO inputVo){
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
 									    	
 								    		mandalMemList = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,locationVo,"mandal",inputVo);
 								    		if(mandalMemList != null && mandalMemList.size()>0)
 								    			setMembersDataForCadreRole(mandalMemList,finalList,inputVo.getAptUserId());
 								    		
 								    	}
 								    	if(locationVo.getTownIdsList() != null && locationVo.getTownIdsList().size() > 0)
 								    	{
 								    	
 								    		 townMemList = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,locationVo,"town",inputVo);
 								    		 if(townMemList != null && townMemList.size()>0)
 								    			setMembersDataForCadreRole(townMemList,finalList,inputVo.getAptUserId());
 								    		
 								    	}
 								    	if(locationVo.getDivisionIdsList() != null && locationVo.getDivisionIdsList().size() > 0){
 								    	 
 								    	 	
 								    	 	divisonMemList = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,locationVo,"division",inputVo);
 								    	 	if(divisonMemList != null && divisonMemList.size()>0)
 								    	 		setMembersDataForCadreRole(divisonMemList,finalList,inputVo.getAptUserId());
 								    	}
 								    	
 								   }
 								    
 								    else if(inputVo.getLevelId() == 6l)//Village,Ward Levels 
 								    {
 								    	List<Object[]> panchayatMemList = null;
 								    	List<Object[]> wardMemList = null;
 								    	if(locationVo.getVillageIdsList() != null && locationVo.getVillageIdsList().size() > 0){
 								    		
 								    		  panchayatMemList = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,locationVo,"panchayat",inputVo);
 								    		  if(panchayatMemList != null && panchayatMemList.size()>0)
 								    			 setMembersDataForCadreRole(panchayatMemList,finalList,inputVo.getAptUserId());
 								    		
 								    	}
 								    	if(locationVo.getWardIdsList() != null && locationVo.getWardIdsList().size() > 0){
 								    		
 								    		
 								    		  wardMemList = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,locationVo,"ward",inputVo);
 								    		  if(wardMemList != null && wardMemList.size()>0)
 								    			 setMembersDataForCadreRole(wardMemList,finalList,inputVo.getAptUserId());
 								    		
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
 								    	
 								    	
 								    	   List<Object[]> memList  = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,locationVo,"",inputVo);
 								    	   if(memList != null && memList.size()>0){
 								    		  setMembersDataForCadreRole(memList,finalList,inputVo.getAptUserId());
 								    	   }
 								    	
 								    }
 								    else // All
 								   {
 								    	List<Object[]> memList1 = null;
 								    
 								    	 memList1  = tdpCadreDAO.advancedSearchMemberForCadreCommittee(searchType,null,"",inputVo);
 								    	 if(memList1 != null && memList1.size()>0){
 								    		setMembersDataForCadreRole(memList1,finalList,inputVo.getAptUserId());
 								    	 }
 								    	
 								    }
 						    }
 						    
 					      
 					 	 else if(searchType.equalsIgnoreCase("name"))
 					      {
 					    	  List<Object[]> nameList = null;
 					    	
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
 							    		setMembersDataForCadreRole(nameList,finalList,inputVo.getAptUserId());
 								    	  }
 							    	}
 						    	  else
 						    	  {
 						    		  nameList = tdpCadreDAO.searchMemberByCriteria(searchType,searchValue,locationVo);  
 						    		  if(nameList != null && nameList.size()>0){
 						    			 setMembersDataForCadreRole(nameList,finalList,inputVo.getAptUserId());
 								    	  }
 						    	  }
 					     }
 					      else if(searchType.equalsIgnoreCase("publicRepresentative"))
 					      {
 					    	  List<Object[]> prList = null;
 					    	  
 					    	  if(inputVo.getLevelId() == 0l) //All
 					    		{
 					    		 
 					    		 
 					    		  prList = tdpCadreDAO.advancedSearchMemberForPublicRepresentative(searchType,null,inputVo);
 					    		  if(prList != null && prList.size()>0)
 					    		  {
 					    			 setMembersDataForCadreRole(prList,finalList,inputVo.getAptUserId());
 					    		  }
 					    		}
 					    		else
 					    		{
 					    			
 					    			  prList = tdpCadreDAO.advancedSearchMemberForPublicRepresentative(searchType,locationVo,inputVo);
 					    			  if(prList != null && prList.size()>0){
 					    				 setMembersDataForCadreRole(prList,finalList,inputVo.getAptUserId());
 					    			}
 					    		}
 					      }
 					 	
 				 		}
 				 catch (Exception e) {
 				LOG.error("Exception raised at advancedSearchApptRequestedMembers() method of AppointmentService", e);
 				 }
 				 
 				 
 			 return finalList;
 		 }
 		
 		public void setMembersDataForCadreRole(List<Object[]> membersList, List<AppointmentCandidateVO>  finalList,Long aptUserId)
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
 				/* List<Object[]> list = appointmentCandidateDAO.getAppointmentCandidateIdForCadreIds(tdpCadreIds,aptUserId);
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
 				 }*/
 				 setConstituencyForPR(tdpCadreIds,finalList);
 				// checkisEligibleForApptCadre(tdpCadreIds,aptUserId,finalList);
 				
 			 }
 		}
 		
 		
 		//search For Cadre
 		public  List<AppointmentCandidateVO> searchRequestedMembers(String searchType,String searchValue){
 			 List<AppointmentCandidateVO>  finalList = null;
 			 
 			 try {
 				      List<Object[]> membersList = null;
 				      List<Long> tdpCadreIds = new ArrayList<Long>();
 				      if(searchType.equalsIgnoreCase("mobileno") || searchType.equalsIgnoreCase("votercardno") || searchType.equalsIgnoreCase("mebershipno"))
				    	  membersList = tdpCadreDAO.searchMemberByCriteria(searchType,searchValue,null);
 				      else
 				    	  membersList =  tdpMemberDAO.searchTdpMemberByCriteria(searchType,searchValue,null);
 
 				     Map<Long, String> cadreEnrollmentYearsMap = new LinkedHashMap<Long, String>(0);
 				      Map<Long,AppointmentCandidateVO> uniqueCadres = new HashMap<Long,AppointmentCandidateVO>();
 				    	  if(membersList!=null && membersList.size()>0){
 				    		  finalList = new ArrayList<AppointmentCandidateVO>(); 
 				    		  for(Object[] obj: membersList){
 				    			 String year = obj[7]!=null?obj[7].toString():"";
 				    			 String existingYear=cadreEnrollmentYearsMap.get((Long)obj[0]);
 								if(existingYear!=null && existingYear.length()>0){
 									existingYear = year+", "+existingYear;  
 									cadreEnrollmentYearsMap.put((Long)obj[0], existingYear);
 									
 								}else{
 									cadreEnrollmentYearsMap.put((Long)obj[0], year);
 								}
 				    			 AppointmentCandidateVO vo = uniqueCadres.get((Long)obj[0]);
 				    			 if(vo == null){
 				    				vo =new AppointmentCandidateVO();
 				    				uniqueCadres.put((Long)obj[0], vo);
 				    				finalList.add(vo);
 				    			 }
 				    			  vo.setTdpCadreId(obj[0]!=null?(Long)obj[0]:0l);
 				    			  vo.setId(obj[0]!=null?(Long)obj[0]:0l);
 					    		  vo.setCandidateType("cadre");
 					    		  vo.setName(obj[1]!=null?obj[1].toString():"");
 					    		  vo.setCadre(true);
 					    		  vo.setMobileNo(obj[2]!=null?obj[2].toString():"");
 					    		  vo.setConstituency(obj[3]!=null?obj[3].toString():"");
 					    		  vo.setMemberShipId(obj[4]!=null?obj[4].toString():"");
 					    		  vo.setVoterCardNo(obj[5]!=null?obj[5].toString():"");
 					    		  vo.setImageURL(obj[6]!=null?"images/cadre_images/"+obj[6].toString():null);
 					    		 String years = cadreEnrollmentYearsMap.get(vo.getTdpCadreId());
 								if(years != null )
 									vo.setEnrollmentYears(years);
 					    		  
 					    		  if(!tdpCadreIds.contains(vo.getId()))
 					    			  tdpCadreIds.add(vo.getId());
 					    		  
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
 					    		  vo.setConstituency(obj[4] != null?obj[4].toString():"");
 					    		  vo.setVoterCardNo(searchValue);
 					    		  finalList.add(vo);
 					    		
 				    		  }
 				    		  
 				    	  }
 				    	  
 				      }
 				      if(tdpCadreIds!=null && tdpCadreIds.size()>0){
 				    	  
 				    	 /* List<Object[]> list = appointmentCandidateDAO.getAppointmentCandidateIdForCadreIds(tdpCadreIds,aptUserId);
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
 							 }*/
 							 setConstituencyForPR(tdpCadreIds,finalList);
 							// checkisEligibleForApptCadre(tdpCadreIds,aptUserId,finalList);
 							 getDesignationsForCadre(tdpCadreIds,finalList);
 							List<CadreCommitteeVO> memberList = getMemberStatusDetails(tdpCadreIds);
 							if(memberList != null && memberList.size() > 0l){
 								finalList.get(0).setPreviousElections(memberList);
 							}
 							
 				      }
 				     
 				  	
 				 	
 			} catch (Exception e) {
 				LOG.error("Exception raised at searchRequestedMembers() method of AppointmentService", e);
 			}
 			 return finalList;
 		 }
 		
 		public List<CadreCommitteeVO> getMemberStatusDetails(List<Long> tdpCadreIds){
 			 List<CadreCommitteeVO> finalList = new ArrayList<CadreCommitteeVO>();
 			 try {
 				 List<Long> npCandIds = null;
 				 CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
 				 if(commonMethodsUtilService.isListOrSetValid(tdpCadreIds)){
 					 npCandIds = nominationPostCandidateDAO.getNominatedPstCandidateIds(tdpCadreIds);
 				 }
 				 if(commonMethodsUtilService.isListOrSetValid(npCandIds)){
 					 List<Object[]> memberDetails = nominatedPostDAO.getMemberStatusDetails(npCandIds);
 					 if(commonMethodsUtilService.isListOrSetValid(memberDetails)){
 						 for (Object[] param : memberDetails) {
 							CadreCommitteeVO vo = new CadreCommitteeVO();
 							vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));//NPId
 							vo.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(param[1]));//NPCandId
 							vo.setCasteStateId(commonMethodsUtilService.getLongValueForObject(param[2]));//StatusId
 							vo.setCasteName(commonMethodsUtilService.getStringValueForObject(param[3]));//Status
 							vo.setCasteCategoryId(commonMethodsUtilService.getLongValueForObject(param[4]));//positionId
 							vo.setCasteCategory(commonMethodsUtilService.getStringValueForObject(param[5]));//postion Name
 							vo.setOccupation(commonMethodsUtilService.getStringValueForObject(param[6]));//DeptName
 							vo.setRole(commonMethodsUtilService.getStringValueForObject(param[7]));//BoardName
 							vo.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(param[8]));//BoardLevelId
 							vo.setElectionType(commonMethodsUtilService.getStringValueForObject(param[9]));//BoardLevelName
 							
 							vo.setRoleName(commonMethodsUtilService.getStringValueForObject(param[10]));//StateName
 							vo.setCadreName(commonMethodsUtilService.getStringValueForObject(param[11]));//DistrictName
 							vo.setConstituency(commonMethodsUtilService.getStringValueForObject(param[12]));
 							vo.setTehsil(commonMethodsUtilService.getStringValueForObject(param[13]));
 							vo.setLocalElectionBody(commonMethodsUtilService.getStringValueForObject(param[14]));
 							vo.setPanchayat(commonMethodsUtilService.getStringValueForObject(param[15]));
 							vo.setRelativeName(commonMethodsUtilService.getStringValueForObject(param[16]));//WardName
 							
 							
 							if(vo.getCadreName() != null && vo.getCadreName().length()>0)
 								vo.setRoleName(vo.getCadreName());
 							if(vo.getConstituency() != null && vo.getConstituency().length()>0)
 								vo.setRoleName(vo.getConstituency());
 							if(vo.getTehsil() != null && vo.getTehsil().length()>0)
 								vo.setRoleName(vo.getTehsil());
 							if(vo.getLocalElectionBody() != null && vo.getLocalElectionBody().length()>0)
 								vo.setRoleName(vo.getLocalElectionBody());
 							if(vo.getPanchayat() != null && vo.getPanchayat().length()>0)
 								vo.setRoleName(vo.getPanchayat());
 							if(vo.getRelativeName() != null && vo.getRelativeName().length()>0)
 								vo.setRoleName(vo.getRelativeName());
 							
 							finalList.add(vo);
 						}
 					 }
 				 }
 				 
 				
 			} catch (Exception e) {
 				LOG.error("Exception raised in CadreCommitteeService of getMemberStatusDetails", e);
 			}
 			 return finalList;
 		 }
 		public List<AppointmentCandidateVO> getNewCadreSearchBySearchType(String searchType,Long searchValue,String locationType,Long locationVal,String gender){
 			List<AppointmentCandidateVO> returnList = new ArrayList<AppointmentCandidateVO>();
 			try {
 				List<Long> tdpCadreIdsNominatedIdsList = new ArrayList<Long>();
 				Long minAge = 0l;
 				Long maxAge = 0l;
 				if(searchType != null && searchType.trim().equalsIgnoreCase("age")){
 					NominatedPostAgeRange ageRange = nominatedPostAgeRangeDAO.get(searchValue);
 					if(ageRange != null){
 						minAge = ageRange.getMinValue();
 						maxAge = ageRange.getMaxValue();
 					}
 				}
				List<Object[]> list = tdpCadreEnrollmentYearDAO.getCadreDetailsBySearch(searchType, searchValue, locationType, locationVal, gender, minAge, maxAge);
				if(list != null && !list.isEmpty()){
					for (Object[] obj : list) {
						AppointmentCandidateVO vo = new AppointmentCandidateVO();
						vo.setId(obj[0]!=null?(Long)obj[0]:0l);
						vo.setCandidateType("cadre");
						vo.setName(obj[1]!=null?obj[1].toString():"");
						vo.setCadre(true);
						vo.setMobileNo(obj[2]!=null?obj[2].toString():"");
						vo.setConstituency(obj[3]!=null?obj[3].toString():"");
						vo.setMemberShipId(obj[4]!=null?obj[4].toString():"");
						vo.setVoterCardNo(obj[5]!=null?obj[5].toString():"");
						vo.setImageURL(obj[6]!=null?"images/cadre_images/"+obj[6].toString():null);
						returnList.add(vo);
						tdpCadreIdsNominatedIdsList.add(vo.getId());
					}
				}
				
				if(tdpCadreIdsNominatedIdsList != null && !tdpCadreIdsNominatedIdsList.isEmpty()){
					List<Object[]> nominatedCandidatesList = nominationPostCandidateDAO.getNOminatedCadreList(tdpCadreIdsNominatedIdsList);
					if(nominatedCandidatesList != null && !nominatedCandidatesList.isEmpty()){
						for (Object[] obj : nominatedCandidatesList) {
							Long cadreId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
							Long nominatedCanId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
							AppointmentCandidateVO vo = getMatchedVO(returnList, cadreId);
							if(vo != null)
								vo.setAppointmentCandidateId(nominatedCanId);
						}
					}
				}
				
			} catch (Exception e) {
				LOG.error("Exception raised at getNewCadreSearchBySearchType() method of AppointmentService", e);
			}
 			return returnList;
 		}
 		
 		public String savingNewMembersForAppointment(final PashiAppNoCadreVO inputvo){
 			   String status = null;
 			try {
 				status = (String)transactionTemplate.execute(new TransactionCallback() {
 					public Object doInTransaction(TransactionStatus arg0) {
 						
 						
 			        	Long appointmtCadtId = 0l;
 					if(inputvo.getMembershipNo() != null && inputvo.getMembershipNo().trim().length() > 0){
 						appointmtCadtId = appointmentCandidateDAO.appointmntCandExist(inputvo.getMembershipNo());
 					}else if(inputvo.getVoterCardNo() != null && inputvo.getVoterCardNo().trim().length() > 0l){
 						appointmtCadtId = appointmentCandidateDAO.appointmntCandExistForVtrId(inputvo.getVoterCardNo());
 					}
 				
 					if(appointmtCadtId == null){
						List<Object[]> list = null;
						if(inputvo.getMembershipNo() != null && inputvo.getMembershipNo().trim().length() > 0l)
							//0.cadreId,1.voterId,2.voterCardNo,3.membershipNo,4.name,5.mobileNo,6.addressId,7.image
							 list = tdpCadreDAO.getCadreDetailsByMembershipNo(inputvo.getMembershipNo(),null);
						else if(inputvo.getVoterCardNo() != null && inputvo.getVoterCardNo().trim().length() > 0l)
							 list = tdpCadreDAO.getCadreDetailsByMembershipNo(null,inputvo.getVoterCardNo());
						
							if(list != null && !list.isEmpty()){
								Object[] obj = list.get(0);
								
								AppointmentCandidate appointmentCandidate = new AppointmentCandidate();
								
								if(inputvo.getName() != null && inputvo.getName().length()>0)
									appointmentCandidate.setName(inputvo.getName());
								else
									appointmentCandidate.setName(obj[4] != null ? obj[4].toString() :"");
								appointmentCandidate.setDesignationId(inputvo.getDesignationId());	//Cadre
								if(inputvo.getMobileNo() !=null && inputvo.getMobileNo().length() > 0)
									appointmentCandidate.setMobileNo(inputvo.getMobileNo());
								else
									appointmentCandidate.setMobileNo(obj[5] != null ? obj[5].toString():"");
								appointmentCandidate.setLocationScopeId(4l);	//constituency
								appointmentCandidate.setLocationValue(inputvo.getConstituencyId());
								appointmentCandidate.setAddressId(Long.valueOf(obj[6] != null ? obj[6].toString():"0"));
								appointmentCandidate.setVoterId(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
								if(inputvo.getVoterCardNo() != null && inputvo.getVoterCardNo().trim().length() > 0)
									appointmentCandidate.setVoterIdCardNo(inputvo.getVoterCardNo());
								else
									appointmentCandidate.setVoterIdCardNo(obj[2] != null ? obj[2].toString():"");
								
								if(inputvo.getMembershipNo() != null && inputvo.getMembershipNo().trim().length() > 0){
									if(inputvo.getMembershipNo() != null && inputvo.getMembershipNo().trim().length() > 0)
										appointmentCandidate.setMembershipId(inputvo.getMembershipNo());
									else
										appointmentCandidate.setMembershipId(obj[3] != null ? obj[3].toString():"");
									
									if(inputvo.getImageStr() != null && inputvo.getImageStr().trim().length() > 0){
										int number = 0;
										 while(String.valueOf(number).length()<11){
												number = new Random().nextInt();
											};
										
										 String filePath = "images/"+IConstants.APPOINTMENT_IMAGES;
										 CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
										 String dirPath= commonMethodsUtilService.createInnerFolders(IConstants.STATIC_CONTENT_FOLDER_URL+filePath);
										 
										 String fileName = inputvo.getMembershipNo().trim()+"_"+String.valueOf(Math.abs(number)).substring(0,5)+".jpg";
										 String finalPath =filePath+dirPath+"/"+fileName ;
										 
										//
										inputvo.setImageStr(inputvo.getImageStr().replace("_", "/"));
										inputvo.setImageStr(inputvo.getImageStr().replace("-", "+"));
										
										boolean status = imageAndStringConverter.convertBase64StringToImage(inputvo.getImageStr(),IConstants.STATIC_CONTENT_FOLDER_URL+finalPath);
										appointmentCandidate.setImageURL(finalPath);
									}
									else
										appointmentCandidate.setImageURL(obj[7] != null ? "images/cadre_images/"+obj[7].toString():"");
									
									appointmentCandidate.setTdpCadreId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
								}
								
								
								
								
								appointmentCandidate.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								appointmentCandidate.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								appointmentCandidate.setPeshiAppUserId(inputvo.getUserId());
								appointmentCandidate.setAppointmentCandidateTypeId(inputvo.getAppointmentCandidateTypeId());
								appointmentCandidate = appointmentCandidateDAO.save(appointmentCandidate);
								
								String stats = savingAppointCandRelaDetails(inputvo.getAppointmentId(),appointmentCandidate.getAppointmentCandidateId());
								
								return stats;	
							}
							else{
								Long voterId = voterDAO.getVtrId(inputvo.getVoterCardNo());
								AppointmentCandidate appointmentCandidate = new AppointmentCandidate();
								
								appointmentCandidate.setName(inputvo.getName());
								appointmentCandidate.setDesignationId(inputvo.getDesignationId());
								appointmentCandidate.setMobileNo(inputvo.getMobileNo());
								appointmentCandidate.setLocationScopeId(4l);	//constituency
								appointmentCandidate.setLocationValue(inputvo.getConstituencyId());
								appointmentCandidate.setVoterId(voterId);
								appointmentCandidate.setVoterIdCardNo(inputvo.getVoterCardNo());
								if(inputvo.getImageStr() != null && inputvo.getImageStr().trim().length() > 0){
									int number = 0;
									 while(String.valueOf(number).length()<11){
											number = new Random().nextInt();
										};
									
									 String filePath = "images/"+IConstants.APPOINTMENT_IMAGES;
									 CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
									 String dirPath= commonMethodsUtilService.createInnerFolders(IConstants.STATIC_CONTENT_FOLDER_URL+filePath);
									 String fileName = (inputvo.getVoterCardNo() != null?inputvo.getVoterCardNo().trim()+"_":"")+String.valueOf(Math.abs(number)).substring(0,5)+".jpg";
									 String finalPath =filePath+dirPath+"/"+fileName ;
									 
									//
									inputvo.setImageStr(inputvo.getImageStr().replace("_", "/"));
									inputvo.setImageStr(inputvo.getImageStr().replace("-", "+"));
									
									boolean status = imageAndStringConverter.convertBase64StringToImage(inputvo.getImageStr(),IConstants.STATIC_CONTENT_FOLDER_URL+finalPath);
									appointmentCandidate.setImageURL(finalPath);
								}
								else
									appointmentCandidate.setImageURL("images/voter_images/"+inputvo.getConstituencyId()+"/"+inputvo.getImageStr());
								
								appointmentCandidate.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								appointmentCandidate.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								appointmentCandidate.setPeshiAppUserId(inputvo.getUserId());
								appointmentCandidate.setAppointmentCandidateTypeId(inputvo.getAppointmentCandidateTypeId());
								appointmentCandidate = appointmentCandidateDAO.save(appointmentCandidate);
								
								String stats = savingAppointCandRelaDetails(inputvo.getAppointmentId(),appointmentCandidate.getAppointmentCandidateId());
								
								return stats;
							}
 				}else{
 					List<Long> apptCandRelIds = appointmentCandidateRelationDAO.checkAppointmentCandidateExistsWithAppointment(inputvo.getAppointmentId(), appointmtCadtId);
 					if(apptCandRelIds != null && !apptCandRelIds.isEmpty()){
 						return "success";
 					}
 					else{
 						String result = savingAppointCandRelaDetails(inputvo.getAppointmentId(),appointmtCadtId);
 	 					return result;
 					}
 				}
 					//return "failure";
 			   }
 					
 				});
 			        
			} catch (Exception e) {
				LOG.error("Exception raised at savingNewMembersForAppointment() method of AppointmentService", e);
				status = "failure";
			}
 			return status;
 		}
 	public String savingAppointCandRelaDetails(Long appointmntId,Long appntCandidateId){
 			String status = null;
 			try{
 				AppointmentCandidateRelation  appointmentCandidateRelation = new AppointmentCandidateRelation();
 					appointmentCandidateRelation.setAppointmentId(appointmntId);
 					appointmentCandidateRelation.setAppointmentCandidateId(appntCandidateId);
 					appointmentCandidateRelationDAO.save(appointmentCandidateRelation);
 					status="success";
 			}catch(Exception e){
 				LOG.error("Exception raised at savingAppointCandRelaDetails() method of AppointmentService", e);
 				status="failure";
 			}
 			return status;
 		}
 	

 	public AppointmentCountDetailsVO getAppointmentCandidateCountDeatils(Long userId){
 		 AppointmentCountDetailsVO  returnVo =null; 
 	  try{
 		 Date insertedDate = dateUtilService.getCurrentDateAndTime();
 		 Long todayCount = appointmentCandidateDAO.todayAppointmentCandidateCount(userId,insertedDate,insertedDate);
 		 Long todayUniqueCount = appointmentCandidateDAO.todayAppointmentCandUniqueCount(userId, insertedDate, insertedDate);
 		 Calendar date = Calendar.getInstance();
         date.set(Calendar.DAY_OF_MONTH, 1);
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
           Date fromDate =null;
          String startDateStr = df.format(date.getTime());
          if(startDateStr !=null ){
				fromDate = df.parse(startDateStr);
			}
          Long totalCount =appointmentCandidateDAO.todayAppointmentCandidateCount(userId,insertedDate,fromDate);
          Long uniqueCount = appointmentCandidateDAO.getAppointmentCandUniqueCount(userId, insertedDate, fromDate);
          returnVo = new AppointmentCountDetailsVO();
           if(todayCount != null && todayCount>0l){
        	   returnVo.setTodayCount(todayCount); 
           }
           if(totalCount != null && totalCount>0l){
        	   returnVo.setMonthCount(totalCount);
           }
           if(uniqueCount != null && uniqueCount.longValue() > 0l){
        	   returnVo.setUniqueCount(uniqueCount);
           }
           if(todayUniqueCount != null && todayUniqueCount.longValue() > 0l){
        	   returnVo.setTodayUniqueCount(todayUniqueCount);
           }
     
 		}catch(Exception e){
 			LOG.error("Exception raised at getAppointmentCandidateCountDeatils() method of AppointmentService", e);
 		}
 		
 		return returnVo;
 	}
 	
 	public List<AppointmentCountDetailsVO> getAppointmentCandidateDetails(String fromDateStr,String toDateStr,Long userId){
 		List<AppointmentCountDetailsVO> returnVo=null;
 		try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			 Date fromDate =null;
			 Date toDate =null;
			if(fromDateStr !=null && toDateStr !=null){
				fromDate = format.parse(fromDateStr);
				toDate = format.parse(toDateStr);
			}
			List<Object[]> candidateDetails =appointmentCandidateDAO.appointmentCandidateDetails(fromDate,toDate,userId);
			if(candidateDetails != null && candidateDetails.size()>0){
				CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
				returnVo =new ArrayList<AppointmentCountDetailsVO>();
				for(Object[] param : candidateDetails){
					AppointmentCountDetailsVO detailsVO = new AppointmentCountDetailsVO();
					 detailsVO.setAppointmentId(commonMethodsUtilService.getLongValueForObject(param[0]));
					 detailsVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					 detailsVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[2]));
					 detailsVO.setImage("httP://www.mytdp.com/"+commonMethodsUtilService.getStringValueForObject(param[3]));
					 detailsVO.setAppointmentTime(commonMethodsUtilService.getStringValueForObject(param[4]));
					 
					 returnVo.add(detailsVO);
				}
			}
 			
 		}catch(Exception e){
 			LOG.error("Exception raised at getAppointmentCandidateDetails() method of AppointmentService", e);
 		}
		return returnVo;
 		
 	}
 	
 	public ResultStatus checkMemberWalkInForToday(final String memberShipId,final String date , final String uniqueId,final Long loginUserId,final Long tabPrimaryKey,final String isCheckedStatus){
		 ResultStatus status = new ResultStatus();
		try {
			 //saveApptDetailsInFatalLog(appointmentVO);
			List<TdpCadre> isRemovedOrNot = tdpCadreDAO.isMembershipIdVAlidOrNotValid(memberShipId);
			if(isRemovedOrNot != null && isRemovedOrNot.size() > 0){
				if(isCheckedStatus.equalsIgnoreCase("online")){
					status.setMessage(" This membership id is deleted.");
					status.setTabPrimaryKey(tabPrimaryKey);
				}
			}else{
				List<TdpCadre> isCadre = tdpCadreDAO.isMebershipIdValid(memberShipId);
				if(isCadre != null && isCadre.size() > 0){
					status = (ResultStatus)transactionTemplate.execute(new TransactionCallback() {
						public Object doInTransaction(TransactionStatus arg0) {
							 ResultStatus rs = new ResultStatus();
							 
				        	List<String> membershipNoList = new ArrayList<String>(0);
				        	/*SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				        	Date todayDate = null;
				        	try {
								todayDate = sdf2.parse(date);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}*/
				        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				        	
				        	Date fromDate =null;
			        		if(date !=null && date !=null){
			    				try {
									fromDate = sdf1.parse(date);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			    			}
			        		membershipNoList.add(memberShipId);
			        		
			        		/*if(membershipNoList!=null && membershipNoList.size() >0 ){
			        			
			        			List<Appointment> list = appointmentCandidateRelationDAO.checkIsAppointmentForToday(membershipNoList,apptCreationStatusList,2l,todayDate);
					        	if(list != null && list.size() >0){
					        		appointment = list.get(0);
					        		appointment.setUpdatedTime(fromDate);
					        		appointment = appointmentDAO.save(appointment);
					        		
					        	}else{*/
			        					Appointment appointment = new Appointment();
					        		 	appointment.setAppointmentUserId(2l);
							        	
							        	appointment.setAppointmentPriorityId(1l);
							        			 
							        	appointment.setReason("Walkin");
							        	
							        	appointment.setAppointmentStatusId(IConstants.APPOINTMENT_STATUS_WAITING);
							        	
							        	appointment.setAppointmentPreferableTimeId(4l);
							        	
							        	
							        	appointment.setCreatedBy(loginUserId);
							        	appointment.setUpdatedBy(loginUserId);
							        	appointment.setInsertedTime(fromDate);
							        	appointment.setUpdatedTime(fromDate);
							        	appointment.setIsDeleted("N");
							        	appointment.setIsLabelled("N");
							        	appointment = appointmentDAO.save(appointment);
					        	//}
			        		//}
			        		//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			        		
				        	
				        	
				        	
				        	
				        	if( appointment != null && appointment.getAppointmentId() != null && appointment.getAppointmentId()>0l){
				        		String temp = "NL";
				        		appointmentDAO.updateUniquesIdForAppointment(temp+"_"+appointment.getAppointmentId(),appointment.getAppointmentId());
				        	}
				        	
				        	//dates
				        	List<Date> datesList = new ArrayList<Date>(0);
				        	
				        	appointment.setAppointmentPreferableTimeId(4l);
				        		datesList = dateUtilService.getDatesOfCurrentWeek();
				        	
				        	
				        	/*if(datesList != null && datesList.size() > 0){
				        		Long order = 1l;
				        		for (Date date : datesList) {*/
				        			AppointmentPreferableDate appointmentPreferableDate = new AppointmentPreferableDate();
				        			
				        			appointmentPreferableDate.setAppointmentId(appointment.getAppointmentId());
				        			appointmentPreferableDate.setAppointmentDate(fromDate);
				        			appointmentPreferableDate.setOrderNo(1l);
				        			
				        			appointmentPreferableDate = appointmentPreferableDateDAO.save(appointmentPreferableDate);
				        			/*order++;
								}
				        	}*/
				        	
				        	//gettdpcadre Ids for membership nums
				        		
				        		Map<String,Object[]> cadreIdsMap = new HashMap<String, Object[]>(0);
				        		if(membershipNoList != null && membershipNoList.size() > 0){
				        			List<Object[]> cadreIdsObjList = tdpCadreDAO.getTdpCadreDetailsByMemberShipId(membershipNoList);
				        			if(cadreIdsObjList != null && cadreIdsObjList.size() > 0){
				        				for (Object[] objects : cadreIdsObjList) {
				        					cadreIdsMap.put(objects[1].toString(),objects);
										}
				        			}
				        		}
				        		
				        		
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
					        					appCandi = appointmentCandidateDetailsSaving(appCandi,cadreIdsMap,loginUserId,memberShipId,fromDate);
					        					AppointmentCandidateRelation acr = new AppointmentCandidateRelation();
							        			acr.setAppointmentId(appointment.getAppointmentId());
							        			acr.setAppointmentCandidateId(appCandi.getAppointmentCandidateId());
							        			appointmentCandidateRelationDAO.save(acr);
						        			
									            saveAppointmentTrackingDetails(appointment.getAppointmentId(),IConstants.APPOINTMENT_ACTION_STATUS_CHANGE,null,
									            		IConstants.APPOINTMENT_STATUS_WAITING,loginUserId,null);
									        	rs.setExceptionMsg("success");
												rs.setResultCode(0);
												rs.setTabPrimaryKey(tabPrimaryKey);
					        				}
					        				
						        			
							return rs;
						}
				    });
				}else{
					status.setMessage("Invalid.");
					status.setTabPrimaryKey(tabPrimaryKey);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at saveAppointment", e);
			status.setExceptionClass(e.getMessage());
			status.setExceptionMsg("failure");
			status.setResultCode(1);
			status.setTabPrimaryKey(tabPrimaryKey);
		}
		return status;
	}
 	
 	public AppointmentCandidate appointmentCandidateDetailsSaving(AppointmentCandidate appCandi,Map<String,Object[]> cadreIdsMap,Long loggerUserId,String memberShipId,Date fromDate){
		
		try{
			
			Object[] obj = cadreIdsMap.get(memberShipId);
			
			CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
			appCandi.setName(commonMethodsUtilService.getStringValueForObject(obj[2]));
			Long desigId = 0l;
			List<Object[]> list = candidateDesignationDAO.getAppCandidateDesigListByType(3l);
			if(list != null && list.size() >0){
				Object[] param = list.get(0);
				 desigId = (Long)param[0];
			}
			
			appCandi.setDesignationId(desigId.longValue());
			
			appCandi.setMobileNo(commonMethodsUtilService.getStringValueForObject(obj[4]));
			 
			UserAddress userAddress = userAddressDAO.get(commonMethodsUtilService.getLongValueForObject(obj[5]));
			if(userAddress !=null)
			{
				appCandi.setAddressId(commonMethodsUtilService.getLongValueForObject(obj[5]));
			}
			
			if(obj[6] !=null && obj[7] != null){
				appCandi.setVoterIdCardNo(commonMethodsUtilService.getStringValueForObject(obj[7]));
				appCandi.setVoterId(commonMethodsUtilService.getLongValueForObject(obj[6]));
			}else if(obj[8] !=null && obj[9] != null){
				appCandi.setVoterIdCardNo(commonMethodsUtilService.getStringValueForObject(obj[9]));
				appCandi.setVoterId(commonMethodsUtilService.getLongValueForObject(obj[8]));
			}
			appCandi.setMembershipId(commonMethodsUtilService.getStringValueForObject(obj[1]));
			appCandi.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(obj[0]));
			appCandi.setImageURL("images/cadre_images/"+commonMethodsUtilService.getStringValueForObject(obj[10]));
			appCandi.setAppointmentCandidateTypeId(3l);
			
			appCandi.setCreatedBy(loggerUserId);
			appCandi.setUpdatedBy(loggerUserId);
			appCandi.setInsertedTime(fromDate);
			appCandi.setUpdatedTime(fromDate);
			
			appCandi = appointmentCandidateDAO.save(appCandi);
			
		}catch (Exception e) {
			LOG.error("Error occured  in appointmentCandidateDetailsSaving() method of AppointmentService",e);
		}
		return appCandi;
	}
 }


