package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IActionTypeDAO;
import com.itgrids.partyanalyst.dao.IActionTypeStatusDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IAlertAssignedDAO;
import com.itgrids.partyanalyst.dao.IAlertCandidateDAO;
import com.itgrids.partyanalyst.dao.IAlertCategoryDAO;
import com.itgrids.partyanalyst.dao.IAlertClarificationCommentsDAO;
import com.itgrids.partyanalyst.dao.IAlertClarificationDAO;
import com.itgrids.partyanalyst.dao.IAlertClarificationDocumentDAO;
import com.itgrids.partyanalyst.dao.IAlertClarificationStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertCommentAssigneeDAO;
import com.itgrids.partyanalyst.dao.IAlertCommentDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertDocumentDAO;
import com.itgrids.partyanalyst.dao.IAlertImpactScopeDAO;
import com.itgrids.partyanalyst.dao.IAlertStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertTrackingDAO;
import com.itgrids.partyanalyst.dao.IAlertTrackingDocumentsDAO;
import com.itgrids.partyanalyst.dao.IAlertTypeDAO;
import com.itgrids.partyanalyst.dao.IAlertUserDAO;
import com.itgrids.partyanalyst.dao.IAlertVerificationUserTypeUserDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.IClarificationRequiredDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEditionTypeDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IMemberTypeDAO;
import com.itgrids.partyanalyst.dao.INewsPaperDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IParliamentAssemblyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITvNewsChannelDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IVerificationCommentsDAO;
import com.itgrids.partyanalyst.dao.IVerificationConversationDAO;
import com.itgrids.partyanalyst.dao.IVerificationDocumentsDAO;
import com.itgrids.partyanalyst.dao.IVerificationStatusDAO;
import com.itgrids.partyanalyst.dao.hibernate.TvNewsChannelDAO;
import com.itgrids.partyanalyst.dao.impl.IAlertSourceUserDAO;
import com.itgrids.partyanalyst.dto.ActionTypeStatusVO;
import com.itgrids.partyanalyst.dto.ActionableVO;
import com.itgrids.partyanalyst.dto.AlertClarificationVO;
import com.itgrids.partyanalyst.dto.AlertCommentVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertDataVO;
import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.dto.AlertOverviewVO;
import com.itgrids.partyanalyst.dto.AlertTrackingVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.AlertVerificationVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.ClarificationDetailsCountVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.StatusTrackingVO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertAssigned;
import com.itgrids.partyanalyst.model.AlertCandidate;
import com.itgrids.partyanalyst.model.AlertCategory;
import com.itgrids.partyanalyst.model.AlertClarification;
import com.itgrids.partyanalyst.model.AlertClarificationComments;
import com.itgrids.partyanalyst.model.AlertClarificationStatus;
import com.itgrids.partyanalyst.model.AlertComment;
import com.itgrids.partyanalyst.model.AlertCommentAssignee;
import com.itgrids.partyanalyst.model.AlertDocument;
import com.itgrids.partyanalyst.model.AlertStatus;
import com.itgrids.partyanalyst.model.AlertTracking;
import com.itgrids.partyanalyst.model.AlertTrackingDocuments;
import com.itgrids.partyanalyst.model.ClarificationRequired;
import com.itgrids.partyanalyst.model.MemberType;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.model.VerificationComments;
import com.itgrids.partyanalyst.model.VerificationConversation;
import com.itgrids.partyanalyst.model.VerificationDocuments;
import com.itgrids.partyanalyst.model.VerificationStatus;
import com.itgrids.partyanalyst.service.IAlertService;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;
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
private IAlertClarificationDAO alertClarificationDAO;
private IAlertClarificationCommentsDAO alertClarificationCommentsDAO;
private ActivityService activityService;
private IAlertClarificationDocumentDAO alertClarificationDocumentDAO;
private IEditionTypeDAO editionTypeDAO; 
private IClarificationRequiredDAO clarificationRequiredDAO;
private IAlertClarificationStatusDAO alertClarificationStatusDAO;
private IActionTypeStatusDAO actionTypeStatusDAO;
private IActionTypeDAO actionTypeDAO;
private IAlertDocumentDAO alertDocumentDAO;
private IVerificationStatusDAO verificationStatusDAO;
private IVerificationConversationDAO verificationConversationDAO;
private IVerificationDocumentsDAO verificationDocumentsDAO;
private IVerificationCommentsDAO verificationCommentsDAO;
private IAlertVerificationUserTypeUserDAO alertVerificationUserTypeUserDAO;
private IAlertTrackingDocumentsDAO alertTrackingDocumentsDAO;
private ITdpCadreDAO tdpCadreDAO;
private IAlertDepartmentStatusDAO alertDepartmentStatusDAO;
private IGovtDepartmentDAO govtDepartmentDAO;
private IDelimitationConstituencyDAO delimitationConstituencyDAO;
private SmsSenderService smsSenderService;
private INewsPaperDAO newsPaperDAO;
private ITvNewsChannelDAO tvNewsChannelDAO;

public void setDelimitationConstituencyDAO(
		IDelimitationConstituencyDAO delimitationConstituencyDAO) {  
	this.delimitationConstituencyDAO = delimitationConstituencyDAO;
}

public void setGovtDepartmentDAO(IGovtDepartmentDAO govtDepartmentDAO) {
	this.govtDepartmentDAO = govtDepartmentDAO;
}

public ITdpCadreDAO getTdpCadreDAO() {
	return tdpCadreDAO;
}

public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
	this.tdpCadreDAO = tdpCadreDAO;
}

public void setAlertTrackingDocumentsDAO(IAlertTrackingDocumentsDAO alertTrackingDocumentsDAO) {
	this.alertTrackingDocumentsDAO = alertTrackingDocumentsDAO;
}
public void setAlertDocumentDAO(IAlertDocumentDAO alertDocumentDAO) {
	this.alertDocumentDAO = alertDocumentDAO;
}

public void setActionTypeDAO(IActionTypeDAO actionTypeDAO) {
	this.actionTypeDAO = actionTypeDAO;
}

public IAlertClarificationStatusDAO getAlertClarificationStatusDAO() {
	return alertClarificationStatusDAO;
}

public void setAlertClarificationStatusDAO(
		IAlertClarificationStatusDAO alertClarificationStatusDAO) {
	this.alertClarificationStatusDAO = alertClarificationStatusDAO;
}

public IClarificationRequiredDAO getClarificationRequiredDAO() {
	return clarificationRequiredDAO;
}

public void setClarificationRequiredDAO(
		IClarificationRequiredDAO clarificationRequiredDAO) {
	this.clarificationRequiredDAO = clarificationRequiredDAO;
}

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
public IAlertClarificationDAO getAlertClarificationDAO() {
	return alertClarificationDAO;
}

public void setAlertClarificationDAO(IAlertClarificationDAO alertClarificationDAO) {
	this.alertClarificationDAO = alertClarificationDAO;
}

public IAlertClarificationCommentsDAO getAlertClarificationCommentsDAO() {
	return alertClarificationCommentsDAO;
}

public void setAlertClarificationCommentsDAO(IAlertClarificationCommentsDAO alertClarificationCommentsDAO) {
	this.alertClarificationCommentsDAO = alertClarificationCommentsDAO;
}
public ActivityService getActivityService() {
	return activityService;
}

public void setActivityService(ActivityService activityService) {
	this.activityService = activityService;
}

public IAlertClarificationDocumentDAO getAlertClarificationDocumentDAO() {
	return alertClarificationDocumentDAO;
}

public void setAlertClarificationDocumentDAO(IAlertClarificationDocumentDAO alertClarificationDocumentDAO) {
	this.alertClarificationDocumentDAO = alertClarificationDocumentDAO;
}


public void setEditionTypeDAO(IEditionTypeDAO editionTypeDAO) {
	this.editionTypeDAO = editionTypeDAO;
}

public void setActionTypeStatusDAO(IActionTypeStatusDAO actionTypeStatusDAO) {
	this.actionTypeStatusDAO = actionTypeStatusDAO;
}
public void setVerificationStatusDAO(IVerificationStatusDAO verificationStatusDAO) {
	this.verificationStatusDAO = verificationStatusDAO;
}

public void setVerificationConversationDAO(
		IVerificationConversationDAO verificationConversationDAO) {
	this.verificationConversationDAO = verificationConversationDAO;
}

public void setVerificationDocumentsDAO(
		IVerificationDocumentsDAO verificationDocumentsDAO) {
	this.verificationDocumentsDAO = verificationDocumentsDAO;
}

public void setVerificationCommentsDAO(
		IVerificationCommentsDAO verificationCommentsDAO) {
	this.verificationCommentsDAO = verificationCommentsDAO;
}
public void setAlertVerificationUserTypeUserDAO(
		IAlertVerificationUserTypeUserDAO alertVerificationUserTypeUserDAO) {
	this.alertVerificationUserTypeUserDAO = alertVerificationUserTypeUserDAO;
}
public void setAlertDepartmentStatusDAO(
		IAlertDepartmentStatusDAO alertDepartmentStatusDAO) {
	this.alertDepartmentStatusDAO = alertDepartmentStatusDAO;
}


public SmsSenderService getSmsSenderService() {
	return smsSenderService;
}

public void setSmsSenderService(SmsSenderService smsSenderService) {
	this.smsSenderService = smsSenderService;
}
public void setNewsPaperDAO(INewsPaperDAO newsPaperDAO) {
	this.newsPaperDAO = newsPaperDAO;
}
public void setTvNewsChannelDAO(ITvNewsChannelDAO tvNewsChannelDAO) {
	this.tvNewsChannelDAO = tvNewsChannelDAO;
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
			 vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]).toString());//last name
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

public String createAlert(final AlertVO inputVO,final Long userId, final Map<File,String> mapFiles)
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
				 saveAlertDocument(alert.getAlertId(),userId,mapFiles);
				 
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
													alertAssigned.setSmsStatus("N");
													alertAssigned = alertAssignedDAO.save(alertAssigned);
													Long alertId = 0l;
													String description = " ";
													String mobilenumber =" ";
													boolean smsStatus = true;
													if(alertAssigned != null){
														Long assignedId = alertAssigned.getAlertAssignedId();
														List<Object[]> leaderDtls = alertAssignedDAO.getLeaderDtls(assignedId);
														if(leaderDtls != null && leaderDtls.size()>0){
															for(Object[] param :leaderDtls){
																alertId =commonMethodsUtilService.getLongValueForObject(param[0]);
																description =commonMethodsUtilService.getStringValueForObject(param[1]);
																mobilenumber=commonMethodsUtilService.getStringValueForObject(param[2]);
															}
															
														}
														String message = commonMethodsUtilService.escapeUnicode("Alert is assigned to you,please follow up and resolve \n\nDescription:\n" +StringEscapeUtils.unescapeHtml(description));
														smsStatus =	smsSenderService.sendSmsForAssignedLeaderInTelugu(commonMethodsUtilService.getUniCodeMessage(StringEscapeUtils.unescapeJava(message)), false, mobilenumber);
														 if(smsStatus == true){     
															 LOG.error(" Sms Status sending successfully ");
															 LOG.error( description );
															 LOG.error( mobilenumber );
														 alertAssignedDAO.updateAlertSmsStatus(assignedId);
														 }else if(smsStatus == false){
															 LOG.error(" Sms Status failed ");
															 LOG.error(description);
															 LOG.error(mobilenumber);
														 }
														
													}
													
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
public String saveAlertDocument(Long alertId,Long userId,final Map<File,String> documentMap){
	
	try{
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DateUtilService dt = new DateUtilService();
		
		String folderName = IConstants.STATIC_CONTENT_FOLDER_PATH+"/Reports/"+IConstants.TOUR_DOCUMENTS;
		AlertDocument alertDocument = null;  
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		 int year = calendar.get(Calendar.YEAR);
		 int month = calendar.get(Calendar.MONTH);
		 //int day = calendar.get(Calendar.DAY_OF_MONTH);
		 int temp = month+1;
		 String monthText = getMonthForInt(temp);
		
		 StringBuilder pathBuilder = null;
		 StringBuilder str ;
		 
		
		 for (Map.Entry<File, String> entry : documentMap.entrySet())
		 {

			 pathBuilder = new StringBuilder();
			 
			 Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
			 String ext = "";
			 String fName = "";
			 String[] extension  = entry.getValue().split("\\.");
			 if(extension.length > 1){
				 ext = extension[extension.length-1];
				 fName = extension[0];
			 }
			 String destPath = folderName+"/"+randomNumber+"."+ext;
			 
			 
			 pathBuilder.append("tour_documents").append("/").append(randomNumber).append(".").append(ext);
			 
			 String fileCpyStts = copyFile(entry.getKey().getAbsolutePath(),destPath);
			 
				if(fileCpyStts.equalsIgnoreCase("error")){
					LOG.error(" Exception Raise in copying file in ToursService ");
					throw new ArithmeticException();
				}
				
				alertDocument = new AlertDocument();
				alertDocument.setDocumentPath(pathBuilder.toString());
				alertDocument.setDocumentName(StringEscapeUtils.escapeJava(fName));     
				//System.out.println(StringEscapeUtils.unescapeJava(encode));
				alertDocument.setAlertId(alertId);
				alertDocument.setInsertedTime(dt.getCurrentDateAndTime());
				alertDocument.setIsDeleted("N");
				alertDocument.setInsertedBy(userId);
				alertDocument = alertDocumentDAO.save(alertDocument);
				
		 }
	}catch(Exception e){
		LOG.error("Exception Occured in saveApplicationDocuments() in ToursService", e);
		return "faliure";
	}
	return "success";
}
public static String folderCreation()
{
	try {
		LOG.debug(" in FolderForDocument ");
  		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new DateUtilService().getCurrentDateAndTime());  
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		
		String targetDirpath = IConstants.STATIC_CONTENT_FOLDER_URL+"Reports/"+IConstants.TOUR_DOCUMENTS+"/"+getMonthForInt(month+1)+year;
		
		File requriredDir = new File(targetDirpath);
		
		if(!requriredDir.exists())
			requriredDir.mkdirs();
		
		return requriredDir.getAbsolutePath();
		 
	} catch (Exception e) {
		LOG.error(" Failed to Create");  
		return "FAILED";
	}
}
public String copyFile(String sourcePath,String destinationPath){
	 try{
		File destFile = new File(destinationPath);
		 if (!destFile.exists()) 
			 destFile.createNewFile();
		 File file = new File(sourcePath);
		if(file.exists()){
			FileUtils.copyFile(file,destFile);
			LOG.error("Copy Success");
			return "success";
		}
	  }catch(Exception e){
		  LOG.error("Exception raised in copyFile in ToursService ", e);
		  LOG.error("Copy Error");
		  return "error";
	  }
	 return "failure";
}
public static String getMonthForInt(int num) {    
	String month = "";
	DateFormatSymbols dfs = new DateFormatSymbols();
	String[] months = dfs.getMonths();
	if (num >= 1 && num <= 12 ) {
		month = months[num-1];
	}
	return month;  
}

public ResultStatus saveAlertTrackingDetails(final AlertTrackingVO alertTrackingVO)
{
	final ResultStatus rs = new ResultStatus();
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
				
				alertTracking = alertTrackingDAO.save(alertTracking);
				rs.setResultState(alertTracking.getAlertTrackingId());
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
	public UserAddress editUserAddress(final AlertVO inputVO, Long addressId)
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
						 
						 BasicVO levelVo =  (BasicVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "id", commonMethodsUtilService.getStringValueForObject(params[1]).toString()) ;
						 if(levelVo == null)
						 {
							 levelVo = new BasicVO();
							 levelVo.setId((Long)params[1]);
							 levelVo.setName(commonMethodsUtilService.getStringValueForObject(params[2]).toString());
							
							 levelVo.setLocationsList(setAlertStatusList(statusList));
							 returnList.add(levelVo);
						 }
						 levelVo.setCount(levelVo.getCount() + (Long)params[0]);
						 BasicVO statusVO =  (BasicVO) setterAndGetterUtilService.getMatchedVOfromList(levelVo.getLocationsList(), "id", commonMethodsUtilService.getStringValueForObject(params[3]).toString()) ;
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
			List<Object[]> docList = alertDocumentDAO.getDocumentsForAlert(alertId);
			 List<Object[]> list = alertDAO.getAlertsData(alertId);
			 Object[] sourceDtls = alertDAO.getSourceDtlsByAlertId(alertId);   
			 String alertSource = "";
			 if(sourceDtls != null){
				 if(commonMethodsUtilService.getLongValueForObject(sourceDtls[0]).longValue() == 1L){//manual
					alertSource = commonMethodsUtilService.getStringValueForObject(sourceDtls[2]);
				 }else if(commonMethodsUtilService.getLongValueForObject(sourceDtls[0]).longValue() == 2L){//print
					 if(sourceDtls[6] != null){
						 alertSource = commonMethodsUtilService.getStringValueForObject(sourceDtls[6]);
					 }else{
						 alertSource = commonMethodsUtilService.getStringValueForObject(sourceDtls[2]);
					 }
					 
				 }else if(commonMethodsUtilService.getLongValueForObject(sourceDtls[0]).longValue() == 3L){//electronic 
					 if(sourceDtls[8] != null){
						 alertSource = commonMethodsUtilService.getStringValueForObject(sourceDtls[8]);
					 }else{
						 alertSource = commonMethodsUtilService.getStringValueForObject(sourceDtls[2]);
					 }
					 
				 }  
			 }
			 List<String> documentList = new ArrayList<String>();
			 List<String> documentNameList = new ArrayList<String>();
			 if(docList != null && docList.size() > 0){
				 for(Object[] param : docList){
					 documentList.add(commonMethodsUtilService.getStringValueForObject(param[1]));
					 documentNameList.add(commonMethodsUtilService.getStringValueForObject(param[2]));
				 }
			 }
			 if(list != null && list.size() > 0)
			 {
				 
				 Map<Long,Long> alertCategoryMap = new HashMap<Long, Long>();
				 
				 for(Object[] params : list)
				 {
					 AlertDataVO alertVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "id",commonMethodsUtilService.getStringValueForObject( params[0]).toString());
					 if(alertVO == null)
					 {
						 alertVO = new AlertDataVO(); 
						 returnList.add(alertVO);
						 if(!alertIds.contains((Long)params[0]))
							 alertIds.add((Long)params[0]);
					 }
					 alertVO.setId((Long)params[0]);
					 alertVO.setTitle(params[25] != null ? params[25].toString() : "");
					 alertVO.setDesc(commonMethodsUtilService.getStringValueForObject(params[1]).toString());
					 alertVO.setAlertSource(alertSource);
					 alertVO.setDate(params[2] != null? params[2].toString():"");
					 alertVO.setAlertType(params[3] != null ? params[3].toString() : "");
					 alertVO.setUserType(params[4] != null ? params[4].toString() : "");
					 alertVO.setSeverity(params[5] != null ? params[5].toString() : "");
					 alertVO.setRegionScopeId(params[6] != null ? (Long)params[6] : null);
					 alertVO.setRegionScope(params[26] != null ?params[26].toString() : "");
					 alertVO.setStatusId(params[8] != null ? (Long)params[8] : null);
					 alertVO.setStatus(params[9] != null ?params[9].toString() : "");
					 alertVO.setDocumentList(documentList);
					 alertVO.setDocumentNameList(documentNameList);
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
					 alertVO.setDepartment(params[31] != null ? params[31].toString() : null);
					 
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
						
						 //0.alertId,1.candidateId,2.candidateName,3.designation,4.organization,5.impactId,6.impact,7.paCandidateId,
						 //8.membershipNo,9.image
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
							 AlertDataVO alertVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "id", commonMethodsUtilService.getStringValueForObject(params[1]).toString());
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
					AlertDataVO alertVo =(AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(dataList, "id", commonMethodsUtilService.getStringValueForObject(params[0]).toString());
					if(alertVo == null)
					{
						alertVo = new AlertDataVO();
						alertVo.setId((Long)params[0]);
						dataList.add(alertVo);
					}
					AlertDataVO candidateVO = null;
					if(params[1] !=null){
						candidateVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(alertVo.getSubList(), "id", commonMethodsUtilService.getStringValueForObject(params[1]).toString());
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
					candidateVO.setMembershipNo(params[8] !=null ? params[8].toString():"");
					candidateVO.setImage(params[9] !=null ? params[9].toString():"");
					candidateVO.setMobileNo(params[10] !=null ? params[10].toString():"");     
					
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
			 List<Object[]> list = alertDAO.getLocationLevelWiseAlertsData(userTypeIds,inputVO,fromDate,toDate);//done
			 List<Object[]> list2 = verificationStatusDAO.getTotalStatus();
			 Map<Long,String> alertAndStatusMap = new HashMap<Long,String>();
			 if(list2 != null && list2.size() > 0){
				 for(Object[] param : list2){
					 alertAndStatusMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				 }
			 }
			 setAlertLocationWiseData(list,returnList,alertAndStatusMap,"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
	}
	
	public List<AlertDataVO> getLocationWiseFilterAlertData(Long userId,LocationVO inputVO,Long assignedCadreId,Long involvedCandidateId, Long impactId)
	{
		List<AlertDataVO> returnList = new ArrayList<AlertDataVO>();
		 List<Long> userTypeIds = alertSourceUserDAO.getAlertSourceUserIds(userId);
		 SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy"); 
		 
		
		try{
			Date fromDate = null;Date toDate=null;
			Date fromDate2 = null;Date toDate2=null;
			if(inputVO.getFromDate() != null && !inputVO.getFromDate().toString().isEmpty()){
				fromDate = sdf.parse(inputVO.getFromDate());
				toDate = sdf.parse(inputVO.getToDate());
			}
			if(inputVO.getFromDate2() != null && !inputVO.getFromDate2().toString().isEmpty()){
				fromDate2 = sdf.parse(inputVO.getFromDate2());
				toDate2 = sdf.parse(inputVO.getToDate2());
			}
			 List<Object[]> list = alertDAO.getLocationWiseFilterAlertData(userTypeIds,fromDate,toDate,inputVO,assignedCadreId,fromDate2,toDate2,involvedCandidateId,impactId);//done
			 List<Object[]> list2 = verificationStatusDAO.getTotalStatus();
			 Map<Long,String> alertAndStatusMap = new HashMap<Long,String>();
			 if(list2 != null && list2.size() > 0){
				 for(Object[] param : list2){
					 alertAndStatusMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				 }
			 }
			 setAlertLocationWiseData(list,returnList,alertAndStatusMap,inputVO.getTask());  
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
	}
	
	public void setAlertLocationWiseData(List<Object[]> list,List<AlertDataVO> returnList,Map<Long,String> alertAndStatusMap,String status)
	{
		List<Long> alertIds = new ArrayList<Long>();
		List<Long> alertIdsNews = new ArrayList<Long>();
		 if(list != null && list.size() > 0)
		 {
			 String alertSource = "";
			 for(Object[] params : list)
			 {
				 AlertDataVO alertVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "id", params[0].toString());
				 if(alertVO == null)
				 {
					 alertVO = new AlertDataVO(); 
					 returnList.add(alertVO);
					 
					 alertVO.setAlertCategoryId(commonMethodsUtilService.getLongValueForObject(params[25]));
					 
					 if(alertVO.getAlertCategoryId() !=null && alertVO.getAlertCategoryId().longValue() > 1l){
						 if(!alertIdsNews.contains((Long)params[0]));
						 alertIdsNews.add((Long)params[0]);
					 }else{
						 if(!alertIds.contains((Long)params[0]));
						 alertIds.add((Long)params[0]);
					 }
					
				 }
				 alertVO.setId((Long)params[0]);
				 alertVO.setDesc(commonMethodsUtilService.getStringValueForObject(params[1]).toString());
				 alertVO.setDate(params[2] != null? params[2].toString():"");
				 alertVO.setAlertType(params[3] != null ? params[3].toString() : "");
				 alertVO.setUserType(params[4] != null ? params[4].toString() : "");
				 alertVO.setSeverity(params[5] != null ? params[5].toString() : "");
				 alertVO.setRegionScopeId(params[6] != null ? (Long)params[6] : null);
				 alertVO.setRegionScope(params[7] != null ?params[7].toString() : "");
				 alertVO.setStatusId(params[8] != null ? (Long)params[8] : null);
				 alertVO.setStatus(params[9] != null ?params[9].toString() : "");
				 if(status.equalsIgnoreCase("verification")){
					 alertVO.setVerificationStatusId(params[34] != null ? (Long)params[34] : null);
					 alertVO.setVerificationStatus(params[35] != null ? params[35].toString() : null);
				 }
				 if(alertAndStatusMap.get((Long)params[0]) != null){
					 alertVO.setVerificationStatus(alertAndStatusMap.get((Long)params[0]));
				 }
				// alertVO.setAlertCategoryId(commonMethodsUtilService.getLongValueForObject(params[25]));
				 alertVO.setAlertCategoryName(commonMethodsUtilService.getStringValueForObject(params[26]));
				 
			 	if(commonMethodsUtilService.getLongValueForObject(params[25]).longValue() == 1L){//manual
					alertSource = commonMethodsUtilService.getStringValueForObject(params[4]);
				}else if(commonMethodsUtilService.getLongValueForObject(params[25]).longValue() == 2L){//print
					if(params[30] != null){
						alertSource = commonMethodsUtilService.getStringValueForObject(params[30]);
					}else{
						alertSource = commonMethodsUtilService.getStringValueForObject(params[4]);
					}
					 
				}else if(commonMethodsUtilService.getLongValueForObject(params[25]).longValue() == 3L){//electronic 
					if(params[32] != null){
						alertSource = commonMethodsUtilService.getStringValueForObject(params[32]);
					}else{
						alertSource = commonMethodsUtilService.getStringValueForObject(params[4]);
					}
				}
			 	
			 	alertVO.setAlertSource(alertSource);
			 	alertVO.setTitle(commonMethodsUtilService.getStringValueForObject(params[33]));
				 
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
					 AlertDataVO alertVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "id", commonMethodsUtilService.getStringValueForObject(params[1]).toString());
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

	public String updateAlertStatus(final Long userId,final AlertVO inputVo,final List<String> fileNames){
		String resultStatus = (String) transactionTemplate.execute(new TransactionCallback() {
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
			    		alertComment.setComments(StringEscapeUtils.unescapeJava(inputVo.getDesc().toString()));
			    	alertComment.setAlertId(inputVo.getId());
			    	alertComment.setInsertedTime(currentDateAndTime);
			    	alertComment.setIsDeleted("N");
			    	alertComment.setInsertedBy(userId);
			    	alertComment = alertCommentDAO.save(alertComment);
			    
			    
			    	if(inputVo.getAssignList() != null && inputVo.getAssignList().size() > 0){
			    		for(IdNameVO vo : inputVo.getAssignList()){
			    			if(vo.getId() != null && vo.getId() > 0){
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
			    	alertTrackingVO.setAlertStatusId(inputVo.getStatusId());
			    	alertTrackingVO.setAlertId(alert.getAlertId());
			    	alertTrackingVO.setAlertTrackingActionId(IConstants.ALERT_ADDED_COMMENT);
			    	ResultStatus rs1 = saveAlertTrackingDetails(alertTrackingVO);	
		    	
		    		if(fileNames != null && fileNames.size() > 0){
		    			for (String string : fileNames) {
							AlertTrackingDocuments alertTrackingDocuments = new AlertTrackingDocuments();
							alertTrackingDocuments.setAlertTrackingId(rs1.getResultState());
							alertTrackingDocuments.setPath(string);
							alertTrackingDocuments.setIsDeleted("N");
							alertTrackingDocumentsDAO.save(alertTrackingDocuments);
						}
		    		}
				}catch (Exception ex) {
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
			Map<Long,List<KeyValueVO>> alertTrackingDocumentsMap = new HashMap<Long, List<KeyValueVO>>();//alerttrackingid,docsList
			
			Map<Long,List<AlertCommentVO>> commentIdAndCommentDtlsMap = new HashMap<Long,List<AlertCommentVO>>();
			List<AlertCommentVO>  alertCommentDtlsList = null;
			AlertCommentVO alertCommentVO = null;
			List<Object[]> list = alertTrackingDAO.getAlertTrackingDetailsList(alertId,true);
			
			if(list != null && list.size() > 0){
				List<Long> alertTrackingIds = new ArrayList<Long>(0);
				List<Long> cadreIds = new ArrayList<Long>(0);
				for (Object[] objects : list) {
					if(objects[6] != null)
						cadreIds.add((Long)objects[6]);
					if(objects[11] != null)
						alertTrackingIds.add((Long)objects[11]);
				}
				 
				if(cadreIds != null && cadreIds.size() > 0){
					List<Object[]> objList = tdpCadreDAO.getCadreFormalDetails(cadreIds);
					if(objList != null && objList.size() > 0){
						for (Object[] objects : objList) {
							List<Object[]> matchedObjList = gatMatchedObject((Long)objects[0],list);
							if(matchedObjList != null && matchedObjList.size() > 0){
								for (Object[] objects2 : matchedObjList) {
									objects2[7]=objects[1].toString();
								}
							}
						}
					}
					
				}
				
				//get alert tracking documents
				if(alertTrackingIds != null && alertTrackingIds.size() > 0){
					List<Object[]> docsObjList = alertTrackingDocumentsDAO.getDocumentsForAlertTracking(alertTrackingIds);
					if(docsObjList != null && docsObjList.size() > 0){
						//0-trackingid,1-docid,2-path
						for (Object[] objects : docsObjList) {
							List<KeyValueVO> voList = null;
							if(alertTrackingDocumentsMap.get((Long)objects[0]) == null){
								voList = new ArrayList<KeyValueVO>(0);
								alertTrackingDocumentsMap.put((Long)objects[0], voList);
							}
							
							KeyValueVO vo = new KeyValueVO();
							vo.setId((Long)objects[1]);
							vo.setName(objects[2] != null?objects[2].toString():"");
							alertTrackingDocumentsMap.get((Long)objects[0]).add(vo);
							
						}
					}
				}
			}
			
			Map<Long,Long> statusOrderMap = new HashMap<Long, Long>(0);
			boolean noList = false;
			if(!commonMethodsUtilService.isListOrSetValid(list)){
				list = new ArrayList<Object[]>(0);
				noList = true;
			}
				
					List<Object[]> list1 = alertTrackingDAO.getAlertTrackingDetailsList(alertId,false);
				if(commonMethodsUtilService.isListOrSetValid(list1)){
					for (Object[] param : list1) {
						Long statusId= commonMethodsUtilService.getLongValueForObject(param[1]);
						if(noList){
							if(statusId == 1L)//for no assigned member alerts pending status
								list.add(param);
						}
						if(statusId == 8L)// verification status
							list.add(param);
					}					
				}
			//}
			SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm:ss");
			SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
			if(list != null && list.size() > 0){   
				for(Object[] param : list){
					statusOrderMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[10]));
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
						alertCommentVO.setOrderNo(commonMethodsUtilService.getLongValueForObject(param[10]));
						if(alertTrackingDocumentsMap != null && alertTrackingDocumentsMap.size() > 0 && param[11] != null && alertTrackingDocumentsMap.get((Long)param[11]) != null){
							alertCommentVO.setDocList(alertTrackingDocumentsMap.get((Long)param[11]));
						}
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
						alertCommentVO.setOrderNo(commonMethodsUtilService.getLongValueForObject(param[10]));
						if(alertTrackingDocumentsMap != null && alertTrackingDocumentsMap.size() > 0 && param[11] != null && alertTrackingDocumentsMap.get((Long)param[11]) != null){
							alertCommentVO.setDocList(alertTrackingDocumentsMap.get((Long)param[11]));
						}
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
					commentVO.setOrderNo(statusOrderMap.get(commentVO.getStatusId()));
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
				List<AlertCommentVO> tempList = new ArrayList<AlertCommentVO>(0);
				List<Long> statusIdList = alertTrackingDAO.lastUpdatedstatus(alertId);
				for(AlertCommentVO param : finalList){  
					if(param.getStatusId().longValue() == statusIdList.get(0)){
						finalList.remove(param);
						finalList.add(param);  
					}
					tempList.add(param);
				}
				
				if(commonMethodsUtilService.isListOrSetValid(tempList)){
					Collections.sort(tempList,new Comparator<AlertCommentVO>() {
						public int compare(AlertCommentVO o1, AlertCommentVO o2) {
							if(o1.getOrderNo() != null && o1.getOrderNo()>0L && o2.getOrderNo() != null && o2.getOrderNo()>0L)
								return o1.getOrderNo().compareTo(o2.getOrderNo());
							else
								return 0;
						}
					});
					
					finalList.clear();
					finalList.addAll(tempList);
				}
				
			}  
			Object[] statusIdAndNme = alertDAO.getAlertStatus(alertId);
			if(finalList != null && finalList.size() > 0){
				for(AlertCommentVO param : finalList){
					param.setCurrentStsId(commonMethodsUtilService.getLongValueForObject(statusIdAndNme[0]));
					param.setCurrentSts(commonMethodsUtilService.getStringValueForObject(statusIdAndNme[1]));
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
					 vo.setName(commonMethodsUtilService.getStringValueForObject(params[1]).toString());
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
					 vo.setName(commonMethodsUtilService.getStringValueForObject(params[1]).toString());
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
				AlertDataVO alertVo =(AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(dataList, "id", commonMethodsUtilService.getStringValueForObject(params[0]).toString());
				if(alertVo == null)
				{
					alertVo = new AlertDataVO();
					alertVo.setId((Long)params[0]);
					dataList.add(alertVo);
				}
				AlertDataVO candidateVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(alertVo.getSubList(), "id", commonMethodsUtilService.getStringValueForObject(params[1]).toString());
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
				 candidateVO.setMembershipNo(params[22] != null ? params[22].toString() : "");
				 if(dataList != null && dataList.size() > 0){
					 setCurrentDesignationForCadre(dataList.get(0).getSubList(), tdpCadreIdsList);

					 List<Object[]> publicRepDertails = tdpCadreCandidateDAO.getPublicRepresentativeDetailsByCadreIds(tdpCadreIdsList);
						if(publicRepDertails != null && publicRepDertails.size() > 0){
							for (Object[] objects : publicRepDertails) {
								AlertDataVO matchedCadreVO = getMatchedCadreVO(dataList.get(0).getSubList(),(Long)objects[0]);
								if(matchedCadreVO != null){
									if(matchedCadreVO.getDesignation().trim().isEmpty())
										matchedCadreVO.setDesignation(objects[2].toString());
									else
										matchedCadreVO.setDesignation(matchedCadreVO.getDesignation()+" , "+objects[2].toString());
								}
							}
						}
				 }
				 
			}
			
		}
		
	}
	public ResultStatus saveAlertAssignedUser(final AlertVO inputVO,final Long userId)
	{
		final ResultStatus rs = new ResultStatus();
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				/*protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				Alert alert = alertDAO.get(inputVO.getAlertTypeId()); 
	        	String mobilenumber ="9581434970";
	        	boolean smsStatus =false;
				String message = commonMethodsUtilService.escapeUnicode("Alert is assigned to you,please follow up and resolve \nDescription:\n" +StringEscapeUtils.unescapeHtml(alert.getTitle()));
				smsStatus =	smsSenderService.sendSmsForAssignedLeader(commonMethodsUtilService.getUniCodeMessage(StringEscapeUtils.unescapeJava(message)), false, mobilenumber);
				System.out.println(smsStatus);
			}*/
			
		        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
		        	  // Changing Alert Status Pending to Notified first time When Candidate is going to assigned for that alert 
					if(inputVO.getAlertTypeId() != null && inputVO.getAlertTypeId().longValue() > 0l){
						 List<Long> cadreIds = alertAssignedDAO.checkCadreAssignedForAlert(inputVO.getAlertTypeId());
						  if(cadreIds == null || cadreIds.size() == 0){
							  if(inputVO.getIdNamesList() != null && inputVO.getIdNamesList().size() > 0){
								  Alert alert = alertDAO.get(inputVO.getAlertTypeId()); 
								  alert.setAlertStatusId(2l);//Notified Status
								  alertDAO.save(alert);
								  
								    AlertTrackingVO alertTrackingVO = new AlertTrackingVO();
							    	alertTrackingVO.setUserId(userId);
							    	alertTrackingVO.setAlertCommentId(null);
							    	alertTrackingVO.setAlertStatusId(2l);//Notified Status
							    	alertTrackingVO.setAlertId(inputVO.getAlertTypeId());
							    	alertTrackingVO.setAlertTrackingActionId(IConstants.ALERT_ACTION_STATUS_CHANGE);
							        saveAlertTrackingDetails(alertTrackingVO);	
							  }
						  }
					}
				  
				   DateUtilService date = new DateUtilService();
				  List<Long> existCadreIds = new ArrayList<Long>();
				  List<Long> tdpCadreIds = new ArrayList();
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
							alertAssigned.setSmsStatus("N");
							alertAssigned = alertAssignedDAO.save(alertAssigned);
						    Long alertId = 0l;
							String description = "";
							String mobilenumber ="";
							boolean smsStatus = true;
							if(alertAssigned != null){
								Long assignedId = alertAssigned.getAlertAssignedId();
								List<Object[]> leaderDtls = alertAssignedDAO.getLeaderDtls(assignedId);
								if(leaderDtls != null && leaderDtls.size()>0){
									for(Object[] param :leaderDtls){
										alertId =commonMethodsUtilService.getLongValueForObject(param[0]);
										description =commonMethodsUtilService.getStringValueForObject(param[1]);
										mobilenumber=commonMethodsUtilService.getStringValueForObject(param[2]);
									}
									
								}
								String message = commonMethodsUtilService.escapeUnicode("Alert is assigned to you,please follow up and resolve \n\nDescription:\n" +StringEscapeUtils.unescapeHtml(description));
								smsStatus =	smsSenderService.sendSmsForAssignedLeaderInTelugu(commonMethodsUtilService.getUniCodeMessage(StringEscapeUtils.unescapeJava(message)), false, mobilenumber);
								//smsStatus =	smsSenderService.sendSmsForAssignedLeaderInTelugu(commonMethodsUtilService.getUniCodeMessage(StringEscapeUtils.unescapeJava(message)), false, "7207785117");

								
								 if(smsStatus == true){
									 LOG.error(" Sms Status sending successfully  ");
									 LOG.error(description);
									 LOG.error(mobilenumber);
									 alertAssignedDAO.updateAlertSmsStatus(assignedId);
								 }else if(smsStatus == false){
									 LOG.error("Sms Status failed");
									 LOG.error( description);   
									 LOG.error(mobilenumber);
								 }
								
							}
							
						 }
					 }
				 }
				    rs.setResultCode(0);
					if(existCadreIds != null && existCadreIds.size() > 0)
					rs.setMessage(Integer.toString(existCadreIds.size()));
				 }
			});
		
		} catch (Exception e) {
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
				AlertDataVO alertVo =(AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(dataList, "id", commonMethodsUtilService.getStringValueForObject(params[0]).toString());
				if(alertVo == null)
				{
					alertVo = new AlertDataVO();
					alertVo.setId((Long)params[0]);
					dataList.add(alertVo);
				}
				AlertDataVO candidateVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(alertVo.getSubList(), "id", commonMethodsUtilService.getStringValueForObject(params[1]).toString());
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
				AlertDataVO alertVo =(AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(dataList, "id", commonMethodsUtilService.getStringValueForObject(params[0]).toString());
				if(alertVo == null)
				{
					alertVo = new AlertDataVO();
					alertVo.setId((Long)params[0]);
					dataList.add(alertVo);
					alertIds.add(alertVo.getId());
				}
				AlertDataVO candidateVO = (AlertDataVO) setterAndGetterUtilService.getMatchedVOfromList(alertVo.getSubList(), "id", commonMethodsUtilService.getStringValueForObject(params[1]).toString());
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
			
			if(dataList != null && dataList.size() > 0){
				List<Object[]> publicRepDertails = tdpCadreCandidateDAO.getPublicRepresentativeDetailsByCadreIds(tdpCadreIdsList);
				if(publicRepDertails != null && publicRepDertails.size() > 0){
					for (Object[] objects : publicRepDertails) {
						AlertDataVO matchedCadreVO = getMatchedCadreVO(dataList.get(0).getSubList(),(Long)objects[0]);
						if(matchedCadreVO != null){
							if(matchedCadreVO.getDesignation().trim().isEmpty())
								matchedCadreVO.setDesignation(objects[2].toString());
							else
								matchedCadreVO.setDesignation(matchedCadreVO.getDesignation()+" , "+objects[2].toString());
						}
					}
				}
			}
				
			 
			 
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
	//swa.
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
					 vo.setUname(commonMethodsUtilService.getStringValueForObject(objects[1]).toString());//first name
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
	
	public List<StatusTrackingVO> getAlertAssignedCandidateForDashBoard(Long alertId,Long stateId,Long alertTypeId,String fromDateStr,String toDateStr)
	{
		LOG.info("Entered in getAlertAssignedCandidate() method");   
		List<StatusTrackingVO> resultList = new ArrayList<StatusTrackingVO>(); ;
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Object[]> list = alertAssignedDAO.getAlertAssignedCandidateForDashBoard(alertId,stateId,alertTypeId,fromDate,toDate);
			 if(list !=null && list.size()>0){  
				 
				 for (Object[] objects : list) {
					 StatusTrackingVO vo= new StatusTrackingVO();
					 vo.setId(Long.valueOf(objects[0].toString()));//candidate id
					 vo.setUname(commonMethodsUtilService.getStringValueForObject(objects[1]).toString());//first name
					 vo.setCount(commonMethodsUtilService.getLongValueForObject(objects[2]));       
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
	public List<StatusTrackingVO> getAlertInvolvedCandidate(Long cadreId,Long stateId,Long alertTypeId,String fromDateStr,String toDateStr)
	{
		LOG.info("Entered in getAlertAssignedCandidate() method");
		List<StatusTrackingVO> resultList = new ArrayList<StatusTrackingVO>(); ;
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Long> alertIdList = alertAssignedDAO.getTotalAlertsRelatedToCadre(cadreId,stateId,alertTypeId,fromDate,toDate);
			List<Object[]> list = alertCandidateDAO.getAlertInvolvedCandidate(alertIdList,stateId,alertTypeId,fromDate,toDate);
			 if(list !=null && list.size()>0){             
				 
				 for (Object[] objects : list) {
					 StatusTrackingVO vo= new StatusTrackingVO();
					 vo.setId(Long.valueOf(objects[0].toString()));//candidate id
					 vo.setUname(commonMethodsUtilService.getStringValueForObject(objects[1]).toString());//first name
					 vo.setCount(commonMethodsUtilService.getLongValueForObject(objects[2]));    
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
			//List<Object[]> statusList = alertStatusDAO.getAllStatus();
			List<Object[]> statusList = alertDepartmentStatusDAO.getAlertStatusByAlertType(alertTypeId);
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
			//List<Object[]> statusList = alertStatusDAO.getAllStatus();
			List<Object[]> statusList = alertDepartmentStatusDAO.getAlertStatusByAlertType(alertTypeId);
			
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
									updateAlertForNewsInUpdateStatus(inputVO,null);//only status change
								}else if(inputVO !=null && inputVO.getType().trim().equalsIgnoreCase("update")){
									//updateAlertForNewsInUpdateStatus(inputVO);				
									List<Alert> alertList = alertDAO.getAlertDetailsOfNewstype(inputVO.getId());				
									alert = alertList.get(0);
									updateAlertForNewsInUpdateStatus(inputVO,alert);//Updation				
								}else{
									updateAlertForNewsInUpdateStatus(inputVO,new Alert());//like saving
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
	
	Alert globalAlert=null;
	public void updateAlertForNewsInUpdateStatus(final ActionableVO inputVO,final Alert oldAlert){
		try {
			
			if(oldAlert == null){
				//int updateAlert = alertDAO.updateAlertStatusOfNews(inputVO.getId(),inputVO.getStatusId());
				
				String updateAlert = updateActionableItemsToActionNotRequired(inputVO);
				
			}
			else{	
				
				 final Map<Long,IdNameVO> deptMap = new HashMap<Long, IdNameVO>();
				
				//saveAlertForNewsInUpdateStatus(inputVO,oldAlert);				
				
				 transactionTemplate.execute(new TransactionCallbackWithoutResult() 
			    	{
					  public void doInTransactionWithoutResult(TransactionStatus status){
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
							 
							 if(inputVO.getInsertedTime() != null)
								 alert.setCreatedTime(inputVO.getInsertedTime());
							 alert.setAlertStatusId(inputVO.getStatusId());
							 alert.setEditionTypeId(inputVO.getEditionTypeId() != null && inputVO.getEditionTypeId() > 0l ? inputVO.getEditionTypeId() : null);	
							 alert.setEditionId(inputVO.getEditionId() !=null && inputVO.getEditionId()>0l ? inputVO.getEditionId():null);
							// alert.setCreatedBy(inputVO.getUserId());
							 
						 }				 
						 alert.setAlertTypeId(inputVO.getAlertType());
						 
						 setImpactId(alert,inputVO);
						 
						 //alert.setImpactLevelId(inputVO.getRegionScopeId());
						 
						 //if(alert.getImpactLevelId() !=  null && alert.getImpactLevelId().longValue() ==3L && inputVO.getDistrictId() != null && inputVO.getDistrictId().longValue()>0L){ //only new district ids
						
						 if(inputVO.getDistrictId() != null && inputVO.getDistrictId().longValue()>0L){
							if(inputVO.getDistrictId().longValue() == IConstants.CNP_VISHAKAPATTANAM_RURAL_DISTRICT_ID){//334L
								inputVO.setDistrictId(517L);
								inputVO.setRegionScopeValue(inputVO.getDistrictId());
						 	}
							else if(inputVO.getDistrictId().longValue() == IConstants.CNP_MANCHERIAL_DISTRICT_ID){
								inputVO.setDistrictId(518L);
								inputVO.setRegionScopeValue(inputVO.getDistrictId());
							}
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
						 alert.setTvNewsChannelId(inputVO.getTvNewsChannelId() !=null && inputVO.getTvNewsChannelId() >0 ? inputVO.getTvNewsChannelId():null);
						 
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
						
						alert.setNewsUserId(inputVO.getNewsUserId());
						
						 alert = alertDAO.save(alert);
						 
						 if(alert !=null){
							 globalAlert = alert;
						 }
							
						 
						 AlertTrackingVO alertTrackingVO = new AlertTrackingVO();
						// alertTrackingVO.setAlertUserTypeId(alert.getAlertSourceId());
						 alertTrackingVO.setAlertStatusId(alert.getAlertStatusId());
						 alertTrackingVO.setAlertId(alert.getAlertId());
						 alertTrackingVO.setAlertTrackingActionId(IConstants.ALERT_ACTION_STATUS_CHANGE);
						 saveAlertTrackingDetails(alertTrackingVO)	;
						 
						 
						 //Deleting Candidates If Already there for Alert
							List<Long> alertCandidateIds = alertCandidateDAO.getAlertCandidatesForUpdate(alert.getAlertId());
							
							if(alertCandidateIds !=null && alertCandidateIds.size()>0){
								alertCandidateDAO.deleteAlertCandidatesForUpdate(alertCandidateIds);
							}
						 
						 
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
							 List<Object[]> tdpCadres = null;
							 if(candidateIds != null && candidateIds.size() > 0)
								 tdpCadres = tdpCadreCandidateDAO.getTdpCadreIdsOfCandidates(candidateIds);
							 
							 Map<Long,Long> tdpcadreMap = new HashMap<Long, Long>(0);
							 if(tdpCadres !=null && tdpCadres.size()>0){
								 for (Object[] obj : tdpCadres) {							
									 tdpcadreMap.put((Long)obj[0], obj[1] !=null ? (Long)obj[1]:null);
								}
							 }
							 							 							 
							
							 
							//Adding Involved candidates For alert
							for(ActionableVO vo : inputVO.getActionableVoList())
							 {
								 if(vo != null && vo.getId()!= null && vo.getId() > 0)
								 {
									 AlertCandidate alertCandidate = new AlertCandidate();
									 alertCandidate.setAlertId(alert.getAlertId());
									 alertCandidate.setAlertImpactId(vo.getBenefitId());
									 alertCandidate.setCandidateId( vo.getCandidateId() != null && vo.getCandidateId() > 0l ?vo.getCandidateId():null);//pacandidate
									 if(vo.getCandidateId() !=null && vo.getCandidateId()>0l && tdpcadreMap !=null && tdpcadreMap.size()>0){
										 alertCandidate.setTdpCadreId(tdpcadreMap.get(vo.getCandidateId()));
									 }
									 
									 alertCandidate.setNewsCandidateId(vo.getNewsCandidateId() != null && vo.getNewsCandidateId() > 0l ?vo.getNewsCandidateId():null);
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
									 
									 if(vo.getCategoryList() !=null && vo.getCategoryList().size()>0){
										 for (String category : vo.getCategoryList()) {
											 if(alertCandidate.getCategory() !=null && !alertCandidate.getCategory().trim().isEmpty()){
												 alertCandidate.setCategory(alertCandidate.getCategory()+","+category);
											 }else{
												 alertCandidate.setCategory(category);
											 }
											
										}										 
									 }
									 
									 alertCandidate.setOrganization(vo.getOrganization());
									 alertCandidate.setNewsOrganizationId(vo.getNewsOrganizationId() != null && vo.getNewsOrganizationId() > 0l ?vo.getNewsOrganizationId():null);
									 alertCandidate.setIsDepartment(vo.getIsDepartment());
									 alertCandidateDAO.save(alertCandidate);
									 
									 
									// Single Organization Adding for every alert 
									 
									 if(alert.getAlertTypeId() !=null && alert.getAlertTypeId() == 2l && vo.getIsDepartment() !=null &&
											  !vo.getIsDepartment().isEmpty() && vo.getIsDepartment().equalsIgnoreCase("Y")){
										 if(vo.getNewsOrganizationId() !=null && vo.getNewsOrganizationId()>0l){									 
											 IdNameVO VO = new IdNameVO();									 
											 VO.setId(vo.getNewsOrganizationId());
											 VO.setOrderId(vo.getBenefitId());									
											 deptMap.put(vo.getNewsOrganizationId(), VO);									
										 }
									 }									 									
								 }		
								 								 								
							 }
						 }						 
			      }
				});
				
				if(deptMap !=null && deptMap.size()>0 )
					updateGovtDepartment(deptMap,globalAlert);
				
				
			}
		} catch (Exception e) {
			LOG.error("error in updateAlertForNewsInUpdateStatus() method",e);
		}
	}
	
	public void updateGovtDepartment(Map<Long,IdNameVO> deptMap,Alert alert ){
		try{
			
			if(deptMap !=null && deptMap.size()>0){
				
				Long newsDeptId = 0l;
				String isMultiple= "N";
				
					//If only one Organization
					if(deptMap.size() ==1){
						
						for (Entry<Long, IdNameVO> obj : deptMap.entrySet()) {
							newsDeptId = obj.getKey();
						} 
						
					}else{
						
						isMultiple = "Y";
						
						for (Entry<Long, IdNameVO> obj : deptMap.entrySet()) {
							
							IdNameVO vo  = obj.getValue();
							
							//If any Organization is Negative
							if(vo.getOrderId() !=null && vo.getOrderId() >0l && vo.getOrderId() ==2l){ //benefitId
								newsDeptId = vo.getId();//organizationId
							}																				
						}
						
						//If Organizations all are positive
						if(newsDeptId <=0l){																		
							newsDeptId = new ArrayList<IdNameVO>(deptMap.values()).get(0).getId();
						}
					}
					
					if(newsDeptId !=null && newsDeptId>0l){																				
						List<Long> deptIds = govtDepartmentDAO.getGovtDepartmentIdsOfNewsDept(new ArrayList<Long>(Arrays.asList(newsDeptId)));
						
						if(deptIds !=null && deptIds.size()>0){
							int cnt = alertDAO.setDepartmentOfAlert(deptIds.get(0),isMultiple,alert.getAlertId()); //Like saving Not Updation
						}
																	
					}
					
				}
			
		}catch(Exception e){
			LOG.error("error in updateGovtDepartment() method",e);
		}
	}
	
	public String saveAlertForNewsInUpdateStatus(final ActionableVO inputVO,final Alert oldAlert){
		
		String finalResult = new String();
		
		try {
			
			finalResult = (String) transactionTemplate.execute(new TransactionCallback() {
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
					// alertTrackingVO.setAlertUserTypeId(alert.getAlertSourceId());
					 alertTrackingVO.setAlertStatusId(alert.getAlertStatusId());
					 alertTrackingVO.setAlertId(alert.getAlertId());
					 alertTrackingVO.setAlertTrackingActionId(IConstants.ALERT_ACTION_STATUS_CHANGE);
					 saveAlertTrackingDetails(alertTrackingVO)	;
					 
					 
					 //Deleting Candidates If Already there for Alert
						List<Long> alertCandidateIds = alertCandidateDAO.getAlertCandidatesForUpdate(alert.getAlertId());
						
						if(alertCandidateIds !=null && alertCandidateIds.size()>0){
							alertCandidateDAO.deleteAlertCandidatesForUpdate(alertCandidateIds);
						}
					 
					 
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
						 
						//Adding Involved candidates For alert
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
								 alertCandidate.setNewsOrganizationId(vo.getNewsOrganizationId());
								 alertCandidate.setIsDepartment(vo.getIsDepartment());
								 alertCandidateDAO.save(alertCandidate);
							 }						
						 }														
					 }
				return "success";
		      }
			});
			
		} catch (Exception e) {
			LOG.error("error in saveAlertForNewsInUpdateStatus() method",e);
		}
		return finalResult;
	}
	
	public String updateActionableItemsToActionNotRequired(ActionableVO VO)//Long categoryTypeId,Long statusId)
	{
		String result=new String();
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			String currentDate = dateUtilService.getCurrentDateInStringFormatNew("yyyy-MM-dd");
			
			String categoryCreatedDate = null;
			Long alertId=null;
			if(VO.getId() !=null && VO.getId().longValue()>0){
				//Date categoryCreatedDateObj  
				List<Object[]> objList = alertDAO.getAlertCreatedDate(VO.getId());					
				Date categoryCreatedDateObj=null;
				if(objList !=null && objList.size()>0){
					
					alertId = objList.get(0)[0] !=null ?  (Long)objList.get(0)[0]:null;
					categoryCreatedDateObj= objList.get(0)[1] !=null ?  (Date)objList.get(0)[1]:null;
				}
				
				if(categoryCreatedDateObj !=null){
					categoryCreatedDate =sdf.format(categoryCreatedDateObj);
				}			
			}
			
			int updateAlert=0;
			if(categoryCreatedDate !=null && !categoryCreatedDate.trim().isEmpty() && categoryCreatedDate.trim().equalsIgnoreCase(currentDate)){							
				updateAlert = alertDAO.updateAlertStatusOfNewsForDelete(alertId);//
			}else{
				updateAlert = alertDAO.updateAlertStatusOfNews(VO.getId(),VO.getStatusId());
				if(updateAlert>0){
					AlertTrackingVO alertTrackingVO = new AlertTrackingVO();
					 alertTrackingVO.setAlertStatusId(VO.getStatusId());
					 alertTrackingVO.setAlertId(alertId);
					 alertTrackingVO.setAlertTrackingActionId(1l);
					 
					 //tracking saving method
					 saveAlertTrackingDetails(alertTrackingVO)	;
				}
			}
			if(updateAlert > 0){
				result ="success";
			}
		} catch (Exception e) {
			LOG.error("error in updateActionableItemsToActionNotRequired() method",e);
		}
		return result;
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
			Map<Long,Long> constIdAndNoMap = new HashMap<Long,Long>();
			if(Location.equalsIgnoreCase("Constituency")){
				List<Object[]> constOrderList = delimitationConstituencyDAO.getConstituencyNumbersForConstituenctIds(locaionIds);
				if(constOrderList != null && constOrderList.size() > 0){
					for(Object[] param : constOrderList){
						constIdAndNoMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
					}
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
		//	Map<Long,Long> statusIdAndCountMap = new HashMap<Long,Long>();
			//get all the alert category for  building the template
			List<Object[]> categoryList = alertCategoryDAO.getAllCategory(); 
			
			//get all the alert count group by Location then status then category.
			Map<Long,Map<Long,String>> locationIdAndStatusIdAndSatatusMap = new LinkedHashMap<Long,Map<Long,String>>();
			Map<Long,String> statusIdAndStatusNameMap = null;//new LinkedHashMap<Long,String>();
			
			//Map<Long,String> statusIdAndNameMap = new HashMap<Long,String>();
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
			if(superFinalList != null && superFinalList.size() > 0){
				if(Location.equalsIgnoreCase("Constituency")){
					for(AlertVO param : superFinalList){
						if(constIdAndNoMap.get(param.getLocationId()) != null){
							param.setConstituencyNo(constIdAndNoMap.get(param.getLocationId()));
						}   
					}
					Collections.sort(superFinalList,new Comparator<AlertVO>() {
						public int compare(AlertVO o1, AlertVO o2) {
							if(o1.getConstituencyNo() != null && o1.getConstituencyNo() > 0L && o2.getConstituencyNo() != null && o2.getConstituencyNo() > 0L)
								return o1.getConstituencyNo().compareTo(o2.getConstituencyNo());
							else
								return 0;
						}
					});
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
	public List<AlertVO> getTotalAlertGroupByLocationThenCategory(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, String group,Long alertTypeId, Long editionId){
		LOG.info("Entered in getTotalAlertGroupByLocationThenCategory() method of AlertService{}");
		try{  
			Date fromDate = null;  
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Long> alertTypeList = new ArrayList<Long>();
			List<Long> editionList = new ArrayList<Long>();
			if(alertTypeId != null){
				if(alertTypeId.longValue() == 0L){
					
				}else{
					alertTypeList.add(alertTypeId);
				}
			}
			
			if(editionId != null){
				if(editionId.longValue() == 0L){
					
				}else if(editionId.longValue() == 1L){
					editionList.add(editionId);
				}else if(editionId.longValue() == 2L){
					editionList.add(editionId);
					editionList.add(3L);
				}
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
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByLocation(fromDate, toDate, stateId, scopeIdList, "One",userAccessLevelId, userAccessLevelValues,alertTypeList,editionList);
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
			List<Object[]> alertCountGrpByLocList = alertDAO.getTotalAlertGroupByLocation(fromDate, toDate, stateId, scopeIdList, "two",userAccessLevelId, userAccessLevelValues,alertTypeList,editionList);    
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
	public List<AlertVO> getTotalAlertGroupByLocationThenStatus(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, String group,Long alertTypeId,Long editionId,String filterType,List<Long> districtIds){
		List<AlertVO> finalResultList = new ArrayList<AlertVO>(0);
		try{
			List<AlertVO> resultList = getRequiredData(fromDateStr,toDateStr,stateId,scopeIdList,activityMemberId,group,alertTypeId,editionId,filterType,districtIds,"");
			    finalResultList.addAll(resultList);
			if(filterType != null && filterType.equalsIgnoreCase("District") && districtIds.size() ==0l){
				List<AlertVO> stateDataList = getRequiredData(fromDateStr,toDateStr,stateId,scopeIdList,activityMemberId,group,alertTypeId,editionId,"State",districtIds,"State");
				setResultToFinalList(stateDataList,finalResultList,"State Level");
			}else if(filterType != null && filterType.equalsIgnoreCase("Constituency")){
				List<Long> scopeIds = new ArrayList<Long>();
				scopeIds.add(2l);
				scopeIds.add(8l);
				List<AlertVO> districtDataList = getRequiredData(fromDateStr,toDateStr,stateId,scopeIds,activityMemberId,group,alertTypeId,editionId,"District",districtIds,"District");
				setResultToFinalList(districtDataList,finalResultList,"District/GMC CORP Impact Level");
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getTotalAlertGroupByLocationThenStatus() method of AlertService{}");	
		}
		return finalResultList;
	}
	public void setResultToFinalList(List<AlertVO> list,List<AlertVO> finalResultList,String level){
		try{
			if(list != null && list.size() > 0){
				AlertVO VO = new AlertVO();
				boolean flag = true;
				for(AlertVO locationVO:list){
					if(flag){
						VO.setStatusId(0l);
						VO.setStatus(level);
						VO.setCount(0l);
						if(locationVO != null && locationVO.getSubList1().size() > 0){
							for(AlertVO statusVO:locationVO.getSubList1()){
								AlertVO sttuVO = new AlertVO();
								sttuVO.setCategoryId(statusVO.getCategoryId());
								sttuVO.setCategory(statusVO.getCategory());
								if(VO.getSubList1() == null){
									VO.setSubList1(new ArrayList<AlertVO>());
								}
								VO.getSubList1().add(sttuVO);
								
							}
						}
					}
					VO.setCount(VO.getCount()+locationVO.getCount());
					VO.getDeptIdList().add(locationVO.getStatusId());
					if(VO.getSubList1() != null && VO.getSubList1().size() > 0){
						for(AlertVO statusVO:VO.getSubList1()){
							AlertVO matchVO = getStatusMatchVO(locationVO.getSubList1(),statusVO.getCategoryId());
							 if(matchVO != null){
								 statusVO.setCategoryCount(statusVO.getCategoryCount()+matchVO.getCategoryCount());
							 }
						}
					}
					flag = false;
				}
				finalResultList.add(VO);
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setResultToFinalList() method of AlertService{}");	
		}
	}
	public AlertVO getStatusMatchVO(List<AlertVO> resultList,Long statusId){
		try{
			if(resultList == null || resultList.size() == 0)
				return null;
			for(AlertVO vo:resultList){
				if(vo.getCategoryId().equals(statusId)){
					return vo;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getStatusMatchVO() method of AlertService{}");	
		}
		return null;
	}
	public List<AlertVO> getRequiredData(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, String group,Long alertTypeId,Long editionId,String filterType,List<Long> districtIds,String requiredLevel){
		LOG.info("Entered in getTotalAlertGroupByLocationThenStatus() method of AlertService{}");
		try{  
			Date fromDate = null;        
			Date toDate = null; 
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Long> alertTypeList = new ArrayList<Long>();
			List<Long> editionList = new ArrayList<Long>();
			if(alertTypeId != null){
				if(alertTypeId.longValue() == 0L){
					
				}else{
					alertTypeList.add(alertTypeId);
				}
			}
			
			if(editionId != null){
				if(editionId.longValue() == 0L){
					
				}else if(editionId.longValue() == 1L){
					editionList.add(editionId);
				}else if(editionId.longValue() == 2L){
					editionList.add(editionId);
					editionList.add(3L);
				}
			}
			AlertVO alertVO = null;    
			List<AlertVO> alertVOs = null;//new ArrayList<AlertVO>();
			Map<Long,Long> locationIdAndCountMap = new HashMap<Long,Long>();
			//get all the alert status for  building the template
			//List<Object[]> statusList = alertStatusDAO.getAllStatus();
			List<Object[]> statusList = alertDepartmentStatusDAO.getAlertStatusByAlertTypeId(alertTypeId);
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
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByLocationThenStatus(fromDate, toDate, stateId, scopeIdList, "One", userAccessLevelId, userAccessLevelValues,alertTypeList,editionList,filterType,districtIds,requiredLevel);
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
			List<Object[]> alertCountGrpByLocList = alertDAO.getTotalAlertGroupByLocationThenStatus(fromDate, toDate, stateId, scopeIdList, "two", userAccessLevelId, userAccessLevelValues,alertTypeList,editionList,filterType,districtIds,requiredLevel);    
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
			//List<AlertCoreDashBoardVO> alrtyTypeList = new ArrayList<AlertCoreDashBoardVO>();
						
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
  public AlertOverviewVO getAlertOverviewDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr, Long alertType,Long editionType){
	  
	   AlertOverviewVO resultVO = new AlertOverviewVO();
	   Set<Long> locationValues = new HashSet<Long>(0);
	   Long locationAccessLevelId =0l;
	   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	   Date fromDate=null;
	   Date toDate = null;
	   List<Long> alertTypes = new ArrayList<Long>();
	   alertTypes.add(alertType);
	   List<Long> alertEditions = new ArrayList<Long>();
	   if(editionType.longValue() == 2L){
		   alertEditions.add(editionType);
		   alertEditions.add(3L);
	   }else{
		   alertEditions.add(editionType);
	   }
	   alertEditions.add(editionType);
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
			 }//swadhin
			//convert parliament into constituency.
			if(locationAccessLevelId.longValue() == 4L){
				List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(new ArrayList<Long>(locationValues));
				locationAccessLevelId = 5L;
				locationValues.clear();
				locationValues.addAll(parliamentAssemlyIds);      
			}
			 
		   List<Object[]> rtrnTtlAlrtObjLst = alertDAO.getAlertCntByAlertTypeBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate, "false",alertTypes,alertEditions);
		   Long totalAlertCnt = 0l;
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
				   }else if(statusId == 2l){
					   overViewVO.setGovtAlertCnt(alertCnt);
				   }
			   }
			   overViewVO.setTotalAlertCnt(totalAlertCnt);
		   }
		   overViewVO.setPartyAlertCntPer(calculatePercantage(overViewVO.getPartyAlertCnt(), totalAlertCnt));
		   overViewVO.setOtherAlertCntPer(calculatePercantage(overViewVO.getOtherAlertCnt(), totalAlertCnt));
		   overViewVO.setGovtAlertCntPer(calculatePercantage(overViewVO.getGovtAlertCnt(), totalAlertCnt));
		   //1
		   //for edition
		   Map<Long,AlertOverviewVO> alertTypeAndEditionDtlsVoMap = new HashMap<Long,AlertOverviewVO>();
		   List<Object[]> editionTypeList = editionTypeDAO.getEditionTypeList();
		   List<Object[]> alertTypeList = alertTypeDAO.getAlertType();
		   prepareTempForAlertTypeAndEdition(editionTypeList,alertTypeList,alertTypeAndEditionDtlsVoMap);
		   
		   List<Object[]> rtrnTtlAlrtGrpByEditionObjLst = alertDAO.getAlertCntByAlertTypeBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate, "true",alertTypes,alertEditions);
		   
		   if(rtrnTtlAlrtGrpByEditionObjLst != null && rtrnTtlAlrtGrpByEditionObjLst.size() > 0){
			   for(Object[] param : rtrnTtlAlrtGrpByEditionObjLst){
				   Long alertTypeId = commonMethodsUtilService.getLongValueForObject(param[0]);
				   Long editionId = commonMethodsUtilService.getLongValueForObject(param[2]);
				   Long alertcnt = commonMethodsUtilService.getLongValueForObject(param[4]);
				   if(alertTypeAndEditionDtlsVoMap.get(alertTypeId) != null ){
					   AlertOverviewVO alertTypeVO = getMatchVOForEdition(alertTypeAndEditionDtlsVoMap.get(alertTypeId).getEditionList(),editionId);
					   if(alertTypeVO != null ){
						   alertTypeVO.setEditionCnt(alertcnt);
						   alertTypeVO.setAlertTypeId(alertTypeId);  
					   }
				   } 
			   }  
		   }
		   //2  
		   Map<Long,AlertOverviewVO> alertStatusMap = new HashMap<Long, AlertOverviewVO>(0);
		   //List<Object[]> rtrnAlrtStatusObjLst = alertStatusDAO.getAllStatus();
		   List<Object[]> rtrnAlrtStatusObjLst = alertDepartmentStatusDAO.getAlertStatusByAlertTypeId(alertType);
		   List<Object[]> rtrnAlrtSttsWsCntObjLst = alertDAO.getAlertCntByAlertStatusBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate, "false",alertTypes,alertEditions);
		   prepareTemplateStatusWise(rtrnAlrtStatusObjLst,alertStatusMap);//Prepare Template 
		   if(rtrnAlrtSttsWsCntObjLst != null && rtrnAlrtSttsWsCntObjLst.size() > 0){
			   for(Object[] param:rtrnAlrtSttsWsCntObjLst){
				    if(alertStatusMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) != null ){
				    	alertStatusMap.get(commonMethodsUtilService.getLongValueForObject(param[0])).setStatusCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
				    	alertStatusMap.get(commonMethodsUtilService.getLongValueForObject(param[0])).setStatusCntPer(calculatePercantage(alertStatusMap.get(commonMethodsUtilService.getLongValueForObject(param[0])).getStatusCnt(), totalAlertCnt));
				    }
			   }  
		   } 
		   //modified code
		   Map<Long,AlertOverviewVO> statusTypeAndEditionDtlsVoMap = new HashMap<Long,AlertOverviewVO>();
		   prepareTempForAlertTypeAndEdition(editionTypeList,rtrnAlrtStatusObjLst,statusTypeAndEditionDtlsVoMap);
		   List<Object[]> rtrnAlrtSttsAndEditionWsCntObjLst = alertDAO.getAlertCntByAlertStatusBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate, "true",alertTypes,alertEditions);
		   
		   if(rtrnAlrtSttsAndEditionWsCntObjLst != null && rtrnAlrtSttsAndEditionWsCntObjLst.size() > 0){
			   for(Object[] param : rtrnAlrtSttsAndEditionWsCntObjLst){
				   Long statusTypeId = commonMethodsUtilService.getLongValueForObject(param[0]);
				   Long editionId = commonMethodsUtilService.getLongValueForObject(param[2]);
				   Long alertcnt = commonMethodsUtilService.getLongValueForObject(param[4]);
				   if(statusTypeAndEditionDtlsVoMap.get(statusTypeId) != null ){
					   AlertOverviewVO alertTypeVO = getMatchVOForEdition(statusTypeAndEditionDtlsVoMap.get(statusTypeId).getEditionList(),editionId);
					   if(alertTypeVO != null ){
						   alertTypeVO.setEditionCnt(alertcnt);
					   }
				   }
			   }
		   }
		   
		   
		   
		   
		   Map<Long,AlertOverviewVO> alertCategoryMap = new ConcurrentHashMap<Long,AlertOverviewVO>();
		   
		   List<Object[]> rtrnAlertCategoryObjLst = alertCategoryDAO.getAllCategoryOrderBy();
		   prepareAlertCategoryTemplate(rtrnAlertCategoryObjLst,rtrnAlrtStatusObjLst,alertCategoryMap);//Prepare Template ddddd
		   
		   List<Object[]> rtrnAlrCtgryCntobjLst = alertDAO.getAlertCntByAlertCategoryBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate,"false",alertTypes,alertEditions);
		 
		   if(rtrnAlrCtgryCntobjLst != null && !rtrnAlrCtgryCntobjLst.isEmpty() ){  
			  for(Object[] param:rtrnAlrCtgryCntobjLst)  {
				  if(alertCategoryMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) != null ){
					  alertCategoryMap.get(commonMethodsUtilService.getLongValueForObject(param[0])).setStatusCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
				    } 
			  }

		   }
		   List<Object[]> rtrnAlrtCtgryAndSttsWseCntObjLst = alertDAO.getAlertCntByAlertCategoryAndAlertStatusWiseBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate,alertTypes,alertEditions);
		 
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
		   
		   //modified code
		   Map<Long,AlertOverviewVO> categoryTypeAndEditionDtlsVoMap = new HashMap<Long,AlertOverviewVO>();
		   prepareTempForAlertTypeAndEdition(editionTypeList,rtrnAlertCategoryObjLst,categoryTypeAndEditionDtlsVoMap);
		   List<Object[]> rtrnAlrCtgryCntAndEditionobjLst = alertDAO.getAlertCntByAlertCategoryBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate,"true",alertTypes,alertEditions);
		   
		   if(rtrnAlrCtgryCntAndEditionobjLst != null && rtrnAlrCtgryCntAndEditionobjLst.size() > 0){
			   for(Object[] param : rtrnAlrCtgryCntAndEditionobjLst){
				   Long statusTypeId = commonMethodsUtilService.getLongValueForObject(param[0]);
				   Long editionId = commonMethodsUtilService.getLongValueForObject(param[2]);
				   Long alertcnt = commonMethodsUtilService.getLongValueForObject(param[4]);
				   if(categoryTypeAndEditionDtlsVoMap.get(statusTypeId) != null ){
					   AlertOverviewVO alertTypeVO = getMatchVOForEdition(categoryTypeAndEditionDtlsVoMap.get(statusTypeId).getEditionList(),editionId);
					   if(alertTypeVO != null ){
						   alertTypeVO.setEditionCnt(alertcnt);
					   }
				   }
			   }
		   }
		   /* Getting Alert Action Type Wise Data start */
	       Map<Long,AlertOverviewVO> alertActionMap = new LinkedHashMap<Long, AlertOverviewVO>();
		   List<Object[]> rtrnStatusObjList = actionTypeStatusDAO.getAlertActionTypeWiseStatus(0l);
		   prepareActionTypeTemplate(rtrnStatusObjList,alertActionMap);
		   List<Object[]> rtrnAlerCntObjLst = verificationStatusDAO.getAlertCountStatusWiseBasedOnActionType(locationAccessLevelId, locationValues, stateId, fromDate, toDate, alertTypes, alertEditions);
		   setActionWiseAlertCnt(rtrnAlerCntObjLst,alertActionMap);
		   if(alertActionMap != null && alertActionMap.size() > 0){
			   resultVO.getActionTypeList().addAll(new ArrayList<AlertOverviewVO>(alertActionMap.values()));
			   alertActionMap.clear();
		   }
		   /* End */
		   
		   
		   // preparing final result(1)
		   resultVO.setOverAllVO(overViewVO);  
		   //for party
		   if(alertTypeAndEditionDtlsVoMap.get(1L) != null){
			   resultVO.setTotalPartyList(alertTypeAndEditionDtlsVoMap.get(1L).getEditionList());
			   
		   }
		   mergeTwoEdition(resultVO.getTotalPartyList());
		   //for govt
		   if(alertTypeAndEditionDtlsVoMap.get(2L) != null){
			   resultVO.setTotalGovtList(alertTypeAndEditionDtlsVoMap.get(2L).getEditionList());
		   }
		   mergeTwoEdition(resultVO.getTotalGovtList());
		   //for others
		   if(alertTypeAndEditionDtlsVoMap.get(3L) != null){
			   resultVO.setTotalOtherList(alertTypeAndEditionDtlsVoMap.get(3L).getEditionList());
		   }
		   mergeTwoEdition(resultVO.getTotalOtherList());
		   
		   //(2)
		   if(alertStatusMap != null && alertStatusMap.size() > 0){
			   resultVO.getStatusList().addAll(alertStatusMap.values());
			   alertStatusMap.clear();
		   }
		   if(resultVO.getStatusList() != null && resultVO.getStatusList().size() > 0){
			   for(AlertOverviewVO param : resultVO.getStatusList()){
				   Long statusId = param.getStatusTypeId();
				   param.getEditionList().addAll(statusTypeAndEditionDtlsVoMap.get(statusId).getEditionList());
			   }
		   }
		   if(resultVO.getStatusList() != null && resultVO.getStatusList().size() > 0){
			   for(AlertOverviewVO param : resultVO.getStatusList()){
				   mergeTwoEdition(param.getEditionList());
			   }
		   }
		   
		   //remove alert category which does not contain alert count
            if(alertCategoryMap != null && alertCategoryMap.size() > 0){
            	for(Entry<Long,AlertOverviewVO> entry:alertCategoryMap.entrySet()){
            		 if(entry.getValue().getStatusCnt() == 0l){
            			 alertCategoryMap.remove(entry.getKey());
            		 }
            	}
            }
		   //(3)
		   if(alertCategoryMap != null && alertCategoryMap.size() > 0){
			   resultVO.getCategoryList().addAll(alertCategoryMap.values());
			   alertCategoryMap.clear();  
		   }
		   
		   if(resultVO.getCategoryList() != null && resultVO.getCategoryList().size() > 0){
			   for(AlertOverviewVO param : resultVO.getCategoryList()){
				   Long statusId = param.getStatusTypeId();
				   param.getEditionList().addAll(categoryTypeAndEditionDtlsVoMap.get(statusId).getEditionList());
			   }
		   }
		   
		   
		   if(resultVO.getCategoryList() != null && resultVO.getCategoryList().size() > 0){
			   for(AlertOverviewVO param : resultVO.getCategoryList()){
				   mergeTwoEdition(param.getEditionList());
			   }  
		   }
	   }catch(Exception e){
		   LOG.error("Error occured getAlertOverviewDetails() method of AlertService{}",e);
	   }
	   return resultVO;
   }
  public void mergeTwoEdition(List<AlertOverviewVO> editionVoList){
	  Long alertCnt = 0L;
	  if(editionVoList != null && editionVoList.size() > 0){
		  for(AlertOverviewVO param : editionVoList){
			  if(param.getEditionId().longValue() != 1){
				  alertCnt += param.getEditionCnt();
			  }
		  }
		  editionVoList.get(1).setEditionCnt(alertCnt);
		  editionVoList.remove(2);
		  editionVoList.remove(2);  
	  }
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
  public void prepareActionTypeTemplate(List<Object[]> objList,Map<Long,AlertOverviewVO> alertActionMap){
	  try{
		  if(objList != null && objList.size() > 0){
			  for(Object[] param:objList){
				  Long actionTypeId = commonMethodsUtilService.getLongValueForObject(param[0]);//actionTypeId
				  AlertOverviewVO actionVO = alertActionMap.get(actionTypeId);
				   if(actionVO == null){
					   actionVO = new AlertOverviewVO(); 
					   actionVO.setId(actionTypeId);
					   actionVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					   actionVO.setSubList1(new ArrayList<AlertOverviewVO>());
					   alertActionMap.put(actionTypeId, actionVO);
				   }
				   AlertOverviewVO statusVO = new AlertOverviewVO();
				   statusVO.setStatusTypeId(commonMethodsUtilService.getLongValueForObject(param[2]));
				   statusVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[3]));
				   actionVO.getSubList1().add(statusVO);
			  }
		  }
	  }catch(Exception e){
		  LOG.error("Error occured prepareActionTypeTemplate() method of AlertService{}",e);  
	  }
  }
  public void setActionWiseAlertCnt(List<Object[]> objList,Map<Long,AlertOverviewVO> actionMap){
	  try{
		  if(objList != null && objList.size() > 0){
			  for(Object[] param:objList){
				  Long actionTypeId = commonMethodsUtilService.getLongValueForObject(param[0]);
				  Long statusId = commonMethodsUtilService.getLongValueForObject(param[1]);
				  Long alertCnt = commonMethodsUtilService.getLongValueForObject(param[2]);
				  AlertOverviewVO actionVO = actionMap.get(actionTypeId);
				   if(actionVO != null){
					   actionVO.setAlertCnt(actionVO.getAlertCnt()+alertCnt);//overAll Alert Action Type Wise
					   AlertOverviewVO statusVO = getMatchVO(actionVO.getSubList1(), statusId);
					   if(statusVO != null){
						   statusVO.setAlertCnt(alertCnt);   
					   }
				   }
			  }
		  }
	  }catch(Exception e){
		  LOG.error("Error occured setActionWiseAlertCnt() method of AlertService{}",e);  
	  }
  }
	/* Swadhin Lenka
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertService#getTotalAlertGroupByDist(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.lang.Long)
	 */
	public List<AlertCommentVO> getTotalAlertGroupByDist(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId,Long alertTypeId, Long editionId){
		LOG.info("Entered in getTotalAlertGroupByDist() method of AlertService{}");
		try{
			Date fromDate = null;           
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Long> alertTypeList = new ArrayList<Long>();
			List<Long> editionList = new ArrayList<Long>();
			if(alertTypeId != null){
				if(alertTypeId.longValue() == 0L){
					
				}else{
					alertTypeList.add(alertTypeId);
				}
			}
			
			if(editionId != null){
				if(editionId.longValue() == 0L){
					
				}else if(editionId.longValue() == 1L){
					editionList.add(editionId);
				}else if(editionId.longValue() == 2L){
					editionList.add(editionId);
					editionList.add(3L);
				}
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
			List<Object[]> alertList = alertDAO.getTotalAlertGroupByDist(fromDate,toDate,stateId,scopeIdList,userAccessLevelId,userAccessLevelValues,alertTypeList,editionList);
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
	public List<AlertCoreDashBoardVO> getAlertDtls(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId, Long alertStatusId, Long alertCategoryId, Long activityMemberId, Long editionId,String isActionType,Long alertActionTypeId){
		LOG.info("Entered in getAlertDtls() method of AlertService{}");
		try{
			Date fromDate = null;      
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Long> editionList = new ArrayList<Long>();
			if(editionId != null){
				if(editionId.longValue() == 1L){
					editionList.add(editionId);
				}else if(editionId.longValue() == 2L){
					editionList.add(editionId);
					editionList.add(3L);
				}
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
			}//swadhin
			//convert parliament into constituency.
			if(userAccessLevelId.longValue() == 4L){
				List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(userAccessLevelValues);
				userAccessLevelId = 5L;
				userAccessLevelValues.clear();
				userAccessLevelValues.addAll(parliamentAssemlyIds);      
			}
			//DateUtilService dateUtilService = new DateUtilService();
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			List<Object[]> alertList = null;
			if(isActionType != null && isActionType.equalsIgnoreCase("Yes")){ //alertStatusId means alertActionTypeStatusId
			  alertList = verificationStatusDAO.getActionTypeAlertDetails(fromDate, toDate, stateId, alertTypeId, alertStatusId, userAccessLevelId, userAccessLevelValues, editionList,alertActionTypeId);
			}else{
			 alertList = alertDAO.getAlertDtls(fromDate, toDate, stateId, alertTypeId, alertStatusId, alertCategoryId, userAccessLevelId, userAccessLevelValues,editionList);	
			}
			setAlertDtls(alertCoreDashBoardVOs, alertList);
			
			return alertCoreDashBoardVOs;
		}catch(Exception e){
			e.printStackTrace();  
			LOG.error("Error occured getAlertDtls() method of AlertService{}");
		}
		return null;        
	}
	public void prepareTempForAlertTypeAndEdition(List<Object[]> editionTypeList,List<Object[]> alertTypeList,Map<Long,AlertOverviewVO> alertTypeAndEditionDtlsVoMap){
		if(editionTypeList != null && editionTypeList.size() > 0){
			for(Object[] param : alertTypeList){
				AlertOverviewVO categoryVO = new AlertOverviewVO();
				categoryVO.setAlertTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
				categoryVO.setAlertType(commonMethodsUtilService.getStringValueForObject(param[1]));
				categoryVO.getEditionList().addAll(getEditionList(editionTypeList));
				alertTypeAndEditionDtlsVoMap.put(categoryVO.getAlertTypeId(), categoryVO);
			}
		}
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
	public List<AlertOverviewVO> getEditionList(List<Object[]> editionTypeList){
		List<AlertOverviewVO> editionList = new ArrayList<AlertOverviewVO>();
		try{
			if(editionTypeList != null && editionTypeList.size() > 0){
				for(Object[] param : editionTypeList){
					AlertOverviewVO editionVO = new AlertOverviewVO();
					editionVO.setEditionId(commonMethodsUtilService.getLongValueForObject(param[0]));
					editionVO.setEdition(commonMethodsUtilService.getStringValueForObject(param[1]));
					editionList.add(editionVO);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return editionList;
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
	public AlertOverviewVO getMatchVOForEdition(List<AlertOverviewVO> statusList,Long editionId){
		try{
			   if(statusList == null || statusList.size() == 0)
				   return null;
			   for(AlertOverviewVO vo:statusList){
				   if(vo.getEditionId().equals(editionId)){
					   return vo;
				   }
			   }
		   }catch(Exception e){
			   LOG.error("Error occured getMatchVO() method of AlertService{}",e);  
		   }
		   return null;
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
 public List<AlertOverviewVO> getAlertCategoryDtlsLocationWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,Long alertType, Long editionTypeId) {
	 List<AlertOverviewVO> resultList = new ArrayList<AlertOverviewVO>();
	 Map<Long,AlertOverviewVO> categoryMap = new ConcurrentHashMap<Long, AlertOverviewVO>(0);
	 Set<Long> locationValues = new HashSet<Long>(0);
     Long locationAccessLevelId =0l;
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date fromDate=null;
     Date toDate = null;
     List<Long> alertTypeList = new ArrayList<Long>();
     List<Long> editionList = new ArrayList<Long>();
     if(alertType != null){
    	 if(alertType.longValue() == 0L){
    	 }else{
    		 alertTypeList.add(alertType);
    	 }
     }
     if(editionTypeId != null){
    	 if(editionTypeId.longValue() == 0L){
    	 }else if(editionTypeId.longValue() == 1L){
    		 editionList.add(editionTypeId);
    	 }else if(editionTypeId.longValue() == 2L){  
    		 editionList.add(editionTypeId);
    		 editionList.add(3L);
    	 }
     }
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
			 }//swadhin
			//convert parliament into constituency.
				if(locationAccessLevelId.longValue() == 4L){
					List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(new ArrayList<Long>(locationValues));
					locationAccessLevelId = 5L;
					locationValues.clear();
					locationValues.addAll(parliamentAssemlyIds);      
				}
			 
			  List<Object[]> rtrnAlertCategoryObjLst = alertCategoryDAO.getAllCategoryOrderBy();
			  List<Object[]> rtrnAlertImpactLevelObjLst = alertImpactScopeDAO.getAllAlertImpactLevel();
			  List<Object[]> rtrnAlertStatusObjLst = alertDepartmentStatusDAO.getAlertStatusByAlertTypeId(alertType);
			  prepareTemplate(rtrnAlertCategoryObjLst,rtrnAlertImpactLevelObjLst,rtrnAlertStatusObjLst,categoryMap);////Prepare Template 
			  List<Object[]> rtrnImpactLevelCntObjLst = alertDAO.getAlertCntByAlertCategoryAndImpactLevelWiseBasedOnUserAccessLevel(locationAccessLevelId,locationValues,stateId,fromDate, toDate,alertTypeList,editionList);
			  setAlertImpactLevelWiseAlertCnt(rtrnImpactLevelCntObjLst,categoryMap); 
			  List<Object[]> rtrnImpctLvlSttusWsCntObjLst = alertDAO.getAlertCntByAlertCategoryImpactLevelAndStatusWiseBasedOnUserAccessLevel(locationAccessLevelId,locationValues,stateId,fromDate, toDate,alertTypeList,editionList);
			  setStatusWiseAlertCnt(rtrnImpctLvlSttusWsCntObjLst,categoryMap);
			  
			  // merge mandal,muncipality,Village And Ward data 
			  if(categoryMap != null && categoryMap.size() > 0){
				  for(Entry<Long, AlertOverviewVO> entry : categoryMap.entrySet()){
					  AlertOverviewVO mandalMuncipalityVO = new AlertOverviewVO();
					  AlertOverviewVO villageWardVO = new AlertOverviewVO();
					  AlertOverviewVO mandalVO = getImpactLevelMatchVO(entry.getValue().getSubList(), 5l);
					  AlertOverviewVO MuncipalityVO = getImpactLevelMatchVO(entry.getValue().getSubList(), 12l);
					  mergeRequiredData(mandalMuncipalityVO,mandalVO,rtrnAlertStatusObjLst,4l,"Mandal/Muncipality");
					  mergeRequiredData(mandalMuncipalityVO,MuncipalityVO,rtrnAlertStatusObjLst,4l,"Mandal/Muncipality");
					  AlertOverviewVO villageVO = getImpactLevelMatchVO(entry.getValue().getSubList(), 7l);
					  AlertOverviewVO wardVO = getImpactLevelMatchVO(entry.getValue().getSubList(),9l);
					  AlertOverviewVO panchayatVo = getImpactLevelMatchVO(entry.getValue().getSubList(),6l);
					  mergeRequiredData(villageWardVO,villageVO,rtrnAlertStatusObjLst,5l,"Village/Ward");
					  mergeRequiredData(villageWardVO,wardVO,rtrnAlertStatusObjLst,5l,"Village/Ward");
					  mergeRequiredData(villageWardVO,panchayatVo,rtrnAlertStatusObjLst,5l,"Village/Ward");
					  entry.getValue().getSubList().remove(mandalVO);//removeMandalData
					  entry.getValue().getSubList().remove(MuncipalityVO);//removemuncipalityData
					  entry.getValue().getSubList().add(mandalMuncipalityVO);//adding mandalMucipality Merge Data
					  entry.getValue().getSubList().remove(villageVO);//removeVillageData
					  entry.getValue().getSubList().remove(wardVO);//removeWardData
					  entry.getValue().getSubList().remove(panchayatVo);//removePanchaytData
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
								 /*  overAllVO.setPendingCnt(locationVO.getPendingCnt());
								   overAllVO.setNotifiedCnt(locationVO.getNotifiedCnt());
								   overAllVO.setActionInProgessCnt(locationVO.getActionInProgessCnt());
								   overAllVO.setCompletedCnt(locationVO.getCompletedCnt());
								   overAllVO.setUnabletoResolveCnt(locationVO.getUnabletoResolveCnt());
								   overAllVO.setActionNotRequiredCnt(locationVO.getActionNotRequiredCnt());
								   overAllVO.setDuplicatesStatusCnt(locationVO.getDuplicatesStatusCnt());*/
								   //Setting StatusList 
								   overAllVO.getStatusList().addAll(getAlrtStatus(rtrnAlertStatusObjLst));
								   if(overAllVO != null && overAllVO.getStatusList().size() > 0){
									   for(AlertOverviewVO statusVO:overAllVO.getStatusList()){
										   AlertOverviewVO matchVO = getMatchVO(locationVO.getStatusList(), statusVO.getStatusTypeId());
										    if(matchVO != null){
										    	statusVO.setAlertCount(statusVO.getAlertCount()+matchVO.getAlertCount());
										    }
									   }
								   }
								   overAllAlrtDtlsMap.put(overAllVO.getLocationTypeId(), overAllVO);
							   }else{
								   if(overAllVO != null && overAllVO.getStatusList().size() > 0){
									   for(AlertOverviewVO statusVO:overAllVO.getStatusList()){
										   AlertOverviewVO matchVO = getMatchVO(locationVO.getStatusList(), statusVO.getStatusTypeId());
										    if(matchVO != null){
										    	statusVO.setAlertCount(statusVO.getAlertCount()+matchVO.getAlertCount());
										    }
									   }
								   }
							   overAllVO.setAlertCount(overAllVO.getAlertCount()+locationVO.getAlertCount());
							 /*  overAllVO.setPendingCnt(overAllVO.getPendingCnt()+locationVO.getPendingCnt());
							   overAllVO.setNotifiedCnt(overAllVO.getNotifiedCnt()+locationVO.getNotifiedCnt());
							   overAllVO.setActionInProgessCnt(overAllVO.getActionInProgessCnt()+locationVO.getActionInProgessCnt());
							   overAllVO.setCompletedCnt(overAllVO.getCompletedCnt()+locationVO.getCompletedCnt());
							   overAllVO.setUnabletoResolveCnt(overAllVO.getUnabletoResolveCnt()+locationVO.getUnabletoResolveCnt());
							   overAllVO.setActionNotRequiredCnt(overAllVO.getActionNotRequiredCnt()+locationVO.getActionNotRequiredCnt());
							   overAllVO.setDuplicatesStatusCnt(overAllVO.getDuplicatesStatusCnt()+locationVO.getDuplicatesStatusCnt());
						*/   }
						  }
					  }
				  }
			  }
			  
			  if(overAllAlrtDtlsMap != null && overAllAlrtDtlsMap.size() > 0){
				  AlertOverviewVO corpGhmcVO = null;
				  if(overAllAlrtDtlsMap.containsKey(8l)){
					  corpGhmcVO = overAllAlrtDtlsMap.get(8l);//corp ghmc Id
					  overAllAlrtDtlsMap.remove(8l);//remove corpGhmc  
				  }
				
				  AlertOverviewVO overAllAlertVO = new AlertOverviewVO();
				  overAllAlertVO.setName("All Categories Alerts");
				  List<AlertOverviewVO> overAllAlertCntList = new ArrayList<AlertOverviewVO>(overAllAlrtDtlsMap.values());
				  if(overAllAlertCntList.size() > 0){
					  overAllAlertCntList.add(2, corpGhmcVO);
				  }
				  overAllAlertVO.getSubList().addAll(overAllAlertCntList);
				  resultList.add(0, overAllAlertVO);
				  overAllAlrtDtlsMap.clear();
			  }
			
	 }catch(Exception e){
		 LOG.error("Error occured getAlertCategoryDtlsLocationWise() method of AlertService{}",e); 
	 }
	 return resultList;
 }
 public void prepareTemplate(List<Object[]> objList,List<Object[]> impactLevelLst,List<Object[]> statusList,Map<Long,AlertOverviewVO> categoryMap) {
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				 AlertOverviewVO categoryVO = new AlertOverviewVO();
				 categoryVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				 categoryVO.setName(commonMethodsUtilService.getStringValueForObject(param[1])+" Alerts");
				 categoryVO.getSubList().addAll(getImpactLevelList(impactLevelLst,statusList));
				 categoryMap.put(categoryVO.getId(), categoryVO);
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Error occured prepareTemplate() method of AlertService{}",e); 
	 }
 }
 public List<AlertOverviewVO> getImpactLevelList(List<Object[]> impactLevlLst,List<Object[]> statusList){
	 List<AlertOverviewVO> locationList = new CopyOnWriteArrayList<AlertOverviewVO>();
	   try{
		   if(impactLevlLst != null  && !impactLevlLst.isEmpty() ){
			   for(Object[] param:impactLevlLst){
				   AlertOverviewVO locationVO = new AlertOverviewVO();
				   locationVO.setLocationTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
				   locationVO.setLocationType(commonMethodsUtilService.getStringValueForObject(param[1]));
				   locationVO.getStatusList().addAll(getAlrtStatus(statusList));
				   locationList.add(locationVO);
			   }
		   }
	   }catch(Exception e){
		   LOG.error("Error occured getImpactLevelList() method of AlertService{}",e); 
	   }
	   return locationList; 
 }
 public List<AlertOverviewVO> getAlrtStatus(List<Object[]> objList){
	 List<AlertOverviewVO> alertStatusList = new ArrayList<AlertOverviewVO>();
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				 AlertOverviewVO statusVO = new AlertOverviewVO();
				 statusVO.setStatusTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
				 statusVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[1]));
				 alertStatusList.add(statusVO);
			 }
		 }	 
	 }catch(Exception e){
		 LOG.error("Error occured getAlertStatus() method of AlertService{}",e); 
	 }
	 return alertStatusList;
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
					 if(impactLevelVO != null){
						 AlertOverviewVO statusVO = getMatchVO(impactLevelVO.getStatusList(),statusId);
						   if(statusVO != null){
							   statusVO.setAlertCount(alertCnt);   
						   }
					 }
					 /*	if(impactLevelVO != null ){
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
							 		}else if(statusId == 7l){
							 			impactLevelVO.setDuplicatesStatusCnt(alertCnt);
							 		}
					 }*/
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
 public void mergeRequiredData(AlertOverviewVO resutlVO,AlertOverviewVO VO,List<Object[]> statuObjList,Long impactLevelId,String impactLevel){
	 try{
		 if(VO != null){
			 if(resutlVO.getLocationTypeId() == null){
				 resutlVO.setLocationTypeId(impactLevelId);
				 resutlVO.setLocationType(impactLevel);
				 resutlVO.getStatusList().addAll(getAlrtStatus(statuObjList));
			 }
			 resutlVO.setAlertCount(resutlVO.getAlertCount()+VO.getAlertCount());
			 if(resutlVO.getStatusList() != null && resutlVO.getStatusList().size()>0){
				 for(AlertOverviewVO statusVO:resutlVO.getStatusList()){
					 AlertOverviewVO matchVO = getMatchVO(VO.getStatusList(), statusVO.getStatusTypeId()); 
					  if(matchVO != null){
						  statusVO.setAlertCount(statusVO.getAlertCount()+matchVO.getAlertCount()); 
					  }
				 }
			 }
			 
			/* resutlVO.setPendingCnt(resutlVO.getPendingCnt()+VO.getPendingCnt());
			 resutlVO.setNotifiedCnt(resutlVO.getNotifiedCnt()+VO.getNotifiedCnt());
			 resutlVO.setActionInProgessCnt(resutlVO.getActionInProgessCnt()+VO.getActionInProgessCnt());
			 resutlVO.setCompletedCnt(resutlVO.getCompletedCnt()+VO.getCompletedCnt());
			 resutlVO.setUnabletoResolveCnt(resutlVO.getUnabletoResolveCnt()+VO.getUnabletoResolveCnt());
			 resutlVO.setActionNotRequiredCnt(resutlVO.getActionNotRequiredCnt()+VO.getActionNotRequiredCnt());
			 resutlVO.setDuplicatesStatusCnt(resutlVO.getDuplicatesStatusCnt()+VO.getDuplicatesStatusCnt());*/
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
 				 vo.setName(commonMethodsUtilService.getStringValueForObject(params[1]).toString());
 				 returnList.add(vo);
 			 }
 			 
 		 }
 	}
 	catch (Exception e) {
 		LOG.error("Exception in getAlertImpactScope()",e);  	
 	}
 	return returnList;
 }
 //ssss  
 public List<AlertVO> getTotalAlertGroupByPubRepThenStatus(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, Long publicRepresentativeTypeId,List<Long> commitLvlIdList, String groupAssignType, String position, Long designationId,Long alertTypeId,Long editionTypeId,Long districtId){
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
			//List<Object[]> statusList = alertStatusDAO.getAllStatus();
			List<Object[]> statusList = alertDepartmentStatusDAO.getAlertStatusByAlertTypeId(alertTypeId);
			
			
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
				List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(new ArrayList<Long>(userAccessLevelValues));
				userAccessLevelId = 5L;
				userAccessLevelValues.clear();
				userAccessLevelValues.addAll(parliamentAssemlyIds);      
			}
			
			List<Long> alertTypeList = new ArrayList<Long>();
			List<Long> editionList = new ArrayList<Long>();
			if(alertTypeId != null){
				if(alertTypeId.longValue() != 0L){
				   alertTypeList.add(alertTypeId);
				}
			}
			
			if(editionTypeId != null){
				if(editionTypeId.longValue() != 0L ){
					if(editionTypeId.longValue() == 1L){
						editionList.add(editionTypeId);
					}else if(editionTypeId.longValue() == 2L){
						editionList.add(editionTypeId);
						editionList.add(3L);
					}
				}
			}
			
			//get alert status count and and create a map of LocationId and its corresponding  alert count
			//Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, Long userAccessLevelId, List<Long> userAccessLevelValues
			List<Object[]> alertCountList = null;//alertDAO.getTotalAlertGroupByPubRepThenStatus(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, "one");
			if(groupAssignType.equalsIgnoreCase("Party Committee")){
				if(position.equalsIgnoreCase("bellow")){
					alertCountList = alertDAO.getTdpCommitteeRolesByAlertCnt(userAccessLevelId,userAccessLevelValues,stateId,scopeIdList,fromDate,toDate,commitLvlIdList,designationId,"one",alertTypeList,editionList,districtId);
				}else{  
					alertCountList = alertDAO.getTdpBasicCommiteeTypeAndAlertStatusByAlertCnt(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate,commitLvlIdList, "one",alertTypeList,editionList,districtId);
				}
			}else{  
				if(publicRepresentativeTypeId.longValue() == 0L){
					alertCountList = alertDAO.getTotalAlertGroupByPubRepThenStatus(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, "one",alertTypeList,editionList,districtId);
				}else{
					alertCountList = alertDAO.getTotalAlertGroupByCandThenStatus(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, "one",publicRepresentativeTypeId,alertTypeList,editionList,districtId);
				}
			}
			
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
			List<Object[]> alertCountGrpByLocList = null;//alertDAO.getTotalAlertGroupByPubRepThenStatus(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, "two");    
			if(groupAssignType.equalsIgnoreCase("Party Committee")){
				if(position.equalsIgnoreCase("bellow")){
					alertCountGrpByLocList = alertDAO.getTdpCommitteeRolesByAlertCnt(userAccessLevelId,userAccessLevelValues,stateId,scopeIdList,fromDate,toDate,commitLvlIdList,designationId,"two",alertTypeList,editionList,districtId);
				}else{
					alertCountGrpByLocList = alertDAO.getTdpBasicCommiteeTypeAndAlertStatusByAlertCnt(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate,commitLvlIdList, "two",alertTypeList,editionList,districtId);

				}
			}else{
				if(publicRepresentativeTypeId.longValue() == 0L){
					alertCountGrpByLocList = alertDAO.getTotalAlertGroupByPubRepThenStatus(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, "two",alertTypeList,editionList,districtId);
				}else{  
					alertCountGrpByLocList = alertDAO.getTotalAlertGroupByCandThenStatus(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, "two",publicRepresentativeTypeId,alertTypeList,editionList,districtId);
				}
			}
			
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
								Long statusId = commonMethodsUtilService.getLongValueForObject(param[0]);
								if(statusId != 1l && statusId !=6 && statusId !=7){//pending - 1,action not required -6 and dublicate 7
									alertVO.setCategoryId(commonMethodsUtilService.getLongValueForObject(param[0]));
									alertVO.setCategory(commonMethodsUtilService.getStringValueForObject(param[1]));
									alertVOs.add(alertVO);  
								}
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
 	public List<AlertVO> getMemForPartyCommitDesg(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId,List<Long> commitLvlIdArr,Long commitTypeId,Long designationId,Long alertTypeId,Long editionTypeId,Long districtId){
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
			//List<Object[]> statusList = alertStatusDAO.getAllStatus();
			List<Object[]> statusList = alertDepartmentStatusDAO.getAlertStatusByAlertTypeId(alertTypeId);
			
			List<Long> alertTypeList = new ArrayList<Long>();
			List<Long> editionList = new ArrayList<Long>();
			if(alertTypeId != null){
				if(alertTypeId.longValue() != 0L){
				   alertTypeList.add(alertTypeId);
				}
			}
			
			if(editionTypeId != null){
				if(editionTypeId.longValue() != 0L ){
					if(editionTypeId.longValue() == 1L){
						editionList.add(editionTypeId);
					}else if(editionTypeId.longValue() == 2L){
						editionList.add(editionTypeId);
						editionList.add(3L);
					}
				}
			}
			
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
				List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(new ArrayList<Long>(userAccessLevelValues));
				userAccessLevelId = 5L;
				userAccessLevelValues.clear();
				userAccessLevelValues.addAll(parliamentAssemlyIds);      
			}
			
			List<Object[]> alertCountList = alertDAO.getMemForPartyCommitDesg(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, commitLvlIdArr, commitTypeId, designationId, "one",alertTypeList,editionList,districtId);
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
			List<Object[]> alertCountGrpByLocList = null;
			alertCountGrpByLocList = alertDAO.getMemForPartyCommitDesg(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, commitLvlIdArr, commitTypeId, designationId, "two",alertTypeList,editionList,districtId);
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
								Long statusId = commonMethodsUtilService.getLongValueForObject(param[0]);
								if(statusId != 1l && statusId !=6 && statusId !=7){//pending - 1,action not required -6 and dublicate 7
									alertVO.setCategoryId(commonMethodsUtilService.getLongValueForObject(param[0]));
									alertVO.setCategory(commonMethodsUtilService.getStringValueForObject(param[1]));
									alertVOs.add(alertVO);  
								}
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
 			LOG.error("Error occured getMemForPartyCommitDesg() method of AlertService{}");
 		}  
 		return null;
 	}
 
 	public String updateCandidateStatusOfAlert(Long alertId,Long userId){
	 try{
		 
		int count =  alertDAO.updateCandidateStatusOfAlert(alertId,userId);
		
		if(count>0){			
			 AlertTrackingVO alertTrackingVO = new AlertTrackingVO();
			 alertTrackingVO.setUserId(userId);
			 alertTrackingVO.setAlertStatusId(1l);
			 alertTrackingVO.setAlertId(alertId);
			 alertTrackingVO.setAlertTrackingActionId(1l);
			 
			 //tracking saving method
			 saveAlertTrackingDetails(alertTrackingVO)	;
			 
			 return "success";
		}
		
	 }catch(Exception e){		 
		 LOG.error("Exception in updateCandidateStatusOfAlert()",e);	
		 return "failure";
	 }
	 return "failure";
 }
/*
 * santosh (non-Javadoc)
 * @see com.itgrids.partyanalyst.service.IAlertService#getAssignGroupTypeAlertDtlsByImpactLevelWise(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.util.List)
 */
 public List<AlertOverviewVO> getAssignGroupTypeAlertDtlsByImpactLevelWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertTypeId,Long editionTypeId,Long districtId){
	 List<AlertOverviewVO> resultList = new ArrayList<AlertOverviewVO>();
	 Set<Long> locationValues = new HashSet<Long>(0);
     Long locationAccessLevelId =0l;
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
			  //convert parliament into constituency.
				if(locationAccessLevelId.longValue() == 4L){
					List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(new ArrayList<Long>(locationValues));
					locationAccessLevelId = 5L;
					locationValues.clear();
					locationValues.addAll(parliamentAssemlyIds);      
				}
				List<Long> alertTypeList = new ArrayList<Long>();
				List<Long> editionList = new ArrayList<Long>();
				if(alertTypeId != null){
					if(alertTypeId.longValue() != 0L){
					   alertTypeList.add(alertTypeId);
					}
				}
				
				if(editionTypeId != null){
					if(editionTypeId.longValue() != 0L ){
						if(editionTypeId.longValue() == 1L){
							editionList.add(editionTypeId);
						}else if(editionTypeId.longValue() == 2L){
							editionList.add(editionTypeId);
							editionList.add(3L);
						}
					}
				}
			  Set<Long> allTypeTdpCadreIds = new HashSet<Long>(0);
			  Map<Long,Set<Long>> statusWiseAlertCntMap = new HashMap<Long, Set<Long>>();
			  Set<Long> totalAletCntSt = new HashSet<Long>(0);
			  //Calculating public representative type alert count  
			  List<Object[]> rtrnPblcRprsnttvTypAlrtDtlsObjLst = alertDAO.getPublicRepresentativeTypeAlertDtls(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList,districtId);
			  mergeStatusWiseAlertCnt(rtrnPblcRprsnttvTypAlrtDtlsObjLst,statusWiseAlertCntMap,allTypeTdpCadreIds,totalAletCntSt,null);
			  setDatatoFinalList(prepareTempalate(alertTypeId),statusWiseAlertCntMap,totalAletCntSt,resultList,"Public Representative");
			  //Calculating party committee type alert count
			  List<Object[]> rtrnPrtyCmmttAlrtDtlsObjLst = alertDAO.getPartyCommitteeTypeAlertDtls(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList,districtId);
			  mergeStatusWiseAlertCnt(rtrnPrtyCmmttAlrtDtlsObjLst,statusWiseAlertCntMap,allTypeTdpCadreIds,totalAletCntSt,null);
			  setDatatoFinalList(prepareTempalate(alertTypeId),statusWiseAlertCntMap,totalAletCntSt,resultList,"Party Committee");
			  //Calculating program type alert count
			  List<Object[]> rtrnPrgrmCmmttAlrtDtlsOblLst = alertDAO.getProgramCommitteeTypeAlertDtls(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList,districtId);
			  mergeStatusWiseAlertCnt(rtrnPrgrmCmmttAlrtDtlsOblLst,statusWiseAlertCntMap,allTypeTdpCadreIds,totalAletCntSt,null);
			  setDatatoFinalList(prepareTempalate(alertTypeId),statusWiseAlertCntMap,totalAletCntSt,resultList,"Program Committee");
			  //Calculating other type alert count
			  List<Object[]> rtrnAllAlertCntDls = alertDAO.getAllAlertDtls(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList,districtId);
			  mergeStatusWiseAlertCnt(rtrnAllAlertCntDls,statusWiseAlertCntMap,null,totalAletCntSt,allTypeTdpCadreIds);
			  setDatatoFinalList(prepareTempalate(alertTypeId),statusWiseAlertCntMap,totalAletCntSt,resultList,"Others");
			  
	 }catch(Exception e){
		 LOG.error("Exception in getAssignGroupTypeAlertDtlsByImpactLevelWise()",e);	 
	 }
	 return resultList;
 }
 public void mergeStatusWiseAlertCnt(List<Object[]> objLst,Map<Long,Set<Long>> statusWiseAlertCntMap, Set<Long> allTypeTdpCadreIds,Set<Long> totalAletCntSt,Set<Long> allTdpCadreIds){
	 try{
		 if(objLst != null && objLst.size() > 0){
			 for(Object[] param:objLst){
				     Long tdpCadreId = commonMethodsUtilService.getLongValueForObject(param[2]);
				     if(allTdpCadreIds != null){ // merge other type alert 
				    	   if(!allTdpCadreIds.contains(tdpCadreId)){
				    		   Set<Long> alertCntSet = statusWiseAlertCntMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));//statusId
							    if(alertCntSet == null){
							    	alertCntSet = new HashSet<Long>();
							    	statusWiseAlertCntMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), alertCntSet);
							    }
							    alertCntSet.add(commonMethodsUtilService.getLongValueForObject(param[1]));//alert id
							    totalAletCntSt.add(commonMethodsUtilService.getLongValueForObject(param[1]));   
				    	   }
				     }else{
			    	    Set<Long> alertCntSet = statusWiseAlertCntMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));//statusId
					    if(alertCntSet == null){
					    	alertCntSet = new HashSet<Long>();
					    	statusWiseAlertCntMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), alertCntSet);
					    }
					    alertCntSet.add(commonMethodsUtilService.getLongValueForObject(param[1]));//alert id
					    allTypeTdpCadreIds.add(tdpCadreId);
					    totalAletCntSt.add(commonMethodsUtilService.getLongValueForObject(param[1])); 
				     }
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception in mergeStatusWiseAlertCnt()",e); 
	 }
 }
 public void setDatatoFinalList(Map<Long,AlertOverviewVO> alertStatusMap,Map<Long,Set<Long>> statusWiseAlertCntMap,Set<Long> totalAletCntSt,List<AlertOverviewVO> resultList,String type){
	 try{
		 if(alertStatusMap != null && alertStatusMap.size() > 0){
			 for(Entry<Long,AlertOverviewVO> entry:alertStatusMap.entrySet()){
				 if(statusWiseAlertCntMap.get(entry.getKey()) != null && statusWiseAlertCntMap.get(entry.getKey()).size() > 0){
					 entry.getValue().setAlertCount(new Long(statusWiseAlertCntMap.get(entry.getKey()).size()));
					 entry.getValue().setAlertCountPer(calculatePercantage(entry.getValue().getAlertCount(), new Long(totalAletCntSt.size())));
				 }
			 }
		 }
		   AlertOverviewVO statusTypeVO = new AlertOverviewVO();
		   statusTypeVO.setName(type);
		   statusTypeVO.setTotalAlertCnt(new Long(totalAletCntSt.size()));
		   statusTypeVO.setSubList1(new ArrayList<AlertOverviewVO>(alertStatusMap.values()));
		   if(statusTypeVO.getSubList1() != null && statusTypeVO.getSubList1().size() > 0){
			   statusTypeVO.getSubList1().get(0).setTotalAlertCnt(statusTypeVO.getTotalAlertCnt());   
		   }
		   resultList.add(statusTypeVO);
		   //clear total alert set
		   totalAletCntSt.clear();
		   //clear status wise map
		   statusWiseAlertCntMap.clear();
	 }catch(Exception e){
		 LOG.error("Exception in setDatatoFinalList()",e); 
	 }
 }
 public Map<Long,AlertOverviewVO> prepareTempalate(Long alertTypeId){
	 Map<Long,AlertOverviewVO> alertStatusMap = new HashMap<Long, AlertOverviewVO>();
	 try{
		// List<Object[]> rtrnStatusLst = alertStatusDAO.getAllStatus();
		 List<Object[]> rtrnStatusLst = alertDepartmentStatusDAO.getAlertStatusByAlertTypeId(alertTypeId);;
		 if(rtrnStatusLst != null && rtrnStatusLst.size() > 0){
			 for(Object[] param:rtrnStatusLst){
				 Long statusId = commonMethodsUtilService.getLongValueForObject(param[0]);
				 if(statusId != 1l && statusId != 6l && statusId != 7l){//except pending - 1 ,Action Not Required - 6  and Duplicate status - 7
					 AlertOverviewVO statusVO = new AlertOverviewVO();
					 statusVO.setStatusTypeId(statusId);
					 statusVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[1]));
					 alertStatusMap.put(statusVO.getStatusTypeId(),statusVO);
				 }
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception in prepareTempalate()",e);  
	 }
	 return alertStatusMap;
 }
 /*
  * santosh (non-Javadoc)
  * @see com.itgrids.partyanalyst.service.IAlertService#getOtherTypeAlertCandiateDtls(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.util.List)
  */
 public List<AlertOverviewVO> getOtherAndPrgrmCmmtteeTypeAlertCndtDtls(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,String resultType,Long alertTypeId,Long editionTypeId,Long districtId){
	 List<AlertOverviewVO> resultList = new ArrayList<AlertOverviewVO>();
	 Set<Long> locationValues = new HashSet<Long>(0);
     Long locationAccessLevelId =0l;
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
			  //convert parliament into constituency.
				if(locationAccessLevelId.longValue() == 4L){
					List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(new ArrayList<Long>(locationValues));
					locationAccessLevelId = 5L;
					locationValues.clear();
					locationValues.addAll(parliamentAssemlyIds);      
				}
				
				List<Long> alertTypeList = new ArrayList<Long>();
				List<Long> editionList = new ArrayList<Long>();
				if(alertTypeId != null){
					if(alertTypeId.longValue() != 0L){
					   alertTypeList.add(alertTypeId);
					}
				}
				
				if(editionTypeId != null){
					if(editionTypeId.longValue() != 0L ){
						if(editionTypeId.longValue() == 1L){
							editionList.add(editionTypeId);
						}else if(editionTypeId.longValue() == 2L){
							editionList.add(editionTypeId);
							editionList.add(3L);
						}
					}
				}
				
			   Map<Long,AlertOverviewVO> candiateDtsMap = new HashMap<Long, AlertOverviewVO>();
			   //List<Object[]> rtrnStatusLst = alertStatusDAO.getAllStatus();
			   List<Object[]> rtrnStatusLst = alertDepartmentStatusDAO.getAlertStatusByAlertTypeId(alertTypeId);
			   List<Object[]> rtrnPrgrmCmmttAlrtDtlsOblLst = alertDAO.getProgramCommitteeTypeAlertDtls(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList,districtId);
			   Set<Long> allTypeTdpCadreIds = new HashSet<Long>(0);
			   if(resultType != null && resultType.trim().equalsIgnoreCase("Program Committee")){
				   
				   setCandiateAlertCnt(rtrnPrgrmCmmttAlrtDtlsOblLst,candiateDtsMap,allTypeTdpCadreIds,rtrnStatusLst); // in this case allTypeTdpCadreIds set is empty
			  
			   }else if(resultType != null && resultType.trim().equalsIgnoreCase("Others")){
				   
				  List<Object[]> rtrnPblcRprsnttvTypAlrtDtlsObjLst = alertDAO.getPublicRepresentativeTypeAlertDtls(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList,districtId);
				  setTdpCadreId(rtrnPblcRprsnttvTypAlrtDtlsObjLst,allTypeTdpCadreIds);
				  List<Object[]> rtrnPrtyCmmttAlrtDtlsObjLst = alertDAO.getPartyCommitteeTypeAlertDtls(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList,districtId);
				  setTdpCadreId(rtrnPrtyCmmttAlrtDtlsObjLst,allTypeTdpCadreIds);
				  setTdpCadreId(rtrnPrgrmCmmttAlrtDtlsOblLst,allTypeTdpCadreIds);
				  List<Object[]> rtrnAllAlertCntDls = alertDAO.getAllAlertDtls(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList,districtId);
				  setCandiateAlertCnt(rtrnAllAlertCntDls,candiateDtsMap,allTypeTdpCadreIds,rtrnStatusLst);
				  
			   }
			  if(candiateDtsMap != null && candiateDtsMap.size() > 0){
				  resultList.addAll(candiateDtsMap.values());
				  candiateDtsMap.clear();
			  }
			 }catch(Exception e){
				 LOG.error("Exception in getOtherTypeAlertCandiateDtls()",e);  
			 }
	         return resultList;
 }
 public void setTdpCadreId(List<Object[]> objList,Set<Long> allTypeTdpCadreIds){
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				 allTypeTdpCadreIds.add(commonMethodsUtilService.getLongValueForObject(param[2])); 
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception in setTdpCadreId()",e); 
	 }
 }
 public void setCandiateAlertCnt(List<Object[]> obList,Map<Long,AlertOverviewVO> candiateDtsMap,Set<Long> allTypeTdpCadreIds,List<Object[]> statusList){
	 try{
		 if(obList != null && obList.size() > 0){
			   for(Object[] param:obList){
				   Long tdpCadreId = commonMethodsUtilService.getLongValueForObject(param[2]);
				   Long statusId = commonMethodsUtilService.getLongValueForObject(param[0]);
				   if(!allTypeTdpCadreIds.contains(tdpCadreId)){
					   AlertOverviewVO candiateVO = candiateDtsMap.get(tdpCadreId);
					    if(candiateVO == null){
					    	candiateVO = new AlertOverviewVO();
					    	candiateVO.setId(tdpCadreId);
					    	candiateVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
					    	candiateVO.setSubList1(getStatusList(statusList));
					    	candiateDtsMap.put(candiateVO.getId(), candiateVO);
					    }
					    candiateVO.setTotalAlertCnt(candiateVO.getTotalAlertCnt()+1);
					    candiateVO.getAlertIdSet().add(commonMethodsUtilService.getLongValueForObject(param[1]));
					    AlertOverviewVO statusVO = getMatchVO(candiateVO.getSubList1(),statusId);
					    if(statusVO != null){
					    	statusVO.setStatusCnt(statusVO.getStatusCnt()+1);	
					    }
				   }
			   }
		   }
	 }catch(Exception e){
		 LOG.error("Exception in setOtherCandiateAlertCnt()",e); 
	 }
 }
 public List<AlertOverviewVO> getStatusList(List<Object[]> rtrnStatusLst){
	 List<AlertOverviewVO> alertStatusMap = new ArrayList<AlertOverviewVO>();
	 try{
		 if(rtrnStatusLst != null && rtrnStatusLst.size() > 0){
			 for(Object[] param:rtrnStatusLst){
				 Long statusId = commonMethodsUtilService.getLongValueForObject(param[0]);
				 if(statusId != 1l && statusId != 6l && statusId != 7l){//except pending - 1 ,Action Not Required - 6  and Duplicate status - 7
					 AlertOverviewVO statusVO = new AlertOverviewVO();
					 statusVO.setStatusTypeId(statusId);
					 statusVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[1]));
					 alertStatusMap.add(statusVO);
				 }
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception in getStatusList()",e);  
	 }
	 return alertStatusMap;
 }
 public List<AlertCoreDashBoardVO> getAlertDtlsForPubRep(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, Long publicRepresentativeTypeId, Long cadreId, Long statusId,Long alertTypeId,Long editionTypeId,Long districtId){
 		LOG.info("Entered in getTotalAlertGroupByLocationThenStatus() method of AlertService{}");
		try{  
			Date fromDate = null;          
			Date toDate = null; 
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
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
				List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(new ArrayList<Long>(userAccessLevelValues));
				userAccessLevelId = 5L;
				userAccessLevelValues.clear();
				userAccessLevelValues.addAll(parliamentAssemlyIds);      
			}
			
			List<Long> alertTypeList = new ArrayList<Long>();
			List<Long> editionList = new ArrayList<Long>();
			if(alertTypeId != null){
				if(alertTypeId.longValue() != 0L){
				   alertTypeList.add(alertTypeId);
				}
			}
			
			if(editionTypeId != null){
				if(editionTypeId.longValue() != 0L ){
					if(editionTypeId.longValue() == 1L){
						editionList.add(editionTypeId);
					}else if(editionTypeId.longValue() == 2L){
						editionList.add(editionTypeId);
						editionList.add(3L);
					}
				}
			}
			
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			List<Object[]> alertList = alertDAO.getAlertDtlsForPubRep(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, publicRepresentativeTypeId, cadreId, statusId,alertTypeList,editionList,districtId);
			setAlertDtls(alertCoreDashBoardVOs, alertList);
			return alertCoreDashBoardVOs;
			}catch(Exception e){  
				e.printStackTrace();
				LOG.error("Error occured getTotalAlertGroupByLocationThenStatus() method of AlertService{}");
			}
		return null;
 	}
 public void setAlertDtls(List<AlertCoreDashBoardVO> alertCoreDashBoardVOs, List<Object[]> alertList){//abcd
		try{
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date today = dateUtilService.getCurrentDateAndTime();
			String td = myFormat.format(today);
			Long dist = 0l;
			Long statusId = 0L;
			AlertCoreDashBoardVO alertCoreDashBoardVO = null;  
			String alertSource = "";
			if(alertList != null && alertList.size() > 0){  
				for(Object[] param : alertList ){
					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					alertCoreDashBoardVO.setCreatedDate(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10));
					alertCoreDashBoardVO.setUpdatedDate(commonMethodsUtilService.getStringValueForObject(param[2]).substring(0, 10));
					alertCoreDashBoardVO.setStatusId(commonMethodsUtilService.getLongValueForObject(param[3]));
					alertCoreDashBoardVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[4]));
					statusId = commonMethodsUtilService.getLongValueForObject(param[3]);
					if(param[1] != null && param[2] != null){
						if(statusId == 4L || statusId == 5L || statusId == 6L || statusId == 7L){
							dist = dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10),commonMethodsUtilService.getStringValueForObject(param[2]).substring(0, 10));
						}else{
							dist = dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10),td);
						}  
						alertCoreDashBoardVO.setInterval(dist);
					}
					alertCoreDashBoardVO.setAlertLevel(commonMethodsUtilService.getStringValueForObject(param[8]));
					alertCoreDashBoardVO.setTitle(commonMethodsUtilService.getStringValueForObject(param[9]));    
					if(param[10] == null){
						alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[11]));
					}else{
						alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[10]));    
					}  
					//hiii
						if(commonMethodsUtilService.getLongValueForObject(param[5]).longValue() == 1L){//manual
							alertSource = commonMethodsUtilService.getStringValueForObject(param[13]);
						}else if(commonMethodsUtilService.getLongValueForObject(param[5]).longValue() == 2L){//print
							if(param[17] != null){
								alertSource = commonMethodsUtilService.getStringValueForObject(param[17]);
							}else{
								alertSource = commonMethodsUtilService.getStringValueForObject(param[13]);
							}
							 
						}else if(commonMethodsUtilService.getLongValueForObject(param[5]).longValue() == 3L){//electronic 
							if(param[19] != null){
								alertSource = commonMethodsUtilService.getStringValueForObject(param[19]);
							}else{
								alertSource = commonMethodsUtilService.getStringValueForObject(param[13]);
							}
						}
						alertCoreDashBoardVO.setSource(alertSource);
					 
					alertCoreDashBoardVOs.add(alertCoreDashBoardVO);
					
				}  
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
 /*
  * Santosh (non-Javadoc)
  * @see com.itgrids.partyanalyst.service.IAlertService#getStateImpactLevelAlertDtlsCnt(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.util.List)
  */
 public AlertOverviewVO getStateImpactLevelAlertDtlsCnt(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertTypeId, Long editionId){
	 AlertOverviewVO resultVO = new AlertOverviewVO();
	 Set<Long> locationValues = new HashSet<Long>(0);
     Long locationAccessLevelId =0l;
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date fromDate=null;
     Date toDate = null;
 	List<Long> alertTypeList = new ArrayList<Long>();
	List<Long> editionList = new ArrayList<Long>();
	if(alertTypeId != null){
		if(alertTypeId.longValue() == 0L){
			
		}else{
			alertTypeList.add(alertTypeId);
		}
	}
	
	if(editionId != null){
		if(editionId.longValue() == 0L){
			
		}else if(editionId.longValue() == 1L){
			editionList.add(editionId);
		}else if(editionId.longValue() == 2L){
			editionList.add(editionId);
			editionList.add(3L);
		}
	}
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
			 }//swadhin
			//convert parliament into constituency.
			if(locationAccessLevelId.longValue() == 4L){
				List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(new ArrayList<Long>(locationValues));
				locationAccessLevelId = 5L;
				locationValues.clear();
				locationValues.addAll(parliamentAssemlyIds);      
			}
			 List<Object[]> rtrnCategoryObjLst = alertCategoryDAO.getAllCategoryOrderBy();
			 prepareRquiredTemplate(rtrnCategoryObjLst,resultVO.getCategoryList());
			// List<Object[]> rtrnStatusObjLst = alertStatusDAO.getAllStatus();
			 List<Object[]> rtrnStatusObjLst = alertDepartmentStatusDAO.getAlertStatusByAlertTypeId(alertTypeId);
			 prepareRquiredTemplate(rtrnStatusObjLst,resultVO.getStatusList());
			 
			 List<Object[]> rtrnObjLst = alertDAO.getStateImpactLevelAlertCnt(locationAccessLevelId, locationValues, stateId, impactLevelIds, fromDate, toDate, "State",alertTypeList,editionList);
			 if(rtrnObjLst != null && rtrnObjLst.size() > 0){
				 for(Object[] param:rtrnObjLst){
					 AlertOverviewVO stateVO = new AlertOverviewVO();
					 stateVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					 stateVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					 stateVO.setAlertCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					 resultVO.getSubList().add(stateVO);
				 }
			 }
			  List<Object[]> rtrnCtgryObjLst = alertDAO.getStateImpactLevelAlertCnt(locationAccessLevelId, locationValues, stateId, impactLevelIds, fromDate, toDate, "Category",alertTypeList,editionList);
			 	if(rtrnCtgryObjLst != null && rtrnCtgryObjLst.size() > 0){
			 		for(Object[] param:rtrnCtgryObjLst){
			 			Long categoryId = commonMethodsUtilService.getLongValueForObject(param[0]);
			 			AlertOverviewVO categoryVO = getRequiredMatchVO(resultVO.getCategoryList(),categoryId);
			 			if(categoryVO != null){
			 				categoryVO.setAlertCount(commonMethodsUtilService.getLongValueForObject(param[2]));
			 			}
			 		}
			 	}
			  List<Object[]> rtrnStatusWiseCntObjLst = alertDAO.getStateImpactLevelAlertCnt(locationAccessLevelId, locationValues, stateId, impactLevelIds, fromDate, toDate, "Status",alertTypeList,editionList);	
			  if(rtrnStatusWiseCntObjLst != null && rtrnStatusWiseCntObjLst.size() > 0){
				  if(rtrnStatusWiseCntObjLst != null && rtrnStatusWiseCntObjLst.size() > 0){
				 		for(Object[] param:rtrnStatusWiseCntObjLst){
				 			Long statusId = commonMethodsUtilService.getLongValueForObject(param[0]);
				 			AlertOverviewVO statusVO = getRequiredMatchVO(resultVO.getStatusList(),statusId);
				 			if(statusVO != null){
				 				statusVO.setAlertCount(commonMethodsUtilService.getLongValueForObject(param[2]));
				 			}
				 		}
				 	} 
			  }
	 }catch(Exception e){
		 LOG.error("Exception occured  in getStateImpactLevelAlertDtlsCnt() in AlertService class ",e);  
	 }
	 return resultVO;
 }
 public void prepareRquiredTemplate(List<Object[]> objList,List<AlertOverviewVO> list){
	 try{
		 if(objList != null && objList.size() > 0){
			 for(Object[] param:objList){
				 AlertOverviewVO VO = new AlertOverviewVO();
				 VO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				 VO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
				 list.add(VO);
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception occured  in getStateImpactLevelAlertDtlsCnt() in AlertService class ",e);
	 }
 }
 public AlertOverviewVO getRequiredMatchVO(List<AlertOverviewVO> list,Long id){
	 try{
		 if(list == null || list.size() == 0)
			 return null;
		 for(AlertOverviewVO VO:list){
			 if(VO.getId().equals(id)){
				 return VO;
			 }
		 }
	 }catch(Exception e){
		 LOG.error("Exception occured  in getRequiredMatchVO() in AlertService class ",e); 
	 }
	 return null;
	 }
 	public List<AlertCoreDashBoardVO> getAlertDtlsAssignedByPartyCommite(String fromDateStr,String toDateStr,Long stateId,List<Long> scopeIdList,Long activityMemberId,List<Long> commitLvlIdList,Long commitTypeId,Long designationId,Long cadreId, Long statusId,Long alertTypeId,Long editionTypeId,Long districtId){
 		try{
 			Date fromDate = null;          
			Date toDate = null; 
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
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
				List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(new ArrayList<Long>(userAccessLevelValues));
				userAccessLevelId = 5L;
				userAccessLevelValues.clear();
				userAccessLevelValues.addAll(parliamentAssemlyIds);      
			}
			
			List<Long> alertTypeList = new ArrayList<Long>();
			List<Long> editionList = new ArrayList<Long>();
			if(alertTypeId != null){
				if(alertTypeId.longValue() != 0L){
				   alertTypeList.add(alertTypeId);
				}
			}
			
			if(editionTypeId != null){
				if(editionTypeId.longValue() != 0L ){
					if(editionTypeId.longValue() == 1L){
						editionList.add(editionTypeId);
					}else if(editionTypeId.longValue() == 2L){
						editionList.add(editionTypeId);
						editionList.add(3L);
					}
				}
			}
			
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			List<Object[]> alertList = alertDAO.getAlertDtlsAssignedByPartyCommite(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, commitLvlIdList, cadreId, commitTypeId,designationId,statusId,alertTypeList,editionList,districtId);
			setAlertDtls(alertCoreDashBoardVOs, alertList);    
			return alertCoreDashBoardVOs;
 		}catch(Exception e){
 			e.printStackTrace();
 			LOG.error("Exception occured  in getAlertDtlsAssignedByPartyCommite() in AlertService class ",e);
 		}
 		return null;
 	}
 /*
  * santosh (non-Javadoc)
  * @see com.itgrids.partyanalyst.service.IAlertService#getAlertDetailsTdpCadreWise(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String)
  */
 public List<AlertCoreDashBoardVO> getAlertDetailsTdpCadreWise(String fromDateStr, String toDateStr, Long stateId,List<Long> impactLevelIds, Long activityMemberId,Long tdpCadreId, Long statusId,String resultType,Long alertTypeId,Long editionTypeId,Long districtId){
		LOG.info("Entered in getAlertDetailsTdpCadreWise() method of AlertService{}");
		try{  
			Date fromDate = null;          
			Date toDate = null; 
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
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
				List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(new ArrayList<Long>(userAccessLevelValues));
				userAccessLevelId = 5L;
				userAccessLevelValues.clear();
				userAccessLevelValues.addAll(parliamentAssemlyIds);      
			}
			 
			List<Long> alertTypeList = new ArrayList<Long>();
			List<Long> editionList = new ArrayList<Long>();
			if(alertTypeId != null){
				if(alertTypeId.longValue() != 0L){
				   alertTypeList.add(alertTypeId);
				}
			}
			
			if(editionTypeId != null){
				if(editionTypeId.longValue() != 0L ){
					if(editionTypeId.longValue() == 1L){
						editionList.add(editionTypeId);
					}else if(editionTypeId.longValue() == 2L){
						editionList.add(editionTypeId);
						editionList.add(3L);
					}
				}
			}
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			List<Object[]> alertList = alertDAO.getAlertDetailsByCadreWise(userAccessLevelId,userAccessLevelValues,fromDate,toDate,stateId,impactLevelIds,tdpCadreId,statusId,resultType,alertTypeList,editionList,districtId);
			setAlertDtls(alertCoreDashBoardVOs, alertList);
			return alertCoreDashBoardVOs;
			}catch(Exception e){  
				e.printStackTrace();
				LOG.error("Error occured getAlertDetailsTdpCadreWise() method of AlertService{}");
			}
		return null;
	}
//swadhin
  public List<AlertCoreDashBoardVO> getDistrictAndStateImpactLevelWiseAlertDtls(String fromDateStr, String toDateStr, Long stateId,List<Long> impactLevelIds, Long activityMemberId,
		  List<Long> districtIdList,Long catId,Long alertTypeId, Long editionId,Long constituencyId,Long alertStatusId,String locationLevel,String isPublication,String publicationIdStr){
		LOG.info("Entered in getDistrictAndStateImpactLevelWiseAlertDtls() method of AlertService{}");
		try{  
			Date fromDate = null;          
			Date toDate = null; 
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Long> alertTypeList = new ArrayList<Long>();
			List<Long> editionTypeList = new ArrayList<Long>();
			if(alertTypeId != null){
				if(alertTypeId.longValue() == 0L){
					
				}else{
					alertTypeList.add(alertTypeId);
				}
			}
			if(editionId != null){
				if(editionId.longValue() == 0L){
					
				}else if(editionId.longValue() == 1L){
					editionTypeList.add(editionId);
				}else if(editionId.longValue() == 2L){
					editionTypeList.add(editionId);
					editionTypeList.add(3L);
				}
			}
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
			if(locationLevel != null && locationLevel.equalsIgnoreCase("District/GMC CORP Impact Level")){
				impactLevelIds.clear();
				impactLevelIds.add(2l);
				impactLevelIds.add(8l);
			}
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			String publicationType="";
			Long publicationId = 0l;
			if(isPublication != null && isPublication.equalsIgnoreCase("true") || locationLevel != null && locationLevel.equalsIgnoreCase("District/GMC CORP Impact Level")){
				if(publicationIdStr != null && publicationIdStr.length() > 0){
					Long firstDigit = Long.valueOf(publicationIdStr.substring(0,1));
					if(firstDigit == 1l){
						publicationId = Long.valueOf(publicationIdStr.substring(1));
						publicationType="TvChannel";
					}else if(firstDigit == 2l){
						publicationId = Long.valueOf(publicationIdStr.substring(1));
						publicationType="NewsPaper";
					}else{
						List<Object[]> tvChannelAlertList = alertDAO.getDistrictAndStateImpactLevelWiseAlertDtls(userAccessLevelId, userAccessLevelValues, fromDate, toDate, stateId, impactLevelIds, districtIdList,catId,alertTypeList,editionTypeList,constituencyId,alertStatusId,locationLevel,"TvChannel",publicationId);
						setAlertDtls(alertCoreDashBoardVOs, tvChannelAlertList);	
						List<Object[]> newsPaperAelrtList = alertDAO.getDistrictAndStateImpactLevelWiseAlertDtls(userAccessLevelId, userAccessLevelValues, fromDate, toDate, stateId, impactLevelIds, districtIdList,catId,alertTypeList,editionTypeList,constituencyId,alertStatusId,locationLevel,"NewsPaper",publicationId);
						setAlertDtls(alertCoreDashBoardVOs, newsPaperAelrtList);	
						return alertCoreDashBoardVOs;
					}
				}
			}
			List<Object[]> alertList = alertDAO.getDistrictAndStateImpactLevelWiseAlertDtls(userAccessLevelId, userAccessLevelValues, fromDate, toDate, stateId, impactLevelIds, districtIdList,catId,alertTypeList,editionTypeList,constituencyId,alertStatusId,locationLevel,publicationType,publicationId);
			setAlertDtls(alertCoreDashBoardVOs, alertList);
			//Appending state in the case of state level alert.
			if(locationLevel != null && locationLevel.equalsIgnoreCase("State Level")){
			  Object[] stateObj = alertDAO.getStateByStateId(stateId);
			  if(stateObj != null && stateObj.length > 0){
				  if(alertCoreDashBoardVOs != null && alertCoreDashBoardVOs.size() > 0){
					  for(AlertCoreDashBoardVO  alertVO:alertCoreDashBoardVOs){
						  if(alertVO.getLocation() != null){
							  alertVO.setLocation(commonMethodsUtilService.getStringValueForObject(stateObj[1]));
						  }
					  }
				  }
			  }
			}
			return alertCoreDashBoardVOs;
			}catch(Exception e){  
				e.printStackTrace();  
				LOG.error("Error occured getDistrictAndStateImpactLevelWiseAlertDtls() method of AlertService{}");
			}
		return null;
	}
  public String getAlertLastUpdatedTime(){
	  String lastUpdatedTimeStr="";
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a ");
	  try{
		  Date lastUpdatedTime = alertDAO.getAlertLastUpdatedTime();
		  if(lastUpdatedTime != null){
			  lastUpdatedTimeStr= sdf.format(lastUpdatedTime);
		  }
	  }catch(Exception e){
		  LOG.error("Error occured at getAlertLastUpdatedTime() in AlertService {}",e);  
	  }
	  return lastUpdatedTimeStr;
}
  
  public String getAlertStatusOfArticle(Long articleId){
	  String result =null;
	  try{
		  
		  Long article = alertDAO.getAlertStatusOfArticle(articleId);
		  if(article !=null && article.longValue()>0l){
			  result="Y";
		  }else{
			  result="N";
		  }
		  
	  }catch(Exception e){
		  LOG.error("Error occured at getAlertStatusOfArticle() in AlertService {}",e); 
		  e.printStackTrace();
	  }
	  return result;
  }
  
  
  /*
   * auther : Srishailam Pittala
   * Date : 29th Dec, 2016
   * Description : to Get Cadre wise alert Details
   * */
  
  public AlertVO getAlertDetailsBySearch(Long tdpCadreId,Long stateId,String startDateStr,String endDateStr,String searchType,Long alertTypeId){
	  AlertVO returnVo = new AlertVO();
	  try {
		Date fromDate = dateUtilService.getCurrentDateAndTime();
		Date toDate = dateUtilService.getCurrentDateAndTime();
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		if(startDateStr != null && startDateStr.length() >0 && endDateStr != null && endDateStr.length() >0){
			fromDate = format.parse(startDateStr);
			toDate = format.parse(endDateStr);
		}
		
		List<Object[]> categoryList = alertCategoryDAO.getAllCategory(); 
		 List<Object[]> list = alertTypeDAO.getAlertType();	
		 List<Object[]> statusList = alertStatusDAO.getAllStatus();
			
		//List<AlertVO> categoriesList = new ArrayList<AlertVO>();
		 Map<Long,AlertVO> categoriesMap = new HashMap<Long, AlertVO>(0);
		 Map<Long,Map<Long,Long>> categoryStatusWiseCountMap = new HashMap<Long, Map<Long,Long>>(0);
		 Object[] totalArr = {"0","TOTAL ALERTS"};
		 categoryList.add(totalArr);
		if(categoryList != null && categoryList.size() > 0){
			for(Object[] param : categoryList){
				AlertVO categoryVO = new AlertVO();
				categoryVO.setCategoryId(commonMethodsUtilService.getLongValueForObject(param[0]));
				categoryVO.setCategory(commonMethodsUtilService.getStringValueForObject(param[1]));
				
				 List<AlertVO> alertTypeList = new ArrayList<AlertVO>();
				 if(list != null && list.size() > 0)
				 {
					 for(Object[] params : list)
					 {
						 AlertVO alertTypeVO = new AlertVO();
						 alertTypeVO.setAlertTypeId(commonMethodsUtilService.getLongValueForObject(params[0]));
						 alertTypeVO.setAlertTypeName(commonMethodsUtilService.getStringValueForObject(params[1]));
						 
						 List<AlertVO> alertStatusVOList = new ArrayList<AlertVO>();
						 if(statusList != null && statusList.size() > 0){
								for(Object[] parm : statusList){
									AlertVO alertStatusVO = new AlertVO();
									alertStatusVO.setStatusId(commonMethodsUtilService.getLongValueForObject(parm[0]));
									alertStatusVO.setStatus(commonMethodsUtilService.getStringValueForObject(parm[1]));
										alertStatusVOList.add(alertStatusVO);
										
										Map<Long,Long> statusMap = new HashMap<Long, Long>(0);
										if(categoryStatusWiseCountMap.get(categoryVO.getCategoryId()) != null){
											statusMap = categoryStatusWiseCountMap.get(categoryVO.getCategoryId());
										}
										statusMap.put(alertStatusVO.getStatusId(), 0L);
										categoryStatusWiseCountMap.put(categoryVO.getCategoryId(), statusMap);
								}
							}
						 alertTypeVO.setSubList1(alertStatusVOList);
						 categoryVO.setSubList2(alertStatusVOList);
						 if(alertTypeId != null && alertTypeId.longValue() ==0L)
							 alertTypeList.add(alertTypeVO);  
						else if(alertTypeVO.getAlertTypeId() != null && alertTypeVO.getAlertTypeId().longValue() == alertTypeId )
							 alertTypeList.add(alertTypeVO);  
					 }
				 }
				 
				 categoryVO.setSubList1(alertTypeList);
				 categoriesMap.put(categoryVO.getCategoryId(), categoryVO); 
				 
			}
		}
		
		List<Object[]> assignedList = null;
		if(searchType != null && (searchType.equalsIgnoreCase("Assigned") || searchType.equalsIgnoreCase("All"))){
			assignedList = alertAssignedDAO.getTdpCadreWiseAssignedAlertDetails(tdpCadreId,fromDate, toDate,alertTypeId);
			if(commonMethodsUtilService.isListOrSetValid(assignedList)){
				for (Object[] param : assignedList) {
					Long categoryId = commonMethodsUtilService.getLongValueForObject(param[0]);
					AlertVO categoryVO = categoriesMap.get(categoryId);
					if(categoryVO != null){
						 List<AlertVO> alertTypeList = categoryVO.getSubList1();
						 if(commonMethodsUtilService.isListOrSetValid(alertTypeList)){
							Long alertTypesId = commonMethodsUtilService.getLongValueForObject(param[1]);
							 for (AlertVO alertTypeVO : alertTypeList) {
								if(alertTypeVO.getAlertTypeId() != null && alertTypeVO.getAlertTypeId().longValue() == alertTypesId){
									 List<AlertVO> alertStatusVOList =  alertTypeVO.getSubList1();
									 if(commonMethodsUtilService.isListOrSetValid(alertStatusVOList)){
										 Long statusId = commonMethodsUtilService.getLongValueForObject(param[2]);
										 for (AlertVO statusVO : alertStatusVOList) {
											 if(statusVO.getStatusId() != null && statusVO.getStatusId().longValue() == statusId){
												 if(statusVO.getCount() != null)
													 statusVO.setCount( statusVO.getCount() + commonMethodsUtilService.getLongValueForObject(param[3]));
												 else
													 statusVO.setCount(commonMethodsUtilService.getLongValueForObject(param[3]));
												 
												 
												 	Map<Long,Long> statusMap = new HashMap<Long, Long>(0);
												 	Long overAllCount =0L;
													if(categoryStatusWiseCountMap.get(categoryVO.getCategoryId()) != null){
														statusMap = categoryStatusWiseCountMap.get(categoryVO.getCategoryId());
														if(commonMethodsUtilService.isMapValid(statusMap)){
															if(statusMap.get(statusVO.getStatusId()) != null){
																overAllCount = statusMap.get(statusVO.getStatusId());
															}
														}
													}
													 overAllCount = overAllCount+commonMethodsUtilService.getLongValueForObject(param[3]);
													statusMap.put(statusVO.getStatusId(), overAllCount);
													categoryStatusWiseCountMap.put(categoryVO.getCategoryId(), statusMap);
													
											 }
										}
									 }
								}
							}
						 }
					}
				}
			}
		}
		
		
		if(searchType != null && (searchType.equalsIgnoreCase("Involved") || searchType.equalsIgnoreCase("All"))){
			assignedList = alertCandidateDAO.getTdpCadreWiseInvoledAlertDetails(tdpCadreId,fromDate, toDate,alertTypeId);
			if(commonMethodsUtilService.isListOrSetValid(assignedList)){
				for (Object[] param : assignedList) {
					Long categoryId = commonMethodsUtilService.getLongValueForObject(param[0]);
					AlertVO categoryVO = categoriesMap.get(categoryId);
					if(categoryVO != null){
						 List<AlertVO> alertTypeList = categoryVO.getSubList1();
						 if(commonMethodsUtilService.isListOrSetValid(alertTypeList)){
							Long alertTypesId = commonMethodsUtilService.getLongValueForObject(param[1]);
							 for (AlertVO alertTypeVO : alertTypeList) {
								if(alertTypeVO.getAlertTypeId() != null && alertTypeVO.getAlertTypeId().longValue() == alertTypesId){
									 List<AlertVO> alertStatusVOList =  alertTypeVO.getSubList1();
									 if(commonMethodsUtilService.isListOrSetValid(alertStatusVOList)){
										 Long statusId = commonMethodsUtilService.getLongValueForObject(param[2]);
										 for (AlertVO statusVO : alertStatusVOList) {
											 if(statusVO.getStatusId() != null && statusVO.getStatusId().longValue() == statusId){
												 if(statusVO.getCount() != null)
													 statusVO.setCount( statusVO.getCount() + commonMethodsUtilService.getLongValueForObject(param[3]));
												 else
													 statusVO.setCount(commonMethodsUtilService.getLongValueForObject(param[3]));
												 
												 Map<Long,Long> statusMap = new HashMap<Long, Long>(0);
												 	Long overAllCount =0L;
													if(categoryStatusWiseCountMap.get(categoryVO.getCategoryId()) != null){
														statusMap = categoryStatusWiseCountMap.get(categoryVO.getCategoryId());
														if(commonMethodsUtilService.isMapValid(statusMap)){
															if(statusMap.get(statusVO.getStatusId()) != null){
																overAllCount = statusMap.get(statusVO.getStatusId());
															}
														}
													}
													 overAllCount = overAllCount+commonMethodsUtilService.getLongValueForObject(param[3]);
													statusMap.put(statusVO.getStatusId(), overAllCount);
													categoryStatusWiseCountMap.put(categoryVO.getCategoryId(), statusMap);
												 
											 }
										}
									 }
								}
							}
						 }
					}
				}
			}
		}
		
		
		
		Map<Long,Long> statusWiseMap = new HashMap<Long, Long>(0);
		Map<Long,Long> alerttypeWiseMap = new HashMap<Long, Long>(0);
		if(commonMethodsUtilService.isMapValid(categoriesMap)){
			 List<AlertVO> categoryWiseList = new ArrayList<AlertVO>(0);
			 for (Long categoryId : categoriesMap.keySet()) {
				 if(categoryId>0L){
					 AlertVO categoryVO1 = categoriesMap.get(categoryId);
					 if(categoryVO1 != null){
						 List<AlertVO> alertTypeList = categoryVO1.getSubList1();
						 if(commonMethodsUtilService.isListOrSetValid(alertTypeList)){
							 for (AlertVO alertTypeVO : alertTypeList) {
								 List<AlertVO> alertStatusVOList =  alertTypeVO.getSubList1();
								 if(commonMethodsUtilService.isListOrSetValid(alertStatusVOList)){
									 for (AlertVO statusVO : alertStatusVOList) {
										 if(statusVO.getCount() != null){
											 if(alertTypeVO.getCount() != null)
												 alertTypeVO.setCount( alertTypeVO.getCount()+statusVO.getCount());
											 else
												 alertTypeVO.setCount(statusVO.getCount());
										 }
										 
										 Long count =0L;
										 if(statusWiseMap.get(statusVO.getStatusId()) != null){
											 count = statusWiseMap.get(statusVO.getStatusId());
										 }
										 
										 if(count>0L){
											 if(statusVO.getCount() != null && statusVO.getCount().longValue()>0L)
											 count = count+statusVO.getCount();
										 }else{
											 if(statusVO.getCount() != null){
												 count = statusVO.getCount();
											 }else if(statusVO.getCount() == null){
												 count =0L;
											 }else{
												 count = count+statusVO.getCount();
											 }
										 }
										 
										 statusWiseMap.put(statusVO.getStatusId(), count);
									}
								 }
								 
								 if(alertTypeVO.getCount() != null){
									 if(categoryVO1.getCount() != null)
										 categoryVO1.setCount( categoryVO1.getCount()+alertTypeVO.getCount());
									 else
										 categoryVO1.setCount(alertTypeVO.getCount());
								 }
								 
								 Long count =0L;
								 if(alerttypeWiseMap.get(alertTypeVO.getAlertTypeId()) != null){
									 count = alerttypeWiseMap.get(alertTypeVO.getAlertTypeId());
								 }
								 
								 if(count>0L){
									 if(alertTypeVO.getCount() != null && alertTypeVO.getCount().longValue()>0L)
										 count = count+alertTypeVO.getCount();
								 }
								 else{
									 if(alertTypeVO.getCount() != null){
										 count = alertTypeVO.getCount();
									 }else if(alertTypeVO.getCount() == null){
										 count =0L;
									 }else{
										 count = count+alertTypeVO.getCount();
									 }
								 }
								 
								 
								 alerttypeWiseMap.put(alertTypeVO.getAlertTypeId(), count);
							}
						 }
						 
						 categoryWiseList.add(categoryVO1); 
					}
					 
					 if(commonMethodsUtilService.isMapValid(categoryStatusWiseCountMap)){
						 Map<Long,Long> statusMap =  categoryStatusWiseCountMap.get(categoryId);
						 if(commonMethodsUtilService.isMapValid(statusMap)){
								List<AlertVO> statussList = categoryVO1.getSubList2();
								if(commonMethodsUtilService.isListOrSetValid(statussList)){
									for (AlertVO alertVO : statussList) {
										alertVO.setCount(statusMap.get(alertVO.getStatusId()));
									}
								}
						}
					 }
					 
				 }
			}
			 
			 
			 AlertVO categoryVO2 = categoriesMap.get(0L);
			 if(categoryVO2 != null){
				 List<AlertVO> alertTypeList = categoryVO2.getSubList1();
				 if(commonMethodsUtilService.isListOrSetValid(alertTypeList)){
					 for (AlertVO alertTypeVO : alertTypeList) {
						 List<AlertVO> alertStatusVOList =  alertTypeVO.getSubList1();
						 if(commonMethodsUtilService.isListOrSetValid(alertStatusVOList)){
							 for (AlertVO statusVO : alertStatusVOList) {
								 statusVO.setCount(statusWiseMap.get(statusVO.getStatusId()));
							 }
						 }
						 
						 alertTypeVO.setCount(alerttypeWiseMap.get(alertTypeVO.getAlertTypeId()));
						 if(alertTypeVO.getCount() == null)
							 alertTypeVO.setCount(0L);
						 if(alertTypeVO.getCount() != null){
							 if(categoryVO2.getCount() != null)
								 categoryVO2.setCount( categoryVO2.getCount()+alertTypeVO.getCount());
							 else
								 categoryVO2.setCount(alertTypeVO.getCount());
						 }
					}
					 
					 List<AlertVO> alertStatussVOList =  categoryVO2.getSubList2();
					 if(commonMethodsUtilService.isListOrSetValid(alertStatussVOList)){
						 for (AlertVO statusVO : alertStatussVOList) {
							 if(commonMethodsUtilService.isMapValid(categoryStatusWiseCountMap)){
								 for (Long categoryId : categoriesMap.keySet()) {
									 if(categoryId >0L){
										 Map<Long,Long> statusMap =  categoryStatusWiseCountMap.get(categoryId);
										 if(commonMethodsUtilService.isMapValid(statusMap)){
											 Long count = statusMap.get(statusVO.getStateId());
											 if(count == null)
												 count =0L;
											 if(statusVO.getCount() != null){
												 statusVO.setCount(statusVO.getCount()+count);
											 }else{
												 statusVO.setCount(count);
											 }
										}
									 }
								}
							 }
							 statusVO.setCount(statusWiseMap.get(statusVO.getStatusId()));
						 }
					 }
				 }
				 categoryWiseList.add(categoryVO2); 
			}
			 
			returnVo.setSubList1(categoryWiseList);
		}
	}catch (Exception e) {
		LOG.error("Error occured at getAlertDetailsBySearch() in AlertService {}",e); 
		e.printStackTrace();
	}
	 return returnVo;
  }
  
  
  /*
   * auther : Srishailam Pittala
   * Date : 29th Dec, 2016
   * Description : to Get Cadre wise alert Details
   * */
  
  public AlertVO getCandidateAlertDetailsBySearch(Long tdpCadreId,Long stateId,String startDateStr,String endDateStr,String searchType,Long alertTypeId,Long categoryId,Long statusId){
	  AlertVO returnVo = new AlertVO();
	  try {
		Date fromDate = dateUtilService.getCurrentDateAndTime();
		Date toDate = dateUtilService.getCurrentDateAndTime();
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		if(startDateStr != null && startDateStr.length() >0 && endDateStr != null && endDateStr.length() >0){
			fromDate = format.parse(startDateStr);
			toDate = format.parse(endDateStr);
		}
		
		List<Object[]> assignedList = null;
	
		Map<Long,AlertVO> alertsMap = new HashMap<Long, AlertVO>(0);
		
		if(searchType != null && (searchType.equalsIgnoreCase("Assigned") || searchType.equalsIgnoreCase("All"))){
			assignedList = alertAssignedDAO.getCandidateAlertDetailsBySearch(tdpCadreId,fromDate, toDate,alertTypeId,categoryId,statusId);
			if(commonMethodsUtilService.isListOrSetValid(assignedList)){
				for (Object[] param : assignedList) {
					Long alertId= commonMethodsUtilService.getLongValueForObject(param[0]);
					String description = commonMethodsUtilService.getStringValueForObject(param[1]);
					String createdDate =commonMethodsUtilService.getStringValueForObject(param[2]);
					String lastUpdatedDate=commonMethodsUtilService.getStringValueForObject(param[3]);
					//Long statusId=commonMethodsUtilService.getLongValueForObject(param[0]);
					String status =commonMethodsUtilService.getStringValueForObject(param[5]);
					//Long impactLevelId=commonMethodsUtilService.getLongValueForObject(param[6]);
					String impactLevelStr =commonMethodsUtilService.getStringValueForObject(param[7]);
					
					
					AlertVO vo = new AlertVO();
					vo.setId(alertId);
					vo.setDesc(description);
					vo.setDate1(createdDate);
					vo.setDate2(lastUpdatedDate);
					vo.setLocationName(impactLevelStr);
					vo.setStatus(status);
					Long noOfDays = dateUtilService.noOfDayBetweenDates(vo.getDate1(), vo.getDate2());
					alertsMap.put(alertId, vo);
				}
			}
		}
		
		if(searchType != null && (searchType.equalsIgnoreCase("Involved") || searchType.equalsIgnoreCase("All"))){
			assignedList = alertCandidateDAO.getCandidateAlertDetailsBySearch(tdpCadreId,fromDate, toDate,alertTypeId,categoryId,statusId);
			if(commonMethodsUtilService.isListOrSetValid(assignedList)){
				for (Object[] param : assignedList) {
					Long alertId= commonMethodsUtilService.getLongValueForObject(param[0]);
					String description = commonMethodsUtilService.getStringValueForObject(param[1]);
					String createdDate =commonMethodsUtilService.getStringValueForObject(param[2]);
					String lastUpdatedDate=commonMethodsUtilService.getStringValueForObject(param[3]);
					//Long statusId=commonMethodsUtilService.getLongValueForObject(param[0]);
					String status =commonMethodsUtilService.getStringValueForObject(param[5]);
					//Long impactLevelId=commonMethodsUtilService.getLongValueForObject(param[6]);
					String impactLevelStr =commonMethodsUtilService.getStringValueForObject(param[7]);
					
					
					AlertVO vo = new AlertVO();
					vo.setId(alertId);
					vo.setDesc(description);
					vo.setDate1(createdDate);
					vo.setDate2(lastUpdatedDate);
					vo.setLocationName(impactLevelStr);
					vo.setStatus(status);

					Long noOfDays = dateUtilService.noOfDayBetweenDates(vo.getDate1(), vo.getDate2());
			          vo.setNoOfDays(noOfDays-1);
					alertsMap.put(alertId, vo);
				}
			}
		}
		
		if(commonMethodsUtilService.isMapValid(alertsMap)){
			List<AlertVO> candidateAlertsList = new ArrayList<AlertVO>(0);
			candidateAlertsList.addAll(alertsMap.values());
			returnVo.setSubList1(candidateAlertsList);
		}
		
	}catch (Exception e) {
		LOG.error("Error occured at getCandidateAlertDetailsBySearch() in AlertService {}",e); 
		e.printStackTrace();
	}
	 return returnVo;
  }
 /* public List<AlertVO> getAlertClarificationStatus(Long alertId){
	  List<AlertVO> returnVO = new ArrayList<AlertVO>();
	  try{
		 List<Object[]> status = alertClarificationDAO.getAlertClarificationStatus(alertId);
		 if(status != null && !status.isEmpty()){
			for (Object[] object : status) {
				AlertVO vo = new AlertVO();
				vo.setStatusId(commonMethodsUtilService.getLongValueForObject(object[0]));
				vo.setStatus(commonMethodsUtilService.getStringValueForObject(object[1]));
				returnVO.add(vo);
			}
		 }
	  }catch(Exception e){
		  LOG.error("Error occured at getAlertClarificationStatus() in AlertService {}",e); 
	  }
	  return returnVO;
  }
  */
  
	public ResultStatus uploadAlertsDocs(final Long userId,final Long alertId,final List<String> fileNamesList){
		 final ResultStatus resultStatus = new ResultStatus();
		 try{ 
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
						if(fileNamesList != null && !fileNamesList.isEmpty()){
							for (String string : fileNamesList) {
								AlertDocument alertDocument = new AlertDocument();
								alertDocument.setAlertId(alertId);
								alertDocument.setDocumentPath(string);
								alertDocument.setIsDeleted("N");
								alertDocument.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								alertDocument.setInsertedBy(userId);
								alertDocumentDAO.save(alertDocument);  
							}
						}
						
					resultStatus.setResultCode(0);
					resultStatus.setMessage("success");
				}
			});
		}catch(Exception e){
			resultStatus.setResultCode(1);
			resultStatus.setMessage("failure");
			LOG.error("Error occured at saveAlertClarificationDetails() in AlertService",e); 
		}
		 
		return resultStatus;
  	}
  
  public static String folderCreationForAlertsAttachments(){
	  	 try {
	  		 LOG.debug(" in FolderForNotCadre ");
	  		
	  		 String staticPath = IConstants.STATIC_CONTENT_FOLDER_URL;
			 String notCadreImagesFoldr = ActivityService.createFolder(staticPath+"/images/"+IConstants.ALERTS_ATTACHMENTS);
			 
			 String foldrSts = ActivityService.createFolder(notCadreImagesFoldr);
			 if(!foldrSts.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			 return staticPath+"/images/"+IConstants.ALERTS_ATTACHMENTS;
			 
		} catch (Exception e) {
			LOG.error(" Failed to Create");
			return "FAILED";
		}
	}
  
  	public AlertClarificationVO getClarificationDetails(Long alertId){
  		AlertClarificationVO vo = new AlertClarificationVO();
  		try {
  			
			List<Object[]> objList1 = clarificationRequiredDAO.getDetails(alertId);
			
			Object[] objArr = null;
			if(objList1 != null && objList1.size() > 0)
				objArr=objList1.get(objList1.size()-1);
			
			if(objArr != null){
				vo.setClarificationRequired(objArr[0] != null && objArr[0].toString().trim().equalsIgnoreCase("Y") ? "Y":"N");
				vo.setClarificationStatusId(objArr[1] != null && (Long)objArr[1] > 0l ? (Long)objArr[1] : 0l);
				vo.setClarificationStatus(objArr[2] != null ? objArr[2].toString():"");
			}
			
			
			if(objArr != null && objArr[0].toString().trim().equalsIgnoreCase("Y")){
				List<Object[]> objList = alertClarificationDAO.getAlertClarificationStatus(alertId);
				
				if(objList != null && objList.size() > 0){
					vo.setClarificationStatusId((Long)objList.get(0)[0]);
				}
				
				List<Object[]> commentsObjList = alertClarificationCommentsDAO.getClarificationComments(alertId);
				if(commentsObjList != null && commentsObjList.size() > 0){
					for (Object[] objects : commentsObjList) {
						KeyValueVO voIn = new KeyValueVO();
							voIn.setId((Long)objects[0]);
							voIn.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
						vo.getClarificationComments().add(voIn);
					}
				}
				
				List<Object[]> filePathList = alertClarificationDocumentDAO.getAlertAttachments(alertId);
				if(filePathList != null && filePathList.size() > 0){
					for (Object[] objects : filePathList) {
						KeyValueVO voIn = new KeyValueVO();
							voIn.setId((Long)objects[0]);
							voIn.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
						vo.getDocumentsList().add(voIn);
					}
				}
				
			}
		} catch (Exception e) {
			LOG.error("Error occured at getClarificationDetails() in AlertService",e); 
		}
  		return vo;
  	}
  	
  	public String updateCommentAndTrackingDetails(final Long userId,final Long  statusId,final Long alertId,final String remarks ){
  		String resultstatus = "failure";
  		try {
  			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
  		        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
  		        	AlertTrackingVO alertTrackingVO = new AlertTrackingVO();
	  					
	  					alertTrackingVO.setAlertId(alertId);
	  					alertTrackingVO.setAlertStatusId(8L);// default in verification Status by info cell and program committee memebers updation
	  					alertTrackingVO.setUserId(userId);
	  					alertTrackingVO.setAlertTrackingActionId(1L);// only status change no remark
  		        	if(remarks != null && !remarks.isEmpty()){
  		        		AlertComment alertComment = new AlertComment();
  	  				    alertComment.setComments(remarks);
  	  				    alertComment.setAlertId(alertId);
  	  				    alertComment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
  	  				    alertComment.setIsDeleted("N");
  	  				    alertComment.setInsertedBy(userId);
  	  				    alertComment = alertCommentDAO.save(alertComment);
  	  				    
  	  					
  	  					alertTrackingVO.setAlertTrackingActionId(2L);// with remark
  	  					alertTrackingVO.setAlertCommentId(alertComment.getAlertCommentId());
  		        	}
  		        	saveAlertTrackingDetails(alertTrackingVO);
  		        }	
  		     });
  			resultstatus = "success";
		} catch (Exception e) {
			LOG.error("Error occured at updateCommentAndTrackingDetails() in AlertService",e); 
		}
  		
  		return resultstatus;
  	}
  	public String saveClarificationRequiredStatus(Long userId,String status,Long alertId,String remarks){
  		String resultStatus; 
  		try {
  			List<Object[]> list = clarificationRequiredDAO.getDetails(alertId);
  					if(commonMethodsUtilService.isListOrSetValid(list))
  						clarificationRequiredDAO.updateStatusForOld(userId,alertId,dateUtilService.getCurrentDateAndTime(),"PROGRAM COMMITTE");
  			
  			ClarificationRequired cr = new ClarificationRequired();
			cr.setAlertId(alertId);
			if(remarks != null && !remarks.trim().isEmpty())
				cr.setAlertClarificationStatusId(1l);
			cr.setIsRequired(status);
			cr.setComment(remarks);
			cr.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			cr.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			cr.setInsertedBy(userId);
			cr.setUpdatedBy(userId);
			cr.setIsDeleted(status.trim().equalsIgnoreCase("Y")?"N":"Y");
			clarificationRequiredDAO.save(cr);
			
			
			AlertClarificationComments alertClarificationComments = new AlertClarificationComments();
			alertClarificationComments.setAlertId(alertId);
			alertClarificationComments.setComments(remarks);
			alertClarificationComments.setIsDeleted("N");
			alertClarificationComments.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			alertClarificationComments.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			alertClarificationComments.setInsertedBy(userId);
			alertClarificationComments.setUpdatedBy(userId);
			alertClarificationCommentsDAO.save(alertClarificationComments);
		
			alertClarificationDAO.updateStatusForOld(userId,alertId,dateUtilService.getCurrentDateAndTime());
			AlertClarification alertClarification = new AlertClarification();
				alertClarification.setAlertId(alertId);
				alertClarification.setAlertClarificationStatusId(1L);
				alertClarification.setIsDeleted("N");
				alertClarification.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				alertClarification.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				alertClarification.setInsertedBy(userId);
				alertClarification.setUpdatedBy(userId);
			alertClarification = alertClarificationDAO.save(alertClarification);
			
			updateCommentAndTrackingDetails(userId,1L,alertId,remarks);
			resultStatus = "success";
			
		} catch (Exception e) {
			resultStatus="failure";
			LOG.error("Error occured at saveClarificationRequiredStatus() in AlertService",e); 
		}
  		return resultStatus;
  	}
  	
  	public String removeAlertComment(Long commentId){
  		String status=null;
  		try {
			Integer val = alertClarificationCommentsDAO.updateCommentStatus(commentId);
			
			if(val.longValue() == 1l)status = "success";
			else status = "failure";
			
		} catch (Exception e) {
			LOG.error("Error occured at removeAlertComment() in AlertService",e);
		}
  		return status;
  	}
  	
  	public String removeAlertDocument(Long documentId){
  		String status=null;
  		try {
			Integer val = alertClarificationDocumentDAO.updateDocumentStatus(documentId);
			
			if(val.longValue() == 1l)status = "success";
			else status = "failure";
			
		} catch (Exception e) {
			LOG.error("Error occured at removeAlertDocument() in AlertService",e);
		}
  		return status;
  	}
  	/*
  	 * Author - Swadhin K Lenka
  	 * Date - 20-01-2017
  	 * (non-Javadoc)
  	 * @see com.itgrids.partyanalyst.service.IAlertService#getStatusAndCategoryWiseAlertsCount(java.lang.Long, java.lang.String, java.lang.String, java.lang.Long)
  	 */
  	public List<ClarificationDetailsCountVO> getStatusAndCategoryWiseAlertsCount(Long stateId,String fromDateStr,String toDateStr,Long alertTypeId){
  		List<AlertVO> voList = new ArrayList<AlertVO>(0);
  		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
  		
  		try {
			Date fromDate=null,toDate=null;
			
			if(fromDateStr != null && toDateStr != null){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			List<AlertCategory> acList = alertCategoryDAO.getAll();
			Map<Long,List<ActionTypeStatusVO>> actionTypeIdAndActionTypeStatusVOMap = new HashMap<Long,List<ActionTypeStatusVO>>();
			List<ActionTypeStatusVO> actionTypeStatusVOList = null;
			ActionTypeStatusVO actionTypeStatusVO = null;
			List<Object[]> alertActionTypeStatusList = actionTypeStatusDAO.getActionTypeList();
			if(alertActionTypeStatusList != null && alertActionTypeStatusList.size() > 0){
				for(Object[] param : alertActionTypeStatusList){
					actionTypeStatusVOList = actionTypeIdAndActionTypeStatusVOMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(actionTypeStatusVOList == null){
						actionTypeStatusVOList = new ArrayList<ActionTypeStatusVO>();
						actionTypeIdAndActionTypeStatusVOMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), actionTypeStatusVOList);
					}
					actionTypeStatusVO = new ActionTypeStatusVO();
					actionTypeStatusVO.setActionTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
					actionTypeStatusVO.setTypeName(commonMethodsUtilService.getStringValueForObject(param[1]));
					actionTypeStatusVO.setActionTypeStatusId(commonMethodsUtilService.getLongValueForObject(param[2]));
					actionTypeStatusVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[3]));
					actionTypeStatusVOList.add(actionTypeStatusVO);
				}
			}  
			
			
			//first build the template 
			List<Object[]> actionTypeList = actionTypeDAO.getActionTypeList();
			List<ClarificationDetailsCountVO> actionTypeDtlsList = new ArrayList<ClarificationDetailsCountVO>();
			ClarificationDetailsCountVO clarificationDetailsCountVO = null;
			if(actionTypeList != null && actionTypeList.size() > 0){
				for(Object[] param : actionTypeList){
					clarificationDetailsCountVO = new ClarificationDetailsCountVO();
					clarificationDetailsCountVO.setActionTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
					clarificationDetailsCountVO.setTypeName(commonMethodsUtilService.getStringValueForObject(param[1]));
					actionTypeDtlsList.add(clarificationDetailsCountVO);
				}
			}
			
			//build status list for all action type
			if(actionTypeDtlsList != null && actionTypeDtlsList.size() > 0){
				for(ClarificationDetailsCountVO param : actionTypeDtlsList){
					actionTypeStatusVOList = actionTypeIdAndActionTypeStatusVOMap.get(param.getActionTypeId());
					buildStatusList(param,actionTypeStatusVOList);
				}
			}
			//build category list for status
			List<ClarificationDetailsCountVO> actionTypeStatusList = null;
			if(actionTypeDtlsList != null && actionTypeDtlsList.size() > 0){
				for(ClarificationDetailsCountVO param : actionTypeDtlsList){
					actionTypeStatusList = param.getStatusTypeList();
					if(actionTypeStatusList != null && actionTypeStatusList.size() > 0){
						for(ClarificationDetailsCountVO param1 : actionTypeStatusList){
							buildCategoryList(param1,acList);
						}
					}
				}
			}  
			
			List<Object[]> alertCntList = verificationStatusDAO.getStatusWiseAlertCount(stateId, fromDate, toDate, alertTypeId);
			
			List<ClarificationDetailsCountVO> actionTypeStatusListFinal = null;
			List<ClarificationDetailsCountVO> categoryList = null;
			ClarificationDetailsCountVO clarificationDtlsCountVO = null;  
			if(alertCntList != null && alertCntList.size() > 0){
				for(Object[] param3 : alertCntList){
					//update count for action type...
					clarificationDtlsCountVO = getMatchedVOForActionType(actionTypeDtlsList,commonMethodsUtilService.getLongValueForObject(param3[0]));
					clarificationDtlsCountVO.setCount(clarificationDtlsCountVO.getCount() + commonMethodsUtilService.getLongValueForObject(param3[6]));
					//update count for action type status...
					actionTypeStatusListFinal = clarificationDtlsCountVO.getStatusTypeList();
					clarificationDtlsCountVO = getMatchedVOForActionTypeStatus(actionTypeStatusListFinal,commonMethodsUtilService.getLongValueForObject(param3[2]));
					clarificationDtlsCountVO.setCount(clarificationDtlsCountVO.getCount() + commonMethodsUtilService.getLongValueForObject(param3[6]));
					//update count for category 
					categoryList = clarificationDtlsCountVO.getCategoryTypeList();
					clarificationDtlsCountVO = getmatchedVOForCategory(categoryList,commonMethodsUtilService.getLongValueForObject(param3[4]));
					clarificationDtlsCountVO.setCount(clarificationDtlsCountVO.getCount() + commonMethodsUtilService.getLongValueForObject(param3[6]));
				}
			}else{
				actionTypeDtlsList.clear();     
			}
			
			return actionTypeDtlsList;
		} catch (Exception e) {
			LOG.error("Error occured at getStatusAndCategoryWiseAlertsCount() in AlertService",e);
		}
  		return null;
  	}
  	public void buildStatusList(ClarificationDetailsCountVO param,List<ActionTypeStatusVO> actionTypeStatusVOList){
  		List<ClarificationDetailsCountVO> statusTypeList = new ArrayList<ClarificationDetailsCountVO>();
  		ClarificationDetailsCountVO clarificationDetailsCountVO = null;
  		if(actionTypeStatusVOList != null && actionTypeStatusVOList.size() >0){
  			for(ActionTypeStatusVO actionTypeStatusVO : actionTypeStatusVOList){
  				clarificationDetailsCountVO = new ClarificationDetailsCountVO();
  	  			clarificationDetailsCountVO.setActionTypeStatusId(actionTypeStatusVO.getActionTypeStatusId());
  	  			clarificationDetailsCountVO.setStatus(actionTypeStatusVO.getStatus());
  	  			statusTypeList.add(clarificationDetailsCountVO);
  			}
  			param.getStatusTypeList().addAll(statusTypeList);
  		}
  	}
  	public void buildCategoryList(ClarificationDetailsCountVO param1,List<AlertCategory> acList){
  		List<ClarificationDetailsCountVO> alertCategoryList = new ArrayList<ClarificationDetailsCountVO>();
  		ClarificationDetailsCountVO clarificationDetailsCountVO = null;
  		if(acList != null && acList.size() > 0){
  			for(AlertCategory param : acList){
  				clarificationDetailsCountVO = new ClarificationDetailsCountVO();
  				clarificationDetailsCountVO.setAlertCategoryId(param.getAlertCategoryId());
  				clarificationDetailsCountVO.setCategory(param.getCategory());
  				alertCategoryList.add(clarificationDetailsCountVO);
  			}
  			param1.getCategoryTypeList().addAll(alertCategoryList);
  		}
  	}
  	public List<IdNameVO> getResulttoList(List<AlertCategory> acList){
  		List<IdNameVO> voList = new ArrayList<IdNameVO>(0);
  		if(acList != null && acList.size() > 0){
  			for (AlertCategory ac : acList) {
  				IdNameVO vo = new IdNameVO();
  				vo.setId(ac.getAlertCategoryId());
  				vo.setName(ac.getCategory());
  				voList.add(vo);
			}
  		}
  		return voList;
  	}
  	public ClarificationDetailsCountVO getMatchedVOForActionType(List<ClarificationDetailsCountVO> actionTypeDtlsList,Long actionTypeId){
  		if(actionTypeDtlsList != null && actionTypeDtlsList.size() > 0){
  			for(ClarificationDetailsCountVO param : actionTypeDtlsList){
  				if(param.getActionTypeId().equals(actionTypeId)){
  					return param;
  				}
  			}
  		}
		return null;
  	}
  	public ClarificationDetailsCountVO getMatchedVOForActionTypeStatus(List<ClarificationDetailsCountVO> actionTypeStatusListFinal,Long actionTypeStatusId){
  		if(actionTypeStatusListFinal != null && actionTypeStatusListFinal.size() > 0){
  			for(ClarificationDetailsCountVO param : actionTypeStatusListFinal){
  				if(param.getActionTypeStatusId().equals(actionTypeStatusId)){
  					return param;
  				}
  			}
  		}
  		return null;
  	}
  	public ClarificationDetailsCountVO getmatchedVOForCategory(List<ClarificationDetailsCountVO> categoryList,Long categoryId){
  		if(categoryList != null && categoryList.size() > 0){
  			for(ClarificationDetailsCountVO param : categoryList){
  				if(param.getAlertCategoryId().equals(categoryId)){
  					return param;
  				}
  			}
  		}
  		return null;
  	}
  	public AlertVO getMatchedStatusVO(List<AlertVO> voList,Long statusId){
  		if(voList != null && voList.size() > 0){
  			for (AlertVO alertVO : voList) {
				if(alertVO.getStateId().equals(statusId))
					return alertVO;
			}
  		}
  		return null;
  	}
  	
  	public IdNameVO getMatchedCategoryVO(List<IdNameVO> idNameVOList,Long categoryId){
  		if(idNameVOList != null && idNameVOList.size() > 0){
  			for (IdNameVO idNameVO : idNameVOList) {
				if(idNameVO.getId().equals(categoryId))
					return idNameVO;
			}
  		}
  		return null;
  	}
  	
  	public List<AlertDataVO> getLocationLevelAlertClarificationData(Long userId,AlertInputVO inputVO){
  		List<AlertDataVO> voList = new ArrayList<AlertDataVO>(0);
  		try {

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
  				 List<Object[]> list = clarificationRequiredDAO.getLocationLevelAlertClarificationData(userTypeIds,inputVO,fromDate,toDate);
  				 List<AlertClarificationStatus> list1 = alertClarificationStatusDAO.getAll();
  				 if(commonMethodsUtilService.isListOrSetValid(list)){
  					 for (Object[] param : list) {
						Long id = commonMethodsUtilService.getLongValueForObject(param[9]);
						for (AlertClarificationStatus status : list1) {
							if(id != null && status.getAlertClarificationStatusId() != null && id.longValue() == status.getAlertClarificationStatusId())
								param[9] = status.getStatus();
						}
					}
  				 }
  				 setAlertLocationWiseData(list,returnList,new HashMap<Long,String>(),"");
  			}
  			catch(Exception e)
  			{
  				e.printStackTrace();
  			}
  			return returnList;
  		
		} catch (Exception e) {
			LOG.error("Error occured at getLocationLevelAlertClarificationData() in AlertService",e);
		}
  		return voList;
  	}
  	
  	public List<AlertDataVO> getAllAlertsWithoutFilter(Long userId,AlertInputVO inputVO){
  		List<AlertDataVO> voList = new ArrayList<AlertDataVO>(0);
  		try {

  			List<AlertDataVO> returnList = new ArrayList<AlertDataVO>();
  			 List<Long> userTypeIds = alertSourceUserDAO.getAlertSourceUserIds(userId);
  			 SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");  
  			
  			try{
  				Date fromDate = null;Date toDate=null;
  				Date fromDate2 = null;Date toDate2=null;
  				if(inputVO.getFromDate() != null && !inputVO.getFromDate().toString().isEmpty()){
  					fromDate = sdf.parse(inputVO.getFromDate());
  					toDate = sdf.parse(inputVO.getToDate());
  				}
  				if(inputVO.getFromDate2() != null && !inputVO.getFromDate2().toString().isEmpty()){
  					fromDate2 = sdf.parse(inputVO.getFromDate2());
  					toDate2 = sdf.parse(inputVO.getToDate2());  
  				}
  				 List<Object[]> list = verificationStatusDAO.getAllAlerts(userTypeIds,inputVO,fromDate,toDate,fromDate2,toDate2);//done
  				 setAlertLocationWiseData(list,returnList,new HashMap<Long,String>(),"verification");
  			}
  			catch(Exception e)
  			{
  				e.printStackTrace();
  			}
  			return returnList;
  		
		} catch (Exception e) {
			LOG.error("Error occured at getLocationLevelAlertClarificationData() in AlertService",e);
		}
  		return voList;
  	}
  	
  	public List<KeyValueVO> getDocumentsForAlert(Long alertId){
  		List<KeyValueVO> voList = new ArrayList<KeyValueVO>(0);
  		try {
			List<Object[]> objList = alertDocumentDAO.getDocumentsForAlert(alertId);
			
			if(objList != null && objList.size() > 0){
				for (Object[] objects : objList) {
					KeyValueVO vo = new KeyValueVO();
					vo.setId((Long)objects[0]);
					vo.setName(objects[1].toString());
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured at getDocumentsForAlert() in AlertService",e);
		}
  		return voList;
  	}
  	
  	public List<Object[]> gatMatchedObject(Long cadreId,List<Object[]> objList){
  		List<Object[]> returnObj = new ArrayList<Object[]>(0);
  		if(objList != null && objList.size() > 0){
  			for (Object[] objects : objList) {
				if(objects[6] != null && ((Long)objects[6]).equals(cadreId))
					returnObj.add(objects);
			}
  		}
  		return returnObj;
  	}
  	/* Alert Verification Service Start */
  	
  	/*
  	 * Author:Santosh
  	 * Description:This service is used to save and update Alert Verification Details.
  	 */
  	public String updateVerificationStatus(final Long alertId ,final String comments,final Long actionTypeStatusId,final Long userId, final Map<File,String> mapFiles)
  	{
  	String resultStatus = (String) transactionTemplate
  			.execute(new TransactionCallback() {
  				public Object doInTransaction(TransactionStatus status) {
  					String rs = new String();
  					try {
  						
  				Long alertVerificationUserTypeId = alertVerificationUserTypeUserDAO.getAlertVerificationUserTypeId(userId);
  				Long statusId = verificationStatusDAO.getAlertStatusId(alertId);
  				
  				DateUtilService date = new DateUtilService();
  				boolean flag=false;
				if( actionTypeStatusId == 0 || !actionTypeStatusId.equals(statusId)){
					flag = true;	
				}
				
			    if(alertVerificationUserTypeId == 1l && actionTypeStatusId.longValue() == 2l  && comments != null && comments.trim().length() > 0){ // 2l completed status id
				 verificationStatusDAO.updateStatusForOldAlert(userId, alertId, date.getCurrentDateAndTime()); // program committee is going to change status in progress.
				  flag = true;
	  		    }
				if(actionTypeStatusId.longValue() > 0 && !actionTypeStatusId.equals(statusId)){
					 verificationStatusDAO.updateStatusForOldAlert(userId, alertId, date.getCurrentDateAndTime());	
				}
			
				if(flag){
					 VerificationStatus verificationStatus = new VerificationStatus();
	  				 
	  				 if(alertId != null && alertId.longValue() > 0){
	  					 verificationStatus.setAlertId(alertId);
	  				 }
	  				 if(actionTypeStatusId.longValue() == 0l){
	  					 verificationStatus.setActionTypeStatusId(1l);//default progress status
	  			     }else if(alertVerificationUserTypeId == 1l && actionTypeStatusId.longValue() ==2l && comments != null && comments.trim().length() > 0){ //2 completed
	  			    	 verificationStatus.setActionTypeStatusId(1l); 
	  				 }else {
	  					verificationStatus.setActionTypeStatusId(actionTypeStatusId); 
	  				 }
	  				 if(alertVerificationUserTypeId != null && alertVerificationUserTypeId.longValue() > 0l){
	  					verificationStatus.setAlertVerificationUserTypeId(alertVerificationUserTypeId);	 
	  				 }
	  				 verificationStatus.setInsertedBy(userId);
	  				 verificationStatus.setInsertedTime(date.getCurrentDateAndTime());
	  				 verificationStatus.setUpdatedBy(userId);
	  				 verificationStatus.setUpdatedTime(date.getCurrentDateAndTime());
	  				 verificationStatus.setIsDeleted("N");
	  				 verificationStatus = verificationStatusDAO.save(verificationStatus);	
				}
  				
  				 
				 VerificationConversation verificationConversation = new VerificationConversation();
  				 if(alertId != null && alertId.longValue() > 0){
  					 verificationConversation.setAlertId(alertId);
  				 }
  				 if(alertVerificationUserTypeId != null && alertVerificationUserTypeId.longValue() > 0l){
  					verificationConversation.setAlertVerificationUserTypeId(alertVerificationUserTypeId);	 
   				 }
  				 verificationConversation.setInsertedBy(userId);
  				 verificationConversation.setUpdatedBy(userId);
  			     verificationConversation.setInsertedTime(date.getCurrentDateAndTime());
  			     verificationConversation.setUpdatedTime(date.getCurrentDateAndTime());
  			     verificationConversation.setIsDeleted("N");
  			     verificationConversation = verificationConversationDAO.save(verificationConversation);	
  			     
  			      VerificationComments verificationComments = new VerificationComments();
  			    
  			      verificationComments.setVerificationConversationId(verificationConversation.getVerificationConversationId());
  				  if(alertVerificationUserTypeId != null && alertVerificationUserTypeId.longValue() > 0l){
  					verificationComments.setAlertVerificationUserTypeId(alertVerificationUserTypeId);	 
   				  }
  			      verificationComments.setComments(comments);
  			      verificationComments.setInsertedBy(userId);
  			      verificationComments.setUpdatedBy(userId);
  			      verificationComments.setInsertedTime(date.getCurrentDateAndTime());
  			      verificationComments.setUpdatedTime(date.getCurrentDateAndTime());
  			      verificationComments.setIsDeleted("N");
  			      verificationCommentsDAO.save(verificationComments);
  			     
  			      if(mapFiles != null && mapFiles.size() > 0){
  			    	String folderName = IConstants.STATIC_CONTENT_FOLDER_PATH+"/Reports/"+IConstants.TOUR_DOCUMENTS;
  			    	
  		  			Calendar calendar = Calendar.getInstance();
  		  			calendar.setTime(new Date());
  		  			 int year = calendar.get(Calendar.YEAR);
  		  			 int month = calendar.get(Calendar.MONTH);
  		  			 int temp = month+1;
  		  			 String monthText = getMonthForInt(temp);
  		  			
  		  			 StringBuilder pathBuilder = null;
  		  			 VerificationDocuments verificationDocuments = null;
  		  			 StringBuilder str ;
  		  			 
  		  			 for (Map.Entry<File, String> entry : mapFiles.entrySet())
  		  			 {
  		  				 pathBuilder = new StringBuilder();
  		  				 str = new StringBuilder();
  		  				 Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
  		  				 String destPath = folderName+"/"+randomNumber+"."+entry.getValue();
  		  				// pathBuilder.append(monthText).append("").append(year).append("/").append(randomNumber).append(".")
  		  				  pathBuilder.append(randomNumber).append(".")
  		  				 .append(entry.getValue());
  		  				 str.append(randomNumber).append(".").append(entry.getValue());
  		  				 String fileCpyStts = copyFile(entry.getKey().getAbsolutePath(),destPath);
  		  				 
  		  					if(fileCpyStts.equalsIgnoreCase("error")){
  		  						LOG.error(" Exception Raise in copying file in ToursService ");
  		  						throw new ArithmeticException();
  		  					}
  		  					
  		  				  	  verificationDocuments = new VerificationDocuments();
  			  				  verificationDocuments.setDocumentPath(pathBuilder.toString());				
  			  				  verificationDocuments.setVerificationConversationId(verificationConversation.getVerificationConversationId());
  			  				  if(alertVerificationUserTypeId != null && alertVerificationUserTypeId.longValue() > 0){
  			  					verificationDocuments.setAlertVerificationUserTypeId(alertVerificationUserTypeId);  
  			  				  }
  				  			  verificationDocuments.setInsertedBy(userId);
  				  			  verificationDocuments.setUpdatedBy(userId);
  				  			  verificationDocuments.setInsertedTime(date.getCurrentDateAndTime());
  				  			  verificationDocuments.setUpdatedTime(date.getCurrentDateAndTime());
  				  			  verificationDocuments.setIsDeleted("N");
  				  			   verificationDocumentsDAO.save(verificationDocuments);
  			  		 }
  		        }
  				  rs = "success";
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
  public AlertVerificationVO getAlertVerificationDtls(Long alertId){
	    AlertVerificationVO resultVO = new AlertVerificationVO();
	 	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
		SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");
		Map<Long,AlertVerificationVO> alertCommentsMap = new LinkedHashMap<Long, AlertVerificationVO>(0);
	  try{
		  Object[] alertStatusObj = verificationStatusDAO.getAertStausIdAndName(alertId);
		   if(alertStatusObj != null && alertStatusObj.length > 0){
			   resultVO.setAlertActionTypeStatusId((Long)alertStatusObj[0]);
			   resultVO.setActionTypeStatus(commonMethodsUtilService.getStringValueForObject(alertStatusObj[1]));
		   }
		
		   List<Object[]> rtnrObjList = verificationCommentsDAO.getAletConversationDtls(alertId);
		   
		    if(rtnrObjList != null && rtnrObjList.size() > 0){
		    	for(Object[] param:rtnrObjList){
		    		Long conversationId = commonMethodsUtilService.getLongValueForObject(param[0]);
		    		AlertVerificationVO commentVO = alertCommentsMap.get(conversationId);
		    		Long userTypeId = commonMethodsUtilService.getLongValueForObject(param[1]);
		    		if(commentVO == null){
		    			commentVO = new AlertVerificationVO();
		    			if(userTypeId == 1l){
		    				commentVO.setHeading("Program Committee Remarks");
		    			}else if(userTypeId == 2l){
		    				commentVO.setHeading("Info Cell Remarks");	
		    			}
		    			commentVO.setComments(commonMethodsUtilService.getStringValueForObject(param[2]));
		    			if(param[3] != null){
		    				commentVO.setUpdateTime(sdf.format(param[3]));	
		    			}
		    			if(param[4] != null){ 
		    				Date timeInDateFormat = sdf1.parse(param[4].toString());
		    				commentVO.setTime(_12HourSDF.format(timeInDateFormat));	
		    			}
		    			commentVO.setName(commonMethodsUtilService.getStringValueForObject(param[5])+" "+commonMethodsUtilService.getStringValueForObject(param[6]));//first and last name
		    			commentVO.setDocumentList(new ArrayList<String>());
		    			alertCommentsMap.put(conversationId, commentVO);
		    		}
		    		 String filePath = commonMethodsUtilService.getStringValueForObject(param[7]);
	    			 if(filePath.length() > 0){
	    				 commentVO.getDocumentList().add(filePath);
	    			 }
		    		
		    	}
		    }
		   if(alertCommentsMap != null && alertCommentsMap.size() > 0){
			   resultVO.setConversationList(new ArrayList<AlertVerificationVO>(alertCommentsMap.values()));  
		   }
	  }catch(Exception e){
		  LOG.error("Exception Occured in getAlertVerificationDtls() in ToursService", e);
	  }
	  return resultVO;
  }	
  public List<AlertVerificationVO> getAlertTypeActionStatus(Long actionTypeId){
	  List<AlertVerificationVO> actionTypeStatusList = new ArrayList<AlertVerificationVO>(0);
	  try{
		  List<Object[]> rtrnObjLst = actionTypeStatusDAO.getAlertActionTypeWiseStatus(actionTypeId);
		  if(rtrnObjLst != null && rtrnObjLst.size() > 0l){
			  for(Object[] param:rtrnObjLst){
				  AlertVerificationVO statuVO = new AlertVerificationVO();
				  statuVO.setAlertActionTypeStatusId(commonMethodsUtilService.getLongValueForObject(param[2]));
				  statuVO.setActionTypeStatus(commonMethodsUtilService.getStringValueForObject(param[3]));
				  actionTypeStatusList.add(statuVO);
				  
			  }
		  }
	  }catch(Exception e){
		  LOG.error("Exception Occured in getAlertTypeActionStatus() in ToursService", e);  
	  }
	  return actionTypeStatusList;
  }
	/* Alert Verification Service end */
  
  
  /*
	 * Author:Santosh
	 * Description : This service is used to get alert details based on alert type
	 */
	public List<AlertCoreDashBoardVO> getAlertDetailsByAlertType(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId,Long activityMemberId){
		LOG.info("Entered in getAlertDetailsByAlertType() method of AlertService{}");
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
				List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(new ArrayList<Long>(userAccessLevelValues));
				userAccessLevelId = 5L;
				userAccessLevelValues.clear();
				userAccessLevelValues.addAll(parliamentAssemlyIds);      
			}
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			List<Object[]> alertList = alertDAO.getAlertDtlsByAlertTypeId(fromDate, toDate, stateId, alertTypeId, userAccessLevelId, userAccessLevelValues);	
			setAlertDtls(alertCoreDashBoardVOs, alertList);
			return alertCoreDashBoardVOs;
		}catch(Exception e){
			e.printStackTrace();  
			LOG.error("Error occured getAlertDetailsByAlertType() method of AlertService{}");
		}
		return null;        
	}
	
	
	public List<AlertVO> getTotalAlertGroupByStatusForCentralMembers(String fromDateStr, String toDateStr, Long stateId,Long alertTypeId,Long tdpCadreId){
		LOG.info("Entered in getTotalAlertGroupByStatusForCentralMembers() method of AlertService{}");
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
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByStatusForCentralMembers(fromDate,toDate,stateId,alertTypeId,tdpCadreId);
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
			LOG.error("Error occured getTotalAlertGroupByStatusForCentralMembers() method of AlertService{}");
		}
		return null;
	}
	
	public List<AlertVO> getTotalAlertGroupByStatusThenCategoryForCentralMembers(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId, Long tdpCadreId){
		LOG.info("Entered in getTotalAlertGroupByStatusThenCategoryForCentralMembers() method of AlertService{}");
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
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByStatusForCentralMembers(fromDate,toDate,stateId,alertTypeId,tdpCadreId);
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
			//get all the alert count group by status then category.
			Map<Long,String> statusIdAndNameMap = new HashMap<Long,String>();
			Map<Long,Long> categoryIdAndCountMap = null;//new HashMap<Long, Long>();
			Map<Long,Map<Long,Long>> statusIdAndCategoryIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
			List<Object[]> alertCountGrpByCatList = alertDAO.getTotalAlertGroupByStatusThenCategoryForCentralMembers(fromDate, toDate, stateId, alertTypeId,tdpCadreId);
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
			LOG.error("Error occured getTotalAlertGroupByStatusThenCategoryForCentralMembers() method of AlertService{}");
		}
		return null;
	}
	
	public List<AlertDataVO> getAlertAssignedCandidatesForCentralMembers(Long tdpCadreId)
	{
		List<AlertDataVO> dataList = new ArrayList<AlertDataVO>();
		try{
			
			List<Object[]> list = alertCandidateDAO.getAlertAssignedCandidatesForCentralMembers(tdpCadreId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					AlertDataVO vo = new AlertDataVO();
					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					dataList.add(vo);
				}
			}
		
		}
		catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception in getAlertAssignedCandidatesForCentralMembers()",e);	
		}
		return dataList;
	}
	
	public List<AlertDataVO> getLocationLevelWiseAlertsDataForCentralMembers(Long userId,AlertInputVO inputVO)
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
			 List<Object[]> list = alertDAO.getLocationLevelWiseAlertsDataForCentralMembers(userTypeIds,inputVO,fromDate,toDate);//done
			 List<Object[]> list2 = verificationStatusDAO.getTotalStatus();
			 Map<Long,String> alertAndStatusMap = new HashMap<Long,String>();
			 if(list2 != null && list2.size() > 0){
				 for(Object[] param : list2){
					 alertAndStatusMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				 }
			 }
			 setAlertLocationWiseData(list,returnList,alertAndStatusMap,"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
	}
	
	public List<AlertDataVO> getAllAlertsWithoutFilterForCentralMembers(Long userId,AlertInputVO inputVO){
  		List<AlertDataVO> voList = new ArrayList<AlertDataVO>(0);
  		try {

  			List<AlertDataVO> returnList = new ArrayList<AlertDataVO>();
  			 List<Long> userTypeIds = alertSourceUserDAO.getAlertSourceUserIds(userId);
  			 SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");  
  			
  			try{
  				Date fromDate = null;Date toDate=null;
  				Date fromDate2 = null;Date toDate2=null;
  				if(inputVO.getFromDate() != null && !inputVO.getFromDate().toString().isEmpty()){
  					fromDate = sdf.parse(inputVO.getFromDate());
  					toDate = sdf.parse(inputVO.getToDate());
  				}
  				if(inputVO.getFromDate2() != null && !inputVO.getFromDate2().toString().isEmpty()){
  					fromDate2 = sdf.parse(inputVO.getFromDate2());
  					toDate2 = sdf.parse(inputVO.getToDate2());  
  				}
  				 List<Object[]> list = verificationStatusDAO.getAllAlertsForCentralMembers(userTypeIds,inputVO,fromDate,toDate,fromDate2,toDate2);//done
  				 setAlertLocationWiseData(list,returnList,new HashMap<Long,String>(),"verification");
  			}
  			catch(Exception e)
  			{
  				e.printStackTrace();
  			}
  			return returnList;
  		
		} catch (Exception e) {
			LOG.error("Error occured at getLocationLevelAlertClarificationData() in AlertService",e);
		}
  		return voList;
  	}
	/*
	 * Author:Swadhin Lenka
	*/
	public AlertVO getAlertDetailsForEdit(Long alertId){
		try{
			//contains alert details
			AlertVO alertVO = new AlertVO();
			//contains assign candidate dtls
			List<IdNameVO> assignedCandList = new ArrayList<IdNameVO>();
			//contains involved candidate dtls
			List<IdNameVO> involvedCandList = new ArrayList<IdNameVO>();
			//contains document list
			List<IdNameVO> docList = new ArrayList<IdNameVO>();
			IdNameVO idNameVO = null;
			List<Object[]> alertDtlsList = alertDAO.getAlertDetailsForUpdate(alertId);
			List<Object[]> assignedCandidateList = alertAssignedDAO.getAssignedCandidateList(alertId);
			List<Object[]> involvedCandidateList = alertCandidateDAO.getInvolveCandidateList(alertId);
			List<Object[]> documentList = alertDocumentDAO.getDocumentsForAlert(alertId);
			//this list c ontain only one element
			if(alertDtlsList != null && alertDtlsList.size() > 0){
				for(Object[] param : alertDtlsList){
					alertVO.setAlertTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
					alertVO.setTitle(commonMethodsUtilService.getStringValueForObject(param[1]));
					alertVO.setDesc(commonMethodsUtilService.getStringValueForObject(param[2]));
					alertVO.setSeverity(commonMethodsUtilService.getLongValueForObject(param[3]));
					alertVO.setLocationLevelId(commonMethodsUtilService.getLongValueForObject(param[4]));
					alertVO.setAlertSourceId(commonMethodsUtilService.getLongValueForObject(param[5]));
					alertVO.setAlertImpactId(commonMethodsUtilService.getLongValueForObject(param[6]));
					alertVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[7]));
					alertVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[8]));
					alertVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[9]));
					alertVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[10]));
					alertVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[11]));
					alertVO.setLocalBodyId(commonMethodsUtilService.getLongValueForObject(param[12]));
					alertVO.setWardId(commonMethodsUtilService.getLongValueForObject(param[13]));
				}
			}
			if(assignedCandidateList != null && assignedCandidateList.size() > 0){
				for(Object[] param : assignedCandidateList){
					idNameVO = new IdNameVO();
					idNameVO.setCadreId(commonMethodsUtilService.getLongValueForObject(param[0]));
					idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					idNameVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[2]));
					idNameVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[3]));
					idNameVO.setImage(commonMethodsUtilService.getStringValueForObject(param[4]));
					assignedCandList.add(idNameVO);
				}
			}
			if(involvedCandidateList != null && involvedCandidateList.size() > 0){
				for(Object[] param : involvedCandidateList){
					idNameVO = new IdNameVO();
					idNameVO.setCadreId(commonMethodsUtilService.getLongValueForObject(param[0]));
					idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					idNameVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[2]));
					idNameVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[3]));
					idNameVO.setStatusId(commonMethodsUtilService.getLongValueForObject(param[4]));
					idNameVO.setImage(commonMethodsUtilService.getStringValueForObject(param[5])); 
					involvedCandList.add(idNameVO);
				}
			}
			if(documentList != null && documentList.size() > 0){
				for(Object[] param : documentList){
					idNameVO = new IdNameVO();
					idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
					idNameVO.setPositionName(commonMethodsUtilService.getStringValueForObject(param[1]));
					docList.add(idNameVO);
				}
			}
			if(assignedCandList != null && assignedCandList.size() > 0){
				alertVO.setAssignList(assignedCandList);
			}
			if(involvedCandList != null && involvedCandList.size() > 0){
				alertVO.setIdNamesList(involvedCandList);
			}
			if(docList != null && docList.size() > 0){
				alertVO.setDocList(docList);
			}
			return alertVO;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getAlertDetailsForEdit() method of AlertService{}");
		}  
		return null;
	}
	
	public String editAlert(final AlertVO inputVO,final Long userId, final Map<File,String> mapFiles)
	{
		
	    String resultStatus = (String) transactionTemplate .execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					String rs = new String();
					try {
				 DateUtilService date = new DateUtilService();
				 Alert alert = alertDAO.get(inputVO.getAlertId());
				 
				 alert.setAlertSeverityId(inputVO.getSeverity());
				 alert.setAlertTypeId(inputVO.getAlertTypeId());
				 alert.setImpactLevelId(inputVO.getLocationLevelId());
				 alert.setImpactLevelValue(inputVO.getLocationValue());
				 alert.setDescription(inputVO.getDesc().toString());
				 alert.setUpdatedBy(userId);
				 alert.setImpactScopeId(inputVO.getAlertImpactId());
				 alert.setAlertSourceId(inputVO.getAlertSourceId());
				 alert.setUpdatedTime(date.getCurrentDateAndTime());
				 alert.setAlertCategoryId(1L);//default Manual alert
				 if(inputVO.getAssignList() != null && inputVO.getAssignList().size() > 0){
					 if(alert.getAlertStatusId().longValue() == 1L){
						 alert.setAlertStatusId(2L);
					 }
				 }else if(inputVO.getAssignList() != null && inputVO.getAssignList().size() == 0){
					 alert.setAlertStatusId(1L);
				 }  
				 alert.setTitle(inputVO.getTitle());
				 alert = alertDAO.save(alert);
				 
				 UserAddress userAddress = alert.getUserAddress();
				 
				 if(inputVO.getLocationLevelId().longValue() == 2l)
					{
						userAddress.setState(stateDAO.get(inputVO.getStateId()));
						//swa
						userAddress.setDistrict(null);
						userAddress.setConstituency(null); 
						userAddress.setLocalElectionBody(null);	
						userAddress.setWard(null);
						userAddress.setTehsil(null);
						userAddress.setPanchayatId(null);
					}
					else if(inputVO.getLocationLevelId().longValue() == 3l)
					{
						userAddress.setState(stateDAO.get(inputVO.getStateId()));
						userAddress.setDistrict(districtDAO.get(inputVO.getDistrictId()));
						//swa
						userAddress.setConstituency(null); 
						userAddress.setLocalElectionBody(null);	
						userAddress.setWard(null);
						userAddress.setTehsil(null);
						userAddress.setPanchayatId(null);
					}
					
					else if(inputVO.getLocationLevelId().longValue() == 4l)
					{
						userAddress.setState(stateDAO.get(inputVO.getStateId()));
						userAddress.setDistrict(districtDAO.get(inputVO.getDistrictId()));
						userAddress.setConstituency(constituencyDAO.get(inputVO.getConstituencyId()));
						//swa
						userAddress.setLocalElectionBody(null);	
						userAddress.setWard(null);
						userAddress.setTehsil(null);
						userAddress.setPanchayatId(null);
					}
					else if(inputVO.getLocationLevelId().longValue() == 5l || inputVO.getLocationLevelId().longValue() == 7l)
					{
						userAddress.setState(stateDAO.get(inputVO.getStateId()));
						userAddress.setDistrict(districtDAO.get(inputVO.getDistrictId()));
						userAddress.setConstituency(constituencyDAO.get(inputVO.getConstituencyId()));
						if(inputVO.getLocationLevelId() ==  5l){
							userAddress.setTehsil(tehsilDAO.get(inputVO.getTehsilId()));
							//swa
							userAddress.setLocalElectionBody(null);	
						}	
						else{
							userAddress.setLocalElectionBody(localElectionBodyDAO.get(inputVO.getTehsilId()));
							//swa
							userAddress.setTehsil(null);
						}
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
							//swa
							userAddress.setLocalElectionBody(null);	
							userAddress.setWard(null);
						}
						else
						{
							userAddress.setLocalElectionBody(localElectionBodyDAO.get(inputVO.getTehsilId()));	
							userAddress.setWard(constituencyDAO.get(inputVO.getPanchayatId()));
							//swa
							userAddress.setTehsil(null);
							userAddress.setPanchayatId(null);
						}
					}
					
					userAddress = userAddressDAO.save(userAddress);  
				 
				 //delete the document list here//fileIdList//  ,122,125,145
				 if(inputVO.getFileIdList() != null && inputVO.getFileIdList().length() >1){
					 String[] fileIdArr = inputVO.getFileIdList().split(",");
					 for(String fId : fileIdArr){
						 if(fId != null && fId.length() > 0){
							 long fileId = Long.parseLong(fId);
							 int count = alertDocumentDAO.deleteDocument(new Long(fileId));
						 }
					 }
				 }
				 saveAlertDocument(alert.getAlertId(),userId,mapFiles);
				 List<Long> existingCadres = alertCandidateDAO.getTdpCadreIdsByAlertId(inputVO.getAlertId());
				 List<Long> existingCadreIds = new ArrayList<Long>();
				 if(existingCadres != null && existingCadres.size()>0){
					 for(Long param : existingCadres){
						 if(param != null){
							 existingCadreIds.add(param);
						 }
						 
					 }
					 
				 }
				
				 if(inputVO.getIdNamesList() != null && inputVO.getIdNamesList().size() > 0){
					Map<Long,Long> candidateAneImpactMap = new HashMap<Long,Long>();
					List<Long> newCandidateList = new ArrayList<Long>();
					List<Long> newList = new ArrayList<Long>();
					
					for(IdNameVO vo : inputVO.getIdNamesList()){
						if(vo != null && vo.getId() != null && vo.getId().longValue() > 0L){
							newCandidateList.add(vo.getId());    
							if(vo.getName() == null){
								candidateAneImpactMap.put(vo.getId(), 2L);
							}else{
								candidateAneImpactMap.put(vo.getId(), 1L);
							}
						}
						
					}
					if(existingCadreIds != null && existingCadreIds.size() > 0 && newCandidateList != null && newCandidateList.size() > 0){
						for(Long id : existingCadreIds){
							if(!(newCandidateList.contains(id))){
								int  result = alertCandidateDAO.deleteAlertCandidatesExistingtdpCadreIds(id,inputVO.getAlertId());
							}
						}
						for(Long id : newCandidateList){
							if(!(existingCadreIds.contains(id))){
								newList.add(id);
							}
						}
						if(newList != null && newList.size() > 0){
							for(Long id : newList){
								Long impactId = candidateAneImpactMap.get(id);
								AlertCandidate alertCandidate = new AlertCandidate();
								alertCandidate.setAlertId(inputVO.getAlertId());
								alertCandidate.setTdpCadreId(id);
								alertCandidate.setAlertImpactId(impactId);
								alertCandidateDAO.save(alertCandidate);
							}
						}
						
					}else if(newCandidateList != null && newCandidateList.size() > 0){
						for(IdNameVO vo : inputVO.getIdNamesList()){
							 if(vo != null && vo.getId()!= null && vo.getId() > 0L){
								 AlertCandidate alertCandidate = new AlertCandidate();
								 alertCandidate.setAlertId(inputVO.getAlertId());
								 alertCandidate.setTdpCadreId(vo.getId());
								 if(vo.getName() == null)
									 alertCandidate.setAlertImpactId(2l); 
								 else
									alertCandidate.setAlertImpactId(1l);
								 alertCandidateDAO.save(alertCandidate);
							 }
							 
						 }
					}
					
				 }else if(existingCadreIds != null && existingCadreIds.size() > 0){
						for(Long id : existingCadreIds){
							int  result = alertCandidateDAO.deleteAlertCandidatesExistingtdpCadreIds(id,inputVO.getAlertId());
						}  
					}  
				 existingCadreIds = alertAssignedDAO.getAssignedTdpCadreIdsByAlertId(inputVO.getAlertId());
				 if(inputVO.getAssignList() != null && inputVO.getAssignList().size() > 0){
					 List<Long> newCandidateList = new ArrayList<Long>();
						List<Long> newList = new ArrayList<Long>();
						
						for(IdNameVO vo : inputVO.getAssignList()){  
							if(vo != null && vo.getId()!= null && vo.getId() > 0L)
								newCandidateList.add(vo.getId());
						}
						
						
						if(existingCadreIds != null && existingCadreIds.size() > 0 && newCandidateList != null && newCandidateList.size() > 0){
							for(Long id : existingCadreIds){
								if(!(newCandidateList.contains(id))){
									int  result = alertAssignedDAO.deleteAlertAssignedByExistingIds(id,inputVO.getAlertId());
								}
							}
							for(Long id : newCandidateList){
								if(!(existingCadreIds.contains(id))){
									newList.add(id);
								}
							}
							if(newList != null && newList.size() > 0){
								for(Long id : newList){
									AlertAssigned alertAssigned = new AlertAssigned();
									alertAssigned.setAlertId(inputVO.getAlertId());
									alertAssigned.setTdpCadreId(id);
									alertAssigned.setCreatedBy(userId);
									alertAssigned.setInsertedTime(date.getCurrentDateAndTime());
									alertAssigned.setUpdatedTime(date.getCurrentDateAndTime());
									alertAssigned.setIsDeleted("N");
									alertAssigned.setSmsStatus("N");
									alertAssigned = alertAssignedDAO.save(alertAssigned);
									String description = " ";
									String mobilenumber =" ";
									boolean smsStatus = true;
									if(alertAssigned != null){
										Long assignedId = alertAssigned.getAlertAssignedId();
										List<Object[]> leaderDtls = alertAssignedDAO.getLeaderDtls(assignedId);
										if(leaderDtls != null && leaderDtls.size()>0){
											for(Object[] param :leaderDtls){
												description =commonMethodsUtilService.getStringValueForObject(param[1]);
												mobilenumber=commonMethodsUtilService.getStringValueForObject(param[2]);
											}
											
										}
										String message = commonMethodsUtilService.escapeUnicode("Alert is assigned to you,please follow up and resolve \n\nDescription:\n" +StringEscapeUtils.unescapeHtml(description));
										smsStatus =	smsSenderService.sendSmsForAssignedLeaderInTelugu(commonMethodsUtilService.getUniCodeMessage(StringEscapeUtils.unescapeJava(message)), false, mobilenumber);
										 if(smsStatus == true){
											 LOG.error(" Sms Status sending successfully ");
											 LOG.error(description);
											 LOG.error( mobilenumber );
											 alertAssignedDAO.updateAlertSmsStatus(assignedId);
										 }else if(smsStatus == false){
											 LOG.error(" Sms Status failed ");
											 LOG.error(description);
											 LOG.error(mobilenumber);
										 }
										
									}
								}
							}
							
						}else if(newCandidateList != null && newCandidateList.size() > 0){
							for(IdNameVO vo : inputVO.getAssignList()){
								 if(vo != null && vo.getId()!= null && vo.getId() > 0){
									 	AlertAssigned alertAssigned = new AlertAssigned();
										alertAssigned.setAlertId(inputVO.getAlertId());
										alertAssigned.setTdpCadreId(vo.getId());
										alertAssigned.setCreatedBy(userId);
										alertAssigned.setInsertedTime(date.getCurrentDateAndTime());
										alertAssigned.setUpdatedTime(date.getCurrentDateAndTime());
										alertAssigned.setIsDeleted("N");
										alertAssigned.setSmsStatus("N");
										alertAssigned = alertAssignedDAO.save(alertAssigned);
										String description = " ";
										String mobilenumber =" ";
										 boolean smsStatus = true;
										if(alertAssigned != null){
											Long assignedId = alertAssigned.getAlertAssignedId();
											List<Object[]> leaderDtls = alertAssignedDAO.getLeaderDtls(assignedId);
											if(leaderDtls != null && leaderDtls.size()>0){
												for(Object[] param :leaderDtls){
													description =commonMethodsUtilService.getStringValueForObject(param[1]);
													mobilenumber=commonMethodsUtilService.getStringValueForObject(param[2]);
												}
												
											}
											String message = commonMethodsUtilService.escapeUnicode("Alert is assigned to you,please follow up and resolve\n\nDescription:\n" +StringEscapeUtils.unescapeHtml(description));
											smsStatus =	smsSenderService.sendSmsForAssignedLeaderInTelugu(commonMethodsUtilService.getUniCodeMessage(StringEscapeUtils.unescapeJava(message)), false, mobilenumber);
											 if(smsStatus == true){
												 LOG.error(" Sms Status sending successfully ");
												 LOG.error( description );
												 LOG.error(mobilenumber);
												 alertAssignedDAO.updateAlertSmsStatus(assignedId);
											 }else if(smsStatus == false){
												 LOG.error(" Sms Status failed ");
												 LOG.error( description);
												 LOG.error(mobilenumber);
											 }
											
										}
										
								 }
								 
							 }
						}
				}else if(existingCadreIds != null && existingCadreIds.size() > 0){
					for(Long id : existingCadreIds){
						int  result = alertAssignedDAO.deleteAlertAssignedByExistingIds(id,inputVO.getAlertId());
					}
				}
				 
				 rs = "edit";
					}
					catch (Exception ex) {
						LOG.error("Error occured editAlert() method of AlertService{}",ex);
						 rs = "fail";
						
						return rs;
					}
						return rs;
				}

			});
	return resultStatus;
	}
	public String deleteAlert(Long alertId){
		try{
			int count = alertDAO.deleteAlert(alertId);
			if(count > 0){
				return "success";
			}else{
				return "failed";
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured deleteAlert() method of AlertService{}",e);
		}
		return null;
	}
	
	public AlertDataVO getMatchedCadreVO(List<AlertDataVO> voList,Long cadreId){
		if(voList != null && voList.size() > 0){
			for (AlertDataVO alertDataVO : voList) {
				if(alertDataVO.getId().equals(cadreId)){
					return alertDataVO;
				}
			}
		}
		return null;
	}
	public List<AlertVO> getAlertStatusByAlertTypeId(Long alertTypeId,Long alertId){
		List<AlertVO> statusList = new ArrayList<AlertVO>(0);
		try{
			if(alertTypeId == 0l){
				alertTypeId = alertDAO.getAlertTypeByAlertTypeId(alertId);
			}
			List<Object[]> rtrnObjLst = alertDepartmentStatusDAO.getAlertStatusByAlertType(alertTypeId);
			  if(rtrnObjLst != null && rtrnObjLst.size() > 0){
				  for(Object[] param:rtrnObjLst){
					  AlertVO statusVO = new AlertVO();
					  statusVO.setStatusId(commonMethodsUtilService.getLongValueForObject(param[0]));
					  statusVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[1]));
					  statusList.add(statusVO);
				  }
			  }
		}catch(Exception e){
			LOG.error("Error occured getAlertStatusByAlertTypeId() method of AlertService{}",e);	
		}
		return statusList;
		
	}
	public List<AlertOverviewVO> getDistrictListByStateId(Long stateId,Long activityMemberId,Long userTypeId,String fromDateStr,String toDateStr,Long alertTypeId,Long editionTypeId){
		List<AlertOverviewVO> resultList = new ArrayList<AlertOverviewVO>();
		Set<Long> locationValues = new HashSet<Long>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fromDate=null;
		Date toDate = null;
		Long locationAccessLevelId =0l;
		try{
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.length() > 0l && toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
				   fromDate = sdf.parse(fromDateStr);
				   toDate = sdf.parse(toDateStr);
			 }
			 List<Long> alertTypeList = new ArrayList<Long>();
		     List<Long> editionList = new ArrayList<Long>();
			     if(alertTypeId != null){
			    	 if(alertTypeId.longValue()!= 0L){
			    		 alertTypeList.add(alertTypeId);
			    	 }
			     }
			     if(editionTypeId != null){
			    	 if(editionTypeId.longValue() == 1L){
			    		 editionList.add(editionTypeId);
			    	 }else if(editionTypeId.longValue() == 2L){  
			    		 editionList.add(editionTypeId);
			    		 editionList.add(3L);
			    	 }
			     }
			 List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			 if(rtrnUsrAccssLvlIdAndVlusObjLst != null && rtrnUsrAccssLvlIdAndVlusObjLst.size() > 0){
				 locationAccessLevelId=(Long) rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0];
				 for(Object[] param:rtrnUsrAccssLvlIdAndVlusObjLst){
					 locationValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				 }
			 }
			     //convert parliament into constituency.
				if(locationAccessLevelId.longValue() == 4L){
					List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(new ArrayList<Long>(locationValues));
					locationAccessLevelId = 5L;
					locationValues.clear();
					locationValues.addAll(parliamentAssemlyIds);      
				}
			  List<Object[]> rtrnDistObjLst = alertDAO.getDistrictIdAndNameByUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate,alertTypeList,editionList);
			  setRequiredDataToList(rtrnDistObjLst,resultList); 
		}catch(Exception e){
			LOG.error("Error occured getDistrictListByStateId() method of AlertService{}",e);	
		}
		return resultList;
	}
	public List<AlertOverviewVO> getAlertStatus(Long alertTypeId){
		List<AlertOverviewVO> resultList = new ArrayList<AlertOverviewVO>();
		try{
			//List<Object[]> statusObjLst = alertStatusDAO.getAllStatus();
			List<Object[]> statusObjLst = alertDepartmentStatusDAO.getAlertStatusByAlertTypeId(alertTypeId);
			setRequiredDataToList(statusObjLst,resultList);
		}catch(Exception e){
			LOG.error("Error occured getAlertStatus() method of AlertService{}",e);		
		}
		return resultList;
	}
	public void setRequiredDataToList(List<Object[]> objList,List<AlertOverviewVO> resultLis){
		try{
			 if(objList != null && objList.size() > 0){
				 for(Object[] param:objList){
					 AlertOverviewVO districtVO = new AlertOverviewVO();
					  districtVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					  districtVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					  resultLis.add(districtVO);
				 }
			 }
		}catch(Exception e){
			LOG.error("Error occured setRequiredDataToList() method of AlertService{}",e);	
		}
   }
	/*
	 * Author :Santosh
	 * Date : 22-02-2017
	 * @Description : This service is used to get publication wise alert count;
	 */
	public List<AlertOverviewVO> getPublicationWiseAlert(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, Long alertStatusId,Long alertTypeId,Long editionId,String filterType,List<Long> districtIds){
		List<AlertOverviewVO> finalResultList = new ArrayList<AlertOverviewVO>(0);
		try{
			List<AlertOverviewVO> publicationList = new ArrayList<AlertOverviewVO>(0);
			List<Object[]> rtrnNewsPaperObjList = newsPaperDAO.getNewPaperList();
			List<Object[]> rtrnTvChannelObjLst = tvNewsChannelDAO.getChannelList();
			preparePublicationTemplate(rtrnTvChannelObjLst,publicationList,"TvChannel");
			preparePublicationTemplate(rtrnNewsPaperObjList,publicationList,"NewsPaper");
			List<AlertOverviewVO> resultList = getLocationLevelWisePublicationData(publicationList,fromDateStr,toDateStr,stateId,scopeIdList,activityMemberId,alertStatusId,alertTypeId,editionId,filterType,districtIds,"");
			    finalResultList.addAll(resultList);
		    if(filterType != null && filterType.equalsIgnoreCase("Constituency")){
		    	List<Long> scopeIds = new ArrayList<Long>();
				scopeIds.add(2l);
				scopeIds.add(8l);
				List<AlertOverviewVO> districtDataList = getLocationLevelWisePublicationData(publicationList,fromDateStr,toDateStr,stateId,scopeIds,activityMemberId,alertStatusId,alertTypeId,editionId,"District",districtIds,"District");
				setPublicationRsltToFnalList(districtDataList,finalResultList,"District/GMC CORP Impact Level");
			}
		    removePublicationHasNoData(finalResultList);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getPublicationWiseAlert() method of AlertService{}");	
		}
		return finalResultList;
	} 	
 	public void setPublicationRsltToFnalList(List<AlertOverviewVO> list,List<AlertOverviewVO> finalResultList,String level){
		try{
			if(list != null && list.size() > 0){
				AlertOverviewVO resutVO = new AlertOverviewVO();
				boolean flag = true;
				for(AlertOverviewVO locationVO:list){
					if(flag){
						resutVO.setId(0l);
						resutVO.setName(level);
						resutVO.setAlertCnt(0l);
						if(locationVO != null && locationVO.getSubList().size() > 0){
							for(AlertOverviewVO publiactionVO:locationVO.getSubList()){
								AlertOverviewVO VO = new AlertOverviewVO();
								VO.setPublicationId(publiactionVO.getPublicationId());
								VO.setPublicationName(publiactionVO.getPublicationName());
								resutVO.setLocationIdList(new ArrayList<Long>());
								resutVO.getSubList().add(VO);
								
							}
						}
					}
					resutVO.setAlertCnt(resutVO.getAlertCnt()+locationVO.getAlertCnt());
					if(locationVO.getId() != null && locationVO.getId().longValue() > 0l){
						resutVO.getLocationIdList().add(locationVO.getId());
					}
					if(resutVO.getSubList() != null && resutVO.getSubList().size() > 0){
						for(AlertOverviewVO publiationVO:resutVO.getSubList()){
							AlertOverviewVO publicationMatchVO = getPublicationMatchVO(publiationVO.getPublicationId(),locationVO.getSubList());
							 if(publicationMatchVO != null){
								 publiationVO.setAlertCnt(publiationVO.getAlertCnt()+publicationMatchVO.getAlertCnt());
							 }
						}
					}
					flag = false;
				}
				finalResultList.add(resutVO);
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setPublicationRsltToFnalList() method of AlertService{}");	
		}
	}
   public void removePublicationHasNoData(List<AlertOverviewVO> finalResultList){
	   try{
		   if(finalResultList != null && finalResultList.size() > 0){
			   List<String> publicationIdList = new ArrayList<String>();
			   for(AlertOverviewVO locationVO:finalResultList){
				   if(locationVO.getSubList() != null && locationVO.getSubList().size() > 0){
					   for(AlertOverviewVO publicationVO:locationVO.getSubList()){
						    if(publicationVO.getAlertCnt()>0l){
						    	publicationIdList.add(publicationVO.getPublicationId());//getting publication id which has no alert cnt
						    }
					   }
				   }
			   }
			   //remove publication which has no alert cnt
			   for(AlertOverviewVO locationVO:finalResultList){
				   if(locationVO.getSubList() != null && locationVO.getSubList().size() > 0){
					   for(AlertOverviewVO publicationVO:locationVO.getSubList()){
						    if(!publicationIdList.contains(publicationVO.getPublicationId())){
						    	locationVO.getSubList().remove(publicationVO);
						    }
					   }
				   }
			   }
		   }
	   }catch(Exception e){
		   e.printStackTrace();
			LOG.error("Error occured removePublicationHasNoData() method of AlertService{}");	   
	   }
   }
 public void preparePublicationTemplate(List<Object[]> objList,List<AlertOverviewVO> publicationList,String publicationType){
 		try{
 			if(objList != null && objList.size() > 0){
 				for(Object[] param:objList){
 					AlertOverviewVO publicationVO = new AlertOverviewVO();
 					String publicationIdStr = commonMethodsUtilService.getStringValueForObject(commonMethodsUtilService.getStringValueForObject(param[0]));
 					if(publicationType.equalsIgnoreCase("TvChannel")){
 						publicationIdStr = "1"+publicationIdStr;//We are appending 1 value for TvChannel and 2 for newspaper because their primary key value is same to identify purpose.
 					}else if(publicationType.equalsIgnoreCase("NewsPaper")){
 						publicationIdStr = "2"+publicationIdStr;
 					}
 					publicationVO.setPublicationId(publicationIdStr.trim());
 					publicationVO.setPublicationName(commonMethodsUtilService.getStringValueForObject(param[1]));
 					publicationList.add(publicationVO);
 				}
 			}
 		}catch(Exception e){
 			LOG.error("Error occured preparePublicationTemplate() method of AlertService{}");	
 		}
 	}
 	public List<AlertOverviewVO> getLocationLevelWisePublicationData(List<AlertOverviewVO> publicationList,String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, Long alertStatusId,Long alertTypeId,Long editionId,String filterType,List<Long> districtIds,String requiredLevel){
		LOG.info("Entered in getLocationLevelWisePublicationData() method of AlertService{}");
		List<AlertOverviewVO> resultList = new ArrayList<AlertOverviewVO>();
		Map<Long,AlertOverviewVO> locationWisAlertCntMap = new HashMap<Long, AlertOverviewVO>(0);
			try{  
			Date fromDate = null;        
			Date toDate = null; 
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Long> alertTypeList = new ArrayList<Long>();
			List<Long> editionList = new ArrayList<Long>();
			if(alertTypeId != null){
				if(alertTypeId.longValue() != 0L){
					alertTypeList.add(alertTypeId);
				}
			}
			if(editionId != null){
			   if(editionId.longValue() == 1L){
					editionList.add(editionId);
				}else if(editionId.longValue() == 2L){
					editionList.add(editionId);
					editionList.add(3L);
				}
			}
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
			List<Object[]> rtrnTvChannelArtcntObjLst = alertDAO.getPublicationWiseAlertCnt(fromDate, toDate, stateId, scopeIdList, "TvChannel", userAccessLevelId, userAccessLevelValues, alertTypeList, editionList, filterType, districtIds, requiredLevel,alertStatusId);
			List<Object[]> rtrnTvNwsPprArtcntObjLst = alertDAO.getPublicationWiseAlertCnt(fromDate, toDate, stateId, scopeIdList, "NewsPaper", userAccessLevelId, userAccessLevelValues, alertTypeList, editionList, filterType, districtIds, requiredLevel,alertStatusId);
			setPublicationWiseAlertCnt(rtrnTvChannelArtcntObjLst,locationWisAlertCntMap,publicationList,"TvChannel");
			setPublicationWiseAlertCnt(rtrnTvNwsPprArtcntObjLst,locationWisAlertCntMap,publicationList,"NewsPaper");
			
			//Calculation location wise overAll Alert Cnt.
			if(locationWisAlertCntMap != null && locationWisAlertCntMap.size() > 0){
				for(Entry<Long,AlertOverviewVO> locationEntry:locationWisAlertCntMap.entrySet()){
					if(locationEntry.getValue().getSubList() != null && locationEntry.getValue().getSubList().size()>0){
						for(AlertOverviewVO publicationVO:locationEntry.getValue().getSubList()){
							locationEntry.getValue().setAlertCnt(locationEntry.getValue().getAlertCnt()+publicationVO.getAlertCnt());
						}
					}
					
				}
				resultList.addAll(locationWisAlertCntMap.values());
				locationWisAlertCntMap.clear();
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getLocationLevelWisePublicationData() method of AlertService{}");
		}
		return resultList;
	}
 	public void setPublicationWiseAlertCnt(List<Object[]> objList,Map<Long,AlertOverviewVO> locationWisAlertCntMap,List<AlertOverviewVO> publicationList,String publicationType){
 		try{
 			if(objList != null && objList.size() > 0){
 				for(Object[] param:objList){
 					AlertOverviewVO locationVO = locationWisAlertCntMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
 					 if(locationVO == null){
 						locationVO = new AlertOverviewVO();
 						locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
 						locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
 						locationVO.getSubList().addAll(getPublicationList(publicationList));
 						locationWisAlertCntMap.put(locationVO.getId(), locationVO);
 					 }
 					 String publicationIdStr = commonMethodsUtilService.getStringValueForObject(param[2]);
 					 if(publicationType.equalsIgnoreCase("TvChannel")){
 						publicationIdStr = "1"+publicationIdStr;
 					 }else if(publicationType.equalsIgnoreCase("NewsPaper")){
 						publicationIdStr = "2"+publicationIdStr;
 					 }
 					 Long alertCnt = commonMethodsUtilService.getLongValueForObject(param[3]);
 					 AlertOverviewVO publicationMatchVO = getPublicationMatchVO(publicationIdStr,locationVO.getSubList());
 					 if(publicationMatchVO != null){
 						publicationMatchVO.setAlertCnt(alertCnt);
 					 }
 				}
 			}
 		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setPublicationWiseAlertCnt() method of AlertService{}");
 		}
 	}
 	public List<AlertOverviewVO> getPublicationList(List<AlertOverviewVO> publicationList){
 		List<AlertOverviewVO> rtrnPublctnLst = new ArrayList<AlertOverviewVO>(0);
 		try{
 			if(publicationList != null && publicationList.size() > 0){
 				for(AlertOverviewVO publicationVO:publicationList){
 					AlertOverviewVO VO = new AlertOverviewVO();
 					VO.setPublicationId(publicationVO.getPublicationId());
 					VO.setPublicationName(publicationVO.getPublicationName());
 					rtrnPublctnLst.add(VO);
 				}
 			}
 		}catch(Exception e){
 			e.printStackTrace();
			LOG.error("Error occured getPublicationList() method of AlertService{}");
 		}
 		return  rtrnPublctnLst;
 	}
 	public AlertOverviewVO getPublicationMatchVO(String publicationIdStr,List<AlertOverviewVO> publicationList){
 		try{
 			if(publicationList == null || publicationList.size() == 0)
 				return null;
 			for(AlertOverviewVO publicationVO:publicationList){
 				if(publicationVO.getPublicationId().equals(publicationIdStr.trim())){
 					return publicationVO;
 				}
 			}
		}catch(Exception e){
 			e.printStackTrace();
			LOG.error("Error occured getPublicationList() method of AlertService{}");	
 		}
 		return null;
 	}
}

