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
import java.util.GregorianCalendar;
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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IActionTypeDAO;
import com.itgrids.partyanalyst.dao.IActionTypeStatusDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IAlertAssignedDAO;
import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerDAO;
import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerNewDAO;
import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerTrackingDAO;
import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerTrackingNewDAO;
import com.itgrids.partyanalyst.dao.IAlertCallerDAO;
import com.itgrids.partyanalyst.dao.IAlertCallerRelationDAO;
import com.itgrids.partyanalyst.dao.IAlertCallerTypeDAO;
import com.itgrids.partyanalyst.dao.IAlertCandidateDAO;
import com.itgrids.partyanalyst.dao.IAlertCategoryDAO;
import com.itgrids.partyanalyst.dao.IAlertClarificationCommentsDAO;
import com.itgrids.partyanalyst.dao.IAlertClarificationDAO;
import com.itgrids.partyanalyst.dao.IAlertClarificationDocumentDAO;
import com.itgrids.partyanalyst.dao.IAlertClarificationStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertCommentAssigneeDAO;
import com.itgrids.partyanalyst.dao.IAlertCommentDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentCommentDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentCommentNewDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertDocumentDAO;
import com.itgrids.partyanalyst.dao.IAlertFeedbackStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertGovtOfficerSmsDetailsDAO;
import com.itgrids.partyanalyst.dao.IAlertImpactScopeDAO;
import com.itgrids.partyanalyst.dao.IAlertIssueSubTypeDAO;
import com.itgrids.partyanalyst.dao.IAlertIssueTypeDAO;
import com.itgrids.partyanalyst.dao.IAlertStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertTrackingDAO;
import com.itgrids.partyanalyst.dao.IAlertTrackingDocumentsDAO;
import com.itgrids.partyanalyst.dao.IAlertTypeDAO;
import com.itgrids.partyanalyst.dao.IAlertUserDAO;
import com.itgrids.partyanalyst.dao.IAlertVerificationUserTypeUserDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.IClarificationRequiredDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEditionTypeDAO;
import com.itgrids.partyanalyst.dao.IGovtAlertDepartmentLocationNewDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationHierarchyDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationNewDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDetailsDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDetailsNewDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentIssueTypeDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentWorkLocationDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentWorkLocationRelationDAO;
import com.itgrids.partyanalyst.dao.IGovtOfficerDAO;
import com.itgrids.partyanalyst.dao.IGovtOfficerNewDAO;
import com.itgrids.partyanalyst.dao.IGovtProposalPropertyCategoryDAO;
import com.itgrids.partyanalyst.dao.IGovtSmsActionTypeDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ILocalityDAO;
import com.itgrids.partyanalyst.dao.IMemberTypeDAO;
import com.itgrids.partyanalyst.dao.INewsPaperDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.dao.IParliamentAssemblyDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreLoginDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeEnrollmentDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITvNewsChannelDAO;
import com.itgrids.partyanalyst.dao.IUrbanBlockDAO;
import com.itgrids.partyanalyst.dao.IUrbanLocalityDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserDistrictAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserLoginDetailsDAO;
import com.itgrids.partyanalyst.dao.IVerificationCommentsDAO;
import com.itgrids.partyanalyst.dao.IVerificationConversationDAO;
import com.itgrids.partyanalyst.dao.IVerificationDocumentsDAO;
import com.itgrids.partyanalyst.dao.IVerificationStatusDAO;
import com.itgrids.partyanalyst.dao.impl.IAlertSourceUserDAO;
import com.itgrids.partyanalyst.dto.ActionTypeStatusVO;
import com.itgrids.partyanalyst.dto.ActionableVO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.AlertClarificationVO;
import com.itgrids.partyanalyst.dto.AlertCommentVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertDataVO;
import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.dto.AlertInputsVO;
import com.itgrids.partyanalyst.dto.AlertOverviewVO;
import com.itgrids.partyanalyst.dto.AlertTrackingVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.AlertVerificationVO;
import com.itgrids.partyanalyst.dto.AlertsSummeryVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CallCenterVO;
import com.itgrids.partyanalyst.dto.ClarificationDetailsCountVO;
import com.itgrids.partyanalyst.dto.GovtDepartmentVO;
import com.itgrids.partyanalyst.dto.GovtOfficerSmsDetailsVO;
import com.itgrids.partyanalyst.dto.GrievanceAlertVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.StatusTrackingVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertAssigned;
import com.itgrids.partyanalyst.model.AlertAssignedOfficer;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerNew;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerTracking;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerTrackingNew;
import com.itgrids.partyanalyst.model.AlertCaller;
import com.itgrids.partyanalyst.model.AlertCallerRelation;
import com.itgrids.partyanalyst.model.AlertCandidate;
import com.itgrids.partyanalyst.model.AlertCategory;
import com.itgrids.partyanalyst.model.AlertClarification;
import com.itgrids.partyanalyst.model.AlertClarificationComments;
import com.itgrids.partyanalyst.model.AlertClarificationStatus;
import com.itgrids.partyanalyst.model.AlertComment;
import com.itgrids.partyanalyst.model.AlertCommentAssignee;
import com.itgrids.partyanalyst.model.AlertDepartmentCommentNew;
import com.itgrids.partyanalyst.model.AlertDocument;
import com.itgrids.partyanalyst.model.AlertFeedbackStatus;
import com.itgrids.partyanalyst.model.AlertGovtOfficerSmsDetails;
import com.itgrids.partyanalyst.model.AlertIssueType;
import com.itgrids.partyanalyst.model.AlertStatus;
import com.itgrids.partyanalyst.model.AlertTracking;
import com.itgrids.partyanalyst.model.AlertTrackingDocuments;
import com.itgrids.partyanalyst.model.ClarificationRequired;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.GovtDepartment;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignation;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationNew;
import com.itgrids.partyanalyst.model.GovtDepartmentWorkLocation;
import com.itgrids.partyanalyst.model.GovtOfficer;
import com.itgrids.partyanalyst.model.GovtOfficerNew;
import com.itgrids.partyanalyst.model.MemberType;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.model.VerificationComments;
import com.itgrids.partyanalyst.model.VerificationConversation;
import com.itgrids.partyanalyst.model.VerificationDocuments;
import com.itgrids.partyanalyst.model.VerificationStatus;
import com.itgrids.partyanalyst.service.IAlertManagementSystemService;
import com.itgrids.partyanalyst.service.IAlertService;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
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
private ICoreDashboardGenericService coreDashboardGenericService;

private IAlertIssueTypeDAO alertIssueTypeDAO;
private IPanchayatHamletDAO panchayatHamletDAO;
private IAlertCallerTypeDAO alertCallerTypeDAO;
private IAlertCallerDAO alertCallerDAO;
private IHamletDAO hamletDAO;
private IGovtDepartmentDesignationOfficerDetailsNewDAO govtDepartmentDesignationOfficerDetailsNewDAO;
private IAlertAssignedOfficerNewDAO alertAssignedOfficerNewDAO;
private IAlertAssignedOfficerTrackingNewDAO alertAssignedOfficerTrackingNewDAO;
private IGovtOfficerNewDAO govtOfficerNewDAO;
private IGovtDepartmentDesignationNewDAO govtDepartmentDesignationNewDAO;
private IGovtDepartmentDesignationHierarchyDAO govtDepartmentDesignationHierarchyDAO;
private IGovtDepartmentDesignationOfficerDetailsDAO govtDepartmentDesignationOfficerDetailsDAO;
private ILocalityDAO localityDAO;
private IUserLoginDetailsDAO userLoginDetailsDAO;
private IUserDAO userDAO;  
private IBoothDAO boothDAO;

private IAlertFeedbackStatusDAO alertFeedbackStatusDAO;
private IAlertAssignedOfficerDAO alertAssignedOfficerDAO;
private IAlertAssignedOfficerTrackingDAO alertAssignedOfficerTrackingDAO;
private IGovtOfficerDAO govtOfficerDAO;
private IGovtDepartmentDesignationDAO govtDepartmentDesignationDAO;
private IAlertIssueSubTypeDAO alertIssueSubTypeDAO;
private IAlertManagementSystemService alertManagementSystemService;
private IGovtDepartmentIssueTypeDAO govtDepartmentIssueTypeDAO;
private IUrbanLocalityDAO urbanLocalityDAO;
private IUrbanBlockDAO urbanBlockDAO;

private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
private ITdpCadreLoginDetailsDAO  tdpCadreLoginDetailsDAO;
private IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO;
private IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO;
private IAlertGovtOfficerSmsDetailsDAO alertGovtOfficerSmsDetailsDAO;
private IAlertCallerRelationDAO alertCallerRelationDAO;
private IGovtDepartmentWorkLocationDAO govtDepartmentWorkLocationDAO;
private IGovtDepartmentWorkLocationRelationDAO govtDepartmentWorkLocationRelationDAO;
private IGovtSmsActionTypeDAO govtSmsActionTypeDAO;
private IGovtAlertDepartmentLocationNewDAO govtAlertDepartmentLocationNewDAO;
private IGovtProposalPropertyCategoryDAO govtProposalPropertyCategoryDAO;
private ITdpCommitteeEnrollmentDAO tdpCommitteeEnrollmentDAO;

private SmsCountrySmsService smsCountrySmsService;


public void setSmsCountrySmsService(SmsCountrySmsService smsCountrySmsService) {
	this.smsCountrySmsService = smsCountrySmsService;
}

public IGovtAlertDepartmentLocationNewDAO getGovtAlertDepartmentLocationNewDAO() {
	return govtAlertDepartmentLocationNewDAO;
}

public void setGovtAlertDepartmentLocationNewDAO(
		IGovtAlertDepartmentLocationNewDAO govtAlertDepartmentLocationNewDAO) {
	this.govtAlertDepartmentLocationNewDAO = govtAlertDepartmentLocationNewDAO;
}

public IGovtSmsActionTypeDAO getGovtSmsActionTypeDAO() {
	return govtSmsActionTypeDAO;
}

public void setGovtSmsActionTypeDAO(IGovtSmsActionTypeDAO govtSmsActionTypeDAO) {
	this.govtSmsActionTypeDAO = govtSmsActionTypeDAO;
}

public IGovtDepartmentWorkLocationRelationDAO getGovtDepartmentWorkLocationRelationDAO() {
	return govtDepartmentWorkLocationRelationDAO;
}

public void setGovtDepartmentWorkLocationRelationDAO(
		IGovtDepartmentWorkLocationRelationDAO govtDepartmentWorkLocationRelationDAO) {
	this.govtDepartmentWorkLocationRelationDAO = govtDepartmentWorkLocationRelationDAO;
}

public IGovtDepartmentWorkLocationDAO getGovtDepartmentWorkLocationDAO() {
	return govtDepartmentWorkLocationDAO;
}

public void setGovtDepartmentWorkLocationDAO(
		IGovtDepartmentWorkLocationDAO govtDepartmentWorkLocationDAO) {
	this.govtDepartmentWorkLocationDAO = govtDepartmentWorkLocationDAO;
}

private IAlertDepartmentCommentDAO alertDepartmentCommentDAO;  
private IAlertDepartmentCommentNewDAO alertDepartmentCommentNewDAO;
public IAlertCallerRelationDAO getAlertCallerRelationDAO() {
	return alertCallerRelationDAO;
}

public void setAlertCallerRelationDAO(
		IAlertCallerRelationDAO alertCallerRelationDAO) {
	this.alertCallerRelationDAO = alertCallerRelationDAO;
}

public IAlertGovtOfficerSmsDetailsDAO getAlertGovtOfficerSmsDetailsDAO() {
	return alertGovtOfficerSmsDetailsDAO;
}

public void setAlertGovtOfficerSmsDetailsDAO(
		IAlertGovtOfficerSmsDetailsDAO alertGovtOfficerSmsDetailsDAO) {
	this.alertGovtOfficerSmsDetailsDAO = alertGovtOfficerSmsDetailsDAO;
}

public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
	return assemblyLocalElectionBodyDAO;
}

public void setAssemblyLocalElectionBodyDAO(
		IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
	this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
}

public IUrbanBlockDAO getUrbanBlockDAO() {
	return urbanBlockDAO;
}
private IRegionScopesDAO regionScopesDAO;

public void setUrbanBlockDAO(IUrbanBlockDAO urbanBlockDAO) {
	this.urbanBlockDAO = urbanBlockDAO;
}

public IUrbanLocalityDAO getUrbanLocalityDAO() {
	return urbanLocalityDAO;
}

public void setUrbanLocalityDAO(IUrbanLocalityDAO urbanLocalityDAO) {
	this.urbanLocalityDAO = urbanLocalityDAO;
}


public IGovtDepartmentIssueTypeDAO getGovtDepartmentIssueTypeDAO() {
	return govtDepartmentIssueTypeDAO;
}

public void setGovtDepartmentIssueTypeDAO(
		IGovtDepartmentIssueTypeDAO govtDepartmentIssueTypeDAO) {
	this.govtDepartmentIssueTypeDAO = govtDepartmentIssueTypeDAO;
}

public IAlertManagementSystemService getAlertManagementSystemService() {
	return alertManagementSystemService;
}

public void setAlertManagementSystemService(
		IAlertManagementSystemService alertManagementSystemService) {
	this.alertManagementSystemService = alertManagementSystemService;
}

public IBoothDAO getBoothDAO() {
	return boothDAO;
}

public void setBoothDAO(IBoothDAO boothDAO) {
	this.boothDAO = boothDAO;
}

public void setUserDAO(IUserDAO userDAO) {
	this.userDAO = userDAO;
}

public void setUserLoginDetailsDAO(IUserLoginDetailsDAO userLoginDetailsDAO) {
	this.userLoginDetailsDAO = userLoginDetailsDAO;
}
public IAlertIssueSubTypeDAO getAlertIssueSubTypeDAO() {
	return alertIssueSubTypeDAO;
}

public void setAlertIssueSubTypeDAO(IAlertIssueSubTypeDAO alertIssueSubTypeDAO) {
	this.alertIssueSubTypeDAO = alertIssueSubTypeDAO;
}

public IGovtDepartmentDesignationDAO getGovtDepartmentDesignationDAO() {
	return govtDepartmentDesignationDAO;
}

public void setGovtDepartmentDesignationDAO(
		IGovtDepartmentDesignationDAO govtDepartmentDesignationDAO) {
	this.govtDepartmentDesignationDAO = govtDepartmentDesignationDAO;
}

public IGovtOfficerDAO getGovtOfficerDAO() {
	return govtOfficerDAO;
}

public void setGovtOfficerDAO(IGovtOfficerDAO govtOfficerDAO) {
	this.govtOfficerDAO = govtOfficerDAO;
}

public IAlertAssignedOfficerTrackingDAO getAlertAssignedOfficerTrackingDAO() {
	return alertAssignedOfficerTrackingDAO;
}

public void setAlertAssignedOfficerTrackingDAO(
		IAlertAssignedOfficerTrackingDAO alertAssignedOfficerTrackingDAO) {
	this.alertAssignedOfficerTrackingDAO = alertAssignedOfficerTrackingDAO;
}

public IAlertAssignedOfficerDAO getAlertAssignedOfficerDAO() {
	return alertAssignedOfficerDAO;
}

public void setAlertAssignedOfficerDAO(
		IAlertAssignedOfficerDAO alertAssignedOfficerDAO) {
	this.alertAssignedOfficerDAO = alertAssignedOfficerDAO;
}

public ILocalityDAO getLocalityDAO() {
	return localityDAO;
}

public void setLocalityDAO(ILocalityDAO localityDAO) {
	this.localityDAO = localityDAO;
}

public IGovtDepartmentDesignationOfficerDetailsDAO getGovtDepartmentDesignationOfficerDetailsDAO() {
	return govtDepartmentDesignationOfficerDetailsDAO;
}

public void setGovtDepartmentDesignationOfficerDetailsDAO(
		IGovtDepartmentDesignationOfficerDetailsDAO govtDepartmentDesignationOfficerDetailsDAO) {
	this.govtDepartmentDesignationOfficerDetailsDAO = govtDepartmentDesignationOfficerDetailsDAO;
}

public IGovtDepartmentDesignationHierarchyDAO getGovtDepartmentDesignationHierarchyDAO() {
	return govtDepartmentDesignationHierarchyDAO;
}

public void setGovtDepartmentDesignationHierarchyDAO(
		IGovtDepartmentDesignationHierarchyDAO govtDepartmentDesignationHierarchyDAO) {
	this.govtDepartmentDesignationHierarchyDAO = govtDepartmentDesignationHierarchyDAO;
}

public IGovtDepartmentDesignationNewDAO getGovtDepartmentDesignationNewDAO() {
	return govtDepartmentDesignationNewDAO;
}

public void setGovtDepartmentDesignationNewDAO(
		IGovtDepartmentDesignationNewDAO govtDepartmentDesignationNewDAO) {
	this.govtDepartmentDesignationNewDAO = govtDepartmentDesignationNewDAO;
}

public IGovtOfficerNewDAO getGovtOfficerNewDAO() {
	return govtOfficerNewDAO;
}

public void setGovtOfficerNewDAO(IGovtOfficerNewDAO govtOfficerNewDAO) {
	this.govtOfficerNewDAO = govtOfficerNewDAO;
}

public IGovtDepartmentDesignationOfficerDetailsNewDAO getGovtDepartmentDesignationOfficerDetailsNewDAO() {
	return govtDepartmentDesignationOfficerDetailsNewDAO;
}

public void setGovtDepartmentDesignationOfficerDetailsNewDAO(
		IGovtDepartmentDesignationOfficerDetailsNewDAO govtDepartmentDesignationOfficerDetailsNewDAO) {
	this.govtDepartmentDesignationOfficerDetailsNewDAO = govtDepartmentDesignationOfficerDetailsNewDAO;
}

public IAlertAssignedOfficerNewDAO getAlertAssignedOfficerNewDAO() {
	return alertAssignedOfficerNewDAO;
}

public void setAlertAssignedOfficerNewDAO(
		IAlertAssignedOfficerNewDAO alertAssignedOfficerNewDAO) {
	this.alertAssignedOfficerNewDAO = alertAssignedOfficerNewDAO;
}

public IAlertAssignedOfficerTrackingNewDAO getAlertAssignedOfficerTrackingNewDAO() {
	return alertAssignedOfficerTrackingNewDAO;
}

public void setAlertAssignedOfficerTrackingNewDAO(
		IAlertAssignedOfficerTrackingNewDAO alertAssignedOfficerTrackingNewDAO) {
	this.alertAssignedOfficerTrackingNewDAO = alertAssignedOfficerTrackingNewDAO;
}

public IHamletDAO getHamletDAO() {
	return hamletDAO;
}

public void setHamletDAO(IHamletDAO hamletDAO) {
	this.hamletDAO = hamletDAO;
}

public IAlertCallerDAO getAlertCallerDAO() {
	return alertCallerDAO;
}

public void setAlertCallerDAO(IAlertCallerDAO alertCallerDAO) {
	this.alertCallerDAO = alertCallerDAO;
}

public IAlertCallerTypeDAO getAlertCallerTypeDAO() {
	return alertCallerTypeDAO;
}

public void setAlertCallerTypeDAO(IAlertCallerTypeDAO alertCallerTypeDAO) {
	this.alertCallerTypeDAO = alertCallerTypeDAO;
}

public IPanchayatHamletDAO getPanchayatHamletDAO() {
	return panchayatHamletDAO;
}

public void setPanchayatHamletDAO(IPanchayatHamletDAO panchayatHamletDAO) {
	this.panchayatHamletDAO = panchayatHamletDAO;
}

public void setDelimitationConstituencyDAO(
		IDelimitationConstituencyDAO delimitationConstituencyDAO) {  
	this.delimitationConstituencyDAO = delimitationConstituencyDAO;
}

public IAlertIssueTypeDAO getAlertIssueTypeDAO() {
	return alertIssueTypeDAO;
}

public void setAlertIssueTypeDAO(IAlertIssueTypeDAO alertIssueTypeDAO) {
	this.alertIssueTypeDAO = alertIssueTypeDAO;
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
public void setCoreDashboardGenericService(
		ICoreDashboardGenericService coreDashboardGenericService) {
	this.coreDashboardGenericService = coreDashboardGenericService;
}
public IAlertFeedbackStatusDAO getAlertFeedbackStatusDAO() {
	return alertFeedbackStatusDAO;
}
public void setAlertFeedbackStatusDAO(IAlertFeedbackStatusDAO alertFeedbackStatusDAO) {
	this.alertFeedbackStatusDAO = alertFeedbackStatusDAO;
}
public ITdpCadreLoginDetailsDAO getTdpCadreLoginDetailsDAO() {
	return tdpCadreLoginDetailsDAO;
}
public void setTdpCadreLoginDetailsDAO(ITdpCadreLoginDetailsDAO tdpCadreLoginDetailsDAO) {
	this.tdpCadreLoginDetailsDAO = tdpCadreLoginDetailsDAO;
}
public IUserDistrictAccessInfoDAO getUserDistrictAccessInfoDAO() {
	return userDistrictAccessInfoDAO;
}
public void setUserDistrictAccessInfoDAO(IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO) {
	this.userDistrictAccessInfoDAO = userDistrictAccessInfoDAO;
}
public IUserConstituencyAccessInfoDAO getUserConstituencyAccessInfoDAO() {
	return userConstituencyAccessInfoDAO;
}
public void setUserConstituencyAccessInfoDAO(IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO) {
	this.userConstituencyAccessInfoDAO = userConstituencyAccessInfoDAO;
}
public IRegionScopesDAO getRegionScopesDAO() {
	return regionScopesDAO;
}
public void setRegionScopesDAO(IRegionScopesDAO regionScopesDAO) {
	this.regionScopesDAO = regionScopesDAO;
}
public IAlertDepartmentCommentDAO getAlertDepartmentCommentDAO() {
	return alertDepartmentCommentDAO;
}

public void setAlertDepartmentCommentDAO(
		IAlertDepartmentCommentDAO alertDepartmentCommentDAO) {
	this.alertDepartmentCommentDAO = alertDepartmentCommentDAO;
}

public IAlertDepartmentCommentNewDAO getAlertDepartmentCommentNewDAO() {
	return alertDepartmentCommentNewDAO;
}

public void setAlertDepartmentCommentNewDAO(
		IAlertDepartmentCommentNewDAO alertDepartmentCommentNewDAO) {
	this.alertDepartmentCommentNewDAO = alertDepartmentCommentNewDAO;
}

public IGovtProposalPropertyCategoryDAO getGovtProposalPropertyCategoryDAO() {
	return govtProposalPropertyCategoryDAO;
}

public void setGovtProposalPropertyCategoryDAO(IGovtProposalPropertyCategoryDAO govtProposalPropertyCategoryDAO) {
	this.govtProposalPropertyCategoryDAO = govtProposalPropertyCategoryDAO;
}

public void setTdpCommitteeEnrollmentDAO(
		ITdpCommitteeEnrollmentDAO tdpCommitteeEnrollmentDAO) {
	this.tdpCommitteeEnrollmentDAO = tdpCommitteeEnrollmentDAO;
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
			 List<String> dueDateList = alertAssignedOfficerTrackingNewDAO.getAlertDueDate(alertId); 
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
					 alertVO.setSeverityId(params[33] != null ? (Long)params[33] : null);
					 alertVO.setRegionScopeId(params[6] != null ? (Long)params[6] : null);
					 alertVO.setRegionScope(params[26] != null ?params[26].toString() : "");
					 alertVO.setStatusId(params[8] != null ? (Long)params[8] : null);
					 alertVO.setStatus(params[9] != null ?params[9].toString() : "");
					 if(alertVO.getStatusId() == 13l){
						 List<Object[]> statusList =govtProposalPropertyCategoryDAO.getStatusFrALert(alertId);
						 if(commonMethodsUtilService.isListOrSetValid(statusList)){
							 for (Object[] objects : statusList) {
								alertVO.setCommitteeName(commonMethodsUtilService.getStringValueForObject(objects[1]));
							}
						 }
					 }
					 if(alertVO.getStatusId() == 10l){
						 List<String> rejinderStatusList = alertAssignedOfficerTrackingNewDAO.getRejoinderStatusForAlert(alertId);
						 if(commonMethodsUtilService.isListOrSetValid(rejinderStatusList)){
							 String status = rejinderStatusList.get(0);
							 	alertVO.setComment(commonMethodsUtilService.getStringValueForObject(status));
						 }	 
						 List<AlertVO> rejoindcumntList = alertManagementSystemService.getRejoinderDocumentsForAlert(alertId);
						 if(commonMethodsUtilService.isListOrSetValid(rejoindcumntList)){
							 alertVO.setRejinderDocList(rejoindcumntList);
						 }
					 }
					 alertVO.setStatusColor(params[32] != null ?params[32].toString() : "");
					 if(dueDateList != null && dueDateList.size() > 0){
						 Date date =new SimpleDateFormat("yyyy-MM-dd").parse(dueDateList.get(dueDateList.size()-1).toString());
						 String dateStr = new SimpleDateFormat("dd/MM/yyyy").format(date);
						 alertVO.setDueDate(dateStr);
					 }
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
					 
					 locationVO.setHamletId(params[34] != null ? (Long)params[34] : null);
					 locationVO.setHamletName(params[35] != null ? params[35].toString() : "");
					 
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
			 
			 if(commonMethodsUtilService.isListOrSetValid(returnList)){
				 AlertDataVO alertVO =  returnList.get(0);
				 if(alertVO != null){
					 List<AlertTrackingVO> subTasksList =  alertManagementSystemService.getSubTaskDetails(alertVO.getId());
					 if(commonMethodsUtilService.isListOrSetValid(subTasksList))
						 alertVO.setSubList1(subTasksList);
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
			List<Long> consIds = new ArrayList<Long>(0);
			List<Object[]> list = null;
			if(inputVO.getSearchType() != null && inputVO.getSearchType().trim().equalsIgnoreCase("areaAlerts")){
				List<Long>  userIds = tdpCadreLoginDetailsDAO.getuserIdsForCadre(assignedCadreId);
				List<Long> districtIds = userDistrictAccessInfoDAO.getDistrictIdsForUser(userIds);
				if(districtIds != null && districtIds.size() > 0l){
					List<Object[]> constList = constituencyDAO.getConstituenciesByDistrict(districtIds);
					if(constList != null && constList.size() > 0l){
						for (Object[] objects : constList) {
							Long elctionTypeId = commonMethodsUtilService.getLongValueForObject(objects[1]);
							if(elctionTypeId == 1){
								
							}else{
								consIds.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
							}
						}
					}
				}else{
					consIds = userConstituencyAccessInfoDAO.getConstituenciesByUser(userIds);
				}
			}
			
			if(inputVO.getSearchType() != null && inputVO.getSearchType().trim().equalsIgnoreCase("areaAlerts")){
				 list = alertDAO.getLocationWiseFilterAlertData(userTypeIds,fromDate,toDate,inputVO,null,fromDate2,toDate2,null,impactId,consIds);//done
			}else{
				 list = alertDAO.getLocationWiseFilterAlertData(userTypeIds,fromDate,toDate,inputVO,assignedCadreId,fromDate2,toDate2,involvedCandidateId,impactId,null);//done
			}
			
			 
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
						alertCommentVO.setUserId(commonMethodsUtilService.getLongValueForObject(param[12]));
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
						alertCommentVO.setUserId(commonMethodsUtilService.getLongValueForObject(param[12]));
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
									//List<Alert> alertList = alertDAO.getAlertDetailsOfNewstype(inputVO.getId(),inputVO.getAlertType());
									List<Alert> alertList = alertDAO.getAlertDetailsOfNewstypeNew(inputVO.getId());	
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
							 List<Alert> alertList  = alertDAO.getAlertDetailsOfNewstype(inputVO.getId(),inputVO.getAlertType());
							 if(alertList !=null && alertList.size()>0){
								 alert = alertList.get(0);
							 }else{
								 if(inputVO.getInsertedTime() != null)
									 alert.setCreatedTime(inputVO.getInsertedTime());
							 }
							 
							// if(inputVO.getInsertedTime() != null)
							//	 alert.setCreatedTime(inputVO.getInsertedTime());
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
						 List<Alert> alertList  = alertDAO.getAlertDetailsOfNewstype(inputVO.getId(),inputVO.getAlertType());
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
			String alertType=null;
			if(VO.getId() !=null && VO.getId().longValue()>0){
				//Date categoryCreatedDateObj  
				List<Object[]> objList = alertDAO.getAlertCreatedDate(VO.getId());					
				Date categoryCreatedDateObj=null;
				if(objList !=null && objList.size()>0){
					
					alertId = objList.get(0)[0] !=null ?  (Long)objList.get(0)[0]:null;
					categoryCreatedDateObj= objList.get(0)[1] !=null ?  (Date)objList.get(0)[1]:null;//Alert Created Date Converting into Date From Object
					alertType= objList.get(0)[2] !=null ?  objList.get(0)[2].toString():null;
				}
				
				//Alert Date Converting into String Format from Date
				if(categoryCreatedDateObj !=null){
					categoryCreatedDate =sdf.format(categoryCreatedDateObj);
				}			
			}
			
			
			//Converting  
			Date fromDate = sdf.parse(categoryCreatedDate);
			Calendar now =  Calendar.getInstance();
			now.setTime(fromDate);
			now.add(Calendar.DATE,6);
			String toDateMonth = (now.get(Calendar.MONTH) + 1)>9?""+(now.get(Calendar.MONTH)+1):"0"+(now.get(Calendar.MONTH)+1);
			String toDate  = now.get(Calendar.YEAR)+"-"+toDateMonth + "-"+ now.get(Calendar.DATE);
		
			List<String> dates = new ArrayList<String>(10);
			
			dates.add(categoryCreatedDate);
		    Calendar cal = Calendar.getInstance();
		    cal.setTime(sdf.parse(categoryCreatedDate));
		    while (cal.getTime().before(sdf.parse(toDate))) {
		        cal.add(Calendar.DATE, 1);
		        dates.add(sdf.format(cal.getTime()).trim());		        
		    }
		    
			int updateAlert=0;
			
			if(alertType !=null && alertType.trim().equalsIgnoreCase("Party")){
				if(dates !=null && dates.size()>0 && dates.contains(currentDate)){							
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
			}
			
			if(updateAlert > 0){
				result ="success";
			}else{
				result ="Govt Alert";
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
	 * Swadhin1
	 * @see com.itgrids.partyanalyst.service.IAlertService#getTotalAlertGroupByLocationThenStatus(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String, java.lang.Long, java.util.List, java.lang.String, java.lang.Long)
	 */
	public List<AlertOverviewVO> getTotalAlertGroupByLocationThenStatus(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, String group,Long alertTypeId,Long editionId,String filterType,Long locationValue,List<Long> alertStatusIds,String sortingType,Long disctrictId){
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
				if(alertTypeId.longValue() != 0L){
					alertTypeList.add(alertTypeId);
			}
			
			if(editionId != null){
			    if(editionId.longValue() == 1L){
					editionList.add(editionId);
				}else if(editionId.longValue() == 2L){
					editionList.add(editionId);
					editionList.add(3L);
				}
			}
			AlertOverviewVO alertVO = null;    
			List<AlertOverviewVO> alertVOs = null;//new ArrayList<AlertVO>();
			Map<Long,Long> locationIdAndCountMap = new HashMap<Long,Long>();
			//get all the alert status for  building the template
			List<Object[]> statusList = alertDepartmentStatusDAO.getAlertStatusByAlertStatusId(alertStatusIds,alertTypeId);
	
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
			//String filterType,Long locationValue
			String blockType="";
			List<Long> locationValues = null;
			if(filterType != null && filterType.trim().equalsIgnoreCase("Parliament") && locationValue != null && locationValue.longValue() > 0){
				if(locationValue != null && locationValue.longValue() > 0L){
					locationValues = parliamentAssemblyDAO.getConstituencyIdsByParliamntId(locationValue);
					locationValue = null;
				}
				blockType = filterType;
			}
			if(filterType != null && filterType.trim().equalsIgnoreCase("Parliament")){
				blockType = filterType;
				filterType = "Constituency";
			}
			//get alert status count and and create a map of LocationId and its corresponding  alert count
			List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByLocationThenStatus(fromDate, toDate, stateId, scopeIdList, "One", userAccessLevelId, userAccessLevelValues,alertTypeList,editionList,filterType,locationValue,disctrictId,alertStatusIds,locationValues);
			if(userAccessLevelId == 5L && blockType != null && blockType.trim().equalsIgnoreCase("Parliament") && locationValues != null && locationValues.size()> 0L){
				filteredConstituencyList(alertCountList,locationValues);
		          
		      }
			
			List<Object[]> parliamentList=null;
			if(blockType != null && blockType.trim().equalsIgnoreCase("Parliament")){
				List<Long> constituencyIds = new ArrayList<Long>();
				if(alertCountList != null && alertCountList.size() > 0){
					for(Object[] param : alertCountList){
						if(param[0] != null){
							constituencyIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
						}
					}
					parliamentList = parliamentAssemblyDAO.getParliamntIdByConsIds(constituencyIds);
				}
			}
			
			
			if(blockType != null && blockType.trim().equalsIgnoreCase("Parliament")){
				convertConstituencyDateIntoParliamentDateForToralAlert(alertCountList,parliamentList);
			}
			
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					if(param[0] != null)//{207=1, 203=1, 277=1, 282=1, 286=1, 285=1, 327=1, 155=1, 257=1, 157=1, 218=3, 334=1, 146=1, 215=1, 149=2, 305=1, 238=1, 306=1, 232=1, 163=1, 352=3, 353=1, 294=1, 178=1, 179=1, 298=2}
						locationIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}  
			//get all the alert count group by status then category.
			Map<Long,String> locationIdAndNameMap = new HashMap<Long,String>();
			Map<Long,Long> statusIdAndCountMap = null;//new HashMap<Long, Long>();  
			Map<Long,Map<Long,Long>> locationIdAndStatusIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
			List<Object[]> alertCountGrpByLocList = alertDAO.getTotalAlertGroupByLocationThenStatus(fromDate, toDate, stateId, scopeIdList, "two", userAccessLevelId, userAccessLevelValues,alertTypeList,editionList,filterType,locationValue,disctrictId,alertStatusIds,locationValues);    
			if(userAccessLevelId == 5L && blockType != null && blockType.trim().equalsIgnoreCase("Parliament") && locationValues != null && locationValues.size() > 0L){
				filteredConstituencyList(alertCountGrpByLocList,locationValues);
		          
			}
			
			if(blockType != null && blockType.trim().equalsIgnoreCase("Parliament")){
				convertConstituencyDateIntoParliamentDate(alertCountGrpByLocList,parliamentList,"status");
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
			List<AlertOverviewVO> finalList = new ArrayList<AlertOverviewVO>();
			AlertOverviewVO innerListAlertVO = null;
			if(locationIdAndStatusIdAndCountMap.size() > 0){
				for(Entry<Long,Map<Long,Long>> entry : locationIdAndStatusIdAndCountMap.entrySet()){
					statusIdAndCountMap = entry.getValue();
					if(statusIdAndCountMap.size() > 0){
						if(statusList != null && statusList.size() > 0){
							alertVOs = new ArrayList<AlertOverviewVO>();
							innerListAlertVO = new AlertOverviewVO();
							for(Object[] param : statusList){
								alertVO = new AlertOverviewVO();
								alertVO.setStatusTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
								alertVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[1]));
								alertVOs.add(alertVO);  
							}
						}
						for(AlertOverviewVO param : alertVOs){
							if(statusIdAndCountMap.get(param.getStatusTypeId()) != null){
								param.setTotalAlertCnt(statusIdAndCountMap.get(param.getStatusTypeId()));  
							}else{
								param.setTotalAlertCnt(0l);  
							}
						}
						innerListAlertVO.setSubList1(alertVOs);
						if(locationIdAndNameMap.get(entry.getKey()) != null){
							innerListAlertVO.setId(entry.getKey());
							innerListAlertVO.setName(locationIdAndNameMap.get(entry.getKey()));
							
						}
						if(locationIdAndCountMap.get(entry.getKey()) != null){
							innerListAlertVO.setTotalAlertCnt(locationIdAndCountMap.get(entry.getKey()));
						}
						finalList.add(innerListAlertVO);     
					}
				}
			}  
			//Sorting list based on required parameter
			if(finalList != null && finalList.size() > 0){
				sortListByRequiredType(finalList,sortingType);	
			}
			return finalList; 
		   }
	  }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getTotalAlertGroupByLocationThenStatus() method of AlertService{}");
		}
		return null;
	}
	public void convertConstituencyDateIntoParliamentDateForToralAlert(List<Object[]> alertCountList,List<Object[]> parliamentList){
		try{
			//create a map for parliamentId and listOfConstituencyId
 			Map<Long,List<Long>> parliamentIdAndListConstituency = new HashMap<Long,List<Long>>();
 			//create a map for parliamentIdAndName
 			Map<Long,String> parliamentIdAndNameMap = new HashMap<Long,String>();
 			List<Long> constituencyIdList = null;
 			if(parliamentList != null && parliamentList.size() > 0){
 				for(Object[] param : parliamentList){
 					parliamentIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1]));
 					constituencyIdList = parliamentIdAndListConstituency.get(commonMethodsUtilService.getLongValueForObject(param[0]));
 					if(constituencyIdList == null){
 						constituencyIdList = new ArrayList<Long>();
 						parliamentIdAndListConstituency.put(commonMethodsUtilService.getLongValueForObject(param[0]), constituencyIdList);
 					}
 					constituencyIdList.add(commonMethodsUtilService.getLongValueForObject(param[2]));
 				}
 			}
 			//create a map for constituencyId and constituency dtls
 			Map<Long,List<Object[]>> constituencyIdAndListOfThatConstituencyDtls = new HashMap<Long,List<Object[]>>();
 			List<Object[]> listOfThatConstituencyDtls = null;
 			if(alertCountList != null && alertCountList.size() > 0){
 				for(Object[] param : alertCountList){
 					if(param[0] != null){
 						listOfThatConstituencyDtls = constituencyIdAndListOfThatConstituencyDtls.get(commonMethodsUtilService.getLongValueForObject(param[0]));
 						if(null == listOfThatConstituencyDtls){
 							listOfThatConstituencyDtls = new ArrayList<Object[]>();
 							constituencyIdAndListOfThatConstituencyDtls.put(commonMethodsUtilService.getLongValueForObject(param[0]), listOfThatConstituencyDtls);
 						}
 						listOfThatConstituencyDtls.add(param);
 					}
 				}
 			}
 			
 			// create a map for parliamentId and listOfObjectArr for constituency
 			Map<Long,List<Object[]>> parliamentIdAndListOfObjectArr = new HashMap<Long,List<Object[]>>();
 			List<Object[]> listOfConArr = null;
 			if(parliamentIdAndListConstituency != null && parliamentIdAndListConstituency.size() > 0){
 				for(Entry<Long,List<Long>> innerParam : parliamentIdAndListConstituency.entrySet()){
 					listOfConArr = new ArrayList<Object[]>();
 					for(Long conId : innerParam.getValue()){
 						listOfConArr.addAll(constituencyIdAndListOfThatConstituencyDtls.get(conId));
 					}
 					parliamentIdAndListOfObjectArr.put(innerParam.getKey(), listOfConArr);
 				}
 			}
 			//create map for parliamentId and count map
 			Map<Long,Long> parliamentIdAndCountMap = new HashMap<Long,Long>();
 			Long count=null;
 			if(parliamentIdAndListOfObjectArr != null && parliamentIdAndListOfObjectArr.size() > 0){
 				for(Entry<Long,List<Object[]>> innerParam : parliamentIdAndListOfObjectArr.entrySet()){
 					count = new Long(0L);
 					for(Object[] param : innerParam.getValue()){
 						count = count + commonMethodsUtilService.getLongValueForObject(param[2]);
 					}
 					parliamentIdAndCountMap.put(innerParam.getKey(), count);
 				}
 			}
 			// now create list of object[]
 			alertCountList.clear();
 			Object[] arr = null;
 			if(parliamentIdAndCountMap != null && parliamentIdAndCountMap.size() > 0){
 				for(Entry<Long,Long> param : parliamentIdAndCountMap.entrySet()){
					arr = new Object[3];
					arr[0] = param.getKey();
					arr[1] = parliamentIdAndNameMap.get(param.getKey());
					arr[2] = param.getValue();
					alertCountList.add(arr);
 				}
 			}
		}catch(Exception e){
			LOG.error("Error occured convertConstituencyDateIntoParliamentDateForToralAlert() method of AlertService{}");
		}
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
  public AlertOverviewVO getAlertOverviewDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr, Long alertType,Long editionType,List<Long> scopeIdList,List<Long> alertStatusIds){
	  
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
	  // alertEditions.add(editionType);
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
			if(alertType > 0l){ //we are taking alert status by alert type
				List<Object[]> statusObjLst = alertDepartmentStatusDAO.getAlertStatusByAlertStatusId(alertStatusIds,alertType);
				setAlertStatusByAlertType(statusObjLst,alertStatusIds);
		    }
		   List<Object[]> rtrnTtlAlrtObjLst = alertDAO.getAlertCntByAlertTypeBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate, "false",alertTypes,alertEditions,scopeIdList,alertStatusIds);
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
		   
		   List<Object[]> rtrnTtlAlrtGrpByEditionObjLst = alertDAO.getAlertCntByAlertTypeBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate, "true",alertTypes,alertEditions,scopeIdList,alertStatusIds);
		   
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
		   List<Object[]> rtrnAlrtStatusObjLst = alertDepartmentStatusDAO.getAlertStatusByAlertStatusId(alertStatusIds,alertType);
		   List<Object[]> rtrnAlrtSttsWsCntObjLst = alertDAO.getAlertCntByAlertStatusBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate, "false",alertTypes,alertEditions,scopeIdList,alertStatusIds);
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
		   List<Object[]> rtrnAlrtSttsAndEditionWsCntObjLst = alertDAO.getAlertCntByAlertStatusBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate, "true",alertTypes,alertEditions,scopeIdList,alertStatusIds);
		   
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
		   
		   List<Object[]> rtrnAlrCtgryCntobjLst = alertDAO.getAlertCntByAlertCategoryBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate,"false",alertTypes,alertEditions,scopeIdList,alertStatusIds);
		 
		   if(rtrnAlrCtgryCntobjLst != null && !rtrnAlrCtgryCntobjLst.isEmpty() ){  
			  for(Object[] param:rtrnAlrCtgryCntobjLst)  {
				  if(alertCategoryMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) != null ){
					  alertCategoryMap.get(commonMethodsUtilService.getLongValueForObject(param[0])).setStatusCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
				    } 
			  }

		   }
		   List<Object[]> rtrnAlrtCtgryAndSttsWseCntObjLst = alertDAO.getAlertCntByAlertCategoryAndAlertStatusWiseBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate,alertTypes,alertEditions,scopeIdList,alertStatusIds);
		 
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
		   List<Object[]> rtrnAlrCtgryCntAndEditionobjLst = alertDAO.getAlertCntByAlertCategoryBasedOnUserAccessLevel(locationAccessLevelId, locationValues, stateId, fromDate, toDate,"true",alertTypes,alertEditions,scopeIdList,alertStatusIds);
		   
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
		   List<Object[]> rtrnAlerCntObjLst = verificationStatusDAO.getAlertCountStatusWiseBasedOnActionType(locationAccessLevelId, locationValues, stateId, fromDate, toDate, alertTypes, alertEditions,scopeIdList,alertStatusIds);
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
	public List<AlertCoreDashBoardVO> getAlertDtls(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId, List<Long> alertStatusIds, Long alertCategoryId, Long activityMemberId, Long editionId,String isActionType,Long alertActionTypeId,List<Long> impactScopeIds,Long alertVerificationStatusId){
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
			if(isActionType != null && isActionType.equalsIgnoreCase("Yes")){ //alertStatusId means alertActionTypeStatusId //d
			  alertList = verificationStatusDAO.getActionTypeAlertDetails(fromDate, toDate, stateId, alertTypeId, alertStatusIds, userAccessLevelId, userAccessLevelValues, editionList,alertActionTypeId,impactScopeIds,alertVerificationStatusId);
			}else{//d
			 alertList = alertDAO.getAlertDtls(fromDate, toDate, stateId, alertTypeId, alertStatusIds, alertCategoryId, userAccessLevelId, userAccessLevelValues,editionList,impactScopeIds);	
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
 public List<AlertOverviewVO> getAlertCategoryDtlsLocationWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,Long alertType, Long editionTypeId,List<Long> impactScopeIds,List<Long> alertStatusIds) {
	 List<AlertOverviewVO> resultList = new ArrayList<AlertOverviewVO>();
	 Map<Long,AlertOverviewVO> categoryMap = new ConcurrentHashMap<Long, AlertOverviewVO>(0);
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
	    	 List<Long> alertTypeList = new ArrayList<Long>();
	         List<Long> editionList = new ArrayList<Long>();
	         if(alertType != null){
	        	 if(alertType.longValue() != 0L){
	        	   alertTypeList.add(alertType);
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
		 	  List<Object[]> rtrnAlertCategoryObjLst = alertCategoryDAO.getAllCategoryOrderBy();
			  List<AlertOverviewVO> implactLevelList = getAlertImpactLevelWiseLocationSubTemplate(impactScopeIds); 
			  List<Object[]> rtrnAlertStatusObjLst = alertDepartmentStatusDAO.getAlertStatusByAlertStatusId(alertStatusIds,alertType);
			  if(alertType > 0l){ //we are taking alert status by alert type 
				  setAlertStatusByAlertType(rtrnAlertStatusObjLst,alertStatusIds);
			    }
			   
			  		 prepareTemplate(rtrnAlertCategoryObjLst,implactLevelList,rtrnAlertStatusObjLst,categoryMap);////Prepare Template 
			
			  
					  List<Object[]> rtrnImpactLevelCntObjLst = alertDAO.getAlertCntByAlertCategoryAndImpactLevelWiseBasedOnUserAccessLevel(locationAccessLevelId,locationValues,stateId,fromDate, toDate,alertTypeList,editionList,alertStatusIds,impactScopeIds,"allImpact");
					  setAlertImpactLevelWiseAlertCnt(rtrnImpactLevelCntObjLst,categoryMap); 
					  List<Object[]> rtrnImpctLvlSttusWsCntObjLst = alertDAO.getAlertCntByAlertCategoryImpactLevelAndStatusWiseBasedOnUserAccessLevel(locationAccessLevelId,locationValues,stateId,fromDate, toDate,alertTypeList,editionList,alertStatusIds,impactScopeIds,"allImpact");
					  setStatusWiseAlertCnt(rtrnImpctLvlSttusWsCntObjLst,categoryMap);
		
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
			  Map<Long,AlertOverviewVO> overAllAlrtDtlsMap = new LinkedHashMap<Long, AlertOverviewVO>();
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
 public void prepareTemplate(List<Object[]> objList,List<AlertOverviewVO> impactLevelLst,List<Object[]> statusList,Map<Long,AlertOverviewVO> categoryMap) {
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
 public List<AlertOverviewVO> getImpactLevelList(List<AlertOverviewVO> impactLevlLst,List<Object[]> statusList){
	 List<AlertOverviewVO> locationList = new CopyOnWriteArrayList<AlertOverviewVO>();
	   try{
		   if(impactLevlLst != null  && !impactLevlLst.isEmpty() ){
			   for(AlertOverviewVO impactLevelVO:impactLevlLst){
				   AlertOverviewVO impctLvlVO = new AlertOverviewVO();
				   impctLvlVO.setLocationTypeId(impactLevelVO.getId());
				   impctLvlVO.setLocationType(impactLevelVO.getName());
				   impctLvlVO.getStatusList().addAll(getAlrtStatus(statusList));
				   locationList.add(impctLvlVO);
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
				 if(impactLevelId == 5l || impactLevelId==12l){/* MANDAL/MUNICIPALITY */ 
	 	 				impactLevelId = 5l;
	 			 }else if(impactLevelId == 7l || impactLevelId==9l || impactLevelId==6l){/* VILLAGE/WARD/PANCHAYAT */
	 					impactLevelId = 7l;
	 			 }
				 if(categoryMap.get(alrtCtgryId) != null){
					 AlertOverviewVO impactLevelVO = getImpactLevelMatchVO(categoryMap.get(alrtCtgryId).getSubList(),impactLevelId);
					 	if(impactLevelVO != null ){
					 		impactLevelVO.setAlertCount(impactLevelVO.getAlertCount()+alertCnt);
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
				 if(impactLevelId == 5l || impactLevelId==12l){/* MANDAL/MUNICIPALITY */ 
	 	 				impactLevelId = 5l;
	 			 }else if(impactLevelId == 7l || impactLevelId==9l || impactLevelId==6l){/* VILLAGE/WARD/PANCHAYAT */
	 					impactLevelId = 7l;
	 			 }
				 if(categoryMap.get(alrtCtgryId) != null){
					 AlertOverviewVO impactLevelVO = getImpactLevelMatchVO(categoryMap.get(alrtCtgryId).getSubList(),impactLevelId);
					 if(impactLevelVO != null){
						 AlertOverviewVO statusVO = getMatchVO(impactLevelVO.getStatusList(),statusId);
						   if(statusVO != null){
							   statusVO.setAlertCount(statusVO.getAlertCount()+alertCnt);   
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
 public List<AlertVO> getTotalAlertGroupByPubRepThenStatus(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, Long publicRepresentativeTypeId,List<Long> commitLvlIdList, String groupAssignType, String position, Long designationId,Long alertTypeId,Long editionTypeId,Long districtId,List<Long> alertStatusIds,List<Long> enrollementYearIds){
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
			List<Object[]> statusList = alertDepartmentStatusDAO.getAlertStatusByAlertStatusId(alertStatusIds,alertTypeId);
			
			
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
					alertCountList = alertDAO.getTdpCommitteeRolesByAlertCnt(userAccessLevelId,userAccessLevelValues,stateId,scopeIdList,fromDate,toDate,commitLvlIdList,designationId,"one",alertTypeList,editionList,districtId,alertStatusIds,enrollementYearIds);
				}else{  
					alertCountList = alertDAO.getTdpBasicCommiteeTypeAndAlertStatusByAlertCnt(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate,commitLvlIdList, "one",alertTypeList,editionList,districtId,alertStatusIds,enrollementYearIds);
				}
			}else{  
				if(publicRepresentativeTypeId.longValue() == 0L){
					alertCountList = alertDAO.getTotalAlertGroupByPubRepThenStatus(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, "one",alertTypeList,editionList,districtId,alertStatusIds);
				}else{
					alertCountList = alertDAO.getTotalAlertGroupByCandThenStatus(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, "one",publicRepresentativeTypeId,alertTypeList,editionList,districtId,alertStatusIds);
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
					alertCountGrpByLocList = alertDAO.getTdpCommitteeRolesByAlertCnt(userAccessLevelId,userAccessLevelValues,stateId,scopeIdList,fromDate,toDate,commitLvlIdList,designationId,"two",alertTypeList,editionList,districtId,alertStatusIds,enrollementYearIds);
				}else{
					alertCountGrpByLocList = alertDAO.getTdpBasicCommiteeTypeAndAlertStatusByAlertCnt(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate,commitLvlIdList, "two",alertTypeList,editionList,districtId,alertStatusIds,enrollementYearIds);

				}
			}else{
				if(publicRepresentativeTypeId.longValue() == 0L){
					alertCountGrpByLocList = alertDAO.getTotalAlertGroupByPubRepThenStatus(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, "two",alertTypeList,editionList,districtId,alertStatusIds);
				}else{  
					alertCountGrpByLocList = alertDAO.getTotalAlertGroupByCandThenStatus(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, "two",publicRepresentativeTypeId,alertTypeList,editionList,districtId,alertStatusIds);
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
 	public List<AlertVO> getMemForPartyCommitDesg(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId,List<Long> commitLvlIdArr,Long commitTypeId,Long designationId,Long alertTypeId,Long editionTypeId,Long districtId,List<Long> alertStatusIds,List<Long> enrollementYearIds){
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
			List<Object[]> statusList = alertDepartmentStatusDAO.getAlertStatusByAlertStatusId(alertStatusIds,alertTypeId);
			
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
			
			List<Object[]> alertCountList = alertDAO.getMemForPartyCommitDesg(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, commitLvlIdArr, commitTypeId, designationId, "one",alertTypeList,editionList,districtId,alertStatusIds,enrollementYearIds);
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
			alertCountGrpByLocList = alertDAO.getMemForPartyCommitDesg(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, commitLvlIdArr, commitTypeId, designationId, "two",alertTypeList,editionList,districtId,alertStatusIds,enrollementYearIds);
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
 public List<AlertOverviewVO> getAssignGroupTypeAlertDtlsByImpactLevelWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertTypeId,Long editionTypeId,Long districtId,List<Long> alertStatusIds,List<Long> enrollementYearIds){
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
			   if(alertTypeId > 0l){
					List<Object[]> statusObjLst = alertDepartmentStatusDAO.getAlertStatusByAlertStatusId(alertStatusIds,alertTypeId);
					setAlertStatusByAlertType(statusObjLst,alertStatusIds);
			   }
			  Set<Long> allTypeTdpCadreIds = new HashSet<Long>(0);
			  Map<Long,Set<Long>> statusWiseAlertCntMap = new HashMap<Long, Set<Long>>();
			  Set<Long> totalAletCntSt = new HashSet<Long>(0);
			  //Calculating public representative type alert count  
			  List<Object[]> rtrnPblcRprsnttvTypAlrtDtlsObjLst = alertDAO.getPublicRepresentativeTypeAlertDtls(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList,districtId,alertStatusIds);
			  mergeStatusWiseAlertCnt(rtrnPblcRprsnttvTypAlrtDtlsObjLst,statusWiseAlertCntMap,allTypeTdpCadreIds,totalAletCntSt,null);
			  setDatatoFinalList(prepareTempalate(alertStatusIds,alertTypeId),statusWiseAlertCntMap,totalAletCntSt,resultList,"Public Representative");
			  //Calculating party committee type alert count
			  List<Object[]> rtrnPrtyCmmttAlrtDtlsObjLst = alertDAO.getPartyCommitteeTypeAlertDtls(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList,districtId,alertStatusIds,enrollementYearIds);
			  mergeStatusWiseAlertCnt(rtrnPrtyCmmttAlrtDtlsObjLst,statusWiseAlertCntMap,allTypeTdpCadreIds,totalAletCntSt,null);
			  setDatatoFinalList(prepareTempalate(alertStatusIds,alertTypeId),statusWiseAlertCntMap,totalAletCntSt,resultList,"Party Committee");
			  //Calculating program type alert count
			  List<Object[]> rtrnPrgrmCmmttAlrtDtlsOblLst = alertDAO.getProgramCommitteeTypeAlertDtls(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList,districtId,alertStatusIds);
			  mergeStatusWiseAlertCnt(rtrnPrgrmCmmttAlrtDtlsOblLst,statusWiseAlertCntMap,allTypeTdpCadreIds,totalAletCntSt,null);
			  setDatatoFinalList(prepareTempalate(alertStatusIds,alertTypeId),statusWiseAlertCntMap,totalAletCntSt,resultList,"Program Committee");
			  //Calculating other type alert count
			  List<Object[]> rtrnAllAlertCntDls = alertDAO.getAllAlertDtls(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList,districtId,alertStatusIds);
			  mergeStatusWiseAlertCnt(rtrnAllAlertCntDls,statusWiseAlertCntMap,null,totalAletCntSt,allTypeTdpCadreIds);
			  setDatatoFinalList(prepareTempalate(alertStatusIds,alertTypeId),statusWiseAlertCntMap,totalAletCntSt,resultList,"Others");
			  
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
 public Map<Long,AlertOverviewVO> prepareTempalate(List<Long> alertStatuIds,Long alertTypeId){
	 Map<Long,AlertOverviewVO> alertStatusMap = new HashMap<Long, AlertOverviewVO>();
	 try{
		 List<Object[]> rtrnStatusLst = alertDepartmentStatusDAO.getAlertStatusByAlertStatusId(alertStatuIds,alertTypeId);;
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
 public List<AlertOverviewVO> getOtherAndPrgrmCmmtteeTypeAlertCndtDtls(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,String resultType,Long alertTypeId,Long editionTypeId,Long districtId,List<Long> alertStatusIds,List<Long> enrollementYearIds){
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
			   List<Object[]> rtrnStatusLst = alertDepartmentStatusDAO.getAlertStatusByAlertStatusId(alertStatusIds,alertTypeId);
			   List<Object[]> rtrnPrgrmCmmttAlrtDtlsOblLst = alertDAO.getProgramCommitteeTypeAlertDtls(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList,districtId,alertStatusIds);
			   Set<Long> allTypeTdpCadreIds = new HashSet<Long>(0);
			   if(resultType != null && resultType.trim().equalsIgnoreCase("Program Committee")){
				   
				   setCandiateAlertCnt(rtrnPrgrmCmmttAlrtDtlsOblLst,candiateDtsMap,allTypeTdpCadreIds,rtrnStatusLst); // in this case allTypeTdpCadreIds set is empty
			  
			   }else if(resultType != null && resultType.trim().equalsIgnoreCase("Others")){
				   
				  List<Object[]> rtrnPblcRprsnttvTypAlrtDtlsObjLst = alertDAO.getPublicRepresentativeTypeAlertDtls(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList,districtId,alertStatusIds);
				  setTdpCadreId(rtrnPblcRprsnttvTypAlrtDtlsObjLst,allTypeTdpCadreIds);
				  List<Object[]> rtrnPrtyCmmttAlrtDtlsObjLst = alertDAO.getPartyCommitteeTypeAlertDtls(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList,districtId,alertStatusIds,enrollementYearIds);
				  setTdpCadreId(rtrnPrtyCmmttAlrtDtlsObjLst,allTypeTdpCadreIds);
				  setTdpCadreId(rtrnPrgrmCmmttAlrtDtlsOblLst,allTypeTdpCadreIds);
				  List<Object[]> rtrnAllAlertCntDls = alertDAO.getAllAlertDtls(locationAccessLevelId,locationValues,stateId,impactLevelIds, fromDate, toDate,alertTypeList,editionList,districtId,alertStatusIds);
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
 public List<AlertCoreDashBoardVO> getAlertDtlsForPubRep(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, Long publicRepresentativeTypeId, Long cadreId, List<Long> alertStatusIds,Long alertTypeId,Long editionTypeId,Long districtId){
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
			
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();//d
			List<Object[]> alertList = alertDAO.getAlertDtlsForPubRep(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, publicRepresentativeTypeId, cadreId, alertStatusIds,alertTypeList,editionList,districtId);
			setAlertDtls(alertCoreDashBoardVOs, alertList);
			return alertCoreDashBoardVOs;  
			}catch(Exception e){  
				e.printStackTrace();
				LOG.error("Error occured getTotalAlertGroupByLocationThenStatus() method of AlertService{}");
			}
		return null;
 	}
 public void setAlertDtls(List<AlertCoreDashBoardVO> alertCoreDashBoardVOs, List<Object[]> alertList){
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
					alertCoreDashBoardVO.setSevertyColor(commonMethodsUtilService.getStringValueForObject(param[24]));
					alertCoreDashBoardVO.setStatusColor(commonMethodsUtilService.getStringValueForObject(param[25]));
					if(param.length > 26){
						alertCoreDashBoardVO.setProblem(commonMethodsUtilService.getStringValueForObject(param[26]));
						alertCoreDashBoardVO.setRelatedTo(commonMethodsUtilService.getStringValueForObject(param[27]));
					}
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
					
					if(param[23] != null){
						alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[23]));	
					}else if(param[22] != null){
						alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[22]));	
					}else if(param[10] != null){
						alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[10]));	
					}else if(param[11] != null){
						alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[11]));	
					}else if(param[20] != null){
						alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[20]));
					}
					//hiii
					   if(commonMethodsUtilService.getLongValueForObject(param[5]).longValue() == 2L){//print
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
						}else{
							alertSource = commonMethodsUtilService.getStringValueForObject(param[13]);//for social media,call center and other category
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
 public AlertOverviewVO getStateOrGhmcImpactLevelAlertStatusWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertTypeId, Long editionId,List<Long> alertStatusIds,Long districtId){
	 AlertOverviewVO resultVO = new AlertOverviewVO();
	 Set<Long> locationValues = new HashSet<Long>(0);
     Long locationAccessLevelId =0l;
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date fromDate=null;
     Date toDate = null;
 	 try{
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
			 List<Object[]> rtrnStatusObjLst = alertDepartmentStatusDAO.getAlertStatusByAlertStatusId(alertStatusIds,alertTypeId);
			 
			
			  List<Object[]> rtrnStatusWiseCntObjLst = alertDAO.getStateImpactLevelAlertCnt(locationAccessLevelId, locationValues, stateId, impactLevelIds, fromDate, toDate,alertTypeList,editionList,alertStatusIds,districtId);	
			  if(impactLevelIds != null && impactLevelIds.size() > 0 && impactLevelIds.size() == 1 &&  impactLevelIds.contains(8l)){
				  if(rtrnStatusWiseCntObjLst != null && rtrnStatusWiseCntObjLst.size() > 0){
						 // if(rtrnStatusWiseCntObjLst != null && rtrnStatusWiseCntObjLst.size() > 0){
						 		for(Object[] param:rtrnStatusWiseCntObjLst){
						 			Long statusId = commonMethodsUtilService.getLongValueForObject(param[0]);
						 			Long locationId = commonMethodsUtilService.getLongValueForObject(param[3]);
						 			AlertOverviewVO locationVO = getRequiredMatchVO(resultVO.getCategoryList(),locationId);
						 			if(locationVO == null){
						 				locationVO = new AlertOverviewVO();
						 				locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[3]));
						 				locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[4]));
						 				resultVO.getCategoryList().add(locationVO);
						 				prepareRquiredTemplate(rtrnStatusObjLst,locationVO.getStatusList());
						 			}
						 			AlertOverviewVO statusVO = getRequiredMatchVO(locationVO.getStatusList(),statusId);
						 			if(statusVO != null){
						 				statusVO.setAlertCount(commonMethodsUtilService.getLongValueForObject(param[2]));
						 			}
						 		}
						 	//} 
					  }
			  }else {
				  prepareRquiredTemplate(rtrnStatusObjLst,resultVO.getStatusList());
				  if(rtrnStatusWiseCntObjLst != null && rtrnStatusWiseCntObjLst.size() > 0){
					 // if(rtrnStatusWiseCntObjLst != null && rtrnStatusWiseCntObjLst.size() > 0){
					 		for(Object[] param:rtrnStatusWiseCntObjLst){
					 			Long statusId = commonMethodsUtilService.getLongValueForObject(param[0]);
					 			AlertOverviewVO statusVO = getRequiredMatchVO(resultVO.getStatusList(),statusId);
					 			if(statusVO != null){
					 				statusVO.setAlertCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					 			}
					 		}
					 	//} 
				  }
			  }
			  //Calculate overall alert
			  if(impactLevelIds != null && impactLevelIds.size() > 0 && impactLevelIds.size() == 1 &&  impactLevelIds.contains(8l) && resultVO.getCategoryList() != null && resultVO.getCategoryList().size() >0){
				  for(AlertOverviewVO locationVO :resultVO.getCategoryList()){
				  if(locationVO.getStatusList() != null && locationVO.getStatusList().size() > 0){
						for(AlertOverviewVO statusVO:locationVO.getStatusList()){
							locationVO.setAlertCount(locationVO.getAlertCount().longValue()+statusVO.getAlertCount().longValue());
						}
					}
				  }
			  }else{
					if(resultVO.getStatusList() != null && resultVO.getStatusList().size() > 0){
						for(AlertOverviewVO statusVO:resultVO.getStatusList()){
							resultVO.setAlertCount(resultVO.getAlertCount().longValue()+statusVO.getAlertCount().longValue());
						}
					}
			  }
	 }catch(Exception e){
		 LOG.error("Exception occured  in getStateOrGhmcImpactLevelAlertStatusWise() in AlertService class ",e);  
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
		 LOG.error("Exception occured  in prepareRquiredTemplate() in AlertService class ",e);
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
 	public List<AlertCoreDashBoardVO> getAlertDtlsAssignedByPartyCommite(String fromDateStr,String toDateStr,Long stateId,List<Long> scopeIdList,Long activityMemberId,List<Long> commitLvlIdList,Long commitTypeId,Long designationId,Long cadreId, List<Long> alertStatusIds,Long alertTypeId,Long editionTypeId,Long districtId,List<Long> enrollementYearIds){
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
			
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();//d
			List<Object[]> alertList = alertDAO.getAlertDtlsAssignedByPartyCommite(userAccessLevelId, userAccessLevelValues, stateId, scopeIdList, fromDate, toDate, commitLvlIdList, cadreId, commitTypeId,designationId,alertStatusIds,alertTypeList,editionList,districtId,enrollementYearIds);
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
 public List<AlertCoreDashBoardVO> getAlertDetailsTdpCadreWise(String fromDateStr, String toDateStr, Long stateId,List<Long> impactLevelIds, Long activityMemberId,Long tdpCadreId, List<Long> alertStatusIds,String resultType,Long alertTypeId,Long editionTypeId,Long districtId){
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
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();//d
			List<Object[]> alertList = alertDAO.getAlertDetailsByCadreWise(userAccessLevelId,userAccessLevelValues,fromDate,toDate,stateId,impactLevelIds,tdpCadreId,alertStatusIds,resultType,alertTypeList,editionList,districtId);
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
		List<Long> districtIdList,Long catId,Long alertTypeId, Long editionId,Long constituencyId,List<Long> alertStatusIds,String locationLevel,String isPublication,String publicationIdStr,Long localElectionBodyId,String type,Long parliamentId){
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
			
			List<Long> constituencyList = null;
			if(parliamentId != null && parliamentId.longValue() > 0L){
				constituencyList = parliamentAssemblyDAO.getConstituencyIdsByParliamntId(parliamentId);
				districtIdList = null;
				constituencyId = null;
			}
			
			
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			String publicationType="";
			Long publicationId = 0l;
			if(isPublication != null && isPublication.equalsIgnoreCase("true")){//|| locationLevel != null && locationLevel.equalsIgnoreCase("District/GMC CORP Impact Level")
				if(publicationIdStr != null && publicationIdStr.length() > 0){
					Long firstDigit = Long.valueOf(publicationIdStr.substring(0,1));
					if(firstDigit == 1l){
						publicationId = Long.valueOf(publicationIdStr.substring(1));
						publicationType="TvChannel";
					}else if(firstDigit == 2l){
						publicationId = Long.valueOf(publicationIdStr.substring(1));
						publicationType="NewsPaper";
					}else{//d
						List<Object[]> tvChannelAlertList = alertDAO.getDistrictAndStateImpactLevelWiseAlertDtls(userAccessLevelId, userAccessLevelValues, fromDate, toDate, stateId, impactLevelIds, districtIdList,catId,alertTypeList,editionTypeList,constituencyId,alertStatusIds,"TvChannel",publicationId,localElectionBodyId,locationLevel,type,constituencyList);
						setAlertDtls(alertCoreDashBoardVOs, tvChannelAlertList);	
						List<Object[]> newsPaperAelrtList = alertDAO.getDistrictAndStateImpactLevelWiseAlertDtls(userAccessLevelId, userAccessLevelValues, fromDate, toDate, stateId, impactLevelIds, districtIdList,catId,alertTypeList,editionTypeList,constituencyId,alertStatusIds,"NewsPaper",publicationId,localElectionBodyId,locationLevel,type,constituencyList);
						setAlertDtls(alertCoreDashBoardVOs, newsPaperAelrtList);	
						return alertCoreDashBoardVOs;
					}
				}
			}
			
			
			List<Object[]> alertList = alertDAO.getDistrictAndStateImpactLevelWiseAlertDtls(userAccessLevelId, userAccessLevelValues, fromDate, toDate, stateId, impactLevelIds, districtIdList,catId,alertTypeList,editionTypeList,constituencyId,alertStatusIds,publicationType,publicationId,localElectionBodyId,locationLevel,type,constituencyList);
			setAlertDtls(alertCoreDashBoardVOs, alertList);
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
			 String notCadreImagesFoldr = ActivityService.createFolder(staticPath+"images/"+IConstants.ALERTS_ATTACHMENTS);
			 
			 String foldrSts = ActivityService.createFolder(notCadreImagesFoldr);
			 if(!foldrSts.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			 return staticPath+"images/"+IConstants.ALERTS_ATTACHMENTS;
			 
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
  public List<AlertCoreDashBoardVO> getAlertDetailsByAlertType(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId,Long activityMemberId,List<Long> impactScopeIds,List<Long> alertStatusIds,Long editionId,String alertType){
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
			List<Long> editionTypeList = new ArrayList<Long>();
			if(editionId != null){
			    if(editionId.longValue() == 1L){
					editionTypeList.add(editionId);
				}else if(editionId.longValue() == 2L){
					editionTypeList.add(editionId);
					editionTypeList.add(3L);
				}
			}
			//convert parliament into constituency.
			if(userAccessLevelId.longValue() == 4L){
				List<Long> parliamentAssemlyIds = parliamentAssemblyDAO.getAssemblyConstituencyforParliament(new ArrayList<Long>(userAccessLevelValues));
				userAccessLevelId = 5L;
				userAccessLevelValues.clear();
				userAccessLevelValues.addAll(parliamentAssemlyIds);      
			}
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();//d
			List<Object[]> alertList = alertDAO.getAlertDtlsByAlertTypeId(fromDate, toDate, stateId, alertTypeId, userAccessLevelId, userAccessLevelValues,impactScopeIds,alertStatusIds,editionTypeList,alertType);	
			setAlertDtls(alertCoreDashBoardVOs, alertList);
			return alertCoreDashBoardVOs;
		}catch(Exception e){
			e.printStackTrace();  
			LOG.error("Error occured getAlertDetailsByAlertType() method of AlertService{}");
		}
		return null;        
	}
	
	
	public List<AlertVO> getTotalAlertGroupByStatusForCentralMembers(String fromDateStr, String toDateStr, Long stateId,Long alertTypeId,Long tdpCadreId,String searchType){
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
			List<Long> consIds = new ArrayList<Long>(0);
			List<Object[]> alertCountList = null;
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
			if(searchType != null && searchType.trim().equalsIgnoreCase("areaAlerts")){
				List<Long>  userIds = tdpCadreLoginDetailsDAO.getuserIdsForCadre(tdpCadreId);
				List<Long> districtIds = userDistrictAccessInfoDAO.getDistrictIdsForUser(userIds);
				if(districtIds != null && districtIds.size() > 0l){
					List<Object[]> constList = constituencyDAO.getConstituenciesByDistrict(districtIds);
					if(constList != null && constList.size() > 0l){
						for (Object[] objects : constList) {
							Long elctionTypeId = commonMethodsUtilService.getLongValueForObject(objects[1]);
							if(elctionTypeId == 1){
								
							}else{
								consIds.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
							}
						}
					}
				}else{
					consIds = userConstituencyAccessInfoDAO.getConstituenciesByUser(userIds);
				}
			}
			
			//get alert status count and and create a map of alertStatusId and its count
			if(searchType != null && searchType.trim().equalsIgnoreCase("areaAlerts")){
				 alertCountList = alertDAO.getTotalAlertGroupByStatusForCentralAreaMembers(fromDate,toDate,stateId,alertTypeId,consIds);
			}else{
				 alertCountList = alertDAO.getTotalAlertGroupByStatusForCentralMembers(fromDate,toDate,stateId,alertTypeId,tdpCadreId,null);
			}
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
	
	public List<AlertVO> getTotalAlertGroupByStatusThenCategoryForCentralMembers(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId, Long tdpCadreId,String searchType){
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
			List<Long> consIds = new ArrayList<Long>(0);
			List<Object[]> alertCountList = null;
			List<Object[]> alertCountGrpByCatList =null;
			//get all the alert category for  building the template
			List<Object[]> categoryList = alertCategoryDAO.getAllCategory(); 
			
			if(searchType != null && searchType.trim().equalsIgnoreCase("areaAlerts")){
				List<Long>  userIds = tdpCadreLoginDetailsDAO.getuserIdsForCadre(tdpCadreId);
				List<Long> districtIds = userDistrictAccessInfoDAO.getDistrictIdsForUser(userIds);
				if(districtIds != null && districtIds.size() > 0l){
					List<Object[]> constList = constituencyDAO.getConstituenciesByDistrict(districtIds);
					if(constList != null && constList.size() > 0l){
						for (Object[] objects : constList) {
							Long elctionTypeId = commonMethodsUtilService.getLongValueForObject(objects[1]);
							if(elctionTypeId == 1){
								
							}else{
								consIds.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
							}
						}
					}
				}else{
					consIds = userConstituencyAccessInfoDAO.getConstituenciesByUser(userIds);
				}
			}
			
			if(searchType != null && searchType.trim().equalsIgnoreCase("areaAlerts")){
				 alertCountList = alertDAO.getTotalAlertGroupByStatusForCentralAreaMembers(fromDate,toDate,stateId,alertTypeId,consIds);
			}else{
				 alertCountList = alertDAO.getTotalAlertGroupByStatusForCentralMembers(fromDate,toDate,stateId,alertTypeId,tdpCadreId,null);
			}
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
			//get all the alert count group by status then category.
			Map<Long,String> statusIdAndNameMap = new HashMap<Long,String>();
			Map<Long,Long> categoryIdAndCountMap = null;//new HashMap<Long, Long>();
			Map<Long,Map<Long,Long>> statusIdAndCategoryIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
			
			//get alert status count and and create a map of alertStatusId and its count
			if(searchType != null && searchType.trim().equalsIgnoreCase("areaAlerts")){
				 alertCountGrpByCatList = alertDAO.getTotalAlertGroupByStatusThenCategoryForCentralAreaMembers(fromDate, toDate, stateId, alertTypeId,consIds);
			}else{
				 alertCountGrpByCatList = alertDAO.getTotalAlertGroupByStatusThenCategoryForCentralMembers(fromDate, toDate, stateId, alertTypeId,tdpCadreId,consIds);
			}
			
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
			List<Long> consIds = new ArrayList<Long>(0);
			List<Object[]> list = null;
			
			if(inputVO.getSearchType() != null && inputVO.getSearchType().trim().equalsIgnoreCase("areaAlerts")){
				List<Long>  userIds = tdpCadreLoginDetailsDAO.getuserIdsForCadre(inputVO.getAssignId());
				inputVO.setAssignId(null);
				List<Long> districtIds = userDistrictAccessInfoDAO.getDistrictIdsForUser(userIds);
				if(districtIds != null && districtIds.size() > 0l){
					List<Object[]> constList = constituencyDAO.getConstituenciesByDistrict(districtIds);
					if(constList != null && constList.size() > 0l){
						for (Object[] objects : constList) {
							Long elctionTypeId = commonMethodsUtilService.getLongValueForObject(objects[1]);
							if(elctionTypeId == 1){
								
							}else{
								consIds.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
							}
						}
					}
				}else{
					consIds = userConstituencyAccessInfoDAO.getConstituenciesByUser(userIds);
				}
			}
			
			if(inputVO.getSearchType() != null && inputVO.getSearchType().trim().equalsIgnoreCase("areaAlerts")){
				 list = alertDAO.getLocationLevelWiseAlertsDataForCentralAreaMembers(userTypeIds,inputVO,fromDate,toDate,consIds);//done
			}else{
				 list = alertDAO.getLocationLevelWiseAlertsDataForCentralMembers(userTypeIds,inputVO,fromDate,toDate,null);//done
			}
			
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
	/* Extra code */
	/*
	 * Author :Santosh
	 * Date : 22-02-2017
	 * @Description : This service is used to get publication wise alert count;
	 */
	/*public List<AlertOverviewVO> getPublicationWiseAlert(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, List<Long> alertStatusIds,Long alertTypeId,Long editionId,String filterType,List<Long> districtIds){
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
	}*/
   public void removePublicationHasNoData(List<AlertOverviewVO> finalResultList){
	   try{
		   if(finalResultList != null && finalResultList.size() > 0){
			   List<String> publicationIdList = new ArrayList<String>();
			   for(AlertOverviewVO locationVO:finalResultList){
				   if(locationVO.getSubList() != null && locationVO.getSubList().size() > 0){
					   for(AlertOverviewVO publicationVO:locationVO.getSubList()){
						    if(publicationVO.getTotalAlertCnt()>0l){
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
     /*
	 * Author :Santosh,swadhin
	 * Date : 22-02-2017
	 * @Description : This service is used to get publication wise alert count;
	 */
 	public List<AlertOverviewVO> getPublicationWiseAlert(String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList, Long activityMemberId, List<Long> alertStatusIds,Long alertTypeId,Long editionId,String filterType,Long locationValue,String sortingType,Long disctrictId){
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
			if(alertTypeId > 0l){
				List<Object[]> statusObjLst = alertDepartmentStatusDAO.getAlertStatusByAlertStatusId(alertStatusIds,alertTypeId);
				setAlertStatusByAlertType(statusObjLst,alertStatusIds);
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
			//Preparing Template
			List<AlertOverviewVO> publicationList = new ArrayList<AlertOverviewVO>(0);
			List<Object[]> rtrnNewsPaperObjList = newsPaperDAO.getNewPaperList();//[1, AndhraBhoomi]
			List<Object[]> rtrnTvChannelObjLst = tvNewsChannelDAO.getChannelList();//[5, ABN]
			preparePublicationTemplate(rtrnTvChannelObjLst,publicationList,"TvChannel");
			preparePublicationTemplate(rtrnNewsPaperObjList,publicationList,"NewsPaper");
			
			//in case parliament wise alert
			String blockType = " ";
			List<Long> constituencyList = new ArrayList<Long>();
			if(filterType != null && filterType.trim().equalsIgnoreCase("Parliament") && locationValue != null && locationValue.longValue() > 0){
				if(locationValue != null && locationValue.longValue() > 0L){
					constituencyList = parliamentAssemblyDAO.getConstituencyIdsByParliamntId(locationValue);
				}
				locationValue = null;
				blockType = filterType;
				filterType="Constituency";
			}
			if(filterType != null && filterType.trim().equalsIgnoreCase("Parliament")){
				blockType = filterType;
				filterType="Constituency";
			}
			
			//Dao calls
			
			List<Object[]> rtrnTvChannelArtcntObjLst = alertDAO.getPublicationWiseAlertCnt(fromDate, toDate, stateId, scopeIdList, "TvChannel", userAccessLevelId, userAccessLevelValues, alertTypeList, editionList, filterType, locationValue,alertStatusIds,disctrictId,constituencyList);
			if(userAccessLevelId == 5L && blockType != null && blockType.trim().equalsIgnoreCase("Parliament") && constituencyList != null && constituencyList.size() > 0L){
				filteredConstituencyList(rtrnTvChannelArtcntObjLst,constituencyList);
			}
			List<Object[]> rtrnTvNwsPprArtcntObjLst = alertDAO.getPublicationWiseAlertCnt(fromDate, toDate, stateId, scopeIdList, "NewsPaper", userAccessLevelId, userAccessLevelValues, alertTypeList, editionList, filterType, locationValue,alertStatusIds,disctrictId,constituencyList);
			if(userAccessLevelId == 5L && blockType != null && blockType.trim().equalsIgnoreCase("Parliament") && constituencyList != null && constituencyList.size() > 0L){
				filteredConstituencyList(rtrnTvNwsPprArtcntObjLst,constituencyList);
		    }
			//convert constituency to parliament for TvChannel
			List<Object[]> parliamentListForTvChannel = null;
			if(blockType != null && blockType.trim().equalsIgnoreCase("Parliament")){
				List<Long> constituencyIds = new ArrayList<Long>();
				if(rtrnTvChannelArtcntObjLst != null && rtrnTvChannelArtcntObjLst.size() > 0){
					for(Object[] param : rtrnTvChannelArtcntObjLst){
						if(param[0] != null){
							constituencyIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
						}
					}
					parliamentListForTvChannel = parliamentAssemblyDAO.getParliamntIdByConsIds(constituencyIds);
					convertConstituencyDateIntoParliamentDate(rtrnTvChannelArtcntObjLst,parliamentListForTvChannel,"overview");
				}
			}
			
			//convert constituency to parliament for TvNews
			List<Object[]> parliamentListForTvNews = null;
			if(blockType != null && blockType.trim().equalsIgnoreCase("Parliament")){
				List<Long> constituencyIds = new ArrayList<Long>();
				if(rtrnTvNwsPprArtcntObjLst != null && rtrnTvNwsPprArtcntObjLst.size() > 0){
					for(Object[] param : rtrnTvNwsPprArtcntObjLst){
						if(param[0] != null){
							constituencyIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
						}
					}
					parliamentListForTvNews = parliamentAssemblyDAO.getParliamntIdByConsIds(constituencyIds);
					convertConstituencyDateIntoParliamentDate(rtrnTvNwsPprArtcntObjLst,parliamentListForTvNews,"overview");
				}
			}
			
			setPublicationWiseAlertCnt(rtrnTvChannelArtcntObjLst,locationWisAlertCntMap,publicationList,"TvChannel");
			setPublicationWiseAlertCnt(rtrnTvNwsPprArtcntObjLst,locationWisAlertCntMap,publicationList,"NewsPaper");
			
			//Calculation location wise overAll Alert Cnt.
			if(locationWisAlertCntMap != null && locationWisAlertCntMap.size() > 0){
				for(Entry<Long,AlertOverviewVO> locationEntry:locationWisAlertCntMap.entrySet()){
					if(locationEntry.getValue().getSubList() != null && locationEntry.getValue().getSubList().size()>0){
						for(AlertOverviewVO publicationVO:locationEntry.getValue().getSubList()){
							locationEntry.getValue().setTotalAlertCnt(locationEntry.getValue().getTotalAlertCnt()+publicationVO.getTotalAlertCnt());
						}
					}
					
				}
				resultList.addAll(locationWisAlertCntMap.values());
				locationWisAlertCntMap.clear();
			}
			//Remove publication has no data
			 removePublicationHasNoData(resultList);
			//Sorting list based on required parameter
			 if(resultList != null && resultList.size() > 0){
				 sortListByRequiredType(resultList,sortingType);	 
			 }
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getLocationLevelWisePublicationData() method of AlertService{}");
		}
		return resultList;
	}
    public void setAlertStatusByAlertType(List<Object[]> statusObjLst,List<Long> alertStatusIds){
    	try{
    		alertStatusIds.clear();
    		if(statusObjLst != null && statusObjLst.size() > 0){
    			for(Object[] param:statusObjLst){
    				alertStatusIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
    			}
    		}
    	}catch(Exception e){
    		LOG.error("Error occured setAlertStatusByAlertType() method of AlertService{}",e);
    	}
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
 						publicationMatchVO.setTotalAlertCnt(alertCnt);
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
 	    /*
 		 * Author :Santosh
 		 * Date : 15-03-2017
 		 * @Description : This service is used to get state impact and its sub level alert count;
 		 */
 	public AlertOverviewVO getStateImpactandItsSubLevelAlert(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertTypeId, Long editionId,List<Long> alertStatusIds,String selectionType){
 		 AlertOverviewVO resultVO = new AlertOverviewVO();
 	     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 	     Date fromDate=null;
 	     Date toDate = null;
 		 try{
 			   AlertInputsVO inputVO = new AlertInputsVO();
 			   if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.length() > 0l && toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
				   fromDate = sdf.parse(fromDateStr);
				   toDate = sdf.parse(toDateStr);
				 } 
 			   //Setting input parameter
 			    AlertInputsVO userAccessLevelVO = getUserAccessLevelIdAndValues(activityMemberId);
	 		    inputVO.setUserAccessLevelId(userAccessLevelVO.getUserAccessLevelId());
 				inputVO.setUserAccessLevelValues(userAccessLevelVO.getUserAccessLevelValues());
 				inputVO.setStateId(stateId);
 				inputVO.setFromDate(fromDate);
 				inputVO.setToDate(toDate);
 				inputVO.setType(selectionType);
 				inputVO.setImpactLevelIds(new ArrayList<Long>(impactLevelIds));
 				inputVO.setEditionList(getEditionList(editionId));
 				inputVO.setAlertStatusIds(alertStatusIds);
 				inputVO.setAlertTypeId(alertTypeId);
 				//Preparing required templated
 				 resultVO.setName("STATE OVERVIEW - IMPACT ALERTS");
 			     String resultType = "";
 				 List<Object[]> rtrnObjLst = null;
 				 
			   if(selectionType != null && selectionType.equalsIgnoreCase("impactScopeWise")){
				  prepareImpactLevelWiseTemplate(getAlertImpactLevelWiseLocationSubTemplate(impactLevelIds),resultVO);
			   }else if(selectionType != null && selectionType.equalsIgnoreCase("locationWise")){
				  prepareImpactLevelWiseTemplate(getAlertImpactLocationWiseLocationSubTemplate(impactLevelIds),resultVO);
			   }
			   
			    rtrnObjLst = alertDAO.getImpactLevelByAlertCount(inputVO,"State","");
				if(rtrnObjLst != null && rtrnObjLst.size() > 0){
 					for(Object[] param:rtrnObjLst){
 						setAlertAlertCount(resultVO,commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]),selectionType);
 					}
 				}
 	 			
 				//Calculating overall alert
 				if(resultVO.getSubList1() != null && resultVO.getSubList1().size() > 0){
 					for(AlertOverviewVO impactVO:resultVO.getSubList1()){
 						resultVO.setAlertCount(resultVO.getAlertCount()+impactVO.getAlertCount());	
 					}
 				}
 		 }catch(Exception e){
 			 LOG.error("Exception occured  in getStateImpactandItsSubLevelAlert() in AlertService class ",e);  
 		 }
 		 return resultVO;
 	 }
    public void prepareImpactLevelWiseTemplate(List<AlertOverviewVO> alertOverviewVOs,AlertOverviewVO resultVO){
   	 try{
   		 if(alertOverviewVOs != null && alertOverviewVOs.size() > 0){
   			 for(AlertOverviewVO VO:alertOverviewVOs){
   				 AlertOverviewVO locationLevelVO = new AlertOverviewVO();
   				 locationLevelVO.setId(VO.getId());
   				 locationLevelVO.setName(VO.getName());
   				 if(resultVO.getSubList1() == null){
   					 resultVO.setSubList1(new ArrayList<AlertOverviewVO>());	 
   				 }
   				  resultVO.getSubList1().add(locationLevelVO);
   			 }
   		 }
   	 }catch(Exception e){
   		 LOG.error("Exception occured  in prepareLocationLevelWiseTemplate() in AlertService class ",e); 
   	  }
    }
     /*
	 * Author :Santosh,Swadhin
	 * Date : 16-03-2017
	 * @Description : This service is used to get district or constituency impact and its sub level alert count;
	 */
 	public AlertOverviewVO getDistrictOrConstituencyImpactandItsSubLevelAlert(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertTypeId, Long editionId,List<Long> alertStatusIds,Long locationValue,String sortingType,String resultType,Long disctrictId,String selectionType){
		 AlertOverviewVO resultVO = new AlertOverviewVO();
		 Map<Long,AlertOverviewVO> locationMap = new HashMap<Long, AlertOverviewVO>();
	     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	     Date fromDate=null;
	     Date toDate = null;
		 try{
			     AlertInputsVO inputVO = new AlertInputsVO();
			     if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.length() > 0l && toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
				   fromDate = sdf.parse(fromDateStr);
				   toDate = sdf.parse(toDateStr);
				 } 
			     
			   //Setting input parameter
			    AlertInputsVO userAccessLevelVO = getUserAccessLevelIdAndValues(activityMemberId);
	 		    inputVO.setUserAccessLevelId(userAccessLevelVO.getUserAccessLevelId());
				inputVO.setUserAccessLevelValues(userAccessLevelVO.getUserAccessLevelValues());
				inputVO.setStateId(stateId);
				inputVO.setFromDate(fromDate);
				inputVO.setToDate(toDate);
				inputVO.setType(selectionType);//impactScopeWise
 				inputVO.setImpactLevelIds(new ArrayList<Long>(impactLevelIds));//[2, 3, 8, 5, 12, 7, 9, 6]
 				inputVO.setEditionList(getEditionList(editionId));
 				inputVO.setAlertStatusIds(alertStatusIds);//[2, 3, 8, 5, 12, 7, 9, 6]
				inputVO.setAlertTypeId(alertTypeId);
				inputVO.setDistrictId(disctrictId);
				List<Long> constituencyList = null;
				List<Object[]> parliamentList = null;
				String blockType = "";
				if(resultType != null && resultType.trim().equalsIgnoreCase("District")){
					resultVO.setName("DISTRICT OVERVIEW - IMPACT ALERTS");
					inputVO.setDistrictId(locationValue);	
				}else if(resultType != null && resultType.trim().equalsIgnoreCase("Constituency")){
					resultVO.setName("CONSTITUENCY OVERVIEW - IMPACT ALERTS");
					inputVO.setConstituencyId(locationValue);
				}else if(resultType != null && resultType.trim().equalsIgnoreCase("Parliament")){
					resultVO.setName("PARLIAMENT OVERVIEW - IMPACT ALERTS");
					
					if(locationValue != null && locationValue.longValue() > 0L){
						constituencyList = parliamentAssemblyDAO.getConstituencyIdsByParliamntId(locationValue);
					}
					inputVO.setConstituencyIds(constituencyList);
					blockType = resultType;
					resultType="Constituency";
				}
				
				List<Object[]> rtrnObjLst = alertDAO.getImpactLevelByAlertCount(inputVO,resultType,"");
				if(userAccessLevelVO.getUserAccessLevelId().longValue() == 5L && blockType != null && blockType.trim().equalsIgnoreCase("Parliament") && locationValue != null && locationValue.longValue() > 0L){
					filteredConstituencyList(rtrnObjLst,constituencyList);
			    }
				
				if(blockType != null && blockType.trim().equalsIgnoreCase("Parliament")){
					List<Long> constituencyIds = new ArrayList<Long>();
					if(rtrnObjLst != null && rtrnObjLst.size() > 0){
						for(Object[] param : rtrnObjLst){
							if(param[0] != null){
								constituencyIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
							}
						}
						parliamentList = parliamentAssemblyDAO.getParliamntIdByConsIds(constituencyIds);
						convertConstituencyDateIntoParliamentDate(rtrnObjLst,parliamentList,"overview");
					}
				}
				
				
				setLocationLevelWiseData(rtrnObjLst,locationMap,impactLevelIds,selectionType);
				
				if(locationMap != null && locationMap.size() > 0){
					 resultVO.setSubList1(new ArrayList<AlertOverviewVO>(locationMap.values()));
				}
				//Calculating overall alert
 				calculateOverAllAlert(resultVO.getSubList1());
 				//Sorting location based on sorting type
 				if(resultVO != null && resultVO.getSubList1() != null && resultVO.getSubList1().size() > 0){
 					sortListByRequiredType(resultVO.getSubList1(),sortingType);	
 				}
 		 }catch(Exception e){
			 LOG.error("Exception occured  in getDistrictOrConstituencyImpactandItsSubLevelAlert() in AlertService class ",e);  
		 }
		 return resultVO;
	 }
	public void  filteredConstituencyList(List<Object[]> rtrnObjLst,List<Long> constituencyList) {
		List<Object[]> finallist = new ArrayList<Object[]>();
	 	for (Object[] obj : rtrnObjLst) {  
	 		if(constituencyList.contains( Long.valueOf(obj[0].toString()))){
	 			finallist.add(obj);
	 		}
	 	}
	 	rtrnObjLst.clear();
	 	rtrnObjLst.addAll(finallist);
	}

	public void convertConstituencyDateIntoParliamentDate(List<Object[]> rtrnObjLst,List<Object[]> parliamentList,String detailsType){
 		try{
 			Map<Long,String> statusIdAndStatus = new HashMap<Long,String>();
 			int dataPosition=0;
 			if(detailsType != null && detailsType.trim().equalsIgnoreCase("overview")){
 				dataPosition = 3;
 			}else if(detailsType != null && detailsType.trim().equalsIgnoreCase("status")){
 				dataPosition = 4;
 				if(rtrnObjLst != null && rtrnObjLst.size() > 0){
					for(Object[] param : rtrnObjLst){
						statusIdAndStatus.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getStringValueForObject(param[3]));
					}
				}
 			}
 			
 			//create a map for parliamentId and listOfConstituencyId
 			Map<Long,List<Long>> parliamentIdAndListConstituency = new HashMap<Long,List<Long>>();
 			//create a map for parliamentIdAndName
 			Map<Long,String> parliamentIdAndNameMap = new HashMap<Long,String>();
 			List<Long> constituencyIdList = null;
 			if(parliamentList != null && parliamentList.size() > 0){
 				for(Object[] param : parliamentList){
 					parliamentIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1]));
 					constituencyIdList = parliamentIdAndListConstituency.get(commonMethodsUtilService.getLongValueForObject(param[0]));
 					if(constituencyIdList == null){
 						constituencyIdList = new ArrayList<Long>();
 						parliamentIdAndListConstituency.put(commonMethodsUtilService.getLongValueForObject(param[0]), constituencyIdList);
 					}
 					constituencyIdList.add(commonMethodsUtilService.getLongValueForObject(param[2]));
 				}
 			}
 			//create a map for constituencyId and constituency dtls
 			Map<Long,List<Object[]>> constituencyIdAndListOfThatConstituencyDtls = new HashMap<Long,List<Object[]>>();
 			List<Object[]> listOfThatConstituencyDtls = null;
 			if(rtrnObjLst != null && rtrnObjLst.size() > 0){
 				for(Object[] param : rtrnObjLst){
 					if(param[0] != null){
 						listOfThatConstituencyDtls = constituencyIdAndListOfThatConstituencyDtls.get(commonMethodsUtilService.getLongValueForObject(param[0]));
 						if(null == listOfThatConstituencyDtls){
 							listOfThatConstituencyDtls = new ArrayList<Object[]>();
 							constituencyIdAndListOfThatConstituencyDtls.put(commonMethodsUtilService.getLongValueForObject(param[0]), listOfThatConstituencyDtls);
 						}
 						listOfThatConstituencyDtls.add(param);
 					}
 				}
 			}
 			
 			// create a map for parliamentId and listOfObjectArr for constituency
 			Map<Long,List<Object[]>> parliamentIdAndListOfObjectArr = new HashMap<Long,List<Object[]>>();
 			List<Object[]> listOfConArr = null;
 			if(parliamentIdAndListConstituency != null && parliamentIdAndListConstituency.size() > 0){
 				for(Entry<Long,List<Long>> innerParam : parliamentIdAndListConstituency.entrySet()){
 					listOfConArr = new ArrayList<Object[]>();
 					for(Long conId : innerParam.getValue()){
 						listOfConArr.addAll(constituencyIdAndListOfThatConstituencyDtls.get(conId));
 					}
 					parliamentIdAndListOfObjectArr.put(innerParam.getKey(), listOfConArr);
 				}
 			}
 			//create map for parliamentId and statusId and count map
 			Map<Long,Map<Long,Long>> parliamentIdAndStatusIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
 			Map<Long,Long> statusIdAndCountMap = null;
 			if(parliamentIdAndListOfObjectArr != null && parliamentIdAndListOfObjectArr.size() > 0){
 				for(Entry<Long,List<Object[]>> innerParam : parliamentIdAndListOfObjectArr.entrySet()){
 					statusIdAndCountMap = new HashMap<Long,Long>();
 					for(Object[] param : innerParam.getValue()){
 						if(statusIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[2])) != null){
 							statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), statusIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[2])) + commonMethodsUtilService.getLongValueForObject(param[dataPosition]));
 						}else{
 							statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[dataPosition]));
 						}
 					}
 					parliamentIdAndStatusIdAndCountMap.put(innerParam.getKey(), statusIdAndCountMap);
 				}
 			}
 			// now create list of object[]
 			 			
 			rtrnObjLst.clear();
 			Object[] arr = null;
 			if(detailsType != null && detailsType.trim().equalsIgnoreCase("overview")){
 				if(parliamentIdAndStatusIdAndCountMap != null && parliamentIdAndStatusIdAndCountMap.size() > 0){
 	 				for(Entry<Long,Map<Long,Long>> outerParam : parliamentIdAndStatusIdAndCountMap.entrySet()){
 	 					for(Entry<Long,Long> innerParam : outerParam.getValue().entrySet()){
 	 						arr = new Object[4];
 	 						arr[0] = outerParam.getKey();
 	 						arr[1] = parliamentIdAndNameMap.get(outerParam.getKey());
 	 						arr[2] = innerParam.getKey();
 	 						arr[3] = innerParam.getValue();
 	 						rtrnObjLst.add(arr);
 	 					}
 	 				}
 	 			}
 			}else if(detailsType != null && detailsType.trim().equalsIgnoreCase("status")){
 				if(parliamentIdAndStatusIdAndCountMap != null && parliamentIdAndStatusIdAndCountMap.size() > 0){
 	 				for(Entry<Long,Map<Long,Long>> outerParam : parliamentIdAndStatusIdAndCountMap.entrySet()){
 	 					for(Entry<Long,Long> innerParam : outerParam.getValue().entrySet()){
 	 						arr = new Object[5];
 	 						arr[0] = outerParam.getKey();
 	 						arr[1] = parliamentIdAndNameMap.get(outerParam.getKey());
 	 						arr[2] = innerParam.getKey();
 	 						arr[3] = statusIdAndStatus.get(innerParam.getKey());
 	 						arr[4] = innerParam.getValue();
 	 						rtrnObjLst.add(arr);
 	 					}
 	 				}
 	 			}
 			}
 			
 		}catch(Exception e){
 			LOG.error("Exception occured  in convertConstituencyDateIntoParliamentDate() in AlertService class ",e); 
 		}
 	}
 	 public void sortListByRequiredType(List<AlertOverviewVO> resultList,String sortingType){
 		 try{
 			if(sortingType != null && sortingType.equalsIgnoreCase("Decending")){
 				Collections.sort(resultList, alertDescendingCountWiseSorting);
			}else if(sortingType != null && sortingType.equalsIgnoreCase("Ascending")){
				Collections.sort(resultList, alertAscendingCountWiseSorting);
			}else if(sortingType != null && sortingType.equalsIgnoreCase("AlphabeticalAscending")){
				Collections.sort(resultList, alphabeticalAscendingSort);
			}else if(sortingType != null && sortingType.equalsIgnoreCase("AlphabeticalDescending")){
				Collections.sort(resultList, alphabeticalDescendingSort);
			}else if(sortingType != null && sortingType.equalsIgnoreCase("locationIdAscendingOrder")){
				Collections.sort(resultList, locationIdAscendingOrder);	
			}else if(sortingType != null && sortingType.equalsIgnoreCase("locationIdDescendingOrder")){
				Collections.sort(resultList, locationIdDescendingOrder);	
			}
 		 }catch(Exception e){
 			LOG.error("Exception occured  in sortListByRequiredType() in AlertService class ",e);  
 		 }
 	 }
 	public static Comparator<AlertOverviewVO> alertDescendingCountWiseSorting = new Comparator<AlertOverviewVO>() {
    	public int compare(AlertOverviewVO location2, AlertOverviewVO location1) {
    	Long count2 = location2.getTotalAlertCnt();
    	Long count1 = location1.getTotalAlertCnt();
    	//descending order of percantages.
    	 return count1.compareTo(count2);
    	}
     };
     public static Comparator<AlertOverviewVO> alertAscendingCountWiseSorting = new Comparator<AlertOverviewVO>() {
     	public int compare(AlertOverviewVO location2, AlertOverviewVO location1) {
     	Long count2 = location2.getTotalAlertCnt();
     	Long count1 = location1.getTotalAlertCnt();
     	//ascending order of percantages.
     	 return count2.compareTo(count1);
     	}
      };
      public static Comparator<AlertOverviewVO> alphabeticalDescendingSort = new Comparator<AlertOverviewVO>() {
       	public int compare(AlertOverviewVO location2, AlertOverviewVO location1) {
        	String name2 = location2.getName();
       	    String name1 = location1.getName();
       	    //descending order of percantages.
       	    return name1.compareTo(name2);
       	}
       };
       public static Comparator<AlertOverviewVO> alphabeticalAscendingSort = new Comparator<AlertOverviewVO>() {
          	public int compare(AlertOverviewVO location2, AlertOverviewVO location1) {
          	String name2 = location2.getName();
          	String name1 = location1.getName();
          	//ascending order of percantages.
          	 return name2.compareTo(name1);
          	}
        };
        public static Comparator<AlertOverviewVO> locationIdAscendingOrder = new Comparator<AlertOverviewVO>() {
            	public int compare(AlertOverviewVO location2, AlertOverviewVO location1) {
            	Long id2 = location2.getId();
            	Long id1 = location1.getId();
            	//ascending order of percantages.
            	 return id2.compareTo(id1);
            	}
        };
        public static Comparator<AlertOverviewVO> locationIdDescendingOrder = new Comparator<AlertOverviewVO>() {
        	public int compare(AlertOverviewVO location2, AlertOverviewVO location1) {
        	Long id2 = location2.getId();
        	Long id1 = location1.getId();
        	//descending order of percantages.
        	 return id1.compareTo(id2);
        	}
    };
    /*
   	 * Author :Santosh
   	 * Date : 18-03-2017
   	 * @Description : This service is used to get corp ghmc impact and its sub level alert count;
   	 */
 	public AlertOverviewVO getCorpGMCAlert(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertTypeId, Long editionId,List<Long> alertStatusIds,Long districtId,String selectionType){
		 AlertOverviewVO resultVO = new AlertOverviewVO();
	     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	     Date fromDate=null;
	     Date toDate = null;
		 try{
			     AlertInputsVO inputVO = new AlertInputsVO();
			     if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.length() > 0l && toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
				   fromDate = sdf.parse(fromDateStr);
				   toDate = sdf.parse(toDateStr);
				 } 
			   //Setting input parameter
			    AlertInputsVO userAccessLevelVO = getUserAccessLevelIdAndValues(activityMemberId);
	 		    inputVO.setUserAccessLevelId(userAccessLevelVO.getUserAccessLevelId());
				inputVO.setUserAccessLevelValues(userAccessLevelVO.getUserAccessLevelValues());
				inputVO.setStateId(stateId);
				inputVO.setFromDate(fromDate);
				inputVO.setToDate(toDate);
				inputVO.setType(selectionType);
 				inputVO.setImpactLevelIds(new ArrayList<Long>(impactLevelIds));
 				inputVO.setEditionList(getEditionList(editionId));
 				inputVO.setAlertStatusIds(alertStatusIds);
				inputVO.setAlertTypeId(alertTypeId);
				inputVO.setDistrictId(districtId);
				//Preparing required templated
				resultVO.setName("CORPORATION - GMC - IMPACT - ALERTS");
				List<Object[]> rtrnObjLst = alertDAO.getImpactLevelByAlertCount(inputVO,"CORPGMC","");
				List<Object[]> rtrnOtherLocationObjLst = alertDAO.getImpactLevelByAlertCount(inputVO,"","State");//getting all other data except ghmc
				setGhmcData(rtrnObjLst,resultVO,"GHMC");
				setGhmcData(rtrnOtherLocationObjLst,resultVO,"Other");
				 //Sorting
				 if(resultVO != null && resultVO.getSubList1() != null && resultVO.getSubList1().size() > 0){
					 Collections.sort(resultVO.getSubList1(), descendingSortingAlertCount);
				 }
		 }catch(Exception e){
			 LOG.error("Exception occured  in getCorpGMCAlert() in AlertService class ",e);  
		 }
		 return resultVO;
	 }
 	  public void setGhmcData(List<Object[]> objList,AlertOverviewVO resultVO,String type){
 		  try{
 			 if(objList != null && objList.size() > 0){
				  for(Object[] param:objList){
					  AlertOverviewVO corpgmcVO = new AlertOverviewVO();
					  if(type.equalsIgnoreCase("Other")){
						  corpgmcVO.setId(0l);
						  corpgmcVO.setName("OtherLocations"); 
					  }else{
						  corpgmcVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						  corpgmcVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));  
					  }
					  corpgmcVO.setAlertCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					  if(resultVO.getSubList1() == null){
						  resultVO.setSubList1(new ArrayList<AlertOverviewVO>());
					  }
					  resultVO.getSubList1().add(corpgmcVO);
					  resultVO.setAlertCount(resultVO.getAlertCount()+corpgmcVO.getAlertCount());
				  }
			 }  
 		  }catch(Exception e){
 			 LOG.error("Exception occured  in setGhmcData() in AlertService class ",e); 
 		  }
 	  }
 	  public static Comparator<AlertOverviewVO> descendingSortingAlertCount = new Comparator<AlertOverviewVO>() {
 	     	public int compare(AlertOverviewVO location2, AlertOverviewVO location1) {
 	     	Long count2 = location2.getAlertCount();
 	     	Long count1 = location1.getAlertCount();
 	     	//ascending order of percantages.
 	     	 return count1.compareTo(count2);
 	     	}
 	      };
     public void setLocationLevelWiseData(List<Object[]> objList,Map<Long,AlertOverviewVO> locationMap,List<Long> impactLevelIds,String resultType){
    	 try{
    		 List<AlertOverviewVO> impactLevelList = null;
    		 if(objList != null && objList.size() > 0){
    			  if(resultType.equalsIgnoreCase("impactScopeWise")){
    				   impactLevelList = getAlertImpactLevelWiseLocationSubTemplate(impactLevelIds);
    			  }else if(resultType.equalsIgnoreCase("locationWise")){
    				  impactLevelList = getAlertImpactLocationWiseLocationSubTemplate(impactLevelIds);
    			  }
    			
    			 for(Object[] param:objList){
    				  Long locationLevelId = commonMethodsUtilService.getLongValueForObject(param[2]);
    				  Long alertCnt = commonMethodsUtilService.getLongValueForObject(param[3]);
    				  if(param[0] != null){
    					   AlertOverviewVO locationVO = locationMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
	       				   if(locationVO == null){
	       					   locationVO = new AlertOverviewVO();
	       					   locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[0])); 
	       					   locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
	       					   locationVO.setSubList1(getAlertImpactLevel(impactLevelList));
	       					   locationMap.put(locationVO.getId(), locationVO);
	       				   }
	       				   setAlertAlertCount(locationVO,locationLevelId,alertCnt,resultType);  
    				  }
    			 }
    		 }
    	 }catch(Exception e){
    		 LOG.error("Exception occured  in setLocationLevelWiseData() in AlertService class ",e);
    	 }
     }
     public List<AlertOverviewVO> getAlertImpactLevel(List<AlertOverviewVO> locationList){
    	 List<AlertOverviewVO> resultList =  new ArrayList<AlertOverviewVO>();
    	 try{
    		 if(locationList != null && locationList.size() > 0){
    			 for(AlertOverviewVO vo:locationList){
    				 AlertOverviewVO locationVO = new AlertOverviewVO();
    				 locationVO.setId(vo.getId());
    				 locationVO.setName(vo.getName());
    				 resultList.add(locationVO);
    			 }
    		 }
    	 }catch(Exception e){
    		 LOG.error("Exception occured  in getAlertLocation() in AlertService class ",e);
    	 }
    	 return resultList;
     }
     public List<Long> getEditionList(Long editionId){
		 List<Long> editionList = new ArrayList<Long>();
		 try{
			  	if(editionId != null){
	 	 			if(editionId.longValue() == 1L){
	 	 				editionList.add(editionId);
	 	 			}else if(editionId.longValue() == 2L){
	 	 				editionList.add(editionId);
	 	 				editionList.add(3L);
	 	 			}
	 	 		}
		 }catch(Exception e){
			 LOG.error("Exception occured  in getEditionList() in AlertService class ",e); 	 
		 }
		 return editionList;
	 }
	public AlertInputsVO getUserAccessLevelIdAndValues(Long activityMemberId){
		 Set<Long> locationValues = new HashSet<Long>(0);
	     Long locationAccessLevelId =0l;
	     AlertInputsVO resultVO = new AlertInputsVO();
		try{
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
				resultVO.setUserAccessLevelId(locationAccessLevelId);	
				resultVO.setUserAccessLevelValues(locationValues);
		}catch(Exception e){
			LOG.error("Exception occured  in getUserAccessLevelIdAndValues() in AlertService class ",e);
		}
		return resultVO;
	}
 	public List<AlertOverviewVO> getAlertImpactLevelWiseLocationSubTemplate(List<Long> impactLevelIds){
		 List<AlertOverviewVO> locationLevelList = new ArrayList<AlertOverviewVO>(0);
		  try{
			  List<Object[]> impactScopeObjLst = null;
			  impactScopeObjLst = alertImpactScopeDAO.getAlertImpactScopeByImpactId(impactLevelIds);
			  if(impactScopeObjLst != null && impactScopeObjLst.size() > 0){
				  for(Object[] param:impactScopeObjLst){
					  Long impactLevelId = commonMethodsUtilService.getLongValueForObject(param[0]);
					  if(impactLevelId == 5l || impactLevelId == 12l){ /* MANDAL/MUNICIPALITY */ 
						 AlertOverviewVO locationVO = getImpactLevelMtchVO(locationLevelList,5l);
						  if(locationVO == null){
							     locationVO = new AlertOverviewVO();
								 locationVO.setId(5l);
								 locationVO.setName("MANDAL/MUNICIPALITY");
								 locationLevelList.add(locationVO);
						  }
					  }else if(impactLevelId == 7l || impactLevelId == 9l || impactLevelId ==6l){/* VILLAGE/WARD/PANCHAYAT */
						  AlertOverviewVO locationVO = getImpactLevelMtchVO(locationLevelList,7l);
						   if(locationVO == null){
							      locationVO = new AlertOverviewVO();
								  locationVO.setId(7l);
								  locationVO.setName("VILLAGE/WARD/PANCHAYAT");
								  locationLevelList.add(locationVO);
						   }
					  }else{
						AlertOverviewVO locationVO = new AlertOverviewVO();
						locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						locationLevelList.add(locationVO);
					  }
				  }
			  }
		  }catch(Exception e){
			 e.printStackTrace();
			LOG.error("Error occured getAlertImpactLevelWiseLocationSubTemplate() method of AlertService{}");	  
		  }
		  return locationLevelList;
	}
 	public AlertOverviewVO getImpactLevelMtchVO(List<AlertOverviewVO> impactLevelList,Long impactLevelId){
 		try{
 			if(impactLevelList == null || impactLevelList.size() == 0)
 				return null;
 			for(AlertOverviewVO impactLevelVO:impactLevelList){
 				if(impactLevelVO.getId().equals(impactLevelId)){
 					return impactLevelVO;
 				}
 			}
 		}catch(Exception e){
 			LOG.error("Error occured getLocationLevelMatchVO() method of AlertService{}",e);	
 		}
 		return null;
 	}
     public void setAlertAlertCount(AlertOverviewVO resultVO,Long impactLevelId,Long alertCnt,String resultType){
  		try{
  			   if(resultType.equalsIgnoreCase("impactScopeWise")){
	  				 if(impactLevelId == 5l || impactLevelId==12l){/* MANDAL/MUNICIPALITY */ 
	  	 				impactLevelId = 5l;
	  				 }else if(impactLevelId == 7l || impactLevelId==9l || impactLevelId==6l){/* VILLAGE/WARD/PANCHAYAT */
	  					impactLevelId = 7l;
	  				}  
  			   }else if(resultType.equalsIgnoreCase("locationWise")){
  				 if(impactLevelId == 5l || impactLevelId == 7l){ /* MANDAL/MUNICIPALITY */ 
  					impactLevelId = 5l;
				  }else if(impactLevelId == 6l || impactLevelId == 8l || impactLevelId == 11l){/* VILLAGE/WARD/Hamlet */
					  impactLevelId = 6l;
				  }
  			  }
 	 			
 			    AlertOverviewVO matchVO = getImpactLevelMtchVO(resultVO.getSubList1(),impactLevelId);
 				 if(matchVO != null){
 					matchVO.setAlertCount(matchVO.getAlertCount()+alertCnt); 
 				 }
  		}catch(Exception e){
  			 LOG.error("Exception occured  in setAlertCnt() in AlertService class ",e); 	
  		}
  	}
    public void calculateOverAllAlert(List<AlertOverviewVO> resultList){
    	 try{
    		 if(resultList != null && resultList.size() > 0){
    			 for(AlertOverviewVO vo:resultList){
    				 if(vo.getSubList1() != null && vo.getSubList1().size() > 0){
    					 for(AlertOverviewVO impactVO:vo.getSubList1()){
    						 vo.setTotalAlertCnt(vo.getTotalAlertCnt()+impactVO.getAlertCount());
    					 }
    				 }
    			 }
    		 }
    	 }catch(Exception e){
    		 LOG.error("Exception occured  in calculateOverAllAlert() in AlertService class ",e); 
    	 }
     }
     /**
      * @param Long parentActivityMemberId
      * @param List<Long> childUserTypeIds
      * @param String reportType
      * @param Long stateId
      * @param String fromDateStr
      * @param String toDateStr
      * @param Long activityMemberId
      * @param Long userTypeId
      * @return ToursBasicVO
      * @author Santosh 
      * @Description :This service is used to get alert count based candidate on candiate access location. 
      *  @since 15-MARCH-2017
      */
    public List<UserTypeVO> getAlertByUserTypeBasedOnAccessLevel(Long parentActivityMemberId,List<Long> childUserTypeIds,String reportType,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertTypeId, Long editionId,List<Long> alertStatusIds){

    		List<UserTypeVO> resultList = new ArrayList<UserTypeVO>(0);
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    		Date fromDate=null;
    		Date toDate = null;
    		try{
    			  if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.length() > 0l && toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
   				   fromDate = sdf.parse(fromDateStr);
   				   toDate = sdf.parse(toDateStr);
   				  } 
    			  
    			  //Setting input parameter
    			    AlertInputsVO inputVO = new AlertInputsVO();
	  			    inputVO.setStateId(stateId);
	  				inputVO.setFromDate(fromDate);
	  				inputVO.setToDate(toDate);
	  				inputVO.setImpactLevelIds(impactLevelIds);
	  				inputVO.setEditionList(getEditionList(editionId));
	  				inputVO.setAlertStatusIds(alertStatusIds);
	  				inputVO.setAlertTypeId(alertTypeId);
	  			
	  				List<Object[]> rtrnAlrtStatusObjLst = alertDepartmentStatusDAO.getAlertStatusByAlertStatusId(alertStatusIds,alertTypeId);
	  				
    			   //calling generic method to get childActivityMembers and there location level and values
    			  ActivityMemberVO activityMemberVO=null;
    			  Map<Long,UserTypeVO> childActivityMembersMap=null;
    			  Map<Long,Set<Long>> locationLevelIdsMap=null;
    			  Map<String,String>     nameForLocationMap=null;
    			  Map<String,Long> totalAlertMap = new HashMap<String, Long>(0);
    			  Map<String,Long> assignedAletMap = new HashMap<String, Long>();
    			  Map<String,Long> involveAletMap = new HashMap<String, Long>();
    			  Map<String,Map<Long,Long>> aletStatusMap = new HashMap<String, Map<Long,Long>>(0);
    			  
    			  if(reportType != null && reportType.equalsIgnoreCase("selectedUserType")){
    				  activityMemberVO = coreDashboardGenericService.getRequiredSubLevelActivityMembersDetails(parentActivityMemberId,childUserTypeIds);
    				  childActivityMembersMap= activityMemberVO.getActivityMembersMap();
    				  locationLevelIdsMap= activityMemberVO.getLocationLevelIdsMap();
    			  }else if(reportType != null && reportType.equalsIgnoreCase("directChild")){
    				  if(childUserTypeIds != null && childUserTypeIds.size()>0){
    					   activityMemberVO = coreDashboardGenericService.getDirectChildActivityMemberCommitteeDetails(parentActivityMemberId,childUserTypeIds.get(0));//activityMemerId,userTypeId
    				  }
    				   childActivityMembersMap = activityMemberVO.getActivityMembersMap();
    				   locationLevelIdsMap = activityMemberVO.getLocationLevelIdsMap();
    			  }
    			   
    			  if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
    				  nameForLocationMap = coreDashboardGenericService.getLocationNamesByLocationIds(locationLevelIdsMap);
    			  }
    			  //Setting Location name
				  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
				      for(UserTypeVO vo:childActivityMembersMap.values()){
				    	  for(Long locationValueId:vo.getLocationValuesSet()){
				    		  String key = vo.getLocationLevelId()+"_"+locationValueId;
				    		  if(vo.getLocationName() == null || vo.getLocationName().isEmpty()){
				    			  vo.setLocationName(nameForLocationMap.get(key));
							   }else{
								   vo.setLocationName(vo.getLocationName()+","+ nameForLocationMap.get(key) );  
							   }
				    	  }
				      }
			      }    
    		 	    if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
    					  for(Entry<Long,Set<Long>> entry:locationLevelIdsMap.entrySet()){
    						    Long userAccessLevelId =entry.getKey();	
    						    Set<Long> userAccessLevelValues = entry.getValue();
    						    if(userAccessLevelId.longValue() == 4l){
    						    	userAccessLevelId = 5l;
    						    	Set<Long> constituencyIds = new HashSet<Long>(0);
    						     	List<Object[]> parliamentAssemlyIdObjLst = parliamentAssemblyDAO.getAssemblyConstituencyforByPaliament(new ArrayList<Long>(userAccessLevelValues));
        						    convertParliamentToConstituency(parliamentAssemlyIdObjLst,childActivityMembersMap,constituencyIds);
    						    	userAccessLevelValues.clear();
    						    	userAccessLevelValues.addAll(constituencyIds);
    						    }
    						    inputVO.setUserAccessLevelId(userAccessLevelId);
    						    inputVO.setUserAccessLevelValues(userAccessLevelValues);
    						    
    						    if((userAccessLevelId.longValue() ==IConstants.ASSEMBLY_LEVEl_ACCESS_ID)){//4,5 is two activityMemberId of two general secretary who are showing district impact also for them
    				                if(reportType.equalsIgnoreCase("selectedUserType")){
    				                   	if(childUserTypeIds.get(0).longValue() != 3l){//GS
 	    				         			impactLevelIds.remove(2l);//We are not display 2 district,8 ghmc impact level for those designation candidate who has constituency access
 	    				        			impactLevelIds.remove(8l);
 	    				                }	
    				                   	if(commonMethodsUtilService.isMapValid(childActivityMembersMap)){
    				                   		for (Map.Entry<Long,UserTypeVO> entryUser : childActivityMembersMap.entrySet()) {
    				                   			inputVO.getTdpCadreIds().add(entryUser.getValue().getTdpCadreId());
    				                   			inputVO.setType("selectedUserType");
											}
    				                   	}
    				                }
    						       if(reportType.equalsIgnoreCase("directChild")){
    				                		impactLevelIds.remove(2l);//We are not display 2 district,8 ghmc impact level for those designation candidate who has constituency access
 	    				        			impactLevelIds.remove(8l); 
    				                 }
    				    		}
    						      if(impactLevelIds.size() == 0){
    						    	   impactLevelIds.add(0l);
    						       }
    						    
    				    		   List<Object[]> returnTotalAlertObjList = alertDAO.getLocationWiseAssignedAndInvolveAlertCnt(inputVO, "Total",null);
    						       List<Object[]> returnInvolveAlertObjList = alertDAO.getLocationWiseAssignedAndInvolveAlertCnt(inputVO, "Involve",null);
    						       List<Object[]> returnAssignedAlertObjList = alertDAO.getLocationWiseAssignedAndInvolveAlertCnt(inputVO, "Assigned",null);
    						       List<Object[]> returnStatusWiseAlertObjList = alertDAO.getLocationWiseAssignedAndInvolveAlertCnt(inputVO, "Total","Status");
    							   setLocationWiseAlertCnt(returnTotalAlertObjList,totalAlertMap,userAccessLevelId);
    							   setLocationWiseAlertCnt(returnInvolveAlertObjList,involveAletMap,userAccessLevelId);
    							   setLocationWiseAlertCnt(returnAssignedAlertObjList,assignedAletMap,userAccessLevelId);
    							   setStatusWiseAlertCnt(returnStatusWiseAlertObjList,aletStatusMap,userAccessLevelId);
    					   }  
    				}  
    			  //Pushing Required Count
    			 	  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
    				      for(UserTypeVO vo:childActivityMembersMap.values()){
    				    	  setAlertStatusList(vo,rtrnAlrtStatusObjLst);
    				    	   for(Long locationValueId:vo.getLocationValuesSet()){
    				    		   
    				    		   String key = vo.getLocationLevelId()+"-"+locationValueId; 
    				    		          if(totalAlertMap.get(key) != null){
    				    		        	  vo.setTotalCount(vo.getTotalCount()+totalAlertMap.get(key));	  
    				    		          }
    				    				  if(assignedAletMap.get(key) != null){
    				    					  vo.setPositiveCount(vo.getPositiveCount()+assignedAletMap.get(key)); //Assigned Count	  
    				    				  }
    				    				  if(involveAletMap.get(key) != null){
    				    					  vo.setNegativeCount(vo.getNegativeCount()+involveAletMap.get(key)); //involve count	  
    				    				  }
    				    				 if(aletStatusMap != null && aletStatusMap.size() > 0){
    				    					 Map<Long,Long> statusMap = aletStatusMap.get(key);
    				    					  if(statusMap != null && statusMap.size() > 0){
    				    						  for(Entry<Long,Long> entry:statusMap.entrySet()){
    				    							  UserTypeVO matchVO = getAlertStatusMatchVO(vo.getSubList(), entry.getKey()); 
    				    							   if(matchVO != null){
    				    								   matchVO.setTotalCount(matchVO.getTotalCount()+ entry.getValue()); 
    				    							   }
    				    						  }
    				    					  }
    				    					 
    				    				 }
    				    	   }
    				      }
    			   }  
    			
    				//Calculating percentage
    			  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
    				      for(UserTypeVO vo:childActivityMembersMap.values()){
    				    	  if(vo.getSubList() != null && vo.getSubList().size() > 0){
    				    		  for(UserTypeVO statusVO:vo.getSubList()){
    				    			  statusVO.setPositivePercentage(calculatePercantage(statusVO.getTotalCount(),vo.getTotalCount()));
    				    			/*  if(statusVO.getId().longValue() == 4l)//Completed Status
    				    			  {
    				    				  vo.setPositivePercentage(vo.getPositivePercentage()+statusVO.getPositivePercentage());  
    				    			  }*/
    				    		  }
    				    	  }
    				      }
    			  }
    			  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
    				  resultList.addAll(childActivityMembersMap.values());
    			  }
    			  if(resultList != null && resultList.size() > 0)
    			  {
    				  Collections.sort(resultList, alertCompletedPer);
    			  }
    		}catch(Exception e){
    			LOG.error("Error occured at getSelectedChildTypeMembersForTrainingProgram() in CoreDashboardMainService ",e);
    		}
    		return resultList;	
    		}
    
    public static Comparator<UserTypeVO> alertCompletedPer = new Comparator<UserTypeVO>() {
    	public int compare(UserTypeVO member2, UserTypeVO member1) {

    	Long count2 = member2.getTotalCount();
    	Long count1 = member1.getTotalCount();
    	//ascending order of percantages.
    	 return count2.compareTo(count1);
    	}
     }; 
    public void setAlertStatusList(UserTypeVO VO,List<Object[]> objList){
    	try{
    		if(objList != null && objList.size() > 0){
    			for(Object[] param:objList){
    				UserTypeVO statusVO = new UserTypeVO();
    				statusVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
    				statusVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
    				if(VO.getSubList() == null){
    					VO.setSubList(new ArrayList<UserTypeVO>());
    				}
    				VO.getSubList().add(statusVO);
    			}
    		}
    	}catch(Exception e){
    		LOG.error("Error occured at setAlertStatusList() in CoreDashboardMainService ",e);	
    	}
    }
    public UserTypeVO getAlertStatusMatchVO(List<UserTypeVO> statusList,Long statusId){
    	try{
    		if(statusList == null || statusList.size() == 0)
    			return null;
    		for(UserTypeVO statusVO:statusList){
    			if(statusVO.getId().equals(statusId)){
    				return statusVO;
    			}
    		}
    	}catch(Exception e){
    		LOG.error("Error occured at getAlertStatusMatchVO() in CoreDashboardMainService ",e);	
    	}
    	return null;
    }
    public void setLocationWiseAlertCnt(List<Object[]> ojbList, Map<String,Long> resultMap,Long userAccessLevelId){
    	try{
    		if(ojbList != null && ojbList.size() > 0){
    			for(Object[] param:ojbList){
    				 String key = userAccessLevelId+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
    				 resultMap.put(key, commonMethodsUtilService.getLongValueForObject(param[2]));
    			}
    		}
    	}catch(Exception e){
    		LOG.error("Error occured at setLocationWiseAlertCnt() in CoreDashboardMainService ",e);	
    	}
    }
    public void setStatusWiseAlertCnt(List<Object[]> ojbList, Map<String,Map<Long,Long>> resultMap,Long userAccessLevelId){
    	try{
    		if(ojbList != null && ojbList.size() > 0){
    			for(Object[] param:ojbList){
    				 String key = userAccessLevelId+"-"+commonMethodsUtilService.getStringValueForObject(param[0]);
    				 Map<Long,Long> statusMap = resultMap.get(key);
    				  if(statusMap == null){
    					  statusMap = new HashMap<Long, Long>();
    					  resultMap.put(key, statusMap);  
    				  }
    				  statusMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[3]));
    			}
    		}
    	}catch(Exception e){
    		LOG.error("Error occured at setLocationWiseAlertCnt() in CoreDashboardMainService ",e);	
    	}
    }
    public void convertParliamentToConstituency(List<Object[]> objList,Map<Long,UserTypeVO> childActivityMembersMap,Set<Long> constituencyIds){
    	try{
    		Map<Long,Set<Long>> paraliamentConstituencyMap = new HashMap<Long, Set<Long>>(0);
    		if(objList != null && objList.size() > 0){
    			for(Object[] param:objList){
    				Set<Long> constituencyIdSet = paraliamentConstituencyMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
    				 if(constituencyIdSet == null){
    					 constituencyIdSet = new HashSet<Long>();
    					 paraliamentConstituencyMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), constituencyIdSet);
    				 }
    				 constituencyIdSet.add(commonMethodsUtilService.getLongValueForObject(param[1]));
    				 constituencyIds.add(commonMethodsUtilService.getLongValueForObject(param[1]));
    			}
    		}
    		  if(childActivityMembersMap != null && childActivityMembersMap.size() > 0){
			      for(UserTypeVO vo:childActivityMembersMap.values()){
			    	   if(vo.getLocationLevelId().longValue() == 4l){
				    	    Set<Long> overAllConstituencyIds = new HashSet<Long>(0);
			    	        for(Long locationValueId:vo.getLocationValuesSet()){
			    		    	 Set<Long> constituencyIdSet = paraliamentConstituencyMap.get(locationValueId);
			    		    	 overAllConstituencyIds.addAll(constituencyIdSet);
			    		     }
			    	        vo.setLocationLevelId(5l);
			    	        vo.getLocationValuesSet().clear();
			    	        vo.getLocationValuesSet().addAll(overAllConstituencyIds);
			    	   }
			      }
		   }  
    	}catch(Exception e){
    		LOG.error("Error occured at convertParliamentToConstituency() in CoreDashboardMainService ",e);	
    	}
    }
    /*
     * Santosh (non-Javadoc)
     * @see com.itgrids.partyanalyst.service.IAlertService#getDirectChildMemberAlertStatusWise(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.util.List, java.lang.Long, java.lang.Long, java.util.List)
     */
    public List<AlertOverviewVO> getDirectChildMemberAlertStatusWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,List<Long> impactLevelIds,Long alertTypeId, Long editionId,List<Long> alertStatusIds){
    	List<AlertOverviewVO> resultList = new ArrayList<AlertOverviewVO>(0);
    	Map<Long,AlertOverviewVO> locationMap = new HashMap<Long, AlertOverviewVO>(0);
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fromDate=null;
		Date toDate = null;
    	try{
    		 AlertInputsVO inputVO = new AlertInputsVO();
		     if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.length() > 0l && toDateStr != null && !toDateStr.isEmpty() && toDateStr.length() > 0){
			    fromDate = sdf.parse(fromDateStr);
			    toDate = sdf.parse(toDateStr);
			 } 
		   //Setting input parameter
		    AlertInputsVO userAccessLevelVO = getUserAccessLevelIdAndValues(activityMemberId);
 		    inputVO.setUserAccessLevelId(userAccessLevelVO.getUserAccessLevelId());
			inputVO.setUserAccessLevelValues(userAccessLevelVO.getUserAccessLevelValues());
			inputVO.setActivityMemerId(activityMemberId);
			inputVO.setStateId(stateId);
			inputVO.setFromDate(fromDate);
			inputVO.setToDate(toDate);
			inputVO.setImpactLevelIds(impactLevelIds);
			inputVO.setEditionList(getEditionList(editionId));
			inputVO.setAlertStatusIds(alertStatusIds);
			inputVO.setAlertTypeId(alertTypeId);
			
			if((userAccessLevelVO.getUserAccessLevelId().longValue() ==IConstants.ASSEMBLY_LEVEl_ACCESS_ID)){//4,5 is two activityMemberId of two general secretary who are showing district impact also for them
                if(activityMemberId.longValue() != 4l && activityMemberId.longValue()!=5l){
         			impactLevelIds.remove(2l);//We are not display 2 district,8 ghmc impact level for those designation candidate who has constituency access
        			impactLevelIds.remove(8l);
                }
    		}
			if(impactLevelIds.size() == 0){
				impactLevelIds.add(0l);                          
			}
			//DAO Calls
			List<Object[]> statusObjLst = alertDepartmentStatusDAO.getAlertStatusByAlertStatusId(alertStatusIds,alertTypeId);
			List<Object[]> rtrnTotalAlertObjList = alertDAO.getLocationAndImapctLevelWiseAssignedAndInvolveAlertCnt(inputVO, "Total");
			List<Object[]> rtrnAssignedAlertObjList = alertDAO.getLocationAndImapctLevelWiseAssignedAndInvolveAlertCnt(inputVO, "Assigned");
			List<Object[]> rtrnInvolveAlertObjList = alertDAO.getLocationAndImapctLevelWiseAssignedAndInvolveAlertCnt(inputVO, "Involve");
			setRequiredAlertDataLocationWise(rtrnTotalAlertObjList,statusObjLst,locationMap,impactLevelIds,activityMemberId,userAccessLevelVO.getUserAccessLevelId(),"Total");
			setRequiredAlertDataLocationWise(rtrnAssignedAlertObjList,statusObjLst,locationMap,impactLevelIds,activityMemberId,userAccessLevelVO.getUserAccessLevelId(),"Assigned");
			setRequiredAlertDataLocationWise(rtrnInvolveAlertObjList,statusObjLst,locationMap,impactLevelIds,activityMemberId,userAccessLevelVO.getUserAccessLevelId(),"Involve");
    	     
			if(locationMap != null && locationMap.size() > 0){
				resultList.addAll(locationMap.values());
			}
			//Calculating OverAll Alert Count
			if(resultList != null && resultList.size() > 0){
				for(AlertOverviewVO locationVO:resultList){
					if(locationVO.getSubList1() != null && locationVO.getSubList1().size() > 0){
						for(AlertOverviewVO impactLevelVO:locationVO.getSubList1()){
							if(impactLevelVO.getStatusList() != null && impactLevelVO.getStatusList().size() > 0){
								for(AlertOverviewVO statusVO:impactLevelVO.getStatusList()){
									impactLevelVO.setAlertCount(impactLevelVO.getAlertCount()+statusVO.getAlertCount());
								}
							}
							locationVO.setAlertCount(locationVO.getAlertCount()+impactLevelVO.getAlertCount());
						}
					}
				}
			}
    	}catch(Exception e){
    		LOG.error("Error occured at getDirectChildMemberAlertStatusWise() in CoreDashboardMainService ",e);	
    	}
    	return resultList;
    }
    public void setRequiredAlertDataLocationWise(List<Object[]> objList,List<Object[]> objStatusList,Map<Long,AlertOverviewVO> locationMap,List<Long> impactLevelIds,Long activityMemberId,Long userAccessLevelId,String type){
    	try{
    		if(objList != null && objList.size() > 0){
    			for(Object[] param:objList){
    				//0:locationId,1:locationValue,2:impactLevelId,3:statusId,4:alertCnt
    				AlertOverviewVO locationVO = locationMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
    				 if(locationVO == null){
    					 locationVO = new AlertOverviewVO(); 
    					 locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
    					 locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
    					 locationVO.setSubList1(getImpactLevelRoleWise(objStatusList,impactLevelIds,activityMemberId,userAccessLevelId));
    					 locationMap.put(locationVO.getId(), locationVO);
    				 }
    				 Long impactLevelId = commonMethodsUtilService.getLongValueForObject(param[2]);
    				 if(impactLevelId == 5l || impactLevelId==12l){/* MANDAL/MUNICIPALITY */ 
    	 	 				impactLevelId = 5l;
    	 			 }else if(impactLevelId == 7l || impactLevelId==9l || impactLevelId==6l){/* VILLAGE/WARD/PANCHAYAT */
    	 					impactLevelId = 7l;
    	 			 }
    				 AlertOverviewVO impactLevelMatchVO = getImpactLevelMtchVO(locationVO.getSubList1(),impactLevelId);
    				 if(impactLevelMatchVO != null){
    					 AlertOverviewVO matchVO = getMatchVO(impactLevelMatchVO.getStatusList(), commonMethodsUtilService.getLongValueForObject(param[3]));
        				 if(matchVO != null){
        					 if(type.equalsIgnoreCase("Total")){
        						 matchVO.setAlertCount(matchVO.getAlertCount()+commonMethodsUtilService.getLongValueForObject(param[4]));	 
        					 }else if(type.equalsIgnoreCase("Assigned")){
        						 matchVO.setAssignedAlertCnt(matchVO.getAssignedAlertCnt()+commonMethodsUtilService.getLongValueForObject(param[4])); 
        					 }else if(type.equalsIgnoreCase("Involve")){
        						 matchVO.setInvolveAlertCnt(matchVO.getInvolveAlertCnt()+commonMethodsUtilService.getLongValueForObject(param[4]));	 
        					 }
        					 
        				 }	 
    				 }
    			}
    		}
    	}catch(Exception e){
    		LOG.error("Error occured at setRequiredAlertDataLocationWise() in CoreDashboardMainService ",e);	
    	}
    }
    public List<AlertOverviewVO> getImpactLevelRoleWise(List<Object[]> objStatusList,List<Long> impactLevelIds,Long activityMemberId,Long userAccessLevelId){
    	try{
    		List<AlertOverviewVO> impactLevelLst = getAlertImpactLevelWiseLocationSubTemplate(impactLevelIds);
    		if(impactLevelLst != null && impactLevelLst.size() > 0){
    			for(AlertOverviewVO impactVO:impactLevelLst){
    				impactVO.getStatusList().addAll(getAlrtStatus(objStatusList));
    			}
    		}
    		return impactLevelLst;
    	}catch(Exception e){
    		LOG.error("Error occured at getImpactLevelRoleWise() in CoreDashboardMainService ",e);
    	}
    	return null;
    }
    /*
     * Santosh (non-Javadoc)
     * @see com.itgrids.partyanalyst.service.IAlertService#getStateOrGHMCImpcatLevelAlertCntPublicationWise(java.lang.Long, java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.lang.Long, java.lang.Long, java.util.List)
     */
    public AlertOverviewVO getStateOrGHMCImpcatLevelAlertCntPublicationWise(Long activityMemberId,String fromDateStr, String toDateStr, Long stateId,List<Long> scopeIdList,Long alertTypeId,Long editionId, List<Long> alertStatusIds,Long disctrictId){
		 LOG.info("Entered in getStateOrGHMCImpcatLevelAlertCntPublicationWise() method of AlertService{}");
	     AlertOverviewVO resultVO = new AlertOverviewVO();
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
			if(alertTypeId > 0l){
				List<Object[]> statusObjLst = alertDepartmentStatusDAO.getAlertStatusByAlertStatusId(alertStatusIds,alertTypeId);
				setAlertStatusByAlertType(statusObjLst,alertStatusIds);
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
			List<Object[]> rtrnTvChannelArtcntObjLst = alertDAO.getStateOrGHMCImpcatLevelAlertCntPublicationWise(fromDate, toDate, stateId, scopeIdList, "TvChannel", userAccessLevelId, userAccessLevelValues, alertTypeList, editionList, alertStatusIds,disctrictId);
			List<Object[]> rtrnTvNwsPprArtcntObjLst = alertDAO.getStateOrGHMCImpcatLevelAlertCntPublicationWise(fromDate, toDate, stateId, scopeIdList, "NewsPaper", userAccessLevelId, userAccessLevelValues, alertTypeList, editionList, alertStatusIds,disctrictId);
			setImpactLevelWisePublicationAlert(rtrnTvChannelArtcntObjLst,resultVO,"TvChannel");
			setImpactLevelWisePublicationAlert(rtrnTvNwsPprArtcntObjLst,resultVO,"NewsPaper");
			
			//Calculation location wise overAll Alert Cnt.
			if(resultVO.getSubList1() != null && resultVO.getSubList1().size() > 0){
				for(AlertOverviewVO publicationVO:resultVO.getSubList1()){
					resultVO.setAlertCnt(resultVO.getAlertCnt()+publicationVO.getAlertCnt());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getStateOrGHMCImpcatLevelAlertCntPublicationWise() method of AlertService{}");
		}
		return resultVO;
	}
    public void setImpactLevelWisePublicationAlert(List<Object[]> objList,AlertOverviewVO resultVO,String publicationType){
 		try{
 			if(objList != null && objList.size() > 0){
 				for(Object[] param:objList){
 					   AlertOverviewVO locationVO = new AlertOverviewVO();
 					    String publicationIdStr = commonMethodsUtilService.getStringValueForObject(param[0]);
	 					 if(publicationType.equalsIgnoreCase("TvChannel")){
	  						publicationIdStr = "1"+publicationIdStr;
	  					 }else if(publicationType.equalsIgnoreCase("NewsPaper")){
	  						publicationIdStr = "2"+publicationIdStr;
	  					 }
  						locationVO.setId(Long.valueOf(publicationIdStr));
 						locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
 						locationVO.setAlertCnt(commonMethodsUtilService.getLongValueForObject(param[2]));
 						if(resultVO.getSubList1() == null){
 							resultVO.setSubList1(new ArrayList<AlertOverviewVO>());	
 						}
 						resultVO.getSubList1().add(locationVO);
 				}
 			}
 		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setImpactLevelWisePublicationAlert() method of AlertService{}");
 		}
 	}
    
    public List<KeyValueVO> getAlertIssueTypes(){
    	List<KeyValueVO> returnList = new ArrayList<KeyValueVO>();
    	try {
			List<AlertIssueType> list = alertIssueTypeDAO.getAll();
			if(list != null && !list.isEmpty()){
				for (AlertIssueType alertIssueType : list) {
					KeyValueVO vo = new KeyValueVO();
					vo.setId(alertIssueType.getAlertIssueTypeId());
					vo.setName(alertIssueType.getIssueType());
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured setImpactLevelWisePublicationAlert() method of AlertService{}",e);
		}
    	return returnList;
    }
    
    public List<KeyValueVO> getHamletsForPanchayat(Long panchayatId){
    	List<KeyValueVO> returnList = new ArrayList<KeyValueVO>();
    	try {
			List<Object[]> list = panchayatHamletDAO.getHamletsOfAPanchayat(panchayatId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					KeyValueVO vo = new KeyValueVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured getHamletsForPanchayat() method of AlertService{}",e);
		}
    	return returnList;
    }
    
    public List<KeyValueVO> getAlertCallerTypes(){
    	List<KeyValueVO> returnList = new ArrayList<KeyValueVO>();
    	try {
			List<Object[]> list = alertCallerTypeDAO.getAlertCallerTypesByOrder();
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					KeyValueVO vo = new KeyValueVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured getAlertCallerTypes() method of AlertService{}",e);
		}
    	return returnList;
    }
    
    public String createGrievanceAlert(final GrievanceAlertVO inputVO,final Long userId, final Map<File,String> mapFiles)
	{
		String resultStatus = null;
		try {
			resultStatus = (String) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					String rs = new String();
					DateUtilService date = new DateUtilService();
					String callCenterVersion = IConstants.CALL_CENTER_VERSION.toString();
					Long levelValue = 999999L;
					Long alertId = null;
					
					if(inputVO.getAlertId() != null && inputVO.getAlertId().longValue() > 0l){
						AlertCallerRelation alertCallerRelation = new AlertCallerRelation();

						List<Long> existingList = alertCallerDAO.checkIsExist(inputVO.getMobileNo(),inputVO.getName());
						if(!commonMethodsUtilService.isListOrSetValid(existingList)){
							AlertCaller alertCaller = new AlertCaller();
							alertCaller.setCallerName(inputVO.getName());
							alertCaller.setAddress(inputVO.getAddress());
							alertCaller.setMobileNo(inputVO.getMobileNo());
							alertCaller.setEmail(inputVO.getEmailId());
							alertCaller = alertCallerDAO.save(alertCaller);
							//alert.setAlertCallerId(alertCaller.getAlertCallerId());
							alertCallerRelation.setAlertCallerId(alertCaller.getAlertCallerId());
						}
						else{
							//alert.setAlertCallerId(existingList.get(0));
							alertCallerRelation.setAlertCallerId(existingList.get(0));
						}
						Alert alert = alertDAO.get(inputVO.getAlertId());
						alertCallerRelation.setAlertId(inputVO.getAlertId());
						alertCallerRelation.setIsDeleted("N");
						alertCallerRelation = alertCallerRelationDAO.save(alertCallerRelation);
						
						levelValue = alert.getUserAddress(). getConstituency() != null ? alert.getUserAddress().getConstituency().getConstituencyId() : 999999L;
						alertId = alert.getAlertId();
					}
					else{
						Alert alert = new Alert();
						AlertCallerRelation alertCallerRelation = new AlertCallerRelation();

						List<Long> existingList = alertCallerDAO.checkIsExist(inputVO.getMobileNo(),inputVO.getName());
						if(!commonMethodsUtilService.isListOrSetValid(existingList)){
							AlertCaller alertCaller = new AlertCaller();
							alertCaller.setCallerName(inputVO.getName());
							alertCaller.setAddress(inputVO.getAddress());
							alertCaller.setMobileNo(inputVO.getMobileNo());
							alertCaller.setEmail(inputVO.getEmailId());
							alertCaller = alertCallerDAO.save(alertCaller);
							//alert.setAlertCallerId(alertCaller.getAlertCallerId());
							alertCallerRelation.setAlertCallerId(alertCaller.getAlertCallerId());
						}
						else{
							//alert.setAlertCallerId(existingList.get(0));
							alertCallerRelation.setAlertCallerId(existingList.get(0));
						}

						alert.setAlertSeverityId(2l);
						alert.setAlertTypeId(2l);
						alert.setImpactLevelId(inputVO.getLocationLevelId());
						alert.setImpactLevelValue(inputVO.getLocationValue());
						alert.setDescription(inputVO.getDescription().toString());
						alert.setCreatedBy(userId);
						alert.setUpdatedBy(userId);
						if(inputVO.getLocationTypeStr() != null && inputVO.getLocationTypeStr().trim().equalsIgnoreCase("Rural"))
							alert.setImpactScopeId(7l);
						else if(inputVO.getLocationTypeStr() != null && inputVO.getLocationTypeStr().trim().equalsIgnoreCase("Urban"))
							alert.setImpactScopeId(13l);

						alert.setAlertStatusId(2l);// default pending status

						alert.setAlertSourceId(inputVO.getInformationSourceId());
						alert.setCreatedTime(date.getCurrentDateAndTime());
						alert.setUpdatedTime(date.getCurrentDateAndTime());
						alert.setIsDeleted("N");
						alert.setAlertCategoryId(4L);//default Manual alert
						alert.setTitle(inputVO.getAlertTitle());

						UserAddress userAddress = saveUserAddressForGrievanceAlert(inputVO);
						alert.setAddressId(userAddress.getUserAddressId());
						
						levelValue=userAddress.getConstituency() != null ? userAddress.getConstituency() .getConstituencyId():999999L;

						alert.setAlertCallerTypeId(inputVO.getCallerTypeId());
						alert.setAlertEntrySourceId(inputVO.getEntrySourceId());
						alert.setAlertIssueTypeId(inputVO.getIssueTypeId());
						alert.setAlertSourceId(5l);
						alert.setGovtDepartmentId(inputVO.getDepartmentId());
						alert.setAlertIssueSubTypeId(inputVO.getAlertIssueSubTypeId());
						alert.setIsMultiple("N");
						alert.setAlertCallCenterTypeId(inputVO.getAlertCallCenterTypeId() !=null ? inputVO.getAlertCallCenterTypeId():null);

						alert = alertDAO.save(alert);
						
						alertId = alert.getAlertId();
						alertCallerRelation.setAlertId(alert.getAlertId());
						alertCallerRelation.setIsDeleted("N");
						alertCallerRelation = alertCallerRelationDAO.save(alertCallerRelation);

						//saveAlertDocument(alert.getAlertId(),userId,mapFiles);
						saveAlertDocumentNew(alert.getAlertId(),userId,mapFiles);

						AlertComment alertComment = new AlertComment();
						alertComment.setComments(inputVO.getDescription().toString());
						alertComment.setAlertId(alert.getAlertId());
						alertComment.setInsertedTime(date.getCurrentDateAndTime());
						alertComment.setIsDeleted("N");
						alertComment.setInsertedBy(userId);
						alertComment = alertCommentDAO.save(alertComment);

						AlertTrackingVO alertTrackingVO = new AlertTrackingVO();
						alertTrackingVO.setUserId(userId);
						alertTrackingVO.setAlertCommentId(alertComment.getAlertCommentId());
						alertTrackingVO.setAlertUserTypeId(inputVO.getEntrySourceId());
						/*if(inputVO.getAssignList() != null && inputVO.getAssignList().size() > 0)
						 {
							 alertTrackingVO.setAlertStatusId(2l);
						 }else{*/
						alertTrackingVO.setAlertStatusId(2l);
						//}

						alertTrackingVO.setAlertId(alert.getAlertId());
						alertTrackingVO.setAlertTrackingActionId(IConstants.ALERT_ACTION_STATUS_CHANGE);

						saveAlertTrackingDetails(alertTrackingVO);	

						//Get Department Designation Officer Ids
						Long desigOfficerId = null;
						List<Long> designationOfficerIds = null;
						
						if(callCenterVersion.trim().equalsIgnoreCase("old"))
							designationOfficerIds = govtDepartmentDesignationOfficerDetailsNewDAO.getOldDesignationOfficerIds(inputVO.getLevelId(), inputVO.getMandalId(), inputVO.getDesignationId(), inputVO.getGovtOfficerId());
						else if(callCenterVersion.trim().equalsIgnoreCase("new"))
							designationOfficerIds = govtDepartmentDesignationOfficerDetailsNewDAO.getOldDesignationOfficerIdsNew(inputVO.getLevelId(), inputVO.getMandalId(), inputVO.getDesignationId(), inputVO.getGovtOfficerId());
						
						if(designationOfficerIds != null && !designationOfficerIds.isEmpty())
							desigOfficerId = designationOfficerIds.get(0);

						//Officer Assigning
						if(callCenterVersion.trim().equalsIgnoreCase("old")){
							AlertAssignedOfficer alertAssignedOfficer = new AlertAssignedOfficer();
							alertAssignedOfficer.setAlertId(alert.getAlertId());
							alertAssignedOfficer.setGovtDepartmentDesignationOfficerId(desigOfficerId);
							alertAssignedOfficer.setGovtOfficerId(inputVO.getGovtOfficerId() !=null ? (Long)inputVO.getGovtOfficerId():null);
							alertAssignedOfficer.setInsertedBy(userId);
							alertAssignedOfficer.setUpdatedBy(userId);
							alertAssignedOfficer.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
							alertAssignedOfficer.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
							alertAssignedOfficer.setAlertStatusId(2l);
							alertAssignedOfficer.setIsDeleted("N");
							alertAssignedOfficer.setIsApproved("Y");
							alertAssignedOfficer = alertAssignedOfficerDAO.save(alertAssignedOfficer);

							//Officer Assigning Tracking
							AlertAssignedOfficerTracking alertAssignedOfficerTracking = new AlertAssignedOfficerTracking();
							alertAssignedOfficerTracking.setAlertAssignedOfficerId(alertAssignedOfficer.getAlertAssignedOfficerId());
							alertAssignedOfficerTracking.setAlertId(alert.getAlertId());
							alertAssignedOfficerTracking.setGovtDepartmentDesignationOfficerId(desigOfficerId);
							alertAssignedOfficerTracking.setGovtOfficerId(inputVO.getGovtOfficerId());
							alertAssignedOfficerTracking.setInsertedBy(userId);
							alertAssignedOfficerTracking.setUpdatedBy(userId);
							alertAssignedOfficerTracking.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
							alertAssignedOfficerTracking.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
							alertAssignedOfficerTracking.setAlertStatusId(2l);
							//alertAssignedOfficerTracking.setGovtAlertActionTypeId(1l);
							alertAssignedOfficerTracking.setIsApproved("Y");
							//alertAssignedOfficerTracking.setAlertSeviorityId(alert.getAlertSeverityId());

							alertAssignedOfficerTracking = alertAssignedOfficerTrackingDAO.save(alertAssignedOfficerTracking);
						}
						else if(callCenterVersion.trim().equalsIgnoreCase("new")){
							AlertAssignedOfficerNew alertAssignedOfficer = new AlertAssignedOfficerNew();
							alertAssignedOfficer.setAlertId(alert.getAlertId());
							alertAssignedOfficer.setGovtDepartmentDesignationOfficerId(desigOfficerId);
							alertAssignedOfficer.setGovtOfficerId(inputVO.getGovtOfficerId() !=null ? (Long)inputVO.getGovtOfficerId():null);
							alertAssignedOfficer.setInsertedBy(userId);
							alertAssignedOfficer.setUpdatedBy(userId);
							alertAssignedOfficer.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
							alertAssignedOfficer.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
							alertAssignedOfficer.setAlertStatusId(2l);
							alertAssignedOfficer.setIsDeleted("N");
							alertAssignedOfficer.setIsApproved("Y");
							alertAssignedOfficer.setGovtDepartmentId(inputVO.getDepartmentId());
							alertAssignedOfficer = alertAssignedOfficerNewDAO.save(alertAssignedOfficer);

							//Officer Assigning Tracking
							AlertAssignedOfficerTrackingNew alertAssignedOfficerTracking = new AlertAssignedOfficerTrackingNew();
							alertAssignedOfficerTracking.setAlertAssignedOfficerId(alertAssignedOfficer.getAlertAssignedOfficerId());
							alertAssignedOfficerTracking.setAlertId(alert.getAlertId());
							alertAssignedOfficerTracking.setGovtDepartmentDesignationOfficerId(desigOfficerId);
							alertAssignedOfficerTracking.setGovtOfficerId(inputVO.getGovtOfficerId());
							alertAssignedOfficerTracking.setInsertedBy(userId);
							alertAssignedOfficerTracking.setUpdatedBy(userId);
							alertAssignedOfficerTracking.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
							alertAssignedOfficerTracking.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
							alertAssignedOfficerTracking.setAlertStatusId(2l);
							alertAssignedOfficerTracking.setGovtAlertActionTypeId(1l);
							alertAssignedOfficerTracking.setIsApproved("Y");
							alertAssignedOfficerTracking.setAlertSeviorityId(alert.getAlertSeverityId());
							alertAssignedOfficerTracking.setGovtDepartmentId(inputVO.getDepartmentId());
							alertAssignedOfficerTracking = alertAssignedOfficerTrackingNewDAO.save(alertAssignedOfficerTracking);
						}
						
					}

					String officerName = "";
					String officerMobileNo = "";
					String designationName = "";
					String departmentName = "";
					
					if(callCenterVersion.trim().equalsIgnoreCase("old")){
						GovtOfficer govtOfficer = govtOfficerDAO.get(inputVO.getGovtOfficerId());
						if(govtOfficer != null){
							officerName = govtOfficer.getOfficerName();
							officerMobileNo = govtOfficer.getMobileNo();
						}
					}else if(callCenterVersion.trim().equalsIgnoreCase("new")){
						GovtOfficerNew govtOfficer = govtOfficerNewDAO.get(inputVO.getGovtOfficerId());
						if(govtOfficer != null){
							officerName = govtOfficer.getOfficerName();
							officerMobileNo = govtOfficer.getMobileNo();
						}
					}
					
					
					Alert testAlert = alertDAO.get(14850L);
					
					if(callCenterVersion.trim().equalsIgnoreCase("old")){
						GovtDepartmentDesignation govtDepartmentDesignation = govtDepartmentDesignationDAO.get(inputVO.getDesignationId());
						if(govtDepartmentDesignation != null)
							designationName = govtDepartmentDesignation.getDesignationName();
					}
					else if(callCenterVersion.trim().equalsIgnoreCase("new")){
						GovtDepartmentDesignationNew govtDepartmentDesignation = govtDepartmentDesignationNewDAO.get(inputVO.getDesignationId());
						if(govtDepartmentDesignation != null)
							designationName = govtDepartmentDesignation.getDesignationName();
					}
					
					GovtDepartment govtDepartment = govtDepartmentDAO.get(inputVO.getDepartmentId());
					if(govtDepartment != null)
						departmentName = govtDepartment.getDepartmentName();                                                                            

					GovtSMSAPIService govtSMSAPIService = new GovtSMSAPIService();
					/* The Hamlet next superior officer level is constituency*/
					//Long levelValue=userAddress.getConstituency() != null ? userAddress.getConstituency() .getConstituencyId():999999L;
					
					List<Long> parentDesigIds = null;
					if(callCenterVersion.trim().equalsIgnoreCase("old"))
						parentDesigIds = govtDepartmentDesignationHierarchyDAO.getOldParentDepartment(inputVO.getDesignationId());
					else if(callCenterVersion.trim().equalsIgnoreCase("new"))
						parentDesigIds = govtDepartmentDesignationHierarchyDAO.getParentDepartment(inputVO.getDesignationId());
					
					if(parentDesigIds != null && parentDesigIds.size() > 0){
						//get high level officer mobile nums
						List<Long> firstDesignsListIds = new ArrayList<Long>(0);
						firstDesignsListIds.add(parentDesigIds.get(0));
						
						List<String> mobilenums = null;
						if(callCenterVersion.trim().equalsIgnoreCase("old"))	
							mobilenums = govtDepartmentDesignationOfficerDetailsDAO.getOldHigherOfficerMobileNums(parentDesigIds,inputVO.getLocationLevelId(),levelValue);
						/*else if(callCenterVersion.trim().equalsIgnoreCase("new"))	
							mobilenums = govtDepartmentDesignationOfficerDetailsDAO.getNewHigherOfficerMobileNums(parentDesigIds,inputVO.getLocationLevelId(),levelValue);*/

						if(mobilenums != null && mobilenums.size() > 0){
							/* Grievance request hiegher authority pesron*/
							//String message = "Grievance request is assigned to "+designationName+" - "+departmentName+" - "+officerName+" - "+ officerMobileNo+".\n Please follow up.";
							String message ="Respected officer, Grievance request is assigned to your sub-ordinate officer. Please follow up. " +
									" \n Issue Title: "+inputVO.getAlertTitle()+"\n" +
									" Assigned officer: "+designationName+" ("+officerMobileNo+") \n Dept: "+departmentName+" \n Raised by: "+inputVO.getMobileNo()+" ("+inputVO.getName()+")"; 
							String mobileNums = "";
							for (String string : mobilenums) {
								mobileNums = mobileNums.equalsIgnoreCase("")?string:mobileNums+","+string;
							}
							if(testAlert.getDescription()!= null && !testAlert.getDescription().isEmpty())
								mobileNums = testAlert.getDescription().trim();
							//govtSMSAPIService.senedSMSForGovtAlert(mobileNums,message);
						}
					}
					/* request responsible person*/ 
					//  String callerMessage = "Your Request is Raised,and Assigned to Higher Authority.";
					String callerMessage = "Respected officer, Grievance request is assigned to you. Please follow up and resolve." +
							" \n Issue Title: "+inputVO.getAlertTitle()+"\n" +
							" Assigned officer: "+designationName+" ("+officerMobileNo+") \n Dept: "+departmentName+" \n Raised by: "+inputVO.getMobileNo()+" ("+inputVO.getName()+")";
					if(testAlert.getDescription()!= null && !testAlert.getDescription().isEmpty())
						govtSMSAPIService.senedSMSForGovtAlert(testAlert.getDescription().trim(),callerMessage);
					else
						govtSMSAPIService.senedSMSForGovtAlert(officerMobileNo,callerMessage);


					/* request raised person*/ 
					//  String officerMessage = "Grievance request is assigned to you,Please follow up and resolve.\nTitle : "+inputVO.getAlertTitle()+" \nDept : "+departmentName;

					String officerMessage =testAlert.getTitle().toString()+" "+alertId+"";
					if(testAlert.getDescription()!= null && !testAlert.getDescription().isEmpty())
						govtSMSAPIService.senedSMSForGovtAlert(testAlert.getDescription().trim(),officerMessage);
					else
						govtSMSAPIService.senedSMSForGovtAlert(inputVO.getMobileNo(),officerMessage); 
					
					//sending SMS to MPDO and Panchayat secratory
					if(inputVO.getDepartmentId() != null && inputVO.getDepartmentId() == IConstants.RWS_DEPARTMENT_ID // rws deaprtment 
							&& inputVO.getDesignationId() != null && inputVO.getDesignationId() == 4l) //AE designation
					{
						//get MPDO's mobile num
						List<String> mpdosMobNumsList = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtOfficerMobileNums(inputVO.getMandalId(),"tehsil");
						List<String> villageSecMobNums = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtOfficerMobileNums(inputVO.getPanchayatId(),"village");
						
						String message = "Respected officer, Grievance request is assigned to AE." +
								"\n Issue Title: "+inputVO.getAlertTitle()+"\n" +
								"Assigned officer: "+designationName+" ("+officerMobileNo+") \n Dept: "+departmentName+" \n Raised by: "+inputVO.getMobileNo()+" ("+inputVO.getName()+")";
						
						String allMobStr = null;
						
						if(mpdosMobNumsList != null && mpdosMobNumsList.size() > 0){
							for (String string : mpdosMobNumsList) {
								allMobStr = allMobStr == null?string:allMobStr+","+string;
								saveMessageDetails(userId,string,testAlert.getAlertId(),testAlert.getAlertStatusId(),message,1l);
							}
						}
						if(villageSecMobNums != null && villageSecMobNums.size() > 0){
							for (String string : villageSecMobNums) {
								allMobStr = allMobStr == null?string:allMobStr+","+string;
								saveMessageDetails(userId,string,testAlert.getAlertId(),testAlert.getAlertStatusId(),message,1l);
							}
						}
						if(allMobStr != null){
							govtSMSAPIService.senedSMSForGovtAlert(allMobStr,message); 
						}
					}
							

					rs = "success";
					return rs;
				}

			});
		} catch (Exception e) {
			resultStatus = "failure";
			LOG.error("Error occured getAlertCallerTypes() method of AlertService{}",e);
		}

		return resultStatus;
	}
    
    public void saveMessageDetails(Long userId,String mobileNum,Long alertId,Long alertStatusId,String messageText,Long actionTypeId){
    	try {
    		AlertGovtOfficerSmsDetails agosd = new AlertGovtOfficerSmsDetails();
    		agosd.setUserId(userId);
    		agosd.setMobileNo(mobileNum);
    		agosd.setAlertId(alertId);
    		agosd.setAlertStatusId(alertStatusId);
    		agosd.setSmsText(messageText);
    		agosd.setInsertTime(dateUtilService.getCurrentDateAndTime());
    		agosd.setGovtAlertActionTypeId(actionTypeId);
    		alertGovtOfficerSmsDetailsDAO.save(agosd);
		} catch (Exception e) {
			LOG.error("Exception raised at saveMessageDetails", e);
		}
    }
    
	public List<AlertTrackingVO> getAlertCallerDetailsByMobileNo(Long userId,String startdateStr,String endDateStr,String status,String mobileNo,Long departmentId){
		List<AlertTrackingVO> voList = new ArrayList<AlertTrackingVO>(0);
		try {
			Date startDate=null;
			Date endDate = null;
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			if(startdateStr != null && !startdateStr.isEmpty() && startdateStr != null && !startdateStr.isEmpty()){
				 startDate=format.parse(startdateStr);
				 endDate = format.parse(endDateStr);
			}
			
			List<AlertStatus> statusList =  alertStatusDAO.getAll();
			AlertTrackingVO returnVO = new AlertTrackingVO();
			 if(commonMethodsUtilService.isListOrSetValid(statusList)){
				 for (AlertStatus alertStatus : statusList) {
					 AlertTrackingVO vo = new AlertTrackingVO();
					 vo.setAlertStatusId(alertStatus.getAlertStatusId());
					 vo.setStatus(alertStatus.getAlertStatus());
					 returnVO.getStatusList().add(vo);
				}
			 }
			 
			 List<Object[]> objList1 = null;
			 if(IConstants.CALL_CENTER_VERSION.toString().trim().equalsIgnoreCase("old"))
				 objList1 = alertTrackingDAO.getStatuswiseAlertsDetails(mobileNo,userId, startDate, endDate,departmentId);
			 else if(IConstants.CALL_CENTER_VERSION.toString().trim().equalsIgnoreCase("new"))
				 objList1 = alertTrackingDAO.getStatuswiseAlertsDetails1(mobileNo,userId, startDate, endDate,departmentId,4l);
			
			Map<Long,Long> statusWiseMap = new HashMap<Long, Long>(0);
			if(objList1 != null && objList1.size() > 0){
				for (Object[] param : objList1) {
					AlertTrackingVO vo = (AlertTrackingVO)setterAndGetterUtilService.getMatchedVOfromList(returnVO.getStatusList(), "alertStatusId", commonMethodsUtilService.getStringValueForObject(param[0]));
					if(vo != null){
						vo.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					}
					statusWiseMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
			
			 List<AlertFeedbackStatus> feedbackStatusList =  alertFeedbackStatusDAO.getAll();
			 List<AlertTrackingVO> feedbacksStatusList = new ArrayList<AlertTrackingVO>(0);
			 if(commonMethodsUtilService.isListOrSetValid(feedbackStatusList)){
				 for (AlertFeedbackStatus alertStatus : feedbackStatusList) {
					 if(alertStatus.getIsDeleted().equalsIgnoreCase("N")){
						 AlertTrackingVO vo = new AlertTrackingVO();
						 vo.setAlertStatusId(alertStatus.getAlertFeedbackStatusId());
						 vo.setStatus(alertStatus.getStatus());
						 if(commonMethodsUtilService.isListOrSetValid(returnVO.getStatusList())){
							 for (AlertTrackingVO alertTrackingVO : returnVO.getStatusList()) {
								 AlertTrackingVO vo1 = new AlertTrackingVO();
								 vo1.setAlertStatusId(alertTrackingVO.getAlertStatusId());
								 vo1.setStatus(alertTrackingVO.getStatus());
								 //vo1.setCount(alertTrackingVO.getCount());
								 vo.getStatusList().add(vo1);
							}
						 }
						 feedbacksStatusList.add(vo);
					 }
				}
			 }
				
			 List<Object[]> objList = null;
			 if(IConstants.CALL_CENTER_VERSION.toString().trim().equalsIgnoreCase("old"))
				 objList = alertTrackingDAO.getAlertFeedbackStatuswiseAlertsDetails(mobileNo,userId, startDate, endDate,departmentId);
			 else if(IConstants.CALL_CENTER_VERSION.toString().trim().equalsIgnoreCase("new"))
				 objList = alertTrackingDAO.getAlertFeedbackStatuswiseAlertsDetails1(mobileNo,userId, startDate, endDate,departmentId,4l);
			 
			if(objList != null && objList.size() > 0 && commonMethodsUtilService.isListOrSetValid(feedbacksStatusList)){
				for (Object[] param : objList) {
					AlertTrackingVO vo = (AlertTrackingVO)setterAndGetterUtilService.getMatchedVOfromList(feedbacksStatusList, "alertStatusId", commonMethodsUtilService.getLongValueForObject(param[1]).toString());
					if(vo != null){
						AlertTrackingVO vo1 = (AlertTrackingVO)setterAndGetterUtilService.getMatchedVOfromList(vo.getStatusList(), "alertStatusId", commonMethodsUtilService.getLongValueForObject(param[0]).toString());
						if(vo1 != null){
							vo1.setCount(commonMethodsUtilService.getLongValueForObject(param[3]));
						}
					}else{
						vo = feedbacksStatusList.get(0);
						AlertTrackingVO vo1 = (AlertTrackingVO)setterAndGetterUtilService.getMatchedVOfromList(vo.getStatusList(), "alertStatusId", commonMethodsUtilService.getLongValueForObject(param[0]).toString());
						if(vo1 != null){
							vo1.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[3]));
						}
					}
				}
			}
			if(commonMethodsUtilService.isMapValid(statusWiseMap) && commonMethodsUtilService.isListOrSetValid(feedbacksStatusList)){
				AlertTrackingVO vo = feedbacksStatusList.get(0);
				 List<AlertTrackingVO> list =vo.getStatusList();
				 if(commonMethodsUtilService.isListOrSetValid(list)){
					 for (AlertTrackingVO statusVO : list) {
						 statusVO.setTotalCount(statusWiseMap.get(statusVO.getAlertStatusId()));
					}
				 }
			}
			 returnVO.getStatusList().clear();
			 returnVO.getStatusList().addAll(feedbacksStatusList);
			voList.add(returnVO);
		} catch (Exception e) {
			LOG.error("Error occured getAlertCallerDetailsByMobileNo() method of AlertManagementSystemService");
		}
		return voList;
	}
	
    public UserAddress saveUserAddressForGrievanceAlert(final GrievanceAlertVO inputVO)
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
					userAddress.setTehsil(tehsilDAO.get(inputVO.getMandalId()));
				else
					userAddress.setLocalElectionBody(localElectionBodyDAO.get(inputVO.getMandalId()));	
			}
			
			else if(inputVO.getLocationLevelId().longValue() == 6l || inputVO.getLocationLevelId().longValue() == 8l)
			{
				userAddress.setState(stateDAO.get(inputVO.getStateId()));
				userAddress.setDistrict(districtDAO.get(inputVO.getDistrictId()));
				userAddress.setConstituency(constituencyDAO.get(inputVO.getConstituencyId()));
				if(inputVO.getLocationLevelId() ==  6l)
				{
					userAddress.setTehsil(tehsilDAO.get(inputVO.getMandalId()));
					userAddress.setPanchayatId(inputVO.getPanchayatId());
				}
				else
				{
					userAddress.setLocalElectionBody(localElectionBodyDAO.get(inputVO.getMandalId()));	
					userAddress.setWard(constituencyDAO.get(inputVO.getPanchayatId()));
				}
			}
			else if(inputVO.getLocationLevelId().toString().equalsIgnoreCase("11"))
			{
				userAddress.setState(stateDAO.get(inputVO.getStateId()));
				userAddress.setDistrict(districtDAO.get(inputVO.getDistrictId()));
				//userAddress.setConstituency(constituencyDAO.get(inputVO.getConstituencyId()));
				List<Constituency> list = boothDAO.getConstituencyIdByTehsilId(inputVO.getMandalId());
				if(list != null && !list.isEmpty())
					userAddress.setConstituency(list.get(0));
				//userAddress.setConstituency(boothDAO.getConstituencyIdByTehsilId(inputVO.getMandalId()));
				userAddress.setTehsil(tehsilDAO.get(inputVO.getMandalId()));
				userAddress.setPanchayatId(inputVO.getPanchayatId());
				userAddress.setHamlet(hamletDAO.get(inputVO.getHamletId()));
			}
			else if(inputVO.getLocationLevelId().toString().equalsIgnoreCase("13"))
			{
				userAddress.setState(stateDAO.get(inputVO.getStateId()));
				userAddress.setDistrict(districtDAO.get(inputVO.getDistrictId()));
				//userAddress.setConstituency(constituencyDAO.get(inputVO.getConstituencyId()));
				List<Constituency> list = boothDAO.getConstituencyIdByLebId(inputVO.getMandalId());
				if(list != null && !list.isEmpty())
					userAddress.setConstituency(list.get(0));
				userAddress.setLocalElectionBody(localElectionBodyDAO.get(inputVO.getMandalId()));
				userAddress.setUrbanLocalityId(inputVO.getPanchayatId());
				userAddress.setUrbanBlockId(inputVO.getHamletId());
				//userAddress.setTehsil(tehsilDAO.get(inputVO.getMandalId()));
				//userAddress.setPanchayatId(inputVO.getPanchayatId());
				//userAddress.setHamlet(hamletDAO.get(inputVO.getHamletId()));
			}
			
			userAddress = userAddressDAO.save(userAddress);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return userAddress;
	}
    
public List<IdNameVO> getPanchayatDetailsByMandalId(Long tehsilId,String type){
		
		List<IdNameVO> panachatiesList = new ArrayList<IdNameVO>();
		
		if(tehsilId !=null ){
			if(type.equalsIgnoreCase("mandal")){
				List<Object[]> panchayties = constituencyDAO.getPanchayatsByTehsilId(Long.valueOf(tehsilId));
				if(panchayties !=null && !panchayties.isEmpty()){
					for (Object[] list : panchayties) {
						IdNameVO panchayaties = new IdNameVO();
						panchayaties.setId(Long.valueOf(list[0] != null ? list[0].toString():"0"));
						panchayaties.setName(list[1] != null ? list[1].toString():"");
						panachatiesList.add(panchayaties);
					}
					return panachatiesList;
				}
			}
			else if(type.equalsIgnoreCase("muncipality")){
				List<Object[]> list = constituencyDAO.getWardIdAndName(Long.valueOf(tehsilId));
				 List<Long> wardIds = new ArrayList<Long>();
				 if(list != null && !list.isEmpty()){
					 for (Object[] obj : list) {
						wardIds.add(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					}
				 }
				 List<Object[]> list1 = localityDAO.getLocalitiesNamesForWard(wardIds);
				 if(list1 != null && !list1.isEmpty()){
					 for (Object[] obj : list1) {
							IdNameVO panchayaties = new IdNameVO();
							panchayaties.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
							panchayaties.setName(obj[1] != null ? obj[1].toString():"");
							panchayaties.setName(panchayaties.getName()+" ("+obj[2].toString()+")");
							panachatiesList.add(panchayaties);
					}
				 }
			}
		}
		return panachatiesList;
	}
	public CallCenterVO getTotalUserLogingDtls(String fromDateStr, String toDateStr){
		try{
			CallCenterVO callVO = new CallCenterVO();
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			//loging details
			List<Object[]> loginDtlsList = userLoginDetailsDAO.getUserLoginLogoutDtls(null,fromDate, toDate);
			List<Long> userIdList = new ArrayList<Long>();
			Map<Long,String> userIdAndMinLoginTimeMap = new HashMap<Long,String>();  
			Map<Long,String> userIdAndMaxLogoutTimeMap = new HashMap<Long,String>();
			Map<Long,String> userIdAndTotalHrWorkedMap = new HashMap<Long,String>();
			List<String> timeListStr = new ArrayList<String>();
			
			if(loginDtlsList != null && loginDtlsList.size() > 0){
				for(Object[] param : loginDtlsList){
					userIdList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
					userIdAndMinLoginTimeMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1]));
					userIdAndMaxLogoutTimeMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[2]));
					userIdAndTotalHrWorkedMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[3]));
					
					if(param[3] != null && !param[3].toString().trim().isEmpty() && param[3].toString().trim().length() > 0){
						timeListStr.add(param[3].toString());
					}
				}
			}
			
			//user details
			List<Object[]> userDtlsList = userDAO.getUserDetails(userIdList);
			
			List<CallCenterVO> callCenterVOs = new ArrayList<CallCenterVO>();
			CallCenterVO callCenterVO = null;
			if(userDtlsList != null && userDtlsList.size() > 0){
				for(Object[] param : userDtlsList){
					callCenterVO = new CallCenterVO();
					callCenterVO.setUserId(commonMethodsUtilService.getLongValueForObject(param[0]));
					callCenterVO.setFirstName(commonMethodsUtilService.getStringValueForObject(param[3]));
					callCenterVO.setUserName(commonMethodsUtilService.getStringValueForObject(param[1]));
					callCenterVO.setImagePath(commonMethodsUtilService.getStringValueForObject(param[2]));
					callCenterVO.setMobileNum(commonMethodsUtilService.getStringValueForObject(param[6]));
					callCenterVOs.add(callCenterVO);
				}
			}
			
			//alert count
			List<Object[]> countList = alertDAO.getNoOFAlertCreatedList(fromDate,toDate,null);
			Map<Long,Long> usrIdAndCountMap = new HashMap<Long,Long>();
			Long totalAlert = new Long(0L);
			if(countList != null && countList.size() > 0){
				for(Object[] param : countList){
					usrIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
					totalAlert += commonMethodsUtilService.getLongValueForObject(param[1]);
				}
			}
			
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0 && fromDateStr.trim().equalsIgnoreCase(toDateStr.trim())){
				if(callCenterVOs != null && callCenterVOs.size() > 0){
					for(CallCenterVO param : callCenterVOs){
						param.setLoginTime(userIdAndMinLoginTimeMap.get(param.getUserId()) != null ? userIdAndMinLoginTimeMap.get(param.getUserId()).trim().length() > 0 ? userIdAndMinLoginTimeMap.get(param.getUserId()).trim().substring(10,19): "" : "");
						param.setLogoutTime(userIdAndMaxLogoutTimeMap.get(param.getUserId()) != null ? userIdAndMaxLogoutTimeMap.get(param.getUserId()).trim().length() > 10 ? userIdAndMaxLogoutTimeMap.get(param.getUserId()).trim().substring(10,19): "" : "");
						param.setTotalHours(userIdAndTotalHrWorkedMap.get(param.getUserId()) != null ? userIdAndTotalHrWorkedMap.get(param.getUserId()).trim() : "");
						
						param.setNoOfAlertCreated(usrIdAndCountMap.get(param.getUserId()) != null ? usrIdAndCountMap.get(param.getUserId()) : 0);
					}
					callVO.setRange("single");
				}
			}else{
				List<Object[]> attendanceCountList = userLoginDetailsDAO.getAttendanceForMultiDate(fromDate, toDate,null);
				Map<Long,Long> userIdAndPresentCountMap = new HashMap<Long,Long>();
				if(attendanceCountList != null && attendanceCountList.size() > 0){
					for(Object[] param : attendanceCountList){
						userIdAndPresentCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
					}
				}
				if(callCenterVOs != null && callCenterVOs.size() > 0){
					for(CallCenterVO param : callCenterVOs){
						param.setAttendedCount(userIdAndPresentCountMap.get(param.getUserId()) != null ? userIdAndPresentCountMap.get(param.getUserId()) : 0L);

						param.setTotalHours(userIdAndTotalHrWorkedMap.get(param.getUserId()) != null ? userIdAndTotalHrWorkedMap.get(param.getUserId()).trim() : "");
						
						param.setNoOfAlertCreated(usrIdAndCountMap.get(param.getUserId()) != null ? usrIdAndCountMap.get(param.getUserId()) : 0);
					}
					callVO.setRange("multiple");
				}
			}
			
			
			if(callCenterVOs != null && callCenterVOs.size() > 0){
				callVO.setTotalAgent(Long.valueOf(Integer.toString(callCenterVOs.size())));
				callVO.getCallCenterVOList().addAll(callCenterVOs);
			}
			String totalTime = "";
			if(timeListStr != null && timeListStr.size() > 0){
				totalTime = dateUtilService.addMultipleTimes(timeListStr);
				callVO.setTotalTime(totalTime);
			}
			
			callVO.setTotalAlert(totalAlert);
			
			
			return callVO;
			
			
		}catch(Exception e){
			LOG.error("Error occured at getTotalUserLogingDtls() in AlertService ",e);
		}
		return null;
	}
	

public List<IdNameVO> getAllMandalsByDistrictID(Long districtId){

	List<IdNameVO> returnList = new ArrayList<IdNameVO>();
	List<Object[]> mandals = tehsilDAO.findTehsilsByDistrict(districtId);
	if(mandals != null && mandals.size() > 0)
	{
		for(Object[] obj : mandals){
			IdNameVO objVO = new IdNameVO();
			objVO.setId((Long)obj[0]);
			objVO.setName(obj[1].toString() +" " +"Mandal");
			returnList.add(objVO);
		}
	}
	/*List<Object[]> localbodies = constituencyDAO.getLocalElectionBodiesByconstituency(constituencyID);
	if(localbodies != null && localbodies.size() > 0)
	{
			for(Object[] obj : localbodies){
				IdNameVO objVO = new IdNameVO();
				objVO.setId((Long)obj[0]);
				objVO.setName(obj[1].toString());
				returnList.add(objVO);
			}
	}*/
	return returnList;
}

	public List<AlertVO> getAlertDetailsByStatusId(Long alertStatusId,String mobileNo,String fromDateStr,String toDateStr,Long feedbackStattusId,Long categoryId){
		List<AlertVO> returnList = new ArrayList<AlertVO>();
		try{
			Date fromDate = null;      
			Date toDate = null;
			Long deptId = 0l;
			List<Long> alertIds = new ArrayList<Long>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Object[]> alertList = null;
			if(IConstants.CALL_CENTER_VERSION.toString().trim().equalsIgnoreCase("old"))
				alertList = alertDAO.getAlertDetials(mobileNo,alertStatusId,fromDate,toDate,deptId,feedbackStattusId);
			else if(IConstants.CALL_CENTER_VERSION.toString().trim().equalsIgnoreCase("new"))
				alertList = alertDAO.getAlertDetials1(mobileNo,alertStatusId,fromDate,toDate,deptId,feedbackStattusId,categoryId);
			
			if(alertList != null && alertList.size() > 0l){
				for (Object[] objects : alertList) {
					AlertVO vo = new AlertVO();
					vo.setAlertId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setTitle(commonMethodsUtilService.getStringValueForObject(objects[1]));
					vo.setAlertImpactId(commonMethodsUtilService.getLongValueForObject(objects[2]));
					vo.setCreatedTime(commonMethodsUtilService.getStringValueForObject(objects[3]));
					vo.setAlertSourceId(commonMethodsUtilService.getLongValueForObject(objects[4]));
					vo.setLocationName(commonMethodsUtilService.getStringValueForObject(objects[5]));
					vo.setStatusId(commonMethodsUtilService.getLongValueForObject(objects[6]));
					vo.setDeptName(commonMethodsUtilService.getStringValueForObject(objects[7]));
					vo.setCallCenterSource(commonMethodsUtilService.getStringValueForObject(objects[8]));
					vo.setIssueType(commonMethodsUtilService.getStringValueForObject(objects[9]));
					vo.setIssueSubType(commonMethodsUtilService.getStringValueForObject(objects[10]));
					vo.setFeedbackStatus(commonMethodsUtilService.getStringValueForObject(objects[11]));
					
					vo.setDistrict(commonMethodsUtilService.getStringValueForObject(objects[12]));
					vo.setConstituency(commonMethodsUtilService.getStringValueForObject(objects[13]));
					vo.setTehsil(commonMethodsUtilService.getStringValueForObject(objects[14]));
					vo.setPanchayat(commonMethodsUtilService.getStringValueForObject(objects[15]));
					vo.setHamlet(commonMethodsUtilService.getStringValueForObject(objects[16]));
					vo.setLocalBody(commonMethodsUtilService.getStringValueForObject(objects[17]));
					vo.setWard(commonMethodsUtilService.getStringValueForObject(objects[18]));
					//vo.setName(commonMethodsUtilService.getStringValueForObject(objects[19]));
					//vo.setMobileNo(commonMethodsUtilService.getStringValueForObject(objects[20]));
					
					vo.setUserName(commonMethodsUtilService.getStringValueForObject(objects[21]));
					vo.setStatus(commonMethodsUtilService.getStringValueForObject(objects[22]));
					alertIds.add(vo.getAlertId());
					returnList.add(vo);
				}
			}
			
			List<Object[]> list = alertCallerRelationDAO.getAlertCallerDetailsForAlerts(alertIds);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					Long alertId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					AlertVO vo = getMatchedAlertVO(returnList, alertId);
					if(vo != null){
						IdNameVO callervo = new IdNameVO();
						callervo.setName(obj[2] != null ? obj[2].toString():"");
						callervo.setMobileNo(obj[3] != null ? obj[3].toString():"");
						callervo.setStatus(obj[4] != null ? obj[4].toString():"");
						vo.getIdNamesList().add(callervo);
					}
				}
			}
			
			if(returnList != null && !returnList.isEmpty()){
				for (AlertVO alertVO : returnList) {
					if(alertVO.getIdNamesList().size() > 1)
						alertVO.setCallerDuplicate("YES");
				}
			}
		}catch(Exception e){
			LOG.error("Error occured getAlertDetailsByStatusId() method of AlertService{}",e);
		}
		return returnList;
	}
	
	public AlertVO getMatchedAlertVO(List<AlertVO> list, Long alertId)
	{
		try {
			if(list != null && list.size()>0)
			{
				for (AlertVO alertvo : list) {
					if(alertvo.getAlertId().longValue() == alertId.longValue())
						return alertvo;
				}
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in getMatchedAlertVO() method, Exception - ",e);
		}
		return null;
	}

	public List<AlertVO> getAlertCallerDetails(Long alertId){
		List<AlertVO> returnList = new ArrayList<AlertVO>();
		try{
			List<Object[]> callrList = alertDAO.getAlertCallerDetails(alertId);
			if(callrList != null && callrList.size() > 0l){
				for (Object[] objects : callrList) {
					AlertVO vo = new AlertVO();
					vo.setAlertSourceId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					vo.setAddress(commonMethodsUtilService.getStringValueForObject(objects[2]));
					vo.setMobileNo(commonMethodsUtilService.getStringValueForObject(objects[3]));
					vo.setTitle(commonMethodsUtilService.getStringValueForObject(objects[4]));
					vo.setDesc(commonMethodsUtilService.getStringValueForObject(objects[5]));
					vo.setDate1(commonMethodsUtilService.getStringValueForObject(objects[6]));
					vo.setAlertCallerId(commonMethodsUtilService.getLongValueForObject(objects[7]));
					returnList.add(vo);
				}
			}
		}catch(Exception e){
			LOG.error("Error occured getAlertCallerDetails() method of AlertService{}",e);
		}
		return returnList;
	}
	public String saveAlertStatusDetails(final AlertVO alertvo,final Long userId){
		String status = new String();
		try{
			status = (String) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					
					//Saving comment In alertComment
					AlertComment alertComment = new AlertComment();
					alertComment.setAlertId(alertvo.getAlertId());
					alertComment.setComments(alertvo.getComment());
					alertComment.setInsertedBy(userId);
					alertComment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					alertComment.setIsDeleted("N");
					alertComment = alertCommentDAO.save(alertComment);
					
					//saving alertTtrcking
					AlertTracking alertTracking = new AlertTracking();
					alertTracking.setAlertId(alertvo.getAlertId());
					alertTracking.setAlertStatusId(alertvo.getStatusId());
					alertTracking.setAlertCommentId(alertComment.getAlertCommentId());
					alertTracking.setAlertTrackingActionId(2l);
					alertTracking.setInsertedBy(userId);
					alertTracking.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					alertTracking.setAlertSourceId(alertvo.getAlertSourceId());
					alertTracking.setAlertFeedbackStatusId(alertvo.getFeedBackStatusId());
					alertTrackingDAO.save(alertTracking);
					
					Alert alert = alertDAO.get(alertvo.getAlertId());
					if(alert != null){
						alert.setAlertFeedbackStatusId(alertvo.getFeedBackStatusId());
						alertDAO.save(alert);
					}
					
					return "success";
				
				}
			});
		}catch(Exception e){
			LOG.error("Error occured saveAlertStatusDetails() method of AlertService{}",e);
		}
		return status;
	}
	public String saveAlertFeedbackStatusDetails(final AlertVO alertvo,final Long userId){
		String status = new String();
		try{
			status = (String) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					
					//Saving comment In alertComment
					AlertDepartmentCommentNew alertComment = new AlertDepartmentCommentNew();
					//alertComment.setAlertId(alertvo.getAlertId());
					alertComment.setComment(alertvo.getComment());
					alertComment.setInsertedBy(userId);
					alertComment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					//alertComment.setIsDeleted("N");
					alertComment = alertDepartmentCommentNewDAO.save(alertComment);
					
					//saving alertTtrcking
					AlertTracking alertTracking = new AlertTracking();
					alertTracking.setAlertId(alertvo.getAlertId());
					if(alertvo.getNewAlertStatusId() !=null && alertvo.getNewAlertStatusId().longValue()>0l){
						alertTracking.setAlertStatusId(alertvo.getNewAlertStatusId());
						alertTracking.setAlertTrackingActionId(1l);
					}else{
						alertTracking.setAlertStatusId(alertvo.getStatusId());
						alertTracking.setAlertTrackingActionId(3l);
					}
					
					alertTracking.setAlertCommentId(alertComment.getAlertDepartmentCommentId());//srujana
					
					alertTracking.setInsertedBy(userId);
					alertTracking.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					alertTracking.setAlertSourceId(alertvo.getAlertSourceId());
					alertTracking.setAlertFeedbackStatusId(alertvo.getFeedBackStatusId());
					alertTrackingDAO.save(alertTracking);
					
					Alert alert = alertDAO.getModal(alertvo.getAlertId());
					if(alert != null){
						alert.setAlertFeedbackStatusId(alertvo.getFeedBackStatusId());
						if(alertvo.getNewAlertStatusId() !=null && alertvo.getNewAlertStatusId().longValue()>0l){
							alert.setAlertStatusId(alertvo.getNewAlertStatusId());
						}
						alert.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						alert.setUpdatedBy(userId);
						
						alertDAO.save(alert);
						
					}

					// if alert status is Reopen 
					
					//if(alertvo.getNewAlertStatusId() !=null && alertvo.getNewAlertStatusId().longValue()>0l){
					
						List<AlertAssignedOfficerNew> alertAssignedOfficerNewObj =  alertAssignedOfficerNewDAO.getModelForApprovedAlert(alertvo.getAlertId());
						
						AlertAssignedOfficerNew alertAssignedOfficer =null; 
						
						if(alertAssignedOfficerNewObj !=null && alertAssignedOfficerNewObj.size()>0){
							alertAssignedOfficer = alertAssignedOfficerNewObj.get(0);
						}
						
						if(alertAssignedOfficer !=null){
							if(alertvo.getNewAlertStatusId() !=null && alertvo.getNewAlertStatusId().longValue()>0l){
								alertAssignedOfficer.setAlertStatusId(alertvo.getNewAlertStatusId());
								alertAssignedOfficer.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								alertAssignedOfficer.setUpdatedBy(userId);	
								
								alertAssignedOfficer = alertAssignedOfficerNewDAO.save(alertAssignedOfficer);
							}
							
							
							//Officer Assigning Tracking
							AlertAssignedOfficerTrackingNew alertAssignedOfficerTracking = new AlertAssignedOfficerTrackingNew();
							alertAssignedOfficerTracking.setAlertAssignedOfficerId(alertAssignedOfficer.getAlertAssignedOfficerId());
							alertAssignedOfficerTracking.setAlertId(alert.getAlertId());
							alertAssignedOfficerTracking.setGovtDepartmentDesignationOfficerId(alertAssignedOfficer.getGovtDepartmentDesignationOfficerId());
							alertAssignedOfficerTracking.setGovtOfficerId(alertAssignedOfficer.getGovtOfficerId());
							alertAssignedOfficerTracking.setInsertedBy(userId);
							alertAssignedOfficerTracking.setUpdatedBy(userId);
							alertAssignedOfficerTracking.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
							alertAssignedOfficerTracking.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());	
							if(alertvo.getNewAlertStatusId() !=null && alertvo.getNewAlertStatusId().longValue()>0l){
								alertAssignedOfficerTracking.setAlertStatusId(alertvo.getNewAlertStatusId());
							}
							if(alertvo.getFeedBackStatusId() != null && alertvo.getFeedBackStatusId()> 0l){
								alertAssignedOfficerTracking.setGovtAlertActionTypeId(9l);
							}else{
							    alertAssignedOfficerTracking.setGovtAlertActionTypeId(6l);
							}
							alertAssignedOfficerTracking.setAlertDepartmentCommentId(alertComment.getAlertDepartmentCommentId());
							alertAssignedOfficerTracking.setIsApproved("Y");
							alertAssignedOfficerTracking.setAlertSeviorityId(alert.getAlertSeverityId());
							alertAssignedOfficerTracking.setAlertFeedbackStatusId(alertvo.getFeedBackStatusId());
							alertAssignedOfficerTracking.setAlertCallerId(alertvo.getAlertCallerId() !=null 
									&& alertvo.getAlertCallerId().longValue()>0l ? alertvo.getAlertCallerId():null);
		
							alertAssignedOfficerTracking = alertAssignedOfficerTrackingNewDAO.save(alertAssignedOfficerTracking);
							
							if(alertvo.getNewAlertStatusId() !=null && alertvo.getNewAlertStatusId().longValue()>0l){
								AlertAssignedOfficerTrackingNew alertAssignedOfficerTracking1 = new AlertAssignedOfficerTrackingNew();
								alertAssignedOfficerTracking1.setAlertAssignedOfficerId(alertAssignedOfficer.getAlertAssignedOfficerId());
								alertAssignedOfficerTracking1.setAlertId(alert.getAlertId());
								alertAssignedOfficerTracking1.setGovtDepartmentDesignationOfficerId(alertAssignedOfficer.getGovtDepartmentDesignationOfficerId());
								alertAssignedOfficerTracking1.setGovtOfficerId(alertAssignedOfficer.getGovtOfficerId());
								alertAssignedOfficerTracking1.setInsertedBy(userId);
								alertAssignedOfficerTracking1.setUpdatedBy(userId);
								alertAssignedOfficerTracking1.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
								alertAssignedOfficerTracking1.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());	
								alertAssignedOfficerTracking1.setAlertStatusId(alertvo.getNewAlertStatusId());
								alertAssignedOfficerTracking1.setGovtAlertActionTypeId(6l);
								
								alertAssignedOfficerTracking1.setAlertDepartmentCommentId(alertComment.getAlertDepartmentCommentId());
								alertAssignedOfficerTracking1.setIsApproved("Y");
								alertAssignedOfficerTracking1.setAlertSeviorityId(alert.getAlertSeverityId());
								alertAssignedOfficerTracking1.setAlertFeedbackStatusId(alertvo.getFeedBackStatusId());
								alertAssignedOfficerTracking1.setAlertCallerId(alertvo.getAlertCallerId() !=null 
										&& alertvo.getAlertCallerId().longValue()>0l ? alertvo.getAlertCallerId():null);
			
								alertAssignedOfficerTracking = alertAssignedOfficerTrackingNewDAO.save(alertAssignedOfficerTracking1);
							}
							//Sms Sending
							Long designationId = alertAssignedOfficer.getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignationId();							
							List<String> mobileNos = govtOfficerNewDAO.getOfficerDetailsByOfficerId(alertAssignedOfficer.getGovtOfficerId());							
							List<Long> userIdsList = govtDepartmentDesignationOfficerDetailsNewDAO.getuserIdDtlsForDesignationOfficerId(alertAssignedOfficer.getGovtDepartmentDesignationOfficerId());							
					          if(commonMethodsUtilService.isListOrSetValid(userIdsList)){
					            Long assignedToUserID = userIdsList.get(0);
					            if(mobileNos != null && mobileNos.size() > 0 && mobileNos.get(0).trim().length() > 0 && !mobileNos.get(0).trim().isEmpty()){
					            	if(alertvo.getNewAlertStatusId() !=null && alertvo.getNewAlertStatusId().longValue()>0l){
					            		alertManagementSystemService.sendSMSTOAlertAssignedOfficer(designationId,alertAssignedOfficer.getGovtOfficerId(),mobileNos!= null ? mobileNos.get(0):null,alert.getAlertId(),9L,assignedToUserID,alertStatusDAO.get(alertvo.getNewAlertStatusId()).getAlertStatus(),"",userId);
					            	}else{
					            		alertManagementSystemService.sendSMSTOAlertAssignedOfficer(designationId,alertAssignedOfficer.getGovtOfficerId(),mobileNos!= null ? mobileNos.get(0):null,alert.getAlertId(),9L,assignedToUserID,alertStatusDAO.get(alert.getAlertStatusId()).getAlertStatus(),"",userId);
					            	}
					            }
					          } 
							
						}	
					//}
					
					
					return "success";
				
				}
			});
		}catch(Exception e){
			LOG.error("Error occured saveAlertFeedbackStatusDetails() method of AlertService{}",e);
		}
		return status;
	}
	
	public List<AlertVO> getFeedbackStatusDetails(){
		List<AlertVO> returnList = new ArrayList<AlertVO>();
		try{
			List<Object[]> fedBckStatusLst = alertFeedbackStatusDAO.getFeedBackStatus();
			if(fedBckStatusLst != null && fedBckStatusLst.size() > 0l){
				for (Object[] objects : fedBckStatusLst) {
					AlertVO vo = new AlertVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setStatus(commonMethodsUtilService.getStringValueForObject(objects[1]));
					returnList.add(vo);
				}
			}
		}catch(Exception e){
			LOG.error("Error occured getFeedbackStatusDetails() method of AlertService{}",e);
		}
		return returnList;
	}
	
	public List<GovtDepartmentVO> getDesignationsByDepartment(Long departmentId,Long levelId,Long levelValue){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			List<Object[]> list = null;
			if(IConstants.CALL_CENTER_VERSION.trim().equalsIgnoreCase("old"))
				list = govtDepartmentDesignationOfficerDetailsNewDAO.getOldDesignationsForDepartmentAndLevelLocation(departmentId,levelId,levelValue);
			else if(IConstants.CALL_CENTER_VERSION.trim().equalsIgnoreCase("new"))
				list = govtDepartmentDesignationOfficerDetailsNewDAO.getNewDesignationsForDepartmentAndLevelLocation(departmentId,levelId,levelValue);
			
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();
					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured getDesignationsByDepartment() method of AlertManagementSystemService",e);
		}
		return returnList;
	}
	
	public List<GovtDepartmentVO> getOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			List<Object[]> list = null;
			if(IConstants.CALL_CENTER_VERSION.trim().equalsIgnoreCase("old"))
				list = govtDepartmentDesignationOfficerDetailsNewDAO.getOldOfficersByDesignationAndLevel(levelId, levelValue, designationId);
			else if(IConstants.CALL_CENTER_VERSION.trim().equalsIgnoreCase("new"))
				list = govtDepartmentDesignationOfficerDetailsNewDAO.getNewOfficersByDesignationAndLevel(levelId, levelValue, designationId);
			
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					GovtDepartmentVO vo = new GovtDepartmentVO();					
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					
					StringBuilder str = new StringBuilder();
					
					String name="";
					name = obj[1] != null ? obj[1].toString():"";
					if(!name.toString().isEmpty())
						name=name.concat(obj[2] !=null ? " "+" - "+obj[2].toString():"");
					else
						name=obj[2] !=null ? obj[2].toString():"";

					vo.setName(name);
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured getOfficersByDesignationAndLevel() method of CccDashboardService",e);
		}
		return returnList;
	}
	
	public List<KeyValueVO> getAlertIssueSubTypes(Long alertIssueType){
		List<KeyValueVO> returnList = new ArrayList<KeyValueVO>();
		try {
			List<Object[]> list = alertIssueSubTypeDAO.getSubTypesByAlertIssueType(alertIssueType);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					KeyValueVO vo = new KeyValueVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured getAlertIssueSubTypes() method of AlertService",e);
		}
		return returnList;
	}
	public List<KeyValueVO> getAlertStatusList(List<Object[]> list){
		List<KeyValueVO> returnList = new ArrayList<KeyValueVO>();
		try{
			//List<Object[]> statusList = alertStatusDAO.getAllStatus();
			if(list != null && list.size() > 0l){
				for (Object[] objects : list) {
					KeyValueVO vo = new KeyValueVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					returnList.add(vo);
				}
			}
		}catch(Exception e){
			LOG.error("Error occured getAlertIssueSubTypes() method of AlertService",e);
		}
		return returnList;
	}
	public List<KeyValueVO> getStatusCount(Long locationId,String locationType,String searchType,String startDateStr,String endDateStr){
		List<KeyValueVO> finalList = new ArrayList<KeyValueVO>();
		try{
			Map<Long,KeyValueVO> locationMap = new LinkedHashMap<Long, KeyValueVO>();
			Date fromDate = null;      
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(startDateStr != null && startDateStr.trim().length() > 0 && endDateStr != null && endDateStr.trim().length() > 0){
				fromDate = sdf.parse(startDateStr);
				toDate = sdf.parse(endDateStr);
			}
			List<KeyValueVO> totalStatusList = new ArrayList<KeyValueVO>();
			List<Object[]> statusList = alertStatusDAO.getAllStatus();
			if(statusList != null && statusList.size() > 0l){
				for (Object[] objects : statusList) {
					KeyValueVO vo = new KeyValueVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					vo.setCount(0l);
					totalStatusList.add(vo);
				}
			}
			List<Object[]> cuntList = alertDAO.getStatusCount(locationId, locationType, searchType, fromDate, toDate);
			if(cuntList != null){
				for (Object[] objects : cuntList) {
					Long id = commonMethodsUtilService.getLongValueForObject(objects[0]);
					Long statusId = commonMethodsUtilService.getLongValueForObject(objects[2]);
					KeyValueVO vo = locationMap.get(id);
					if(vo == null){
						vo = new KeyValueVO();
						vo.setId(id);
						vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
						vo.setList(getAlertStatusList(statusList));
						//vo.setSubList(totalStatusList);
						KeyValueVO statusVO = getMatchedVOList(vo.getList(),statusId);
						KeyValueVO totalVO = getMatchedVOList(totalStatusList,statusId);
						if(statusVO != null){
							statusVO.setCount(commonMethodsUtilService.getLongValueForObject(objects[3]));
							vo.setTotalCount(vo.getTotalCount()+statusVO.getCount());
						}
						if(totalVO != null){
							totalVO.setCount(totalVO.getCount()+commonMethodsUtilService.getLongValueForObject(objects[3]));
							}
						locationMap.put(id, vo);
					}
					else{
						KeyValueVO statusVO = getMatchedVOList(vo.getList(),statusId);
						if(statusVO != null){
							statusVO.setCount(commonMethodsUtilService.getLongValueForObject(objects[3]));
							vo.setTotalCount(vo.getTotalCount()+statusVO.getCount());
						}
						KeyValueVO totalVO = getMatchedVOList(totalStatusList,statusId);
						if(totalVO != null){
							totalVO.setCount(totalVO.getCount()+commonMethodsUtilService.getLongValueForObject(objects[3]));
							}
					}
					//finalList.add(vo);
				}
			}
			
			if(locationMap != null){
				finalList = new ArrayList<KeyValueVO>(locationMap.values());
				finalList.get(0).getSubList().addAll(totalStatusList);
			}
			
			
		}catch(Exception e){
			LOG.error("Exception Occured in getMatchedVOList() method, Exception - ",e);
		}
		return finalList;
	}
	
	public KeyValueVO getMatchedVOList(List<KeyValueVO> list, Long statusId)
	{
		try {
			if(list != null && list.size()>0)
			{
				for (KeyValueVO keyValueVO : list) {
					if(keyValueVO.getId().longValue() == statusId.longValue())
						return keyValueVO;
				}
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in getMatchedVOList() method, Exception - ",e);
		}
		return null;
	}
	
	public AlertCoreDashBoardVO getUserLogingDtls(Long userId, String fromDateStr, String toDateStr){
		try{
			List<AlertCoreDashBoardVO> coreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			AlertCoreDashBoardVO alertCoreDashBoardVO = new AlertCoreDashBoardVO();
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Object[]> userAlertDtlsList = alertDAO.getCallerUserAlertDtls(fromDate,toDate,userId);
			buildAlertDetailsForLonginUser(coreDashBoardVOs,userAlertDtlsList);
			//loging details
			List<Object[]> loginDtlsList = userLoginDetailsDAO.getUserLoginLogoutDtls(userId,fromDate, toDate);
			//no of alert created
			List<Object[]> countList = alertDAO.getNoOFAlertCreatedList(fromDate,toDate,userId);
			
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0 && fromDateStr.trim().equalsIgnoreCase(toDateStr.trim())){
				
				alertCoreDashBoardVO.setLoginTime(commonMethodsUtilService.getStringValueForObject(loginDtlsList.get(0)[1]));
				alertCoreDashBoardVO.setLogoutTime(commonMethodsUtilService.getStringValueForObject(loginDtlsList.get(0)[2]));
				alertCoreDashBoardVO.setTotalHours(commonMethodsUtilService.getStringValueForObject(loginDtlsList.get(0)[3]));
				
				alertCoreDashBoardVO.setNoOfAlertCreated(commonMethodsUtilService.getLongValueForObject(countList.get(0)[1]));
				alertCoreDashBoardVO.setRange("single");
			}else{
				List<Object[]> attendanceCountList = userLoginDetailsDAO.getAttendanceForMultiDate(fromDate, toDate,userId);
				alertCoreDashBoardVO.setAttendedCount(commonMethodsUtilService.getLongValueForObject(attendanceCountList.get(0)[1]));
				alertCoreDashBoardVO.setTotalHours(commonMethodsUtilService.getStringValueForObject(loginDtlsList.get(0)[3]));
				
				alertCoreDashBoardVO.setNoOfAlertCreated(commonMethodsUtilService.getLongValueForObject(countList.get(0)[1]));
				
				alertCoreDashBoardVO.setRange("multiple");
				
			}
			if(coreDashBoardVOs != null && coreDashBoardVOs.size() > 0){
				alertCoreDashBoardVO.setSubList(coreDashBoardVOs);
			}
			return alertCoreDashBoardVO;
		}catch(Exception e){
			LOG.error("Error occured getUserLogingDtls() method of AlertService",e);
		}
		return null;
	}
	public void buildAlertDetailsForLonginUser(List<AlertCoreDashBoardVO> coreDashBoardVOs, List<Object[]> userAlertDtlsList){
		try{
			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
			if(userAlertDtlsList != null && userAlertDtlsList.size() > 0){
				for(Object[] param : userAlertDtlsList){
					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					alertCoreDashBoardVO.setCreatedDate(commonMethodsUtilService.getStringValueForObject(param[1]));
					alertCoreDashBoardVO.setCategory(commonMethodsUtilService.getStringValueForObject(param[2]));
					alertCoreDashBoardVO.setTitle(commonMethodsUtilService.getStringValueForObject(param[3]));
					alertCoreDashBoardVO.setDesc(commonMethodsUtilService.getStringValueForObject(param[4]));
					alertCoreDashBoardVO.setName(commonMethodsUtilService.getStringValueForObject(param[5]));
					alertCoreDashBoardVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[6]));
					alertCoreDashBoardVO.setEmail(commonMethodsUtilService.getStringValueForObject(param[7]));
					alertCoreDashBoardVO.setDepartment(commonMethodsUtilService.getStringValueForObject(param[8]));
					alertCoreDashBoardVO.setImpactLevel(commonMethodsUtilService.getStringValueForObject(param[9]));
					if(param[15] != null){
						alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[15]));
					}else if(param[14] != null){
						alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[14]));
					}else if(param[13] != null){
						alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[13]));
					}else if(param[12] != null){
						alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[12]));
					}else if(param[11] != null){
						alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[11]));
					}else if(param[10] != null){
						alertCoreDashBoardVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[10]));
					}
					alertCoreDashBoardVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[16]));
					alertCoreDashBoardVO.setOfficerMobileNo(commonMethodsUtilService.getStringValueForObject(param[17]));
					coreDashBoardVOs.add(alertCoreDashBoardVO);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<AlertsSummeryVO> getStatusWiseAlertsCountSummery(List<Integer> daysLst){
		
		List<AlertsSummeryVO> returnList = new ArrayList<AlertsSummeryVO>();
		Map<Long,AlertsSummeryVO> statusMap = new HashMap<Long, AlertsSummeryVO>();
		try{
			List<AlertStatus> statusList =  alertStatusDAO.getAll();
			 if(commonMethodsUtilService.isListOrSetValid(statusList)){
			 for(AlertStatus status : statusList)
				 {
					 AlertsSummeryVO vo = new AlertsSummeryVO();
					 vo.setId(status.getAlertStatusId());
					 vo.setName(status.getAlertStatus());
					 //returnList.add(vo);
					 statusMap.put(status.getAlertStatusId(), vo);
				 }
			 }
			 
			if(daysLst!=null && daysLst.size()>0){
					for(Integer day:daysLst){
						
						Date today 	 =  dateUtilService.getCurrentDateAndTime();
						Date fromDate =  null;
						if(today!=null){
							fromDate =  getPrevDayForNoOfDays(day, today);
						}
						
						 List<Object[]> statusWiseCount = alertDAO.getStatusWiseAlertsCountByDates(fromDate,today); 
						 getEfficiencyOfDatesForEachStatus(statusWiseCount,returnList,statusMap,day);
					}
				}
			
			if(commonMethodsUtilService.isMapValid(statusMap)){
				//returnList.addAll(statusMap.values());
				for(Map.Entry<Long,AlertsSummeryVO > entry :statusMap.entrySet()){
					AlertsSummeryVO statusVo = entry.getValue();
					returnList.add(statusVo);
					if(commonMethodsUtilService.isListOrSetValid(statusVo.getEffcncyRslts())){
						for (AlertsSummeryVO daysVo : statusVo.getEffcncyRslts()) {
							
							String percentage = "0.0";
							if(statusVo.getTtlAlrtss() != null && statusVo.getTtlAlrtss().longValue() >0l)
							 percentage = (new BigDecimal(daysVo.getEffcncyAlerts()*(100.0)/statusVo.getTtlAlrtss())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
							
							float prcntgeFlt = Float.parseFloat(percentage);
							if(daysVo.getDays()==30){
								if(prcntgeFlt>=90.0f){
									daysVo.setClrFrEffcncy("green");
								}
							}else{
								if(prcntgeFlt>=95.0f){
									daysVo.setClrFrEffcncy("green");
								}
							}
							
							daysVo.setEffcncyPrcnt(percentage);
							
						}
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getStatusWiseAlertsCountSummery() method of AlertService",e);
		}
		return returnList;
	}
	
	public Date getPrevDayForNoOfDays(int noOfDays, Date today){
		Calendar cal = new GregorianCalendar();
		cal.setTime(today);
		
		cal.add(Calendar.DAY_OF_MONTH, -noOfDays);
		Date prevDay = cal.getTime();
		return prevDay;
	}
	public void getEfficiencyOfDatesForEachStatus(List<Object[]> statusWiseCount,List<AlertsSummeryVO> returnList,Map<Long,AlertsSummeryVO> statusMap,Integer day){
		
		try{
			
			Long indiTtlCnt      = 0l;
			Long indiEffcncyCnt  = 0l;
			if(commonMethodsUtilService.isListOrSetValid(statusWiseCount)){
				for (Object[] obj : statusWiseCount) {
					indiTtlCnt = indiTtlCnt+Long.valueOf(obj[0].toString());
					AlertsSummeryVO statusVo = statusMap.get(commonMethodsUtilService.getLongValueForObject(obj[1]));
					if(statusVo != null){
							AlertsSummeryVO daysVo = new AlertsSummeryVO();
							daysVo.setEffcncyType("Last "+day+" Days");
							daysVo.setEffcncyPrcnt("0.0");
							daysVo.setClrFrEffcncy("red");
							//indiEffcncyCnt = indiEffcncyCnt+Long.valueOf(obj[0].toString());
					
							statusVo.setTtlAlrtss(statusVo.getTtlAlrtss()+indiTtlCnt);
							daysVo.setEffcncyAlerts(daysVo.getEffcncyAlerts()+Long.valueOf(obj[0].toString()));
							daysVo.setDays(day);
							statusVo.getEffcncyRslts().add(daysVo);
					}
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getEfficiencyOfDatesForEachStatus() method of AlertService",e);
		}
	}
	
	public List<KeyValueVO> getStatusWiseViewWiseCounts(Long viewType,Long departmentId,Long locationId,String locationType,String searchType,String startDate,String endDate){
		List<KeyValueVO> voList = new ArrayList<KeyValueVO>(0);
		try {
			if(viewType == 1l){//day wise viwe
				getDayWiseAlertsCounts(departmentId,locationId,locationType,searchType,startDate,endDate,voList);
			}else if(viewType == 2l){//week wise view
				
			}else if(viewType == 3l){//month wise view
				
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getStatusWiseOverAllCounts", e);
		}
		return voList;
	}
	
	public void getDayWiseAlertsCounts(Long departmentId,Long locationId,String locationType,String searchType,String startDate,String endDate,List<KeyValueVO> voList){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(startDate != null && endDate != null){
				Date fromDate = sdf.parse(startDate),toDate = sdf.parse(endDate);
				List<Date> dates = commonMethodsUtilService.getBetweenDates(fromDate,toDate);
				if(dates != null && dates.size() > 0){
					//0-statusId,1-status,2-date,3-count
					List<Object[]> objList = alertDAO.getDayWiseAlertsCounts(departmentId,fromDate,toDate);
					if(objList != null && objList.size() > 0){
						for (Object[] objects : objList) {
							KeyValueVO matchedStatusVO = getMatchedVO(voList,objects[0].toString());
							if(matchedStatusVO == null){
								matchedStatusVO = new KeyValueVO();
								matchedStatusVO.setId((Long)objects[0]);
								matchedStatusVO.setName(objects[1].toString());
								voList.add(matchedStatusVO);
							}
							matchedStatusVO = getMatchedVO(voList,objects[0].toString());
							KeyValueVO matchedDateVO = getMatchedVO(matchedStatusVO.getSubList(),objects[2].toString());
							if(matchedDateVO == null){
								matchedDateVO = new KeyValueVO();
								matchedDateVO.setName(objects[2].toString());
								matchedDateVO.setCount((Long)objects[3]);
								matchedStatusVO.getSubList().add(matchedDateVO);
							}
							
						}
					}
				}
			}
				
		} catch (Exception e) {
			LOG.error("Exception raised at getDayWiseAlertsCounts", e);
		}
	}
	
	public KeyValueVO getMatchedVO(List<KeyValueVO> voList,String id){
		if(voList != null && voList.size() > 0){
			for (KeyValueVO keyValueVO : voList) {
				if(keyValueVO.getId().toString().equalsIgnoreCase(id))
					return keyValueVO;
			}
		}
		return null;
	}
	

	public List<KeyValueVO> getRelatedDepartmentsForIssueType(Long issueTypeId){
		List<KeyValueVO> returnList = new ArrayList<KeyValueVO>();
		try {
			List<Object[]> list = govtDepartmentIssueTypeDAO.getRelatedDepartmentsForIssueType(issueTypeId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					KeyValueVO vo = new KeyValueVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured getRelatedDepartmentsForIssueType() method of AlertService{}",e);
		}
		return returnList;
	}
	
	public List<KeyValueVO> getUrbanLocalitiesForMuncipality(Long lebId){
		List<KeyValueVO> returnList = new ArrayList<KeyValueVO>();
		try {
			List<Object[]> list = urbanLocalityDAO.getUrbanLocalitiesForMuncipality(lebId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					KeyValueVO vo = new KeyValueVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
			
			KeyValueVO othervo = new KeyValueVO();
			othervo.setId(99999L);
			othervo.setName("OTHERS");
			returnList.add(othervo);
		} catch (Exception e) {
			LOG.error("Error occured getUrbanLocalitiesForMuncipality() method of AlertService{}",e);
		}
		return returnList;
	}
	
	public List<KeyValueVO> getUrbanBlocksForLocality(Long localityId){
		List<KeyValueVO> returnList = new ArrayList<KeyValueVO>();
		try {
			List<Object[]> list = urbanBlockDAO.getUrbanBlocksForLocality(localityId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					KeyValueVO vo = new KeyValueVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					if(vo.getId() == 99999l)
						vo.setName(obj[1] != null ? obj[1].toString():"");
					else
						vo.setName(obj[1] != null ? "Block-"+obj[1].toString():"");
					returnList.add(vo);
				}
			}
			
			/*KeyValueVO othervo = new KeyValueVO();
			othervo.setId(99999L);
			othervo.setName("OTHERS");
			returnList.add(othervo);*/
		} catch (Exception e) {
			LOG.error("Error occured getUrbanBlocksForLocality() method of AlertService{}",e);
		}
		return returnList;
	}
	
	public List<IdNameVO> getAllLebsByDistrictID(Long districtId){

		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		List<Object[]> mandals = localElectionBodyDAO.findByDistrictId(districtId);
		if(mandals != null && mandals.size() > 0)
		{
			for(Object[] obj : mandals){
				IdNameVO objVO = new IdNameVO();
				objVO.setId((Long)obj[0]);
				objVO.setName(obj[1].toString() +" " +obj[2].toString());
				returnList.add(objVO);
			}
		}
		
		return returnList;
	}
	
	
	public List<AlertsSummeryVO> getAlertEfficiencyList(List<Integer> daysLst, List<Long> departmentIds,List<Long> sourceIds,boolean includeProposal,List<Long> alertstatusIds){
		LOG.debug(" Entered Into getAlertEfficiencyList");
		List<AlertsSummeryVO> finalList = new ArrayList<AlertsSummeryVO>();
		 
		try{
			
			if(daysLst!=null && daysLst.size()>0){
				for(Integer day:daysLst){
					AlertsSummeryVO temp = new AlertsSummeryVO();
					temp.setEffcncyType("Last "+day+" Days");
					temp.setEffcncyPrcnt("0.0");
					temp.setClrFrEffcncy("red");
					temp.setDays(day);
					Date today 	 =  dateUtilService.getCurrentDateAndTime();
					if(today!=null){
						getEfficiencyOfDates(today, temp, day, departmentIds,sourceIds,includeProposal,alertstatusIds);
					}
					
					finalList.add(temp);
				}
			}
			
			AlertsSummeryVO temp = new AlertsSummeryVO();
			temp.setEffcncyType(" Overall ");
			temp.setEffcncyPrcnt("0.0");
			temp.setClrFrEffcncy("red");
			temp.setDays(0);
			getEfficiencyOfDates(null, temp, 0, departmentIds,sourceIds,includeProposal,alertstatusIds);
			finalList.add(temp);
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Raised in getAlertEfficiencyList");
		}
		return finalList;
	}
	
	public void getEfficiencyOfDates(Date today, AlertsSummeryVO temp, int days,  List<Long> departmentIds,List<Long> sourceIds,boolean includeProposal,List<Long> alertstatusIds){
		Date prevDay =  null;
		if(today!=null){
			prevDay =  getPrevDayForNoOfDays(days, today);
		}
		
		//List<Long> alertStatusIds = new ArrayList<Long>();
		List<Long> totalAlertStatusIds = new ArrayList<Long>(0);
		
		totalAlertStatusIds.addAll(IConstants.ALERT_STATUS_IDS);
		
		if(includeProposal)
			totalAlertStatusIds.add(13L);
		
		Long totalAlerts = alertDAO.getTotalAlertsByStatusIdsAndDates(prevDay,today,departmentIds,sourceIds,totalAlertStatusIds);
		
		//alertStatusIds.add(12l);
		//alertStatusIds.add(4l);
		Long completedAlerts = alertDAO.getTotalAlertsByStatusIdsAndDates(prevDay,today,departmentIds,sourceIds,alertstatusIds);
			
			
			temp.setTtlAlrtss(totalAlerts);
			temp.setEffcncyAlerts(completedAlerts);
			
			String percentage = "0.0";
			if(totalAlerts != null && totalAlerts.longValue() >0l)
			 percentage = (new BigDecimal(completedAlerts*(100.0)/totalAlerts)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			
			if(percentage != null){
			float prcntgeFlt = Float.parseFloat(percentage);
			
			if(temp.getDays()==30){
				if(prcntgeFlt>=90.0f){
					temp.setClrFrEffcncy("green");
				}
			}else{
				if(prcntgeFlt>=95.0f){
					temp.setClrFrEffcncy("green");
				}
			}
			}
			
			temp.setEffcncyPrcnt(percentage);
		
		
	}
	/* abcd
	 * Swadhin K Lenka
	 * @see com.itgrids.partyanalyst.service.IAlertService#getGrievanceReport(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String)
	 */
	public List<AlertOverviewVO> getGrievanceReport(String fromDateStr, String toDateStr, Long stateId,Long departmentId, Long sourceId, String rangeType,Long LocationId,Long stsId,String groupType){
		LOG.info("Entered in getTotalAlertGroupByLocationThenStatus() method of AlertService{}");
		try{  
			Date fromDate = null;        
			Date toDate = null; 
			String frmDate = null;
			String tDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
				frmDate = fromDateStr;
				tDate =	toDateStr;
			}
			Long otherTotal = 0L;
			Map<Long,Long> otherStatusIdAndCountMap = new HashMap<Long,Long>();
			AlertOverviewVO alertVO = null;    
			List<AlertOverviewVO> alertVOs = null;//new ArrayList<AlertVO>();
			List<AlertOverviewVO> alertVOs2 = null;//new ArrayList<AlertVO>();
			Map<Long,Long> locationIdAndCountMap = new HashMap<Long,Long>();
			//get all the alert status for  building the template
			List<Object[]> statusList = alertDepartmentStatusDAO.getAlertStatusByDepartmentId(departmentId);
			//for day
			List<String> dayList = DateUtilService.getDaysBetweenDatesStringFormat(fromDate, toDate);
			Collections.reverse(dayList);
			
			//get all the alert count group by Location then status.
			Map<Long,String> locationIdAndNameMap = new HashMap<Long,String>();
			Map<Long,Long> statusIdAndCountMap = null;//new HashMap<Long, Long>();  
			Map<Long,Map<Long,Long>> locationIdAndStatusIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
			List<Object[]> alertCountGrpByLocList = null;
			if(rangeType != null && !rangeType.isEmpty() && rangeType.length() > 0){
				alertCountGrpByLocList = alertDAO.getTotalAlertGroupByLocationThenStatus(fromDate, toDate, stateId, departmentId,sourceId,"District","two",LocationId,stsId);
			}else{//for tehsil and panchayat
				alertCountGrpByLocList = alertDAO.getTotalAlertGroupByBellowLocationThenStatus(fromDate, toDate, stateId, departmentId,sourceId, groupType,"two", LocationId);
				//only dist alerts status wise
				if(alertCountGrpByLocList != null && alertCountGrpByLocList.size() > 0){
					for(Object[] param : alertCountGrpByLocList){
						if(param[0] == null){
							otherStatusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
						}
					}
				}
			}
			if(alertCountGrpByLocList != null && alertCountGrpByLocList.size() > 0){
				for(Object[] param : alertCountGrpByLocList){  
					if(param[0] != null){
						//create a map of LocationId and its corresponding  alert count
						locationIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(locationIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0])))+commonMethodsUtilService.getLongValueForObject(param[4]));
						statusIdAndCountMap = locationIdAndStatusIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						if(statusIdAndCountMap != null){
							statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
						}else{
							statusIdAndCountMap = new HashMap<Long, Long>();
							statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
							locationIdAndStatusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),statusIdAndCountMap);
						}  
						locationIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					}else{
						otherTotal = commonMethodsUtilService.getLongValueForObject(commonMethodsUtilService.getLongValueForObject(param[4]));
					}
				}
			}
			//get all alert count group by location then day
			Map<String,Long> dayAndCountMap = null;//new HashMap<Long, Long>();  
			Map<Long,Map<String,Long>> locationIdAndDayAndCountMap = new HashMap<Long,Map<String,Long>>();
			
			if(rangeType != null && !rangeType.isEmpty() && rangeType.length() > 0){
				List<Object[]> alertCountGrpByDayList = alertDAO.getTotalAlertGroupByLocationThenStatus(fromDate, toDate, stateId, departmentId,sourceId,"District","day",LocationId,stsId);    
				if(alertCountGrpByDayList != null && alertCountGrpByDayList.size() > 0){
					for(Object[] param : alertCountGrpByDayList){  
						if(param[0] != null){
							dayAndCountMap = locationIdAndDayAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
							if(dayAndCountMap != null){
								dayAndCountMap.put(commonMethodsUtilService.getStringValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[3]));
							}else{
								dayAndCountMap = new HashMap<String, Long>();
								dayAndCountMap.put(commonMethodsUtilService.getStringValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[3]));
								locationIdAndDayAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),dayAndCountMap);
							}  
						}
					}
				}
			}
			
			//build final vo to sent to ui
			List<AlertOverviewVO> finalList = new ArrayList<AlertOverviewVO>();
			AlertOverviewVO innerListAlertVO = null;
			if(locationIdAndStatusIdAndCountMap.size() > 0){
				for(Entry<Long,Map<Long,Long>> entry : locationIdAndStatusIdAndCountMap.entrySet()){
					statusIdAndCountMap = entry.getValue();
					dayAndCountMap = locationIdAndDayAndCountMap.get(entry.getKey());
					if(statusIdAndCountMap.size() > 0){
						//for status
						if(statusList != null && statusList.size() > 0){
							alertVOs = new ArrayList<AlertOverviewVO>();
							innerListAlertVO = new AlertOverviewVO();
							for(Object[] param : statusList){
								alertVO = new AlertOverviewVO();
								alertVO.setStatusTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
								alertVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[1]));
								alertVOs.add(alertVO);  
							}
						}
						// for date range
						if(rangeType != null && !rangeType.isEmpty() && rangeType.length() > 0){
							if(rangeType != null && !rangeType.isEmpty() && rangeType.length() > 0 && rangeType.equalsIgnoreCase("day")){
								if(dayList != null && dayList.size() > 0){
									alertVOs2 = new ArrayList<AlertOverviewVO>();
									//innerListAlertVO = new AlertOverviewVO();
									for(String param : dayList){
										alertVO = new AlertOverviewVO();
										alertVO.setDay(commonMethodsUtilService.getStringValueForObject(param));
										alertVOs2.add(alertVO);  
									}
								}
								for(AlertOverviewVO param : alertVOs2){
									if(dayAndCountMap.get(param.getDay()) != null){
										param.setTotalAlertCnt(dayAndCountMap.get(param.getDay()));  
									}else{
										param.setTotalAlertCnt(0l);  
									}
								}
								innerListAlertVO.setSubList2(alertVOs2);
							}else if(rangeType != null && !rangeType.isEmpty() && rangeType.length() > 0 && rangeType.equalsIgnoreCase("week")){
								LinkedHashMap<String,List<String>> weekAndDaysMap = null;
								weekAndDaysMap = DateUtilService.getTotalWeeksMap(fromDate, toDate);
								
								if(weekAndDaysMap != null && weekAndDaysMap.size() > 0){
									alertVOs2 = new ArrayList<AlertOverviewVO>();
									//innerListAlertVO = new AlertOverviewVO();
									for(Entry<String,List<String>> entry2 : weekAndDaysMap.entrySet()){
										alertVO = new AlertOverviewVO();
										alertVO.setDay(commonMethodsUtilService.getStringValueForObject(entry2.getKey()));
										alertVOs2.add(alertVO);  
									}
								}
								for(AlertOverviewVO param : alertVOs2){
									if(weekAndDaysMap.get(param.getDay()) != null){
										Long total = 0l;
										for(String day:weekAndDaysMap.get(param.getDay())){
											if(dayAndCountMap.get(day) != null){
												total += dayAndCountMap.get(day);
											}
										}
										param.setTotalAlertCnt(total);  
									}else{
										param.setTotalAlertCnt(0l);  
									}
								}
								innerListAlertVO.setSubList2(alertVOs2);
							}else if(rangeType != null && !rangeType.isEmpty() && rangeType.length() > 0 && rangeType.equalsIgnoreCase("month")){
								LinkedHashMap<String,List<String>> weekAndDaysMap = null;
								weekAndDaysMap = getMonthWeekAndDaysList(fromDateStr, toDateStr,"month");
								if(weekAndDaysMap != null && weekAndDaysMap.size() > 0){
									alertVOs2 = new ArrayList<AlertOverviewVO>();
									//innerListAlertVO = new AlertOverviewVO();
									for(Entry<String,List<String>> entry2 : weekAndDaysMap.entrySet()){
										alertVO = new AlertOverviewVO();
										alertVO.setDay(commonMethodsUtilService.getStringValueForObject(entry2.getKey()));
										alertVOs2.add(alertVO);
									}
								}
								for(AlertOverviewVO param : alertVOs2){
									if(weekAndDaysMap.get(param.getDay()) != null){
										Long total = 0l;
										for(String day:weekAndDaysMap.get(param.getDay())){
											if(dayAndCountMap.get(day) != null){
												total += dayAndCountMap.get(day);
											}
										}
										param.setTotalAlertCnt(total);  
									}else{
										param.setTotalAlertCnt(0l);  
									}
								}
								Collections.reverse(alertVOs2);
								innerListAlertVO.setSubList2(alertVOs2);
							}
						}
						
						
						for(AlertOverviewVO param : alertVOs){
							if(statusIdAndCountMap.get(param.getStatusTypeId()) != null){
								param.setTotalAlertCnt(statusIdAndCountMap.get(param.getStatusTypeId()));  
							}else{
								param.setTotalAlertCnt(0l);  
							}
						}
						innerListAlertVO.setSubList1(alertVOs);
						if(locationIdAndNameMap.get(entry.getKey()) != null){
							innerListAlertVO.setId(entry.getKey());
							innerListAlertVO.setName(locationIdAndNameMap.get(entry.getKey()));
							
						}
						if(locationIdAndCountMap.get(entry.getKey()) != null){
							innerListAlertVO.setTotalAlertCnt(locationIdAndCountMap.get(entry.getKey()));
						}
						finalList.add(innerListAlertVO);     
					}
				}
			} 
			if(rangeType != null && !rangeType.isEmpty() && rangeType.length() > 0){
				
				// collect status wise count
				Map<Long,Long> sttusIdAndCountMap = new HashMap<Long,Long>();
				if(locationIdAndStatusIdAndCountMap != null && locationIdAndStatusIdAndCountMap.size() > 0){
					for(Entry<Long,Map<Long,Long>> param : locationIdAndStatusIdAndCountMap.entrySet()){
						Map<Long,Long> map = param.getValue();
						if(map != null && map.size() > 0){
							for(Entry<Long,Long> param2 : map.entrySet()){
								Long cnt = sttusIdAndCountMap.get(param2.getKey());
								if(cnt != null){
									sttusIdAndCountMap.put(param2.getKey(), cnt+param2.getValue());
								}else{
									sttusIdAndCountMap.put(param2.getKey(), param2.getValue());
								}
							}
						}
					}
				}
				if(finalList != null && finalList.get(0) != null && finalList.get(0).getSubList1() != null ){
					for(AlertOverviewVO param : finalList.get(0).getSubList1()){
						Long statusId = param.getStatusTypeId();
						if(sttusIdAndCountMap != null && sttusIdAndCountMap.get(statusId) != null){
							param.setGrandTotal(sttusIdAndCountMap.get(statusId));
						}
					}
				}
				// collect day wise count
				Map<String,Long> dateAndCountMap = new HashMap<String,Long>();
				if(finalList != null && finalList.get(0) != null && finalList.get(0).getSubList2() != null ){
					for(AlertOverviewVO param : finalList.get(0).getSubList2()){
						dateAndCountMap.put(param.getDay(), 0L);
					}
				}
				
				if(finalList != null && finalList.size() > 0){
					for(AlertOverviewVO param : finalList){      
						if(param.getSubList2() != null && param.getSubList2().size() > 0){
							for(AlertOverviewVO param2 : param.getSubList2()){
								String dayName = param2.getDay();
								Long alertCount = param2.getTotalAlertCnt();
								dateAndCountMap.put(dayName, alertCount+dateAndCountMap.get(dayName));
							}
						}
						
					}
				}
				if(finalList != null && finalList.get(0) != null && finalList.get(0).getSubList2() != null ){
					for(AlertOverviewVO param : finalList.get(0).getSubList2()){
						String dt = param.getDay();
						if(dateAndCountMap != null && dateAndCountMap.get(dt) != null){
							param.setGrandTotal(dateAndCountMap.get(dt));
						}
					}
				}
			}
			//push for other count
			AlertOverviewVO otherAlertVO = new AlertOverviewVO();
			List<AlertOverviewVO> alertVOList = null;
			if(otherStatusIdAndCountMap != null && otherStatusIdAndCountMap.size() > 0){
				otherAlertVO.setId(0L);
				otherAlertVO.setName("OTHER");
				otherAlertVO.setTotalAlertCnt(otherTotal);
				alertVOList = new ArrayList<AlertOverviewVO>();
				for(Object[] param : statusList){
					alertVO = new AlertOverviewVO();
					alertVO.setStatusTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
					alertVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[1]));
					alertVOList.add(alertVO);  
				}
				for(AlertOverviewVO param : alertVOList){
					if(otherStatusIdAndCountMap.get(param.getStatusTypeId()) != null){
						param.setTotalAlertCnt(otherStatusIdAndCountMap.get(param.getStatusTypeId()));  
					}else{
						param.setTotalAlertCnt(0l);  
					}
				}
				otherAlertVO.setSubList1(alertVOList);
				finalList.add(otherAlertVO);  
			}
			//Getting feebback alert
			//Santosh
			if(sourceId == 0l || sourceId == 4l || sourceId == 5l){//Getting only feebback alert for social media and call center
				getLocationWiseFeebbackAlertCnt(frmDate, tDate, stateId, departmentId,sourceId,rangeType, groupType,LocationId,finalList);	
			}
			return finalList;  
	  }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getTotalAlertGroupByLocationThenStatus() method of AlertService{}");
	  }
		return null;
	}   
	
	public void getLocationWiseFeebbackAlertCnt(String fromDateStr, String toDateStr,Long stateId,Long departmentId,Long sourceId,String rangeType,String groupType,Long LocationId,List<AlertOverviewVO> finalList){
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Object[]> feebbackStatusObjLst = null;
			List<Object[]> pendingFeebbakObjLst = null;
			List<Object[]> reopeObjLst = null;
			List<Object[]> reopeObjLstForOfficer = null;
			
			
			List<Object[]> rtrnfeedbackStatusObjLst = alertFeedbackStatusDAO.getFeedBackStatus();
		 	if(rangeType != null && !rangeType.isEmpty() && rangeType.length() > 0){
				feebbackStatusObjLst = alertDAO.getLocationWisefeedbackAlertCnt(fromDate, toDate, stateId, departmentId, sourceId, "district", "feebbackStatus", null, null);
				pendingFeebbakObjLst = alertDAO.getLocationWisefeedbackAlertCnt(fromDate, toDate, stateId, departmentId, sourceId, "district", "pendingFeedback", null,new ArrayList<Long>(){{add(12l);}});
				reopeObjLst = alertDAO.getLocationWiseReopenCount(fromDate, toDate, stateId, departmentId, sourceId,"district","callcenter", null);
				reopeObjLstForOfficer = alertDAO.getLocationWiseReopenCount(fromDate, toDate, stateId, departmentId, sourceId,"district","officer", null); 
			}else{//for tehsil and panchayat
				feebbackStatusObjLst = alertDAO.getLocationWisefeedbackAlertCnt(fromDate, toDate, stateId, departmentId, sourceId, groupType, "feebbackStatus",LocationId,null);
				pendingFeebbakObjLst = alertDAO.getLocationWisefeedbackAlertCnt(fromDate, toDate, stateId, departmentId, sourceId, groupType, "pendingFeedback",LocationId, new ArrayList<Long>(){{add(12l);}});
				reopeObjLst = alertDAO.getLocationWiseReopenCount(fromDate, toDate, stateId, departmentId, sourceId,groupType,"callcenter", LocationId);
				reopeObjLstForOfficer = alertDAO.getLocationWiseReopenCount(fromDate, toDate, stateId, departmentId, sourceId,groupType,"officer", LocationId);
			}
		 	
		 	Map<Long,AlertOverviewVO> locationMap = new HashMap<Long,AlertOverviewVO>();
			prepareLocationWiseFeebbackAlert(feebbackStatusObjLst,rtrnfeedbackStatusObjLst,locationMap);//feebback status alert
			prepareLocationWiseFeebbackAlert(pendingFeebbakObjLst,rtrnfeedbackStatusObjLst,locationMap);//pending feebback alert 
			prepareLocationWiseReopenAlert(finalList,reopeObjLst,reopeObjLstForOfficer);
			//Calculating Grand Total alert
			Map<String,Long> totalCntMap = new HashMap<String, Long>();
			if(locationMap != null && locationMap.size() > 0){
				for(Entry<Long, AlertOverviewVO> entry:locationMap.entrySet()){
					if(entry.getValue().getSubList1() != null && entry.getValue().getSubList1().size() >0){
						for(AlertOverviewVO statusVO:entry.getValue().getSubList1()){
							Long alertcnt = totalCntMap.get(statusVO.getStatusType());
							 if(alertcnt == null){
								 totalCntMap.put(statusVO.getStatusType(), 0l);
							 }
							 totalCntMap.put(statusVO.getStatusType(), totalCntMap.get(statusVO.getStatusType())+statusVO.getTotalAlertCnt());
						}
					}
				}
			}
			
			//Setting into final list
			if(finalList != null && finalList.size() > 0){
				for(AlertOverviewVO locationVO:finalList){
					if(locationVO != null && locationVO.getSubList1() != null){
						if(locationMap.get(locationVO.getId()) != null && locationMap.get(locationVO.getId()).getSubList1() != null){
							locationVO.getSubList1().addAll(locationMap.get(locationVO.getId()).getSubList1());
						}else{
							locationVO.getSubList1().addAll(getFeedBackStatusList(rtrnfeedbackStatusObjLst));
						}
					}
				}
			}
			
			
			 //Setting grand total alert cnt into final list
			  settingGrandTotalAlert(finalList,totalCntMap);
			  
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getLocationWiseFeebbackAlertCnt() method of AlertService{}");
		}
	}
	public void prepareLocationWiseFeebbackAlert(List<Object[]> objList,List<Object[]> statusObjLst,Map<Long,AlertOverviewVO> locationMap){
		try{
			if(objList != null && objList.size() > 0){
				for(Object[] param:objList){
					AlertOverviewVO locationVO = locationMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					 if(locationVO == null){
						 locationVO = new AlertOverviewVO();
						 locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						 locationVO.setSubList1(getFeedBackStatusList(statusObjLst));
						 locationMap.put(locationVO.getId(), locationVO);
					 }
					 AlertOverviewVO statusMatchVO = getFeedBackStatusMatchVO(locationVO.getSubList1(), commonMethodsUtilService.getStringValueForObject(param[3]));
					  if(statusMatchVO != null){
						  statusMatchVO.setTotalAlertCnt(commonMethodsUtilService.getLongValueForObject(param[4])); //alertCnt
					  }
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured prepareLocationWiseFeebbackAlert() method of AlertService{}");
		}
	}
	public LinkedHashMap<String,List<String>> getMonthWeekAndDaysList(String startDate,String endDate,String type){
		LinkedHashMap<String,List<String>> returnDays = new LinkedHashMap<String, List<String>>();
    	try{
		
		List<String> wkDays = new ArrayList<String>();
		List<String> daysArr = new ArrayList<String>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("MMM-yyyy");
		if(type != null && type.trim().equalsIgnoreCase("month")){
		 List<String> mntDays = alertStatusDAO.getMonthAndYear(sdf.parse(startDate),sdf.parse(endDate));
			int i = 1;
			for (String string : mntDays) {
				Date dateee = sdf1.parse(string);
				cal.setTime(dateee);
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
				Date monthStart = cal.getTime();
				
				cal.setTime(dateee);
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				Date monthEnd = cal.getTime();
				 
				cal.setTime(sdf.parse(startDate));
				Date  strDate = cal.getTime();
				cal.setTime(sdf.parse(endDate));
				Date  edDate = cal.getTime();
				
				if(i == 1){
					if(strDate.compareTo(monthStart) > 0){
						monthStart = strDate;
					}
				}
				if(i == mntDays.size()){
					if(monthEnd.compareTo(edDate) > 0){
						monthEnd = edDate;
					}
				}
				
				daysArr = dateUtilService.getDaysBetweenDatesStringFormat(monthStart,monthEnd);
				returnDays.put(string,daysArr);
				i++;
			}
		}else if(type != null && type.trim().equalsIgnoreCase("week")){
			wkDays  = commonMethodsUtilService.getBetweenWeeks(sdf.parse(startDate),sdf.parse(endDate),"yyyy-MM-dd");
			int i=1;
				for (String string : wkDays) {
					String[] days = string.split("to");
					daysArr = dateUtilService.getDaysBetweenDatesStringFormat(sdf.parse(days[0]),sdf.parse(days[1]));
					returnDays.put("week"+i,daysArr);
					i++;
			}
		}else if(type != null && type.trim().equalsIgnoreCase("today")){
			List<String> noOfDays = dateUtilService.getDaysBetweenDatesStringFormat(sdf.parse(startDate),sdf.parse(endDate));
			int i=1;
				for (String string : noOfDays) {
					daysArr = dateUtilService.getDaysBetweenDatesStringFormat(sdf.parse(string),sdf.parse(string));
					returnDays.put("day"+i, daysArr);
					i++;
				}
		   }
    	}catch(Exception e){
    		LOG.error("Error occured getMonthWeekAndDays() method of AlertManagementSystemService",e);
    	}
		
		return returnDays;
	}
	 public List<AlertOverviewVO> getAlertImpactLocationWiseLocationSubTemplate(List<Long> locationIds){
		 List<AlertOverviewVO> locationLevelList = new ArrayList<AlertOverviewVO>(0);
		  try{
			  List<Object[]> locationObjLst = null;
			  locationObjLst = regionScopesDAO.getAlertLocationLevelById(locationIds);
			  if(locationObjLst != null && locationObjLst.size() > 0){
				  for(Object[] param:locationObjLst){
					  Long LevelId = commonMethodsUtilService.getLongValueForObject(param[0]);
					  if(LevelId == 5l || LevelId == 7l){ /* MANDAL/MUNICIPALITY */ 
						 AlertOverviewVO locationVO = getImpactLevelMtchVO(locationLevelList,5l);
						  if(locationVO == null){
							     locationVO = new AlertOverviewVO();
								 locationVO.setId(5l);
								 locationVO.setName("MANDAL/MUNICIPALITY");
								 locationLevelList.add(locationVO);
						  }
					  }else if(LevelId == 6l || LevelId == 8l || LevelId == 11l){/* VILLAGE/WARD/Hamlet */
						  AlertOverviewVO locationVO = getImpactLevelMtchVO(locationLevelList,6l);
						   if(locationVO == null){
							      locationVO = new AlertOverviewVO();
								  locationVO.setId(6l);
								  locationVO.setName("VILLAGE/WARD/HAMLET");
								  locationLevelList.add(locationVO);
						   }
					  }else{
						AlertOverviewVO locationVO = new AlertOverviewVO();
						locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						locationLevelList.add(locationVO);
					  }
				  }
			  }
		  }catch(Exception e){
			 e.printStackTrace();
			LOG.error("Error occured getAlertImpactLevelWiseLocationSubTemplate() method of AlertService{}");	  
		  }
		  return locationLevelList;
	}
	
	public KeyValueVO getAverageIssuePendingDays(String fromDateStr ,String toDateStr,List<Long> departmentIds,List<Long> sourceIds,List<Long> alertstatusIds){
		KeyValueVO vo = new KeyValueVO();
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			
			Long diffSum = 0l;
			List<Object[]> alertDiffTime = null; 
			if(alertstatusIds != null && alertstatusIds.size() > 0){
				alertDiffTime = alertDAO.getDifferenceTime(fromDate,toDate,departmentIds,sourceIds,alertstatusIds);
			}
			
			if(commonMethodsUtilService.isListOrSetValid(alertDiffTime)){
				for(Object[] obj :alertDiffTime){
					diffSum = diffSum+commonMethodsUtilService.getLongValueForObject(obj[4]);
				}
			}
			Double avgTime = 0.0d;
			if(diffSum != null && diffSum.longValue() >0l && commonMethodsUtilService.isListOrSetValid(alertDiffTime)){
				avgTime = Double.valueOf(diffSum/alertDiffTime.size());
			}
			if(avgTime != null && avgTime.doubleValue() > 0.0d){
				Double avgDays = avgTime/24;
				if(avgDays != null && avgDays.doubleValue()>0.0d){
					//Long iPart = Long.valueOf(avgDays.toString());
					//Double fPart = avgDays-iPart;
					String days = Double.toString(avgDays);
				     String[] convert = days.split("\\.");

				     int a = Integer.parseInt(convert[0]);
				     Double b = avgDays-a;
				     int a1= 0;
					if( b>0.0d){
						Double hours =b*24;
						String hrs = Double.toString(hours);
					     String[] convert1 = hrs.split("\\.");

					      a1 = Integer.parseInt(convert1[0]);
						 //iPart1 =Long.valueOf(hours.toString());
					}
					//vo = new KeyValueVO();
					vo.setCount(Long.valueOf(a));//
					vo.setTotalCount(Long.valueOf(a1));
					
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getAverageIssuePendingDays() method of AlertService{}");
		}
		return vo;
	}
	public List<AlertOverviewVO> getGrievanceReportBasedOnLocation(String fromDateStr,String toDateStr,Long stateId,Long deptId,Long sourceId,Long locationId,Long statusId,String group,String pattern,String rangeType){
		try{
			List<AlertOverviewVO> alertOverviewvoList1 = new ArrayList<AlertOverviewVO>();
			List<AlertCoreDashBoardVO> dtlsList = new ArrayList<AlertCoreDashBoardVO>();
			Date fromDate = null;        
			Date toDate = null; 
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			if(group != null && !group.isEmpty() && group.equalsIgnoreCase("day") ){
				if(rangeType != null && !rangeType.isEmpty() && rangeType.equalsIgnoreCase("day") ){
					
					fromDate = sdf2.parse(pattern.trim());
					toDate = sdf2.parse(pattern.trim());
					fromDateStr = sdf.format(fromDate);
					toDateStr = sdf.format(toDate);
					alertOverviewvoList1 = getGrievanceReport(fromDateStr, toDateStr, stateId,deptId, sourceId, rangeType,locationId,null,null);
					alertOverviewvoList1.get(0).setFromDateStr(fromDateStr);
					alertOverviewvoList1.get(0).setToDateStr(toDateStr);
				}else if(rangeType != null && !rangeType.isEmpty() && rangeType.equalsIgnoreCase("week")){
					Map<String,List<String>> weekAndDaysMap = dateUtilService.getTotalWeeksMap(fromDate,toDate);
					List<String> daysList = null;
					if(weekAndDaysMap != null && weekAndDaysMap.size() > 0){
						daysList = weekAndDaysMap.get(pattern.trim());
						if(daysList != null && daysList.size() > 1){
							int len = daysList.size();
							fromDate = sdf2.parse(daysList.get(len-1).trim());
							toDate = sdf2.parse(daysList.get(0).trim());
							fromDateStr = sdf.format(fromDate);
							toDateStr = sdf.format(toDate);
							alertOverviewvoList1 = getGrievanceReport(fromDateStr, toDateStr, stateId,deptId, sourceId, rangeType,locationId,null,null);
							alertOverviewvoList1.get(0).setFromDateStr(fromDateStr);
							alertOverviewvoList1.get(0).setToDateStr(toDateStr);
						}else{
							fromDate = sdf2.parse(daysList.get(0).trim());
							toDate = sdf2.parse(daysList.get(0).trim());
							fromDateStr = sdf.format(fromDate);
							toDateStr = sdf.format(toDate);
							alertOverviewvoList1 = getGrievanceReport(fromDateStr, toDateStr, stateId,deptId, sourceId, rangeType,locationId,null,null);
							alertOverviewvoList1.get(0).setFromDateStr(fromDateStr);
							alertOverviewvoList1.get(0).setToDateStr(toDateStr);
						}
					}
				}else if(rangeType != null && !rangeType.isEmpty() && rangeType.equalsIgnoreCase("month")){
					LinkedHashMap<String,List<String>> monthAndDaysMap = null;
					monthAndDaysMap = getMonthWeekAndDaysList(fromDateStr, toDateStr,"month");
					List<String> daysList = null;
					if(monthAndDaysMap != null && monthAndDaysMap.size() > 0){
						daysList = monthAndDaysMap.get(pattern.trim());
						if(daysList != null && daysList.size() > 1){
							int len = daysList.size();
							fromDate = sdf2.parse(daysList.get(0).trim());
							toDate = sdf2.parse(daysList.get(len-1).trim());
							fromDateStr = sdf.format(fromDate);
							toDateStr = sdf.format(toDate);
							alertOverviewvoList1 = getGrievanceReport(fromDateStr, toDateStr, stateId,deptId, sourceId, rangeType,locationId,null,null);
							alertOverviewvoList1.get(0).setFromDateStr(fromDateStr);
							alertOverviewvoList1.get(0).setToDateStr(toDateStr);
						}else{
							fromDate = sdf2.parse(daysList.get(0).trim());
							toDate = sdf2.parse(daysList.get(0).trim());
							fromDateStr = sdf.format(fromDate);
							toDateStr = sdf.format(toDate);
							alertOverviewvoList1 = getGrievanceReport(fromDateStr, toDateStr, stateId,deptId, sourceId, rangeType,locationId,null,null);
							alertOverviewvoList1.get(0).setFromDateStr(fromDateStr);
							alertOverviewvoList1.get(0).setToDateStr(toDateStr);
						}
					}
				}
				//get alertIds here
				List<Long> alertIdList = alertDAO.getTotalAlertForGrievance(fromDate, toDate, stateId, deptId, sourceId, "district", locationId,null);
				List<Object[]> altDtlsList = alertDAO.getAlertDtlsForGrievance(alertIdList);
				setAlertDtls(dtlsList,altDtlsList);
				//merge here.
				alertOverviewvoList1.get(0).getAlertCoreDashBoardVOs().addAll(dtlsList);
				
			}else{
				alertOverviewvoList1 = getGrievanceReport(fromDateStr, toDateStr, stateId,deptId, sourceId, rangeType,locationId,statusId,null);
				List<Long> alertIdList = alertDAO.getTotalAlertForGrievance(fromDate, toDate, stateId, deptId, sourceId, "district", locationId,statusId);
				List<Object[]> altDtlsList = alertDAO.getAlertDtlsForGrievance(alertIdList);
				setAlertDtls(dtlsList,altDtlsList);
				//merge here.
				alertOverviewvoList1.get(0).getAlertCoreDashBoardVOs().addAll(dtlsList);
			}
			return alertOverviewvoList1;  
		}catch(Exception e){  
			e.printStackTrace();
			LOG.error("Error occured getGrievanceReportBasedOnLocation() method of AlertService{}");
		}
		return null;
	}
	public List<AlertCoreDashBoardVO> getGrievanceReportBasedOnLocationAndStatus(String fromDateStr,String toDateStr,Long stateId,Long deptId,Long sourceId,Long locationId,Long statusId){
		try{
			List<AlertCoreDashBoardVO> dtlsList = new ArrayList<AlertCoreDashBoardVO>();
			Date fromDate = null;        
			Date toDate = null; 
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Long> alertIdList = alertDAO.getTotalAlertForGrievance(fromDate, toDate, stateId, deptId, sourceId, "district", locationId,statusId);
			List<Object[]> altDtlsList = alertDAO.getAlertDtlsForGrievance(alertIdList);
			setAlertDtls(dtlsList,altDtlsList);
			return dtlsList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getGrievanceReportBasedOnLocationAndStatus() method of AlertService{}");
		}
		return null;  
	}
	public List<AlertCoreDashBoardVO> getGrievanceReportDtlsForBellowLocation(String fromDateStr,String toDateStr,Long stateId,Long deptId,Long sourceId,Long locationId,Long statusId,String areaType,String groupType){
		try{
			List<AlertCoreDashBoardVO> dtlsList = new ArrayList<AlertCoreDashBoardVO>();
			Date fromDate = null;        
			Date toDate = null; 
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			List<Long> alertIdList = alertDAO.getGrievanceReportDtlsForBellowLocation(fromDate, toDate, stateId, deptId, sourceId,locationId,statusId,areaType,groupType);
			List<Object[]> altDtlsList = alertDAO.getAlertDtlsForGrievance(alertIdList);
			setAlertDtls(dtlsList,altDtlsList);
			return dtlsList;
			
		}catch(Exception e){  
			e.printStackTrace();
			LOG.error("Error occured getGrievanceReportDtlsForBellowLocation() method of AlertService{}");
		}
		return null;
	}
	public List<IdNameVO> getDeptListForGrievance(){
		try{
			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
			IdNameVO idNameVO = null;
			List<Object[]> list = alertDAO.getDeptList();
			if(list != null && list.size() > 0){
				for(Object[] param : list){
					idNameVO = new IdNameVO();
					idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					idNameVOs.add(idNameVO);
				}
			}
			return idNameVOs;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getDeptList() method of AlertService{}");
		}
		return null;
	}
	/*
	 * Swadhin K Lenka
	 * @see com.itgrids.partyanalyst.service.IAlertService#getGrievanceReport(java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String)
	 */
	public List<AlertOverviewVO> getGrievanceReportDayWise(String fromDateStr, String toDateStr, Long stateId,Long departmentId, Long sourceId, String rangeType,Long LocationId,Long stsId,String groupType){
		LOG.info("Entered in getGrievanceReportDayWise() method of AlertService{}");
		try{  
			Date fromDate = null;        
			Date toDate = null; 
			String frmDteStr= null;
			String tDateStr = null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
				frmDteStr = fromDateStr;
				tDateStr = toDateStr;
			}
			Long otherTotal = 0L;
			Map<Long,Long> otherStatusIdAndCountMap = new HashMap<Long,Long>();
			AlertOverviewVO alertVO = null;    
			List<AlertOverviewVO> alertVOs = null;//new ArrayList<AlertVO>();
			List<AlertOverviewVO> alertVOs2 = null;//new ArrayList<AlertVO>();
			Map<String,Long> dateAndCountMap = new HashMap<String,Long>();
			//get all the alert status for  building the template
			List<Object[]> statusList = alertDepartmentStatusDAO.getAlertStatusByDepartmentId(departmentId);
			//for day
			List<String> dayList = DateUtilService.getDaysBetweenDatesStringFormat(fromDate, toDate);
			Collections.reverse(dayList);
			
			//get alert status count and and create a map of Date and its corresponding  alert count
			List<Object[]> alertCountList = null;
			if(rangeType != null && !rangeType.isEmpty() && rangeType.length() > 0){
				alertCountList = alertDAO.getTotalAlertGroupByDateThenStatus(fromDate, toDate, stateId, departmentId,sourceId,"District","One",LocationId,stsId);
			}else{
				alertCountList = alertDAO.getTotalAlertGroupByBellowLocationThenStatus(fromDate, toDate, stateId, departmentId,sourceId, groupType,"one", LocationId);
				//only dist alerts
				
				if(alertCountList.get(0)[0] == null){
					otherTotal = commonMethodsUtilService.getLongValueForObject(alertCountList.get(0)[2]);
				}
			}
			
			if(alertCountList != null && alertCountList.size() > 0){
				for(Object[] param : alertCountList){
					if(param[0] != null)
						dateAndCountMap.put(commonMethodsUtilService.getStringValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}  
			//get all the alert count group by Location then status.
			//Map<Long,String> locationIdAndNameMap = new HashMap<Long,String>();
			Map<Long,Long> statusIdAndCountMap = null;//new HashMap<Long, Long>();  
			Map<String,Map<Long,Long>> dateAndStatusIdAndCountMap = new HashMap<String,Map<Long,Long>>();
			List<Object[]> alertCountGrpByLocList = null;
			if(rangeType != null && !rangeType.isEmpty() && rangeType.length() > 0){
				alertCountGrpByLocList = alertDAO.getTotalAlertGroupByDateThenStatus(fromDate, toDate, stateId, departmentId,sourceId,"District","two",LocationId,stsId);
			}else{
				alertCountGrpByLocList = alertDAO.getTotalAlertGroupByBellowLocationThenStatus(fromDate, toDate, stateId, departmentId,sourceId, groupType,"two", LocationId);
				//only dist alerts status wise
				if(alertCountGrpByLocList != null && alertCountGrpByLocList.size() > 0){
					for(Object[] param : alertCountGrpByLocList){
						if(param[0] == null){
							otherStatusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
						}
					}
				}
			}
			if(alertCountGrpByLocList != null && alertCountGrpByLocList.size() > 0){
				for(Object[] param : alertCountGrpByLocList){  
					if(param[0] != null){
						statusIdAndCountMap = dateAndStatusIdAndCountMap.get(commonMethodsUtilService.getStringValueForObject(param[0]));
						if(statusIdAndCountMap != null){
							statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[3]));
						}else{
							statusIdAndCountMap = new HashMap<Long, Long>();
							statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[3]));
							dateAndStatusIdAndCountMap.put(commonMethodsUtilService.getStringValueForObject(param[0]),statusIdAndCountMap);
						}  
					}
				}
			}
			//aaaaaaa
			//build final vo to sent to ui
			List<AlertOverviewVO> finalList = new ArrayList<AlertOverviewVO>();
			AlertOverviewVO innerListAlertVO = null;
			List<String> daysList = null;
			if(rangeType != null && !rangeType.isEmpty() && rangeType.length() > 0){
				if(rangeType != null && !rangeType.isEmpty() && rangeType.length() > 0 && rangeType.equalsIgnoreCase("day")){
					if(dateAndStatusIdAndCountMap.size() > 0){
						for(Entry<String,Map<Long,Long>> entry : dateAndStatusIdAndCountMap.entrySet()){
							statusIdAndCountMap = entry.getValue();
							if(statusIdAndCountMap.size() > 0){
								//for status
								if(statusList != null && statusList.size() > 0){
									alertVOs = new ArrayList<AlertOverviewVO>();
									innerListAlertVO = new AlertOverviewVO();
									for(Object[] param : statusList){
										alertVO = new AlertOverviewVO();
										alertVO.setStatusTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
										alertVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[1]));
										alertVOs.add(alertVO);  
									}
								}
							}
							for(AlertOverviewVO param : alertVOs){
								if(statusIdAndCountMap.get(param.getStatusTypeId()) != null){
									param.setTotalAlertCnt(statusIdAndCountMap.get(param.getStatusTypeId()));  
								}else{
									param.setTotalAlertCnt(0l);  
								}
							}
							innerListAlertVO.setSubList1(alertVOs);
							innerListAlertVO.setDay(entry.getKey());
							
							if(dateAndCountMap.get(entry.getKey()) != null){
								innerListAlertVO.setTotalAlertCnt(dateAndCountMap.get(entry.getKey()));
							}
							//push from and to date
							
							
							fromDate = sdf2.parse(entry.getKey().trim());
							toDate = sdf2.parse(entry.getKey().trim());
							fromDateStr = sdf.format(fromDate);
							toDateStr = sdf.format(toDate);
							
							innerListAlertVO.setFromDateStr(fromDateStr);
							innerListAlertVO.setToDateStr(toDateStr);
							finalList.add(innerListAlertVO);   
						}
					}
				}else if(rangeType != null && !rangeType.isEmpty() && rangeType.length() > 0 && rangeType.equalsIgnoreCase("week")){
					LinkedHashMap<String,List<String>> weekAndDaysMap = null;
					weekAndDaysMap = DateUtilService.getTotalWeeksMap(fromDate, toDate);
					if(weekAndDaysMap != null && weekAndDaysMap.size() > 0){
						for(Entry<String,List<String>> entry : weekAndDaysMap.entrySet()){
							//for status
							if(statusList != null && statusList.size() > 0){
								alertVOs = new ArrayList<AlertOverviewVO>();
								innerListAlertVO = new AlertOverviewVO();
								for(Object[] param : statusList){
									alertVO = new AlertOverviewVO();
									alertVO.setStatusTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
									alertVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[1]));
									alertVOs.add(alertVO);  
								}
							}
							
							for(AlertOverviewVO param : alertVOs){
								if(weekAndDaysMap.get(entry.getKey()) != null){
									for(String day : weekAndDaysMap.get(entry.getKey())){
										Map<Long,Long> stsIdAndCntMap = dateAndStatusIdAndCountMap.get(day);
										if(stsIdAndCntMap != null && stsIdAndCntMap.size() > 0){
											if(stsIdAndCntMap.get(param.getStatusTypeId()) != null){
												param.setTotalAlertCnt(param.getTotalAlertCnt() + stsIdAndCntMap.get(param.getStatusTypeId()) );
											}
										}
									}
								}else{
									param.setTotalAlertCnt(0l);  
								}
							}
							innerListAlertVO.setSubList1(alertVOs);
							innerListAlertVO.setDay(entry.getKey());
							
							if(weekAndDaysMap.get(entry.getKey()) != null){
								for(String day : weekAndDaysMap.get(entry.getKey())){
									if(dateAndCountMap.get(day) != null){
										innerListAlertVO.setTotalAlertCnt(innerListAlertVO.getTotalAlertCnt() + dateAndCountMap.get(day));
									}
								}
							}else{
								innerListAlertVO.setTotalAlertCnt(0L);
							}
							//push from and to date
							daysList = entry.getValue();
							if(daysList != null && daysList.size() > 1){
								int len = daysList.size();
								fromDate = sdf2.parse(daysList.get(len-1).trim());
								toDate = sdf2.parse(daysList.get(0).trim());
								fromDateStr = sdf.format(fromDate);
								toDateStr = sdf.format(toDate);
								
							}else{
								fromDate = sdf2.parse(daysList.get(0).trim());
								toDate = sdf2.parse(daysList.get(0).trim());
								fromDateStr = sdf.format(fromDate);
								toDateStr = sdf.format(toDate);
								
							}
							innerListAlertVO.setFromDateStr(fromDateStr);
							innerListAlertVO.setToDateStr(toDateStr);
							finalList.add(innerListAlertVO);
						}
					}
				}else if(rangeType != null && !rangeType.isEmpty() && rangeType.length() > 0 && rangeType.equalsIgnoreCase("month")){
					LinkedHashMap<String,List<String>> weekAndDaysMap = null;
					weekAndDaysMap = getMonthWeekAndDaysList(fromDateStr, toDateStr,"month");
					
					if(weekAndDaysMap != null && weekAndDaysMap.size() > 0){
						for(Entry<String,List<String>> entry : weekAndDaysMap.entrySet()){
							//for status
							if(statusList != null && statusList.size() > 0){
								alertVOs = new ArrayList<AlertOverviewVO>();
								innerListAlertVO = new AlertOverviewVO();
								for(Object[] param : statusList){
									alertVO = new AlertOverviewVO();
									alertVO.setStatusTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
									alertVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[1]));
									alertVOs.add(alertVO);  
								}
							}
							
							for(AlertOverviewVO param : alertVOs){
								if(weekAndDaysMap.get(entry.getKey()) != null){
									for(String day : weekAndDaysMap.get(entry.getKey())){
										Map<Long,Long> stsIdAndCntMap = dateAndStatusIdAndCountMap.get(day);
										if(stsIdAndCntMap != null && stsIdAndCntMap.size() > 0){
											if(stsIdAndCntMap.get(param.getStatusTypeId()) != null){
												param.setTotalAlertCnt(param.getTotalAlertCnt() + stsIdAndCntMap.get(param.getStatusTypeId()) );
											}
										}
									}
								}else{
									param.setTotalAlertCnt(0l);  
								}
							}
							innerListAlertVO.setSubList1(alertVOs);
							innerListAlertVO.setDay(entry.getKey());
							
							if(weekAndDaysMap.get(entry.getKey()) != null){
								for(String day : weekAndDaysMap.get(entry.getKey())){
									if(dateAndCountMap.get(day) != null){
										innerListAlertVO.setTotalAlertCnt(innerListAlertVO.getTotalAlertCnt() + dateAndCountMap.get(day));
									}
								}
							}else{
								innerListAlertVO.setTotalAlertCnt(0L);
							}
							daysList = entry.getValue();
							if(daysList != null && daysList.size() > 1){
								int len = daysList.size();
								fromDate = sdf2.parse(daysList.get(0).trim());
								toDate = sdf2.parse(daysList.get(len-1).trim());
								fromDateStr = sdf.format(fromDate);
								toDateStr = sdf.format(toDate);
								
							}else{
								fromDate = sdf2.parse(daysList.get(0).trim());
								toDate = sdf2.parse(daysList.get(0).trim());
								fromDateStr = sdf.format(fromDate);
								toDateStr = sdf.format(toDate);
								
							}
							innerListAlertVO.setFromDateStr(fromDateStr);
							innerListAlertVO.setToDateStr(toDateStr);
							finalList.add(innerListAlertVO);
						}
					}
				}
			}
			//create a map of status id and count
			Map<Long,Long> statusIdAndTotalCntMap = new HashMap<Long,Long>();
			for(Object[] param : statusList){
				statusIdAndTotalCntMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), 0L);
			}
			if(finalList != null && finalList.size() > 0){
				for(AlertOverviewVO param : finalList){
					if(param.getSubList1() != null && param.getSubList1().size() > 0){
						for(AlertOverviewVO param2 : param.getSubList1()){
							statusIdAndTotalCntMap.put(param2.getStatusTypeId(), statusIdAndTotalCntMap.get(param2.getStatusTypeId())+param2.getTotalAlertCnt());
						}
					}
				}
			}
			if(finalList != null && finalList.size() > 0){
				if(finalList.get(0) != null && finalList.get(0).getSubList1() != null && finalList.get(0).getSubList1().size() > 0){
					for(AlertOverviewVO param : finalList.get(0).getSubList1()){
						if(statusIdAndTotalCntMap.get(param.getStatusTypeId()) != null){
							param.setGrandTotal(statusIdAndTotalCntMap.get(param.getStatusTypeId()));
						}
					}
				}
			}
			 /*Getting Feedback Alert */ 
			//Santosh
			if(sourceId ==0l || sourceId==4l || sourceId==5l){//Getting only feebback alert for social media and call center
			   gettingFeedbackAlert(frmDteStr,tDateStr,stateId,departmentId,sourceId,rangeType,LocationId,finalList);
			 }
			 
			return finalList;  
	  }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getGrievanceReportDayWise() method of AlertService{}");
	  }
		return null;
	} 
	
    public void gettingFeedbackAlert(String fromDateStr,String toDateStr,Long stateId,Long departmentId,Long alertCategoryId,String rangeType,Long LocationId,List<AlertOverviewVO> finalList){
    	try{
	    		Date fromDate = null;        
				Date toDate = null; 
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    		if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				
				}
	    		
    		    List<Object[]> rtrnfeedbackStatusObjLst = alertFeedbackStatusDAO.getFeedBackStatus();
    	    	List<Object[]> rtrnFeedAlertObjLst = alertDAO.getDateWiseAlert(fromDate, toDate, stateId, departmentId, alertCategoryId,LocationId);
		 	    List<Object[]> pendingFeedBackObjLst = alertDAO.getAlertBasedOnRequiredParameter(fromDate, toDate, stateId, departmentId, alertCategoryId, new ArrayList<Long>(){{add(12l);}}, "pendingFeedback",LocationId);
			    List<Object[]> reopenObjLst = alertDAO.getDateWiseReopenAlertDtls(fromDate, toDate, stateId, departmentId, alertCategoryId, "callcenter",LocationId);
			    List<Object[]> reopenObjLstForOfficer = alertDAO.getDateWiseReopenAlertDtls(fromDate, toDate, stateId, departmentId, alertCategoryId, "officer",LocationId);
			    if(rangeType != null && rangeType.equalsIgnoreCase("day")){
			    	
    			Map<String,AlertOverviewVO> daysMap = new LinkedHashMap<String, AlertOverviewVO>();
    			
    			List<String> dayList = DateUtilService.getDaysBetweenDatesStringFormat(fromDate, toDate);
    			
    			 prepareTemplate(dayList,rtrnfeedbackStatusObjLst,daysMap);
    			 //feedback status wise alert
    			  if(rtrnFeedAlertObjLst != null && rtrnFeedAlertObjLst.size() > 0){
    				  for(Object[] param:rtrnFeedAlertObjLst){//0-date,1-status,2-alertcnt
    		    		  setAlertData(daysMap,commonMethodsUtilService.getStringValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[2]),commonMethodsUtilService.getLongValueForObject(param[3]));
    				  }
    			  }
    			  //Setting Pending Feedback alert
    			   if(pendingFeedBackObjLst != null && pendingFeedBackObjLst.size() > 0){
    				   for(Object[] param:pendingFeedBackObjLst){//0-date,1-alertcnt
    		     		  setAlertData(daysMap,commonMethodsUtilService.getStringValueForObject(param[0]),"Pending FeedBack",commonMethodsUtilService.getLongValueForObject(param[1]));
     				   }
    			   }
    			   //Setting Reopen alert
    			   prepareDateWiseReopenAlert(finalList,reopenObjLst,reopenObjLstForOfficer);
    			   //Calculating grand total
    			   Map<String,Long> statusWiseTotalAlertMap = new HashMap<String, Long>();
    			   if(daysMap != null && daysMap.size() > 0){
      				 	for(Entry<String,AlertOverviewVO> entry:daysMap.entrySet()){
      					    if(entry.getValue().getSubList1() != null && entry.getValue().getSubList1().size()>0){
      					    	for(AlertOverviewVO statusVO:entry.getValue().getSubList1()){
      					    		Long alertCnt = statusWiseTotalAlertMap.get(statusVO.getStatusType());
      					    		 if(alertCnt == null){
      					    			 statusWiseTotalAlertMap.put(statusVO.getStatusType(),0l);
      					    		 }
      					    		 statusWiseTotalAlertMap.put(statusVO.getStatusType(), statusWiseTotalAlertMap.get(statusVO.getStatusType())+statusVO.getTotalAlertCnt());
      					    	}
      					    }
      				 	}
    			   }
      			 //Adding feedback alert status into final list
    			  appendingFeebbackAlertInFinalList(finalList,daysMap);
     			 //Setting grand total alert cnt into final list
     			  settingGrandTotalAlert(finalList,statusWiseTotalAlertMap);  
      			  
      	}else if(rangeType != null && (rangeType.equalsIgnoreCase("month") || rangeType.equalsIgnoreCase("week"))){
    			  LinkedHashMap<String,List<String>> weekAndDaysMap = null;
    			  Map<String,Map<String,AlertOverviewVO>> daysMap = new LinkedHashMap<String, Map<String,AlertOverviewVO>>();
    			  Map<String,Map<String,Long>> alertCntMap = new HashMap<String, Map<String,Long>>();
    			if(rangeType.equalsIgnoreCase("week")){
    				weekAndDaysMap = DateUtilService.getTotalWeeksMap(fromDate, toDate);
    			}else if(rangeType.equalsIgnoreCase("month")){
    				weekAndDaysMap = getMonthWeekAndDaysList(sdf.format(fromDate), sdf.format(toDate),"month");
        		}
    			
    			prepareWeekMonthWiseDateTemplate(weekAndDaysMap,daysMap,rtrnfeedbackStatusObjLst);
    			
    			prepareAelrtInRequiredFormat(rtrnFeedAlertObjLst,alertCntMap,"feebbackstaus");//setting feedback status alert
    			prepareAelrtInRequiredFormat(pendingFeedBackObjLst,alertCntMap,"Pending FeedBack");//Pending FeedBack status alert
    			//prepareAelrtInRequiredFormat(reopenObjLst,alertCntMap,"Reopen");//Reopen status alert
    			
    			//Seeting alert count into main map
    			if(daysMap != null && daysMap.size() > 0){
    				for(Entry<String, Map<String, AlertOverviewVO>> entry:daysMap.entrySet()){
    					 if(entry.getValue() != null && entry.getValue().size() > 0){
    						 for(Entry<String,AlertOverviewVO> dateEntry:entry.getValue().entrySet()){
    							 Map<String,Long> statusMap = alertCntMap.get(dateEntry.getKey());
    							 if(dateEntry.getValue().getSubList1() != null && dateEntry.getValue().getSubList1().size()>0){
    								 for(AlertOverviewVO statusVO:dateEntry.getValue().getSubList1()){
    									 if(statusMap != null && statusMap.size() > 0){
    										 if(statusMap.get(statusVO.getStatusType()) != null){
        										 statusVO.setTotalAlertCnt(statusMap.get(statusVO.getStatusType()));
        									 }
        								 }
    								 }
    							 }
    						 }
    					 }
    				}
    			}
    			//Prepare cumulative result month or year wise
    			Map<String,AlertOverviewVO> tempMap = new LinkedHashMap<String, AlertOverviewVO>();
    			if(daysMap != null && daysMap.size() > 0){
    				for(Entry<String, Map<String, AlertOverviewVO>> entry:daysMap.entrySet()){
    					if(entry.getValue() != null && entry.getValue().size() > 0){
    						for(Entry<String,AlertOverviewVO> daysEntry:entry.getValue().entrySet()){
    							AlertOverviewVO vo = tempMap.get(entry.getKey());
    							 if(vo == null){
    								 vo = new AlertOverviewVO();
    								 vo.setDay(entry.getKey());
    								 vo.setSubList1(getFeedBackStatusList(rtrnfeedbackStatusObjLst));
    								 tempMap.put(entry.getKey(), vo);
    							 }
    							 if(daysEntry.getValue().getSubList1() != null && daysEntry.getValue().getSubList1().size() > 0){
    								 for(AlertOverviewVO statusVO:daysEntry.getValue().getSubList1()){
    									  AlertOverviewVO matchVO = getFeedBackStatusMatchVO(vo.getSubList1(),statusVO.getStatusType());
    	    							   if(matchVO != null){
    	    								   matchVO.setTotalAlertCnt(matchVO.getTotalAlertCnt()+statusVO.getTotalAlertCnt());
    	    							   }
    	    						 }
    							 }
    						}
    					}
    				}
    			}
    			//Calculating grand total alert
    			 Map<String,Long> statusWiseTotalAlertMap = new HashMap<String, Long>();
    			 if(tempMap != null && tempMap.size() > 0){
    				 for(Entry<String,AlertOverviewVO> entry:tempMap.entrySet()){
    					    if(entry.getValue().getSubList1() != null && entry.getValue().getSubList1().size()>0){
    					    	for(AlertOverviewVO statusVO:entry.getValue().getSubList1()){
    					    		Long alertCnt = statusWiseTotalAlertMap.get(statusVO.getStatusType());
    					    		 if(alertCnt == null){
    					    			 statusWiseTotalAlertMap.put(statusVO.getStatusType(),0l);
    					    		 }
    					    		 statusWiseTotalAlertMap.put(statusVO.getStatusType(), statusWiseTotalAlertMap.get(statusVO.getStatusType())+statusVO.getTotalAlertCnt());
    					    	}
    					    }
    				 }
    			 }
    			 //Setting Reopen alert
    			 prepareWeekAndMonthWiseReopenAlert(finalList,reopenObjLst,reopenObjLstForOfficer,rangeType,fromDate,toDate);
    			 //Adding feedback alert status into final list
    			 appendingFeebbackAlertInFinalList(finalList,tempMap);
    			 //Setting grand total alert cnt into final list
    			 settingGrandTotalAlert(finalList,statusWiseTotalAlertMap);   
     		}
    	}catch(Exception e){
    		e.printStackTrace();
			LOG.error("Error occured settingFeedbackAlert() method of AlertService{}");
    	}
    }
    public void settingGrandTotalAlert(List<AlertOverviewVO> finalList,Map<String,Long> statusWiseTotalAlertMap){
    	try{
    		if(finalList != null && finalList.size() > 0){
   				if(finalList.get(0) != null && finalList.get(0).getSubList1() != null && finalList.get(0).getSubList1().size() > 0){
   					for(AlertOverviewVO param : finalList.get(0).getSubList1()){
   						if(statusWiseTotalAlertMap.get(param.getStatusType()) != null){
   							param.setGrandTotal(statusWiseTotalAlertMap.get(param.getStatusType()));
   						}
   					}
   				}
   			}
    	}catch(Exception e){
    		e.printStackTrace();
			LOG.error("Error occured settingGrandTotalAlert() method of AlertService{}");
    
    	}
    }
    public  void appendingFeebbackAlertInFinalList(List<AlertOverviewVO> finalList,Map<String,AlertOverviewVO> daysMap){
    	try{
    		 if(daysMap != null && daysMap.size() > 0){
				  for(Entry<String,AlertOverviewVO> entry:daysMap.entrySet()){
					  AlertOverviewVO dayVO = getDaysMatchVO(finalList,entry.getKey());
					   if(dayVO != null){
						 dayVO.getSubList1().addAll(entry.getValue().getSubList1());
					   }
				  }
			  }
    	}catch(Exception e){
    		e.printStackTrace();
			LOG.error("Error occured appendingFeebbackAlertInFinalList() method of AlertService{}");
    	}
    }
    public void prepareAelrtInRequiredFormat(List<Object[]> objList,Map<String,Map<String,Long>> alertCntMap,String type){
    	try{
    		if(objList != null && objList.size() > 0){
    			for(Object[] param:objList){
    				Map<String,Long> statusMap = alertCntMap.get(commonMethodsUtilService.getStringValueForObject(param[0]));//0-date
    				if(statusMap == null ){
    					statusMap = new HashMap<String, Long>();
    					alertCntMap.put(commonMethodsUtilService.getStringValueForObject(param[0]), statusMap);
    				}
    				if(type != null && (type.equalsIgnoreCase("Pending FeedBack") || type.equalsIgnoreCase("Reopen"))){
    					statusMap.put(type, commonMethodsUtilService.getLongValueForObject(param[1]));
    				}else if(type.equalsIgnoreCase("feebbackstaus")){
    					statusMap.put(commonMethodsUtilService.getStringValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[3]));
    				}
    			}
    		}
    	}catch(Exception e){
     		e.printStackTrace();
    		LOG.error("Error occured prepareAelrtInRequiredFormat() method of AlertService{}");
       }
    }
    public void setAlertData(Map<String,AlertOverviewVO> daysMap,String day,String status,Long alertCnt){
    	try{
    		 AlertOverviewVO dayVO = daysMap.get(day);
			  if(dayVO != null && dayVO.getSubList1() != null && dayVO.getSubList1().size() > 0){
				  AlertOverviewVO statusVO = getFeedBackStatusMatchVO(dayVO.getSubList1(),status);
				   if(statusVO != null){
					   statusVO.setTotalAlertCnt(alertCnt);
				   }
			  }
    	}catch(Exception e){
     		e.printStackTrace();
    		LOG.error("Error occured setAlertData() method of AlertService{}");
      }
    }
    public AlertOverviewVO getFeedBackStatusMatchVO(List<AlertOverviewVO> statusList,String status){
    	try{
    		if(statusList == null || statusList.size()==0){
    			return null;
    		}
			for(AlertOverviewVO statusVO:statusList){
				if(statusVO.getStatusType().trim().equalsIgnoreCase(status.trim())){
					return statusVO;
				}
			}
    	}catch(Exception e){
    		e.printStackTrace();
			LOG.error("Error occured getFeedBackStatusMatchVO() method of AlertService{}");
    	}
    	return null;
    }
    public AlertOverviewVO getDaysMatchVO(List<AlertOverviewVO> daysList,String day){
    	try{
    		if(daysList == null || daysList.size()==0){
    			return null;
    		}
			for(AlertOverviewVO dayVO:daysList){
				if(dayVO.getDay().trim().equalsIgnoreCase(day.trim())){
					return dayVO;
				}
			}
    	}catch(Exception e){
    		e.printStackTrace();
			LOG.error("Error occured getFeedBackStatusMatchVO() method of AlertService{}");
    	}
    	return null;
    }
    public void prepareTemplate(List<String> days,List<Object[]> objList,Map<String,AlertOverviewVO> daysMap){
    	try{
    		if(days != null && days.size() > 0){
    			for(String day:days){
    				AlertOverviewVO dayVO = new AlertOverviewVO();
    				dayVO.setDay(day);
    				dayVO.setSubList1(getFeedBackStatusList(objList));
    				daysMap.put(day, dayVO);
    			}
    		}
    	}catch(Exception e){
     		e.printStackTrace();
    		LOG.error("Error occured prepareTemplate() method of AlertService{}");
      }
    }
    public void prepareWeekMonthWiseDateTemplate(Map<String,List<String>> weekAndDaysMap,Map<String,Map<String,AlertOverviewVO>> tempWeakdaysMap,List<Object[]> objList){
    	try{
    		if(weekAndDaysMap != null && weekAndDaysMap.size() > 0){
    			for(Entry<String,List<String>> entry:weekAndDaysMap.entrySet()){
    				if(entry.getValue() != null && entry.getValue().size() > 0){
    					Map<String,AlertOverviewVO> daysMap = tempWeakdaysMap.get(entry.getKey());
    					if(daysMap == null){
    						daysMap = new LinkedHashMap<String, AlertOverviewVO>();
    						tempWeakdaysMap.put(entry.getKey(), daysMap);
    					}
    					for(String day:entry.getValue()){
    						AlertOverviewVO dayVO = new AlertOverviewVO();
    	    				dayVO.setDay(day);
    	    				dayVO.setSubList1(getFeedBackStatusList(objList));
    	    				daysMap.put(day, dayVO);
    					}
    				}
    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		LOG.error("Error occured prepareTemplate() method of AlertService{}");
    	}
    }
    public List<AlertOverviewVO> getFeedBackStatusList(List<Object[]> objList){
    	try{
    		List<AlertOverviewVO> statuList = new ArrayList<AlertOverviewVO>(0);
    		if(objList != null && objList.size() > 0){
    			for(Object[] param:objList){
    				AlertOverviewVO statusVO = new AlertOverviewVO();
    				statusVO.setStatusTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
    				statusVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[1]));
    				statusVO.setName("feebbackAlert");
    				statuList.add(statusVO);
    			}
    		}
    		AlertOverviewVO pendingFeedBackVO = new AlertOverviewVO();
    		pendingFeedBackVO.setStatusTypeId(4l);
    		pendingFeedBackVO.setStatusType("Pending FeedBack");
    		pendingFeedBackVO.setName("pendingFeedBack");
			statuList.add(pendingFeedBackVO);
			/*AlertOverviewVO reopenVO = new AlertOverviewVO();
			reopenVO.setStatusTypeId(5l);
			reopenVO.setStatusType("Reopen");
			reopenVO.setName("reopen");
			statuList.add(reopenVO);*/
			return statuList;
    	}catch(Exception e){
       		e.printStackTrace();
    		LOG.error("Error occured getFeedBackStatusList() method of AlertService{}");
    	}
    	return null;
    }
     public List<AlertOverviewVO> getTotalAlertGroupByCategoryThenStatus(String fromDateStr, String toDateStr, Long stateId, Long departmentId,Long sourceId,Long locationId,Long statusId){
		 try{  
				Date fromDate = null;        
				Date toDate = null; 
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				AlertOverviewVO alertVO = null;    
				List<AlertOverviewVO> alertVOs = null;//new ArrayList<AlertVO>();
				Map<Long,Long> categoryIdAndCountMap = new HashMap<Long,Long>();
				Map<Long,String> categoryIdAndCategoryNameMap = new HashMap<Long,String>();
				List<Object[]> statusList=alertDAO.getTotalAlertGroupByCategoryThenStatus(fromDate, toDate,  stateId,  departmentId,sourceId, "one", locationId, statusId);
				if(statusList != null && statusList.size() > 0){
				for(Object[] param : statusList){
					categoryIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
					categoryIdAndCategoryNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}}
				
				Map<Long,Long> statusIdAndCountMap = null;//new HashMap<Long, Long>();  
				Map<Long,Map<Long,Long>> categoryIdAndStatusIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
				List<Long> statusIdList = new ArrayList<Long>();
				List<Object[]> stepList=alertDAO.getTotalAlertGroupByCategoryThenStatus(fromDate, toDate,  stateId,  departmentId,sourceId, "two", locationId, statusId);
				if(stepList != null && stepList.size() > 0){
					for(Object[] param : stepList){ 
						if(param[0] != null){
							//collect existing status id only.
							statusIdList.add(commonMethodsUtilService.getLongValueForObject(param[2]));
							statusIdAndCountMap = categoryIdAndStatusIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
							if(statusIdAndCountMap != null){
								statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
							}else{
								statusIdAndCountMap = new HashMap<Long, Long>();
								statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
								categoryIdAndStatusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),statusIdAndCountMap);
							}
						}
					}
				}
				
				
				List<Object[]> statusLists = alertStatusDAO.getAlertStatusDtlsBasidOnAlertIds(statusIdList);
				
				//build final vo to sent to ui
				List<AlertOverviewVO> finalList = new ArrayList<AlertOverviewVO>();
				AlertOverviewVO innerListAlertVO = null;
				if(categoryIdAndStatusIdAndCountMap.size() > 0){
					for(Entry<Long,Map<Long,Long>> entry : categoryIdAndStatusIdAndCountMap.entrySet()){
						statusIdAndCountMap = entry.getValue();
						if(statusIdAndCountMap.size() > 0){
							if(statusList != null && statusList.size() > 0){
								alertVOs = new ArrayList<AlertOverviewVO>();
								innerListAlertVO = new AlertOverviewVO();
								for(Object[] param : statusLists){
									alertVO = new AlertOverviewVO();
									alertVO.setStatusTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
									alertVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[1]));
									alertVOs.add(alertVO);  
								}
							}
							for(AlertOverviewVO param : alertVOs){
								if(statusIdAndCountMap.get(param.getStatusTypeId()) != null){
									param.setTotalAlertCnt(statusIdAndCountMap.get(param.getStatusTypeId()));  
								}else{
									param.setTotalAlertCnt(0l);  
								}
							}
							innerListAlertVO.setSubList1(alertVOs);
							if(categoryIdAndCategoryNameMap.get(entry.getKey()) != null){
								innerListAlertVO.setId(entry.getKey());
								innerListAlertVO.setName(categoryIdAndCategoryNameMap.get(entry.getKey()));
								
							}
							if(categoryIdAndCountMap.get(entry.getKey()) != null){
								innerListAlertVO.setTotalAlertCnt(categoryIdAndCountMap.get(entry.getKey()));
							}
							finalList.add(innerListAlertVO);     
						}
					}
				}
				//push feedback count.
				List<Object[]> feedbackList=alertDAO.getTotalAlertGroupByCategoryThenFeedbackStatus(fromDate, toDate,  stateId,  departmentId,sourceId, "two", locationId, statusId,"other","false");
				List<Object[]> feedbackListForPending=alertDAO.getTotalAlertGroupByCategoryThenFeedbackStatus(fromDate, toDate,  stateId,  departmentId,sourceId, "one", locationId, statusId,"pending","false");
				if(finalList != null && finalList.size() > 0){
					pushFeedbackCount(finalList,categoryIdAndStatusIdAndCountMap,feedbackList,feedbackListForPending);
				}
				//push reopen count for call center.
				List<Object[]> reopenList = alertDAO.getTotalReopenAlerts(fromDate, toDate,  stateId,  departmentId,sourceId,"callCenter");
				if(finalList != null && finalList.size() > 0){
					pushReopenCount(finalList,reopenList);
				}
				//push reopen count for officer.
				List<Object[]> reopenListForOfficer = alertDAO.getTotalReopenAlerts(fromDate, toDate,  stateId,  departmentId,sourceId,"officer");
				if(finalList != null && finalList.size() > 0){
					pushReopenCountForOfficer(finalList,reopenListForOfficer);
				}
				//push reopen count for overall.
				if(finalList !=null && finalList.size() > 0){
					for(AlertOverviewVO param : finalList){
						param.setOverallReopenCount(param.getReopenCount()+param.getReopenCountForOfficer());
					}
				}          
				return finalList;  
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Error occured getTotalAlertGroupByLocationThenStatus() method of AlertService{}");
			}
		 	return null;
	 }
	 public List<IdNameVO> getDistrictList(String fromDateStr, String toDateStr, Long stateId, Long departmentId,Long sourceId){
		 try{
			 Date fromDate = null;        
			 Date toDate = null; 
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			 if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				 fromDate = sdf.parse(fromDateStr);
				 toDate = sdf.parse(toDateStr);
			 }
			 List<Object[]> alertCountList = alertDAO.getTotalAlertGroupByLocationThenStatus(fromDate, toDate, stateId, departmentId,sourceId,"District","One",null,null);
			 List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
			 IdNameVO idNameVO = null;
			 if(alertCountList != null && alertCountList.size() > 0){
				 for(Object[] param : alertCountList){
					 if(param[0] != null){
						 idNameVO = new IdNameVO();
						 idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						 idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						 idNameVOs.add(idNameVO);
					 }
				 }
			 }
			 return idNameVOs;
		 }catch(Exception e){
			 e.printStackTrace();
			 LOG.error("Error occured getDistrictList() method of AlertService{}");
		 }
		 return null;
	 }
	 
	 public List<IdNameVO> getAllDepts(){
		 List<IdNameVO> finalList = new ArrayList<IdNameVO>();
		 try {
			 
			 List<Object[]> listObj = govtDepartmentDAO.getAllDepartment();
			 if(listObj !=null && listObj.size()>0){
				 for (Object[] objects : listObj) {
					IdNameVO idNameVO = new IdNameVO();
					idNameVO.setId(objects[0] !=null ? (Long)objects[0]:0l);
					idNameVO.setName(objects[1] !=null ? objects[1].toString():"");
					finalList.add(idNameVO);
				}
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error occured getAllDepts() method of AlertService{}");
		}
		 return finalList;
	 }
	 
	 public String updateDuplicateAlertCallerDetails(final GrievanceAlertVO inputVO,final Long userId){
		 String status = null;
		 try {
			 status = (String) transactionTemplate.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						AlertCallerRelation alertCallerRelation = new AlertCallerRelation();
						
						List<Long> existingList = alertCallerDAO.checkIsExist(inputVO.getMobileNo(),inputVO.getName());
						if(!commonMethodsUtilService.isListOrSetValid(existingList)){
							AlertCaller alertCaller = new AlertCaller();
							alertCaller.setCallerName(inputVO.getName() !=null ? inputVO.getName():null);
							alertCaller.setAddress(inputVO.getAddress() !=null && !inputVO.getAddress().trim().isEmpty() ? inputVO.getAddress():null);
							alertCaller.setMobileNo(inputVO.getMobileNo() !=null ? inputVO.getMobileNo():null);
							alertCaller.setEmail(inputVO.getEmailId() !=null && !inputVO.getEmailId().trim().isEmpty() ? inputVO.getEmailId():null);
							//alertCaller.setAccountId(inputVO.getAccountId() !=null ? inputVO.getAccountId():null);
							//alertCaller.setSocialMediaTypeId(inputVO.getSocialMediaTypeId() !=null ? inputVO.getSocialMediaTypeId():null);
							
							alertCaller = alertCallerDAO.save(alertCaller);
							alertCallerRelation.setAlertCallerId(alertCaller.getAlertCallerId());
						}
						else{
							alertCallerRelation.setAlertCallerId(existingList.get(0));
						}
						Long maxOrder = alertCallerRelationDAO.getMaxCallerOrderForAlert(inputVO.getAlertId());
						alertCallerRelation.setAlertId(inputVO.getAlertId());
						alertCallerRelation.setIsDeleted("N");
						if(maxOrder != null)
							alertCallerRelation.setCallerOrder(maxOrder+1l);
						else
							alertCallerRelation.setCallerOrder(1l);
						alertCallerRelation = alertCallerRelationDAO.save(alertCallerRelation);
						
						Alert alert = alertDAO.get(inputVO.getAlertId());
						Alert testAlert = alertDAO.get(14850L);
						GovtSMSAPIService govtSMSAPIService = new GovtSMSAPIService();
						/*String officerName = "";
						String officerMobileNo = "";
						String designationName = "";
						String departmentName = "";
						String callCenterVersion = IConstants.CALL_CENTER_VERSION.toString();
						Long levelValue = 999999L;
						levelValue = alert.getUserAddress(). getConstituency() != null ? alert.getUserAddress().getConstituency().getConstituencyId() : 999999L;
						
						if(callCenterVersion.trim().equalsIgnoreCase("old")){
							GovtOfficer govtOfficer = govtOfficerDAO.get(inputVO.getGovtOfficerId());
							if(govtOfficer != null){
								officerName = govtOfficer.getOfficerName();
								officerMobileNo = govtOfficer.getMobileNo();
							}
						}else if(callCenterVersion.trim().equalsIgnoreCase("new")){
							GovtOfficerNew govtOfficer = govtOfficerNewDAO.get(inputVO.getGovtOfficerId());
							if(govtOfficer != null){
								officerName = govtOfficer.getOfficerName();
								officerMobileNo = govtOfficer.getMobileNo();
							}
						}
						
						
						Alert testAlert = alertDAO.get(14850L);
						
						if(callCenterVersion.trim().equalsIgnoreCase("old")){
							GovtDepartmentDesignation govtDepartmentDesignation = govtDepartmentDesignationDAO.get(inputVO.getDesignationId());
							if(govtDepartmentDesignation != null)
								designationName = govtDepartmentDesignation.getDesignationName();
						}
						else if(callCenterVersion.trim().equalsIgnoreCase("new")){
							GovtDepartmentDesignationNew govtDepartmentDesignation = govtDepartmentDesignationNewDAO.get(inputVO.getDesignationId());
							if(govtDepartmentDesignation != null)
								designationName = govtDepartmentDesignation.getDesignationName();
						}
						
						GovtDepartment govtDepartment = govtDepartmentDAO.get(inputVO.getDepartmentId());
						if(govtDepartment != null)
							departmentName = govtDepartment.getDepartmentName();                                                                            

						GovtSMSAPIService govtSMSAPIService = new GovtSMSAPIService();
						/* The Hamlet next superior officer level is constituency*/
						//Long levelValue=userAddress.getConstituency() != null ? userAddress.getConstituency() .getConstituencyId():999999L;
						
						/*List<Long> parentDesigIds = null;
						if(callCenterVersion.trim().equalsIgnoreCase("old"))
							parentDesigIds = govtDepartmentDesignationHierarchyDAO.getOldParentDepartment(inputVO.getDesignationId());
						else if(callCenterVersion.trim().equalsIgnoreCase("new"))
							parentDesigIds = govtDepartmentDesignationHierarchyDAO.getParentDepartment(inputVO.getDesignationId());
						
						if(parentDesigIds != null && parentDesigIds.size() > 0){
							//get high level officer mobile nums
							List<Long> firstDesignsListIds = new ArrayList<Long>(0);
							firstDesignsListIds.add(parentDesigIds.get(0));
							
							List<String> mobilenums = null;
							if(callCenterVersion.trim().equalsIgnoreCase("old"))	
								mobilenums = govtDepartmentDesignationOfficerDetailsDAO.getOldHigherOfficerMobileNums(parentDesigIds,inputVO.getLocationLevelId(),levelValue);
							/*else if(callCenterVersion.trim().equalsIgnoreCase("new"))	
								mobilenums = govtDepartmentDesignationOfficerDetailsDAO.getNewHigherOfficerMobileNums(parentDesigIds,inputVO.getLocationLevelId(),levelValue);*/

						//	if(mobilenums != null && mobilenums.size() > 0){
								/* Grievance request hiegher authority pesron*/
								//String message = "Grievance request is assigned to "+designationName+" - "+departmentName+" - "+officerName+" - "+ officerMobileNo+".\n Please follow up.";
							/*	String message ="Respected officer, Grievance request is assigned to your sub-ordinate officer. Please follow up. " +
										" \n Issue Title: "+inputVO.getAlertTitle()+"\n" +
										" Assigned officer: "+designationName+" ("+officerMobileNo+") \n Dept: "+departmentName+" \n Raised by: "+inputVO.getMobileNo()+" ("+inputVO.getName()+")"; 
								String mobileNums = "";
								for (String string : mobilenums) {
									mobileNums = mobileNums.equalsIgnoreCase("")?string:mobileNums+","+string;
								}
								if(testAlert.getDescription()!= null && !testAlert.getDescription().isEmpty())
									mobileNums = testAlert.getDescription().trim();
								//govtSMSAPIService.senedSMSForGovtAlert(mobileNums,message);
							}
						}
						/* request responsible person*/ 
						//  String callerMessage = "Your Request is Raised,and Assigned to Higher Authority.";
					/*	String callerMessage = "Respected officer, Grievance request is assigned to you. Please follow up and resolve." +
								" \n Issue Title: "+inputVO.getAlertTitle()+"\n" +
								" Assigned officer: "+designationName+" ("+officerMobileNo+") \n Dept: "+departmentName+" \n Raised by: "+inputVO.getMobileNo()+" ("+inputVO.getName()+")";
						if(testAlert.getDescription()!= null && !testAlert.getDescription().isEmpty())
							govtSMSAPIService.senedSMSForGovtAlert(testAlert.getDescription().trim(),callerMessage);
						else
							govtSMSAPIService.senedSMSForGovtAlert(officerMobileNo,callerMessage);*/


						/* request raised person*/ 
						//  String officerMessage = "Grievance request is assigned to you,Please follow up and resolve.\nTitle : "+inputVO.getAlertTitle()+" \nDept : "+departmentName;

						String officerMessage =testAlert.getTitle().toString()+" "+alert.getAlertId()+"";
						if(testAlert.getDescription()!= null && !testAlert.getDescription().isEmpty())
							govtSMSAPIService.senedSMSForGovtAlert(testAlert.getDescription().trim(),officerMessage);
						else
							govtSMSAPIService.senedSMSForGovtAlert(inputVO.getMobileNo(),officerMessage); 
						
						saveMessageDetails(userId,inputVO.getMobileNo(),alert.getAlertId(),alert.getAlertStatusId(),officerMessage,1l);
						
						//sending SMS to MPDO and Panchayat secratory
						/*if(inputVO.getDepartmentId() != null && inputVO.getDepartmentId() == IConstants.RWS_DEPARTMENT_ID // rws deaprtment 
								&& inputVO.getDesignationId() != null && inputVO.getDesignationId() == 4l) //AE designation
						{
							//get MPDO's mobile num
							List<String> mpdosMobNumsList = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtOfficerMobileNums(inputVO.getMandalId(),"tehsil");
							List<String> villageSecMobNums = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtOfficerMobileNums(inputVO.getPanchayatId(),"village");
							
							String message = "Respected officer, Grievance request is assigned to AE." +
									"\n Issue Title: "+inputVO.getAlertTitle()+"\n" +
									"Assigned officer: "+designationName+" ("+officerMobileNo+") \n Dept: "+departmentName+" \n Raised by: "+inputVO.getMobileNo()+" ("+inputVO.getName()+")";
							
							String allMobStr = null;
							
							if(mpdosMobNumsList != null && mpdosMobNumsList.size() > 0){
								for (String string : mpdosMobNumsList) {
									allMobStr = allMobStr == null?string:allMobStr+","+string;
									saveMessageDetails(userId,string,testAlert.getAlertId(),testAlert.getAlertStatusId(),message,1l);
								}
							}
							if(villageSecMobNums != null && villageSecMobNums.size() > 0){
								for (String string : villageSecMobNums) {
									allMobStr = allMobStr == null?string:allMobStr+","+string;
									saveMessageDetails(userId,string,testAlert.getAlertId(),testAlert.getAlertStatusId(),message,1l);
								}
							}
							if(allMobStr != null){
								govtSMSAPIService.senedSMSForGovtAlert(allMobStr,message); 
							}
						}*/
						
						return "success";
					}
			 });
		} catch (Exception e) {
			LOG.error("Error occured updateDuplicateAlertCallerDetails() method of AlertService{}",e);
		}
		 return status;
	 }
	 
	 public String saveSocialAlert(final GrievanceAlertVO inputVO,final Long userId, final Map<File,String> mapFiles)
		{
			String resultStatus = null;
			try {
				resultStatus = (String) transactionTemplate.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						String rs = new String();
						DateUtilService date = new DateUtilService();
						
						if(inputVO.getAlertId() != null && inputVO.getAlertId().longValue() > 0l){
							AlertCallerRelation alertCallerRelation = new AlertCallerRelation();
							
							List<Long> existingList = alertCallerDAO.checkIsExistSocialCaller(inputVO.getMobileNo(),inputVO.getAccountId(),inputVO.getSocialMediaTypeId());
							if(!commonMethodsUtilService.isListOrSetValid(existingList)){
								AlertCaller alertCaller = new AlertCaller();
								alertCaller.setCallerName(inputVO.getName() !=null ? inputVO.getName():null);
								alertCaller.setAddress(inputVO.getAddress() !=null && !inputVO.getAddress().trim().isEmpty() ? inputVO.getAddress():null);
								alertCaller.setMobileNo(inputVO.getMobileNo() !=null ? inputVO.getMobileNo():null);
								alertCaller.setEmail(inputVO.getEmailId() !=null && !inputVO.getEmailId().trim().isEmpty() ? inputVO.getEmailId():null);
								alertCaller.setAccountId(inputVO.getAccountId() !=null ? inputVO.getAccountId():null);
								alertCaller.setSocialMediaTypeId(inputVO.getSocialMediaTypeId() !=null ? inputVO.getSocialMediaTypeId():null);
								
								alertCaller = alertCallerDAO.save(alertCaller);
								alertCallerRelation.setAlertCallerId(alertCaller.getAlertCallerId());
							}
							else{
								alertCallerRelation.setAlertCallerId(existingList.get(0));
							}
							
							alertCallerRelation.setAlertId(inputVO.getAlertId());
							alertCallerRelation.setIsDeleted("N");
							alertCallerRelation = alertCallerRelationDAO.save(alertCallerRelation);
						}
						else{
							Alert alert = new Alert();
							AlertCallerRelation alertCallerRelation = new AlertCallerRelation();

							List<Long> existingList = alertCallerDAO.checkIsExistSocialCaller(inputVO.getMobileNo(),inputVO.getAccountId(),inputVO.getSocialMediaTypeId());
													
							if(!commonMethodsUtilService.isListOrSetValid(existingList)){
								AlertCaller alertCaller = new AlertCaller();
								alertCaller.setCallerName(inputVO.getName() !=null ? inputVO.getName():null);
								alertCaller.setAddress(inputVO.getAddress() !=null && !inputVO.getAddress().trim().isEmpty() ? inputVO.getAddress():null);
								alertCaller.setMobileNo(inputVO.getMobileNo() !=null ? inputVO.getMobileNo():null);
								alertCaller.setEmail(inputVO.getEmailId() !=null && !inputVO.getEmailId().trim().isEmpty() ? inputVO.getEmailId():null);
								alertCaller.setAccountId(inputVO.getAccountId() !=null ? inputVO.getAccountId():null);
								alertCaller.setSocialMediaTypeId(inputVO.getSocialMediaTypeId() !=null ? inputVO.getSocialMediaTypeId():null);
								
								alertCaller = alertCallerDAO.save(alertCaller);
								alertCallerRelation.setAlertCallerId(alertCaller.getAlertCallerId());
							}
							else{
								alertCallerRelation.setAlertCallerId(existingList.get(0));
							}
							
							alert.setAlertSeverityId(2l);
							alert.setAlertTypeId(2l);
							alert.setImpactLevelId(inputVO.getLocationLevelId() !=null ? inputVO.getLocationLevelId():null);
							alert.setImpactLevelValue(inputVO.getLocationValue() !=null ? inputVO.getLocationValue():null);
							alert.setDescription(inputVO.getDescription() !=null ? inputVO.getDescription().toString():null);
							alert.setCreatedBy(userId);
							alert.setUpdatedBy(userId);
							alert.setImpactScopeId(inputVO.getLocationLevelId() !=null ? inputVO.getLocationLevelId():null);

							alert.setAlertStatusId(14l);// default Pre_Pending status

							alert.setAlertSourceId(6l);
							alert.setCreatedTime(date.getCurrentDateAndTime());
							alert.setUpdatedTime(date.getCurrentDateAndTime());
							alert.setIsDeleted("N");
							alert.setAlertCategoryId(5l);// Social Media
							alert.setTitle(inputVO.getAlertTitle());

							UserAddress userAddress = saveUserAddressForGrievanceAlertNew(inputVO);
							alert.setAddressId(userAddress.getUserAddressId());

							
							alert.setGovtDepartmentId(inputVO.getDepartmentId());
							alert.setIsMultiple("N");
							
							alert.setSocialMediaTypeId(inputVO.getSocialMediaTypeId() !=null ? inputVO.getSocialMediaTypeId():null);

							alert = alertDAO.save(alert);
							
							alertCallerRelation.setAlertId(alert.getAlertId());
							alertCallerRelation.setIsDeleted("N");
							alertCallerRelation = alertCallerRelationDAO.save(alertCallerRelation);
							
							//balu
					    	
							//Document Saving
							//saveAlertDocument(alert.getAlertId(),userId,mapFiles);
							saveAlertDocumentNew(alert.getAlertId(),userId,mapFiles);
							
							//Alert Tracking
							AlertTrackingVO alertTrackingVO = new AlertTrackingVO();
							
							alertTrackingVO.setUserId(userId);
							alertTrackingVO.setAlertStatusId(14l);	
							alertTrackingVO.setAlertId(alert.getAlertId());
							alertTrackingVO.setAlertTrackingActionId(IConstants.ALERT_ACTION_STATUS_CHANGE);
							
							saveAlertTrackingDetails(alertTrackingVO);	
						}
						
						rs = "success";
						return rs;
					}

				});
			} catch (Exception e) {
				resultStatus = "failure";
				LOG.error("Error occured saveSocialAlert() method of AlertService{}",e);
			}

			return resultStatus;
		}
	 public UserAddress saveUserAddressForGrievanceAlertNew(final GrievanceAlertVO inputVO)
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
					
					if(inputVO.getLocationLevelId() ==  5l){
						List<Constituency> constituencyList = boothDAO.getConstituencyIdByTehsilId(inputVO.getMandalId());
						if(constituencyList !=null && constituencyList.size()>0){
							userAddress.setConstituency(constituencyList.get(0));
						}
						userAddress.setTehsil(tehsilDAO.get(inputVO.getMandalId()));
					}					
					else{	
						List<Constituency> constituencyList = assemblyLocalElectionBodyDAO.getConstituencyByAssemblyLocalEleBodyId(inputVO.getMandalId());
						if(constituencyList !=null && constituencyList.size()>0){
							userAddress.setConstituency(constituencyList.get(0));
						}
						userAddress.setLocalElectionBody(localElectionBodyDAO.get(inputVO.getMandalId()));
					}
							
				}
				
				else if(inputVO.getLocationLevelId().longValue() == 6l || inputVO.getLocationLevelId().longValue() == 8l)
				{
					userAddress.setState(stateDAO.get(inputVO.getStateId()));
					userAddress.setDistrict(districtDAO.get(inputVO.getDistrictId()));
					
					if(inputVO.getLocationLevelId() ==  6l)
					{
						List<Constituency> constituencyList = boothDAO.getConstituencyIdByTehsilId(inputVO.getMandalId());
						if(constituencyList !=null && constituencyList.size()>0){
							userAddress.setConstituency(constituencyList.get(0));
						}
						//userAddress.setConstituency(boothDAO.getConstituencyIdByTehsilId(inputVO.getMandalId()));
						userAddress.setTehsil(tehsilDAO.get(inputVO.getMandalId()));
						userAddress.setPanchayatId(inputVO.getPanchayatId());
					}
					else
					{
						List<Constituency> constituencyList = assemblyLocalElectionBodyDAO.getConstituencyByAssemblyLocalEleBodyId(inputVO.getMandalId());
						if(constituencyList !=null && constituencyList.size()>0){
							userAddress.setConstituency(constituencyList.get(0));
						}
						userAddress.setLocalElectionBody(localElectionBodyDAO.get(inputVO.getMandalId()));	
						userAddress.setWard(constituencyDAO.get(inputVO.getPanchayatId()));
					}
				}
				else if(inputVO.getLocationLevelId().toString().equalsIgnoreCase("11"))
				{
					userAddress.setState(stateDAO.get(inputVO.getStateId()));
					userAddress.setDistrict(districtDAO.get(inputVO.getDistrictId()));
					//userAddress.setConstituency(constituencyDAO.get(inputVO.getConstituencyId()));
					List<Constituency> constituencyList = boothDAO.getConstituencyIdByTehsilId(inputVO.getMandalId());
					if(constituencyList !=null && constituencyList.size()>0){
						userAddress.setConstituency(constituencyList.get(0));
					}
					userAddress.setTehsil(tehsilDAO.get(inputVO.getMandalId()));
					userAddress.setPanchayatId(inputVO.getPanchayatId());
					userAddress.setHamlet(hamletDAO.get(inputVO.getHamletId()));
				}
				else if(inputVO.getLocationLevelId().toString().equalsIgnoreCase("13"))
				{
					userAddress.setState(stateDAO.get(inputVO.getStateId()));
					userAddress.setDistrict(districtDAO.get(inputVO.getDistrictId()));
					//userAddress.setConstituency(constituencyDAO.get(inputVO.getConstituencyId()));
					List<Constituency> constituencyList = boothDAO.getConstituencyIdByLebId(inputVO.getMandalId());
					if(constituencyList !=null && constituencyList.size()>0){
						userAddress.setConstituency(constituencyList.get(0));
					}
					userAddress.setLocalElectionBody(localElectionBodyDAO.get(inputVO.getMandalId()));
					userAddress.setUrbanLocalityId(inputVO.getPanchayatId());
					userAddress.setUrbanBlockId(inputVO.getHamletId());
				}
				
				userAddress = userAddressDAO.save(userAddress);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return userAddress;
		}
	 
	 public List<AlertTrackingVO> getSocialAlertCallerDetails(Long userId,String startdateStr,String endDateStr,String status,String mobileNo,Long departmentId){
			List<AlertTrackingVO> voList = new ArrayList<AlertTrackingVO>(0);
			try {
				Date startDate=null;
				Date endDate = null;
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				if(startdateStr != null && !startdateStr.isEmpty() && startdateStr != null && !startdateStr.isEmpty()){
					 startDate=format.parse(startdateStr);
					 endDate = format.parse(endDateStr);
				}
				
				List<AlertStatus> statusList =  alertStatusDAO.getAll();
				AlertTrackingVO returnVO = new AlertTrackingVO();
				 if(commonMethodsUtilService.isListOrSetValid(statusList)){
					 for (AlertStatus alertStatus : statusList) {
						 AlertTrackingVO vo = new AlertTrackingVO();
						 vo.setAlertStatusId(alertStatus.getAlertStatusId());
						 vo.setStatus(alertStatus.getAlertStatus());
						 returnVO.getStatusList().add(vo);
					}
				 }
				 
				 List<Object[]> objList1 = null;
					 objList1 = alertTrackingDAO.getStatuswiseAlertsDetailsOfSocial(mobileNo,userId, startDate, endDate,departmentId,5l);
				
				Map<Long,Long> statusWiseMap = new HashMap<Long, Long>(0);
				if(objList1 != null && objList1.size() > 0){
					for (Object[] param : objList1) {
						AlertTrackingVO vo = (AlertTrackingVO)setterAndGetterUtilService.getMatchedVOfromList(returnVO.getStatusList(), "alertStatusId", commonMethodsUtilService.getStringValueForObject(param[0]));
						if(vo != null){
							vo.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
						}
						statusWiseMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
					}
				}
				
				 /*List<AlertFeedbackStatus> feedbackStatusList =  alertFeedbackStatusDAO.getAll();
				 List<AlertTrackingVO> feedbacksStatusList = new ArrayList<AlertTrackingVO>(0);
				 if(commonMethodsUtilService.isListOrSetValid(feedbackStatusList)){
					 for (AlertFeedbackStatus alertStatus : feedbackStatusList) {
						 if(alertStatus.getIsDeleted().equalsIgnoreCase("N")){
							 AlertTrackingVO vo = new AlertTrackingVO();
							 vo.setAlertStatusId(alertStatus.getAlertFeedbackStatusId());
							 vo.setStatus(alertStatus.getStatus());
							 if(commonMethodsUtilService.isListOrSetValid(returnVO.getStatusList())){
								 for (AlertTrackingVO alertTrackingVO : returnVO.getStatusList()) {
									 AlertTrackingVO vo1 = new AlertTrackingVO();
									 vo1.setAlertStatusId(alertTrackingVO.getAlertStatusId());
									 vo1.setStatus(alertTrackingVO.getStatus());
									 //vo1.setCount(alertTrackingVO.getCount());
									 vo.getStatusList().add(vo1);
								}
							 }
							 feedbacksStatusList.add(vo);
						 }
					}
				 }*/
					
				List<AlertTrackingVO> socialMediaTypeList = new ArrayList<AlertTrackingVO>(0);
				List<Object[]> socialMediaObj =  alertDAO.getAllSocialMediaType();
				if(commonMethodsUtilService.isListOrSetValid(socialMediaObj)){
					for (Object[] objects : socialMediaObj) {						
						AlertTrackingVO vo = new AlertTrackingVO();
						vo.setAlertStatusId(objects[0] !=null ? (Long)objects[0]:null);
						vo.setStatus(objects[1] !=null ? objects[1].toString():"");
						
						 if(commonMethodsUtilService.isListOrSetValid(returnVO.getStatusList())){
							 for (AlertTrackingVO alertTrackingVO : returnVO.getStatusList()) {
								 AlertTrackingVO vo1 = new AlertTrackingVO();
								 vo1.setAlertStatusId(alertTrackingVO.getAlertStatusId());
								 vo1.setStatus(alertTrackingVO.getStatus());
								 //vo1.setCount(alertTrackingVO.getCount());
								 vo.getStatusList().add(vo1);
							}
						 } 						 
						 socialMediaTypeList.add(vo);
					}
				}
				
				
				 List<Object[]> objList = null;
					 //objList = alertTrackingDAO.getAlertFeedbackStatuswiseAlertsDetailsOfSocial(mobileNo,userId, startDate, endDate,departmentId,5l);
				 
				 objList = alertTrackingDAO.getSocialMediaTypeAlertsDetails(mobileNo,userId, startDate, endDate,departmentId,5l);
				 
				if(objList != null && objList.size() > 0 && commonMethodsUtilService.isListOrSetValid(socialMediaTypeList)){
					for (Object[] param : objList) {
						AlertTrackingVO vo = (AlertTrackingVO)setterAndGetterUtilService.getMatchedVOfromList(socialMediaTypeList, "alertStatusId", commonMethodsUtilService.getLongValueForObject(param[1]).toString());
						if(vo != null){
							AlertTrackingVO vo1 = (AlertTrackingVO)setterAndGetterUtilService.getMatchedVOfromList(vo.getStatusList(), "alertStatusId", commonMethodsUtilService.getLongValueForObject(param[0]).toString());
							if(vo1 != null){
								vo1.setCount(commonMethodsUtilService.getLongValueForObject(param[3]));
							}
						}else{
							vo = socialMediaTypeList.get(0);
							AlertTrackingVO vo1 = (AlertTrackingVO)setterAndGetterUtilService.getMatchedVOfromList(vo.getStatusList(), "alertStatusId", commonMethodsUtilService.getLongValueForObject(param[0]).toString());
							if(vo1 != null){
								vo1.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[3]));
							}
						}
					}
				}
				if(commonMethodsUtilService.isMapValid(statusWiseMap) && commonMethodsUtilService.isListOrSetValid(socialMediaTypeList)){
					AlertTrackingVO vo = socialMediaTypeList.get(0);
					 List<AlertTrackingVO> list =vo.getStatusList();
					 if(commonMethodsUtilService.isListOrSetValid(list)){
						 for (AlertTrackingVO statusVO : list) {
							 statusVO.setTotalCount(statusWiseMap.get(statusVO.getAlertStatusId()));
						}
					 }
				}
				 returnVO.getStatusList().clear();
				 returnVO.getStatusList().addAll(socialMediaTypeList);
				voList.add(returnVO);				
				
				
			} catch (Exception e) {
				LOG.error("Error occured getSocialAlertCallerDetails() method of AlertManagementSystemService");
			}
			return voList;
		}
	 
	 public static String folderCreationForAlertsAttachmentNew(){
	  	 try {
	  		 LOG.debug(" in FolderForNotCadre ");
	  		
	  		 String staticPath = IConstants.STATIC_CONTENT_FOLDER_URL;
	  		 
	  		Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			int year = calendar.get(Calendar.YEAR);
			SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_VALUE);
			String dateStr = sdf.format(new Date());
			 
			 String notCadreImagesFoldr = staticPath+"images/"+IConstants.ALERTS_ATTACHMENTS+"/"+year+"/"+dateStr;
			 
			 String foldrSts = ActivityService.createFolder(notCadreImagesFoldr);
			 if(!foldrSts.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			 return staticPath+"images/"+IConstants.ALERTS_ATTACHMENTS+"/"+year+"/"+dateStr;
			 
		} catch (Exception e) {
			LOG.error(" Failed to Create ");
			return "FAILED";
		}
	}
	 
	 public String saveAlertDocumentNew(Long alertId,Long userId,final Map<File,String> documentMap){
			
			try{
				
				DateUtilService dt = new DateUtilService();
				
				String folderName = folderCreationForAlertsAttachmentNew();
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
				 
				 SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_VALUE);
				 String dateStr = sdf.format(new Date());
				 String yearStr = String.valueOf(year);
				 
				
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
					 
					 
					 pathBuilder.append("alerts_attachments/").append(yearStr).append("/").append(dateStr).append("/").append(randomNumber).append(".").append(ext);
					 
					 String fileCpyStts = copyFile(entry.getKey().getAbsolutePath(),destPath);
					 
						if(fileCpyStts.equalsIgnoreCase("error")){
							LOG.error(" Exception Raise in copying file in saveAlertDocumentNew ");
							throw new ArithmeticException();
						}
						
						alertDocument = new AlertDocument();
						alertDocument.setDocumentPath(pathBuilder.toString());
						alertDocument.setDocumentName(StringEscapeUtils.escapeJava(fName));     
						alertDocument.setAlertId(alertId);
						alertDocument.setInsertedTime(dt.getCurrentDateAndTime());
						alertDocument.setIsDeleted("N");
						alertDocument.setInsertedBy(userId);
						alertDocument = alertDocumentDAO.save(alertDocument);
						
				 }
			}catch(Exception e){
				LOG.error("Exception Occured in saveAlertDocumentNew() in ToursService", e);
				return "faliure";
			}
			return "success";
		}
	 
	 public List<AlertVO> getSocialAlertDetailsByStatus(Long alertStatusId,String mobileNo,String fromDateStr,String toDateStr,Long feedbackStatusId,
			 Long deptId,Long categoryId,Long userId,Long smTypeId){
			List<AlertVO> returnList = new ArrayList<AlertVO>();
			try{
				Date fromDate = null;      
				Date toDate = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				
				List<Object[]> alertList = alertDAO.getSocialAlertDetials(mobileNo,alertStatusId,fromDate,toDate,deptId,feedbackStatusId,categoryId,userId,smTypeId);
				
				if(alertList != null && alertList.size() > 0l){
					List<Long> alertIds = new ArrayList<Long>(0);
					for (Object[] objects : alertList) {
						AlertVO vo = new AlertVO();
						vo.setAlertId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						vo.setTitle(commonMethodsUtilService.getStringValueForObject(objects[1]));
						vo.setAlertImpactId(commonMethodsUtilService.getLongValueForObject(objects[2]));
						vo.setCreatedTime(commonMethodsUtilService.getStringValueForObject(objects[3]));
						vo.setAlertSourceId(commonMethodsUtilService.getLongValueForObject(objects[4]));
						vo.setLocationName(commonMethodsUtilService.getStringValueForObject(objects[5]));
						vo.setStatusId(commonMethodsUtilService.getLongValueForObject(objects[6]));
						vo.setDeptName(commonMethodsUtilService.getStringValueForObject(objects[7]));
						vo.setFeedbackStatus(commonMethodsUtilService.getStringValueForObject(objects[8]));
						
						vo.setDistrict(commonMethodsUtilService.getStringValueForObject(objects[9]));
						vo.setConstituency(commonMethodsUtilService.getStringValueForObject(objects[10]));
						vo.setTehsil(commonMethodsUtilService.getStringValueForObject(objects[11]));
						vo.setPanchayat(commonMethodsUtilService.getStringValueForObject(objects[12]));
						vo.setHamlet(commonMethodsUtilService.getStringValueForObject(objects[13]));
						vo.setLocalBody(commonMethodsUtilService.getStringValueForObject(objects[14]));
						vo.setWard(commonMethodsUtilService.getStringValueForObject(objects[15]));
						//vo.setName(commonMethodsUtilService.getStringValueForObject(objects[16]));
						//vo.setMobileNo(commonMethodsUtilService.getStringValueForObject(objects[17]));
						
						vo.setUserName(commonMethodsUtilService.getStringValueForObject(objects[18]));
						vo.setSmTypeId(commonMethodsUtilService.getLongValueForObject(objects[19]));
						vo.setSmType(commonMethodsUtilService.getStringValueForObject(objects[20]));
						vo.setVerifyStatus(commonMethodsUtilService.getStringValueForObject(objects[21]));
						vo.setState(commonMethodsUtilService.getStringValueForObject(objects[22]));
						
						alertIds.add(vo.getAlertId());
						returnList.add(vo);
					}
					
					List<Object[]> list = alertCallerRelationDAO.getAlertCallerDetailsForAlerts(alertIds);
					if(list != null && !list.isEmpty()){
						for (Object[] obj : list) {
							Long alertId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
							AlertVO vo = getMatchedAlertVO(returnList, alertId);
							if(vo != null){
								IdNameVO callervo = new IdNameVO();
								callervo.setName(obj[2] != null ? obj[2].toString():"");
								callervo.setMobileNo(obj[3] != null ? obj[3].toString():"");
								callervo.setStatus(obj[4] != null ? obj[4].toString():"");
								callervo.setId(obj[0] != null ? (Long)obj[0]:null);
								vo.getIdNamesList().add(callervo);
							}
						}
					}
					
					if(returnList != null && !returnList.isEmpty()){
						for (AlertVO alertVO : returnList) {
							if(alertVO.getIdNamesList().size() > 1)
								alertVO.setCallerDuplicate("YES");
						}
					}
					
				}
			}catch(Exception e){
				LOG.error("Error occured getSocialAlertDetailsByStatus() method of AlertService{}",e);
			}
			return returnList;
		}
	 
	 public String changeVeificationStatusDetails(final AlertVO alertvo,final Long userId){
		 
		 String  status = new String();
		 
		 try{
				status = (String) transactionTemplate.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						
						//Saving comment In alertComment
						AlertComment alertComment = new AlertComment();
						alertComment.setAlertId(alertvo.getAlertId());
						alertComment.setComments(alertvo.getComment());
						alertComment.setInsertedBy(userId);
						alertComment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						alertComment.setIsDeleted("N");
						alertComment = alertCommentDAO.save(alertComment);
						
						//saving alertTtrcking
						AlertTracking alertTracking = new AlertTracking();
						alertTracking.setAlertId(alertvo.getAlertId());						
						alertTracking.setAlertCommentId(alertComment.getAlertCommentId());
						
						if(alertvo.getStatus() !=null && !alertvo.getStatus().trim().isEmpty() &&
								alertvo.getStatus().trim().equalsIgnoreCase("Y")){
							alertTracking.setAlertTrackingActionId(1l);
							alertTracking.setAlertStatusId(alertvo.getStatusId());
						}else if(alertvo.getStatus() !=null && !alertvo.getStatus().trim().isEmpty() &&
								alertvo.getStatus().trim().equalsIgnoreCase("N")){
							alertTracking.setAlertTrackingActionId(2l);
							alertTracking.setAlertStatusId(14l);
						}
						
						
						alertTracking.setInsertedBy(userId);
						alertTracking.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						//alertTracking.setAlertSourceId(6l);
						alertTrackingDAO.save(alertTracking);
						
						Alert alert = alertDAO.get(alertvo.getAlertId());
						
						if(alert != null){
							if(alertvo.getStatus() !=null && !alertvo.getStatus().trim().isEmpty() &&
									alertvo.getStatus().trim().equalsIgnoreCase("Y")){
								
								alert.setAlertStatusId(alertvo.getStatusId() !=null ? alertvo.getStatusId():null);
								alert.setIsVerified(alertvo.getStatus());
								
							}else if(alertvo.getStatus() !=null && !alertvo.getStatus().trim().isEmpty() &&
									alertvo.getStatus().trim().equalsIgnoreCase("N")){
								alert.setIsVerified(alertvo.getStatus());
							}
							
							alertDAO.save(alert);
						}
						
						return "success";
					
					}
				});
			}catch(Exception e){
				LOG.error("Error occured changeVeificationStatusDetails() method of AlertService{}",e);
			}
		 return status;
	 }
	 public Date getAfterDayForNoOfDays(int noOfDays, Date today){
			Calendar cal = new GregorianCalendar();
			cal.setTime(today);
			
			cal.add(Calendar.DAY_OF_MONTH, noOfDays);
			Date prevDay = cal.getTime();
			return prevDay;
		}
	public List<AlertsSummeryVO> getAlertEfficiencyList1(List<Integer> daysLst, List<Long> departmentIds,List<Long> sourceIds,List<Long> alertstatusIds
				,String startDate,String endDate){
			LOG.debug(" Entered Into getAlertEfficiencyList");
			List<AlertsSummeryVO> finalList = new ArrayList<AlertsSummeryVO>();
			 
			try{
				
				List<Long> totalAlertStatusIds = new ArrayList<Long>(0);
				
				totalAlertStatusIds.addAll(IConstants.ALERT_STATUS_IDS);
				
				Date fromDate = null;        
				Date toDate = null; 
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Long totalAlerts =null;
				if(startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0){
					fromDate = sdf.parse(startDate);
					toDate = sdf.parse(endDate);
					 totalAlerts = alertDAO.getTotalAlertsByStatusIdsAndDates(fromDate,toDate,departmentIds,sourceIds,totalAlertStatusIds);
				}
				if(daysLst!=null && daysLst.size()>0){
					for(Integer day:daysLst){
						AlertsSummeryVO temp = new AlertsSummeryVO();
						temp.setEffcncyType("First "+day+" Days");
						temp.setEffcncyPrcnt("0.0");
						temp.setClrFrEffcncy("red");
						temp.setDays(day);
						//Date today 	 =  dateUtilService.getCurrentDateAndTime();
						List<String> datesStrList = dateUtilService.getDaysBetweenDatesStringFormat(fromDate, toDate);
						
						if(datesStrList != null && datesStrList.size() >0){
							if(datesStrList.size() >0 && datesStrList.size() <=day){
								getEfficiencyOfDates1(fromDate, temp, toDate, departmentIds,sourceIds,alertstatusIds,totalAlerts);
							}else if(datesStrList.size() >0 && datesStrList.size() >day){
								Date afterDay =  null;
								afterDay =  getAfterDayForNoOfDays(day, fromDate);
								getEfficiencyOfDates1(fromDate, temp, afterDay, departmentIds,sourceIds,alertstatusIds,totalAlerts);
							}
						}
						
						finalList.add(temp);
					}
				}
				
				AlertsSummeryVO temp = new AlertsSummeryVO();
				temp.setEffcncyType(" Overall ");
				temp.setEffcncyPrcnt("0.0");
				temp.setClrFrEffcncy("red");
				temp.setDays(0);
				getEfficiencyOfDates1(fromDate, temp, toDate, departmentIds,sourceIds,alertstatusIds, totalAlerts);
				finalList.add(temp);
			}catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception Raised in getAlertEfficiencyList");
			}
			return finalList;
		}
	//abcd
		public List<AlertsSummeryVO> getAlertEfficiencyList2(List<Long> departmentIds,List<Long> sourceIds,List<Long> alertstatusIds
				,String startDate,String endDate,int rangeValue){
			LOG.debug(" Entered Into getAlertEfficiencyList");
			List<AlertsSummeryVO> finalList = new ArrayList<AlertsSummeryVO>();
			 
			try{
				
				List<Long> totalAlertStatusIds = new ArrayList<Long>(0);
				
				totalAlertStatusIds.addAll(IConstants.ALERT_STATUS_IDS);
				
				Date fromDate = null;        
				Date toDate = null; 
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				if(startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0){
					fromDate = sdf.parse(startDate);
					toDate = sdf.parse(endDate);
				}
				List<Object[]> totalAlertsDateWise = null;
				if(alertstatusIds != null && alertstatusIds.size() > 0){
					totalAlertsDateWise = alertDAO.getDifferenceDay(fromDate,toDate,departmentIds,sourceIds,alertstatusIds);
				}
				Long ttlAlts = 0L;
				if(totalAlertsDateWise != null && totalAlertsDateWise.size() > 0){
					ttlAlts = Long.valueOf(totalAlertsDateWise.size());
				}
				
				//create a map for date and count
				Map<Long,Long> noOfDayAndCountMap = new HashMap<Long,Long>();
				if(totalAlertsDateWise != null && totalAlertsDateWise.size() > 0){
					for(Object[] param : totalAlertsDateWise){
						noOfDayAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), (commonMethodsUtilService.getLongValueForObject(noOfDayAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[3])))+1L));
					}
				}
				List<String> dayList = DateUtilService.getDaysBetweenDatesStringFormat(fromDate, toDate);
				Long maxDays = Collections.max(noOfDayAndCountMap.keySet());
				int maxVal = Integer.parseInt(maxDays.toString());
				
				AlertsSummeryVO alertVO = null;
				if(dayList != null && dayList.size() > 0 && totalAlertsDateWise != null && totalAlertsDateWise.size() > 0){
					int order = rangeValue;
					int loopCount = maxVal/rangeValue;
					if(maxVal%rangeValue > 0){
						loopCount+=1;
					}
					if(maxVal == 0){
						loopCount = 1;
					}
					int cnt = 0;
					for(int i = 1 ; i <= loopCount ; i++){
						alertVO = new AlertsSummeryVO();
						if(dayList.size() <= rangeValue){
							alertVO.setName("<="+dayList.size()+" Days");
						}else{
							alertVO.setName("<= "+order+" Days");
						}
						alertVO.setRange(cnt+"-"+order);
						for(int k=cnt ; k<=order ; k++){
							alertVO.setRangeCount(alertVO.getRangeCount()+commonMethodsUtilService.getLongValueForObject(noOfDayAndCountMap.get(Long.valueOf(k))));
						}
						if(cnt == 0){
							cnt++;
						}
						cnt+=rangeValue;
						
						alertVO.setTtlAlrtss(commonMethodsUtilService.getLongValueForObject(ttlAlts));
						for(int j=0 ; j<=order ; j++){
							alertVO.setEffcncyAlerts(alertVO.getEffcncyAlerts()+commonMethodsUtilService.getLongValueForObject(noOfDayAndCountMap.get(Long.valueOf(j))));
						}
						finalList.add(alertVO); 
						order+=rangeValue;
					}
				}
				if(finalList != null && finalList.size() > 0){
					for(AlertsSummeryVO param : finalList){
						String percentage = "0.0";
						if(totalAlertsDateWise != null && totalAlertsDateWise.size() > 0)
							percentage = (new BigDecimal(param.getEffcncyAlerts()*(100.0)/ttlAlts)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
							param.setEffcncyPrcnt(commonMethodsUtilService.getStringValueForObject(percentage));
					}
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception Raised in getAlertEfficiencyList");
			}
			return finalList;
		}
		
		public void getEfficiencyOfDates1(Date fromDate, AlertsSummeryVO temp, Date  afterDay,  List<Long> departmentIds,List<Long> sourceIds,List<Long> alertstatusIds,Long totalAlerts){
			
			Long completedAlerts = alertDAO.getTotalAlertsByStatusIdsAndDates(fromDate,afterDay,departmentIds,sourceIds,alertstatusIds);
				
				
				temp.setTtlAlrtss(totalAlerts);
				temp.setEffcncyAlerts(completedAlerts);
				
				String percentage = "0.0";
				if(totalAlerts != null && totalAlerts.longValue() >0l)
				 percentage = (new BigDecimal(completedAlerts*(100.0)/totalAlerts)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				
				if(percentage != null){
				float prcntgeFlt = Float.parseFloat(percentage);
				
				if(temp.getDays()==30){
					if(prcntgeFlt>=90.0f){
						temp.setClrFrEffcncy("green");
					}
				}else{
					if(prcntgeFlt>=95.0f){
						temp.setClrFrEffcncy("green");
					}
				}
				}
				
				temp.setEffcncyPrcnt(percentage);
			
			
		}
		
		public List<AlertTrackingVO> getSocialAlertFeedBackDetails(Long userId,String startdateStr,String endDateStr,String mobileNo,Long departmentId){
			List<AlertTrackingVO> voList = new ArrayList<AlertTrackingVO>(0);
			try {
				Date startDate=null;
				Date endDate = null;
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				if(startdateStr != null && !startdateStr.isEmpty() && startdateStr != null && !startdateStr.isEmpty()){
					 startDate=format.parse(startdateStr);
					 endDate = format.parse(endDateStr);
				}
				
				List<AlertFeedbackStatus> feedbackStatusList =  alertFeedbackStatusDAO.getAll();
				AlertTrackingVO returnVO = new AlertTrackingVO();
				 if(commonMethodsUtilService.isListOrSetValid(feedbackStatusList)){
					 for (AlertFeedbackStatus alertStatus : feedbackStatusList) {
						 if(alertStatus.getIsDeleted().equalsIgnoreCase("N")){
							 AlertTrackingVO vo = new AlertTrackingVO();
							 vo.setAlertStatusId(alertStatus.getAlertFeedbackStatusId());
							 vo.setStatus(alertStatus.getStatus());
							 returnVO.getStatusList().add(vo);
						 }
					}
				 }
				 
				 List<Object[]> objList1  = alertTrackingDAO.getFeedbackStatuswiseAlertsDetailsOfSocial(mobileNo, startDate, endDate,departmentId);
				
				Map<Long,Long> statusWiseMap = new HashMap<Long, Long>(0);
				if(objList1 != null && objList1.size() > 0){
					for (Object[] param : objList1) {
						AlertTrackingVO vo = (AlertTrackingVO)setterAndGetterUtilService.getMatchedVOfromList(returnVO.getStatusList(), "alertStatusId", commonMethodsUtilService.getStringValueForObject(param[0]));
						if(vo != null){
							vo.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
						}
						statusWiseMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
					}
				}
					
				List<AlertTrackingVO> socialMediaTypeList = new ArrayList<AlertTrackingVO>(0);
				List<Object[]> socialMediaObj =  alertDAO.getAllSocialMediaType();
				if(commonMethodsUtilService.isListOrSetValid(socialMediaObj)){
					for (Object[] objects : socialMediaObj) {						
						AlertTrackingVO vo = new AlertTrackingVO();
						vo.setAlertStatusId(objects[0] !=null ? (Long)objects[0]:null);
						vo.setStatus(objects[1] !=null ? objects[1].toString():"");
						
						 if(commonMethodsUtilService.isListOrSetValid(returnVO.getStatusList())){
							 for (AlertTrackingVO alertTrackingVO : returnVO.getStatusList()) {
								 AlertTrackingVO vo1 = new AlertTrackingVO();
								 vo1.setAlertStatusId(alertTrackingVO.getAlertStatusId());
								 vo1.setStatus(alertTrackingVO.getStatus());
								 vo.getStatusList().add(vo1);
							}
						 } 						 
						 socialMediaTypeList.add(vo);
					}
				}
				 
				 List<Object[]> objList = alertTrackingDAO.getSocialMediaTypeAlertsFeedbackDetails(mobileNo, startDate, endDate,departmentId);
				 
				if(objList != null && objList.size() > 0 && commonMethodsUtilService.isListOrSetValid(socialMediaTypeList)){
					for (Object[] param : objList) {
						AlertTrackingVO vo = (AlertTrackingVO)setterAndGetterUtilService.getMatchedVOfromList(socialMediaTypeList, "alertStatusId", commonMethodsUtilService.getLongValueForObject(param[1]).toString());
						if(vo != null){
							AlertTrackingVO vo1 = (AlertTrackingVO)setterAndGetterUtilService.getMatchedVOfromList(vo.getStatusList(), "alertStatusId", commonMethodsUtilService.getLongValueForObject(param[0]).toString());
							if(vo1 != null){
								vo1.setCount(commonMethodsUtilService.getLongValueForObject(param[3]));
							}
						}else{
							vo = socialMediaTypeList.get(0);
							AlertTrackingVO vo1 = (AlertTrackingVO)setterAndGetterUtilService.getMatchedVOfromList(vo.getStatusList(), "alertStatusId", commonMethodsUtilService.getLongValueForObject(param[0]).toString());
							if(vo1 != null){
								vo1.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[3]));
							}
						}
					}
				}
				if(commonMethodsUtilService.isMapValid(statusWiseMap) && commonMethodsUtilService.isListOrSetValid(socialMediaTypeList)){
					AlertTrackingVO vo = socialMediaTypeList.get(0);
					 List<AlertTrackingVO> list =vo.getStatusList();
					 if(commonMethodsUtilService.isListOrSetValid(list)){
						 for (AlertTrackingVO statusVO : list) {
							 statusVO.setTotalCount(statusWiseMap.get(statusVO.getAlertStatusId()));
						}
					 }
				}
				 returnVO.getStatusList().clear();
				 returnVO.getStatusList().addAll(socialMediaTypeList);
				voList.add(returnVO);				
				
				
			} catch (Exception e) {
				LOG.error("Error occured getSocialAlertFeedBackDetails() method of AlertManagementSystemService");
			}
			return voList;
		}
		public List<AlertCoreDashBoardVO> getFeedbackAlertDetails(String fromDateStr,String toDateStr,Long stateId,Long deptId,Long sourceId,Long locationId,Long statusId,String type,String level){
			try{
				List<AlertCoreDashBoardVO> dtlsList = new ArrayList<AlertCoreDashBoardVO>();
				Date fromDate = null;        
				Date toDate = null; 
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				List<Long> alertIds = null;
				if(type != null && type.equalsIgnoreCase("feebbackAlert")){
					alertIds = alertDAO.getFeedbackAlertIds(fromDate, toDate, stateId, deptId, sourceId, null, null, locationId, statusId,level);
				}else if(type.equalsIgnoreCase("pendingFeedBack")){
					alertIds = alertDAO.getFeedbackAlertIds(fromDate, toDate, stateId, deptId, sourceId, new ArrayList<Long>(){{add(12l);}}, "pendingFeedback", locationId, null,level);
				}
				List<Object[]> altDtlsList = alertDAO.getAlertDtlsForGrievance(alertIds);
				setAlertDtls(dtlsList,altDtlsList);
				return dtlsList;
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Error occured getFeedbackAlertDetails() method of AlertService{}");
			}
			return null;  
		}
		public List<AlertCoreDashBoardVO> getLocationWiseFeebBackAlert(String fromDateStr,String toDateStr,Long stateId,Long deptId,Long sourceId,Long locationId,Long statusId,String areaType,String groupType,String type){
			try{
				List<AlertCoreDashBoardVO> dtlsList = new ArrayList<AlertCoreDashBoardVO>();
				Date fromDate = null;        
				Date toDate = null; 
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				List<Long> alertIds = null;
				if(type != null && type.equalsIgnoreCase("feebbackAlert")){
					alertIds = alertDAO.getLocationWiseFeebbackAlertIds(fromDate, toDate, stateId, deptId, sourceId, groupType, null, locationId, null,statusId,areaType);
				}else if(type.equalsIgnoreCase("pendingFeedBack")){
					alertIds = alertDAO.getLocationWiseFeebbackAlertIds(fromDate, toDate, stateId, deptId, sourceId, groupType, "pendingFeedback", locationId, new ArrayList<Long>(){{add(12l);}}, null,areaType);
				}else if(type.equalsIgnoreCase("reopen")){
					alertIds = alertDAO.getLocationWiseFeebbackAlertIds(fromDate, toDate, stateId, deptId, sourceId, groupType, "reopen", locationId, new ArrayList<Long>(){{add(11l);}}, null,areaType);
				}
				List<Object[]> altDtlsList = alertDAO.getAlertDtlsForGrievance(alertIds);
				setAlertDtls(dtlsList,altDtlsList);
				return dtlsList;
				
			}catch(Exception e){  
				e.printStackTrace();
				LOG.error("Error occured getLocationWiseFeebBackAlert() method of AlertService{}");
			}
			return null;
		}
		public List<IdNameVO> getAllCategoryForLocationWiseGrievance(){
			try{
				List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
				IdNameVO idNameVO = null;
				List<Object[]> list = alertCategoryDAO.getAllCategoryForLocationWiseGrievance();
				if(list != null && list.size() > 0){
					for(Object[] param : list){
						idNameVO = new IdNameVO();
						idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						idNameVOs.add(idNameVO);
					}
				}
				return idNameVOs;
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
		public void pushFeedbackCount(List<AlertOverviewVO> finalList,Map<Long,Map<Long,Long>> categoryIdAndStatusIdAndCountMap,List<Object[]> feedbackList,List<Object[]> feedbackListForPending){
			try{
				//for feedback
				Map<Long,Map<Long,Long>> categoryIdAndFeedbackStatusIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
				Map<Long,Long> feedbackStatusIdAndCountMap = null;
				if(feedbackList != null && feedbackList.size() > 0){
					for(Object[] param : feedbackList){
						feedbackStatusIdAndCountMap = categoryIdAndFeedbackStatusIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						if(feedbackStatusIdAndCountMap == null){
							feedbackStatusIdAndCountMap = new HashMap<Long,Long>();
							feedbackStatusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
							categoryIdAndFeedbackStatusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), feedbackStatusIdAndCountMap);
						}
						feedbackStatusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
					}
				}
				
				//for pending feedback
				Map<Long,Long> categoryIdAndCountMapForPending = new HashMap<Long,Long>();
				
				if(feedbackListForPending != null && feedbackListForPending.size() > 0){
					for(Object[] param : feedbackListForPending){
						categoryIdAndCountMapForPending.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
					}
				}
				
				//get all feedback status id and name
				List<Object[]> feedbackStatusList = alertFeedbackStatusDAO.getFeedBackStatus();
				Map<Long,String> feedbackStatusIdAndStatusName = new HashMap<Long,String>();
				if(feedbackStatusList != null && feedbackStatusList.size() > 0){
					for(Object[] param : feedbackStatusList){
						feedbackStatusIdAndStatusName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					}
				}
				
				for(AlertOverviewVO param : finalList){
					feedbackStatusIdAndStatusName.put(4L, "Pending Feedback");
					buildFeedbackStatusInTemplate(param,feedbackStatusIdAndStatusName);
				}
				//push feedback status into vo.
				
				//push the feedback count in vo.
				for(AlertOverviewVO param : finalList){
					Long categoryId = param.getId();
					if(categoryIdAndFeedbackStatusIdAndCountMap.size() > 0 && categoryIdAndFeedbackStatusIdAndCountMap.get(categoryId) != null){
						for(AlertOverviewVO innerParam : param.getSubList2()){
							if(categoryIdAndFeedbackStatusIdAndCountMap.get(categoryId).get(innerParam.getId()) != null){
								innerParam.setTotalAlertCnt(categoryIdAndFeedbackStatusIdAndCountMap.get(categoryId).get(innerParam.getId()));
							}else{
								innerParam.setTotalAlertCnt(0L);
							}
						}
					}
				}
				//now push feedback pending count into vo.
				for(AlertOverviewVO param : finalList){
					Long categoryId = param.getId();
					for(AlertOverviewVO innerParam : param.getSubList2()){
						if(innerParam.getId().longValue() == 4L){//feedback pending count   
							innerParam.setTotalAlertCnt(commonMethodsUtilService.getLongValueForObject(categoryIdAndCountMapForPending.get(categoryId)));
						}
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		public void buildFeedbackStatusInTemplate(AlertOverviewVO param,Map<Long,String> feedbackAndStatusIdMap){
			try{
				AlertOverviewVO alertOverviewVO = null;
				List<AlertOverviewVO> AlertOverviewVOs = new ArrayList<AlertOverviewVO>();
				for(Entry<Long,String> entry : feedbackAndStatusIdMap.entrySet()){
					alertOverviewVO = new AlertOverviewVO();
					alertOverviewVO.setId(commonMethodsUtilService.getLongValueForObject(entry.getKey()));
					alertOverviewVO.setName(commonMethodsUtilService.getStringValueForObject(entry.getValue()));
					AlertOverviewVOs.add(alertOverviewVO);
				}
				param.setSubList2(AlertOverviewVOs);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		public void pushReopenCount(List<AlertOverviewVO> finalList,List<Object[]> reopenList){
			try{
				Map<Long,Long> categoryIdAndReopenCountMap = new HashMap<Long,Long>();
				if(reopenList != null && reopenList.size() > 0){
					for(Object[] param : reopenList){
						categoryIdAndReopenCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]	), commonMethodsUtilService.getLongValueForObject(param[2]));
					}
				}
				for(AlertOverviewVO param : finalList){
					if(categoryIdAndReopenCountMap != null && categoryIdAndReopenCountMap.get(param.getId()) != null){
						param.setReopenCount(categoryIdAndReopenCountMap.get(param.getId()));
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		public AlertOverviewVO getStateLevelAlertDetails(String fromDateStr, String toDateStr,Long stateId,Long departmentId,Long sourceId,String level){
			try{
				AlertOverviewVO finalVO = new AlertOverviewVO();
				Date fromDate = null;
				Date toDate = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				List<Object[]> alertList = null;
				List<Object[]> feebbackStatusObjLst = null;
				List<Object[]> pendingFeebbakObjLst = null;
				List<Object[]> reopeObjLstForCallCenter = null;
				List<Object[]> reopeObjLstForOfficer = null;
				
				List<Object[]> depaStatusList = alertDepartmentStatusDAO.getAllStatusForDepartment(departmentId);
				List<Object[]> rtrnfeedbackStatusObjLst = alertFeedbackStatusDAO.getFeedBackStatus();
			 	
		 		alertList = alertDAO.getTotalAlertGroupByStatusForGrievancePage(fromDate, toDate, stateId,sourceId,departmentId,level);
				feebbackStatusObjLst = alertDAO.getStateLevalfeedbackAlertCnt(fromDate, toDate, stateId, departmentId, sourceId,"feedbackStatus", null,level);
				pendingFeebbakObjLst = alertDAO.getStateLevalfeedbackAlertCnt(fromDate, toDate, stateId, departmentId, sourceId,"pendingFeedback",new ArrayList<Long>(){{add(12l);}},level);
				reopeObjLstForCallCenter = alertDAO.getStateLevalReopenAlertCnt(fromDate, toDate, stateId, departmentId, sourceId,level,"callCenter");
				reopeObjLstForOfficer = alertDAO.getStateLevalReopenAlertCnt(fromDate, toDate, stateId, departmentId, sourceId,level,"officer");
				
			 	prepareStateLevelAlertStatusWise(finalVO,alertList,depaStatusList);
			 	prepareStateLevelAlertFeedbackStatusWise(finalVO,feebbackStatusObjLst,rtrnfeedbackStatusObjLst);//feebback status alert
			 	prepareStateLevelFeedbackPendingAlerts(finalVO,pendingFeebbakObjLst);//pending feebback alert 
			 	prepareStateLevelReopenAlertsForCallCenter(finalVO,reopeObjLstForCallCenter);//reopen alert for call center
			 	prepareStateLevelReopenAlertsForOfficer(finalVO,reopeObjLstForOfficer);//reopen alert for call officer
			 	//overAll reopen count.
			 	finalVO.setOverallReopenCount(finalVO.getReopenCount()+finalVO.getReopenCountForOfficer());
				return finalVO;
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Error occured getLocationWiseFeebbackAlertCnt() method of AlertService{}");
			}
			return null;
		}
		
		
		void prepareStateLevelAlertStatusWise(AlertOverviewVO finalVO,List<Object[]> alertList,List<Object[]> depaStatusList){
			try{
				List<AlertOverviewVO> alertOverviewVOs = new ArrayList<AlertOverviewVO>();
				AlertOverviewVO alertOverviewVO = null;
				if(depaStatusList != null && depaStatusList.size() > 0){
					for(Object[] param : depaStatusList){
						alertOverviewVO = new AlertOverviewVO();
						alertOverviewVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						alertOverviewVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						alertOverviewVOs.add(alertOverviewVO);
					}
				}
				
				//create a map for statusId and alert count
				Map<Long,Long> statusIdAndAlertCountMap = new HashMap<Long,Long>(0);
				if(alertList != null && alertList.size() > 0){
					for(Object[] param : alertList){
						statusIdAndAlertCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[2]));
					}
				}
				//push status count into vo.
				if(alertOverviewVOs != null && alertOverviewVOs.size() > 0){
					for(AlertOverviewVO param : alertOverviewVOs){
						param.setAlertCnt(commonMethodsUtilService.getLongValueForObject(statusIdAndAlertCountMap.get(param.getId())));
					}
				}
				finalVO.setSubList1(alertOverviewVOs);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	 	void prepareStateLevelAlertFeedbackStatusWise(AlertOverviewVO finalVO,List<Object[]> feebbackStatusObjLst,List<Object[]> rtrnfeedbackStatusObjLst){
	 		try{
	 			List<AlertOverviewVO> alertOverviewVOs = new ArrayList<AlertOverviewVO>();
				AlertOverviewVO alertOverviewVO = null;
				if(rtrnfeedbackStatusObjLst != null && rtrnfeedbackStatusObjLst.size() > 0){
					for(Object[] param : rtrnfeedbackStatusObjLst){
						alertOverviewVO = new AlertOverviewVO();
						alertOverviewVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						alertOverviewVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						alertOverviewVO.setType("feebbackAlert");
						alertOverviewVOs.add(alertOverviewVO);
					}
					alertOverviewVO = new AlertOverviewVO();
					alertOverviewVO.setId(4L);
					alertOverviewVO.setName("Pending Feedback");
					alertOverviewVO.setType("pendingFeedBack");
					alertOverviewVOs.add(alertOverviewVO);
				}
				
				//create a map for statusId and alert count
				Map<Long,Long> statusIdAndAlertCountMap = new HashMap<Long,Long>(0);
				if(feebbackStatusObjLst != null && feebbackStatusObjLst.size() > 0){
					for(Object[] param : feebbackStatusObjLst){
						statusIdAndAlertCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[2]));
					}
				}
				//push status count into vo.
				if(alertOverviewVOs != null && alertOverviewVOs.size() > 0){
					for(AlertOverviewVO param : alertOverviewVOs){
						param.setAlertCnt(commonMethodsUtilService.getLongValueForObject(statusIdAndAlertCountMap.get(param.getId())));
					}
				}
				finalVO.setSubList2(alertOverviewVOs);  
			}catch(Exception e){
				e.printStackTrace();
			}
	 	}
	 	void prepareStateLevelFeedbackPendingAlerts(AlertOverviewVO finalVO,List<Object[]> pendingFeebbakObjLst){
	 		try{
				if(pendingFeebbakObjLst != null && pendingFeebbakObjLst.size() > 0){
					if(finalVO.getSubList2() != null && finalVO.getSubList2().size() > 0){
						for(AlertOverviewVO param : finalVO.getSubList2()){
							if(param.getId().longValue() == 4L){
								param.setAlertCnt(commonMethodsUtilService.getLongValueForObject(pendingFeebbakObjLst.get(0)[2]));
							}
						}
					}
				}
	 			
			}catch(Exception e){
				e.printStackTrace();
			}
	 	}
	 	void prepareStateLevelReopenAlertsForCallCenter(AlertOverviewVO finalVO,List<Object[]> reopeObjLst){
	 		try{
				if(reopeObjLst != null && reopeObjLst.size() > 0){
					finalVO.setReopenCount(commonMethodsUtilService.getLongValueForObject(reopeObjLst.get(0)[2]));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
	 	}
	 	
	 	public String createMeekosamGrievanceAlert(final GrievanceAlertVO inputVO,final Long userId, final Map<File,String> mapFiles)
		{
			String resultStatus = null;
			try {
				resultStatus = (String) transactionTemplate.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						String rs = new String();
						DateUtilService date = new DateUtilService();
						String callCenterVersion = IConstants.CALL_CENTER_VERSION.toString();
						Long levelValue = 999999L;
						Long alertId = null;
						
						if(inputVO.getAlertId() != null && inputVO.getAlertId().longValue() > 0l){
							AlertCallerRelation alertCallerRelation = new AlertCallerRelation();

							List<Long> existingList = alertCallerDAO.checkIsExist(inputVO.getMobileNo(),inputVO.getName());
							if(!commonMethodsUtilService.isListOrSetValid(existingList)){
								AlertCaller alertCaller = new AlertCaller();
								alertCaller.setCallerName(inputVO.getName());
								alertCaller.setAddress(inputVO.getAddress());
								alertCaller.setMobileNo(inputVO.getMobileNo());
								alertCaller.setEmail(inputVO.getEmailId());
								alertCaller = alertCallerDAO.save(alertCaller);
								//alert.setAlertCallerId(alertCaller.getAlertCallerId());
								alertCallerRelation.setAlertCallerId(alertCaller.getAlertCallerId());
							}
							else{
								//alert.setAlertCallerId(existingList.get(0));
								alertCallerRelation.setAlertCallerId(existingList.get(0));
							}
							Alert alert = alertDAO.get(inputVO.getAlertId());
							alertCallerRelation.setAlertId(inputVO.getAlertId());
							alertCallerRelation.setIsDeleted("N");
							alertCallerRelation = alertCallerRelationDAO.save(alertCallerRelation);
							
							levelValue = alert.getUserAddress(). getConstituency() != null ? alert.getUserAddress().getConstituency().getConstituencyId() : 999999L;
							alertId = alert.getAlertId();
						}
						else{
							Alert alert = new Alert();
							AlertCallerRelation alertCallerRelation = new AlertCallerRelation();

							List<Long> existingList = alertCallerDAO.checkIsExist(inputVO.getMobileNo(),inputVO.getName());
							if(!commonMethodsUtilService.isListOrSetValid(existingList)){
								AlertCaller alertCaller = new AlertCaller();
								alertCaller.setCallerName(inputVO.getName());
								alertCaller.setAddress(inputVO.getAddress());
								alertCaller.setMobileNo(inputVO.getMobileNo());
								alertCaller.setEmail(inputVO.getEmailId());
								alertCaller = alertCallerDAO.save(alertCaller);
								//alert.setAlertCallerId(alertCaller.getAlertCallerId());
								alertCallerRelation.setAlertCallerId(alertCaller.getAlertCallerId());
							}
							else{
								//alert.setAlertCallerId(existingList.get(0));
								alertCallerRelation.setAlertCallerId(existingList.get(0));
							}

							alert.setAlertSeverityId(2l);
							alert.setAlertTypeId(2l);
							alert.setImpactLevelId(inputVO.getLocationLevelId());
							alert.setImpactLevelValue(inputVO.getLocationValue());
							alert.setDescription(inputVO.getDescription().toString());
							alert.setCreatedBy(userId);
							alert.setUpdatedBy(userId);
							/*if(inputVO.getLocationTypeStr() != null && inputVO.getLocationTypeStr().trim().equalsIgnoreCase("Rural"))
								alert.setImpactScopeId(7l);
							else if(inputVO.getLocationTypeStr() != null && inputVO.getLocationTypeStr().trim().equalsIgnoreCase("Urban"))
								alert.setImpactScopeId(13l);*/
							if(inputVO.getLocationLevelId() == 2l)
								alert.setImpactScopeId(1l);
							else if(inputVO.getLocationLevelId() == 3l)
								alert.setImpactScopeId(2l);
							else if(inputVO.getLocationLevelId() == 5l)
								alert.setImpactScopeId(5l);
							else if(inputVO.getLocationLevelId() == 6l)
								alert.setImpactScopeId(6l);
							else if(inputVO.getLocationLevelId() == 7l)
								alert.setImpactScopeId(12l);
							else if(inputVO.getLocationLevelId() == 11l)
								alert.setImpactScopeId(7l);
							else if(inputVO.getLocationLevelId() == 12l)
								alert.setImpactScopeId(14l);
							else if(inputVO.getLocationLevelId() == 13l)
								alert.setImpactScopeId(13l);

							alert.setAlertStatusId(2l);// default pending status

							alert.setAlertSourceId(inputVO.getInformationSourceId());
							alert.setCreatedTime(date.getCurrentDateAndTime());
							alert.setUpdatedTime(date.getCurrentDateAndTime());
							alert.setIsDeleted("N");
							alert.setAlertCategoryId(inputVO.getAlertCategoryId());
							alert.setTitle(inputVO.getAlertTitle());

							UserAddress userAddress = saveUserAddressForMeekosamGrievanceAlert(inputVO);
							alert.setAddressId(userAddress.getUserAddressId());
							
							levelValue=userAddress.getConstituency() != null ? userAddress.getConstituency() .getConstituencyId():999999L;

							alert.setAlertCallerTypeId(inputVO.getCallerTypeId());
							alert.setAlertEntrySourceId(inputVO.getEntrySourceId());
							alert.setAlertIssueTypeId(inputVO.getIssueTypeId());
							
							if(inputVO.getAlertCategoryId() == 6l){
								alert.setAlertSourceId(7l);
								alert.setMondayGrievanceTypeId(1l);
							}
							else if(inputVO.getAlertCategoryId() == 7l){
								alert.setAlertSourceId(8l);
								alert.setJanmabhoomiTypeId(1l);
							}
							else if(inputVO.getAlertCategoryId() == 8l){
								alert.setAlertSourceId(9l);
								alert.setSpecialGrievanceTypeId(1l);
							}
							else if(inputVO.getAlertCategoryId() == 9l){
								alert.setAlertSourceId(10l);
								alert.setGeneralGrievanceTypeId(1l);
							}
								
							alert.setGovtDepartmentId(inputVO.getDepartmentId());
							alert.setAlertIssueSubTypeId(inputVO.getAlertIssueSubTypeId());
							alert.setIsMultiple("N");
							//alert.setAlertCallCenterTypeId(inputVO.getAlertCallCenterTypeId() !=null ? inputVO.getAlertCallCenterTypeId():null);

							alert = alertDAO.save(alert);
							
							alertId = alert.getAlertId();
							alertCallerRelation.setAlertId(alert.getAlertId());
							alertCallerRelation.setIsDeleted("N");
							alertCallerRelation = alertCallerRelationDAO.save(alertCallerRelation);

							//saveAlertDocument(alert.getAlertId(),userId,mapFiles);
							saveAlertDocumentNew(alert.getAlertId(),userId,mapFiles);

							AlertComment alertComment = new AlertComment();
							alertComment.setComments(inputVO.getDescription().toString());
							alertComment.setAlertId(alert.getAlertId());
							alertComment.setInsertedTime(date.getCurrentDateAndTime());
							alertComment.setIsDeleted("N");
							alertComment.setInsertedBy(userId);
							alertComment = alertCommentDAO.save(alertComment);

							AlertTrackingVO alertTrackingVO = new AlertTrackingVO();
							alertTrackingVO.setUserId(userId);
							alertTrackingVO.setAlertCommentId(alertComment.getAlertCommentId());
							alertTrackingVO.setAlertUserTypeId(inputVO.getEntrySourceId());
							/*if(inputVO.getAssignList() != null && inputVO.getAssignList().size() > 0)
							 {
								 alertTrackingVO.setAlertStatusId(2l);
							 }else{*/
							alertTrackingVO.setAlertStatusId(2l);
							//}

							alertTrackingVO.setAlertId(alert.getAlertId());
							alertTrackingVO.setAlertTrackingActionId(IConstants.ALERT_ACTION_STATUS_CHANGE);

							saveAlertTrackingDetails(alertTrackingVO);	

							//Get Department Designation Officer Ids
							Long desigOfficerId = null;
							List<Long> designationOfficerIds = null;
							
							if(callCenterVersion.trim().equalsIgnoreCase("old"))
								designationOfficerIds = govtDepartmentDesignationOfficerDetailsNewDAO.getOldDesignationOfficerIds(inputVO.getLevelId(), inputVO.getMandalId(), inputVO.getDesignationId(), inputVO.getGovtOfficerId());
							else if(callCenterVersion.trim().equalsIgnoreCase("new"))
								designationOfficerIds = govtDepartmentDesignationOfficerDetailsNewDAO.getNewDesignationOfficerIdsNew(inputVO.getLevelId(), inputVO.getLevelValue(), inputVO.getDesignationId(), inputVO.getGovtOfficerId());
							
							if(designationOfficerIds != null && !designationOfficerIds.isEmpty())
								desigOfficerId = designationOfficerIds.get(0);

							//Officer Assigning
							if(callCenterVersion.trim().equalsIgnoreCase("old")){
								AlertAssignedOfficer alertAssignedOfficer = new AlertAssignedOfficer();
								alertAssignedOfficer.setAlertId(alert.getAlertId());
								alertAssignedOfficer.setGovtDepartmentDesignationOfficerId(desigOfficerId);
								alertAssignedOfficer.setGovtOfficerId(inputVO.getGovtOfficerId() !=null ? (Long)inputVO.getGovtOfficerId():null);
								alertAssignedOfficer.setInsertedBy(userId);
								alertAssignedOfficer.setUpdatedBy(userId);
								alertAssignedOfficer.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
								alertAssignedOfficer.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
								alertAssignedOfficer.setAlertStatusId(2l);
								alertAssignedOfficer.setIsDeleted("N");
								alertAssignedOfficer.setIsApproved("Y");
								alertAssignedOfficer = alertAssignedOfficerDAO.save(alertAssignedOfficer);

								//Officer Assigning Tracking
								AlertAssignedOfficerTracking alertAssignedOfficerTracking = new AlertAssignedOfficerTracking();
								alertAssignedOfficerTracking.setAlertAssignedOfficerId(alertAssignedOfficer.getAlertAssignedOfficerId());
								alertAssignedOfficerTracking.setAlertId(alert.getAlertId());
								alertAssignedOfficerTracking.setGovtDepartmentDesignationOfficerId(desigOfficerId);
								alertAssignedOfficerTracking.setGovtOfficerId(inputVO.getGovtOfficerId());
								alertAssignedOfficerTracking.setInsertedBy(userId);
								alertAssignedOfficerTracking.setUpdatedBy(userId);
								alertAssignedOfficerTracking.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
								alertAssignedOfficerTracking.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
								alertAssignedOfficerTracking.setAlertStatusId(2l);
								//alertAssignedOfficerTracking.setGovtAlertActionTypeId(1l);
								alertAssignedOfficerTracking.setIsApproved("Y");
								//alertAssignedOfficerTracking.setAlertSeviorityId(alert.getAlertSeverityId());

								alertAssignedOfficerTracking = alertAssignedOfficerTrackingDAO.save(alertAssignedOfficerTracking);
							}
							else if(callCenterVersion.trim().equalsIgnoreCase("new")){
								AlertAssignedOfficerNew alertAssignedOfficer = new AlertAssignedOfficerNew();
								alertAssignedOfficer.setAlertId(alert.getAlertId());
								alertAssignedOfficer.setGovtDepartmentDesignationOfficerId(desigOfficerId);
								alertAssignedOfficer.setGovtOfficerId(inputVO.getGovtOfficerId() !=null ? (Long)inputVO.getGovtOfficerId():null);
								alertAssignedOfficer.setInsertedBy(userId);
								alertAssignedOfficer.setUpdatedBy(userId);
								alertAssignedOfficer.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
								alertAssignedOfficer.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
								alertAssignedOfficer.setAlertStatusId(2l);
								alertAssignedOfficer.setIsDeleted("N");
								alertAssignedOfficer.setIsApproved("Y");
								alertAssignedOfficer.setGovtDepartmentId(inputVO.getDepartmentId());
								alertAssignedOfficer = alertAssignedOfficerNewDAO.save(alertAssignedOfficer);

								//Officer Assigning Tracking
								AlertAssignedOfficerTrackingNew alertAssignedOfficerTracking = new AlertAssignedOfficerTrackingNew();
								alertAssignedOfficerTracking.setAlertAssignedOfficerId(alertAssignedOfficer.getAlertAssignedOfficerId());
								alertAssignedOfficerTracking.setAlertId(alert.getAlertId());
								alertAssignedOfficerTracking.setGovtDepartmentDesignationOfficerId(desigOfficerId);
								alertAssignedOfficerTracking.setGovtOfficerId(inputVO.getGovtOfficerId());
								alertAssignedOfficerTracking.setInsertedBy(userId);
								alertAssignedOfficerTracking.setUpdatedBy(userId);
								alertAssignedOfficerTracking.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
								alertAssignedOfficerTracking.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
								alertAssignedOfficerTracking.setAlertStatusId(2l);
								alertAssignedOfficerTracking.setGovtAlertActionTypeId(1l);
								alertAssignedOfficerTracking.setIsApproved("Y");
								alertAssignedOfficerTracking.setAlertSeviorityId(alert.getAlertSeverityId());
								alertAssignedOfficerTracking.setGovtDepartmentId(inputVO.getDepartmentId());
								alertAssignedOfficerTracking = alertAssignedOfficerTrackingNewDAO.save(alertAssignedOfficerTracking);
							}
							
							/* SMS sending while assigning a new alert to any officer */
							
							List<String> mobileNos = govtOfficerNewDAO.getOfficerDetailsByOfficerId(inputVO.getGovtOfficerId());
							List<Long> userIdsList = govtDepartmentDesignationOfficerDetailsNewDAO.getuserIdDtlsForDesignationOfficerId(desigOfficerId);
							if(commonMethodsUtilService.isListOrSetValid(userIdsList)){
								Long assignedToUserID = userIdsList.get(0);
								if(mobileNos != null && mobileNos.size() > 0 && mobileNos.get(0).trim().length() > 0 && !mobileNos.get(0).trim().isEmpty()){
									 sendSMSTOAlertAssignedOfficer(inputVO.getDesignationId(),inputVO.getGovtOfficerId(),mobileNos!= null ? mobileNos.get(0):null,alert.getAlertId(),1l,assignedToUserID,"","",userId);	
								}
							}
							
						}
						
						rs = "success";
						return rs;
					}

				});
			} catch (Exception e) {
				resultStatus = "failure";
				LOG.error("Error occured getAlertCallerTypes() method of AlertService{}",e);
			}

			return resultStatus;
		}
	 	
	 	 public ResultStatus sendSMSTOAlertAssignedOfficer(Long designationId,Long govtOfficerId,String mobileNo,Long alertId,Long actionTypeId,Long userId,String status,String comment,Long mainUserId){
           	ResultStatus rs = new ResultStatus();
           	try {
           		Long alertStatusId =0L;
           		String callerName = "";
       			String callerMobileNo = "";
           		String userNameStr="ADMIN";
 				String departmentStr=" - ";
 				String designationStr=" - ";
 				String location="";
 				if(userId != null && userId.longValue()>0L){
 					List<Object[]> userdtls = govtDepartmentDesignationOfficerDetailsNewDAO.getDesigNameForUser(userId);
 					if(commonMethodsUtilService.isListOrSetValid(userdtls)){
 						for (Object[] param : userdtls) {
 							userNameStr = commonMethodsUtilService.getStringValueForObject(param[0]);
 							designationStr = commonMethodsUtilService.getStringValueForObject(param[1]);
 							departmentStr = commonMethodsUtilService.getStringValueForObject(param[2]);
 							
 							Long locationTypeId = commonMethodsUtilService.getLongValueForObject(param[3]);
 							Long scopeValue = commonMethodsUtilService.getLongValueForObject(param[4]);
 							
 							if(locationTypeId != null && locationTypeId.longValue()>0L){
 								GovtDepartmentWorkLocation workLocation = govtDepartmentWorkLocationDAO.get(scopeValue);
 								if(workLocation != null){
 									location=workLocation.getLocationName()+" "+(workLocation.getGovtDepartmentScope() != null? workLocation.getGovtDepartmentScope().getLevelName():"");
 								}
 							}
 						}
 					}
 				}
 				
           		GovtSMSAPIService govtSMSAPIService = new GovtSMSAPIService();
           		//get asigned officer dept, alert title
           		//0-title,1-deptId,2-deptName
           		Alert tempSMSAlert = alertDAO.get(16894L);// testing alert id for custom mobile no
           		Object[] objArr = alertDAO.getAlertDetailsForSMS(alertId);
           		List<Object[]> levelDetails = alertAssignedOfficerNewDAO.getAlertAssignedLevelDetails(alertId);
           		Long levelId=0L;
           		Long levelValue=0L;
           		List<Long> levelValuesList = new ArrayList<Long>(0);
           		if(commonMethodsUtilService.isListOrSetValid(levelDetails)){
           			levelId=commonMethodsUtilService.getLongValueForObject(levelDetails.get(0)[0]);
               		levelValue=commonMethodsUtilService.getLongValueForObject(levelDetails.get(0)[1]);
               		
               		List<Object[]> alertAssignedScopeDtls = govtDepartmentWorkLocationRelationDAO.getParentGovtSuperLevelInfo(levelValue);
               		if(commonMethodsUtilService.isListOrSetValid(alertAssignedScopeDtls)){
               			for (Object[] objects : alertAssignedScopeDtls) {
               				levelId = commonMethodsUtilService.getLongValueForObject(objects[0]);
               				Long tempLevelValue=commonMethodsUtilService.getLongValueForObject(objects[1]);
                   			levelValuesList.add(tempLevelValue);
							}
               		}
           		}
           		if(objArr != null){
           			alertStatusId = commonMethodsUtilService.getLongValueForObject(objArr[3]);
           			callerName = commonMethodsUtilService.getStringValueForObject(objArr[4]);
           			callerMobileNo = commonMethodsUtilService.getStringValueForObject(objArr[5]);              			
           			String message = "Alert is assigned to you,Please follow up and resolve.\nTitle : "+objArr[0].toString()+" \nDept"+objArr[2].toString();
           			List<String> smsText = govtSmsActionTypeDAO.getSMSTextforActionTypeId(actionTypeId,1L,1L,1L);//1 
            			
            			if(commonMethodsUtilService.isListOrSetValid(smsText)){
            				message = smsText.get(0) != null ? smsText.get(0).toString():message;
            				message=message.replace("flag0", "\n");
            				message=message.replace("flag1", objArr[0].toString()+"\n");
            				message=message.replace("flag2", userNameStr.toString()+"\n");
            				message=message.replace("flag3", designationStr.toString()+"\n");
            				message=message.replace("flag4", location.toString()+"\n");
            				message=message.replace("flag5", departmentStr.toString());
            				message=message.replace("flag6", comment.toString()+"\n");
            				message=message.replace("flag7", status.toString()+"\n");
            				message=message.replace("flag_99", alertId.toString());
            			}
            			if(tempSMSAlert.getDescription() != null && !tempSMSAlert.getDescription().isEmpty())
            				mobileNo=tempSMSAlert.getDescription().trim();
            			if(mobileNo != null && mobileNo.trim().length()>0 && message != null && !message.isEmpty() && message.length()>0)
            				govtSMSAPIService.senedSMSForGovtAlert(mobileNo,message); 
            			
            		    //srujana
            			if(mobileNo != null && !mobileNo.isEmpty()){
            			String[] mobileNOArr = mobileNo.split(",");
            			if(mobileNOArr != null && mobileNOArr.length>0){
            				for (int i = 0; i < mobileNOArr.length; i++) {
		              			GovtOfficerSmsDetailsVO smsDetailsVO =new GovtOfficerSmsDetailsVO(); 
		              			smsDetailsVO.setUserId(mainUserId);
		               			smsDetailsVO.setGovtOfficerId(govtOfficerId);
		               			smsDetailsVO.setAlertStatusId(alertStatusId);
		               			smsDetailsVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(mobileNOArr[i]));
		               			smsDetailsVO.setAlertId(alertId);
		               			smsDetailsVO.setSmsText(message);
		               			smsDetailsVO.setActionTypeId(actionTypeId);
		               			saveGovtOfficerSmsDetails(smsDetailsVO);
            				}
            			}
            			}
           		}
           		mobileNo="";
           		//get parent designation Id
           		List<Long> parentDesigIds = govtDepartmentDesignationHierarchyDAO.getParentDepartment(designationId);
           		if(parentDesigIds != null && parentDesigIds.size() > 0){
           			//get high level officer mobile nums
           			List<String> mobilenums = govtDepartmentDesignationOfficerDetailsNewDAO.getHigherOfficerMobileNums(parentDesigIds,levelId,levelValuesList);
           			
           			if(mobilenums != null && mobilenums.size() > 0){
           				String message = "Alert is assigned to "+objArr[2].toString()+" - "+govtDepartmentDesignationDAO.getDepartmentDetails(designationId)+" - "+ mobileNo+".\n Please follow up.";
           			
           				for (String tempMobileNo : mobilenums) {
           					if(tempMobileNo != null && !tempMobileNo.trim().isEmpty())
           						mobileNo = mobileNo.equalsIgnoreCase("")?tempMobileNo:mobileNo+","+tempMobileNo;
           				}
           				
           				List<String> smsText = govtSmsActionTypeDAO.getSMSTextforActionTypeId(actionTypeId,1L,1L,2L);//2 immidiate superior 
                			
                			if(commonMethodsUtilService.isListOrSetValid(smsText)){
                				message = smsText.get(0) != null ? smsText.get(0).toString():message;
                				//message=message.replace("flag3", objArr[2].toString());
                				//message=message.replace("flag4", govtDepartmentDesignationDAO.getDepartmentDetails(designationId)+" - "+ mobileNo);
                				message=message.replace("flag0", "\n");
                				message=message.replace("flag1", objArr[0].toString()+"\n");
                				message=message.replace("flag2", userNameStr.toString()+"\n");
                				message=message.replace("flag3", designationStr.toString()+"\n");
                				message=message.replace("flag4", location.toString()+"\n");
                				message=message.replace("flag5", departmentStr.toString());
                				message=message.replace("flag6", comment.toString()+"\n");
                				message=message.replace("flag7", status.toString()+"\n");
                				message=message.replace("flag8", status.toString()+"\n");
                				message=message.replace("flag_99", alertId.toString());
                			}
                			
                			
                			if(tempSMSAlert.getDescription() != null && !tempSMSAlert.getDescription().isEmpty())
                				mobileNo=tempSMSAlert.getDescription().trim();
                			if(mobileNo != null && mobileNo.trim().length()>0 && message != null && !message.isEmpty() && message.length()>0)
                				govtSMSAPIService.senedSMSForGovtAlert(mobileNo,message);
                			
                		   //srujana
                			if(mobileNo != null && !mobileNo.isEmpty()){
                			String[] mobileNOArr = mobileNo.split(",");
                			if(mobileNOArr != null && mobileNOArr.length>0){
                				for (int i = 0; i < mobileNOArr.length; i++) {
		              				GovtOfficerSmsDetailsVO smsDetailsVO =new GovtOfficerSmsDetailsVO();
		              				smsDetailsVO.setUserId(mainUserId);
		                   			//smsDetailsVO.setGovtOfficerId(govtOfficerId);
		              				smsDetailsVO.setAlertStatusId(alertStatusId);
		                   			smsDetailsVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(mobileNOArr[i]));
		                   			smsDetailsVO.setAlertId(alertId);
		                   			smsDetailsVO.setSmsText(message);
		                   			smsDetailsVO.setActionTypeId(actionTypeId);
		                   			saveGovtOfficerSmsDetails(smsDetailsVO);
                				}
                			}
                		 }
                			
                			if(callerName != null && !callerName.isEmpty() && callerName.length()>0 && 
                					 alertStatusId != null && alertStatusId.longValue() == 12L && actionTypeId != null && actionTypeId.longValue()== 6L){//alert closed, 6 - status change 
                				smsText = govtSmsActionTypeDAO.getSMSTextforActionTypeId(actionTypeId,1L,1L,3L);//3 Alert Caller
                    			
                    			if(commonMethodsUtilService.isListOrSetValid(smsText)){
                    				message = smsText.get(0) != null ? smsText.get(0).toString():message;
                    				message=message.replace("flag0", callerName);
                    				message=message.replace("flag1", status+"\n");
                    				message=message.replace("flag2", objArr[0].toString()+"\n");
                    				message=message.replace("flag3", comment+"\n");
                    			}
                				
                    			if(tempSMSAlert.getDescription() != null && !tempSMSAlert.getDescription().isEmpty())
                    				callerMobileNo=tempSMSAlert.getDescription().trim();
                    			
                    			if(callerMobileNo != null && callerMobileNo.trim().length()>0 && message != null && !message.isEmpty() && message.length()>0)
                    				govtSMSAPIService.senedSMSForGovtAlert(callerMobileNo,message);
                    			
                				GovtOfficerSmsDetailsVO smsDetailsVO =new GovtOfficerSmsDetailsVO();
	              				smsDetailsVO.setUserId(mainUserId);
	                   			smsDetailsVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(callerMobileNo));
	                   			smsDetailsVO.setAlertStatusId(alertStatusId);
	                   			smsDetailsVO.setAlertId(alertId);
	                   			smsDetailsVO.setSmsText(message);
	                   			smsDetailsVO.setActionTypeId(actionTypeId);
	                   			saveGovtOfficerSmsDetails(smsDetailsVO);
                			}
           			}
           		}
           		
           		
           		rs.setExceptionMsg("success");
           	} catch (Exception e) {
           		rs.setExceptionMsg("failure");
           		LOG.error("Error occured sendSMSTOAlertAssignedOfficer() method of AlertManagementSystemService{}");
           	}
           	return rs;
           }    
	 	 
	 	public ResultStatus saveGovtOfficerSmsDetails(final GovtOfficerSmsDetailsVO smsDetailsVO){
			
			final ResultStatus rs = new ResultStatus();
			try {
				
			        	//Date currentDateAndTime  = dateUtilService.getCurrentDateAndTime();
			        	AlertGovtOfficerSmsDetails smsDetails =new AlertGovtOfficerSmsDetails();
			        	if(smsDetailsVO.getUserId() != null){
			        		smsDetails.setUserId(smsDetailsVO.getUserId());
			        	}
						if(smsDetailsVO.getGovtOfficerId() != null){
							smsDetails.setGovtOfficerId(smsDetailsVO.getGovtOfficerId());
						}
						if(smsDetailsVO.getMobileNo() != null){
							smsDetails.setMobileNo(smsDetailsVO.getMobileNo());
						}
						if(smsDetailsVO.getAlertId() != null){
							smsDetails.setAlertId(smsDetailsVO.getAlertId());
						}
						if(smsDetailsVO.getAlertStatusId() != null){
							smsDetails.setAlertStatusId(smsDetailsVO.getAlertStatusId());
						}
						if(smsDetailsVO.getSmsText() != null){
							smsDetails.setSmsText(smsDetailsVO.getSmsText());
						}
						
						smsDetails.setInsertTime(dateUtilService.getCurrentDateAndTime());
						
						if(smsDetailsVO.getActionTypeId() != null){
							smsDetails.setGovtAlertActionTypeId(smsDetailsVO.getActionTypeId());
						}
						if(smsDetailsVO.getGovtSubTaskId() != null){
							smsDetails.setGovtAlertSubTaskId(smsDetailsVO.getGovtSubTaskId());
						}
			        	smsDetails = alertGovtOfficerSmsDetailsDAO.save(smsDetails);
					
				rs.setExceptionMsg("success");
				rs.setResultCode(0);
				
			} catch (Exception e) {
				rs.setExceptionMsg("failure");
				rs.setResultCode(1);
				LOG.error("Exception raised at saveGovtOfficerSmsDetails() in AlertService class ", e);
			}
			return rs;
			
			
			 
	      }
	 	
	 	public UserAddress saveUserAddressForMeekosamGrievanceAlert(final GrievanceAlertVO inputVO)
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
					//userAddress.setConstituency(constituencyDAO.get(inputVO.getConstituencyId()));
					
					if(inputVO.getLocationLevelId() ==  5l){
						List<Constituency> list = boothDAO.getConstituencyIdByTehsilId(inputVO.getMandalId());
						if(list != null && !list.isEmpty())
							userAddress.setConstituency(list.get(0));
						userAddress.setTehsil(tehsilDAO.get(inputVO.getMandalId()));
					}
					else{
						List<Constituency> list = boothDAO.getConstituencyIdByLebId(inputVO.getMandalId());
						if(list != null && !list.isEmpty())
							userAddress.setConstituency(list.get(0));
						userAddress.setLocalElectionBody(localElectionBodyDAO.get(inputVO.getMandalId()));	
					}
				}
				
				else if(inputVO.getLocationLevelId().longValue() == 6l || inputVO.getLocationLevelId().longValue() == 12l)
				{
					userAddress.setState(stateDAO.get(inputVO.getStateId()));
					userAddress.setDistrict(districtDAO.get(inputVO.getDistrictId()));
					//userAddress.setConstituency(constituencyDAO.get(inputVO.getConstituencyId()));
					
					if(inputVO.getLocationLevelId() ==  6l)
					{
						List<Constituency> list = boothDAO.getConstituencyIdByTehsilId(inputVO.getMandalId());
						if(list != null && !list.isEmpty())
							userAddress.setConstituency(list.get(0));
						userAddress.setTehsil(tehsilDAO.get(inputVO.getMandalId()));
						userAddress.setPanchayatId(inputVO.getPanchayatId());
					}
					else
					{
						List<Constituency> list = boothDAO.getConstituencyIdByLebId(inputVO.getMandalId());
						if(list != null && !list.isEmpty())
							userAddress.setConstituency(list.get(0));
						userAddress.setLocalElectionBody(localElectionBodyDAO.get(inputVO.getMandalId()));	
						//userAddress.setWard(constituencyDAO.get(inputVO.getPanchayatId()));
						userAddress.setUrbanLocalityId(inputVO.getPanchayatId());
					}
				}
				else if(inputVO.getLocationLevelId().toString().equalsIgnoreCase("11"))
				{
					userAddress.setState(stateDAO.get(inputVO.getStateId()));
					userAddress.setDistrict(districtDAO.get(inputVO.getDistrictId()));
					//userAddress.setConstituency(constituencyDAO.get(inputVO.getConstituencyId()));
					List<Constituency> list = boothDAO.getConstituencyIdByTehsilId(inputVO.getMandalId());
					if(list != null && !list.isEmpty())
						userAddress.setConstituency(list.get(0));
					userAddress.setTehsil(tehsilDAO.get(inputVO.getMandalId()));
					userAddress.setPanchayatId(inputVO.getPanchayatId());
					userAddress.setHamlet(hamletDAO.get(inputVO.getHamletId()));
				}
				else if(inputVO.getLocationLevelId().toString().equalsIgnoreCase("13"))
				{
					userAddress.setState(stateDAO.get(inputVO.getStateId()));
					userAddress.setDistrict(districtDAO.get(inputVO.getDistrictId()));
					//userAddress.setConstituency(constituencyDAO.get(inputVO.getConstituencyId()));
					List<Constituency> list = boothDAO.getConstituencyIdByLebId(inputVO.getMandalId());
					if(list != null && !list.isEmpty())
						userAddress.setConstituency(list.get(0));
					userAddress.setLocalElectionBody(localElectionBodyDAO.get(inputVO.getMandalId()));
					userAddress.setUrbanLocalityId(inputVO.getPanchayatId());
					userAddress.setUrbanBlockId(inputVO.getHamletId());
					//userAddress.setTehsil(tehsilDAO.get(inputVO.getMandalId()));
					//userAddress.setPanchayatId(inputVO.getPanchayatId());
					//userAddress.setHamlet(hamletDAO.get(inputVO.getHamletId()));
				}
				
				userAddress = userAddressDAO.save(userAddress);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return userAddress;
		}
	 	
	 	public KeyValueVO getRegionScopeValuesForUser(Long userId,Long deptId){
	 		KeyValueVO returnvo = new KeyValueVO();
	 		try {
				List<Object[]> list = govtAlertDepartmentLocationNewDAO.getRegionScopeValuesForUser(userId,deptId);
				if(list != null && !list.isEmpty()){
					for (Object[] obj : list) {
						Long scopeId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						Long scopeValue = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						returnvo.setId(scopeId);
						returnvo.setTotalCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
						returnvo.setScopeValue(Long.valueOf(obj[3] != null ? obj[3].toString():"0"));
						if(scopeId == 3l)
							returnvo.setCount(scopeValue);
						else if(scopeId == 5l)
							returnvo.setCount(tehsilDAO.get(scopeValue).getDistrict().getDistrictId());
						else if(scopeId == 7l)
							returnvo.setCount(localElectionBodyDAO.get(scopeValue).getDistrict().getDistrictId());
					}
				}
			} catch (Exception e) {
				LOG.error("Exception raised at getRegionScopeValuesForUser() in AlertService class ", e);
			}
	 		return returnvo;
	 	}

	 	void prepareStateLevelReopenAlertsForOfficer(AlertOverviewVO finalVO,List<Object[]> reopeObjLst){
	 		try{
				if(reopeObjLst != null && reopeObjLst.size() > 0){
					finalVO.setReopenCountForOfficer(commonMethodsUtilService.getLongValueForObject(reopeObjLst.get(0)[2]));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
	 	}
	 	public void pushReopenCountForOfficer(List<AlertOverviewVO> finalList,List<Object[]> reopenList){
			try{
				Map<Long,Long> categoryIdAndReopenCountMap = new HashMap<Long,Long>();
				if(reopenList != null && reopenList.size() > 0){
					for(Object[] param : reopenList){
						categoryIdAndReopenCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]	), commonMethodsUtilService.getLongValueForObject(param[2]));
					}
				}
				for(AlertOverviewVO param : finalList){
					if(categoryIdAndReopenCountMap != null && categoryIdAndReopenCountMap.get(param.getId()) != null){
						param.setReopenCountForOfficer(categoryIdAndReopenCountMap.get(param.getId()));
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	 	public void prepareLocationWiseReopenAlert(List<AlertOverviewVO> finalList,List<Object[]> reopeObjLst,List<Object[]> reopeObjLstForOfficer){
	 		try{
	 			if(finalList != null && finalList.size() > 0){
	 				for(AlertOverviewVO param : finalList){
	 					buildTemplateForReopenDtls(param);
	 				}
	 			}
	 			//location id and call center reopen count.
	 			Map<Long,Long> locAndCallcenterCountMap = new HashMap<Long,Long>();
	 			Long callCenterTotal = 0L;
	 			if(reopeObjLst != null && reopeObjLst.size() > 0){
	 				for(Object[] param : reopeObjLst){
	 					locAndCallcenterCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[2]));
	 					callCenterTotal = callCenterTotal+commonMethodsUtilService.getLongValueForObject(param[2]);
	 				}
	 			}
	 			//location id and officer reopen count.
	 			Map<Long,Long> locAndOfficerCountMap = new HashMap<Long,Long>();
	 			Long officerTotal = 0L;
	 			if(reopeObjLstForOfficer != null && reopeObjLstForOfficer.size() > 0){
	 				for(Object[] param : reopeObjLstForOfficer){
	 					locAndOfficerCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[2]));
	 					officerTotal = officerTotal+commonMethodsUtilService.getLongValueForObject(param[2]);
	 				}
	 			}
	 			if(finalList != null && finalList.size() > 0){
	 				for(AlertOverviewVO param : finalList){
	 					if(param.getSubList() != null && param.getSubList().size() > 0){
	 						for(AlertOverviewVO param2 : param.getSubList()){
	 							if(param2.getId().longValue() == 1L){
	 								param2.setTotalAlertCnt(commonMethodsUtilService.getLongValueForObject(locAndCallcenterCountMap.get(param.getId())));
	 							}
	 							if(param2.getId().longValue() == 2L){
	 								param2.setTotalAlertCnt(commonMethodsUtilService.getLongValueForObject(locAndOfficerCountMap.get(param.getId())));
	 							}
	 							if(param2.getId().longValue() == 3L){
	 								param2.setTotalAlertCnt(commonMethodsUtilService.getLongValueForObject(locAndCallcenterCountMap.get(param.getId()))+commonMethodsUtilService.getLongValueForObject(locAndOfficerCountMap.get(param.getId())));
	 							}
	 						}
	 					}
	 				}
	 			}
	 			//push grand total for reopen
	 			if(finalList != null && finalList.size() > 0){
	 				finalList.get(0).getSubList().get(0).setGrandTotal(callCenterTotal);
	 				finalList.get(0).getSubList().get(1).setGrandTotal(officerTotal);
	 				finalList.get(0).getSubList().get(2).setGrandTotal(callCenterTotal+officerTotal);
	 			}
	 			
	 		}catch(Exception e){
	 			e.printStackTrace();
	 		}
	 	}
	 	public void buildTemplateForReopenDtls(AlertOverviewVO alertOverviewVO){
	 		try{
	 			List<AlertOverviewVO> alertOverviewVOs = new ArrayList<AlertOverviewVO>();
	 			AlertOverviewVO alertVO = null;
	 			alertVO = new AlertOverviewVO();
	 			alertVO.setId(1L);
	 			alertVO.setType("reopenCallcenter");
	 			alertVO.setName("Reopen By Call Center");
	 			alertOverviewVOs.add(alertVO);
	 			alertVO = new AlertOverviewVO();
	 			alertVO.setId(2L);
	 			alertVO.setType("reopenOffecer");
	 			alertVO.setName("Reopen By Officer");
	 			alertOverviewVOs.add(alertVO);
	 			alertVO = new AlertOverviewVO();
	 			alertVO.setId(3L);
	 			alertVO.setType("reopenOverall");
	 			alertVO.setName("Reopen By Overall");
	 			alertOverviewVOs.add(alertVO);
	 			alertOverviewVO.getSubList().addAll(alertOverviewVOs);
	 		}catch(Exception e){
	 			e.printStackTrace();
	 		}
	 	}
	 	public void prepareDateWiseReopenAlert(List<AlertOverviewVO> finalList,List<Object[]> reopeObjLst,List<Object[]> reopeObjLstForOfficer){
	 		try{
	 			if(finalList != null && finalList.size() > 0){
	 				for(AlertOverviewVO param : finalList){
	 					buildTemplateForReopenDtls(param);
	 				}
	 			}
	 			//date and call center reopen count.
	 			Map<String,Long> dateAndCallcenterCountMap = new HashMap<String,Long>();
	 			Long callCenterTotal = 0L;
	 			if(reopeObjLst != null && reopeObjLst.size() > 0){
	 				for(Object[] param : reopeObjLst){
	 					dateAndCallcenterCountMap.put(commonMethodsUtilService.getStringValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
	 					callCenterTotal = callCenterTotal+commonMethodsUtilService.getLongValueForObject(param[1]);
	 				}
	 			}
	 			//date and officer reopen count.
	 			Map<String,Long> dateAndOfficerCountMap = new HashMap<String,Long>();
	 			Long officerTotal = 0L;
	 			if(reopeObjLstForOfficer != null && reopeObjLstForOfficer.size() > 0){
	 				for(Object[] param : reopeObjLstForOfficer){
	 					dateAndOfficerCountMap.put(commonMethodsUtilService.getStringValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
	 					officerTotal = officerTotal+commonMethodsUtilService.getLongValueForObject(param[1]);
	 				}
	 			}
	 			if(finalList != null && finalList.size() > 0){
	 				for(AlertOverviewVO param : finalList){
	 					if(param.getSubList() != null && param.getSubList().size() > 0){
	 						for(AlertOverviewVO param2 : param.getSubList()){
	 							if(param2.getId().longValue() == 1L){
	 								param2.setTotalAlertCnt(commonMethodsUtilService.getLongValueForObject(dateAndCallcenterCountMap.get(param.getDay())));
	 							}
	 							if(param2.getId().longValue() == 2L){
	 								param2.setTotalAlertCnt(commonMethodsUtilService.getLongValueForObject(dateAndOfficerCountMap.get(param.getDay())));
	 							}
	 							if(param2.getId().longValue() == 3L){
	 								param2.setTotalAlertCnt(commonMethodsUtilService.getLongValueForObject(dateAndCallcenterCountMap.get(param.getDay()))+commonMethodsUtilService.getLongValueForObject(dateAndOfficerCountMap.get(param.getDay())));
	 							}
	 						}
	 					}
	 				}
	 			}
	 			//push grand total for reopen
	 			if(finalList != null && finalList.size() > 0){
	 				finalList.get(0).getSubList().get(0).setGrandTotal(callCenterTotal);
	 				finalList.get(0).getSubList().get(1).setGrandTotal(officerTotal);
	 				finalList.get(0).getSubList().get(2).setGrandTotal(callCenterTotal+officerTotal);
	 			}
	 		}catch(Exception e){
	 			e.printStackTrace();
	 		}
	 	}
	 	public void prepareWeekAndMonthWiseReopenAlert(List<AlertOverviewVO> finalList,List<Object[]> reopeObjLst,List<Object[]> reopeObjLstForOfficer,String rangeType,Date fromDate,Date toDate){
	 		try{
	 			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	 			LinkedHashMap<String,List<String>> weekAndDaysMap = null;
	 			if(finalList != null && finalList.size() > 0){
	 				for(AlertOverviewVO param : finalList){
	 					buildTemplateForReopenDtls(param);
	 				}
	 			}
   			  	if(rangeType.equalsIgnoreCase("week")){
   			  		weekAndDaysMap = DateUtilService.getTotalWeeksMap(fromDate, toDate);
   			  	}else if(rangeType.equalsIgnoreCase("month")){
   			  		weekAndDaysMap = getMonthWeekAndDaysList(sdf.format(fromDate), sdf.format(toDate),"month");
   			  	}
   			  	//date and call center reopen count.
	 			Map<String,Long> dateAndCallcenterCountMap = new HashMap<String,Long>();
	 			Long callCenterTotal = 0L;
	 			if(reopeObjLst != null && reopeObjLst.size() > 0){
	 				for(Object[] param : reopeObjLst){
	 					dateAndCallcenterCountMap.put(commonMethodsUtilService.getStringValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
	 					callCenterTotal = callCenterTotal+commonMethodsUtilService.getLongValueForObject(param[1]);
	 				}
	 			}
	 			//date and officer reopen count.
	 			Map<String,Long> dateAndOfficerCountMap = new HashMap<String,Long>();
	 			Long officerTotal = 0L;
	 			if(reopeObjLstForOfficer != null && reopeObjLstForOfficer.size() > 0){
	 				for(Object[] param : reopeObjLstForOfficer){
	 					dateAndOfficerCountMap.put(commonMethodsUtilService.getStringValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
	 					officerTotal = officerTotal+commonMethodsUtilService.getLongValueForObject(param[1]);
	 				}
	 			}
   			  	//create a map for week or month name and total callcenter reopen count.
   			  	Map<String,Long> weekAndTotalCallcenterCountMap = new HashMap<String,Long>();
   			  	if(weekAndDaysMap != null && weekAndDaysMap.size() > 0){
   			  		for(Entry<String,List<String>> entry : weekAndDaysMap.entrySet()){
   			  			weekAndTotalCallcenterCountMap.put(entry.getKey(), 0L);
   			  			if(entry.getValue() != null && entry.getValue().size() > 0){
   			  				for(String param : entry.getValue()){
   			  					weekAndTotalCallcenterCountMap.put(entry.getKey(), weekAndTotalCallcenterCountMap.get(entry.getKey())+commonMethodsUtilService.getLongValueForObject(dateAndCallcenterCountMap.get(param)));
   			  				}	
   			  			}
   			  		}
   			  	}
   			  	
   			  	//create a map for week or month name and total officer reopen count.
   			  	Map<String,Long> weekAndTotalOfficerCountMap = new HashMap<String,Long>();
   			  	if(weekAndDaysMap != null && weekAndDaysMap.size() > 0){
   			  		for(Entry<String,List<String>> entry : weekAndDaysMap.entrySet()){
   			  		weekAndTotalOfficerCountMap.put(entry.getKey(), 0L);
   			  			if(entry.getValue() != null && entry.getValue().size() > 0){
   			  				for(String param : entry.getValue()){
   			  					weekAndTotalOfficerCountMap.put(entry.getKey(), weekAndTotalOfficerCountMap.get(entry.getKey())+commonMethodsUtilService.getLongValueForObject(dateAndOfficerCountMap.get(param)));
   			  				}	
   			  			}
   			  		}
   			  	}
   			  	if(finalList != null && finalList.size() > 0){
	 				for(AlertOverviewVO param : finalList){
	 					if(param.getSubList() != null && param.getSubList().size() > 0){
	 						for(AlertOverviewVO param2 : param.getSubList()){
	 							if(param2.getId().longValue() == 1L){
	 								param2.setTotalAlertCnt(commonMethodsUtilService.getLongValueForObject(weekAndTotalCallcenterCountMap.get(param.getDay())));
	 							}
	 							if(param2.getId().longValue() == 2L){
	 								param2.setTotalAlertCnt(commonMethodsUtilService.getLongValueForObject(weekAndTotalOfficerCountMap.get(param.getDay())));
	 							}
	 							if(param2.getId().longValue() == 3L){
	 								param2.setTotalAlertCnt(commonMethodsUtilService.getLongValueForObject(weekAndTotalCallcenterCountMap.get(param.getDay()))+commonMethodsUtilService.getLongValueForObject(weekAndTotalOfficerCountMap.get(param.getDay())));
	 							}
	 						}
	 					}
	 				}
	 			}
   			  	//push grand total for reopen
	 			if(finalList != null && finalList.size() > 0){
	 				finalList.get(0).getSubList().get(0).setGrandTotal(callCenterTotal);
	 				finalList.get(0).getSubList().get(1).setGrandTotal(officerTotal);
	 				finalList.get(0).getSubList().get(2).setGrandTotal(callCenterTotal+officerTotal);
	 			}
	 		}catch(Exception e){
	 			e.printStackTrace();
	 		}
	 	}
	public List<AlertCoreDashBoardVO> getReopenCountDtls(String fromDateStr,String toDateStr, Long stateId, Long departmentId,Long sourceId, String groupType,Long reopenType, Long locationId){
	try{
		List<AlertCoreDashBoardVO> dtlsList = new ArrayList<AlertCoreDashBoardVO>();
		Date fromDate = null;        
		Date toDate = null; 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
			fromDate = sdf.parse(fromDateStr);
			toDate = sdf.parse(toDateStr);
		}
		List<Long> alertIdList = alertDAO.getReopenCountDtls(fromDate, toDate, stateId, departmentId, sourceId, groupType,reopenType,locationId);
		List<Object[]> altDtlsList = alertDAO.getAlertDtlsForGrievance(alertIdList);
		setAlertDtls(dtlsList,altDtlsList);
		return dtlsList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getGrievanceReportBasedOnLocationAndStatus() method of AlertService{}");
		}
		return null;  
	}
	public List<AlertCoreDashBoardVO> getTotalAlertGroupByStatusForStateLvlGrievancePage(String fromDateStr,String toDateStr, Long stateId, Long departmentId,Long sourceId, Long statusId,String level){
		try{
			List<AlertCoreDashBoardVO> dtlsList = new ArrayList<AlertCoreDashBoardVO>();
			Date fromDate = null;        
			Date toDate = null; 
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Long> alertIdList = alertDAO.getTotalAlertGroupByStatusForStateLvlGrievancePage(fromDate, toDate, stateId, sourceId,departmentId, statusId,level);
			List<Object[]> altDtlsList = alertDAO.getAlertDtlsForGrievance(alertIdList);
			setAlertDtls(dtlsList,altDtlsList);
			return dtlsList;
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Error occured getGrievanceReportBasedOnLocationAndStatus() method of AlertService{}");
			}
			return null;  
		}
	/*
	 * Author:Santosh
	 */
	public AlertOverviewVO getAlertCntInRequiredFormatToExportToExcel(String fromDateStr, String toDateStr, Long stateId,Long departmentId, Long sourceId, String rangeType){
		LOG.info("Entered in getAlertDetailsInRequiredFormatToExportToExcel() method of AlertService{}");
		try{  
			AlertOverviewVO resultVO = new AlertOverviewVO();
			Date fromDate = null;        
			Date toDate = null; 
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			
			}
			 Set<Long> alertStatusIds = new HashSet<Long>();
			 List<Object[]> statuobjList = alertDepartmentStatusDAO.getRequiredAlertGovtDepartmentStatus();
			 setAlrtSttsIds(statuobjList,alertStatusIds);
			 
			//getting overall alert
			 AlertOverviewVO overAllVO = new AlertOverviewVO();
			List<Object[]> categoryAlertObjList = alertDAO.getStatusWiseLocationAlert(fromDate, toDate, stateId, departmentId, sourceId, "Category", "",alertStatusIds);
			List<Object[]> stateAlertObjList = alertDAO.getStatusWiseLocationAlert(fromDate, toDate, stateId, departmentId, sourceId, "State", "",alertStatusIds);
			List<Object[]> districtAlertObjList = alertDAO.getStatusWiseLocationAlert(fromDate, toDate, stateId, departmentId, sourceId, "District", "",alertStatusIds);
		    
			setOverAllAlertData(categoryAlertObjList,overAllVO,statuobjList,"category");
		    setOverAllAlertData(stateAlertObjList,overAllVO,statuobjList,"state");
		    setOverAllAlertData(districtAlertObjList,overAllVO,statuobjList,"district");
		   
			//gettin day wise  alert
			Map<Long,AlertOverviewVO> categroyMap = new LinkedHashMap<Long, AlertOverviewVO>();
			Map<Long,AlertOverviewVO> stateMap = new LinkedHashMap<Long, AlertOverviewVO>();
			Map<Long,AlertOverviewVO>  districtMap = new LinkedHashMap<Long, AlertOverviewVO>();
			Map<String,Map<Long,Map<Long,Long>>> dateWiseCategoryAlertCntMap = new HashMap<String, Map<Long,Map<Long,Long>>>();
			Map<String,Map<Long,Map<Long,Long>>> stateWiseCategoryAlertCntMap = new HashMap<String, Map<Long,Map<Long,Long>>>();
			Map<String,Map<Long,Map<Long,Long>>> districtWiseCategoryAlertCntMap = new HashMap<String, Map<Long,Map<Long,Long>>>();
			
		    List<Object[]> daysCategoryAlertObjList = alertDAO.getStatusWiseLocationAlert(fromDate, toDate, stateId, departmentId, sourceId, "Category", "Day",alertStatusIds);
			List<Object[]> daysStateAlertObjList = alertDAO.getStatusWiseLocationAlert(fromDate, toDate, stateId, departmentId, sourceId, "State", "Day",alertStatusIds);
			List<Object[]> daysDistrictAlertObjList = alertDAO.getStatusWiseLocationAlert(fromDate, toDate, stateId, departmentId, sourceId, "District", "Day",alertStatusIds);
			 
			prepareResultDateWise(daysCategoryAlertObjList,categroyMap,dateWiseCategoryAlertCntMap);
			prepareResultDateWise(daysStateAlertObjList,stateMap,stateWiseCategoryAlertCntMap);
			prepareResultDateWise(daysDistrictAlertObjList,districtMap,districtWiseCategoryAlertCntMap);
			
			Map<String,List<String>> weekAndDaysMap = new LinkedHashMap<String, List<String>>();
			Map<String,AlertOverviewVO> dateWiseMap = new LinkedHashMap<String, AlertOverviewVO>();
			
			String type="Day";
			if(rangeType != null && rangeType.equalsIgnoreCase("Day")){
			  List<String> dayList = DateUtilService.getDaysBetweenDatesStringFormat(fromDate, toDate);	
			  weekAndDaysMap.put("Days", dayList);
			}else if(rangeType.equalsIgnoreCase("month") || rangeType.equalsIgnoreCase("week")){
				if(rangeType.equalsIgnoreCase("week")){
					weekAndDaysMap = DateUtilService.getTotalWeeksMap(fromDate, toDate);
				}else if(rangeType.equalsIgnoreCase("month")){
					weekAndDaysMap = getMonthWeekAndDaysList(sdf.format(fromDate), sdf.format(toDate),"month");
	    		}
				type = "weekMonth";
			}
			//prepare result
			prepareResultInRequiredFormatBasedOnDate(dateWiseMap,weekAndDaysMap,categroyMap,stateMap,districtMap,statuobjList,type);
			setDateWiseData(dateWiseMap,dateWiseCategoryAlertCntMap,stateWiseCategoryAlertCntMap,districtWiseCategoryAlertCntMap);
			caculateDayWiseGrandTotalAndPercentage(dateWiseMap);
			
			//setting date wise result to final list
			if(dateWiseMap != null && dateWiseMap.size() > 0){
				resultVO.getSubList().addAll(dateWiseMap.values());
				dateWiseMap.clear();
			}
			//Seeting overall result to resultvo
			resultVO.setOverAllVO(overAllVO);
			//generateExcelReport(resultVO);
			return resultVO;  
	  }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getAlertDetailsInRequiredFormatToExportToExcel() method of AlertService{}");
	  }
	 return null;
	}   
	
	public void prepareResultDateWise(List<Object[]> objList,Map<Long,AlertOverviewVO> locationMap,Map<String,Map<Long,Map<Long,Long>>> dateWiseAlertCntMap){
		try{
			if(objList != null && objList.size() > 0){
				for(Object[] param:objList){
					if(param[1] != null){
						AlertOverviewVO locationVO = locationMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
						 if(locationVO == null){
							 locationVO = new AlertOverviewVO();
							 locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
							 locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
							 locationMap.put(locationVO.getId(), locationVO);
						 }
						 Map<Long,Map<Long,Long>> lctnMap = dateWiseAlertCntMap.get(commonMethodsUtilService.getStringValueForObject(param[0]));
						 if(lctnMap == null){
							 lctnMap = new HashMap<Long, Map<Long,Long>>();
							 dateWiseAlertCntMap.put(commonMethodsUtilService.getStringValueForObject(param[0]), lctnMap);
						 }
						 Map<Long,Long> statusMap = lctnMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
						  if(statusMap == null){
							  statusMap = new HashMap<Long, Long>();
							  lctnMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), statusMap);
						  }
						  statusMap.put(commonMethodsUtilService.getLongValueForObject(param[3]),commonMethodsUtilService.getLongValueForObject(param[5]));
					 }	
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured prepareResultDateWise() method of AlertService{}");
		}
	}
	public void prepareResultInRequiredFormatBasedOnDate(Map<String,AlertOverviewVO> tempMap,Map<String,List<String>> weekAndDaysMap,Map<Long,AlertOverviewVO> categoryMap,Map<Long,AlertOverviewVO> stateMap,Map<Long,AlertOverviewVO> districtMap,List<Object[]> statuobjList,String type){
		try{
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		    SimpleDateFormat sdf3 = new SimpleDateFormat("MMM-dd-yyyy");
			Date fromDate = null;
			Date toDate = null;
			String fromDateStr = null;
			String toDateStr = null;
			
	        if(weekAndDaysMap != null && weekAndDaysMap.size() > 0){
            	for(Entry<String,List<String>> dateEntry:weekAndDaysMap.entrySet()){
            		if(type != null && type.equalsIgnoreCase("Day")){
            			if(dateEntry.getValue() != null && dateEntry.getValue().size() > 0){
            				 for(String date:dateEntry.getValue()){
            					 AlertOverviewVO dateVO = new AlertOverviewVO();
            					 
            					 fromDate = sdf2.parse(date);
        						 toDate = sdf2.parse(date);
        						 fromDateStr = sdf.format(fromDate);
     							 toDateStr = sdf.format(toDate);
     							 dateVO.setFromDateStr(fromDateStr);
     					    	 dateVO.setToDateStr(toDateStr);
     					    	
        						 dateVO.setHeading(sdf3.format(fromDate) + " Report");
            					 
        						 dateVO.setDay(date);
            					 dateVO.getList().add(date);
            					 dateVO.getSubList().addAll(getRequiredList(categoryMap,statuobjList));
            					 dateVO.setSubList1(getRequiredList(stateMap,statuobjList));
            					 dateVO.setSubList2(getRequiredList(districtMap,statuobjList));
            					 tempMap.put(date, dateVO);
            				 }
            			}
            		}else{
            			 AlertOverviewVO dateVO = new AlertOverviewVO();
    					 dateVO.setDay(dateEntry.getKey());
    					 
    					 dateVO.setHeading(dateEntry.getKey() + " Report");
    					 
    					 dateVO.getList().addAll(dateEntry.getValue());
    					 dateVO.getSubList().addAll(getRequiredList(categoryMap,statuobjList));
    					 dateVO.setSubList1(getRequiredList(stateMap,statuobjList));
    					 dateVO.setSubList2(getRequiredList(districtMap,statuobjList));
    					 //Sending start and end date fo week or month
				    	if(dateVO.getList() != null && dateVO.getList().size() > 1){
							int len = dateVO.getList().size();
							fromDate = sdf2.parse(dateVO.getList().get(0).trim());
							toDate = sdf2.parse(dateVO.getList().get(len-1).trim());
							fromDateStr = sdf.format(fromDate);
							toDateStr = sdf.format(toDate);
							
						}else{
							fromDate = sdf2.parse(dateVO.getList().get(0).trim());
							toDate = sdf2.parse(dateVO.getList().get(0).trim());
							fromDateStr = sdf.format(fromDate);
							toDateStr = sdf.format(toDate);
						}
				    	dateVO.setFromDateStr(fromDateStr);
				    	dateVO.setToDateStr(toDateStr);
				    	
    			    	tempMap.put(dateEntry.getKey(), dateVO);
    	    		}
            	}
            }
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getAlertDetailsInRequiredFormatToExportToExcel() method of AlertService{}");
		}
	}
	public List<AlertOverviewVO> getRequiredList(Map<Long,AlertOverviewVO> map,List<Object[]> statuobjList){
		List<AlertOverviewVO> resultList = new ArrayList<AlertOverviewVO>();
		try{
		  if(map != null && map.size() > 0){
			  for(Entry<Long,AlertOverviewVO> entry:map.entrySet()){
				  AlertOverviewVO locationVO = new AlertOverviewVO();
					 locationVO.setId(entry.getValue().getId());
					 locationVO.setName(entry.getValue().getName()); 
					 locationVO.getSubList().addAll(prepareRequiredTemplate(statuobjList));
					 resultList.add(locationVO);
			  }
		  }
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getAlertDetailsInRequiredFormatToExportToExcel() method of AlertService{}");
		}
		return resultList;
	}
	public void setDateWiseData(Map<String,AlertOverviewVO> dateWiseMap,Map<String,Map<Long,Map<Long,Long>>> dateWiseCategoryAlertCntMap,Map<String,Map<Long,Map<Long,Long>>> stateWiseCategoryAlertCntMap,Map<String,Map<Long,Map<Long,Long>>> districtWiseCategoryAlertCntMap){
		try{
			 if(dateWiseMap != null && dateWiseMap.size() > 0){
				 for(Entry<String,AlertOverviewVO> entry:dateWiseMap.entrySet()){
					 if(entry != null && entry.getValue().getList() != null && entry.getValue().getList().size() > 0){
						 for(String date:entry.getValue().getList()){
							 Map<Long,Map<Long,Long>> categoryMap = dateWiseCategoryAlertCntMap.get(date);
							 Map<Long,Map<Long,Long>> stateMap = stateWiseCategoryAlertCntMap.get(date);
							 Map<Long,Map<Long,Long>> districtMap = districtWiseCategoryAlertCntMap.get(date);
							 setRequiredDateBasedStatus(entry.getValue().getSubList(),categoryMap);//Setting category wise data
							 setRequiredDateBasedStatus(entry.getValue().getSubList1(),stateMap);//Setting state wise data
							 setRequiredDateBasedStatus(entry.getValue().getSubList2(),districtMap);//Setting distrct wise data
						 }
					 }
					}
			 }
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setDateWiseData() method of AlertService{}");
		}
	}
	public void setRequiredDateBasedStatus(List<AlertOverviewVO> subList,Map<Long,Map<Long,Long>> map){
		try{
			 if(subList != null && subList.size() > 0){
				 
				   for(AlertOverviewVO vo:subList){
					   
					   if(map != null){
						   
						   Map<Long,Long> statusMap = map.get(vo.getId());
						    
						    List<AlertOverviewVO> mainList = vo.getSubList();
						    
						    if(mainList != null && mainList.size() > 0){
						    	
						    	for(AlertOverviewVO mainVO:mainList){
						    		
						    		if(mainVO != null && mainVO.getSubList() != null && mainVO.getSubList().size() > 0){
						    			
						    		   for(AlertOverviewVO statusVO:mainVO.getSubList()){
						    			 
						    			   if(statusMap != null ){
						    				   
						    				   if(statusMap.get(statusVO.getStatusTypeId()) != null){
							    				   
									    			 statusVO.setAlertCnt(statusVO.getAlertCnt()+statusMap.get(statusVO.getStatusTypeId()));
									    		} 
						    			   }
						    			   
						    		   }
						    		}
						    	}
						    } 
					   }
					   
				   }
			   }
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setRequiredDateBasedStatus() method of AlertService{}");
		}
	}
	public void caculateDayWiseGrandTotalAndPercentage(Map<String,AlertOverviewVO> dateWiseMap){
		try{
			if(dateWiseMap != null && dateWiseMap.size() > 0){
				for(Entry<String,AlertOverviewVO> entry:dateWiseMap.entrySet()){
					entry.getValue().getList().clear();//Clearing temp date list which is used for calculation
					calculateDateWisePercentage(entry.getValue().getSubList());
					calculateDateWisePercentage(entry.getValue().getSubList1());
					calculateDateWisePercentage(entry.getValue().getSubList2());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured caculateDayWiseGrandTotalAndPercentage() method of AlertService{}");
		}
	}
	public void calculateDateWisePercentage(List<AlertOverviewVO> subList){
		try{
			Map<Long,Long> statusWiseTotalAlertCntMap = new HashMap<Long, Long>();
			Long totalAlertCnt = 0l;
			if(subList != null && subList.size() > 0){
				
				for(AlertOverviewVO VO:subList){//State or District or Category List
					
					if(VO.getSubList() != null && VO.getSubList().size() > 0){
						
						for(AlertOverviewVO mainVO:VO.getSubList()){
				    		
				    		if(subList != null && subList.size() > 0){
				    			
				    			 for(AlertOverviewVO statusVO:mainVO.getSubList()){
				    				 
				    					Long alertCnt = statusWiseTotalAlertCntMap.get(statusVO.getStatusTypeId());
				    					
							    		 if(alertCnt == null){
							    			 
							    			 statusWiseTotalAlertCntMap.put(statusVO.getStatusTypeId(),0l);
							    		 }
							    		 statusWiseTotalAlertCntMap.put(statusVO.getStatusTypeId(), statusWiseTotalAlertCntMap.get(statusVO.getStatusTypeId())+statusVO.getAlertCnt());
							    		 //overall alert cnt
							    		 totalAlertCnt = totalAlertCnt + statusVO.getAlertCnt();
							    		 //total alert for not started,initialstage,finish,...
							    		 mainVO.setAlertCnt(mainVO.getAlertCnt()+statusVO.getAlertCnt());
							    		 //Total AlertCnt category or state or district wise
							    		 VO.setAlertCnt(VO.getAlertCnt()+statusVO.getAlertCnt());
							   }
				    		}
						}
					}
				}
			}
			//Calculating Grand total
			settingGrandTotal(subList,totalAlertCnt,statusWiseTotalAlertCntMap);
			
	    }catch(Exception e){
			e.printStackTrace();
		}
	}
	public void setOverAllAlertData(List<Object[]> objList, AlertOverviewVO overAllVO,List<Object[]> statusObjList,String type){
		try{
			Map<Long,AlertOverviewVO> tempMap = new LinkedHashMap<Long, AlertOverviewVO>();
			if(objList != null && objList.size() > 0){
				for(Object[] param:objList){
					if(param[0] != null){
						AlertOverviewVO VO = tempMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						   if(VO == null){
							   VO = new AlertOverviewVO();
							   VO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
							   VO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
							   VO.getSubList().addAll(prepareRequiredTemplate(statusObjList));
							   tempMap.put(VO.getId(), VO);
						   }
						   Long statusId = commonMethodsUtilService.getLongValueForObject(param[2]);
						   Long alertCnt = commonMethodsUtilService.getLongValueForObject(param[4]);
						   List<AlertOverviewVO> statusList = VO.getSubList();
						   if(statusList != null && statusList.size() > 0){
						      for(AlertOverviewVO vo:statusList)	{
						    	  AlertOverviewVO matchVO = getMatchVO(vo.getSubList(),statusId);
						    	   if(matchVO != null){
						    		   matchVO.setAlertCnt(alertCnt);
						    	   }
						      }
						  }	
					}
				}
			}
			// Calculating grand total alert and percentage
			calulateGrandTotalAndPercentage(tempMap,overAllVO,type);
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setOverAllAlertData() method of AlertService{}");
		}
	}
	public void calulateGrandTotalAndPercentage(Map<Long,AlertOverviewVO> resultMap,AlertOverviewVO resultVO,String type){
		try{
			 Map<Long,Long> statusWiseTotalAlertCntMap = new HashMap<Long, Long>();
			 Long totalAlertCnt = 0l;
			 if(resultMap != null && resultMap.size() > 0){
				 
				 for(Entry<Long,AlertOverviewVO> entry:resultMap.entrySet()){
					 
					    if(entry.getValue().getSubList() != null && entry.getValue().getSubList().size()>0){
					    	
					    	for(AlertOverviewVO mainVO:entry.getValue().getSubList()){
					    		
					    		if(mainVO != null && mainVO.getSubList() != null && mainVO.getSubList().size() > 0){
					    			
					    			 for(AlertOverviewVO statusVO:mainVO.getSubList()){
					    				 
					    					Long alertCnt = statusWiseTotalAlertCntMap.get(statusVO.getStatusTypeId());
					    					
								    		 if(alertCnt == null){
								    			 
								    			 statusWiseTotalAlertCntMap.put(statusVO.getStatusTypeId(),0l);
								    		 }
								    		 statusWiseTotalAlertCntMap.put(statusVO.getStatusTypeId(), statusWiseTotalAlertCntMap.get(statusVO.getStatusTypeId())+statusVO.getAlertCnt());
								    		 //overall alert cnt
								    		 totalAlertCnt = totalAlertCnt + statusVO.getAlertCnt();
								    		 //total alert for not started,initialstage,finish,...
								    		 mainVO.setAlertCnt(mainVO.getAlertCnt()+statusVO.getAlertCnt());
								    		 //Total AlertCnt category or state or district wise
								    		 entry.getValue().setAlertCnt(entry.getValue().getAlertCnt()+statusVO.getAlertCnt());
								   }
					    		}
					    	}
					    }
				 }
			 }	
			 if(resultMap != null && resultMap.size() > 0){
				 if(type != null && type.equalsIgnoreCase("category")){
					 resultVO.getSubList().addAll(resultMap.values());
					 settingGrandTotal(resultVO.getSubList(),totalAlertCnt,statusWiseTotalAlertCntMap);
				 }else if(type.equalsIgnoreCase("state")){
					 resultVO.setSubList1(new ArrayList<AlertOverviewVO>(resultMap.values()));
					 settingGrandTotal(resultVO.getSubList1(),totalAlertCnt,statusWiseTotalAlertCntMap);
				 }else if(type.equalsIgnoreCase("district")){
					 resultVO.setSubList2(new ArrayList<AlertOverviewVO>(resultMap.values()));
					 settingGrandTotal(resultVO.getSubList2(),totalAlertCnt,statusWiseTotalAlertCntMap);
				 }
			 }
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured calulateGrandTotalAndPercentage() method of AlertService{}");
		}
	}
	public void settingGrandTotal(List<AlertOverviewVO> resultList,Long totalAlert,Map<Long,Long> statusWiseTotalAlertCntMap){
		try{
			//Setting grand total percentage
			if(resultList != null && resultList.size() > 0){
				AlertOverviewVO VO = resultList.get(0);
				VO.setGrandTotal(totalAlert);
				 List<AlertOverviewVO> mainList = VO.getSubList();
				    if(mainList != null && mainList.size() > 0){
				      for(AlertOverviewVO mainVO:mainList)	{
				    	  if(mainVO.getSubList() != null && mainVO.getSubList().size() > 0){
				    		  for(AlertOverviewVO statusVO:mainVO.getSubList()){
				    			  if(statusWiseTotalAlertCntMap.get(statusVO.getStatusTypeId()) != null){
				    			   	  statusVO.setGrandTotal(statusWiseTotalAlertCntMap.get(statusVO.getStatusTypeId()));
				    			   	  mainVO.setGrandTotal(mainVO.getGrandTotal()+statusVO.getGrandTotal());
							    	  statusVO.setPercentage(calculatePercantage(statusVO.getGrandTotal(), totalAlert));
								  }
				    		  }
				    	  }
				    	  mainVO.setGrandOverAllper(calculatePercantage(mainVO.getGrandTotal(),totalAlert));
				      }
				    }
			}
			// calculating percentage for notStarted,initialstage,finish,...
           if(resultList != null && resultList.size() > 0){
        	   for(AlertOverviewVO VO:resultList){
        		   if(VO.getSubList() != null && VO.getSubList().size() > 0){
        			   for(AlertOverviewVO mainVO:VO.getSubList()){
        				   if(mainVO.getSubList() != null && mainVO.getSubList().size() > 0){
        					   for(AlertOverviewVO statusVO:mainVO.getSubList()){
        						   statusVO.setOverAllPer(calculatePercantage(mainVO.getAlertCnt(), VO.getAlertCnt()));
        					   }
        				   }
        				   mainVO.setOverAllPer(calculatePercantage(mainVO.getAlertCnt(), VO.getAlertCnt()));
        			   }
        		   }
        	   }
           }
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured settingGrandTotal() method of AlertService{}");
		}
	}
	public List<AlertOverviewVO> prepareRequiredTemplate(List<Object[]> statuobjList){
		List<AlertOverviewVO> resultList = new ArrayList<AlertOverviewVO>();
		try{
			
			 AlertOverviewVO notStatusVO = new AlertOverviewVO();
			 notStatusVO.setStatusTypeId(1l);
			 notStatusVO.setStatusType("NOT STARTED");
			 
			 AlertOverviewVO initialStageVO = new AlertOverviewVO();
			 initialStageVO.setStatusTypeId(2l);
			 initialStageVO.setStatusType("INITIAL STAGE");
			 
			 AlertOverviewVO finishVO = new AlertOverviewVO();
			 finishVO.setStatusTypeId(3l);
			 finishVO.setStatusType("FINISH");
			 
			 AlertOverviewVO mvTOthrCtgryVO = new AlertOverviewVO();
			 mvTOthrCtgryVO.setStatusTypeId(4l);
			 mvTOthrCtgryVO.setStatusType("MOVED TO OTHER CATEGORY");
			 
			if(statuobjList != null && statuobjList.size() > 0){
				for(Object[] param:statuobjList){
					Long statusId = commonMethodsUtilService.getLongValueForObject(param[0]).longValue();
					if(statusId == 2l){// 2:Notify
						notStatusVO.getSubList().add(getStatusVO(param));
					}else if(statusId == 3l){// 3:Action in progress
						initialStageVO.getSubList().add(getStatusVO(param));
					}else if(statusId == 4l || statusId == 12l || statusId == 13l){// 4:completed 12:Closed 13:Proposal
						finishVO.getSubList().add(getStatusVO(param));
					}else if(statusId == 6l || statusId == 7l || statusId == 8l || statusId == 9l || statusId == 11){// 6:ActionNotRequired 7:Duplicate 8:Wrongly Mapped Designation 9:Wrongly Mapped Department 11:Reopen
						mvTOthrCtgryVO.getSubList().add(getStatusVO(param));
					}
				}
			}
			resultList.add(notStatusVO);
			resultList.add(initialStageVO);
			resultList.add(finishVO);
			resultList.add(mvTOthrCtgryVO);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured prepareRequiredTemplate() method of AlertService{}");
		}
		return resultList;
	}
	public AlertOverviewVO getStatusVO(Object[] obj){
		try{
			if(obj != null && obj.length > 0){
				AlertOverviewVO statusVO = new AlertOverviewVO();
				statusVO.setStatusTypeId(commonMethodsUtilService.getLongValueForObject(obj[0]));
				statusVO.setStatusType(commonMethodsUtilService.getStringValueForObject(obj[1]));
				return statusVO;
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getStatusVO() method of AlertService{}");
		}
		return null;
	}
	public void setAlrtSttsIds(List<Object[]> objList,Set<Long> alertStatusIdsSet){
		try{
			if(objList != null && objList.size() > 0){
				for(Object[] param:objList){
					alertStatusIdsSet.add(commonMethodsUtilService.getLongValueForObject(param[0]));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setAlrtSttsIds() method of AlertService{}");
		}
	}
	public void generateExcelReport(AlertOverviewVO resultVO){
		try{
			 HSSFWorkbook workbook = new HSSFWorkbook(); 
			 HSSFSheet spreadsheet = workbook.createSheet("Over All");
			 spreadsheet.setColumnWidth(0, 8000);
			 
			 prepareExcelReportHeading(spreadsheet,workbook);//Prepare template
			 AlertOverviewVO overViewVO = resultVO.getOverAllVO();
			 int rowNo = 2;
			 if(overViewVO != null){
				 rowNo =  writeDataToExcelSheetWorkBook(overViewVO.getSubList(),"Category",spreadsheet,rowNo);
				 rowNo = writeDataToExcelSheetWorkBook(overViewVO.getSubList1(),"State",spreadsheet,rowNo);
				 rowNo = writeDataToExcelSheetWorkBook(overViewVO.getSubList2(),"District",spreadsheet,rowNo);
			 }
			 if(resultVO != null && resultVO.getSubList() != null){
				 for(AlertOverviewVO dateVO:resultVO.getSubList()){
					 HSSFSheet sprdsht = workbook.createSheet(dateVO.getHeading());
					 
					 prepareExcelReportHeading(sprdsht,workbook);//Prepare template
					 rowNo = 2;
					 rowNo = writeDataToExcelSheetWorkBook(dateVO.getSubList(),"Category",sprdsht,rowNo);
					 rowNo = writeDataToExcelSheetWorkBook(dateVO.getSubList1(),"State",sprdsht,rowNo);
					 rowNo =writeDataToExcelSheetWorkBook(dateVO.getSubList2(),"District",sprdsht,rowNo);
				 }
			 }
			 /* FileOutputStream out = new FileOutputStream(new File(IConstants.EXPORT_TO_EXCEL_FOLDER_URL+"alertReport.xls"));
				      workbook.write(out);
				      out.close();
				      System.out.println("Writesheet.xlsx written successfully" );*/
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured generateExcelReport() method of AlertService{}");
		}
	}
	public void prepareExcelReportHeading(HSSFSheet spreadsheet,HSSFWorkbook workbook){
		try{
			  HSSFRow headingRow = spreadsheet.createRow(1);
			  spreadsheet.autoSizeColumn((short)1000000);
			 //create cell style
			  HSSFCellStyle  cellStyle = workbook.createCellStyle();
			  cellStyle.setWrapText(true);
			  
		      //cellStyle.setFillBackgroundColor(HSSFColor.YELLOW.index);
		      //cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		     // cellStyle.setFillForegroundColor(HSSFColor.BLACK.index);
		     // cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		      //merge required cell 
		      spreadsheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 4));
		      spreadsheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 7));
		      spreadsheet.addMergedRegion(new CellRangeAddress(1, 1, 9, 12));
		      spreadsheet.addMergedRegion(new CellRangeAddress(1, 1, 14, 19));
			 
			 Cell cell1 = headingRow.createCell(1);
			 Cell cell2 = headingRow.createCell(2);
			 Cell cell3 = headingRow.createCell(3);
			 cell3.setCellValue("NOT STARTED");
			 cell3.setCellStyle(cellStyle);
			 
			 Cell cell4 = headingRow.createCell(5);
			 Cell cell5 = headingRow.createCell(6);
			 cell5.setCellValue("INITIAL STAGE");
			 cell5.setCellStyle(cellStyle);
			 
			 Cell cell6 = headingRow.createCell(8);
			 Cell cell7 = headingRow.createCell(9);
			 cell7.setCellValue("FINISHED");
			 cell7.setCellStyle(cellStyle);
			 
			 Cell cell8 = headingRow.createCell(13);
			 Cell cell9 = headingRow.createCell(14);
			 cell9.setCellValue("MOVED TO OTHER CATEGORY");
			 cell9.setCellStyle(cellStyle);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured prepareExcelReportHeading() method of AlertService{}");
		}
	}
	public int writeDataToExcelSheetWorkBook(List<AlertOverviewVO> subList,String heading,HSSFSheet spreadsheet,int noOfRow){
		try{
			HSSFRow row2 = spreadsheet.createRow(noOfRow++);
			 Cell cell = null;
			 cell = row2.createCell(1);
			 cell.setCellValue(heading);
			 cell = row2.createCell(2);
			 cell.setCellValue("Total");
			 int cellNumber=3;
			 //building statusHeading
			 if(subList != null && subList.size() > 0){
				 for(AlertOverviewVO mainVO:subList.get(0).getSubList()){
                    if(mainVO != null && mainVO.getSubList() != null && mainVO.getSubList().size() > 0){
                    	int size = mainVO.getSubList().size();
                    	int loopIteration = 0;
                    	for(AlertOverviewVO statusVO:mainVO.getSubList()){
                    		 cell = row2.createCell(cellNumber++);
                			 cell.setCellValue(statusVO.getStatusType());
                			 loopIteration++;
                			 if(size == loopIteration){
                			  cell = row2.createCell(cellNumber++); 
                			 }
                    	}
                    	 cell = row2.createCell(cellNumber++); 
                    }
				 }
			 }
			 
			  HSSFRow row = null;
			 if(subList != null && subList.size() > 0){
				 for(AlertOverviewVO categoryVO:subList){
					  cellNumber = 1;
					 row = spreadsheet.createRow(noOfRow++);
					 cell = row.createCell(cellNumber++); 
					 cell.setCellValue(categoryVO.getName());
					 cell = row.createCell(cellNumber++); 
					 cell.setCellValue(categoryVO.getAlertCnt());
                    if(categoryVO != null && categoryVO.getSubList() != null && categoryVO.getSubList().size() > 0){
                    	for(AlertOverviewVO mainVO:categoryVO.getSubList()){
                    		if(mainVO.getSubList() != null && mainVO.getSubList().size() > 0){
                    				int size = mainVO.getSubList().size();
                                	int loopIteration = 0;
                                	for(AlertOverviewVO statusVO:mainVO.getSubList()){
                                		 cell = row.createCell(cellNumber++);
                            			 cell.setCellValue(statusVO.getAlertCnt());
                            			 loopIteration++;
                            			 if(size == loopIteration){
                            			  cell = row.createCell(cellNumber++); 
                            			  cell.setCellValue(mainVO.getOverAllPer());
                            			 }
                                	}
                             	 cell = row.createCell(cellNumber++); 
                    		}
                    	}
                    }
				 }
			 } 
			 //grand total
			 if(subList != null && subList.size() > 0){
				    AlertOverviewVO categoryVO = subList.get(0);
				     cellNumber = 1;
					 row = spreadsheet.createRow(noOfRow++);
					 cell = row.createCell(cellNumber++); 
					 cell.setCellValue("Grand Total");
					 cell = row.createCell(cellNumber++); 
					 cell.setCellValue(categoryVO.getGrandTotal());
                    if(categoryVO != null && categoryVO.getSubList() != null && categoryVO.getSubList().size() > 0){ 
                    	for(AlertOverviewVO mainVO:categoryVO.getSubList()){
                    		if(mainVO.getSubList() != null && mainVO.getSubList().size() > 0){
                    				int size = mainVO.getSubList().size();
                                	int loopIteration = 0;
                                	for(AlertOverviewVO statusVO:mainVO.getSubList()){
                                		 cell = row.createCell(cellNumber++);
                            			 cell.setCellValue(statusVO.getGrandTotal());
                            			 loopIteration++;
                            			 if(size == loopIteration){
                            			  cell = row.createCell(cellNumber++); 
                            			  cell.setCellValue(mainVO.getGrandOverAllper());
                            			 }
                                	}
                             	 cell = row.createCell(cellNumber++); 
                    		}
                    	}
                  } 
                    //Status Wise overAll percentage
                    row = spreadsheet.createRow(noOfRow++);
                    cellNumber = 1;
                    cell = row.createCell(cellNumber++); 
                    cell = row.createCell(cellNumber++); 
                    if(categoryVO != null && categoryVO.getSubList() != null && categoryVO.getSubList().size() > 0){ 
                    	for(AlertOverviewVO mainVO:categoryVO.getSubList()){
                    		if(mainVO.getSubList() != null && mainVO.getSubList().size() > 0){
                    				int size = mainVO.getSubList().size();
                                	int loopIteration = 0;
                                	for(AlertOverviewVO statusVO:mainVO.getSubList()){
                                		 cell = row.createCell(cellNumber++);
                            			 cell.setCellValue(statusVO.getPercentage());
                            			 loopIteration++;
                            			 if(size == loopIteration){
                            			  cell = row.createCell(cellNumber++); 
                            			 }
                                	}
                             	 cell = row.createCell(cellNumber++); 
                    		}
                    	}
                  } 
		  }
			 noOfRow = noOfRow +2;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured writeDataToExcelSheetWorkBook() method of AlertService{}");
		}
		
		return noOfRow;
	}
	public ResultStatus getSmsTdpCadreDetails(){
		ResultStatus rs = new ResultStatus();
		try {
			
			Map<Long, AlertVO> cadreMap = new HashMap<Long, AlertVO>();
			//0.tdpCadreId,1.mobileNo,2.name,3.alertStatusId,4.alertStatus,5.alertCount
			List<Object[]> alertAssignedObj =alertAssignedDAO.getSmsTdpCadreDetails();
			if(alertAssignedObj !=null && alertAssignedObj.size()>0){
				for (Object[] objects : alertAssignedObj) {
					AlertVO vo = cadreMap.get(objects[0] !=null ? (Long)objects[0]:null);
					
					if(vo == null){
						vo = new AlertVO();
						vo.setId((Long)objects[0]);
						vo.setName(objects[2] !=null ? objects[2].toString():null);
						vo.setMobileNo(objects[1] !=null ? objects[1].toString():null);
						cadreMap.put((Long)objects[0], vo);
					}
					
					if(objects[3] !=null && (Long)objects[3] == 2l)
					{
						vo.setNotifiedCount((Long)objects[5]);
					}else if((Long)objects[3] == 3l ){
						vo.setActionInProgressCount((Long)objects[5]);
					}
					
				}
				
				if(cadreMap !=null && cadreMap.size()>0){
					for (Entry<Long, AlertVO> cadre : cadreMap.entrySet()) {
						AlertVO vo=cadre.getValue();
						StringBuilder sb = new StringBuilder();
						sb.append(" Dear "+vo.getName()+" Garu , \nYou are having  " );
						
						if(vo.getNotifiedCount() !=null && vo.getNotifiedCount().longValue()>0l){
							sb.append(" \nNotified Alerts : "+vo.getNotifiedCount()+".  ");
						}else if(vo.getActionInProgressCount() !=null && vo.getActionInProgressCount().longValue()>0l){
							sb.append(" \nAction In Progress Alerts : "+vo.getActionInProgressCount()+".  ");
						}
								
						sb.append(" \nPlease take the necessary action and close it ASAP. ");
													
						rs = smsCountrySmsService.sendSmsFromAdmin(sb.toString(), true, vo.getMobileNo());
						
					}
				
				}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error occured getSmsTdpCadreDetails() method of AlertService{}");
		}
		return rs;
	}
	/*
	 * Santosh (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertService#getTdpCadreEnrollementYearIds()
	 */
	public List<AlertOverviewVO> getTdpCadreEnrollementYearIds() {
		List<AlertOverviewVO> enrollementIdList = new ArrayList<AlertOverviewVO>(0);
		try {
			List<Object[]> objList = tdpCommitteeEnrollmentDAO.getTdpCadreEnrollmentYear();
			 if (objList != null && objList.size() > 0) {
				 for (Object[] param : objList) {
					AlertOverviewVO enrollementYearVO = new AlertOverviewVO();
					enrollementYearVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					enrollementYearVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					enrollementIdList.add(enrollementYearVO);
				}
			 }
		} catch (Exception e) {
			LOG.error("Error occured getTdpCadreEnrollementYearIds() method of AlertService{}");
		}
		return  enrollementIdList;
	}
}
