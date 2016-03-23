package com.itgrids.partyanalyst.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAppointmentCandidateDAO;
import com.itgrids.partyanalyst.dao.IAppointmentCandidateDesignationDAO;
import com.itgrids.partyanalyst.dao.IAppointmentCandidateRelationDAO;
import com.itgrids.partyanalyst.dao.IAppointmentLabelDAO;
import com.itgrids.partyanalyst.dao.IAppointmentLabelStatusDAO;
import com.itgrids.partyanalyst.dao.IAppointmentManageUserDAO;
import com.itgrids.partyanalyst.dao.IAppointmentPreferableDateDAO;
import com.itgrids.partyanalyst.dao.IAppointmentPriorityDAO;
import com.itgrids.partyanalyst.dao.IAppointmentStatusDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ILabelAppointmentDAO;
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
import com.itgrids.partyanalyst.dto.AppointmentStatusVO;
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
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IAppointmentService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class AppointmentService implements IAppointmentService{
	
	private static Logger LOG = Logger.getLogger(AppointmentService.class);
	private TransactionTemplate transactionTemplate;
	private DateUtilService dateUtilService = new DateUtilService();
	private AppointmentDAO appointmentDAO;
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
	private IAppointmentPreferableDateDAO appointmentPreferableDateDAO;
	private IBoothPublicationVoterDAO     boothPublicationVoterDAO;
	private RtcUnionService               rtcUnionService;
	private IAppointmentCandidateRelationDAO appointmentCandidateRelationDAO;
	private ILabelAppointmentDAO labelAppointmentDAO;
	
	
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
	public AppointmentDAO getAppointmentDAO() {
		return appointmentDAO;
	}
	public void setAppointmentDAO(AppointmentDAO appointmentDAO) {
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
	
	
	
	public ResultStatus saveAppointment(final AppointmentVO appointmentVO,final Long loggerUserId){
		ResultStatus rs = new ResultStatus();
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
		        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
		        	
		        	Appointment appointment = new Appointment();
		        	appointment.setAppointmentUserId(appointmentVO.getAppointmentUserId());
		        	appointment.setAppointmentPriorityId(appointmentVO.getAppointmentPrioprityId());
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
		        			if(basicInfo.getDistrictId() == 0l)basicInfo.setDistrictId(null);
		        			userAddress.setDistrict(districtDAO.get(basicInfo.getDistrictId()));
		        			if(basicInfo.getConstituencyId() == 0l)basicInfo.setConstituencyId(null);
		        			userAddress.setConstituency(constituencyDAO.get(basicInfo.getConstituencyId()));
		        			
		        			if(basicInfo.getTehsilId() != null && basicInfo.getTehsilId() > 0l && basicInfo.getTehsilId().toString().substring(0, 1).equalsIgnoreCase("4")){
		        				userAddress.setTehsil(tehsilDAO.get(Long.valueOf(basicInfo.getTehsilId().toString().substring(1))));
		        				if(basicInfo.getVillageId() != null && basicInfo.getVillageId() > 0l)
		        					userAddress.setPanchayat(panchayatDAO.get(basicInfo.getVillageId()));
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
						}
		        	}
		        }
		    });
			
		} catch (Exception e) {
			LOG.error("Exception raised at saveAppointment", e);
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
	public ResultStatus createAppointmentLeble(String labelName,Long insertedBy,String date){
		DateUtilService dateUtilService = new DateUtilService();
		ResultStatus resultStatus = new ResultStatus();
		try{
			LOG.info("Entered into createAppointmentLeble() method of AppointmentService");
			Date insertedDate = dateUtilService.getCurrentDateAndTime();
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date dt = format.parse(date);
			AppointmentLabel appointmentLabel = new AppointmentLabel();
			appointmentLabel.setLabelName(labelName);
			appointmentLabel.setDate(dt);
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
			
			/*List<Object[]> labelDtlsList=appointmentLabelDAO.getLabelDtslByDate(date,appntmntUsrId);
			if(labelDtlsList!=null && !labelDtlsList.isEmpty()){
				for(Object[] param:labelDtlsList){
					AppointmentBasicInfoVO labelDtslVO=new AppointmentBasicInfoVO();
					labelDtslVO.setAppointmentLabelId((Long)param[0]);
					labelDtslVO.setName(param[1]!=null ? param[1].toString():"");
				    labelDtslVO.setDate(param[2]!=null ?param[2].toString().split(" ")[0]:"");
					labelDtlsFnlList.add(labelDtslVO);
				}
			}*/
			
			List<AppointmentStatus> asList = appointmentStatusDAO.getAll();
			
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
			
			if(finalMap != null && finalMap.size() > 0){
				for (Entry<Long, LabelStatusVO> entry : finalMap.entrySet()) {
					finalVoList.add(entry.getValue());
				}
			}
			
			if(finalVoList != null && finalVoList.size() > 0){
				finalVoList.get(0).setStaticStatusList(asList);
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getLabelDtslByDate() method of AppointmentService", e);
		}
		return finalVoList;
	}
	
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
	
     // Appointments search criteria.
     public List<AppointmentDetailsVO> getAppointmentsBySearchCriteria(Long designationId,Long priorityId,Long statusId,Long districtId,Long constituencyid){
		   List<AppointmentDetailsVO> finalList = new ArrayList<AppointmentDetailsVO>(0);
		try {
			
			Set<Long> appointmentIds = new HashSet<Long>(0);
			Set<Long> candidateIds = new HashSet<Long>(0);
			
			List<Object[]> statList = appointmentStatusDAO.getAppointmentStatusList();
			
			Map<Long,AppointmentDetailsVO> appointmentsMap = null;
			
			List<Object[]>   list = appointmentCandidateRelationDAO.getAppointmentsBySearchCriteria(designationId,priorityId,statusId,districtId,constituencyid);
			if(list !=null && list.size()>0){
				
				appointmentsMap = new LinkedHashMap<Long, AppointmentDetailsVO>();
				
				for(Object[]  obj: list){
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
			e.printStackTrace();
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
	
}
