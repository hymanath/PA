package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAlertAssignedDAO;
import com.itgrids.partyanalyst.dao.IAlertCandidateDAO;
import com.itgrids.partyanalyst.dao.IAlertCategoryDAO;
import com.itgrids.partyanalyst.dao.IAlertCommentAssigneeDAO;
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
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.impl.IAlertSourceUserDAO;
import com.itgrids.partyanalyst.dto.ActionableVO;
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
import com.itgrids.partyanalyst.model.AlertCommentAssignee;
import com.itgrids.partyanalyst.model.AlertStatus;
import com.itgrids.partyanalyst.model.AlertTracking;
import com.itgrids.partyanalyst.model.MemberType;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IAlertService;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
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
private IAlertCommentAssigneeDAO alertCommentAssigneeDAO;
private ITdpCommitteeMemberDAO tdpCommitteeMemberDAO;
private ICadreCommitteeService cadreCommitteeService;
private IAlertCategoryDAO alertCategoryDAO;
private IPanchayatDAO panchayatDAO;

private ITdpCadreCandidateDAO tdpCadreCandidateDAO;



public ITdpCadreCandidateDAO getTdpCadreCandidateDAO() {
	return tdpCadreCandidateDAO;
}

public void setTdpCadreCandidateDAO(ITdpCadreCandidateDAO tdpCadreCandidateDAO) {
	this.tdpCadreCandidateDAO = tdpCadreCandidateDAO;
}

public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
	this.panchayatDAO = panchayatDAO;
}

public ICadreCommitteeService getCadreCommitteeService() {
	return cadreCommitteeService;
}

public void setCadreCommitteeService(
		ICadreCommitteeService cadreCommitteeService) {
	this.cadreCommitteeService = cadreCommitteeService;
}

public ITdpCommitteeMemberDAO getTdpCommitteeMemberDAO() {
	return tdpCommitteeMemberDAO;
}

public void setTdpCommitteeMemberDAO(
		ITdpCommitteeMemberDAO tdpCommitteeMemberDAO) {
	this.tdpCommitteeMemberDAO = tdpCommitteeMemberDAO;
}

public IAlertCommentAssigneeDAO getAlertCommentAssigneeDAO() {
	return alertCommentAssigneeDAO;
}

public void setAlertCommentAssigneeDAO(
		IAlertCommentAssigneeDAO alertCommentAssigneeDAO) {
	this.alertCommentAssigneeDAO = alertCommentAssigneeDAO;
}

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


public void setAlertCategoryDAO(IAlertCategoryDAO alertCategoryDAO) {
	this.alertCategoryDAO = alertCategoryDAO;
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

				 if(inputVO.getAssignList() != null && inputVO.getAssignList().size() > 0)
					 alert.setAlertStatusId(2l);// if assifn list given default status is notified
				 else
					 alert.setAlertStatusId(1l);// default pending status

				 alert.setAlertSourceId(inputVO.getAlertSourceId());
				 alert.setCreatedTime(date.getCurrentDateAndTime());
				 alert.setUpdatedTime(date.getCurrentDateAndTime());
				 alert.setIsDeleted("N");
				 
				 alert.setAlertCategoryId(1L);//default Manual alert
				 alert.setTitle(inputVO.getTitle());
				 
				 UserAddress userAddress = saveUserAddress(inputVO);
				 alert.setAddressId(userAddress.getUserAddressId());
				 alert.setAlertCategoryTypeId(inputVO.getCategoryId());
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
				if(inputVO.getAssignList() != null && inputVO.getAssignList().size() > 0)
				 {
					 /* List<Long> existCadreIds = new ArrayList<Long>();
					  for(IdNameVO vo : inputVO.getAssignList())
						 {
							 if(vo != null && vo.getId()!= null && vo.getId() > 0)
							 {
								 existCadreIds.add(vo.getId());
							 }
						}
						  existCadreIds = alertAssignedDAO.checkCadreExistsForAlert(existCadreIds,alert.getAlertId());*/
							for(IdNameVO vo1 : inputVO.getAssignList())
							 {
								 if(vo1 != null && vo1.getId()!= null && vo1.getId() > 0)
								 {
									 				AlertAssigned alertAssigned = new AlertAssigned();
													alertAssigned.setAlertId(alert.getAlertId());
													alertAssigned.setTdpCadreId(vo1.getId());
													alertAssigned.setCreatedBy(userId);
													alertAssigned.setInsertedTime(date.getCurrentDateAndTime());
													alertAssigned.setUpdatedTime(date.getCurrentDateAndTime());
													alertAssigned.setIsDeleted("N");
													alertAssignedDAO.save(alertAssigned);
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
		
		try{
			Date fromDate = null;Date toDate=null;
			if(inputVO.getFromDate() != null && !inputVO.getFromDate().toString().isEmpty())
			{
			 fromDate = sdf.parse(inputVO.getFromDate());
			 toDate = sdf.parse(inputVO.getToDate());
			}
			 List<Object[]> list = alertDAO.getLocationLevelWiseAlertsData(userTypeIds,inputVO,fromDate,toDate);
			 setAlertLocationWiseData(list,returnList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
	}
	
	public List<AlertDataVO> getLocationWiseFilterAlertData(Long userId,LocationVO inputVO,Long assignedCadreId)
	{
		List<AlertDataVO> returnList = new ArrayList<AlertDataVO>();
		 List<Long> userTypeIds = alertSourceUserDAO.getAlertSourceUserIds(userId);
		 SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		
		try{
			Date fromDate = null;Date toDate=null;
			if(inputVO.getFromDate() != null && !inputVO.getFromDate().toString().isEmpty())
			{
			 fromDate = sdf.parse(inputVO.getFromDate());
			 toDate = sdf.parse(inputVO.getToDate());
			}
			 List<Object[]> list = alertDAO.getLocationWiseFilterAlertData(userTypeIds,fromDate,toDate,inputVO,assignedCadreId);
			 setAlertLocationWiseData(list,returnList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
	}
	
	public void setAlertLocationWiseData(List<Object[]> list,List<AlertDataVO> returnList)
	{
		List<Long> alertIds = new ArrayList<Long>();
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
				 alertVO.setAlertCategoryId(commonMethodsUtilService.getLongValueForObject(params[25]));
				 alertVO.setAlertCategoryName(commonMethodsUtilService.getStringValueForObject(params[26]));
				 
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
		    
		    
		    if(inputVo.getAssignList() != null && inputVo.getAssignList().size() > 0)
		    {
		    	for(IdNameVO vo : inputVo.getAssignList())
		    	{
		    		if(vo.getId() != null && vo.getId() > 0)
		    		{
			    		AlertCommentAssignee alertCommentAssignee = new AlertCommentAssignee();
			    		alertCommentAssignee.setAlertCommentId(alertComment.getAlertCommentId());
			    		alertCommentAssignee.setAssignTdpCadreId(vo.getId());
			    		alertCommentAssigneeDAO.save(alertCommentAssignee);
		    		}
		    	}
		    }
			   
		    
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
					resultList = getAlertStatusCommentsList(list,alertId);
							
						}
				catch(Exception e)
				{
					e.printStackTrace();
					LOG.error("Entered in getAppointmentStatusFlowTrackingDetails() method");
				}
				return resultList;
			}
	
	public List<StatusTrackingVO> getAlertStatusCommentsList(List<Object[]> list,Long alertId)
	{
		List<StatusTrackingVO>  resultList = new ArrayList<StatusTrackingVO>();
		Map<Long,List<IdNameVO>> assignMap = new HashMap<Long,List<IdNameVO>>();
		 List<Object[]> assignCadres = alertCommentAssigneeDAO.getAlertCommentAssignedCandidates(alertId);
		 if(assignCadres != null && assignCadres.size() > 0)
		 {
			 for(Object[] obj :assignCadres)
			 {
				 List<IdNameVO> candidates = assignMap.get((Long)obj[0]);
				 if(candidates == null || candidates.size() == 0)
				 {
					 candidates = new ArrayList<IdNameVO>();
					 assignMap.put((Long)obj[0], candidates);
				 }
				 IdNameVO vo = new IdNameVO();
				 vo.setId((Long)obj[1]);
				 vo.setName(obj[2] != null ? obj[2].toString() : "");
				 candidates.add(vo);
			 }
		 }
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
				if(vo.getCommentsList() == null || vo.getCommentsList().size() == 0)
				{
					vo.setSubList(new ArrayList<IdNameVO>());
					IdNameVO commentVO = new IdNameVO();
					commentVO.setName(params[7] != null ? params[7].toString() : "");
					String fname1 = params[8] != null ? params[8].toString() : "";
					String lname1 = params[9] != null ? params[9].toString() : "";
					commentVO.setStatus(fname1+" "+lname1);
					commentVO.setDateStr(params[10] != null ? params[10].toString().substring(0, 19) : "");
					/*commentVO.setApplicationStatus(params[11] != null ? params[11].toString() : null);//Tdpcadre
					commentVO.setApplicationStatusId(params[12] != null ? (Long)params[12] : null);//Tdpcadre
*/					List<IdNameVO> candidates = assignMap.get((Long)params[6]);
					commentVO.setSubList1(candidates);
					vo.getSubList().add(commentVO);
				}
				vo.setDate(params[5] != null ? params[5].toString().substring(0, 19) : "");
				 for(int j=i+1;j<list.size();j++)
				  {
					  Object[] params1 = list.get(j);
					
					  if(params[1].toString().equalsIgnoreCase(params1[1].toString()))
					  {
						  IdNameVO commentVO = new IdNameVO();
							commentVO.setName(params1[7] != null ? params1[7].toString() : "");
							String fname1 = params1[8] != null ? params1[8].toString() : "";
							String lname1 = params1[9] != null ? params1[9].toString() : "";
							commentVO.setStatus(fname1+" "+lname1);
							commentVO.setDateStr(params1[10] != null ? params1[10].toString().substring(0, 19) : "");
							/*commentVO.setApplicationStatus(params1[11] != null ? params1[11].toString() : null);//Tdpcadre
							commentVO.setApplicationStatusId(params1[12] != null ? (Long)params1[12] : null);//Tdpcadre*/							List<IdNameVO> candidates = assignMap.get((Long)params1[6]);
							commentVO.setSubList1(candidates);
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
		List<Long> tdpCadreIdsList = new ArrayList<Long>();
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
				if(!tdpCadreIdsList.contains((Long)params[1]))
					tdpCadreIdsList.add((Long)params[1]);
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
				 if(dataList != null && dataList.size() > 0)
				 setCurrentDesignationForCadre(dataList.get(0).getSubList(), tdpCadreIdsList);
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
		List<Long> tdpCadreIdsList = new ArrayList<Long>();
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
				if(!tdpCadreIdsList.add((Long)params[1]));
				tdpCadreIdsList.add((Long)params[1]);
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
			 if(dataList != null && dataList.size() > 0)
			setCurrentDesignationForCadre(dataList.get(0).getSubList(), tdpCadreIdsList);
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
	
	public List<StatusTrackingVO> getAlertAssignedCandidate(Long alertId)
	{
		LOG.info("Entered in getAlertAssignedCandidate() method");
		List<StatusTrackingVO> resultList = new ArrayList<StatusTrackingVO>(); ;
		try{
			List<Object[]> list = alertAssignedDAO.getAlertAssignedCandidate(alertId);
			 if(list !=null && list.size()>0){
				 
				 for (Object[] objects : list) {
					 StatusTrackingVO vo= new StatusTrackingVO();
					 vo.setId(Long.valueOf(objects[0].toString()));//candidate id
					 vo.setUname(objects[1].toString());//first name
					 resultList.add(vo);
				}
			 }				
				}
		catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Entered in getAlertAssignedCandidate() method");
		}
		return resultList;
	}

	
	public void setCurrentDesignationForCadre(List<AlertDataVO> cadreCommitteeList,List<Long> tdpCadreIdsList){
		List<Object[]> tdpCommitteeMemberList = tdpCommitteeMemberDAO.getTdpCommitteeMemberForTdpCadreIdList(tdpCadreIdsList);
		
		if(tdpCommitteeMemberList != null && tdpCommitteeMemberList.size()>0)
		{
			for (Object[] tdpCadre : tdpCommitteeMemberList) 
			{
				Long id = tdpCadre[0] != null ? Long.valueOf(tdpCadre[0].toString()):0L;
				String committeeName = tdpCadre[1] != null ? tdpCadre[1].toString():"";
				String positionName =  tdpCadre[2] != null ? tdpCadre[2].toString():"";
				Long LocationTypeId = tdpCadre[3] != null ? Long.valueOf(tdpCadre[3].toString()):0L;
				Long locationValue = tdpCadre[4] != null ? Long.valueOf(tdpCadre[4].toString()):0L;
				Long roleId = tdpCadre[5] != null ? Long.valueOf(String.valueOf(tdpCadre[5]).trim()):0L ;
				AlertDataVO cadreVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(cadreCommitteeList,"id",id.toString());
				if(cadreVO != null)
				{
					String location = null;
					if(locationValue.longValue() > 0L){
						//System.out.println("tdpCadreId :"+id+"  \t positionName  :"+positionName);
						location = cadreCommitteeService.getLocationName(LocationTypeId,locationValue);
						cadreVO.setCommitteeLocation(location);
					    cadreVO.setCommitteePosition(positionName);
					    cadreVO.setCommitteeName(committeeName);
					    cadreVO.setElectionType(tdpCadre[6] != null ? tdpCadre[6].toString():"");
					    if(cadreVO.getElectionType().trim().equalsIgnoreCase("Panchayat"))
					    {
					    	 cadreVO.setElectionType("Village/Ward ");
					    }
					    else if(cadreVO.getElectionType().trim().equalsIgnoreCase("Mandal"))
					    {
					    	 cadreVO.setElectionType("Mandal/Division/Town");
					    }
					    cadreVO.setVoterId(roleId);
				    }
			   }
		    }
		}
	}
	/*
	 * Swadhin(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertService#getTotalAlertGroupByStatus(java.lang.String, java.lang.String, java.lang.Long)
	 */
	public List<AlertVO> getTotalAlertGroupByStatus(String fromDateStr, String toDateStr, Long stateId){
		LOG.info("Entered in getTotalAlertGroupByStatus() method of AlertService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			AlertVO alertVO = null;
			List<AlertVO> alertVOs = new ArrayList<AlertVO>();
			Map<Long,Long> statusIdAndCountMap = new HashMap<Long,Long>();
			//get all the alert status and build the template
			List<Object[]> statusList = alertStatusDAO.getAllStatus();
			if(statusList != null && statusList.size() > 0){
				for(Object[] param : statusList){
					alertVO = new AlertVO();
					alertVO.setStatusId(commonMethodsUtilService.getLongValueForObject(param[0]));
					alertVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[1]));
					alertVOs.add(alertVO);
				}
			}
			//get alert status count and and create a map of alertStatusId and its count
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByStatus(fromDate,toDate,stateId);
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
			//push the status count into list if count is 0 push 0 also
			if(alertVOs != null && alertVOs.size() > 0){
				for(AlertVO vo : alertVOs){
					if(statusIdAndCountMap.get(vo.getStatusId()) != null){
						vo.setCount(statusIdAndCountMap.get(vo.getStatusId()));
					}else{
						vo.setCount(0l);
					}
				}
			}
			return alertVOs; 
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getTotalAlertGroupByStatus() method of AlertService{}");
		}
		return null;
	}
	/*
	 * Swadhin(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertService#getTotalAlertGroupByStatusThenCategory(java.lang.String, java.lang.String, java.lang.Long)
	 */
	public List<AlertVO> getTotalAlertGroupByStatusThenCategory(String fromDateStr, String toDateStr, Long stateId){
		LOG.info("Entered in getTotalAlertGroupByStatusThenCategory() method of AlertService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			AlertVO alertVO = null;
			List<AlertVO> alertVOs = null;//new ArrayList<AlertVO>();
			Map<Long,Long> statusIdAndCountMap = new HashMap<Long,Long>();
			//get all the alert category for  building the template
			List<Object[]> categoryList = alertCategoryDAO.getAllCategory(); 
			
			//get alert status count and and create a map of alertStatusId and its corresponding  alert count
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByStatus(fromDate,toDate,stateId);
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
			//get all the alert count group by status then category.
			Map<Long,String> statusIdAndNameMap = new HashMap<Long,String>();
			Map<Long,Long> categoryIdAndCountMap = null;//new HashMap<Long, Long>();
			Map<Long,Map<Long,Long>> statusIdAndCategoryIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
			List<Object[]> alertCountGrpByCatList = alertDAO.getTotalAlertGroupByStatusThenCategory(fromDate, toDate, stateId);
			if(alertCountGrpByCatList != null && alertCountGrpByCatList.size() > 0){
				for(Object[] param : alertCountGrpByCatList){
					categoryIdAndCountMap = statusIdAndCategoryIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(categoryIdAndCountMap != null){
						categoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
					}else{
						categoryIdAndCountMap = new HashMap<Long, Long>();
						categoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
						statusIdAndCategoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),categoryIdAndCountMap);
					}
					statusIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
			//build final vo to sent to ui
			List<AlertVO> finalList = new ArrayList<AlertVO>();
			AlertVO innerListAlertVO = null;
			if(statusIdAndCategoryIdAndCountMap.size() > 0){
				for(Entry<Long,Map<Long,Long>> entry : statusIdAndCategoryIdAndCountMap.entrySet()){
					categoryIdAndCountMap = entry.getValue();
					if(categoryIdAndCountMap.size() > 0){
						if(categoryList != null && categoryList.size() > 0){
							alertVOs = new ArrayList<AlertVO>();
							innerListAlertVO = new AlertVO();
							for(Object[] param : categoryList){
								alertVO = new AlertVO();
								alertVO.setCategoryId(commonMethodsUtilService.getLongValueForObject(param[0]));
								alertVO.setCategory(commonMethodsUtilService.getStringValueForObject(param[1]));
								alertVOs.add(alertVO);  
							}
						}
						for(AlertVO param : alertVOs){
							if(categoryIdAndCountMap.get(param.getCategoryId()) != null){
								param.setCategoryCount(categoryIdAndCountMap.get(param.getCategoryId()));  
							}else{
								param.setCategoryCount(0l);
							}
						}
						innerListAlertVO.setSubList1(alertVOs);
						if(statusIdAndNameMap.get(entry.getKey()) != null){
							innerListAlertVO.setStatusId(entry.getKey());
							innerListAlertVO.setStatus(statusIdAndNameMap.get(entry.getKey()));
							
						}
						if(statusIdAndCountMap.get(entry.getKey()) != null){
							innerListAlertVO.setCount(statusIdAndCountMap.get(entry.getKey()));
						}
						finalList.add(innerListAlertVO);     
					}
				}
			}
			return finalList; 
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getTotalAlertGroupByStatusThenCategory() method of AlertService{}");
		}
		return null;
	}
	/*
	 * Swadhin(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertService#getAlertCountGroupByLocationThenStatus(java.lang.String, java.lang.String, java.lang.Long)
	 */
	public List<AlertVO> getAlertCountGroupByLocationThenStatus(String fromDateStr, String toDateStr, Long stateId){
		LOG.info("Entered in getAlertCountGroupByLocationThenStatus() method of AlertService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			AlertVO alertVO = null;
			List<AlertVO> alertVOs = null;//new ArrayList<AlertVO>();
			Map<Long,Long> impactLevelIdAndCountMap = new HashMap<Long,Long>();
			//get all the alert status for  building the template
			List<Object[]> statusList = alertStatusDAO.getAllStatus();
			//get alert status count and and create a map of impactLevelId and its corresponding alert count
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByImpactLevel(fromDate,toDate,stateId);
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					impactLevelIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
			//get all the alert count group by impact level then status wise.
			Map<Long,String> impactIdAndLevelMap = new HashMap<Long,String>();
			Map<Long,Long> statusIdAndCountMap = null;//new HashMap<Long, Long>();
			Map<Long,Map<Long,Long>> impactIdAndStatusIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
			List<Object[]> alertCountGrpByStatusList = alertDAO.getTotalAlertGroupByImpactLevelThenStatus(fromDate, toDate, stateId);
			if(alertCountGrpByStatusList != null && alertCountGrpByStatusList.size() > 0){  
				for(Object[] param : alertCountGrpByStatusList){  
					statusIdAndCountMap = impactIdAndStatusIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(statusIdAndCountMap != null){
						statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
					}else{
						statusIdAndCountMap = new HashMap<Long, Long>();
						statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
						impactIdAndStatusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),statusIdAndCountMap);
					}
					impactIdAndLevelMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
			//build final vo to sent to ui
			List<AlertVO> finalList = new ArrayList<AlertVO>();  
			AlertVO innerListAlertVO = null;
			if(impactIdAndStatusIdAndCountMap.size() > 0){
				for(Entry<Long,Map<Long,Long>> entry : impactIdAndStatusIdAndCountMap.entrySet()){
					statusIdAndCountMap = entry.getValue();
					if(statusIdAndCountMap.size() > 0){
						if(statusList != null && statusList.size() > 0){
							alertVOs = new ArrayList<AlertVO>();
							innerListAlertVO = new AlertVO();
							for(Object[] param : statusList){
								alertVO = new AlertVO();
								alertVO.setCategoryId(commonMethodsUtilService.getLongValueForObject(param[0]));//status is
								alertVO.setCategory(commonMethodsUtilService.getStringValueForObject(param[1]));//status name
								alertVOs.add(alertVO);  
							}
						}
						for(AlertVO param : alertVOs){
							if(statusIdAndCountMap.get(param.getCategoryId()) != null){
								param.setCategoryCount(statusIdAndCountMap.get(param.getCategoryId()));//status wise alert count
							}else{
								param.setCategoryCount(0l);//status wise alert count
							}
						}  
						innerListAlertVO.setSubList1(alertVOs);
						if(impactIdAndLevelMap.get(entry.getKey()) != null){  
							innerListAlertVO.setStatusId(entry.getKey());//impact level id
							innerListAlertVO.setStatus(impactIdAndLevelMap.get(entry.getKey()));//impact level
							
						}
						if(impactLevelIdAndCountMap.get(entry.getKey()) != null){
							innerListAlertVO.setCount(impactLevelIdAndCountMap.get(entry.getKey()));// total count
						}
						finalList.add(innerListAlertVO);     
					}
				}
				//merge village and ward
				Long impLvlId = 0L;
				String impLvl = "";
				Long impLvlCnt = 0L;
				Long stsCnt = 0l;
				List<AlertVO> mergedVo = new ArrayList<AlertVO>();
				Map<Long,String> statusIdAndNameMap = null;//new HashMap<Long,String>();
				Map<Long,Long> stsIdAndCountMap = null;//new HashMap<Long,Long>();
				if(finalList != null && finalList.size() > 0){
					//for template
					for(Object[] param : statusList){
						alertVO = new AlertVO();
						alertVO.setCategoryId(commonMethodsUtilService.getLongValueForObject(param[0]));//status is
						alertVO.setCategory(commonMethodsUtilService.getStringValueForObject(param[1]));//status name
						mergedVo.add(alertVO);  
					}
					
					for(AlertVO param : finalList){
						if(param.getStatusId().longValue() == 6L || param.getStatusId().longValue() == 8L){
							impLvlId = 11L;
							impLvl = "VILLAGE/WARD";
							impLvlCnt = impLvlCnt + param.getCount();
							statusIdAndNameMap = new HashMap<Long,String>();
							stsIdAndCountMap = new HashMap<Long,Long>();
							for(AlertVO param2 : param.getSubList1()){
								statusIdAndNameMap.put(param2.getCategoryId(), param2.getCategory());
								stsIdAndCountMap.put(param2.getCategoryId(), param2.getCategoryCount());
							}
							
							//push status count
							for(AlertVO param3 : mergedVo){
								if(stsIdAndCountMap.get(param3.getCategoryId()) != null){
									param3.setCategoryCount(param3.getCategoryCount() + stsIdAndCountMap.get(param3.getCategoryId()));//status wise alert count
								}else{
									param3.setCategoryCount(0l);//status wise alert count
								}  
							}
						}
					}
					if(impLvlId.longValue() == 11L){
						AlertVO newAlertVO = new AlertVO();
						newAlertVO.setStatusId(impLvlId);
						newAlertVO.setStatus(impLvl);
						newAlertVO.setCount(impLvlCnt);
						newAlertVO.setSubList1(mergedVo);
						finalList.add(newAlertVO);
					}
				}
			}
			return finalList;   
		}catch(Exception e){
			e.printStackTrace();  
			LOG.error("Error occured getAlertCountGroupByLocationThenStatus() method of AlertService{}");
		}
		return null;
	}
	
	public String  setArticleDetailsIntoAlert(final ActionableVO inputVO){		
		String resultStatus = "";
		try{				
			resultStatus = (String) transactionTemplate
					.execute(new TransactionCallback() {
						public Object doInTransaction(TransactionStatus status) {
							String rs = new String();
								 Alert alert = null;
								if(inputVO !=null && inputVO.getType().trim().equalsIgnoreCase("updateStatus")){
									updateAlertForNewsInUpdateStatus(inputVO,null);
								}else if(inputVO !=null && inputVO.getType().trim().equalsIgnoreCase("update")){
									//updateAlertForNewsInUpdateStatus(inputVO);				
									List<Alert> alertList = alertDAO.getAlertDetailsOfNewstype(inputVO.getId());				
									alert = alertList.get(0);
									updateAlertForNewsInUpdateStatus(inputVO,alert);				
								}else{
									updateAlertForNewsInUpdateStatus(inputVO,new Alert());
								}								
							return "success";
						}
						
					});
			 
		}catch(Exception e){
			resultStatus = "failure";
			e.printStackTrace();
			LOG.error("Entered in setArticleDetailsIntoAlert() method");
		}
		return resultStatus; 
		
	}
	
	public void updateAlertForNewsInUpdateStatus(final ActionableVO inputVO,final Alert oldAlert){
		try {
			
			if(oldAlert == null){
				int updateAlert = alertDAO.updateAlertStatusOfNews(inputVO.getId(),inputVO.getStatusId());
			}
			else{
				String result = (String) transactionTemplate.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
					//else{
										
						Alert alert = null;
						
						if(oldAlert.getAlertId() !=null && oldAlert.getAlertId()>0){
							alert = oldAlert;
						}else{
							alert = new Alert();
						}
						
						 if(inputVO.getType() !=null && inputVO.getType().equalsIgnoreCase("save")){
							 List<Alert> alertList  = alertDAO.getAlertDetailsOfNewstype(inputVO.getId());
							 if(alertList !=null && alertList.size()>0){
								 alert = alertList.get(0);
							 }
							 
							 alert.setCreatedTime(inputVO.getInsertedTime());
							 alert.setAlertStatusId(inputVO.getStatusId());
							 alert.setCreatedBy(inputVO.getUserId());
							 
						 }				 
						 alert.setAlertTypeId(inputVO.getAlertType());
						 
						 setImpactId(alert,inputVO);
						 
						 //alert.setImpactLevelId(inputVO.getRegionScopeId());
						 alert.setImpactLevelValue(inputVO.getRegionScopeValue());
						 alert.setDescription(inputVO.getDesc());	
						 alert.setTitle(inputVO.getTitle());
						 alert.setUpdatedBy(inputVO.getUserId());
						 alert.setAlertSourceId(3l);				 
						 alert.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						 alert.setAlertCategoryId(inputVO.getAlertCategory());
						 alert.setIsDeleted("N");
						 alert.setAlertCategoryTypeId(inputVO.getId());
						 
						UserAddress UA = new UserAddress();
							
						UA.setState(stateDAO.get(inputVO.getStateId()));
						UA.setDistrict(inputVO.getDistrictId()!=null?districtDAO.get(inputVO.getDistrictId()):null);
						UA.setConstituency(inputVO.getConstituencyId() !=null ? constituencyDAO.get(inputVO.getConstituencyId()):null);
						UA.setTehsil(inputVO.getMandalId() !=null ? tehsilDAO.get(inputVO.getMandalId()):null);
						UA.setLocalElectionBody(inputVO.getMunicipalCorpGmcId() !=null ? localElectionBodyDAO.get(inputVO.getMunicipalCorpGmcId()):null);
						UA.setWard(inputVO.getWardId() !=null ? constituencyDAO.get(inputVO.getWardId()):null);
						UA.setParliamentConstituency(inputVO.getParliamentId() !=null ? constituencyDAO.get(inputVO.getParliamentId()):null);
						UA.setPanchayat(inputVO.getPanchayatId() !=null ? panchayatDAO.get(inputVO.getPanchayatId()):null);
						 
						UserAddress userAddressNew = userAddressDAO.save(UA); 
						
						alert.setAddressId(userAddressNew.getUserAddressId() !=null ?
								userAddressNew.getUserAddressId().longValue():null);
						
						 alert = alertDAO.save(alert);
						 
						 //Saving into Alert Candidate For Print and Electronic Media
						 if(inputVO.getActionableVoList() != null && inputVO.getActionableVoList().size() > 0)
						 {
							 
							//get TdpCadre Details by candidateId
							 Set<Long> candidateIds = new HashSet<Long>();
							 for(ActionableVO vo : inputVO.getActionableVoList()) {
								 if(vo.getCandidateId() !=null && vo.getCandidateId()>0l){
									 candidateIds.add(vo.getCandidateId());
								 }						
							}
							//candidateId,cadreId
							List<Object[]> tdpCadres = tdpCadreCandidateDAO.getTdpCadreIdsOfCandidates(candidateIds);
							 
							 Map<Long,Long> tdpcadreMap = new HashMap<Long, Long>(0);
							 if(tdpCadres !=null && tdpCadres.size()>0){
								 for (Object[] obj : tdpCadres) {							
									 tdpcadreMap.put((Long)obj[0], obj[1] !=null ? (Long)obj[1]:null);
								}
							 }
							 
							 
							for(ActionableVO vo : inputVO.getActionableVoList())
							 {
								 if(vo != null && vo.getId()!= null && vo.getId() > 0)
								 {
									 AlertCandidate alertCandidate = new AlertCandidate();
									 alertCandidate.setAlertId(alert.getAlertId());
									 alertCandidate.setAlertImpactId(vo.getBenefitId());
									 alertCandidate.setCandidateId(vo.getCandidateId());
									 if(vo.getCandidateId() !=null && vo.getCandidateId()>0l){
										 alertCandidate.setTdpCadreId(tdpcadreMap.get(vo.getCandidateId()));
									 }							
									 alertCandidateDAO.save(alertCandidate);
								 }						
							 }
						 }
					return "success";
			      }
				});
			}
		} catch (Exception e) {
			LOG.error("error in updateAlertForNewsInUpdateStatus() method");
		}
	}
	
	public ActionableVO setImpactId(Alert alert,ActionableVO VO){
		try {
			if(VO.getRegionScopeId() !=null && VO.getRegionScopeId()>0l){
				if(VO.getRegionScopeId() ==1l){
					alert.setImpactLevelId(2l);
				}else if(VO.getRegionScopeId() ==2l){
					alert.setImpactLevelId(3l);
				}else if(VO.getRegionScopeId() ==3l){
					alert.setImpactLevelId(4l);
				}if(VO.getRegionScopeId() ==4l){
					alert.setImpactLevelId(10l);
				}if(VO.getRegionScopeId() ==5l){
					alert.setImpactLevelId(5l);
				}if(VO.getRegionScopeId() ==6l){
					alert.setImpactLevelId(6l);
				}if(VO.getRegionScopeId() ==8l){
					alert.setImpactLevelId(7l);
				}if(VO.getRegionScopeId() ==9l){
					alert.setImpactLevelId(8l);
				}
			}
			
		}catch (Exception e) {
			LOG.error("error in setImpactId() method ");
		}
		return VO;
	}
	
}
	

