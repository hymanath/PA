 package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IActivityConductedInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityDAO;
import com.itgrids.partyanalyst.dao.IActivityInfoDocumentDAO;
import com.itgrids.partyanalyst.dao.IActivityLevelDAO;
import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IActivityQuestionAnswerDAO;
import com.itgrids.partyanalyst.dao.IActivityScopeDAO;
import com.itgrids.partyanalyst.dao.IActivityScopeRequiredAttributesDAO;
import com.itgrids.partyanalyst.dao.IActivitySubTypeDAO;
import com.itgrids.partyanalyst.dao.IActivityTypeDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothInchargeCommitteeDAO;
import com.itgrids.partyanalyst.dao.IBoothInchargeConditionDAO;
import com.itgrids.partyanalyst.dao.IBoothInchargeDAO;
import com.itgrids.partyanalyst.dao.IBoothInchargeRoleConditionMappingDAO;
import com.itgrids.partyanalyst.dao.ICadreCommitteeChangeDesignationsDAO;
import com.itgrids.partyanalyst.dao.ICadreCommitteeIncreasedPositionsDAO;
import com.itgrids.partyanalyst.dao.ICadreCommitteeRoleDAO;
import com.itgrids.partyanalyst.dao.ICadreDeleteReasonDAO;
import com.itgrids.partyanalyst.dao.ICadreIvrResponseDAO;
import com.itgrids.partyanalyst.dao.ICadreOtpDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreParticipatedElectionDAO;
import com.itgrids.partyanalyst.dao.ICadrePreviousRolesDAO;
import com.itgrids.partyanalyst.dao.ICadreRegUserTabUserDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.ICommitteIvrDistrictDetailDAO;
import com.itgrids.partyanalyst.dao.ICommitteIvrTotalDetailDAO;
import com.itgrids.partyanalyst.dao.ICommitteeConfirmRuleConditionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictConstituenciesDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.IEventDAO;
import com.itgrids.partyanalyst.dao.IEventGroupDAO;
import com.itgrids.partyanalyst.dao.IEventInviteeDAO;
import com.itgrids.partyanalyst.dao.IEventRfidDetailsDAO;
import com.itgrids.partyanalyst.dao.IEventSurveyUserDAO;
import com.itgrids.partyanalyst.dao.IEventUserDAO;
import com.itgrids.partyanalyst.dao.IIvrCampaignOptionsDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.ILocationInfoDAO;
import com.itgrids.partyanalyst.dao.INewDistrictConstituencyDAO;
import com.itgrids.partyanalyst.dao.INominatedPostDAO;
import com.itgrids.partyanalyst.dao.INominationPostCandidateDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPublicRepresentativeDAO;
import com.itgrids.partyanalyst.dao.IPublicRepresentativeTypeDAO;
import com.itgrids.partyanalyst.dao.IRequiredAttributeDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpBasicCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDesignationDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeElectrolRolesDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeElectrolsDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeEnrollmentDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeLevelDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberHistoryDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeRoleDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeRoleHistoryDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeVacantPostDAO;
import com.itgrids.partyanalyst.dao.ITdpRolesDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserDistrictAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.hibernate.NominatedPostDAO;
import com.itgrids.partyanalyst.dao.impl.IActivityLocationInfoDatesDAO;
import com.itgrids.partyanalyst.dto.AccessedPageLoginTimeVO;
import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeReportVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeRolesInfoVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.CadreIVRVO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CasteDetailsVO;
import com.itgrids.partyanalyst.dto.CommitteeApprovalVO;
import com.itgrids.partyanalyst.dto.CommitteeResultVO;
import com.itgrids.partyanalyst.dto.CommitteeSummaryVO;
import com.itgrids.partyanalyst.dto.EventCreationVO;
import com.itgrids.partyanalyst.dto.EventDocumentVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.InviteesVO;
import com.itgrids.partyanalyst.dto.IvrOptionsVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO1;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO2;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.RolesVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.UserEventDetailsVO;
import com.itgrids.partyanalyst.dto.VO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.ActivityConductedInfo;
import com.itgrids.partyanalyst.model.ActivityLocationInfo;
import com.itgrids.partyanalyst.model.ActivityLocationInfoDates;
import com.itgrids.partyanalyst.model.ActivityScope;
import com.itgrids.partyanalyst.model.BoothIncharge;
import com.itgrids.partyanalyst.model.BoothInchargeCommittee;
import com.itgrids.partyanalyst.model.BoothInchargeCondition;
import com.itgrids.partyanalyst.model.BoothInchargeRoleConditionMapping;
import com.itgrids.partyanalyst.model.CadreCommitteeChangeDesignations;
import com.itgrids.partyanalyst.model.CadreCommitteeIncreasedPositions;
import com.itgrids.partyanalyst.model.CadreOtpDetails;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.EducationalQualifications;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.Event;
import com.itgrids.partyanalyst.model.EventInvitee;
import com.itgrids.partyanalyst.model.EventRfidDetails;
import com.itgrids.partyanalyst.model.EventSurveyUser;
import com.itgrids.partyanalyst.model.EventUser;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Occupation;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.PublicRepresentativeType;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.TdpBasicCommittee;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.TdpCommittee;
import com.itgrids.partyanalyst.model.TdpCommitteeDesignation;
import com.itgrids.partyanalyst.model.TdpCommitteeElectrolRoles;
import com.itgrids.partyanalyst.model.TdpCommitteeElectrols;
import com.itgrids.partyanalyst.model.TdpCommitteeMember;
import com.itgrids.partyanalyst.model.TdpCommitteeMemberHistory;
import com.itgrids.partyanalyst.model.TdpCommitteeRole;
import com.itgrids.partyanalyst.model.TdpCommitteeRoleHistory;
import com.itgrids.partyanalyst.model.TdpCommitteeVacantPost;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.model.VoterAgeRange;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;

public class CadreCommitteeService implements ICadreCommitteeService
{
	private static final Logger   LOG = Logger.getLogger(CadreCommitteeService.class);
	private ITdpCadreDAO tdpCadreDAO;
	private IConstituencyDAO constituencyDAO;
	private ITehsilDAO tehsilDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IPanchayatDAO panchayatDAO;
	private ICadrePreviousRolesDAO cadrePreviousRolesDAO;
	private ICadreCommitteeRoleDAO cadreCommitteeRoleDAO;
	private ICadreParticipatedElectionDAO cadreParticipatedElectionDAO;
	private ICadreOtpDetailsDAO cadreOtpDetailsDAO;
	private SmsCountrySmsService smsCountrySmsService;
	private IVoterAgeRangeDAO voterAgeRangeDAO;
	private ICasteStateDAO casteStateDAO;
	private IEducationalQualificationsDAO educationalQualificationsDAO;
	private IOccupationDAO occupationDAO;
	private IElectionTypeDAO electionTypeDAO;
	private IRegionServiceData regionServiceDataImp;
	private ITdpCommitteeDAO tdpCommitteeDAO;
	private ITdpCommitteeRoleDAO tdpCommitteeRoleDAO;
	private ITdpCommitteeMemberDAO tdpCommitteeMemberDAO;
	private ITdpCommitteeElectrolsDAO tdpCommitteeElectrolsDAO;
	private ITdpCommitteeElectrolRolesDAO tdpCommitteeElectrolRolesDAO;
	private ITdpCommitteeDesignationDAO tdpCommitteeDesignationDAO;
	private TransactionTemplate             transactionTemplate;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IVoterDAO voterDAO;
	private IBoothDAO                       boothDAO;
	private ITdpCommitteeMemberHistoryDAO tdpCommitteeMemberHistoryDAO;
	private IDistrictDAO districtDAO;
	private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;
	private ICadreDetailsService cadreDetailsService;
	private ITdpBasicCommitteeDAO tdpBasicCommitteeDAO;
	private ILocalElectionBodyWardDAO localElectionBodyWardDAO;
	private ITdpCommitteeLevelDAO tdpCommitteeLevelDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private IUserDAO userDAO;
	private ICadreCommitteeIncreasedPositionsDAO cadreCommitteeIncreasedPositionsDAO;
	private ICadreCommitteeChangeDesignationsDAO cadreCommitteeChangeDesignationsDAO;
	private ITdpCommitteeRoleHistoryDAO tdpCommitteeRoleHistoryDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IUserDistrictAccessInfoDAO  userDistrictAccessInfoDAO;
	private IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private ITdpCadreInfoDAO tdpCadreInfoDAO;
	private ICadreIvrResponseDAO cadreIvrResponseDAO;
	private IIvrCampaignOptionsDAO ivrCampaignOptionsDAO;
	private ICommitteIvrDistrictDetailDAO committeIvrDistrictDetailDAO;
	private ICommitteIvrTotalDetailDAO committeIvrTotalDetailDAO;
	private IVoterAgeInfoDAO voterAgeInfoDAO;
	private ITdpCommitteeVacantPostDAO tdpCommitteeVacantPostDAO;
	private ITdpCommitteeEnrollmentDAO tdpCommitteeEnrollmentDAO;
	@Autowired
	private ITdpRolesDAO tdpRolesDAO;
	private IEventGroupDAO eventGroupDAO;
	private IPublicRepresentativeTypeDAO publicRepresentativeTypeDAO;
	private IPublicRepresentativeDAO publicRepresentativeDAO;
	private IEventDAO eventDAO;
	private IEventInviteeDAO eventInviteeDAO;
	private IEventSurveyUserDAO eventSurveyUserDAO;
	private IEventRfidDetailsDAO eventRfidDetailsDAO;
	private IEventUserDAO eventUserDAO;
	private ICandidateResultDAO candidateResultDAO;
	private INewDistrictConstituencyDAO newDistrictConstituencyDAO;
	private ITdpCadreCandidateDAO tdpCadreCandidateDAO;
	private IDistrictConstituenciesDAO districtConstituenciesDAO;
	private ICadreDeleteReasonDAO cadreDeleteReasonDAO;
	private CadreRegistrationService cadreRegistrationService;
	private IStateDAO stateDAO;
	
	private IActivityTypeDAO activityTypeDAO;
	private IActivitySubTypeDAO activitySubTypeDAO;
	private IActivityDAO activityDAO;
	private IActivityLevelDAO activityLevelDAO;
	private IActivityScopeDAO activityScopeDAO;
	private IActivityLocationInfoDAO activityLocationInfoDAO;
	private ILocationInfoDAO locationInfoDAO;
	private IActivityQuestionAnswerDAO activityQuestionAnswerDAO;
	
	private IActivityInfoDocumentDAO activityInfoDocumentDAO;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();

	private SetterAndGetterUtilService setterAndGetterUtilService;
	private IActivityLocationInfoDatesDAO activityLocationInfoDatesDAO;
	private IActivityScopeRequiredAttributesDAO activityScopeRequiredAttributesDAO;
	private INominationPostCandidateDAO nominationPostCandidateDAO;    
	private ICadreRegUserTabUserDAO cadreRegUserTabUserDAO;
	private IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
	private CoreDashboardGenericService coreDashboardGenericService;
	private ICommitteeConfirmRuleConditionDAO committeeConfirmRuleConditionDAO;
	private IActivityConductedInfoDAO activityConductedInfoDAO;
	private IRequiredAttributeDAO requiredAttributeDAO;
	private IBoothInchargeDAO boothInchargeDAO;
	private IBoothInchargeRoleConditionMappingDAO boothInchargeRoleConditionMappingDAO;
	private IBoothInchargeCommitteeDAO boothInchargeCommitteeDAO;
	private INominatedPostDAO nominatedPostDAO;
	private IBoothInchargeConditionDAO boothInchargeConditionDAO;
	
	
	public IBoothInchargeConditionDAO getBoothInchargeConditionDAO() {
		return boothInchargeConditionDAO;
	}

	public void setBoothInchargeConditionDAO(
			IBoothInchargeConditionDAO boothInchargeConditionDAO) {
		this.boothInchargeConditionDAO = boothInchargeConditionDAO;
	}

	public INominatedPostDAO getNominatedPostDAO() {
		return nominatedPostDAO;
	}

	public void setNominatedPostDAO(INominatedPostDAO nominatedPostDAO) {
		this.nominatedPostDAO = nominatedPostDAO;
	}

	public IBoothInchargeCommitteeDAO getBoothInchargeCommitteeDAO() {
		return boothInchargeCommitteeDAO;
	}

	public void setBoothInchargeCommitteeDAO(
			IBoothInchargeCommitteeDAO boothInchargeCommitteeDAO) {
		this.boothInchargeCommitteeDAO = boothInchargeCommitteeDAO;
	}

	public IBoothInchargeRoleConditionMappingDAO getBoothInchargeRoleConditionMappingDAO() {
		return boothInchargeRoleConditionMappingDAO;
	}

	public void setBoothInchargeRoleConditionMappingDAO(
			IBoothInchargeRoleConditionMappingDAO boothInchargeRoleConditionMappingDAO) {
		this.boothInchargeRoleConditionMappingDAO = boothInchargeRoleConditionMappingDAO;
	}
		
	public IRequiredAttributeDAO getRequiredAttributeDAO() {
		return requiredAttributeDAO;
	}

	public void setRequiredAttributeDAO(IRequiredAttributeDAO requiredAttributeDAO) {
		this.requiredAttributeDAO = requiredAttributeDAO;
	}

	public IActivityMemberAccessLevelDAO getActivityMemberAccessLevelDAO() {
		return activityMemberAccessLevelDAO;
	}

	public void setActivityMemberAccessLevelDAO(
			IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO) {
		this.activityMemberAccessLevelDAO = activityMemberAccessLevelDAO;
	}

	public ICadreRegUserTabUserDAO getCadreRegUserTabUserDAO() {
		return cadreRegUserTabUserDAO;
	}

	public void setCadreRegUserTabUserDAO(
			ICadreRegUserTabUserDAO cadreRegUserTabUserDAO) {
		this.cadreRegUserTabUserDAO = cadreRegUserTabUserDAO;
	}

	public INominationPostCandidateDAO getNominationPostCandidateDAO() {
		return nominationPostCandidateDAO;
	}

	public void setNominationPostCandidateDAO(
			INominationPostCandidateDAO nominationPostCandidateDAO) {
		this.nominationPostCandidateDAO = nominationPostCandidateDAO;
	}

	public IActivityScopeRequiredAttributesDAO getActivityScopeRequiredAttributesDAO() {
		return activityScopeRequiredAttributesDAO;
	}

	public void setActivityScopeRequiredAttributesDAO(
			IActivityScopeRequiredAttributesDAO activityScopeRequiredAttributesDAO) {
		this.activityScopeRequiredAttributesDAO = activityScopeRequiredAttributesDAO;
	}

	public IActivityLocationInfoDatesDAO getActivityLocationInfoDatesDAO() {
		return activityLocationInfoDatesDAO;
	}

	public void setActivityLocationInfoDatesDAO(
			IActivityLocationInfoDatesDAO activityLocationInfoDatesDAO) {
		this.activityLocationInfoDatesDAO = activityLocationInfoDatesDAO;
	}

	public void setSetterAndGetterUtilService(
			SetterAndGetterUtilService setterAndGetterUtilService) {
		this.setterAndGetterUtilService = setterAndGetterUtilService;
	}

	public IActivityQuestionAnswerDAO getActivityQuestionAnswerDAO() {
		return activityQuestionAnswerDAO;
	}

	public void setActivityQuestionAnswerDAO(
			IActivityQuestionAnswerDAO activityQuestionAnswerDAO) {
		this.activityQuestionAnswerDAO = activityQuestionAnswerDAO;
	}

	public IActivityInfoDocumentDAO getActivityInfoDocumentDAO() {
		return activityInfoDocumentDAO;
	}

	public void setActivityInfoDocumentDAO(
			IActivityInfoDocumentDAO activityInfoDocumentDAO) {
		this.activityInfoDocumentDAO = activityInfoDocumentDAO;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public void setTdpRolesDAO(ITdpRolesDAO tdpRolesDAO) {
		this.tdpRolesDAO = tdpRolesDAO;
	}

	public void setLocationInfoDAO(ILocationInfoDAO locationInfoDAO) {
		this.locationInfoDAO = locationInfoDAO;
	}

	public IActivityLocationInfoDAO getActivityLocationInfoDAO() {
		return activityLocationInfoDAO;
	}

	public void setActivityLocationInfoDAO(
			IActivityLocationInfoDAO activityLocationInfoDAO) {
		this.activityLocationInfoDAO = activityLocationInfoDAO;
	}

	public IActivitySubTypeDAO getActivitySubTypeDAO() {
		return activitySubTypeDAO;
	}

	public void setActivitySubTypeDAO(IActivitySubTypeDAO activitySubTypeDAO) {
		this.activitySubTypeDAO = activitySubTypeDAO;
	}

	public IActivityScopeDAO getActivityScopeDAO() {
		return activityScopeDAO;
	}

	public void setActivityScopeDAO(IActivityScopeDAO activityScopeDAO) {
		this.activityScopeDAO = activityScopeDAO;
	}

	public IActivityTypeDAO getActivityTypeDAO() {
		return activityTypeDAO;
	}

	public void setActivityTypeDAO(IActivityTypeDAO activityTypeDAO) {
		this.activityTypeDAO = activityTypeDAO;
	}

	public IActivityDAO getActivityDAO() {
		return activityDAO;
	}

	public void setActivityDAO(IActivityDAO activityDAO) {
		this.activityDAO = activityDAO;
	}

	public IActivityLevelDAO getActivityLevelDAO() {
		return activityLevelDAO;
	}

	public void setActivityLevelDAO(IActivityLevelDAO activityLevelDAO) {
		this.activityLevelDAO = activityLevelDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	
	public void setCadreRegistrationService(
			CadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}

	public ICadreDeleteReasonDAO getCadreDeleteReasonDAO() {
		return cadreDeleteReasonDAO;
	}

	public void setCadreDeleteReasonDAO(ICadreDeleteReasonDAO cadreDeleteReasonDAO) {
		this.cadreDeleteReasonDAO = cadreDeleteReasonDAO;
	}
	
	
	public IDistrictConstituenciesDAO getDistrictConstituenciesDAO() {
		return districtConstituenciesDAO;
	}

	public void setDistrictConstituenciesDAO(
			IDistrictConstituenciesDAO districtConstituenciesDAO) {
		this.districtConstituenciesDAO = districtConstituenciesDAO;
	}

	public ITdpCadreCandidateDAO getTdpCadreCandidateDAO() {
		return tdpCadreCandidateDAO;
	}

	public void setTdpCadreCandidateDAO(ITdpCadreCandidateDAO tdpCadreCandidateDAO) {
		this.tdpCadreCandidateDAO = tdpCadreCandidateDAO;
	}

	public INewDistrictConstituencyDAO getNewDistrictConstituencyDAO() {
		return newDistrictConstituencyDAO;
	}

	public void setNewDistrictConstituencyDAO(
			INewDistrictConstituencyDAO newDistrictConstituencyDAO) {
		this.newDistrictConstituencyDAO = newDistrictConstituencyDAO;
	}

	public ICandidateResultDAO getCandidateResultDAO() {
		return candidateResultDAO;
	}

	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
		this.candidateResultDAO = candidateResultDAO;
	}

	public IEventUserDAO getEventUserDAO() {
		return eventUserDAO;
	}

	public void setEventRfidDetailsDAO(IEventRfidDetailsDAO eventRfidDetailsDAO) {
		this.eventRfidDetailsDAO = eventRfidDetailsDAO;
	}

	public void setEventSurveyUserDAO(IEventSurveyUserDAO eventSurveyUserDAO) {
		this.eventSurveyUserDAO = eventSurveyUserDAO;
	}

	public void setEventInviteeDAO(IEventInviteeDAO eventInviteeDAO) {
		this.eventInviteeDAO = eventInviteeDAO;
	}

	public void setEventDAO(IEventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}

	public void setPublicRepresentativeDAO(
			IPublicRepresentativeDAO publicRepresentativeDAO) {
		this.publicRepresentativeDAO = publicRepresentativeDAO;
	}

	public void setEventGroupDAO(IEventGroupDAO eventGroupDAO) {
		this.eventGroupDAO = eventGroupDAO;
	}

	
	public void setPublicRepresentativeTypeDAO(
			IPublicRepresentativeTypeDAO publicRepresentativeTypeDAO) {
		this.publicRepresentativeTypeDAO = publicRepresentativeTypeDAO;
	}


	public void setVoterAgeInfoDAO(IVoterAgeInfoDAO voterAgeInfoDAO) {
		this.voterAgeInfoDAO = voterAgeInfoDAO;
	}
	
	public void setTdpCadreInfoDAO(ITdpCadreInfoDAO tdpCadreInfoDAO) {
		this.tdpCadreInfoDAO = tdpCadreInfoDAO;
	}
	public void setCadreIvrResponseDAO(ICadreIvrResponseDAO cadreIvrResponseDAO) {
		this.cadreIvrResponseDAO = cadreIvrResponseDAO;
	}
	public void setIvrCampaignOptionsDAO(
			IIvrCampaignOptionsDAO ivrCampaignOptionsDAO) {
		this.ivrCampaignOptionsDAO = ivrCampaignOptionsDAO;
	}
	public void setCommitteIvrDistrictDetailDAO(
			ICommitteIvrDistrictDetailDAO committeIvrDistrictDetailDAO) {
		this.committeIvrDistrictDetailDAO = committeIvrDistrictDetailDAO;
	}
	public void setCommitteIvrTotalDetailDAO(
			ICommitteIvrTotalDetailDAO committeIvrTotalDetailDAO) {
		this.committeIvrTotalDetailDAO = committeIvrTotalDetailDAO;
	}
	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	public void setUserConstituencyAccessInfoDAO(
			IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO) {
		this.userConstituencyAccessInfoDAO = userConstituencyAccessInfoDAO;
	}
	public void setUserDistrictAccessInfoDAO(
			IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO) {
		this.userDistrictAccessInfoDAO = userDistrictAccessInfoDAO;
	}
	public ITdpCommitteeRoleHistoryDAO getTdpCommitteeRoleHistoryDAO() {
		return tdpCommitteeRoleHistoryDAO;
	}
	public void setTdpCommitteeRoleHistoryDAO(
			ITdpCommitteeRoleHistoryDAO tdpCommitteeRoleHistoryDAO) {
		this.tdpCommitteeRoleHistoryDAO = tdpCommitteeRoleHistoryDAO;
	}
	public ICadreCommitteeChangeDesignationsDAO getCadreCommitteeChangeDesignationsDAO() {
		return cadreCommitteeChangeDesignationsDAO;
	}
	public void setCadreCommitteeChangeDesignationsDAO(
			ICadreCommitteeChangeDesignationsDAO cadreCommitteeChangeDesignationsDAO) {
		this.cadreCommitteeChangeDesignationsDAO = cadreCommitteeChangeDesignationsDAO;
	}
	public ITdpCommitteeLevelDAO getTdpCommitteeLevelDAO() {
		return tdpCommitteeLevelDAO;
	}
	public void setTdpCommitteeLevelDAO(ITdpCommitteeLevelDAO tdpCommitteeLevelDAO) {
		this.tdpCommitteeLevelDAO = tdpCommitteeLevelDAO;
	}
	public ICadreCommitteeIncreasedPositionsDAO getCadreCommitteeIncreasedPositionsDAO() {
		return cadreCommitteeIncreasedPositionsDAO;
	}
	public void setCadreCommitteeIncreasedPositionsDAO(
			ICadreCommitteeIncreasedPositionsDAO cadreCommitteeIncreasedPositionsDAO) {
		this.cadreCommitteeIncreasedPositionsDAO = cadreCommitteeIncreasedPositionsDAO;
	}
	public void setTdpBasicCommitteeDAO(ITdpBasicCommitteeDAO tdpBasicCommitteeDAO) {
		this.tdpBasicCommitteeDAO = tdpBasicCommitteeDAO;
	}
	public void setCadreDetailsService(ICadreDetailsService cadreDetailsService) {
		this.cadreDetailsService = cadreDetailsService;
	}
	
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public void setTdpCommitteeMemberHistoryDAO(
			ITdpCommitteeMemberHistoryDAO tdpCommitteeMemberHistoryDAO) {
		this.tdpCommitteeMemberHistoryDAO = tdpCommitteeMemberHistoryDAO;
	}
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}
	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public void setElectionTypeDAO(IElectionTypeDAO electionTypeDAO) {
		this.electionTypeDAO = electionTypeDAO;
	}
	public void setEducationalQualificationsDAO(
			IEducationalQualificationsDAO educationalQualificationsDAO) {
		this.educationalQualificationsDAO = educationalQualificationsDAO;
	}
	public void setOccupationDAO(IOccupationDAO occupationDAO) {
		this.occupationDAO = occupationDAO;
	}
	public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
		this.casteStateDAO = casteStateDAO;
	}
	public void setVoterAgeRangeDAO(IVoterAgeRangeDAO voterAgeRangeDAO) {
		this.voterAgeRangeDAO = voterAgeRangeDAO;
	}
	public void setSmsCountrySmsService(SmsCountrySmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}
	public void setCadreOtpDetailsDAO(ICadreOtpDetailsDAO cadreOtpDetailsDAO) {
		this.cadreOtpDetailsDAO = cadreOtpDetailsDAO;
	}
	public void setCadreParticipatedElectionDAO(ICadreParticipatedElectionDAO cadreParticipatedElectionDAO) {
		this.cadreParticipatedElectionDAO = cadreParticipatedElectionDAO;
	}
	public void setCadrePreviousRolesDAO(
			ICadrePreviousRolesDAO cadrePreviousRolesDAO) {
		this.cadrePreviousRolesDAO = cadrePreviousRolesDAO;
	}
	public void setCadreCommitteeRoleDAO(
			ICadreCommitteeRoleDAO cadreCommitteeRoleDAO) {
		this.cadreCommitteeRoleDAO = cadreCommitteeRoleDAO;
	}
	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}	
	
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	
	public void setTdpCommitteeDAO(ITdpCommitteeDAO tdpCommitteeDAO) {
		this.tdpCommitteeDAO = tdpCommitteeDAO;
	}
	
	public void setTdpCommitteeRoleDAO(ITdpCommitteeRoleDAO tdpCommitteeRoleDAO) {
		this.tdpCommitteeRoleDAO = tdpCommitteeRoleDAO;
	}
	
	public void setTdpCommitteeMemberDAO(
			ITdpCommitteeMemberDAO tdpCommitteeMemberDAO) {
		this.tdpCommitteeMemberDAO = tdpCommitteeMemberDAO;
	}
	
	public void setTdpCommitteeElectrolsDAO(
			ITdpCommitteeElectrolsDAO tdpCommitteeElectrolsDAO) {
		this.tdpCommitteeElectrolsDAO = tdpCommitteeElectrolsDAO;
	}
	
	public void setTdpCommitteeElectrolRolesDAO(
			ITdpCommitteeElectrolRolesDAO tdpCommitteeElectrolRolesDAO) {
		this.tdpCommitteeElectrolRolesDAO = tdpCommitteeElectrolRolesDAO;
	}
	
	public void setTdpCommitteeDesignationDAO(
			ITdpCommitteeDesignationDAO tdpCommitteeDesignationDAO) {
		this.tdpCommitteeDesignationDAO = tdpCommitteeDesignationDAO;
	}
	
	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
	
	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}
	
	public void setAssemblyLocalElectionBodyWardDAO(
			IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO) {
		this.assemblyLocalElectionBodyWardDAO = assemblyLocalElectionBodyWardDAO;
	}
	
	
	public void setLocalElectionBodyWardDAO(
			ILocalElectionBodyWardDAO localElectionBodyWardDAO) {
		this.localElectionBodyWardDAO = localElectionBodyWardDAO;
	}
	public IUserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void setTdpCommitteeVacantPostDAO(
			ITdpCommitteeVacantPostDAO tdpCommitteeVacantPostDAO) {
		this.tdpCommitteeVacantPostDAO = tdpCommitteeVacantPostDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}
	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	
	public void setTdpCommitteeEnrollmentDAO(
			ITdpCommitteeEnrollmentDAO tdpCommitteeEnrollmentDAO) {
		this.tdpCommitteeEnrollmentDAO = tdpCommitteeEnrollmentDAO;
	}
     
	
	public CoreDashboardGenericService getCoreDashboardGenericService() {
		return coreDashboardGenericService;
	}

	public void setCoreDashboardGenericService(
			CoreDashboardGenericService coreDashboardGenericService) {
		this.coreDashboardGenericService = coreDashboardGenericService;
	}
	
	public ICommitteeConfirmRuleConditionDAO getCommitteeConfirmRuleConditionDAO() {
		return committeeConfirmRuleConditionDAO;
	}

	public void setCommitteeConfirmRuleConditionDAO(
			ICommitteeConfirmRuleConditionDAO committeeConfirmRuleConditionDAO) {
		this.committeeConfirmRuleConditionDAO = committeeConfirmRuleConditionDAO;
	}
	public IActivityConductedInfoDAO getActivityConductedInfoDAO() {
		return activityConductedInfoDAO;
	}
	public void setActivityConductedInfoDAO(IActivityConductedInfoDAO activityConductedInfoDAO) {
		this.activityConductedInfoDAO = activityConductedInfoDAO;
	}
    public IBoothInchargeDAO getBoothInchargeDAO() {
		return boothInchargeDAO;
	}
    public void setBoothInchargeDAO(IBoothInchargeDAO boothInchargeDAO) {
		this.boothInchargeDAO = boothInchargeDAO;
	}

	public CadreCommitteeVO getCadreDetailsByTdpCadreId(Long tdpCadreId)
	{
		CadreCommitteeVO cadreCommitteeVO = null;
		SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd");
		try {
			TdpCadre tdpCadre = tdpCadreDAO.get(tdpCadreId);
			if(tdpCadre != null)
			{
				cadreCommitteeVO = new CadreCommitteeVO();
				
				//CADRE BASIC DETAILS.
				cadreCommitteeVO.setCadreName(tdpCadre.getFirstname());
				cadreCommitteeVO.setTdpCadreId(tdpCadre.getTdpCadreId());
				if(tdpCadre.getMemberShipNo().toString().trim().length() > 8){
					cadreCommitteeVO.setMemberShipCardId(tdpCadre.getMemberShipNo().toString().trim().substring(tdpCadre.getMemberShipNo().toString().trim().length()-8));
				}else{
					cadreCommitteeVO.setMemberShipCardId(tdpCadre.getMemberShipNo().toString());
				}
				//cadreCommitteeVO.setMemberShipCardId(tdpCadre.getMemberShipNo().substring(4));
				if(tdpCadre.getAge() != null){
				    cadreCommitteeVO.setAge(tdpCadre.getAge().toString());
				}else{
					cadreCommitteeVO.setAge("");
				}
				if(tdpCadre.getGender() != null){
				  cadreCommitteeVO.setGender(tdpCadre.getGender());
				}
				cadreCommitteeVO.setDOB(tdpCadre.getDateOfBirth() != null ? format.format(tdpCadre.getDateOfBirth()):"");
				cadreCommitteeVO.setVoterId(tdpCadre.getVoterId() != null ? tdpCadre.getVoterId():0L);
				cadreCommitteeVO.setMobileType("");
				if(tdpCadre.getPreviousEnrollmentNo() != null){
				  cadreCommitteeVO.setPreEnrollNo(tdpCadre.getPreviousEnrollmentNo());
				}else{
					cadreCommitteeVO.setPreEnrollNo("");
				}
				cadreCommitteeVO.setMobileNo(tdpCadre.getMobileNo());
				if(tdpCadre.getMobileType() == null || tdpCadre.getMobileType().trim().length() == 0 ||  tdpCadre.getMobileType().trim().equalsIgnoreCase("N")){
					cadreCommitteeVO.setIsSmartPhone("N");
				}else{
					cadreCommitteeVO.setIsSmartPhone("Y");
				}
				//cadreCommitteeVO.setIsSmartPhone(tdpCadre.getMobileType());
				cadreCommitteeVO.setAdhaarNo(tdpCadre.getCadreAadherNo());
				cadreCommitteeVO.setAddress(tdpCadre.getUserAddress().getStreet() != null ? tdpCadre.getUserAddress().getStreet():"");
				if(tdpCadre.getCasteStateId() != null && tdpCadre.getCasteStateId().longValue() > 0){
					CasteState casteState = casteStateDAO.get(tdpCadre.getCasteStateId());
					cadreCommitteeVO.setCasteStateId(casteState.getCasteStateId());
					cadreCommitteeVO.setCasteName(casteState.getCaste().getCasteName());
				}else{
					cadreCommitteeVO.setCasteStateId(0L);
					cadreCommitteeVO.setCasteName("");
				}
				
				cadreCommitteeVO.setCasteCategoryId(tdpCadre.getCasteState() != null?tdpCadre.getCasteState().getCasteCategoryGroup().getCasteCategory().getCasteCategoryId():0L);
				cadreCommitteeVO.setCasteCategory(tdpCadre.getCasteState() != null? tdpCadre.getCasteState().getCasteCategoryGroup().getCasteCategory().getCategoryName():"");
				
				UserAddress address = tdpCadre.getUserAddress();
				if(address.getLocalElectionBody() != null){
					cadreCommitteeVO.setElectrolLocation(address.getLocalElectionBody().getName()+" "+address.getLocalElectionBody().getElectionType().getElectionType() );
				}else if(address.getTehsil() != null){
					cadreCommitteeVO.setElectrolLocation(address.getTehsil().getTehsilName()+" Mandal");
				}
				if(tdpCadre.getVoterId()  != null && tdpCadre.getVoterId().longValue() > 0){
				    cadreCommitteeVO.setVoterCardNo( voterDAO.get(tdpCadre.getVoterId()).getVoterIDCardNo());
				}else{
					cadreCommitteeVO.setVoterCardNo("");
				}
				if(cadreCommitteeVO.getVoterCardNo() != null && cadreCommitteeVO.getVoterCardNo().trim().length()<=0)
				{
					if(tdpCadre.getFamilyVoterId()  != null && tdpCadre.getFamilyVoterId().longValue() > 0){
					  cadreCommitteeVO.setFamilyVoterCardNo(voterDAO.get(tdpCadre.getFamilyVoterId()).getVoterIDCardNo());
					}else{
						cadreCommitteeVO.setFamilyVoterCardNo("");
					}
				}
				if(tdpCadre.getEducationId() != null &&  tdpCadre.getEducationId().longValue() > 0){
					EducationalQualifications eq = educationalQualificationsDAO.get(tdpCadre.getEducationId());
					cadreCommitteeVO.setEducationId(eq.getEduQualificationId());
					cadreCommitteeVO.setEducation(eq.getQualification());
				}else{
				    cadreCommitteeVO.setEducationId(0L);
				    cadreCommitteeVO.setEducation("");
				}
				if(tdpCadre.getOccupationId() != null &&  tdpCadre.getOccupationId().longValue() > 0){
					Occupation occupation = occupationDAO.get(tdpCadre.getOccupationId());
					cadreCommitteeVO.setOccupationId(occupation.getOccupationId());
					cadreCommitteeVO.setOccupation(occupation.getOccupation());
				}else{
				    cadreCommitteeVO.setOccupationId(0L);
				    cadreCommitteeVO.setOccupation("");
				}
				if(tdpCadre.getEmailId() != null){
				   cadreCommitteeVO.setEmailId(tdpCadre.getEmailId());
				}else{
					cadreCommitteeVO.setEmailId("");
				}
				cadreCommitteeVO.setImageURL(tdpCadre.getImage() != null ? tdpCadre.getImage():"null");
				
				//CADRE PREVIOUS ROLES IN COMMITTEES.
				cadreCommitteeVO.setPreviousRoles(getExistingRollsInfo(tdpCadreId));
				
				//CADRE PREVIOUS ELECTIONS PARTICIPATION INFO.
				cadreCommitteeVO.setPreviousElections(getExistingCadreParticipationInfo(tdpCadreId));
				
				//GET ALL CASTESTATE FOR AP STATE
				List<Object[]> castesList = casteStateDAO.getAllCasteDetailsForVoters(1L); // for AP state
				List<CadreCommitteeVO> stateCasteList = new ArrayList<CadreCommitteeVO>();
				if(castesList != null && castesList.size()>0){
					for (Object[] cast : castesList) {
						CadreCommitteeVO castevo = new  CadreCommitteeVO();
						castevo.setCasteStateId(cast[0] != null ? (Long) cast[0]:0L);
						castevo.setCasteName(cast[1] != null ? cast[1].toString():"");
						
						stateCasteList.add(castevo);
					}
					cadreCommitteeVO.setCasteList(stateCasteList);			
				}
				
				//CADRE IN COMMITTEES INFORMATION AT PRESENT.
				TdpCommitteeMember tdpCommitteeMember = null;
				List<TdpCommitteeMember> tdpCommitteeMemberList = tdpCommitteeMemberDAO.getTdpCommitteeMemberByTdpCadreId(tdpCadreId);
				if(tdpCommitteeMemberList.size() > 0){
					tdpCommitteeMember = tdpCommitteeMemberList.get(0);
				}
				if(tdpCommitteeMember != null)
				{
					String LocationType = tdpCommitteeMember.getTdpCommitteeRole().getTdpCommittee().getTdpCommitteeLevel().getTdpCommitteeLevel();
					String location = null;
					Long locationValue = tdpCommitteeMember.getTdpCommitteeRole().getTdpCommittee().getTdpCommitteeLevelValue();
					if(LocationType.equalsIgnoreCase(IConstants.PANCHAYAT) || LocationType.equalsIgnoreCase("Village"))
					{
						location = panchayatDAO.get(locationValue).getPanchayatName()+" Panchayat";
					}
					else if(LocationType.equalsIgnoreCase(IConstants.WARD))
					{
						location = constituencyDAO.get(locationValue).getName();
					}
					else if(LocationType.equalsIgnoreCase(IConstants.MANDAL))
					{
						location = tehsilDAO.get(locationValue).getTehsilName()+" Mandal";
					}	
					else if(LocationType.equalsIgnoreCase("Local Election Body"))
					{
						LocalElectionBody localElectionBody = localElectionBodyDAO.get(locationValue);						
						location = localElectionBody.getName() +" "+localElectionBody.getElectionType().getElectionType();
						
						if(locationValue.longValue() == 20L || locationValue.longValue() == 124L || locationValue.longValue() == 119L)
						{
							String wardName = constituencyDAO.get(locationValue).getName();
							location = location+" - "+wardName;
						}
						
					}else if(LocationType.equalsIgnoreCase("Division"))
					{
						 Constituency constituency = constituencyDAO.get(locationValue);
						LocalElectionBody localElectionBody =  constituency.getLocalElectionBody();						
						location = localElectionBody.getName() +" "+localElectionBody.getElectionType().getElectionType();
							String wardName =constituency.getName();
							List name = localElectionBodyWardDAO.findWardName(locationValue);
							if(name != null && name.size() > 0 && name.get(0) != null){
								location = location+" - "+wardName+"("+name.get(0).toString()+")";
							}else{
							    location = location+" - "+wardName;
							}
						
						
					}else if(LocationType.equalsIgnoreCase("State")){
						if(locationValue.longValue() == 1){
							location = "AndhraPradesh State";
						}else{
							location = "Telangana State";
						}
					}else if(LocationType.equalsIgnoreCase("District")){
						location = districtDAO.get(locationValue).getDistrictName()+" District";
					}
					
					String positionName = tdpCommitteeMember.getTdpCommitteeRole().getTdpRoles().getRole();
					String committeeName = tdpCommitteeMember.getTdpCommitteeRole().getTdpCommittee().getTdpBasicCommittee().getName();
					
					cadreCommitteeVO.setCommitteeLocation(location);
					cadreCommitteeVO.setCommitteePosition(positionName);
					cadreCommitteeVO.setCommitteeName(committeeName);
					
				}
				
			}
		} catch (Exception e) {
			cadreCommitteeVO = null;
			LOG.error("Exception occured in getCadreDetailsByTdpCadreId() at CadreCommitteeService.",e);
		}
		
		return cadreCommitteeVO;
	}
	
	public List<CadreCommitteeVO> getExistingCadreParticipationInfo(Long tdpCadreId)
	{
		List<CadreCommitteeVO> returnList = new ArrayList<CadreCommitteeVO>();
		
		try {			
			List<Object[]> participationInfo = cadreParticipatedElectionDAO.getPreviousParticipationInfoByTdpCadreId(tdpCadreId);

			if(participationInfo != null && participationInfo.size()>0)
			{
				for (Object[] participation : participationInfo)
				{
					CadreCommitteeVO vo = new CadreCommitteeVO();
					if(participation[0] != null)
					{
						Election eleciton = (Election) participation[0];
						vo.setElectioinYearId(eleciton.getElectionId());
						vo.setElectionTypeId(eleciton.getElectionScope().getElectionScopeId());
						vo.setElectionType(eleciton.getElectionScope().getElectionType().getElectionType());
						vo.setElectionYear(eleciton.getElectionYear()+" ("+eleciton.getElecSubtype()+" )");
						if(participation[1] != null)
						{
							vo.setConstituency(constituencyDAO.get(participation[1] != null ? Long.valueOf(participation[1].toString()):0L).getName());
						}
						vo.setConstituencyId(participation[1] != null ? Long.valueOf(participation[1].toString()):0L);
						returnList.add(vo);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getExistingCadreParticipationInfo in CadreCommitteeService service", e);
		}
		
		return returnList;
	}
	
	public List<CadreCommitteeVO> getExistingRollsInfo(Long tdpCadreId)
	{
		List<CadreCommitteeVO> returnList = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			
			List<Object[]> participationInfo = cadrePreviousRolesDAO.getexistingRolesForTdpCadreByTdpCadreIdForCommittee(tdpCadreId);//0,1,2,3
			
			if(participationInfo != null && participationInfo.size()>0)
			{
				returnList = new ArrayList<CadreCommitteeVO>();
				
				for (Object[] participation : participationInfo)
				{
					CadreCommitteeVO vo = new CadreCommitteeVO();	
					vo.setCommitteeLevelId(participation[4] != null ? Long.valueOf(participation[4].toString().trim()):0L);
					vo.setCommitteeId(participation[5] != null ? Long.valueOf(participation[5].toString().trim()):0L);
					vo.setRoleId(participation[0] != null ? Long.valueOf(participation[0].toString().trim()):0L);// role id
					vo.setFromDate(participation[1] != null ?format.format((Date)participation[1]):"");		// from date
					vo.setToDate(participation[2] != null ? format.format((Date)participation[2]):"");		// to date 
					vo.setRole(participation[3] != null ? participation[3].toString().trim():"");	
					
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getExistingCadreParticipationInfo in CadreCommitteeService service", e);
		}
		
		return returnList;
	}
	
	public String genarateOTP(Long userId, Long mobileNo)
	{
		String refeRenceNo = "";
		try
		{
			cadreOtpDetailsDAO.updateIsDeleted(mobileNo);
			RandomNumberGeneraion rnd = new RandomNumberGeneraion();
			int otpRand = 0;
			int refRand = 0;
			
			while(otpRand <= 0 && refRand <= 0){
				 otpRand = rnd.randomGenerator(6);
				 refRand = rnd.randomGenerator(6);
			}
			refeRenceNo = String.valueOf(refRand);
			String message = "your OTP is "+otpRand+" for Reference Id # " +refRand+" ";
			String[] phoneNumbers = {mobileNo.toString()};
			smsCountrySmsService.sendSmsFromAdmin(message, true, phoneNumbers);
			
			DateUtilService dateUtilService = new DateUtilService();
			CadreOtpDetails cadreOtpDetails  = new CadreOtpDetails();
			cadreOtpDetails.setOtpNo(String.valueOf(otpRand));
			cadreOtpDetails.setOtpReferenceId(String.valueOf(refRand));
			cadreOtpDetails.setMobileNo(mobileNo.toString());
			cadreOtpDetails.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			cadreOtpDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			cadreOtpDetails.setUserId(userId);
			cadreOtpDetails.setIsDeleted("N");
			CadreOtpDetails savedStatus = cadreOtpDetailsDAO.save(cadreOtpDetails);
		}
		catch (Exception e) {
			refeRenceNo = null;
			LOG.error("Exception occured in genarateOTP() in CadreCommitteeService class.",e);
		}
		return refeRenceNo;
	}
	
	
	public String validateOTPForUser(Long userId, Long mobileNo,Long refNo, Long otpNumber)
	{
		String status = "";
		try
		{
			CadreOtpDetails cadreOtpDetails = cadreOtpDetailsDAO.checkOTPValid(mobileNo,refNo,userId);
			if(cadreOtpDetails != null)
			{
				if(cadreOtpDetails.getOtpNo() != null && Long.valueOf(cadreOtpDetails.getOtpNo().toString()).longValue() != 0L)
				{
					if(otpNumber.longValue() == Long.valueOf(cadreOtpDetails.getOtpNo().toString()).longValue())
					{
						status = "success";
						cadreOtpDetails.setIsDeleted("Y");
						cadreOtpDetailsDAO.save(cadreOtpDetails);
					}
					else
					{
						status = "failure";
					}
				}
			}
			
		}
		catch (Exception e) {
			status = null;
			LOG.error("Exception occured in validateOTPForUser() in CadreCommitteeService class.",e);
		}
		return status;
	}
	
	public List<String> getAgeRangeDetailsForCadre()
	{
		List<String> ageRangesList = null;
		try {
			List<VoterAgeRange> ageRanges = voterAgeRangeDAO.getVoterAgeRangeList();
			if(ageRanges != null && ageRanges.size()>0)
			{
				ageRangesList = new ArrayList<String>();
				for (VoterAgeRange ageRange : ageRanges) 
				{
					String btwnAge = ageRange.getMinValue()+" - "+ageRange.getMaxValue();
					ageRangesList.add(btwnAge);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured in getAgeRangeDetailsForCadre() in CadreCommitteeService class.",e);
		}
		return ageRangesList;
	}
	
	public List<GenericVO> getAllCasteDetailsForState()
	{
		List<GenericVO> castesDetails = null;
		try {
			List<Object[]> castesList = casteStateDAO.getAllCasteDetailsForVoters(1L);
			if(castesList != null && castesList.size()>0)
			{
				castesDetails = new ArrayList<GenericVO>();
				for (Object[] caste : castesList)
				{
					GenericVO vo = new GenericVO();
					vo.setId(caste[0] != null ? Long.valueOf(caste[0].toString().trim()):0L);
					vo.setName(caste[1] != null ? caste[1].toString().trim():"");
					castesDetails.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured in getAgeRangeDetailsForCadre() in CadreCommitteeService class.",e);
		}
		return castesDetails;
	}
	
	public List<GenericVO> getAllEducationList()
	{
		List<GenericVO> educationList = null;
		try {
			List<EducationalQualifications> educations = educationalQualificationsDAO.getEducationalQualificationsList();
			if(educations != null && educations.size()>0)
			{
				educationList = new ArrayList<GenericVO>();
				for (EducationalQualifications education : educations)
				{
					GenericVO vo = new GenericVO();
					vo.setId(education.getEduQualificationId());
					vo.setName(education.getQualification());
					educationList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured in getAllEducationList() in CadreCommitteeService class.",e);
		}
		return educationList;
	}
	
	public List<GenericVO> getAllOccupationsList()
	{
		List<GenericVO> occupationsList = null;
		try {
			List<Occupation> occupations = occupationDAO.getOccupationList();
			if(occupations != null && occupations.size()>0)
			{
				occupationsList = new ArrayList<GenericVO>();
				for (Occupation occupation : occupations)
				{
					GenericVO vo = new GenericVO();
					vo.setId(occupation.getOccupationId());
					vo.setName(occupation.getOccupation());
					occupationsList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured in getAllOccupationsList() in CadreCommitteeService class.",e);
		}
		return occupationsList;
	}
	
	public List<GenericVO> getElectionOptionDetailsForCadre()
	{
		List<GenericVO> returnList = null;
		try{
			List<ElectionType> electionTypeList = electionTypeDAO.getElectionTypeList();
			if(electionTypeList != null && electionTypeList.size()>0)
			{
				returnList = new ArrayList<GenericVO>();
				for (ElectionType electionType : electionTypeList) 
				{
					GenericVO vo = new GenericVO();
					vo.setId(electionType.getElectionTypeId() != null ? electionType.getElectionTypeId():0L);
					vo.setName(electionType.getElectionType() != null ? electionType.getElectionType():"");
					
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getElectionOptionDetailsForCadre() in CadreCommitteeService service", e);
		}
		return returnList;
	}
	
	public List<LocationWiseBoothDetailsVO> getLocationsList(Long constituencyId,String level){
		try{
			if(level.equalsIgnoreCase("mandal")){
				return getMandalMunicCorpDetailsNew(constituencyId);
			}else{
				return getPanchayatWardDivisionDetailsNew(constituencyId);
			}
		}catch(Exception e){
			LOG.error("Exception raised in getLocationsList", e);
			return new ArrayList<LocationWiseBoothDetailsVO>(); 
		}
	}
	
	public List<LocationWiseBoothDetailsVO> getMandalMunicCorpDetails1(Long constituencyId){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		 LocationWiseBoothDetailsVO vo1 = new LocationWiseBoothDetailsVO();
	        vo1.setLocationId(0l);
	        vo1.setLocationName("Select Mandal/Muncipality/Corporation");
	        locationsList.add(vo1);
		LocationWiseBoothDetailsVO vo = null;
		List<SelectOptionVO> locations = regionServiceDataImp.getAllMandalsByConstituencyID(constituencyId);
		List<Object[]> localBodies = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituency(constituencyId);
	        for(SelectOptionVO location:locations){
	        	vo = new LocationWiseBoothDetailsVO();
	        	vo.setLocationId(Long.valueOf("2"+location.getId()));
	        	vo.setLocationName(WordUtils.capitalizeFully(location.getName()+" Mandal"));
	        	vo.setName("Mandal");
	        	locationsList.add(vo);
	        }
	        int count =0;
	        for(Object[] localBodi:localBodies){
	        	count = count+1;
	        	if(((Long)localBodi[0]).longValue() != 20l &&  ((Long)localBodi[0]).longValue() != 124l && ((Long)localBodi[0]).longValue() != 119l){
		        	vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("1"+localBodi[0].toString()));
		        	vo.setLocationName(WordUtils.capitalizeFully(localBodi[1].toString()));
		        	vo.setName("Municipality");
		        	locationsList.add(vo);
	        	}
	        	else
	        	{
	        		/*if(count==1)
	        			locationsList.clear();*/
	        		
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("1"+localBodi[0].toString()));
		        	vo.setLocationName(WordUtils.capitalizeFully(localBodi[1].toString()));
		        	vo.setName("Municipality");
		        	locationsList.add(vo);
	        	}
	        }
	       
	        return locationsList;
	}
	
	public List<LocationWiseBoothDetailsVO> getMandalMunicCorpDetailsNew(Long constituencyId){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		LocationWiseBoothDetailsVO vo = null;
		List<Long> greaterCorpIds = new ArrayList<Long>();
		List<SelectOptionVO> locations = regionServiceDataImp.getAllMandalsByConstituencyID(constituencyId);
		List<Object[]> localBodies = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituency(constituencyId);
	        for(SelectOptionVO location:locations){
	        	vo = new LocationWiseBoothDetailsVO();
	        	vo.setLocationId(Long.valueOf("2"+location.getId()));
	        	vo.setLocationName(location.getName()+" Mandal");
	        	locationsList.add(vo);
	        }
	        for(Object[] localBodi:localBodies){
	        	Long localBdyId = (Long)localBodi[0];
	        	if(!(localBdyId.longValue() == 20l ||  localBdyId.longValue() == 124l || localBdyId.longValue() == 119l)){
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("1"+localBodi[0].toString()));
		        	vo.setLocationName(localBodi[1].toString());
		        	locationsList.add(vo);
	        	}else{
	        		greaterCorpIds.add(localBdyId);
	        	}
	        }
	        if(greaterCorpIds.size() > 0){
        	  for(Long id:greaterCorpIds){
        		  List<Object[]>  wards = assemblyLocalElectionBodyWardDAO.findWardsByLocalBodyConstiId(id, constituencyId);
        		  for(Object[] location:wards){
        			  vo = new LocationWiseBoothDetailsVO();
  		        	vo.setLocationId(Long.valueOf("3"+location[0].toString()));
  		        	vo.setLocationName(location[1].toString());
  		        	locationsList.add(vo);
        		  }
        	  }
	        }
	        return locationsList;
	}
	public List<LocationWiseBoothDetailsVO> getPanchayatWardDivisionDetails1(Long constituencyId){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		LocationWiseBoothDetailsVO vo = null;
		List<LocationWiseBoothDetailsVO> mandalsList = getMandalMunicCorpDetails1(constituencyId);
		List<Long> mandalIds = new ArrayList<Long>();
		List<Long> localBodyIds = new ArrayList<Long>();
	        for(LocationWiseBoothDetailsVO location:mandalsList){        	
	        	if(location.getLocationId().toString().substring(0,1).trim().equalsIgnoreCase("1")){
	        		localBodyIds.add(Long.valueOf(location.getLocationId().toString().substring(1)));
	        	}else{
	        		mandalIds.add(Long.valueOf(location.getLocationId().toString().substring(1)));
	        	}
	        }
	        if(mandalIds.size() > 0){
	        	//0panchayatId,1panchayatName,2tehsilName
	        	List<Object[]> panchayatsList = panchayatDAO.getAllPanchayatsInMandals(mandalIds);
	        	for(Object[] panchayat:panchayatsList){
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("1"+(Long)panchayat[0]));
		        	vo.setLocationName(panchayat[1].toString()+"("+panchayat[2].toString()+")");
		        	locationsList.add(vo);
	        	}
	        }
	        if(localBodyIds.size() > 0){
	        	//0wardId,1pwardName,2localBdyName
	        	List<Object[]> localBodyList = constituencyDAO.getWardsInLocalElectionBody(localBodyIds);
	        	for(Object[] localBody:localBodyList){
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("2"+(Long)localBody[0]));
		        	vo.setLocationName(localBody[1].toString()+"("+localBody[2].toString()+")");
		        	locationsList.add(vo);
	        	}
	        }
	        return locationsList;
	} 
	public List<LocationWiseBoothDetailsVO> getPanchayatWardDivisionDetailsNew(Long constituencyId){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		LocationWiseBoothDetailsVO vo = null;
		List<LocationWiseBoothDetailsVO> mandalsList = getMandalMunicCorpDetails1(constituencyId);
		List<Long> mandalIds = new ArrayList<Long>();
		List<Long> localBodyIds = new ArrayList<Long>();
	        for(LocationWiseBoothDetailsVO location:mandalsList){        	
	        	if(location.getLocationId().toString().substring(0,1).trim().equalsIgnoreCase("1")){
	        		Long localBdyId = Long.valueOf(location.getLocationId().toString().substring(1));
	        		//if(!(localBdyId.longValue() == 20l ||  localBdyId.longValue() == 124l || localBdyId.longValue() == 119l)){
	        		   localBodyIds.add(localBdyId);
	        		//}
	        	}else{
	        		String locIdString = location.getLocationId().toString().substring(1);
	        		if(locIdString != null && !locIdString.trim().isEmpty()){
	        			mandalIds.add(Long.valueOf(locIdString));
	        		}
	        	}
	        }
	        if(mandalIds.size() > 0){
	        	//0panchayatId,1panchayatName,2tehsilName
	        	List<Object[]> panchayatsList = panchayatDAO.getAllPanchayatsInMandalsByPublciationId(constituencyId,mandalIds,IConstants.VOTER_DATA_PUBLICATION_ID);
	        	for(Object[] panchayat:panchayatsList){
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("1"+(Long)panchayat[0]));
		        	vo.setLocationName(panchayat[1].toString()+"("+panchayat[2].toString()+")");
		        	locationsList.add(vo);
	        	}
	        }
	        if(localBodyIds.size() > 0){
	        	//0wardId,1pwardName,2localBdyName
	        	List<Object[]> localBodyList = constituencyDAO.getWardsInLocalElectionBody(localBodyIds);
	        	for(Object[] localBody:localBodyList){
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("2"+(Long)localBody[0]));
		        	vo.setLocationName(localBody[1].toString()+"("+localBody[2].toString()+")");
		        	locationsList.add(vo);
	        	}
	        }
	        return locationsList;
	}
	
	/** 
	  *  Get All Affliated Committees In A Location.
	*/
	public  List<LocationWiseBoothDetailsVO> getAllAffiliatedCommittiesInALocation(Long levelId,Long levelValue){
		List<LocationWiseBoothDetailsVO> affiliatedCommittiesList = new ArrayList<LocationWiseBoothDetailsVO>();
		try{
			LocationWiseBoothDetailsVO vo = null;
			List<Object[]> commitiesList = tdpCommitteeDAO.getAllAffiliatedCommittiesInALocation(levelId, levelValue);
			for(Object[] committee:commitiesList){
				vo = new LocationWiseBoothDetailsVO();
				vo.setLocationId((Long)committee[0]);
				vo.setLocationName(committee[1].toString());
				affiliatedCommittiesList.add(vo);
			}
		}catch(Exception e){
			LOG.error("Exception raised in getAllAffiliatedCommittiesInALocation", e);
		}
		return affiliatedCommittiesList;
	}
	
	
	/** 
	  *  Get All Committees(Main + Affliated ) Related Members Information  For A Given Location.
	*/
	public LocationWiseBoothDetailsVO getAllCommitteeMembersInfoInALoc(Long locationLvl,Long locationVal){
		LocationWiseBoothDetailsVO returnVo = new LocationWiseBoothDetailsVO();
        List<SelectOptionVO> committeeMembersList = new ArrayList<SelectOptionVO>();
		returnVo.setHamletsOfTownship(committeeMembersList);
		try{
			List<Object[]>  electedMembersInfoList = tdpCommitteeMemberDAO.getAllCommitteeMembersInfoInALoc(locationLvl, locationVal);
			SelectOptionVO memberVo = null;
			for(Object[] electedMembersInfo:electedMembersInfoList){
				memberVo = new SelectOptionVO();
				memberVo.setValue(electedMembersInfo[0].toString());//role
				if(electedMembersInfo[1] != null){
				   memberVo.setUrl(electedMembersInfo[1].toString());//image
				}
				memberVo.setName(electedMembersInfo[2].toString());//name
				if(electedMembersInfo[3].toString().trim().length() > 8){
					memberVo.setType(electedMembersInfo[3].toString().trim().substring(electedMembersInfo[3].toString().trim().length()-8));
				}else{
					memberVo.setType(electedMembersInfo[3].toString());//membership
				}
				
				
				memberVo.setId((Long)electedMembersInfo[4]);//tdpCommitteeMemberId
				memberVo.setOrderId((Long)electedMembersInfo[5]);//tdpCadreId
				memberVo.setMainAccountId((Long)electedMembersInfo[6]);//tdpCommitteeRoleId
				memberVo.setMandalName(electedMembersInfo[7].toString());//tdpCommitteeRoleId
				memberVo.setCommitteeMemberStatus(electedMembersInfo[8] != null ? electedMembersInfo[8].toString() : "");
				committeeMembersList.add(memberVo);
			}
		}catch(Exception e){

			LOG.error("Exception raised in getAllCommitteeMembersInfoInALoc", e);
		}
		return returnVo;
	}
	
	
	/** 
	  *  Get Main Committee Members Information  For A Given Location.
	*/
	public LocationWiseBoothDetailsVO getMainCommitteeMembersInfo(Long levelId,Long levelValue){
		
		Long committeeId = getMainCommitteeIdInALocation(levelId,levelValue);
		if(committeeId != null){
			return getCommitteeMembersInfoNEW(committeeId);
		}else{
			return new LocationWiseBoothDetailsVO();
		}
	}
	
	public Long getMainCommitteeIdInALocation(Long levelId,Long levelValue){
		Long committeeId = null;
		try{

			List<Long>  committeeEnrollmentIds = new ArrayList<Long>();
			committeeEnrollmentIds.add(IConstants.CURRENT_ENROLLMENT_ID);
			
			List<Long> committeeIds = tdpCommitteeDAO.getMainCommittiesInALocation(levelId, levelValue,committeeEnrollmentIds,null,null);
			if(committeeIds.size() > 0){
				committeeId = committeeIds.get(0);
			}
		}catch(Exception e){
			LOG.error("Exception raised in getMainCommitteeIdInALocation", e);
		}
		return committeeId;
	}
	
	/** 
	  *  GET  COMMITTEE MEMBERS INFORMATION  FOR A GIVEN COMMITTEE.
	*/
	public LocationWiseBoothDetailsVO getCommitteeMembersInfo(Long committeeId){
		LocationWiseBoothDetailsVO returnVo = new LocationWiseBoothDetailsVO();
		returnVo.setPopulation(committeeId);
		if(committeeId != null && committeeId.longValue() > 0){
		   returnVo.setLocationName(tdpCommitteeDAO.get(committeeId).getIsCommitteeConfirmed());
		}
		List<LocationWiseBoothDetailsVO> committeeMembersInfoList = new ArrayList<LocationWiseBoothDetailsVO>();
		List<SelectOptionVO> committeeMembersList = new ArrayList<SelectOptionVO>();
		
		returnVo.setResult(committeeMembersInfoList);
		returnVo.setHamletsOfTownship(committeeMembersList);
		try{
			Map<Long,LocationWiseBoothDetailsVO> committeeMembersMap = new HashMap<Long,LocationWiseBoothDetailsVO>();
			LocationWiseBoothDetailsVO vo = null;
			SelectOptionVO memberVo = null;
			//0committeeRoleid,1role name,2max nos
			List<Object[]> totalCommitteRolesList = tdpCommitteeRoleDAO.getAllCommitteeRoles(committeeId);
			for(Object[] totalCommitteRole:totalCommitteRolesList){
				vo = new LocationWiseBoothDetailsVO();
				vo.setLocationName(totalCommitteRole[1].toString());
				vo.setLocationId((Long)totalCommitteRole[0]);
				vo.setPopulation((Long)totalCommitteRole[2]);//total positions
				vo.setTotal((Long)totalCommitteRole[2]);//total positions left
				vo.setVotesPolled(0l);//total positions filled
				committeeMembersMap.put((Long)totalCommitteRole[0], vo);
				committeeMembersInfoList.add(vo);
			}
			if(committeeMembersMap.size() > 0){
				//0 count,1 id
				List<Object[]>  electedPersonsList = tdpCommitteeMemberDAO.getRoleWiseAllocatedMembersCount(committeeMembersMap.keySet());
				for(Object[] electedPersons:electedPersonsList){
					LocationWiseBoothDetailsVO roleInfo = committeeMembersMap.get((Long)electedPersons[1]);
					roleInfo.setVotesPolled((Long)electedPersons[0]);
					roleInfo.setTotal(roleInfo.getPopulation() - (Long)electedPersons[0]);
				}
				
				//0 role,1 image,2name,3membership,4tdpCommitteeMemberId,5cadreId,6tdpCommitteeRoleId
				List<Object[]>  electedMembersInfoList = tdpCommitteeMemberDAO.getMembersInfo(committeeMembersMap.keySet());
				
				for(Object[] electedMembersInfo:electedMembersInfoList){
					memberVo = new SelectOptionVO();
					memberVo.setValue(electedMembersInfo[0].toString());//role
					if(electedMembersInfo[1] != null){
					   memberVo.setUrl(electedMembersInfo[1].toString());//image
					}
					memberVo.setName(electedMembersInfo[2].toString());//name
					if(electedMembersInfo[3].toString().trim().length() > 8){
						memberVo.setType(electedMembersInfo[3].toString().trim().substring(electedMembersInfo[3].toString().trim().length()-8));//membership
					}else{
					    memberVo.setType(electedMembersInfo[3].toString());//membership
					}
					memberVo.setId((Long)electedMembersInfo[4]);//tdpCommitteeMemberId
					memberVo.setOrderId((Long)electedMembersInfo[5]);//tdpCadreId
					memberVo.setMainAccountId((Long)electedMembersInfo[6]);//tdpCommitteeRoleId
					committeeMembersList.add(memberVo);
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised in getCommitteeMembersInfo", e);
		}
		return returnVo;
	}
	public LocationWiseBoothDetailsVO getCommitteeMembersInfoNEW(Long committeeId){
		LocationWiseBoothDetailsVO returnVo = new LocationWiseBoothDetailsVO();
		
		returnVo.setPopulation(committeeId);
		if(committeeId != null && committeeId.longValue() > 0){
		   returnVo.setLocationName(tdpCommitteeDAO.get(committeeId).getIsCommitteeConfirmed());
		}
		
		try{
			
			//SUMMARY BLOCK
			List<LocationWiseBoothDetailsVO> committeeMembersInfoList = new ArrayList<LocationWiseBoothDetailsVO>();
			Map<Long,LocationWiseBoothDetailsVO> committeeMembersMap = new HashMap<Long,LocationWiseBoothDetailsVO>();
			
			List<Object[]> totalCommitteRolesList = tdpCommitteeRoleDAO.getAllCommitteeRoles(committeeId);
			
			for(Object[] obj:totalCommitteRolesList){
				
				LocationWiseBoothDetailsVO vo = new LocationWiseBoothDetailsVO();
				
				vo.setLocationId((Long)obj[0]);//roleId
				vo.setLocationName(obj[1] != null ? obj[1].toString() : "");//role
				vo.setRoleType( obj[3] != null ? obj[3].toString() : null);
				vo.setTotalCount(obj[2] != null ? (Long)obj[2] : 0l);
				vo.setVaccancyCount(vo.getTotalCount());
				vo.setFinalizedCount(0l);
				
				committeeMembersMap.put((Long)obj[0], vo);
				committeeMembersInfoList.add(vo);
			}
			if(committeeMembersMap.size() > 0){
				
				List<Object[]>  electedPersonsList = tdpCommitteeMemberDAO.getRoleWiseProposedAndFinalizedMembersCounts(committeeMembersMap.keySet());
				for(Object[] obj:electedPersonsList){
					LocationWiseBoothDetailsVO roleVO = committeeMembersMap.get((Long)obj[0]);	
					if(roleVO != null){
						if(obj[1] != null && !obj[1].toString().trim().isEmpty()){
							if(obj[1].toString().trim().equalsIgnoreCase("P")){//proposed
								roleVO.setProposedCount(obj[2] != null ? (Long)obj[2] : 0l);
							}else{//finalized
								roleVO.setFinalizedCount(obj[2] != null ? (Long)obj[2] : 0l);
								roleVO.setVaccancyCount( roleVO.getTotalCount() - roleVO.getFinalizedCount());
							}
						}
					}
				}
				
				for(Map.Entry<Long,LocationWiseBoothDetailsVO>  roleEntry: committeeMembersMap.entrySet()){
					LocationWiseBoothDetailsVO role = roleEntry.getValue();
					if(role != null && role.getRoleType() != null && role.getRoleType().equalsIgnoreCase("P")){
						role.setProposedCount( role.getProposedCount() + role.getFinalizedCount());
					}
				}
				
			}
			
			
			returnVo.setResult(committeeMembersInfoList);
			
			//GETTING MEMBERS BLOCK
			List<SelectOptionVO> committeeMembersList = new ArrayList<SelectOptionVO>();
			if(committeeMembersMap.size() > 0){
				  List<Object[]>  electedMembersInfoList = tdpCommitteeMemberDAO.getMembersInfo(committeeMembersMap.keySet());//committee role members
				//List<Object[]>  electedMembersInfoList = tdpCommitteeMemberDAO.getFinalizedMembersInfoForCommitteeRoleIds(committeeMembersMap.keySet()); 
				for(Object[] electedMembersInfo:electedMembersInfoList){
					SelectOptionVO memberVo = new SelectOptionVO();
					memberVo.setValue(electedMembersInfo[0].toString());//role
					if(electedMembersInfo[1] != null){
					   memberVo.setUrl(electedMembersInfo[1].toString());//image
					}
					memberVo.setName(electedMembersInfo[2].toString());//name
					if(electedMembersInfo[3].toString().trim().length() > 8){
						memberVo.setType(electedMembersInfo[3].toString().trim().substring(electedMembersInfo[3].toString().trim().length()-8));//membership
					}else{
					    memberVo.setType(electedMembersInfo[3].toString());//membership
					}
					memberVo.setId((Long)electedMembersInfo[4]);//tdpCommitteeMemberId
					memberVo.setOrderId((Long)electedMembersInfo[5]);//tdpCadreId
					memberVo.setMainAccountId((Long)electedMembersInfo[6]);//tdpCommitteeRoleId
					memberVo.setCommitteeMemberStatus(electedMembersInfo[7] != null ? electedMembersInfo[7].toString() : null);
					committeeMembersList.add(memberVo);
				}
				returnVo.setHamletsOfTownship(committeeMembersList);
			}
			
		}catch(Exception e){
			LOG.error("Exception raised in getCommitteeMembersInfoNEW", e);
		}
		return returnVo;
	}
	
	//Hint Please call this method in transaction only
	public ResultStatus saveMandalLevelElectrolInfo(final Long tdpCadreId,final  List<CadrePreviousRollesVO> eligibleRoles)
	{
		ResultStatus status = new ResultStatus();
	  synchronized("CADRECOMMITTEEMANDALLVLELECTROL"){
		try {
			status = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
				 public Object doInTransaction(TransactionStatus status) {
					 ResultStatus resultStatus = new ResultStatus();
					 
					 if(eligibleRoles != null && eligibleRoles.size() > 0)
						{
							
							Long tdpCommitteeLevelId= null;
							Long levelValue = null;
							UserAddress userAddress = tdpCadreDAO.get(tdpCadreId).getUserAddress();
							if(userAddress.getLocalElectionBody() != null)
							{
								tdpCommitteeLevelId = 7L;
								levelValue = userAddress.getLocalElectionBody().getLocalElectionBodyId();
								
								if(levelValue.longValue() == 20L || levelValue.longValue() == 124L || levelValue.longValue() == 119L)
								{
									tdpCommitteeLevelId = 9L;
									if( userAddress.getWard() != null){
									     levelValue = userAddress.getWard().getConstituencyId();
									}
								}
							}
							else if(userAddress.getTehsil() != null)
							{
								tdpCommitteeLevelId = 5L;
								levelValue = userAddress.getTehsil().getTehsilId();
							}
							else
							{
								resultStatus.setResultCode(3);
								resultStatus.setMessage("Location Not Mapped for this Cadre...");
								
								return resultStatus;
							}
							
							Long tdpCommitteeId = getTdpCommitteeId(1l,tdpCommitteeLevelId,levelValue,IConstants.CURRENT_ENROLLMENT_ID);
							if(tdpCommitteeId == null){
								resultStatus.setResultCode(3);
								resultStatus.setMessage("Committee Didn't Exist For This Location...");
								return resultStatus;
							}
							Long count = tdpCommitteeElectrolsDAO.checkUserAlreadyAddedToThisCommittee(tdpCadreId,tdpCommitteeLevelId,levelValue,IConstants.CURRENT_ENROLLMENT_ID,1l,tdpCommitteeId);
							if(count != null && count.longValue() > 0l){
								resultStatus.setResultCode(3);
								resultStatus.setMessage("Candidate Already Added As Electrol To This Committee...");
								return resultStatus;
							}
							CadrePreviousRollesVO eligibleRole1 = eligibleRoles.get(0);
							if(eligibleRole1 != null && eligibleRole1.getDesignationLevelId() != null && eligibleRole1.getFromDateStr() != null)
							{
								TdpCommitteeElectrols tdpCommitteeElectrols = new TdpCommitteeElectrols();
								tdpCommitteeElectrols.setTdpCadreId(tdpCadreId);
								tdpCommitteeElectrols.setTdpCommitteeLevelId(tdpCommitteeLevelId);
								tdpCommitteeElectrols.setLevelValue(levelValue);
								tdpCommitteeElectrols.setTdpCommitteeEnrollmentId(IConstants.CURRENT_ENROLLMENT_ID);
								tdpCommitteeElectrols.setTdpCommitteeTypeId(1l);
								tdpCommitteeElectrols.setTdpCommitteeId(tdpCommitteeId);
								tdpCommitteeElectrols.setIsDeleted("N");
								tdpCommitteeElectrols.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								tdpCommitteeElectrols = tdpCommitteeElectrolsDAO.save(tdpCommitteeElectrols);
								
									for(CadrePreviousRollesVO eligibleRole:eligibleRoles){
										if(eligibleRole != null && eligibleRole.getDesignationLevelId() != null && eligibleRole.getDesignationLevelId().longValue() > 0){
											TdpCommitteeElectrolRoles tdpCommitteeElectrolRoles = new TdpCommitteeElectrolRoles();
											tdpCommitteeElectrolRoles.setIsDeleted("N");
											tdpCommitteeElectrolRoles.setTdpCommitteeDesignationId(eligibleRole.getDesignationLevelId());
											tdpCommitteeElectrolRoles.setTdpCommitteeElectrolsId(tdpCommitteeElectrols.getTdpCommitteeElectrolsId());
											
											try {
												if(eligibleRole.getFromDateStr() != null && eligibleRole.getFromDateStr().trim().length() > 0){
												   tdpCommitteeElectrolRoles.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(eligibleRole.getFromDateStr()));
												}
												if(eligibleRole.getToDateStr() != null && eligibleRole.getToDateStr().trim().length() > 0){
													tdpCommitteeElectrolRoles.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(eligibleRole.getToDateStr()));
												}
												
											} catch (Exception e) {}
											
											tdpCommitteeElectrolRolesDAO.save(tdpCommitteeElectrolRoles);
										}
									}
								}
						}else{
							resultStatus.setResultCode(2);
							resultStatus.setMessage(" Not Eligible to add as a Electrol...");
							return resultStatus;
						}
					 
					 resultStatus.setResultCode(0);
					 resultStatus.setMessage("Electoral Added Successfully... ");
					return resultStatus;
					 
				 }
			});
			
		} catch (Exception e) {
			status.setResultCode(1);
			status.setMessage("Error occured while updating details...");
			LOG.error("Exception raised in saveMandalLevelElectrolInfo", e);
		}
		return status;	
	  }
	}
	
	public Long getTdpCommitteeId(Long tdpBasicCommitteeId,Long tdpCommitteeLevelId,Long tdpCommitteeLevelValue,Long tdpCommitteeEnrollmentId){
		Long tdpCommitteeId = null;
		List<Long> ids = tdpCommitteeDAO.getTdpCommitteeId(tdpBasicCommitteeId,tdpCommitteeLevelId,tdpCommitteeLevelValue,tdpCommitteeEnrollmentId);
		if(ids.size() > 0 && ids.get(0) != null){
			tdpCommitteeId = ids.get(0);
		}
		return tdpCommitteeId;
	}
	
	//Hint Please call this method in transaction only
	public ResultStatus saveMandalLevelAffliactedElectrolInfo(final Long tdpCadreId,final  Long tdpBasicCommitteeId){
		
		ResultStatus status = new ResultStatus();
		synchronized("CADRECOMMITTEEMANDALLVLAFFELECTROL"){
		try {
				status = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
				 public Object doInTransaction(TransactionStatus status) {
					 ResultStatus resultStatus = new ResultStatus();
					Long tdpCommitteeLevelId= null;
					Long levelValue = null;
					UserAddress userAddress = tdpCadreDAO.get(tdpCadreId).getUserAddress();
					if(userAddress.getLocalElectionBody() != null)
					{
						tdpCommitteeLevelId = 7L;
						levelValue = userAddress.getLocalElectionBody().getLocalElectionBodyId();
						
						if(levelValue.longValue() == 20L || levelValue.longValue() == 124L || levelValue.longValue() == 119L)
						{
							tdpCommitteeLevelId = 9L;
							if( userAddress.getWard() != null){
							     levelValue = userAddress.getWard().getConstituencyId();
							}
						}
					}
					else if(userAddress.getTehsil() != null)
					{
						tdpCommitteeLevelId = 5L;
						levelValue = userAddress.getTehsil().getTehsilId();
					}
					else
					{
						resultStatus.setResultCode(3);
						resultStatus.setMessage("Location Not Mapped for this Cadre...");
						return resultStatus;
					}
					Long tdpCommitteeId = getTdpCommitteeId(tdpBasicCommitteeId,tdpCommitteeLevelId,levelValue,IConstants.CURRENT_ENROLLMENT_ID);
					if(tdpCommitteeId == null){
						resultStatus.setResultCode(3);
						resultStatus.setMessage("Committee Didn't Exist For This Location...");
						return resultStatus;
					}
					Long count = tdpCommitteeElectrolsDAO.checkUserAlreadyAddedToThisCommittee(tdpCadreId,tdpCommitteeLevelId,levelValue,IConstants.CURRENT_ENROLLMENT_ID,2l,tdpCommitteeId);
					if(count != null && count.longValue() > 0l){
						resultStatus.setResultCode(3);
						resultStatus.setMessage("Candidate Already Added As Electrol To This Committee...");
						return resultStatus;
					}
					
					Long affiliatedCount = tdpCommitteeElectrolsDAO.checkUserAlreadyAddedToOtherAffiliatedCommittee(tdpCadreId, tdpCommitteeLevelId, levelValue, IConstants.CURRENT_ENROLLMENT_ID);
					if(affiliatedCount != null && affiliatedCount.longValue() > 0l){
						resultStatus.setResultCode(3);
						resultStatus.setMessage("Candidate Already Added As Electoral For Affiliated Committee.You Cannot Add An Electrol For More Than One Affiliated Committee...");
						return resultStatus;
					}
					TdpCommitteeElectrols tdpCommitteeElectrols = new TdpCommitteeElectrols();
					tdpCommitteeElectrols.setTdpCadreId(tdpCadreId);
					tdpCommitteeElectrols.setTdpCommitteeLevelId(tdpCommitteeLevelId);
					tdpCommitteeElectrols.setLevelValue(levelValue);
					tdpCommitteeElectrols.setTdpCommitteeEnrollmentId(IConstants.CURRENT_ENROLLMENT_ID);
					tdpCommitteeElectrols.setTdpCommitteeTypeId(2L);
					tdpCommitteeElectrols.setTdpCommitteeId(tdpCommitteeId);
					tdpCommitteeElectrols.setIsDeleted("N");
					tdpCommitteeElectrols.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					tdpCommitteeElectrols = tdpCommitteeElectrolsDAO.save(tdpCommitteeElectrols);
					
					resultStatus.setResultCode(0);
					resultStatus.setMessage(" Electoral Added Successfully... ");
					
					return resultStatus;
				 }
			});
		}
		catch(Exception e)
		{
			status.setResultCode(1);
			status.setMessage("Error occured while updating details... ");
			LOG.error("Exception raised in saveMandalLevelAffliactedElectrolInfo", e);
		}
		
		return status;
	  }
    }
	
	public Long getTdpCommittee(Long tdpBasicCommitteeId,Long tdpCommitteeLevelId,Long tdpCommitteeLevelValue){
		Long tdpCommitteeId = null;
		List<Long> ids = tdpCommitteeDAO.getTdpCommittee(tdpBasicCommitteeId,tdpCommitteeLevelId,tdpCommitteeLevelValue);
		if(ids.size() > 0 && ids.get(0) != null){
			tdpCommitteeId = ids.get(0);
		}
		return tdpCommitteeId;
	}
	public List<LocationWiseBoothDetailsVO> getAllTdpCommitteeDesignations(){
		List<LocationWiseBoothDetailsVO> designationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		LocationWiseBoothDetailsVO designation = null;
		try{
			List<TdpCommitteeDesignation> tdpCommitteeDesignationList = tdpCommitteeDesignationDAO.getAll();
			for(TdpCommitteeDesignation tdpCommitteeDesignation:tdpCommitteeDesignationList){
				designation = new LocationWiseBoothDetailsVO();
				designation.setLocationId(tdpCommitteeDesignation.getTdpCommitteeDesignationId());
				designation.setLocationName(tdpCommitteeDesignation.getDesignation());
				designationsList.add(designation);
			}
		}catch(Exception e){
			LOG.error("Exception raised in getAllTdpCommitteeDesignations", e);
		}
		return designationsList;
	}
	
	public ResultStatus saveCadreCommitteDetails(final Long userId, final Long tdpCadreId,final Long tdpCommitteeRoleId)
	{
		ResultStatus status = new ResultStatus();
	
	//	try {
			
			Long oldCommitteeId = null;
			
			//CHECK IF ALREADY EXISTS
			boolean isExist = false;
			List<Object[]> cadreCommitteeInfo = tdpCommitteeMemberDAO.getMemberInfo(tdpCadreId);//any status
			if(cadreCommitteeInfo != null && cadreCommitteeInfo.size()>0){
				isExist = true;
			}
			
			//CHECK FOR ELIGIBILITY
			boolean isEligible = true;
			String eligibleCheck = checkIsVacancyForDesignation(tdpCommitteeRoleId);
			if(eligibleCheck != null && !eligibleCheck.toString().trim().isEmpty()){
				isEligible = false;
			}
			if(!isEligible){
				status.setMessage(eligibleCheck);
				status.setResultCode(2);
				return status;
			}
			
			if(isEligible)				
			{
				DateUtilService dateUtilService = new DateUtilService();
				
				TdpCommitteeMember tdpCommitteeMember = null;
				if(isExist)
				{
					List<TdpCommitteeMember> tdpCommitteeMemberList = tdpCommitteeMemberDAO.getTdpCommitteeMemberByTdpCadreId(tdpCadreId);//any status
					if(tdpCommitteeMemberList.size() > 0){
						tdpCommitteeMember = tdpCommitteeMemberList.get(0);
					}
					
					try {
						TdpCommitteeVacantPost tdpCommitteeVacantPost = new TdpCommitteeVacantPost();
						tdpCommitteeVacantPost.setVacantCommitteeRole(tdpCommitteeMember.getTdpCommitteeRole());
						tdpCommitteeVacantPost.setCadre(tdpCommitteeMember.getTdpCadre());
						tdpCommitteeVacantPost.setCadreNewCommitteeRole(tdpCommitteeRoleDAO.get(tdpCommitteeRoleId));
						tdpCommitteeVacantPost.setTdpCommitteeEnrollment(tdpCommitteeEnrollmentDAO.get(IConstants.CURRENT_ENROLLMENT_ID));
						tdpCommitteeVacantPost.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						tdpCommitteeVacantPost.setInsertedBy(userDAO.get(userId));
						tdpCommitteeVacantPostDAO.save(tdpCommitteeVacantPost);
						
						oldCommitteeId = tdpCommitteeMember.getTdpCommitteeRole().getTdpCommitteeId();
						TdpCommitteeMemberHistory tdpCommitteeMemberHistory = new TdpCommitteeMemberHistory();
						
						tdpCommitteeMemberHistory.setTdpCommitteeMemberId(tdpCommitteeMember.getTdpCommitteeMemberId());
						tdpCommitteeMemberHistory.setTdpCadreId(tdpCommitteeMember.getTdpCadreId());
						tdpCommitteeMemberHistory.setTdpCommitteeRoleId(tdpCommitteeMember.getTdpCommitteeRoleId());
						tdpCommitteeMemberHistory.setTdpCommitteeEnrollmentId(tdpCommitteeMember.getTdpCommitteeEnrollmentId());
						tdpCommitteeMemberHistory.setStartDate(tdpCommitteeMember.getStartDate());
						tdpCommitteeMemberHistory.setEndDate(tdpCommitteeMember.getEndDate());
						tdpCommitteeMemberHistory.setInsertedUserId(tdpCommitteeMember.getInsertedUserId());
						tdpCommitteeMemberHistory.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						tdpCommitteeMemberHistory.setUpdatedUserId(tdpCommitteeMember.getUpdatedUserId());
						tdpCommitteeMemberHistory.setUpdatedTime(tdpCommitteeMember.getUpdatedTime());					
						tdpCommitteeMemberHistory.setIsActive(tdpCommitteeMember.getIsActive());
						
						tdpCommitteeMemberHistory.setUserId(tdpCommitteeMember.getInsertedUserId());
						tdpCommitteeMemberHistory.setInsertedUserId(userId);
						tdpCommitteeMemberHistory.setHistoryInsertedTime(dateUtilService.getCurrentDateAndTime());
						tdpCommitteeMemberHistoryDAO.save(tdpCommitteeMemberHistory);
					} catch (Exception e) {
						LOG.error("Exception raised in saveCadreCommitteDetails when inserting in history table ", e);
					}
					
				}
				else
				{
					tdpCommitteeMember = new TdpCommitteeMember(); 
					tdpCommitteeMember.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				}
				
				tdpCommitteeMember.setTdpCommitteeRoleId(tdpCommitteeRoleId);
				tdpCommitteeMember.setTdpCadreId(tdpCadreId);
				tdpCommitteeMember.setIsActive("Y");
				tdpCommitteeMember.setTdpCommitteeEnrollmentId(IConstants.CURRENT_ENROLLMENT_ID);
				
				String committeeRoleType = tdpCommitteeRoleDAO.getTdpCommitteeRoleType(tdpCommitteeRoleId);
				if(committeeRoleType != null){
					tdpCommitteeMember.setStatus(committeeRoleType);
				}
				
				tdpCommitteeMember.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				if(isExist)
				{
					tdpCommitteeMember.setUpdatedUserId(userId);
				}
				else
				{
					tdpCommitteeMember.setInsertedUserId(userId);
				}
				
				tdpCommitteeMember = tdpCommitteeMemberDAO.save(tdpCommitteeMember);
				
				
				TdpCommittee tdpCommittee = tdpCommitteeRoleDAO.get(tdpCommitteeRoleId).getTdpCommittee();
				
				//UPDATE START DATE TO COMMITTEE
				if(tdpCommittee.getStartedDate() == null){
					tdpCommittee.setStartedDate(dateUtilService.getCurrentDateAndTime());
					tdpCommitteeDAO.save(tdpCommittee);
				}
				if(oldCommitteeId != null && oldCommitteeId.longValue() > 0){
					updateCommitteeStartedStatus(oldCommitteeId);
				}
				
				if(committeeRoleType != null){
					if(committeeRoleType.equalsIgnoreCase("F")){
						status.setMessage(" Cadre Added To Committee Successfully... ");		
					}else if(committeeRoleType.equalsIgnoreCase("P")){
						status.setMessage(" Cadre Proposed To Committee Successfully... ");
					}
				}
				status.setResultCode(0);
			}
		
		return status;
	}
	
	public void updateCommitteeStartedStatus(Long tdpCommitteId){
		Long membersCnt = tdpCommitteeMemberDAO.getCommitteMembers(tdpCommitteId);
		if(membersCnt == null || membersCnt == 0)
		{
			TdpCommittee tdpCommittee = tdpCommitteeDAO.get(tdpCommitteId);
			tdpCommittee.setIsCommitteeConfirmed("N");
			tdpCommittee.setStartedDate(null);
			tdpCommittee.setCompletedDate(null);
			tdpCommitteeDAO.save(tdpCommittee);
		}else{
			TdpCommittee tdpCommittee = tdpCommitteeDAO.get(tdpCommitteId);
			tdpCommittee.setIsCommitteeConfirmed("N");
			tdpCommittee.setCompletedDate(null);
			tdpCommitteeDAO.save(tdpCommittee);
		}
	}
	public List<CadrePreviousRollesVO> getCadreEligiableRoles(Long tdpCadreId){
		List<CadrePreviousRollesVO> rolesList = new ArrayList<CadrePreviousRollesVO>();
			try{
			CadrePreviousRollesVO vo = null;
			SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
			//0 id,1 name,2 startDate,3endDate
			List<Object[]> cadreRoles = tdpCommitteeElectrolRolesDAO.getAllRolesForACadre(tdpCadreId);
			for(Object[] role:cadreRoles){
				vo = new CadrePreviousRollesVO();
				vo.setDesignationLevelId((Long)role[0]);
				vo.setCandidateId(role[1].toString());
				vo.setFromDateStr("");
				vo.setToDateStr("");
				if(role[2] != null){
					vo.setFromDateStr(sdf.format((Date)role[2]));
				}
	            if(role[3] != null){
	            	vo.setToDateStr(sdf.format((Date)role[3]));
				}
	            
	            rolesList.add(vo);
			}
		}catch(Exception e){
			LOG.error("Exception raised in getCadreEligiableRoles", e);
		}
		return rolesList;
	}
	public List<GenericVO> getCadsteDetailsByGroupId(Long casteGroupId){
		
		List<GenericVO> casteDetail=new ArrayList<GenericVO>();
		List<Object[]> casteDetailsVO = casteStateDAO.getStatewiseCastNamesByGroupId(casteGroupId,1L);
		
		if(casteDetailsVO !=null){
			
			for (Object[] caste : casteDetailsVO) {
				GenericVO casteDetails = new GenericVO();
				casteDetails.setId(caste[0] != null ? Long.valueOf(caste[0].toString().trim()):0L);
				casteDetails.setName(caste[1] != null ? caste[1].toString().trim():"");
				casteDetail.add(casteDetails);
			}
		}
		if(casteGroupId == 0){
			casteDetail=getAllCasteDetailsForState();
		}
		
		return casteDetail;
	}
	public List<GenericVO> getPanchayatDetailsByMandalId(Long tehsilId){
		
		List<GenericVO> panachatiesList = new ArrayList<GenericVO>();
		List<Object[]> panchayties=null;
		if(tehsilId !=null ){
			if(Long.valueOf(tehsilId.toString().substring(0, 1))==2){
				 panchayties = panchayatDAO.getPanchayatsByTehsilId(Long.valueOf(tehsilId.toString().substring(1)));
			}
			if(Long.valueOf(tehsilId.toString().substring(0, 1))==1){
				 panchayties = constituencyDAO.getWardIdAndName(Long.valueOf(tehsilId.toString().substring(1)));
			}
			if(panchayties !=null ){
				for (Object[] list : panchayties) {
					GenericVO panchayaties = new GenericVO();
					panchayaties.setId(Long.valueOf(list[0].toString()));
					panchayaties.setName(list[1].toString());
					
					panachatiesList.add(panchayaties);
				}
				return panachatiesList;
			}
			else{	
				return null;
			}
		}
		return panachatiesList;
	}
	public List<Long> getBoothsInPanchayatId(Long panchayatId){
		List<Long> booth=new ArrayList<Long>();
		List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(
				panchayatId, new Long(IConstants.VOTER_DATA_PUBLICATION_ID));
		if(boothsList !=null){
			for (Object[] objects : boothsList) {
				booth.add((Long)objects[0]);
			}
			return booth;
		}else{
			return null;	
		}
	}
	
	public List<IdNameVO> getLocationsOfCommitteeLevel(Long levelId,Long constiId){
		// STATE - 1, DISTRICT - 2, MANDAL - 5, PANCHAYAT - 7,  MUNCIPAL-CORP-GHMC - 6, WARD - 9, INCHARGE - 10, DIVISION - 11
		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
			
		List<Long> mandalIds =new ArrayList<Long>();
		
		if(levelId==2){//DISTRICT
			
			IdNameVO iv = new IdNameVO(0l, "Select District");
			finalList.add(iv);
			
			List<Object[]> list = districtDAO.getAllDistrictDetails(1l);
			if(list!=null && list.size()>0){
				for(Object[] obj:list){
					IdNameVO temp = new IdNameVO();
					temp.setId(Long.valueOf(obj[0].toString()));
					temp.setName(obj[1].toString());
					
					finalList.add(temp);
				}
			}
			
		}
		
		if(levelId==5){
			
			IdNameVO iv = new IdNameVO(0l, "Select Mandal");
			finalList.add(iv);
			
			List<SelectOptionVO> locations = regionServiceDataImp.getTehsilsInAConstituency(constiId);
			if(locations!=null && locations.size()>0){
				for(SelectOptionVO sv:locations){
					IdNameVO temp = new IdNameVO();
					temp.setId(sv.getId());
					temp.setName(sv.getName());
					
					finalList.add(temp);
				}
			}
		}
		
		if(levelId==7){
			List<SelectOptionVO> locations = regionServiceDataImp.getTehsilsInAConstituency(constiId);
				if(locations!=null && locations.size()>0){
					for(SelectOptionVO sv:locations){
						mandalIds.add(sv.getId());
					}
				}
				
				IdNameVO iv = new IdNameVO(0l, "Select Panchayat");
				finalList.add(iv);
				
			if(mandalIds!=null && mandalIds.size()>0){
				List<Object[]> panchayatsList = panchayatDAO.getAllPanchayatsInMandals(mandalIds);
		       	
		       	if(panchayatsList!=null && panchayatsList.size()>0){
		       		for(Object[] panchayat:panchayatsList){
			       		IdNameVO temp = new IdNameVO();
						temp.setId(Long.valueOf(panchayat[0].toString()));
						temp.setName(panchayat[1].toString()+"("+panchayat[2].toString()+")");
						
						finalList.add(temp);
					}
		       	}
		     }
	        	
	     }
		
		if(levelId == 6){
			List<Object[]> localBodies = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituency(constiId);
			
			for(Object[] localBodi:localBodies){
				IdNameVO temp = new IdNameVO();
				temp.setId(Long.valueOf(localBodi[0].toString()));
				temp.setName(localBodi[1].toString());
				finalList.add(temp);
	        }
		}
		
		if(levelId ==9){
			
			List<Object[]> localBodies = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituency(constiId);
			List<Long> localBodyIds = new ArrayList<Long>();
			for(Object[] localBodi:localBodies){
				localBodyIds.add(Long.valueOf(localBodi[0].toString()));
		    }
				
			 if(localBodyIds.size() > 0){
		        	List<Object[]> localBodyList = constituencyDAO.getWardsInLocalElectionBody(localBodyIds);
		        	if(localBodyList!=null && localBodyList.size()>0){
		        		for(Object[] localBody:localBodyList){
			        		IdNameVO temp = new IdNameVO();
							temp.setId(Long.valueOf((Long)localBody[0]));
							temp.setName(localBody[1].toString()+"("+localBody[2].toString()+")");
							finalList.add(temp);
				    	}
		        	}
		        	
		        }
			
		}
		
		if(levelId ==11){
			
			List<Object[]> localBodies = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituency(constiId);
			List<Long> localBodyIds = new ArrayList<Long>();
			for(Object[] localBodi:localBodies){
				localBodyIds.add(Long.valueOf(localBodi[0].toString()));
		    }
			
			if(localBodyIds.size()>0){
				List<Object[]>  wards = assemblyLocalElectionBodyWardDAO.findWardsByLocalBodyConstiId(localBodyIds.get(0), constiId);
	    		  for(Object[] location:wards){
	    			  
	    			  IdNameVO temp = new IdNameVO();
						temp.setId(Long.valueOf(location[0].toString()));
						temp.setName(location[1].toString());
						finalList.add(temp);
				  }
			}
			
		}
		
		return finalList;
	}
	
	public List<IdNameVO> getConstituenciesOfState(Long levelId){
		
		// STATE - 1, DISTRICT - 2, MANDAL - 5, PANCHAYAT - 7,  MUNCIPAL-CORP-GHMC - 6, WARD - 9, INCHARGE - 10, DIVISION - 11
		
		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
		IdNameVO iv = new IdNameVO(0l, "Select Constituency");
		finalList.add(iv);
		
		if(levelId!=11){
			List<Object[]> asslyList = constituencyDAO.getConstituencyByStateAndAreaType(1l,levelId);
			if(asslyList!=null && asslyList.size()>0){
				for(Object[] obj:asslyList){
					IdNameVO vo = new IdNameVO(Long.valueOf(obj[0].toString()), obj[1].toString());
					
					finalList.add(vo);
				}
			}
		}else{
			List<Object[]> asslyList = assemblyLocalElectionBodyDAO.getGreaterCitiesConstituencies();
			if(asslyList!=null && asslyList.size()>0){
				for(Object[] obj:asslyList){
					IdNameVO vo = new IdNameVO(Long.valueOf(obj[0].toString()), obj[1].toString());
					finalList.add(vo);
				}
			}
			
		}
		
		return finalList;
	}

	public CadreCommitteeVO searchTdpCadreDetailsBySearchCriteriaForCadreCommitte(Long locationLevel,Long locationId, String searchName,String memberShipCardNo,
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,Long fromAge,Long toAge,String houseNo,String gender,int startIndex,int maxIndex,boolean isRemoved,Long enrollmentId,String searchType)
	{
		CadreCommitteeVO cadreCommitteeVO = new CadreCommitteeVO();
		try {
			List<Long> tdpCadreIdsNominatedIdsList = new ArrayList<Long>(0);
			TdpCadreVO tdpCadreVO = cadreDetailsService.searchTdpCadreDetailsBySearchCriteriaForCommitte(locationLevel,locationId, searchName,memberShipCardNo, 
					voterCardNo, trNumber, mobileNo,casteStateId,casteCategory,fromAge,toAge,houseNo,gender,startIndex,maxIndex,isRemoved,enrollmentId,searchType);
			List<CadreCommitteeVO> cadreCommitteeList = null;
			if(tdpCadreVO != null)
			{
				List<TdpCadreVO> tdpCadreVOList = tdpCadreVO.getTdpCadreDetailsList();
				List<Long> tdpCadreIdsList = new ArrayList<Long>();
				
			
				if(tdpCadreVOList != null && tdpCadreVOList.size()>0)
				{
					cadreCommitteeList = new ArrayList<CadreCommitteeVO>();
					
					for (TdpCadreVO tdpCadre : tdpCadreVOList)
					{
						tdpCadreIdsList.add(tdpCadre.getId());
						tdpCadreIdsNominatedIdsList.add(tdpCadre.getId());
						CadreCommitteeVO committeeVO = new CadreCommitteeVO();
						committeeVO.setTdpCadreId(tdpCadre.getId());
						if(tdpCadre.getMemberShipNo() != null){
							if(tdpCadre.getMemberShipNo().toString().trim().length() > 8){
								committeeVO.setMemberShipCardId(tdpCadre.getMemberShipNo().toString().trim().substring(tdpCadre.getMemberShipNo().toString().trim().length()-8));
							}else{
								committeeVO.setMemberShipCardId(tdpCadre.getMemberShipNo().toString());
							}
						}else{
							committeeVO.setMemberShipCardId("");
						}
						//committeeVO.setMemberShipCardId(tdpCadre.getMemberShipNo() != null ? tdpCadre.getMemberShipNo().substring(tdpCadre.getMemberShipNo().length() - 8) :"");
						committeeVO.setCadreName(tdpCadre.getCadreName());
						committeeVO.setRelativeName(tdpCadre.getRelativeName());
						committeeVO.setAge(tdpCadre.getAge().toString());
						committeeVO.setMobileNo(tdpCadre.getMobileNo());
						committeeVO.setCasteName(tdpCadre.getCasteName());
						committeeVO.setGender(tdpCadre.getGender());
						committeeVO.setVoterCardNo(tdpCadre.getVoterCardNo());
						committeeVO.setImageURL(tdpCadre.getImageURL());
						committeeVO.setDataSourceType(tdpCadre.getDataSourceType());
						committeeVO.setDeletedStatus(tdpCadre.getDeletedStatus());
						committeeVO.setDeletedReason(tdpCadre.getDeleteReason());
						committeeVO.setImportantLeaderType(tdpCadre.getImportantLeaderType());
						committeeVO.setImportantLeaderLevel(tdpCadre.getImportantLeaderLevel());
						committeeVO.setImportantLeaderLocation(tdpCadre.getImportantLeaderLocation());
						committeeVO.setImportantLeaderCadreId(tdpCadre.getImportantLeaderCadreId());
						committeeVO.setFromYear(tdpCadre.getFromYear());
						committeeVO.setToYear(tdpCadre.getToYear());
						committeeVO.setYear(tdpCadre.getYear());
						committeeVO.setEnrollmentYears(tdpCadre.getEnrollmentYears());
						committeeVO.setCadreVoterId(tdpCadre.getVoterId());
						cadreCommitteeList.add(committeeVO);
					}
					if(maxIndex != 0)
					cadreCommitteeList.get(0).setMobileType(tdpCadreVOList.get(0).getTotalCount().toString());
				}
				
				if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
				{
					setCurrentDesignation(cadreCommitteeList,tdpCadreIdsList);
					//setCurrentElectrolInfo(cadreCommitteeList,tdpCadreIdsList);
					checkIsAlreadyRegistered(cadreCommitteeList, tdpCadreIdsList);
					setAddressForCadre(cadreCommitteeList,tdpCadreIdsList);
					
				}
				
				//cadreCommitteeVO.setPreviousRoles(cadreCommitteeList);
			}
			
			if(maxIndex == 0 && commonMethodsUtilService.isListOrSetValid(tdpCadreIdsNominatedIdsList)){
				List<Object[]> nominatedCandidatesList = nominationPostCandidateDAO.getNOminatedCadreList(tdpCadreIdsNominatedIdsList);
				if(commonMethodsUtilService.isListOrSetValid(nominatedCandidatesList)){
					for (Object[] param : nominatedCandidatesList) {
						CadreCommitteeVO cadreCommitteVO =  (CadreCommitteeVO) setterAndGetterUtilService.getMatchedVOfromList(cadreCommitteeList, "tdpCadreId", commonMethodsUtilService.getStringValueForObject(param[0]));
						if(cadreCommitteVO != null){
							cadreCommitteVO.setNominatedPostCandidateId(commonMethodsUtilService.getLongValueForObject(param[1]));
						}
					}
				}
			}
			List<CadreCommitteeVO> memberList = getMemberStatusDetails(tdpCadreIdsNominatedIdsList);
			if(memberList != null && memberList.size() > 0l){
				cadreCommitteeVO.setPreviousElections(memberList);
			}
			cadreCommitteeVO.setPreviousRoles(cadreCommitteeList);
			
		} catch (Exception e) {
			LOG.error("Exception raised in searchTdpCadreDetailsBySearchCriteriaForCadreCommitte", e);
		}
		return cadreCommitteeVO;
	}
	
	public void setAddressForCadre(List<CadreCommitteeVO> cadreCommitteeList,List<Long> tdpCadreIdsList)
	{
		
		try{
			
			 List<Object[]> list = tdpCadreDAO.getUserAddressForCadre(tdpCadreIdsList);
		
			 for(Object[] params : list)
			 {
				 AddressVO vo = null;
				 CadreCommitteeVO cadreVO = getMatchedVOById(cadreCommitteeList,(Long)params[0]);
					if(cadreVO != null)
					{
						if(params[1] != null)
							{
							 vo = new AddressVO();
							UserAddress userAddress = (UserAddress) params[1];
							vo.setConstituencyId(Long.valueOf(userAddress.getConstituency() != null ? userAddress.getConstituency().getConstituencyId().toString():"0"));
							vo.setConstituencyName(userAddress.getConstituency() != null ?userAddress.getConstituency().getName() : "");
							vo.setDistrictId(Long.valueOf(userAddress.getDistrict() != null ? userAddress.getDistrict().getDistrictId().toString():"0"));
							vo.setDistrictName(userAddress.getDistrict() != null ? userAddress.getDistrict().getDistrictName() : "");
							vo.setMandalId(Long.valueOf(userAddress.getTehsil() != null ? userAddress.getTehsil().getTehsilId().toString():"0"));
							vo.setMandalName(userAddress.getTehsil() != null ? userAddress.getTehsil().getTehsilName() : "");
							vo.setPanchaytId(Long.valueOf(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getPanchayatId().toString():"0"));
							vo.setPanchayatName(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getPanchayatName() : "");
							vo.setLocalElectionBodyId(Long.valueOf(userAddress.getLocalElectionBody() != null ? userAddress.getLocalElectionBody().getLocalElectionBodyId().toString():"0"));
							vo.setLocalElectionBodyName(userAddress.getLocalElectionBody() != null ? userAddress.getLocalElectionBody().getName() : "");
							vo.setWardId(Long.valueOf(userAddress.getWard() != null ? userAddress.getWard().getConstituencyId().toString():"0"));
							vo.setWardName(userAddress.getWard() != null ? userAddress.getWard().getName() : "");
							}
						
					}
					cadreVO.setAddressVO(vo);
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void checkIsAlreadyRegistered(List<CadreCommitteeVO> cadreCommitteeList,List<Long> tdpCadreIdsList){
		
		Map<Long,String> checkMap = new LinkedHashMap<Long, String>();
		List<Object[]> list = tdpCadreDAO.checkAlreayRegistrationByMemberShipNo(tdpCadreIdsList);
		if(list != null && list.size() > 0){
			for (Object[] objects : list) {
				//checkMap.put(Long.valueOf(objects[1].toString()), "Already Registered"); 
				
				String paymentStatus = commonMethodsUtilService.getStringValueForObject(objects[2]);
				if(paymentStatus != null && paymentStatus.trim().equalsIgnoreCase(IConstants.NOT_PAID_STATUS))
					checkMap.put(Long.valueOf(objects[1].toString()), "Payment is Pending");
				else
					checkMap.put(Long.valueOf(objects[1].toString()), "Already Registered");
			}
		}
		
		if(cadreCommitteeList != null && cadreCommitteeList.size() > 0){
			for (CadreCommitteeVO vo : cadreCommitteeList) {
				Long cadreId = vo.getTdpCadreId();
				String check = checkMap.get(cadreId);
				
				if(check != null && check.contains("Payment")){
					vo.setPaymentStatus("PAY NOW");
				}
				if(check != null){
					vo.setAlreadyRegistered(check);
					vo.setPaymentStatus("PAID");
				}
			}
		}
	}
	public void setCurrentDesignation(List<CadreCommitteeVO> cadreCommitteeList,List<Long> tdpCadreIdsList){
		List<Object[]> tdpCommitteeMemberList = tdpCommitteeMemberDAO.getTdpCommitteeMemberForTdpCadreIdList(tdpCadreIdsList,IConstants.CURRENT_ENROLLMENT_ID);
		
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
				CadreCommitteeVO cadreVO = getMatchedVOById(cadreCommitteeList,id);
				if(cadreVO != null)
				{
					String location = null;
					if(locationValue.longValue() > 0L){
						//System.out.println("tdpCadreId :"+id+"  \t positionName  :"+positionName);
						location = getLocationName(LocationTypeId,locationValue);
						cadreVO.setCommitteeLocation(location);
					    cadreVO.setCommitteePosition(positionName);
					    cadreVO.setCommitteeName(committeeName);
					    cadreVO.setCommitteeMemberStatus(tdpCadre[7] != null ? tdpCadre[7].toString() :"");
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
	public void setCurrentElectrolInfo(List<CadreCommitteeVO> cadreCommitteeList,List<Long> tdpCadreIdsList){
		List<Object[]> tdpCommitteeMemberList = tdpCommitteeElectrolsDAO.getTdpCommitteeElectrolsForTdpCadreIdList(tdpCadreIdsList,IConstants.CURRENT_ENROLLMENT_ID);
		
		if(tdpCommitteeMemberList != null && tdpCommitteeMemberList.size()>0)
		{
			for (Object[] tdpCadre : tdpCommitteeMemberList) 
			{
				Long id = tdpCadre[0] != null ? Long.valueOf(tdpCadre[0].toString()):0L;
				String committeeName = tdpCadre[1] != null ? tdpCadre[1].toString():"";
				String positionName =  tdpCadre[2] != null ? tdpCadre[2].toString():"";
				Long LocationTypeId = tdpCadre[3] != null ? Long.valueOf(tdpCadre[3].toString()):0L;
				Long locationValue = tdpCadre[4] != null ? Long.valueOf(tdpCadre[4].toString()):0L;
				
				CadreCommitteeVO cadreVO = getMatchedVOById(cadreCommitteeList,id);
				if(cadreVO != null)
				{
					CadreCommitteeVO vo = new CadreCommitteeVO();
					String location = null;
					if(locationValue.longValue() > 0L){
						location = getLocationName(LocationTypeId,locationValue);
						vo.setCommitteeLocation(location);
						vo.setCommitteePosition(positionName);
						vo.setCommitteeName(committeeName);
						cadreVO.getPreviousRoles().add(vo);
				    }
			   }
		    }
			
		}

	}
	public String getLocationName(Long LocationTypeId,Long locationValue){
		String location ="";
		if(LocationTypeId.longValue() == 6L){
			location = panchayatDAO.get(locationValue).getPanchayatName()+" Panchayat";
		}
		else if(LocationTypeId.longValue() == 8L){
			location = constituencyDAO.get(locationValue).getName();
		}
		else if(LocationTypeId.longValue() == 5L){
			location = tehsilDAO.get(locationValue).getTehsilName()+" Mandal";
		}else if(LocationTypeId.longValue() == 7L){
			LocalElectionBody localElectionBody = localElectionBodyDAO.get(locationValue);						
			location = localElectionBody.getName() +" "+localElectionBody.getElectionType().getElectionType();
			if(locationValue.longValue() == 20L || locationValue.longValue() == 124L || locationValue.longValue() == 119L){
				String wardName = constituencyDAO.get(locationValue).getName();
				location = location+" - "+wardName;
			}
		}else if(LocationTypeId.longValue() == 9L){
			Constituency constituency = constituencyDAO.get(locationValue);
			LocalElectionBody localElectionBody =  constituency.getLocalElectionBody();						
			location = localElectionBody.getName() +" "+localElectionBody.getElectionType().getElectionType();
			String wardName =constituency.getName();
			List name = localElectionBodyWardDAO.findWardName(locationValue);
			if(name != null && name.size() > 0 && name.get(0) != null){
				location = location+" - "+wardName+"("+name.get(0).toString()+")";
			}else{
			    location = location+" - "+wardName;
			}
		}else if(LocationTypeId.longValue() == 10L){
			if(locationValue.longValue() == 1){
				location = "AndhraPradesh State";
			}else{
				location = "Telangana State";
			}
		}else if(LocationTypeId.longValue() == 11L){
			location = districtDAO.get(locationValue).getDistrictName()+" District";
		}
		return location;
	}
	public CadreCommitteeVO getMatchedVOById(List<CadreCommitteeVO> tdpCadreVOList,Long id)
	{
		CadreCommitteeVO returnVO = null;
		try {
			
			if(tdpCadreVOList != null && tdpCadreVOList.size()>0)
			{
				for (CadreCommitteeVO tdpCadre : tdpCadreVOList)
				{
					if(tdpCadre.getTdpCadreId().longValue() == id.longValue())
					{
						return tdpCadre;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getMatchedVOById", e);
		}
		return returnVO;
	}
	
	public List<SelectOptionVO> getBasicCadreCommitteesDetails()
	{
		List<SelectOptionVO> committeesList = null;
		try {
			List<TdpBasicCommittee> tdpbasicCommitteDetls = tdpBasicCommitteeDAO.getAll();
			if(tdpbasicCommitteDetls != null && tdpbasicCommitteDetls.size()>0)
			{
				committeesList = new ArrayList<SelectOptionVO>();
				for (TdpBasicCommittee tdpBasicCommittee : tdpbasicCommitteDetls) 
				{
					if(tdpBasicCommittee.getTdpBasicCommitteeId() != 1)
					{
						SelectOptionVO vo = new SelectOptionVO();
						vo.setId(tdpBasicCommittee.getTdpBasicCommitteeId());
						vo.setName(tdpBasicCommittee.getName());
						
						committeesList.add(vo);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getBasicCadreCommitteesDetails", e);
		}
		return committeesList;
	}
	
	/*public List<CadreCommitteeMemberVO> getCommitteeDetailsByStatus(Long basicCommitteeTypeId,String status,Long levelId)
	{
		List<CadreCommitteeMemberVO> resultList = new ArrayList<CadreCommitteeMemberVO>();
		List<Long> levelIds = new ArrayList<Long>();
		List<CadreCommitteeMemberVO> toRemove = new ArrayList<CadreCommitteeMemberVO>();
		try{
			if(levelId == 1) // MANDAL / TOWN / DIVISION
			{
				levelIds.add(5l);
				levelIds.add(7l);
				levelIds.add(9l);
			}
			if(levelId == 2) // Village/Ward
			{
				levelIds.add(6l);
				levelIds.add(8l);
			}
			List<Object[]> list = tdpCommitteeDAO.getLocationByTypeIdAndLevel(levelIds,basicCommitteeTypeId);
			if(list != null && list.size() > 0)
			{
				List<Long> locationValues = new ArrayList<Long>();
				for(Object[] params : list)
				{
					CadreCommitteeMemberVO locationVo = new CadreCommitteeMemberVO();
					String locationName = getLocationName((Long)params[2],(Long)params[0]);
					locationVo.setId((Long)params[0]);
					locationVo.setName(locationName);
					locationVo.setStatus(params[1].toString());
					locationVo.setLevel((Long)params[2]);
					locationValues.add((Long)params[0]);
					resultList.add(locationVo);
				}
				
				List<Object[]> membersList = tdpCommitteeMemberDAO.getComitteeMembersByCommiteTypeAndLocation(levelIds,locationValues,basicCommitteeTypeId);
				if(membersList != null && membersList.size() > 0)
				{
					for(Object[] params : membersList)
					{
						CadreCommitteeMemberVO vo = getMatchedLocation((Long)params[1],(Long)params[2],resultList);
						if(vo != null)
						{
							 for Not Started 
								if(status.equalsIgnoreCase("NotStarted"))
									{
									toRemove.add(vo);
									}
									else
									{
											   vo.setTotal((Long)params[0]);
											   if(vo.getStatus().equalsIgnoreCase("Y"))
												   vo.setStatus("Completed");
											   else
											   {
												   if(vo.getTotal() != null && vo.getTotal() > 0)
													   vo.setStatus("Started"); 
												   else
													   vo.setStatus("NotStarted");  
													   
											   }
									}
								}		
							   }
							}
				         }
			
			if(!status.equalsIgnoreCase("Total"))
			{
				 for Started 
				if(status.equalsIgnoreCase("Started") || status.equalsIgnoreCase("Completed"))
				{
					for(CadreCommitteeMemberVO vo : resultList)
					{
						if(!status.equalsIgnoreCase(vo.getStatus()))
						  toRemove.add(vo);
					}
				}
				
				resultList.removeAll(toRemove);
			}
			if(resultList != null && resultList.size() > 0)
			resultList.get(0).setCommitte(tdpBasicCommitteeDAO.get(basicCommitteeTypeId).getName());
		}
		catch(Exception e)
		{
			LOG.error("Exception raised in getCommitteeDetailsByStatus", e);	
		}
		
		return resultList;
		
	}*/
	
	public CadreCommitteeReportVO getCommitteeDetailsByLocation(String state,List<Long> levelIds,String startDateString,String endDateString,Long userId,String accessType,Long accessValue,List<Long> enrollmentIdsList){
		
		Long completedMainCommittees=0l;
		Long completedAfflCommittees=0l;
		
		CadreCommitteeReportVO cadreCommitteeReportVO =new CadreCommitteeReportVO();
		try{ 
			List<Long> districtIds = new ArrayList<Long>();
			List<Long> assemblyIds = new ArrayList<Long>();
			List<Long> locationLevelValues = new ArrayList<Long>();
			
			cadreCommitteeReportVO.setAccessState("ALL");
			if(accessType.trim().equalsIgnoreCase("MP"))
			{
				cadreCommitteeReportVO.setAccessState(delimitationConstituencyAssemblyDetailsDAO.get(accessValue).getConstituency().getName()+" "+"Parliament");
				districtIds = null;
				List<Object[]> accessConstituencyList = userConstituencyAccessInfoDAO.findByElectionScopeUser(1L,userId);
				
				List<Long> parliamentsIds = new ArrayList<Long>();
				if(accessConstituencyList != null && accessConstituencyList.size()>0)
				{
					for (Object[] parliament : accessConstituencyList) {
						parliamentsIds.add(parliament[0] != null ? Long.valueOf(parliament[0].toString().trim()):0L);
					}
				}
				else
				{
					parliamentsIds.add(accessValue);
				}
				
				if(parliamentsIds != null && parliamentsIds.size()>0)
				{
					assemblyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliamentList(parliamentsIds);
				}
				
				if(assemblyIds!=null && assemblyIds.size()>0){
					List<Object[]> distList = constituencyDAO.getDistictWiseConstituencyListByConstiIds(assemblyIds);
					if(distList!=null && distList.size()>0){
						Long dstrct = Long.valueOf(distList.get(0)[2].toString());
						if(dstrct>10){
							state = "AP";
						}else{
							state = "TS";
						}
					}
				}
			
				/*if(levelIds.contains(5L)) // mandal
				{
					List<Object[]> tehsilDetails = tehsilDAO.getTehsilsByConstituencyIdsListAndPublicationDateId(assemblyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
					if(tehsilDetails != null && tehsilDetails.size() > 0)
					{
						for (Object[] tehsil : tehsilDetails) {
							locationLevelValues.add(tehsil[0] != null ? Long.valueOf(tehsil[0].toString().trim()):0L);						
						}
					}
				}
				if(levelIds.contains(7L)) //localelectionbody 
				{
					List<Object[]> tehsilDetails = tehsilDAO.getAllLocalElecBodyListByConstituencyIdsListAndPublicationDateId(assemblyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
					if(tehsilDetails != null && tehsilDetails.size() > 0)
					{
						for (Object[] tehsil : tehsilDetails) {
							locationLevelValues.add(tehsil[0] != null ? Long.valueOf(tehsil[0].toString().trim()):0L);						
						}
					}
				}
				if(levelIds.contains(9L)) //DIVISION 
				{
				}*/
			}
			else
			{
				List<Object[]> accessDistrictsList = userDistrictAccessInfoDAO.findByUser(userId);
				if(accessDistrictsList != null && accessDistrictsList.size()>0)
				{
					for (Object[] districtId : accessDistrictsList) {
						districtIds.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
					}
					Long stateTypeId = 0L;
					
					if(districtIds != null && districtIds.size() == 1)
					{
						Long districtId = districtIds.get(0).longValue();
						if(districtId != 0L)
							cadreCommitteeReportVO.setAccessState(districtDAO.get(districtId).getDistrictName()+" District");
					}
					else if(districtIds != null && districtIds.contains(11L))
					{
						cadreCommitteeReportVO.setAccessState("AP");
						stateTypeId =1L;
					}
					else if(districtIds != null && districtIds.contains(1L))
					{
						cadreCommitteeReportVO.setAccessState("TG");
						stateTypeId =36L;
					}
					
					List<Object[]> newDistrictsList = districtDAO.getNewDistrictForState(stateTypeId);
					
					if(districtIds != null && districtIds.size()>1 && newDistrictsList!=null && newDistrictsList.size()>0){
						for(Object[] obj:newDistrictsList){
							districtIds.add(Long.valueOf(obj[0].toString()));
						}
					}
				}
			}
			
			
			Date startDate=null;
			Date endDate=null;
			String globalState ="AP";
			if(startDateString !=null && endDateString !=null){
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				 startDate = sdf.parse(startDateString);
				 endDate=sdf.parse(endDateString);
			}
			
			
			if((!accessType.trim().equalsIgnoreCase("MP") || levelIds.contains(6l) || levelIds.contains(8l) ) || levelIds.contains(11l) || levelIds.contains(10l)){
				Long committeeCnt =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,levelIds,districtIds,assemblyIds,locationLevelValues,enrollmentIdsList,null,null);
				//0count ,1 isCommitteeConfirmed,2.tdpCommitteeLevelId,3.tdpBasicCommitteeId
				List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state,levelIds,startDate,endDate,districtIds,assemblyIds,locationLevelValues,enrollmentIdsList);
				
				if(committeeCntDtls !=null && committeeCntDtls.size() > 0){				
					for (Object[] objects : committeeCntDtls) {
						globalState = 	commonMethodsUtilService.getStringValueForObject(objects[3]);		
						if(Long.valueOf(objects[1].toString())==1l)
							completedMainCommittees = completedMainCommittees+(Long)objects[0];
						else if(Long.valueOf(objects[1].toString()) == 2l)
							completedAfflCommittees=completedAfflCommittees+(Long)objects[0];	
					}
				}
					 
				cadreCommitteeReportVO.setMainCommittees(0l);
				cadreCommitteeReportVO.setAfflCommittees(0l);
				 List<Object[]> startedCount=tdpCommitteeMemberDAO.getStartedCommitteesCountByLocation(state, levelIds,startDate,endDate,districtIds,assemblyIds,locationLevelValues,enrollmentIdsList);
						if(startedCount != null && startedCount.size() > 0){
							for (Object[] objects : startedCount) {
								if(Long.valueOf(objects[1].toString())==1l){
									cadreCommitteeReportVO.setMainCommittees(Long.valueOf(objects[0].toString()));//startedCount in Main type
								}
								else if(Long.valueOf(objects[1].toString()) == 2l){
									cadreCommitteeReportVO.setAfflCommittees(Long.valueOf(objects[0].toString()));//startedCount in Affliated Type
								}
							}
						}
						
					
			  Long memberscount= tdpCommitteeMemberDAO.getMembersCountByLocation(state, levelIds,startDate,endDate,districtIds,assemblyIds,locationLevelValues,enrollmentIdsList);				
					
			  cadreCommitteeReportVO.setMembersCount(memberscount != null ? memberscount : 0l);//totalMembers				
					
			  cadreCommitteeReportVO.setCommitteesCount(committeeCnt);//Total Committes count.
			 // if(Arrays.asList(IConstants.COMMITTEE_ALL_DATA_ACCESS_USER_IDS).contains(userId.longValue()) && (levelIds.contains(6l) || levelIds.contains(8l)))
			  if((userId.longValue() == 1L || userId.longValue() == 7446L ) && (levelIds.contains(6l) || levelIds.contains(8l)))
			  {
				   CadreIVRVO committeeSummaryVO = new CadreIVRVO();
				   if(enrollmentIdsList != null && enrollmentIdsList.contains(1L))
					   getCadreCommitteIVRDerails(districtIds,committeeSummaryVO,state,levelIds);
				   cadreCommitteeReportVO.setCommitteeSummaryVO(committeeSummaryVO);
			  }
			   
		}else if(accessType.trim().equalsIgnoreCase("MP") && (levelIds.contains(5l)|| levelIds.contains(7l) || levelIds.contains(9l))){
		
		  Long committeesCount = 0l;
		  Long memsCount = 0l;
		  Long mainStrtdCount = 0l;
		  Long afflStrtdCount = 0l;
		  
		  Map<String,List<Long>> lctnsMap = getLocalBodiesDivisionsMandalByContituencyIds(assemblyIds,levelIds);
		  for (Entry<String, List<Long>> entry : lctnsMap.entrySet()) {
			  //System.out.println(entry.getKey() + "/" + entry.getValue());
			  if(entry.getKey().equalsIgnoreCase("Tehsils")){
				  List<Long> lctnsIds = entry.getValue();
				  if(lctnsIds!=null && lctnsIds.size()>0){
					  List<Long> lvlIds = new ArrayList<Long>();
					  lvlIds.add(5l);
					  Long cmtCnt =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,lvlIds,districtIds,null,lctnsIds,enrollmentIdsList,null,null);
					  committeesCount = committeesCount + cmtCnt;
					  
					  Long memberscount= tdpCommitteeMemberDAO.getMembersCountByLocation(state, lvlIds,startDate,endDate,districtIds,null,lctnsIds,enrollmentIdsList);
					  memsCount = memsCount + memberscount;
					  
					  List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state,lvlIds,startDate,endDate,districtIds,null,lctnsIds,enrollmentIdsList);
						
						if(committeeCntDtls !=null && committeeCntDtls.size() > 0){				
							for (Object[] objects : committeeCntDtls) {
												
								if(Long.valueOf(objects[1].toString())==1l)
									completedMainCommittees = completedMainCommittees+(Long)objects[0];
								else if(Long.valueOf(objects[1].toString()) == 2l)
									completedAfflCommittees=completedAfflCommittees+(Long)objects[0];	
							}
						}
						
						List<Object[]> startedCount=tdpCommitteeMemberDAO.getStartedCommitteesCountByLocation(state, lvlIds,startDate,endDate,districtIds,null,lctnsIds,enrollmentIdsList);
						if(startedCount != null && startedCount.size() > 0){
							for (Object[] objects : startedCount) {
								if(Long.valueOf(objects[1].toString())==1l){
									mainStrtdCount = mainStrtdCount + Long.valueOf(objects[0].toString());//startedCount in Main type
								}
								else if(Long.valueOf(objects[1].toString()) == 2l){
									afflStrtdCount =  afflStrtdCount + Long.valueOf(objects[0].toString());//startedCount in Affliated Type
								}
							}
						}
				  }
				  
				  
				  
			  }
			  if(entry.getKey().equalsIgnoreCase("LocalBodies")){
				  List<Long> lctnsIds = entry.getValue();
				  if(lctnsIds!=null && lctnsIds.size()>0){
					  List<Long> lvlIds = new ArrayList<Long>();
					  lvlIds.add(7l);
					  Long cmtCnt =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,lvlIds,districtIds,null,lctnsIds,enrollmentIdsList,null,null);
					  committeesCount = committeesCount + cmtCnt;
					  
					  Long memberscount= tdpCommitteeMemberDAO.getMembersCountByLocation(state, lvlIds,startDate,endDate,districtIds,null,lctnsIds,enrollmentIdsList);
					  memsCount = memsCount + memberscount;
					  
					  List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state,lvlIds,startDate,endDate,districtIds,null,lctnsIds,enrollmentIdsList);
						
						if(committeeCntDtls !=null && committeeCntDtls.size() > 0){				
							for (Object[] objects : committeeCntDtls) {
												
								if(Long.valueOf(objects[1].toString())==1l)
									completedMainCommittees = completedMainCommittees+(Long)objects[0];
								else if(Long.valueOf(objects[1].toString()) == 2l)
									completedAfflCommittees=completedAfflCommittees+(Long)objects[0];	
							}
						}
						
						List<Object[]> startedCount=tdpCommitteeMemberDAO.getStartedCommitteesCountByLocation(state, lvlIds,startDate,endDate,districtIds,null,lctnsIds,enrollmentIdsList);
						if(startedCount != null && startedCount.size() > 0){
							for (Object[] objects : startedCount) {
								if(Long.valueOf(objects[1].toString())==1l){
									mainStrtdCount = mainStrtdCount + Long.valueOf(objects[0].toString());//startedCount in Main type
								}
								else if(Long.valueOf(objects[1].toString()) == 2l){
									afflStrtdCount =  afflStrtdCount + Long.valueOf(objects[0].toString());//startedCount in Affliated Type
								}
							}
						}
				  }
			  }
			  if(entry.getKey().equalsIgnoreCase("DivisionWards")){
				  List<Long> lctnsIds = entry.getValue();
				  if(lctnsIds!=null && lctnsIds.size()>0){
					  List<Long> lvlIds = new ArrayList<Long>();
					  lvlIds.add(9l);
					  Long cmtCnt =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,lvlIds,districtIds,null,lctnsIds,enrollmentIdsList,null,null);
					  committeesCount = committeesCount + cmtCnt;
					  
					  Long memberscount= tdpCommitteeMemberDAO.getMembersCountByLocation(state, lvlIds,startDate,endDate,districtIds,null,lctnsIds,enrollmentIdsList);
					  memsCount = memsCount + memberscount;
					  
					  List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state,lvlIds,startDate,endDate,districtIds,null,lctnsIds,enrollmentIdsList);
						
						if(committeeCntDtls !=null && committeeCntDtls.size() > 0){				
							for (Object[] objects : committeeCntDtls) {
												
								if(Long.valueOf(objects[1].toString())==1l)
									completedMainCommittees = completedMainCommittees+(Long)objects[0];
								else if(Long.valueOf(objects[1].toString()) == 2l)
									completedAfflCommittees=completedAfflCommittees+(Long)objects[0];	
							}
						}
					  
						List<Object[]> startedCount=tdpCommitteeMemberDAO.getStartedCommitteesCountByLocation(state, lvlIds,startDate,endDate,districtIds,null,lctnsIds,enrollmentIdsList);
						if(startedCount != null && startedCount.size() > 0){
							for (Object[] objects : startedCount) {
								if(Long.valueOf(objects[1].toString())==1l){
									mainStrtdCount = mainStrtdCount + Long.valueOf(objects[0].toString());//startedCount in Main type
								}
								else if(Long.valueOf(objects[1].toString()) == 2l){
									afflStrtdCount =  afflStrtdCount + Long.valueOf(objects[0].toString());//startedCount in Affliated Type
								}
							}
						}
				  }
			  }
		  }
		  
		 
		  	cadreCommitteeReportVO.setMainCommittees(mainStrtdCount);
			cadreCommitteeReportVO.setAfflCommittees(afflStrtdCount);
			cadreCommitteeReportVO.setMembersCount(memsCount != null ? memsCount : 0l);//totalMembers				
			cadreCommitteeReportVO.setCommitteesCount(committeesCount);//Total Committes count.
		}
				
				cadreCommitteeReportVO.setCompletedCommittees(completedMainCommittees);//completedCount for Main
				cadreCommitteeReportVO.setAffliatedCompleted(completedAfflCommittees);//completedCount for Affliated
				
				if(cadreCommitteeReportVO.getCommitteesCount()  > 0){				
					cadreCommitteeReportVO.setStartedCommitteePerc(new BigDecimal(cadreCommitteeReportVO.getMainCommittees() * 100.0/cadreCommitteeReportVO.getCommitteesCount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					cadreCommitteeReportVO.setCompletedCommitteePerc(new BigDecimal(cadreCommitteeReportVO.getCompletedCommittees() * 100.0/cadreCommitteeReportVO.getCommitteesCount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					//cadreCommitteeReportVO.setAffliatedCompletedPerc(new BigDecimal(cadreCommitteeReportVO.getAffliatedCompleted() * 100.0/cadreCommitteeReportVO.getCommitteesCount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					//cadreCommitteeReportVO.setAfflCommitteesPerc(new BigDecimal(cadreCommitteeReportVO.getAfflCommittees() * 100.0/cadreCommitteeReportVO.getCommitteesCount()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				}
				else{
					cadreCommitteeReportVO.setStartedCommitteePerc(0.0);
					cadreCommitteeReportVO.setCompletedCommitteePerc(0.0);
					cadreCommitteeReportVO.setAffliatedCompletedPerc(0.0);//percentage for completed Affliated
				}
				
				
			cadreCommitteeReportVO.setState(globalState);
			return cadreCommitteeReportVO;
		}
		catch(Exception e){
			LOG.error("Exception raised in getCommitteeDetailsByLocation method",e);
		}
		return cadreCommitteeReportVO;
	}

	public void getCadreCommitteIVRDerails(List<Long> districtIds,CadreIVRVO resultVO,String state,List<Long> levelIds)
	{
		try {
			if(state != null && state.equalsIgnoreCase("AP"))
			{
				districtIds.add(11l);districtIds.add(12l);districtIds.add(13l);districtIds.add(14l);districtIds.add(15l);districtIds.add(16l);districtIds.add(17l);districtIds.add(18l);districtIds.add(19l);districtIds.add(20l);districtIds.add(21l);districtIds.add(22l);districtIds.add(23l);
			}
			else
			{
				districtIds.add(1l);districtIds.add(2l);districtIds.add(3l);districtIds.add(4l);districtIds.add(5l);districtIds.add(6l);districtIds.add(7l);districtIds.add(8l);districtIds.add(9l);districtIds.add(10l);
			}
			
			List<Object[]> values = committeIvrTotalDetailDAO.getStateWiseIvrDetails(state);
	    	if(values != null && values.size() > 0)
	    	{
	    		Object[] data = values.get(0);
	    		if(levelIds.size() > 1)
	    		{
	    			resultVO.setCount(data[0] != null ? Long.valueOf(data[0].toString().trim()) : 0l);
			    	resultVO.setTotalIvrCalls(data[1] != null ? Long.valueOf(data[1].toString().trim()) : 0l);
			    	resultVO.setAnsweredIvrCalls(data[2] != null ? Long.valueOf(data[2].toString().trim()) : 0l);
			    	resultVO.setTotalWards(data[3] != null ? Long.valueOf(data[3].toString().trim()) : 0l);
			    	resultVO.setTotalWardIvr(data[4] != null ? Long.valueOf(data[4].toString().trim()) : 0l);
			    	resultVO.setTotalWardAnswerdIvr(data[5] != null ? Long.valueOf(data[5].toString().trim()) : 0l);
	    		}
	    		else
	    		{
	    			if(levelIds.get(0) == 6)
		    		{
		    			resultVO.setCount(data[0] != null ? Long.valueOf(data[0].toString().trim()) : 0l);
				    	resultVO.setTotalIvrCalls(data[1] != null ? Long.valueOf(data[1].toString().trim()) : 0l);
				    	resultVO.setAnsweredIvrCalls(data[2] != null ? Long.valueOf(data[2].toString().trim()) : 0l);
		    		}
		    		else if(levelIds.get(0) == 8)
		    		{
		    			resultVO.setTotalWards(data[3] != null ? Long.valueOf(data[3].toString().trim()) : 0l);
				    	resultVO.setTotalWardIvr(data[4] != null ? Long.valueOf(data[4].toString().trim()) : 0l);
				    	resultVO.setTotalWardAnswerdIvr(data[5] != null ? Long.valueOf(data[5].toString().trim()) : 0l);
		    		}
	    		}
	    		
	    		
	    	}
	    	
	    	
			
			

		    if(levelIds.size() > 1)
    		{
		    	List<Object[]> optionsList = ivrCampaignOptionsDAO.getAllIVROptions(2l);
	    		Map<Long,String> optionNames = new HashMap<Long,String>();
	    		List<IvrOptionsVO> options = new ArrayList<IvrOptionsVO>();
	    		getOptionSbyCampain(optionsList,optionNames,options);
	    		resultVO.setOptionsList(options);
	    		List<Object[]> cadreIvrDetails  = cadreIvrResponseDAO.getStateWiseCommitterIvrDetails(districtIds,2l);
	    		if(cadreIvrDetails != null && cadreIvrDetails.size() > 0)
	    		{
	    			fillCadreIvrDetails(cadreIvrDetails,resultVO,1l);
		    		resultVO.setOptionsList(options);
	    		}
	    		
	    		
	    		
	    		List<Object[]> optionsList1 = ivrCampaignOptionsDAO.getAllIVROptions(3l);
	    		Map<Long,String> optionNames1 = new HashMap<Long,String>();
	    		List<IvrOptionsVO> options1 = new ArrayList<IvrOptionsVO>();
	    		getOptionSbyCampain(optionsList1,optionNames1,options1);
	    		resultVO.setOptionsList1(options1);
	    		List<Object[]> cadreIvrDetails1 = cadreIvrResponseDAO.getStateWiseCommitterIvrDetails(districtIds,3l);
	    		if(cadreIvrDetails1 != null && cadreIvrDetails1.size() > 0)
	    		{
	    			fillCadreIvrDetails(cadreIvrDetails1,resultVO,2l);
	    			resultVO.setOptionsList1(resultVO.getOptionsList1());
	    		}
	    		
	    		
    		}
		    else
		    {
		    	if(levelIds.get(0) == 6)
	    		{
		    		List<Object[]> optionsList = ivrCampaignOptionsDAO.getAllIVROptions(2l);
		    		Map<Long,String> optionNames = new HashMap<Long,String>();
		    		List<IvrOptionsVO> options = new ArrayList<IvrOptionsVO>();
		    		getOptionSbyCampain(optionsList,optionNames,options);
		    		resultVO.setOptionsList(options);
		    		List<Object[]> cadreIvrDetails  =  cadreIvrResponseDAO.getStateWiseCommitterIvrDetails(districtIds,2l);
		    		if(cadreIvrDetails != null && cadreIvrDetails.size() > 0)
		    		{
		    			fillCadreIvrDetails(cadreIvrDetails,resultVO,1l);
			    		resultVO.setOptionsList(options);
		    		}
		    		
	    		}
		    	else
		    	{
		    		List<Object[]> optionsList = ivrCampaignOptionsDAO.getAllIVROptions(3l);
		    		Map<Long,String> optionNames = new HashMap<Long,String>();
		    		List<IvrOptionsVO> options = new ArrayList<IvrOptionsVO>();
		    		getOptionSbyCampain(optionsList,optionNames,options);
		    		resultVO.setOptionsList1(options);
		    		List<Object[]> cadreIvrDetails  =  cadreIvrResponseDAO.getStateWiseCommitterIvrDetails(districtIds,3l);
		    		if(cadreIvrDetails != null && cadreIvrDetails.size() > 0)
		    		{
		    			fillCadreIvrDetails(cadreIvrDetails,resultVO,2l);
		    			resultVO.setOptionsList1(resultVO.getOptionsList1());
		    		}
		    		
		    		
		    	}
		    }
			
			
		} catch (Exception e) {
			LOG.error("Exception raised in getCadreCommitteIVRDerails method",e);
		}
		
	}
	 
	public void getOptionSbyCampain(List<Object[]> optionsList,Map<Long,String> optionNames,List<IvrOptionsVO> options)
	{
		if(optionsList != null && optionsList.size() >0){
			for(Object[] option:optionsList){
				optionNames.put((Long)option[0], option[1].toString());
			}
		}
	    if(optionNames != null && optionNames.size() > 0)
	    {
	    	for (Long optId : optionNames.keySet())
	    	{
	    		IvrOptionsVO optVO = new IvrOptionsVO();
	    		optVO.setId(optId);
	    		optVO.setName(optionNames.get(optId));
	    		optVO.setCount(0l);
	    		optVO.setPerc("0.0");
	    		options.add(optVO);
			}
	    }
	}
	public void fillCadreIvrDetails( List<Object[]> cadreIvrDetails,CadreIVRVO resultVO,Long id)
	{

			Map<Long,IvrOptionsVO> optionsMap = new HashMap<Long, IvrOptionsVO>();
			for (Object[] ivrCountInfo : cadreIvrDetails)
			{
				if(ivrCountInfo[0].toString().equalsIgnoreCase(IConstants.NORMAL_CLEARING))
				{
					if(ivrCountInfo[1] != null)
					{
						List<IvrOptionsVO> mainOptionsList = null;
						if(id == 1)
						{
							mainOptionsList = resultVO.getOptionsList();
						}
						else
						{
							mainOptionsList = resultVO.getOptionsList1();
						}
						
						if(mainOptionsList != null && mainOptionsList.size() > 0)
						{
							for (IvrOptionsVO ivrOptionsVO : mainOptionsList)
							{
								if(ivrOptionsVO.getId().longValue() == Long.valueOf(ivrCountInfo[1].toString().trim()))
								{
									ivrOptionsVO.setCount(Long.valueOf(ivrCountInfo[2].toString().trim()));//count
									resultVO.setTotal(ivrOptionsVO.getCount() + resultVO.getTotal());
								}
							}
						}
					}
						

				}
				else
				{
					if(ivrCountInfo[0].toString().equalsIgnoreCase(IConstants.USER_BUSY))
					{
						resultVO.setUserBusy(resultVO.getUserBusy() + Long.valueOf(ivrCountInfo[2].toString().trim()));
					}
					else if(ivrCountInfo[0].toString().equalsIgnoreCase(IConstants.CALL_REJECTED))
					{
						resultVO.setCallRejectedCount(resultVO.getCallRejectedCount() + Long.valueOf(ivrCountInfo[2].toString().trim()));
					}
						
					else if(ivrCountInfo[0].toString().equalsIgnoreCase(IConstants.NO_ANSWER) || ivrCountInfo[0].toString().equalsIgnoreCase(IConstants.NO_USER_RESPONSE))
					{
						 resultVO.setNoAnswer(resultVO.getNoAnswer() + Long.valueOf(ivrCountInfo[2].toString().trim()));
					}
						
					else if(ivrCountInfo[0].toString().equalsIgnoreCase(IConstants.SWITCH_CONGESTION) || ivrCountInfo[0].toString().equalsIgnoreCase(IConstants.NORMAL_CIRCUIT_CONGESTION))
					{
						resultVO.setSwitchCongestion(resultVO.getSwitchCongestion() + Long.valueOf(ivrCountInfo[2].toString().trim()));
					}
						
					else if(ivrCountInfo[0].toString().equalsIgnoreCase(IConstants.NORMAL_TEMPORARY_FAILURE) || ivrCountInfo[0].toString().equalsIgnoreCase(IConstants.DESTINATION_OUT_OF_ORDER) 
							 || ivrCountInfo[0].toString().equalsIgnoreCase(IConstants.NETWORK_OUT_OF_ORDER) || ivrCountInfo[0].toString().equalsIgnoreCase(IConstants.SUBSCRIBER_ABSENT))
					{
						resultVO.setNewtworkError(resultVO.getNewtworkError() + Long.valueOf(ivrCountInfo[2].toString().trim()));
					}
						
					else if(ivrCountInfo[0].toString().equalsIgnoreCase(IConstants.UNALLOCATED_NUMBER))
					{
						resultVO.setUnallocatedNumbers(resultVO.getUnallocatedNumbers() + Long.valueOf(ivrCountInfo[2].toString().trim()));
					}
							
					else if(ivrCountInfo[0].toString().equalsIgnoreCase(IConstants.INTERWORKING))
					{
						resultVO.setInterworkingCount(resultVO.getInterworkingCount() + Long.valueOf(ivrCountInfo[2].toString().trim()));
					}
						
					else 
					{
						resultVO.setOtherError(resultVO.getOtherError() + Long.valueOf(ivrCountInfo[2].toString().trim()));
					}
				}
			}
			
		
	}
	
	
	//--cadreCommitteeRequest-
			public LocationWiseBoothDetailsVO getMainCommitteeMembersInfoRequest(Long levelId,Long levelValue){
				Long committeeId = getMainCommitteeIdInALocationRequest(levelId,levelValue);
				if(committeeId != null){
					return getCommitteeMembersInfoRequest(committeeId);
				}else{
					return new LocationWiseBoothDetailsVO();
				}
			}
			public Long getMainCommitteeIdInALocationRequest(Long levelId,Long levelValue){
				Long committeeId = null;
				try{
					
					List<Long>  committeeEnrollmentIds = new ArrayList<Long>();
					committeeEnrollmentIds.add(IConstants.CURRENT_ENROLLMENT_ID);
					List<Long> committeeIds = tdpCommitteeDAO.getMainCommittiesInALocation(levelId, levelValue,committeeEnrollmentIds,null,null);
					if(committeeIds.size() > 0){
						committeeId = committeeIds.get(0);
					}
				}catch(Exception e){
					LOG.error("Exception raised in getMainCommitteeIdInALocationRequest", e);
				}
				return committeeId;
			}
			public LocationWiseBoothDetailsVO getCommitteeMembersInfoRequest(Long committeeId){
				LocationWiseBoothDetailsVO returnVo=null;
			try{
				 String confirmedCommittee=tdpCommitteeDAO.gettingConfirmedCommittee(committeeId);
				
					returnVo = new LocationWiseBoothDetailsVO();
					returnVo.setElectionYear(confirmedCommittee);
					List<LocationWiseBoothDetailsVO> committeeMembersInfoList = new ArrayList<LocationWiseBoothDetailsVO>();
					List<SelectOptionVO> committeeMembersList = new ArrayList<SelectOptionVO>();
					
					returnVo.setResult(committeeMembersInfoList);
					returnVo.setHamletsOfTownship(committeeMembersList);
					
						Map<Long,LocationWiseBoothDetailsVO> committeeMembersMap = new HashMap<Long,LocationWiseBoothDetailsVO>();
						LocationWiseBoothDetailsVO vo = null;
						SelectOptionVO memberVo = null;
						//0committeeRoleid,1role name,2max nos
						List<Object[]> totalCommitteRolesList = tdpCommitteeRoleDAO.getAllCommitteeRoles(committeeId);
						for(Object[] totalCommitteRole:totalCommitteRolesList){
						         vo = new LocationWiseBoothDetailsVO();
								 vo.setLocationName(totalCommitteRole[1].toString());
								 vo.setLocationId((Long)totalCommitteRole[0]);
								 vo.setPopulation((Long)totalCommitteRole[2]);//total positions
								 vo.setTotal((Long)totalCommitteRole[2]);//total positions left
								 vo.setVotesPolled(0l);//total positions filled
								 committeeMembersMap.put((Long)totalCommitteRole[0], vo);
								 committeeMembersInfoList.add(vo);
							   
						 }
						if(committeeMembersMap.size() > 0){
							//0 count,1 id
							List<Object[]>  electedPersonsList = tdpCommitteeMemberDAO.getRoleWiseAllocatedMembersCount(committeeMembersMap.keySet());
							for(Object[] electedPersons:electedPersonsList){
								LocationWiseBoothDetailsVO roleInfo = committeeMembersMap.get((Long)electedPersons[1]);
								roleInfo.setVotesPolled((Long)electedPersons[0]);
								roleInfo.setTotal(roleInfo.getPopulation() - (Long)electedPersons[0]);
							}
							
							//0 role(designation),1 image,2name,3membership,4 cadreId(commiteememeberid),5 designation ids(role id).
							List<Object[]>  electedMembersInfoList = tdpCommitteeMemberDAO.getMembersInfoForRequest(committeeMembersMap.keySet());
							
							for(Object[] electedMembersInfo:electedMembersInfoList){
								memberVo = new SelectOptionVO();
								memberVo.setValue(electedMembersInfo[0].toString());//role
								memberVo.setId((Long)electedMembersInfo[5]);//designation ids(role id)
								
								if(electedMembersInfo[1] != null){
								   memberVo.setUrl(electedMembersInfo[1].toString());//image
								}
								memberVo.setName(electedMembersInfo[2].toString());//name
								memberVo.setMainAccountId((Long)electedMembersInfo[4]);//tdpCadreId
								memberVo.setOrderId((Long)electedMembersInfo[6]);//commiteememeberid
								if(electedMembersInfo[3].toString().trim().length() > 8){
									memberVo.setType(electedMembersInfo[3].toString().trim().substring(electedMembersInfo[3].toString().trim().length()-8));//membership
								}else{
									memberVo.setType(electedMembersInfo[3].toString());//membership
								}
															
								committeeMembersList.add(memberVo);
							}
					  }
				
				}catch(Exception e){
					LOG.error("Exception raised in getCommitteeMembersInfo", e);
				}
				return returnVo;
			}
			boolean flagGlobal=false;
			ResultStatus rs=null;
			public ResultStatus cadreCommitteeIncreasedPositionsOrChangeDesignations(final Long tdpCommitteeRoleId,final Long requestUserId,final Long currentmaxPositions,final Long requestedMaxPositions,final String type,final List<LocationWiseBoothDetailsVO> changeDesignationsList,final Long committeeId )
			{
				
				   ResultStatus resultStatus=new ResultStatus();
				  
				   try {
					
					   if(type.equalsIgnoreCase("positionsIncreased")){
						   transactionTemplate.execute(new TransactionCallbackWithoutResult() 
					       {
							  public void doInTransactionWithoutResult(TransactionStatus status) 
							  {
									CadreCommitteeIncreasedPositions cadreCommitteeIncreasedPositions=new CadreCommitteeIncreasedPositions();
									
									cadreCommitteeIncreasedPositions.setTdpCommitteeRole(tdpCommitteeRoleDAO.get(tdpCommitteeRoleId));
									cadreCommitteeIncreasedPositions.setUserIdRequest(userDAO.get(requestUserId));
									cadreCommitteeIncreasedPositions.setApprovedUser(null);
									cadreCommitteeIncreasedPositions.setCurrentCount(currentmaxPositions);
									cadreCommitteeIncreasedPositions.setRequestCount(requestedMaxPositions);
									cadreCommitteeIncreasedPositions.setStatus("pending");
									cadreCommitteeIncreasedPositions.setApprovedCount(null);
									cadreCommitteeIncreasedPositions.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
									cadreCommitteeIncreasedPositions.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
									cadreCommitteeIncreasedPositions.setType(type); 
									 
									CadreCommitteeIncreasedPositions output = cadreCommitteeIncreasedPositionsDAO.save(cadreCommitteeIncreasedPositions); 
								    Long id = output.getCadreCommitteeIncreasedPositionsId();
								     String refNo="REF#"+id.toString().trim();
									output.setRefNo(refNo);
									cadreCommitteeIncreasedPositionsDAO.save(output);
							  }
					       });
						   resultStatus.setResultCode(1);
					   }
					  
					   else  if(type.equalsIgnoreCase("changeDesignations")){
						   synchronized("CHANGEDESIGNATIONS"){
						   transactionTemplate.execute(new TransactionCallbackWithoutResult() 
					       {
							  public void doInTransactionWithoutResult(TransactionStatus status) 
							  {
								  rs= gettingStatus(committeeId,changeDesignationsList);
								  flagGlobal=rs.getIsResultPartial();
								  if(flagGlobal==false){
								   
								    //inserting parent.
								    CadreCommitteeIncreasedPositions cadreCommitteeIncreasedPositions=new CadreCommitteeIncreasedPositions();
									
									cadreCommitteeIncreasedPositions.setTdpCommitteeRole(null);
									cadreCommitteeIncreasedPositions.setUserIdRequest(userDAO.get(requestUserId));
									cadreCommitteeIncreasedPositions.setApprovedUser(null);
									cadreCommitteeIncreasedPositions.setCurrentCount(null);
									cadreCommitteeIncreasedPositions.setRequestCount(null);
									cadreCommitteeIncreasedPositions.setStatus("pending");
									cadreCommitteeIncreasedPositions.setApprovedCount(null);
									cadreCommitteeIncreasedPositions.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
									cadreCommitteeIncreasedPositions.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
									cadreCommitteeIncreasedPositions.setType(type); 
									 
									CadreCommitteeIncreasedPositions output=cadreCommitteeIncreasedPositionsDAO.save(cadreCommitteeIncreasedPositions);
									  Long id = output.getCadreCommitteeIncreasedPositionsId();
									  String refNo="REF#"+id.toString().trim();
									  output.setRefNo(refNo);
									  cadreCommitteeIncreasedPositionsDAO.save(output);
									//inserting childs.
									
									if(changeDesignationsList!=null && changeDesignationsList.size()>0){
										
										for(LocationWiseBoothDetailsVO locationWiseBoothDetailsVO : changeDesignationsList){
											CadreCommitteeChangeDesignations cadreCommitteeChangeDesignations=new CadreCommitteeChangeDesignations();
											cadreCommitteeChangeDesignations.setTdpCommitteeMember(tdpCommitteeMemberDAO.get(locationWiseBoothDetailsVO.getLocationId()));
											cadreCommitteeChangeDesignations.setCurrentRole(tdpCommitteeRoleDAO.get(locationWiseBoothDetailsVO.getPopulation()));
											cadreCommitteeChangeDesignations.setNewRole(tdpCommitteeRoleDAO.get(locationWiseBoothDetailsVO.getVotesPolled()));
											cadreCommitteeChangeDesignations.setCadreCommitteeIncreasedPositions(output);
											cadreCommitteeChangeDesignationsDAO.save(cadreCommitteeChangeDesignations);
										}
									  }
								   }
	                           }//
					        }); 
					       if(flagGlobal==false){
						    resultStatus.setResultCode(1); 
						   
					       }
					       else{
					    	   resultStatus.setResultCode(2); 
					    	   resultStatus.setMessage(rs.getMessage());
					       }
						 }
					   }
				   } catch (Exception e){
					   LOG.error("Exception raised in cadreCommitteeIncreasedPositionsOrChangeDesignations", e);
					   resultStatus.setResultCode(0);
				   }
				   return resultStatus;
			}
	public ResultStatus gettingStatus(Long committeeId,List<LocationWiseBoothDetailsVO> changeDesignationsList){
		//boolean flag=false;
		ResultStatus rs=new ResultStatus();
		try{
		
			//getting cid,roleids for a committee.
			List<Object[]> resultList=tdpCommitteeMemberDAO.getCommitteeDetails(committeeId);
			Map<Long,Long> resultMap=new HashMap<Long, Long>();
			if(resultList!=null && resultList.size()>0){
			  for (Object[] objects : resultList){
				  resultMap.put((Long)objects[0], (Long)objects[1]);
			  }	
			}
			
			//set New Roles To Candidates From Ui.
			if(changeDesignationsList!=null && changeDesignationsList.size()>0){
				for(LocationWiseBoothDetailsVO param : changeDesignationsList) {
					Long oldRoleId=resultMap.get(param.getTotal());
					if(oldRoleId==null){
						resultMap.put(param.getTotal(), param.getVotesPolled());
					}
					else
					  resultMap.put(param.getTotal(), param.getVotesPolled());
					
				}
			}
			
			//map for roles and its corresponding counts.
			Map<Long,Long> rolesMap=new HashMap<Long, Long>();
			
			//getting newRoles and its corresponding counts.
			 for (Map.Entry<Long, Long> entry : resultMap.entrySet())
			 {
					Long newRoleId=entry.getValue();
					Long countVar=rolesMap.get(newRoleId);
					if(countVar==null){
						rolesMap.put(newRoleId, 1l);
					}
					else{
						countVar=countVar+1l;
						rolesMap.put(newRoleId, countVar);
					}
			 }
			 
			 //getting a role and its maxCount from db for a committee.
			List<Object[]> maxCountList= tdpCommitteeRoleDAO.getMaxCountsForACommittee(committeeId);
			
			if(maxCountList!=null && maxCountList.size()>0){
				for (Object[] objects : maxCountList){
					if((Long)objects[1]!=0){
					  Long roleCount=rolesMap.get((Long)objects[0]);
					  if(roleCount!=null){
					     if(roleCount>(Long)objects[1]){
					      rs.setResultPartial(true);
					      if(rs.getMessage()==null){
					    	  rs.setMessage(objects[2].toString());
					      }
					      else{
					    	  rs.setMessage(rs.getMessage()+","+objects[2].toString());
					      }
						  objects[2].toString();
					     }
					   }
					 }
				  }
			 }
			 

		} catch (Exception e) {
			LOG.error("Exception raised in gettingDetailsForACommittee", e);
		}
		return rs;
	}
	public CadreCommitteeReportVO getTotalCommitteeDetailsByLocation(String state,Long userId,String accessType,Long accessValue,List<Long> enrollmentIdsList,String startDateStr,String endDateStr){
		
		Long totalCompletedCommittees=0l;
		Long totalStartedCommittees=0l;
		CadreCommitteeReportVO cadreCommitteeReportVO =new CadreCommitteeReportVO();
		try{
			List<Long> districtIds = new ArrayList<Long>();
			List<Long> assemblyIds = new ArrayList<Long>();
			List<Long> locationLevelValues = new ArrayList<Long>();
			Date startDate = null;
			Date endDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			if(startDateStr != null && !startDateStr.isEmpty() && endDateStr != null && !endDateStr.isEmpty()){
				 startDate = sdf.parse(startDateStr);
				 endDate = sdf.parse(endDateStr);
			 }
			
			cadreCommitteeReportVO.setAccessState("ALL");
			if(accessType.trim().equalsIgnoreCase("MP"))
			{
				//cadreCommitteeReportVO.setAccessState(delimitationConstituencyAssemblyDetailsDAO.get(accessValue).getConstituency().getName()+" "+"Parliament");
				List<Object[]> list = constituencyDAO.getParliamentConstituencyByParliamentId(accessValue);
				String userAccessValue = list.get(0)[1].toString() + " Parliament";
				cadreCommitteeReportVO.setAccessState(userAccessValue);
				
				districtIds = null;
				List<Object[]> accessConstituencyList = userConstituencyAccessInfoDAO.findByElectionScopeUser(1L,userId);
				
				List<Long> parliamentsIds = new ArrayList<Long>();
				if(accessConstituencyList != null && accessConstituencyList.size()>0)
				{
					for (Object[] parliament : accessConstituencyList) {
						parliamentsIds.add(parliament[0] != null ? Long.valueOf(parliament[0].toString().trim()):0L);
					}
				}
				else
				{
					parliamentsIds.add(accessValue);
				}
				
				if(parliamentsIds != null && parliamentsIds.size()>0)
				{
					assemblyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliamentList(parliamentsIds);
				}
				if(assemblyIds!=null && assemblyIds.size()>0){
					List<Object[]> distList = constituencyDAO.getDistictWiseConstituencyListByConstiIds(assemblyIds);
					if(distList!=null && distList.size()>0){
						Long dstrct = Long.valueOf(distList.get(0)[2].toString());
						if(dstrct>10){
							state = "AP";
						}else{
							state = "TS";
						}
					}
				}
				
			
					/*List<Object[]> tehsilDetails = tehsilDAO.getTehsilsByConstituencyIdsListAndPublicationDateId(assemblyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
					if(tehsilDetails != null && tehsilDetails.size() > 0)
					{
						for (Object[] tehsil : tehsilDetails) {
							locationLevelValues.add(tehsil[0] != null ? Long.valueOf(tehsil[0].toString().trim()):0L);						
						}
					}
					tehsilDetails = tehsilDAO.getAllLocalElecBodyListByConstituencyIdsListAndPublicationDateId(assemblyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
					if(tehsilDetails != null && tehsilDetails.size() > 0)
					{
						for (Object[] tehsil : tehsilDetails) {
							locationLevelValues.add(tehsil[0] != null ? Long.valueOf(tehsil[0].toString().trim()):0L);						
						}
					}*/
				
			}
			else
			{
				List<Object[]> accessDistrictsList = userDistrictAccessInfoDAO.findByUser(userId);
				if(accessDistrictsList != null && accessDistrictsList.size()>0)
				{
					for (Object[] districtId : accessDistrictsList) {
						districtIds.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
					}
					
					if(districtIds != null && districtIds.size() == 1)
					{
						Long districtId = districtIds.get(0).longValue();
						if(districtId != 0L)
							cadreCommitteeReportVO.setAccessState(districtDAO.get(districtId).getDistrictName()+" District");
					}
					else if(districtIds != null && districtIds.contains(1L)) // Adilabad
					{
						cadreCommitteeReportVO.setAccessState("TG");
					}
					else if(districtIds != null && districtIds.contains(11L))//Srikakulam
					{
						cadreCommitteeReportVO.setAccessState("AP");
					}
				}
			}
		
		
			if(!accessType.trim().equalsIgnoreCase("MP") && !accessType.trim().equalsIgnoreCase("DISTRICT") ){
				Long totalCommitteeCntDtls =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,null,districtIds,assemblyIds,null,enrollmentIdsList,null,null);		
				cadreCommitteeReportVO.setTotalCommittees(totalCommitteeCntDtls);
				
				List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state,null,startDate,endDate,districtIds,assemblyIds,null ,enrollmentIdsList);
				
				if(committeeCntDtls !=null && committeeCntDtls.size() > 0){				
					for (Object[] objects : committeeCntDtls) {
										
						if(Long.valueOf(objects[1].toString()).longValue() == 1l)
							totalCompletedCommittees = totalCompletedCommittees+(Long)objects[0];
							
					}
				}
				cadreCommitteeReportVO.setTotalCompleted(totalCompletedCommittees);
				if(cadreCommitteeReportVO.getTotalCommittees()  > 0){
					cadreCommitteeReportVO.setTotalCntPerc(new BigDecimal(cadreCommitteeReportVO.getTotalCompleted() * 100.0/cadreCommitteeReportVO.getTotalCommittees()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				}
				else{
					cadreCommitteeReportVO.setTotalCntPerc(0.0);
				}
			}
			else if(accessType.trim().equalsIgnoreCase("DISTRICT") ){
				List<Long> levelIds = new ArrayList<Long>();
				  levelIds.add(5l);
				  levelIds.add(7l);
				  levelIds.add(9l);
				  levelIds.add(6l);
				  levelIds.add(8l);
				  levelIds.add(11l);
					Long totalCommitteeCntDtls =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,levelIds,districtIds,assemblyIds,null,enrollmentIdsList,null,null);		
					cadreCommitteeReportVO.setTotalCommittees(totalCommitteeCntDtls);
					
					List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state,levelIds,startDate,endDate,districtIds,assemblyIds,null,enrollmentIdsList);
					
					if(committeeCntDtls !=null && committeeCntDtls.size() > 0){				
						for (Object[] objects : committeeCntDtls) {
											
							if(Long.valueOf(objects[1].toString()).longValue() == 1l)
								totalCompletedCommittees = totalCompletedCommittees+(Long)objects[0];
								
						}
					}
				
				cadreCommitteeReportVO.setTotalCompleted(totalCompletedCommittees);
				if(cadreCommitteeReportVO.getTotalCommittees()  > 0){
					cadreCommitteeReportVO.setTotalCntPerc(new BigDecimal(cadreCommitteeReportVO.getTotalCompleted() * 100.0/cadreCommitteeReportVO.getTotalCommittees()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				}
				else{
					cadreCommitteeReportVO.setTotalCntPerc(0.0);
				}
			}else{
				  Long committeesCount = 0l;
				  Long memsCount = 0l;
				  /*Long mainStrtdCount = 0l;
				  Long afflStrtdCount = 0l;*/
				  List<Long> levelIds = new ArrayList<Long>();
				  levelIds.add(5l);
				  levelIds.add(7l);
				  levelIds.add(9l);
				  
				  Long completedMainCommittees = 0l;
				  Long completedAfflCommittees = 0l;
				  
				  
				  Map<String,List<Long>> lctnsMap = getLocalBodiesDivisionsMandalByContituencyIds(assemblyIds,levelIds);
				  for (Entry<String, List<Long>> entry : lctnsMap.entrySet()) {
					  if(entry.getKey().equalsIgnoreCase("Tehsils")){
						  List<Long> lctnsIds = entry.getValue();
						  if(lctnsIds!=null && lctnsIds.size()>0){
							  List<Long> lvlIds = new ArrayList<Long>();
							  lvlIds.add(5l);
							  Long cmtCnt =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,lvlIds,districtIds,null,lctnsIds,enrollmentIdsList,null,null);
							  committeesCount = committeesCount + cmtCnt;
							  
							
							  List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state, levelIds,startDate,endDate, districtIds, assemblyIds, lctnsIds,enrollmentIdsList);
								if(committeeCntDtls !=null && committeeCntDtls.size() > 0){				
									for (Object[] objects : committeeCntDtls) {
														
										if(Long.valueOf(objects[1].toString())==1l)
											completedMainCommittees = completedMainCommittees+(Long)objects[0];
										else if(Long.valueOf(objects[1].toString()) == 2l)
											completedAfflCommittees=completedAfflCommittees+(Long)objects[0];	
									}
								}
						  }
					  }
					
					  if(entry.getKey().equalsIgnoreCase("LocalBodies")){
						  List<Long> lctnsIds = entry.getValue();
						  if(lctnsIds!=null && lctnsIds.size()>0){
							  List<Long> lvlIds = new ArrayList<Long>();
							  lvlIds.add(7l);
							  Long cmtCnt =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,lvlIds,districtIds,null,lctnsIds,enrollmentIdsList,null,null);
							  committeesCount = committeesCount + cmtCnt;
							  
							  List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state,lvlIds,startDate,endDate,districtIds,null,lctnsIds,enrollmentIdsList);
								if(committeeCntDtls !=null && committeeCntDtls.size() > 0){				
									for (Object[] objects : committeeCntDtls) {
														
										if(Long.valueOf(objects[1].toString())==1l)
											completedMainCommittees = completedMainCommittees+(Long)objects[0];
										else if(Long.valueOf(objects[1].toString()) == 2l)
											completedAfflCommittees=completedAfflCommittees+(Long)objects[0];	
									}
								}
						  }
					  }
					
					  if(entry.getKey().equalsIgnoreCase("DivisionWards")){
						  List<Long> lctnsIds = entry.getValue();
						  if(lctnsIds!=null && lctnsIds.size()>0){
							  List<Long> lvlIds = new ArrayList<Long>();
							  lvlIds.add(9l);
							  Long cmtCnt =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,lvlIds,districtIds,null,lctnsIds,enrollmentIdsList,null,null);
							  committeesCount = committeesCount + cmtCnt;
							  
							   List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state,lvlIds,startDate,endDate,districtIds,null,lctnsIds,enrollmentIdsList);
								
								if(committeeCntDtls !=null && committeeCntDtls.size() > 0){				
									for (Object[] objects : committeeCntDtls) {
														
										if(Long.valueOf(objects[1].toString())==1l)
											completedMainCommittees = completedMainCommittees+(Long)objects[0];
										else if(Long.valueOf(objects[1].toString()) == 2l)
											completedAfflCommittees=completedAfflCommittees+(Long)objects[0];	
									}
								}
								
						  }
					  }
				  }
				  
				  
				  List<Long> ruralLvlIds = new ArrayList<Long>();
				  ruralLvlIds.add(6l);
				  ruralLvlIds.add(8l);
				  
				  Long committeeCnt =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,ruralLvlIds,districtIds,assemblyIds,null,enrollmentIdsList,null,null);
				  committeesCount = committeesCount + committeeCnt;
				  //0count ,1 isCommitteeConfirmed,2.tdpCommitteeLevelId,3.tdpBasicCommitteeId
				  List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state,ruralLvlIds,startDate,endDate,districtIds,assemblyIds,null,enrollmentIdsList);
				  if(committeeCntDtls !=null && committeeCntDtls.size() > 0){				
						for (Object[] objects : committeeCntDtls) {
											
							if(Long.valueOf(objects[1].toString())==1l)
								completedMainCommittees = completedMainCommittees+(Long)objects[0];
							else if(Long.valueOf(objects[1].toString()) == 2l)
								completedAfflCommittees=completedAfflCommittees+(Long)objects[0];	
						}
					}
					
				  
				 
				  	cadreCommitteeReportVO.setTotalCommittees(committeesCount);
				  	cadreCommitteeReportVO.setTotalCompleted(completedMainCommittees);
					if(cadreCommitteeReportVO.getTotalCommittees()  > 0){
						cadreCommitteeReportVO.setTotalCntPerc(new BigDecimal(cadreCommitteeReportVO.getTotalCompleted() * 100.0/cadreCommitteeReportVO.getTotalCommittees()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					}
					else{
						cadreCommitteeReportVO.setTotalCntPerc(0.0);
					}
				
			}
			
		return cadreCommitteeReportVO;
		}
		catch(Exception e){
			LOG.error("Exception raised in getTotalCommitteeDetailsByLocation method",e);
		}
		return cadreCommitteeReportVO;
	}
	
	public List<CommitteeApprovalVO> getCommitteesForApproval(Long startNo, Long endNo,Long requestUserId){
		
		LOG.debug(" Entered into getCommitteesForApproval ");
		List<CommitteeApprovalVO> finalList = new ArrayList<CommitteeApprovalVO>();
		try{
			List<Object[]> list = tdpCommitteeLevelDAO.getAllLevels();
			List<Object[]> list1=null;
			if(requestUserId==null){
			  list1 = cadreCommitteeIncreasedPositionsDAO.getAllRecordsForApproval(startNo.intValue(), endNo.intValue()); 
			}
			else{
			  list1 = cadreCommitteeIncreasedPositionsDAO.getRequestDetailsForAUser(requestUserId); 
			}
			Map<Long, String> pancMap = new HashMap<Long, String>();
			Map<Long, String> tehsilMap = new HashMap<Long, String>();
			Map<Long, String> lebMap = new HashMap<Long, String>();
			Map<Long, String> wardMap = new HashMap<Long, String>();
			Map<Long, String> divisMap = new HashMap<Long, String>();
			
			List<CommitteeApprovalVO> locations = new ArrayList<CommitteeApprovalVO>();
			
			if(list!=null && list.size()>0){
				for(Object[] obj:list){
					CommitteeApprovalVO tmp = new CommitteeApprovalVO();
					tmp.setLocationTypeId(Long.valueOf(obj[0].toString()));
					tmp.setLocationType(obj[1].toString());
					locations.add(tmp);
				}
			}
			
			if(list1!=null && list1.size()>0){
				for(Object[] obj:list1){
					CommitteeApprovalVO tmp = getMatchedLocation(Long.valueOf(obj[1].toString()),locations);
					if(tmp!=null){
						List<Long> lctnIds = tmp.getLocationIds();
						if(lctnIds==null){
							lctnIds = new ArrayList<Long>();
						}
						lctnIds.add(Long.valueOf(obj[3].toString()));
						
						tmp.setLocationIds(lctnIds);
					}
				}
			}
			
			if(locations.size()>0){
				for(CommitteeApprovalVO tmp:locations){
					if(tmp.getLocationIds()!=null && tmp.getLocationIds().size()>0){
						if(tmp.getLocationTypeId().equals(6l)){
							List<Object[]> panchayats =  panchayatDAO.getPanchayatsByPanchayatIdsListAlongMandal(tmp.getLocationIds());
							if(panchayats!=null && panchayats.size()>0){
								for(Object[] obj:panchayats){
									pancMap.put(Long.valueOf(obj[0].toString()), obj[1].toString());
								}
							}
						}
						
						if(tmp.getLocationTypeId().equals(5l)){
							List<Object[]> tehsils =  tehsilDAO.getTehsilNameByTehsilIdsList(tmp.getLocationIds());
							if(tehsils!=null && tehsils.size()>0){
								for(Object[] obj:tehsils){
									tehsilMap.put(Long.valueOf(obj[0].toString()), obj[1].toString());
								}
							}
						}
						
						if(tmp.getLocationTypeId().equals(7l)){
							List<Object[]> lebs =  localElectionBodyDAO.findByLocalElecBodyIds(tmp.getLocationIds());
							if(lebs!=null && lebs.size()>0){
								for(Object[] obj:lebs){
									lebMap.put(Long.valueOf(obj[0].toString()), obj[1].toString()+" "+obj[2].toString());
								}
							}
						}
						
						if(tmp.getLocationTypeId().equals(8l)){
							List<Object[]> wards =  constituencyDAO.getWardsNameInLocalElectionBodyByWardIds(tmp.getLocationIds());
							if(wards!=null && wards.size()>0){
								for(Object[] obj:wards){
									wardMap.put(Long.valueOf(obj[0].toString()), obj[1].toString()+" ("+obj[2].toString()+")");
								}
							}
						}
						
						if(tmp.getLocationTypeId().equals(9l)){
							List<Object[]> divis =  constituencyDAO.getWardsNameInLocalElectionBodyByWardIds(tmp.getLocationIds());
							if(divis!=null && divis.size()>0){
								for(Object[] obj:divis){
									divisMap.put(Long.valueOf(obj[0].toString()), obj[1].toString()+" ("+obj[2].toString()+")");
								}
							}
						}
					}
				}
			}
			
			
			
			if(list1!=null && list1.size()>0){
				for(Object[] obj:list1){
					CommitteeApprovalVO cv = new CommitteeApprovalVO();
					if(requestUserId==null)
					{
						cv.setRequestNo(""+Long.valueOf(obj[0].toString()));
						cv.setTdpCommitteeRoleId(Long.valueOf(obj[11].toString())); // FOR UPDATING THE MAX POSITIONS IN TDP_COMMITTEE_ROLE 
						cv.setCadreCommitteeIncreasedPosId(Long.valueOf(obj[12].toString()));
					}
					   
					else{
					  cv.setRefNo(obj[0]!=null?obj[0].toString():"");
					  cv.setDateString(new SimpleDateFormat("dd-MM-yyyy").format(obj[11]));
					}
					cv.setLocationTypeId(Long.valueOf(obj[1].toString()));
					cv.setLocationType(obj[2].toString());
					cv.setLocationId(Long.valueOf(obj[3].toString()));
					cv.setCommitteeId(Long.valueOf(obj[4].toString()));
					cv.setCommitteeName(obj[5].toString());
					cv.setRoleId(Long.valueOf(obj[6].toString()));
					cv.setRole(obj[7].toString());
					cv.setCurrentPosCount(Long.valueOf(obj[8].toString()));
					cv.setRequestdPosCount(Long.valueOf(obj[9].toString()));
					cv.setStatus(obj[10].toString());
					
					
					String location = "";
					if(cv.getLocationTypeId().equals(5l)){
						location = tehsilMap.get(cv.getLocationId());
					}
					if(cv.getLocationTypeId().equals(6l)){
						location = pancMap.get(cv.getLocationId());					
					}
					if(cv.getLocationTypeId().equals(7l)){
						location = lebMap.get(cv.getLocationId());
					}
					if(cv.getLocationTypeId().equals(8l)){
						location = wardMap.get(cv.getLocationId());					
					}
					if(cv.getLocationTypeId().equals(9l)){
						location = divisMap.get(cv.getLocationId());
					}
					
					cv.setLocation(location);
					
					finalList.add(cv);
				}
			}
			
			
		}catch (Exception e) {
			LOG.error(" Exception Raised in getCommitteesForApproval " + e);
		}
		
		return finalList;
		
	}
	
	public CommitteeApprovalVO getMatchedLocation(Long id, List<CommitteeApprovalVO> list){
		if(id!=null && list!=null && list.size()>0){
			for(CommitteeApprovalVO temp:list){
				if(temp.getLocationTypeId().equals(id)){
					return temp;
				}
			}
		}
		return null;
	}
	
	public CommitteeSummaryVO getMatchedLocationForConstSummary(Long id, List<CommitteeSummaryVO> list){
		if(id!=null && list!=null && list.size()>0){
			for(CommitteeSummaryVO temp:list){
				if(temp.getLocationId().equals(id)){
					return temp;
				}
			}
		}
		return null;
	}
	public String checkIsVacancyForDesignation(Long tdpCommitteeRoleId)
	{
		String isEligible ="";
		try {	
			
			String committeeStatus = tdpCommitteeRoleDAO.getCommitteeStatus(tdpCommitteeRoleId);
			if(committeeStatus.equalsIgnoreCase("Y")){
				return " This Committee Is Already Confirmed, You Cannot Add Or Update Committee Members Info ";
			}
			
			TdpCommitteeRole tdpCommitteeRole = tdpCommitteeRoleDAO.get(tdpCommitteeRoleId);
			String roleType = tdpCommitteeRole.getRoleType();
			Long maxMembers = tdpCommitteeRole.getMaxMembers();
			
			if(roleType != null && roleType.equalsIgnoreCase("F"))//NOMINATED ROLE.
			{
				Set<Long> committeeRoleIds = new HashSet<Long>();
				committeeRoleIds.add(tdpCommitteeRoleId);
				List<Object[]> existringDtails = tdpCommitteeMemberDAO.getRoleWiseAllocatedMembersCount(committeeRoleIds);
			
				if(existringDtails != null && existringDtails.size()>0)
				{
					for (Object[] role : existringDtails) 
					{
						Long count = role[0] != null ? Long.valueOf(role[0].toString()):0L;
						if( maxMembers.longValue() > 0 && (count.longValue() >= maxMembers.longValue()) )
						{
							isEligible = " Max Members Already Added for this designation... ";
						}
					}
				}
			}else if(roleType != null && roleType.equalsIgnoreCase("P"))//ELECTION ROLE.
			{
				
				List<Object[]>  statusCounts = tdpCommitteeMemberDAO.getStatusWiseCandiCountForACommitteeRole(tdpCommitteeRoleId);
				
				Long finalizedMembersCount = 0l;
				Long proposedMembersCount = 0l;
				
				if(statusCounts != null && statusCounts.size() > 0)
				{
					for(Object[] obj : statusCounts)
					{
						if(obj[0] != null)
						{
							String status = obj[0].toString();
							if(status.trim().equalsIgnoreCase("F")){
								finalizedMembersCount = obj[1] != null ? (Long)obj[1] : 0l;
								proposedMembersCount = proposedMembersCount + finalizedMembersCount;
							}else if(status.trim().equalsIgnoreCase("P")){
								proposedMembersCount = proposedMembersCount + (obj[1] != null ? (Long)obj[1] : 0l);
							}
						}
					}
					if( maxMembers.longValue() > 0 && (finalizedMembersCount.longValue() >= maxMembers.longValue()) )
					{
						isEligible = " Max Members Already Added for this designation... ";
					}else{
						
						Long maxProposeMembers = tdpCommitteeRole.getMaxProposeMembers();
						if(maxProposeMembers != null && maxProposeMembers.longValue() > 0l){
							if(proposedMembersCount.longValue() >= maxProposeMembers.longValue() ){
								isEligible = " Max Members Already Proposed for this designation... ";
							}
						}
					}
				}
				
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in checkIsVacancyForDesignation method"+e);
			return " ";
		}
		
		return isEligible;
	}
	public List<CadreCommitteeMemberVO> getCommitteeDetailsByStatus(Long basicCommitteeTypeId,String status,Long levelId,String accessValue,List<Long> committeeEnrollmentIdsLst,String startDate,String endDate)
	{
		List<CadreCommitteeMemberVO> resultList = new ArrayList<CadreCommitteeMemberVO>();
		List<Long> levelIds = new ArrayList<Long>();
		//List<CadreCommitteeMemberVO> toRemove = new ArrayList<CadreCommitteeMemberVO>();
		Long constituencyId=Long.parseLong(accessValue);
		List<Object[]> list = new ArrayList<Object[]>();
			
		try{
		  SimpleDateFormat format =  new SimpleDateFormat("MM/dd/yyyy");
			Date stDate = (Date)format.parse(startDate);
			Date edDate = (Date)format.parse(endDate);
		
			if(levelId == 2)
			{
				levelIds.add(6l);// Village/Ward
				levelIds.add(8l);
			list = tdpCommitteeDAO.getLocationByTypeIdAndLevel(levelIds,basicCommitteeTypeId,constituencyId,status,committeeEnrollmentIdsLst,stDate,edDate);
			}
			
			else if(levelId == 10 || levelId == 11)
			{
				List<Long> locationIds = new ArrayList<Long>();
				locationIds.add(new Long(accessValue));
				list = tdpCommitteeDAO.getLocationsByTypeIdAndLevel(levelId,basicCommitteeTypeId,locationIds,status,committeeEnrollmentIdsLst,stDate,edDate);
			}
			else
			{
				levelIds.add(5l);
				levelIds.add(7l);
				levelIds.add(9l);// MANDAL / TOWN / DIVISION
				List<LocationWiseBoothDetailsVO> locationsList = getMandalMunicCorpDetailsNew(constituencyId);
				
				//Map<Long,List<Long>> mandalOrMuncipalMap = null;
				
				if(locationsList != null && locationsList.size() > 0)
				{
					List<Long> mandalIds = new ArrayList<Long>();
					List<Long> muncipalIds = new ArrayList<Long>();
					List<Long> divisionIds = new ArrayList<Long>();
					
					for (LocationWiseBoothDetailsVO locationWiseBoothDetailsVO : locationsList)
					{	
						char ch=(locationWiseBoothDetailsVO.getLocationId().toString().charAt(0));
						Long val=Long.parseLong(Character.toString(ch));
					
						if(val == 1l)
						{
							//muncipalIds.add(locationWiseBoothDetailsVO.getLocationId());
							muncipalIds.add(Long.parseLong(locationWiseBoothDetailsVO.getLocationId().toString().substring(1)));
							//muncipalIds.add(val);
							//mandalOrMuncipalMap.put(1l, muncipalIds);
						}
						else if (val == 2l)
						{
							mandalIds.add(Long.parseLong(locationWiseBoothDetailsVO.getLocationId().toString().substring(1)));
							//mandalOrMuncipalMap.put(2l, mandalIds);
						}
						else
						{
							divisionIds.add(Long.parseLong(locationWiseBoothDetailsVO.getLocationId().toString().substring(1)));
							//mandalOrMuncipalMap.put(3l, divisionIds);
						}		
					}
					if(muncipalIds.size() > 0){
						list.addAll(tdpCommitteeDAO.getLocationsByTypeIdAndLevel(7l,basicCommitteeTypeId,muncipalIds,status,committeeEnrollmentIdsLst,stDate,edDate));
					}
					if(mandalIds.size() > 0){
						list.addAll(tdpCommitteeDAO.getLocationsByTypeIdAndLevel(5l,basicCommitteeTypeId,mandalIds,status,committeeEnrollmentIdsLst,stDate,edDate));
					}
					if(divisionIds.size() > 0){
						list.addAll(tdpCommitteeDAO.getLocationsByTypeIdAndLevel(9l,basicCommitteeTypeId,divisionIds,status,committeeEnrollmentIdsLst,stDate,edDate));
					}
				}
					
			}
			
			
			if(list != null && list.size() > 0)
			{
				Map<Long,List<Long>> levelValuesMap = new HashMap<Long,List<Long>>();
				Map<Long,List<CadreCommitteeMemberVO>> levelVosMap = new HashMap<Long,List<CadreCommitteeMemberVO>>();
				for(Object[] params : list)
				{
					CadreCommitteeMemberVO locationVo = new CadreCommitteeMemberVO();
					String locationName = getLocationName((Long)params[2],(Long)params[0]);
					locationVo.setId((Long)params[0]);
					locationVo.setName(locationName);
					locationVo.setStatus(params[1].toString());
					locationVo.setLevel((Long)params[2]);
					locationVo.setCasteName(params[3]!= null?params[3].toString():"");
					locationVo.setVoterName(params[4]!= null?params[4].toString():"");
					
					resultList.add(locationVo);
					List<Long> levelValuesList = levelValuesMap.get((Long)params[2]);
					List<CadreCommitteeMemberVO> levelVosList = levelVosMap.get((Long)params[2]);
					if(levelValuesList == null){
						 levelValuesList = new ArrayList<Long>();
						 levelValuesMap.put((Long)params[2],levelValuesList);
						 levelVosList = new ArrayList<CadreCommitteeMemberVO>();
						 levelVosMap.put((Long)params[2],levelVosList);
						 
					}
					levelValuesList.add((Long)params[0]);
					levelVosList.add(locationVo);
				}
				
				if(!status.equalsIgnoreCase("NotStarted"))
				{
				  for(Long level : levelValuesMap.keySet()){
					  
					  List<CadreCommitteeMemberVO> newResultList = levelVosMap.get(level);
					List<Object[]> membersList = tdpCommitteeMemberDAO.getComitteeMembersByCommiteTypeAndLocation(level,levelValuesMap.get(level),basicCommitteeTypeId,status,committeeEnrollmentIdsLst,stDate,edDate);
					if(membersList != null && membersList.size() > 0)
					{
						for(Object[] params : membersList)
						{
							CadreCommitteeMemberVO vo = getMatchedLocation((Long)params[1],(Long)params[2],newResultList);
							if(vo != null)
								vo.setTotal((Long)params[0]);
						}
					}
				  }
				}
			
			if(resultList != null && resultList.size() > 0)
			resultList.get(0).setCommitte(tdpBasicCommitteeDAO.get(basicCommitteeTypeId).getName());
		
				
				}
		}
		catch(Exception e)
		{
			LOG.error("Exception raised in getCommitteeDetailsByStatus", e);	
		}
		
		return resultList;
		
	}

	public CadreCommitteeMemberVO getMatchedLocation(Long levelId,Long levelValue,List<CadreCommitteeMemberVO>resultList)
	{
		try{
			if(resultList == null || resultList.size() == 0)
				return null;
			for(CadreCommitteeMemberVO vo : resultList)
			{
				if(vo.getLevel().longValue() == levelId && vo.getId().longValue() == levelValue)
					return vo;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	/*public String getLocationName(Long LocationTypeId,Long locationValue)
	{
		String location = "";
		if(locationValue != 0L)
		{
			if(LocationTypeId.longValue() == 6L)
			{
				location = panchayatDAO.get(locationValue).getPanchayatName()+" Panchayat";
			}
			else 	if(LocationTypeId.longValue() == 8L)
			{
				location = constituencyDAO.get(locationValue).getName();
			}
			else 	if(LocationTypeId.longValue() == 5L)
			{
				location = tehsilDAO.get(locationValue).getTehsilName()+" Mandal";
			}	
			else if(LocationTypeId.longValue() == 7L)
			{
				LocalElectionBody localElectionBody = localElectionBodyDAO.get(locationValue);						
				location = localElectionBody.getName() +" "+localElectionBody.getElectionType().getElectionType();
				
				if(locationValue.longValue() == 20L || locationValue.longValue() == 124L || locationValue.longValue() == 119L)
				{
					String wardName = constituencyDAO.get(locationValue).getName();
					location = location+" - "+wardName;
				}
				
			}
			
			else if(LocationTypeId.longValue() == 9L)
			{
				String wardName = constituencyDAO.get(locationValue).getName();

				List ward = localElectionBodyWardDAO.findWardName(locationValue);
				if(ward != null && ward.size() > 0)
				location = wardName +"("+ward.get(0)+")";	
				else
				location = wardName;
				
			}	
		}
		return location;
	}*/
	public List<CadreCommitteeMemberVO> getCommitteeMemberDetails(Long basicCommitteeTypeId,Long locationId,Long levelId,String status,List<Long> committeeEnrollmentIdsLst,String startDate,String endDate)
	{
		List<CadreCommitteeMemberVO> resultList = new ArrayList<CadreCommitteeMemberVO>();
		List<Long> levelIds = new ArrayList<Long>();
		try{
			SimpleDateFormat format =  new SimpleDateFormat("MM/dd/yyyy");	
			Date stDate = (Date)format.parse(startDate);
			Date edDate = (Date)format.parse(endDate);
		List<Object[]> membersList = tdpCommitteeMemberDAO.getComitteeMembersInfoByCommiteTypeAndLocation(levelId,locationId,basicCommitteeTypeId,status,committeeEnrollmentIdsLst,stDate,edDate);
			if(membersList != null && membersList.size() > 0)
			{
				String locationName = getLocationName(levelId,locationId);
				for(Object[] params : membersList)
				{
					CadreCommitteeMemberVO vo = new CadreCommitteeMemberVO();
					vo.setImagePath(params[4] != null ? params[4].toString() : "");
					vo.setId((Long)params[2]);
					vo.setName(params[3].toString());
					if(params[5].toString().trim().length() > 8){
						vo.setMembershipNo(params[5].toString().trim().substring(params[5].toString().trim().length()-8));
					}else{
					   vo.setMembershipNo(params[5].toString());
					}
					vo.setLevel((Long)params[0]); //roleId
					vo.setRole(params[1].toString());//role
					vo.setTotal((Long)params[6]);
					vo.setStatus(params[7].toString());
					vo.setOccupation(params[22] != null ? params[22].toString() : "");
					resultList.add(vo);	
				}
				if(resultList != null && resultList.size() > 0)
				{
				resultList.get(0).setLocationName(locationName);
				resultList.get(0).setCommitte(tdpBasicCommitteeDAO.get(basicCommitteeTypeId).getName());
				}
			}
			
		}
		catch(Exception e)
		{
			LOG.error("Exception raised in getCommitteeMemberDetails", e);	
		}
		return resultList;
	}
	
	public CommitteeResultVO setCommitteConfirmation(Long basicCommitteeTypeId,Long locationId,Long levelId,List<Long> committeeEnrollmentIdsLst,String startDate,String endDate)
	{
		CommitteeResultVO resultVO = null;
		SimpleDateFormat format =  new SimpleDateFormat("MM/dd/yyyy");
		try{	
				Date stDate = null;
				Date edDate = null;
				if(startDate != null && endDate != null){
					stDate = format.parse(startDate);
					edDate = (Date)format.parse(endDate);
				}
				
				List<Long> tdpcommitteIds = tdpCommitteeMemberDAO.getTdpCommitteIds(levelId,locationId,basicCommitteeTypeId,committeeEnrollmentIdsLst,stDate,edDate);
                Long tdpCommitteeId = null;					
				if(tdpcommitteIds != null && tdpcommitteIds.size() > 0){
					tdpCommitteeId = tdpcommitteIds.get(0);
				}else{
					resultVO = new CommitteeResultVO();
					resultVO.setErrorCode(0L);
					resultVO.setMessage("This Committee Does Not Have Members..");
					return resultVO;
				}
				
				if(tdpCommitteeId != null && tdpCommitteeId.longValue() > 0l)
				{	
					if(levelId.longValue() == 6l || levelId.longValue() == 8l)////village/ward committees
					{	
						resultVO = new CommitteeResultVO();
						
						Long totalMembersCount = 0l;
						List<Object[]> roleWiseMembersCountList = tdpCommitteeMemberDAO.getRoleWiseCommitteeMembersCount(tdpCommitteeId);
						if(roleWiseMembersCountList != null && roleWiseMembersCountList.size() > 0)
						{
							for(Object[] obj : roleWiseMembersCountList)
							{	
								if(obj[0] != null)
								{	
									totalMembersCount = totalMembersCount + (Long)obj[2];
									
									if(((Long)obj[0]).longValue() == 1l)//president
									{
										if(((Long)obj[2]).longValue() >= 1l){
											resultVO.setPresidentExist(true);
										}
									}
                                    if(((Long)obj[0]).longValue() == 3l)//general secretery.
                                    {
                                    	if(((Long)obj[2]).longValue() >= 1l){
											resultVO.setGeneralSecreteryExist(true);
										}
									}
								}
							}
						}
						
						if(totalMembersCount.longValue() >= 14l){
							resultVO.setTotalMembersExist(true);
						}
						
						if(!resultVO.isPresidentExist() || !resultVO.isGeneralSecreteryExist() || !resultVO.isTotalMembersExist()){
							resultVO.setTotalCount(totalMembersCount);
							resultVO.setErrorCode(1l);
							resultVO.setMessage("Counts Are Not Matching..");
							resultVO.setTdpCommitteeId(tdpCommitteeId);
							return resultVO;
						}else{
							
							CadreCommitteeMemberVO vo = new CadreCommitteeMemberVO();
							TdpCommittee tdpCommittee = tdpCommitteeDAO.get(tdpCommitteeId);
							tdpCommittee.setCompletedDate(dateUtilService.getCurrentDateAndTime());
							tdpCommittee.setIsCommitteeConfirmed("Y");
							tdpCommitteeDAO.save(tdpCommittee);
							
							resultVO.setErrorCode(2l);
							resultVO.setMessage(" Entry Finished Successfully..");
							resultVO.setTdpCommitteeId(tdpCommitteeId);
							return resultVO;
							
						}
						
					}else{// Mandal/Town/Division committees
						
							resultVO = validateCommitteeRolesMinPositions(tdpCommitteeId);
							if(resultVO != null){
								if(resultVO.isErrorStatus()){
									resultVO.setErrorCode(1l);
									resultVO.setMessage("Committee Role Positions Counts Not Matched..");
									resultVO.setTdpCommitteeId(tdpCommitteeId);
									return resultVO;
								}else{
									
									CadreCommitteeMemberVO vo = new CadreCommitteeMemberVO();
									TdpCommittee tdpCommittee = tdpCommitteeDAO.get(tdpCommitteeId);
									tdpCommittee.setCompletedDate(dateUtilService.getCurrentDateAndTime());
									tdpCommittee.setIsCommitteeConfirmed("Y");
									tdpCommitteeDAO.save(tdpCommittee);
									
									resultVO.setErrorCode(2l);
									resultVO.setMessage(" Entry Finished Successfully..");
									resultVO.setTdpCommitteeId(tdpCommitteeId);
									return resultVO;
								}
							}
						
					}
				}
			
		}
		catch(Exception e)
		{
			LOG.error("Exception raised in setCommitteConfirmation", e);	
			if(resultVO == null){
				resultVO = new CommitteeResultVO();
			}
			resultVO.setErrorCode(3l);
			resultVO.setMessage("Exception Occurred...");
			return resultVO;
		}
		return resultVO;
	}
	
	@SuppressWarnings("unused")
	public CommitteeResultVO validateCommitteeRolesMinPositions(Long tdpCommitteeId){
		
		CommitteeResultVO finalVO = new CommitteeResultVO();
		try{
			 
			 
			    Long  committeeConfirmRuleId = null;
				 
				//1. get all roles to this committees and push role wise members count.
				 Map<Long , CommitteeResultVO> allRolesMap = new LinkedHashMap<Long, CommitteeResultVO>();
				 
				 List<Object[]> allRoles = tdpCommitteeRoleDAO.getAllRolesInACommittee(tdpCommitteeId);
				 
				 if(allRoles != null && allRoles.size() > 0){
					 for(Object[] obj : allRoles){
						 if(obj[0] != null){
							 CommitteeResultVO roleVO = new  CommitteeResultVO();
							 roleVO.setTdpRolesId((Long)obj[0]);
							 roleVO.setRole(obj[1]!= null ? obj[1].toString() : "");
							 roleVO.setOccupiedCount(0l);
							 roleVO.setTotalCount(obj[3]!= null ? (Long)obj[3] : 0l);
							 allRolesMap.put( roleVO.getTdpRolesId() , roleVO);
							 
							 if(committeeConfirmRuleId == null && obj[2]!= null){
								 committeeConfirmRuleId = (Long)obj[2];
							 }
						 }
					 }
				 }
				
				 List<Object[]> roleWiseCountList = tdpCommitteeMemberDAO.getRoleWiseCommitteeMembersCount(tdpCommitteeId);
				 if(roleWiseCountList != null && roleWiseCountList.size() > 0){
					 for(Object[] obj : roleWiseCountList){
						 if(obj[0] != null){
							 CommitteeResultVO rolesVO = allRolesMap.get((Long)obj[0]);
							 if(rolesVO != null){
								 rolesVO.setOccupiedCount(obj[2] != null ? (Long)obj[2] : 0l);
							 }
						 }
					 }
				 }
				 
				 if(committeeConfirmRuleId != null && committeeConfirmRuleId.longValue() > 0l){
					 
					 //2.get Each Role Min positions count.
					 Map<Long,Long> tdpRolesMinPositionsMap = getCommitteeRolesMinPositions(committeeConfirmRuleId);
					 
					 //validation
					 boolean errorFlag = false;
					 List<CommitteeResultVO> errorMsgList = new ArrayList<CommitteeResultVO>();
					 
					 if(allRolesMap != null && allRolesMap.size() > 0 && tdpRolesMinPositionsMap != null && tdpRolesMinPositionsMap.size() > 0)
					 {
						 for(Map.Entry<Long, CommitteeResultVO> roleEntry : allRolesMap.entrySet())
						 {
							 Long roleId = roleEntry.getKey();
							 CommitteeResultVO roleVO = roleEntry.getValue();
							 
							 if(tdpRolesMinPositionsMap.containsKey(roleId))
							 {
								 Long minCount = tdpRolesMinPositionsMap.get(roleId);
								 Long occupiedCount = roleVO.getOccupiedCount();
								 
								 if(occupiedCount.longValue() < minCount.longValue()){
									 errorFlag = true;
									 
									 CommitteeResultVO errorRoleVO = new CommitteeResultVO();
									 errorRoleVO.setTdpRolesId(roleVO.getTdpRolesId()); 
									 errorRoleVO.setRole(roleVO.getRole());
									 errorRoleVO.setMinCount(minCount);
									 errorRoleVO.setOccupiedCount(occupiedCount);
									 errorRoleVO.setTotalCount(roleVO.getTotalCount());
									 errorMsgList.add(errorRoleVO);
									 
								 }
							 }
						 }
					 }
					 
					 finalVO.setErrorStatus(errorFlag);
					 if(errorFlag){
						 finalVO.setSubList(errorMsgList);
					 }
				 }
				
			
			
		}catch (Exception e) {
			LOG.error("Exception raised in validateCommitteeRolesMinPositions", e);	
		}
		return finalVO;
	}
	
	 
	public Map<Long,Long> getCommitteeRolesMinPositions(Long committeeConfirmRuleId)
	{
		 Map<Long,Long> tdpRolesMinPositionsMap = new HashMap<Long, Long>();
		try
		{	 
			 List<Object[]> rolesList =  committeeConfirmRuleConditionDAO.getRolesMinPositionsByRule(committeeConfirmRuleId);
			 
			 if(rolesList != null && rolesList.size() > 0)
			 {
				 for(Object[] obj : rolesList)
				 {
				   	 if(obj[0] != null && obj[1] != null)
				   	 {
				   		tdpRolesMinPositionsMap.put((Long)obj[0], (Long)obj[1]);
				   	 }
				 }
			 }
			
		}catch(Exception e){
			LOG.error("Exception raised in getCommitteeRolesMinPositions", e);	
		}
		return tdpRolesMinPositionsMap;
	}
		
	public List<CadreCommitteeMemberVO> deleteCadreRole(Long tdpCommitteeMemberId,List<Long> committeeEnrollmentIdsLst,String startDate,String endDate)
	{
		List<CadreCommitteeMemberVO> resultList = new ArrayList<CadreCommitteeMemberVO>();
		try{
			 SimpleDateFormat format =  new SimpleDateFormat("MM/dd/yyyy");
				Date stDate = (Date)format.parse(startDate);
				Date edDate = (Date)format.parse(endDate);
			CadreCommitteeMemberVO vo = new CadreCommitteeMemberVO();
		
			List<Object[]> list = tdpCommitteeMemberDAO.getCommitteStatusAndId(tdpCommitteeMemberId,committeeEnrollmentIdsLst,stDate,edDate);
			if(list != null)
			{
				Object[] params = list.get(0);
				String status = params[0].toString();
				Long tdpCommitteId = new Long(params[1].toString());
				if(!status.equalsIgnoreCase("Y"))
				{
					Integer val = tdpCommitteeMemberDAO.deleteCadreRole(tdpCommitteeMemberId,committeeEnrollmentIdsLst,stDate,edDate);
					if(val != null && val > 0)
					{
					vo.setStatus("Removed");
					resultList.add(vo);	
					}
					Long membersCnt = tdpCommitteeMemberDAO.getCommitteMembers(tdpCommitteId);
					if(membersCnt == null || membersCnt == 0)
					{
						TdpCommittee tdpCommittee = tdpCommitteeDAO.get(tdpCommitteId);
						tdpCommittee.setStartedDate(null);
						tdpCommitteeDAO.save(tdpCommittee);
					}
				}
				else 
				{
					vo.setStatus("Confirmed");
					resultList.add(vo);		
				}
			}
		}
		catch(Exception e)
		{
			LOG.error("Exception raised in setCommitteConfirmation", e);	
		}
		return resultList;
	}
	
	public String updateCommitteePosCount(final Long roleId, final Long maxCount, final String type, final Long increasedPosId, final Long approveCount){
		LOG.debug("Entered Into updateCommitteePosCount ");
		String finalStatus ="failed";
		ResultStatus status = new ResultStatus();
		try {
				status = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
				 public Object doInTransaction(TransactionStatus status) {
					 ResultStatus resultStatus = new ResultStatus();
					 resultStatus.setResultCode(1);
					 
					if(type.equalsIgnoreCase("Approved")){
						List<Object[]> list = tdpCommitteeRoleDAO.getDetailsForTdpCommitteRoleId(roleId);
						if(list!=null && list.size()>0){
							TdpCommitteeRoleHistory histroy = new TdpCommitteeRoleHistory();
							Object[] tmp = list.get(0);
							histroy.setTdpCommitteeRoleId(Long.valueOf(tmp[0].toString()));
							histroy.setTdpCommitteeId(Long.valueOf(tmp[1].toString()));
							histroy.setTdpRolesId(Long.valueOf(tmp[2].toString()));
							histroy.setMaxMembers(Long.valueOf(tmp[3].toString()));
							histroy.setUpdatedTime((Date) tmp[4]);
							
							
							Date updatedTime = dateUtilService.getCurrentDateAndTime();
							
							int updatedCount = tdpCommitteeRoleDAO.updateMaxPosForCommitteeRoleId(roleId, approveCount, updatedTime);
							
							if(updatedCount>0){
								TdpCommitteeRoleHistory savHistory = tdpCommitteeRoleHistoryDAO.save(histroy);
								
								int statusUpdate = cadreCommitteeIncreasedPositionsDAO.updateStatus(type, updatedTime, increasedPosId, approveCount);
								if(statusUpdate>0){
									resultStatus.setResultCode(0);
								}
							}
						}
						
					}
					if(type.equalsIgnoreCase("Rejected")){
						Date updatedTime = dateUtilService.getCurrentDateAndTime();
						int statusUpdate = cadreCommitteeIncreasedPositionsDAO.updateStatus(type, updatedTime, increasedPosId, null);
						if(statusUpdate>0){
							resultStatus.setResultCode(0);
						}
					}
					
					return resultStatus;
				 }
				});
			
			if(status.getResultCode()==0){
				finalStatus ="success";
			}
		}catch (Exception e) {
			LOG.error("Exception Raised in updateCommitteePosCount " + e);
			return finalStatus;
		}
		return finalStatus;
	}
	
	public CommitteeApprovalVO getStatusCountsOfApproval(){
			LOG.debug(" In getStatusCountsOfApproval() ");
			CommitteeApprovalVO cv = new CommitteeApprovalVO();
			try{
				List<Object[]> list = cadreCommitteeIncreasedPositionsDAO.getAllRecordsCountStatusWise();
				Long totalCount = 0l;
				if(list!=null && list.size()>0){
					for(Object[] obj:list){
						if(obj[1].toString().equalsIgnoreCase("Approved")){
							cv.setApprovedCount(Long.valueOf(obj[0].toString()));
						}else if(obj[1].toString().equalsIgnoreCase("Rejected")){
							cv.setRejectedCount(Long.valueOf(obj[0].toString()));
						}else{
							cv.setPendingCount(Long.valueOf(obj[0].toString()));
						}
						totalCount = totalCount + Long.valueOf(obj[0].toString());
					}
					cv.setTotalCount(totalCount);
				}
			}catch (Exception e) {
				LOG.error("Exception Raised in getStatusCountsOfApproval() ");
			}
			return cv;
		
	}
	
	public String gettingReferenceNumber(Long id){
		String output=null;
		try{
		    String refNo=id.toString();
			int refLength=refNo.trim().length();
			if(refLength==1)
			 output="#0000000"+refNo;
			else if(refLength==2)
			  output="#000000"+refNo;
			else if(refLength==3)
			  output="#00000"+refNo;
			else if(refLength==4)
			  output="#0000"+refNo;
			else if(refLength==5)
			  output="#000"+refNo;
			else if(refLength==6)
			  output="#00"+refNo;
			else if(refLength==7)
			  output="#0"+refNo;
			else
			  output="#"+refNo;
		}catch (Exception e) {
			 LOG.error("Exception raised in gettingReferenceNumber", e);
		}
		return output;
	}
	
	public List<CommitteeSummaryVO> getSummaryDetails(String accessValue,String reqLocationType,String startDateStr,String endDateStr, 
			 List<Long> committeeEnrollmentIdsLst,List<Long> levelIdsLsit)
	{
		List<CommitteeSummaryVO> returnList = null;
		try {
			SimpleDateFormat format =  new SimpleDateFormat("MM/dd/yyyy");	
			Date startDate = (Date)format.parse(startDateStr);
			Date endDate = (Date)format.parse(endDateStr);
			
			Long constituencyId=Long.parseLong(accessValue);
			List<Object[]> valuesList = tdpCommitteeDAO.getLocationWiseVillageDetails(constituencyId,reqLocationType,startDate,endDate,committeeEnrollmentIdsLst,levelIdsLsit);
			List<Object[]> allStartedList = tdpCommitteeDAO.getLocationWiseVillageStartedDetails(constituencyId,reqLocationType,startDate,endDate,committeeEnrollmentIdsLst,levelIdsLsit);
			Map<Long,CommitteeSummaryVO> returnMap = new HashMap<Long,CommitteeSummaryVO>();
			CommitteeSummaryVO mainVO = new CommitteeSummaryVO();
			 getVillageLvlInfo( valuesList,allStartedList,returnMap,mainVO);
			 returnList = new ArrayList<CommitteeSummaryVO>(returnMap.values());
			if(returnList.size() > 0){
				CommitteeSummaryVO vo = returnList.get(0);
				vo.setMainComitteesConformed(mainVO.getMainComitteesConformed() != null ? mainVO.getMainComitteesConformed():0L );
				vo.setMainComittees(mainVO.getMainComittees() != null ? mainVO.getMainComittees():0L);
				vo.setStartedCount(mainVO.getStartedCount() != null ? mainVO.getStartedCount():0L);
			}else{
				returnList.add(mainVO);
			}
			return returnList;
		} catch (Exception e) {
			LOG.error("Exception raised in getSummaryDetails", e);
		}
		
		return returnList;
		
	}
	
	public void getVillageLvlInfo(List<Object[]> valuesList,List<Object[]> allStartedList
			,Map<Long,CommitteeSummaryVO> returnMap,CommitteeSummaryVO mainVO){
		Long mainCommitteTotal = 0l;
		Long mainCommitteConformed = 0l;
		Long startedCount = 0l;

		//0 basiccommId,1 name,2confirmd,3count
		for(Object[] count: valuesList){
			if(((Long)count[0]).longValue() == 1l){
				mainCommitteTotal = mainCommitteTotal+(Long)count[3];
				if(count[2].toString().equalsIgnoreCase("Y") && count[4] != null){
					mainCommitteConformed = mainCommitteConformed+(Long)count[3];
				}
			}else{
			     CommitteeSummaryVO vo = returnMap.get((Long)count[0]);
			     if(vo == null){
				   vo = new  CommitteeSummaryVO();
				   vo.setAffilatedCommitteId((Long)count[0]);
				   vo.setAffilatedCommitteeName(count[1].toString());
				   returnMap.put((Long)count[0],vo);
			     }
			    vo.setTotalAffilatedCommittees(vo.getTotalAffilatedCommittees()+(Long)count[3]);
				if(count[2].toString().equalsIgnoreCase("Y")  && count[4] != null){
					 vo.setAffComitteesConformed(vo.getAffComitteesConformed()+(Long)count[3]);
				}
		    }
		}
		//0 basiccommId,3count
		for(Object[] count: allStartedList){
			if(((Long)count[0]).longValue() == 1l){
				startedCount = startedCount+(Long)count[1];
			}else{
			     CommitteeSummaryVO vo = returnMap.get((Long)count[0]);
			     if(vo == null){
				   vo = new  CommitteeSummaryVO();
				   vo.setAffilatedCommitteeName("");
				   returnMap.put((Long)count[0],vo);
			     }
			    vo.setAffilatedStartedCount(vo.getAffilatedStartedCount()+(Long)count[1]);
		    }
		}
		mainVO.setMainComitteesConformed(mainVO.getMainComitteesConformed()+mainCommitteConformed);
		mainVO.setMainComittees(mainVO.getMainComittees()+mainCommitteTotal);
		mainVO.setStartedCount(mainVO.getStartedCount()+startedCount);
		
	}
	/*if(((Long)committeesCount[1]).longValue() == 1l){
		if(((Long)committeesCount[2]).longValue() == 6l || ((Long)committeesCount[2]).longValue() == 8l){
			mainVillageVo.setLocationId((Long)committeesCount[0]);
		}else{
			mainMandalVo.setLocationId((Long)committeesCount[0]);
		}
	}else{
		if(((Long)committeesCount[2]).longValue() == 6l || ((Long)committeesCount[2]).longValue() == 8l){
			affVillageVo.setLocationId((Long)committeesCount[0]);
		}else{
			affMandalVo.setLocationId((Long)committeesCount[0]);
		}
	}*/
		//0count ,1 basic committeeId,2basic committee name,3committeeType
	
	public List<LocationWiseBoothDetailsVO> getStartedCommitteesCountInALocation(String accessValue){
		Long constituencyId=Long.parseLong(accessValue);
		
		List<LocationWiseBoothDetailsVO> committeesCountsInfo = new ArrayList<LocationWiseBoothDetailsVO>();
		
		LocationWiseBoothDetailsVO villageLevelStartedCommVo   = new LocationWiseBoothDetailsVO();
		List<LocationWiseBoothDetailsVO> villageLevelStartedAffComm = new ArrayList<LocationWiseBoothDetailsVO>();
		villageLevelStartedCommVo.setResult(villageLevelStartedAffComm);
		
		List<Object[]> committeesStartedList = tdpCommitteeMemberDAO.getStartedCommitteesCountInALocation(constituencyId);
		
		for(Object[] committeesCount:committeesStartedList){
			if(((Long)committeesCount[3]).longValue() == 1l){
				villageLevelStartedCommVo.setTotal(Long.parseLong(committeesCount[0].toString()));//count
				villageLevelStartedCommVo.setLocationId(Long.parseLong(committeesCount[1].toString()));//basic committeeId
				villageLevelStartedCommVo.setLocationName(committeesCount[2].toString());//basic committee name
				villageLevelStartedCommVo.setPopulation(Long.parseLong(committeesCount[3].toString()));//3committeeType
			}
			else if(((Long)committeesCount[3]).longValue() == 2l){
				LocationWiseBoothDetailsVO tempVo=new LocationWiseBoothDetailsVO();
				tempVo.setTotal(Long.parseLong(committeesCount[0].toString()));
				tempVo.setLocationId(Long.parseLong(committeesCount[1].toString()));
				tempVo.setLocationName(committeesCount[2].toString());
				tempVo.setPopulation(Long.parseLong(committeesCount[3].toString()));//3committeeType
				villageLevelStartedAffComm.add(tempVo);
			}
		}
		
		committeesCountsInfo.add(villageLevelStartedCommVo);
		
			return committeesCountsInfo;
	}
	
		
	@SuppressWarnings("unused")
	public List<CommitteeSummaryVO> gettingMandalAndMuncipalAndDivisonSummary(String accessValue,List<Long> committeeEnrollmentIdsLst,String startDate,String endDate,String reqLocationTypeStr , List<Long> levelIdsList)
	{	
		List<CommitteeSummaryVO> returnList = null;
		try{
        SimpleDateFormat format =  new SimpleDateFormat("dd/MM/yyyy");	
			Date stDate = (Date)format.parse(startDate);
			Date edDate = (Date)format.parse(endDate);
			Map<Long,CommitteeSummaryVO> returnMap = new HashMap<Long,CommitteeSummaryVO>();
			CommitteeSummaryVO mainVO = new CommitteeSummaryVO();
		Long constituencyId=Long.parseLong(accessValue);
		
		List<LocationWiseBoothDetailsVO> resultList = getMandalMunicCorpDetailsNew(constituencyId);
		
		//Map<Long,List<Long>> mandalOrMuncipalMap = null;
		
		if(resultList != null && resultList.size() > 0)
		{
			//mandalOrMuncipalMap = new HashMap<Long, List<Long>>();
			List<Long> mandalIds = new ArrayList<Long>();
			List<Long> muncipalIds = new ArrayList<Long>();
			List<Long> divisionIds = new ArrayList<Long>();
			for (LocationWiseBoothDetailsVO locationWiseBoothDetailsVO : resultList)
			{	
				char ch=(locationWiseBoothDetailsVO.getLocationId().toString().charAt(0));
				Long val=Long.parseLong(Character.toString(ch));
			
				if(val.longValue() == 1l)
				{
					//muncipalIds.add(locationWiseBoothDetailsVO.getLocationId());
					muncipalIds.add(Long.parseLong(locationWiseBoothDetailsVO.getLocationId().toString().substring(1)));
					//muncipalIds.add(val);
					//mandalOrMuncipalMap.put(1l, muncipalIds);
				}
				else if (val.longValue() == 2l)
				{
					mandalIds.add(Long.parseLong(locationWiseBoothDetailsVO.getLocationId().toString().substring(1)));
					//mandalOrMuncipalMap.put(2l, mandalIds);
				}
				else
				{
					divisionIds.add(Long.parseLong(locationWiseBoothDetailsVO.getLocationId().toString().substring(1)));
					//mandalOrMuncipalMap.put(3l, divisionIds);
				}
			}
			if(mandalIds.size() > 0){
				List<Object[]> valuesList = tdpCommitteeDAO.getLocationWiseMandalDetails(mandalIds,5l,committeeEnrollmentIdsLst,stDate,edDate);
				List<Object[]> allStartedList = tdpCommitteeDAO.getLocationWiseMandalStartedDetails(mandalIds,5l,committeeEnrollmentIdsLst,stDate,edDate);
				getVillageLvlInfo( valuesList,allStartedList,returnMap,mainVO); 
			}
			if(muncipalIds.size() > 0){
				List<Object[]> valuesList = tdpCommitteeDAO. getLocationWiseMandalDetails(muncipalIds,7l,committeeEnrollmentIdsLst,stDate,edDate);
				List<Object[]> allStartedList = tdpCommitteeDAO.getLocationWiseMandalStartedDetails(muncipalIds,7l,committeeEnrollmentIdsLst,stDate,edDate);
				getVillageLvlInfo( valuesList,allStartedList,returnMap,mainVO); 
			}
			if(divisionIds.size() > 0){
				List<Object[]> valuesList = tdpCommitteeDAO. getLocationWiseMandalDetails(divisionIds,9l,committeeEnrollmentIdsLst,stDate,edDate);
				List<Object[]> allStartedList = tdpCommitteeDAO.getLocationWiseMandalStartedDetails(divisionIds,9l,committeeEnrollmentIdsLst,stDate,edDate);
				getVillageLvlInfo( valuesList,allStartedList,returnMap,mainVO); 
			}
			returnList = new ArrayList<CommitteeSummaryVO>(returnMap.values());
			if(returnList.size() > 0){
				CommitteeSummaryVO vo = returnList.get(0);
				vo.setMainComitteesConformed(mainVO.getMainComitteesConformed());
				vo.setMainComittees(mainVO.getMainComittees());
				vo.setStartedCount(mainVO.getStartedCount());
			}
			return returnList;
		}
		}catch (Exception e) {
			LOG.error("Exception raised gettingMandalAndMuncipalAndDivisonSummary()",e);
		}
		
		return returnList;
	}
	
	@SuppressWarnings("unused")
	public List<CommitteeSummaryVO> getCommitteeSummaryInfoByUserAccess(Long accessValue,String accessType,List<Long> committeeEnrollmentIdsLst,String startDate,
			 String endDate,List<Long> committeeLevelIdsList,List<Long> mainOrAfflCommitteIds)
	{	
		List<CommitteeSummaryVO> returnList = null;
		List<Long> locationIds = new ArrayList<Long>();
		Long locationLevelId = 0l;
		locationIds.add(accessValue);
		if(accessType.equalsIgnoreCase("State"))
			locationLevelId = 10l;
		else if(accessType.equalsIgnoreCase("District"))
			locationLevelId = 11l;
				
		try{
			SimpleDateFormat format =  new SimpleDateFormat("MM/dd/yyyy");
			Date stDate = (Date)format.parse(startDate);
			Date edDate = (Date)format.parse(endDate);		
			List<Object[]> valuesList = tdpCommitteeDAO.getLocationsWiseMandalDetails(locationIds,committeeLevelIdsList,committeeEnrollmentIdsLst,stDate,edDate,accessType,mainOrAfflCommitteIds);
			List<Object[]> allStartedList = tdpCommitteeDAO.getLocationsWiseMandalStartedDetails(locationIds,committeeLevelIdsList,committeeEnrollmentIdsLst,stDate,edDate,accessType,mainOrAfflCommitteIds);
			Map<Long,CommitteeSummaryVO> returnMap = new HashMap<Long,CommitteeSummaryVO>();
			CommitteeSummaryVO mainVO = new CommitteeSummaryVO();
			getVillageLvlInfo( valuesList,allStartedList,returnMap,mainVO);
			 returnList = new ArrayList<CommitteeSummaryVO>(returnMap.values());
			 
			 CommitteeSummaryVO vo = null;
			 if(returnList.size() > 0){
                 vo = returnList.get(0);
				 vo.setAffliatedCommitteesExist(true);
				 //adding main committees counts
				 if(mainVO.getMainComittees() != null && mainVO.getMainComittees().longValue() > 0){
					 vo.setMainCommitteesExist(true);
					 vo.setMainComittees(mainVO.getMainComittees());
					 vo.setMainComitteesConformed(mainVO.getMainComitteesConformed());
					 vo.setStartedCount(mainVO.getStartedCount());
				 }else{
					 vo.setMainCommitteesExist(false); 
				 }
				 vo.setLocationId(locationLevelId);
				 vo.setLocationName(getLocationName(locationLevelId, accessValue));
				 
			 }else{
                 vo = new CommitteeSummaryVO();
                 vo.setAffliatedCommitteesExist(false);
				//adding main committees counts
				 if(mainVO.getMainComittees() != null && mainVO.getMainComittees().longValue() > 0){
					 vo.setMainCommitteesExist(true);
					 vo.setMainComittees(mainVO.getMainComittees());
					 vo.setMainComitteesConformed(mainVO.getMainComitteesConformed());
					 vo.setStartedCount(mainVO.getStartedCount());
				 }else{
					 vo.setMainCommitteesExist(false); 
				 }
				 vo.setLocationId(locationLevelId);
				 vo.setLocationName(getLocationName(locationLevelId, accessValue));
				 returnList.add(vo);
			 }
			
			return returnList;

		}
		catch(Exception e)
		{
			LOG.error(e);
		}
		return returnList;
	}
	public void fillResultDetails(List<LocationWiseBoothDetailsVO> startedList,List<CommitteeSummaryVO> returnList,Long startedCount,Map<Long,CommitteeSummaryVO> affilatedMap,CommitteeSummaryVO mainCommitteesVO)
	{
		try {
			if(startedList != null && startedList.size() > 0)
			{
				for (LocationWiseBoothDetailsVO locationWiseBoothDetailsVO : startedList)
				{
					if(locationWiseBoothDetailsVO.getLocationId() == 1)
					{
						startedCount = locationWiseBoothDetailsVO.getTotal();
					}
					else
					{
						CommitteeSummaryVO affVO = affilatedMap.get(locationWiseBoothDetailsVO.getLocationId());
						if(affVO != null)
						{
							affVO.setAffilatedStartedCount(locationWiseBoothDetailsVO.getTotal());
							returnList.add(affVO);
						}
					}
					
					
				}
				
				if(returnList != null && returnList.size() > 0)
				{
					returnList.get(0).setMainComitteesConformed(mainCommitteesVO.getMainComitteesConformed());
					returnList.get(0).setMainComitteesNotConformed(mainCommitteesVO.getMainComitteesNotConformed());
					returnList.get(0).setStartedCount(startedCount);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised fillResultDetails()", e);
		}
	}
	public void fillCommitteeSummaryVO(List<Object[]> valuesList,Long mainCommitteConformed,Long mainCommitteNotConformed,Map<Long,CommitteeSummaryVO> affilatedMap,CommitteeSummaryVO mainCommitteesVO)
	{
		try
		{
			for (Object[] objects : valuesList)
			{
				if(objects[0] != null)
				{
					if((Long)objects[0] == 1)
					{
						if(objects[3] != null)
						{
							if(objects[3].toString().equalsIgnoreCase("Y"))
							{
								 mainCommitteConformed = mainCommitteConformed + (Long)objects[4];
								 mainCommitteesVO.setMainComitteesConformed(mainCommitteConformed);
							}
							else
							{
								 mainCommitteNotConformed = mainCommitteNotConformed + (Long)objects[4];
								 mainCommitteesVO.setMainComitteesNotConformed(mainCommitteNotConformed);
							}
							
						}
					}
					else
					{
						if(objects[2] != null)
						{
							CommitteeSummaryVO affVO = affilatedMap.get((Long)objects[2]);
							if(affVO == null)
							{
								affVO = new CommitteeSummaryVO();
								affVO.setAffilatedCommitteId((Long)objects[2]);
								affVO.setAffilatedCommitteeName(objects[1].toString());
								affilatedMap.put((Long)objects[2], affVO);
							}
							
							if(objects[3].toString().equalsIgnoreCase("Y"))
							{
								affVO.setAffComitteesConformed((Long)objects[4]);
							}
							else
							{
								affVO.setAffComitteesNotConformed((Long)objects[4]);
							}
							
						}
					}
				}
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception raised fillCommitteeSummaryVO()",e);
		}
	}
	public List<LocationWiseBoothDetailsVO> getMandalMuncipalDivisonTotalCommittees(String accessValue,Map<Long,CommitteeSummaryVO> affilatedMap){
		List<LocationWiseBoothDetailsVO> finalCounts= null;
		try{
			Long constituencyId=Long.parseLong(accessValue);
			List<LocationWiseBoothDetailsVO> resultList = getMandalMunicCorpDetailsNew(constituencyId);
			
			//Map<Long,List<Long>> mandalOrMuncipalMap = null;
			
			if(resultList != null && resultList.size() > 0)
			{
				//mandalOrMuncipalMap = new HashMap<Long, List<Long>>();
				List<Long> mandalIds = new ArrayList<Long>();
				List<Long> muncipalIds = new ArrayList<Long>();
				List<Long> divisionIds = new ArrayList<Long>();
				for (LocationWiseBoothDetailsVO locationWiseBoothDetailsVO : resultList)
				{	
					char ch=(locationWiseBoothDetailsVO.getLocationId().toString().charAt(0));
					Long val=Long.parseLong(Character.toString(ch));
				
					if(val == 1l)
					{
						//muncipalIds.add(locationWiseBoothDetailsVO.getLocationId());
						muncipalIds.add(Long.parseLong(locationWiseBoothDetailsVO.getLocationId().toString().substring(1)));
						//muncipalIds.add(val);
						//mandalOrMuncipalMap.put(1l, muncipalIds);
					}
					else if (val == 2l)
					{
						mandalIds.add(Long.parseLong(locationWiseBoothDetailsVO.getLocationId().toString().substring(1)));
						//mandalOrMuncipalMap.put(2l, mandalIds);
					}
					else
					{
						divisionIds.add(Long.parseLong(locationWiseBoothDetailsVO.getLocationId().toString().substring(1)));
						//mandalOrMuncipalMap.put(3l, divisionIds);
					}
				}
				
				// MANDAL WISE DETAILS
				List<Object[]> mandalWiseList = tdpCommitteeMemberDAO.getLocationWiseStartedCount(mandalIds,5l);
				// muncipal WISE DETAILS
				List<Object[]> muncipalList  = tdpCommitteeMemberDAO.getLocationWiseStartedCount(muncipalIds,7l);
				// divisions WISE DETAILS
				//List<Object[]> divisionsList  = tdpCommitteeMemberDAO.getLocationWiseStartedCount(divisionIds,9l);//open
				
				Map<Long, Long> counts = new HashMap<Long, Long>();
				for(Long i=1l;i<10l;i++)/*number committeess in tdp_basic_committee table*/{
					counts.put(i, 0l);
				}
				
				List<Object[]> valuesList = new ArrayList<Object[]>();
				
				valuesList.addAll(mandalWiseList);
				valuesList.addAll(muncipalList);
				//valuesList.addAll(divisionsList);
				Map<Long,LocationWiseBoothDetailsVO> resultMap = new HashMap<Long, LocationWiseBoothDetailsVO>();
				for(Object[] objects : valuesList)
				{
					if(objects[0] != null)
					{
						LocationWiseBoothDetailsVO vo = resultMap.get((Long)objects[0]);
						if(vo == null)
						{
							vo = new LocationWiseBoothDetailsVO();
							vo.setLocationId((Long)objects[0]);
							vo.setLocationName(objects[1] != null ? objects[0].toString() : "");
							resultMap.put((Long)objects[0], vo);
						}
						vo.setTotal(vo.getTotal() + (Long)objects[2]);
						
					}
					
				}
				
				if(affilatedMap != null && affilatedMap.size() > 0)
				{
					finalCounts = new ArrayList<LocationWiseBoothDetailsVO>();
					Set<Long> committeeIds = affilatedMap.keySet();
					for (Long committeeId : committeeIds) 
					{
						CommitteeSummaryVO vo = affilatedMap.get(committeeId);
						LocationWiseBoothDetailsVO subVO = resultMap.get(committeeId);
						if(subVO == null)
						{
							subVO = new LocationWiseBoothDetailsVO();
							subVO.setLocationId(committeeId);
							subVO.setLocationName(vo.getAffilatedCommitteeName());
							subVO.setTotal(0l);
						}
						
						finalCounts.add(subVO);
					}
					LocationWiseBoothDetailsVO subVO = resultMap.get(1l);
					if(subVO != null)
					{
						finalCounts.add(subVO);
					}
				}
			}
			
			
		}catch (Exception e) {
			LOG.error("Exception raised in getMandalMuncipalDivisonTotalCommittees()"+e);
		}
		return finalCounts;
	}
	
	public List<LocationWiseBoothDetailsVO> getVillageTotalCommittees(String accessValue,Map<Long,CommitteeSummaryVO> affilatedMap){
		List<LocationWiseBoothDetailsVO> finalCounts= null;
		try{
			Long constituencyId=Long.parseLong(accessValue);
			
			
				Map<Long, Long> counts = new HashMap<Long, Long>();
				for(Long i=1l;i<10l;i++)/*number committeess in tdp_basic_committee table*/{
					counts.put(i, 0l);
				}
				
				List<Object[]> valuesList = tdpCommitteeMemberDAO.getVillageStartedCount(constituencyId);

				Map<Long,LocationWiseBoothDetailsVO> resultMap = new HashMap<Long, LocationWiseBoothDetailsVO>();
				for(Object[] objects : valuesList)
				{
					if(objects[0] != null)
					{
						LocationWiseBoothDetailsVO vo = resultMap.get((Long)objects[0]);
						if(vo == null)
						{
							vo = new LocationWiseBoothDetailsVO();
							vo.setLocationId((Long)objects[0]);
							vo.setLocationName(objects[1] != null ? objects[0].toString() : "");
							resultMap.put((Long)objects[0], vo);
						}
						vo.setTotal(vo.getTotal() + (Long)objects[2]);
						
					}
					
				}
				
				if(affilatedMap != null && affilatedMap.size() > 0)
				{
					finalCounts = new ArrayList<LocationWiseBoothDetailsVO>();
					Set<Long> committeeIds = affilatedMap.keySet();
					for (Long committeeId : committeeIds) 
					{
						CommitteeSummaryVO vo = affilatedMap.get(committeeId);
						LocationWiseBoothDetailsVO subVO = resultMap.get(committeeId);
						if(subVO == null)
						{
							subVO = new LocationWiseBoothDetailsVO();
							subVO.setLocationId(committeeId);
							subVO.setLocationName(vo.getAffilatedCommitteeName());
							subVO.setTotal(0l);
						}
						
						finalCounts.add(subVO);
					}
					LocationWiseBoothDetailsVO subVO = resultMap.get(1l);
					if(subVO != null)
					{
						finalCounts.add(subVO);
					}
				}

		}catch (Exception e) {
			LOG.error("Exception raised in getVillageTotalCommittees()"+e);
		}
		return finalCounts;
	}
	
	public List<CommitteeSummaryVO> getDistrictWiseCommittesSummary(String state,String startDate, String endDate,Long userId,String accessType, Long accessValue,String mandalCheck, String villageCheck,String districtCommCheck,List<Long> committeeSpanTypeIdsList ){
		LOG.debug("Entered Into getDistrictWiseCommittesSummary");
		List<CommitteeSummaryVO> fnlLst = new ArrayList<CommitteeSummaryVO>();
		try{
			Long stateTypeId = 1l;
			List<Long> distIds = new ArrayList<Long>();
			if(userId != null && userId.longValue() != 0L)
			{
				List<Object[]> accessDistrictsList = userDistrictAccessInfoDAO.findByUser(userId);
				if(accessDistrictsList != null && accessDistrictsList.size()>0)
				{
					for (Object[] districtId : accessDistrictsList) {
						CommitteeSummaryVO cv = new CommitteeSummaryVO();
						cv.setDistrictId(Long.valueOf(districtId[0].toString()));
						cv.setDistrictName(districtId[1].toString());
						fnlLst.add(cv);
						
						distIds.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
					}
					
					if(fnlLst != null && distIds != null && distIds.contains(11L))
					{
						CommitteeSummaryVO vo = fnlLst.get(0);
						vo.setAccessState("AP");
					}
					if(fnlLst != null && distIds != null && distIds.contains(1L))
					{
						CommitteeSummaryVO vo = fnlLst.get(0);
						vo.setAccessState("TG");
					}
				}
			}
			
			if(distIds == null || distIds.size() == 0)
			{
				if(state.equalsIgnoreCase("TS")){
					stateTypeId = 2l;
				}
				
				List<Object[]> districtsList = districtDAO.getDistrictIdAndNameByStateForStateTypeId(1l, stateTypeId);
				
				
				if(districtsList!=null && districtsList.size()>0){
					for(Object[] obj:districtsList){
						CommitteeSummaryVO cv = new CommitteeSummaryVO();
						cv.setDistrictId(Long.valueOf(obj[0].toString()));
						cv.setDistrictName(obj[1].toString());
						
						distIds.add(Long.valueOf(obj[0].toString()));
						fnlLst.add(cv);
					}
					
					CommitteeSummaryVO vo = fnlLst.get(0);
					vo.setAccessState("ALL");
					
					List<Object[]> newDistrictsList = districtDAO.getNewDistrictForState(stateTypeId);
					
					if(newDistrictsList!=null && newDistrictsList.size()>0){
						for(Object[] obj:newDistrictsList){
							CommitteeSummaryVO cv = new CommitteeSummaryVO();
							cv.setDistrictId(Long.valueOf(obj[0].toString()));
							cv.setDistrictName(obj[1].toString());
							
							distIds.add(Long.valueOf(obj[0].toString()));
							fnlLst.add(cv);
						}
					}
				}
			}
			
			SimpleDateFormat format =  new SimpleDateFormat("MM/dd/yyyy");
			
			Date stDate = (Date)format.parse(startDate);
			Date edDate = (Date)format.parse(endDate);
			
			List<Long> mandalMunciDivisionIds = new ArrayList<Long>();
			mandalMunciDivisionIds.add(5l);
			mandalMunciDivisionIds.add(7l);
			mandalMunciDivisionIds.add(9l);
			if(mandalCheck.equalsIgnoreCase("true")){
			List<Object[]> memResLst = tdpCommitteeMemberDAO.membersCountDistrictWise(mandalMunciDivisionIds, stDate, edDate, distIds,committeeSpanTypeIdsList);
			List<Object[]> ttlList = tdpCommitteeDAO.getCommitteesCountByDistrictIdAndLevel(distIds, mandalMunciDivisionIds,committeeSpanTypeIdsList);
			pushResultDistrictWiseMemsCount("munci", memResLst, fnlLst);
			pushTotalCountsForDistrict("munci", ttlList, fnlLst);
			}
			
			List<Long> villageWardIds = new ArrayList<Long>();
			villageWardIds.add(6l);
			villageWardIds.add(8l);
			if(villageCheck.equalsIgnoreCase("true")){
			List<Object[]> memResLstVill = tdpCommitteeMemberDAO.membersCountDistrictWise(villageWardIds, stDate, edDate, distIds,committeeSpanTypeIdsList);
			List<Object[]> ttlListVill = tdpCommitteeDAO.getCommitteesCountByDistrictIdAndLevel(distIds, villageWardIds,committeeSpanTypeIdsList);
			pushResultDistrictWiseMemsCount("village", memResLstVill, fnlLst);
			pushTotalCountsForDistrict("village", ttlListVill, fnlLst);
			}
			
			
			List<Long> districtCommIds = new ArrayList<Long>();
			districtCommIds.add(11l);			
			if(districtCommCheck.equalsIgnoreCase("true")){
			List<Object[]> memResLstVill = tdpCommitteeMemberDAO.membersCountDistrictWise(districtCommIds, stDate, edDate, distIds,committeeSpanTypeIdsList);
			List<Object[]> ttlListVill = tdpCommitteeDAO.getCommitteesCountByDistrictIdAndLevel(distIds, districtCommIds,committeeSpanTypeIdsList);
			pushResultDistrictWiseMemsCount("district", memResLstVill, fnlLst);
			pushTotalCountsForDistrict("district", ttlListVill, fnlLst);
			}
			if(mandalCheck.equalsIgnoreCase("true")){
			List<Object[]> stResLst = tdpCommitteeDAO.committeesCountByDistrict(mandalMunciDivisionIds, stDate, edDate, "started", distIds,committeeSpanTypeIdsList);
			List<Object[]> endResLst = tdpCommitteeDAO.committeesCountByDistrict(mandalMunciDivisionIds, stDate, edDate, "completed", distIds,committeeSpanTypeIdsList);
			pushResultDistrictWise("munci", stResLst, fnlLst, "start");
			pushResultDistrictWise("munci", endResLst, fnlLst, "completed");
			}
			if(villageCheck.equalsIgnoreCase("true")){
			List<Object[]> stResLstVill = tdpCommitteeDAO.committeesCountByDistrict(villageWardIds, stDate, edDate, "started", distIds,committeeSpanTypeIdsList);
			List<Object[]> endResLstVill = tdpCommitteeDAO.committeesCountByDistrict(villageWardIds, stDate, edDate, "completed", distIds,committeeSpanTypeIdsList);
			pushResultDistrictWise("village", stResLstVill, fnlLst, "start");
			pushResultDistrictWise("village", endResLstVill, fnlLst, "completed");		
			}
						
			if(districtCommCheck.equalsIgnoreCase("true")){
			List<Object[]> stResLst = tdpCommitteeDAO.committeesCountByDistrict(districtCommIds, stDate, edDate, "started", distIds,committeeSpanTypeIdsList);
			List<Object[]> endResLst = tdpCommitteeDAO.committeesCountByDistrict(districtCommIds, stDate, edDate, "completed", distIds,committeeSpanTypeIdsList);
			pushResultDistrictWise("district", stResLst, fnlLst, "start");
			pushResultDistrictWise("district", endResLst, fnlLst, "completed");
			}
		
			
			if(fnlLst!=null && fnlLst.size()>0){
				for(CommitteeSummaryVO temp:fnlLst){
				if(mandalCheck.equalsIgnoreCase("true")){	
					if(temp.getTownMandalDivisionVO()!=null){
						Long strt = temp.getTownMandalDivisionVO().getMainStarted();
						Long cmpl = temp.getTownMandalDivisionVO().getMainCompleted();
						
						Long total = temp.getTownMandalDivisionVO().getTotalCommittees();
						if(total==null){
							total = 0l;
						}
						if(strt==null){strt = 0l;}
						/*if(cmpl==null){cmpl = 0l;}
						
						Long total = strt + cmpl;*/
						
						if(total!=0){
							String percentage = (new BigDecimal(strt*(100.0)/total)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
							temp.getTownMandalDivisionVO().setStartPerc(percentage);
						}else{
							temp.getVillageWardVO().setStartPerc("0.0");
						}
					}
				}
				if(villageCheck.equalsIgnoreCase("true")){	
					if(temp.getVillageWardVO()!=null){
						Long strtv = temp.getVillageWardVO().getMainStarted();
						Long cmplv = temp.getVillageWardVO().getMainCompleted();
						
						Long totalv = temp.getVillageWardVO().getTotalCommittees();
						if(totalv==null){
							totalv = 0l;
						}
						
						if(strtv==null){strtv = 0l;}
						/*if(cmplv==null){cmplv = 0l;}
						
						Long totalv = strtv + cmplv;*/
						
						if(totalv!=0){
							//temp.getVillageWardVO().setTotalCommittees(totalv);
							String percentage = (new BigDecimal(strtv*(100.0)/totalv)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
							temp.getVillageWardVO().setStartPerc(percentage);
						}else{
							temp.getVillageWardVO().setStartPerc("0.0");
						}
					}
				}
				
				if(districtCommCheck.equalsIgnoreCase("true")){	
					if(temp.getDistrictCommVO()!=null){
						Long strt = temp.getDistrictCommVO().getMainStarted();
						Long cmpl = temp.getDistrictCommVO().getMainCompleted();
						
						Long total = temp.getDistrictCommVO().getTotalCommittees();
						if(total==null){
							total = 0l;
						}
						if(strt==null){strt = 0l;}
						/*if(cmpl==null){cmpl = 0l;}
						
						Long total = strt + cmpl;*/
						
						if(total!=0){
							String percentage = (new BigDecimal(strt*(100.0)/total)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
							temp.getDistrictCommVO().setStartPerc(percentage);
						}else{
							temp.getDistrictCommVO().setStartPerc("0.0");
						}
					}
				}

				}
			}
			if(villageCheck.equalsIgnoreCase("true") && userId.longValue() == 1 && committeeSpanTypeIdsList.contains(1L)){
				getAllIvrDetailsForCampaind(fnlLst,1l,"District");
			}
			
			if(fnlLst != null && fnlLst.size()>0)
			{
				for (CommitteeSummaryVO summaryVO : fnlLst) 
				{
					CadreIVRVO ivrDetailsVO = summaryVO.getCadreIVRVO();
					
					if(ivrDetailsVO != null)
					{
						Long totalIVRCount = 0L;
						int wardListLength = 0;
						int villageListLength =0;
						List<IvrOptionsVO> villageIVRDetails = ivrDetailsVO.getOptionsList();
						List<IvrOptionsVO> wardIVRDetails = ivrDetailsVO.getOptionsList1();
						if(villageIVRDetails != null && villageIVRDetails.size() > 0)
						villageListLength = villageIVRDetails.size();
						if(wardIVRDetails != null && wardIVRDetails.size() > 0)
						 wardListLength = wardIVRDetails.size();
						
						int maxLenght = villageListLength>wardListLength?  villageListLength : wardListLength;
						
						for (int i = 0; i < maxLenght; i++) 
						{
							IvrOptionsVO villageVO =villageIVRDetails != null ? villageIVRDetails.get(i):null;
							IvrOptionsVO wardVO = wardIVRDetails != null ? wardIVRDetails.get(i): null;
							
							if(villageVO !=null && wardVO != null && villageVO.getName().trim().equalsIgnoreCase(wardVO.getName().trim()))
							{
								Long villageCount = villageVO.getCount() != null ? villageVO.getCount() :0L;
								Long wardCount = wardVO.getCount() != null ? wardVO.getCount():0L;
								Long totalCount = villageCount + wardCount;
								totalIVRCount = totalIVRCount + totalCount;
								
								IvrOptionsVO returnVO = getMatchedVOByIVRStatus(villageIVRDetails,villageVO.getName().trim());
								if(returnVO != null)
								{
									returnVO.setCount(totalCount);
								}
							}
						}
						
						ivrDetailsVO.setTotal(totalIVRCount);
					}
				}
			}
			
		}catch (Exception e) {
			LOG.error("Exception Raised in getDistrictWiseCommittesSummary",e);
		}
		return fnlLst;
	}
	
	public IvrOptionsVO getMatchedVOByIVRStatus(List<IvrOptionsVO> IVRDetailsList,String status)
	{
		IvrOptionsVO returnVO = null;
		try {
			if(IVRDetailsList != null && IVRDetailsList.size()>0)
			{
				for (IvrOptionsVO ivrOptionsVO : IVRDetailsList) 
				{
					if(ivrOptionsVO.getName().trim().equalsIgnoreCase(status.trim()))
					{
						return ivrOptionsVO;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Raised in getMatchedVOByIVRStatus",e);
		}
		
		return returnVO;
	}
	
	public void getCadreCommitteIvrDetails(Map<Long,CadreIVRVO> committeeIvrMap,Long id,Map<Long,String> optionNames,Long levelId)
	{
		try 
		{
			    List<Object[]> ivrResponceDetails = null;
			    if(id == 1)
			    {
			    	if(levelId == 6)
			    	{
			    		ivrResponceDetails = committeIvrDistrictDetailDAO.getDistrictWiseIvrDetails(2l);
			    	}
			    	else
			    	{
			    		ivrResponceDetails = committeIvrDistrictDetailDAO.getDistrictWiseIvrDetails(3l);
			    	}
			    	
			    }
			    else
			    {
			    	if(levelId == 6)
			    	{
			    		ivrResponceDetails = cadreIvrResponseDAO.getCadreCommitteesIvRDetails(id,2l);
			    	}
			    	else
			    	{
			    		ivrResponceDetails = cadreIvrResponseDAO.getCadreCommitteesIvRDetails(id,3l);
			    	}
			    }
				
				List<IvrOptionsVO> ivrOptionsList = null;
				if(ivrResponceDetails != null && ivrResponceDetails.size() > 0)
				{
					for (Object[] ivrCountInfo : ivrResponceDetails)
					{
						if(ivrCountInfo[0] != null)
						{
							CadreIVRVO resultVO = committeeIvrMap.get(Long.valueOf(ivrCountInfo[0].toString().trim() ));
							if(resultVO == null)
							{
								resultVO = new CadreIVRVO();
								resultVO.setId(Long.valueOf(ivrCountInfo[0].toString().trim()));
								resultVO.setName(ivrCountInfo[1] != null ? ivrCountInfo[1].toString():"");
								ivrOptionsList = new ArrayList<IvrOptionsVO>();
								 if(optionNames != null && optionNames.size() > 0)
								  {
									 List<IvrOptionsVO> options = new ArrayList<IvrOptionsVO>();
								    	for (Long optId : optionNames.keySet())
								    	{
								    		IvrOptionsVO optVO = new IvrOptionsVO();
								    		optVO.setId(optId);
								    		optVO.setName(optionNames.get(optId));
								    		optVO.setCount(0l);
								    		optVO.setPerc("0.0");
								    		options.add(optVO);
										}
								    	ivrOptionsList.addAll(options);
								   }
								 if(levelId == 6)
								 {
									 resultVO.setOptionsList(ivrOptionsList);
								 }
								 else
								 {
									 resultVO.setOptionsList1(ivrOptionsList);
								 }
								//resultVO.setOptionsList(ivrOptionsList);
								
								committeeIvrMap.put(Long.valueOf(ivrCountInfo[0].toString().trim()), resultVO);
							}
							if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.NORMAL_CLEARING))
							{
								if(ivrCountInfo[3] != null && optionNames != null && optionNames.size() > 0)
								{
									List<IvrOptionsVO>  optionsList = null;
									 if(levelId == 6)
									 {
										 optionsList = resultVO.getOptionsList();
									 }
									 else
									 {
										 optionsList =  resultVO.getOptionsList1();
									 }
									
									if(optionsList != null && optionsList.size() > 0)
									{
										for (IvrOptionsVO ivrOptionsVO : optionsList)
										{
											if(ivrOptionsVO.getId().longValue() == Long.valueOf(ivrCountInfo[3].toString().trim()))
											{
												ivrOptionsVO.setCount((Long)ivrCountInfo[4]);//count
												resultVO.setTotal(ivrOptionsVO.getCount() + resultVO.getTotal());//total
											}
										}
									}
								}
							}
							else
							{
								if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.USER_BUSY))
								{
									resultVO.setUserBusy(resultVO.getUserBusy() + Long.valueOf(ivrCountInfo[4].toString().trim()));
								}
								else if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.CALL_REJECTED))
								{
									resultVO.setCallRejectedCount(resultVO.getCallRejectedCount() + Long.valueOf(ivrCountInfo[4].toString().trim()));
								}
									
								else if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.NO_ANSWER) || ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.NO_USER_RESPONSE))
								{
									 resultVO.setNoAnswer(resultVO.getNoAnswer() + Long.valueOf(ivrCountInfo[4].toString().trim()));
								}
									
								else if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.SWITCH_CONGESTION) || ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.NORMAL_CIRCUIT_CONGESTION))
								{
									resultVO.setSwitchCongestion(resultVO.getSwitchCongestion() + Long.valueOf(ivrCountInfo[4].toString().trim()));
								}
									
								else if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.NORMAL_TEMPORARY_FAILURE) || ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.DESTINATION_OUT_OF_ORDER) 
										 || ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.NETWORK_OUT_OF_ORDER) || ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.SUBSCRIBER_ABSENT))
								{
									resultVO.setNewtworkError(resultVO.getNewtworkError() + Long.valueOf(ivrCountInfo[4].toString().trim()));
								}
									
								else if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.UNALLOCATED_NUMBER))
								{
									resultVO.setUnallocatedNumbers(resultVO.getUnallocatedNumbers() + Long.valueOf(ivrCountInfo[4].toString().trim()));
								}
										
								else if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.INTERWORKING))
								{
									resultVO.setInterworkingCount(resultVO.getInterworkingCount() + Long.valueOf(ivrCountInfo[4].toString().trim()));
								}
									
								else 
								{
									resultVO.setOtherError(resultVO.getOtherError() + Long.valueOf(ivrCountInfo[4].toString().trim()));
								}
							}
						}
						
					}
					
					
				}
		} 
		catch (Exception e) 
		{
			LOG.error("Exception Raised in getCadreCommitteIvrDetails",e);
		}
	}
	public void pushResultDistrictWiseMemsCount(String type,List<Object[]> memResLst, List<CommitteeSummaryVO> fnlLst){
		if(memResLst!=null && memResLst.size()>0){
			for(Object[] obj:memResLst){
				CommitteeSummaryVO temp = getMatchedDistrict(Long.valueOf(obj[1].toString()), fnlLst);
				if(temp==null){
					temp = new CommitteeSummaryVO();
				}
				
				if(type.equalsIgnoreCase("munci")){
					if(temp.getTownMandalDivisionVO()==null){
						temp.setTownMandalDivisionVO(new CommitteeSummaryVO());
					}
					Long committeeId = Long.valueOf(obj[2].toString());
					if(committeeId.longValue() == 1L)
					{
						temp.getTownMandalDivisionVO().setMembersCount(Long.valueOf(obj[0].toString()));
					}
					else
					{
						Long existingCount = temp.getTownMandalDivisionVO().getAfflMembersCount() != null ? temp.getTownMandalDivisionVO().getAfflMembersCount():0L;
						temp.getTownMandalDivisionVO().setAfflMembersCount(existingCount+Long.valueOf(obj[0].toString()));
					}
					
				}
				else if(type.equalsIgnoreCase("district")){
					if(temp.getDistrictCommVO()==null){
						temp.setDistrictCommVO(new CommitteeSummaryVO());
					}
					if(Long.valueOf(obj[2].toString()).longValue() == 1L){
						temp.getDistrictCommVO().setMembersCount(Long.valueOf(obj[0].toString()));
					}else
					{
						setDistrictCommitteeParameterValues(temp,Long.valueOf(obj[2].toString()),"start", Long.valueOf(obj[0].toString()));
					}
				}
			
				else{
					
					if(temp.getDistrictCommVO()==null){
						temp.setDistrictCommVO(new CommitteeSummaryVO());
					}
					if(Long.valueOf(obj[2].toString()).longValue() == 1L){
						temp.getDistrictCommVO().setMembersCount(Long.valueOf(obj[0].toString()));
					}else
					{
						setDistrictCommitteeParameterValues(temp,Long.valueOf(obj[2].toString()),"start", Long.valueOf(obj[0].toString()));
					}
					
					Long committeeId = Long.valueOf(obj[2].toString());
					if(temp.getVillageWardVO()==null){
						temp.setVillageWardVO(new CommitteeSummaryVO());
					}
					if(committeeId.longValue() == 1L)
					{
						temp.getVillageWardVO().setMembersCount(Long.valueOf(obj[0].toString()));
					}
					else
					{
						Long existingCount = temp.getVillageWardVO().getAfflMembersCount() != null ? temp.getVillageWardVO().getAfflMembersCount():0L;
						temp.getVillageWardVO().setAfflMembersCount(existingCount+Long.valueOf(obj[0].toString()));
					}
					
				}
				
			}
		}
	}
	
	public void pushTotalCountsForDistrict(String type,List<Object[]> memResLst, List<CommitteeSummaryVO> fnlLst){
		if(memResLst!=null && memResLst.size()>0){
			for(Object[] obj:memResLst){
				CommitteeSummaryVO temp = getMatchedDistrict(Long.valueOf(obj[1].toString()), fnlLst);
				if(temp==null){
					temp = new CommitteeSummaryVO();
				}
				
				if(type.equalsIgnoreCase("munci")){
					if(temp.getTownMandalDivisionVO()==null){
						temp.setTownMandalDivisionVO(new CommitteeSummaryVO());
					}
					
					
					Long committeeId = Long.valueOf(obj[2].toString());
					if(committeeId.longValue() == 1L)
					{
						temp.getTownMandalDivisionVO().setTotalCommittees(Long.valueOf(obj[0].toString()));
					}
					else
					{
						Long existingCount = temp.getTownMandalDivisionVO().getTotalAffilatedCommittees() != null ? temp.getTownMandalDivisionVO().getTotalAffilatedCommittees():0L;
						temp.getTownMandalDivisionVO().setTotalAffilatedCommittees(existingCount+Long.valueOf(obj[0].toString()));
					}
				}else if(type.equalsIgnoreCase("district")){
					if(temp.getDistrictCommVO()==null){
						temp.setDistrictCommVO(new CommitteeSummaryVO());
					}
					temp.getDistrictCommVO().setTotalCommittees(Long.valueOf(obj[0].toString()));
				}else{
					if(temp.getVillageWardVO()==null){
						temp.setVillageWardVO(new CommitteeSummaryVO());
					}  
					Long committeeId = Long.valueOf(obj[2].toString());
					if(committeeId.longValue() == 1L)
					{
						temp.getVillageWardVO().setTotalCommittees(Long.valueOf(obj[0].toString()));
					}
					else
					{
						Long existingCount = temp.getVillageWardVO().getTotalAffilatedCommittees() != null ? temp.getVillageWardVO().getTotalAffilatedCommittees():0L;
						temp.getVillageWardVO().setTotalAffilatedCommittees(existingCount+Long.valueOf(obj[0].toString()));
					}
					
				}
				
			}
		}
	}
	
	public void setDistrictCommitteeParameterValues(CommitteeSummaryVO temp,Long committeeId,String resType, Long count)
	{
		try {
			boolean isConsidered = false;
			if(committeeId.longValue() == 2l){
				isConsidered = true;
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getYouvathaStarted() != null)
						temp.getDistrictCommVO().setYouvathaStarted(temp.getDistrictCommVO().getYouvathaStarted()+1L);
					else
						temp.getDistrictCommVO().setYouvathaStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getYouvathaCmpltd() != null)
						temp.getDistrictCommVO().setYouvathaCmpltd(temp.getDistrictCommVO().getYouvathaCmpltd()+1L);
					else
						temp.getDistrictCommVO().setYouvathaCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 3l){
				isConsidered = true;
				
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getMahilaStarted() != null)
						temp.getDistrictCommVO().setMahilaStarted(temp.getDistrictCommVO().getMahilaStarted()+1L);
					else
						temp.getDistrictCommVO().setMahilaStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getMahilaCmpltd() != null)
						temp.getDistrictCommVO().setMahilaCmpltd(temp.getDistrictCommVO().getMahilaCmpltd()+1L);
					else
						temp.getDistrictCommVO().setMahilaCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 4l){
				isConsidered = true;
				
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getRythuStarted() != null)
						temp.getDistrictCommVO().setRythuStarted(temp.getDistrictCommVO().getRythuStarted()+1L);
					else
						temp.getDistrictCommVO().setRythuStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getRythuCmpltd() != null)
						temp.getDistrictCommVO().setRythuCmpltd(temp.getDistrictCommVO().getRythuCmpltd()+1L);
					else
						temp.getDistrictCommVO().setRythuCmpltd(1L);
				}
				
			}
			else if(committeeId.longValue() == 5l){
				
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getTntucStarted() != null)
						temp.getDistrictCommVO().setTntucStarted(temp.getDistrictCommVO().getTntucStarted()+1L);
					else
						temp.getDistrictCommVO().setTntucStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getTntucCmpltd() != null)
						temp.getDistrictCommVO().setTntucCmpltd(temp.getDistrictCommVO().getTntucCmpltd()+1L);
					else
						temp.getDistrictCommVO().setTntucCmpltd(1L);
				}
				
			}
			else if(committeeId.longValue() == 6l){
				

				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getBcCellStarted() != null)
						temp.getDistrictCommVO().setBcCellStarted(temp.getDistrictCommVO().getBcCellStarted()+1L);
					else
						temp.getDistrictCommVO().setBcCellStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getBcCellCmpltd() != null)
						temp.getDistrictCommVO().setBcCellCmpltd(temp.getDistrictCommVO().getBcCellCmpltd()+1L);
					else
						temp.getDistrictCommVO().setBcCellCmpltd(1L);
				}
				
			}
			else if(committeeId.longValue() == 7l){
				
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getScCellStarted() != null)
						temp.getDistrictCommVO().setScCellStarted(temp.getDistrictCommVO().getScCellStarted()+1L);
					else
						temp.getDistrictCommVO().setScCellStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getScCellCmpltd() != null)
						temp.getDistrictCommVO().setScCellCmpltd(temp.getDistrictCommVO().getScCellCmpltd()+1L);
					else
						temp.getDistrictCommVO().setScCellCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 8l){
				
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getStCellStarted() != null)
						temp.getDistrictCommVO().setStCellStarted(temp.getDistrictCommVO().getStCellStarted()+1L);
					else
						temp.getDistrictCommVO().setStCellStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getStCellCmpltd() != null)
						temp.getDistrictCommVO().setStCellCmpltd(temp.getDistrictCommVO().getStCellCmpltd()+1L);
					else
						temp.getDistrictCommVO().setStCellCmpltd(1L);
				}
				
			}
			else if(committeeId.longValue() == 9l){
				
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getMinorityStarted() != null)
						temp.getDistrictCommVO().setMinorityStarted(temp.getDistrictCommVO().getMinorityStarted()+1L);
					else
						temp.getDistrictCommVO().setMinorityStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getMinorityCmpltd() != null)
						temp.getDistrictCommVO().setMinorityCmpltd(temp.getDistrictCommVO().getMinorityCmpltd()+1L);
					else
						temp.getDistrictCommVO().setMinorityCmpltd(1L);
				}
				
			}
			else if(committeeId.longValue() == 10l){
				
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getLegalCellStarted() != null)
						temp.getDistrictCommVO().setLegalCellStarted(temp.getDistrictCommVO().getLegalCellStarted()+1L);
					else
						temp.getDistrictCommVO().setLegalCellStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getLegalCmpltd() != null)
						temp.getDistrictCommVO().setLegalCmpltd(temp.getDistrictCommVO().getLegalCmpltd()+1L);
					else
						temp.getDistrictCommVO().setLegalCmpltd(1L);
				}
				
			}
			else if(committeeId.longValue() == 11l){
				
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getTnsfStarted() != null)
						temp.getDistrictCommVO().setTnsfStarted(temp.getDistrictCommVO().getTnsfStarted()+1L);
					else
						temp.getDistrictCommVO().setTnsfStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getTnsfCmpltd() != null)
						temp.getDistrictCommVO().setTnsfCmpltd(temp.getDistrictCommVO().getTnsfCmpltd()+1L);
					else
						temp.getDistrictCommVO().setTnsfCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 12l){
				
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getCommercialStarted() != null)
						temp.getDistrictCommVO().setCommercialStarted(temp.getDistrictCommVO().getCommercialStarted()+1L);
					else
						temp.getDistrictCommVO().setCommercialStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getCommercialCmpltd() != null)
						temp.getDistrictCommVO().setCommercialCmpltd(temp.getDistrictCommVO().getCommercialCmpltd()+1L);
					else
						temp.getDistrictCommVO().setCommercialCmpltd(1L);
				}
				
			}
			else if(committeeId.longValue() == 13l){
				
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getCulturalStarted() != null)
						temp.getDistrictCommVO().setCulturalStarted(temp.getDistrictCommVO().getCulturalStarted()+1L);
					else
						temp.getDistrictCommVO().setCulturalStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getCulturalCmpltd() != null)
						temp.getDistrictCommVO().setCulturalCmpltd(temp.getDistrictCommVO().getCulturalCmpltd()+1L);
					else
						temp.getDistrictCommVO().setCulturalCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 14l){
				
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getTnusStarted() != null)
						temp.getDistrictCommVO().setTnusStarted(temp.getDistrictCommVO().getTnusStarted()+1L);
					else
						temp.getDistrictCommVO().setTnusStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getTnusCmpltd() != null)
						temp.getDistrictCommVO().setTnusCmpltd(temp.getDistrictCommVO().getTnusCmpltd()+1L);
					else
						temp.getDistrictCommVO().setTnusCmpltd(1L);
				}
				
			}
			else if(committeeId.longValue() == 15l){
				
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getTsnvStarted() != null)
						temp.getDistrictCommVO().setTsnvStarted(temp.getDistrictCommVO().getTsnvStarted()+1L);
					else
						temp.getDistrictCommVO().setTsnvStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getTsnvCmpltd() != null)
						temp.getDistrictCommVO().setTsnvCmpltd(temp.getDistrictCommVO().getTsnvCmpltd()+1L);
					else
						temp.getDistrictCommVO().setTsnvCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 16l){
				
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getDoctorStarted() != null)
						temp.getDistrictCommVO().setDoctorStarted(temp.getDistrictCommVO().getDoctorStarted()+1L);
					else
						temp.getDistrictCommVO().setDoctorStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getDoctorCmpltd() != null)
						temp.getDistrictCommVO().setDoctorCmpltd(temp.getDistrictCommVO().getDoctorCmpltd()+1L);
					else
						temp.getDistrictCommVO().setDoctorCmpltd(1L);
				}
				
			}
			else if(committeeId.longValue() == 17l){
				
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getTradeStarted() != null)
						temp.getDistrictCommVO().setTradeStarted(temp.getDistrictCommVO().getTradeStarted()+1L);
					else
						temp.getDistrictCommVO().setTradeStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getTradeCmpltd() != null)
						temp.getDistrictCommVO().setTradeCmpltd(temp.getDistrictCommVO().getTradeCmpltd()+1L);
					else
						temp.getDistrictCommVO().setTradeCmpltd(1L);
				}
				
			}
			else if(committeeId.longValue() == 18l){
				
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getCristianStarted() != null)
						temp.getDistrictCommVO().setCristianStarted(temp.getDistrictCommVO().getCristianStarted()+1L);
					else
						temp.getDistrictCommVO().setCristianStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getCristianCmpltd() != null)
						temp.getDistrictCommVO().setCristianCmpltd(temp.getDistrictCommVO().getCristianCmpltd()+1L);
					else
						temp.getDistrictCommVO().setCristianCmpltd(1L);
				}
				
			}
			else if(committeeId.longValue() == 19l){
				
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getRakshaVedikaStarted() != null)
						temp.getDistrictCommVO().setRakshaVedikaStarted(temp.getDistrictCommVO().getRakshaVedikaStarted()+1L);
					else
						temp.getDistrictCommVO().setRakshaVedikaStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getRakshaVedikaCmpltd() != null)
						temp.getDistrictCommVO().setRakshaVedikaCmpltd(temp.getDistrictCommVO().getRakshaVedikaCmpltd()+1L);
					else
						temp.getDistrictCommVO().setRakshaVedikaCmpltd(1L);
				}
				
			}
			
			else if(committeeId.longValue() == 20l){
				
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getKalluGeethaStarted() != null)
						temp.getDistrictCommVO().setKalluGeethaStarted(temp.getDistrictCommVO().getKalluGeethaStarted()+1L);
					else
						temp.getDistrictCommVO().setKalluGeethaStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getKalluGeethaCmpltd() != null)
						temp.getDistrictCommVO().setKalluGeethaCmpltd(temp.getDistrictCommVO().getKalluGeethaCmpltd()+1L);
					else
						temp.getDistrictCommVO().setKalluGeethaCmpltd(1L);
				}
				
			}
			else if(committeeId.longValue() == 21l){
				
				if(resType.equalsIgnoreCase("start")){
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getChenethaStarted() != null)
						temp.getDistrictCommVO().setChenethaStarted(temp.getDistrictCommVO().getChenethaStarted()+1L);
					else
						temp.getDistrictCommVO().setChenethaStarted(1L);
				}else{
					
					if(temp.getDistrictCommVO() != null && temp.getDistrictCommVO().getChenethaCmpltd() != null)
						temp.getDistrictCommVO().setChenethaCmpltd(temp.getDistrictCommVO().getChenethaCmpltd()+1L);
					else
						temp.getDistrictCommVO().setChenethaCmpltd(1L);
				}
				
			}
			
			if(!isConsidered)
			{
				Long totalCoutn = temp.getDistrictCommVO().getOthersStarted() != null?temp.getDistrictCommVO().getOthersStarted():0L;
				totalCoutn = totalCoutn+1;
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setOthersStarted(totalCoutn);
				}else{
					temp.getDistrictCommVO().setOthersCmpltd(count);
				}
			}		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void setCommitteeParameterValues(CommitteeSummaryVO temp,Long committeeId,String resType, Long count)
	{
		try {
			boolean isConsidered = false;
			if(committeeId.longValue() == 2l){
				isConsidered = true;
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setYouvathaStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setYouvathaCmpltd(count);
				}
			}
			else if(committeeId.longValue() == 3l){
				isConsidered = true;
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setMahilaStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setMahilaCmpltd(count);
				}
			}
			else if(committeeId.longValue() == 4l){
				isConsidered = true;
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setRythuStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setRythuCmpltd(count);
				}
			}
			else if(committeeId.longValue() == 5l){
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setTntucStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setTntucCmpltd(count);
				}
			}
			else if(committeeId.longValue() == 6l){
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setBcCellStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setBcCellCmpltd(count);
				}
			}
			else if(committeeId.longValue() == 7l){
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setScCellStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setScCellCmpltd(count);
				}
			}
			else if(committeeId.longValue() == 8l){
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setStCellStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setStCellCmpltd(count);
				}
			}
			else if(committeeId.longValue() == 9l){
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setMinorityStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setMinorityCmpltd(count);
				}
			}
			else if(committeeId.longValue() == 10l){
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setLegalCellStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setLegalCmpltd(count);
				}
			}
			else if(committeeId.longValue() == 11l){
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setTnsfStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setTnsfCmpltd(count);
				}
			}
			else if(committeeId.longValue() == 12l){
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setCommercialStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setCommercialCmpltd(count);
				}
			}
			else if(committeeId.longValue() == 13l){
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setCulturalStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setCulturalCmpltd(count);
				}
			}
			else if(committeeId.longValue() == 14l){
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setTnusStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setTnusCmpltd(count);
				}
			}
			else if(committeeId.longValue() == 15l){
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setTsnvStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setTsnvCmpltd(count);
				}
			}
			else if(committeeId.longValue() == 16l){
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setDoctorStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setDoctorCmpltd(count);
				}
			}
			else if(committeeId.longValue() == 17l){
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setTradeStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setTradeCmpltd(count);
				}
			}
			else if(committeeId.longValue() == 18l){
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setCristianStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setCristianCmpltd(count);
				}
			}
			else if(committeeId.longValue() == 19l){
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setRakshaVedikaStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setRakshaVedikaCmpltd(count);
				}
			}
			
			else if(committeeId.longValue() == 20l){
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setKalluGeethaStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setKalluGeethaCmpltd(count);
				}
			}
			else if(committeeId.longValue() == 21l){
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setChenethaStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setChenethaCmpltd(count);
				}
			}
			
			if(!isConsidered)
			{
				if(resType.equalsIgnoreCase("start")){
					temp.getTownMandalDivisionVO().setOthersStarted(count);
				}else{
					temp.getTownMandalDivisionVO().setOthersCmpltd(count);
				}
			}		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void pushResultDistrictWise(String type,List<Object[]> memResLst, List<CommitteeSummaryVO> fnlLst, String resType){
		if(memResLst!=null && memResLst.size()>0){
			for(Object[] obj:memResLst){
				CommitteeSummaryVO temp = getMatchedDistrict(Long.valueOf(obj[2].toString()), fnlLst);
				
				if(temp==null){
					temp = new CommitteeSummaryVO();
				}
				
				
				if(type.equalsIgnoreCase("munci")){
					if(temp.getTownMandalDivisionVO()==null){
						temp.setTownMandalDivisionVO(new CommitteeSummaryVO());
					}
					if(Long.valueOf(obj[1].toString()).equals(1l)){
						if(resType.equalsIgnoreCase("start")){	
							temp.getTownMandalDivisionVO().setMainStarted(Long.valueOf(obj[0].toString()));
						}else{
							temp.getTownMandalDivisionVO().setMainCompleted(Long.valueOf(obj[0].toString()));
						}
					}
					else{
						if(resType.equalsIgnoreCase("start")){
							Long existingCount = temp.getTownMandalDivisionVO().getAfflStarted() != null ? temp.getTownMandalDivisionVO().getAfflStarted():0L;
							temp.getTownMandalDivisionVO().setAfflStarted(existingCount+Long.valueOf(obj[0].toString()));							
						}else{
							Long existingCount = temp.getTownMandalDivisionVO().getAfflCompleted() != null ? temp.getTownMandalDivisionVO().getAfflCompleted():0L;
							temp.getTownMandalDivisionVO().setAfflCompleted(existingCount+Long.valueOf(obj[0].toString()));
						}
						
						setCommitteeParameterValues(temp,Long.valueOf(obj[3].toString()),resType, Long.valueOf(obj[0].toString()));
						
						/*boolean isConsidered = false;
						if(Long.valueOf(obj[3].toString()).equals(2l)){
							isConsidered = true;
							if(resType.equalsIgnoreCase("start")){
								temp.getTownMandalDivisionVO().setYouvathaStarted(Long.valueOf(obj[0].toString()));
							}else{
								temp.getTownMandalDivisionVO().setYouvathaCmpltd(Long.valueOf(obj[0].toString()));
							}
						}
						if(Long.valueOf(obj[3].toString()).equals(3l)){
							isConsidered = true;
							if(resType.equalsIgnoreCase("start")){
								temp.getTownMandalDivisionVO().setMahilaStarted(Long.valueOf(obj[0].toString()));
							}else{
								temp.getTownMandalDivisionVO().setMahilaCmpltd(Long.valueOf(obj[0].toString()));
							}
						}
						if(Long.valueOf(obj[3].toString()).equals(4l)){
							isConsidered = true;
							if(resType.equalsIgnoreCase("start")){
								temp.getTownMandalDivisionVO().setRythuStarted(Long.valueOf(obj[0].toString()));
							}else{
								temp.getTownMandalDivisionVO().setRythuCmpltd(Long.valueOf(obj[0].toString()));
							}
						}
						if(Long.valueOf(obj[3].toString()).equals(5l)){
							isConsidered = true;
							if(resType.equalsIgnoreCase("start")){
								temp.getTownMandalDivisionVO().setRythuStarted(Long.valueOf(obj[0].toString()));
							}else{
								temp.getTownMandalDivisionVO().setRythuCmpltd(Long.valueOf(obj[0].toString()));
							}
						}
						if(!isConsidered)
						{
							if(resType.equalsIgnoreCase("start")){
								temp.getTownMandalDivisionVO().setOthersStarted(Long.valueOf(obj[0].toString()));
							}else{
								temp.getTownMandalDivisionVO().setOthersCmpltd(Long.valueOf(obj[0].toString()));
							}
						}*/
					}
					
					
					
				}
				else if(type.equalsIgnoreCase("district")){

					if(temp.getDistrictCommVO()==null){
						temp.setDistrictCommVO(new CommitteeSummaryVO());
					}
					if(Long.valueOf(obj[1].toString()).equals(1l)){
						if(resType.equalsIgnoreCase("start")){	
							temp.getDistrictCommVO().setMainStarted(Long.valueOf(obj[0].toString()));
						}else{
							temp.getDistrictCommVO().setMainCompleted(Long.valueOf(obj[0].toString()));
						}
					}
					else{
						if(resType.equalsIgnoreCase("start")){
							temp.getDistrictCommVO().setAfflStarted(Long.valueOf(obj[0].toString()));
						}else{
							temp.getDistrictCommVO().setAfflCompleted(Long.valueOf(obj[0].toString()));
						}
						
						//setDistrictCommitteeParameterValues(temp,Long.valueOf(obj[3].toString()),resType, Long.valueOf(obj[0].toString()));
						
						/*boolean isConsidered = false;
						if(Long.valueOf(obj[3].toString()).equals(2l)){
							isConsidered = true;
							if(resType.equalsIgnoreCase("start")){
								temp.getTownMandalDivisionVO().setYouvathaStarted(Long.valueOf(obj[0].toString()));
							}else{
								temp.getTownMandalDivisionVO().setYouvathaCmpltd(Long.valueOf(obj[0].toString()));
							}
						}
						if(Long.valueOf(obj[3].toString()).equals(3l)){
							isConsidered = true;
							if(resType.equalsIgnoreCase("start")){
								temp.getTownMandalDivisionVO().setMahilaStarted(Long.valueOf(obj[0].toString()));
							}else{
								temp.getTownMandalDivisionVO().setMahilaCmpltd(Long.valueOf(obj[0].toString()));
							}
						}
						if(Long.valueOf(obj[3].toString()).equals(4l)){
							isConsidered = true;
							if(resType.equalsIgnoreCase("start")){
								temp.getTownMandalDivisionVO().setRythuStarted(Long.valueOf(obj[0].toString()));
							}else{
								temp.getTownMandalDivisionVO().setRythuCmpltd(Long.valueOf(obj[0].toString()));
							}
						}
						if(Long.valueOf(obj[3].toString()).equals(5l)){
							isConsidered = true;
							if(resType.equalsIgnoreCase("start")){
								temp.getTownMandalDivisionVO().setRythuStarted(Long.valueOf(obj[0].toString()));
							}else{
								temp.getTownMandalDivisionVO().setRythuCmpltd(Long.valueOf(obj[0].toString()));
							}
						}
						if(!isConsidered)
						{
							if(resType.equalsIgnoreCase("start")){
								temp.getTownMandalDivisionVO().setOthersStarted(Long.valueOf(obj[0].toString()));
							}else{
								temp.getTownMandalDivisionVO().setOthersCmpltd(Long.valueOf(obj[0].toString()));
							}
						}*/
					}
					
					
					
					
					
				}else{
					if(temp.getVillageWardVO()==null){
						temp.setVillageWardVO(new CommitteeSummaryVO());
					}
					if(Long.valueOf(obj[1].toString()).equals(1l)){
						if(resType.equalsIgnoreCase("start")){	   
							temp.getVillageWardVO().setMainStarted(Long.valueOf(obj[0].toString()));
						}else{
							temp.getVillageWardVO().setMainCompleted(Long.valueOf(obj[0].toString()));
						}
					}
					else{
						if(resType.equalsIgnoreCase("start")){	
							Long existingCount = temp.getVillageWardVO().getAfflStarted() != null ? temp.getVillageWardVO().getAfflStarted():0L;
							temp.getVillageWardVO().setAfflStarted(existingCount+Long.valueOf(obj[0].toString()));
						}else{
							Long existingCount = temp.getVillageWardVO().getAfflCompleted() != null ? temp.getVillageWardVO().getAfflCompleted():0L;
							temp.getVillageWardVO().setAfflCompleted(existingCount+Long.valueOf(obj[0].toString()));
						}
						
						setCommitteeParameterValues(temp,Long.valueOf(obj[3].toString()),resType, Long.valueOf(obj[0].toString()));
					}
				}
				
			}
		}
	}
	
	
	public CommitteeSummaryVO getMatchedDistrict(Long districtId, List<CommitteeSummaryVO> list){
		if(districtId!=null && list!=null && list.size()>0){
			for(CommitteeSummaryVO obj:list){
				if(obj.getDistrictId().equals(districtId)){
					return obj;
				}
			}
		}
		return null;
	}
	
	public List<CadreCommitteeReportVO> getStartedAffliCommitteesCountByLocation(String state,List<Long> levelIds,String startDateStr,String endDateStr,String accessType,Long accessValue,Long userId,String committeeType,List<Long> committeeSpanTypeIdsLsit){
		List<CadreCommitteeReportVO> resultList= new ArrayList<CadreCommitteeReportVO>();
		try{
			
			List<Long> districtIds = new ArrayList<Long>();
							List<Long> assemblyIds = new ArrayList<Long>();
							List<Long> locationLevelValues = new ArrayList<Long>();
							
							if(accessType.trim().equalsIgnoreCase("MP"))
							{
								districtIds = null;
								List<Object[]> accessConstituencyList = userConstituencyAccessInfoDAO.findByElectionScopeUser(1L,userId);
								
								List<Long> parliamentsIds = new ArrayList<Long>();
								if(accessConstituencyList != null && accessConstituencyList.size()>0)
								{
									for (Object[] parliament : accessConstituencyList) {
										parliamentsIds.add(parliament[0] != null ? Long.valueOf(parliament[0].toString().trim()):0L);
									}
								}
								else
								{
									parliamentsIds.add(accessValue);
								}
								
								if(parliamentsIds != null && parliamentsIds.size()>0)
								{
									assemblyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliamentList(parliamentsIds);
								}
							
								if(levelIds.contains(5L)) // mandal
								{
									List<Object[]> tehsilDetails = tehsilDAO.getTehsilsByConstituencyIdsListAndPublicationDateId(assemblyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
									if(tehsilDetails != null && tehsilDetails.size() > 0)
									{
										for (Object[] tehsil : tehsilDetails) {
											locationLevelValues.add(tehsil[0] != null ? Long.valueOf(tehsil[0].toString().trim()):0L);						
										}
									}
								}
								if(levelIds.contains(7L)) //localelectionbody 
								{
									List<Object[]> tehsilDetails = tehsilDAO.getAllLocalElecBodyListByConstituencyIdsListAndPublicationDateId(assemblyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
									if(tehsilDetails != null && tehsilDetails.size() > 0)
									{
										for (Object[] tehsil : tehsilDetails) {
											locationLevelValues.add(tehsil[0] != null ? Long.valueOf(tehsil[0].toString().trim()):0L);						
										}
									}
								}
								if(levelIds.contains(9L)) //DIVISION 
								{
								}
							}
							else
							{
								List<Object[]> accessDistrictsList = userDistrictAccessInfoDAO.findByUser(userId);
								if(accessDistrictsList != null && accessDistrictsList.size()>0)
								{
									for (Object[] districtId : accessDistrictsList) {
										districtIds.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
									}
									
								}
							}
			Date startDate = null;
			Date endDate = null;
			 if(startDateStr !=null && endDateStr !=null){
				 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				 startDate = sdf.parse(startDateStr);
				 endDate =sdf.parse(endDateStr);
				 
			 }
			 Long accessTypelist = null;
			 if(accessValue == null){
				 accessTypelist=Long.valueOf(accessType);
				 List<Object[]> activityLocationValue = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersIdForOrganization(userId); 
		          if(activityLocationValue != null && activityLocationValue.size()>0 ){
		        	   for(Object[] param : activityLocationValue){
		        		   if(commonMethodsUtilService.getLongValueForObject(param[0]) == 5l){
		        			   assemblyIds.add(commonMethodsUtilService.getLongValueForObject(param[1]));
		        		   }else if(commonMethodsUtilService.getLongValueForObject(param[0]) == 3l){
		        			   districtIds.add(commonMethodsUtilService.getLongValueForObject(param[1]));
		        		   }
		        		   }
		        		   
		        	   }
		          } 
			List<Object[]>  startedCount = null;
			if(committeeType.equalsIgnoreCase("started")){
				startedCount=tdpCommitteeDAO.getStartedAffliCommitteesCountByLocation(state, levelIds,startDate,endDate,districtIds,assemblyIds,locationLevelValues,committeeSpanTypeIdsLsit);
			}
			else
			{
				startedCount=tdpCommitteeDAO.getCompletedAffliCommitteesCountByLocation(state, levelIds,startDate,endDate,districtIds,assemblyIds,locationLevelValues,committeeSpanTypeIdsLsit);
			}
			
			if(startedCount != null && startedCount.size() > 0){
				for (Object[] objects : startedCount) {		
						CadreCommitteeReportVO vo = new CadreCommitteeReportVO();
						vo.setAfflCommittees(Long.valueOf(objects[0].toString()));
						vo.setName(objects[1].toString());	
						vo.setId(Long.valueOf(objects[2].toString()));
						resultList.add(vo);
				}
			}
		}catch (Exception e) {
			LOG.error("Exception raised in getVillageTotalCommittees()"+e);
		}
		return resultList;
	}
	
	
	public List<CadreCommitteeReportVO> getMembersRangeCountByLocation(String state,List<Long> levelIds,Long committeeId,String startDateStr,String endDateStr,String accessType,Long accessValue,Long userId,String committeeType,List<Long> committeeSpanTypeIdsLsit){
		List<CadreCommitteeReportVO> resultList= new ArrayList<CadreCommitteeReportVO>();
		try{
			
			CadreCommitteeReportVO vo = new CadreCommitteeReportVO();

			List<Long> districtIds = new ArrayList<Long>();
							List<Long> assemblyIds = new ArrayList<Long>();
							List<Long> locationLevelValues = new ArrayList<Long>();
							
							vo.setAccessState("ALL");
							if(accessType.trim().equalsIgnoreCase("MP"))
							{
								vo.setAccessState(delimitationConstituencyAssemblyDetailsDAO.get(accessValue).getConstituency().getName()+" "+"Parliament");
								districtIds = null;
								List<Object[]> accessConstituencyList = userConstituencyAccessInfoDAO.findByElectionScopeUser(1L,userId);
								
								List<Long> parliamentsIds = new ArrayList<Long>();
								if(accessConstituencyList != null && accessConstituencyList.size()>0)
								{
									for (Object[] parliament : accessConstituencyList) {
										parliamentsIds.add(parliament[0] != null ? Long.valueOf(parliament[0].toString().trim()):0L);
									}
								}
								else
								{
									parliamentsIds.add(accessValue);
								}
								
								if(parliamentsIds != null && parliamentsIds.size()>0)
								{
									assemblyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliamentList(parliamentsIds);
								}
							
								if(levelIds.contains(5L)) // mandal
								{
									List<Object[]> tehsilDetails = tehsilDAO.getTehsilsByConstituencyIdsListAndPublicationDateId(assemblyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
									if(tehsilDetails != null && tehsilDetails.size() > 0)
									{
										for (Object[] tehsil : tehsilDetails) {
											locationLevelValues.add(tehsil[0] != null ? Long.valueOf(tehsil[0].toString().trim()):0L);						
										}
									}
								}
								if(levelIds.contains(7L)) //localelectionbody 
								{
									List<Object[]> tehsilDetails = tehsilDAO.getAllLocalElecBodyListByConstituencyIdsListAndPublicationDateId(assemblyIds,IConstants.VOTER_DATA_PUBLICATION_ID);
									if(tehsilDetails != null && tehsilDetails.size() > 0)
									{
										for (Object[] tehsil : tehsilDetails) {
											locationLevelValues.add(tehsil[0] != null ? Long.valueOf(tehsil[0].toString().trim()):0L);						
										}
									}
								}
								if(levelIds.contains(9L)) //DIVISION 
								{
								}
							}
							else
							{
								List<Object[]> accessDistrictsList = userDistrictAccessInfoDAO.findByUser(userId);
								if(accessDistrictsList != null && accessDistrictsList.size()>0)
								{
									for (Object[] districtId : accessDistrictsList) {
										districtIds.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
									}
									
									if(districtIds != null && districtIds.size() == 1)
									{
										Long districtId = districtIds.get(0).longValue();
										if(districtId != 0L)
											vo.setAccessState(districtDAO.get(districtId).getDistrictName()+" District");
									}									
									else if(districtIds != null && districtIds.contains(11L))
									{
										vo.setAccessState("AP");
									}
									else if(districtIds != null && districtIds.contains(1L))
									{
										vo.setAccessState("TG");
									}
								}
							}
			Date startDate = null;
			Date endDate = null;
			 if(startDateStr !=null && endDateStr !=null){
				 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				 startDate = sdf.parse(startDateStr);
				 endDate =sdf.parse(endDateStr);
				 
			 }
			 Long accessTypelist = null;
			 if(accessValue == null){
				 accessTypelist=Long.valueOf(accessType);
				 List<Object[]> activityLocationValue = activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersIdForOrganization(userId); 
		          if(activityLocationValue != null && activityLocationValue.size()>0 ){
		        	   for(Object[] param : activityLocationValue){
		        		   if(commonMethodsUtilService.getLongValueForObject(param[0]) == 5l){
		        			   assemblyIds.add(commonMethodsUtilService.getLongValueForObject(param[1]));
		        		   }else if(commonMethodsUtilService.getLongValueForObject(param[0]) == 3l){
		        			   districtIds.add(commonMethodsUtilService.getLongValueForObject(param[1]));
		        		   }
		        		   }
		        		   
		        	   }
		          } 
			List<Object[]> membersCount = null; 
			if(committeeType.equalsIgnoreCase("started")){
				membersCount = tdpCommitteeMemberDAO.getStartedCommitteesMembersCountByLocation(state, levelIds,committeeId,startDate,endDate,districtIds,assemblyIds,locationLevelValues,committeeSpanTypeIdsLsit);
			}else{
				membersCount = tdpCommitteeMemberDAO.getMembersCountInCommitteeByLocation(state, levelIds,committeeId,startDate,endDate,districtIds,assemblyIds,locationLevelValues,committeeSpanTypeIdsLsit);
			}
			
			if(membersCount != null && membersCount.size() > 0){
				for (Object[] objects : membersCount) {		
						
						
						if(Long.valueOf(objects[0].toString()) == 1L)
						{
							vo.setMembersCount(vo.getMembersCount() + 1);
						}
						else if(Long.valueOf(objects[0].toString()) > 1L && Long.valueOf(objects[0].toString()) <= 4L)
						{
							vo.setMembersCount1(vo.getMembersCount1() + 1);
						}
						else if(Long.valueOf(objects[0].toString()) >= 5L && Long.valueOf(objects[0].toString()) <= 6L)
						{
							vo.setMembersCount2(vo.getMembersCount2() +1);
						}
						else if(Long.valueOf(objects[0].toString()) > 6L && Long.valueOf(objects[0].toString()) <=14L)
						{
							vo.setMembersCount3(vo.getMembersCount3() + 1);
						}
						else if(Long.valueOf(objects[0].toString()) > 14L )
						{
							vo.setMembersCount4(vo.getMembersCount4() + 1);
						}
						
					
				}
				resultList.add(vo);
			}
		}catch (Exception e) {
			LOG.error("Exception raised in getVillageTotalCommittees()"+e);
		}
		return resultList;
	}




	public Long gettingCommitteeIdForMainCommittee(Long levelId,Long levelValue){
		Long committeeId = null;
		try{
			
			List<Long>  committeeEnrollmentIds = new ArrayList<Long>();
			committeeEnrollmentIds.add(IConstants.CURRENT_ENROLLMENT_ID);
			
			List<Long> committeeIds = tdpCommitteeDAO.getMainCommittiesInALocation(levelId, levelValue,committeeEnrollmentIds,null,null);
			if(committeeIds.size() > 0){
				committeeId = committeeIds.get(0);
			}
		}catch(Exception e){
			LOG.error("Exception raised in gettingCommitteeIdForMainCommittee", e);
		}
		return committeeId;
	}
	
	
	   public BasicVO getAccessLocationValuesByState(String accessType,Long accessValue,Long stateId,Long userId,List<Long> enrollIdsList)
	   {
	  	  BasicVO basicVO = new BasicVO();
	  	  List<BasicVO> resultList =new ArrayList<BasicVO>();
	  	 List<Long> districtIds = new ArrayList<Long>();
	  	 
	  	  List<Object[]> list = null;
	  	  Long stateTypeId = 1l;
				String accessState = "ALL";
	  	  try{
	  		 
	  		 
	  		   if(accessType.equalsIgnoreCase("MP"))
	  		  {
	  			accessState = delimitationConstituencyAssemblyDetailsDAO.get(accessValue).getConstituency().getName()+" "+"Parliament"; 
					List<Object[]> accessConstituencyList = userConstituencyAccessInfoDAO.findByElectionScopeUser(1L,userId);
					List<Long> parliamentsIds = new ArrayList<Long>();
					if(accessConstituencyList != null && accessConstituencyList.size()>0)
					{
						for (Object[] parliament : accessConstituencyList) {
							parliamentsIds.add(parliament[0] != null ? Long.valueOf(parliament[0].toString().trim()):0L);
						}
					}
					else
					{
						parliamentsIds.add(accessValue);
					}
					
					if(parliamentsIds != null && parliamentsIds.size()>0)
					{
						list = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesDetailsByParliamentList(parliamentsIds);
					}
	      		 
	  		  }
	  		   else
	  		   {

	   				List<Object[]> accessDistrictsList = userDistrictAccessInfoDAO.findByUser(userId);
					if(accessDistrictsList != null && accessDistrictsList.size()>0)
					{
						for (Object[] districtId : accessDistrictsList) {
							districtIds.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
						}
						
						if(districtIds != null && districtIds.size() == 1)
						{
							Long districtId = districtIds.get(0).longValue();
							if(districtId != 0L)
								accessState =  districtDAO.get(districtId).getDistrictName()+" District";
						}
						else if(districtIds != null && districtIds.contains(11L))
						{
							accessState = "AP";
						}
						else if(districtIds != null && districtIds.contains(1L))
						{
							accessState = "TG";
						}
						list = constituencyDAO.getAssemblyConstituencyDetlsByDistrictIds(districtIds);
					}				
					else{
						if(stateId == 2l){
							stateTypeId = 2l;
						}
						list = constituencyDAO.getConstituenciesByStateId(1l, stateTypeId);
					}
				
	  		   }
	  		  if(list != null && list.size() > 0)
	  		  {
	  		  for(Object[] assembly:list){
	  			  BasicVO subVo = new BasicVO();
	  			  subVo.setId((Long)assembly[0]);
	  			  subVo.setName(assembly[1].toString());
	  			  resultList.add(subVo);
	  		  }
	  		  }
	  		  basicVO.setHamletVoterInfo(resultList);
	  	  }
	  	  catch(Exception e)
	  	  {
	  		  e.printStackTrace();
	  	  }
	  	 
				return basicVO;
	    }
	
	public List<CommitteeSummaryVO> getConstituencyWiseCommittesSummary(String state,String startDate, String endDate,Long userId, String accessType,Long accessValue,String mandalCheck,String villageCheck,
			String reqLocationTypeStr,List<Long> committeeEnrollmentIdsLst,List<Long> levelIdsList){  
		LOG.debug("Entered Into getConstituencyWiseCommittesSummary");
		List<CommitteeSummaryVO> constiLst = new ArrayList<CommitteeSummaryVO>();
		try{
			Long stateTypeId = 1l;
			String accessState = "ALL";
			List<Object[]> constituencysList = null;
			List<Long> districtIds = new ArrayList<Long>();
			List<Long> constiIds = new ArrayList<Long>();
			List<Long> constiIds1 = new ArrayList<Long>();
			if(accessType.trim().equalsIgnoreCase("MP"))
			{
				accessState = delimitationConstituencyAssemblyDetailsDAO.get(accessValue).getConstituency().getName()+" "+"Parliament"; 
				List<Object[]> accessConstituencyList = userConstituencyAccessInfoDAO.findByElectionScopeUser(1L,userId);
				List<Long> parliamentsIds = new ArrayList<Long>();
				if(accessConstituencyList != null && accessConstituencyList.size()>0)
				{
					for (Object[] parliament : accessConstituencyList) {
						parliamentsIds.add(parliament[0] != null ? Long.valueOf(parliament[0].toString().trim()):0L);
					}
				}
				else
				{
					parliamentsIds.add(accessValue);
				}
				
				if(parliamentsIds != null && parliamentsIds.size()>0)
				{
					constituencysList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesDetailsByParliamentList(parliamentsIds);
				}
			}
			else
			{
				List<Object[]> accessDistrictsList = userDistrictAccessInfoDAO.findByUser(userId);
				if(accessDistrictsList != null && accessDistrictsList.size()>0)
				{
					for (Object[] districtId : accessDistrictsList) {
						districtIds.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
					}
					
					if(districtIds != null && districtIds.size() == 1)
					{
						Long districtId = districtIds.get(0).longValue();
						if(districtId != 0L)
							accessState =  districtDAO.get(districtId).getDistrictName()+" District";
					}
					else if(districtIds != null && districtIds.contains(11L))
					{
						accessState = "AP";
					}
					else if(districtIds != null && districtIds.contains(1L))
					{
						accessState = "TG";
					}
					
					constituencysList = constituencyDAO.getAssemblyConstituencyDetlsByDistrictIds(districtIds);
				}				
				else{
					if(state.equalsIgnoreCase("TS")){
						stateTypeId = 2l;
					}
					constituencysList = constituencyDAO.getConstituenciesByStateId(1l, stateTypeId);
				}
			}
			
			if(constituencysList != null && constituencysList.size()>0){
				for(Object[] obj:constituencysList){
					if(!constiIds1.contains(Long.valueOf(obj[0].toString())))
					{
							constiIds1.add(Long.valueOf(obj[0].toString()));
					}
				}
			}
			
			Map<Long,Long> constiNoMap = new HashMap<Long, Long>();
			List<Object[]> constiNosDtls = delimitationConstituencyDAO.getConstituencyNo(constiIds1, IConstants.DELIMITATION_YEAR);
			if(constiNosDtls != null && constiNosDtls.size() > 0){
				for(Object[] params : constiNosDtls){
					Long constiNo = constiNoMap.get((Long)params[0]);
					if(constiNo == null){
						constiNoMap.put((Long)params[0],(Long)params[1]);
					}
				}
			}
			
			Map<Long,CommitteeSummaryVO> stateMap = new HashMap<Long, CommitteeSummaryVO>();
			Map<Long,CommitteeSummaryVO> districtsMap = new HashMap<Long, CommitteeSummaryVO>();
			Map<Long,CommitteeSummaryVO> constuncyMap = new HashMap<Long, CommitteeSummaryVO>();
			
			if(constituencysList != null && constituencysList.size()>0){
				for(Object[] obj:constituencysList){
					CommitteeSummaryVO cv = new CommitteeSummaryVO();
					cv.setConstiId(Long.valueOf(obj[0].toString()));
					cv.setName(obj[1].toString());
					cv.setConstiNo(constiNoMap.get(Long.valueOf(obj[0].toString())));
					cv.setDistrictId(commonMethodsUtilService.getLongValueForObject(obj[2]));
					cv.setDistrictName(commonMethodsUtilService.getStringValueForObject(obj[3]));
					cv.setStateId(commonMethodsUtilService.getLongValueForObject(obj[4]));
					cv.setStateName(commonMethodsUtilService.getStringValueForObject(obj[5]));
					constiIds.add(Long.valueOf(obj[0].toString()));
					constiLst.add(cv);
					
					CommitteeSummaryVO vo2 = stateMap.get(cv.getStateId());
					if(vo2 == null){
						vo2 = new CommitteeSummaryVO();
						//vo.setConstiId(cv.getConstiId());
						vo2.setStateId(cv.getStateId());
						vo2.setStateName(cv.getStateName());
						stateMap.put(cv.getStateId(), vo2);
					}
					
					CommitteeSummaryVO vo = districtsMap.get(cv.getDistrictId());
					if(vo == null){
						vo = new CommitteeSummaryVO();
						//vo.setConstiId(cv.getConstiId());
						vo.setDistrictId(cv.getDistrictId());
						vo.setDistrictName(cv.getDistrictName());
						districtsMap.put(cv.getDistrictId(), vo);
					}
					
					CommitteeSummaryVO vo1 = constuncyMap.get(cv.getConstiId());
					if(vo1 == null){
						vo1 =  new CommitteeSummaryVO();
						vo1.setConstiId(cv.getConstiId());
						vo1.setName(cv.getName());
						constuncyMap.put(cv.getConstiId(), vo1);
					}
				}
			}
			
			
			//constiCountForMandalTownDivisions(constiIds);
			
			SimpleDateFormat format =  new SimpleDateFormat("MM/dd/yyyy");
			
			Date stDate = (Date)format.parse(startDate);
			Date edDate = (Date)format.parse(endDate);
			
			List<Long> mandalMunciDivisionIds = new ArrayList<Long>();
			mandalMunciDivisionIds.add(5l);
			mandalMunciDivisionIds.add(7l);
			mandalMunciDivisionIds.add(9l);
			/*List<Object[]> memResLst = tdpCommitteeMemberDAO.membersCountConstituencyWise(mandalMunciDivisionIds, stDate, edDate, constiIds);
			pushResultConstituencyWiseMemsCount("munci", memResLst, constiLst);*/
			 Map<Long,Long> mainMembersCountMap = new HashMap<Long,Long>();
			 Map<Long,Long> startedCommitteesCountMap = new HashMap<Long,Long>();
			 Map<Long,Long> completedCommitteesCountMap = new HashMap<Long,Long>();
			 Map<Long,Long> startedCommitteesAffCountMap = new HashMap<Long,Long>();
			 Map<Long,Long> completedCommitteesAffCountMap = new HashMap<Long,Long>();
			 
			 Map<Long,Long> startedYuvathaCommitteesAffCountMap = new HashMap<Long,Long>();
			 Map<Long,Long> completedYuvathaCommitteesAffCountMap = new HashMap<Long,Long>();
			 
			 Map<Long,Long> startedMahilaCommitteesAffCountMap = new HashMap<Long,Long>();
			 Map<Long,Long> completedMahilaCommitteesAffCountMap = new HashMap<Long,Long>();
			 
			 Map<Long,Long> startedRythuCommitteesAffCountMap = new HashMap<Long,Long>();
			 Map<Long,Long> completedRythuCommitteesAffCountMap = new HashMap<Long,Long>();
			 
			 Map<Long,Long> startedOthersCommitteesAffCountMap = new HashMap<Long,Long>();
			 Map<Long,Long> completedOthersCommitteesAffCountMap = new HashMap<Long,Long>();
				Map<Long,Long> afflMembersCountMap = new LinkedHashMap<Long, Long>();
			 List<Object[]> basicCommitteesRslt = tdpBasicCommitteeDAO.getBasicCommittees();
				List<CadreCommitteeReportVO> basicCmmty = new ArrayList<CadreCommitteeReportVO>();
				if(basicCommitteesRslt!=null && basicCommitteesRslt.size()>0){
					for(Object[] obj:basicCommitteesRslt){
						CadreCommitteeReportVO vo = new CadreCommitteeReportVO();
						vo.setId(Long.valueOf(obj[0].toString()));
						vo.setName(obj[1].toString());
						basicCmmty.add(vo);
					}
				}
				
				
			 Map<Long,Long> mainCommitteesCountMap = new HashMap<Long,Long>();
			if(mandalCheck.equalsIgnoreCase("true"))
				constiCountForMandalTownDivisions(constiIds, stDate, edDate, mainMembersCountMap,afflMembersCountMap, startedCommitteesCountMap, completedCommitteesCountMap, 
						startedCommitteesAffCountMap, completedCommitteesAffCountMap, mainCommitteesCountMap,startedYuvathaCommitteesAffCountMap,completedYuvathaCommitteesAffCountMap,
						startedMahilaCommitteesAffCountMap,completedMahilaCommitteesAffCountMap,startedRythuCommitteesAffCountMap,
						completedRythuCommitteesAffCountMap,startedOthersCommitteesAffCountMap,completedOthersCommitteesAffCountMap,basicCmmty,reqLocationTypeStr, committeeEnrollmentIdsLst,levelIdsList);
			if(villageCheck.equalsIgnoreCase("true"))
			{
			
			List<Long> villageWardIds = new ArrayList<Long>();
			villageWardIds.add(6l);
			villageWardIds.add(8l);
			
			List<Object[]> memResLstVill = tdpCommitteeMemberDAO.membersCountConstituencyWise(villageWardIds, stDate, edDate, constiIds,reqLocationTypeStr, committeeEnrollmentIdsLst);
			List<Object[]> ttlV = tdpCommitteeDAO.getCommitteesCountByConstituencyIdAndLevel(constiIds, villageWardIds,reqLocationTypeStr, committeeEnrollmentIdsLst);
			pushResultConstituencyWiseMemsCount("village", memResLstVill, constiLst);
			pushTotalCountsForConstituency("village", ttlV, constiLst);
			
			
			/*List<Object[]> stResLst = tdpCommitteeDAO.committeesCountByConstituency(mandalMunciDivisionIds, stDate, edDate, "started", constiIds);
			List<Object[]> endResLst = tdpCommitteeDAO.committeesCountByConstituency(mandalMunciDivisionIds, stDate, edDate, "completed", constiIds);
			pushResultConstiWise("munci", stResLst, constiLst, "start");
			pushResultConstiWise("munci", endResLst, constiLst, "completed");*/
			
			List<Object[]> stResLstVill = tdpCommitteeDAO.committeesCountByConstituency(villageWardIds, stDate, edDate, "started", constiIds,reqLocationTypeStr, committeeEnrollmentIdsLst);
			List<Object[]> endResLstVill = tdpCommitteeDAO.committeesCountByConstituency(villageWardIds, stDate, edDate, "completed", constiIds,reqLocationTypeStr, committeeEnrollmentIdsLst);
			pushResultConstiWise("village", stResLstVill, constiLst, "start");
			pushResultConstiWise("village", endResLstVill, constiLst, "completed");
			}
			
			if(constiLst!=null && constiLst.size()>0){
				for(CommitteeSummaryVO temp:constiLst){
					if(mandalCheck.equalsIgnoreCase("true")){
					if(temp.getTownMandalDivisionVO()==null){
						temp.setTownMandalDivisionVO(new CommitteeSummaryVO());
					}
					if(temp.getTownMandalDivisionVO()!=null){
						
						Long constiId = temp.getConstiId();
						
						Long membersCount = mainMembersCountMap.get(constiId);
						if(membersCount==null){membersCount=null;}
						
						Long mainStarted = startedCommitteesCountMap.get(constiId);
						if(mainStarted==null){mainStarted=null;}
						
						Long mainCompleted = completedCommitteesCountMap.get(constiId);
						if(mainCompleted==null){mainCompleted=null;}
						
						Long afflStarted = startedCommitteesAffCountMap.get(constiId);
						if(afflStarted==null){afflStarted=null;}
						
						Long afflCompleted = completedCommitteesAffCountMap.get(constiId);
						if(afflCompleted==null){afflCompleted=null;}
						
						Long totalCommittees = mainCommitteesCountMap.get(constiId);
						if(totalCommittees==null){totalCommittees=null;}
						
						/*Long yuvathaStarted = startedYuvathaCommitteesAffCountMap.get(constiId);
						if(yuvathaStarted==null){yuvathaStarted=null;}
						
						Long yuvathaCompleted = completedYuvathaCommitteesAffCountMap.get(constiId);
						if(yuvathaCompleted==null){yuvathaCompleted=null;}
						
						Long mahilaStarted = startedMahilaCommitteesAffCountMap.get(constiId);
						if(mahilaStarted==null){mahilaStarted=null;}
						
						Long mahilaCompleted = completedMahilaCommitteesAffCountMap.get(constiId);
						if(mahilaCompleted==null){mahilaCompleted=null;}
						
						
						Long rythuStarted = startedRythuCommitteesAffCountMap.get(constiId);
						if(rythuStarted==null){rythuStarted=null;}
						
						Long rythuCompleted = completedRythuCommitteesAffCountMap.get(constiId);
						if(rythuCompleted==null){rythuCompleted=null;}
						*/
						
						Long othersStarted = startedOthersCommitteesAffCountMap.get(constiId);
						if(othersStarted==null){othersStarted=null;}
						
						Long othersCompleted = completedOthersCommitteesAffCountMap.get(constiId);
						if(othersCompleted==null){othersCompleted=null;}
						
						
						if(basicCmmty != null && basicCmmty.size()>0)
						{
							
							for (CadreCommitteeReportVO committeeVO : basicCmmty) 
							{
								CadreCommitteeReportVO locationVO = getMatchedVOListByLocationId(committeeVO.getCadreCommitteeReportVOList(),constiId);
								if(locationVO != null)
								{
									Long startedCount = locationVO.getStartedCommittees() != 0? locationVO.getStartedCommittees() :null;
									Long completedCount = locationVO.getCompletedCommittees() != 0? locationVO.getCompletedCommittees() :null;
									if(committeeVO.getId().longValue() == 2L)
									{
										temp.getTownMandalDivisionVO().setYouvathaStarted(startedCount);
										temp.getTownMandalDivisionVO().setYouvathaCmpltd(completedCount);
										
									}
									else if(committeeVO.getId().longValue() == 3L)
									{
										temp.getTownMandalDivisionVO().setMahilaStarted(startedCount);
										temp.getTownMandalDivisionVO().setMahilaCmpltd(completedCount);									
									}
									else if(committeeVO.getId().longValue() == 4L)
									{
										temp.getTownMandalDivisionVO().setRythuStarted(startedCount);
										temp.getTownMandalDivisionVO().setRythuCmpltd(completedCount);									
									}	
									else if(committeeVO.getId().longValue() == 5L)
									{
										temp.getTownMandalDivisionVO().setTntucStarted(startedCount);
										temp.getTownMandalDivisionVO().setTntucCmpltd(completedCount);
									}
									else if(committeeVO.getId().longValue() == 6L)
									{
										temp.getTownMandalDivisionVO().setBcCellStarted(startedCount);
										temp.getTownMandalDivisionVO().setBcCellCmpltd(completedCount);
									}
									else if(committeeVO.getId().longValue() == 7L)
									{
										temp.getTownMandalDivisionVO().setScCellStarted(startedCount);
										temp.getTownMandalDivisionVO().setScCellCmpltd(completedCount);
									}
									else if(committeeVO.getId().longValue() == 8L)
									{
										temp.getTownMandalDivisionVO().setStCellStarted(startedCount);
										temp.getTownMandalDivisionVO().setStCellCmpltd(completedCount);
									}
									else if(committeeVO.getId().longValue() == 9L)
									{
										temp.getTownMandalDivisionVO().setMinorityStarted(startedCount);
										temp.getTownMandalDivisionVO().setMinorityCmpltd(completedCount);
									}
									else if(committeeVO.getId().longValue() == 10L)
									{
										temp.getTownMandalDivisionVO().setLegalCellStarted(startedCount);
										temp.getTownMandalDivisionVO().setLegalCmpltd(completedCount);
									}
									else if(committeeVO.getId().longValue() == 11L)
									{
										temp.getTownMandalDivisionVO().setTnsfStarted(startedCount);
										temp.getTownMandalDivisionVO().setTnsfCmpltd(completedCount);
									}
									else if(committeeVO.getId().longValue() == 12L)
									{
										temp.getTownMandalDivisionVO().setCommercialStarted(startedCount);
										temp.getTownMandalDivisionVO().setCommercialCmpltd(completedCount);
									}
									else if(committeeVO.getId().longValue() == 13L)
									{
										temp.getTownMandalDivisionVO().setCulturalStarted(startedCount);
										temp.getTownMandalDivisionVO().setCulturalCmpltd(completedCount);
									}	
									else if(committeeVO.getId().longValue() == 14L)
									{
										temp.getTownMandalDivisionVO().setTnusStarted(startedCount);
										temp.getTownMandalDivisionVO().setTnusCmpltd(completedCount);
									}
									else if(committeeVO.getId().longValue() == 15L)
									{
										temp.getTownMandalDivisionVO().setTsnvStarted(startedCount);
										temp.getTownMandalDivisionVO().setTsnvCmpltd(completedCount);
									}
									else if(committeeVO.getId().longValue() == 16L)
									{
										temp.getTownMandalDivisionVO().setDoctorStarted(startedCount);
										temp.getTownMandalDivisionVO().setDoctorCmpltd(completedCount);
									}
									else if(committeeVO.getId().longValue() == 17L)
									{
										temp.getTownMandalDivisionVO().setTradeStarted(startedCount);
										temp.getTownMandalDivisionVO().setTradeCmpltd(completedCount);
									}
									else if(committeeVO.getId().longValue() == 18L)
									{
										temp.getTownMandalDivisionVO().setCristianStarted(startedCount);
										temp.getTownMandalDivisionVO().setCristianCmpltd(completedCount);
									}
									else if(committeeVO.getId().longValue() == 19L)
									{
										temp.getTownMandalDivisionVO().setRakshaVedikaStarted(startedCount);
										temp.getTownMandalDivisionVO().setRakshaVedikaCmpltd(completedCount);
									}
									else if(committeeVO.getId().longValue() == 20L)
									{
										temp.getTownMandalDivisionVO().setKalluGeethaStarted(startedCount);
										temp.getTownMandalDivisionVO().setKalluGeethaCmpltd(completedCount);
									}
									else if(committeeVO.getId().longValue() == 21L)
									{
										temp.getTownMandalDivisionVO().setChenethaStarted(startedCount);
										temp.getTownMandalDivisionVO().setChenethaCmpltd(completedCount);
									}
								}
							}
						}
						
						temp.getTownMandalDivisionVO().setOthersStarted(startedOthersCommitteesAffCountMap.get(constiId));
						temp.getTownMandalDivisionVO().setOthersCmpltd(completedOthersCommitteesAffCountMap.get(constiId));
						
						temp.getTownMandalDivisionVO().setMainStarted(mainStarted);
						temp.getTownMandalDivisionVO().setMainCompleted(mainCompleted);
						temp.getTownMandalDivisionVO().setAfflStarted(afflStarted);
						temp.getTownMandalDivisionVO().setAfflCompleted(afflCompleted);
						temp.getTownMandalDivisionVO().setTotalCommittees(totalCommittees);
						temp.getTownMandalDivisionVO().setMembersCount(membersCount);
						
						Long strt = temp.getTownMandalDivisionVO().getMainStarted();
						//Long cmpl = temp.getTownMandalDivisionVO().getMainCompleted();
						
						if(strt==null){strt = 0l;}
						/*if(cmpl==null){cmpl = 0l;}*/
						
						Long total = temp.getTownMandalDivisionVO().getTotalCommittees();
						if(total==null){total = 0l;}
						if(total!=0){
							//temp.getTownMandalDivisionVO().setTotalCommittees(total);
							String percentage = (new BigDecimal(strt*(100.0)/total)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
							temp.getTownMandalDivisionVO().setStartPerc(percentage);
						}else{
							temp.getTownMandalDivisionVO().setStartPerc("0.0");
						}
					}
					}
					if(villageCheck.equalsIgnoreCase("true")){
					if(temp.getVillageWardVO()!=null){
						Long strtv = temp.getVillageWardVO().getMainStarted();
						Long cmplv = temp.getVillageWardVO().getMainCompleted();
						Long totalv = temp.getVillageWardVO().getTotalCommittees();
						if(totalv==null){
							totalv = 0l;
						}
						
						if(strtv==null){strtv = 0l;}
						/*if(cmplv==null){cmplv = 0l;}
						
						Long totalv = strtv + cmplv;*/
						
						if(totalv!=0){
							temp.getVillageWardVO().setTotalCommittees(totalv);
							String percentage = (new BigDecimal(strtv*(100.0)/totalv)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
							temp.getVillageWardVO().setStartPerc(percentage);
						}else{
							temp.getVillageWardVO().setStartPerc("0.0");
						}
					}
					}
				}
			}
			
			Collections.sort(constiLst, new Comparator<CommitteeSummaryVO>() {
				public int compare(CommitteeSummaryVO o1, CommitteeSummaryVO o2) {
					return o1.getName().compareToIgnoreCase(o2.getName());
				}
			});
			
			if(constiLst != null && constiLst.size()>0)
			{
				constiLst.get(0).setAccessState(accessState);
			}
			
			if(villageCheck.equalsIgnoreCase("true") && userId.longValue() == 1 && committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.get(0) == 1l ){
				
				getAllIvrDetailsForCampaind(constiLst,2l,"constituency");
			}
			
			
			if(constiLst != null && constiLst.size()>0)
			{
				for (CommitteeSummaryVO summaryVO : constiLst) 
				{
					CadreIVRVO ivrDetailsVO = summaryVO.getCadreIVRVO();
					
					if(ivrDetailsVO != null)
					{
						Long totalIVRCount = 0L;
						List<IvrOptionsVO> villageIVRDetails = ivrDetailsVO.getOptionsList();
						List<IvrOptionsVO> wardIVRDetails = ivrDetailsVO.getOptionsList1();
						
						int villageListLength = villageIVRDetails != null ? villageIVRDetails.size():0;
						int wardListLength = wardIVRDetails != null ? wardIVRDetails.size():0;
						
						int maxLenght = villageListLength>wardListLength?  villageListLength : wardListLength;
						
						for (int i = 0; i < maxLenght; i++) 
						{
							IvrOptionsVO villageVO =villageIVRDetails != null ? villageIVRDetails.get(i):null;
							IvrOptionsVO wardVO = wardIVRDetails != null ? wardIVRDetails.get(i): null;
							
							if(villageVO !=null && wardVO != null && villageVO.getName().trim().equalsIgnoreCase(wardVO.getName().trim()))
							{
								Long villageCount = villageVO.getCount() != null ? villageVO.getCount() :0L;
								Long wardCount = wardVO.getCount() != null ? wardVO.getCount():0L;
								Long totalCount = villageCount + wardCount;
								totalIVRCount = totalIVRCount + totalCount;
								
								IvrOptionsVO returnVO = getMatchedVOByIVRStatus(villageIVRDetails,villageVO.getName().trim());
								if(returnVO != null)
								{
									returnVO.setCount(totalCount);
								}
							}
						}
						
						if(totalIVRCount != null && totalIVRCount.longValue() !=0)
							ivrDetailsVO.setTotal(totalIVRCount);
					}
					
				}
				constiLst.get(0).getDistrictWiseList().addAll(districtsMap.values());
				constiLst.get(0).getConstinuncyList().addAll(constuncyMap.values());
				constiLst.get(0).getStateList().addAll(stateMap.values());
			}
		}catch (Exception e) {
			LOG.error("Exception Raised in getConstituencyWiseCommittesSummary",e);
		}
		return constiLst;
	}
	
	public CadreCommitteeReportVO getMatchedVOListByLocationId(List<CadreCommitteeReportVO> list, Long locationId)
	{
		//CadreCommitteeReportVO locationVO = getMatchedVOListByLocationId(committeeVO.getCadreCommitteeReportVOList(),temp.getConstiId());
		CadreCommitteeReportVO returnVO = null;
		try {
			if(list != null && list.size()>0)
			{
				for (CadreCommitteeReportVO cadreCommitteeReportVO : list) {
						if(locationId != null)
					if(cadreCommitteeReportVO.getLocationId() != null && cadreCommitteeReportVO.getLocationId().longValue() == locationId.longValue()){
						return cadreCommitteeReportVO;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Raised in getMatchedVOListByLocationId",e);
		}
		return returnVO;
	}
	
	public List<CommitteeSummaryVO> getConstituencyWiseCommittesSummaryForDistrict(String state,String startDate, String endDate,Long userId, String accessType,Long accessValue,String mandalCheck,String villageCheck,List<Long> committeeEnrollmentIdsLst){  
		LOG.debug("Entered Into getConstituencyWiseCommittesSummaryForDistrict");
		List<CommitteeSummaryVO> constiLst = new ArrayList<CommitteeSummaryVO>();
		try{
			Long stateTypeId = 1l;
			String accessState = "ALL";
			List<Object[]> constituencysList = null;
			List<Long> districtIds = new ArrayList<Long>();
			List<Long> constiIds = new ArrayList<Long>();
			List<Long> constiIds1 = new ArrayList<Long>();
			
			String reqLocationTypeStr = null;
			//List<Long> committeeEnrollmentIdsLst = null ;
			List<Long>levelIdsList = null;
			
			
			if(accessValue != null)
			{
				
				districtIds.add(accessValue);
				
				if(districtIds != null && districtIds.size() == 1)
				{
					Long districtId = districtIds.get(0).longValue();
					if(districtId != 0L)
						accessState =  districtDAO.get(districtId).getDistrictName()+" District";
				}
				else if(districtIds != null && districtIds.contains(11L))
				{
					accessState = "AP";
				}
				else if(districtIds != null && districtIds.contains(1L))
				{
					accessState = "TG";
				}
				
				
				constituencysList = constituencyDAO.getAssemblyConstituencyDetlsByDistrictIds(districtIds);
			}				
			else{
				if(state.equalsIgnoreCase("TS")){
					stateTypeId = 2l;
				}
				constituencysList = constituencyDAO.getConstituenciesByStateId(1l, stateTypeId);
			}
		
		if(constituencysList != null && constituencysList.size()>0){
			for(Object[] obj:constituencysList){
				if(!constiIds1.contains(Long.valueOf(obj[0].toString())))
				{
						constiIds1.add(Long.valueOf(obj[0].toString()));
				}
			}
		}
	
	Map<Long,Long> constiNoMap = new HashMap<Long, Long>();
	List<Object[]> constiNosDtls = delimitationConstituencyDAO.getConstituencyNo(constiIds1, IConstants.DELIMITATION_YEAR);
	if(constiNosDtls != null && constiNosDtls.size() > 0){
		for(Object[] params : constiNosDtls){
			Long constiNo = constiNoMap.get((Long)params[0]);
			if(constiNo == null){
				constiNoMap.put((Long)params[0],(Long)params[1]);
			}
		}
	}
	
	if(constituencysList != null && constituencysList.size()>0){
		for(Object[] obj:constituencysList){
			CommitteeSummaryVO cv = new CommitteeSummaryVO();
			cv.setConstiId(Long.valueOf(obj[0].toString()));
			cv.setName(obj[1].toString());
			cv.setConstiNo(constiNoMap.get(Long.valueOf(obj[0].toString())));
			constiIds.add(Long.valueOf(obj[0].toString()));
			constiLst.add(cv);
		}
	}
	
	
	//constiCountForMandalTownDivisions(constiIds);
	
	SimpleDateFormat format =  new SimpleDateFormat("MM/dd/yyyy");
	
	Date stDate = (Date)format.parse(startDate);
	Date edDate = (Date)format.parse(endDate);
	
	List<Long> mandalMunciDivisionIds = new ArrayList<Long>();
	mandalMunciDivisionIds.add(5l);
	mandalMunciDivisionIds.add(7l);
	mandalMunciDivisionIds.add(9l);
	/*List<Object[]> memResLst = tdpCommitteeMemberDAO.membersCountConstituencyWise(mandalMunciDivisionIds, stDate, edDate, constiIds);
	pushResultConstituencyWiseMemsCount("munci", memResLst, constiLst);*/
	 Map<Long,Long> mainMembersCountMap = new HashMap<Long,Long>();
	 Map<Long,Long> startedCommitteesCountMap = new HashMap<Long,Long>();
	 Map<Long,Long> completedCommitteesCountMap = new HashMap<Long,Long>();
	 Map<Long,Long> startedCommitteesAffCountMap = new HashMap<Long,Long>();
	 Map<Long,Long> completedCommitteesAffCountMap = new HashMap<Long,Long>();
	 Map<Long,Long> mainCommitteesCountMap = new HashMap<Long,Long>();
	 

	 Map<Long,Long> startedYuvathaCommitteesAffCountMap = new HashMap<Long,Long>();
	 Map<Long,Long> completedYuvathaCommitteesAffCountMap = new HashMap<Long,Long>();
	 
	 Map<Long,Long> startedMahilaCommitteesAffCountMap = new HashMap<Long,Long>();
	 Map<Long,Long> completedMahilaCommitteesAffCountMap = new HashMap<Long,Long>();
	 
	 Map<Long,Long> startedRythuCommitteesAffCountMap = new HashMap<Long,Long>();
	 Map<Long,Long> completedRythuCommitteesAffCountMap = new HashMap<Long,Long>();
	 
	 Map<Long,Long> startedOthersCommitteesAffCountMap = new HashMap<Long,Long>();
	 Map<Long,Long> completedOthersCommitteesAffCountMap = new HashMap<Long,Long>();
		Map<Long,Long> afflMembersCountMap = new LinkedHashMap<Long, Long>();
		
	 List<Object[]> basicCommitteesRslt = tdpBasicCommitteeDAO.getBasicCommittees();
	 
		List<CadreCommitteeReportVO> basicCmmty = new ArrayList<CadreCommitteeReportVO>();
		if(basicCommitteesRslt!=null && basicCommitteesRslt.size()>0){
			for(Object[] obj:basicCommitteesRslt){
				CadreCommitteeReportVO vo = new CadreCommitteeReportVO();
				vo.setId(Long.valueOf(obj[0].toString()));
				vo.setName(obj[1].toString());
				basicCmmty.add(vo);
			}
		}
		
	if(mandalCheck.equalsIgnoreCase("true"))
		constiCountForMandalTownDivisions(constiIds, stDate, edDate, mainMembersCountMap,afflMembersCountMap, startedCommitteesCountMap, completedCommitteesCountMap, 
				startedCommitteesAffCountMap, completedCommitteesAffCountMap, mainCommitteesCountMap,startedYuvathaCommitteesAffCountMap,completedYuvathaCommitteesAffCountMap,
				startedMahilaCommitteesAffCountMap,completedMahilaCommitteesAffCountMap,startedRythuCommitteesAffCountMap,completedRythuCommitteesAffCountMap,
				startedOthersCommitteesAffCountMap,completedOthersCommitteesAffCountMap,basicCmmty,reqLocationTypeStr, committeeEnrollmentIdsLst,levelIdsList );
	if(villageCheck.equalsIgnoreCase("true"))
	{
	
	List<Long> villageWardIds = new ArrayList<Long>();
	villageWardIds.add(6l);
	villageWardIds.add(8l);
	
	List<Object[]> memResLstVill = tdpCommitteeMemberDAO.membersCountConstituencyWise(villageWardIds, stDate, edDate, constiIds,reqLocationTypeStr, committeeEnrollmentIdsLst);
	List<Object[]> ttlV = tdpCommitteeDAO.getCommitteesCountByConstituencyIdAndLevel(constiIds, villageWardIds,reqLocationTypeStr, committeeEnrollmentIdsLst);
	pushResultConstituencyWiseMemsCount("village", memResLstVill, constiLst);
	pushTotalCountsForConstituency("village", ttlV, constiLst);
	
	
	/*List<Object[]> stResLst = tdpCommitteeDAO.committeesCountByConstituency(mandalMunciDivisionIds, stDate, edDate, "started", constiIds);
	List<Object[]> endResLst = tdpCommitteeDAO.committeesCountByConstituency(mandalMunciDivisionIds, stDate, edDate, "completed", constiIds);
	pushResultConstiWise("munci", stResLst, constiLst, "start");
	pushResultConstiWise("munci", endResLst, constiLst, "completed");*/
	
	List<Object[]> stResLstVill = tdpCommitteeDAO.committeesCountByConstituency(villageWardIds, stDate, edDate, "started", constiIds,reqLocationTypeStr, committeeEnrollmentIdsLst);
	List<Object[]> endResLstVill = tdpCommitteeDAO.committeesCountByConstituency(villageWardIds, stDate, edDate, "completed", constiIds,reqLocationTypeStr, committeeEnrollmentIdsLst);
	pushResultConstiWise("village", stResLstVill, constiLst, "start");
	pushResultConstiWise("village", endResLstVill, constiLst, "completed");
	}
	
	if(constiLst!=null && constiLst.size()>0){
		for(CommitteeSummaryVO temp:constiLst){
			if(mandalCheck.equalsIgnoreCase("true")){
			if(temp.getTownMandalDivisionVO()==null){
				temp.setTownMandalDivisionVO(new CommitteeSummaryVO());
			}
			if(temp.getTownMandalDivisionVO()!=null){
				
				Long constiId = temp.getConstiId();
				
				Long membersCount = mainMembersCountMap.get(constiId);
				if(membersCount==null){membersCount=0l;}
				
				Long mainStarted = startedCommitteesCountMap.get(constiId);
				if(mainStarted==null){mainStarted=0l;}
				
				Long mainCompleted = completedCommitteesCountMap.get(constiId);
				if(mainCompleted==null){mainCompleted=0l;}
				
				Long afflStarted = startedCommitteesAffCountMap.get(constiId);
				if(afflStarted==null){afflStarted=0l;}
				
				Long afflCompleted = completedCommitteesAffCountMap.get(constiId);
				if(afflCompleted==null){afflCompleted=0l;}
				
				Long totalCommittees = mainCommitteesCountMap.get(constiId);
				if(totalCommittees==null){totalCommittees=0l;}
				
				Long yuvathaStarted = startedYuvathaCommitteesAffCountMap.get(constiId);
				if(yuvathaStarted==null){yuvathaStarted=null;}
				
				Long yuvathaCompleted = completedYuvathaCommitteesAffCountMap.get(constiId);
				if(yuvathaCompleted==null){yuvathaCompleted=null;}
				
				Long mahilaStarted = startedMahilaCommitteesAffCountMap.get(constiId);
				if(mahilaStarted==null){mahilaStarted=null;}
				
				Long mahilaCompleted = completedMahilaCommitteesAffCountMap.get(constiId);
				if(mahilaCompleted==null){mahilaCompleted=null;}
				
				
				Long rythuStarted = startedRythuCommitteesAffCountMap.get(constiId);
				if(rythuStarted==null){rythuStarted=null;}
				
				Long rythuCompleted = completedRythuCommitteesAffCountMap.get(constiId);
				if(rythuCompleted==null){rythuCompleted=null;}
				
				
				Long othersStarted = startedOthersCommitteesAffCountMap.get(constiId);
				if(othersStarted==null){othersStarted=null;}
				
				Long othersCompleted = completedOthersCommitteesAffCountMap.get(constiId);
				if(othersCompleted==null){othersCompleted=null;}
				
				temp.getTownMandalDivisionVO().setYouvathaStarted(yuvathaStarted);
				temp.getTownMandalDivisionVO().setYouvathaCmpltd(yuvathaCompleted);
				temp.getTownMandalDivisionVO().setMahilaStarted(mahilaStarted);
				temp.getTownMandalDivisionVO().setMahilaCmpltd(mahilaCompleted);
				temp.getTownMandalDivisionVO().setRythuStarted(rythuStarted);
				temp.getTownMandalDivisionVO().setRythuCmpltd(rythuCompleted);
				temp.getTownMandalDivisionVO().setOthersStarted(othersStarted);
				temp.getTownMandalDivisionVO().setOthersCmpltd(othersCompleted);
				
				temp.getTownMandalDivisionVO().setMainStarted(mainStarted);
				temp.getTownMandalDivisionVO().setMainCompleted(mainCompleted);
				temp.getTownMandalDivisionVO().setAfflStarted(afflStarted);
				temp.getTownMandalDivisionVO().setAfflCompleted(afflCompleted);
				temp.getTownMandalDivisionVO().setTotalCommittees(totalCommittees);
				temp.getTownMandalDivisionVO().setMembersCount(membersCount);
				
				Long strt = temp.getTownMandalDivisionVO().getMainStarted();
				Long cmpl = temp.getTownMandalDivisionVO().getMainCompleted();
				
				if(strt==null){strt = 0l;}
				/*if(cmpl==null){cmpl = 0l;}*/
				
				Long total = temp.getTownMandalDivisionVO().getTotalCommittees();
				if(total==null){total = 0l;}
				if(total!=0){
					//temp.getTownMandalDivisionVO().setTotalCommittees(total);
					String percentage = (new BigDecimal(strt*(100.0)/total)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					temp.getTownMandalDivisionVO().setStartPerc(percentage);
				}else{
					temp.getTownMandalDivisionVO().setStartPerc("0.0");
				}
			}
			}
			if(villageCheck.equalsIgnoreCase("true")){
			if(temp.getVillageWardVO()!=null){
				Long strtv = temp.getVillageWardVO().getMainStarted();
				Long cmplv = temp.getVillageWardVO().getMainCompleted();
				Long totalv = temp.getVillageWardVO().getTotalCommittees();
				if(totalv==null){
					totalv = 0l;
				}
				
				if(strtv==null){strtv = 0l;}
				/*if(cmplv==null){cmplv = 0l;}
				
				Long totalv = strtv + cmplv;*/
				
				if(totalv!=0){
					temp.getVillageWardVO().setTotalCommittees(totalv);
					String percentage = (new BigDecimal(strtv*(100.0)/totalv)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					temp.getVillageWardVO().setStartPerc(percentage);
				}else{
					temp.getVillageWardVO().setStartPerc("0.0");
				}
			}
			}
		}
	}
	
	Collections.sort(constiLst, new Comparator<CommitteeSummaryVO>() {
		public int compare(CommitteeSummaryVO o1, CommitteeSummaryVO o2) {
			return o1.getName().compareToIgnoreCase(o2.getName());
		}
	});
	
	if(constiLst != null && constiLst.size()>0)
	{
		constiLst.get(0).setAccessState(accessState);
	}
	
	if(villageCheck.equalsIgnoreCase("true") && userId.longValue() == 1){
		
		getAllIvrDetailsForCampaind(constiLst,2l,"constituency");
	}
	
	if(constiLst != null && constiLst.size()>0)
	{
		for (CommitteeSummaryVO summaryVO : constiLst) 
		{
			CadreIVRVO ivrDetailsVO = summaryVO.getCadreIVRVO();
			
			if(ivrDetailsVO != null)
			{
				Long totalIVRCount = 0L;
				int wardListLength = 0;
				int villageListLength  =0;
				List<IvrOptionsVO> villageIVRDetails = ivrDetailsVO.getOptionsList();
				List<IvrOptionsVO> wardIVRDetails = ivrDetailsVO.getOptionsList1();
				if(villageIVRDetails != null && villageIVRDetails.size() > 0)
				 villageListLength = villageIVRDetails.size();
				if(wardIVRDetails != null && wardIVRDetails.size() > 0)
					wardListLength = wardIVRDetails.size();
				
				int maxLenght = villageListLength>wardListLength?  villageListLength : wardListLength;
				
				for (int i = 0; i < maxLenght; i++) 
				{
					IvrOptionsVO villageVO =villageIVRDetails != null ? villageIVRDetails.get(i):null;
					IvrOptionsVO wardVO = wardIVRDetails != null ? wardIVRDetails.get(i): null;
					
					if(villageVO !=null && wardVO != null && villageVO.getName().trim().equalsIgnoreCase(wardVO.getName().trim()))
					{
						Long villageCount = villageVO.getCount() != null ? villageVO.getCount() :0L;
						Long wardCount = wardVO.getCount() != null ? wardVO.getCount():0L;
						Long totalCount = villageCount + wardCount;
						totalIVRCount = totalIVRCount + totalCount;
						
						IvrOptionsVO returnVO = getMatchedVOByIVRStatus(villageIVRDetails,villageVO.getName().trim());
						if(returnVO != null)
						{
							returnVO.setCount(totalCount);
						}
					}
				}
				
				if(totalIVRCount != null && totalIVRCount.longValue() !=0)
					ivrDetailsVO.setTotal(totalIVRCount);
			}
		}
	}
	
}catch (Exception e) {
	LOG.error("Exception Raised in getConstituencyWiseCommittesSummary",e);
}
return constiLst;
}
	public void getAllIvrDetailsForCampaind(List<CommitteeSummaryVO> constiLst,Long id,String type)
	{
		Map<Long,String> optionNames = new HashMap<Long,String>();
		Map<Long,String> optionNames1 = new HashMap<Long,String>();
		List<IvrOptionsVO> options1 = new ArrayList<IvrOptionsVO>();
		List<IvrOptionsVO> options2 = new ArrayList<IvrOptionsVO>();
		
		if(type.equalsIgnoreCase("ward"))
		{
			List<Object[]> optionsList1 = ivrCampaignOptionsDAO.getAllIVROptions(3l);
			if(optionsList1 != null && optionsList1.size() > 0)
			{
				getOptionsByCampain(optionsList1,optionNames1,options2);
			}
			Map<Long,CadreIVRVO> committeeIvrMap1 = new HashMap<Long, CadreIVRVO>();
			getCadreCommitteIvrDetails(committeeIvrMap1,id,optionNames1,8l);
			if(committeeIvrMap1 != null && committeeIvrMap1.size() > 0)
			{
				for (Long locId : committeeIvrMap1.keySet()) 
				{
					CadreIVRVO ivrVO = committeeIvrMap1.get(locId);
					if(committeeIvrMap1.get(locId) != null && committeeIvrMap1.get(locId).getOptionsList1() != null && committeeIvrMap1.get(locId).getOptionsList1().size() > 0)
					ivrVO.setOptionsList1(committeeIvrMap1.get(locId).getOptionsList1());
					else
					ivrVO.setOptionsList1(options2);
					if(ivrVO.getOptionsList1() == null)
					{
						ivrVO.setOptionsList1(options2);
					}
				}
			}
			fillIvrCommitteMap(committeeIvrMap1,constiLst,options2,type);
		}
		else
		{
			List<Object[]> optionsList = ivrCampaignOptionsDAO.getAllIVROptions(2l);
			if(optionsList != null && optionsList.size() > 0)
			{
				getOptionsByCampain(optionsList,optionNames,options1);
			}
			
			if(!type.equalsIgnoreCase("mandal"))
			{
				List<Object[]> optionsList1 = ivrCampaignOptionsDAO.getAllIVROptions(3l);
				if(optionsList1 != null && optionsList1.size() > 0)
				{
					getOptionsByCampain(optionsList1,optionNames1,options2);
				}
			}
			
			Map<Long,CadreIVRVO> committeeIvrMap = new HashMap<Long, CadreIVRVO>();
			getCadreCommitteIvrDetails(committeeIvrMap,id,optionNames,6l);
			fillIvrCommitteMap(committeeIvrMap,constiLst,options1,type);
			if(!type.equalsIgnoreCase("mandal"))
			{
				Map<Long,CadreIVRVO> committeeIvrMap1 = new HashMap<Long, CadreIVRVO>();
				getCadreCommitteIvrDetails(committeeIvrMap1,id,optionNames1,8l);
				if(committeeIvrMap != null && committeeIvrMap.size() > 0 && committeeIvrMap1 != null && committeeIvrMap1.size() > 0)
				{
					for (Long locId : committeeIvrMap.keySet()) 
					{
						CadreIVRVO ivrVO = committeeIvrMap.get(locId);
						if(committeeIvrMap1.get(locId) != null && committeeIvrMap1.get(locId).getOptionsList1() != null && committeeIvrMap1.get(locId).getOptionsList1().size() > 0)
						ivrVO.setOptionsList1(committeeIvrMap1.get(locId).getOptionsList1());
						else
						ivrVO.setOptionsList1(options2);
						if(ivrVO.getOptionsList1() == null)
						{
							ivrVO.setOptionsList1(options2);
						}
					}
				}
				fillIvrCommitteMap(committeeIvrMap,constiLst,options2,type);
			}
		}
		
		
	}
	public void getOptionsByCampain(List<Object[]> optionsList,Map<Long,String> optionNames,List<IvrOptionsVO> options )
	{
		if(optionsList != null && optionsList.size() >0){
			for(Object[] option:optionsList){
				optionNames.put((Long)option[0], option[1].toString());
			}
		}
		
		for(Long id : optionNames.keySet())
		{
			IvrOptionsVO optionVo = new IvrOptionsVO();
			optionVo.setId(id);
			optionVo.setName(optionNames.get(id));
			optionVo.setCount(0l);
			options.add(optionVo);
		}
	}
	
	public void fillIvrCommitteMap(Map<Long,CadreIVRVO> committeeIvrMap,List<CommitteeSummaryVO> constiLst,List<IvrOptionsVO> options,String type)
	{
		if(committeeIvrMap != null && committeeIvrMap.size() > 0 && constiLst != null && constiLst.size() > 0)
		{
			
			
			for (CommitteeSummaryVO vo : constiLst)
			{
				CadreIVRVO ivrVO = null;
				if(type.equalsIgnoreCase("district"))
				{
					ivrVO = committeeIvrMap.get(vo.getDistrictId());
				}
				else if(type.equalsIgnoreCase("constituency"))
				{
					ivrVO = committeeIvrMap.get(vo.getConstiId());
				}
				else
				{
					if(vo.getLocationsList() != null)
					{
					for (CommitteeSummaryVO panVO  : vo.getLocationsList())
					{
						ivrVO = committeeIvrMap.get(panVO.getLocationId());
						
						if(ivrVO != null )
						{
							panVO.setCadreIVRVO(ivrVO);
						}
						else
						{
							ivrVO = new CadreIVRVO();
							ivrVO.setOptionsList(options);
							panVO.setCadreIVRVO(ivrVO);
						}
					}
					}
					
				}
				
				if(ivrVO != null )
				{
					vo.setCadreIVRVO(ivrVO);
				}
				else
				{
					ivrVO = new CadreIVRVO();
					ivrVO.setOptionsList(options);
					vo.setCadreIVRVO(ivrVO);
				}
			}
		}
	}
	public void pushResultConstituencyWiseMemsCount(String type,List<Object[]> memResLst, List<CommitteeSummaryVO> fnlLst){
		if(memResLst!=null && memResLst.size()>0){
			for(Object[] obj:memResLst){
				
				CommitteeSummaryVO temp = getMatchedConstituency(Long.valueOf(obj[1].toString()), fnlLst);
				if(temp==null){
					temp = new CommitteeSummaryVO();
				}
				
				if(type.equalsIgnoreCase("munci")){
					if(temp.getTownMandalDivisionVO()==null){
						temp.setTownMandalDivisionVO(new CommitteeSummaryVO());
					}
					temp.getTownMandalDivisionVO().setMembersCount(Long.valueOf(obj[0].toString()));
				}else{  
					/*if(temp.getVillageWardVO()==null){
						temp.setVillageWardVO(new CommitteeSummaryVO());
					}
					temp.getVillageWardVO().setMembersCount(Long.valueOf(obj[0].toString()));
					*/
					Long committeeId = Long.valueOf(obj[2].toString());
					if(temp.getVillageWardVO()==null){
						temp.setVillageWardVO(new CommitteeSummaryVO());
					}
					if(committeeId.longValue() == 1L)
					{
						temp.getVillageWardVO().setMembersCount(Long.valueOf(obj[0].toString()));
					}
					else
					{
						Long existingCount = temp.getVillageWardVO().getAfflMembersCount() != null ? temp.getVillageWardVO().getAfflMembersCount():0L;
						temp.getVillageWardVO().setAfflMembersCount(existingCount+Long.valueOf(obj[0].toString()));
					}
					
					
				}
				
			}
		}
	}
	
	public void pushTotalCountsForConstituency(String type,List<Object[]> memResLst, List<CommitteeSummaryVO> fnlLst){
		if(memResLst!=null && memResLst.size()>0){
			for(Object[] obj:memResLst){
				CommitteeSummaryVO temp = getMatchedConstituency(Long.valueOf(obj[1].toString()), fnlLst);
				if(temp==null){
					temp = new CommitteeSummaryVO();
				}
				
				if(type.equalsIgnoreCase("munci")){
					if(temp.getTownMandalDivisionVO()==null){
						temp.setTownMandalDivisionVO(new CommitteeSummaryVO());
					}
					temp.getTownMandalDivisionVO().setTotalCommittees(Long.valueOf(obj[0].toString()));
				}else{
					/*if(temp.getVillageWardVO()==null){
						temp.setVillageWardVO(new CommitteeSummaryVO());
					}
					temp.getVillageWardVO().setTotalCommittees(Long.valueOf(obj[0].toString()));*/
					
					Long committeeId = Long.valueOf(obj[2].toString());
					if(temp.getVillageWardVO()==null){
						temp.setVillageWardVO(new CommitteeSummaryVO());
					}
					if(committeeId.longValue() == 1L)
					{
						temp.getVillageWardVO().setTotalCommittees(Long.valueOf(obj[0].toString()));
					}
					else
					{
						Long existingCount = temp.getVillageWardVO().getTotalAffilatedCommittees() != null ? temp.getVillageWardVO().getTotalAffilatedCommittees():0L;
						temp.getVillageWardVO().setTotalAffilatedCommittees(existingCount+Long.valueOf(obj[0].toString()));
					}
				}
				
			}
		}
	}
	
	public void pushResultConstiWise(String type,List<Object[]> memResLst, List<CommitteeSummaryVO> fnlLst, String resType){
		if(memResLst!=null && memResLst.size()>0){
			for(Object[] obj:memResLst){
				CommitteeSummaryVO temp = getMatchedConstituency(Long.valueOf(obj[2].toString()), fnlLst);
				
				if(temp==null){
					temp = new CommitteeSummaryVO();
				}
				
				
				if(type.equalsIgnoreCase("munci")){
					if(temp.getTownMandalDivisionVO()==null){
						temp.setTownMandalDivisionVO(new CommitteeSummaryVO());
					}
					if(Long.valueOf(obj[1].toString()).equals(1l)){
						if(resType.equalsIgnoreCase("start")){	
							temp.getTownMandalDivisionVO().setMainStarted(Long.valueOf(obj[0].toString()));
						}else{
							temp.getTownMandalDivisionVO().setMainCompleted(Long.valueOf(obj[0].toString()));
						}
					}else{
						if(resType.equalsIgnoreCase("start")){
							temp.getTownMandalDivisionVO().setAfflStarted(Long.valueOf(obj[0].toString()));
						}else{
							temp.getTownMandalDivisionVO().setAfflCompleted(Long.valueOf(obj[0].toString()));
						}
						
						setCommitteeParameterValues(temp,Long.valueOf(obj[3].toString()),resType, Long.valueOf(obj[0].toString()));
						
					}
					
				}else{
					if(temp.getVillageWardVO()==null){
						temp.setVillageWardVO(new CommitteeSummaryVO());
					}
					if(Long.valueOf(obj[1].toString()).equals(1l)){
						if(resType.equalsIgnoreCase("start")){	
							temp.getVillageWardVO().setMainStarted(Long.valueOf(obj[0].toString()));
						}else{
							temp.getVillageWardVO().setMainCompleted(Long.valueOf(obj[0].toString()));
						}
					}else{
						if(resType.equalsIgnoreCase("start")){	
							temp.getVillageWardVO().setAfflStarted(Long.valueOf(obj[0].toString()));
						}else{
							temp.getVillageWardVO().setAfflCompleted(Long.valueOf(obj[0].toString()));
						}
					}
					
				}
				
			}
		}
	}
	
	public CommitteeSummaryVO getMatchedConstituency(Long id, List<CommitteeSummaryVO> list){
		if(id!=null && list!=null && list.size()>0){
			for(CommitteeSummaryVO obj:list){
				if(obj.getConstiId().equals(id)){
					return obj;
				}
			}
		}
		return null;
	}
	public List<CommitteeApprovalVO> changeDesignationRecordsForAUser(Long userId,Long startNo,Long endNo){
		List<CommitteeApprovalVO> resultList=null;
		try
		{
			 List<Object[]> list1=null;
		   if(userId!=null){
		      list1= cadreCommitteeChangeDesignationsDAO.changeDesignationRecordsForAUser(userId);
		   }
		   else if(userId==null){
			  list1= cadreCommitteeChangeDesignationsDAO.changeDesignationRecordsForApproval(startNo.intValue(),endNo.intValue());
		   }
		   
		   
		   //getting Locations.
		   List<Object[]> list = tdpCommitteeLevelDAO.getAllLevels();	
		  
		    Map<Long, String> pancMap = new HashMap<Long, String>();
			Map<Long, String> tehsilMap = new HashMap<Long, String>();
			Map<Long, String> lebMap = new HashMap<Long, String>();
			Map<Long, String> wardMap = new HashMap<Long, String>();
			Map<Long, String> divisMap = new HashMap<Long, String>();
			
			List<CommitteeApprovalVO> locations = new ArrayList<CommitteeApprovalVO>();
			if(list!=null && list.size()>0){
				for(Object[] obj:list){
					CommitteeApprovalVO tmp = new CommitteeApprovalVO();
					tmp.setLocationTypeId(Long.valueOf(obj[0].toString()));
					tmp.setLocationType(obj[1].toString());
					locations.add(tmp);
				}
			}
			
			
			 if(list1!=null && list1.size()>0){
					for(Object[] obj:list1){
						CommitteeApprovalVO tmp = getMatchedLocation(Long.valueOf(obj[2].toString()),locations);
						if(tmp!=null){
							List<Long> lctnIds = tmp.getLocationIds();
							if(lctnIds==null){
								lctnIds = new ArrayList<Long>();
							}
							lctnIds.add(Long.valueOf(obj[4].toString()));
							
							tmp.setLocationIds(lctnIds);
						}
					}
				}
			 
			 if(locations.size()>0){
					for(CommitteeApprovalVO tmp:locations){
						if(tmp.getLocationIds()!=null && tmp.getLocationIds().size()>0){
							if(tmp.getLocationTypeId().equals(6l)){
								List<Object[]> panchayats =  panchayatDAO.getPanchayatsByPanchayatIdsListAlongMandal(tmp.getLocationIds());
								if(panchayats!=null && panchayats.size()>0){
									for(Object[] obj:panchayats){
										pancMap.put(Long.valueOf(obj[0].toString()), obj[1].toString());
									}
								}
							}
							
							if(tmp.getLocationTypeId().equals(5l)){
								List<Object[]> tehsils =  tehsilDAO.getTehsilNameByTehsilIdsList(tmp.getLocationIds());
								if(tehsils!=null && tehsils.size()>0){
									for(Object[] obj:tehsils){
										tehsilMap.put(Long.valueOf(obj[0].toString()), obj[1].toString());
									}
								}
							}
							
							if(tmp.getLocationTypeId().equals(7l)){
								List<Object[]> lebs =  localElectionBodyDAO.findByLocalElecBodyIds(tmp.getLocationIds());
								if(lebs!=null && lebs.size()>0){
									for(Object[] obj:lebs){
										lebMap.put(Long.valueOf(obj[0].toString()), obj[1].toString()+" "+obj[2].toString());
									}
								}
							}
							
							if(tmp.getLocationTypeId().equals(8l)){
								List<Object[]> wards =  constituencyDAO.getWardsNameInLocalElectionBodyByWardIds(tmp.getLocationIds());
								if(wards!=null && wards.size()>0){
									for(Object[] obj:wards){
										wardMap.put(Long.valueOf(obj[0].toString()), obj[1].toString()+" ("+obj[2].toString()+")");
									}
								}
							}
							
							if(tmp.getLocationTypeId().equals(9l)){
								List<Object[]> divis =  constituencyDAO.getWardsNameInLocalElectionBodyByWardIds(tmp.getLocationIds());
								if(divis!=null && divis.size()>0){
									for(Object[] obj:divis){
										divisMap.put(Long.valueOf(obj[0].toString()), obj[1].toString()+" ("+obj[2].toString()+")");
									}
								}
							}
						}
					}
				}
		   //locations Complete
		//Get SAME REQUEST RECORDS.
			 Map<Long,CommitteeApprovalVO> resultMap=new LinkedHashMap<Long, CommitteeApprovalVO>();
		    if(list1!=null && list1.size()>0){
		    	for (Object[] obj : list1){
		    		  CommitteeApprovalVO	mainVO=resultMap.get((Long)obj[0]);
		    		  if(mainVO==null){
		    			CommitteeApprovalVO committeeApprovalvo=new CommitteeApprovalVO();
		    			committeeApprovalvo.setLocationTypeId(Long.valueOf(obj[2].toString()));
		    			committeeApprovalvo.setLocationType(obj[3].toString());
		    			committeeApprovalvo.setLocationId(Long.valueOf(obj[4].toString()));
			    		String location = "";
						if(committeeApprovalvo.getLocationTypeId().equals(5l)){
							location = tehsilMap.get(committeeApprovalvo.getLocationId());
						}
						if(committeeApprovalvo.getLocationTypeId().equals(6l)){
							location = pancMap.get(committeeApprovalvo.getLocationId());					
						}
						if(committeeApprovalvo.getLocationTypeId().equals(7l)){
							location = lebMap.get(committeeApprovalvo.getLocationId());
						}
						if(committeeApprovalvo.getLocationTypeId().equals(8l)){
							location = wardMap.get(committeeApprovalvo.getLocationId());					
						}
						if(committeeApprovalvo.getLocationTypeId().equals(9l)){
							location = divisMap.get(committeeApprovalvo.getLocationId());
						}
						committeeApprovalvo.setLocation(location);
						
						committeeApprovalvo.setCommitteeId(Long.valueOf(obj[5].toString()));
						committeeApprovalvo.setCommitteeName(obj[6].toString());
						committeeApprovalvo.setStatus(obj[14].toString());
						committeeApprovalvo.setDateString(new SimpleDateFormat("dd-MM-yyyy").format(obj[1]));
						committeeApprovalvo.setRefNo(obj[15].toString());
		    			resultMap.put((Long)obj[0], committeeApprovalvo);
		    		}
		    		CommitteeApprovalVO	mainvo=resultMap.get((Long)obj[0]);
		    		
		    		CommitteeApprovalVO subvo=new CommitteeApprovalVO();
		    		subvo.setCurrentRoleId((Long)obj[10]);
		    		subvo.setCurrentRole(obj[11].toString());
		    		subvo.setNewRoleId((Long)obj[12]);
		    		subvo.setNewRole(obj[13].toString());
		    		subvo.setPosition(obj[7].toString());
		    		subvo.setPositionId(obj[8].toString());
		    		subvo.setTdpCommitteeMemberId((Long)obj[16]);
		    		if(obj[9].toString().trim().length() > 8){
		    			subvo.setMemberShipNo(obj[9].toString().trim().substring(obj[9].toString().trim().length()-8));
					}else{
						subvo.setMemberShipNo(obj[9].toString());
					}
		    		
		    		if(mainvo.getLocationsList()==null){
		    		 mainvo.setLocationsList(new ArrayList<CommitteeApprovalVO>());
		    		 mainvo.getLocationsList().add(subvo);
		    		}
		    		else
		    			 mainvo.getLocationsList().add(subvo);
		    		
				}
		      }
			 
		    resultList=new ArrayList<CommitteeApprovalVO>();
		    resultList.addAll(resultMap.values());
		   // System.out.println(resultList);
		}catch(Exception e)
		{
			LOG.error("Exception raised in changeDesignationRecordsForAUser", e);
		}
		return resultList;
		
	}
	
	public void constiCountForMandalTownDivisions(List<Long> constituencyIds,Date startDate, Date endDate,
			Map<Long,Long> mainMembersCountMap,Map<Long,Long> afflMembersCountMap,Map<Long,Long> startedCommitteesCountMap,Map<Long,Long> completedCommitteesCountMap,
			Map<Long,Long> startedCommitteesAffCountMap,Map<Long,Long> completedCommitteesAffCountMap,Map<Long,Long> mainCommitteesCountMap,
			Map<Long,Long>  startedYuvathaCommitteesAffCountMap,Map<Long,Long>  completedYuvathaCommitteesAffCountMap,
			Map<Long,Long>  startedMahilaCommitteesAffCountMap,Map<Long,Long>  completedMahilaCommitteesAffCountMap,
			Map<Long,Long>  startedRythuCommitteesAffCountMap,Map<Long,Long>  completedRythuCommitteesAffCountMap,
			Map<Long,Long>  startedOthersCommitteesAffCountMap,Map<Long,Long>  completedOthersCommitteesAffCountMap,List<CadreCommitteeReportVO> basicCmmty,
			String reqLocationTypeStr,List<Long> committeeEnrollmentIdsLst,List<Long>levelIdsList){
			Map<Long,List<Long>> mandalIdsMap = new HashMap<Long,List<Long>>();//Map<id,List<1constituencyId>>
			Map<Long,List<Long>> localBodiesMap = new HashMap<Long,List<Long>>();//Map<id,List<1constituencyId>>
			List<Long> divisionLclIds = new ArrayList<Long>();//Map<id,List<1constituencyId>>
			Map<Long,List<Long>> divisionIdsMap = new HashMap<Long,List<Long>>();//Map<id,List<1constituencyId>>
		
		if(constituencyIds!=null && constituencyIds.size()>0){
			
			/* Map<Long,Long> mainMembersCountMap = new HashMap<Long,Long>();
			 Map<Long,Long> startedCommitteesCountMap = new HashMap<Long,Long>();
			 Map<Long,Long> completedCommitteesCountMap = new HashMap<Long,Long>();
			 Map<Long,Long> startedCommitteesAffCountMap = new HashMap<Long,Long>();
			 Map<Long,Long> completedCommitteesAffCountMap = new HashMap<Long,Long>();
			 Map<Long,Long> mainCommitteesCountMap = new HashMap<Long,Long>();*/
			 
			 getLocationsInfo1(constituencyIds, divisionLclIds, localBodiesMap, divisionIdsMap, mandalIdsMap);
			 
			 
			 if(mandalIdsMap.size() > 0){
				 List<Long> levelValues = new ArrayList<Long>(mandalIdsMap.keySet());
				 
				//0 count,1levelId
				 List<Object[]> totalMandalMainMembers = tdpCommitteeMemberDAO.totalMainMembersCountLocationsWise1(5l,startDate, endDate,constituencyIds,reqLocationTypeStr,committeeEnrollmentIdsLst,levelIdsList);
				//0count,1locationID
				 List<Object[]> totalMandalCommittees = tdpCommitteeDAO.totalCommitteesCountByLocationIds1(5l,constituencyIds,reqLocationTypeStr,committeeEnrollmentIdsLst,levelIdsList);
				 
				//0count,1locationID,2typeId
				 List<Object[]> totalMandalStartedCommittees = tdpCommitteeDAO.committeesCountByLocationIds1(5l,constituencyIds,startDate,endDate,"started",reqLocationTypeStr,committeeEnrollmentIdsLst,levelIdsList);
				//0count,1locationID,2typeId
				 List<Object[]> totalMandalCompletedCommittees = tdpCommitteeDAO.committeesCountByLocationIds1(5l,constituencyIds,startDate,endDate,"completed",reqLocationTypeStr,committeeEnrollmentIdsLst,levelIdsList);
				
				 
				    populateLocationCounts(
						 mainMembersCountMap, afflMembersCountMap,
						 mainCommitteesCountMap,
						 
						 startedCommitteesCountMap, 
						 startedCommitteesAffCountMap,
						 
						 completedCommitteesCountMap, 
						 completedCommitteesAffCountMap, 
						 
						 totalMandalMainMembers, 
						 totalMandalCommittees, 
						 totalMandalStartedCommittees, 
						 totalMandalCompletedCommittees, 
						 mandalIdsMap,startedYuvathaCommitteesAffCountMap,completedYuvathaCommitteesAffCountMap,
							startedMahilaCommitteesAffCountMap,completedMahilaCommitteesAffCountMap,startedRythuCommitteesAffCountMap,
							completedRythuCommitteesAffCountMap,startedOthersCommitteesAffCountMap,completedOthersCommitteesAffCountMap,basicCmmty);
			 }
			 if(localBodiesMap.size() > 0){
				 List<Long> levelValues = new ArrayList<Long>(localBodiesMap.keySet());
				 
				//0 count,1levelId
				 List<Object[]> totalLocalBodMainMembers = tdpCommitteeMemberDAO.totalMainMembersCountLocationsWise1(7l,startDate, endDate,constituencyIds,reqLocationTypeStr,committeeEnrollmentIdsLst,levelIdsList);
				//0count,1locationID
				 List<Object[]> totalLocalBodCommittees = tdpCommitteeDAO.totalCommitteesCountByLocationIds1(7l,constituencyIds,reqLocationTypeStr,committeeEnrollmentIdsLst,levelIdsList);
				 
				//0count,1locationID,2typeId
				 List<Object[]> totalLocalBodStartedCommittees = tdpCommitteeDAO.committeesCountByLocationIds1(7l,constituencyIds,startDate,endDate,"started",reqLocationTypeStr,committeeEnrollmentIdsLst,levelIdsList);
				//0count,1locationID,2typeId
				 List<Object[]> totalLocalBodCompletedCommittees = tdpCommitteeDAO.committeesCountByLocationIds1(7l,constituencyIds,startDate,endDate,"completed",reqLocationTypeStr,committeeEnrollmentIdsLst,levelIdsList);
				
				 
				 populateLocationCounts(
						 mainMembersCountMap,  afflMembersCountMap,
						 mainCommitteesCountMap,
						 
						 startedCommitteesCountMap, 
						 startedCommitteesAffCountMap,
						 
						 completedCommitteesCountMap, 
						 completedCommitteesAffCountMap, 
						 
						 totalLocalBodMainMembers, 
						 totalLocalBodCommittees, 
						 totalLocalBodStartedCommittees, 
						 totalLocalBodCompletedCommittees, 
						 localBodiesMap,startedYuvathaCommitteesAffCountMap,completedYuvathaCommitteesAffCountMap,
							startedMahilaCommitteesAffCountMap,completedMahilaCommitteesAffCountMap,startedRythuCommitteesAffCountMap,
							completedRythuCommitteesAffCountMap,startedOthersCommitteesAffCountMap,completedOthersCommitteesAffCountMap,basicCmmty);
			 }
			 if(divisionIdsMap.size() > 0){
				 List<Long> levelValues = new ArrayList<Long>(divisionIdsMap.keySet());
				 
				//0 count,1levelId
				 List<Object[]> totalDivisionMainMembers = tdpCommitteeMemberDAO.totalMainMembersCountLocationsWise1(9l,startDate, endDate,constituencyIds,reqLocationTypeStr,committeeEnrollmentIdsLst,levelIdsList);
				//0count,1locationID
				 List<Object[]> totalDivisionCommittees = tdpCommitteeDAO.totalCommitteesCountByLocationIds1(9l,constituencyIds,reqLocationTypeStr,committeeEnrollmentIdsLst,levelIdsList);
				 
				//0count,1locationID,2typeId
				 List<Object[]> totalDivisionStartedCommittees = tdpCommitteeDAO.committeesCountByLocationIds1(9l,constituencyIds,startDate,endDate,"started",reqLocationTypeStr,committeeEnrollmentIdsLst,levelIdsList);
				//0count,1locationID,2typeId
				 List<Object[]> totalDivisionCompletedCommittees = tdpCommitteeDAO.committeesCountByLocationIds1(9l,constituencyIds,startDate,endDate,"completed",reqLocationTypeStr,committeeEnrollmentIdsLst,levelIdsList);
				
				 
				 populateLocationCounts(
						 mainMembersCountMap,  afflMembersCountMap,
						 mainCommitteesCountMap,
						 
						 startedCommitteesCountMap, 
						 startedCommitteesAffCountMap,
						 
						 completedCommitteesCountMap, 
						 completedCommitteesAffCountMap, 
						 
						 totalDivisionMainMembers, 
						 totalDivisionCommittees, 
						 totalDivisionStartedCommittees, 
						 totalDivisionCompletedCommittees, 
						 divisionIdsMap,startedYuvathaCommitteesAffCountMap,completedYuvathaCommitteesAffCountMap,
							startedMahilaCommitteesAffCountMap,completedMahilaCommitteesAffCountMap,startedRythuCommitteesAffCountMap,
							completedRythuCommitteesAffCountMap,startedOthersCommitteesAffCountMap,completedOthersCommitteesAffCountMap,basicCmmty);
			 }
			 
		}
	}
	
	public void getLocationsInfo(List<Long> constituencyIds,List<Long> divisionLclIds,
			Map<Long,List<Long>> localBodiesMap,Map<Long,List<Long>> divisionIdsMap,Map<Long,List<Long>> mandalIdsMap){
		//0localBodyId,1constituencyId
		List<Object[]> localBodyIdsList = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituencyList(constituencyIds);
		for(Object[] localBody:localBodyIdsList){
			Long localBdyId = (Long)localBody[0];
			if((localBdyId.longValue() == 20l ||  localBdyId.longValue() == 124l || localBdyId.longValue() == 119l)){
				divisionLclIds.add(localBdyId);
        	}else{
        		List<Long> constiIds = localBodiesMap.get(localBdyId);
        		if(constiIds == null){
        			constiIds = new ArrayList<Long>();
        			localBodiesMap.put(localBdyId,constiIds);
        		}
        		constiIds.add((Long)localBody[3]);
        	}
		}
		if(divisionLclIds.size() > 0){
			List<Object[]> divisionIdsList = assemblyLocalElectionBodyWardDAO.findWardsByLocalBodyIds(divisionLclIds);
			for(Object[] division:divisionIdsList){
				Long divisionId = (Long)division[0];
				List<Long> constiIds = divisionIdsMap.get(divisionId);
        		if(constiIds == null){
        			constiIds = new ArrayList<Long>();
        			divisionIdsMap.put(divisionId,constiIds);
        		}
        		constiIds.add((Long)division[1]);
			}
		}
		 List<BigInteger> delimitationIds = delimitationConstituencyDAO.getDelimitIds(constituencyIds);
		 if(delimitationIds.size() > 0){
			 List<Long> ids = new ArrayList<Long>();
			 for(BigInteger delimitationId:delimitationIds){
				 ids.add(delimitationId.longValue());
			 }
			 List<Object[]> tehsilIdsList = delimitationConstituencyMandalDAO.getTehsilsByDelimitationConstituencyIDs(ids) ;
			 for(Object[] tehsil:tehsilIdsList){
					Long tehsilId = (Long)tehsil[0];
					List<Long> tehsilIds = mandalIdsMap.get(tehsilId);
	        		if(tehsilIds == null){
	        			tehsilIds = new ArrayList<Long>();
	        			mandalIdsMap.put(tehsilId,tehsilIds);
	        		}
	        		tehsilIds.add((Long)tehsil[1]);
				}
		 }
	}
	
	public void getLocationsInfo1(List<Long> constituencyIds,List<Long> divisionLclIds,
			Map<Long,List<Long>> localBodiesMap,Map<Long,List<Long>> divisionIdsMap,Map<Long,List<Long>> mandalIdsMap){
		//0localBodyId,1constituencyId
		List<Object[]> localBodyIdsList = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituencyList(constituencyIds);
		for(Object[] localBody:localBodyIdsList){
			Long constiteucnyId = (Long)localBody[3]; // constiteucnyId
			Long localBdyId = (Long)localBody[0];
			if((localBdyId.longValue() == 20l ||  localBdyId.longValue() == 124l || localBdyId.longValue() == 119l)){
				divisionLclIds.add(localBdyId);
        	}else{
        		List<Long> constiIds = localBodiesMap.get(constiteucnyId);
        		if(constiIds == null){
        			constiIds = new ArrayList<Long>();
        			localBodiesMap.put(constiteucnyId,constiIds);
        		}
        		constiIds.add((Long)localBody[0]);
        	}
		}
		if(divisionLclIds.size() > 0){
			List<Object[]> divisionIdsList = assemblyLocalElectionBodyWardDAO.findWardsByLocalBodyIds(divisionLclIds);
			for(Object[] division:divisionIdsList){
				Long divisionId = (Long)division[1]; // constiteucnyId
				List<Long> constiIds = divisionIdsMap.get(divisionId);
        		if(constiIds == null){
        			constiIds = new ArrayList<Long>();
        			divisionIdsMap.put(divisionId,constiIds);
        		}
        		constiIds.add((Long)division[0]);
			}
		}
		 List<BigInteger> delimitationIds = delimitationConstituencyDAO.getDelimitIds(constituencyIds);
		 if(delimitationIds.size() > 0){
			 List<Long> ids = new ArrayList<Long>();
			 for(BigInteger delimitationId:delimitationIds){
				 ids.add(delimitationId.longValue());
			 }
			 List<Object[]> tehsilIdsList = delimitationConstituencyMandalDAO.getTehsilsByDelimitationConstituencyIDs(ids) ;
			 for(Object[] tehsil:tehsilIdsList){
					Long tehsilId = (Long)tehsil[1]; // constiteucnyId
					List<Long> tehsilIds = mandalIdsMap.get(tehsilId);
	        		if(tehsilIds == null){
	        			tehsilIds = new ArrayList<Long>();
	        			mandalIdsMap.put(tehsilId,tehsilIds);
	        		}
	        		tehsilIds.add((Long)tehsil[0]);
				}
		 }
	}
	
	private void populateLocationCounts(
			 Map<Long,Long> mainMembersCountMap,
			 Map<Long,Long> afflMembersCountMap,
			 Map<Long,Long> mainCommitteesCountMap,
			 
			 Map<Long,Long> startedCommitteesCountMap,
			 Map<Long,Long> startedCommitteesAffCountMap,
			 
			 Map<Long,Long> completedCommitteesCountMap,			 
			 Map<Long,Long> completedCommitteesAffCountMap,
			 
			 List<Object[]> totalMainMembers,
			 List<Object[]> totalCommittees,
			 
			 List<Object[]> totalStartedCommittees,
			 
			 List<Object[]> totalCompletedCommittees,
			 
			 Map<Long,List<Long>> locationsMap,Map<Long,Long>  startedYuvathaCommitteesAffCountMap,Map<Long,Long>  completedYuvathaCommitteesAffCountMap,
				Map<Long,Long>  startedMahilaCommitteesAffCountMap,Map<Long,Long>  completedMahilaCommitteesAffCountMap,
				Map<Long,Long>  startedRythuCommitteesAffCountMap,Map<Long,Long>  completedRythuCommitteesAffCountMap,
				Map<Long,Long> startedOthersCommitteesAffCountMap,Map<Long,Long>  completedOthersCommitteesAffCountMap,List<CadreCommitteeReportVO> basicCmmty){
		
		     populateMainCounts(totalMainMembers,locationsMap,mainMembersCountMap,afflMembersCountMap);
		     populateMainCounts(totalCommittees,locationsMap,mainCommitteesCountMap,afflMembersCountMap);
		     populateMainAndAffCounts("Started",totalStartedCommittees,locationsMap,startedCommitteesCountMap,startedCommitteesAffCountMap,startedYuvathaCommitteesAffCountMap,
		    		 startedMahilaCommitteesAffCountMap,startedRythuCommitteesAffCountMap,startedOthersCommitteesAffCountMap,basicCmmty);
		     populateMainAndAffCounts("Completed",totalCompletedCommittees,locationsMap,completedCommitteesCountMap,completedCommitteesAffCountMap,completedYuvathaCommitteesAffCountMap,
		    		 completedMahilaCommitteesAffCountMap,completedRythuCommitteesAffCountMap,completedOthersCommitteesAffCountMap,basicCmmty);
		
	}
	
	private void populateMainCounts(List<Object[]> mainMembers,Map<Long,List<Long>> locationsMap,Map<Long,Long> countMap,Map<Long,Long> afflMembersCountMap){
		//0 count,1levelId
		for(Object[] countArr:mainMembers){
			List<Long> constituenciesIds = locationsMap.get((Long)countArr[1]);
			if(constituenciesIds != null && constituenciesIds.size() > 0){
				//for(Long constituenciesId:constituenciesIds){
					Long commiteeId = countArr[2] != null ? Long.valueOf(countArr[2].toString().trim()):0L;
					if(commiteeId.longValue() >0L)
					{
						if(commiteeId.longValue() ==1L)
						{
							Long count = countMap.get((Long)countArr[1]);
							if(count == null){
								countMap.put((Long)countArr[1],(Long)countArr[0]);
							}else{
								countMap.put((Long)countArr[1],count+(Long)countArr[0]);
							}
						}
						else
						{
							Long count = afflMembersCountMap.get((Long)countArr[1]);
							if(count == null){
								afflMembersCountMap.put((Long)countArr[1],(Long)countArr[0]);
							}else{
								afflMembersCountMap.put((Long)countArr[1],count+(Long)countArr[0]);
							}
						}
				//	}
					
				}
			}
		}
	}
	
	public CadreCommitteeReportVO getMatchedCadreCommitteeReportVOById(List<CadreCommitteeReportVO> basicCmmty, Long id)
	{
		CadreCommitteeReportVO returnVO = null;
		try {
			
			if(basicCmmty != null && basicCmmty.size()>0)
			{
				for (CadreCommitteeReportVO committeeVO : basicCmmty) {
					if(committeeVO.getId().longValue() == id.longValue())
					{
						return committeeVO;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getMatchedCadreCommitteeReportVOById", e);
		}
		return returnVO;
	}
	private void populateMainAndAffCounts(String committeeType, List<Object[]> mainAffMembers,Map<Long,List<Long>> locationsMap,Map<Long,Long> mainCountMap,Map<Long,Long> affCountMap,
			Map<Long,Long>  yuvathaCommitteesAffCountMap,Map<Long,Long>  mahilaCommitteesAffCountMap,Map<Long,Long>  rythuCommitteesAffCountMap,
			Map<Long,Long>   othersCommitteesAffCountMap,List<CadreCommitteeReportVO> basicCmmty){
		List<CadreCommitteeReportVO> cadreCommitteeReportVOList = null;
		//0 count,1levelId,2typeId
		for(Object[] countArr:mainAffMembers){

			Map<Long,Long> countMap = mainCountMap;
			Map<Long,Long> afflCountMap = mainCountMap;
			if(((Long)countArr[2]).longValue() == 2l){
				countMap = affCountMap;
				
				if(((Long)countArr[3]).longValue() == 2l){
					afflCountMap = yuvathaCommitteesAffCountMap;
				}
				else if(((Long)countArr[3]).longValue() == 3l){
					afflCountMap = mahilaCommitteesAffCountMap;
				}
				else if(((Long)countArr[3]).longValue() == 4l){
					afflCountMap = rythuCommitteesAffCountMap;
				}
				else
				{
					afflCountMap = othersCommitteesAffCountMap;							
				}				
			}			
			
			List<Long> constituenciesIds = locationsMap.get((Long)countArr[1]);
			if(constituenciesIds != null && constituenciesIds.size() > 0){
				//for(Long constituenciesId:constituenciesIds){
					Long constituenciesId = commonMethodsUtilService.getLongValueForObject(countArr[1]);
					Long count = countMap.get(constituenciesId);
					if(count == null){
						countMap.put(constituenciesId,(Long)countArr[0]);
					}else{
						countMap.put(constituenciesId,count+(Long)countArr[0]);
					}
					
					if(((Long)countArr[2]).longValue() == 2l){
						Long afflCount =afflCountMap.get(constituenciesId);
						
							if(afflCount == null){
								afflCountMap.put(constituenciesId,(Long)countArr[0]);
							}else{
								afflCountMap.put(constituenciesId,afflCount+(Long)countArr[0]);
							}
							
							CadreCommitteeReportVO committeeVO = getMatchedCadreCommitteeReportVOById(basicCmmty,((Long)countArr[3]).longValue());
							if(constituenciesId.longValue() == 248L)
							{
								//System.out.println("Setting values");
							}
							if(committeeVO != null)
							{		
								CadreCommitteeReportVO locationWiseCommitteeVO = getMatchedVOListByLocationId(committeeVO.getCadreCommitteeReportVOList(),committeeVO.getLocationId());
								if(locationWiseCommitteeVO == null)
								{
									locationWiseCommitteeVO = new CadreCommitteeReportVO();
								}
								locationWiseCommitteeVO.setLocationId(constituenciesId);
								if(committeeType.equalsIgnoreCase("Started"))
								{							
									Long presentCount = locationWiseCommitteeVO.getStartedCommittees() != null ? locationWiseCommitteeVO.getStartedCommittees():0L;
									locationWiseCommitteeVO.setStartedCommittees(presentCount + ((Long)countArr[0]).longValue());
								}
								else if(committeeType.equalsIgnoreCase("Completed"))
								{
									Long presentCount = locationWiseCommitteeVO.getCompletedCommittees() != null ? locationWiseCommitteeVO.getCompletedCommittees():0L;
									locationWiseCommitteeVO.setCompletedCommittees(presentCount + ((Long)countArr[0]).longValue());
								}
														
								committeeVO.getCadreCommitteeReportVOList().add(locationWiseCommitteeVO);
							}		
						}
						
						
						
						/*
						CadreCommitteeReportVO committeeVO = getMatchedCadreCommitteeReportVOById(basicCmmty,((Long)countArr[3]).longValue());
						
						if(committeeVO != null)
						{		
							CadreCommitteeReportVO locationWiseCommitteeVO = new CadreCommitteeReportVO();
							locationWiseCommitteeVO.setLocationId(constituenciesId);
							if(committeeType.equalsIgnoreCase("Started"))
							{							
								Long presentCount = locationWiseCommitteeVO.getStartedCommittees() != null ? locationWiseCommitteeVO.getStartedCommittees():0L;
								locationWiseCommitteeVO.setStartedCommittees(presentCount + ((Long)countArr[0]).longValue());
							}
							else if(committeeType.equalsIgnoreCase("Completed"))
							{
								Long presentCount = locationWiseCommitteeVO.getCompletedCommittees() != null ? locationWiseCommitteeVO.getCompletedCommittees():0L;
								locationWiseCommitteeVO.setCompletedCommittees(presentCount + ((Long)countArr[0]).longValue());
							}
							
							cadreCommitteeReportVOList.add(locationWiseCommitteeVO);							
							committeeVO.setCadreCommitteeReportVOList(cadreCommitteeReportVOList);
						}		
						*/
					//}
					/*Long afflCount =afflCountMap.get(constituenciesId);
					if(afflCount == null){
						afflCountMap.put(constituenciesId,(Long)countArr[0]);
					}else{
						afflCountMap.put(constituenciesId,afflCount+(Long)countArr[0]);
					}*/
					/*
					if(afflCount == null){
						
						CadreCommitteeReportVO committeeVO = getMatchedCadreCommitteeReportVOById(basicCmmty,((Long)countArr[3]).longValue());
						
						if(committeeVO != null)
						{		
							CadreCommitteeReportVO locationWiseCommitteeVO = new CadreCommitteeReportVO();
							locationWiseCommitteeVO.setLocationId(constituenciesId);
							if(committeeType.equalsIgnoreCase("Started"))
							{							
								Long presentCount = locationWiseCommitteeVO.getStartedCommittees() != null ? locationWiseCommitteeVO.getStartedCommittees():0L;
								locationWiseCommitteeVO.setStartedCommittees(presentCount + ((Long)countArr[0]).longValue());
							}
							else if(committeeType.equalsIgnoreCase("Completed"))
							{
								Long presentCount = locationWiseCommitteeVO.getCompletedCommittees() != null ? locationWiseCommitteeVO.getCompletedCommittees():0L;
								locationWiseCommitteeVO.setCompletedCommittees(presentCount + ((Long)countArr[0]).longValue());
							}
							
							cadreCommitteeReportVOList.add(locationWiseCommitteeVO);							
							committeeVO.setCadreCommitteeReportVOList(cadreCommitteeReportVOList);
						}		
						afflCountMap.put(constituenciesId,(Long)countArr[0]);
						
					}else{
						
						CadreCommitteeReportVO committeeVO = getMatchedCadreCommitteeReportVOById(basicCmmty,((Long)countArr[3]).longValue());
						
						if(committeeVO != null)
						{
							CadreCommitteeReportVO locationWiseCommitteeVO = new CadreCommitteeReportVO();
							locationWiseCommitteeVO.setLocationId(constituenciesId);
							if(committeeType.equalsIgnoreCase("Started"))
							{							
								Long presentCount = locationWiseCommitteeVO.getStartedCommittees() != null ? locationWiseCommitteeVO.getStartedCommittees():0L;
								locationWiseCommitteeVO.setStartedCommittees(presentCount + ((Long)countArr[0]).longValue());
							}
							else if(committeeType.equalsIgnoreCase("Completed"))
							{
								Long presentCount = locationWiseCommitteeVO.getCompletedCommittees() != null ? locationWiseCommitteeVO.getCompletedCommittees():0L;
								locationWiseCommitteeVO.setCompletedCommittees(presentCount + ((Long)countArr[0]).longValue());
							}
							
							cadreCommitteeReportVOList.add(locationWiseCommitteeVO);							
							committeeVO.setCadreCommitteeReportVOList(cadreCommitteeReportVOList);
						}	
						
						afflCountMap.put(constituenciesId,afflCount+(Long)countArr[0]);
					}*/
				//}
			}
		}
	}
	
	public List<IdNameVO> getAllDistricts()
	{
	  List<IdNameVO> idNameVOList=null;
	  try
	 {
		List<Object[]> districtList=districtDAO.getAllDistrictDetailsForAState(1l);
		if(districtList!=null && districtList.size()>0){
			idNameVOList=new ArrayList<IdNameVO>();
			for (Object[] objects : districtList){
				IdNameVO idNameVO=new IdNameVO();
				idNameVO.setId((Long)objects[0]);
				idNameVO.setName(objects[1].toString());
				idNameVOList.add(idNameVO);
			}
		 }
	 }
	 catch (Exception e){
		LOG.error("Exception raised in getAllDistricts", e);
	 }	
	 return  idNameVOList;
	}
	public List<IdNameVO> getAllConstituencysForADistrict(Long districtId)
	{
	  List<IdNameVO> idNameVOList=null;
	  try
	 {
		List<Object[]> constituencyList=constituencyDAO.getAllConstituenciesInADistrict(districtId);
		if(constituencyList!=null && constituencyList.size()>0){
			idNameVOList=new ArrayList<IdNameVO>();
			for (Object[] objects : constituencyList){
				IdNameVO idNameVO=new IdNameVO();
				idNameVO.setId((Long)objects[0]);
				idNameVO.setName(objects[1].toString());
				idNameVOList.add(idNameVO);
			}
		 }
	 }
	 catch (Exception e){
		LOG.error("Exception raised in getAllConstituencysForADistrict", e);
	 }	
	 return  idNameVOList;
	}
	
	public CommitteeSummaryVO getConstituencySummary(Long reprtType, Long constituencyId,Long userId,Long committeeTypeId,List<Long> enrollIdsList,String startDateStr,String endDateStr){
		CommitteeSummaryVO fnlVO = new CommitteeSummaryVO();
			LOG.debug(" Entered Into getConstituencySummary()");
			try{
				fnlVO.setAccessState(constituencyDAO.getConstituencyNameByConstituencyId(constituencyId));
				List<LocationWiseBoothDetailsVO> svList = getMandalMunicCorpDetailsNew(constituencyId);
				List<Long> mandalIds = new ArrayList<Long>();
				List<Long> localBodyIds = new ArrayList<Long>();
				List<Long> divisionIds = new ArrayList<Long>();
				
				List<CommitteeSummaryVO> localBodies = new ArrayList<CommitteeSummaryVO>();
				List<CommitteeSummaryVO> mandals = new ArrayList<CommitteeSummaryVO>();
				List<CommitteeSummaryVO> divisions = new ArrayList<CommitteeSummaryVO>();
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date startDate = null;
				Date endDate = null;
				if(startDateStr != null && endDateStr != null){
					startDate = sdf.parse(startDateStr);
					endDate = sdf.parse(endDateStr);
				}
				
				if(svList!=null && svList.size()>0){
					for(LocationWiseBoothDetailsVO temp:svList){
						if(temp.getLocationId().toString().substring(0,1).trim().equalsIgnoreCase("1")){
			        		localBodyIds.add(Long.valueOf(temp.getLocationId().toString().substring(1)));
			        		
			        		CommitteeSummaryVO lv = new CommitteeSummaryVO();
			        		lv.setLocationId(Long.valueOf(temp.getLocationId().toString().substring(1)));
			        		lv.setLocationName(temp.getLocationName());
			        		localBodies.add(lv);
			        		
			        	}else if(temp.getLocationId().toString().substring(0,1).trim().equalsIgnoreCase("2")){
			        		mandalIds.add(Long.valueOf(temp.getLocationId().toString().substring(1)));
			        		
			        		CommitteeSummaryVO lv = new CommitteeSummaryVO();
			        		lv.setLocationId(Long.valueOf(temp.getLocationId().toString().substring(1)));
			        		lv.setLocationName(temp.getLocationName());
			        		mandals.add(lv);
			        		
			        	}else if(temp.getLocationId().toString().substring(0,1).trim().equalsIgnoreCase("3")){
			        		divisionIds.add(Long.valueOf(temp.getLocationId().toString().substring(1)));
			        		
			        		CommitteeSummaryVO lv = new CommitteeSummaryVO();
			        		lv.setLocationId(Long.valueOf(temp.getLocationId().toString().substring(1)));
			        		lv.setLocationName(temp.getLocationName());
			        		divisions.add(lv);
			        	}
					}
				}
				
				Map<Long,List<CommitteeSummaryVO>> mandalMap = new HashMap<Long, List<CommitteeSummaryVO>>();
				Map<Long,List<CommitteeSummaryVO>> wardMap = new HashMap<Long, List<CommitteeSummaryVO>>();
				List<Long> panchIds = new ArrayList<Long>();
				List<Long> wardIds = new ArrayList<Long>();
				List<CommitteeSummaryVO> panchList = null;
				List<CommitteeSummaryVO> allPanchayats = new ArrayList<CommitteeSummaryVO>();
				List<CommitteeSummaryVO> allWardsList = new ArrayList<CommitteeSummaryVO>();
				List<CommitteeSummaryVO> wardsList = null;
				if(reprtType.equals(1l)){
					 if(mandalIds.size() > 0){
				        	//0panchayatId,1panchayatName,2tehsilName
				        	List<Object[]> panchayatsList = panchayatDAO.getAllPanchayatsWithTehsilIdsInMandals(mandalIds);
						 //List<Object[]> panchayatsList = tdpCommitteeDAO.getAllPanchayatsWithTehsilIdsInMandals(mandalIds);
				        	for(Object[] panchayat:panchayatsList){
				        		CommitteeSummaryVO vo = new CommitteeSummaryVO();
					        	vo.setLocationId(Long.valueOf((Long)panchayat[0]));
					        	vo.setLocationName(panchayat[1].toString()+"("+panchayat[2].toString()+")");
					        	
					        	panchList = mandalMap.get(Long.valueOf(panchayat[3].toString()));
					        	if(panchList==null){
					        		panchList = new ArrayList<CommitteeSummaryVO>();
					        	}
					        	panchList.add(vo);
					        	allPanchayats.add(vo);
					        	panchIds.add(Long.valueOf((Long)panchayat[0]));
					        	mandalMap.put(Long.valueOf(panchayat[3].toString()), panchList);
				        	}
				        }
				        if(localBodyIds.size() > 0){
				        	//0wardId,1pwardName,2localBdyName
				        	List<Object[]> localBodyList = constituencyDAO.getWardsAndLEBIdsInLocalElectionBody(localBodyIds);
				        	//List<Object[]> localBodyList = tdpCommitteeDAO.getWardsAndLEBIdsInLocalElectionBody(localBodyIds);
				        	for(Object[] localBody:localBodyList){
				        		CommitteeSummaryVO vo = new CommitteeSummaryVO();
					        	vo.setLocationId(Long.valueOf((Long)localBody[0]));
					        	vo.setLocationName(localBody[1].toString()+"("+localBody[2].toString()+")");
					        	
					        	wardsList = wardMap.get(Long.valueOf(localBody[3].toString()));
					        	if(wardsList==null){
					        		wardsList = new ArrayList<CommitteeSummaryVO>();
					        	}
					        	wardIds.add(Long.valueOf((Long)localBody[0]));
					        	wardsList.add(vo);
					        	allWardsList.add(vo);
					        	wardMap.put(Long.valueOf(localBody[3].toString()), wardsList);
					        	
				        	}
				        }
				}
				
				
				List<Object[]> basicCommitteesRslt = tdpBasicCommitteeDAO.getBasicCommitteesByTypeId(committeeTypeId);
				List<CommitteeSummaryVO> basicCmmty = new ArrayList<CommitteeSummaryVO>();
				if(basicCommitteesRslt!=null && basicCommitteesRslt.size()>0){
					for(Object[] obj:basicCommitteesRslt){
						CommitteeSummaryVO vo = new CommitteeSummaryVO();
						vo.setBasicCommitteeTypeId(Long.valueOf(obj[0].toString()));
						vo.setBasicCommitteeName(obj[1].toString());
						basicCmmty.add(vo);
					}
					fnlVO.setResultList(basicCmmty);
				}
				
				if(localBodies!=null && localBodies.size()>0){
					List<Object[]> list = tdpCommitteeMemberDAO.getCommitteeMembersCountByLocationAndCommitteeType(7l, localBodyIds,committeeTypeId,enrollIdsList,startDate,endDate);
					List<CommitteeSummaryVO> locsResult = pushBasicCommitteesToLocations(basicCommitteesRslt, localBodies);
					pushConstSummaryToLocations(list, locsResult);
					
					fnlVO.setLocalBodiesList(localBodies);
				}
				if(mandalIds!=null && mandalIds.size()>0){
					List<Object[]> list = tdpCommitteeMemberDAO.getCommitteeMembersCountByLocationAndCommitteeType(5l, mandalIds,committeeTypeId,enrollIdsList,startDate,endDate);
					List<CommitteeSummaryVO> locsResult =  pushBasicCommitteesToLocations(basicCommitteesRslt, mandals);
					pushConstSummaryToLocations(list, locsResult);
					
					fnlVO.setMandalsList(mandals);
				}
				if(divisionIds!=null && divisionIds.size()>0){
					List<Object[]> list = tdpCommitteeMemberDAO.getCommitteeMembersCountByLocationAndCommitteeType(9l, divisionIds,committeeTypeId,enrollIdsList,startDate,endDate);
					List<CommitteeSummaryVO> locsResult =  pushBasicCommitteesToLocations(basicCommitteesRslt, divisions);
					pushConstSummaryToLocations(list, locsResult);
					
					fnlVO.setDivisionList(divisions);
				}
				 
				if(panchIds!=null && panchIds.size()>0){
					List<Object[]> list = tdpCommitteeMemberDAO.getCommitteeMembersCountByLocationAndCommitteeType(6l, panchIds,committeeTypeId,enrollIdsList,startDate,endDate);
					List<CommitteeSummaryVO> locsResult =  pushBasicCommitteesToLocations(basicCommitteesRslt, allPanchayats);
					pushConstSummaryToLocations(list, locsResult);
					
					List<Object[]> electedMems = tdpCommitteeMemberDAO.getCommitteePresidentAndVicePresidentsCount(panchIds, 6l,committeeTypeId,enrollIdsList,startDate,endDate);
					
					List<Object[]> electedUsers = tdpCommitteeMemberDAO.getCommitteePresidentAndGS(panchIds, 6l,committeeTypeId,enrollIdsList,startDate,endDate);
					List<Long> eletedMemIds = new ArrayList<Long>();
					if(electedUsers!=null && electedUsers.size()>0){
						for(Object[] obj:electedUsers){
							eletedMemIds.add(Long.valueOf(obj[0].toString()));
						}
					}
					
					List<Object[]> electrolsRslt = new ArrayList<Object[]>();
					List<Object[]> electrolsRsltAffl = new ArrayList<Object[]>();
					
						electrolsRslt = tdpCommitteeElectrolsDAO.getElectrolsForPanchayatsWardsWithOutDuplicates(panchIds, "panchayat", eletedMemIds,enrollIdsList,startDate,endDate);
						electrolsRsltAffl = tdpCommitteeElectrolsDAO.getElectrolsForPanchayatsWards(panchIds, "panchayat",enrollIdsList,startDate,endDate);
						
						if(electrolsRsltAffl!=null && electrolsRsltAffl.size()>0){
							electrolsRslt.addAll(electrolsRsltAffl);
						}
					
					
					
					pushElectrolsCount(electrolsRslt, locsResult, "");
					pushElectrolsCount(electedMems, locsResult, "members");
					
					
					pushPanchayatsAndWards(mandalMap, fnlVO.getMandalsList());
				}
				if(wardIds!=null && wardIds.size()>0){
					List<Object[]> list = tdpCommitteeMemberDAO.getCommitteeMembersCountByLocationAndCommitteeType(8l, wardIds,committeeTypeId,enrollIdsList,startDate,endDate);
					List<CommitteeSummaryVO> locsResult =  pushBasicCommitteesToLocations(basicCommitteesRslt, allWardsList);
					pushConstSummaryToLocations(list, locsResult);
					
					List<Object[]> electedMems = tdpCommitteeMemberDAO.getCommitteePresidentAndVicePresidentsCount(wardIds, 8l,committeeTypeId,enrollIdsList,startDate,endDate);
					
					List<Object[]> electedUsers = tdpCommitteeMemberDAO.getCommitteePresidentAndGS(wardIds, 8l,committeeTypeId,enrollIdsList,startDate,endDate);
					List<Long> eletedMemIds = new ArrayList<Long>();
					if(electedUsers!=null && electedUsers.size()>0){
						for(Object[] obj:electedUsers){
							eletedMemIds.add(Long.valueOf(obj[0].toString()));
						}
					}
					
					List<Object[]> electrolsRslt = new ArrayList<Object[]>();
					List<Object[]> electrolsRsltAffl = new ArrayList<Object[]>();
					
						electrolsRslt = tdpCommitteeElectrolsDAO.getElectrolsForPanchayatsWardsWithOutDuplicates(wardIds, "ward", eletedMemIds,enrollIdsList,startDate,endDate);
						electrolsRsltAffl = tdpCommitteeElectrolsDAO.getElectrolsForPanchayatsWards(wardIds, "ward",enrollIdsList,startDate,endDate);
						
						if(electrolsRsltAffl!=null && electrolsRsltAffl.size()>0){
							electrolsRslt.addAll(electrolsRsltAffl);
						}
					
					
					pushElectrolsCount(electrolsRslt, locsResult,"");
					pushElectrolsCount(electedMems, locsResult,"members");
					
					pushPanchayatsAndWards(wardMap, fnlVO.getLocalBodiesList());
				}
				
				
			}catch (Exception e) {
				LOG.error(" Entered Into getConstituencySummary() " + e);
			}
			
			if(userId.longValue() == 1)
			{
				
				getAllIvrDetailsForCampaind(fnlVO.getMandalsList(),3l,"mandal");
				getAllIvrDetailsForCampaind(fnlVO.getLocalBodiesList(),4l,"ward");
			}
			
		
		return fnlVO;
	}
	
	public void pushPanchayatsAndWards(Map<Long,List<CommitteeSummaryVO>> locationsMap, List<CommitteeSummaryVO> locations){
		if(locations!=null && locations.size()>0){
			for(CommitteeSummaryVO temp:locations){
				temp.setLocationsList(locationsMap.get(temp.getLocationId()));
			}
		}
	}
	
	public List<CommitteeSummaryVO> pushBasicCommitteesToLocations(List<Object[]> basicCommitteesRslt, List<CommitteeSummaryVO> locations){
		
		
		if(locations!=null && locations.size()>0){
			
			
			
			for(CommitteeSummaryVO cv:locations){
				List<CommitteeSummaryVO> basicCommittees = new ArrayList<CommitteeSummaryVO>();
				if(basicCommitteesRslt!=null && basicCommitteesRslt.size()>0){
					for(Object[] obj:basicCommitteesRslt){
						CommitteeSummaryVO vo = new CommitteeSummaryVO();
						vo.setBasicCommitteeTypeId(Long.valueOf(obj[0].toString()));
						vo.setBasicCommitteeName(obj[1].toString());
						basicCommittees.add(vo);
					}
				}
				cv.setResultList(basicCommittees);
			}
		}
		return locations;
	}
	
	public void pushConstSummaryToLocations(List<Object[]> resltLst, List<CommitteeSummaryVO> locationsList){
		
		if(resltLst!=null && resltLst.size()>0 && locationsList!=null && locationsList.size()>0){
			for(Object[] obj:resltLst){
				CommitteeSummaryVO cs = getMatchedLocationForConstSummary(Long.valueOf(obj[2].toString()), locationsList);
				if(cs!=null){
					CommitteeSummaryVO basic = getMatchedBasicCommittee(Long.valueOf(obj[3].toString()), cs.getResultList());
					
					if(basic!=null){
						Long memsCount = basic.getMembersCount();
						if(memsCount == null){
							memsCount = 0l;
						}
						
						if(Long.valueOf(obj[0].toString())!=null){
							basic.setMembersCount(memsCount+Long.valueOf(obj[0].toString()));
						}
					}
				}
				
			}
		}
	}
	
	public void pushElectrolsCount(List<Object[]> resltLst, List<CommitteeSummaryVO> locationsList,String memType){
			
			if(resltLst!=null && resltLst.size()>0 && locationsList!=null && locationsList.size()>0){
				for(Object[] obj:resltLst){
					CommitteeSummaryVO cs = getMatchedLocationForConstSummary(Long.valueOf(obj[1].toString()), locationsList);
					if(cs!=null){
							CommitteeSummaryVO basic = getMatchedBasicCommittee(Long.valueOf(obj[2].toString()), cs.getResultList());
							if(memType.equalsIgnoreCase("members")){
								if(basic!=null){
									if(basic.getBasicCommitteeTypeId().equals(1l)){
										Long memsCount = basic.getElectrolsCount();
										if(memsCount == null){
											memsCount = 0l;
										}
										
										if(Long.valueOf(obj[0].toString())!=null){
											basic.setElectrolsCount(memsCount+Long.valueOf(obj[0].toString()));
										}
									}
									
								}
							}else{
								if(basic!=null){
									Long memsCount = basic.getElectrolsCount();
									if(memsCount == null){
										memsCount = 0l;
									}
										
									if(Long.valueOf(obj[0].toString())!=null){
										basic.setElectrolsCount(memsCount+Long.valueOf(obj[0].toString()));
									}
								}
							}
							
						}
					
				}
			}
		}
	
	public CommitteeSummaryVO getMatchedBasicCommittee(Long id, List<CommitteeSummaryVO> list){
		if(id!=null && list!=null && list.size()>0){
			for(CommitteeSummaryVO temp:list){
				if(temp.getBasicCommitteeTypeId().equals(id)){
					return temp;
				}
			}
		}
		return null;
	}
	public LocationWiseBoothDetailsVO getAffiliatedCommitteMembersInfo(List<Long> affiliIds){
		LocationWiseBoothDetailsVO returnVo = new LocationWiseBoothDetailsVO();
		List<LocationWiseBoothDetailsVO> committeeMembersInfoList = new ArrayList<LocationWiseBoothDetailsVO>();
		List<SelectOptionVO> committeeMembersList = new ArrayList<SelectOptionVO>();
		
		returnVo.setResult(committeeMembersInfoList);
		returnVo.setHamletsOfTownship(committeeMembersList);
		try{
			Map<Long,LocationWiseBoothDetailsVO> committeeMembersMap = new HashMap<Long,LocationWiseBoothDetailsVO>();
			LocationWiseBoothDetailsVO vo = null;
			SelectOptionVO memberVo = null;
			//0committeeRoleid,1role name,2max nos
			List<Object[]> totalCommitteRolesList = tdpCommitteeRoleDAO.getAllAffiliCommitteeRoles(affiliIds);
			for(Object[] totalCommitteRole:totalCommitteRolesList){
				vo = new LocationWiseBoothDetailsVO();
				vo.setLocationName(totalCommitteRole[1].toString());
				vo.setLocationId((Long)totalCommitteRole[0]);
				vo.setPopulation((Long)totalCommitteRole[2]);//total positions
				vo.setTotal((Long)totalCommitteRole[2]);//total positions left
				vo.setVotesPolled(0l);//total positions filled
				committeeMembersMap.put((Long)totalCommitteRole[0], vo);
				committeeMembersInfoList.add(vo);
			}
			if(committeeMembersMap.size() > 0){
				//0 count,1 id
				List<Object[]>  electedPersonsList = tdpCommitteeMemberDAO.getRoleWiseAllocatedMembersCount(committeeMembersMap.keySet());
				for(Object[] electedPersons:electedPersonsList){
					LocationWiseBoothDetailsVO roleInfo = committeeMembersMap.get((Long)electedPersons[1]);
					roleInfo.setVotesPolled((Long)electedPersons[0]);
					roleInfo.setTotal(roleInfo.getPopulation() - (Long)electedPersons[0]);
				}
				
				//0 role,1 image,2name,3membership
				List<Object[]>  electedMembersInfoList = tdpCommitteeMemberDAO.getAffiliCommMembersInfo(committeeMembersMap.keySet());
				
				for(Object[] electedMembersInfo:electedMembersInfoList){
					memberVo = new SelectOptionVO();
					memberVo.setValue(electedMembersInfo[0].toString());//role
					if(electedMembersInfo[1] != null){
					   memberVo.setUrl(electedMembersInfo[1].toString());//image
					}
					memberVo.setName(electedMembersInfo[2].toString());//name
					if(electedMembersInfo[3].toString().trim().length() > 8){
						memberVo.setType(electedMembersInfo[3].toString().trim().substring(electedMembersInfo[3].toString().trim().length()-8));
					}else{
						memberVo.setType(electedMembersInfo[3].toString());
					}
					//memberVo.setType(electedMembersInfo[3].toString());//membership
					memberVo.setPartno(electedMembersInfo[4].toString());//Affilicated name
					committeeMembersList.add(memberVo);
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised in getCommitteeMembersInfo", e);
		}
		return returnVo;
		
	}
	
	public List<AccessedPageLoginTimeVO> getElctoralInfoForALocation(Long locationValue){
		
		List<AccessedPageLoginTimeVO> returnResult=new ArrayList<AccessedPageLoginTimeVO>();
		try{
			Long locationLvl  = null;
			if(locationValue.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				 locationLvl = 6l;
			 }else{
				 locationLvl = 8l;
			 }
			
			Set<Long> cadreIds = new HashSet<Long>();
			
			List<Object[]> mainCommitList = tdpCommitteeMemberDAO.getAllMembersInMainCommWithPresidentAndGeneralSecretaryRole(locationLvl, Long.valueOf(locationValue.toString().substring(1)) , null);
			
			for (Object[] object : mainCommitList) {
				if(!cadreIds.contains((Long)object[4])){
					
					cadreIds.add((Long)object[4]);
					
					AccessedPageLoginTimeVO result=new AccessedPageLoginTimeVO();
					result.setFromTime(object[0].toString());//comm type
					result.setToTime(object[1].toString());//name
					if(object[2]!=null){
						result.setPageUrl(object[2].toString());//img url
					}
					if(object[3].toString().trim().length() > 8){
						result.setTimeSpent(object[3].toString().trim().substring(object[3].toString().trim().length()-8));
					}else{
					  result.setTimeSpent(object[3].toString());//id
					}
					result.setCommitteeType("main");
					result.setCommitteeMemberStatus(object[5] != null ? object[5].toString() :null);
					returnResult.add(result);
				}
			}
			
			List<Object[]> elctoralsList = tdpCommitteeElectrolsDAO.getElctoralInfoForALocation(locationValue,IConstants.CURRENT_ENROLLMENT_ID);
			for (Object[] object : elctoralsList) {
				if(!cadreIds.contains((Long)object[4])){
					
					cadreIds.add((Long)object[4]);
					
					AccessedPageLoginTimeVO result=new AccessedPageLoginTimeVO();
					result.setFromTime(object[0].toString());//comm type
					result.setToTime(object[1].toString());//name
					if(object[2]!=null){
						result.setPageUrl(object[2].toString());//img url
					}
					if(object[3].toString().trim().length() > 8){
						result.setTimeSpent(object[3].toString().trim().substring(object[3].toString().trim().length()-8));
					}else{
					  result.setTimeSpent(object[3].toString());//id
					}
					result.setCommitteeType("electrols");
					result.setCommitteeMemberStatus("F");//All electrols are finalized by default.
					returnResult.add(result);
				}
			}
			
			
		}
		catch (Exception e) {
			LOG.error("Exception raised in getElctoralInfoForALocation() in cadreCommitteeService calss",e);
		}
		return returnResult;
	}
		
	public List<LocationWiseBoothDetailsVO> getMandalsByConstituency(Long constituencyId ){
		
		List<LocationWiseBoothDetailsVO> mandalsList = getMandalMunicCorpDetails1(constituencyId);
		return mandalsList;
		
	}
	public ResultStatus updateCandidateDesignation(final Long committeeId,final List<LocationWiseBoothDetailsVO> changeDesignationsList,final Long userId){
		ResultStatus status = null;
		try{
			TdpCommittee tdpCommittee = tdpCommitteeDAO.get(committeeId);
			if(tdpCommittee.getIsCommitteeConfirmed().equalsIgnoreCase("Y")){
				status = new ResultStatus();
				status.setResultCode(2);
				status.setMessage("This Committee Is Already Confirmed.You Cannot Update Any Information");
				return status;
			}
			synchronized("UPDATECADREDESIGNATIONNEW"){
				status = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
					 public Object doInTransaction(TransactionStatus status) {
						 ResultStatus rs= gettingStatus(committeeId,changeDesignationsList);
						  flagGlobal=rs.getIsResultPartial();
						  if(!flagGlobal){
							  for(LocationWiseBoothDetailsVO candidate:changeDesignationsList){
								  Long newRoleId = candidate.getVotesPolled();
								  Long tdpCommitteeMemberId = candidate.getLocationId();
								  TdpCommitteeMember tdpCommitteeMember = tdpCommitteeMemberDAO.get(tdpCommitteeMemberId);
								  tdpCommitteeMember.setTdpCommitteeRoleId(newRoleId);
								  tdpCommitteeMember.setUpdatedUserId(userId);
								  tdpCommitteeMember.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								  tdpCommitteeMemberDAO.save(tdpCommitteeMember);
							  }
							  rs.setResultCode(1); 
						  }else{
						      rs.setResultCode(2);
						  }
						  return rs;
					}	
			    });
		  }
		}catch(Exception e){
			LOG.error("Exception raised in updateCandidateDesignation", e);
			status = new ResultStatus();
			status.setResultCode(0);
		}
	     
	     return status;
	}
	public List<LocationWiseBoothDetailsVO> getPanchayatWardByMandalId(String mandalId,Long constituencyId){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		 LocationWiseBoothDetailsVO vo1 = new LocationWiseBoothDetailsVO();
		   vo1.setLocationId(0l);
		   vo1.setLocationName("Select Panchayat/Ward/Division/City");
		   locationsList.add(vo1);
		LocationWiseBoothDetailsVO vo = null;
		List<Long> mandalIds = new ArrayList<Long>();
		List<Long> localBodyIds = new ArrayList<Long>();
		
		if((mandalId.substring(0,1)).equalsIgnoreCase("2")){
			mandalIds.add(Long.valueOf(mandalId.substring(1)));
		}
		if((mandalId.substring(0,1)) .equalsIgnoreCase("1")){
			localBodyIds.add(Long.valueOf(mandalId.substring(1)));
		}
		
		if(mandalIds.size()>0){
        	//0panchayatId,1panchayatName,2tehsilName
        	List<Object[]> panchayatsList = panchayatDAO.getAllPanchayatsInMandals(mandalIds);
        	for(Object[] panchayat:panchayatsList){
        		vo = new LocationWiseBoothDetailsVO();
	        	vo.setLocationId(Long.valueOf("1"+(Long)panchayat[0]));
	        	vo.setLocationName(WordUtils.capitalizeFully(panchayat[1].toString()+"("+panchayat[2].toString()+")"));
	        	vo.setName("Village");
	        	locationsList.add(vo);
        	}
        }
		   if(localBodyIds.size() > 0){
			   List<Object[]> localBodyList = new ArrayList<Object[]>();
			   //0wardId,1pwardName,2localBdyName
			   if(constituencyId == null || constituencyId.longValue() == 0L)
				   localBodyList = constituencyDAO.getWardsInLocalElectionBody(localBodyIds);
			   else
				   localBodyList = assemblyLocalElectionBodyWardDAO.getWardsInLocalElectionBody(localBodyIds, constituencyId);
			   
	        	for(Object[] localBody:localBodyList){
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("2"+(Long)localBody[0]));
		        	vo.setLocationName(WordUtils.capitalizeFully(localBody[1].toString()+"("+localBody[2].toString()+")"));
		        	vo.setName("Ward");
		        	locationsList.add(vo);
	        	}
	        }
		  
		return locationsList;
	}
	public List<CadreCommitteeMemberVO> getElectrolsOfPanchayatAndWards(Long locationId,Long locationType,Long basicCommitteeTypeId)
	{
		List<CadreCommitteeMemberVO> cadreCommitteeMemberVOList=null;
		try
		{
			List<Object[]> rolesList= new ArrayList<Object[]>();
			Map<String,CasteDetailsVO> casteGroupMap = null;
			Map<String,CasteDetailsVO> ageGroupMap = null;
			if(basicCommitteeTypeId.equals(1l)){
				 rolesList=tdpCommitteeMemberDAO.getPresidentsAndVPInfoForCommittee(locationType,locationId,basicCommitteeTypeId);
			}
			
		    List<Object[]> electrolsList=tdpCommitteeElectrolsDAO.getElectrolsOfPanchayatAndWards(locationId,locationType,basicCommitteeTypeId);
		    List<Long> mmbrIds = new ArrayList<Long>();
		    Long maleCount = 0L;
		    Long femaleCount = 0L;
		    if(rolesList!=null && rolesList.size()>0){
		    	casteGroupMap = new HashMap<String, CasteDetailsVO>();
		    	ageGroupMap = new LinkedHashMap<String, CasteDetailsVO>();
		    	ageGroupMap.put("18-25",null);
		    	ageGroupMap.put("26-35",null);
		    	ageGroupMap.put("36-45",null);
		    	ageGroupMap.put("46-60",null);
		    	ageGroupMap.put("Above60",null);
				
		    	cadreCommitteeMemberVOList=new ArrayList<CadreCommitteeMemberVO>();
			      for (Object[] objects : rolesList){
			    	  CadreCommitteeMemberVO cadreCommitteeMemberVO=new CadreCommitteeMemberVO();
			    	  cadreCommitteeMemberVO.setRole(objects[1] != null ? objects[1].toString():"");
			    	  cadreCommitteeMemberVO.setId(objects[2] != null ? Long.valueOf(objects[2].toString().trim()) :0L);
			    	  mmbrIds.add(cadreCommitteeMemberVO.getId());
			    	  	
			    	  cadreCommitteeMemberVO.setName(objects[3] != null ?objects[3].toString():"");
			    	  cadreCommitteeMemberVO.setImagePath(objects[4] != null ?objects[4].toString():"");
			    	  if(objects[5] != null){
			    	    if(objects[5].toString().trim().length() > 8){
			    		    cadreCommitteeMemberVO.setMembershipNo(objects[5].toString().trim().substring(objects[5].toString().trim().length()-8));
						}else{
							cadreCommitteeMemberVO.setMembershipNo(objects[5].toString());
						}
			    	  }else{
			    		  cadreCommitteeMemberVO.setMembershipNo(""); 
			    	  }
			    	  cadreCommitteeMemberVO.setCasteName(objects[8] != null ? objects[8].toString().trim():"");
			    		cadreCommitteeMemberVO.setGender(objects[9] != null ? objects[9].toString().trim():"");
			    		cadreCommitteeMemberVO.setAge(objects[10] != null ? objects[10].toString().trim():"");
			    		cadreCommitteeMemberVO.setCasteGroupName(objects[12] != null ? objects[12].toString().trim():"");
			    		CasteDetailsVO casteCatgVO = casteGroupMap.get(objects[12].toString().trim());
			    		if(casteCatgVO == null)
			    		{
			    			casteCatgVO = new CasteDetailsVO();
			    			casteCatgVO.setCasteId(1l);
			    			casteGroupMap.put(objects[12].toString().trim(),casteCatgVO);
			    		}
			    		else
			    		{
			    			casteCatgVO.setCasteId(casteCatgVO.getCasteId() + 1l);
			    			casteGroupMap.put(objects[12].toString().trim(),casteCatgVO);
			    		}
			    		if(cadreCommitteeMemberVO.getGender().equalsIgnoreCase("M"))
		    			{
		    				casteCatgVO.setStateId(casteCatgVO.getStateId() + 1l);
		    			}
		    			else
		    			{
		    				casteCatgVO.setCastStateId(casteCatgVO.getCastStateId() + 1l);  
		    			}
		    			
			    		if(cadreCommitteeMemberVO.getAge() != null)
			    		{
			    			if(Long.valueOf(cadreCommitteeMemberVO.getAge()) >= 18 && Long.valueOf(cadreCommitteeMemberVO.getAge()) < 26)
			    			{
			    				CasteDetailsVO ageGroupVO = ageGroupMap.get("18-25");
			    				if(ageGroupVO == null)
			    				{
			    					ageGroupVO = new CasteDetailsVO();
			    					ageGroupVO.setCasteId(1l);
			    					ageGroupMap.put("18-25", ageGroupVO);
			    				}
			    				else
			    				{
			    					ageGroupVO.setCasteId(ageGroupVO.getCasteId() + 1l);
			    					ageGroupMap.put("18-25", ageGroupVO);
			    				}
			    				if(cadreCommitteeMemberVO.getGender().equalsIgnoreCase("M"))
				    			{
			    					ageGroupVO.setStateId(ageGroupVO.getStateId() + 1l);
				    			}
				    			else
				    			{
				    				ageGroupVO.setCastStateId(ageGroupVO.getCastStateId() + 1l);  
				    			}
			    			}
			    			else if(Long.valueOf(cadreCommitteeMemberVO.getAge()) >= 26 && Long.valueOf(cadreCommitteeMemberVO.getAge()) < 35)
			    			{
			    				CasteDetailsVO ageGroupVO = ageGroupMap.get("26-35");
			    				if(ageGroupVO == null)
			    				{
			    					ageGroupVO = new CasteDetailsVO();
			    					ageGroupVO.setCasteId(1l);
			    					ageGroupMap.put("26-35", ageGroupVO);
			    				}
			    				else
			    				{
			    					ageGroupVO.setCasteId(ageGroupVO.getCasteId() + 1l);
			    					ageGroupMap.put("26-35", ageGroupVO);
			    				}
			    				if(cadreCommitteeMemberVO.getGender().equalsIgnoreCase("M"))
				    			{
			    					ageGroupVO.setStateId(ageGroupVO.getStateId() + 1l);
				    			}
				    			else
				    			{
				    				ageGroupVO.setCastStateId(ageGroupVO.getCastStateId() + 1l);  
				    			}
			    			}
			    			else if(Long.valueOf(cadreCommitteeMemberVO.getAge()) >= 36 && Long.valueOf(cadreCommitteeMemberVO.getAge()) < 45)
			    			{
			    				CasteDetailsVO ageGroupVO = ageGroupMap.get("36-45");
			    				if(ageGroupVO == null)
			    				{
			    					ageGroupVO = new CasteDetailsVO();
			    					ageGroupVO.setCasteId(1l);
			    					ageGroupMap.put("36-45", ageGroupVO);
			    				}
			    				else
			    				{
			    					ageGroupVO.setCasteId(ageGroupVO.getCasteId() + 1l);
			    					ageGroupMap.put("36-45", ageGroupVO);
			    				}
			    				if(cadreCommitteeMemberVO.getGender().equalsIgnoreCase("M"))
				    			{
			    					ageGroupVO.setStateId(ageGroupVO.getStateId() + 1l);
				    			}
				    			else
				    			{
				    				ageGroupVO.setCastStateId(ageGroupVO.getCastStateId() + 1l);  
				    			}
			    			}
			    			else if(Long.valueOf(cadreCommitteeMemberVO.getAge()) >= 46 && Long.valueOf(cadreCommitteeMemberVO.getAge()) < 60)
			    			{
			    				CasteDetailsVO ageGroupVO = ageGroupMap.get("46-60");
			    				if(ageGroupVO == null)
			    				{
			    					ageGroupVO = new CasteDetailsVO();
			    					ageGroupVO.setCasteId(1l);
			    					ageGroupMap.put("46-60", ageGroupVO);
			    				}
			    				else
			    				{
			    					ageGroupVO.setCasteId(ageGroupVO.getCasteId() + 1l);
			    					ageGroupMap.put("46-60", ageGroupVO);
			    				}
			    				if(cadreCommitteeMemberVO.getGender().equalsIgnoreCase("M"))
				    			{
			    					ageGroupVO.setStateId(ageGroupVO.getStateId() + 1l);
				    			}
				    			else
				    			{
				    				ageGroupVO.setCastStateId(ageGroupVO.getCastStateId() + 1l);  
				    			}
			    			}
			    			else
			    			{
			    				CasteDetailsVO ageGroupVO = ageGroupMap.get("Above60");
			    				if(ageGroupVO == null)
			    				{
			    					ageGroupVO = new CasteDetailsVO();
			    					ageGroupVO.setCasteId(1l);
			    					ageGroupMap.put("Above60", ageGroupVO);
			    				}
			    				else
			    				{
			    					ageGroupVO.setCasteId(ageGroupVO.getCasteId() + 1l);
			    					ageGroupMap.put("Above60",ageGroupVO);
			    				}
			    				if(cadreCommitteeMemberVO.getGender().equalsIgnoreCase("M"))
				    			{
			    					ageGroupVO.setStateId(ageGroupVO.getStateId() + 1l);
				    			}
				    			else
				    			{
				    				ageGroupVO.setCastStateId(ageGroupVO.getCastStateId() + 1l);  
				    			}
			    			}
			    			

			    		}
			    		if(cadreCommitteeMemberVO.getGender().equalsIgnoreCase("M") || cadreCommitteeMemberVO.getGender().equalsIgnoreCase("Male"))
			    		{
			    			maleCount = maleCount+1;
			    		}
			    		else if(cadreCommitteeMemberVO.getGender().equalsIgnoreCase("F") || cadreCommitteeMemberVO.getGender().equalsIgnoreCase("Female"))
			    		{
			    			femaleCount = femaleCount+1;
			    		}
			    		
			    		if(cadreCommitteeMemberVO.getAge() == null)
						{					
							String dateOfBirth = objects[11] != null ? objects[11].toString().trim().substring(0, 10).trim():"";
							
							if(dateOfBirth.trim().length()>0)
							{
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								
								Date today = new Date();
								Date DOB = dateFormat.parse(dateOfBirth);

								Calendar startCalendar = new GregorianCalendar();
								startCalendar.setTime(DOB);
								Calendar endCalendar = new GregorianCalendar();
								endCalendar.setTime(today);

								int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
								//int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
								
								cadreCommitteeMemberVO.setAge(diffYear != 0 ? Long.valueOf(Integer.toString(diffYear)).toString():"");
							}
						}
			    		
			    	  cadreCommitteeMemberVOList.add(cadreCommitteeMemberVO);
				   }
			      if(casteGroupMap != null && casteGroupMap.size() > 0)
			    	{
			    		List<CasteDetailsVO> casteGroupList = new ArrayList<CasteDetailsVO>();
			    		for (String casteGroup : casteGroupMap.keySet())
			    		{
			    			CasteDetailsVO ageGroupVO = casteGroupMap.get(casteGroup);
			    			ageGroupVO.setCastName(casteGroup);
			    			casteGroupList.add(ageGroupVO);
						}
			    		if(cadreCommitteeMemberVOList != null && cadreCommitteeMemberVOList.size() > 0)
			    		{
			    			cadreCommitteeMemberVOList.get(0).setCasteGroupVO(casteGroupList);
			    		}
			    	}
			    	
			    	if(ageGroupMap != null && ageGroupMap.size() > 0)
			    	{
			    		List<CasteDetailsVO> ageGroupList = new LinkedList<CasteDetailsVO>();
			    		for (String casteGroup : ageGroupMap.keySet())
			    		{
			    			CasteDetailsVO ageGroupVO = ageGroupMap.get(casteGroup);
			    			if(ageGroupVO != null)
			    			{
			    				ageGroupVO.setCastName(casteGroup);
				    			ageGroupList.add(ageGroupVO);
			    			}
						}
			    		if(cadreCommitteeMemberVOList != null && cadreCommitteeMemberVOList.size() > 0)
			    		{
			    			cadreCommitteeMemberVOList.get(0).setAgeDetailsIfoVO(ageGroupList);
			    		}
			    	}
			    }
		    
		    

		    if(electrolsList!=null && electrolsList.size()>0){
		    	
		    	String locationName = getLocationName(locationType,locationId);
		    	if(cadreCommitteeMemberVOList==null)
		    		cadreCommitteeMemberVOList=new ArrayList<CadreCommitteeMemberVO>();
		    	
		    	for (Object[] objects : electrolsList){
		    		if(!mmbrIds.contains(Long.valueOf(objects[0].toString()))){
		    			CadreCommitteeMemberVO cadreCommitteeMemberVO=new CadreCommitteeMemberVO();
			    		//cadreCommitteeMemberVO.setLevel((Long)objects[0]);//tdpCommitteeRoleId
			    		//cadreCommitteeMemberVO.setRole(objects[1].toString());//role
			    		cadreCommitteeMemberVO.setId((Long)objects[0]);//cadreId
			    		cadreCommitteeMemberVO.setName(objects[1].toString());//cadreName
			    		cadreCommitteeMemberVO.setImagePath(objects[2].toString());//image
			    		if(objects[3].toString().trim().length() > 8){
			    			cadreCommitteeMemberVO.setMembershipNo(objects[3].toString().trim().substring(objects[3].toString().trim().length()-8));
			    		}else{
			    		  cadreCommitteeMemberVO.setMembershipNo(objects[3].toString());//membershipno
			    		}
			    		//cadreCommitteeMemberVO.setMembershipNo(objects[3].toString());//membershipno
			    		cadreCommitteeMemberVOList.add(cadreCommitteeMemberVO);
		    		}
		    		
				}
		    	
		    	if(cadreCommitteeMemberVOList != null && cadreCommitteeMemberVOList.size() > 0)
				{
		    	 cadreCommitteeMemberVOList.get(0).setLocationName(locationName);
		    	 cadreCommitteeMemberVOList.get(0).setCommitte(tdpBasicCommitteeDAO.get(basicCommitteeTypeId).getName());
		    	 cadreCommitteeMemberVOList.get(0).setMaleCount(maleCount.toString());
		    	 cadreCommitteeMemberVOList.get(0).setFemaleCount(femaleCount.toString());
		    	 Long total = maleCount+femaleCount;
		    	 cadreCommitteeMemberVOList.get(0).setTotalCount(total.toString());
				}
		      }
		    if(cadreCommitteeMemberVOList != null && cadreCommitteeMemberVOList.size() > 0)
			{	    	
	    	 cadreCommitteeMemberVOList.get(0).setMaleCount(maleCount.toString());
	    	 cadreCommitteeMemberVOList.get(0).setFemaleCount(femaleCount.toString());
	    	 Long total = maleCount+femaleCount;
	    	 cadreCommitteeMemberVOList.get(0).setTotal(total);
			}
		}
		catch(Exception e){
			
			LOG.error("Exception raised in getElectrolsOfPanchayatAndWards", e);
		}
		return cadreCommitteeMemberVOList;
	}
	
	public List<CadreCommitteeMemberVO> getCommitteeMemberPerformanceDetails(Long locationLevelId, Long locationLevelValue){
		LOG.debug("Entered Into getCommitteeMemberPerformanceDetails");
		List<CadreCommitteeMemberVO> finalList = new ArrayList<CadreCommitteeMemberVO>();
		try{
			List<Object[]> rsltListDtls = tdpCommitteeMemberDAO.cadreMemberDetailsForPerformance(locationLevelId, locationLevelValue);
			Map<Long,String> constiNames = new HashMap<Long,String>();
			if(rsltListDtls!=null && rsltListDtls.size()>0){
				for(Object[] temp:rsltListDtls){
					CadreCommitteeMemberVO cv = new CadreCommitteeMemberVO();
					if(temp[0] != null){
						if(temp[0].toString().trim().length() > 8){
							cv.setMembershipNo(temp[0].toString().trim().substring(temp[0].toString().trim().length()-8));
						}else{
							cv.setMembershipNo(temp[0].toString());
						}
					}else{
						cv.setMembershipNo("");
					}
					//cv.setMembershipNo(temp[0].toString());
					cv.setName(temp[1].toString());
					if(temp[2] != null)
					cv.setVtrId(Long.valueOf(temp[2].toString()));
					cv.setMobileNo(temp[3].toString());
					if(temp[4] != null)
					cv.setAge(temp[4].toString());
					if(temp[5] != null)
					cv.setGender(temp[5].toString());
					if(temp[6] != null)
					cv.setCasteName(temp[6].toString());
					if(temp[7] != null)
					cv.setCasteGroupName(temp[7].toString());
					cv.setVoterCardNo(temp[8].toString());
					cv.setCommitte(temp[9].toString());
					cv.setRole(temp[10].toString());
					if(temp[12] != null){
						Long id = Long.valueOf(temp[12].toString());
						if(constiNames.get(id) == null){
							constiNames.put(id, constituencyDAO.getConstituencyNameById(id));
						}
						
						cv.setConstituencyId(id);
						cv.setConstituencyName(constiNames.get(id));
					}
					if(temp[14] != null)
					cv.setBoothId(Long.valueOf(temp[14].toString()));
					if(temp[13] != null)
					cv.setPartNo(temp[13].toString());
					
					SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
					
					if(temp[15]!=null){
						cv.setFromDate(sdfr.format(temp[15]));
					}
					
					if(temp[16]!=null){
						cv.setToDate(sdfr.format(temp[16]));
					}
					
					
					CadreCommitteeMemberVO cadreVO  = getMatchedVoter(finalList, cv.getVtrId());
					if(cadreVO!=null){
						if(cv.getFromDate()!=null && cv.getFromDate()!=""){
							cadreVO.setFromDate(cv.getFromDate());
						}
						if(cv.getToDate()!=null && cv.getToDate()!=""){
							cadreVO.setToDate(cv.getToDate());
						}
					}else{
						finalList.add(cv);
					}
				}
			}
			
			getPerformanceOfCadre(finalList);
			/*List<Object[]> rsltList = tdpCommitteeMemberDAO.cadreMemberBoothDetailsForPerformance(locationLevelId, locationLevelValue);
			if(rsltListDtls!=null && rsltListDtls.size()>0){
				
			}*/
		}catch(Exception e){
			LOG.error(" Exception Raised In getCommitteeMemberPerformanceDetails",e);
		}
		
		return finalList;
	}
	
	public void getPerformanceOfCadre(List<CadreCommitteeMemberVO> finalList){
		
		if(finalList!=null && finalList.size()>0){
				Long ttlMembs = 0l;
				Long ttlMainMembs = 0l;
				Long ttlAfflMembs = 0l;
				Long ttlLowPerfMainMembs = 0l;
				Long ttlLowPerfAfflMembs = 0l;
				
			for(CadreCommitteeMemberVO cv:finalList){
				
				List<Long> naIds = new ArrayList<Long>();
				List<Long> naIds1 = new ArrayList<Long>();
				
				String constiPerc = null;
				Map<Long,String> constiPercMap =  new HashMap<Long,String>();
				
				naIds.add(103l);
				naIds.add(310l);
				
				Set<Long> ownMandalsList = new HashSet<Long>();
				Set<Long> ownMunicList = new HashSet<Long>();
				Set<Long> ownPanchayatList = new HashSet<Long>();
				Set<Long> ownWardList = new HashSet<Long>();
				Set<Long> assMandalsList = new HashSet<Long>();
				Set<Long> assMunicList = new HashSet<Long>();
				Set<Long> assPanchayatList = new HashSet<Long>();
				Set<Long> assWardList = new HashSet<Long>();
				
				String ownBoothId = cv.getPartNo();
				Long constituencyId = cv.getConstituencyId();
				
				Set<String> ownBoothsList = new HashSet<String>();
				Set<String> assigBoothsList = new HashSet<String>();
				Set<String> allBooths = new HashSet<String>();
				
				if(ownBoothId != null){
					ownBoothsList.add(ownBoothId);
				}
				
				allBooths.addAll(ownBoothsList);
				allBooths.addAll(assigBoothsList);
				constiPerc = constiPercMap.get(constituencyId);
				if(constiPerc == null){
					List<Long> partyIds = new ArrayList<Long>();
			    	partyIds.add(163l);
			    	partyIds.add(872l);
			    	//0 partyId,1 votesearnedPerc
			    	List<Object[]> candidateResult = candidateResultDAO.getResultPercByPartyIds(partyIds,constituencyId,258l);
			    	if(candidateResult.size() == 1){
			    		constiPerc = candidateResult.get(0)[1].toString();
			    	}else{
			    		for(Object[] result:candidateResult){
			    			if(((Long)result[0]).longValue() == 872l){
			    				constiPerc =result[1].toString();
			    			}
			    		}
			    	}
			    	
			    	constiPercMap.put(constituencyId, constiPerc);
				}
				
				String ownMandalPerc = "";
				String ownMunicPerc = "";
				String ownPancPerc = "";
				String ownWardPerc = "";
				String ownBoothPerc = "";
				
				
				String assMandalPerc = "";
				String assMunicPerc = "";
				String assPancPerc = "";
				String assWardPerc = "";
				String assBoothPerc = "";
				
				/*Map<Long,Double> allMandalsList = new HashMap<Long,Double>();
				Map<Long,Double> allMunicList = new HashMap<Long,Double>();
				Map<Long,Double> allPanchayatList = new HashMap<Long,Double>();
				Map<Long,Double> allWardList = new HashMap<Long,Double>();*/
				
				Map<String,VoterVO> boothsInfoMap = new HashMap<String,VoterVO>();
				
				List<Object[]> boothsInfo = new ArrayList<Object[]>();
				//0boothId, 1partNo, 2tehsilId, 3lclBodyId, 4panchayatId, 5wardId
				if(!(allBooths == null || allBooths.size() == 0))
				    boothsInfo = boothDAO.getBoothsInfo(constituencyId,allBooths);
				for(Object[] info:boothsInfo){
					VoterVO vo = new VoterVO();
					vo.setBoothId((Long)info[0]);
					vo.setPartNo(Long.valueOf(info[1].toString()));
					vo.setCandidateId((Long)info[2]);//tehsilId
					vo.setLocalAreaId((Long)info[3]);//lclBodyId
					vo.setPanchayatId((Long)info[4]);
					vo.setHamletId((Long)info[5]);//wardId
					boothsInfoMap.put(info[1].toString(), vo);
				}
				Long electionId = 258l;
				List<Long> reqPartyIds = new ArrayList<Long>();
				if(!naIds.contains(constituencyId)){
					if(constituencyId.longValue() == 243l || constituencyId.longValue() == 229l || constituencyId.longValue() == 297l || constituencyId.longValue() == 301l){
						reqPartyIds.add( 872l);
					}else{
						reqPartyIds.add( 872l);
						reqPartyIds.add( 163l);
					}
				}else{
					reqPartyIds.add( 872l);
					reqPartyIds.add( 163l);
					electionId = 260l;
				}
				
				List<Long> assBtIds = new ArrayList<Long>();
					for(String partNo:assigBoothsList){
						VoterVO vo = boothsInfoMap.get(partNo);
						if(vo != null){
							assBtIds.add(vo.getBoothId());
							if(vo.getLocalAreaId() == null && vo.getCandidateId() != null){
								assMandalsList.add(vo.getCandidateId());
							
							}
							
							if(vo.getLocalAreaId() != null && vo.getHamletId() == null){
								assMunicList.add(vo.getLocalAreaId());
								
							}
							
							if(vo.getPanchayatId() != null){
								assPanchayatList.add(vo.getPanchayatId());
								
							}
							
							if(vo.getHamletId() != null){
								assWardList.add(vo.getHamletId());
								
							}
						}
					}
					if(assMandalsList.size() > 0){
						assMandalPerc = calculateMandalPerc(assMandalsList,constituencyId,reqPartyIds, electionId);			
					}if(assMunicList.size() > 0){
						assMunicPerc = calculateMunicPerc(assMunicList,constituencyId,reqPartyIds, electionId);
					}if(assPanchayatList.size() > 0){
						assPancPerc = calculatePancPerc(assPanchayatList,constituencyId,reqPartyIds, electionId);
					}if(assWardList.size() > 0){
						assWardPerc = calculateWardPerc(assWardList,constituencyId,reqPartyIds, electionId);
					}
					
					if(assBtIds.size() > 0){
					   assBoothPerc = calculateBootPerc(assBtIds,constituencyId,reqPartyIds, electionId);
					}
		          if(constiPerc == null){
		        	  List<Long> boothIds = boothDAO.getBoothsDetailIds("", constituencyId, null);
		        	  constiPerc = calculateBootPerc(boothIds, constituencyId, reqPartyIds, electionId);
		        	  constiPercMap.put(constituencyId, constiPerc);
			    	}
					List<Long> ownBtIds = new ArrayList<Long>();
					for(String partNo:ownBoothsList){
						VoterVO vo = boothsInfoMap.get(partNo);
						if(vo != null){
							ownBtIds.add(vo.getBoothId());
							if(vo.getLocalAreaId() == null && vo.getCandidateId() != null){
								ownMandalsList.add(vo.getCandidateId());
							}
							
							if(vo.getLocalAreaId() != null && vo.getHamletId() == null){
								ownMunicList.add(vo.getLocalAreaId());
							}
							
							if(vo.getPanchayatId() != null){
								ownPanchayatList.add(vo.getPanchayatId());
							}
							
							if(vo.getHamletId() != null){
								ownWardList.add(vo.getHamletId());
							}
						}
					}
					
					cv.setOwnConstiPerc(constiPerc);
					
					  if(ownMandalsList.size() > 0){
						  ownMandalPerc = calculateMandalPerc(ownMandalsList,constituencyId,reqPartyIds,electionId);
						  cv.setOwnMandalPerc(ownMandalPerc);
						}if(ownMunicList.size() > 0){
							ownMunicPerc = calculateMunicPerc(ownMunicList,constituencyId,reqPartyIds,electionId);
							cv.setOwnMunciPerc(ownMunicPerc);
						}if(ownWardList.size() > 0){
							ownWardPerc = calculateWardPerc(ownWardList,constituencyId,reqPartyIds,electionId);
							cv.setOwnWardPerc(ownWardPerc);
						}if(ownPanchayatList.size() > 0){
							ownPancPerc = calculatePancPerc(ownPanchayatList,constituencyId,reqPartyIds,electionId);
							cv.setOwnPanchPerc(ownPancPerc);
						}
						
					if(ownBtIds.size() > 0){
						ownBoothPerc = calculateBootPerc(ownBtIds,constituencyId,reqPartyIds,electionId);
						cv.setOwnBoothPerc(ownBoothPerc);
					}
					
					if(cv.getOwnMandalPerc()!=null && cv.getOwnBoothPerc()!=null){
						double boothPer = Double.parseDouble(cv.getOwnBoothPerc());
						double mandalPer = Double.parseDouble(cv.getOwnMandalPerc());
						double prfrmancePerc = boothPer-mandalPer;
						cv.setMandalBoothCmpr(String.valueOf(prfrmancePerc));
						if(prfrmancePerc<0){
							cv.setLowPerformance(true);
						}else{
							cv.setLowPerformance(false);
						}
					}
					if(cv.getOwnMunciPerc()!=null && cv.getOwnBoothPerc()!=null){
						double boothPer = Double.parseDouble(cv.getOwnBoothPerc());
						double munciPer = Double.parseDouble(cv.getOwnMunciPerc());
						double prfrmancePerc = boothPer-munciPer;
						cv.setMandalBoothCmpr(String.valueOf(prfrmancePerc));
						if(prfrmancePerc<0){
							cv.setLowPerformance(true);
						}else{
							cv.setLowPerformance(false);
						}
					}
					
					ttlMembs =  ttlMembs + 1;
					if(cv.getCommitte().equalsIgnoreCase("Main")){
						ttlMainMembs = ttlMainMembs + 1;
						if(cv.isLowPerformance()){
							ttlLowPerfMainMembs = ttlLowPerfMainMembs + 1;
						}
					}else{
						ttlAfflMembs = ttlAfflMembs + 1;
						if(cv.isLowPerformance()){
							ttlLowPerfAfflMembs = ttlLowPerfAfflMembs + 1;
						}
					}
					
			}
			
			 CadreCommitteeMemberVO firstCV  = finalList.get(0);
			 
			 if(firstCV!=null){
				 firstCV.setTotalMembs(ttlMembs);
				 firstCV.setMainCmmtteeMembs(ttlMainMembs);
				 firstCV.setAfflCmmtteeMembs(ttlAfflMembs);
				 firstCV.setLowPerfMainCmmtteeMembs(ttlLowPerfMainMembs);
				 firstCV.setLowPerfAfflCmmtteeMembs(ttlLowPerfAfflMembs);
			 }
			
		}
		
		
	}
	
	public CadreCommitteeMemberVO getMatchedVoter(List<CadreCommitteeMemberVO> list, Long voterId){
		if(list!=null && list.size()>0 && voterId != null){
			for(CadreCommitteeMemberVO cv:list){
				if(cv.getVtrId().equals(voterId)){
					return cv;
				}
			}
		}
		return null;
	}
	
	public String calculateMandalPerc(Set<Long> mandalIds,Long constituencyId,List<Long> partyIds,Long electionId){
		List<Long> boothIds = boothDAO.getBoothsDetailIds("tehsil", constituencyId, mandalIds);
		 Long totalVoters =  boothDAO.getBoothResults(boothIds,electionId);
		 Long totalSecured =  boothDAO.getCandidateBoothResults(boothIds,partyIds,electionId);
		try{ 
		return (new BigDecimal((totalSecured * 100.0)/totalVoters.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
    public String calculateMunicPerc(Set<Long> municIds,Long constituencyId,List<Long> partyIds,Long electionId){
    	List<Long> boothIds = boothDAO.getBoothsDetailIds("munic", constituencyId, municIds);
    	Long totalVoters =  boothDAO.getBoothResults(boothIds,electionId);
		Long totalSecured =  boothDAO.getCandidateBoothResults(boothIds,partyIds,electionId);
		try{ 
		return (new BigDecimal((totalSecured * 100.0)/totalVoters.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		
	}
    
	public String calculatePancPerc(Set<Long> panchayatIds,Long constituencyId,List<Long> partyIds,Long electionId){
		List<Long> boothIds = boothDAO.getBoothsDetailIds("panchayat", constituencyId, panchayatIds);
		if(boothIds != null && boothIds.size() > 0){
		Long totalVoters =  boothDAO.getBoothResults(boothIds,electionId);
		Long totalSecured =  boothDAO.getCandidateBoothResults(boothIds,partyIds,electionId);
		try{ 
		return (new BigDecimal((totalSecured * 100.0)/totalVoters.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		}
		return "";
	}
 
   public String calculateBootPerc(List<Long> boothIds,Long constituencyId,List<Long> partyIds,Long electionId){
	   Long totalVoters =  boothDAO.getBoothResults(boothIds,electionId);
		Long totalSecured =  boothDAO.getCandidateBoothResults(boothIds,partyIds,electionId);
		try{ 
		return (new BigDecimal((totalSecured * 100.0)/totalVoters.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
 
   public String calculateWardPerc(Set<Long> wardIds,Long constituencyId,List<Long> partyIds,Long electionId){
	
	 try{ 
		 List<Long> boothIds = boothDAO.getBoothsDetailIds("ward", constituencyId, wardIds);
		 Long totalVoters =  boothDAO.getBoothResults(boothIds,electionId);
		 Long totalSecured =  boothDAO.getCandidateBoothResults(boothIds,partyIds,electionId);
	return (new BigDecimal((totalSecured * 100.0)/totalVoters.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
	 }catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	public List<CadreCommitteeMemberVO> getComitteeMembersInfoByCommiteTypeAndLocation(Long locationId,Long locationType,Long basicCommitteeTypeId,String status,List<Long> committeeEnrollmentIdsLst,String startDateStr,String endDateStr)
	{
		List<CadreCommitteeMemberVO> cadreCommitteeMemberVOList=null;
		try
		{
			Map<String,CasteDetailsVO> casteGroupMap = null;
			Map<String,CasteDetailsVO> ageGroupMap = null;
			Map<Long,Long> mandalIdsMap = new HashMap<Long,Long>();
			Map<Long,Long> locIdsMap = new HashMap<Long,Long>();
			Map<Long,Long> divisionIdsMap = new HashMap<Long,Long>();

			Map<Long,Long> allmandalsMap = new LinkedHashMap<Long, Long>();
			Map<Long,Long> allMunciMap = new LinkedHashMap<Long, Long>();
			Map<Long,Long> allCorpsMap = new LinkedHashMap<Long, Long>();
			Map<Long,Long> allDivisionsMap = new LinkedHashMap<Long, Long>();
			Long actualMandalsCount = 0L;
			Long actualMuncisCount = 0L;
			Long actualCorpsCount = 0L;
			Long actualdivisionsCount = 0L;
			Long others = 0l;
			Long committeeId = 0l;
			List<Long> constituencyList  = null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate = null;
			Date endDate = null;
			if(startDateStr != null && endDateStr != null ){
				startDate = sdf.parse(startDateStr);
				endDate = sdf.parse(endDateStr);
			}
			List<Object[]> newDistrictConstList = newDistrictConstituencyDAO.getConstituencyListForDistrict(locationId);
			if(newDistrictConstList != null && newDistrictConstList.size()>0)
			{
				constituencyList = new ArrayList<Long>();
				for (Object[] constituency : newDistrictConstList) {
					constituencyList.add(constituency[0] != null ? Long.valueOf(constituency[0].toString().trim()):0L);
				}
			}
			else
			{
				constituencyList = constituencyDAO.getConstituenciesInADistrict(locationId);
			}
		
			if(constituencyList != null && constituencyList.size()>0)
			{
				List<Object[]> mandalsList = tehsilDAO.getTehsilsByConstituencyIdsListAndPublicationDateId(constituencyList,IConstants.VOTER_DATA_PUBLICATION_ID);
				
				if(mandalsList != null && mandalsList.size()>0)
				{
					for (Object[] mandal : mandalsList) {
						allmandalsMap.put(mandal[0] != null ? Long.valueOf(mandal[0].toString().trim()):0L,0L);
						actualMandalsCount = actualMandalsCount+1;
					}
				}
				List<Object[]> localBodysList = tehsilDAO.getAllLocalElecBodyListByConstituencyIdsListAndPublicationDateId(constituencyList,IConstants.VOTER_DATA_PUBLICATION_ID);
				if(localBodysList != null && localBodysList.size()>0)
				{
					for (Object[] localBody : localBodysList) {
						Long id = localBody[0] != null ? Long.valueOf(localBody[0].toString().trim()):0L;
						String electionType = localBody[2] != null ? localBody[2].toString().trim():"";
						if(id != 20L)
						{							
							
							if(electionType.trim().equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE))
							{
								allMunciMap.put(id, 0L);
								actualMuncisCount = actualMuncisCount+1;
							}
							else 
							if(electionType.trim().equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE))
							{
								allCorpsMap.put(id, 0L);
								actualCorpsCount = actualCorpsCount+1;
							}
						}
						else
						{
							actualdivisionsCount = actualdivisionsCount+1;
							allDivisionsMap.put(id, 0L);
						}
					}
				}
			}
			
			
			
			//19tehsilId, 20localElectionBodyId 21constituencyId
		    List<Object[]> tdpCadresList=tdpCommitteeMemberDAO.getComitteeMembersInfoByCommiteTypeAndLocation(locationType,locationId,basicCommitteeTypeId,status,committeeEnrollmentIdsLst,startDate,endDate);
		    if(tdpCadresList!=null && tdpCadresList.size()>0){
		    	casteGroupMap = new HashMap<String, CasteDetailsVO>();
		    	ageGroupMap = new LinkedHashMap<String, CasteDetailsVO>();
		    	ageGroupMap.put("18-25",null);
		    	ageGroupMap.put("26-35",null);
		    	ageGroupMap.put("36-45",null);
		    	ageGroupMap.put("46-60",null);
		    	ageGroupMap.put("Above60",null);
		    	String locationName = getLocationName(locationType,locationId);
		    	cadreCommitteeMemberVOList=new ArrayList<CadreCommitteeMemberVO>();
		    	Map<String,Map<String,Long>> castesMap = new LinkedHashMap<String, Map<String,Long>>();
		    	
		    	Map<String,Map<String,Long>> constiMap = new LinkedHashMap<String, Map<String,Long>>();
		    	
		    	Long maleCount = 0L;
		    	Long femaleCount = 0L;
		    	
		    	Map<String,Long> rolesMap = new HashMap<String, Long>();

		    	Set<Long> voteIdsList = new HashSet<Long>();
		    	for (Object[] objects : tdpCadresList){
		    		if(objects[20] != null){ // local election body id
		    			if(objects[21] != null && ((Long)objects[20]).longValue() == 20l){ // division id
		    				if(divisionIdsMap.get((Long)objects[21]) != null){
		    					divisionIdsMap.put((Long)objects[21],divisionIdsMap.get((Long)objects[21])+1l);
		    				}else{
		    					divisionIdsMap.put((Long)objects[21],1l);
		    				}
		    				allDivisionsMap.remove((Long)objects[21]);
		    			}else{
		    				if(locIdsMap.get((Long)objects[20]) != null){ // local election body id
		    					locIdsMap.put((Long)objects[20],locIdsMap.get((Long)objects[20])+1l);
		    				}else{
		    					locIdsMap.put((Long)objects[20],1l);
		    					
		    				}
		    				try {
		    					allMunciMap.remove((Long)objects[20]);
			    				allCorpsMap.remove((Long)objects[20]);
							} catch (Exception e) {}
		    				
		    			}
		    		}else if(objects[19] != null){// mandal Id
		    			if(mandalIdsMap.get((Long)objects[19]) != null){
		    				mandalIdsMap.put((Long)objects[19],mandalIdsMap.get((Long)objects[19])+1l);
	    				}else{
	    					mandalIdsMap.put((Long)objects[19],1l);
	    				}
		    			allmandalsMap.remove((Long)objects[19]);
		    		}else{
		    			others=others+1;
		    		}
		    		CadreCommitteeMemberVO cadreCommitteeMemberVO=new CadreCommitteeMemberVO();
		    		cadreCommitteeMemberVO.setLevel(objects[0] != null ? Long.valueOf(objects[0].toString().trim()):0L);//roleId
		    		cadreCommitteeMemberVO.setRole(objects[1] != null ?objects[1].toString():"");//role
		    		cadreCommitteeMemberVO.setId(objects[2] != null ?(Long)objects[2]:0L);//cadreId
		    		cadreCommitteeMemberVO.setName(objects[3] != null ?objects[3].toString():"");//cadreName
		    		cadreCommitteeMemberVO.setImagePath(objects[4] != null ?objects[4].toString():"");//image
		    		cadreCommitteeMemberVO.setCasteName(objects[8] != null ? objects[8].toString().trim():"");
		    		cadreCommitteeMemberVO.setGender(objects[9] != null ? objects[9].toString().trim():"");
		    		cadreCommitteeMemberVO.setAge(objects[10] != null ? objects[10].toString().trim():"");
		    		cadreCommitteeMemberVO.setCasteGroupName(objects[12] != null ? objects[12].toString().trim():"");
		    		cadreCommitteeMemberVO.setMobileNo(objects[13] != null ? objects[13].toString().trim():"");
		    		cadreCommitteeMemberVO.setConstituencyName(objects[17] != null ? objects[17].toString(): "");
		    		cadreCommitteeMemberVO.setOccupation(objects[22] != null ? objects[22].toString() : "");
		    			    		
		    		if(cadreCommitteeMemberVO.getRole() != null && !cadreCommitteeMemberVO.getRole().isEmpty())
		    		{			    				    			
		    			Long rolescnt= rolesMap.get(cadreCommitteeMemberVO.getRole());
		    			if(rolescnt == null){
		    				rolescnt=1L;
		    				rolesMap.put(cadreCommitteeMemberVO.getRole(),rolescnt);
		    			}
		    			else{
		    				rolesMap.put(cadreCommitteeMemberVO.getRole(),rolescnt + 1);
		    			}
		    		}
		    		
		    		
		    		Map<String,Long> constiGenderMap = new LinkedHashMap<String, Long>();
		    		if(cadreCommitteeMemberVO.getConstituencyName() != null && !cadreCommitteeMemberVO.getConstituencyName().isEmpty())
		    		{
		    			if(constiMap.get(cadreCommitteeMemberVO.getConstituencyName().trim()) != null)
			    		{
		    				constiGenderMap = constiMap.get(cadreCommitteeMemberVO.getConstituencyName().trim());
			    		}
			    		
			    		if(cadreCommitteeMemberVO.getGender() != null)
			    		{
			    			Long constiGenderCount=0L;
		    				if(constiGenderMap.get(cadreCommitteeMemberVO.getGender().trim()) != null)
		    				{		    					
		    					constiGenderCount = constiGenderMap.get(cadreCommitteeMemberVO.getGender().trim());
		    				}	
		    				constiGenderCount = constiGenderCount+1;
			    			constiGenderMap.put(cadreCommitteeMemberVO.getGender().trim(), constiGenderCount);
			    		}
			    		constiMap.put(cadreCommitteeMemberVO.getConstituencyName().trim(), constiGenderMap);
		    		}	
		    		
		    		Map<String,Long> genderMap = new LinkedHashMap<String, Long>();
		    		if(cadreCommitteeMemberVO.getCasteName() != null && !cadreCommitteeMemberVO.getCasteName().isEmpty())
		    		{
		    			if(castesMap.get(cadreCommitteeMemberVO.getCasteName().trim()) != null)
			    		{
			    			genderMap = castesMap.get(cadreCommitteeMemberVO.getCasteName().trim());
			    		}
			    		
			    		if(cadreCommitteeMemberVO.getGender() != null)
			    		{
			    			Long casteCount=0L;
		    				if(genderMap.get(cadreCommitteeMemberVO.getGender().trim()) != null)
		    				{		    					
		    					casteCount = genderMap.get(cadreCommitteeMemberVO.getGender().trim());
		    				}	
			    			casteCount = casteCount+1;
		    				genderMap.put(cadreCommitteeMemberVO.getGender().trim(), casteCount);
			    		}
			    		castesMap.put(cadreCommitteeMemberVO.getCasteName().trim(), genderMap);
		    		}
		    		
		    		if(objects[14] != null)
		    		{
		    			Long voterId = objects[14] != null ? Long.valueOf(objects[14].toString().trim()):0L;
		    			cadreCommitteeMemberVO.setVoterId(voterId.toString());
		    			voteIdsList.add(voterId);
		    			cadreCommitteeMemberVO.setVoterCardNo(" (Own) ");
		    		}
		    		else if(objects[15] != null)
		    		{
		    			Long voterId = objects[15] != null ? Long.valueOf(objects[15].toString().trim()):0L;
		    			cadreCommitteeMemberVO.setVoterId(voterId.toString());
		    			voteIdsList.add(voterId);
		    			cadreCommitteeMemberVO.setVoterCardNo(" (Family) ");
		    		}
		    		cadreCommitteeMemberVO.setCommiteeName(objects[16] != null ? objects[16].toString().trim():"");
		    		
		    		
		    		CasteDetailsVO casteCatgVO = null; 
		    		List<Long> minorityCaste = new ArrayList<Long>();
		    		minorityCaste.add(292l);minorityCaste.add(301l);minorityCaste.add(430l);minorityCaste.add(459l);
		    		
		    		if(minorityCaste.contains((Long)objects[18])){
		    			casteCatgVO = casteGroupMap.get("Minority");
		    			if(casteCatgVO == null){
			    			casteCatgVO = new CasteDetailsVO();
			    			casteCatgVO.setCasteId(1l);
			    			casteGroupMap.put("Minority",casteCatgVO);
			    		}
		    			else{
			    			casteCatgVO.setCasteId(casteCatgVO.getCasteId() + 1l);
			    			casteGroupMap.put("Minority",casteCatgVO);
			    		}		    			
		    		}
		    		else{
		    			casteCatgVO = casteGroupMap.get(objects[12].toString().trim());
			    		if(casteCatgVO == null){		    		
			    			casteCatgVO = new CasteDetailsVO();
			    			casteCatgVO.setCasteId(1l);
			    			casteGroupMap.put(objects[12].toString().trim(),casteCatgVO);
			    		}
			    		else{
			    			casteCatgVO.setCasteId(casteCatgVO.getCasteId() + 1l);
			    			casteGroupMap.put(objects[12].toString().trim(),casteCatgVO);
			    		}
		    		}
		    		if(cadreCommitteeMemberVO.getGender().equalsIgnoreCase("M"))
	    			{
	    				casteCatgVO.setStateId(casteCatgVO.getStateId() + 1l);
	    			}
	    			else
	    			{
	    				casteCatgVO.setCastStateId(casteCatgVO.getCastStateId() + 1l);  
	    			}
	    			
		    		if(cadreCommitteeMemberVO.getAge() != null)
		    		{
		    			if(Long.valueOf(cadreCommitteeMemberVO.getAge()) >= 18 && Long.valueOf(cadreCommitteeMemberVO.getAge()) < 26)
		    			{
		    				CasteDetailsVO ageGroupVO = ageGroupMap.get("18-25");
		    				if(ageGroupVO == null)
		    				{
		    					ageGroupVO = new CasteDetailsVO();
		    					ageGroupVO.setCasteId(1l);
		    					ageGroupMap.put("18-25", ageGroupVO);
		    				}
		    				else
		    				{
		    					ageGroupVO.setCasteId(ageGroupVO.getCasteId() + 1l);
		    					ageGroupMap.put("18-25", ageGroupVO);
		    				}
		    				if(cadreCommitteeMemberVO.getGender().equalsIgnoreCase("M"))
			    			{
		    					ageGroupVO.setStateId(ageGroupVO.getStateId() + 1l);
			    			}
			    			else
			    			{
			    				ageGroupVO.setCastStateId(ageGroupVO.getCastStateId() + 1l);  
			    			}
		    			}
		    			else if(Long.valueOf(cadreCommitteeMemberVO.getAge()) >= 26 && Long.valueOf(cadreCommitteeMemberVO.getAge()) < 35)
		    			{
		    				CasteDetailsVO ageGroupVO = ageGroupMap.get("26-35");
		    				if(ageGroupVO == null)
		    				{
		    					ageGroupVO = new CasteDetailsVO();
		    					ageGroupVO.setCasteId(1l);
		    					ageGroupMap.put("26-35", ageGroupVO);
		    				}
		    				else
		    				{
		    					ageGroupVO.setCasteId(ageGroupVO.getCasteId() + 1l);
		    					ageGroupMap.put("26-35", ageGroupVO);
		    				}
		    				if(cadreCommitteeMemberVO.getGender().equalsIgnoreCase("M"))
			    			{
		    					ageGroupVO.setStateId(ageGroupVO.getStateId() + 1l);
			    			}
			    			else
			    			{
			    				ageGroupVO.setCastStateId(ageGroupVO.getCastStateId() + 1l);  
			    			}
		    			}
		    			else if(Long.valueOf(cadreCommitteeMemberVO.getAge()) >= 36 && Long.valueOf(cadreCommitteeMemberVO.getAge()) < 45)
		    			{
		    				CasteDetailsVO ageGroupVO = ageGroupMap.get("36-45");
		    				if(ageGroupVO == null)
		    				{
		    					ageGroupVO = new CasteDetailsVO();
		    					ageGroupVO.setCasteId(1l);
		    					ageGroupMap.put("36-45", ageGroupVO);
		    				}
		    				else
		    				{
		    					ageGroupVO.setCasteId(ageGroupVO.getCasteId() + 1l);
		    					ageGroupMap.put("36-45", ageGroupVO);
		    				}
		    				if(cadreCommitteeMemberVO.getGender().equalsIgnoreCase("M"))
			    			{
		    					ageGroupVO.setStateId(ageGroupVO.getStateId() + 1l);
			    			}
			    			else
			    			{
			    				ageGroupVO.setCastStateId(ageGroupVO.getCastStateId() + 1l);  
			    			}
		    			}
		    			else if(Long.valueOf(cadreCommitteeMemberVO.getAge()) >= 46 && Long.valueOf(cadreCommitteeMemberVO.getAge()) < 60)
		    			{
		    				CasteDetailsVO ageGroupVO = ageGroupMap.get("46-60");
		    				if(ageGroupVO == null)
		    				{
		    					ageGroupVO = new CasteDetailsVO();
		    					ageGroupVO.setCasteId(1l);
		    					ageGroupMap.put("46-60", ageGroupVO);
		    				}
		    				else
		    				{
		    					ageGroupVO.setCasteId(ageGroupVO.getCasteId() + 1l);
		    					ageGroupMap.put("46-60", ageGroupVO);
		    				}
		    				if(cadreCommitteeMemberVO.getGender().equalsIgnoreCase("M"))
			    			{
		    					ageGroupVO.setStateId(ageGroupVO.getStateId() + 1l);
			    			}
			    			else
			    			{
			    				ageGroupVO.setCastStateId(ageGroupVO.getCastStateId() + 1l);  
			    			}
		    			}
		    			else
		    			{
		    				CasteDetailsVO ageGroupVO = ageGroupMap.get("Above60");
		    				if(ageGroupVO == null)
		    				{
		    					ageGroupVO = new CasteDetailsVO();
		    					ageGroupVO.setCasteId(1l);
		    					ageGroupMap.put("Above60", ageGroupVO);
		    				}
		    				else
		    				{
		    					ageGroupVO.setCasteId(ageGroupVO.getCasteId() + 1l);
		    					ageGroupMap.put("Above60",ageGroupVO);
		    				}
		    				if(cadreCommitteeMemberVO.getGender().equalsIgnoreCase("M"))
			    			{
		    					ageGroupVO.setStateId(ageGroupVO.getStateId() + 1l);
			    			}
			    			else
			    			{
			    				ageGroupVO.setCastStateId(ageGroupVO.getCastStateId() + 1l);  
			    			}
		    			}
		    			

		    		}
		    		
		    		if(cadreCommitteeMemberVO.getGender().equalsIgnoreCase("M") || cadreCommitteeMemberVO.getGender().equalsIgnoreCase("Male"))
		    		{
		    			maleCount = maleCount+1;
		    		}
		    		else if(cadreCommitteeMemberVO.getGender().equalsIgnoreCase("F") || cadreCommitteeMemberVO.getGender().equalsIgnoreCase("Female"))
		    		{
		    			femaleCount = femaleCount+1;
		    		}
		    		
		    		if(cadreCommitteeMemberVO.getAge() == null)
					{					
						String dateOfBirth = objects[11] != null ? objects[11].toString().trim().substring(0, 10).trim():"";
						
						if(dateOfBirth.trim().length()>0)
						{
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							
							Date today = new Date();
							Date DOB = dateFormat.parse(dateOfBirth);

							Calendar startCalendar = new GregorianCalendar();
							startCalendar.setTime(DOB);
							Calendar endCalendar = new GregorianCalendar();
							endCalendar.setTime(today);

							int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
							//int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
							
							cadreCommitteeMemberVO.setAge(diffYear != 0 ? Long.valueOf(Integer.toString(diffYear)).toString():"");
						}
					}

		    		
		    		if(objects[5].toString().trim().length() > 8){
		    			cadreCommitteeMemberVO.setMembershipNo(objects[5].toString().trim().substring(objects[5].toString().trim().length()-8));
					}else{
						cadreCommitteeMemberVO.setMembershipNo(objects[5].toString());//membershipno
					}
		    		
		    		cadreCommitteeMemberVOList.add(cadreCommitteeMemberVO);
				}
		    	
		    	
		    	List<CasteDetailsVO> constiNameDetails = new ArrayList<CasteDetailsVO>();
		    	if(constiMap != null && constiMap.size()>0)
		    	{
		    		for (String constiName : constiMap.keySet()) {		    			
		    			Map<String,Long> constiGendersMap = constiMap.get(constiName);
		    			if(constiGendersMap != null && constiGendersMap.size()>0)
				    	{
				    		for (String genderStr : constiGendersMap.keySet()) {
				    			CasteDetailsVO constiVO = null;
				    			constiVO = getMatchedCasteDetailsVO(constiNameDetails,constiName.trim());
				    			if(constiVO == null)
				    			{
				    				constiVO = new CasteDetailsVO();
				    				constiVO.setCastName(constiName);
					    			if(genderStr.trim().equalsIgnoreCase("M") || genderStr.trim().equalsIgnoreCase("Male"))
					    			{
					    				constiVO.setMaleCount(constiGendersMap.get(genderStr).toString());
					    			}
					    			else if(genderStr.trim().equalsIgnoreCase("F") || genderStr.trim().equalsIgnoreCase("Female"))
					    			{
					    				constiVO.setFemaleCount(constiGendersMap.get(genderStr).toString());
					    			}
					    			Long mcount = constiVO.getMaleCount() != null ? new Long(constiVO.getMaleCount().toString()) : 0;
					    			Long fcount = constiVO.getFemaleCount() != null ? new Long(constiVO.getFemaleCount().toString()) : 0;
					    			constiVO.setCasteCount(mcount + fcount);
					    			
					    			constiNameDetails.add(constiVO);
				    			}
				    			else
				    			{
					    			if(genderStr.trim().equalsIgnoreCase("M") || genderStr.trim().equalsIgnoreCase("Male"))
					    			{
					    				constiVO.setMaleCount(constiGendersMap.get(genderStr).toString());
					    			}
					    			else if(genderStr.trim().equalsIgnoreCase("F") || genderStr.trim().equalsIgnoreCase("Female"))
					    			{
					    				constiVO.setFemaleCount(constiGendersMap.get(genderStr).toString());
					    			}
					    			Long mcount = constiVO.getMaleCount() != null ? new Long(constiVO.getMaleCount().toString()) : 0;
					    			Long fcount = constiVO.getFemaleCount() != null ? new Long(constiVO.getFemaleCount().toString()) : 0;
					    			constiVO.setCasteCount(mcount + fcount);
				    			}
				    		}
				    	}
					}
		    	}
		    	List<Long> committeeIds = tdpCommitteeDAO.getMainCommittiesInALocation(locationType, locationId,committeeEnrollmentIdsLst,startDate,endDate);
				if(committeeIds.size() > 0){
					committeeId = committeeIds.get(0);
				}	
		    	List<RolesVO> rolesList = new ArrayList<RolesVO>();
		    	if(committeeId != null){			
					//0committeeRoleid,1role name,2max nos
					List<Object[]> totalCommitteRolesList = tdpCommitteeRoleDAO.getAllCommitteeRoles(committeeId,committeeEnrollmentIdsLst,startDate,endDate);
					for(Object[] role:totalCommitteRolesList){
						RolesVO vo = new RolesVO();
						vo.setId((Long)role[0]);
						vo.setName(role[1].toString());
						vo.setTotal((Long)role[2]);//total positions 				
						vo.setFilledCount(rolesMap.get(role[1].toString())!= null ? rolesMap.get(role[1].toString()) : 0l);//filled positions 
						vo.setVacancyCount(vo.getTotal() - vo.getFilledCount());//vacant positions
						rolesList.add(vo);
					}
				}
		    	
		    	
		    	
		    	List<CasteDetailsVO> casteNameDetails = new ArrayList<CasteDetailsVO>();
		    	if(castesMap != null && castesMap.size()>0)
		    	{
		    		for (String casteName : castesMap.keySet()) {		    			
		    			Map<String,Long> gendersMap = castesMap.get(casteName);
		    			if(gendersMap != null && gendersMap.size()>0)
				    	{
				    		for (String genderStr : gendersMap.keySet()) {
				    			CasteDetailsVO casteVO = null;
				    			casteVO = getMatchedCasteDetailsVO(casteNameDetails,casteName.trim());
				    			if(casteVO == null)
				    			{
				    				casteVO = new CasteDetailsVO();
				    				casteVO.setCastName(casteName);
					    			if(genderStr.trim().equalsIgnoreCase("M") || genderStr.trim().equalsIgnoreCase("Male"))
					    			{
					    				casteVO.setMaleCount(gendersMap.get(genderStr).toString());
					    			}
					    			else if(genderStr.trim().equalsIgnoreCase("F") || genderStr.trim().equalsIgnoreCase("Female"))
					    			{
					    				casteVO.setFemaleCount(gendersMap.get(genderStr).toString());
					    			}
					    			
					    			Long mcnt = casteVO.getMaleCount() != null ? new Long(casteVO.getMaleCount().toString()) : 0;
					    			Long fcnt = casteVO.getFemaleCount() != null ? new Long(casteVO.getFemaleCount().toString()) : 0;
					    			casteVO.setCasteCount(mcnt + fcnt);
					    			casteNameDetails.add(casteVO);
				    			}
				    			else
				    			{
					    			if(genderStr.trim().equalsIgnoreCase("M") || genderStr.trim().equalsIgnoreCase("Male"))
					    			{
					    				casteVO.setMaleCount(gendersMap.get(genderStr).toString());
					    			}
					    			else if(genderStr.trim().equalsIgnoreCase("F") || genderStr.trim().equalsIgnoreCase("Female"))
					    			{
					    				casteVO.setFemaleCount(gendersMap.get(genderStr).toString());
					    			}
					    			Long mcnt = casteVO.getMaleCount() != null ? new Long(casteVO.getMaleCount().toString()) : 0;
					    			Long fcnt = casteVO.getFemaleCount() != null ? new Long(casteVO.getFemaleCount().toString()) : 0;
					    			casteVO.setCasteCount(mcnt + fcnt);
				    			}
				    		}
				    	}
					}
		    	}
		    	if(casteGroupMap != null && casteGroupMap.size() > 0)
		    	{
		    		List<CasteDetailsVO> casteGroupList = new ArrayList<CasteDetailsVO>();
		    		for (String casteGroup : casteGroupMap.keySet())
		    		{
		    			CasteDetailsVO ageGroupVO = casteGroupMap.get(casteGroup);
		    			ageGroupVO.setCastName(casteGroup);
		    			casteGroupList.add(ageGroupVO);
					}
		    		if(cadreCommitteeMemberVOList != null && cadreCommitteeMemberVOList.size() > 0)
		    		{
		    			
		    			Collections.sort(casteGroupList,sortDataForTotal);
		    			cadreCommitteeMemberVOList.get(0).setCasteGroupVO(casteGroupList);
		    		}
		    	}
		    	
		    	if(ageGroupMap != null && ageGroupMap.size() > 0)
		    	{
		    		List<CasteDetailsVO> ageGroupList = new ArrayList<CasteDetailsVO>();
		    		for (String casteGroup : ageGroupMap.keySet())
		    		{
		    			CasteDetailsVO ageGroupVO = ageGroupMap.get(casteGroup);
		    			if(ageGroupVO != null)
		    			{
		    				ageGroupVO.setCastName(casteGroup);
			    			ageGroupList.add(ageGroupVO);
		    			}
		    			
					}
		    		if(cadreCommitteeMemberVOList != null && cadreCommitteeMemberVOList.size() > 0)
		    		{
		    		
		    			cadreCommitteeMemberVOList.get(0).setAgeDetailsIfoVO(ageGroupList);
		    		}
		    	}
		    	if(cadreCommitteeMemberVOList != null && cadreCommitteeMemberVOList.size() > 0)
				{
		    	 cadreCommitteeMemberVOList.get(0).setLocationName(locationName);
		    	 
		    	 if(basicCommitteeTypeId != null && basicCommitteeTypeId.longValue()>0)
		    		 cadreCommitteeMemberVOList.get(0).setCommitte(tdpBasicCommitteeDAO.get(basicCommitteeTypeId).getName());
		    	 
		    	 cadreCommitteeMemberVOList.get(0).setFemaleCount(femaleCount.toString());
		    	 cadreCommitteeMemberVOList.get(0).setMaleCount(maleCount.toString());
		    	 Long totalCount = femaleCount+maleCount ;
		    	 cadreCommitteeMemberVOList.get(0).setTotal(totalCount);
		    	 Collections.sort(casteNameDetails,sortData);
		    	
		    	 cadreCommitteeMemberVOList.get(0).setCasteNameVO(casteNameDetails);
		    	 
		    	 Collections.sort(constiNameDetails,sortData);
		    	 cadreCommitteeMemberVOList.get(0).setConstiVOList(constiNameDetails);
		    	 cadreCommitteeMemberVOList.get(0).setMandalLevelDetails(populateMandalWiseInfo(mandalIdsMap,locIdsMap,divisionIdsMap,others));
		    	 
		    	 cadreCommitteeMemberVOList.get(0).setNotParticipatedMandals(populateMandalWiseInfo(allmandalsMap,null,null,null));
		    	 cadreCommitteeMemberVOList.get(0).setNotParticipatedLocalBodys(populateMandalWiseInfo(null,allMunciMap,null,null));
		    	 cadreCommitteeMemberVOList.get(0).setNotParticioatedOthers(populateMandalWiseInfo(null,allCorpsMap,null,null));
		    	 cadreCommitteeMemberVOList.get(0).setNotParticioatedDivisions(populateMandalWiseInfo(null,null,allDivisionsMap,null));
		    	 
		    	 cadreCommitteeMemberVOList.get(0).setActualDivistions(actualdivisionsCount);
		    	 cadreCommitteeMemberVOList.get(0).setActualLocalBodys(actualMuncisCount);
		    	 cadreCommitteeMemberVOList.get(0).setActualMandals(actualMandalsCount);
		    	 cadreCommitteeMemberVOList.get(0).setActualOthers(actualCorpsCount);
		    	 
		    	 cadreCommitteeMemberVOList.get(0).setRolesList(rolesList);		    	 
				}
		    	
		    	if(voteIdsList !=null && voteIdsList.size()>0)
		    	{
		    		List<Long> voterIdsList = new ArrayList<Long>();
		    		voterIdsList.addAll(voteIdsList);
		    		List<Object[]> list = voterDAO.getVotersInfoByVoterId(voterIdsList);
		    		if(list != null && list.size()>0)
		    		{
		    			for (Object[] voter : list) {
		    				CadreCommitteeMemberVO vo = null;
		    				String voterId = voter[0] != null ? voter[0].toString().trim():"";
		    				if(voterId != null && voterId.trim().length()>0)
		    				{
		    					vo = getMAtchedVOForVoterID(cadreCommitteeMemberVOList,voterId);
		    					if(vo != null && vo.getVoterCardNo() != null&& vo.getVoterCardNo().trim().length()>0)
		    					{
		    						String type = vo.getVoterCardNo().trim();
		    						String voterCardId = voter[1] != null ? voter[1].toString().trim():"";
		    						vo.setVoterCardNo(voterCardId+" "+type);		    						
		    					}
		    				}
						}
		    		}
		    	}
		    }
		}
		catch(Exception e){
			
			LOG.error("Exception raised in getComitteeMembersInfoByCommiteTypeAndLocation", e);
		}
		return cadreCommitteeMemberVOList;
	}
	
	public List<CasteDetailsVO> populateMandalWiseInfo(Map<Long,Long> mandalIdsMap,Map<Long,Long> locIdsMap,Map<Long,Long> divisionIdsMap,Long others){
		List<CasteDetailsVO> mandalLevelDetails = new ArrayList<CasteDetailsVO>();
		if(mandalIdsMap != null && mandalIdsMap.size() > 0){
			List<Object[]> tehsilDetails = tehsilDAO.getTehsilNameByTehsilIdsList(new ArrayList<Long>(mandalIdsMap.keySet()));
			for(Object[] tehsil:tehsilDetails){
				CasteDetailsVO vo = new CasteDetailsVO();
				vo.setCasteId((Long)tehsil[0]);
				vo.setCastName(tehsil[1].toString()+" Mandal");
				vo.setCasteCount(mandalIdsMap.get((Long)tehsil[0]));
				mandalLevelDetails.add(vo);
			}
		}
		if(locIdsMap != null && locIdsMap.size() > 0){
			List<Object[]> lobDetails = localElectionBodyDAO.getLocalElectionBodyNames(new ArrayList<Long>(locIdsMap.keySet()));
			for(Object[] lob:lobDetails){
				CasteDetailsVO vo = new CasteDetailsVO();
				vo.setCasteId((Long)lob[0]);
				vo.setCastName(lob[1].toString());
				vo.setCasteCount(locIdsMap.get((Long)lob[0]));
				mandalLevelDetails.add(vo);
			}
		}
		if(divisionIdsMap != null && divisionIdsMap.size() > 0){
			List<Object[]> divisionDetails = (List<Object[]>)localElectionBodyWardDAO.getLocalBodyElectionInfo(new ArrayList<Long>(divisionIdsMap.keySet()));
			for(Object[] division:divisionDetails){
				CasteDetailsVO vo = new CasteDetailsVO();
				vo.setCasteId((Long)division[0]);
				vo.setCastName(division[1].toString()+" Division");
				vo.setCasteCount(divisionIdsMap.get((Long)division[0]));
				mandalLevelDetails.add(vo);
			}
		}
		if(others != null && others.longValue() > 0){
			CasteDetailsVO vo = new CasteDetailsVO();
			vo.setCasteId(0l);
			vo.setCastName("Others");
			vo.setCasteCount(others);
			mandalLevelDetails.add(vo);
		}
		return mandalLevelDetails;
	}
	public static Comparator<CasteDetailsVO> sortData = new Comparator<CasteDetailsVO>()
		    {
		   
		        public int compare(CasteDetailsVO vo1, CasteDetailsVO vo2)
		        {
		        	 if(vo1.getCasteCount()  < vo2.getCasteCount()){
				            return 1;
				        } else {
				            return -1;
				        }
		        }
		    };
		    
		    public static Comparator<CasteDetailsVO> sortDataForTotal = new Comparator<CasteDetailsVO>()
				    {
				   
				        public int compare(CasteDetailsVO vo1, CasteDetailsVO vo2)
				        {
				        	 if(vo1.getCasteId()  < vo2.getCasteId()){
						            return 1;
						        } else {
						            return -1;
						        }
				        }
				    };
	
	private  CasteDetailsVO getMatchedCasteDetailsVO(List<CasteDetailsVO> casteDetailsVOList , String casteName)
	{
		CasteDetailsVO returnVO = null;
		try {
			
			if(casteDetailsVOList != null && casteDetailsVOList.size()>0)
			{
				for (CasteDetailsVO casteVo : casteDetailsVOList) {
					if(casteVo.getCastName().trim().equalsIgnoreCase(casteName.trim()))
					{
						return casteVo;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return returnVO;
	}
	public CadreCommitteeMemberVO getMAtchedVOForVoterID(List<CadreCommitteeMemberVO> cadreCommitteeMemberVOlist, String voterId)
	{
		CadreCommitteeMemberVO returnVO = null;
		try {
			if(cadreCommitteeMemberVOlist != null && cadreCommitteeMemberVOlist.size()>0)
			{
				for (CadreCommitteeMemberVO cadreCommitteeMemberVO : cadreCommitteeMemberVOlist) {
					if(cadreCommitteeMemberVO.getVoterId().toString().trim().equalsIgnoreCase(voterId.toString().trim()))
					{
						return cadreCommitteeMemberVO;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return returnVO;
	}
	public String userAccessTypeDetailsForDashBoard(Long userId, String accessType,Long accessValue){
		String userAccessValue = "ALL";
		try {
			
			if(accessType.equalsIgnoreCase("MP"))
			{				
				//userAccessValue = delimitationConstituencyAssemblyDetailsDAO.get(accessValue).getConstituency().getName()+" "+"Parliament";
				List<Object[]> list = constituencyDAO.getParliamentConstituencyByParliamentId(accessValue);
				userAccessValue = list.get(0)[1].toString() + " Parliament";
			}
			else
			{
				List<Long> districtIds = new ArrayList<Long>();
				List<Object[]> accessDistrictsList = userDistrictAccessInfoDAO.findByUser(userId);
				if(accessDistrictsList != null && accessDistrictsList.size()>0)
				{
					for (Object[] districtId : accessDistrictsList) {
						districtIds.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
					}
					
					if(districtIds != null && districtIds.size() == 1)
					{
						Long districtId = districtIds.get(0).longValue();
						if(districtId != 0L)
							userAccessValue =  districtDAO.get(districtId).getDistrictName()+" District";
					}
					else if(districtIds != null && districtIds.contains(1L)) // Adilabad
					{
						userAccessValue = "TS";
					}
					else if(districtIds != null && districtIds.contains(11L))//Srikakulam
					{
						userAccessValue = "AP";
					}
				}	
			}
		} catch (Exception e) {
			LOG.error("Exception raised in userAccessTypeDetailsForDashBoard", e);
		}
		return userAccessValue;
	}
	
	public List<IdNameVO> getStatesForLocationLevel(String accessType,Long accessValue){
		
		List<IdNameVO> statesList = new ArrayList<IdNameVO>();
		try{
			
			List<Object[]> locationDetails = null;
			if(accessType !=null && !accessType.equalsIgnoreCase("District")){
				List<Long> stateIds =new ArrayList<Long>();
				stateIds.add(1l);
				stateIds.add(36l);
				locationDetails = stateDAO.getAllStatesByStateIds(stateIds);
			}else if(accessType !=null && accessType.equalsIgnoreCase("District")){
				
				List<Long> distIds = new ArrayList<Long>();
				distIds.add(accessValue);
				
				locationDetails = districtDAO.getStatesForDistricts(distIds);
			}
			
			if(locationDetails !=null && locationDetails.size()>0){
				for (Object[] objects : locationDetails) {					
					IdNameVO vo = new IdNameVO();					
					vo.setId(objects[0] !=null ? (Long)objects[0]:0l);
					vo.setName(objects[1] !=null ? objects[1].toString():"");					
					statesList.add(vo);
				}
			}
			
		}catch (Exception e) {
			LOG.error("Exception raised in getStatesForLocationLevel", e);
		}
		return statesList;
	}
	
	public ResultStatus  approvingChangeDesignations(final Long cadreCommitteeIncreasedPositionsId,final String approvedStatus){
		 ResultStatus rs=new ResultStatus();
	 try
	 {
		if(approvedStatus.equalsIgnoreCase("Rejected")){
			CadreCommitteeIncreasedPositions cadreCommitteeIncreasedPositions=cadreCommitteeIncreasedPositionsDAO.get(cadreCommitteeIncreasedPositionsId);
			cadreCommitteeIncreasedPositions.setStatus(approvedStatus);
			cadreCommitteeIncreasedPositions.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
			cadreCommitteeIncreasedPositionsDAO.save(cadreCommitteeIncreasedPositions);
			
			rs.setResultCode(1);
		}
		else if(approvedStatus.equalsIgnoreCase("Approved")){
			
			 List<Object[]> rolesList=cadreCommitteeChangeDesignationsDAO.gettingCommitteeRolesByIncreasedPositionsId(cadreCommitteeIncreasedPositionsId);
			 final List<LocationWiseBoothDetailsVO> changeDesignationsList=new ArrayList<LocationWiseBoothDetailsVO>();
			   if(rolesList!=null && rolesList.size()>0){
				  for (Object[] objects : rolesList) {
					    LocationWiseBoothDetailsVO changeDesignationsVO=new LocationWiseBoothDetailsVO();
						changeDesignationsVO.setLocationId((Long)objects[1]);//tdpCommitteeMemberId
						changeDesignationsVO.setTotal((Long)objects[2]);//cadreId
						changeDesignationsVO.setPopulation((Long)objects[3]);//currentRole
						changeDesignationsVO.setVotesPolled((Long)objects[4]);//newRole
						changeDesignationsList.add(changeDesignationsVO);
				   } 
			    }
			   
			   
			   rs=gettingStatus((Long)rolesList.get(0)[0],changeDesignationsList);
			  
			  if(rs.getIsResultPartial()==false){
				  synchronized("APPROVINGDESIGNATIONS"){
					   transactionTemplate.execute(new TransactionCallbackWithoutResult() 
				       {
						  public void doInTransactionWithoutResult(TransactionStatus status) 
						  {
							 for (LocationWiseBoothDetailsVO locationWiseBoothDetailsVO : changeDesignationsList){
								 
								 //updating tdpCommitteeRole with new role.
								TdpCommitteeMember tdpCommitteeMember= tdpCommitteeMemberDAO.get(locationWiseBoothDetailsVO.getLocationId());
								tdpCommitteeMember.setTdpCommitteeRoleId(locationWiseBoothDetailsVO.getVotesPolled());
								tdpCommitteeMemberDAO.save(tdpCommitteeMember);
								
								//updating date,status.
								CadreCommitteeIncreasedPositions cadreCommitteeIncreasedPositions=cadreCommitteeIncreasedPositionsDAO.get(cadreCommitteeIncreasedPositionsId);
								cadreCommitteeIncreasedPositions.setStatus(approvedStatus);
								cadreCommitteeIncreasedPositions.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
								cadreCommitteeIncreasedPositionsDAO.save(cadreCommitteeIncreasedPositions);
							 }
							 
						  }
					  
				       });
			       }
			    }
			 
			  if(rs.getIsResultPartial()==false){
				  rs.setResultCode(1); 
			  }
			  else{
				  rs.setResultCode(2); 
				  rs.setMessage(rs.getMessage());
			   }
		
		}
	
	 }catch (Exception e) {
		 LOG.error("Exception raised in approvingChangeDesignations", e);
		 rs.setResultCode(0);
	}	
    return rs;
	}
	

public CommitteeApprovalVO statusForChangeDesignationsApproval(){
	LOG.debug(" In statusForChangeDesignationsApproval() ");
	CommitteeApprovalVO cv = new CommitteeApprovalVO();
	try{
		List<Object[]> list = cadreCommitteeIncreasedPositionsDAO.statusForChangeDesignationsApproval();
		Long totalCount = 0l;
		if(list!=null && list.size()>0){
			for(Object[] obj:list){
				if(obj[1].toString().equalsIgnoreCase("Approved")){
					cv.setApprovedCount(Long.valueOf(obj[0].toString()));
				}else if(obj[1].toString().equalsIgnoreCase("Rejected")){
					cv.setRejectedCount(Long.valueOf(obj[0].toString()));
				}else{
					cv.setPendingCount(Long.valueOf(obj[0].toString()));
				}
				totalCount = totalCount + Long.valueOf(obj[0].toString());
			}
			cv.setTotalCount(totalCount);
		}
	}catch (Exception e) {
		LOG.error("Exception Raised in statusForChangeDesignationsApproval() ");
	}
	return cv;

}

public Map<String,List<Long>> getLocalBodiesDivisionsMandalByContituencyIds(List<Long> constituencyIds,List<Long> levelIds){
	
	List<Long> divisionLclIds = new ArrayList<Long>();
	List<Long> divisionWardId = new ArrayList<Long>();
	List<Long> lebIds = new ArrayList<Long>();
	List<Long> tehsilIds = new ArrayList<Long>();
	
	Map<String,List<Long>> locationsMap = new HashMap<String, List<Long>>();
	
	
	List<Object[]> localBodyIdsList = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituencyList(constituencyIds);
	for(Object[] localBody:localBodyIdsList){
		Long localBdyId = (Long)localBody[0];
		if((localBdyId.longValue() == 20l ||  localBdyId.longValue() == 124l || localBdyId.longValue() == 119l)){
			if(!divisionLclIds.contains(localBdyId)){
				divisionLclIds.add(localBdyId);
			}
    	}else{
    		/*List<Long> constiIds = localBodiesMap.get(localBdyId);
    		if(constiIds == null){
    			constiIds = new ArrayList<Long>();
    			localBodiesMap.put(localBdyId,constiIds);
    		}*/
    		if(!lebIds.contains((Long)localBody[0])){
    			lebIds.add((Long)localBody[0]);
    		}
    	}
	}
	
	
	if(divisionLclIds.size() > 0){
		List<Object[]> divisionIdsList = assemblyLocalElectionBodyWardDAO.findWardsByLocalBodyIdsAndConstituencyIds(divisionLclIds, constituencyIds);
		for(Object[] division:divisionIdsList){
			Long divisionId = (Long)division[0];
			/*List<Long> constiIds = divisionIdsMap.get(divisionId);
    		if(constiIds == null){
    			constiIds = new ArrayList<Long>();
    			divisionIdsMap.put(divisionId,constiIds);
    		}*/
			if(!divisionWardId.contains(divisionId)){
				divisionWardId.add(divisionId);
			}
		}
	}
	
	
	List<BigInteger> delimitationIds = delimitationConstituencyDAO.getDelimitIds(constituencyIds);
	 if(delimitationIds.size() > 0){
		 List<Long> ids = new ArrayList<Long>();
		 for(BigInteger delimitationId:delimitationIds){
			 ids.add(delimitationId.longValue());
		 }
		 List<Object[]> tehsilIdsList = delimitationConstituencyMandalDAO.getTehsilsByDelimitationConstituencyIDs(ids) ;
		 for(Object[] tehsil:tehsilIdsList){
			/*	Long tehsilId = (Long)tehsil[0];
				List<Long> tehsilIds = mandalIdsMap.get(tehsilId);
       		if(tehsilIds == null){
       			tehsilIds = new ArrayList<Long>();
       			mandalIdsMap.put(tehsilId,tehsilIds);
       		}*/
			 if(!tehsilIds.contains((Long)tehsil[0])){
				 tehsilIds.add((Long)tehsil[0]);
			 }
			}
	 }
	 
	 
	 if(levelIds.size()==1){
		 if(levelIds.contains(5l)){
			 locationsMap.put("Tehsils", tehsilIds);
			 locationsMap.put("DivisionWards", new ArrayList<Long>());
			 locationsMap.put("LocalBodies", new ArrayList<Long>());
		}
		 if(levelIds.contains(7l)){
			 locationsMap.put("Tehsils", new ArrayList<Long>());
			 locationsMap.put("DivisionWards", new ArrayList<Long>());
			 locationsMap.put("LocalBodies", lebIds);
		}
		 if(levelIds.contains(9l)){
			 locationsMap.put("Tehsils", new ArrayList<Long>());
			 locationsMap.put("DivisionWards", divisionWardId);
			 locationsMap.put("LocalBodies", new ArrayList<Long>());
		}
	 }else{
		 locationsMap.put("Divisions", divisionLclIds);
		 locationsMap.put("DivisionWards", divisionWardId);
		 locationsMap.put("LocalBodies", lebIds);
		 locationsMap.put("Tehsils", tehsilIds);
	 }
	 
	 return locationsMap;
	 
}

	public CadreCommitteeRolesInfoVO getConstituencyOverviewCommitteeRoleAgeWiseDetailsByLocationType(String userAccessType,String locationValue,Long committeeTypeId,List<Long> positionIdsList,List<Long> casteCategoryIdsList,List<Long> casteCategoryGroupIdsList, 
		List<Long> casteIdsList,Long locationLevelId,Long userId, Long accessValue,String selectedRadio,List<Long> enrollIdsList)
	{
		CadreCommitteeRolesInfoVO returnVO = new CadreCommitteeRolesInfoVO();
		try {
			
			List<Long> locationIdsList  = null;
			List<Long> wardIdsList  = null;
			List<Long> committeeTypeIdsList  = new ArrayList<Long>();
			String segrigatStr = "";
			Long enrollmentId = 0L;
			if(enrollIdsList.contains(1l)){
				enrollmentId = 3L;
			}else if(enrollIdsList.contains(2l)){
				enrollmentId = 4L;
			}
			
			if(committeeTypeId != null && Long.valueOf(committeeTypeId).longValue() != 0L)
				committeeTypeIdsList.add(committeeTypeId);
			
			locationIdsList = new ArrayList<Long>();
			if(locationValue != null && Long.valueOf(locationValue).longValue() != 0L)
			{
				locationIdsList.add( Long.valueOf(locationValue));
			
			Constituency constituency = constituencyDAO.get(Long.valueOf(locationValue));
			String areaType=constituency.getAreaType();
			
			if(selectedRadio.equalsIgnoreCase("2")) //Mandal/Muncipality
			{
				locationLevelId = 6l;
				if(areaType.equalsIgnoreCase("RURAL"))
					locationIdsList = boothDAO.getLocationIds(Long.valueOf(locationValue),IConstants.TEHSIL,IConstants.VOTER_DATA_PUBLICATION_ID);
				else if(areaType.equalsIgnoreCase("RURAL-URBAN"))
				{
					locationIdsList = boothDAO.getLocationIds(Long.valueOf(locationValue),IConstants.TEHSIL,IConstants.VOTER_DATA_PUBLICATION_ID);
					wardIdsList = boothDAO.getLocationIds(Long.valueOf(locationValue),IConstants.LOCAL_ELECTION_BODY,IConstants.VOTER_DATA_PUBLICATION_ID);
				}
				else if(areaType.equalsIgnoreCase("URBAN"))
				{
					wardIdsList = boothDAO.getLocationIds(Long.valueOf(locationValue),IConstants.LOCAL_ELECTION_BODY,IConstants.VOTER_DATA_PUBLICATION_ID);	
				}
				
				segrigatStr = "MandalORTown";
			}
			else if(selectedRadio.equalsIgnoreCase("1"))
			{
				locationLevelId = 5l;
				if(areaType.equalsIgnoreCase("RURAL"))
				{
				locationIdsList = boothDAO.getLocationIds(Long.valueOf(locationValue),IConstants.PANCHAYAT,IConstants.VOTER_DATA_PUBLICATION_ID);
				}
				else if(areaType.equalsIgnoreCase("RURAL-URBAN"))
				{
					//wardIdsList = boothDAO.getLocationIds(Long.valueOf(locationValue),IConstants.WARD,IConstants.VOTER_DATA_PUBLICATION_ID);
					locationIdsList = boothDAO.getLocationIds(Long.valueOf(locationValue),IConstants.PANCHAYAT,IConstants.VOTER_DATA_PUBLICATION_ID);
					wardIdsList =assemblyLocalElectionBodyWardDAO.getWardsByconstituency(Long.valueOf(locationValue));
				}
				else if(areaType.equalsIgnoreCase("URBAN"))
				{
					wardIdsList =assemblyLocalElectionBodyWardDAO.getWardsByconstituency(Long.valueOf(locationValue));	
				}
				
				segrigatStr = "VillageORWard";
			}
		  }
			DecimalFormat decimalFormat = new DecimalFormat("##.##");
			Set<Long> locationIdsSet = new HashSet<Long>();
			locationIdsSet.add(Long.valueOf(locationValue));
			
			List<Object[]> constituencyList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationIdsSet,"Constituency",null,"Registered",enrollmentId);
			Long registeredCadreCount = 0L;
			if(constituencyList != null && constituencyList.size()>0)
			{
				for (Object[] consituncy : constituencyList){
					registeredCadreCount = consituncy[1] != null ? Long.valueOf(consituncy[1].toString().trim()):0L;
					returnVO.setAvailableCadreCount(registeredCadreCount.toString());
				}
			}
			
			if(locationIdsList != null && locationIdsList.size()>0)
			{
				if(locationIdsList.contains(null))
				{
					locationIdsList.remove(null);
				}
			}
			if(wardIdsList != null && wardIdsList.size()>0)
			{
				if(wardIdsList.contains(null))
				{
					wardIdsList.remove(null);
				}
			}
			
			List<Object[]> casteWiseCategoryList = tdpCommitteeMemberDAO.getCasteCategoryInfoForLocations(locationLevelId,locationIdsList,wardIdsList,userAccessType,segrigatStr,"casteCategory",enrollIdsList);
			Map<String , Long> casteCategoryCountMap = new HashMap<String, Long>();
			
			if(casteWiseCategoryList != null && casteWiseCategoryList.size()>0)
			{
				for (Object[] casteWiseCategory : casteWiseCategoryList) {
					Long categoryId = casteWiseCategory[1] != null ? Long.valueOf(casteWiseCategory[1].toString()):0L;
					Long count = casteWiseCategory[2] != null ? Long.valueOf(casteWiseCategory[2].toString()):0L;
					String categoryName  = categoryId != 0L ? (categoryId == 1L? "OC":(categoryId == 2L? "BC":(categoryId == 3L? "SC" : (categoryId == 4L? "ST":"")))):"N/A";
					Long categoryCount = 0L;
					if(casteCategoryCountMap.get(categoryName) != null)
					{
						categoryCount = casteCategoryCountMap.get(categoryName);
					}
					categoryCount = categoryCount+count;
					casteCategoryCountMap.put(categoryName, categoryCount);
				}
			}
			
			List<Object[]> casteCategoryWiseCountList = tdpCommitteeMemberDAO.getCommitteeRoleCasteCategoryNameWiseDetailsByLocationType(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,null,enrollIdsList,null,null);
			List<CadreCommitteeRolesInfoVO> casteCategoryVOList = new ArrayList<CadreCommitteeRolesInfoVO>();
			
			List<Long> constiteuncyIds = new ArrayList<Long>();
			constiteuncyIds.add(Long.valueOf(locationValue.toString()));
			Long registeredCount  = tdpCadreInfoDAO.getTdpCadreCountForLocations(IConstants.CONSTITUENCY,constiteuncyIds,"Registered","Constituency",enrollmentId);
			
			if(casteCategoryWiseCountList != null && casteCategoryWiseCountList.size()>0)
			{
				Long totalGenderCount = 0L;
				
				for (Object[] param : casteCategoryWiseCountList) 
				{
					Long maleCount = 0L;
					Long femaleCount = 0L;
					boolean isAvailable = false;
					CadreCommitteeRolesInfoVO cadreVO = getMatchedByCasteCategoryName(casteCategoryVOList,param[1] != null ? param[1].toString().trim():"");
					
					if(cadreVO != null)
					{
						isAvailable = true;
						maleCount = cadreVO.getMaleCount();
						femaleCount = cadreVO.getFemaleCount();
					}
					else
					{
						cadreVO = new CadreCommitteeRolesInfoVO();
						cadreVO.setCaste(param[0] != null ? param[0].toString():"");
						cadreVO.setCasteCategory(param[1] != null ? param[1].toString():"");
						cadreVO.setAvailableCasteCount(casteCategoryCountMap.get(cadreVO.getCasteCategory()) != null ? casteCategoryCountMap.get(cadreVO.getCasteCategory()).toString():"0");
					}
					
					String genderType = param[2] != null ? param[2].toString():"";
					
					if(genderType.equalsIgnoreCase("M"))
					{
						Long tempCount = param[3] != null ? Long.valueOf(param[3].toString()):0L;
						maleCount = maleCount+tempCount;
					}
					else if(genderType.equalsIgnoreCase("F"))
					{
						Long tempCount = param[3] != null ? Long.valueOf(param[3].toString()):0L;
						femaleCount = femaleCount+tempCount;
					}
					cadreVO.setMaleCount(maleCount);
					cadreVO.setFemaleCount(femaleCount);
					totalGenderCount = maleCount + femaleCount;
					cadreVO.setTotalCount(totalGenderCount);
					
					if(!isAvailable)
						casteCategoryVOList.add(cadreVO);
				}
			}
			
			List<Object[]> casteWiseList = tdpCommitteeMemberDAO.getCasteInfoForLocations(locationLevelId,locationIdsList,wardIdsList,userAccessType,segrigatStr,"caste",enrollIdsList);
			Map<Long , Long> casteCountMap = new HashMap<Long, Long>();
			List<Long> minorityCastes = new ArrayList<Long>();
			minorityCastes.add(459L);//459,301,292,430
			minorityCastes.add(301L);
			minorityCastes.add(292L);
			minorityCastes.add(430L);
			Long minorityAvailableCount = 0L;
			Long maleMinority =0L;
			Long femaleMinority = 0L;	
			
			if(casteWiseList != null && casteWiseList.size()>0)
			{
				for (Object[] caste : casteWiseList) {
					Long castestateId = caste[1] != null ? Long.valueOf(caste[1].toString()):0L;
					Long count = caste[2] != null ? Long.valueOf(caste[2].toString()):0L;
					Long categoryCount = 0L;
					if(casteCountMap.get(castestateId) != null)
					{
						categoryCount = casteCountMap.get(castestateId);
					}
					categoryCount = categoryCount+count;
					casteCountMap.put(castestateId, categoryCount);
					if(minorityCastes.contains(castestateId))
					{
						minorityAvailableCount = minorityAvailableCount+count;
					}					
				}
			}
			
			List<Object[]> casteWiseCountList = tdpCommitteeMemberDAO.getCommitteeRoleCasteNameWiseDetailsByLocationType(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,null,enrollIdsList,null,null);
					
			
			List<CadreCommitteeRolesInfoVO> castewiseVOList = new ArrayList<CadreCommitteeRolesInfoVO>();
			if(casteWiseCountList != null && casteWiseCountList.size()>0)
			{
				
				Long totalGenderCount = 0L;
				for (Object[] param : casteWiseCountList) {					
					Long maleCount = 0L;
					Long femaleCount = 0L;	
					boolean isAvailable = false;
					CadreCommitteeRolesInfoVO cadreVO = getMatchedByCasteName(castewiseVOList,param[1] != null ? param[1].toString().trim():"");
					
					if(cadreVO != null)
					{
						isAvailable = true;
						maleCount = cadreVO.getMaleCount();
						femaleCount = cadreVO.getFemaleCount();
					}
					else
					{
						cadreVO = new CadreCommitteeRolesInfoVO();
						cadreVO.setCaste(param[1] != null ? param[1].toString():"");
						Long casteStateId = param[0] != null ? Long.valueOf(param[0].toString()):0L;
						Long  avalCasteCount = casteCountMap.get(casteStateId) != null ? casteCountMap.get(casteStateId) :0L;
						cadreVO.setAvailableCasteCount(avalCasteCount.toString());
					}
					
					String genderType = param[2] != null ? param[2].toString():"";
					
					if(genderType.equalsIgnoreCase("M"))
					{
						Long tempCount = param[3] != null ? Long.valueOf(param[3].toString()):0L;
						Long casteStateId = param[0] != null ? Long.valueOf(param[0].toString()):0L;
						maleCount = maleCount+tempCount;
						if(minorityCastes.contains(casteStateId))
						{
							maleMinority = maleMinority+tempCount;
						}						
					}
					else if(genderType.equalsIgnoreCase("F"))
					{
						Long tempCount = param[3] != null ? Long.valueOf(param[3].toString()):0L;
						Long casteStateId = param[0] != null ? Long.valueOf(param[0].toString()):0L;
						femaleCount = femaleCount+tempCount;
						if(minorityCastes.contains(casteStateId))
						{
							femaleMinority = femaleMinority+tempCount;
						}
					}
					cadreVO.setMaleCount(maleCount);
					cadreVO.setFemaleCount(femaleCount);
					totalGenderCount = maleCount + femaleCount;
					cadreVO.setTotalCount(totalGenderCount);
					
					if(!isAvailable)
						castewiseVOList.add(cadreVO);
				}
			}
			
			if(maleMinority.longValue() != 0 || femaleMinority.longValue() != 0L)
			{
				CadreCommitteeRolesInfoVO minorityCadreVO  = new CadreCommitteeRolesInfoVO();
				minorityCadreVO.setCasteCategory("Minority");
				minorityCadreVO.setMaleCount(maleMinority);
				minorityCadreVO.setFemaleCount(femaleMinority);
				minorityCadreVO.setAvailableCasteCount(minorityAvailableCount.toString());
				Long total = maleMinority + femaleMinority;
				minorityCadreVO.setTotalCount(total);
				
				casteCategoryVOList.add(minorityCadreVO);
				
			}
			
			List<Object[]> voterAgeRangeDetails = voterAgeInfoDAO.getVoterAgeInfoListByconstituencyExceptYouth(Long.valueOf(locationValue.toString().trim()),IConstants.VOTER_DATA_PUBLICATION_ID);
			Map<Long,Long> availableVotersByAgeRange = new HashMap<Long, Long>();
			Long avaibaleTotalAgeRangeCount = 0L;
			if(voterAgeRangeDetails != null && voterAgeRangeDetails.size()>0)
			{
				for (Object[] voterAgeRange : voterAgeRangeDetails)
				{
					//String ageRange = voterAgeRange[1] != null ? voterAgeRange[1].toString().trim():"";
					Long ageRangeId = voterAgeRange[0] != null ? Long.valueOf(voterAgeRange[0].toString().trim()):0L;
					Long votersCount = voterAgeRange[2] != null ? Long.valueOf(voterAgeRange[2].toString().trim()):0L;
					avaibaleTotalAgeRangeCount = avaibaleTotalAgeRangeCount+votersCount;
					availableVotersByAgeRange.put(ageRangeId, votersCount);
				}
			}
			List<Object[]> ageRangeList = tdpCommitteeMemberDAO.getCadreAgerangeInfoForLocations(locationLevelId,locationIdsList,wardIdsList,userAccessType,segrigatStr,enrollIdsList);
			Map<Long , Long> agerangeCountMap = new HashMap<Long, Long>();
			
			if(ageRangeList != null && ageRangeList.size()>0)
			{
				for (Object[] agerange : ageRangeList) {
					Long agerangeId = agerange[1] != null ? Long.valueOf(agerange[1].toString()):0L;
					Long count = agerange[2] != null ? Long.valueOf(agerange[2].toString()):0L;
					Long categoryCount = 0L;
					if(agerangeCountMap.get(agerangeId) != null)
					{
						categoryCount = agerangeCountMap.get(agerangeId);
					}
					categoryCount = categoryCount+count;
					
					agerangeCountMap.put(agerangeId, categoryCount);
				}
			}
			
			List<Object[]> cadreDetails = tdpCommitteeMemberDAO.getCommitteeRoleAgerangeWiseDetailsByLocationType(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,null,enrollIdsList,null,null);
			List<CadreCommitteeRolesInfoVO> ageRangeVOList = new LinkedList<CadreCommitteeRolesInfoVO>();
						
			if(cadreDetails != null && cadreDetails.size()>0)			
			{				
				Long totalGenderCount = 0L;
				for (Object[] param : cadreDetails) {					
					Long maleCount = 0L;
					Long femaleCount = 0L;	
					boolean isAvailable = false;
					CadreCommitteeRolesInfoVO cadreVO = getMatchedByAgeRange(ageRangeVOList,param[1] != null ? param[1].toString().trim():"");
					
					if(cadreVO != null)
					{
						isAvailable = true;
						maleCount = cadreVO.getMaleCount();
						femaleCount = cadreVO.getFemaleCount();
					}
					else
					{
						cadreVO = new CadreCommitteeRolesInfoVO();
						cadreVO.setAgeRange(param[1] != null ? param[1].toString():"");
						Long agerangeID = param[0] != null ? Long.valueOf(param[0].toString().trim()):0L;
						Long agerangecount = agerangeCountMap.get(agerangeID) != null ? agerangeCountMap.get(agerangeID) :0L;
						cadreVO.setAvaibleAgeWiseCount(agerangecount.toString());
						
						Long availableVoterCount = availableVotersByAgeRange.get(agerangeID) != null ? availableVotersByAgeRange.get(agerangeID) :0L;
						cadreVO.setAvailableVoters(availableVoterCount);
						
						Double votersPerc = Double.valueOf(decimalFormat.format((availableVoterCount * 100.00) / avaibaleTotalAgeRangeCount));
						cadreVO.setAvailableVotersPerc(votersPerc);
						
					}
					
					String genderType = param[2] != null ? param[2].toString():"";
					
					if(genderType.equalsIgnoreCase("M"))
					{
						Long tempCount = param[3] != null ? Long.valueOf(param[3].toString()):0L;
						maleCount = maleCount+tempCount;
						//totalMaleCount = totalMaleCount+tempCount;
					}
					else if(genderType.equalsIgnoreCase("F"))
					{
						Long tempCount = param[3] != null ? Long.valueOf(param[3].toString()):0L;
						femaleCount = femaleCount+tempCount;
						//totalFemaleCount = totalFemaleCount+tempCount;
					}
					cadreVO.setMaleCount(maleCount);
					cadreVO.setFemaleCount(femaleCount);
					totalGenderCount = maleCount + femaleCount;
					cadreVO.setTotalCount(totalGenderCount);
					
					if(!isAvailable)
						ageRangeVOList.add(cadreVO);
				}
			
			}
			
			List<Object[]> genderWiseResults = tdpCommitteeMemberDAO.getCommitteeRolesGenderWiseDetailsByLocation(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,null,null,enrollIdsList,null,null);
			Long totalCount = 0L;
			Long totalMaleCount = 0L;
			Long totalFemaleCount = 0L;
			if(genderWiseResults != null && genderWiseResults.size()>0)			
			{				
				for (Object[] param : genderWiseResults) {					
					String genderType = param[0] != null ? param[0].toString():"";
					
					if(genderType.equalsIgnoreCase("M"))
					{
						Long tempCount = param[1] != null ? Long.valueOf(param[1].toString()):0L;
						totalMaleCount = totalMaleCount+tempCount;
					}
					else if(genderType.equalsIgnoreCase("F"))
					{
						Long tempCount = param[1] != null ? Long.valueOf(param[1].toString()):0L;
						totalFemaleCount = totalFemaleCount+tempCount;
					}
				}
			}
			
			List<CadreCommitteeRolesInfoVO> roleVOList = new ArrayList<CadreCommitteeRolesInfoVO>();
			
			if(totalMaleCount.longValue() != 0L || totalFemaleCount.longValue() != 0L)
			{
				totalCount = totalMaleCount + totalFemaleCount;
				CadreCommitteeRolesInfoVO cadreVO = new CadreCommitteeRolesInfoVO();
				cadreVO.setMaleCount(totalMaleCount);
				cadreVO.setFemaleCount(totalFemaleCount);
				cadreVO.setTotalCount(totalCount);
				cadreVO.setAvailableCadreCount(registeredCount.toString());
				roleVOList.add(cadreVO);
			}
						
			if(roleVOList != null && roleVOList.size()>0)
			{
				returnVO.setCadreCommitteeRolesInfoVOList(roleVOList);
			}
			if(casteCategoryVOList != null && casteCategoryVOList.size()>0)
			{
				for (CadreCommitteeRolesInfoVO casteVO : casteCategoryVOList) 
				{
					if(casteVO != null)
					{
						Long casteTotalCount = casteVO.getTotalCount();
						Long casteMaleCount = casteVO.getMaleCount();
						Long casteFemaleCount = casteVO.getFemaleCount();

						Double malePerc = 0.00d;
						Double femalePerc = 0.00d;
						Double castePercInTotal = 0.00d;
						if(casteMaleCount != null && totalMaleCount != null && totalMaleCount.longValue() > 0){
						 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / totalMaleCount));
						}
						if(casteFemaleCount != null && totalFemaleCount != null && totalFemaleCount.longValue() > 0){
						 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / totalFemaleCount));
						}
						if(casteTotalCount != null && totalCount != null && totalCount.longValue() > 0){
						 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / totalCount));
						}
						
						casteVO.setMalePerc(malePerc);
						casteVO.setFemalePerc(femalePerc);
						casteVO.setTotalPerc(castePercInTotal);
						
						Long registerdCadreCount = Long.valueOf(casteVO.getAvailableCasteCount().trim());
						casteVO.setAvailableCadrePerc(Double.valueOf(decimalFormat.format((registerdCadreCount * 100.00) / registeredCount)));
						//casteVO.setAvailableCadreCount(registerdCadreCount.toString());
					}
				}
				returnVO.setCasteCategoryWiseList(casteCategoryVOList);
			}
						
			if(castewiseVOList != null && castewiseVOList.size()>0)
			{
				for (CadreCommitteeRolesInfoVO casteVO : castewiseVOList) 
				{
					if(casteVO != null)
					{
						Long casteTotalCount = casteVO.getTotalCount();
						Long casteMaleCount = casteVO.getMaleCount();
						Long casteFemaleCount = casteVO.getFemaleCount();

						Double malePerc = 0.00d;
						Double femalePerc = 0.00d;
						Double castePercInTotal = 0.00d;
						
						if(casteMaleCount != null && totalMaleCount != null && totalMaleCount.longValue() > 0){
						 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / totalMaleCount));
						}
						if(casteFemaleCount != null && totalFemaleCount != null && totalFemaleCount.longValue() > 0){
						 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / totalFemaleCount));
						}
						if(casteTotalCount != null && totalCount != null && totalCount.longValue() > 0){
						 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / totalCount));
						}
						
						casteVO.setMalePerc(malePerc);
						casteVO.setFemalePerc(femalePerc);
						casteVO.setTotalPerc(castePercInTotal);
						
						Long registerdCadreCount = Long.valueOf(casteVO.getAvailableCasteCount().trim());
						casteVO.setAvailableCadrePerc(Double.valueOf(decimalFormat.format((registerdCadreCount * 100.00) / registeredCount)));
						//casteVO.setAvailableCadreCount(registerdCadreCount.toString());
					}
				}
				
				returnVO.setCasteWiseList(castewiseVOList);
			}
			if(ageRangeVOList != null && ageRangeVOList.size()>0)
			{
				for (CadreCommitteeRolesInfoVO casteVO : ageRangeVOList) 
				{
					if(casteVO != null)
					{
						Long casteTotalCount = casteVO.getTotalCount();
						Long casteMaleCount = casteVO.getMaleCount();
						Long casteFemaleCount = casteVO.getFemaleCount();

						Double malePerc = 0.00d;
						Double femalePerc = 0.00d;
						Double castePercInTotal = 0.00d;
						if(casteMaleCount != null && totalMaleCount != null && totalMaleCount.longValue() > 0){
						 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / totalMaleCount));
						}
						if(casteFemaleCount != null && totalFemaleCount != null && totalFemaleCount.longValue() > 0){
						 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / totalFemaleCount));
						}
						if(casteTotalCount != null && totalCount != null && totalCount.longValue() > 0){
						 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / totalCount));
						}
						
						casteVO.setMalePerc(malePerc);
						casteVO.setFemalePerc(femalePerc);
						casteVO.setTotalPerc(castePercInTotal);
						
						Long registerdCadreCount = Long.valueOf(casteVO.getAvaibleAgeWiseCount().trim());
						casteVO.setAvailableCadrePerc(Double.valueOf(decimalFormat.format((registerdCadreCount * 100.00) / registeredCount)));
						//casteVO.setAvailableCadreCount(registerdCadreCount.toString());
					}
				}
				returnVO.setAgeRangeWiseList(ageRangeVOList);
			}
			
		}
		catch(Exception e)
		{
			LOG.error("Exception Raised in getConstituencyOverviewCommitteeRoleAgeWiseDetailsByLocationType() ");
		}
		
		return returnVO;
	}

	public CadreCommitteeRolesInfoVO getCommitteeRoleAgeWiseDetailsByLocationType(String userAccessType,String locationValue,Long committeeTypeId,List<Long> positionIdsList,List<Long> casteCategoryIdsList,List<Long> casteCategoryGroupIdsList, 
		List<Long> casteIdsList,Long locationLevelId,Long userId, Long accessValue,String selectedRadio,List<Long> enrollIdsList,String startDateStr,String endDateStr)
	{
		CadreCommitteeRolesInfoVO returnVO = new CadreCommitteeRolesInfoVO();
		try {
			DecimalFormat decimalFormat = new DecimalFormat("##.##");
			List<Long> locationIdsList = new ArrayList<Long>();
			List<Long> wardIdsList  = null;
			List<Long> committeeTypeIdsList  = new ArrayList<Long>();
			Long actuallevelId = locationLevelId;
			String segritageQuery = null;
			List<Long> districtIds = new ArrayList<Long>();
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date startDate = null;
			Date endDate = null;
			if(startDateStr != null && endDateStr != null){
				startDate = sdf.parse(startDateStr);
				endDate = sdf.parse(endDateStr);
			}
			Long enrollmentId = 0L;
			if(enrollIdsList.contains(1l)){
				enrollmentId = 3L;
			}else if(enrollIdsList.contains(2l)){
				enrollmentId = 4L;
			}
			if(selectedRadio.equalsIgnoreCase("2")) //Mandal/Muncipality
			{
				segritageQuery = "MandalORTown";
			}
			else if(selectedRadio.equalsIgnoreCase("1"))
			{
				segritageQuery = "VillageORWard";
			}
			else if(selectedRadio.equalsIgnoreCase("3"))
			{
				segritageQuery = "district";
			}else if(selectedRadio.equalsIgnoreCase("4"))
			{
				segritageQuery = "state";
			}
			if(locationValue != null && Long.valueOf(locationValue).longValue() != 0L && Long.valueOf(locationLevelId).longValue() == 3L) //Constituency
			{
				return  getConstituencyOverviewCommitteeRoleAgeWiseDetailsByLocationType(userAccessType, locationValue, committeeTypeId, positionIdsList, 
						casteCategoryIdsList, casteCategoryGroupIdsList, casteIdsList, locationLevelId, userId, accessValue, selectedRadio,enrollIdsList);
			}
			
			if(committeeTypeId != null && Long.valueOf(committeeTypeId).longValue() != 0L)
				committeeTypeIdsList.add(committeeTypeId);
					
				if(locationValue != null && Long.valueOf(locationLevelId) == 2L) //District
				{			
					if(locationValue != null && Long.valueOf(locationValue).longValue() != 0L)
					{
						locationIdsList.add( Long.valueOf(locationValue));
						districtIds.add(Long.valueOf(locationValue).longValue());
					}
						
				}
				else if(locationValue != null && Long.valueOf(locationLevelId).longValue() == 4L) //Parliament
				{
					if(locationValue != null && Long.valueOf(locationValue).longValue() != 0L)
					{
						locationIdsList.add( Long.valueOf(locationValue));
					}
						
				}
			boolean isLevelIdChanged = false;
			
			if(locationIdsList == null || locationIdsList.size() ==0)
			{
				if(locationLevelId.longValue() == 0L || locationLevelId.longValue() == 1L) // on load or state Access
				{
					locationIdsList = new ArrayList<Long>();
					
					if(userAccessType.equalsIgnoreCase("STATE") || userAccessType.equalsIgnoreCase("ALL"))
					{
						locationLevelId =1L;			
						List<Object[]> accessDistrictsList = userDistrictAccessInfoDAO.findByUser(userId);
						if(accessDistrictsList != null && accessDistrictsList.size()>0)
						{
							isLevelIdChanged = true;
							locationLevelId =2L;
							for (Object[] districtId : accessDistrictsList) {
								locationIdsList.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
							}
						}	
						else
						{
							locationIdsList.add(accessValue);							
						}
					}
					else if(userAccessType.equalsIgnoreCase("DISTRICT"))
					{
						locationIdsList.add(accessValue);
						locationLevelId = 2L;
					}
					else if(userAccessType.equalsIgnoreCase("MP") )
					{						
						locationIdsList.add(accessValue);
						locationLevelId = 4L;
					}
					else if(userAccessType.equalsIgnoreCase("MLA"))
					{
						locationIdsList.add(accessValue);
						locationLevelId = 3L;
					}
					else if(userAccessType.equalsIgnoreCase("AP") || userAccessType.equalsIgnoreCase("TS"))
					{						
						locationLevelId = 2L;
						List<Object[]> accessDistrictsList = userDistrictAccessInfoDAO.findByUser(userId);
						if(accessDistrictsList != null && accessDistrictsList.size()>0)
						{
							locationIdsList = new ArrayList<Long>();
							for (Object[] districtId : accessDistrictsList) {
								locationIdsList.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
							}
						}	
					}
					else
					{
						locationLevelId = 2L;
						
						List<Object[]> accessDistrictsList = userDistrictAccessInfoDAO.findByUser(userId);
						if(accessDistrictsList != null && accessDistrictsList.size()>0)
						{
							locationIdsList = new ArrayList<Long>();
							for (Object[] districtId : accessDistrictsList) {
								locationIdsList.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
							}
						}	
					}
				}			
				else if(locationLevelId.longValue() == 2L) // District Access
				{
					List<Object[]> locationIds = userDistrictAccessInfoDAO.findByUser(userId);
					if(locationIds != null && locationIds.size()>0)
					{
						isLevelIdChanged = true;
						locationLevelId =2L;
						locationIdsList = new ArrayList<Long>();
						for (Object[] districtId : locationIds) {
							locationIdsList.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
						}
					}
					else if(userAccessType.equalsIgnoreCase("DISTRICT"))
					{
						locationIdsList = new ArrayList<Long>();
						locationIdsList.add(accessValue);
					}
					
					// get next level overview details
					
					Long descriptionLevelId = 0L;

					if(locationIdsList != null && locationIdsList.size()>0)
					{
						if(actuallevelId != 3)
						{
							if(isLevelIdChanged)
								descriptionLevelId = actuallevelId;
							else
								descriptionLevelId = actuallevelId + 1;
						}
						else{
							descriptionLevelId = 6L;
						}
					}
					else
					{
						descriptionLevelId = actuallevelId; 
					}
					
					List<Object[]> genderWiseCountList = tdpCommitteeMemberDAO.getCommitteeRolesGenderWiseDetailsByLocation(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,segritageQuery,descriptionLevelId,enrollIdsList,startDate,endDate);
					
					List<CadreCommitteeRolesInfoVO> nextLevelList = new ArrayList<CadreCommitteeRolesInfoVO>();
					Set<Long> locationIdsSet = new HashSet<Long>();
					List<Object[]> constituencyList = null;
					if(descriptionLevelId.longValue() == 2)
					{
						locationIdsSet.addAll(locationIdsList);
						constituencyList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationIdsSet,"District",null,"Registered",enrollmentId);
					}
					else if(descriptionLevelId.longValue() == 3)
					{
						locationIdsSet.addAll(locationIdsList);
						constituencyList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationIdsSet,"Constituency",null,"Registered",enrollmentId);
					}		
					
					Map<Long,Long> locationWiseRegisteredCount = new HashMap<Long, Long>();
					Long totalRegisteredCount = 0L;
					if(constituencyList != null && constituencyList.size()>0)
					{
						for (Object[] consituncy : constituencyList){
							Long locationId = consituncy[0] != null ? Long.valueOf(consituncy[0].toString().trim()):0L;
							Long registeredCadreCount = consituncy[1] != null ? Long.valueOf(consituncy[1].toString().trim()):0L;							
							locationWiseRegisteredCount.put(locationId, registeredCadreCount);
						}
					}
					Long locationWiseMaleCount = 0L;
					Long locationWiseFemaleCount = 0L;
					Long locationWiseTotalCount = 0L;
					if(genderWiseCountList != null && genderWiseCountList.size()>0)
					{
						Long totalGenderCount = 0L;				
						
						for (Object[] param : genderWiseCountList) {
							
							totalGenderCount = 0L;
							Long maleCount = 0L;
							Long femaleCount = 0L;
							
							boolean isAvailable =false;
							String genderType = param[0] != null ? param[0].toString():"";
							
							CadreCommitteeRolesInfoVO  cadreVO = getMatchedByLocationId(nextLevelList,param[2] != null ? Long.valueOf(param[2].toString().trim()):0L);

							if(cadreVO != null)
							{
								isAvailable = true;
								maleCount = cadreVO.getMaleCount();
								femaleCount = cadreVO.getFemaleCount();
							}
							else
							{
								cadreVO = new CadreCommitteeRolesInfoVO();
								cadreVO.setLocationId(param[2] != null ? param[2].toString().trim():"0");
								cadreVO.setLocationName(param[3] != null ?param[3].toString().trim():"0");
								String availableCadreCount = locationWiseRegisteredCount.get(Long.valueOf(cadreVO.getLocationId())) != null ? locationWiseRegisteredCount.get(Long.valueOf(cadreVO.getLocationId())).toString():"0";
								cadreVO.setAvailableCadreCount(availableCadreCount);
								totalRegisteredCount = totalRegisteredCount+Long.valueOf(availableCadreCount.trim());
							}
							
							if(genderType.equalsIgnoreCase("M"))
							{						
								Long tempCount = param[1] != null ? Long.valueOf(param[1].toString()):0L;
								maleCount = maleCount + tempCount;	
								locationWiseMaleCount = locationWiseMaleCount+tempCount;
							}
							else if(genderType.equalsIgnoreCase("F"))
							{
								Long tempCount = param[1] != null ? Long.valueOf(param[1].toString()):0L;
								femaleCount = femaleCount + tempCount;
								locationWiseFemaleCount = locationWiseFemaleCount+tempCount;
							}
							
							cadreVO.setMaleCount(maleCount);
							cadreVO.setFemaleCount(femaleCount);
							totalGenderCount = maleCount + femaleCount;
							cadreVO.setTotalCount(totalGenderCount);
							
							if(!isAvailable)
								nextLevelList.add(cadreVO);
						}
					}
					
					if(nextLevelList != null && nextLevelList.size()>0)
					{

						locationWiseTotalCount = locationWiseFemaleCount + locationWiseMaleCount;
						
						for (CadreCommitteeRolesInfoVO locationVO : nextLevelList) 
						{
							if(locationVO != null)
							{
								Long casteTotalCount = locationVO.getTotalCount();
								Long casteMaleCount = locationVO.getMaleCount();
								Long casteFemaleCount = locationVO.getFemaleCount();

								Double malePerc = 0.00d;
								Double femalePerc = 0.00d;
								Double castePercInTotal = 0.00d;
								 if(casteMaleCount != null && locationWiseMaleCount != null && locationWiseMaleCount.longValue() >0){
								      malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / locationWiseMaleCount));
								 }
								 if(casteFemaleCount != null && locationWiseFemaleCount != null && locationWiseFemaleCount.longValue() >0){
								      femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / locationWiseFemaleCount));
								 }
								 if(casteTotalCount != null && locationWiseTotalCount != null && locationWiseTotalCount.longValue() >0){
								      castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / locationWiseTotalCount));
								 }
								
								 locationVO.setMalePerc(malePerc);
								 locationVO.setFemalePerc(femalePerc);
								 locationVO.setTotalPerc(castePercInTotal);
								
								Long registerdCadreCount = Long.valueOf(locationVO.getAvailableCadreCount().trim());
								if(registerdCadreCount != null && registerdCadreCount.longValue()>0L && totalRegisteredCount != null && totalRegisteredCount.longValue()>0L )
									locationVO.setAvailableCadrePerc(Double.valueOf(decimalFormat.format((registerdCadreCount * 100.00) / totalRegisteredCount)));
								else
									locationVO.setAvailableCadrePerc(0.00d);
							}
						}
						
						returnVO.setLocationWiseList(nextLevelList);
					}
					
					returnVO.setLocationId(descriptionLevelId.toString());
					
					if(descriptionLevelId.longValue() == 2L)
						returnVO.setLocationName("District");
					else if(descriptionLevelId.longValue() == 3L)
						returnVO.setLocationName("Constituency");
					else if(descriptionLevelId.longValue() == 4L)
						returnVO.setLocationName("Parliament");
					else if(descriptionLevelId.longValue() == 5L)
						returnVO.setLocationName(IConstants.TEHSIL);
					else if(descriptionLevelId.longValue() == 6L)
						returnVO.setLocationName(IConstants.PANCHAYAT);
					
					return returnVO;

				}
				else if(locationLevelId.longValue() == 4L) // Parliament Access
				{
					locationIdsList = new ArrayList<Long>();
					List<Object[]> locationIds = userDistrictAccessInfoDAO.findByUser(userId);
					List<Long> districtIdsList = new ArrayList<Long>();
					if(locationIds != null && locationIds.size()>0)
					{
						for (Object[] districtId : locationIds) {
							districtIdsList.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
						}
						
						locationIds =  delimitationConstituencyDAO.getLatestConstituencyListByElectionTypeInDistricts(1L, districtIdsList);
						if(locationIds != null && locationIds.size()>0)
						{
							for (Object[] districtId : locationIds){
								locationIdsList.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
							}
						}
					}
					if(userAccessType.equalsIgnoreCase("DISTRICT"))
					{
						districtIdsList.add(accessValue);
						locationIds =  delimitationConstituencyDAO.getLatestConstituencyListByElectionTypeInDistricts(1L, districtIdsList);
						if(locationIds != null && locationIds.size()>0)
						{
							for (Object[] districtId : locationIds){
								locationIdsList.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
							}
						}
					}
					else if(userAccessType.equalsIgnoreCase("MP"))
					{
						locationIdsList.add(accessValue);
					}
					
					// get next level overview details
					
					Long descriptionLevelId = 0L;

					if(locationIdsList != null && locationIdsList.size()>0)
					{
						if(actuallevelId != 3)
						{
							descriptionLevelId = actuallevelId + 1;
						}
						else{
							descriptionLevelId = 6L;
						}
					}
					else
					{
						descriptionLevelId = actuallevelId; 
					}
					List<Object[]> genderWiseCountList = tdpCommitteeMemberDAO.getCommitteeRolesGenderWiseDetailsByLocation(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,segritageQuery,descriptionLevelId,enrollIdsList,startDate,endDate);
					
					List<CadreCommitteeRolesInfoVO> nextLevelList = new ArrayList<CadreCommitteeRolesInfoVO>();
					Set<Long> locationIdsSet = new HashSet<Long>();
					List<Object[]> constituencyList = null;
					if(descriptionLevelId.longValue() == 2)
					{
						locationIdsSet.addAll(locationIdsList);
						constituencyList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationIdsSet,"District",null,"Registered",enrollmentId);
					}
					if(descriptionLevelId.longValue() == 3)
					{
						locationIdsSet.addAll(locationIdsList);
						constituencyList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationIdsSet,"Constituency",null,"Registered",enrollmentId);
					}		
					
					Map<Long,Long> locationWiseRegisteredCount = new HashMap<Long, Long>();
					Long totalRegisteredCount = 0L;
					if(constituencyList != null && constituencyList.size()>0)
					{
						for (Object[] consituncy : constituencyList){
							Long locationId = consituncy[0] != null ? Long.valueOf(consituncy[0].toString().trim()):0L;
							Long registeredCadreCount = consituncy[1] != null ? Long.valueOf(consituncy[1].toString().trim()):0L;
							locationWiseRegisteredCount.put(locationId, registeredCadreCount);
						}
					}
					Long locationWiseMaleCount = 0L;
					Long locationWiseFemaleCount = 0L;
					Long locationWiseTotalCount = 0L;
					if(genderWiseCountList != null && genderWiseCountList.size()>0)
					{
						Long totalGenderCount = 0L;				
						
						for (Object[] param : genderWiseCountList) {
							
							totalGenderCount = 0L;
							Long maleCount = 0L;
							Long femaleCount = 0L;
							
							boolean isAvailable =false;
							String genderType = param[0] != null ? param[0].toString():"";
							
							CadreCommitteeRolesInfoVO  cadreVO = getMatchedByLocationId(nextLevelList,param[2] != null ? Long.valueOf(param[2].toString().trim()):0L);

							if(cadreVO != null)
							{
								
								
								isAvailable = true;
								maleCount = cadreVO.getMaleCount();
								femaleCount = cadreVO.getFemaleCount();
							}
							else
							{
								cadreVO = new CadreCommitteeRolesInfoVO();
								cadreVO.setLocationId(param[2] != null ? param[2].toString().trim():"0");
								cadreVO.setLocationName(param[3] != null ?param[3].toString().trim():"0");
								Long availableCadreCount = locationWiseRegisteredCount.get(Long.valueOf(cadreVO.getLocationId())) != null ? locationWiseRegisteredCount.get(Long.valueOf(cadreVO.getLocationId())):0L;
								cadreVO.setAvailableCadreCount(availableCadreCount.toString());
								totalRegisteredCount = totalRegisteredCount+availableCadreCount;
								
							}
							if(genderType.equalsIgnoreCase("M"))
							{						
								Long tempCount = param[1] != null ? Long.valueOf(param[1].toString()):0L;
								maleCount = maleCount + tempCount;	
								locationWiseMaleCount = locationWiseMaleCount+tempCount;
							}
							else if(genderType.equalsIgnoreCase("F"))
							{
								Long tempCount = param[1] != null ? Long.valueOf(param[1].toString()):0L;
								femaleCount = femaleCount + tempCount;
								locationWiseFemaleCount = locationWiseFemaleCount+tempCount;
							}
							cadreVO.setMaleCount(maleCount);
							cadreVO.setFemaleCount(femaleCount);
							totalGenderCount = maleCount + femaleCount;
							cadreVO.setTotalCount(totalGenderCount);
							
							if(!isAvailable)
								nextLevelList.add(cadreVO);
						}
						
						
					}
					
					if(nextLevelList != null && nextLevelList.size()>0)
					{
						locationWiseTotalCount = locationWiseFemaleCount + locationWiseMaleCount;
						
						for (CadreCommitteeRolesInfoVO locationVO : nextLevelList) 
						{
							if(locationVO != null)
							{
								Long casteTotalCount = locationVO.getTotalCount();
								Long casteMaleCount = locationVO.getMaleCount();
								Long casteFemaleCount = locationVO.getFemaleCount();

								Double malePerc = 0.00d;
								Double femalePerc = 0.00d;
								Double castePercInTotal = 0.00d;
								if(casteMaleCount != null && locationWiseMaleCount != null && locationWiseMaleCount.longValue() > 0){
								 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / locationWiseMaleCount));
								}
								if(casteFemaleCount != null && locationWiseFemaleCount != null && locationWiseFemaleCount.longValue() > 0){
								 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / locationWiseFemaleCount));
								}
								if(casteTotalCount != null && locationWiseTotalCount != null && locationWiseTotalCount.longValue() > 0){
								 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / locationWiseTotalCount));
								}
								
								 locationVO.setMalePerc(malePerc);
								 locationVO.setFemalePerc(femalePerc);
								 locationVO.setTotalPerc(castePercInTotal);
								
								Long registerdCadreCount = Long.valueOf(locationVO.getAvailableCadreCount().trim());
								locationVO.setAvailableCadrePerc(Double.valueOf(decimalFormat.format((registerdCadreCount * 100.00) / totalRegisteredCount)));
								
							
							}
						}
						
						returnVO.setLocationWiseList(nextLevelList);
					}
					
					returnVO.setLocationId(descriptionLevelId.toString());
					
					if(descriptionLevelId.longValue() == 2L)
						returnVO.setLocationName("District");
					else if(descriptionLevelId.longValue() == 3L)
						returnVO.setLocationName("Constituency");
					else if(descriptionLevelId.longValue() == 4L)
						returnVO.setLocationName("Parliament");
					else if(descriptionLevelId.longValue() == 5L)
						returnVO.setLocationName(IConstants.TEHSIL);
					else if(descriptionLevelId.longValue() == 6L)
						returnVO.setLocationName(IConstants.PANCHAYAT);
					
					return returnVO;
				}			
				else if(locationLevelId.longValue() == 3L) // Constituency
				{
					locationIdsList = new ArrayList<Long>();
					
						List<Object[]> locationIds = userDistrictAccessInfoDAO.findByUser(userId);
						List<Long> districtIdsList = new ArrayList<Long>();
						if(locationIds != null && locationIds.size()>0)
						{
							for (Object[] districtId : locationIds) {
								districtIdsList.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
							}
							
							locationIds =  constituencyDAO.getConstituencyListByDistrictIdsList(districtIdsList);
							if(locationIds != null && locationIds.size()>0)
							{
								for (Object[] districtId : locationIds){
									locationIdsList.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
								}
							}
						}
						else if(userAccessType.equalsIgnoreCase("DISTRICT"))
						{
							districtIdsList.add(accessValue);
							locationIds =  constituencyDAO.getConstituencyListByDistrictIdsList(districtIdsList);
							if(locationIds != null && locationIds.size()>0)
							{
								for (Object[] districtId : locationIds){
									locationIdsList.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
								}
							}
						}
						else if(userAccessType.equalsIgnoreCase("MP"))
						{
							List<Long> pcList  = new ArrayList<Long>();
							pcList.add(accessValue);						
							locationIdsList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(pcList);
						}
						else if(userAccessType.equalsIgnoreCase("MLA"))
						{
							locationIdsList.add(accessValue);
						}
						// get next level overview details
						
						Long descriptionLevelId = 0L;

						descriptionLevelId = actuallevelId; 

						List<Object[]> genderWiseCountList = tdpCommitteeMemberDAO.getCommitteeRolesGenderWiseDetailsByLocation(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,segritageQuery,descriptionLevelId,enrollIdsList,startDate,endDate);
						
						List<CadreCommitteeRolesInfoVO> nextLevelList = new ArrayList<CadreCommitteeRolesInfoVO>();
						Set<Long> locationIdsSet = new HashSet<Long>();
						List<Object[]> constituencyList = null;
						if(descriptionLevelId.longValue() == 2)
						{
							locationIdsSet.addAll(locationIdsList);
							constituencyList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationIdsSet,"District",null,"Registered",enrollmentId);
						}
						else if(descriptionLevelId.longValue() == 3)
						{
							locationIdsSet.addAll(locationIdsList);
							constituencyList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationIdsSet,"Constituency",null,"Registered",enrollmentId);
						}		
						
						Map<Long,Long> locationWiseRegisteredCount = new TreeMap<Long, Long>();
						Long totalRegisteredCount = 0L;
						if(constituencyList != null && constituencyList.size()>0)
						{
							for (Object[] consituncy : constituencyList){
								Long locationId = consituncy[0] != null ? Long.valueOf(consituncy[0].toString().trim()):0L;
								Long registeredCadreCount = consituncy[1] != null ? Long.valueOf(consituncy[1].toString().trim()):0L;								
								locationWiseRegisteredCount.put(locationId, registeredCadreCount);
							}
						}
						
						Long locationWiseMaleCount = 0L;
						Long locationWiseFemaleCount = 0L;
						Long locationWiseTotalCount = 0L;
						
						if(genderWiseCountList != null && genderWiseCountList.size()>0)
						{
							Long totalGenderCount = 0L;				
							
							for (Object[] param : genderWiseCountList) {
								
								totalGenderCount = 0L;
								Long maleCount = 0L;
								Long femaleCount = 0L;
								
								boolean isAvailable =false;
								String genderType = param[0] != null ? param[0].toString():"";
								
								CadreCommitteeRolesInfoVO  cadreVO = getMatchedByLocationId(nextLevelList,param[2] != null ? Long.valueOf(param[2].toString().trim()):0L);

								if(cadreVO != null)
								{
									isAvailable = true;
									maleCount = cadreVO.getMaleCount();
									femaleCount = cadreVO.getFemaleCount();
								}
								else
								{
									cadreVO = new CadreCommitteeRolesInfoVO();
									cadreVO.setLocationId(param[2] != null ? param[2].toString().trim():"0");
									cadreVO.setLocationName(param[3] != null ?param[3].toString().trim():"0");
									
									Long availableCadreCount = locationWiseRegisteredCount.get(Long.valueOf(cadreVO.getLocationId())) != null ? locationWiseRegisteredCount.get(Long.valueOf(cadreVO.getLocationId())):0L;
									cadreVO.setAvailableCadreCount(availableCadreCount.toString());
									totalRegisteredCount = totalRegisteredCount + availableCadreCount;
								}
								
								if(genderType.equalsIgnoreCase("M"))
								{						
									Long tempCount = param[1] != null ? Long.valueOf(param[1].toString()):0L;
									maleCount = maleCount + tempCount;	
									locationWiseMaleCount = locationWiseMaleCount+tempCount;
								}
								else if(genderType.equalsIgnoreCase("F"))
								{
									Long tempCount = param[1] != null ? Long.valueOf(param[1].toString()):0L;
									femaleCount = femaleCount + tempCount;
									locationWiseFemaleCount = locationWiseFemaleCount+tempCount;
								}
								
								
								cadreVO.setMaleCount(maleCount);
								cadreVO.setFemaleCount(femaleCount);
								totalGenderCount = maleCount + femaleCount;
								cadreVO.setTotalCount(totalGenderCount);
								
								if(!isAvailable)
									nextLevelList.add(cadreVO);
							}
						}
						
						if(nextLevelList != null && nextLevelList.size()>0)
						{
							locationWiseTotalCount = locationWiseFemaleCount + locationWiseMaleCount;
							
							for (CadreCommitteeRolesInfoVO locationVO : nextLevelList) 
							{
								if(locationVO != null)
								{
									Long casteTotalCount = locationVO.getTotalCount();
									Long casteMaleCount = locationVO.getMaleCount();
									Long casteFemaleCount = locationVO.getFemaleCount();

									Double malePerc = 0.00d;
									Double femalePerc = 0.00d;
									Double castePercInTotal = 0.00d;
									if(casteMaleCount != null && locationWiseMaleCount != null && locationWiseMaleCount.longValue() > 0){
									 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / locationWiseMaleCount));
									}
									if(casteFemaleCount != null && locationWiseFemaleCount != null && locationWiseFemaleCount.longValue() > 0){
									 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / locationWiseFemaleCount));
									}
									if(casteTotalCount != null && locationWiseTotalCount != null && locationWiseTotalCount.longValue() > 0){
									 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / locationWiseTotalCount));
									}
									
									 locationVO.setMalePerc(malePerc);
									 locationVO.setFemalePerc(femalePerc);
									 locationVO.setTotalPerc(castePercInTotal);
									
									Long registerdCadreCount = Long.valueOf(locationVO.getAvailableCadreCount().trim());
									locationVO.setAvailableCadrePerc(Double.valueOf(decimalFormat.format((registerdCadreCount * 100.00) / totalRegisteredCount)));
								}
							}
							
							returnVO.setLocationWiseList(nextLevelList);
						}
						
						returnVO.setLocationId(descriptionLevelId.toString());
						
						if(descriptionLevelId.longValue() == 2L)
							returnVO.setLocationName("District");
						else if(descriptionLevelId.longValue() == 3L)
							returnVO.setLocationName("Constituency");
						else if(descriptionLevelId.longValue() == 4L)
							returnVO.setLocationName("Parliament");
						else if(descriptionLevelId.longValue() == 5L)
							returnVO.setLocationName(IConstants.TEHSIL);
						else if(descriptionLevelId.longValue() == 6L)
							returnVO.setLocationName(IConstants.PANCHAYAT);
						
						return returnVO;
				}
			}
			
			
			List<Object[]> casteWiseCategoryList = tdpCommitteeMemberDAO.getCasteCategoryInfoForLocations(locationLevelId,locationIdsList,wardIdsList,userAccessType,segritageQuery,"casteCategory",enrollIdsList);
			Map<String , Long> casteCategoryCountMap = new HashMap<String, Long>();
			
			if(casteWiseCategoryList != null && casteWiseCategoryList.size()>0)
			{
				for (Object[] casteWiseCategory : casteWiseCategoryList) {
					Long categoryId = casteWiseCategory[1] != null ? Long.valueOf(casteWiseCategory[1].toString()):0L;
					Long count = casteWiseCategory[2] != null ? Long.valueOf(casteWiseCategory[2].toString()):0L;
					String categoryName  = categoryId != 0L ? (categoryId == 1L? "OC":(categoryId == 2L? "BC":(categoryId == 3L? "SC" : (categoryId == 4L? "ST":"")))):"";
					Long categoryCount = 0L;
					if(casteCategoryCountMap.get(categoryName) != null)
					{
						categoryCount = casteCategoryCountMap.get(categoryName);
					}
					categoryCount = categoryCount+count;
					casteCategoryCountMap.put(categoryName, categoryCount);
				}
			}
			
			List<Object[]> casteCategoryWiseCountList = tdpCommitteeMemberDAO.getCommitteeRoleCasteCategoryNameWiseDetailsByLocationType(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,segritageQuery,enrollIdsList,startDate,endDate);
			List<CadreCommitteeRolesInfoVO> casteCategoryVOList = new ArrayList<CadreCommitteeRolesInfoVO>();
			if(casteCategoryWiseCountList != null && casteCategoryWiseCountList.size()>0)
			{
				Long totalGenderCount = 0L;
				
				for (Object[] param : casteCategoryWiseCountList) 
				{
					Long maleCount = 0L;
					Long femaleCount = 0L;
					boolean isAvailable = false;
					CadreCommitteeRolesInfoVO cadreVO = getMatchedByCasteCategoryName(casteCategoryVOList,param[1] != null ? param[1].toString().trim():"");
					
					if(cadreVO != null)
					{
						isAvailable = true;
						maleCount = cadreVO.getMaleCount();
						femaleCount = cadreVO.getFemaleCount();
					}
					else
					{
						cadreVO = new CadreCommitteeRolesInfoVO();
						cadreVO.setCasteCategory(param[1] != null ? param[1].toString():"");
						cadreVO.setAvailableCasteCount(casteCategoryCountMap.get(cadreVO.getCasteCategory()) != null ? casteCategoryCountMap.get(cadreVO.getCasteCategory()).toString():"0");
					}
					
					String genderType = param[2] != null ? param[2].toString():"";
					
					if(genderType.equalsIgnoreCase("M"))
					{
						Long tempCount = param[3] != null ? Long.valueOf(param[3].toString()):0L;
						maleCount = maleCount+tempCount;
					}
					else if(genderType.equalsIgnoreCase("F"))
					{
						Long tempCount = param[3] != null ? Long.valueOf(param[3].toString()):0L;
						femaleCount = femaleCount+tempCount;
					}
					cadreVO.setMaleCount(maleCount);
					cadreVO.setFemaleCount(femaleCount);
					totalGenderCount = maleCount + femaleCount;
					cadreVO.setTotalCount(totalGenderCount);
					
					if(!isAvailable)
						casteCategoryVOList.add(cadreVO);
				}
			}
			
			List<Object[]> casteWiseList = tdpCommitteeMemberDAO.getCasteInfoForLocations(locationLevelId,locationIdsList,wardIdsList,userAccessType,segritageQuery,"caste",enrollIdsList);
			Map<Long , Long> casteCountMap = new HashMap<Long, Long>();
			List<Long> minorityCastes = new ArrayList<Long>();
			minorityCastes.add(459L);//459,301,292,430
			minorityCastes.add(301L);
			minorityCastes.add(292L);
			minorityCastes.add(430L);
			Long maleMinority =0L;
			Long femaleMinority = 0L;
			Long minorityAvailableCount  = 0L;
			
			if(casteWiseList != null && casteWiseList.size()>0)
			{
				for (Object[] caste : casteWiseList) {
					Long castestateId = caste[1] != null ? Long.valueOf(caste[1].toString()):0L;
					Long count = caste[2] != null ? Long.valueOf(caste[2].toString()):0L;
					Long categoryCount = 0L;
					if(casteCountMap.get(castestateId) != null)
					{
						categoryCount = casteCountMap.get(castestateId);
					}
					categoryCount = categoryCount+count;
					casteCountMap.put(castestateId, categoryCount);
					if(minorityCastes.contains(castestateId))
					{
						minorityAvailableCount = minorityAvailableCount+count;
					}	
				}
			}
			
			
			List<Object[]> casteWiseCountList = tdpCommitteeMemberDAO.getCommitteeRoleCasteNameWiseDetailsByLocationType(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,segritageQuery,enrollIdsList,startDate,endDate);
			Long totalCount = 0L;
			Long totalMaleCount = 0L;
			Long totalFemaleCount = 0L;
			List<CadreCommitteeRolesInfoVO> castewiseVOList = new ArrayList<CadreCommitteeRolesInfoVO>();
			
			if(casteWiseCountList != null && casteWiseCountList.size()>0)
			{
				
				Long totalGenderCount = 0L;
				for (Object[] param : casteWiseCountList) {					
					Long maleCount = 0L;
					Long femaleCount = 0L;	
					boolean isAvailable = false;
					CadreCommitteeRolesInfoVO cadreVO = getMatchedByCasteName(castewiseVOList,param[1] != null ? param[1].toString().trim():"");
					
					if(cadreVO != null)
					{
						isAvailable = true;
						maleCount = cadreVO.getMaleCount();
						femaleCount = cadreVO.getFemaleCount();
					}
					else
					{
						cadreVO = new CadreCommitteeRolesInfoVO();
						cadreVO.setCaste(param[1] != null ? param[1].toString():"");
						Long casteStateId = param[0] != null ? Long.valueOf(param[0].toString()):0L;
						Long  avalCasteCount = casteCountMap.get(casteStateId) != null ? casteCountMap.get(casteStateId) :0L;
						cadreVO.setAvailableCasteCount(avalCasteCount.toString());
					}
					
					String genderType = param[2] != null ? param[2].toString():"";
					
					if(genderType.equalsIgnoreCase("M"))
					{
						Long tempCount = param[3] != null ? Long.valueOf(param[3].toString()):0L;
						Long casteStateId = param[0] != null ? Long.valueOf(param[0].toString()):0L;
						totalMaleCount = totalMaleCount+tempCount;
						maleCount = maleCount+tempCount;
						if(minorityCastes.contains(casteStateId))
						{
							maleMinority = maleMinority+tempCount;
						}
						
					}
					else if(genderType.equalsIgnoreCase("F"))
					{
						Long tempCount = param[3] != null ? Long.valueOf(param[3].toString()):0L;
						Long casteStateId = param[0] != null ? Long.valueOf(param[0].toString()):0L;
						totalFemaleCount = totalFemaleCount+tempCount;
						femaleCount = femaleCount+tempCount;
						if(minorityCastes.contains(casteStateId))
						{
							femaleMinority = femaleMinority+tempCount;
						}
					}
					cadreVO.setMaleCount(maleCount);
					cadreVO.setFemaleCount(femaleCount);
					totalGenderCount = maleCount + femaleCount;
					cadreVO.setTotalCount(totalGenderCount);
					
					if(!isAvailable)
						castewiseVOList.add(cadreVO);
				}
			}
			
			if(maleMinority.longValue() != 0 || femaleMinority.longValue() != 0L)
			{
				CadreCommitteeRolesInfoVO minorityCadreVO  = new CadreCommitteeRolesInfoVO();
				minorityCadreVO.setCasteCategory("Minority");
				minorityCadreVO.setMaleCount(maleMinority);
				minorityCadreVO.setFemaleCount(femaleMinority);
				Long total = maleMinority + femaleMinority;
				minorityCadreVO.setTotalCount(total);
				minorityCadreVO.setAvailableCasteCount(minorityAvailableCount.toString());
				casteCategoryVOList.add(minorityCadreVO);
				
			}
			
			
			
			List<Object[]> voterAgeRangeDetails = voterAgeInfoDAO.getVoterAgesInfoListByconstituencyExceptYouths(districtIds,userAccessType,IConstants.VOTER_DATA_PUBLICATION_ID);
			
			Map<Long,Long> availableVotersByAgeRange = new HashMap<Long, Long>();
			Long avaibaleTotalAgeRangeCount = 0L;
			if(voterAgeRangeDetails != null && voterAgeRangeDetails.size()>0)
			{
				for (Object[] voterAgeRange : voterAgeRangeDetails)
				{
					//String ageRange = voterAgeRange[1] != null ? voterAgeRange[1].toString().trim():"";
					Long ageRangeId = voterAgeRange[0] != null ? Long.valueOf(voterAgeRange[0].toString().trim()):0L;
					Long votersCount = voterAgeRange[2] != null ? Long.valueOf(voterAgeRange[2].toString().trim()):0L;
					avaibaleTotalAgeRangeCount = avaibaleTotalAgeRangeCount+votersCount;
					availableVotersByAgeRange.put(ageRangeId, votersCount);
				}
			}
			
			
			List<Object[]> ageRangeList = tdpCommitteeMemberDAO.getCadreAgerangeInfoForLocations(locationLevelId,locationIdsList,wardIdsList,userAccessType,segritageQuery,enrollIdsList);
			Map<Long , Long> agerangeCountMap = new HashMap<Long, Long>();
			
			if(ageRangeList != null && ageRangeList.size()>0)
			{
				for (Object[] agerange : ageRangeList) {
					Long agerangeId = agerange[1] != null ? Long.valueOf(agerange[1].toString()):0L;
					Long count = agerange[2] != null ? Long.valueOf(agerange[2].toString()):0L;
					Long categoryCount = 0L;
					if(agerangeCountMap.get(agerangeId) != null)
					{
						categoryCount = agerangeCountMap.get(agerangeId);
					}
					categoryCount = categoryCount+count;
					
					agerangeCountMap.put(agerangeId, categoryCount);
				}
			}
			
			List<Object[]> cadreDetails = tdpCommitteeMemberDAO.getCommitteeRoleAgerangeWiseDetailsByLocationType(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,segritageQuery,enrollIdsList,startDate,endDate);
			List<CadreCommitteeRolesInfoVO> ageRangeVOList = new LinkedList<CadreCommitteeRolesInfoVO>();
			
			if(cadreDetails != null && cadreDetails.size()>0)			
			{				
				Long totalGenderCount = 0L;
				for (Object[] param : cadreDetails) {					
					Long maleCount = 0L;
					Long femaleCount = 0L;	
					boolean isAvailable = false;
					CadreCommitteeRolesInfoVO cadreVO = getMatchedByAgeRange(ageRangeVOList,param[1] != null ? param[1].toString().trim():"");
					
					if(cadreVO != null)
					{
						isAvailable = true;
						maleCount = cadreVO.getMaleCount();
						femaleCount = cadreVO.getFemaleCount();
					}
					else
					{
						cadreVO = new CadreCommitteeRolesInfoVO();
						cadreVO.setAgeRange(param[1] != null ? param[1].toString():"");
						Long agerangeID = param[0] != null ? Long.valueOf(param[0].toString()):0L;
						Long agerangecount = agerangeCountMap.get(agerangeID) != null ?  agerangeCountMap.get(agerangeID) :0L;
						cadreVO.setAvaibleAgeWiseCount(agerangecount.toString());
						
						Long availableVoterCount = availableVotersByAgeRange.get(agerangeID) != null ? availableVotersByAgeRange.get(agerangeID) :0L;
						cadreVO.setAvailableVoters(availableVoterCount);
						
						Double votersPerc = Double.valueOf(decimalFormat.format((availableVoterCount * 100.00) / avaibaleTotalAgeRangeCount));
						cadreVO.setAvailableVotersPerc(votersPerc);
					}
					
					String genderType = param[2] != null ? param[2].toString():"";
					
					if(genderType.equalsIgnoreCase("M"))
					{
						Long tempCount = param[3] != null ? Long.valueOf(param[3].toString()):0L;
						maleCount = maleCount+tempCount;
						
					}
					else if(genderType.equalsIgnoreCase("F"))
					{
						Long tempCount = param[3] != null ? Long.valueOf(param[3].toString()):0L;
						femaleCount = femaleCount+tempCount;
						
					}
					cadreVO.setMaleCount(maleCount);
					cadreVO.setFemaleCount(femaleCount);
					totalGenderCount = maleCount + femaleCount;
					cadreVO.setTotalCount(totalGenderCount);
					
					if(!isAvailable)
						ageRangeVOList.add(cadreVO);
				}
			
			}			
			List<CadreCommitteeRolesInfoVO> roleVOList = new ArrayList<CadreCommitteeRolesInfoVO>();
			Long registeredCount  = tdpCadreInfoDAO.getTdpCadreCountForLocations(userAccessType,locationIdsList,"Registered","District",enrollmentId);
			if(totalMaleCount.longValue() != 0L || totalFemaleCount.longValue() != 0L)
			{
				totalCount = totalMaleCount + totalFemaleCount;
				CadreCommitteeRolesInfoVO cadreVO = new CadreCommitteeRolesInfoVO();
				cadreVO.setMaleCount(totalMaleCount);
				cadreVO.setFemaleCount(totalFemaleCount);
				cadreVO.setTotalCount(totalCount);
				cadreVO.setAvailableCadreCount(registeredCount.toString());
				roleVOList.add(cadreVO);
			}
						
			if(roleVOList != null && roleVOList.size()>0)
			{
				returnVO.setCadreCommitteeRolesInfoVOList(roleVOList);
			}
			if(casteCategoryVOList != null && casteCategoryVOList.size()>0)
			{
				for (CadreCommitteeRolesInfoVO casteVO : casteCategoryVOList) 
				{
					if(casteVO != null)
					{
						Long casteTotalCount = casteVO.getTotalCount();
						Long casteMaleCount = casteVO.getMaleCount();
						Long casteFemaleCount = casteVO.getFemaleCount();

						Double malePerc = 0.00d;
						Double femalePerc = 0.00d;
						Double castePercInTotal = 0.00d;
						
						if(casteMaleCount != null && totalMaleCount != null && totalMaleCount.longValue() > 0){
						 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / totalMaleCount));
						}
						if(casteFemaleCount != null && totalFemaleCount != null && totalFemaleCount.longValue() > 0){
						 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / totalFemaleCount));
						}
						if(casteTotalCount != null && totalCount != null && totalCount.longValue() > 0){
						 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / totalCount));
						}
						
						casteVO.setMalePerc(malePerc);
						casteVO.setFemalePerc(femalePerc);
						casteVO.setTotalPerc(castePercInTotal);
						
						Long registerdCadreCount = Long.valueOf(casteVO.getAvailableCasteCount().trim());
						casteVO.setAvailableCadrePerc(Double.valueOf(decimalFormat.format((registerdCadreCount * 100.00) / registeredCount)));
						//casteVO.setAvailableCadreCount(registerdCadreCount.toString());
					}
				}
				returnVO.setCasteCategoryWiseList(casteCategoryVOList);
 			}
			
			if(castewiseVOList != null && castewiseVOList.size()>0)
			{
				for (CadreCommitteeRolesInfoVO casteVO : castewiseVOList) 
				{
					if(casteVO != null)
					{
						Long casteTotalCount = casteVO.getTotalCount();
						Long casteMaleCount = casteVO.getMaleCount();
						Long casteFemaleCount = casteVO.getFemaleCount();
						Double malePerc = 0.00d;
						Double femalePerc = 0.00d;
						Double castePercInTotal = 0.00d;
						if(casteMaleCount != null && totalMaleCount != null && totalMaleCount.longValue() > 0){
						 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / totalMaleCount));
						}
						if(casteFemaleCount != null && totalFemaleCount != null && totalFemaleCount.longValue() > 0){
						 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / totalFemaleCount));
						}
						if(casteTotalCount != null && totalCount != null && totalCount.longValue() > 0){
						 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / totalCount));
						}
						
						casteVO.setMalePerc(malePerc);
						casteVO.setFemalePerc(femalePerc);
						casteVO.setTotalPerc(castePercInTotal);
						
						Long registerdCadreCount = Long.valueOf(casteVO.getAvailableCasteCount());
						casteVO.setAvailableCadrePerc(Double.valueOf(decimalFormat.format((registerdCadreCount * 100.00) / registeredCount)));
						
					}
				}
				
				returnVO.setCasteWiseList(castewiseVOList);
			}
			if(ageRangeVOList != null && ageRangeVOList.size()>0)
			{
				for (CadreCommitteeRolesInfoVO casteVO : ageRangeVOList) 
				{
					if(casteVO != null)
					{
						Long casteTotalCount = casteVO.getTotalCount();
						Long casteMaleCount = casteVO.getMaleCount();
						Long casteFemaleCount = casteVO.getFemaleCount();

						Double malePerc = 0.00d;
						Double femalePerc = 0.00d;
						Double castePercInTotal = 0.00d;
						if(casteMaleCount != null && totalMaleCount != null && totalMaleCount.longValue() > 0){
						 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / totalMaleCount));
						}
						if(casteFemaleCount != null && totalFemaleCount != null && totalFemaleCount.longValue() > 0){
						 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / totalFemaleCount));
						}
						if(casteTotalCount != null && totalCount != null && totalCount.longValue() > 0){
						 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / totalCount));
						}
						
						casteVO.setMalePerc(malePerc);
						casteVO.setFemalePerc(femalePerc);
						casteVO.setTotalPerc(castePercInTotal);
						
						Long registerdCadreCount = Long.valueOf(casteVO.getAvaibleAgeWiseCount().trim());
						casteVO.setAvailableCadrePerc(Double.valueOf(decimalFormat.format((registerdCadreCount * 100.00) / registeredCount)));
						//casteVO.setAvailableCadreCount(registerdCadreCount.toString());
					}
				}
				returnVO.setAgeRangeWiseList(ageRangeVOList);
			}
			
			// get next level overview details
			
			Long descriptionLevelId = 0L;

			if(locationIdsList != null && locationIdsList.size()>0)
			{
				if(actuallevelId != 3)
				{
					descriptionLevelId = actuallevelId + 1;
				}
				else{
					descriptionLevelId = 6L;
				}
			}
			else if(actuallevelId.longValue() == 1L)
			{
				descriptionLevelId = actuallevelId+1; 
			}
			else if(actuallevelId.longValue() == 1L)
			{
				descriptionLevelId = actuallevelId; 
			}
			List<Object[]> genderWiseCountList = tdpCommitteeMemberDAO.getCommitteeRolesGenderWiseDetailsByLocation(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,segritageQuery,descriptionLevelId,enrollIdsList,startDate,endDate);
			List<Long> locationIdsForTotalTdpCadreCount = new ArrayList<Long>();
			
			if(genderWiseCountList != null && genderWiseCountList.size()>0)
			{
				for (Object[] location : genderWiseCountList) {
					Long areaId = location[2] != null ? Long.valueOf(location[2].toString().trim()):0L;
					locationIdsForTotalTdpCadreCount.add(areaId);
				}
			}
			List<CadreCommitteeRolesInfoVO> nextLevelList = new ArrayList<CadreCommitteeRolesInfoVO>();
			
			Set<Long> locationIdsSet = new HashSet<Long>();
			List<Object[]> constituencyList = null;
			if(actuallevelId.longValue() == 2)
			{
				locationIdsSet.addAll(locationIdsForTotalTdpCadreCount);
				constituencyList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationIdsSet,"Constituency",null,"Registered",enrollmentId);
			}
			if(actuallevelId.longValue() == 1)
			{
				locationIdsSet.addAll(locationIdsForTotalTdpCadreCount);
				constituencyList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationIdsSet,"District",null,"Registered",enrollmentId);
			}		
			
			Map<Long,Long> locationWiseRegisteredCount = new HashMap<Long, Long>();
			if(constituencyList != null && constituencyList.size()>0)
			{
				for (Object[] consituncy : constituencyList){
					Long locationId = consituncy[0] != null ? Long.valueOf(consituncy[0].toString().trim()):0L;
					Long registeredCadreCount = consituncy[1] != null ? Long.valueOf(consituncy[1].toString().trim()):0L;
					locationWiseRegisteredCount.put(locationId, registeredCadreCount);
				}
			}
			
			if(genderWiseCountList != null && genderWiseCountList.size()>0)
			{
				Long totalGenderCount = 0L;				
				
				for (Object[] param : genderWiseCountList) {
					
					totalGenderCount = 0L;
					Long maleCount = 0L;
					Long femaleCount = 0L;
					
					boolean isAvailable =false;
					String genderType = param[0] != null ? param[0].toString():"";
					
					CadreCommitteeRolesInfoVO  cadreVO = getMatchedByLocationId(nextLevelList,param[2] != null ? Long.valueOf(param[2].toString().trim()):0L);

					if(cadreVO != null)
					{
						
						
						isAvailable = true;
						maleCount = cadreVO.getMaleCount();
						femaleCount = cadreVO.getFemaleCount();						
					}
					else
					{
						cadreVO = new CadreCommitteeRolesInfoVO();
						cadreVO.setLocationId(param[2] != null ? param[2].toString().trim():"0");
						cadreVO.setLocationName(param[3] != null ?param[3].toString().trim():"0");
						cadreVO.setAvailableCadreCount(locationWiseRegisteredCount.get(Long.valueOf(cadreVO.getLocationId())) != null ? locationWiseRegisteredCount.get(Long.valueOf(cadreVO.getLocationId())).toString():"0");
					}
					
					if(genderType.equalsIgnoreCase("M"))
					{						
						Long tempCount = param[1] != null ? Long.valueOf(param[1].toString()):0L;
						maleCount = maleCount + tempCount;						
					}
					else if(genderType.equalsIgnoreCase("F"))
					{
						Long tempCount = param[1] != null ? Long.valueOf(param[1].toString()):0L;
						femaleCount = femaleCount + tempCount;
					}
					
					cadreVO.setMaleCount(maleCount);
					cadreVO.setFemaleCount(femaleCount);
					totalGenderCount = maleCount + femaleCount;
					cadreVO.setTotalCount(totalGenderCount);
					
					if(!isAvailable)
						nextLevelList.add(cadreVO);
				}
			}
			
			if(nextLevelList != null && nextLevelList.size()>0)
			{
				for (CadreCommitteeRolesInfoVO locationVO : nextLevelList) 
				{
					if(locationVO != null)
					{
						Long casteTotalCount = locationVO.getTotalCount();
						Long casteMaleCount = locationVO.getMaleCount();
						Long casteFemaleCount = locationVO.getFemaleCount();

						Double malePerc = 0.00d;
						Double femalePerc = 0.00d;
						Double castePercInTotal = 0.00d;
						if(casteMaleCount != null && totalMaleCount != null && totalMaleCount.longValue() > 0){
						 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / totalMaleCount));
						}
						if(casteFemaleCount != null && totalFemaleCount != null && totalFemaleCount.longValue() > 0){
						 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / totalFemaleCount));
						}
						if(casteTotalCount != null && totalCount != null && totalCount.longValue() > 0){
						 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / totalCount));
						}
						
						 locationVO.setMalePerc(malePerc);
						 locationVO.setFemalePerc(femalePerc);
						 locationVO.setTotalPerc(castePercInTotal);
						
						Long registerdCadreCount = Long.valueOf(locationVO.getAvailableCadreCount().trim());
						locationVO.setAvailableCadrePerc(Double.valueOf(decimalFormat.format((registerdCadreCount * 100.00) / registeredCount)));
					}
				}
				
				returnVO.setLocationWiseList(nextLevelList);
			}
			
			returnVO.setLocationId(descriptionLevelId.toString());
			
			if(descriptionLevelId.longValue() == 2L)
				returnVO.setLocationName("District");
			else if(descriptionLevelId.longValue() == 3L)
				returnVO.setLocationName("Constituency");
			else if(descriptionLevelId.longValue() == 4L)
				returnVO.setLocationName("Parliament");
			else if(descriptionLevelId.longValue() == 5L)
				returnVO.setLocationName(IConstants.TEHSIL);
			else if(descriptionLevelId.longValue() == 6L)
				returnVO.setLocationName(IConstants.PANCHAYAT);
				
		} catch (Exception e) {
			LOG.error("Exception Raised in getCommitteeRoleAgeWiseDetailsByLocationType() ",e);
		}
		
		return returnVO;
	}
	private CadreCommitteeRolesInfoVO getMatchedByCasteCategoryName(List<CadreCommitteeRolesInfoVO> list,String casteCategoryName)
	{
		CadreCommitteeRolesInfoVO returnVO = null;
		try {
			
			if(list != null && list.size()>0)
			{
				for (CadreCommitteeRolesInfoVO cadreCommitteeRolesInfoVO : list) 
				{
					if(cadreCommitteeRolesInfoVO.getCasteCategory().trim().equalsIgnoreCase(casteCategoryName))
					{
						return cadreCommitteeRolesInfoVO;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Raised in getMatchedByName() ");
		}
		return returnVO;
	}
	
	private CadreCommitteeRolesInfoVO getMatchedByCasteName(List<CadreCommitteeRolesInfoVO> list,String casteName)
	{
		CadreCommitteeRolesInfoVO returnVO = null;
		try {
			
			if(list != null && list.size()>0)
			{
				for (CadreCommitteeRolesInfoVO cadreCommitteeRolesInfoVO : list) 
				{
					if(cadreCommitteeRolesInfoVO.getCaste().trim().equalsIgnoreCase(casteName))
					{
						return cadreCommitteeRolesInfoVO;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Raised in getMatchedByCasteName() ");
		}
		return returnVO;
	}
	
	
	private CadreCommitteeRolesInfoVO getMatchedByAgeRange(List<CadreCommitteeRolesInfoVO> list,String ageRangeStr)
	{
		CadreCommitteeRolesInfoVO returnVO = null;
		try {
			
			if(list != null && list.size()>0)
			{
				for (CadreCommitteeRolesInfoVO cadreCommitteeRolesInfoVO : list) 
				{
					if(cadreCommitteeRolesInfoVO.getAgeRange().trim().equalsIgnoreCase(ageRangeStr))
					{
						return cadreCommitteeRolesInfoVO;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Raised in getMatchedByCasteName() ");
		}
		return returnVO;
	}
	
	private CadreCommitteeRolesInfoVO getMatchedByLocationId(List<CadreCommitteeRolesInfoVO> list,Long locationId)
	{
		CadreCommitteeRolesInfoVO returnVO = null;
		try {
			
			if(list != null && list.size()>0)
			{
				for (CadreCommitteeRolesInfoVO cadreCommitteeRolesInfoVO : list) 
				{
					if(Long.valueOf(cadreCommitteeRolesInfoVO.getLocationId()).longValue() == locationId)
					{
						return cadreCommitteeRolesInfoVO;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Raised in getMatchedByCasteName() ");
		}
		return returnVO;
	}
	
	public String getDistrictName(Long  districtId){
		String name="";
		try {
		   name = districtDAO.getDistrictNameById(districtId).toString();
		} catch (Exception e) {
			LOG.error("Exception Raised in getDistrictName() ",e);
		}
		return name;
	}
	public List<BasicVO> getAllCommittees()
	{
	List<BasicVO> basicCmmty = new ArrayList<BasicVO>();
		try{
	List<Object[]> basicCommitteesRslt = tdpBasicCommitteeDAO.getBasicCommittees();
	
	if(basicCommitteesRslt!=null && basicCommitteesRslt.size()>0){
		for(Object[] obj:basicCommitteesRslt){
			BasicVO vo = new BasicVO();
			vo.setId((Long)obj[0]);
			vo.setName(obj[1].toString());
			basicCmmty.add(vo);
			}
		}
	}
		catch(Exception e)
		{
			LOG.error(e);
		}
		return basicCmmty;
	}
	public List<BasicVO> getCommitteeRoles()
	{
	List<BasicVO> basicCmmty = new ArrayList<BasicVO>();
		try{
		List<Object[]> roles = tdpRolesDAO.getRoles();
	
		if(roles!=null && roles.size()>0){
		for(Object[] obj:roles){
			BasicVO vo = new BasicVO();
			vo.setId((Long)obj[0]);
			vo.setName(obj[1].toString());
			basicCmmty.add(vo);
			}
		}
	}
		catch(Exception e)
		{
			LOG.error(e);
		}
		return basicCmmty;
	}
	
	
	
	public List<CommitteeSummaryVO> getConstituencyWiseCommittesSummaryForMandal(String state,String startDate, String endDate,Long userId, String accessType,Long accessValue,String mandalCheck,String villageCheck,List<Long> committeeEnrollmentIdsLst){  
		LOG.debug("Entered Into getConstituencyWiseCommittesSummaryForDistrict");
		List<CommitteeSummaryVO> mandalList = new ArrayList<CommitteeSummaryVO>();
		try{
			Long stateTypeId = 1l;
			String accessState = "ALL";
			List<Object[]> constituencysList = null;
			List<Long> districtIds = new ArrayList<Long>();
			List<Long> constiIds = new ArrayList<Long>();
			List<Long> constiIds1 = new ArrayList<Long>();
			List<Long> mandalIds = new ArrayList<Long>();
			List<Long> localBodyIds = new ArrayList<Long>();
			constiIds.add(accessValue);
			constiIds1.add(accessValue);
	
			Map<Long,List<Long>> mandalIdsMap = new HashMap<Long,List<Long>>();//Map<id,List<1constituencyId>>
			Map<Long,List<Long>> localBodiesMap = new HashMap<Long,List<Long>>();//Map<id,List<1constituencyId>>
			List<Long> divisionLclIds = new ArrayList<Long>();//Map<id,List<1constituencyId>>
			Map<Long,List<Long>> divisionIdsMap = new HashMap<Long,List<Long>>();//Map<id,List<1constituencyId>>
			getLocationsInfo(constiIds, divisionLclIds, localBodiesMap, divisionIdsMap, mandalIdsMap);	
			
			if(mandalIdsMap.size() > 0){
				  mandalIds = new ArrayList<Long>(mandalIdsMap.keySet());
				 
				 for(Long mandalId : mandalIds)
				 {
					 CommitteeSummaryVO mandalVo = new CommitteeSummaryVO();
					 String mandal = "2"+mandalId;
					 mandalVo.setConstiId(new Long(mandal.toString()));
					 mandalVo.setName(getLocationName(5l,mandalId));
					 mandalVo.setConstiNo(2l);//mandal
					 mandalList.add(mandalVo);
					
				 }
			}
			
			 if(localBodiesMap.size() > 0){
				 localBodyIds = new ArrayList<Long>(localBodiesMap.keySet());
				 for(Long localbody : localBodyIds)
				 {
					 CommitteeSummaryVO mandalVo = new CommitteeSummaryVO();
					 String localEle = "1"+localbody;
					 mandalVo.setConstiId(new Long(localEle.toString()));
					 mandalVo.setName(getLocationName(7l,localbody));
					 mandalVo.setConstiNo(1l);//localbody
					 mandalList.add(mandalVo);
				 }
			 }
			 if(divisionIdsMap.size() > 0){
				 List<Long> levelValues = new ArrayList<Long>(divisionIdsMap.keySet());
				 for(Long division : levelValues)
				 {
					 CommitteeSummaryVO mandalVo = new CommitteeSummaryVO();
					 String localEle = "3"+division;
					 mandalVo.setConstiId(new Long(localEle.toString()));
					 mandalVo.setName(getLocationName(9l,division));
					 mandalVo.setConstiNo(3l);//mandal
					 mandalList.add(mandalVo);
				 }
			 }
	//constiCountForMandalTownDivisions(constiIds);
	
	SimpleDateFormat format =  new SimpleDateFormat("MM/dd/yyyy");
	
	Date stDate = (Date)format.parse(startDate);
	Date edDate = (Date)format.parse(endDate);
	
	List<Long> mandalMunciDivisionIds = new ArrayList<Long>();
	mandalMunciDivisionIds.add(5l);
	mandalMunciDivisionIds.add(7l);
	mandalMunciDivisionIds.add(9l);
	 Map<Long,Long> mainMembersCountMap = new HashMap<Long,Long>();
	 Map<Long,Long> startedCommitteesCountMap = new HashMap<Long,Long>();
	 Map<Long,Long> completedCommitteesCountMap = new HashMap<Long,Long>();
	 Map<Long,Long> startedCommitteesAffCountMap = new HashMap<Long,Long>();
	 Map<Long,Long> completedCommitteesAffCountMap = new HashMap<Long,Long>();
	 Map<Long,Long> mainCommitteesCountMap = new HashMap<Long,Long>();
	/*List<Object[]> memResLst = tdpCommitteeMemberDAO.membersCountConstituencyWise(mandalMunciDivisionIds, stDate, edDate, constiIds);
	pushResultConstituencyWiseMemsCount("munci", memResLst, constiLst);*/
	
	if(mandalCheck.equalsIgnoreCase("true"))
		constiCountForMandalTownDivisions1(constiIds, stDate, edDate, mainMembersCountMap, startedCommitteesCountMap, completedCommitteesCountMap, startedCommitteesAffCountMap, completedCommitteesAffCountMap, mainCommitteesCountMap,mandalList,committeeEnrollmentIdsLst);
	if(villageCheck.equalsIgnoreCase("true"))
	{
		
		
	List<Long> villageIds = new ArrayList<Long>();
	List<Long> wardIds = new ArrayList<Long>();
	villageIds.add(6l);
	wardIds.add(8l);
	
	
	List<Object[]> memResLstVill = tdpCommitteeMemberDAO.membersCountMandalWise(villageIds, stDate, edDate, constiIds,"mandal");
	List<Object[]> ttlV = tdpCommitteeDAO.getCommitteesCountForMandalByConstituencyIdAndLevel(constiIds, villageIds,"mandal");
	pushResultConstituencyWiseMemsCount1("village", memResLstVill, mandalList,"mandal");
	pushTotalCountsForConstituency1("village", ttlV, mandalList,"mandal");
	List<Object[]> stResLstVill = tdpCommitteeDAO.committeesCountForMandalByConstituency(villageIds, stDate, edDate, "started", constiIds,"mandal"); 
	List<Object[]> endResLstVill = tdpCommitteeDAO.committeesCountForMandalByConstituency(villageIds, stDate, edDate, "completed", constiIds,"mandal");
	pushResultConstiWise1("village", stResLstVill, mandalList, "start","mandal");
	pushResultConstiWise1("village", endResLstVill, mandalList, "completed","mandal");
	
	List<Object[]> memResLstVill1 = tdpCommitteeMemberDAO.membersCountMandalWise(wardIds, stDate, edDate, constiIds,"muncipality");
	List<Object[]> ttlV1 = tdpCommitteeDAO.getCommitteesCountForMandalByConstituencyIdAndLevel(constiIds, wardIds,"muncipality");
		pushResultConstituencyWiseMemsCount1("village", memResLstVill1, mandalList,"muncipality");
		pushTotalCountsForConstituency1("village", ttlV1, mandalList,"muncipality");
		List<Object[]> stResLstVill1 = tdpCommitteeDAO.committeesCountForMandalByConstituency(wardIds, stDate, edDate, "started", constiIds,"muncipality"); 
		List<Object[]> endResLstVill1 = tdpCommitteeDAO.committeesCountForMandalByConstituency(wardIds, stDate, edDate, "completed", constiIds,"muncipality");
		 
		 
		pushResultConstiWise1("village", stResLstVill1, mandalList, "start","muncipality");
		pushResultConstiWise1("village", endResLstVill1, mandalList, "completed","muncipality");
	}
	
	if(mandalList!=null && mandalList.size()>0){
		for(CommitteeSummaryVO temp:mandalList){
			if(mandalCheck.equalsIgnoreCase("true")){
			if(temp.getTownMandalDivisionVO()==null){
				temp.setTownMandalDivisionVO(new CommitteeSummaryVO());
			}
			if(temp.getTownMandalDivisionVO()!=null){
				
				Long constiId = temp.getConstiId();
				
				Long membersCount = mainMembersCountMap.get(constiId);
				if(membersCount==null){membersCount=0l;}
				
				Long mainStarted = startedCommitteesCountMap.get(constiId);
				if(mainStarted==null){mainStarted=0l;}
				
				Long mainCompleted = completedCommitteesCountMap.get(constiId);
				if(mainCompleted==null){mainCompleted=0l;}
				
				Long afflStarted = startedCommitteesAffCountMap.get(constiId);
				if(afflStarted==null){afflStarted=0l;}
				
				Long afflCompleted = completedCommitteesAffCountMap.get(constiId);
				if(afflCompleted==null){afflCompleted=0l;}
				
				Long totalCommittees = mainCommitteesCountMap.get(constiId);
				if(totalCommittees==null){totalCommittees=0l;}
				
				temp.getTownMandalDivisionVO().setMainStarted(mainStarted);
				temp.getTownMandalDivisionVO().setMainCompleted(mainCompleted);
				temp.getTownMandalDivisionVO().setAfflStarted(afflStarted);
				temp.getTownMandalDivisionVO().setAfflCompleted(afflCompleted);
				temp.getTownMandalDivisionVO().setTotalCommittees(totalCommittees);
				temp.getTownMandalDivisionVO().setMembersCount(membersCount);
				
				Long strt = temp.getTownMandalDivisionVO().getMainStarted();
				Long cmpl = temp.getTownMandalDivisionVO().getMainCompleted();
				
				if(strt==null){strt = 0l;}
				/*if(cmpl==null){cmpl = 0l;}*/
				
				Long total = temp.getTownMandalDivisionVO().getTotalCommittees();
				if(total==null){total = 0l;}
				if(total!=0){
					//temp.getTownMandalDivisionVO().setTotalCommittees(total);
					String percentage = (new BigDecimal(strt*(100.0)/total)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					temp.getTownMandalDivisionVO().setStartPerc(percentage);
				}else{
					temp.getTownMandalDivisionVO().setStartPerc("0.0");
				}
			}
			}
			if(villageCheck.equalsIgnoreCase("true")){
			if(temp.getVillageWardVO()!=null){
				Long strtv = temp.getVillageWardVO().getMainStarted();
				Long cmplv = temp.getVillageWardVO().getMainCompleted();
				Long totalv = temp.getVillageWardVO().getTotalCommittees();
				if(totalv==null){
					totalv = 0l;
				}
				
				if(strtv==null){strtv = 0l;}
				/*if(cmplv==null){cmplv = 0l;}
				
				Long totalv = strtv + cmplv;*/
				
				if(totalv!=0){
					temp.getVillageWardVO().setTotalCommittees(totalv);
					String percentage = (new BigDecimal(strtv*(100.0)/totalv)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					temp.getVillageWardVO().setStartPerc(percentage);
				}else{
					temp.getVillageWardVO().setStartPerc("0.0");
				}
			}
			}
		}
	}
	
	Collections.sort(mandalList, new Comparator<CommitteeSummaryVO>() {
		public int compare(CommitteeSummaryVO o1, CommitteeSummaryVO o2) {
			return o1.getName().compareToIgnoreCase(o2.getName());
		}
	});
	
	if(mandalList != null && mandalList.size()>0)
	{
		mandalList.get(0).setAccessState(accessState);
	}
	
	List<CommitteeSummaryVO> mandals = new ArrayList<CommitteeSummaryVO>();
	List<CommitteeSummaryVO> localBodies = new ArrayList<CommitteeSummaryVO>();
	if(mandalList != null && mandalList.size() > 0)
	{
		for(CommitteeSummaryVO vo : mandalList)
		{
			if(vo.getConstiId().toString().substring(0,1).trim().equalsIgnoreCase("2"))
			{
				
				CommitteeSummaryVO tmpVO = new CommitteeSummaryVO();
				tmpVO.setConstiId(new Long(vo.getConstiId().toString().substring(01)));
				tmpVO.setLocationId(tmpVO.getConstiId());
				tmpVO.setName(vo.getName());
				mandals.add(tmpVO);
			}
			else if(vo.getConstiId().toString().substring(0,1).trim().equalsIgnoreCase("1"))
			{
				CommitteeSummaryVO tmpVO = new CommitteeSummaryVO();
				tmpVO.setConstiId(new Long(vo.getConstiId().toString().substring(01)));
				tmpVO.setLocationId(tmpVO.getConstiId());
				tmpVO.setName(vo.getName());
				localBodies.add(tmpVO);	
			}
		}
	}
	if(villageCheck.equalsIgnoreCase("true") && userId.longValue() == 1){
		Map<Long,List<CommitteeSummaryVO>> mandalMap = new HashMap<Long, List<CommitteeSummaryVO>>();
		Map<Long,List<CommitteeSummaryVO>> wardMap = new HashMap<Long, List<CommitteeSummaryVO>>();
		List<Long> panchIds = new ArrayList<Long>();
		List<Long> wardIds = new ArrayList<Long>();
		List<CommitteeSummaryVO> panchList = null;
		List<CommitteeSummaryVO> allPanchayats = new ArrayList<CommitteeSummaryVO>();
		List<CommitteeSummaryVO> allWardsList = new ArrayList<CommitteeSummaryVO>();
		List<CommitteeSummaryVO> wardsList = null;
		 if(mandalIds.size() > 0){
	        	//0panchayatId,1panchayatName,2tehsilName
	        	List<Object[]> panchayatsList = panchayatDAO.getAllPanchayatsWithTehsilIdsInMandals(mandalIds);
	        	for(Object[] panchayat:panchayatsList){
	        		CommitteeSummaryVO vo = new CommitteeSummaryVO();
		        	vo.setLocationId(Long.valueOf((Long)panchayat[0]));
		        	vo.setLocationName(panchayat[1].toString()+"("+panchayat[2].toString()+")");
		        	
		        	panchList = mandalMap.get(Long.valueOf(panchayat[3].toString()));
		        	if(panchList==null){
		        		panchList = new ArrayList<CommitteeSummaryVO>();
		        	}
		        	panchList.add(vo);
		        	allPanchayats.add(vo);
		        	panchIds.add(Long.valueOf((Long)panchayat[0]));
		        	mandalMap.put(Long.valueOf(panchayat[3].toString()), panchList);
		        	
	        	}
	        	pushPanchayatsAndWards(mandalMap, mandals);
	        }
	        if(localBodyIds.size() > 0){
	        	//0wardId,1pwardName,2localBdyName
	        	List<Object[]> localBodyList = constituencyDAO.getWardsAndLEBIdsInLocalElectionBody(localBodyIds);
	        	for(Object[] localBody:localBodyList){
	        		CommitteeSummaryVO vo = new CommitteeSummaryVO();
		        	vo.setLocationId(Long.valueOf((Long)localBody[0]));
		        	vo.setLocationName(localBody[1].toString()+"("+localBody[2].toString()+")");
		        	
		        	wardsList = wardMap.get(Long.valueOf(localBody[3].toString()));
		        	if(wardsList==null){
		        		wardsList = new ArrayList<CommitteeSummaryVO>();
		        	}
		        	wardIds.add(Long.valueOf((Long)localBody[0]));
		        	wardsList.add(vo);
		        	allWardsList.add(vo);
		        	wardMap.put(Long.valueOf(localBody[3].toString()), wardsList);
		        	
	        	}
	        	pushPanchayatsAndWards(wardMap, localBodies);
	        	
	        }
	
		
		getAllIvrDetailsForCampaind(mandals,3l,"mandal");
		getAllIvrDetailsForCampaind(localBodies,4l,"ward");
	}/*if(villageCheck.equalsIgnoreCase("true") && userId.longValue() == 1){
		
		getAllIvrDetailsForCampaind(constiLst,2l,"constituency");
	}*/
	
		
	if(mandalList != null && mandalList.size()>0)
	{
		Long totalIVRCount = 0L;
		for (CommitteeSummaryVO summaryVO : mandalList) 
		{
			try{
			CadreIVRVO cadreIVRVO = new CadreIVRVO();
			List<IvrOptionsVO> optionsList = setOptionsList(mandals.get(0).getCadreIVRVO().getOptionsList());
			cadreIVRVO.setOptionsList(optionsList);
			summaryVO.setCadreIVRVO(cadreIVRVO);
			
			CommitteeSummaryVO ivrVO = getMatchedVOByConstId(mandals,summaryVO.getName().trim()); 
			
			if(ivrVO != null)
			{
				for(CommitteeSummaryVO vo : ivrVO.getLocationsList()) // mandal panchayats List
				{	
					for(IvrOptionsVO vo1 : summaryVO.getCadreIVRVO().getOptionsList()) // ivr details list
					{
						IvrOptionsVO returnVo = getMatchedIvrOption(vo.getCadreIVRVO().getOptionsList(),vo1.getId()); // mandal Options List
						if(returnVo != null)
						{
								vo1.setCount(returnVo.getCount() + vo1.getCount()); // mandal Option VO
								summaryVO.getCadreIVRVO().setTotal(summaryVO.getCadreIVRVO().getTotal() + returnVo.getCount());
							
						}
					}
					
				}
				
			}
			else
			{
				
				
				ivrVO = getMatchedVOByConstId(localBodies,summaryVO.getName().trim());
				if(ivrVO != null)
				{
					for(CommitteeSummaryVO vo : ivrVO.getLocationsList()) // localbody wards List
					{	
						for(IvrOptionsVO vo1 : summaryVO.getCadreIVRVO().getOptionsList()) // ivr details list
						{
							IvrOptionsVO returnVo = getMatchedIvrOption(vo.getCadreIVRVO().getOptionsList(),vo1.getId()); // ward options List
							if(returnVo != null)
							{
									vo1.setCount(returnVo.getCount() + vo1.getCount()); // localbody Option VO
									summaryVO.getCadreIVRVO().setTotal(summaryVO.getCadreIVRVO().getTotal() + returnVo.getCount());
								
							}
						}
						
					}
					
				}
			}
		  }catch(Exception e){}
		}
	}
	
	
}catch (Exception e) {
	LOG.error("Exception Raised in getConstituencyWiseCommittesSummary",e);
}
return mandalList;
}
	
	public List<IvrOptionsVO> setOptionsList(List<IvrOptionsVO> optionsList)
	{
		List<IvrOptionsVO> list = new ArrayList<IvrOptionsVO>();
		try{
			for(IvrOptionsVO vo : optionsList)
			{
			IvrOptionsVO optionVo = new IvrOptionsVO();
			optionVo.setId(vo.getId());
			optionVo.setName(vo.getName());
			optionVo.setCount(0l);
			list.add(optionVo);
			}
			
		}
		catch(Exception e)
		{
			
		}
		return list;
	}
	public CommitteeSummaryVO getMatchedVOByConstId(List<CommitteeSummaryVO> volist,String  locatioName)
	{
		CommitteeSummaryVO returnVO = null;
		try {
			if(volist != null && volist.size()>0)
			{
				for (CommitteeSummaryVO  vo: volist) 
				{
					if(vo.getName().trim().equalsIgnoreCase(locatioName.trim()))
					{
						return vo;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Raised in getMatchedVOByIVRStatus",e);
		}
		
		return returnVO;
	}
	
	public void constiCountForMandalTownDivisions1(List<Long> constituencyIds,Date startDate, Date endDate,
			Map<Long,Long> mainMembersCountMap,Map<Long,Long> startedCommitteesCountMap,Map<Long,Long> completedCommitteesCountMap,
			Map<Long,Long> startedCommitteesAffCountMap,Map<Long,Long> completedCommitteesAffCountMap,Map<Long,Long> mainCommitteesCountMap,List<CommitteeSummaryVO> mandals,List<Long> committeeEnrollmentIdsLst){
			Map<Long,List<Long>> mandalIdsMap = new HashMap<Long,List<Long>>();//Map<id,List<1constituencyId>>
			Map<Long,List<Long>> localBodiesMap = new HashMap<Long,List<Long>>();//Map<id,List<1constituencyId>>
			List<Long> divisionLclIds = new ArrayList<Long>();//Map<id,List<1constituencyId>>
			Map<Long,List<Long>> divisionIdsMap = new HashMap<Long,List<Long>>();//Map<id,List<1constituencyId>>
			
		if(constituencyIds!=null && constituencyIds.size()>0){
			
			
			 
			 getLocationsInfo(constituencyIds, divisionLclIds, localBodiesMap, divisionIdsMap, mandalIdsMap);
			 
			 
			 if(mandalIdsMap.size() > 0){
				 List<Long> levelValues = new ArrayList<Long>(mandalIdsMap.keySet());
				 
				
				//0 count,1levelId
				 List<Object[]> totalMandalMainMembers = tdpCommitteeMemberDAO.totalMainMembersCountLocationsWise(5l,startDate, endDate,levelValues,null,committeeEnrollmentIdsLst,null);
				//0count,1locationID
				 List<Object[]> totalMandalCommittees = tdpCommitteeDAO.totalCommitteesCountByLocationIds(5l,levelValues,null,committeeEnrollmentIdsLst,null);
				 
				//0count,1locationID,2typeId
				 List<Object[]> totalMandalStartedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(5l,levelValues,startDate,endDate,"started",null,committeeEnrollmentIdsLst,null);
				//0count,1locationID,2typeId
				 List<Object[]> totalMandalCompletedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(5l,levelValues,startDate,endDate,"completed",null,committeeEnrollmentIdsLst,null);
				
				 
				    populateLocationCounts1(
						 mainMembersCountMap, 
						 mainCommitteesCountMap,
						 
						 startedCommitteesCountMap, 
						 startedCommitteesAffCountMap,
						 
						 completedCommitteesCountMap, 
						 completedCommitteesAffCountMap, 
						 
						 totalMandalMainMembers, 
						 totalMandalCommittees, 
						 totalMandalStartedCommittees, 
						 totalMandalCompletedCommittees, 
						 mandalIdsMap,"mandal",mandals);
			 }
			 if(localBodiesMap.size() > 0){
				 List<Long> levelValues = new ArrayList<Long>(localBodiesMap.keySet());
				
				//0 count,1levelId
				 List<Object[]> totalLocalBodMainMembers = tdpCommitteeMemberDAO.totalMainMembersCountLocationsWise(7l,startDate, endDate,levelValues,null,committeeEnrollmentIdsLst,null);
				//0count,1locationID
				 List<Object[]> totalLocalBodCommittees = tdpCommitteeDAO.totalCommitteesCountByLocationIds(7l,levelValues,null,committeeEnrollmentIdsLst,null);
				 
				//0count,1locationID,2typeId
				 List<Object[]> totalLocalBodStartedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(7l,levelValues,startDate,endDate,"started",null,committeeEnrollmentIdsLst,null);
				//0count,1locationID,2typeId
				 List<Object[]> totalLocalBodCompletedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(7l,levelValues,startDate,endDate,"completed",null,committeeEnrollmentIdsLst,null);
				
				 
				 populateLocationCounts1(
						 mainMembersCountMap, 
						 mainCommitteesCountMap,
						 
						 startedCommitteesCountMap, 
						 startedCommitteesAffCountMap,
						 
						 completedCommitteesCountMap, 
						 completedCommitteesAffCountMap, 
						 
						 totalLocalBodMainMembers, 
						 totalLocalBodCommittees, 
						 totalLocalBodStartedCommittees, 
						 totalLocalBodCompletedCommittees, 
						 localBodiesMap,"muncipality",mandals);
			 }
			 if(divisionIdsMap.size() > 0){
				 List<Long> levelValues = new ArrayList<Long>(divisionIdsMap.keySet());
				
				//0 count,1levelId
				 List<Object[]> totalDivisionMainMembers = tdpCommitteeMemberDAO.totalMainMembersCountLocationsWise(9l,startDate, endDate,levelValues,null,committeeEnrollmentIdsLst,null);
				//0count,1locationID
				 List<Object[]> totalDivisionCommittees = tdpCommitteeDAO.totalCommitteesCountByLocationIds(9l,levelValues,null,committeeEnrollmentIdsLst,null);
				 
				//0count,1locationID,2typeId
				 List<Object[]> totalDivisionStartedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(9l,levelValues,startDate,endDate,"started",null,committeeEnrollmentIdsLst,null);
				//0count,1locationID,2typeId
				 List<Object[]> totalDivisionCompletedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(9l,levelValues,startDate,endDate,"completed",null,committeeEnrollmentIdsLst,null);
				
				 
				 populateLocationCounts1(
						 mainMembersCountMap, 
						 mainCommitteesCountMap,
						 
						 startedCommitteesCountMap, 
						 startedCommitteesAffCountMap,
						 
						 completedCommitteesCountMap, 
						 completedCommitteesAffCountMap, 
						 
						 totalDivisionMainMembers, 
						 totalDivisionCommittees, 
						 totalDivisionStartedCommittees, 
						 totalDivisionCompletedCommittees, 
						 divisionIdsMap,"division",mandals);
			 }
			 
		}
	}
	private void populateLocationCounts1(
			 Map<Long,Long> mainMembersCountMap,
			 Map<Long,Long> mainCommitteesCountMap,
			 
			 Map<Long,Long> startedCommitteesCountMap,
			 Map<Long,Long> startedCommitteesAffCountMap,
			 
			 Map<Long,Long> completedCommitteesCountMap,			 
			 Map<Long,Long> completedCommitteesAffCountMap,
			 
			 List<Object[]> totalMainMembers,
			 List<Object[]> totalCommittees,
			 
			 List<Object[]> totalStartedCommittees,
			 
			 List<Object[]> totalCompletedCommittees,
			 
			 Map<Long,List<Long>> locationsMap,String locationType,List<CommitteeSummaryVO> mandals){
		
		     populateMainCounts1(totalMainMembers,locationsMap,mainMembersCountMap,locationType, mandals);
		     populateMainCounts1(totalCommittees,locationsMap,mainCommitteesCountMap,locationType,mandals);
		     populateMainAndAffCounts1(totalStartedCommittees,locationsMap,startedCommitteesCountMap,startedCommitteesAffCountMap,locationType,mandals);
		     populateMainAndAffCounts1(totalCompletedCommittees,locationsMap,completedCommitteesCountMap,completedCommitteesAffCountMap,locationType,mandals);
		
	}
	private void populateMainCounts1(List<Object[]> mainMembers,Map<Long,List<Long>> locationsMap,Map<Long,Long> countMap,String locationType,List<CommitteeSummaryVO> mandals){
		//0 count,1levelId
		for(Object[] countArr:mainMembers){
			String location = "";
			if(locationType.equalsIgnoreCase("mandal"))
				 location = "2"+(Long)countArr[1];
			else if(locationType.equalsIgnoreCase("muncipality"))
				 location = "1"+(Long)countArr[1];
			else if(locationType.equalsIgnoreCase("division"))
				 location = "3"+(Long)countArr[1];
					Long count = countMap.get(new Long(location.toString())); // locationId
					if(count == null){
						countMap.put(new Long(location.toString()),(Long)countArr[0]);
					}else{
						countMap.put(new Long(location.toString()),count+(Long)countArr[0]);
					}
			}
	}

	private void populateMainAndAffCounts1(List<Object[]> mainAffMembers,Map<Long,List<Long>> locationsMap,Map<Long,Long> mainCountMap,Map<Long,Long> affCountMap,String locationType,List<CommitteeSummaryVO> mandals){
		//0 count,1levelId,2typeId
		for(Object[] countArr:mainAffMembers){
			
			String location = "";
			if(locationType.equalsIgnoreCase("mandal"))
				 location = "2"+(Long)countArr[1];
			else if(locationType.equalsIgnoreCase("muncipality"))
				 location = "1"+(Long)countArr[1];
			else if(locationType.equalsIgnoreCase("division"))
				 location = "3"+(Long)countArr[1];
			Map<Long,Long> countMap = mainCountMap;
			if(((Long)countArr[2]).longValue() == 2l){
				countMap = affCountMap;
			}
					Long count = countMap.get(new Long(location.toString()));
					if(count == null){
						countMap.put(new Long(location.toString()),(Long)countArr[0]);
					}else{
						countMap.put(new Long(location.toString()),count+(Long)countArr[0]);
					}
				
			
		}
	}
	
	public void pushResultConstituencyWiseMemsCount1(String type,List<Object[]> memResLst, List<CommitteeSummaryVO> fnlLst,String locationType){
		if(memResLst!=null && memResLst.size()>0){
			
			for(Object[] obj:memResLst){
				String location = "";
				if(locationType.equalsIgnoreCase("mandal"))
					 location = "2"+Long.valueOf(obj[1].toString());
				else if(locationType.equalsIgnoreCase("muncipality"))
					 location = "1"+Long.valueOf(obj[1].toString());
				else if(locationType.equalsIgnoreCase("division"))
					 location = "3"+Long.valueOf(obj[1].toString());
				CommitteeSummaryVO temp = getMatchedConstituency(new Long(location.toString()), fnlLst);
				if(temp==null){
					temp = new CommitteeSummaryVO();
				}
				
				if(type.equalsIgnoreCase("munci")){
					if(temp.getTownMandalDivisionVO()==null){
						temp.setTownMandalDivisionVO(new CommitteeSummaryVO());
					}
					temp.getTownMandalDivisionVO().setMembersCount(Long.valueOf(obj[0].toString()));
				}else{
					if(temp.getVillageWardVO()==null){
						temp.setVillageWardVO(new CommitteeSummaryVO());
					}
					temp.getVillageWardVO().setMembersCount(Long.valueOf(obj[0].toString()));
				}
				
			}
		}
	}
	
	public void pushTotalCountsForConstituency1(String type,List<Object[]> memResLst, List<CommitteeSummaryVO> fnlLst,String locationType){
		if(memResLst!=null && memResLst.size()>0){
			
			for(Object[] obj:memResLst){
				String location = "";
				if(locationType.equalsIgnoreCase("mandal"))
					 location = "2"+Long.valueOf(obj[1].toString());
				else if(locationType.equalsIgnoreCase("muncipality"))
					 location = "1"+Long.valueOf(obj[1].toString());
				else if(locationType.equalsIgnoreCase("division"))
					 location = "3"+Long.valueOf(obj[1].toString());
				CommitteeSummaryVO temp = getMatchedConstituency(new Long(location.toString()), fnlLst);
				if(temp==null){
					temp = new CommitteeSummaryVO();
				}
				
				if(type.equalsIgnoreCase("munci")){
					if(temp.getTownMandalDivisionVO()==null){
						temp.setTownMandalDivisionVO(new CommitteeSummaryVO());
					}
					temp.getTownMandalDivisionVO().setTotalCommittees(Long.valueOf(obj[0].toString()));
				}else{
					if(temp.getVillageWardVO()==null){
						temp.setVillageWardVO(new CommitteeSummaryVO());
					}
					temp.getVillageWardVO().setTotalCommittees(Long.valueOf(obj[0].toString()));
				}
				
			}
		}
	}
	
	public void pushResultConstiWise1(String type,List<Object[]> memResLst, List<CommitteeSummaryVO> fnlLst, String resType,String locationType){
		if(memResLst!=null && memResLst.size()>0){
			
			for(Object[] obj:memResLst){
				String location = "";
				if(locationType.equalsIgnoreCase("mandal"))
					 location = "2"+Long.valueOf(obj[2].toString());
				else if(locationType.equalsIgnoreCase("muncipality"))
					 location = "1"+Long.valueOf(obj[2].toString());
				else if(locationType.equalsIgnoreCase("division"))
					 location = "3"+Long.valueOf(obj[2].toString());
				CommitteeSummaryVO temp = getMatchedConstituency(new Long(location.toString()), fnlLst);
				
				if(temp==null){
					temp = new CommitteeSummaryVO();
				}
				
				
				if(type.equalsIgnoreCase("munci")){
					if(temp.getTownMandalDivisionVO()==null){
						temp.setTownMandalDivisionVO(new CommitteeSummaryVO());
					}
					if(Long.valueOf(obj[1].toString()).equals(1l)){
						if(resType.equalsIgnoreCase("start")){	
							temp.getTownMandalDivisionVO().setMainStarted(Long.valueOf(obj[0].toString()));
						}else{
							temp.getTownMandalDivisionVO().setMainCompleted(Long.valueOf(obj[0].toString()));
						}
					}else{
						if(resType.equalsIgnoreCase("start")){
							temp.getTownMandalDivisionVO().setAfflStarted(Long.valueOf(obj[0].toString()));
						}else{
							temp.getTownMandalDivisionVO().setAfflCompleted(Long.valueOf(obj[0].toString()));
						}
					}
				}else{
					if(temp.getVillageWardVO()==null){
						temp.setVillageWardVO(new CommitteeSummaryVO());
					}
					if(Long.valueOf(obj[1].toString()).equals(1l)){
						if(resType.equalsIgnoreCase("start")){	
							temp.getVillageWardVO().setMainStarted(Long.valueOf(obj[0].toString()));
						}else{
							temp.getVillageWardVO().setMainCompleted(Long.valueOf(obj[0].toString()));
						}
					}else{
						if(resType.equalsIgnoreCase("start")){	
							temp.getVillageWardVO().setAfflStarted(Long.valueOf(obj[0].toString()));
						}else{
							temp.getVillageWardVO().setAfflCompleted(Long.valueOf(obj[0].toString()));
						}
					}
				}
				
			}
		}
	}
	public IvrOptionsVO getMatchedIvrOption(List<IvrOptionsVO> volist,Long  Id)
	{
		IvrOptionsVO returnVO = null;
		try {
			if(volist != null && volist.size()>0)
			{
				for (IvrOptionsVO  vo: volist) 
				{
					if(vo.getId().longValue() == Id.longValue())
					{
						return vo;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Raised in getMatchedVOByIVRStatus",e);
		}
		
		return returnVO;
	}
	
	//srishailam
	public List<TdpCadreVO>  getEventInviteesList(Long userId,String accessLevel,String accessValue, Long stateId,List<InviteesVO> inviteesVOList,Long eventId,String actionType,String stateStr,String reportType, Integer startIndex,Integer maxIndex)
	{
		List<TdpCadreVO> returnList = null;
		try {
			List<CadreCommitteeVO> cadreCommitteeList = new ArrayList<CadreCommitteeVO>();
			Map<String,Long> statePositonsCountMap = new LinkedHashMap<String, Long>();
			Map<String,Long> districtPositonsCountMap = new LinkedHashMap<String, Long>();
			Map<String,Long> mandalPositonsCountMap = new LinkedHashMap<String, Long>();
			Map<String,Long> villagePositonsCountMap = new LinkedHashMap<String, Long>();
			Map<String,Long> publicRepresentsCountMap = new LinkedHashMap<String, Long>();
			Long totalCount = 0L;
			Set<Long> compareCadreIds = new LinkedHashSet<Long>();
			Set<Long> compareRepresentsIds = new LinkedHashSet<Long>();
			
			if(inviteesVOList != null && inviteesVOList.size()>0)
			{
				if(startIndex == 0)
				{
					List<IdNameVO> publicRepresents = getPublicRepresenttativesList();
					 if(publicRepresents != null && publicRepresents.size()>0)
					 {
						 for (IdNameVO idNameVO : publicRepresents) {
							 publicRepresentsCountMap.put(idNameVO.getName(), 0L);							 
						}
					 }
					 
					List<BasicVO> tdpRoles = getCommitteeRoles();
					 if(tdpRoles != null && tdpRoles.size()>0)
					 {
						 for (BasicVO idNameVO : tdpRoles) {
							 statePositonsCountMap.put(idNameVO.getName(), 0L);
							 districtPositonsCountMap.put(idNameVO.getName(), 0L);
							 mandalPositonsCountMap.put(idNameVO.getName(), 0L);
							 villagePositonsCountMap.put(idNameVO.getName(), 0L);
						}
					 }
				}
				 Set<Long> tdpCadreIdsList = new TreeSet<Long>();
				 Set<Long> publicRepresentativesIds = new TreeSet<Long>();
				// List<Long> tdpCadreList = null;
				 List<Object[]> tdpCadresList = null;
				 List<Integer> publicRepresentativesList = null;
				 
				 
				 String region ="ALL";
				 if(stateId != null && stateId.longValue() == 2L )
				 {
					 region ="Telangana";
				 }
				 if(stateId != null && stateId.longValue() == 1L )
				 {
					 region ="AP";
				 }
				 
				for (InviteesVO inviteeVO : inviteesVOList) 
				{
					 List<Long> disstrictIds = new ArrayList<Long>();
					 List<Long> locationValuesList = new ArrayList<Long>();
					 List<Long> locationLevelIdsList = new ArrayList<Long>();
					 if(inviteeVO.getLevelStr().trim().equalsIgnoreCase("central")){ //central
						 List<InviteesVO> centralVOList = inviteeVO.getCentralLevelVOList();
						 if(centralVOList != null && centralVOList.size()>0)
						 {
							 for (InviteesVO inviteesVO : centralVOList) {
								 if(inviteesVO.getLevelId() != null && inviteesVO.getLevelId().longValue() == 12L)//central level
								 {
									 locationLevelIdsList.clear();
									 locationLevelIdsList.add(inviteesVO.getLevelId().longValue());
									 
									 tdpCadresList = tdpCommitteeMemberDAO.getCommitteeMemberDetailsByPositionsForCentral(locationLevelIdsList,inviteesVO.getRolesIds());
									 
									 if(tdpCadresList != null && tdpCadresList.size()>0)
									 {
										 Long count = 0L;
										 for (Object[] cadre : tdpCadresList) {
											Long cadreId = cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L;
											String positiion = cadre[1] != null ? cadre[1].toString().trim():null;											
											tdpCadreIdsList.add(cadreId);
											
											 if(startIndex == 0)
												{
													 if(compareCadreIds != null && compareCadreIds.size()>0)
													 {
														// for (Long candidateId : compareCadreIds) 
														// {
															if(!compareCadreIds.contains(cadreId))
															{
																if(statePositonsCountMap.get(positiion) != null)
																{
																	count = statePositonsCountMap.get(positiion);
																}
																count = count+1;
																statePositonsCountMap.put(positiion,count);
															}
														// }
													 }
													 else
													 {
														 if(statePositonsCountMap.get(positiion) != null)
															{
																count = statePositonsCountMap.get(positiion);
															}
															count = count+1;
															statePositonsCountMap.put(positiion,count);
														
													 }
												}
										 }
										 
										 compareCadreIds.addAll(tdpCadreIdsList);
										 
									 }
								 }
							 }
						 }
					 } //state111
					 else if(inviteeVO.getLevelStr().trim().equalsIgnoreCase("state"))
					 {
						 List<InviteesVO> stateVOList = inviteeVO.getStateLevelVOList();
						 if(stateVOList != null && stateVOList.size()>0)
						 {
							 for (InviteesVO inviteesVO : stateVOList) {
								 disstrictIds.clear();
								 if(inviteesVO.getLevelId() != null && inviteesVO.getLevelId().longValue() ==10L)//state level
								 {
									 locationLevelIdsList.clear();
									 locationValuesList.clear();
									 locationLevelIdsList.add(inviteesVO.getLevelId().longValue());
									 //locationValuesList.add(inviteesVO.getLevelValue().longValue());
									 
									 if(inviteesVO.getName().equalsIgnoreCase("PublicRepresentatives"))
									 {
										 List<Long> positions = inviteesVO.getRolesIds();
										 if(inviteesVO.getConstituencyId() != null && inviteesVO.getConstituencyId().longValue() > 0L) // constituency selected
										 {
											 locationValuesList.add(inviteesVO.getConstituencyId());
											 if(positions != null && positions.size()>0)
											 {
												 for (Long positionId : positions) {
													 publicRepresentativesList =  publicRepresentativeDAO.getRepresentativesByPositions(null,locationValuesList,positionId);
													 if(publicRepresentativesList != null && publicRepresentativesList.size()>0)
													 {
														 for (Integer candidateId : publicRepresentativesList) {
															 publicRepresentativesIds.add(Long.valueOf(String.valueOf(candidateId)));
														 }
														 
														 if(startIndex == 0)
															{
															 String positiion=  publicRepresentativeTypeDAO.get(positionId).getType();
														 	 Long count = 0L;
														 		
															 if(compareRepresentsIds != null && compareRepresentsIds.size()>0)
															 {
																 for (Integer candidateId : publicRepresentativesList) 
																 {
																	if(!compareRepresentsIds.contains(Long.valueOf(String.valueOf(candidateId))))
																	{
																		if(publicRepresentsCountMap.get(positiion) != null)
																		{
																			count = publicRepresentsCountMap.get(positiion);
																		}
																		count = count+1;
																		publicRepresentsCountMap.put(positiion,count);
																	}
																 }
															 }
															 else
															 {
																 if(publicRepresentsCountMap.get(positiion) != null)
																	{
																		count = publicRepresentsCountMap.get(positiion);
																	}
																	count = count+Long.valueOf(String.valueOf(publicRepresentativesList.size()));
																	
																	publicRepresentsCountMap.put(positiion,count);
															 }
																// compareRepresentsIds.addAll(publicRepresentativesList);
																 for (Integer candidateId : publicRepresentativesList) {
																	 compareRepresentsIds.add(Long.valueOf(String.valueOf(candidateId)));
																 }
															}
													 	}
												 	}
											 	}
										 }
										 else if(inviteesVO.getDistrictId() != null && inviteesVO.getDistrictId().longValue() > 0L) // District selected
											 {										 
											 if(positions != null && positions.size()>0)
											 {
												 for (Long positionId : positions) {
													 
													if(positionId.longValue() == 1L || positionId.longValue() == 9L || positionId.longValue() == 11L)//MP or Ex MP or Central Minister-2014
													 {
														 List<Object[]> parliamentts = delimitationConstituencyAssemblyDetailsDAO.findParliamentConstituenciesByDistrictId(inviteesVO.getDistrictId().longValue(),2009L);
														 if(parliamentts != null && parliamentts.size()>0)
														 {
															 for (Object[] parliament : parliamentts) {
																 Long parliamentId = parliament[0] != null ? Long.valueOf(parliament[0].toString().trim()):0L;
																 locationValuesList.add(parliamentId);
															}
														 }
													 }
													 else  if(positionId.longValue() == 2L  || positionId.longValue() == 6L || positionId.longValue() == 8L 
															  || positionId.longValue() == 10L || positionId.longValue() == 21L || positionId.longValue() == 22L)//MLA
													 {
														 List<Object[]> assemblyys = constituencyDAO.getConstituenciesDetaildByDistrictId(inviteesVO.getDistrictId().longValue());
														 if(assemblyys != null && assemblyys.size()>0)
														 {
															 for (Object[] assembly : assemblyys) {
																 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
																 locationValuesList.add(assemblyId);
															}
														 }
													 }
													 else  if(positionId.longValue() == 4L )//ZPTC
													 {
														 List<Object[]> assemblyys = constituencyDAO.getLatestConstituenciesByStateIdForregion("ZPTC", 1L, region,inviteesVO.getDistrictId(),null);
														 if(assemblyys != null && assemblyys.size()>0)
														 {
															 for (Object[] assembly : assemblyys) {
																 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
																 locationValuesList.add(assemblyId);
															}
														 }
													 }
													 else  if( positionId.longValue() == 3L || positionId.longValue() == 5L  || positionId.longValue() == 13L)//MPTC.MPP
													 {
														 List<Object[]> assemblyys = constituencyDAO.getLatestConstituenciesByStateIdForregion("MPTC", 1L, region,inviteesVO.getDistrictId(),null);
														 if(assemblyys != null && assemblyys.size()>0)
														 {
															 for (Object[] assembly : assemblyys) {
																 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
																 locationValuesList.add(assemblyId);
															}
														 }
													 }
													 else  if( positionId.longValue() == 12L || positionId.longValue() == 14L || positionId.longValue() == 15L)//MLC, ZP CHAIRMAN ,ZP VICE CHAIRMAN
													 {
														 locationValuesList.add(inviteesVO.getDistrictId());
													 }
													 else  if( positionId.longValue() == 17L || positionId.longValue() == 18L || positionId.longValue() == 19L
															  || positionId.longValue() == 20L)//
													 {	 
														 List<Object[]> localBodysLisst = localElectionBodyDAO.findByDistrictId(inviteesVO.getDistrictId());
														 if(localBodysLisst != null && localBodysLisst.size()>0)
														 {
															 for (Object[] district : localBodysLisst) {
																 Long localBodyId = district[0] != null ? Long.valueOf(district[0].toString().trim()):0L;
																 locationValuesList.add(localBodyId);
															}
														 }
													 }
													
													 publicRepresentativesList =  publicRepresentativeDAO.getRepresentativesByPositions(null,locationValuesList,positionId);
													 if(publicRepresentativesList != null && publicRepresentativesList.size()>0)
													 {
														// publicRepresentativesIds.addAll(publicRepresentativesList);
														 for (Integer candidateId : publicRepresentativesList) {
															 publicRepresentativesIds.add(Long.valueOf(String.valueOf(candidateId)));
														 }
														 
														 if(startIndex == 0)
															{
															 String positiion=  publicRepresentativeTypeDAO.get(positionId).getType();
														 	 Long count = 0L;
														 		
															 if(compareRepresentsIds != null && compareRepresentsIds.size()>0)
															 {
																 for (Integer candidateId : publicRepresentativesList) 
																 {
																	if(!compareRepresentsIds.contains(Long.valueOf(String.valueOf(candidateId))))
																	{
																		if(publicRepresentsCountMap.get(positiion) != null)
																		{
																			count = publicRepresentsCountMap.get(positiion);
																		}
																		count = count+1;
																		publicRepresentsCountMap.put(positiion,count);
																	}
																 }
															 }
															 else
															 {
																 if(publicRepresentsCountMap.get(positiion) != null)
																	{
																		count = publicRepresentsCountMap.get(positiion);
																	}
																	count = count+Long.valueOf(String.valueOf(publicRepresentativesList.size()));
																	
																	publicRepresentsCountMap.put(positiion,count);
															 }
																// compareRepresentsIds.addAll(publicRepresentativesList);
																 for (Integer candidateId : publicRepresentativesList) {
																	 compareRepresentsIds.add(Long.valueOf(String.valueOf(candidateId)));
																 }
															}
													 }
												}
											 }
										 }											
										 else
										 {
											 if(positions != null && positions.size()>0)
											 {
												 for (Long positionId : positions) {
													 locationValuesList.clear();
													 if(positionId.longValue() == 1L  || positionId.longValue() == 7L || positionId.longValue() == 9L || positionId.longValue() == 11L)//MP or EX.MP
													 {
														 List<Object[]> parliamentts = delimitationConstituencyAssemblyDetailsDAO.getLatestParliamentByStateIdForregion("Parliament", 1L, region);
														 if(parliamentts != null && parliamentts.size()>0)
														 {
															 for (Object[] parliament : parliamentts) {
																 Long parliamentId = parliament[0] != null ? Long.valueOf(parliament[0].toString().trim()):0L;
																 locationValuesList.add(parliamentId);
															}
														 }
													 }
													 else  if(positionId.longValue() == 2L || positionId.longValue() == 6L || positionId.longValue() == 8L
															  || positionId.longValue() == 10L || positionId.longValue() == 21L || positionId.longValue() == 22L)//MLA or State Ministers or Ex. MLA
													 {
														 List<Object[]> assemblyys = constituencyDAO.getLatestConstituenciesByStateIdForregion("Assembly", 1L, region,null,null);
														 if(assemblyys != null && assemblyys.size()>0)
														 {
															 for (Object[] assembly : assemblyys) {
																 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
																 locationValuesList.add(assemblyId);
															}
														 }
													 }
													 else  if(positionId.longValue() == 4L )//ZPTC
													 {
														 List<Object[]> assemblyys = constituencyDAO.getLatestConstituenciesByStateIdForregion("ZPTC", 1L, region,null,null);
														 if(assemblyys != null && assemblyys.size()>0)
														 {
															 for (Object[] assembly : assemblyys) {
																 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
																 locationValuesList.add(assemblyId);
															}
														 }
													 }
													 else  if( positionId.longValue() == 3L || positionId.longValue() == 5L || positionId.longValue() == 13L)//MPTC.MPP
													 {
														 List<Object[]> assemblyys = constituencyDAO.getLatestConstituenciesByStateIdForregion("MPTC", 1L, region,null,null);
														 if(assemblyys != null && assemblyys.size()>0)
														 {
															 for (Object[] assembly : assemblyys) {
																 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
																 locationValuesList.add(assemblyId);
															}
														 }
													 }
													 else  if( positionId.longValue() == 12L || positionId.longValue() == 14L || positionId.longValue() == 15L)//MLC, ZP CHAIRMAN ,ZP VICE CHAIRMAN
													 {
														 List<Object[]> districtsLisst = constituencyDAO.getLatestConstituenciesByStateIdForregion("MLC", 1L, region,null,null);
														 if(districtsLisst != null && districtsLisst.size()>0)
														 {
															 for (Object[] district : districtsLisst) {
																 Long districtId = district[0] != null ? Long.valueOf(district[0].toString().trim()):0L;
																 locationValuesList.add(districtId);
															}
														 }
													 }
													 else  if( positionId.longValue() == 16L || positionId.longValue() == 23)//MP (rajyasabha, EX state minister
													 {
														 List<Object[]> statesLisst = stateDAO.findStatesByCountryId(1L);// india id = 1L
														 if(statesLisst != null && statesLisst.size()>0)
														 {
															 for (Object[] state : statesLisst) {
																 Long statteId = state[0] != null ? Long.valueOf(state[0].toString().trim()):0L;
																 locationValuesList.add(statteId);
															}
														 }
													 }
													 else  if( positionId.longValue() == 17L || positionId.longValue() == 18L || positionId.longValue() == 19L
															  || positionId.longValue() == 20L)//
													 {	 
														 List<Object[]> localBodysLisst = localElectionBodyDAO.getAllLocalBodysByState(1L);
														 if(localBodysLisst != null && localBodysLisst.size()>0)
														 {
															 for (Object[] district : localBodysLisst) {
																 Long localBodyId = district[0] != null ? Long.valueOf(district[0].toString().trim()):0L;
																 locationValuesList.add(localBodyId);
															}
														 }
													 }
													 
													 
													 
													 publicRepresentativesList =  publicRepresentativeDAO.getRepresentativesByPositions(null,locationValuesList,positionId);
													 if(publicRepresentativesList != null && publicRepresentativesList.size()>0)
													 {
														 for (Integer candidateId : publicRepresentativesList) {
															 publicRepresentativesIds.add(Long.valueOf(String.valueOf(candidateId)));
														 }
														
														 if(startIndex == 0)
															{
																 String positiion=  publicRepresentativeTypeDAO.get(positionId).getType();
															 	 Long count = 0L;
															 		
																 if(compareRepresentsIds != null && compareRepresentsIds.size()>0)
																 {
																	 for (Integer candidateId : publicRepresentativesList) 
																	 {
																		if(!compareRepresentsIds.contains(Long.valueOf(String.valueOf(candidateId))))
																		{
																			if(publicRepresentsCountMap.get(positiion) != null)
																			{
																				count = publicRepresentsCountMap.get(positiion);
																			}
																			count = count+1;
																			publicRepresentsCountMap.put(positiion,count);
																		}
																	 }
																 }
																 else
																 {
																	 if(publicRepresentsCountMap.get(positiion) != null)
																		{
																			count = publicRepresentsCountMap.get(positiion);
																		}
																		count = count+Long.valueOf(String.valueOf(publicRepresentativesList.size()));
																		
																		publicRepresentsCountMap.put(positiion,count);
																 }
																 	//compareRepresentsIds.addAll(publicRepresentativesList);
																	 for (Integer candidateId : publicRepresentativesList) {
																		 compareRepresentsIds.add(Long.valueOf(String.valueOf(candidateId)));
																	 }
															}
													 }
												}
											 }
										 }
									 }
									 else
									 {
										 if(stateId != null && stateId.longValue() == 0L || stateId.longValue() == 2L )
										 {
											 disstrictIds.add(1L);disstrictIds.add(2L);disstrictIds.add(3L);disstrictIds.add(4L);disstrictIds.add(5L);disstrictIds.add(6L);disstrictIds.add(7L);disstrictIds.add(8L);disstrictIds.add(9L);disstrictIds.add(10L);
											 
										 }
										 if(stateId != null && stateId.longValue() == 0L || stateId.longValue() == 1L )
										 {
											 disstrictIds.add(11L);disstrictIds.add(12L);disstrictIds.add(13L);disstrictIds.add(14L);disstrictIds.add(15L);disstrictIds.add(16L);disstrictIds.add(17L);disstrictIds.add(18L);disstrictIds.add(19L);disstrictIds.add(20L);
											 disstrictIds.add(21L);disstrictIds.add(22L);disstrictIds.add(23L);
											
										 }
										 
										 if(locationLevelIdsList != null && locationLevelIdsList.size()>0)
										 {
											 Long committteeLevelId = locationLevelIdsList.get(0);
											if(committteeLevelId != null && (committteeLevelId.longValue() == 10L || committteeLevelId.longValue() == 12L ))
												 disstrictIds = null;
											if(stateId.longValue() == 1L)
											locationValuesList.add(1l);
											if(stateId.longValue() == 2L)
												locationValuesList.add(36l);
										 }
										 tdpCadresList = tdpCommitteeMemberDAO.getCommiteeMembersDetailsByPostionsAndCommiteeLevel(locationLevelIdsList,locationValuesList,inviteesVO.getCommitteeId(),inviteesVO.getRolesIds(),disstrictIds,null,null);										 
									 }
									
									 if(tdpCadresList != null && tdpCadresList.size()>0)
									 {
										 Long count = 0L;
										 for (Object[] cadre : tdpCadresList) {
											Long cadreId = cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L;
											String positiion = cadre[1] != null ? cadre[1].toString().trim():null;											
											tdpCadreIdsList.add(cadreId);
											
											 if(startIndex == 0)
												{
													 if(compareCadreIds != null && compareCadreIds.size()>0)
													 {
														// for (Long candidateId : compareCadreIds) 
														// {
															if(!compareCadreIds.contains(cadreId))
															{
																if(statePositonsCountMap.get(positiion) != null)
																{
																	count = statePositonsCountMap.get(positiion);
																}
																count = count+1;
																statePositonsCountMap.put(positiion,count);
															}
														// }
													 }
													 else
													 {
														 if(statePositonsCountMap.get(positiion) != null)
															{
																count = statePositonsCountMap.get(positiion);
															}
															count = count+1;
															statePositonsCountMap.put(positiion,count);
														
													 }
												}
										 }
										 
										 compareCadreIds.addAll(tdpCadreIdsList);
										 
									 }
								 }
							 }
						 }
						
					 }	//district111				
					 else if(inviteeVO.getLevelStr().trim().equalsIgnoreCase("district"))
					 {
						 List<InviteesVO> districtVOList = inviteeVO.getDistrictLevelVOList();
						 if(districtVOList != null && districtVOList.size()>0)
						 {
							 for (InviteesVO inviteesVO : districtVOList) {
								 disstrictIds.clear();
								 if(inviteesVO.getLevelId() != null && inviteesVO.getLevelId().longValue() ==11L)//district level
								 {
									 locationLevelIdsList.clear();
									 locationValuesList.clear();
									 locationLevelIdsList.add(inviteesVO.getLevelId().longValue());
									// locationValuesList.add(inviteesVO.getLevelValue().longValue());
									 
									 if(inviteesVO.getName().equalsIgnoreCase("PublicRepresentatives"))
									 {
										 List<Long> positions = inviteesVO.getRolesIds();
										 
										 if(inviteesVO.getDistrictId() != null && inviteesVO.getDistrictId().longValue() ==0L) // district not selected
										 {
											 if(positions != null && positions.size()>0)
											 {
												 for (Long positionId : positions) {
													 
													 if(positionId.longValue() == 1L || positionId.longValue() == 9L || positionId.longValue() == 11L)//MP or Ex MP or Central Minister-2014
													 {
														 //0.constituencyId,1.name,2.districtName,3.districtId
														 List<Object[]> parliamentts = delimitationConstituencyAssemblyDetailsDAO.getLatestParliamentByStateIdForregion("Parliament", 1L, region);
														 if(parliamentts != null && parliamentts.size()>0)
														 {
															 for (Object[] parliament : parliamentts) {
																 Long parliamentId = parliament[0] != null ? Long.valueOf(parliament[0].toString().trim()):0L;
																 locationValuesList.add(parliamentId);
															 }
														 }
													 }
													 else if(positionId.longValue() == 2L   || positionId.longValue() == 6L || positionId.longValue() == 8L 
															 || positionId.longValue() == 10L || positionId.longValue() == 21L || positionId.longValue() == 22L )//MLA or Ex MLA or 
													 {
														 List<Object[]> assemblyys = constituencyDAO.getLatestConstituenciesByStateIdForregion("Assembly", 1L, region,null, null);
														 if(assemblyys != null && assemblyys.size()>0)
														 {
															 for (Object[] assembly : assemblyys) {
																 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
																 locationValuesList.add(assemblyId);
															}
														 }
													 }
													 else  if(positionId.longValue() == 4L )//ZPTC
													 {
														 List<Object[]> assemblyys = constituencyDAO.getLatestConstituenciesByStateIdForregion("ZPTC", 1L, region,null,null);
														 if(assemblyys != null && assemblyys.size()>0)
														 {
															 for (Object[] assembly : assemblyys) {
																 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
																 locationValuesList.add(assemblyId);
															}
														 }
													 }
													 else  if( positionId.longValue() == 3L || positionId.longValue() == 5L || positionId.longValue() == 13L )//MPTC.MPP
													 {
														 List<Object[]> assemblyys = constituencyDAO.getLatestConstituenciesByStateIdForregion("MPTC", 1L, region,null,null);
														 if(assemblyys != null && assemblyys.size()>0)
														 {
															 for (Object[] assembly : assemblyys) {
																 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
																 locationValuesList.add(assemblyId);
															}
														 }
													 }
													 else  if( positionId.longValue() == 12L || positionId.longValue() == 14L || positionId.longValue() == 15L)//MLC, ZP CHAIRMAN ,ZP VICE CHAIRMAN
													 {
														 List<Object[]> districtsLisst = constituencyDAO.getLatestConstituenciesByStateIdForregion("MLC", 1L, region,null,null);
														 if(districtsLisst != null && districtsLisst.size()>0)
														 {
															 for (Object[] district : districtsLisst) {
																 Long districtId = district[0] != null ? Long.valueOf(district[0].toString().trim()):0L;
																 locationValuesList.add(districtId);
															}
														 }
													 }
													 else  if( positionId.longValue() == 17L || positionId.longValue() == 18L || positionId.longValue() == 19L
															  || positionId.longValue() == 20L)// localbodyslist
													 {	 
														 List<Object[]> localBodysLisst = localElectionBodyDAO.getAllLocalBodysByState(1L);
														 if(localBodysLisst != null && localBodysLisst.size()>0)
														 {
															 for (Object[] district : localBodysLisst) {
																 Long localBodyId = district[0] != null ? Long.valueOf(district[0].toString().trim()):0L;
																 locationValuesList.add(localBodyId);
															}
														 }
													 }
													 publicRepresentativesList =  publicRepresentativeDAO.getRepresentativesByPositions(null,locationValuesList,positionId);
													 if(publicRepresentativesList != null && publicRepresentativesList.size()>0)
													 {
														 //publicRepresentativesIds.addAll(publicRepresentativesList);
														 
														 for (Integer candidateId : publicRepresentativesList) {
															 publicRepresentativesIds.add(Long.valueOf(String.valueOf(candidateId)));
														 }
														 
														 if(startIndex == 0)
															{
															 String positiion=  publicRepresentativeTypeDAO.get(positionId).getType();
														 	 Long count = 0L;
														 		
															 if(compareRepresentsIds != null && compareRepresentsIds.size()>0)
															 {
																 for (Integer candidateId : publicRepresentativesList) 
																 {
																	if(!compareRepresentsIds.contains(Long.valueOf(String.valueOf(candidateId))))
																	{
																		if(publicRepresentsCountMap.get(positiion) != null)
																		{
																			count = publicRepresentsCountMap.get(positiion);
																		}
																		count = count+1;
																		publicRepresentsCountMap.put(positiion,count);
																	}
																 }
															 }
															 else
															 {
																 if(publicRepresentsCountMap.get(positiion) != null)
																	{
																		count = publicRepresentsCountMap.get(positiion);
																	}
																	count = count+Long.valueOf(String.valueOf(publicRepresentativesList.size()));
																	
																	publicRepresentsCountMap.put(positiion,count);
															 }
																 //compareRepresentsIds.addAll(publicRepresentativesList);
																 for (Integer candidateId : publicRepresentativesList) {
																	 compareRepresentsIds.add(Long.valueOf(String.valueOf(candidateId)));
																 }
															}
													 }
												}
											 }
										 }
										 else if(inviteesVO.getConstituencyId() != null && inviteesVO.getConstituencyId().longValue() > 0L) // constituency selected
										 {
											 locationValuesList.add(inviteesVO.getConstituencyId());
											 if(positions != null && positions.size()>0)
											 {
												 for (Long positionId : positions) {
													 publicRepresentativesList =  publicRepresentativeDAO.getRepresentativesByPositions(null,locationValuesList,positionId);
													 if(publicRepresentativesList != null && publicRepresentativesList.size()>0)
													 {
														 for (Integer candidateId : publicRepresentativesList) {
															 publicRepresentativesIds.add(Long.valueOf(String.valueOf(candidateId)));
														 }
														 
														 if(startIndex == 0)
															{
															 String positiion=  publicRepresentativeTypeDAO.get(positionId).getType();
														 	 Long count = 0L;
														 		
															 if(compareRepresentsIds != null && compareRepresentsIds.size()>0)
															 {
																 for (Integer candidateId : publicRepresentativesList) 
																 {
																	if(!compareRepresentsIds.contains(Long.valueOf(String.valueOf(candidateId))))
																	{
																		if(publicRepresentsCountMap.get(positiion) != null)
																		{
																			count = publicRepresentsCountMap.get(positiion);
																		}
																		count = count+1;
																		publicRepresentsCountMap.put(positiion,count);
																	}
																 }
															 }
															 else
															 {
																 if(publicRepresentsCountMap.get(positiion) != null)
																	{
																		count = publicRepresentsCountMap.get(positiion);
																	}
																	count = count+Long.valueOf(String.valueOf(publicRepresentativesList.size()));
																	
																	publicRepresentsCountMap.put(positiion,count);
															 }
																// compareRepresentsIds.addAll(publicRepresentativesList);
																 for (Integer candidateId : publicRepresentativesList) {
																	 compareRepresentsIds.add(Long.valueOf(String.valueOf(candidateId)));
																 }
															}
													 	}
												 	}
											 	}
										 }
										 else
										 {
											 
											 if(positions != null && positions.size()>0)
											 {
												 for (Long positionId : positions) {
													 
													if(positionId.longValue() == 1L || positionId.longValue() == 9L || positionId.longValue() == 11L)//MP or Ex MP or Central Minister-2014
													 {
														 List<Object[]> parliamentts = delimitationConstituencyAssemblyDetailsDAO.findParliamentConstituenciesByDistrictId(inviteesVO.getDistrictId().longValue(),2009L);
														 if(parliamentts != null && parliamentts.size()>0)
														 {
															 for (Object[] parliament : parliamentts) {
																 Long parliamentId = parliament[0] != null ? Long.valueOf(parliament[0].toString().trim()):0L;
																 locationValuesList.add(parliamentId);
															}
														 }
													 }
													 else  if(positionId.longValue() == 2L  || positionId.longValue() == 6L || positionId.longValue() == 8L 
															  || positionId.longValue() == 10L || positionId.longValue() == 21L || positionId.longValue() == 22L)//MLA
													 {
														 List<Object[]> assemblyys = constituencyDAO.getConstituenciesDetaildByDistrictId(inviteesVO.getDistrictId().longValue());
														 if(assemblyys != null && assemblyys.size()>0)
														 {
															 for (Object[] assembly : assemblyys) {
																 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
																 locationValuesList.add(assemblyId);
															}
														 }
													 }
													 else  if(positionId.longValue() == 4L )//ZPTC
													 {
														 List<Object[]> assemblyys = constituencyDAO.getLatestConstituenciesByStateIdForregion("ZPTC", 1L, region,inviteesVO.getDistrictId(),null);
														 if(assemblyys != null && assemblyys.size()>0)
														 {
															 for (Object[] assembly : assemblyys) {
																 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
																 locationValuesList.add(assemblyId);
															}
														 }
													 }
													 else  if( positionId.longValue() == 3L || positionId.longValue() == 5L  || positionId.longValue() == 13L)//MPTC.MPP
													 {
														 List<Object[]> assemblyys = constituencyDAO.getLatestConstituenciesByStateIdForregion("MPTC", 1L, region,inviteesVO.getDistrictId(),null);
														 if(assemblyys != null && assemblyys.size()>0)
														 {
															 for (Object[] assembly : assemblyys) {
																 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
																 locationValuesList.add(assemblyId);
															}
														 }
													 }
													 else  if( positionId.longValue() == 12L || positionId.longValue() == 14L || positionId.longValue() == 15L)//MLC, ZP CHAIRMAN ,ZP VICE CHAIRMAN
													 {
														 locationValuesList.add(inviteesVO.getDistrictId());
													 }
													 else  if( positionId.longValue() == 17L || positionId.longValue() == 18L || positionId.longValue() == 19L
															  || positionId.longValue() == 20L)//
													 {	 
														 List<Object[]> localBodysLisst = localElectionBodyDAO.findByDistrictId(inviteesVO.getDistrictId());
														 if(localBodysLisst != null && localBodysLisst.size()>0)
														 {
															 for (Object[] district : localBodysLisst) {
																 Long localBodyId = district[0] != null ? Long.valueOf(district[0].toString().trim()):0L;
																 locationValuesList.add(localBodyId);
															}
														 }
													 }
													
													 publicRepresentativesList =  publicRepresentativeDAO.getRepresentativesByPositions(null,locationValuesList,positionId);
													 if(publicRepresentativesList != null && publicRepresentativesList.size()>0)
													 {
														// publicRepresentativesIds.addAll(publicRepresentativesList);
														 for (Integer candidateId : publicRepresentativesList) {
															 publicRepresentativesIds.add(Long.valueOf(String.valueOf(candidateId)));
														 }
														 
														 if(startIndex == 0)
															{
															 String positiion=  publicRepresentativeTypeDAO.get(positionId).getType();
														 	 Long count = 0L;
														 		
															 if(compareRepresentsIds != null && compareRepresentsIds.size()>0)
															 {
																 for (Integer candidateId : publicRepresentativesList) 
																 {
																	if(!compareRepresentsIds.contains(Long.valueOf(String.valueOf(candidateId))))
																	{
																		if(publicRepresentsCountMap.get(positiion) != null)
																		{
																			count = publicRepresentsCountMap.get(positiion);
																		}
																		count = count+1;
																		publicRepresentsCountMap.put(positiion,count);
																	}
																 }
															 }
															 else
															 {
																 if(publicRepresentsCountMap.get(positiion) != null)
																	{
																		count = publicRepresentsCountMap.get(positiion);
																	}
																	count = count+Long.valueOf(String.valueOf(publicRepresentativesList.size()));
																	
																	publicRepresentsCountMap.put(positiion,count);
															 }
																// compareRepresentsIds.addAll(publicRepresentativesList);
																 for (Integer candidateId : publicRepresentativesList) {
																	 compareRepresentsIds.add(Long.valueOf(String.valueOf(candidateId)));
																 }
															}
													 }
												}
											 }
										 }
									 }
									 else
									 {
										 if(inviteesVO.getDistrictId() != null && inviteesVO.getDistrictId().longValue() ==0L) // district not selected
										 {
											 if(stateId != null && stateId.longValue() == 0L || stateId.longValue() == 2L )
											 {
												 disstrictIds.add(1L);disstrictIds.add(2L);disstrictIds.add(3L);disstrictIds.add(4L);disstrictIds.add(5L);disstrictIds.add(6L);disstrictIds.add(7L);disstrictIds.add(8L);disstrictIds.add(9L);disstrictIds.add(10L);
											 }
											 if(stateId != null && stateId.longValue() == 0L || stateId.longValue() == 1L )
											 {
												 disstrictIds.add(11L);disstrictIds.add(12L);disstrictIds.add(13L);disstrictIds.add(14L);disstrictIds.add(15L);disstrictIds.add(16L);disstrictIds.add(17L);disstrictIds.add(18L);disstrictIds.add(19L);disstrictIds.add(20L);
												 disstrictIds.add(21L);disstrictIds.add(22L);disstrictIds.add(23L);
											 }
										 }
										 else // district selected
										 {
											 disstrictIds.add(inviteesVO.getDistrictId().longValue());
										 }
										 
										 tdpCadresList = tdpCommitteeMemberDAO.getCommiteeMembersDetailsByPostionsAndCommiteeLevel(locationLevelIdsList,locationValuesList,inviteesVO.getCommitteeId(),inviteesVO.getRolesIds(),disstrictIds,null,null);
										 if(tdpCadresList != null && tdpCadresList.size()>0)
										 {
											 Long count = 0L;
											 for (Object[] cadre : tdpCadresList) {
												Long cadreId = cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L;
												String positiion = cadre[1] != null ? cadre[1].toString().trim():null;											
												tdpCadreIdsList.add(cadreId);
												
												 if(startIndex == 0)
													{
														 if(compareCadreIds != null && compareCadreIds.size()>0)
														 {
															// for (Long candidateId : compareCadreIds) 
															// {
																if(!compareCadreIds.contains(cadreId))
																{
																	if(districtPositonsCountMap.get(positiion) != null)
																	{
																		count = districtPositonsCountMap.get(positiion);
																	}
																	count = count+1;
																	districtPositonsCountMap.put(positiion,count);
																}
															// }
														 }
														 else
														 {
															 if(districtPositonsCountMap.get(positiion) != null)
																{
																	count = districtPositonsCountMap.get(positiion);
																}
																count = count+1;
																districtPositonsCountMap.put(positiion,count);															
														 }
													}
											 }
											 compareCadreIds.addAll(tdpCadreIdsList);
										}
									 }
									
								 }
							}
						 }
					 }//mandal111
					 else  if(inviteeVO.getLevelStr().trim().equalsIgnoreCase("mandal"))
					 {  
						 List<InviteesVO> mandalList = inviteeVO.getMandalLevelVOList();
						 if(mandalList != null && mandalList.size()>0)
						 {
							 for (InviteesVO inviteesVO : mandalList) {
								 if(inviteesVO.getName().trim().equalsIgnoreCase("PublicRepresentatives"))								 
								 {
									 List<Long> positions = inviteesVO.getRolesIds();
									 
									 if(inviteesVO.getMandalId() != null && inviteesVO.getMandalId().longValue() ==0L) // district not selected
									 {
										 if(positions != null && positions.size()>0)
										 {
											 for (Long positionId : positions) {
												 
												 if(positionId.longValue() == 4L )//ZPTC
												 {
													 List<Object[]> assemblyys = constituencyDAO.getLatestConstituenciesByStateIdForregion("ZPTC", 1L, region,null,null);
													 if(assemblyys != null && assemblyys.size()>0)
													 {
														 for (Object[] assembly : assemblyys) {
															 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
															 locationValuesList.add(assemblyId);
														}
													 }
												 }
												 else  if( positionId.longValue() == 3L || positionId.longValue() == 5L || positionId.longValue() == 13L)//MPTC.MPP
												 {
													 List<Object[]> assemblyys = constituencyDAO.getLatestConstituenciesByStateIdForregion("MPTC", 1L, region,null,null);
													 if(assemblyys != null && assemblyys.size()>0)
													 {
														 for (Object[] assembly : assemblyys) {
															 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
															 locationValuesList.add(assemblyId);
														}
													 }
												 }
												 publicRepresentativesList =  publicRepresentativeDAO.getRepresentativesByPositions(null,locationValuesList,positionId);
												 if(publicRepresentativesList != null && publicRepresentativesList.size()>0)
												 {
													 //publicRepresentativesIds.addAll(publicRepresentativesList);
													 
													 for (Integer candidateId : publicRepresentativesList) {
														 publicRepresentativesIds.add(Long.valueOf(String.valueOf(candidateId)));
													 }
													 
													 if(startIndex == 0)
														{
														 String positiion=  publicRepresentativeTypeDAO.get(positionId).getType();
													 	 Long count = 0L;
													 		
														 if(compareRepresentsIds != null && compareRepresentsIds.size()>0)
														 {
															 for (Integer candidateId : publicRepresentativesList) 
															 {
																if(!compareRepresentsIds.contains(Long.valueOf(String.valueOf(candidateId))))
																{
																	if(publicRepresentsCountMap.get(positiion) != null)
																	{
																		count = publicRepresentsCountMap.get(positiion);
																	}
																	count = count+1;
																	publicRepresentsCountMap.put(positiion,count);
																}
															 }
														 }
														 else
														 {
															 if(publicRepresentsCountMap.get(positiion) != null)
																{
																	count = publicRepresentsCountMap.get(positiion);
																}
																count = count+Long.valueOf(String.valueOf(publicRepresentativesList.size()));
																
																publicRepresentsCountMap.put(positiion,count);
														 }
															 //compareRepresentsIds.addAll(publicRepresentativesList);
															 for (Integer candidateId : publicRepresentativesList) {
																 compareRepresentsIds.add(Long.valueOf(String.valueOf(candidateId)));
															 }
														}
												 }
											}
										 }
									 }
									 else // mandal selected
									 {
										 
										 if(positions != null && positions.size()>0)
										 {
											 for (Long positionId : positions) {
												 
												if(positionId.longValue() == 4L )//ZPTC
												 {
													 List<Object[]> assemblyys = constituencyDAO.getLatestConstituenciesByStateIdForregion("ZPTC", 1L, region,null,inviteesVO.getMandalId());
													 if(assemblyys != null && assemblyys.size()>0)
													 {
														 for (Object[] assembly : assemblyys) {
															 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
															 locationValuesList.add(assemblyId);
														}
													 }
												 }
												 else  if( positionId.longValue() == 3L || positionId.longValue() == 5L || positionId.longValue() == 13L)//MPTC.MPP
												 {
													 List<Object[]> assemblyys = constituencyDAO.getLatestConstituenciesByStateIdForregion("MPTC", 1L, region,null,inviteesVO.getMandalId());
													 if(assemblyys != null && assemblyys.size()>0)
													 {
														 for (Object[] assembly : assemblyys) {
															 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
															 locationValuesList.add(assemblyId);
														}
													 }
												 }
												 publicRepresentativesList =  publicRepresentativeDAO.getRepresentativesByPositions(null,locationValuesList,positionId);
												 if(publicRepresentativesList != null && publicRepresentativesList.size()>0)
												 {
													// publicRepresentativesIds.addAll(publicRepresentativesList);
													 for (Integer candidateId : publicRepresentativesList) {
														 publicRepresentativesIds.add(Long.valueOf(String.valueOf(candidateId)));
													 }
													 
													 if(startIndex == 0)
														{
														 String positiion=  publicRepresentativeTypeDAO.get(positionId).getType();
													 	 Long count = 0L;
													 		
														 if(compareRepresentsIds != null && compareRepresentsIds.size()>0)
														 {
															 for (Integer candidateId : publicRepresentativesList) 
															 {
																if(!compareRepresentsIds.contains(Long.valueOf(String.valueOf(candidateId))))
																{
																	if(publicRepresentsCountMap.get(positiion) != null)
																	{
																		count = publicRepresentsCountMap.get(positiion);
																	}
																	count = count+1;
																	publicRepresentsCountMap.put(positiion,count);
																}
															 }
														 }
														 else
														 {
															 if(publicRepresentsCountMap.get(positiion) != null)
																{
																	count = publicRepresentsCountMap.get(positiion);
																}
																count = count+Long.valueOf(String.valueOf(publicRepresentativesList.size()));
																
																publicRepresentsCountMap.put(positiion,count);
														 }
															// compareRepresentsIds.addAll(publicRepresentativesList);
															 for (Integer candidateId : publicRepresentativesList) {
																 compareRepresentsIds.add(Long.valueOf(String.valueOf(candidateId)));
															 }
														}
												 }
											}
										 }
									 }
								 }
								 if(!inviteesVO.getName().trim().equalsIgnoreCase("PublicRepresentatives"))
								 {
									 disstrictIds.clear();
									 if(inviteesVO.getLevelId() != null && inviteesVO.getLevelId().longValue() ==5L)//mandal level
									 {
										 locationLevelIdsList.clear();
										 locationValuesList.clear();
										// locationValuesList.add(inviteesVO.getLevelValue().longValue());
										 
										 locationLevelIdsList.add(5L);
										 locationLevelIdsList.add(7L);
										 locationLevelIdsList.add(9L);
										 
										 if(inviteesVO.getMandalId() != null && inviteesVO.getMandalId().longValue() != 0L) // mandal/muncipality selected
										 {										
											 locationValuesList.add(Long.valueOf(inviteesVO.getMandalId().toString().substring(1)));
										 }
										 else if(inviteesVO.getConstituencyId() != null && inviteesVO.getConstituencyId().longValue() != 0L) // constituency selected
										 {
											 List<Object[]> tehsilIds = tehsilDAO.findTehsilsByConstituencyIdAndPublicationDateId(inviteesVO.getConstituencyId(),IConstants.VOTER_DATA_PUBLICATION_ID);
											 if(tehsilIds != null && tehsilIds.size()>0)
											 {
												 for (Object[] tehsil : tehsilIds) {
													 locationValuesList.add(tehsil[0] != null ? Long.valueOf(tehsil[0].toString().trim()):0L);
												}
											 }
											 
											 List<Long> localElectionBodyIds = localElectionBodyDAO.getMuncipalitiesAndCorporationsForConstituency(locationValuesList);
											 if(localElectionBodyIds != null && localElectionBodyIds.size()>0)
											 {
												 for (Long localElectionBodyId : localElectionBodyIds) {
													List<Object[]> wardsList =  constituencyDAO.findWardsInLocalElectionBodies(localElectionBodyId.toString());
													 for (Object[] panchayat : wardsList) {
														 locationValuesList.add(panchayat[0] != null ? Long.valueOf(panchayat[0].toString().trim()):0L);
													}
												}
											 }
										 }
										 else if(inviteesVO.getDistrictId() != null && inviteesVO.getDistrictId().longValue() ==0L) // district not selected
										 {
											 if(stateId != null && stateId.longValue() == 0L || stateId.longValue() == 2L )
											 {
												 disstrictIds.add(1L);disstrictIds.add(2L);disstrictIds.add(3L);disstrictIds.add(4L);disstrictIds.add(5L);disstrictIds.add(6L);disstrictIds.add(7L);disstrictIds.add(8L);disstrictIds.add(9L);disstrictIds.add(10L);
											 }
											 if(stateId != null && stateId.longValue() == 0L || stateId.longValue() == 1L )
											 {
												 disstrictIds.add(11L);disstrictIds.add(12L);disstrictIds.add(13L);disstrictIds.add(14L);disstrictIds.add(15L);disstrictIds.add(16L);disstrictIds.add(17L);disstrictIds.add(18L);disstrictIds.add(19L);disstrictIds.add(20L);
												 disstrictIds.add(21L);disstrictIds.add(22L);disstrictIds.add(23L);
											 }
										 }
										 else  if(inviteesVO.getDistrictId() != null && inviteesVO.getDistrictId().longValue() != 0L) // district selected
										 {
											 disstrictIds.add(inviteesVO.getDistrictId());
										 }
										
										 tdpCadresList = tdpCommitteeMemberDAO.getCommiteeMembersDetailsByPostionsAndCommiteeLevel(locationLevelIdsList,locationValuesList,inviteesVO.getCommitteeId(),inviteesVO.getRolesIds(),disstrictIds,null,null);
										 
										 if(tdpCadresList != null && tdpCadresList.size()>0)
										 {
											 Long count = 0L;
											 for (Object[] cadre : tdpCadresList) {
												Long cadreId = cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L;
												String positiion = cadre[1] != null ? cadre[1].toString().trim():null;											
												tdpCadreIdsList.add(cadreId);
												
												 if(startIndex == 0)
													{
														 if(compareCadreIds != null && compareCadreIds.size()>0)
														 {
															 //for (Long candidateId : compareCadreIds) 
															// {
																if(!compareCadreIds.contains(cadreId))
																{
																	if(mandalPositonsCountMap.get(positiion) != null)
																	{
																		count = mandalPositonsCountMap.get(positiion);
																	}
																	count = count+1;
																	mandalPositonsCountMap.put(positiion,count);
																}
															// }
														 }
														 else
														 {
															 if(mandalPositonsCountMap.get(positiion) != null)
																{
																	count = mandalPositonsCountMap.get(positiion);
																}
																count = count+1;
																mandalPositonsCountMap.put(positiion,count);
															
																
														 }
													}
											 }
											 
											 compareCadreIds.addAll(tdpCadreIdsList);
											 
										}
									 }
								
								 }
							}
						 }
						
					 }			//village111
					 else  if(inviteeVO.getLevelStr().trim().equalsIgnoreCase("village"))
					 {
						
						 List<InviteesVO> villageVOList = inviteeVO.getVillageLevelVOList();
						 if(villageVOList != null && villageVOList.size()>0)
						 {
							 for (InviteesVO inviteesVO : villageVOList) 
							 {
								 if(!inviteesVO.getName().trim().equalsIgnoreCase("PublicRepresentatives"))
								 {

									 disstrictIds.clear();
									 if(inviteesVO.getLevelId() != null && inviteesVO.getLevelId().longValue() ==6L)//village/ward level
									 {
										 locationLevelIdsList.clear();
										 locationValuesList.clear();
										// locationValuesList.add(inviteesVO.getLevelValue().longValue());
										 locationLevelIdsList.add(6L);
										 locationLevelIdsList.add(8L);
										 if(inviteesVO.getPanchayatId() != null && inviteesVO.getPanchayatId().longValue() != 0L) // mandal/muncipality selected
										 {
											 locationValuesList.add(inviteesVO.getPanchayatId());
										 }
										 else if(inviteesVO.getMandalId() != null && inviteesVO.getMandalId().longValue() != 0L) // mandal/muncipality selected
										 {
											 List<Long> tehsilIds = new ArrayList<Long>();
											 tehsilIds.add(Long.valueOf(inviteesVO.getMandalId().toString().substring(1)));	
											 List<Long> localElectionBodyIds = localElectionBodyDAO.getMuncipalitiesAndCorporationsForConstituency(tehsilIds);
											 if(localElectionBodyIds != null && localElectionBodyIds.size()>0)
											 {
												 for (Long localElectionBodyId : localElectionBodyIds) {
													List<Object[]> wardsList =  constituencyDAO.findWardsInLocalElectionBodies(localElectionBodyId.toString());
													 for (Object[] panchayat : wardsList) {
														 locationValuesList.add(panchayat[0] != null ? Long.valueOf(panchayat[0].toString().trim()):0L);
													}
												}
											 }
											 List<Object[]> panchayatLsit =  panchayatDAO.getPanchayatIdsByMandalIdsList(tehsilIds);
											 if(panchayatLsit != null && panchayatLsit.size()>0)
											 {
												 for (Object[] panchayat : panchayatLsit) {
													 locationValuesList.add(panchayat[0] != null ? Long.valueOf(panchayat[0].toString().trim()):0L);
												}
											 }
										 }
										 else if(inviteesVO.getConstituencyId() != null && inviteesVO.getConstituencyId().longValue() != 0L) // constituency selected
										 {
											 List<Long> tehsilIds = boothDAO.getTehsildByConstituency(inviteesVO.getConstituencyId(), IConstants.VOTER_DATA_PUBLICATION_ID);
											 List<Long> localElectionBodyIds = localElectionBodyDAO.getMuncipalitiesAndCorporationsForConstituency(tehsilIds);
											 if(localElectionBodyIds != null && localElectionBodyIds.size()>0)
											 {
												 for (Long localElectionBodyId : localElectionBodyIds) {
													List<Object[]> wardsList =  constituencyDAO.findWardsInLocalElectionBodies(localElectionBodyId.toString());
													 for (Object[] panchayat : wardsList) {
														 locationValuesList.add(panchayat[0] != null ? Long.valueOf(panchayat[0].toString().trim()):0L);
													}
												}
											 }
											
											 List<Object[]> panchayatLsit =  panchayatDAO.getPanchayatsByConstituencyId(inviteesVO.getConstituencyId());
											 if(panchayatLsit != null && panchayatLsit.size()>0)
											 {
												 for (Object[] panchayat : panchayatLsit) {
													 locationValuesList.add(panchayat[0] != null ? Long.valueOf(panchayat[0].toString().trim()):0L);
												}
											 }
										 }
										 else if(inviteesVO.getDistrictId() != null && inviteesVO.getDistrictId().longValue() == 0L) // district not selected
										 {
											 if(stateId != null && stateId.longValue() == 0L || stateId.longValue() == 2L )
											 {
												 disstrictIds.add(1L);disstrictIds.add(2L);disstrictIds.add(3L);disstrictIds.add(4L);disstrictIds.add(5L);disstrictIds.add(6L);disstrictIds.add(7L);disstrictIds.add(8L);disstrictIds.add(9L);disstrictIds.add(10L);
											 }
											 if(stateId != null && stateId.longValue() == 0L || stateId.longValue() == 1L )
											 {
												 disstrictIds.add(11L);disstrictIds.add(12L);disstrictIds.add(13L);disstrictIds.add(14L);disstrictIds.add(15L);disstrictIds.add(16L);disstrictIds.add(17L);disstrictIds.add(18L);disstrictIds.add(19L);disstrictIds.add(20L);
												 disstrictIds.add(21L);disstrictIds.add(22L);disstrictIds.add(23L);
											 }
										 }
										 else  if(inviteesVO.getDistrictId() != null && inviteesVO.getDistrictId().longValue() != 0L) // district selected
										 {
											 disstrictIds.add(inviteesVO.getDistrictId());
										 }
										 
										 tdpCadresList = tdpCommitteeMemberDAO.getCommiteeMembersDetailsByPostionsAndCommiteeLevel(locationLevelIdsList,locationValuesList,inviteesVO.getCommitteeId(),inviteesVO.getRolesIds(),disstrictIds,null,null);
										 if(tdpCadresList != null && tdpCadresList.size()>0)
										 {
											 Long count = 0L;
											 for (Object[] cadre : tdpCadresList) {
												Long cadreId = cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L;
												String positiion = cadre[1] != null ? cadre[1].toString().trim():null;											
												tdpCadreIdsList.add(cadreId);
												
												 if(startIndex == 0)
													{
														 if(compareCadreIds != null && compareCadreIds.size()>0)
														 {
															// for (Long candidateId : compareCadreIds) 
															// {
																if(!compareCadreIds.contains(cadreId))
																{
																	if(villagePositonsCountMap.get(positiion) != null)
																	{
																		count = villagePositonsCountMap.get(positiion);
																	}
																	count = count+1;
																	villagePositonsCountMap.put(positiion,count);
																}
															// }
														 }
														 else
														 {
															 if(villagePositonsCountMap.get(positiion) != null)
																{
																	count = villagePositonsCountMap.get(positiion);
																}
																count = count+1;
																villagePositonsCountMap.put(positiion,count);
														 }
													}
											 }
											 compareCadreIds.addAll(tdpCadreIdsList);
										 }
									 }
								 }
							 }								
						 }					 
					 }
				}
					
				
				
				if((tdpCadreIdsList != null && tdpCadreIdsList.size()>0) || (publicRepresentativesIds != null && publicRepresentativesIds.size()>0))
				{

					List<Long> finalCadreIDsList = new ArrayList<Long>();
					List<Object[]> tdpCadreIdssList = new ArrayList<Object[]>();
				
					returnList = new ArrayList<TdpCadreVO>();
					List<Long> tdpCadresIdList = new ArrayList<Long>();
					
					
					List<Long> publicRepresentativesIdList = new ArrayList<Long>();
					
										
					if(publicRepresentativesIds != null && publicRepresentativesIds.size()>0)
					{
						 totalCount = totalCount+(Long.valueOf(publicRepresentativesIds.size()));
						 publicRepresentativesIdList.addAll(publicRepresentativesIds);
					}
					if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
					{
						 totalCount = totalCount+(Long.valueOf(tdpCadreIdsList.size()));
						 tdpCadresIdList.addAll(tdpCadreIdsList);
					}
					
					Map<Long,List<IdNameVO>> parliamentInfoMap = new HashMap<Long, List<IdNameVO>>();
					 Map<Long,List<IdNameVO>> assemblyInfoMap = new HashMap<Long, List<IdNameVO>>();
					 Map<Long,List<IdNameVO>> mandalsListMap = new HashMap<Long, List<IdNameVO>>();
					 Map<Long,List<IdNameVO>> districtsListMap = new HashMap<Long, List<IdNameVO>>();
					 Map<Long,List<IdNameVO>> localbodyListMap = new HashMap<Long, List<IdNameVO>>();
					
					 
					 List<Object[]> constiNoList = delimitationConstituencyDAO.getConstiNoByConstiId(2009L);
					 Map<Long,Long> constiNoMap = new LinkedHashMap<Long,Long>(0);
							 
					 if(constiNoList != null && constiNoList.size()>0)
					 {
						 for(Object[] data : constiNoList)
						 {
							 constiNoMap.put(data[0] != null ? Long.valueOf(data[0].toString().trim()):0L, data[1] != null ? Long.valueOf(data[1].toString().trim()):0L);
						 }
					 }
					 List<Object[]> parliaments = delimitationConstituencyAssemblyDetailsDAO.getLatestParliamentByStateIdForregion("Parliament", 1L, region);
					 if(parliaments != null && parliaments.size()>0)
					 {
						 for (Object[] parliament : parliaments) {
							 Long parliamentId = parliament[0] != null ? Long.valueOf(parliament[0].toString().trim()):0L;
							 List<IdNameVO> parliamentInfoList = new ArrayList<IdNameVO>();
							 if(parliamentInfoMap.get(parliamentId) != null)
							 {
								 parliamentInfoList = parliamentInfoMap.get(parliamentId);
							 }
							 Long constituencyNo = constiNoMap.get(parliamentId);//delimitationConstituencyDAO.getConstituencyNo(parliamentId,2009L);
							 IdNameVO parliamentVO = new IdNameVO();
							 parliamentVO.setId(parliamentId);
							 parliamentVO.setName(parliament[1] != null ?parliament[1].toString().trim():"");
							 parliamentVO.setDistrictid(parliament[3] != null ? Long.valueOf(parliament[3].toString().trim()):0L);
							 if(constituencyNo != null)
							 { 
								 parliamentVO.setName(constituencyNo+"_"+parliamentVO.getName());
							 }
							 parliamentInfoList.add(parliamentVO);
							 IdNameVO districtVO = new IdNameVO();
							 districtVO.setId(parliamentId);
							 districtVO.setName(parliament[2] != null ? parliament[2].toString().trim():"");
							 districtVO.setDistrictid(parliament[3] != null ? Long.valueOf(parliament[3].toString().trim()):0L);
							 parliamentInfoList.add(districtVO);
							 
							 parliamentInfoMap.put(parliamentId, parliamentInfoList);
							 
						}
					 }
					 Set<IdNameVO> districtsSets= new HashSet<IdNameVO>(0);
					 List<Object[]> assemblys = constituencyDAO.getLatestConstituenciesByStateIdForregion("Assembly", 1L, region,null,null);
					 if(assemblys != null && assemblys.size()>0)
					 {
						 for (Object[] assembly : assemblys) {
							 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
							 Long constituencyNo =  constiNoMap.get(assemblyId);;//delimitationConstituencyDAO.getConstituencyNo(assemblyId,2009L);
							 List<IdNameVO> assemblyInfoList = new ArrayList<IdNameVO>();
							 if(assemblyInfoMap.get(assemblyId) != null)
							 {
								 assemblyInfoList = assemblyInfoMap.get(assemblyId);
							 }
							 
							 IdNameVO parliamentVO = new IdNameVO();
							 parliamentVO.setId(assemblyId);
							 parliamentVO.setName(assembly[1] != null ? assembly[1].toString().trim():"");
							 parliamentVO.setDistrictid(assembly[3] != null ? Long.valueOf(assembly[3].toString().trim()):0L);
							 if(constituencyNo != null)
							 { 
								 parliamentVO.setName(constituencyNo+"_"+parliamentVO.getName());
							 }
							 assemblyInfoList.add(parliamentVO);
							 
							 IdNameVO districtVO = new IdNameVO();
							 districtVO.setId(assemblyId);
							 districtVO.setName(assembly[2] != null ? assembly[2].toString().trim():"");
							 districtVO.setDistrictid(assembly[3] != null ? Long.valueOf(assembly[3].toString().trim()):0L);
							 assemblyInfoList.add(districtVO);
							 
							 assemblyInfoMap.put(assemblyId, assemblyInfoList);
							 
							 List<IdNameVO> districtsList = new ArrayList<IdNameVO>(0);
							 IdNameVO distVO = new IdNameVO();
							 distVO.setId(assembly[3] != null ? Long.valueOf(assembly[3].toString().trim()):0L);
							 distVO.setName(assembly[2] != null ? assembly[2].toString().trim():"");
							 districtsList.add(distVO);
							 districtsSets.add(distVO);
							 districtsListMap.put(distVO.getId(), districtsList);
						}
					 }
					 
					 List<Object[]> localBodysListt = localElectionBodyDAO.getAllLocalBodysByState(1L);
					 if(localBodysListt != null && localBodysListt.size()>0)
					 {
						 for (Object[] lcoalbody : localBodysListt) {
							 
							 Long localbodyId = lcoalbody[0] != null ? Long.valueOf(lcoalbody[0].toString().trim()):0L;
							// Long constituencyNo =  constiNoMap.get(assemblyId);;//delimitationConstituencyDAO.getConstituencyNo(assemblyId,2009L);
							 List<IdNameVO> assemblyInfoList = new ArrayList<IdNameVO>();
							 
							 IdNameVO localbodyVO = new IdNameVO();
							 localbodyVO.setId(localbodyId);
							 localbodyVO.setName(lcoalbody[1] != null ? lcoalbody[1].toString().trim()+" "+ lcoalbody[2] != null?lcoalbody[1].toString():"":"");
							 localbodyVO.setDistrictid(lcoalbody[3] != null ? Long.valueOf(lcoalbody[3].toString().trim()):0L);
							
							 assemblyInfoList.add(localbodyVO);
							 
							 IdNameVO districtVO = new IdNameVO();
							 districtVO.setId(localbodyId);
							 districtVO.setName(lcoalbody[4] != null ? lcoalbody[4].toString().trim():"");
							 districtVO.setDistrictid(lcoalbody[3] != null ? Long.valueOf(lcoalbody[3].toString().trim()):0L);
							 assemblyInfoList.add(districtVO);
							 
							 localbodyListMap.put(localbodyId, assemblyInfoList);
						}
					 }
					 
					 List<Integer> availableMPTCZPTCLocations = publicRepresentativeDAO.getRepresentativesByPositions(null,new ArrayList<Long>(0),IConstants.MPTC_ELCTION_TYPE_ID); // MPTC,ZPTC,MPP Locations
					 List<Long> areaIdsList = new ArrayList<Long>(0);
					 if(availableMPTCZPTCLocations !=null && availableMPTCZPTCLocations.size()>0)
					 {
						 for (Integer locationId : availableMPTCZPTCLocations) {
							 areaIdsList.add(Long.valueOf(String.valueOf(locationId)));
						}
					 }
					List<Long> tehsilIdsList =  delimitationConstituencyMandalDAO.getStateWideDelimitationConstituencyIdsList(1L);
					 List<Object[]> MPTCZPTCAreaList = constituencyDAO.getMPTCZPTCLocationAreaDetails(areaIdsList,tehsilIdsList);
					 
					 if(MPTCZPTCAreaList != null && MPTCZPTCAreaList.size()>0)
					 {
						 for (Object[] assembly : MPTCZPTCAreaList) {
							 Long assemblyId = assembly[0] != null ? Long.valueOf(assembly[0].toString().trim()):0L;
							 Long constituencyNo =  constiNoMap.get(assemblyId);//delimitationConstituencyDAO.getConstituencyNo(assemblyId,2009L);
							 List<IdNameVO> assemblyInfoList = new ArrayList<IdNameVO>();
							 if(assemblyInfoMap.get(assembly[6] != null ? Long.valueOf(assembly[6].toString().trim()):0L) != null)
							 {
								 assemblyInfoList = assemblyInfoMap.get(assembly[6] != null ? Long.valueOf(assembly[6].toString().trim()):0L);
							 }
							 
							 IdNameVO assemblyVO = new IdNameVO();
							 assemblyVO.setId(assemblyId);
							 assemblyVO.setName(assembly[1] != null ? assembly[1].toString().trim():"");
							 assemblyVO.setDistrictid(assembly[2] != null ? Long.valueOf(assembly[2].toString().trim()):0L);
							 if(constituencyNo != null)
							 { 
								 assemblyVO.setName(constituencyNo+"_"+assemblyVO.getName());
							 }
							 assemblyInfoList.add(assemblyVO);
							 
							 IdNameVO districtVO = new IdNameVO();
							 districtVO.setId(assembly[2] != null ? Long.valueOf(assembly[2].toString().trim()):0L);
							 districtVO.setName(assembly[3] != null ? assembly[3].toString().trim():"");
							 districtVO.setDistrictid(assembly[2] != null ? Long.valueOf(assembly[2].toString().trim()):0L);
							 assemblyInfoList.add(districtVO);
							 
							 IdNameVO mandalVO = new IdNameVO();
							 mandalVO.setId(assembly[4] != null ? Long.valueOf(assembly[4].toString().trim()):0L);
							 mandalVO.setName(assembly[5] != null ? assembly[5].toString().trim():"");
							 mandalVO.setDistrictid(assembly[2] != null ? Long.valueOf(assembly[2].toString().trim()):0L);
							 assemblyInfoList.add(mandalVO);
							 
							 mandalsListMap.put(assembly[6] != null ? Long.valueOf(assembly[6].toString().trim()):0L, assemblyInfoList);
						}
					 }
					 
					 
					if(reportType != null && reportType.trim().equalsIgnoreCase("EXPORTEXCEL"))
					{
						return exprortExcelForInvitees(publicRepresentativesIdList,tdpCadresIdList,parliamentInfoMap,assemblyInfoMap,mandalsListMap);
					}
					
					Map<Long,String> committeeMap = new LinkedHashMap<Long, String>();
					List<Object[]> basicCommitteList = tdpBasicCommitteeDAO.getBasicCommittees();
					if(basicCommitteList != null && basicCommitteList.size()>0)
					{
						for (Object[] committee : basicCommitteList) {
							Long id = committee[0] != null ? Long.valueOf(committee[0].toString().trim()):0L;
							String name = committee[1] != null ? committee[1].toString().trim():"";
							if(name != null && !name.isEmpty())
							{
								committeeMap.put(id, name);
							}
						}
					}
					
					//addtoevent
					if(actionType != null && actionType.trim().equalsIgnoreCase("invite"))
					{
						
						Long count = addToEventDetails(userId,eventId,publicRepresentativesIdList,tdpCadresIdList);
						
						
						
						TdpCadreVO finalCadreVO = new TdpCadreVO();
						if(count == null){
							finalCadreVO.setResponseCode("1");
							finalCadreVO.setResponseStatus("error");
						}
						else
						{
							finalCadreVO.setResponseCode("0");
							finalCadreVO.setResponseStatus("success");
							finalCadreVO.setTotalCount(count);
						}
						//finalCadreVO.setTotalCount(totalCount);
						returnList.add(finalCadreVO);	
					}
					else
					{
						int maxCount = 0;
						List<Long> existingCandidates = new ArrayList<Long>();
						if(publicRepresentativesIdList != null && publicRepresentativesIdList.size()>0)
						{
							 if(publicRepresentativesIdList.size()>100)
							 {
								 int count =0;
								 for( int i=startIndex ; i<publicRepresentativesIdList.size();i++)
								 {
									 try {
										 finalCadreIDsList.add(publicRepresentativesIdList.get(i));
										 count = count+1;
										 maxCount = maxCount+1;
										 if(maxIndex == count)
										 {
											 break;
										 }
									} catch (Exception e) {}
								 }
							 }
							 else
							 {
								 finalCadreIDsList.addAll(publicRepresentativesIdList);
							 }
							 
							 if(finalCadreIDsList != null && finalCadreIDsList.size()>0)
							 {
								 //0.tdpCadreId, 1.candidateId,membershipId -2,voterId-3
								 tdpCadreIdssList = tdpCadreCandidateDAO.getTdpCadreCandidateIds(finalCadreIDsList);
								 List<Object[]> representativeDetails = publicRepresentativeDAO.getCandidateInfoByCandidateIds(finalCadreIDsList);
									
									if(representativeDetails != null && representativeDetails.size()>0)
									{
										for (Object[] cadre : representativeDetails) {
											Long id = cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L;
											if(!existingCandidates.contains(id))
											{
												existingCandidates.add(id);
												CadreCommitteeVO committeeVO = new CadreCommitteeVO();
												
												Long tdpCadreCommitteeId = 0L;
												
												committeeVO.setTdpCadreId(id);
												committeeVO.setCadreName(cadre[1] != null ? cadre[1].toString().trim():"");
												committeeVO.setMobileNo(cadre[2] != null ? cadre[2].toString().trim():"");
												committeeVO.setGender(cadre[3] != null ? cadre[3].toString().trim():"");
												committeeVO.setMobileType(cadre[4] != null ? cadre[4].toString():"");
												
												if(tdpCadreIdssList != null && tdpCadreIdssList.size()>0)
												{
													for (Object[] tdpCadre : tdpCadreIdssList)
													{
														Long tdpCadreId = tdpCadre[1] != null ? Long.valueOf(tdpCadre[1].toString().trim()):0L;//candidateId
														if(id.longValue() == tdpCadreId.longValue())
														{
															tdpCadreCommitteeId = tdpCadre[0] != null ? Long.valueOf(tdpCadre[0].toString().trim()):0L;
															committeeVO.setTdpCadreCommitteeId(tdpCadreCommitteeId);
															committeeVO.setMemberShipCardId(tdpCadre[2] != null ? tdpCadre[2].toString().trim():"");
															committeeVO.setVoterCardNo(tdpCadre[3] != null ? tdpCadre[3].toString().trim():"");
															
														}
													}
												}
												
												committeeVO.setType("PublicRepresentative");
												Long levelValue = cadre[5] != null ? Long.valueOf(cadre[5].toString()):0L;
												if(committeeVO.getMobileType() != null && ( committeeVO.getMobileType().trim().equalsIgnoreCase("MP") ||
														 committeeVO.getMobileType().trim().equalsIgnoreCase("EX MP") || committeeVO.getMobileType().trim().equalsIgnoreCase("2014 PARLIAMENT CONTESTED"))  )
												{
													List<IdNameVO> parliamentList = parliamentInfoMap.get(levelValue);
													if(parliamentList != null && parliamentList.size()>0)
													{
														IdNameVO vo1 = parliamentList.get(0);
														committeeVO.setConstituency(vo1.getName()+" Parliament");
														
														IdNameVO vo2 = parliamentList.get(1);
														committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
													}
												}
												else if(committeeVO.getMobileType() != null && (committeeVO.getMobileType().trim().equalsIgnoreCase("MLA") || 
														committeeVO.getMobileType().trim().equalsIgnoreCase("EX MLA") ||
														committeeVO.getMobileType().trim().equalsIgnoreCase("2014 AP STATE MINISTERS") ||
														committeeVO.getMobileType().trim().equalsIgnoreCase("2014 ASSEMBLY CONTESTED") ||
														committeeVO.getMobileType().trim().equalsIgnoreCase("CONSTITUENCY INCHARGE") ||
														committeeVO.getMobileType().trim().equalsIgnoreCase("3-MEN COMMITTEE")))
												{
													List<IdNameVO> assemblyList = assemblyInfoMap.get(levelValue);
													if(assemblyList != null && assemblyList.size()>0)
													{
														IdNameVO vo1 = assemblyList.get(0);
														committeeVO.setConstituency(vo1.getName()+" Assembly");
														
														IdNameVO vo2 = assemblyList.get(1);
														committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
													}
												}
												else if(committeeVO.getMobileType() != null && (
														committeeVO.getMobileType().trim().equalsIgnoreCase("ZPTC") || 
														committeeVO.getMobileType().trim().equalsIgnoreCase("MPTC") || 
														committeeVO.getMobileType().trim().equalsIgnoreCase("MPP") ||
														committeeVO.getMobileType().trim().equalsIgnoreCase("VICE MPP")))
												{
													List<IdNameVO> assemblyList = mandalsListMap.get(levelValue);
													if(assemblyList != null && assemblyList.size()>0)
													{
														IdNameVO vo1 = assemblyList.get(0);
														committeeVO.setConstituency(vo1.getName()+" Assembly");
														
														IdNameVO vo2 = assemblyList.get(1);
														committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
														if(assemblyList.size()>2){
															IdNameVO vo3 = assemblyList.get(2);
															committeeVO.setTehsil(vo3.getId()+"_"+vo3.getName());
														}
													}
												}
												else if(committeeVO.getMobileType() != null && (
														committeeVO.getMobileType().trim().equalsIgnoreCase("MUNCIPAL CHAIRMAN") || 
														committeeVO.getMobileType().trim().equalsIgnoreCase("MUNCIPAL CHAIRPERSON") || 
														committeeVO.getMobileType().trim().equalsIgnoreCase("MAYOR") ||
														committeeVO.getMobileType().trim().equalsIgnoreCase("DEPUTY MAYOR")))
												{
													List<IdNameVO> localbodyList = localbodyListMap.get(levelValue);
													if(localbodyList != null && localbodyList.size()>0)
													{
														IdNameVO vo1 = localbodyList.get(0);
														committeeVO.setTehsil(vo1.getId()+"_"+vo1.getName());
														
														IdNameVO vo2 = localbodyList.get(1);
														committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
														
														/*if(localbodyListMap.size()>2){
															IdNameVO vo3 = localbodyList.get(2);
															committeeVO.setConstituency(vo3.getName()+" Assembly ");
														}*/
													}
												}
												else if(committeeVO.getMobileType() != null && (
														committeeVO.getMobileType().trim().equalsIgnoreCase("MLC") || 
														committeeVO.getMobileType().trim().equalsIgnoreCase("ZP VICE CHAIRMAN") || 
														committeeVO.getMobileType().trim().equalsIgnoreCase("ZP CHAIRMAN") ) )
												{
													//districtsListMap
													List<IdNameVO> distrtictsList = districtsListMap.get(levelValue);
													IdNameVO vo1 = distrtictsList.get(0);
													committeeVO.setConstituency(vo1.getName()+" District");
													committeeVO.setAddress(vo1.getId()+"_"+vo1.getName());
												}
												else if(committeeVO.getMobileType() != null && (
														committeeVO.getMobileType().trim().equalsIgnoreCase("MP (RAJYA SABHA)") || 
														committeeVO.getMobileType().trim().equalsIgnoreCase("EX STATE MINISTER") ))
												{
													//districtsListMap
													State stateObj = stateDAO.get(levelValue);
													committeeVO.setConstituency(stateObj.getStateName());
													committeeVO.setAddress(stateObj.getStateId()+"_"+stateObj.getStateName());
												}
												cadreCommitteeList.add(committeeVO);
										
											}
											else
											{
												CadreCommitteeVO existingVO = getMatchedCommitteeVO(cadreCommitteeList,id);
												if(existingVO != null)
												{
													if(existingVO.getMobileType().trim().contains("EX"))
													{
														if(existingVO.getMobileType().trim().contains("EX MLA"))
														{
															String candidateType = cadre[4] != null ? cadre[4].toString():"";
															if(candidateType != null && !candidateType.trim().isEmpty() && !candidateType.trim().equalsIgnoreCase(existingVO.getMobileType().trim()) && 
																	candidateType.trim().equalsIgnoreCase("EX MP") || candidateType.trim().equalsIgnoreCase("2014 PARLIAMENT CONTESTED"))															
															{
																CadreCommitteeVO committeeVO = new CadreCommitteeVO();
																
																committeeVO.setTdpCadreId(id);
																committeeVO.setCadreName(cadre[1] != null ? cadre[1].toString().trim():"");
																committeeVO.setMobileNo(cadre[2] != null ? cadre[2].toString().trim():"");
																committeeVO.setGender(cadre[3] != null ? cadre[3].toString().trim():"");
																committeeVO.setMobileType(cadre[4] != null ? cadre[4].toString():"");
																
																committeeVO.setType("PublicRepresentative");
																Long levelValue = cadre[5] != null ? Long.valueOf(cadre[5].toString()):0L;
																if(committeeVO.getMobileType() != null && ( committeeVO.getMobileType().trim().equalsIgnoreCase("MP") ||
																		 committeeVO.getMobileType().trim().equalsIgnoreCase("EX MP") || committeeVO.getMobileType().trim().equalsIgnoreCase("2014 PARLIAMENT CONTESTED")) )
																{
																	List<IdNameVO> parliamentList = parliamentInfoMap.get(levelValue);
																	if(parliamentList != null && parliamentList.size()>0)
																	{
																		IdNameVO vo1 = parliamentList.get(0);
																		committeeVO.setConstituency(vo1.getName()+" Parliament");
																		
																		IdNameVO vo2 = parliamentList.get(1);
																		committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
																	}
																}
																else if(committeeVO.getMobileType() != null && (committeeVO.getMobileType().trim().equalsIgnoreCase("MLA") || 
																		committeeVO.getMobileType().trim().equalsIgnoreCase("EX MLA") ||
																		committeeVO.getMobileType().trim().equalsIgnoreCase("2014 AP STATE MINISTERS")||
																		committeeVO.getMobileType().trim().equalsIgnoreCase("2014 ASSEMBLY CONTESTED") ||
																		committeeVO.getMobileType().trim().equalsIgnoreCase("CONSTITUENCY INCHARGE") ||
																		committeeVO.getMobileType().trim().equalsIgnoreCase("3-MEN COMMITTEE") ))
																{
																	List<IdNameVO> assemblyList = assemblyInfoMap.get(levelValue);
																	if(assemblyList != null && assemblyList.size()>0)
																	{
																		IdNameVO vo1 = assemblyList.get(0);
																		committeeVO.setConstituency(vo1.getName()+" Assembly");
																		
																		IdNameVO vo2 = assemblyList.get(1);
																		committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
																	}
																}
																else if(committeeVO.getMobileType() != null && (
																		committeeVO.getMobileType().trim().equalsIgnoreCase("ZPTC") || 
																		committeeVO.getMobileType().trim().equalsIgnoreCase("MPTC") || 
																		committeeVO.getMobileType().trim().equalsIgnoreCase("MPP") ||
																		committeeVO.getMobileType().trim().equalsIgnoreCase("VICE MPP") ))
																{
																	List<IdNameVO> assemblyList = mandalsListMap.get(levelValue);
																	if(assemblyList != null && assemblyList.size()>0)
																	{
																		IdNameVO vo1 = assemblyList.get(0);
																		committeeVO.setConstituency(vo1.getName()+" Assembly");
																		
																		IdNameVO vo2 = assemblyList.get(1);
																		committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
																		if(assemblyList.size()>2){
																			IdNameVO vo3 = assemblyList.get(2);
																			committeeVO.setTehsil(vo3.getId()+"_"+vo3.getName());
																		}
																	}
																}
																else if(committeeVO.getMobileType() != null && (
																		committeeVO.getMobileType().trim().equalsIgnoreCase("MUNCIPAL CHAIRMAN") || 
																		committeeVO.getMobileType().trim().equalsIgnoreCase("MUNCIPAL CHAIRPERSON") || 
																		committeeVO.getMobileType().trim().equalsIgnoreCase("MAYOR") ||
																		committeeVO.getMobileType().trim().equalsIgnoreCase("DEPUTY MAYOR")))
																{
																	List<IdNameVO> localbodyList = localbodyListMap.get(levelValue);
																	if(localbodyList != null && localbodyList.size()>0)
																	{
																		IdNameVO vo1 = localbodyList.get(0);
																		committeeVO.setTehsil(vo1.getId()+"_"+vo1.getName());
																		
																		IdNameVO vo2 = localbodyList.get(1);
																		committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
																		
																		/*if(localbodyListMap.size()>2){
																			IdNameVO vo3 = localbodyList.get(2);
																			committeeVO.setConstituency(vo3.getName()+" Assembly ");
																		}*/
																	}
																}
																else if(committeeVO.getMobileType() != null && (
																		committeeVO.getMobileType().trim().equalsIgnoreCase("MP (RAJYA SABHA)") || 
																		committeeVO.getMobileType().trim().equalsIgnoreCase("EX STATE MINISTER") ))
																{
																	//districtsListMap
																	State stateObj = stateDAO.get(levelValue);
																	committeeVO.setConstituency(stateObj.getStateName());
																	committeeVO.setAddress(stateObj.getStateId()+"_"+stateObj.getStateName());
																}
																else if(committeeVO.getMobileType() != null && (
																		committeeVO.getMobileType().trim().equalsIgnoreCase("MLC") || 
																		committeeVO.getMobileType().trim().equalsIgnoreCase("ZP VICE CHAIRMAN") || 
																		committeeVO.getMobileType().trim().equalsIgnoreCase("ZP CHAIRMAN") ) )
																{
																	//districtsListMap
																	List<IdNameVO> distrtictsList = districtsListMap.get(levelValue);
																	IdNameVO vo1 = distrtictsList.get(0);
																	committeeVO.setConstituency(vo1.getName()+" District");
																	committeeVO.setAddress(vo1.getId()+"_"+vo1.getName());
																}
																cadreCommitteeList.add(committeeVO);
															}
															else
															{

																//CadreCommitteeVO committeeVO = new CadreCommitteeVO();
																
																existingVO.setTdpCadreId(id);
																existingVO.setCadreName(cadre[1] != null ? cadre[1].toString().trim():"");
																existingVO.setMobileNo(cadre[2] != null ? cadre[2].toString().trim():"");
																existingVO.setGender(cadre[3] != null ? cadre[3].toString().trim():"");
																//existingVO.setMobileType(cadre[4] != null ? cadre[4].toString():"");
																
																existingVO.setType("PublicRepresentative");
																Long levelValue = cadre[5] != null ? Long.valueOf(cadre[5].toString()):0L;
																if(existingVO.getMobileType() != null && ( existingVO.getMobileType().trim().equalsIgnoreCase("MP") ||
																		existingVO.getMobileType().trim().equalsIgnoreCase("EX MP") || existingVO.getMobileType().trim().equalsIgnoreCase("2014 PARLIAMENT CONTESTED")) )
																{
																	List<IdNameVO> parliamentList = parliamentInfoMap.get(levelValue);
																	if(parliamentList != null && parliamentList.size()>0)
																	{
																		IdNameVO vo1 = parliamentList.get(0);
																		existingVO.setConstituency(vo1.getName()+" Parliament");
																		
																		IdNameVO vo2 = parliamentList.get(1);
																		existingVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
																	}
																}
																else if(existingVO.getMobileType() != null && (existingVO.getMobileType().trim().equalsIgnoreCase("MLA") || 
																		existingVO.getMobileType().trim().equalsIgnoreCase("EX MLA") ||
																		existingVO.getMobileType().trim().equalsIgnoreCase("2014 AP STATE MINISTERS") ||
																		existingVO.getMobileType().trim().equalsIgnoreCase("2014 ASSEMBLY CONTESTED") ||
																		existingVO.getMobileType().trim().equalsIgnoreCase("CONSTITUENCY INCHARGE") ||
																		existingVO.getMobileType().trim().equalsIgnoreCase("3-MEN COMMITTEE") ))
																{
																	List<IdNameVO> assemblyList = assemblyInfoMap.get(levelValue);
																	if(assemblyList != null && assemblyList.size()>0)
																	{
																		IdNameVO vo1 = assemblyList.get(0);
																		existingVO.setConstituency(vo1.getName()+" Assembly");
																		
																		IdNameVO vo2 = assemblyList.get(1);
																		existingVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
																	}
																}
																else if(existingVO.getMobileType() != null && (
																		existingVO.getMobileType().trim().equalsIgnoreCase("ZPTC") || 
																		existingVO.getMobileType().trim().equalsIgnoreCase("MPTC") || 
																		existingVO.getMobileType().trim().equalsIgnoreCase("MPP")  ||
																		existingVO.getMobileType().trim().equalsIgnoreCase("VICE MPP")))
																{
																	List<IdNameVO> assemblyList = mandalsListMap.get(levelValue);
																	if(assemblyList != null && assemblyList.size()>0)
																	{
																		IdNameVO vo1 = assemblyList.get(0);
																		existingVO.setConstituency(vo1.getName()+" Assembly");
																		
																		IdNameVO vo2 = assemblyList.get(1);
																		existingVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
																		if(assemblyList.size()>2){
																			IdNameVO vo3 = assemblyList.get(2);
																			existingVO.setTehsil(vo3.getId()+"_"+vo3.getName());
																		}
																	}
																}
																else if(existingVO.getMobileType() != null && (
																		existingVO.getMobileType().trim().equalsIgnoreCase("MUNCIPAL CHAIRMAN") || 
																		existingVO.getMobileType().trim().equalsIgnoreCase("MUNCIPAL CHAIRPERSON") || 
																		existingVO.getMobileType().trim().equalsIgnoreCase("MAYOR") ||
																		existingVO.getMobileType().trim().equalsIgnoreCase("DEPUTY MAYOR")))
																{
																	List<IdNameVO> localbodyList = localbodyListMap.get(levelValue);
																	if(localbodyList != null && localbodyList.size()>0)
																	{
																		IdNameVO vo1 = localbodyList.get(0);
																		existingVO.setTehsil(vo1.getId()+"_"+vo1.getName());
																		
																		IdNameVO vo2 = localbodyList.get(1);
																		existingVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
																		
																		/*if(localbodyListMap.size()>2){
																			IdNameVO vo3 = localbodyList.get(2);
																			existingVO.setConstituency(vo3.getName()+" Assembly ");
																		}*/
																	}
																}
																else if(existingVO.getMobileType() != null && (
																		existingVO.getMobileType().trim().equalsIgnoreCase("MLC") || 
																		existingVO.getMobileType().trim().equalsIgnoreCase("ZP VICE CHAIRMAN") || 
																		existingVO.getMobileType().trim().equalsIgnoreCase("ZP CHAIRMAN") ) )
																{
																	//districtsListMap
																	List<IdNameVO> distrtictsList = districtsListMap.get(levelValue);
																	IdNameVO vo1 = distrtictsList.get(0);
																	existingVO.setConstituency(vo1.getName()+" District");
																	existingVO.setAddress(vo1.getId()+"_"+vo1.getName());
																}
																else if(existingVO.getMobileType() != null && (
																		existingVO.getMobileType().trim().equalsIgnoreCase("MP (RAJYA SABHA)") || 
																		existingVO.getMobileType().trim().equalsIgnoreCase("EX STATE MINISTER") ))
																{
																	//districtsListMap
																	State stateObj = stateDAO.get(levelValue);
																	existingVO.setConstituency(stateObj.getStateName());
																	existingVO.setAddress(stateObj.getStateId()+"_"+stateObj.getStateName());
																}
																//cadreCommitteeList.add(committeeVO);
															
																existingVO.setMobileType(candidateType);
															}
														} 													
														else if(existingVO.getMobileType().trim().contains("EX MP"))
														{
															String candidateType = cadre[4] != null ? cadre[4].toString():"";
															if(candidateType != null && !candidateType.trim().isEmpty() && !candidateType.trim().equalsIgnoreCase(existingVO.getMobileType().trim()) && 
																	candidateType.trim().equalsIgnoreCase("EX MLA") )															
															{
																CadreCommitteeVO committeeVO = new CadreCommitteeVO();
																
																committeeVO.setTdpCadreId(id);
																committeeVO.setCadreName(cadre[1] != null ? cadre[1].toString().trim():"");
																committeeVO.setMobileNo(cadre[2] != null ? cadre[2].toString().trim():"");
																committeeVO.setGender(cadre[3] != null ? cadre[3].toString().trim():"");
																committeeVO.setMobileType(cadre[4] != null ? cadre[4].toString():"");
																
																committeeVO.setType("PublicRepresentative");
																Long levelValue = cadre[5] != null ? Long.valueOf(cadre[5].toString()):0L;
																if(committeeVO.getMobileType() != null && ( committeeVO.getMobileType().trim().equalsIgnoreCase("MP") ||
																		 committeeVO.getMobileType().trim().equalsIgnoreCase("EX MP") ||
																		 committeeVO.getMobileType().trim().equalsIgnoreCase("2014 PARLIAMENT CONTESTED")) )
																{
																	List<IdNameVO> parliamentList = parliamentInfoMap.get(levelValue);
																	if(parliamentList != null && parliamentList.size()>0)
																	{
																		IdNameVO vo1 = parliamentList.get(0);
																		committeeVO.setConstituency(vo1.getName()+" Parliament");
																		
																		IdNameVO vo2 = parliamentList.get(1);
																		committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
																	}
																}
																else if(committeeVO.getMobileType() != null && (committeeVO.getMobileType().trim().equalsIgnoreCase("MLA") || 
																		committeeVO.getMobileType().trim().equalsIgnoreCase("EX MLA") ||
																		committeeVO.getMobileType().trim().equalsIgnoreCase("2014 AP STATE MINISTERS") ||
																		committeeVO.getMobileType().trim().equalsIgnoreCase("2014 ASSEMBLY CONTESTED") ||
																		committeeVO.getMobileType().trim().equalsIgnoreCase("CONSTITUENCY INCHARGE") ||
																		committeeVO.getMobileType().trim().equalsIgnoreCase("3-MEN COMMITTEE")))
																{
																	List<IdNameVO> assemblyList = assemblyInfoMap.get(levelValue);
																	if(assemblyList != null && assemblyList.size()>0)
																	{
																		IdNameVO vo1 = assemblyList.get(0);
																		committeeVO.setConstituency(vo1.getName()+" Assembly");
																		
																		IdNameVO vo2 = assemblyList.get(1);
																		committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
																	}
																}
																else if(committeeVO.getMobileType() != null && (
																		committeeVO.getMobileType().trim().equalsIgnoreCase("ZPTC") || 
																		committeeVO.getMobileType().trim().equalsIgnoreCase("MPTC") || 
																		committeeVO.getMobileType().trim().equalsIgnoreCase("MPP")  ||
																		committeeVO.getMobileType().trim().equalsIgnoreCase("VICE MPP")))
																{
																	List<IdNameVO> assemblyList = mandalsListMap.get(levelValue);
																	if(assemblyList != null && assemblyList.size()>0)
																	{
																		IdNameVO vo1 = assemblyList.get(0);
																		committeeVO.setConstituency(vo1.getName()+" Assembly");
																		
																		IdNameVO vo2 = assemblyList.get(1);
																		committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
																		if(assemblyList.size()>2){
																			IdNameVO vo3 = assemblyList.get(2);
																			committeeVO.setTehsil(vo3.getId()+"_"+vo3.getName());
																		}
																	}
																}
																else if(committeeVO.getMobileType() != null && (
																		committeeVO.getMobileType().trim().equalsIgnoreCase("MUNCIPAL CHAIRMAN") || 
																		committeeVO.getMobileType().trim().equalsIgnoreCase("MUNCIPAL CHAIRPERSON") || 
																		committeeVO.getMobileType().trim().equalsIgnoreCase("MAYOR") ||
																		committeeVO.getMobileType().trim().equalsIgnoreCase("DEPUTY MAYOR")))
																{
																	List<IdNameVO> localbodyList = localbodyListMap.get(levelValue);
																	if(localbodyList != null && localbodyList.size()>0)
																	{
																		IdNameVO vo1 = localbodyList.get(0);
																		committeeVO.setTehsil(vo1.getId()+"_"+vo1.getName());
																		
																		IdNameVO vo2 = localbodyList.get(1);
																		committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
																		
																		/*if(localbodyListMap.size()>2){
																			IdNameVO vo3 = localbodyList.get(2);
																			committeeVO.setConstituency(vo3.getName()+" Assembly ");
																		}*/
																	}
																}
																else if(committeeVO.getMobileType() != null && (
																		committeeVO.getMobileType().trim().equalsIgnoreCase("MLC") || 
																		committeeVO.getMobileType().trim().equalsIgnoreCase("ZP VICE CHAIRMAN") || 
																		committeeVO.getMobileType().trim().equalsIgnoreCase("ZP CHAIRMAN") ) )
																{
																	//districtsListMap
																	List<IdNameVO> distrtictsList = districtsListMap.get(levelValue);
																	IdNameVO vo1 = distrtictsList.get(0);
																	committeeVO.setConstituency(vo1.getName()+" District");
																	committeeVO.setAddress(vo1.getId()+"_"+vo1.getName());
																}
																else if(committeeVO.getMobileType() != null && (
																		committeeVO.getMobileType().trim().equalsIgnoreCase("MP (RAJYA SABHA)") || 
																		committeeVO.getMobileType().trim().equalsIgnoreCase("EX STATE MINISTER") ))
																{
																	//districtsListMap
																	State stateObj = stateDAO.get(levelValue);
																	committeeVO.setConstituency(stateObj.getStateName());
																	committeeVO.setAddress(stateObj.getStateId()+"_"+stateObj.getStateName());
																}
																cadreCommitteeList.add(committeeVO);
															}
															else
															{
																existingVO.setMobileType(candidateType);
															}
														}
													}													
													else
													{
														String candidateType = cadre[4] != null ? cadre[4].toString():"";
														if(!candidateType.trim().contains("EX"))
														{
															if(existingVO.getMobileType().trim().equalsIgnoreCase("MLA") || existingVO.getMobileType().trim().equalsIgnoreCase("MP"))
															{
																existingVO.setMobileType(existingVO.getMobileType()+", <br> "+candidateType);
															}
															else
															{
																existingVO.setMobileType(candidateType+",<br>"+existingVO.getMobileType());
															}
														}
													} 
												}
											}
										}
									}
									
									finalCadreIDsList.clear();
							 }
							
						}
						
						if(maxCount < 100 && tdpCadresIdList != null && tdpCadresIdList.size()>0)
						{
							 if(tdpCadresIdList.size()>100)
							 {
								 int count =0;
								 for( int i=startIndex ; i<tdpCadresIdList.size();i++)
								 {
									 try {
										 finalCadreIDsList.add(tdpCadresIdList.get(i));
										 count = count+1;
										 maxCount = maxCount+1;
										 if(maxIndex == count)
										 {
											 break;
										 }
									} catch (Exception e) {}								 
								 }
							 }
							 else
							 {
								 finalCadreIDsList.addAll(tdpCadresIdList);
							 }
							 
							 if(finalCadreIDsList != null && finalCadreIDsList.size()>0)
							 {
								 List<Object[]> tdpCadreIdDetails = tdpCadreDAO.getMobileNoByTdpCadreIdList(finalCadreIDsList,0,100);						 
							 
								 if(tdpCadreIdDetails != null && tdpCadreIdDetails.size()>0)
								 {								
										if(tdpCadreIdDetails != null && tdpCadreIdDetails.size()>0)
										{
											for (Object[] cadre : tdpCadreIdDetails) 
											{
												CadreCommitteeVO committeeVO = new CadreCommitteeVO();

												committeeVO.setTdpCadreId(cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L);
												
												committeeVO.setMemberShipCardId(cadre[4] != null ? cadre[4].toString():"");
												committeeVO.setVoterCardNo(cadre[19] != null ? cadre[19].toString():"");
												committeeVO.setCadreName(cadre[1] != null ? cadre[1].toString():"");
												committeeVO.setRelativeName(cadre[2] != null ? cadre[2].toString():"");
												committeeVO.setMobileNo(cadre[6] != null ? cadre[6].toString():"");
												//committeeVO.setCasteName(cadre[18] != null ? cadre[18].toString().trim():"");
												//committeeVO.setGender(cadre[3] != null ? cadre[3].toString():"");
												committeeVO.setImageURL(cadre[7] != null ? cadre[7].toString():"");
												committeeVO.setType("CadreCommittee");
												
												String electionType = cadre[20] != null ? cadre[20].toString().trim():""; // municipality/corporation/ghmc....
												committeeVO.setLocalElectionBody(cadre[16] != null ? cadre[16].toString().trim()+" "+electionType:"");
												
												committeeVO.setPanchayat(cadre[15] != null ? cadre[15].toString().trim():"");
												committeeVO.setTehsil(cadre[14] != null ? cadre[14].toString().trim()+" Mandal":"");
												String constiteuncyNo = cadre[35] != null ? cadre[35].toString()+"_":"";
												String districtNo = cadre[26] != null ? cadre[26].toString()+"_":"";
												committeeVO.setConstituency(cadre[11] != null ? constiteuncyNo+cadre[11].toString().trim():"");
												committeeVO.setAddress(cadre[17] != null ? districtNo+cadre[17].toString().trim():"");
												
												Long basicCommiteeID = cadre[29] != null ? Long.valueOf(cadre[29].toString()):0L;
												String committeeName = committeeMap.get(basicCommiteeID);
												String positionName =  cadre[30] != null ? cadre[30].toString():"";
												Long LocationTypeId = cadre[31] != null ? Long.valueOf(cadre[31].toString()):0L;
												Long locationValue = cadre[32] != null ? Long.valueOf(cadre[32].toString()):0L;
												Long roleId = cadre[33] != null ? Long.valueOf(String.valueOf(cadre[33]).trim()):0L ;
												
													String location = null;
													if(locationValue.longValue() > 0L){
														//System.out.println("tdpCadreId :"+id+"  \t positionName  :"+positionName);
														if(LocationTypeId.longValue() == 6L || LocationTypeId.longValue() == 8L || LocationTypeId.longValue() == 9L)
														{
															location = getLocationName(LocationTypeId,locationValue);
															committeeVO.setCommitteeLocation(location);
														}else
														{
															committeeVO.setCommitteeLocation("");
														}
														
														committeeVO.setCommitteePosition(positionName);
														if(committeeName != null)
															committeeVO.setCommitteeName(committeeName);
														committeeVO.setElectionType(cadre[34] != null ? cadre[34].toString():"");
													    if(committeeVO.getElectionType().trim().equalsIgnoreCase("Panchayat"))
													    {
													    	committeeVO.setElectionType("Village/Ward ");
													    }
													    else if(committeeVO.getElectionType().trim().equalsIgnoreCase("Mandal"))
													    {
													    	committeeVO.setElectionType("Mandal/Division/Town");
													    }
													    committeeVO.setVoterId(roleId);
												    }
													cadreCommitteeList.add(committeeVO);
											} 
										}
								 }
							 }
						}	
						
						List<TdpCadreVO> positionsVOList = null;
						if(publicRepresentsCountMap != null && publicRepresentsCountMap.size()>0)
						{
							positionsVOList = new ArrayList<TdpCadreVO>();
							for (String position : publicRepresentsCountMap.keySet()) {
								TdpCadreVO positionVO = new TdpCadreVO();
								positionVO.setCadreName(position);
								positionVO.setTotalCount(publicRepresentsCountMap.get(position));
								positionsVOList.add(positionVO);
							}
						}
						
						List<TdpCadreVO> villagePositionsVOList = null;
						if(villagePositonsCountMap != null && villagePositonsCountMap.size()>0)
						{
							villagePositionsVOList = new ArrayList<TdpCadreVO>();
							for (String position : villagePositonsCountMap.keySet()) {
								TdpCadreVO positionVO = new TdpCadreVO();
								positionVO.setCadreName(position);
								positionVO.setTotalCount(villagePositonsCountMap.get(position));
								villagePositionsVOList.add(positionVO);
							}
						}
						
						List<TdpCadreVO> mandalPositionsVOList = null;
						if(mandalPositonsCountMap != null && mandalPositonsCountMap.size()>0)
						{
							mandalPositionsVOList = new ArrayList<TdpCadreVO>();
							for (String position : mandalPositonsCountMap.keySet()) {
								TdpCadreVO positionVO = new TdpCadreVO();
								positionVO.setCadreName(position);
								positionVO.setTotalCount(mandalPositonsCountMap.get(position));
								mandalPositionsVOList.add(positionVO);
							}
						}
						
						List<TdpCadreVO> districtPositionsVOList = null;
						if(districtPositonsCountMap != null && districtPositonsCountMap.size()>0)
						{
							districtPositionsVOList = new ArrayList<TdpCadreVO>();
							for (String position : districtPositonsCountMap.keySet()) {
								TdpCadreVO positionVO = new TdpCadreVO();
								positionVO.setCadreName(position);
								positionVO.setTotalCount(districtPositonsCountMap.get(position));
								districtPositionsVOList.add(positionVO);
							}
						}
						
						List<TdpCadreVO> statePositionsVOList = null;
						if(statePositonsCountMap != null && statePositonsCountMap.size()>0)
						{
							statePositionsVOList = new ArrayList<TdpCadreVO>();
							for (String position : statePositonsCountMap.keySet()) {
								TdpCadreVO positionVO = new TdpCadreVO();
								positionVO.setCadreName(position);
								positionVO.setTotalCount(statePositonsCountMap.get(position));
								statePositionsVOList.add(positionVO);
							}
						}
						
						TdpCadreVO countVO = new TdpCadreVO();
						if(districtPositionsVOList != null && districtPositionsVOList.size()>0)
						{
							countVO.setCadreSearchList(districtPositionsVOList);//district
						}
						if(statePositionsVOList != null && statePositionsVOList.size()>0)
						{
							countVO.setTdpCadreDetailsList(statePositionsVOList); // state
						}
						if(villagePositionsVOList != null && villagePositionsVOList.size()>0)
						{
							countVO.setVoterSearchList(villagePositionsVOList); // village
						}
						
						
						
						TdpCadreVO finalCadreVO = new TdpCadreVO();
						finalCadreVO.setResponseCode("0");
						finalCadreVO.setResponseStatus("success");
						finalCadreVO.setTotalCount(totalCount);
						finalCadreVO.setCadreComitteeVOList(cadreCommitteeList);
						if(positionsVOList != null && positionsVOList.size()>0)
						{
							finalCadreVO.setCadreSearchList(positionsVOList); // public represents count details
						}
						if(mandalPositionsVOList != null && mandalPositionsVOList.size()>0)
						{
							finalCadreVO.setTdpCadreDetailsList(mandalPositionsVOList); // mandal level positions count
						}
						finalCadreVO.getVoterSearchList().add(countVO);//village, state, district level positions count
						
						returnList.add(finalCadreVO);	
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception Raised in getEventInviteesList",e);
		}
		return returnList;
	}

	public CadreCommitteeVO getMatchedCommitteeVO(List<CadreCommitteeVO> list , Long id){
		CadreCommitteeVO cadrecommitteeVO = null;
		try {
			if(list != null && list.size()>0)
			{
				for (CadreCommitteeVO vo : list) {
					if(vo.getTdpCadreId().longValue() == id.longValue())
					{
						return vo;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Raised in getMatchedCommitteeVO",e);
		}
		return cadrecommitteeVO;
	}
	 public ResultStatus sendSmsForInvitees(Long userId,List<Long> mobileNoList,String message)
	 {
		 ResultStatus status = new ResultStatus();
		 
		 try {
			 
			 String mobileNumbersStr="";
			 if(mobileNoList != null && mobileNoList.size()>0)
			 {
				 for (Long mobileNo : mobileNoList) 
				 {
					if(mobileNumbersStr.trim().length()>0)
					{
						mobileNumbersStr=mobileNumbersStr+", "+mobileNo;
					}
					else
					{
						mobileNumbersStr=""+mobileNo;
					}
				 }
				 
				 if(mobileNumbersStr != null && mobileNumbersStr.trim().length()>0 && message.trim().length()>0)
				 {
					smsCountrySmsService.sendSmsFromAdmin(message, true, mobileNumbersStr);
				 }
			 }
			 status.setResultCode(0);
			 status.setMessage("success");
			 
		} catch (Exception e) {
			 status.setResultCode(1);
			 status.setMessage("Failure");			 
			LOG.error("Exception rised in sendSmsForInvitees() while closing write operation",e);
		}
		 
		 return status;
	 }
	 
	 public List<IdNameVO> getPartyEvents(Long userId)
	 {
		 List<IdNameVO> returnList = null;
		 try {
			
			List<Object[]> eventsDtails = eventDAO.getEventsForUser(userId);
			if(eventsDtails != null && eventsDtails.size()>0)
			{
				returnList = new ArrayList<IdNameVO>();
				for (Object[] group : eventsDtails) {
					IdNameVO vo = new IdNameVO();
					
					vo.setId(group[0] != null? Long.valueOf(group[0].toString().trim()):0L);
					vo.setName(group[1] != null? group[1].toString().trim():"");
					
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception rised in getPartyEventGroups() while closing write operation",e);
		}
		 
		 return returnList;
		 
	 }
	 
	 
	 public List<IdNameVO> getPartyEventGroups(Long userId)
	 {
		 List<IdNameVO> returnList = null;
		 try {
			
			List<Object[]> groupDtails = eventGroupDAO.getEventGroups(userId);
			if(groupDtails != null && groupDtails.size()>0)
			{
				returnList = new ArrayList<IdNameVO>();
				for (Object[] group : groupDtails) {
					IdNameVO vo = new IdNameVO();
					
					vo.setId(group[0] != null? Long.valueOf(group[0].toString().trim()):0L);
					vo.setName(group[1] != null? group[1].toString().trim():"");
					
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception rised in getPartyEventGroups() while closing write operation",e);
		}
		 
		 return returnList;
		 
	 }
	 
	 public List<IdNameVO> getPublicRepresenttativesList()
	 {
		 List<IdNameVO> returnList = null;
		 try {

			 List<PublicRepresentativeType> list = publicRepresentativeTypeDAO.getAll();
			 if(list != null && list.size()>0)
				{
					returnList = new ArrayList<IdNameVO>();
					for (PublicRepresentativeType publicRepresentativeType : list) {
						IdNameVO vo = new IdNameVO();
						
						vo.setId(publicRepresentativeType.getPublicRepresentativeTypeId());
						vo.setName(publicRepresentativeType.getType());
						vo.setOrderId(publicRepresentativeType.getOrderNo());
						returnList.add(vo);
					}
				}
			 if(returnList !=null)
				 Collections.sort(returnList,new IdNameVO());
		} catch (Exception e) {
			LOG.error("Exception rised in getPartyEventGroups() while closing write operation",e);
		}
		 
		 return returnList;
		 
	 }
	 
	 public Long addToEventDetails(final Long userId,final Long eventId,final List<Long> publicRepresentativesIdList,final List<Long> tdpCadresIdList)
	 {
		 Long count = null;
		 try {
			 count = (Long) transactionTemplate.execute(new TransactionCallback() {
				 public Object doInTransaction(TransactionStatus status) {
					 DateUtilService dateService = new DateUtilService();
					 Long totalCount = 0L;
					 if( publicRepresentativesIdList!= null && publicRepresentativesIdList.size()>0)
						{
							 for (Long publicRepresentativesId : publicRepresentativesIdList) 
							 {
								 Long eventInviteeId  = eventInviteeDAO.checkIsExistDetails(publicRepresentativesId,eventId,"publicRepresentative");
								 	if(eventInviteeId == null || eventInviteeId.longValue() == 0L)
								 	{
								 		EventInvitee eventInvitee = new EventInvitee();
									 	eventInvitee.setEventId(eventId);
									 	eventInvitee.setPublicRepresentativeId(publicRepresentativesId);
									 	eventInvitee.setCreatedBy(userId);
									 	eventInvitee.setInsertedTime(dateService.getCurrentDateAndTime());
									 	eventInviteeDAO.save(eventInvitee);
									 	totalCount = totalCount+1;
								 	}
							 }
						}
						if(tdpCadresIdList != null && tdpCadresIdList.size()>0)
						{
							 for (Long tdpCadresId : tdpCadresIdList) 
							 {
								 	Long eventInviteeId  = eventInviteeDAO.checkIsExistDetails(tdpCadresId,eventId,"tdpCadre");
								 	if(eventInviteeId == null || eventInviteeId.longValue() == 0L)
								 	{
								 		EventInvitee eventInvitee = new EventInvitee();
									 	eventInvitee.setEventId(eventId);
									 	eventInvitee.setTdpCadreId(tdpCadresId);
									 	eventInvitee.setCreatedBy(userId);
									 	eventInvitee.setInsertedTime(dateService.getCurrentDateAndTime());
									 	eventInviteeDAO.save(eventInvitee);
									 	totalCount = totalCount+1;
								 	}
							 }
						}
					 return totalCount;
				 }});
		} catch (Exception e) {
			LOG.error("Exception rised in addToEventDetails() while closing write operation",e);
		}
		return count;		 	
	 }
	 
	 public ResultStatus createNewEvent(final Long userId,final  UserEventDetailsVO userEventDetailsVO,final String actionType)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 try {
			 String status = (String) transactionTemplate.execute(new TransactionCallback() {
				 public Object doInTransaction(TransactionStatus status) {
					 DateUtilService dateService = new DateUtilService();
					 SimpleDateFormat foramt = new SimpleDateFormat("MM/dd/yy");//MM-DD-YYYY
					 
					 
					 /*//converting 12 hours into 24 hours
					 
					 SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
			           SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
			           
			           Date _24HourDt = _24HourSDF.parse(_24HourTime);
			           System.out.println(_24HourDt);
			           System.out.println(_12HourSDF.format(_24HourDt));
					 
			           
			          String  startTimeFor24=userEventDetailsVO.getStartTime();
			          String endTimeFor24=userEventDetailsVO.getEndTime();
			          
			          String  _24HourStartTime="";
			          String _24HourEndTime=""; 
			          if(startTimeFor24 !=null && endTimeFor24 !=null){
			        	 String _12HourTime = _12HourSDF.format(startTimeFor24);
			        	 _24HourStartTime=_24HourSDF.format(_12HourTime);
			        	 
			        	 String _12HouEndrTime = _12HourSDF.format(startTimeFor24);
			        	 _24HourEndTime=_24HourSDF.format(_12HouEndrTime);
			          }
			        
			          userEventDetailsVO.setStartTime(_24HourStartTime);
			          userEventDetailsVO.setEndTime(_24HourEndTime);
			      //time conversion End
*/			        
					 Event event = null;
					 if(actionType != null && actionType.trim().equalsIgnoreCase("update"))
					 {
						 event = eventDAO.get(userEventDetailsVO.getEventId().longValue());
					 }
					 else
					 {
						 event = new Event();
					 }
					
					 event.setName(userEventDetailsVO.getEventName());
					 event.setDescription(userEventDetailsVO.getStatus());
					 event.setInsertedTime(dateService.getCurrentDateAndTime());
					 event.setServerWorkMode(userEventDetailsVO.getServerWorkMode());
					 event.setTabWorkMode(userEventDetailsVO.getTabWorkMode());
					 try{
						 event.setEventStartTime(foramt.parse(userEventDetailsVO.getStartDate()));
						 event.setEventEndTime(foramt.parse(userEventDetailsVO.getEndDate()));
						 event.setStartTime(userEventDetailsVO.getStartTime());
						 event.setEndTime(userEventDetailsVO.getEndTime());
					 }catch(Exception e){}
					 
					// event.setIsEnabled("Y");
					 if(userEventDetailsVO.getMainEventId().longValue() != 0L)
					 {
						 event.setParentEventId(userEventDetailsVO.getMainEventId());
					 }
					 else
					 {
						 event.setParentEventId(null);
					 }
					 
					 eventDAO.save(event);
					 
					 return "success";
					 
				 }});
			 
			 if(status != null)
			 {
				 resultStatus.setResultCode(0);
				 resultStatus.setMessage("success");
			 }
			 else
			 {
				 resultStatus.setResultCode(1);
				 resultStatus.setMessage("error");
			 }
		} catch (Exception e) {
			LOG.error("Exception rised in createNewEvent() while closing write operation",e);
			 resultStatus.setResultCode(1);
			 resultStatus.setMessage("error");
		}
		 return resultStatus;
	 }
	 
	 public ResultStatus createANewUserForEvents(final Long userId,final String firstName,final String lastName,final String userName,final String password,final String mobileNo)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 try {
			 String status = (String) transactionTemplate.execute(new TransactionCallback() {
				 public Object doInTransaction(TransactionStatus status) {
					 DateUtilService dateService = new DateUtilService();
					 EventSurveyUser eventSurveyUser = new EventSurveyUser();
					 eventSurveyUser.setUserName(userName);
					 eventSurveyUser.setPassWord(password);
					 eventSurveyUser.setFirstName(firstName);
					 eventSurveyUser.setLastName(lastName);
					 eventSurveyUser.setMobile(mobileNo);
					 eventSurveyUser.setUserId(userId);
					 eventSurveyUser.setInsertedTime(dateService.getCurrentDateAndTime());
					 eventSurveyUserDAO.save(eventSurveyUser);
					 return "success";
					 
				 }});
			 
			 if(status != null)
			 {
				 resultStatus.setResultCode(0);
				 resultStatus.setMessage("success");
			 }
			 else
			 {
				 resultStatus.setResultCode(1);
				 resultStatus.setMessage("error");
			 }
		} catch (Exception e) {
			LOG.error("Exception rised in createANewUserForEvents() while closing write operation",e);
			 resultStatus.setResultCode(1);
			 resultStatus.setMessage("error");
		}
		 return resultStatus;
	 }
	 
	 
	 public ResultStatus updateEventSettings(final Long userId,final  UserEventDetailsVO userEventDetailsVO,final String actionType)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 try {
			 String status = (String) transactionTemplate.execute(new TransactionCallback() {
				 public Object doInTransaction(TransactionStatus status) {
					 EventRfidDetails eventRfidDetails = null;
					 if(actionType != null && actionType.trim().equalsIgnoreCase("update"))
					 {
						try {
								 List<Long> eventsIds = new ArrayList<Long>();
								 eventsIds.add(userEventDetailsVO.getEventId().longValue());
								 eventRfidDetailsDAO.deleteEventRFIDDetailsByEventIds(eventsIds);
							} catch (Exception e) {e.printStackTrace();}
						 
					 }
						
					 	Long orderNo=0L;
						
						if(userEventDetailsVO.getSubList() != null && userEventDetailsVO.getSubList().size()>0)
						{
							for (UserEventDetailsVO rfdetailsVO : userEventDetailsVO.getSubList()) {
								orderNo = orderNo+1;
								eventRfidDetails = new EventRfidDetails();
								eventRfidDetails.setEvent(eventDAO.get(userEventDetailsVO.getEventId()));
								eventRfidDetails.setRfidOperation(rfdetailsVO.getRFID());
								eventRfidDetails.setSectorNo(Integer.valueOf(rfdetailsVO.getSectorNo().toString()));
								eventRfidDetails.setBlockNo(Integer.valueOf(rfdetailsVO.getBlockNo().toString()));
								eventRfidDetails.setRegText(rfdetailsVO.getRegText());
								eventRfidDetails.setOrderNo(orderNo);
								eventRfidDetailsDAO.save(eventRfidDetails);
							}
						}

					 return "success";
					 
				 }});
			 
			 if(status != null)
			 {
				 resultStatus.setResultCode(0);
				 resultStatus.setMessage("success");
			 }
			 else
			 {
				 resultStatus.setResultCode(1);
				 resultStatus.setMessage("error");
			 }
		} catch (Exception e) {
			LOG.error("Exception rised in updateEventSettings() while closing write operation",e);
			 resultStatus.setResultCode(1);
			 resultStatus.setMessage("error");
		}
		 return resultStatus;
	 }
	 
	 public ResultStatus assignEventForUser(final Long userId,final  UserEventDetailsVO userEventDetailsVO)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 try {
			 String status = (String) transactionTemplate.execute(new TransactionCallback() {
				 public Object doInTransaction(TransactionStatus status) {
					 DateUtilService dateService = new DateUtilService();
					 
					 EventUser eventUser = new EventUser();
					 eventUser.setEventId(userEventDetailsVO.getEventId());
					 eventUser.setUserId(userEventDetailsVO.getUserId());
					 eventUser.setInsertedBy(userId);
					 eventUser.setInsertedTime(dateService.getCurrentDateAndTime());
					 eventUserDAO.save(eventUser);
					 return "success";
					 
				 }});
			 
			 if(status != null)
			 {
				 resultStatus.setResultCode(0);
				 resultStatus.setMessage("success");
			 }
			 else
			 {
				 resultStatus.setResultCode(1);
				 resultStatus.setMessage("error");
			 }
		} catch (Exception e) {
			LOG.error("Exception rised in assignEventForUser() while closing write operation",e);
			 resultStatus.setResultCode(1);
			 resultStatus.setMessage("error");
		}
		 return resultStatus;
	 }
	 //pre populating the results in event settings
	 public EventCreationVO getPrePopulatingValuesOfEvents(Long eventId){
		 
		 EventCreationVO eventCreationVO = new EventCreationVO();
				 
		 try{
			//Map<Long,List<EventCreationVO>> eventSettingMap=new HashMap<Long, List<EventCreationVO>>();
				List<EventCreationVO> listOfeventSettings=new ArrayList<EventCreationVO>();
				
				List<Object> eventsDetailsList=eventRfidDetailsDAO.getPrePopulatingValuesOfEvents(eventId);
			 
				if(eventsDetailsList !=null && eventsDetailsList.size()>0){
					
					
					for(int i=0;i<eventsDetailsList.size();i++){
						
						 EventRfidDetails eventRfidDetails= (EventRfidDetails) eventsDetailsList.get(i);
						 
						 EventCreationVO  vo=new EventCreationVO();
						
						 vo.setEventId(eventRfidDetails.getEvent().getEventId());
						 vo.setRfidStr(eventRfidDetails.getRfidOperation());
						 vo.setRegTextStr(eventRfidDetails.getRegText());
						 vo.setSelectorL(eventRfidDetails.getSectorNo());
						 vo.setBlockNoL(eventRfidDetails.getBlockNo());
						 
						 listOfeventSettings.add(vo);
					}
						
					if(listOfeventSettings !=null && listOfeventSettings.size()>0){
						//eventSettingMap.put(eventId, listOfeventSettings);
						eventCreationVO.setSubList(listOfeventSettings);
					}
				}
				
				return eventCreationVO;
		 }catch(Exception e){
			 LOG.error("Exception rised in getPrePopulatingValuesOfEvents() while closing write operation",e);
		 }
		 return eventCreationVO;
		 
	 }
	 
	 public List<VO> getDistrictNamesIds(Long userId){
		 List<VO> vo = new ArrayList<VO>();
		 try {
			 List<Long> districtIds = new ArrayList<Long>();
			 List<Object[]> accessDistrictsList = userDistrictAccessInfoDAO.findByUser(userId);
			 for (Object[] districtId : accessDistrictsList) {
					districtIds.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
				}
			 List<Object[]> distList = null;
			 if(districtIds !=  null && districtIds.size()>0)
			 {
				  distList = districtDAO.getDistrictNamesIds(districtIds);
			 }
			 else
			 {
				  distList = districtDAO.getDistrictNamesIds(null);
			 }
			 for (Object[] objects : distList) {
					VO temp= new VO();
					temp.setId((Long)objects[0]);
					temp.setName(objects[1].toString());
					
					vo.add(temp);
				}
		} catch (Exception e) {
			LOG.error("Exception raised in getDistrictNamesIds",e);
		}
		 return vo;
	 } 
	 
	 
	 
	 

	public List<String> getConstituencyByDistrict(Long districtId){
		 List<String> constiList = new ArrayList<String>();
		 try {			
			 constiList	= newDistrictConstituencyDAO.getConstiDetailsOfDistrict(districtId);
			 if(constiList == null || constiList.size() == 0){
				constiList = new ArrayList<String>();
				List<Object[]> result = constituencyDAO.getAllConstituenciesInADistrict(districtId);
				for(Object[] obj : result){			
					constiList.add(obj[1].toString());
				}
			 }
		} catch (Exception e) {
			LOG.error("Exception raised in getConstituencyByDistrict",e);
		}
		 return constiList;
	 } 
	
	public List<TdpCadreVO> exprortExcelForInvitees(List<Long> publicRepresentativesIdList,List<Long> tdpCadresIdList,
			 Map<Long,List<IdNameVO>> parliamentInfoMap ,Map<Long,List<IdNameVO>> assemblyInfoMap,Map<Long,List<IdNameVO>> mandalsListMap )
	{
		List<TdpCadreVO> returnList = new ArrayList<TdpCadreVO>();
		try {
			List<CadreCommitteeVO> cadreCommitteeList = new ArrayList<CadreCommitteeVO>();
			Map<Long,String> committeeMap = new LinkedHashMap<Long, String>();
			Map<Long,String> panchayatMap = new LinkedHashMap<Long, String>();
			Map<Long,String> wardMap = new LinkedHashMap<Long, String>();
			

			List<Object[]> panchayatList = boothDAO.getPanchaytsInfoByStateId(IConstants.VOTER_DATA_PUBLICATION_ID,1L);
			if(panchayatList != null && panchayatList.size()>0)
			{
				for (Object[] committee : panchayatList) {
					Long id = committee[0] != null ? Long.valueOf(committee[0].toString().trim()):0L;
					String name = committee[1] != null ? committee[1].toString().trim():"";
					if(name != null && !name.isEmpty())
					{
						panchayatMap.put(id, name);
					}
				}
			}
			
			
			List<Object[]> wardsList = constituencyDAO.getAllWardsForState(1L);
			if(wardsList != null && wardsList.size()>0)
			{
				for (Object[] committee : wardsList) {
					Long id = committee[0] != null ? Long.valueOf(committee[0].toString().trim()):0L;
					String name = committee[1] != null ? committee[1].toString().trim():"";
					if(name != null && !name.isEmpty())
					{
						wardMap.put(id, name);
					}
				}
			}
			
			List<Object[]> basicCommitteList = tdpBasicCommitteeDAO.getBasicCommittees();
			if(basicCommitteList != null && basicCommitteList.size()>0)
			{
				for (Object[] committee : basicCommitteList) {
					Long id = committee[0] != null ? Long.valueOf(committee[0].toString().trim()):0L;
					String name = committee[1] != null ? committee[1].toString().trim():"";
					if(name != null && !name.isEmpty())
					{
						committeeMap.put(id, name);
					}
				}
			}
			
			
			if(tdpCadresIdList != null && tdpCadresIdList.size()>0)
			{
				List<Long> tdpCadreIds = new ArrayList<Long>();
					System.out.println("Total Members : "+tdpCadresIdList.size());
					LOG.info("Total Invitees for export excel report : "+tdpCadresIdList.size());
					tdpCadreIds.addAll(tdpCadresIdList);
					 List<Object[]> tdpCadreIdDetails = tdpCadreDAO.getMobileNoByTdpCadreIdList(tdpCadreIds,0,0);						 
					 if(tdpCadreIdDetails != null && tdpCadreIdDetails.size()>0)
					 {								
							if(tdpCadreIdDetails != null && tdpCadreIdDetails.size()>0)
							{
								SimpleDateFormat format  = new SimpleDateFormat("yy-MM-dd");
								for (Object[] cadre : tdpCadreIdDetails) 
								{
									CadreCommitteeVO committeeVO = new CadreCommitteeVO();

									committeeVO.setTdpCadreId(cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L);

									committeeVO.setCadreName(cadre[1] != null ? cadre[1].toString():"");
									committeeVO.setRelativeName(cadre[2] != null ? cadre[2].toString():"");
									committeeVO.setMobileNo(cadre[6] != null ? cadre[6].toString():"");
									//committeeVO.setCasteName(cadre[18] != null ? cadre[18].toString().trim():"");
									//committeeVO.setGender(cadre[3] != null ? cadre[3].toString():"");
									committeeVO.setImageURL(cadre[7] != null ? cadre[7].toString():"");
									committeeVO.setType("CadreCommittee");
									
									String electionType = cadre[20] != null ? cadre[20].toString().trim():""; // municipality/corporation/ghmc....
									committeeVO.setLocalElectionBody(cadre[16] != null ? cadre[16].toString().trim()+" "+electionType:"");
									
									committeeVO.setPanchayat(cadre[15] != null ? cadre[15].toString().trim():"");
									committeeVO.setTehsil(cadre[14] != null ? cadre[14].toString().trim()+" Mandal":"");
									//String constiteuncyNo = cadre[35] != null ? cadre[35].toString()+"_":"";
									//String districtNo = cadre[26] != null ? cadre[26].toString()+"_":"";
									committeeVO.setDistrictNo(cadre[26] != null ? cadre[26].toString().trim():"");
									committeeVO.setConstiteuncyNo(cadre[35] != null ? cadre[35].toString().trim():"");
									committeeVO.setConstituency(cadre[11] != null ? cadre[11].toString().trim():"");
									committeeVO.setAddress(cadre[17] != null ? cadre[17].toString().trim():"");
									
									Long basicCommiteeID = cadre[29] != null ? Long.valueOf(cadre[29].toString()):0L;
									String committeeName = committeeMap.get(basicCommiteeID);
									String positionName =  cadre[30] != null ? cadre[30].toString():"";
									Long LocationTypeId = cadre[31] != null ? Long.valueOf(cadre[31].toString()):0L;
									Long locationValue = cadre[32] != null ? Long.valueOf(cadre[32].toString()):0L;
									Long roleId = cadre[33] != null ? Long.valueOf(String.valueOf(cadre[33]).trim()):0L ;
									
										if(locationValue.longValue() > 0L){
											if(LocationTypeId.longValue() == 6L)
											{
												committeeVO.setCommitteeLocation(panchayatMap.get(locationValue)+" Panchayat");
											}
											else if(LocationTypeId.longValue() == 8L)
											{
												committeeVO.setCommitteeLocation(wardMap.get(locationValue));
											}else
											{
												committeeVO.setCommitteeLocation("");
											}
											
											committeeVO.setCommitteePosition(positionName);
											if(committeeName != null)
												committeeVO.setCommitteeName(committeeName);
											committeeVO.setElectionType(cadre[34] != null ? cadre[34].toString():"");
										    if(committeeVO.getElectionType().trim().equalsIgnoreCase("Panchayat"))
										    {
										    	committeeVO.setElectionType("Village/Ward ");
										    }
										    else if(committeeVO.getElectionType().trim().equalsIgnoreCase("Mandal"))
										    {
										    	committeeVO.setElectionType("Mandal/Division/Town");
										    }
										    committeeVO.setVoterId(roleId);
									    }
										cadreCommitteeList.add(committeeVO);
								} 
							}
					 }
				}	
			
			if(publicRepresentativesIdList != null && publicRepresentativesIdList.size()>0)
			{

				 List<Object[]> representativeDetails = publicRepresentativeDAO.getCandidateInfoByCandidateIds(publicRepresentativesIdList);
				 List<Long> existingCandidates = new ArrayList<Long>();
				for (Object[] cadre : representativeDetails) {
					Long id = cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L;
					if(!existingCandidates.contains(id))
					{
						existingCandidates.add(id);
						CadreCommitteeVO committeeVO = new CadreCommitteeVO();
						
						committeeVO.setTdpCadreId(id);
						committeeVO.setCadreName(cadre[1] != null ? cadre[1].toString().trim():"");
						committeeVO.setMobileNo(cadre[2] != null ? cadre[2].toString().trim():"");
						committeeVO.setGender(cadre[3] != null ? cadre[3].toString().trim():"");
						committeeVO.setMobileType(cadre[4] != null ? cadre[4].toString():"");
						
						committeeVO.setType("PublicRepresentative");
						Long levelValue = cadre[5] != null ? Long.valueOf(cadre[5].toString()):0L;
						if(committeeVO.getMobileType() != null && ( committeeVO.getMobileType().trim().equalsIgnoreCase("MP") ||
								 committeeVO.getMobileType().trim().equalsIgnoreCase("EX MP")) )
						{
							List<IdNameVO> parliamentList = parliamentInfoMap.get(levelValue);
							if(parliamentList != null && parliamentList.size()>0)
							{
								IdNameVO vo1 = parliamentList.get(0);
								committeeVO.setConstituency(vo1.getName()+" Parliament");
								
								IdNameVO vo2 = parliamentList.get(1);
								committeeVO.setAddress(vo2.getName());
								
								List<Object[]> parliamentInfo = delimitationConstituencyAssemblyDetailsDAO.findDistrictsOfParliamentConstituency(levelValue);
								if(parliamentInfo != null && parliamentInfo.size()>0)
								{
									Object[] param = parliamentInfo.get(0); // parliament --> district Id -- 0th place
									if(committeeVO.getAddress() != null && param != null && param[0] != null)
									{
										committeeVO.setDistrictNo(param[0].toString());
									}
								}
							}
						}
						else if(committeeVO.getMobileType() != null && (committeeVO.getMobileType().trim().equalsIgnoreCase("MLA") || 
								committeeVO.getMobileType().trim().equalsIgnoreCase("EX MLA") ||
								committeeVO.getMobileType().trim().equalsIgnoreCase("2014 AP STATE MINISTERS") )) 
						{
							List<IdNameVO> assemblyList = assemblyInfoMap.get(levelValue);
							if(assemblyList != null && assemblyList.size()>0)
							{
								IdNameVO vo1 = assemblyList.get(0);
								committeeVO.setConstituency(vo1.getName()+" Assembly");
								
								IdNameVO vo2 = assemblyList.get(1);
								committeeVO.setAddress(vo2.getName());
								
								List<Object[]> constiInfo = constituencyDAO.getStateDistrictConstituency(levelValue);//232
								if(constiInfo != null && constiInfo.size()>0)
								{
									Object[] param = constiInfo.get(0); // constituency List district Id -- 2nd place
									if(committeeVO.getAddress() != null && param != null && param[2] != null)
									{
										committeeVO.setDistrictNo(param[2].toString());
									}
								}
							}
						}
						else if(committeeVO.getMobileType() != null && (
								committeeVO.getMobileType().trim().equalsIgnoreCase("ZPTC") || 
								committeeVO.getMobileType().trim().equalsIgnoreCase("MPTC") || 
								committeeVO.getMobileType().trim().equalsIgnoreCase("MPP") ))
						{
							List<IdNameVO> assemblyList = mandalsListMap.get(levelValue);
							if(assemblyList != null && assemblyList.size()>0)
							{
								IdNameVO vo1 = assemblyList.get(0);
								committeeVO.setConstituency(vo1.getName()+" Assembly");
								
								IdNameVO vo2 = assemblyList.get(1);
								committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
								if(assemblyList.size()>2){
									IdNameVO vo3 = assemblyList.get(2);
									committeeVO.setTehsil(vo3.getId()+"_"+vo3.getName());
								}
							}
						}
						cadreCommitteeList.add(committeeVO);
					}
					else
					{
						CadreCommitteeVO existingVO = getMatchedCommitteeVO(cadreCommitteeList,id);
						if(existingVO != null)
						{
							if(existingVO.getMobileType().trim().contains("EX"))
							{
								if(existingVO.getMobileType().trim().contains("EX MLA"))
								{
									String candidateType = cadre[4] != null ? cadre[4].toString():"";
									if(candidateType != null && !candidateType.trim().isEmpty() && !candidateType.trim().equalsIgnoreCase(existingVO.getMobileType().trim()) && 
											candidateType.trim().equalsIgnoreCase("EX MP") )															
									{
										CadreCommitteeVO committeeVO = new CadreCommitteeVO();
										
										committeeVO.setTdpCadreId(id);
										committeeVO.setCadreName(cadre[1] != null ? cadre[1].toString().trim():"");
										committeeVO.setMobileNo(cadre[2] != null ? cadre[2].toString().trim():"");
										committeeVO.setGender(cadre[3] != null ? cadre[3].toString().trim():"");
										committeeVO.setMobileType(cadre[4] != null ? cadre[4].toString():"");
										
										committeeVO.setType("PublicRepresentative");
										Long levelValue = cadre[5] != null ? Long.valueOf(cadre[5].toString()):0L;
										if(committeeVO.getMobileType() != null && ( committeeVO.getMobileType().trim().equalsIgnoreCase("MP") ||
												 committeeVO.getMobileType().trim().equalsIgnoreCase("EX MP")) )
										{
											List<IdNameVO> parliamentList = parliamentInfoMap.get(levelValue);
											if(parliamentList != null && parliamentList.size()>0)
											{
												IdNameVO vo1 = parliamentList.get(0);
												committeeVO.setConstituency(vo1.getName()+" Parliament");
												
												IdNameVO vo2 = parliamentList.get(1);
												committeeVO.setAddress(vo2.getName());
												
												List<Object[]> parliamentInfo = delimitationConstituencyAssemblyDetailsDAO.findDistrictsOfParliamentConstituency(levelValue);
												if(parliamentInfo != null && parliamentInfo.size()>0)
												{
													Object[] param = parliamentInfo.get(0); // parliament --> district Id -- 0th place
													if(committeeVO.getAddress() != null && param != null && param[0] != null)
													{
														committeeVO.setDistrictNo(param[0].toString());
													}
												}
											}
										}
										else if(committeeVO.getMobileType() != null && (committeeVO.getMobileType().trim().equalsIgnoreCase("MLA") || 
												committeeVO.getMobileType().trim().equalsIgnoreCase("EX MLA") ||
												committeeVO.getMobileType().trim().equalsIgnoreCase("2014 AP STATE MINISTERS") )) 
										{
											List<IdNameVO> assemblyList = assemblyInfoMap.get(levelValue);
											if(assemblyList != null && assemblyList.size()>0)
											{
												IdNameVO vo1 = assemblyList.get(0);
												committeeVO.setConstituency(vo1.getName()+" Assembly");
												
												IdNameVO vo2 = assemblyList.get(1);
												committeeVO.setAddress(vo2.getName());
												
												List<Object[]> constiInfo = constituencyDAO.getStateDistrictConstituency(levelValue);//232
												if(constiInfo != null && constiInfo.size()>0)
												{
													Object[] param = constiInfo.get(0); // constituency List district Id -- 2nd place
													if(committeeVO.getAddress() != null && param != null && param[2] != null)
													{
														committeeVO.setDistrictNo(param[2].toString());
													}
												}
												
											}
										}
										else if(committeeVO.getMobileType() != null && (
												committeeVO.getMobileType().trim().equalsIgnoreCase("ZPTC") || 
												committeeVO.getMobileType().trim().equalsIgnoreCase("MPTC") || 
												committeeVO.getMobileType().trim().equalsIgnoreCase("MPP") ))
										{
											List<IdNameVO> assemblyList = mandalsListMap.get(levelValue);
											if(assemblyList != null && assemblyList.size()>0)
											{
												IdNameVO vo1 = assemblyList.get(0);
												committeeVO.setConstituency(vo1.getName()+" Assembly");
												
												IdNameVO vo2 = assemblyList.get(1);
												committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
												if(assemblyList.size()>2){
													IdNameVO vo3 = assemblyList.get(2);
													committeeVO.setTehsil(vo3.getId()+"_"+vo3.getName());
												}
											}
										}
										cadreCommitteeList.add(committeeVO);
									}
									else
									{
										existingVO.setTdpCadreId(id);
										existingVO.setCadreName(cadre[1] != null ? cadre[1].toString().trim():"");
										existingVO.setMobileNo(cadre[2] != null ? cadre[2].toString().trim():"");
										existingVO.setGender(cadre[3] != null ? cadre[3].toString().trim():"");
										//existingVO.setMobileType(cadre[4] != null ? cadre[4].toString():"");
										
										existingVO.setType("PublicRepresentative");
										Long levelValue = cadre[5] != null ? Long.valueOf(cadre[5].toString()):0L;
										if(existingVO.getMobileType() != null && ( existingVO.getMobileType().trim().equalsIgnoreCase("MP") ||
												existingVO.getMobileType().trim().equalsIgnoreCase("EX MP")) )
										{
											List<IdNameVO> parliamentList = parliamentInfoMap.get(levelValue);
											if(parliamentList != null && parliamentList.size()>0)
											{
												IdNameVO vo1 = parliamentList.get(0);
												existingVO.setConstituency(vo1.getName()+" Parliament");
												
												IdNameVO vo2 = parliamentList.get(1);
												existingVO.setAddress(vo2.getName());
												
												List<Object[]> parliamentInfo = delimitationConstituencyAssemblyDetailsDAO.findDistrictsOfParliamentConstituency(levelValue);
												if(parliamentInfo != null && parliamentInfo.size()>0)
												{
													Object[] param = parliamentInfo.get(0); // parliament --> district Id -- 0th place
													if(existingVO.getAddress() != null && param != null && param[0] != null)
													{
														existingVO.setDistrictNo(param[0].toString());
													}
												}
											}
										}
										else if(existingVO.getMobileType() != null && (existingVO.getMobileType().trim().equalsIgnoreCase("MLA") || 
												existingVO.getMobileType().trim().equalsIgnoreCase("EX MLA") ||
												existingVO.getMobileType().trim().equalsIgnoreCase("2014 AP STATE MINISTERS"))) 
										{
											List<IdNameVO> assemblyList = assemblyInfoMap.get(levelValue);
											if(assemblyList != null && assemblyList.size()>0)
											{
												IdNameVO vo1 = assemblyList.get(0);
												existingVO.setConstituency(vo1.getName()+" Assembly");
												
												IdNameVO vo2 = assemblyList.get(1);
												existingVO.setAddress(vo2.getName());
												
												List<Object[]> constiInfo = constituencyDAO.getStateDistrictConstituency(levelValue);//232
												if(constiInfo != null && constiInfo.size()>0)
												{
													Object[] param = constiInfo.get(0); // constituency List district Id -- 2nd place
													if(existingVO.getAddress() != null && param != null && param[2] != null)
													{
														existingVO.setDistrictNo(param[2].toString());
													}
												}
												
											}
										}
										else if(existingVO.getMobileType() != null && (
												existingVO.getMobileType().trim().equalsIgnoreCase("ZPTC") || 
												existingVO.getMobileType().trim().equalsIgnoreCase("MPTC") || 
												existingVO.getMobileType().trim().equalsIgnoreCase("MPP") ))
										{
											List<IdNameVO> assemblyList = mandalsListMap.get(levelValue);
											if(assemblyList != null && assemblyList.size()>0)
											{
												IdNameVO vo1 = assemblyList.get(0);
												existingVO.setConstituency(vo1.getName()+" Assembly");
												
												IdNameVO vo2 = assemblyList.get(1);
												existingVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
												if(assemblyList.size()>2){
													IdNameVO vo3 = assemblyList.get(2);
													existingVO.setTehsil(vo3.getId()+"_"+vo3.getName());
												}
											}
										}
										//cadreCommitteeList.add(committeeVO);
									
										existingVO.setMobileType(candidateType);
									}
								} 													
								else if(existingVO.getMobileType().trim().contains("EX MP"))
								{
									String candidateType = cadre[4] != null ? cadre[4].toString():"";
									if(candidateType != null && !candidateType.trim().isEmpty() && !candidateType.trim().equalsIgnoreCase(existingVO.getMobileType().trim()) && 
											candidateType.trim().equalsIgnoreCase("EX MLA") )															
									{
										CadreCommitteeVO committeeVO = new CadreCommitteeVO();
										
										committeeVO.setTdpCadreId(id);
										committeeVO.setCadreName(cadre[1] != null ? cadre[1].toString().trim():"");
										committeeVO.setMobileNo(cadre[2] != null ? cadre[2].toString().trim():"");
										committeeVO.setGender(cadre[3] != null ? cadre[3].toString().trim():"");
										committeeVO.setMobileType(cadre[4] != null ? cadre[4].toString():"");
										
										committeeVO.setType("PublicRepresentative");
										Long levelValue = cadre[5] != null ? Long.valueOf(cadre[5].toString()):0L;
										if(committeeVO.getMobileType() != null && ( committeeVO.getMobileType().trim().equalsIgnoreCase("MP") ||
												 committeeVO.getMobileType().trim().equalsIgnoreCase("EX MP")) )
										{
											List<IdNameVO> parliamentList = parliamentInfoMap.get(levelValue);
											if(parliamentList != null && parliamentList.size()>0)
											{
												IdNameVO vo1 = parliamentList.get(0);
												committeeVO.setConstituency(vo1.getName()+" Parliament");
												
												IdNameVO vo2 = parliamentList.get(1);
												committeeVO.setAddress(vo2.getName());
												
												List<Object[]> parliamentInfo = delimitationConstituencyAssemblyDetailsDAO.findDistrictsOfParliamentConstituency(levelValue);
												if(parliamentInfo != null && parliamentInfo.size()>0)
												{
													Object[] param = parliamentInfo.get(0); // parliament --> district Id -- 0th place
													if(committeeVO.getAddress() != null && param != null && param[0] != null)
													{
														committeeVO.setDistrictNo(param[0].toString());
													}
												}
											}
										}
										else if(committeeVO.getMobileType() != null && (committeeVO.getMobileType().trim().equalsIgnoreCase("MLA") || 
												committeeVO.getMobileType().trim().equalsIgnoreCase("EX MLA") ||
												committeeVO.getMobileType().trim().equalsIgnoreCase("2014 AP STATE MINISTERS") )) 
										{
											List<IdNameVO> assemblyList = assemblyInfoMap.get(levelValue);
											if(assemblyList != null && assemblyList.size()>0)
											{
												IdNameVO vo1 = assemblyList.get(0);
												committeeVO.setConstituency(vo1.getName()+" Assembly");
												
												IdNameVO vo2 = assemblyList.get(1);
												committeeVO.setAddress(vo2.getName());
												
												List<Object[]> constiInfo = constituencyDAO.getStateDistrictConstituency(levelValue);//232
												if(constiInfo != null && constiInfo.size()>0)
												{
													Object[] param = constiInfo.get(0); // constituency List district Id -- 2nd place
													if(committeeVO.getAddress() != null && param != null && param[2] != null)
													{
														committeeVO.setDistrictNo(param[2].toString());
													}
												}
												
											}
										}
										else if(committeeVO.getMobileType() != null && (
												committeeVO.getMobileType().trim().equalsIgnoreCase("ZPTC") || 
												committeeVO.getMobileType().trim().equalsIgnoreCase("MPTC") || 
												committeeVO.getMobileType().trim().equalsIgnoreCase("MPP") ))
										{
											List<IdNameVO> assemblyList = mandalsListMap.get(levelValue);
											if(assemblyList != null && assemblyList.size()>0)
											{
												IdNameVO vo1 = assemblyList.get(0);
												committeeVO.setConstituency(vo1.getName()+" Assembly");
												
												IdNameVO vo2 = assemblyList.get(1);
												committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
												if(assemblyList.size()>2){
													IdNameVO vo3 = assemblyList.get(2);
													committeeVO.setTehsil(vo3.getId()+"_"+vo3.getName());
												}
											}
										}
										cadreCommitteeList.add(committeeVO);
									}
									else
									{
										existingVO.setMobileType(candidateType);
									}
								}
							}													
							else
							{
								String candidateType = cadre[4] != null ? cadre[4].toString():"";
								if(!candidateType.trim().contains("EX"))
								{
									if(existingVO.getMobileType().trim().equalsIgnoreCase("MLA") || existingVO.getMobileType().trim().equalsIgnoreCase("MP"))
									{
										existingVO.setMobileType(existingVO.getMobileType()+","+candidateType);
									}
									else
									{
										existingVO.setMobileType(candidateType+", "+existingVO.getMobileType());
									}
								}
							} 
						}
					}
				}
			
				}

			String url = "";
			if(cadreCommitteeList != null && cadreCommitteeList.size()>0)
			{
				url = generateExcelReport(cadreCommitteeList);
				TdpCadreVO vo = new TdpCadreVO();
				vo.setImageURL(url);
				vo.setTotalCount(Long.valueOf(String.valueOf(tdpCadresIdList.size())));
				returnList.add(vo);
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in exprortExcelForInvitees",e);
		}
		
		return returnList;
	}
	
	public String generateExcelReport(List<CadreCommitteeVO> cadreCommitteeList)
	{
		String url = "";
		try {
			   
			    
			if(cadreCommitteeList  != null && cadreCommitteeList.size()>0)
			{	
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("INVITEE DETAILS");
				HSSFRow rowhead = sheet.createRow((short) 1);
				
				
				
				HSSFFont font = workbook.createFont();
			    font.setFontName("Calibri");
			    font.setFontHeightInPoints((short)14);
			    CellStyle style = workbook.createCellStyle();
			    style.setFillForegroundColor(HSSFColor.YELLOW.index);
			    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			    style.setAlignment(CellStyle.ALIGN_CENTER);
			    style.setFont(font);
			    
			    //for data.
			    HSSFFont font1 = workbook.createFont();
			    font1.setFontName("Calibri");
			    font1.setFontHeightInPoints((short)11);
			    CellStyle style1 = workbook.createCellStyle();
			    style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			    style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
			    style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
			    style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			    style1.setAlignment(CellStyle.ALIGN_CENTER);
			    style1.setFont(font1);
			   
			    HSSFFont font2 = workbook.createFont();
			    font2.setFontName("Calibri");
			    font2.setFontHeightInPoints((short)11);
			    CellStyle style2 = workbook.createCellStyle();
			    style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			    style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			    style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
			    style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			    style1.setFont(font2);
			   
			    
			    HSSFCell cell = (HSSFCell) rowhead.createCell(1);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" S.NO ");
			    
			    cell = (HSSFCell) rowhead.createCell(2);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" DISTRICT_NO ");
			    
			    cell = (HSSFCell) rowhead.createCell(3);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" DISTRICT ");
			    
			    cell = (HSSFCell) rowhead.createCell(4);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" CONSTITUENCY_NO ");
			    
			    cell = (HSSFCell) rowhead.createCell(5);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" CONSTITUENCY ");
			    
			    cell = (HSSFCell) rowhead.createCell(6);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" MANDAL / MUNICIPALITY ");
			    
			    cell = (HSSFCell) rowhead.createCell(7);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" CANDIDATE NAME ");
			    
			    cell = (HSSFCell) rowhead.createCell(8);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" COMMITTEE - DESIGNATION ");
			    
			    cell = (HSSFCell) rowhead.createCell(9);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" COMMITTEE LEVEL ");
			    
			    cell = (HSSFCell) rowhead.createCell(10);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" MOBILE NO ");
			    
				int rowNum = 1;
				int sno = 0;
				for (CadreCommitteeVO cadreCommitteeVO : cadreCommitteeList) 
				{
					rowNum++;
					sno++;
				    Row dataRow = sheet.createRow(rowNum);

				    cell = (HSSFCell) dataRow.createCell(1);
				    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
			    	cell.setCellStyle(style2);
			    	cell.setCellValue(sno);
			    	
			    	cell = (HSSFCell) dataRow.createCell(2);
				    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
				    if(cadreCommitteeVO.getDistrictNo() != null )
				    {
				    	cell.setCellStyle(style1);
				    	cell.setCellValue(cadreCommitteeVO.getDistrictNo());
				    }
				    else
				    {
				    	cell.setCellStyle(style1);
				    	cell.setCellValue(" - ");
				    }
				    
				    cell = (HSSFCell) dataRow.createCell(3);
				    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
				    if(cadreCommitteeVO.getAddress() != null )
				    {
				    	cell.setCellStyle(style2);
				    	cell.setCellValue(cadreCommitteeVO.getAddress());
				    }
				    else
				    {
				    	cell.setCellStyle(style1);
				    	cell.setCellValue(" - ");
				    }
			    	
				    cell = (HSSFCell) dataRow.createCell(4);
				    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
			    	 if(cadreCommitteeVO.getConstiteuncyNo() != null )
					    {
					    	cell.setCellStyle(style1);
					    	cell.setCellValue(cadreCommitteeVO.getConstiteuncyNo());
					    }
			    	 else if(cadreCommitteeVO.getConstituency() != null && (cadreCommitteeVO.getConstituency()).toString().contains("_"))
			    	 	{
			    		 String disres[] = (cadreCommitteeVO.getConstituency()).split("_");
			    		 if(disres[0].toString().trim().equalsIgnoreCase("0"))
			    		 {
			    			 cell.setCellStyle(style1);
			    			 cell.setCellValue(" - ");
			    		 }
			    		 else
			    		 {
			    			 cell.setCellStyle(style1);
			    			 cell.setCellValue(disres[0]);
			    		 }
			    		 
			    	 	}
					    else
					    {
					    	cell.setCellStyle(style1);
					    	cell.setCellValue(" - ");
					    }
			    	 
			    	cell = (HSSFCell) dataRow.createCell(5);
				    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
			    	 if(cadreCommitteeVO.getConstituency() != null )
					    {
			    		 if(cadreCommitteeVO.getConstituency().toString().contains("_"))
			    		 {
			    			 String disres1[] = (cadreCommitteeVO.getConstituency()).split("_");
			    			 cell.setCellStyle(style2);
			    			 cell.setCellValue(disres1[1]);
			    		 }
			    		 else 
			    		 {
					    	cell.setCellStyle(style2);
					    	cell.setCellValue(cadreCommitteeVO.getConstituency());
			    		 }
					    }
					    else
					    {
					    	cell.setCellStyle(style1);
					    	cell.setCellValue(" - ");
					    }
			    	 
			    	cell = (HSSFCell) dataRow.createCell(6);
				    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
			    	 if(cadreCommitteeVO.getTehsil() != null )
					    {
					    	cell.setCellStyle(style2);
					    	cell.setCellValue(cadreCommitteeVO.getTehsil());
					    }
					    else
					    {
					    	cell.setCellStyle(style1);
					    	cell.setCellValue(" - ");
					    }
			    	 
			    	cell = (HSSFCell) dataRow.createCell(7);
				    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
			    	 if(cadreCommitteeVO.getCadreName() != null )
					    {
					    	cell.setCellStyle(style2);
					    	cell.setCellValue(cadreCommitteeVO.getCadreName());
					    }
					    else
					    {
					    	cell.setCellStyle(style1);
					    	cell.setCellValue(" - ");
					    }
			    	 
			    	cell = (HSSFCell) dataRow.createCell(8);
				    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
			    	 if(cadreCommitteeVO.getCommitteeName() != null && !cadreCommitteeVO.getCommitteeName().trim().isEmpty())
					    {
					    	cell.setCellStyle(style2);
					    	cell.setCellValue(cadreCommitteeVO.getCommitteeName()+" - "+cadreCommitteeVO.getCommitteePosition()+"");
					    }
			    	 	else if(cadreCommitteeVO.getMobileType() != null )
					    {
					    	cell.setCellStyle(style2);
					    	cell.setCellValue(cadreCommitteeVO.getMobileType());
					    }
					    else
					    {
					    	cell.setCellStyle(style1);
					    	cell.setCellValue(" - ");
					    }
			    	 
			    	cell = (HSSFCell) dataRow.createCell(9);
				    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
				    if(cadreCommitteeVO.getElectionType() != null )
				    {
				    	cell.setCellStyle(style2);
				    	String position = "";
				    	 if(cadreCommitteeVO.getCommitteeLocation() != null && !cadreCommitteeVO.getCommitteeLocation().trim().isEmpty())
						    {
				    		 	position = cadreCommitteeVO.getCommitteeLocation()+" - ";
						    }
				    	cell.setCellValue(position+""+cadreCommitteeVO.getElectionType());
				    }				   
					    else
					    {
					    	cell.setCellStyle(style1);
					    	cell.setCellValue(" - ");
					    }
			    	 
			    	cell = (HSSFCell) dataRow.createCell(10);
				    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
			    	 if(cadreCommitteeVO.getMobileNo() != null )
					    {
					    	cell.setCellStyle(style2);
					    	cell.setCellValue(cadreCommitteeVO.getMobileNo());
					    }
					    else
					    {
					    	cell.setCellStyle(style1);
					    	cell.setCellValue(" - ");
					    }
				}
				
				 try {
					 	Random randomNum = new Random();
		    		   	//String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
		    		   	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    		   	Date date = new Date();
		    		   //	System.out.println(dateFormat.format(date));
		    		   	url = "Invitees/"+dateFormat.format(date)+"_"+randomNum.nextInt(10000)+".xls";
		    	        FileOutputStream out =  new FileOutputStream(new File(IConstants.STATIC_CONTENT_FOLDER_URL+""+url));
		    	        workbook.write(out);
		    	        out.close();
		    	         
		    	    } catch (FileNotFoundException e) {
		    	        e.printStackTrace();
		    	    } catch (IOException e) {
		    	        e.printStackTrace();
		    	    } 
				 
			}
		} catch (Exception e) {
			LOG.error("Exception raised in generateExcelReport",e);
		}
		return url;
	}
	
	public void setCurrentExcelDesignation(Map<Long,CadreCommitteeVO> cadreMap,List<Long> tdpCadreIdsList,List<CadreCommitteeVO> cadreCommitteeList){
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
				CadreCommitteeVO cadreVO = cadreMap.get(id);
				if(cadreVO != null)
				{
					String location = null;
					if(locationValue.longValue() > 0L){
						//System.out.println("tdpCadreId :"+id+"  \t positionName  :"+positionName);
						//location = getLocationName(LocationTypeId,locationValue);
						//cadreVO.setCommitteeLocation(location);
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
					
					cadreCommitteeList.add(cadreVO);
			   }
		    }
			
		}
	}
public List<GenericVO> getPanchayatDetailsByMandalIdAddingParam(Long tehsilId){
		
		List<GenericVO> panachatiesList = new ArrayList<GenericVO>();
		List<Object[]> panchayties=null;
		if(tehsilId !=null ){
			if(Long.valueOf(tehsilId.toString().substring(0, 1))==2){
				 panchayties = panchayatDAO.getPanchayatsByTehsilId(Long.valueOf(tehsilId.toString().substring(1)));
			}
			if(Long.valueOf(tehsilId.toString().substring(0, 1))==1){
				 panchayties = constituencyDAO.getWardIdAndName(Long.valueOf(tehsilId.toString().substring(1)));
			}
			if(panchayties !=null ){
				for (Object[] list : panchayties) {
					GenericVO panchayaties = new GenericVO();
					String idStr=null;
					String insertIdStr=null;
					if(Long.valueOf(tehsilId.toString().substring(0, 1))==2){
						idStr=list[0].toString();
						insertIdStr="1"+idStr;					
						panchayaties.setId(Long.valueOf(insertIdStr));//PanchayatId Setting with 1 Before
					}else if(Long.valueOf(tehsilId.toString().substring(0, 1))==1){
						 idStr=list[0].toString();
						 insertIdStr="2"+idStr;
						panchayaties.setId(Long.valueOf(insertIdStr));//WardId Setting with 2 Before
					}
					
					panchayaties.setName(list[1].toString());
					
					panachatiesList.add(panchayaties);
				}
				return panachatiesList;
			}
			else{	
				return null;
			}
		}
		return panachatiesList;
	}

	public List<LocationWiseBoothDetailsVO> getLocationsOfSublevelConstituencyMandal(Long stateId, List<Long> districtIds, List<Long> constituencyIds, String mandalStr, Long locationLevelId){
		
		List<Long> constiIds = new ArrayList<Long>();
		List<Long> mandalIds = new ArrayList<Long>();
		List<Long> localBodyIds = new ArrayList<Long>();
		if(locationLevelId.equals(4l)){
			if(constituencyIds!=null && constituencyIds.size()>0){
				constiIds = constituencyIds;
			}else{
				//List<Object[]> rslt = constituencyDAO.getConstituenciesByStateAndDistrict(stateId, districtIds);
				List<LocationWiseBoothDetailsVO> rslt = getConstituencyOfDistrict(stateId, districtIds);
				if(rslt!=null && rslt.size()>0){
					for(LocationWiseBoothDetailsVO obj:rslt){
						constiIds.add(obj.getLocationId());
					}
				}
				
			}
		}else{
			if(!mandalStr.equalsIgnoreCase("0") && !mandalStr.trim().equalsIgnoreCase("") ){
				String firstLetter = mandalStr.substring(0, 1);
				if(firstLetter.equalsIgnoreCase("4")){
					Long mandalId = Long.valueOf(mandalStr.substring(1));
					mandalIds.add(mandalId);
				}else if(firstLetter.equalsIgnoreCase("5")){
					Long localBody = Long.valueOf(mandalStr.substring(1));
					localBodyIds.add(localBody);
				}
				
			}else{
				if(constituencyIds!=null && constituencyIds.size()>0){
					constiIds = constituencyIds;
				}else{
					List<LocationWiseBoothDetailsVO> rslt = getConstituencyOfDistrict(stateId, districtIds);
					if(rslt!=null && rslt.size()>0){
						for(LocationWiseBoothDetailsVO obj:rslt){
							constiIds.add(obj.getLocationId());
						}
					}
				}
			}
		}
		
		try{
			if(locationLevelId.equals(4l)){
				return getMandalMunicCorpDetailsOfConstituencies(constiIds,"");
			}else{
				return getPanchayatWardDivisionDetailsOfSubLocation(constiIds, mandalIds, localBodyIds);
			}
		}catch(Exception e){
			LOG.error("Exception raised in getLocationsList", e);
			return new ArrayList<LocationWiseBoothDetailsVO>(); 
		}
	}
	
	
	// TO GET MANDAL/LOCAL BODY/ DIVISION DETAILS OF CONSTITUENCIES
	public List<LocationWiseBoothDetailsVO> getMandalMunicCorpDetailsOfConstituencies(List<Long> constituencyIds,String type){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		List<Long> corporationIds = new ArrayList<Long>();
		corporationIds.add(20l);
		corporationIds.add(124l);
		corporationIds.add(119l);
		LocationWiseBoothDetailsVO vo = null;
		List<Long> greaterCorpIds = new ArrayList<Long>();
		List<SelectOptionVO> locations = regionServiceDataImp.getAllMandalsByAllConstituencies(constituencyIds);
		List<Object[]> localBodies = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituencyList(constituencyIds);
	        for(SelectOptionVO location:locations){
	        	vo = new LocationWiseBoothDetailsVO();
	        	vo.setLocationId(Long.valueOf("4"+location.getId()));
	        	vo.setLocationName(location.getName()+" Mandal");
	        	locationsList.add(vo);
	        }
	        for(Object[] localBodi:localBodies){
	        	Long localBdyId = (Long)localBodi[0];
	        	if(!(corporationIds.contains(localBdyId)) || type.equalsIgnoreCase("nominatedPostFilter")){
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("5"+localBodi[0].toString()));
		        	vo.setLocationName(localBodi[4].toString() +" "+ localBodi[2].toString());
		        	locationsList.add(vo);
	        	}else if(type.equalsIgnoreCase("")){
	        		if(!greaterCorpIds.contains(localBdyId)){
	        			greaterCorpIds.add(localBdyId);
	        		}
	        	}
	        }
	        if(greaterCorpIds.size() > 0){
        	  for(Long id:greaterCorpIds){
        		  List<Object[]>  wards = assemblyLocalElectionBodyWardDAO.findWardsByLocalBodyConstiIds(greaterCorpIds, constituencyIds);
        		  for(Object[] location:wards){
        			  vo = new LocationWiseBoothDetailsVO();
  		        	vo.setLocationId(Long.valueOf("6"+location[0].toString()));
  		        	vo.setLocationName(location[1].toString());
  		        	locationsList.add(vo);
        		  }
        	  }
	        }
	        Collections.sort(locationsList,new Comparator<LocationWiseBoothDetailsVO>() {
				public int compare(LocationWiseBoothDetailsVO o1,
						LocationWiseBoothDetailsVO o2) {
					return o1.getLocationName().compareTo(o2.getLocationName());
				}
			});
	        return locationsList;
	}
	
	
	// TO GET VILLAGE / WARD DETAILS OF MANDAL/LOCAL BODY/DIVISION
	public List<LocationWiseBoothDetailsVO> getPanchayatWardDivisionDetailsOfSubLocation(List<Long> constituencyIds, List<Long> mandals, List<Long> localBodys){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		LocationWiseBoothDetailsVO vo = null;
		
		List<Long> mandalIds = new ArrayList<Long>();
		List<Long> localBodyIds = new ArrayList<Long>();
		
		if(constituencyIds!=null && constituencyIds.size()>0){
			List<LocationWiseBoothDetailsVO> mandalsList = getMandalMunicCorpDetailsOfConstituencies(constituencyIds,"");
			
	        for(LocationWiseBoothDetailsVO location:mandalsList){        	
	        	if(location.getLocationId().toString().substring(0,1).trim().equalsIgnoreCase("5")){
	        		Long localBdyId = Long.valueOf(location.getLocationId().toString().substring(1));
	        		if(!(localBdyId.longValue() == 20l ||  localBdyId.longValue() == 124l || localBdyId.longValue() == 119l)){
	        		   localBodyIds.add(localBdyId);
	        		}
	        	}else if(location.getLocationId().toString().substring(0,1).trim().equalsIgnoreCase("4")){
	        		mandalIds.add(Long.valueOf(location.getLocationId().toString().substring(1)));
	        	}
	        }
		}else{
			mandalIds = mandals;
			localBodyIds = localBodys;
		}
		
		
		
	        if(mandalIds.size() > 0){
	        	//0panchayatId,1panchayatName,2tehsilName
	        	List<Object[]> panchayatsList = panchayatDAO.getAllPanchayatsInMandals(mandalIds);
	        	for(Object[] panchayat:panchayatsList){
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("7"+(Long)panchayat[0]));
		        	vo.setLocationName(panchayat[1].toString()+"("+panchayat[2].toString()+")");
		        	locationsList.add(vo);
	        	}
	        }
	        if(localBodyIds.size() > 0){
	        	//0wardId,1pwardName,2localBdyName
	        	List<Object[]> localBodyList = constituencyDAO.getWardsInLocalElectionBody(localBodyIds);
	        	for(Object[] localBody:localBodyList){
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("8"+(Long)localBody[0]));
		        	vo.setLocationName(localBody[1].toString()+"("+localBody[2].toString()+")");
		        	locationsList.add(vo);
	        	}
	        }
	        return locationsList;
	}
	
	public List<LocationWiseBoothDetailsVO> getConstituencyOfDistrict(Long stateId, List<Long> districtIds){
		List<Object[]> rslt = constituencyDAO.getConstituenciesByStateAndDistrict(stateId, districtIds);
		List<Object[]> rsltNew = districtConstituenciesDAO.getConstituenciesOfDistrict(null);
		Map<Long,List<Long>> distMap = new HashMap<Long, List<Long>>();
		Map<Long, Long> constisSplttdMap = new HashMap<Long, Long>();
		Map<Long,String> constiNameMap = new HashMap<Long, String>();
		
		
		if(rsltNew!=null && rsltNew.size()>0){
			for(Object[] obj:rsltNew){
				List<Long> consties = distMap.get(Long.valueOf(obj[0].toString()));
				if(consties==null){
					consties = new ArrayList<Long>();
				}
				consties.add(Long.valueOf(obj[2].toString()));
				constisSplttdMap.put(Long.valueOf(obj[2].toString()),Long.valueOf(obj[0].toString()));
				distMap.put(Long.valueOf(obj[0].toString()), consties);
				constiNameMap.put(Long.valueOf(obj[2].toString()), obj[3].toString());
				
			}
		}
		
		List<LocationWiseBoothDetailsVO> finalList =  new ArrayList<LocationWiseBoothDetailsVO>();
		if(rslt!=null && rslt.size()>0){
			for(Object[] temp:rslt){
				if(constisSplttdMap.get(Long.valueOf(temp[0].toString())) == null ){
					LocationWiseBoothDetailsVO vo = new LocationWiseBoothDetailsVO();
			       	vo.setLocationId(Long.valueOf(temp[0].toString()));
			       	vo.setLocationName(temp[1].toString());
			       	finalList.add(vo);
				}
				else if(constisSplttdMap.get(Long.valueOf(temp[0].toString())) != (Long)temp[2] ){
					LocationWiseBoothDetailsVO vo = new LocationWiseBoothDetailsVO();
			       	vo.setLocationId(Long.valueOf(temp[0].toString()));
			       	vo.setLocationName(temp[1].toString());
			       	finalList.add(vo);
				}
			}
		}else{
			List<Long> distList = new ArrayList<Long>();
			if(districtIds!=null && districtIds.size()>0){
				for(Long districtId:districtIds){
					distList.addAll(distMap.get(districtId));
				}
			}
			if(distList!=null && distList.size()>0){
				for(Long temp:distList){
					if(distList.contains(temp)){
						LocationWiseBoothDetailsVO vo = new LocationWiseBoothDetailsVO();
			        	vo.setLocationId(temp);
			        	vo.setLocationName(constiNameMap.get(temp));
			        	finalList.add(vo);
					}
				}
				
			}
		}
		
		return finalList;
	}
	
	
	public List<IdNameVO> getDistrictsOfStateWithSplitted(Long stateId){
		List<Object[]> rslt = districtDAO.getDistrictsWithNewSplitted(stateId);
		
		List<IdNameVO> finalList =  new ArrayList<IdNameVO>();
		if(rslt!=null && rslt.size()>0){
			
			for(Object[] temp:rslt){
					IdNameVO vo = new IdNameVO();
		        	vo.setId(Long.valueOf(temp[0].toString()));
		        	vo.setName(temp[1].toString());
		        	finalList.add(vo);
				}
				
        	}
		return finalList;
	}
	
	public List<IdNameVO> getLocationNameByLocationIds(List<Long> locationIds, Long locationLevel){
		LOG.debug(" ENTERED INTO getLocationNameByLocationIds");
		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
		try{
			if(locationLevel.longValue() == 1l){
				if(locationIds.get(0).longValue()==1L){
					IdNameVO vo = new IdNameVO();
					vo.setId(1l);
					vo.setName("Andhra Pradesh State");
					finalList.add(vo);
				}else if(locationIds.get(0).longValue()==36L){
					IdNameVO vo = new IdNameVO();
					vo.setId(36l);
					vo.setName("Telangana State");
					finalList.add(vo);
				}
				else if(locationIds.get(0).longValue()!=1L){
					IdNameVO vo = new IdNameVO();
					vo.setId(36l);
					vo.setName("Telangana State");
					finalList.add(vo);
				}
			}else if(locationLevel.longValue() == 2l){
				List<Object[]> distRslt = districtDAO.getDistrictDetailsByDistrictIds(locationIds);
				if(distRslt!=null && distRslt.size()>0){
					for(Object[] obj:distRslt){
						IdNameVO vo = new IdNameVO();
						vo.setId(Long.valueOf(obj[0].toString()));
						vo.setName(obj[1].toString() + " District");
						finalList.add(vo);
					}
					
				}
			}else if(locationLevel.longValue() == 3l){
				List<Object[]> constRslt = constituencyDAO.getConstituencyNameByConstituencyIdsList(locationIds);
				if(constRslt!=null && constRslt.size()>0){
					for(Object[] obj:constRslt){
						IdNameVO vo = new IdNameVO();
						vo.setId(Long.valueOf(obj[0].toString()));
						vo.setName(obj[1].toString()  + " Constituency");
						finalList.add(vo);
					}
					
				}
			}else if(locationLevel.longValue() == 4l){
				List<Object[]> tehsilRslt = tehsilDAO.getTehsilNameByTehsilIdsList(locationIds);
				if(tehsilRslt!=null && tehsilRslt.size()>0){
					for(Object[] obj:tehsilRslt){
						IdNameVO vo = new IdNameVO();
						vo.setId(Long.valueOf(obj[0].toString()));
						vo.setName(obj[1].toString() + " Mandal");
						finalList.add(vo);
					}
					
				}
			}else if(locationLevel.longValue() == 5l){
				List<Object[]> lebRslt = localElectionBodyDAO.findByLocalElecBodyIds(locationIds);
				if(lebRslt!=null && lebRslt.size()>0){
					for(Object[] obj:lebRslt){
						IdNameVO vo = new IdNameVO();
						vo.setId(Long.valueOf(obj[0].toString()));
						vo.setName(obj[1].toString()+" "+obj[2].toString());
						finalList.add(vo);
					}
					
				}
			}else if(locationLevel.longValue() == 6l){
				List<Object[]> divisions = assemblyLocalElectionBodyWardDAO.getWardDetailsById(locationIds);
				if(divisions!=null && divisions.size()>0){
					for(Object[] obj:divisions){
						IdNameVO vo = new IdNameVO();
						vo.setId(Long.valueOf(obj[0].toString()));
						vo.setName(obj[1].toString() + " Division");
						finalList.add(vo);
					}
					
				}
			}else if(locationLevel.longValue() == 7l){
				List<Object[]> panchayats = panchayatDAO.getPanchayatsByPanchayatIdsList(locationIds);
				if(panchayats!=null && panchayats.size()>0){
					for(Object[] obj:panchayats){
						IdNameVO vo = new IdNameVO();
						vo.setId(Long.valueOf(obj[0].toString()));
						vo.setName(obj[1].toString() + " Village");
						finalList.add(vo);
					}
					
				}
			}else if(locationLevel.longValue() == 8l){
				List<Object[]> wards = constituencyDAO.getWardDetailsById(locationIds);
				if(wards!=null && wards.size()>0){
					for(Object[] obj:wards){
						IdNameVO vo = new IdNameVO();
						vo.setId(Long.valueOf(obj[0].toString()));
						vo.setName(obj[1].toString() + " Ward");
						finalList.add(vo);
					}
					
				}
			}
		}catch (Exception e) {
			LOG.error(" ERROR in getLocationNameByLocationIds",e);
		}
		return finalList;
	}
	
	public void getLocationNameByLocationTypeAndId(Long committeeLevelId,Long locationValue,String location)
	{
		try {
			if(locationValue != null && locationValue.longValue()>0L)
			{

				if(committeeLevelId.longValue() == IConstants.VILLAGE_COMMITTEE_LEVEL_ID)
				{
					location = panchayatDAO.get(locationValue).getPanchayatName()+" Panchayat ";
				}
				else if(committeeLevelId.longValue() == IConstants.WARD_COMMITTEE_LEVEL_ID)
				{
					Constituency constituency = constituencyDAO.get(locationValue);
		    		location = constituency.getName()+" ( "+constituency.getLocalElectionBody().getName()+" "+constituency.getLocalElectionBody().getElectionType().getElectionType()+" )";
				}
				else if(committeeLevelId.longValue() == IConstants.MANDAL_COMMITTEE_LEVEL_ID)
				{
					location = tehsilDAO.get(locationValue).getTehsilName()+" Mandal ";
				}
				else if(committeeLevelId.longValue() == IConstants.TOWN_COMMITTEE_LEVEL_ID || committeeLevelId.longValue() == IConstants.DIVISION_COMMITTEE_LEVEL_ID)
				{
					LocalElectionBody localbody = localElectionBodyDAO.get(locationValue);
		    		if(localbody.getElectionType().getElectionTypeId() != 7L)
		    			location = localbody.getName()+" "+localbody.getElectionType().getElectionType();
				}				
				else if(committeeLevelId.longValue() == IConstants.DISTRICT_COMMITTEE_LEVEL_ID)
				{
					location = districtDAO.get(locationValue).getDistrictName();
				}
				else if(committeeLevelId.longValue() == IConstants.STATE_COMMITTEE_LEVEL_ID)
				{
					location = stateDAO.get(locationValue).getStateName();
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getLocationNameByLocationTypeAndId() method, Exception - ",e);
		}
	}
	
	public List<IdNameVO> getAllCadreDeleteReasons(){
		
		List<IdNameVO> listVo = new ArrayList<IdNameVO>();
		try{
			
			List<Object[]> deleteResons = cadreDeleteReasonDAO.getAllCadreDeleteReasons();
			
			if(deleteResons !=null && deleteResons.size()>0){
				for (Object[] objects : deleteResons) {
					IdNameVO vo = new IdNameVO();
					vo.setId(objects[0] !=null ? (Long)objects[0] : 0l );//resonId
					vo.setName(objects[1] !=null ? objects[1].toString() :"");//reason
					
					listVo.add(vo);
				}
			}
			
			return listVo;
		}catch (Exception e) {
			LOG.error("Exception Occured in getAllCadreDeleteReasons() method, Exception - ",e);
		}
		return listVo;
	}
	public ResultStatus saveRemovingCadreDetailsAction(Long cadreId,Long reasonId,String remark,Long userId){
		
		ResultStatus finalRs = new ResultStatus();
		try{
			TdpCadre cadreData= tdpCadreDAO.get(cadreId);
			if(cadreData !=null){
				DateUtilService dt = new DateUtilService();
				cadreRegistrationService.saveDataToHistoryTable(cadreData);
				cadreData.setCadreDeleteReasonId(reasonId);
				cadreData.setIsDeleted("MD");
				cadreData.setDeleteRemark(remark);
				cadreData.setUpdatedTime(dt.getCurrentDateAndTime());
				cadreData.setUpdatedWebUserId(userId);
				tdpCadreDAO.save(cadreData);
				
				finalRs.setResultCode(0);
			}
			return finalRs;
		}catch (Exception e) {
			finalRs.setResultCode(1);
			LOG.error("Exception Occured in saveRemovingCadreDetailsAction() method, Exception - ",e);
		}
		return finalRs;
	}
    public ResultStatus updateMobileNumberAndCasteForCadre(Long cadreId,String mobileNo,Long casteId,Long userId){
		
		ResultStatus finalRs = new ResultStatus();
		DateUtilService dt = new DateUtilService();
		try{
			
			if(cadreId !=null && cadreId.longValue()>0l){
				
			   TdpCadre cadreData= tdpCadreDAO.get(cadreId);
			   cadreRegistrationService.saveDataToHistoryTable(cadreData);
				
				cadreData.setMobileNo(mobileNo);
				cadreData.setCasteStateId(casteId);
				cadreData.setUpdatedTime(dt.getCurrentDateAndTime());
				cadreData.setUpdatedWebUserId(userId);
				
				tdpCadreDAO.save(cadreData);
				
				finalRs.setResultCode(0);
			}
			return finalRs;
		}catch (Exception e) {
			finalRs.setResultCode(1);
			LOG.error("Exception Occured in updateMobileNumberForCadre() method, Exception - ",e);
		}
		return finalRs;
	}
	public List<Long> getAllRemovedCadre(){
		List<Long> cadreIds =null;
		try{
			 cadreIds = tdpCadreDAO.getAllRemovedCadre();
			return cadreIds;
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getAllRemovedCadre() method, Exception - ",e);
		}
		
		return cadreIds;
	}
	
	public ResultStatus saveActivityDetails(final ActivityVO activityVO,final Long userId){
		ResultStatus resultStatus = new ResultStatus();
			 try {
				 String status = (String) transactionTemplate.execute(new TransactionCallback() {
					 public Object doInTransaction(TransactionStatus status) {
						 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						 ActivityLocationInfo activityLocationInfo = null;
						 if(activityVO != null){
							 Long activityScopeId = activityVO.getActivityLevelId();
							 List<ActivityVO> activityList = activityVO.getActivityVoList();
							 if(activityList != null && activityList.size() > 0){
								 for (ActivityVO activityvo : activityList) {
									 if(activityvo != null)
									 {
										 activityLocationInfo = new ActivityLocationInfo();
										 if((activityvo.getConductedDate() != null && activityvo.getConductedDate().trim().length() > 0) || 
												( activityvo.getPlannedDate() != null && activityvo.getPlannedDate().trim().length() > 0 )){
											
											 Long locationLevel = activityvo.getLocationLevel();
											 String locationTypeflagId = "";
											 if(locationLevel <5)
												 locationTypeflagId = activityvo.getLocationValue().toString().trim().substring(0, 1);										
											 else
												 locationTypeflagId = activityvo.getLocationValue().toString().trim();
											
											 Long locationLevelId = null;
											 if(locationLevel.longValue() == 1L)
											 {
												 if(locationTypeflagId.equalsIgnoreCase("1"))
														locationLevelId = 6L;
												 else if(locationTypeflagId.equalsIgnoreCase("2"))
														locationLevelId = 8L;
											 }
											 else if(locationLevel.longValue() == 2L)
											 {
												 	if(locationTypeflagId.equalsIgnoreCase("1"))
														locationLevelId = 7L;
												 	else if(locationTypeflagId.equalsIgnoreCase("2"))
														locationLevelId = 5L;
												 	else if(locationTypeflagId.equalsIgnoreCase("3"))
														locationLevelId = 9L;
											 }
											 else if(locationLevel.longValue() == 3L)
												 locationLevelId = 11L;
											 else if(locationLevel.longValue() == 4L)
												 locationLevelId = 10L;
											 else if(locationLevel.longValue() == 5L)
												 locationLevelId = 13L;
											 
											activityLocationInfo.setConstituencyId(activityVO.getConstituencyId());
											activityLocationInfo.setActivityScopeId(activityScopeId);
											activityLocationInfo.setLocationLevel(locationLevelId);
											if(locationLevelId.longValue() == 5L || locationLevelId.longValue() == 6L || locationLevelId.longValue() == 7L
													 || locationLevelId.longValue() == 8L || locationLevelId.longValue() == 9L)
												activityLocationInfo.setLocationValue(Long.valueOf(activityvo.getLocationValue().toString().trim().substring(1)));
											else if(locationLevelId.longValue() == 13L){
												activityLocationInfo.setLocationValue(Long.valueOf(activityvo.getLocationValue()));
												activityLocationInfo.setConstituencyId(activityLocationInfo.getLocationValue());
											}
											else
												activityLocationInfo.setLocationValue(Long.valueOf(activityvo.getLocationValue()));
											
											try {
												if(activityvo.getPlannedDate() != null && activityvo.getPlannedDate().trim().length() > 0)
													activityLocationInfo.setPlannedDate(sdf.parse(activityvo.getPlannedDate() != null ? activityvo.getPlannedDate().toString():""));
												else
													activityLocationInfo.setPlannedDate(null);
												if(activityvo.getConductedDate() != null && activityvo.getConductedDate().trim().length() > 0)
													activityLocationInfo.setConductedDate(sdf.parse(activityvo.getConductedDate() != null ? activityvo.getConductedDate().toString():""));
												else
													activityLocationInfo.setConductedDate(null);
											} catch (ParseException e) {
												LOG.error("Exception rised in saveActivityDetails()",e);
											}

											List<Long> availableIds = activityLocationInfoDAO.isAlreadyAvailableActivityLocationDtls(
													activityLocationInfo.getActivityScopeId(), activityLocationInfo.getLocationLevel(), activityLocationInfo.getLocationValue());
											if(availableIds != null && availableIds.size()>0){
												ActivityLocationInfo existingVO = activityLocationInfoDAO.get(availableIds.get(0));
												//existingVO.setConstituencyId(activityVO.getConstituencyId());
												//existingVO.setActivityScopeId(activityScopeId);
												//existingVO.setLocationLevel(locationLevelId);
												//existingVO.setLocationValue(Long.valueOf(activityvo.getLocationValue().toString().substring(1)));
												try {
													if(activityvo.getPlannedDate() != null && activityvo.getPlannedDate().trim().length() > 0)
														existingVO.setPlannedDate(sdf.parse(activityvo.getPlannedDate() != null ? activityvo.getPlannedDate().toString():""));
													
													if(activityvo.getConductedDate() != null && activityvo.getConductedDate().length() > 0)
														existingVO.setConductedDate(sdf.parse(activityvo.getConductedDate() != null ? activityvo.getConductedDate().toString():""));
													
												} catch (ParseException e) {
													LOG.error("Exception rised in saveActivityDetails()",e);
												}
												existingVO.setUpdatedBy(userId);
												existingVO.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
												activityLocationInfo = activityLocationInfoDAO.save(existingVO);
											}	
											else{
												activityLocationInfo.setInsertedBy(userId);
												activityLocationInfo.setUpdatedBy(userId);
												activityLocationInfo.setInsertionTime(dateUtilService.getCurrentDateAndTime());
												activityLocationInfo.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
												activityLocationInfo = activityLocationInfoDAO.save(activityLocationInfo);
											}
											activityLocationInfo.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
											//activityLocationInfoDAO.isAlreadAvailableActivity();
										 }
										 else
										 {
											 String locationTypeflagId = activityvo.getLocationValue().toString().substring(0, 1);										
											 Long locationLevel = activityvo.getLocationLevel();
											 Long locationLevelId = null;
											 if(locationLevel.longValue() == 1L)
											 {
												 if(locationTypeflagId.equalsIgnoreCase("1"))
														locationLevelId = 6L;
												 else if(locationTypeflagId.equalsIgnoreCase("2"))
														locationLevelId = 8L;
											 }
											 else if(locationLevel.longValue() == 2L)
											 {
												 	if(locationTypeflagId.equalsIgnoreCase("1"))
														locationLevelId = 7L;
												 	else if(locationTypeflagId.equalsIgnoreCase("2"))
														locationLevelId = 5L;
												 	else if(locationTypeflagId.equalsIgnoreCase("3"))
														locationLevelId = 9L;
											 }
											 else if(locationLevel.longValue() == 3L)
												 locationLevelId = 11L;
											 else if(locationLevel.longValue() == 4L)
												 locationLevelId = 10L;
											 else if(locationLevel.longValue() == 5L)
												 locationLevelId = 13L;
											activityLocationInfo = new ActivityLocationInfo();
											activityLocationInfo.setConstituencyId(activityVO.getConstituencyId());
											activityLocationInfo.setActivityScopeId(activityScopeId);
											activityLocationInfo.setLocationLevel(locationLevelId);
											if(locationLevelId.longValue() == 5L || locationLevelId.longValue() == 6L || locationLevelId.longValue() == 7L
													 || locationLevelId.longValue() == 8L || locationLevelId.longValue() == 9L)
												activityLocationInfo.setLocationValue(Long.valueOf(activityvo.getLocationValue().toString().substring(1)));
											else if(locationLevelId.longValue() == 13L){
												activityLocationInfo.setLocationValue(Long.valueOf(activityvo.getLocationValue()));
												activityLocationInfo.setConstituencyId(activityLocationInfo.getLocationValue());
											}
											else
												activityLocationInfo.setLocationValue(Long.valueOf(activityvo.getLocationValue()));
											
											 List<Long> availableIds = activityLocationInfoDAO.isAlreadyAvailableActivityLocationDtls(
														activityLocationInfo.getActivityScopeId(), activityLocationInfo.getLocationLevel(), activityLocationInfo.getLocationValue());
												if(availableIds != null && availableIds.size()>0){
													ActivityLocationInfo existingVO = activityLocationInfoDAO.get(availableIds.get(0));
													
													try {
														if(activityvo.getPlannedDate() != null && activityvo.getPlannedDate().length() > 0)
															existingVO.setPlannedDate(sdf.parse(activityvo.getPlannedDate() != null ? activityvo.getPlannedDate().toString():""));
														else
															existingVO.setPlannedDate(null);
														if(activityvo.getConductedDate() != null && activityvo.getConductedDate().length() > 0)
															existingVO.setConductedDate(sdf.parse(activityvo.getConductedDate() != null ? activityvo.getConductedDate().toString():""));
														else
															existingVO.setConductedDate(null);
													} catch (ParseException e) {
														LOG.error("Exception rised in saveActivityDetails()",e);
													}

													activityLocationInfo = activityLocationInfoDAO.save(existingVO);
												}else
												{
													activityLocationInfo = activityLocationInfoDAO.save(activityLocationInfo);
												}
										 }
										 try {
											 
											 Map<Long,List<Long>> activityDateTypeMap = new HashMap<Long, List<Long>>();
											 List<ActivityLocationInfoDates> updateDates = activityLocationInfoDatesDAO.getActivityLocationInfoDates(activityLocationInfo.getActivityLocationInfoId(),activityvo.getDay());
											 
											 if(updateDates != null && updateDates.size() > 0)
											 {
												 for(ActivityLocationInfoDates params : updateDates)
												 {
													 List<Long> IDs = activityDateTypeMap.get(params.getActivityDateTypeId());
													 if(IDs == null || IDs.size() == 0)
													 {
														 IDs = new ArrayList<Long>();
													 }
													 IDs.add(params.getActivityLocationInfoDatesId());
													 activityDateTypeMap.put(params.getActivityDateTypeId(),IDs);
												 }
											 }
										 if(updateDates != null && updateDates.size() > 0)
										 {
											 List<Long> activityLocInfoIds = null;
											
											if(activityvo.getPlannedDate() != null ){
												for(ActivityLocationInfoDates activityLocationInfoDates : updateDates)
												{
													if(activityvo.getPlannedDate() != null){
														if(activityLocationInfoDates.getActivityDateTypeId() == 1)
														{
															activityLocInfoIds = activityDateTypeMap.get(activityLocationInfoDates.getActivityDateTypeId());
														}
													}
												}
											
													if(activityLocInfoIds == null || activityLocInfoIds.size() == 0)
													{
														ActivityLocationInfoDates	activityLocationInfoDates1 = new ActivityLocationInfoDates();
														activityLocationInfoDates1.setActivityDateTypeId(1l);
														activityLocationInfoDates1.setActivityLocationInfoId(activityLocationInfo.getActivityLocationInfoId());
														if(activityvo.getPlannedDate() != null && !activityvo.getPlannedDate().isEmpty())
														activityLocationInfoDates1.setActivityDate(sdf.parse(activityvo.getPlannedDate() != null ? activityvo.getPlannedDate().toString():""));
														else
															activityLocationInfoDates1.setActivityDate(null)	;
														activityLocationInfoDates1.setDay(activityvo.getDay());
														 activityLocationInfoDatesDAO.save(activityLocationInfoDates1);	
													}
													else
													{
														for(Long id : activityLocInfoIds)
														{
															ActivityLocationInfoDates activityLocationInfoDates = activityLocationInfoDatesDAO.get(id);
															activityLocationInfoDates.setActivityDateTypeId(1l);
															activityLocationInfoDates.setActivityLocationInfoId(activityLocationInfo.getActivityLocationInfoId());
															if(activityvo.getPlannedDate() != null && !activityvo.getPlannedDate().isEmpty())
																activityLocationInfoDates.setActivityDate(sdf.parse(activityvo.getPlannedDate() != null ? activityvo.getPlannedDate().toString():""));
																else
																	activityLocationInfoDates.setActivityDate(null)	;
															activityLocationInfoDates.setDay(activityvo.getDay());
															 activityLocationInfoDatesDAO.save(activityLocationInfoDates);
														}
													}
											}
												
													
														List<Long> activityLocInfoIds1 = null;
														if(activityvo.getConductedDate() != null){
															
															for(ActivityLocationInfoDates activityLocationInfoDates : updateDates)
															{
																if(activityvo.getConductedDate() != null){
																	if(activityLocationInfoDates.getActivityDateTypeId() == 2)
																	{
																		activityLocInfoIds1 = activityDateTypeMap.get(activityLocationInfoDates.getActivityDateTypeId());
																	}
																}
															}
															if(activityLocInfoIds1 == null || activityLocInfoIds1.size() == 0)
															{
																ActivityLocationInfoDates	activityLocationInfoDates1 = new ActivityLocationInfoDates();
																activityLocationInfoDates1.setActivityDateTypeId(2l);
																activityLocationInfoDates1.setActivityLocationInfoId(activityLocationInfo.getActivityLocationInfoId());
																if(activityvo.getConductedDate() != null && !activityvo.getConductedDate().isEmpty())
																	activityLocationInfoDates1.setActivityDate(sdf.parse(activityvo.getConductedDate() != null ? activityvo.getConductedDate().toString():""));
																	else
																		activityLocationInfoDates1.setActivityDate(null)	;
																activityLocationInfoDates1.setDay(activityvo.getDay());
																 activityLocationInfoDatesDAO.save(activityLocationInfoDates1);	
															}
															else
															{
																for(Long id : activityLocInfoIds1)
																{
																	ActivityLocationInfoDates activityLocationInfoDates = activityLocationInfoDatesDAO.get(id);
																	activityLocationInfoDates.setActivityDateTypeId(2l);
																	activityLocationInfoDates.setActivityLocationInfoId(activityLocationInfo.getActivityLocationInfoId());
																	if(activityvo.getConductedDate() != null && !activityvo.getConductedDate().isEmpty())
																		activityLocationInfoDates.setActivityDate(sdf.parse(activityvo.getConductedDate() != null ? activityvo.getConductedDate().toString():""));
																		else
																			activityLocationInfoDates.setActivityDate(null)	;
																	activityLocationInfoDates.setDay(activityvo.getDay());
																	 activityLocationInfoDatesDAO.save(activityLocationInfoDates);
																}
															}
														
														}
										 }
									 else
										 {
											 ActivityLocationInfoDates activityLocationInfoDates = new ActivityLocationInfoDates();
											 if(activityvo.getPlannedDate() != null && !activityvo.getPlannedDate().isEmpty()){
												 	activityLocationInfoDates.setActivityDate(sdf.parse(activityvo.getPlannedDate() != null ? activityvo.getPlannedDate().toString():""));
													activityLocationInfoDates.setActivityDateTypeId(1l);
													activityLocationInfoDates.setActivityLocationInfoId(activityLocationInfo.getActivityLocationInfoId());
													activityLocationInfoDates.setDay(activityvo.getDay());
													activityLocationInfoDatesDAO.save(activityLocationInfoDates);	
												} 
												
												 if(activityvo.getConductedDate() != null && !activityvo.getConductedDate().isEmpty()){
													activityLocationInfoDates.setActivityDate(sdf.parse(activityvo.getConductedDate() != null ? activityvo.getConductedDate().toString():""));
													activityLocationInfoDates.setActivityDateTypeId(2l);
													activityLocationInfoDates.setActivityLocationInfoId(activityLocationInfo.getActivityLocationInfoId());
													activityLocationInfoDates.setDay(activityvo.getDay());
													activityLocationInfoDatesDAO.save(activityLocationInfoDates);	
												}
												
											 
										 }
										 } 
										 catch (ParseException e) {
												
												e.printStackTrace();
											}
									 }
								}
							 }
							 
							
							  
							 
							 
						 }
						 
						 return "success";
						 
					 }});
				 
				 if(status != null)
				 {
					 resultStatus.setResultCode(0);
					 resultStatus.setMessage("success");
					 resultStatus.setExceptionMsg(status);
				 }
				 else
				 {
					 resultStatus.setResultCode(1);
					 resultStatus.setMessage("error");
					 resultStatus.setExceptionMsg("0");
				 }
			} catch (Exception e) {
				LOG.error("Exception rised in saveActivityDetails()",e);
				 resultStatus.setResultCode(1);
				 resultStatus.setMessage("error");
			}
			 return resultStatus;
		 }
		 
	
	
	public BasicVO getActivityTypeList()
	{
		BasicVO basicVO = null;
		/*try {
			List<ActivitySubType> activityTypeLsit = activitySubTypeDAO.getAll();
			if(activityTypeLsit != null && activityTypeLsit.size()>0)
			{
				List<BasicVO> activitiesLsit = new ArrayList<BasicVO>();
				for (ActivitySubType activityType : activityTypeLsit) {
					if(activityType.getIsDeleted().equalsIgnoreCase("N")){
						BasicVO vo = new BasicVO();
						vo.setId(activityType.getActivitySubTypeId());
						vo.setName(activityType.getSubType());
						activitiesLsit.add(vo);
					}
				}
				
				if(activitiesLsit != null && activitiesLsit.size()>0)
				{
					basicVO = new BasicVO();
					basicVO.setPanchayatVoterInfo(activitiesLsit);
				}
			}
		}*/ 
		try{
			List<Object[]> activityTypeLsit = activitySubTypeDAO.activityTypeNames();
			if(activityTypeLsit != null && activityTypeLsit.size()>0)
			{
				List<BasicVO> activitiesLsit = new ArrayList<BasicVO>();
				for (Object[] activityType : activityTypeLsit) {
					
						BasicVO vo = new BasicVO();
						vo.setId((Long) (activityType[0] != null?activityType[0]:0L));
						vo.setName(activityType[1] != null?activityType[1].toString():"");
						activitiesLsit.add(vo);
					
				}
				if(activitiesLsit != null && activitiesLsit.size()>0)
				{
					basicVO = new BasicVO();
					basicVO.setPanchayatVoterInfo(activitiesLsit);
				}
			}
		}
			catch (Exception e) {
			LOG.error("Exception Occured in getActivityTypeList() method, Exception - ",e);
		}
		return basicVO;
	}
	
	public List<IdNameVO> getActivityLevelsList()
	{
		List<IdNameVO> idNameVoList = null;
		try {
			List<Object[]> activityTypeLsit = activityLevelDAO.actvityLvlOrder();
			if(activityTypeLsit != null && activityTypeLsit.size()>0)
			{
				idNameVoList = new ArrayList<IdNameVO>();
				for (Object[] object : activityTypeLsit) {
					IdNameVO vo = new IdNameVO();
					vo.setId(Long.valueOf(object[0].toString().trim()));
					vo.setName(object[1] != null ? object[1].toString():"");
					idNameVoList.add(vo);
				}
				
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getActivityTypeList() method, Exception - ",e);
		}
		return idNameVoList;
	}
	
	public List<IdNameVO> getActivitiesListByTypeAndLevel(Long activityTypeId,Long  activityLevelId)
	{
		List<IdNameVO> idNameVoList = null;
		try {
			List<Object[]> activityTypeLsit = activityScopeDAO.getActivitiesListByTypeAndLevel(activityTypeId, activityLevelId);
			if(activityTypeLsit != null && activityTypeLsit.size()>0)
			{
				idNameVoList = new ArrayList<IdNameVO>();
				for (Object[] activityType : activityTypeLsit) {
					IdNameVO vo = new IdNameVO();
					if(activityType[0] != null && Long.valueOf(activityType[0].toString().trim()).longValue() >0L)
					{
						vo.setId(Long.valueOf(activityType[0].toString().trim()));
						vo.setName(activityType[1] != null ? activityType[1].toString():"");
						idNameVoList.add(vo);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getActivitiesListByTypeAndLevel() method, Exception - ",e);
		}
		return idNameVoList;
	}
	
	
	public List<LocationWiseBoothDetailsVO> getMandalMunicCorpDetailsByConstituencyList(List<Long> constituencyIds){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		LocationWiseBoothDetailsVO vo = null;
		List<Long> greaterCorpIds = new ArrayList<Long>();
		List<SelectOptionVO> locations = regionServiceDataImp.getAllMandalsByAllConstituencies(constituencyIds);
		List<Object[]> localBodies = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituencyList(constituencyIds);
	        for(SelectOptionVO location:locations){
	        	vo = new LocationWiseBoothDetailsVO();
	        	vo.setLocationId(Long.valueOf("2"+location.getId()));
	        	vo.setLocationName(location.getName()+" Mandal");
	        	locationsList.add(vo);
	        }
	        for(Object[] localBodi:localBodies){
	        	Long localBdyId = (Long)localBodi[0];
	        	if(!(localBdyId.longValue() == 20l ||  localBdyId.longValue() == 124l || localBdyId.longValue() == 119l)){
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("1"+localBodi[0].toString()));
		        	vo.setLocationName(localBodi[1].toString());
		        	locationsList.add(vo);
	        	}else{
	        		greaterCorpIds.add(localBdyId);
	        	}
	        }
	        if(greaterCorpIds.size() > 0){
        	  for(Long id:greaterCorpIds){
        		  List<Object[]>  wards = assemblyLocalElectionBodyWardDAO.findWardsByLocalBodyConstituncyListIds(id, constituencyIds);
        		  for(Object[] location:wards){
        			  vo = new LocationWiseBoothDetailsVO();
  		        	vo.setLocationId(Long.valueOf("3"+location[0].toString()));
  		        	vo.setLocationName(location[1].toString());
  		        	locationsList.add(vo);
        		  }
        	  }
	        }
	        return locationsList;
	}
	
	public LocationWiseBoothDetailsVO1 getActivityLocationDetails(String isChecked,Long activityScopeId,Long activityLevelId,String searchBy,Long locationId,
			 String searchStartDateStr,String searchEndDateStr,Long constituencyId,Long optionId,Long questionId)
	{
		LocationWiseBoothDetailsVO1 returnVO = null;	
		try {
			
			if(activityLevelId.longValue() == 4l){
				List<LocationWiseBoothDetailsVO> returnStateList = new ArrayList<LocationWiseBoothDetailsVO>();
				String[] staArr = IConstants.STATIC_STATE_IDS.split(",");
				List<Long> stateIds = new ArrayList<Long>();
				if(staArr != null && staArr.length > 0){
					for (int i = 0; i < staArr.length; i++) {
						stateIds.add(Long.valueOf(staArr[i].toString()));
					}
				}
				List<Object[]> stateList = stateDAO.getAllStatesByStateIds(stateIds);
				if(commonMethodsUtilService.isListOrSetValid(stateList)){
					for (Object[] obj : stateList) {
						LocationWiseBoothDetailsVO vo = new LocationWiseBoothDetailsVO();
						
						vo.setLocationId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						vo.setLocationName(obj[1] != null ? obj[1].toString():"");
						
						returnStateList.add(vo);
					}
				}
				returnVO = new LocationWiseBoothDetailsVO1();
				returnVO.getResult().addAll(returnStateList);
			}
			else{
			List<LocationWiseBoothDetailsVO> returnList = null;
			List<Long> updatedLocationIdsList  = new ArrayList<Long>(0);
			List<Long> notUpdatedLocationIdsList  = new ArrayList<Long>(0);
			List<LocationWiseBoothDetailsVO> reportList = null;
			List<Long> constituencyIds = new ArrayList<Long>(0);
			Map<Long,List<ActivityVO>> activityMap = new LinkedHashMap<Long, List<ActivityVO>>(0);
			//List<LocationWiseBoothDetailsVO> mandalList = new ArrayList<LocationWiseBoothDetailsVO>(0);
			//List<LocationWiseBoothDetailsVO> panchayatList=new ArrayList<LocationWiseBoothDetailsVO>(0);
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			
			Date startDate = null;
			Date endDate = null;
			
			if(searchStartDateStr != null && searchStartDateStr.trim().length() > 0 && searchEndDateStr != null && searchEndDateStr.trim().length() > 0){
				startDate = format.parse(searchStartDateStr);
				endDate = format.parse(searchEndDateStr);
			}
			Map<Long,List<Long>> questionResponsesMap = null;
			if(optionId != null && optionId>0L){
				questionResponsesMap = getActivityLocationWiseQuestionsData(activityScopeId,questionId,constituencyId);				
				if(questionResponsesMap == null || questionResponsesMap.size() == 0){
					returnVO = new LocationWiseBoothDetailsVO1();
					return returnVO;
				}	
			}
			if(activityScopeId != null && activityScopeId.longValue()>0L)
			{
				 List<Object[]> updatedList= activityLocationInfoDAO.getUpdatedLocationsListForScope(activityScopeId,startDate,endDate);
				 if(updatedList != null && updatedList.size()>0)
				 {
					 for (Object[] locations : updatedList) {
						 Long locationValue = locations[0] != null ? Long.valueOf(locations[0].toString()):0L;
						 String planDate = locations[1] != null ? locations[1].toString():null;
						 String conductedDate = locations[2] != null ? locations[2].toString():null;
						 Long locationlevel = locations[3] != null ? Long.valueOf(locations[3].toString()):0L;
						 String status = locations[4] != null ? locations[4].toString():null;
						 
						 Date planDateStr = planDate != null ? format1.parse(planDate):null;
						 Date conductedDateStr = conductedDate != null ? format1.parse(conductedDate):null;

						 String locationLevelId = "";
						 if(locationlevel.longValue() == 6L || locationlevel.longValue() == 7L)
						 {
							 locationLevelId = "1";
						 }
						 else if(locationlevel.longValue() == 8L || locationlevel.longValue() == 5L)
						 {
							 locationLevelId = "2";
						 }
						 else if(locationlevel.longValue() == 9L)
						 {
							 locationLevelId = "3";
						 }
						 String finalIdStr = locationLevelId+""+locationValue;
						 Long finalLocationId = Long.valueOf(finalIdStr);
						 List<ActivityVO> list = new ArrayList<ActivityVO>(0);
						 if(activityMap.get(locationValue) != null)
						 {
							 list = activityMap.get(locationValue);
						 }
						 ActivityVO vo = new ActivityVO();
						 if(planDateStr != null)
							 vo.setPlannedDate(format.format(planDateStr).toString());
						 if(conductedDateStr != null)
							 vo.setConductedDate(format.format(conductedDateStr).toString());
						 vo.setLocationValue(finalLocationId);
						 vo.setLocationLevel(locationlevel);
						 vo.setStatus(status);
						 
						 list.add(vo);
						 activityMap.put(locationValue, list);
						// if(conductedDate != null )
			               updatedLocationIdsList.add(locationValue);
			             //else
			             //  notUpdatedLocationIdsList.add(locationValue);
						 //updatedLocationIdsList.add(locationValue);
					}
				 }
			}
			if(activityLevelId != null && activityLevelId.longValue()>0L)
			{
				if(activityLevelId.longValue() == 2L)
				{
					if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.DISTRICT))
					{
						constituencyIds = constituencyDAO.getConstituencyIdsByDistrictId(locationId,IConstants.ASSEMBLY_ELECTION_TYPE_ID);
						reportList = getMandalMunicCorpDetailsByConstituencyList(constituencyIds);
					}
					else if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.CONSTITUENCY))
					{
						constituencyIds.add(locationId);
						reportList = getMandalMunicCorpDetailsByConstituencyList(constituencyIds);
					}
					else if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.MANDAL))
					{
						String locationTypeflagId = locationId.toString().substring(0, 1);
						String locatonId = locationId.toString().substring(1);
						reportList = new ArrayList<LocationWiseBoothDetailsVO>(0);
						if(locationTypeflagId.equalsIgnoreCase("2")){
							Tehsil tehsil= tehsilDAO.get(Long.valueOf(locatonId));
							if(tehsil != null)
								reportList.add(new LocationWiseBoothDetailsVO(locationId,tehsil.getTehsilName()));
						}
						else if(locationTypeflagId.equalsIgnoreCase("1")){
							LocalElectionBody localbody= localElectionBodyDAO.get(Long.valueOf(locatonId));
							if(localbody != null)
								reportList.add(new LocationWiseBoothDetailsVO(locationId,localbody.getName()));
						}
					}
					//if(reportList != null && reportList.size()>0)
					//	mandalList.addAll(reportList);
				}
				else if(activityLevelId.longValue() == 1L)
				{
					if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.CONSTITUENCY))
					{
						reportList = getPanchayatWardDivisionDetailsNew(locationId);
					}
					else if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.MANDAL))
					{
						reportList = getPanchayatWardByMandalId(locationId.toString(),constituencyId);
					}
					else if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.PANCHAYAT))
					{
						String locationTypeflagId = locationId.toString().substring(0, 1);
						String locatonId = locationId.toString().substring(1);
						reportList = new ArrayList<LocationWiseBoothDetailsVO>(0);
						if(locationTypeflagId.equalsIgnoreCase("2")){
							Constituency constituency= constituencyDAO.get(Long.valueOf(locatonId));
							if(constituency != null)
								reportList.add(new LocationWiseBoothDetailsVO(locationId,constituency.getName()));
						}
						else if(locationTypeflagId.equalsIgnoreCase("1")){
							Panchayat panchayat= panchayatDAO.get(Long.valueOf(locatonId));
							if(panchayat != null)
								reportList.add(new LocationWiseBoothDetailsVO(locationId,panchayat.getPanchayatName()));
						}
					}
					
					//if(reportList != null && reportList.size()>0)
					//	panchayatList.addAll(reportList);
				}
				else if(activityLevelId.longValue() == 5l)
				{
					if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.CONSTITUENCY))
					{
						reportList = new ArrayList<LocationWiseBoothDetailsVO>(0);
						Constituency constituency= constituencyDAO.get(Long.valueOf(locationId));
						if(constituency != null)
							reportList.add(new LocationWiseBoothDetailsVO(locationId,constituency.getName()));
					}
					else if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.DISTRICT))
					{
						reportList = getConstituencyByDistrictId(locationId);
					}
					
					
					//if(reportList != null && reportList.size()>0)
					//	panchayatList.addAll(reportList);
				}
				else if(activityLevelId.longValue() == 3l)
				{
					/*if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.CONSTITUENCY))
					{
						reportList = new ArrayList<LocationWiseBoothDetailsVO>(0);
						Constituency constituency= constituencyDAO.get(Long.valueOf(locationId));
						if(constituency != null)
							reportList.add(new LocationWiseBoothDetailsVO(locationId,constituency.getName()));
					}
					else
					if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.DISTRICT))
					{*/
					List<Object[]> list = null;
					reportList = new ArrayList<LocationWiseBoothDetailsVO>(0);
					if(locationId.longValue() > 0L){
						list = districtDAO.getDistrictDetailsById(locationId);
					}
					else{
						list = districtDAO.getDistrictIdAndNameByStateForStateTypeId(1L, 0L);
					}
					if(list != null && list.size() > 0){
						for (Object[] obj : list) {
							LocationWiseBoothDetailsVO vo = new LocationWiseBoothDetailsVO();
							vo.setLocationId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
							vo.setLocationName(obj[1] != null ? obj[1].toString():"");
							reportList.add(vo);
						}
					}
							//reportList = getConstituencyByDistrictId(locationId);
					//}
					
					
					//if(reportList != null && reportList.size()>0)
					//	panchayatList.addAll(reportList);
				}
				
				//CadreCommitteeMemberVO membersVO = getAllCommitteeMembInfoInLocation(activityLevelId,constituencyIds,mandalList,panchayatList);
				//Map<Long,Map<Long,CadreCommitteeMemberVO>> mandalMap = new HashMap<Long,Map<Long,CadreCommitteeMemberVO>>();
				//Map<Long,Map<Long,CadreCommitteeMemberVO>> divisionMap = new HashMap<Long,Map<Long,CadreCommitteeMemberVO>>();
				//Map<Long,Map<Long,CadreCommitteeMemberVO>> townMap = new HashMap<Long,Map<Long,CadreCommitteeMemberVO>>();
				//Map<Long,Map<Long,CadreCommitteeMemberVO>> villageMap = new HashMap<Long,Map<Long,CadreCommitteeMemberVO>>();
				//Map<Long,Map<Long,CadreCommitteeMemberVO>> wardMap = new HashMap<Long,Map<Long,CadreCommitteeMemberVO>>();
				
				/*if(membersVO != null)
				{
					if(mandalList != null && mandalList.size()>0)
					{
						mandalMap = membersVO.getGenericMap1();
						divisionMap = membersVO.getGenericMap2();
						townMap = membersVO.getGenericMap3();
					}
					else if(panchayatList != null && panchayatList.size()>0)
					{
						villageMap = membersVO.getGenericMap1();
						wardMap = membersVO.getGenericMap1();
					}
				}*/
				 List<Long> locatnValList = null;
					List<Object[]> locatnValues = activityInfoDocumentDAO.getLocationValue(activityScopeId);
					if(locatnValues != null && locatnValues.size() >0){
						locatnValList = new ArrayList<Long>() ;
						for(Object[] obj : locatnValues){
							if(!locatnValList.contains(obj[2]))
									locatnValList.add((Long)obj[2]);
					}
				}
				String[] levelIdsArr = {"1","2"};
				if(reportList != null && reportList.size()>0)
				{
					returnVO = new LocationWiseBoothDetailsVO1();
					returnList = new ArrayList<LocationWiseBoothDetailsVO>(0);
					if(isChecked != null && isChecked.equalsIgnoreCase("notConducted"))
					{
						for (LocationWiseBoothDetailsVO vo : reportList) {
							String locationIdStr = vo.getLocationId().toString().substring(1);
							if(!updatedLocationIdsList.contains(Long.valueOf(locationIdStr))){
								
								LocationWiseBoothDetailsVO finalVO = new LocationWiseBoothDetailsVO();
								finalVO = vo;
								
								Long locationsId = vo.getLocationId();
								if(Arrays.asList(levelIdsArr).contains(activityLevelId.toString().trim()))
									locationsId = Long.valueOf(vo.getLocationId().toString().trim().substring(1));
								
								//To show Only Already Upload Images 
								if(locatnValList != null && locatnValList.contains(locationsId)){
									 vo.setIsAlreadyImageUpload("true");
								 }else{
									 vo.setIsAlreadyImageUpload("false"); 
								 }
								List<ActivityVO> activityVOList = activityMap.get(locationsId);
								
								if(activityVOList != null && activityVOList.size()>0)
								{
									for (ActivityVO activityVO : activityVOList) {
										finalVO.setPlanedDate(activityVO.getPlannedDate());
										finalVO.setConductedDate(activityVO.getConductedDate());
										if(activityVO.getStatus().equalsIgnoreCase("updated") && ( activityVO.getConductedDate() == null || activityVO.getConductedDate().length()<=0))
											returnList.add(finalVO);
									}
								}else
								{
									returnList.add(finalVO);
								}
							}
						}
					}
					else if(isChecked != null && isChecked.equalsIgnoreCase("conducted")){
						for (LocationWiseBoothDetailsVO vo : reportList) {
							String locationIdStr = vo.getLocationId().toString().substring(1);
							if(updatedLocationIdsList.contains(Long.valueOf(locationIdStr))){
								LocationWiseBoothDetailsVO finalVO = new LocationWiseBoothDetailsVO();
								finalVO = vo;
								
								Long locationsId = vo.getLocationId();
								if(Arrays.asList(levelIdsArr).contains(activityLevelId.toString().trim()))
									locationsId = Long.valueOf(vo.getLocationId().toString().trim().substring(1));
								
								if(locatnValList != null && locatnValList.contains(locationsId)){
									 vo.setIsAlreadyImageUpload("true");
								 }else{
									 vo.setIsAlreadyImageUpload("false"); 
								 }
								List<ActivityVO> activityVOList = activityMap.get(locationsId);
								
								if(activityVOList != null && activityVOList.size()>0)
								{
									for (ActivityVO activityVO : activityVOList) {
										finalVO.setPlanedDate(activityVO.getPlannedDate());
										finalVO.setConductedDate(activityVO.getConductedDate());
										
										if(activityVO.getStatus().equalsIgnoreCase("updated") && ( activityVO.getConductedDate() != null && activityVO.getConductedDate().length()>0))
											returnList.add(finalVO);
									}
								}else
								{
									returnList.add(finalVO);
								}
							}
						}
					}
					else if(isChecked != null && isChecked.equalsIgnoreCase("all")){
						
						for (LocationWiseBoothDetailsVO vo : reportList) {

							LocationWiseBoothDetailsVO finalVO = new LocationWiseBoothDetailsVO();
							finalVO = vo;
							Long locationsId = vo.getLocationId();
							if(Arrays.asList(levelIdsArr).contains(activityLevelId.toString().trim()))
								locationsId = Long.valueOf(vo.getLocationId().toString().trim().substring(1));
							
							if(locatnValList != null && locatnValList.contains(locationsId)){
								 vo.setIsAlreadyImageUpload("true");
							 }else{
								 vo.setIsAlreadyImageUpload("false"); 
							 }
							List<ActivityVO> activityVOList = activityMap.get(locationsId);
							if(activityVOList != null && activityVOList.size()>0)
							{
								for (ActivityVO activityVO : activityVOList) {
									finalVO.setPlanedDate(activityVO.getPlannedDate());
									finalVO.setConductedDate(activityVO.getConductedDate());
									
									returnList.add(finalVO);
								}
							}else
							{
								returnList.add(finalVO);
							}
						}
					}
					Collections.sort(returnList,new Comparator<LocationWiseBoothDetailsVO>() {
						public int compare(LocationWiseBoothDetailsVO o1,
								LocationWiseBoothDetailsVO o2) {
							return o1.getLocationName().compareTo(o2.getLocationName());
						}
					});
					
					
					if(questionResponsesMap!= null && questionResponsesMap.size()>0){
						
						List<Long> locationValuesList = questionResponsesMap.get(optionId);
						List<LocationWiseBoothDetailsVO> optionsVOList = new ArrayList<LocationWiseBoothDetailsVO>(0);
						if(returnList!= null && returnList.size()>0){
							for (LocationWiseBoothDetailsVO vo : returnList) {
								Long locationsId = vo.getLocationId();
								if(Arrays.asList(levelIdsArr).contains(activityLevelId.toString().trim()))
									locationsId = Long.valueOf(vo.getLocationId().toString().trim().substring(1));
								
								if(locationValuesList != null && locationValuesList.contains(locationsId)){
									optionsVOList.add(vo);
								}
							}
							returnList.clear();
							if(optionsVOList != null && optionsVOList.size()>0){
								returnList.addAll(optionsVOList);
							}
						}
					}
					returnVO.getResult().addAll(returnList);
				}
			}
			}
			
			if(returnVO == null){
				returnVO = new LocationWiseBoothDetailsVO1();
			}
			
			
				//getting Required attributes For every scope start		
				
				List<IdNameVO> idnameList = new ArrayList<IdNameVO>(0);
				List<Long> idsList = new ArrayList<Long>();
				List<Object[]> requiredObjList = activityScopeRequiredAttributesDAO.getRequiredAttributesOfScope(activityScopeId);			
				if(commonMethodsUtilService.isListOrSetValid(requiredObjList)){				
					String[] setterPropertiesList = {"id","name"};
					idnameList = (List<IdNameVO>) setterAndGetterUtilService.setValuesToVO(requiredObjList, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");
					for(Object[] obj:requiredObjList){
						idsList.add(obj[0] !=null ? (Long)obj[0]:0l);
					}
				}			
				if(commonMethodsUtilService.isListOrSetValid(idnameList)){
					returnVO.setIdNameVolist(idnameList);
				}

				
				//getting Required attributes For every scope end
				
				/*get Activity scope Dates and betweenDates Of those start*/
				
				/*	Date frmDate=null;
					Date toDate =null;
					Object[] scopeDates = activityScopeDAO.getRequiredDatesOfScope(activityScopeId);
				
					if(scopeDates !=null && scopeDates.length>0){
						frmDate = scopeDates[0] !=null ? (Date)scopeDates[0]:null;
						toDate =  scopeDates[1] !=null ? (Date)scopeDates[1]:null;
					}
					
					List<String> datesStr = new ArrayList<String>(0);
					if(frmDate !=null && toDate !=null){
						List<Date> dates = commonMethodsUtilService.getBetweenDates(frmDate, toDate);
						if(dates !=null && dates.size()>0){
							for (Date date : dates) {
								datesStr.add(sdf.format(date));
							}							
						}
						returnVO.setDatesList(datesStr);
					}
					*/
				/*get Activity scope Dates and betweenDates Of those End*/
			
			
		} catch (Exception e) {
			 LOG.error("Exception Occured in getActivityLocationDetails() method, Exception - ",e);
		}
		
		return returnVO;
	}
	
	public LocationWiseBoothDetailsVO1 getBetweenDatesOfActivityScope(Long activityScopeId){
		
		LocationWiseBoothDetailsVO1 finalVo = new LocationWiseBoothDetailsVO1();
		
		try{
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			
			Date frmDate=null;
			Date toDate =null;
			Long noOfTimes = activityScopeDAO.getNoOfTimesCountForActivityScope(activityScopeId);
			Object[] scopeDates = activityScopeDAO.getRequiredDatesOfScope(activityScopeId);
		
			if(scopeDates !=null && scopeDates.length>0){
				frmDate = scopeDates[0] !=null ? (Date)scopeDates[0]:null;
				toDate =  scopeDates[1] !=null ? (Date)scopeDates[1]:null;
			}
			
			List<String> datesStr = new ArrayList<String>(0);
			if(frmDate !=null && toDate !=null){
				List<Date> dates = commonMethodsUtilService.getBetweenDates(frmDate, toDate);
				if(noOfTimes != null && noOfTimes.longValue() != 1l && Long.valueOf(String.valueOf(dates.size())) == noOfTimes.longValue()){
					if(dates !=null && dates.size()>0){
						for (Date date : dates) {
							datesStr.add(sdf.format(date));
						}							
					}
				}
				else if(noOfTimes.longValue() != 1l && dates.size() != noOfTimes.longValue()){
					for (int i = 1; i <= noOfTimes; i++) {
						datesStr.add("day "+i);
					}
				}
				else
					datesStr.add("day");
				finalVo.setDatesList(datesStr);
			}
			
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getBetweenDatesOfActivityScope() method, Exception - ",e);
		}
		
		return finalVo;
	}
	
	
	public List<LocationWiseBoothDetailsVO> getLocationsListForList(List<Long> constituencyIds,String level){
		try{
			if(level.equalsIgnoreCase("mandal")){
				return getMandalMunicCorpDetailsByConstituencyList(constituencyIds);
			}else{
				return getPanchayatWardDivisionDetailsNew(constituencyIds.get(0));
			}
		}catch(Exception e){
			LOG.error("Exception raised in getLocationsList", e);
			return new ArrayList<LocationWiseBoothDetailsVO>(); 
		}
	}
	
	public CadreCommitteeMemberVO getAllCommitteeMembInfoInLocation(Long activityLevelId,List<Long> constituencyIds,List<LocationWiseBoothDetailsVO> mandalList,
			List<LocationWiseBoothDetailsVO> panchayatList){
		
		CadreCommitteeMemberVO finalVo = new CadreCommitteeMemberVO();
		
		try{
			/*Long activityLevelId = 2l;
			Long constituencyId=232l;*/
			
			Map<Long,Map<Long,CadreCommitteeMemberVO>> mandalMap = new HashMap<Long,Map<Long,CadreCommitteeMemberVO>>();
			Map<Long,Map<Long,CadreCommitteeMemberVO>> divisionMap = new HashMap<Long,Map<Long,CadreCommitteeMemberVO>>();
			Map<Long,Map<Long,CadreCommitteeMemberVO>> townMap = new HashMap<Long,Map<Long,CadreCommitteeMemberVO>>();
			Map<Long,Map<Long,CadreCommitteeMemberVO>> villageMap = new HashMap<Long,Map<Long,CadreCommitteeMemberVO>>();
			Map<Long,Map<Long,CadreCommitteeMemberVO>> wardMap = new HashMap<Long,Map<Long,CadreCommitteeMemberVO>>();
			
			
			//List<LocationWiseBoothDetailsVO> mandalList = null;
			//List<LocationWiseBoothDetailsVO> panchayatList=null;

			List<Long> mandalIds =new ArrayList<Long>(0);
			List<Long> localBodyIds =new ArrayList<Long>(0);
			List<Long> divisionIds =new ArrayList<Long>(0);
			List<Long> panchayties =new ArrayList<Long>(0);
			List<Long> wards =new ArrayList<Long>(0);
			
			/*if(activityLevelId ==2l){	
				mandalList = getLocationsListForList(constituencyIds,"Mandal");
			}else if(activityLevelId ==1l){
				panchayatList = getLocationsListForList(constituencyIds,"panchayat");
			}*/
			
			if(mandalList != null){
				for (LocationWiseBoothDetailsVO vo : mandalList) {
					Long mandalIdWithExtra = vo.getLocationId();
					if(mandalIdWithExtra !=null){
						
						if(mandalIdWithExtra.toString().substring(0,1).trim().equalsIgnoreCase("1")){
							
							if( Long.valueOf(mandalIdWithExtra.toString().substring(1)) == 20l ||  Long.valueOf(mandalIdWithExtra.toString().substring(1)) == 124l || Long.valueOf(mandalIdWithExtra.toString().substring(1)) == 119l ){
								divisionIds.add(Long.valueOf(mandalIdWithExtra.toString().substring(1)));
							}else{
								localBodyIds.add(Long.valueOf(mandalIdWithExtra.toString().substring(1)));
							}
							
			        	}else{
			        		mandalIds.add(Long.valueOf(mandalIdWithExtra.toString().substring(1)));
			        	}
					}
					
				}
			}
			if(panchayatList != null){
				for (LocationWiseBoothDetailsVO vo : panchayatList) {
					Long panchayatIdsWithExtra = vo.getLocationId();
					if(panchayatIdsWithExtra !=null){
						
						if(panchayatIdsWithExtra.toString().substring(0,1).trim().equalsIgnoreCase("1")){
							panchayties.add(Long.valueOf(panchayatIdsWithExtra.toString().substring(1)));
			        	}else{
			        		wards.add(Long.valueOf(panchayatIdsWithExtra.toString().substring(1)));
			        	}
					}
					
				}
			}
			
			List<Object[]> result =null;
			List<Object[]> result1 =null;
			List<Object[]> result2 =null;

		if(activityLevelId ==2l){	
			if(mandalIds !=null && mandalIds.size()>0){
				//0 role,,1name,2membership,3tdpCommitteeMemberId,4cadreId,5tdpCommitteeRoleId,6.committeeName,7.committeeLevelValue,8.mobileNo
				result =  tdpCommitteeMemberDAO.getAllCommitteeMembInfoInLocation(5l,mandalIds);//president and general secratary
				if(result !=null && result.size()>0){
					mandalMap = setForMapByLocation(result,mandalMap,"Mandal");
				}
				
			}
			if(localBodyIds !=null && localBodyIds.size()>0){
				result1 = tdpCommitteeMemberDAO.getAllCommitteeMembInfoInLocation(7l,localBodyIds);
				if(result1 !=null && result1.size()>0){
					townMap = setForMapByLocation(result1,townMap,"Town");
				}
				
			}
			if(divisionIds !=null && divisionIds.size()>0){
				result2 =tdpCommitteeMemberDAO.getAllCommitteeMembInfoInLocation(9l,divisionIds);
				if(result2 !=null && result2.size()>0){
					divisionMap = setForMapByLocation(result2,divisionMap,"Division");
				}
				
			}
			
			
			finalVo.setGenericMap1(mandalMap);
			finalVo.setGenericMap2(townMap);
			finalVo.setGenericMap3(divisionMap);
			
		}else if(activityLevelId == 1l){
			if(panchayties !=null && panchayties.size()>0){
				result =  tdpCommitteeMemberDAO.getAllCommitteeMembInfoInLocation(6l,panchayties);//president and general secratary
				if(result !=null && result.size()>0){
					villageMap = setForMapByLocation(result,villageMap,"village");
				}
				
			}
			if(wards !=null && wards.size()>0){
				result1 =  tdpCommitteeMemberDAO.getAllCommitteeMembInfoInLocation(8l,panchayties);//president and general secratary
				if(result1 !=null && result1.size()>0){
					wardMap = setForMapByLocation(result1,wardMap,"ward");
				}
				
			}
			
			finalVo.setGenericMap1(villageMap);
			finalVo.setGenericMap2(wardMap);
		}
		
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return finalVo;
	}
	public Map<Long,Map<Long,CadreCommitteeMemberVO>> setForMapByLocation(List<Object[]> result,Map<Long,Map<Long,CadreCommitteeMemberVO>> allocatedMap,String type){
		try{
			if(result !=null && result.size()>0){
				
				
				for (Object[] objects : result) {
					
					Map<Long,CadreCommitteeMemberVO> roleMap = new HashMap<Long, CadreCommitteeMemberVO>(0);
					
					CadreCommitteeMemberVO mainVo = null;
					if(allocatedMap !=null && allocatedMap.size()>0){
						roleMap = allocatedMap.get(objects[7] !=null ? (Long)objects[7]:0l );
					}else{
						allocatedMap = new HashMap<Long, Map<Long,CadreCommitteeMemberVO>>();
					}
					
					if(roleMap !=null && roleMap.size()>0){
						mainVo = roleMap.get(objects[5] !=null ? (Long)objects[5]:0l);
					}else{
						roleMap = new HashMap<Long, CadreCommitteeMemberVO>();
					}
					
					if(mainVo ==null){
						mainVo = new CadreCommitteeMemberVO();
					}
					
					mainVo.setRole(objects[0] !=null ? objects[0].toString():"") ;//role
					mainVo.setName(objects[1] !=null ? objects[1].toString():"");//memberName
					mainVo.setMembershipNo(objects[2] !=null ? objects[2].toString():"");
					mainVo.setCadreId(objects[3] !=null ? (Long)objects[3]:0l);
					mainVo.setCommiteeName(objects[6] !=null ? objects[6].toString():"");
					mainVo.setLevelValue(objects[7] !=null ? (Long)objects[7]:0l);
					mainVo.setMobileNo(objects[8] !=null ? objects[8].toString():"");
					mainVo.setRoleId(objects[5] !=null ? (Long)objects[5]:0l);
					
					/*if(type.equalsIgnoreCase("Town") || type.equalsIgnoreCase("ward") || type.equalsIgnoreCase("Division")){
						mainVo.setLevelValue(Long.parseLong("1"+mainVo.getLevelValue().toString()));
					}else if(type.equalsIgnoreCase("mandal") ||  type.equalsIgnoreCase("village")){
						mainVo.setLevelValue(Long.parseLong("2"+mainVo.getLevelValue().toString()));
					}*/
					
					roleMap.put(mainVo.getRoleId(), mainVo);
					allocatedMap.put(mainVo.getLevelValue(), roleMap);
					
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return allocatedMap;
	}
	
	public List<CadreCommitteeMemberVO> getComitteeMembersInfoInActivity(Long locationId,Long locationType,Long basicCommitteeTypeId,Long constituencyId)
	{
		List<CadreCommitteeMemberVO> cadreCommitteeMemberVOList= new ArrayList<CadreCommitteeMemberVO>();
		try
		{
			if(constituencyId != null && constituencyId.longValue() > 0){
				Long localElectionBodyId = boothDAO.getLocalElectionBodyByConstituency(constituencyId);
				if(localElectionBodyId != null){
					if(localElectionBodyId.longValue() == 20L || localElectionBodyId.longValue() == 124L || localElectionBodyId.longValue() == 119L){
						locationType = 9l;
					}
				}
			}
			//19tehsilId, 20localElectionBodyId 21constituencyId
		    List<Object[]> tdpCadresList=tdpCommitteeMemberDAO.getComitteeMembersInfoInActivity(locationType,locationId,basicCommitteeTypeId);
		    if(tdpCadresList!=null && tdpCadresList.size()>0){
		    	
		    	for (Object[] objects : tdpCadresList){
		    		CadreCommitteeMemberVO cadreCommitteeMemberVO=new CadreCommitteeMemberVO();
		    		
		    		cadreCommitteeMemberVO.setLevel(objects[0] != null ? Long.valueOf(objects[0].toString().trim()):0L);//roleId
		    		cadreCommitteeMemberVO.setRole(objects[1] != null ?objects[1].toString():"");//role
		    		cadreCommitteeMemberVO.setId(objects[2] != null ?Long.valueOf(objects[2].toString()):0L);//cadreId
		    		cadreCommitteeMemberVO.setName(objects[3] != null ?objects[3].toString():"");//cadreName
		    		cadreCommitteeMemberVO.setImagePath(objects[4] != null ?objects[4].toString():"");//image
		    		cadreCommitteeMemberVO.setCasteName(objects[5] != null ? objects[5].toString().trim():"");
		    		cadreCommitteeMemberVO.setGender(objects[6] != null ? objects[6].toString().trim():"");
		    		cadreCommitteeMemberVO.setAge(objects[7] != null ? objects[7].toString().trim():"");
		    		cadreCommitteeMemberVO.setCasteGroupName(objects[8] != null ? objects[8].toString().trim():"");
		    		cadreCommitteeMemberVO.setMobileNo(objects[9] != null ? objects[9].toString().trim():"");
		    		cadreCommitteeMemberVO.setCommiteeName(objects[10] != null ? objects[10].toString().trim():"");
		    		cadreCommitteeMemberVO.setConstituencyName(objects[11] != null ? objects[11].toString(): "");
		    		
		    		cadreCommitteeMemberVOList.add(cadreCommitteeMemberVO);
				}
		    }	
		}
		catch(Exception e){
			
			LOG.error("Exception raised in getComitteeMembersInfoByCommiteTypeAndLocation", e);
		}
		return cadreCommitteeMemberVOList;
	}

	public List<ActivityVO> asemblyConstWiseActivities(String startDateString,String endDateString,Long activityScopeId,Long activityLevelId,String accessType,Long accessValue,Long stateId,Long userId){
		List<ActivityVO> finalList=null;
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		try{
			      //stat data
			
			 //get Constituencies.
			 List<Long> constIds=new ArrayList<Long>(0);
			 
			 List<Long>  committeeEnrollmentIds = new ArrayList<Long>();
				committeeEnrollmentIds.add(IConstants.CURRENT_ENROLLMENT_ID);
			 BasicVO basicVO=getAccessLocationValuesByState(accessType,accessValue,stateId,userId,committeeEnrollmentIds);
			 if(basicVO!=null && basicVO.getHamletVoterInfo()!=null && basicVO.getHamletVoterInfo().size()>0){
				 
				 List<BasicVO> constList=basicVO.getHamletVoterInfo();
				 finalList=new ArrayList<ActivityVO>(0);
				 for(BasicVO vo:constList){
					 ActivityVO conVO=new ActivityVO();
					 conVO.setId(vo.getId());
					 conVO.setName(vo.getName());
					 conVO.setTotalCount(0l);
					 conVO.setPlannedCount(0l);
					 conVO.setConductedCount(0l);
					 conVO.setNonConductedCount(0l);
					 finalList.add(conVO);
					 constIds.add(conVO.getId());
				 }
				 
				//Dates
				 Date startDate=null;
				 Date endDate=null;
				 if(startDateString!=null && startDateString.trim().length()>0){
					 startDate=sdf.parse(startDateString);
				 }
				 if(endDateString!=null && startDateString.trim().length()>0){
					 endDate=sdf.parse(endDateString);
				 }
				 
				 //planned data
				 List<Object[]> plannedlist=activityLocationInfoDAO.getAssemblyConstWiseDetails(startDate,endDate,activityScopeId,constIds);
				 if(plannedlist!=null && plannedlist.size()>0){
					 for(Object[] obj:plannedlist){
						 Long constId=(Long)obj[0];
						 ActivityVO convo=getMatchedConstVO(constId,finalList);
						 if(convo!=null){
							 //convo.setPlannedCount(obj[1]!=null?(Long)obj[1]:0l);
							 convo.setConductedCount(obj[2]!=null?(Long)obj[2]:0l);
							 //convo.setNonConductedCount(convo.getPlannedCount()-convo.getConductedCount());
						 }
					 }
				 }
				 
				List<Object[]> plannedCounts = activityLocationInfoDAO.getPlannedCountForAssemblyConstWise(startDate, endDate, activityScopeId, constIds);
				 if(plannedCounts!=null && plannedCounts.size()>0){
					 for(Object[] obj:plannedCounts){
						 Long constId=(Long)obj[0];
						 ActivityVO convo=getMatchedConstVO(constId,finalList);
						 if(convo!=null){
							 convo.setPlannedCount(obj[1]!=null?(Long)obj[1]:0l);
							 //convo.setConductedCount(obj[2]!=null?(Long)obj[2]:0l);
							 //convo.setNonConductedCount(convo.getPlannedCount()-convo.getConductedCount());
						 }
					 }
				 }
				 
				 List<Object[]> notConductedlist=activityLocationInfoDAO.getNotConductedCountForAssemblyConstWise(startDate, endDate, activityScopeId, constIds);
				 if(notConductedlist!=null && notConductedlist.size()>0){
					 for(Object[] obj:notConductedlist){
						 Long constId=(Long)obj[0];
						 ActivityVO convo=getMatchedConstVO(constId,finalList);
						 if(convo!=null){
							 //convo.setPlannedCount(obj[1]!=null?(Long)obj[1]:0l);
							 //convo.setConductedCount(obj[2]!=null?(Long)obj[2]:0l);
							 convo.setNonConductedCount(obj[1]!=null?(Long)obj[1]:0l);
						 }
					 }
				 }
				 
				 List<Object[]> notPlannedlist=activityLocationInfoDAO.getNotPlannedCountForAssemblyConstWise(activityScopeId,constIds);
				 if(notPlannedlist!=null && notPlannedlist.size()>0){
					 for(Object[] obj:notPlannedlist){
						 Long constId=(Long)obj[0];
						 ActivityVO convo=getMatchedConstVO(constId,finalList);
						 if(convo!=null){
							 //convo.setPlannedCount(obj[1]!=null?(Long)obj[1]:0l);
							 //convo.setConductedCount(obj[2]!=null?(Long)obj[2]:0l);
							 //convo.setNonConductedCount(convo.getPlannedCount()-convo.getConductedCount());
							 convo.setNotPlannedCount((Long) (obj[1] != null ? obj[1]:0l));
						 }
					 }
				 }
				 
				 //total counts.
				 Long activityTimes=activityScopeDAO.getNoOFTimesOfAnActivity(activityScopeId);
				 if(activityTimes ==null){
					 activityTimes=1l;
				 }
				 
				 List<Long> levelIds=new ArrayList<Long>();
				 if(activityLevelId==1l){//village/ward
					 levelIds.add(6l);
					 levelIds.add(8l);
				 }else if(activityLevelId==2l){//mandal/town/division
					 levelIds.add(5l);
					 levelIds.add(7l);
				 }else if(activityLevelId==3l){//District
					 levelIds.add(3l);
				 }else if(activityLevelId==4l){//State
					 levelIds.add(2l);
				 }else if(activityLevelId==5l){//Constituency
					 levelIds.add(4l);
				 }
				 
				 List<Object[]> totalCounts=locationInfoDAO.getLocationWiseTotalCounts(levelIds,constIds,4L);
				 if(totalCounts!=null && totalCounts.size()>0){
					 for(Object[] obj:totalCounts){
						 Long constId=(Long)obj[0];
						 ActivityVO convo=getMatchedConstVO(constId,finalList);
						 if(convo!=null){
							 Long count=obj[1]!=null?(Long)obj[1]:0l ;
							 convo.setTotalCount(count*activityTimes);
						 }
					 }
				 }
			 }
		}catch(Exception e){
			LOG.error("Exception raised in asemblyConstWiseActivities", e);
		}
		return finalList;
	}
	public ActivityVO getMatchedConstVO(Long id,List<ActivityVO> list){
		if(id!=null && list!=null && list.size()>0){
			for(ActivityVO vo:list){
				if(vo.getId().equals(id)){
					return vo;
				}
			}
		}
		return null;
	}
	
	public List<EventDocumentVO> getEventDocumentsForLocation(EventDocumentVO inputVo,Long activityMemberId,Long stateId,Long userTypeId)
	{
		List<EventDocumentVO> returnList = null;
		List<Long> days =new ArrayList<Long>();
		Date startDate = null;
		Date toDate = null;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try{
			
			Long locationAccessLevelId = 0l;
			Set<Long> locationValues = new HashSet<Long>(0);
			//Long stateId = 1L; 
			 List<Object[]> userAccLvlANdVals=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(activityMemberId);
			 if(userAccLvlANdVals != null && userAccLvlANdVals.size() > 0){
				 locationAccessLevelId=(Long) userAccLvlANdVals.get(0)[0];
				 for(Object[] param:userAccLvlANdVals){
					 locationValues.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				 }
		    }
			ActivityScope activityScope = activityScopeDAO.get(inputVo.getActivityId());
			if(activityScope != null && activityScope.getIsDeleted().equalsIgnoreCase("N")){
				List<String> weeksList = commonMethodsUtilService.getBetweenWeeks(activityScope.getStartDate(),activityScope.getEndDate(),"dd-MM-yyyy");
				
				if(inputVo.getStrDate() != null && !inputVo.getStrDate().trim().isEmpty())
				{
					startDate = format.parse(inputVo.getStrDate().toString().trim());
					toDate = format.parse(inputVo.getEndDate().toString().trim());
				}
				
				
				
				List<Object[]> list = null;
				Long totalCount = 0L;
				List<Object[]> counstList = null;
				if(inputVo.getCallFrom().equalsIgnoreCase("BD")){
					list = activityInfoDocumentDAO.getEventDocumentsByLocationInfo(inputVo,startDate,toDate,locationAccessLevelId,locationValues);
					//totalCount = activityInfoDocumentDAO.getEventDocumentsCountByLocationInbfo(inputVo,startDate,toDate,locationAccessLevelId,locationValues);
					counstList = activityInfoDocumentDAO.getEventsDocumentsCountByLocationInbfo(inputVo,startDate,toDate,locationAccessLevelId,locationValues);
				}else{
					list = activityInfoDocumentDAO.getEventDocuments(inputVo,startDate,toDate,locationAccessLevelId,locationValues);
					//totalCount = activityInfoDocumentDAO.getEventDocumentsCount(inputVo,startDate,toDate,locationAccessLevelId,locationValues);
					counstList = activityInfoDocumentDAO.getEventsDocumentsCountByLocationInbfo(inputVo,startDate,toDate,locationAccessLevelId,locationValues);
				}
				
				Map<String,EventDocumentVO> documentsMap = new TreeMap<String, EventDocumentVO>();
				Set<Date> avaiableDatesSet = new HashSet<Date>(0);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if(commonMethodsUtilService.isListOrSetValid(counstList)){
					for (Object[] params : counstList) {
						Long day = commonMethodsUtilService.getLongValueForObject(params[1]);
						String date =commonMethodsUtilService.getStringValueForObject(params[0]);
						Long documentsCount =commonMethodsUtilService.getLongValueForObject(params[2]);
						Long locationsCount =commonMethodsUtilService.getLongValueForObject(params[3]);
						Long conductedCount =commonMethodsUtilService.getLongValueForObject(params[4]);
						avaiableDatesSet.add(sdf.parse(date));
						EventDocumentVO dayWiseVO = new EventDocumentVO();
						dayWiseVO.setDay(day);
						dayWiseVO.setStrDate(date);
						dayWiseVO.setTotalResult(documentsCount);
						dayWiseVO.setCoveredCount(locationsCount);
						if(conductedCount != null && conductedCount.longValue()>0L)
							dayWiseVO.setCoveredCount(conductedCount);
						
						documentsMap.put(day.toString(), dayWiseVO);
						
						totalCount = totalCount+documentsCount;
					}
					
				}
				if(list != null && list.size() > 0)
				{
					returnList = new ArrayList<EventDocumentVO>();
					for(Object[] params : list)
					{
						try {
							EventDocumentVO vo = null;
							if(!days.contains((Long)params[2]))
							{
							 vo = new EventDocumentVO();
							 vo.setDay(commonMethodsUtilService.getLongValueForObject(params[2]));
							 vo.setId(commonMethodsUtilService.getStringValueForObject(params[3]));
							 returnList.add(vo);
							 days.add(commonMethodsUtilService.getLongValueForObject(params[2]));
							 
							 EventDocumentVO dtlsVO = documentsMap.get(vo.getDay().toString());
							 if(dtlsVO != null){
								 vo.setStrDate(dtlsVO.getStrDate());
								 vo.setTotalResult(dtlsVO.getTotalResult());
								 vo.setCoveredCount(dtlsVO.getCoveredCount());
							 } 
							}
							vo=getMatchedVO(returnList,(Long)params[2]);
							if(vo != null)
							{
								
								EventDocumentVO subVo = new EventDocumentVO();
								subVo.setName(commonMethodsUtilService.getStringValueForObject(params[0]));
								subVo.setPath(commonMethodsUtilService.getStringValueForObject(params[1]));
								subVo.setId(commonMethodsUtilService.getStringValueForObject(params[3]));
								
								AddressVO addressVO = new AddressVO();
								addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(params[5]));
								addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(params[4]));
								
								addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(params[7]));
								addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(params[6]));
								
								addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(params[9]));
								addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(params[8]));
								
								addressVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(params[11]));
								addressVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(params[10]));
								
								addressVO.setTownshipName(commonMethodsUtilService.getStringValueForObject(params[13])+" "+commonMethodsUtilService.getStringValueForObject(params[14]) );
								addressVO.setTownshipId(commonMethodsUtilService.getStringValueForObject(params[12]));
								
								addressVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(params[15]));
								addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(params[16]));
								addressVO.setPanchayatName(commonMethodsUtilService.getStringValueForObject(params[20]));
								addressVO.setWardName(commonMethodsUtilService.getStringValueForObject(params[18]));
								
								subVo.setAddress(addressVO);
								vo.getSubList().add(subVo);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(totalCount != null && commonMethodsUtilService.isListOrSetValid(returnList)){
						EventDocumentVO vo = returnList.get(0);
						if(vo != null){
							vo.setTotalResult(totalCount);
							if(commonMethodsUtilService.isListOrSetValid(weeksList)){
								for (int j = 0 ;j<weeksList.size();j++) {
									try {
										EventDocumentVO weekVO = new EventDocumentVO();
										Calendar cal = Calendar.getInstance();
										Date weekStartdate= format.parse(weeksList.get(j).split("to")[0].toString().trim());
										Date weekEnddate= format.parse(weeksList.get(j).split("to")[1].toString().trim());
										cal.setTime(weekStartdate);
										weekVO.setName(IConstants.MONTH_NAMES[cal.get(Calendar.MONTH)]);
										weekVO.setStrDate(weeksList.get(j));
										boolean isAvailableData=false;
										if(commonMethodsUtilService.isListOrSetValid(avaiableDatesSet)){
											for (Date availableDate : avaiableDatesSet) {
												if(availableDate.equals(weekStartdate) || (availableDate.after(weekStartdate) && availableDate.before(weekEnddate))
									    				 || availableDate.equals(weekEnddate)){
														isAvailableData=true;
														break;
												}else if(weekStartdate.equals(weekEnddate)){
													isAvailableData=true;
													break;
												}
											}
										}
										else{
											vo.getSubList2().add(weekVO);
										}
										if(isAvailableData)
											vo.getSubList2().add(weekVO);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
						}
					}
				}
			}
		}
		catch(Exception e){
			LOG.error("Exception raised in getEventDocumentsForLocation", e);
		}
		return returnList;
	}
	
	public AddressVO getLocationNamesByUserAddressObj(UserAddress userAddress){
		AddressVO addressVO = null;
		try {
			if(userAddress != null){
				addressVO = new AddressVO();
				if(userAddress.getState() != null){
					State state = userAddress.getState();
					addressVO.setStateName(state.getStateName());
					addressVO.setStateId(state.getStateId());
				}
				if(userAddress.getDistrict() != null){
					District district = userAddress.getDistrict();
					addressVO.setDistrictName(district.getDistrictName());
					addressVO.setDistrictId(district.getDistrictId());
				}
				if(userAddress.getParliamentConstituency() != null){
					Constituency pConstituency = userAddress.getParliamentConstituency();
					addressVO.setParliamentName(pConstituency.getName());
					addressVO.setParliamentId(pConstituency.getConstituencyId());
				}
				if(userAddress.getConstituency() != null){
					Constituency assembly = userAddress.getConstituency();
					addressVO.setConstituencyName(assembly.getName());
					addressVO.setConstituencyId(assembly.getConstituencyId());
				}
				if(userAddress.getTehsil() != null){
					Tehsil tehil =userAddress.getTehsil();
					addressVO.setTehsilName(tehil.getTehsilName()+" Mandal" );
					addressVO.setTehsilId(tehil.getTehsilId() );
				}
				if(userAddress.getLocalElectionBody() != null){
					LocalElectionBody leb = userAddress.getLocalElectionBody();
					addressVO.setTehsilName(leb.getName()+" "+leb.getElectionType().getElectionType());
					addressVO.setTehsilId(leb.getLocalElectionBodyId());
				}
				if(userAddress.getPanchayat() != null){
					addressVO.setPanchayatName(userAddress.getPanchayat().getPanchayatName());
				}
				if(userAddress.getWard() != null){
					addressVO.setWardName(userAddress.getWard().getName());
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getLocationNamesByUserAddressObj() method in CadreCommitteeService service class. ", e);
		}
		return addressVO;
	}
	public EventDocumentVO getMatchedVO(List<EventDocumentVO> returnList,Long id)
	{
		if(returnList == null || returnList.size() == 0)
			return null;
		for(EventDocumentVO vo : returnList)
			if(vo.getDay()==null || vo.getDay()==0l){
				vo.setDay(1l);
				if(id==null)id=1l;
				if(vo.getDay().longValue() == id.longValue())
					return vo;
			}else if(vo.getDay()==1l){
				if(id==null)id=1l;
				if(vo.getDay().longValue() == id.longValue())
					return vo;
			}else if(vo.getDay().longValue()==id.longValue()){
					return vo;
			}
			
		return null;
	}
	
	public List<BasicVO> getDistrictsByUserId(Long userId,String isAdmin,String accessType,Long accessValue){
		
		List<BasicVO> basicVOList = new ArrayList<BasicVO>();
		
		try {
			
			if(isAdmin.equalsIgnoreCase("true")){
				List<Object[]> districtsList = districtDAO.getDistrictsForState(1l);
				
				if(districtsList != null && districtsList.size() > 0){
					for (Object[] objects : districtsList) {
						BasicVO vo = new BasicVO();
						
						vo.setId((Long) (objects[0] != null ? objects[0]:0l));
						vo.setName(objects[1] != null ? objects[1].toString():"");
						
						basicVOList.add(vo);
					}
				}
			}
			else{
				List<Object[]> accessDistrictsList = userDistrictAccessInfoDAO.findByUser(userId);
				
				if(accessDistrictsList != null && accessDistrictsList.size() > 0){
					for (Object[] objects : accessDistrictsList) {
						BasicVO vo = new BasicVO();
						
						vo.setId((Long) (objects[0] != null ? objects[0]:0l));
						vo.setName(objects[1] != null ? objects[1].toString():"");
						
						basicVOList.add(vo);
					}
				}else{
					District district = districtDAO.get(accessValue);
					
					if(district != null){
						BasicVO vo = new BasicVO();
						
						vo.setId(district.getDistrictId());
						vo.setName(district.getDistrictName());
						
						basicVOList.add(vo);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return basicVOList;
	}
	
	public BasicVO getLocationsHierarchyForEvent(EventDocumentVO inputVo,String type)
	{
		Map<String,Long> dayWiseMap = new LinkedHashMap<String, Long>();
		BasicVO vo = new BasicVO();
		Date startDate = null;
		Date toDate = null;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try{
			if(inputVo.getStrDate() != null && !inputVo.getStrDate().trim().isEmpty())
			{
				startDate = format.parse(inputVo.getStrDate().trim().toString());
				toDate = format.parse(inputVo.getEndDate().trim().toString());
			}
		List<Object[]> list  = null;
		List<BasicVO> locationsList = new ArrayList<BasicVO>();
		
		if(inputVo.getLocationScope().equalsIgnoreCase("constituency"))
		{
			if(type.equalsIgnoreCase("dayWise")){
				list = activityInfoDocumentDAO.getDayWiseImagesCount(inputVo, startDate, toDate);
				if(commonMethodsUtilService.isListOrSetValid(list)){
					for (Object[] obj : list) {
						String dateStr = obj[0] != null ? obj[0].toString():"";
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						dayWiseMap.put(dateStr, count);
					}
				}
				vo.setDayWiseMap(dayWiseMap);
			}
			else
			{
				list = activityInfoDocumentDAO.getLocationWiseImageCount(inputVo,startDate,toDate,IConstants.MANDAL);
				List<BasicVO> mandalList = setLocationsListForMandal(list,IConstants.MANDAL);
				list = activityInfoDocumentDAO.getLocationWiseImageCount(inputVo,startDate,toDate,IConstants.LOCAL_ELECTION_BODY);
				List<BasicVO> localbodyList = setLocationsListForMandal(list,"Muncipality");
				if(mandalList != null && mandalList.size() > 0)
				locationsList.addAll(mandalList);
				if(localbodyList != null && localbodyList.size() > 0)
				locationsList.addAll(localbodyList);
				vo.setLocationsList(locationsList);
			}
			
		}
		else if(inputVo.getLocationScope().equalsIgnoreCase("mandal"))
		{
			
			if(type.equalsIgnoreCase("dayWise")){
				list = activityInfoDocumentDAO.getDayWiseImagesCount(inputVo, startDate, toDate);
				if(commonMethodsUtilService.isListOrSetValid(list)){
					for (Object[] obj : list) {
						String dateStr = obj[0] != null ? obj[0].toString():"";
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						dayWiseMap.put(dateStr, count);
					}
				}
				vo.setDayWiseMap(dayWiseMap);
			}
			else
			{
				list = activityInfoDocumentDAO.getLocationWiseImageCount(inputVo,startDate,toDate,null);
				if(inputVo.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("2"))
				{
					List<BasicVO> mandalList = setLocationsListForMandal(list,IConstants.PANCHAYAT);
					if(mandalList != null && mandalList.size() > 0)
					locationsList.addAll(mandalList);
				}
				if(inputVo.getLocationValue().toString().substring(0, 1).equalsIgnoreCase("1"))
				{
					List<BasicVO> localbodyList =  setLocationsListForMandal(list,IConstants.WARD);
					if(localbodyList != null && localbodyList.size() > 0)
					locationsList.addAll(localbodyList);
				}
				vo.setLocationsList(locationsList);
			}
		}
		else
		{
			if(type.equalsIgnoreCase("dayWise")){
				list = activityInfoDocumentDAO.getDayWiseImagesCount(inputVo, startDate, toDate);
				if(commonMethodsUtilService.isListOrSetValid(list)){
					for (Object[] obj : list) {
						String dateStr = obj[0] != null ? obj[0].toString():"";
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						dayWiseMap.put(dateStr, count);
					}
				}
				vo.setDayWiseMap(dayWiseMap);
			}
			else
			{
				list = activityInfoDocumentDAO.getLocationWiseImageCount(inputVo,startDate,toDate,null);
				locationsList = setLocationsList(list);
				vo.setLocationsList(locationsList);
			}
			
		}
		
		
		}
		catch(Exception e){
			LOG.error("Exception raised in getLocationsHierarchyForEvent", e);
		}
		return vo;
	}
	
	public List<BasicVO> getAvailableDates(EventDocumentVO inputVo,Long activityMemberId,Long stateId,Long userTypeId)
	{
		List<BasicVO> returnList = new ArrayList<BasicVO>();
		
		Date startDate = null;
		Date toDate = null;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try{
			if(inputVo.getStrDate() != null && !inputVo.getStrDate().trim().isEmpty())
			{
				startDate = format.parse(inputVo.getStrDate().trim().toString());
				toDate = format.parse(inputVo.getEndDate().trim().toString());
			}
			
			List<Object[]> counstList = activityInfoDocumentDAO.getEventsDocumentsCountByLocationInbfo(inputVo,startDate,toDate,null,null);
		Map<String , EventDocumentVO> datesMap = new LinkedHashMap<String, EventDocumentVO>();
		Map<String,EventDocumentVO> documentsMap = new TreeMap<String, EventDocumentVO>();
		if(commonMethodsUtilService.isListOrSetValid(counstList)){
			for (Object[] params : counstList) {
				Long day = commonMethodsUtilService.getLongValueForObject(params[1]);
				String date =commonMethodsUtilService.getStringValueForObject(params[0]);
				Long documentsCount =commonMethodsUtilService.getLongValueForObject(params[2]);
				Long locationsCount =commonMethodsUtilService.getLongValueForObject(params[3]);
				Long conductedCount =commonMethodsUtilService.getLongValueForObject(params[4]);
				
			
				EventDocumentVO dayWiseVO1 = documentsMap.get(day.toString());
				 if(dayWiseVO1 != null){
					 	dayWiseVO1.setDay(day);
						dayWiseVO1.setStrDate(date);
						dayWiseVO1.setTotalResult(documentsCount+dayWiseVO1.getTotalResult());
						dayWiseVO1.setCoveredCount(locationsCount+dayWiseVO1.getCoveredCount());
						if(conductedCount != null && conductedCount.longValue()>0L)
							dayWiseVO1.setCoveredCount(conductedCount+dayWiseVO1.getCoveredCount());
				 }else{
					 	dayWiseVO1 = new EventDocumentVO();
					 	dayWiseVO1.setDay(day);
						dayWiseVO1.setStrDate(date);
						dayWiseVO1.setTotalResult(documentsCount);
						dayWiseVO1.setCoveredCount(locationsCount);
						if(conductedCount != null && conductedCount.longValue()>0L)
							dayWiseVO1.setCoveredCount(conductedCount);
				 }
				
				documentsMap.put(day.toString(), dayWiseVO1);
				
				EventDocumentVO dayWiseVO = datesMap.get(date);
				 if(dayWiseVO != null){
					 	dayWiseVO.setDay(day);
						dayWiseVO.setStrDate(date);
						dayWiseVO.setTotalResult(documentsCount+dayWiseVO.getTotalResult());
						dayWiseVO.setCoveredCount(locationsCount+dayWiseVO.getCoveredCount());
						if(conductedCount != null && conductedCount.longValue()>0L)
							dayWiseVO.setCoveredCount(conductedCount+dayWiseVO.getCoveredCount());
				 }else{
					 	dayWiseVO = new EventDocumentVO();
					 	dayWiseVO.setDay(day);
						dayWiseVO.setStrDate(date);
						dayWiseVO.setTotalResult(documentsCount);
						dayWiseVO.setCoveredCount(locationsCount);
						if(conductedCount != null && conductedCount.longValue()>0L)
							dayWiseVO.setCoveredCount(conductedCount);
				 }
				
				 datesMap.put(date.toString(), dayWiseVO);
			}
		}
		
		 List<Object[]> list  = null;
			
				list = activityInfoDocumentDAO.getAvailableDates(inputVo,startDate,toDate,null);
			
			if(list != null && list.size() > 0)
			{
				int i=0;
				for(Object[] params : list)
				{
					BasicVO vo = new BasicVO();
					vo.setId((Long)params[0]);
					vo.setName(params[1].toString());
					
					 EventDocumentVO dtlsVO = documentsMap.get(vo.getId().toString());
					 if(dtlsVO != null){
						 vo.setDate(dtlsVO.getStrDate());
						 vo.setTotalResult(dtlsVO.getTotalResult());
						 vo.setCoveredCount(dtlsVO.getCoveredCount());
					 } 
					 
					
					if(i==0 && commonMethodsUtilService.isMapValid(datesMap)){
						vo.getDocumentsVOList().addAll(datesMap.values());
					}
					returnList.add(vo);
					i=i+1;
				}
				Collections.sort(returnList,sortDays);
		}
		}
		catch (Exception e) {
			LOG.error("Exception raised in getAvailableDates", e);
		}
		return returnList;
	}
	 public static Comparator<BasicVO> sortDays = new Comparator<BasicVO>()
				{	  
						  public int compare(BasicVO arg1,BasicVO arg2)
							{  
							  	return (arg1.getId().intValue()) - (arg2.getId().intValue());
							}
				};
	
	public List<BasicVO> setLocationsList(List<Object[]> list)
	{
		List<BasicVO> returnList = new ArrayList<BasicVO>();
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				BasicVO vo = new BasicVO();
				vo.setId((Long)params[0]);
				vo.setName(params[1].toString());
				vo.setCount(params[2] != null ? (Long)params[2] : 0);
				returnList.add(vo);
			}
		}
		return returnList;
	}
	
	public List<BasicVO> setLocationsListForMandal(List<Object[]> list,String type)
	{
		List<BasicVO> returnList = new ArrayList<BasicVO>();
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				BasicVO vo = new BasicVO();
				if(type.equalsIgnoreCase(IConstants.MANDAL) || type.equalsIgnoreCase(IConstants.WARD))
				{
				vo.setId(new Long("2"+params[0].toString()));
				vo.setName(params[1].toString() + " " +type);
				}
				if(type.equalsIgnoreCase("Muncipality"))
				{
					vo.setId(new Long("1"+params[0].toString()));
					vo.setName(params[1].toString() + " " +type);
				}
				
				if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
				{
					vo.setId(new Long("1"+params[0].toString()));
					vo.setName(params[1].toString());
				}
				vo.setCount(params[2] != null ? (Long)params[2] : 0);
				returnList.add(vo);
			}
		}
		return returnList;
	}
	
public List<BasicVO> getDistrictsListByUserId(Long userId,String accessType,Long accessValue){
		
		List<BasicVO> basicVOList = new ArrayList<BasicVO>();
		
		try {
			
			List<Object[]> accessDistrictsList = userDistrictAccessInfoDAO.findByUser(userId);
			
			if(accessDistrictsList != null && accessDistrictsList.size() > 0){
				for (Object[] objects : accessDistrictsList) {
					BasicVO vo = new BasicVO();
					
					vo.setId((Long) (objects[0] != null ? objects[0]:0l));
					vo.setName(objects[1] != null ? objects[1].toString():"");
					
					basicVOList.add(vo);
				}
			}
			
			if(accessType.equalsIgnoreCase("district")){
				District district = districtDAO.get(accessValue);
				
				if(district != null){
					BasicVO vo = new BasicVO();
					
					vo.setId(district.getDistrictId());
					vo.setName(district.getDistrictName());
					
					basicVOList.add(vo);
				}
			}
			else if(accessType.equalsIgnoreCase("state")){
				List<Object[]> districtsList = districtDAO.getDistrictsForState(1l);
				
				if(districtsList != null && districtsList.size() > 0){
					for (Object[] objects : districtsList) {
						BasicVO vo = new BasicVO();
						
						vo.setId((Long) (objects[0] != null ? objects[0]:0l));
						vo.setName(objects[1] != null ? objects[1].toString():"");
						
						basicVOList.add(vo);
					}
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return basicVOList;
	}

public List<ActivityVO> getDistrictWiseActivities(String startDateString,String endDateString,Long activityScopeId,Long activityLevelId,String accessType,Long accessValue,Long stateId,Long userId){
	List<ActivityVO> finalList=null;
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	try{
		 
		List<Long> distIds = new ArrayList<Long>(0);
		List<BasicVO> districtList = getDistrictsListByUserId(userId,accessType,accessValue);
		 if(districtList !=null && districtList.size()>0){
			 
			 finalList=new ArrayList<ActivityVO>(0);
			 for(BasicVO vo:districtList){
				 ActivityVO distVO=new ActivityVO();
				 distVO.setId(vo.getId());
				 distVO.setName(vo.getName());
				 distVO.setTotalCount(0l);
				 distVO.setPlannedCount(0l);
				 distVO.setConductedCount(0l);
				 distVO.setNonConductedCount(0l);
				 finalList.add(distVO);
				 distIds.add(distVO.getId());
			 }
			 
			//Dates
			 Date startDate=null;
			 Date endDate=null;
			 if(startDateString!=null && startDateString.trim().length()>0){
				 startDate=sdf.parse(startDateString);
			 }
			 if(endDateString!=null && startDateString.trim().length()>0){
				 endDate=sdf.parse(endDateString);
			 }
			 
			 //planned data
			 List<Object[]> plannedlist=activityLocationInfoDAO.getDistrictWiseDetails(startDate,endDate,activityScopeId,distIds);
			 if(plannedlist!=null && plannedlist.size()>0){
				 for(Object[] obj:plannedlist){
					 Long distId=(Long)obj[0];
					 ActivityVO distvo=getMatchedConstVO(distId,finalList);
					 if(distvo!=null){
						 distvo.setPlannedCount(obj[1]!=null?(Long)obj[1]:0l);
						 distvo.setConductedCount(obj[2]!=null?(Long)obj[2]:0l);
						 distvo.setNonConductedCount(distvo.getPlannedCount()-distvo.getConductedCount());
					}
				 }
			 }
		 }
	}catch(Exception e){
		LOG.error("Exception raised in getDistrictWiseActivities", e);
	}
	return finalList;
}
 public List<IdNameVO> getAllCastes(){
	 
	List<IdNameVO> idNameVOList=null;
	try{
		  List<Object[]> castesList = casteStateDAO.getAllCasteDetailsForVoters(1L); // for AP state
		  idNameVOList=buildIdNameVOList(castesList);
	}catch(Exception e){
		LOG.error("Exception raised in getAllCastes", e);
	}
	return idNameVOList;
 }
 
 public  List<IdNameVO> buildIdNameVOList(List<Object[]> list){
	 
	 List<IdNameVO> idNameVOList=null;
	 if(list!=null && list.size()>0){
		 idNameVOList=new ArrayList<IdNameVO>();
		 for(Object[] obj:list){
			 IdNameVO vo=new IdNameVO();
			 vo.setId(obj[0]!=null?(Long)obj[0]:0l);
			 vo.setName(obj[1]!=null?obj[1].toString():"");
			 idNameVOList.add(vo);
		 }
	 }
	 return idNameVOList;
 }
 
 public List<LocationWiseBoothDetailsVO> getConstituencyByDistrictId(Long districtId){
	 List<LocationWiseBoothDetailsVO> constiList = new ArrayList<LocationWiseBoothDetailsVO>();
	 LocationWiseBoothDetailsVO vo = null;
	 try {			
			 
			List<Object[]> result = constituencyDAO.getConstituenciesByDistrictId(districtId);
			if(result != null && result.size() >0){
			for(Object[] obj : result){
				vo = new LocationWiseBoothDetailsVO();
				vo.setLocationId(Long.valueOf(obj[0].toString()));
				vo.setLocationName(obj[1].toString());
				constiList.add(vo);
			}
			
		}
	} catch (Exception e) {
		LOG.error("Exception raised in getConstituencyByDistrict",e);
	}
	 return constiList;
 } 
 
 public List<BasicVO> getAllCommitteesForLevelId(Long levelId)
	{
	List<BasicVO> basicCmmty = new ArrayList<BasicVO>();
		try{
	List<Object[]> basicCommitteesRslt = tdpCommitteeDAO.getCommitteesForLevelId(levelId);
	
	if(basicCommitteesRslt!=null && basicCommitteesRslt.size()>0){
		for(Object[] obj:basicCommitteesRslt){
			BasicVO vo = new BasicVO();
			vo.setId((Long)obj[0]);
			vo.setName(obj[1].toString());
			basicCmmty.add(vo);
			}
		}
	}
		catch(Exception e)
		{
			LOG.error(e);
		}
		return basicCmmty;
	}
 public Map<Long, List<Long>> getActivityLocationWiseQuestionsData(Long activityScopeId,Long questionId,Long constistuencyId){
		Map<Long, List<Long>> finalMap = new LinkedHashMap<Long, List<Long>>(0);
		try {
			LOG.error("Entered  in to getActivityLocationWiseQuestionsData() method,");
			
			List<Object[]> activityLocationWiseList = activityQuestionAnswerDAO.getTheLocationWiseData(questionId, activityScopeId,constistuencyId);
			if(activityLocationWiseList != null && activityLocationWiseList.size()>0){
				for (Object[] obj : activityLocationWiseList) {
					Long optionId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long locationValue = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					List<Long> finalList = finalMap.get(optionId);
					if(finalList != null){
						finalList.add(locationValue);
					}
					else{
						finalList = new ArrayList<Long>();
						finalList.add(locationValue);
						finalMap.put(optionId, finalList);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getActivityLocationWiseQuestionsData() method, Exception - ",e);
		}
		return finalMap;
	}
 
 public LocationWiseBoothDetailsVO1 getActivityLocationDetailsNew(String isChecked,Long activityScopeId,Long activityLevelId,String searchBy,Long locationId,
		 String searchStartDateStr,String searchEndDateStr,Long constituencyId,Long optionId,Long questionId,List<String> datesList)
{
	LocationWiseBoothDetailsVO1 returnVO = null;	
	try {
		Map<Long,List<ActivityVO>> activityMap = new LinkedHashMap<Long, List<ActivityVO>>(0);
		if(activityLevelId.longValue() == 4l){
			List<LocationWiseBoothDetailsVO> returnStateList = new ArrayList<LocationWiseBoothDetailsVO>();
			String[] staArr = IConstants.STATIC_STATE_IDS.split(",");
			List<Long> stateIds = new ArrayList<Long>();
			if(staArr != null && staArr.length > 0){
				for (int i = 0; i < staArr.length; i++) {
					stateIds.add(Long.valueOf(staArr[i].toString()));
				}
			}
			List<Object[]> stateList = stateDAO.getAllStatesByStateIds(stateIds);
			if(commonMethodsUtilService.isListOrSetValid(stateList)){
				for (Object[] obj : stateList) {
					LocationWiseBoothDetailsVO vo = new LocationWiseBoothDetailsVO();
					
					vo.setLocationId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setLocationName(obj[1] != null ? obj[1].toString():"");
					
					returnStateList.add(vo);
				}
			}
			returnVO = new LocationWiseBoothDetailsVO1();
			returnVO.getResult().addAll(returnStateList);
		}
		else{
		List<LocationWiseBoothDetailsVO> returnList = null;
		List<Long> updatedLocationIdsList  = new ArrayList<Long>(0);
		List<Long> notUpdatedLocationIdsList  = new ArrayList<Long>(0);
		List<LocationWiseBoothDetailsVO> reportList = null;
		List<Long> constituencyIds = new ArrayList<Long>(0);
		
		//List<LocationWiseBoothDetailsVO> mandalList = new ArrayList<LocationWiseBoothDetailsVO>(0);
		//List<LocationWiseBoothDetailsVO> panchayatList=new ArrayList<LocationWiseBoothDetailsVO>(0);
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		
		Date startDate = null;
		Date endDate = null;
		
		if(searchStartDateStr != null && searchStartDateStr.trim().length() > 0 && searchEndDateStr != null && searchEndDateStr.trim().length() > 0){
			startDate = format.parse(searchStartDateStr);
			endDate = format.parse(searchEndDateStr);
		}
		Map<Long,List<Long>> questionResponsesMap = null;
		if(optionId != null && optionId>0L){
			questionResponsesMap = getActivityLocationWiseQuestionsData(activityScopeId,questionId,constituencyId);				
			if(questionResponsesMap == null || questionResponsesMap.size() == 0){
				returnVO = new LocationWiseBoothDetailsVO1();
				return returnVO;
			}	
		}
		if(activityScopeId != null && activityScopeId.longValue()>0L)
		{
			/* List<Object[]> updatedList= activityLocationInfoDAO.getUpdatedLocationsListForScope(activityScopeId,startDate,endDate);
			 if(updatedList != null && updatedList.size()>0)
			 {
				 for (Object[] locations : updatedList) {
					 Long locationValue = locations[0] != null ? Long.valueOf(locations[0].toString()):0L;
					 String planDate = locations[1] != null ? locations[1].toString():null;
					 String conductedDate = locations[2] != null ? locations[2].toString():null;
					 Long locationlevel = locations[3] != null ? Long.valueOf(locations[3].toString()):0L;
					 
					 Date planDateStr = planDate != null ? format1.parse(planDate):null;
					 Date conductedDateStr = conductedDate != null ? format1.parse(conductedDate):null;

					 String locationLevelId = "";
					 if(locationlevel.longValue() == 6L || locationlevel.longValue() == 7L)
					 {
						 locationLevelId = "1";
					 }
					 else if(locationlevel.longValue() == 8L || locationlevel.longValue() == 5L)
					 {
						 locationLevelId = "2";
					 }
					 else if(locationlevel.longValue() == 9L)
					 {
						 locationLevelId = "3";
					 }
					 String finalIdStr = locationLevelId+""+locationValue;
					 Long finalLocationId = Long.valueOf(finalIdStr);
					 List<ActivityVO> list = new ArrayList<ActivityVO>(0);
					 if(activityMap.get(locationValue) != null)
					 {
						 list = activityMap.get(locationValue);
					 }
					 ActivityVO vo = new ActivityVO();
					 if(planDateStr != null)
						 vo.setPlannedDate(format.format(planDateStr).toString());
					 if(conductedDateStr != null)
						 vo.setConductedDate(format.format(conductedDateStr).toString());
					 vo.setLocationValue(finalLocationId);
					 vo.setLocationLevel(locationlevel);
					 
					 list.add(vo);
					 activityMap.put(locationValue, list);
					// if(conductedDate != null )
		               updatedLocationIdsList.add(locationValue);
		             //else
		             //  notUpdatedLocationIdsList.add(locationValue);
					 //updatedLocationIdsList.add(locationValue);
				}
			 }*/
			 List<Object[]> updatedList= activityLocationInfoDatesDAO.getActivityDatesByScope(activityScopeId,startDate,endDate);
			 
			 
			 if(updatedList != null && updatedList.size() > 0)
			 {
				 for (Object[] locations : updatedList) {
					 String planDate = null;String conductedDate=null;
					 Long locationValue = locations[1] != null ? Long.valueOf(locations[1].toString()):0L;
					 if(locations[4] != null && (Long)locations[4] == 1l)
					  planDate = locations[3] != null ? locations[3].toString():null;
					 if(locations[4] != null && (Long)locations[4] == 2l)
					  conductedDate = locations[3] != null ? locations[3].toString():null;
					 Long locationlevel = locations[0] != null ? Long.valueOf(locations[0].toString()):0L;
					 Date planDateStr = planDate != null ? format1.parse(planDate):null;
					 Date conductedDateStr = conductedDate != null ? format1.parse(conductedDate):null;
					 String locationLevelId = "";
					 if(locationlevel.longValue() == 6L || locationlevel.longValue() == 7L)
					 {
						 locationLevelId = "1";
					 }
					 else if(locationlevel.longValue() == 8L || locationlevel.longValue() == 5L)
					 {
						 locationLevelId = "2";
					 }
					 else if(locationlevel.longValue() == 9L)
					 {
						 locationLevelId = "3";
					 }
					 String finalIdStr = locationLevelId+""+locationValue;
					 Long finalLocationId = Long.valueOf(finalIdStr);
					 List<ActivityVO> list = new ArrayList<ActivityVO>(0);
					 if(activityMap.get(locationValue) != null)
					 {
						 list = activityMap.get(locationValue);
					 }
					// ActivityVO vo = new ActivityVO();
					 ActivityVO vo = getMatchedLocation(list,locationlevel,finalLocationId,(Long)locations[2]);
					 if(vo == null)
					 {
						 vo = new ActivityVO(); 
					 }
					 if(planDateStr != null)
						 vo.setPlannedDate(format.format(planDateStr).toString());
					 if(conductedDateStr != null)
						 vo.setConductedDate(format.format(conductedDateStr).toString());
					 vo.setLocationValue(finalLocationId);
					 vo.setId(locationValue);
					 vo.setLocationLevel(locationlevel);
					 vo.setDay((Long)locations[2]);
					 vo.setDateStr(locations[3] != null ? locations[3].toString() : "");
					 list.add(vo);
					 activityMap.put(locationValue, list);
					 if(!updatedLocationIdsList.contains(locationValue))
					 updatedLocationIdsList.add(locationValue);
		             
				 }
			 }
			 
			 
			
		}
		if(activityLevelId != null && activityLevelId.longValue()>0L)
		{
			if(activityLevelId.longValue() == 2L)
			{
				if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.DISTRICT))
				{
					constituencyIds = constituencyDAO.getConstituencyIdsByDistrictId(locationId,IConstants.ASSEMBLY_ELECTION_TYPE_ID);
					reportList = getMandalMunicCorpDetailsByConstituencyList(constituencyIds);
				}
				else if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.CONSTITUENCY))
				{
					constituencyIds.add(locationId);
					reportList = getMandalMunicCorpDetailsByConstituencyList(constituencyIds);
				}
				else if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.MANDAL))
				{
					String locationTypeflagId = locationId.toString().substring(0, 1);
					String locatonId = locationId.toString().substring(1);
					reportList = new ArrayList<LocationWiseBoothDetailsVO>(0);
					if(locationTypeflagId.equalsIgnoreCase("2")){
						Tehsil tehsil= tehsilDAO.get(Long.valueOf(locatonId));
						if(tehsil != null)
							reportList.add(new LocationWiseBoothDetailsVO(locationId,tehsil.getTehsilName()));
					}
					else if(locationTypeflagId.equalsIgnoreCase("1")){
						LocalElectionBody localbody= localElectionBodyDAO.get(Long.valueOf(locatonId));
						if(localbody != null)
							reportList.add(new LocationWiseBoothDetailsVO(locationId,localbody.getName()));
					}
				}
				//if(reportList != null && reportList.size()>0)
				//	mandalList.addAll(reportList);
			}
			else if(activityLevelId.longValue() == 1L)
			{
				if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.CONSTITUENCY))
				{
					reportList = getPanchayatWardDivisionDetailsNew(locationId);
				}
				else if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.MANDAL))
				{
					reportList = getPanchayatWardByMandalId(locationId.toString(),constituencyId);
				}
				else if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.PANCHAYAT))
				{
					String locationTypeflagId = locationId.toString().substring(0, 1);
					String locatonId = locationId.toString().substring(1);
					reportList = new ArrayList<LocationWiseBoothDetailsVO>(0);
					if(locationTypeflagId.equalsIgnoreCase("2")){
						Constituency constituency= constituencyDAO.get(Long.valueOf(locatonId));
						if(constituency != null)
							reportList.add(new LocationWiseBoothDetailsVO(locationId,constituency.getName()));
					}
					else if(locationTypeflagId.equalsIgnoreCase("1")){
						Panchayat panchayat= panchayatDAO.get(Long.valueOf(locatonId));
						if(panchayat != null)
							reportList.add(new LocationWiseBoothDetailsVO(locationId,panchayat.getPanchayatName()));
					}
				}
				
				//if(reportList != null && reportList.size()>0)
				//	panchayatList.addAll(reportList);
			}
			else if(activityLevelId.longValue() == 5l)
			{
				if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.CONSTITUENCY))
				{
					reportList = new ArrayList<LocationWiseBoothDetailsVO>(0);
					Constituency constituency= constituencyDAO.get(Long.valueOf(locationId));
					if(constituency != null)
						reportList.add(new LocationWiseBoothDetailsVO(locationId,constituency.getName()));
				}
				else if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.DISTRICT))
				{
					reportList = getConstituencyByDistrictId(locationId);
				}
				
				
				//if(reportList != null && reportList.size()>0)
				//	panchayatList.addAll(reportList);
			}
			else if(activityLevelId.longValue() == 3l)
			{
				/*if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.CONSTITUENCY))
				{
					reportList = new ArrayList<LocationWiseBoothDetailsVO>(0);
					Constituency constituency= constituencyDAO.get(Long.valueOf(locationId));
					if(constituency != null)
						reportList.add(new LocationWiseBoothDetailsVO(locationId,constituency.getName()));
				}
				else
				if(searchBy != null && searchBy.trim().equalsIgnoreCase(IConstants.DISTRICT))
				{*/
				List<Object[]> list = null;
				reportList = new ArrayList<LocationWiseBoothDetailsVO>(0);
				if(locationId.longValue() > 0L){
					list = districtDAO.getDistrictDetailsById(locationId);
				}
				else{
					list = districtDAO.getDistrictIdAndNameByStateForStateTypeId(1L, 0L);
				}
				if(list != null && list.size() > 0){
					for (Object[] obj : list) {
						LocationWiseBoothDetailsVO vo = new LocationWiseBoothDetailsVO();
						vo.setLocationId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						vo.setLocationName(obj[1] != null ? obj[1].toString():"");
						reportList.add(vo);
					}
				}
						//reportList = getConstituencyByDistrictId(locationId);
				//}
				
				
				//if(reportList != null && reportList.size()>0)
				//	panchayatList.addAll(reportList);
			}
			
			//CadreCommitteeMemberVO membersVO = getAllCommitteeMembInfoInLocation(activityLevelId,constituencyIds,mandalList,panchayatList);
			//Map<Long,Map<Long,CadreCommitteeMemberVO>> mandalMap = new HashMap<Long,Map<Long,CadreCommitteeMemberVO>>();
			//Map<Long,Map<Long,CadreCommitteeMemberVO>> divisionMap = new HashMap<Long,Map<Long,CadreCommitteeMemberVO>>();
			//Map<Long,Map<Long,CadreCommitteeMemberVO>> townMap = new HashMap<Long,Map<Long,CadreCommitteeMemberVO>>();
			//Map<Long,Map<Long,CadreCommitteeMemberVO>> villageMap = new HashMap<Long,Map<Long,CadreCommitteeMemberVO>>();
			//Map<Long,Map<Long,CadreCommitteeMemberVO>> wardMap = new HashMap<Long,Map<Long,CadreCommitteeMemberVO>>();
			
			/*if(membersVO != null)
			{
				if(mandalList != null && mandalList.size()>0)
				{
					mandalMap = membersVO.getGenericMap1();
					divisionMap = membersVO.getGenericMap2();
					townMap = membersVO.getGenericMap3();
				}
				else if(panchayatList != null && panchayatList.size()>0)
				{
					villageMap = membersVO.getGenericMap1();
					wardMap = membersVO.getGenericMap1();
				}
			}*/
			 List<Long> locatnValList = null;
				List<Object[]> locatnValues = activityInfoDocumentDAO.getLocationValue(activityScopeId);
				if(locatnValues != null && locatnValues.size() >0){
					locatnValList = new ArrayList<Long>() ;
					for(Object[] obj : locatnValues){
						if(!locatnValList.contains(obj[2]))
								locatnValList.add((Long)obj[2]);
				}
			}
			String[] levelIdsArr = {"1","2"};
			if(reportList != null && reportList.size()>0)
			{
				returnVO = new LocationWiseBoothDetailsVO1();
				returnList = new ArrayList<LocationWiseBoothDetailsVO>(0);
				if(isChecked != null && isChecked.equalsIgnoreCase("notConducted"))
				{
					for (LocationWiseBoothDetailsVO vo : reportList) {
						String locationIdStr = vo.getLocationId().toString().substring(1);
						if(!updatedLocationIdsList.contains(Long.valueOf(locationIdStr))){
							
							LocationWiseBoothDetailsVO finalVO = new LocationWiseBoothDetailsVO();
							finalVO = vo;
							
							Long locationsId = vo.getLocationId();
							if(Arrays.asList(levelIdsArr).contains(activityLevelId.toString().trim()))
								locationsId = Long.valueOf(vo.getLocationId().toString().trim().substring(1));
							
							//To show Only Already Upload Images 
							if(locatnValList != null && locatnValList.contains(locationsId)){
								 vo.setIsAlreadyImageUpload("true");
							 }else{
								 vo.setIsAlreadyImageUpload("false"); 
							 }
							//List<ActivityVO> activityVOList = activityMap.get(locationsId);
							
							/*if(activityVOList != null && activityVOList.size()>0)
							{
								for (ActivityVO activityVO : activityVOList) {
									finalVO.setPlanedDate(activityVO.getPlannedDate());
									finalVO.setConductedDate(activityVO.getConductedDate());
									
									returnList.add(finalVO);
								}
							}else
							{
								returnList.add(finalVO);
							}*/
							returnList.add(finalVO);
						}
					}
				}
				else if(isChecked != null && isChecked.equalsIgnoreCase("conducted")){
					for (LocationWiseBoothDetailsVO vo : reportList) {
						String locationIdStr = vo.getLocationId().toString().substring(1);
						if(updatedLocationIdsList.contains(Long.valueOf(locationIdStr))){
							LocationWiseBoothDetailsVO finalVO = new LocationWiseBoothDetailsVO();
							finalVO = vo;
							
							Long locationsId = vo.getLocationId();
							if(Arrays.asList(levelIdsArr).contains(activityLevelId.toString().trim()))
								locationsId = Long.valueOf(vo.getLocationId().toString().trim().substring(1));
							
							if(locatnValList != null && locatnValList.contains(locationsId)){
								 vo.setIsAlreadyImageUpload("true");
							 }else{
								 vo.setIsAlreadyImageUpload("false"); 
							 }
						//	List<ActivityVO> activityVOList = activityMap.get(locationsId);
							
							/*if(activityVOList != null && activityVOList.size()>0)
							{
								for (ActivityVO activityVO : activityVOList) {
									finalVO.setPlanedDate(activityVO.getPlannedDate());
									finalVO.setConductedDate(activityVO.getConductedDate());
									
									returnList.add(finalVO);
								}
							}else
							{
								returnList.add(finalVO);
							}*/
							returnList.add(finalVO);
						}
					}
				}
				else if(isChecked != null && isChecked.equalsIgnoreCase("all")){
					
					for (LocationWiseBoothDetailsVO vo : reportList) {

						LocationWiseBoothDetailsVO finalVO = new LocationWiseBoothDetailsVO();
						finalVO = vo;
						Long locationsId = vo.getLocationId();
						if(Arrays.asList(levelIdsArr).contains(activityLevelId.toString().trim()))
							locationsId = Long.valueOf(vo.getLocationId().toString().trim().substring(1));
						
						if(locatnValList != null && locatnValList.contains(locationsId)){
							 vo.setIsAlreadyImageUpload("true");
						 }else{
							 vo.setIsAlreadyImageUpload("false"); 
						 }
						/*List<ActivityVO> activityVOList = activityMap.get(locationsId);
						if(activityVOList != null && activityVOList.size()>0)
						{
							for (ActivityVO activityVO : activityVOList) {
								finalVO.setPlanedDate(activityVO.getPlannedDate());
								finalVO.setConductedDate(activityVO.getConductedDate());
								
								returnList.add(finalVO);
							}
						}else
						{
							returnList.add(finalVO);
						}*/
						returnList.add(finalVO);
					}
				}
				Collections.sort(returnList,new Comparator<LocationWiseBoothDetailsVO>() {
					public int compare(LocationWiseBoothDetailsVO o1,
							LocationWiseBoothDetailsVO o2) {
						return o1.getLocationName().compareTo(o2.getLocationName());
					}
				});
				
				
				if(questionResponsesMap!= null && questionResponsesMap.size()>0){
					
					List<Long> locationValuesList = questionResponsesMap.get(optionId);
					List<LocationWiseBoothDetailsVO> optionsVOList = new ArrayList<LocationWiseBoothDetailsVO>(0);
					if(returnList!= null && returnList.size()>0){
						for (LocationWiseBoothDetailsVO vo : returnList) {
							Long locationsId = vo.getLocationId();
							if(Arrays.asList(levelIdsArr).contains(activityLevelId.toString().trim()))
								locationsId = Long.valueOf(vo.getLocationId().toString().trim().substring(1));
							
							if(locationValuesList != null && locationValuesList.contains(locationsId)){
								optionsVOList.add(vo);
							}
						}
						returnList.clear();
						if(optionsVOList != null && optionsVOList.size()>0){
							returnList.addAll(optionsVOList);
						}
					}
				}
				returnVO.getResult().addAll(returnList);
			}
		}
		}
		
		if(returnVO == null){
			returnVO = new LocationWiseBoothDetailsVO1();
		}
		
		
			//getting Required attributes For every scope start		
			
			List<IdNameVO> idnameList = new ArrayList<IdNameVO>(0);
			List<Long> idsList = new ArrayList<Long>();
			List<Object[]> requiredObjList = activityScopeRequiredAttributesDAO.getRequiredAttributesOfScope(activityScopeId);			
			if(commonMethodsUtilService.isListOrSetValid(requiredObjList)){				
				String[] setterPropertiesList = {"id","name"};
				idnameList = (List<IdNameVO>) setterAndGetterUtilService.setValuesToVO(requiredObjList, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");
				for(Object[] obj:requiredObjList){
					idsList.add(obj[0] !=null ? (Long)obj[0]:0l);
				}
			}			
			if(commonMethodsUtilService.isListOrSetValid(idnameList)){
				returnVO.setIdNameVolist(idnameList);
			}
			
			//getting Required attributes For every scope end
			
			/*get Activity scope Dates and betweenDates Of those start*/
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			
			Date frmDate=null;
				Date toDate =null;
				Object[] scopeDates = activityScopeDAO.getRequiredDatesOfScope(activityScopeId);
			
				if(scopeDates !=null && scopeDates.length>0){
					frmDate = scopeDates[0] !=null ? (Date)scopeDates[0]:null;
					toDate =  scopeDates[1] !=null ? (Date)scopeDates[1]:null;
				}
				
				List<String> datesStr = new ArrayList<String>(0);
				if(frmDate !=null && toDate !=null){
					Long noOfTimes = activityScopeDAO.getNoOfTimesCountForActivityScope(activityScopeId);
					List<Date> dates = commonMethodsUtilService.getBetweenDates(frmDate, toDate);
					if(noOfTimes != null && noOfTimes.longValue() != 1l && Long.valueOf(String.valueOf(dates.size())) == noOfTimes.longValue()){
						int i=0;
						if(dates !=null && dates.size()>0){
							for (Date date : dates) {
								i++;
								datesStr.add("Day" +i+ "("+sdf.format(date)+")");
							}							
						}
					}
				}
				
			/*get Activity scope Dates and betweenDates Of those End*/
		
			
			if(returnVO !=null && commonMethodsUtilService.isListOrSetValid(returnVO.getResult())){				
				for (LocationWiseBoothDetailsVO obj : returnVO.getResult()) {
						
					List<LocationWiseBoothDetailsVO2> VOList  = new ArrayList<LocationWiseBoothDetailsVO2>(0);
					if(obj.getResult() == null || obj.getResult().size() ==0){						
						if(commonMethodsUtilService.isListOrSetValid(datesList)){							 
							VOList = setDayObjToList(datesList,VOList,obj);													
						}else if(commonMethodsUtilService.isListOrSetValid(datesStr)){							
							VOList = setDayObjToList(datesStr,VOList,obj);
						}
						else{
							LocationWiseBoothDetailsVO2 vo = new LocationWiseBoothDetailsVO2();
							vo.setLocationId(obj.getLocationId());
							vo.setLocationName(obj.getLocationName());
							vo.setIsAlreadyImageUpload(obj.getIsAlreadyImageUpload());
							vo.setDay("day");
							VOList.add(vo);
						}
						obj.setResult2(VOList);
					} 
										
				}	
				
				for (LocationWiseBoothDetailsVO obj : returnVO.getResult()) {
					
						List<ActivityVO> activityVOList = activityMap.get(obj.getId());
						if(activityVOList != null && activityVOList.size()>0)
						{
							for (ActivityVO activityVO : activityVOList) {
								if(activityVO.getDateStr() != null && !activityVO.getDateStr().isEmpty())
								{
									LocationWiseBoothDetailsVO2 vo = getMatchedDateObj(obj.getResult2(),activityVO.getLocationValue(),activityVO.getDateStr());
									if(vo != null)
									{
										obj.setPlanedDate(vo.getPlanedDate());
									obj.setConductedDate(vo.getConductedDate());
									}
								}
								
							 }
						}
					
					} 
										
					
				
			}
			
			
			
			
			
	} catch (Exception e) {
		 LOG.error("Exception Occured in getActivityLocationDetailsNew() method, Exception - ",e);
	}
	
	return returnVO;
}
 
 
 public LocationWiseBoothDetailsVO2 getMatchedDateObj(List<LocationWiseBoothDetailsVO2> list,Long locationId,String date)
 {
	 if(list == null)
		 return null;
	 
	 String dateStr = date.substring(7, 10)+"-"+date.substring(3, 4)+"-"+ date.substring(1, 2);
	 for(LocationWiseBoothDetailsVO2 vo : list)
	 {
		 if(vo.getLocationId().longValue() == locationId && vo.getDateStr().equalsIgnoreCase(dateStr))
			 return vo;
	 }
	 
	return null; 
 }
 public ActivityVO getMatchedLocation(List<ActivityVO> list,Long locationlevel,Long finalLocationId,Long day)
 {
	 if(list == null)
		 return null;
	 for(ActivityVO vo : list)
	 {
		 if(vo.getLocationLevel().longValue() == locationlevel && finalLocationId.longValue() == vo.getLocationValue().longValue() && day.longValue() == vo.getDay().longValue())
			 return vo;
	 }
	 
	return null;
	 
 }
 public List<LocationWiseBoothDetailsVO2> setDayObjToList(List<String> datesList,List<LocationWiseBoothDetailsVO2> VOList,LocationWiseBoothDetailsVO obj){
	 try{
		 
		 if(datesList !=null && datesList.size()>0){
			 for (String objects : datesList) {	
				 	//LocationWiseBoothDetailsVO vo = (LocationWiseBoothDetailsVO)obj.clone();
				 LocationWiseBoothDetailsVO2 vo = new LocationWiseBoothDetailsVO2();
				 	vo.setDay(objects);		
				 	int firstBracket = objects.indexOf('(');
				 	String contentOfBrackets = objects.substring(firstBracket + 1, objects.indexOf(')', firstBracket));
				 	vo.setDateStr(contentOfBrackets);
				 	vo.setLocationId(obj.getLocationId());
				 	vo.setLocationName(obj.getLocationName());
				 	vo.setIsAlreadyImageUpload(obj.getIsAlreadyImageUpload());
				 	
				 	/*vo.setPlanedDate(obj.getPlanedDate());
				 	vo.setConductedDate(obj.getConductedDate());*/
				 	
					VOList.add(vo);
				}
		 }
		 
	 }catch (Exception e) {
		 LOG.error("Exception Occured in setDayObjToList() method, Exception - ",e);
	}
	 return VOList;
 }
 
 public List<LocationWiseBoothDetailsVO> getSubLevelForConstituency(Long stateId, List<Long> districtIds, List<Long> constituencyIds, Long locationLevelId){
	 List<LocationWiseBoothDetailsVO> finaVoList = new ArrayList<LocationWiseBoothDetailsVO>();
		try{
			List<Long> constiIds = new ArrayList<Long>();
		/*	List<Long> mandalIds = new ArrayList<Long>();
			List<Long> localBodyIds = new ArrayList<Long>();*/
			if(locationLevelId.equals(5l)){
				if(constituencyIds!=null && constituencyIds.size()>0){
					constiIds = constituencyIds;
				}else{
					//List<Object[]> rslt = constituencyDAO.getConstituenciesByStateAndDistrict(stateId, districtIds);
					List<LocationWiseBoothDetailsVO> rslt = getConstituencyOfDistrict(stateId, districtIds);
					if(rslt!=null && rslt.size()>0){
						for(LocationWiseBoothDetailsVO obj:rslt){
							constiIds.add(obj.getLocationId());
						}
					}					
				}
			}
			
			
			if(locationLevelId.equals(5l)){
				finaVoList =  getMandalMunicCorpDetailsOfConstituencies(constiIds,"nominatedPostFilter");
			}
			
		}catch (Exception e) {
			LOG.error("Exception raised in getSubLevelForConstituency", e);
		}
		
		return finaVoList;
	}
 public List<CadreCommitteeVO> getFieldMonitoringMapReportDetails(Long constitunecyId,Long fieldUserId)
 {
	List<CadreCommitteeVO> returnList = new ArrayList<CadreCommitteeVO>();
	try{
		List<Object[]> reportDetails = cadreRegUserTabUserDAO.getFieldMonitoringMapReportDetails(constitunecyId,fieldUserId);
		if(reportDetails != null && reportDetails.size() > 0){
			
			for (Object[] param : reportDetails) {
				CadreCommitteeVO vo = new CadreCommitteeVO();
				vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setCadreName(commonMethodsUtilService.getStringValueForObject(param[1]));
				returnList.add(vo);
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		LOG.error("Exception Occured in getFieldMonitoringMapReportDetails() Method - Exception is : ",e);
	}
	return returnList;
	
}
 public List<LocationWiseBoothDetailsVO> getMandalMunicCorpDetails(Long constituencyId){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		LocationWiseBoothDetailsVO vo = null;
		List<SelectOptionVO> locations = regionServiceDataImp.getAllMandalsByConstituencyID(constituencyId);
		List<Object[]> localBodies = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituency(constituencyId);
	        for(SelectOptionVO location:locations){
	        	vo = new LocationWiseBoothDetailsVO();
	        	vo.setLocationId(Long.valueOf("4"+location.getId()));
	        	vo.setLocationName(location.getName()+" Mandal");
	        	locationsList.add(vo);
	        }
	        for(Object[] localBodi:localBodies){
	        	Long localBdyId = (Long)localBodi[0];
	        	if(!(localBdyId.longValue() == 20l ||  localBdyId.longValue() == 124l || localBdyId.longValue() == 119l)){
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("5"+localBodi[0].toString()));
		        	vo.setLocationName(localBodi[1].toString());
		        	locationsList.add(vo);
	        	}
	        }
	        return locationsList;
	}
public List<LocationWiseBoothDetailsVO> getPanchayatList(Long tehsilId)
{
	List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
	LocationWiseBoothDetailsVO	vo = null;
	try{
		if(tehsilId.toString().substring(0,1).equalsIgnoreCase("4")){
			List<Object[]> panchayatsList = panchayatDAO.getPanchayatList(Long.parseLong(tehsilId.toString().substring(1)));
			for(Object[] panchayat:panchayatsList){
				vo = new LocationWiseBoothDetailsVO();
		 	vo.setLocationId(Long.valueOf("7"+(Long)panchayat[0]));
		 	vo.setLocationName(panchayat[1].toString());
		 	locationsList.add(vo);
			}
		}else if(tehsilId.toString().substring(0,1).equalsIgnoreCase("5")){
			List<Object[]> wardList = constituencyDAO.getWardDetailsIdsForLocalBody(Long.parseLong(tehsilId.toString().substring(1)));
			for(Object[] panchayat:wardList){
				vo = new LocationWiseBoothDetailsVO();
		 	vo.setLocationId(Long.valueOf("8"+(Long)panchayat[0]));
		 	vo.setLocationName(panchayat[1].toString());
		 	locationsList.add(vo);
		}
	}
	}catch(Exception e)
	{
		e.printStackTrace();
		LOG.error("Exception Occured in getPanchayatList() Method - Exception is : ",e);
	}
	return locationsList;
}

//UpdateNominationPostcandidate
public List<CadreCommitteeVO> updateSearchTdpCadreDetailsBySearchCriteriaForCadreCommitte(String searchType,String searchValue)
{
	List<CadreCommitteeVO> returnList = new ArrayList<CadreCommitteeVO>();
	try {
		List<Long> tdpCadreIdsNominatedIdsList = new ArrayList<Long>(0);
		List<Object[]> nomPstCndList = nominationPostCandidateDAO.updateCadresearch(searchType,searchValue);
		if(nomPstCndList != null && nomPstCndList.size() > 0){
			for (Object[] objects : nomPstCndList) {
				CadreCommitteeVO vo = new CadreCommitteeVO();
				vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
				vo.setMobileNo(commonMethodsUtilService.getStringValueForObject(objects[1]));
				vo.setCadreName(commonMethodsUtilService.getStringValueForObject(objects[2]));
				vo.setVoterCardNo(commonMethodsUtilService.getStringValueForObject(objects[3]));
				vo.setImageURL(commonMethodsUtilService.getStringValueForObject(objects[4]));
				vo.setCasteName(commonMethodsUtilService.getStringValueForObject(objects[6]));
				vo.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(objects[7]));
				vo.setMemberShipCardId(commonMethodsUtilService.getStringValueForObject(objects[8]));
				returnList.add(vo);
			}
		}
		
	} catch (Exception e) {
		LOG.error("Exception raised in searchTdpCadreDetailsBySearchCriteriaForCadreCommitte", e);
	}
	return returnList;
}
public List<CadreCommitteeVO> getCadreEnrollmentYears()
{
	List<CadreCommitteeVO> returnList = new ArrayList<CadreCommitteeVO>();
	CadreCommitteeVO vo = null;
	try {
		List<Object[]> cadreCommitteeYearsLst = tdpCommitteeEnrollmentDAO.getCadreCommitteYearsList();
		if(cadreCommitteeYearsLst != null && cadreCommitteeYearsLst.size() > 0){
			for (Object[] objects : cadreCommitteeYearsLst) {
				vo = new CadreCommitteeVO();
				vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
				vo.setElectionYear(commonMethodsUtilService.getStringValueForObject(objects[1]));
				
				returnList.add(vo);
			}
		}
		
	} catch (Exception e) {
		LOG.error("Exception raised in getCadreEnrollmentYears", e);
	}
	return returnList;
}
public List<CadreCommitteeVO> getCommitteeDetailsByEnrollementId(List<Long> enrollYearIds)
{
	List<CadreCommitteeVO> returnList = new ArrayList<CadreCommitteeVO>();
	CadreCommitteeVO vo = null;
	try {
		List<Object[]> CommitteesLst = tdpCommitteeDAO.getTdpCommitteeDetailsByEnrollmentId(enrollYearIds);
		if(CommitteesLst != null && CommitteesLst.size() > 0){
			for (Object[] objects : CommitteesLst) {
				vo = new CadreCommitteeVO();
				vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
				vo.setFromDate(commonMethodsUtilService.getStringValueForObject(objects[1]));
				vo.setToDate(commonMethodsUtilService.getStringValueForObject(objects[2]));
				
				returnList.add(vo);
			}
		}
		
	} catch (Exception e) {
		LOG.error("Exception raised in getCommitteeDetailsByEnrollementId", e);
	}
	return returnList;
}

public LocationWiseBoothDetailsVO getCommitteeMembersAvailableInfo1(Long levelId,Long levelValue,Long committeeEnrollmentId,String startDate,String endDate,Long basicCommitteetypeId){
	LocationWiseBoothDetailsVO returnVo = null;
	try{
	SimpleDateFormat format =  new SimpleDateFormat("MM/dd/yyyy");
    Date stDate = (Date)format.parse(startDate);
    Date edDate = (Date)format.parse(endDate);
	Long committeeId = getCommitteeId(levelId,levelValue,committeeEnrollmentId,stDate,edDate,basicCommitteetypeId);
	if(committeeId != null){
		returnVo =  getCommitteeMembersInfoNEW(committeeId);
	}else{
		returnVo =  new LocationWiseBoothDetailsVO();
	}
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Exception raised in getCommitteeMembersAvailableInfo1", e);
	}
	return returnVo;
}

public Long getCommitteeId(Long levelId,Long levelValue,Long committeeEnrollmentId,Date stDate,Date edDate,Long basicCommitteetypeId){
	Long committeeId = null;
	try{
		List<Long> committeeIds = tdpCommitteeDAO.getCommitteeIds(levelId, levelValue,committeeEnrollmentId,stDate,edDate,basicCommitteetypeId);
		if(committeeIds.size() > 0){
			committeeId = committeeIds.get(0);
		}
	}catch(Exception e){
		LOG.error("Exception raised in getMainCommitteeIdInALocation", e);
	}
	return committeeId;
}



public List<LocationWiseBoothDetailsVO> getCommitteeCreationDetails(Long committeeTypeId,List<Long> committeeLevlIdsList,List<Long> designationsList,Long locationLvlId,List<Long> loctnLevlValues,
		List<Long> committeeEnrollmntIds,Long stateId,String searchType){
	
	List<LocationWiseBoothDetailsVO> returnList = new ArrayList<LocationWiseBoothDetailsVO>();
	try{
		String levlValue = "";
		List<Long> loctnLevlValues1 = new ArrayList<Long>();
		if(locationLvlId != null && locationLvlId.longValue() ==5l){
			if(loctnLevlValues != null && loctnLevlValues.size() > 0){
				for(Long loctnLevlValue : loctnLevlValues){
					if(loctnLevlValue.toString().substring(0,1).trim().equalsIgnoreCase("1")){
						loctnLevlValue = Long.valueOf(loctnLevlValue.toString().substring(1));
						loctnLevlValues1.add(loctnLevlValue);
						locationLvlId = 7l;
					}else if(loctnLevlValue.toString().substring(0,1).trim().equalsIgnoreCase("2")){
						loctnLevlValue = Long.valueOf(loctnLevlValue.toString().substring(1));
						loctnLevlValues1.add(loctnLevlValue);
						locationLvlId = 5l;
					}
				}
			
			}
		}else if(locationLvlId != null && locationLvlId.longValue() ==6l){
			if(loctnLevlValues != null && loctnLevlValues.size() > 0){
				for(Long loctnLevlValue : loctnLevlValues){
					if(loctnLevlValue.toString().substring(0,1).trim().equalsIgnoreCase("1")){
						loctnLevlValue = Long.valueOf(loctnLevlValue.toString().substring(1));
						loctnLevlValues1.add(loctnLevlValue);
						locationLvlId = 6l;
					}else if(loctnLevlValue.toString().substring(0,1).trim().equalsIgnoreCase("2")){
						loctnLevlValue = Long.valueOf(loctnLevlValue.toString().substring(1));
						loctnLevlValues1.add(loctnLevlValue);
						locationLvlId = 8l;
					}
				}
			
			}
		}
		
		if(loctnLevlValues1 != null && loctnLevlValues1.size() >0){
			loctnLevlValues.clear();
			loctnLevlValues.addAll(loctnLevlValues1);
		}
		
		//List<BasicVO> roles  = getCommitteeRoles();
		
		List<Object[]> rolesList = tdpCommitteeRoleDAO.getTdpRoles(committeeEnrollmntIds,committeeLevlIdsList);
		Map<Long,LocationWiseBoothDetailsVO> getMatchdMap = new HashMap<Long,LocationWiseBoothDetailsVO>();
		List<Object[]> locListFTot = tdpCommitteeMemberDAO.getCommitteeCreationDetails(committeeTypeId,committeeLevlIdsList,designationsList,locationLvlId,loctnLevlValues,committeeEnrollmntIds,stateId,searchType);
		if(locListFTot != null  && locListFTot.size() >0){
					for(Object[] obj :locListFTot){
						LocationWiseBoothDetailsVO vo  = null;
						if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(10l)){
							vo = getMatchdMap.get(commonMethodsUtilService.getLongValueForObject(obj[0]));
						 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(11l)){
							 vo = getMatchdMap.get(commonMethodsUtilService.getLongValueForObject(obj[2]));
						 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 3 ){
							 vo = getMatchdMap.get(obj[6] != null ? (Long)obj[6] : (obj[8] != null ? (Long)obj[8] : 0l));
						}else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 2 ){
							vo = getMatchdMap.get(obj[10] != null ? (Long)obj[10] : (obj[12] != null ? (Long)obj[12] : 0l));
						}
						
						if(vo != null){
							SelectOptionVO committeeVO = getMatchedVO1(vo.getHamletsOfTownship(),(Long)obj[14]);
							if(committeeVO == null){
								//List<LocationWiseBoothDetailsVO> committees = new ArrayList<LocationWiseBoothDetailsVO>();
								 committeeVO = new SelectOptionVO();
								committeeVO.setId(commonMethodsUtilService.getLongValueForObject(obj[14]));//basiCommitteeId
								committeeVO.setName(commonMethodsUtilService.getStringValueForObject(obj[18]));//basiCommitteeName
								List<LocationWiseBoothDetailsVO> tdpRoles = new ArrayList<LocationWiseBoothDetailsVO>();
								if(commonMethodsUtilService.isListOrSetValid(rolesList)){
									for(Object[] role :rolesList){
										LocationWiseBoothDetailsVO roleVo = new LocationWiseBoothDetailsVO();
										roleVo.setId(commonMethodsUtilService.getLongValueForObject(role[0]));
										roleVo.setName(commonMethodsUtilService.getStringValueForObject(role[1]));
										tdpRoles.add(roleVo);
									}
								}
								committeeVO.setResult(tdpRoles);
								
								if(vo.getHamletsOfTownship() == null){
									List<SelectOptionVO> committees = new ArrayList<SelectOptionVO>();
									vo.setHamletsOfTownship(committees);
								}
								vo.getHamletsOfTownship().add(committeeVO);
								
							}
						}else{
							//List<LocationWiseBoothDetailsVO> committees = new ArrayList<LocationWiseBoothDetailsVO>();
									 vo = new LocationWiseBoothDetailsVO();
								vo.setLocationId(commonMethodsUtilService.getLongValueForObject(obj[0]));//stateId
								vo.setLocationName(commonMethodsUtilService.getStringValueForObject(obj[1]));//stateName
								vo.setDistrictId(commonMethodsUtilService.getLongValueForObject(obj[2]));
								vo.setDistrictName(commonMethodsUtilService.getStringValueForObject(obj[3]));
								vo.setConstituencyId(commonMethodsUtilService.getLongValueForObject(obj[4]));
								vo.setConstituencyName(commonMethodsUtilService.getStringValueForObject(obj[5]));
								if(obj[6] != null && obj[7].toString() != null){
									vo.setMandalId(commonMethodsUtilService.getLongValueForObject(obj[6]));
									vo.setMandalName(commonMethodsUtilService.getStringValueForObject(obj[7]));
								}else if(obj[8] != null && obj[9].toString() != null){
									vo.setMandalId(commonMethodsUtilService.getLongValueForObject(obj[8]));
									vo.setMandalName(commonMethodsUtilService.getStringValueForObject(obj[9]));
								}
								
								if(obj[10] != null && obj[11].toString() != null){
									vo.setVillageId(commonMethodsUtilService.getLongValueForObject(obj[10]));
									vo.setVillageName(commonMethodsUtilService.getStringValueForObject(obj[11]));
								}else if(obj[12] != null && obj[13].toString() != null){
									vo.setVillageId(commonMethodsUtilService.getLongValueForObject(obj[12]));
									vo.setVillageName(commonMethodsUtilService.getStringValueForObject(obj[13]));
								}
								
								
								
								SelectOptionVO committeeVO = new SelectOptionVO();
								committeeVO.setId(commonMethodsUtilService.getLongValueForObject(obj[14]));//basiCommitteeId
								committeeVO.setName(commonMethodsUtilService.getStringValueForObject(obj[18]));//basiCommitteeName
								List<LocationWiseBoothDetailsVO> tdpRoles = new ArrayList<LocationWiseBoothDetailsVO>();
								if(commonMethodsUtilService.isListOrSetValid(rolesList)){
									for(Object[] role :rolesList){
										LocationWiseBoothDetailsVO roleVo = new LocationWiseBoothDetailsVO();
										roleVo.setId(commonMethodsUtilService.getLongValueForObject(role[0]));
										roleVo.setName(commonMethodsUtilService.getStringValueForObject(role[1]));
										tdpRoles.add(roleVo);
									}
								}
								committeeVO.setResult(tdpRoles);
								
								if(vo.getHamletsOfTownship() == null){
									List<SelectOptionVO> committees = new ArrayList<SelectOptionVO>();
									vo.setHamletsOfTownship(committees);
								}
								vo.getHamletsOfTownship().add(committeeVO);
								if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(10l)){
									getMatchdMap.put(commonMethodsUtilService.getLongValueForObject(obj[0]), vo);
								 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(11l)){
									 getMatchdMap.put(commonMethodsUtilService.getLongValueForObject(obj[2]), vo);
								 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(4l)){
									 getMatchdMap.put(commonMethodsUtilService.getLongValueForObject(obj[4]), vo);
								 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 2 ){
									 getMatchdMap.put(vo.getVillageId(), vo);
								}else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 3 ){
									getMatchdMap.put(vo.getMandalId(), vo);
								}
						}
					}
				}
		
		if(locListFTot != null  && locListFTot.size() >0){
			for(Object[] obj :locListFTot){
				LocationWiseBoothDetailsVO vo  = null;
				if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(10l)){
					vo = getMatchdMap.get(commonMethodsUtilService.getLongValueForObject(obj[0]));
				 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(11l)){
					 vo = getMatchdMap.get(commonMethodsUtilService.getLongValueForObject(obj[2]));
				 }/*else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(4l)){
					 vo = getMatchdMap.get(commonMethodsUtilService.getLongValueForObject(obj[5]));
				 }*/else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 3 ){
					 vo = getMatchdMap.get(obj[6] != null ? (Long)obj[6] : (obj[8] != null ? (Long)obj[8] : 0l));
				}else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 2 ){
					vo = getMatchdMap.get(obj[10] != null ? (Long)obj[10] : (obj[12] != null ? (Long)obj[12] : 0l));
				}
				
				if(vo != null){
					
					SelectOptionVO committeeVO = getMatchedVO1(vo.getHamletsOfTownship(),(Long)obj[14]);
					if(committeeVO != null){
					LocationWiseBoothDetailsVO roleVO = getMatchedVO2(committeeVO.getResult(),(Long)obj[15]);
						if(roleVO != null){
							roleVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(obj[17]));
						}
					}
				}
			}
		}
		
		
		List<Object[]> propsdFinlsdList = tdpCommitteeMemberDAO.getProposedAndFinalyzedCount(committeeTypeId,committeeLevlIdsList,designationsList,locationLvlId,loctnLevlValues,committeeEnrollmntIds,stateId,searchType);
		
		if(propsdFinlsdList != null  && propsdFinlsdList.size() >0){
			for(Object[] obj :propsdFinlsdList){
				LocationWiseBoothDetailsVO vo  = null;
				if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(10l)){
					vo = getMatchdMap.get(commonMethodsUtilService.getLongValueForObject(obj[0]));
				 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(11l)){
					 vo = getMatchdMap.get(commonMethodsUtilService.getLongValueForObject(obj[2]));
				 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(4l)){
					 vo = getMatchdMap.get(commonMethodsUtilService.getLongValueForObject(obj[4]));
				 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 3 ){
					 vo = getMatchdMap.get(obj[6] != null ? (Long)obj[6] : (obj[8] != null ? (Long)obj[8] : 0l));
				}else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 2 ){
					vo = getMatchdMap.get(obj[10] != null ? (Long)obj[10] : (obj[12] != null ? (Long)obj[12] : 0l));
				}
				
				if(vo != null){
					SelectOptionVO committeeVO = getMatchedVO1(vo.getHamletsOfTownship(),(Long)obj[14]);
					if(committeeVO != null){
					LocationWiseBoothDetailsVO roleVO = getMatchedVO2(committeeVO.getResult(),(Long)obj[15]);
					if(roleVO != null){
						if(obj[17] != null && obj[17].toString().equalsIgnoreCase("F")){
							roleVO.setFinalizedCount(roleVO.getFinalizedCount()+commonMethodsUtilService.getLongValueForObject(obj[18]));
						}else if(obj[17] != null && obj[17].toString().equalsIgnoreCase("P")){
							roleVO.setProposedCount(roleVO.getProposedCount()+commonMethodsUtilService.getLongValueForObject(obj[18]));
						}
						
					}
					}
				}
			}
		}
		
		if(commonMethodsUtilService.isMapValid(getMatchdMap)){
			returnList.addAll(getMatchdMap.values());
		}
		
		
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Exception raised in getMainCommitteeIdInALocation", e);
	}
	return returnList;
 }
public LocationWiseBoothDetailsVO getMatchedVO2(List<LocationWiseBoothDetailsVO> list,Long id)
{
	LocationWiseBoothDetailsVO returnVO = null;
	try {
		
		if(list != null && list.size()>0)
		{
			for (LocationWiseBoothDetailsVO roleVO : list)
			{
				if(roleVO.getId().longValue() == id.longValue())
				{
					return roleVO;
				}
			}
		}
	} catch (Exception e) {
		LOG.error("Exception raised in getMatchedVO", e);
	}
	return returnVO;
}
public SelectOptionVO getMatchedVO1(List<SelectOptionVO> list,Long id)
{
	SelectOptionVO returnVO = null;
	try {
		
		if(list != null && list.size()>0)
		{
			for (SelectOptionVO committeeVO : list)
			{
				if(committeeVO.getId().longValue() == id.longValue())
				{
					return committeeVO;
				}
			}
		}
	} catch (Exception e) {
		LOG.error("Exception raised in getMatchedVO", e);
	}
	return returnVO;
}
public List<BasicVO> userWiseDetailsForDashBoard(Long userId, String accessType, String accessValue){
	//String userAccessValue = "ALL";
	List<BasicVO> returnList =  new ArrayList<BasicVO>();
	try {
		List<Long> distrctIds = new ArrayList<Long>();
		if(accessType.equalsIgnoreCase("MP"))
		{	
			List<Object[]> assmblyList = userConstituencyAccessInfoDAO.findByUser(userId);
			if(assmblyList.isEmpty()){
				assmblyList = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituencies(Long.valueOf(accessValue));
			}
			
			if(assmblyList != null && assmblyList.size() > 0){
				for (Object[] objects : assmblyList) {
					BasicVO vo = new BasicVO();
					vo.setParlimentId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setParliament(commonMethodsUtilService.getStringValueForObject(objects[1]));
					//vo.setAccessType(accessType);
					returnList.add(vo);
					
				}
			}
		}
		else if(accessType.equalsIgnoreCase("DISTRICT")){
			List<Long> districtsList = userDistrictAccessInfoDAO.getDistrictIdsByUsrId(userId);
						distrctIds.addAll(districtsList);
			List<Object[]> constList = constituencyDAO.getConstituencyListByDistrictIdsList(distrctIds);
			if(constList != null && constList.size() > 0){
				for (Object[] objects : constList) {
					BasicVO vo = new BasicVO();
					vo.setParlimentId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setParliament(commonMethodsUtilService.getStringValueForObject(objects[1]));
					returnList.add(vo);
				}
			}
		}
		else
		{
			List<Object[]> accessDistrictsList = userDistrictAccessInfoDAO.findByUser(userId);
			if(accessDistrictsList != null && accessDistrictsList.size()>0)
			{
				for (Object[] objects : accessDistrictsList) {
					BasicVO vo =  new BasicVO();
					vo.setDistrictId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					//vo.setAccessType(accessType);
					returnList.add(vo);
					//districtIds.add(districtId[0] != null ? Long.valueOf(districtId[0].toString().trim()):0L);
				}
				
				/*if(districtIds != null && districtIds.size() == 1)
				{
					Long districtId = districtIds.get(0).longValue();
					if(districtId != 0L)
						userAccessValue =  districtDAO.get(districtId).getDistrictName()+" District";
				}
				else if(districtIds != null && districtIds.contains(1L)) // Adilabad
				{
					userAccessValue = "TS";
				}
				else if(districtIds != null && districtIds.contains(11L))//Srikakulam
				{
					userAccessValue = "AP";
				}*/
			}	
		}
	} catch (Exception e) {
		LOG.error("Exception raised in userAccessTypeDetailsForDashBoard", e);
	}
	return returnList;
}
public List<LocationWiseBoothDetailsVO> getTdpCommitteePanchayatWardByMandal(String mandalId,Long constituencyId,Long enrollmentId){
	List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
	// LocationWiseBoothDetailsVO vo1 = new LocationWiseBoothDetailsVO();
	   //vo1.setLocationId(0l);
	   //vo1.setLocationName("Select Panchayat/Ward/Division/City");
	  // locationsList.add(vo1);
	LocationWiseBoothDetailsVO vo = null;
	List<Long> mandalIds = new ArrayList<Long>();
	List<Long> localBodyIds = new ArrayList<Long>();
	
	if((mandalId.substring(0,1)).equalsIgnoreCase("2")){
		mandalIds.add(Long.valueOf(mandalId.substring(1)));
	}
	if((mandalId.substring(0,1)) .equalsIgnoreCase("1")){
		localBodyIds.add(Long.valueOf(mandalId.substring(1)));
	}
	
	if(mandalIds.size()>0){
    	//0panchayatId,1panchayatName
    	List<Object[]> panchayatsList = tdpCommitteeDAO.getTdpCommitteeAllPanchayatsInMandals(mandalIds,enrollmentId,constituencyId);
    	for(Object[] panchayat:panchayatsList){
    		vo = new LocationWiseBoothDetailsVO();
        	vo.setLocationId(Long.valueOf("1"+(Long)panchayat[0]));
        	vo.setLocationName(panchayat[1].toString()+"("+panchayat[2].toString()+")");
        	locationsList.add(vo);
    	}
    }
	   if(localBodyIds.size() > 0){
		   List<Object[]> localBodyList = new ArrayList<Object[]>();
		   //0wardId,1pwardName,2localBdyName
		   if(constituencyId == null || constituencyId.longValue() == 0L){
			   localBodyList = constituencyDAO.getWardsInLocalElectionBody(localBodyIds);
		   }
		   if(enrollmentId != null && enrollmentId.longValue() == 0L) // enrollment year id is zero 
			     localBodyList =boothDAO.getBoothsForMuncipalitys(localBodyIds,constituencyId);
		     else
				 localBodyList = tdpCommitteeDAO.getTdpCommitteeWardsInLocalElectionBody(localBodyIds, constituencyId);
        	for(Object[] localBody:localBodyList){
        		vo = new LocationWiseBoothDetailsVO();
	        	vo.setLocationId(Long.valueOf("2"+commonMethodsUtilService.getLongValueForObject(localBody[0])));
	        	vo.setLocationName(localBody[1].toString()+" ("+localBody[2].toString()+")");
	        	locationsList.add(vo);
        	}
        }
	  
	return locationsList;
}

public List<IdNameVO> getConstituenciesByActivityId(Long activityId)
{
	List<IdNameVO> idNameVoList = new ArrayList<IdNameVO>();
	try {
		List<Object[]> constLsit = activityLocationInfoDAO.getCnstenciesByActivityId(activityId);
		if(constLsit != null && constLsit.size()>0)
		{
			for (Object[] objects : constLsit) {
					IdNameVO vo = new IdNameVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					idNameVoList.add(vo);
				}
			}
		} catch (Exception e) {
		LOG.error("Exception Occured in getConstituenciesByActivityId() method, Exception - ",e);
		}
	return idNameVoList;
}
public List<IdNameVO> getMandalsByConstituencyId(Long constituencyId,Long activityScopeId)
{
	List<IdNameVO> idNameVoList = new ArrayList<IdNameVO>();
	try {
		List<Object[]> mandalsLsit = activityLocationInfoDAO.getMandalsByConstituency(constituencyId,activityScopeId);
		if(mandalsLsit != null && mandalsLsit.size()>0)
		{
			for (Object[] objects : mandalsLsit) {
					IdNameVO vo = new IdNameVO();
					vo.setId(Long.valueOf("2"+commonMethodsUtilService.getLongValueForObject(objects[0])));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1])+" Mandal ");
					idNameVoList.add(vo);
				}
		}
		List<Object[]> munciplitiesList = activityLocationInfoDAO.getMuncipalitiesByConstituency(constituencyId,activityScopeId);
		if(munciplitiesList != null && munciplitiesList.size()>0)
		{
			for (Object[] objects : munciplitiesList) {
					IdNameVO vo = new IdNameVO();
					vo.setId(Long.valueOf("1"+commonMethodsUtilService.getLongValueForObject(objects[0])));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1])+" Muncipality ");
					idNameVoList.add(vo);
				}
		}
		
		} catch (Exception e) {
		LOG.error("Exception Occured in getMandalsByConstituencyId() method, Exception - ",e);
		}
	return idNameVoList;
}
public List<IdNameVO> getPanchayatBymandalId(Long mandalId,Long activityScopeId)
{
	List<IdNameVO> idNameVoList = new ArrayList<IdNameVO>();
	try {
		if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
			List<Object[]> panchayatsLsit = activityLocationInfoDAO.getPanchayatByTehsil(Long.valueOf(mandalId.toString().substring(1)),activityScopeId);
				if(panchayatsLsit != null && panchayatsLsit.size()>0)
				{
					for (Object[] objects : panchayatsLsit) {
							IdNameVO vo = new IdNameVO();
							vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
							vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
							idNameVoList.add(vo);
						}
					}
			}
		if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
			List<Object[]> wardsLsit = activityLocationInfoDAO.getWardsByMun(Long.valueOf(mandalId.toString().substring(1)),activityScopeId);
				if(wardsLsit != null && wardsLsit.size()>0)
				{
					for (Object[] objects : wardsLsit) {
							IdNameVO vo = new IdNameVO();
							vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
							vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
							idNameVoList.add(vo);
						}
					}
			}
		} catch (Exception e) {
		LOG.error("Exception Occured in getPanchayatBymandalId() method, Exception - ",e);
		}
	return idNameVoList;
}
public List<LocationWiseBoothDetailsVO> getActivityLocationDetails(Long levelId,Long locationId,Long activityScopeId,String searchType,String checkedValue){
	List<LocationWiseBoothDetailsVO> returnList = new ArrayList<LocationWiseBoothDetailsVO>();
	try{
		if(searchType.trim().equalsIgnoreCase("Mandal") ||	searchType.trim().equalsIgnoreCase("Muncipality") ){
			locationId = Long.valueOf(locationId.toString().substring(1));
		}
		
		List<Long> villageIdsList = new ArrayList<Long>();
		List<Long> wardIdsList = new ArrayList<Long>();
		List<Long> constituencyIdsList = new ArrayList<Long>();
		List<Long> districtIdsList = new ArrayList<Long>();
		List<Long> mandalIdsList = new ArrayList<Long>();
		List<Long> munciIdsList = new ArrayList<Long>();
		List<Object[]> lctWiseList = activityLocationInfoDAO.getLocationWise(levelId, locationId, activityScopeId, searchType,checkedValue);
		if(lctWiseList != null && lctWiseList.size() > 0){
				if(levelId.longValue() == 3l || levelId.longValue() == 5l){
					for (Object[] objects : lctWiseList) {
						LocationWiseBoothDetailsVO vo = new LocationWiseBoothDetailsVO();
							vo.setDistrictId(commonMethodsUtilService.getLongValueForObject(objects[0]));
							vo.setDistrictName(commonMethodsUtilService.getStringValueForObject(objects[1]));
							districtIdsList.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
							vo.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objects[2]));
							vo.setConstituencyName(commonMethodsUtilService.getStringValueForObject(objects[3]));
							constituencyIdsList.add(commonMethodsUtilService.getLongValueForObject(objects[2]));
							vo.setPlanedDate(commonMethodsUtilService.getStringValueForObject(objects[8]));
							vo.setConductedDate(commonMethodsUtilService.getStringValueForObject(objects[9]));
							vo.setIvrStatus(commonMethodsUtilService.getStringValueForObject(objects[10]));
							vo.setActivityLocatInfoId(commonMethodsUtilService.getLongValueForObject(objects[11]));
							vo.setStatus(commonMethodsUtilService.getStringValueForObject(objects[12]));
							vo.setTableName("LocationInfo");
							returnList.add(vo);
					}
				}else if(levelId.longValue() == 1l || levelId.longValue() == 2l){
					for (Object[] objects : lctWiseList) {
						LocationWiseBoothDetailsVO vo = new LocationWiseBoothDetailsVO();
						vo.setStatus(commonMethodsUtilService.getStringValueForObject(objects[12]));
						if(commonMethodsUtilService.getLongValueForObject(objects[0]) != null && commonMethodsUtilService.getLongValueForObject(objects[0]) != 0){
							vo.setMandalId(Long.valueOf("2"+commonMethodsUtilService.getLongValueForObject(objects[0])));
							vo.setMandalName(commonMethodsUtilService.getStringValueForObject(objects[1])+" Mandal ");
							mandalIdsList.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
						if(objects[5] != null){
							vo.setVillageId(Long.valueOf("1"+commonMethodsUtilService.getLongValueForObject(objects[4])));
							vo.setVillageName(commonMethodsUtilService.getStringValueForObject(objects[5])+" Panchayat ");
							villageIdsList.add(commonMethodsUtilService.getLongValueForObject(objects[4]));
						  }
						}else if(commonMethodsUtilService.getLongValueForObject(objects[2]) != null && commonMethodsUtilService.getLongValueForObject(objects[2]) != 0){
							vo.setMandalId(Long.valueOf("1"+commonMethodsUtilService.getLongValueForObject(objects[2])));
							vo.setMandalName(commonMethodsUtilService.getStringValueForObject(objects[3])+ " Muncipality ");
							vo.setMandalName(vo.getMandalName()+(objects[7] != null ? "("+objects[7].toString()+")":""));
							munciIdsList.add(commonMethodsUtilService.getLongValueForObject(objects[2]));
							vo.setVillageId(Long.valueOf("2"+commonMethodsUtilService.getLongValueForObject(objects[6])));
							vo.setVillageName(commonMethodsUtilService.getStringValueForObject(objects[7]));
							wardIdsList.add(commonMethodsUtilService.getLongValueForObject(objects[6]));
							
						}
							vo.setPlanedDate(commonMethodsUtilService.getStringValueForObject(objects[8]));
							vo.setConductedDate(commonMethodsUtilService.getStringValueForObject(objects[9]));
							vo.setIvrStatus(commonMethodsUtilService.getStringValueForObject(objects[10]));
							vo.setActivityLocatInfoId(commonMethodsUtilService.getLongValueForObject(objects[11]));
							vo.setTableName("LocationInfo");
							returnList.add(vo);
				}
			}
		}else{
			List<Object[]> lctList = activityConductedInfoDAO.getActivityLocationDetails(levelId, locationId, activityScopeId, searchType,checkedValue);
			if(lctList != null && lctList.size() > 0){
					if(levelId.longValue() == 3l || levelId.longValue() == 5l){
						for (Object[] objects : lctList) {
							LocationWiseBoothDetailsVO vo = new LocationWiseBoothDetailsVO();
								vo.setDistrictId(commonMethodsUtilService.getLongValueForObject(objects[0]));
								vo.setDistrictName(commonMethodsUtilService.getStringValueForObject(objects[1]));
								districtIdsList.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
								vo.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objects[2]));
								vo.setConstituencyName(commonMethodsUtilService.getStringValueForObject(objects[3]));
								constituencyIdsList.add(commonMethodsUtilService.getLongValueForObject(objects[2]));
								vo.setPlanedDate(commonMethodsUtilService.getStringValueForObject(objects[8]));
								vo.setConductedDate(commonMethodsUtilService.getStringValueForObject(objects[9]));
								vo.setIvrStatus(commonMethodsUtilService.getStringValueForObject(objects[10]));
								vo.setActivityLocatInfoId(commonMethodsUtilService.getLongValueForObject(objects[11]));
								vo.setTableName("ConductedInfo");
								vo.setStatus(commonMethodsUtilService.getStringValueForObject(objects[12]));
								returnList.add(vo);
						}
					}else if(levelId.longValue() == 1l || levelId.longValue() == 2l){
						for (Object[] objects : lctList) {
							LocationWiseBoothDetailsVO vo = new LocationWiseBoothDetailsVO();
							vo.setStatus(commonMethodsUtilService.getStringValueForObject(objects[12]));
							if(commonMethodsUtilService.getLongValueForObject(objects[0]) != null && commonMethodsUtilService.getLongValueForObject(objects[0]) != 0){
								vo.setMandalId(commonMethodsUtilService.getLongValueForObject(objects[0]));
								vo.setMandalName(commonMethodsUtilService.getStringValueForObject(objects[1])+" Mandal ");
								mandalIdsList.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
								vo.setVillageId(Long.valueOf("1"+commonMethodsUtilService.getLongValueForObject(objects[4])));
								vo.setVillageName(commonMethodsUtilService.getStringValueForObject(objects[5])+"Panchayat");
								villageIdsList.add(commonMethodsUtilService.getLongValueForObject(objects[4]));
							}else if(commonMethodsUtilService.getLongValueForObject(objects[2]) != null && commonMethodsUtilService.getLongValueForObject(objects[2]) != 0){
								vo.setMandalId(commonMethodsUtilService.getLongValueForObject(objects[2]));
								vo.setMandalName(commonMethodsUtilService.getStringValueForObject(objects[3])+ " Muncipality ");
								munciIdsList.add(commonMethodsUtilService.getLongValueForObject(objects[2]));
								vo.setVillageId(Long.valueOf("2"+commonMethodsUtilService.getLongValueForObject(objects[6])));
								vo.setVillageName(commonMethodsUtilService.getStringValueForObject(objects[7]));
								wardIdsList.add(commonMethodsUtilService.getLongValueForObject(objects[6]));
							}
								vo.setPlanedDate(commonMethodsUtilService.getStringValueForObject(objects[8]));
								vo.setConductedDate(commonMethodsUtilService.getStringValueForObject(objects[9]));
								vo.setIvrStatus(commonMethodsUtilService.getStringValueForObject(objects[10]));
								vo.setActivityLocatInfoId(commonMethodsUtilService.getLongValueForObject(objects[11]));
								vo.setTableName("ConductedInfo");
								returnList.add(vo);
					}
				}
			}
		}
		
		
		Map<Long,Long> docmntListMap = new LinkedHashMap<Long, Long>();
		List<Object[]> docListCnt = null;
			if(levelId.longValue() == 3l || levelId.longValue() == 5l){
				if(levelId.longValue() == 3l){
					docListCnt = activityInfoDocumentDAO.getDocumentCuntByScopeId(activityScopeId,districtIdsList,null);
				}if(levelId.longValue() == 5l){
					docListCnt = activityInfoDocumentDAO.getDocumentCuntByScopeId(activityScopeId,null,constituencyIdsList);
				}
			}else if(levelId.longValue() == 1l || levelId.longValue() == 2l){
				if(levelId.longValue() == 1l)
					docListCnt = activityInfoDocumentDAO.getDocumentsCuntByScopeId(activityScopeId,villageIdsList,wardIdsList);
				if(levelId.longValue() == 2l)
					docListCnt = activityInfoDocumentDAO.getDocumentsCuntForScopeId(activityScopeId,mandalIdsList,munciIdsList);
			}
		
		if(docListCnt != null && docListCnt.size() > 0){
			for (Object[] objects : docListCnt) {
				if(commonMethodsUtilService.getLongValueForObject(objects[0]) != null && commonMethodsUtilService.getLongValueForObject(objects[0]).longValue() > 0L && commonMethodsUtilService.getLongValueForObject(objects[0]).longValue() != 0L){
					docmntListMap.put(commonMethodsUtilService.getLongValueForObject(objects[0]), commonMethodsUtilService.getLongValueForObject(objects[1]));
				}else if(commonMethodsUtilService.getLongValueForObject(objects[2]) != null && commonMethodsUtilService.getLongValueForObject(objects[2]).longValue() > 0L && commonMethodsUtilService.getLongValueForObject(objects[2]).longValue() != 0L){
					docmntListMap.put(commonMethodsUtilService.getLongValueForObject(objects[2]), commonMethodsUtilService.getLongValueForObject(objects[1]));
				}
			}
		}
		if(returnList != null && !returnList.isEmpty()){
			for (LocationWiseBoothDetailsVO vo : returnList) {
				Long locationInfoId = vo.getActivityLocatInfoId();
					vo.setCount(docmntListMap.get(locationInfoId));
			}
		}

		//srinu
		List<Object[]> scopeValues = requiredAttributeDAO.getAttributesTypes(activityScopeId);
		//List<LocationWiseBoothDetailsVO> scopeValuesVoList = new ArrayList<LocationWiseBoothDetailsVO>(0);
	if(scopeValues != null && !scopeValues.isEmpty()){
		for (Object[] obj : scopeValues) {
		KeyValueVO vo = new KeyValueVO();

		vo.setId(Long.valueOf(obj[0] !=null ? obj[0].toString() :"0"));
		vo.setName(obj[1] !=null ? obj[1].toString() :"");
		if(commonMethodsUtilService.isListOrSetValid(returnList) && returnList.get(0) != null)
			returnList.get(0).getSubList().add(vo);
	}
}
			
	}catch(Exception e){
		LOG.error("Exception Occured in getActivityLocationDetails() method, Exception - ",e);
	}
	return returnList;
}
public List<IdNameVO> getDistrictsByActivityId(Long activityId)
{
	List<IdNameVO> idNameVoList = new ArrayList<IdNameVO>();
	try {
		List<Object[]> constLsit = activityLocationInfoDAO.getDistrictsByActivityId(activityId);
		if(constLsit != null && constLsit.size()>0)
		{
			for (Object[] objects : constLsit) {
					IdNameVO vo = new IdNameVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					idNameVoList.add(vo);
				}
			}
		} catch (Exception e) {
		LOG.error("Exception Occured in getDistrictsByActivityId() method, Exception - ",e);
		}
	return idNameVoList;
}
public String saveActivityLocationDetails(final ActivityVO activityVO,final Long userId){
	String status = "";
	try{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//ActivityLocationInfo activityLocationInfo = null;
		//ActivityConductedInfo activityConductedInfo = null;
		String typeIdsList = activityVO.getTypes();
		String[] typeIds = typeIdsList.split(",");
		List<Long> typeIds1 = new ArrayList<Long>(0);
		for(String typeId : typeIds){
			typeIds1.add(Long.valueOf(typeId));
		}
		 if(activityVO != null){
			 Long activityScopeId = activityVO.getActivityLevelId();
			 List<ActivityVO> activityList = activityVO.getActivityVoList();
			 if(activityList != null && activityList.size() > 0){
				 for (ActivityVO activityvo : activityList) {
					 if(activityvo != null && activityvo.getIsChecked() != null && activityvo.getIsChecked().trim().length()>0)
					 {
						 if(activityvo.getTable().trim().equalsIgnoreCase("locationInfo")){
							 ActivityLocationInfo activityLocationInfo = activityLocationInfoDAO.isAlreadyAvailableLocationDtls(activityvo.getActivityLocationInfoId());
							 if(activityLocationInfo != null){
								 //activityLocationInfo = new ActivityLocationInfo(); 
								 
								 if(activityVO.getStatus() != null && activityVO.getStatus().equalsIgnoreCase("Conducted")){//overall vo status
									 activityLocationInfo.setUpdatedStatus("Updated");
									 if(typeIds1.contains(6l)){
										 
										 	if(activityVO.getSelectedCnductedDate() != null && activityVO.getSelectedCnductedDate().trim().length() > 0)
										 		activityLocationInfo.setConductedDate(sdf.parse(activityVO.getSelectedCnductedDate() != null ? activityVO.getSelectedCnductedDate().toString():""));
											else
												activityLocationInfo.setConductedDate(sdf.parse(activityvo.getConductedDate() != null ? activityvo.getConductedDate().toString():""));
										 	
									 }else if(typeIds1.contains(5l)){
										 if(activityvo.getPlannedDate() != null && activityvo.getPlannedDate().trim().length() > 0)
												activityLocationInfo.setPlannedDate(sdf.parse(activityvo.getPlannedDate() != null ? activityvo.getPlannedDate().toString():""));
											else
												activityLocationInfo.setPlannedDate(sdf.parse(activityVO.getSelectedPlanedDate() != null ? activityVO.getSelectedPlanedDate().toString():""));
									}else if(typeIds1.contains(7l)){
										 if(activityvo.getIvrStatus() != null)
												activityLocationInfo.setIvrStatus(activityvo.getIvrStatus() != null ? activityvo.getIvrStatus().toString():"");
											else
												activityLocationInfo.setIvrStatus(activityVO.getActalIvrStatus() != null ? activityVO.getActalIvrStatus().toString():"");
									}
									/*else if(activityVO.getAttributeId().longValue() == 0l){
										 if(activityvo.getConductedDate() != null && activityvo.getConductedDate().trim().length() > 0)
												activityLocationInfo.setConductedDate(sdf.parse(activityvo.getConductedDate() != null ? activityvo.getConductedDate().toString():""));
											else
												activityLocationInfo.setConductedDate(sdf.parse(activityVO.getSelectedCnductedDate() != null ? activityVO.getSelectedCnductedDate().toString():""));
										 if(activityvo.getIvrStatus() != null)
												activityLocationInfo.setIvrStatus(activityvo.getIvrStatus() != null ? activityvo.getIvrStatus().toString():"");
											else
												activityLocationInfo.setIvrStatus(activityVO.getActalIvrStatus() != null ? activityVO.getActalIvrStatus().toString():"");
									 }*/
							 	}
								 else  if(activityVO.getStatus() != null && activityVO.getStatus().equalsIgnoreCase("Not Conducted") ){//main not conducted
									 activityLocationInfo.setConductedDate(null);
									 try {
										 List<Long> activityInfoIdList = new ArrayList<Long>(0);
										 activityInfoIdList.add(activityvo.getActivityLocationInfoId());
										 activityInfoDocumentDAO.deleteEventUploadFilebyActivityInfoId( activityInfoIdList);
										 
									} catch (Exception e) {
										e.printStackTrace();
									}
								 }
								 else  if(activityvo.getStatus() != null && activityvo.getStatus().equalsIgnoreCase("Conducted")  ){//individual vo status
									 activityLocationInfo.setUpdatedStatus("Updated");
									 if(typeIds1.contains(6l)){
										 	if(activityVO.getSelectedCnductedDate() != null && activityVO.getSelectedCnductedDate().trim().length() > 0)
										 		activityLocationInfo.setConductedDate(sdf.parse(activityVO.getSelectedCnductedDate() != null ? activityVO.getSelectedCnductedDate().toString():""));
											else
												activityLocationInfo.setConductedDate(sdf.parse(activityvo.getConductedDate() != null ? activityvo.getConductedDate().toString():""));
										 	
									 }else if(typeIds1.contains(5l)){
										 if(activityvo.getPlannedDate() != null && activityvo.getPlannedDate().trim().length() > 0)
												activityLocationInfo.setPlannedDate(sdf.parse(activityvo.getPlannedDate() != null ? activityvo.getPlannedDate().toString():""));
											else
												activityLocationInfo.setPlannedDate(sdf.parse(activityVO.getSelectedPlanedDate() != null ? activityVO.getSelectedPlanedDate().toString():""));
									}else if(typeIds1.contains(7l)){
										 if(activityvo.getIvrStatus() != null)
												activityLocationInfo.setIvrStatus(activityvo.getIvrStatus() != null ? activityvo.getIvrStatus().toString():"");
											else
												activityLocationInfo.setIvrStatus(activityVO.getActalIvrStatus() != null ? activityVO.getActalIvrStatus().toString():"");
									}
									/*else if(activityVO.getAttributeId().longValue() == 0l){
										 if(activityvo.getConductedDate() != null && activityvo.getConductedDate().trim().length() > 0)
												activityLocationInfo.setConductedDate(sdf.parse(activityvo.getConductedDate() != null ? activityvo.getConductedDate().toString():""));
											else
												activityLocationInfo.setConductedDate(sdf.parse(activityVO.getSelectedCnductedDate() != null ? activityVO.getSelectedCnductedDate().toString():""));
										 if(activityvo.getIvrStatus() != null)
												activityLocationInfo.setIvrStatus(activityvo.getIvrStatus() != null ? activityvo.getIvrStatus().toString():"");
											else
												activityLocationInfo.setIvrStatus(activityVO.getActalIvrStatus() != null ? activityVO.getActalIvrStatus().toString():"");
									 }*/
							 	}
								else  if(activityvo.getStatus() != null && activityvo.getStatus().equalsIgnoreCase("Not Conducted") ){
									 activityLocationInfo.setConductedDate(null);
									 activityLocationInfo.setUpdatedStatus("Updated");
									 try {
										 List<Long> activityInfoIdList = new ArrayList<Long>(0);
										 activityInfoIdList.add(activityvo.getActivityLocationInfoId());
										 activityInfoDocumentDAO.deleteEventUploadFilebyActivityInfoId( activityInfoIdList);
									} catch (Exception e) {
										e.printStackTrace();
									}
								 }
								else  if(activityvo.getStatus() != null && activityvo.getStatus().equalsIgnoreCase("Not Updated") ){
									 activityLocationInfo.setConductedDate(null);
									 activityLocationInfo.setUpdatedStatus("Not Updated");
									 try {
										 List<Long> activityInfoIdList = new ArrayList<Long>(0);
										 activityInfoIdList.add(activityvo.getActivityLocationInfoId());
										 activityInfoDocumentDAO.deleteEventUploadFilebyActivityInfoId( activityInfoIdList);
										 
									} catch (Exception e) {
										e.printStackTrace();
									}
								 }
								activityLocationInfo.setUpdatedBy(userId);
								activityLocationInfo.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								activityLocationInfoDAO.save(activityLocationInfo);
								status = "success";
						 }
					 }else if(activityvo.getTable().trim().equalsIgnoreCase("conductedInfo")){
						 ActivityConductedInfo activityConductedInfo = activityConductedInfoDAO.isAlreadyAvailableLocationDtls(activityvo.getActivityLocationInfoId());
						 if(activityConductedInfo != null ){
							 
							 if(activityVO.getStatus() != null && activityVO.getStatus().equalsIgnoreCase("Conducted")){
								 activityConductedInfo.setUpdatedStatus("Updated");
								 if(typeIds1.contains(6l)){
									 activityConductedInfo = new ActivityConductedInfo(); 
									if(activityVO.getSelectedCnductedDate() != null && activityVO.getSelectedCnductedDate().trim().length() > 0)
										activityConductedInfo.setConductedDate(sdf.parse(activityVO.getSelectedCnductedDate() != null ? activityVO.getSelectedCnductedDate().toString():""));
									else
										activityConductedInfo.setConductedDate(sdf.parse(activityvo.getConductedDate() != null ? activityvo.getConductedDate().toString():""));
								 
								 }else if(typeIds1.contains(5l)){
									 if(activityvo.getPlannedDate() != null && activityvo.getPlannedDate().trim().length() > 0)
										 activityConductedInfo.setPlannedDate(sdf.parse(activityvo.getPlannedDate() != null ? activityvo.getPlannedDate().toString():""));
										else
											activityConductedInfo.setPlannedDate(sdf.parse(activityVO.getSelectedPlanedDate() != null ? activityVO.getSelectedPlanedDate().toString():""));
								}else if(typeIds1.contains(7l)){
									 if(activityvo.getIvrStatus() != null)
										 activityConductedInfo.setIvrStatus(activityvo.getIvrStatus() != null ? activityvo.getIvrStatus().toString():"");
										else
											activityConductedInfo.setIvrStatus(activityVO.getActalIvrStatus() != null ? activityVO.getActalIvrStatus().toString():"");
								 }
								 /*else if(activityVO.getAttributeId().longValue() == 0l){
									 if(activityvo.getConductedDate() != null && activityvo.getConductedDate().trim().length() > 0)
										 activityConductedInfo.setConductedDate(sdf.parse(activityvo.getConductedDate() != null ? activityvo.getConductedDate().toString():""));
										else
											activityConductedInfo.setConductedDate(sdf.parse(activityVO.getSelectedCnductedDate() != null ? activityVO.getSelectedCnductedDate().toString():""));
									 if(activityvo.getIvrStatus() != null)
										 activityConductedInfo.setIvrStatus(activityvo.getIvrStatus() != null ? activityvo.getIvrStatus().toString():"");
										else
											activityConductedInfo.setIvrStatus(activityVO.getActalIvrStatus() != null ? activityVO.getActalIvrStatus().toString():"");
								 }*/
							 } 
							 else  if(activityVO.getStatus() != null && activityVO.getStatus().equalsIgnoreCase("Not Conducted")){
								 activityConductedInfo.setConductedDate(null);
								 activityConductedInfo.setUpdatedStatus("Updated");
								 try {
									 List<Long> activityInfoIdList = new ArrayList<Long>(0);
									 activityInfoIdList.add(activityvo.getActivityLocationInfoId());
									 activityInfoDocumentDAO.deleteEventUploadFilebyActivityConductedInfoId(activityInfoIdList);
								} catch (Exception e) {
									e.printStackTrace();
								}
							 }
							 else  if(activityVO.getStatus() != null && activityVO.getStatus().equalsIgnoreCase("Not Updated") ){
								  activityConductedInfo.setConductedDate(null);
								  activityConductedInfo.setUpdatedStatus("Not Updated");
								 try {
									 List<Long> activityInfoIdList = new ArrayList<Long>(0);
									 activityInfoIdList.add(activityvo.getActivityLocationInfoId());
									 activityInfoDocumentDAO.deleteEventUploadFilebyActivityConductedInfoId(activityInfoIdList);
								} catch (Exception e) {
									e.printStackTrace();
								}
							 }
							 else if( activityvo.getStatus() != null && activityvo.getStatus().equalsIgnoreCase("Conducted") ){
								 activityConductedInfo.setUpdatedStatus("Updated");	
								 if(typeIds1.contains(6l)){
										 activityConductedInfo = new ActivityConductedInfo(); 
										if(activityVO.getSelectedCnductedDate() != null && activityVO.getSelectedCnductedDate().trim().length() > 0)
											activityConductedInfo.setConductedDate(sdf.parse(activityVO.getSelectedCnductedDate() != null ? activityVO.getSelectedCnductedDate().toString():""));
										else
											activityConductedInfo.setConductedDate(sdf.parse(activityvo.getConductedDate() != null ? activityvo.getConductedDate().toString():""));
									 
									 }else if(typeIds1.contains(5l)){
										 if(activityvo.getPlannedDate() != null && activityvo.getPlannedDate().trim().length() > 0)
											 activityConductedInfo.setPlannedDate(sdf.parse(activityvo.getPlannedDate() != null ? activityvo.getPlannedDate().toString():""));
											else
												activityConductedInfo.setPlannedDate(sdf.parse(activityVO.getSelectedPlanedDate() != null ? activityVO.getSelectedPlanedDate().toString():""));
									}else if(typeIds1.contains(7l)){
										 if(activityvo.getIvrStatus() != null)
											 activityConductedInfo.setIvrStatus(activityvo.getIvrStatus() != null ? activityvo.getIvrStatus().toString():"");
											else
												activityConductedInfo.setIvrStatus(activityVO.getActalIvrStatus() != null ? activityVO.getActalIvrStatus().toString():"");
									 }
									 /*else if(activityVO.getAttributeId().longValue() == 0l){
										 if(activityvo.getConductedDate() != null && activityvo.getConductedDate().trim().length() > 0)
											 activityConductedInfo.setConductedDate(sdf.parse(activityvo.getConductedDate() != null ? activityvo.getConductedDate().toString():""));
											else
												activityConductedInfo.setConductedDate(sdf.parse(activityVO.getSelectedCnductedDate() != null ? activityVO.getSelectedCnductedDate().toString():""));
										 if(activityvo.getIvrStatus() != null)
											 activityConductedInfo.setIvrStatus(activityvo.getIvrStatus() != null ? activityvo.getIvrStatus().toString():"");
											else
												activityConductedInfo.setIvrStatus(activityVO.getActalIvrStatus() != null ? activityVO.getActalIvrStatus().toString():"");
									 }*/
								 } 
								else  if(activityvo.getStatus() != null && activityvo.getStatus().equalsIgnoreCase("Not Conducted") ){
									activityConductedInfo.setUpdatedStatus("Updated");
									activityConductedInfo.setConductedDate(null);
									 try {
										 List<Long> activityInfoIdList = new ArrayList<Long>(0);
										 activityInfoIdList.add(activityvo.getActivityLocationInfoId());
										 activityInfoDocumentDAO.deleteEventUploadFilebyActivityConductedInfoId(activityInfoIdList);
									} catch (Exception e) {
										e.printStackTrace();
									}
								 }
								else  if(activityvo.getStatus() != null && activityvo.getStatus().equalsIgnoreCase("Not Updated") ){
									  activityConductedInfo.setConductedDate(null);
									  activityConductedInfo.setUpdatedStatus("Not Updated");
									 try {
										 List<Long> activityInfoIdList = new ArrayList<Long>(0);
										 activityInfoIdList.add(activityvo.getActivityLocationInfoId());
										 activityInfoDocumentDAO.deleteEventUploadFilebyActivityConductedInfoId(activityInfoIdList);
									} catch (Exception e) {
										e.printStackTrace();
									}
								 }
							 
							
							 activityConductedInfoDAO.save(activityConductedInfo);
							 status = "success";
						 } 
					 }
				 }
			 }
		 }
		}
	}catch(Exception e){
		status = "failure";
		LOG.error("Exception Occured in saveActivityLocationDetails() method, Exception - ",e);
	}
	return status;
}

public List<ActivityVO> asemblyConstWiseActivitiesCount(Long activityScopeId,Long activityLevelId,String accessType,Long accessValue,Long stateId,Long userId){
	List<ActivityVO> finalList = null;
	
	try{    
		 //get Constituencies.
		 List<Long> constIds=new ArrayList<Long>(0);
		 List<IdNameVO> constiList =getConstituenciesByActivityId(activityScopeId);
		 if(constiList != null && constiList.size() > 0){
			 finalList = new ArrayList<ActivityVO>(0);
			 for(IdNameVO vo : constiList){
				 ActivityVO conVO = new ActivityVO();
				 conVO.setId(vo.getId());
				 conVO.setName(vo.getName());
				 conVO.setConductedCount(0l);
				 conVO.setNonConductedCount(0l);
				 conVO.setNotUpdatedCount(0l);
				 finalList.add(conVO);
				 constIds.add(conVO.getId());
			 }
			 
			//ConductedCount
			 List<Object[]> conductedlist = activityLocationInfoDAO.getConductedForAssemblyConstWise(activityScopeId,constIds);
			 if(conductedlist != null && conductedlist.size()>0){
				 for(Object[] obj:conductedlist){
					 Long constId = commonMethodsUtilService.getLongValueForObject(obj[0]);
					 ActivityVO convo = getMatchedConstVO(constId,finalList);
					 if(convo != null){
						 convo.setConductedCount(commonMethodsUtilService.getLongValueForObject(obj[1]));
					 }
				 }
			 }
			 
			 //NotConductedCount
			List<Object[]> NotConduCounts = activityLocationInfoDAO.getNotConductedForAssemblyConstWise(activityScopeId, constIds);
			 if(NotConduCounts != null && NotConduCounts.size()>0){
				 for(Object[] obj : NotConduCounts){
					 Long constId = commonMethodsUtilService.getLongValueForObject(obj[0]);
					 ActivityVO convo=getMatchedConstVO(constId,finalList);
					 if(convo != null){
						convo.setNonConductedCount(commonMethodsUtilService.getLongValueForObject(obj[1]));
					 }
				 }
			 }
			 
			 //NotUpdatedCount
			 List<Object[]> notUpdatedlist=activityLocationInfoDAO.getNotUpdatedCuntForAssemblyConstWise(activityScopeId, constIds);
			 if(notUpdatedlist != null && notUpdatedlist.size()>0){
				 for(Object[] obj : notUpdatedlist){
					 Long constId = commonMethodsUtilService.getLongValueForObject(obj[0]);
					 ActivityVO convo = getMatchedConstVO(constId,finalList);
					 if(convo != null){
						 convo.setNotUpdatedCount(commonMethodsUtilService.getLongValueForObject(obj[1]));
					 }
				 }
			 }
			
		 }
	}catch(Exception e){
		LOG.error("Exception raised in asemblyConstWiseActivitiesCount", e);
	}
	return finalList;
}

public List<ActivityVO> getDistrictWiseActivitiesCount(Long activityScopeId,Long activityLevelId,String accessType,Long accessValue,Long stateId,Long userId){
	List<ActivityVO> finalList = null;
	try{
		 
		List<Long> distIds = new ArrayList<Long>(0);
		List<IdNameVO> districtList = getDistrictsByActivityId(activityScopeId);
		 if(districtList != null && districtList.size()>0){
			 
			 finalList = new ArrayList<ActivityVO>(0);
			 for(IdNameVO vo:districtList){
				 ActivityVO distVO = new ActivityVO();
				 distVO.setId(vo.getId());
				 distVO.setName(vo.getName());
				 distVO.setConductedCount(0l);
				 distVO.setNonConductedCount(0l);
				 distVO.setNotUpdatedCount(0l);
				 finalList.add(distVO);
				 distIds.add(distVO.getId());
			 }
			 
			
			 List<Object[]> conductedlist = activityLocationInfoDAO.getConductedCountForDistrict(activityScopeId,distIds);
			 if(conductedlist != null && conductedlist.size()>0){
				 for(Object[] obj:conductedlist){
					 Long distId = commonMethodsUtilService.getLongValueForObject(obj[0]);
					 ActivityVO convo = getMatchedConstVO(distId,finalList);
					 if(convo != null){
						 convo.setConductedCount(commonMethodsUtilService.getLongValueForObject(obj[1]));
					 }
				 }
			 }
			 
			 //NotConductedCount
			List<Object[]> NotConduCounts = activityLocationInfoDAO.getNotConductedCountForDistrict(activityScopeId, distIds);
			 if(NotConduCounts != null && NotConduCounts.size()>0){
				 for(Object[] obj : NotConduCounts){
					 Long distId = commonMethodsUtilService.getLongValueForObject(obj[0]);
					 ActivityVO convo=getMatchedConstVO(distId,finalList);
					 if(convo != null){
						convo.setNonConductedCount(commonMethodsUtilService.getLongValueForObject(obj[1]));
					 }
				 }
			 }
			 
			 //NotUpdatedCount
			 List<Object[]> notUpdatedlist=activityLocationInfoDAO.getNotUpdatedForDistrict(activityScopeId, distIds);
			 if(notUpdatedlist != null && notUpdatedlist.size()>0){
				 for(Object[] obj : notUpdatedlist){
					 Long distId = commonMethodsUtilService.getLongValueForObject(obj[0]);
					 ActivityVO convo = getMatchedConstVO(distId,finalList);
					 if(convo != null){
						 convo.setNotUpdatedCount(commonMethodsUtilService.getLongValueForObject(obj[1]));
					 }
				 }
			 }
		 }
	}catch(Exception e){
		LOG.error("Exception raised in getDistrictWiseActivitiesCount", e);
	}
	return finalList;
}

public String updateCommitteeMemberDesignationByCadreId(final Long tdpCadreId,final Long userId){
	String status = null;
	try {
		status = (String)transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus arg0) {
				
				List<Object[]> list = tdpCommitteeMemberDAO.getActiveMemberDetailsByCadreId(tdpCadreId);
				if(list != null && !list.isEmpty()){
					Object[] obj = list.get(0);
					Long tdpCommitteeMemberId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long tdpCommitteeRoleId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long tdpCommitteeId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					
					TdpCommitteeMember tdpCommitteeMember = tdpCommitteeMemberDAO.get(tdpCommitteeMemberId);
					if(tdpCommitteeMember != null){
						
						TdpCommitteeMemberHistory tdpCommitteeMemberHistory = new TdpCommitteeMemberHistory();
						tdpCommitteeMemberHistory.setTdpCommitteeMemberId(tdpCommitteeMember.getTdpCommitteeMemberId());
						tdpCommitteeMemberHistory.setTdpCommitteeRoleId(tdpCommitteeMember.getTdpCommitteeRoleId());
						tdpCommitteeMemberHistory.setTdpCadreId(tdpCommitteeMember.getTdpCadreId());
						tdpCommitteeMemberHistory.setStartDate(tdpCommitteeMember.getStartDate());
						tdpCommitteeMemberHistory.setEndDate(tdpCommitteeMember.getEndDate());
						tdpCommitteeMemberHistory.setIsActive(tdpCommitteeMember.getIsActive());
						tdpCommitteeMemberHistory.setTdpCommitteeEnrollmentId(tdpCommitteeMember.getTdpCommitteeEnrollmentId());
						tdpCommitteeMemberHistory.setInsertedUserId(tdpCommitteeMember.getInsertedUserId());
						tdpCommitteeMemberHistory.setUpdatedUserId(tdpCommitteeMember.getUpdatedUserId());
						tdpCommitteeMemberHistory.setInsertedTime(tdpCommitteeMember.getInsertedTime());
						tdpCommitteeMemberHistory.setUpdatedTime(tdpCommitteeMember.getUpdatedTime());
						tdpCommitteeMemberHistory.setHistoryInsertedTime(dateUtilService.getCurrentDateAndTime());
						tdpCommitteeMemberHistory.setUserId(userId);
						
						tdpCommitteeMemberHistory = tdpCommitteeMemberHistoryDAO.save(tdpCommitteeMemberHistory);
						
						tdpCommitteeMember.setIsActive("N");
						tdpCommitteeMember.setUpdatedUserId(userId);
						tdpCommitteeMember.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						
						tdpCommitteeMember = tdpCommitteeMemberDAO.save(tdpCommitteeMember);
					}
					
					TdpCommittee tdpCommittee = tdpCommitteeDAO.get(tdpCommitteeId);
					if(tdpCommittee != null){
						tdpCommittee.setIsCommitteeConfirmed("N");
						tdpCommittee.setCompletedDate(null);
						tdpCommittee = tdpCommitteeDAO.save(tdpCommittee);
					}
							
					return "success";
				}
				return "failure";
			}
		});
	} catch (Exception e) {
		LOG.error("Exception raised in updateCommitteeMemberDesignationByCadreId", e);
	}
	return status;
}

	public List<TdpCadreVO>  getPartyLeadersDeatails(Long userId,Long levelId,List<Long> locationIdsList,Long representativeTypeId,List<Long> designationIdsList,
			List<Long> committeeLevelIdsList,List<Long> enrollmentIdsList,List<Long> committeeTypeIdsList, Long stateId, int firstIndex,int maxIndex,String reportType){
		List<TdpCadreVO> returnList = new ArrayList<TdpCadreVO>(0);
		try {
			
			if(commonMethodsUtilService.isListOrSetValid(locationIdsList)){
				if(locationIdsList.contains(0L))
					locationIdsList.clear();
			}
			
			if(commonMethodsUtilService.isListOrSetValid(designationIdsList)){
				if(designationIdsList.contains(0L))
					designationIdsList.clear();
			}
			if(commonMethodsUtilService.isListOrSetValid(committeeTypeIdsList)){
				if(committeeTypeIdsList.contains(0L))
					committeeTypeIdsList.clear();
			}
			if(commonMethodsUtilService.isListOrSetValid(committeeLevelIdsList)){
				if(committeeLevelIdsList.contains(0L))
					committeeLevelIdsList.clear();
			}
			if(commonMethodsUtilService.isListOrSetValid(enrollmentIdsList)){
				if(enrollmentIdsList.contains(0L))
					enrollmentIdsList.clear();
			}
			List<Object[]> membersList =  new ArrayList<Object[]>(0);
			List<Long> constiIds1 = new ArrayList<Long>(0);
			Map<Long, TdpCadreVO> cadreMap1 = new HashMap<Long, TdpCadreVO>(0);
			if(representativeTypeId.longValue() == 1L || representativeTypeId.longValue() ==0L){
				List<Object[]> totalCountList = publicRepresentativeDAO.getPartyLeadersDeatails(stateId,enrollmentIdsList,levelId,locationIdsList,designationIdsList,firstIndex,maxIndex,"count");
				
				List<Object[]> cadreList = new ArrayList<Object[]>();
				if(totalCountList != null && totalCountList.size()>0){
					Object[] totalCountArr = totalCountList.get(0);
					int totalCount = totalCountArr[0] != null ? Integer.valueOf(totalCountArr[0].toString()):0;
					
					 int filterCount = maxIndex;
                     int i = 0; 
                     int j = filterCount;
                     int maxcount = maxIndex;
                     if(maxIndex == 1500 || maxIndex == 10000 || maxIndex == 20000 || maxIndex == 30000  || maxIndex == 50000  )
                    	 maxcount = totalCount;
                     while (maxcount >0){  
                         if(maxcount<filterCount)
                             j = i+maxcount;
                         
                         List<Object[]>  tempList  =  publicRepresentativeDAO.getPartyLeadersDeatails(stateId,enrollmentIdsList,levelId,locationIdsList,designationIdsList,i,j,"records");
                            if(commonMethodsUtilService.isListOrSetValid(tempList)){
                            	cadreList.addAll(tempList);
                            }
                         i=i+j;
                         maxcount = maxcount-filterCount;
                        // j=j+filterCount;
                         
                     }
				}
				
				//List<Object[]> cadreList = publicRepresentativeDAO.getPartyLeadersDeatails(stateId,enrollmentIdsList,levelId,locationIdsList,designationIdsList,firstIndex,maxIndex,"records");
				if(commonMethodsUtilService.isListOrSetValid(cadreList))
					membersList.addAll(cadreList);
				
					if(commonMethodsUtilService.isListOrSetValid(cadreList)){
						for (Object[] param : cadreList) {
							
							TdpCadreVO vo = new TdpCadreVO();
							vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
							if(cadreMap1.get(vo.getId()) != null){
								vo = cadreMap1.get(vo.getId());
								vo.setDesignation(vo.getDesignation() != null ?vo.getDesignation()+",":"" +commonMethodsUtilService.getStringValueForObject(param[4]));
							}
							else{
								vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
								vo.setMemberShipNo(commonMethodsUtilService.getStringValueForObject(param[1]));
								vo.setCadreName(commonMethodsUtilService.getStringValueForObject(param[2]));
								vo.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[3]));
								vo.setDesignation(commonMethodsUtilService.getStringValueForObject(param[4]));
								vo.setState(commonMethodsUtilService.getStringValueForObject(param[6]));
								vo.setDistrict(commonMethodsUtilService.getStringValueForObject(param[8]));
								vo.setParliament(commonMethodsUtilService.getStringValueForObject(param[10]));
								vo.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[11]));
								vo.setConstituency(commonMethodsUtilService.getStringValueForObject(param[12]));
								
								if(commonMethodsUtilService.getStringValueForObject(param[16]).trim().length()>0)
									vo.setTehsil(commonMethodsUtilService.getStringValueForObject(param[16]) +" Munci/Corp/Greater City" );
								else 
									vo.setTehsil(commonMethodsUtilService.getStringValueForObject(param[14]));
								
								if(commonMethodsUtilService.getStringValueForObject(param[20]).trim().length()>0)
									vo.setPanchayat(commonMethodsUtilService.getStringValueForObject(param[20]));
								else
									vo.setPanchayat(commonMethodsUtilService.getStringValueForObject(param[18]));
								
								vo.setImageURL(commonMethodsUtilService.getStringValueForObject(param[21]));
								//returnList.add(vo);
								cadreMap1.put(vo.getId(), vo);
								if(!constiIds1.contains(vo.getConstituencyId()))
									constiIds1.add(vo.getConstituencyId());
							}
						}
				}
			}
			
			Map<Long, TdpCadreVO> cadreMap2 = new HashMap<Long, TdpCadreVO>(0);
			if(representativeTypeId.longValue() == 2L  || representativeTypeId.longValue() ==0L){
				List<Object[]> totalCountList  = cadreCommitteeRoleDAO.getPartyCommitteeLeadersDeatails(stateId,enrollmentIdsList,committeeLevelIdsList,committeeTypeIdsList,levelId,locationIdsList,designationIdsList,firstIndex,maxIndex,"count");
				List<Object[]> cadreList = new ArrayList<Object[]>();
				if(totalCountList != null && totalCountList.size()>0){
					Object[] totalCountArr = totalCountList.get(0);
					int totalCount = totalCountArr[0] != null ? Integer.valueOf(totalCountArr[0].toString()):0;
					
					 int filterCount = maxIndex;
                     int i = 0; 
                     int j = filterCount;
                     int maxcount = maxIndex;
                     if(maxIndex == 1500 || maxIndex == 10000 || maxIndex == 20000 || maxIndex == 30000  || maxIndex == 50000  )
                    	 maxcount = totalCount;
                     while (maxcount >0){  
                         if(maxcount<filterCount)
                             j = i+maxcount;
                         
                         List<Object[]>  tempList  = cadreCommitteeRoleDAO.getPartyCommitteeLeadersDeatails(stateId,enrollmentIdsList,committeeLevelIdsList,committeeTypeIdsList,levelId,locationIdsList,designationIdsList,i,j,"records");
                            if(commonMethodsUtilService.isListOrSetValid(tempList)){
                            	cadreList.addAll(tempList);
                            }
                         i=i+j;
                         maxcount = maxcount-filterCount;
                        // j=j+filterCount;
                         
                     }
                     
				}
				//List<Object[]> cadreList = cadreCommitteeRoleDAO.getPartyCommitteeLeadersDeatails(stateId,enrollmentIdsList,committeeLevelIdsList,committeeTypeIdsList,levelId,locationIdsList,designationIdsList,firstIndex,maxIndex,"records");
				if(commonMethodsUtilService.isListOrSetValid(cadreList))
					membersList.addAll(cadreList);
				
					if(commonMethodsUtilService.isListOrSetValid(cadreList)){
						for (Object[] param : cadreList) {
							
							TdpCadreVO vo = new TdpCadreVO();
							vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
							if(cadreMap2.get(vo.getId()) != null){
								vo = cadreMap2.get(vo.getId());
								vo.setDesignation(vo.getDesignation() != null ?vo.getDesignation()+",":"" +commonMethodsUtilService.getStringValueForObject(param[4]));
								vo.setStatus(vo.getStatus() != null ?vo.getStatus()+",":"" +commonMethodsUtilService.getStringValueForObject(param[22]));
							}
							else{
								vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
								vo.setMemberShipNo(commonMethodsUtilService.getStringValueForObject(param[1]));
								vo.setCadreName(commonMethodsUtilService.getStringValueForObject(param[2]));
								vo.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[3]));
								vo.setDesignation(commonMethodsUtilService.getStringValueForObject(param[4]));
								vo.setStatus(commonMethodsUtilService.getStringValueForObject(param[22]));
								vo.setState(commonMethodsUtilService.getStringValueForObject(param[6]));
								vo.setDistrict(commonMethodsUtilService.getStringValueForObject(param[8]));
								vo.setParliament(commonMethodsUtilService.getStringValueForObject(param[10]));
								vo.setConstituencyId(commonMethodsUtilService.getLongValueForObject(param[11]));
								vo.setConstituency(commonMethodsUtilService.getStringValueForObject(param[12]));
								
								if(commonMethodsUtilService.getStringValueForObject(param[16]).trim().length()>0)
									vo.setTehsil(commonMethodsUtilService.getStringValueForObject(param[16]) +" Munci/Corp/Greater City" );
								else 
									vo.setTehsil(commonMethodsUtilService.getStringValueForObject(param[14]));
								
								if(commonMethodsUtilService.getStringValueForObject(param[20]).trim().length()>0)
									vo.setPanchayat(commonMethodsUtilService.getStringValueForObject(param[20]));
								else
									vo.setPanchayat(commonMethodsUtilService.getStringValueForObject(param[18]));
								
								vo.setImageURL(commonMethodsUtilService.getStringValueForObject(param[21]));
								
								cadreMap2.put(vo.getId(), vo);
								if(!constiIds1.contains(vo.getConstituencyId()))
									constiIds1.add(vo.getConstituencyId());
							}
						}
						
				}
			
			}
			
			if(commonMethodsUtilService.isListOrSetValid(membersList)){
				Map<Long,Long> constiNoMap = new HashMap<Long, Long>();
				List<Object[]> constiNosDtls = delimitationConstituencyDAO.getConstituencyNo(constiIds1, IConstants.DELIMITATION_YEAR);
				if(constiNosDtls != null && constiNosDtls.size() > 0){
					for(Object[] params : constiNosDtls){
						Long constiNo = constiNoMap.get((Long)params[0]);
						if(constiNo == null){
							constiNoMap.put((Long)params[0],(Long)params[1]);
						}
					}
				}
				
				if(commonMethodsUtilService.isMapValid(cadreMap1)){
					for (Long cadreId : cadreMap1.keySet()){
						TdpCadreVO vo  = cadreMap1.get(cadreId);
						vo.setConstituencyNo(constiNoMap.get(vo.getConstituencyId()));
						returnList.add(vo);
					}
				}
				if(commonMethodsUtilService.isMapValid(cadreMap2)){
					for (Long cadreId : cadreMap2.keySet()){
						TdpCadreVO vo  = cadreMap2.get(cadreId);
						vo.setConstituencyNo(constiNoMap.get(vo.getConstituencyId()));
						returnList.add(vo);
					}
				}
			}
			
			if(reportType != null && reportType.trim().contains("EXPORTEXCEL"))
			{
				String[] arr = reportType.split("::");
				String url = arr[1].toString();
				String path = exprortToExcelForLeaders(returnList,url);
				if(commonMethodsUtilService.isListOrSetValid(returnList)){
					returnList.clear();
					TdpCadreVO vo  = new TdpCadreVO();
					vo.setTotalImagePathStr(path);
					returnList.add(vo);
				}
			}else{
				Random randomNum = new Random();
    		   	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    		   	Date date = new Date();
    		   //	System.out.println(dateFormat.format(date));
    		   	String url = "Invitees/"+dateFormat.format(date)+"_"+randomNum.nextInt(10000)+".xls";
    		   	if(returnList != null && returnList.size()>0){
    		   		TdpCadreVO vo  = returnList.get(0);
    		   		vo.setAadharNo(url);
    		   	}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getPartyLeadersDeatails", e);
		}
		return returnList;
	}
	
	public String exprortToExcelForLeaders(List<TdpCadreVO> returnList,String url){
		
		try {
			if(returnList  != null && returnList.size()>0)
			{	
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("INVITEE DETAILS");
				HSSFRow rowhead = sheet.createRow((short) 1);
				
				
				
				HSSFFont font = workbook.createFont();
			    font.setFontName("Calibri");
			    font.setFontHeightInPoints((short)14);
			    CellStyle style = workbook.createCellStyle();
			    style.setFillForegroundColor(HSSFColor.YELLOW.index);
			    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			    style.setAlignment(CellStyle.ALIGN_CENTER);
			    style.setFont(font);
			    
			    //for data.
			    HSSFFont font1 = workbook.createFont();
			    font1.setFontName("Calibri");
			    font1.setFontHeightInPoints((short)11);
			    CellStyle style1 = workbook.createCellStyle();
			    style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			    style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
			    style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
			    style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			    style1.setAlignment(CellStyle.ALIGN_CENTER);
			    style1.setFont(font1);
			   
			    HSSFFont font2 = workbook.createFont();
			    font2.setFontName("Calibri");
			    font2.setFontHeightInPoints((short)11);
			    CellStyle style2 = workbook.createCellStyle();
			    style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			    style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			    style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
			    style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			    style1.setFont(font2);
			   
			    
			    HSSFCell cell = (HSSFCell) rowhead.createCell(1);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" S.NO ");
			    
			    cell = (HSSFCell) rowhead.createCell(2);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" DISTRICT");
			    
			    cell = (HSSFCell) rowhead.createCell(3);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" PC_NAME ");
			    
			    cell = (HSSFCell) rowhead.createCell(4);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" AC_NO ");
			    
			    cell = (HSSFCell) rowhead.createCell(5);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" AC_NAME ");
			    
			    cell = (HSSFCell) rowhead.createCell(6);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" MANDAL/TOWN/DIVISION ");
			    
			    cell = (HSSFCell) rowhead.createCell(7);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" VILLAGE/WARD ");
			    
			    cell = (HSSFCell) rowhead.createCell(8);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue("  CANDIDATE NAME ");
			    
			    cell = (HSSFCell) rowhead.createCell(9);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" COMMITTEE - LEVEL  ");
			    
			    cell = (HSSFCell) rowhead.createCell(10);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" DESIGNATION ");
			    
			    cell = (HSSFCell) rowhead.createCell(11);
			    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
		    	cell.setCellStyle(style);
			    cell.setCellValue(" MOBILE NO ");
			    
				int rowNum = 1;
				int sno = 0;
				for (TdpCadreVO cadreCommitteeVO : returnList) 
				{
					rowNum++;
					sno++;
				    Row dataRow = sheet.createRow(rowNum);

				    cell = (HSSFCell) dataRow.createCell(1);
				    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
			    	cell.setCellStyle(style2);
			    	cell.setCellValue(sno);
			    	
			    	cell = (HSSFCell) dataRow.createCell(2);
				    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
				    if(cadreCommitteeVO.getDistrict() != null )
				    {
				    	cell.setCellStyle(style2);
				    	cell.setCellValue(cadreCommitteeVO.getDistrict());
				    }
				    else
				    {
				    	cell.setCellStyle(style1);
				    	cell.setCellValue(" - ");
				    }
				    	
				    cell = (HSSFCell) dataRow.createCell(3);
				    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
				    if(cadreCommitteeVO.getParliament() != null )
				    {
				    	cell.setCellStyle(style2);
				    	cell.setCellValue(cadreCommitteeVO.getParliament());
				    }
				    else
				    {
				    	cell.setCellStyle(style1);
				    	cell.setCellValue(" - ");
				    }
			    	
				    cell = (HSSFCell) dataRow.createCell(4);
				    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
				    if(cadreCommitteeVO.getConstituencyNo() != null )
				    {
				    	cell.setCellStyle(style2);
				    	cell.setCellValue(cadreCommitteeVO.getConstituencyNo());
				    }
				    else
				    {
				    	cell.setCellStyle(style1);
				    	cell.setCellValue(" - ");
				    }
				    
			    	 
			    	cell = (HSSFCell) dataRow.createCell(5);
			    	cell.setCellType( HSSFCell.CELL_TYPE_STRING );
				    if(cadreCommitteeVO.getConstituency() != null )
				    {
				    	cell.setCellStyle(style2);
				    	cell.setCellValue(cadreCommitteeVO.getConstituency());
				    }
				    else
				    {
				    	cell.setCellStyle(style1);
				    	cell.setCellValue(" - ");
				    }
			    	 
			    	cell = (HSSFCell) dataRow.createCell(6);
				    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
			    	 if(cadreCommitteeVO.getTehsil() != null )
					    {
					    	cell.setCellStyle(style2);
					    	cell.setCellValue(cadreCommitteeVO.getTehsil());
					    }
					    else
					    {
					    	cell.setCellStyle(style1);
					    	cell.setCellValue(" - ");
					    }
			    	 
			    	 cell = (HSSFCell) dataRow.createCell(7);
					    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
				    	 if(cadreCommitteeVO.getPanchayat() != null )
						    {
						    	cell.setCellStyle(style2);
						    	cell.setCellValue(cadreCommitteeVO.getPanchayat());
						    }
						    else
						    {
						    	cell.setCellStyle(style1);
						    	cell.setCellValue(" - ");
						    }
				    	 
			    	cell = (HSSFCell) dataRow.createCell(8);
				    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
			    	 if(cadreCommitteeVO.getCadreName() != null )
					    {
					    	cell.setCellStyle(style2);
					    	cell.setCellValue(cadreCommitteeVO.getCadreName());
					    }
					    else
					    {
					    	cell.setCellStyle(style1);
					    	cell.setCellValue(" - ");
					    }
			    	 
			    	cell = (HSSFCell) dataRow.createCell(9);
			    	 cell.setCellType( HSSFCell.CELL_TYPE_STRING );
			    	 if(cadreCommitteeVO.getStatus() != null )
					    {
					    	cell.setCellStyle(style2);
					    	cell.setCellValue(cadreCommitteeVO.getStatus());
					    }
					    else
					    {
					    	cell.setCellStyle(style1);
					    	cell.setCellValue(" - ");
					    }
			    	 
			    	cell = (HSSFCell) dataRow.createCell(10);
			    	cell.setCellType( HSSFCell.CELL_TYPE_STRING );
			    	 if(cadreCommitteeVO.getDesignation() != null )
					    {
					    	cell.setCellStyle(style2);
					    	cell.setCellValue(cadreCommitteeVO.getDesignation());
					    }
					    else
					    {
					    	cell.setCellStyle(style1);
					    	cell.setCellValue(" - ");
					    }
			    	 
			    	cell = (HSSFCell) dataRow.createCell(11);
				    cell.setCellType( HSSFCell.CELL_TYPE_STRING );
			    	 if(cadreCommitteeVO.getMobileNo() != null )
					    {
					    	cell.setCellStyle(style2);
					    	cell.setCellValue(cadreCommitteeVO.getMobileNo());
					    }
					    else
					    {
					    	cell.setCellStyle(style1);
					    	cell.setCellValue(" - ");
					    }
				}
				
				 try {
		    	        FileOutputStream out =  new FileOutputStream(new File(IConstants.STATIC_CONTENT_FOLDER_URL+""+url));
		    	        workbook.write(out);
		    	        out.close();
		    	         
		    	    } catch (FileNotFoundException e) {
		    	        e.printStackTrace();
		    	    } catch (IOException e) {
		    	        e.printStackTrace();
		    	    } 
				 
			}
		} catch (Exception e) {
			LOG.error("Exception raised in generateExcelReport",e);
		}
		return url;
	}
	
	public List<IdNameVO> getCommitteeLevelDetils(){
		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
		try {
			List<Object[]> committeLevelObjLst = tdpCommitteeLevelDAO.getAllLevels();
			if(committeLevelObjLst != null && committeLevelObjLst.size()>0){
				for(Object[] param : committeLevelObjLst){
					IdNameVO resultVO = new IdNameVO();
					resultVO.setId((Long)param[0]);
					resultVO.setName(param[1] != null ?param[1].toString() :"");
					finalList.add(resultVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in CadreCommitteeService of getCommitteeLevelDetils", e);
		}
		return finalList;
	}
	
	public List<IdNameVO> getCommitteeTypeDetils(){
		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
		try {
			List<Object[]> committeTypeObjLst = tdpBasicCommitteeDAO.getBasicCommittees();
			if(committeTypeObjLst != null && committeTypeObjLst.size()>0){
				for(Object[] param : committeTypeObjLst){
					IdNameVO resultVO = new IdNameVO();
					resultVO.setId((Long)param[0]);
					resultVO.setName(param[1] != null ?param[1].toString() :"");
					finalList.add(resultVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in CadreCommitteeService of getCommitteeLevelDetils", e);
		}
		return finalList;
	}
	public ResultStatus saveElectionBoothCommitteeDetails(Long userId,Long boothId,Long tdpCadreId,Long boothInchrgRoleId,List<Long> boothEnrollmentYrIds,String isOtherRange){
		ResultStatus status = new ResultStatus();
		try{
			BoothInchargeRoleConditionMapping boothInchargeRoleConditionMapping = boothInchargeRoleConditionMappingDAO.get(boothInchrgRoleId);
			if(boothInchargeRoleConditionMapping.getBoothInchargeRoleCondition().getBoothInchargeRoleId() >1L){//except convener
				 List<Long> boothIdsList = new ArrayList<Long>(0);
				 boothIdsList.add(boothId);
				 List<Long> tdpCadreIdsList = new ArrayList<Long>(0);
				 tdpCadreIdsList.add(tdpCadreId);
				 boolean isEligibleToAdd = true;
				 
				List<Object[]> boothCadreIdList = boothInchargeDAO.getCadreIdsForLocation(boothIdsList,1L);
				Set<Long> needToDisableSerialNosList = new HashSet<Long>(0);
				Object[] resultStatus = null;
				Long minValue=0L;
				Long maxValue =0L;
				
				if(boothCadreIdList != null && boothCadreIdList.size() > 0){
					 Map<Long,String> otherRangeIdMap = new HashMap<Long, String>(0);
					 Map<Long,String> otherSerialNoRangeIdMap = new HashMap<Long, String>(0);
					List<Long> voterIdsList = new ArrayList<Long>(0);
					for(Object[] Obj: boothCadreIdList){
						//own voterId, family VoterId
						 voterIdsList.add(commonMethodsUtilService.getLongValueForObject(Obj[8]));
						 voterIdsList.add(commonMethodsUtilService.getLongValueForObject(Obj[9]));
						 if(commonMethodsUtilService.getLongValueForObject(Obj[10]).longValue()>0L){
							 if(commonMethodsUtilService.getLongValueForObject(Obj[8]).longValue()>0L)
								 otherRangeIdMap.put(commonMethodsUtilService.getLongValueForObject(Obj[8]), "true");
							 if(commonMethodsUtilService.getLongValueForObject(Obj[9]).longValue()>0L)
								 otherRangeIdMap.put(commonMethodsUtilService.getLongValueForObject(Obj[9]), "true");
						 }
						}
					
						List<Object[]> voterAndSerialNoList = boothDAO.getVoterSerialNoByVoterIdsList(voterIdsList,boothId);
						if(commonMethodsUtilService.isListOrSetValid(voterAndSerialNoList)){
							for (Object[] param : voterAndSerialNoList) {
								needToDisableSerialNosList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
								if(otherRangeIdMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) != null){
									otherSerialNoRangeIdMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), "true");
								}
							}
						}
						List<Object[]> cadreSerilaNoList = tdpCadreDAO.getSerialNoInLastestPublicationForCadre(tdpCadreIdsList);
						if(commonMethodsUtilService.isListOrSetValid(cadreSerilaNoList)){
							for (Long checkWithSerialNo : needToDisableSerialNosList) {
								if(isOtherRange != null && !isOtherRange.trim().contains("other")){
									if(otherSerialNoRangeIdMap.get(checkWithSerialNo) == null){
										if(isEligibleToAdd){
											if(checkWithSerialNo != null && checkWithSerialNo.longValue()>0L){
												resultStatus = commonMethodsUtilService.isAvaiableBetweenPreAndPost_100_Distance(commonMethodsUtilService.getLongValueForObject(cadreSerilaNoList.get(0)[1]),checkWithSerialNo);
											}
											if(resultStatus != null){
												isEligibleToAdd = (boolean) Boolean.valueOf(resultStatus[0].toString());
												minValue =  commonMethodsUtilService.getLongValueForObject(resultStatus[1]);
												maxValue =  commonMethodsUtilService.getLongValueForObject(resultStatus[2]);
											}
										}
									}
								}else{
									isEligibleToAdd = true;
								}
							}
						}else{
							isEligibleToAdd = false;
						}
					}
				if(!isEligibleToAdd){
					status.setResultCode(2);
					status.setMessage("The Serial Nos between "+minValue+" - "+maxValue+" already added. So try with other than this range Serial Nos...");
					return status;
				}
			}
			
			//generate booth incharge condition id 
			String tempIsOtherRange = isOtherRange.replace("other", "");
			String rangeArr[] = tempIsOtherRange.split("-");
			Long boothInchargeConditionId = 0L;
			if(rangeArr != null && rangeArr.length>0){
				Long minValue =  commonMethodsUtilService.getLongValueForObject(rangeArr[0]);
				Long maxValue =  commonMethodsUtilService.getLongValueForObject(rangeArr[1]);
				List<Long> idsList = boothInchargeConditionDAO.getboothInchargeConditionIdByRange(minValue,maxValue);
				if(commonMethodsUtilService.isListOrSetValid(idsList))
					boothInchargeConditionId = idsList.get(0);
			}
			List<Long> boothInchId = boothInchargeDAO.checkIsBoothAlreadySaved(boothId,boothInchrgRoleId,boothEnrollmentYrIds);
			
			if(boothInchId == null || boothInchId.size() == 0){
				//BoothInchargeRoleConditionMapping boothInchargeRoleConditionMapping = boothInchargeRoleConditionMappingDAO.get(boothInchrgRoleId);
				if(boothInchargeRoleConditionMapping != null){
					BoothInchargeCommittee boothInchargeCommittee = boothInchargeCommitteeDAO.get(boothInchargeRoleConditionMapping.getBoothInchargeCommitteeId());
					if(boothInchargeCommittee != null){
						boothInchargeCommittee.setStartDate(dateUtilService.getCurrentDateAndTime());
						boothInchargeCommitteeDAO.save(boothInchargeCommittee);
					}
				}
			}
			//BoothInchargeRoleConditionMapping boothInchargeRoleConditionMapping = boothInchargeRoleConditionMappingDAO.get(boothInchrgRoleId);
		    Long maxBoothRoleCount = boothInchargeRoleConditionMapping.getBoothInchargeRoleCondition().getMaxMembers();
		    Long boothInchargeRoleId = boothInchargeRoleConditionMapping.getBoothInchargeRoleCondition().getBoothInchargeRoleId();
		    Long boothAddedMemberCount = boothInchargeDAO.getRoleWiseTotalAddedMember(boothId, boothEnrollmentYrIds.get(0), boothInchargeRoleId);
		    
		    if (boothAddedMemberCount < maxBoothRoleCount) {/*Checking is required member added or not.if not then only user can add else we are sending resultCode by that we are showing message to end user.Now Vacany is not there. */
		    	BoothIncharge boothIncharge = null;
		    	List<BoothIncharge> boothInchargeList = boothInchargeDAO.getExistingMember(tdpCadreId,"addOption");
		    	
		    	if(commonMethodsUtilService.isListOrSetValid(boothInchargeList))
		    		boothIncharge = boothInchargeList.get(0);
		    	
				if(boothIncharge != null){
					boothIncharge.setBoothInchargeSerialNoRangeId(1L);//default 1 , it will update by scheduler call -- Hyma
					boothIncharge.setBoothInchargeRoleConditionMappingId(boothInchrgRoleId);
					if(isOtherRange.trim().contains("other") && boothInchargeConditionId.longValue()>0L)
						boothIncharge.setAddedBoothInhcargeConditionId(boothInchargeConditionId);
					else
						boothIncharge.setAddedBoothInhcargeConditionId(null);
					boothIncharge.setUpdatedBy(userId);
					boothIncharge.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					boothIncharge.setIsActive("Y");
					boothIncharge.setIsDeleted("N");
					boothInchargeDAO.save(boothIncharge);
				}else{
					BoothIncharge boothInchrge = new BoothIncharge();
					//boothInchrge.setBoothId(Long.valueOf(boothId));
					boothInchrge.setTdpCadreId(tdpCadreId);
					boothInchrge.setIsActive("Y");
					boothInchrge.setIsDeleted("N");
					boothInchrge.setBoothInchargeSerialNoRangeId(1L);//default 1 , it will update by scheduler call -- Hyma
					boothInchrge.setBoothInchargeEnrollmentId(boothEnrollmentYrIds.get(0));
					boothInchrge.setInsertedBy(userId);
					boothInchrge.setUpdatedBy(userId);
					boothInchrge.setBoothInchargeRoleConditionMappingId(boothInchrgRoleId);
					if(isOtherRange.trim().contains("other") && boothInchargeConditionId.longValue()>0L)
						boothInchrge.setAddedBoothInhcargeConditionId(boothInchargeConditionId);
					else
						boothInchrge.setAddedBoothInhcargeConditionId(null);
					boothInchrge.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					boothInchrge.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					boothInchargeDAO.save(boothInchrge);
				}
				
				status.setResultCode(0);
		    }else {
		    	status.setResultCode(3);
		    }
		}catch(Exception e){
			status.setResultCode(1);
			LOG.error("Exception raised in saveElectionBoothCommitteeDetails ", e);
		}
		return status;
	 }

		public List<CadreCommitteeVO> getBoothsForMandals(Long mandalId,Long constituencyId){
		 List<CadreCommitteeVO> returnList = new ArrayList<CadreCommitteeVO>();
		 try{
			 List<Long> mandalIds = new ArrayList<Long>();
			List<Long> localBodyIds = new ArrayList<Long>();
			List<Object[]> boothList = null;
				
			 if((mandalId.toString().substring(0,1)).equalsIgnoreCase("2")){
					mandalIds.add(Long.valueOf(mandalId.toString().substring(1)));
			}
			if((mandalId.toString().substring(0,1)) .equalsIgnoreCase("1")){
				localBodyIds.add(Long.valueOf(mandalId.toString().substring(1)));
			}
			 if(mandalIds != null && mandalIds.size() > 0l){
				 //boothList = boothDAO.getBoothsForTehsilId(mandalIds,constituencyId);
				 boothList = boothInchargeRoleConditionMappingDAO.getBoothDetailsForTehsilId(mandalIds,constituencyId);
			 }
			 if(localBodyIds != null && localBodyIds.size() > 0l){
				 //boothList = boothDAO.getBoothsForMuncipality(localBodyIds,constituencyId);
				 boothList = boothInchargeRoleConditionMappingDAO.getBoothsDetailsForMuncipality(localBodyIds,constituencyId);
			 }
			 if(boothList != null && boothList.size() > 0l){
				 for (Object[] objects : boothList) {
					 CadreCommitteeVO vo = new CadreCommitteeVO();
					 vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));//BoothId
					 vo.setRoleId(commonMethodsUtilService.getLongValueForObject(objects[1]));//partNo
					 vo.setRelativeName(commonMethodsUtilService.getStringValueForObject(objects[2]));//villageCvred
					 returnList.add(vo);
				}
			 } 
		 }catch(Exception e){
			 LOG.error("Exception raised in getBoothsForMandals ", e);
		 }
		 return returnList;
	 }
	public CadreCommitteeVO getCadreDetailsForBothsCommittee(Long locationLevel,Long locationId, String searchName,String memberShipCardNo,
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,Long fromAge,Long toAge,String houseNo,String gender,int startIndex,int maxIndex,boolean isRemoved,Long enrollmentId,String searchType){
		CadreCommitteeVO committeevo = new CadreCommitteeVO();
		try{
			Long boothId =houseNo != null && houseNo.trim().length()>0?Long.valueOf(houseNo):0L;
			List<Long> boothIdsList = new ArrayList<Long>(0);
			boothIdsList.add(boothId);
			
			Map<Long,CadreCommitteeVO> cadreMap = new LinkedHashMap<Long, CadreCommitteeVO>(0);
			List<CadreCommitteeVO> returnList  = new ArrayList<CadreCommitteeVO>();
			List<Long> tdpCadreIds = new ArrayList<Long>(0);
			Long tdpCadreId = 0l;
			committeevo = searchTdpCadreDetailsBySearchCriteriaForCadreCommitte(locationLevel,locationId,searchName,memberShipCardNo,voterCardNo,trNumber,mobileNo,casteStateId,casteCategory,
			 fromAge, toAge, null, gender, startIndex, maxIndex, isRemoved,enrollmentId,searchType);
			List<Object[]> votersList = boothDAO.getVoterDetailsByBoothId(boothId);
			Map<Long,Long> voterSerialNoMap = new HashMap<Long, Long>(0);
			if(commonMethodsUtilService.isListOrSetValid(votersList)){
				for (Object[] param : votersList) {
					voterSerialNoMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
			
			if(committeevo != null && commonMethodsUtilService.isListOrSetValid(committeevo.getPreviousRoles())){
				for (CadreCommitteeVO vo: committeevo.getPreviousRoles()){
					if(voterSerialNoMap.keySet().contains(vo.getCadreVoterId())){
						tdpCadreId = vo.getTdpCadreId();
						tdpCadreIds.add(tdpCadreId);
						vo.setType("Not Added");
						vo.setSerialNo(voterSerialNoMap.get(vo.getCadreVoterId()).toString());
						cadreMap.put(vo.getTdpCadreId(), vo);
					}
				}
			}
			
			List<Object[]> boothCadreIdList = boothInchargeDAO.getCadreIdsForLocation(boothIdsList,0L);
			Set<Long> needToDisableSerialNosList = new HashSet<Long>(0);
			Set<String> addedSerialNoList = new HashSet<String>(0);
			if(boothCadreIdList != null && boothCadreIdList.size() > 0l){
				for(Object[] Obj: boothCadreIdList){
					//own voterId, family VoterId
					// needToDisableSerialNosList.add(voterSerialNoMap.get(commonMethodsUtilService.getLongValueForObject(Obj[8])));
					// needToDisableSerialNosList.add(voterSerialNoMap.get(commonMethodsUtilService.getLongValueForObject(Obj[9])));
					CadreCommitteeVO vo = cadreMap.get(commonMethodsUtilService.getLongValueForObject(Obj[0]));
						if(vo !=null){
							
							 vo.setType("Added Member");
							 vo.setRoleName(commonMethodsUtilService.getStringValueForObject(Obj[7]));
							 vo.setBoothNumber(commonMethodsUtilService.getLongValueForObject(Obj[1]));
							 vo.setPanchayat(commonMethodsUtilService.getStringValueForObject(Obj[2]));
							 vo.setTehsilId(commonMethodsUtilService.getLongValueForObject(Obj[3]));
							 vo.setLocalElectionId(commonMethodsUtilService.getLongValueForObject(Obj[5]));
							 if(Obj[3] == null){
								 vo.setLocalElectionBody(commonMethodsUtilService.getStringValueForObject(Obj[6]));	 
							 }else{
								 vo.setTehsil(commonMethodsUtilService.getStringValueForObject(Obj[4]));
							 }
							 vo.setSerialNo(voterSerialNoMap.get(vo.getCadreVoterId()).toString());
							 vo.setIsDuplicate("No");
							 if(addedSerialNoList.contains(vo.getSerialNo().trim())){
								 vo.setIsDuplicate("Yes");
							 }
							addedSerialNoList.add(vo.getSerialNo().trim());
						}
					}
				}
			
			if(commonMethodsUtilService.isMapValid(cadreMap)){
				returnList = new ArrayList<CadreCommitteeVO>(0);
				if(!commonMethodsUtilService.isListOrSetValid(needToDisableSerialNosList)){
					//returnList = new ArrayList<CadreCommitteeVO>(cadreMap.values());
					for (CadreCommitteeVO vo : cadreMap.values()) {
						vo.setIsDuplicate("No");
						 if(addedSerialNoList.contains(vo.getSerialNo().trim())){
							 vo.setIsDuplicate("Yes");
						 }
						 returnList.add(vo);	
					}
				}else if(commonMethodsUtilService.isListOrSetValid(needToDisableSerialNosList)){
				
					Object[] resultStatus = null;
					for (CadreCommitteeVO vo : cadreMap.values()) {
						boolean isEligibleToAdd = true;
						for (Long serialNo : needToDisableSerialNosList) {
							if(isEligibleToAdd){
								if(serialNo != null && serialNo.longValue()>0L){
									resultStatus = commonMethodsUtilService.isAvaiableBetweenPreAndPost_100_Distance(Long.valueOf(vo.getSerialNo()),serialNo);
								}
							}
						}
						if(resultStatus != null){
							isEligibleToAdd = (boolean) Boolean.valueOf(resultStatus[0].toString());
						}
						
					if(isEligibleToAdd)
						returnList.add(vo);
					}
				}
			}
			committeevo.setPreviousRoles(returnList);
			//Checking isVoterDelted or not and sending status to UI.
			if (committeevo.getPreviousRoles() != null && committeevo.getPreviousRoles().size() > 0) {
				Set<Long> cadreIdSet = new HashSet<Long>(0);
				for (CadreCommitteeVO cadreVO : committeevo.getPreviousRoles()) {
					cadreIdSet.add(cadreVO.getTdpCadreId());
				}
				List<Object[]> cadreObjLst = tdpCadreDAO.isVoterDeleted(cadreIdSet);
				Map<Long, String> cadreStatusMap = new HashMap<Long, String>(0);
				if (cadreObjLst != null && cadreObjLst.size() > 0) {
					
					for (Object[] param : cadreObjLst) {
						cadreStatusMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1]));
					}
					
					for (CadreCommitteeVO cadreVO : committeevo.getPreviousRoles()) {
						cadreVO.setIsDeletedVoter(cadreStatusMap.get(cadreVO.getTdpCadreId()));
					}
				}
				
			}
			
			
		}catch(Exception e){
			 LOG.error("Exception raised in saveElectionBoothCommitteeDetails ", e);
		}
		return  committeevo;
	}
	 
		public List<IdNameVO> getMultplConstituencesByDistctIds(List<Long> districtIdsLst){
			List<IdNameVO> finalList = new ArrayList<IdNameVO>();
			try {
				List<Object[]> committeTypeObjLst = constituencyDAO.getDistrictConstituenciesList(districtIdsLst);
				if(committeTypeObjLst != null && committeTypeObjLst.size()>0){
					for(Object[] param : committeTypeObjLst){
						IdNameVO resultVO = new IdNameVO();
						resultVO.setId((Long)param[0]);
						resultVO.setName(param[1] != null ?param[1].toString() :"");
						finalList.add(resultVO);
					}
				}
			} catch (Exception e) {
				LOG.error("Exception raised in CadreCommitteeService of getMultplConstituencesByDistctIds", e);
			}
			return finalList;
		}
		
		
		
	public List<LocationWiseBoothDetailsVO> getMultiMandalsByConstituencyLst(List<Long> constituencyIds){
			
			List<LocationWiseBoothDetailsVO> mandalsList = getMultiMandalMunicCorpDetailsLst(constituencyIds);
			return mandalsList;
			
		}

	public List<LocationWiseBoothDetailsVO> getMultiMandalMunicCorpDetailsLst(List<Long> constituencyIds){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		 LocationWiseBoothDetailsVO vo1 = new LocationWiseBoothDetailsVO();
	        vo1.setLocationId(0l);
	        vo1.setLocationName("Select Mandal/Muncipality/Corporation");
	        locationsList.add(vo1);
		LocationWiseBoothDetailsVO vo = null;
		List<SelectOptionVO> locations = regionServiceDataImp.getAllMandalsByAllConstituencies(constituencyIds);
		List<Object[]> localBodies = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituencyList(constituencyIds);
	        for(SelectOptionVO location:locations){
	        	vo = new LocationWiseBoothDetailsVO();
	        	vo.setLocationId(Long.valueOf("2"+location.getId()));
	        	vo.setLocationName(location.getName()+" Mandal");
	        	locationsList.add(vo);
	        }
	        int count =0;
	        for(Object[] localBodi:localBodies){
	        	count = count+1;
	        	if(((Long)localBodi[0]).longValue() != 20l &&  ((Long)localBodi[0]).longValue() != 124l && ((Long)localBodi[0]).longValue() != 119l){
		        	vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("1"+localBodi[0].toString()));
		        	vo.setLocationName(localBodi[1].toString());
		        	locationsList.add(vo);
	        	}
	        	else
	        	{
	        		/*if(count==1)
	        			locationsList.clear();*/
	        		
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("1"+localBodi[0].toString()));
		        	vo.setLocationName(localBodi[1].toString());
		        	locationsList.add(vo);
	        	}
	        }
	       
	        return locationsList;
	}

	public  List<LocationWiseBoothDetailsVO> getMultplePanchayatWardByMandalIdsLst(List<Long> constituencyIds,List<Long> mandalIds){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		 LocationWiseBoothDetailsVO vo1 = new LocationWiseBoothDetailsVO();
		   vo1.setLocationId(0l);
		   vo1.setLocationName("Select Panchayat/Ward/Division/City");
		   locationsList.add(vo1);
		LocationWiseBoothDetailsVO vo = null;
		List<Long> mandalIdsLst = new ArrayList<Long>();
		List<Long> localBodyIds = new ArrayList<Long>();
		
		for(Long locationId : mandalIds){        	
	    	if(locationId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
	    		mandalIdsLst.add(Long.valueOf(locationId.toString().substring(1)));
	    	}
	    	if(locationId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
	    		localBodyIds.add(Long.valueOf(locationId.toString().substring(1)));
	    	}
	    }
		
		
		if(mandalIdsLst.size()>0){
	   	//0panchayatId,1panchayatName,2tehsilName
	   	List<Object[]> panchayatsList = panchayatDAO.getAllPanchayatsInMandals(mandalIdsLst);
	   	for(Object[] panchayat:panchayatsList){
	   		vo = new LocationWiseBoothDetailsVO();
	       	vo.setLocationId(Long.valueOf("1"+(Long)panchayat[0]));
	       	vo.setLocationName(panchayat[1].toString()+"("+panchayat[2].toString()+")");
	       	locationsList.add(vo);
	   	}
	   }
		   if(localBodyIds.size() > 0){
			   List<Object[]> localBodyList = new ArrayList<Object[]>();
			   //0wardId,1pwardName,2localBdyName
			   if(constituencyIds == null || constituencyIds.size() == 0L)
				   localBodyList = constituencyDAO.getWardsInLocalElectionBody(localBodyIds);
			   else
				   localBodyList = assemblyLocalElectionBodyWardDAO.findWardsByLocalBodyConstiIds(localBodyIds, constituencyIds);
			   
	       	for(Object[] localBody:localBodyList){
	       		vo = new LocationWiseBoothDetailsVO();
		        	//vo.setLocationId(Long.valueOf("2"+(Long)localBody[0]));
	       		//vo.setLocationName(localBody[1].toString()+"("+localBody[2].toString()+")");
	       		vo.setLocationId((Long)localBody[0]);
		        vo.setLocationName(localBody[1] != null ? localBody[1].toString():"");
		        	locationsList.add(vo);
	       	}
	       }
		  
		return locationsList;
	}
 public ResultStatus removeMbrFromCurentLocation(Long userId,Long tdpCadreId){
	 ResultStatus status = new ResultStatus();
	 try{
		 BoothIncharge boothIncharge = null;
    	 List<BoothIncharge> boothInchargeList = boothInchargeDAO.getExistingMember(tdpCadreId,"removeOption");
    	 if(commonMethodsUtilService.isListOrSetValid(boothInchargeList))
    		boothIncharge = boothInchargeList.get(0);
	    	
		 if(boothIncharge != null){
			 boothIncharge.setIsActive("N");
			 boothIncharge.setUpdatedBy(userId);
			 boothIncharge.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			 boothIncharge.setIsDeleted("Y");
			 boothInchargeDAO.save(boothIncharge);
			 status.setResultCode(0);
		  }
	}catch(Exception e){
		 status.setResultCode(1);
		 LOG.error("Exception raised in CadreCommitteeService of removeMbrFromCurentLocation", e);
	 }
	 return status;
 }
 
 public LocationWiseBoothDetailsVO getTotalBoothsCountByConstituenctIds(Long constituencyId){
	 LocationWiseBoothDetailsVO locationVO = new LocationWiseBoothDetailsVO() ;
	 try{
		 LOG.info("Entered into CadreCommitteeService of getTotalBoothsCountByConstituenctIds");
		  Long totNotStartedBothCnt =0l;
		  
		  Long  totBothsCnt = boothDAO.getTotalBoothsByConstituencyId(constituencyId);//total Booths count
			if(totBothsCnt.longValue()>0l){
				locationVO.setTotalCount(totBothsCnt);
			}
		
		 Long totStartedBoothCnt = boothInchargeDAO.getStartedBothCountByConstiId(constituencyId);//total Started Booth counts
	         if(totStartedBoothCnt.longValue()>0l){
	        	 locationVO.setCount(totStartedBoothCnt);
	         }
         
         totNotStartedBothCnt = ((locationVO.getTotalCount())-(locationVO.getCount()));
       
         locationVO.setTotNotStartedBothCnt(totNotStartedBothCnt);
         
	}catch(Exception e){
		 LOG.error("Exception raised in CadreCommitteeService of getTotalBoothsCountByConstituenctIds", e);
	 }
	 return locationVO;
}
 public List<LocationWiseBoothDetailsVO> getTdpCommitteeMunicipalityByWards(String mandalId,Long constituencyId,Long enrollmentId){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		// LocationWiseBoothDetailsVO vo1 = new LocationWiseBoothDetailsVO();
		   //vo1.setLocationId(0l);
		   //vo1.setLocationName("Select Panchayat/Ward/Division/City");
		  // locationsList.add(vo1);
		LocationWiseBoothDetailsVO vo = null;
		List<Long> mandalIds = new ArrayList<Long>();
		List<Long> localBodyIds = new ArrayList<Long>();
		
		if((mandalId.substring(0,1)).equalsIgnoreCase("2")){
			mandalIds.add(Long.valueOf(mandalId.substring(1)));
		}
		if((mandalId.substring(0,1)).equalsIgnoreCase("1")){
			localBodyIds.add(Long.valueOf(mandalId.substring(1)));
		}
		
		if(mandalIds.size()>0){
	    	//0panchayatId,1panchayatName
	    	List<Object[]> panchayatsList = tdpCommitteeDAO.getTdpCommitteeAllPanchayatsInMandals(mandalIds,enrollmentId,constituencyId);
	    	for(Object[] panchayat:panchayatsList){
	    		vo = new LocationWiseBoothDetailsVO();
	        	vo.setLocationId(Long.valueOf("1"+(Long)panchayat[0]));
	        	vo.setLocationName(panchayat[1].toString()+"("+panchayat[2].toString()+")");
	        	locationsList.add(vo);
	    	}
	    }
		   if(localBodyIds.size() > 0){
			   List<Object[]> localBodyList = new ArrayList<Object[]>();
			   //0wardId,1pwardName,2localBdyName
			   if(constituencyId == null || constituencyId.longValue() == 0L){
				   localBodyList = constituencyDAO.getWardsInLocalElectionBody(localBodyIds);
			   }
			   if(enrollmentId != null && enrollmentId.longValue() == 0L) // enrollment year id is zero 
				     localBodyList =boothDAO.getBoothsForMuncipalityWise(localBodyIds,constituencyId);
			     else
					 localBodyList = tdpCommitteeDAO.getTdpCommitteeWardsInLocalElectionBody(localBodyIds, constituencyId);
	        	for(Object[] localBody:localBodyList){
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("2"+commonMethodsUtilService.getLongValueForObject(localBody[0])));
		        	vo.setLocationName(localBody[1].toString()+" ("+localBody[2].toString()+")");
		        	locationsList.add(vo);
	        	}
	        }
		  
		return locationsList;
	}

 public CadreCommitteeVO getCadreVoterBthSerilNo(Long locationId,String houseNo,String gender){
	   List<Object[]> votersList = null;
	  CadreCommitteeVO returnVO = new CadreCommitteeVO();
	 try{
		 Long boothId =houseNo != null && houseNo.trim().length()>0?Long.valueOf(houseNo):0L;
		 List<Long> serialNoIdLists=new ArrayList<Long>();
		 List<Long> boothIdsList = new ArrayList<Long>(0);
		 Map<Long,Long> voterSerialNoMap = new HashMap<Long, Long>(0);
		 Map<Long,Map<Long,String>> cadreGenderMap = new HashMap<Long,Map<Long,String>>(0);
		
		 Map<Long,Long> boothCadreMap =new HashMap<Long,Long>();
		 Map<Long,Long> convenerBoothCadreMap =new HashMap<Long,Long>();
		 Map<Long,Long> otherSerialNoRangeIdsMap = new HashMap<Long, Long>(0);
		 boothIdsList.add(boothId);
		
		votersList = boothDAO.getVoterDetailsByBoothId(boothId);
		if(commonMethodsUtilService.isListOrSetValid(votersList)){
			for (Object[] param : votersList) {
				voterSerialNoMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
			}
		} 
		List<Object[]> boothCadreObjsLst = boothInchargeDAO.getActiveBoothMemeberDetails(boothId);
		if(commonMethodsUtilService.isListOrSetValid(boothCadreObjsLst)){
			for(Object[] cadreObj : boothCadreObjsLst){
				if(commonMethodsUtilService.getLongValueForObject(cadreObj[3]) == 2L){//memeber position 
					if(commonMethodsUtilService.getLongValueForObject(cadreObj[0]) >0L)
						boothCadreMap.put(commonMethodsUtilService.getLongValueForObject(cadreObj[1]), commonMethodsUtilService.getLongValueForObject(cadreObj[0]));
					else if(commonMethodsUtilService.getLongValueForObject(cadreObj[2])>0L)
						boothCadreMap.put(commonMethodsUtilService.getLongValueForObject(cadreObj[1]), commonMethodsUtilService.getLongValueForObject(cadreObj[2]));
					if(commonMethodsUtilService.getLongValueForObject(cadreObj[4]).longValue()>0L){
						 BoothInchargeCondition condition = boothInchargeConditionDAO.get(commonMethodsUtilService.getLongValueForObject(cadreObj[4]));
						Long min =condition.getMinValue();
						//Long max=condition.getMaxValue();						
						otherSerialNoRangeIdsMap.put(commonMethodsUtilService.getLongValueForObject(cadreObj[1]),min);
					}
				}else if(commonMethodsUtilService.getLongValueForObject(cadreObj[3]) == 1L){//convener position 
					if(commonMethodsUtilService.getLongValueForObject(cadreObj[0]) >0L)
						convenerBoothCadreMap.put(commonMethodsUtilService.getLongValueForObject(cadreObj[1]), commonMethodsUtilService.getLongValueForObject(cadreObj[0]));
					else if(commonMethodsUtilService.getLongValueForObject(cadreObj[2])>0L)
						convenerBoothCadreMap.put(commonMethodsUtilService.getLongValueForObject(cadreObj[1]), commonMethodsUtilService.getLongValueForObject(cadreObj[2]));
				}
			}
		}
		
		Long serialNoId = 0L;
		List<Object[]> tdpcadreIdObjsList = tdpCadreDAO.getCadreBasicDetailsByVoterIds(locationId, IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID,boothId,"OwnVoterId");
		List<Object[]> familyTdpcadreIdObjsList = tdpCadreDAO.getCadreBasicDetailsByVoterIds(locationId, IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID,boothId,"FamilyVoterId");
		
		if(!commonMethodsUtilService.isListOrSetValid(tdpcadreIdObjsList))
			tdpcadreIdObjsList = new ArrayList<Object[]>(0);
		if(commonMethodsUtilService.isListOrSetValid(familyTdpcadreIdObjsList))	
			tdpcadreIdObjsList.addAll(familyTdpcadreIdObjsList);
		
		if(tdpcadreIdObjsList != null && tdpcadreIdObjsList.size() >0){
			for(Object[] obj: tdpcadreIdObjsList){
				Long voterId = commonMethodsUtilService.getLongValueForObject(obj[5]);
				 gender = commonMethodsUtilService.getStringValueForObject(obj[7]);
				if(voterId==null || voterId.longValue()==0L)
					 voterId = commonMethodsUtilService.getLongValueForObject(obj[6]);
				serialNoId = commonMethodsUtilService.getLongValueForObject(obj[4]);
				gender =commonMethodsUtilService.getStringValueForObject(obj[7]);
				if(voterSerialNoMap.keySet().contains(voterId))
					serialNoIdLists.add(serialNoId);
				
				 Map<Long,String> genderMap = new HashMap<Long,String>();
				 genderMap.put(voterId, gender);
				 cadreGenderMap.put(commonMethodsUtilService.getLongValueForObject(obj[3]), genderMap);
			}
		}
		
		List<CadreCommitteeVO> rangeList  = new ArrayList<CadreCommitteeVO>();
		if(votersList !=null && votersList.size() > 0){
				int sizeList = (int) votersList.size() / 100;
		
				String valueSize =String.valueOf(votersList.size());
				Integer valueSizeVal=valueSize.length();
				Integer lenghLstIndx=valueSizeVal-2;
				String lastTwodigit="0";
				if(valueSizeVal>2)
					lastTwodigit = valueSize.substring(lenghLstIndx, valueSizeVal);
				Integer integerVal =Integer.parseInt(lastTwodigit);
				Integer remainder=100-integerVal;
				String cnvrtedBfrStr=String.valueOf(sizeList)+"00";
		Integer cnvrtedBfrVal=Integer.parseInt(cnvrtedBfrStr);
		int cnvrtedAftVal=cnvrtedBfrVal+integerVal+remainder;
		if(remainder == 100L)
			cnvrtedAftVal=cnvrtedBfrVal;
		
		int rangeCount = (int) cnvrtedAftVal / 100;
		
		//cheking is extra member required or not
		Object[] objArr = checkedIsExtraMeberRequiredOrNot(votersList);
		if (objArr[0].toString().equalsIgnoreCase("YES") && (votersList.size()%100) !=0) {
			rangeCount = rangeCount-1; 
		}
		if(commonMethodsUtilService.isListOrSetValid(votersList) && votersList.size()<100){
			rangeCount = 1;//1-votersList.size()
		}
		
				for(Long i=1l ; i<= rangeCount ; i++){
					String startRange =i+"00";
					Long maxRange=Long.parseLong(startRange);
					Long minRange =Long.parseLong(startRange)-99;
					//adding remaining member in last range .
					 if (objArr[0].toString().equalsIgnoreCase("YES")) {
						 if (rangeCount == i) {
							 maxRange = maxRange+Long.valueOf(objArr[1].toString());
						 }
					 } else {
						 if (rangeCount == i) {
							 maxRange = Long.valueOf(votersList.size());
						 }
					 
					 }
					
					String finalRange=minRange+" - "+maxRange;
					 if(votersList.size()<100)
						 finalRange=1+" - "+votersList.size();
					 
					CadreCommitteeVO cadreCommetteeVO=new CadreCommitteeVO();
					cadreCommetteeVO.setMinRange(minRange);
					cadreCommetteeVO.setMaxRange(maxRange);
					cadreCommetteeVO.setFinalRangeStr(finalRange);
					rangeList.add(cadreCommetteeVO);
				}
				if(commonMethodsUtilService.isListOrSetValid(rangeList)){
					for (Long id : voterSerialNoMap.keySet()) {
						
						if(commonMethodsUtilService.isMapValid(cadreGenderMap)){
							for (Long tdpCadreId : cadreGenderMap.keySet()) {
								Map<Long,String> genderMap = cadreGenderMap.get(tdpCadreId);
								Long serialNo = voterSerialNoMap.get(id);
								
								Long addedOtherMinRange = otherSerialNoRangeIdsMap.get(tdpCadreId);
								String gender1 = genderMap.get(id);
								if(gender1 != null && !gender1.isEmpty() && serialNoIdLists.contains(serialNo)){
									for (CadreCommitteeVO rangeVO : rangeList) {
										if((addedOtherMinRange == null || addedOtherMinRange.longValue()==0) && serialNo.longValue()>=rangeVO.getMinRange() && serialNo.longValue()<=rangeVO.getMaxRange()){
											rangeVO.setTotalCount(rangeVO.getTotalCount().longValue()+1L);
											if(gender1.equalsIgnoreCase("M") || gender1.equalsIgnoreCase("MALE")){
												rangeVO.setMaleCount(rangeVO.getMaleCount()+1L);
												if(boothCadreMap.get(tdpCadreId) !=null){
													rangeVO.setAlreadyRegistered("ADDED");
													rangeVO.setAddedCount(rangeVO.getAddedCount()+1L);
												}else if(convenerBoothCadreMap.get(tdpCadreId) !=null){
													rangeVO.setConvenerAddedCount(rangeVO.getConvenerAddedCount()+1L);
												}
											}else if(gender1.equalsIgnoreCase("F") || gender1.equalsIgnoreCase("FEMALE")){
												rangeVO.setFemaleCount(rangeVO.getFemaleCount()+1L);
												if(boothCadreMap.get(tdpCadreId) !=null){
													rangeVO.setAlreadyRegistered("ADDED");
													rangeVO.setAddedCount(rangeVO.getAddedCount()+1L);
												}else if(convenerBoothCadreMap.get(tdpCadreId) !=null){
													rangeVO.setConvenerAddedCount(rangeVO.getConvenerAddedCount()+1L);
												}
											}
										}else if(addedOtherMinRange != null && addedOtherMinRange.longValue()>0L){
											if(addedOtherMinRange>=rangeVO.getMinRange() && addedOtherMinRange <=rangeVO.getMaxRange()){
												if(boothCadreMap.get(tdpCadreId) !=null){
													rangeVO.setAlreadyRegistered("ADDED");
													rangeVO.setAddedCount(rangeVO.getAddedCount()+1L);
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
		   returnVO.getCasteList().addAll(rangeList);
 }catch(Exception e){
		 LOG.error("Exception raised in CadreCommitteeService of getCadreVoterBthSerilNo", e);
	 }
		return returnVO;
 }
 
 public Object[] checkedIsExtraMeberRequiredOrNot(List<Object[]> votersList) {
	 String status = "NO";
	 Object[] objArr = new Object[2];
	 try {
		 Long remainder = 0l;
		 if (votersList != null && votersList.size() > 0) {
			 Long noOfVoter = Long.valueOf(votersList.size());
			  if (noOfVoter > 100) {
				  remainder = noOfVoter%100;
			  } else {
				  remainder = noOfVoter;
			  }
			  if (remainder<50) {
				  status = "YES";
			  }
		 }
		 objArr[0] = status;
		 objArr[1] = remainder;
	 } catch (Exception e) {
		 LOG.error("Exception raised in checkedIsExtraMeberRequiredOrNot of getCadreVoterBthSerilNo", e);
	 }
	 return objArr;
	 
 }
 
 public CadreCommitteeVO getSerialNoAvailbleCadreRangeWise(Long mandalId, Long boothId,String range,String gender){
	
	 CadreCommitteeVO returnVO= new CadreCommitteeVO();
	 Long minRange=0l;
	 Long maxRange=0l;
	 if(!range.equalsIgnoreCase("0")){
		 range =  range.trim().replace(" ", "");
		 range =  range.trim().replace(" ", "");
		 String[] str=range.trim().split("-");
		 minRange=Long.parseLong(str[0].toString());
		 maxRange=Long.parseLong(str[1].toString());
	 }
	 try{
		 Map<Long,Long> voterSerialNoMap = new HashMap<Long, Long>(0);
		 
		 List<Object[]> votersList = boothDAO.getVoterDetailsByBoothId(boothId);
		 List<Long> boothIdsList = new ArrayList<Long>(0);
		 boothIdsList.add(boothId);
		 Long serialNo=0l;
		 if(commonMethodsUtilService.isListOrSetValid(votersList)){
			 
				for (Object[] param : votersList) {	
					serialNo=commonMethodsUtilService.getLongValueForObject(param[1]);	
					if(!range.trim().equalsIgnoreCase("0")){						
						if(serialNo.longValue()>=minRange && serialNo.longValue()<=maxRange){
							voterSerialNoMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));	
						}
					}else{
						voterSerialNoMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getLongValueForObject(param[1]));
					}
				}
		    }	
				List<Object[]> tdpcadreIdObjsList = tdpCadreDAO.getRangeWiseTdpCadreDtlsObjs(voterSerialNoMap.keySet(),"OwnVoterId");
				List<Object[]> familyTdpcadreIdObjsList = tdpCadreDAO.getRangeWiseTdpCadreDtlsObjs(voterSerialNoMap.keySet(),"FamilyVoterId");
					
				if(!commonMethodsUtilService.isListOrSetValid(tdpcadreIdObjsList))
					tdpcadreIdObjsList = new ArrayList<Object[]>(0);
				if(commonMethodsUtilService.isListOrSetValid(familyTdpcadreIdObjsList))	
					tdpcadreIdObjsList.addAll(familyTdpcadreIdObjsList);
				
					Map<Long,CadreCommitteeVO> cadreMap = new HashMap<Long, CadreCommitteeVO>(0);
					if(tdpcadreIdObjsList !=null && tdpcadreIdObjsList.size() > 0){
					 
					 for(Object[] Obj: tdpcadreIdObjsList){
						 CadreCommitteeVO finalVO = new CadreCommitteeVO();
						 
						 finalVO.setName(commonMethodsUtilService.getStringValueForObject(Obj[0]));
						 finalVO.setRelativeName(commonMethodsUtilService.getStringValueForObject(Obj[1]));
						 finalVO.setAge(commonMethodsUtilService.getStringValueForObject(Obj[2]));
						 finalVO.setGender(commonMethodsUtilService.getStringValueForObject(Obj[3]));
						 finalVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(Obj[4]));
						 finalVO.setCasteName(commonMethodsUtilService.getStringValueForObject(Obj[5]));
						 finalVO.setVoterId(commonMethodsUtilService.getLongValueForObject(Obj[6]));
						 finalVO.setImageURL(commonMethodsUtilService.getStringValueForObject(Obj[7]));
						 finalVO.setTehsil(commonMethodsUtilService.getStringValueForObject(Obj[8]));
						 finalVO.setPanchayat(commonMethodsUtilService.getStringValueForObject(Obj[9]));
						 finalVO.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(Obj[10]));
						 finalVO.setGender(commonMethodsUtilService.getStringValueForObject(Obj[3]));
						 finalVO.setMemberShipCardId(commonMethodsUtilService.getStringValueForObject(Obj[11]));
						 finalVO.setVoterCardNo(commonMethodsUtilService.getStringValueForObject(Obj[12]));
						 if(finalVO.getVoterCardNo() == null || finalVO.getVoterCardNo().length()==0){
							 finalVO.setVoterCardNo(commonMethodsUtilService.getStringValueForObject(Obj[13]));
							 finalVO.setVoterId(commonMethodsUtilService.getLongValueForObject(Obj[14]));
						 }
						 
						 finalVO.setType("Not Added");
						 finalVO.setIsDuplicate("");
						 finalVO.setSerialNo(voterSerialNoMap.get(finalVO.getVoterId()).toString());
						/* if(commonMethodsUtilService.getStringValueForObject(Obj[3]).equalsIgnoreCase(gender)){
							 returnVO.getCasteList().add(finalVO);
						 } else if (gender.equalsIgnoreCase("0")){
							 returnVO.getCasteList().add(finalVO);
						 }*/
						 
						  	if(commonMethodsUtilService.getStringValueForObject(Obj[3]).equalsIgnoreCase(gender)){
							  cadreMap.put(finalVO.getTdpCadreId(), finalVO);
							 } else if (gender.equalsIgnoreCase("0")){
								 cadreMap.put(finalVO.getTdpCadreId(), finalVO);
							 }
					 }
					 List<Object[]> boothCadreIdList = boothInchargeDAO.getCadreIdsForLocation(boothIdsList,0L);
						if(boothCadreIdList != null && boothCadreIdList.size() > 0l){
							List<String> addedSerialNoList = new ArrayList<String>(0);
							for(Object[] Obj: boothCadreIdList){
								 CadreCommitteeVO vo = cadreMap.get(commonMethodsUtilService.getLongValueForObject(Obj[0]));
								if(vo != null){
									 vo.setType("Added Member");
									 vo.setRoleName(commonMethodsUtilService.getStringValueForObject(Obj[7]));
									 vo.setBoothNumber(commonMethodsUtilService.getLongValueForObject(Obj[1]));
									 vo.setPanchayat(commonMethodsUtilService.getStringValueForObject(Obj[2]));
									 vo.setTehsilId(commonMethodsUtilService.getLongValueForObject(Obj[3]));
									 vo.setLocalElectionId(commonMethodsUtilService.getLongValueForObject(Obj[5]));
									 if(Obj[3] == null){
										 vo.setLocalElectionBody(commonMethodsUtilService.getStringValueForObject(Obj[6]));	 
									 }else{
										 vo.setTehsil(commonMethodsUtilService.getStringValueForObject(Obj[4]));
									 }
									 vo.setIsDuplicate("No");
									 if(addedSerialNoList.contains(vo.getSerialNo().trim())){
										 vo.setIsDuplicate("Yes");
									 }
									addedSerialNoList.add(vo.getSerialNo().trim());
								}
							}
						}
						
						if(commonMethodsUtilService.isMapValid(cadreMap)){
							 returnVO.getCasteList().addAll(cadreMap.values());
						}
				  }
			
		 }catch(Exception e){
			 LOG.error("Exception raised in CadreCommitteeService of getSerialNoAvailbleCadreRangeWise", e);
		 }
	 return returnVO;
	 }
 
 public List<CadreCommitteeVO> getMemberStatusDetails(List<Long> tdpCadreIds){
	 List<CadreCommitteeVO> finalList = new ArrayList<CadreCommitteeVO>();
	 try {
		 List<Long> npCandIds = null;
		 if(commonMethodsUtilService.isListOrSetValid(tdpCadreIds)){
			 npCandIds = nominationPostCandidateDAO.getNominatedPstCandidateIds(tdpCadreIds);
		 }
		 if(commonMethodsUtilService.isListOrSetValid(npCandIds)){
			 List<Object[]> memberDetails = nominatedPostDAO.getMemberStatusDetails(npCandIds);
			 if(commonMethodsUtilService.isListOrSetValid(memberDetails)){
				 for (Object[] param : memberDetails) {
					CadreCommitteeVO vo = new CadreCommitteeVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));//NPId
					vo.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(param[1]));//NPCandId
					vo.setCasteStateId(commonMethodsUtilService.getLongValueForObject(param[2]));//StatusId
					vo.setCasteName(commonMethodsUtilService.getStringValueForObject(param[3]));//Status
					vo.setCasteCategoryId(commonMethodsUtilService.getLongValueForObject(param[4]));//positionId
					vo.setCasteCategory(commonMethodsUtilService.getStringValueForObject(param[5]));//postion Name
					vo.setOccupation(commonMethodsUtilService.getStringValueForObject(param[6]));//DeptName
					vo.setRole(commonMethodsUtilService.getStringValueForObject(param[7]));//BoardName
					vo.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(param[8]));//BoardLevelId
					vo.setElectionType(commonMethodsUtilService.getStringValueForObject(param[9]));//BoardLevelName
					
					vo.setRoleName(commonMethodsUtilService.getStringValueForObject(param[10]));//StateName
					vo.setCadreName(commonMethodsUtilService.getStringValueForObject(param[11]));//DistrictName
					vo.setConstituency(commonMethodsUtilService.getStringValueForObject(param[12]));
					vo.setTehsil(commonMethodsUtilService.getStringValueForObject(param[13]));
					vo.setLocalElectionBody(commonMethodsUtilService.getStringValueForObject(param[14]));
					vo.setPanchayat(commonMethodsUtilService.getStringValueForObject(param[15]));
					vo.setRelativeName(commonMethodsUtilService.getStringValueForObject(param[16]));//WardName
					
					
					if(vo.getCadreName() != null && vo.getCadreName().length()>0)
						vo.setRoleName(vo.getCadreName());
					if(vo.getConstituency() != null && vo.getConstituency().length()>0)
						vo.setRoleName(vo.getConstituency());
					if(vo.getTehsil() != null && vo.getTehsil().length()>0)
						vo.setRoleName(vo.getTehsil());
					if(vo.getLocalElectionBody() != null && vo.getLocalElectionBody().length()>0)
						vo.setRoleName(vo.getLocalElectionBody());
					if(vo.getPanchayat() != null && vo.getPanchayat().length()>0)
						vo.setRoleName(vo.getPanchayat());
					if(vo.getRelativeName() != null && vo.getRelativeName().length()>0)
						vo.setRoleName(vo.getRelativeName());
					
					finalList.add(vo);
				}
			 }
		 }
		 
		
	} catch (Exception e) {
		LOG.error("Exception raised in CadreCommitteeService of getMemberStatusDetails", e);
	}
	 return finalList;
 }
 
 public CadreCommitteeVO getCommitteeCountDetailsByLevelId(Long constituencyId,List<Long> levelIds,List<Long> levelValues,List<Long> basicCommitteeIds,List<Long> cmiteEnrlmntYearIds){
	 CadreCommitteeVO finalVO = new CadreCommitteeVO();
	 try {
		 List<CadreCommitteeVO> rolesList = new ArrayList<CadreCommitteeVO>();
		List<Object[]> roleList = tdpRolesDAO.getRoles();
		if(commonMethodsUtilService.isListOrSetValid(roleList)){
			for (Object[] param : roleList) {
				CadreCommitteeVO vo = new CadreCommitteeVO();
				vo.setRoleId(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setRoleName(commonMethodsUtilService.getStringValueForObject(param[1]));
				rolesList.add(vo);
			}
		}
		List<Object[]> rolesWiseCountLst = tdpCommitteeRoleDAO.getTotalCommitteesByRole(constituencyId,levelIds,levelValues,basicCommitteeIds,cmiteEnrlmntYearIds);
		if(commonMethodsUtilService.isListOrSetValid(rolesWiseCountLst)){
			for (Object[] param : rolesWiseCountLst) {
				finalVO.setSubList(rolesList);
				CadreCommitteeVO rolesVO = getMatchedVOByRoleId(finalVO.getSubList(), commonMethodsUtilService.getLongValueForObject(param[0]));
				if(rolesVO != null){
					rolesVO.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}
		}
		List<Object[]> finalizedCountLst = tdpCommitteeMemberDAO.getFinilizedCommittesByRole(constituencyId,levelIds,levelValues,basicCommitteeIds,cmiteEnrlmntYearIds);
		if(commonMethodsUtilService.isListOrSetValid(finalizedCountLst)){
			for (Object[] param : finalizedCountLst) {
				finalVO.setSubList(rolesList);
				CadreCommitteeVO rolesVO = getMatchedVOByRoleId(finalVO.getSubList(), commonMethodsUtilService.getLongValueForObject(param[0]));
				if(rolesVO != null){
					rolesVO.setFinalizedCount(commonMethodsUtilService.getLongValueForObject(param[1]));
					rolesVO.setRemainingCount(rolesVO.getTotalCount()-rolesVO.getFinalizedCount());
				}
			}
		}
	} catch (Exception e) {
		LOG.error("Exception raised in CadreCommitteeService of getCommitteeCountDetailsByLevelId", e);
	}
	 return finalVO;
 }
 public CadreCommitteeVO getMatchedVOByRoleId(List<CadreCommitteeVO> rolesList,Long roleId)
	{
		CadreCommitteeVO returnVO = null;
		try {
			
			if(rolesList != null && rolesList.size()>0)
			{
				for (CadreCommitteeVO rolesVO : rolesList)
				{
					if(rolesVO.getRoleId().longValue() == roleId.longValue())
					{
						return rolesVO;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getMatchedVOByRoleId", e);
		}
		return returnVO;
	}
}