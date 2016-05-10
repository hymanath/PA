package com.itgrids.partyanalyst.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBloodGroupDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICadreGovtDesignationDAO;
import com.itgrids.partyanalyst.dao.ICadrePartyDesignationDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeErrorDAO;
import com.itgrids.partyanalyst.dao.IEventDAO;
import com.itgrids.partyanalyst.dao.IEventInfoDAO;
import com.itgrids.partyanalyst.dao.IEventInviteeDAO;
import com.itgrids.partyanalyst.dao.IEventUserDAO;
import com.itgrids.partyanalyst.dao.IGovtDesignationDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPartyDesignationDAO;
import com.itgrids.partyanalyst.dao.ISocialCategoryDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserAuthDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreInfoDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.CadreVo;
import com.itgrids.partyanalyst.dto.EventActionPlanVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.MahanaduEventVO;
import com.itgrids.partyanalyst.dto.MahanaduVisitVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreGovtDesignation;
import com.itgrids.partyanalyst.model.CadrePartyDesignation;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.EventInfo;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IMahaNaduService;
import com.itgrids.partyanalyst.service.IMahanaduDashBoardService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;



public class MahaNaduService implements IMahaNaduService{
	private static final Logger LOG = Logger.getLogger(MahaNaduService.class);
	private ICadrePartyDesignationDAO cadrePartyDesignationDAO;
	private ICadreGovtDesignationDAO cadreGovtDesignationDAO;
	private IUserAddressDAO userAddressDAO;
	private IEducationalQualificationsDAO educationalQualificationsDAO;
	private IOccupationDAO occupationDAO;
	private ISocialCategoryDAO socialCategoryDAO;
	private IDistrictDAO districtDAO;
	private ITehsilDAO tehsilDAO;
	private ITownshipDAO townshipDAO;
	private IConstituencyDAO constituencyDAO;
	private IHamletDAO hamletDAO;
	private TransactionTemplate transactionTemplate = null;
	private ICadreDAO cadreDAO;
	private ICasteStateDAO casteStateDAO;
	private IPartyDesignationDAO partyDesignationDAO;
	private IGovtDesignationDAO govtDesignationDAO;
	private CadreManagementService cadreManagementService;
	private IUserDAO userDAO;
	private IBloodGroupDAO bloodGroupDAO;
	private IVoterDAO voterDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IEventInviteeDAO eventInviteeDAO;
	private IEventInfoDAO eventInfoDAO;
	private IEventAttendeeDAO eventAttendeeDAO;
	private IVoterInfoDAO voterInfoDAO;
	private ITdpCadreInfoDAO tdpCadreInfoDAO;
	private IEventUserDAO eventUserDAO;
	private IEventDAO eventDAO;
	private ISurveyUserAuthDAO surveyUserAuthDAO;
	private IMahanaduDashBoardService mahanaduDashBoardService;
	private IEventAttendeeErrorDAO eventAttendeeErrorDAO;
	
	public IEventDAO getEventDAO() {
		return eventDAO;
	}

	public void setEventDAO(IEventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}

	public IEventUserDAO getEventUserDAO() {
		return eventUserDAO;
	}

	public void setEventUserDAO(IEventUserDAO eventUserDAO) {
		this.eventUserDAO = eventUserDAO;
	}

	public ITdpCadreInfoDAO getTdpCadreInfoDAO() {
		return tdpCadreInfoDAO;
	}

	public void setTdpCadreInfoDAO(ITdpCadreInfoDAO tdpCadreInfoDAO) {
		this.tdpCadreInfoDAO = tdpCadreInfoDAO;
	}

	public IVoterInfoDAO getVoterInfoDAO() {
		return voterInfoDAO;
	}

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}

	public IEventAttendeeDAO getEventAttendeeDAO() {
		return eventAttendeeDAO;
	}

	public void setEventAttendeeDAO(IEventAttendeeDAO eventAttendeeDAO) {
		this.eventAttendeeDAO = eventAttendeeDAO;
	}

	public IEventInfoDAO getEventInfoDAO() {
		return eventInfoDAO;
	}

	public void setEventInfoDAO(IEventInfoDAO eventInfoDAO) {
		this.eventInfoDAO = eventInfoDAO;
	}

	public IEventInviteeDAO getEventInviteeDAO() {
		return eventInviteeDAO;
	}

	public void setEventInviteeDAO(IEventInviteeDAO eventInviteeDAO) {
		this.eventInviteeDAO = eventInviteeDAO;
	}

	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}

	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}

	public IBloodGroupDAO getBloodGroupDAO() {
		return bloodGroupDAO;
	}

	public void setBloodGroupDAO(IBloodGroupDAO bloodGroupDAO) {
		this.bloodGroupDAO = bloodGroupDAO;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public IGovtDesignationDAO getGovtDesignationDAO() {
		return govtDesignationDAO;
	}

	public void setGovtDesignationDAO(IGovtDesignationDAO govtDesignationDAO) {
		this.govtDesignationDAO = govtDesignationDAO;
	}

	public IPartyDesignationDAO getPartyDesignationDAO() {
		return partyDesignationDAO;
	}

	public void setPartyDesignationDAO(IPartyDesignationDAO partyDesignationDAO) {
		this.partyDesignationDAO = partyDesignationDAO;
	}

	public ICadreDAO getCadreDAO() {
		return cadreDAO;
	}

	public void setCadreDAO(ICadreDAO cadreDAO) {
		this.cadreDAO = cadreDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
public ICadrePartyDesignationDAO getCadrePartyDesignationDAO() {
		return cadrePartyDesignationDAO;
	}

	public void setCadrePartyDesignationDAO(
			ICadrePartyDesignationDAO cadrePartyDesignationDAO) {
		this.cadrePartyDesignationDAO = cadrePartyDesignationDAO;
	}

	public ICadreGovtDesignationDAO getCadreGovtDesignationDAO() {
		return cadreGovtDesignationDAO;
	}

	public void setCadreGovtDesignationDAO(
			ICadreGovtDesignationDAO cadreGovtDesignationDAO) {
		this.cadreGovtDesignationDAO = cadreGovtDesignationDAO;
	}

	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}

	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}

	public IEducationalQualificationsDAO getEducationalQualificationsDAO() {
		return educationalQualificationsDAO;
	}

	public void setEducationalQualificationsDAO(
			IEducationalQualificationsDAO educationalQualificationsDAO) {
		this.educationalQualificationsDAO = educationalQualificationsDAO;
	}

	public IOccupationDAO getOccupationDAO() {
		return occupationDAO;
	}

	public void setOccupationDAO(IOccupationDAO occupationDAO) {
		this.occupationDAO = occupationDAO;
	}

	public ISocialCategoryDAO getSocialCategoryDAO() {
		return socialCategoryDAO;
	}

	public void setSocialCategoryDAO(ISocialCategoryDAO socialCategoryDAO) {
		this.socialCategoryDAO = socialCategoryDAO;
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

	public ITownshipDAO getTownshipDAO() {
		return townshipDAO;
	}

	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}

 private IBoothDAO boothDAO;
 IBoothDAO getBoothDAO() {
	return boothDAO;
}

public void setBoothDAO(IBoothDAO boothDAO) {
	this.boothDAO = boothDAO;
}
 
 public ICasteStateDAO getCasteStateDAO() {
	return casteStateDAO;
}

public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
	this.casteStateDAO = casteStateDAO;
}

public void setSurveyUserAuthDAO(ISurveyUserAuthDAO surveyUserAuthDAO) {
	this.surveyUserAuthDAO = surveyUserAuthDAO;
}

public void setMahanaduDashBoardService(
		IMahanaduDashBoardService mahanaduDashBoardService) {
	this.mahanaduDashBoardService = mahanaduDashBoardService;
}

public IEventAttendeeErrorDAO getEventAttendeeErrorDAO() {
	return eventAttendeeErrorDAO;
}

public void setEventAttendeeErrorDAO(
		IEventAttendeeErrorDAO eventAttendeeErrorDAO) {
	this.eventAttendeeErrorDAO = eventAttendeeErrorDAO;
}

public List<SelectOptionVO> getBoothsInAConstituency(Long constituencyId,Long publicationID,Long tehsilId,Long localElecBodyId){
	 List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
	 try{
		 List<Object[]> boothsList = boothDAO.getBoothOfAConstituencyByPublication(constituencyId, publicationID,tehsilId,localElecBodyId);
		 for(Object[] boothInfo:boothsList){
			 SelectOptionVO vo = new SelectOptionVO();
			 vo.setId((Long)boothInfo[0]);
			 vo.setName(boothInfo[1].toString());
			 returnList.add(vo);
		 }
	 }catch(Exception e){
		 LOG.error("Exception rised in getBoothsInAConstituency ",e);
	 }
	 return returnList;
 }
 
 public CadreVo searchCadreDetails(Long userId,Long constiId, String searchEle,String searchType,String sort,String sortBy,int startIndex,int maxResult)
 { 
	 CadreVo returnVo = new CadreVo();
	 List<CadreVo> returnList = new ArrayList<CadreVo>();
	 try {
		 String queryStr = " ";
		 
		 String[] searchBy = searchEle.split(",");
		 
		 if(searchEle.length()>0){
			 if(searchType.equalsIgnoreCase("all")){
				 queryStr = " model.firstName like '%"+searchBy[0].toString()+"%' and model.lastName like '%"+searchBy[1].toString()+"%'  and  model.mobile like '%"+searchBy[2].toString()+"%' ";
			 }
			 else if(searchType.equalsIgnoreCase("firstTwo")){
				 queryStr = " model.firstName like '%"+searchBy[0].toString()+"%' and model.lastName like '%"+searchBy[1].toString()+"%' ";
			 }
			 else if(searchType.equalsIgnoreCase("secondTwo")){
				 queryStr = " model.lastName like '%"+searchBy[0].toString()+"%'  and  model.mobile like '%"+searchBy[1].toString()+"%' ";
			 }
			 else if(searchType.equalsIgnoreCase("firstLast")){
				 queryStr = " model.firstName like '%"+searchBy[0].toString()+"%'  and  model.mobile like '%"+searchBy[1].toString()+"%' ";
			 }
			 else if(searchType.equalsIgnoreCase("firstone")){
				 queryStr = " model.firstName like '%"+searchBy[0].toString()+"%' ";
			 }
			 else if(searchType.equalsIgnoreCase("secondone")){
				 queryStr = " model.lastName like '%"+searchBy[0].toString()+"%' ";
			 }
			 else if(searchType.equalsIgnoreCase("thirdone")){
				 queryStr = " model.mobile like '%"+searchBy[0].toString()+"%' ";
			 }
		 }
		 
		 
		 	List<Object[]> cadreInfo1 =  cadreDAO.searchCadreInfoByConstidAndNameORMobile(constiId,"asc","firstName",startIndex,maxResult,queryStr,"count");
			
		 	List<Object[]> cadreInfo =  cadreDAO.searchCadreInfoByConstidAndNameORMobile(constiId,"asc","firstName",startIndex,maxResult,queryStr,null);
			
			System.out.println(cadreInfo);
			 
			if(cadreInfo != null && cadreInfo.size()>0){
				
				for (Object[] cadre : cadreInfo) {
					CadreVo vo = new CadreVo();
					vo.setCadreId(cadre[11] != null?(Long)cadre[11]:0L);
					vo.setFirstName(cadre[0] != null?cadre[0].toString():"");
					vo.setLastName(cadre[1] != null?cadre[1].toString():"");
					vo.setMobileNo(cadre[2] != null && cadre[2].toString().trim().length()>0 ?cadre[2].toString():" - ");
					vo.setMemberType(cadre[3] != null && cadre[3].toString().trim().length()>0 ? cadre[3].toString():" - ");
					vo.setImage(cadre[4] != null && cadre[4].toString().trim().length()>0 ?cadre[4].toString():"human.jpg");
				//	vo.setDistrictName(cadre[6] != null?cadre[6].toString():"");
					//vo.setConstituencyName(cadre[7] != null?cadre[7].toString():"");
					
				 /*	if(cadre[10] == null){
						vo.setMandalName(cadre[8] != null?tehsilDAO.get((Long)cadre[8]).getTehsilName()+" Mandal":"");
						vo.setVillageName(cadre[9] != null?hamletDAO.get((Long)cadre[9]).getHamletName():"");
					}
					else{
						vo.setMandalName(localElectionBodyDAO.getLocalElectionBodyName(83L)+" Muncipality ");
					}
					*/
					
					vo.setBoothNo(cadre[12] != null?Long.valueOf(boothDAO.get((Long)cadre[12]).getPartNo().toString()):0L);
					vo.setVoterCardId(cadre[13] != null? voterDAO.get((Long)cadre[13]).getVoterIDCardNo().toString():"");
					
					returnList.add(vo);
				}
				
				returnVo.setCadreVOList(returnList);
				if(cadreInfo1 != null && cadreInfo1.size()>0){
					for (Object[] couints : cadreInfo1) {
						returnVo.setCount((Long) couints[0]);
					}
				}
				
			} 
	} catch (Exception e) {
		e.printStackTrace();
		 LOG.error("Exception rised in searchCadreDetails() in mahanaduService class. ",e);
	}
	 return returnVo;
	 
 }
 
 public List<SelectOptionVO> getIncomeSources(){
	 List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
	 return returnList;
 }
 
 public List<SelectOptionVO> getCasteCategories(){
	 List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
	 try{
	 List<Object[]> casteDetails = casteStateDAO.getAllCasteDetailsForVoters(1l);
	 for(Object[] casteData:casteDetails){
		 SelectOptionVO vo = new SelectOptionVO();
		 vo.setId((Long)casteData[0]);
		 vo.setName(casteData[1].toString());
		 returnList.add(vo);
	 }
	 }catch(Exception e){
		 LOG.error("Exception rised in getCasteCategories ",e);
	 }
	 return returnList;
 }
 
 public List<SelectOptionVO> getPartyDesignations(){
	 List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
	 try{
		 List<Object[]> partyDesigDetails = partyDesignationDAO.getAllPartyDesignation();
		 for(Object[] partyDesig:partyDesigDetails){
			 SelectOptionVO vo = new SelectOptionVO();
			 vo.setId((Long)partyDesig[0]);
			 vo.setName(partyDesig[1].toString());
			 returnList.add(vo);
		 }
	 }catch(Exception e){
		 LOG.error("Exception rised in getPartyDesignations ",e);
	 }
	 return returnList;
 }
 
 public List<SelectOptionVO> getgovernmentDesignations(){
	 List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
	 List<Object[]> govDesigDetails = govtDesignationDAO.getAllGovtDesignation();
	 for(Object[] govDesig:govDesigDetails){
		 SelectOptionVO vo = new SelectOptionVO();
		 vo.setId((Long)govDesig[0]);
		 vo.setName(govDesig[1].toString());
		 returnList.add(vo);
	 }
	 return returnList;
 }


 public ResultStatus saveCadreInfoForMahaNadu(CadreVo CadreVoToSave)
	{
		
		
		
			ResultStatus rs = saveCadreDetails(CadreVoToSave);
		
		
	
		return rs;	
	}
	public final String getExceptionMessage(String expClass) {

		if ("class java.lang.NullPointerException".equalsIgnoreCase(expClass))
			return IConstants.NULL_POINTER_EXCEPTION;
		else if ("class java.lang.ArrayIndexOutOfBoundsException"
				.equalsIgnoreCase(expClass))
			return IConstants.ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION;
		else if ("class java.lang.NumberFormatException"
				.equalsIgnoreCase(expClass))
			return IConstants.NUMBER_FORMAT_EXCEPTION;
		else
			return IConstants.GENERAL_EXCEPTION;

	}
	@SuppressWarnings("unchecked")
	private Boolean checkForHamlet(Long tehsilId,Long hamletId){
		
		List hamletData = hamletDAO.findByTehsilIdAndHamletId(hamletId, tehsilId);
		
		if(hamletData != null && hamletData.size() > 0)
			return true;
		
	 return false;
	}
	public ResultStatus saveCadreDetails (final CadreVo cadreInfo) 
	{
		ResultStatus rs = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {

			UserAddress currentAddress = new UserAddress();
			ResultStatus rs = new ResultStatus();
			Cadre cadre = null;
			if(cadreInfo.getCadreId() != null && cadreInfo.getCadreId() > 0)
				cadre = cadreDAO.get(cadreInfo.getCadreId());
			else{
				cadre = new Cadre();
				cadre.setUser(userDAO.get(cadreInfo.getUserId()));
			}
			try {
				
				
				cadre.setFirstName(cadreInfo.getFirstName());
				cadre.setLastName(cadreInfo.getLastName());
				
				cadre.setGender(cadreInfo.getGender());
				if(cadreInfo.getAge() != null && (!StringUtils.isBlank(cadreInfo.getAge().toString())))
				cadre.setAge(cadreInfo.getAge());
				if(cadreInfo.getFatherName() != null && (!StringUtils.isBlank(cadreInfo.getFatherName())))
				cadre.setFatherOrSpouseName(cadreInfo.getFatherName());
				if(cadreInfo.getBloodGroupId() != null && cadreInfo.getBloodGroupId() != 0){
				   cadre.setBloodGroup(cadreInfo.getBloodGroupId() != 0 ? bloodGroupDAO.get(cadreInfo.getBloodGroupId())  : null);
				   cadre.setBloodGroupId(cadreInfo.getBloodGroupId());
			    }
				if(cadreInfo.getNoOfFamilyMembers() != null && (!StringUtils.isBlank(cadreInfo.getNoOfFamilyMembers())))
				cadre.setNoOfFamilyMembers(cadreInfo.getNoOfFamilyMembers());
				if(cadreInfo.getNoOfVoters() != null && (!StringUtils.isBlank(cadreInfo.getNoOfVoters())))
				cadre.setNoOfVoters(cadreInfo.getNoOfVoters());
				
				cadre.setMobile(cadreInfo.getMobileNo());
				if(cadreInfo.getLandNo() != null && (!StringUtils.isBlank(cadreInfo.getLandNo())))
				cadre.setTelephone(cadreInfo.getLandNo());
				if(cadreInfo.getEmailId() != null && (!StringUtils.isBlank(cadreInfo.getEmailId())))
				cadre.setEmail(cadreInfo.getEmailId());
				cadre.setIsMahanadu("Y");
				if (!cadreInfo.getEducationId().equals(new Long(0)))
					cadre.setEducation(educationalQualificationsDAO.get(cadreInfo.getEducationId()));
					
				if(cadreInfo.getVoterCardId()!= null && cadreInfo.getVoterCardId().trim().length() > 0){
					List<Long> voterIds = voterDAO.getVoterId(cadreInfo.getVoterCardId().trim());
					if(voterIds != null && voterIds.size() > 0 && voterIds.get(0) != null){
						cadre.setVoter(voterDAO.get(voterIds.get(0)));
					}
				}
				if(cadreInfo.getIsVerified().longValue() == 1l){
					cadre.setIsVerified("N");
				}else{
					cadre.setIsVerified("Y");
				}
				if(cadreInfo.getProfessionId() != null && cadreInfo.getProfessionId() > 0)
				cadre.setOccupation(occupationDAO.get(cadreInfo.getProfessionId()));
				if(cadreInfo.getCasteCategory() != null && cadreInfo.getCasteCategory() > 0)
				cadre.setCasteState(casteStateDAO.get(cadreInfo.getCasteCategory()));
				Double annunaIncome = null;
				if (cadreInfo.getAnnualIncome() != null && (!StringUtils.isBlank(cadreInfo.getAnnualIncome())))
					annunaIncome = new Double(cadreInfo.getAnnualIncome().trim());

				cadre.setAnnualIncome(annunaIncome);
	
				if (cadreInfo.getSourceIncome() != null && cadreInfo.getSourceIncome().trim().length() > 0)
				{	
				  cadre.setIncomeSource(cadreInfo.getSourceIncome());
				}
				SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_PATTERN);
				if (cadreInfo.getActiveDateField() != null && (!StringUtils.isBlank(cadreInfo.getActiveDateField()))) {
					cadre.setActiveDateField(format.parse(cadreInfo.getActiveDateField()));
				} 
				cadre.setMemberType(cadreInfo.getMemberType());
				if(cadreInfo.getAddress() != null && (!StringUtils.isBlank(cadreInfo.getAddress())))
				cadre.setAddress(cadreInfo.getAddress());
				// Current Address
				
				//currentAddress.setHouseNo(cadreInfo.getHno());
				//currentAddress.setStreet(cadreInfo.getStreet());
				//currentAddress.setPinCode(cadreInfo.getPinCode());
				
				if(cadreInfo.getDistrictId() != null && cadreInfo.getDistrictId().longValue() > 0)
					 currentAddress.setDistrict(districtDAO.get(new Long(cadreInfo.getDistrictId())));
				if(cadreInfo.getConstituencyId() != null && cadreInfo.getConstituencyId().longValue() > 0)
				  currentAddress.setConstituency(constituencyDAO.get(cadreInfo.getConstituencyId()));
				if(cadreInfo.getBoothNo() != null && cadreInfo.getBoothNo().longValue() > 0)
				{
					currentAddress.setBooth(boothDAO.get(cadreInfo.getBoothNo()));
				}
				
				/*if(cadreInfo.getMandalId()!= null){
					currentAddress.setTehsil(tehsilDAO.get(new Long(cadreInfo.getMandalId())));
				}
				if(cadreInfo.getVillageId()!=null ){
					Boolean isHamlet = checkForHamlet(cadreInfo.getMandalId(),cadreInfo.getVillageId());
					
					//if location details are hamlet
					if(isHamlet)
					    currentAddress.setHamlet(hamletDAO.get(new Long(cadreInfo.getVillageId())));
					//if location details are township
					else
						currentAddress.setTownship(townshipDAO.get(new Long(cadreInfo.getVillageId())));
					
				}*/
				
				
				currentAddress = userAddressDAO.save(currentAddress);
				cadre.setCurrentAddress(currentAddress);
				cadre = cadreDAO.save(cadre);
				if(cadreInfo.getUploadImage() != null){
					String result = uploadCadreImage(cadre.getCadreId(), cadreInfo.getPath(), cadreInfo.getUploadImageContentType(), cadreInfo.getUploadImage());
					if(result != null){
						cadreManagementService.updateCadreImage(cadre.getCadreId(),cadre.getCadreId().toString()+"."+cadreInfo.getUploadImageContentType().split("/")[1]);
					}else{
						cadreManagementService.updateCadreImage(cadre.getCadreId(),"human.jpg");
					}
				}
				if(cadreInfo.getBase64Image() != null && cadreInfo.getBase64Image().trim().length() > 0){
					String result = convertBase64StringToImage(cadreInfo.getBase64Image(),cadre.getCadreId().toString(),cadreInfo.getPath());
					if(result.equalsIgnoreCase("success")){
						cadreManagementService.updateCadreImage(cadre.getCadreId(),cadre.getCadreId().toString()+".jpg");
					}
				}
				cadrePartyDesignationDAO.deleteExisting(cadre.getCadreId());
				cadreGovtDesignationDAO.deleteExisting(cadre.getCadreId());
				if (cadre != null)
				{
					LOG.debug("inside cadre obj block");
					
					if(cadreInfo.getPartyDesignationList() != null && cadreInfo.getPartyDesignationList().size() > 0)
					{
						for(Long partyDesgId : cadreInfo.getPartyDesignationList())
						{
							if(partyDesgId != null){
								CadrePartyDesignation cadrePartyDesignation = new CadrePartyDesignation();
								cadrePartyDesignation.setCadre(cadre);
								cadrePartyDesignation.setPartyDesignation(partyDesignationDAO.get(partyDesgId));
								cadrePartyDesignationDAO.save(cadrePartyDesignation);
							}
						}
					}
					
					if(cadreInfo.getGovtDesignationList() != null && cadreInfo.getGovtDesignationList().size() > 0)
					{
						for(Long govtDesgId : cadreInfo.getGovtDesignationList())
						{
						 if(govtDesgId != null){
							CadreGovtDesignation cadreGovtDesignation = new CadreGovtDesignation();
							cadreGovtDesignation.setCadre(cadre);
							cadreGovtDesignation.setGovtDesignation(govtDesignationDAO.get(govtDesgId));
							cadreGovtDesignationDAO.save(cadreGovtDesignation);
						 }
						}
					}
					
					
					
				}
				rs.setResultCode(ResultCodeMapper.SUCCESS);
			}
			catch(Exception e)
			{
				rs.setExceptionEncountered(e);
				rs.setExceptionClass(e.getClass().toString());
				rs.setExceptionMsg(getExceptionMessage(e.getClass().toString()));
				rs.setResultCode(ResultCodeMapper.FAILURE);
				rs.setResultPartial(true);
				e.printStackTrace();
				LOG.error("Exception saveCadreDetails ", e);
				LOG.debug(e);
			}
				return rs;
			} });
		return rs;
		}
	
	
	public String uploadCadreImage(Long cadreId,String url,String uploadImageContentType,File uploadImage)
	{
		try{
			String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			
			String filePath = url + "images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator;
			
			LOG.info("Cadre File Path -- "+filePath);
			
			BufferedImage image = ImageIO.read(uploadImage);
			
			
			if(image == null)
				return null;
			LOG.info("Image is Read");
			String constiName[] = uploadImageContentType.split("/");
			String fileName = filePath+cadreId.toString()+"."+constiName[1];
			LOG.info("file name -- "+fileName);
			//String imageName =  cadreId.toString()+"."+constiName[1];
			
			FileImageOutputStream filName = new FileImageOutputStream(new File(fileName));
			
			ImageIO.write(image, constiName[1],filName);
			LOG.info("file uploaded");
            filName.close();
            return "success";
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
public CadreVo getCadreCompleteInfo(Long cadreId) {
		
	CadreVo cadreInfo = new CadreVo();
		try
		{
			Cadre cadre = cadreDAO.get(cadreId);
			cadreInfo = convertCadreToCadreVo(cadre);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return cadreInfo;
	}

@SuppressWarnings("deprecation")
public CadreVo convertCadreToCadreVo(Cadre cadre) {
	
	CadreVo cadreInfo = new CadreVo();
	SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	UserAddress currentAddress;
	
	cadreInfo.setCadreId(cadre.getCadreId());
	cadreInfo.setFirstName(cadre.getFirstName());
	cadreInfo.setLastName(cadre.getLastName());
	cadreInfo.setBloodGroupId(cadre.getBloodGroup() != null ? cadre.getBloodGroup().getBloodGroupId() : null);
	cadreInfo.setBloodGroupStr(cadre.getBloodGroup() != null ? cadre.getBloodGroup().getBloodGroup() : "");
	cadreInfo.setFatherName(cadre.getFatherOrSpouseName()!=null ? cadre.getFatherOrSpouseName():"");
	cadreInfo.setNoOfFamilyMembers(cadre.getNoOfFamilyMembers()!=null ? cadre.getNoOfFamilyMembers() : "");
	cadreInfo.setNoOfVoters(cadre.getNoOfVoters() != null ? cadre.getNoOfVoters() : "");
	cadreInfo.setAge(cadre.getAge() != null ? cadre.getAge() : null);
	cadreInfo.setGender(cadre.getGender() != null ? cadre.getGender() : "");
	cadreInfo.setMobileNo(cadre.getMobile() != null ? cadre.getMobile() : "");
	cadreInfo.setLandNo(cadre.getTelephone() != null ? cadre.getTelephone() : "");
	cadreInfo.setEmailId(cadre.getEmail() != null ? cadre.getEmail() : "");
	if(cadre.getVoter() != null){
		cadreInfo.setVoterCardId(cadre.getVoter().getVoterIDCardNo());
	}
	if(cadre.getIsVerified().equalsIgnoreCase("N")){
		cadreInfo.setIsVerified(1l);
	}else{
		cadreInfo.setIsVerified(2l);
	}
	if(cadre.getImage() != null && cadre.getImage().trim().length() > 0){
		cadreInfo.setPath(cadre.getImage());
	}
	currentAddress = cadre.getCurrentAddress();
	if(cadre.getAddress() != null){
		cadreInfo.setAddress(cadre.getAddress());
	}else{
		cadreInfo.setAddress("");
	}
	if(currentAddress != null)
	{
	//cadreInfo.setHno(currentAddress.getHouseNo() != null ? currentAddress.getHouseNo() :"");
	//cadreInfo.setStreet(currentAddress.getStreet() != null ? currentAddress.getStreet() : "");
	//cadreInfo.setPinCode(currentAddress.getPinCode() != null ? currentAddress.getPinCode() : "");
	// retrieving current address(CA) locations
	//State stateCA = currentAddress.getState();
	District districtCA = currentAddress.getDistrict();
	Constituency constituencyCA = currentAddress.getConstituency();
	Booth boothCA = currentAddress.getBooth();
	
	if(districtCA != null)
	{
		cadreInfo.setDistrictId(districtCA.getDistrictId());
		cadreInfo.setDistrictName(districtCA.getDistrictName()+" (Dt.)");
	}
	if(constituencyCA != null)
	{
	cadreInfo.setConstituencyId(constituencyCA.getConstituencyId());
	cadreInfo.setConstituencyName(constituencyCA.getName());
	}
	
	if(boothCA != null)
	{
		cadreInfo.setBoothNo(boothCA.getBoothId());
		cadreInfo.setBoothName("Booth No"+boothCA.getPartNo()+" - "+boothCA.getLocation());
	}
	
	}
	
	if(cadre.getEducation() != null){
		cadreInfo.setEducationId(cadre.getEducation().getEduQualificationId());
		cadreInfo.setEducationStr(cadre.getEducation().getQualification());
	}
	if(cadre.getOccupation() != null){
		cadreInfo.setProfessionId(cadre.getOccupation().getOccupationId());
		cadreInfo.setProfessionStr(cadre.getOccupation().getOccupation());		
	}
	if(cadre.getAnnualIncome() != null)
	cadreInfo.setAnnualIncome(new Long(cadre.getAnnualIncome().longValue()).toString());
	if(cadre.getIncomeSource() != null)
		cadreInfo.setSourceIncome(cadre.getIncomeSource().toString());
	else
		cadreInfo.setSourceIncome("");
	if(cadre.getCasteState() != null){
		cadreInfo.setCasteCategory(cadre.getCasteState().getCasteStateId());
		cadreInfo.setCasteCategoryName(cadre.getCasteState().getCaste().getCasteName());
	}
	
	cadreInfo.setMemberType(cadre.getMemberType());
	if(cadre.getActiveDateField() != null){
	    Date date = cadre.getActiveDateField();
		Calendar c = Calendar.getInstance(); 
		c.setTime(date);
		String dateStr = "";
		int day =c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		dateStr=dateStr+day+"/"+month+"/"+year;
		cadreInfo.setActiveDateField(dateStr);
	}else{
		cadreInfo.setActiveDateField("");
	}
	
	List<Object[]> partyDesignations=cadrePartyDesignationDAO.getPartyDesignationsByCadreId(cadre.getCadreId());
	List<Long> partyDesignationList = new ArrayList<Long>();
	List<Long> govtDesignationList = new ArrayList<Long>();
	if(partyDesignations != null && partyDesignations.size() > 0)
	{
		for(Object[] params : partyDesignations)
		{
			if(!partyDesignationList.contains((Long)params[0]))
			partyDesignationList.add((Long)params[0]);
		}
	}
	
	List<Object[]> govtDesignations=cadreGovtDesignationDAO.findByCadreId(cadre.getCadreId());
	if(govtDesignations != null && govtDesignations.size() > 0)
	{
		for(Object[] params1 : govtDesignations)
		{
			if(!govtDesignationList.contains((Long)params1[0]))
			govtDesignationList.add((Long)params1[0]);
		}
	}
	cadreInfo.setPartyDesignationList(partyDesignationList);
	cadreInfo.setGovtDesignationList(govtDesignationList);
	return cadreInfo;
}

public CadreVo searchVoterInfo(Long userId,Long boothId, String searchName,String searchType,String sort,String sortBy,int startIndex,int maxResult)
{ 
	 CadreVo returnVo = new CadreVo();
	 List<CadreVo> returnList = new ArrayList<CadreVo>();
	 try {
		 
		 	Long publicationDateId = Long.valueOf(IConstants.STRATAGIC_REPORT_PUBLICATION_DATE_ID);
		 	Long count = boothPublicationVoterDAO.searchVoterdetailsByBoothAndNameCount(boothId,searchName,searchType,publicationDateId,startIndex,maxResult,"count");
		 	
		 	if(count !=null ){
		 		
		 			returnVo.setCount(count);
		 	}
		 	
		 	
			List<Object[]> votersList = boothPublicationVoterDAO.searchVoterdetailsByBoothAndName(boothId,searchName,searchType,publicationDateId,startIndex,maxResult,null);
			
			if(votersList != null && votersList.size()>0){
				for (Object[] voter : votersList) {
						CadreVo vo = new CadreVo();
						
						vo.setCadreId(voter[0] !=null ? (Long)voter[0] :0L);
						vo.setFirstName(voter[1] != null ? voter[1].toString():"");
						vo.setAddress(voter[2] != null ? voter[2].toString():"");
						vo.setBooth(voter[3] != null ? voter[3].toString():"");
						vo.setVoterCardId(voter[4] != null ? voter[4].toString():"");
						vo.setFatherName(voter[5] != null ? voter[5].toString():"");
						returnList.add(vo);						
				}
			}
			
			returnVo.setCadreVOList(returnList);
	} catch (Exception e) {
		//e.printStackTrace();
		 LOG.error("Exception rised in searchVoterInfo() in mahanaduService class. ",e);
	}
	 return returnVo;
	 
}


public CadreVo getDetailToPopulate(String voterIdCardNo,Long publicationId)
{
	CadreVo result = new CadreVo();
	try{
		List<Object[]> list = boothPublicationVoterDAO.getDetailsByVoterIdCardNo(voterIdCardNo,publicationId);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				result.setBoothNo((Long)params[0]);
				result.setBooth(params[1].toString());
				result.setVoterCardId(params[2].toString());
				result.setDistrictId((Long)params[4]);
				result.setConstituencyId((Long)params[3]);
				result.setFirstName(params[5] !=null ? params[5].toString() : " ");
				result.setAge((Long)params[6]);
				if(params[7] !=null && params[7].toString().trim().equalsIgnoreCase("Father"))
				   result.setFatherName(params[8] !=null ? params[8].toString() : " ");
				else
					 result.setFatherName("");
				result.setGender(params[9].toString());
			}
		}
		
		
	}
	catch (Exception e) {
	Log.error("Exception in getDetailToPopulate()");
	}
	return result;
}

 public String convertBase64StringToImage(String imageDataString,String cadreId,String url){
	   FileOutputStream imageOutFile = null;
	 try {       
		 byte[] imageByteArray = Base64.decodeBase64(imageDataString.getBytes());
		 String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
		 String filePath = url + "images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator;
		 String fileName = filePath+cadreId.toString()+".jpg";
		  imageOutFile = new FileOutputStream(fileName);
	     imageOutFile.write(imageByteArray);
	    return "success";
	 }catch(Exception e){
		 Log.error("Exception rised in convertBase64StringToImage()",e);
		 if(imageOutFile != null){
			 try{
			 imageOutFile.close();
			 }catch(Exception e1){
				 Log.error("Exception rised in convertBase64StringToImage() while closing write operation",e1);
			 }
		 }
	 }
	 return "fail";
 }
 
 public ResultStatus insertDataintoEventInfo()
 {
	 ResultStatus result = new ResultStatus();
	 List<Object[]> list = null;
	 List<Object[]> list1 = null;
	 List<Object[]> list2 = null;
	 List<Long> events  = null;
	 List<Object[]> statewise = null;
	 List<Object[]> statewise1 = null;
	 try{
		 DateUtilService date = new DateUtilService();
		events = eventInfoDAO.getEventIds(2l,date.getCurrentDateAndTime());
		 if(events != null && events.size() > 0)
		 eventInfoDAO.deleteEventInfo(2l,events) ;
		 voterDAO.flushAndclearSession();
		 statewise1= eventAttendeeDAO.getStateWiseEventAttendeeInfo("attendee",date.getCurrentDateAndTime(),1l);
		statewise= eventAttendeeDAO.getStateWiseEventAttendeeInfo("invitee",date.getCurrentDateAndTime(),1l);
		setInviteeInfoForState(statewise1,2l,"attendee",1l);
		setInviteeInfoForState(statewise,2l,"invitee",1l);
		
		statewise1= eventAttendeeDAO.getStateWiseEventAttendeeInfo("attendee",date.getCurrentDateAndTime(),36l);
		statewise= eventAttendeeDAO.getStateWiseEventAttendeeInfo("invitee",date.getCurrentDateAndTime(),36l);
		setInviteeInfoForState(statewise1,2l,"attendee",36l);
		setInviteeInfoForState(statewise,2l,"invitee",36l);
		 events = eventInfoDAO.getEventIds(3l,date.getCurrentDateAndTime());
		    if(events != null && events.size() > 0)
			 eventInfoDAO.deleteEventInfo(3l,events) ;
			 voterDAO.flushAndclearSession();
			
		 
		 list2= eventAttendeeDAO.getEventAttendeeInfo(IConstants.DISTRICT,"attendee",date.getCurrentDateAndTime());
		 list1= eventAttendeeDAO.getEventAttendeeInfo(IConstants.DISTRICT,"invitee",date.getCurrentDateAndTime());
		 
		 	setInviteeInfo(list2,3l,"attendee");
	      setInviteeInfo(list1,3l,"invitee");
		  
		 events = eventInfoDAO.getEventIds(4l,date.getCurrentDateAndTime());
		 if(events != null && events.size() > 0)
		 eventInfoDAO.deleteEventInfo(4l,events) ;
		 voterDAO.flushAndclearSession();
		
		 list2= eventAttendeeDAO.getEventAttendeeInfo(IConstants.CONSTITUENCY,"attendee",date.getCurrentDateAndTime());
		 list1= eventAttendeeDAO.getEventAttendeeInfo(IConstants.CONSTITUENCY,"invitee",date.getCurrentDateAndTime());
		
		 setInviteeInfo(list2,4l,"attendee");
		 setInviteeInfo(list1,4l,"invitee");
		 
		 
	 }
	 catch(Exception e)
	 {
		 Log.error("Exception rised in insertDataintoEventInfo() while closing write operation",e); 
		 e.printStackTrace();
	 }
	return result;
 }
 

 
 public void setInviteeInfo(List<Object[]> list,Long reportLevelId,String type)
 {
	 List<Long> locationValues = new ArrayList<Long>();
	 try{
		DateUtilService date = new DateUtilService();
		 List<EventActionPlanVO> resultList = new ArrayList<EventActionPlanVO>();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 if(list != null && list.size() > 0)
		 {
			
			 List<Object[]> checkList = eventInfoDAO.getEventInfo(reportLevelId);
			 if(checkList != null && checkList.size() > 0)
			 {
				 for(Object[] params : checkList)
				 {
					 EventActionPlanVO eventActionPlanVO = new EventActionPlanVO();
					 eventActionPlanVO.setId((Long)params[0]);
					 eventActionPlanVO.setLocationValue((Long)params[1]);
					 eventActionPlanVO.setEventId((Long)params[2]);
					 eventActionPlanVO.setMessage(params[3] != null ? params[3].toString() : "");
					 resultList.add(eventActionPlanVO);
				 }
			 }
			 for(Object[] params : list)
			 {
				 EventInfo eventInfo = null;
				 EventActionPlanVO eventActionPlanVO  = getMatchedEventVo(resultList,(Long)params[2],(Long)params[0],params[3].toString());
				 if(eventActionPlanVO == null)
				 {
				 eventInfo = new EventInfo();
				 eventInfo.setEventId((Long)params[0]);
				 eventInfo.setReportLevelId(reportLevelId);
				 eventInfo.setLocationValue((Long)params[2]);
				 eventInfo.setDate(format.parse(params[3].toString()));
				 if(type.equalsIgnoreCase("invitee"))
				 eventInfo.setInvitees((Long)params[1]);
				 if(type.equalsIgnoreCase("attendee"))
				 {
				 eventInfo.setTotalAttendes((Long)params[1]);
				 eventInfo.setNoninvitees((Long)params[1]);
				 }
				 eventInfo.setInsertedTime(date.getCurrentDateAndTime());
				 eventInfoDAO.save(eventInfo);
				
				 }
				 else
				 {
					 eventInfo = eventInfoDAO.get(eventActionPlanVO.getId()); 
					 if(type.equalsIgnoreCase("attendee"))
						 eventInfo.setTotalAttendes((Long)params[1]);
					 else if(type.equalsIgnoreCase("invitee"))
					 {
						 eventInfo.setInvitees((Long)params[1]);
						 eventInfo.setNoninvitees(eventInfo.getTotalAttendes() - (Long)params[1]);
					 }
					 eventInfo.setInsertedTime(date.getCurrentDateAndTime());
						 eventInfoDAO.save(eventInfo);
				 }
				 if(!locationValues.contains((Long)params[2]))
					 locationValues.add((Long)params[2]); 
				 voterDAO.flushAndclearSession();
			 }
			
				 List<Object[]>  districts = eventInfoDAO.getDistricts(locationValues,reportLevelId) ;
				 Map<Long,List<Long>> stateMap = new HashMap<Long,List<Long>>();
				 if(districts != null && districts.size() > 0)
				 {
					 for(Object[] params : districts)
					 {
						if((Long)params[1] < 11)
						{
							List<Long> values =stateMap.get(36l);
							if(values == null)
							{
								values = new ArrayList<Long>();
							}
							values.add((Long)params[0]);
							stateMap.put(36l, values);
						}
						
						if((Long)params[1] > 10)
						{
							List<Long> values =stateMap.get(1l);
							if(values == null)
							{
								values = new ArrayList<Long>();
							}
							values.add((Long)params[0]);
							stateMap.put(1l, values);
						}
							
					 }
					 List<Long> apValues = stateMap.get(1l);
					 List<Long> tsValues = stateMap.get(36l);
					 if(apValues != null && apValues.size() > 0)
						 eventInfoDAO.updateState(apValues,reportLevelId,1l);
					 if(tsValues != null && tsValues.size() > 0)
						 eventInfoDAO.updateState(tsValues,reportLevelId,36l);
					 voterDAO.flushAndclearSession();
				 }
				 
			 
		 }
	 }
	 catch(Exception e)
	 {
		 Log.error("Exception rised in setInviteeInfo() while closing write operation",e); 
		 e.printStackTrace();
	 }
 }
 
 public void setInviteeInfoForState(List<Object[]> list,Long reportLevelId,String type,Long stateId)
 {
	
	 try{
		DateUtilService date = new DateUtilService();
		 List<EventActionPlanVO> resultList = new ArrayList<EventActionPlanVO>();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 if(list != null && list.size() > 0)
		 {
			
			 List<Object[]> checkList = eventInfoDAO.getEventInfoForState(reportLevelId,stateId);
			 if(checkList != null && checkList.size() > 0)
			 {
				 for(Object[] params : checkList)
				 {
					 EventActionPlanVO eventActionPlanVO = new EventActionPlanVO();
					 eventActionPlanVO.setId((Long)params[0]);
					 eventActionPlanVO.setLocationValue((Long)params[1]);
					 eventActionPlanVO.setEventId((Long)params[2]);
					 eventActionPlanVO.setMessage(params[3] != null ? params[3].toString() : "");
					 resultList.add(eventActionPlanVO);
				 }
			 }
			 for(Object[] params : list)
			 {
				 EventInfo eventInfo = null;
				 EventActionPlanVO eventActionPlanVO  = getMatchedEventVo(resultList,stateId,(Long)params[0],params[2].toString());
				 if(eventActionPlanVO == null)
				 {
				 eventInfo = new EventInfo();
				 eventInfo.setEventId((Long)params[0]);
				 eventInfo.setReportLevelId(reportLevelId);
				 eventInfo.setLocationValue(stateId);
				 eventInfo.setDate(format.parse(params[2].toString()));
				 eventInfo.setStateId(stateId);
				 if(type.equalsIgnoreCase("invitee"))
				 eventInfo.setInvitees((Long)params[1]);
				 if(type.equalsIgnoreCase("attendee"))
				 {
				 eventInfo.setTotalAttendes((Long)params[1]);
				 eventInfo.setNoninvitees((Long)params[1]);
				 }
				 eventInfo.setInsertedTime(date.getCurrentDateAndTime());
				 eventInfoDAO.save(eventInfo);
				 }
				 else
				 {
					 try {
						 eventInfo = eventInfoDAO.get(eventActionPlanVO.getId()); 
						 if(type.equalsIgnoreCase("attendee"))
							 eventInfo.setTotalAttendes((Long)params[1]);
						 else if(type.equalsIgnoreCase("invitee"))
						 {
							 eventInfo.setInvitees((Long)params[1]);
							 eventInfo.setNoninvitees(eventInfo.getTotalAttendes() - (Long)params[1]);
						 }
						 eventInfo.setInsertedTime(date.getCurrentDateAndTime());
						 eventInfoDAO.save(eventInfo);
					} catch (Exception e) {
						e.printStackTrace();
					}
				 }
				 voterDAO.flushAndclearSession(); 
			 }
			// voterDAO.flushAndclearSession();
				
		 }
	 }
	 catch(Exception e)
	 {
		 Log.error("Exception rised in setInviteeInfo() while closing write operation",e); 
		 e.printStackTrace();
	 }
 }
 public EventActionPlanVO getMatchedEventVo(List<EventActionPlanVO> resultList,Long location,Long event,String date)
 {
	 try{
		 
		 if(resultList == null || resultList.size() == 0)
			 return null;
		 for(EventActionPlanVO vo : resultList)
		 {
			 if(vo.getLocationValue() != null)
			 if((vo.getLocationValue().longValue() == location.longValue() && vo.getEventId().longValue() == event.longValue() && vo.getMessage().equalsIgnoreCase(date)))
				 return vo;
				 
		 }
	 }
	 catch(Exception e)
	 {
		 Log.error("Exception rised in getMatchedEventVo() while closing write operation",e); 
		 e.printStackTrace(); 
	 }
	return null;
 }
 
 public List<MahanaduEventVO> getEventInfoByReportType(Long eventId,Long stateId,Long reportLevelId,List<Long> subEventIds,String startDate,String endDate)
 {
	 List<MahanaduEventVO>  resultList = new ArrayList<MahanaduEventVO>();
	 Date eventStrDate = null;
	 Date eventEndDate = null;
	 try{
		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		 if(startDate != null && !startDate.isEmpty())
		 eventStrDate = format.parse(startDate);
		 if(endDate != null && !endDate.isEmpty())
		 eventEndDate = format.parse(endDate);
		 String locationType = "";
		 if(reportLevelId == 2l)
			 locationType = "State";
		 else if(reportLevelId == 3l)
			 locationType = "District";
		 else if(reportLevelId == 4l)
			 locationType = "Constituency";
		 DateUtilService date = new DateUtilService();
		  List<Object[]> list = eventInfoDAO.getEventDataByReportLevelId(reportLevelId,eventId,stateId,subEventIds,eventStrDate,eventEndDate);
		  Set<Long> reportLevelValues = new java.util.HashSet<Long>();
		  if(list != null && list.size() > 0)
		  {
			  for(Object[] params : list)
			  {
				  MahanaduEventVO eventVo = new MahanaduEventVO();
				  eventVo.setId((Long)params[0]);
				  if(reportLevelId != 2l){
					  eventVo.setName(getLocationName(reportLevelId,(Long)params[0]));
				  }
				 
				  eventVo.setInvitees(params[1] != null ? (Long)params[1] : 0l);
				  eventVo.setNonInvitees(params[2] != null ? (Long)params[2] : 0l);			 
				  eventVo.setAttendees(eventVo.getInvitees() + eventVo.getNonInvitees());				
				  resultList.add(eventVo);
				  reportLevelValues.add((Long)params[0]);
			  }
			  if(reportLevelId != 2l){
			  List<Object[]> votersCount  = null;
				 if(reportLevelId == 3l){
					 votersCount = voterInfoDAO.getVotersCountForDistrict(reportLevelValues, 11l);
				 }
				 else if(reportLevelId == 4l){
					 votersCount = voterInfoDAO.getVotersCountByLocationValues(1L, reportLevelValues, 11l,null);
				 }
				 
			 List<Object[]> cadreCount = tdpCadreInfoDAO.getLocationWiseCadreRegisterCount(reportLevelValues,locationType,null,"Registered");
			 
			 if(votersCount != null && votersCount.size() > 0){
				 for(Object[] obj :votersCount){
					 MahanaduEventVO vo = getMatchedVO(resultList, (Long)obj[0]);
					 if(vo != null){
						 vo.setVoterCount((Long)obj[1]);
					 }
				 }				 
			 }
			 
			 if(cadreCount != null && cadreCount.size() > 0){
				 for(Object[] obj :cadreCount){
					 MahanaduEventVO vo = getMatchedVO(resultList, (Long)obj[0]);
					 if(vo != null){
						 vo.setCadreCount((Long)obj[1]);
					 }
				 }				 
			 }
			
			/* List<Object[]>  inviteesCnt = eventInviteeDAO.getEventInviteesCountByLocationTypeAndEvent(locationType,date.getCurrentDateAndTime(),eventId);
			 if(inviteesCnt != null && inviteesCnt.size() > 0){
				 for(Object[] obj :inviteesCnt){
					 MahanaduEventVO vo = getMatchedVO(resultList, (Long)obj[1]);
					 if(vo != null){
						 vo.setTotal((Long)obj[0]);
					 }
				 }				 
			 }*/
		  }/*else{
			  Long totalCnt = eventInviteeDAO.getEventInviteesCountByState(stateId,date.getCurrentDateAndTime(),eventId);				
						 MahanaduEventVO vo = getMatchedVO(resultList,stateId);
						 if(vo != null){
							 vo.setTotal(totalCnt);
						 }					 
		  }
			  */
			  
		  }
	 }
	 catch(Exception e)
	 {
		 Log.error("Exception rised in getEventInfoByReportType() while closing write operation",e); 
		 e.printStackTrace();
	 }
	return resultList;
 }
 
 public String getLocationName(Long LocationTypeId,Long locationValue){
		String location ="";
		if(LocationTypeId.longValue() == 3L){
			location = districtDAO.get(locationValue).getDistrictName();
			}
		else if(LocationTypeId.longValue() == 4L)
		{
			location = constituencyDAO.get(locationValue).getName();
		}
	
		return location;
	}
 
/* public List<MahanaduEventVO> getSubEventInfo(Long parentId,Long userId)
 {
	 List<MahanaduEventVO> resultList = new ArrayList<MahanaduEventVO>();
	 Date eventStrDate = null;
	 Date eventEndDate = null;
	 try{
		
		 List<Long> parentEventIds = new ArrayList<Long>();
		 parentEventIds.add(parentId);
		 DateUtilService date = new DateUtilService();
		 List<Object[]> list = eventUserDAO.getEventsByUserAndParentIds(userId,date.getCurrentDateAndTime(),parentEventIds);
		 if(list != null && list.size() > 0)
		 {
			List<Long> eventIds = new ArrayList<Long>();
			 for(Object[] params : list)
			 {
				 MahanaduEventVO vo = new MahanaduEventVO();
				 vo.setId((Long)params[0]);
				 vo.setName(params[1] != null ? params[1].toString() : "");
				 if(params[4] != null)
				 vo.setStartTime(TimeForm(params[4].toString()));
				 if(params[5] != null)
					 vo.setEndTime(TimeForm(params[4].toString())); 
				 vo.setDesc(params[3] != null ? params[3].toString():"");
				 vo.setInviteeExists(params[5] != null ? params[5].toString():"");
				 resultList.add(vo);
				 eventIds.add((Long)params[0]);
			 }
			 List<Object[]> attendeeInfo = eventInfoDAO.getEventDataByReportLevelId(2l,eventIds,0l,date.getCurrentDateAndTime());
			 if(attendeeInfo != null && attendeeInfo.size() > 0)
			 {
				 for(Object[] params : attendeeInfo)
					{
					 	MahanaduEventVO eventVO = getMatchedVO(resultList,(Long)params[0]);
					 	if(eventVO != null)
					 	{
					 		if(params[1] != null)
					 		eventVO.setInvitees(eventVO.getInvitees() + (Long)params[1]);
					 		if(params[2] != null)
					 		eventVO.setNonInvitees(eventVO.getNonInvitees() + (Long)params[2]);
					 	}
					 	
					}
			 }
			 Long totalVisits= eventAttendeeDAO.getTotlaVisitsCount(parentId,date.getCurrentDateAndTime());
			 if(totalVisits != null && resultList.size() > 0)
				 resultList.get(0).setTotal(totalVisits);
			
		 }
	 }
	 catch(Exception e)
	 {
		 LOG.error("Exception Occured in getSubEventInfo()", e);
		 e.printStackTrace();
	 }
	return resultList;
 }*/
 
 
 public List<MahanaduEventVO> getSubEventCount(Long parentId,List<Long> subEventIds,String startDate,String endDate)
 {
	 List<MahanaduEventVO> resultList = new ArrayList<MahanaduEventVO>();
	 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	 DateUtilService date = new DateUtilService();
	 
	 Date eventStrDate = null;
	 Date eventEndDate = null;
	 try{
		 
		 
		 if(startDate != null && !startDate.isEmpty()){
			 eventStrDate = format.parse(startDate);
		 }
		 
		 if(endDate != null && !endDate.isEmpty()){
			 eventEndDate = format.parse(endDate); 
		 }
		 
		 List<Long> parentEventIds = new ArrayList<Long>();
		 parentEventIds.add(parentId);
		 
		 boolean preEntrySubEventExist = false; 
		 Long preEntrySubEventId = null;
		 
		  List<Object[]> list = eventDAO.getVisibleEventNames(subEventIds);
		  if(list != null && list.size() > 0)
		  {
			  for(Object[] params : list)
				 {
				  MahanaduEventVO vo = new MahanaduEventVO();
					 vo.setId((Long)params[0]);
					 vo.setName(params[1] != null ? params[1].toString() : "");
					 
					 if(vo.getName().trim().equalsIgnoreCase(IConstants.EVENT_PRE_ENTRY)){
						 preEntrySubEventExist = true;
						 preEntrySubEventId = vo.getId();
					 }
				  resultList.add(vo);
				 }
		  }
		  
		  
		  //CHECK EVENT IS MAHANDU EVENT OR NOT.
	     boolean isMahanaduEvent = false;
		 String parentEventName = eventDAO.getEventName(parentId);
		 if( parentEventName != null && parentEventName.trim().length() > 0){
			 if(parentEventName.contains("Mahanadu")){
				 isMahanaduEvent = true;
			 }
		 }
	    if( resultList != null && resultList.size() > 0){
		  resultList.get(0).setMahanaduEvent(isMahanaduEvent);
	    }
		  
		 	/*List<Object[]> attendeeInfo = eventInfoDAO.getEventDataByReportLevelId(2l,subEventIds,0l,eventStrDate,eventEndDate);
			 if(attendeeInfo != null && attendeeInfo.size() > 0)
			 {
				
				 for(Object[] params : attendeeInfo)
					{
					 	MahanaduEventVO eventVO = getMatchedVO(resultList,(Long)params[0]);
					 	if(eventVO != null)
					 	{
					 		
					 		if(params[1] != null)
					 		eventVO.setInvitees(eventVO.getInvitees() + (Long)params[1]);
					 		if(params[2] != null)
					 		eventVO.setNonInvitees(eventVO.getNonInvitees() + (Long)params[2]);
					 	}
					 	
					}
			 }*/
		  
		     List<Object[]> attendeeInfo =eventAttendeeDAO.getStateWiseEventAttendeeCounts(parentId,eventStrDate,eventEndDate,subEventIds);
		     if(attendeeInfo != null && attendeeInfo.size() > 0)
			 {
		    	  for(Object[] params : attendeeInfo)
					{
					 	MahanaduEventVO eventVO = getMatchedVO(resultList,(Long)params[0]);
					 	if(eventVO != null)
					 	{
					 		if(params[1] != null){
					 			
					 			eventVO.setInvitees(eventVO.getInvitees() + (Long)params[1]);
					 			
					 			if( preEntrySubEventId != null && preEntrySubEventId.longValue() == ((Long)params[0]).longValue()){
					 				//valid count
					 				eventVO.setValidCount(eventVO.getInvitees());
					 			}
					 			
					 		}
					 		
					 		/*if(params[2] != null)
					 		eventVO.setNonInvitees(eventVO.getNonInvitees() + (Long)params[2]);*/
					 	}
					 	
					}
			 }
		     
		     if(preEntrySubEventExist){
		    	
		    	 Long invalidCount =  eventAttendeeErrorDAO.getPreEntryInvalidCount(preEntrySubEventId,eventStrDate,eventEndDate);
		    	 if( invalidCount != null && invalidCount.longValue() > 0l){
		    		 
		    		 MahanaduEventVO eventVO = getMatchedVO(resultList,preEntrySubEventId);
		    		 if(eventVO != null){
					 	eventVO.setInvitees(eventVO.getInvitees() + invalidCount);
					 	
					 	//invalid count
					 	eventVO.setInValidCount(invalidCount);
					 }
		    	 }
		    	 
		     }
		     
		     
			 List<Object[]> totalUniqueVisits= eventAttendeeDAO.getTotlaVisitsCount(parentId,eventStrDate,eventEndDate,subEventIds);
			 if(totalUniqueVisits != null && resultList.size() > 0)
			 {
				 Long uniqueMemberInday = 0l;
				 for(Object[] params : totalUniqueVisits)
				 {
					 uniqueMemberInday = uniqueMemberInday + (Long)params[1];
				 }
				 resultList.get(0).setTotal(uniqueMemberInday);
			 }
			
		 try{
			 if(parentId.longValue() == 7l && eventStrDate != null && eventEndDate !=null && eventStrDate.equals(eventEndDate)){
				 Calendar cal = Calendar.getInstance();
				 cal.setTime(eventStrDate);
				 int day = cal.get(Calendar.DAY_OF_MONTH);
				 if(cal.get(Calendar.YEAR) == 2015 && cal.get(Calendar.MONTH) == 4 && (day == 27 || day == 28 || day == 29)){
					 
					 List<MahanaduVisitVO> currentVisitorsCountInfo = mahanaduDashBoardService.getTodayTotalAndCurrentUsersInfo(eventStrDate,eventEndDate);
					 if(currentVisitorsCountInfo != null && currentVisitorsCountInfo.size() > 0){
						 Long currentVisitors = currentVisitorsCountInfo.get(0).getCurrentVisitors();
						 if(currentVisitors != null && currentVisitors.longValue() > 0){
							 MahanaduEventVO eventVO = new MahanaduEventVO();
							 eventVO.setName("Visitors in Campus");
							 eventVO.setInvitees(currentVisitors);
							 resultList.add(eventVO);
						 }
					 }
				 }
			 }
		 }catch(Exception e){
			 LOG.error(e);
		 }
	 }
	 catch(Exception e)
	 {
		 LOG.error("Exception Occured in getSubEventInfo()", e);
		 e.printStackTrace();
	 }
	return resultList;
 }
 
 public String TimeForm(String time)
 {
	   String output = null;
	   try{
		   
		   String input = time;
		
		 //Date/time pattern of input date
	       DateFormat df = new SimpleDateFormat("HH:mm:ss");
	       //Date/time pattern of desired output date
	       DateFormat outputformat = new SimpleDateFormat(" hh:mm:ss aa");
	       Date date = null;
	      
	      
	          //Conversion of input String to date
	    	  date= df.parse(input);
	          //old date format to new date format
	    	  output = outputformat.format(date);
	    	  System.out.println(output);
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	return output;
 }
 
 public MahanaduEventVO getMatchedVO(List<MahanaduEventVO> list,Long id)
	{		
			if(list != null && list.size()>0)
			{
				for (MahanaduEventVO vo : list)
				{
					if(vo.getId().longValue() == id.longValue())
					{
						return vo;
					}
				}
			}

		return null;
	}
 public MahanaduEventVO getMatchedName(List<MahanaduEventVO> list,String name)
	{		
			if(list != null && list.size()>0)
			{
				for (MahanaduEventVO vo : list)
				{
					if(vo.getName().equalsIgnoreCase(name))
					{
						return vo;
					}
				}
			}

		return null;
	}
 public List<MahanaduEventVO> getHourWiseSubEventsCount(Long parentEventId,List<Long> subEventIds,String startDate )
 {
	 List<MahanaduEventVO> resultList = new ArrayList<MahanaduEventVO>();
	 Date eventStrDate = null;
	
	 try{
		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		 if(startDate != null && !startDate.isEmpty())
		 eventStrDate = format.parse(startDate);
		 List<Long> eventIds = new ArrayList<Long>();
		 List<Object[]> hourWiseResult = eventAttendeeDAO.getHourWiseVisitorsCount(parentEventId,eventStrDate,subEventIds);
		 if(hourWiseResult != null && hourWiseResult.size() > 0){
			 for(Object[] obj :hourWiseResult){				
				if(!eventIds.contains((Long)obj[1])){
				 MahanaduEventVO resultVO = new MahanaduEventVO();
				 resultVO.setId((Long)obj[1]);
				 resultVO.setName(obj[3].toString());
				 resultVO.setSubList(setHoursList());
				 resultList.add(resultVO);
				 eventIds.add((Long)obj[1]);
				}
			 }
			 
			 for(Object[] obj :hourWiseResult){
				 MahanaduEventVO vo =  getMatchedVO(resultList,(Long)obj[1]);
				 if(vo != null){
					 
						 MahanaduEventVO vo1 =  getMatchedVO(vo.getSubList(),new Long((Integer) obj[2]));
						 if(vo1 != null){
							 vo1.setCadreCount((Long)obj[0] + vo1.getCadreCount());
						 
					 }					 
				 }
			 }			 
		 }	 
	 }
	 catch(Exception e)
	 {
		 LOG.error("Exception Occured in getHourWiseSubEventsCount()", e);
		 e.printStackTrace();
	 }
	return resultList;
 }
 
  
 public List<MahanaduEventVO> getDayWiseSubEventsCount(Long parentEventId,List<Long> subEventIds,String startDate,String endDate )
 {
	 List<MahanaduEventVO> resultList = new ArrayList<MahanaduEventVO>();
	
	 try{
		 Date eventStrDate = null;
		 Date eventEndDate = null;
		 DateUtilService date = new DateUtilService();
		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		 if(startDate != null && !startDate.isEmpty())
		 eventStrDate = format.parse(startDate);
		 if(endDate != null && !endDate.isEmpty())
		 eventEndDate = format.parse(endDate);
		 List<Long> eventIds = new ArrayList<Long>();
		 List<Object[]> dayWiseResult = eventAttendeeDAO.getDayWiseVisitorsCount(parentEventId,subEventIds,eventStrDate,eventEndDate);
		 List<String> dates = new ArrayList<String>();
		 if(dayWiseResult != null && dayWiseResult.size() > 0){
			 for(Object[] obj :dayWiseResult)
			 {
				 if(!dates.contains(obj[2].toString()))
				 dates.add(obj[2].toString());
			 }
			 for(Object[] obj :dayWiseResult){				
				if(!eventIds.contains((Long)obj[1])){
				 MahanaduEventVO resultVO = new MahanaduEventVO();
				 resultVO.setId((Long)obj[1]);
				 resultVO.setName(obj[3].toString());
				 resultVO.setSubList(setDaysList(dates));
				 resultList.add(resultVO);
				 eventIds.add((Long)obj[1]);
				}
			 }
			 
			 for(Object[] obj :dayWiseResult){
				 MahanaduEventVO vo =  getMatchedVO(resultList,(Long)obj[1]);
				 if(vo != null){
					 
						 MahanaduEventVO vo1 =  getMatchedName(vo.getSubList(),obj[2].toString());
						 if(vo1 != null){
							 vo1.setCadreCount((Long)obj[0] + vo1.getCadreCount());
						 
					 }					 
				 }
			 }			 
		 }	 
	 }
	 catch(Exception e)
	 {
		 LOG.error("Exception Occured in getHourWiseSubEventsCount()", e);
		 e.printStackTrace();
	 }
	return resultList;
 }
 
 public List<MahanaduEventVO> setHoursList(){
	 	 
 	  	 List<MahanaduEventVO> hoursList = new ArrayList<MahanaduEventVO>();
		 for(int i=8;i<=21;i++){
			 	MahanaduEventVO vo = new MahanaduEventVO();
				vo.setId(new Long(i));
				if(i<12){
					if(i==0)
						vo.setName(12 +"AM");
					else
						vo.setName(i +"AM");					
				}else if(i >= 12){
					if(i==12)
						vo.setName(12 +"PM");
					else
						vo.setName(i - 12 +"PM");
				}
				hoursList.add(vo);			
		}
	    return hoursList;
	}
 public List<MahanaduEventVO> setDaysList(List<String> dates){
 	 
	  	 List<MahanaduEventVO> daysList = new ArrayList<MahanaduEventVO>();
	  	 if(dates != null && dates.size() > 0)
	  	 {
	  		for(String date : dates) 
	  		{
	  			MahanaduEventVO vo = new MahanaduEventVO();
	  			vo.setName(date.toString());
	  			daysList.add(vo);
	  		}
	  	 }
	      
    return daysList;
}
 public List<MahanaduEventVO> getEventMembersCount(Long parentEventId,List<Long> subEventIds,String startDate,String endDate)
 {
	 List<MahanaduEventVO> resultList= new ArrayList<MahanaduEventVO>();
	try{
		 Date eventStrDate = null;
		 Date eventEndDate = null;
		 DateUtilService date = new DateUtilService();
		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		 if(startDate != null && !startDate.isEmpty())
		 eventStrDate = format.parse(startDate);
		 if(endDate != null && !endDate.isEmpty())
		 eventEndDate = format.parse(endDate);
		Map<Long,Long> eventCount = new HashMap<Long, Long>();
		List<Object[]> list = eventAttendeeDAO.getEventCountsByParentEventId(parentEventId,subEventIds,eventStrDate,eventEndDate);
		List<Long> eventIds = new ArrayList<Long>();
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				Long count = eventCount.get((Long)params[0]);
				if(count == null)
				eventCount.put((Long)params[0],(Long)params[1]);
				else
				eventCount.put((Long)params[0],(Long)params[1] + count);	
				if(!eventIds.contains((Long)params[0]))
						eventIds.add((Long)params[0]);
				
			}
			for(Long eventId : eventIds)
			{
				MahanaduEventVO returnVo = new MahanaduEventVO();
				List<MahanaduEventVO> uniEventVOList = new ArrayList<MahanaduEventVO>();
				for(Long compareEventId : eventIds)
				{
					if(eventId != compareEventId)
					{
						MahanaduEventVO eventVo = new MahanaduEventVO();
						Long count = eventCount.get(eventId) ;
						List<Object[]> unionCounts = eventAttendeeDAO.getUnionMembersForEvent(eventId,compareEventId,eventStrDate,eventEndDate);
						eventVo.setId(compareEventId);
						eventVo.setName(eventDAO.get(compareEventId).getName());
						if(unionCounts != null)
						for(Object[] params : unionCounts)
						{
							if(params[1] != null)
							eventVo.setTotal(new BigInteger(params[1].toString()).longValue() + eventVo.getTotal());
						}
						uniEventVOList.add(eventVo);
					}
				}
				String mainEventName = eventDAO.get(eventId).getName();
				MahanaduEventVO mainVo = new MahanaduEventVO();
				mainVo.setId(eventId);
				mainVo.setName(mainEventName);
				if(eventCount != null && eventCount.get(eventId) != null)
				mainVo.setTotal(eventCount.get(eventId));
				uniEventVOList.add(mainVo);
				if(uniEventVOList != null && uniEventVOList.size() > 0)
				Collections.sort(uniEventVOList,sortByName);
				returnVo.setId(eventId);
				returnVo.setName(mainEventName);
				returnVo.setSubList(uniEventVOList);
				resultList.add(returnVo);
				if(resultList != null && resultList.size() > 0)
					Collections.sort(resultList,sortByName);
			}
			
		}
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return resultList;
 }
 public static Comparator<MahanaduEventVO> sortByName = new Comparator<MahanaduEventVO>()
			{	  
					  public int compare(MahanaduEventVO arg1,MahanaduEventVO arg2)
						{
						  return arg1.getName().trim().toUpperCase().compareTo(arg2.getName().trim().toUpperCase());
						}
			};	
			
			public List<MahanaduEventVO> getEventsForUser(Long userId)
			{
				DateUtilService date = new DateUtilService();
				List<MahanaduEventVO> returnList = new ArrayList<MahanaduEventVO>();
				try{
					List<Long> parentIds = new ArrayList<Long>();
					List<Object[]> parentEvent = eventDAO.getParentEvents(date.getCurrentDateAndTime());
					if(parentEvent != null && parentEvent.size() > 0)
					{
						
						for(Object[] params : parentEvent)
						{
							MahanaduEventVO eventVo = new MahanaduEventVO();
							eventVo.setId((Long)params[0]);
							eventVo.setName(params[1] != null ? params[1].toString() : "");
							returnList.add(eventVo);
							parentIds.add((Long)params[0]);
						}
						List<Object[]> events = eventDAO.getEventsByUserAndParentIds(date.getCurrentDateAndTime(),parentIds);
						if(events != null && events.size() > 0)
						{
							for(Object[] params : events)
							{
								MahanaduEventVO parentVo = getMatchedVO(returnList,(Long)params[2]);
								if(parentVo != null)
								{
									MahanaduEventVO childEventVo = new MahanaduEventVO();
									childEventVo.setId((Long)params[0]);
									childEventVo.setName(params[1] != null ? params[1].toString() : "");
									if(params[4]!= null)
									childEventVo.setStartTime(TimeForm(params[4].toString()));
									if(params[5]!= null)
									childEventVo.setEndTime(TimeForm(params[5]!= null?params[5].toString():""));
									parentVo.getSubList().add(childEventVo);
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
			
			
	public List<MahanaduEventVO> getSubEvent(Long eventId){
		
	List<MahanaduEventVO> resultList= new ArrayList<MahanaduEventVO>();
	try{
		List<Object[]> list = eventDAO.getSubEventsByParentEvent(eventId);
		if(list != null && list.size() > 0){
			  for(Object[] params : list){
				  MahanaduEventVO vo = new MahanaduEventVO();
				  vo.setId((Long)params[0]);
				  vo.setName(params[1] != null ? params[1].toString() : "");
				  resultList.add(vo);
				 }
		  }
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		return resultList;
		
	}
	
	public List<CadreRegisterInfo> getAuthDetails(Long id,String variable){
		List<CadreRegisterInfo> authDetails = new ArrayList<CadreRegisterInfo>();
		try{
			List<Object[]> usersList = null;
			CadreRegisterInfo info = null;
			if(id.longValue() == 1l){//for userName
				usersList = surveyUserAuthDAO.getAuthDetailsByUserId(variable);
			}else{//for imei
				//0authId,1userName,2name,3mobileNo,4imei
				usersList = surveyUserAuthDAO.getAuthDetailsByImei(variable);
			}
			if(usersList != null && usersList.size() > 0){
				for(Object[] user:usersList){
					info = new CadreRegisterInfo();
					info.setId((Long)user[0]);
					if(user[2] != null){
					   info.setName(user[2].toString());
					}else{
					  info.setName("");
					}
					info.setUname(user[1].toString());
					if(user[3] != null){
					  info.setNumber(user[3].toString());
					}else{
					  info.setNumber("");
					}
					if(user[4] != null){
					  info.setTabNo(user[4].toString());
					}
					authDetails.add(info);
				}
			}
		}catch(Exception e){
			LOG.error("Exception rised in getAuthDetails",e);
		}
		return authDetails;
	}
	
	public String updateTabAllocationDetails(Long authId,String cause,Long userId){
		try{
			surveyUserAuthDAO.updateStatus(authId, cause, userId);
			return "success";
		}catch(Exception e){
			LOG.error("Exception rised in updateTabAllocationDetails",e);
			return "error";
		}
	}
	
	public List<MahanaduEventVO> getMembersDetailsBySubEvent(Long eventId,String startDate,String endDate,Integer startIndex,Integer maxIndex ){
		List<Long> tdpCadreIds = new ArrayList<Long>();
		List<MahanaduEventVO> resultList= new ArrayList<MahanaduEventVO>();		
		Date eventStrDate = null;
	    Date eventEndDate = null;		 
		try{
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			if(startDate != null && !startDate.isEmpty())
				eventStrDate = format.parse(startDate);
			if(endDate != null && !endDate.isEmpty())
				eventEndDate = format.parse(endDate);
		
			List<Object[]> list = eventAttendeeDAO.getMembersDetailsBySubEvent(eventId,eventStrDate,eventEndDate,startIndex,maxIndex);
			if(list != null && list.size() > 0){
				  for(Object[] params : list){
					  MahanaduEventVO vo = new MahanaduEventVO();
					  vo.setId((Long)params[0]);
					  vo.setName(params[1] != null ? params[1].toString() : "");
					  vo.setDesc(params[2] != null ? params[2].toString() : "");
					  vo.setMobileNo(params[3] != null ? params[3].toString() : "");
					  vo.setLocationName(params[4] != null ? params[4].toString() : "");
					  vo.setLocationType("N");  
					  resultList.add(vo);
					  tdpCadreIds.add((Long)params[0]);
					 }
				  if(tdpCadreIds != null && tdpCadreIds.size() > 0)
				  {
					  List parentevent = eventDAO.getParentEventId(eventId);
					  
					  List<Object[]> list1 =eventInviteeDAO.checkInvitees(tdpCadreIds,(Long)parentevent.get(0));
					 
					  if(list1 != null && list1.size() > 0)
					  {
						  for(Object[] params : list1)
						  {
							  MahanaduEventVO tdpCadreVO = getMatchedVO(resultList, (Long)params[0]) ;
							  if(tdpCadreVO != null)
							  {
								  tdpCadreVO.setLocationType("Y");
							  }
							  else
							  {
								  tdpCadreVO.setLocationType("N");  
							  }
						  }
					  }
				  }
			  }
			}catch(Exception e){
				e.printStackTrace();
			}
		return resultList;			
		}
	
	/*public List<MahanaduEventVO> getAttendeeSummaryForEvents(Long eventId,Long stateId,Long reportLevelId,List<Long> subEventIds,String startDate,String endDate){
		List<MahanaduEventVO> finalList = new ArrayList<MahanaduEventVO>();
		LOG.debug("Entered Into getAttendeeSummaryForEvents ");
		Date eventStrDate = null;
	    Date eventEndDate = null;	
	    Date currentDate = null;
	    
		try{
			String locationType = "STATE";
			if(reportLevelId==3l){ locationType = "DISTRICT"; }
			if(reportLevelId==4l){ locationType = "CONSTITUENCY"; }
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			if(startDate != null && !startDate.isEmpty())
				eventStrDate = format.parse(startDate);
			if(endDate != null && !endDate.isEmpty())
				eventEndDate = format.parse(endDate);
			
			
			
			List<Object[]> list = eventAttendeeDAO.getEventAttendeeInfoDynamicIndiDates(locationType, eventStrDate, subEventIds);
			
			
			//DISTRICTS
			if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
				Long stateTypeId = 1l;
				if(stateId==36l){stateTypeId = 2l;}
				List<Object[]> distRes = districtDAO.getDistrictIdAndNameByStateForStateTypeId(1l, stateTypeId);
				if(distRes!=null){
					for(Object[] obj:distRes){
						MahanaduEventVO mv = new MahanaduEventVO();
						mv.setLocationId(Long.valueOf(obj[0].toString()));
						mv.setLocationName(obj[1].toString());
						
						finalList.add(mv);
					}
				}
			}
			
			//CONSTITUENCIES
			if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				Long stteId = 1l;
				if(stateId == 36l){stteId = 0l;}
				List<Object[]> constRes = constituencyDAO.getConstituenciesByElectionTypeAndStateId1(2l, stteId);
				if(constRes!=null){
					for(Object[] obj:constRes){
						MahanaduEventVO mv = new MahanaduEventVO();
						mv.setLocationId(Long.valueOf(obj[0].toString()));
						mv.setLocationName(obj[1].toString());
						
						finalList.add(mv);
					}
				}
			}
			
			setDatesTabsForLocations(finalList, eventStrDate);
			
			
			//INDIVIDUAL DATES RESULT
			if(list!=null){
				for(Object[] obj:list){
					MahanaduEventVO tempVO =  getMatchedLocation(finalList, Long.valueOf(obj[2].toString()));
					if(tempVO!=null){
						if(tempVO.getSubList()!=null && tempVO.getSubList().size()>0){
							MahanaduEventVO temp = getMatchedDate(tempVO.getSubList(),(Date)obj[3]);
							if(temp!=null){
								temp.setTotal(temp.getTotal()+Long.valueOf(obj[1].toString()));
							}
						}
						
					}
				}
			}
			
			StringBuffer sb_start = new StringBuffer();
			StringBuffer sb_middle = new StringBuffer();
			
			List<Object[]> combRslt = null;
			if(finalList!=null){
				MahanaduEventVO ev = finalList.get(0);
				int datesCnt = ev.getDates().size();
				
				List<Date> eventDates =  new ArrayList<Date>();
				if(ev.getDates()!=null && ev.getDates().size()>0){
					for(String edt:ev.getDates()){
						Date evtDate = format.parse(edt);
						eventDates.add(evtDate);
					}
					
				}
				
				
				
				if(datesCnt>1){
					if(datesCnt ==2){
						 sb_start.append(" ,EventAttendee model1 ");
						 sb_middle.append(" and model1.tdpCadre.tdpCadreId =  model.tdpCadre.tdpCadreId " +
						 		" and date(model1.attendedTime)=:date1");
						 
					}
					
					if(datesCnt ==3){
						sb_start.append(" ,EventAttendee model1, EventAttendee model2 ");
						sb_middle.append(" and model1.tdpCadre.tdpCadreId = model.tdpCadre.tdpCadreId " +
								" and model2.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId " +
								" and date(model1.attendedTime)=:date1" +
								" and date(model2.attendedTime) =:date2 ");
					}
					
					combRslt = eventAttendeeDAO.getEventAttendeeInfoDynamic(locationType, eventStrDate, eventDates, sb_start.toString(), sb_middle.toString(), subEventIds);
					
					for(int i=1;i<datesCnt;i++){
						sb_start.append(" ,EventAttendee model"+i+"");
						sb_middle.append(" and model"+i+".tdpCadre.tdpCadreId = model.tdpCadre.tdpCadreId ");
					}
				}
			}
			
			
			
			
		}catch (Exception e) {
			LOG.error(" Exception Raised in getAttendeeSummaryForEvents ",e);
		}
		return finalList;
	}*/
	
	public MahanaduEventVO getMatchedLocation(List<MahanaduEventVO> list, Long id){
		if(list!=null && list.size()>0 && id!=null){
			for(MahanaduEventVO mv:list){
				if(mv.getLocationId().equals(id)){
					return mv;
				}
			}
		}
		return null;
	}
	
	public MahanaduEventVO getMatchedDate(List<MahanaduEventVO> list, Date date){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		if(list!=null && list.size()>0 && date!=null){
			String dt = format.format(date);
			for(MahanaduEventVO mv:list){
				if(mv.getStartTime().equals(dt)){
					return mv;
				}
			}
		}
		return null;
	}
	
	public void setDatesTabsForLocations(List<MahanaduEventVO> finalList,Date eventStrDate){
		if(finalList!=null && finalList.size()>0){
			
			List<MahanaduEventVO> subList = new ArrayList<MahanaduEventVO>();
			
			//GENERATING THE DATES TO DISPLAY
			DateUtilService date = new DateUtilService();
			Date crrntDt = date.getCurrentDateAndTime();
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				
			List<String> dates = new ArrayList<String>();
		    long interval = 24*1000 * 60 * 60; 
		    long endTime =crrntDt.getTime() ;
		    long curTime = eventStrDate.getTime();
		    while (curTime <= endTime) {
		        
		        String ds = format.format(new Date(curTime));
		        dates.add(ds);
		        curTime += interval;
		    }
		    
		    List<String> dateCombinations = new ArrayList<String>();
		    if(dates.size()>0){
		    	
		    	
		    	for(String dt:dates){
		    		MahanaduEventVO mv = new MahanaduEventVO();
		    		mv.setStartTime(dt);
		    		subList.add(mv);
		    	}
		    	
		    	if(dates.size()==2){
		    		MahanaduEventVO mv = new MahanaduEventVO();
		    		mv.setStartTime(dates.get(0)+","+dates.get(1));
		    		subList.add(mv);
		    		dateCombinations.add(dates.get(0)+","+dates.get(1));
		    	}
		    	
		    	if(dates.size()==3){
		    		MahanaduEventVO mv = new MahanaduEventVO();
		    		mv.setStartTime(dates.get(0)+","+dates.get(2));
		    		subList.add(mv);
		    		dateCombinations.add(dates.get(0)+","+dates.get(2));
		    		
		    		MahanaduEventVO mv1 = new MahanaduEventVO();
		    		mv1.setStartTime(dates.get(1)+","+dates.get(2));
		    		subList.add(mv1);
		    		dateCombinations.add(dates.get(1)+","+dates.get(2));
		    		
		    		MahanaduEventVO mv2 = new MahanaduEventVO();
		    		mv2.setStartTime(dates.get(0)+","+dates.get(1)+" "+dates.get(2));
		    		subList.add(mv2);
		    		dateCombinations.add(dates.get(0)+","+dates.get(1)+" "+dates.get(2));
		    		
		    	}
		    }
		    
		    finalList.get(0).setDates(dates);
		    finalList.get(0).setDateCombinations(dateCombinations);
		    finalList.get(0).setSubList(subList);
		    
			for(MahanaduEventVO temp:finalList){
				temp.setSubList(subList);
			}
		}
	}
	public List<MahanaduEventVO> getAttendeeSummaryForEvents(Long eventId,Long stateId,Long reportLevelId,List<Long> subEventIds,String startDate,String endDate){
		List<MahanaduEventVO> resultList = getAttendeeSummaryForReqEvents(eventId,stateId,reportLevelId,subEventIds,startDate,endDate);
		List<MahanaduEventVO> inviteeResultList = getAttendeeSummaryForEventsInvities(eventId,stateId,reportLevelId,subEventIds,startDate,endDate);
			for(MahanaduEventVO invitee:inviteeResultList){
				for(MahanaduEventVO mainResult:resultList){
					if(invitee.getLocationId() != null && mainResult.getLocationId() != null && invitee.getLocationId().longValue() == mainResult.getLocationId().longValue()){
						if(invitee.getTotal() != null)
						mainResult.setNewCount(invitee.getTotal().intValue());
						if(mainResult.getSubList() != null && invitee.getSubList() != null && mainResult.getSubList().size() == invitee.getSubList().size()){
							for(int i=0;i<mainResult.getSubList().size();i++){
								mainResult.getSubList().get(i).setNewCount(invitee.getSubList().get(i).getCount());
							}
						}
					}
			}
		}
		return resultList;
		
	}
	public List<MahanaduEventVO> getAttendeeSummaryForReqEvents(Long eventId,Long stateId,Long reportLevelId,List<Long> subEventIds,String startDate,String endDate){
		List<MahanaduEventVO> finalList = new ArrayList<MahanaduEventVO>();
		LOG.debug("Entered Into getAttendeeSummaryForEvents ");
		Date eventStrDate = null;
	    Date eventEndDate = null;	
	    
		try{
			String locationType = "STATE";
			if(reportLevelId==3l){ locationType = "DISTRICT"; }
			if(reportLevelId==4l){ locationType = "CONSTITUENCY"; }
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			if(startDate != null && !startDate.isEmpty())
				eventStrDate = format.parse(startDate);
			if(endDate != null && !endDate.isEmpty())
				eventEndDate = format.parse(endDate);
			
			
			
			List<Object[]> list = eventAttendeeDAO.getEventAttendeeInfoDynamicIndiDates(locationType, eventStrDate, subEventIds);
			
			List<Object[]> list_temp = eventAttendeeDAO.getEventAttendeeInfoDynamicIndiDates("ALL", eventStrDate, subEventIds);
			
			
			//DISTRICTS
			if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
				Long stateTypeId = 1l;
				if(stateId==36l){stateTypeId = 2l;}
				List<Object[]> distRes = districtDAO.getDistrictIdAndNameByStateForStateTypeId(1l, stateTypeId);
				if(distRes!=null){
					for(Object[] obj:distRes){
						MahanaduEventVO mv = new MahanaduEventVO();
						mv.setLocationId(Long.valueOf(obj[0].toString()));
						mv.setLocationName(obj[1].toString());
						
						finalList.add(mv);
					}
				}
			}
			
			//CONSTITUENCIES
			if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				Long stteId = 1l;
				if(stateId == 36l){stteId = 0l;}
				List<Object[]> constRes = constituencyDAO.getConstituenciesByElectionTypeAndStateId1(2l, stteId);
				if(constRes!=null){
					for(Object[] obj:constRes){
						MahanaduEventVO mv = new MahanaduEventVO();
						mv.setLocationId(Long.valueOf(obj[0].toString()));
						mv.setLocationName(obj[1].toString());
						
						finalList.add(mv);
					}
				}
			}
			
			List<Object[]> Rsltlist = eventAttendeeDAO.getEventAttendeesSummary("ALL", eventStrDate, subEventIds);
			Map<Long,Long> cadreAttendedCntMap = new HashMap<Long, Long>(); //EACH INDIVIDUAL ATTENDED TIMES
			Map<String,List<Long>> datesAttendedCadreMap = new HashMap<String, List<Long>>();
			if(Rsltlist!=null && Rsltlist.size()>0){
				for(Object[] tmp:Rsltlist){
					Long count = cadreAttendedCntMap.get(Long.valueOf(tmp[0].toString()));
					if(count==null){count = 0l;}
					count = count + 1;
					cadreAttendedCntMap.put(Long.valueOf(tmp[0].toString()), count);
					
					String dt = format.format((Date)tmp[2]);
					List<Long> cadreList  = datesAttendedCadreMap.get(dt);
					if(cadreList==null){cadreList = new ArrayList<Long>();}
					cadreList.add(Long.valueOf(tmp[0].toString()));
					datesAttendedCadreMap.put(dt, cadreList); // DATE WISE ATTENDED CADRE TOTAL
				}
			}
			
			//INDIVIDUAL DATES RESULT
			if(list!=null){
				for(Object[] obj:list){
					MahanaduEventVO tempVO =  getMatchedLocation(finalList, Long.valueOf(obj[2].toString()));
					if(tempVO!=null && obj[1]!=null){
						tempVO.setTotal(tempVO.getTotal()+Long.valueOf(obj[1].toString()));
					}
				}
			}
			
			if(list_temp!=null){
				if(finalList!=null && finalList.size()>0){
					List<MahanaduEventVO> mv = finalList.get(0).getDatesList();
					if(mv == null){mv = new ArrayList<MahanaduEventVO>();}
					for(Object[] obj:list_temp){
						MahanaduEventVO temp = new MahanaduEventVO();
						String dt = format.format((Date)obj[2]);
						temp.setStartTime(dt);
						temp.setTotal(Long.valueOf(obj[1].toString()));
						
						List<Long> cadresAttended = datesAttendedCadreMap.get(dt);
						temp.setAttendedCadres(cadresAttended);
						
						mv.add(temp);
					}
					Collections.sort(mv,datesSort);
					
					if(mv!=null){
						for(int i=0;i<mv.size();i++){
							int oneDayCount = 0;
							List<Long> cadres = mv.get(i).getAttendedCadres();
							
							Set<Long> tempList = new HashSet<Long>();
							
							if(i==0){
								if(mv.get(i)!=null && mv.get(i).getAttendedCadres()!=null){
									mv.get(i).setOneDayCount(mv.get(i).getAttendedCadres().size());
									oneDayCount = mv.get(i).getAttendedCadres().size();
								}
							}
							
							for(int j=i;j>0;j--){
								if(mv.get(j-1).getAttendedCadres()!=null){
									tempList.addAll(mv.get(j-1).getAttendedCadres());
								}
							}
							
							if(cadres!=null && cadres.size()>0 && tempList.size()>0){
								for(Long cd:cadres){
									if(!tempList.contains(cd)){
										oneDayCount = oneDayCount + 1;
									}
								}
							}
							
							mv.get(i).setOneDayCount(oneDayCount);
							mv.get(i).setRevisitCount(mv.get(i).getTotal()-Long.valueOf(mv.get(i).getOneDayCount()));
						}
						
					}
				}
			}
			
			
			
			Map<Long,Long> visitTimesMap = new HashMap<Long, Long>(); // 1 - 1454, 2 - 1254 etc
			Map<Long,List<Long>> visitTimesCadre = new HashMap<Long, List<Long>>(); // 1 - [25485,24588], 2 - [---] etc
			for (Entry<Long, Long> entry : cadreAttendedCntMap.entrySet()){
				Long total = visitTimesMap.get(entry.getValue());
				if(total==null){total =0l;}
				total = total + 1;
				visitTimesMap.put(entry.getValue(), total);// 1 - 1454, 2 - 1254 etc
				
				List<Long> cadresLst = visitTimesCadre.get(entry.getValue());
				if(cadresLst==null){cadresLst = new ArrayList<Long>();}
				cadresLst.add(entry.getKey());
				visitTimesCadre.put(entry.getValue(), cadresLst);// 1 - [25485,24588], 2 - [---] etc
			}
			
			
			List<Object[]> locationRslt = eventAttendeeDAO.getEventAttendeesSummary(locationType, eventStrDate, subEventIds);
			Map<Long,List<Long>> locationCadreMap = new HashMap<Long, List<Long>>();
			if(locationRslt!=null && locationRslt.size()>0){
				for(Object[] obj:locationRslt){
					List<Long> cadreIds = locationCadreMap.get(Long.valueOf(obj[3].toString()));
					if(cadreIds == null){cadreIds = new ArrayList<Long>();}
					cadreIds.add(Long.valueOf(obj[0].toString()));
					locationCadreMap.put(Long.valueOf(obj[3].toString()), cadreIds);
				}
			}
			
			Map<Long,Map<Long,Integer>> lctnTimesCountMap = new HashMap<Long, Map<Long,Integer>>();
			if(locationCadreMap!=null){
				for (Entry<Long, List<Long>> entry : locationCadreMap.entrySet()){
					Map<Long,Integer> timesCountMap = new HashMap<Long, Integer>();  // 1 - 25 , 2 - 32
					List<Long> cadreIds = entry.getValue();
					if(cadreIds!=null && cadreIds.size()>0){
						for (Entry<Long, List<Long>> entryInner : visitTimesCadre.entrySet()){
							Set<Long> cdrs = new HashSet<Long>();
							cdrs.addAll(cadreIds);
							List<Long> common = new ArrayList<Long>(cdrs);
							common.retainAll(entryInner.getValue());
							
							timesCountMap.put(entryInner.getKey(), common.size());
						}
					}
					lctnTimesCountMap.put(entry.getKey(), timesCountMap);
				}
			}
			
			
			
			
			if(locationType.equalsIgnoreCase("DISTRICT") || locationType.equalsIgnoreCase("CONSTITUENCY"))
			if(finalList!=null && finalList.size()>0){
				List<MahanaduEventVO> mvLst = finalList.get(0).getHoursList();
				for (Entry<Long, Long> entryInner : visitTimesMap.entrySet()){
					if(mvLst==null){mvLst = new ArrayList<MahanaduEventVO>();}
					MahanaduEventVO obj = new MahanaduEventVO();
					obj.setId(entryInner.getKey());
					obj.setTotal(entryInner.getValue());
					
					mvLst.add(obj);
				}
				Collections.sort(mvLst,idSort);
				
				
				for(MahanaduEventVO temp:finalList){
					Map<Long,Integer> countsMap = lctnTimesCountMap.get(temp.getLocationId());
					List<MahanaduEventVO> mvpList = null;
					if(temp!=null){
						mvpList = temp.getSubList();
					}
					
					if(countsMap!=null){
						for (Entry<Long, Integer> entryInner : countsMap.entrySet()){
							if(mvpList==null){mvpList = new ArrayList<MahanaduEventVO>();}
							MahanaduEventVO obj = new MahanaduEventVO();
							obj.setId(entryInner.getKey());
							obj.setCount(entryInner.getValue());
							
							mvpList.add(obj);
						}
					}
					Collections.sort(mvpList,idSort);
				}
			}
			
			
			
		}catch (Exception e) {
			LOG.error(" Exception Raised in getAttendeeSummaryForEvents ",e);
		}
		return finalList;
	}
	
	//Collections.sort(sampleList,comparedCountSort);
	public  Comparator<MahanaduEventVO> idSort = new Comparator<MahanaduEventVO>(){
		public int compare(MahanaduEventVO o1, MahanaduEventVO o2) {									
			return o1.getId().compareTo(o2.getId());
		}
	};
	
	
	public  Comparator<MahanaduEventVO> datesSort = new Comparator<MahanaduEventVO>(){
		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			public int compare(MahanaduEventVO o1, MahanaduEventVO o2) {
				Date startTime1 = null;
				Date startTime2 = null;
				try{	
					startTime1 = sdf.parse(o1.getStartTime());
					startTime2 = sdf.parse(o2.getStartTime());
				}catch (Exception e) {
					LOG.error("",e);
				}
				return startTime1.compareTo(startTime2);
				
			}
		
	};
	
	public List<MahanaduEventVO> getOtherStateDeligatesCount(Long parentEventId,List<Long> subEventIds,String startDate,String endDate)
	{
		
		
			 List<MahanaduEventVO> resultList= new ArrayList<MahanaduEventVO>();
			try{
				 Date eventStrDate = null;
				 Date eventEndDate = null;
				 DateUtilService date = new DateUtilService();
				 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				 if(startDate != null && !startDate.isEmpty())
				 eventStrDate = format.parse(startDate);
				 if(endDate != null && !endDate.isEmpty())
				 eventEndDate = format.parse(endDate);
				Map<Long,Long> eventCount = new HashMap<Long, Long>();
				List<Object[]> list = eventAttendeeDAO.getOtherStateDeligatesCount(parentEventId,eventStrDate,eventEndDate,subEventIds);
				
				List<Long> eventIds = new ArrayList<Long>();
				if(list != null && list.size() > 0)
				{
					for(Object[] params : list)
					{
						MahanaduEventVO locationVO = getMatchedVO(resultList,(Long)params[2]);
						if(locationVO == null)
						{
							locationVO = new MahanaduEventVO();
							locationVO.setId((Long)params[2]);
							locationVO.setName(params[3] != null ? params[3].toString() : "");
							locationVO.setSubList(setEvents(subEventIds));
							resultList.add(locationVO);
						}
						
						MahanaduEventVO eventVO = getMatchedVO(locationVO.getSubList(),(Long)params[0]);
						if(eventVO != null)
						{
							eventVO.setTotal(eventVO.getTotal() + (Long)params[1]);
						}
					}
					
				}
			}
				catch (Exception e) {
					LOG.error(" Exception Raised in getOtherStateDeligatesCount ",e);
					e.printStackTrace();
				}
			return resultList;
	}
	public List<MahanaduEventVO> setEvents(List<Long> subEventIds)
	{
		List<MahanaduEventVO> resultList = new ArrayList<MahanaduEventVO>();
	 List<Object[]> list = eventDAO.getEventNames(subEventIds);
	  if(list != null && list.size() > 0)
	  {
		  for(Object[] params : list)
			 {
			  MahanaduEventVO vo = new MahanaduEventVO();
				 vo.setId((Long)params[0]);
				 vo.setName(params[1] != null ? params[1].toString() : "");
			     resultList.add(vo);
			 }
	  }
	return resultList;
	}
	
	public List<MahanaduEventVO> getStatewiseCount(Long eventId,String startDate,String endDate)
	{
		Long apCnt =0l;
		Long tsCnt =0l;
		Date eventStrDate = null;
		Date eventEndDate = null;
		 List<MahanaduEventVO>  resultList = new ArrayList<MahanaduEventVO>();
			try{
		 DateUtilService date = new DateUtilService();
		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		 if(startDate != null && !startDate.isEmpty())
		 eventStrDate = format.parse(startDate);
		 if(endDate != null && !endDate.isEmpty())
		 eventEndDate = format.parse(endDate);
		
		List<Object[]> list = eventAttendeeDAO.getStateCount(eventId,eventStrDate,eventEndDate);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				if((Long)params[0] > 10)
				{
					apCnt = apCnt + (Long)params[1];
				}
				else
				{
					tsCnt = tsCnt + (Long)params[1];
				}
			}
			MahanaduEventVO vo = new MahanaduEventVO();
			vo.setId(1l);
			vo.setName("Andhra Pradesh");
			vo.setTotal(apCnt);
			MahanaduEventVO vo1 = new MahanaduEventVO();
			vo1.setId(0l);
			vo1.setName("Telangana");
			vo1.setTotal(tsCnt);
			resultList.add(vo);resultList.add(vo1);
		}
		
		List<Object[]> otherStates =eventAttendeeDAO.getOtherStateCount(eventId,eventStrDate,eventEndDate);
		if(otherStates != null && otherStates.size() > 0)
		{
			for(Object[] params : otherStates)
			{
				MahanaduEventVO locationVO = getMatchedVO(resultList,(Long)params[1]);
				if(locationVO == null)
				{
					locationVO = new MahanaduEventVO();
					locationVO.setId((Long)params[1]);
					locationVO.setName(params[2] != null ? params[2].toString() : "");
					locationVO.setTotal((Long)params[0]);
					resultList.add(locationVO);
				}
				else
				{
					locationVO.setTotal(locationVO.getTotal() + (Long)params[0]);	
				}
				
			}
		}
	  }
	
		catch (Exception e) {
			LOG.error(" Exception Raised in getStatewiseCount ",e);
			e.printStackTrace();
		}
		return resultList;
	}
	public List<MahanaduEventVO> getAttendeeSummaryForEventsInvities(Long eventId,Long stateId,Long reportLevelId,List<Long> subEventIds,String startDate,String endDate){
		List<MahanaduEventVO> finalList = new ArrayList<MahanaduEventVO>();
		LOG.debug("Entered Into getAttendeeSummaryForEvents ");
		Date eventStrDate = null;
	    Date eventEndDate = null;	
	    
		try{
			String locationType = "STATE";
			if(reportLevelId==3l){ locationType = "DISTRICT"; }
			if(reportLevelId==4l){ locationType = "CONSTITUENCY"; }
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			if(startDate != null && !startDate.isEmpty())
				eventStrDate = format.parse(startDate);
			if(endDate != null && !endDate.isEmpty())
				eventEndDate = format.parse(endDate);
			
			
			
			List<Object[]> list = eventAttendeeDAO.getEventAttendeeInfoDynamicIndiDatesForInvities(locationType, eventStrDate, subEventIds);
			
			List<Object[]> list_temp = eventAttendeeDAO.getEventAttendeeInfoDynamicIndiDatesForInvities("ALL", eventStrDate, subEventIds);
			
			
			//DISTRICTS
			if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
				Long stateTypeId = 1l;
				if(stateId==36l){stateTypeId = 2l;}
				List<Object[]> distRes = districtDAO.getDistrictIdAndNameByStateForStateTypeId(1l, stateTypeId);
				if(distRes!=null){
					for(Object[] obj:distRes){
						MahanaduEventVO mv = new MahanaduEventVO();
						mv.setLocationId(Long.valueOf(obj[0].toString()));
						mv.setLocationName(obj[1].toString());
						
						finalList.add(mv);
					}
				}
			}
			
			//CONSTITUENCIES
			if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				Long stteId = 1l;
				if(stateId == 36l){stteId = 0l;}
				List<Object[]> constRes = constituencyDAO.getConstituenciesByElectionTypeAndStateId1(2l, stteId);
				if(constRes!=null){
					for(Object[] obj:constRes){
						MahanaduEventVO mv = new MahanaduEventVO();
						mv.setLocationId(Long.valueOf(obj[0].toString()));
						mv.setLocationName(obj[1].toString());
						
						finalList.add(mv);
					}
				}
			}
			
			List<Object[]> Rsltlist = eventAttendeeDAO.getEventAttendeesSummaryForInvities("ALL", eventStrDate, subEventIds);
			Map<Long,Long> cadreAttendedCntMap = new HashMap<Long, Long>(); //EACH INDIVIDUAL ATTENDED TIMES
			Map<String,List<Long>> datesAttendedCadreMap = new HashMap<String, List<Long>>();
			if(Rsltlist!=null && Rsltlist.size()>0){
				for(Object[] tmp:Rsltlist){
					Long count = cadreAttendedCntMap.get(Long.valueOf(tmp[0].toString()));
					if(count==null){count = 0l;}
					count = count + 1;
					cadreAttendedCntMap.put(Long.valueOf(tmp[0].toString()), count);
					
					String dt = format.format((Date)tmp[2]);
					List<Long> cadreList  = datesAttendedCadreMap.get(dt);
					if(cadreList==null){cadreList = new ArrayList<Long>();}
					cadreList.add(Long.valueOf(tmp[0].toString()));
					datesAttendedCadreMap.put(dt, cadreList); // DATE WISE ATTENDED CADRE TOTAL
				}
			}
			
			//INDIVIDUAL DATES RESULT
			if(list!=null){
				for(Object[] obj:list){
					MahanaduEventVO tempVO =  getMatchedLocation(finalList, Long.valueOf(obj[2].toString()));
					if(tempVO!=null && obj[1]!=null){
						tempVO.setTotal(tempVO.getTotal()+Long.valueOf(obj[1].toString()));
					}
				}
			}
			
			if(list_temp!=null){
				if(finalList!=null && finalList.size()>0){
					List<MahanaduEventVO> mv = finalList.get(0).getDatesList();
					if(mv == null){mv = new ArrayList<MahanaduEventVO>();}
					for(Object[] obj:list_temp){
						MahanaduEventVO temp = new MahanaduEventVO();
						String dt = format.format((Date)obj[2]);
						temp.setStartTime(dt);
						temp.setTotal(Long.valueOf(obj[1].toString()));
						
						List<Long> cadresAttended = datesAttendedCadreMap.get(dt);
						temp.setAttendedCadres(cadresAttended);
						
						mv.add(temp);
					}
					Collections.sort(mv,datesSort);
					
					if(mv!=null){
						for(int i=0;i<mv.size();i++){
							int oneDayCount = 0;
							List<Long> cadres = mv.get(i).getAttendedCadres();
							
							Set<Long> tempList = new HashSet<Long>();
							
							if(i==0){
								if(mv.get(i)!=null && mv.get(i).getAttendedCadres()!=null){
									mv.get(i).setOneDayCount(mv.get(i).getAttendedCadres().size());
									oneDayCount = mv.get(i).getAttendedCadres().size();
								}
							}
							
							for(int j=i;j>0;j--){
								if(mv.get(j-1).getAttendedCadres()!=null){
									tempList.addAll(mv.get(j-1).getAttendedCadres());
								}
							}
							
							if(cadres!=null && cadres.size()>0 && tempList.size()>0){
								for(Long cd:cadres){
									if(!tempList.contains(cd)){
										oneDayCount = oneDayCount + 1;
									}
								}
							}
							
							mv.get(i).setOneDayCount(oneDayCount);
							mv.get(i).setRevisitCount(mv.get(i).getTotal()-Long.valueOf(mv.get(i).getOneDayCount()));
						}
						
					}
				}
			}
			
			
			
			Map<Long,Long> visitTimesMap = new HashMap<Long, Long>(); // 1 - 1454, 2 - 1254 etc
			Map<Long,List<Long>> visitTimesCadre = new HashMap<Long, List<Long>>(); // 1 - [25485,24588], 2 - [---] etc
			for (Entry<Long, Long> entry : cadreAttendedCntMap.entrySet()){
				Long total = visitTimesMap.get(entry.getValue());
				if(total==null){total =0l;}
				total = total + 1;
				visitTimesMap.put(entry.getValue(), total);// 1 - 1454, 2 - 1254 etc
				
				List<Long> cadresLst = visitTimesCadre.get(entry.getValue());
				if(cadresLst==null){cadresLst = new ArrayList<Long>();}
				cadresLst.add(entry.getKey());
				visitTimesCadre.put(entry.getValue(), cadresLst);// 1 - [25485,24588], 2 - [---] etc
			}
			
			
			List<Object[]> locationRslt = eventAttendeeDAO.getEventAttendeesSummaryForInvities(locationType, eventStrDate, subEventIds);
			Map<Long,List<Long>> locationCadreMap = new HashMap<Long, List<Long>>();
			if(locationRslt!=null && locationRslt.size()>0){
				for(Object[] obj:locationRslt){
					List<Long> cadreIds = locationCadreMap.get(Long.valueOf(obj[3].toString()));
					if(cadreIds == null){cadreIds = new ArrayList<Long>();}
					cadreIds.add(Long.valueOf(obj[0].toString()));
					locationCadreMap.put(Long.valueOf(obj[3].toString()), cadreIds);
				}
			}
			
			Map<Long,Map<Long,Integer>> lctnTimesCountMap = new HashMap<Long, Map<Long,Integer>>();
			if(locationCadreMap!=null){
				for (Entry<Long, List<Long>> entry : locationCadreMap.entrySet()){
					Map<Long,Integer> timesCountMap = new HashMap<Long, Integer>();  // 1 - 25 , 2 - 32
					List<Long> cadreIds = entry.getValue();
					if(cadreIds!=null && cadreIds.size()>0){
						for (Entry<Long, List<Long>> entryInner : visitTimesCadre.entrySet()){
							Set<Long> cdrs = new HashSet<Long>();
							cdrs.addAll(cadreIds);
							List<Long> common = new ArrayList<Long>(cdrs);
							common.retainAll(entryInner.getValue());
							
							timesCountMap.put(entryInner.getKey(), common.size());
						}
					}
					lctnTimesCountMap.put(entry.getKey(), timesCountMap);
				}
			}
			
			
			
			
			if(locationType.equalsIgnoreCase("DISTRICT") || locationType.equalsIgnoreCase("CONSTITUENCY"))
			if(finalList!=null && finalList.size()>0){
				List<MahanaduEventVO> mvLst = finalList.get(0).getHoursList();
				for (Entry<Long, Long> entryInner : visitTimesMap.entrySet()){
					if(mvLst==null){mvLst = new ArrayList<MahanaduEventVO>();}
					MahanaduEventVO obj = new MahanaduEventVO();
					obj.setId(entryInner.getKey());
					obj.setTotal(entryInner.getValue());
					
					mvLst.add(obj);
				}
				Collections.sort(mvLst,idSort);
				
				
				for(MahanaduEventVO temp:finalList){
					Map<Long,Integer> countsMap = lctnTimesCountMap.get(temp.getLocationId());
					List<MahanaduEventVO> mvpList = null;
					if(temp!=null){
						mvpList = temp.getSubList();
					}
					
					if(countsMap!=null){
						for (Entry<Long, Integer> entryInner : countsMap.entrySet()){
							if(mvpList==null){mvpList = new ArrayList<MahanaduEventVO>();}
							MahanaduEventVO obj = new MahanaduEventVO();
							obj.setId(entryInner.getKey());
							obj.setCount(entryInner.getValue());
							
							mvpList.add(obj);
						}
					}
					Collections.sort(mvpList,idSort);
				}
			}
			
			
			
		}catch (Exception e) {
			LOG.error(" Exception Raised in getAttendeeSummaryForEvents ",e);
		}
		return finalList;
	}
	
	public PartyMeetingVO getParticipatedCandidateEventDetails(Long tdpCadreId)
	{
		PartyMeetingVO returnVO = new PartyMeetingVO();
		try {
			List<Object[]> invitationList = eventInviteeDAO.getInvitationCountforCandidate(tdpCadreId);
			Long invitationCount = 0L;
			List<Long> invitationEventsList = new ArrayList<Long>(0);
			if(invitationList != null && invitationList.size()>0)
			{
				for (Object[] param : invitationList) {
					Long count = param[2] != null ? Long.valueOf(param[2].toString()):0L;
					invitationCount = invitationCount+count;
					invitationEventsList.add(param[0] != null ? Long.valueOf(param[0].toString()):0L);
				}
			}
			
			List<Object[]> attendedList = eventAttendeeDAO.getAttendedEventsCountforCandidate(tdpCadreId);
			Long attendedCount = 0L;
			boolean isNonInvitee = false;
			if(attendedList != null && attendedList.size()>0)
			{
				
				for (Object[] param : attendedList) {
					Long count = param[2] != null ? Long.valueOf(param[2].toString()):0L;
					attendedCount = attendedCount+count;
					if(!isNonInvitee && invitationEventsList != null && invitationEventsList.size()>0){
						if(invitationEventsList.contains(param[0] != null ? Long.valueOf(param[0].toString()):0L))
						{
							invitationEventsList.remove(param[0] != null ? Long.valueOf(param[0].toString()):0L);
						}
						else{
							isNonInvitee = true;
							invitationEventsList.add(param[0] != null ? Long.valueOf(param[0].toString()):0L);
						}
					}
					else{
						isNonInvitee = true;
						invitationEventsList.add(param[0] != null ? Long.valueOf(param[0].toString()):0L);
					}
				}
				
			}
			Long absentCount = 0L;
			if(!isNonInvitee)
			{
				absentCount = Long.valueOf(String.valueOf(invitationEventsList.size()));
				
				if(absentCount > 0L)
				{
					List<Object[]> eventNames = eventDAO.getEventNames(invitationEventsList);
					if(eventNames != null && eventNames.size()>0)
					{
						List<PartyMeetingVO> partyMeetingList = new ArrayList<PartyMeetingVO>(0);
						for (Object[] param : eventNames) {
							PartyMeetingVO vo = new PartyMeetingVO();
							vo.setId(param[0] != null ? Long.valueOf(param[0].toString()):0L);
							vo.setName(param[1] != null ? param[1].toString():"");
							partyMeetingList.add(vo);
						}
						returnVO.setPartyMeetingVOList(partyMeetingList);
					}
				}
			}
			
			
			returnVO.setAbsentCount(absentCount);
			returnVO.setInvitedCount(invitationCount);
			returnVO.setAttendedCount(attendedCount);
			
		} catch (Exception e) {
			LOG.error(" Exception Raised in getAttendeeSummaryForEvents ",e);
		}
		return returnVO;
	}
	
	public List<MahanaduEventVO> getAttendeeSummaryForEventsList(Long eventId,Long stateId,Long reportLevelId,List<Long> subEventIds){		
		List<MahanaduEventVO> returnList = new ArrayList<MahanaduEventVO>();		
		try{
			
			SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");			
			Date fromDate = null;
			Date toDate	  = null;
			
			String startDate=null;
			String endDate = null;
			
			Object[] datesList =  eventDAO.getEventDates(eventId);
			
			if(datesList !=null && datesList.length>0){
				fromDate = datesList[0] !=null ? (Date)datesList[0]:null;
				toDate	 = datesList[1] !=null ? (Date)datesList[1]:null;
				
				startDate = form.format(fromDate);
				endDate =  form.format(toDate);			
			}
			returnList = getAttendeeSummaryForEvents(eventId,stateId,reportLevelId,subEventIds,startDate,endDate);
			
		}catch (Exception e) {
			LOG.error(" Exception Raised in getAttendeeSummaryForEventsList ",e);
		}
		
		return returnList; 
		
	}
	
	public List<IdNameVO> getSubEventsOfEvent(Long eventId){
		List<IdNameVO> idNameList = new ArrayList<IdNameVO>();
		try{
			
			Long mainEntryId = 0l; 
			List<Object[]> list = eventDAO.getSubEventsByParentEvent(eventId);
			if(list !=null && list.size()>0){
				setToIdNameVoList(list,idNameList);
			}
			
		}catch (Exception e) {
			LOG.error(" Exception Raised in getAttendeeSummaryForEventsList ",e);
		}
		return idNameList;
		
	}
	
	public void setToIdNameVoList(List<Object[]> list,List<IdNameVO> idNameList){
		try{			
			if(list !=null && list.size()>0){				
				for (Object[] idNameVO : list) {					
					IdNameVO VO = new IdNameVO();
					VO.setId(idNameVO[0] !=null ? (Long)idNameVO[0]:0l);
					VO.setName(idNameVO[1] !=null ? idNameVO[1].toString():"");	
					idNameList.add(VO);
				}				
			}			
		}catch (Exception e) {
			LOG.error(" Exception Raised in setToIdNameVoList ",e);
		}		
	}
	
}




