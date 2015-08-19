package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICadreCommitteeChangeDesignationsDAO;
import com.itgrids.partyanalyst.dao.ICadreCommitteeIncreasedPositionsDAO;
import com.itgrids.partyanalyst.dao.ICadreCommitteeRoleDAO;
import com.itgrids.partyanalyst.dao.ICadreIvrResponseDAO;
import com.itgrids.partyanalyst.dao.ICadreOtpDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreParticipatedElectionDAO;
import com.itgrids.partyanalyst.dao.ICadrePreviousRolesDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.ICommitteIvrDistrictDetailDAO;
import com.itgrids.partyanalyst.dao.ICommitteIvrTotalDetailDAO;
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
import com.itgrids.partyanalyst.dao.INewDistrictConstituencyDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPublicRepresentativeDAO;
import com.itgrids.partyanalyst.dao.IPublicRepresentativeTypeDAO;
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
import com.itgrids.partyanalyst.dto.AccessedPageLoginTimeVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeReportVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeRolesInfoVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.CadreIVRVO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CasteDetailsVO;
import com.itgrids.partyanalyst.dto.CommitteeApprovalVO;
import com.itgrids.partyanalyst.dto.CommitteeSummaryVO;
import com.itgrids.partyanalyst.dto.EventCreationVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.InviteesVO;
import com.itgrids.partyanalyst.dto.IvrOptionsVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.RolesVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.UserEventDetailsVO;
import com.itgrids.partyanalyst.dto.VO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.CadreCommitteeChangeDesignations;
import com.itgrids.partyanalyst.model.CadreCommitteeIncreasedPositions;
import com.itgrids.partyanalyst.model.CadreOtpDetails;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.model.Constituency;
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
import com.itgrids.partyanalyst.model.PublicRepresentativeType;
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
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.model.VoterAgeRange;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;

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

	public CadreCommitteeVO getCadreDetailsByTdpCadreId(Long tdpCadreId)
	{
		CadreCommitteeVO cadreCommitteeVO = null;
		SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd");
		try {
			TdpCadre tdpCadre = tdpCadreDAO.get(tdpCadreId);
			if(tdpCadre != null)
			{
				cadreCommitteeVO = new CadreCommitteeVO();
				
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
				
				cadreCommitteeVO.setPreviousRoles(getExistingRollsInfo(tdpCadreId));
				cadreCommitteeVO.setPreviousElections(getExistingCadreParticipationInfo(tdpCadreId));
				
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
					if(LocationType.equalsIgnoreCase(IConstants.PANCHAYAT))
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
		LocationWiseBoothDetailsVO vo = null;
		List<SelectOptionVO> locations = regionServiceDataImp.getAllMandalsByConstituencyID(constituencyId);
		List<Object[]> localBodies = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituency(constituencyId);
	        for(SelectOptionVO location:locations){
	        	vo = new LocationWiseBoothDetailsVO();
	        	vo.setLocationId(Long.valueOf("2"+location.getId()));
	        	vo.setLocationName(location.getName()+" Mandal");
	        	locationsList.add(vo);
	        }
	        for(Object[] localBodi:localBodies){
	        	if(((Long)localBodi[0]).longValue() != 20l &&  ((Long)localBodi[0]).longValue() != 124l && ((Long)localBodi[0]).longValue() != 119l){
		        	vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("1"+localBodi[0].toString()));
		        	vo.setLocationName(localBodi[1].toString());
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
	        		if(!(localBdyId.longValue() == 20l ||  localBdyId.longValue() == 124l || localBdyId.longValue() == 119l)){
	        		   localBodyIds.add(localBdyId);
	        		}
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
				committeeMembersList.add(memberVo);
			}
		}catch(Exception e){
			LOG.error("Exception raised in getAllCommitteeMembersInfoInALoc", e);
		}
		return returnVo;
	}
	public Long getMainCommitteeIdInALocation(Long levelId,Long levelValue){
		Long committeeId = null;
		try{
			List<Long> committeeIds = tdpCommitteeDAO.getMainCommittiesInALocation(levelId, levelValue);
			if(committeeIds.size() > 0){
				committeeId = committeeIds.get(0);
			}
		}catch(Exception e){
			LOG.error("Exception raised in getMainCommitteeIdInALocation", e);
		}
		return committeeId;
	}
	
	public LocationWiseBoothDetailsVO getMainCommitteeMembersInfo(Long levelId,Long levelValue){
		Long committeeId = getMainCommitteeIdInALocation(levelId,levelValue);
		if(committeeId != null){
			return getCommitteeMembersInfo(committeeId);
		}else{
			return new LocationWiseBoothDetailsVO();
		}
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
							
							Long tdpCommitteeId = getTdpCommittee(1l,tdpCommitteeLevelId,levelValue);
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
					Long tdpCommitteeId = getTdpCommittee(tdpBasicCommitteeId,tdpCommitteeLevelId,levelValue);
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
			boolean isEligible = true;
			boolean isExist = false;
			Long oldCommitteeId = null;
			List<Object[]> cadreCommitteeInfo = tdpCommitteeMemberDAO.getMemberInfo(tdpCadreId);
			if(cadreCommitteeInfo != null && cadreCommitteeInfo.size()>0)
			{
				isExist = true;
			}
			
			TdpCommitteeRole tdpCommitteeRole = tdpCommitteeRoleDAO.get(tdpCommitteeRoleId);
			
			Long maxMembers = tdpCommitteeRole.getMaxMembers();
			Set<Long> committeeRoleIds = new HashSet<Long>();
			committeeRoleIds.add(tdpCommitteeRoleId);
			
			List<Object[]> existringDtails = tdpCommitteeMemberDAO.getRoleWiseAllocatedMembersCount(committeeRoleIds);
		
			if(existringDtails != null && existringDtails.size()>0)
			{
				for (Object[] role : existringDtails) 
				{
					Long count = role[0] != null ? Long.valueOf(role[0].toString()):0L;
					if(maxMembers.longValue() > 0 && (count.longValue() >= maxMembers.longValue()) )
					{
						isEligible = false;
					}
				}
			}
			
			if(isEligible)				
			{
				DateUtilService dateUtilService = new DateUtilService();
				
				TdpCommitteeMember tdpCommitteeMember = null;
				if(isExist)
				{
					List<TdpCommitteeMember> tdpCommitteeMemberList = tdpCommitteeMemberDAO.getTdpCommitteeMemberByTdpCadreId(tdpCadreId);
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
				if(tdpCommittee.getStartedDate() == null){
					tdpCommittee.setStartedDate(dateUtilService.getCurrentDateAndTime());
					tdpCommitteeDAO.save(tdpCommittee);
				}
				if(oldCommitteeId != null && oldCommitteeId.longValue() > 0){
					updateCommitteeStartedStatus(oldCommitteeId);
				}
				status.setMessage(" Cadre Added To Committee Successfully... ");
				status.setResultCode(0);
			}
			else
			{
				status.setMessage(" Max Members are already Added for This Position... ");
				status.setResultCode(2);
			}
			
		/*} catch (Exception e) {
			status.setMessage(" Error Occured While Updating Details... ");
			status.setResultCode(1);
			LOG.error("Exception raised in saveCadreCommitteDetails", e);
		}*/
		return status;
	}
	
	public void updateCommitteeStartedStatus(Long tdpCommitteId){
		Long membersCnt = tdpCommitteeMemberDAO.getCommitteMembers(tdpCommitteId);
		if(membersCnt == null || membersCnt == 0)
		{
			TdpCommittee tdpCommittee = tdpCommitteeDAO.get(tdpCommitteId);
			tdpCommittee.setStartedDate(null);
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
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,Long fromAge,Long toAge,String houseNo,String gender,int startIndex,int maxIndex)
	{
		CadreCommitteeVO cadreCommitteeVO = new CadreCommitteeVO();
		try {
			
			TdpCadreVO tdpCadreVO = cadreDetailsService.searchTdpCadreDetailsBySearchCriteriaForCommitte(locationLevel,locationId, searchName,memberShipCardNo, 
					voterCardNo, trNumber, mobileNo,casteStateId,casteCategory,fromAge,toAge,houseNo,gender,startIndex,maxIndex);
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
						cadreCommitteeList.add(committeeVO);
					}
					if(maxIndex != 0)
					cadreCommitteeList.get(0).setMobileType(tdpCadreVOList.get(0).getTotalCount().toString());
				}
				
				if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
				{
					setCurrentDesignation(cadreCommitteeList,tdpCadreIdsList);
					setCurrentElectrolInfo(cadreCommitteeList,tdpCadreIdsList);
				}
				cadreCommitteeVO.setPreviousRoles(cadreCommitteeList);
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in searchTdpCadreDetailsBySearchCriteriaForCadreCommitte", e);
		}
		return cadreCommitteeVO;
	}
	
	public void setCurrentDesignation(List<CadreCommitteeVO> cadreCommitteeList,List<Long> tdpCadreIdsList){
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
		List<Object[]> tdpCommitteeMemberList = tdpCommitteeElectrolsDAO.getTdpCommitteeElectrolsForTdpCadreIdList(tdpCadreIdsList);
		
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
	
	public CadreCommitteeReportVO getCommitteeDetailsByLocation(String state,List<Long> levelIds,String startDateString,String endDateString,Long userId,String accessType,Long accessValue){
		
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
					
					if(newDistrictsList!=null && newDistrictsList.size()>0){
						for(Object[] obj:newDistrictsList){
							districtIds.add(Long.valueOf(obj[0].toString()));
						}
					}
				}
			}
			
			
			Date startDate=null;
			Date endDate=null;
			
			if(startDateString !=null && endDateString !=null){
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				 startDate = sdf.parse(startDateString);
				 endDate=sdf.parse(endDateString);
			}
			
			
			if((!accessType.trim().equalsIgnoreCase("MP") || levelIds.contains(6l) || levelIds.contains(8l) ) || levelIds.contains(11l) || levelIds.contains(10l)){
				Long committeeCnt =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,levelIds,districtIds,assemblyIds,locationLevelValues);
				//0count ,1 isCommitteeConfirmed,2.tdpCommitteeLevelId,3.tdpBasicCommitteeId
				List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state,levelIds,startDate,endDate,districtIds,assemblyIds,locationLevelValues);
				
				if(committeeCntDtls !=null && committeeCntDtls.size() > 0){				
					for (Object[] objects : committeeCntDtls) {
										
						if(Long.valueOf(objects[1].toString())==1l)
							completedMainCommittees = completedMainCommittees+(Long)objects[0];
						else if(Long.valueOf(objects[1].toString()) == 2l)
							completedAfflCommittees=completedAfflCommittees+(Long)objects[0];	
					}
				}
					 
				cadreCommitteeReportVO.setMainCommittees(0l);
				cadreCommitteeReportVO.setAfflCommittees(0l);
				 List<Object[]> startedCount=tdpCommitteeMemberDAO.getStartedCommitteesCountByLocation(state, levelIds,startDate,endDate,districtIds,assemblyIds,locationLevelValues);
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
						
					
			  Long memberscount= tdpCommitteeMemberDAO.getMembersCountByLocation(state, levelIds,startDate,endDate,districtIds,assemblyIds,locationLevelValues);				
					
			  cadreCommitteeReportVO.setMembersCount(memberscount != null ? memberscount : 0l);//totalMembers				
					
			  cadreCommitteeReportVO.setCommitteesCount(committeeCnt);//Total Committes count.
			  if(userId.longValue() == 1 && (levelIds.contains(6l) || levelIds.contains(8l)))
			  {
				   CadreIVRVO committeeSummaryVO = new CadreIVRVO();
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
					  Long cmtCnt =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,lvlIds,districtIds,null,lctnsIds);
					  committeesCount = committeesCount + cmtCnt;
					  
					  Long memberscount= tdpCommitteeMemberDAO.getMembersCountByLocation(state, lvlIds,startDate,endDate,districtIds,null,lctnsIds);
					  memsCount = memsCount + memberscount;
					  
					  List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state,lvlIds,startDate,endDate,districtIds,null,lctnsIds);
						
						if(committeeCntDtls !=null && committeeCntDtls.size() > 0){				
							for (Object[] objects : committeeCntDtls) {
												
								if(Long.valueOf(objects[1].toString())==1l)
									completedMainCommittees = completedMainCommittees+(Long)objects[0];
								else if(Long.valueOf(objects[1].toString()) == 2l)
									completedAfflCommittees=completedAfflCommittees+(Long)objects[0];	
							}
						}
						
						List<Object[]> startedCount=tdpCommitteeMemberDAO.getStartedCommitteesCountByLocation(state, lvlIds,startDate,endDate,districtIds,null,lctnsIds);
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
					  Long cmtCnt =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,lvlIds,districtIds,null,lctnsIds);
					  committeesCount = committeesCount + cmtCnt;
					  
					  Long memberscount= tdpCommitteeMemberDAO.getMembersCountByLocation(state, lvlIds,startDate,endDate,districtIds,null,lctnsIds);
					  memsCount = memsCount + memberscount;
					  
					  List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state,lvlIds,startDate,endDate,districtIds,null,lctnsIds);
						
						if(committeeCntDtls !=null && committeeCntDtls.size() > 0){				
							for (Object[] objects : committeeCntDtls) {
												
								if(Long.valueOf(objects[1].toString())==1l)
									completedMainCommittees = completedMainCommittees+(Long)objects[0];
								else if(Long.valueOf(objects[1].toString()) == 2l)
									completedAfflCommittees=completedAfflCommittees+(Long)objects[0];	
							}
						}
						
						List<Object[]> startedCount=tdpCommitteeMemberDAO.getStartedCommitteesCountByLocation(state, lvlIds,startDate,endDate,districtIds,null,lctnsIds);
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
					  Long cmtCnt =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,lvlIds,districtIds,null,lctnsIds);
					  committeesCount = committeesCount + cmtCnt;
					  
					  Long memberscount= tdpCommitteeMemberDAO.getMembersCountByLocation(state, lvlIds,startDate,endDate,districtIds,null,lctnsIds);
					  memsCount = memsCount + memberscount;
					  
					  List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state,lvlIds,startDate,endDate,districtIds,null,lctnsIds);
						
						if(committeeCntDtls !=null && committeeCntDtls.size() > 0){				
							for (Object[] objects : committeeCntDtls) {
												
								if(Long.valueOf(objects[1].toString())==1l)
									completedMainCommittees = completedMainCommittees+(Long)objects[0];
								else if(Long.valueOf(objects[1].toString()) == 2l)
									completedAfflCommittees=completedAfflCommittees+(Long)objects[0];	
							}
						}
					  
						List<Object[]> startedCount=tdpCommitteeMemberDAO.getStartedCommitteesCountByLocation(state, lvlIds,startDate,endDate,districtIds,null,lctnsIds);
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
					List<Long> committeeIds = tdpCommitteeDAO.getMainCommittiesInALocation(levelId, levelValue);
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
	public CadreCommitteeReportVO getTotalCommitteeDetailsByLocation(String state,Long userId,String accessType,Long accessValue){
		
		Long totalCompletedCommittees=0l;
		Long totalStartedCommittees=0l;
		CadreCommitteeReportVO cadreCommitteeReportVO =new CadreCommitteeReportVO();
		try{
			List<Long> districtIds = new ArrayList<Long>();
			List<Long> assemblyIds = new ArrayList<Long>();
			List<Long> locationLevelValues = new ArrayList<Long>();
			
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
				Long totalCommitteeCntDtls =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,null,districtIds,assemblyIds,null);		
				cadreCommitteeReportVO.setTotalCommittees(totalCommitteeCntDtls);
				
				List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state,null,null,null,districtIds,assemblyIds,null);
				
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
					Long totalCommitteeCntDtls =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,levelIds,districtIds,assemblyIds,null);		
					cadreCommitteeReportVO.setTotalCommittees(totalCommitteeCntDtls);
					
					List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state,levelIds,null,null,districtIds,assemblyIds,null);
					
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
							  Long cmtCnt =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,lvlIds,districtIds,null,lctnsIds);
							  committeesCount = committeesCount + cmtCnt;
							  
							
							  List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state, levelIds, null, null, districtIds, assemblyIds, lctnsIds);
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
							  Long cmtCnt =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,lvlIds,districtIds,null,lctnsIds);
							  committeesCount = committeesCount + cmtCnt;
							  
							  List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state,lvlIds,null,null,districtIds,null,lctnsIds);
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
							  Long cmtCnt =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,lvlIds,districtIds,null,lctnsIds);
							  committeesCount = committeesCount + cmtCnt;
							  
							   List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state,lvlIds,null,null,districtIds,null,lctnsIds);
								
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
				  
				  Long committeeCnt =tdpCommitteeDAO.getTotalCommitteesCountByLocation(state,ruralLvlIds,districtIds,assemblyIds,null);
				  committeesCount = committeesCount + committeeCnt;
				  //0count ,1 isCommitteeConfirmed,2.tdpCommitteeLevelId,3.tdpBasicCommitteeId
				  List<Object[]> committeeCntDtls =tdpCommitteeDAO.getTotalCompletedCommitteesCountByLocation(state,ruralLvlIds,null,null,districtIds,assemblyIds,null);
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
			String status = tdpCommitteeRoleDAO.getCommitteeStatus(tdpCommitteeRoleId);
			if(status.equalsIgnoreCase("Y")){
				return " This Committee Is Already Confirmed, You Cannot Add Or Update Committee Members Info ";
			}
			TdpCommitteeRole tdpCommitteeRole = tdpCommitteeRoleDAO.get(tdpCommitteeRoleId);
			
			Long maxMembers = tdpCommitteeRole.getMaxMembers();
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
		} catch (Exception e) {
			LOG.error("Exception raised in checkIsVacancyForDesignation method"+e);
			return " ";
		}
		
		return isEligible;
	}
	public List<CadreCommitteeMemberVO> getCommitteeDetailsByStatus(Long basicCommitteeTypeId,String status,Long levelId,String accessValue)
	{
		List<CadreCommitteeMemberVO> resultList = new ArrayList<CadreCommitteeMemberVO>();
		List<Long> levelIds = new ArrayList<Long>();
		//List<CadreCommitteeMemberVO> toRemove = new ArrayList<CadreCommitteeMemberVO>();
		Long constituencyId=Long.parseLong(accessValue);
		List<Object[]> list = new ArrayList<Object[]>();
		try{
			
		
			if(levelId == 2)
			{
				levelIds.add(6l);// Village/Ward
				levelIds.add(8l);
			list = tdpCommitteeDAO.getLocationByTypeIdAndLevel(levelIds,basicCommitteeTypeId,constituencyId,status);
			}
			
			else if(levelId == 10 || levelId == 11)
			{
				List<Long> locationIds = new ArrayList<Long>();
				locationIds.add(new Long(accessValue));
				list = tdpCommitteeDAO.getLocationsByTypeIdAndLevel(levelId,basicCommitteeTypeId,locationIds,status);
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
						list.addAll(tdpCommitteeDAO.getLocationsByTypeIdAndLevel(7l,basicCommitteeTypeId,muncipalIds,status));
					}
					if(mandalIds.size() > 0){
						list.addAll(tdpCommitteeDAO.getLocationsByTypeIdAndLevel(5l,basicCommitteeTypeId,mandalIds,status));
					}
					if(divisionIds.size() > 0){
						list.addAll(tdpCommitteeDAO.getLocationsByTypeIdAndLevel(9l,basicCommitteeTypeId,divisionIds,status));
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
					List<Object[]> membersList = tdpCommitteeMemberDAO.getComitteeMembersByCommiteTypeAndLocation(level,levelValuesMap.get(level),basicCommitteeTypeId,status);
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
	public List<CadreCommitteeMemberVO> getCommitteeMemberDetails(Long basicCommitteeTypeId,Long locationId,Long levelId,String status)
	{
		List<CadreCommitteeMemberVO> resultList = new ArrayList<CadreCommitteeMemberVO>();
		List<Long> levelIds = new ArrayList<Long>();
		try{
			List<Object[]> membersList = tdpCommitteeMemberDAO.getComitteeMembersInfoByCommiteTypeAndLocation(levelId,locationId,basicCommitteeTypeId,status);
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
	public List<CadreCommitteeMemberVO> setCommitteConfirmation(Long basicCommitteeTypeId,Long locationId,Long levelId)
	{
		List<CadreCommitteeMemberVO> resultList = new ArrayList<CadreCommitteeMemberVO>();
		try{
			DateUtilService date = new DateUtilService();
			
			List<Long> tdpcommitteIds = tdpCommitteeMemberDAO.getTdpCommitteIds(levelId,locationId,basicCommitteeTypeId);
			for(Long id : tdpcommitteIds)
			{
			CadreCommitteeMemberVO vo = new CadreCommitteeMemberVO();
			TdpCommittee tdpCommittee = tdpCommitteeDAO.get(id);
			tdpCommittee.setCompletedDate(date.getCurrentDateAndTime());
			tdpCommittee.setIsCommitteeConfirmed("Y");
			tdpCommitteeDAO.save(tdpCommittee);
			vo.setStatus("Updated");
			resultList.add(vo);	
			}
		
		}
		catch(Exception e)
		{
			LOG.error("Exception raised in setCommitteConfirmation", e);	
		}
		return resultList;
	}
	public List<CadreCommitteeMemberVO> deleteCadreRole(Long tdpCommitteeMemberId)
	{
		List<CadreCommitteeMemberVO> resultList = new ArrayList<CadreCommitteeMemberVO>();
		try{
			CadreCommitteeMemberVO vo = new CadreCommitteeMemberVO();
		
			List<Object[]> list = tdpCommitteeMemberDAO.getCommitteStatusAndId(tdpCommitteeMemberId);
			if(list != null)
			{
				Object[] params = list.get(0);
				String status = params[0].toString();
				Long tdpCommitteId = new Long(params[1].toString());
				if(!status.equalsIgnoreCase("Y"))
				{
					Integer val = tdpCommitteeMemberDAO.deleteCadreRole(tdpCommitteeMemberId);
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
	
	public List<CommitteeSummaryVO> getSummaryDetails(String accessValue)
	{
		List<CommitteeSummaryVO> returnList = null;
		try {
			
			Long constituencyId=Long.parseLong(accessValue);
			List<Object[]> valuesList = tdpCommitteeDAO.getLocationWiseVillageDetails(constituencyId);
			List<Object[]> allStartedList = tdpCommitteeDAO.getLocationWiseVillageStartedDetails(constituencyId);
			Map<Long,CommitteeSummaryVO> returnMap = new HashMap<Long,CommitteeSummaryVO>();
			CommitteeSummaryVO mainVO = new CommitteeSummaryVO();
			 getVillageLvlInfo( valuesList,allStartedList,returnMap,mainVO);
			 returnList = new ArrayList<CommitteeSummaryVO>(returnMap.values());
			if(returnList.size() > 0){
				CommitteeSummaryVO vo = returnList.get(0);
				vo.setMainComitteesConformed(mainVO.getMainComitteesConformed());
				vo.setMainComittees(mainVO.getMainComittees());
				vo.setStartedCount(mainVO.getStartedCount());
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
				if(count[2].toString().equalsIgnoreCase("Y")){
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
				if(count[2].toString().equalsIgnoreCase("Y")){
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
	public List<CommitteeSummaryVO> gettingMandalAndMuncipalAndDivisonSummary(String accessValue)
	{	
		List<CommitteeSummaryVO> returnList = null;
		try{
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
				List<Object[]> valuesList = tdpCommitteeDAO. getLocationWiseMandalDetails(mandalIds,5l);
				List<Object[]> allStartedList = tdpCommitteeDAO.getLocationWiseMandalStartedDetails(mandalIds,5l);
				getVillageLvlInfo( valuesList,allStartedList,returnMap,mainVO); 
			}
			if(muncipalIds.size() > 0){
				List<Object[]> valuesList = tdpCommitteeDAO. getLocationWiseMandalDetails(muncipalIds,7l);
				List<Object[]> allStartedList = tdpCommitteeDAO.getLocationWiseMandalStartedDetails(muncipalIds,7l);
				getVillageLvlInfo( valuesList,allStartedList,returnMap,mainVO); 
			}
			if(divisionIds.size() > 0){
				List<Object[]> valuesList = tdpCommitteeDAO. getLocationWiseMandalDetails(divisionIds,9l);
				List<Object[]> allStartedList = tdpCommitteeDAO.getLocationWiseMandalStartedDetails(divisionIds,9l);
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
	public List<CommitteeSummaryVO> getCommitteeSummaryInfoByUserAccess(Long accessValue,String accessType)
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
			
			List<Object[]> valuesList = tdpCommitteeDAO. getLocationWiseMandalDetails(locationIds,locationLevelId);
			List<Object[]> allStartedList = tdpCommitteeDAO.getLocationWiseMandalStartedDetails(locationIds,locationLevelId);
			Map<Long,CommitteeSummaryVO> returnMap = new HashMap<Long,CommitteeSummaryVO>();
			CommitteeSummaryVO mainVO = new CommitteeSummaryVO();
			getVillageLvlInfo( valuesList,allStartedList,returnMap,mainVO);
			 returnList = new ArrayList<CommitteeSummaryVO>(returnMap.values());
			if(returnList.size() > 0){
				CommitteeSummaryVO vo = returnList.get(0);
				vo.setMainComitteesConformed(mainVO.getMainComitteesConformed());
				vo.setMainComittees(mainVO.getMainComittees());
				vo.setStartedCount(mainVO.getStartedCount());
				vo.setLocationId(locationLevelId);
				vo.setLocationName(getLocationName(locationLevelId, accessValue));
				
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
	
	public List<CommitteeSummaryVO> getDistrictWiseCommittesSummary(String state,String startDate, String endDate,Long userId,String accessType, Long accessValue,String mandalCheck, String villageCheck,String districtCommCheck){
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
			List<Object[]> memResLst = tdpCommitteeMemberDAO.membersCountDistrictWise(mandalMunciDivisionIds, stDate, edDate, distIds);
			List<Object[]> ttlList = tdpCommitteeDAO.getCommitteesCountByDistrictIdAndLevel(distIds, mandalMunciDivisionIds);
			pushResultDistrictWiseMemsCount("munci", memResLst, fnlLst);
			pushTotalCountsForDistrict("munci", ttlList, fnlLst);
			}
			
			List<Long> villageWardIds = new ArrayList<Long>();
			villageWardIds.add(6l);
			villageWardIds.add(8l);
			if(villageCheck.equalsIgnoreCase("true")){
			List<Object[]> memResLstVill = tdpCommitteeMemberDAO.membersCountDistrictWise(villageWardIds, stDate, edDate, distIds);
			List<Object[]> ttlListVill = tdpCommitteeDAO.getCommitteesCountByDistrictIdAndLevel(distIds, villageWardIds);
			pushResultDistrictWiseMemsCount("village", memResLstVill, fnlLst);
			pushTotalCountsForDistrict("village", ttlListVill, fnlLst);
			}
			
			
			List<Long> districtCommIds = new ArrayList<Long>();
			districtCommIds.add(11l);			
			if(districtCommCheck.equalsIgnoreCase("true")){
			List<Object[]> memResLstVill = tdpCommitteeMemberDAO.membersCountDistrictWise(districtCommIds, stDate, edDate, distIds);
			List<Object[]> ttlListVill = tdpCommitteeDAO.getCommitteesCountByDistrictIdAndLevel(distIds, districtCommIds);
			pushResultDistrictWiseMemsCount("district", memResLstVill, fnlLst);
			pushTotalCountsForDistrict("district", ttlListVill, fnlLst);
			}
			if(mandalCheck.equalsIgnoreCase("true")){
			List<Object[]> stResLst = tdpCommitteeDAO.committeesCountByDistrict(mandalMunciDivisionIds, stDate, edDate, "started", distIds);
			List<Object[]> endResLst = tdpCommitteeDAO.committeesCountByDistrict(mandalMunciDivisionIds, stDate, edDate, "completed", distIds);
			pushResultDistrictWise("munci", stResLst, fnlLst, "start");
			pushResultDistrictWise("munci", endResLst, fnlLst, "completed");
			}
			if(villageCheck.equalsIgnoreCase("true")){
			List<Object[]> stResLstVill = tdpCommitteeDAO.committeesCountByDistrict(villageWardIds, stDate, edDate, "started", distIds);
			List<Object[]> endResLstVill = tdpCommitteeDAO.committeesCountByDistrict(villageWardIds, stDate, edDate, "completed", distIds);
			pushResultDistrictWise("village", stResLstVill, fnlLst, "start");
			pushResultDistrictWise("village", endResLstVill, fnlLst, "completed");		
			}
						
			if(districtCommCheck.equalsIgnoreCase("true")){
			List<Object[]> stResLst = tdpCommitteeDAO.committeesCountByDistrict(districtCommIds, stDate, edDate, "started", distIds);
			List<Object[]> endResLst = tdpCommitteeDAO.committeesCountByDistrict(districtCommIds, stDate, edDate, "completed", distIds);
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
			if(villageCheck.equalsIgnoreCase("true") && userId.longValue() == 1){
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
						List<IvrOptionsVO> villageIVRDetails = ivrDetailsVO.getOptionsList();
						List<IvrOptionsVO> wardIVRDetails = ivrDetailsVO.getOptionsList1();
						
						int villageListLength = villageIVRDetails.size();
						int wardListLength = wardIVRDetails.size();
						
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
					temp.getDistrictCommVO().setYouvathaStarted(1L);
				}else{
					temp.getDistrictCommVO().setYouvathaCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 3l){
				isConsidered = true;
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setMahilaStarted(1L);
				}else{
					temp.getDistrictCommVO().setMahilaCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 4l){
				isConsidered = true;
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setRythuStarted(1L);
				}else{
					temp.getDistrictCommVO().setRythuCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 5l){
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setTntucStarted(1L);
				}else{
					temp.getDistrictCommVO().setTntucCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 6l){
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setBcCellStarted(1L);
				}else{
					temp.getDistrictCommVO().setBcCellCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 7l){
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setScCellStarted(1L);
				}else{
					temp.getDistrictCommVO().setScCellCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 8l){
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setStCellStarted(1L);
				}else{
					temp.getDistrictCommVO().setStCellCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 9l){
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setMinorityStarted(1L);
				}else{
					temp.getDistrictCommVO().setMinorityCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 10l){
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setLegalCellStarted(1L);
				}else{
					temp.getDistrictCommVO().setLegalCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 11l){
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setTnsfStarted(1L);
				}else{
					temp.getDistrictCommVO().setTnsfCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 12l){
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setCommercialStarted(1L);
				}else{
					temp.getDistrictCommVO().setCommercialCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 13l){
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setCulturalStarted(1L);
				}else{
					temp.getDistrictCommVO().setCulturalCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 14l){
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setTnusStarted(1L);
				}else{
					temp.getDistrictCommVO().setTnusCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 15l){
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setTsnvStarted(1L);
				}else{
					temp.getDistrictCommVO().setTsnvCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 16l){
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setDoctorStarted(1L);
				}else{
					temp.getDistrictCommVO().setDoctorCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 17l){
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setTradeStarted(1L);
				}else{
					temp.getDistrictCommVO().setTradeCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 18l){
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setCristianStarted(1L);
				}else{
					temp.getDistrictCommVO().setCristianCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 19l){
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setRakshaVedikaStarted(1L);
				}else{
					temp.getDistrictCommVO().setRakshaVedikaCmpltd(1L);
				}
			}
			
			else if(committeeId.longValue() == 20l){
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setKalluGeethaStarted(1L);
				}else{
					temp.getDistrictCommVO().setKalluGeethaCmpltd(1L);
				}
			}
			else if(committeeId.longValue() == 21l){
				if(resType.equalsIgnoreCase("start")){
					temp.getDistrictCommVO().setChenethaStarted(1L);
				}else{
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
	
	public List<CadreCommitteeReportVO> getStartedAffliCommitteesCountByLocation(String state,List<Long> levelIds,String startDateStr,String endDateStr,String accessType,Long accessValue,Long userId,String committeeType){
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
			
			List<Object[]>  startedCount = null;
			if(committeeType.equalsIgnoreCase("started")){
				startedCount=tdpCommitteeDAO.getStartedAffliCommitteesCountByLocation(state, levelIds,startDate,endDate,districtIds,assemblyIds,locationLevelValues);
			}
			else
			{
				startedCount=tdpCommitteeDAO.getCompletedAffliCommitteesCountByLocation(state, levelIds,startDate,endDate,districtIds,assemblyIds,locationLevelValues);
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
	
	
	public List<CadreCommitteeReportVO> getMembersRangeCountByLocation(String state,List<Long> levelIds,Long committeeId,String startDateStr,String endDateStr,String accessType,Long accessValue,Long userId,String committeeType){
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
			 
			List<Object[]> membersCount = null; 
			if(committeeType.equalsIgnoreCase("started")){
				membersCount = tdpCommitteeMemberDAO.getStartedCommitteesMembersCountByLocation(state, levelIds,committeeId,startDate,endDate,districtIds,assemblyIds,locationLevelValues);
			}else{
				membersCount = tdpCommitteeMemberDAO.getMembersCountInCommitteeByLocation(state, levelIds,committeeId,startDate,endDate,districtIds,assemblyIds,locationLevelValues);
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
						else if(Long.valueOf(objects[0].toString()) > 6L )
						{
							vo.setMembersCount3(vo.getMembersCount3() + 1);
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
			List<Long> committeeIds = tdpCommitteeDAO.getMainCommittiesInALocation(levelId, levelValue);
			if(committeeIds.size() > 0){
				committeeId = committeeIds.get(0);
			}
		}catch(Exception e){
			LOG.error("Exception raised in gettingCommitteeIdForMainCommittee", e);
		}
		return committeeId;
	}
	
	
	   public BasicVO getAccessLocationValuesByState(String accessType,Long accessValue,Long stateId,Long userId)
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
	
	public List<CommitteeSummaryVO> getConstituencyWiseCommittesSummary(String state,String startDate, String endDate,Long userId, String accessType,Long accessValue,String mandalCheck,String villageCheck){  
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
						completedRythuCommitteesAffCountMap,startedOthersCommitteesAffCountMap,completedOthersCommitteesAffCountMap,basicCmmty);
			if(villageCheck.equalsIgnoreCase("true"))
			{
			
			List<Long> villageWardIds = new ArrayList<Long>();
			villageWardIds.add(6l);
			villageWardIds.add(8l);
			
			List<Object[]> memResLstVill = tdpCommitteeMemberDAO.membersCountConstituencyWise(villageWardIds, stDate, edDate, constiIds);
			List<Object[]> ttlV = tdpCommitteeDAO.getCommitteesCountByConstituencyIdAndLevel(constiIds, villageWardIds);
			pushResultConstituencyWiseMemsCount("village", memResLstVill, constiLst);
			pushTotalCountsForConstituency("village", ttlV, constiLst);
			
			
			/*List<Object[]> stResLst = tdpCommitteeDAO.committeesCountByConstituency(mandalMunciDivisionIds, stDate, edDate, "started", constiIds);
			List<Object[]> endResLst = tdpCommitteeDAO.committeesCountByConstituency(mandalMunciDivisionIds, stDate, edDate, "completed", constiIds);
			pushResultConstiWise("munci", stResLst, constiLst, "start");
			pushResultConstiWise("munci", endResLst, constiLst, "completed");*/
			
			List<Object[]> stResLstVill = tdpCommitteeDAO.committeesCountByConstituency(villageWardIds, stDate, edDate, "started", constiIds);
			List<Object[]> endResLstVill = tdpCommitteeDAO.committeesCountByConstituency(villageWardIds, stDate, edDate, "completed", constiIds);
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
	
	public List<CommitteeSummaryVO> getConstituencyWiseCommittesSummaryForDistrict(String state,String startDate, String endDate,Long userId, String accessType,Long accessValue,String mandalCheck,String villageCheck){  
		LOG.debug("Entered Into getConstituencyWiseCommittesSummaryForDistrict");
		List<CommitteeSummaryVO> constiLst = new ArrayList<CommitteeSummaryVO>();
		try{
			Long stateTypeId = 1l;
			String accessState = "ALL";
			List<Object[]> constituencysList = null;
			List<Long> districtIds = new ArrayList<Long>();
			List<Long> constiIds = new ArrayList<Long>();
			List<Long> constiIds1 = new ArrayList<Long>();
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
				startedOthersCommitteesAffCountMap,completedOthersCommitteesAffCountMap,basicCmmty);
	if(villageCheck.equalsIgnoreCase("true"))
	{
	
	List<Long> villageWardIds = new ArrayList<Long>();
	villageWardIds.add(6l);
	villageWardIds.add(8l);
	
	List<Object[]> memResLstVill = tdpCommitteeMemberDAO.membersCountConstituencyWise(villageWardIds, stDate, edDate, constiIds);
	List<Object[]> ttlV = tdpCommitteeDAO.getCommitteesCountByConstituencyIdAndLevel(constiIds, villageWardIds);
	pushResultConstituencyWiseMemsCount("village", memResLstVill, constiLst);
	pushTotalCountsForConstituency("village", ttlV, constiLst);
	
	
	/*List<Object[]> stResLst = tdpCommitteeDAO.committeesCountByConstituency(mandalMunciDivisionIds, stDate, edDate, "started", constiIds);
	List<Object[]> endResLst = tdpCommitteeDAO.committeesCountByConstituency(mandalMunciDivisionIds, stDate, edDate, "completed", constiIds);
	pushResultConstiWise("munci", stResLst, constiLst, "start");
	pushResultConstiWise("munci", endResLst, constiLst, "completed");*/
	
	List<Object[]> stResLstVill = tdpCommitteeDAO.committeesCountByConstituency(villageWardIds, stDate, edDate, "started", constiIds);
	List<Object[]> endResLstVill = tdpCommitteeDAO.committeesCountByConstituency(villageWardIds, stDate, edDate, "completed", constiIds);
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
				List<IvrOptionsVO> villageIVRDetails = ivrDetailsVO.getOptionsList();
				List<IvrOptionsVO> wardIVRDetails = ivrDetailsVO.getOptionsList1();
				
				int villageListLength = villageIVRDetails.size();
				int wardListLength = wardIVRDetails.size();
				
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
		    System.out.println(resultList);
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
			Map<Long,Long>  startedOthersCommitteesAffCountMap,Map<Long,Long>  completedOthersCommitteesAffCountMap,List<CadreCommitteeReportVO> basicCmmty){
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
			 
			 getLocationsInfo(constituencyIds, divisionLclIds, localBodiesMap, divisionIdsMap, mandalIdsMap);
			 
			 
			 if(mandalIdsMap.size() > 0){
				 List<Long> levelValues = new ArrayList<Long>(mandalIdsMap.keySet());
				 
				//0 count,1levelId
				 List<Object[]> totalMandalMainMembers = tdpCommitteeMemberDAO.totalMainMembersCountLocationsWise(5l,startDate, endDate,levelValues);
				//0count,1locationID
				 List<Object[]> totalMandalCommittees = tdpCommitteeDAO.totalCommitteesCountByLocationIds(5l,levelValues);
				 
				//0count,1locationID,2typeId
				 List<Object[]> totalMandalStartedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(5l,levelValues,startDate,endDate,"started");
				//0count,1locationID,2typeId
				 List<Object[]> totalMandalCompletedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(5l,levelValues,startDate,endDate,"completed");
				
				 
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
				 List<Object[]> totalLocalBodMainMembers = tdpCommitteeMemberDAO.totalMainMembersCountLocationsWise(7l,startDate, endDate,levelValues);
				//0count,1locationID
				 List<Object[]> totalLocalBodCommittees = tdpCommitteeDAO.totalCommitteesCountByLocationIds(7l,levelValues);
				 
				//0count,1locationID,2typeId
				 List<Object[]> totalLocalBodStartedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(7l,levelValues,startDate,endDate,"started");
				//0count,1locationID,2typeId
				 List<Object[]> totalLocalBodCompletedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(7l,levelValues,startDate,endDate,"completed");
				
				 
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
				 List<Object[]> totalDivisionMainMembers = tdpCommitteeMemberDAO.totalMainMembersCountLocationsWise(9l,startDate, endDate,levelValues);
				//0count,1locationID
				 List<Object[]> totalDivisionCommittees = tdpCommitteeDAO.totalCommitteesCountByLocationIds(9l,levelValues);
				 
				//0count,1locationID,2typeId
				 List<Object[]> totalDivisionStartedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(9l,levelValues,startDate,endDate,"started");
				//0count,1locationID,2typeId
				 List<Object[]> totalDivisionCompletedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(9l,levelValues,startDate,endDate,"completed");
				
				 
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
        		constiIds.add((Long)localBody[1]);
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
				for(Long constituenciesId:constituenciesIds){
					Long commiteeId = countArr[2] != null ? Long.valueOf(countArr[2].toString().trim()):0L;
					if(commiteeId.longValue() >0L)
					{
						if(commiteeId.longValue() ==1L)
						{
							Long count = countMap.get(constituenciesId);
							if(count == null){
								countMap.put(constituenciesId,(Long)countArr[0]);
							}else{
								countMap.put(constituenciesId,count+(Long)countArr[0]);
							}
						}
						else
						{
							Long count = afflMembersCountMap.get(constituenciesId);
							if(count == null){
								afflMembersCountMap.put(constituenciesId,(Long)countArr[0]);
							}else{
								afflMembersCountMap.put(constituenciesId,count+(Long)countArr[0]);
							}
						}
					}
					
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
				for(Long constituenciesId:constituenciesIds){
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
								System.out.println("Setting values");
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
				}
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
	
	public CommitteeSummaryVO getConstituencySummary(Long reprtType, Long constituencyId,Long userId){
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
				
				
				List<Object[]> basicCommitteesRslt = tdpBasicCommitteeDAO.getBasicCommittees();
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
					List<Object[]> list = tdpCommitteeMemberDAO.getCommitteeMembersCountByLocationAndCommitteeType(7l, localBodyIds);
					List<CommitteeSummaryVO> locsResult = pushBasicCommitteesToLocations(basicCommitteesRslt, localBodies);
					pushConstSummaryToLocations(list, locsResult);
					
					fnlVO.setLocalBodiesList(localBodies);
				}
				if(mandalIds!=null && mandalIds.size()>0){
					List<Object[]> list = tdpCommitteeMemberDAO.getCommitteeMembersCountByLocationAndCommitteeType(5l, mandalIds);
					List<CommitteeSummaryVO> locsResult =  pushBasicCommitteesToLocations(basicCommitteesRslt, mandals);
					pushConstSummaryToLocations(list, locsResult);
					
					fnlVO.setMandalsList(mandals);
				}
				if(divisionIds!=null && divisionIds.size()>0){
					List<Object[]> list = tdpCommitteeMemberDAO.getCommitteeMembersCountByLocationAndCommitteeType(9l, divisionIds);
					List<CommitteeSummaryVO> locsResult =  pushBasicCommitteesToLocations(basicCommitteesRslt, divisions);
					pushConstSummaryToLocations(list, locsResult);
					
					fnlVO.setDivisionList(divisions);
				}
				 
				if(panchIds!=null && panchIds.size()>0){
					List<Object[]> list = tdpCommitteeMemberDAO.getCommitteeMembersCountByLocationAndCommitteeType(6l, panchIds);
					List<CommitteeSummaryVO> locsResult =  pushBasicCommitteesToLocations(basicCommitteesRslt, allPanchayats);
					pushConstSummaryToLocations(list, locsResult);
					
					List<Object[]> electedMems = tdpCommitteeMemberDAO.getCommitteePresidentAndVicePresidentsCount(panchIds, 6l);
					
					List<Object[]> electedUsers = tdpCommitteeMemberDAO.getCommitteePresidentAndGS(panchIds, 6l);
					List<Long> eletedMemIds = new ArrayList<Long>();
					if(electedUsers!=null && electedUsers.size()>0){
						for(Object[] obj:electedUsers){
							eletedMemIds.add(Long.valueOf(obj[0].toString()));
						}
					}
					
					List<Object[]> electrolsRslt = new ArrayList<Object[]>();
					List<Object[]> electrolsRsltAffl = new ArrayList<Object[]>();
					
						electrolsRslt = tdpCommitteeElectrolsDAO.getElectrolsForPanchayatsWardsWithOutDuplicates(panchIds, "panchayat", eletedMemIds);
						electrolsRsltAffl = tdpCommitteeElectrolsDAO.getElectrolsForPanchayatsWards(panchIds, "panchayat");
						
						if(electrolsRsltAffl!=null && electrolsRsltAffl.size()>0){
							electrolsRslt.addAll(electrolsRsltAffl);
						}
					
					
					
					pushElectrolsCount(electrolsRslt, locsResult, "");
					pushElectrolsCount(electedMems, locsResult, "members");
					
					
					pushPanchayatsAndWards(mandalMap, fnlVO.getMandalsList());
				}
				if(wardIds!=null && wardIds.size()>0){
					List<Object[]> list = tdpCommitteeMemberDAO.getCommitteeMembersCountByLocationAndCommitteeType(8l, wardIds);
					List<CommitteeSummaryVO> locsResult =  pushBasicCommitteesToLocations(basicCommitteesRslt, allWardsList);
					pushConstSummaryToLocations(list, locsResult);
					
					List<Object[]> electedMems = tdpCommitteeMemberDAO.getCommitteePresidentAndVicePresidentsCount(wardIds, 8l);
					
					List<Object[]> electedUsers = tdpCommitteeMemberDAO.getCommitteePresidentAndGS(wardIds, 8l);
					List<Long> eletedMemIds = new ArrayList<Long>();
					if(electedUsers!=null && electedUsers.size()>0){
						for(Object[] obj:electedUsers){
							eletedMemIds.add(Long.valueOf(obj[0].toString()));
						}
					}
					
					List<Object[]> electrolsRslt = new ArrayList<Object[]>();
					List<Object[]> electrolsRsltAffl = new ArrayList<Object[]>();
					
						electrolsRslt = tdpCommitteeElectrolsDAO.getElectrolsForPanchayatsWardsWithOutDuplicates(wardIds, "ward", eletedMemIds);
						electrolsRsltAffl = tdpCommitteeElectrolsDAO.getElectrolsForPanchayatsWards(wardIds, "ward");
						
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
		List<Object[]> elctoralsList= new ArrayList<Object[]>();
		List<AccessedPageLoginTimeVO> returnResult=new ArrayList<AccessedPageLoginTimeVO>();
		try{
			Long locationLvl  = null;
			if(locationValue.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				 locationLvl = 6l;
			 }else{
				 locationLvl = 8l;
			 }
			elctoralsList.addAll(tdpCommitteeMemberDAO.getAllMembersInMainCommWithPresidentAndGeneralSecretaryRole(locationLvl, Long.valueOf(locationValue.toString().substring(1))));
			elctoralsList.addAll(tdpCommitteeElectrolsDAO.getElctoralInfoForALocation(locationValue));
			Set<Long> cadreIds = new HashSet<Long>();
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
	public List<LocationWiseBoothDetailsVO> getPanchayatWardByMandalId(String mandalId){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
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
	
	public List<CadreCommitteeMemberVO> getComitteeMembersInfoByCommiteTypeAndLocation(Long locationId,Long locationType,Long basicCommitteeTypeId,String status)
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
		    List<Object[]> tdpCadresList=tdpCommitteeMemberDAO.getComitteeMembersInfoByCommiteTypeAndLocation(locationType,locationId,basicCommitteeTypeId,status);
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
		    	List<Long> committeeIds = tdpCommitteeDAO.getMainCommittiesInALocation(locationType, locationId);
				if(committeeIds.size() > 0){
					committeeId = committeeIds.get(0);
				}	
		    	List<RolesVO> rolesList = new ArrayList<RolesVO>();
		    	if(committeeId != null){			
					//0committeeRoleid,1role name,2max nos
					List<Object[]> totalCommitteRolesList = tdpCommitteeRoleDAO.getAllCommitteeRoles(committeeId);
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
		List<Long> casteIdsList,Long locationLevelId,Long userId, Long accessValue,String selectedRadio)
	{
		CadreCommitteeRolesInfoVO returnVO = new CadreCommitteeRolesInfoVO();
		try {
			
			List<Long> locationIdsList  = null;
			List<Long> wardIdsList  = null;
			List<Long> committeeTypeIdsList  = new ArrayList<Long>();
			String segrigatStr = "";
			
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
			
			List<Object[]> constituencyList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationIdsSet,"Constituency",null,"Registered");
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
			
			List<Object[]> casteWiseCategoryList = tdpCommitteeMemberDAO.getCasteCategoryInfoForLocations(locationLevelId,locationIdsList,wardIdsList,userAccessType,segrigatStr,"casteCategory");
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
			
			List<Object[]> casteCategoryWiseCountList = tdpCommitteeMemberDAO.getCommitteeRoleCasteCategoryNameWiseDetailsByLocationType(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,null);
			List<CadreCommitteeRolesInfoVO> casteCategoryVOList = new ArrayList<CadreCommitteeRolesInfoVO>();
			
			List<Long> constiteuncyIds = new ArrayList<Long>();
			constiteuncyIds.add(Long.valueOf(locationValue.toString()));
			Long registeredCount  = tdpCadreInfoDAO.getTdpCadreCountForLocations(IConstants.CONSTITUENCY,constiteuncyIds,"Registered","Constituency");
			
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
			
			List<Object[]> casteWiseList = tdpCommitteeMemberDAO.getCasteInfoForLocations(locationLevelId,locationIdsList,wardIdsList,userAccessType,segrigatStr,"caste");
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
			
			List<Object[]> casteWiseCountList = tdpCommitteeMemberDAO.getCommitteeRoleCasteNameWiseDetailsByLocationType(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,null);
					
			
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
			List<Object[]> ageRangeList = tdpCommitteeMemberDAO.getCadreAgerangeInfoForLocations(locationLevelId,locationIdsList,wardIdsList,userAccessType,segrigatStr);
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
			
			List<Object[]> cadreDetails = tdpCommitteeMemberDAO.getCommitteeRoleAgerangeWiseDetailsByLocationType(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,null);
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
			
			List<Object[]> genderWiseResults = tdpCommitteeMemberDAO.getCommitteeRolesGenderWiseDetailsByLocation(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,null,null);
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
		List<Long> casteIdsList,Long locationLevelId,Long userId, Long accessValue,String selectedRadio)
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
						casteCategoryIdsList, casteCategoryGroupIdsList, casteIdsList, locationLevelId, userId, accessValue, selectedRadio);
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
					
					List<Object[]> genderWiseCountList = tdpCommitteeMemberDAO.getCommitteeRolesGenderWiseDetailsByLocation(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,segritageQuery,descriptionLevelId);
					
					List<CadreCommitteeRolesInfoVO> nextLevelList = new ArrayList<CadreCommitteeRolesInfoVO>();
					Set<Long> locationIdsSet = new HashSet<Long>();
					List<Object[]> constituencyList = null;
					if(descriptionLevelId.longValue() == 2)
					{
						locationIdsSet.addAll(locationIdsList);
						constituencyList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationIdsSet,"District",null,"Registered");
					}
					else if(descriptionLevelId.longValue() == 3)
					{
						locationIdsSet.addAll(locationIdsList);
						constituencyList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationIdsSet,"Constituency",null,"Registered");
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
					List<Object[]> genderWiseCountList = tdpCommitteeMemberDAO.getCommitteeRolesGenderWiseDetailsByLocation(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,segritageQuery,descriptionLevelId);
					
					List<CadreCommitteeRolesInfoVO> nextLevelList = new ArrayList<CadreCommitteeRolesInfoVO>();
					Set<Long> locationIdsSet = new HashSet<Long>();
					List<Object[]> constituencyList = null;
					if(descriptionLevelId.longValue() == 2)
					{
						locationIdsSet.addAll(locationIdsList);
						constituencyList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationIdsSet,"District",null,"Registered");
					}
					if(descriptionLevelId.longValue() == 3)
					{
						locationIdsSet.addAll(locationIdsList);
						constituencyList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationIdsSet,"Constituency",null,"Registered");
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

						List<Object[]> genderWiseCountList = tdpCommitteeMemberDAO.getCommitteeRolesGenderWiseDetailsByLocation(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,segritageQuery,descriptionLevelId);
						
						List<CadreCommitteeRolesInfoVO> nextLevelList = new ArrayList<CadreCommitteeRolesInfoVO>();
						Set<Long> locationIdsSet = new HashSet<Long>();
						List<Object[]> constituencyList = null;
						if(descriptionLevelId.longValue() == 2)
						{
							locationIdsSet.addAll(locationIdsList);
							constituencyList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationIdsSet,"District",null,"Registered");
						}
						else if(descriptionLevelId.longValue() == 3)
						{
							locationIdsSet.addAll(locationIdsList);
							constituencyList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationIdsSet,"Constituency",null,"Registered");
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
			
			
			List<Object[]> casteWiseCategoryList = tdpCommitteeMemberDAO.getCasteCategoryInfoForLocations(locationLevelId,locationIdsList,wardIdsList,userAccessType,segritageQuery,"casteCategory");
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
			
			List<Object[]> casteCategoryWiseCountList = tdpCommitteeMemberDAO.getCommitteeRoleCasteCategoryNameWiseDetailsByLocationType(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,segritageQuery);
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
			
			List<Object[]> casteWiseList = tdpCommitteeMemberDAO.getCasteInfoForLocations(locationLevelId,locationIdsList,wardIdsList,userAccessType,segritageQuery,"caste");
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
			
			
			List<Object[]> casteWiseCountList = tdpCommitteeMemberDAO.getCommitteeRoleCasteNameWiseDetailsByLocationType(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,segritageQuery);
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
			
			
			List<Object[]> ageRangeList = tdpCommitteeMemberDAO.getCadreAgerangeInfoForLocations(locationLevelId,locationIdsList,wardIdsList,userAccessType,segritageQuery);
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
			
			List<Object[]> cadreDetails = tdpCommitteeMemberDAO.getCommitteeRoleAgerangeWiseDetailsByLocationType(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,segritageQuery);
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
			Long registeredCount  = tdpCadreInfoDAO.getTdpCadreCountForLocations(userAccessType,locationIdsList,"Registered","District");
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
			List<Object[]> genderWiseCountList = tdpCommitteeMemberDAO.getCommitteeRolesGenderWiseDetailsByLocation(positionIdsList,locationLevelId,locationIdsList,wardIdsList,committeeTypeIdsList,userAccessType,segritageQuery,descriptionLevelId);
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
				constituencyList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationIdsSet,"Constituency",null,"Registered");
			}
			if(actuallevelId.longValue() == 1)
			{
				locationIdsSet.addAll(locationIdsForTotalTdpCadreCount);
				constituencyList = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(locationIdsSet,"District",null,"Registered");
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
	
	
	
	public List<CommitteeSummaryVO> getConstituencyWiseCommittesSummaryForMandal(String state,String startDate, String endDate,Long userId, String accessType,Long accessValue,String mandalCheck,String villageCheck){  
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
		constiCountForMandalTownDivisions1(constiIds, stDate, edDate, mainMembersCountMap, startedCommitteesCountMap, completedCommitteesCountMap, startedCommitteesAffCountMap, completedCommitteesAffCountMap, mainCommitteesCountMap,mandalList);
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
			Map<Long,Long> startedCommitteesAffCountMap,Map<Long,Long> completedCommitteesAffCountMap,Map<Long,Long> mainCommitteesCountMap,List<CommitteeSummaryVO> mandals){
			Map<Long,List<Long>> mandalIdsMap = new HashMap<Long,List<Long>>();//Map<id,List<1constituencyId>>
			Map<Long,List<Long>> localBodiesMap = new HashMap<Long,List<Long>>();//Map<id,List<1constituencyId>>
			List<Long> divisionLclIds = new ArrayList<Long>();//Map<id,List<1constituencyId>>
			Map<Long,List<Long>> divisionIdsMap = new HashMap<Long,List<Long>>();//Map<id,List<1constituencyId>>
			
		if(constituencyIds!=null && constituencyIds.size()>0){
			
			
			 
			 getLocationsInfo(constituencyIds, divisionLclIds, localBodiesMap, divisionIdsMap, mandalIdsMap);
			 
			 
			 if(mandalIdsMap.size() > 0){
				 List<Long> levelValues = new ArrayList<Long>(mandalIdsMap.keySet());
				 
				
				//0 count,1levelId
				 List<Object[]> totalMandalMainMembers = tdpCommitteeMemberDAO.totalMainMembersCountLocationsWise(5l,startDate, endDate,levelValues);
				//0count,1locationID
				 List<Object[]> totalMandalCommittees = tdpCommitteeDAO.totalCommitteesCountByLocationIds(5l,levelValues);
				 
				//0count,1locationID,2typeId
				 List<Object[]> totalMandalStartedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(5l,levelValues,startDate,endDate,"started");
				//0count,1locationID,2typeId
				 List<Object[]> totalMandalCompletedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(5l,levelValues,startDate,endDate,"completed");
				
				 
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
				 List<Object[]> totalLocalBodMainMembers = tdpCommitteeMemberDAO.totalMainMembersCountLocationsWise(7l,startDate, endDate,levelValues);
				//0count,1locationID
				 List<Object[]> totalLocalBodCommittees = tdpCommitteeDAO.totalCommitteesCountByLocationIds(7l,levelValues);
				 
				//0count,1locationID,2typeId
				 List<Object[]> totalLocalBodStartedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(7l,levelValues,startDate,endDate,"started");
				//0count,1locationID,2typeId
				 List<Object[]> totalLocalBodCompletedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(7l,levelValues,startDate,endDate,"completed");
				
				 
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
				 List<Object[]> totalDivisionMainMembers = tdpCommitteeMemberDAO.totalMainMembersCountLocationsWise(9l,startDate, endDate,levelValues);
				//0count,1locationID
				 List<Object[]> totalDivisionCommittees = tdpCommitteeDAO.totalCommitteesCountByLocationIds(9l,levelValues);
				 
				//0count,1locationID,2typeId
				 List<Object[]> totalDivisionStartedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(9l,levelValues,startDate,endDate,"started");
				//0count,1locationID,2typeId
				 List<Object[]> totalDivisionCompletedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(9l,levelValues,startDate,endDate,"completed");
				
				 
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
				 Map<Long,List<IdNameVO>> parliamentInfoMap = new HashMap<Long, List<IdNameVO>>();
				 Map<Long,List<IdNameVO>> assemblyInfoMap = new HashMap<Long, List<IdNameVO>>();
				 Map<Long,List<IdNameVO>> mandalsListMap = new HashMap<Long, List<IdNameVO>>();
				 
				 String region ="ALL";
				 if(stateId != null && stateId.longValue() == 2L )
				 {
					 region ="Telangana";
				 }
				 if(stateId != null && stateId.longValue() == 1L )
				 {
					 region ="AP";
				 }
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
				 
				for (InviteesVO inviteeVO : inviteesVOList) 
				{
					 List<Long> disstrictIds = new ArrayList<Long>();
					 List<Long> locationValuesList = new ArrayList<Long>();
					 List<Long> locationLevelIdsList = new ArrayList<Long>();
					 
					 if(inviteeVO.getLevelStr().trim().equalsIgnoreCase("state"))
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
																				 
										 if(positions != null && positions.size()>0)
										 {
											 for (Long positionId : positions) {
												 locationValuesList.clear();
												 if(positionId.longValue() == 1L  || positionId.longValue() == 9L)//MP or EX.MP
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
												 else  if(positionId.longValue() == 2L || positionId.longValue() == 6L || positionId.longValue() == 8L)//MLA or State Ministers or Ex. MLA
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
												 else  if( positionId.longValue() == 3L || positionId.longValue() == 5L)//MPTC.MPP
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
						
					 }					
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
													 
													 if(positionId.longValue() == 1L || positionId.longValue() == 9L || positionId.longValue() == 7L)//MP or Ex MP or Central Minister-2014
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
													 else if(positionId.longValue() == 2L   || positionId.longValue() == 6L || positionId.longValue() == 8L )//MLA or Ex MLA or 
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
													 else  if( positionId.longValue() == 3L || positionId.longValue() == 5L)//MPTC.MPP
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
										 else // district selected
										 {
											 
											 if(positions != null && positions.size()>0)
											 {
												 for (Long positionId : positions) {
													 
													if(positionId.longValue() == 1L || positionId.longValue() == 9L || positionId.longValue() == 7L)//MP or Ex MP or Central Minister-2014
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
													 else  if(positionId.longValue() == 2L  || positionId.longValue() == 6L || positionId.longValue() == 8L)//MLA
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
													 else  if( positionId.longValue() == 3L || positionId.longValue() == 5L)//MPTC.MPP
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
					 }
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
												 else  if( positionId.longValue() == 3L || positionId.longValue() == 5L)//MPTC.MPP
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
												 else  if( positionId.longValue() == 3L || positionId.longValue() == 5L)//MPTC.MPP
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
						
					 }			
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
								 //0.tdpCadreId, 1.candidateId
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
														}
													}
												}
												
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
														committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
													}
												}
												else if(committeeVO.getMobileType() != null && (committeeVO.getMobileType().trim().equalsIgnoreCase("MLA") || 
														committeeVO.getMobileType().trim().equalsIgnoreCase("EX MLA") ||
														committeeVO.getMobileType().trim().equalsIgnoreCase("2014 AP STATE MINISTERS")))
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
																		committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
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
																		committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
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

																//CadreCommitteeVO committeeVO = new CadreCommitteeVO();
																
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
																		existingVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
																	}
																}
																else if(existingVO.getMobileType() != null && (existingVO.getMobileType().trim().equalsIgnoreCase("MLA") || 
																		existingVO.getMobileType().trim().equalsIgnoreCase("EX MLA") ||
																		existingVO.getMobileType().trim().equalsIgnoreCase("2014 AP STATE MINISTERS")  ))
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
																		committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
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
																		committeeVO.setAddress(vo2.getDistrictid()+"_"+vo2.getName());
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
												
												//committeeVO.setMemberShipCardId(cadre[4] != null ? cadre[4].toString().substring(4):"");
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
						
						returnList.add(vo);
					}
				}
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
		    		   	System.out.println(dateFormat.format(date));
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
			if(!mandalStr.equalsIgnoreCase("0")){
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
				return getMandalMunicCorpDetailsOfConstituencies(constiIds);
			}else{
				return getPanchayatWardDivisionDetailsOfSubLocation(constiIds, mandalIds, localBodyIds);
			}
		}catch(Exception e){
			LOG.error("Exception raised in getLocationsList", e);
			return new ArrayList<LocationWiseBoothDetailsVO>(); 
		}
	}
	
	
	// TO GET MANDAL/LOCAL BODY/ DIVISION DETAILS OF CONSTITUENCIES
	public List<LocationWiseBoothDetailsVO> getMandalMunicCorpDetailsOfConstituencies(List<Long> constituencyIds){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
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
	        	if(!(localBdyId.longValue() == 20l ||  localBdyId.longValue() == 124l || localBdyId.longValue() == 119l)){
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("5"+localBodi[0].toString()));
		        	vo.setLocationName(localBodi[1].toString() +" "+ localBodi[2].toString());
		        	locationsList.add(vo);
	        	}else{
	        		greaterCorpIds.add(localBdyId);
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
	        return locationsList;
	}
	
	
	// TO GET VILLAGE / WARD DETAILS OF MANDAL/LOCAL BODY/DIVISION
	public List<LocationWiseBoothDetailsVO> getPanchayatWardDivisionDetailsOfSubLocation(List<Long> constituencyIds, List<Long> mandals, List<Long> localBodys){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		LocationWiseBoothDetailsVO vo = null;
		
		List<Long> mandalIds = new ArrayList<Long>();
		List<Long> localBodyIds = new ArrayList<Long>();
		
		if(constituencyIds!=null && constituencyIds.size()>0){
			List<LocationWiseBoothDetailsVO> mandalsList = getMandalMunicCorpDetailsOfConstituencies(constituencyIds);
			
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
		List<Object[]> rsltNew = districtConstituenciesDAO.getConstituenciesOfDistrict();
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
				if(constisSplttdMap.get(Long.valueOf(temp[0].toString()))==null){
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
			if(locationLevel.equals(1l)){
				if(locationIds.get(0).equals(1)){
					IdNameVO vo = new IdNameVO();
					vo.setId(1l);
					vo.setName("AndhraPradesh");
					finalList.add(vo);
				}else if(locationIds.get(0).equals(1)){
					IdNameVO vo = new IdNameVO();
					vo.setId(36l);
					vo.setName("Telangana");
					finalList.add(vo);
				}
			}else if(locationLevel.equals(2l)){
				List<Object[]> distRslt = districtDAO.getDistrictDetailsByDistrictIds(locationIds);
				if(distRslt!=null && distRslt.size()>0){
					for(Object[] obj:distRslt){
						IdNameVO vo = new IdNameVO();
						vo.setId(Long.valueOf(obj[0].toString()));
						vo.setName(obj[1].toString());
						finalList.add(vo);
					}
					
				}
			}else if(locationLevel.equals(3l)){
				List<Object[]> constRslt = constituencyDAO.getConstituencyNameByConstituencyIdsList(locationIds);
				if(constRslt!=null && constRslt.size()>0){
					for(Object[] obj:constRslt){
						IdNameVO vo = new IdNameVO();
						vo.setId(Long.valueOf(obj[0].toString()));
						vo.setName(obj[1].toString());
						finalList.add(vo);
					}
					
				}
			}else if(locationLevel.equals(4l)){
				List<Object[]> tehsilRslt = tehsilDAO.getTehsilNameByTehsilIdsList(locationIds);
				if(tehsilRslt!=null && tehsilRslt.size()>0){
					for(Object[] obj:tehsilRslt){
						IdNameVO vo = new IdNameVO();
						vo.setId(Long.valueOf(obj[0].toString()));
						vo.setName(obj[1].toString());
						finalList.add(vo);
					}
					
				}
			}else if(locationLevel.equals(5l)){
				List<Object[]> lebRslt = localElectionBodyDAO.findByLocalElecBodyIds(locationIds);
				if(lebRslt!=null && lebRslt.size()>0){
					for(Object[] obj:lebRslt){
						IdNameVO vo = new IdNameVO();
						vo.setId(Long.valueOf(obj[0].toString()));
						vo.setName(obj[1].toString()+" "+obj[2].toString());
						finalList.add(vo);
					}
					
				}
			}else if(locationLevel.equals(6l)){
				List<Object[]> divisions = assemblyLocalElectionBodyWardDAO.getWardDetailsById(locationIds);
				if(divisions!=null && divisions.size()>0){
					for(Object[] obj:divisions){
						IdNameVO vo = new IdNameVO();
						vo.setId(Long.valueOf(obj[0].toString()));
						vo.setName(obj[1].toString());
						finalList.add(vo);
					}
					
				}
			}else if(locationLevel.equals(7l)){
				List<Object[]> panchayats = panchayatDAO.getPanchayatsByPanchayatIdsList(locationIds);
				if(panchayats!=null && panchayats.size()>0){
					for(Object[] obj:panchayats){
						IdNameVO vo = new IdNameVO();
						vo.setId(Long.valueOf(obj[0].toString()));
						vo.setName(obj[1].toString());
						finalList.add(vo);
					}
					
				}
			}else if(locationLevel.equals(8l)){
				List<Object[]> wards = constituencyDAO.getWardDetailsById(locationIds);
				if(wards!=null && wards.size()>0){
					for(Object[] obj:wards){
						IdNameVO vo = new IdNameVO();
						vo.setId(Long.valueOf(obj[0].toString()));
						vo.setName(obj[1].toString());
						finalList.add(vo);
					}
					
				}
			}
		}catch (Exception e) {
			LOG.error(" ERROR in getLocationNameByLocationIds",e);
		}
		return finalList;
	}
	
	
	
	
}