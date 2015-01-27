package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICadreCommitteeRoleDAO;
import com.itgrids.partyanalyst.dao.ICadreOtpDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreParticipatedElectionDAO;
import com.itgrids.partyanalyst.dao.ICadrePreviousRolesDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.ITdpBasicCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDesignationDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeElectrolRolesDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeElectrolsDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberHistoryDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeRoleDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.model.CadreOtpDetails;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.model.EducationalQualifications;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Occupation;
import com.itgrids.partyanalyst.model.TdpBasicCommittee;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.TdpCommitteeDesignation;
import com.itgrids.partyanalyst.model.TdpCommitteeElectrolRoles;
import com.itgrids.partyanalyst.model.TdpCommitteeElectrols;
import com.itgrids.partyanalyst.model.TdpCommitteeMember;
import com.itgrids.partyanalyst.model.TdpCommitteeMemberHistory;
import com.itgrids.partyanalyst.model.TdpCommitteeRole;
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
				//cadreCommitteeVO.setIsSmartPhone(tdpCadre.getIsSmartPhone());
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
					else if(LocationType.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
					{
						LocalElectionBody localElectionBody = localElectionBodyDAO.get(locationValue);						
						location = localElectionBody.getName() +" "+localElectionBody.getElectionType().getElectionType();
						
						if(locationValue.longValue() == 20L || locationValue.longValue() == 124L || locationValue.longValue() == 119L)
						{
							String wardName = constituencyDAO.get(locationValue).getName();
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
	        	vo.setLocationName(location.getName());
	        	locationsList.add(vo);
	        }
	        for(Object[] localBodi:localBodies){
	        	vo = new LocationWiseBoothDetailsVO();
	        	vo.setLocationId(Long.valueOf("1"+localBodi[0].toString()));
	        	vo.setLocationName(localBodi[1].toString());
	        	locationsList.add(vo);
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
	        	vo.setLocationName(location.getName());
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
				
				//0 role,1 image,2name,3membership
				List<Object[]>  electedMembersInfoList = tdpCommitteeMemberDAO.getMembersInfo(committeeMembersMap.keySet());
				
				for(Object[] electedMembersInfo:electedMembersInfoList){
					memberVo = new SelectOptionVO();
					memberVo.setValue(electedMembersInfo[0].toString());//role
					if(electedMembersInfo[1] != null){
					   memberVo.setUrl(electedMembersInfo[1].toString());//image
					}
					memberVo.setName(electedMembersInfo[2].toString());//name
					memberVo.setType(electedMembersInfo[3].toString());//membership
					committeeMembersList.add(memberVo);
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised in getCommitteeMembersInfo", e);
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
	public void saveElectrolInfo(Long tdpCadreId,Long tdpCommitteeLevelId,Long levelValue,Long committeeMngtType,List<CadrePreviousRollesVO> eligibleRoles,Long tdpCommitteeTypeId){

			if(eligibleRoles != null && eligibleRoles.size() > 0)
			{
				
				if(committeeMngtType != null)
				{
					if(committeeMngtType.longValue() == 2L)
					{
						tdpCommitteeTypeId = 1L;
					}
					else if(committeeMngtType.longValue() == 3L)
					{
						tdpCommitteeTypeId = 2L;
					}
				}
				
				CadrePreviousRollesVO eligibleRole1 = eligibleRoles.get(0);
				if(eligibleRole1 != null && eligibleRole1.getDesignationLevelId() != null && eligibleRole1.getFromDateStr() != null)
				{
					TdpCommitteeElectrols tdpCommitteeElectrols = new TdpCommitteeElectrols();
					tdpCommitteeElectrols.setTdpCadreId(tdpCadreId);
					tdpCommitteeElectrols.setTdpCommitteeLevelId(tdpCommitteeLevelId);
					tdpCommitteeElectrols.setLevelValue(levelValue);
					tdpCommitteeElectrols.setTdpCommitteeEnrollmentId(IConstants.CURRENT_ENROLLMENT_ID);
					tdpCommitteeElectrols.setTdpCommitteeTypeId(tdpCommitteeTypeId);
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
			}
		
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
					if(count.longValue() >= maxMembers.longValue() )
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
						
						tdpCommitteeMemberHistoryDAO.save(tdpCommitteeMemberHistory);
					} catch (Exception e) {
						LOG.error("Exception raised in saveCadreCommitteDetails when inserting in history table ", e);
					}
					
				}
				else
				{
					tdpCommitteeMember = new TdpCommitteeMember(); 
				}
				
				tdpCommitteeMember.setTdpCommitteeRoleId(tdpCommitteeRoleId);
				tdpCommitteeMember.setTdpCadreId(tdpCadreId);
				tdpCommitteeMember.setIsActive("Y");
				tdpCommitteeMember.setTdpCommitteeEnrollmentId(IConstants.CURRENT_ENROLLMENT_ID);
				tdpCommitteeMember.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				tdpCommitteeMember.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				if(isExist)
				{
					tdpCommitteeMember.setUpdatedUserId(userId);
				}
				else
				{
					tdpCommitteeMember.setInsertedUserId(userId);
				}
				
				tdpCommitteeMemberDAO.save(tdpCommitteeMember);
					
				status.setMessage(" Cadre Assigned Successfully... ");
				status.setResultCode(0);
			}
			else
			{
				status.setMessage(" Max Members are already Added for This Position . ");
				status.setResultCode(2);
			}
			
		/*} catch (Exception e) {
			status.setMessage(" Error Occured While Updating Details... ");
			status.setResultCode(1);
			LOG.error("Exception raised in saveCadreCommitteDetails", e);
		}*/
		return status;
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
							
							CadreCommitteeVO cadreVO = getMatchedVOById(cadreCommitteeList,id);
							if(cadreVO != null)
							{								
								String location = null;
								
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
									else 	if(LocationTypeId.longValue() == 7L)
									{
										LocalElectionBody localElectionBody = localElectionBodyDAO.get(locationValue);						
										location = localElectionBody.getName() +" "+localElectionBody.getElectionType().getElectionType();
										
										if(locationValue.longValue() == 20L || locationValue.longValue() == 124L || locationValue.longValue() == 119L)
										{
											String wardName = constituencyDAO.get(locationValue).getName();
											location = location+" - "+wardName;
										}
										
									}	
									
									cadreVO.setCommitteeLocation(location);
									cadreVO.setCommitteePosition(positionName);
									cadreVO.setCommitteeName(committeeName);
								}
								
							}
						}
					}
				}
				cadreCommitteeVO.setPreviousRoles(cadreCommitteeList);
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in searchTdpCadreDetailsBySearchCriteriaForCadreCommitte", e);
		}
		return cadreCommitteeVO;
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
					SelectOptionVO vo = new SelectOptionVO();
					vo.setId(tdpBasicCommittee.getTdpBasicCommitteeId());
					vo.setName(tdpBasicCommittee.getName());
					
					committeesList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getBasicCadreCommitteesDetails", e);
		}
		return committeesList;
	}
}
