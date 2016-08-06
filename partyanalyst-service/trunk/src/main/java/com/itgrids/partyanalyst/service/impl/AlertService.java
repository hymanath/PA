package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAlertAssignedDAO;
import com.itgrids.partyanalyst.dao.IAlertCandidateDAO;
import com.itgrids.partyanalyst.dao.IAlertCommentDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertTrackingDAO;
import com.itgrids.partyanalyst.dao.IAlertTypeDAO;
import com.itgrids.partyanalyst.dao.IAlertUserDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IMemberTypeDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.impl.IAlertSourceDAO;
import com.itgrids.partyanalyst.dao.impl.IAlertSourceUserDAO;
import com.itgrids.partyanalyst.dto.AlertDataVO;
import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.dto.AlertTrackingVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.StatusTrackingVO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertAssigned;
import com.itgrids.partyanalyst.model.AlertCandidate;
import com.itgrids.partyanalyst.model.AlertComment;
import com.itgrids.partyanalyst.model.AlertStatus;
import com.itgrids.partyanalyst.model.AlertTracking;
import com.itgrids.partyanalyst.model.AppointmentComment;
import com.itgrids.partyanalyst.model.AppointmentTracking;
import com.itgrids.partyanalyst.model.MemberType;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IAlertService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;

public class AlertService implements IAlertService{
private TransactionTemplate transactionTemplate = null;
private IAlertDAO alertDAO;
private IAlertCandidateDAO alertCandidateDAO; 
private IUserAddressDAO userAddressDAO;
private IStateDAO stateDAO;
private IDistrictDAO districtDAO;
private ITehsilDAO tehsilDAO;
private ILocalElectionBodyDAO localElectionBodyDAO;
private IConstituencyDAO constituencyDAO;
private IAlertUserDAO alertUserDAO;
private SetterAndGetterUtilService setterAndGetterUtilService;
private static final Logger LOG = Logger.getLogger(AlertService.class);
private ICandidateDAO candidateDAO;
private IAlertStatusDAO alertStatusDAO;
private IAlertTrackingDAO alertTrackingDAO;
private IAlertCommentDAO alertCommentDAO;
private IAlertTypeDAO alertTypeDAO;
private IAlertSourceUserDAO alertSourceUserDAO;
private IAlertAssignedDAO alertAssignedDAO;
private DateUtilService dateUtilService = new DateUtilService();
private IMemberTypeDAO memberTypeDAO;
private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();



public IMemberTypeDAO getMemberTypeDAO() {
	return memberTypeDAO;
}

public void setMemberTypeDAO(IMemberTypeDAO memberTypeDAO) {
	this.memberTypeDAO = memberTypeDAO;
}

public IAlertAssignedDAO getAlertAssignedDAO() {
	return alertAssignedDAO;
}

public void setAlertAssignedDAO(IAlertAssignedDAO alertAssignedDAO) {
	this.alertAssignedDAO = alertAssignedDAO;
}

public IAlertSourceUserDAO getAlertSourceUserDAO() {
	return alertSourceUserDAO;
}

public void setAlertSourceUserDAO(IAlertSourceUserDAO alertSourceUserDAO) {
	this.alertSourceUserDAO = alertSourceUserDAO;
}

public IAlertCommentDAO getAlertCommentDAO() {
	return alertCommentDAO;
}

public void setAlertCommentDAO(IAlertCommentDAO alertCommentDAO) {
	this.alertCommentDAO = alertCommentDAO;
}

public IAlertTrackingDAO getAlertTrackingDAO() {
	return alertTrackingDAO;
}

public void setAlertTrackingDAO(IAlertTrackingDAO alertTrackingDAO) {
	this.alertTrackingDAO = alertTrackingDAO;
}

public IAlertStatusDAO getAlertStatusDAO() {
	return alertStatusDAO;
}

public void setAlertStatusDAO(IAlertStatusDAO alertStatusDAO) {
	this.alertStatusDAO = alertStatusDAO;
}

public void setSetterAndGetterUtilService(
		SetterAndGetterUtilService setterAndGetterUtilService) {
	this.setterAndGetterUtilService = setterAndGetterUtilService;
}

public IAlertUserDAO getAlertUserDAO() {
	return alertUserDAO;
}

public void setAlertUserDAO(IAlertUserDAO alertUserDAO) {
	this.alertUserDAO = alertUserDAO;
}

public IStateDAO getStateDAO() {
	return stateDAO;
}

public void setStateDAO(IStateDAO stateDAO) {
	this.stateDAO = stateDAO;
}

public IDistrictDAO getDistrictDAO() {
	return districtDAO;
}

public void setDistrictDAO(IDistrictDAO districtDAO) {
	this.districtDAO = districtDAO;
}

public ITehsilDAO getTehsilDAO() {
	return tehsilDAO;
}

public void setTehsilDAO(ITehsilDAO tehsilDAO) {
	this.tehsilDAO = tehsilDAO;
}

public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
	return localElectionBodyDAO;
}

public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
	this.localElectionBodyDAO = localElectionBodyDAO;
}

public ICandidateDAO getCandidateDAO() {
	return candidateDAO;
}

public void setCandidateDAO(ICandidateDAO candidateDAO) {
	this.candidateDAO = candidateDAO;
}

public IConstituencyDAO getConstituencyDAO() {
	return constituencyDAO;
}
public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
	this.constituencyDAO = constituencyDAO;
}
public IAlertDAO getAlertDAO() {
	return alertDAO;
}
public void setAlertDAO(IAlertDAO alertDAO) {
	this.alertDAO = alertDAO;
}

public IUserAddressDAO getUserAddressDAO() {
	return userAddressDAO;
}
public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
	this.userAddressDAO = userAddressDAO;
}
public IAlertCandidateDAO getAlertCandidateDAO() {
	return alertCandidateDAO;
}
public void setAlertCandidateDAO(IAlertCandidateDAO alertCandidateDAO) {
	this.alertCandidateDAO = alertCandidateDAO;
}
public TransactionTemplate getTransactionTemplate() {
	return transactionTemplate;
}
public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
	this.transactionTemplate = transactionTemplate;
}

public IAlertTypeDAO getAlertTypeDAO() {
	return alertTypeDAO;
}

public void setAlertTypeDAO(IAlertTypeDAO alertTypeDAO) {
	this.alertTypeDAO = alertTypeDAO;
}

public List<BasicVO> getCandidatesByName(String candidateName){
	List<BasicVO> list = new ArrayList<BasicVO>();
	 List<Object[]> candidate=null;
	 if(candidateName != null && candidateName.length() > 0)
		 candidate=candidateDAO.getCandidatesByName(candidateName);
	 else
		 candidate=candidateDAO.getCandidatesByName();
	
	 try{
		 LOG.info("entered into getCandidatesByName()");
	 if(candidate !=null && candidate.size()>0){
		 
		 for (Object[] objects : candidate) {
			BasicVO vo= new BasicVO();
			 vo.setId(Long.valueOf(objects[0].toString()));//candidate id
			 vo.setName(objects[1].toString());//last name
			 list.add(vo);
		}
	 }
	 }
	 catch (Exception e) {
		 e.printStackTrace();
		LOG.error("Exception ocured in getCandidatesByName()"+e);
	}
	return list;
}

public String createAlert(final AlertVO inputVO,final Long userId)
	{
	String resultStatus = (String) transactionTemplate
			.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					String rs = new String();
					try {
				 DateUtilService date = new DateUtilService();
				 Alert alert = new Alert();
				 
				 alert.setAlertSeverityId(inputVO.getSeverity());
				 alert.setAlertTypeId(inputVO.getAlertTypeId());
				 alert.setImpactLevelId(inputVO.getLocationLevelId());
				 alert.setImpactLevelValue(inputVO.getLocationValue());
				 alert.setDescription(inputVO.getDesc().toString());
				 alert.setCreatedBy(userId);
				 alert.setUpdatedBy(userId);
				 alert.setAlertStatusId(1l);
				 alert.setAlertSourceId(inputVO.getAlertSourceId());
				 alert.setCreatedTime(date.getCurrentDateAndTime());
				 alert.setUpdatedTime(date.getCurrentDateAndTime());
				 alert.setIsDeleted("N");
				 UserAddress userAddress = saveUserAddress(inputVO);
				 alert.setAddressId(userAddress.getUserAddressId());
				 alert = alertDAO.save(alert);
				 
				 
				 if(inputVO.getIdNamesList() != null && inputVO.getIdNamesList().size() > 0)
				 {
					for(IdNameVO vo : inputVO.getIdNamesList())
					 {
						 if(vo != null && vo.getId()!= null && vo.getId() > 0)
						 {
							 AlertCandidate alertCandidate = new AlertCandidate();
							 alertCandidate.setAlertId(alert.getAlertId());
							 alertCandidate.setTdpCadreId(vo.getId());
							 if(vo.getName() == null)
								 alertCandidate.setAlertImpactId(2l); 
							else
							 alertCandidate.setAlertImpactId(1l);
							 alertCandidateDAO.save(alertCandidate);
						 }
						 
					 }
				 }
				 rs = "success";
				    AlertComment alertComment = new AlertComment();
				    alertComment.setComments(inputVO.getDesc().toString());
				    alertComment.setAlertId(alert.getAlertId());
				    alertComment.setInsertedTime(date.getCurrentDateAndTime());
				    alertComment.setIsDeleted("N");
				    alertComment.setInsertedBy(userId);
				    alertComment = alertCommentDAO.save(alertComment);
				 AlertTrackingVO alertTrackingVO = new AlertTrackingVO();
				 alertTrackingVO.setUserId(userId);
				 alertTrackingVO.setAlertCommentId(alertComment.getAlertCommentId());
				 alertTrackingVO.setAlertUserTypeId(inputVO.getAlertSourceId());
				 alertTrackingVO.setAlertStatusId(1l);
				 alertTrackingVO.setAlertId(alert.getAlertId());
				 alertTrackingVO.setAlertTrackingActionId(IConstants.ALERT_ACTION_STATUS_CHANGE);
				 
				 saveAlertTrackingDetails(alertTrackingVO)	;	
					}
					catch (Exception ex) {
						 rs = "fail";
						
						return rs;
					}
						return rs;
				}

			});
	return resultStatus;
	}

public ResultStatus saveAlertTrackingDetails(final AlertTrackingVO alertTrackingVO)
{
	ResultStatus rs = new ResultStatus();
	try {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
	        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
	        	Date currentDateAndTime  = dateUtilService.getCurrentDateAndTime();
				AlertTracking alertTracking=new AlertTracking();
				alertTracking.setAlertId(alertTrackingVO.getAlertId());
				alertTracking.setAlertStatusId(alertTrackingVO.getAlertStatusId());
				alertTracking.setAlertCommentId(alertTrackingVO.getAlertCommentId());
				alertTracking.setAlertSourceId(alertTracking.getAlertUserTypeId());
				alertTracking.setInsertedBy(alertTrackingVO.getUserId());
				alertTracking.setInsertedTime(currentDateAndTime);
				alertTracking.setAlertTrackingActionId(alertTrackingVO.getAlertTrackingActionId());
				alertTrackingDAO.save(alertTracking);
			 }
		});
		rs.setExceptionMsg("success");
		rs.setResultCode(0);
		
	} catch (Exception e) {
		rs.setExceptionMsg("failure");
		rs.setResultCode(1);
		LOG.error("Exception raised at saveAlertTrackingDetails() in AlertService class ", e);
	}
	return rs;
}
	public UserAddress saveUserAddress(final AlertVO inputVO)
	{
		UserAddress userAddress = new UserAddress();
		try{
			
			if(inputVO.getLocationLevelId().longValue() == 2l)
			{
				userAddress.setState(stateDAO.get(inputVO.getStateId()));
			}
			else if(inputVO.getLocationLevelId().longValue() == 3l)
			{
				userAddress.setState(stateDAO.get(inputVO.getStateId()));
				userAddress.setDistrict(districtDAO.get(inputVO.getDistrictId()));
				
			}
			
			else if(inputVO.getLocationLevelId().longValue() == 4l)
			{
				userAddress.setState(stateDAO.get(inputVO.getStateId()));
				userAddress.setDistrict(districtDAO.get(inputVO.getDistrictId()));
				userAddress.setConstituency(constituencyDAO.get(inputVO.getConstituencyId()));
			}
			else if(inputVO.getLocationLevelId().longValue() == 5l || inputVO.getLocationLevelId().longValue() == 7l)
			{
				userAddress.setState(stateDAO.get(inputVO.getStateId()));
				userAddress.setDistrict(districtDAO.get(inputVO.getDistrictId()));
				userAddress.setConstituency(constituencyDAO.get(inputVO.getConstituencyId()));
				if(inputVO.getLocationLevelId() ==  5l)
					userAddress.setTehsil(tehsilDAO.get(inputVO.getTehsilId()));
				else
					userAddress.setLocalElectionBody(localElectionBodyDAO.get(inputVO.getTehsilId()));	
			}
			
			else if(inputVO.getLocationLevelId().longValue() == 6l || inputVO.getLocationLevelId().longValue() == 8l)
			{
				userAddress.setState(stateDAO.get(inputVO.getStateId()));
				userAddress.setDistrict(districtDAO.get(inputVO.getDistrictId()));
				userAddress.setConstituency(constituencyDAO.get(inputVO.getConstituencyId()));
				if(inputVO.getLocationLevelId() ==  6l)
				{
					userAddress.setTehsil(tehsilDAO.get(inputVO.getTehsilId()));
					userAddress.setPanchayatId(inputVO.getPanchayatId());
				}
				else
				{
					userAddress.setLocalElectionBody(localElectionBodyDAO.get(inputVO.getTehsilId()));	
					userAddress.setWard(constituencyDAO.get(inputVO.getPanchayatId()));
				}
			}
			
			userAddress = userAddressDAO.save(userAddress);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return userAddress;
	}
	
	public List<BasicVO> getLocationLevelWiseAlerts(Long userId,String fromDate,String toDate)
	{
		 List<BasicVO> returnList = new ArrayList<BasicVO>();
		try{
			Date startDate=null;
			Date endDate=null;
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
			if(fromDate!=null && fromDate.length()>0){
				startDate=sdf.parse(fromDate);
			}
			if(toDate!=null && toDate.length()>0){
				endDate=sdf.parse(toDate);
			}
			
			 List<Long> userTypeIds = alertSourceUserDAO.getAlertSourceUserIds(userId);
			 List<AlertStatus> statusList =  alertStatusDAO.getAll();
			 if(userTypeIds != null && userTypeIds.size() > 0)
			 {
				 List<Object[]> list = alertDAO.getLocationLevelWiseAlerts(userTypeIds,startDate,endDate);
				 if(list != null && list.size() > 0)
				 {
					 for(Object[] params : list)
					 {
						 
						 BasicVO levelVo =  (BasicVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "id", params[1].toString()) ;
						 if(levelVo == null)
						 {
							 levelVo = new BasicVO();
							 levelVo.setId((Long)params[1]);
							 levelVo.setName(params[2].toString());
							
							 levelVo.setLocationsList(setAlertStatusList(statusList));
							 returnList.add(levelVo);
						 }
						 levelVo.setCount(levelVo.getCount() + (Long)params[0]);
						 BasicVO statusVO =  (BasicVO) setterAndGetterUtilService.getMatchedVOfromList(levelVo.getLocationsList(), "id", params[3].toString()) ;
						 if(statusVO != null)
						 {
							 statusVO.setCount(statusVO.getCount() + (Long)params[0]);
						 }
						 
					 }
				 }
				 
			 }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return returnList;
	}
	
	public List<BasicVO> setAlertStatusList(List<AlertStatus> list)
	{
		 List<BasicVO> statusList = new ArrayList<BasicVO>();
		 
		 for(AlertStatus status : list)
		 {
			 BasicVO vo = new BasicVO();
			 vo.setId(status.getAlertStatusId());
			 vo.setName(status.getAlertStatus());
			 statusList.add(vo); 
		 }
		 
		return statusList;
		
	}
	public List<AlertDataVO> getAlertsData(Long alertId)
	{
		List<AlertDataVO> returnList = new ArrayList<AlertDataVO>();
		
		List<Long> alertIds = new ArrayList<Long>();
		try{
			 List<Object[]> list = alertDAO.getAlertsData(alertId);
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params : list)
				 {
					 AlertDataVO alertVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "id", params[0].toString());
					 if(alertVO == null)
					 {
						 alertVO = new AlertDataVO(); 
						 returnList.add(alertVO);
						 if(!alertIds.contains((Long)params[0]));
						 alertIds.add((Long)params[0]);
					 }
					 alertVO.setId((Long)params[0]);
					 alertVO.setDesc(params[1].toString());
					 alertVO.setDate(params[2] != null? params[2].toString():"");
					 alertVO.setAlertType(params[3] != null ? params[3].toString() : "");
					 alertVO.setUserType(params[4] != null ? params[4].toString() : "");
					 alertVO.setSeverity(params[5] != null ? params[5].toString() : "");
					 alertVO.setRegionScopeId(params[6] != null ? (Long)params[6] : null);
					 alertVO.setRegionScope(params[7] != null ?params[7].toString() : "");
					 alertVO.setStatusId(params[8] != null ? (Long)params[8] : null);
					 alertVO.setStatus(params[9] != null ?params[9].toString() : "");
					 LocationVO locationVO = new LocationVO();
					 locationVO.setWardId(params[23] != null ? (Long)params[23] : null);
					 locationVO.setWardName(params[24] != null ? params[24].toString() : "");
					 locationVO.setStateId(params[21] != null ? (Long)params[21] : null);
					 locationVO.setState(params[22] != null ? params[22].toString() : "");
					 locationVO.setDistrictId(params[16] != null ? (Long)params[16] : null);
					 locationVO.setDistrictName(params[17] != null ?params[17].toString() : "");
					 locationVO.setConstituencyId(params[19] != null ? (Long)params[19] : null);
					 locationVO.setConstituencyName(params[20] != null ? params[20].toString() : "");
					 locationVO.setTehsilId(params[10] != null ? (Long)params[10] : null);
					 locationVO.setTehsilName(params[11] != null ? params[11].toString() : "");
					 locationVO.setVillageId(params[12] != null ? (Long)params[12] : null);
					 locationVO.setVillageName(params[13] != null ? params[13].toString() : "");
					 locationVO.setLocalBodyId(params[14] != null ? (Long)params[14] : null);
					 String eleType = params[18] != null ? params[18].toString() : "";
					 locationVO.setLocalEleBodyName(params[15] != null ? params[15].toString() +" "+eleType : "");
					alertVO.setLocationVO(locationVO);
					 
					
				 }
				 if(alertIds != null && alertIds.size() > 0)
				 {
				 List<Object[]> candiateCnts = alertCandidateDAO.getAlertCandidateCount(alertIds);
					 for(Object[] params : candiateCnts)
					 {
						 AlertDataVO alertVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "id", params[1].toString());
							 if(alertVO != null)
							 {
								 alertVO.setCount((Long)params[0]);
							 }
					 }
					 List<Object[]> alertCandidates = alertCandidateDAO.getAlertCandidatesData(alertIds);
					 setAlertCandidateData(alertCandidates,returnList);
				 }
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
		
	}
	public List<AlertDataVO> getLocationLevelWiseAlertsData(Long userId,AlertInputVO inputVO)
	{
		List<AlertDataVO> returnList = new ArrayList<AlertDataVO>();
		 List<Long> userTypeIds = alertSourceUserDAO.getAlertSourceUserIds(userId);
		 SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		List<Long> alertIds = new ArrayList<Long>();
		try{
			Date fromDate = null;Date toDate=null;
			if(inputVO.getFromDate() != null && !inputVO.getFromDate().toString().isEmpty())
			{
			 fromDate = sdf.parse(inputVO.getFromDate());
			 toDate = sdf.parse(inputVO.getToDate());
			}
			 List<Object[]> list = alertDAO.getLocationLevelWiseAlertsData(userTypeIds,fromDate,toDate,inputVO.getLevelId(),inputVO.getStatusId());
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params : list)
				 {
					 AlertDataVO alertVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "id", params[0].toString());
					 if(alertVO == null)
					 {
						 alertVO = new AlertDataVO(); 
						 returnList.add(alertVO);
						 if(!alertIds.contains((Long)params[0]));
						 alertIds.add((Long)params[0]);
					 }
					 alertVO.setId((Long)params[0]);
					 alertVO.setDesc(params[1].toString());
					 alertVO.setDate(params[2] != null? params[2].toString():"");
					 alertVO.setAlertType(params[3] != null ? params[3].toString() : "");
					 alertVO.setUserType(params[4] != null ? params[4].toString() : "");
					 alertVO.setSeverity(params[5] != null ? params[5].toString() : "");
					 alertVO.setRegionScopeId(params[6] != null ? (Long)params[6] : null);
					 alertVO.setRegionScope(params[7] != null ?params[7].toString() : "");
					 alertVO.setStatusId(params[8] != null ? (Long)params[8] : null);
					 alertVO.setStatus(params[9] != null ?params[9].toString() : "");
					 LocationVO locationVO = new LocationVO();
					 locationVO.setWardId(params[23] != null ? (Long)params[23] : null);
					 locationVO.setWardName(params[24] != null ? params[24].toString() : "");
					 locationVO.setStateId(params[21] != null ? (Long)params[21] : null);
					 locationVO.setState(params[22] != null ? params[22].toString() : "");
					 locationVO.setDistrictId(params[16] != null ? (Long)params[16] : null);
					 locationVO.setDistrictName(params[17] != null ?params[17].toString() : "");
					 locationVO.setConstituencyId(params[19] != null ? (Long)params[19] : null);
					 locationVO.setConstituencyName(params[20] != null ? params[20].toString() : "");
					 locationVO.setTehsilId(params[10] != null ? (Long)params[10] : null);
					 locationVO.setTehsilName(params[11] != null ? params[11].toString() : "");
					 locationVO.setVillageId(params[12] != null ? (Long)params[12] : null);
					 locationVO.setVillageName(params[13] != null ? params[13].toString() : "");
					 locationVO.setLocalBodyId(params[14] != null ? (Long)params[14] : null);
					 String eleType = params[18] != null ? params[18].toString() : "";
					 locationVO.setLocalEleBodyName(params[15] != null ? params[15].toString() +" "+eleType : "");
					alertVO.setLocationVO(locationVO);
					 
					
				 }
				 if(alertIds != null && alertIds.size() > 0)
				 {
				 List<Object[]> candiateCnts = alertCandidateDAO.getAlertCandidateCount(alertIds);
					 for(Object[] params : candiateCnts)
					 {
						 AlertDataVO alertVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "id", params[1].toString());
							 if(alertVO != null)
							 {
								 alertVO.setCount((Long)params[0]);
							 }
					 }
					 List<Object[]> alertCandidates = alertCandidateDAO.getAlertCandidatesData(alertIds);
					 setAlertCandidateData(alertCandidates,returnList);
				 }
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
		
	}
	
	

	public String updateAlertStatus(final Long userId,final AlertVO inputVo)
	{
		String resultStatus = (String) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						String rs = new String();
						try {
			Date currentDateAndTime  = dateUtilService.getCurrentDateAndTime();
			Alert alert =	alertDAO.get(inputVo.getId());
			alert.setAlertStatusId(inputVo.getStatusId());
			alert = alertDAO.save(alert);
		    rs = "success";
		    
		    AlertComment alertComment = new AlertComment();
		    if(inputVo.getDesc() != null && !inputVo.getDesc().isEmpty())
		    	//alertComment.setComments(commonMethodsUtilService.getUniCodeMessage(StringEscapeUtils.unescapeJava(IConstants.TRAINING_CAMP_FEEDBACK_SMS_CONTENT)));
		   alertComment.setComments(StringEscapeUtils.unescapeJava(inputVo.getDesc().toString()));
		    alertComment.setAlertId(inputVo.getId());
		    alertComment.setInsertedTime(currentDateAndTime);
		    alertComment.setIsDeleted("N");
		    alertComment.setInsertedBy(userId);
		    alertComment = alertCommentDAO.save(alertComment);
		    
		    
		     AlertTrackingVO alertTrackingVO = new AlertTrackingVO();
			 alertTrackingVO.setUserId(userId);
			 alertTrackingVO.setAlertCommentId(alertComment.getAlertCommentId());
			// alertTrackingVO.setAlertUserTypeId();
			 alertTrackingVO.setAlertStatusId(inputVo.getStatusId());
			 alertTrackingVO.setAlertId(alert.getAlertId());
			 alertTrackingVO.setAlertTrackingActionId(IConstants.ALERT_ADDED_COMMENT);
			 saveAlertTrackingDetails(alertTrackingVO)	;	
		
						}
						catch (Exception ex) {
							 rs = "fail";
							
							return rs;
						}
							return rs;
					}

				});
		return resultStatus;
		}

	// Alert Status Flow Tracking Details
	public List<StatusTrackingVO> getAlertStatusCommentsTrackingDetails(Long alertId)
			{
				LOG.info("Entered in getAlertStatusCommentsTrackingDetails() method");
				List<StatusTrackingVO> resultList = null;
				try{
					List<Object[]> list = alertTrackingDAO.getAlertTrackingDetails(alertId);
					resultList = getAlertStatusCommentsList(list);
							
						}
				catch(Exception e)
				{
					e.printStackTrace();
					LOG.error("Entered in getAppointmentStatusFlowTrackingDetails() method");
				}
				return resultList;
			}
	
	public List<StatusTrackingVO> getAlertStatusCommentsList(List<Object[]> list)
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
					vo.setSubList(new ArrayList<IdNameVO>());
					IdNameVO commentVO = new IdNameVO();
					commentVO.setName(params[7] != null ? params[7].toString() : "");
					String fname1 = params[8] != null ? params[8].toString() : "";
					String lname1 = params[9] != null ? params[9].toString() : "";
					commentVO.setStatus(fname1+" "+lname1);
					commentVO.setDateStr(params[10] != null ? params[10].toString().substring(0, 19) : "");
					
					vo.getSubList().add(commentVO);
				}
				vo.setDate(params[5] != null ? params[5].toString().substring(0, 19) : "");
				 for(int j=i+1;j<list.size();j++)
				  {
					  Object[] params1 = list.get(j);
					
					  if(params[1].toString().equalsIgnoreCase(params1[1].toString()))
					  {
						  IdNameVO commentVO = new IdNameVO();
							commentVO.setName(params[7] != null ? params[7].toString() : "");
							String fname1 = params[8] != null ? params[8].toString() : "";
							String lname1 = params[9] != null ? params[9].toString() : "";
							commentVO.setStatus(fname1+" "+lname1);
							commentVO.setDateStr(params[10] != null ? params[10].toString().substring(0, 19) : "");
							vo.getSubList().add(commentVO);
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
			LOG.error("Entered in getStatusFlowList() method");
		}
		return resultList;
	}
	public List<BasicVO> getAlertType()
	{
		List<BasicVO> returnList = new ArrayList<BasicVO>();
		try{
			 List<Object[]> list = alertTypeDAO.getAlertType();	
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params : list)
				 {
					 BasicVO vo = new BasicVO();
					 vo.setId((Long)params[0]);
					 vo.setName(params[1].toString());
					 returnList.add(vo);
				 }
				 
			 }
		}
		catch (Exception e) {
			LOG.error("Exception in getAlertType()",e);	
		}
		return returnList;
	}
	
	public List<BasicVO> getAlertSourceForUser(Long userId)
	{
		List<BasicVO> returnList = new ArrayList<BasicVO>();
		try{
			 List<Object[]> list = alertSourceUserDAO.getAlertSourceUsersByUser(userId);	
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params : list)
				 {
					 BasicVO vo = new BasicVO();
					 vo.setId((Long)params[0]);
					 vo.setName(params[1].toString());
					 returnList.add(vo);
				 }
				 
			 }
		}
		catch (Exception e) {
			LOG.error("Exception in getAlertSourceForUser()",e);	
		}
		return returnList;
	}

	public List<AlertDataVO> getAlertCandidatesData(Long alertId)
	{
		List<AlertDataVO> dataList = new ArrayList<AlertDataVO>();
		try{
			List<Long> alertIds = new ArrayList<Long>();
			
			alertIds.add(alertId);
			List<Object[]> list = alertCandidateDAO.getAlertCandidatesData(alertIds);
			setAlertCandidateData(list,dataList);
		
		}
		catch (Exception e) {
			LOG.error("Exception in getLocationLevelWiseCandidateAlertsData()",e);	
		}
		return dataList;
	}
	
	public void setAlertCandidateData(List<Object[]> list,List<AlertDataVO> dataList)

	{
		
		if(dataList == null)
			dataList = new ArrayList<AlertDataVO>();
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				AlertDataVO alertVo =(AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(dataList, "id", params[0].toString());
				if(alertVo == null)
				{
					alertVo = new AlertDataVO();
					alertVo.setId((Long)params[0]);
					dataList.add(alertVo);
				}
				AlertDataVO candidateVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(alertVo.getSubList(), "id", params[1].toString());
				if(candidateVO == null)
				{
					candidateVO = new AlertDataVO();
					alertVo.getSubList().add(candidateVO);
				}
				candidateVO.setId((Long)params[1]);
				candidateVO.setName(params[2] != null ? params[2].toString() : "");
				 LocationVO locationVO = new LocationVO();
				 locationVO.setWardId(params[16] != null ? (Long)params[16] : null);
				 locationVO.setWardName(params[17] != null ? params[17].toString() : "");
				 locationVO.setStateId(params[14] != null ? (Long)params[14] : null);
				 locationVO.setState(params[15] != null ? params[15].toString() : "");
				 locationVO.setDistrictId(params[9] != null ? (Long)params[9] : null);
				 locationVO.setDistrictName(params[10] != null ?params[10].toString() : "");
				 locationVO.setConstituencyId(params[12] != null ? (Long)params[12] : null);
				 locationVO.setConstituencyName(params[13] != null ? params[13].toString() : "");
				 locationVO.setTehsilId(params[3] != null ? (Long)params[3] : null);
				 locationVO.setTehsilName(params[4] != null ? params[4].toString() : "");
				 locationVO.setVillageId(params[5] != null ? (Long)params[5] : null);
				 locationVO.setVillageName(params[6] != null ? params[6].toString() : "");
				 locationVO.setLocalBodyId(params[7] != null ? (Long)params[7] : null);
				 String eleType = params[11] != null ? params[11].toString() : "";
				 locationVO.setLocalEleBodyName(params[8] != null ? params[8].toString() +" "+eleType : "");
				 candidateVO.setLocationVO(locationVO);
				 
				 candidateVO.setImpactId(params[18] != null ? (Long)params[18] : null);
				 candidateVO.setImpact(params[19] != null ? params[19].toString() : "");
				 candidateVO.setImage(params[20] != null ? params[20].toString() : "");
				 candidateVO.setMobileNo(params[21] != null ? params[21].toString() : "");
				
			}
		}
		
	}
	
	public ResultStatus saveAlertAssignedUser(AlertVO inputVO,Long userId)
	{
		ResultStatus rs = new ResultStatus();
		List<Long> tdpCadreIds = new ArrayList();
		try{
			 DateUtilService date = new DateUtilService();
			  List<Long> existCadreIds = new ArrayList<Long>();
			 if(inputVO.getIdNamesList() != null && inputVO.getIdNamesList().size() > 0)
			 {
				 
				 for(IdNameVO vo : inputVO.getIdNamesList() )
				 {
					 tdpCadreIds.add(vo.getId());
				 }
				  existCadreIds = alertAssignedDAO.checkCadreExistsForAlert(tdpCadreIds,inputVO.getAlertTypeId());
				  
				 for(IdNameVO vo : inputVO.getIdNamesList() )
				 {
					 if(!existCadreIds.contains(vo.getId()))
					 {
						AlertAssigned alertAssigned = new AlertAssigned();
						alertAssigned.setAlertId(inputVO.getAlertTypeId());
						alertAssigned.setTdpCadreId(vo.getId());
						alertAssigned.setCreatedBy(userId);
						alertAssigned.setInsertedTime(date.getCurrentDateAndTime());
						alertAssigned.setUpdatedTime(date.getCurrentDateAndTime());
						alertAssigned.setIsDeleted("N");
						alertAssignedDAO.save(alertAssigned);
					 }
				 }
			 }
			rs.setResultCode(0);
			if(existCadreIds != null && existCadreIds.size() > 0)
			rs.setMessage(Integer.toString(existCadreIds.size()));
		}
		catch(Exception e)
		{
			LOG.error("Exception in saveAlertAssignedUser()",e);	
			rs.setResultCode(121);
		}
		return rs;
	}
	
	public List<IdNameVO> getMemberTypesList()
	{
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try{
			List<MemberType> list = memberTypeDAO.getAll();
			if(list != null && list.size() > 0)
			{
				for(MemberType obj : list)
				{
					IdNameVO vo = new IdNameVO();
					vo.setId(obj.getMemberTypeId());
					vo.setName(obj.getType());
					returnList.add(vo);
				}
			}
		}
		catch(Exception e)
		{
			LOG.error("Exception in getMemberTypesList()",e);	
			
		}
		return returnList;
	}
	public List<AlertDataVO> getAlertAssignedCandidates(Long alertId)
	{
		List<AlertDataVO> dataList = new ArrayList<AlertDataVO>();
		try{
			List<Long> alertIds = new ArrayList<Long>();
			
			alertIds.add(alertId);
			List<Object[]> list = alertCandidateDAO.getAlertAssignedCandidates(alertIds);
			setAlertAssignedCandidateData(list,dataList);
		
		}
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception in getAlertAssignedCandidates()",e);	
		}
		return dataList;
	}
	
	
	public void setAlertAssignedCandidateData(List<Object[]> list,List<AlertDataVO> dataList)

	{
		
		if(dataList == null)
			dataList = new ArrayList<AlertDataVO>();
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				AlertDataVO alertVo =(AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(dataList, "id", params[0].toString());
				if(alertVo == null)
				{
					alertVo = new AlertDataVO();
					alertVo.setId((Long)params[0]);
					dataList.add(alertVo);
				}
				AlertDataVO candidateVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(alertVo.getSubList(), "id", params[1].toString());
				if(candidateVO == null)
				{
					candidateVO = new AlertDataVO();
					alertVo.getSubList().add(candidateVO);
				}
				candidateVO.setId((Long)params[1]);
				candidateVO.setName(params[2] != null ? params[2].toString() : "");
				 LocationVO locationVO = new LocationVO();
				 locationVO.setWardId(params[16] != null ? (Long)params[16] : null);
				 locationVO.setWardName(params[17] != null ? params[17].toString() : "");
				 locationVO.setStateId(params[14] != null ? (Long)params[14] : null);
				 locationVO.setState(params[15] != null ? params[15].toString() : "");
				 locationVO.setDistrictId(params[9] != null ? (Long)params[9] : null);
				 locationVO.setDistrictName(params[10] != null ?params[10].toString() : "");
				 locationVO.setConstituencyId(params[12] != null ? (Long)params[12] : null);
				 locationVO.setConstituencyName(params[13] != null ? params[13].toString() : "");
				 locationVO.setTehsilId(params[3] != null ? (Long)params[3] : null);
				 locationVO.setTehsilName(params[4] != null ? params[4].toString() : "");
				 locationVO.setVillageId(params[5] != null ? (Long)params[5] : null);
				 locationVO.setVillageName(params[6] != null ? params[6].toString() : "");
				 locationVO.setLocalBodyId(params[7] != null ? (Long)params[7] : null);
				 String eleType = params[11] != null ? params[11].toString() : "";
				 locationVO.setLocalEleBodyName(params[8] != null ? params[8].toString() +" "+eleType : "");
				 candidateVO.setLocationVO(locationVO);
				 
				/* candidateVO.setImpactId(params[17] != null ? (Long)params[17] : null);
				 candidateVO.setImpact(params[18] != null ? params[18].toString() : "");*/
				 candidateVO.setImage(params[18] != null ? params[18].toString() : "");
				candidateVO.setMobileNo(params[19] != null ? params[19].toString() : "");
			}
		}
		
	}
	public String deleteAlertAssignedCandidates(Long alertId,Long tdpCadreId)
	{
		String rs=null;
		try{
			List<Long> list = alertAssignedDAO.getDeleteAlertAssignedCandidates(alertId,tdpCadreId);
		
			if(list != null && list.size() > 0)
			{
				for(Long obj : list)
				{
					AlertAssigned alertAssigned = alertAssignedDAO.get(obj);
					alertAssigned.setIsDeleted("Y");
					alertAssignedDAO.save(alertAssigned);
				}
				rs="success";
			}
		}
		catch (Exception e) {
			     rs="failure";
			e.printStackTrace();
			LOG.error("Exception in deleteAlertAssignedCandidates()",e);	
		}
		return rs;
	}	
	
}

