package com.itgrids.partyanalyst.service.impl;


import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAppointmentCandidateDAO;
import com.itgrids.partyanalyst.dao.IAppointmentCandidateDesignationDAO;
import com.itgrids.partyanalyst.dao.IAppointmentCandidateRelationDAO;
import com.itgrids.partyanalyst.dao.IAppointmentDAO;
import com.itgrids.partyanalyst.dao.IAppointmentLabelDAO;
import com.itgrids.partyanalyst.dao.IAppointmentLabelStatusDAO;
import com.itgrids.partyanalyst.dao.IAppointmentManageUserDAO;
import com.itgrids.partyanalyst.dao.IAppointmentPreferableDateDAO;
import com.itgrids.partyanalyst.dao.IAppointmentPriorityDAO;
import com.itgrids.partyanalyst.dao.IAppointmentStatusDAO;
import com.itgrids.partyanalyst.dao.IAppointmentTimeSlotDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ILabelAppointmentDAO;
import com.itgrids.partyanalyst.dao.ILabelAppointmentHistoryDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.hibernate.AppointmentDAO;
import com.itgrids.partyanalyst.dao.hibernate.ConstituencyDAO;
import com.itgrids.partyanalyst.dao.hibernate.DistrictDAO;
import com.itgrids.partyanalyst.dao.hibernate.TdpCadreDAO;
import com.itgrids.partyanalyst.dao.hibernate.TehsilDAO;
import com.itgrids.partyanalyst.dao.hibernate.UserAddressDAO;
import com.itgrids.partyanalyst.dao.hibernate.VoterDAO;
import com.itgrids.partyanalyst.dto.AppointmentBasicInfoVO;
import com.itgrids.partyanalyst.dto.AppointmentCandidateVO;
import com.itgrids.partyanalyst.dto.AppointmentDetailsVO;
import com.itgrids.partyanalyst.dto.AppointmentInputVO;
import com.itgrids.partyanalyst.dto.AppointmentScheduleVO;
import com.itgrids.partyanalyst.dto.AppointmentSlotsVO;
import com.itgrids.partyanalyst.dto.AppointmentStatusVO;
import com.itgrids.partyanalyst.dto.AppointmentUpdateStatusVO;
import com.itgrids.partyanalyst.dto.AppointmentVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LabelStatusVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Appointment;
import com.itgrids.partyanalyst.model.AppointmentCandidate;
import com.itgrids.partyanalyst.model.AppointmentCandidateRelation;
import com.itgrids.partyanalyst.model.AppointmentLabel;
import com.itgrids.partyanalyst.model.AppointmentPreferableDate;
import com.itgrids.partyanalyst.model.AppointmentStatus;
import com.itgrids.partyanalyst.model.AppointmentTimeSlot;
import com.itgrids.partyanalyst.model.LabelAppointment;
import com.itgrids.partyanalyst.model.LabelAppointmentHistory;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IAppointmentService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.utils.DateUtilService;


public class AppointmentService implements IAppointmentService{
	
	private static Logger LOG = Logger.getLogger(AppointmentService.class);
	private TransactionTemplate transactionTemplate;
	private DateUtilService dateUtilService = new DateUtilService();
	private IAppointmentDAO appointmentDAO;
	private IAppointmentStatusDAO appointmentStatusDAO;
	private IAppointmentCandidateDesignationDAO candidateDesignationDAO;
	private IAppointmentPriorityDAO appointmentPriorityDAO;
	private IAppointmentLabelStatusDAO appointmentLabelStatusDAO;
	private DistrictDAO districtDAO;
	private ConstituencyDAO constituencyDAO;
	private TehsilDAO tehsilDAO;
	private UserAddressDAO userAddressDAO;
	private VoterDAO voterDAO;
	private TdpCadreDAO tdpCadreDAO;
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
	public TehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}
	public void setTehsilDAO(TehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public ConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(ConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public DistrictDAO getDistrictDAO() {
		return districtDAO;
	}
	public void setDistrictDAO(DistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public UserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}
	public void setUserAddressDAO(UserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}
	public VoterDAO getVoterDAO() {
		return voterDAO;
	}
	public void setVoterDAO(VoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}
	public TdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}
	public void setTdpCadreDAO(TdpCadreDAO tdpCadreDAO) {
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
	
	
	public ResultStatus saveAppointment(final AppointmentVO appointmentVO,final Long loggerUserId){
		ResultStatus rs = new ResultStatus();
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
		        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
		        	
		        	Appointment appointment = new Appointment();
		        	appointment.setAppointmentUserId(appointmentVO.getAppointmentUserId());
		        	appointment.setAppointmentPriorityId(appointmentVO.getAppointmentPriorityId());
		        	appointment.setReason(appointmentVO.getReason());
		        	appointment.setAppointmentStatusId(1l);
		        	
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
		        			voterCardNumList.add(basicInfo.getVoterCardNo());
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
		        			membershipNoList.add(basicInfo.getMembershipNum());
		        		}
		        		if(membershipNoList != null && membershipNoList.size() > 0){
		        			List<Object[]> cadreIdsObjList = tdpCadreDAO.getTdpCadreIdForMemberShipNums(membershipNoList);
		        			if(cadreIdsObjList != null && cadreIdsObjList.size() > 0){
		        				for (Object[] objects : cadreIdsObjList) {
		        					cadreIdsMap.put(objects[0].toString(),Long.valueOf(objects[1].toString()));
								}
		        			}
		        		}
		        		
		        		
		        		for (AppointmentBasicInfoVO basicInfo : appointmentVO.getBasicInfoList()) {
		        			if(basicInfo.getAppointCandidateId() == null){
		        				AppointmentCandidate appCandi = new AppointmentCandidate();
			        			//appCandi.setAppointmentId(appointment.getAppointmentId());
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
			        				//Long id = Long.valueOf(basicInfo.getVillageId().toString().substring(1));
			        				appCandi.setLocationValue(basicInfo.getVillageId());
			        			}
			        			
			        			//user addres saving logic
			        			UserAddress userAddress = new UserAddress();
			        			userAddress.setState(stateDAO.get(1l));
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
			        			appCandi = appointmentCandidateDAO.save(appCandi);
			        			
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
			List<Object[]>  appDesigLst = candidateDesignationDAO.getAppCandidateDesigList();
			appCndDesigList = setDataToVO(appDesigLst);
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
	public ResultStatus createAppointmentLeble(String labelName,Long insertedBy,String fromDateStr,String toDateStr){
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
			appointmentLabelDAO.save(appointmentLabel);
			resultStatus.setResultCode(1);
			resultStatus.setMessage("Appointment Label created...");
		}catch(Exception e){
			LOG.error("Exception raised at createAppointmentLeble() method of AppointmentService", e);
		}
		return resultStatus;
	}
	@Override
	public List<LabelStatusVO> getLabelDtslByDate(String slctdDate,Long appntmntUsrId) {
		
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
			
			List<Object[]> allLablesObjList = appointmentLabelDAO.getAllLabels(date,appntmntUsrId);
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
			
			
			
			List<Object[]> objList = labelAppointmentDAO.getLableDetailsWithStatusWiseCounts(date,appntmntUsrId);
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
	public  List<AppointmentCandidateVO> searchApptRequestedMembers(String searchType,String searchValue){
		 List<AppointmentCandidateVO>  finalList = null;
		 
		 try {
			      List<Object[]> membersList = null;
			 
			      membersList = appointmentCandidateDAO.searchAppointmentRequestedMember(searchType,searchValue);
			      
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
			    		  finalList.add(vo);
			    	  }
			      }
			      else{
			    	  
			    	  membersList = tdpCadreDAO.searchMemberByCriteria(searchType,searchValue);
			    	  
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
				    		  finalList.add(vo);
			    		  }
			    	  }
			    	  
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
			 	
		} catch (Exception e) {
			LOG.error("Exception raised at searchApptRequestedMembers() method of AppointmentService", e);
		}
		 return finalList;
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
     public List<AppointmentBasicInfoVO> getAllAppointmentDetails(int startIndex,int maxIndex){
    	 List<AppointmentBasicInfoVO> appointmentBasicInfoVOs = new ArrayList<AppointmentBasicInfoVO>(0);
    	 AppointmentBasicInfoVO appointmentBasicInfoVO = null;
    	 try{
 			LOG.info("Entered into getAllAppointmentDetails() method of AppointmentService");
 			List<Object[]> list = appointmentCandidateRelationDAO.getAllAppointmentDetails(startIndex,maxIndex);
 			if(list!=null && list.size()>0){
 				for(Object[] obj:list){
 					appointmentBasicInfoVO = new AppointmentBasicInfoVO();
 					appointmentBasicInfoVO.setName(obj[0]!=null?obj[0].toString():"");
 					appointmentBasicInfoVO.setMembershipNum(obj[1]!=null?obj[1].toString():"");
 					appointmentBasicInfoVO.setDate(obj[2]!=null?obj[2].toString():"");
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
     public List<AppointmentDetailsVO> getAppointmentsBySearchCriteria(Long designationId,Long priorityId,Long statusId,Long districtId,Long constituencyid,Long appointmentlabelId,String fromDateStr,String toDateStr){
		   List<AppointmentDetailsVO> finalList = new ArrayList<AppointmentDetailsVO>(0);
		   SimpleDateFormat sdf =  new SimpleDateFormat("MM/dd/yyyy");
		   SimpleDateFormat sdf1 = new SimpleDateFormat("dd MMM yyyy h:mm a");
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
			
			List<Object[]>   list = appointmentCandidateRelationDAO.getAppointmentsBySearchCriteria(designationId,priorityId,statusId,districtId,constituencyid,fromDate,toDate);
			if(list !=null && list.size()>0){
				
				appointmentsMap = new LinkedHashMap<Long, AppointmentDetailsVO>();
				
				for(Object[]  obj: list){
					
					if(obj[5]!=null && (Long)obj[5]>0){
						
						Long apptLabelId = (Long)obj[5];
						if(apptLabelId == appointmentlabelId){
							AppointmentDetailsVO appointment =new AppointmentDetailsVO();
							appointment.setAppointmentId(obj[0]!=null?(Long)obj[0]:0l);
							appointment.setSubject(obj[1]!=null?obj[1].toString():"");
							appointment.setPriority(obj[2]!=null?obj[2].toString():"");
							appointment.setStatus(obj[3]!=null?obj[3].toString():"");
							appointment.setDateString(obj[4]!=null?obj[4].toString():"");
							appointmentsMap.put(appointment.getAppointmentId(),appointment);
							
							//appointmentIds
							appointmentIds.add(appointment.getAppointmentId());
						}
					}else{
						AppointmentDetailsVO appointment =new AppointmentDetailsVO();
						appointment.setAppointmentId(obj[0]!=null?(Long)obj[0]:0l);
						appointment.setSubject(obj[1]!=null?obj[1].toString():"");
						appointment.setPriority(obj[2]!=null?obj[2].toString():"");
						appointment.setStatus(obj[3]!=null?obj[3].toString():"");
						appointment.setDateString(obj[4]!=null?obj[4].toString():"");
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
				List<Object[]> candidPreviousDetails =appointmentCandidateRelationDAO.getCandidatePreviousApptDetails1(candidates);
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
				List<Object[]> lastVisitList = appointmentCandidateRelationDAO.getLastVisitsByCandidates(candidates);
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
			        				updatingAppointmentIds = new ArrayList<Long>(labeledAppointmentIds);
			        				deletedAppointmentIds  = new ArrayList<Long>(labeledAppointmentIds);
			        				
			        				updatingAppointmentIds.retainAll(appointmentIds);
			        				deletedAppointmentIds.removeAll(appointmentIds);
			        				savingAppointmentIds.removeAll(labeledAppointmentIds);
			        			}
			        			
			        			List<Long> updateAndDeletedAppointmentIds = new ArrayList<Long>();
			        			
			        			if(updatingAppointmentIds!=null && updatingAppointmentIds.size()>0){
			        				int updatedCount = labelAppointmentDAO.updateLabeledAppointments(apptLabelId,updatingAppointmentIds,loggerUserId,dateUtilService.getCurrentDateAndTime());
			        				
			        				updateAndDeletedAppointmentIds.addAll(updatingAppointmentIds);
			        			}
			        			
			        			if(deletedAppointmentIds!=null && deletedAppointmentIds.size()>0){
			        				int deletedCount = labelAppointmentDAO.deleteLabeledAppointments(apptLabelId,deletedAppointmentIds);
			        				
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
			        			}
			        			
			        			
			        			
			        		}
			        	}
			        	rs.setResultCode(1);
			        	rs.setMessage("success");
		         }
		    });
			
		} catch (Exception e) {
			LOG.error("Exception raised at saveAppointment", e);
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
			List<Object[]> assignedAppointmentsObjList = labelAppointmentDAO.getAppointmentsOfALableForUpdate(lableId);
			
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
	
	
	public LabelStatusVO getStatusWiseCountsOfAppointments(){
		LabelStatusVO finalVo = new LabelStatusVO();
		
		try{
			
			DateUtilService dt = new DateUtilService();
			Date curentDateTime = dt.getCurrentDateAndTime();
			
			List<Object[]> totalTodayObjList = new ArrayList<Object[]>();
			List<Object[]> totalObjList = new ArrayList<Object[]>();
			
			//toDay Block
				//Fixed Status
						//0.statusId,1.status,2.count
						List<Object[]> inProgreeList = labelAppointmentDAO.getLabelAppointmentsForFixedSatus(curentDateTime,"Inprogress","ToDay");
						List<Object[]> upcomingList  = labelAppointmentDAO.getLabelAppointmentsForFixedSatus(curentDateTime,"Upcoming","ToDay");
						List<Object[]> completedList  = labelAppointmentDAO.getLabelAppointmentsForFixedSatus(curentDateTime,"Completed","ToDay");
						
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
						
						List<Object[]> statusObjList = labelAppointmentDAO.getLabelAppointmentsStatus(curentDateTime,"ToDay");
						
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
				
			//OverAll Scenario
						List<Object[]> inProgreeOverAllList = labelAppointmentDAO.getLabelAppointmentsForFixedSatus(curentDateTime,"Inprogress","overall");
						List<Object[]> upcomingOverAllList  = labelAppointmentDAO.getLabelAppointmentsForFixedSatus(curentDateTime,"Upcoming","overall");
						List<Object[]> completedOverAllList  = labelAppointmentDAO.getLabelAppointmentsForFixedSatus(curentDateTime,"Completed","overall");
						
						
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
						
						List<Object[]> statusObjOverAllList = labelAppointmentDAO.getLabelAppointmentsStatus(curentDateTime,"overall");
						
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
						}
						
			
		}catch(Exception e){
			LOG.error("Exception raised at getStatusWiseCountsOfAppointments", e);
		}
		return finalVo;
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
			if(list!=null && list.size()>0){
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
		}catch(Exception e){
			LOG.error("Exception raised at getTimeSlotsDetails() method of AppointmentService", e);
		}
		return appointmentSlotsVO;
	}
	public List<IdNameVO> getAppointmentLabels(){
		List<IdNameVO> labelList = new ArrayList<IdNameVO>();
		try{
			List<Object[]> list=appointmentLabelDAO.getAppointmentLabels();
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
			Date date2 = new Date();
			if (toDate1.compareTo(date2) < 0)
			{
					scheduleType = "Completed";
			       // System.out.println("date1 is before date2");
			}
			else if (fromDate1.compareTo(date2) > 0)
			{
				scheduleType = "UpCome";
			   // System.out.println("fromDate1 is after date2");
			}
			else
			{
				scheduleType = "InProgress";
			   // System.out.println("date1 is equal to date2");
			}
		 } catch (ParseException e) {
				
				e.printStackTrace();
			}
		return scheduleType;
	}
	
	//view apointments for label
	 public List<AppointmentDetailsVO> viewAppointmentsOfALable(Long labelId){
		   List<AppointmentDetailsVO> finalList = new ArrayList<AppointmentDetailsVO>(0);
		   SimpleDateFormat sdf1 = new SimpleDateFormat("dd MMM yyyy h:mm a");
		try {
			
			Set<Long> appointmentIds = new HashSet<Long>(0);
			Set<Long> candidateIds = new HashSet<Long>(0);
			
			List<Object[]> statList = appointmentStatusDAO.getAppointmentStatusList();
			
			Map<Long,AppointmentDetailsVO> appointmentsMap = null;
			
			//List<Object[]>   list = appointmentCandidateRelationDAO.getAppointmentsBySearchCriteria(designationId,priorityId,statusId,districtId,constituencyid);
			List<Object[]> list = labelAppointmentDAO.getAppointmentsOfALableForUpdate(labelId);
			if(list !=null && list.size()>0){
				
				appointmentsMap = new LinkedHashMap<Long, AppointmentDetailsVO>();
				
				for(Object[]  obj: list){
					AppointmentDetailsVO appointment =new AppointmentDetailsVO();
					appointment.setAppointmentId(obj[0]!=null?(Long)obj[0]:0l);
					appointment.setSubject(obj[3]!=null?obj[3].toString():"");
					appointment.setPriority(obj[2]!=null?obj[2].toString():"");
					appointment.setStatus(obj[5]!=null?obj[5].toString():"");
					appointment.setDateString(obj[8]!=null?obj[8].toString():"");
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
				List<Object[]> candidPreviousDetails =appointmentCandidateRelationDAO.getCandidatePreviousApptDetails1(candidates);
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
				List<Object[]> lastVisitList = appointmentCandidateRelationDAO.getLastVisitsByCandidates(candidates);
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
		 
		 	public ResultStatus updateMemberAppointmentsStatus(Long memberAppntId,Long updateAppntStatusId) {
			   
				ResultStatus status=new ResultStatus();
				try{
					
					 Integer updateCount=appointmentLabelDAO.updateMemberAppointmentsStatus(memberAppntId,updateAppntStatusId);	
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
	
	@SuppressWarnings("unused")
	public ResultStatus setTimeSlotForAppointment(Long appointmentId,String dateStr,String fromTime,String toTime,Long userId){
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
		  
			
			AppointmentTimeSlot timeSlot = new AppointmentTimeSlot();
			timeSlot.setAppointmentId(appointmentId);
			timeSlot.setDate(date);
			timeSlot.setFromDate(appointmentFromTime);
			timeSlot.setToDate(appointmentToTime);
			timeSlot.setInsertedBy(userId);
			timeSlot.setUpdatedBy(userId);
			timeSlot.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			timeSlot.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			timeSlot.setIsDeleted("N");
			
			appointmentTimeSlotDAO.save(timeSlot);
			
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
}
