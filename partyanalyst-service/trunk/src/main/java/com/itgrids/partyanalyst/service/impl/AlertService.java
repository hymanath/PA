package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import com.itgrids.partyanalyst.dao.IAlertCandidateDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertTrackingDAO;
import com.itgrids.partyanalyst.dao.IAlertUserDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dto.AlertDataVO;
import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.dto.AlertTrackingVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertCandidate;
import com.itgrids.partyanalyst.model.AlertStatus;
import com.itgrids.partyanalyst.model.AlertTracking;
import com.itgrids.partyanalyst.model.AppointmentComment;
import com.itgrids.partyanalyst.model.AppointmentTracking;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IAlertService;
import com.itgrids.partyanalyst.utils.DateUtilService;
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
  
private DateUtilService dateUtilService = new DateUtilService();





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
				 alert.setDescription(inputVO.getDesc());
				 alert.setCreatedBy(userId);
				 alert.setUpdatedBy(userId);
				 alert.setAlertStatusId(1l);
				 alert.setUserTypeId(inputVO.getUserTypeId());
				 alert.setCreatedTime(date.getCurrentDateAndTime());
				 alert.setUpdatedTime(date.getCurrentDateAndTime());
				 UserAddress userAddress = saveUserAddress(inputVO);
				 alert.setAddressId(userAddress.getUserAddressId());
				 alert = alertDAO.save(alert);
				 
				 AlertCandidate alertCandidate = new AlertCandidate();
				 alertCandidate.setAlertId(alert.getAlertId());
				 alertCandidate.setCandidateId(inputVO.getCandidateId());
				 alertCandidate.setAlertImpactId(inputVO.getAlertImpactId());
				 alertCandidateDAO.save(alertCandidate);
				 rs = "success";
				/* AlertTrackingVO alertTrackingVO = new AlertTrackingVO();
				 alertTrackingVO.setUserId(userId);
				// alertTrackingVO.setAlertCommentId(alertCommentId);
				 alertTrackingVO.setAlertUserTypeId(inputVO.getUserTypeId());
				// alertTrackingVO.setAlertStatusId(alertStatusId);
				 alertTrackingVO.setAlertId(alert.getAlertId());
				 saveAlertTrackingDetails(alertTrackingVO)	;	*/
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
				alertTracking.setAlertUserTypeId(alertTracking.getAlertUserTypeId());
				alertTracking.setInsertedBy(alertTrackingVO.getUserId());
				alertTracking.setInsertedTime(currentDateAndTime);
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
			
			 List<Long> userTypeIds = alertUserDAO.getUserTypeIds(userId);
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
							 levelVo.setCount(levelVo.getCount() + (Long)params[0]);
							 levelVo.setLocationsList(setAlertStatusList(statusList));
							 returnList.add(levelVo);
						 }
						 
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
	public List<AlertDataVO> getLocationLevelWiseAlertsData(Long userId,AlertInputVO inputVO)
	{
		List<AlertDataVO> returnList = new ArrayList<AlertDataVO>();
		 List<Long> userTypeIds = alertUserDAO.getUserTypeIds(userId);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		List<Long> alertIds = new ArrayList<Long>();
		try{
			Date fromDate = null;Date toDate=null;
			if(inputVO.getFromDate() != null && !inputVO.getFromDate().toString().isEmpty())
			{
			 fromDate = format.parse(inputVO.getFromDate());
			 toDate = format.parse(inputVO.getToDate());
			}
			 List<Object[]> list = alertDAO.getLocationLevelWiseAlertsData(userTypeIds,fromDate,toDate,inputVO.getLevelId());
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
					 LocationVO locationVO = new LocationVO();
					 locationVO.setDistrictId(params[16] != null ? (Long)params[16] : null);
					 locationVO.setDistrictName(params[17] != null ?params[17].toString() : "");
					 locationVO.setConstituencyId(params[19] != null ? (Long)params[19] : null);
					 locationVO.setConstituencyName(params[20] != null ? params[20].toString() : "");
					 locationVO.setTehsilId(params[10] != null ? (Long)params[10] : null);
					 locationVO.setTehsilName(params[11] != null ? params[11].toString() : "");
					 locationVO.setVillageId(params[12] != null ? (Long)params[12] : null);
					 locationVO.setVillageName(params[13] != null ? params[13].toString() : "");
					 locationVO.setLocalBodyId(params[14] != null ? (Long)params[14] : null);
					 String eleType = params[19] != null ? params[19].toString() : "";
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
				 }
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
		
	}
	public String UpdateAlertStatus(final AlertVO inputVO,Long alertId,Long alertStatusId,String comments)
	{
		String rs = null;
		try{
			Alert alert =	alertDAO.get(inputVO.getTehsilId());
			
			alert.setAlertStatusId(inputVO.getPanchayatId());
				
		    alert = alertDAO.save(alert);
		    rs = "success";
		
		}catch(Exception e){
			e.printStackTrace();
			 rs = "fail";
		}
		return rs;
		
	}
	
}

