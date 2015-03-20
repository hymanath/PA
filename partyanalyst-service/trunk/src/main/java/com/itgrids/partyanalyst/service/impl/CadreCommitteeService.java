package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
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
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.ICommitteIvrDistrictDetailDAO;
import com.itgrids.partyanalyst.dao.ICommitteIvrTotalDetailDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.IIvrCampaignOptionsDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.ITdpBasicCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDesignationDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeElectrolRolesDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeElectrolsDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeLevelDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberHistoryDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeRoleDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeRoleHistoryDAO;
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
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.IvrOptionsVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.model.CadreCommitteeChangeDesignations;
import com.itgrids.partyanalyst.model.CadreCommitteeIncreasedPositions;
import com.itgrids.partyanalyst.model.CadreOtpDetails;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.EducationalQualifications;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Occupation;
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
	
	
	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}
	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
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
				cadreCommitteeVO.setMemberShipCardId(tdpCadre.getMemberShipNo().substring(4));
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
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,Long fromAge,Long toAge,String houseNo,String gender)
	{
		CadreCommitteeVO cadreCommitteeVO = new CadreCommitteeVO();
		try {
			
			TdpCadreVO tdpCadreVO = cadreDetailsService.searchTdpCadreDetailsBySearchCriteriaForCommitte(locationLevel,locationId, searchName,memberShipCardNo, 
					voterCardNo, trNumber, mobileNo,casteStateId,casteCategory,fromAge,toAge,houseNo,gender);
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
						committeeVO.setCadreName(tdpCadre.getCadreName());
						committeeVO.setRelativeName(tdpCadre.getRelativeName());
						committeeVO.setAge(tdpCadre.getAge().toString());
						committeeVO.setMobileNo(tdpCadre.getMobileNo());
						committeeVO.setCasteName(tdpCadre.getCasteName());
						committeeVO.setGender(tdpCadre.getGender());
						committeeVO.setVoterCardNo(tdpCadre.getVoterCardNo());
						committeeVO.setImageURL(tdpCadre.getImageURL());
						cadreCommitteeList.add(committeeVO);
					}
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
				Long roleId = (Long)tdpCadre[5];
				CadreCommitteeVO cadreVO = getMatchedVOById(cadreCommitteeList,id);
				if(cadreVO != null)
				{
					String location = null;
					if(locationValue.longValue() > 0L){
						location = getLocationName(LocationTypeId,locationValue);
						cadreVO.setCommitteeLocation(location);
					    cadreVO.setCommitteePosition(positionName);
					    cadreVO.setCommitteeName(committeeName);
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
					
					if(districtIds != null && districtIds.size() == 13)
					{
						cadreCommitteeReportVO.setAccessState("AP");
					}
					else if(districtIds != null && districtIds.size() == 10)
					{
						cadreCommitteeReportVO.setAccessState("TG");
					}
					else if(districtIds != null && districtIds.size() == 1)
					{
						Long districtId = districtIds.get(0).longValue();
						if(districtId != 0L)
							cadreCommitteeReportVO.setAccessState(districtDAO.get(districtId).getDistrictName()+" District");
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
			
			
			if(!accessType.trim().equalsIgnoreCase("MP") || levelIds.contains(6l) || levelIds.contains(8l)){
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
				   getCadreCommitteIVRDerails(districtIds,committeeSummaryVO,state);
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

	public void getCadreCommitteIVRDerails(List<Long> districtIds,CadreIVRVO resultVO,String state)
	{
		try {
			if(state != null && state.equalsIgnoreCase("AP"))
			{
				districtIds.add(11l);districtIds.add(12l);districtIds.add(13l);districtIds.add(14l);districtIds.add(15l);districtIds.add(16l);districtIds.add(17l);districtIds.add(18l);districtIds.add(19l);districtIds.add(20l);districtIds.add(21l);districtIds.add(21l);districtIds.add(23l);
			}
			else
			{
				districtIds.add(1l);districtIds.add(2l);districtIds.add(3l);districtIds.add(4l);districtIds.add(5l);districtIds.add(6l);districtIds.add(7l);districtIds.add(8l);districtIds.add(9l);districtIds.add(10l);
			}
			Map<Long,String> optionNames = new HashMap<Long,String>();
			List<Object[]> optionsList = ivrCampaignOptionsDAO.getAllIVROptions(2l);
			if(optionsList != null && optionsList.size() >0){
				for(Object[] option:optionsList){
					optionNames.put((Long)option[0], option[1].toString());
				}
			}
			List<IvrOptionsVO> optionsList1 = new ArrayList<IvrOptionsVO>();
			for(Long id : optionNames.keySet())
			{
				IvrOptionsVO optionVo = new IvrOptionsVO();
				optionVo.setId(id);
				optionVo.setName(optionNames.get(id));
				optionVo.setCount(0l);
				optionsList1.add(optionVo);
			}
			List<IvrOptionsVO> options = null;
		    if(optionNames != null && optionNames.size() > 0)
		    {
		    	options = new ArrayList<IvrOptionsVO>();
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
	    	List<Object[]> values = committeIvrTotalDetailDAO.getStateWiseIvrDetails(state);
	    	if(values != null && values.size() > 0)
	    	{
	    		Object[] data = values.get(0);
	    		resultVO.setCount(data[0] != null ? Long.valueOf(data[0].toString().trim()) : 0l);
		    	resultVO.setTotalIvrCalls(data[1] != null ? Long.valueOf(data[1].toString().trim()) : 0l);
		    	resultVO.setAnsweredIvrCalls(data[2] != null ? Long.valueOf(data[2].toString().trim()) : 0l);
	    	}
	    	
			/*List<Long> panchayatsCount = cadreIvrResponseDAO.getPanchayatsCountIvrStarted(districtIds,2l);
			if(panchayatsCount != null && panchayatsCount.size() > 0)
		    {
				resultVO.setCount(Long.valueOf(panchayatsCount.size()));
		    }
			List<Long> totalIvrCalls = cadreIvrResponseDAO.getTotalIvrCalls(districtIds,2l);
			if(totalIvrCalls != null && totalIvrCalls.size() > 0)
			{
				resultVO.setTotalIvrCalls(totalIvrCalls.get(0));
			}
			List<Long> answerCount = cadreIvrResponseDAO.getTotalAnsweredIvrCalls(districtIds,2l);
			if(answerCount != null && answerCount.size() > 0)
			{
				resultVO.setAnsweredIvrCalls(answerCount.get(0));
			}*/
			List<Object[]> cadreIvrDetails = cadreIvrResponseDAO.getStateWiseCommitterIvrDetails(districtIds,2l);
			if(cadreIvrDetails != null && cadreIvrDetails.size() > 0)
			{
				Map<Long,IvrOptionsVO> optionsMap = new HashMap<Long, IvrOptionsVO>();
				//List<IvrOptionsVO> options = resultVO.getOptionsList();
				resultVO.setOptionsList(options);
				for (Object[] ivrCountInfo : cadreIvrDetails)
				{
					if(ivrCountInfo[0].toString().equalsIgnoreCase(IConstants.NORMAL_CLEARING))
					{
						if(ivrCountInfo[1] != null)
						{
							List<IvrOptionsVO> mainOptionsList = resultVO.getOptionsList();
							if(mainOptionsList != null && mainOptionsList.size() > 0)
							{
								for (IvrOptionsVO ivrOptionsVO : mainOptionsList)
								{
									if(ivrOptionsVO.getId().longValue() == Long.valueOf(ivrCountInfo[1].toString().trim()))
									{
										ivrOptionsVO.setCount((Long)ivrCountInfo[2]);//count
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
					resultVO.setOptionsList(options);
				}
				
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getCadreCommitteIVRDerails method",e);
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
					
					if(districtIds != null && districtIds.size() == 13)
					{
						cadreCommitteeReportVO.setAccessState("AP");
					}
					else if(districtIds != null && districtIds.size() == 10)
					{
						cadreCommitteeReportVO.setAccessState("TG");
					}
					else if(districtIds != null && districtIds.size() == 1)
					{
						Long districtId = districtIds.get(0).longValue();
						if(districtId != 0L)
							cadreCommitteeReportVO.setAccessState(districtDAO.get(districtId).getDistrictName()+" District");
					}
				}
			}
		
		
			if(!accessType.trim().equalsIgnoreCase("MP")){
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
	
	public List<CommitteeSummaryVO> getDistrictWiseCommittesSummary(String state,String startDate, String endDate,Long userId,String accessType, Long accessValue,String mandalCheck, String villageCheck){
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
					
					if(fnlLst != null && distIds != null && distIds.size() == 13)
					{
						CommitteeSummaryVO vo = fnlLst.get(0);
						vo.setAccessState("AP");
					}
					if(fnlLst != null && distIds != null && distIds.size() == 10)
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
				}
			}
			if(villageCheck.equalsIgnoreCase("true") && userId.longValue() == 1){
				Map<Long,CadreIVRVO> committeeIvrMap = new HashMap<Long, CadreIVRVO>();
				Map<Long,String> optionNames = new HashMap<Long,String>();
				List<Object[]> optionsList = ivrCampaignOptionsDAO.getAllIVROptions(2l);
				if(optionsList != null && optionsList.size() >0){
					for(Object[] option:optionsList){
						optionNames.put((Long)option[0], option[1].toString());
					}
				}
				List<IvrOptionsVO> optionsList1 = new ArrayList<IvrOptionsVO>();
				for(Long id : optionNames.keySet())
				{
					IvrOptionsVO optionVo = new IvrOptionsVO();
					optionVo.setId(id);
					optionVo.setName(optionNames.get(id));
					optionVo.setCount(0l);
					optionsList1.add(optionVo);
				}
				getCadreCommitteIvrDetails(committeeIvrMap,1l,optionNames);
				if(committeeIvrMap != null && committeeIvrMap.size() > 0 && fnlLst != null && fnlLst.size() > 0)
				{
					for (CommitteeSummaryVO vo : fnlLst)
					{
						CadreIVRVO ivrVO = committeeIvrMap.get(vo.getDistrictId());
						if(ivrVO != null )
						{
							vo.setCadreIVRVO(ivrVO);
						}
						else
						{
							ivrVO = new CadreIVRVO();
							ivrVO.setOptionsList(optionsList1);
							vo.setCadreIVRVO(ivrVO);
						}
					}
				}
			}
			
			
			
		}catch (Exception e) {
			LOG.error("Exception Raised in getDistrictWiseCommittesSummary",e);
		}
		return fnlLst;
	}
	
	
	public void getCadreCommitteIvrDetails(Map<Long,CadreIVRVO> committeeIvrMap,Long id,Map<Long,String> optionNames)
	{
		try 
		{
			    List<Object[]> ivrResponceDetails = null;
			    if(id == 1)
			    {
			    	ivrResponceDetails = committeIvrDistrictDetailDAO.getDistrictWiseIvrDetails();
			    }
			    else
			    {
			    	ivrResponceDetails = cadreIvrResponseDAO.getCadreCommitteesIvRDetails(id,2l);
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
								
								resultVO.setOptionsList(ivrOptionsList);
								
								committeeIvrMap.put(Long.valueOf(ivrCountInfo[0].toString().trim()), resultVO);
							}
							if(ivrCountInfo[2].toString().equalsIgnoreCase(IConstants.NORMAL_CLEARING))
							{
								if(ivrCountInfo[3] != null && optionNames != null && optionNames.size() > 0)
								{
									List<IvrOptionsVO> optionsList = resultVO.getOptionsList();
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
									
									if(districtIds != null && districtIds.size() == 13)
									{
										vo.setAccessState("AP");
									}
									else if(districtIds != null && districtIds.size() == 10)
									{
										vo.setAccessState("TG");
									}
									else if(districtIds != null && districtIds.size() == 1)
									{
										Long districtId = districtIds.get(0).longValue();
										if(districtId != 0L)
											vo.setAccessState(districtDAO.get(districtId).getDistrictName()+" District");
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
						
						if(districtIds != null && districtIds.size() == 13)
						{
							accessState = "AP";
						}
						else if(districtIds != null && districtIds.size() == 10)
						{
							accessState = "TG";
						}
						else if(districtIds != null && districtIds.size() == 1)
						{
							Long districtId = districtIds.get(0).longValue();
							if(districtId != 0L)
								accessState =  districtDAO.get(districtId).getDistrictName()+" District";
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
					
					if(districtIds != null && districtIds.size() == 13)
					{
						accessState = "AP";
					}
					else if(districtIds != null && districtIds.size() == 10)
					{
						accessState = "TG";
					}
					else if(districtIds != null && districtIds.size() == 1)
					{
						Long districtId = districtIds.get(0).longValue();
						if(districtId != 0L)
							accessState =  districtDAO.get(districtId).getDistrictName()+" District";
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
			 Map<Long,Long> mainCommitteesCountMap = new HashMap<Long,Long>();
			if(mandalCheck.equalsIgnoreCase("true"))
				constiCountForMandalTownDivisions(constiIds, stDate, edDate, mainMembersCountMap, startedCommitteesCountMap, completedCommitteesCountMap, startedCommitteesAffCountMap, completedCommitteesAffCountMap, mainCommitteesCountMap);
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
				Map<Long,CadreIVRVO> committeeIvrMap = new HashMap<Long, CadreIVRVO>();
				Map<Long,String> optionNames = new HashMap<Long,String>();
				List<Object[]> optionsList = ivrCampaignOptionsDAO.getAllIVROptions(2l);
				if(optionsList != null && optionsList.size() >0){
					for(Object[] option:optionsList){
						optionNames.put((Long)option[0], option[1].toString());
					}
				}
				List<IvrOptionsVO> optionsList1 = new ArrayList<IvrOptionsVO>();
				for(Long id : optionNames.keySet())
				{
					IvrOptionsVO optionVo = new IvrOptionsVO();
					optionVo.setId(id);
					optionVo.setName(optionNames.get(id));
					optionVo.setCount(0l);
					optionsList1.add(optionVo);
				}
				getCadreCommitteIvrDetails(committeeIvrMap,2l,optionNames);
				if(committeeIvrMap != null && committeeIvrMap.size() > 0 && constiLst != null && constiLst.size() > 0)
				{
					
					
					for (CommitteeSummaryVO vo : constiLst)
					{
						CadreIVRVO ivrVO = committeeIvrMap.get(vo.getConstiId());
						if(ivrVO != null )
						{
							vo.setCadreIVRVO(ivrVO);
						}
						else
						{
							ivrVO = new CadreIVRVO();
							ivrVO.setOptionsList(optionsList1);
							vo.setCadreIVRVO(ivrVO);
						}
					}
				}
			}
			
		}catch (Exception e) {
			LOG.error("Exception Raised in getConstituencyWiseCommittesSummary",e);
		}
		return constiLst;
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
					if(temp.getVillageWardVO()==null){
						temp.setVillageWardVO(new CommitteeSummaryVO());
					}
					temp.getVillageWardVO().setMembersCount(Long.valueOf(obj[0].toString()));
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
					if(temp.getVillageWardVO()==null){
						temp.setVillageWardVO(new CommitteeSummaryVO());
					}
					temp.getVillageWardVO().setTotalCommittees(Long.valueOf(obj[0].toString()));
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
			Map<Long,Long> mainMembersCountMap,Map<Long,Long> startedCommitteesCountMap,Map<Long,Long> completedCommitteesCountMap,
			Map<Long,Long> startedCommitteesAffCountMap,Map<Long,Long> completedCommitteesAffCountMap,Map<Long,Long> mainCommitteesCountMap){
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
						 mandalIdsMap);
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
						 localBodiesMap);
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
						 divisionIdsMap);
			 }
			 
		}
	}
	
	private void getLocationsInfo(List<Long> constituencyIds,List<Long> divisionLclIds,
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
			 Map<Long,Long> mainCommitteesCountMap,
			 
			 Map<Long,Long> startedCommitteesCountMap,
			 Map<Long,Long> startedCommitteesAffCountMap,
			 
			 Map<Long,Long> completedCommitteesCountMap,			 
			 Map<Long,Long> completedCommitteesAffCountMap,
			 
			 List<Object[]> totalMainMembers,
			 List<Object[]> totalCommittees,
			 
			 List<Object[]> totalStartedCommittees,
			 
			 List<Object[]> totalCompletedCommittees,
			 
			 Map<Long,List<Long>> locationsMap){
		
		     populateMainCounts(totalMainMembers,locationsMap,mainMembersCountMap);
		     populateMainCounts(totalCommittees,locationsMap,mainCommitteesCountMap);
		     populateMainAndAffCounts(totalStartedCommittees,locationsMap,startedCommitteesCountMap,startedCommitteesAffCountMap);
		     populateMainAndAffCounts(totalCompletedCommittees,locationsMap,completedCommitteesCountMap,completedCommitteesAffCountMap);
		
	}
	
	private void populateMainCounts(List<Object[]> mainMembers,Map<Long,List<Long>> locationsMap,Map<Long,Long> countMap){
		//0 count,1levelId
		for(Object[] countArr:mainMembers){
			List<Long> constituenciesIds = locationsMap.get((Long)countArr[1]);
			if(constituenciesIds != null && constituenciesIds.size() > 0){
				for(Long constituenciesId:constituenciesIds){
					Long count = countMap.get(constituenciesId);
					if(count == null){
						countMap.put(constituenciesId,(Long)countArr[0]);
					}else{
						countMap.put(constituenciesId,count+(Long)countArr[0]);
					}
				}
			}
		}
	}
	
	private void populateMainAndAffCounts(List<Object[]> mainAffMembers,Map<Long,List<Long>> locationsMap,Map<Long,Long> mainCountMap,Map<Long,Long> affCountMap){
		//0 count,1levelId,2typeId
		for(Object[] countArr:mainAffMembers){
			Map<Long,Long> countMap = mainCountMap;
			if(((Long)countArr[2]).longValue() == 2l){
				countMap = affCountMap;
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
				Map<Long,CadreIVRVO> committeeIvrMap = new HashMap<Long, CadreIVRVO>();
				Map<Long,String> optionNames = new HashMap<Long,String>();
				List<Object[]> optionsList = ivrCampaignOptionsDAO.getAllIVROptions(2l);
				if(optionsList != null && optionsList.size() >0){
					for(Object[] option:optionsList){
						optionNames.put((Long)option[0], option[1].toString());
					}
				}
				List<IvrOptionsVO> optionsList1 = new ArrayList<IvrOptionsVO>();
				for(Long id : optionNames.keySet())
				{
					IvrOptionsVO optionVo = new IvrOptionsVO();
					optionVo.setId(id);
					optionVo.setName(optionNames.get(id));
					optionVo.setCount(0l);
					optionsList1.add(optionVo);
				}
				getCadreCommitteIvrDetails(committeeIvrMap,3l,optionNames);
				if(committeeIvrMap != null && committeeIvrMap.size() > 0 && fnlVO.getMandalsList() != null && fnlVO.getMandalsList().size() > 0)
				{
					
					
					for (CommitteeSummaryVO vo : fnlVO.getMandalsList())
					{
						for(CommitteeSummaryVO subvo :vo.getLocationsList())
						{
							CadreIVRVO ivrVO = committeeIvrMap.get(subvo.getLocationId());
							if(ivrVO != null )
							{
								subvo.setCadreIVRVO(ivrVO);
							}
							else
							{
								ivrVO = new CadreIVRVO();
								ivrVO.setOptionsList(optionsList1);
								subvo.setCadreIVRVO(ivrVO);
							}
						}
						
					}
				}
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
					memberVo.setType(electedMembersInfo[3].toString());//membership
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
			    	  if(objects[5].toString().trim().length() > 8){
			    		    cadreCommitteeMemberVO.setMembershipNo(objects[5] != null ? objects[5].toString().trim().substring(objects[5].toString().trim().length()-8):"");
						}else{
							cadreCommitteeMemberVO.setMembershipNo(objects[5] != null ? objects[5].toString():"");
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
	public List<CadreCommitteeMemberVO> getComitteeMembersInfoByCommiteTypeAndLocation(Long locationId,Long locationType,Long basicCommitteeTypeId,String status)
	{
		List<CadreCommitteeMemberVO> cadreCommitteeMemberVOList=null;
		try
		{
			Map<String,CasteDetailsVO> casteGroupMap = null;
			Map<String,CasteDetailsVO> ageGroupMap = null;
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
		    	Long maleCount = 0L;
		    	Long femaleCount = 0L;
		    	for (Object[] objects : tdpCadresList){
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

		    		
		    		if(objects[5].toString().trim().length() > 8){
		    			cadreCommitteeMemberVO.setMembershipNo(objects[5].toString().trim().substring(objects[5].toString().trim().length()-8));
					}else{
						cadreCommitteeMemberVO.setMembershipNo(objects[5].toString());//membershipno
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
		    	 cadreCommitteeMemberVOList.get(0).setCommitte(tdpBasicCommitteeDAO.get(basicCommitteeTypeId).getName());
		    	 cadreCommitteeMemberVOList.get(0).setFemaleCount(femaleCount.toString());
		    	 cadreCommitteeMemberVOList.get(0).setMaleCount(maleCount.toString());
		    	 Long totalCount = femaleCount+maleCount ;
		    	 cadreCommitteeMemberVOList.get(0).setTotal(totalCount);
				}
		    	
		    }
		}
		catch(Exception e){
			
			LOG.error("Exception raised in getComitteeMembersInfoByCommiteTypeAndLocation", e);
		}
		return cadreCommitteeMemberVOList;
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
					
					if(districtIds != null && districtIds.size() == 13)
					{
						userAccessValue = "AP";
					}
					else if(districtIds != null && districtIds.size() == 10)
					{
						userAccessValue = "TS";
					}
					else if(districtIds != null && districtIds.size() == 1)
					{
						Long districtId = districtIds.get(0).longValue();
						if(districtId != 0L)
							userAccessValue =  districtDAO.get(districtId).getDistrictName()+" District";
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
						
						 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / totalMaleCount));
						 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / totalFemaleCount));
						 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / totalCount));
						
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
						
						 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / totalMaleCount));
						 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / totalFemaleCount));
						 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / totalCount));
						
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
						
						 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / totalMaleCount));
						 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / totalFemaleCount));
						 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / totalCount));
						
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
								
								 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / locationWiseMaleCount));
								 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / locationWiseFemaleCount));
								 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / locationWiseTotalCount));
								
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
								
								 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / locationWiseMaleCount));
								 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / locationWiseFemaleCount));
								 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / locationWiseTotalCount));
								
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
									
									 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / locationWiseMaleCount));
									 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / locationWiseFemaleCount));
									 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / locationWiseTotalCount));
									
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
						
						 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / totalMaleCount));
						 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / totalFemaleCount));
						 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / totalCount));
						
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
						
						 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / totalMaleCount));
						 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / totalFemaleCount));
						 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / totalCount));
						
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
						
						 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / totalMaleCount));
						 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / totalFemaleCount));
						 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / totalCount));
						
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
						
						 malePerc = Double.valueOf(decimalFormat.format((casteMaleCount * 100.00) / totalMaleCount));
						 femalePerc = Double.valueOf(decimalFormat.format((casteFemaleCount * 100.00) / totalFemaleCount));
						 castePercInTotal = Double.valueOf(decimalFormat.format((casteTotalCount * 100.00) / totalCount));
						
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
			LOG.error("Exception Raised in getCommitteeRoleAgeWiseDetailsByLocationType() ");
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
}
