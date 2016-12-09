package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IAlertAssignedDAO;
import com.itgrids.partyanalyst.dao.IAlertCandidateDAO;
import com.itgrids.partyanalyst.dao.IAlertCategoryDAO;
import com.itgrids.partyanalyst.dao.IAlertCommentAssigneeDAO;
import com.itgrids.partyanalyst.dao.IAlertCommentDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertImpactScopeDAO;
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
import com.itgrids.partyanalyst.dao.IParliamentAssemblyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.impl.IAlertSourceUserDAO;
import com.itgrids.partyanalyst.dto.ActionableVO;
import com.itgrids.partyanalyst.dto.AlertCommentVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertDataVO;
import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.dto.AlertOverviewVO;
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
private IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
private IParliamentAssemblyDAO parliamentAssemblyDAO; 
private IAlertImpactScopeDAO alertImpactScopeDAO;



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
public void setActivityMemberAccessLevelDAO(
		IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO) {
	this.activityMemberAccessLevelDAO = activityMemberAccessLevelDAO;
}
public void setParliamentAssemblyDAO(
		IParliamentAssemblyDAO parliamentAssemblyDAO) {
	this.parliamentAssemblyDAO = parliamentAssemblyDAO;
}
public void setAlertImpactScopeDAO(IAlertImpactScopeDAO alertImpactScopeDAO) {
	this.alertImpactScopeDAO = alertImpactScopeDAO;
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
				 alert.setImpactScopeId(inputVO.getAlertImpactId());

				 if(inputVO.getAssignList() != null && inputVO.getAssignList().size() > 0)
					 alert.setAlertStatusId(2l);// if assign list given default status is notified
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
				// alert.setAlertCategoryTypeId(inputVO.getCategoryId());
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
				 if(inputVO.getAssignList() != null && inputVO.getAssignList().size() > 0)
				 {
					 alertTrackingVO.setAlertStatusId(2l);
				 }else{
					 alertTrackingVO.setAlertStatusId(1l);
				 }
				 
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
				if(alertTrackingVO.getAlertStatusId() !=null){
					alertTracking.setAlertStatusId(alertTrackingVO.getAlertStatusId());
				}				
				if(alertTrackingVO.getAlertCommentId() !=null){
					alertTracking.setAlertCommentId(alertTrackingVO.getAlertCommentId());
				}
				/*if(alertTrackingVO.getAlertUserTypeId() !=null && alertTrackingVO.getAlertUserTypeId().longValue()>0l){
					alertTracking.setAlertSourceId(alertTrackingVO.getAlertUserTypeId());
				}*/				
				if(alertTrackingVO.getUserId() !=null){
					alertTracking.setInsertedBy(alertTrackingVO.getUserId());
				}				
				alertTracking.setInsertedTime(currentDateAndTime);
				if(alertTrackingVO.getAlertTrackingActionId() !=null && alertTrackingVO.getAlertTrackingActionId().longValue()>0l){
					alertTracking.setAlertTrackingActionId(alertTrackingVO.getAlertTrackingActionId());
				}
				
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
				 
				 Map<Long,Long> alertCategoryMap = new HashMap<Long, Long>();
				 
				 for(Object[] params : list)
				 {
					 AlertDataVO alertVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "id", params[0].toString());
					 if(alertVO == null)
					 {
						 alertVO = new AlertDataVO(); 
						 returnList.add(alertVO);
						 if(!alertIds.contains((Long)params[0]))
							 alertIds.add((Long)params[0]);
					 }
					 alertVO.setId((Long)params[0]);
					 alertVO.setTitle(params[25] != null ? params[25].toString() : "");
					 alertVO.setDesc(params[1].toString());
					 alertVO.setDate(params[2] != null? params[2].toString():"");
					 alertVO.setAlertType(params[3] != null ? params[3].toString() : "");
					 alertVO.setUserType(params[4] != null ? params[4].toString() : "");
					 alertVO.setSeverity(params[5] != null ? params[5].toString() : "");
					 alertVO.setRegionScopeId(params[6] != null ? (Long)params[6] : null);
					 alertVO.setRegionScope(params[26] != null ?params[26].toString() : "");
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
					 
					 alertVO.setCategoryId(params[27] != null ? (Long)params[27] : null);
					 alertVO.setCategory(params[28] != null ? params[28].toString() : "");
					 alertVO.setImageUrl(params[29] != null ? params[29].toString() : "");
					 alertVO.setAlertCategoryTypeId(params[30] != null ? (Long)params[30] : null);
					 
					 String eleType = params[18] != null ? params[18].toString() : "";
					 locationVO.setLocalEleBodyName(params[15] != null ? params[15].toString() +" "+eleType : "");
					 
					 //category
					 alertCategoryMap.put((Long)params[0], alertVO.getCategoryId());
					 
					alertVO.setLocationVO(locationVO);
					 
					
				 }
				 if(alertIds != null && alertIds.size() > 0)
				 {
					 List<Object[]> candiateCnts = null;
					 if(alertCategoryMap.get(alertId) !=null && alertCategoryMap.get(alertId)>0l && alertCategoryMap.get(alertId) !=1l){
						
						 //0.alertId,1.candidateId,2.candidateName,3.designation,4.organization,5.impactId,6.impact,7.paCandidateId
						 List<Long> aleds = new ArrayList<Long>();
						 aleds.add(alertId);
						 List<Object[]> newsAlertCandidates = alertCandidateDAO.getInvolvedCandidateDetailsOfAlert(aleds);
						 setNewsAlertCandidateData(newsAlertCandidates,returnList);
						 
						 //total Involved Candidates
						 candiateCnts = alertCandidateDAO.getAlertNewsCandidateCount(alertIds);
						 
					 }else{						 
						 List<Object[]> alertCandidates = alertCandidateDAO.getAlertCandidatesData(alertIds);
						 setAlertCandidateData(alertCandidates,returnList);
						 
						 //total Involved Candidates
						 candiateCnts = alertCandidateDAO.getAlertCandidateCount(alertIds);
						 
					 }
					 	
					if(candiateCnts !=null && candiateCnts.size()>0){
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
		
	}
	
	 //0.alertId,1.candidateId,2.candidateName,3.designation,4.organization,5.impactId,6.impact,7.paCandidateId
	public void setNewsAlertCandidateData(List<Object[]> list , List<AlertDataVO> dataList){
		try{
			
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
					AlertDataVO candidateVO = null;
					if(params[1] !=null){
						candidateVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(alertVo.getSubList(), "id", params[1].toString());
					}
					
					if(candidateVO == null)
					{
						candidateVO = new AlertDataVO();
						alertVo.getSubList().add(candidateVO);
					}
					
					candidateVO.setId(params[1] !=null ? (Long)params[1]:null);
					candidateVO.setName(params[2] !=null ? params[2].toString():"");
					candidateVO.setCommitteePosition(params[3] !=null ? params[3].toString():"");//designation
					
					candidateVO.setOrganization(params[4] !=null ? params[4].toString():"");
					candidateVO.setImpactId(params[5] !=null ? (Long)params[5]:null);
					candidateVO.setImpact(params[6] !=null ? params[6].toString():"");
					
					candidateVO.setCategoryId(params[7] !=null ? (Long)params[7]:null);//PaCandidateId
					
				}
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
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
		List<Long> alertIdsNews = new ArrayList<Long>();
		 if(list != null && list.size() > 0)
		 {
			 for(Object[] params : list)
			 {
				 AlertDataVO alertVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "id", params[0].toString());
				 if(alertVO == null)
				 {
					 alertVO = new AlertDataVO(); 
					 returnList.add(alertVO);
					 
					 alertVO.setAlertCategoryId(commonMethodsUtilService.getLongValueForObject(params[25]));
					 
					 if(alertVO.getAlertCategoryId() !=null && alertVO.getAlertCategoryId().longValue()>1l){
						 if(!alertIdsNews.contains((Long)params[0]));
						 alertIdsNews.add((Long)params[0]);
					 }else{
						 if(!alertIds.contains((Long)params[0]));
						 alertIds.add((Long)params[0]);
					 }
					
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
				// alertVO.setAlertCategoryId(commonMethodsUtilService.getLongValueForObject(params[25]));
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
			 if(alertIdsNews !=null && alertIdsNews.size()>0){
				 //total Involved Candidates
				 List<Object[]> candiateCnts = alertCandidateDAO.getAlertNewsCandidateCount(alertIdsNews);
				 for(Object[] params : candiateCnts)
				 {
					 AlertDataVO alertVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "id", params[1].toString());
						 if(alertVO != null)
						 {
							 alertVO.setCount((Long)params[0]);
						 }
				 }
				 //0.alertId,1.candidateId,2.candidateName,3.designation,4.organization,5.impactId,6.impact,7.paCandidateId
				 List<Object[]> newsAlertCandidates = alertCandidateDAO.getInvolvedCandidateDetailsOfAlert(alertIdsNews);
				 setNewsAlertCandidateData(newsAlertCandidates,returnList);
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
			alert.setUpdatedTime(currentDateAndTime);    
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
	/*public List<StatusTrackingVO> getAlertStatusCommentsTrackingDetails(Long alertId){
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
	}*/
	public List<AlertCommentVO> getAlertStatusCommentsTrackingDetails(Long alertId){
		LOG.info("Entered in getAlertStatusCommentsTrackingDetails() method");
		List<StatusTrackingVO> resultList = null;
		try{
			Map<Long,String> idAndNameMap = new HashMap<Long,String>();
			
			Map<Long,Set<String>> statusIdAndDateIdListMap = new LinkedHashMap<Long,Set<String>>();
			Set<String> dateIdList = null;//new HashSet<String>(); 
			
			Map<String,Set<Long>> dateIdAndCmtListMap = new HashMap<String,Set<Long>>();
			Set<Long> commentIdList = null;
			
			Map<Long,List<AlertCommentVO>> commentIdAndCommentDtlsMap = new HashMap<Long,List<AlertCommentVO>>();
			List<AlertCommentVO>  alertCommentDtlsList = null;
			AlertCommentVO alertCommentVO = null;
			List<Object[]> list = alertTrackingDAO.getAlertTrackingDetailsList(alertId);
			
			
			SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm:ss");
			SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
			if(list != null && list.size() > 0){   
				for(Object[] param : list){
					
					//for statusId and date list map
					dateIdList = statusIdAndDateIdListMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
					if(dateIdList != null){
						dateIdList.add(commonMethodsUtilService.getStringValueForObject(param[2])+":"+commonMethodsUtilService.getStringValueForObject(param[1]));
					}else{
						dateIdList = new LinkedHashSet<String>();  
						dateIdList.add(commonMethodsUtilService.getStringValueForObject(param[2])+":"+commonMethodsUtilService.getStringValueForObject(param[1]));
						statusIdAndDateIdListMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), dateIdList);
					}
					//for dateId and list of comment id list
					commentIdList = dateIdAndCmtListMap.get(commonMethodsUtilService.getStringValueForObject(param[2])+":"+commonMethodsUtilService.getStringValueForObject(param[1]));
					if(commentIdList != null){
						commentIdList.add(commonMethodsUtilService.getLongValueForObject(param[4]));
					}else{
						commentIdList = new HashSet<Long>();
						commentIdList.add(commonMethodsUtilService.getLongValueForObject(param[4]));
						dateIdAndCmtListMap.put(commonMethodsUtilService.getStringValueForObject(param[2])+":"+commonMethodsUtilService.getStringValueForObject(param[1]), commentIdList);
					}  
					
					//for commentId and comment Dtls list map
					alertCommentDtlsList = commentIdAndCommentDtlsMap.get(commonMethodsUtilService.getLongValueForObject(param[4]));
					if(alertCommentDtlsList != null){   
						alertCommentVO = new AlertCommentVO();
						alertCommentVO.setCommentId(commonMethodsUtilService.getLongValueForObject(param[4]));
						alertCommentVO.setComment(commonMethodsUtilService.getStringValueForObject(param[5]));
						if(param[2] != null){
							Date _24HourDt = _24HourSDF.parse(commonMethodsUtilService.getStringValueForObject(param[3]));
							alertCommentVO.setTimeString(_12HourSDF.format(_24HourDt));
						}
						alertCommentVO.setCadreName(commonMethodsUtilService.getStringValueForObject(param[7]));
						alertCommentVO.setUserName(commonMethodsUtilService.getStringValueForObject(param[8]));
						alertCommentDtlsList.add(alertCommentVO);
					}else{
						alertCommentVO = new AlertCommentVO();
						alertCommentVO.setCommentId(commonMethodsUtilService.getLongValueForObject(param[4]));
						alertCommentVO.setComment(commonMethodsUtilService.getStringValueForObject(param[5]));
						if(param[2] != null){
							Date _24HourDt = _24HourSDF.parse(commonMethodsUtilService.getStringValueForObject(param[3]));
							alertCommentVO.setTimeString(_12HourSDF.format(_24HourDt));
						}
						alertCommentVO.setCadreName(commonMethodsUtilService.getStringValueForObject(param[7]));
						alertCommentVO.setUserName(commonMethodsUtilService.getStringValueForObject(param[8]));
						alertCommentDtlsList = new ArrayList<AlertCommentVO>();
						alertCommentDtlsList.add(alertCommentVO);
						commentIdAndCommentDtlsMap.put(commonMethodsUtilService.getLongValueForObject(param[4]), alertCommentDtlsList);
					}
					idAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getStringValueForObject(param[9]));
				}
			}
			AlertCommentVO commentVO = null;
			//vo for each date
			AlertCommentVO commentVOForDate = null;
			List<AlertCommentVO> commentVOForDateList = null;
			//for multiuser involvement
			List<List<AlertCommentVO>> list2 = null;
			//final vo 
			List<AlertCommentVO> finalList = new CopyOnWriteArrayList<AlertCommentVO>();  
			if(statusIdAndDateIdListMap.size() > 0){  
				for(Entry<Long,Set<String>> entry : statusIdAndDateIdListMap.entrySet()){
					commentVO = new AlertCommentVO();
					commentVO.setStatusId(entry.getKey());
					commentVO.setStatus(idAndNameMap.get(entry.getKey()));
					dateIdList = (LinkedHashSet)entry.getValue();    
					if(dateIdList != null && dateIdList.size() > 0){
						commentVOForDateList = new ArrayList<AlertCommentVO>();
						for(String dateId : dateIdList){
							commentVOForDate = new AlertCommentVO();
							commentVOForDate.setDate(dateId.split(":")[0]);
							commentIdList = dateIdAndCmtListMap.get(dateId);
							if(commentIdList != null && commentIdList.size() > 0){
								list2 = new ArrayList<List<AlertCommentVO>>();
								for(Long cmtId : commentIdList){
									list2.add(commentIdAndCommentDtlsMap.get(cmtId));
								}
								//Collections.sort(list2, commentSort);
								commentVOForDate.setSublist(list2);  
							}
							commentVOForDateList.add(commentVOForDate);
						}
					}
					commentVO.setSublist2(commentVOForDateList);
					finalList.add(commentVO);
				}
			}
			if(finalList != null && finalList.size() > 0){
				List<Long> statusIdList = alertTrackingDAO.lastUpdatedstatus(alertId);
				for(AlertCommentVO param : finalList){  
					if(param.getStatusId().longValue() == statusIdList.get(0)){
						finalList.remove(param);
						finalList.add(param);  
					}
				}
			}  
			
			return finalList;   		
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Entered in getAppointmentStatusFlowTrackingDetails() method");
		}
		return null;
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
			setAlertAssignedCandidateDataNew(list,dataList);
		
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
	
	public void setAlertAssignedCandidateDataNew(List<Object[]> list,List<AlertDataVO> dataList)

	{
		List<Long> alertIds = new ArrayList<Long>();
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
					alertIds.add(alertVo.getId());
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
			 
			 
			 //alertCommentDetails
			 
			 Map<Long,String> commentMap = new HashMap<Long, String>(); 
			 if(alertIds !=null && alertIds.size()>0 && alertIds.get(0) !=null && tdpCadreIdsList !=null && tdpCadreIdsList.size() >0){
				 //0.tdpCadreId,1.comment
				 List<Object[]> alertCommentObj = alertCommentAssigneeDAO.getAssignedCandidateAlertComment(alertIds.get(0), tdpCadreIdsList);
				 if(alertCommentObj !=null && alertCommentObj.size()>0){
					 for (Object[] obj : alertCommentObj) {						 
						 String comment = commentMap.get(obj[0] !=null ? (Long)obj[0]:null);						 
						 if(comment == null){
							 comment = new String();
							 comment=obj[1] !=null ? obj[1].toString():"";
							 commentMap.put((Long)obj[0], comment);							 
						 }else{
							 comment=comment+","+obj[1] !=null ? obj[1].toString():"";
						 }
					}
				 }
			 }
				 
			 if(commentMap !=null && commentMap.size()>0){
				 AlertDataVO alertVo =(AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(dataList, "id", alertIds.get(0).toString());
				 if(alertVo !=null){					 
					 for (Entry<Long, String> tdpCadre : commentMap.entrySet()) {
						 AlertDataVO candidateVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(alertVo.getSubList(), "id",tdpCadre.getKey().toString());						 
						 if(candidateVO !=null){
							 candidateVO.setComment(tdpCadre.getValue());
						 }						 
					}
				 }
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
	public List<AlertVO> getTotalAlertGroupByStatus(String fromDateStr, String toDateStr, Long stateId,Long alertTypeId){
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
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByStatus(fromDate,toDate,stateId,alertTypeId);
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
	public List<AlertVO> getTotalAlertGroupByStatusThenCategory(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId){
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
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByStatus(fromDate,toDate,stateId,alertTypeId);
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
			//get all the alert count group by status then category.
			Map<Long,String> statusIdAndNameMap = new HashMap<Long,String>();
			Map<Long,Long> categoryIdAndCountMap = null;//new HashMap<Long, Long>();
			Map<Long,Map<Long,Long>> statusIdAndCategoryIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
			List<Object[]> alertCountGrpByCatList = alertDAO.getTotalAlertGroupByStatusThenCategory(fromDate, toDate, stateId, alertTypeId);
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
	public List<AlertVO> getAlertCountGroupByLocationThenStatus(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId){
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
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByImpactLevel(fromDate,toDate,stateId,alertTypeId);
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					impactLevelIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
			//get all the alert count group by impact level then status wise.
			Map<Long,String> impactIdAndLevelMap = new HashMap<Long,String>();
			Map<Long,Long> statusIdAndCountMap = null;//new HashMap<Long, Long>();
			Map<Long,Map<Long,Long>> impactIdAndStatusIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
			List<Object[]> alertCountGrpByStatusList = alertDAO.getTotalAlertGroupByImpactLevelThenStatus(fromDate, toDate, stateId, alertTypeId);
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
						if(param.getStatusId().longValue() == 7L || param.getStatusId().longValue() == 9L){
							impLvlId = 12L;
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
					if(impLvlId.longValue() == 12L){
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
							// alert.setCreatedBy(inputVO.getUserId());
							 
						 }				 
						 alert.setAlertTypeId(inputVO.getAlertType());
						 
						 setImpactId(alert,inputVO);
						 
						 //alert.setImpactLevelId(inputVO.getRegionScopeId());
						 
						 if(alert.getImpactLevelId() !=  null &&alert.getImpactLevelId().longValue() ==3L && 
						inputVO.getDistrictId() != null && inputVO.getDistrictId().longValue()>0L){ //only new district ids
							if(inputVO.getDistrictId().longValue() == IConstants.CNP_VISHAKAPATTANAM_RURAL_DISTRICT_ID)
								inputVO.setDistrictId(517L);
							if(inputVO.getDistrictId().longValue() == IConstants.CNP_MANCHERIAL_DISTRICT_ID)
								inputVO.setDistrictId(518L);
							
								inputVO.setRegionScopeValue(inputVO.getDistrictId());
						 }
						 
						 alert.setImpactLevelValue(inputVO.getRegionScopeValue());
						 alert.setDescription(inputVO.getDesc());	
						 alert.setTitle(inputVO.getTitle());
						// alert.setUpdatedBy(inputVO.getUserId());
						 alert.setAlertSourceId(3l);				 
						 alert.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						 alert.setAlertCategoryId(inputVO.getAlertCategory());
						 alert.setIsDeleted("N");
						 alert.setAlertCategoryTypeId(inputVO.getId());
						 alert.setImpactScopeId(inputVO.getImpactScopeId());
						 alert.setAlertSeverityId(2l);
						 
						UserAddress UA = new UserAddress();
							 
						UA.setState(inputVO.getStateId() !=null ? stateDAO.get(inputVO.getStateId()):null);
						UA.setDistrict(inputVO.getDistrictId()!=null?districtDAO.get(inputVO.getDistrictId()):null);
						UA.setConstituency(inputVO.getConstituencyId() !=null ? constituencyDAO.get(inputVO.getConstituencyId()):null);
						UA.setTehsil(inputVO.getMandalId() !=null ? tehsilDAO.get(inputVO.getMandalId()):null);
						UA.setLocalElectionBody(inputVO.getMunicipalCorpGmcId() !=null ? localElectionBodyDAO.get(inputVO.getMunicipalCorpGmcId()):null);
						UA.setWard(inputVO.getWardId() !=null ? constituencyDAO.get(inputVO.getWardId()):null);
						UA.setParliamentConstituency(inputVO.getParliamentId() !=null ? constituencyDAO.get(inputVO.getParliamentId()):null);
						UA.setPanchayatId(inputVO.getPanchayatId() !=null ? inputVO.getPanchayatId():null);
						 
						UserAddress userAddressNew = null;
						if(alert.getImpactLevelId() !=null && alert.getImpactLevelId() >0l){
							userAddressNew = userAddressDAO.save(UA); 
						}
						
						if(userAddressNew !=null){
							alert.setAddressId(userAddressNew.getUserAddressId() !=null ?
									userAddressNew.getUserAddressId().longValue():null);
						}						
						
						alert.setImageUrl(inputVO.getImageUrl() !=null ? inputVO.getImageUrl():null);
						
						 alert = alertDAO.save(alert);
						 
						 AlertTrackingVO alertTrackingVO = new AlertTrackingVO();
						 alertTrackingVO.setAlertUserTypeId(alert.getAlertSourceId());
						 alertTrackingVO.setAlertStatusId(alert.getAlertStatusId());
						 alertTrackingVO.setAlertId(alert.getAlertId());
						 alertTrackingVO.setAlertTrackingActionId(IConstants.ALERT_ACTION_STATUS_CHANGE);
						 saveAlertTrackingDetails(alertTrackingVO)	;
						 
						 
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
							//paCandidateId,cadreId
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
									 alertCandidate.setCandidateId(vo.getCandidateId());//pacandidate
									 if(vo.getCandidateId() !=null && vo.getCandidateId()>0l){
										 alertCandidate.setTdpCadreId(tdpcadreMap.get(vo.getCandidateId()));
									 }
									 
									 alertCandidate.setNewsCandidateId(vo.getNewsCandidateId());
									 alertCandidate.setNewsCandidate(vo.getNewsCandidate() !=null ? vo.getNewsCandidate():"");
									 
									 if(vo.getDesignationList() !=null && vo.getDesignationList().size()>0){
										 for (String designation : vo.getDesignationList()) {
											 if(alertCandidate.getDesignation() !=null && !alertCandidate.getDesignation().trim().isEmpty()){
												 alertCandidate.setDesignation(alertCandidate.getDesignation()+","+designation);
											 }else{
												 alertCandidate.setDesignation(designation);
											 }
											
										}										 
									 }
									 alertCandidate.setOrganization(vo.getOrganization());
								
									 alertCandidateDAO.save(alertCandidate);
								 }						
							 }
						 }
					return "success";
			      }
				});
			}
		} catch (Exception e) {
			LOG.error("error in updateAlertForNewsInUpdateStatus() method",e);
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
	/*
	 * Swadhin(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertService#getTotalAlertGroupByStatusThenCategory(java.lang.String, java.lang.String, java.lang.Long)
	 */
	public List<AlertVO> getTotalAlertGroupByStatusThenCategoryLocationWise(String fromDateStr, String toDateStr, Long stateId, String Location, Long alertTypeId){
		LOG.info("Entered in getTotalAlertGroupByStatusThenCategory() method of AlertService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			//get all the location id list.
			List<Object[]> locationIdAndName1 = null;
			List<Object[]> locationIdAndName2 = null;
			if(Location.equalsIgnoreCase("village")){
				locationIdAndName1 = alertDAO.getLocationIdList(fromDate, toDate, stateId, Location, alertTypeId);
				locationIdAndName2 = alertDAO.getLocationIdList(fromDate, toDate, stateId, Location, alertTypeId);
				if(locationIdAndName2 != null && locationIdAndName2.size() > 0){
					if(locationIdAndName1 != null){
						locationIdAndName1.addAll(locationIdAndName2);
					}else{
						locationIdAndName1 = new ArrayList<Object[]>();
						locationIdAndName1.addAll(locationIdAndName2);
					}
				}
			}else{
				locationIdAndName1 = alertDAO.getLocationIdList(fromDate, toDate, stateId, Location, alertTypeId);
			}
			//all location values
			List<Long> locaionIds = new ArrayList<Long>();
			if(locationIdAndName1 != null && locationIdAndName1.size() > 0){
				for(Object[] param : locationIdAndName1){
					locaionIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));    
				}
			}
			
			//create a map for locationId and name 
			Map<Long,String> locationIdAndNameMap = new HashMap<Long,String>();
			if(locationIdAndName1 != null && locationIdAndName1.size() > 0){
				for(Object[] param : locationIdAndName1){
					if(param[0] != null)
						locationIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
			AlertVO alertVO = null;
			List<AlertVO> alertVOs = null;//new ArrayList<AlertVO>();
			Map<Long,Long> statusIdAndCountMap = new HashMap<Long,Long>();
			//get all the alert category for  building the template
			List<Object[]> categoryList = alertCategoryDAO.getAllCategory(); 
			
			//get all the alert count group by Location then status then category.
			Map<Long,Map<Long,String>> locationIdAndStatusIdAndSatatusMap = new LinkedHashMap<Long,Map<Long,String>>();
			Map<Long,String> statusIdAndStatusNameMap = null;//new LinkedHashMap<Long,String>();
			
			Map<Long,String> statusIdAndNameMap = new HashMap<Long,String>();
			Map<Long,Long> categoryIdAndCountMap = null;//new HashMap<Long, Long>();
			Map<Long,Map<Long,Long>> statusIdAndCategoryIdAndCountMap = null;//new HashMap<Long,Map<Long,Long>>();
			Map<Long,Map<Long,Map<Long,Long>>> locationIdAndStatusIdAndCategoryIdAndCountMap = new LinkedHashMap<Long,Map<Long,Map<Long,Long>>>();
			if(locaionIds.size() > 0){
				List<Object[]> alertCountGrpBylocationIdAndStatusIdAndCategoryId1 = null;
				List<Object[]> alertCountGrpBylocationIdAndStatusIdAndCategoryId2 = null;
				if(Location.equalsIgnoreCase("village")){
					alertCountGrpBylocationIdAndStatusIdAndCategoryId1 = alertDAO.getAlertCountGrpBylocationIdAndStatusIdAndCategoryId(fromDate, toDate, stateId, locaionIds, "village",alertTypeId);
					alertCountGrpBylocationIdAndStatusIdAndCategoryId2 = alertDAO.getAlertCountGrpBylocationIdAndStatusIdAndCategoryId(fromDate, toDate, stateId, locaionIds, "ward",alertTypeId);
					if(alertCountGrpBylocationIdAndStatusIdAndCategoryId2 != null && alertCountGrpBylocationIdAndStatusIdAndCategoryId2.size() > 0){
						if(alertCountGrpBylocationIdAndStatusIdAndCategoryId1 != null){
							alertCountGrpBylocationIdAndStatusIdAndCategoryId1.addAll(alertCountGrpBylocationIdAndStatusIdAndCategoryId2);
						}else{
							alertCountGrpBylocationIdAndStatusIdAndCategoryId1 = new ArrayList<Object[]>();
							alertCountGrpBylocationIdAndStatusIdAndCategoryId1.addAll(alertCountGrpBylocationIdAndStatusIdAndCategoryId2);
						}
					}
				}else{
					alertCountGrpBylocationIdAndStatusIdAndCategoryId1 = alertDAO.getAlertCountGrpBylocationIdAndStatusIdAndCategoryId(fromDate, toDate, stateId, locaionIds, Location, alertTypeId);
				}
				if(alertCountGrpBylocationIdAndStatusIdAndCategoryId1 != null && alertCountGrpBylocationIdAndStatusIdAndCategoryId1.size() > 0){
					for(Object[] param : alertCountGrpBylocationIdAndStatusIdAndCategoryId1){
						if(param[0] != null){
							statusIdAndCategoryIdAndCountMap = locationIdAndStatusIdAndCategoryIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
							if(statusIdAndCategoryIdAndCountMap != null){
								categoryIdAndCountMap = statusIdAndCategoryIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
								if(categoryIdAndCountMap != null){
									categoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[5]));
									
								}else{  
									categoryIdAndCountMap = new LinkedHashMap<Long, Long>();
									categoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[5]));
									statusIdAndCategoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]),categoryIdAndCountMap);
								}
							}else{
								statusIdAndCategoryIdAndCountMap = new LinkedHashMap<Long,Map<Long,Long>>();
								categoryIdAndCountMap = new LinkedHashMap<Long, Long>();
								categoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[5]));
								statusIdAndCategoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]),categoryIdAndCountMap);
								locationIdAndStatusIdAndCategoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), statusIdAndCategoryIdAndCountMap);
							}
							//create a map for related status against location
							statusIdAndStatusNameMap = locationIdAndStatusIdAndSatatusMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
							if(statusIdAndStatusNameMap != null){
								statusIdAndStatusNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getStringValueForObject(param[2]));
							}else{
								statusIdAndStatusNameMap = new LinkedHashMap<Long,String>();
								statusIdAndStatusNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getStringValueForObject(param[2]));
								locationIdAndStatusIdAndSatatusMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), statusIdAndStatusNameMap);
							}
						}
					}
				}
			}    
			
			//build final vo to sent to ui
			List<AlertVO> finalList = null;//new ArrayList<AlertVO>();
			List<AlertVO> superFinalList = new ArrayList<AlertVO>();
			AlertVO innerListAlertVO = null;
			AlertVO outerListAlertVO = null;
			if(locationIdAndStatusIdAndCategoryIdAndCountMap .size() > 0){
				for(Entry<Long,Map<Long,Map<Long,Long>>> entry2 : locationIdAndStatusIdAndCategoryIdAndCountMap.entrySet()){
					statusIdAndCategoryIdAndCountMap = entry2.getValue();
					if(statusIdAndCategoryIdAndCountMap.size() > 0){
						finalList = new ArrayList<AlertVO>();
						outerListAlertVO = new AlertVO();
						statusIdAndStatusNameMap = locationIdAndStatusIdAndSatatusMap.get(entry2.getKey());
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
								if(statusIdAndStatusNameMap.get(entry.getKey()) != null){
									innerListAlertVO.setStatusId(entry.getKey());
									innerListAlertVO.setStatus(statusIdAndStatusNameMap.get(entry.getKey()));
								}
								/*if(statusIdAndCountMap.get(entry.getKey()) != null){
									innerListAlertVO.setCount(statusIdAndCountMap.get(entry.getKey()));
								}*/  
								finalList.add(innerListAlertVO);     
							}
						}
						outerListAlertVO.setLocationId(entry2.getKey());
						outerListAlertVO.setLocationName(locationIdAndNameMap.get(entry2.getKey()));
						outerListAlertVO.setSubList2(finalList);
						superFinalList.add(outerListAlertVO);
					}
				}
			}
			return superFinalList; 
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getTotalAlertGroupByStatusThenCategory() method of AlertService{}");
		}
		return null;
	}
	/*
	 * Swadhin(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertService#getTotalAlertGroupByStatusThenCategory(java.lang.String, java.lang.String, java.lang.Long)
	 */
	/*public List<AlertVO> getTotalAlertGroupByLocationThenCategory(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, String group){
		LOG.info("Entered in getTotalAlertGroupByLocationThenCategory() method of AlertService{}");
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
			Map<Long,Long> locationIdAndCountMap = new HashMap<Long,Long>();
			//get all the alert category for  building the template
			List<Object[]> categoryList = alertCategoryDAO.getAllCategory(); 
			
			Long userAccessLevelId = null;
			List<Long> userAccessLevelValues = new ArrayList<Long>();
			List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);  
			if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){
				userAccessLevelId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
				for(Object[] param : accessLvlIdAndValuesList){
					userAccessLevelValues.add(param[1] != null ? (Long)param[1] : 0l);
				}
			}
			//for state access we need to display districtwise.
			if(group.equalsIgnoreCase("lower")){
				userAccessLevelValues.clear();
				if(stateId.longValue() == 1L){
					userAccessLevelValues.addAll(Arrays.asList(IConstants.AP_NEW_DISTRICTS_IDS));
				}else{
					userAccessLevelValues.addAll(Arrays.asList(IConstants.TS_NEW_DISTRICTS_IDS));
				}
				userAccessLevelId = 3L;
			}    
			//convert parliament into constituency.
			if(userAccessLevelId.longValue() == 4L){
				List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(userAccessLevelValues);
				userAccessLevelId = 5L;
				userAccessLevelValues.clear();
				userAccessLevelValues.addAll(parliamentAssemlyIds);      
			}  

			//get alert status count and and create a map of LocationId and its corresponding  alert count
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByLocation(fromDate, toDate, stateId, scopeIdList, "One",userAccessLevelId, userAccessLevelValues);
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					locationIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}  
			//get all the alert count group by status then category.
			Map<Long,String> locationIdAndNameMap = new HashMap<Long,String>();
			Map<Long,Long> categoryIdAndCountMap = null;//new HashMap<Long, Long>();  
			Map<Long,Map<Long,Long>> locationIdAndCategoryIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
			List<Object[]> alertCountGrpByLocList = alertDAO.getTotalAlertGroupByLocation(fromDate, toDate, stateId, scopeIdList, "two",userAccessLevelId, userAccessLevelValues);    
			if(alertCountGrpByLocList != null && alertCountGrpByLocList.size() > 0){
				for(Object[] param : alertCountGrpByLocList){  
					categoryIdAndCountMap = locationIdAndCategoryIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(categoryIdAndCountMap != null){
						categoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
					}else{
						categoryIdAndCountMap = new HashMap<Long, Long>();
						categoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
						locationIdAndCategoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),categoryIdAndCountMap);
					}
					locationIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}  
			}
			//build final vo to sent to ui
			List<AlertVO> finalList = new ArrayList<AlertVO>();
			AlertVO innerListAlertVO = null;
			if(locationIdAndCategoryIdAndCountMap.size() > 0){
				for(Entry<Long,Map<Long,Long>> entry : locationIdAndCategoryIdAndCountMap.entrySet()){
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
						if(locationIdAndNameMap.get(entry.getKey()) != null){
							innerListAlertVO.setStatusId(entry.getKey());
							innerListAlertVO.setStatus(locationIdAndNameMap.get(entry.getKey()));
							
						}
						if(locationIdAndCountMap.get(entry.getKey()) != null){
							innerListAlertVO.setCount(locationIdAndCountMap.get(entry.getKey()));
						}
						finalList.add(innerListAlertVO);     
					}
				}
			}  
			return finalList; 
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getTotalAlertGroupByLocationThenCategory() method of AlertService{}");
		}
		return null;
	}*/
	public List<AlertVO> getTotalAlertGroupByLocationThenCategory(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, String group){
		LOG.info("Entered in getTotalAlertGroupByLocationThenCategory() method of AlertService{}");
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
			Map<Long,Long> locationIdAndCountMap = new HashMap<Long,Long>();
			//get all the alert category for  building the template
			List<Object[]> categoryList = alertCategoryDAO.getAllCategory(); 
			
			Long userAccessLevelId = null;
			List<Long> userAccessLevelValues = new ArrayList<Long>();
			List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);  
			if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){
				userAccessLevelId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
				for(Object[] param : accessLvlIdAndValuesList){
					userAccessLevelValues.add(param[1] != null ? (Long)param[1] : 0l);
				}
			}
			
			//convert parliament into constituency.
			if(userAccessLevelId.longValue() == 4L){
				List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(userAccessLevelValues);
				userAccessLevelId = 5L;
				userAccessLevelValues.clear();
				userAccessLevelValues.addAll(parliamentAssemlyIds);      
			}  

			//get alert status count and and create a map of LocationId and its corresponding  alert count
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByLocation(fromDate, toDate, stateId, scopeIdList, "One",userAccessLevelId, userAccessLevelValues);
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					if(param[0] != null){
						locationIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
					}
				}
			}  
			//get all the alert count group by status then category.
			Map<Long,String> locationIdAndNameMap = new HashMap<Long,String>();
			Map<Long,Long> categoryIdAndCountMap = null;//new HashMap<Long, Long>();  
			Map<Long,Map<Long,Long>> locationIdAndCategoryIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
			List<Object[]> alertCountGrpByLocList = alertDAO.getTotalAlertGroupByLocation(fromDate, toDate, stateId, scopeIdList, "two",userAccessLevelId, userAccessLevelValues);    
			if(alertCountGrpByLocList != null && alertCountGrpByLocList.size() > 0){
				for(Object[] param : alertCountGrpByLocList){
					if(param[0] != null){
						categoryIdAndCountMap = locationIdAndCategoryIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						if(categoryIdAndCountMap != null){
							categoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
						}else{
							categoryIdAndCountMap = new HashMap<Long, Long>();
							categoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
							locationIdAndCategoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),categoryIdAndCountMap);
						}
						locationIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					}  
				}  
			}
			//build final vo to sent to ui
			List<AlertVO> finalList = new ArrayList<AlertVO>();
			AlertVO innerListAlertVO = null;
			if(locationIdAndCategoryIdAndCountMap.size() > 0){
				for(Entry<Long,Map<Long,Long>> entry : locationIdAndCategoryIdAndCountMap.entrySet()){
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
						if(locationIdAndNameMap.get(entry.getKey()) != null){
							innerListAlertVO.setStatusId(entry.getKey());
							innerListAlertVO.setStatus(locationIdAndNameMap.get(entry.getKey()));
							
						}
						if(locationIdAndCountMap.get(entry.getKey()) != null){
							innerListAlertVO.setCount(locationIdAndCountMap.get(entry.getKey()));
						}
						finalList.add(innerListAlertVO);     
					}
				}
			}  
			return finalList; 
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getTotalAlertGroupByLocationThenCategory() method of AlertService{}");
		}
		return null;
	}
	/*
	 * Swadhin(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertService#getTotalAlertGroupByStatusThenCategory(java.lang.String, java.lang.String, java.lang.Long)
	 */
	/*public List<AlertVO> getTotalAlertGroupByLocationThenStatus(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, String group){
		LOG.info("Entered in getTotalAlertGroupByLocationThenStatus() method of AlertService{}");
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
			Map<Long,Long> locationIdAndCountMap = new HashMap<Long,Long>();
			//get all the alert status for  building the template
			List<Object[]> statusList = alertStatusDAO.getAllStatus();
			
			Long userAccessLevelId = null;
			List<Long> userAccessLevelValues = new ArrayList<Long>();
			List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);  
			if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){
				userAccessLevelId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
				for(Object[] param : accessLvlIdAndValuesList){
					userAccessLevelValues.add(param[1] != null ? (Long)param[1] : 0l);
				}
			}
			//for state access we need to display districtwise.
			if(group.equalsIgnoreCase("lower")){
				userAccessLevelValues.clear();
				if(stateId.longValue() == 1L){
					userAccessLevelValues.addAll(Arrays.asList(IConstants.AP_NEW_DISTRICTS_IDS));
				}else{
					userAccessLevelValues.addAll(Arrays.asList(IConstants.TS_NEW_DISTRICTS_IDS));
				}
				userAccessLevelId = 3L;
			}
			
			//convert parliament into constituency.
			if(userAccessLevelId.longValue() == 4L){
				List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(userAccessLevelValues);
				userAccessLevelId = 5L;
				userAccessLevelValues.clear();
				userAccessLevelValues.addAll(parliamentAssemlyIds);      
			}
			
			//get alert status count and and create a map of LocationId and its corresponding  alert count
			//Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, Long userAccessLevelId, List<Long> userAccessLevelValues
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByLocationThenStatus(fromDate, toDate, stateId, scopeIdList, "One", userAccessLevelId, userAccessLevelValues);
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					locationIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}  
			//get all the alert count group by status then category.
			Map<Long,String> locationIdAndNameMap = new HashMap<Long,String>();
			Map<Long,Long> statusIdAndCountMap = null;//new HashMap<Long, Long>();  
			Map<Long,Map<Long,Long>> locationIdAndStatusIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
			List<Object[]> alertCountGrpByLocList = alertDAO.getTotalAlertGroupByLocationThenStatus(fromDate, toDate, stateId, scopeIdList, "two", userAccessLevelId, userAccessLevelValues);    
			if(alertCountGrpByLocList != null && alertCountGrpByLocList.size() > 0){
				for(Object[] param : alertCountGrpByLocList){  
					statusIdAndCountMap = locationIdAndStatusIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(statusIdAndCountMap != null){
						statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
					}else{
						statusIdAndCountMap = new HashMap<Long, Long>();
						statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
						locationIdAndStatusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),statusIdAndCountMap);
					}  
					locationIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
			//build final vo to sent to ui
			List<AlertVO> finalList = new ArrayList<AlertVO>();
			AlertVO innerListAlertVO = null;
			if(locationIdAndStatusIdAndCountMap.size() > 0){
				for(Entry<Long,Map<Long,Long>> entry : locationIdAndStatusIdAndCountMap.entrySet()){
					statusIdAndCountMap = entry.getValue();
					if(statusIdAndCountMap.size() > 0){
						if(statusList != null && statusList.size() > 0){
							alertVOs = new ArrayList<AlertVO>();
							innerListAlertVO = new AlertVO();
							for(Object[] param : statusList){
								alertVO = new AlertVO();
								alertVO.setCategoryId(commonMethodsUtilService.getLongValueForObject(param[0]));
								alertVO.setCategory(commonMethodsUtilService.getStringValueForObject(param[1]));
								alertVOs.add(alertVO);  
							}
						}
						for(AlertVO param : alertVOs){
							if(statusIdAndCountMap.get(param.getCategoryId()) != null){
								param.setCategoryCount(statusIdAndCountMap.get(param.getCategoryId()));  
							}else{
								param.setCategoryCount(0l);
							}
						}
						innerListAlertVO.setSubList1(alertVOs);
						if(locationIdAndNameMap.get(entry.getKey()) != null){
							innerListAlertVO.setStatusId(entry.getKey());
							innerListAlertVO.setStatus(locationIdAndNameMap.get(entry.getKey()));
							
						}
						if(locationIdAndCountMap.get(entry.getKey()) != null){
							innerListAlertVO.setCount(locationIdAndCountMap.get(entry.getKey()));
						}
						finalList.add(innerListAlertVO);     
					}
				}
			}  
			return finalList; 
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getTotalAlertGroupByLocationThenStatus() method of AlertService{}");
		}
		return null;
	}*/  
	public List<AlertVO> getTotalAlertGroupByLocationThenStatus(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, String group){
		LOG.info("Entered in getTotalAlertGroupByLocationThenStatus() method of AlertService{}");
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
			Map<Long,Long> locationIdAndCountMap = new HashMap<Long,Long>();
			//get all the alert status for  building the template
			List<Object[]> statusList = alertStatusDAO.getAllStatus();
			
			Long userAccessLevelId = null;
			List<Long> userAccessLevelValues = new ArrayList<Long>();
			List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);  
			if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){
				userAccessLevelId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
				for(Object[] param : accessLvlIdAndValuesList){
					userAccessLevelValues.add(param[1] != null ? (Long)param[1] : 0l);
				}
			}
			
			//convert parliament into constituency.
			if(userAccessLevelId.longValue() == 4L){
				List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(userAccessLevelValues);
				userAccessLevelId = 5L;
				userAccessLevelValues.clear();
				userAccessLevelValues.addAll(parliamentAssemlyIds);      
			}
			    
			//get alert status count and and create a map of LocationId and its corresponding  alert count
			//Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, Long userAccessLevelId, List<Long> userAccessLevelValues
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByLocationThenStatus(fromDate, toDate, stateId, scopeIdList, "One", userAccessLevelId, userAccessLevelValues);
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					if(param[0] != null)
						locationIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}  
			//get all the alert count group by status then category.
			Map<Long,String> locationIdAndNameMap = new HashMap<Long,String>();
			Map<Long,Long> statusIdAndCountMap = null;//new HashMap<Long, Long>();  
			Map<Long,Map<Long,Long>> locationIdAndStatusIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
			List<Object[]> alertCountGrpByLocList = alertDAO.getTotalAlertGroupByLocationThenStatus(fromDate, toDate, stateId, scopeIdList, "two", userAccessLevelId, userAccessLevelValues);    
			if(alertCountGrpByLocList != null && alertCountGrpByLocList.size() > 0){
				for(Object[] param : alertCountGrpByLocList){  
					if(param[0] != null){
						statusIdAndCountMap = locationIdAndStatusIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						if(statusIdAndCountMap != null){
							statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
						}else{
							statusIdAndCountMap = new HashMap<Long, Long>();
							statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
							locationIdAndStatusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),statusIdAndCountMap);
						}  
						locationIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					}
				}
			}
			//build final vo to sent to ui
			List<AlertVO> finalList = new ArrayList<AlertVO>();
			AlertVO innerListAlertVO = null;
			if(locationIdAndStatusIdAndCountMap.size() > 0){
				for(Entry<Long,Map<Long,Long>> entry : locationIdAndStatusIdAndCountMap.entrySet()){
					statusIdAndCountMap = entry.getValue();
					if(statusIdAndCountMap.size() > 0){
						if(statusList != null && statusList.size() > 0){
							alertVOs = new ArrayList<AlertVO>();
							innerListAlertVO = new AlertVO();
							for(Object[] param : statusList){
								alertVO = new AlertVO();
								alertVO.setCategoryId(commonMethodsUtilService.getLongValueForObject(param[0]));
								alertVO.setCategory(commonMethodsUtilService.getStringValueForObject(param[1]));
								alertVOs.add(alertVO);  
							}
						}
						for(AlertVO param : alertVOs){
							if(statusIdAndCountMap.get(param.getCategoryId()) != null){
								param.setCategoryCount(statusIdAndCountMap.get(param.getCategoryId()));  
							}else{
								param.setCategoryCount(0l);  
							}
						}
						innerListAlertVO.setSubList1(alertVOs);
						if(locationIdAndNameMap.get(entry.getKey()) != null){
							innerListAlertVO.setStatusId(entry.getKey());
							innerListAlertVO.setStatus(locationIdAndNameMap.get(entry.getKey()));
							
						}
						if(locationIdAndCountMap.get(entry.getKey()) != null){
							innerListAlertVO.setCount(locationIdAndCountMap.get(entry.getKey()));
						}
						finalList.add(innerListAlertVO);     
					}
				}
			}  
			return finalList; 
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getTotalAlertGroupByLocationThenStatus() method of AlertService{}");
		}
		return null;
	}
	
	public List<AlertCoreDashBoardVO> getOverAllAlertDetailsForCoreDashBoard(String startDate,String endDate,Long locationLevelId,
			List<Long> levelValues,List<Long> impactScopeIds){
		AlertCoreDashBoardVO finalVo = new AlertCoreDashBoardVO();
		
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			
			Date fromDate=null;
			Date toDate =null;
			if(startDate !=null && endDate !=null && !startDate.trim().isEmpty() && !endDate.trim().isEmpty()){
				fromDate = sdf.parse(startDate);
				toDate = sdf.parse(endDate);
			}
			
			Map<Long,AlertCoreDashBoardVO> alrtyTypeMap = new HashMap<Long, AlertCoreDashBoardVO>();
			List<AlertCoreDashBoardVO> alrtyTypeList = new ArrayList<AlertCoreDashBoardVO>();
						
			List<Object[]> alertObj = alertTypeDAO.getAlertType();
			setAlertTypes(alertObj,alrtyTypeMap);
			
			Map<Long,AlertCoreDashBoardVO> statusWiseMap = new HashMap<Long, AlertCoreDashBoardVO>();
			
			List<Object[]> alertStatusObj = alertStatusDAO.getAllStatus();
			setAlertStatus(alertStatusObj,statusWiseMap);
			
			
			Map<Long,Map<Long,AlertCoreDashBoardVO>> categoryWiseMap = new HashMap<Long, Map<Long,AlertCoreDashBoardVO>>();
			
			//0.alertId,1.alertCategoryId,2.category,3.alertTypeId,4.alertType,5.alertStatusId,6.alertStatus
			List<Object[]> listObj = alertDAO.getOverAllAlertDetailsForCoreDashBoard(fromDate, toDate, locationLevelId, levelValues, impactScopeIds);
			
		// 1) Alert Type Data Inserting Into finalVo
			if(listObj !=null && listObj.size()>0){
				for (Object[] obj : listObj) {					
					AlertCoreDashBoardVO alertVo = alrtyTypeMap.get((Long)obj[3]);
					if(alertVo !=null){
						if(obj[0] !=null){			
								alertVo.getSetList().add((Long)obj[0]);														
						}						
					}					
				}
			}
			
			if(alrtyTypeMap !=null && alrtyTypeMap.size()>0){
				List<AlertCoreDashBoardVO> alertTypeList = new ArrayList<AlertCoreDashBoardVO>(0);
				for (Entry<Long, AlertCoreDashBoardVO> obj : alrtyTypeMap.entrySet()) {					
					AlertCoreDashBoardVO Vo = obj.getValue();					
					Vo.setCount(Vo.getSetList() !=null ? Long.valueOf(Vo.getSetList().size()):0l);	
					alertTypeList.add(Vo);
				}				
				finalVo.setSubList(alertTypeList);				
			}
			
		
		// 2) Alert Status Wise Data Inserting Into finalVo
			
			if(listObj !=null && listObj.size()>0){
				for (Object[] obj : listObj) {					
					AlertCoreDashBoardVO alertVo = statusWiseMap.get((Long)obj[5]);
					if(alertVo !=null){
						if(obj[0] !=null){							
							alertVo.getSetList().add((Long)obj[0]);														
						}						
					}					
				}
			}
			
			if(statusWiseMap !=null && statusWiseMap.size()>0){
				List<AlertCoreDashBoardVO> alertStatusList = new ArrayList<AlertCoreDashBoardVO>(0);
				for (Entry<Long, AlertCoreDashBoardVO> obj : statusWiseMap.entrySet()) {					
					AlertCoreDashBoardVO Vo = obj.getValue();					
					Vo.setCount(Vo.getSetList() !=null ? Long.valueOf(Vo.getSetList().size()):0l);	
					alertStatusList.add(Vo);
				}				
				finalVo.setSubList1(alertStatusList);				
			}
			
		//3)Alert Category Wise Data Inserting Into finalVo
			
			if(listObj !=null && listObj.size()>0){
				for (Object[] obj : listObj) {					
					Map<Long,AlertCoreDashBoardVO> statusMap = categoryWiseMap.get((Long)obj[1]);
					if(statusMap ==null){
						statusMap = new HashMap<Long, AlertCoreDashBoardVO>();
						setAlertStatus(alertStatusObj,statusMap);//default Status Details										
						categoryWiseMap.put((Long)obj[1], statusMap);
					}					
					AlertCoreDashBoardVO VO = statusMap.get((Long)obj[5]);					
					if(VO == null){
						VO = new AlertCoreDashBoardVO();
						VO.setId((Long)obj[5]);
						VO.setName(obj[6] !=null ? obj[6].toString():"");						
					}
					
					VO.setCategoryId(obj[1] !=null ? (Long)obj[1]:0l);
					VO.setCategory(obj[2] !=null ? obj[2].toString():"");
					
					VO.getSetList().add(obj[0] !=null ? (Long)obj[0]:0l);
				}
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error occured getOverAllAlertDetailsForCoreDashBoard() method of AlertService{}");
		}
		
		
		return null;
	}
	
	public void setAlertTypes(List<Object[]> alertObj ,Map<Long,AlertCoreDashBoardVO> alrtyTypeMap){
		try {
			
			if(alertObj !=null && alertObj.size()>0){
				for (Object[] obj : alertObj) {
					AlertCoreDashBoardVO VO = new AlertCoreDashBoardVO();					
					VO.setId(obj[0] !=null ? (Long)obj[0]:0l);
					VO.setName(obj[1] !=null ? obj[1].toString():"");					
					alrtyTypeMap.put(VO.getId(), VO);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Error occured setAlertTypes() method of AlertService{}");
		}
	}
	
	public void setAlertStatus(List<Object[]>  alertStatusObj,Map<Long,AlertCoreDashBoardVO> statusWiseMap){
			try {
			
				if(alertStatusObj !=null && alertStatusObj.size()>0){
					for (Object[] obj : alertStatusObj) {
						AlertCoreDashBoardVO VO = new AlertCoreDashBoardVO();					
						VO.setId(obj[0] !=null ? (Long)obj[0]:0l);
						VO.setName(obj[1] !=null ? obj[1].toString():"");					
						statusWiseMap.put(VO.getId(), VO);
					}
				}
			
		} catch (Exception e) {
			LOG.error("Error occured setAlertStatus() method of AlertService{}");
		}
	}
    /*
     * Santosh (non-Javadoc)
     * @see com.itgrids.partyanalyst.service.IAlertService#getAlertOverviewDetails(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
     */
  public AlertOverviewVO getAlertOverviewDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr){
	  
	   AlertOverviewVO resultVO = new AlertOverviewVO();
	   Set<Long> locationValues = new HashSet<Long>(0);
	   Long locationAccessLevelId =0l;
	   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	   Date fromDate=null;
	   Date toDate = null;
	   try{
		   if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.length() > 0l && toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
			   fromDate = sdf.parse(fromDateStr);
			   toDate = sdf.parse(toDateStr);
			 }
		   	 List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			 if(rtrnUsrAccssLvlIdAndVlusObjLst != null && rtrnUsrAccssLvlIdAndVlusObjLst.size() > 0){
				 locationAccessLevelId=(Long) rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0];
				 for(Object[] param:rtrnUsrAccssLvlIdAndVlusObjLst){
					 locationValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				 }
			 }
		   List<Object[]> rtrnTtlAlrtObjLst = alertDAO.getAlertCntByAlertTypeBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate);
		   Long totalAlertCnt =0l;
		   AlertOverviewVO overViewVO = new AlertOverviewVO();
		   if(rtrnTtlAlrtObjLst != null && !rtrnTtlAlrtObjLst.isEmpty() ){
			   for(Object[] param:rtrnTtlAlrtObjLst){
				   Long statusId = commonMethodsUtilService.getLongValueForObject(param[0]);
				   Long alertCnt = commonMethodsUtilService.getLongValueForObject(param[2]); 
				   totalAlertCnt = totalAlertCnt + alertCnt;
				   if(statusId == 1l){//Party
					   overViewVO.setPartyAlertCnt(alertCnt);
				   }else if(statusId == 3l){//Other
					   overViewVO.setOtherAlertCnt(alertCnt);
				   }
			   }
			   overViewVO.setTotalAlertCnt(totalAlertCnt);
		   }
		   overViewVO.setPartyAlertCntPer(calculatePercantage(overViewVO.getPartyAlertCnt(), totalAlertCnt));
		   overViewVO.setOtherAlertCntPer(calculatePercantage(overViewVO.getOtherAlertCnt(), totalAlertCnt));
		   
		   Map<Long,AlertOverviewVO> alertStatusMap = new HashMap<Long, AlertOverviewVO>(0);
		   List<Object[]> rtrnAlrtStatusObjLst = alertStatusDAO.getAllStatus();
		   List<Object[]> rtrnAlrtSttsWsCntObjLst = alertDAO.getAlertCntByAlertStatusBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate);
		   prepareTemplateStatusWise(rtrnAlrtStatusObjLst,alertStatusMap);//Prepare Template 
		   if(rtrnAlrtSttsWsCntObjLst != null && rtrnAlrtSttsWsCntObjLst.size() > 0){
			   for(Object[] param:rtrnAlrtSttsWsCntObjLst){
				    if(alertStatusMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) != null ){
				    	alertStatusMap.get(commonMethodsUtilService.getLongValueForObject(param[0])).setStatusCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
				    	alertStatusMap.get(commonMethodsUtilService.getLongValueForObject(param[0])).setStatusCntPer(calculatePercantage(alertStatusMap.get(commonMethodsUtilService.getLongValueForObject(param[0])).getStatusCnt(), totalAlertCnt));
				    }
			   }
		   }   
		   Map<Long,AlertOverviewVO> alertCategoryMap = new ConcurrentHashMap<Long,AlertOverviewVO>();
		   
		   List<Object[]> rtrnAlertCategoryObjLst = alertCategoryDAO.getAllCategory();
		   prepareAlertCategoryTemplate(rtrnAlertCategoryObjLst,rtrnAlrtStatusObjLst,alertCategoryMap);//Prepare Template 
		   
		   List<Object[]> rtrnAlrCtgryCntobjLst = alertDAO.getAlertCntByAlertCategoryBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate);
		 
		   if(rtrnAlrCtgryCntobjLst != null && !rtrnAlrCtgryCntobjLst.isEmpty() ){
			  for(Object[] param:rtrnAlrCtgryCntobjLst)  {
				  if(alertCategoryMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) != null ){
					  alertCategoryMap.get(commonMethodsUtilService.getLongValueForObject(param[0])).setStatusCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
				    } 
			  }
		   }
		   List<Object[]> rtrnAlrtCtgryAndSttsWseCntObjLst = alertDAO.getAlertCntByAlertCategoryAndAlertStatusWiseBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate);
		 
		   if(rtrnAlrtCtgryAndSttsWseCntObjLst != null && !rtrnAlrtCtgryAndSttsWseCntObjLst.isEmpty()){
			   for(Object[] param:rtrnAlrtCtgryAndSttsWseCntObjLst){
				   Long categoryId = commonMethodsUtilService.getLongValueForObject(param[0]);
				   Long statusId = commonMethodsUtilService.getLongValueForObject(param[2]);
				   Long alertcnt = commonMethodsUtilService.getLongValueForObject(param[4]);
				   if(alertCategoryMap.get(categoryId) != null ){
					   AlertOverviewVO statusVO = getMatchVO(alertCategoryMap.get(categoryId).getStatusList(),statusId);
					   if(statusVO != null ){
						   statusVO.setStatusCnt(alertcnt);  
						   statusVO.setStatusCntPer(calculatePercantage(statusVO.getStatusCnt(), alertCategoryMap.get(categoryId).getStatusCnt()));
					   }
				   }
			   }
		   }
		   // preparing final result
		   resultVO.setOverAllVO(overViewVO);
		   if(alertStatusMap != null && alertStatusMap.size() > 0){
			   resultVO.getStatusList().addAll(alertStatusMap.values());
			   alertStatusMap.clear();
		   }
		   //remove alert category which does not contain alert count
            if(alertCategoryMap != null && alertCategoryMap.size() > 0){
            	for(Entry<Long,AlertOverviewVO> entry:alertCategoryMap.entrySet()){
            		 if(entry.getValue().getStatusCnt() == 0l){
            			 alertCategoryMap.remove(entry.getKey());
            		 }
            	}
            }
		   
		   if(alertCategoryMap != null && alertCategoryMap.size() > 0){
			   resultVO.getCategoryList().addAll(alertCategoryMap.values());
			   alertCategoryMap.clear();
		   }
	   }catch(Exception e){
		   LOG.error("Error occured getAlertOverviewDetails() method of AlertService{}",e);
	   }
	   return resultVO;
   }	
   public void prepareTemplateStatusWise(List<Object[]> objList,Map<Long,AlertOverviewVO> alertStatusMap){
	   try{
		   if(objList != null && objList.size() > 0){
			   for(Object[] param:objList){
				   AlertOverviewVO statusVO = new AlertOverviewVO();
				   statusVO.setStatusTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
				   statusVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[1]));
				   alertStatusMap.put(statusVO.getStatusTypeId(), statusVO);
			   }
		   }   
	   }catch(Exception e){
		   LOG.error("Error occured prepareTemplateStatusWise() method of AlertService{}",e);   
	   }
   }
	/* Swadhin Lenka
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertService#getTotalAlertGroupByDist(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.lang.Long)
	 */
	public List<AlertCommentVO> getTotalAlertGroupByDist(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId){
		LOG.info("Entered in getTotalAlertGroupByDist() method of AlertService{}");
		try{
			Date fromDate = null;           
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			//get access level id and access level value
			Long userAccessLevelId = null;
			List<Long> userAccessLevelValues = new ArrayList<Long>();
			List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);  
			if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){
				userAccessLevelId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
				for(Object[] param : accessLvlIdAndValuesList){
					userAccessLevelValues.add(param[1] != null ? (Long)param[1] : 0l);
				}
			}
			
			//convert parliament into constituency.
			if(userAccessLevelId.longValue() == 4L){
				List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(userAccessLevelValues);
				userAccessLevelId = 5L;
				userAccessLevelValues.clear();
				userAccessLevelValues.addAll(parliamentAssemlyIds);      
			}
			
			List<AlertCommentVO> alertCountList = new ArrayList<AlertCommentVO>();
			AlertCommentVO alertCommentVO = null;
			//get total alert group by district
			List<Object[]> alertList = alertDAO.getTotalAlertGroupByDist(fromDate,toDate,stateId,scopeIdList,userAccessLevelId,userAccessLevelValues);
			if(alertList != null && alertList.size() > 0){
				for(Object[] param : alertList){
					if(param[0] != null){
						alertCommentVO = new AlertCommentVO();
						alertCommentVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
						alertCommentVO.setLocaitonName(commonMethodsUtilService.getStringValueForObject(param[1]));
						alertCommentVO.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
						alertCountList.add(alertCommentVO);
					}
				}
			}
			return alertCountList;
		}catch(Exception e){  
			e.printStackTrace();
			LOG.error("Error occured getTotalAlertGroupByDist() method of AlertService{}");
		}
		return null;
	}
	/* Swadhin Lenka
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertService#getAlertDtls(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	public List<AlertCoreDashBoardVO> getAlertDtls(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId, Long alertStatusId, Long alertCategoryId, Long activityMemberId){
		LOG.info("Entered in getAlertDtls() method of AlertService{}");
		try{
			Date fromDate = null;      
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			//get access level id and access level value
			Long userAccessLevelId = null;
			List<Long> userAccessLevelValues = new ArrayList<Long>();
			List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);  
			if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){  
				userAccessLevelId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
				for(Object[] param : accessLvlIdAndValuesList){
					userAccessLevelValues.add(param[1] != null ? (Long)param[1] : 0l);
				}
			}
			Long dist = 0l;  
			DateUtilService dateUtilService = new DateUtilService();
			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			List<Object[]> alertList = alertDAO.getAlertDtls(fromDate, toDate, stateId, alertTypeId, alertStatusId, alertCategoryId, userAccessLevelId, userAccessLevelValues);
			if(alertList != null && alertList.size() > 0){
				for(Object[] param : alertList ){
					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					alertCoreDashBoardVO.setCreatedDate(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10));
					alertCoreDashBoardVO.setUpdatedDate(commonMethodsUtilService.getStringValueForObject(param[2]).substring(0, 10));
					alertCoreDashBoardVO.setStatusId(commonMethodsUtilService.getLongValueForObject(param[3]));
					alertCoreDashBoardVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[4]));
					if(param[1] != null && param[2] != null){
						dist = dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10),commonMethodsUtilService.getStringValueForObject(param[2]).substring(0, 10));
						alertCoreDashBoardVO.setInterval(dist);
					}
					alertCoreDashBoardVO.setAlertLevel(commonMethodsUtilService.getStringValueForObject(param[8]));
					alertCoreDashBoardVOs.add(alertCoreDashBoardVO);
				}
			}
			return alertCoreDashBoardVOs;
		}catch(Exception e){
			e.printStackTrace();  
			LOG.error("Error occured getAlertDtls() method of AlertService{}");
		}
		return null;        
	}
   public void prepareAlertCategoryTemplate(List<Object[]> alertCategoryObjList,List<Object[]> alertStatusObjLst,Map<Long,AlertOverviewVO> alertCategoryMap){
	   try{
		   if(alertCategoryObjList != null && alertCategoryObjList.size() > 0){
			   for(Object[] param:alertCategoryObjList){
				   AlertOverviewVO categoryVO = new AlertOverviewVO();
				   categoryVO.setStatusTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
				   categoryVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[1])+" Alerts");
				   categoryVO.getStatusList().addAll(getStatutList(alertStatusObjLst));
				   alertCategoryMap.put(categoryVO.getStatusTypeId(), categoryVO);
			   }
		   }
	   }catch(Exception e){
		   LOG.error("Error occured prepareAlertCategoryTemplate() method of AlertService{}",e);  
	   }
   }
   public List<AlertOverviewVO> getStatutList(List<Object[]> alertObjList){
	   List<AlertOverviewVO> statusList = new ArrayList<AlertOverviewVO>();
	   try{
		   if(alertObjList != null  && !alertObjList.isEmpty() ){
			   for(Object[] param:alertObjList){
				   AlertOverviewVO statusVO = new AlertOverviewVO();
				   statusVO.setStatusTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
				   statusVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[1]));
				   statusList.add(statusVO);
			   }
		   }
	   }catch(Exception e){
		   LOG.error("Error occured getStatutList() method of AlertService{}",e); 
	   }
	   return statusList;
   }
   public AlertOverviewVO getMatchVO(List<AlertOverviewVO> statusList,Long statusId){
	   try{
		   if(statusList == null || statusList.size() == 0)
			   return null;
		   for(AlertOverviewVO vo:statusList){
			   if(vo.getStatusTypeId().equals(statusId)){
				   return vo;
			   }
		   }
	   }catch(Exception e){
		   LOG.error("Error occured getMatchVO() method of AlertService{}",e);  
	   }
	   return null;
   }
  public Double calculatePercantage(Long subCount,Long totalCount){
		Double d=0.0d;
		if(subCount.longValue()>0l && totalCount.longValue()==0l)
		LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);

		if(totalCount.longValue() > 0l){
			 d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
		}
		return d;
 }
  /*
   * santosh (non-Javadoc)
   * @see com.itgrids.partyanalyst.service.IAlertService#getAlertCategoryDtlsLocationWise(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
   */
 public List<AlertOverviewVO> getAlertCategoryDtlsLocationWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr) {
	 List<AlertOverviewVO> resultList = new ArrayList<AlertOverviewVO>();
	 Map<Long,AlertOverviewVO> categoryMap = new ConcurrentHashMap<Long, AlertOverviewVO>(0);
	 Set<Long> locationValues = new HashSet<Long>(0);
     Long locationAccessLevelId =0l;
     SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
     Date fromDate=null;
     Date toDate = null;
	 try{
		 if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.length() > 0l && toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
			   fromDate = sdf.parse(fromDateStr);
			   toDate = sdf.parse(toDateStr);
			 }
		   	 List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			 if(rtrnUsrAccssLvlIdAndVlusObjLst != null && rtrnUsrAccssLvlIdAndVlusObjLst.size() > 0){
				 locationAccessLevelId=(Long) rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0];
				 for(Object[] param:rtrnUsrAccssLvlIdAndVlusObjLst){
					 locationValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				 }
			 }
			  List<Object[]> rtrnAlertCategoryObjLst = alertCategoryDAO.getAllCategory();
			  List<Object[]> rtrnAlertImpactLevelObjLst = alertImpactScopeDAO.getAllAlertImpactLevel();
			  prepareTemplate(rtrnAlertCategoryObjLst,rtrnAlertImpactLevelObjLst,categoryMap);////Prepare Template 
			  List<Object[]> rtrnImpactLevelCntObjLst = alertDAO.getAlertCntByAlertCategoryAndImpactLevelWiseBasedOnUserAccessLevel(locationAccessLevelId,locationValues,stateId,fromDate, toDate);
			  setAlertImpactLevelWiseAlertCnt(rtrnImpactLevelCntObjLst,categoryMap);
			  List<Object[]> rtrnImpctLvlSttusWsCntObjLst = alertDAO.getAlertCntByAlertCategoryImpactLevelAndStatusWiseBasedOnUserAccessLevel(locationAccessLevelId,locationValues,stateId,fromDate, toDate);
			  setStatusWiseAlertCnt(rtrnImpctLvlSttusWsCntObjLst,categoryMap);
			  
			  // merge mandal,muncipality,Village And Ward data 
			  if(categoryMap != null && categoryMap.size() > 0){
				  for(Entry<Long, AlertOverviewVO> entry : categoryMap.entrySet()){
					  AlertOverviewVO mandalMuncipalityVO = new AlertOverviewVO();
					  AlertOverviewVO villageWardVO = new AlertOverviewVO();
					  AlertOverviewVO mandalVO = getImpactLevelMatchVO(entry.getValue().getSubList(), 5l);
					  AlertOverviewVO MuncipalityVO = getImpactLevelMatchVO(entry.getValue().getSubList(), 8l);
					  mergeRequiredData(mandalMuncipalityVO,mandalVO,4l,"Mandal/Muncipality");
					  mergeRequiredData(mandalMuncipalityVO,MuncipalityVO,4l,"Mandal/Muncipality");
					  AlertOverviewVO villageVO = getImpactLevelMatchVO(entry.getValue().getSubList(), 7l);
					  AlertOverviewVO wardVO = getImpactLevelMatchVO(entry.getValue().getSubList(),9l);
					  mergeRequiredData(villageWardVO,villageVO,5l,"Village/Ward");
					  mergeRequiredData(villageWardVO,wardVO,5l,"Village/Ward");
					  entry.getValue().getSubList().remove(mandalVO);//removeMandalData
					  entry.getValue().getSubList().remove(MuncipalityVO);//removemuncipalityData
					  entry.getValue().getSubList().add(mandalMuncipalityVO);//adding mandalMucipality Merge Data
					  entry.getValue().getSubList().remove(villageVO);//removeVillageData
					  entry.getValue().getSubList().remove(wardVO);//removeWardData
					  entry.getValue().getSubList().add(villageWardVO);//adding VillageWard Merge Data
			      }
			  }
			  //remove alert category which does not contain any alert count in all impact level.
			  if(categoryMap != null && categoryMap.size() > 0){
				  for(Entry<Long,AlertOverviewVO> entry:categoryMap.entrySet()){
					  boolean isAlertCount = false;
					  if(entry.getValue().getSubList() != null && entry.getValue().getSubList().size()> 0){
						  for(AlertOverviewVO locationVO:entry.getValue().getSubList()){
							  if(locationVO.getAlertCount() > 0l){
								  isAlertCount = true;
							  }
							  
						  }
					  }  
					  if(isAlertCount==false){
						  categoryMap.remove(entry.getKey());  
					  }
				 }
			  }
			  if(categoryMap != null && categoryMap.size() > 0){
				  resultList.addAll(categoryMap.values());  
			  }
			  //Calculating overall Category Data
			  Map<Long,AlertOverviewVO> overAllAlrtDtlsMap = new HashMap<Long, AlertOverviewVO>();
			  if(categoryMap != null && categoryMap.size() > 0){
				  for(Entry<Long, AlertOverviewVO> entry : categoryMap.entrySet()){
					  if(entry.getValue().getSubList() != null && entry.getValue().getSubList().size() > 0){
						  for(AlertOverviewVO locationVO:entry.getValue().getSubList()){
							  AlertOverviewVO overAllVO = overAllAlrtDtlsMap.get(locationVO.getLocationTypeId());
							   if(overAllVO == null){
								   overAllVO = new AlertOverviewVO();
								   overAllVO.setLocationTypeId(locationVO.getLocationTypeId());
								   overAllVO.setLocationType(locationVO.getLocationType());
								   overAllVO.setAlertCount(locationVO.getAlertCount());
								   overAllVO.setPendingCnt(locationVO.getPendingCnt());
								   overAllVO.setNotifiedCnt(locationVO.getNotifiedCnt());
								   overAllVO.setActionInProgessCnt(locationVO.getActionInProgessCnt());
								   overAllVO.setCompletedCnt(locationVO.getCompletedCnt());
								   overAllVO.setUnabletoResolveCnt(locationVO.getUnabletoResolveCnt());
								   overAllVO.setActionNotRequiredCnt(locationVO.getActionNotRequiredCnt());
								   overAllAlrtDtlsMap.put(overAllVO.getLocationTypeId(), overAllVO);
							   }else{
							   overAllVO.setAlertCount(overAllVO.getAlertCount()+locationVO.getAlertCount());
							   overAllVO.setPendingCnt(overAllVO.getPendingCnt()+locationVO.getPendingCnt());
							   overAllVO.setNotifiedCnt(overAllVO.getNotifiedCnt()+locationVO.getNotifiedCnt());
							   overAllVO.setActionInProgessCnt(overAllVO.getActionInProgessCnt()+locationVO.getActionInProgessCnt());
							   overAllVO.setCompletedCnt(overAllVO.getCompletedCnt()+locationVO.getCompletedCnt());
							   overAllVO.setUnabletoResolveCnt(overAllVO.getUnabletoResolveCnt()+locationVO.getUnabletoResolveCnt());
							   overAllVO.setActionNotRequiredCnt(overAllVO.getActionNotRequiredCnt()+locationVO.getActionNotRequiredCnt());
						   }
						  }
					  }
				  }
			  }
			  
			  if(overAllAlrtDtlsMap != null && overAllAlrtDtlsMap.size() > 0){
				  AlertOverviewVO overAllAlertVO = new AlertOverviewVO();
				  overAllAlertVO.setName("All Categories Alerts");
				  List<AlertOverviewVO> overAllAlertCntList = new ArrayList<AlertOverviewVO>(overAllAlrtDtlsMap.values());
				  overAllAlertVO.getSubList().addAll(overAllAlertCntList);
				  resultList.add(0, overAllAlertVO);
				  overAllAlrtDtlsMap.clear();
			  }
			
	 }catch(Exception e){
		 LOG.error("Error occured getAlertCategoryDtlsLocationWise() method of AlertService{}",e); 
	 }
	 return resultList;
 }
 public void prepareTemplate(List<Object[]> objList,List<Object[]> impactLevelLst,Map<Long,AlertOverviewVO> categoryMap) {
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				 AlertOverviewVO categoryVO = new AlertOverviewVO();
				 categoryVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				 categoryVO.setName(commonMethodsUtilService.getStringValueForObject(param[1])+" Alerts");
				 categoryVO.getSubList().addAll(getImpactLevelList(impactLevelLst));
				 categoryMap.put(categoryVO.getId(), categoryVO);
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Error occured prepareTemplate() method of AlertService{}",e); 
	 }
 }
 public List<AlertOverviewVO> getImpactLevelList(List<Object[]> impactLevlLst){
	 List<AlertOverviewVO> locationList = new CopyOnWriteArrayList<AlertOverviewVO>();
	   try{
		   if(impactLevlLst != null  && !impactLevlLst.isEmpty() ){
			   for(Object[] param:impactLevlLst){
				   AlertOverviewVO locationVO = new AlertOverviewVO();
				   locationVO.setLocationTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
				   locationVO.setLocationType(commonMethodsUtilService.getStringValueForObject(param[1]));
				   locationList.add(locationVO);
			   }
		   }
	   }catch(Exception e){
		   LOG.error("Error occured getImpactLevelList() method of AlertService{}",e); 
	   }
	   return locationList; 
 }
 public void setAlertImpactLevelWiseAlertCnt(List<Object[]> objList,Map<Long,AlertOverviewVO> categoryMap){
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				 Long alrtCtgryId = commonMethodsUtilService.getLongValueForObject(param[0]);
				 Long impactLevelId = commonMethodsUtilService.getLongValueForObject(param[2]);
				 Long alertCnt = commonMethodsUtilService.getLongValueForObject(param[4]);
				 if(categoryMap.get(alrtCtgryId) != null){
					 AlertOverviewVO impactLevelVO = getImpactLevelMatchVO(categoryMap.get(alrtCtgryId).getSubList(),impactLevelId);
					 	if(impactLevelVO != null ){
					 		impactLevelVO.setAlertCount(alertCnt);
					 	}
				 }
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Error occured setAlertImpactLevelWiseAlertCnt() method of AlertService{}",e); 
	 }
 }
 public void setStatusWiseAlertCnt(List<Object[]> statusWiseAlertCntObjLst,Map<Long,AlertOverviewVO> categoryMap){
	 try{
		 if(statusWiseAlertCntObjLst != null && !statusWiseAlertCntObjLst.isEmpty()){
			 for(Object[] param:statusWiseAlertCntObjLst){
				 Long alrtCtgryId = commonMethodsUtilService.getLongValueForObject(param[0]);
				 Long impactLevelId = commonMethodsUtilService.getLongValueForObject(param[2]);
				 Long statusId = commonMethodsUtilService.getLongValueForObject(param[4]);
				 Long alertCnt = commonMethodsUtilService.getLongValueForObject(param[6]);
				 if(categoryMap.get(alrtCtgryId) != null){
					 AlertOverviewVO impactLevelVO = getImpactLevelMatchVO(categoryMap.get(alrtCtgryId).getSubList(),impactLevelId);
					 	if(impactLevelVO != null ){
							 		if(statusId == 1l){ //Pending
							 			impactLevelVO.setPendingCnt(alertCnt);
							 		}else if(statusId == 2l){//Notified
							 			impactLevelVO.setNotifiedCnt(alertCnt);
							 		}else if(statusId == 3l){//Action In Progess
							 			impactLevelVO.setActionInProgessCnt(alertCnt);
							 		}else if(statusId == 4l){//Completed
							 			impactLevelVO.setCompletedCnt(alertCnt);
							 		}else if(statusId == 5l){//Unable to Resolve
							 			impactLevelVO.setUnabletoResolveCnt(alertCnt);
							 		}else if(statusId == 6l){//Action Not Required
							 			impactLevelVO.setActionNotRequiredCnt(alertCnt);
							 		}
					 }
				 }
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Error occured setStatusWiseAlertCnt() method of AlertService{}",e); 
	 }
 }
 public AlertOverviewVO getImpactLevelMatchVO(List<AlertOverviewVO> impactLevelList,Long impactLevelId){
	 try{
	 	 if(impactLevelList == null || impactLevelList.size() == 0)
	 		 return null;
	 	 for(AlertOverviewVO impactLevelVO:impactLevelList){
	 		if(impactLevelVO.getLocationTypeId().equals(impactLevelId)){
	 			return impactLevelVO;
	 		}
	 	 }
	 	}catch(Exception e){
	 		 LOG.error("Error occured getImpactLevelMatchVO() method of AlertService{}",e);
	 	}	 
	 return null;
 }
 public void mergeRequiredData(AlertOverviewVO resutlVO,AlertOverviewVO VO,Long impactLevelId,String impactLevel){
	 try{
		 if(VO != null){
			 if(resutlVO.getLocationTypeId() == null){
				 resutlVO.setLocationTypeId(impactLevelId);
				 resutlVO.setLocationType(impactLevel);
			 }
			 resutlVO.setAlertCount(resutlVO.getAlertCount()+VO.getAlertCount());
			 resutlVO.setPendingCnt(resutlVO.getPendingCnt()+VO.getPendingCnt());
			 resutlVO.setNotifiedCnt(resutlVO.getNotifiedCnt()+VO.getNotifiedCnt());
			 resutlVO.setActionInProgessCnt(resutlVO.getActionInProgessCnt()+VO.getActionInProgessCnt());
			 resutlVO.setCompletedCnt(resutlVO.getCompletedCnt()+VO.getCompletedCnt());
			 resutlVO.setUnabletoResolveCnt(resutlVO.getUnabletoResolveCnt()+VO.getUnabletoResolveCnt());
			 resutlVO.setActionNotRequiredCnt(resutlVO.getActionNotRequiredCnt()+VO.getActionNotRequiredCnt());
	 }
	 }catch(Exception e){
		 LOG.error("Error occured mergeRequiredData() method of AlertService{}",e);
	 }
 }
 public List<BasicVO> getAlertImpactScope()
 {
 	List<BasicVO> returnList = new ArrayList<BasicVO>();
 	try{
 		 List<Object[]> list = alertImpactScopeDAO.getAlertImpactScope();	
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
 		LOG.error("Exception in getAlertImpactScope()",e);	
 	}
 	return returnList;
 }
 	public List<AlertVO> getTotalAlertGroupByPubRepThenStatus(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId){
		LOG.info("Entered in getTotalAlertGroupByLocationThenStatus() method of AlertService{}");
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
			Map<Long,Long> locationIdAndCountMap = new HashMap<Long,Long>();
			//get all the alert status for  building the template
			List<Object[]> statusList = alertStatusDAO.getAllStatus();
			
			Long userAccessLevelId = null;
			List<Long> userAccessLevelValues = new ArrayList<Long>();
			List<Object[]> accessLvlIdAndValuesList = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);  
			if(accessLvlIdAndValuesList != null && accessLvlIdAndValuesList.size() > 0){
				userAccessLevelId = accessLvlIdAndValuesList.get(0)[0] != null ? (Long)accessLvlIdAndValuesList.get(0)[0] : 0l;
				for(Object[] param : accessLvlIdAndValuesList){
					userAccessLevelValues.add(param[1] != null ? (Long)param[1] : 0l);
				}
			}
			
			//convert parliament into constituency.
			if(userAccessLevelId.longValue() == 4L){
				List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(userAccessLevelValues);
				userAccessLevelId = 5L;
				userAccessLevelValues.clear();
				userAccessLevelValues.addAll(parliamentAssemlyIds);      
			}
			    
			//get alert status count and and create a map of LocationId and its corresponding  alert count
			//Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, Long userAccessLevelId, List<Long> userAccessLevelValues
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByPubRepThenStatus(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, "one");
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					if(param[0] != null)
						locationIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}  
			//get all the alert count group by status then category.
			Map<Long,String> locationIdAndNameMap = new HashMap<Long,String>();
			Map<Long,Long> statusIdAndCountMap = null;//new HashMap<Long, Long>();  
			Map<Long,Map<Long,Long>> locationIdAndStatusIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
			List<Object[]> alertCountGrpByLocList = alertDAO.getTotalAlertGroupByPubRepThenStatus(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, "two");    
			if(alertCountGrpByLocList != null && alertCountGrpByLocList.size() > 0){
				for(Object[] param : alertCountGrpByLocList){  
					if(param[0] != null){
						statusIdAndCountMap = locationIdAndStatusIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						if(statusIdAndCountMap != null){
							statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
						}else{
							statusIdAndCountMap = new HashMap<Long, Long>();
							statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
							locationIdAndStatusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),statusIdAndCountMap);
						}  
						locationIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					}
				}
			}
			//build final vo to sent to ui
			List<AlertVO> finalList = new ArrayList<AlertVO>();
			AlertVO innerListAlertVO = null;
			if(locationIdAndStatusIdAndCountMap.size() > 0){
				for(Entry<Long,Map<Long,Long>> entry : locationIdAndStatusIdAndCountMap.entrySet()){
					statusIdAndCountMap = entry.getValue();
					if(statusIdAndCountMap.size() > 0){
						if(statusList != null && statusList.size() > 0){
							alertVOs = new ArrayList<AlertVO>();
							innerListAlertVO = new AlertVO();
							for(Object[] param : statusList){
								alertVO = new AlertVO();
								alertVO.setCategoryId(commonMethodsUtilService.getLongValueForObject(param[0]));
								alertVO.setCategory(commonMethodsUtilService.getStringValueForObject(param[1]));
								alertVOs.add(alertVO);  
							}
						}
						for(AlertVO param : alertVOs){
							if(statusIdAndCountMap.get(param.getCategoryId()) != null){
								param.setCategoryCount(statusIdAndCountMap.get(param.getCategoryId()));  
							}else{
								param.setCategoryCount(0l);  
							}
						}
						innerListAlertVO.setSubList1(alertVOs);
						if(locationIdAndNameMap.get(entry.getKey()) != null){
							innerListAlertVO.setStatusId(entry.getKey());
							innerListAlertVO.setStatus(locationIdAndNameMap.get(entry.getKey()));
							
						}
						if(locationIdAndCountMap.get(entry.getKey()) != null){
							innerListAlertVO.setCount(locationIdAndCountMap.get(entry.getKey()));
						}
						finalList.add(innerListAlertVO);     
					}
				}
			}  
			return finalList; 
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Error occured getTotalAlertGroupByLocationThenStatus() method of AlertService{}");
			}
			return null;  
 		}
	    
	}