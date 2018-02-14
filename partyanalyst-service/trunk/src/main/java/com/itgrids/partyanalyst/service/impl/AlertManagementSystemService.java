package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
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
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerNewDAO;
import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerTrackingNewDAO;
import com.itgrids.partyanalyst.dao.IAlertCallerRelationDAO;
import com.itgrids.partyanalyst.dao.IAlertCandidateDAO;
import com.itgrids.partyanalyst.dao.IAlertCategoryDAO;
import com.itgrids.partyanalyst.dao.IAlertCommentDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentCommentNewDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentDocumentNewDAO;
import com.itgrids.partyanalyst.dao.IAlertDepartmentStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertDocumentDAO;
import com.itgrids.partyanalyst.dao.IAlertFeedbackStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertGovtOfficerSmsDetailsDAO;
import com.itgrids.partyanalyst.dao.IAlertImpactScopeDAO;
import com.itgrids.partyanalyst.dao.IAlertMeekosamPetitionerDAO;
import com.itgrids.partyanalyst.dao.IAlertSeverityDAO;
import com.itgrids.partyanalyst.dao.IAlertStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertSubTaskStatusDAO;
import com.itgrids.partyanalyst.dao.IAlertTypeDAO;
import com.itgrids.partyanalyst.dao.IAmsOfficerOtpDetailsDAO;
import com.itgrids.partyanalyst.dao.IEditionsDAO;
import com.itgrids.partyanalyst.dao.IGovtAlertDepartmentLocationNewDAO;
import com.itgrids.partyanalyst.dao.IGovtAlertSubTaskDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationHierarchyDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDetailsDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDetailsNewDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerNewDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentLevelDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentRelationDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentScopeDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentScopeLevelDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentWorkLocationDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentWorkLocationRelationDAO;
import com.itgrids.partyanalyst.dao.IGovtOfficerNewDAO;
import com.itgrids.partyanalyst.dao.IGovtOfficerSubTaskTrackingDAO;
import com.itgrids.partyanalyst.dao.IGovtProposalCategoryDAO;
import com.itgrids.partyanalyst.dao.IGovtProposalPropertyCategoryDAO;
import com.itgrids.partyanalyst.dao.IGovtProposalPropertyCategoryTrackingDAO;
import com.itgrids.partyanalyst.dao.IGovtRejoinderActionDAO;
import com.itgrids.partyanalyst.dao.IGovtSmsActionTypeDAO;
import com.itgrids.partyanalyst.dao.INewsPaperDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITvNewsChannelDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserGroupRelationDAO;
import com.itgrids.partyanalyst.dto.ActionableVO;
import com.itgrids.partyanalyst.dto.AlertAssigningVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertTrackingVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.AlertsSummeryVO;
import com.itgrids.partyanalyst.dto.AmsAppLoginVO;
import com.itgrids.partyanalyst.dto.AmsAppVO;
import com.itgrids.partyanalyst.dto.AmsDataVO;
import com.itgrids.partyanalyst.dto.AmsKeyValueVO;
import com.itgrids.partyanalyst.dto.AmsTrackingVO;
import com.itgrids.partyanalyst.dto.AmsVO;
import com.itgrids.partyanalyst.dto.DistrictOfficeViewAlertVO;
import com.itgrids.partyanalyst.dto.FilterSectionVO;
import com.itgrids.partyanalyst.dto.GovtDepartmentVO;
import com.itgrids.partyanalyst.dto.GovtOfficerSmsDetailsVO;
import com.itgrids.partyanalyst.dto.GrievanceAlertVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.JalavaniAlertsInputVO;
import com.itgrids.partyanalyst.dto.JalavaniVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationAlertVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerNew;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerTrackingNew;
import com.itgrids.partyanalyst.model.AlertComment;
import com.itgrids.partyanalyst.model.AlertDepartmentCommentNew;
import com.itgrids.partyanalyst.model.AlertDepartmentDocumentNew;
import com.itgrids.partyanalyst.model.AlertGovtOfficerSmsDetails;
import com.itgrids.partyanalyst.model.AlertStatus;
import com.itgrids.partyanalyst.model.AlertSubTaskStatus;
import com.itgrids.partyanalyst.model.AmsOfficerOtpDetails;
import com.itgrids.partyanalyst.model.CustomReport;
import com.itgrids.partyanalyst.model.GovtAlertSubTask;
import com.itgrids.partyanalyst.model.GovtDepartmentDesignationOfficerNew;
import com.itgrids.partyanalyst.model.GovtDepartmentWorkLocation;
import com.itgrids.partyanalyst.model.GovtOfficerNew;
import com.itgrids.partyanalyst.model.GovtOfficerSubTaskTracking;
import com.itgrids.partyanalyst.model.GovtProposalPropertyCategory;
import com.itgrids.partyanalyst.model.GovtProposalPropertyCategoryTracking;
import com.itgrids.partyanalyst.service.IAlertManagementSystemService;
import com.itgrids.partyanalyst.service.IAlertService;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ImageAndStringConverter;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class AlertManagementSystemService extends AlertService implements IAlertManagementSystemService{

	private final static Logger LOG =  Logger.getLogger(AlertManagementSystemService.class);
	private CommonMethodsUtilService commonMethodsUtilService;
	private IAlertDAO alertDAO; 
	private IAlertAssignedOfficerNewDAO alertAssignedOfficerNewDAO;
	private IGovtAlertDepartmentLocationNewDAO govtAlertDepartmentLocationNewDAO;
	private IAlertDepartmentStatusDAO alertDepartmentStatusDAO;
	private IAlertDepartmentCommentNewDAO alertDepartmentCommentNewDAO;
	private DateUtilService dateUtilService;
	private IAlertAssignedOfficerTrackingNewDAO alertAssignedOfficerTrackingNewDAO;
	private IGovtDepartmentDesignationOfficerDetailsNewDAO govtDepartmentDesignationOfficerDetailsNewDAO;
	private IGovtDepartmentDesignationOfficerNewDAO govtDepartmentDesignationOfficerNewDAO;
	private IGovtAlertSubTaskDAO govtAlertSubTaskDAO;
	
	private TransactionTemplate transactionTemplate = null;
	private IGovtDepartmentScopeLevelDAO govtDepartmentScopeLevelDAO;
	private IGovtDepartmentWorkLocationDAO govtDepartmentWorkLocationDAO;
	private IGovtOfficerSubTaskTrackingDAO govtOfficerSubTaskTrackingDAO;
	private IGovtDepartmentWorkLocationRelationDAO govtDepartmentWorkLocationRelationDAO;
	private IAlertSubTaskStatusDAO alertSubTaskStatusDAO;
	private IGovtDepartmentScopeDAO govtDepartmentScopeDAO;
	private IGovtDepartmentDAO govtDepartmentDAO;
	private ActivityService activityService;
	private IAlertDepartmentDocumentNewDAO alertDepartmentDocumentNewDAO;
	private IGovtDepartmentDesignationHierarchyDAO govtDepartmentDesignationHierarchyDAO;
	private IGovtDepartmentDesignationOfficerDetailsDAO govtDepartmentDesignationOfficerDetailsDAO;
	private IGovtDepartmentDesignationDAO govtDepartmentDesignationDAO;

	private IAlertStatusDAO alertStatusDAO; 
	private IAlertSeverityDAO alertSeverityDAO; 
	private IAlertCategoryDAO alertCategoryDAO;
	private IEditionsDAO editionsDAO; 
	private ITvNewsChannelDAO tvNewsChannelDAO;
	private INewsPaperDAO newsPaperDAO;
	private IGovtOfficerNewDAO govtOfficerNewDAO;
	private IUserDAO userDAO;
	private IGovtSmsActionTypeDAO govtSmsActionTypeDAO;
	private IUserGroupRelationDAO userGroupRelationDAO;
	private IAlertGovtOfficerSmsDetailsDAO alertGovtOfficerSmsDetailsDAO;
	private IAlertImpactScopeDAO alertImpactScopeDAO;
	private IAlertFeedbackStatusDAO alertFeedbackStatusDAO;
	private IAlertService alertService;
	private IAlertCallerRelationDAO alertCallerRelationDAO;
	
	private IGovtDepartmentRelationDAO govtDepartmentRelationDAO;
	private IGovtProposalPropertyCategoryDAO govtProposalPropertyCategoryDAO;
	private IGovtProposalPropertyCategoryTrackingDAO govtProposalPropertyCategoryTrackingDAO;
	private IGovtProposalCategoryDAO govtProposalCategoryDAO;
	private IAlertCommentDAO alertCommentDAO;
	private IAmsOfficerOtpDetailsDAO amsOfficerOtpDetailsDAO;
	private SmsCountrySmsService smsCountrySmsService;
	private IGovtRejoinderActionDAO govtRejoinderActionDAO;
	private IAlertDocumentDAO alertDocumentDAO;
	private SetterAndGetterUtilService setterAndGetterUtilService;
	private IAlertCandidateDAO alertCandidateDAO;
	private ITdpCadreCandidateDAO tdpCadreCandidateDAO;
	private ICadreCommitteeService cadreCommitteeService;
	private ITdpCommitteeMemberDAO tdpCommitteeMemberDAO;
	private IGovtDepartmentLevelDAO govtDepartmentLevelDAO;
	private IAlertMeekosamPetitionerDAO alertMeekosamPetitionerDAO;
	private ImageAndStringConverter imageAndStringConverter;
	
	private ICadreDetailsService cadreDetailsService;
	private IAlertTypeDAO alertTypeDAO;
	//private IParliamentAssemblyDAO parliamentAssemblyDAO;
	
	
	/*public void setParliamentAssemblyDAO(
			IParliamentAssemblyDAO parliamentAssemblyDAO) {
		this.parliamentAssemblyDAO = parliamentAssemblyDAO;
	}*/
	public void setCadreDetailsService(ICadreDetailsService cadreDetailsService) {
		this.cadreDetailsService = cadreDetailsService;
	}
	public ImageAndStringConverter getImageAndStringConverter() {
		return imageAndStringConverter;
	}
	public void setImageAndStringConverter(
			ImageAndStringConverter imageAndStringConverter) {
		this.imageAndStringConverter = imageAndStringConverter;
	}
	public IAlertTypeDAO getAlertTypeDAO() {
		return alertTypeDAO;
	}
	public void setAlertTypeDAO(IAlertTypeDAO alertTypeDAO) {
		this.alertTypeDAO = alertTypeDAO;
	}
	public IGovtDepartmentLevelDAO getGovtDepartmentLevelDAO() {
		return govtDepartmentLevelDAO;
	}
	public void setGovtDepartmentLevelDAO(
			IGovtDepartmentLevelDAO govtDepartmentLevelDAO) {
		this.govtDepartmentLevelDAO = govtDepartmentLevelDAO;
	}
	public ITdpCommitteeMemberDAO getTdpCommitteeMemberDAO() {
		return tdpCommitteeMemberDAO;
	}
	public void setTdpCommitteeMemberDAO(
			ITdpCommitteeMemberDAO tdpCommitteeMemberDAO) {
		this.tdpCommitteeMemberDAO = tdpCommitteeMemberDAO;
	}
	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}
	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
	public ITdpCadreCandidateDAO getTdpCadreCandidateDAO() {
		return tdpCadreCandidateDAO;
	}
	public void setTdpCadreCandidateDAO(ITdpCadreCandidateDAO tdpCadreCandidateDAO) {
		this.tdpCadreCandidateDAO = tdpCadreCandidateDAO;
	}
	public IAlertCandidateDAO getAlertCandidateDAO() {
		return alertCandidateDAO;
	}
	public void setAlertCandidateDAO(IAlertCandidateDAO alertCandidateDAO) {
		this.alertCandidateDAO = alertCandidateDAO;
	}
	public SetterAndGetterUtilService getSetterAndGetterUtilService() {
		return setterAndGetterUtilService;
	}
	public void setSetterAndGetterUtilService(
			SetterAndGetterUtilService setterAndGetterUtilService) {
		this.setterAndGetterUtilService = setterAndGetterUtilService;
	}
	public IAlertDocumentDAO getAlertDocumentDAO() {
		return alertDocumentDAO;
	}
	public void setAlertDocumentDAO(IAlertDocumentDAO alertDocumentDAO) {
		this.alertDocumentDAO = alertDocumentDAO;
	}
	public SmsCountrySmsService getSmsCountrySmsService() {
		return smsCountrySmsService;
	}
	public void setSmsCountrySmsService(SmsCountrySmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}
	public void setAmsOfficerOtpDetailsDAO(
			IAmsOfficerOtpDetailsDAO amsOfficerOtpDetailsDAO) {
		this.amsOfficerOtpDetailsDAO = amsOfficerOtpDetailsDAO;
	}

	public IAlertCallerRelationDAO getAlertCallerRelationDAO() {
		return alertCallerRelationDAO;
	}

	public void setAlertCallerRelationDAO(
			IAlertCallerRelationDAO alertCallerRelationDAO) {
		this.alertCallerRelationDAO = alertCallerRelationDAO;
	}


	public void setGovtDepartmentRelationDAO(
			IGovtDepartmentRelationDAO govtDepartmentRelationDAO) {
		this.govtDepartmentRelationDAO = govtDepartmentRelationDAO;
	}

	public void setAlertFeedbackStatusDAO(
			IAlertFeedbackStatusDAO alertFeedbackStatusDAO) {
		this.alertFeedbackStatusDAO = alertFeedbackStatusDAO;
	}

	public IGovtSmsActionTypeDAO getGovtSmsActionTypeDAO() {
		return govtSmsActionTypeDAO;
	}

	public void setGovtSmsActionTypeDAO(IGovtSmsActionTypeDAO govtSmsActionTypeDAO) {
		this.govtSmsActionTypeDAO = govtSmsActionTypeDAO;
	}

	public IGovtOfficerNewDAO getGovtOfficerNewDAO() {
		return govtOfficerNewDAO;
	}

	public void setGovtOfficerNewDAO(IGovtOfficerNewDAO govtOfficerNewDAO) {
		this.govtOfficerNewDAO = govtOfficerNewDAO;
	}

	public IUserGroupRelationDAO getUserGroupRelationDAO() {
		return userGroupRelationDAO;
	}

	public void setUserGroupRelationDAO(IUserGroupRelationDAO userGroupRelationDAO) {
		this.userGroupRelationDAO = userGroupRelationDAO;
	}

	public IAlertSubTaskStatusDAO getAlertSubTaskStatusDAO() {
		return alertSubTaskStatusDAO;
	}

	public void setAlertSubTaskStatusDAO(
			IAlertSubTaskStatusDAO alertSubTaskStatusDAO) {
		this.alertSubTaskStatusDAO = alertSubTaskStatusDAO;
	}

	public IAlertSeverityDAO getAlertSeverityDAO() {
		return alertSeverityDAO;
	}

	public void setAlertSeverityDAO(IAlertSeverityDAO alertSeverityDAO) {
		this.alertSeverityDAO = alertSeverityDAO;
	}

	public IAlertCategoryDAO getAlertCategoryDAO() {
		return alertCategoryDAO;
	}

	public void setAlertCategoryDAO(IAlertCategoryDAO alertCategoryDAO) {
		this.alertCategoryDAO = alertCategoryDAO;
	}

	public IEditionsDAO getEditionsDAO() {
		return editionsDAO;
	}

	public void setEditionsDAO(IEditionsDAO editionsDAO) {
		this.editionsDAO = editionsDAO;
	}

	public ITvNewsChannelDAO getTvNewsChannelDAO() {
		return tvNewsChannelDAO;
	}

	public void setTvNewsChannelDAO(ITvNewsChannelDAO tvNewsChannelDAO) {
		this.tvNewsChannelDAO = tvNewsChannelDAO;
	}

	public void setAlertStatusDAO(IAlertStatusDAO alertStatusDAO) {
		this.alertStatusDAO = alertStatusDAO;
	}

	public IGovtDepartmentDesignationDAO getGovtDepartmentDesignationDAO() {
		return govtDepartmentDesignationDAO;
	}

	public void setGovtDepartmentDesignationDAO(IGovtDepartmentDesignationDAO govtDepartmentDesignationDAO) {
		this.govtDepartmentDesignationDAO = govtDepartmentDesignationDAO;
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

	public void setGovtDepartmentDesignationHierarchyDAO(IGovtDepartmentDesignationHierarchyDAO govtDepartmentDesignationHierarchyDAO) {
		this.govtDepartmentDesignationHierarchyDAO = govtDepartmentDesignationHierarchyDAO;
	}

	public IAlertDepartmentDocumentNewDAO getAlertDepartmentDocumentNewDAO() {
		return alertDepartmentDocumentNewDAO;
	}

	public void setAlertDepartmentDocumentNewDAO(IAlertDepartmentDocumentNewDAO alertDepartmentDocumentNewDAO) {
		this.alertDepartmentDocumentNewDAO = alertDepartmentDocumentNewDAO;
	}

	public ActivityService getActivityService() {
		return activityService;
	}

	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	public IGovtDepartmentDAO getGovtDepartmentDAO() {
		return govtDepartmentDAO;
	}

	public void setGovtDepartmentDAO(IGovtDepartmentDAO govtDepartmentDAO) {
		this.govtDepartmentDAO = govtDepartmentDAO;
	}
	public IGovtDepartmentScopeDAO getGovtDepartmentScopeDAO() {
		return govtDepartmentScopeDAO;
	}

	public void setGovtDepartmentScopeDAO(
			IGovtDepartmentScopeDAO govtDepartmentScopeDAO) {
		this.govtDepartmentScopeDAO = govtDepartmentScopeDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public IGovtAlertDepartmentLocationNewDAO getGovtAlertDepartmentLocationNewDAO() {
		return govtAlertDepartmentLocationNewDAO;
	}

	public IAlertDepartmentStatusDAO getAlertDepartmentStatusDAO() {
		return alertDepartmentStatusDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public IGovtDepartmentScopeLevelDAO getGovtDepartmentScopeLevelDAO() {
		return govtDepartmentScopeLevelDAO;
	}

	public IGovtDepartmentWorkLocationDAO getGovtDepartmentWorkLocationDAO() {
		return govtDepartmentWorkLocationDAO;
	}
	public void setGovtDepartmentWorkLocationRelationDAO(
			IGovtDepartmentWorkLocationRelationDAO govtDepartmentWorkLocationRelationDAO) {
		this.govtDepartmentWorkLocationRelationDAO = govtDepartmentWorkLocationRelationDAO;
	}

	public void setGovtOfficerSubTaskTrackingDAO(
			IGovtOfficerSubTaskTrackingDAO govtOfficerSubTaskTrackingDAO) {
		this.govtOfficerSubTaskTrackingDAO = govtOfficerSubTaskTrackingDAO;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setGovtDepartmentScopeLevelDAO(
			IGovtDepartmentScopeLevelDAO govtDepartmentScopeLevelDAO) {
		this.govtDepartmentScopeLevelDAO = govtDepartmentScopeLevelDAO;
	}

	public void setGovtDepartmentWorkLocationDAO(
			IGovtDepartmentWorkLocationDAO govtDepartmentWorkLocationDAO) {
		this.govtDepartmentWorkLocationDAO = govtDepartmentWorkLocationDAO;
	}

	public IGovtAlertSubTaskDAO getGovtAlertSubTaskDAO() {
		return govtAlertSubTaskDAO;
	}

	public void setGovtAlertSubTaskDAO(IGovtAlertSubTaskDAO govtAlertSubTaskDAO) {
		this.govtAlertSubTaskDAO = govtAlertSubTaskDAO;
	}

	public IAlertAssignedOfficerNewDAO getAlertAssignedOfficerNewDAO() {
		return alertAssignedOfficerNewDAO;
	}


	public IGovtDepartmentDesignationOfficerDetailsNewDAO getGovtDepartmentDesignationOfficerDetailsNewDAO() {
		return govtDepartmentDesignationOfficerDetailsNewDAO;
	}

	public void setGovtDepartmentDesignationOfficerDetailsNewDAO(
			IGovtDepartmentDesignationOfficerDetailsNewDAO govtDepartmentDesignationOfficerDetailsNewDAO) {
		this.govtDepartmentDesignationOfficerDetailsNewDAO = govtDepartmentDesignationOfficerDetailsNewDAO;
	}

	
	public IGovtDepartmentDesignationOfficerNewDAO getGovtDepartmentDesignationOfficerNewDAO() {
		return govtDepartmentDesignationOfficerNewDAO;
	}

	public void setGovtDepartmentDesignationOfficerNewDAO(
			IGovtDepartmentDesignationOfficerNewDAO govtDepartmentDesignationOfficerNewDAO) {
		this.govtDepartmentDesignationOfficerNewDAO = govtDepartmentDesignationOfficerNewDAO;
	}
	
	
	public IAlertAssignedOfficerTrackingNewDAO getAlertAssignedOfficerTrackingNewDAO() {
		return alertAssignedOfficerTrackingNewDAO;
	}
	public void setAlertAssignedOfficerTrackingNewDAO(
			IAlertAssignedOfficerTrackingNewDAO alertAssignedOfficerTrackingNewDAO) {
		this.alertAssignedOfficerTrackingNewDAO = alertAssignedOfficerTrackingNewDAO;
	}
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public void setAlertDAO(IAlertDAO alertDAO) {
		this.alertDAO = alertDAO;
	}
	public void setAlertAssignedOfficerNewDAO(
			IAlertAssignedOfficerNewDAO alertAssignedOfficerNewDAO) {
		this.alertAssignedOfficerNewDAO = alertAssignedOfficerNewDAO;
	}
	public void setGovtAlertDepartmentLocationNewDAO(
			IGovtAlertDepartmentLocationNewDAO govtAlertDepartmentLocationNewDAO) {
		this.govtAlertDepartmentLocationNewDAO = govtAlertDepartmentLocationNewDAO;
	}
	public void setAlertDepartmentStatusDAO(
			IAlertDepartmentStatusDAO alertDepartmentStatusDAO) {
		this.alertDepartmentStatusDAO = alertDepartmentStatusDAO;
	}
	public IAlertDepartmentCommentNewDAO getAlertDepartmentCommentNewDAO() {
		return alertDepartmentCommentNewDAO;
	}
	public void setAlertDepartmentCommentNewDAO(IAlertDepartmentCommentNewDAO alertDepartmentCommentNewDAO) {
		this.alertDepartmentCommentNewDAO = alertDepartmentCommentNewDAO;
	}
	
	public INewsPaperDAO getNewsPaperDAO() {
		return newsPaperDAO;
	}

	public void setNewsPaperDAO(INewsPaperDAO newsPaperDAO) {
		this.newsPaperDAO = newsPaperDAO;
	}
	public IUserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public IAlertGovtOfficerSmsDetailsDAO getAlertGovtOfficerSmsDetailsDAO() {
		return alertGovtOfficerSmsDetailsDAO;
	}

	public void setAlertGovtOfficerSmsDetailsDAO(
			IAlertGovtOfficerSmsDetailsDAO alertGovtOfficerSmsDetailsDAO) {
		this.alertGovtOfficerSmsDetailsDAO = alertGovtOfficerSmsDetailsDAO;
	}

	
	public IAlertImpactScopeDAO getAlertImpactScopeDAO() {
		return alertImpactScopeDAO;
	}

	public void setAlertImpactScopeDAO(IAlertImpactScopeDAO alertImpactScopeDAO) {
		this.alertImpactScopeDAO = alertImpactScopeDAO;
	}

	public IAlertService getAlertService() {
		return alertService;
	}

	public void setAlertService(IAlertService alertService) {
		this.alertService = alertService;
	}

	public IGovtProposalPropertyCategoryDAO getGovtProposalPropertyCategoryDAO() {
		return govtProposalPropertyCategoryDAO;
	}

	public void setGovtProposalPropertyCategoryDAO(IGovtProposalPropertyCategoryDAO govtProposalPropertyCategoryDAO) {
		this.govtProposalPropertyCategoryDAO = govtProposalPropertyCategoryDAO;
	}

	public IGovtProposalPropertyCategoryTrackingDAO getGovtProposalPropertyCategoryTrackingDAO() {
		return govtProposalPropertyCategoryTrackingDAO;
	}

	public void setGovtProposalPropertyCategoryTrackingDAO(
			IGovtProposalPropertyCategoryTrackingDAO govtProposalPropertyCategoryTrackingDAO) {
		this.govtProposalPropertyCategoryTrackingDAO = govtProposalPropertyCategoryTrackingDAO;
	}

	public IGovtProposalCategoryDAO getGovtProposalCategoryDAO() {
		return govtProposalCategoryDAO;
	}

	public void setGovtProposalCategoryDAO(IGovtProposalCategoryDAO govtProposalCategoryDAO) {
		this.govtProposalCategoryDAO = govtProposalCategoryDAO;
	}

	public IAlertCommentDAO getAlertCommentDAO() {
		return alertCommentDAO;
	}

	public void setAlertCommentDAO(IAlertCommentDAO alertCommentDAO) {
		this.alertCommentDAO = alertCommentDAO;
	}

	public IGovtRejoinderActionDAO getGovtRejoinderActionDAO() {
		return govtRejoinderActionDAO;
	}

	public void setGovtRejoinderActionDAO(IGovtRejoinderActionDAO govtRejoinderActionDAO) {
		this.govtRejoinderActionDAO = govtRejoinderActionDAO;
	}
	public IAlertMeekosamPetitionerDAO getAlertMeekosamPetitionerDAO() {
		return alertMeekosamPetitionerDAO;
	}

	public void setAlertMeekosamPetitionerDAO(
			IAlertMeekosamPetitionerDAO alertMeekosamPetitionerDAO) {
		this.alertMeekosamPetitionerDAO = alertMeekosamPetitionerDAO;
	}

	//Business Method
	/*
	 * Santosh (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getStatusWiseAlertOverviewcnt(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.util.List, java.lang.Long, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	public List<AlertVO> getStatusWiseAlertOverviewcnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		LOG.info("Entered in getStatusWiseAlertOverviewcnt() method of AlertManagementSystemService{}");
		try{
			
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
			
			prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
			
			if(deptIdList != null && deptIdList.size() == 0){
				deptIdList.add(0L);  
			}
			//get alert status count and and create a map of alertStatusId and its count
			List<Object[]> totalList = new ArrayList<Object[]>();
			List<Object[]> alertCountList = alertDAO.getTotalGovtPendingStatusAlertCnt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,"Status",calCntrIdList,null,null,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//for pending status
			if(alertCountList != null && alertCountList.size() > 0){
				totalList.addAll(alertCountList);
			}
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			List<Object[]> alertCountList2 = alertAssignedOfficerNewDAO.getAlertCntByRequiredType(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"status",null,null,calCntrIdList,socialMediaTypeIds,null,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			if(alertCountList2 != null && alertCountList2.size() > 0){
				totalList.addAll(alertCountList2);
			}
			setAlertCount(totalList,finalAlertVOs); 
			return finalAlertVOs; 
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getStatusWiseAlertOverviewcnt() method of AlertManagementSystemService{}");
		}
		return null;
	}
	public void setAlertCount(List<Object[]> objList,List<AlertVO> finalAlertVOs){
	    try{
	    	if(objList != null && objList.size() > 0){         
				Long totalAlertCnt = 0l;
				for(Object[] param : objList){
						 totalAlertCnt = totalAlertCnt+commonMethodsUtilService.getLongValueForObject(param[3]);	 
				 }
				 for(Object[] param : objList){
					Long id = commonMethodsUtilService.getLongValueForObject(param[0]);
					 AlertVO VO = getMatchVO1(finalAlertVOs,id);
					 if(VO == null){
						 VO = new AlertVO();
						 VO.setId(id);
						 VO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						 VO.setColor(commonMethodsUtilService.getStringValueForObject(param[2])); 
						 VO.setAlertCnt(commonMethodsUtilService.getLongValueForObject(param[3]));
						 finalAlertVOs.add(VO); 
					 }else{
					 	  VO.setAlertCnt(VO.getAlertCnt()+commonMethodsUtilService.getLongValueForObject(param[3]));
					 }
				}
				//Calculating Percentage
				calculatePercentage(finalAlertVOs,totalAlertCnt);
			}
	    }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setStatusWiseAlertCnt() method of CccDashboardService{}");
	    }
	}
	public void calculatePerc(List<AlertVO> resuList,Long totalAlertCnt){
		try{
			if(resuList != null && resuList.size() > 0){
				for(AlertVO VO:resuList){
					if(VO.getSubList2() != null && VO.getSubList2().size() > 0){
						for (AlertVO alertVO : VO.getSubList2()) {
							alertVO.setPercentage(calculatePercantage(alertVO.getAlertCnt(), totalAlertCnt));
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured calculatePercentage() method of CccDashboardService{}");
	    }
	}
	public void calculatePercentage(List<AlertVO> resuList,Long totalAlertCnt){
		try{
			if(resuList != null && resuList.size() > 0){
				for(AlertVO VO:resuList){
					VO.setPercentage(calculatePercantage(VO.getAlertCnt(), totalAlertCnt));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured calculatePercentage() method of CccDashboardService{}");
	    }
	}
	public AlertVO getMatchVO1(List<AlertVO> finalAlertVOs,Long id){
		try{
			if(finalAlertVOs == null || finalAlertVOs.size() ==0)
				return null;
			for(AlertVO VO:finalAlertVOs){
				if(VO.getId().equals(id))
				{
					return VO;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getMatchVO1() method of CccDashboardService{}");
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
	 * Santosh (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getLevelWiseAlertOverviewCnt(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.util.List, java.lang.Long)
	 */
	public List<AlertVO> getLevelWiseAlertOverviewCnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		LOG.info("Entered in getLevelWiseAlertOverviewCnt() method of AlertManagementSystemService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
			
			prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
			
			if(deptIdList != null && deptIdList.size() == 0){
				deptIdList.add(0L);  
			}
			//get all the alert status and build the template
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			List<Object[]> rtrnObjLst = alertAssignedOfficerNewDAO.getAlertCntByRequiredType(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"Level",null,null,calCntrIdList,socialMediaTypeIds,null,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			setAlertCount(rtrnObjLst,finalAlertVOs);
			return finalAlertVOs; 
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getLevelWiseAlertOverviewCnt() method of AlertManagementSystemService{}");
		}
		return null;
	}
	/*
	 * Santosh(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getDepartmentStatus()
	 */
	public List<AlertVO> getDepartmentStatus(){	
		List<AlertVO> finalList = new ArrayList<AlertVO>(); 
		try {
			AlertVO VO = new AlertVO();
			VO.setId(1l);
			VO.setName("Pending");
			finalList.add(VO);
			List<Object[]> statusList = alertAssignedOfficerNewDAO.getAssignedStatuses();
			setRequiredData(statusList,finalList);
		} catch (Exception e) {
			LOG.error("Error occured getDepartmentStatus() method of AlertManagementSystemService{}");
		}
		return finalList;
	}
	/*
	 * Satnosh (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getDepartmentScope()
	 */
	public List<AlertVO> getDepartmentScope(){	
		List<AlertVO> finalList = new ArrayList<AlertVO>(); 
		try {
			List<Object[]> scopeObjList = alertAssignedOfficerNewDAO.getDepartmentScope();
			setRequiredData(scopeObjList,finalList);
		  } catch (Exception e) {
			  LOG.error("Error occured getDepartmentStatus() method of AlertManagementSystemService{}");
		}
		return finalList;
	}
	public void setRequiredData(List<Object[]> objList,List<AlertVO> resultList){
		try{
			 if(objList != null && objList.size() > 0){
				 for(Object[] param:objList){
					 AlertVO VO = new AlertVO();
					   VO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					   VO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					   resultList.add(VO);
				 }
			 }
		}catch(Exception e){
			LOG.error("Error occured setRequiredData() method of AlertManagementSystemService{}");
		}
  }
	/*
	 * Santosh (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getDepartmentWiseAlertOverviewCnt(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.util.List, java.lang.Long, java.util.List, java.util.List, java.lang.String)
	 */
	public List<AlertVO> getDepartmentWiseAlertOverviewCnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> alertStatusIds,List<Long> departmentScopeIds,String resultType,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		LOG.info("Entered in getLevelWiseAlertOverviewCnt() method of AlertManagementSystemService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
			
			prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
			
			if(deptIdList != null && deptIdList.size() == 0){
				deptIdList.add(0L);  
			}
			
			List<Object[]> totalList = new ArrayList<Object[]>();
			if(resultType != null && resultType.equalsIgnoreCase("Status") && (alertStatusIds.size() == 0l || alertStatusIds.contains(1l))){
				List<Object[]> alertCountList = alertDAO.getTotalGovtPendingStatusAlertCnt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,"Department",calCntrIdList,null,null,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//for pending status getting department wise status
				if(alertCountList != null && alertCountList.size() > 0){
					totalList.addAll(alertCountList);
				}
				setAlertCount(totalList,finalAlertVOs);//Pending Alert setting department wise
			}
			//get all the alert status and build the template
			List<Long> levelValues = new ArrayList<Long>();    
			Map<Long,AlertVO> departmentMap = new LinkedHashMap<Long, AlertVO>();
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			List<Object[]> rtrnObjLst = null;
			List<Object[]> deptObjLst = alertAssignedOfficerNewDAO.getMainDeptAndItsSubDepartment();
			prepareParentChildDeptTemplate(deptObjLst,departmentMap);
			
			if(resultType != null && resultType.equalsIgnoreCase("Status") && (alertStatusIds.size() == 0l || !alertStatusIds.contains(1l))){
				  rtrnObjLst  = alertAssignedOfficerNewDAO.getAlertCntByRequiredType(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"Department",alertStatusIds,departmentScopeIds,calCntrIdList,socialMediaTypeIds,null,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			}
			if(resultType != null && resultType.equalsIgnoreCase("Department")){
				 rtrnObjLst = alertAssignedOfficerNewDAO.getAlertCntByRequiredType(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"Department",alertStatusIds,departmentScopeIds,calCntrIdList,socialMediaTypeIds,null,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			}
			
			setDepartmentWiseAlertCnt(rtrnObjLst,departmentMap);
			
			if(totalList != null && totalList.size() > 0){//in the case of overall and pending alert we are merging data in required format
					if(departmentMap != null && departmentMap.size() > 0){
						for(Entry<Long,AlertVO> deptEntry:departmentMap.entrySet()){
							if(deptEntry.getValue().getSubMap() != null && deptEntry.getValue().getSubMap().size() > 0){
								for(Entry<Long,AlertVO> childDeptEntry:deptEntry.getValue().getSubMap().entrySet()){
									 AlertVO matchVO = getMatchVO1(finalAlertVOs,childDeptEntry.getKey());
									 if(matchVO != null){
										 childDeptEntry.getValue().setAlertCnt(childDeptEntry.getValue().getAlertCnt()+matchVO.getAlertCnt());
										 deptEntry.getValue().setAlertCnt(deptEntry.getValue().getAlertCnt()+matchVO.getAlertCnt());
									 }
								}
							}
							
						}
					}
					if(departmentMap != null && departmentMap.size() > 0){
						for(Entry<Long,AlertVO> deptEntry:departmentMap.entrySet()){
							AlertVO matchVO = getMatchVO1(finalAlertVOs,deptEntry.getKey());
							if(matchVO != null){
								   matchVO.setAlertCnt(deptEntry.getValue().getAlertCnt());
								   matchVO.setSubList1(new ArrayList<AlertVO>(deptEntry.getValue().getSubMap().values()));
								   matchVO.setChildDeptIds(new HashSet<Long>(deptEntry.getValue().getSubMap().keySet()));
								   deptEntry.getValue().getSubMap().clear();
							}else{
								   matchVO = new AlertVO();
								   matchVO.setId(deptEntry.getValue().getId());
								   matchVO.setName(deptEntry.getValue().getName());
								   matchVO.setColor(deptEntry.getValue().getColor()); 
								   matchVO.setAlertCnt(deptEntry.getValue().getAlertCnt());
								   matchVO.setSubList1(new ArrayList<AlertVO>(deptEntry.getValue().getSubMap().values()));
								   matchVO.setChildDeptIds(new HashSet<Long>(deptEntry.getValue().getSubMap().keySet()));
								   deptEntry.getValue().getSubMap().clear();
								   if(deptEntry.getValue().getAlertCnt() > 0){
									   finalAlertVOs.add(matchVO);   
								   }
								   
							}
						}
				 }
			}else{
				if(departmentMap != null && departmentMap.size() > 0){
					for(Entry<Long,AlertVO> deptEntry:departmentMap.entrySet()){
						deptEntry.getValue().setSubList1(new ArrayList<AlertVO>(deptEntry.getValue().getSubMap().values()));
						deptEntry.getValue().setChildDeptIds(new HashSet<Long>(deptEntry.getValue().getSubMap().keySet()));
						deptEntry.getValue().getSubMap().values().clear();
						if(deptEntry.getValue().getAlertCnt() != null && deptEntry.getValue().getAlertCnt() > 0){
							finalAlertVOs.add(deptEntry.getValue());	
						}
					}
				}
			}
			
			calculatePer(finalAlertVOs);
			//sort list based on alphabetical order
			if(finalAlertVOs.size()>0){
				Collections.sort(finalAlertVOs, alphabeticalOrderWiseSorting);
			}
			return finalAlertVOs; 
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setDepartmentWiseAlertCnt() method of AlertManagementSystemService{}");
		}
		return null;
	}
	public static Comparator<AlertVO> alphabeticalOrderWiseSorting = new Comparator<AlertVO>() {

		public int compare(AlertVO obj1, AlertVO ojb2) {
			return obj1.getName().compareTo(ojb2.getName());
		}
    	
     };
	public void calculatePer(List<AlertVO> finalList){
		try{
			Long totalAlertCnt = 0l;
			if(finalList != null && finalList.size() > 0){
				for(AlertVO vo:finalList){
					totalAlertCnt = totalAlertCnt + vo.getAlertCnt();
				}
				for(AlertVO parentDeptVO:finalList){
					parentDeptVO.setPercentage(calculatePercantage(parentDeptVO.getAlertCnt(), totalAlertCnt));
					if(parentDeptVO.getSubList1() != null && parentDeptVO.getSubList1().size() > 0){
						for(AlertVO childDeptVO:parentDeptVO.getSubList1()){
							childDeptVO.setPercentage(calculatePercantage(childDeptVO.getAlertCnt(), parentDeptVO.getAlertCnt()));
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured calculatePer() method of AlertManagementSystemService{}");
		}
	}
	public void prepareParentChildDeptTemplate(List<Object[]> objList,Map<Long,AlertVO> departmentMap){
		try{
			if(objList != null && objList.size() > 0){
				for(Object[] param:objList){
					AlertVO deptVO = departmentMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					 if(deptVO == null){
						 deptVO = new AlertVO();
						 deptVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));//parent deptId
						 deptVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));//name 
						 deptVO.setColor(commonMethodsUtilService.getStringValueForObject(param[2])); 
						 deptVO.setSubMap(new LinkedHashMap<Long, AlertVO>());
						 departmentMap.put(deptVO.getId(), deptVO);
					 }
					    AlertVO subDetpVO = deptVO.getSubMap().get(commonMethodsUtilService.getLongValueForObject(param[3]));
						   subDetpVO = new AlertVO();
						   subDetpVO.setId(commonMethodsUtilService.getLongValueForObject(param[3]));//child deptId
						   subDetpVO.setName(commonMethodsUtilService.getStringValueForObject(param[4]));//name
						   subDetpVO.setColor(commonMethodsUtilService.getStringValueForObject(param[5])); 
						   deptVO.getSubMap().put(subDetpVO.getId(), subDetpVO);
			 }
		  }
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setDepartmentWiseAlertCnt() method of AlertManagementSystemService{}");
		}
	}
	public void setDepartmentWiseAlertCnt(List<Object[]> objLst,Map<Long,AlertVO> departmentMap){
		try{
			Map<Long,Long> deptIdCntMap = new HashMap<Long, Long>();
			if(objLst != null && objLst.size() > 0){
				for(Object[] param:objLst){
					deptIdCntMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[3]));
				}
			}
			if(departmentMap != null && departmentMap.size() > 0){
				for(Entry<Long,AlertVO> parentDeptEntry:departmentMap.entrySet()){
					if(parentDeptEntry.getValue().getSubMap()  != null && parentDeptEntry.getValue().getSubMap().size() > 0){
						 for(Entry<Long,AlertVO> childDeptEntry:parentDeptEntry.getValue().getSubMap().entrySet()){
							 if(deptIdCntMap.get(childDeptEntry.getKey()) != null){
								 childDeptEntry.getValue().setAlertCnt(deptIdCntMap.get(childDeptEntry.getKey()));
								 parentDeptEntry.getValue().setAlertCnt(parentDeptEntry.getValue().getAlertCnt()+deptIdCntMap.get(childDeptEntry.getKey()));
							 }
						 }
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setDepartmentWiseAlertCnt() method of AlertManagementSystemService{}");
		}
	}
	//sandeep
		public ResultStatus updateComment(final Long alertId,final String comment,final Long userId){
			final ResultStatus rs = new ResultStatus();
			try {
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
						AlertDepartmentCommentNew adcn = new AlertDepartmentCommentNew();
						adcn.setComment(comment);
						adcn.setInsertedBy(userId);
						adcn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						adcn = alertDepartmentCommentNewDAO.save(adcn);

						List<AlertAssignedOfficerNew> aaonList = alertAssignedOfficerNewDAO.getModelForAlert(alertId);
						if(aaonList != null && aaonList.size() > 0){
							AlertAssignedOfficerNew aaon = aaonList.get(0);
							
							//save record in tracking
							saveRecordIntoTracking(aaon,userId,adcn.getAlertDepartmentCommentId()+"",7l);
							getAlertDetailsAndSendSMS(alertId,7L,userId,"",comment);
						}
						rs.setExceptionMsg("success");
					}
				});
				
			} catch (Exception e) {
				rs.setExceptionMsg("failure");
				LOG.error("Exception raised at updateComment");
			}
			return rs;
		}

		public ResultStatus updateAlertPriority(final Long alertId,final Long priorityId,final Long userId){
			final ResultStatus rs = new ResultStatus();
			try {
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
						Integer count = alertDAO.updateAlertPriority(alertId,priorityId,userId,dateUtilService.getCurrentDateAndTime());
						if(count != null && count > 0){
							//save record in tracking
							saveRecordIntoTracking(alertAssignedOfficerNewDAO.getModelForAlert(alertId).get(0),userId,priorityId+"",5l);
							getAlertDetailsAndSendSMS(alertId,5L,userId,alertSeverityDAO.get(priorityId).getSeverity(),"");
							rs.setExceptionMsg("success");
						}
					}
				});
				
			} catch (Exception e) {
				rs.setExceptionMsg("failure");
				LOG.error("Exception raised at updateAlertPriority");
			}
			return rs;
		}
		
		public void saveRecordIntoTracking(AlertAssignedOfficerNew aaon,Long userId,String id,Long actiontype){
			try {
				AlertAssignedOfficerTrackingNew aaotn = new AlertAssignedOfficerTrackingNew();
				aaotn.setAlertAssignedOfficerId(aaon.getAlertAssignedOfficerId());
				aaotn.setAlertId(aaon.getAlertId());
				aaotn.setGovtDepartmentDesignationOfficerId(aaon.getGovtDepartmentDesignationOfficerId());
				aaotn.setGovtOfficerId(aaon.getGovtOfficerId());
				aaotn.setGovtAlertActionTypeId(actiontype);
				
				if(actiontype == 7l)//comment
					aaotn.setAlertDepartmentCommentId(Long.parseLong(id));
				if(actiontype == 5l)
					aaotn.setAlertSeviorityId(Long.parseLong(id));
				if(actiontype == 4l)
					aaotn.setDueDate(new SimpleDateFormat("dd/MM/yyyy").parse(id));
				if(actiontype == 3l)
					aaotn.setAlertDepartmentDocumentId(Long.parseLong(id));
				
				aaotn.setInsertedBy(userId);
				aaotn.setAlertStatusId(aaon.getAlertStatusId());
				aaotn.setUpdatedBy(userId);
				aaotn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				aaotn.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				aaotn.setIsApproved(aaon.getIsApproved());
				alertAssignedOfficerTrackingNewDAO.save(aaotn);
			} catch (Exception e) {
				LOG.error("Exception raised at saveRecordIntoTracking");
			}
			
		}
		
		public ResultStatus updateAlertDueDate(Long alertId ,String date,Long userId){
			ResultStatus rs = new ResultStatus();
			try {
				saveRecordIntoTracking(alertAssignedOfficerNewDAO.getModelForAlert(alertId).get(0),userId,date,4l);
				//getAlertDetailsAndSendSMS(alertId,4l,userId);
				rs.setExceptionMsg("success");
			} catch (Exception e) {
				rs.setExceptionMsg("failure");
				LOG.error("Exception Occured in AlertManagementSystemService of  updateAlertDueDate() ", e);
			}
			return rs;
		}
		
		public ResultStatus updateAlertStatusComment(final Long alertId,final Long statusId,final String comment,final Long userId,final Long proposalCategoryId,final String proposalAmount,final Long rejoinderActionId){
			final ResultStatus rs = new ResultStatus();
			try {
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
						
						
						/* here only we are updating for one assigned officer. But we can assing to multiple members . because of this we need to update present status of alert
						 * for every assigned user. so am iterating the whole assigned officers 
						 * Srishailam Pittala 
						 */
						
						/*AlertAssignedOfficerNew aaon = alertAssignedOfficerNewDAO.getModelForAlert(alertId).get(0);
						if(statusId == 8l || statusId == 9l)
							aaon.setIsApproved("N");
						aaon.setAlertStatusId(statusId);
						aaon.setUpdatedBy(userId);
						aaon.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						alertAssignedOfficerNewDAO.save(aaon);
						
						AlertAssignedOfficerTrackingNew aaotn = new AlertAssignedOfficerTrackingNew();
							if(statusId == 8l || statusId == 9l)
								aaon.setIsApproved("N");
						aaotn.setAlertAssignedOfficerId(aaon.getAlertAssignedOfficerId());
						aaotn.setAlertId(aaon.getAlertId());
						aaotn.setGovtDepartmentDesignationOfficerId(aaon.getGovtDepartmentDesignationOfficerId());
						aaotn.setGovtOfficerId(aaon.getGovtOfficerId());
						aaotn.setGovtAlertActionTypeId(6l);
						if(statusId != null && statusId.longValue()>0L)
							aaotn.setAlertStatusId(statusId);
						
						if(adcn != null)
							aaotn.setAlertDepartmentCommentId(adcn.getAlertDepartmentCommentId());
						
						aaotn.setInsertedBy(userId);
						aaotn.setAlertStatusId(aaon.getAlertStatusId());
						aaotn.setUpdatedBy(userId);
						aaotn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						aaotn.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						aaotn.setIsApproved(aaon.getIsApproved());
						alertAssignedOfficerTrackingNewDAO.save(aaotn);
						*/
						/* SMS sending while assigning a new alert to any officer */
						
						//List<Long> assingedIdsList = alertAssignedOfficerNewDAO.getAssignedDtls(alertId);
						List<Long> assingedIdsList = new ArrayList<Long>(0);
						Alert alert = alertDAO.get(alertId);
						if(statusId == 13l){
							GovtProposalPropertyCategory govtProposalPropertyExistingAlert = govtProposalPropertyCategoryDAO.getExistingStatusByAlertId(alertId);
							if(govtProposalPropertyExistingAlert == null){
								GovtProposalPropertyCategory govtProposalPropertyCategory = new GovtProposalPropertyCategory();
								govtProposalPropertyCategory.setAlertId(alertId);
								govtProposalPropertyCategory.setGovtProposalCategoryId(proposalCategoryId);
								govtProposalPropertyCategory.setProposalAmount(proposalAmount);
								govtProposalPropertyCategory.setGovtProposalStatusId(1l);
								govtProposalPropertyCategory.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								govtProposalPropertyCategory.setInsertedBy(userId);
								govtProposalPropertyCategory.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								govtProposalPropertyCategory.setUpdatedBy(userId);
								govtProposalPropertyCategory.setIsDeleted("N");
								govtProposalPropertyCategoryDAO.save(govtProposalPropertyCategory);
							
								AlertComment alertComment = new AlertComment();
								alertComment.setAlertId(alertId);
								alertComment.setComments(comment);
								alertComment.setInsertedBy(userId);
								alertComment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								alertComment.setIsDeleted("N");
								alertComment = alertCommentDAO.save(alertComment);
								
							GovtProposalPropertyCategoryTracking govtProposalPropertyCategoryTracking = new GovtProposalPropertyCategoryTracking();
								 govtProposalPropertyCategoryTracking.setAlertId(alertId);
								 govtProposalPropertyCategoryTracking.setGovtProposalCategoryId(proposalCategoryId);
								 govtProposalPropertyCategoryTracking.setGovtProposalStatusId(1l);
								 govtProposalPropertyCategoryTracking.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								 govtProposalPropertyCategoryTracking.setInsertedBy(userId);
								 govtProposalPropertyCategoryTracking.setIsDeleted("N");
								 govtProposalPropertyCategoryTracking.setAlertCommentId(alertComment.getAlertCommentId());
							     govtProposalPropertyCategoryTrackingDAO.save(govtProposalPropertyCategoryTracking);
							     
							     //Alert alert = alertDAO.get(alertId);
									if(alert != null && statusId != null && statusId.longValue()>0L){
										alert.setAlertStatusId(statusId);
										alert.setUpdatedBy(userId);
										alert.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
										alertDAO.save(alert);
									}
									
									AlertDepartmentCommentNew adcn = null;
									if(comment != null && !comment.trim().isEmpty()){
										adcn = new AlertDepartmentCommentNew();
										adcn.setComment(comment);
										adcn.setInsertedBy(userId);
										adcn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
										adcn = alertDepartmentCommentNewDAO.save(adcn);
									}
									//List<Long> assingedIdsList = new ArrayList<Long>(0);
									List<AlertAssignedOfficerNew> assignedOfficersList = alertAssignedOfficerNewDAO.getModelForAlert(alertId);
									if(commonMethodsUtilService.isListOrSetValid(assignedOfficersList)){
										
										for (AlertAssignedOfficerNew aaon : assignedOfficersList) {
											
											if(statusId == 8l || statusId == 9l)
												aaon.setIsApproved("N");
											aaon.setAlertStatusId(statusId);
											aaon.setUpdatedBy(userId);
											aaon.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
											alertAssignedOfficerNewDAO.save(aaon);
											
											AlertAssignedOfficerTrackingNew aaotn = new AlertAssignedOfficerTrackingNew();
												if(statusId == 8l || statusId == 9l)
													aaon.setIsApproved("N");
											aaotn.setAlertAssignedOfficerId(aaon.getAlertAssignedOfficerId());
											aaotn.setAlertId(aaon.getAlertId());
											aaotn.setGovtDepartmentDesignationOfficerId(aaon.getGovtDepartmentDesignationOfficerId());
											aaotn.setGovtOfficerId(aaon.getGovtOfficerId());
											aaotn.setGovtAlertActionTypeId(6l);
											if(statusId != null && statusId.longValue()>0L)
												aaotn.setAlertStatusId(statusId);
											
											if(adcn != null)
												aaotn.setAlertDepartmentCommentId(adcn.getAlertDepartmentCommentId());
											
											aaotn.setInsertedBy(userId);
											aaotn.setAlertStatusId(aaon.getAlertStatusId());
											aaotn.setUpdatedBy(userId);
											aaotn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
											aaotn.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
											aaotn.setIsApproved(aaon.getIsApproved());
											alertAssignedOfficerTrackingNewDAO.save(aaotn);
											
											assingedIdsList.add(aaon.getAlertAssignedOfficerId());
										}
									}
							}else{
								rs.setMessage("Already In ProposalStatus");
							}
						}else if(statusId == 10l){
							
							if(alert != null && statusId != null && statusId.longValue()>0L){
								alert.setAlertStatusId(statusId);
								alert.setUpdatedBy(userId);
								alert.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								alertDAO.save(alert);
							}
							
							AlertDepartmentCommentNew adcn = null;
							if(comment != null && !comment.trim().isEmpty()){
								adcn = new AlertDepartmentCommentNew();
								adcn.setComment(comment);
								adcn.setInsertedBy(userId);
								adcn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								adcn = alertDepartmentCommentNewDAO.save(adcn);
							}
							//List<Long> assingedIdsList = new ArrayList<Long>(0);
							List<AlertAssignedOfficerNew> assignedOfficersList = alertAssignedOfficerNewDAO.getModelForAlert(alertId);
							if(commonMethodsUtilService.isListOrSetValid(assignedOfficersList)){
								
								for (AlertAssignedOfficerNew aaon : assignedOfficersList) {
									
									if(statusId == 8l || statusId == 9l)
										aaon.setIsApproved("N");
									aaon.setAlertStatusId(statusId);
									aaon.setUpdatedBy(userId);
									aaon.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									alertAssignedOfficerNewDAO.save(aaon);
									
									/*String folderName = folderCreationForAlertsAttachmentNew();
									AlertDepartmentDocumentNew addn = null;	
									
									if(mapfiles != null && mapfiles.size() > 0){
										 AlertAssignedOfficerNew aaon = alertAssignedOfficerNewDAO.getModelForAlert(alertId).get(0);
										 for (Map.Entry<File, String> entry : mapfiles.entrySet()){
											 str = new StringBuilder();
											 Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
											 String destPath = folderName+"/"+randomNumber+"."+entry.getValue();
											 StringBuilder pathBuilder = new StringBuilder();
											  pathBuilder.append("alerts_attachments/").append(yearStr).append("/").append(dateStr).append("/").append(randomNumber).append(".").append(entry.getValue());
											 str.append(randomNumber).append(".").append(entry.getValue());
											String fileCpyStts = activityService.copyFile(entry.getKey().getAbsolutePath(),destPath);
											 
												if(fileCpyStts.equalsIgnoreCase("error")){
													resultStatus.setResultCode(ResultCodeMapper.FAILURE);
													LOG.error(" Exception Raise in copying file");
													throw new ArithmeticException();
												}
												
												addn = new AlertDepartmentDocumentNew();
												addn.setDocument(pathBuilder.toString());
												addn.setInsertedBy(userId);
												addn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
												addn = alertDepartmentDocumentNewDAO.save(addn);
									}*/
									
									AlertAssignedOfficerTrackingNew aaotn = new AlertAssignedOfficerTrackingNew();
										if(statusId == 8l || statusId == 9l)
											aaon.setIsApproved("N");
									aaotn.setAlertAssignedOfficerId(aaon.getAlertAssignedOfficerId());
									aaotn.setAlertId(aaon.getAlertId());
									aaotn.setGovtDepartmentDesignationOfficerId(aaon.getGovtDepartmentDesignationOfficerId());
									aaotn.setGovtOfficerId(aaon.getGovtOfficerId());
									aaotn.setGovtAlertActionTypeId(6l);
									if(statusId != null && statusId.longValue()>0L)
										aaotn.setAlertStatusId(statusId);
									
									if(adcn != null)
										aaotn.setAlertDepartmentCommentId(adcn.getAlertDepartmentCommentId());
									
									
									aaotn.setInsertedBy(userId);
									aaotn.setAlertStatusId(aaon.getAlertStatusId());
									aaotn.setUpdatedBy(userId);
									aaotn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
									aaotn.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									aaotn.setIsApproved(aaon.getIsApproved());
									aaotn.setGovtRejoinderActionId(rejoinderActionId);
									alertAssignedOfficerTrackingNewDAO.save(aaotn);
									
									assingedIdsList.add(aaon.getAlertAssignedOfficerId());
								}
							}
						
						}else{
							//Alert alert = alertDAO.get(alertId);
							if(alert != null && statusId != null && statusId.longValue()>0L){
								alert.setAlertStatusId(statusId);
								alert.setUpdatedBy(userId);
								alert.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								alertDAO.save(alert);
							}
							
							AlertDepartmentCommentNew adcn = null;
							if(comment != null && !comment.trim().isEmpty()){
								adcn = new AlertDepartmentCommentNew();
								adcn.setComment(comment);
								adcn.setInsertedBy(userId);
								adcn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								adcn = alertDepartmentCommentNewDAO.save(adcn);
							}
							
							List<AlertAssignedOfficerNew> assignedOfficersList = alertAssignedOfficerNewDAO.getModelForAlert(alertId);
							if(commonMethodsUtilService.isListOrSetValid(assignedOfficersList)){
								
								for (AlertAssignedOfficerNew aaon : assignedOfficersList) {
									
									if(statusId == 8l || statusId == 9l)
										aaon.setIsApproved("N");
									aaon.setAlertStatusId(statusId);
									aaon.setUpdatedBy(userId);
									aaon.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									alertAssignedOfficerNewDAO.save(aaon);
									
									AlertAssignedOfficerTrackingNew aaotn = new AlertAssignedOfficerTrackingNew();
										if(statusId == 8l || statusId == 9l)
											aaon.setIsApproved("N");
									aaotn.setAlertAssignedOfficerId(aaon.getAlertAssignedOfficerId());
									aaotn.setAlertId(aaon.getAlertId());
									aaotn.setGovtDepartmentDesignationOfficerId(aaon.getGovtDepartmentDesignationOfficerId());
									aaotn.setGovtOfficerId(aaon.getGovtOfficerId());
									aaotn.setGovtAlertActionTypeId(6l);
									if(statusId != null && statusId.longValue()>0L)
										aaotn.setAlertStatusId(statusId);
									
									if(adcn != null)
										aaotn.setAlertDepartmentCommentId(adcn.getAlertDepartmentCommentId());
									
									aaotn.setInsertedBy(userId);
									aaotn.setAlertStatusId(aaon.getAlertStatusId());
									aaotn.setUpdatedBy(userId);
									aaotn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
									aaotn.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									aaotn.setIsApproved(aaon.getIsApproved());
									alertAssignedOfficerTrackingNewDAO.save(aaotn);
									
									assingedIdsList.add(aaon.getAlertAssignedOfficerId());
								}
							}
						}
						
						if(commonMethodsUtilService.isListOrSetValid(assingedIdsList)){//assingedId != null){
							for (Long assingedId : assingedIdsList) {
								AlertAssignedOfficerNew alertAssignedOfficer2 = alertAssignedOfficerNewDAO.get(assingedId);
								Long designationId = alertAssignedOfficer2.getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getGovtDepartmentDesignationId();
								Long govtofficerId = alertAssignedOfficer2.getGovtOfficerId();
								
								List<String> mobileNos = govtOfficerNewDAO.getOfficerDetailsByOfficerId(govtofficerId);
								if(mobileNos != null && mobileNos.size() > 0 && mobileNos.get(0).trim().length() > 0 && !mobileNos.get(0).trim().isEmpty()){
					                  sendSMSTOAlertAssignedOfficer(designationId,govtofficerId,mobileNos!= null ? mobileNos.get(0):null,alert.getAlertId(),6L,userId,alertStatusDAO.get(statusId).getAlertStatus(),comment,userId);  
					            }
							}
						}
						
						rs.setExceptionMsg("success");
					}
				});	
				
				
			} catch (Exception e) {
				rs.setExceptionMsg("failure");
				LOG.error("Exception Occured in updateAlertStatusComment  ", e);
			}
			return rs;
		}
		
		/**
		 * * 
		 *  @author Srishailam Pittala
		 *  @Date 25th April,2017
		 * 	@param alertId
		 * 	@return success/failure
		 */
		public String getAlertDetailsAndSendSMS(Long alertId,Long actionTypeId,Long userId,String status,String comment){
			try {
				/* SMS sending while assigning a new alert to any officer */
				
				List<Long> assingedIdsList = alertAssignedOfficerNewDAO.getAssignedDtls(alertId);
				if(commonMethodsUtilService.isListOrSetValid(assingedIdsList)){//assingedId != null){
					for (Long assingedId : assingedIdsList) {
						AlertAssignedOfficerNew alertAssignedOfficer2 = alertAssignedOfficerNewDAO.get(assingedId);
						Long designationId = alertAssignedOfficer2.getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getGovtDepartmentDesignationId();
						Long govtofficerId = alertAssignedOfficer2.getGovtOfficerId();
						
						List<String> mobileNoList = govtOfficerNewDAO.getOfficerDetailsByOfficerId(govtofficerId);
						String mobileNos="";
						if(commonMethodsUtilService.isListOrSetValid(mobileNoList)){
							for (String mobileNo : mobileNoList) {
								mobileNos = mobileNos+","+mobileNo;
							}
						}
						sendSMSTOAlertAssignedOfficer(designationId,govtofficerId,mobileNos,alertId,actionTypeId,userId,status,comment,userId);
					}
				}
				
			} catch (Exception e) {
				LOG.error("Exception Occured in getAlertDetailsAndSendSMS  ", e);
				return "failure";
			}
			return "success";
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
				LOG.error(" Failed to Create");
				return "FAILED";
			}
		}
		
		public ResultStatus uploadDocumentsForAlert(final Map<File, String> mapfiles,final Long alertId,final Long userId){

			final ResultStatus resultStatus = new ResultStatus();
			try {
				
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
			String folderName = folderCreationForAlertsAttachmentNew();
			CustomReport customReport = null;
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			 int year = calendar.get(Calendar.YEAR);
			 int month = calendar.get(Calendar.MONTH);
			// int day = calendar.get(Calendar.DAY_OF_MONTH);
			 int temp = month+1;
			 String monthText = getMonthForInt(temp);
			 SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_VALUE);
			 String dateStr = sdf.format(new Date());
			 String yearStr = String.valueOf(year);
			 
			 StringBuilder str ;
			 
			 if(mapfiles != null && mapfiles.size() > 0){
				 AlertAssignedOfficerNew aaon = alertAssignedOfficerNewDAO.getModelForAlert(alertId).get(0);
				 for (Map.Entry<File, String> entry : mapfiles.entrySet()){
					 str = new StringBuilder();
					 Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
					 String destPath = folderName+"/"+randomNumber+"."+entry.getValue();
					 StringBuilder pathBuilder = new StringBuilder();
					  pathBuilder.append("alerts_attachments/").append(yearStr).append("/").append(dateStr).append("/").append(randomNumber).append(".").append(entry.getValue());
					 str.append(randomNumber).append(".").append(entry.getValue());
					String fileCpyStts = activityService.copyFile(entry.getKey().getAbsolutePath(),destPath);
					 
						if(fileCpyStts.equalsIgnoreCase("error")){
							resultStatus.setResultCode(ResultCodeMapper.FAILURE);
							LOG.error(" Exception Raise in copying file");
							throw new ArithmeticException();
						}
						
						AlertDepartmentDocumentNew addn = new AlertDepartmentDocumentNew();
						addn.setDocument(pathBuilder.toString());
						addn.setInsertedBy(userId);
						addn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						addn = alertDepartmentDocumentNewDAO.save(addn);
						
						//save record in tracking
						saveRecordIntoTracking(aaon,userId,addn.getAlertDepartmentDocumentId()+"",3l);
						//getAlertDetailsAndSendSMS(alertId,3L,userId);
				 }
			 }
			
			 resultStatus.setExceptionMsg("success");
					}
				});
			}catch (Exception e) {
				resultStatus.setExceptionMsg("failure");
				LOG.error(" Exception Occured in saveCustomReportUploadFile() method, Exception - ",e);
			}
			return resultStatus;
		
		}
		
		/**
		 * * 
		 *  @author Srishailam Pittala
		 *  @Date 25th April,2017
		 * 	@param alertId
		 * 	@return success/failure
		 */
		public String getSubTaskDetailsAndSendSMS(Long designationId,Long govtofficerId, Long subTaskId,Long actionTypeId,Long userId,String status,String comment,Long mainUserId){
			try {
				/* SMS sending while assigning a new alert to any officer */
				if(designationId == null || designationId.longValue()==0L){
					GovtAlertSubTask govtAlertSubTask = govtAlertSubTaskDAO.get(subTaskId);
					designationId = govtAlertSubTask.getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getGovtDepartmentDesignationId();
					govtofficerId = govtAlertSubTask.getSubTaskGovtOfficerId();
				}
				
				List<String> mobileNoList = govtOfficerNewDAO.getOfficerDetailsByOfficerId(govtofficerId);
				String mobileNos="";
				if(commonMethodsUtilService.isListOrSetValid(mobileNoList)){
					for (String mobileNo : mobileNoList) {
						mobileNos = mobileNos+","+mobileNo;
					}
				}
				sendSMSTOSubTaskAssignedOfficer(designationId,govtofficerId,mobileNos,subTaskId,actionTypeId,userId,status,comment,mainUserId);
			} catch (Exception e) {
				LOG.error("Exception Occured in getAlertDetailsAndSendSMS  ", e);
				return "failure";
			}
			return "success";
		}
		
		 public ResultStatus sendSMSTOSubTaskAssignedOfficer(Long designationId,Long govtOfficerId,String mobileNo,Long subTaskId,Long actionTypeId,Long userId,String status,String comment,Long mainUserId){
           	ResultStatus rs = new ResultStatus();
           	try {
           		
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
								if(workLocation != null)
									location=workLocation.getLocationName()+" "+workLocation.getGovtDepartmentScope().getLevelName();
							}
						}
					}
				}
				
           		GovtSMSAPIService govtSMSAPIService = new GovtSMSAPIService();
           		//get asigned officer dept, alert title
           		//0-title,1-deptId,2-deptName
           		Alert tempSMSAlert = alertDAO.get(16894L);// testing alert id for custom mobile no
           		GovtAlertSubTask task1 = govtAlertSubTaskDAO.get(subTaskId);
           		GovtDepartmentDesignationOfficerNew designationOfficeNew = govtDepartmentDesignationOfficerNewDAO.get(task1.getGovtDepartmentDesignationOfficerId());
          		Long levelId= designationOfficeNew.getGovtDepartmentScopeId();
          		Long levelValue=designationOfficeNew.getLevelValue();
          		
          		List<Long> levelValuesList = new ArrayList<Long>(0);
          		List<Object[]> alertAssignedScopeDtls = govtDepartmentWorkLocationRelationDAO.getParentGovtSuperLevelInfo(levelValue);
          		if(commonMethodsUtilService.isListOrSetValid(alertAssignedScopeDtls)){
          			for (Object[] objects : alertAssignedScopeDtls) {
          				levelId = commonMethodsUtilService.getLongValueForObject(objects[0]);
          				Long tempLevelValue=commonMethodsUtilService.getLongValueForObject(objects[1]);
              			levelValuesList.add(tempLevelValue);
					}
          		}
          		
           		Object[] objArr = govtAlertSubTaskDAO.getSubTaskDetailsForSMS(subTaskId);
           		if(objArr != null){
           			List<String> smsText = govtSmsActionTypeDAO.getSMSTextforActionTypeId(actionTypeId,2L,1L,1L);
           			String message ="Alert is assigned to you,Please follow up and resolve.\nTitle : "+objArr[0].toString()+" \nDept"+objArr[2].toString();
           			
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
           				message=message.replace("flag8", status.toString()+"\n");
           				message=message.replace("flag_99", task1 != null?task1.getAlertId().toString():null);
           				
           			}
           			if(tempSMSAlert.getDescription() != null && !tempSMSAlert.getDescription().isEmpty())
           				mobileNo=tempSMSAlert.getDescription().trim();
           			if(mobileNo != null && mobileNo.trim().length()>0 && message != null && !message.isEmpty() && message.length()>0)
           				govtSMSAPIService.senedSMSForGovtAlert(mobileNo,message); 
           			
           		
           			if(mobileNo != null && !mobileNo.isEmpty()){
           			String[] mobileNOArr = mobileNo.split(",");
           			if(mobileNOArr != null && mobileNOArr.length>0){
           				for (int i = 0; i < mobileNOArr.length; i++) {
		           			GovtOfficerSmsDetailsVO smsDetailsVO =new GovtOfficerSmsDetailsVO();
		           			smsDetailsVO.setAlertId(task1 != null?task1.getAlertId():null);
		           			smsDetailsVO.setUserId(mainUserId);
		           			smsDetailsVO.setGovtOfficerId(govtOfficerId);
		           			smsDetailsVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(mobileNOArr[i]));
		           			smsDetailsVO.setGovtSubTaskId(subTaskId);
		           			smsDetailsVO.setSmsText(message);
		           			smsDetailsVO.setActionTypeId(actionTypeId);
		           			saveGovtOfficerSmsDetails(smsDetailsVO);
           				}
           			}
           		 }
           		}
           		mobileNo="";
           		//get parent designation Id
           		List<Long> parentDesigIds = govtDepartmentDesignationHierarchyDAO.getParentDepartment(designationOfficeNew.getGovtDepartmentDesignationId());
           		if(parentDesigIds != null && parentDesigIds.size() > 0){
           			//get high level officer mobile nums
           			//List<String> mobilenums = govtDepartmentDesignationOfficerDetailsDAO.getHigherOfficerMobileNums(parentDesigIds);
           			List<String> mobilenums = govtDepartmentDesignationOfficerDetailsNewDAO.getHigherOfficerMobileNums(parentDesigIds,levelId,levelValuesList);
          			
           			if(mobilenums != null && mobilenums.size() > 0){
           				String message = "Alert is assigned to "+objArr[2].toString()+" - "+govtDepartmentDesignationDAO.getDepartmentDetails(designationId)+" - "+ mobileNo+".\n Please follow up.";
           				mobileNo = "";
           				for (String tempMobleNo : mobilenums) {
           					if(tempMobleNo != null && !tempMobleNo.trim().isEmpty())
           						mobileNo = mobileNo.equalsIgnoreCase("")?tempMobleNo:mobileNo+","+tempMobleNo;
           				}
           				
           				List<String> smsText = govtSmsActionTypeDAO.getSMSTextforActionTypeId(actionTypeId,2L,1L,2L);//2 immidiate superior type id 
               			
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
               				message=message.replace("flag8", status.toString()+"\n");
               				message=message.replace("flag_99", task1 != null?task1.getAlertId().toString():null);
               			}
               			if(tempSMSAlert.getDescription() != null && !tempSMSAlert.getDescription().isEmpty())
               				mobileNo=tempSMSAlert.getDescription().trim();
               			if(mobileNo != null && mobileNo.trim().length()>0 && message != null && !message.isEmpty() && message.length()>0)
               				govtSMSAPIService.senedSMSForGovtAlert(mobileNo,message);
               			
           
               			if(mobileNo != null && !mobileNo.isEmpty()){
               			String[] mobileNOArr = mobileNo.split(",");
               			if(mobileNOArr != null && mobileNOArr.length>0){
               				for (int i = 0; i < mobileNOArr.length; i++) {
               					GovtOfficerSmsDetailsVO smsDetailsVO =new GovtOfficerSmsDetailsVO();
                       			smsDetailsVO.setUserId(mainUserId);
                       			//smsDetailsVO.setGovtOfficerId(govtOfficerId);
                       			smsDetailsVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(mobileNOArr[i]));
                       			smsDetailsVO.setGovtSubTaskId(subTaskId);
                       			smsDetailsVO.setSmsText(message);
                       			smsDetailsVO.setActionTypeId(actionTypeId);
                       			saveGovtOfficerSmsDetails(smsDetailsVO);
							}
               			}
               		 }
           			}
           		}
           		
           		rs.setExceptionMsg("success");
           	} catch (Exception e) {
           		rs.setExceptionMsg("failure");
           		LOG.error("Error occured sendSMSTOSubTaskAssignedOfficer() method of AlertManagementSystemService{}");
           	}
           	return rs;
           }  
		 
		public List<AlertTrackingVO> viewAlertHistory(Long alertId){
			List<AlertTrackingVO> finalList = new ArrayList<AlertTrackingVO>(0);
			SimpleDateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			SimpleDateFormat dateSdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm");
			try {
				
				List<AlertAssignedOfficerTrackingNew> qryRstList = alertAssignedOfficerTrackingNewDAO.getAlertHistory(alertId);
				
				if(qryRstList != null && qryRstList.size() > 0){
					
					AlertAssignedOfficerNew aaon = alertAssignedOfficerNewDAO.getModelForAlert(alertId).get(0);
					Map<Long,AlertTrackingVO> userMap = new HashMap<Long, AlertTrackingVO>(0);
					
					for (AlertAssignedOfficerTrackingNew alertAssignedOfficerTrackingNew : qryRstList) {
						
					/*	String userName = alertAssignedOfficerTrackingNew.getGovtOfficer().getOfficerName()+" - "+alertAssignedOfficerTrackingNew.getGovtOfficer().getMobileNo();
						String designation = alertAssignedOfficerTrackingNew.getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getDesignationName()
								+" & "+alertAssignedOfficerTrackingNew.getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getGovtDepartment().getDepartmentName();*/
						String govtAlertActionType = alertAssignedOfficerTrackingNew.getGovtAlertActionType().getActionType();
						Long updatedUserId = alertAssignedOfficerTrackingNew.getInsertedBy();
						if(userMap.get(updatedUserId) == null){
							List<Object[]> userDetls = govtDepartmentDesignationOfficerDetailsDAO.getNewLocationInfoOfUser(updatedUserId);
							if(commonMethodsUtilService.isListOrSetValid(userDetls)){
								for (Object[] param : userDetls){
									String deptName = commonMethodsUtilService.getStringValueForObject(param[2]);
									String designationStr = commonMethodsUtilService.getStringValueForObject(param[3]);
									String officerName = commonMethodsUtilService.getStringValueForObject(param[4]);
									String mobileNo = commonMethodsUtilService.getStringValueForObject(param[5]);
									String locationName = commonMethodsUtilService.getStringValueForObject(param[6]);
									
									AlertTrackingVO vo = new AlertTrackingVO();
									vo.setDeptName(deptName);
									vo.setDesignation(designationStr);
									vo.setUserName(officerName);
									vo.setMobileNO(mobileNo);
									vo.setLocation(locationName);
									userMap.put(updatedUserId, vo);
								}
							}
						}
						
						AlertTrackingVO matchedDateVO = getMatchedDateVO(finalList,dateSdf.format(dbSdf.parse(alertAssignedOfficerTrackingNew.getInsertedTime().toString())));
						
						if(matchedDateVO == null){
							matchedDateVO = new AlertTrackingVO();
							matchedDateVO.setDate(dateSdf.format(dbSdf.parse(alertAssignedOfficerTrackingNew.getInsertedTime().toString())));
							finalList.add(matchedDateVO);
						}
						
						matchedDateVO = getMatchedDateVO(finalList,dateSdf.format(dbSdf.parse(alertAssignedOfficerTrackingNew.getInsertedTime().toString())));
						
						AlertTrackingVO matchedTimeVO = getMatchedDateVO(matchedDateVO.getTimeList(),timeSdf.format(dbSdf.parse(alertAssignedOfficerTrackingNew.getInsertedTime().toString())));
						if(matchedTimeVO == null){
							matchedTimeVO = new AlertTrackingVO();
							matchedTimeVO.setDate(timeSdf.format(dbSdf.parse(alertAssignedOfficerTrackingNew.getInsertedTime().toString())));
							matchedDateVO.getTimeList().add(matchedTimeVO);
						}
						
						matchedTimeVO = getMatchedDateVO(matchedDateVO.getTimeList(),timeSdf.format(dbSdf.parse(alertAssignedOfficerTrackingNew.getInsertedTime().toString())));
						
						if(alertAssignedOfficerTrackingNew.getAlertDepartmentDocumentId() != null && alertAssignedOfficerTrackingNew.getAlertDepartmentDocumentId() > 0l 
								 && alertAssignedOfficerTrackingNew.getAlertDepartmentDocument() != null){
							AlertTrackingVO vo = new AlertTrackingVO();
							vo.getStrList().add(alertAssignedOfficerTrackingNew.getAlertDepartmentDocument().getDocument());
							//vo.setUserName(userName);
							//vo.setDesignation(designation);
							vo.setUpdatedUserName(alertAssignedOfficerTrackingNew.getUpdatedUser().getUserName());
							vo.setAlertTrackingActionType(govtAlertActionType);
							
							AlertTrackingVO updatedUserVO  = userMap.get(updatedUserId);
							if(updatedUserVO != null)
							{
								vo.setDeptName(updatedUserVO.getDeptName());
								vo.setDesignation(updatedUserVO.getDesignation());
								vo.setUserName(updatedUserVO.getUserName());
								vo.setMobileNO(updatedUserVO.getMobileNO());
								vo.setLocation(updatedUserVO.getLocation());
							}
							
							matchedTimeVO.getAttachementsList().add(vo);
						}
						
						if(alertAssignedOfficerTrackingNew.getAlertDepartmentCommentId() != null && alertAssignedOfficerTrackingNew.getAlertDepartmentCommentId() > 0l && alertAssignedOfficerTrackingNew.getAlertDepartmentComment() != null){
							AlertTrackingVO vo = new AlertTrackingVO();
							vo.getStrList().add(alertAssignedOfficerTrackingNew.getAlertDepartmentComment().getComment());
							//vo.setUserName(userName);
							//vo.setDesignation(designation);
							vo.setUpdatedUserName(alertAssignedOfficerTrackingNew.getUpdatedUser().getUserName());
							vo.setAlertTrackingActionType(govtAlertActionType);
							
							AlertTrackingVO updatedUserVO  = userMap.get(updatedUserId);
							if(updatedUserVO != null)
							{
								vo.setDeptName(updatedUserVO.getDeptName());
								vo.setDesignation(updatedUserVO.getDesignation());
								vo.setUserName(updatedUserVO.getUserName());
								vo.setMobileNO(updatedUserVO.getMobileNO());
								vo.setLocation(updatedUserVO.getLocation());
							}
							
							
							matchedTimeVO.getCommentList().add(vo);
						}
						
						if(alertAssignedOfficerTrackingNew.getDueDate() != null && !alertAssignedOfficerTrackingNew.getDueDate().toString().trim().isEmpty()){
							AlertTrackingVO vo = new AlertTrackingVO();
							vo.getStrList().add(alertAssignedOfficerTrackingNew.getDueDate() != null ? alertAssignedOfficerTrackingNew.getDueDate().toString().substring(1, 10):"");
							//vo.setUserName(userName);
							//vo.setDesignation(designation);
							vo.setUpdatedUserName(alertAssignedOfficerTrackingNew.getUpdatedUser().getUserName());
							vo.setAlertTrackingActionType(govtAlertActionType);
							
							AlertTrackingVO updatedUserVO  = userMap.get(updatedUserId);
							if(updatedUserVO != null)
							{
								vo.setDeptName(updatedUserVO.getDeptName());
								vo.setDesignation(updatedUserVO.getDesignation());
								vo.setUserName(updatedUserVO.getUserName());
								vo.setMobileNO(updatedUserVO.getMobileNO());
								vo.setLocation(updatedUserVO.getLocation());
							}
							
							
							matchedTimeVO.getDueDateList().add(vo);
						}
						
						if(alertAssignedOfficerTrackingNew.getAlertStatusId() != null && alertAssignedOfficerTrackingNew.getAlertStatusId() > 0l && alertAssignedOfficerTrackingNew.getAlertStatus() != null){
							AlertTrackingVO vo = new AlertTrackingVO();
							vo.getStrList().add(alertAssignedOfficerTrackingNew.getAlertStatus().getAlertStatus());
							//vo.setUserName(userName);
							//vo.setDesignation(designation);
							vo.setUpdatedUserName(alertAssignedOfficerTrackingNew.getUpdatedUser().getUserName());
							vo.setAlertTrackingActionType(govtAlertActionType);
							
							AlertTrackingVO updatedUserVO  = userMap.get(updatedUserId);
							if(updatedUserVO != null)
							{
								vo.setDeptName(updatedUserVO.getDeptName());
								vo.setDesignation(updatedUserVO.getDesignation());
								vo.setUserName(updatedUserVO.getUserName());
								vo.setMobileNO(updatedUserVO.getMobileNO());
								vo.setLocation(updatedUserVO.getLocation());
							}
							
							
							matchedTimeVO.getStatusList().add(vo);
						}
						
						if(alertAssignedOfficerTrackingNew.getAlertSeviorityId() != null && alertAssignedOfficerTrackingNew.getAlertSeviorityId() > 0l && alertAssignedOfficerTrackingNew.getAlertSeviority() != null){
							AlertTrackingVO vo = new AlertTrackingVO();
							vo.getStrList().add(alertAssignedOfficerTrackingNew.getAlertSeviority().getSeverity());
							//vo.setUserName(userName);
							//vo.setDesignation(designation);
							vo.setUpdatedUserName(alertAssignedOfficerTrackingNew.getUpdatedUser().getUserName());
							vo.setAlertTrackingActionType(govtAlertActionType);
							
							AlertTrackingVO updatedUserVO  = userMap.get(updatedUserId);
							if(updatedUserVO != null)
							{
								vo.setDeptName(updatedUserVO.getDeptName());
								vo.setDesignation(updatedUserVO.getDesignation());
								vo.setUserName(updatedUserVO.getUserName());
								vo.setMobileNO(updatedUserVO.getMobileNO());
								vo.setLocation(updatedUserVO.getLocation());
							}
							
							matchedTimeVO.getPriorityList().add(vo);
						}
						
					}
					
					if(commonMethodsUtilService.isListOrSetValid(finalList)){
						Collections.sort(finalList, new Comparator<AlertTrackingVO>() {
							public int compare(AlertTrackingVO o1,AlertTrackingVO o2) {
								int i =0;
								try {
									SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
									i = sdf.parse(o2.getDate()).compareTo(sdf.parse(o1.getDate()));
								} catch (Exception e) {
									e.printStackTrace();
								}
								return i;
							}
						});
						
						for (AlertTrackingVO dateVO : finalList) {
							if(commonMethodsUtilService.isListOrSetValid(dateVO.getTimeList())){
								Collections.sort(dateVO.getTimeList(), new Comparator<AlertTrackingVO>() {
									public int compare(AlertTrackingVO o1,AlertTrackingVO o2) {
										int i =0;
										try {
											SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
											i = sdf.parse(o2.getDate()).compareTo(sdf.parse(o1.getDate()));
										} catch (Exception e) {
											e.printStackTrace();
										}
										return i;
									}
								});
							}
						}
						
					}
				}
			} catch (Exception e) {
				LOG.error(" Exception Occured in viewAlertHistory() method, Exception - ",e);
			}
			return finalList;
		}
		
		public AlertTrackingVO getMatchedDateVO(List<AlertTrackingVO> voList,String str){
			if(voList != null && voList.size() > 0){
				for (AlertTrackingVO alertTrackingVO : voList) {
					if(alertTrackingVO.getDate().equalsIgnoreCase(str))
						return alertTrackingVO;
				}
			}
			return null;
		}
		
		//sandeep
		
		/*
		 * Hymavathi(non-Javadoc)
		 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getDistrictOfficerAlertsCountView(java.lang.Long)
		 */
	public DistrictOfficeViewAlertVO getDistrictOfficerAlertsCountView(Long userId,String startDate,String endDate, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		
		DistrictOfficeViewAlertVO returnVO = new DistrictOfficeViewAlertVO();
		try{
			Date fromDate = null;
		      Date toDate = null;
		      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		      if(startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0){
		        fromDate = sdf.parse(startDate);
		        toDate = sdf.parse(endDate);
		      }
		      
		      prepareRequiredParameter(printIdsList,electronicIdsList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds); //Setting Parameter
		      
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			returnVO.setLevelValues(levelValues);
			returnVO.setLevelId(levelId);
			List<Object[]> list1 = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOffrDetlsIdAndGovtOfcrId(userId,levelValues,levelId);
			
			List<Long> govtDepDesigOffcrIds = new ArrayList<Long>(0);
			List<Long> govtOffcrIds = new ArrayList<Long>(0);
			if(commonMethodsUtilService.isListOrSetValid(list1)){
				for( Object[]  obj :list1){
					govtDepDesigOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[0]));
					govtOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[1]));
					returnVO.getDeptIds().add(commonMethodsUtilService.getLongValueForObject(obj[3]));//depId
					returnVO.setGovtDeptDesigOffcrIds(govtDepDesigOffcrIds);
					returnVO.setGovtOfficerIds(govtOffcrIds);
					returnVO.setId(commonMethodsUtilService.getLongValueForObject(obj[1]));//officerId
					returnVO.setName(commonMethodsUtilService.getStringValueForObject(obj[2]));//officerName
					returnVO.setDepartmentId(commonMethodsUtilService.getLongValueForObject(obj[3]));//depId
					returnVO.setDeptName(commonMethodsUtilService.getStringValueForObject(obj[4]));//deptName
					returnVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(obj[5]));//designationId
					returnVO.setDesigName(commonMethodsUtilService.getStringValueForObject(obj[6]));//designationName
					
				}
			}
			
			List<Object[]> myAlertsTodayList = null;
			List<Object[]> myAlertsOverAllList = null;
			if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size()>0 && govtOffcrIds != null && govtOffcrIds.size()>0){
				myAlertsTodayList = alertAssignedOfficerNewDAO.getDistrictOfficerAlertsCount(govtDepDesigOffcrIds,govtOffcrIds,"today",new DateUtilService().getCurrentDateAndTime(),new DateUtilService().getCurrentDateAndTime(),printIdsList,electronicIdsList,calCntrIdList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				myAlertsOverAllList = alertAssignedOfficerNewDAO.getDistrictOfficerAlertsCount(govtDepDesigOffcrIds,govtOffcrIds,"overAll",fromDate,toDate,printIdsList,electronicIdsList,calCntrIdList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				//List<Long> todayAlertIds = alertAssignedOfficerNewDAO.getDistrictOfficerAlertsIds(govtDepDesigOffcrIds,govtOffcrIds,"today");
				//List<Long> overAllAlertIds = alertAssignedOfficerNewDAO.getDistrictOfficerAlertsIds(govtDepDesigOffcrIds,govtOffcrIds,"overAll");
				// My alerts Status wise count
				if(myAlertsOverAllList != null && myAlertsOverAllList.size() > 0)
				setStatusWiseCount( myAlertsOverAllList, returnVO,"myAlerts",Long.valueOf(myAlertsTodayList.size()));
			}
			
				
			
			if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size()>0 && govtOffcrIds != null && govtOffcrIds.size()>0){
				myAlertsTodayList = null;
				myAlertsOverAllList = null;
				myAlertsTodayList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCount(govtDepDesigOffcrIds,govtOffcrIds,"today","mySubTasks",new DateUtilService().getCurrentDateAndTime(),new DateUtilService().getCurrentDateAndTime(),printIdsList,electronicIdsList,calCntrIdList,socialMediaTypeIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				myAlertsOverAllList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCount(govtDepDesigOffcrIds,govtOffcrIds,"overAll","mySubTasks",fromDate,toDate,printIdsList,electronicIdsList,calCntrIdList,socialMediaTypeIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				//List<Long> todayMySubTaskAlertIds = govtAlertSubTaskDAO.getDistrictOfficerSubTasksAlertIds(govtDepDesigOffcrIds,govtOffcrIds,"today","mySubTasks");
				//List<Long> overAllMySubTaskAlertIds = govtAlertSubTaskDAO.getDistrictOfficerSubTasksAlertIds(govtDepDesigOffcrIds,govtOffcrIds,"overAll","mySubTasks");
				// My SubTasks Status wise count
				
				if(myAlertsOverAllList != null && myAlertsOverAllList.size() > 0)
				setStatusWiseCount( myAlertsOverAllList, returnVO,"mySubTasks",Long.valueOf(myAlertsTodayList.size()));
			}
			
			
			
			if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size()>0 && govtOffcrIds != null && govtOffcrIds.size()>0){
				myAlertsTodayList = null;
				myAlertsOverAllList = null;
				myAlertsTodayList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCount(govtDepDesigOffcrIds,govtOffcrIds,"today","myAssignedSubTasks",new DateUtilService().getCurrentDateAndTime(),new DateUtilService().getCurrentDateAndTime(),printIdsList,electronicIdsList,calCntrIdList,socialMediaTypeIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				myAlertsOverAllList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCount(govtDepDesigOffcrIds,govtOffcrIds,"overAll","myAssignedSubTasks",fromDate,toDate,printIdsList,electronicIdsList,calCntrIdList,socialMediaTypeIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);

				//List<Long> todayMyAssSubTaskAlertIds = govtAlertSubTaskDAO.getDistrictOfficerSubTasksAlertIds(govtDepDesigOffcrIds,govtOffcrIds,"today","myAssignedSubTasks");
				//List<Long> overAllMyAssSubTaskAlertIds = govtAlertSubTaskDAO.getDistrictOfficerSubTasksAlertIds(govtDepDesigOffcrIds,govtOffcrIds,"overAll","myAssignedSubTasks");
				// My Assigned SubTasks Status wise count
				if(myAlertsOverAllList != null && myAlertsOverAllList.size() > 0)
				setStatusWiseCount( myAlertsOverAllList, returnVO,"myAssignedSubTasks",Long.valueOf(myAlertsTodayList.size()));
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in AlertManagementSystemService of  getDistrictOfficerAlertsCountView() ", e);
		}
		return returnVO;
	}
	
	public void setStatusWiseCount(List<Object[]> myAlertsOverAllList,DistrictOfficeViewAlertVO returnVO,String type,Long todayCount){
		try{
			Map<Long,DistrictOfficeViewAlertVO> myAlertsStatusMap = new HashMap<Long,DistrictOfficeViewAlertVO>();
			
			if(commonMethodsUtilService.isListOrSetValid(myAlertsOverAllList)){
				for (Object[] objects : myAlertsOverAllList) {
					DistrictOfficeViewAlertVO vo = myAlertsStatusMap.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
					if(vo == null){
						vo = new DistrictOfficeViewAlertVO();
						vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
						vo.setColor(commonMethodsUtilService.getStringValueForObject(objects[3]));
						myAlertsStatusMap.put(commonMethodsUtilService.getLongValueForObject(objects[0]), vo);
					}
					vo.setCount(vo.getCount()+commonMethodsUtilService.getLongValueForObject(objects[2]));
				}
			}
			Long myAlertsOverAllCnt = 0l;
			if(commonMethodsUtilService.isMapValid(myAlertsStatusMap)){
				for (Map.Entry<Long,DistrictOfficeViewAlertVO> entry : myAlertsStatusMap.entrySet()) {
					DistrictOfficeViewAlertVO vo = entry.getValue();
					if(vo != null){
						myAlertsOverAllCnt = myAlertsOverAllCnt+vo.getCount();
						if(type != null && type.equalsIgnoreCase("myAlerts")){
							returnVO.getList1().add(vo);
							returnVO.getList1().get(0).setOverAllCnt(myAlertsOverAllCnt);
						}else if(type != null && type.equalsIgnoreCase("mySubTasks")){
							returnVO.getList2().add(vo);
							returnVO.getList2().get(0).setOverAllCnt(myAlertsOverAllCnt);
						}else if(type != null && type.equalsIgnoreCase("myAssignedSubTasks")){
							returnVO.getList3().add(vo);
							returnVO.getList3().get(0).setOverAllCnt(myAlertsOverAllCnt);
						}
					}
				}
			}
			
			if(type != null && type.equalsIgnoreCase("myAlerts")){
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getList1())){
					//returnVO.getList1().get(0).getTodayAlertIds().addAll(todayAlertIds);
					//returnVO.getList1().get(0).getOverAllAlertIds().addAll(overAllAlertId);
					returnVO.getList1().get(0).setTodayCount(todayCount);
					for (DistrictOfficeViewAlertVO vo : returnVO.getList1()) {
						vo.setPerc(calculatePercantage(vo.getCount(),vo.getOverAllCnt()));
					}
				}
			}else if(type != null && type.equalsIgnoreCase("mySubTasks")){
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getList2())){
					//returnVO.getList2().get(0).getTodayAlertIds().addAll(todayAlertIds);
					//returnVO.getList2().get(0).getOverAllAlertIds().addAll(overAllAlertId);
					returnVO.getList2().get(0).setTodayCount(todayCount);
					for (DistrictOfficeViewAlertVO vo : returnVO.getList2()) {
						vo.setPerc(calculatePercantage(vo.getCount(),vo.getOverAllCnt()));
					}
				}
			}else if(type != null && type.equalsIgnoreCase("myAssignedSubTasks")){
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getList3())){
					returnVO.getList3().get(0).setTodayCount(todayCount);
					//returnVO.getList3().get(0).getTodayAlertIds().addAll(todayAlertIds);
					//returnVO.getList3().get(0).getOverAllAlertIds().addAll(overAllAlertId);
					for (DistrictOfficeViewAlertVO vo : returnVO.getList3()) {
						vo.setPerc(calculatePercantage(vo.getCount(),vo.getOverAllCnt()));
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in AlertManagementSystemService of  getDistrictOfficerAlertsCountView() ", e);
		}
	}
	/*
	 * Santosh(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getDeptListForUser(java.lang.Long)
	 */
	public List<IdAndNameVO> getDeptListForUser(Long userId){   
		try{
			List<IdAndNameVO> resultList = new ArrayList<IdAndNameVO>();
			List<Object[]> deptList = govtAlertDepartmentLocationNewDAO.getDeptIdAndNameForUserAccessLevel(userId);
			setDataToList(deptList,resultList);
			return resultList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getDeptListForUser() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public List<IdAndNameVO> getSocialMediaTypeList(){   
		try{
			List<IdAndNameVO> resultList = new ArrayList<IdAndNameVO>();
			List<Object[]> rtrnObjList = alertDAO.getAllSocialMediaType();
			setDataToList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getSocialMediaTypeList() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public List<IdAndNameVO> getAlertCallCenterType(){   
		try{
			List<IdAndNameVO> resultList = new ArrayList<IdAndNameVO>();
			List<Object[]> rtrnObjList = alertDAO.getAlertCallCenterType();
			setDataToList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getAlertCallCenterType() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public List<IdAndNameVO> getAlertSeverity(){   
		try{
			List<IdAndNameVO> resultList = new ArrayList<IdAndNameVO>();
			List<Object[]> rtrnObjList = alertSeverityDAO.getAlertSeverity();
			setDataToList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getAlertSeverity() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public List<IdAndNameVO> getAlertStatus(){   
		try{
			List<IdAndNameVO> resultList = new ArrayList<IdAndNameVO>();
			List<Object[]> rtrnObjList = alertAssignedOfficerNewDAO.getDataAvailableAlertStatus();
			setDataToList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getAlertStatus() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public List<IdAndNameVO> getSubTaskAlertStatus(){   
		try{
			List<IdAndNameVO> resultList = new ArrayList<IdAndNameVO>();
			List<Object[]> rtrnObjList = govtAlertSubTaskDAO.getDataAvailableSubTaskStatus();
			setDataToList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getSubTaskAlertStatus() method of AlertManagementSystemService",e);
		}
		return null;
	}
    public void setDataToList(List<Object[]> objList,List<IdAndNameVO> resultList){
    	try{
    		if(objList != null && objList.size() > 0){  
				for(Object[] param : objList){
				  IdAndNameVO	idAndNameVO = new IdAndNameVO();
					idAndNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					idAndNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					resultList.add(idAndNameVO);
				}
			}
    	}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setDataToList() method of AlertManagementSystemService",e);
    	}
    }
	public String assigningAlertToOfficer(final AlertAssigningVO inputvo){
		String status = null;
		try {
			status = (String)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					
					Long oldDeptId = null;
					Long oldAlertStatusId = null;
					//Alert Status Updation
					Alert alert = alertDAO.get(inputvo.getAlertId());
					
					oldDeptId = alert.getGovtDepartmentId();
					oldAlertStatusId = alert.getAlertStatusId();
					
					alert.setAlertStatusId(2l);
					alert.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
					alert.setUpdatedBy(inputvo.getUserId());
					if(inputvo.getMainDepartmentId() !=null){
						alert.setGovtDepartmentId(inputvo.getMainDepartmentId());
					}
					
					alert = alertDAO.save(alert);
					
					//Get Department Designation Officer Ids
					Long desigOfficerId = null;
					List<GovtDepartmentDesignationOfficerNew> govtDepartmentDesignationOfficerList = govtDepartmentDesignationOfficerDetailsNewDAO.getDesignationOfficerIdsNew(inputvo.getLevelId(), inputvo.getLevelValue(), inputvo.getDesignationId(),
							inputvo.getGovtOfficerId());
					if(govtDepartmentDesignationOfficerList != null && !govtDepartmentDesignationOfficerList.isEmpty())
						desigOfficerId = govtDepartmentDesignationOfficerList.get(0).getGovtDepartmentDesignationOfficerId();
					
					//Officer Assigning
					AlertAssignedOfficerNew alertAssignedOfficer = new AlertAssignedOfficerNew();
					alertAssignedOfficer.setAlertId(inputvo.getAlertId());
					alertAssignedOfficer.setGovtDepartmentDesignationOfficerId(desigOfficerId);
					alertAssignedOfficer.setGovtOfficerId(inputvo.getGovtOfficerId() !=null ? (Long)inputvo.getGovtOfficerId():null);
					alertAssignedOfficer.setInsertedBy(inputvo.getUserId());
					alertAssignedOfficer.setUpdatedBy(inputvo.getUserId());
					alertAssignedOfficer.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
					alertAssignedOfficer.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
					alertAssignedOfficer.setAlertStatusId(2l);
					alertAssignedOfficer.setIsDeleted("N");
					alertAssignedOfficer.setIsApproved("Y");
					//alertAssignedOfficer.setGovtDepartmentId(alert.getGovtDepartmentId() !=null ? alert.getGovtDepartmentId().longValue():null);
					
					alertAssignedOfficer.setGovtDepartmentId(govtDepartmentDesignationOfficerList !=null && 
							govtDepartmentDesignationOfficerList.size()>0 ? govtDepartmentDesignationOfficerList.get(0).getGovtDepartmentDesignation().getGovtDepartmentId():null);
					
					//check whether the alert is asigned to somebody or not, if already assigned delete that assignment
					List<Long> assingedIdsList = alertAssignedOfficerNewDAO.getAssignedDtls(inputvo.getAlertId());
					
					AlertAssignedOfficerNew alertAssignedOfficerNew = new AlertAssignedOfficerNew();
					
					if(commonMethodsUtilService.isListOrSetValid(assingedIdsList)){//assingedId != null){
						for (Long assingedId : assingedIdsList) {
							AlertAssignedOfficerNew alertAssignedOfficer2 = new AlertAssignedOfficerNew();
							alertAssignedOfficer2 = alertAssignedOfficerNewDAO.get(assingedId);
							alertAssignedOfficer2.setAlertId(inputvo.getAlertId());
							alertAssignedOfficer2.setGovtDepartmentDesignationOfficerId(desigOfficerId);
							alertAssignedOfficer2.setGovtOfficerId(inputvo.getGovtOfficerId() !=null ? (Long)inputvo.getGovtOfficerId():null);
							alertAssignedOfficer2.setInsertedBy(inputvo.getUserId());
							alertAssignedOfficer2.setUpdatedBy(inputvo.getUserId());
							alertAssignedOfficer2.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
							alertAssignedOfficer2.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
							alertAssignedOfficer2.setAlertStatusId(2l);// setting present status of alert 
							alertAssignedOfficer2.setIsDeleted("N");
							alertAssignedOfficer2.setIsApproved("Y");  							
							alertAssignedOfficer2.setGovtDepartmentId(govtDepartmentDesignationOfficerList !=null && 
									govtDepartmentDesignationOfficerList.size()>0 ? govtDepartmentDesignationOfficerList.get(0).getGovtDepartmentDesignation().getGovtDepartmentId():null);
							
							alertAssignedOfficer2 = alertAssignedOfficerNewDAO.save(alertAssignedOfficer2);
							
							//alertAssignedOfficer2.getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getGovtDepartmentDesignationId();
							
							alertAssignedOfficerNew = alertAssignedOfficer2;
						}

						if(oldAlertStatusId !=null && oldAlertStatusId == 8l || oldAlertStatusId == 9l && 
								alert.getAlertCategoryId() !=null && alert.getAlertCategoryId() == 2l || alert.getAlertCategoryId() == 3l){
							
							Long cnpOldDeptId = govtDepartmentDAO.getCNPGovtDepartmentIdForGovtDepartment(oldDeptId);
							Long cnpChangedDeptId = govtDepartmentDAO.getCNPGovtDepartmentIdForGovtDepartment(inputvo.getMainDepartmentId());
							
							try{
								String articleStatus = changeCNPDepartment(alert.getAlertCategoryId(),alert.getAlertCategoryTypeId(),cnpChangedDeptId,cnpOldDeptId);
							}catch (Exception e) {
								e.printStackTrace();
							}
								
						}
						
					}else{
						alertAssignedOfficerNew = alertAssignedOfficerNewDAO.save(alertAssignedOfficer);
					}
					
					
					//Officer Assigning Tracking
					AlertAssignedOfficerTrackingNew alertAssignedOfficerTracking = new AlertAssignedOfficerTrackingNew();
					alertAssignedOfficerTracking.setAlertAssignedOfficerId(alertAssignedOfficerNew.getAlertAssignedOfficerId());
					alertAssignedOfficerTracking.setAlertId(inputvo.getAlertId());
					alertAssignedOfficerTracking.setGovtDepartmentDesignationOfficerId(desigOfficerId);
					alertAssignedOfficerTracking.setGovtOfficerId(inputvo.getGovtOfficerId());
					alertAssignedOfficerTracking.setInsertedBy(inputvo.getUserId());
					alertAssignedOfficerTracking.setUpdatedBy(inputvo.getUserId());
					alertAssignedOfficerTracking.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
					alertAssignedOfficerTracking.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
					alertAssignedOfficerTracking.setAlertStatusId(2l);
					alertAssignedOfficerTracking.setGovtAlertActionTypeId(1l);
					alertAssignedOfficerTracking.setIsApproved("Y");
					alertAssignedOfficerTracking.setGovtDepartmentId(govtDepartmentDesignationOfficerList !=null && 
							govtDepartmentDesignationOfficerList.size()>0 ? govtDepartmentDesignationOfficerList.get(0).getGovtDepartmentDesignation().getGovtDepartmentId():null);
					alertAssignedOfficerTracking.setAlertSeviorityId(alert.getAlertSeverityId());
					
					alertAssignedOfficerTracking = alertAssignedOfficerTrackingNewDAO.save(alertAssignedOfficerTracking);
					
					/* SMS sending while assigning a new alert to any officer */
					
					List<String> mobileNos = govtOfficerNewDAO.getOfficerDetailsByOfficerId(inputvo.getGovtOfficerId());
					List<Long> userIdsList = govtDepartmentDesignationOfficerDetailsNewDAO.getuserIdDtlsForDesignationOfficerId(desigOfficerId);
					if(commonMethodsUtilService.isListOrSetValid(userIdsList)){
						Long assignedToUserID = userIdsList.get(0);
						if(mobileNos != null && mobileNos.size() > 0 && mobileNos.get(0).trim().length() > 0 && !mobileNos.get(0).trim().isEmpty()){
							 sendSMSTOAlertAssignedOfficer(inputvo.getDesignationId(),alertAssignedOfficerNew.getGovtOfficerId(),mobileNos!= null ? mobileNos.get(0):null,alert.getAlertId(),alertAssignedOfficerTracking.getGovtAlertActionTypeId(),assignedToUserID,"","",inputvo.getUserId());	
						}
					}
					
					return "success";
				}
			});
		} catch (Exception e) {
			LOG.error("Error occured assigningAlertToOfficer() method of AlertManagementSystemService",e);
		}
		return status;
	}
	
	public List<IdNameVO> getDepartmentLevels(Long departmentId){
		List<IdNameVO> resultList = new ArrayList<IdNameVO>();
		try {						
			List<Object[]> levelObj = govtDepartmentScopeLevelDAO.getDepartmentLevels(departmentId);
			if(levelObj != null && levelObj.size()>0){
				for (Object[] param : levelObj) {
					IdNameVO VO = new IdNameVO();
					   VO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					   VO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					   resultList.add(VO);
				}				 
			}
			
		} catch (Exception e) {
			LOG.error("Error occured getDepartmentLevels(Long departmentId) method of AlertManagementSystemService",e);
		}
		return resultList;
	}
	
	public List<IdNameVO> getParentLevelsOfLevel(Long departmentId,Long levelId){
		
		List<IdNameVO> finalList = new ArrayList<IdNameVO>(0);
		
		try {
			
			Map<Long,IdNameVO> levelMap = new LinkedHashMap<Long, IdNameVO>();
			
			
			List<Object[]> subLevelObj = govtDepartmentScopeLevelDAO.getParentLevelsOfLevel(departmentId,levelId);
			
			if(subLevelObj !=null && subLevelObj.size()>0){
				Set<Long> subLevelIds = new LinkedHashSet<Long>();
				for (Object[] param : subLevelObj) {					
					//if(param[0] !=null && !(param[0].equals(levelId))){	
						
						IdNameVO vo = new IdNameVO();						
						vo.setId((Long)param[0]);
						vo.setName(param[1] !=null ? param[1].toString():"");	
						
						//levelList.add(vo);
						levelMap.put((Long)param[0], vo);
						
						subLevelIds.add((Long)param[0]);
					//}
				}
				
				//0.levelId,1.workLocationId,2.LocationName
				List<Object[]> objList = govtDepartmentWorkLocationDAO.getLevelWiseInfo(departmentId,subLevelIds);
				
				setLocationValuesToMap(objList,levelMap);
				
				if(levelMap !=null && levelMap.size()>0){
					finalList.addAll(levelMap.values());
				}
				
			}
			
			
		} catch (Exception e) {
			LOG.error("Error occured getParentLevelsOfLevel(Long departmentId,Long levelId) method of AlertManagementSystemService",e);
		}
		return finalList;
	}
	
	public void setLocationValuesToMap(List<Object[]> objList,Map<Long,IdNameVO> levelMap){
		try {
			
			if(objList !=null && objList.size()>0){
				for (Object[] obj : objList) {
					
					if(obj[0] !=null){
						if((Long)obj[0] == 1l){
							
							IdNameVO mainVo = levelMap.get(obj[0] !=null ? (Long)obj[0]:0l);
							if(mainVo == null){
								mainVo = new IdNameVO();
								
								mainVo.setId((Long)obj[0]);
								levelMap.put((Long)obj[0], mainVo);
							}
							
							if(obj[1] !=null){
								IdNameVO subVo = new IdNameVO();
								
								subVo.setId((Long)obj[1]);
								subVo.setName(obj[2] !=null ? obj[2].toString():"");
								
								mainVo.getIdnameList().add(subVo);
							}
							
						}
					}
					
					
						
				}
			}
			
		} catch (Exception e) {
			LOG.error("Error occured setLocationValuesToMap() method of AlertManagementSystemService",e);
		}
	}
	
	/*
	 * Swadhin(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getTotalAlertByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId)
	 */
	public List<AlertCoreDashBoardVO> getTotalAlertByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId,Long deptId,List<Long> calCntrIdList,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		LOG.info("Entered in getTotalAlertByStatus() method of AlertManagementSystemService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Preparing Parameter
			
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			List<Object[]> alertList = alertDAO.getTotalAlertByStatusNew(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusId,deptId,calCntrIdList,impactLevelIdList,priorityIdList,alertSourceIdList,printMediaIdList,electronicMediaIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			setAlertDtls(alertCoreDashBoardVOs, alertList); 
			//set Subtask into alert logic 
			List<Long> alertIds = new ArrayList<Long>();
			if(alertList != null && alertList.size() > 0){
				for(Object[] param : alertList){
					alertIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
				}
			}
			//get subtask count.
			List<Object[]> subtaskCountList = null;
			if(alertIds != null && alertIds.size() > 0){
				subtaskCountList = govtAlertSubTaskDAO.getSubTaskCount(alertIds);
			}
			//create a map from alertId and subtask count.
			Map<Long,Long> alertIdAndSubTaskCountMap = new HashMap<Long,Long>();
			if(subtaskCountList != null && subtaskCountList.size() > 0){
				for(Object[] param : subtaskCountList){
					alertIdAndSubTaskCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			if(alertCoreDashBoardVOs != null && alertCoreDashBoardVOs.size() > 0){
				for(AlertCoreDashBoardVO alertCoreDashBoardVO : alertCoreDashBoardVOs){
					if(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()) != null){
						alertCoreDashBoardVO.setSubTaskCount(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()));
					}
				}
			}
			return alertCoreDashBoardVOs;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getTotalAlertByStatus() method of AlertManagementSystemService{}");
		}
		return null;
	}
	/*
	 * Swadhin(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getTotalAlertByOtherStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId)
	 */
	public List<AlertCoreDashBoardVO> getTotalAlertByOtherStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId,Long userId,Long govtDeptScopeId,Long deptId,List<Long> calCntrIdList,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		LOG.info("Entered in getTotalAlertByOtherStatus() method of AlertManagementSystemService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Preparing Parameter
			
			List<Long> levelValuesList = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValuesList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			List<Long> alertIdSet = alertAssignedOfficerNewDAO.getTotalAlertByOtherStatus(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusId,levelId,levelValuesList,govtDeptScopeId,deptId,calCntrIdList,impactLevelIdList,priorityIdList,alertSourceIdList,printMediaIdList,electronicMediaIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			if(alertIdSet != null && alertIdSet.size() > 0){
				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIdSet));
				setAlertDtls(alertCoreDashBoardVOs, list); 
			}
			//set Subtask into alert logic 
			//get subtask count.
			List<Object[]> subtaskCountList = null;
			if(alertIdSet != null && alertIdSet.size() > 0){
				subtaskCountList = govtAlertSubTaskDAO.getSubTaskCount(alertIdSet);
			}
			//create a map from alertId and subtask count.
			Map<Long,Long> alertIdAndSubTaskCountMap = new HashMap<Long,Long>();
			if(subtaskCountList != null && subtaskCountList.size() > 0){
				for(Object[] param : subtaskCountList){
					alertIdAndSubTaskCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			if(alertCoreDashBoardVOs != null && alertCoreDashBoardVOs.size() > 0){
				for(AlertCoreDashBoardVO alertCoreDashBoardVO : alertCoreDashBoardVOs){
					if(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()) != null){
						alertCoreDashBoardVO.setSubTaskCount(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()));
					}
				}
			}
			return alertCoreDashBoardVOs;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getTotalAlertByOtherStatus() method of AlertManagementSystemService{}");
		}
		return null;  
	}
	/*
	 * Teja(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getDistrictLevelDeptWiseFilterView(java.lang.Long,java.lang.String,java.lang.String,java.lang.int,java.lang.int)
	 */
	public  List<AlertVO> getDistrictLevelDeptWiseFilterView(Long userId,String startDateStr,String fromDateStr,String type){
		List<AlertVO> finalVoList = new ArrayList<AlertVO>(0);
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(startDateStr != null && startDateStr.trim().length() > 0 && fromDateStr != null && fromDateStr.trim().length() > 0){
				fromDate = sdf.parse(startDateStr);
				toDate = sdf.parse(fromDateStr);
			}
			List<Long> levelValues = new ArrayList<Long>();    
			Long scopeId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					scopeId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			List<Object[]> scopeDetlsLst = govtDepartmentScopeDAO.getgovtDepatScopeDetails(scopeId);
			Map<Long, List<Long>> deptlevelmap = new HashMap<Long, List<Long>>(0);
			List<Object[]> levelLst = govtDepartmentScopeLevelDAO.govtDepartmentScopeLevelDetails(scopeId);
			if(levelLst != null && levelLst.size() > 0){
				for (Object[] objects : levelLst) {
					if(deptlevelmap.get((Long) objects[0]) == null){
						List<Long> scopeIds = new ArrayList<Long>(0);
						scopeIds.add((Long) objects[1]);
						deptlevelmap.put((Long) objects[0],scopeIds);
					}else{
						deptlevelmap.get((Long) objects[0]).add((Long) objects[1]);
					}
				}
			}
			if(type.equalsIgnoreCase("alert")){
				//deptId-0,deptNmae-1,scopeId-2,levelName-3,count-4
				List<Object[]> levelWiseCntsLst = alertAssignedOfficerNewDAO.getAlertAssignCountsForDeptWiseDetails(fromDate, toDate);
				if(levelWiseCntsLst != null && levelWiseCntsLst.size() > 0){
					setDistrictLevelDeptWiseFilterView(levelWiseCntsLst,finalVoList,scopeDetlsLst,deptlevelmap);
				}
			}else if(type.equalsIgnoreCase("subTask")){
				//deptId-0,deptNmae-1,scopeId-2,levelName-3,count-4
				List<Object[]> subLevelWiseCntsLst = govtAlertSubTaskDAO.getSubTaskAlertAssignCountsForDeptWiseDetails(fromDate, toDate);
				if(subLevelWiseCntsLst != null && subLevelWiseCntsLst.size() > 0){
					setDistrictLevelDeptWiseFilterView(subLevelWiseCntsLst,finalVoList,scopeDetlsLst,deptlevelmap);
				}
			}
			if(finalVoList != null && finalVoList.size() > 0){
				for (AlertVO vo : finalVoList) {
					if(vo.getSubList2() != null && vo.getSubList2().size() > 0){
						for (AlertVO subVo : vo.getSubList2()){
							   vo.setCategoryCount(vo.getCategoryCount()+subVo.getCount());
						}
					}
				}
			}
		}catch(Exception e){
			LOG.error(" Exception Occured in getDistrictLevelDeptWiseFilterView() method, Exception - ",e);
		}
		return finalVoList;
	}
	public void setDistrictLevelDeptWiseFilterView(List<Object[]> objLst,List<AlertVO> finalVOList,List<Object[]> scopeDetlsLst,Map<Long, List<Long>> deptlevelmap){
		if(objLst != null && objLst.size() > 0){
			for (Object[] obj : objLst) {
				AlertVO matchedDeptVO = getmatchedVo(finalVOList,(Long)obj[0]);
				if(matchedDeptVO == null){
					matchedDeptVO = new AlertVO(); 
					matchedDeptVO.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
					matchedDeptVO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
					setScopeDetailsSkeleton(scopeDetlsLst, matchedDeptVO);
					List<Long> scopeIdsList = deptlevelmap.get((Long)obj[0]);
					if(scopeIdsList != null){
						if(scopeIdsList.contains((Long)obj[2])){
							AlertVO matchedScopeVO = getmatchedVo(matchedDeptVO.getSubList2(),(Long)obj[2]);
							if(matchedScopeVO != null){
								matchedScopeVO.setCount((Long) obj[4]);
							}
						}
					}
					finalVOList.add(matchedDeptVO);
				}else{
						AlertVO matchedScopeVo = getmatchedVo(matchedDeptVO.getSubList2(),(Long)obj[2]);
						if(matchedScopeVo != null){
							matchedScopeVo.setCount((Long) obj[4]);
						}
				   }
			   }
		   }
	  }
	public void setScopeDetailsSkeleton(List<Object[]> scopeDetlsLst,AlertVO finalVo){
		if(scopeDetlsLst != null && scopeDetlsLst.size() > 0){
			for (Object[] objects : scopeDetlsLst){
				AlertVO vo = new AlertVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
				finalVo.getSubList2().add(vo);
		   }
		}
	}
	public AlertVO getmatchedVo(List<AlertVO> finalVOList,Long deptId){
		if(finalVOList != null && finalVOList.size() > 0){
			for (AlertVO alertVO : finalVOList){
				if(alertVO.getId() != null && alertVO.getId().equals(deptId)){
					return alertVO;
				}
			}
		}
		return null;
	}

	public List<AlertCoreDashBoardVO> groupAlertsTimeWise(List<AlertCoreDashBoardVO> alertCoreDashBoardVOs){
		try{
			Map<Long,ArrayList<AlertCoreDashBoardVO>> groupIdThenAlertListMap = new LinkedHashMap<Long,ArrayList<AlertCoreDashBoardVO>>();
			ArrayList<AlertCoreDashBoardVO> dashBoardVOs = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			if(alertCoreDashBoardVOs != null && alertCoreDashBoardVOs.size() > 0){
				for(AlertCoreDashBoardVO  alertCoreDashBoardVO : alertCoreDashBoardVOs){
					
					String alertDateStr = alertCoreDashBoardVO.getCreatedDate();
					Date alertDate = sdf.parse(alertDateStr);
					Long group = getDateGroup(alertDate);
					dashBoardVOs = groupIdThenAlertListMap.get(group);
					if(dashBoardVOs == null){
						dashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
						groupIdThenAlertListMap.put(group, dashBoardVOs);
					}
					dashBoardVOs.add(alertCoreDashBoardVO);
				}
			}
			List<AlertCoreDashBoardVO> list = new ArrayList<AlertCoreDashBoardVO>();
			AlertCoreDashBoardVO altVO = null;
			DateUtilService dateUtilService = new DateUtilService();
			Date todayDate = dateUtilService.getCurrentDateAndTime();
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH) + 1;
			String monthStr = getMonthInString(month);
			String dateStr0 = sdf1.format(todayDate);	
			if(groupIdThenAlertListMap != null && groupIdThenAlertListMap.size() > 0){
				for(Entry<Long,ArrayList<AlertCoreDashBoardVO>> entry : groupIdThenAlertListMap.entrySet()){
					altVO = new AlertCoreDashBoardVO();
					if(entry.getKey().longValue() == 1L){
						altVO.setId(entry.getKey());
						altVO.setName("Today Alerts");
						altVO.setCreatedDate(dateStr0);
						altVO.setSubList(entry.getValue());
					}else if(entry.getKey().longValue() == 2L){
						altVO.setId(entry.getKey());
						altVO.setName("Week Alerts");
						altVO.setCreatedDate("Past 7 Days");
						altVO.setSubList(entry.getValue());
					}else if(entry.getKey().longValue() == 3L){
						altVO.setId(entry.getKey());
						altVO.setName("Month Alerts");
						altVO.setCreatedDate(monthStr+" "+year);
						altVO.setSubList(entry.getValue());
					}else if(entry.getKey().longValue() == 4L){
						altVO.setId(entry.getKey());
						altVO.setName("Past Alerts");
						altVO.setCreatedDate("Before "+ monthStr+" "+year);
						altVO.setSubList(entry.getValue());
					}
					
					list.add(altVO);
				}
			}
			if(list != null && list.size() > 0){
				Collections.sort(list, alertDateWiseAscOrder);
			}
				
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	public static Comparator<AlertCoreDashBoardVO> alertDateWiseAscOrder = new Comparator<AlertCoreDashBoardVO>() {
    	public int compare(AlertCoreDashBoardVO obj2, AlertCoreDashBoardVO obj1) {
    	Long vo2 = obj2.getId();
    	Long vo1 = obj1.getId();
    	//descending order of percantages.
    	 return vo2.compareTo(vo1);
    	}
     };
	public Long getDateGroup(Date alertDate){
		try{
			DateUtilService dateUtilService = new DateUtilService();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			//today date
			Date todayDate = dateUtilService.getCurrentDateAndTime();
			String dateStr0 = sdf.format(todayDate);				 
			Long milisecToday = sdf.parse(dateStr0).getTime();
			//date before 7 days
			Date sevevDaysBefore = dateUtilService.getDateBeforeNDays(6);
			String dateStr1 = sdf.format(sevevDaysBefore);
			Long milisecSevevDaysBefore = sdf.parse(dateStr1).getTime();
			//start date of month.
			Date startDateOfMonth = dateUtilService.getStartDateOfMonth();
			String dateStr2 = sdf.format(startDateOfMonth);
			Long milisecStartDateOfMonth = sdf.parse(dateStr2).getTime();
			//last day of privious month.
			Date lastDayOfPreviousMonth = dateUtilService.getLastDayOfPreiviousMonth();
			String dateStr3 = sdf.format(lastDayOfPreviousMonth);
			Long milisecLastDayOfPreviousMonth = sdf.parse(dateStr3).getTime();
			
			if(alertDate.getTime() == milisecToday.longValue()){
				return 1L;
			}else if(alertDate.getTime() <= milisecToday.longValue() && alertDate.getTime() >= milisecSevevDaysBefore.longValue()){
				return 2L;
			}else if(alertDate.getTime() <= milisecToday.longValue() && alertDate.getTime() >= milisecStartDateOfMonth.longValue()){
				return 3L;
			}else{
				return 4L;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public  List<AlertVO> getDistrictLevelDeptWiseLocationLevelView(Long userId,String startDateStr,String fromDateStr,String type,List<Long> deptIds,String sortingType,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,String resultType,List<Long> deptScopeIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		List<AlertVO> finalVoList = new ArrayList<AlertVO>(0);
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(startDateStr != null && startDateStr.trim().length() > 0 && fromDateStr != null && fromDateStr.trim().length() > 0){
				fromDate = sdf.parse(startDateStr);
				toDate = sdf.parse(fromDateStr);
			}
			
			prepareRequiredParameter(printIdsList,electronicIdsList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
			
			List<Long> levelValues = new ArrayList<Long>();    
			Long scopeId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					scopeId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
		   //scopeDetlsLst = govtDepartmentScopeDAO.getGovtDepartmenttScopeDetails(scopeId);
	
			if(type.equalsIgnoreCase("alert")){
				//deptId-0,deptname-1,scopeId-2,level-3,,color-4,count-4
				List<Object[]> deptWiseLocationLvlLList = alertAssignedOfficerNewDAO.getDistrictLevelDeptWiseLocationLevelView(fromDate, toDate,deptIds,printIdsList,electronicIdsList,calCntrIdList,scopeId,levelValues,resultType,deptScopeIds,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				if(deptWiseLocationLvlLList != null && deptWiseLocationLvlLList.size() > 0){
					setDeptWiseGraphView(deptWiseLocationLvlLList,finalVoList);
				}
			}else if(type.equalsIgnoreCase("subTask")){
				List<Object[]> deptWiseSubTaskList = govtAlertSubTaskDAO.getDistrictLevelDeptWiseLocationLevelViewForSubtask(fromDate, toDate, deptIds,printIdsList,electronicIdsList,calCntrIdList,scopeId,levelValues,resultType,deptScopeIds,socialMediaTypeIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				if(deptWiseSubTaskList != null && deptWiseSubTaskList.size() > 0){
					setDeptWiseGraphView(deptWiseSubTaskList,finalVoList);
				}
			}
			if(finalVoList != null && finalVoList.size() > 0){
				for (AlertVO finalVo : finalVoList) {
					if(finalVo.getSubList2() != null && finalVo.getSubList2().size() > 0){
						Long totalCount = 0l;
						for (AlertVO subVo : finalVo.getSubList2()) {
							totalCount = totalCount+subVo.getCount();
						}
						finalVo.setCount(totalCount);
					}
				}
				
				if(sortingType != null && !sortingType.trim().isEmpty()){
					sortListBasedRequiredType(finalVoList,sortingType);
				}
			   }
		} catch (Exception e) {
			LOG.error(" Exception Occured in getDistrictLevelDeptWiseLocationLevelView() method, Exception - ",e);
		}
		return finalVoList;
	}
	public  List<AlertVO> getDistrictLevelDeptWiseStatusOverView(Long userId,String startDateStr,String fromDateStr,String type,List<Long> deptIds,String sortingType,Long levelId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		List<AlertVO> finalVoList = new ArrayList<AlertVO>(0);
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(startDateStr != null && startDateStr.trim().length() > 0 && fromDateStr != null && fromDateStr.trim().length() > 0){
				fromDate = sdf.parse(startDateStr);
				toDate = sdf.parse(fromDateStr);
			}
			
			prepareRequiredParameter(printIdsList,electronicIdsList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
			
   		  //statuisId-0,status-1,color-2,shortName-3
   			List<Object[]> statusList = null;
   			List<Long> levelValues = new ArrayList<Long>();    
			Long scopeId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					scopeId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			if(type.equalsIgnoreCase("alert")){
				//deptId-0,deptName-1,statusId-2,statusName-3,color-4,Count-5
				List<Object[]> deptWiseStatusViewLst = alertAssignedOfficerNewDAO.getDistrictLevelDeptWiseStatusOverView(fromDate, toDate,scopeId,deptIds,levelId,printIdsList,electronicIdsList,calCntrIdList,levelValues,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				if(deptWiseStatusViewLst != null && deptWiseStatusViewLst.size() > 0){
					//statusList = alertDepartmentStatusDAO.getAllStatuses(getStatusIds(deptWiseStatusViewLst));
					setDeptWiseGraphView(deptWiseStatusViewLst,finalVoList);
				}
			}else if(type.equalsIgnoreCase("subTask")){
				//deptId-0,deptName-1,statusId-2,statusName-3,color-4,Count-5
				List<Object[]> deptWiseSubtaskLst = govtAlertSubTaskDAO.getDistrictLevelDeptWiseStatusOverViewForSubTask(fromDate, toDate, scopeId,deptIds,levelId,printIdsList,electronicIdsList,calCntrIdList,levelValues,socialMediaTypeIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				if(deptWiseSubtaskLst != null && deptWiseSubtaskLst.size() > 0){
					//statusList = alertSubTaskStatusDAO.getAllSubTaskStatus();
					setDeptWiseGraphView(deptWiseSubtaskLst,finalVoList);
				}
			}
			if(finalVoList != null && finalVoList.size() > 0){
				for (AlertVO finalVo : finalVoList) {
					if(finalVo.getSubList2() != null && finalVo.getSubList2().size() > 0){
						Long totalCount = 0l;
						for (AlertVO subVo : finalVo.getSubList2()) {
							totalCount = totalCount+subVo.getCount();
						}
						finalVo.setCount(totalCount);
					}
				}
				if(sortingType != null && !sortingType.trim().isEmpty()){
					sortListBasedRequiredType(finalVoList,sortingType);
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception Occured in getDistrictLevelDeptWiseStatusOverView() method, Exception - ",e);
		}
		return finalVoList;
	}
	public List<Long> getStatusIds(List<Object[]> deptWiseStatusViewLst){
		List<Long> statusIds = new ArrayList<Long>();
		try{
			if(deptWiseStatusViewLst != null && deptWiseStatusViewLst.size() > 0){
				for(Object[] param:deptWiseStatusViewLst){
					statusIds.add(commonMethodsUtilService.getLongValueForObject(param[2]));
				}
			}
		}catch(Exception e){
			LOG.error(" Exception Occured in getStatusIds() method, Exception - ",e);
		}
		return statusIds;
	}
	public void setDeptWiseGraphView(List<Object[]> objList,List<AlertVO> finalVoList){
		if(objList != null && objList.size() >0){
			for (Object[] obj : objList) {
				AlertVO matchedDeptVo = getmatchedVo(finalVoList, (Long)obj[0]);
				if(matchedDeptVo == null){
					matchedDeptVo = new AlertVO();
					matchedDeptVo.setSubList2(getTemplate(objList));
					matchedDeptVo.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
					matchedDeptVo.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
				
					if(matchedDeptVo.getSubList2() != null && matchedDeptVo.getSubList2().size() > 0){
						for (AlertVO  vo : matchedDeptVo.getSubList2()) {
							if(vo.getId().equals((Long)obj[2])){
								vo.setCount(commonMethodsUtilService.getLongValueForObject(obj[5]));
							}
						}
						
					}
					finalVoList.add(matchedDeptVo);
					
				}else{
					AlertVO matchedStatusVo = getmatchedVo(matchedDeptVo.getSubList2(),(Long)obj[2]);
					if(matchedStatusVo == null){
						matchedStatusVo = new AlertVO();
						matchedStatusVo.setId(commonMethodsUtilService.getLongValueForObject(obj[2]));
						matchedStatusVo.setName(commonMethodsUtilService.getStringValueForObject(obj[3]));
						matchedStatusVo.setColor(commonMethodsUtilService.getStringValueForObject(obj[4]));
						matchedStatusVo.setCount(matchedStatusVo.getCount()+commonMethodsUtilService.getLongValueForObject(obj[5]));
						matchedDeptVo.getSubList2().add(matchedStatusVo);
					}else{
						matchedStatusVo.setCount(matchedStatusVo.getCount()+commonMethodsUtilService.getLongValueForObject(obj[5]));
					}
				}
			}
		}
	}
	public List<AlertVO> getGovtDepartmentDetails(Long userId){
		 List<AlertVO> finalVoList = new ArrayList<AlertVO>(0);
		try {
			//List<Object[]> deptList = govtDepartmentDAO.getAllDepartment();
			List<Object[]> rtrnObjLst = govtAlertDepartmentLocationNewDAO.getDeptIdAndNameForUserAccessLevel(userId);
			List<Long> deptLst = new ArrayList<Long>();
			 if(rtrnObjLst != null && rtrnObjLst.size() > 0){
				 for(Object[] param:rtrnObjLst){
					 deptLst.add(commonMethodsUtilService.getLongValueForObject(param[0]));
				 }
			 }
			    Long levelId = 0L;
			    List<Long> levelVaue = new ArrayList<Long>(0);
				List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
				if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
					for(Object[] param : lvlValueAndLvlIdList){
						levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
						levelVaue.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					}
				}
				
			  List<Object[]> deptList = null;
			 if(deptLst != null && deptLst.size() > 0){
				 deptList = alertAssignedOfficerNewDAO.getAllDepartmentHasData(deptLst,levelId,levelVaue);
			 }
			if (deptList != null && deptList.size() > 0) {
			 for (Object[] objects : deptList) {
				 AlertVO alertVO = new AlertVO();
				 alertVO.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
				 alertVO.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));			 
				 finalVoList.add(alertVO);
			 }
			}
		} catch (Exception e) {
			LOG.error(" Exception Occured in getGovtDepartmentDetails() method, Exception - ",e);
		}
		return finalVoList;
	}
	public List<AlertVO> getGovtDeptScopeDetails(List<Long> departmentIds,Long userId){
		List<AlertVO> finalVoList = new ArrayList<AlertVO>(0);
		try {
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			List<Object[]> scopeDetlsLst = null;
			if(departmentIds != null && departmentIds.size() >  0){
				   List<Object[]> rtrnObjList = govtDepartmentScopeLevelDAO.getDeptsChildLevelByParentScope(levelId, departmentIds);//levelId means Access Level 
	      			Set<Long> scopeIdsSet = new HashSet<Long>();
	      			if(rtrnObjList != null && rtrnObjList.size() > 0){
	      				for(Object[] param:rtrnObjList){
	      				   scopeIdsSet.add(commonMethodsUtilService.getLongValueForObject(param[2]));
	      				}
	      				scopeDetlsLst = govtDepartmentScopeLevelDAO.getChildGovtScopeByParentScope(scopeIdsSet,departmentIds);
	      			}
	     	}/*else{
	     		    scopeDetlsLst = govtDepartmentScopeDAO.getGovtDeptScopeDetails(levelId);;	
			}*/
			Map<Long,AlertVO> deptLevelMap = new LinkedHashMap<Long, AlertVO>(0);
			if(scopeDetlsLst != null && scopeDetlsLst.size() > 0 ){
				for (Object[] objects : scopeDetlsLst) {
					AlertVO alertVO = new AlertVO();
					alertVO.setId(commonMethodsUtilService.getLongValueForObject(objects[2]));
					alertVO.setName(commonMethodsUtilService.getStringValueForObject(objects[3]));
					deptLevelMap.put(alertVO.getId(), alertVO);
				}				
			}			
			if(deptLevelMap != null && deptLevelMap.size() > 0){
				finalVoList.addAll(deptLevelMap.values());
				//Sorting list based on department scope id
				Collections.sort(finalVoList, new Comparator<AlertVO>() {
					public int compare(AlertVO o1, AlertVO o2) {
						return o1.getId().compareTo(o2.getId());
					}
				});
			}
		} catch (Exception e) {
			LOG.error(" Exception Occured in getGovtDepartmentDetails() method, Exception - ",e);
		}		
		return finalVoList;
	}
	
	public  List<IdAndNameVO> getSubOrdinateLevels(Long designationId){
		List<IdAndNameVO> finalVoList = new ArrayList<IdAndNameVO>(0);
		try {
			//0-govtDepartmentScopeId,1-levelName
			List<Object[]> scopeList = govtDepartmentDesignationOfficerNewDAO.getSubOrdinateLevels(designationId);
			
			if(scopeList != null && scopeList.size() > 0){
				for (Object[] objects : scopeList) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					finalVoList.add(vo);
				}
		}
	
	}catch (Exception e) {
		LOG.error(" Exception Occured getSubOrdinateLevels() method, Exception - ",e);
	}
	return finalVoList;
		
	}
	
	/*public DistrictOfficeViewAlertVO getAlertDetailsForDistrictOfficer(Long userId,List<Long> departmentIds,Long govtOffcrId,String type,String fromDateStr,
			String toDateStr,Long stateId){
		DistrictOfficeViewAlertVO returnVO = new DistrictOfficeViewAlertVO();
		try{
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			Date todayDate = dateUtilService.getCurrentDateAndTime();
			List<Object[]> todayList = alertAssignedOfficerNewDAO.getAlertDetailsForDistrictOfficer( todayDate,  todayDate,  stateId,  departmentIds, levelId, levelValues, type,null);
			setDateWiseAlertDetails(todayList,"today",returnVO);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(todayDate);
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-6);
			Date last7thDay = cal.getTime();
			List<Object[]> last7dayList = alertAssignedOfficerNewDAO.getAlertDetailsForDistrictOfficer( todayDate,  last7thDay,  stateId,  departmentIds, levelId, levelValues, type,null);
			setDateWiseAlertDetails(last7dayList,"last7days",returnVO);
			
			
			Calendar c = Calendar.getInstance();
		    int year = c.get(Calendar.YEAR);
		    int month = c.get(Calendar.MONTH);
		    int day = 1;
		    c.set(year, month, day);
		    Date fisrtDayOfCurrentMonth = c.getTime();
			List<Object[]> thisMonthList = alertAssignedOfficerNewDAO.getAlertDetailsForDistrictOfficer( fisrtDayOfCurrentMonth,  todayDate,  stateId,  departmentIds, levelId, levelValues, type,null);
			setDateWiseAlertDetails(thisMonthList,"currentMonth",returnVO);
			
			Calendar c1 = Calendar.getInstance();
		    int lastMonth = c1.get(Calendar.MONTH)-1;
		    int numOfDaysInMonth = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
		    int day1 = numOfDaysInMonth-1;
		    c.set(year, month, day);
		    Date lastDayOfLastMonth = c.getTime();
		    List<Object[]> beforeCurntMnthList = alertAssignedOfficerNewDAO.getAlertDetailsForDistrictOfficer( lastDayOfLastMonth,  null,  stateId,  departmentIds, levelId, levelValues, type,"pastData");
			setDateWiseAlertDetails(beforeCurntMnthList,"beforThisMonth",returnVO);
			
		}catch (Exception e) {
			LOG.error("Error occured getAlertDetailsForDistrictOfficer() method of AlertManagementSystemService",e);
		}
		return returnVO;
	}*/
	
	public List<DistrictOfficeViewAlertVO> getSubOrdinateAlertsOverview(Long userId,String fromDateStr,String toDateStr , List<Long> govtScopeIds,List<Long> locationValues,
			List<Long> desigIds,Long priorityId){
		
		List<DistrictOfficeViewAlertVO> returnList = new ArrayList<DistrictOfficeViewAlertVO>();
		
		try{
			
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			Map<Long,DistrictOfficeViewAlertVO> scopWiseMap = new HashMap<Long,DistrictOfficeViewAlertVO>();
			List<Object[]> list = alertAssignedOfficerNewDAO.getSubOrdinateAlertsDetails(userId,fromDate,toDate,govtScopeIds,locationValues,levelId,levelValues,desigIds,priorityId,"totalAlert");
			setScopeDetails(list,scopWiseMap,"totalAlert");
			
			List<Object[]> completedAlerts = alertAssignedOfficerNewDAO.getSubOrdinateAlertsDetails(userId,fromDate,toDate,govtScopeIds,locationValues,levelId,levelValues,desigIds,priorityId,"completedAlerts");
			setScopeDetails(completedAlerts,scopWiseMap,"completedAlerts");
			
			List<Object[]> totalTasks = govtAlertSubTaskDAO.getSubOrdinateTasksDetails(userId,fromDate,toDate,govtScopeIds,locationValues,levelId,levelValues,desigIds,priorityId,"totalTasks");
			setScopeDetails(totalTasks,scopWiseMap,"totalTasks");
			
			List<Object[]> completedTasks = govtAlertSubTaskDAO.getSubOrdinateTasksDetails(userId,fromDate,toDate,govtScopeIds,locationValues,levelId,levelValues,desigIds,priorityId,"completedTasks");
			setScopeDetails(completedTasks,scopWiseMap,"completedTasks");
			
			if(commonMethodsUtilService.isMapValid(scopWiseMap)){
				for(Map.Entry<Long, DistrictOfficeViewAlertVO> entry : scopWiseMap.entrySet()){
					DistrictOfficeViewAlertVO vo = entry.getValue();
					returnList.add(vo);
					if(commonMethodsUtilService.isListOrSetValid(vo.getList1())){
						for(DistrictOfficeViewAlertVO locationVO :vo.getList1()){
							if(commonMethodsUtilService.isListOrSetValid(locationVO.getList2())){
								for(DistrictOfficeViewAlertVO desigVO :locationVO.getList2()){
										if(desigVO.getCount() != null && desigVO.getCompletedCnt() != null)
										{
											desigVO.setAlertsPerc(calculatePercantage(desigVO.getCompletedCnt(),desigVO.getCount()));//alertperc
											desigVO.setOverAllCnt(desigVO.getOverAllCnt()+desigVO.getCount());
										}
										if(desigVO.getTaskCnt() != null && desigVO.getTaskCompletedCnt() != null)
										{
											desigVO.setPerc(calculatePercantage(desigVO.getTaskCompletedCnt(),desigVO.getTaskCnt()));//taskPerc
											desigVO.setOverAllCnt(desigVO.getOverAllCnt()+desigVO.getTaskCnt());
										}
								 }
							}							
						}
					}
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getSubOrdinateAlertsOverview() method of AlertManagementSystemService",e);
		}
		return returnList;
	}
	
	public void setScopeDetails(List<Object[]> list,
			Map<Long,DistrictOfficeViewAlertVO> scopWiseMap,String type){
		
		try{
			if(list != null && list.size() >0){
				for(Object[] obj : list ){
					DistrictOfficeViewAlertVO scopeVO = scopWiseMap.get(commonMethodsUtilService.getLongValueForObject(obj[0]));
					if(scopeVO == null){
						scopeVO = new DistrictOfficeViewAlertVO();
						scopWiseMap.put(commonMethodsUtilService.getLongValueForObject(obj[0]), scopeVO);
					}
					scopeVO.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
					scopeVO.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
					
					DistrictOfficeViewAlertVO locationVO = getMatchVOForSubOrdinate(scopeVO.getList1(),commonMethodsUtilService.getLongValueForObject(obj[5]));
					if(locationVO == null){
						locationVO = new DistrictOfficeViewAlertVO();
						scopeVO.getList1().add(locationVO);
					}
					locationVO.setId(commonMethodsUtilService.getLongValueForObject(obj[5]));
					locationVO.setName(commonMethodsUtilService.getStringValueForObject(obj[6]));
					
					DistrictOfficeViewAlertVO desigVo = getMatchVOForSubOrdinate(locationVO.getList2(),commonMethodsUtilService.getLongValueForObject(obj[2]));
					if(desigVo == null){
						desigVo = new DistrictOfficeViewAlertVO();
						locationVO.getList2().add(desigVo);
					}
					desigVo.setId(commonMethodsUtilService.getLongValueForObject(obj[2]));
					desigVo.setName(commonMethodsUtilService.getStringValueForObject(obj[3]));
					if(type != null && type.equalsIgnoreCase("totalAlert")){
						desigVo.setCount(desigVo.getCount()+commonMethodsUtilService.getLongValueForObject(obj[4]));
					}else if(type != null && type.equalsIgnoreCase("completedAlerts")){
						desigVo.setCompletedCnt(desigVo.getCompletedCnt()+commonMethodsUtilService.getLongValueForObject(obj[4]));
					}else if(type != null && type.equalsIgnoreCase("totalTasks")){
						desigVo.setTaskCnt(desigVo.getTaskCnt()+commonMethodsUtilService.getLongValueForObject(obj[4]));
					}else if(type != null && type.equalsIgnoreCase("completedTasks")){
						desigVo.setTaskCompletedCnt(desigVo.getTaskCompletedCnt()+commonMethodsUtilService.getLongValueForObject(obj[4]));
					}
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setScopeDetails() method of AlertManagementSystemService",e);
		}
	}
	
	public DistrictOfficeViewAlertVO getMatchVOForSubOrdinate(List<DistrictOfficeViewAlertVO> list,Long id){
		try{
			if(list == null || list.size() ==0)
				return null;
			for(DistrictOfficeViewAlertVO VO:list){
				if(VO.getId().equals(id))
				{
					return VO;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getMatchVOForSubOrdinate() method of AlertManagementSystemService{}");
		}
		return null;
	}
	public String  getMonthInString(int month){
		String monthStr = "";
		switch(month){
		case 1:
			monthStr =  "January";
			break;
		case 2:
			monthStr = "February";
			break;
		case 3:
			monthStr = "March";
			break;
		case 4:
			monthStr = "April";
			break;
		case 5:
			monthStr = "May";
			break;
		case 6:
			monthStr = "June";
			break;
		case 7:
			monthStr = "July";
			break;
		case 8:
			monthStr = "August";
			break;
		case 9:
			monthStr = "September";
			break;
		case 10:
			monthStr = "October";
			break;
		case 11:
			monthStr = "November";
			break;
			
		case 12:
			monthStr = "December";
			break;
		}
		return monthStr;
	}
	/*
	 * Swadhin K Lenks
	 * overview
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getStateThenGovtDeptScopeWiseAlertCount(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	public List<AlertCoreDashBoardVO> getStateThenGovtDeptScopeWiseAlertCount(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String sortingType,String order){
		try{
			
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
			if(printIdList != null && printIdList.size() > 0){  
				if(electronicIdList != null && electronicIdList.size() == 0){
					electronicIdList.add(0L);
				}
			}else if(electronicIdList != null && electronicIdList.size() > 0){
				if(printIdList != null && printIdList.size() == 0){
					printIdList.add(0L);
				}
			}/*else{
				electronicIdList.add(0L);
				printIdList.add(0L);
			}*/
			
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			
			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
			List<Long> deptScopeIdList = new ArrayList<Long>();
			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
				for(Object [] param : childDeptScopeIdList){
					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			
			
			List<Object[]> alertList = alertAssignedOfficerNewDAO.getLocationThenGovtDeptScopeWiseAlertCount(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList);
			Map<Long,String> locIdAndLocNameMap = new LinkedHashMap<Long,String>();
			Map<Long,String> lvlIdAndLvlName = new LinkedHashMap<Long,String>();
			Map<Long,String> lvlIdAndColor = new LinkedHashMap<Long,String>();
			Map<Long,LinkedHashMap<Long,Long>> locIdThenLvlIdThenAlertCount = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
			LinkedHashMap<Long,Long> levelIdAndAlertCountMap = null;
			
			if(alertList != null && alertList.size() > 0){
				for(Object[] param : alertList){
					locIdAndLocNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[2]));
					lvlIdAndLvlName.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getStringValueForObject(param[4]));
					lvlIdAndColor.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getStringValueForObject(param[6]));
					
					levelIdAndAlertCountMap = locIdThenLvlIdThenAlertCount.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(levelIdAndAlertCountMap == null){
						levelIdAndAlertCountMap = new LinkedHashMap<Long,Long>();
						locIdThenLvlIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), levelIdAndAlertCountMap);
					}
					levelIdAndAlertCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[5]));
				}
			}
			List<AlertCoreDashBoardVO> returnList = new ArrayList<AlertCoreDashBoardVO>();
			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
			if(locIdThenLvlIdThenAlertCount != null && locIdThenLvlIdThenAlertCount.size() > 0){
				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : locIdThenLvlIdThenAlertCount.entrySet()){
					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
					alertCoreDashBoardVO.setName(locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
					buildStatusWiseTemplate(alertCoreDashBoardVO,lvlIdAndLvlName,lvlIdAndColor);
					Long total = new Long(0L);
					for(AlertCoreDashBoardVO boardVO : alertCoreDashBoardVO.getSubList()){
						if(outerEntry.getValue() != null && outerEntry.getValue().get(boardVO.getId()) != null){
							boardVO.setCount(outerEntry.getValue().get(boardVO.getId()));
							total = total + outerEntry.getValue().get(boardVO.getId());
						}
					}
					alertCoreDashBoardVO.setTotalCount(total);
					returnList.add(alertCoreDashBoardVO);
				}
			}
			if(returnList != null && returnList.size() > 0){
				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("count")){
					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
						Collections.sort(returnList, alertAscendingCountWiseSortingLvlWise);
					}else{
						Collections.sort(returnList, alertDescCountWiseSortingLvlWise);
					}
				}
				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("name")){
					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
						Collections.sort(returnList, alphabeticalAscSortLvlWise);
					}else{
						Collections.sort(returnList, alphabeticalDescendingSortLvlWise);
					}
				}
			}
			return returnList;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public void buildStatusWiseTemplate(AlertCoreDashBoardVO alertCoreDashBoardVO,Map<Long,String> lvlIdAndLvlName,Map<Long,String> lvlIdAndColor){
		try{
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			AlertCoreDashBoardVO coreDashBoardVO = null;
			if(lvlIdAndLvlName != null && lvlIdAndLvlName.size() > 0 && lvlIdAndColor != null &&  lvlIdAndColor.size() > 0){
				for(Entry<Long,String> entry : lvlIdAndLvlName.entrySet()){
					coreDashBoardVO = new AlertCoreDashBoardVO();
					coreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(entry.getKey()));
					coreDashBoardVO.setName(commonMethodsUtilService.getStringValueForObject(entry.getValue()));
					coreDashBoardVO.setSevertyColor(lvlIdAndColor.get(entry.getKey()) != null ? lvlIdAndColor.get(entry.getKey()) : "");
					alertCoreDashBoardVOs.add(coreDashBoardVO);
				}
				alertCoreDashBoardVO.setSubList(alertCoreDashBoardVOs);  
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<AlertCoreDashBoardVO> getDistrictLevelDeptWiseFlterClick(Long userId,Long deptId,Long locatonLevelId,
			List<Long> statusIds,String type,String fromDateStr,String toDateStr,
			Long desigDeptOfficerId,Long officerId,List<Long> printIdsList,
			List<Long> electronicIdsList,List<Long> calCntrIdList,Long alertCategoryId,List<Long> socialMediaTypeIds,
			List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		List<AlertCoreDashBoardVO> finalVoList = new ArrayList<AlertCoreDashBoardVO>(0);
		List<Long> alertIds = null;
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			prepareRequiredParameter(printIdsList,electronicIdsList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
			
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			if(type.equalsIgnoreCase("alert")){
				alertIds = alertAssignedOfficerNewDAO.getAlertIdsForDeptAndLevelId(deptId,locatonLevelId,statusIds,fromDate,toDate,desigDeptOfficerId,officerId,levelId,levelValues,printIdsList,electronicIdsList,calCntrIdList,alertCategoryId,socialMediaTypeIds,alertSeverityIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			}else if(type.equalsIgnoreCase("subTask")){
				alertIds = govtAlertSubTaskDAO.getAlertIdsForDeptAndLevelId(deptId,locatonLevelId,statusIds,fromDate,toDate,desigDeptOfficerId,officerId,levelId,levelValues,printIdsList,electronicIdsList,calCntrIdList,alertCategoryId,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			}
			if(alertIds != null && alertIds.size() > 0){
				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIds));
				setAlertDtls(finalVoList, list); 
			}
			setSubListCount(finalVoList, alertIds);
		} catch (Exception e) {
			LOG.error(" Exception Occured in getDistrictLevelDeptWiseFlterClick() method, Exception - ",e);
		}		
		return finalVoList;
	}
	
	public List<GovtDepartmentVO> getDesignationsByDepartment(Long departmentId,Long levelId,Long levelValue){
		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
		try {
			
			List<Object[]> list = govtDepartmentDesignationOfficerDetailsNewDAO.getDesignationsForDepartmentAndLevelLocation(departmentId,levelId,levelValue);
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
			
			List<Object[]> list = govtDepartmentDesignationOfficerDetailsNewDAO.getOfficersByDesignationAndLevel(levelId, levelValue, designationId);
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
	
	public String assigningSubTaskToOfficer(final AlertAssigningVO inputvo){
		String status = null;
		try {
			status = (String)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					
					try {
						//Alert details
						//Alert alert = alertDAO.get(inputvo.getAlertId());
						
						Long alertAssignedOfficerId=null;
						List<Long> alertAssignedOfficerIds = alertAssignedOfficerNewDAO.getAlertAssignedOfficerId(inputvo.getAlertId());
						if(alertAssignedOfficerIds !=null && alertAssignedOfficerIds.size()>0)
							alertAssignedOfficerId = alertAssignedOfficerIds.get(0);
						

						//Get Department Designation Officer Ids
						Long desigOfficerId = null;
						List<GovtDepartmentDesignationOfficerNew> govtDepartmentDesignationOfficer = govtDepartmentDesignationOfficerDetailsNewDAO.getDesignationOfficerIdsNew(inputvo.getLevelId(), inputvo.getLevelValue(), inputvo.getDesignationId(),
								inputvo.getGovtOfficerId());
						if(govtDepartmentDesignationOfficer != null && !govtDepartmentDesignationOfficer.isEmpty())
							desigOfficerId = govtDepartmentDesignationOfficer.get(0).getGovtDepartmentDesignationOfficerId();
						
						//Subtask Assigning to Officer
						GovtAlertSubTask govtAlertSubTask = new GovtAlertSubTask();
						
						govtAlertSubTask.setAlertId(inputvo.getAlertId());
						govtAlertSubTask.setTitle(inputvo.getTitle());
						govtAlertSubTask.setDescription(inputvo.getTitle());
						govtAlertSubTask.setGovtDepartmentDesignationOfficerId(desigOfficerId);
						govtAlertSubTask.setDueDate(!inputvo.getDueDate().equalsIgnoreCase("Due Date") ? new SimpleDateFormat("dd/MM/yyyy").parse(inputvo.getDueDate()):null);
						if(inputvo.getAlertAssignedOfficerId() !=null)
							govtAlertSubTask.setAlertAssignedOfficerId(alertAssignedOfficerId);
						else
							govtAlertSubTask.setAlertAssignedOfficerId(alertAssignedOfficerId);
						
						govtAlertSubTask.setGovtDepartmentDesignationOfficerId(desigOfficerId);
						govtAlertSubTask.setSubTaskGovtOfficerId(inputvo.getGovtOfficerId() !=null ? (Long)inputvo.getGovtOfficerId():null);
						govtAlertSubTask.setCreatedBy(inputvo.getUserId());
						govtAlertSubTask.setUpdatedBy(inputvo.getUserId());
						govtAlertSubTask.setCreatedTime(new DateUtilService().getCurrentDateAndTime());
						govtAlertSubTask.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
						
						govtAlertSubTask.setAlertSubTaskStatusId(1l); // default status Notified
						govtAlertSubTask.setIsApproved("Y");
						govtAlertSubTask.setIsDeleted("N");
						
						govtAlertSubTask = govtAlertSubTaskDAO.save(govtAlertSubTask);
						
						//Officer Assigning Tracking
						GovtOfficerSubTaskTracking govtOfficerSubTaskTracking = new GovtOfficerSubTaskTracking();
						
						if(inputvo.getAlertAssignedOfficerId() !=null)
							govtOfficerSubTaskTracking.setAlertAssignedOfficerId(alertAssignedOfficerId);
						else
							govtOfficerSubTaskTracking.setAlertAssignedOfficerId(alertAssignedOfficerId);
						
						govtOfficerSubTaskTracking.setGovtAlertSubTaskId(govtAlertSubTask.getGovtAlertSubTaskId());
						govtOfficerSubTaskTracking.setGovtAlertActionTypeId(1l);
						govtOfficerSubTaskTracking.setAlertSubTaskStatusId(govtAlertSubTask.getAlertSubTaskStatusId());
						govtOfficerSubTaskTracking.setInsertedById(inputvo.getUserId());
						govtOfficerSubTaskTracking.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
						govtOfficerSubTaskTracking.setIsDeleted("N");
						
						
						govtOfficerSubTaskTracking = govtOfficerSubTaskTrackingDAO.save(govtOfficerSubTaskTracking);
						
						List<Long> userIdsList = govtDepartmentDesignationOfficerDetailsNewDAO.getuserIdDtlsForDesignationOfficerId(desigOfficerId);
						if(commonMethodsUtilService.isListOrSetValid(userIdsList)){
							Long assignedToUserID = userIdsList.get(0);
							getSubTaskDetailsAndSendSMS(desigOfficerId,govtAlertSubTask.getSubTaskGovtOfficerId(),govtAlertSubTask.getGovtAlertSubTaskId(),1L,assignedToUserID,"","",inputvo.getUserId());
						}
						
						return "success";
					} catch (Exception e) {
						LOG.error("Error occured assigningSubTaskToOfficer() method of AlertManagementSystemService",e);
					}
					return "failure";
				}
			});
		} catch (Exception e) {
			LOG.error("Error occured assigningSubTaskToOfficer() method of AlertManagementSystemService",e);
		}
		return status;
	}
	
	public IdNameVO getGovtSubLevelInfo(Long departmentId,Long LevelId,Long levelValue){
		
		IdNameVO finalVO = new IdNameVO();
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		
		try {
			
			List<Object[]> objList = govtDepartmentWorkLocationRelationDAO.getGovtSubLevelInfo(levelValue);
			if(objList !=null && objList.size()>0){
				for (Object[] obj : objList) {
					
					IdNameVO VO = getGovtSubLevelMatchedVo(returnList,(Long)obj[0]);
					if(VO == null){
						VO = new IdNameVO();
						VO.setId((Long)obj[0]);
						VO.setName(obj[1] !=null ? obj[1].toString():"");
						returnList.add(VO);
					}
					
					IdNameVO subVo = new IdNameVO();					
					subVo.setId((Long)obj[2]);
					subVo.setName(obj[3] !=null ? obj[3].toString():"");
					
					VO.getIdnameList().add(subVo);
					
				}
			}
			
			if(returnList !=null && returnList.size()>0)
				finalVO = returnList.get(0);
			
		} catch (Exception e) {
			LOG.error("Error occured getGovtSubLevelInfo() method of AlertManagementSystemService",e);
		}
		
		return finalVO;
	}
	
	public IdNameVO getGovtSubLevelMatchedVo(List<IdNameVO> returnList,Long levelId){
		try{
			if(returnList == null || returnList.size() ==0)
				return null;
			for(IdNameVO VO:returnList){
				if(VO.getId().equals(levelId))
				{
					return VO;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getGovtSubLevelMatchedVo() method of AlertManagementSystemService{}");
		}
		return null;
	}
	public void sortListBasedRequiredType(List<AlertVO> resultList,String sortingType){
		 try{
			if(sortingType != null && sortingType.equalsIgnoreCase("Decending")){
				Collections.sort(resultList, alertDescendingCountWiseSorting);
			}else if(sortingType != null && sortingType.equalsIgnoreCase("Ascending")){
				Collections.sort(resultList, alertAscendingCountWiseSorting);
			}else if(sortingType != null && sortingType.equalsIgnoreCase("AlphabeticalAscending")){
				Collections.sort(resultList, alphabeticalAscendingSort);
			}else if(sortingType != null && sortingType.equalsIgnoreCase("AlphabeticalDescending")){
				Collections.sort(resultList, alphabeticalDescendingSort);
			}
		 }catch(Exception e){
			LOG.error("Exception occured  in sortListByRequiredType() in AlertManagementSystemService class ",e);  
		 }
	 }
	public static Comparator<AlertVO> alertDescendingCountWiseSorting = new Comparator<AlertVO>() {
    	public int compare(AlertVO location2, AlertVO location1) {
    	Long count2 = location2.getCount();
    	Long count1 = location1.getCount();
    	//descending order of percantages.
    	 return count1.compareTo(count2);
    	}
     };
     public static Comparator<AlertVO> alertAscendingCountWiseSorting = new Comparator<AlertVO>() {
     	public int compare(AlertVO location2, AlertVO location1) {
     	Long count2 = location2.getCount();
     	Long count1 = location1.getCount();
     	//ascending order of percantages.
     	 return count2.compareTo(count1);
     	}
      };
      public static Comparator<AlertVO> alphabeticalDescendingSort = new Comparator<AlertVO>() {
       	public int compare(AlertVO location2, AlertVO location1) {
        	String name2 = location2.getName();
       	    String name1 = location1.getName();
       	    //descending order of percantages.
       	    return name1.compareTo(name2);
       	}
       };
       public static Comparator<AlertVO> alphabeticalAscendingSort = new Comparator<AlertVO>() {
          	public int compare(AlertVO location2, AlertVO location1) {
          	String name2 = location2.getName();
          	String name1 = location1.getName();
          	//ascending order of percantages.
          	 return name2.compareTo(name1);
          	}
        };
        /*
         * Swadhin K Lenka
         * status
         * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getStateThenGovtDeptScopeWiseAlertCountStatusWise(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
         */
        public List<AlertCoreDashBoardVO> getStateThenGovtDeptScopeWiseAlertCountStatusWise(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType){
    		try{
    			
    			Date fromDate = null;
    			Date toDate = null;
    			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
    				fromDate = sdf.parse(fromDateStr);
    				toDate = sdf.parse(toDateStr);
    			}
    			
    			if(electronicIdList == null && printIdList == null){
    				electronicIdList = new ArrayList<Long>();
    				printIdList = new ArrayList<Long>();
    			}
    			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
    			if(printIdList != null && printIdList.size() > 0){  
    				if(electronicIdList != null && electronicIdList.size() == 0){
    					electronicIdList.add(0L);
    				}
    			}else if(electronicIdList != null && electronicIdList.size() > 0){
    				if(printIdList != null && printIdList.size() == 0){
    					printIdList.add(0L);
    				}
    			}/*else{
    				
    				electronicIdList.add(0L);
    				printIdList.add(0L);
    			}*/
    			
    			List<Long> levelValues = new ArrayList<Long>();    
    			Long levelId = 0L;
    			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
    			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
    				for(Object[] param : lvlValueAndLvlIdList){
    					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
    					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
    				}
    			}
    			
    			
    			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
    			List<Long> deptScopeIdList = new ArrayList<Long>();
    			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
    				for(Object [] param : childDeptScopeIdList){
    					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
    				}
    			}
    			
    			List<Object[]> alertList =null;
    			if(alertType != null && alertType.equalsIgnoreCase("alert")){
    				 alertList = alertAssignedOfficerNewDAO.getLocationThenGovtDeptScopeWiseAlertCountForStatus(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList);
    			}else if(alertType != null && alertType.equalsIgnoreCase("subAlert")){
   				 	 alertList = govtAlertSubTaskDAO.getDistrictOfficerSubTaskAlerts(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,"status");
    			}
    			List<AlertCoreDashBoardVO> returnList = new ArrayList<AlertCoreDashBoardVO>();
    			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
    				prepareResultForState(alertList,returnList,sortingType,order,null,null);
    				return returnList;
    			}
    			
    			Map<Long,String> locIdAndLocNameMap = new LinkedHashMap<Long,String>();
    			Map<Long,String> statusIdAndStatusName = new LinkedHashMap<Long,String>();
    			Map<Long,String> statusIdAndColor = new LinkedHashMap<Long,String>();
    			Map<Long,LinkedHashMap<Long,Long>> locIdThenStatusIdThenAlertCount = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
    			LinkedHashMap<Long,Long> statusIdAndAlertCountMap = null;
    			
    			if(alertList != null && alertList.size() > 0){ 
    				for(Object[] param : alertList){
    					locIdAndLocNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[2]));
    					statusIdAndStatusName.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getStringValueForObject(param[4]));
    					statusIdAndColor.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getStringValueForObject(param[6]));
    					
    					statusIdAndAlertCountMap = locIdThenStatusIdThenAlertCount.get(commonMethodsUtilService.getLongValueForObject(param[0]));
    					if(statusIdAndAlertCountMap == null){
    						statusIdAndAlertCountMap = new LinkedHashMap<Long,Long>();
    						locIdThenStatusIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), statusIdAndAlertCountMap);
    					}
    					statusIdAndAlertCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[5]));
    				}
    			}
    			
    			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
    			if(locIdThenStatusIdThenAlertCount != null && locIdThenStatusIdThenAlertCount.size() > 0){
    				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : locIdThenStatusIdThenAlertCount.entrySet()){
    					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
    					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
    					alertCoreDashBoardVO.setName(locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
    					buildStatusWiseTemplate(alertCoreDashBoardVO,statusIdAndStatusName,statusIdAndColor);
    					Long total = new Long(0L);
    					for(AlertCoreDashBoardVO boardVO : alertCoreDashBoardVO.getSubList()){
    						if(outerEntry.getValue() != null && outerEntry.getValue().get(boardVO.getId()) != null){
    							boardVO.setCount(outerEntry.getValue().get(boardVO.getId()));
    							total = total + outerEntry.getValue().get(boardVO.getId());
    						}
    					}
    					alertCoreDashBoardVO.setTotalCount(total);
    					returnList.add(alertCoreDashBoardVO);
    				}
    			}
    			
    			if(returnList != null && returnList.size() > 0){
    				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("count")){
    					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
    						Collections.sort(returnList, alertAscendingCountWiseSortingLvlWise);
    					}else{
    						Collections.sort(returnList, alertDescCountWiseSortingLvlWise);
    					}
    				}
    				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("name")){
    					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
    						Collections.sort(returnList, alphabeticalAscSortLvlWise);
    					}else{
    						Collections.sort(returnList, alphabeticalDescendingSortLvlWise);
    					}
    				}
    			}
    			return returnList;
    			
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    		return null;
    	}
        /*
         * Swadhin K Lenka
         */
        public List<AlertCoreDashBoardVO> prepareResultForState(List<Object[]> alertList,List<AlertCoreDashBoardVO> returnList,String sortingType,String order,String alertType,String searchType){
        	try{
        		
        		Map<Long,String> lvlIdAndLvlName = new LinkedHashMap<Long,String>();
    			Map<Long,String> statusIdAndStatusName = new LinkedHashMap<Long,String>();
    			Map<Long,String> statusIdAndColor = new LinkedHashMap<Long,String>();    
    			
    			Map<Long,LinkedHashMap<Long,Long>> lvlIdThenStatusIdThenAlertCount = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
    			LinkedHashMap<Long,Long> statusIdThenAlertCount = null;
    			
    			
    			Set<Long> deptScopeIds = new HashSet<Long>();
    			Set<Long> statusIds = new HashSet<Long>();//Containing status id or alert categoryId 
    			Long stateId = 0l;
    			if(alertList != null && alertList.size() > 0){
    				for(Object[] param : alertList){
    					deptScopeIds.add(commonMethodsUtilService.getLongValueForObject(param[3]));
    					stateId = commonMethodsUtilService.getLongValueForObject(param[1]);
    				}
    			}
    			
    			if(alertList != null && alertList.size() > 0){
    				for(Object[] param : alertList){
    					statusIds.add(commonMethodsUtilService.getLongValueForObject(param[4]));
    				}
    			}
    			List<Object[]> statusIdDtlsList = null;
    			if(statusIds != null && statusIds.size() > 0){
    				if(alertType != null && alertType.equalsIgnoreCase("subTask") && searchType != null && searchType.equalsIgnoreCase("statuswise")){
    					 statusIdDtlsList=alertSubTaskStatusDAO.getAlertStatusDtlsBasidOnAlertIds(new ArrayList<Long>(statusIds));    					
    				}else if(searchType != null && searchType.equalsIgnoreCase("alertSource")){
    					 statusIdDtlsList = alertCategoryDAO.getAlertCategoryByCategoryIds(new ArrayList<Long>(statusIds));	
    				}else if(searchType.equalsIgnoreCase("statuswise")){
    					 statusIdDtlsList = alertStatusDAO.getAlertStatusDtlsBasidOnAlertIds(new ArrayList<Long>(statusIds));
    				}
    			}
    			
    			List<Object[]> deptScopeIdDtlsList = null;
    			if(deptScopeIds != null && deptScopeIds.size() >0){
    				deptScopeIdDtlsList = govtDepartmentScopeDAO.getGovtDepartmenttScopeDetailsBasedOnScopeIds(new ArrayList<Long>(deptScopeIds));
    			}
    			
    			
    			
    			if(deptScopeIdDtlsList != null && deptScopeIdDtlsList.size() > 0){
    				for(Object[] param : deptScopeIdDtlsList){
    					lvlIdAndLvlName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
    				}  
    			}
    			if(statusIdDtlsList != null && statusIdDtlsList.size() > 0){
    				for(Object[] param : statusIdDtlsList){
        				statusIdAndStatusName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
        				statusIdAndColor.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[2]));
    				}  
    			}
    			
    			
    			
        		if(alertList != null && alertList.size() > 0){
        			for(Object[] param : alertList){
        				statusIdThenAlertCount = lvlIdThenStatusIdThenAlertCount.get(commonMethodsUtilService.getLongValueForObject(param[3]));
    					if(statusIdThenAlertCount == null){
    						statusIdThenAlertCount = new LinkedHashMap<Long,Long>();
    						lvlIdThenStatusIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[3]), statusIdThenAlertCount);
    					}
    					statusIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[4]), commonMethodsUtilService.getLongValueForObject(param[5]));
    				}
        		}
        		//calculate status wise total count.
    			Map<Long,Long> statusIdAndTotalCount = new HashMap<Long,Long>();
    			if(lvlIdThenStatusIdThenAlertCount != null && lvlIdThenStatusIdThenAlertCount.size() > 0){
    				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : lvlIdThenStatusIdThenAlertCount.entrySet()){
    					for(Entry<Long,Long> innerEntry : outerEntry.getValue().entrySet()){
    						statusIdAndTotalCount.put(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey()), 0L);
    					}
    				}
    			}
    			if(lvlIdThenStatusIdThenAlertCount != null && lvlIdThenStatusIdThenAlertCount.size() > 0){
    				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : lvlIdThenStatusIdThenAlertCount.entrySet()){
    					for(Entry<Long,Long> innerEntry : outerEntry.getValue().entrySet()){
    						statusIdAndTotalCount.put(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey()), statusIdAndTotalCount.get(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey()))+commonMethodsUtilService.getLongValueForObject(innerEntry.getValue()));
    					}
    				}
    			}
        		
    			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
    			if(lvlIdThenStatusIdThenAlertCount != null && lvlIdThenStatusIdThenAlertCount.size() > 0){
    				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : lvlIdThenStatusIdThenAlertCount.entrySet()){
    					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
    					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
    					alertCoreDashBoardVO.setName(lvlIdAndLvlName.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? lvlIdAndLvlName.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
    					buildStatusWiseTemplate(alertCoreDashBoardVO,statusIdAndStatusName,statusIdAndColor);
    					Long total = new Long(0L);
    					for(AlertCoreDashBoardVO boardVO : alertCoreDashBoardVO.getSubList()){
    						if(outerEntry.getValue() != null && outerEntry.getValue().get(boardVO.getId()) != null){
    							boardVO.setCount(outerEntry.getValue().get(boardVO.getId()));
    							total = total + outerEntry.getValue().get(boardVO.getId());
    						}
    					}
    					alertCoreDashBoardVO.setTotalCount(total);
    					alertCoreDashBoardVO.setStateId(stateId);
    					returnList.add(alertCoreDashBoardVO);
    				}
    			}
    			if(returnList != null && returnList.size() > 0){
    				
    				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("count")){
    					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("Default")){
    					}else{
    						if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
        						Collections.sort(returnList, alertAscendingCountWiseSortingLvlWise);
        					}else{
        						Collections.sort(returnList, alertDescCountWiseSortingLvlWise);
        					}
        				}
    				}
    				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("name")){
    					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
    						Collections.sort(returnList, alphabeticalAscSortLvlWise);
    					}else{
    						Collections.sort(returnList, alphabeticalDescendingSortLvlWise);
    					}
    				}
    			}
    			
    			if(statusIdAndTotalCount.size() > 0){
    				if(returnList != null && returnList.size() > 0){
    					AlertCoreDashBoardVO altCorevo = returnList.get(0);
    					for(AlertCoreDashBoardVO param : altCorevo.getSubList()){
    						if(statusIdAndTotalCount.get(param.getId()) != null){
    							param.setGrandTotal(statusIdAndTotalCount.get(param.getId()));
    						}
    					}
    				}
    			}
    			
    			return returnList;
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	return null;
        }
        public void sortStateLevelLstBasedOnScope(List<AlertCoreDashBoardVO> resultList,Long govtDeptId){
        	try{
        		List<Object[]> objList = govtDepartmentScopeLevelDAO.getDeptScopeIdAndOrder(govtDeptId);
        		 if(objList != null && objList.size() > 0){
        			 for(Object[] param:objList){
        				 AlertCoreDashBoardVO matchVO = getStatusMatchVO(resultList,commonMethodsUtilService.getLongValueForObject(param[0]));
        				  if(matchVO != null){
        					  matchVO.setOrderNo(commonMethodsUtilService.getLongValueForObject(param[1]));
        				  }
        			 }
        		 }
        		 if(resultList != null && resultList.size() > 0){
        			 Collections.sort(resultList, ascendingSortingByScopeIds);	 
        		 }
        		 
        	}catch(Exception e){
        		e.printStackTrace();
        		LOG.error("Error occured sortStateLevelLstBasedOnScope() method of AlertManagementSystemService{}");
        	}
        }
        
        public static Comparator<AlertCoreDashBoardVO> ascendingSortingByScopeIds = new Comparator<AlertCoreDashBoardVO>() {
           	public int compare(AlertCoreDashBoardVO location2, AlertCoreDashBoardVO location1) {
           	Long id2 = location2.getOrderNo();
           	Long id1 = location1.getOrderNo();
           	//ascending order by id.
           	 return id2.compareTo(id1);
           	}
         };
        public static Comparator<AlertCoreDashBoardVO> alertAscendingCountWiseSortingLvlWise = new Comparator<AlertCoreDashBoardVO>() {
         	public int compare(AlertCoreDashBoardVO location2, AlertCoreDashBoardVO location1) {
         	Long count2 = location2.getTotalCount();
         	Long count1 = location1.getTotalCount();
         	//ascending order of percantages.
         	 return count2.compareTo(count1);
         	}
        };
        public static Comparator<AlertCoreDashBoardVO> alertDescCountWiseSortingLvlWise = new Comparator<AlertCoreDashBoardVO>() {
           	public int compare(AlertCoreDashBoardVO location2, AlertCoreDashBoardVO location1) {
           	Long count2 = location2.getTotalCount();
           	Long count1 = location1.getTotalCount();
           	//desc order of percantages.
           	 return count1.compareTo(count2);
           	}
         };
         public static Comparator<AlertCoreDashBoardVO> alphabeticalDescendingSortLvlWise = new Comparator<AlertCoreDashBoardVO>() {
         public int compare(AlertCoreDashBoardVO location2, AlertCoreDashBoardVO location1) {
            	String name2 = location2.getName();
           	    String name1 = location1.getName();
           	    //descending order of percantages.
           	    return name1.compareTo(name2);
         	}
         };
         public static Comparator<AlertCoreDashBoardVO> alphabeticalAscSortLvlWise = new Comparator<AlertCoreDashBoardVO>() {
              	public int compare(AlertCoreDashBoardVO location2, AlertCoreDashBoardVO location1) {
              		String name2 = location2.getName();
              	    String name1 = location1.getName();
              	    //descending order of percantages.
              	    return name2.compareTo(name1);
              	}
              };
              //State level soring 
              public static Comparator<AlertCoreDashBoardVO> alertStateAscendingCountWiseSortingLvlWise = new Comparator<AlertCoreDashBoardVO>() {
               	public int compare(AlertCoreDashBoardVO location2, AlertCoreDashBoardVO location1) {
               	Long count2 = location2.getCount();
               	Long count1 = location1.getCount();
               	//ascending order of percantages.
               	 return count2.compareTo(count1);
               	}
              };
              public static Comparator<AlertCoreDashBoardVO> alertStateDescCountWiseSortingLvlWise = new Comparator<AlertCoreDashBoardVO>() {
                 	public int compare(AlertCoreDashBoardVO location2, AlertCoreDashBoardVO location1) {
                 	Long count2 = location2.getCount();
                 	Long count1 = location1.getCount();
                 	//desc order of percantages.
                 	 return count1.compareTo(count2);
                 	}
               };
               public static Comparator<AlertCoreDashBoardVO> alphabeticalStateDescendingSortLvlWise = new Comparator<AlertCoreDashBoardVO>() {
               public int compare(AlertCoreDashBoardVO location2, AlertCoreDashBoardVO location1) {
                  	String name2 = location2.getName();
                 	    String name1 = location1.getName();
                 	    //descending order of percantages.
                 	    return name1.compareTo(name2);
               	}
               };
               public static Comparator<AlertCoreDashBoardVO> alphabeticalStateAscSortLvlWise = new Comparator<AlertCoreDashBoardVO>() {
                    	public int compare(AlertCoreDashBoardVO location2, AlertCoreDashBoardVO location1) {
                    		String name2 = location2.getName();
                    	    String name1 = location1.getName();
                    	    //descending order of percantages.
                    	    return name2.compareTo(name1);
                    	}
                    };
            /*  public List<IdNameVO> getDeptListForMultiLvl(Long userId){
            	  try{
            		  List<Object[]> deptList= alertAssignedOfficerNewDAO.getDeptList(userId);
            		  List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();  
            		  IdNameVO idNameVO = null;
            		  if(deptList != null && deptList.size() > 0){
            			  for(Object[] param : deptList){
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
              }*/
             
           public List<AlertCoreDashBoardVO> getDistrictOfficerAlertDetails(List<Long> govtDeptGovtOffrIds,List<Long> govtOffrcrIds,String countType,String alertType, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList,String fromDateStr,String toDateStr,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds, List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
        		List<AlertCoreDashBoardVO> finalVoList = new ArrayList<AlertCoreDashBoardVO>(0);
        		try {
        			Date fromDate = null;
  		      		Date toDate = null;
  		           SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	  		      if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
	  		    	fromDate = sdf.parse(fromDateStr);
	  		    	toDate = sdf.parse(toDateStr);
	  		      }
        			List<Long> alertIdList = null;
        		
        			prepareRequiredParameter(printIdsList,electronicIdsList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Setting Parameter
        			
        			if(alertType != null && alertType.equalsIgnoreCase("alert")){
        				 alertIdList = alertAssignedOfficerNewDAO.getDistrictOfficerAlertsIds(govtDeptGovtOffrIds,govtOffrcrIds,countType,printIdsList,electronicIdsList,calCntrIdList,fromDate,toDate,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
        			}else {
       				 	alertIdList = govtAlertSubTaskDAO.getDistrictOfficerSubTasksAlertIds(govtDeptGovtOffrIds,govtOffrcrIds,countType,alertType,printIdsList,electronicIdsList,calCntrIdList,fromDate,toDate,socialMediaTypeIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
        			}
        			if(alertIdList != null && alertIdList.size() > 0){
        				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIdList));
        				setAlertDtls(finalVoList, list); 
        			}
        			setSubListCount(finalVoList, alertIdList);
        		} catch (Exception e) {
        			LOG.error(" Exception Occured in getDistrictOfficerAlertDetails() method, Exception - ",e);
        		}		
        		return finalVoList;
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
              
              public List<AlertTrackingVO> getAlertStatusHistory(Long alertId){
              	List<AlertTrackingVO> voList = new ArrayList<AlertTrackingVO>(0);
              	try {
              		//0-status,1-comment,2-date,3-officerName,4-mobileNo,5-designationName,6-departmentName
              		
              		List<Object[]> objList = alertAssignedOfficerTrackingNewDAO.getAlertStatusForAdminHistory1(alertId,6L);//status change Id - 6
              		if(!commonMethodsUtilService.isListOrSetValid(objList))
              			objList = new ArrayList<Object[]>(0);
              			
       				List<Object[]> statusCommentsList = alertAssignedOfficerTrackingNewDAO.getAlertStatusHistory1(alertId,6L);
       				if(commonMethodsUtilService.isListOrSetValid(statusCommentsList))
       					objList.addAll(statusCommentsList);
       				
       				Map<String,AlertTrackingVO> alertMap = new LinkedHashMap<String, AlertTrackingVO>(0);
       				if(objList != null && objList.size() > 0){
       					for (Object[] objects : objList) {
       						AlertTrackingVO vo = new AlertTrackingVO();
       						
       						vo.setStatus(objects[0] != null ? objects[0].toString():"");
       						vo.setComment(objects[1] != null ? objects[1].toString():"");
       						vo.setDate(objects[2] != null ? objects[2].toString():"");
       						vo.setUserName(objects[3] != null ? objects[3].toString():""+" - "+objects[4] != null ? objects[4].toString():"");
       						vo.setMobileNO(commonMethodsUtilService.getStringValueForObject(objects[4]));
       						vo.setDesignation(commonMethodsUtilService.getStringValueForObject(objects[5]));
       						vo.setDeptName(commonMethodsUtilService.getStringValueForObject(objects[6]));
       						vo.setUserId(commonMethodsUtilService.getLongValueForObject(objects[7]));
       						vo.setLocation(commonMethodsUtilService.getStringValueForObject(objects[8]));
       						
       						AlertTrackingVO tempVO = alertMap.get(vo.getDate()+"-"+vo.getComment());
       						if( tempVO!= null && tempVO.getDate().equalsIgnoreCase(vo.getDate()) && tempVO.getComment().equalsIgnoreCase(vo.getComment()))
       						{
       							if(!tempVO.getDeptName().contains(vo.getDeptName())){
       								if(tempVO.getDeptName().trim().length() > 0 && !tempVO.getDeptName().trim().isEmpty()){
       									vo.setDeptName(tempVO.getDeptName()+", "+vo.getDeptName());
       								}else{
       									vo.setDeptName(vo.getDeptName());
       								}
       								
       							}
       						}
       						if(objects[3] != null && objects[3].toString().trim().contains("govt"))
       						{
       							vo.setDesignation("Admin");
       						}
       						
       						alertMap.put(vo.getDate()+"-"+vo.getComment(), vo);
       					}
       					
       					if(commonMethodsUtilService.isMapValid(alertMap)){
       						voList.addAll(alertMap.values());
       						
       						if(commonMethodsUtilService.isListOrSetValid(voList)){
       							Collections.sort(voList, new Comparator<AlertTrackingVO>() {
									public int compare(AlertTrackingVO o1,AlertTrackingVO o2) {
										int i =0;
										try {
											SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
											i = sdf.parse(o2.getDate()).compareTo(sdf.parse(o1.getDate()));
										} catch (Exception e) {
											e.printStackTrace();
										}
										return i;
									}
								});
       						}
       					}
       				}
       			} catch (Exception e) {
       				LOG.error("Error occured getAlertStatusHistory() method of AlertManagementSystemService{}");
       			}
              	return voList;
              }
              
              
              public List<AlertCoreDashBoardVO> getDistrictOfficerScopesWiseAlerts(String fromDateStr, String toDateStr, Long stateId, Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType){
          		try{
          			
          			Date fromDate = null;
        			Date toDate = null;
        			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
        				fromDate = sdf.parse(fromDateStr);
        				toDate = sdf.parse(toDateStr);
        			}
        			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
        			
        			List<Long> levelValues = new ArrayList<Long>();    
        			Long levelId = 0L;
        			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
        			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
        				for(Object[] param : lvlValueAndLvlIdList){
        					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
        					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
        				}
        			}
        			
        			
        			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
        			List<Long> deptScopeIdList = new ArrayList<Long>();
        			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
        				for(Object [] param : childDeptScopeIdList){
        					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
        				}
        			}
          			
          			List<Object[]> alertList =null;
          			if(alertType != null && alertType.equalsIgnoreCase("alert")){
          				 alertList = alertAssignedOfficerNewDAO.getDistrictOfficerScopesWiseAlerts(fromDate,toDate,stateId,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList);
          			}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
         				 	 alertList = govtAlertSubTaskDAO.getDistrictOfficerSubTaskAlerts(fromDate,toDate,stateId,null,null,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,"scopes");
          			}
          			List<AlertCoreDashBoardVO> returnList = new ArrayList<AlertCoreDashBoardVO>();
        			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
        				prepareResultForState(alertList,returnList,sortingType,order,null,null);
        				return returnList;
        			}
        			
        			Map<Long,String> locIdAndLocNameMap = new LinkedHashMap<Long,String>();
        			Map<Long,String> statusIdAndStatusName = new LinkedHashMap<Long,String>();
        			Map<Long,String> statusIdAndColor = new LinkedHashMap<Long,String>();
        			Map<Long,LinkedHashMap<Long,Long>> locIdThenStatusIdThenAlertCount = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
        			LinkedHashMap<Long,Long> statusIdAndAlertCountMap = null;
        			
        			if(alertList != null && alertList.size() > 0){ 
        				for(Object[] param : alertList){
        					locIdAndLocNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[2]));
        					statusIdAndStatusName.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getStringValueForObject(param[4]));
        					statusIdAndColor.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getStringValueForObject(param[6]));
        					
        					statusIdAndAlertCountMap = locIdThenStatusIdThenAlertCount.get(commonMethodsUtilService.getLongValueForObject(param[0]));
        					if(statusIdAndAlertCountMap == null){
        						statusIdAndAlertCountMap = new LinkedHashMap<Long,Long>();
        						locIdThenStatusIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), statusIdAndAlertCountMap);
        					}
        					statusIdAndAlertCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[5]));
        				}
        			}
        			
        			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
        			if(locIdThenStatusIdThenAlertCount != null && locIdThenStatusIdThenAlertCount.size() > 0){
        				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : locIdThenStatusIdThenAlertCount.entrySet()){
        					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
        					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
        					alertCoreDashBoardVO.setName(locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
        					buildStatusWiseTemplate(alertCoreDashBoardVO,statusIdAndStatusName,statusIdAndColor);
        					Long total = new Long(0L);
        					for(AlertCoreDashBoardVO boardVO : alertCoreDashBoardVO.getSubList()){
        						if(outerEntry.getValue() != null && outerEntry.getValue().get(boardVO.getId()) != null){
        							boardVO.setCount(outerEntry.getValue().get(boardVO.getId()));
        							total = total + outerEntry.getValue().get(boardVO.getId());
        						}
        					}
        					alertCoreDashBoardVO.setTotalCount(total);
        					returnList.add(alertCoreDashBoardVO);
        				}
        			}
        			
        			if(returnList != null && returnList.size() > 0){
        				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("count")){
        					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
        						Collections.sort(returnList, alertAscendingCountWiseSortingLvlWise);
        					}else{
        						Collections.sort(returnList, alertDescCountWiseSortingLvlWise);
        					}
        				}
        				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("name")){
        					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
        						Collections.sort(returnList, alphabeticalAscSortLvlWise);
        					}else{
        						Collections.sort(returnList, alphabeticalDescendingSortLvlWise);
        					}
        				}
        			}
        			return returnList;
          			
          		}catch(Exception e){
          			e.printStackTrace();
          		}
          		return null;
          	}
           public List<GovtDepartmentVO> getAssignedOfficersDetails(Long alertId){
       		List<GovtDepartmentVO> returnList = new ArrayList<GovtDepartmentVO>();
       		try {
       			List<Object[]> list = alertAssignedOfficerNewDAO.getAssignedOfficersDetails(alertId);
       			if(list != null && !list.isEmpty()){
       				for (Object[] obj : list) {
       					GovtDepartmentVO vo = new GovtDepartmentVO();
       					
       					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
       					vo.setName(obj[1] != null ? obj[1].toString():"");
       					vo.setDepartment(obj[2] != null ? obj[2].toString():"");
       					vo.setMobileNo(obj[3] != null ? obj[3].toString():"");
       					vo.setDesignation(obj[4] != null ? obj[4].toString():"");
       					vo.setSource(obj[5] !=null ?  obj[5].toString():"");
       					
       					returnList.add(vo);
       				}
       			}
       		} catch (Exception e) {
       			LOG.error("Error occured getAssignedOfficersDetails() method of CccDashboardService",e);
       		}
       		return returnList;
       	}
            
            public List<IdNameVO> getDepartmentSubLevels(Long departmentId,Long parentLevelId){
            	List<IdNameVO> returnList = new ArrayList<IdNameVO>();
            	try {
            		
            		List<Object[]> objList = govtDepartmentScopeLevelDAO.getDepartmentSubLevels(departmentId,parentLevelId);
    	        		
    	        	if(objList !=null && objList.size()>0){
    	        		for (Object[] obj : objList) {
    	        			IdNameVO VO = new IdNameVO();
    						VO.setId((Long)obj[0]);
    						VO.setName(obj[1] !=null ? obj[1].toString():"");
    						returnList.add(VO);
    					}
    	        	}
    			
    			} catch (Exception e) {
    				e.printStackTrace();
    				LOG.error("Error occured getDepartmentSubLevels() method of AlertManagementSystemService class ");
    			}
            	return returnList;        	
            }
            
            public List<IdNameVO> getChildLevelValuesForSubTask(Long departmentId,Long parentLevelId,List<Long> parentLevelValues,Long levelId){
            	List<IdNameVO> finalList = new ArrayList<IdNameVO>(0);
            	try {
            		
            		Map<Long,IdNameVO> levelMap = new LinkedHashMap<Long, IdNameVO>();
            		
            		//ScopeId,levelName
            		List<Object[]> subLevelObj = govtDepartmentScopeLevelDAO.getParentLevelsOfLevel(departmentId,levelId);
            		
            		if(subLevelObj !=null && subLevelObj.size()>0){
            				Set<Long> subLevelIds = new LinkedHashSet<Long>();
            				for (Object[] param : subLevelObj) {		
            					if(parentLevelId <= (Long)param[0]){
            						IdNameVO vo = new IdNameVO();						
            						vo.setId((Long)param[0]);
            						vo.setName(param[1] !=null ? param[1].toString():"");	
            						
            						levelMap.put((Long)param[0], vo);
            						
            						subLevelIds.add((Long)param[0]);
            					}
            						
            				}
            				
            				List<Object[]> levelValueObj = govtDepartmentWorkLocationDAO.getParentLevelValuesListInfo(parentLevelValues);
            				
            				List<IdNameVO> parentList = new ArrayList<IdNameVO>(); 
            				
            				parentList = setParentListInfo(parentList,levelValueObj);
            				
            				
            				//0.levelId,1.workLocationId,2.LocationName
            				List<Object[]> objValueList = govtDepartmentWorkLocationDAO.getLevelWiseInfo(departmentId,subLevelIds);
            				
            				if(objValueList !=null && objValueList.size()>0){
            					setLocationValuesToLevelMap(objValueList,levelMap,parentList,parentLevelId);
            				}
            				
            				if(levelMap !=null && levelMap.size()>0){
            					finalList.addAll(levelMap.values());
            				}
            				
            		}
            		
            		
    			} catch (Exception e) {
    				LOG.error("Error occured getChildLevelValuesForSubTask() method of CccDashboardService{}");
    			}
            	
            	return finalList;
            	
            }
            
            public List<IdNameVO> setParentListInfo(List<IdNameVO> parentList,List<Object[]> levelValueObj ){
            	        	
            	try {
    				
            		if(levelValueObj !=null && levelValueObj.size()>0){
            			for (Object[] param : levelValueObj) {
    						
            				IdNameVO Vo = new IdNameVO();
            				Vo.setId((Long)param[0]);//workLocationId
            				Vo.setName(param[1] !=null ? param[1].toString():"");
            				
            				parentList.add(Vo);
    					}
            		}
            		
    			} catch (Exception e) {
    				LOG.error("Error occured setParentListInfo() method of CccDashboardService{}");
    			}
            	return parentList;
            } 
            
            
            public void setLocationValuesToLevelMap(List<Object[]> objList,Map<Long,IdNameVO> levelMap,List<IdNameVO> parentList,
            		Long parentLevelId){
        		try {
        			
        			if(objList !=null && objList.size()>0){
        				for (Object[] obj : objList) {    					
        					if(obj[0] !=null){    						
        						if(parentLevelId.equals((Long)obj[0])){
        							
        							IdNameVO mainVo = levelMap.get(obj[0] !=null ? (Long)obj[0]:0l);
        							if(mainVo == null){
        								mainVo = new IdNameVO();    								
        								mainVo.setId((Long)obj[0]);
        								levelMap.put((Long)obj[0], mainVo);
        							}
        							
        							if(obj[1] !=null){    								
        								IdNameVO subVo = getGovtSubLevelMatchedVo(parentList,(Long)obj[1]);    								
        								if(subVo !=null){
        									mainVo.getIdnameList().add(subVo);
        								}
        							}    							
        						}
        					}
        				}
        			}
        			
        		} catch (Exception e) {
        			LOG.error("Error occured setLocationValuesToLevelMap() method of AlertManagementSystemService",e);
        		}
        	}
            public List<AlertVO> getAllDivisionDetails(Long districtId){
         List<AlertVO> finalVoList = new ArrayList<AlertVO>(0);
     		try {
	    		List<Object[]> divisionList = govtDepartmentWorkLocationDAO.getAllDivisionDetails(districtId);
	    		if (divisionList != null && divisionList.size() > 0) {
           			for (Object[] objects : divisionList) {
           				 AlertVO alertVO = new AlertVO();
           				 alertVO.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
           				 alertVO.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
           				 finalVoList.add(alertVO);
           				}					
   				}				
   			} catch (Exception e) {
   				LOG.error(" Exception Occured in getAllDivisionDetails() method, Exception - ",e);
   			}    	
           	return finalVoList;
           }
           public List<AlertVO> getAllSubDivisionDetails(Long divisionId){
               List<AlertVO> finalVoList = new ArrayList<AlertVO>(0);
              	try {
              		List<Object[]> subDivisionList = govtDepartmentWorkLocationDAO.getAllSubDivisionDetails(divisionId);
              		if (subDivisionList != null && subDivisionList.size() > 0) {
              			for (Object[] objects : subDivisionList) {
              				 AlertVO alertVO = new AlertVO();
              				 alertVO.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
              				 alertVO.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
              				 finalVoList.add(alertVO);
              				}					
      				}				
      			} catch (Exception e) {
      				LOG.error(" Exception Occured in getAllSubDivisionDetails() method, Exception - ",e);
      			}    	
              	return finalVoList;
              }
           
             
         	public ResultStatus updateSubTaskComment(final Long subTaskId,final String comment,final Long userId){
        		final ResultStatus rs = new ResultStatus();
        		try {
        			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
        				public void doInTransactionWithoutResult(TransactionStatus status) {
        					AlertDepartmentCommentNew adcn = new AlertDepartmentCommentNew();
        					adcn.setComment(comment);
        					adcn.setInsertedBy(userId);
        					adcn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
        					adcn = alertDepartmentCommentNewDAO.save(adcn);

        					GovtAlertSubTask gast = govtAlertSubTaskDAO.get(subTaskId);
        					
        					if(gast != null){
        						//save record in tracking
        						saveRecordIntoSubTaskTracking(gast,userId,adcn.getAlertDepartmentCommentId()+"",7l);
        						getSubTaskDetailsAndSendSMS(null,null,subTaskId,7L,userId,"",comment,userId);
        					}
        					rs.setExceptionMsg("success");
        				}
        			});
        			
        			
        		} catch (Exception e) {
        			rs.setExceptionMsg("failure");
        			LOG.error("Exception raised at updateSubTaskComment");
        		}
        		return rs;
        	}
        	
        	public ResultStatus updateSubTaskStatusComment(final Long subTaskId,final Long statusId,final String comment,final Long userId){
        		final ResultStatus rs = new ResultStatus();
        		try {
        			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
        				public void doInTransactionWithoutResult(TransactionStatus status) {
        					
        					GovtAlertSubTask gast = govtAlertSubTaskDAO.get(subTaskId);
        					if(gast != null && statusId != null && statusId.longValue()>0L){
        						gast.setAlertSubTaskStatusId(statusId);
        						gast.setUpdatedBy(userId);
        						gast.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
        						govtAlertSubTaskDAO.save(gast);
        					}
        					
        					AlertDepartmentCommentNew adcn = null;
        					if(comment != null && !comment.trim().isEmpty()){
        						adcn = new AlertDepartmentCommentNew();
        						adcn.setComment(comment);
        						adcn.setInsertedBy(userId);
        						adcn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
        						adcn = alertDepartmentCommentNewDAO.save(adcn);
        					}
        					
        					GovtOfficerSubTaskTracking gostt = new GovtOfficerSubTaskTracking();
        					gostt.setAlertAssignedOfficerId(gast.getAlertAssignedOfficerId());
        					gostt.setGovtAlertSubTaskId(gast.getGovtAlertSubTaskId());
        					gostt.setGovtAlertActionTypeId(6l);
        					gostt.setAlertSubTaskStatusId(statusId);
        					gostt.setAlertSeverityId(gostt.getAlertSeverityId());
        					
        					if(adcn != null)
        						gostt.setAlertDepartmentCommentId(adcn.getAlertDepartmentCommentId());
        					
        					gostt.setInsertedById(userId);
        					gostt.setInsertedTime(dateUtilService.getCurrentDateAndTime());
        					gostt.setIsDeleted("N");
        					govtOfficerSubTaskTrackingDAO.save(gostt);
        					
        					getSubTaskDetailsAndSendSMS(null,null,subTaskId,gostt.getGovtAlertActionTypeId(),userId,alertSubTaskStatusDAO.get(statusId).getStatus(),comment,userId);
        					
        					rs.setExceptionMsg("success");
        				}
        			});	
        			
        			
        		} catch (Exception e) {
        			rs.setExceptionMsg("failure");
        			LOG.error("Exception Occured in updateAlertStatusComment of  updateSubTaskStatusComment() ", e);
        		}
        		return rs;
        	}
        	
        	public ResultStatus updateSubTaskPriority(final Long subTaskId,final Long priorityId,final Long userId){
        		final ResultStatus rs = new ResultStatus();
        		try {
        			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
        				public void doInTransactionWithoutResult(TransactionStatus status) {
        					Integer count = govtAlertSubTaskDAO.updateSubTaskPriority(subTaskId,priorityId,userId,dateUtilService.getCurrentDateAndTime());
        					if(count != null && count > 0){
        						//save record in tracking
        						saveRecordIntoSubTaskTracking(govtAlertSubTaskDAO.get(subTaskId),userId,priorityId+"",5l);
        						getSubTaskDetailsAndSendSMS(null,null,subTaskId,5L,userId,alertSeverityDAO.get(priorityId).getSeverity(),"",userId);
        						rs.setExceptionMsg("success");
        					}
        				}
        			});
        			
        		} catch (Exception e) {
        			rs.setExceptionMsg("failure");
        			LOG.error("Exception raised at updateSubTaskPriority");
        		}
        		return rs;
        	}
        	
        	public ResultStatus updateSubTaskDueDate(final Long subTaskId,final String dueDate,final Long userId){
        		final ResultStatus rs = new ResultStatus();
        		try {
        			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
        				public void doInTransactionWithoutResult(TransactionStatus status) {
        					Integer count;
        					try {
        						count = govtAlertSubTaskDAO.updateSubTaskDueDate(subTaskId,new SimpleDateFormat("dd/MM/yyyy").parse(dueDate),userId,dateUtilService.getCurrentDateAndTime());
        						if(count != null && count > 0){
        							//save record in tracking
        							saveRecordIntoSubTaskTracking(govtAlertSubTaskDAO.get(subTaskId),userId,dueDate,4l);
        							//getSubTaskDetailsAndSendSMS(null,null,subTaskId,4L,userId);
        							rs.setExceptionMsg("success");
        						}
        					} catch (ParseException e) {
        						// TODO Auto-generated catch block
        						e.printStackTrace();
        					}
        				}
        			});
        			
        		} catch (Exception e) {
        			rs.setExceptionMsg("failure");
        			LOG.error("Exception raised at updateSubTaskPriority");
        		}
        		return rs;
        	}
        	
        	public void saveRecordIntoSubTaskTracking(GovtAlertSubTask gast,Long userId,String id,Long actionType){
        		try {
        			GovtOfficerSubTaskTracking gostt = new GovtOfficerSubTaskTracking();
        			gostt.setAlertAssignedOfficerId(gast.getAlertAssignedOfficerId());
        			gostt.setGovtAlertSubTaskId(gast.getGovtAlertSubTaskId());
        			gostt.setGovtAlertActionTypeId(actionType);
        			gostt.setAlertSubTaskStatusId(gast.getAlertSubTaskStatusId());
        			gostt.setAlertSeverityId(gostt.getAlertSeverityId());
        			
        			if(actionType == 7l)
        				gostt.setAlertDepartmentCommentId(Long.parseLong(id));
        			if(actionType == 5l)
        				gostt.setAlertSeverityId(Long.parseLong(id));
        			if(actionType == 4l)
        				gostt.setDueDate(new SimpleDateFormat("dd/MM/yyyy").parse(id));
        			if(actionType == 3l)
        				gostt.setAlertDepartmentDocumentId(Long.parseLong(id));
        			
        			gostt.setInsertedById(userId);
        			gostt.setInsertedTime(dateUtilService.getCurrentDateAndTime());
        			gostt.setIsDeleted("N");
        			govtOfficerSubTaskTrackingDAO.save(gostt);
        		} catch (Exception e) {
        			LOG.error("Exception raised at saveRecordIntoSubTaskTracking");
        		}
        	}
        	public ResultStatus uploadDocumentsForSubTask(final Map<File, String> mapfiles,final Long subTaskId,final Long userId){

        		final ResultStatus resultStatus = new ResultStatus();
        		try {
        			
        			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
        				public void doInTransactionWithoutResult(TransactionStatus status) {
        		String folderName = folderCreationForAlertsAttachmentNew();
        		//CustomReportFile customReportFile = null;
        		//CustomReport customReport = null;
        		
        		Calendar calendar = Calendar.getInstance();
        		calendar.setTime(new Date());
        		 int year = calendar.get(Calendar.YEAR);
        		 int month = calendar.get(Calendar.MONTH);
        		// int day = calendar.get(Calendar.DAY_OF_MONTH);
        		 int temp = month+1;
        		 String monthText = getMonthForInt(temp);
        		 SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_VALUE);
 				 String dateStr = sdf.format(new Date());
 				 String yearStr = String.valueOf(year);
        		
        		
        		 
        		 if(mapfiles != null && mapfiles.size() > 0){
        			GovtAlertSubTask gast = govtAlertSubTaskDAO.get(subTaskId);
        			 for (Map.Entry<File, String> entry : mapfiles.entrySet()){
        				 StringBuilder pathBuilder = new StringBuilder();
                		 StringBuilder str = new StringBuilder();
        				 Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
        				 String destPath = folderName+"/"+randomNumber+"."+entry.getValue();
        				 pathBuilder.append("alerts_attachments/").append(yearStr).append("/").append(dateStr).append("/").append(randomNumber).append(".").append(entry.getValue());
        				 //pathBuilder.append("alerts_attachments/"+randomNumber+"."+entry.getValue());
        				// pathBuilder.append(monthText).append("-").append(year).append("/").append(randomNumber).append(".")
        				// .append(entry.getValue());
        				 str.append(randomNumber).append(".").append(entry.getValue());
        				String fileCpyStts = activityService.copyFile(entry.getKey().getAbsolutePath(),destPath);
        				 
        					if(fileCpyStts.equalsIgnoreCase("error")){
        						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
        						LOG.error(" Exception Raise in copying file");
        						throw new ArithmeticException();
        					}
        					
        					AlertDepartmentDocumentNew addn = new AlertDepartmentDocumentNew();
        					addn.setDocument(pathBuilder.toString());
        					addn.setInsertedBy(userId);
        					addn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
        					addn = alertDepartmentDocumentNewDAO.save(addn);
        					
        					//save record in tracking
        					saveRecordIntoSubTaskTracking(gast,userId,addn.getAlertDepartmentDocumentId()+"",3l);
        					//getSubTaskDetailsAndSendSMS(null,null,subTaskId,3L,userId);
        			 }
        		 }
        		
        		 resultStatus.setResultCode(0);
        		// resultStatus.setResultState(customReportFile.getCustomReportFileId());
        		 resultStatus.setMessage("success");
        				}
        			});
        		}catch (Exception e) {
        			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
        			resultStatus.setMessage("failure");
        			LOG.error(" Exception Occured in saveCustomReportUploadFile() method, Exception - ",e);
        		}
        		return resultStatus;
        	
        	}
        	
        	public List<AlertTrackingVO> getSubTaskDetails(Long alertId){
        		List<AlertTrackingVO> returnList = null;
        		try {
        			List<Long> alertIds = new ArrayList<Long>(0);
        			alertIds.add(alertId);
					List<Long> subTasksAlertIdsList = govtAlertSubTaskDAO.getSubTasksIdsList(alertIds);
					if(commonMethodsUtilService.isListOrSetValid(subTasksAlertIdsList)){
						returnList = new ArrayList<AlertTrackingVO>(0);
						for (Long subtaskId : subTasksAlertIdsList) {
							returnList.addAll(viewSubTaskHistory(subtaskId));							
						}
					}
				} catch (Exception e) {
					LOG.error(" Exception Occured in getSubTaskDetails() method, Exception - ",e);
				}        		
        		return returnList;
        	}
        	public List<AlertTrackingVO> viewSubTaskHistory(Long subTaskId){
        		List<AlertTrackingVO> finalList = new ArrayList<AlertTrackingVO>(0);
        		SimpleDateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        		SimpleDateFormat dateSdf = new SimpleDateFormat("dd-MM-yyyy");
        		SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm");
        		try {
        			
        			List<GovtOfficerSubTaskTracking> qryRstList = govtOfficerSubTaskTrackingDAO.getModelForSubTask(subTaskId);
        			
        			if(qryRstList != null && qryRstList.size() > 0){
        				
        				GovtAlertSubTask gast = govtAlertSubTaskDAO.get(subTaskId);
        				
        				for (GovtOfficerSubTaskTracking govtOfficerSubTaskTracking : qryRstList) {
        					
        					String userName = govtOfficerSubTaskTracking.getGovtAlertSubTask().getSubTaskGovtOfficer().getOfficerName()+" - "+govtOfficerSubTaskTracking.getGovtAlertSubTask().getSubTaskGovtOfficer().getMobileNo();
        					String designation = govtOfficerSubTaskTracking.getGovtAlertSubTask().getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getDesignationName()
        							+" & "+govtOfficerSubTaskTracking.getGovtAlertSubTask().getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getGovtDepartment().getDepartmentName();
        					
        					AlertTrackingVO matchedDateVO = getMatchedDateVO(finalList,dateSdf.format(dbSdf.parse(govtOfficerSubTaskTracking.getInsertedTime().toString())));
        					
        					if(matchedDateVO == null){
        						matchedDateVO = new AlertTrackingVO();
        						matchedDateVO.setUserName(userName);
        						matchedDateVO.setDesignation(designation);
        						matchedDateVO.setTitle(gast.getTitle().toString());
        						matchedDateVO.setAlertId(gast.getGovtAlertSubTaskId());
        						matchedDateVO.setDate(dateSdf.format(dbSdf.parse(govtOfficerSubTaskTracking.getInsertedTime().toString())));
        						finalList.add(matchedDateVO);
        					}
        					
        					matchedDateVO = getMatchedDateVO(finalList,dateSdf.format(dbSdf.parse(govtOfficerSubTaskTracking.getInsertedTime().toString())));
        					
        					AlertTrackingVO matchedTimeVO = getMatchedDateVO(matchedDateVO.getTimeList(),timeSdf.format(dbSdf.parse(govtOfficerSubTaskTracking.getInsertedTime().toString())));
        					if(matchedTimeVO == null){
        						matchedTimeVO = new AlertTrackingVO();
        						matchedTimeVO.setDate(timeSdf.format(dbSdf.parse(govtOfficerSubTaskTracking.getInsertedTime().toString())));
        						matchedDateVO.setTitle(gast.getTitle().toString());
        						matchedDateVO.setAlertId(gast.getGovtAlertSubTaskId());
        						matchedDateVO.setUserName(userName);
        						matchedDateVO.setDesignation(designation);
        						matchedDateVO.getTimeList().add(matchedTimeVO);
        					}
        					
        					matchedTimeVO = getMatchedDateVO(matchedDateVO.getTimeList(),timeSdf.format(dbSdf.parse(govtOfficerSubTaskTracking.getInsertedTime().toString())));
        					
        					if(govtOfficerSubTaskTracking.getAlertDepartmentDocumentId() != null && govtOfficerSubTaskTracking.getAlertDepartmentDocumentId() > 0l && govtOfficerSubTaskTracking.getAlertDepartmentDocument() != null){
        						AlertTrackingVO vo = new AlertTrackingVO();
        						vo.getStrList().add(govtOfficerSubTaskTracking.getAlertDepartmentDocument().getDocument());
        						vo.setTitle(gast.getTitle().toString());
        						vo.setAlertId(gast.getGovtAlertSubTaskId());
        						vo.setUserName(userName);
        						vo.setDesignation(designation);
        						matchedTimeVO.getAttachementsList().add(vo);
        					}
        					
        					if(govtOfficerSubTaskTracking.getAlertDepartmentCommentId() != null && govtOfficerSubTaskTracking.getAlertDepartmentCommentId() > 0l && govtOfficerSubTaskTracking.getAlertDepartmentComment() != null){
        						AlertTrackingVO vo = new AlertTrackingVO();
        						vo.getStrList().add(govtOfficerSubTaskTracking.getAlertDepartmentComment().getComment());
        						vo.setTitle(gast.getTitle().toString());
        						vo.setAlertId(gast.getGovtAlertSubTaskId());
        						vo.setUserName(userName);
        						vo.setDesignation(designation);
        						matchedTimeVO.getCommentList().add(vo);
        					}
        					
        					if(govtOfficerSubTaskTracking.getDueDate() != null && !govtOfficerSubTaskTracking.getDueDate().toString().trim().isEmpty()){
        						AlertTrackingVO vo = new AlertTrackingVO();
        						vo.getStrList().add(govtOfficerSubTaskTracking.getDueDate().toString());
        						vo.setTitle(gast.getTitle().toString());
        						vo.setAlertId(gast.getGovtAlertSubTaskId());
        						vo.setUserName(userName);
        						vo.setDesignation(designation);
        						matchedTimeVO.getDueDateList().add(vo);
        					}
        					
        					if(govtOfficerSubTaskTracking.getAlertSubTaskStatusId() != null && govtOfficerSubTaskTracking.getAlertSubTaskStatusId() > 0l && govtOfficerSubTaskTracking.getAlertSubTaskStatusId() != null){
        						AlertTrackingVO vo = new AlertTrackingVO();
        						vo.getStrList().add(govtOfficerSubTaskTracking.getAlertSubTaskStatus().getStatus());
        						vo.setTitle(gast.getTitle().toString());
        						vo.setAlertId(gast.getGovtAlertSubTaskId());
        						vo.setUserName(userName);
        						vo.setDesignation(designation);
        						matchedTimeVO.getStatusList().add(vo);
        					}
        					
        					if(govtOfficerSubTaskTracking.getAlertSeverityId() != null && govtOfficerSubTaskTracking.getAlertSeverityId() > 0l && govtOfficerSubTaskTracking.getAlertSeverity() != null){
        						AlertTrackingVO vo = new AlertTrackingVO();
        						vo.getStrList().add(govtOfficerSubTaskTracking.getAlertSeverity().getSeverity());
        						vo.setTitle(gast.getTitle().toString());
        						vo.setAlertId(gast.getGovtAlertSubTaskId());
        						vo.setUserName(userName);
        						vo.setDesignation(designation);
        						matchedTimeVO.getPriorityList().add(vo);
        					}
        					
        				}
        			}
        			
        		} catch (Exception e) {
        			LOG.error(" Exception Occured in viewAlertHistory() method, Exception - ",e);
        		}
        		return finalList;
        	}
        	public List<AlertTrackingVO> getSubTaskStatusHistory(Long subTaskId){
        		List<AlertTrackingVO> voList = new ArrayList<AlertTrackingVO>(0);
        		try {
        			//0-status,1-comment,2-date,3-officerName,4-mobileNo,5-designationName,6-departmentName
        			List<Object[]> objList = govtOfficerSubTaskTrackingDAO.getSubTaskStatusHistory(subTaskId);
        			
        			/*if(objList != null && objList.size() > 0){
        				for (Object[] objects : objList) {
        					AlertTrackingVO vo = new AlertTrackingVO();
        					vo.setStatus(objects[0] != null ? objects[0].toString():"");
        					vo.setComment(objects[1] != null ? objects[1].toString():"");
        					vo.setDate(objects[2] != null ? objects[2].toString():"");
        					vo.setUserName((objects[3] != null ? objects[3].toString():"")+""+(objects[4] != null ? " - "+objects[4].toString():""));
        					vo.setDesignation((objects[5] != null ? objects[5].toString():"")+""+(objects[6] != null ? " - "+objects[6].toString():""));
        					voList.add(vo);
        					
        				}
        			}*/
        			
        			Map<String,AlertTrackingVO> alertMap = new LinkedHashMap<String, AlertTrackingVO>(0);
       				if(objList != null && objList.size() > 0){
       					for (Object[] objects : objList) {
       						AlertTrackingVO vo = new AlertTrackingVO();
       						
       						vo.setStatus(objects[0] != null ? objects[0].toString():"");
       						vo.setComment(objects[1] != null ? objects[1].toString():"");
       						vo.setDate(objects[2] != null ? objects[2].toString():"");
       						vo.setUserName(objects[3] != null ? objects[3].toString():""+" - "+objects[4] != null ? objects[4].toString():"");
       						vo.setMobileNO(commonMethodsUtilService.getStringValueForObject(objects[4]));
       						vo.setDesignation(commonMethodsUtilService.getStringValueForObject(objects[5]));
       						vo.setDeptName(commonMethodsUtilService.getStringValueForObject(objects[6]));
       						//vo.setUserId(commonMethodsUtilService.getLongValueForObject(objects[7]));
       						vo.setLocation(commonMethodsUtilService.getStringValueForObject(objects[7]));
       						
       						AlertTrackingVO tempVO = alertMap.get(vo.getDate()+"-"+vo.getComment());
       						if( tempVO!= null && tempVO.getDate().equalsIgnoreCase(vo.getDate()) && tempVO.getComment().equalsIgnoreCase(vo.getComment()))
       						{
       							if(!tempVO.getDeptName().contains(vo.getDeptName())){
       								if(tempVO.getDeptName().trim().length() > 0 && !tempVO.getDeptName().trim().isEmpty()){
       									vo.setDeptName(tempVO.getDeptName()+", "+vo.getDeptName());
       								}else{
       									vo.setDeptName(vo.getDeptName());
       								}
       							}
       						}
       						alertMap.put(vo.getDate()+"-"+vo.getComment(), vo);
       					}
       					
       					if(commonMethodsUtilService.isMapValid(alertMap)){
       						voList.addAll(alertMap.values());
       					}
       				}
       				
        		} catch (Exception e) {
        			LOG.error("Error occured getSubTaskStatusHistory() method of AlertManagementSystemService{}");
        		}
        		return voList;
        	} 
        	
        	
        public List<AlertTrackingVO> getSubTaskInfoForAlert(Long alertId,Long userId){
        	List<AlertTrackingVO> voList = new ArrayList<AlertTrackingVO>(0); 
        	try {
        		List<Object[]> objList = govtAlertSubTaskDAO.getSubTaskInfoForAlert(alertId);
        		Map<Long,AlertTrackingVO> tempMap = new LinkedHashMap<Long, AlertTrackingVO>(0);
        		List<Long> assignedToList  =new ArrayList<Long>(0);
        		Map<Long,String> userLocationMap = new HashMap<Long, String>(0);
        		Map<Long,Long> subtaskUserMap = new HashMap<Long, Long>(0);
        		
        		if(objList != null && objList.size() > 0){
        			for (Object[] obj : objList) {
        				Long subTaskGovtOfficerId  = commonMethodsUtilService.getLongValueForObject(obj[6]);
        				Long govtDepartmentDesignationOfficerId  = commonMethodsUtilService.getLongValueForObject(obj[7]);
        				List<Object[]> userList = govtDepartmentDesignationOfficerDetailsDAO.getUserIdForDeptDesigOfficerIdAndGovtOfficerId(subTaskGovtOfficerId,govtDepartmentDesignationOfficerId);
        				if(commonMethodsUtilService.isListOrSetValid(userList)){
        					for (Object[] param : userList) {
        						subtaskUserMap.put(commonMethodsUtilService.getLongValueForObject(obj[0]), commonMethodsUtilService.getLongValueForObject(param[0]));
        						if(!assignedToList.contains(commonMethodsUtilService.getLongValueForObject(param[0])))
                    				assignedToList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
							}
        				}
        			}
        			
        			if(commonMethodsUtilService.isListOrSetValid(assignedToList)){
        				List<Object[]> userdtls = govtDepartmentDesignationOfficerDetailsNewDAO.getDesignationNameForUsers(assignedToList);
        				if(commonMethodsUtilService.isListOrSetValid(userdtls)){
        					String location="";
        					for (Object[] param : userdtls) {
    							Long locationTypeId = commonMethodsUtilService.getLongValueForObject(param[3]);
    							Long scopeValue = commonMethodsUtilService.getLongValueForObject(param[4]);
    							Long assignTO = commonMethodsUtilService.getLongValueForObject(param[6]);
    							
    							if(locationTypeId != null && locationTypeId.longValue()>0L){
    								GovtDepartmentWorkLocation workLocation = govtDepartmentWorkLocationDAO.get(scopeValue);
    								if(workLocation != null)
    									location=workLocation.getLocationName();
    							}
    							userLocationMap.put(assignTO, location);
    						}
        				}
        			}
        		}
        		
        		if(objList != null && objList.size() > 0){
        			List<Long> subTaskIds = new ArrayList<Long>(0);
        			for (Object[] obj : objList) {
            		
        				AlertTrackingVO vo = new AlertTrackingVO();
        				vo.setUserId(commonMethodsUtilService.getLongValueForObject(obj[0]));
        				vo.setUserName(commonMethodsUtilService.getStringValueForObject(obj[1]));
        				vo.setStatus(commonMethodsUtilService.getStringValueForObject(obj[4]));
						vo.setColor(commonMethodsUtilService.getStringValueForObject(obj[5]));
        				vo.setAlertId(commonMethodsUtilService.getLongValueForObject(obj[0]));
        				vo.setTitle(commonMethodsUtilService.getStringValueForObject(obj[1]));
        				vo.setDate(commonMethodsUtilService.getStringValueForObject(obj[3]));
        				vo.setAlertTypeStr("others");//default sub task owner is other person , by comparing login userid
        				tempMap.put((Long)obj[0], vo);
        				subTaskIds.add(commonMethodsUtilService.getLongValueForObject(obj[0]));
					}
        			
        			List<Long> govtDeptDesigOfficerIdList = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOfficerIdListByUserId(userId);
        			List<Object[]> subTaskOwnersList = govtAlertSubTaskDAO.getGovtDeptDesigOfficerIdsListBySubTaskId(subTaskIds);
        			
        			if(commonMethodsUtilService.isListOrSetValid(subTaskOwnersList)){
        				for (Object[] param : subTaskOwnersList) {
        					AlertTrackingVO vo  = tempMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
        					if(vo != null){
        						if(govtDeptDesigOfficerIdList.contains(commonMethodsUtilService.getLongValueForObject(param[1]))){
        							vo.setAlertTypeStr("owner");//setting sub task owner is owner of this subtask , by comparing login userid
        						}
        					}
						}
        			}
        			
        			
        			List<Object[]> officersList = govtOfficerSubTaskTrackingDAO.getSubTasksCommentsAndStatusHistory(subTaskIds);
        			if(officersList != null && officersList.size() > 0){
        				for (Object[] objects : officersList) {
							if(tempMap.get((Long)objects[0]) != null){
								AlertTrackingVO vo  = tempMap.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
								if(vo != null){
									if(!commonMethodsUtilService.getStringValueForObject(objects[2]).isEmpty())
										vo.getCommentList().add(new AlertTrackingVO(commonMethodsUtilService.getLongValueForObject(objects[0]), 
											 commonMethodsUtilService.getStringValueForObject(objects[2]),commonMethodsUtilService.getStringValueForObject(objects[3])));
									vo.setUserName(commonMethodsUtilService.getStringValueForObject(objects[4]));
									vo.setMobileNO(commonMethodsUtilService.getStringValueForObject(objects[5]));
									vo.setDesignation(commonMethodsUtilService.getStringValueForObject(objects[6]));
									vo.setDeptName(commonMethodsUtilService.getStringValueForObject(objects[7]));
									Long assignedTo = subtaskUserMap.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
									vo.setLocation(userLocationMap.get(assignedTo) != null ? userLocationMap.get(assignedTo) :"");
								}
							}
						}
        			}
        			
        			
        			List<Object[]> objectsList = govtOfficerSubTaskTrackingDAO.getCommentsForSubTasks(subTaskIds);
        			if(objectsList != null && objectsList.size() > 0){
        				for (Object[] objects : objectsList) {
							if(tempMap.get((Long)objects[0]) != null){
								tempMap.get((Long)objects[0]).setCount((Long)objects[1]);
							}
						}
        			}
        			
        		}
        		
        		if(tempMap != null && tempMap.size() > 0){
        			AlertTrackingVO returnVO = new AlertTrackingVO();
        			for (Long subTaskId : tempMap.keySet()) {
        				AlertTrackingVO vo  = tempMap.get(subTaskId);
    					if(vo != null){
    						if(vo.getAlertTypeStr().trim().equalsIgnoreCase("owner"))
    							returnVO.getAttachementsList().add(vo);
    						else
    							returnVO.getCommentList().add(vo);
    					}
					}
        			voList.add(returnVO);
        		}
			} catch (Exception e) {
				LOG.error("Error occured getSubTaskInfoForAlert() method of AlertManagementSystemService");
			}	
        	return voList;
        } 
        	
        public List<AlertTrackingVO> getCommentsForAlert(Long alertId){
        	List<AlertTrackingVO> voList = new ArrayList<AlertTrackingVO>(0);
        	try {
        		Set<Long> uniqueCommentIds = new HashSet<Long>();
				List<Object[]> objList = alertAssignedOfficerTrackingNewDAO.getCommentsForAdminCommentsAlert(alertId);
				List<Object[]> commentsList = alertAssignedOfficerTrackingNewDAO.getCommentsForAlert(alertId);
				if(commentsList != null && commentsList.size() > 0){
					for (Object[] objects : commentsList) {
						Long commentId = Long.valueOf(objects[0] != null ? objects[0].toString():"0");
						if(!uniqueCommentIds.contains(commentId)){
							AlertTrackingVO vo = new AlertTrackingVO();
	   						vo.setComment(objects[1] != null ? objects[1].toString():"");
	   						vo.setDate(objects[2] != null ? objects[2].toString():"");
	   						vo.setUserName(objects[3] != null ? objects[3].toString():""+" - "+objects[3] != null ? objects[1].toString():"");
	   						vo.setMobileNO(commonMethodsUtilService.getStringValueForObject(objects[4]));
	   						vo.setDesignation(commonMethodsUtilService.getStringValueForObject(objects[5]));
	   						vo.setDeptName(commonMethodsUtilService.getStringValueForObject(objects[6]));
	   						vo.setLocation(commonMethodsUtilService.getStringValueForObject(objects[7]));
	   						voList.add(vo);
	   						uniqueCommentIds.add(commentId);
						}
					}
				}
				
				if(objList != null && objList.size() > 0){
					for (Object[] objects : objList) {
						Long commentId = Long.valueOf(objects[0] != null ? objects[0].toString():"0");
						if(!uniqueCommentIds.contains(commentId)){
							AlertTrackingVO vo = new AlertTrackingVO();
	   						vo.setComment(objects[1] != null ? objects[1].toString():"");
	   						vo.setDate(objects[2] != null ? objects[2].toString():"");
	   						vo.setUserName(objects[3] != null ? objects[3].toString():""+" - "+objects[3] != null ? objects[1].toString():"");
	   						vo.setMobileNO(commonMethodsUtilService.getStringValueForObject(objects[4]));
	   						vo.setDesignation(commonMethodsUtilService.getStringValueForObject(objects[5]));
	   						vo.setDeptName(commonMethodsUtilService.getStringValueForObject(objects[6]));
	   						vo.setLocation(commonMethodsUtilService.getStringValueForObject(objects[7]));
	   						voList.add(vo);
	   						uniqueCommentIds.add(commentId);
						}
					}
				}
				
			} catch (Exception e) {
				LOG.error("Error occured getCommentsForAlert() method of AlertManagementSystemService");
			}
        	return voList;
        }
        
         /*
      	 * Swadhin K Lenka
      	 * overview  click
      	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getStateThenGovtDeptScopeWiseAlertCount(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long)
      	 */
      	public List<AlertCoreDashBoardVO> getStateThenGovtDeptScopeWiseAlertCountOnClick(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long locationId, Long childLocationId,String category){
      		try{
      			
      			Date fromDate = null;
      			Date toDate = null;
      			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
      				fromDate = sdf.parse(fromDateStr);
      				toDate = sdf.parse(toDateStr);
      			}
      			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
      			if(printIdList != null && printIdList.size() > 0){  
      				if(electronicIdList != null && electronicIdList.size() == 0){
      					electronicIdList.add(0L);
      				}
      			}else if(electronicIdList != null && electronicIdList.size() > 0){
      				if(printIdList != null && printIdList.size() == 0){
      					printIdList.add(0L);
      				}
      			}/*else{
      				electronicIdList.add(0L);
      				printIdList.add(0L);
      			}*/
      			
      			List<Long> levelValues = new ArrayList<Long>();    
      			Long levelId = 0L;
      			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
      			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
      				for(Object[] param : lvlValueAndLvlIdList){
      					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
      					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
      				}
      			}
      			
      			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
    			List<Long> deptScopeIdList = new ArrayList<Long>();
    			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
    				for(Object [] param : childDeptScopeIdList){
    					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
    				}
    			} 
    			List<Long> alertList = null;
    			if(category != null && category.trim().isEmpty() && !category.trim().equalsIgnoreCase("overview")){
    				alertList = alertAssignedOfficerNewDAO.getLocationThenGovtDeptScopeWiseAlertCountOnClick(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,deptScopeIdList,parentGovtDepartmentScopeId,locationId,childLocationId);

    			}else{
    				alertList = alertAssignedOfficerNewDAO.getLocationThenGovtDeptScopeWiseAlertCountForStatusForClick(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,deptScopeIdList,parentGovtDepartmentScopeId,locationId,childLocationId);

    			}
      			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
      			if(alertList != null && alertList.size() > 0){
    				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertList));
    				setAlertDtls(alertCoreDashBoardVOs, list); 
    			}
      			return alertCoreDashBoardVOs;    
      			
      		}catch(Exception e){
      			e.printStackTrace();    
      		}
      		return null;
      	}
      	public List<IdNameVO> getStatusCompletionInfoForSubTask(Long alertId,Long subTaskId,Long userId){
      		try{
      			List<IdNameVO> finalList = new ArrayList<IdNameVO>();
      			
      			GovtAlertSubTask govtAlertSubTask = govtAlertSubTaskDAO.get(subTaskId);
      			
      			Long assignedByOfficerId=govtAlertSubTask.getCreatedBy();//sub task assigned by 
      			Long assignedToOfficerId = govtAlertSubTask.getGovtDepartmentDesignationOfficerId();
      			String subTaskUserTypeStr ="other";
      			
      			//get all govt dept desig off ids
        		List<Long> govtDeptDesigOfficerIdList = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOfficerIdListByUserId(userId);
      			
      			if(assignedByOfficerId != null && userId != null && assignedByOfficerId.longValue() == userId.longValue()){
      				subTaskUserTypeStr="assignedBy";//setting sub task owner is owner of this subtask , by comparing login userid
				}
      			else if(govtDeptDesigOfficerIdList.contains(assignedToOfficerId)){
      				subTaskUserTypeStr="assignedTo";//setting sub task owner is owner of this subtask , by comparing login userid
				}
      			
      			Long currentStatusId = govtAlertSubTask.getAlertSubTaskStatusId();
      			
        		//get govt dept desig off id by alertId
        		Long govtDeptDesigOfficerId = alertAssignedOfficerNewDAO.getGovtDeptDesigOfficerIdListByUserId(alertId);
        		
        		//to check whether the logedin user is owner of the subtask or not.
        		
        		Long govtDeptDesigOfficerId2 = govtAlertSubTaskDAO.getGovtDeptDesigOfficerIdBySubTaskId(subTaskId);
        		
        		
        		String isAccess = "";
        		//if(govtDeptDesigOfficerIdList != null && govtDeptDesigOfficerId != null && govtDeptDesigOfficerIdList.size() > 0 && govtDeptDesigOfficerIdList.contains(govtDeptDesigOfficerId)){
        		if(subTaskUserTypeStr.equalsIgnoreCase("assignedBy")){
        			if(govtAlertSubTask.getAlertSubTaskStatusId() != null && govtAlertSubTask.getAlertSubTaskStatusId().longValue() == 3L){
        				isAccess = "true";
        			}
        			
        			Long[] availableIdsArr = {6L,7L};// reopen, closed 
        			
        			List<Long> availableIdsList = new ArrayList<Long>(0);
        			availableIdsList.addAll(Arrays.asList(availableIdsArr));
        			
        			List<AlertSubTaskStatus> objList = alertSubTaskStatusDAO.getAll();
        			if(objList != null && objList.size() > 0){
        				for (AlertSubTaskStatus param : objList) {
        					if(currentStatusId.longValue() != param.getAlertSubTaskStatusId().longValue()){
	        					IdNameVO VO = new IdNameVO();
	        					VO.setId(param.getAlertSubTaskStatusId());
	                			VO.setName(param.getStatus());
	                			if(availableIdsList.contains(VO.getId()))
	                				finalList.add(VO);
        					}
    					}
        			}
        			
        		//}else if(govtDeptDesigOfficerIdList != null && govtDeptDesigOfficerId2 != null && govtDeptDesigOfficerIdList.size() > 0 && govtDeptDesigOfficerIdList.contains(govtDeptDesigOfficerId2)){
        		}if(subTaskUserTypeStr.equalsIgnoreCase("assignedTo")){
        			
        			Long[] availableIdsArr = {1L,2L,3L,4L,5L};
        			
        			List<Long> availableIdsList = new ArrayList<Long>(0);
        			availableIdsList.addAll(Arrays.asList(availableIdsArr));
        			
        			List<AlertSubTaskStatus> objList = alertSubTaskStatusDAO.getAll();
        			if(objList != null && objList.size() > 0){
        				for (AlertSubTaskStatus param : objList) {
        					if(currentStatusId.longValue() != param.getAlertSubTaskStatusId().longValue()){
	        					IdNameVO VO = new IdNameVO();
	        					VO.setId(param.getAlertSubTaskStatusId());
	                			VO.setName(param.getStatus());
	                			if(availableIdsList.contains(VO.getId()))
	                				finalList.add(VO);
        					}
    					}
        			}
        			
        		}
        		Long alertSubStatusId = govtAlertSubTask.getAlertSubTaskStatusId();
        		
        		if(!commonMethodsUtilService.isListOrSetValid(finalList)){
        			IdNameVO VO = new IdNameVO();
					VO.setId(commonMethodsUtilService.getLongValueForObject(alertSubStatusId));
        			VO.setName(govtAlertSubTask.getAlertSubTaskStatus().getStatus());
        			finalList.add(VO);
        		}
        		
        		String userName = "";
        		String locationName = "";
        		String deptName = "";
        		String desigName  = "";
        		
    			List<Object[]> desigList = govtDepartmentDesignationOfficerDetailsNewDAO.getLocationNameByAssignedOficer(assignedByOfficerId);
    			if(desigList != null && desigList.size() > 0){
    				for (Object[] objects : desigList) {
    					userName = commonMethodsUtilService.getStringValueForObject(objects[0]);
    					locationName = commonMethodsUtilService.getStringValueForObject(objects[3]);
    					deptName = commonMethodsUtilService.getStringValueForObject(objects[2]);
    					desigName = commonMethodsUtilService.getStringValueForObject(objects[1]);
    				}
    			}
    			if(finalList != null && finalList.size() > 0){
    				IdNameVO vo = finalList.get(0);
    				Long assignedUserId = govtAlertSubTask.getCreatedBy();
    				if(assignedUserId != null){
    					List<String> deptList = govtAlertDepartmentLocationNewDAO.getAccessDepartmentList(assignedUserId);
    					if(deptList != null && deptList.size() > 0){
    						StringBuilder strBuild = new StringBuilder();
    						if(deptList.size() > 3){
    							for(int i=1 ; i <= 3 ; i++){
    								strBuild.append(deptList.get(i));
    								strBuild.append(",");
    							}
    							strBuild.append("...");
    						}else{
    							for(String str : deptList){
    								strBuild.append(str);
    								strBuild.append(",");
    							}
    						}  
    						vo.setDeptName(strBuild.toString());
    					}
    				}
    				
    				//vo.setDeptName(govtAlertSubTask.getAlertAssignedOfficer().getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getGovtDepartment().getDepartmentName());
    				vo.setAssignedByOfficerStr(govtAlertSubTask.getAlertAssignedOfficer().getGovtOfficer().getOfficerName());
    				vo.setAssignedOfficerStr(govtAlertSubTask.getSubTaskGovtOfficer().getOfficerName());
    				//vo.setMobileNo(govtAlertSubTask.getAlertAssignedOfficer().getGovtOfficer().getMobileNo() != null ?govtAlertSubTask.getAlertAssignedOfficer().getGovtOfficer().getMobileNo():"");//mobile No for Assigned By
    				vo.setMobileNo(govtAlertSubTask.getSubTaskGovtOfficer().getMobileNo() != null ?govtAlertSubTask.getSubTaskGovtOfficer().getMobileNo():"");//mobile No for Assigned to
    				vo.setDesignation(desigName);
    				vo.setAlertId(subTaskId);
    				vo.setDescription(govtAlertSubTask.getAlert().getDescription());
    				vo.setMainTitle(govtAlertSubTask.getAlert().getTitle());
    				vo.setTitle(govtAlertSubTask.getTitle());
    				vo.setDateStr(govtAlertSubTask.getCreatedTime() != null ? govtAlertSubTask.getCreatedTime().toString().substring(0, 10):"");
    				vo.setDueDateStr(govtAlertSubTask.getDueDate() != null ? govtAlertSubTask.getDueDate().toString().substring(0, 10):"");
    				
    				vo.setIsAccess(isAccess);
    				vo.setStatus(alertSubTaskStatusDAO.get(currentStatusId).getStatus());
    				vo.setStatusId(commonMethodsUtilService.getLongValueForObject(alertSubStatusId));
    				vo.setColor(alertSubTaskStatusDAO.get(currentStatusId).getColor());
    				vo.setCategoryId(govtAlertSubTask.getAlert().getAlertCategoryId());
    				vo.setPositionName(locationName);//LocationName for Assigned By
    				vo.setCallerName(govtAlertSubTask.getGovtDepartmentDesignationOfficer().getLevelValueGovtDepartmentWorkLocation().getLocationName());//Location for assignedTo
    				vo.setBoardName(govtAlertSubTask.getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getGovtDepartment().getDepartmentName());//depart for assigned to
    				
        		/*	List<Long> subTaskIds = new ArrayList<Long>(0);
    				List<Long> userIds = new ArrayList<Long>(0);
    				subTaskIds.add(vo.getAlertId());
    				List<Object[]> officersList = govtOfficerSubTaskTrackingDAO.getSubTasksStatusHistory(subTaskIds);
        			if(officersList != null && officersList.size() > 0){
	    				for (Object[] objects : officersList) {
	    					//userIds.add(commonMethodsUtilService.getLongValueForObject(objects[11]));

							//}
					//}
        			//List<Object[]> deptList = govtOfficerSubTaskTrackingDAO.getSubTasksStatusHistoryByUser(userIds);
        			//if(deptList != null && deptList.size() > 0){
	    				//for (Object[] objects : deptList) {
								if(!commonMethodsUtilService.getStringValueForObject(objects[2]).isEmpty()){
									vo.getCommentList().add(new AlertTrackingVO(commonMethodsUtilService.getLongValueForObject(objects[0]), 
										 commonMethodsUtilService.getStringValueForObject(objects[2]),commonMethodsUtilService.getStringValueForObject(objects[3]),
										 commonMethodsUtilService.getStringValueForObject(objects[4]),commonMethodsUtilService.getStringValueForObject(objects[6]),commonMethodsUtilService.getStringValueForObject(objects[7]),commonMethodsUtilService.getStringValueForObject(objects[10])));
								}
								
								if(!commonMethodsUtilService.getStringValueForObject(objects[8]).isEmpty()){
									vo.getSubList().add(commonMethodsUtilService.getStringValueForObject(objects[8]));
								

							}
	    				}
        			}*/
    				List<Long> subTaskIds = new ArrayList<Long>(0);
    				Set<Long> userIds = new HashSet<Long>(0);
    				Map<Long,AlertTrackingVO> map = new LinkedHashMap<Long, AlertTrackingVO>(0);
    				subTaskIds.add(vo.getAlertId());
    				List<Object[]> officersList = govtOfficerSubTaskTrackingDAO.getSubTasksStatusHistory(subTaskIds);
        			if(officersList != null && officersList.size() > 0){
	    				for (Object[] objects : officersList) {
	    					userIds.add(commonMethodsUtilService.getLongValueForObject(objects[11]));
								if(!commonMethodsUtilService.getStringValueForObject(objects[2]).isEmpty()){
									vo.getCommentList().add(new AlertTrackingVO(commonMethodsUtilService.getLongValueForObject(objects[0]), 
										 commonMethodsUtilService.getStringValueForObject(objects[2]),commonMethodsUtilService.getStringValueForObject(objects[3]),
										 commonMethodsUtilService.getStringValueForObject(objects[4]),commonMethodsUtilService.getStringValueForObject(objects[6]),commonMethodsUtilService.getStringValueForObject(objects[7]),commonMethodsUtilService.getStringValueForObject(objects[10]),commonMethodsUtilService.getLongValueForObject(objects[11])));
								}
								
								if(!commonMethodsUtilService.getStringValueForObject(objects[8]).isEmpty()){
									vo.getSubList().add(commonMethodsUtilService.getStringValueForObject(objects[8]));
							}
	    	    		}
	            	}
	    		    
        			List<Object[]> deptList = govtDepartmentDesignationOfficerDetailsNewDAO.getDesigAndDepartForUser(new ArrayList<Long>(userIds));
        			if(deptList != null && deptList.size() > 0){
	    				for (Object[] object : deptList) {
	    					Long usrId = commonMethodsUtilService.getLongValueForObject(object[0]);
	    					AlertTrackingVO trackingVO = map.get(usrId);
	    					if(trackingVO == null){
	    						trackingVO  = new AlertTrackingVO();
	    						trackingVO.setUserName(commonMethodsUtilService.getStringValueForObject(object[1]));
	    						trackingVO.setDesignation(commonMethodsUtilService.getStringValueForObject(object[2]));
	    						trackingVO.setLocation(commonMethodsUtilService.getStringValueForObject(object[3]));
	    						trackingVO.setDeptName(commonMethodsUtilService.getStringValueForObject(object[4]));
	    						map.put(usrId,trackingVO);
	    					}
							}
	    				}
        			
        			if(vo.getCommentList() != null && !vo.getCommentList().isEmpty()){
        				for (AlertTrackingVO desigVO: vo.getCommentList()) {
        					Long usrId = desigVO.getUserId();
        					AlertTrackingVO finalVO = map.get(usrId);
        					if(finalVO != null){
        						desigVO.setUserName(finalVO.getUserName());
        						desigVO.setDesignation(finalVO.getDesignation());
        						desigVO.setLocation(finalVO.getLocation());
        						desigVO.setDeptName(finalVO.getDeptName());
        					}
						}
        			}
        			vo.setUserType(subTaskUserTypeStr);// assignedBy user or assignedTo user
        			
    			}
    			return 	finalList;
      		}catch(Exception e){
      			e.printStackTrace();
      		}
      		return null;
      	}
      	public List<IdNameVO>  getStatusCompletionInfo(Long alertId,Long levelValue,Long designationId,Long levelId,Long userId){
        	List<IdNameVO> finalList = new ArrayList<IdNameVO>();
        	try {
        		
        		Alert alert  = alertDAO.get(alertId);
        		
        		String userType = null;
        		//whether this alert is belongs to same logedin user or not.
        		//get all govt dept desig off ids
        		List<Long> govtDeptDesigOfficerIdList = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOfficerIdListByUserId(userId);
        		
        		//get govt dept desig off id by alertId
        		Long govtDeptDesigOfficerId = alertAssignedOfficerNewDAO.getGovtDeptDesigOfficerIdListByUserId(alertId);
        		
        		//whether this alert is belongs to just subordinate or not.
        		
        		//get all govt dept desig ids
        		List<Long> govtDeptDesigIdList = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigIdListByUserId(userId);
        		//get govt dept desig id by alertId
        		Long govtDeptDesigId2 = alertAssignedOfficerNewDAO.getGovtDeptDesigIdListByUserId(alertId); 
        		
        		//now check
        		List<Object[]> list2 = null;
        		if(govtDeptDesigIdList != null && govtDeptDesigIdList.size() > 0 && govtDeptDesigId2 != null && govtDeptDesigId2.longValue() > 0L){
        			list2 = govtDepartmentDesignationHierarchyDAO.getChildDesigData(govtDeptDesigIdList,govtDeptDesigId2);
        		}
        		
        		//to check same level designation.
        		//by alert id take scope.
        		Long govtDeptScopeIdForAlert = alertAssignedOfficerNewDAO.getGovtDeptScopeIdForAlert(alertId);
        		//by user Id take scope.
        		List<Long> govtDeptScopeIdsForUserId = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptScopeIdsForUserId(userId);
        		
        		//to wheck whether he/she is an admin or not.
        		//Long userCount = govtDepartmentDesignationOfficerNewDAO.getUserIdCount(userId);
        		List<String> entitlement = userGroupRelationDAO.getUserIdCount(userId);
	              String entlmnt = "";
	              if(entitlement != null && entitlement.size() > 0){
	            	 entlmnt = entitlement.get(0);
	              }
	              String userStatus = "";
	              if(entlmnt != null && !entlmnt.isEmpty() && entlmnt.equalsIgnoreCase("GOVT_DEPARTMENT_ADMIN_USER_GROUP_ENTITLEMENT_NEW")){
	                userStatus = "admin";
	              }else{
	                userStatus = "officer";    
	              }
        		if(govtDeptDesigOfficerIdList != null && govtDeptDesigOfficerId != null && govtDeptDesigOfficerIdList.size() > 0 && govtDeptDesigOfficerIdList.contains(govtDeptDesigOfficerId)){
        			userType ="own";
        			
        			List<Object[]> objList = alertDepartmentStatusDAO.getAlertGovtDepartmentStatus(alert.getGovtDepartmentId());
        			if(objList != null && objList.size() > 0){
        				for (Object[] objects : objList) {
        					IdNameVO VO = new IdNameVO();
        					VO.setId((Long)objects[0]);
                			VO.setName(objects[1].toString());
                			VO.setDateStr(objects[2] != null ? objects[2].toString():"");
                			finalList.add(VO);
						}
        			}
        			
        		}else if(list2 != null && list2.size() > 0 ){ 
        			
        			userType = "subUser";
        			
        			if(alert.getAlertStatusId().longValue() == 4l || alert.getAlertStatusId().longValue() == 11l || alert.getAlertStatusId().longValue() == 12l){//Completed Status  
        				//userType = "subUserStatus";
        				List<Object[]> listObj = alertStatusDAO.getAlertStatusInfoForReOpen();
            			if(listObj !=null && listObj.size()>0){
            				for (Object[] objects : listObj) {
    							IdNameVO vo = new IdNameVO();
    							vo.setId((Long)objects[0]);
    							vo.setName(objects[1].toString());
    							vo.setDateStr(objects[2] != null? objects[2].toString():null);
    							finalList.add(vo);
    						}
            			}
        			}else{
        				IdNameVO vo = new IdNameVO();        			
            			finalList.add(vo);            			
        			}  
        			
        		}else if(govtDeptScopeIdsForUserId != null && govtDeptScopeIdsForUserId.size() > 0 && govtDeptScopeIdForAlert != null && govtDeptScopeIdsForUserId.contains(govtDeptScopeIdForAlert)){
        			userType = "same";
        			IdNameVO vo = new IdNameVO();          			
        			finalList.add(vo);
        		}
        		else{
        			userType = "other";        			
        			IdNameVO vo = new IdNameVO();        			
        			finalList.add(vo);
        		}
        		
        		
        		if(finalList != null && finalList.size() > 0){
        			finalList.get(0).setApplicationStatus(userType+" - "+alert.getAlertStatusId());
        			finalList.get(0).setUserStatus(userStatus);
        		}
				
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Error occured getStatusCompletionInfo() method of AlertManagementSystemService",e);
			}
        	return finalList;
        }
      	      	
      	public AlertVO getSubTaskFullDetails(Long subTaskId){
      		AlertVO finalVO = new AlertVO();
      		try {
      			GovtAlertSubTask gast = govtAlertSubTaskDAO.get(subTaskId);
      			if(gast != null){
      				finalVO.setSubTaskId(gast.getGovtAlertSubTaskId());
      				finalVO.setAlertId(gast.getAlertId());
      				finalVO.setTitle(gast.getTitle());
      				finalVO.setDesc(gast.getDescription() != null ? gast.getDescription():"");
      				finalVO.setSeverity(gast.getAlertSeverityId() != null ? gast.getAlertSeverityId() : null);
      				finalVO.setDueDate(gast.getCreatedTime() != null ? gast.getDueDate().toString():"");
      				finalVO.setStatusId(gast.getAlertSubTaskStatusId());
      				finalVO.setDate1(new SimpleDateFormat("dd/MM/yyy").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse((gast.getCreatedTime().toString()))));
      				if(gast.getAlert().getAlertCategoryId() == 2l)
      					finalVO.setImageUrl(gast.getAlert().getImageUrl());
      				
      				//get sub task comment details
      				List<Object[]> objList = govtOfficerSubTaskTrackingDAO.getCommentDetialsForSubTasks(subTaskId);
      				if(objList != null && objList.size() > 0){
      					for (Object[] objects : objList) {
							IdNameVO voIn = new IdNameVO();
							voIn.setId((Long)objects[0]);
							voIn.setName(objects[1].toString());
							voIn.setDateStr(objects[3].toString());
							finalVO.getIdNamesList().add(voIn);
						}
      				}
      				
      				
      			}
			} catch (Exception e) {
				LOG.error("Error occured getSubTaskFullDetails() method of AlertManagementSystemService",e);
			}
      		return finalVO;
      	}
      	
      	public List<KeyValueVO> getDocumentsForAlert(Long alertId){
      		List<KeyValueVO> voList = new ArrayList<KeyValueVO>(0);
      		try {
				List<Object[]> objList = alertAssignedOfficerTrackingNewDAO.getDocumentsForAlert(alertId);
				if(objList != null && objList.size() > 0){
					for (Object[] objects : objList) {
						KeyValueVO vo = new KeyValueVO();
						vo.setId((Long)objects[0]);
						vo.setName(objects[1].toString());
						voList.add(vo);
					}
				}
			} catch (Exception e) {
				LOG.error("Error occured getDocumentsForAlert() method of AlertManagementSystemService",e);
			}
      		return voList;
      	}

      	
      	//district officer graphical click click
     	public List<AlertCoreDashBoardVO> getDistrictLevelWiseClick(String fromDateStr, String toDateStr, Long stateId, 
				                             List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
				                            Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
				                          Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId,String group,String searchType,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIdList,List<Long> sublevels){
    		List<AlertCoreDashBoardVO> finalVoList = new ArrayList<AlertCoreDashBoardVO>(0);
    		try {
    			Date fromDate = null;
      			Date toDate = null;
      			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
      				fromDate = sdf.parse(fromDateStr);
      				toDate = sdf.parse(toDateStr);
      			}
      			if(printIdList != null && printIdList.size() > 0){  
                    if(electronicIdList != null && electronicIdList.size() == 0){
                      electronicIdList.add(0L);
                      if(calCntrIdList != null && calCntrIdList.size() == 0){
                        calCntrIdList.add(0L);
                        }
                    }
                  } 
      			if(electronicIdList != null && electronicIdList.size() > 0){
                    if(printIdList != null && printIdList.size() == 0){
                      printIdList.add(0L);
                      if(calCntrIdList != null && calCntrIdList.size() == 0){
                        calCntrIdList.add(0L);
                        }
                    }
                  }
      			if(calCntrIdList != null && calCntrIdList.size() > 0){
                    if(printIdList != null && printIdList.size() == 0){
                      printIdList.add(0L);
                      if(electronicIdList != null && electronicIdList.size() == 0){
                        electronicIdList.add(0L);
                        }
                    }
                  }
      			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
      			List<Long> levelValues = new ArrayList<Long>();    
      			Long levlId = 0L;
      			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
      			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
      				for(Object[] param : lvlValueAndLvlIdList){
      					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
      					levlId = commonMethodsUtilService.getLongValueForObject(param[0]);
      				}
      			}
      			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
       			List<Long> deptScopeIdList = new ArrayList<Long>();
       			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
       				for(Object [] param : childDeptScopeIdList){
       					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
       				}
       			}
       			
       			if(commonMethodsUtilService.isListOrSetValid(sublevels)){
    				deptScopeIdList.clear();
					deptScopeIdList.addAll(sublevels);
				}
       			//start
    			List<Object[]> deptAndLvlList = govtDepartmentScopeLevelDAO.getAllScopesInAscOrder(govtDepartmentId);
    			//for level position create a map for scope and its position
    			Map<Long,Long> scopeIdAndLevelMap = new HashMap<Long,Long>();
    			Map<Long,Long> levelAndScopeIdMap = new HashMap<Long,Long>();
    			long position = 0l;
    			if(deptAndLvlList != null && deptAndLvlList.size() > 0){
    				for(Object[] param : deptAndLvlList){
    					position+=1;
    					scopeIdAndLevelMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), position);
    					levelAndScopeIdMap.put(position, commonMethodsUtilService.getLongValueForObject(param[1]));
    				}
    			}
    			//based on the dept scope get its position
    			position = scopeIdAndLevelMap.get(parentGovtDepartmentScopeId);
    			//end
       			
    			List<Long> alertIds = null;
    			if(position == 2l){
	   				if(alertType != null && alertType.equalsIgnoreCase("alert"))
	   						alertIds = alertAssignedOfficerNewDAO.getDistrictOfficerAlertsDetails(fromDate,toDate,stateId,electronicIdList,printIdList,levlId,levelValues
	   								,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,statusId,govtDeprtMentScopeId,calCntrIdList);
	   				else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
	   						alertIds = govtAlertSubTaskDAO.getStateAndDistrictWorkLocationGovtDeptScopeWiseSubTaskCountDetails(fromDate,toDate,stateId,electronicIdList,printIdList,levlId,levelValues,govtDepartmentId,
	   								parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,statusId,govtDeprtMentScopeId,calCntrIdList);
       			}else if(position == 3l){
       				Long justUpperLvl = levelAndScopeIdMap.get(position-1);
       				if(alertType != null && alertType.equalsIgnoreCase("alert"))
       					alertIds = alertAssignedOfficerNewDAO.getDivisionWorkLocationGovtDeptScopeWiseAlertCountDetails(fromDate,toDate,stateId,electronicIdList,printIdList,levlId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,statusId,govtDeprtMentScopeId,calCntrIdList,justUpperLvl);
       				else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
       					alertIds = govtAlertSubTaskDAO.getDivisionWorkLocationGovtDeptScopeWiseSubTaskDetails(fromDate,toDate,stateId,electronicIdList,printIdList,levlId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,statusId,govtDeprtMentScopeId,calCntrIdList,justUpperLvl);
       			}else if(position == 4l){
       				Long levelTwo = levelAndScopeIdMap.get(position-2);
					Long levelThree = levelAndScopeIdMap.get(position-1);
       				if(alertType != null && alertType.equalsIgnoreCase("alert"))
       					alertIds = alertAssignedOfficerNewDAO.getSubDivisionWorkLocationDeptScopeWiseAlertCountDetails(fromDate,toDate,stateId,electronicIdList,printIdList,levlId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,statusId,govtDeprtMentScopeId,calCntrIdList,levelTwo,levelThree);
       				else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
       					alertIds = govtAlertSubTaskDAO.getSubDivisionWorkLocationDeptScopeWiseSubTaskCountDetails(fromDate,toDate,stateId,electronicIdList,printIdList,levlId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,statusId,govtDeprtMentScopeId,calCntrIdList,levelTwo,levelThree);
       			}	
    		 if(alertIds != null && alertIds.size() > 0){
    				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIds));
    				setAlertDtls(finalVoList, list); 
    			}	
    		  setSubListCount(finalVoList, alertIds); 
    			
    		} catch (Exception e) {
    			LOG.error(" Exception Occured in getDistrictLevelWiseClick() method, Exception - ",e);
    		}		
    		return finalVoList;
    	}

          /*
  		 * Teja(non-Javadoc)
  		 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#stateLevelDeptOfficerStatusOverview()
  		 */
          public List<AlertVO> stateLevelDeptOfficerStatusOverview(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverity,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
          		LOG.info("Entered in stateLevelDeptOfficerStatusOverview() method of AlertManagementSystemService{}");
          		try{
          			Date fromDate = null;
          			Date toDate = null;
          			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
          			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
          				fromDate = sdf.parse(fromDateStr);
          				toDate = sdf.parse(toDateStr);
          			}
          			
          			prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Setting Parameter
          			
          			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
          			//get alert status count and and create a map of alertStatusId and its count
          			 List<Object[]> totalList = new ArrayList<Object[]>();
          			//List<Object[]> alertCountList = alertDAO.getTotalGovtPendingStatusAlertCnt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,"Status",calCntrIdList,null,null,socialMediaTypeIds);//for pending status
          		/*	if(alertCountList != null && alertCountList.size() > 0){
          				totalList.addAll(alertCountList);
          			}*/
          			List<Long> levelValues = new ArrayList<Long>();    
          			Long levelId = 0L;
          			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
          			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
          				for(Object[] param : lvlValueAndLvlIdList){
          					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
          					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
          				}
          			}
          			List<Object[]> alertCountList2 = alertAssignedOfficerNewDAO.getAlertCntByRequiredType(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"status",alertStatusIds,null,calCntrIdList,socialMediaTypeIds,alertSeverity,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
          			if(alertCountList2 != null && alertCountList2.size() > 0){
          				totalList.addAll(alertCountList2);
          			}
          			setAlertCount(totalList,finalAlertVOs); 
          			/*if(alertCountList != null && alertCountList.size() > 0){//Adding Pending Alert Count
          				AlertVO VO = null;
          				for(Object[] param:alertCountList){
          					 VO = new AlertVO();
          					 VO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
    						 VO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
    						 VO.setColor(commonMethodsUtilService.getStringValueForObject(param[2])); 
    						 VO.setAlertCnt(commonMethodsUtilService.getLongValueForObject(param[3]));
          				}
          				if(finalAlertVOs != null && finalAlertVOs.size() > 0){
          					finalAlertVOs.add(0, VO);
          				}
          			}*/
          			return finalAlertVOs; 
          		}catch(Exception e){
          			e.printStackTrace();
          			LOG.error("Error occured stateLevelDeptOfficerStatusOverview() method of AlertManagementSystemService{}");
          		}
          		return null;
          	}
         
          public List<AlertVO> stateLevelDeptOfficerLocationLevelOverview(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
      		LOG.info("Entered in stateLevelDeptOfficerLocationLevelOverview() method of AlertManagementSystemService{}");
      		try{
      			Date fromDate = null;
      			Date toDate = null;
      			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
      				fromDate = sdf.parse(fromDateStr);
      				toDate = sdf.parse(toDateStr);
      			}
      			
      			prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Setting Parameter
      			
      			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
      			//get all the alert status and build the template
      			List<Long> levelValues = new ArrayList<Long>();    
      			Long levelId = 0L;
      			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
      			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
      				for(Object[] param : lvlValueAndLvlIdList){
      					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
      					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
      				}
      			}
      			List<Object[]> rtrnObjLst = alertAssignedOfficerNewDAO.getAlertCntByRequiredType(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"Level",alertStatusIds,null,calCntrIdList,socialMediaTypeIds,alertSeverityIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
      			setAlertCount(rtrnObjLst,finalAlertVOs);
      			return finalAlertVOs; 
      		}catch(Exception e){
      			e.printStackTrace();
      			LOG.error("Error occured stateLevelDeptOfficerLocationLevelOverview() method of AlertManagementSystemService{}");
      		}
      		return null;
      	}
        public DistrictOfficeViewAlertVO getIASOfficerMyAlertsCountView(Long userId,String fromDateStr,String toDateStr,List<Long> printIdList, List<Long> electronicIdList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
      		
      		DistrictOfficeViewAlertVO returnVO = new DistrictOfficeViewAlertVO();
      		try{
      			Date fromDate = null;
      			Date toDate = null;
      			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
      				fromDate = sdf.parse(fromDateStr);
      				toDate = sdf.parse(toDateStr);
      			}
      			List<Long> levelValues = new ArrayList<Long>();    
      			Long levelId = 0L;
      			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
      			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
      				for(Object[] param : lvlValueAndLvlIdList){
      					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
      					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
      				}
      			}
      			prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
      			
      			List<Object[]> list1 = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOffrDetlsIdAndGovtOfcrId(userId,levelValues,levelId);
      			
      			List<Long> govtDepDesigOffcrIds = new ArrayList<Long>(0);
      			List<Long> govtOffcrIds =  new ArrayList<Long>(0);
      			if(commonMethodsUtilService.isListOrSetValid(list1)){
      				for( Object[]  obj :list1){
      					govtDepDesigOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[0]));
      					govtOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[1]));
      					returnVO.setGovtDeptDesigOffcrIds(govtDepDesigOffcrIds);
      					returnVO.setGovtOfficerIds(govtOffcrIds);
      					returnVO.setId(commonMethodsUtilService.getLongValueForObject(obj[1]));//officerId
      					returnVO.setName(commonMethodsUtilService.getStringValueForObject(obj[2]));//officerName
      					returnVO.setDepartmentId(commonMethodsUtilService.getLongValueForObject(obj[3]));//depId
      					returnVO.setDeptName(commonMethodsUtilService.getStringValueForObject(obj[4]));//deptName
      					returnVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(obj[5]));//designationId
      					returnVO.setDesigName(commonMethodsUtilService.getStringValueForObject(obj[6]));//designationName
      					
      				}
      			}
      			// My alerts view
      			List<Object[]> myAlertsTodayList = null;
      			List<Object[]> myAlertsCompletedList = null;
      			List<Object[]> myAlertsStatusList = null;
      			if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size()>0 && govtOffcrIds != null && govtOffcrIds.size() >0){
      				myAlertsTodayList = alertAssignedOfficerNewDAO.getDistrictOfficerMyAlertsCountView(govtDepDesigOffcrIds,govtOffcrIds,"today",new DateUtilService().getCurrentDateAndTime(),new DateUtilService().getCurrentDateAndTime(),printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
      				myAlertsCompletedList = alertAssignedOfficerNewDAO.getDistrictOfficerMyAlertsCountView(govtDepDesigOffcrIds,govtOffcrIds,"completed",fromDate,toDate,printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
      				myAlertsStatusList = alertAssignedOfficerNewDAO.getDistrictOfficerMyAlertsStatusWiseDetails(govtDepDesigOffcrIds, govtOffcrIds,fromDate,toDate,printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
      			}
      			setIASOfficerStatusWiseCountView(myAlertsTodayList,myAlertsCompletedList,myAlertsStatusList,returnVO,"MyAlerts");
      			
      		}catch(Exception e){
      			e.printStackTrace();
      			LOG.error("Exception Occured in AlertManagementSystemService of  getIASOfficerAlertsCountMainView() ", e);
      		}
      		return returnVO;
      	}
        public DistrictOfficeViewAlertVO getIASOfficerMySubTasksCountView(Long userId,String fromDateStr,String toDateStr,List<Long> printIdList, List<Long> electronicIdList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSubTaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
      		
      		DistrictOfficeViewAlertVO returnVO = new DistrictOfficeViewAlertVO();
      		try{
      			Date fromDate = null;
      			Date toDate = null;
      			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
      				fromDate = sdf.parse(fromDateStr);
      				toDate = sdf.parse(toDateStr);
      			}
      			List<Long> levelValues = new ArrayList<Long>();    
      			Long levelId = 0L;
      			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
      			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
      				for(Object[] param : lvlValueAndLvlIdList){
      					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
      					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
      				}
      			}
      			prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
      			
      			List<Object[]> list1 = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOffrDetlsIdAndGovtOfcrId(userId,levelValues,levelId);
      			
      			List<Long> govtDepDesigOffcrIds = new ArrayList<Long>(0);
      			List<Long> govtOffcrIds =  new ArrayList<Long>(0);
      			if(commonMethodsUtilService.isListOrSetValid(list1)){
      				for( Object[]  obj :list1){
      					govtDepDesigOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[0]));
      					govtOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[1]));
      					returnVO.setGovtDeptDesigOffcrIds(govtDepDesigOffcrIds);
      					returnVO.setGovtOfficerIds(govtOffcrIds);
      					returnVO.setId(commonMethodsUtilService.getLongValueForObject(obj[1]));//officerId
      					returnVO.setName(commonMethodsUtilService.getStringValueForObject(obj[2]));//officerName
      					returnVO.setDepartmentId(commonMethodsUtilService.getLongValueForObject(obj[3]));//depId
      					returnVO.setDeptName(commonMethodsUtilService.getStringValueForObject(obj[4]));//deptName
      					returnVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(obj[5]));//designationId
      					returnVO.setDesigName(commonMethodsUtilService.getStringValueForObject(obj[6]));//designationName
      					
      				}
      			}
      			// My alerts view
      			List<Object[]> mySubTasksTodayList = null;
      			List<Object[]> mySubTasksCompletedList = null;  
      			List<Object[]> mySubTasksStatusList = null;
      			if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() > 0 && govtOffcrIds != null && govtOffcrIds.size() >0){
      				mySubTasksTodayList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCountsView(govtDepDesigOffcrIds, govtOffcrIds,"today",new DateUtilService().getCurrentDateAndTime(),new DateUtilService().getCurrentDateAndTime(),printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,alertSubTaskStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
      				mySubTasksCompletedList = govtAlertSubTaskDAO.getDistrictOfficerAlertsSubTasksCountsView(govtDepDesigOffcrIds,govtOffcrIds,"completed",fromDate,toDate,printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,alertSubTaskStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
      				mySubTasksStatusList = govtAlertSubTaskDAO.getDistrictOfficerMySubTasksStatusWiseDetails(govtDepDesigOffcrIds, govtOffcrIds,fromDate,toDate,printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,alertSubTaskStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
      			}
      			setIASOfficerStatusWiseCountView(mySubTasksTodayList,mySubTasksCompletedList,mySubTasksStatusList,returnVO,"MyAlerts");
      			
      		}catch(Exception e){
      			e.printStackTrace();
      			LOG.error("Exception Occured in AlertManagementSystemService of  getIASOfficerAlertsCountMainView() ", e);
      		}
      		return returnVO;
      	} 
       public DistrictOfficeViewAlertVO getIASOfficerMyAssignedSubTasksCountView(Long userId,String fromDateStr,String toDateStr,List<Long> printIdList, List<Long> electronicIdList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSubTaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
      		
      		DistrictOfficeViewAlertVO returnVO = new DistrictOfficeViewAlertVO();
      		try{
      			Date fromDate = null;
      			Date toDate = null;
      			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
      				fromDate = sdf.parse(fromDateStr);
      				toDate = sdf.parse(toDateStr);
      			}
      			List<Long> levelValues = new ArrayList<Long>();    
      			Long levelId = 0L;
      			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
      			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
      				for(Object[] param : lvlValueAndLvlIdList){
      					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
      					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
      				}
      			}
      			
      			prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
      			
      			List<Object[]> list1 = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOffrDetlsIdAndGovtOfcrId(userId,levelValues,levelId);
      			
      			List<Long> govtDepDesigOffcrIds = new ArrayList<Long>(0);
      			List<Long> govtOffcrIds =  new ArrayList<Long>(0);
      			if(commonMethodsUtilService.isListOrSetValid(list1)){
      				for( Object[]  obj :list1){
      					govtDepDesigOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[0]));
      					govtOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[1]));
      					returnVO.setGovtDeptDesigOffcrIds(govtDepDesigOffcrIds);
      					returnVO.setGovtOfficerIds(govtOffcrIds);
      					returnVO.setId(commonMethodsUtilService.getLongValueForObject(obj[1]));//officerId
      					returnVO.setName(commonMethodsUtilService.getStringValueForObject(obj[2]));//officerName
      					returnVO.setDepartmentId(commonMethodsUtilService.getLongValueForObject(obj[3]));//depId
      					returnVO.setDeptName(commonMethodsUtilService.getStringValueForObject(obj[4]));//deptName
      					returnVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(obj[5]));//designationId
      					returnVO.setDesigName(commonMethodsUtilService.getStringValueForObject(obj[6]));//designationName
      					
      				}
      			}
      			// My alerts view
      			List<Object[]> myAssignedSubTasksTodayList = null;
      			List<Object[]> myAssignedSubTasksCompletedList = null;
      			List<Object[]> myAssignedSubTasksStatusList = null;
      			if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size() >0 && govtOffcrIds != null && govtOffcrIds.size() > 0){
      				myAssignedSubTasksTodayList = govtAlertSubTaskDAO.getDistrictOfficerMyAssignedSubTasksCountsView(govtDepDesigOffcrIds, govtOffcrIds,"today",new DateUtilService().getCurrentDateAndTime(),new DateUtilService().getCurrentDateAndTime(),printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,alertSubTaskStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
      				myAssignedSubTasksCompletedList = govtAlertSubTaskDAO.getDistrictOfficerMyAssignedSubTasksCountsView(govtDepDesigOffcrIds,govtOffcrIds,"completed",fromDate,toDate,printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,alertSubTaskStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
      				myAssignedSubTasksStatusList = govtAlertSubTaskDAO.getDistrictOfficerMyAssignedSubTasksStatusWiseDetails(govtDepDesigOffcrIds, govtOffcrIds,fromDate,toDate,printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,alertSubTaskStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
      			}
      			setIASOfficerStatusWiseCountView(myAssignedSubTasksTodayList,myAssignedSubTasksCompletedList,myAssignedSubTasksStatusList,returnVO,"MyAlerts");
      			
      		}catch(Exception e){
      			e.printStackTrace();
      			LOG.error("Exception Occured in AlertManagementSystemService of  getIASOfficerAlertsCountMainView() ", e);
      		}
      		return returnVO;
      	} 
      	public void setIASOfficerStatusWiseCountView(List<Object[]> myAlertsTodayList,List<Object[]> myAlertsCompletedList,
      			List<Object[]> statusList,
      			DistrictOfficeViewAlertVO returnVO,String alertType){
      		try{
      			List<DistrictOfficeViewAlertVO> todayFinalList = new ArrayList<DistrictOfficeViewAlertVO>(0);
      			List<DistrictOfficeViewAlertVO> completedFinalList = new ArrayList<DistrictOfficeViewAlertVO>(0);
      			List<DistrictOfficeViewAlertVO> statusFinalList = new ArrayList<DistrictOfficeViewAlertVO>(0);
      			
      			if(myAlertsTodayList != null && myAlertsTodayList.size() > 0){
      				for (Object[] objects : myAlertsTodayList) {
      					DistrictOfficeViewAlertVO todayVo = new DistrictOfficeViewAlertVO();
      						todayVo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
      						todayVo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
      						todayVo.setCount(commonMethodsUtilService.getLongValueForObject(objects[2]));
      					todayFinalList.add(todayVo);
					}
      			}
      			
      			if(myAlertsCompletedList != null && myAlertsCompletedList.size() > 0){
      				for (Object[] objects : myAlertsCompletedList) {
      					DistrictOfficeViewAlertVO completedVo = new DistrictOfficeViewAlertVO();
      						completedVo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
      						completedVo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
      						completedVo.setCount(commonMethodsUtilService.getLongValueForObject(objects[2]));
      					completedFinalList.add(completedVo);
					}
      			}
      			if(statusList != null && statusList.size() >0){
      				for (Object[] param : statusList) {
      				DistrictOfficeViewAlertVO statusVo = new DistrictOfficeViewAlertVO();
      					statusVo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
  						statusVo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
  						statusVo.setColor(commonMethodsUtilService.getStringValueForObject(param[2]));
  						statusVo.setCount(commonMethodsUtilService.getLongValueForObject(param[3]));
  					statusFinalList.add(statusVo);
					}
      			}
      			//if(alertType !=null && alertType.isEmpty() && alertType.equalsIgnoreCase("MyAlerts")){
      				//if(commonMethodsUtilService.isListOrSetValid(returnVO.getList1())){
      					DistrictOfficeViewAlertVO vo = new DistrictOfficeViewAlertVO();
      					vo.setSubList1(todayFinalList);
      					vo.setSubList2(completedFinalList);
      					vo.setSubList3(statusFinalList);
      					
      					returnVO.getList1().add(vo);
      				//}
      				if(returnVO.getList1() != null && returnVO.getList1().size() > 0){
      					for (DistrictOfficeViewAlertVO districtOfficeViewAlertVO : returnVO.getList1()){
      						Long totalCount = 0l;
      						if(districtOfficeViewAlertVO.getSubList3() != null && districtOfficeViewAlertVO.getSubList3().size() > 0){
      								for (DistrictOfficeViewAlertVO statusFinalVo : districtOfficeViewAlertVO.getSubList3()) {
      									totalCount = totalCount + statusFinalVo.getCount();
									}
      							}
      							districtOfficeViewAlertVO.setOverAllCnt(totalCount);//TotalCount
  							  if(districtOfficeViewAlertVO.getSubList3() != null && districtOfficeViewAlertVO.getSubList3().size() > 0){
								for (DistrictOfficeViewAlertVO statusVo : districtOfficeViewAlertVO.getSubList3()) {
									statusVo.setPerc(calculatePercantage(statusVo.getCount(),districtOfficeViewAlertVO.getOverAllCnt()));
								}
      					      }
      						}
      					}
      			//	}
      		}catch(Exception e){
      			e.printStackTrace();
      			LOG.error("Exception Occured in AlertManagementSystemService of  setIASOfficerStatusWiseCountView() ", e);
      		}
      	}
      	public List<AlertVO> getTemplate(List<Object[]> tempList){
      		List<AlertVO> finalList = new ArrayList<AlertVO>(0);
      		Map<Long,AlertVO> map = new LinkedHashMap<Long, AlertVO>(0);
      		try{
      			if(tempList != null && tempList.size() > 0){
    				for (Object[] objects : tempList) {
    					AlertVO vo = new AlertVO();
    						vo.setId(commonMethodsUtilService.getLongValueForObject(objects[2]));
    						vo.setName(commonMethodsUtilService.getStringValueForObject(objects[3]));
    						vo.setColor(commonMethodsUtilService.getStringValueForObject(objects[4]));
    						map.put(vo.getId(), vo);
    				}
    			}
      			if(map != null && map.size() > 0){
      				finalList.addAll(map.values());
      			}
      		}catch(Exception e){
    			e.printStackTrace();
      			LOG.error("Exception Occured in  getTemplate() of AlertManagementSystemService class ", e);
      		}
			return finalList;
      	}
      	/*
         * Swadhin K Lenka
         * overview and Status new
         * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getStateThenGovtDeptScopeWiseAlertCountStatusWise(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
         */
       /* public List<AlertCoreDashBoardVO> getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverview(String fromDateStr, String toDateStr, Long stateId, 
        									List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
        									Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
        									Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId, String group,List<Long> calCntrIdList,List<Long> sublevels){
    		try{
    			
    			Date fromDate = null;
    			Date toDate = null;
    			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
    				fromDate = sdf.parse(fromDateStr);
    				toDate = sdf.parse(toDateStr);
    			}
    			if(printIdList != null && printIdList.size() > 0){  
    				if(electronicIdList != null && electronicIdList.size() == 0){
    					electronicIdList.add(0L);
    				}
    			}else if(electronicIdList != null && electronicIdList.size() > 0){
    				if(printIdList != null && printIdList.size() == 0){
    					printIdList.add(0L);
    				}
    			}else{
                    if(electronicIdList != null && electronicIdList.size() == 0){
                      electronicIdList.add(0L);
                      if(calCntrIdList != null && calCntrIdList.size() == 0){
                        calCntrIdList.add(0L);
                        }
                    }
                  }
    			if(electronicIdList != null && electronicIdList.size() > 0){
                    if(printIdList != null && printIdList.size() == 0){
                      printIdList.add(0L);
                      if(calCntrIdList != null && calCntrIdList.size() == 0){
                        calCntrIdList.add(0L);
                        }
                    }
                  }
    			if(calCntrIdList != null && calCntrIdList.size() > 0){
                    if(printIdList != null && printIdList.size() == 0){
                      printIdList.add(0L);
                      if(electronicIdList != null && electronicIdList.size() == 0){
                        electronicIdList.add(0L);
                        }
                    }
                  }
    			
    			/*if(printIdList == null || printIdList.size() == 0)
    				printIdList.add(0L);
    			if(electronicIdList == null || electronicIdList.size() == 0)
    				electronicIdList.add(0L);
    				printIdList.add(0L);
    			}
    			if(calCntrIdList == null || calCntrIdList.size() == 0)
    				calCntrIdList.add(0L);
    			
    			List<Long> levelValues = new ArrayList<Long>();    
    			Long levelId = 0L;
    			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
    			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
    				for(Object[] param : lvlValueAndLvlIdList){
    					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
    					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
    				}
    			}
    			
    			List<KeyValueVO> subLevels = new ArrayList<KeyValueVO>();
    			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
    			List<Long> deptScopeIdList = new ArrayList<Long>();
    			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
    				for(Object [] param : childDeptScopeIdList){
    					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
    					KeyValueVO sublevel = new KeyValueVO();
    					sublevel.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
    					sublevel.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
    					subLevels.add(sublevel);
    				}
    			}
    			
    			if(commonMethodsUtilService.isListOrSetValid(sublevels)){
    				deptScopeIdList.clear();
					deptScopeIdList.addAll(sublevels);
				}
    			List<AlertCoreDashBoardVO> returnList = new ArrayList<AlertCoreDashBoardVO>();
    			List<Object[]> alertList = null; 
    			if(deptScopeIdList != null && deptScopeIdList.size() > 0){
    				if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status") ){
        				if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
                			alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,null,calCntrIdList);
                			//prepareResultForState(alertList,returnList,sortingType,order);
                			if(alertType != null && alertType.equalsIgnoreCase("alert")){
                				prepareResultForStateNew(alertList,returnList,sortingType,order,group,alertType);
        					}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
        						prepareResultForStateNew(alertList,returnList,sortingType,order,group,alertType);
        					}
            				return returnList;
        				}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 5L){
        					if(alertType != null && alertType.equalsIgnoreCase("alert")){
        						alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,"statusWise",calCntrIdList);
        					}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
        						alertList = govtAlertSubTaskDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,"statusWise",calCntrIdList);
        					}
            			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 6L){
            				
            				if(alertType != null && alertType.equalsIgnoreCase("alert")){
            					alertList = alertAssignedOfficerNewDAO.getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,"statusWise",calCntrIdList);
            				}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
            					alertList = govtAlertSubTaskDAO.getDivisionWorkLocationThenGovtDeptScopeWiseSubTaskForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,"statusWise",calCntrIdList);
            				}
            			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 7L){
            				if(alertType != null && alertType.equalsIgnoreCase("alert")){
            					alertList = alertAssignedOfficerNewDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,"statusWise",calCntrIdList);
            				}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
            					alertList = govtAlertSubTaskDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,"statusWise",calCntrIdList);
            				}
            			}
        			}else{
        				if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L || parentGovtDepartmentScopeId.longValue() == 5L){
                			alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,null,calCntrIdList);
            			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 6L){
                			alertList = alertAssignedOfficerNewDAO.getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,null,calCntrIdList);
            			}else if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 7L){
                			alertList = alertAssignedOfficerNewDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,null,calCntrIdList);
            			}
        			}
        			
    			}
    			
    			Map<Long,String> locIdAndLocNameMap = new LinkedHashMap<Long,String>();
    			Map<Long,String> lvlIdAndLvlName = new LinkedHashMap<Long,String>();
    			Map<Long,String> lvlIdAndColor = new LinkedHashMap<Long,String>();
    			Map<Long,LinkedHashMap<Long,Long>> locIdThenLvlIdThenAlertCount = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
    			LinkedHashMap<Long,Long> levelIdAndAlertCountMap = null;
    			
    			Set<Long> deptScopeIds = new HashSet<Long>();
    			if(alertList != null && alertList.size() > 0){
    				for(Object[] param : alertList){
    					deptScopeIds.add(commonMethodsUtilService.getLongValueForObject(param[3]));
    				}
    			}
    			List<Object[]> deptScopeIdDtlsList = null;
    			if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status") && deptScopeIds != null && deptScopeIds.size() >0){
    				if(alertType !=null && !alertType.trim().isEmpty() && alertType.equalsIgnoreCase("subTask")){    					
    					deptScopeIdDtlsList = alertSubTaskStatusDAO.getAlertStatusDtlsBasidOnAlertIds(new ArrayList<Long>(deptScopeIds));    					
    				}else{
    					deptScopeIdDtlsList = alertStatusDAO.getAlertStatusDtlsBasidOnAlertIds(new ArrayList<Long>(deptScopeIds));
    				}
    				
    			}else if(deptScopeIds != null && deptScopeIds.size() >0){ 
    				deptScopeIdDtlsList = govtDepartmentScopeDAO.getGovtDepartmenttScopeDetailsBasedOnScopeIds(new ArrayList<Long>(deptScopeIds));
    			}
    			
    			
    			if(deptScopeIdDtlsList != null && deptScopeIdDtlsList.size() > 0){
    				for(Object[] param : deptScopeIdDtlsList){
    					lvlIdAndLvlName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
    					lvlIdAndColor.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[2]));

    				}  
    			}
    			
    			if(alertList != null && alertList.size() > 0){   
    				for(Object[] param : alertList){
    					locIdAndLocNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]),commonMethodsUtilService.getStringValueForObject(param[2]));
    					
    					levelIdAndAlertCountMap = locIdThenLvlIdThenAlertCount.get(commonMethodsUtilService.getLongValueForObject(param[1]));
    					if(levelIdAndAlertCountMap == null){
    						levelIdAndAlertCountMap = new LinkedHashMap<Long,Long>();
    						locIdThenLvlIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[1]), levelIdAndAlertCountMap);
    					}
    					levelIdAndAlertCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[4]));
    				}
    			}
    			
    			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
    			if(locIdThenLvlIdThenAlertCount != null && locIdThenLvlIdThenAlertCount.size() > 0){
    				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : locIdThenLvlIdThenAlertCount.entrySet()){
    					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
    					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
    					alertCoreDashBoardVO.setName(locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
    					buildStatusWiseTemplate(alertCoreDashBoardVO,lvlIdAndLvlName,lvlIdAndColor);
    					Long total = new Long(0L);
    					for(AlertCoreDashBoardVO boardVO : alertCoreDashBoardVO.getSubList()){
    						if(outerEntry.getValue() != null && outerEntry.getValue().get(boardVO.getId()) != null){
    							boardVO.setCount(outerEntry.getValue().get(boardVO.getId()));
    							total = total + outerEntry.getValue().get(boardVO.getId());
    						}
    					}
    					alertCoreDashBoardVO.setTotalCount(total);
    					returnList.add(alertCoreDashBoardVO);
    				}
    			}
    			
    			if(returnList != null && returnList.size() > 0){
    				returnList.get(0).getSubLevels().addAll(subLevels);
    				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("count")){
    					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
    						Collections.sort(returnList, alertAscendingCountWiseSortingLvlWise);
    					}else{
    						Collections.sort(returnList, alertDescCountWiseSortingLvlWise);
    					}
    				}
    				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("name")){
    					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
    						Collections.sort(returnList, alphabeticalAscSortLvlWise);
    					}else{
    						Collections.sort(returnList, alphabeticalDescendingSortLvlWise);
    					}
    				}
    			}   
    			return returnList;
    			
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    		return null;
    	}*/
      //Regarding filter. district
        public List<IdNameVO> getDistIdListForDistFilter(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String group,String alertType,String searchType,List<Long> calCntrIdList){
      		try{
      			
      			Date fromDate = null;
      			Date toDate = null;
      			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
      				fromDate = sdf.parse(fromDateStr);
      				toDate = sdf.parse(toDateStr);
      			}
      			if(printIdList != null && printIdList.size() > 0){  
                    if(electronicIdList != null && electronicIdList.size() == 0){
                      electronicIdList.add(0L);
                      if(calCntrIdList != null && calCntrIdList.size() == 0){
                        calCntrIdList.add(0L);
                        }
                    }
                  }
      			if(electronicIdList != null && electronicIdList.size() > 0){
                    if(printIdList != null && printIdList.size() == 0){
                      printIdList.add(0L);
                      if(calCntrIdList != null && calCntrIdList.size() == 0){
                        calCntrIdList.add(0L);
                        }
                    }
                  }
      			if(calCntrIdList != null && calCntrIdList.size() > 0){
                    if(printIdList != null && printIdList.size() == 0){
                      printIdList.add(0L);
                      if(electronicIdList != null && electronicIdList.size() == 0){
                        electronicIdList.add(0L);
                        }
                    }
                  }
      			
      			List<Long> levelValues = new ArrayList<Long>();    
      			Long levelId = 0L;
      			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
      			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
      				for(Object[] param : lvlValueAndLvlIdList){
      					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
      					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
      				}
      			}
      			
      			
      			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
      			List<Long> deptScopeIdList = new ArrayList<Long>();
      			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
      				for(Object [] param : childDeptScopeIdList){
      					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
      				}
      			}
      			
      			List<Object[]> alertList = null;  
      			if(alertType != null && alertType.equalsIgnoreCase("alert"))
    			 alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,null,group,searchType,calCntrIdList);
      			else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
      				alertList = govtAlertSubTaskDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,null,group,searchType,calCntrIdList);	
      			Map<Long,String> idAndNameMap = new HashMap<Long,String>(); 
    			if(alertList != null && alertList.size() > 0){
      				for(Object[] param : alertList){
      					idAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getStringValueForObject(param[2]));
      				}
      			}
    			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();  
	          		  IdNameVO idNameVO = null;
	          		  if(idAndNameMap != null && idAndNameMap.size() > 0){
	          			  for(Entry<Long,String> param : idAndNameMap.entrySet()){
	          				  idNameVO = new IdNameVO();
	          				  idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param.getKey()));
	          				  idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param.getValue()));
	          				  idNameVOs.add(idNameVO);
	          			  }
	          		  }
	          		  return idNameVOs;
      			
      		}catch(Exception e){
      			e.printStackTrace();
      		}
      		return null;
        }
        //Regarding filter. division->district
        public List<IdNameVO> getDistIdListForDivisionFilter(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String group,String alertType,String searchType,List<Long> calCntrIdList){
    		try{
    			
    			Date fromDate = null;
    			Date toDate = null;
    			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
    				fromDate = sdf.parse(fromDateStr);
    				toDate = sdf.parse(toDateStr);
    			}
    			if(printIdList != null && printIdList.size() > 0){  
    				if(electronicIdList != null && electronicIdList.size() == 0){
    					electronicIdList.add(0L);
    					if(calCntrIdList != null && calCntrIdList.size() == 0){
    						calCntrIdList.add(0L);
        				}
    				}
    			}
    			if(electronicIdList != null && electronicIdList.size() > 0){
    				if(printIdList != null && printIdList.size() == 0){
    					printIdList.add(0L);
    					if(calCntrIdList != null && calCntrIdList.size() == 0){
    						calCntrIdList.add(0L);
        				}
    				}
    			}
    			if(calCntrIdList != null && calCntrIdList.size() > 0){
    				if(printIdList != null && printIdList.size() == 0){
    					printIdList.add(0L);
    					if(electronicIdList != null && electronicIdList.size() == 0){
    						electronicIdList.add(0L);
        				}
    				}
    			}
    			
    			List<Long> levelValues = new ArrayList<Long>();    
    			Long levelId = 0L;
    			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
    			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
    				for(Object[] param : lvlValueAndLvlIdList){
    					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
    					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
    				}
    			}
    			
    			//start
    			List<Object[]> deptAndLvlList = govtDepartmentScopeLevelDAO.getAllScopesInAscOrder(govtDepartmentId);
    			//for level position create a map for scope and its position
    			Map<Long,Long> scopeIdAndLevelMap = new HashMap<Long,Long>();
    			Map<Long,Long> levelAndScopeIdMap = new HashMap<Long,Long>();
    			long position = 0l;
    			if(deptAndLvlList != null && deptAndLvlList.size() > 0){
    				for(Object[] param : deptAndLvlList){
    					position+=1;
    					scopeIdAndLevelMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), position);
    					levelAndScopeIdMap.put(position, commonMethodsUtilService.getLongValueForObject(param[1]));
    				}
    			}
    			//based on the dept scope get its position
    			position = scopeIdAndLevelMap.get(parentGovtDepartmentScopeId);
    			//end
    			
    			
    			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
    			List<Long> deptScopeIdList = new ArrayList<Long>();
    			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
    				for(Object [] param : childDeptScopeIdList){
    					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
    				}
    			}
    			List<Object[]> alertList = null;
    			Long justUpperLvl = levelAndScopeIdMap.get(position-1);
    			if(alertType != null && alertType.equalsIgnoreCase("alert")){
    				
    				alertList = alertAssignedOfficerNewDAO.getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,null,null,"true",group,searchType,calCntrIdList,justUpperLvl);
    			}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
    				alertList = govtAlertSubTaskDAO.getDivisionWorkLocationThenGovtDeptScopeWiseSubTaskForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,null,null,"true",group,searchType,calCntrIdList,justUpperLvl);
    			}
    				Map<Long,String> idAndNameMap = new HashMap<Long,String>(); 
    			if(alertList != null && alertList.size() > 0){
      				for(Object[] param : alertList){
      					idAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getStringValueForObject(param[2]));
      				}
      			}
    			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();  
	          		  IdNameVO idNameVO = null;
	          		  if(idAndNameMap != null && idAndNameMap.size() > 0){
	          			  for(Entry<Long,String> param : idAndNameMap.entrySet()){
	          				  idNameVO = new IdNameVO();
	          				  idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param.getKey()));
	          				  idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param.getValue()));
	          				  idNameVOs.add(idNameVO);
	          			  }
	          		  }
	          		  return idNameVOs;
    			
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    		return null;
    	}
        //Regarding filter. division->division
        public List<IdNameVO> getDivisionIdListForDivisionFilter(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long districtWorkLocationId,String group,String alertType,String searchType,List<Long> calCntrIdList){
    		try{
    			Date fromDate = null;
    			Date toDate = null;
    			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
    				fromDate = sdf.parse(fromDateStr);
    				toDate = sdf.parse(toDateStr);
    			}
    			if(printIdList != null && printIdList.size() > 0){  
                    if(electronicIdList != null && electronicIdList.size() == 0){
                      electronicIdList.add(0L);
                      if(calCntrIdList != null && calCntrIdList.size() == 0){
                        calCntrIdList.add(0L);
                        }
                    }
                  }
       			if(electronicIdList != null && electronicIdList.size() > 0){
                    if(printIdList != null && printIdList.size() == 0){
                      printIdList.add(0L);
                      if(calCntrIdList != null && calCntrIdList.size() == 0){
                        calCntrIdList.add(0L);
                        }
                    }
                  }
       			if(calCntrIdList != null && calCntrIdList.size() > 0){
                    if(printIdList != null && printIdList.size() == 0){
                      printIdList.add(0L);
                      if(electronicIdList != null && electronicIdList.size() == 0){
                        electronicIdList.add(0L);
                        }
                    }
                  }
    			
    			List<Long> levelValues = new ArrayList<Long>();    
    			Long levelId = 0L;
    			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
    			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
    				for(Object[] param : lvlValueAndLvlIdList){
    					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
    					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
    				}
    			}
    			
    			
    			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
    			List<Long> deptScopeIdList = new ArrayList<Long>();
    			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
    				for(Object [] param : childDeptScopeIdList){
    					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
    				}
    			}
    			
    			//start
    			List<Object[]> deptAndLvlList = govtDepartmentScopeLevelDAO.getAllScopesInAscOrder(govtDepartmentId);
    			//for level position create a map for scope and its position
    			Map<Long,Long> scopeIdAndLevelMap = new HashMap<Long,Long>();
    			Map<Long,Long> levelAndScopeIdMap = new HashMap<Long,Long>();
    			long position = 0l;
    			if(deptAndLvlList != null && deptAndLvlList.size() > 0){
    				for(Object[] param : deptAndLvlList){
    					position+=1;
    					scopeIdAndLevelMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), position);
    					levelAndScopeIdMap.put(position, commonMethodsUtilService.getLongValueForObject(param[1]));
    				}
    			}
    			//based on the dept scope get its position
    			position = scopeIdAndLevelMap.get(parentGovtDepartmentScopeId);
    			//end
    			
    			
    			List<Object[]> alertList = null;
    			Long justUpperLvl = levelAndScopeIdMap.get(position-1);
    			if(alertType != null && alertType.equalsIgnoreCase("alert")){
    				
    			   alertList = alertAssignedOfficerNewDAO.getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,null,"true",group,searchType,calCntrIdList,justUpperLvl);
    			}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
    			   alertList = govtAlertSubTaskDAO.getDivisionWorkLocationThenGovtDeptScopeWiseSubTaskForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,null,"true",group,searchType,calCntrIdList,justUpperLvl);
    			}
    			   Map<Long,String> idAndNameMap = new HashMap<Long,String>(); 
    			if(alertList != null && alertList.size() > 0){
      				for(Object[] param : alertList){
      					idAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getStringValueForObject(param[4]));
      				}
      			}
    			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();  
	          		  IdNameVO idNameVO = null;
	          		  if(idAndNameMap != null && idAndNameMap.size() > 0){
	          			  for(Entry<Long,String> param : idAndNameMap.entrySet()){
	          				  idNameVO = new IdNameVO();
	          				  idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param.getKey()));
	          				  idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param.getValue()));
	          				  idNameVOs.add(idNameVO);
	          			  }
	          		  }
	          		  return idNameVOs;
    			
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    		return null;
      }
      //Regarding filter. division->sub division
        //1
      //Regarding filter. district->sub division
        public List<IdNameVO> getDistrictIdListForSubDivisionFilter(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String group,String alertType,String searchType,List<Long> calCntrIdList){
       	 try{
       		
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			if(printIdList != null && printIdList.size() > 0){  
                if(electronicIdList != null && electronicIdList.size() == 0){
                  electronicIdList.add(0L);
                  if(calCntrIdList != null && calCntrIdList.size() == 0){
                    calCntrIdList.add(0L);
                    }
                }
              }
			if(electronicIdList != null && electronicIdList.size() > 0){
                if(printIdList != null && printIdList.size() == 0){
                  printIdList.add(0L);
                  if(calCntrIdList != null && calCntrIdList.size() == 0){
                    calCntrIdList.add(0L);
                    }
                }
              }
			if(calCntrIdList != null && calCntrIdList.size() > 0){
                if(printIdList != null && printIdList.size() == 0){
                  printIdList.add(0L);
                  if(electronicIdList != null && electronicIdList.size() == 0){
                    electronicIdList.add(0L);
                    }
                }
              }
			
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			
			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
			List<Long> deptScopeIdList = new ArrayList<Long>();
			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
				for(Object [] param : childDeptScopeIdList){
					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			
			//start
			List<Object[]> deptAndLvlList = govtDepartmentScopeLevelDAO.getAllScopesInAscOrder(govtDepartmentId);
			//for level position create a map for scope and its position
			Map<Long,Long> scopeIdAndLevelMap = new HashMap<Long,Long>();
			Map<Long,Long> levelAndScopeIdMap = new HashMap<Long,Long>();
			long position = 0l;
			if(deptAndLvlList != null && deptAndLvlList.size() > 0){
				for(Object[] param : deptAndLvlList){
					position+=1;
					scopeIdAndLevelMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), position);
					levelAndScopeIdMap.put(position, commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			//based on the dept scope get its position
			position = scopeIdAndLevelMap.get(parentGovtDepartmentScopeId);
			//end
			
			
			List<Object[]> alertList = null;
			Long levelTwo = levelAndScopeIdMap.get(position-2);
			Long levelThree = levelAndScopeIdMap.get(position-1);
			if(alertType != null && alertType.equalsIgnoreCase("alert")){
			    alertList = alertAssignedOfficerNewDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,null,null,null,"true",group,searchType,calCntrIdList,levelTwo,levelThree);
			}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
				alertList = govtAlertSubTaskDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,null,null,null,"true",group,searchType,calCntrIdList,levelTwo,levelThree);
       	 	}
			Map<Long,String> idAndNameMap = new HashMap<Long,String>(); 
			if(alertList != null && alertList.size() > 0){
  				for(Object[] param : alertList){
  					idAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getStringValueForObject(param[2]));
  				}
  			}
			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();  
          		  IdNameVO idNameVO = null;
          		  if(idAndNameMap != null && idAndNameMap.size() > 0){
          			  for(Entry<Long,String> param : idAndNameMap.entrySet()){
          				  idNameVO = new IdNameVO();
          				  idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param.getKey()));
          				  idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param.getValue()));
          				  idNameVOs.add(idNameVO);
          			  }
          		  }
          		  return idNameVOs;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
       	
        }
        //2
      //Regarding filter. division->sub division
       public List<IdNameVO> getDivisionIdListForSubDivisionFilter(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long districtWorkLocationId,String group,String alertType,String searchType,List<Long> calCntrIdList){
    	   try{
   
   			Date fromDate = null;
   			Date toDate = null;
   			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
   			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
   				fromDate = sdf.parse(fromDateStr);
   				toDate = sdf.parse(toDateStr);
   			}
   			if(printIdList != null && printIdList.size() > 0){  
                if(electronicIdList != null && electronicIdList.size() == 0){
                  electronicIdList.add(0L);
                  if(calCntrIdList != null && calCntrIdList.size() == 0){
                    calCntrIdList.add(0L);
                    }
                }
              }
   			if(electronicIdList != null && electronicIdList.size() > 0){
                if(printIdList != null && printIdList.size() == 0){
                  printIdList.add(0L);
                  if(calCntrIdList != null && calCntrIdList.size() == 0){
                    calCntrIdList.add(0L);
                    }
                }
              }
   			if(calCntrIdList != null && calCntrIdList.size() > 0){
                if(printIdList != null && printIdList.size() == 0){
                  printIdList.add(0L);
                  if(electronicIdList != null && electronicIdList.size() == 0){
                    electronicIdList.add(0L);
                    }
                }
              }
   			
   			List<Long> levelValues = new ArrayList<Long>();    
   			Long levelId = 0L;
   			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
   			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
   				for(Object[] param : lvlValueAndLvlIdList){
   					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
   					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
   				}
   			}
   			
   			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
   			List<Long> deptScopeIdList = new ArrayList<Long>();
   			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
   				for(Object [] param : childDeptScopeIdList){
   					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
   				}
   			}
   			
   			
   			//start
			List<Object[]> deptAndLvlList = govtDepartmentScopeLevelDAO.getAllScopesInAscOrder(govtDepartmentId);
			//for level position create a map for scope and its position
			Map<Long,Long> scopeIdAndLevelMap = new HashMap<Long,Long>();
			Map<Long,Long> levelAndScopeIdMap = new HashMap<Long,Long>();
			long position = 0l;
			if(deptAndLvlList != null && deptAndLvlList.size() > 0){
				for(Object[] param : deptAndLvlList){
					position+=1;
					scopeIdAndLevelMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), position);
					levelAndScopeIdMap.put(position, commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			//based on the dept scope get its position
			position = scopeIdAndLevelMap.get(parentGovtDepartmentScopeId);
			//end
   			
   			
   			List<Object[]> alertList = null;
   			Long levelTwo = levelAndScopeIdMap.get(position-2);
			Long levelThree = levelAndScopeIdMap.get(position-1);
			if(alertType != null && alertType.equalsIgnoreCase("alert"))
			   alertList = alertAssignedOfficerNewDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,null,null,"true",group,searchType,calCntrIdList,levelTwo,levelThree);
			else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
			 alertList = govtAlertSubTaskDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,null,null,null,"true",group,searchType,calCntrIdList,levelTwo,levelThree);
   			Map<Long,String> idAndNameMap = new HashMap<Long,String>(); 
   			if(alertList != null && alertList.size() > 0){
     				for(Object[] param : alertList){
     					idAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getStringValueForObject(param[4]));
     				}
     			}
   			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();  
             		  IdNameVO idNameVO = null;
             		  if(idAndNameMap != null && idAndNameMap.size() > 0){
             			  for(Entry<Long,String> param : idAndNameMap.entrySet()){
             				  idNameVO = new IdNameVO();
             				  idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param.getKey()));
             				  idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param.getValue()));
             				  idNameVOs.add(idNameVO);
             			  }
             		  }
             		  return idNameVOs;
   			
   		}catch(Exception e){
   			e.printStackTrace();
   		}
   		return null;
       }

        //3
      //Regarding filter. sub division->sub division
        public List<IdNameVO> getSubDivisionIdListForSubDivisionFilter(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long districtWorkLocationId,Long divisionWorkLocationId,String group,String alertType,String searchType,List<Long> calCntrIdList){
        	try{
        	
   			Date fromDate = null;
   			Date toDate = null;
   			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
   			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
   				fromDate = sdf.parse(fromDateStr);
   				toDate = sdf.parse(toDateStr);
   			}
   			if(printIdList != null && printIdList.size() > 0){  
                if(electronicIdList != null && electronicIdList.size() == 0){
                  electronicIdList.add(0L);
                  if(calCntrIdList != null && calCntrIdList.size() == 0){
                    calCntrIdList.add(0L);
                    }
                }
              }
   			if(electronicIdList != null && electronicIdList.size() > 0){
                if(printIdList != null && printIdList.size() == 0){
                  printIdList.add(0L);
                  if(calCntrIdList != null && calCntrIdList.size() == 0){
                    calCntrIdList.add(0L);
                    }
                }
              }
   			if(calCntrIdList != null && calCntrIdList.size() > 0){
                if(printIdList != null && printIdList.size() == 0){
                  printIdList.add(0L);
                  if(electronicIdList != null && electronicIdList.size() == 0){
                    electronicIdList.add(0L);
                    }
                }
              }
   			
   			List<Long> levelValues = new ArrayList<Long>();    
   			Long levelId = 0L;
   			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
   			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
   				for(Object[] param : lvlValueAndLvlIdList){
   					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
   					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
   				}
   			}
   			
   			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
   			List<Long> deptScopeIdList = new ArrayList<Long>();
   			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
   				for(Object [] param : childDeptScopeIdList){
   					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
   				}
   			}
   			
   			//start
			List<Object[]> deptAndLvlList = govtDepartmentScopeLevelDAO.getAllScopesInAscOrder(govtDepartmentId);
			//for level position create a map for scope and its position
			Map<Long,Long> scopeIdAndLevelMap = new HashMap<Long,Long>();
			Map<Long,Long> levelAndScopeIdMap = new HashMap<Long,Long>();
			long position = 0l;
			if(deptAndLvlList != null && deptAndLvlList.size() > 0){
				for(Object[] param : deptAndLvlList){
					position+=1;
					scopeIdAndLevelMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), position);
					levelAndScopeIdMap.put(position, commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			//based on the dept scope get its position
			position = scopeIdAndLevelMap.get(parentGovtDepartmentScopeId);
			//end
   			
   			
   			List<Object[]> alertList = null;
   			Long levelTwo = levelAndScopeIdMap.get(position-2);
			Long levelThree = levelAndScopeIdMap.get(position-1);
			if(alertType != null && alertType.equalsIgnoreCase("alert")) //s3
				alertList = alertAssignedOfficerNewDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,"true",group,searchType,calCntrIdList,levelTwo,levelThree);
			else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
				alertList = govtAlertSubTaskDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,null,null,null,"true",group,searchType,calCntrIdList,levelTwo,levelThree);
   			Map<Long,String> idAndNameMap = new HashMap<Long,String>(); 
   			if(alertList != null && alertList.size() > 0){
     				for(Object[] param : alertList){
     					idAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[5]), commonMethodsUtilService.getStringValueForObject(param[6]));
     				}
     			}
   			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();  
             		  IdNameVO idNameVO = null;
             		  if(idAndNameMap != null && idAndNameMap.size() > 0){
             			  for(Entry<Long,String> param : idAndNameMap.entrySet()){
             				  idNameVO = new IdNameVO();
             				  idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param.getKey()));
             				  idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param.getValue()));
             				  idNameVOs.add(idNameVO);
             			  }
             		  }
             		  return idNameVOs;
   			
   		}catch(Exception e){
   			e.printStackTrace();
   		}
   		return null;
    }
    	public List<AlertVO> getAllDistrictDetails(Long departmentId){
            List<AlertVO> finalVoList = new ArrayList<AlertVO>(0);
        		try {
        		List<Object[]> districtList = govtDepartmentWorkLocationDAO.getAllDistrictDetails(departmentId);
        		if (districtList != null && districtList.size() > 0) {
              			for
              			(Object[] objects : districtList) {  
              				 AlertVO alertVO = new AlertVO();
              				 alertVO.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
              				 alertVO.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
              				 finalVoList.add(alertVO);
              				}					
      				}				
      			} catch (Exception e) {
      				LOG.error(" Exception Occured in getAllDistrictDetails() method, Exception - ",e);
      			}    	
              	return finalVoList;
          } 
    	//optional
    	 public List<AlertCoreDashBoardVO> getWorkLocationWiseThenGovtDeptScopeWiseAlertCount(String fromDateStr, String toDateStr, Long stateId, 
    				List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
    				Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
    				Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId, String group,String searchType,List<Long> sublevels,List<Long> calCntrIds){
                   try{
                	   Date fromDate = null;
           			   Date toDate = null;
           			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
           			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
           				fromDate = sdf.parse(fromDateStr);
           				toDate = sdf.parse(toDateStr);
           			}
           			if(printIdList != null && printIdList.size() > 0){  
                        if(electronicIdList != null && electronicIdList.size() == 0){
                          electronicIdList.add(0L);
                          if(calCntrIds != null && calCntrIds.size() == 0){
                        	  calCntrIds.add(0L);
                            }
                        }
                      }
           			if(electronicIdList != null && electronicIdList.size() > 0){
                        if(printIdList != null && printIdList.size() == 0){
                          printIdList.add(0L);
                          if(calCntrIds != null && calCntrIds.size() == 0){
                        	  calCntrIds.add(0L);
                            }
                        }
                      }
           			if(calCntrIds != null && calCntrIds.size() > 0){
                        if(printIdList != null && printIdList.size() == 0){
                          printIdList.add(0L);
                          if(electronicIdList != null && electronicIdList.size() == 0){
                            electronicIdList.add(0L);
                            }
                        }
                      }
           			
           			List<Long> levelValues = new ArrayList<Long>();    
           			Long levelId = 0L;
           			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
           			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
           				for(Object[] param : lvlValueAndLvlIdList){
           					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
           					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
           				}
           			}
           			
           			List<KeyValueVO> subLevels = new ArrayList<KeyValueVO>();
           			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
           			List<Long> deptScopeIdList = new ArrayList<Long>();
           			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
           				for(Object [] param : childDeptScopeIdList){
           					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
           					KeyValueVO sublevel = new KeyValueVO();
        					sublevel.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
        					sublevel.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
        					subLevels.add(sublevel);
           				}
           			}
           			
           			if(commonMethodsUtilService.isListOrSetValid(sublevels)){
        				deptScopeIdList.clear();
    					deptScopeIdList.addAll(sublevels);
    				}
           			
           			//start
	    			List<Object[]> deptAndLvlList = govtDepartmentScopeLevelDAO.getAllScopesInAscOrder(govtDepartmentId);
	    			//for level position create a map for scope and its position
	    			Map<Long,Long> scopeIdAndLevelMap = new HashMap<Long,Long>();
	    			Map<Long,Long> levelAndScopeIdMap = new HashMap<Long,Long>();
	    			long position = 0l;
	    			if(deptAndLvlList != null && deptAndLvlList.size() > 0){
	    				for(Object[] param : deptAndLvlList){
	    					position+=1;
	    					scopeIdAndLevelMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), position);
	    					levelAndScopeIdMap.put(position, commonMethodsUtilService.getLongValueForObject(param[1]));
	    				}
	    			}
	    			//based on the dept scope get its position
	    			position = scopeIdAndLevelMap.get(parentGovtDepartmentScopeId);
	    			//end
           			
           			
           			
           			List<AlertCoreDashBoardVO> returnList = new ArrayList<AlertCoreDashBoardVO>();
           			List<Object[]> alertList = null;  
           			if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status") ){
           				if(position == 1l && parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
                   			alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,null,calCntrIds);
                   			prepareResultForState(alertList,returnList,sortingType,order,null,null);
               				return returnList;
           				}else if(position == 2l){
           					if(alertType != null && alertType.equalsIgnoreCase("alert"))
           						alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,searchType,calCntrIds);
           					else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
           						alertList = govtAlertSubTaskDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,searchType,calCntrIds);
               			}else if(position == 3L){
               				Long justUpperLvl = levelAndScopeIdMap.get(position-1);
               				if(alertType != null && alertType.equalsIgnoreCase("alert"))
               					alertList = alertAssignedOfficerNewDAO.getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,searchType,calCntrIds,justUpperLvl);
               				else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
               					alertList = govtAlertSubTaskDAO.getDivisionWorkLocationThenGovtDeptScopeWiseSubTaskForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,searchType,calCntrIds,justUpperLvl);
               			}else if(position == 4l){
               				Long levelTwo = levelAndScopeIdMap.get(position-2);
            				Long levelThree = levelAndScopeIdMap.get(position-1);
               				if(alertType != null && alertType.equalsIgnoreCase("alert"))//s4
               					alertList = alertAssignedOfficerNewDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,searchType,calCntrIds,levelTwo,levelThree);
               				else if(alertType != null && alertType.equalsIgnoreCase("subTask"))
               					alertList = govtAlertSubTaskDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,searchType,calCntrIds,levelTwo,levelThree);
               			}
           			}else{  
           				if(position == 1l || position == 2l){
                   			alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,null,calCntrIds);
               			}else if(position == 3l){
               				Long justUpperLvl = levelAndScopeIdMap.get(position-1);
                   			alertList = alertAssignedOfficerNewDAO.getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,null,calCntrIds,justUpperLvl);
               			}else if(position == 4l){
               				Long levelTwo = levelAndScopeIdMap.get(position-2);
            				Long levelThree = levelAndScopeIdMap.get(position-1);
                   			alertList = alertAssignedOfficerNewDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,null,calCntrIds,levelTwo,levelThree);
               			}
           			}
           			

           			
           			
           			Map<Long,String> locIdAndLocNameMap = new LinkedHashMap<Long,String>();
           			Map<Long,String> lvlIdAndLvlName = new LinkedHashMap<Long,String>();
           			Map<Long,String> lvlIdAndColor = new LinkedHashMap<Long,String>();
           			Map<Long,LinkedHashMap<Long,Long>> locIdThenLvlIdThenAlertCount = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
           			LinkedHashMap<Long,Long> levelIdAndAlertCountMap = null;
           			
           			Set<Long> deptScopeIds = new HashSet<Long>();
           			if(alertList != null && alertList.size() > 0){
           				for(Object[] param : alertList){
           					deptScopeIds.add(commonMethodsUtilService.getLongValueForObject(param[3]));
           				}
           			}
           			List<Object[]> deptScopeIdDtlsList = null;
           			if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status") && deptScopeIds != null && deptScopeIds.size()>0){
           				deptScopeIdDtlsList = govtDepartmentScopeDAO.getGovtDepartmenttScopeDetailsBasedOnScopeIds(new ArrayList<Long>(deptScopeIds));
           				//deptScopeIdDtlsList = alertStatusDAO.getAlertStatusDtlsBasidOnAlertIds(new ArrayList<Long>(deptScopeIds));
           			}/*else if(deptScopeIds != null && deptScopeIds.size()>0){ 
           				deptScopeIdDtlsList = govtDepartmentScopeDAO.getGovtDepartmenttScopeDetailsBasedOnScopeIds(new ArrayList<Long>(deptScopeIds));
           			}*/
           			
           			
           			if(deptScopeIdDtlsList != null && deptScopeIdDtlsList.size() > 0){
           				for(Object[] param : deptScopeIdDtlsList){
           					lvlIdAndLvlName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
           					lvlIdAndColor.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[2]));

           				}  
           			}
           			
           			if(alertList != null && alertList.size() > 0){   
           				for(Object[] param : alertList){
           					locIdAndLocNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]),commonMethodsUtilService.getStringValueForObject(param[2]));
           					
           					levelIdAndAlertCountMap = locIdThenLvlIdThenAlertCount.get(commonMethodsUtilService.getLongValueForObject(param[1]));
           					if(levelIdAndAlertCountMap == null){
           						levelIdAndAlertCountMap = new LinkedHashMap<Long,Long>();
           						locIdThenLvlIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[1]), levelIdAndAlertCountMap);
           					}
           					levelIdAndAlertCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[4]));
           				}
           			}
           			
           			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
           			if(locIdThenLvlIdThenAlertCount != null && locIdThenLvlIdThenAlertCount.size() > 0){
           				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : locIdThenLvlIdThenAlertCount.entrySet()){
           					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
           					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
           					alertCoreDashBoardVO.setName(locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
           					buildStatusWiseTemplate(alertCoreDashBoardVO,lvlIdAndLvlName,lvlIdAndColor);
           					Long total = new Long(0L);
           					for(AlertCoreDashBoardVO boardVO : alertCoreDashBoardVO.getSubList()){
           						if(outerEntry.getValue() != null && outerEntry.getValue().get(boardVO.getId()) != null){
           							boardVO.setCount(outerEntry.getValue().get(boardVO.getId()));
           							total = total + outerEntry.getValue().get(boardVO.getId());
           						}
           					}
           					alertCoreDashBoardVO.setTotalCount(total);
           					returnList.add(alertCoreDashBoardVO);
           				}
           			}
           			
           			if(returnList != null && returnList.size() > 0){
           				returnList.get(0).getSubLevels().addAll(subLevels);
           				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("count")){
           					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
           						Collections.sort(returnList, alertAscendingCountWiseSortingLvlWise);
           					}else{
           						Collections.sort(returnList, alertDescCountWiseSortingLvlWise);
           					}
           				}
           				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("name")){
           					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
           						Collections.sort(returnList, alphabeticalAscSortLvlWise);
           					}else{
           						Collections.sort(returnList, alphabeticalDescendingSortLvlWise);
           					}
           				}
           			}
           			return returnList;
           			
           		}catch(Exception e){
           			e.printStackTrace();
           		}
           		return null;
           	}

        /*
		 * Teja(non-Javadoc)
		 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#stateLevelDeptOfficerDepartmentWiseAlertsView()
		 */
        public List<AlertVO> stateLevelDeptOfficerDepartmentWiseAlertsView(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> callCenterIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
        		LOG.info("Entered in stateLevelDeptOfficerStatusOverview() method of AlertManagementSystemService{}");
        		try{
        			
        			Date fromDate = null;
        			Date toDate = null;
        			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
        				fromDate = sdf.parse(fromDateStr);
        				toDate = sdf.parse(toDateStr);
        			}
        			
        			prepareRequiredParameter(printIdList,electronicIdList,callCenterIds,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
        			
        			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
        			//get alert status count and and create a map of alertStatusId and its count
        			List<Object[]> totalList = new ArrayList<Object[]>();
        			/*List<Object[]> alertCountList = alertDAO.stateLevelDeptOfficerDepartmentWiseAlertsViewForAlertCnt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,"Status");//for pending status
        			if(alertCountList != null && alertCountList.size() > 0){
        				totalList.addAll(alertCountList);
        			}*/
        			List<Long> levelValues = new ArrayList<Long>();    
        			Long levelId = 0L;
        			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
        			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
        				for(Object[] param : lvlValueAndLvlIdList){
        					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
        					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
        				}
        			}
        			List<Object[]> alertCountList2 = alertAssignedOfficerNewDAO.stateLevelDeptOfficerDepartmentWiseAlertsView(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"status",alertStatusIds,null,callCenterIds,socialMediaTypeIds,alertSeverityIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
        			if(alertCountList2 != null && alertCountList2.size() > 0){
        				totalList.addAll(alertCountList2);
        			}
        			setAlertCountDetailsforDepartmentWise(totalList,finalAlertVOs); 
        			return finalAlertVOs; 
        		}catch(Exception e){
        			e.printStackTrace();
        			LOG.error("Error occured stateLevelDeptOfficerDepartmentWiseAlertsView() method of AlertManagementSystemService{}");
        		}
        		return null;
        	}
        public void setAlertCountDetails(List<Object[]> objList,List<AlertVO> finalAlertVOs){
    	    try{
    	    	if(objList != null && objList.size() > 0){         
    				Long totalAlertCnt = 0l;
    				for(Object[] param : objList){
    						 totalAlertCnt = totalAlertCnt+commonMethodsUtilService.getLongValueForObject(param[5]);	 
    				 }
    				 for(Object[] param : objList){
    					Long id = commonMethodsUtilService.getLongValueForObject(param[0]);
    					 AlertVO VO = getAlertStatusMatchVO2(finalAlertVOs,id);
    					 if(VO == null){
    						 VO = new AlertVO();
    						 VO.setStatusId(id);
    						 VO.setStatus(commonMethodsUtilService.getStringValueForObject(param[1]));
    						 VO.setColor(commonMethodsUtilService.getStringValueForObject(param[2]));
    						 VO.setId(commonMethodsUtilService.getLongValueForObject(param[3]));//deptId
    						 VO.setName(commonMethodsUtilService.getStringValueForObject(param[4]));//dept name 
    						 VO.setAlertCnt(commonMethodsUtilService.getLongValueForObject(param[5]));
    					  finalAlertVOs.add(VO); 
    					 }else{
    					 	  VO.setAlertCnt(VO.getAlertCnt()+commonMethodsUtilService.getLongValueForObject(param[5]));
    					 }
    				}
    				//Calculating Percentage
    				calculatePercentage(finalAlertVOs,totalAlertCnt);
    			}
    	    }catch(Exception e){
    			e.printStackTrace();
    			LOG.error("Error occured setStatusWiseAlertCnt() method of CccDashboardService{}");
    	    }
    	}
        
    	public AlertVO getAlertStatusMatchVO2(List<AlertVO> finalAlertVOs,Long id){
    		try{
    			if(finalAlertVOs == null || finalAlertVOs.size() ==0)
    				return null;
    			for(AlertVO VO:finalAlertVOs){
    				if(VO.getStatusId().equals(id))
    				{
    					return VO;
    				}
    			}
    		}catch(Exception e){
    			e.printStackTrace();
    			LOG.error("Error occured getAlertStatusMatchVO2() method of CccDashboardService{}");
    		}
    		return null;
    	}
        //For status over view sub task for state level page
        public List<AlertVO> stateLevelDeptOfficerLocationLevelOverviewBySubTasks(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> callCenterIdslIst,List<Long> socialMediaTypeIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
      		LOG.info("Entered in stateLevelDeptOfficerLocationLevelOverviewBySubTasks() method of AlertManagementSystemService{}");
      		try{
      			Date fromDate = null;
      			Date toDate = null;
      			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
      				fromDate = sdf.parse(fromDateStr);
      				toDate = sdf.parse(toDateStr);
      			}
      			
      			prepareRequiredParameter(printIdList,electronicIdList,callCenterIdslIst,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
      			
      			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
      			//get all the alert status and build the template
      			List<Long> levelValues = new ArrayList<Long>();    
      			Long levelId = 0L;
      			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
      			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
      				for(Object[] param : lvlValueAndLvlIdList){
      					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
      					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
      				}
      			}
      			List<Object[]> rtrnObjLst = govtAlertSubTaskDAO.stateLevelDeptOfficerLocationLevelOverviewBySubTasks(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"Level",subTaskAlertStatusIds,null,callCenterIdslIst,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
      			setAlertCount(rtrnObjLst,finalAlertVOs);
      			return finalAlertVOs; 
      		}catch(Exception e){
      			e.printStackTrace();
      			LOG.error("Error occured stateLevelDeptOfficerLocationLevelOverviewBySubTasks() method of AlertManagementSystemService{}");
      		}
      		return null;
      	}
	 public List<AlertCoreDashBoardVO> getDistrictLevelDeptWiseAlertClick(Long userId,List<Long> govtOffceIds,List<Long> statusIds,String formDateStr,String toDateStr,String clickType,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds, List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
			List<AlertCoreDashBoardVO> finalVoList = new ArrayList<AlertCoreDashBoardVO>(0);
			try {
			
				Date fromDate = null; 
	   			Date toDate = null;
	   			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	   			if(formDateStr != null && formDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
	   				fromDate = sdf.parse(formDateStr);
	   				toDate = sdf.parse(toDateStr);
	   			}
	   			
	   			List<Long> govtDeptDesigOffceIds= govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOfficerIdListByUserId(userId);
	   			
	   			prepareRequiredParameter(printIdsList,electronicIdsList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
		   		
				List<Long> alertIds = null;
				if(govtDeptDesigOffceIds != null && govtDeptDesigOffceIds.size() > 0l && govtOffceIds != null && govtOffceIds.size() > 0l){
				 if(clickType != null && clickType.equalsIgnoreCase("alert"))
				  alertIds = alertAssignedOfficerNewDAO.getDistrictOffrAlertsIds(govtDeptDesigOffceIds,govtOffceIds,fromDate,toDate,statusIds,printIdsList,electronicIdsList,calCntrIdList,socialMediaTypeIds,alertSeverityIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				 else if(clickType != null && clickType.equalsIgnoreCase("mySubTasks"))
				  alertIds = govtAlertSubTaskDAO.getDistrictOffcrSubTasksAlertIds(govtDeptDesigOffceIds,govtOffceIds,"mySubTasks",fromDate,toDate,statusIds,printIdsList,electronicIdsList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				 else if(clickType != null && clickType.equalsIgnoreCase("myAssignedSubTasks"))
				  alertIds = govtAlertSubTaskDAO.getDistrictOffcerSubTsksAlertIds(govtDeptDesigOffceIds,govtOffceIds,"myAssignedSubTasks",fromDate,toDate,statusIds,printIdsList,electronicIdsList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				}
				if(alertIds != null && alertIds.size() > 0){
					List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIds));
					
					setAlertDtls(finalVoList, list); 
				}
				setSubListCount(finalVoList, alertIds);
				
			} catch (Exception e) {
				LOG.error(" Exception Occured in getDistrictLevelDeptWiseAlertClick() method, Exception - ",e);
			}		
			return finalVoList;
		}
	 public void setSubListCount(List<AlertCoreDashBoardVO> finalVoList,List<Long> alertIds){
		 try{
			 List<Object[]> subtaskCountList = null;
				if(alertIds != null && alertIds.size() > 0){
					subtaskCountList = govtAlertSubTaskDAO.getSubTaskCount(alertIds);
				}
				//create a map from alertId and subtask count.
				Map<Long,Long> alertIdAndSubTaskCountMap = new HashMap<Long,Long>();
				if(subtaskCountList != null && subtaskCountList.size() > 0){
					for(Object[] param : subtaskCountList){
						alertIdAndSubTaskCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
					}
				}
				if(finalVoList != null && finalVoList.size() > 0){
					for(AlertCoreDashBoardVO alertCoreDashBoardVO : finalVoList){
						if(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()) != null){
							alertCoreDashBoardVO.setSubTaskCount(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()));
						}
					}
				}
		 }catch (Exception e) {
				LOG.error(" Exception Occured in setSubListCount() method, Exception - ",e);
			}
		
	 }
	//For state level page
     public List<AlertVO> stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> callCenterIdsList,List<Long> socialMediaTypeIds,List<Long> subTaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
 		LOG.info("Entered in stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick() method of AlertManagementSystemService{}");
 		try{
 			
 			Date fromDate = null;
 			Date toDate = null;
 			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
 				fromDate = sdf.parse(fromDateStr);
 				toDate = sdf.parse(toDateStr);
 			}
 			
 			prepareRequiredParameter(printIdList,electronicIdList,callCenterIdsList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
 			
 			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
 			//get alert status count and and create a map of alertStatusId and its count
 			List<Object[]> totalList = new ArrayList<Object[]>();
 			/*List<Object[]> alertCountList = alertDAO.stateLevelDeptOfficerDepartmentWiseAlertsViewForAlertCnt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,"Status");//for pending status
 			if(alertCountList != null && alertCountList.size() > 0){
 				totalList.addAll(alertCountList);
 			}*/
 			List<Long> levelValues = new ArrayList<Long>();    
 			Long levelId = 0L;
 			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
 			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
 				for(Object[] param : lvlValueAndLvlIdList){
 					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
 					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
 				}
 			}
 			
 			List<Object[]> alertCountList2 = govtAlertSubTaskDAO.stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"status",subTaskStatusIds,null,callCenterIdsList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
 			if(alertCountList2 != null && alertCountList2.size() > 0){
 				totalList.addAll(alertCountList2);
 			}
 			setAlertCountDetails(totalList,finalAlertVOs); 
 			return finalAlertVOs; 
 		}catch(Exception e){
 			e.printStackTrace();
 			LOG.error("Error occured stateLevelDeptOfficerStatusOverview() method of AlertManagementSystemService{}");
 		}
 		return null;
 	}

	 public List<GrievanceAlertVO> getGovtGrievanceAlertDetails(String mobileNo,String locatoinType,Long locationId,String fromDateStr,String toDateStr,Long statusId,Long deptId){
			List<GrievanceAlertVO> finalVoList = new ArrayList<GrievanceAlertVO>(0);
		try {
			Date fromDate = null;
 			Date toDate = null;
 			List<Long> alertIds = new ArrayList<Long>();
 			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
 				fromDate = sdf.parse(fromDateStr);
 				toDate = sdf.parse(toDateStr);
 			}
 			
			List<Object[]> GovtGrivenceDetails = alertDAO.getGovtGrievanceAlertDetails(mobileNo,locatoinType,locationId,fromDate,toDate,statusId,deptId);
			 if(GovtGrivenceDetails != null &&  GovtGrivenceDetails.size()>0){
				 for(Object[] param : GovtGrivenceDetails){
					 GrievanceAlertVO Vo = new  GrievanceAlertVO();
					 Vo.setDate(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10));
					 Vo.setTime(commonMethodsUtilService.getStringValueForObject(param[1]).substring(11, 16));
					 Vo.setTitle(commonMethodsUtilService.getStringValueForObject(param[2]));
					 Vo.setDescription(commonMethodsUtilService.getStringValueForObject(param[3]));
					 Vo.setRelatedTo(commonMethodsUtilService.getStringValueForObject(param[4]));
					 Vo.setProblem(commonMethodsUtilService.getStringValueForObject(param[5]));
					 Vo.setStatus(commonMethodsUtilService.getStringValueForObject(param[6]));
					 //Vo.setCreatedBy(commonMethodsUtilService.getStringValueForObject(param[7]));
					 
					 Vo.setDistrict(commonMethodsUtilService.getStringValueForObject(param[8]));
					 Vo.setAssembly(commonMethodsUtilService.getStringValueForObject(param[9]));
					 Vo.setTehsil(commonMethodsUtilService.getStringValueForObject(param[10]));
					 Vo.setPanchayat(commonMethodsUtilService.getStringValueForObject(param[11]));
					 Vo.setHamlet(commonMethodsUtilService.getStringValueForObject(param[12]));
					 Vo.setLeb(commonMethodsUtilService.getStringValueForObject(param[13]));
					 Vo.setWard(commonMethodsUtilService.getStringValueForObject(param[14]));
					 //Vo.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[15]));
					 Vo.setAlertId(commonMethodsUtilService.getLongValueForObject(param[16]));
					 
					 alertIds.add(Vo.getAlertId());
					 finalVoList.add(Vo);
				 }
			 }
			 
			 if(alertIds != null && !alertIds.isEmpty()){
				 List<Object[]> list = alertCallerRelationDAO.getAlertCallerDetailsForAlerts(alertIds);
				 if(list != null && !list.isEmpty()){
					 for (Object[] obj : list) {
						Long alertId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						GrievanceAlertVO vo = getMatchedGrieAlertVO(finalVoList, alertId);
						if(vo != null){
							GrievanceAlertVO callervo = new GrievanceAlertVO();
							callervo.setName(obj[2] != null ? obj[2].toString():"");
							callervo.setMobileNo(obj[3] != null ? obj[3].toString():"");
							callervo.setStatus(obj[4] != null ? obj[4].toString():"");
							vo.getSubList().add(callervo);
						}
					}
				 }
			 }
			 
			 if(finalVoList != null && !finalVoList.isEmpty()){
					for (GrievanceAlertVO alertVO : finalVoList) {
						if(alertVO.getSubList().size() > 1)
							alertVO.setCallerDuplicate("YES");
					}
				}
							
			} catch (Exception e) {
				LOG.error(" Exception Occured in getGovtGrievanceAlertDetails() method, Exception - ",e);
			}		
			return finalVoList;
		}
	 
	 public GrievanceAlertVO getMatchedGrieAlertVO(List<GrievanceAlertVO> list, Long alertId)
		{
			try {
				if(list != null && list.size()>0)
				{
					for (GrievanceAlertVO alertvo : list) {
						if(alertvo.getAlertId().longValue() == alertId.longValue())
							return alertvo;
					}
				}
			} catch (Exception e) {
				 LOG.error("Exception Occured in getMatchedGrieAlertVO() method, Exception - ",e);
			}
			return null;
		}
	 
	 public void setAlertCountDetailsforDepartmentWise(List<Object[]> objList,List<AlertVO> finalAlertVOs){
 	    try{
 	    	if(objList != null && objList.size() > 0){         
 				Long totalAlertCnt = 0l;
 				for(Object[] param : objList){
 						 totalAlertCnt = totalAlertCnt+commonMethodsUtilService.getLongValueForObject(param[5]);	 
 				 }
 				 for(Object[] param : objList){
 					Long id = commonMethodsUtilService.getLongValueForObject(param[0]);
 					AlertVO matchedDeptVo = getmatchedVo(finalAlertVOs,(Long)param[3]);
 					if(matchedDeptVo == null){
 						matchedDeptVo = new AlertVO();
 						matchedDeptVo.setId(commonMethodsUtilService.getLongValueForObject(param[3]));//deptId
 						matchedDeptVo.setName(commonMethodsUtilService.getStringValueForObject(param[4]));//dept name 
 						
 						AlertVO statusVo = new AlertVO();
 						statusVo.setId(id);//statusId
 						statusVo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));//statusNAme
 						statusVo.setColor(commonMethodsUtilService.getStringValueForObject(param[2]));
 						statusVo.setAlertCnt(commonMethodsUtilService.getLongValueForObject(param[5]));
 						
 						matchedDeptVo.getSubList2().add(statusVo);
 						finalAlertVOs.add(matchedDeptVo);
 					}else{
 						AlertVO matchedStatusVo = getmatchedVo(finalAlertVOs,(Long)param[0]);
 						if( matchedStatusVo == null){
 							matchedStatusVo = new AlertVO();
 							matchedStatusVo.setId(id);//statusId
 	 						matchedStatusVo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));//statusName
 	 						matchedStatusVo.setColor(commonMethodsUtilService.getStringValueForObject(param[2]));
 	 						matchedStatusVo.setAlertCnt(matchedStatusVo.getAlertCnt()+commonMethodsUtilService.getLongValueForObject(param[5]));
 	 						
 	 						matchedDeptVo.getSubList2().add(matchedStatusVo);
 						}else{
 							matchedStatusVo.setAlertCnt(matchedStatusVo.getAlertCnt()+commonMethodsUtilService.getLongValueForObject(param[5]));
 						}
 					}
 				}
 				//Calculating Percentage
 				calculatePerc(finalAlertVOs,totalAlertCnt);
 			}
 	    }catch(Exception e){
 			e.printStackTrace();
 			LOG.error("Error occured setStatusWiseAlertCnt() method of AlertManagementSystemService{}");
 	    }
 	}
	 public FilterSectionVO getFilterSectionAlertDetails(Long userId,List<Long> deptIdList){
		 FilterSectionVO filterVo =new FilterSectionVO();
		try {
			Long levelId = 0L;
            List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
            if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
              for(Object[] param : lvlValueAndLvlIdList){
                levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
              }
            }
            List<Object[]> scopeIds = govtDepartmentScopeLevelDAO.getGovtScopesLevelByParentScopeLevel(levelId, deptIdList);//levelId means Access Level 
			//List<Object[]> scopeIds = govtDepartmentScopeDAO.getFilterSectionDetailsOnScopeIds();
			 setFilterSectionAlertDetails(scopeIds,filterVo,"scopes");
            List<Object[]> departIds = govtAlertDepartmentLocationNewDAO.getDeptIdAndNameForUserAccessLevel(userId);
			 setFilterSectionAlertDetails(departIds,filterVo,"departments");
			//List<Long> departIdList =govtAlertDepartmentLocationNewDAO.getDeptNameForUserAccessLevel(userId);
			List<Object[]> severityIds = alertSeverityDAO.getFilterSectionDetailsOnSeverity();
			 setFilterSectionAlertDetails(severityIds,filterVo,"severity");
			List<Object[]> categoryIds = alertCategoryDAO.getAllCategory1();
			 setFilterSectionAlertDetails(categoryIds,filterVo,"category");
			List<Object[]> editionsIds = newsPaperDAO.getNewPaperList();
			 setFilterSectionAlertDetails(editionsIds,filterVo,"editions");
			List<Object[]> tvNewsChannelIds = tvNewsChannelDAO.getAllElectrinicMedia();
			 setFilterSectionAlertDetails(tvNewsChannelIds,filterVo,"tvNewsChannel");
			List<Object[]> locationLevelIds =alertImpactScopeDAO.getAlertImpactScope();
			setFilterSectionAlertDetails(locationLevelIds,filterVo,"locationLevel");
			List<Object[]> alertStatusIds =alertStatusDAO.getAllStatus();
			setFilterSectionAlertDetails(alertStatusIds,filterVo,"alertStatus");
			List<Object[]> subTaskStatusIds =alertSubTaskStatusDAO.getAlertSubStatusDtls();
			setFilterSectionAlertDetails(subTaskStatusIds,filterVo,"alertSubTaskStatus");
			} catch (Exception e) {
				LOG.error(" Exception Occured in getFilterSectionAlertDetails() method, Exception - ",e);
			}		
			return filterVo;
		}
	 public void setFilterSectionAlertDetails(List<Object[]> scopeIds,FilterSectionVO filterVo,String names)
	 { 
		 List<FilterSectionVO> list =new ArrayList<FilterSectionVO>();
		 if(scopeIds!= null && scopeIds.size()>0){
			 for(Object[] param : scopeIds){
				 FilterSectionVO Vo = new  FilterSectionVO();
				 Vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				 Vo.setName(commonMethodsUtilService.getStringValueForObject(param[1])); 
				 list.add(Vo);
				
			 }
		 }
		 if(names.equalsIgnoreCase("scopes")){
			 filterVo.getScopesList().addAll(list); 
		 }
		 if(names.equalsIgnoreCase("severity")){
			 filterVo.getSeverityList().addAll(list);
		 }
		 if(names.equalsIgnoreCase("category")){
			 filterVo.getCategoryList().addAll(list);
		 }
		 if(names.equalsIgnoreCase("editions")){
			 filterVo.getEditionsList().addAll(list);
		 }
		 if(names.equalsIgnoreCase("tvNewsChannel")){
			 filterVo.getTvNewsChannelList().addAll(list);
		 } 
		 if(names.equalsIgnoreCase("locationLevel")){
			 filterVo.getLocationLevelList().addAll(list);
		 }
		 if(names.equalsIgnoreCase("alertStatus")){
			 filterVo.getAlertStatusList().addAll(list);
		 }
		 if(names.equalsIgnoreCase("alertSubTaskStatus")){
			 filterVo.getAlertSubTaskStatusList().addAll(list);
		 }
		 if(names.equalsIgnoreCase("departments")){
			 filterVo.getAlertDepartMentList().addAll(list);
		 } 
		 
	 }
	 public String getDesignationForUser(Long userId){
			String officerName = null;
			String desgnationName = null;
			String officerNameAnddesgnationName = null;
			try {
				List<Object[]> officerList = govtDepartmentDesignationOfficerDetailsNewDAO.getDesignationsNameForUser(userId);
				if(commonMethodsUtilService.isListOrSetValid(officerList)){
					officerName = (String) officerList.get(0)[0];
					desgnationName = (String) officerList.get(0)[1];
				}
					officerNameAnddesgnationName = officerName+"-"+desgnationName;
			} catch (Exception e) {
				LOG.error("Error occured getDesignationForUser() method of AlertManagementSystemService",e);
			}
			return officerNameAnddesgnationName;
		}
	 //New Dept Teja
	 public DistrictOfficeViewAlertVO getDeptDetails(Long userId){
			DistrictOfficeViewAlertVO returnVO = new DistrictOfficeViewAlertVO();
			 try {
				 List<Long> levelValues = new ArrayList<Long>();    
	   			Long levelId = 0L;
	   			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
	   			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
	   				for(Object[] param : lvlValueAndLvlIdList){
	   					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
	   					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
	   				}
	   			}
	   			returnVO.setLevelValues(levelValues);
				returnVO.setLevelId(levelId);
	   			List<Object[]> list1 = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOffrDetlsIdAndGovtOfcrId(userId,levelValues,levelId);
	   			
	   			List<Long> govtDepDesigOffcrIds = new ArrayList<Long>(0);
	   			List<Long> govtOffcrIds =  new ArrayList<Long>(0);
	   			List<Long> departmentIds = new ArrayList<Long>(0);
	   			List<String> departmentNames = new ArrayList<String>(0);
	   			List<Long> designationIds = new ArrayList<Long>(0);
	   			List<String> designationNames = new ArrayList<String>(0);
	   			if(commonMethodsUtilService.isListOrSetValid(list1)){
	   				for( Object[]  obj :list1){
	   					govtDepDesigOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[0]));
	   					govtOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[1]));
	   					departmentIds.add(commonMethodsUtilService.getLongValueForObject(obj[3]));
	   					departmentNames.add(commonMethodsUtilService.getStringValueForObject(obj[4]));
	   					designationIds.add(commonMethodsUtilService.getLongValueForObject(obj[5]));
	   					designationNames.add(commonMethodsUtilService.getStringValueForObject(obj[6]));
	   					
	   					DistrictOfficeViewAlertVO deptVO = new DistrictOfficeViewAlertVO();
	   					deptVO.setId(commonMethodsUtilService.getLongValueForObject(obj[3]));
	   					deptVO.setName(commonMethodsUtilService.getStringValueForObject(obj[4]));
	   					returnVO.getSubList1().add(deptVO);
	   					
	   					
	   					returnVO.setDeptIds(departmentIds);
	   					returnVO.setDepartmentNames(departmentNames);
	   					returnVO.setGovtDeptDesigOffcrIds(govtDepDesigOffcrIds);
	   					returnVO.setGovtOfficerIds(govtOffcrIds);
	   					returnVO.setTodayAlertIds(designationIds);//designationIds
	   					returnVO.setDesignationNames(designationNames);
	   					
	   				}
	   			}
			} catch (Exception e) {
				LOG.error("Error occured getDeptDetails() method of AlertManagementSystemService",e);
			}
			 return returnVO;
		 }
		 public List<AlertCoreDashBoardVO> getStateLevelAlertclickView(List<Long> deptIds,List<Long> statusIds,String type,
				 List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String serachType,String fromDateStr,
				 String toDateStr,List<Long> printIdList, List<Long> electronicIdList,List<Long> callCenterIds,
				 List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
	 		List<AlertCoreDashBoardVO> finalVoList = new ArrayList<AlertCoreDashBoardVO>(0);
	 		try {
	 			Date fromDate = null;
	 			Date toDate = null;
	 			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	 			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
	 				fromDate = sdf.parse(fromDateStr);
	 				toDate = sdf.parse(toDateStr);
	 			}
	 			List<Long> alertIdList = null;
	 			
	 			prepareRequiredParameter(printIdList,electronicIdList,callCenterIds,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Setting Parameter
	 			
	 			if(type != null && type.equalsIgnoreCase("alert")){
	 				if(serachType != null && serachType.equalsIgnoreCase("today")){
	 					alertIdList = alertAssignedOfficerNewDAO.getStateLevelAlertclickViewAlertsIds(govtDepDesigOffcrIds,govtOffcrIds,"today",deptIds,statusIds,new DateUtilService().getCurrentDateAndTime(),new DateUtilService().getCurrentDateAndTime(),printIdList,electronicIdList,callCenterIds,socialMediaTypeIds,alertSeverityIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
	 				}else if(serachType != null && serachType.equalsIgnoreCase("completed")){
	 					alertIdList = alertAssignedOfficerNewDAO.getStateLevelAlertclickViewAlertsIds(govtDepDesigOffcrIds,govtOffcrIds,"",deptIds,statusIds,fromDate,toDate,printIdList,electronicIdList,callCenterIds,socialMediaTypeIds,alertSeverityIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
	 				}
	 			}else if(type.equalsIgnoreCase("subtask")){
	 				if(serachType != null && serachType.equalsIgnoreCase("today")){
	 					alertIdList = govtAlertSubTaskDAO.getStateLevelAlertclickViewAlertIds(govtDepDesigOffcrIds,govtOffcrIds,"today",deptIds,statusIds,new DateUtilService().getCurrentDateAndTime(),new DateUtilService().getCurrentDateAndTime(),printIdList,electronicIdList,callCenterIds,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
	 				}else if(serachType != null && serachType.equalsIgnoreCase("completed")){
	 					alertIdList = govtAlertSubTaskDAO.getStateLevelAlertclickViewAlertIds(govtDepDesigOffcrIds,govtOffcrIds,"",deptIds,statusIds,fromDate,toDate,printIdList,electronicIdList,callCenterIds,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
	 				}
	 			}else if(type.equalsIgnoreCase("assignSubTask")){
	 				if(serachType != null && serachType.equalsIgnoreCase("today")){
	 					alertIdList = govtAlertSubTaskDAO.getStateLevelAssignedAlertClickViewAlertIds(govtDepDesigOffcrIds,govtOffcrIds,"today",deptIds,statusIds,new DateUtilService().getCurrentDateAndTime(),new DateUtilService().getCurrentDateAndTime(),printIdList,electronicIdList,callCenterIds,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
	 				}else if(serachType != null && serachType.equalsIgnoreCase("completed")){
	 					alertIdList = govtAlertSubTaskDAO.getStateLevelAssignedAlertClickViewAlertIds(govtDepDesigOffcrIds,govtOffcrIds,"",deptIds,statusIds,fromDate,toDate,printIdList,electronicIdList,callCenterIds,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
	 				}
	 			}
	 			if(alertIdList != null && alertIdList.size() > 0){
	 				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIdList));
	 				setAlertDtls(finalVoList, list); 
	 			}
	 			setSubListCount(finalVoList, alertIdList);
	 		} catch (Exception e) {
	 			LOG.error(" Exception Occured in getStateLevelAlertclickView() method, Exception - ",e);
	 		}		
	 		return finalVoList;
	 	}
		 /*
	      	 * Swadhin K Lenka
	      	 * state overview  and status click
	      	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getStateThenGovtDeptScopeWiseAlertCount(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long) 
	      	 */
	      	public List<AlertCoreDashBoardVO> getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverviewForClick(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long deptScopeId, Long statusId,List<Long> calCntrIds){
	      		try{
	      			
	      			Date fromDate = null;  
	      			Date toDate = null;
	      			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	      			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
	      				fromDate = sdf.parse(fromDateStr);
	      				toDate = sdf.parse(toDateStr);
	      			}
	      			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
	      			if(printIdList != null && printIdList.size() > 0){  
	      				if(electronicIdList != null && electronicIdList.size() == 0){
	      					electronicIdList.add(0L);
	      					if(calCntrIds != null && calCntrIds.size() == 0){
	      						calCntrIds.add(0L);
		      				}
	      				}
	      			}
	      			if(electronicIdList != null && electronicIdList.size() > 0){
	      				if(printIdList != null && printIdList.size() == 0){
	      					printIdList.add(0L);
	      					if(calCntrIds != null && calCntrIds.size() == 0){
	      						calCntrIds.add(0L);
		      				}
	      				}
	      			}
	      			if(calCntrIds != null && calCntrIds.size() > 0){
	      				if(printIdList != null && printIdList.size() == 0){
	      					printIdList.add(0L);
	      					if(electronicIdList != null && electronicIdList.size() == 0){
	      						electronicIdList.add(0L);
		      				}
	      				}
	      			}
	      			
	      			List<Long> levelValues = new ArrayList<Long>();    
	      			Long levelId = 0L;
	      			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
	      			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
	      				for(Object[] param : lvlValueAndLvlIdList){
	      					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
	      					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
	      				}
	      			}
	      			
	    			List<Long> alertList = null;
	    			
	    			alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverviewForClick(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,deptScopeId,parentGovtDepartmentScopeId, statusId, calCntrIds);

	      			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
	      			if(alertList != null && alertList.size() > 0){
	    				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertList));
	    				setAlertDtls(alertCoreDashBoardVOs, list); 
	    			}
	      			//set Subtask into alert logic 
	    			
	    			//get subtask count.
	    			List<Object[]> subtaskCountList = null;
	    			if(alertList != null && alertList.size() > 0){
	    				subtaskCountList = govtAlertSubTaskDAO.getSubTaskCount(alertList);
	    			}
	    			//create a map from alertId and subtask count.
	    			Map<Long,Long> alertIdAndSubTaskCountMap = new HashMap<Long,Long>();
	    			if(subtaskCountList != null && subtaskCountList.size() > 0){
	    				for(Object[] param : subtaskCountList){
	    					alertIdAndSubTaskCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
	    				}
	    			}
	    			if(alertCoreDashBoardVOs != null && alertCoreDashBoardVOs.size() > 0){
	    				for(AlertCoreDashBoardVO alertCoreDashBoardVO : alertCoreDashBoardVOs){
	    					if(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()) != null){
	    						alertCoreDashBoardVO.setSubTaskCount(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()));
	    					}
	    				}
	    			}
	      			
	      			return alertCoreDashBoardVOs;        
	      			
	      		}catch(Exception e){
	      			e.printStackTrace();    
	      		}
	      		return null;
	      	}
	      	public List<AlertCoreDashBoardVO> getStateLevelDeptWiseFlterClick(Long userId,List<Long> deptIds,Long locatonLevelId,
	    			List<Long> statusIds,String type,String fromDateStr,String toDateStr,
	    			Long desigDeptOfficerId,Long officerId, List<Long> printIdList, 
	    			List<Long> electronicIdList,List<Long> calCntrIdList,Long stateId,
	    			String levelType,String assignType,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
	      		
	    		   List<AlertCoreDashBoardVO> finalVoList = new ArrayList<AlertCoreDashBoardVO>(0);
	    		   List<Long> alertIds = null;
	    		try {
	    			Date fromDate = null;
	    			Date toDate = null;
	    			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
	    				fromDate = sdf.parse(fromDateStr);
	    				toDate = sdf.parse(toDateStr);
	    			}
	    			
	    			prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Setting Parameter
	    			
	    			List<Long> levelValues = new ArrayList<Long>();    
	    			Long levelId = 0L;
	    			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
	    			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
	    				for(Object[] param : lvlValueAndLvlIdList){
	    					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
	    					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
	    				}
	    			}
	    			if(type.equalsIgnoreCase("alert")){
	    			   alertIds = alertAssignedOfficerNewDAO.getStateLevelDeptWiseFlterClick(deptIds,locatonLevelId,statusIds,fromDate,toDate,levelId,levelValues,printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,alertSeverityIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);	
	    			}else if(type.equalsIgnoreCase("subTask")){
	    				alertIds = govtAlertSubTaskDAO.getStateLevelDeptWiseFlterClick(deptIds,locatonLevelId,statusIds,fromDate,toDate,levelId,levelValues,printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
	    			}
	    			
	    			if(alertIds != null && alertIds.size() > 0){
	    				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIds));
	    				setAlertDtls(finalVoList, list); 
	    			}
	    			setSubListCount(finalVoList, alertIds);
	    		} catch (Exception e) {
	    			LOG.error(" Exception Occured in getStateLevelDeptWiseFlterClick() method, Exception - ",e);
	    		}		
	    		return finalVoList;
	    	}
	      	
	      	public List<IdNameVO> getGovtAllDepartmentDetails(){
	      		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
	      		try {
	      			
	      			List<Object[]> list = govtDepartmentRelationDAO.getAllMainDepartments();
	      			if(list !=null && list.size()>0){
	      				for (Object[] objects : list) {							
	      					IdNameVO vo = new IdNameVO();
	      					vo.setId((Long)objects[0]);
	      					vo.setName(objects[1] !=null ? objects[1].toString():"");
	      					
	      					finalList.add(vo);
						}
	      			}
					
				} catch (Exception e) {
					LOG.error(" Exception Occured in getGovtDepartmentDetails() method, Exception - ",e);
				}
	      		return finalList;
	      	}
	      	

  			public List<IdNameVO>  getStatusCompletionInfoNew(Long alertId,Long levelValue,Long designationId,Long levelId,Long userId,List<String> entitlements){
  			            List<IdNameVO> finalList = new ArrayList<IdNameVO>();
  			            try {
  			              
  			              Alert alert  = alertDAO.get(alertId);
  			              
  			              	Long alertPresentStatusId = alertDAO.getPresentStatusOfAlert(alertId);
  			              
  			              String userType = null;
  			              //whether this alert is belongs to same logedin user or not.
  			              //get all govt dept desig off ids
  			              List<Long> govtDeptDesigOfficerIdList = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOfficerIdListByUserId(userId);
  			              
  			              //get govt dept desig off id by alertId
  			              Long govtDeptDesigOfficerId = alertAssignedOfficerNewDAO.getGovtDeptDesigOfficerIdListByUserId(alertId);
  			              
  			              //whether this alert is belongs to just subordinate or not.
  			              
  			              //get all govt dept desig ids
  			              List<Long> govtDeptDesigIdList = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigIdListByUserId(userId);
  			              //get govt dept desig id by alertId
  			              Long govtDeptDesigId2 = alertAssignedOfficerNewDAO.getGovtDeptDesigIdListByUserId(alertId); 
  			              
  			              //now check
  			              List<Object[]> list2 = null;
  			              if(govtDeptDesigIdList != null && govtDeptDesigIdList.size() > 0 && govtDeptDesigId2 != null && govtDeptDesigId2.longValue() > 0L){
  			                list2 = govtDepartmentDesignationHierarchyDAO.getChildDesigDataNew(govtDeptDesigIdList,govtDeptDesigId2);
  			              }
  			              
  			              //to check same level designation.
  			              //by alert id take scope.
  			              Long govtDeptScopeIdForAlert = alertAssignedOfficerNewDAO.getGovtDeptScopeIdForAlert(alertId);
  			              //by user Id take scope.
  			              List<Long> govtDeptScopeIdsForUserId = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptScopeIdsForUserId(userId);
  			              
  			              //to wheck whether he/she is an admin or not.
  			              //Long userCount = govtDepartmentDesignationOfficerNewDAO.getUserIdCount(userId);
  			              List<String> entitlement = userGroupRelationDAO.getUserIdCount(userId);
  			              String entlmnt = "";
  			              if(entitlement != null && entitlement.size() > 0){
  			            	 entlmnt = entitlement.get(0);
  			              }
  			              String userStatus = "";
  			              if(entlmnt != null && !entlmnt.isEmpty() && entlmnt.equalsIgnoreCase("GOVT_DEPARTMENT_ADMIN_USER_GROUP_ENTITLEMENT_NEW")){
  			                userStatus = "admin";
  			              }else{
  			                userStatus = "officer";    
  			              }
  			              if(govtDeptDesigOfficerIdList != null && govtDeptDesigOfficerId != null && govtDeptDesigOfficerIdList.size() > 0 && govtDeptDesigOfficerIdList.contains(govtDeptDesigOfficerId)){
  			                userType ="own";
  			                
  			                List<Object[]> objList = alertDepartmentStatusDAO.getAlertGovtDepartmentStatus(alert.getGovtDepartmentId());
  			                if(objList != null && objList.size() > 0){
  			                  for (Object[] objects : objList) {
  			                    IdNameVO VO = new IdNameVO();
  			                    	VO.setId((Long)objects[0]);
  			                        VO.setName(objects[1].toString());
  			                        VO.setDateStr(objects[2] != null ? objects[2].toString():"");
  			                        if(alert.getAlertStatusId().longValue() != VO.getId().longValue())
  			                        finalList.add(VO);
  			                  }
  			                }
  			                
  			              }else if(list2 != null && list2.size() > 0){ 
  			                
  			                userType = "subUser";
  			                
  			                if(alert.getAlertStatusId().longValue() == 4l || alert.getAlertStatusId().longValue() == 11l || alert.getAlertStatusId().longValue() == 12l){//Completed Status  
  			                  //userType = "subUserStatus";
  			                  List<Object[]> listObj = alertStatusDAO.getAlertStatusInfoForReOpen();
  			                    if(listObj !=null && listObj.size()>0){
  			                      for (Object[] objects : listObj) {
		  			                    IdNameVO vo = new IdNameVO();
		  			                    vo.setId((Long)objects[0]);
		  			                    vo.setName(objects[1].toString());
		  			                    vo.setDateStr(objects[2] != null? objects[2].toString():null);
		  			                    if(alert.getAlertStatusId().longValue() != vo.getId().longValue())
		  			                    	finalList.add(vo);
  			                      }
  			                    }
  			                }else{
  			                	IdNameVO vo = new IdNameVO();              
  			                    finalList.add(vo);                  
  			                }  
  			              }else if(govtDeptScopeIdsForUserId != null && govtDeptScopeIdsForUserId.size() > 0 && govtDeptScopeIdForAlert != null && govtDeptScopeIdsForUserId.contains(govtDeptScopeIdForAlert)){
  			                userType = "same";
  			                IdNameVO vo = new IdNameVO();                
  			                finalList.add(vo);
  			              }
  			              else{
  			                userType = "other";              
  			                IdNameVO vo = new IdNameVO();              
  			                finalList.add(vo);
  			              }
  			              
  			              
  			              if(finalList != null && finalList.size() > 0){
  			            	IdNameVO vo  =finalList.get(0);
  			            	vo.setApplicationStatus(userType+" - "+alertPresentStatusId);
  			            	vo.setUserStatus(userStatus);
  			            	if(entitlements != null && entitlements.size() > 0l){
  			            		for (String string : entitlements) {
  			            			if(string.trim().equalsIgnoreCase("GOVT_DEPARTMENT_ENTITLEMENT_NEW")){
  			            				vo.setPositionName("true");
  			            			}
								}
  			            	}
  			            	List<Object[]> list = alertCallerRelationDAO.getAlertCallerDetailsByAlert(alertId);
  			            	if(list != null && !list.isEmpty()){
  			            		for (Object[] obj : list) {
									IdNameVO callervo = new IdNameVO();
									callervo.setUserType(obj[1] != null ? obj[1].toString():"");
									callervo.setCallerName(obj[3] != null ? obj[3].toString():"");
									callervo.setMobileNo(obj[4] != null ? obj[4].toString():"");
									callervo.setStatus(obj[5] != null ? obj[5].toString():"");
									vo.getIdnameList().add(callervo);
								}
  			            	}
  			            	//vo.setUserType(alert.getAlertCallerType() != null ? alert.getAlertCallerType().getCallerType():"");// citizen/chief minister...etc
  			            	//vo.setCallerName(alert.getAlertCaller() != null ? alert.getAlertCaller().getCallerName():"");
  			            	//vo.setMobileNo(alert.getAlertCaller() != null ? alert.getAlertCaller().getMobileNo():"");
  			                List<String> dueDatesList = alertAssignedOfficerTrackingNewDAO.getAlertDueDate(alertId);
  			                if(commonMethodsUtilService.isListOrSetValid(dueDatesList))
  			                	vo.setDueDateStr(dueDatesList.get(0).toString());
  			                //srujana
  			             /* if( vo.getIdnameList().isEmpty() ){
  			              List<Object[]> petitionerDetails = alertMeekosamPetitionerDAO.getMeekosamPetitionerDetails(alertId);
			            	if(petitionerDetails != null && !petitionerDetails.isEmpty()){
			            		for (Object[] param : petitionerDetails) {
			            			IdNameVO returnVO = new IdNameVO();
			            			returnVO.setName(commonMethodsUtilService.getStringValueForObject(param[0]));
			        				returnVO.setRelativeName(commonMethodsUtilService.getStringValueForObject(param[1]));
			        				returnVO.setAge(commonMethodsUtilService.getLongValueForObject(param[2]));
			        				returnVO.setDateOfbirth(commonMethodsUtilService.getStringValueForObject(param[3]));
			        				returnVO.setGender(commonMethodsUtilService.getStringValueForObject(param[4]));
			        				returnVO.setHouseNo(commonMethodsUtilService.getStringValueForObject(param[5]));
			        				returnVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[6]));
			        				returnVO.setMeekosamOccupation(commonMethodsUtilService.getStringValueForObject(param[7]));
			        				returnVO.setMeekosamCasteCategory(commonMethodsUtilService.getStringValueForObject(param[8]));
			        				returnVO.setMeekosamArgeeCategory(commonMethodsUtilService.getStringValueForObject(param[9]));
			        				returnVO.setMeekosamAnnualIncome(commonMethodsUtilService.getStringValueForObject(param[10]));
			        				returnVO.setAdharNo(commonMethodsUtilService.getStringValueForObject(param[11]));
			        				returnVO.setVoterCardNo(commonMethodsUtilService.getStringValueForObject(param[12]));
			        				returnVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[13]));
			        				returnVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[14]));
			        				returnVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[15]));
			        				returnVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[16]));
			        				returnVO.setPanchayatName(commonMethodsUtilService.getStringValueForObject(param[17]));
			        				returnVO.setMunicipalityName(commonMethodsUtilService.getStringValueForObject(param[18]));
									vo.getPetitionerList().add(returnVO);
								}
			            	}
  			              }*/
  			              }
  			          
  			        } catch (Exception e) {
  			          e.printStackTrace();
  			          LOG.error("Error occured getStatusCompletionInfoNew() method of AlertManagementSystemService",e);
  			        }
  			            return finalList;
  			  }
  			
  			
  			public String getOfficernameDesignationForUser(Long userId){
  	          String officerName = null;
  	          String desgnationName = null;
  	          List<Long> lvlValueList = new ArrayList<Long>();
  	          String locationName = null;
  	          String levelName = null;
  	          String officerNameAnddesgnationName = null;
  	          String deptName = null;
  		          try {
  		            List<Object[]> LocationList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
  		            if(commonMethodsUtilService.isListOrSetValid(LocationList)){
  		              lvlValueList.add((Long) LocationList.get(0)[1]);
  		              if(LocationList.get(0)[2] !=null)
  		            	  levelName = LocationList.get(0)[2].toString();
  		            }
  		            List<Object[]> usrNameList	 = govtDepartmentDesignationOfficerDetailsNewDAO.getDesigNameForUser(userId);
  		            if(usrNameList.isEmpty()){
  		            	List<Object[]>	usrName = userDAO.getUserNameById(userId);
  		            	if(commonMethodsUtilService.isListOrSetValid(usrName)){
  			            	officerName = (String) usrName.get(0)[1];
  				          }
  		            }
  		            if(commonMethodsUtilService.isListOrSetValid(usrNameList)){
  		            	officerName = (String) usrNameList.get(0)[0];
  		            	desgnationName = (String) usrNameList.get(0)[1];
  		            	if(usrNameList.size() <= 1){
  		            			deptName = (String) usrNameList.get(0)[5];// dept short name 
  		            	}
  		            }
  		            
  		             List<Object[]> locNameList = govtDepartmentWorkLocationDAO.getParentLevelValuesListInfo(lvlValueList);
  		             if(commonMethodsUtilService.isListOrSetValid(locNameList)){
  		              locationName = (String) locNameList.get(0)[1];
  		             }
  		             if(desgnationName  != null){
  		            	if(deptName != null)
  		            		officerNameAnddesgnationName = officerName+"/"+desgnationName+"-"+deptName+"-"+locationName+" "+levelName; 
  		            	else
  		            		officerNameAnddesgnationName = officerName+"/"+desgnationName+"-"+locationName+" "+levelName;
  		             }else{
  		            	 officerNameAnddesgnationName = officerName+"/"+locationName+" "+levelName;
  		             }
  		              
  		          } catch (Exception e) {
  		            LOG.error("Error occured getOfficernameDesignationForUser() method of AlertManagementSystemService",e);
  		          }
  	          return officerNameAnddesgnationName;
  	        }
  	  		public List<AlertCoreDashBoardVO> prepareResultForStateNew(List<Object[]> alertList,List<AlertCoreDashBoardVO> returnList,String sortingType,String order,String group,String alertType){
  	  			try{
  		        		
  		        		Map<Long,String> lvlIdAndLvlName = new LinkedHashMap<Long,String>();
  		    			Map<Long,String> statusIdAndStatusName = new LinkedHashMap<Long,String>();
  		    			Map<Long,String> statusIdAndColor = new LinkedHashMap<Long,String>();    
  		    			
  		    			Map<Long,LinkedHashMap<Long,Long>> lvlIdThenStatusIdThenAlertCount = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
  		    			LinkedHashMap<Long,Long> statusIdThenAlertCount = null;
  		    			
  		    			
  		    			Set<Long> deptScopeIds = new HashSet<Long>();
  		    			Set<Long> statusIds = new HashSet<Long>();
  		    			if(alertList != null && alertList.size() > 0){
  		    				for(Object[] param : alertList){
  		    					deptScopeIds.add(commonMethodsUtilService.getLongValueForObject(param[3]));
  		    				}
  		    			}
  		    			
  		    			if(alertList != null && alertList.size() > 0){
  		    				for(Object[] param : alertList){
  		    					statusIds.add(commonMethodsUtilService.getLongValueForObject(param[4]));
  		    				}
  		    			}
  		    			List<Object[]> statusIdDtlsList = null;
  		    			if(statusIds != null && statusIds.size() > 0 && group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status") && alertType != null && !alertType.trim().isEmpty() && alertType.equalsIgnoreCase("alert")){
  		    			 statusIdDtlsList = alertStatusDAO.getAlertStatusDtlsBasidOnAlertIds(new ArrayList<Long>(statusIds));
  		        		}else{
  		        		  statusIdDtlsList = alertSubTaskStatusDAO.getAlertStatusDtlsBasidOnAlertIds(new ArrayList<Long>(statusIds));
  		        		}
  		    			
  		    			List<Object[]> deptScopeIdDtlsList = null;
  		    			if(deptScopeIds != null && deptScopeIds.size() >0){
  		    				deptScopeIdDtlsList = govtDepartmentScopeDAO.getGovtDepartmenttScopeDetailsBasedOnScopeIds(new ArrayList<Long>(deptScopeIds));
  		    			}
  		    			
  		    			
  		    			
  		    			if(deptScopeIdDtlsList != null && deptScopeIdDtlsList.size() > 0){
  		    				for(Object[] param : deptScopeIdDtlsList){
  		    					lvlIdAndLvlName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
  		    				}  
  		    			}
  		    			if(statusIdDtlsList != null && statusIdDtlsList.size() > 0){
  		    				for(Object[] param : statusIdDtlsList){
  		        				statusIdAndStatusName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
  		        				statusIdAndColor.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[2]));
  		    				}  
  		    			}
  		    			
  		    			
  		    			
  		        		if(alertList != null && alertList.size() > 0){
  		        			for(Object[] param : alertList){
  		        				statusIdThenAlertCount = lvlIdThenStatusIdThenAlertCount.get(commonMethodsUtilService.getLongValueForObject(param[3]));
  		    					if(statusIdThenAlertCount == null){
  		    						statusIdThenAlertCount = new LinkedHashMap<Long,Long>();
  		    						lvlIdThenStatusIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[3]), statusIdThenAlertCount);
  		    					}
  		    					statusIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[4]), commonMethodsUtilService.getLongValueForObject(param[5]));
  		    				}
  		        		}
  		    			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
  		    			if(lvlIdThenStatusIdThenAlertCount != null && lvlIdThenStatusIdThenAlertCount.size() > 0){
  		    				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : lvlIdThenStatusIdThenAlertCount.entrySet()){
  		    					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
  		    					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
  		    					alertCoreDashBoardVO.setName(lvlIdAndLvlName.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? lvlIdAndLvlName.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
  		    					buildStatusWiseTemplate(alertCoreDashBoardVO,statusIdAndStatusName,statusIdAndColor);
  		    					Long total = new Long(0L);
  		    					for(AlertCoreDashBoardVO boardVO : alertCoreDashBoardVO.getSubList()){
  		    						if(outerEntry.getValue() != null && outerEntry.getValue().get(boardVO.getId()) != null){
  		    							boardVO.setCount(outerEntry.getValue().get(boardVO.getId()));
  		    							total = total + outerEntry.getValue().get(boardVO.getId());
  		    						}
  		    					}
  		    					alertCoreDashBoardVO.setTotalCount(total);
  		    					returnList.add(alertCoreDashBoardVO);
  		    				}
  		    			}
  		    			if(returnList != null && returnList.size() > 0){
  		    				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("count")){
  		    					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
  		    						Collections.sort(returnList, alertAscendingCountWiseSortingLvlWise);
  		    					}else{
  		    						Collections.sort(returnList, alertDescCountWiseSortingLvlWise);
  		    					}
  		    				}
  		    				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("name")){
  		    					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
  		    						Collections.sort(returnList, alphabeticalAscSortLvlWise);
  		    					}else{
  		    						Collections.sort(returnList, alphabeticalDescendingSortLvlWise);
  		    					}
  		    				}
  		    			}
  		    			
  		    			return returnList;
  		        	}catch(Exception e){
  		        		e.printStackTrace();
  		        	}
  		        	return null;
  		        }
  	  		
  			//New Service
  			//Santosh
  			 public List<IdNameVO> getDeptListForMultiLvl(Long userId,Set<Long> deptSet){
                 try{
                   // Data Available depts
                   List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
                   List<Long> deptIds =new ArrayList<Long>(0);
                   if(deptSet !=null && deptSet.size()>0){
                	  List<Object[]> deptObj = govtDepartmentDesignationOfficerNewDAO.getDataAvailableDepts(deptSet);
                	  IdNameVO idNameVO = null; 
                	   if(deptObj != null && deptObj.size() > 0){
                           for(Object[] param : deptObj){
		                	   deptIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
		                       idNameVO = new IdNameVO();
		                       idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
		                       idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
		                       idNameVOs.add(idNameVO);
                           }
                	   }
                   }
                   
                     Map<Long,Map<Long,IdNameVO>> deptLevelMap = new HashMap<Long, Map<Long,IdNameVO>>();
                     if(deptIds != null && deptIds.size() > 0){
                    	   List<Object[]> rtrnDeptLevelObjLst = govtDepartmentScopeLevelDAO.getAllScopesOfAllDeptInAscOrder(deptIds);
                           setDepartmentWiseLevel(rtrnDeptLevelObjLst,deptLevelMap);    
                     }
                     if(idNameVOs != null && idNameVOs.size() > 0){
                    	 for(IdNameVO vo:idNameVOs){
                    		 
                    		 if(deptLevelMap != null){
                    			 if(vo.getId() != null && deptLevelMap.get(vo.getId()) != null && deptLevelMap.get(vo.getId()).values() != null){
                    				 vo.getSubList1().addAll(deptLevelMap.get(vo.getId()).values());
                    			 }
                    		 }
                    	 }
                     }  
                     return idNameVOs;
                 }catch(Exception e){
                	 e.printStackTrace();
			          LOG.error("Error occured getDeptListForMultiLvl() method of AlertManagementSystemService",e);
                 }
                 return null;
               }
  			 /*
  			  * Santosh (non-Javadoc)
  			  * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getDepartmentDetailsByDepartmentId(java.lang.Long, java.lang.Long, java.lang.String)
  			  */
  			 public List<IdNameVO> getDepartmentDetailsByDepartmentId(Long userId,Long departmentId,String designationType){
                 try{
                   List<Object[]> deptList= alertAssignedOfficerNewDAO.getDepartmentDetaislByDeptIds(departmentId);
                   List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>(); 
                   List<Long> deptIds = new ArrayList<Long>();
                   IdNameVO idNameVO = null;
                   
                   if(deptList != null && deptList.size() > 0){
                     for(Object[] param : deptList){
                       deptIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
                       idNameVO = new IdNameVO();
                       idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
                       idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
                       idNameVOs.add(idNameVO);
                     }
                   }
                     Map<Long,Map<Long,IdNameVO>> deptLevelMap = new HashMap<Long, Map<Long,IdNameVO>>();
                     List<Object[]> rtrnDeptLevelObjLst = null;
                     if(designationType != null && designationType.equalsIgnoreCase("levelWiseOfficer")){
                       Long levelId = 0L;
       	      			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
       	      			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
       	      				for(Object[] param : lvlValueAndLvlIdList){
       	      					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
       	      				}
       	      			}
       	      			List<Object[]> rtrnObjList = govtDepartmentScopeLevelDAO.getChildGovtScopesLevelByParentScopeLevel(levelId, departmentId);//levelId means Access Level 
       	      			Set<Long> scopeIdsSet = new HashSet<Long>();
       	      			if(rtrnObjList != null && rtrnObjList.size() > 0){
       	      				for(Object[] param:rtrnObjList){
       	      				scopeIdsSet.add(commonMethodsUtilService.getLongValueForObject(param[2]));
       	      				}
       	      			}
                           rtrnDeptLevelObjLst = govtDepartmentScopeLevelDAO.getRequiredDeptScopeByScopeIds(deptIds, scopeIdsSet);
                       }else{
                    	   rtrnDeptLevelObjLst = govtDepartmentScopeLevelDAO.getAllScopesOfAllDeptInAscOrder(deptIds);
                       }
                     setDepartmentWiseLevel(rtrnDeptLevelObjLst,deptLevelMap);    
                     if(idNameVOs != null && idNameVOs.size() > 0){
                    	 for(IdNameVO vo:idNameVOs){
                    		 if(deptLevelMap != null){
                    			 vo.getSubList1().addAll(deptLevelMap.get(vo.getId()).values());
                    		 }
                    	 }
                     }
                     return idNameVOs;
                 }catch(Exception e){
                	 e.printStackTrace();
			          LOG.error("Error occured getDepartmentDetailsByDepartmentId() method of AlertManagementSystemService",e);
                 }
                 return null;
               }
  			 public void setDepartmentWiseLevel(List<Object[]> objList,Map<Long,Map<Long,IdNameVO>> deptLocationLevelMap){
  				 try{
  					 Map<Long,Map<Long,Long>> deptLevelPositionMap = new HashMap<Long, Map<Long,Long>>();
  					 Map<Long,Map<Long,Long>> deptLevelReversePositionMap = new HashMap<Long, Map<Long,Long>>();
  					 Map<Long,String> levelIdAndNameMap = new HashMap<Long, String>();
  					 if(objList != null && objList.size() > 0){
  						Long position = 0l;
  						Long position1 = 0l;
  						 for(Object[] param:objList){
  							 
  							  Long departmentId = commonMethodsUtilService.getLongValueForObject(param[0]);
  							  Map<Long,IdNameVO> deptLevelMap = deptLocationLevelMap.get(departmentId);
  							   if(deptLevelMap == null){
  								 deptLevelMap = new LinkedHashMap<Long, IdNameVO>(0);
  								 deptLocationLevelMap.put(departmentId,deptLevelMap);
  							   }
  							   
  							    Map<Long,Long> positionLevelMap  = deptLevelPositionMap.get(departmentId);
  							    if(positionLevelMap == null){
  							    	 position = 0l;
  	  								 position = position+1l;
  							    	 positionLevelMap = new LinkedHashMap<Long, Long>(0);
  							    	 deptLevelPositionMap.put(departmentId,positionLevelMap);
  							    }
  							 
  							   Map<Long,Long> positionReverseLevelMap = deptLevelReversePositionMap.get(departmentId);
  							   if(positionReverseLevelMap == null){
  								 position1 = 0l;
	  							 position1 = position1+1l;
  								 positionReverseLevelMap = new LinkedHashMap<Long, Long>(0);
  								 deptLevelReversePositionMap.put(departmentId,positionReverseLevelMap);
  							   }
  							 
  							   IdNameVO deptLevelVO = new IdNameVO();
  							   deptLevelVO.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
  							   deptLevelVO.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
  							   deptLevelVO.setColor(commonMethodsUtilService.getStringValueForObject(param[3]));
  							   levelIdAndNameMap.put(deptLevelVO.getId(), deptLevelVO.getName());
  							   deptLevelMap.put(deptLevelVO.getId(), deptLevelVO);
  							 
  							   positionLevelMap.put(deptLevelVO.getId(), position);
  							   positionReverseLevelMap.put(position1,deptLevelVO.getId());
  							   position = position+1l;
  							   position1 = position1+1l;
  							  
  						 }
  					 }
  					 if(deptLocationLevelMap != null && deptLocationLevelMap.size() > 0){
  						  for(Entry<Long,Map<Long,IdNameVO>> deptEntry:deptLocationLevelMap.entrySet()){
  							   if(deptEntry.getValue() != null && deptEntry.getValue().size() > 0){
  								    for(Entry<Long,IdNameVO> levelEntry:deptEntry.getValue().entrySet()){
  								    	if(deptLevelPositionMap.get(deptEntry.getKey()) != null){
  								    		//Filter SelectBox Related Code
  								    		if(levelEntry.getKey() != null && levelEntry.getKey().longValue() != 1l){//For State Level We are not sending filter
  								    			Long levelPosition = deptLevelPositionMap.get(deptEntry.getKey()).get(levelEntry.getKey());
  	  								    		List<IdNameVO> upperLevelList = getUpperLevelList(levelPosition,deptEntry.getKey(),deptLevelReversePositionMap,levelIdAndNameMap);
  	  								    		if(upperLevelList != null && upperLevelList.size() > 0){
  	  								    		    IdNameVO lastLevelVO = upperLevelList.get(upperLevelList.size()-1);
  	  								    		    lastLevelVO.setChildLevelId(0l);
  	  								    			levelEntry.getValue().getSubList1().addAll(upperLevelList);
  	  								    		}
  	  								    	    //Setting Child Dept Scope List
  	    									     List<IdNameVO> childLevelDeptScopeLst = setChildLevelList(levelPosition,deptEntry.getKey(),deptLevelReversePositionMap,levelIdAndNameMap);
  	    	  								     if(childLevelDeptScopeLst != null && childLevelDeptScopeLst.size() > 0){
  	    	  								    	levelEntry.getValue().getIdnameList().addAll(childLevelDeptScopeLst);
  	    	  								     }
  								    		}
  								    	}
  								    }
  							   }
  						  }
  					 }
  				 }catch(Exception e){
  					  e.printStackTrace();
 			          LOG.error("Error occured setDepartmentWiseLevel() method of AlertManagementSystemService",e);
  				 }
  			 }
  			 public List<IdNameVO> setChildLevelList(Long levelPosition,Long deptId,Map<Long,Map<Long,Long>> deptLevelReversePositionMap,Map<Long,String> levelIdAndNameMap){
  				 List<IdNameVO> childLevelDeptList = new ArrayList<IdNameVO>();
  				 try{
  					 if(deptLevelReversePositionMap != null && deptLevelReversePositionMap.size() > 0){
  						 Map<Long,Long> levelMap = deptLevelReversePositionMap.get(deptId);
  						  if(levelMap != null && levelMap.size() > 0){
  							  Long levelSize = Long.valueOf(levelMap.size());
  							  for(Long position=levelPosition;position<=levelSize;position++){
  								  IdNameVO levelVO = new IdNameVO();
 								   Long levelId = levelMap.get(position);
 								   levelVO.setId(levelId);
 								   levelVO.setName(levelIdAndNameMap.get(levelId));
 								   if(levelPosition != levelSize){//For Last Dept Child we are not sending dept level
 									  childLevelDeptList.add(levelVO);   
 								   }
 								   	
 							  }
  						  }
  					 }
  				 }catch(Exception e){
  					  e.printStackTrace();
 			          LOG.error("Error occured setChildLevelList() method of AlertManagementSystemService",e);
  				 }
  				 return childLevelDeptList;
  			 }
  			 public List<IdNameVO> getUpperLevelList(Long levelPosition,Long deptId,Map<Long,Map<Long,Long>> deptLevelReversePositionMap,Map<Long,String> levelIdAndNameMap){
  				 List<IdNameVO> upperLevelList = new ArrayList<IdNameVO>();
  				 try{
  					 if(deptLevelReversePositionMap != null && deptLevelReversePositionMap.size() > 0){
  						 Map<Long,Long> levelMap = deptLevelReversePositionMap.get(deptId);
  						   if(levelMap != null && levelMap.size() > 0){
  							   for(Long position =1l;position <=levelPosition;){
  								   IdNameVO levelVO = new IdNameVO();
  								   Long levelId = levelMap.get(position);
  								   levelVO.setId(levelId);
  								   levelVO.setName(levelIdAndNameMap.get(levelId));
  								   Long childLevelId = levelMap.get(position+1);
  								    if(childLevelId != null && childLevelId.longValue() > 0){
  								    	levelVO.setChildLevelId(childLevelId);
  								    }
  								    position = position+1;
  								    if(levelId != null && levelId.longValue() != 1l){////State We are not Adding in filter list
  								    	upperLevelList.add(levelVO);	
  								    }
  							   }
  						   }
  					 }
  				 }catch(Exception e){
  					  e.printStackTrace();
 			          LOG.error("Error occured getUpperLevelList() method of AlertManagementSystemService",e);
  				 }
  				 return upperLevelList;
  			 }
			 /*
			  * Santosh (non-Javadoc)
			  * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getLocationBasedOnDepartmentLevel(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.lang.String, java.lang.Long)
			  */
  	  		 public List<IdNameVO> getLocationBasedOnDepartmentLevel(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String alertType,List<Long> calCntrIdList,List<Long> subLevelIds,
  	  				 List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,String type,Long childScopeId){
   	      		try{
   	      			
   	      			Date fromDate = null;
   	      			Date toDate = null;
   	      			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
   	      			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
   	      				fromDate = sdf.parse(fromDateStr);
   	      				toDate = sdf.parse(toDateStr);
   	      			}
   	      			
   	      		   prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
   	      			
   	      			List<Long> levelValues = new ArrayList<Long>();    
   	      			Long levelId = 0L;
   	      			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
   	      			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
   	      				for(Object[] param : lvlValueAndLvlIdList){
   	      					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
   	      					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
   	      				}
   	      			}
   	      		  
   	      			List<Long> deptScopeIdList = new ArrayList<Long>();
   	      			if(type != null && type.equalsIgnoreCase("deptSubLevelWiseAlert")){
	   	      			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
	   	      			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
	   	      				for(Object [] param : childDeptScopeIdList){
	   	      					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
	   	      				}
	   	      			}
   	      			}else if(type.equalsIgnoreCase("officerWiseAlert")){
   	      				if(childScopeId == 0l){
   	      				childScopeId=parentGovtDepartmentScopeId;
   	      				}
   	      			     deptScopeIdList.add(childScopeId);
   	      			}
   	      		   
   	      		   if(subLevelIds != null && subLevelIds.size() > 0){//In the case of filter data scope wise we are sending selected values
   			    	 deptScopeIdList.clear();
   			    	 deptScopeIdList.addAll(subLevelIds);
   			        }
   	      		   
   	      			List<Object[]> alertList = null;  
   	      			if(alertType != null && alertType.equalsIgnoreCase("alert")){
   	      			  alertList = alertAssignedOfficerNewDAO.getLocationBasedOnDepartmentLevelId(fromDate, toDate, stateId, electronicIdList, printIdList, levelId, levelValues, govtDepartmentId, parentGovtDepartmentScopeId, deptScopeIdList, calCntrIdList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds,childScopeId);	
   	      			}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
   	    			  alertList = govtAlertSubTaskDAO.getSubTaskAlertLocationLevelWise(fromDate, toDate, stateId, electronicIdList, printIdList, levelId, levelValues, govtDepartmentId, parentGovtDepartmentScopeId, deptScopeIdList, calCntrIdList,socialMediaTypeIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds,childScopeId);	
   	      			}
   	      		   List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();  
   	      		   setRequiredLocation(alertList,idNameVOs);	
   	      		   return idNameVOs;
   	      		}catch(Exception e){
   	      			e.printStackTrace();
   	      		}
   	      		return null;
   	        }
  		 /*
  		  * Santosh (non-Javadoc)
  		  * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getChildLocationBasedOnParentLocation(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.lang.String, java.lang.Long)
  		  */
		 public List<IdNameVO> getChildLocationBasedOnParentLocation(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long parentGovtDepartmentScopeValue,Long childLevelId,String alertType ,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,String type,Long resultLevelDeptScopeId){
      		try{
      			Date fromDate = null;
      			Date toDate = null;
      			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
      				fromDate = sdf.parse(fromDateStr);
      				toDate = sdf.parse(toDateStr);
      			}
      			
      			prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
      			
      			List<Long> levelValues = new ArrayList<Long>();    
      			Long levelId = 0L;
      			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
      			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
      				for(Object[] param : lvlValueAndLvlIdList){
      					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
      					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
      				}
      			}
      			
      			List<Long> deptScopeIdList = new ArrayList<Long>();
      			if(type != null && type.equalsIgnoreCase("deptSubLevelWiseAlert")){
	      			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
	      			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
	      				for(Object [] param : childDeptScopeIdList){
	      					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
	      				}
	      			}
      			}else if(type.equalsIgnoreCase("officerWiseAlert")){
	      			   deptScopeIdList.add(resultLevelDeptScopeId);
	      		}
      			List<Object[]> alertList = null;  
      			if(alertType != null && alertType.equalsIgnoreCase("alert")){
      			 alertList = alertAssignedOfficerNewDAO.getChildLocationBasedOnParentLocation(fromDate, toDate, stateId, electronicIdList, printIdList, levelId, levelValues, govtDepartmentId, deptScopeIdList, parentGovtDepartmentScopeId, parentGovtDepartmentScopeValue, childLevelId, calCntrIdList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds,resultLevelDeptScopeId);		
      			}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
      			  alertList = govtAlertSubTaskDAO.getSubTaskChildLocationByParentLocation(fromDate, toDate, stateId, electronicIdList, printIdList, levelId, levelValues, govtDepartmentId, parentGovtDepartmentScopeId, parentGovtDepartmentScopeValue, childLevelId, deptScopeIdList, calCntrIdList,socialMediaTypeIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds,resultLevelDeptScopeId);
      			}
    			List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
    			setRequiredLocation(alertList,idNameVOs);
    	       return idNameVOs;
      		}catch(Exception e){
      			 e.printStackTrace();
      			 LOG.error("Error occured getChildLocationBasedOnParentLocation() method of AlertManagementSystemService",e);
      		}
      		return null;
        }
		 public void setRequiredLocation(List<Object[]> objList,List<IdNameVO> idNameVOs){
			 try{
				if(objList != null && objList.size() > 0){
    				for(Object[] param:objList){
    					      IdNameVO idNameVO = new IdNameVO();
	          				  idNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
	          				  idNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
	          				  idNameVOs.add(idNameVO);
    				}
    			}
		    }catch(Exception e){
				 e.printStackTrace();
      			 LOG.error("Error occured getChildLocationBasedOnParentLocation() method of AlertManagementSystemService",e);
      		 }
		 }
		 /*
		  * Santosh (non-Javadoc)
		  * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getLocationWiseDepartmentOverviewAlertCount(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.util.List, java.util.List, java.lang.Long, java.lang.Long, java.lang.String, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List)
		  */
		 //getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewDynamicNew
		 public List<AlertCoreDashBoardVO> getLocationWiseDepartmentOverviewAlertCount(String fromDateStr, String toDateStr, Long stateId, 
					List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
					Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
					Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId, 
					String group,List<Long> calCntrIdList,List<Long> sublevels,Long filterParentScopeId,
					Long filterScopeValue,String searchType,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds ){
			try{
			
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
			
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			List<KeyValueVO> subLevels = new ArrayList<KeyValueVO>();
			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
			List<Long> deptScopeIdList = new ArrayList<Long>();
			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
				for(Object [] param : childDeptScopeIdList){
					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					KeyValueVO sublevel = new KeyValueVO();
					sublevel.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
					sublevel.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
					subLevels.add(sublevel);
				}
			}
		    if(sublevels != null && sublevels.size() > 0){//In the case of filter data scope wise we are sending selected values
		    	deptScopeIdList.clear();
		    	deptScopeIdList.addAll(sublevels);
		    }
		
		 
			List<AlertCoreDashBoardVO> returnList = new ArrayList<AlertCoreDashBoardVO>();
			List<Object[]> alertList = null; 
			if(deptScopeIdList != null && deptScopeIdList.size() > 0){
				if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status") ){
					if(alertType != null && alertType.equalsIgnoreCase("alert")){
					alertList = alertAssignedOfficerNewDAO.getAlertDetailsLocationWiseBasedOnDepartmentLevel(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,group,searchType,calCntrIdList,filterParentScopeId,filterScopeValue,socialMediaTypeIds,null,null,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
					}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
					 alertList = govtAlertSubTaskDAO.getSubTaskAlertCntBasedOnDepartmentLevel(fromDate, toDate, stateId, electronicIdList, printIdList, levelId, levelValues, govtDepartmentId, parentGovtDepartmentScopeId, deptScopeIdList, group, searchType, calCntrIdList, filterParentScopeId, filterScopeValue,socialMediaTypeIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
					}
					if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
						if(searchType != null && searchType.equalsIgnoreCase("statusWise") || searchType.equalsIgnoreCase("alertSource")){
							prepareResultForState(alertList,returnList,sortingType,order,alertType,searchType);
							sortStateLevelLstBasedOnScope(returnList,govtDepartmentId);
							return returnList;
						}
					}
				}
			}
			
			Map<Long,String> locIdAndLocNameMap = new LinkedHashMap<Long,String>();
			Map<Long,String> lvlIdAndLvlName = new LinkedHashMap<Long,String>();
			Map<Long,String> lvlIdAndColor = new LinkedHashMap<Long,String>();
			Map<Long,LinkedHashMap<Long,Long>> locIdThenLvlIdThenAlertCount = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
			LinkedHashMap<Long,Long> levelIdAndAlertCountMap = null;
			
			Set<Long> deptScopeIds = new HashSet<Long>();
			if(alertList != null && alertList.size() > 0){
				for(Object[] param : alertList){
					deptScopeIds.add(commonMethodsUtilService.getLongValueForObject(param[3]));
				}
			}
			
			List<Object[]> deptScopeIdDtlsList = null;
			if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status") && deptScopeIds != null && deptScopeIds.size() >0){
				if(alertType != null && alertType.equalsIgnoreCase("subTask") && searchType != null && searchType.equalsIgnoreCase("statuswise")){
					deptScopeIdDtlsList =alertSubTaskStatusDAO.getAlertStatusDtlsBasidOnAlertIds(new ArrayList<Long>(deptScopeIds)); 
				}else if(searchType != null && searchType.equalsIgnoreCase("alertSource")){
					deptScopeIdDtlsList = alertCategoryDAO.getAlertCategoryByCategoryIds(new ArrayList<Long>(deptScopeIds));	
				}else if(searchType != null && searchType.equalsIgnoreCase("scopewise")){
					deptScopeIdDtlsList = govtDepartmentScopeDAO.getGovtDepartmenttScopeDetailsBasedOnScopeIds(new ArrayList<Long>(deptScopeIds));
				}else if(searchType != null && searchType.equalsIgnoreCase("statuswise")){
					deptScopeIdDtlsList = alertStatusDAO.getAlertStatusDtlsBasidOnAlertIds(new ArrayList<Long>(deptScopeIds));	
				}
			}
			if(deptScopeIdDtlsList != null && deptScopeIdDtlsList.size() > 0){
				for(Object[] param : deptScopeIdDtlsList){
					lvlIdAndLvlName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					lvlIdAndColor.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[2]));
				}  
			}
			
			if(alertList != null && alertList.size() > 0){   
				for(Object[] param : alertList){
					locIdAndLocNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]),commonMethodsUtilService.getStringValueForObject(param[2]));
					levelIdAndAlertCountMap = locIdThenLvlIdThenAlertCount.get(commonMethodsUtilService.getLongValueForObject(param[1]));
					if(levelIdAndAlertCountMap == null){
						levelIdAndAlertCountMap = new LinkedHashMap<Long,Long>();
						locIdThenLvlIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[1]), levelIdAndAlertCountMap);
					}
					levelIdAndAlertCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[4]));
				}
			}
			
			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
			if(locIdThenLvlIdThenAlertCount != null && locIdThenLvlIdThenAlertCount.size() > 0){
				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : locIdThenLvlIdThenAlertCount.entrySet()){
					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
					alertCoreDashBoardVO.setName(locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
					buildStatusWiseTemplate(alertCoreDashBoardVO,lvlIdAndLvlName,lvlIdAndColor);
					Long total = new Long(0L);
					for(AlertCoreDashBoardVO boardVO : alertCoreDashBoardVO.getSubList()){
						if(outerEntry.getValue() != null && outerEntry.getValue().get(boardVO.getId()) != null){
							boardVO.setCount(outerEntry.getValue().get(boardVO.getId()));
							total = total + outerEntry.getValue().get(boardVO.getId());
						}
					}
					alertCoreDashBoardVO.setTotalCount(total);
					returnList.add(alertCoreDashBoardVO);
				}
			}
			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
				if(returnList != null && returnList.size() > 0){
					returnList.get(0).getSubLevels().addAll(subLevels);
					if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("count")){
						if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("Default")){
    						sortStateLevelLstBasedOnScope(returnList,govtDepartmentId);
    					}else{
    						if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
    							Collections.sort(returnList.get(0).getSubList(), alertStateAscendingCountWiseSortingLvlWise);
    						}else{
    							Collections.sort(returnList.get(0).getSubList(), alertStateDescCountWiseSortingLvlWise);
    						}
    					}
					}
					if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("name")){
						if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
							Collections.sort(returnList.get(0).getSubList(), alphabeticalStateAscSortLvlWise);
						}else{
							Collections.sort(returnList.get(0).getSubList(), alphabeticalStateDescendingSortLvlWise);
						}
					}
				}   
			}else{
				if(returnList != null && returnList.size() > 0){
					returnList.get(0).getSubLevels().addAll(subLevels);
					if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("count")){
						if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
							Collections.sort(returnList, alertAscendingCountWiseSortingLvlWise);
						}else{
							Collections.sort(returnList, alertDescCountWiseSortingLvlWise);
						}
					}
					if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("name")){
						if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
							Collections.sort(returnList, alphabeticalAscSortLvlWise);
						}else{
							Collections.sort(returnList, alphabeticalDescendingSortLvlWise);
						}
					}
				}   
			}
			
			return returnList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewDynamicNew() method of AlertManagementSystemService",e);
	 }
			return null;
}	 
	/*
	 * Santosh	 (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getAlertDetailsBasedOnLocation(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.util.List, java.util.List, java.lang.Long, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.lang.Long)
	 */
	public List<AlertCoreDashBoardVO> getAlertDetailsBasedOnLocation(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long deptScopeId,List<Long> alertStatusIds,List<Long> calCntrIds,Long locationValue,String alertType,Long alertCategoryId,List<Long> subLevelList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,Long govtOfficerId){
	      		try{
	      			
	      			Date fromDate = null;  
	      			Date toDate = null;
	      			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	      			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
	      				fromDate = sdf.parse(fromDateStr);
	      				toDate = sdf.parse(toDateStr);
	      			}
	      			
	      			prepareRequiredParameter(printIdList,electronicIdList,calCntrIds,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
	      			
	      			List<Long> levelValues = new ArrayList<Long>();    
	      			Long levelId = 0L;
	      			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
	      			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
	      				for(Object[] param : lvlValueAndLvlIdList){
	      					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
	      					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
	      				}
	      			}
	      			
	    			List<Long> alertList = null;
	    			if(alertType != null && alertType.equalsIgnoreCase("alert")){
	    			  alertList = alertAssignedOfficerNewDAO.getAlertIdsBasedOnRequiredParameter(fromDate, toDate, stateId, electronicIdList, printIdList, levelId, levelValues, govtDepartmentId, parentGovtDepartmentScopeId, locationValue,calCntrIds, deptScopeId, alertStatusIds,alertCategoryId,subLevelList,socialMediaTypeIds,alertSeverityIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds,govtOfficerId);
	    			}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
	    				alertList = govtAlertSubTaskDAO.getSubTaskAlertIdsBasedOnLocation(fromDate, toDate, stateId, electronicIdList, printIdList, levelId, levelValues, govtDepartmentId, parentGovtDepartmentScopeId, locationValue, deptScopeId, alertStatusIds, calCntrIds,alertCategoryId,subLevelList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds,govtOfficerId);
	    			}
	    		 	List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
	      			if(alertList != null && alertList.size() > 0){
	    				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertList));
	    				setAlertDtls(alertCoreDashBoardVOs, list); 
	    			}
	      			
	    			//get subtask count.
	    			List<Object[]> subtaskCountList = null;
	    			if(alertList != null && alertList.size() > 0){
	    				subtaskCountList = govtAlertSubTaskDAO.getSubTaskCount(alertList);
	    			}
	    			//create a map from alertId and subtask count.
	    			Map<Long,Long> alertIdAndSubTaskCountMap = new HashMap<Long,Long>();
	    			if(subtaskCountList != null && subtaskCountList.size() > 0){
	    				for(Object[] param : subtaskCountList){
	    					alertIdAndSubTaskCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
	    				}
	    			}
	    			if(alertCoreDashBoardVOs != null && alertCoreDashBoardVOs.size() > 0){
	    				for(AlertCoreDashBoardVO alertCoreDashBoardVO : alertCoreDashBoardVOs){
	    					if(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()) != null){
	    						alertCoreDashBoardVO.setSubTaskCount(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()));
	    					}
	    				}
	    			}
	      			
	      			return alertCoreDashBoardVOs;        
	      			
	      		}catch(Exception e){
	      			e.printStackTrace();
	    			LOG.error("Error occured getAlertDetailsBasedOnLocation() method of AlertManagementSystemService",e);
	    	
	      		}
	      		return null;
	      	}
  	/*
     * Swadhin K Lenka
     * overview and Status new
     * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getStateThenGovtDeptScopeWiseAlertCountStatusWise(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
     */
    public List<AlertCoreDashBoardVO> getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewDynamic(String fromDateStr, String toDateStr, Long stateId, 
    									List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
    									Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
    									Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId, String group,List<Long> calCntrIdList,List<Long> sublevels){
		try{
			
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			if(printIdList != null && printIdList.size() > 0){  
				if(electronicIdList != null && electronicIdList.size() == 0){
					electronicIdList.add(0L);
				}
			}else if(electronicIdList != null && electronicIdList.size() > 0){
				if(printIdList != null && printIdList.size() == 0){
					printIdList.add(0L);
				}
			}/*else{
				electronicIdList.add(0L);
				printIdList.add(0L);
			}*/
			
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			List<KeyValueVO> subLevels = new ArrayList<KeyValueVO>();
			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
			List<Long> deptScopeIdList = new ArrayList<Long>();
			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
				for(Object [] param : childDeptScopeIdList){
					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					KeyValueVO sublevel = new KeyValueVO();
					sublevel.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
					sublevel.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
					subLevels.add(sublevel);
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(sublevels)){
				deptScopeIdList.clear();
				deptScopeIdList.addAll(sublevels);
			}
			//start
			List<Object[]> deptAndLvlList = govtDepartmentScopeLevelDAO.getAllScopesInAscOrder(govtDepartmentId);
			//for level position create a map for scope and its position
			Map<Long,Long> scopeIdAndLevelMap = new HashMap<Long,Long>();
			Map<Long,Long> levelAndScopeIdMap = new HashMap<Long,Long>();
			long position = 0l;
			if(deptAndLvlList != null && deptAndLvlList.size() > 0){
				for(Object[] param : deptAndLvlList){
					position+=1;
					scopeIdAndLevelMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), position);
					levelAndScopeIdMap.put(position, commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			//based on the dept scope get its position
			position = scopeIdAndLevelMap.get(parentGovtDepartmentScopeId);
			//end
			
			List<AlertCoreDashBoardVO> returnList = new ArrayList<AlertCoreDashBoardVO>();
			List<Object[]> alertList = null; 
			if(deptScopeIdList != null && deptScopeIdList.size() > 0){
				if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status") ){
    				if(position == 1l && parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
            			alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,null,calCntrIdList);
            			prepareResultForState(alertList,returnList,sortingType,order,null,null);
        				return returnList;
    				}else if(position == 2l){
    					if(alertType != null && alertType.equalsIgnoreCase("alert")){
    						alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,"statusWise",calCntrIdList);
    					}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
    						alertList = govtAlertSubTaskDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,"statusWise",calCntrIdList);
    					}
        			}else if(position == 3l){
        				Long justUpperLvl = levelAndScopeIdMap.get(position-1);
        				if(alertType != null && alertType.equalsIgnoreCase("alert")){
        					alertList = alertAssignedOfficerNewDAO.getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,"statusWise",calCntrIdList,justUpperLvl);
        				}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
        					alertList = govtAlertSubTaskDAO.getDivisionWorkLocationThenGovtDeptScopeWiseSubTaskForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,"statusWise",calCntrIdList,justUpperLvl);
        				}
        			}else if(position == 4l){
        				Long levelTwo = levelAndScopeIdMap.get(position-2);
        				Long levelThree = levelAndScopeIdMap.get(position-1);
        				if(alertType != null && alertType.equalsIgnoreCase("alert")){
        					alertList = alertAssignedOfficerNewDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,"statusWise",calCntrIdList,levelTwo,levelThree);
        				}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
        					alertList = govtAlertSubTaskDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,"statusWise",calCntrIdList,levelTwo,levelThree);
        				}  
        			}
    			}else{
    				if(position == 1l || position == 2l){
            			alertList = alertAssignedOfficerNewDAO.getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,group,null,calCntrIdList);
        			}else if(position == 3l){
        				Long justUpperLvl = levelAndScopeIdMap.get(position-1);
            			alertList = alertAssignedOfficerNewDAO.getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,null,group,null,calCntrIdList,justUpperLvl);
        			}else if(position == 4l){
        				Long levelTwo = levelAndScopeIdMap.get(position-2);
        				Long levelThree = levelAndScopeIdMap.get(position-1);
            			alertList = alertAssignedOfficerNewDAO.getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,districtWorkLocationId,divisionWorkLocationId,subDivisionWorkLocationId,null,group,null,calCntrIdList,levelTwo,levelThree);
        			}
    			}
    			
			}
			
			Map<Long,String> locIdAndLocNameMap = new LinkedHashMap<Long,String>();
			Map<Long,String> lvlIdAndLvlName = new LinkedHashMap<Long,String>();
			Map<Long,String> lvlIdAndColor = new LinkedHashMap<Long,String>();
			Map<Long,LinkedHashMap<Long,Long>> locIdThenLvlIdThenAlertCount = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
			LinkedHashMap<Long,Long> levelIdAndAlertCountMap = null;
			
			Set<Long> deptScopeIds = new HashSet<Long>();
			if(alertList != null && alertList.size() > 0){
				for(Object[] param : alertList){
					deptScopeIds.add(commonMethodsUtilService.getLongValueForObject(param[3]));
				}
			}
			List<Object[]> deptScopeIdDtlsList = null;
			if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status") && deptScopeIds != null && deptScopeIds.size() >0){
				deptScopeIdDtlsList = alertStatusDAO.getAlertStatusDtlsBasidOnAlertIds(new ArrayList<Long>(deptScopeIds));
			}else if(deptScopeIds != null && deptScopeIds.size() >0){ 
				deptScopeIdDtlsList = govtDepartmentScopeDAO.getGovtDepartmenttScopeDetailsBasedOnScopeIds(new ArrayList<Long>(deptScopeIds));
			}
			
			
			if(deptScopeIdDtlsList != null && deptScopeIdDtlsList.size() > 0){
				for(Object[] param : deptScopeIdDtlsList){
					lvlIdAndLvlName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					lvlIdAndColor.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[2]));

				}  
			}
			
			if(alertList != null && alertList.size() > 0){   
				for(Object[] param : alertList){
					locIdAndLocNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]),commonMethodsUtilService.getStringValueForObject(param[2]));
					
					levelIdAndAlertCountMap = locIdThenLvlIdThenAlertCount.get(commonMethodsUtilService.getLongValueForObject(param[1]));
					if(levelIdAndAlertCountMap == null){
						levelIdAndAlertCountMap = new LinkedHashMap<Long,Long>();
						locIdThenLvlIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[1]), levelIdAndAlertCountMap);
					}
					levelIdAndAlertCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[4]));
				}
			}
			
			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
			if(locIdThenLvlIdThenAlertCount != null && locIdThenLvlIdThenAlertCount.size() > 0){
				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : locIdThenLvlIdThenAlertCount.entrySet()){
					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
					alertCoreDashBoardVO.setName(locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
					buildStatusWiseTemplate(alertCoreDashBoardVO,lvlIdAndLvlName,lvlIdAndColor);
					Long total = new Long(0L);
					for(AlertCoreDashBoardVO boardVO : alertCoreDashBoardVO.getSubList()){
						if(outerEntry.getValue() != null && outerEntry.getValue().get(boardVO.getId()) != null){
							boardVO.setCount(outerEntry.getValue().get(boardVO.getId()));
							total = total + outerEntry.getValue().get(boardVO.getId());
						}
					}
					alertCoreDashBoardVO.setTotalCount(total);
					returnList.add(alertCoreDashBoardVO);
				}
			}
			
			if(returnList != null && returnList.size() > 0){
				returnList.get(0).getSubLevels().addAll(subLevels);  
				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("count")){
					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
						Collections.sort(returnList, alertAscendingCountWiseSortingLvlWise);
					}else{
						Collections.sort(returnList, alertDescCountWiseSortingLvlWise);
					}
				}
				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("name")){
					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
						Collections.sort(returnList, alphabeticalAscSortLvlWise);
					}else{
						Collections.sort(returnList, alphabeticalDescendingSortLvlWise);
					}
				}
			}   
			return returnList;  
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;  
	}
    public Map<String,List<String>> getMonthWeekAndDays(String startDate,String endDate,String type){
    	Map<String,List<String>> returnDays = new LinkedHashMap<String, List<String>>();
    	try{    
		
		List<String> wkDays = new ArrayList<String>();  
		List<String> daysArr = new ArrayList<String>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
    public void prepareRequiredParameter(List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,
    		List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
    	try{
    		 if(printIdsList != null && printIdsList.size() > 0){  
		            if(electronicIdsList != null && electronicIdsList.size() == 0){
		            	electronicIdsList.add(0L);
		            }
		            if(calCntrIdList != null && calCntrIdList.size() == 0){
		                calCntrIdList.add(0L);
		             }
		            if(socialMediaTypeIds != null && socialMediaTypeIds.size() ==0){
		            	socialMediaTypeIds.add(0L);
		            }
		            if(mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() ==0){
		            	mondayGrievanceTypeIds.add(0L);
		            }
		            if(janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() ==0){
		            	janmabhoomiTypeIds.add(0L);
		            }
		            if(specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() ==0){
		            	specialGrievanceTypeIds.add(0L);
		            }
		            if(generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() ==0){
		            	generalGrievanceTypeIds.add(0L);
		            }
		      }else if(electronicIdsList != null && electronicIdsList.size() > 0){
		            if(printIdsList != null && printIdsList.size() == 0){
		            	printIdsList.add(0L);
		            }
		            if(calCntrIdList != null && calCntrIdList.size() == 0){
		                calCntrIdList.add(0L);
		             }
		            if(socialMediaTypeIds != null && socialMediaTypeIds.size() ==0){
		            	socialMediaTypeIds.add(0L);
		            }
		            if(mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() ==0){
		            	mondayGrievanceTypeIds.add(0L);
		            }
		            if(janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() ==0){
		            	janmabhoomiTypeIds.add(0L);
		            }
		            if(specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() ==0){
		            	specialGrievanceTypeIds.add(0L);
		            }
		            if(generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() ==0){
		            	generalGrievanceTypeIds.add(0L);
		            }
		      }else if(calCntrIdList != null && calCntrIdList.size() > 0){
		           if(printIdsList != null && printIdsList.size() == 0){
		            	printIdsList.add(0L);
		           }
		           if(electronicIdsList != null && electronicIdsList.size() == 0){
		            	 electronicIdsList.add(0L);
		           }
		           if(socialMediaTypeIds != null && socialMediaTypeIds.size() ==0){
		            	socialMediaTypeIds.add(0L);
		            }
		           if(mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() ==0){
		            	mondayGrievanceTypeIds.add(0L);
		            }
		            if(janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() ==0){
		            	janmabhoomiTypeIds.add(0L);
		            }
		            if(specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() ==0){
		            	specialGrievanceTypeIds.add(0L);
		            }
		            if(generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() ==0){
		            	generalGrievanceTypeIds.add(0L);
		            }
		      }else if(socialMediaTypeIds != null && socialMediaTypeIds.size()>0){
		        	  if(printIdsList != null && printIdsList.size() == 0){
			            	printIdsList.add(0L);
			           }
			           if(electronicIdsList != null && electronicIdsList.size() == 0){
			            	 electronicIdsList.add(0L);
			           } 
			           if(calCntrIdList != null && calCntrIdList.size() == 0){
			                calCntrIdList.add(0L);
			           }
			           if(mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() ==0){
			            	mondayGrievanceTypeIds.add(0L);
			            }
			            if(janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() ==0){
			            	janmabhoomiTypeIds.add(0L);
			            }
			            if(specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() ==0){
			            	specialGrievanceTypeIds.add(0L);
			            }
			            if(generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() ==0){
			            	generalGrievanceTypeIds.add(0L);
			            }
	            }else if(mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() > 0){
	            	 if(printIdsList != null && printIdsList.size() == 0){
			            	printIdsList.add(0L);
			           }
			           if(electronicIdsList != null && electronicIdsList.size() == 0){
			            	 electronicIdsList.add(0L);
			           } 
			           if(calCntrIdList != null && calCntrIdList.size() == 0){
			                calCntrIdList.add(0L);
			           }
			           if(socialMediaTypeIds != null && socialMediaTypeIds.size() ==0){
			            	socialMediaTypeIds.add(0L);
			            }
			           if(janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() ==0){
			            	janmabhoomiTypeIds.add(0L);
			           }
			           if(specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() ==0){
			            	specialGrievanceTypeIds.add(0L);
			           }
			           if(generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() ==0){
			            	generalGrievanceTypeIds.add(0L);
			           }
	            }else if(janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() > 0){
	            	if(printIdsList != null && printIdsList.size() == 0){
		            	printIdsList.add(0L);
		           }
		           if(electronicIdsList != null && electronicIdsList.size() == 0){
		            	 electronicIdsList.add(0L);
		           }
		           if(socialMediaTypeIds != null && socialMediaTypeIds.size() ==0){
		            	socialMediaTypeIds.add(0L);
		            }
		           if(calCntrIdList != null && calCntrIdList.size() == 0){
		                calCntrIdList.add(0L);
		           }
		           if(mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() ==0){
		            	mondayGrievanceTypeIds.add(0L);
		            }
		            if(specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() ==0){
		            	specialGrievanceTypeIds.add(0L);
		            }
		            if(generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() ==0){
		            	generalGrievanceTypeIds.add(0L);
		            }
	            }else if(specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() > 0){
	            	if(printIdsList != null && printIdsList.size() == 0){
		            	printIdsList.add(0L);
		           }
		           if(electronicIdsList != null && electronicIdsList.size() == 0){
		            	 electronicIdsList.add(0L);
		           }
		           if(socialMediaTypeIds != null && socialMediaTypeIds.size() ==0){
		            	socialMediaTypeIds.add(0L);
		            }
		           if(calCntrIdList != null && calCntrIdList.size() == 0){
		                calCntrIdList.add(0L);
		           }
		           if(mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() ==0){
		            	mondayGrievanceTypeIds.add(0L);
		            }
		            if(janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() ==0){
		            	janmabhoomiTypeIds.add(0L);
		            }
		            if(generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() ==0){
		            	generalGrievanceTypeIds.add(0L);
		            }
	            }else if(generalGrievanceTypeIds != null && generalGrievanceTypeIds.size() > 0){
	            	if(printIdsList != null && printIdsList.size() == 0){
		            	printIdsList.add(0L);
		           }
		           if(electronicIdsList != null && electronicIdsList.size() == 0){
		            	 electronicIdsList.add(0L);
		           }
		           if(socialMediaTypeIds != null && socialMediaTypeIds.size() ==0){
		            	socialMediaTypeIds.add(0L);
		            }
		           if(calCntrIdList != null && calCntrIdList.size() == 0){
		                calCntrIdList.add(0L);
		           }
		           if(mondayGrievanceTypeIds != null && mondayGrievanceTypeIds.size() ==0){
		            	mondayGrievanceTypeIds.add(0L);
		            }
		            if(janmabhoomiTypeIds != null && janmabhoomiTypeIds.size() ==0){
		            	janmabhoomiTypeIds.add(0L);
		            }
		            if(specialGrievanceTypeIds != null && specialGrievanceTypeIds.size() ==0){
		            	specialGrievanceTypeIds.add(0L);
		            }
		        }
    	}catch(Exception e){
    		e.printStackTrace();
			LOG.error("Error occured prepareRequiredParameter() method of AlertManagementSystemService",e);
    	}
    }
    
    public List<IdNameVO> getDepartmentDetailsOfAlert(Long alertId){
    	
    	List<IdNameVO> finalList = new ArrayList<IdNameVO>();
    	
    	try {
			List<Object[]> obj = alertDAO.getDepartmentDetailsOfAlert(alertId);
			
			if(obj !=null && obj.size()>0){
				for (Object[] objects : obj) {					
					IdNameVO vo = new IdNameVO();
					vo.setId(objects[0] !=null ? (Long)objects[0]:0l);
					vo.setName(objects[1] !=null ? objects[1].toString():"");					
					finalList.add(vo);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error occured getDepartmentDetailsOfAlert() method of AlertManagementSystemService",e);
		}
    	return finalList;
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
public List<IdNameVO> getLvlsForDepatmnt(Long userId,Long departmentId){
	List<IdNameVO> resultList = new ArrayList<IdNameVO>();
	try {						
		/*List<Object[]> levelObj = govtDepartmentScopeLevelDAO.getDepartmentLevels(departmentId);
		if(levelObj != null && levelObj.size()>0){
			for (Object[] param : levelObj) {
				IdNameVO VO = new IdNameVO();
				   VO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				   VO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
				   resultList.add(VO);
			}				 
		}*/	
		
		Long levelId = 0L;
        List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
           if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
             for(Object[] param : lvlValueAndLvlIdList){
               levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
             }
           }
           List<Object[]> rtrnObjList = govtDepartmentScopeLevelDAO.getChildGovtScopesLevelNamesByParentScopeLevel(levelId, departmentId);//levelId means Access Level 
           if(rtrnObjList != null && rtrnObjList.size() > 0){
             for(Object[] param:rtrnObjList){
            	 IdNameVO VO = new IdNameVO();
				   VO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				   VO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
				   resultList.add(VO);
             }
           }
	} catch (Exception e) {
		LOG.error("Error occured getLvlsForDepatmnt() method of AlertManagementSystemService",e);
	}
	return resultList;
}
public List<IdNameVO> getStatusByType(String type){
	List<IdNameVO> finalList = new ArrayList<IdNameVO>();
	try{
		List<Object[]> statusList = null;
		if(type != null && type.trim().equalsIgnoreCase("alerts")){
			statusList = alertStatusDAO.getAllStatus();
		}else if(type != null && type.trim().equalsIgnoreCase("subTask")){
			statusList = alertSubTaskStatusDAO.getAllSubTaskStatus1();
		}
		
		if(statusList != null && statusList.size() > 0l){
			for (Object[] objects : statusList) {
				IdNameVO vo = new IdNameVO();
				vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
				vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
				finalList.add(vo);
			}
		}
		
	}catch(Exception e){
		LOG.error("Error occured getStatusByType() method of AlertManagementSystemService",e);
	}
	return finalList;
}
public List<IdNameVO> getDeptListForGreivanceReport(Long userId){
	try{
		List<Object[]> deptList = govtAlertDepartmentLocationNewDAO.getDeptIdAndNameForUserAccessLevel(userId);
		Set<Long> deptIdList = new HashSet<Long>();
		if(deptList != null && deptList.size() > 0){
			for(Object[] param : deptList){
				deptIdList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
			}
		}
		List<Object[]> list = null;
		if(deptIdList != null && deptIdList.size() > 0){
			list = govtDepartmentDesignationOfficerDetailsNewDAO.getDeptListForGreivanceReport(deptIdList);
		}
		List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
		 IdNameVO idNameVO = null;
		 if(list != null && list.size() > 0){
			 for(Object[] param : list){
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
		LOG.error("Error occured getDeptListForGreivanceReport() method of AlertManagementSystemService",e);
	}
	return null;
}
public FilterSectionVO getFilterSectionAlertNewDetails(Long userId,List<Long> deptIdList){
	 FilterSectionVO filterVo =new FilterSectionVO();
	try {
		Long levelId = 0L;
        List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
        if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
          for(Object[] param : lvlValueAndLvlIdList){
            levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
          }
        }
        List<Object[]> scopeIds = govtDepartmentScopeLevelDAO.getGovtScopesLevelByParentScopeLevel(levelId, deptIdList);//levelId means Access Level 
		 setFilterSectionAlertDetails(scopeIds,filterVo,"scopes");
		List<Object[]> severityIds = alertSeverityDAO.getFilterSectionDetailsOnSeverity();
		 setFilterSectionAlertDetails(severityIds,filterVo,"severity");
		List<Object[]> categoryIds = alertCategoryDAO.getAllCategory1();
		 setFilterSectionAlertDetails(categoryIds,filterVo,"category");
		List<Object[]> editionsIds = newsPaperDAO.getNewPaperList();
		 setFilterSectionAlertDetails(editionsIds,filterVo,"editions");
		List<Object[]> tvNewsChannelIds = tvNewsChannelDAO.getAllElectrinicMedia();
		 setFilterSectionAlertDetails(tvNewsChannelIds,filterVo,"tvNewsChannel");
		} catch (Exception e) {
			LOG.error(" Exception Occured in getFilterSectionAlertDetails() method, Exception - ",e);
		}		
		return filterVo;
	}

public List<AlertCoreDashBoardVO> getTotalAlertByStatusNew(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,List<Long> statusIdList,Long deptId,List<Long> calCntrIdList,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long startDay,Long endDay,Long scopeId,List<Long> locationIdList,List<Long> subTaskStatusIdList,String isMoreThanYrChkd,String isLagChkd,List<Long> filterSocialMediaIds,List<Long> filterCallCenterIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
	LOG.info("Entered in getTotalAlertByStatus() method of AlertManagementSystemService{}");
	try{
		Date fromDate = null;
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
			fromDate = sdf.parse(fromDateStr);
			toDate = sdf.parse(toDateStr);
		}
		
		prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
		
		List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
		List<Object[]> alertList =null;
		if(statusIdList != null && statusIdList.size()>0){
		 alertList = alertDAO.getTotalAlertByAlertStatus(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusIdList,deptId,calCntrIdList,impactLevelIdList,priorityIdList,alertSourceIdList,printMediaIdList,electronicMediaIdList,scopeId,locationIdList,subTaskStatusIdList,filterSocialMediaIds,filterCallCenterIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
		//setAlertDtls(alertCoreDashBoardVOs, alertList);
		setAlertDtlsNew(alertCoreDashBoardVOs, alertList,startDay,endDay,"alertStatus",isMoreThanYrChkd,isLagChkd);
		}
		if(subTaskStatusIdList != null && subTaskStatusIdList.size()>0){
			alertList = alertDAO.getTotalAlertBySubTaskStatus(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusIdList,deptId,calCntrIdList,impactLevelIdList,priorityIdList,alertSourceIdList,printMediaIdList,electronicMediaIdList,scopeId,locationIdList,subTaskStatusIdList,filterSocialMediaIds,filterCallCenterIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			setAlertDtlsNew(alertCoreDashBoardVOs, alertList,startDay,endDay,"subTaskStatus",isMoreThanYrChkd,isLagChkd);
		}
		//set Subtask into alert logic 
		List<Long> alertIds = new ArrayList<Long>();
		if(alertList != null && alertList.size() > 0){
			for(Object[] param : alertList){
				alertIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
			}
		}	
		//get subtask count.
		List<Object[]> subtaskCountList = null;
		if(alertIds != null && alertIds.size() > 0){
			subtaskCountList = govtAlertSubTaskDAO.getSubTaskCount(alertIds);
		}
		/*List<Object[]> subTaskStatusList =govtAlertSubTaskDAO.getSubTaskStatusIds(alertIds);
		if(subTaskStatusList != null && subTaskStatusList.size()>0){
		  setAlertDtlsNew(alertCoreDashBoardVOs, subTaskStatusList,startDay,endDay,"subTaskStatus");
		}*/
		//create a map from alertId and subtask count.
		Map<Long,Long> alertIdAndSubTaskCountMap = new HashMap<Long,Long>();
		if(subtaskCountList != null && subtaskCountList.size() > 0){
			for(Object[] param : subtaskCountList){
				alertIdAndSubTaskCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
			}
		}
		if(alertCoreDashBoardVOs != null && alertCoreDashBoardVOs.size() > 0){
			for(AlertCoreDashBoardVO alertCoreDashBoardVO : alertCoreDashBoardVOs){
				if(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()) != null){
					alertCoreDashBoardVO.setSubTaskCount(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()));
				}
			}
		}
		return alertCoreDashBoardVOs;
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Error occured getTotalAlertByStatus() method of AlertManagementSystemService{}");
	}
	return null;
}
public void setAlertDtlsNew(List<AlertCoreDashBoardVO> alertCoreDashBoardVOs, List<Object[]> alertList,Long startDay,Long endDay,String name,String isMoreThanYrChkd,String isLagChkd){//abcd
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
				if(param.length > 26){
					alertCoreDashBoardVO.setProblem(commonMethodsUtilService.getStringValueForObject(param[26]));
					alertCoreDashBoardVO.setRelatedTo(commonMethodsUtilService.getStringValueForObject(param[27]));
				}
				statusId = commonMethodsUtilService.getLongValueForObject(param[3]);
				if(name.equalsIgnoreCase("alertStatus")){ 
				if(param[1] != null && param[2] != null){
					if(statusId == 5L || statusId == 8L || statusId == 9L || statusId == 11L || statusId == 13L){
						dist = dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10),commonMethodsUtilService.getStringValueForObject(param[2]).substring(0, 10));
					}else if(statusId == 1L || statusId == 2L || statusId == 3L){
						dist = dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10),td);
					}
					  alertCoreDashBoardVO.setInterval(dist);
					 if(isLagChkd != null && isLagChkd.equalsIgnoreCase("true")){
					if(startDay != null && startDay > 01 && endDay != null && endDay> 01){
						if(alertCoreDashBoardVO.getInterval()>=startDay && alertCoreDashBoardVO.getInterval()<=endDay){
							alertCoreDashBoardVOs.add(alertCoreDashBoardVO);
						}
					}else if(isMoreThanYrChkd != null && isMoreThanYrChkd.equalsIgnoreCase("true")){
						if(alertCoreDashBoardVO.getInterval() >= 365){
							alertCoreDashBoardVOs.add(alertCoreDashBoardVO);
						}
					}
				 }
				}
			  }else if(name.equalsIgnoreCase("subTaskStatus")){
				  if(param[1] != null && param[2] != null){
						if(statusId == 5L || statusId == 6L){
							dist = dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10),commonMethodsUtilService.getStringValueForObject(param[2]).substring(0, 10));
						}else if(statusId == 1L || statusId == 2L){
							dist = dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10),td);
						}
						alertCoreDashBoardVO.setInterval(dist);
						if(isLagChkd != null && isLagChkd.equalsIgnoreCase("true")){
						if(startDay != null && startDay > 01 && endDay != null && endDay> 01){
								if(alertCoreDashBoardVO.getInterval() >= startDay && alertCoreDashBoardVO.getInterval() <= endDay){
									alertCoreDashBoardVOs.add(alertCoreDashBoardVO);
								}
							}else if(isMoreThanYrChkd != null && isMoreThanYrChkd.equalsIgnoreCase("true")){
								if(alertCoreDashBoardVO.getInterval() >= 365){
									alertCoreDashBoardVOs.add(alertCoreDashBoardVO);
								}
							}
						}
					}
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
public List<AlertCoreDashBoardVO> getTotalAlertByOtherStatusNew(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,List<Long> statusIdList,Long userId,Long govtDeptScopeId,Long deptId,List<Long> calCntrIdList,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long startDay,Long endDay,Long scopeId,List<Long> locationList,List<Long> subTaskStatusIdList,String isMoreThanYrChkd,String isLagChkd,List<Long> filterSocialMediaIds,List<Long> filterCallCenterIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
	LOG.info("Entered in getTotalAlertByOtherStatus() method of AlertManagementSystemService{}");
	try{
		Date fromDate = null;
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
			fromDate = sdf.parse(fromDateStr);
			toDate = sdf.parse(toDateStr);
		}
		prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
		List<Long> levelValuesList = new ArrayList<Long>();    
		Long levelId = 0L;
		List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
		if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
			for(Object[] param : lvlValueAndLvlIdList){
				levelValuesList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
			}
		}
		List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
		List<Object[]> list = null;
		List<Long> alertIdSet = null;
		if(subTaskStatusIdList.isEmpty())
		  alertIdSet = alertAssignedOfficerNewDAO.getTotalAlertByAllAlertStatus(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusIdList,levelId,levelValuesList,govtDeptScopeId,deptId,calCntrIdList,impactLevelIdList,priorityIdList,alertSourceIdList,printMediaIdList,electronicMediaIdList,scopeId,locationList,filterSocialMediaIds,filterCallCenterIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
		if(alertIdSet != null && alertIdSet.size() > 0){
			 list = alertDAO.getAlertDtls(new HashSet<Long>(alertIdSet));
			//setAlertDtls(alertCoreDashBoardVOs, list);
			if(statusIdList != null && statusIdList.size()>0)
			 setAlertDtlsNew(alertCoreDashBoardVOs,list,startDay,endDay,"alertStatus",isMoreThanYrChkd,isLagChkd);
		}
		if(subTaskStatusIdList != null && subTaskStatusIdList.size()>0){
			alertIdSet =govtAlertSubTaskDAO.getTotalAlertBySubTaskStatus(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusIdList,levelId,levelValuesList,govtDeptScopeId,deptId,calCntrIdList,impactLevelIdList,priorityIdList,alertSourceIdList,printMediaIdList,electronicMediaIdList,scopeId,locationList,filterSocialMediaIds,filterCallCenterIdList,socialMediaTypeIds,subTaskStatusIdList,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
		List<Object[]> subTaskStatusList = govtAlertSubTaskDAO.getSubTaskStatusIds(new HashSet<Long>(alertIdSet));
		if(subTaskStatusList != null && subTaskStatusList.size()>0){
		    setAlertDtlsNew(alertCoreDashBoardVOs, subTaskStatusList,startDay,endDay,"subTaskStatus",isMoreThanYrChkd,isLagChkd);
		}
		}
		if(alertIdSet != null && alertIdSet.size() > 0 && statusIdList.isEmpty() && subTaskStatusIdList.isEmpty()){
			setAlertDtls(alertCoreDashBoardVOs, list);
		}
		//set Subtask into alert logic 
		//get subtask count.
		List<Object[]> subtaskCountList = null;
		if(alertIdSet != null && alertIdSet.size() > 0){
			subtaskCountList = govtAlertSubTaskDAO.getSubTaskCount(alertIdSet);
		}
		//create a map from alertId and subtask count.
		Map<Long,Long> alertIdAndSubTaskCountMap = new HashMap<Long,Long>();
		if(subtaskCountList != null && subtaskCountList.size() > 0){
			for(Object[] param : subtaskCountList){
				alertIdAndSubTaskCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
			}
		}
		if(alertCoreDashBoardVOs != null && alertCoreDashBoardVOs.size() > 0){
			for(AlertCoreDashBoardVO alertCoreDashBoardVO : alertCoreDashBoardVOs){
				if(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()) != null){
					alertCoreDashBoardVO.setSubTaskCount(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()));
				}
			}
		}
		return alertCoreDashBoardVOs;
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Error occured getTotalAlertByOtherStatus() method of AlertManagementSystemService{}");
	}
	return null;  
}
/*
 * Hymavathi
 * Filter View
 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getSubOrdinateFilterAlertsOverview()
 */
public List<DistrictOfficeViewAlertVO> getSubOrdinateFilterAlertsOverview(Long userId,String fromDateStr,String toDateStr , List<Long> govtScopeIds,List<Long> locationValues,
		List<Long> desigIds,Long priorityId,List<Long> statusIds ,List<Long> deptIds, Long lagStartCnt, 
		Long lagEndCnt,String alertType,String isMoreThanYrChkd,String isLagChkd,List<Long> paperIdList,List<Long> chanelIdList,List<Long> calCntrIdList,List<Long> childLevelVals,Long childLevelId,List<Long> socialMediaTypeIds,
		List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
	
	List<DistrictOfficeViewAlertVO> returnList = new ArrayList<DistrictOfficeViewAlertVO>();
	
	try{
		
		
		List<Long> levelValues = new ArrayList<Long>();    
		Long levelId = 0L;
		List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
		if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
			for(Object[] param : lvlValueAndLvlIdList){
				levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
			}
		}
		
		prepareRequiredParameter(paperIdList,chanelIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
		
		if(govtScopeIds != null && govtScopeIds.size() ==0){
	        List<Object[]> rtrnObjList = govtDepartmentScopeLevelDAO.getChildGovtScopesLevelByParentScopeLevel(levelId, deptIds.get(0).longValue());//levelId means Access Level 
	        if(rtrnObjList != null && rtrnObjList.size() > 0){
	          for(Object[] param:rtrnObjList){
	        	  govtScopeIds.add(commonMethodsUtilService.getLongValueForObject(param[2]));
	          }
	        }
		 }
        
        Date fromDate = null;
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
			fromDate = sdf.parse(fromDateStr);
			toDate = sdf.parse(toDateStr);
		}
		
		Map<Long,DistrictOfficeViewAlertVO> scopWiseMap = new HashMap<Long,DistrictOfficeViewAlertVO>();
		if(alertType != null && alertType.equalsIgnoreCase("alert")){
			if(govtScopeIds != null && govtScopeIds.size() >0){
				for(Long parentGovtDepartmentScopeId : govtScopeIds){
					List<Long> scopeIds = new ArrayList<Long>();
					scopeIds.add(parentGovtDepartmentScopeId);
				List<Object[]> list = alertAssignedOfficerNewDAO.getSubOrdinateFilterAlertsDetails(userId,fromDate,toDate,scopeIds,locationValues,levelId,levelValues,desigIds,priorityId,statusIds,paperIdList,chanelIdList,calCntrIdList,childLevelVals,childLevelId,deptIds,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			 //List<Object[]> list = alertAssignedOfficerNewDAO.getSubOrdinateFilterAlertsDetailsForUser(fromDate,toDate,null,null,null,levelId,levelValues,deptIds,
    	     	//	 parentGovtDepartmentScopeId,null, null, locationValues, desigIds);
				setFilterDetails(list,scopWiseMap,alertType,lagStartCnt,lagEndCnt,statusIds,isMoreThanYrChkd,isLagChkd,govtScopeIds);
				}
			}
		}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
			if(govtScopeIds != null && govtScopeIds.size() >0){
				for(Long parentGovtDepartmentScopeId : govtScopeIds){
					List<Long> scopeIds = new ArrayList<Long>();
					scopeIds.add(parentGovtDepartmentScopeId);
				List<Object[]> totalTasks = govtAlertSubTaskDAO.getSubOrdinateFilterSubTasksDetails(userId,fromDate,toDate,scopeIds,locationValues,levelId,levelValues,desigIds,priorityId,statusIds,paperIdList,chanelIdList,calCntrIdList,childLevelVals,childLevelId,deptIds,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
				setFilterDetails(totalTasks,scopWiseMap,alertType,lagStartCnt,lagEndCnt,statusIds,isMoreThanYrChkd,isLagChkd,govtScopeIds);
				}
			}
		}
		
		if(commonMethodsUtilService.isMapValid(scopWiseMap)){
			for(Map.Entry<Long, DistrictOfficeViewAlertVO> entry : scopWiseMap.entrySet()){
				DistrictOfficeViewAlertVO vo = entry.getValue();
				//returnList.add(vo);
				if(isLagChkd != null && isLagChkd.equalsIgnoreCase("true")){
					if(lagStartCnt != null && lagStartCnt >= 0l && lagEndCnt != null && lagEndCnt>= 0l){
						if(vo.getTaskCnt() >= lagStartCnt && vo.getTaskCnt() <= lagEndCnt){
							returnList.add(vo);
						}
					}else if(isMoreThanYrChkd != null && isMoreThanYrChkd.equalsIgnoreCase("true")){
						if(vo.getTaskCnt() >= 365){
							returnList.add(vo);
						}
					}
				}else{
					returnList.add(vo);
				}
				if(commonMethodsUtilService.isListOrSetValid(vo.getList1())){
					for(DistrictOfficeViewAlertVO locVO :vo.getList1()){
						if(commonMethodsUtilService.isListOrSetValid(locVO.getList2())){
							for(DistrictOfficeViewAlertVO desigVO :locVO.getList2()){
								if(commonMethodsUtilService.isListOrSetValid(desigVO.getSubList1())){
									for(DistrictOfficeViewAlertVO statusVo :desigVO.getSubList1()){
										if(statusVo.getCount() != null && statusVo.getCount().longValue() >0l){
											desigVO.setCount(desigVO.getCount()+statusVo.getCount());
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
		LOG.error("Error occured getSubOrdinateAlertsOverview() method of AlertManagementSystemService",e);
	}
	return returnList;
}

public void setFilterDetails(List<Object[]> list,Map<Long,DistrictOfficeViewAlertVO> scopWiseMap,String alertType,Long lagStartCnt,Long lagEndCnt,
		List<Long> statusIds,String isMoreThanYrChkd,String isLagChkd,List<Long> govtScopeIds){
	
	SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date today = dateUtilService.getCurrentDateAndTime();
    String td = myFormat.format(today);
	try{
		
		List<Object[]> statusList = null;
			if(alertType != null && alertType.equalsIgnoreCase("alert")){
				statusList = alertStatusDAO.getAlertStatusDtlsBasidOnAlertIds(statusIds);
			}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
				statusList = alertSubTaskStatusDAO.getAlertStatusDtlsBasidOnAlertIds(statusIds);
			}
		
		if(list != null && list.size() >0){
			for(Object[] obj : list ){
				DistrictOfficeViewAlertVO scopeVO = scopWiseMap.get(commonMethodsUtilService.getLongValueForObject(obj[8]));
					if(scopeVO == null){
						scopeVO = new DistrictOfficeViewAlertVO();
						scopWiseMap.put(commonMethodsUtilService.getLongValueForObject(obj[8]), scopeVO);
					}
					scopeVO.setId(commonMethodsUtilService.getLongValueForObject(obj[8]));
					scopeVO.setName(commonMethodsUtilService.getStringValueForObject(obj[9]));
				
				DistrictOfficeViewAlertVO locationVO = getMatchVOForSubOrdinate(scopeVO.getList1(),commonMethodsUtilService.getLongValueForObject(obj[6]));
					if(locationVO == null){
						locationVO = new DistrictOfficeViewAlertVO();
						locationVO.setId(commonMethodsUtilService.getLongValueForObject(obj[6]));
						locationVO.setName(commonMethodsUtilService.getStringValueForObject(obj[7]));
							/*if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_STATE_LEVEL_ID){
									
							}else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_ZONE_LEVEL_ID){
			        	      sb.append(" , Z.govtDepartmentWorkLocationId,Z.locationName,Z.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName ");
							}else if(govtScopeIds != null && govtScopeIds.get(0).longValue() ==IConstants.GOVT_DEPARTMENT_REGION_LEVEL_ID){
			        	      sb.append(" , R.govtDepartmentWorkLocationId,R.locationName,R.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName ");
							} else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_CIRCLE_LEVEL_ID){
			        	      sb.append(" , C.govtDepartmentWorkLocationId,C.locationName,C.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName ");
							} else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_DISTRICT_LEVEL_ID){
			        	      sb.append(" , D.govtDepartmentWorkLocationId,D.locationName, "+
			        	      		"D.govtDepartmentScope.govtDepartmentScopeId ,  GDS.levelName ");
							} else*/ if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_DIVISION_LEVEL_ID){
								locationVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(obj[10]));
								locationVO.setDistName(commonMethodsUtilService.getStringValueForObject(obj[11]));
							}else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID){
								locationVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(obj[10]));
								locationVO.setDistName(commonMethodsUtilService.getStringValueForObject(obj[11]));
								locationVO.setDivId(commonMethodsUtilService.getLongValueForObject(obj[12]));
								locationVO.setDivName(commonMethodsUtilService.getStringValueForObject(obj[13]));
							}else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_MANDAL_LEVEL_ID){
								locationVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(obj[10]));
								locationVO.setDistName(commonMethodsUtilService.getStringValueForObject(obj[11]));
								locationVO.setDivId(commonMethodsUtilService.getLongValueForObject(obj[12]));
								locationVO.setDivName(commonMethodsUtilService.getStringValueForObject(obj[13]));
								locationVO.setSubDivId(commonMethodsUtilService.getLongValueForObject(obj[14]));
								locationVO.setSubDivName(commonMethodsUtilService.getStringValueForObject(obj[15]));
							}else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID){
								locationVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(obj[10]));
								locationVO.setDistName(commonMethodsUtilService.getStringValueForObject(obj[11]));
								locationVO.setDivId(commonMethodsUtilService.getLongValueForObject(obj[12]));
								locationVO.setDivName(commonMethodsUtilService.getStringValueForObject(obj[13]));
								locationVO.setSubDivId(commonMethodsUtilService.getLongValueForObject(obj[14]));
								locationVO.setSubDivName(commonMethodsUtilService.getStringValueForObject(obj[15]));
								locationVO.setMandalId(commonMethodsUtilService.getLongValueForObject(obj[16]));
								locationVO.setMandalName(commonMethodsUtilService.getStringValueForObject(obj[17]));
							}else if(govtScopeIds != null && govtScopeIds.get(0).longValue() == IConstants.GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID){
								locationVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(obj[10]));
								locationVO.setDistName(commonMethodsUtilService.getStringValueForObject(obj[11]));
								locationVO.setDivId(commonMethodsUtilService.getLongValueForObject(obj[12]));
								locationVO.setDivName(commonMethodsUtilService.getStringValueForObject(obj[13]));
								locationVO.setSubDivId(commonMethodsUtilService.getLongValueForObject(obj[14]));
								locationVO.setSubDivName(commonMethodsUtilService.getStringValueForObject(obj[15]));
								locationVO.setMandalId(commonMethodsUtilService.getLongValueForObject(obj[16]));
								locationVO.setMandalName(commonMethodsUtilService.getStringValueForObject(obj[17]));
								locationVO.setLEBId(commonMethodsUtilService.getLongValueForObject(obj[18]));
								locationVO.setLEBName(commonMethodsUtilService.getStringValueForObject(obj[19]));
							}
						scopeVO.getList1().add(locationVO);
					}
				
				
				DistrictOfficeViewAlertVO desigVo = getMatchVOForSubOrdinate(locationVO.getList2(),commonMethodsUtilService.getLongValueForObject(obj[0]));
					if(desigVo == null){
						desigVo = new DistrictOfficeViewAlertVO();
						desigVo.setSubList1(setStatusDetails(statusList));
						locationVO.getList2().add(desigVo);
					}
				desigVo.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
				desigVo.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
				
				Long statusId=commonMethodsUtilService.getLongValueForObject(obj[3]);
				DistrictOfficeViewAlertVO statusVO = getMatchVOForSubOrdinate(desigVo.getSubList1(),commonMethodsUtilService.getLongValueForObject(obj[3]));
				List<Long> lagStatusIds = new ArrayList<Long>();
					if(alertType != null && alertType.equalsIgnoreCase("alert")){
						lagStatusIds.add(4l);
						lagStatusIds.add(5l);
						lagStatusIds.add(6l);
						lagStatusIds.add(7l);
						lagStatusIds.add(10l);
						lagStatusIds.add(12l);
					}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
						lagStatusIds.add(4l);
						lagStatusIds.add(5l);
						lagStatusIds.add(6l);
						lagStatusIds.add(7l);
						lagStatusIds.add(8l);
					}
				if(statusVO != null){
					statusVO.setCount(statusVO.getCount()+1l);
					
					Long dist = 0l;
					if(isLagChkd != null && isLagChkd.equalsIgnoreCase("true")){
					if(lagStartCnt != null && lagStartCnt >= 0l && lagEndCnt != null && lagEndCnt>= 0l){
						if(commonMethodsUtilService.getStringValueForObject(obj[4]) != null && commonMethodsUtilService.getStringValueForObject(obj[5]) != null){
							if(lagStatusIds.contains(statusId) ){
								dist = dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(obj[4]).substring(0, 10),commonMethodsUtilService.getStringValueForObject(obj[5]).substring(0, 10));
							}else{
								dist = dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(commonMethodsUtilService.getStringValueForObject(obj[4])).substring(0, 10), td);
							}  
							
						}
						scopeVO.setTaskCnt(dist);
					}
				  }
				}
			}
		}
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Error occured setScopeDetails() method of AlertManagementSystemService",e);
	}
}
 
public List<DistrictOfficeViewAlertVO> setStatusDetails(List<Object[]> listObj){
	List<DistrictOfficeViewAlertVO> finalList = new ArrayList<DistrictOfficeViewAlertVO>();
	try{
		
		if(listObj !=null && listObj.size()>0){
			for (Object[] objects : listObj) {
				DistrictOfficeViewAlertVO vo = new DistrictOfficeViewAlertVO();
				vo.setId((Long)objects[0]);
				vo.setName(objects[1].toString());
				finalList.add(vo);
			}
		}
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Error occured setStatusDetails() method of AlertManagementSystemService",e);
	}
	return finalList;
}
/*
 * Santosh (non-Javadoc)
 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getAlertSourceWiseAlert(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.util.List, java.lang.Long, java.util.List)
 */
public List<AlertVO> getAlertSourceWiseAlert(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,String userType,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
	LOG.info("Entered in getAlertSourceWiseAlert() method of AlertManagementSystemService{}");
	try{
		Date fromDate = null;
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
			fromDate = sdf.parse(fromDateStr);
			toDate = sdf.parse(toDateStr);
		}
		List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
		
		prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
		
		if(deptIdList != null && deptIdList.size() == 0){
			deptIdList.add(0L);  
		}
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
	           Map<Long,AlertVO> catgoryMap = new LinkedHashMap<Long, AlertVO>(0);
			   Map<Long,String> categoryNameAndIdMap = new HashMap<Long, String>(0);
			   
		    //Getting Pending Alert By Alert Source		
			 List<Object[]> totalList = new ArrayList<Object[]>();
			if(userType != null && userType.equalsIgnoreCase("admin")){
					List<Object[]> alertCountList = alertDAO.getTotalGovtPendingStatusAlertCnt(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,"alertSource",calCntrIdList,null,null,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
					if(alertCountList != null && alertCountList.size() > 0){
						totalList.addAll(alertCountList);
					}	
			}
		 
		List<Object[]> rtrnObjLst = alertAssignedOfficerNewDAO.getAlertCntByRequiredType(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,levelId,levelValues,"alertSource",alertStatusIds,null,calCntrIdList,socialMediaTypeIds,alertSeverityIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);

		if(rtrnObjLst != null && rtrnObjLst.size() > 0){
				totalList.addAll(rtrnObjLst);
		}
		
		setAlertCategoryWiseAlert(totalList,catgoryMap,categoryNameAndIdMap);
		
		Long totalAlertCnt = 0l;
		 if(catgoryMap != null && catgoryMap.size() >0){
			 for(Entry<Long,AlertVO> categoryEntry:catgoryMap.entrySet()){
				 AlertVO  categoryVO = new AlertVO();
				 categoryVO.setId(categoryEntry.getKey());
				 categoryVO.setName(categoryNameAndIdMap.get(categoryEntry.getKey()));
				 if(categoryEntry.getValue().getSubList2() != null && categoryEntry.getValue().getSubList2().size() > 0){
					 for(AlertVO statusVO:categoryEntry.getValue().getSubList2()){
						 categoryVO.getSubList2().add(statusVO);
						 categoryVO.setAlertCnt(categoryVO.getAlertCnt()+statusVO.getAlertCnt());
						 totalAlertCnt = totalAlertCnt+statusVO.getAlertCnt();
					 }
				 }
				 finalAlertVOs.add(categoryVO);
			 }
		 }
		 //Calculating Percentage
		 if(finalAlertVOs != null && finalAlertVOs.size() > 0){
			 for(AlertVO categogyVO:finalAlertVOs){
				 if(categogyVO.getSubList2() != null && categogyVO.getSubList2().size()>0){
					 for(AlertVO statusVO:categogyVO.getSubList2()){
						 statusVO.setPercentage(calculatePercantage(statusVO.getAlertCnt(), categogyVO.getAlertCnt()));
					 }
				 }
				 categogyVO.setPercentage(calculatePercantage(categogyVO.getAlertCnt(), totalAlertCnt));
			 }
		 }
		//Soring List
		if(finalAlertVOs != null && finalAlertVOs.size() > 0){
			Collections.sort(finalAlertVOs, new Comparator<AlertVO>() {
				public int compare(AlertVO id1, AlertVO id2) {
					return id1.getId().compareTo(id2.getId());
				}
			});
		}
		return finalAlertVOs; 
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Error occured getAlertSourceWiseAlert() method of AlertManagementSystemService{}");
	}
	return null;
}
public void setAlertCategoryWiseAlert(List<Object[]> objList,Map<Long,AlertVO> categoryMap,Map<Long,String> categoryIdAndNameMap){
	try{
		if(objList != null && objList.size() > 0){
			for(Object[] param:objList){
				 AlertVO categoryVO = categoryMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));//CategoryId
				 if(categoryVO == null){
					 categoryVO = new AlertVO();
					 categoryVO.setSubList2(getAlertSttusList(objList));
					 categoryIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					 categoryMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), categoryVO);
				 }
				 Long alertStatusId = commonMethodsUtilService.getLongValueForObject(param[3]);
				 AlertVO statusVO = getmatchedVo(categoryVO.getSubList2(),alertStatusId);
				 if(statusVO != null){
					 statusVO.setAlertCnt(statusVO.getAlertCnt()+commonMethodsUtilService.getLongValueForObject(param[6]));//AlertCnt
				 }
			}
		}
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Error occured setAlertCategoryWiseAlert() method of AlertManagementSystemService{}");
	}
}
public List<AlertVO> getAlertSttusList(List<Object[]> objList){
	List<AlertVO> statuList = new ArrayList<AlertVO>();
	Map<Long,AlertVO> statuMap = new LinkedHashMap<Long, AlertVO>();
	try{
		if(objList != null && objList.size() > 0){
			for(Object[] param:objList){
				 AlertVO statusVO = new AlertVO();
				 statusVO.setId(commonMethodsUtilService.getLongValueForObject(param[3]));//StatusId
				 statusVO.setName(commonMethodsUtilService.getStringValueForObject(param[4]));//StatusName
				 statusVO.setColor(commonMethodsUtilService.getStringValueForObject(param[5])); //color
				 statuMap.put(statusVO.getId(), statusVO);
			}
		}
		if(statuMap != null && statuMap.size() > 0){
			statuList.addAll(statuMap.values());
		}
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Error occured getAlertSttusList() method of AlertManagementSystemService{}");
	}
	return statuList;
}
/*
 * Santosh (non-Javadoc)
 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getAlertDtlsByAlertSource(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.util.List, java.lang.Long, java.util.List, java.lang.Long, java.lang.String, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List)
 */
public List<AlertCoreDashBoardVO> getAlertDtlsByAlertSource(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,Long alertCategoryId,String userType,List<Long> alertStatusIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
	LOG.info("Entered in getAlertDtlsByAlertSource() method of AlertManagementSystemService{}");
	try{
		Date fromDate = null;
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
			fromDate = sdf.parse(fromDateStr);
			toDate = sdf.parse(toDateStr);
		}
		List<AlertCoreDashBoardVO> finalAlertVOs = new ArrayList<AlertCoreDashBoardVO>();
		
		prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
		
		if(deptIdList != null && deptIdList.size() == 0){
			deptIdList.add(0L);  
		}
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
		
		   //Pending Alert Ids
		   List<Long> alertIds = new ArrayList<Long>();
		   List<Object[]> alertDtlsLst = new ArrayList<Object[]>();
		   List<Long> pendingAlertIds = new ArrayList<Long>();
		   if(userType != null && userType.equalsIgnoreCase("admin")){
			   if(alertStatusIds != null && alertStatusIds.size() == 0){
				   List<Long> pendingAlertIdsList = alertDAO.getPendingAlertCntByAlertCategory(fromDate, toDate, stateId, printIdList, electronicIdList, deptIdList, alertCategoryId, calCntrIdList,null,null,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
					if(pendingAlertIdsList != null && pendingAlertIdsList.size() > 0){
						pendingAlertIds.addAll(pendingAlertIdsList);
					}
			   }
			   if(alertStatusIds != null && alertStatusIds.size() > 0 && alertStatusIds.get(0).longValue() ==1l){
				   List<Long> pendingAlertIdsList = alertDAO.getPendingAlertCntByAlertCategory(fromDate, toDate, stateId, printIdList, electronicIdList, deptIdList, alertCategoryId, calCntrIdList,null,null,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
					if(pendingAlertIdsList != null && pendingAlertIdsList.size() > 0){
						pendingAlertIds.addAll(pendingAlertIdsList);
					}
			   }
			   if(pendingAlertIds != null && pendingAlertIds.size() > 0){
				   alertDtlsLst = alertDAO.getPendingAlertDtls(new HashSet<Long>(pendingAlertIds));   
			   }
			    
		 }
		
		//getAlertCntByAlertCategory
		List<Long> alertAssignedIds = alertAssignedOfficerNewDAO.getAlertCntByAlertCategory(fromDate, toDate, stateId, printIdList, electronicIdList, deptIdList, levelId, levelValues, calCntrIdList, alertCategoryId,alertStatusIds,socialMediaTypeIds,alertSeverityIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
		if(alertAssignedIds != null && alertAssignedIds.size() > 0){
			alertIds.addAll(alertAssignedIds);
		}
		if(alertIds != null && alertIds.size() > 0){
			List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIds));
			alertDtlsLst.addAll(list);//Adding other than pending alert ids
		}
		alertIds.addAll(pendingAlertIds);
		setAlertDtls(finalAlertVOs, alertDtlsLst); 
		setSubListCount(finalAlertVOs, alertIds);
		return finalAlertVOs; 
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Error occured getAlertSourceWiseAlert() method of AlertManagementSystemService{}");
	}
	return null;
}
/*
 * Swadhin K Lenka
 * (non-Javadoc)
 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getBellowDistrictOfficerAlertsDtls(java.lang.String, java.lang.String, java.util.List, java.util.List, java.util.List, java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long)
 */
public List<AlertCoreDashBoardVO> getBellowDistrictOfficerAlertsDtls(String fromDateStr,String toDateStr,List<Long> printIdList,List<Long> electronicIdList,List<Long> calCntrIdList,String task,Long statusId,Long desigDeptOfficerId,Long officerId,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
	try{
		Date fromDate = null;
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
			fromDate = sdf.parse(fromDateStr);
			toDate = sdf.parse(toDateStr);
		}
		List<AlertCoreDashBoardVO> finalAlertVOs = new ArrayList<AlertCoreDashBoardVO>();
		
		prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
		
		List<Long> alertIdList = null;
		if(task != null && !task.trim().isEmpty() && task.trim().length() > 0 && task.trim().equalsIgnoreCase("task")){
			alertIdList = alertAssignedOfficerNewDAO.getBellowDistrictOfficerAlertsDtls(fromDate,toDate,printIdList,electronicIdList,calCntrIdList,statusId,desigDeptOfficerId,officerId,socialMediaTypeIds,alertSeverityIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
		}else{
			alertIdList = govtAlertSubTaskDAO.getBellowDistrictOfficerAlertsDtls(fromDate,toDate,printIdList,electronicIdList,calCntrIdList,statusId,desigDeptOfficerId,officerId,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
		} 
		if(alertIdList != null && alertIdList.size() > 0){
			List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIdList));
			setAlertDtls(finalAlertVOs, list); 
		}
		//set Subtask into alert logic 
		//get subtask count.
		List<Object[]> subtaskCountList = null;
		if(alertIdList != null && alertIdList.size() > 0){
			subtaskCountList = govtAlertSubTaskDAO.getSubTaskCount(alertIdList);
		}
		//create a map from alertId and subtask count.
		Map<Long,Long> alertIdAndSubTaskCountMap = new HashMap<Long,Long>();
		if(subtaskCountList != null && subtaskCountList.size() > 0){
			for(Object[] param : subtaskCountList){
				alertIdAndSubTaskCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
			}
		}
		if(finalAlertVOs != null && finalAlertVOs.size() > 0){
			for(AlertCoreDashBoardVO alertCoreDashBoardVO : finalAlertVOs){
				if(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()) != null){
					alertCoreDashBoardVO.setSubTaskCount(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()));
				}
			}
		}
		return finalAlertVOs;
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Error occured getBellowDistrictOfficerAlertsDtls() method of AlertManagementSystemService{}");
	}
	return null;
}
/*
 * Swadhin K Lenka
 * (non-Javadoc)
 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#viewAlertHistoryNew(java.lang.Long)
 */
public List<List<AlertTrackingVO>> viewAlertHistoryNew(Long alertId, String task){
	try{
		AlertTrackingVO alertTrackingVO = null;
		AlertTrackingVO userDetails = null;
		List<AlertTrackingVO> innerList = null;
		List<List<AlertTrackingVO>> finalList = new ArrayList<List<AlertTrackingVO>>();
		Map<Long,AlertTrackingVO> userMap = new HashMap<Long, AlertTrackingVO>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
		List<Object[]> trackingList = null;
		if(task != null && !task.trim().isEmpty() && task.trim().length() > 0 && task.trim().equalsIgnoreCase("task")){
			trackingList = alertAssignedOfficerTrackingNewDAO.getAlertTrackingDtls(alertId);
		}else{
			trackingList = govtOfficerSubTaskTrackingDAO.getSubTaskAlertTrackingDtls(alertId);
		}
		
		//collect userid
		Set<Long> userIdList = new HashSet<Long>(); 
		if(trackingList != null && trackingList.size() > 0){
			for(Object[] param : trackingList){
				userIdList.add(commonMethodsUtilService.getLongValueForObject(param[13]));
			}
		}
		List<Object[]> userIdAndNameList =null;
		if(userIdList != null && userIdList.size()>0){
			userIdAndNameList = userDAO.getuserIdAndNameList(userIdList);
		}
		//create a map for userId and name
		Map<Long,String> idAndNameMap = new HashMap<Long,String>();
		if(userIdAndNameList != null && userIdAndNameList.size() > 0){
			for(Object[] param : userIdAndNameList){
				idAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
			}
		}
		//check user is admin or not
		Map<Long,String> userIdAndStatusMap = new HashMap<Long,String>();
		
		if(trackingList != null && trackingList.size() > 0){
			for(Object[] param : trackingList){
				Long loginUserId = commonMethodsUtilService.getLongValueForObject(param[13]);
				Long deptId = commonMethodsUtilService.getLongValueForObject(param[14]);
				alertTrackingVO = new AlertTrackingVO();
				AlertTrackingVO vo = new AlertTrackingVO();
				List<Object[]> userDtls = govtDepartmentDesignationOfficerDetailsDAO.getLocationInfoForUser(loginUserId,deptId);
				List<String> deptList = govtDepartmentDesignationOfficerDetailsDAO.getAssignedDeptList(loginUserId);
				if(userDtls != null && userDtls.size() > 0){
					userIdAndStatusMap.put(loginUserId, "officer");
					String deptName = "";
					/*if(deptList != null && deptList.size() > 0){
						if(deptList.size() > 1){
							deptName = "N/A";  
						}else{
							deptName = deptList.get(0).trim();
						}
					}*/
					
					if(deptList != null && deptList.size() > 0){
						StringBuilder strBuild = new StringBuilder();
						if(deptList.size() > 3){
							for(int i=1 ; i <= 3 ; i++){
								strBuild.append(deptList.get(i));
								strBuild.append(",");
							}
							strBuild.append("...");
						}else{
							for(String str : deptList){
								strBuild.append(str);
								strBuild.append(",");
							}
						}
						deptName = strBuild.toString();
					}
					
					String designationStr = commonMethodsUtilService.getStringValueForObject(userDtls.get(0)[0]);
					String officerName = idAndNameMap.get(commonMethodsUtilService.getLongValueForObject(param[13]));
					String mobileNo = commonMethodsUtilService.getStringValueForObject(userDtls.get(0)[2]);
					String location = commonMethodsUtilService.getStringValueForObject(userDtls.get(0)[3]);
					vo.setDeptName(deptName);
					vo.setDesignation(designationStr);
					vo.setUserName(officerName);
					vo.setMobileNO(mobileNo);
					vo.setLocation(location);
					userMap.put(loginUserId, vo);
				}else{
					String officerName = idAndNameMap.get(commonMethodsUtilService.getLongValueForObject(param[13]));
					userIdAndStatusMap.put(loginUserId, "admin");
					vo.setUserName(officerName);
					vo.setDesignation("N/A");
					userMap.put(loginUserId, vo);
				}
				
			}
		}
		
		//create date wise map
		Map<String,List<Object[]>> dateAndTrackingDtlsMap = new LinkedHashMap<String,List<Object[]>>();
		List<Object[]> arrayList = null;
		
		if(trackingList != null && trackingList.size() > 0){
			for(Object[] param : trackingList){
				arrayList = dateAndTrackingDtlsMap.get(commonMethodsUtilService.getStringValueForObject(param[12]).trim().substring(0, 10));
				if(arrayList == null){
					arrayList = new ArrayList<Object[]>();
					arrayList.add(param);
					dateAndTrackingDtlsMap.put(commonMethodsUtilService.getStringValueForObject(param[12]).trim().substring(0, 10),arrayList);
				}else{
					arrayList.add(param);
				}
			}
			
		}
		
		//create list of vo for ui.
		if(dateAndTrackingDtlsMap != null && dateAndTrackingDtlsMap.size() > 0){
			for(Entry<String,List<Object[]>> entry : dateAndTrackingDtlsMap.entrySet()){
				innerList = new ArrayList<AlertTrackingVO>();
				if(entry.getValue() != null && entry.getValue().size() > 0){
					for(Object[] param : entry.getValue()){
						alertTrackingVO = new AlertTrackingVO();
						alertTrackingVO.setActionType(commonMethodsUtilService.getStringValueForObject(param[2]));
						alertTrackingVO.setTrackingDate(commonMethodsUtilService.getStringValueForObject(param[12]).trim().substring(0, 10));
						alertTrackingVO.setTrackingTime(sdf1.format(sdf.parse(commonMethodsUtilService.getStringValueForObject(param[12]).trim().substring(11, 19))));
						alertTrackingVO.setComment(commonMethodsUtilService.getStringValueForObject(param[4]));//  
						alertTrackingVO.setDocument(commonMethodsUtilService.getStringValueForObject(param[6]));
						alertTrackingVO.setDueDate(commonMethodsUtilService.getStringValueForObject(param[7]));
						alertTrackingVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[9]));
						alertTrackingVO.setRejinderStatus(commonMethodsUtilService.getStringValueForObject(param[20]));
						if(alertTrackingVO.getStatus().trim().equalsIgnoreCase("Proposal")){
							List<Object[]> statusList = govtProposalPropertyCategoryDAO.getProposalStatusFrAlert(alertId);
							if(statusList != null && statusList.size() > 0){
								for (Object[] objects : statusList) {
									alertTrackingVO.setProposalStatus(commonMethodsUtilService.getStringValueForObject(objects[0]));
									alertTrackingVO.setCategory(commonMethodsUtilService.getStringValueForObject(objects[1]));
									alertTrackingVO.setCategoryId(commonMethodsUtilService.getLongValueForObject(objects[2]));
									alertTrackingVO.setAmount(commonMethodsUtilService.getStringValueForObject(objects[3]));
									alertTrackingVO.setApprovedAmount(commonMethodsUtilService.getStringValueForObject(objects[4]));
								}
							}
						}
						alertTrackingVO.setSeverty(commonMethodsUtilService.getStringValueForObject(param[11]));
						if(task != null && !task.trim().isEmpty() && task.trim().length() > 0 && task.trim().equalsIgnoreCase("task")){
							alertTrackingVO.setAlertFeedbackStatusId(commonMethodsUtilService.getLongValueForObject(param[16]));
							alertTrackingVO.setAlertFeedbackStatus(commonMethodsUtilService.getStringValueForObject(param[17]));
							alertTrackingVO.setAlertCallerId(commonMethodsUtilService.getLongValueForObject(param[18]));
							alertTrackingVO.setAlertCallerName(commonMethodsUtilService.getStringValueForObject(param[19]));
						}
						alertTrackingVO.setPosition(userIdAndStatusMap.get(commonMethodsUtilService.getLongValueForObject(param[13])));
						
						if(userMap != null && userMap.get(commonMethodsUtilService.getLongValueForObject(param[13])) != null){
							userDetails = userMap.get(commonMethodsUtilService.getLongValueForObject(param[13]));
							alertTrackingVO.setUpdatedUserName(userDetails.getUserName());
							alertTrackingVO.setDesignation(userDetails.getDesignation());
							alertTrackingVO.setLocation(userDetails.getLocation());
							if(userDetails.getDeptName() != null && !userDetails.getDeptName().trim().isEmpty() && userDetails.getDeptName().trim().length() >0){
								alertTrackingVO.setDeptName(userDetails.getDeptName());
							}else{
								alertTrackingVO.setDeptName(commonMethodsUtilService.getStringValueForObject(param[15]));
							}
							
						}
						innerList.add(alertTrackingVO);    
					}  
				}
				finalList.add(innerList);
			}
		}
		if(finalList != null && finalList.size() > 0){
			Collections.reverse(finalList);    
		}
		if(finalList != null && finalList.size() > 0){
			for(List<AlertTrackingVO> inerList : finalList){
				Collections.reverse(inerList);
			}
		}
		
		return finalList;
	} catch (Exception e) {
		LOG.error(" Exception Occured in viewAlertHistory() method, Exception - ",e);
	}
	return null;
}
/*
 * Swadhin K Lenka
 * (non-Javadoc)
 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getBellowDistrictOfficerAlertsCountView(java.lang.Long, java.lang.String, java.lang.String, java.util.List, java.util.List, java.util.List, java.lang.String, java.lang.String)
 */
public List<DistrictOfficeViewAlertVO> getBellowDistrictOfficerAlertsCountView(Long userId,String startDate,String endDate, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList, String task,String sortingType,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
	
	DistrictOfficeViewAlertVO returnVO = new DistrictOfficeViewAlertVO();
	try{
		Date fromDate = null;
	    Date toDate = null;
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    if(startDate != null && startDate.trim().length() > 0 && endDate != null && endDate.trim().length() > 0){
	    	fromDate = sdf.parse(startDate);
	        toDate = sdf.parse(endDate);
	    }
	  
	    prepareRequiredParameter(printIdsList,electronicIdsList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
	    
	    List<Object[]> list1 = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDepaDesigIdForLoginOfficer(userId,66L);//for COLLECTORATE
	    Long parentGovtDeptDesigId = 0L;
	    Long govtDepartmentScopeId = 0L;
	    Long levelValue = 0L;
	    if(list1 != null && list1.size() > 0){
	    	parentGovtDeptDesigId = commonMethodsUtilService.getLongValueForObject(list1.get(0)[1]);
	    	govtDepartmentScopeId = commonMethodsUtilService.getLongValueForObject(list1.get(0)[2]);
	    	levelValue = commonMethodsUtilService.getLongValueForObject(list1.get(0)[3]);
	    }
	    
	    List<Long> govtDepartmentDesignationIds = govtDepartmentDesignationHierarchyDAO.getGovtDeptSubDesigIds(parentGovtDeptDesigId);
	    List<Object[]> list3 = null;
	    if(govtDepartmentDesignationIds != null && govtDepartmentDesignationIds.size() > 0){
	    	list3 = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDepaDesigOfficerDtls(govtDepartmentDesignationIds, govtDepartmentScopeId, levelValue);
	    }
	    
	    Map<Long,String> govtDeptDesigOfficerIdAndNameMap = new HashMap<Long,String>();
	    Map<Long,Long> govtDeptDesigOfficerIdAndOfficerIdMap = new HashMap<Long,Long>();
	    List<Long> govtDeptDesigOfficerIds = new ArrayList<Long>();
	    if(list3 != null && list3.size() > 0){
	    	for(Object[] param : list3){
	    		govtDeptDesigOfficerIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[2]));
	    		govtDeptDesigOfficerIdAndOfficerIdMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
	    		govtDeptDesigOfficerIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
	    	}
	    }
	    List<Object[]> list4 = null;
	    if(govtDeptDesigOfficerIds != null && govtDeptDesigOfficerIds.size() > 0){
	    	if(task != null && !task.trim().isEmpty() && task.trim().length() > 0 && task.trim().equalsIgnoreCase("task")){
	    		list4 = alertAssignedOfficerNewDAO.getBellowLvlDistrictOfficerAlertsCount(govtDeptDesigOfficerIds, fromDate, toDate, printIdsList, electronicIdsList, calCntrIdList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
	    	}else{
	    		list4 = govtAlertSubTaskDAO.getBellowDistrictOfficerAlertsSubTasksCount(govtDeptDesigOfficerIds, fromDate, toDate, printIdsList, electronicIdsList, calCntrIdList,socialMediaTypeIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
	    	}
	    }
	    Map<Long,Long> govtDeptDesigOfficerIdAndCountMap = new HashMap<Long,Long>();
	    Map<Long,Map<Long,Long>> govtDeptDesigOfficerIdThenStatusIdAndCountMap = new HashMap<Long,Map<Long,Long>>();
	    Map<Long,Long> statusIdAndCountMap = new HashMap<Long,Long>();
	    
	    if(list4 != null && list4.size() > 0){
			for(Object[] param : list4){ 
				if(param[0] != null){
					
					Long count = govtDeptDesigOfficerIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(count == null){
						govtDeptDesigOfficerIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[4]));
					}else{
						govtDeptDesigOfficerIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), govtDeptDesigOfficerIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) + commonMethodsUtilService.getLongValueForObject(param[4]));
					}
					
					statusIdAndCountMap = govtDeptDesigOfficerIdThenStatusIdAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(statusIdAndCountMap != null){
						statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[4]));
					}else{
						statusIdAndCountMap = new HashMap<Long, Long>();
						statusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[4]));
						govtDeptDesigOfficerIdThenStatusIdAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),statusIdAndCountMap);
					}
				}
			}
		}
	    
	    //build final vo to sent to ui
	    List<DistrictOfficeViewAlertVO> finalList = new ArrayList<DistrictOfficeViewAlertVO>();
	    List<Object[]> statusLists = null;
	    if(task != null && !task.trim().isEmpty() && task.trim().length() > 0 && task.trim().equalsIgnoreCase("task")){
	    	statusLists = alertDepartmentStatusDAO.getAlertStatusByDepartmentId(66L);
	    }else{
	    	statusLists = alertSubTaskStatusDAO.getAllSubTaskStatus();
	    }
	    
	    buildAlertCount(finalList,govtDeptDesigOfficerIdThenStatusIdAndCountMap,statusLists,govtDeptDesigOfficerIdAndNameMap,govtDeptDesigOfficerIdAndCountMap,govtDeptDesigOfficerIdAndOfficerIdMap);
	    
	    if(finalList != null && finalList.size() > 0){
	    	if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("Ascending")){
		    	Collections.sort(finalList,alertSubUserAscOrder);
		    }else if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("Descending")){
		    	Collections.sort(finalList,alertSubUserDescOrder);
		    }else if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("AlphabeticalAscending")){
		    	Collections.sort(finalList,alertSubUserNameAscOrder);
		    }else if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("AlphabeticalDescending")){
		    	Collections.sort(finalList,alertSubUserNameDescOrder);
		    }
	    }
	    
		return finalList;
	}catch(Exception e){
		e.printStackTrace();
	}
	return null;
}
public static Comparator<DistrictOfficeViewAlertVO> alertSubUserAscOrder = new Comparator<DistrictOfficeViewAlertVO>() {
	public int compare(DistrictOfficeViewAlertVO obj2, DistrictOfficeViewAlertVO obj1) {
	Long vo2 = obj2.getCount();
	Long vo1 = obj1.getCount();
	//descending order of percantages.
	 return vo2.compareTo(vo1);
	}
 };
 public static Comparator<DistrictOfficeViewAlertVO> alertSubUserDescOrder = new Comparator<DistrictOfficeViewAlertVO>() {
		public int compare(DistrictOfficeViewAlertVO obj2, DistrictOfficeViewAlertVO obj1) {
		Long vo2 = obj2.getCount();
		Long vo1 = obj1.getCount();
		//descending order of percantages.
		 return vo1.compareTo(vo2);
		}
};
public static Comparator<DistrictOfficeViewAlertVO> alertSubUserNameAscOrder = new Comparator<DistrictOfficeViewAlertVO>() {
	public int compare(DistrictOfficeViewAlertVO obj2, DistrictOfficeViewAlertVO obj1) {
	String vo2 = obj2.getName();
	String vo1 = obj1.getName();
	//descending order of percantages.
	 return vo2.compareTo(vo1);
	}
};
public static Comparator<DistrictOfficeViewAlertVO> alertSubUserNameDescOrder = new Comparator<DistrictOfficeViewAlertVO>() {
	public int compare(DistrictOfficeViewAlertVO obj2, DistrictOfficeViewAlertVO obj1) {
	String vo2 = obj2.getName();
	String vo1 = obj1.getName();
	//descending order of percantages.
	 return vo1.compareTo(vo2);
	}
};
public void buildAlertCount(List<DistrictOfficeViewAlertVO> finalList,Map<Long,Map<Long,Long>> govtDeptDesigOfficerIdThenStatusIdAndCountMap,List<Object[]> statusLists,Map<Long,String> govtDeptDesigOfficerIdAndNameMap,Map<Long,Long> govtDeptDesigOfficerIdAndCountMap,Map<Long,Long> govtDeptDesigOfficerIdAndOfficerIdMap){
	try{
		Map<Long,Long> statusIdAndCountMap = null;
	    List<DistrictOfficeViewAlertVO> districtOfficeViewAlertVOs = null;
	    DistrictOfficeViewAlertVO innerDistrictOfficeViewAlertVO = null;
	    DistrictOfficeViewAlertVO districtOfficeViewAlertVO = null;
		if(govtDeptDesigOfficerIdThenStatusIdAndCountMap.size() > 0){
			for(Entry<Long,Map<Long,Long>> entry : govtDeptDesigOfficerIdThenStatusIdAndCountMap.entrySet()){
				statusIdAndCountMap = entry.getValue();
				if(statusIdAndCountMap.size() > 0){
					if(statusLists != null && statusLists.size() > 0){
						districtOfficeViewAlertVOs = new ArrayList<DistrictOfficeViewAlertVO>();
						innerDistrictOfficeViewAlertVO = new DistrictOfficeViewAlertVO();
						for(Object[] param : statusLists){
							districtOfficeViewAlertVO = new DistrictOfficeViewAlertVO();
							districtOfficeViewAlertVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
							districtOfficeViewAlertVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
							districtOfficeViewAlertVO.setColor(commonMethodsUtilService.getStringValueForObject(param[2]));
							districtOfficeViewAlertVOs.add(districtOfficeViewAlertVO);  
						}
					}
					for(DistrictOfficeViewAlertVO param : districtOfficeViewAlertVOs){
						if(statusIdAndCountMap.get(param.getId()) != null){
							param.setCount(statusIdAndCountMap.get(param.getId()));  
						}else{
							param.setCount(0l);  
						}
					}
					innerDistrictOfficeViewAlertVO.setSubList1(districtOfficeViewAlertVOs);
					if(govtDeptDesigOfficerIdAndNameMap.get(entry.getKey()) != null){
						innerDistrictOfficeViewAlertVO.setId(entry.getKey()); 
						innerDistrictOfficeViewAlertVO.setGovtOfficerId(govtDeptDesigOfficerIdAndOfficerIdMap.get(entry.getKey()));
						innerDistrictOfficeViewAlertVO.setName(govtDeptDesigOfficerIdAndNameMap.get(entry.getKey()));
						
					}
					if(govtDeptDesigOfficerIdAndCountMap.get(entry.getKey()) != null){
						innerDistrictOfficeViewAlertVO.setCount(govtDeptDesigOfficerIdAndCountMap.get(entry.getKey()));
					}
					finalList.add(innerDistrictOfficeViewAlertVO);     
				}
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}
public List<AlertCoreDashBoardVO> getTotalAlertBySubTaskStatusNew(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,List<Long> statusIdList,Long userId,Long govtDeptScopeId,Long deptId,List<Long> calCntrIdList,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long startDay,Long endDay,Long scopeId,List<Long> locationList,List<Long> subTaskStatusIdList,String isMoreThanYrChkd,String isLagChkd,List<Long> filterSocialMediaIds,List<Long> filterCallCenterIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
	LOG.info("Entered in getTotalAlertByOtherStatus() method of AlertManagementSystemService{}");
	try{
		Date fromDate = null;
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
			fromDate = sdf.parse(fromDateStr);
			toDate = sdf.parse(toDateStr);
		}
		prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
		List<Long> levelValuesList = new ArrayList<Long>();    
		Long levelId = 0L;
		List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
		if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
			for(Object[] param : lvlValueAndLvlIdList){
				levelValuesList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
			}
		}
		List<Long> alertIds = new ArrayList<Long>();
		List<Object[]> list = null;
		List<Long> alertIdSet =null;
		List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
		//if(subTaskStatusIdList.isEmpty())
		  alertIdSet = alertAssignedOfficerNewDAO.getTotalAlertByAllAlertStatus(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusIdList,levelId,levelValuesList,govtDeptScopeId,deptId,calCntrIdList,impactLevelIdList,priorityIdList,alertSourceIdList,printMediaIdList,electronicMediaIdList,scopeId,locationList,filterSocialMediaIds,filterCallCenterIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
		if(alertIdSet != null && alertIdSet.size() > 0){
			alertIds.addAll(alertIdSet);
			 list = alertDAO.getAlertDtls(new HashSet<Long>(alertIdSet));
			//setAlertDtls(alertCoreDashBoardVOs, list);
			if(statusIdList != null && statusIdList.size()>0)
			 setAlertDtlsNew(alertCoreDashBoardVOs,list,startDay,endDay,"alertStatus",isMoreThanYrChkd,isLagChkd);
		}
		if(alertIdSet != null && alertIdSet.size() > 0 && subTaskStatusIdList != null && subTaskStatusIdList.size()>0){
			//alertIdSet =govtAlertSubTaskDAO.getTotalAlertBySubTaskStatus(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusIdList,levelId,levelValuesList,govtDeptScopeId,deptId,calCntrIdList,impactLevelIdList,priorityIdList,alertSourceIdList,printMediaIdList,electronicMediaIdList,scopeId,locationList,filterSocialMediaIds,filterCallCenterIdList,socialMediaTypeIds,subTaskStatusIdList); 
		List<Object[]> subTaskStatusList = govtAlertSubTaskDAO.getSubTaskStatusIds(new HashSet<Long>(alertIdSet));
		if(subTaskStatusList != null && subTaskStatusList.size()>0){
		    setAlertDtlsNew(alertCoreDashBoardVOs, subTaskStatusList,startDay,endDay,"subTaskStatus",isMoreThanYrChkd,isLagChkd);
		} 
		}
		if(alertIdSet != null && alertIdSet.size() > 0 && statusIdList.isEmpty() && subTaskStatusIdList.isEmpty() && !impactLevelIdList.isEmpty()){
			setAlertDtls(alertCoreDashBoardVOs, list);
		}
		if(statusIdList.isEmpty() && impactLevelIdList.isEmpty() && subTaskStatusIdList.isEmpty()){
		List<Long>	alertIdSet1 = alertDAO.getTotalAlertByPendingStatusNew(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusIdList,deptId,calCntrIdList,impactLevelIdList,priorityIdList,alertSourceIdList,printMediaIdList,electronicMediaIdList,scopeId,locationList,subTaskStatusIdList,filterSocialMediaIds,filterCallCenterIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			if(alertIdSet != null && alertIdSet.size() > 0){
				alertIds.addAll(alertIdSet1);
				 list = alertDAO.getAlertDtls(new HashSet<Long>(alertIds));
			setAlertDtls(alertCoreDashBoardVOs, list);
		}
		}
		//set Subtask into alert logic 
		//get subtask count.
		List<Object[]> subtaskCountList = null;
		if(alertIdSet != null && alertIdSet.size() > 0){
			subtaskCountList = govtAlertSubTaskDAO.getSubTaskCount(alertIdSet);
		}
		//create a map from alertId and subtask count.
		Map<Long,Long> alertIdAndSubTaskCountMap = new HashMap<Long,Long>();
		if(subtaskCountList != null && subtaskCountList.size() > 0){
			for(Object[] param : subtaskCountList){
				alertIdAndSubTaskCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
			}
		}
		if(alertCoreDashBoardVOs != null && alertCoreDashBoardVOs.size() > 0){
			for(AlertCoreDashBoardVO alertCoreDashBoardVO : alertCoreDashBoardVOs){
				if(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()) != null){
					alertCoreDashBoardVO.setSubTaskCount(alertIdAndSubTaskCountMap.get(alertCoreDashBoardVO.getId()));
				}
			}
		}
		return alertCoreDashBoardVOs;
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Error occured getTotalAlertByOtherStatus() method of AlertManagementSystemService{}");
	}
	return null;  
}
/*
 * Hymavathi
 * Filter View
 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getLocationFilterClickDetails()
 */
public List<AlertCoreDashBoardVO> getLocationFilterClickDetails(Long userId,String fromDateStr,String toDateStr , List<Long> govtScopeIds,List<Long> locationValues,
		List<Long> desigIds,Long priorityId,List<Long> statusIds ,List<Long> deptIds, Long lagStartCnt, 
		Long lagEndCnt,String alertType,String isMoreThanYrChkd,String isLagChkd,List<Long> paperIdList,List<Long> chanelIdList,List<Long> calCntrIdList,Long childLevelId,List<Long> socialMediaTypeIds,
		List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
	
	List<AlertCoreDashBoardVO> returnList = new ArrayList<AlertCoreDashBoardVO>();
	
	try{
		
		prepareRequiredParameter(paperIdList,chanelIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
		
		List<Long> levelValues = new ArrayList<Long>();    
		Long levelId = 0L;
		List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
		if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
			for(Object[] param : lvlValueAndLvlIdList){
				levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
			}
		}
		
		Date fromDate = null;
		Date toDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
			fromDate = sdf.parse(fromDateStr);
			toDate = sdf.parse(toDateStr);
		}
		List<Long> alertIds = null;
		if(alertType != null && alertType.equalsIgnoreCase("alert")){
			alertIds = alertAssignedOfficerNewDAO.getLocationFilterClickAlertIds(userId,fromDate,toDate,govtScopeIds,locationValues,levelId,levelValues,desigIds,priorityId,statusIds,paperIdList,chanelIdList,calCntrIdList,locationValues,childLevelId,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
		}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
			alertIds = govtAlertSubTaskDAO.getLocationFilterClickAlertIds(userId,fromDate,toDate,govtScopeIds,locationValues,levelId,levelValues,desigIds,priorityId,statusIds,paperIdList,chanelIdList,calCntrIdList,locationValues,childLevelId,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
		}
		 if(alertIds != null && alertIds.size() > 0){
			List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIds));
			setAlertDtls(returnList, list); 
		}
		setSubListCount(returnList, alertIds);
		
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Error occured getLocationFilterClickDetails() method of AlertManagementSystemService{}");
	}
	return returnList;  
}
public Long getSearchAlertsDtls(Long userId,Long alertId)
{
	Long returnAlertId = null;
	try{
		List<Long> deptsList = new ArrayList<Long>(0);
		List<Long> levelValuesList = new ArrayList<Long>(0);
		Long levelId =0l;
		List<Object[]> officerDetailsList =govtDepartmentDesignationOfficerDetailsNewDAO.getLoginUserDetails(userId);
		if(officerDetailsList != null && officerDetailsList.size() > 0){
			for(Object[] param : officerDetailsList){
				deptsList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
				//levelValuesList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				//levelId = commonMethodsUtilService.getLongValueForObject(param[2]);
			}
		}
		List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
	    if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
	      for(Object[] param : lvlValueAndLvlIdList){
	    	  levelValuesList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
	    	  levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
	      }
	    }
	    Set<Long> govtScopeIds = new TreeSet<Long>();
	    if(levelId != null && levelId.longValue() >=0l){
	          List<Object[]> rtrnObjList = govtDepartmentScopeLevelDAO.getChildGovtScopesLevelByParentScopeLevel1(levelId, deptsList);//levelId means Access Level 
	          if(rtrnObjList != null && rtrnObjList.size() > 0){
	            for(Object[] param:rtrnObjList){
	              govtScopeIds.add(commonMethodsUtilService.getLongValueForObject(param[2]));
	            }
	          }
	     }
		 returnAlertId = alertAssignedOfficerNewDAO.getAlertdetails(alertId,deptsList,levelValuesList,levelId,govtScopeIds);
		 if(returnAlertId == null)
		  returnAlertId = govtAlertSubTaskDAO.getAlertSubTaskdetails(alertId,deptsList,levelValuesList,levelId,govtScopeIds);
		
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Error occured getSearchAlertsDtls() method of AlertManagementSystemService{}");
	}
	return returnAlertId;
	} 
	public List<AlertCoreDashBoardVO> getOfficerLocationWiseDepartmentOverviewAlertCount(String fromDateStr, String toDateStr, Long stateId, 
			List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
			Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
			String group,List<Long> calCntrIdList,List<Long> sublevels,Long filterParentScopeId,
			Long filterScopeValue,String searchType,Long source){
		try{

			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}

			//prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList);//Prepare Parameter

			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}

			List<KeyValueVO> subLevels = new ArrayList<KeyValueVO>();
			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
			List<Long> deptScopeIdList = new ArrayList<Long>();
			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
				for(Object [] param : childDeptScopeIdList){
					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					KeyValueVO sublevel = new KeyValueVO();
					sublevel.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
					sublevel.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
					subLevels.add(sublevel);
				}
			}
			if(sublevels != null && sublevels.size() > 0){//In the case of filter data scope wise we are sending selected values
				deptScopeIdList.clear();
				deptScopeIdList.addAll(sublevels);
			}
			
			Map<Long,LinkedHashMap<Long,Long>> locIdThenLvlIdThenAlertCount = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
			LinkedHashMap<Long,Long> levelIdAndAlertCountMap = null;
			List<AlertCoreDashBoardVO> returnList = new ArrayList<AlertCoreDashBoardVO>();
			List<Object[]> alertList = null;
			List<Object[]> alertListForCallCenter = null;
			List<Object[]> feedbackList = null;
			List<Object[]> feedbackListForPending = null;
			List<Object[]> notSatisfiedList = null;
			/*Thread T1 = new Thread(){
				public void run(){
					String myName = alertAssignedOfficerNewDAO.getData();
					System.out.println(myName);
				}
			};
			T1.start();*/
			if(deptScopeIdList != null && deptScopeIdList.size() > 0){
				if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status") ){
					if(alertType != null && alertType.equalsIgnoreCase("alert")){
						alertList = alertAssignedOfficerNewDAO.getAlertDetailsLocationWiseBasedOnDepartmentLevel(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,group,searchType,calCntrIdList,filterParentScopeId,filterScopeValue,null,source,null,null,null,null,null,null,null);
						//only for call center and social media if source is 0 or null
						alertListForCallCenter = alertAssignedOfficerNewDAO.getAlertDetailsLocationWiseBasedOnDepartmentLevel(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,group,searchType,calCntrIdList,filterParentScopeId,filterScopeValue,null,source,"pending",null,null,null,null,null,null);
						//get location wise feedback count
						feedbackList = alertAssignedOfficerNewDAO.getAlertFeedBackDetailsLocationWiseBasedOnDepartmentLevel(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,group,searchType,calCntrIdList,filterParentScopeId,filterScopeValue,"false",source,"other");
						//get location wise feedback count for pending
						feedbackListForPending = alertAssignedOfficerNewDAO.getAlertFeedBackDetailsLocationWiseBasedOnDepartmentLevel(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,group,searchType,calCntrIdList,filterParentScopeId,filterScopeValue,"false",source,"pending");
						//get location wise Not Satisfied and Partially Satisfied alerts in reopen status
						notSatisfiedList = alertAssignedOfficerNewDAO.getAlertFeedBackDetailsLocationWiseBasedOnDepartmentLevel(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,group,searchType,calCntrIdList,filterParentScopeId,filterScopeValue,"true",source,"other");
						
					}
					if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
						if(searchType != null && searchType.equalsIgnoreCase("statusWise")){
							prepareResultForState(alertList,returnList,sortingType,order,alertType,searchType);
							if(returnList != null && returnList.size() > 0){
								pushFeedBackDataForState(returnList,alertListForCallCenter,feedbackList,feedbackListForPending);
							}
							if(returnList != null && returnList.size() > 0){
								pushReopenDataForState(returnList, notSatisfiedList);
							}
							return returnList;  
						}
					}
				}
			}

			Map<Long,String> locIdAndLocNameMap = new LinkedHashMap<Long,String>();
			Map<Long,String> lvlIdAndLvlName = new LinkedHashMap<Long,String>();
			Map<Long,String> lvlIdAndColor = new LinkedHashMap<Long,String>();
			

			Set<Long> deptScopeIds = new HashSet<Long>();
			if(alertList != null && alertList.size() > 0){
				for(Object[] param : alertList){
					deptScopeIds.add(commonMethodsUtilService.getLongValueForObject(param[3]));
				}
			}

			List<Object[]> deptScopeIdDtlsList = null;
			if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status") && deptScopeIds != null && deptScopeIds.size() >0){
				if(searchType != null && searchType.equalsIgnoreCase("statuswise")){
					deptScopeIdDtlsList = alertStatusDAO.getAlertStatusDtlsBasidOnAlertIds(new ArrayList<Long>(deptScopeIds));	
				}
			}
			
			if(deptScopeIdDtlsList != null && deptScopeIdDtlsList.size() > 0){
				for(Object[] param : deptScopeIdDtlsList){
					lvlIdAndLvlName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					lvlIdAndColor.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[2]));
				}  
			}

			if(alertList != null && alertList.size() > 0){   
				for(Object[] param : alertList){
					locIdAndLocNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]),commonMethodsUtilService.getStringValueForObject(param[2]));
					levelIdAndAlertCountMap = locIdThenLvlIdThenAlertCount.get(commonMethodsUtilService.getLongValueForObject(param[1]));
					if(levelIdAndAlertCountMap == null){
						levelIdAndAlertCountMap = new LinkedHashMap<Long,Long>();
						locIdThenLvlIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[1]), levelIdAndAlertCountMap);
					}
					levelIdAndAlertCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[4]));
				}
			}
			
			//for pending
			Map<Long,LinkedHashMap<Long,Long>> locIdThenLvlIdThenAlertCountForPending = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
			LinkedHashMap<Long,Long> levelIdAndAlertCountMapForPending = null;
			if(alertListForCallCenter != null && alertListForCallCenter.size() > 0){   
				for(Object[] param : alertListForCallCenter){
					levelIdAndAlertCountMapForPending = locIdThenLvlIdThenAlertCountForPending.get(commonMethodsUtilService.getLongValueForObject(param[1]));
					if(levelIdAndAlertCountMapForPending == null){
						levelIdAndAlertCountMapForPending = new LinkedHashMap<Long,Long>();
						locIdThenLvlIdThenAlertCountForPending.put(commonMethodsUtilService.getLongValueForObject(param[1]), levelIdAndAlertCountMapForPending);
					}
					levelIdAndAlertCountMapForPending.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[4]));
				}
			}

			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
			if(locIdThenLvlIdThenAlertCount != null && locIdThenLvlIdThenAlertCount.size() > 0){
				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : locIdThenLvlIdThenAlertCount.entrySet()){
					alertCoreDashBoardVO = new AlertCoreDashBoardVO();
					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
					alertCoreDashBoardVO.setName(locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
					buildStatusWiseTemplate(alertCoreDashBoardVO,lvlIdAndLvlName,lvlIdAndColor);
					Long total = new Long(0L);
					for(AlertCoreDashBoardVO boardVO : alertCoreDashBoardVO.getSubList()){
						if(outerEntry.getValue() != null && outerEntry.getValue().get(boardVO.getId()) != null){
							boardVO.setCount(outerEntry.getValue().get(boardVO.getId()));
							total = total + outerEntry.getValue().get(boardVO.getId());
						}
					}
					alertCoreDashBoardVO.setTotalCount(total);
					returnList.add(alertCoreDashBoardVO);
				}
			}
			//calculate status wise total count.
			Map<Long,Long> statusIdAndTotalCount = new HashMap<Long,Long>();
			if(locIdThenLvlIdThenAlertCount != null && locIdThenLvlIdThenAlertCount.size() > 0){
				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : locIdThenLvlIdThenAlertCount.entrySet()){
					for(Entry<Long,Long> innerEntry : outerEntry.getValue().entrySet()){
						statusIdAndTotalCount.put(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey()), 0L);
					}
				}
			}
			if(locIdThenLvlIdThenAlertCount != null && locIdThenLvlIdThenAlertCount.size() > 0){
				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : locIdThenLvlIdThenAlertCount.entrySet()){
					for(Entry<Long,Long> innerEntry : outerEntry.getValue().entrySet()){
						statusIdAndTotalCount.put(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey()), statusIdAndTotalCount.get(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey()))+commonMethodsUtilService.getLongValueForObject(innerEntry.getValue()));
					}
				}
			}
			
			
			
			if(returnList != null && returnList.size() > 0){
				returnList.get(0).getSubLevels().addAll(subLevels);
				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("count")){
					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
						Collections.sort(returnList, alertAscendingCountWiseSortingLvlWise);
					}else{
						Collections.sort(returnList, alertDescCountWiseSortingLvlWise);
					}
				}
				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("name")){
					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
						Collections.sort(returnList, alphabeticalAscSortLvlWise);
					}else{
						Collections.sort(returnList, alphabeticalDescendingSortLvlWise);
					}
				}
			}
			if(returnList != null && returnList.size() > 0){
				pushFeedBackData(returnList, locIdThenLvlIdThenAlertCountForPending,feedbackList,feedbackListForPending);
			}
			if(returnList != null && returnList.size() > 0){
				pushReopenData(returnList, notSatisfiedList);
			}
			if(statusIdAndTotalCount.size() > 0){
				if(returnList != null && returnList.size() > 0){
					AlertCoreDashBoardVO altCorevo = returnList.get(0);
					for(AlertCoreDashBoardVO param : altCorevo.getSubList()){
						if(statusIdAndTotalCount.get(param.getId()) != null){
							param.setGrandTotal(statusIdAndTotalCount.get(param.getId()));
						}
					}
				}
			}
			
			return returnList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewDynamicNew() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public void pushFeedBackData(List<AlertCoreDashBoardVO> returnList,Map<Long,LinkedHashMap<Long,Long>> locIdThenLvlIdThenAlertCountForPending,List<Object[]> feedbackList,List<Object[]> feedbackListForPending){
		try{
			List<Object[]> feedbackStatusList = alertFeedbackStatusDAO.getFeedBackStatus();
			//create map of feedBackId and status map
			Map<Long,String> feedbackAndStatusIdMap = new LinkedHashMap<Long,String>();
			if(feedbackStatusList != null && feedbackStatusList.size() > 0){
				for(Object[] param : feedbackStatusList){
					feedbackAndStatusIdMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
			for(AlertCoreDashBoardVO param : returnList){
				feedbackAndStatusIdMap.put(4L, "Pending Feedback");
				buildFeedbackStatusInTemplate(param,feedbackAndStatusIdMap);
			}
			
			//create a map for locationIdAndFeedbackIdAndFeedbackStatusMap
			Map<Long,Map<Long,Long>> locationIdAndFeedbackIdAndFeedbackStatusCountMap = new HashMap<Long,Map<Long,Long>>();
			Map<Long,Long> feedbackIdAndFeedbackStatusCountMap = null;//new HashMap<Long,String>();
			
			if(feedbackList != null && feedbackList.size() > 0){
				for(Object[] param : feedbackList){
					feedbackIdAndFeedbackStatusCountMap = locationIdAndFeedbackIdAndFeedbackStatusCountMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
					if(feedbackIdAndFeedbackStatusCountMap == null){
						feedbackIdAndFeedbackStatusCountMap = new HashMap<Long,Long>();
						feedbackIdAndFeedbackStatusCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[4]));
						locationIdAndFeedbackIdAndFeedbackStatusCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), feedbackIdAndFeedbackStatusCountMap);
					}
					feedbackIdAndFeedbackStatusCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[4]));
				}
			}
			
			//create a map for locationIdAndFeedbackIdAndFeedbackStatusMap for pending
			Map<Long,Map<Long,Long>> locationIdAndFeedbackIdAndFeedbackStatusCountMapForPending = new HashMap<Long,Map<Long,Long>>();
			Map<Long,Long> feedbackIdAndFeedbackStatusCountMapForPending = null;//new HashMap<Long,String>();
			
			if(feedbackListForPending != null && feedbackListForPending.size() > 0){
				for(Object[] param : feedbackListForPending){
					feedbackIdAndFeedbackStatusCountMapForPending = locationIdAndFeedbackIdAndFeedbackStatusCountMapForPending.get(commonMethodsUtilService.getLongValueForObject(param[1]));
					if(feedbackIdAndFeedbackStatusCountMapForPending == null){
						feedbackIdAndFeedbackStatusCountMapForPending = new HashMap<Long,Long>();
						feedbackIdAndFeedbackStatusCountMapForPending.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[4]));
						locationIdAndFeedbackIdAndFeedbackStatusCountMapForPending.put(commonMethodsUtilService.getLongValueForObject(param[1]), feedbackIdAndFeedbackStatusCountMapForPending);
					}
					feedbackIdAndFeedbackStatusCountMapForPending.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[4]));
				}
			}
			
			
			//calculate feedback status wise total count.
			Map<Long,Long> feedbackStatusIdAndTotalCount = new HashMap<Long,Long>();
			if(locationIdAndFeedbackIdAndFeedbackStatusCountMap != null && locationIdAndFeedbackIdAndFeedbackStatusCountMap.size() > 0){
				for(Entry<Long,Map<Long,Long>> outerEntry : locationIdAndFeedbackIdAndFeedbackStatusCountMap.entrySet()){
					for(Entry<Long,Long> innerEntry : outerEntry.getValue().entrySet()){
						feedbackStatusIdAndTotalCount.put(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey()), 0L);
					}
				}
				feedbackStatusIdAndTotalCount.put(4L, 0L);
			}
			if(locationIdAndFeedbackIdAndFeedbackStatusCountMap != null && locationIdAndFeedbackIdAndFeedbackStatusCountMap.size() > 0){
				for(Entry<Long,Map<Long,Long>> outerEntry : locationIdAndFeedbackIdAndFeedbackStatusCountMap.entrySet()){
					for(Entry<Long,Long> innerEntry : outerEntry.getValue().entrySet()){
						feedbackStatusIdAndTotalCount.put(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey()), feedbackStatusIdAndTotalCount.get(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey()))+commonMethodsUtilService.getLongValueForObject(innerEntry.getValue()));
					}
				}
			}
			
			
			//push the feedback count in vo.
			for(AlertCoreDashBoardVO param : returnList){
				Long locationId = param.getId();
				if(locationIdAndFeedbackIdAndFeedbackStatusCountMap.size() > 0 && locationIdAndFeedbackIdAndFeedbackStatusCountMap.get(locationId) != null){
					for(AlertCoreDashBoardVO innerParam : param.getSubList1()){
						if(locationIdAndFeedbackIdAndFeedbackStatusCountMap.get(locationId).get(innerParam.getId()) != null){
							innerParam.setCount(locationIdAndFeedbackIdAndFeedbackStatusCountMap.get(locationId).get(innerParam.getId()));
						}else{
							innerParam.setCount(0L);
						}
					}
				}
			}
			// calculate feedback pending location wise here...........
			
			//first create a map for locationId and closed alrts
			Map<Long,Long> locationIdAndClosedAlerts = new HashMap<Long,Long>();
			
			for(Entry<Long,LinkedHashMap<Long,Long>> entry : locIdThenLvlIdThenAlertCountForPending.entrySet()){
				Long closedAlerts = new Long(0L);//completed and closed alerts
				/*if(entry.getValue() != null && entry.getValue().get(4L) != null){
					closedAlerts = closedAlerts + entry.getValue().get(4L);//Completed status
				}*/
				if(entry.getValue() != null && entry.getValue().get(12L) != null){
					closedAlerts = closedAlerts + entry.getValue().get(12L);//Closed status
				}
				locationIdAndClosedAlerts.put(entry.getKey(), closedAlerts);
			}
			
			//second create map for locationId and feedback collected alerts
			Map<Long,Long> locationIdAndFeedbackCollectedAlerts = new HashMap<Long,Long>();
			
			for(Entry<Long,Map<Long,Long>> entry : locationIdAndFeedbackIdAndFeedbackStatusCountMapForPending.entrySet()){
				Long feedbackCollectedAlerts = new Long(0L);
				if(entry.getValue() != null && entry.getValue().get(1L) != null){
					feedbackCollectedAlerts = feedbackCollectedAlerts + entry.getValue().get(1L);//Completely Satisfied
				}
				if(entry.getValue() != null && entry.getValue().get(2L) != null){
					feedbackCollectedAlerts = feedbackCollectedAlerts + entry.getValue().get(2L);//Not Satisfied
				}
				if(entry.getValue() != null && entry.getValue().get(3L) != null){
					feedbackCollectedAlerts = feedbackCollectedAlerts + entry.getValue().get(3L);//Partially Satisfied
				}
				locationIdAndFeedbackCollectedAlerts.put(entry.getKey(), feedbackCollectedAlerts);
			}
			
			//now push feedback pending count into vo.
			
			for(AlertCoreDashBoardVO param : returnList){
				Long locationId = param.getId();
				//if(locationIdAndFeedbackIdAndFeedbackStatusCountMap.size() > 0 && locationIdAndFeedbackIdAndFeedbackStatusCountMap.get(locationId) != null){
					for(AlertCoreDashBoardVO innerParam : param.getSubList1()){
						if(innerParam.getId().longValue() == 4L){//feedback pending count   
							Long totalClosedAlert = commonMethodsUtilService.getLongValueForObject(locationIdAndClosedAlerts.get(locationId));
							Long totalFeedbackCollectedAlert = commonMethodsUtilService.getLongValueForObject(locationIdAndFeedbackCollectedAlerts.get(locationId));
							innerParam.setCount(totalClosedAlert-totalFeedbackCollectedAlert);
							feedbackStatusIdAndTotalCount.put(4L, feedbackStatusIdAndTotalCount.get(4L) != null ? (feedbackStatusIdAndTotalCount.get(4L)+(totalClosedAlert-totalFeedbackCollectedAlert)) : (totalClosedAlert-totalFeedbackCollectedAlert));
							/*Long pending = totalClosedAlert-totalFeedbackCollectedAlert;
							if(pending.longValue() < 0L){
								innerParam.setCount(0L);
								feedbackStatusIdAndTotalCount.put(4L, feedbackStatusIdAndTotalCount.get(4L)+0L);
							}else{
								innerParam.setCount(pending);
								feedbackStatusIdAndTotalCount.put(4L, feedbackStatusIdAndTotalCount.get(4L)+pending);
							}*/
							
						}
					}
				//}
			}
			
			if(feedbackStatusIdAndTotalCount.size() > 0){
				if(returnList != null && returnList.size() > 0){
					AlertCoreDashBoardVO altCorevo = returnList.get(0);   
					for(AlertCoreDashBoardVO param : altCorevo.getSubList1()){
						if(feedbackStatusIdAndTotalCount.get(param.getId()) != null){
							param.setGrandTotal(feedbackStatusIdAndTotalCount.get(param.getId()));
						}
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void pushFeedBackDataForState(List<AlertCoreDashBoardVO> returnList,List<Object[]> alertListForCallCenter,List<Object[]> feedbackList,List<Object[]> feedbackListForPending){
		try{
			Map<Long,LinkedHashMap<Long,Long>> lvlIdThenStatusIdThenAlertCountForPending = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
			LinkedHashMap<Long,Long> statusIdThenAlertCountForPending = null;
			if(alertListForCallCenter != null && alertListForCallCenter.size() > 0){
    			for(Object[] param : alertListForCallCenter){
    				statusIdThenAlertCountForPending = lvlIdThenStatusIdThenAlertCountForPending.get(commonMethodsUtilService.getLongValueForObject(param[3]));
					if(statusIdThenAlertCountForPending == null){
						statusIdThenAlertCountForPending = new LinkedHashMap<Long,Long>();
						lvlIdThenStatusIdThenAlertCountForPending.put(commonMethodsUtilService.getLongValueForObject(param[3]), statusIdThenAlertCountForPending);
					}
					statusIdThenAlertCountForPending.put(commonMethodsUtilService.getLongValueForObject(param[4]), commonMethodsUtilService.getLongValueForObject(param[5]));
				}
    		}
			
			List<Object[]> feedbackStatusList = alertFeedbackStatusDAO.getFeedBackStatus();
			//create map of feedBackId and status map
			Map<Long,String> feedbackAndStatusIdMap = new LinkedHashMap<Long,String>();
			if(feedbackStatusList != null && feedbackStatusList.size() > 0){
				for(Object[] param : feedbackStatusList){
					feedbackAndStatusIdMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
			for(AlertCoreDashBoardVO param : returnList){
				feedbackAndStatusIdMap.put(4L, "Pending Feedback");
				buildFeedbackStatusInTemplate(param,feedbackAndStatusIdMap);
			}
			
			//create a map for locationIdAndFeedbackIdAndFeedbackStatusMap
			Map<Long,Map<Long,Long>> locationIdAndFeedbackIdAndFeedbackStatusCountMap = new HashMap<Long,Map<Long,Long>>();
			Map<Long,Long> feedbackIdAndFeedbackStatusCountMap = null;
			
			if(feedbackList != null && feedbackList.size() > 0){
				for(Object[] param : feedbackList){
					feedbackIdAndFeedbackStatusCountMap = locationIdAndFeedbackIdAndFeedbackStatusCountMap.get(commonMethodsUtilService.getLongValueForObject(param[3]));
					if(feedbackIdAndFeedbackStatusCountMap == null){
						feedbackIdAndFeedbackStatusCountMap = new HashMap<Long,Long>();
						feedbackIdAndFeedbackStatusCountMap.put(commonMethodsUtilService.getLongValueForObject(param[4]), commonMethodsUtilService.getLongValueForObject(param[5]));
						locationIdAndFeedbackIdAndFeedbackStatusCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), feedbackIdAndFeedbackStatusCountMap);
					}
					feedbackIdAndFeedbackStatusCountMap.put(commonMethodsUtilService.getLongValueForObject(param[4]), commonMethodsUtilService.getLongValueForObject(param[5]));
				}
			}
			
			//create a map for locationIdAndFeedbackIdAndFeedbackStatusMap for pending
			Map<Long,Map<Long,Long>> locationIdAndFeedbackIdAndFeedbackStatusCountMapForPending = new HashMap<Long,Map<Long,Long>>();
			Map<Long,Long> feedbackIdAndFeedbackStatusCountMapForPending = null;//new HashMap<Long,String>();
			
			if(feedbackListForPending != null && feedbackListForPending.size() > 0){
				for(Object[] param : feedbackListForPending){
					feedbackIdAndFeedbackStatusCountMapForPending = locationIdAndFeedbackIdAndFeedbackStatusCountMapForPending.get(commonMethodsUtilService.getLongValueForObject(param[3]));
					if(feedbackIdAndFeedbackStatusCountMapForPending == null){
						feedbackIdAndFeedbackStatusCountMapForPending = new HashMap<Long,Long>();
						feedbackIdAndFeedbackStatusCountMapForPending.put(commonMethodsUtilService.getLongValueForObject(param[4]), commonMethodsUtilService.getLongValueForObject(param[5]));
						locationIdAndFeedbackIdAndFeedbackStatusCountMapForPending.put(commonMethodsUtilService.getLongValueForObject(param[3]), feedbackIdAndFeedbackStatusCountMapForPending);
					}
					feedbackIdAndFeedbackStatusCountMapForPending.put(commonMethodsUtilService.getLongValueForObject(param[4]), commonMethodsUtilService.getLongValueForObject(param[5]));
				}
			}
			
			
			//calculate feedback status wise total count.
			Map<Long,Long> feedbackStatusIdAndTotalCount = new HashMap<Long,Long>();
			if(locationIdAndFeedbackIdAndFeedbackStatusCountMap != null && locationIdAndFeedbackIdAndFeedbackStatusCountMap.size() > 0){
				for(Entry<Long,Map<Long,Long>> outerEntry : locationIdAndFeedbackIdAndFeedbackStatusCountMap.entrySet()){
					for(Entry<Long,Long> innerEntry : outerEntry.getValue().entrySet()){
						feedbackStatusIdAndTotalCount.put(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey()), 0L);
					}
				}
				feedbackStatusIdAndTotalCount.put(4L, 0L);
			}
			if(locationIdAndFeedbackIdAndFeedbackStatusCountMap != null && locationIdAndFeedbackIdAndFeedbackStatusCountMap.size() > 0){
				for(Entry<Long,Map<Long,Long>> outerEntry : locationIdAndFeedbackIdAndFeedbackStatusCountMap.entrySet()){
					for(Entry<Long,Long> innerEntry : outerEntry.getValue().entrySet()){
						feedbackStatusIdAndTotalCount.put(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey()), feedbackStatusIdAndTotalCount.get(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey()))+commonMethodsUtilService.getLongValueForObject(innerEntry.getValue()));
					}
				}
			}
			
			
			//push the feedback count in vo.
			for(AlertCoreDashBoardVO param : returnList){
				Long locationId = param.getId();
				if(locationIdAndFeedbackIdAndFeedbackStatusCountMap.size() > 0 && locationIdAndFeedbackIdAndFeedbackStatusCountMap.get(locationId) != null){
					for(AlertCoreDashBoardVO innerParam : param.getSubList1()){
						if(locationIdAndFeedbackIdAndFeedbackStatusCountMap.get(locationId).get(innerParam.getId()) != null){
							innerParam.setCount(locationIdAndFeedbackIdAndFeedbackStatusCountMap.get(locationId).get(innerParam.getId()));
						}else{
							innerParam.setCount(0L);
						}
					}
				}
			}
			// calculate feedback pending location wise here...........
			
			//first create a map for locationId and closed alrts
			Map<Long,Long> locationIdAndClosedAlerts = new HashMap<Long,Long>();
			
			for(Entry<Long,LinkedHashMap<Long,Long>> entry : lvlIdThenStatusIdThenAlertCountForPending.entrySet()){
				Long closedAlerts = new Long(0L);//completed and closed alerts
				/*if(entry.getValue() != null && entry.getValue().get(4L) != null){
					closedAlerts = closedAlerts + entry.getValue().get(4L);//Completed status
				}*/
				if(entry.getValue() != null && entry.getValue().get(12L) != null){
					closedAlerts = closedAlerts + entry.getValue().get(12L);//Closed status
				}
				locationIdAndClosedAlerts.put(entry.getKey(), closedAlerts);
			}
			
			//second create map for locationId and feedback collected alerts
			Map<Long,Long> locationIdAndFeedbackCollectedAlerts = new HashMap<Long,Long>();
			
			for(Entry<Long,Map<Long,Long>> entry : locationIdAndFeedbackIdAndFeedbackStatusCountMapForPending.entrySet()){
				Long feedbackCollectedAlerts = new Long(0L);
				if(entry.getValue() != null && entry.getValue().get(1L) != null){
					feedbackCollectedAlerts = feedbackCollectedAlerts + entry.getValue().get(1L);//Completely Satisfied
				}
				if(entry.getValue() != null && entry.getValue().get(2L) != null){
					feedbackCollectedAlerts = feedbackCollectedAlerts + entry.getValue().get(2L);//Not Satisfied
				}
				if(entry.getValue() != null && entry.getValue().get(3L) != null){
					feedbackCollectedAlerts = feedbackCollectedAlerts + entry.getValue().get(3L);//Partially Satisfied
				}
				locationIdAndFeedbackCollectedAlerts.put(entry.getKey(), feedbackCollectedAlerts);
			}
			
			//now push feedback pending count into vo.
			for(AlertCoreDashBoardVO param : returnList){
				Long locationId = param.getId();
				//if(locationIdAndFeedbackIdAndFeedbackStatusCountMap.size() > 0 && locationIdAndFeedbackIdAndFeedbackStatusCountMap.get(locationId) != null){
					for(AlertCoreDashBoardVO innerParam : param.getSubList1()){
						if(innerParam.getId().longValue() == 4L){//feedback pending count
							Long totalClosedAlert = commonMethodsUtilService.getLongValueForObject(locationIdAndClosedAlerts.get(locationId));
							Long totalFeedbackCollectedAlert = commonMethodsUtilService.getLongValueForObject(locationIdAndFeedbackCollectedAlerts.get(locationId));
							innerParam.setCount(totalClosedAlert-totalFeedbackCollectedAlert);
							feedbackStatusIdAndTotalCount.put(4L, (feedbackStatusIdAndTotalCount.get(4L) != null ? feedbackStatusIdAndTotalCount.get(4L) : 0L) +(totalClosedAlert-totalFeedbackCollectedAlert));
							/*Long pending = totalClosedAlert-totalFeedbackCollectedAlert;
							if(pending.longValue() < 0L){
								innerParam.setCount(0L);
								feedbackStatusIdAndTotalCount.put(4L, feedbackStatusIdAndTotalCount.get(4L)+0L);
							}else{
								innerParam.setCount(pending);
								feedbackStatusIdAndTotalCount.put(4L, feedbackStatusIdAndTotalCount.get(4L)+pending);
							}*/
						}
					}
				//}
			}
			if(feedbackStatusIdAndTotalCount.size() > 0){
				if(returnList != null && returnList.size() > 0){
					AlertCoreDashBoardVO altCorevo = returnList.get(0);
					for(AlertCoreDashBoardVO param : altCorevo.getSubList1()){
						if(feedbackStatusIdAndTotalCount.get(param.getId()) != null){
							param.setGrandTotal(feedbackStatusIdAndTotalCount.get(param.getId()));
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void buildFeedbackStatusInTemplate(AlertCoreDashBoardVO param,Map<Long,String> feedbackAndStatusIdMap){
		try{
			AlertCoreDashBoardVO alertCoreDashBoardVO = null;
			List<AlertCoreDashBoardVO> alertCoreDashBoardVOs = new ArrayList<AlertCoreDashBoardVO>();
			for(Entry<Long,String> entry : feedbackAndStatusIdMap.entrySet()){
				alertCoreDashBoardVO = new AlertCoreDashBoardVO();
				alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(entry.getKey()));
				alertCoreDashBoardVO.setName(commonMethodsUtilService.getStringValueForObject(entry.getValue()));
				alertCoreDashBoardVOs.add(alertCoreDashBoardVO);
			}
			param.setSubList1(alertCoreDashBoardVOs);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void pushReopenData(List<AlertCoreDashBoardVO> returnList, List<Object[]> notSatisfiedList){
		try{
			Map<Long,Long> locationIdAndReopenCount = new HashMap<Long,Long>();
			if(notSatisfiedList != null && notSatisfiedList.size() > 0){
				for(Object[] param : notSatisfiedList){
					locationIdAndReopenCount.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[4]));
				}
			}
			for(AlertCoreDashBoardVO param : returnList){
				if(locationIdAndReopenCount.get(param.getId()) != null){
					param.setReopenCount(locationIdAndReopenCount.get(param.getId()));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void pushReopenDataForState(List<AlertCoreDashBoardVO> returnList, List<Object[]> notSatisfiedList){
		try{
			Map<Long,Long> locationIdAndReopenCount = new HashMap<Long,Long>();
			if(notSatisfiedList != null && notSatisfiedList.size() > 0){
				for(Object[] param : notSatisfiedList){
					locationIdAndReopenCount.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[5]));
				}
			}
			for(AlertCoreDashBoardVO param : returnList){
				if(locationIdAndReopenCount.get(param.getId()) != null){
					param.setReopenCount(locationIdAndReopenCount.get(param.getId()));  
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/*public void pushReopenData(List<AlertCoreDashBoardVO> returnList, List<Object[]> notSatisfiedList){
		try{
			//create a map for locationId and list of alert ids
			Map<Long,Set<Long>> locationIdAndAlertIds = new HashMap<Long,Set<Long>>();
			Set<Long> alertIds = null;
			if(notSatisfiedList != null && notSatisfiedList.size() > 0){
				for(Object[] param : notSatisfiedList){
					alertIds = locationIdAndAlertIds.get(commonMethodsUtilService.getLongValueForObject(param[1]));
					if(alertIds == null){
						alertIds = new HashSet<Long>();
						alertIds.add(commonMethodsUtilService.getLongValueForObject(param[4]));
						locationIdAndAlertIds.put(commonMethodsUtilService.getLongValueForObject(param[1]), alertIds);
					}
					alertIds.add(commonMethodsUtilService.getLongValueForObject(param[4]));
				}
			}
			
			//get all the alert whose status is reopened.
			List<Long> reopenAlerts = alertAssignedOfficerNewDAO.getAllReOpenAlerts();
			List<Long> tempList = null;
			for(AlertCoreDashBoardVO parama : returnList){
				Long locId = parama.getId();
				alertIds = locationIdAndAlertIds.get(locId);
				if(alertIds == null){
					parama.setPendingCount(0L);
				}else{
					tempList = new ArrayList<Long>(reopenAlerts);
					if(tempList != null && tempList.size() > 0){
						tempList.retainAll(alertIds);
						int count = tempList.size();
						parama.setPendingCount(Long.valueOf(Integer.toString(count)));
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
	/*public void pushReopenDataForState(List<AlertCoreDashBoardVO> returnList, List<Object[]> notSatisfiedList){
		try{
			//create a map for locationId and list of alert ids
			Map<Long,Set<Long>> locationIdAndAlertIds = new HashMap<Long,Set<Long>>();
			Set<Long> alertIds = null;
			if(notSatisfiedList != null && notSatisfiedList.size() > 0){
				for(Object[] param : notSatisfiedList){
					alertIds = locationIdAndAlertIds.get(commonMethodsUtilService.getLongValueForObject(param[1]));
					if(alertIds == null){
						alertIds = new HashSet<Long>();
						alertIds.add(commonMethodsUtilService.getLongValueForObject(param[4]));
						locationIdAndAlertIds.put(commonMethodsUtilService.getLongValueForObject(param[1]), alertIds);
					}
					alertIds.add(commonMethodsUtilService.getLongValueForObject(param[4]));
				}
			}
			
			//get all the alert whose status is reopened.
			List<Long> reopenAlerts = alertAssignedOfficerNewDAO.getAllReOpenAlerts();
			List<Long> tempList = null;
			for(AlertCoreDashBoardVO parama : returnList){
				Long locId = parama.getId();
				alertIds = locationIdAndAlertIds.get(locId);
				if(alertIds == null){
					parama.setPendingCount(0L);
				}else{
					tempList = new ArrayList<Long>(reopenAlerts);
					if(tempList != null && tempList.size() > 0){
						tempList.retainAll(alertIds);
						int count = tempList.size();
						parama.setPendingCount(Long.valueOf(Integer.toString(count)));
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/

	
	/*
	 * Hymavathi
	 * Filter View
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getAlertDetailsForGrievanceReportClick()
	 */
	public List<AlertCoreDashBoardVO> getAlertDetailsForGrievanceReportClick(String fromDateStr, String toDateStr, Long stateId, 
			List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
			Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
			String group,List<Long> calCntrIdList,List<Long> sublevels,Long filterParentScopeId,
			Long filterScopeValue,String searchType,Long statusId,Long sourseId){
		
		List<AlertCoreDashBoardVO> returnList = new ArrayList<AlertCoreDashBoardVO>();
		
		try{
			
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}

			//prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList);//Prepare Parameter

			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}

			List<KeyValueVO> subLevels = new ArrayList<KeyValueVO>();
			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
			List<Long> deptScopeIdList = new ArrayList<Long>();
			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
				for(Object [] param : childDeptScopeIdList){
					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					KeyValueVO sublevel = new KeyValueVO();
					sublevel.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
					sublevel.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
					subLevels.add(sublevel);
				}
			}
			if(sublevels != null && sublevels.size() > 0){//In the case of filter data scope wise we are sending selected values
				deptScopeIdList.clear();
				deptScopeIdList.addAll(sublevels);
			}
			
			List<Long> alertIds = null;
			List<Long> pendingFeedbackList = null;
			if(deptScopeIdList != null && deptScopeIdList.size() > 0){
				if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status") ){
					if(alertType != null && alertType.equalsIgnoreCase("alert")){
						alertIds = alertAssignedOfficerNewDAO.getAlertDetailsForGrievanceReportClick(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,group,searchType,calCntrIdList,filterParentScopeId,filterScopeValue,statusId,sourseId,"other");
					}else if(alertType != null && alertType.equalsIgnoreCase("feedback")){
						if(statusId != null && statusId.longValue() == 4L){
							pendingFeedbackList = alertAssignedOfficerNewDAO.getAlertFeedBackDetailsForGrievanceReportClick(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,group,searchType,calCntrIdList,filterParentScopeId,filterScopeValue,"false",sourseId,statusId);
							alertIds = alertAssignedOfficerNewDAO.getAlertDetailsForGrievanceReportClick(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,group,searchType,calCntrIdList,filterParentScopeId,filterScopeValue,0L,sourseId,"pending");
						}else{
							alertIds = alertAssignedOfficerNewDAO.getAlertFeedBackDetailsForGrievanceReportClick(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,group,searchType,calCntrIdList,filterParentScopeId,filterScopeValue,"false",sourseId,statusId);
						}
						
					}else if(alertType != null && alertType.equalsIgnoreCase("reopen")){
						alertIds = alertAssignedOfficerNewDAO.getAlertFeedBackDetailsForGrievanceReportClick(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,group,searchType,calCntrIdList,filterParentScopeId,filterScopeValue,"true",sourseId,statusId);
					}
				}
			}
			if(alertType != null && alertType.equalsIgnoreCase("feedback")){
				if(statusId != null && statusId.longValue() == 4L){
					if(pendingFeedbackList != null && pendingFeedbackList.size() > 0){
						alertIds.removeAll(pendingFeedbackList);
					}
					if(alertIds != null && alertIds.size() > 0){
						List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIds));
						setAlertDtls(returnList, list); 
					}
				}else{
					if(alertIds != null && alertIds.size() > 0){
						List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIds));
						setAlertDtls(returnList, list); 
					}
				}
			}else{
				if(alertIds != null && alertIds.size() > 0){
					List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIds));
					setAlertDtls(returnList, list); 
				}
			}
			
			setSubListCount(returnList, alertIds);
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getAlertDetailsForGrievanceReportClick() method of AlertManagementSystemService{}");
		}
		return returnList;  
	}
	public List<IdNameVO> getAllMainDepartments(){
		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
		try {			
			List<Object[]> allDeptsObj = govtDepartmentRelationDAO.getAllMainDepartments();
			if(allDeptsObj !=null && allDeptsObj.size()>0){
				for (Object[] objects : allDeptsObj) {
					IdNameVO vo = new IdNameVO();
					vo.setId(objects[0] !=null ? (Long)objects[0]:null);
					vo.setName(objects[1] !=null ? objects[1].toString():null);
					finalList.add(vo);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error occured getAllMainDepartments() method of AlertManagementSystemService{}");
		}
		return finalList;
	}
	
	public String changeDepartmentStatusToAlert(final Long alertId,final Long changedDeptId,final Long userId){
		String status = null;
		try {
			status = (String)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					
					String successStatus = "success";
					
					Alert alert = alertDAO.get(alertId);					
					Long oldDeptId = alert.getGovtDepartmentId();
					String oldDepartment = alert.getGovtDepartment().getDepartmentName();
					
					alert.setGovtDepartmentId(changedDeptId);
					alert.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					alert.setUpdatedBy(userId);
					alert.setAlertStatusId(1l);
					
					alert = alertDAO.save(alert);
					
					List<Long> assingedIdsObj = alertAssignedOfficerNewDAO.getAssignedDtls(alertId);// Max Only one AssignId will come
					
					AlertAssignedOfficerNew alertAssignedOfficerNew = new AlertAssignedOfficerNew();
					
					if(commonMethodsUtilService.isListOrSetValid(assingedIdsObj)){
						for (Long assingedId : assingedIdsObj) {
							//AlertAssignedOfficerNew alertAssignedOfficer = new AlertAssignedOfficerNew();
							AlertAssignedOfficerNew alertAssignedOfficer = alertAssignedOfficerNewDAO.get(assingedId);
							
							alertAssignedOfficer.setGovtDepartmentId(changedDeptId);							
							alertAssignedOfficer.setAlertStatusId(1l);
							
							alertAssignedOfficer.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							alertAssignedOfficer.setUpdatedBy(userId);

							alertAssignedOfficerNew = alertAssignedOfficerNewDAO.save(alertAssignedOfficer);
							
						}
					}
					
					
					//Officer Assigning Tracking
					AlertAssignedOfficerTrackingNew alertAssignedOfficerTracking = new AlertAssignedOfficerTrackingNew();
					alertAssignedOfficerTracking.setAlertAssignedOfficerId(alertAssignedOfficerNew.getAlertAssignedOfficerId());
					alertAssignedOfficerTracking.setAlertId(alert.getAlertId());
					alertAssignedOfficerTracking.setGovtDepartmentDesignationOfficerId(alertAssignedOfficerNew.getGovtDepartmentDesignationOfficerId());
					alertAssignedOfficerTracking.setGovtOfficerId(alertAssignedOfficerNew.getGovtOfficerId());
					alertAssignedOfficerTracking.setInsertedBy(userId);
					alertAssignedOfficerTracking.setUpdatedBy(userId);
					alertAssignedOfficerTracking.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					alertAssignedOfficerTracking.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					alertAssignedOfficerTracking.setAlertStatusId(1l);
					alertAssignedOfficerTracking.setGovtAlertActionTypeId(8l);
					alertAssignedOfficerTracking.setIsApproved(alertAssignedOfficerNew.getIsApproved() !=null ? alertAssignedOfficerNew.getIsApproved():null);
					alertAssignedOfficerTracking.setGovtDepartmentId(changedDeptId !=null ? changedDeptId.longValue():null);
					alertAssignedOfficerTracking.setAlertSeviorityId(alert.getAlertSeverityId());
					
					alertAssignedOfficerTracking = alertAssignedOfficerTrackingNewDAO.save(alertAssignedOfficerTracking);
					
					/* SMS sending after changing dept */
					
					/*List<String> mobileNos = govtOfficerNewDAO.getOfficerDetailsByOfficerId(alertAssignedOfficerNew.getGovtOfficerId());
					List<Long> userIdsList = govtDepartmentDesignationOfficerDetailsNewDAO.getuserIdDtlsForDesignationOfficerId(alertAssignedOfficerNew.getGovtDepartmentDesignationOfficerId());
					if(commonMethodsUtilService.isListOrSetValid(userIdsList)){
						Long assignedToUserID = userIdsList.get(0);
						if(mobileNos != null && mobileNos.size() > 0 && mobileNos.get(0).trim().length() > 0 && !mobileNos.get(0).trim().isEmpty()){
							 sendSMSTOAlertAssignedOfficer(alertAssignedOfficerNew.getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignationId(),alertAssignedOfficerNew.getGovtOfficerId(),mobileNos!= null ? mobileNos.get(0):null,alert.getAlertId(),
									 alertAssignedOfficerTracking.getGovtAlertActionTypeId(),assignedToUserID,"","",userId);	
						}
					}*/
					
					if(alert.getAlertCategoryId() !=null && alert.getAlertCategoryId() == 2l || alert.getAlertCategoryId() == 3l){
						
						Long cnpOldDeptId = govtDepartmentDAO.getCNPGovtDepartmentIdForGovtDepartment(oldDeptId);
						Long cnpChangedDeptId = govtDepartmentDAO.getCNPGovtDepartmentIdForGovtDepartment(changedDeptId);
						
						String articleStatus = changeCNPDepartment(alert.getAlertCategoryId(),alert.getAlertCategoryTypeId(),cnpChangedDeptId,cnpOldDeptId);	
							successStatus = articleStatus;						
					}
					
					return successStatus;
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error occured changeDepartmentStatusToAlert() method of AlertManagementSystemService{}");
			return "failure";
		}
		return status;
	}
	
	public String changeCNPDepartment(final Long alertCategoryId,final Long alertCategoryTypeId,final Long newDeptId,final Long oldDeptId){
		String status = null;
		try {
			
			status = (String)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					
				
				ClientConfig clientConfig = new DefaultClientConfig();
	
		         clientConfig.getFeatures().put(
		                  JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		         Client client = Client.create(clientConfig);
	
		         ActionableVO vo = new ActionableVO();
		         vo.setAlertCategory(alertCategoryId);
		         vo.setAlertType(alertCategoryTypeId);//AlertCategoryTypeId
		         vo.setStatusId(newDeptId);//NewDepatId
		         vo.setId(oldDeptId);//oldDeptId
		         
				/*WebResource webResource = client
					.resource("http://localhost:8080/CommunityNewsPortal/webservice/changeCNPDepartment");
	            ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class,vo);*/
					
				WebResource webResource = client
				.resource("http://www.mytdp.com/CommunityNewsPortal/webservice/changeCNPDepartment");
			
				ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class,vo);
			
		 	      if(response.getStatus() != 200){
		 	    	throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());			 		     
		 	      }
		 	      else{			 	    	  
		 	    	  return  response.getEntity(String.class);	 
		 	      }
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error occured changeCNPDepartment() method of AlertManagementSystemService{}");
		}
		return status;
	}
	public List<AlertsSummeryVO> getCadreGreivienceEfficiency(String fromDateStr, String toDateStr, Long stateId, 
			List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
			Long parentGovtDepartmentScopeId,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> sublevels,Long source,List<Long> statusIdList,int rangeValue){
		try{
			List<AlertsSummeryVO> finalList = new ArrayList<AlertsSummeryVO>();
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			//prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList);//Prepare Parameter

			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}

			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
			List<Long> deptScopeIdList = new ArrayList<Long>();
			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
				for(Object [] param : childDeptScopeIdList){
					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			if(sublevels != null && sublevels.size() > 0){//In the case of filter data scope wise we are sending selected values
				deptScopeIdList.clear();
				deptScopeIdList.addAll(sublevels);
			}
			
			List<Object[]> totalAlertsDateWise = null;
			
			if(deptScopeIdList != null && deptScopeIdList.size() > 0){
				totalAlertsDateWise = alertAssignedOfficerNewDAO.getDifferenceTimeList(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,calCntrIdList,socialMediaTypeIds,source,statusIdList);
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
			return finalList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewDynamicNew() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public List<IdAndNameVO> getMondayGrievanceTypeList(){   
		try{
			List<IdAndNameVO> resultList = new ArrayList<IdAndNameVO>();
			List<Object[]> rtrnObjList = alertDAO.getMondayGrievanceTypeList();
			setDataToList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getMondayGrievanceTypeList() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public List<IdAndNameVO> getJanmabhoomiTypeList(){   
		try{
			List<IdAndNameVO> resultList = new ArrayList<IdAndNameVO>();
			List<Object[]> rtrnObjList = alertDAO.getJanmabhoomiTypeList();
			setDataToList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getJanmabhoomiTypeList() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public List<IdAndNameVO> getSpecialGrievanceTypeList(){   
		try{
			List<IdAndNameVO> resultList = new ArrayList<IdAndNameVO>();
			List<Object[]> rtrnObjList = alertDAO.getSpecialGrievanceTypeList();
			setDataToList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getSpecialGrievanceTypeList() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public List<IdAndNameVO> getGeneralGrievanceTypeList(){   
		try{
			List<IdAndNameVO> resultList = new ArrayList<IdAndNameVO>();
			List<Object[]> rtrnObjList = alertDAO.getGeneralGrievanceTypeList();
			setDataToList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getGeneralGrievanceTypeList() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public List<IdNameVO> getSubDeptsFrParentDept(Long parentDeptId){
		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
		try{
			
			List<Object[]> subDeptList = govtDepartmentRelationDAO.getSubDeptsForParentDept(parentDeptId);
			if(subDeptList != null && subDeptList.size() > 0l){
				for (Object[] objects : subDeptList) {
					IdNameVO vo = new IdNameVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					finalList.add(vo);
				}
			}
		}catch(Exception e){
			LOG.error("Error occured getSubDeptsFrParentDeptt() method of AlertManagementSystemService{}");
		}
		return finalList;
	}
	public List<IdNameVO> getPresentAssignedDepartmentOfAlert(Long alertId){
		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
		try{
			
			List<Object[]> subDeptList = alertAssignedOfficerNewDAO.getPresentAssignedDepartmentOfAlert(alertId);
			if(subDeptList != null && subDeptList.size() > 0l){
				for (Object[] objects : subDeptList) {
					IdNameVO vo = new IdNameVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					finalList.add(vo);
				}
			}
		}catch(Exception e){
			LOG.error("Error occured getPresentAssignedDepartmentOfAlert() method of AlertManagementSystemService{}");
		}
		return finalList;
	}
	/*
	 * Santosh (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getFinancialAssistanceAlertCntCategoryWise(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.util.List, java.lang.Long, java.util.List, java.lang.String, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	public List<AlertVO> getFinancialAssistanceAlertCntCategoryWise(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		LOG.info("Entered in getFinancialAssistanceAlertCntCategoryWise() method of AlertManagementSystemService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
			prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
			
				if(deptIdList != null && deptIdList.size() == 0){
					deptIdList.add(0L);  
				}
				List<Long> levelValues = new ArrayList<Long>();    
				Long levelId = 0L;
				List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
				if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
					for(Object[] param : lvlValueAndLvlIdList){
						levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
						levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
					}
				}
			     
				Map<Long,AlertVO> catgoryMap = new LinkedHashMap<Long, AlertVO>(0);
				Map<Long,String> categoryNameAndIdMap = new HashMap<Long, String>(0);
				   
			    List<Object[]> rtrnObjLst = alertAssignedOfficerNewDAO.getAlertProposalCategoryWiseAlertCnt(fromDate, toDate, printIdList, electronicIdList, deptIdList, levelId, levelValues, alertStatusIds, calCntrIdList, socialMediaTypeIds, alertSeverityIds, mondayGrievanceTypeIds, janmabhoomiTypeIds, specialGrievanceTypeIds, generalGrievanceTypeIds,"Category");
			    setAlertProposalCategoryWiseAlertcnt(rtrnObjLst,catgoryMap,categoryNameAndIdMap);
			
			  Long totalAmount = 0l;
			 if(catgoryMap != null && catgoryMap.size() >0){
				 for(Entry<Long,AlertVO> categoryEntry:catgoryMap.entrySet()){
					 AlertVO  categoryVO = new AlertVO();
					 categoryVO.setId(categoryEntry.getKey());
					 categoryVO.setName(categoryNameAndIdMap.get(categoryEntry.getKey()));
					 if(categoryEntry.getValue().getSubList2() != null && categoryEntry.getValue().getSubList2().size() > 0){
						 for(AlertVO statusVO:categoryEntry.getValue().getSubList2()){
							 categoryVO.getSubList2().add(statusVO);
							 categoryVO.setAlertCnt(categoryVO.getAlertCnt()+statusVO.getAlertCnt());
							 categoryVO.setProposalAmount(categoryVO.getProposalAmount()+statusVO.getProposalAmount());
							 totalAmount = totalAmount +statusVO.getProposalAmount();
						 }
					 }
					 finalAlertVOs.add(categoryVO);
				 }
			 }
			 if(finalAlertVOs != null && finalAlertVOs.size() > 0){
				 finalAlertVOs.get(0).setCount(totalAmount);//Setting total amount
			 }
			return finalAlertVOs; 
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getFinancialAssistanceAlertCntCategoryWise() method of AlertManagementSystemService{}");
		}
		return null;
	}
	public void setAlertProposalCategoryWiseAlertcnt(List<Object[]> objList,Map<Long,AlertVO> categoryMap,Map<Long,String> categoryIdAndNameMap){
		try{
			if(objList != null && objList.size() > 0){
				for(Object[] param:objList){
					 AlertVO categoryVO = categoryMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));//CategoryId
					 if(categoryVO == null){
						 categoryVO = new AlertVO();
						 categoryVO.setSubList2(getAlertProposalStatusList(objList,"category"));
						 categoryIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
						 categoryMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), categoryVO);
						 
					 }
					 Long propalStatusId = commonMethodsUtilService.getLongValueForObject(param[2]);
					 AlertVO statusVO = getmatchedVo(categoryVO.getSubList2(),propalStatusId);
					 if(statusVO != null){
						 statusVO.setAlertCnt(commonMethodsUtilService.getLongValueForObject(param[4]));//AlertCnt
						 statusVO.setProposalAmount(commonMethodsUtilService.getLongValueForObject(param[5]));//amount
					 }
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setAlertProposalCategoryWiseAlertcnt() method of AlertManagementSystemService{}");
		}
	}
	public List<AlertVO> getAlertProposalStatusList(List<Object[]> objList,String type){
		List<AlertVO> statuList = new ArrayList<AlertVO>();
		Map<Long,AlertVO> statuMap = new TreeMap<Long, AlertVO>();
		try{
			if(objList != null && objList.size() > 0){
				for(Object[] param:objList){
					 AlertVO statusVO = new AlertVO();
					 if(type.equalsIgnoreCase("category")){
						 statusVO.setId(commonMethodsUtilService.getLongValueForObject(param[2]));//StatusId
						 statusVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));//StatusName
					 }else if(type.equalsIgnoreCase("Department")){
						 statusVO.setId(commonMethodsUtilService.getLongValueForObject(param[4]));//StatusId
						 statusVO.setName(commonMethodsUtilService.getStringValueForObject(param[5]));//StatusName
					 }
					 statuMap.put(statusVO.getId(), statusVO);
				}
			}
			if(statuMap != null && statuMap.size() > 0){
				statuList.addAll(statuMap.values());
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getAlertProposalStatusList() method of AlertManagementSystemService{}");
		}
		return statuList;
	}
	/*
	 * Santosh (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getFinancialAssistanceAlertCntDtls(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.util.List, java.lang.Long, java.util.List, java.lang.String, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.lang.Long, java.lang.Long)
	 */
	public List<AlertCoreDashBoardVO> getFinancialAssistanceAlertCntDtls(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,Long proposalCategoryId,Long proposalStatusId){
		LOG.info("Entered in getFinancialAssistanceAlertCntDtls() method of AlertManagementSystemService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			List<AlertCoreDashBoardVO> finalAlertVOs = new ArrayList<AlertCoreDashBoardVO>();
			
			prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
			
			if(deptIdList != null && deptIdList.size() == 0){
				deptIdList.add(0L);  
			}
				List<Long> levelValues = new ArrayList<Long>();    
				Long levelId = 0L;
				List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
				if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
					for(Object[] param : lvlValueAndLvlIdList){
						levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
						levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
					}
				}
			    List<Long> alertIds = alertAssignedOfficerNewDAO.getAlertProposalCategoryWiseAlertDtls(fromDate, toDate, printIdList, electronicIdList, deptIdList, levelId, levelValues, alertStatusIds, calCntrIdList, socialMediaTypeIds, alertSeverityIds, mondayGrievanceTypeIds, janmabhoomiTypeIds, specialGrievanceTypeIds, generalGrievanceTypeIds,proposalCategoryId,proposalStatusId);
			     if(alertIds != null && alertIds.size() > 0){
			    	 List<Object[]> alertDtlsLst = alertDAO.getAlertDtls(new HashSet<Long>(alertIds));
					  setAlertDtls(finalAlertVOs, alertDtlsLst); 
			     }
			   return finalAlertVOs; 
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getFinancialAssistanceAlertCntDtls() method of AlertManagementSystemService{}");
		}
		return null;
	}

	
	public String updateProposalStatusFrAlert(final Long userId,final Long alertId,final Long proposalStatusId,final String comment,final String approvedAmount){
		String result = null;
		try{
			result = (String)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					
					String statusRes = "success";
					if(proposalStatusId == 3l){
						 updateAlertStatusComment(alertId, proposalStatusId,comment, userId,null,null,null);
					}
					
					AlertComment alertComment = new AlertComment();
					alertComment.setAlertId(alertId);
					alertComment.setComments(comment);
					alertComment.setInsertedBy(userId);
					alertComment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					alertComment.setIsDeleted("N");
					alertComment = alertCommentDAO.save(alertComment);
					
					GovtProposalPropertyCategory govtProposalPropertyCategory = govtProposalPropertyCategoryDAO.getExistingStatusByAlertId(alertId);
					if(govtProposalPropertyCategory != null){
						govtProposalPropertyCategory.setGovtProposalStatusId(proposalStatusId);
						if(proposalStatusId == 3l)
							govtProposalPropertyCategory.setApprovedAmount(approvedAmount);
						
						govtProposalPropertyCategory.setUpdatedBy(userId);
						govtProposalPropertyCategory.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						govtProposalPropertyCategory = govtProposalPropertyCategoryDAO.save(govtProposalPropertyCategory);
					} 
					
					GovtProposalPropertyCategoryTracking govtProposalPropertyCategoryTracking = new GovtProposalPropertyCategoryTracking();
					govtProposalPropertyCategoryTracking.setAlertId(alertId);
					govtProposalPropertyCategoryTracking.setGovtProposalCategoryId(govtProposalPropertyCategory.getGovtProposalCategoryId());
					govtProposalPropertyCategoryTracking.setGovtProposalStatusId(proposalStatusId);
					govtProposalPropertyCategoryTracking.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					govtProposalPropertyCategoryTracking.setInsertedBy(userId);
					govtProposalPropertyCategoryTracking.setIsDeleted("N");
					govtProposalPropertyCategoryTracking.setAlertCommentId(alertComment.getAlertCommentId());
					govtProposalPropertyCategoryTrackingDAO.save(govtProposalPropertyCategoryTracking);
					
					
				return statusRes;
				}
			});
		}catch(Exception e){
			LOG.error("Error occured updateProposalStatusFrAlert() method of AlertManagementSystemService{}");
			return "failure";
		}
		return result;
	}

	public String alertDeptmentExistInLogin(Long alertId,Long userId){
		String result = "false";
		try{
			List<Object[]> userDeptList = govtAlertDepartmentLocationNewDAO.getDeptIdAndNameForUserAccessLevel(userId);
			Long alertDeptId = alertDAO.getGovtDepartmentIdForAlert(alertId);
			if(commonMethodsUtilService.isListOrSetValid(userDeptList)){
				for (Object[] objects : userDeptList) {
					Long userDeptId = commonMethodsUtilService.getLongValueForObject(objects[0]);
					if(userDeptId.longValue() == alertDeptId.longValue()){
						result = "true";
					}
				}
			}
		}catch(Exception e){
 			LOG.error("Error occured alertDeptmentExistInLogin() method of AlertManagementSystemService{}");
		}
		return result;
	}
	/*
	 * Santosh (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getDepartmentWiseProposalAlertCnt(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.util.List, java.lang.Long, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	public List<AlertVO> getDepartmentWiseProposalAlertCnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		LOG.info("Entered in getFinancialAssistanceAlertCntCategoryWise() method of AlertManagementSystemService{}");
		try{
				Date fromDate = null;
				Date toDate = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
			
			    List<AlertVO> finalAlertVOs = new ArrayList<AlertVO>();
			    prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
			
				if(deptIdList != null && deptIdList.size() == 0){
					deptIdList.add(0L);  
				}
				List<Long> levelValues = new ArrayList<Long>();    
				Long levelId = 0L;
				List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
				if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
					for(Object[] param : lvlValueAndLvlIdList){
						levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
						levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
					}
				}
			     
				Map<Long,AlertVO> deptartmentMap = new LinkedHashMap<Long, AlertVO>(0);
				
				List<Object[]> rtrnObjLst = alertAssignedOfficerNewDAO.getAlertProposalCategoryWiseAlertCnt(fromDate, toDate, printIdList, electronicIdList, deptIdList, levelId, levelValues, alertStatusIds, calCntrIdList, socialMediaTypeIds, alertSeverityIds, mondayGrievanceTypeIds, janmabhoomiTypeIds, specialGrievanceTypeIds, generalGrievanceTypeIds,"Department");
			    setDepartmentWiseProposalCAlertcnt(rtrnObjLst,deptartmentMap);
			
			 //Calculating over all data
			  if(deptartmentMap != null && deptartmentMap.size() >0){
				 for(Entry<Long,AlertVO> deptEntry:deptartmentMap.entrySet()){
					 if(deptEntry.getValue().getSubList2() != null && deptEntry.getValue().getSubList2().size() > 0){
						 for(AlertVO categoryVO:deptEntry.getValue().getSubList2()){
							  if(categoryVO.getSubList2() != null && categoryVO.getSubList2().size() > 0){
								  for(AlertVO statusVO:categoryVO.getSubList2()){
										 categoryVO.setAlertCnt(categoryVO.getAlertCnt()+statusVO.getAlertCnt());
										 categoryVO.setProposalAmount(categoryVO.getProposalAmount()+statusVO.getProposalAmount());
										 deptEntry.getValue().setProposalAmount(deptEntry.getValue().getProposalAmount()+statusVO.getProposalAmount()); 
										 deptEntry.getValue().setAlertCnt(deptEntry.getValue().getAlertCnt()+statusVO.getAlertCnt());
										 if(statusVO.getId().longValue() == 3l){//Proposal accepted amount
											 deptEntry.getValue().setApprovedAmount(deptEntry.getValue().getApprovedAmount()+statusVO.getProposalAmount());
											 
										 }
											 
								  }
							  }
						 }
					 }
				 }
			 }
		     if(deptartmentMap != null && deptartmentMap.size() > 0){
		    	 finalAlertVOs.addAll(deptartmentMap.values());
		    	 deptartmentMap.clear();
		     }
			return finalAlertVOs; 
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getFinancialAssistanceAlertCntCategoryWise() method of AlertManagementSystemService{}");
		}
		return null;
	}
	
	public void setDepartmentWiseProposalCAlertcnt(List<Object[]> objList,Map<Long,AlertVO> deptMap){
		try{
			if(objList != null && objList.size() > 0){
				for(Object[] param:objList){
					AlertVO deptVO = deptMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					 if(deptVO == null){
						 deptVO = new AlertVO();
						 deptVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						 deptVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						 deptVO.setSubList2(getCategoryList(objList));
						 deptMap.put(deptVO.getId(), deptVO);
					 }
					 Long categoryId = commonMethodsUtilService.getLongValueForObject(param[2]);
					 Long statusId = commonMethodsUtilService.getLongValueForObject(param[4]);
					 Long alertCnt = commonMethodsUtilService.getLongValueForObject(param[6]);
					 Long proposalAmount = commonMethodsUtilService.getLongValueForObject(param[7]);
					 AlertVO categoryVO = getMatchVO1(deptVO.getSubList2(),categoryId);
					 if(categoryVO != null && categoryVO.getSubList2() != null && categoryVO.getSubList2().size() > 0){
						  AlertVO statusVO = getMatchVO1(categoryVO.getSubList2(), statusId);
						   if(statusVO !=  null){
							   statusVO.setAlertCnt(alertCnt);
							   statusVO.setProposalAmount(proposalAmount);
						   }
					 }
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setDepartmentWiseProposalCAlertcnt() method of AlertManagementSystemService{}");
		}
	}
	public List<AlertVO> getCategoryList(List<Object[]> objList){
		List<AlertVO> categoryList = new ArrayList<AlertVO>();
		Map<Long,AlertVO> categoryMap = new TreeMap<Long, AlertVO>();
		try{
			if(objList != null && objList.size() > 0){
				for(Object[] param:objList){
					 AlertVO categoryVO = new AlertVO();
					 categoryVO.setId(commonMethodsUtilService.getLongValueForObject(param[2]));//categoryId
					 categoryVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));//categoryName
					 categoryVO.setSubList2(getAlertProposalStatusList(objList,"Department"));
					 categoryMap.put(categoryVO.getId(), categoryVO);
				}
			}
			if(categoryMap != null && categoryMap.size() > 0){
				categoryList.addAll(categoryMap.values());
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getCategoryList() method of AlertManagementSystemService{}");
		}
		return categoryList;
	}
	/*
	 * Santosh (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getOfficerWiseAlertCntBasedOnDepartmentScopeLevel(java.lang.String, java.lang.String, java.lang.Long, java.util.List, java.util.List, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.util.List, java.util.List, java.lang.Long, java.lang.Long, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List, java.util.List)
	 */
	public List<AlertCoreDashBoardVO> getOfficerWiseAlertCntBasedOnDepartmentScopeLevel(String fromDateStr, String toDateStr, Long stateId,List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
			Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,List<Long> calCntrIdList,List<Long> sublevels,Long filterParentScopeId,Long filterScopeValue,List<Long> socialMediaTypeIds,
			List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,
			List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds ){
			
		   try{
			
				Date fromDate = null;
				Date toDate = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
					fromDate = sdf.parse(fromDateStr);
					toDate = sdf.parse(toDateStr);
				}
				
				prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
				
				List<Long> levelValues = new ArrayList<Long>();    
				Long levelId = 0L;
				List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
				if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
					for(Object[] param : lvlValueAndLvlIdList){
						levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
						levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
					}
				}
				
				List<AlertCoreDashBoardVO> returnList = new ArrayList<AlertCoreDashBoardVO>();
				Map<Long,AlertCoreDashBoardVO> officerMap = new HashMap<Long, AlertCoreDashBoardVO>();
				
				if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() > 0){
					   List<Object[]> alertCntObjLst = null; 
						if(alertType != null && alertType.equalsIgnoreCase("alert")){
							alertCntObjLst = alertAssignedOfficerNewDAO.getOfficerWiseAlertCountBasedOnDepartmentScopeLevel(fromDate, toDate, stateId, electronicIdList, printIdList, levelId, levelValues, govtDepartmentId, parentGovtDepartmentScopeId, calCntrIdList, filterParentScopeId, filterScopeValue, socialMediaTypeIds, alertSeverityIds, alertStatusIds, mondayGrievanceTypeIds, janmabhoomiTypeIds, specialGrievanceTypeIds, generalGrievanceTypeIds);
						}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
							alertCntObjLst = govtAlertSubTaskDAO.getOfficerWiseSubTaskCountBasedOnDepartmentScopeLevel(fromDate, toDate, stateId, electronicIdList, printIdList, levelId, levelValues, govtDepartmentId, parentGovtDepartmentScopeId, calCntrIdList, filterParentScopeId, filterScopeValue, socialMediaTypeIds, alertSeverityIds, subTaskAlertStatusIds, mondayGrievanceTypeIds, janmabhoomiTypeIds, specialGrievanceTypeIds, generalGrievanceTypeIds);
						}
					setOfficerWiseAlertCnt(alertCntObjLst,officerMap);
				}
				
				if(officerMap.size() > 0){
					returnList.addAll(officerMap.values());
					officerMap.clear();
				}
				 if(returnList != null && returnList.size() > 0){
					if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("count")){
						if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
							Collections.sort(returnList, alertAscendingCountWiseSortingLvlWise);
						}else{
							Collections.sort(returnList, alertDescCountWiseSortingLvlWise);
						}
					}
					if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("name")){
						if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
							Collections.sort(returnList, alphabeticalAscSortLvlWise);
						}else{
							Collections.sort(returnList, alphabeticalDescendingSortLvlWise);
						}
					}
				}   
			  return returnList;
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Error occured getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewDynamicNew() method of AlertManagementSystemService",e);
			}
			return null;
	}
	public void setOfficerWiseAlertCnt(List<Object[]> objList,Map<Long,AlertCoreDashBoardVO> officerMap){
		try{
			if(objList != null && objList.size() > 0){
				for(Object[] param:objList){
					AlertCoreDashBoardVO officerVO = officerMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(officerVO == null){
						officerVO = new AlertCoreDashBoardVO();
						officerVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						officerVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						officerVO.setOfficerMobileNo(commonMethodsUtilService.getStringValueForObject(param[2]));
						officerVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(param[3]));
						officerVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[4]));
						officerVO.setDepartmentId(commonMethodsUtilService.getLongValueForObject(param[5]));
						officerVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[6]));
						officerVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[7]));
						String levelHeading = officerVO.getName()+" - "+officerVO.getDesignation()+" - "+officerVO.getOfficerMobileNo()+" - "+officerVO.getLocation();
						officerVO.setLevelHeading(levelHeading);
						officerVO.setSubList(getSttusLists(objList));
						officerMap.put(officerVO.getId(), officerVO);
					}
					Long alertStatusId = commonMethodsUtilService.getLongValueForObject(param[8]);
					AlertCoreDashBoardVO matchVO = getStatusMatchVO(officerVO.getSubList(),alertStatusId);
					 if(matchVO != null){
						 matchVO.setCount(commonMethodsUtilService.getLongValueForObject(param[11]));//alertCnt
					 }
				}
				//Calculating OverAll Alert officer wise
				if(officerMap != null && officerMap.size() > 0){
					for(Entry<Long,AlertCoreDashBoardVO> officerEntry:officerMap.entrySet()){
						if(officerEntry.getValue().getSubList() != null && officerEntry.getValue().getSubList().size() > 0){
							for(AlertCoreDashBoardVO statusVO:officerEntry.getValue().getSubList()){
								officerEntry.getValue().setTotalCount(officerEntry.getValue().getTotalCount()+statusVO.getCount());
							}
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setOfficerWiseAlertCnt() method of AlertManagementSystemService",e);
		}
	}
	public List<AlertCoreDashBoardVO> getSttusLists(List<Object[]> objList){
		try{
			Map<Long,AlertCoreDashBoardVO> statusMap = new  TreeMap<Long, AlertCoreDashBoardVO>();
			List<AlertCoreDashBoardVO> statusList = new ArrayList<AlertCoreDashBoardVO>();
			 if(objList != null && objList.size() > 0){
				 for(Object[] param:objList){
					 AlertCoreDashBoardVO statusVO = statusMap.get(commonMethodsUtilService.getLongValueForObject(param[8]));
					  if(statusVO == null){
						  statusVO = new AlertCoreDashBoardVO();
						  statusVO.setId(commonMethodsUtilService.getLongValueForObject(param[8]));
						  statusVO.setName(commonMethodsUtilService.getStringValueForObject(param[9]));
						  statusVO.setSevertyColor(commonMethodsUtilService.getStringValueForObject(param[10]));
						  statusMap.put(statusVO.getId(), statusVO);
					  }
				 }
				 statusList.addAll(statusMap.values());
			 }
			 return statusList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getSttusLists() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public AlertCoreDashBoardVO getStatusMatchVO(List<AlertCoreDashBoardVO> statusList,Long id){
		try{
			if(statusList == null || statusList.size() ==0)
				return null;
			for(AlertCoreDashBoardVO VO:statusList){
				if(VO.getId().equals(id))
				{
					return VO;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getStatusMatchVO() method of AlertManagementSystemService{}");
		}
		return null;
	}
	public String getOfficerMobilenNo(Long userId){
		String mobileNo = null;
		try {
			
			List<String> mobileNos =  govtDepartmentDesignationOfficerDetailsNewDAO.getOfficerMobilenNo(userId);
			if(mobileNos !=null && mobileNos.size()>0){
				mobileNo  =  mobileNos.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error occured getOfficerMobilenNo(-) method of AlertManagementSystemService{}");
		}
		return mobileNo;
	}
public String generatingAndSavingOTPDetails(String mobileNoStr){
		
		String status = null;
		try {
			String mobileNo=mobileNoStr.trim();
			if(mobileNoStr.length()>10)
				mobileNo = mobileNoStr.substring(mobileNoStr.length() - 10,mobileNoStr.length());
			
			List<Object[]> existingOTPDtls = amsOfficerOtpDetailsDAO.isExistOTPDetails(mobileNo,new DateUtilService().getCurrentDateAndTime());
			if(existingOTPDtls != null && existingOTPDtls.size()>0L){
			//	Object[] obj = existingOTPDtls.get(existingOTPDtls.size()-1);
				Object[] obj = existingOTPDtls.get(0);
				String otp = commonMethodsUtilService.getStringValueForObject(obj[0]);
				String referenceNo = String.valueOf(commonMethodsUtilService.getStringValueForObject(obj[1]));
				String dateStr = commonMethodsUtilService.getStringValueForObject(obj[2]);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = sdf.parse(dateStr);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				// next 15 min date time
				
				 long duration = (Calendar.getInstance().getTimeInMillis() - calendar.getTimeInMillis());
		    	 if(duration < 15 * 60 * 1000)// if duration less than 15 min 
		    	 {
		    		    calendar.add(Calendar.MINUTE, 15);
						String finalDateStr = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(calendar.getTime());
						String message = "your OTP is "+otp+" for Reference Id # " +referenceNo+" This OTP Validate upto Next "+finalDateStr+" .";
						String[] phoneNumbers = {mobileNo.toString()};
						//smsCountrySmsService.sendSmsFromAdmin(message, true, phoneNumbers);
						smsCountrySmsService.sendOTPSmsFromAdmin(message, true,phoneNumbers);
						status=referenceNo;
		    	 }
				else{
					status=getNewOTPDetails(mobileNo);
				}
				
			}else{
				status=getNewOTPDetails(mobileNo);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in generatingAndSavingOTPDetails in CoreDashboardCadreRegistrationService service", e);
			return "failure";
		}
		return status;
	}
	public String getNewOTPDetails(String mobileNo){
		AmsOfficerOtpDetails amsOfficerOtpDetails = null;
		try {
			RandomNumberGeneraion rnd = new RandomNumberGeneraion();
			int otpRand = 0;
			int refRand = 0;
			try {
				if(mobileNo != null && mobileNo.trim().length() ==10)
					amsOfficerOtpDetailsDAO.getAllOtpsForSameMobile(mobileNo.trim());
			} catch (Exception e) {
				LOG.error(" Error occured while updating getAllOtpsForSameMobile as invalid ",e);
			}
		
			while(otpRand <= 0 && refRand <= 0){
				 otpRand = rnd.randomGenerator(6);
				 refRand = rnd.randomGenerator(6);
			}
				String refeRenceNo = String.valueOf(refRand);
				String otpNum = String.valueOf(otpRand);
				String message = "your OTP is "+otpRand+" for Reference Id # " +refRand+" This OTP Validate for Next 15 mins.";
				String[] phoneNumbers = {mobileNo.toString()};
				//smsCountrySmsService.sendSmsFromAdmin(message, true, phoneNumbers);
				smsCountrySmsService.sendOTPSmsFromAdmin(message, true,phoneNumbers);
				amsOfficerOtpDetails = new AmsOfficerOtpDetails();
			
				amsOfficerOtpDetails.setMobileNo(mobileNo);
				amsOfficerOtpDetails.setOtpNo(otpNum);
				amsOfficerOtpDetails.setReferenceId(refeRenceNo);
				amsOfficerOtpDetails.setGeneratedTime(dateUtilService.getCurrentDateAndTime());
				amsOfficerOtpDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				amsOfficerOtpDetails.setIsValid("Y");
				
				amsOfficerOtpDetails = amsOfficerOtpDetailsDAO.save(amsOfficerOtpDetails);
				return refeRenceNo;
		} catch (Exception e) {
			LOG.error("Exception raised in generatingAndSavingOTPDetails in CoreDashboardCadreRegistrationService service", e);
			return "failure";
		}
	}
	public String getOfficerOtpStatus(Long userId,String otp){
		 String status = null;
			try {
				  Date currentTime = dateUtilService.getCurrentDateAndTime();
				  
				  String mobileNo = getOfficerMobilenNo(userId);
				  
	              List<Object[]> existingOTPDtls = amsOfficerOtpDetailsDAO.isExistOTPDetails(mobileNo,currentTime);
	  			if(existingOTPDtls != null && existingOTPDtls.size()>0L){
	  				Object[] obj = existingOTPDtls.get(existingOTPDtls.size()-1);
	  				Long tabDetsId = commonMethodsUtilService.getLongValueForObject(obj[3]);
	  				String originalotp = commonMethodsUtilService.getStringValueForObject(obj[0]);
	  				
					if(originalotp.toString().trim().equalsIgnoreCase(otp.toString().trim()) && tabDetsId != null && tabDetsId.longValue() > 0l){
						AmsOfficerOtpDetails amsOfficerOtpDetails = amsOfficerOtpDetailsDAO.get(tabDetsId);
						amsOfficerOtpDetails.setIsValid("N");
						amsOfficerOtpDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						amsOfficerOtpDetails = amsOfficerOtpDetailsDAO.save(amsOfficerOtpDetails);
						
						status = "success";
					}
					else
						status = "failure";
	  			}
	  			else
	  				status = "failure";
	  			
			} catch (Exception e) {
				status = "failure";
				LOG.error("Exception Occured in checkOTPDetails() in CodeDashboardCadreRegistrationService class.",e);
			}
			return status;
	     }
	
	public List<AmsKeyValueVO> getDeptListForUserForAms(Long userId){   
		try{
			List<AmsKeyValueVO> resultList = new ArrayList<AmsKeyValueVO>();
			List<Object[]> deptList = govtAlertDepartmentLocationNewDAO.getDeptIdAndNameForUserAccessLevel(userId);
			setDataToKeyValueList(deptList,resultList);
			return resultList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getDeptListForUser() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public List<AmsKeyValueVO> getSocialMediaTypeListForAms(){   
		try{
			List<AmsKeyValueVO> resultList = new ArrayList<AmsKeyValueVO>();
			List<Object[]> rtrnObjList = alertDAO.getAllSocialMediaType();
			setDataToKeyValueList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getSocialMediaTypeList() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public List<AmsKeyValueVO> getAlertCallCenterTypeForAms(){   
		try{
			List<AmsKeyValueVO> resultList = new ArrayList<AmsKeyValueVO>();
			List<Object[]> rtrnObjList = alertDAO.getAlertCallCenterType();
			setDataToKeyValueList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getAlertCallCenterType() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public List<AmsKeyValueVO> getMondayGrievanceTypeListForAms(){   
		try{
			List<AmsKeyValueVO> resultList = new ArrayList<AmsKeyValueVO>();
			List<Object[]> rtrnObjList = alertDAO.getMondayGrievanceTypeList();
			setDataToKeyValueList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getMondayGrievanceTypeList() method of AlertManagementSystemService",e);
		}
		return null;
	}
	
	public List<AmsKeyValueVO> getJanmabhoomiTypeListForAms(){   
		try{
			List<AmsKeyValueVO> resultList = new ArrayList<AmsKeyValueVO>();
			List<Object[]> rtrnObjList = alertDAO.getJanmabhoomiTypeList();
			setDataToKeyValueList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getJanmabhoomiTypeList() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public List<AmsKeyValueVO> getSpecialGrievanceTypeListForAms(){   
		try{
			List<AmsKeyValueVO> resultList = new ArrayList<AmsKeyValueVO>();
			List<Object[]> rtrnObjList = alertDAO.getSpecialGrievanceTypeList();
			setDataToKeyValueList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getSpecialGrievanceTypeList() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public List<AmsKeyValueVO> getGeneralGrievanceTypeListForAms(){   
		try{
			List<AmsKeyValueVO> resultList = new ArrayList<AmsKeyValueVO>();
			List<Object[]> rtrnObjList = alertDAO.getGeneralGrievanceTypeList();
			setDataToKeyValueList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getGeneralGrievanceTypeList() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public void setDataToKeyValueList(List<Object[]> objList,List<AmsKeyValueVO> resultList){
    	try{
    		if(objList != null && objList.size() > 0){  
				for(Object[] param : objList){
					AmsKeyValueVO	amsKeyValueVO = new AmsKeyValueVO();
					amsKeyValueVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					amsKeyValueVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					resultList.add(amsKeyValueVO);
				}
			}
    	}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setDataToKeyValueList() method of AlertManagementSystemService",e);
    	}
    }
	
	public DistrictOfficeViewAlertVO getAmsAppAlertsBasicCounts(Long userId,String fromDateStr,String toDateStr,List<Long> printIdList, 
			List<Long> electronicIdList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,
			List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,
			List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,List<Long> subTaskAlertStatusIds){
  		
  		DistrictOfficeViewAlertVO returnVO = new DistrictOfficeViewAlertVO();
  		Long subOrdinateCnt = 0l;
  		try{
  			//getDistrictOfficerAlertsCountView
  			
  			returnVO =  getDistrictOfficerAlertsCountView(userId,fromDateStr,toDateStr,printIdList, electronicIdList,calCntrIdList,socialMediaTypeIds,alertSeverityIds,
  					alertStatusIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
  			
  			
  			returnVO.setSubOrdinateCount(subOrdinateCnt);
  			
  			List<AmsKeyValueVO> userAccessedDepartmentsLst = getUserAccessedDepartmentsForAMS(userId);
  			
  			returnVO.setUserAccessLst(userAccessedDepartmentsLst);
  			/*Date fromDate = null;
  			Date toDate = null;
  			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
  				fromDate = sdf.parse(fromDateStr);
  				toDate = sdf.parse(toDateStr);
  			}
  			List<Long> levelValues = new ArrayList<Long>();    
  			Long levelId = 0L;
  			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
  			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
  				for(Object[] param : lvlValueAndLvlIdList){
  					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
  					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
  				}
  			}
  			prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
  			
  			List<Object[]> list1 = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOffrDetlsIdAndGovtOfcrId(userId,levelValues,levelId);
  			
  			List<Long> govtDepDesigOffcrIds = new ArrayList<Long>(0);
  			List<Long> govtOffcrIds =  new ArrayList<Long>(0);
  			if(commonMethodsUtilService.isListOrSetValid(list1)){
  				for( Object[]  obj :list1){
  					govtDepDesigOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[0]));
  					govtOffcrIds.add(commonMethodsUtilService.getLongValueForObject(obj[1]));
  					returnVO.setGovtDeptDesigOffcrIds(govtDepDesigOffcrIds);
  					returnVO.setGovtOfficerIds(govtOffcrIds);
  					returnVO.setId(commonMethodsUtilService.getLongValueForObject(obj[1]));//officerId
  					returnVO.setName(commonMethodsUtilService.getStringValueForObject(obj[2]));//officerName
  					returnVO.setDepartmentId(commonMethodsUtilService.getLongValueForObject(obj[3]));//depId
  					returnVO.setDeptName(commonMethodsUtilService.getStringValueForObject(obj[4]));//deptName
  					returnVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(obj[5]));//designationId
  					returnVO.setDesigName(commonMethodsUtilService.getStringValueForObject(obj[6]));//designationName
  					
  				}
  			}
  			// My alerts view
  			List<Object[]> myAlertsTodayList = null;
  			List<Object[]> myAlertsCompletedList = null;
  			List<Object[]> myAlertsStatusList = null;
  			if(govtDepDesigOffcrIds != null && govtDepDesigOffcrIds.size()>0 && govtOffcrIds != null && govtOffcrIds.size() >0){
  				myAlertsTodayList = alertAssignedOfficerNewDAO.getDistrictOfficerMyAlertsCountView(govtDepDesigOffcrIds,govtOffcrIds,"today",new DateUtilService().getCurrentDateAndTime(),new DateUtilService().getCurrentDateAndTime(),printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
  				myAlertsCompletedList = alertAssignedOfficerNewDAO.getDistrictOfficerMyAlertsCountView(govtDepDesigOffcrIds,govtOffcrIds,"completed",fromDate,toDate,printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
  				myAlertsStatusList = alertAssignedOfficerNewDAO.getDistrictOfficerMyAlertsStatusWiseDetails(govtDepDesigOffcrIds, govtOffcrIds,fromDate,toDate,printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
  			}
  			setIASOfficerStatusWiseCountView(myAlertsTodayList,myAlertsCompletedList,myAlertsStatusList,returnVO,"MyAlerts");*/
  			
  		}catch(Exception e){
  			e.printStackTrace();
  			LOG.error("Exception Occured in AlertManagementSystemService of  getAmsAppAlertsBasicCounts() ", e);
  		}
  		return returnVO;
  	}

	public ResultStatus uploadDocumentsForRejoinderStatus(final StringBuilder pathBuilder,final Long alertId,final Long userId){

		final ResultStatus resultStatus = new ResultStatus();
		try {
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
		/*String folderName = folderCreationForAlertsAttachmentNew();
		CustomReport customReport = null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		 int year = calendar.get(Calendar.YEAR);
		 int month = calendar.get(Calendar.MONTH);
		// int day = calendar.get(Calendar.DAY_OF_MONTH);
		 int temp = month+1;
		 String monthText = getMonthForInt(temp);
		 SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_VALUE);
		 String dateStr = sdf.format(new Date());
		 String yearStr = String.valueOf(year);
		 
		 StringBuilder str ;
		 
		 if(mapfiles != null && mapfiles.size() > 0){
			// AlertAssignedOfficerNew aaon = alertAssignedOfficerNewDAO.getModelForAlert(alertId).get(0);
			 for (Map.Entry<File, String> entry : mapfiles.entrySet()){
				 str = new StringBuilder();
				 Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
				 String destPath = folderName+"/"+randomNumber+"."+entry.getValue();
				 StringBuilder pathBuilder = new StringBuilder();
				  pathBuilder.append("alerts_attachments/").append(yearStr).append("/").append(dateStr).append("/").append(randomNumber).append(".").append(entry.getValue());
				 str.append(randomNumber).append(".").append(entry.getValue());
				String fileCpyStts = activityService.copyFile(entry.getKey().getAbsolutePath(),destPath);
				 
					if(fileCpyStts.equalsIgnoreCase("error")){
						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
						LOG.error(" Exception Raise in copying file");
						throw new ArithmeticException();
					}*/
					
					AlertDepartmentDocumentNew addn = new AlertDepartmentDocumentNew();
					addn.setDocument(pathBuilder.toString());
					addn.setInsertedBy(userId);
					addn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					addn = alertDepartmentDocumentNewDAO.save(addn);
					
					List<AlertAssignedOfficerTrackingNew> alertAssignedOfficerTrackingNewList = alertAssignedOfficerTrackingNewDAO.getExistRecordFrRejinderStatus(alertId);
					if(commonMethodsUtilService.isListOrSetValid(alertAssignedOfficerTrackingNewList)){
						AlertAssignedOfficerTrackingNew alertAssignedOfficerTrackingNew =  alertAssignedOfficerTrackingNewList.get(0);
						alertAssignedOfficerTrackingNew.setAlertDepartmentDocumentId(addn.getAlertDepartmentDocumentId());
					}
			 	//}
		 	//}
		
		 resultStatus.setExceptionMsg("success");
				}
			});
		}catch (Exception e) {
			resultStatus.setExceptionMsg("failure");
			LOG.error(" Exception Occured in uploadDocumentsForRejoinderStatus() method, Exception - ",e);
		}
		return resultStatus;
	}
	public List<AlertVO> getRejoinderDocumentsForAlert(Long alertId){
  		List<AlertVO> typeList = new ArrayList<AlertVO>(0);
  		try {
  			//List<AlertVO> typeList = new ArrayList<AlertVO>();
  			//Map<Long,AlertVO> rejinderType
  			List<Object[]> rejinderTypeList = govtRejoinderActionDAO.getAllActions();
  			if(commonMethodsUtilService.isListOrSetValid(rejinderTypeList)){
  				for (Object[] objects : rejinderTypeList) {
					AlertVO vo = new AlertVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					typeList.add(vo);
				}
  			}
			List<Object[]> objList = alertAssignedOfficerTrackingNewDAO.getRejoinderDocumentsForAlert(alertId);
			if(objList != null && objList.size() > 0){
				for (Object[] objects : objList) {
					
					AlertVO vo = getMatchVO1(typeList,commonMethodsUtilService.getLongValueForObject(objects[4]));
					if(vo != null){
						AlertVO subvo = new AlertVO();
						subvo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						subvo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
						subvo.setDate1(commonMethodsUtilService.getStringValueForObject(objects[3]));
						vo.getSubList1().add(subvo);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured getDocumentsForAlert() method of AlertManagementSystemService",e);
		}
  		return typeList;
  	}
	public List<AmsKeyValueVO> getAlertSeverityForAms(){   
		try{
			
			List<AmsKeyValueVO> resultList = new ArrayList<AmsKeyValueVO>();
			List<Object[]> rtrnObjList = alertSeverityDAO.getAlertSeverity();
			setDataToKeyValueList(rtrnObjList,resultList);			
			
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getAlertSeverityForAms() method of AlertManagementSystemService",e);
		}
		return null;
	}

	public List<AmsKeyValueVO> getAlertStatusForAms(){   
		try{
			
			List<AmsKeyValueVO> resultList = new ArrayList<AmsKeyValueVO>();
			List<Object[]> rtrnObjList = alertAssignedOfficerNewDAO.getDataAvailableAlertStatus();
			setDataToKeyValueList(rtrnObjList,resultList);
					
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getAlertStatusForAms() method of AlertManagementSystemService",e);
		}
		return null;
	}
	public List<AmsKeyValueVO> getSubTaskAlertStatusForAms(){   
		try{
			
			List<AmsKeyValueVO> resultList = new ArrayList<AmsKeyValueVO>();
			List<Object[]> rtrnObjList = govtAlertSubTaskDAO.getDataAvailableSubTaskStatus();
			setDataToKeyValueList(rtrnObjList,resultList);
			
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getSubTaskAlertStatusForAms() method of AlertManagementSystemService",e);
		}
		return null;
	}
	
	public List<AmsDataVO> getOfficerAlertDetails(Long userId,String countType,String alertType,
			List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList,String fromDateStr,String toDateStr,List<Long>
			socialMediaTypeIds,List<Long> alertSeverityIds, List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,
			List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		
		List<AmsDataVO> finalVoList = new ArrayList<AmsDataVO>(0);
		
		try {
			
			List<Long> govtDeptDesgOffIds = new ArrayList<Long>(0);  
			Set<Long> govtOffrcrSetIds = new HashSet<Long>(0);  
			List<Long> govtOffrcrIds = new ArrayList<Long>(0);
			
			List<Object[]> designationList = govtDepartmentDesignationOfficerDetailsNewDAO.getOfficerDesingationDetails(userId);
			if(designationList != null && designationList.size() > 0){
				for(Object[] param : designationList){
					govtDeptDesgOffIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
					govtOffrcrSetIds.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			
			if(govtOffrcrSetIds !=null){
				govtOffrcrIds.addAll(govtOffrcrSetIds);
			}
			
			

			Date fromDate = null;
      		Date toDate = null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
            	fromDate = sdf.parse(fromDateStr);
            	toDate = sdf.parse(toDateStr);
            }
		    List<Long> alertIdList = null;
		
			prepareRequiredParameter(printIdsList,electronicIdsList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Setting Parameter
			
			if(alertType != null && alertType.equalsIgnoreCase("alert")){
				 alertIdList = alertAssignedOfficerNewDAO.getDistrictOfficerAlertsIds(govtDeptDesgOffIds,govtOffrcrIds,countType,printIdsList,electronicIdsList,calCntrIdList,fromDate,toDate,socialMediaTypeIds,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			}else {
				 	alertIdList = govtAlertSubTaskDAO.getDistrictOfficerSubTasksAlertIds(govtDeptDesgOffIds,govtOffrcrIds,countType,alertType,printIdsList,electronicIdsList,calCntrIdList,fromDate,toDate,socialMediaTypeIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			}
			if(alertIdList != null && alertIdList.size() > 0){
				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIdList));
				setAlertDtlsForAms(finalVoList, list); 
			}
			setSubListCountForAms(finalVoList, alertIdList);
		
			
			/*getDistrictOfficerAlertDetails(govtDeptDesgOffIds,govtOffrcrIds,countType,alertType,
					printIdsList,  electronicIdsList, calCntrIdList,fromDateStr,toDateStr,socialMediaTypeIds,
					alertSeverityIds, alertStatusIds,subTaskAlertStatusIds,
					mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);*/
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(" Exception Occured in getOfficerAlertDetails() method, Exception - ",e);
		}
		return finalVoList;
		
	}
	
	public void setAlertDtlsForAms(List<AmsDataVO> amsDataVoList, List<Object[]> alertList){
		try{
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date today = dateUtilService.getCurrentDateAndTime();
			String td = myFormat.format(today);
			Long dist = 0l;
			Long statusId = 0L;  
			AmsDataVO amsDataVO = null;  
			String alertSource = "";
			if(alertList != null && alertList.size() > 0){  
				for(Object[] param : alertList ){
					amsDataVO = new AmsDataVO();
					amsDataVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					amsDataVO.setCreatedDate(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10));
					amsDataVO.setUpdatedDate(commonMethodsUtilService.getStringValueForObject(param[2]).substring(0, 10));
					amsDataVO.setStatusId(commonMethodsUtilService.getLongValueForObject(param[3]));
					amsDataVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[4]));
					amsDataVO.setSevertyColor(commonMethodsUtilService.getStringValueForObject(param[24]));
					amsDataVO.setStatusColor(commonMethodsUtilService.getStringValueForObject(param[25]));
					if(param.length > 26){
						amsDataVO.setProblem(commonMethodsUtilService.getStringValueForObject(param[26]));
						amsDataVO.setRelatedTo(commonMethodsUtilService.getStringValueForObject(param[27]));
					}
					statusId = commonMethodsUtilService.getLongValueForObject(param[3]);
					if(param[1] != null && param[2] != null){
						if(statusId == 4L || statusId == 5L || statusId == 6L || statusId == 7L){
							dist = dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10),commonMethodsUtilService.getStringValueForObject(param[2]).substring(0, 10));
						}else{
							dist = dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10),td);
						}  
						amsDataVO.setInterval(dist);
					}
					amsDataVO.setAlertLevel(commonMethodsUtilService.getStringValueForObject(param[8]));
					amsDataVO.setTitle(commonMethodsUtilService.getStringValueForObject(param[9]));    
					
					if(param[23] != null){
						amsDataVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[23]));	
					}else if(param[22] != null){
						amsDataVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[22]));	
					}else if(param[10] != null){
						amsDataVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[10]));	
					}else if(param[11] != null){
						amsDataVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[11]));	
					}else if(param[20] != null){
						amsDataVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[20]));
					}

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
						amsDataVO.setSource(alertSource);
					 
						amsDataVoList.add(amsDataVO);
					
				}  
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(" Exception Occured in setAlertDtlsForAms() method, Exception - ",e);
		}
	}
	public void setSubListCountForAms(List<AmsDataVO> finalVoList,List<Long> alertIds){
		 try{
			 List<Object[]> subtaskCountList = null;
				if(alertIds != null && alertIds.size() > 0){
					subtaskCountList = govtAlertSubTaskDAO.getSubTaskCount(alertIds);
				}
				//create a map from alertId and subtask count.
				Map<Long,Long> alertIdAndSubTaskCountMap = new HashMap<Long,Long>();
				if(subtaskCountList != null && subtaskCountList.size() > 0){
					for(Object[] param : subtaskCountList){
						alertIdAndSubTaskCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
					}
				}
				if(finalVoList != null && finalVoList.size() > 0){
					for(AmsDataVO AmsDataVO : finalVoList){
						if(alertIdAndSubTaskCountMap.get(AmsDataVO.getId()) != null){
							AmsDataVO.setSubTaskCount(alertIdAndSubTaskCountMap.get(AmsDataVO.getId()));
						}
					}
				}
		 }catch (Exception e) {
				LOG.error(" Exception Occured in setSubListCountForAms() method, Exception - ",e);
		}
		
	 }
	public AmsVO getAlertDetailsInfoForAms(AmsAppLoginVO keyVo){
		AmsVO returnVo = new AmsVO();
		 try{
			 Long alertId = keyVo.getAlertId();
			 List<Object[]> list = alertAssignedOfficerNewDAO.getAssignedOfficersDetails(alertId);
			 if(list != null && !list.isEmpty()){
    				for (Object[] obj : list) {
    					AmsVO vo = new AmsVO();
    					
    					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
    					vo.setName(obj[1] != null ? obj[1].toString():"");
    					vo.setDepartment(obj[2] != null ? obj[2].toString():"");
    					vo.setMobileNo(obj[3] != null ? obj[3].toString():"");
    					vo.setDesignation(obj[4] != null ? obj[4].toString():"");
    					vo.setSource(obj[5] !=null ?  obj[5].toString():"");
    					
    					returnVo.getAssignOfficerDeatilsList().add(vo);//For alertAssigned officer details
    				}
    			}
			 List<AmsVO> alertsData = getAlertsDataForSms(alertId);
			 returnVo.getAlertsDataList().addAll(alertsData);//getAlertsData
			 
			//getStatusCompletionInfo
			 List<AmsAppVO> statusInfoLst = getStatusCompletionInfoNewForAms(alertId,keyVo.getUserId());
			 returnVo.getStatusComplteInfoLst().addAll(statusInfoLst);
			 
			 List<List<AmsTrackingVO>> historyLst = viewAlertHistoryNewForAms(alertId,keyVo.getTask());
			 returnVo.getViewHistroyLst().addAll(historyLst);//viewAlertHistory
			 

			 String category = null;
					Long govtDeptId = alertDAO.getGovtDepartmentIdForAlert(alertId);
					if(govtDeptId != null && govtDeptId.longValue() > 0l){
						Long cnpDeptId = govtDepartmentDAO.getCNPGovtDepartmentIdForGovtDepartment(govtDeptId);
						if(cnpDeptId != null && cnpDeptId.longValue() > 0l){
							List<String> categoryList = alertCandidateDAO.getCategoryListForAlertAndDepartment(alertId, cnpDeptId);
							if(categoryList != null && !categoryList.isEmpty())
								category = categoryList.get(0);
						}
					}
			returnVo.setCategory(category);
		 }catch (Exception e) {
				LOG.error(" Exception Occured in getAlertDetailsInfoForAms() method, Exception - ",e);
		}
		return returnVo;
		
	 }
	public List<AmsAppVO>  getStatusCompletionInfoNewForAms(Long alertId,Long userId){
          List<AmsAppVO> finalList = new ArrayList<AmsAppVO>();
          try {
            
            Alert alert  = alertDAO.get(alertId);
            
            	Long alertPresentStatusId = alertDAO.getPresentStatusOfAlert(alertId);
            
            String userType = null;
            //whether this alert is belongs to same logedin user or not.
            //get all govt dept desig off ids
            List<Long> govtDeptDesigOfficerIdList = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOfficerIdListByUserId(userId);
            
            //get govt dept desig off id by alertId
            Long govtDeptDesigOfficerId = alertAssignedOfficerNewDAO.getGovtDeptDesigOfficerIdListByUserId(alertId);
            
            //whether this alert is belongs to just subordinate or not.
            
            //get all govt dept desig ids
            List<Long> govtDeptDesigIdList = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigIdListByUserId(userId);
            //get govt dept desig id by alertId
            Long govtDeptDesigId2 = alertAssignedOfficerNewDAO.getGovtDeptDesigIdListByUserId(alertId); 
            
            //now check
            List<Object[]> list2 = null;
            if(govtDeptDesigIdList != null && govtDeptDesigIdList.size() > 0 && govtDeptDesigId2 != null && govtDeptDesigId2.longValue() > 0L){
              list2 = govtDepartmentDesignationHierarchyDAO.getChildDesigDataNew(govtDeptDesigIdList,govtDeptDesigId2);
            }
            
            //to check same level designation.
            //by alert id take scope.
            Long govtDeptScopeIdForAlert = alertAssignedOfficerNewDAO.getGovtDeptScopeIdForAlert(alertId);
            //by user Id take scope.
            List<Long> govtDeptScopeIdsForUserId = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptScopeIdsForUserId(userId);
            
            //to wheck whether he/she is an admin or not.
            //Long userCount = govtDepartmentDesignationOfficerNewDAO.getUserIdCount(userId);
            List<String> entitlement = userGroupRelationDAO.getUserIdCount(userId);
            String entlmnt = "";
            if(entitlement != null && entitlement.size() > 0){
          	 entlmnt = entitlement.get(0);
            }
            String userStatus = "";
            if(entlmnt != null && !entlmnt.isEmpty() && entlmnt.equalsIgnoreCase("GOVT_DEPARTMENT_ADMIN_USER_GROUP_ENTITLEMENT_NEW")){
              userStatus = "admin";
            }else{
              userStatus = "officer";    
            }
            if(govtDeptDesigOfficerIdList != null && govtDeptDesigOfficerId != null && govtDeptDesigOfficerIdList.size() > 0 && govtDeptDesigOfficerIdList.contains(govtDeptDesigOfficerId)){
              userType ="own";
              
              List<Object[]> objList = alertDepartmentStatusDAO.getAlertGovtDepartmentStatus(alert.getGovtDepartmentId());
              if(objList != null && objList.size() > 0){
                for (Object[] objects : objList) {
                	AmsAppVO VO = new AmsAppVO();
                  	VO.setId((Long)objects[0]);
                      VO.setName(objects[1].toString());
                      VO.setDateStr(objects[2] != null ? objects[2].toString():"");
                      if(alert.getAlertStatusId().longValue() != VO.getId().longValue())
                      finalList.add(VO);
                }
              }
              
            }else if(list2 != null && list2.size() > 0){ 
              
              userType = "subUser";
              
              if(alert.getAlertStatusId().longValue() == 4l || alert.getAlertStatusId().longValue() == 11l || alert.getAlertStatusId().longValue() == 12l){//Completed Status  
                //userType = "subUserStatus";
                List<Object[]> listObj = alertStatusDAO.getAlertStatusInfoForReOpen();
                  if(listObj !=null && listObj.size()>0){
                    for (Object[] objects : listObj) {
                    	AmsAppVO vo = new AmsAppVO();
		                    vo.setId((Long)objects[0]);
		                    vo.setName(objects[1].toString());
		                    vo.setDateStr(objects[2] != null? objects[2].toString():null);
		                    if(alert.getAlertStatusId().longValue() != vo.getId().longValue())
		                    	finalList.add(vo);
                    }
                  }
              }else{
            	  AmsAppVO vo = new AmsAppVO();              
                  finalList.add(vo);                  
              }  
            }else if(govtDeptScopeIdsForUserId != null && govtDeptScopeIdsForUserId.size() > 0 && govtDeptScopeIdForAlert != null && govtDeptScopeIdsForUserId.contains(govtDeptScopeIdForAlert)){
              userType = "same";
              AmsAppVO vo = new AmsAppVO();                
              finalList.add(vo);
            }
            else{
              userType = "other";              
              AmsAppVO vo = new AmsAppVO();              
              finalList.add(vo);
            }
            
            List<String> entitlements = userGroupRelationDAO.getEntitlements(userId);
            
            if(finalList != null && finalList.size() > 0){
            AmsAppVO vo  =finalList.get(0);
          	vo.setApplicationStatus(userType+" - "+alertPresentStatusId);
          	vo.setUserStatus(userStatus);
          	if(entitlements != null && entitlements.size() > 0l){
          		for (String string : entitlements) {
          			if(string.trim().equalsIgnoreCase("GOVT_DEPARTMENT_ENTITLEMENT_NEW")){
          				vo.setPositionName("true");
          			}
				}
          	}
          	List<Object[]> list = alertCallerRelationDAO.getAlertCallerDetailsByAlert(alertId);
          	if(list != null && !list.isEmpty()){
          		for (Object[] obj : list) {
          			AmsAppVO callervo = new AmsAppVO();
					callervo.setUserType(obj[1] != null ? obj[1].toString():"");
					callervo.setCallerName(obj[3] != null ? obj[3].toString():"");
					callervo.setMobileNo(obj[4] != null ? obj[4].toString():"");
					callervo.setStatus(obj[5] != null ? obj[5].toString():"");
					vo.getIdnameList().add(callervo);
				}
          	}
          	//vo.setUserType(alert.getAlertCallerType() != null ? alert.getAlertCallerType().getCallerType():"");// citizen/chief minister...etc
          	//vo.setCallerName(alert.getAlertCaller() != null ? alert.getAlertCaller().getCallerName():"");
          	//vo.setMobileNo(alert.getAlertCaller() != null ? alert.getAlertCaller().getMobileNo():"");
              List<String> dueDatesList = alertAssignedOfficerTrackingNewDAO.getAlertDueDate(alertId);
              if(commonMethodsUtilService.isListOrSetValid(dueDatesList))
              	vo.setDueDateStr(dueDatesList.get(0).toString());
            }
        
      } catch (Exception e) {
        e.printStackTrace();
        LOG.error("Error occured getStatusCompletionInfoNew() method of AlertManagementSystemService",e);
      }
          return finalList;
}
	public List<List<AmsTrackingVO>> viewAlertHistoryNewForAms(Long alertId, String task){
		try{
			AmsTrackingVO alertTrackingVO = null;
			AmsTrackingVO userDetails = null;
			List<AmsTrackingVO> innerList = null;
			List<List<AmsTrackingVO>> finalList = new ArrayList<List<AmsTrackingVO>>();
			Map<Long,AmsTrackingVO> userMap = new HashMap<Long, AmsTrackingVO>(0);
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss a");
			List<Object[]> trackingList = null;
			if(task != null && !task.trim().isEmpty() && task.trim().length() > 0 && task.trim().equalsIgnoreCase("task")){
				trackingList = alertAssignedOfficerTrackingNewDAO.getAlertTrackingDtls(alertId);
			}else{
				trackingList = govtOfficerSubTaskTrackingDAO.getSubTaskAlertTrackingDtls(alertId);
			}
			
			//collect userid
			Set<Long> userIdList = new HashSet<Long>(); 
			if(trackingList != null && trackingList.size() > 0){
				for(Object[] param : trackingList){
					userIdList.add(commonMethodsUtilService.getLongValueForObject(param[13]));
				}
			}
			List<Object[]> userIdAndNameList =null;
			if(userIdList != null && userIdList.size()>0){
				userIdAndNameList = userDAO.getuserIdAndNameList(userIdList);
			}
			//create a map for userId and name
			Map<Long,String> idAndNameMap = new HashMap<Long,String>();
			if(userIdAndNameList != null && userIdAndNameList.size() > 0){
				for(Object[] param : userIdAndNameList){
					idAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
			//check user is admin or not
			Map<Long,String> userIdAndStatusMap = new HashMap<Long,String>();
			
			if(trackingList != null && trackingList.size() > 0){
				for(Object[] param : trackingList){
					Long loginUserId = commonMethodsUtilService.getLongValueForObject(param[13]);
					Long deptId = commonMethodsUtilService.getLongValueForObject(param[14]);
					alertTrackingVO = new AmsTrackingVO();
					AmsTrackingVO vo = new AmsTrackingVO();
					List<Object[]> userDtls = govtDepartmentDesignationOfficerDetailsDAO.getLocationInfoForUser(loginUserId,deptId);
					List<String> deptList = govtDepartmentDesignationOfficerDetailsDAO.getAssignedDeptList(loginUserId);
					if(userDtls != null && userDtls.size() > 0){
						userIdAndStatusMap.put(loginUserId, "officer");
						String deptName = "";
						/*if(deptList != null && deptList.size() > 0){
							if(deptList.size() > 1){
								deptName = "N/A";  
							}else{
								deptName = deptList.get(0).trim();
							}
						}*/
						
						if(deptList != null && deptList.size() > 0){
							StringBuilder strBuild = new StringBuilder();
							if(deptList.size() > 3){
								for(int i=1 ; i <= 3 ; i++){
									strBuild.append(deptList.get(i));
									strBuild.append(",");
								}
								strBuild.append("...");
							}else{
								for(String str : deptList){
									strBuild.append(str);
									strBuild.append(",");
								}
							}
							deptName = strBuild.toString();
						}
						
						String designationStr = commonMethodsUtilService.getStringValueForObject(userDtls.get(0)[0]);
						String officerName = idAndNameMap.get(commonMethodsUtilService.getLongValueForObject(param[13]));
						String mobileNo = commonMethodsUtilService.getStringValueForObject(userDtls.get(0)[2]);
						String location = commonMethodsUtilService.getStringValueForObject(userDtls.get(0)[3]);
						vo.setDeptName(deptName);
						vo.setDesignation(designationStr);
						vo.setUserName(officerName);
						vo.setMobileNO(mobileNo);
						vo.setLocation(location);
						userMap.put(loginUserId, vo);
					}else{
						String officerName = idAndNameMap.get(commonMethodsUtilService.getLongValueForObject(param[13]));
						userIdAndStatusMap.put(loginUserId, "admin");
						vo.setUserName(officerName);
						vo.setDesignation("N/A");
						userMap.put(loginUserId, vo);
					}
					
				}
			}
			
			//create date wise map
			Map<String,List<Object[]>> dateAndTrackingDtlsMap = new LinkedHashMap<String,List<Object[]>>();
			List<Object[]> arrayList = null;
			
			if(trackingList != null && trackingList.size() > 0){
				for(Object[] param : trackingList){
					arrayList = dateAndTrackingDtlsMap.get(commonMethodsUtilService.getStringValueForObject(param[12]).trim().substring(0, 10));
					if(arrayList == null){
						arrayList = new ArrayList<Object[]>();
						arrayList.add(param);
						dateAndTrackingDtlsMap.put(commonMethodsUtilService.getStringValueForObject(param[12]).trim().substring(0, 10),arrayList);
					}else{
						arrayList.add(param);
					}
				}
				
			}
			
			//create list of vo for ui.
			if(dateAndTrackingDtlsMap != null && dateAndTrackingDtlsMap.size() > 0){
				for(Entry<String,List<Object[]>> entry : dateAndTrackingDtlsMap.entrySet()){
					innerList = new ArrayList<AmsTrackingVO>();
					if(entry.getValue() != null && entry.getValue().size() > 0){
						for(Object[] param : entry.getValue()){
							alertTrackingVO = new AmsTrackingVO();
							alertTrackingVO.setActionType(commonMethodsUtilService.getStringValueForObject(param[2]));
							alertTrackingVO.setTrackingDate(commonMethodsUtilService.getStringValueForObject(param[12]).trim().substring(0, 10));
							alertTrackingVO.setTrackingTime(sdf1.format(sdf.parse(commonMethodsUtilService.getStringValueForObject(param[12]).trim().substring(11, 19))));
							alertTrackingVO.setComment(commonMethodsUtilService.getStringValueForObject(param[4]));//  
							alertTrackingVO.setDocument(commonMethodsUtilService.getStringValueForObject(param[6]));
							alertTrackingVO.setDueDate(commonMethodsUtilService.getStringValueForObject(param[7]));
							alertTrackingVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[9]));
							alertTrackingVO.setRejinderStatus(commonMethodsUtilService.getStringValueForObject(param[20]));
							if(alertTrackingVO.getStatus().trim().equalsIgnoreCase("Proposal")){
								List<Object[]> statusList = govtProposalPropertyCategoryDAO.getProposalStatusFrAlert(alertId);
								if(statusList != null && statusList.size() > 0){
									for (Object[] objects : statusList) {
										alertTrackingVO.setProposalStatus(commonMethodsUtilService.getStringValueForObject(objects[0]));
										alertTrackingVO.setCategory(commonMethodsUtilService.getStringValueForObject(objects[1]));
										alertTrackingVO.setCategoryId(commonMethodsUtilService.getLongValueForObject(objects[2]));
										alertTrackingVO.setAmount(commonMethodsUtilService.getStringValueForObject(objects[3]));
										alertTrackingVO.setApprovedAmount(commonMethodsUtilService.getStringValueForObject(objects[4]));
									}
								}
							}
							alertTrackingVO.setSeverty(commonMethodsUtilService.getStringValueForObject(param[11]));
							if(task != null && !task.trim().isEmpty() && task.trim().length() > 0 && task.trim().equalsIgnoreCase("task")){
								alertTrackingVO.setAlertFeedbackStatusId(commonMethodsUtilService.getLongValueForObject(param[16]));
								alertTrackingVO.setAlertFeedbackStatus(commonMethodsUtilService.getStringValueForObject(param[17]));
								alertTrackingVO.setAlertCallerId(commonMethodsUtilService.getLongValueForObject(param[18]));
								alertTrackingVO.setAlertCallerName(commonMethodsUtilService.getStringValueForObject(param[19]));
							}
							alertTrackingVO.setPosition(userIdAndStatusMap.get(commonMethodsUtilService.getLongValueForObject(param[13])));
							
							if(userMap != null && userMap.get(commonMethodsUtilService.getLongValueForObject(param[13])) != null){
								userDetails = userMap.get(commonMethodsUtilService.getLongValueForObject(param[13]));
								alertTrackingVO.setUpdatedUserName(userDetails.getUserName());
								alertTrackingVO.setDesignation(userDetails.getDesignation());
								alertTrackingVO.setLocation(userDetails.getLocation());
								if(userDetails.getDeptName() != null && !userDetails.getDeptName().trim().isEmpty() && userDetails.getDeptName().trim().length() >0){
									alertTrackingVO.setDeptName(userDetails.getDeptName());
								}else{
									alertTrackingVO.setDeptName(commonMethodsUtilService.getStringValueForObject(param[15]));
								}
								
							}
							innerList.add(alertTrackingVO);    
						}  
					}
					finalList.add(innerList);
				}
			}
			if(finalList != null && finalList.size() > 0){
				Collections.reverse(finalList);    
			}
			if(finalList != null && finalList.size() > 0){
				for(List<AmsTrackingVO> inerList : finalList){
					Collections.reverse(inerList);
				}
			}
			
			return finalList;
		} catch (Exception e) {
			LOG.error(" Exception Occured in viewAlertHistory() method, Exception - ",e);
		}
		return null;
	}
	
	public List<AmsVO> getAlertsDataForSms(Long alertId){
	List<AmsVO> returnList = new ArrayList<AmsVO>();
	
	List<Long> alertIds = new ArrayList<Long>();
	try{
		List<Object[]> docList = alertDocumentDAO.getDocumentsForAlert(alertId);
		 List<Object[]> list = alertDAO.getAlertsDataForAms(alertId);
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
				 AmsVO alertVO = (AmsVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "id",commonMethodsUtilService.getStringValueForObject( params[0]).toString());
				 if(alertVO == null)
				 {
					 alertVO = new AmsVO(); 
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
					 List<AlertVO> rejoindcumntList = getRejoinderDocumentsForAlert(alertId);
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
				 //alertVO.setCategoryName(category);
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
					 setNewsAlertCandidateDataForSms(newsAlertCandidates,returnList);
					 
					 //total Involved Candidates
					 candiateCnts = alertCandidateDAO.getAlertNewsCandidateCount(alertIds);
					 
				 }else{						 
					 List<Object[]> alertCandidates = alertCandidateDAO.getAlertCandidatesData(alertIds);
					 setAlertCandidateDataForSms(alertCandidates,returnList);
					 
					 //total Involved Candidates
					 candiateCnts = alertCandidateDAO.getAlertCandidateCount(alertIds);
					 
				 }
				 	
				if(candiateCnts !=null && candiateCnts.size()>0){
					 for(Object[] params : candiateCnts)
					 {
						 AmsVO alertVO = (AmsVO) setterAndGetterUtilService.getMatchedVOfromList(returnList, "id", commonMethodsUtilService.getStringValueForObject(params[1]).toString());
							 if(alertVO != null)
							 {
								 alertVO.setCount((Long)params[0]);
							 }
					 }
				}					 
			 }
			 
			 
		 }
		 
		 if(commonMethodsUtilService.isListOrSetValid(returnList)){
			 AmsVO alertVO =  returnList.get(0);
			 if(alertVO != null){
				 List<AmsTrackingVO> subTasksList = getSubTaskDetailsForAms(alertVO.getId());
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
		public void setNewsAlertCandidateDataForSms(List<Object[]> list , List<AmsVO> dataList){
			try{
				
				if(list != null && list.size() > 0)
				{
					for(Object[] params : list)
					{
						AmsVO alertVo =(AmsVO) setterAndGetterUtilService.getMatchedVOfromList(dataList, "id", commonMethodsUtilService.getStringValueForObject(params[0]).toString());
						if(alertVo == null)
						{
							alertVo = new AmsVO();
							alertVo.setId((Long)params[0]);
							dataList.add(alertVo);
						}
						AmsVO candidateVO = null;
						if(params[1] !=null){
							candidateVO = (AmsVO) setterAndGetterUtilService.getMatchedVOfromList(alertVo.getSubList(), "id", commonMethodsUtilService.getStringValueForObject(params[1]).toString());
						}
						
						if(candidateVO == null)
						{
							candidateVO = new AmsVO();
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
		public void setAlertCandidateDataForSms(List<Object[]> list,List<AmsVO> dataList)

		{
			List<Long> tdpCadreIdsList = new ArrayList<Long>();
			if(dataList == null)
				dataList = new ArrayList<AmsVO>();
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					AmsVO alertVo =(AmsVO) setterAndGetterUtilService.getMatchedVOfromList(dataList, "id", commonMethodsUtilService.getStringValueForObject(params[0]).toString());
					if(alertVo == null)
					{
						alertVo = new AmsVO();
						alertVo.setId((Long)params[0]);
						dataList.add(alertVo);
					}
					AmsVO candidateVO = (AmsVO) setterAndGetterUtilService.getMatchedVOfromList(alertVo.getSubList(), "id", commonMethodsUtilService.getStringValueForObject(params[1]).toString());
					if(candidateVO == null)
					{
						candidateVO = new AmsVO();
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
						 setCurrentDesignationForCadreForSms(dataList.get(0).getSubList(), tdpCadreIdsList);

						 List<Object[]> publicRepDertails = tdpCadreCandidateDAO.getPublicRepresentativeDetailsByCadreIds(tdpCadreIdsList);
							if(publicRepDertails != null && publicRepDertails.size() > 0){
								for (Object[] objects : publicRepDertails) {
									AmsVO matchedCadreVO = getMatchedCadreVOForSms(dataList.get(0).getSubList(),(Long)objects[0]);
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
	public AmsVO getMatchedCadreVOForSms(List<AmsVO> voList,Long cadreId){
		if(voList != null && voList.size() > 0){
			for (AmsVO alertDataVO : voList) {
				if(alertDataVO.getId().equals(cadreId)){
					return alertDataVO;
				}
			}
		}
		return null;
	}
	public void setCurrentDesignationForCadreForSms(List<AmsVO> cadreCommitteeList,List<Long> tdpCadreIdsList){
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
				AmsVO cadreVO = (AmsVO) setterAndGetterUtilService.getMatchedVOfromList(cadreCommitteeList,"id",id.toString());
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
	public List<AmsTrackingVO> getSubTaskDetailsForAms(Long alertId){
		List<AmsTrackingVO> returnList = null;
		try {
			List<Long> alertIds = new ArrayList<Long>(0);
			alertIds.add(alertId);
			List<Long> subTasksAlertIdsList = govtAlertSubTaskDAO.getSubTasksIdsList(alertIds);
			if(commonMethodsUtilService.isListOrSetValid(subTasksAlertIdsList)){
				returnList = new ArrayList<AmsTrackingVO>(0);
				for (Long subtaskId : subTasksAlertIdsList) {
					returnList.addAll(viewSubTaskHistoryForAms(subtaskId));							
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception Occured in getSubTaskDetailsForAms() method, Exception - ",e);
		}        		
		return returnList;
	}
	public List<AmsTrackingVO> viewSubTaskHistoryForAms(Long subTaskId){
		List<AmsTrackingVO> finalList = new ArrayList<AmsTrackingVO>(0);
		SimpleDateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		SimpleDateFormat dateSdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm");
		try {
			
			List<GovtOfficerSubTaskTracking> qryRstList = govtOfficerSubTaskTrackingDAO.getModelForSubTask(subTaskId);
			
			if(qryRstList != null && qryRstList.size() > 0){
				
				GovtAlertSubTask gast = govtAlertSubTaskDAO.get(subTaskId);
				
				for (GovtOfficerSubTaskTracking govtOfficerSubTaskTracking : qryRstList) {
					
					String userName = govtOfficerSubTaskTracking.getGovtAlertSubTask().getSubTaskGovtOfficer().getOfficerName()+" - "+govtOfficerSubTaskTracking.getGovtAlertSubTask().getSubTaskGovtOfficer().getMobileNo();
					String designation = govtOfficerSubTaskTracking.getGovtAlertSubTask().getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getDesignationName()
							+" & "+govtOfficerSubTaskTracking.getGovtAlertSubTask().getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getGovtDepartment().getDepartmentName();
					
					AmsTrackingVO matchedDateVO = getMatchedDateVOForAms(finalList,dateSdf.format(dbSdf.parse(govtOfficerSubTaskTracking.getInsertedTime().toString())));
					
					if(matchedDateVO == null){
						matchedDateVO = new AmsTrackingVO();
						matchedDateVO.setUserName(userName);
						matchedDateVO.setDesignation(designation);
						matchedDateVO.setTitle(gast.getTitle().toString());
						matchedDateVO.setAlertId(gast.getGovtAlertSubTaskId());
						matchedDateVO.setDate(dateSdf.format(dbSdf.parse(govtOfficerSubTaskTracking.getInsertedTime().toString())));
						finalList.add(matchedDateVO);
					}
					
					matchedDateVO = getMatchedDateVOForAms(finalList,dateSdf.format(dbSdf.parse(govtOfficerSubTaskTracking.getInsertedTime().toString())));
					
					AmsTrackingVO matchedTimeVO = getMatchedDateVOForAms(matchedDateVO.getTimeList(),timeSdf.format(dbSdf.parse(govtOfficerSubTaskTracking.getInsertedTime().toString())));
					if(matchedTimeVO == null){
						matchedTimeVO = new AmsTrackingVO();
						matchedTimeVO.setDate(timeSdf.format(dbSdf.parse(govtOfficerSubTaskTracking.getInsertedTime().toString())));
						matchedDateVO.setTitle(gast.getTitle().toString());
						matchedDateVO.setAlertId(gast.getGovtAlertSubTaskId());
						matchedDateVO.setUserName(userName);
						matchedDateVO.setDesignation(designation);
						matchedDateVO.getTimeList().add(matchedTimeVO);
					}
					
					matchedTimeVO = getMatchedDateVOForAms(matchedDateVO.getTimeList(),timeSdf.format(dbSdf.parse(govtOfficerSubTaskTracking.getInsertedTime().toString())));
					
					if(govtOfficerSubTaskTracking.getAlertDepartmentDocumentId() != null && govtOfficerSubTaskTracking.getAlertDepartmentDocumentId() > 0l && govtOfficerSubTaskTracking.getAlertDepartmentDocument() != null){
						AmsTrackingVO vo = new AmsTrackingVO();
						vo.getStrList().add(govtOfficerSubTaskTracking.getAlertDepartmentDocument().getDocument());
						vo.setTitle(gast.getTitle().toString());
						vo.setAlertId(gast.getGovtAlertSubTaskId());
						vo.setUserName(userName);
						vo.setDesignation(designation);
						matchedTimeVO.getAttachementsList().add(vo);
					}
					
					if(govtOfficerSubTaskTracking.getAlertDepartmentCommentId() != null && govtOfficerSubTaskTracking.getAlertDepartmentCommentId() > 0l && govtOfficerSubTaskTracking.getAlertDepartmentComment() != null){
						AmsTrackingVO vo = new AmsTrackingVO();
						vo.getStrList().add(govtOfficerSubTaskTracking.getAlertDepartmentComment().getComment());
						vo.setTitle(gast.getTitle().toString());
						vo.setAlertId(gast.getGovtAlertSubTaskId());
						vo.setUserName(userName);
						vo.setDesignation(designation);
						matchedTimeVO.getCommentList().add(vo);
					}
					
					if(govtOfficerSubTaskTracking.getDueDate() != null && !govtOfficerSubTaskTracking.getDueDate().toString().trim().isEmpty()){
						AmsTrackingVO vo = new AmsTrackingVO();
						vo.getStrList().add(govtOfficerSubTaskTracking.getDueDate().toString());
						vo.setTitle(gast.getTitle().toString());
						vo.setAlertId(gast.getGovtAlertSubTaskId());
						vo.setUserName(userName);
						vo.setDesignation(designation);
						matchedTimeVO.getDueDateList().add(vo);
					}
					
					if(govtOfficerSubTaskTracking.getAlertSubTaskStatusId() != null && govtOfficerSubTaskTracking.getAlertSubTaskStatusId() > 0l && govtOfficerSubTaskTracking.getAlertSubTaskStatusId() != null){
						AmsTrackingVO vo = new AmsTrackingVO();
						vo.getStrList().add(govtOfficerSubTaskTracking.getAlertSubTaskStatus().getStatus());
						vo.setTitle(gast.getTitle().toString());
						vo.setAlertId(gast.getGovtAlertSubTaskId());
						vo.setUserName(userName);
						vo.setDesignation(designation);
						matchedTimeVO.getStatusList().add(vo);
					}
					
					if(govtOfficerSubTaskTracking.getAlertSeverityId() != null && govtOfficerSubTaskTracking.getAlertSeverityId() > 0l && govtOfficerSubTaskTracking.getAlertSeverity() != null){
						AmsTrackingVO vo = new AmsTrackingVO();
						vo.getStrList().add(govtOfficerSubTaskTracking.getAlertSeverity().getSeverity());
						vo.setTitle(gast.getTitle().toString());
						vo.setAlertId(gast.getGovtAlertSubTaskId());
						vo.setUserName(userName);
						vo.setDesignation(designation);
						matchedTimeVO.getPriorityList().add(vo);
					}
					
				}
			}
			
		} catch (Exception e) {
			LOG.error(" Exception Occured in viewSubTaskHistoryForAms() method, Exception - ",e);
		}
		return finalList;
	}
	public AmsTrackingVO getMatchedDateVOForAms(List<AmsTrackingVO> voList,String str){
		if(voList != null && voList.size() > 0){
			for (AmsTrackingVO alertTrackingVO : voList) {
				if(alertTrackingVO.getDate().equalsIgnoreCase(str))
					return alertTrackingVO;
			}
		}
		return null;
	}
	  public List<AmsTrackingVO> getSubTaskInfoForAlertForAms(AmsAppLoginVO VO){
      	List<AmsTrackingVO> voList = new ArrayList<AmsTrackingVO>(0); 
      	try {
      		List<Object[]> objList = govtAlertSubTaskDAO.getSubTaskInfoForAlert(VO.getAlertId());
      		Map<Long,AmsTrackingVO> tempMap = new LinkedHashMap<Long, AmsTrackingVO>(0);
      		List<Long> assignedToList  =new ArrayList<Long>(0);
      		Map<Long,String> userLocationMap = new HashMap<Long, String>(0);
      		Map<Long,Long> subtaskUserMap = new HashMap<Long, Long>(0);
      		
      		if(objList != null && objList.size() > 0){
      			for (Object[] obj : objList) {
      				Long subTaskGovtOfficerId  = commonMethodsUtilService.getLongValueForObject(obj[6]);
      				Long govtDepartmentDesignationOfficerId  = commonMethodsUtilService.getLongValueForObject(obj[7]);
      				List<Object[]> userList = govtDepartmentDesignationOfficerDetailsDAO.getUserIdForDeptDesigOfficerIdAndGovtOfficerId(subTaskGovtOfficerId,govtDepartmentDesignationOfficerId);
      				if(commonMethodsUtilService.isListOrSetValid(userList)){
      					for (Object[] param : userList) {
      						subtaskUserMap.put(commonMethodsUtilService.getLongValueForObject(obj[0]), commonMethodsUtilService.getLongValueForObject(param[0]));
      						if(!assignedToList.contains(commonMethodsUtilService.getLongValueForObject(param[0])))
                  				assignedToList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
							}
      				}
      			}
      			
      			if(commonMethodsUtilService.isListOrSetValid(assignedToList)){
      				List<Object[]> userdtls = govtDepartmentDesignationOfficerDetailsNewDAO.getDesignationNameForUsers(assignedToList);
      				if(commonMethodsUtilService.isListOrSetValid(userdtls)){
      					String location="";
      					for (Object[] param : userdtls) {
  							Long locationTypeId = commonMethodsUtilService.getLongValueForObject(param[3]);
  							Long scopeValue = commonMethodsUtilService.getLongValueForObject(param[4]);
  							Long assignTO = commonMethodsUtilService.getLongValueForObject(param[6]);
  							
  							if(locationTypeId != null && locationTypeId.longValue()>0L){
  								GovtDepartmentWorkLocation workLocation = govtDepartmentWorkLocationDAO.get(scopeValue);
  								if(workLocation != null)
  									location=workLocation.getLocationName();
  							}
  							userLocationMap.put(assignTO, location);
  						}
      				}
      			}
      		}
      		
      		if(objList != null && objList.size() > 0){
      			List<Long> subTaskIds = new ArrayList<Long>(0);
      			for (Object[] obj : objList) {
          		
      				AmsTrackingVO vo = new AmsTrackingVO();
      				vo.setUserId(commonMethodsUtilService.getLongValueForObject(obj[0]));
      				vo.setUserName(commonMethodsUtilService.getStringValueForObject(obj[1]));
      				vo.setStatus(commonMethodsUtilService.getStringValueForObject(obj[4]));
						vo.setColor(commonMethodsUtilService.getStringValueForObject(obj[5]));
      				vo.setAlertId(commonMethodsUtilService.getLongValueForObject(obj[0]));
      				vo.setTitle(commonMethodsUtilService.getStringValueForObject(obj[1]));
      				vo.setDate(commonMethodsUtilService.getStringValueForObject(obj[3]));
      				vo.setAlertTypeStr("others");//default sub task owner is other person , by comparing login userid
      				tempMap.put((Long)obj[0], vo);
      				subTaskIds.add(commonMethodsUtilService.getLongValueForObject(obj[0]));
					}
      			
      			List<Long> govtDeptDesigOfficerIdList = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOfficerIdListByUserId(VO.getUserId());
      			List<Object[]> subTaskOwnersList = govtAlertSubTaskDAO.getGovtDeptDesigOfficerIdsListBySubTaskId(subTaskIds);
      			
      			if(commonMethodsUtilService.isListOrSetValid(subTaskOwnersList)){
      				for (Object[] param : subTaskOwnersList) {
      					AmsTrackingVO vo  = tempMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
      					if(vo != null){
      						if(govtDeptDesigOfficerIdList.contains(commonMethodsUtilService.getLongValueForObject(param[1]))){
      							vo.setAlertTypeStr("owner");//setting sub task owner is owner of this subtask , by comparing login userid
      						}
      					}
						}
      			}
      			
      			
      			List<Object[]> officersList = govtOfficerSubTaskTrackingDAO.getSubTasksCommentsAndStatusHistory(subTaskIds);
      			if(officersList != null && officersList.size() > 0){
      				for (Object[] objects : officersList) {
							if(tempMap.get((Long)objects[0]) != null){
								AmsTrackingVO vo  = tempMap.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
								if(vo != null){
									if(!commonMethodsUtilService.getStringValueForObject(objects[2]).isEmpty())
										vo.getCommentList().add(new AmsTrackingVO(commonMethodsUtilService.getLongValueForObject(objects[0]), 
											 commonMethodsUtilService.getStringValueForObject(objects[2]),commonMethodsUtilService.getStringValueForObject(objects[3])));
									vo.setUserName(commonMethodsUtilService.getStringValueForObject(objects[4]));
									vo.setMobileNO(commonMethodsUtilService.getStringValueForObject(objects[5]));
									vo.setDesignation(commonMethodsUtilService.getStringValueForObject(objects[6]));
									vo.setDeptName(commonMethodsUtilService.getStringValueForObject(objects[7]));
									Long assignedTo = subtaskUserMap.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
									vo.setLocation(userLocationMap.get(assignedTo) != null ? userLocationMap.get(assignedTo) :"");
								}
							}
						}
      			}
      			
      			
      			List<Object[]> objectsList = govtOfficerSubTaskTrackingDAO.getCommentsForSubTasks(subTaskIds);
      			if(objectsList != null && objectsList.size() > 0){
      				for (Object[] objects : objectsList) {
							if(tempMap.get((Long)objects[0]) != null){
								tempMap.get((Long)objects[0]).setCount((Long)objects[1]);
							}
						}
      			}
      			
      		}
      		
      		if(tempMap != null && tempMap.size() > 0){
      			AmsTrackingVO returnVO = new AmsTrackingVO();
      			for (Long subTaskId : tempMap.keySet()) {
      				AmsTrackingVO vo  = tempMap.get(subTaskId);
  					if(vo != null){
  						if(vo.getAlertTypeStr().trim().equalsIgnoreCase("owner"))
  							returnVO.getAttachementsList().add(vo);
  						else
  							returnVO.getCommentList().add(vo);
  					}
					}
      			voList.add(returnVO);
      		}
			} catch (Exception e) {
				LOG.error("Error occured getSubTaskInfoForAlertForAms() method of AlertManagementSystemService");
			}	
      	return voList;
      } 
	  public List<AmsAppVO> getStatusCompletionInfoForSubTaskForAms(AmsAppLoginVO inputVo){
    		try{
    			List<AmsAppVO> finalList = new ArrayList<AmsAppVO>();
    			
    			GovtAlertSubTask govtAlertSubTask = govtAlertSubTaskDAO.get(inputVo.getSubTaskId());
    			
    			Long assignedByOfficerId=govtAlertSubTask.getCreatedBy();//sub task assigned by 
    			Long assignedToOfficerId = govtAlertSubTask.getGovtDepartmentDesignationOfficerId();
    			String subTaskUserTypeStr ="other";
    			
    			//get all govt dept desig off ids
      		List<Long> govtDeptDesigOfficerIdList = govtDepartmentDesignationOfficerDetailsNewDAO.getGovtDeptDesigOfficerIdListByUserId(inputVo.getUserId());
    			
    			if(assignedByOfficerId != null && inputVo.getUserId() != null && assignedByOfficerId.longValue() == inputVo.getUserId().longValue()){
    				subTaskUserTypeStr="assignedBy";//setting sub task owner is owner of this subtask , by comparing login userid
				}
    			else if(govtDeptDesigOfficerIdList.contains(assignedToOfficerId)){
    				subTaskUserTypeStr="assignedTo";//setting sub task owner is owner of this subtask , by comparing login userid
				}
    			
    			Long currentStatusId = govtAlertSubTask.getAlertSubTaskStatusId();
    			
      		//get govt dept desig off id by alertId
      		Long govtDeptDesigOfficerId = alertAssignedOfficerNewDAO.getGovtDeptDesigOfficerIdListByUserId(inputVo.getAlertId());
      		
      		//to check whether the logedin user is owner of the subtask or not.
      		
      		Long govtDeptDesigOfficerId2 = govtAlertSubTaskDAO.getGovtDeptDesigOfficerIdBySubTaskId(inputVo.getSubTaskId());
      		
      		
      		String isAccess = "";
      		//if(govtDeptDesigOfficerIdList != null && govtDeptDesigOfficerId != null && govtDeptDesigOfficerIdList.size() > 0 && govtDeptDesigOfficerIdList.contains(govtDeptDesigOfficerId)){
      		if(subTaskUserTypeStr.equalsIgnoreCase("assignedBy")){
      			if(govtAlertSubTask.getAlertSubTaskStatusId() != null && govtAlertSubTask.getAlertSubTaskStatusId().longValue() == 3L){
      				isAccess = "true";
      			}
      			
      			Long[] availableIdsArr = {6L,7L};// reopen, closed 
      			
      			List<Long> availableIdsList = new ArrayList<Long>(0);
      			availableIdsList.addAll(Arrays.asList(availableIdsArr));
      			
      			List<AlertSubTaskStatus> objList = alertSubTaskStatusDAO.getAll();
      			if(objList != null && objList.size() > 0){
      				for (AlertSubTaskStatus param : objList) {
      					if(currentStatusId.longValue() != param.getAlertSubTaskStatusId().longValue()){
      						    AmsAppVO VO = new AmsAppVO();
	        					VO.setId(param.getAlertSubTaskStatusId());
	                			VO.setName(param.getStatus());
	                			if(availableIdsList.contains(VO.getId()))
	                				finalList.add(VO);
      					}
  					}
      			}
      			
      		//}else if(govtDeptDesigOfficerIdList != null && govtDeptDesigOfficerId2 != null && govtDeptDesigOfficerIdList.size() > 0 && govtDeptDesigOfficerIdList.contains(govtDeptDesigOfficerId2)){
      		}if(subTaskUserTypeStr.equalsIgnoreCase("assignedTo")){
      			
      			Long[] availableIdsArr = {1L,2L,3L,4L,5L};
      			
      			List<Long> availableIdsList = new ArrayList<Long>(0);
      			availableIdsList.addAll(Arrays.asList(availableIdsArr));
      			
      			List<AlertSubTaskStatus> objList = alertSubTaskStatusDAO.getAll();
      			if(objList != null && objList.size() > 0){
      				for (AlertSubTaskStatus param : objList) {
      					if(currentStatusId.longValue() != param.getAlertSubTaskStatusId().longValue()){
      						    AmsAppVO VO = new AmsAppVO();
	        					VO.setId(param.getAlertSubTaskStatusId());
	                			VO.setName(param.getStatus());
	                			if(availableIdsList.contains(VO.getId()))
	                				finalList.add(VO);
      					}
  					}
      			}
      			
      		}
      		Long alertSubStatusId = govtAlertSubTask.getAlertSubTaskStatusId();
      		
      		if(!commonMethodsUtilService.isListOrSetValid(finalList)){
      			AmsAppVO VO = new AmsAppVO();
					VO.setId(commonMethodsUtilService.getLongValueForObject(alertSubStatusId));
      			VO.setName(govtAlertSubTask.getAlertSubTaskStatus().getStatus());
      			finalList.add(VO);
      		}
      		
      		String userName = "";
      		String locationName = "";
      		String deptName = "";
      		String desigName  = "";
      		
  			List<Object[]> desigList = govtDepartmentDesignationOfficerDetailsNewDAO.getLocationNameByAssignedOficer(assignedByOfficerId);
  			if(desigList != null && desigList.size() > 0){
  				for (Object[] objects : desigList) {
  					userName = commonMethodsUtilService.getStringValueForObject(objects[0]);
  					locationName = commonMethodsUtilService.getStringValueForObject(objects[3]);
  					deptName = commonMethodsUtilService.getStringValueForObject(objects[2]);
  					desigName = commonMethodsUtilService.getStringValueForObject(objects[1]);
  				}
  			}
  			if(finalList != null && finalList.size() > 0){
  				AmsAppVO vo = finalList.get(0);
  				Long assignedUserId = govtAlertSubTask.getCreatedBy();
  				if(assignedUserId != null){
  					List<String> deptList = govtAlertDepartmentLocationNewDAO.getAccessDepartmentList(assignedUserId);
  					if(deptList != null && deptList.size() > 0){
  						StringBuilder strBuild = new StringBuilder();
  						if(deptList.size() > 3){
  							for(int i=1 ; i <= 3 ; i++){
  								strBuild.append(deptList.get(i));
  								strBuild.append(",");
  							}
  							strBuild.append("...");
  						}else{
  							for(String str : deptList){
  								strBuild.append(str);
  								strBuild.append(",");
  							}
  						}  
  						vo.setDeptName(strBuild.toString());
  					}
  				}
  				
  				//vo.setDeptName(govtAlertSubTask.getAlertAssignedOfficer().getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getGovtDepartment().getDepartmentName());
  				vo.setAssignedByOfficerStr(govtAlertSubTask.getAlertAssignedOfficer().getGovtOfficer().getOfficerName());
  				vo.setAssignedOfficerStr(govtAlertSubTask.getSubTaskGovtOfficer().getOfficerName());
  				//vo.setMobileNo(govtAlertSubTask.getAlertAssignedOfficer().getGovtOfficer().getMobileNo() != null ?govtAlertSubTask.getAlertAssignedOfficer().getGovtOfficer().getMobileNo():"");//mobile No for Assigned By
  				vo.setMobileNo(govtAlertSubTask.getSubTaskGovtOfficer().getMobileNo() != null ?govtAlertSubTask.getSubTaskGovtOfficer().getMobileNo():"");//mobile No for Assigned to
  				vo.setDesignation(desigName);
  				vo.setAlertId(inputVo.getSubTaskId());
  				vo.setDescription(govtAlertSubTask.getAlert().getDescription());
  				vo.setMainTitle(govtAlertSubTask.getAlert().getTitle());
  				vo.setTitle(govtAlertSubTask.getTitle());
  				vo.setDateStr(govtAlertSubTask.getCreatedTime() != null ? govtAlertSubTask.getCreatedTime().toString().substring(0, 10):"");
  				vo.setDueDateStr(govtAlertSubTask.getDueDate() != null ? govtAlertSubTask.getDueDate().toString().substring(0, 10):"");
  				
  				vo.setIsAccess(isAccess);
  				vo.setStatus(alertSubTaskStatusDAO.get(currentStatusId).getStatus());
  				vo.setStatusId(commonMethodsUtilService.getLongValueForObject(alertSubStatusId));
  				vo.setColor(alertSubTaskStatusDAO.get(currentStatusId).getColor());
  				vo.setCategoryId(govtAlertSubTask.getAlert().getAlertCategoryId());
  				vo.setPositionName(locationName);//LocationName for Assigned By
  				vo.setCallerName(govtAlertSubTask.getGovtDepartmentDesignationOfficer().getLevelValueGovtDepartmentWorkLocation().getLocationName());//Location for assignedTo
  				vo.setBoardName(govtAlertSubTask.getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getGovtDepartment().getDepartmentName());//depart for assigned to
  				
      		/*	List<Long> subTaskIds = new ArrayList<Long>(0);
  				List<Long> userIds = new ArrayList<Long>(0);
  				subTaskIds.add(vo.getAlertId());
  				List<Object[]> officersList = govtOfficerSubTaskTrackingDAO.getSubTasksStatusHistory(subTaskIds);
      			if(officersList != null && officersList.size() > 0){
	    				for (Object[] objects : officersList) {
	    					//userIds.add(commonMethodsUtilService.getLongValueForObject(objects[11]));

							//}
					//}
      			//List<Object[]> deptList = govtOfficerSubTaskTrackingDAO.getSubTasksStatusHistoryByUser(userIds);
      			//if(deptList != null && deptList.size() > 0){
	    				//for (Object[] objects : deptList) {
								if(!commonMethodsUtilService.getStringValueForObject(objects[2]).isEmpty()){
									vo.getCommentList().add(new AlertTrackingVO(commonMethodsUtilService.getLongValueForObject(objects[0]), 
										 commonMethodsUtilService.getStringValueForObject(objects[2]),commonMethodsUtilService.getStringValueForObject(objects[3]),
										 commonMethodsUtilService.getStringValueForObject(objects[4]),commonMethodsUtilService.getStringValueForObject(objects[6]),commonMethodsUtilService.getStringValueForObject(objects[7]),commonMethodsUtilService.getStringValueForObject(objects[10])));
								}
								
								if(!commonMethodsUtilService.getStringValueForObject(objects[8]).isEmpty()){
									vo.getSubList().add(commonMethodsUtilService.getStringValueForObject(objects[8]));
								

							}
	    				}
      			}*/
  				List<Long> subTaskIds = new ArrayList<Long>(0);
  				Set<Long> userIds = new HashSet<Long>(0);
  				Map<Long,AmsTrackingVO> map = new LinkedHashMap<Long, AmsTrackingVO>(0);
  				subTaskIds.add(vo.getAlertId());
  				List<Object[]> officersList = govtOfficerSubTaskTrackingDAO.getSubTasksStatusHistory(subTaskIds);
      			if(officersList != null && officersList.size() > 0){
	    				for (Object[] objects : officersList) {
	    					userIds.add(commonMethodsUtilService.getLongValueForObject(objects[11]));
								if(!commonMethodsUtilService.getStringValueForObject(objects[2]).isEmpty()){
									vo.getCommentList().add(new AmsTrackingVO(commonMethodsUtilService.getLongValueForObject(objects[0]), 
										 commonMethodsUtilService.getStringValueForObject(objects[2]),commonMethodsUtilService.getStringValueForObject(objects[3]),
										 commonMethodsUtilService.getStringValueForObject(objects[4]),commonMethodsUtilService.getStringValueForObject(objects[6]),commonMethodsUtilService.getStringValueForObject(objects[7]),commonMethodsUtilService.getStringValueForObject(objects[10]),commonMethodsUtilService.getLongValueForObject(objects[11])));
								}
								
								if(!commonMethodsUtilService.getStringValueForObject(objects[8]).isEmpty()){
									vo.getSubList().add(commonMethodsUtilService.getStringValueForObject(objects[8]));
							}
	    	    		}
	            	}
	    		    
      			List<Object[]> deptList = govtDepartmentDesignationOfficerDetailsNewDAO.getDesigAndDepartForUser(new ArrayList<Long>(userIds));
      			if(deptList != null && deptList.size() > 0){
	    				for (Object[] object : deptList) {
	    					Long usrId = commonMethodsUtilService.getLongValueForObject(object[0]);
	    					AmsTrackingVO trackingVO = map.get(usrId);
	    					if(trackingVO == null){
	    						trackingVO  = new AmsTrackingVO();
	    						trackingVO.setUserName(commonMethodsUtilService.getStringValueForObject(object[1]));
	    						trackingVO.setDesignation(commonMethodsUtilService.getStringValueForObject(object[2]));
	    						trackingVO.setLocation(commonMethodsUtilService.getStringValueForObject(object[3]));
	    						trackingVO.setDeptName(commonMethodsUtilService.getStringValueForObject(object[4]));
	    						map.put(usrId,trackingVO);
	    					}
							}
	    				}
      			
      			if(vo.getCommentList() != null && !vo.getCommentList().isEmpty()){
      				for (AmsTrackingVO desigVO: vo.getCommentList()) {
      					Long usrId = desigVO.getUserId();
      					AmsTrackingVO finalVO = map.get(usrId);
      					if(finalVO != null){
      						desigVO.setUserName(finalVO.getUserName());
      						desigVO.setDesignation(finalVO.getDesignation());
      						desigVO.setLocation(finalVO.getLocation());
      						desigVO.setDeptName(finalVO.getDeptName());
      					}
						}
      			}
      			vo.setUserType(subTaskUserTypeStr);// assignedBy user or assignedTo user
      			
  			}
  			return 	finalList;
    		}catch(Exception e){
    			e.printStackTrace();
    			LOG.error("Error occured getStatusCompletionInfoForSubTaskForAms() method of AlertManagementSystemService");
    		}
    		return null;
    	}
	  
	  
	 
	  /*
		 * Teja(non-Javadoc)
		 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getGovtAllDepartmentDetailsForAms()
		 */
		public List<AmsKeyValueVO> getGovtAllDepartmentDetailsForAms(){
      		List<AmsKeyValueVO> finalList = new ArrayList<AmsKeyValueVO>();
      		try {
      			
      			List<Object[]> list = govtDepartmentRelationDAO.getAllMainDepartments();
      			if(list !=null && list.size()>0){
      				for (Object[] objects : list) {							
      					AmsKeyValueVO vo = new AmsKeyValueVO();
      					vo.setId((Long)objects[0]);
      					vo.setName(objects[1] !=null ? objects[1].toString():"");
      					
      					finalList.add(vo);
					}
      			}
				
			} catch (Exception e) {
				LOG.error(" Exception Occured in getGovtAllDepartmentDetailsForAms() method, Exception - ",e);
			}
      		return finalList;
      	}
		public List<AmsKeyValueVO> getSubDeptsFrParentDeptForAms(AmsAppLoginVO keyVo){
			List<AmsKeyValueVO> finalList = new ArrayList<AmsKeyValueVO>();
			try{
				
				List<Object[]> subDeptList = govtDepartmentRelationDAO.getSubDeptsForParentDept(keyVo.getParentDeptId());
				if(subDeptList != null && subDeptList.size() > 0l){
					for (Object[] objects : subDeptList) {
						AmsKeyValueVO vo = new AmsKeyValueVO();
						vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
						finalList.add(vo);
					}
				}
			}catch(Exception e){
				LOG.error("Error occured getSubDeptsFrParentDeptForAms() method of AlertManagementSystemService{}");
			}
			return finalList;
		}
		public List<AmsKeyValueVO> getDepartmentLevelsForAms(AmsAppLoginVO keyVo){
			List<AmsKeyValueVO> resultList = new ArrayList<AmsKeyValueVO>();
			try {						
				List<Object[]> levelObj = govtDepartmentScopeLevelDAO.getDepartmentLevels(keyVo.getDepartmentId());
				if(levelObj != null && levelObj.size()>0){
					for (Object[] param : levelObj) {
						AmsKeyValueVO VO = new AmsKeyValueVO();
						   VO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						   VO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						   resultList.add(VO);
					}				 
				}
			} catch (Exception e) {
				LOG.error("Error occured getDepartmentLevelsForAms(Long departmentId) method of AlertManagementSystemService",e);
			}
			return resultList;
		}
		/*
		   * Date : 22/06/2017
		   * Author :Teja
		   * @description : getParentLevelsOfLevelForAms(for this data used ams app in location wise selected data)
		   */
		public List<AmsKeyValueVO> getParentLevelsOfLevelForAms(AmsAppLoginVO keyVo){
			
			List<AmsKeyValueVO> finalList = new ArrayList<AmsKeyValueVO>(0);
			
			try {
				
				Map<Long,AmsKeyValueVO> levelMap = new LinkedHashMap<Long, AmsKeyValueVO>();
				
				
				List<Object[]> subLevelObj = govtDepartmentScopeLevelDAO.getParentLevelsOfLevel(keyVo.getDepartmentId(),keyVo.getLevelId());
				
				
				if(subLevelObj !=null && subLevelObj.size()>0){
					Set<Long> subLevelIds = new LinkedHashSet<Long>();
					for (Object[] param : subLevelObj) {					
						//if(param[0] !=null && !(param[0].equals(levelId))){	
							
						AmsKeyValueVO vo = new AmsKeyValueVO();						
							vo.setId((Long)param[0]);
							vo.setName(param[1] !=null ? param[1].toString():"");	
							
							//levelList.add(vo);
							levelMap.put((Long)param[0], vo);
							
							subLevelIds.add((Long)param[0]);
						//}
					}
					
					//0.levelId,1.workLocationId,2.LocationName
					List<Object[]> objList = govtDepartmentWorkLocationDAO.getLevelWiseInfo(keyVo.getDepartmentId(),subLevelIds);
					
					setLocationValuesToMapForAms(objList,levelMap);
					
					if(levelMap !=null && levelMap.size()>0){
						finalList.addAll(levelMap.values());
					}
					
				}
				
				
			} catch (Exception e) {
				LOG.error("Error occured getParentLevelsOfLevel(Long departmentId,Long levelId) method of AlertManagementSystemService",e);
			}
			return finalList;
		}
		public void setLocationValuesToMapForAms(List<Object[]> objList,Map<Long,AmsKeyValueVO> levelMap){
			try {
				
				if(objList !=null && objList.size()>0){
					for (Object[] obj : objList) {
						
						if(obj[0] !=null){
							if((Long)obj[0] == 1l){
								
								AmsKeyValueVO mainVo = levelMap.get(obj[0] !=null ? (Long)obj[0]:0l);
								if(mainVo == null){
									mainVo = new AmsKeyValueVO();
									
									mainVo.setId((Long)obj[0]);
									levelMap.put((Long)obj[0], mainVo);
								}
								
								if(obj[1] !=null){
									AmsKeyValueVO subVo = new AmsKeyValueVO();
									
									subVo.setId((Long)obj[1]);
									subVo.setName(obj[2] !=null ? obj[2].toString():"");
									
									mainVo.getIdnameList().add(subVo);
								}
								
							}
						}
					}
				}
			} catch (Exception e) {
				LOG.error("Error occured setLocationValuesToMapForAms() method of AlertManagementSystemService",e);
			}
		}
		public List<AmsKeyValueVO> getDesignationsByDepartmentForAms(AmsAppLoginVO keyVo){
			List<AmsKeyValueVO> returnList = new ArrayList<AmsKeyValueVO>();
			try {
				
				List<Object[]> list = govtDepartmentDesignationOfficerDetailsNewDAO.getDesignationsForDepartmentAndLevelLocation(keyVo.getDepartmentId(),keyVo.getLevelId(),keyVo.getLevelValue());
				if(list != null && !list.isEmpty()){
					for (Object[] obj : list) {
						AmsKeyValueVO vo = new AmsKeyValueVO();
						vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						vo.setName(obj[1] != null ? obj[1].toString():"");
						returnList.add(vo);
					}
				}
			} catch (Exception e) {
				LOG.error("Error occured getDesignationsByDepartmentForAms() method of AlertManagementSystemService",e);
			}
			return returnList;
		}
		public List<AmsKeyValueVO> getOfficersByDesignationAndLevelForAms(AmsAppLoginVO keyVo){
			List<AmsKeyValueVO> returnList = new ArrayList<AmsKeyValueVO>();
			try {
				List<Object[]> list = govtDepartmentDesignationOfficerDetailsNewDAO.getOfficersByDesignationAndLevel(keyVo.getLevelId(),keyVo.getLevelValue(),keyVo.getDesignationId());
				if(list != null && !list.isEmpty()){
					for (Object[] obj : list) {
						AmsKeyValueVO vo = new AmsKeyValueVO();					
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
				LOG.error("Error occured getOfficersByDesignationAndLevelForAms() method of CccDashboardService",e);
			}
			return returnList;
		}
		public ResultStatus updateAlertStatusCommentForAms(final AmsAppLoginVO keyVo){
			final ResultStatus rs = new ResultStatus();
			try {
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
						
						
						/* here only we are updating for one assigned officer. But we can assing to multiple members . because of this we need to update present status of alert
						 * for every assigned user. so am iterating the whole assigned officers 
						 * Srishailam Pittala 
						 */
						
						/*AlertAssignedOfficerNew aaon = alertAssignedOfficerNewDAO.getModelForAlert(alertId).get(0);
						if(statusId == 8l || statusId == 9l)
							aaon.setIsApproved("N");
						aaon.setAlertStatusId(statusId);
						aaon.setUpdatedBy(userId);
						aaon.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						alertAssignedOfficerNewDAO.save(aaon);
						
						AlertAssignedOfficerTrackingNew aaotn = new AlertAssignedOfficerTrackingNew();
							if(statusId == 8l || statusId == 9l)
								aaon.setIsApproved("N");
						aaotn.setAlertAssignedOfficerId(aaon.getAlertAssignedOfficerId());
						aaotn.setAlertId(aaon.getAlertId());
						aaotn.setGovtDepartmentDesignationOfficerId(aaon.getGovtDepartmentDesignationOfficerId());
						aaotn.setGovtOfficerId(aaon.getGovtOfficerId());
						aaotn.setGovtAlertActionTypeId(6l);
						if(statusId != null && statusId.longValue()>0L)
							aaotn.setAlertStatusId(statusId);
						
						if(adcn != null)
							aaotn.setAlertDepartmentCommentId(adcn.getAlertDepartmentCommentId());
						
						aaotn.setInsertedBy(userId);
						aaotn.setAlertStatusId(aaon.getAlertStatusId());
						aaotn.setUpdatedBy(userId);
						aaotn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						aaotn.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						aaotn.setIsApproved(aaon.getIsApproved());
						alertAssignedOfficerTrackingNewDAO.save(aaotn);						*/
						/* SMS sending while assigning a new alert to any officer */
						
						//List<Long> assingedIdsList = alertAssignedOfficerNewDAO.getAssignedDtls(alertId);
						List<Long> assingedIdsList = new ArrayList<Long>(0);
						Alert alert = alertDAO.get(keyVo.getAlertId());
						if(keyVo.getStatusId() == 13l){
							GovtProposalPropertyCategory govtProposalPropertyExistingAlert = govtProposalPropertyCategoryDAO.getExistingStatusByAlertId(keyVo.getAlertId());
							if(govtProposalPropertyExistingAlert == null){
								GovtProposalPropertyCategory govtProposalPropertyCategory = new GovtProposalPropertyCategory();
								govtProposalPropertyCategory.setAlertId(keyVo.getAlertId());
								govtProposalPropertyCategory.setGovtProposalCategoryId(keyVo.getProposalCategoryId());
								govtProposalPropertyCategory.setProposalAmount(keyVo.getProposalAmount());
								govtProposalPropertyCategory.setGovtProposalStatusId(1l);
								govtProposalPropertyCategory.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								govtProposalPropertyCategory.setInsertedBy(keyVo.getUserId());
								govtProposalPropertyCategory.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								govtProposalPropertyCategory.setUpdatedBy(keyVo.getUserId());
								govtProposalPropertyCategory.setIsDeleted("N");
								govtProposalPropertyCategoryDAO.save(govtProposalPropertyCategory);
							
								AlertComment alertComment = new AlertComment();
								alertComment.setAlertId(keyVo.getAlertId());
								alertComment.setComments(keyVo.getComment());
								alertComment.setInsertedBy(keyVo.getUserId());
								alertComment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								alertComment.setIsDeleted("N");
								alertComment = alertCommentDAO.save(alertComment);
								
							GovtProposalPropertyCategoryTracking govtProposalPropertyCategoryTracking = new GovtProposalPropertyCategoryTracking();
								 govtProposalPropertyCategoryTracking.setAlertId(keyVo.getAlertId());
								 govtProposalPropertyCategoryTracking.setGovtProposalCategoryId(keyVo.getProposalCategoryId());
								 govtProposalPropertyCategoryTracking.setGovtProposalStatusId(1l);
								 govtProposalPropertyCategoryTracking.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								 govtProposalPropertyCategoryTracking.setInsertedBy(keyVo.getUserId());
								 govtProposalPropertyCategoryTracking.setIsDeleted("N");
								 govtProposalPropertyCategoryTracking.setAlertCommentId(alertComment.getAlertCommentId());
							     govtProposalPropertyCategoryTrackingDAO.save(govtProposalPropertyCategoryTracking);
							     
							     //Alert alert = alertDAO.get(alertId);
									if(alert != null && keyVo.getStatusId() != null && keyVo.getStatusId().longValue()>0L){
										alert.setAlertStatusId(keyVo.getStatusId());
										alert.setUpdatedBy(keyVo.getUserId());
										alert.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
										alertDAO.save(alert);
									}
									
									AlertDepartmentCommentNew adcn = null;
									if(keyVo.getComment() != null && !keyVo.getComment().trim().isEmpty()){
										adcn = new AlertDepartmentCommentNew();
										adcn.setComment(keyVo.getComment());
										adcn.setInsertedBy(keyVo.getUserId());
										adcn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
										adcn = alertDepartmentCommentNewDAO.save(adcn);
									}
									//List<Long> assingedIdsList = new ArrayList<Long>(0);
									List<AlertAssignedOfficerNew> assignedOfficersList = alertAssignedOfficerNewDAO.getModelForAlert(keyVo.getAlertId());
									if(commonMethodsUtilService.isListOrSetValid(assignedOfficersList)){
										
										for (AlertAssignedOfficerNew aaon : assignedOfficersList) {
											
											if(keyVo.getStatusId() == 8l || keyVo.getStatusId() == 9l)
												aaon.setIsApproved("N");
											aaon.setAlertStatusId(keyVo.getStatusId());
											aaon.setUpdatedBy(keyVo.getUserId());
											aaon.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
											alertAssignedOfficerNewDAO.save(aaon);
											
											AlertAssignedOfficerTrackingNew aaotn = new AlertAssignedOfficerTrackingNew();
												if(keyVo.getStatusId() == 8l || keyVo.getStatusId() == 9l)
													aaon.setIsApproved("N");
											aaotn.setAlertAssignedOfficerId(aaon.getAlertAssignedOfficerId());
											aaotn.setAlertId(aaon.getAlertId());
											aaotn.setGovtDepartmentDesignationOfficerId(aaon.getGovtDepartmentDesignationOfficerId());
											aaotn.setGovtOfficerId(aaon.getGovtOfficerId());
											aaotn.setGovtAlertActionTypeId(6l);
											if(keyVo.getStatusId() != null && keyVo.getStatusId().longValue()>0L)
												aaotn.setAlertStatusId(keyVo.getStatusId());
											
											if(adcn != null)
												aaotn.setAlertDepartmentCommentId(adcn.getAlertDepartmentCommentId());
											
											aaotn.setInsertedBy(keyVo.getUserId());
											aaotn.setAlertStatusId(aaon.getAlertStatusId());
											aaotn.setUpdatedBy(keyVo.getUserId());
											aaotn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
											aaotn.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
											aaotn.setIsApproved(aaon.getIsApproved());
											alertAssignedOfficerTrackingNewDAO.save(aaotn);
											
											assingedIdsList.add(aaon.getAlertAssignedOfficerId());
										}
									}
							}else{
								rs.setMessage("Already In ProposalStatus");
							}
						}else if(keyVo.getStatusId() == 10l){
							
							if(alert != null && keyVo.getStatusId() != null && keyVo.getStatusId().longValue()>0L){
								alert.setAlertStatusId(keyVo.getStatusId());
								alert.setUpdatedBy(keyVo.getUserId());
								alert.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								alertDAO.save(alert);
							}
							
							AlertDepartmentCommentNew adcn = null;
							if(keyVo.getComment() != null && !keyVo.getComment().trim().isEmpty()){
								adcn = new AlertDepartmentCommentNew();
								adcn.setComment(keyVo.getComment());
								adcn.setInsertedBy(keyVo.getUserId());
								adcn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								adcn = alertDepartmentCommentNewDAO.save(adcn);
							}
							//List<Long> assingedIdsList = new ArrayList<Long>(0);
							List<AlertAssignedOfficerNew> assignedOfficersList = alertAssignedOfficerNewDAO.getModelForAlert(keyVo.getAlertId());
							if(commonMethodsUtilService.isListOrSetValid(assignedOfficersList)){
								
								for (AlertAssignedOfficerNew aaon : assignedOfficersList) {
									
									if(keyVo.getStatusId() == 8l || keyVo.getStatusId() == 9l)
										aaon.setIsApproved("N");
									aaon.setAlertStatusId(keyVo.getStatusId());
									aaon.setUpdatedBy(keyVo.getUserId());
									aaon.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									alertAssignedOfficerNewDAO.save(aaon);
									
									/*String folderName = folderCreationForAlertsAttachmentNew();
									AlertDepartmentDocumentNew addn = null;	
									
									if(mapfiles != null && mapfiles.size() > 0){
										 AlertAssignedOfficerNew aaon = alertAssignedOfficerNewDAO.getModelForAlert(alertId).get(0);
										 for (Map.Entry<File, String> entry : mapfiles.entrySet()){
											 str = new StringBuilder();
											 Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
											 String destPath = folderName+"/"+randomNumber+"."+entry.getValue();
											 StringBuilder pathBuilder = new StringBuilder();
											  pathBuilder.append("alerts_attachments/").append(yearStr).append("/").append(dateStr).append("/").append(randomNumber).append(".").append(entry.getValue());
											 str.append(randomNumber).append(".").append(entry.getValue());
											String fileCpyStts = activityService.copyFile(entry.getKey().getAbsolutePath(),destPath);
											 
												if(fileCpyStts.equalsIgnoreCase("error")){
													resultStatus.setResultCode(ResultCodeMapper.FAILURE);
													LOG.error(" Exception Raise in copying file");
													throw new ArithmeticException();
												}
												
												addn = new AlertDepartmentDocumentNew();
												addn.setDocument(pathBuilder.toString());
												addn.setInsertedBy(userId);
												addn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
												addn = alertDepartmentDocumentNewDAO.save(addn);
									}*/
									
									AlertAssignedOfficerTrackingNew aaotn = new AlertAssignedOfficerTrackingNew();
										if(keyVo.getStatusId() == 8l || keyVo.getStatusId() == 9l)
											aaon.setIsApproved("N");
									aaotn.setAlertAssignedOfficerId(aaon.getAlertAssignedOfficerId());
									aaotn.setAlertId(aaon.getAlertId());
									aaotn.setGovtDepartmentDesignationOfficerId(aaon.getGovtDepartmentDesignationOfficerId());
									aaotn.setGovtOfficerId(aaon.getGovtOfficerId());
									aaotn.setGovtAlertActionTypeId(6l);
									if(keyVo.getStatusId() != null && keyVo.getStatusId().longValue()>0L)
										aaotn.setAlertStatusId(keyVo.getStatusId());
									
									if(adcn != null)
										aaotn.setAlertDepartmentCommentId(adcn.getAlertDepartmentCommentId());
									
									
									aaotn.setInsertedBy(keyVo.getUserId());
									aaotn.setAlertStatusId(aaon.getAlertStatusId());
									aaotn.setUpdatedBy(keyVo.getUserId());
									aaotn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
									aaotn.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									aaotn.setIsApproved(aaon.getIsApproved());
									aaotn.setGovtRejoinderActionId(keyVo.getRejoinderActionId());
									alertAssignedOfficerTrackingNewDAO.save(aaotn);
									
									assingedIdsList.add(aaon.getAlertAssignedOfficerId());
								}
							}
						
						}else{
							//Alert alert = alertDAO.get(alertId);
							if(alert != null && keyVo.getStatusId() != null && keyVo.getStatusId().longValue()>0L){
								alert.setAlertStatusId(keyVo.getStatusId());
								alert.setUpdatedBy(keyVo.getUserId());
								alert.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								alertDAO.save(alert);
							}
							
							AlertDepartmentCommentNew adcn = null;
							if(keyVo.getComment() != null && !keyVo.getComment().trim().isEmpty()){
								adcn = new AlertDepartmentCommentNew();
								adcn.setComment(keyVo.getComment());
								adcn.setInsertedBy(keyVo.getUserId());
								adcn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								adcn = alertDepartmentCommentNewDAO.save(adcn);
							}
							
							List<AlertAssignedOfficerNew> assignedOfficersList = alertAssignedOfficerNewDAO.getModelForAlert(keyVo.getAlertId());
							if(commonMethodsUtilService.isListOrSetValid(assignedOfficersList)){
								
								for (AlertAssignedOfficerNew aaon : assignedOfficersList) {
									
									if(keyVo.getStatusId() == 8l || keyVo.getStatusId() == 9l)
										aaon.setIsApproved("N");
									aaon.setAlertStatusId(keyVo.getStatusId());
									aaon.setUpdatedBy(keyVo.getUserId());
									aaon.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									alertAssignedOfficerNewDAO.save(aaon);
									
									AlertAssignedOfficerTrackingNew aaotn = new AlertAssignedOfficerTrackingNew();
										if(keyVo.getStatusId() == 8l || keyVo.getStatusId() == 9l)
											aaon.setIsApproved("N");
									aaotn.setAlertAssignedOfficerId(aaon.getAlertAssignedOfficerId());
									aaotn.setAlertId(aaon.getAlertId());
									aaotn.setGovtDepartmentDesignationOfficerId(aaon.getGovtDepartmentDesignationOfficerId());
									aaotn.setGovtOfficerId(aaon.getGovtOfficerId());
									aaotn.setGovtAlertActionTypeId(6l);
									if(keyVo.getStatusId() != null && keyVo.getStatusId().longValue()>0L)
										aaotn.setAlertStatusId(keyVo.getStatusId());
									
									if(adcn != null)
										aaotn.setAlertDepartmentCommentId(adcn.getAlertDepartmentCommentId());
									
									aaotn.setInsertedBy(keyVo.getUserId());
									aaotn.setAlertStatusId(aaon.getAlertStatusId());
									aaotn.setUpdatedBy(keyVo.getUserId());
									aaotn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
									aaotn.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									aaotn.setIsApproved(aaon.getIsApproved());
									alertAssignedOfficerTrackingNewDAO.save(aaotn);
									
									assingedIdsList.add(aaon.getAlertAssignedOfficerId());
								}
							}
						}
						
						if(commonMethodsUtilService.isListOrSetValid(assingedIdsList)){//assingedId != null){
							for (Long assingedId : assingedIdsList) {
								AlertAssignedOfficerNew alertAssignedOfficer2 = alertAssignedOfficerNewDAO.get(assingedId);
								Long designationId = alertAssignedOfficer2.getGovtDepartmentDesignationOfficer().getGovtDepartmentDesignation().getGovtDepartmentDesignationId();
								Long govtofficerId = alertAssignedOfficer2.getGovtOfficerId();
								
								List<String> mobileNos = govtOfficerNewDAO.getOfficerDetailsByOfficerId(govtofficerId);
								if(mobileNos != null && mobileNos.size() > 0 && mobileNos.get(0).trim().length() > 0 && !mobileNos.get(0).trim().isEmpty()){
					                  sendSMSTOAlertAssignedOfficer(designationId,govtofficerId,mobileNos!= null ? mobileNos.get(0):null,alert.getAlertId(),6L,keyVo.getUserId(),alertStatusDAO.get(keyVo.getStatusId()).getAlertStatus(),keyVo.getComment(),keyVo.getUserId());  
					            }
							}
						}
						rs.setExceptionMsg("success");
					}
				});	
			} catch (Exception e) {
				rs.setExceptionMsg("failure");
				LOG.error("Exception Occured in updateAlertStatusCommentForAms  ", e);
			}
			return rs;
		}
		 /*
         * Teju
         */
		 public List<DistrictOfficeViewAlertVO> getLocationWiseDepartmentOverviewAlertCountForAms(String fromDateStr, String toDateStr, Long stateId, 
					List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
					Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
					Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId, 
					String group,List<Long> calCntrIdList,List<Long> sublevels,Long filterParentScopeId,
					Long filterScopeValue,String searchType,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds ){
			try{
			
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Prepare Parameter
			
			List<Long> levelValues = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			List<KeyValueVO> subLevels = new ArrayList<KeyValueVO>();
			List<Object[]> childDeptScopeIdList = govtDepartmentScopeLevelDAO.getChildDeptScopeIdList(govtDepartmentId,parentGovtDepartmentScopeId);
			List<Long> deptScopeIdList = new ArrayList<Long>();
			if(childDeptScopeIdList != null && childDeptScopeIdList.size() > 0){
				for(Object [] param : childDeptScopeIdList){
					deptScopeIdList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					KeyValueVO sublevel = new KeyValueVO();
					sublevel.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
					sublevel.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
					subLevels.add(sublevel);
				}
			}
		    if(sublevels != null && sublevels.size() > 0){//In the case of filter data scope wise we are sending selected values
		    	deptScopeIdList.clear();
		    	deptScopeIdList.addAll(sublevels);
		    }
		
		 
			List<DistrictOfficeViewAlertVO> returnList = new ArrayList<DistrictOfficeViewAlertVO>();
			List<Object[]> alertList = null; 
			if(deptScopeIdList != null && deptScopeIdList.size() > 0){
				if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status") ){
					if(alertType != null && alertType.equalsIgnoreCase("alert")){
					alertList = alertAssignedOfficerNewDAO.getAlertDetailsLocationWiseBasedOnDepartmentLevel(fromDate,toDate,stateId,electronicIdList,printIdList,levelId,levelValues,govtDepartmentId,parentGovtDepartmentScopeId,deptScopeIdList,group,searchType,calCntrIdList,filterParentScopeId,filterScopeValue,socialMediaTypeIds,null,null,alertSeverityIds,alertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
					}else if(alertType != null && alertType.equalsIgnoreCase("subTask")){
					 alertList = govtAlertSubTaskDAO.getSubTaskAlertCntBasedOnDepartmentLevel(fromDate, toDate, stateId, electronicIdList, printIdList, levelId, levelValues, govtDepartmentId, parentGovtDepartmentScopeId, deptScopeIdList, group, searchType, calCntrIdList, filterParentScopeId, filterScopeValue,socialMediaTypeIds,subTaskAlertStatusIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
					}
					if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
						if(searchType != null && searchType.equalsIgnoreCase("statusWise") || searchType.equalsIgnoreCase("alertSource")){
							prepareResultForStateForAms(alertList,returnList,sortingType,order,alertType,searchType);
							sortStateLevelLstBasedOnScopeForAms(returnList,govtDepartmentId);
							return returnList;
						}
					}
				}
			}
			
			Map<Long,String> locIdAndLocNameMap = new LinkedHashMap<Long,String>();
			Map<Long,String> lvlIdAndLvlName = new LinkedHashMap<Long,String>();
			Map<Long,String> lvlIdAndColor = new LinkedHashMap<Long,String>();
			Map<Long,LinkedHashMap<Long,Long>> locIdThenLvlIdThenAlertCount = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
			LinkedHashMap<Long,Long> levelIdAndAlertCountMap = null;
			
			Set<Long> deptScopeIds = new HashSet<Long>();
			if(alertList != null && alertList.size() > 0){
				for(Object[] param : alertList){
					deptScopeIds.add(commonMethodsUtilService.getLongValueForObject(param[3]));
				}
			}
			
			List<Object[]> deptScopeIdDtlsList = null;
			if(group != null && !group.trim().isEmpty() && group.trim().equalsIgnoreCase("status") && deptScopeIds != null && deptScopeIds.size() >0){
				if(alertType != null && alertType.equalsIgnoreCase("subTask") && searchType != null && searchType.equalsIgnoreCase("statuswise")){
					deptScopeIdDtlsList =alertSubTaskStatusDAO.getAlertStatusDtlsBasidOnAlertIds(new ArrayList<Long>(deptScopeIds)); 
				}else if(searchType != null && searchType.equalsIgnoreCase("alertSource")){
					deptScopeIdDtlsList = alertCategoryDAO.getAlertCategoryByCategoryIds(new ArrayList<Long>(deptScopeIds));	
				}else if(searchType != null && searchType.equalsIgnoreCase("scopewise")){
					deptScopeIdDtlsList = govtDepartmentScopeDAO.getGovtDepartmenttScopeDetailsBasedOnScopeIds(new ArrayList<Long>(deptScopeIds));
				}else if(searchType != null && searchType.equalsIgnoreCase("statuswise")){
					deptScopeIdDtlsList = alertStatusDAO.getAlertStatusDtlsBasidOnAlertIds(new ArrayList<Long>(deptScopeIds));	
				}
			}
			if(deptScopeIdDtlsList != null && deptScopeIdDtlsList.size() > 0){
				for(Object[] param : deptScopeIdDtlsList){
					lvlIdAndLvlName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					lvlIdAndColor.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[2]));
				}  
			}
			
			if(alertList != null && alertList.size() > 0){   
				for(Object[] param : alertList){
					locIdAndLocNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]),commonMethodsUtilService.getStringValueForObject(param[2]));
					levelIdAndAlertCountMap = locIdThenLvlIdThenAlertCount.get(commonMethodsUtilService.getLongValueForObject(param[1]));
					if(levelIdAndAlertCountMap == null){
						levelIdAndAlertCountMap = new LinkedHashMap<Long,Long>();
						locIdThenLvlIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[1]), levelIdAndAlertCountMap);
					}
					levelIdAndAlertCountMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[4]));
				}
			}
			
			DistrictOfficeViewAlertVO alertCoreDashBoardVO = null;
			if(locIdThenLvlIdThenAlertCount != null && locIdThenLvlIdThenAlertCount.size() > 0){
				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : locIdThenLvlIdThenAlertCount.entrySet()){
					alertCoreDashBoardVO = new DistrictOfficeViewAlertVO();
					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
					alertCoreDashBoardVO.setName(locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? locIdAndLocNameMap.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
					buildStatusWiseTemplateForAms(alertCoreDashBoardVO,lvlIdAndLvlName,lvlIdAndColor);
					Long total = new Long(0L);
					for(DistrictOfficeViewAlertVO boardVO : alertCoreDashBoardVO.getSubList()){
						if(outerEntry.getValue() != null && outerEntry.getValue().get(boardVO.getId()) != null){
							boardVO.setCount(outerEntry.getValue().get(boardVO.getId()));
							total = total + outerEntry.getValue().get(boardVO.getId());
						}
					}
					alertCoreDashBoardVO.setTotalCount(total);
					returnList.add(alertCoreDashBoardVO);
				}
			}
			if(parentGovtDepartmentScopeId != null && parentGovtDepartmentScopeId.longValue() == 1L){
				if(returnList != null && returnList.size() > 0){
					returnList.get(0).getSubLevels().addAll(subLevels);
					if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("count")){
						if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("Default")){
 						sortStateLevelLstBasedOnScopeForAms(returnList,govtDepartmentId);
 					}else{
 						if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
 							Collections.sort(returnList.get(0).getSubList(), alertStateAscendingCountWiseSortingLvlWiseForAms);
 						}else{
 							Collections.sort(returnList.get(0).getSubList(), alertStateDescCountWiseSortingLvlWiseForAms);
 						}
 					}
					}
					if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("name")){
						if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
							Collections.sort(returnList.get(0).getSubList(), alphabeticalStateAscSortLvlWiseForAms);
						}else{
							Collections.sort(returnList.get(0).getSubList(), alphabeticalStateDescendingSortLvlWiseForAms);
						}
					}
				}   
			}else{
				if(returnList != null && returnList.size() > 0){
					returnList.get(0).getSubLevels().addAll(subLevels);
					if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("count")){
						if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
							Collections.sort(returnList, alertAscendingCountWiseSortingLvlWiseForAms);
						}else{
							Collections.sort(returnList, alertDescCountWiseSortingLvlWiseForAms);
						}
					}
					if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("name")){
						if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
							Collections.sort(returnList, alphabeticalAscSortLvlWiseForAms);
						}else{
							Collections.sort(returnList, alphabeticalDescendingSortLvlWiseForAms);
						}
					}
				}   
			}
			
			return returnList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getLocationWiseDepartmentOverviewAlertCountForAms() method of AlertManagementSystemService",e);
	 }
			return null;
	}	
	 public void buildStatusWiseTemplateForAms(DistrictOfficeViewAlertVO alertCoreDashBoardVO,Map<Long,String> lvlIdAndLvlName,Map<Long,String> lvlIdAndColor){
			try{
				List<DistrictOfficeViewAlertVO> alertCoreDashBoardVOs = new ArrayList<DistrictOfficeViewAlertVO>();
				DistrictOfficeViewAlertVO coreDashBoardVO = null;
				if(lvlIdAndLvlName != null && lvlIdAndLvlName.size() > 0 && lvlIdAndColor != null &&  lvlIdAndColor.size() > 0){
					for(Entry<Long,String> entry : lvlIdAndLvlName.entrySet()){
						coreDashBoardVO = new DistrictOfficeViewAlertVO();
						coreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(entry.getKey()));
						coreDashBoardVO.setName(commonMethodsUtilService.getStringValueForObject(entry.getValue()));
						coreDashBoardVO.setSevertyColor(lvlIdAndColor.get(entry.getKey()) != null ? lvlIdAndColor.get(entry.getKey()) : "");
						alertCoreDashBoardVOs.add(coreDashBoardVO);
					}
					alertCoreDashBoardVO.setSubList(alertCoreDashBoardVOs);  
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	 public void sortStateLevelLstBasedOnScopeForAms(List<DistrictOfficeViewAlertVO> resultList,Long govtDeptId){
     	try{
     		List<Object[]> objList = govtDepartmentScopeLevelDAO.getDeptScopeIdAndOrder(govtDeptId);
     		 if(objList != null && objList.size() > 0){
     			 for(Object[] param:objList){
     				DistrictOfficeViewAlertVO matchVO = getStatusMatchVOForAMs(resultList,commonMethodsUtilService.getLongValueForObject(param[0]));
     				  if(matchVO != null){
     					  matchVO.setOrderNo(commonMethodsUtilService.getLongValueForObject(param[1]));
     				  }
     			 }
     		 }
     		 if(resultList != null && resultList.size() > 0){
     			 Collections.sort(resultList, ascendingSortingByScopeIdsForAms);	 
     		 }
     		 
     	}catch(Exception e){
     		e.printStackTrace();
     		LOG.error("Error occured sortStateLevelLstBasedOnScope() method of AlertManagementSystemService{}");
     	}
     }
	 public DistrictOfficeViewAlertVO getStatusMatchVOForAMs(List<DistrictOfficeViewAlertVO> statusList,Long id){
			try{
				if(statusList == null || statusList.size() ==0)
					return null;
				for(DistrictOfficeViewAlertVO VO:statusList){
					if(VO.getId().equals(id))
					{
						return VO;
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				LOG.error("Error occured getStatusMatchVOForAMs() method of AlertManagementSystemService{}");
			}
			return null;
		}
	 public static Comparator<DistrictOfficeViewAlertVO> ascendingSortingByScopeIdsForAms = new Comparator<DistrictOfficeViewAlertVO>() {
        	public int compare(DistrictOfficeViewAlertVO location2, DistrictOfficeViewAlertVO location1) {
        	Long id2 = location2.getOrderNo();
        	Long id1 = location1.getOrderNo();
        	//ascending order by id.
        	 return id2.compareTo(id1);
        	}
      };
     public static Comparator<DistrictOfficeViewAlertVO> alertAscendingCountWiseSortingLvlWiseForAms = new Comparator<DistrictOfficeViewAlertVO>() {
      	public int compare(DistrictOfficeViewAlertVO location2, DistrictOfficeViewAlertVO location1) {
      	Long count2 = location2.getTotalCount();
      	Long count1 = location1.getTotalCount();
      	//ascending order of percantages.
      	 return count2.compareTo(count1);
      	}
     };
     public static Comparator<DistrictOfficeViewAlertVO> alertDescCountWiseSortingLvlWiseForAms = new Comparator<DistrictOfficeViewAlertVO>() {
        	public int compare(DistrictOfficeViewAlertVO location2, DistrictOfficeViewAlertVO location1) {
        	Long count2 = location2.getTotalCount();
        	Long count1 = location1.getTotalCount();
        	//desc order of percantages.
        	 return count1.compareTo(count2);
        	}
      };
      public static Comparator<DistrictOfficeViewAlertVO> alphabeticalDescendingSortLvlWiseForAms = new Comparator<DistrictOfficeViewAlertVO>() {
      public int compare(DistrictOfficeViewAlertVO location2, DistrictOfficeViewAlertVO location1) {
         	String name2 = location2.getName();
        	    String name1 = location1.getName();
        	    //descending order of percantages.
        	    return name1.compareTo(name2);
      	}
      };
      public static Comparator<DistrictOfficeViewAlertVO> alphabeticalAscSortLvlWiseForAms = new Comparator<DistrictOfficeViewAlertVO>() {
           	public int compare(DistrictOfficeViewAlertVO location2, DistrictOfficeViewAlertVO location1) {
           		String name2 = location2.getName();
           	    String name1 = location1.getName();
           	    //descending order of percantages.
           	    return name2.compareTo(name1);
           	}
           };
           //State level soring 
           public static Comparator<DistrictOfficeViewAlertVO> alertStateAscendingCountWiseSortingLvlWiseForAms = new Comparator<DistrictOfficeViewAlertVO>() {
            	public int compare(DistrictOfficeViewAlertVO location2, DistrictOfficeViewAlertVO location1) {
            	Long count2 = location2.getCount();
            	Long count1 = location1.getCount();
            	//ascending order of percantages.
            	 return count2.compareTo(count1);
            	}
           };
           public static Comparator<DistrictOfficeViewAlertVO> alertStateDescCountWiseSortingLvlWiseForAms = new Comparator<DistrictOfficeViewAlertVO>() {
              	public int compare(DistrictOfficeViewAlertVO location2, DistrictOfficeViewAlertVO location1) {
              	Long count2 = location2.getCount();
              	Long count1 = location1.getCount();
              	//desc order of percantages.
              	 return count1.compareTo(count2);
              	}
            };
            public static Comparator<DistrictOfficeViewAlertVO> alphabeticalStateDescendingSortLvlWiseForAms = new Comparator<DistrictOfficeViewAlertVO>() {
            public int compare(DistrictOfficeViewAlertVO location2, DistrictOfficeViewAlertVO location1) {
               	String name2 = location2.getName();
              	    String name1 = location1.getName();
              	    //descending order of percantages.
              	    return name1.compareTo(name2);
            	}
            };
            public static Comparator<DistrictOfficeViewAlertVO> alphabeticalStateAscSortLvlWiseForAms = new Comparator<DistrictOfficeViewAlertVO>() {
                 	public int compare(DistrictOfficeViewAlertVO location2, DistrictOfficeViewAlertVO location1) {
                 		String name2 = location2.getName();
                 	    String name1 = location1.getName();
                 	    //descending order of percantages.
                 	    return name2.compareTo(name1);
                 	}
            };
           
            public List<DistrictOfficeViewAlertVO> prepareResultForStateForAms(List<Object[]> alertList,List<DistrictOfficeViewAlertVO> returnList,String sortingType,String order,String alertType,String searchType){
            	try{
            		
            		Map<Long,String> lvlIdAndLvlName = new LinkedHashMap<Long,String>();
        			Map<Long,String> statusIdAndStatusName = new LinkedHashMap<Long,String>();
        			Map<Long,String> statusIdAndColor = new LinkedHashMap<Long,String>();    
        			
        			Map<Long,LinkedHashMap<Long,Long>> lvlIdThenStatusIdThenAlertCount = new LinkedHashMap<Long,LinkedHashMap<Long,Long>>();
        			LinkedHashMap<Long,Long> statusIdThenAlertCount = null;
        			
        			
        			Set<Long> deptScopeIds = new HashSet<Long>();
        			Set<Long> statusIds = new HashSet<Long>();//Containing status id or alert categoryId 
        			Long stateId = 0l;
        			if(alertList != null && alertList.size() > 0){
        				for(Object[] param : alertList){
        					deptScopeIds.add(commonMethodsUtilService.getLongValueForObject(param[3]));
        					stateId = commonMethodsUtilService.getLongValueForObject(param[1]);
        				}
        			}
        			
        			if(alertList != null && alertList.size() > 0){
        				for(Object[] param : alertList){
        					statusIds.add(commonMethodsUtilService.getLongValueForObject(param[4]));
        				}
        			}
        			List<Object[]> statusIdDtlsList = null;
        			if(statusIds != null && statusIds.size() > 0){
        				if(alertType != null && alertType.equalsIgnoreCase("subTask") && searchType != null && searchType.equalsIgnoreCase("statuswise")){
        					 statusIdDtlsList=alertSubTaskStatusDAO.getAlertStatusDtlsBasidOnAlertIds(new ArrayList<Long>(statusIds));    					
        				}else if(searchType != null && searchType.equalsIgnoreCase("alertSource")){
        					 statusIdDtlsList = alertCategoryDAO.getAlertCategoryByCategoryIds(new ArrayList<Long>(statusIds));	
        				}else if(searchType.equalsIgnoreCase("statuswise")){
        					 statusIdDtlsList = alertStatusDAO.getAlertStatusDtlsBasidOnAlertIds(new ArrayList<Long>(statusIds));
        				}
        			}
        			
        			List<Object[]> deptScopeIdDtlsList = null;
        			if(deptScopeIds != null && deptScopeIds.size() >0){
        				deptScopeIdDtlsList = govtDepartmentScopeDAO.getGovtDepartmenttScopeDetailsBasedOnScopeIds(new ArrayList<Long>(deptScopeIds));
        			}
        			
        			
        			
        			if(deptScopeIdDtlsList != null && deptScopeIdDtlsList.size() > 0){
        				for(Object[] param : deptScopeIdDtlsList){
        					lvlIdAndLvlName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
        				}  
        			}
        			if(statusIdDtlsList != null && statusIdDtlsList.size() > 0){
        				for(Object[] param : statusIdDtlsList){
            				statusIdAndStatusName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
            				statusIdAndColor.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[2]));
        				}  
        			}
        			
        			
        			
            		if(alertList != null && alertList.size() > 0){
            			for(Object[] param : alertList){
            				statusIdThenAlertCount = lvlIdThenStatusIdThenAlertCount.get(commonMethodsUtilService.getLongValueForObject(param[3]));
        					if(statusIdThenAlertCount == null){
        						statusIdThenAlertCount = new LinkedHashMap<Long,Long>();
        						lvlIdThenStatusIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[3]), statusIdThenAlertCount);
        					}
        					statusIdThenAlertCount.put(commonMethodsUtilService.getLongValueForObject(param[4]), commonMethodsUtilService.getLongValueForObject(param[5]));
        				}
            		}
            		//calculate status wise total count.
        			Map<Long,Long> statusIdAndTotalCount = new HashMap<Long,Long>();
        			if(lvlIdThenStatusIdThenAlertCount != null && lvlIdThenStatusIdThenAlertCount.size() > 0){
        				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : lvlIdThenStatusIdThenAlertCount.entrySet()){
        					for(Entry<Long,Long> innerEntry : outerEntry.getValue().entrySet()){
        						statusIdAndTotalCount.put(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey()), 0L);
        					}
        				}
        			}
        			if(lvlIdThenStatusIdThenAlertCount != null && lvlIdThenStatusIdThenAlertCount.size() > 0){
        				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : lvlIdThenStatusIdThenAlertCount.entrySet()){
        					for(Entry<Long,Long> innerEntry : outerEntry.getValue().entrySet()){
        						statusIdAndTotalCount.put(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey()), statusIdAndTotalCount.get(commonMethodsUtilService.getLongValueForObject(innerEntry.getKey()))+commonMethodsUtilService.getLongValueForObject(innerEntry.getValue()));
        					}
        				}
        			}
            		
        			DistrictOfficeViewAlertVO alertCoreDashBoardVO = null;
        			if(lvlIdThenStatusIdThenAlertCount != null && lvlIdThenStatusIdThenAlertCount.size() > 0){
        				for(Entry<Long,LinkedHashMap<Long,Long>> outerEntry : lvlIdThenStatusIdThenAlertCount.entrySet()){
        					alertCoreDashBoardVO = new DistrictOfficeViewAlertVO();
        					alertCoreDashBoardVO.setId(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey()));
        					alertCoreDashBoardVO.setName(lvlIdAndLvlName.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) != null ? lvlIdAndLvlName.get(commonMethodsUtilService.getLongValueForObject(outerEntry.getKey())) : "");
        					buildStatusWiseTemplateForAms(alertCoreDashBoardVO,statusIdAndStatusName,statusIdAndColor);
        					Long total = new Long(0L);
        					for(DistrictOfficeViewAlertVO boardVO : alertCoreDashBoardVO.getSubList()){
        						if(outerEntry.getValue() != null && outerEntry.getValue().get(boardVO.getId()) != null){
        							boardVO.setCount(outerEntry.getValue().get(boardVO.getId()));
        							total = total + outerEntry.getValue().get(boardVO.getId());
        						}
        					}
        					alertCoreDashBoardVO.setTotalCount(total);
        					alertCoreDashBoardVO.setStateId(stateId);
        					returnList.add(alertCoreDashBoardVO);
        				}
        			}
        			if(returnList != null && returnList.size() > 0){
        				
        				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("count")){
        					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("Default")){
        					}else{
        						if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
            						Collections.sort(returnList, alertAscendingCountWiseSortingLvlWiseForAms);
            					}else{
            						Collections.sort(returnList, alertDescCountWiseSortingLvlWiseForAms);
            					}
            				}
        				}
        				if(sortingType != null && !sortingType.trim().isEmpty() && sortingType.trim().equalsIgnoreCase("name")){
        					if(order != null && !order.trim().isEmpty() && order.trim().equalsIgnoreCase("asc")){
        						Collections.sort(returnList, alphabeticalAscSortLvlWiseForAms);
        					}else{
        						Collections.sort(returnList, alphabeticalDescendingSortLvlWiseForAms);
        					}
        				}
        			}
        			
        			if(statusIdAndTotalCount.size() > 0){
        				if(returnList != null && returnList.size() > 0){
        					DistrictOfficeViewAlertVO altCorevo = returnList.get(0);
        					for(DistrictOfficeViewAlertVO param : altCorevo.getSubList()){
        						if(statusIdAndTotalCount.get(param.getId()) != null){
        							param.setGrandTotal(statusIdAndTotalCount.get(param.getId()));
        						}
        					}
        				}
        			}
        			
        			return returnList;
            	}catch(Exception e){
            		e.printStackTrace();
            	}
            	return null;
        }
       public ResultStatus saveDocumentsForAlertForAms(final AmsAppLoginVO keyVo){

    			final ResultStatus resultStatus = new ResultStatus();
    			try {
    				
    				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
    					public void doInTransactionWithoutResult(TransactionStatus status) {
    			String folderName = folderCreationForAlertsAttachmentNew();
    			CustomReport customReport = null;
    			
    			Calendar calendar = Calendar.getInstance();
    			calendar.setTime(new Date());
    			 int year = calendar.get(Calendar.YEAR);
    			 int month = calendar.get(Calendar.MONTH);
    			// int day = calendar.get(Calendar.DAY_OF_MONTH);
    			 int temp = month+1;
    			 String monthText = getMonthForInt(temp);
    			 SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_VALUE);
    			 String dateStr = sdf.format(new Date());
    			 String yearStr = String.valueOf(year);
    			 
    			 StringBuilder str ;
    			 
    			 if(keyVo.getImages() != null && keyVo.getImages().size() > 0){
    				 AlertAssignedOfficerNew aaon = alertAssignedOfficerNewDAO.getModelForAlert(keyVo.getAlertId()).get(0);
    				 for (String imageStr : keyVo.getImages()){
    					 str = new StringBuilder();
    					 Integer randomNumber = RandomNumberGeneraion.randomGenerator(8);
    					 String destPath = folderName+"/"+randomNumber+".jpg";
    					 boolean statusForImage = imageAndStringConverter.convertBase64StringToImage(imageStr,destPath);
    					
    					 
    						if(statusForImage)
							{
    							resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
    							LOG.error(" File saveing successfully ..");
							}
							else
							{
								resultStatus.setResultCode(ResultCodeMapper.FAILURE);
    							LOG.error(" Exception Raise in copying file..");
							}
    						
    						AlertDepartmentDocumentNew addn = new AlertDepartmentDocumentNew();
    						addn.setDocument(destPath);
    						addn.setInsertedBy(keyVo.getUserId());
    						addn.setInsertedTime(dateUtilService.getCurrentDateAndTime());
    						addn = alertDepartmentDocumentNewDAO.save(addn);
    						
    						//save record in tracking
    						saveRecordIntoTracking(aaon,keyVo.getUserId(),addn.getAlertDepartmentDocumentId()+"",3l);
    						//getAlertDetailsAndSendSMS(alertId,3L,userId);
    				 }
    			 }
    			
    			 resultStatus.setExceptionMsg("success");
    					}
    				});
    			}catch (Exception e) {
    				resultStatus.setExceptionMsg("failure");
    				LOG.error(" Exception Occured in uploadDocumentsForAlertForAms() method, Exception - ",e);
    			}
    			return resultStatus;
    		
    		}
       public List<IdNameVO> getAlertDetailsOfCategoryByStatusWise(JalavaniVO mainVo){
 		  List<IdNameVO> finalList = new ArrayList<IdNameVO>(0);
 		  try {
 			  
 			  Date fromDate = null;
 				Date toDate = null;
 				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
 				
 				if(mainVo.getFromDate() != null && mainVo.getFromDate().trim().length() > 0 && mainVo.getToDate() != null && mainVo.getToDate().trim().length() > 0){
 					fromDate = sdf.parse(mainVo.getFromDate().trim());
 					toDate = sdf.parse(mainVo.getToDate().trim());
 				}
 				
 			  List<Object[]> statusObj = alertDAO.getAlertDetailsOfCategoryByStatusWise(fromDate,toDate,mainVo.getDeptId(),mainVo.getYear());
 			  if(statusObj !=null && statusObj.size()>0){
 				  for (Object[] object : statusObj) {					
 					  IdNameVO VO = new IdNameVO();
 					  VO.setId(commonMethodsUtilService.getLongValueForObject(object[0]));
 					  VO.setName(commonMethodsUtilService.getStringValueForObject(object[1]));
 					  VO.setColor(commonMethodsUtilService.getStringValueForObject(object[2]));
 					  VO.setCount(commonMethodsUtilService.getLongValueForObject(object[3]));
 					  
 					  finalList.add(VO);					  
 				}
 			  }
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 			LOG.error("Error occured getAlertDetailsOfCategoryByStatusWise() method of AlertManagementSystemService");
 		}
 		  return finalList;
 	  }
 	  
 	  public List<IdNameVO> getAlertFeedbackStatusDetails(JalavaniVO mainVo){
 		  List<IdNameVO> finalList = new ArrayList<IdNameVO>(0);
 		  try {
 			  
 			 String fromDateStr = mainVo.getFromDate();
 			String toDateStr = mainVo.getToDate();
 			  
 			  Date fromDate = null;
 				Date toDate = null;
 				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
 				if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
 					fromDate = sdf.parse(fromDateStr);
 					toDate = sdf.parse(toDateStr);
 				}
 				
 				List<Object[]> statusObj = alertDAO.getAlertFeedbackStatusDetails(fromDate,toDate,mainVo.getDeptId(),mainVo.getYear());
 				  if(statusObj !=null && statusObj.size()>0){
 					  for (Object[] object : statusObj) {					
 						  IdNameVO VO = new IdNameVO();
 						  VO.setId(commonMethodsUtilService.getLongValueForObject(object[0]));
 						  VO.setName(commonMethodsUtilService.getStringValueForObject(object[1]));
 						  VO.setCount(commonMethodsUtilService.getLongValueForObject(object[2]));
 						  
 						  finalList.add(VO);					  
 					}
 				  }
 				
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 			LOG.error("Error occured getAlertFeedbackStatusDetails() method of AlertManagementSystemService");
 		}
 		  return finalList;
 	  }
 	  
 	 /* public List<AmsDataVO> getAlertsOfCategoryByStatusWise(JalavaniVO mainVo) {
 		  List<AmsDataVO> finalVoList = new ArrayList<AmsDataVO>(0);
 		  try {
 			  
 			  //Fields From DTO
 			 String fromDateStr = mainVo.getFromDate();
 			 String toDateStr =  mainVo.getToDate();
 			 int stIndex = mainVo.getStartIndex();
 			 int endIndex = mainVo.getEndIndex();
 			 String type =   mainVo.getType();
 			 String year = mainVo.getYear();
 			
 			  Date fromDate = null;
 				Date toDate = null;
 				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
 				if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
 					fromDate = sdf.parse(fromDateStr);
 					toDate = sdf.parse(toDateStr);
 				}
 			  
 			  List<Long> alertIdList = new ArrayList<Long>(0);
 			  if(type !=null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("status")){
 				  alertIdList = alertDAO.getAlertsOfCategoryByStatusWise(fromDate , toDate,mainVo.getDeptId(),mainVo.getStatusIds(),stIndex,endIndex,year);
 			  }else if(type !=null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("feedback")){
 				  alertIdList = alertDAO.getAlertsOfFeedbackStatus(fromDate,toDate,mainVo.getDeptId(),mainVo.getStatusIds(),stIndex,endIndex,year);
 			  }
 			  
 			  	if(alertIdList != null && alertIdList.size() > 0){
 					List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIdList));
 					setAlertDtlsForAms(finalVoList, list); 
 				}
 				setSubListCountForAms(finalVoList, alertIdList);
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 			LOG.error("Error occured getAlertsOfCategoryByStatusWise() method of AlertManagementSystemService");
 		}
 		  return finalVoList;
 	  }*/
 	  
 	 public List<KeyValueVO> getLocationWiseAlertStatusCounts(Long departmentId,String fromDateStr,String toDateStr,String year,Long locationTypeId,
 			 List<Long> locationValues,Long searchLevelId,List<Long> searchLevelValues){
  		List<KeyValueVO> voList = new ArrayList<KeyValueVO>(0);
  		 try {
  			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
  			 Date fromDate = null,toDate = null;
 			if(fromDateStr != null && !fromDateStr.trim().isEmpty() && toDateStr != null && !toDateStr.trim().isEmpty()){
 				fromDate = sdf.parse(fromDateStr);
 				toDate = sdf.parse(toDateStr);
 			}
 			
 			Map<Long,KeyValueVO> finalMap = new HashMap<Long, KeyValueVO>();
 			
 			List<AlertStatus> allStatus = alertStatusDAO.getAll();
 			
 			//0-locationId,1-location,2-statusId,3-status,4.color,5-count
 			List<Object[]> objList = alertDAO.getLocationWiseAlertStatusCountsNew(fromDate,toDate,departmentId,year,locationTypeId,locationValues,searchLevelId,searchLevelValues);
 			
 			if(objList != null && objList.size() > 0){
 				for (Object[] objects : objList) { 					
 					if(finalMap.get((Long)objects[0]) == null){
 						KeyValueVO voIn = new KeyValueVO();
 						voIn.setId((Long)objects[0]);
 						voIn.setName(objects[1].toString());
 						
 						getAlertStatusSkelton(allStatus,voIn,(Long)objects[2],(Long)objects[5]);
 						finalMap.put((Long)objects[0], voIn);
 					}else{
 						KeyValueVO matchedStatusVO = getMatchedStatusVONew(finalMap.get((Long)objects[0]).getList(),(Long)objects[2]);
 						matchedStatusVO.setCount((Long)objects[5]);
 					} 
 				} 				
 				voList.addAll(finalMap.values());
 			}
 			
 		} catch (Exception e) {
 			LOG.error("Error occured getLocationWiseAlertStatusCounts() method of AlertManagementSystemService",e);
 		}
  		 return voList;
  	 }
 	 
 	public List<AmsDataVO> getAlertsOfCategoryByStatusWise(JalavaniVO mainVo){
 		//Long departmentId,String fromDateStr,String toDateStr,String year,Long locationTypeId,
		 //List<Long> locationValues,List<Long> statusIds ,int startIndex,int endIndex
 		 List<AmsDataVO> finalVoList = new ArrayList<AmsDataVO>(0);
 		 try {
 			 
 			 String fromDateStr = mainVo.getFromDate();
 			 String toDateStr =  mainVo.getToDate();
 			 int stIndex = mainVo.getStartIndex();
 			 int endIndex = mainVo.getEndIndex();
 			 String type =   mainVo.getType();
 			 String year = mainVo.getYear();
 			 
 			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
 			 Date fromDate = null,toDate = null;
			if(fromDateStr != null && !fromDateStr.trim().isEmpty() && toDateStr != null && !toDateStr.trim().isEmpty()){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			//0-locationId,1-location,2-statusId,3-status,4.color,5-count
			List<Long> alertIdList = alertDAO.getLocationWiseAlertStatusDetailsInfo(fromDate,toDate,mainVo.getDeptId(),year,mainVo.getLocationId(),mainVo.getLevelValues(),mainVo.getStatusIds(),
					stIndex,endIndex);
			
			if(alertIdList != null && alertIdList.size() > 0){
					List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIdList));
					setAlertDtlsForAms(finalVoList, list); 
				}
				setSubListCountForAms(finalVoList, alertIdList);
			
				
		} catch (Exception e) {
			LOG.error("Error occured getAlertsOfCategoryByStatusWise() method of AlertManagementSystemService",e);
		}
 		 return finalVoList;
 	 }
 	 
 	 public KeyValueVO getMatchedStatusVONew(List<KeyValueVO> statusList,Long statusId){
 		 if(statusList != null && statusList.size() > 0){
 			for ( KeyValueVO  kvVO : statusList) {
 				if(kvVO.getId().equals(statusId))
 					return kvVO;
 			} 
 		 }
 		 return null;
 	 }
 	 
 	 public void getAlertStatusSkelton(List<AlertStatus> allStatus,KeyValueVO vo,Long statusId,Long count){
 		 for (AlertStatus alertStatus : allStatus) {
			if(alertStatus.getAlertStatusId() > 1l){
				KeyValueVO subVO = new KeyValueVO();
				subVO.setId(alertStatus.getAlertStatusId());
				subVO.setName(alertStatus.getAlertStatus());
				if(alertStatus.getAlertStatusId().equals(statusId))
					subVO.setCount(count);
				vo.getList().add(subVO);
			}
		}
 	 }
 	 
 	/**
 	 * @param  String mobileNo
 	 * @param String otp
 	 * @return String status
 	 * @author Santosh 
 	 * @Description :This Service Method is used to validate OTP. 
 	 * @since 20-JUNE-2017
 	 */
	public String validateOTP(final String mobileNo, final String otp) {
		String status = "failure";
		try {
			Date currentTime = dateUtilService.getCurrentDateAndTime();
			List<Object[]> existingOTPDtls = amsOfficerOtpDetailsDAO.isExistOTPDetails(mobileNo, currentTime);
			if (existingOTPDtls != null && existingOTPDtls.size() > 0L) {
				Object[] obj = existingOTPDtls.get(existingOTPDtls.size() - 1);
				Long tabDetsId = commonMethodsUtilService.getLongValueForObject(obj[3]);
				String originalotp = commonMethodsUtilService.getStringValueForObject(obj[0]);
				if (originalotp != null && originalotp.toString().trim().equalsIgnoreCase(otp.toString().trim()) && tabDetsId != null && tabDetsId.longValue() > 0l) {
					status = "success";
				}
			}
		} catch (Exception e) {
			status = "failure";
			LOG.error("Exception Occured in validateOTP() in AlertManagementSystemService class.",e);
		}
		return status;
	}
	/**
	 * @param Long userId
 	 * @param  String mobileNo
 	 * @param String otp
 	 * @return String status
 	 * @author Santosh 
 	 * @Description :This Service Method is used to update mobile number. 
 	 * @since 20-JUNE-2017
 	 */
	public String updateMobileNo(final Long userId, final String otp,final String mobileNo) {
		String status = null;
		try {
			status = (String) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					Date currentTime = dateUtilService.getCurrentDateAndTime();

					List<Object[]> existingOTPDtls = amsOfficerOtpDetailsDAO.isExistOTPDetails(mobileNo,currentTime);
					if (existingOTPDtls != null && existingOTPDtls.size() > 0L) {
						Object[] obj = existingOTPDtls.get(existingOTPDtls.size() - 1);
						Long tabDetsId = commonMethodsUtilService.getLongValueForObject(obj[3]);
						String originalotp = commonMethodsUtilService.getStringValueForObject(obj[0]);
						// update otp
						if (originalotp.toString().trim().equalsIgnoreCase(otp.toString().trim()) && tabDetsId != null && tabDetsId.longValue() > 0l) {
							AmsOfficerOtpDetails amsOfficerOtpDetails = amsOfficerOtpDetailsDAO.get(tabDetsId);
							amsOfficerOtpDetails.setIsValid("N");
							amsOfficerOtpDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							amsOfficerOtpDetails = amsOfficerOtpDetailsDAO.save(amsOfficerOtpDetails);

							// update mobile number
							List<Object[]> officerDtlsObjLst = govtDepartmentDesignationOfficerDetailsNewDAO.getOfficerDesingationDetails(userId);
							if (officerDtlsObjLst != null && officerDtlsObjLst.size() > 0) {
								Long officerId = (Long) officerDtlsObjLst.get(0)[1];
								GovtOfficerNew govtOfficerNew = govtOfficerNewDAO.get(officerId);
								govtOfficerNew.setMobileNo(mobileNo);
								govtOfficerNewDAO.save(govtOfficerNew);
							}
						}
					}
					return "success";
				}
			});
		} catch (Exception e) {
			status = "failure";
			LOG.error("Exception Occured in updateMobileNo() in AlertManagementSystemService class.",e);
		}
		return status;
	}
	public List<AlertVO> getHamletWiseIvrStatusCounts(String fromDateStr,String toDateStr,String year,List<Long> locationValues,Long locationTypeId,
			Long serchLevelId,List<Long> searchLevelValues){
 		List<AlertVO> finalList = new ArrayList<AlertVO>();
 		
 		try{
 			Date frmDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				frmDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
 			//0-locationId,1-locationName,2-hamlet_id,3-satisfied_status,4-answers_count
 			List<Object[]> ivrCounts = alertAssignedOfficerNewDAO.getHamletWiseIvrStatusCounts(frmDate, toDate, year, locationValues,locationTypeId,
 					serchLevelId, searchLevelValues);
 			
 			finalList = setIvrDetailsToList(ivrCounts,finalList);
 			
 		}catch (Exception e) {
			e.printStackTrace();
		}
 		return finalList;
 		}
	
	public List<AlertVO> setIvrDetailsToList(List<Object[]> ivrCounts,List<AlertVO> finalList){
		try {
			
			Map<Long,Map<Long,AlertVO>> typeMap = new HashMap<Long,Map<Long,AlertVO>>();
			
			if(ivrCounts !=null && ivrCounts.size()>0){
				for (Object[] objects : ivrCounts) {
	 				Map<Long,AlertVO> hamletMap = typeMap.get((Long)objects[0]);
	 				if(hamletMap==null){
	 					hamletMap = new HashMap<Long, AlertVO>();
	 					typeMap.put((Long)objects[0], hamletMap);
	 				}	 				
	 				AlertVO vo = hamletMap.get((Long)objects[2]);
	 				if(vo==null){
	 					vo = new AlertVO();
	 					vo.setId((Long)objects[2]);
	 					hamletMap.put((Long)objects[2], vo);
	 				}
 					vo.setName(objects[1] !=null ? objects[1].toString():null);//locationName
 				
 					if(objects[3]!=null && objects[3].toString().equalsIgnoreCase("Y")){
 						vo.setSatisfiedCount(objects[4] !=null ? (Long)objects[4]:0l);
 						
 					}else if(objects[3]!=null && objects[3].toString().equalsIgnoreCase("N")){
 						vo.setUnSatisfiedCount(objects[4] !=null ? (Long)objects[4]:0l);
 					}
	 			}	
			}
 			if(typeMap!=null && typeMap.size()>0){
 				for (Entry<Long, Map<Long, AlertVO>> objects : typeMap.entrySet()) {
 					
 					Long greenHamletCount=0l;
 	 				Long orangeHamletCount=0l;
 	 				Long redHamletCount=0l;
 					
 					AlertVO typeVO = new AlertVO();
 					typeVO.setId(objects.getKey());
 					Map<Long, AlertVO> hamletMap = objects.getValue();
 					
 					List<AlertVO> hamletList = new  ArrayList<AlertVO>(hamletMap.values());
 					
 					if(hamletList!=null && hamletList.size()>0){
 						for (AlertVO alertVO : hamletList) {
 							
 							Long satisfyCount = alertVO.getSatisfiedCount() !=null ? alertVO.getSatisfiedCount():0l;
 		 					Long unSatisfyCount = alertVO.getUnSatisfiedCount()!=null ? alertVO.getUnSatisfiedCount():0l;
 		 					
 		 					alertVO.setCount(satisfyCount+unSatisfyCount);
 		 					
 		 					String perc = cadreDetailsService.calculatePercentage(alertVO.getCount(),satisfyCount);
 		 					if(perc !=null && perc !="0" && !perc.trim().isEmpty()){
 		 						alertVO.setPercentage(Double.parseDouble(perc));
 		 						
 		 						if(alertVO.getPercentage()>=80){
 		 							greenHamletCount++;
 		 						}else if(alertVO.getPercentage()<80 && alertVO.getPercentage()>=50){
 		 							orangeHamletCount++;
 		 						}else if(alertVO.getPercentage()<50){
 		 							redHamletCount++;
 		 						}
 		 					}
						}
 						typeVO.setName(hamletList.get(0).getName());
 					}
 					
 					Long total = greenHamletCount+orangeHamletCount+redHamletCount;
 		            double greenPer = (Double.parseDouble(cadreDetailsService.calculatePercentage(total, greenHamletCount)));
 		            double orangePer = (Double.parseDouble(cadreDetailsService.calculatePercentage(total, orangeHamletCount)));
 		            double redPer = (Double.parseDouble(cadreDetailsService.calculatePercentage(total, redHamletCount)));
 					
 					
 					AlertVO green = new AlertVO(); 
 	 				green.setName("green");
 	 				green.setCount(greenHamletCount);
 	 				green.setPercentage(greenPer);
 	 				AlertVO orange = new AlertVO(); 
 	 				orange.setName("orange");
 	 				orange.setCount(orangeHamletCount);
 	 				orange.setPercentage(orangePer);
 	 				AlertVO red = new AlertVO();
 	 				red.setName("red");
 	 				red.setCount(redHamletCount);
 	 				red.setPercentage(redPer);
 	 				
 	 				typeVO.getSubList1().add(green);
 	 				typeVO.getSubList1().add(orange);
 	 				typeVO.getSubList1().add(red);
 					
 	 				finalList.add(typeVO);
				}
 			}
			
		} catch (Exception e) {
			LOG.error("Error occured setIvrDetailsToList() method of AlertManagementSystemService",e);
		}
		return finalList;
	}
 	
	/*
	   * Date : 23/06/2017
	   * Author :Teja
	   * @description : getDistrictWiseInfoForAms(Getting district and mandal data for ams app)
	   */
public AmsKeyValueVO getDistrictWiseInfoForAms(Long departmentId,Long LevelId,Long levelValue){
		
		AmsKeyValueVO finalVO = new AmsKeyValueVO();
		List<AmsKeyValueVO> returnList = new ArrayList<AmsKeyValueVO>();
		
		try {
			
			List<Object[]> objList = govtDepartmentWorkLocationRelationDAO.getGovtSubLevelInfo(levelValue);
			if(objList !=null && objList.size()>0){
				for (Object[] obj : objList) {
					
					AmsKeyValueVO VO = getGovtSubLevelMatchedVoForAMS(returnList,(Long)obj[0]);
					if(VO == null){
						VO = new AmsKeyValueVO();
						VO.setId((Long)obj[0]);
						VO.setName(obj[1] !=null ? obj[1].toString():"");
						returnList.add(VO);
					}
					
					AmsKeyValueVO subVo = new AmsKeyValueVO();					
					subVo.setId((Long)obj[2]);
					subVo.setName(obj[3] !=null ? obj[3].toString():"");
					
					VO.getIdnameList().add(subVo);
					
				}
			}
			
			if(returnList !=null && returnList.size()>0)
				finalVO = returnList.get(0);
			
		} catch (Exception e) {
			LOG.error("Error occured getDistrictWiseInfoForAms() method of AlertManagementSystemService",e);
		}
		
		return finalVO;
	}
	public AmsKeyValueVO getGovtSubLevelMatchedVoForAMS(List<AmsKeyValueVO> returnList,Long levelId){
		try{
			if(returnList == null || returnList.size() ==0)
				return null;
			for(AmsKeyValueVO VO:returnList){
				if(VO.getId().equals(levelId))
				{
					return VO;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getGovtSubLevelMatchedVoForAMS() method of AlertManagementSystemService{}");
		}
		return null;
	}
	/*
	 * Teja(non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getTotalAlertByOtherStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId)
	 */
	public List<DistrictOfficeViewAlertVO> getTotalAlertByOtherStatusForAMS(String fromDateStr, String toDateStr,
			Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,
			Long statusId,Long userId,Long govtDeptScopeId,Long deptId,List<Long> calCntrIdList,
			List<Long> impactLevelIdList,List<Long> priorityIdList,
			List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,
			List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds){
		LOG.info("Entered in getTotalAlertByOtherStatusForAMS() method of AlertManagementSystemService{}");
		try{
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			prepareRequiredParameter(printIdList,electronicIdList,calCntrIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);//Preparing Parameter
			
			List<Long> levelValuesList = new ArrayList<Long>();    
			Long levelId = 0L;
			List<Object[]> lvlValueAndLvlIdList = govtAlertDepartmentLocationNewDAO.getUserAccessLevels(userId);
			if(lvlValueAndLvlIdList != null && lvlValueAndLvlIdList.size() > 0){
				for(Object[] param : lvlValueAndLvlIdList){
					levelValuesList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
					levelId = commonMethodsUtilService.getLongValueForObject(param[0]);
				}
			}
			
			List<DistrictOfficeViewAlertVO> districtOfficeViewAlertVOLst = new ArrayList<DistrictOfficeViewAlertVO>();
			List<Long> alertIdSet = alertAssignedOfficerNewDAO.getTotalAlertByOtherStatus(fromDate,toDate,stateId,printIdList,electronicIdList,deptIdList,statusId,levelId,levelValuesList,govtDeptScopeId,deptId,calCntrIdList,impactLevelIdList,priorityIdList,alertSourceIdList,printMediaIdList,electronicMediaIdList,socialMediaTypeIds,mondayGrievanceTypeIds,janmabhoomiTypeIds,specialGrievanceTypeIds,generalGrievanceTypeIds);
			if(alertIdSet != null && alertIdSet.size() > 0){
				List<Object[]> list = alertDAO.getAlertDtls(new HashSet<Long>(alertIdSet));
				setTotalAlertDtlsForAms(districtOfficeViewAlertVOLst, list); 
			}
			//set Subtask into alert logic 
			//get subtask count.
			List<Object[]> subtaskCountList = null;
			if(alertIdSet != null && alertIdSet.size() > 0){
				subtaskCountList = govtAlertSubTaskDAO.getSubTaskCount(alertIdSet);
			}
			//create a map from alertId and subtask count.
			Map<Long,Long> alertIdAndSubTaskCountMap = new HashMap<Long,Long>();
			if(subtaskCountList != null && subtaskCountList.size() > 0){
				for(Object[] param : subtaskCountList){
					alertIdAndSubTaskCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			if(districtOfficeViewAlertVOLst != null && districtOfficeViewAlertVOLst.size() > 0){
				for(DistrictOfficeViewAlertVO districtOfficeViewAlertVO : districtOfficeViewAlertVOLst){
					if(alertIdAndSubTaskCountMap.get(districtOfficeViewAlertVO.getId()) != null){
						districtOfficeViewAlertVO.setSubTaskCount(alertIdAndSubTaskCountMap.get(districtOfficeViewAlertVO.getId()));
					}
				}
			}
			return districtOfficeViewAlertVOLst;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getTotalAlertByOtherStatusForAMS() method of AlertManagementSystemService{}");
		}
		return null;  
	}
	public void setTotalAlertDtlsForAms(List<DistrictOfficeViewAlertVO> amsDataVoList, List<Object[]> alertList){
		try{
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date today = dateUtilService.getCurrentDateAndTime();
			String td = myFormat.format(today);
			Long dist = 0l;
			Long statusId = 0L;  
			DistrictOfficeViewAlertVO vo = null;  
			String alertSource = "";
			if(alertList != null && alertList.size() > 0){  
				for(Object[] param : alertList ){
					vo = new DistrictOfficeViewAlertVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					vo.setCreatedDate(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10));
					vo.setUpdatedDate(commonMethodsUtilService.getStringValueForObject(param[2]).substring(0, 10));
					vo.setStatusId(commonMethodsUtilService.getLongValueForObject(param[3]));
					vo.setStatus(commonMethodsUtilService.getStringValueForObject(param[4]));
					vo.setSevertyColor(commonMethodsUtilService.getStringValueForObject(param[24]));
					vo.setStatusColor(commonMethodsUtilService.getStringValueForObject(param[25]));
					if(param.length > 26){
						vo.setProblem(commonMethodsUtilService.getStringValueForObject(param[26]));
						vo.setRelatedTo(commonMethodsUtilService.getStringValueForObject(param[27]));
					}
					statusId = commonMethodsUtilService.getLongValueForObject(param[3]);
					if(param[1] != null && param[2] != null){
						if(statusId == 4L || statusId == 5L || statusId == 6L || statusId == 7L){
							dist = dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10),commonMethodsUtilService.getStringValueForObject(param[2]).substring(0, 10));
						}else{
							dist = dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(param[1]).substring(0, 10),td);
						}  
						vo.setInterval(dist);
					}
					vo.setAlertLevel(commonMethodsUtilService.getStringValueForObject(param[8]));
					vo.setTitle(commonMethodsUtilService.getStringValueForObject(param[9]));    
					
					if(param[23] != null){
						vo.setLocation(commonMethodsUtilService.getStringValueForObject(param[23]));	
					}else if(param[22] != null){
						vo.setLocation(commonMethodsUtilService.getStringValueForObject(param[22]));	
					}else if(param[10] != null){
						vo.setLocation(commonMethodsUtilService.getStringValueForObject(param[10]));	
					}else if(param[11] != null){
						vo.setLocation(commonMethodsUtilService.getStringValueForObject(param[11]));	
					}else if(param[20] != null){
						vo.setLocation(commonMethodsUtilService.getStringValueForObject(param[20]));
					}

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
						vo.setSource(alertSource);
					 
						amsDataVoList.add(vo);
					
				}  
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error(" Exception Occured in setTotalAlertDtlsForAms() method, Exception - ",e);
		}
	}
	public List<AmsKeyValueVO> getUserAccessedDepartmentsForAMS(Long userId){
		List<AmsKeyValueVO> returnList = new ArrayList<AmsKeyValueVO>();
		try {
									
			List<Object[]> list = govtAlertDepartmentLocationNewDAO.getDeptIdAndNameForUserAccessLevel(userId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					AmsKeyValueVO vo = new AmsKeyValueVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured getUserAccessedDepartmentsForAMS() method of AlertManagementSystemService",e);
		}
		return returnList;
	}
	  /*
	   * Date : 23/06/2017
	   * Author :Teja
	   * @description : getTotalAlertDetailsForConstituencyInfo(Getting Alert details for constituency page)
	   */
	public  LocationAlertVO getTotalAlertDetailsForConstituencyInfo(String fromDateStr ,String toDateStr,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year){
		LocationAlertVO finalVO = new LocationAlertVO();
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Object[]> alertStatusLst = alertDAO.getAlertStatusWiseDetailsForConstituencyInfo(fromDate, toDate, locationValues, alertTypeIds,locationTypeId,year);
			finalVO.setSubList(getStatusWiseData(alertStatusLst));
			
			List<Object[]> alertImpactLevelLst  = alertDAO.getAlertImpactLevelWiseDetailsForConstituencyInfo(fromDate, toDate, locationValues, alertTypeIds,locationTypeId,year,"impactScope");
			finalVO.setImpactScopeList(getImpactLevelData(alertImpactLevelLst));
			
			List<Object[]> totalAlertCntObjList = alertDAO.getTotalAlertDetailsCount(fromDate, toDate, locationValues, alertTypeIds,locationTypeId,year);
			List<Object[]> involvedMembersObjList = alertCandidateDAO.getInvolvedMemberAlertDetails(fromDate, toDate, locationValues, alertTypeIds,locationTypeId,year);
			List<Object[]> assignMembersObjList = alertAssignedOfficerNewDAO.getAssignedMemberAlertDetails(fromDate, toDate, locationValues, alertTypeIds,locationTypeId,year);
			
			finalVO.setTotalAlertCount(getRequirdTotalCount(totalAlertCntObjList));
			finalVO.setInvolveMemberCount(getRequirdTotalCount(involvedMembersObjList));
			finalVO.setAssignedMemberCount(getRequirdTotalCount(assignMembersObjList));
			
		} catch (Exception e) {
			LOG.error("Error occured getTotalAlertDetailsForConstituencyInfo() method of AlertManagementSystemService",e);
		}
		return finalVO;
	}
	public Long getRequirdTotalCount(List<Object[]> objList ){
		Long totalCount = 0l;
		 try {
			  if (objList != null && objList.size() > 0){
				  for (Object[] param : objList) {
					 totalCount = totalCount + commonMethodsUtilService.getLongValueForObject(param[2]);
				}
			  }
		 } catch (Exception e){
			 LOG.error("Error occured getTotalCount() method of AlertManagementSystemService",e);
		 }
		 return totalCount;
	}
 public List<LocationAlertVO> getImpactLevelData(List<Object[]> objList){
	 List<LocationAlertVO> impactScopeList = null;
	   try {
		     List<Object[]> scopeList = alertImpactScopeDAO.getAlertImpactScope();
			 impactScopeList = setImpactScopeSkeletonNew(scopeList);
			 if (objList != null && objList.size() > 0 ){
				    for (Object[] param : objList) {
						Long impactScopeId = commonMethodsUtilService.getLongValueForObject(param[0]);
						Long alertCount = commonMethodsUtilService.getLongValueForObject(param[4]);
						LocationAlertVO matchVO = getImpactScopeMatchVO(impactScopeList,impactScopeId);
						 if (matchVO != null ){
							 matchVO.setCount(alertCount);
						 }
					}
			   }
		   
	   }catch(Exception e ){
		   LOG.error("Exception occured at getImpactLevelData( )",e);
	   }
	return impactScopeList;
	}
	public LocationAlertVO getImpactScopeMatchVO(List<LocationAlertVO> impactScopeList,Long impactScopeId ){
		try {
			 if (impactScopeList == null && impactScopeList.size() == 0 )
				 return null;
			 for (LocationAlertVO locationAlertVO : impactScopeList) {
				  if (locationAlertVO.getId().equals(impactScopeId)){
					  return locationAlertVO;
				  }
			}
		} catch (Exception e){
			LOG.error("Exception occured at getImpactScopeMatchVO( )",e);
		}
		return null;
	}
	public List<LocationAlertVO> setImpactScopeSkeletonNew(List<Object[]> scopeDetlsLst){
		List<LocationAlertVO> finalVOList = new ArrayList<LocationAlertVO>();
			if(scopeDetlsLst != null && scopeDetlsLst.size() > 0){
				for (Object[] objects : scopeDetlsLst){
					LocationAlertVO vo = new LocationAlertVO();
						vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						vo.setStatus(commonMethodsUtilService.getStringValueForObject(objects[1]));
						finalVOList.add(vo);
			   }
			}
		return finalVOList;
	}
	//0-statusId,1-status,2-color,3-impactScopeId,4-impactScope,5-alertTypeId,alertType-6,7-count
	public List<LocationAlertVO> getStatusWiseData(List<Object[]> objList) {
		try {
			Map<Long,LocationAlertVO> locationLevelMap = new HashMap<Long, LocationAlertVO>();
			List<Object[]> alertTypeList = alertTypeDAO.getAlertType();
			List<Object[]> statusList = alertStatusDAO.getAllStatus();
			for (Object[] objects : objList) {
				Long locationId=commonMethodsUtilService.getLongValueForObject(objects[3]);
				String locationName = "";
				if(locationId ==7l || locationId== 5l){
					locationId= 5l;
					locationName="Mandal/Municipality";
				} else if(locationId == 8l || locationId == 6l){
					locationId= 6l;
					locationName="Village/Ward";
				}else {
					locationName = commonMethodsUtilService.getStringValueForObject(objects[4]);
				}
					LocationAlertVO locationVO = locationLevelMap.get(locationId);
					if(locationVO == null){
						locationVO =new LocationAlertVO();
						locationVO.setLocationId(locationId);
						locationVO.setLocationName(locationName);
						locationVO.setSubList(getStatusListVO(alertTypeList,statusList));
						locationLevelMap.put(locationId,locationVO);
					}
					if(locationVO != null){
						LocationAlertVO subVO = getmatchedLocationAlertVo(locationVO.getSubList(),commonMethodsUtilService.getLongValueForObject(objects[0]));
						if(subVO!=null){
							LocationAlertVO innerSubVo= getmatchedLocationAlertVo(subVO.getSubList(), commonMethodsUtilService.getLongValueForObject(objects[5]));
							if(innerSubVo!=null){
								innerSubVo.setCount(innerSubVo.getCount()+commonMethodsUtilService.getLongValueForObject(objects[7]));
								locationVO.setCount(locationVO.getCount()+innerSubVo.getCount());
							}
							subVO.setCount(subVO.getCount()+innerSubVo.getCount());
						}
					
					}
					
			}
			List<LocationAlertVO> finalVOList= new ArrayList<LocationAlertVO>(locationLevelMap.values());

			return finalVOList;
		} catch (Exception e) {
			LOG.error("Exception occured at getStatusWiseData()", e);
			return null;
		}

	}
	private List<LocationAlertVO> getStatusListVO(List<Object[]> alertTypeList,List<Object[]> statusListArr) {
		List<LocationAlertVO> statusListVo= new ArrayList<LocationAlertVO>();
		for (Object[] param : statusListArr) {
			LocationAlertVO statusVo = new LocationAlertVO(); 
			statusVo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
			statusVo.setStatus(commonMethodsUtilService.getStringValueForObject(param[1]));
			statusVo.setColour(commonMethodsUtilService.getStringValueForObject(param[2]));
			statusVo.setSubList(getAlertTypelist(alertTypeList));
			statusListVo.add(statusVo);
		}
		
		return statusListVo;
	}
	private List<LocationAlertVO> getAlertTypelist(List<Object[]> alertTypeList) {
		List<LocationAlertVO> alertTypeListVo= new ArrayList<LocationAlertVO>();
		for (Object[] param : alertTypeList) {
			LocationAlertVO alertTypeVo = new LocationAlertVO(); 
			alertTypeVo.setAlertTypeId(commonMethodsUtilService.getLongValueForObject(param[0]));
			alertTypeVo.setAlertType(commonMethodsUtilService.getStringValueForObject(param[1]));
			alertTypeVo.setCount(0l);
			alertTypeListVo.add(alertTypeVo);
		}
		
		return alertTypeListVo;
	}
	public AlertVO getmatchedAlertVo(List<AlertVO> finalVOList,Long orgId){
		if(finalVOList != null && finalVOList.size() > 0){
			for (AlertVO vo : finalVOList) {
				if(vo.getId() != null && vo.getId().equals(orgId)){
					return vo;
				}else if(vo.getAlertId()!=null && vo.getAlertId().equals(orgId)){
					return vo;
				}
			}
		}
		return null;
	}
	public LocationAlertVO getmatchedLocationAlertVo(List<LocationAlertVO> loationList,Long locationId){
		if(loationList != null && loationList.size() > 0){
			for (LocationAlertVO vo : loationList) {
				if(vo.getId() != null && vo.getId().equals(locationId)){
					return vo;
				}else if(vo.getAlertTypeId()!=null && vo.getAlertTypeId().equals(locationId)){
					return vo;
				}
			}
		}
		return null;
	}
	public Double caclPercantage(Long subCount,Long totalCount){
		Double d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d;
	}
	/*
	 * @Param String fromDateStr
	 * @Param String toDateStr
	 * @Param String year
	 * @Param List<Long> locationValues
	 * @Param Long locationTypeId
	 * @Param String statusType
	 * @author R Nagarjuna Gowd
	 * @return List<AlertVO>
	 * @Description This Method implements the get location wise hamlet details based on green,orange and red status type.
	 * @see com.itgrids.partyanalyst.service.IAlertManagementSystemService#getHamletWiseIvrStatusList(java.lang.String, java.lang.String, java.lang.String, java.util.List, java.lang.Long, java.lang.String)
	 */

	public List<AlertVO> getHamletWiseIvrStatusList(String fromDateStr, String toDateStr, String year,List<Long> locationValues, Long locationTypeId,String statusType) {
        List<AlertVO> finalList = new ArrayList<AlertVO>();
        List<AlertVO> countsList = new ArrayList<AlertVO>();
 		try{
 			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			//0-stateId,1-stateName,2-districtId,3-districtName,4-conId,5-consName,6-mandalId,7-mandalName,8-panchayatId,9-panchayatName,10-hamletId,11-hamletName,12-status,13-count
			List<Object[]> ivrList = alertAssignedOfficerNewDAO.getHamletWiseIvrStatusList(fromDate, toDate, year, locationValues, locationTypeId);
			
			if(ivrList !=null && ivrList.size()>0){
				for (Object[] objects : ivrList) {
		            AlertVO matchedVO = getMatchedAlertVO(countsList, (Long)objects[10]);
		            if(matchedVO == null){
		            	matchedVO = new AlertVO();
		            	matchedVO.setAlertId((Long)objects[10]);
		            	setHamletDetails(objects,matchedVO);
		            	countsList.add(matchedVO);
		            }
		            //Here get and set the ivrs ans counts based on ivrs satifaction status
					if(objects[12]!=null && objects[12].toString().equalsIgnoreCase("Y")){
						matchedVO.setSatisfiedCount(objects[13] !=null ? (Long)objects[13]:0l);
 						
 					}else if(objects[12]!=null && objects[12].toString().equalsIgnoreCase("N")){
 						matchedVO.setUnSatisfiedCount(objects[13] !=null ? (Long)objects[13]:0l);
 					}
					
				}	
			}
					if(countsList !=null && countsList.size()>0){
						for (AlertVO hamletVO : countsList) {
							// Calculate the percentage for hamlet ivrs answers count
							String perc = cadreDetailsService.calculatePercentage(hamletVO.getSatisfiedCount()+hamletVO.getUnSatisfiedCount(),hamletVO.getSatisfiedCount());
							if(statusType.equalsIgnoreCase("green") && statusType!=null && perc!=null && perc!="0" && (Double.parseDouble(perc)>=80)){
								finalList.add(hamletVO);
								//Here set the object status type is orange(orange means ivrs answers percentage is >=50 and <80)
							}else if(statusType.equalsIgnoreCase("orange") && statusType!=null && perc!=null && perc!="0" && (Double.parseDouble(perc)>=50 && (Double.parseDouble(perc)<80))){
								finalList.add(hamletVO);
								//Here set the object status type is red(red means ivrs answers percentage is <50)
							}else if(statusType.equalsIgnoreCase("red") && statusType!=null && perc!=null && perc!="0" && (Double.parseDouble(perc)<50)){
								finalList.add(hamletVO);
							}
						}
						
					}
			
 		}catch (Exception e) {
			e.printStackTrace();
		}
 		return finalList;
	}
	public AlertVO setHamletDetails(Object[] obj,AlertVO vo){
		if(obj!=null){
		vo.setStateId(obj[0] !=null ? (Long)obj[0]:0l);
		vo.setState(obj[1] !=null ? obj[1].toString():null);
		vo.setDistrictId(obj[2] !=null ? (Long)obj[2]:0l);
		vo.setDistrict(obj[3] !=null ? obj[3].toString():null);
		vo.setConstituencyId(obj[4] !=null ? (Long)obj[4]:0l);
		vo.setConstituency(obj[5] !=null ? obj[5].toString():null);
		vo.setTehsilId(obj[6] !=null ? (Long)obj[6]:0l);
		vo.setTehsil(obj[7] !=null ? obj[7].toString():null);
		vo.setPanchayatId(obj[8] !=null ? (Long)obj[8]:0l);
		vo.setPanchayat(obj[9] !=null ? obj[9].toString():null);
		vo.setHamletId(obj[10] !=null ? (Long)obj[10]:0l);
		vo.setHamlet(obj[11] !=null ? obj[11].toString():null);
		}
		return vo;
	}
	public List<AlertVO> getDrainsIvrStatusCounts(String fromDateStr,String toDateStr,List<Long> locationValues,Long locationTypeId,
			 Long searchlevelId,List<Long> searchLevelValues,Long entityType,List<Long> questionsList,List<String> selectedDatesStr){
		List<AlertVO> finalList = new ArrayList<AlertVO>();
		try {
 			Date frmDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				frmDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			List<Date> selectedDates = new ArrayList<Date>(0);
			if(selectedDatesStr !=null && selectedDatesStr.size()>0){
				for (String dateStr : selectedDatesStr) {
					selectedDates.add( sdf.parse(dateStr));
				}				
			} 
			
			
			/*Map<Long,IdNameVO> assemblyMap = new HashMap<Long, IdNameVO>(); 
			if(locationTypeId !=null && locationTypeId.longValue()==10l){
				List<Object[]> parliamentObj = parliamentAssemblyDAO.getParliamentDetailsOfAssembly(1l);
				if(parliamentObj !=null && parliamentObj.size()>0){
					assemblyMap = setParliamentObjectDetails(parliamentObj,assemblyMap);
				}				
			}*/
			
			//0-locationId,1-locationName,2-panchayat_id,3-satisfied_status,4-answers_count,5.Date
			//0-locationId,1-locationName,2-panchayat_id,3-satisfied_status,4-answers_count			
			List<Object[]> ivrCountsObj = alertAssignedOfficerNewDAO.getDrainsIvrStatusCounts(frmDate, toDate, locationValues,locationTypeId,
 					searchlevelId, searchLevelValues,entityType,questionsList,selectedDates);
			
			if(selectedDates !=null && selectedDates.size()>1){
	 			finalList = setIvrComparisonDetailsToList(ivrCountsObj,finalList,selectedDatesStr);
			}else{		
	 			finalList = setIvrDetailsToList(ivrCountsObj,finalList);
			}
			
 			/*if(finalList !=null && finalList.size()>0){
 				if(assemblyMap !=null && assemblyMap.size()>0){
 					for (Entry<Long, IdNameVO> mainVO : assemblyMap.entrySet()) {
						
					}
 				}
 			}*/
 			
 		} catch (Exception e) {
			LOG.error("Error occured getDrainsIvrStatusCounts() method of AlertManagementSystemService",e);
		}
		return finalList;
	}
	public Map<Long,IdNameVO> setParliamentObjectDetails(List<Object[]> objList,Map<Long,IdNameVO> assemblyMap){
		try {
			
			if(objList !=null && objList.size()>0){
				for (Object[] objects : objList) {
					IdNameVO  vo= assemblyMap.get((Long)objects[2]);//assemblyId
					if(vo == null){
						vo = new IdNameVO();
						assemblyMap.put((Long)objects[2], vo);
					}
					vo.setId((Long)objects[0]);//ParliamentId
					vo.setName(objects[1] !=null ? objects[1].toString():null);//ParliamentName					
					vo.setOrderId((Long)objects[2]);//assemblyId
				}
			}
			
		} catch (Exception e) {
			LOG.error("Error occured setParliamentObjectDetails() method of AlertManagementSystemService",e);
		}
		return assemblyMap;
	}
	//0-locationId,1-locationName,2-panchayat_id,3-satisfied_status,4-answers_count,5.Date
	public List<AlertVO> setIvrComparisonDetailsToList(List<Object[]> ivrCountsObj,List<AlertVO> finalList,List<String> dateListStr){
		try {
			
			// Here Hamlet means Panchayat
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			
			Map<Long,Map<String,Map<Long,AlertVO>>> typeMap = new HashMap<Long,Map<String,Map<Long,AlertVO>>>();
			
			if(ivrCountsObj !=null && ivrCountsObj.size()>0){
				for (Object[] objects : ivrCountsObj) {
					
					Map<String,Map<Long,AlertVO>> dateMap =  typeMap.get((Long)objects[0]);
					if(dateMap == null){			
						dateMap = new HashMap<String, Map<Long,AlertVO>>();
						
						for (String dateStr : dateListStr) {
							dateMap.put(dateStr,new HashMap<Long, AlertVO>());// default dates Pushing into Dates Map
						}												
						typeMap.put((Long)objects[0], dateMap);
					}
					
	 				Map<Long,AlertVO> hamletMap = dateMap.get(sdf.format(objects[5]));
	 				if(hamletMap==null){
	 					hamletMap = new HashMap<Long, AlertVO>();
	 					dateMap.put(sdf.format(objects[5]), hamletMap);
	 				}	 				
	 				AlertVO vo = hamletMap.get((Long)objects[2]);
	 				if(vo==null){
	 					vo = new AlertVO();
	 					vo.setId((Long)objects[2]);
	 					hamletMap.put((Long)objects[2], vo);
	 				}
 					vo.setName(objects[1] !=null ? objects[1].toString():null);//locationName
 					vo.setDate1(objects[5] !=null ? sdf.format(objects[5]):null);//Date String
 				
 					if(objects[3]!=null && objects[3].toString().equalsIgnoreCase("Y")){
 						vo.setSatisfiedCount(objects[4] !=null ? (Long)objects[4]:0l);
 						
 					}else if(objects[3]!=null && objects[3].toString().equalsIgnoreCase("N")){
 						vo.setUnSatisfiedCount(objects[4] !=null ? (Long)objects[4]:0l);
 					}
	 			}	
			}
 			if(typeMap!=null && typeMap.size()>0){
 				
 				for (Entry<Long, Map<String, Map<Long, AlertVO>>> objects : typeMap.entrySet()) {
 					
 					AlertVO typeVO = new AlertVO();
 					typeVO.setId(objects.getKey());//locationId

 					Map<String, Map<Long,AlertVO>> dateMap = objects.getValue();
 					
 					if(dateMap !=null && dateMap.size()>0){
 						
 						List<AlertVO> dateList = new ArrayList<AlertVO>(0);
 						
 						for (Entry<String, Map<Long, AlertVO>> datMp : dateMap.entrySet()) {
 							

 	 						Long greenHamletCount=0l;
 	 	 	 				Long orangeHamletCount=0l;
 	 	 	 				Long redHamletCount=0l;
 							
 							AlertVO vo = new AlertVO(); 							
 							vo.setDate1(datMp.getKey());
 							
 							Map<Long,AlertVO> hamletMap = datMp.getValue();

 							List<AlertVO> hamletList = new  ArrayList<AlertVO>(hamletMap.values());
 							
 							//Panchayat Details of DateWise
 							if(hamletList!=null && hamletList.size()>0){
 		 						for (AlertVO hamletVo : hamletList) { 		 							
 		 							Long satisfyCount = hamletVo.getSatisfiedCount() !=null ? hamletVo.getSatisfiedCount():0l;
 		 		 					Long unSatisfyCount = hamletVo.getUnSatisfiedCount()!=null ? hamletVo.getUnSatisfiedCount():0l;
 		 		 					
 		 		 					hamletVo.setCount(satisfyCount+unSatisfyCount);
 		 		 					
 		 		 					String perc = cadreDetailsService.calculatePercentage(hamletVo.getCount(),satisfyCount);
 		 		 					if(perc !=null && perc !="0" && !perc.trim().isEmpty()){
 		 		 						hamletVo.setPercentage(Double.parseDouble(perc));
 		 		 						
 		 		 						if(hamletVo.getPercentage()>=80){
 		 		 							greenHamletCount++;
 		 		 						}else if(hamletVo.getPercentage()<80 && hamletVo.getPercentage()>=50){
 		 		 							orangeHamletCount++;
 		 		 						}else if(hamletVo.getPercentage()<50){
 		 		 							redHamletCount++;
 		 		 						}
 		 		 					}
 								}
 		 						typeVO.setName(hamletList.get(0).getName());
 		 					}
 							
 							Long total = greenHamletCount+orangeHamletCount+redHamletCount;
 							double greenPer = (Double.parseDouble(cadreDetailsService.calculatePercentage(total, greenHamletCount)));
 		 		            double orangePer = (Double.parseDouble(cadreDetailsService.calculatePercentage(total, orangeHamletCount)));
 		 		            double redPer = (Double.parseDouble(cadreDetailsService.calculatePercentage(total, redHamletCount)));
 		 		            
 		 		            AlertVO green = new AlertVO(); 
 		 	 				green.setName("green");
 		 	 				green.setCount(greenHamletCount);
 		 	 				green.setPercentage(greenPer);
 		 	 				AlertVO orange = new AlertVO(); 
 		 	 				orange.setName("orange");
 		 	 				orange.setCount(orangeHamletCount);
 		 	 				orange.setPercentage(orangePer);
 		 	 				AlertVO red = new AlertVO();
 		 	 				red.setName("red");
 		 	 				red.setCount(redHamletCount);
 		 	 				red.setPercentage(redPer);
 		 		            
 		 	 				vo.getSubList1().add(green);
 		 	 				vo.getSubList1().add(orange);
 		 	 				vo.getSubList1().add(red);
 		 					
 		 	 				dateList.add(vo); // color Vo adding to dates List
 		 	 				
 						}
 						typeVO.setSubList1(dateList);//dateList Adding to LocationType VO
 					}
 	 				
 					finalList.add(typeVO);//finally locationType Vo adding to location List  
				}
 			}
			
		} catch (Exception e) {
			LOG.error("Error occured setIvrComparisonDetailsToList() method of AlertManagementSystemService",e);
		}
		return finalList;
	}
	
	public List<AlertVO> getOverAllIvrDetails(String fromDateStr,String toDateStr,Long entityType,List<Long> questionsList,String type){
		List<AlertVO> finalList = new ArrayList<AlertVO>();
		try {
			
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			//0.optionId,1.option,2.count
			List<Object[]> ivrObj =  alertAssignedOfficerNewDAO.getOverAllIvrDetails(fromDate, toDate, entityType, questionsList, type);
			
			if(ivrObj !=null && ivrObj.size()>0){
				Long total=0l;
				for (Object[] obj : ivrObj) {
					AlertVO vo = new AlertVO();
					vo.setId(obj[0] !=null ? (Long)obj[0]:null);
					vo.setName(obj[1] !=null ? obj[1].toString():null);
					vo.setCount(obj[2] !=null ? (Long)obj[2]:0l);
					total = total + vo.getCount();
					finalList.add(vo);
				}
				finalList.get(0).setCategoryCount(total);
				
				for (AlertVO vo : finalList) {					
					vo.setPercentage(calculatePercantage(vo.getCount(), total));					
				}				
			}
			
			
		} catch (Exception e) {
			LOG.error("Error occured getOverAllIvrDetails() method of AlertManagementSystemService",e);
		}
		return finalList;
	}
	
	public List<IdNameVO> getIvrSurveyDates(String fromDateStr,String toDateStr,Long entityType){
		List<IdNameVO> finalList = new ArrayList<IdNameVO>(0);
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			List<Date> resultObj = alertAssignedOfficerNewDAO.getIvrSurveyDates(fromDate,toDate,entityType);
			
			if(resultObj !=null && resultObj.size()>0){
				for (Date obj : resultObj) {
					IdNameVO vo = new IdNameVO();
					vo.setName(obj !=null ? sdf.format(obj):null);
					finalList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Error occured getIvrSurveyDates() method of AlertManagementSystemService",e);
		}
		return finalList;
	}
	public List<IdNameVO> getIvrSurveyQuestions(String fromDateStr,String toDateStr,Long entityType){
		List<IdNameVO> finalList = new ArrayList<IdNameVO>(0);
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(fromDateStr != null && fromDateStr.trim().length() > 0 && toDateStr != null && toDateStr.trim().length() > 0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			List<Object[]> resultObj = alertAssignedOfficerNewDAO.getIvrSurveyQuestions(fromDate,toDate,entityType);
			
			if(resultObj !=null && resultObj.size()>0){
				for (Object[] objects : resultObj) {
					IdNameVO vo = new IdNameVO();
					vo.setId(objects[0] !=null ? (Long)objects[0]:null);
					vo.setName(objects[1] !=null ? objects[1].toString():null);
					finalList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Error occured getIvrSurveyQuestions() method of AlertManagementSystemService",e);
		}
		return finalList;
	}
	public List<AlertVO> getMonthSkeleton(){
		List<AlertVO> voList = new ArrayList<AlertVO>(0);
		List<Long> monthIds = IConstants.MONTH_IDS;
		String[] months = IConstants.MONTH_NAMES;
		List<String> monthNames =new ArrayList<String>();
		for (String monthName : months) {
			monthNames.add(monthName);
		}
		if(monthIds != null && monthIds.size() > 0){
			for (int i=0;i<monthIds.size();i++) {
				AlertVO vo = new AlertVO();
				vo.setSmTypeId(monthIds.get(i));
				vo.setSmType(monthNames.get(i));
				voList.add(vo);
			}
		}
		return voList;
	}
	public AlertVO getJalavaniDashBoardViewInfo(JalavaniAlertsInputVO inputVo){
		AlertVO finalVo = new AlertVO();
		try {
			Date startDate = null;Date endDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(inputVo.getFromDateStr() != null && inputVo.getToDateStr() != null){
				startDate = sdf.parse(inputVo.getFromDateStr());
				endDate = sdf.parse(inputVo.getToDateStr());
			}
			Long totalCallCenterCalls = alertCallerRelationDAO.totalCallCenterCallForRwsDept(startDate,endDate);//total call center calls count
			
			List<Object[]> totalAlertcountsList =alertDAO.getTotalAlertCounts(startDate, endDate,0l);//totalAlerts counts for categorywise(callcenter,PM,EMN)
			finalVo.setCount(totalCallCenterCalls);
			
			if(totalAlertcountsList !=null && totalAlertcountsList.size()>0){
				for (Object[] objects : totalAlertcountsList){
					AlertVO vo = new AlertVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					vo.setAlertCnt(commonMethodsUtilService.getLongValueForObject(objects[2]));
					
					finalVo.getSubList1().add(vo);
				}
			}
			finalVo.getSubList2().addAll(getMonthSkeleton());
			List<Object[]> monthObjList = alertDAO.getAlertsMonthWiseOverview(startDate, endDate,0l);
			if(monthObjList !=null && monthObjList.size() >0){
				for (Object[] objects : monthObjList) {
					AlertVO matchedMonthVO = getmatchedMonthVo(finalVo.getSubList2(),commonMethodsUtilService.getLongValueForObject(objects[0]));
					if(matchedMonthVO != null){
						matchedMonthVO.setLocationCnt((Long)objects[1]);
					}
				}
			}
			//status cade pending 
			List<AlertStatus> allStatus = alertStatusDAO.getAll();
			getAlertStatusWiseSkelton(allStatus,finalVo);
			//statusId-0,status-1,statusColor-2,count-3
			List<Object[]> statusList = alertDAO.getAlertsStatusOverView(startDate, endDate,0l);
			if(statusList !=null && statusList.size() >0){
				for (Object[] objects : statusList) {
					AlertVO matchedColorVo = getmatchedStatusVo(finalVo.getList(),commonMethodsUtilService.getLongValueForObject(objects[0]));
					if(matchedColorVo !=null){
						matchedColorVo.setColor(objects[2].toString());
						matchedColorVo.setStatusCount((Long)objects[3]);
					}
				}
			}
			
		}catch (Exception e){
			LOG.error("Error occured getJalavaniDashBoardViewInfo() method of AlertManagementSystemService",e);
		}
		return finalVo;
	}
	 public void getAlertStatusWiseSkelton(List<AlertStatus> allStatus,AlertVO finalVo){
 		 for (AlertStatus alertStatus : allStatus) {
			if(alertStatus.getAlertStatusId() > 1l){
				AlertVO subVO = new AlertVO();
				subVO.setStatusId(alertStatus.getAlertStatusId());
				subVO.setStatus(alertStatus.getAlertStatus());
				
				finalVo.getList().add(subVO);
			}
		}
 	 }
	 public AlertVO getmatchedStatusVo(List<AlertVO> finalVOList,Long statusId){
			if(finalVOList != null && finalVOList.size() > 0){
				for (AlertVO alertVO : finalVOList){
					if(alertVO.getStatusId() != null && alertVO.getStatusId().equals(statusId)){
						return alertVO;
					}
				}
			}
			return null;
		}
	public AlertVO getmatchedMonthVo(List<AlertVO> finalVOList,Long monthId){
		if(finalVOList != null && finalVOList.size() > 0){
			for (AlertVO vo : finalVOList) {
				if(vo.getSmTypeId() != null && vo.getSmTypeId().equals(monthId)){
					return vo;
				}
			}
		}
		return null;
	}
	public AlertVO getJalavaniDashBoardPrintMediaDetailsInfo(JalavaniAlertsInputVO inputVo){
		AlertVO finalVo = new AlertVO();
		try {
			Date startDate = null;Date endDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(inputVo.getFromDateStr() != null && inputVo.getToDateStr() != null){
				startDate = sdf.parse(inputVo.getFromDateStr());
				endDate = sdf.parse(inputVo.getToDateStr());
			}
			Long totalPMAlertsCount = alertDAO.getCountOfAlertsForAlertWiseCategory(startDate,endDate,2l);//total Print media alerts count
			finalVo.setCount(totalPMAlertsCount);
			
			//Print media wise status 
			List<AlertStatus> allStatus = alertStatusDAO.getAll();
			getAlertStatusWiseSkelton(allStatus,finalVo);
			//statusId-0,status-1,statusColor-2,count-3
			List<Object[]> statusList = alertDAO.getAlertsStatusOverView(startDate, endDate,2l);
			if(statusList !=null && statusList.size() >0){
				for (Object[] objects : statusList) {
					AlertVO matchedColorVo = getmatchedStatusVo(finalVo.getList(),commonMethodsUtilService.getLongValueForObject(objects[0]));
					if(matchedColorVo !=null){
						matchedColorVo.setColor(objects[2].toString());
						matchedColorVo.setStatusCount((Long)objects[3]);
					}
				}
			}
			//Print media alerts wise monthly overview
			finalVo.getSubList2().addAll(getMonthSkeleton());
			List<Object[]> monthObjList = alertDAO.getAlertsMonthWiseOverview(startDate, endDate,2l);
			if(monthObjList !=null && monthObjList.size() >0){
				for (Object[] objects : monthObjList) {
					AlertVO matchedMonthVO = getmatchedMonthVo(finalVo.getSubList2(),commonMethodsUtilService.getLongValueForObject(objects[0]));
					if(matchedMonthVO != null){
						matchedMonthVO.setLocationCnt((Long)objects[1]);
					}
				}
			}
			
		}catch (Exception e){
			LOG.error("Error occured getJalavaniDashBoardPrintMediaDetailsInfo() method of AlertManagementSystemService",e);
		}
		return finalVo;
	}
	public AlertVO getJalavaniDashBoardElectronicMediaDetailsInfo(JalavaniAlertsInputVO inputVo){
		AlertVO finalVo = new AlertVO();
		try {
			Date startDate = null;Date endDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if(inputVo.getFromDateStr() != null && inputVo.getToDateStr() != null){
				startDate = sdf.parse(inputVo.getFromDateStr());
				endDate = sdf.parse(inputVo.getToDateStr());
			}
			Long totalEMAlertsCount =  alertDAO.getCountOfAlertsForAlertWiseCategory(startDate,endDate,3l); //total Electronic media alerts count
			finalVo.setCount(totalEMAlertsCount);
			
			//Electronic media wise status 
			List<AlertStatus> allStatus = alertStatusDAO.getAll();
			getAlertStatusWiseSkelton(allStatus,finalVo);
			//statusId-0,status-1,statusColor-2,count-3
			List<Object[]> statusList = alertDAO.getAlertsStatusOverView(startDate, endDate,3l);
			if(statusList !=null && statusList.size() >0){
				for (Object[] objects : statusList) {
					AlertVO matchedColorVo = getmatchedStatusVo(finalVo.getList(),commonMethodsUtilService.getLongValueForObject(objects[0]));
					if(matchedColorVo !=null){
						matchedColorVo.setColor(objects[2].toString());
						matchedColorVo.setStatusCount((Long)objects[3]);
					}
				}
			}
			//Electronic media alerts wise monthly overview
			finalVo.getSubList2().addAll(getMonthSkeleton());
			List<Object[]> monthObjList = alertDAO.getAlertsMonthWiseOverview(startDate, endDate,3l);
			if(monthObjList !=null && monthObjList.size() >0){
				for (Object[] objects : monthObjList) {
					AlertVO matchedMonthVO = getmatchedMonthVo(finalVo.getSubList2(),commonMethodsUtilService.getLongValueForObject(objects[0]));
					if(matchedMonthVO != null){
						matchedMonthVO.setLocationCnt((Long)objects[1]);
					}
				}
			}
			
		}catch (Exception e){
			LOG.error("Error occured getJalavaniDashBoardElectronicMediaDetailsInfo() method of AlertManagementSystemService",e);
		}
		return finalVo;
	}

}