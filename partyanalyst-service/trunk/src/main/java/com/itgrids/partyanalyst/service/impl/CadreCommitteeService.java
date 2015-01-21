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
import com.itgrids.partyanalyst.dao.ICadreCommitteeRoleDAO;
import com.itgrids.partyanalyst.dao.ICadreOtpDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreParticipatedElectionDAO;
import com.itgrids.partyanalyst.dao.ICadrePreviousRolesDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDesignationDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeElectrolRolesDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeElectrolsDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeRoleDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.CadreOtpDetails;
import com.itgrids.partyanalyst.model.EducationalQualifications;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.Occupation;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.TdpCommitteeDesignation;
import com.itgrids.partyanalyst.model.TdpCommitteeElectrolRoles;
import com.itgrids.partyanalyst.model.TdpCommitteeElectrols;
import com.itgrids.partyanalyst.model.TdpCommitteeMember;
import com.itgrids.partyanalyst.model.TdpCommitteeRole;
import com.itgrids.partyanalyst.model.VoterAgeRange;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
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
				cadreCommitteeVO.setMobileNo(tdpCadre.getMobileNo());
				cadreCommitteeVO.setAdhaarNo(tdpCadre.getCadreAadherNo());
				cadreCommitteeVO.setAddress(tdpCadre.getUserAddress().getStreet() != null ? tdpCadre.getUserAddress().getStreet():"");
				cadreCommitteeVO.setCasteStateId(tdpCadre.getCasteState() != null?tdpCadre.getCasteState().getCasteStateId():0L);
				cadreCommitteeVO.setCasteName(tdpCadre.getCasteState() != null? tdpCadre.getCasteState().getCaste().getCasteName():"");
				cadreCommitteeVO.setCasteCategoryId(tdpCadre.getCasteState() != null?tdpCadre.getCasteState().getCasteCategoryGroup().getCasteCategory().getCasteCategoryId():0L);
				cadreCommitteeVO.setCasteCategory(tdpCadre.getCasteState() != null? tdpCadre.getCasteState().getCasteCategoryGroup().getCasteCategory().getCategoryName():"");
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
				cadreCommitteeVO.setEducationId(tdpCadre.getEducationalQualifications() != null ? tdpCadre.getEducationalQualifications().getEduQualificationId():0L);
				cadreCommitteeVO.setEducation(tdpCadre.getEducationalQualifications() != null ? tdpCadre.getEducationalQualifications().getQualification():"");
				cadreCommitteeVO.setOccupationId(tdpCadre.getOccupation() != null ? tdpCadre.getOccupation().getOccupationId():0L);
				cadreCommitteeVO.setOccupation(tdpCadre.getOccupation() != null ? tdpCadre.getOccupation().getOccupation():"");
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
				
				List<Object[]> cadreCommitteeInfo = tdpCommitteeMemberDAO.getMemberInfo(tdpCadreId);
				
				if(cadreCommitteeInfo != null && cadreCommitteeInfo.size()>0)
				{
					for (Object[] cadreCommittee : cadreCommitteeInfo)
					{
						cadreCommitteeVO.setCommitteeLevelId(cadreCommittee[1] != null ? Long.valueOf(cadreCommittee[1].toString()):0L);
						cadreCommitteeVO.setCommitteeTypeId(cadreCommittee[2] != null ? Long.valueOf(cadreCommittee[2].toString()):0L);
						cadreCommitteeVO.setCommitteeId(cadreCommittee[3] != null ? Long.valueOf(cadreCommittee[3].toString()):0L);
						cadreCommitteeVO.setRoleId(cadreCommittee[4] != null ? Long.valueOf(cadreCommittee[4].toString()):0L);						
					}
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
			Long otpNumberForRefNo = cadreOtpDetailsDAO.checkOTPValid(mobileNo,refNo,userId);
			if(otpNumberForRefNo != null && otpNumberForRefNo.longValue() != 0L)
			{
				if(otpNumber.longValue() == otpNumberForRefNo.longValue())
				{
					status = "success";
				}
				else
				{
					status = "failure";
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
				return getMandalMunicCorpDetails(constituencyId);
			}else{
				return getPanchayatWardDivisionDetails(constituencyId);
			}
		}catch(Exception e){
			LOG.error("Exception raised in getLocationsList", e);
			return new ArrayList<LocationWiseBoothDetailsVO>(); 
		}
	}
	
	public List<LocationWiseBoothDetailsVO> getMandalMunicCorpDetails(Long constituencyId){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		LocationWiseBoothDetailsVO vo = null;
		List<SelectOptionVO> locations = regionServiceDataImp.getTehsilsInConstituency(constituencyId);
		List<Object[]> localBodies = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituency(constituencyId);
	        for(SelectOptionVO location:locations){
	        	vo = new LocationWiseBoothDetailsVO();
	        	vo.setLocationId(location.getId());
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
	
	public List<LocationWiseBoothDetailsVO> getPanchayatWardDivisionDetails(Long constituencyId){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		LocationWiseBoothDetailsVO vo = null;
		List<LocationWiseBoothDetailsVO> mandalsList = getMandalMunicCorpDetails(constituencyId);
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
	public void saveElectrolInfo(Long tdpCadreId,Long tdpCommitteeLevelId,Long levelValue,Long tdpCommitteeTypeId,Long committeeMngtType,Long committeeId,List<CadrePreviousRollesVO> eligibleRoles){

			if(eligibleRoles != null && eligibleRoles.size() > 0)
			{
				
				if(committeeMngtType != null)
				{
					if(committeeMngtType.longValue() == 2L)
					{
						tdpCommitteeTypeId = 1L;
					}
					if(committeeMngtType.longValue() == 3L)
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
	
	public ResultStatus saveCadreCommitteDetails(final Long tdpCadreId,final Long tdpCommitteeRoleId)
	{
		ResultStatus status = new ResultStatus();
	
		try {
			boolean isEligible = true;
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

				TdpCommitteeMember tdpCommitteeMember = new TdpCommitteeMember(); 
				
				tdpCommitteeMember.setTdpCommitteeRoleId(tdpCommitteeRoleId);
				tdpCommitteeMember.setTdpCadreId(tdpCadreId);
				tdpCommitteeMember.setIsActive("Y");
				tdpCommitteeMember.setTdpCommitteeEnrollmentId(IConstants.CURRENT_ENROLLMENT_ID);
				tdpCommitteeMemberDAO.save(tdpCommitteeMember);
					
				status.setMessage(IConstants.SUCCESS);
				status.setResultCode(0);
			}
			else
			{
				status.setMessage("Max Members are already Added for This Position .");
				status.setResultCode(2);
			}
			
		} catch (Exception e) {
			status.setMessage("FAILURE");
			status.setResultCode(1);
			LOG.error("Exception raised in saveCadreCommitteDetails", e);
		}
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
	
}
