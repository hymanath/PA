package com.itgrids.partyanalyst.service.impl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAppointmentCandidateDAO;
import com.itgrids.partyanalyst.dao.IAppointmentCandidateDesignationDAO;
import com.itgrids.partyanalyst.dao.IAppointmentLableDAO;
import com.itgrids.partyanalyst.dao.IAppointmentLableStatusDAO;
import com.itgrids.partyanalyst.dao.IAppointmentManageUserDAO;
import com.itgrids.partyanalyst.dao.IAppointmentPriorityDAO;
import com.itgrids.partyanalyst.dao.IAppointmentStatusDAO;
import com.itgrids.partyanalyst.dao.hibernate.AppointmentDAO;
import com.itgrids.partyanalyst.dao.hibernate.ConstituencyDAO;
import com.itgrids.partyanalyst.dao.hibernate.DistrictDAO;
import com.itgrids.partyanalyst.dao.hibernate.TdpCadreDAO;
import com.itgrids.partyanalyst.dao.hibernate.TehsilDAO;
import com.itgrids.partyanalyst.dao.hibernate.UserAddressDAO;
import com.itgrids.partyanalyst.dao.hibernate.VoterDAO;
import com.itgrids.partyanalyst.dto.AppointmentBasicInfoVO;
import com.itgrids.partyanalyst.dto.AppointmentVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Appointment;
import com.itgrids.partyanalyst.model.AppointmentCandidate;
import com.itgrids.partyanalyst.model.AppointmentUser;
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
	private IAppointmentLableStatusDAO appointmentLableStatusDAO;
	private DistrictDAO districtDAO;
	private ConstituencyDAO constituencyDAO;
	private TehsilDAO tehsilDAO;
	private UserAddressDAO userAddressDAO;
	private VoterDAO voterDAO;
	private TdpCadreDAO tdpCadreDAO;
	private IAppointmentCandidateDAO appointmentCandidateDAO;
	private IAppointmentManageUserDAO appointmentManageUserDAO;
	private IAppointmentLableDAO appointmentLableDAO;
	
	
	public IAppointmentLableDAO getAppointmentLableDAO() {
		return appointmentLableDAO;
	}
	public void setAppointmentLableDAO(IAppointmentLableDAO appointmentLableDAO) {
		this.appointmentLableDAO = appointmentLableDAO;
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
	public IAppointmentLableStatusDAO getAppointmentLableStatusDAO() {
		return appointmentLableStatusDAO;
	}
	public void setAppointmentLableStatusDAO(
			IAppointmentLableStatusDAO appointmentLableStatusDAO) {
		this.appointmentLableStatusDAO = appointmentLableStatusDAO;
	}
	public IAppointmentStatusDAO getAppointmentStatusDAO() {
		return appointmentStatusDAO;
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
		        	appointment.setAppointmentStatusId(appointmentVO.getAppointmentStatusId());
		        	appointment.setAppointmentPreferableTimeId(appointmentVO.getAppointmentPreferableIimeId());
		        	appointment.setCreatedBy(loggerUserId);
		        	appointment.setUpdatedBy(loggerUserId);
		        	appointment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
		        	appointment.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
		        	appointment.setIsDeleted("N");
		        	appointment = appointmentDAO.save(appointment);
		        	
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
		        					cadreIdsMap.put(objects[0].toString(),(Long)objects[1]);
								}
		        			}
		        		}
		        		
		        		
		        		for (AppointmentBasicInfoVO basicInfo : appointmentVO.getBasicInfoList()) {
		        			AppointmentCandidate appCandi = new AppointmentCandidate();
		        			appCandi.setAppointmentId(appointment.getAppointmentId());
		        			appCandi.setName(basicInfo.getName());
		        			appCandi.setDesignationId(basicInfo.getDesignationId());
		        			appCandi.setMobileNo(basicInfo.getMobileNo());
		        			appCandi.setLocationScopeId(basicInfo.getLocationScopeId());
		        			if(basicInfo.getLocationScopeId()==1l)//dist
		        				appCandi.setLocationValue(basicInfo.getDistrictId());
		        			else if(basicInfo.getLocationScopeId()==2l)//const
		        				appCandi.setLocationValue(basicInfo.getConstituencyId());
		        			else if(basicInfo.getLocationScopeId()==3l)//tehsil
		        				appCandi.setLocationValue(basicInfo.getTehsilId());
		        			
		        			//user addres saving logic
		        			UserAddress userAddress = new UserAddress();
		        			userAddress.setDistrict(districtDAO.get(basicInfo.getDistrictId()));
		        			userAddress.setConstituency(constituencyDAO.get(basicInfo.getConstituencyId()));
		        			userAddress.setTehsil(tehsilDAO.get(basicInfo.getTehsilId()));
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
		        			appointmentCandidateDAO.save(appCandi);
		        			
		        			
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
			List<Object[]>  appLblStatusLst = appointmentLableStatusDAO.getAppmntLblStatusList();
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
					appntmntUsrDtlsLst.add(appntmntUsrVO);
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised at getAppointmentUsersDtlsByUserId() method of AppointmentService", e);
		}
		return appntmntUsrDtlsLst;
	}
	@Override
	public List<AppointmentBasicInfoVO> getLabelDtslByDate(String slctdDate) {
		
		List<AppointmentBasicInfoVO> labelDtlsFnlList=new ArrayList<AppointmentBasicInfoVO>(0);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try{
			LOG.info("Entered into getLabelDtslByDate() method of AppointmentService");
			Date date=null;
			if(labelDtlsFnlList!=null){
				 date=sdf.parse(slctdDate);
			}
			List<Object[]> labelDtlsList=appointmentLableDAO.getLabelDtslByDate(date);
			if(labelDtlsList!=null && !labelDtlsList.isEmpty()){
				for(Object[] param:labelDtlsList){
					AppointmentBasicInfoVO labelDtslVO=new AppointmentBasicInfoVO();
					labelDtslVO.setName(param[0]!=null ? param[0].toString():"");
				    labelDtslVO.setDate(param[1]!=null ?param[1].toString().split(" ")[0]:"");
					labelDtlsFnlList.add(labelDtslVO);
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised at getLabelDtslByDate() method of AppointmentService", e);
		}
		return labelDtlsFnlList;
	}
}
