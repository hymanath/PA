package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

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
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.CadreOtpDetails;
import com.itgrids.partyanalyst.model.EducationalQualifications;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.Occupation;
import com.itgrids.partyanalyst.model.TdpCadre;
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
				cadreCommitteeVO.setAge(tdpCadre.getAge().toString());
				cadreCommitteeVO.setGender(tdpCadre.getGender());
				try {
					cadreCommitteeVO.setDOB(format.format(new SimpleDateFormat("yyyy-dd-MM").parse(tdpCadre.getDateOfBirth().toString().substring(0, 10))));
				} catch (Exception e) {
					cadreCommitteeVO.setDOB(format.format(tdpCadre.getDateOfBirth()));
				}
				cadreCommitteeVO.setMobileType("");
				cadreCommitteeVO.setMobileNo(tdpCadre.getMobileNo());
				cadreCommitteeVO.setAdhaarNo(tdpCadre.getAadheerNo());
				cadreCommitteeVO.setAddress(tdpCadre.getUserAddress().getStreet() != null ? tdpCadre.getUserAddress().getStreet():"");
				cadreCommitteeVO.setCasteStateId(tdpCadre.getCasteState() != null?tdpCadre.getCasteState().getCasteStateId():0L);
				cadreCommitteeVO.setCasteName(tdpCadre.getCasteState() != null? tdpCadre.getCasteState().getCaste().getCasteName():"");
				cadreCommitteeVO.setCasteCategoryId(tdpCadre.getCasteState() != null?tdpCadre.getCasteState().getCasteCategoryGroup().getCasteCategory().getCasteCategoryId():0L);
				cadreCommitteeVO.setCasteCategory(tdpCadre.getCasteState().getCasteCategoryGroup().getCasteCategory().getCategoryName());
				cadreCommitteeVO.setVoterCardNo(tdpCadre.getVoter() != null ? tdpCadre.getVoter().getVoterIDCardNo():"");
				cadreCommitteeVO.setEducationId(tdpCadre.getEducationalQualifications() != null ? tdpCadre.getEducationalQualifications().getEduQualificationId():0L);
				cadreCommitteeVO.setEducation(tdpCadre.getEducationalQualifications() != null ? tdpCadre.getEducationalQualifications().getQualification():"");
				cadreCommitteeVO.setOccupationId(tdpCadre.getOccupation() != null ? tdpCadre.getOccupation().getOccupationId():0L);
				cadreCommitteeVO.setOccupation(tdpCadre.getOccupation() != null ? tdpCadre.getOccupation().getOccupation():"");
				
				cadreCommitteeVO.setEmailId(tdpCadre.getEmailId());
				cadreCommitteeVO.setImageURL(tdpCadre.getImage() != null ? tdpCadre.getImage():"");
				
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
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat ddMMyyyyFT = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			
			List<Object[]> participationInfo = cadrePreviousRolesDAO.getexistingRolesForTdpCadreByTdpCadreIdForCommittee(tdpCadreId);//0,1,2,3
			
			if(participationInfo != null && participationInfo.size()>0)
			{
				returnList = new ArrayList<CadreCommitteeVO>();
				
				for (Object[] participation : participationInfo)
				{
					CadreCommitteeVO vo = new CadreCommitteeVO();		
					vo.setRoleId(participation[0] != null ? Long.valueOf(participation[0].toString().trim()):0L);
					vo.setFromDate(participation[1] != null ?ddMMyyyyFT.format(format1.parse(participation[1].toString())):"");		// from date
					vo.setToDate(participation[2] != null ? ddMMyyyyFT.format(format1.parse(participation[2].toString())):"");		// to date 
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
					status = " Cadre Details are Successfully updated.";
				}
				else
				{
					status = " Invalid OTP. Regenerate OTP then submit data.";
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
		List<SelectOptionVO> locations = regionServiceDataImp.getSubRegionsInConstituency(constituencyId, IConstants.PRESENT_YEAR, "");
	        for(SelectOptionVO location:locations){
	        	vo = new LocationWiseBoothDetailsVO();
	        	vo.setLocationId(location.getId());
	        	vo.setLocationName(location.getName());
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
	        	List<Object[]> panchayatsList = panchayatDAO.getAllPanchayatsInAListOfMandals(mandalIds);
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
		        	vo.setLocationId(Long.valueOf("1"+(Long)localBody[0]));
		        	vo.setLocationName(localBody[1].toString()+"("+localBody[2].toString()+")");
		        	locationsList.add(vo);
	        	}
	        }
	        return locationsList;
	} 
}
