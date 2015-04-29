package com.itgrids.partyanalyst.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hsqldb.lib.HashSet;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.itgrids.partyanalyst.dao.IEventInfoDAO;
import com.itgrids.partyanalyst.dao.IEventInviteeDAO;
import com.itgrids.partyanalyst.dao.IEventUserDAO;
import com.itgrids.partyanalyst.dao.IGovtDesignationDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPartyDesignationDAO;
import com.itgrids.partyanalyst.dao.ISocialCategoryDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreInfoDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.hibernate.TdpCadreInfoDAO;
import com.itgrids.partyanalyst.dto.CadreAddressVO;
import com.itgrids.partyanalyst.dto.CadreVo;
import com.itgrids.partyanalyst.dto.EventActionPlanVO;
import com.itgrids.partyanalyst.dto.MahanaduEventVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserEventDetailsVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreGovtDesignation;
import com.itgrids.partyanalyst.model.CadrePartyDesignation;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.EventInfo;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IMahaNaduService;
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
		statewise= eventAttendeeDAO.getStateWiseEventAttendeeInfo("invitee",date.getCurrentDateAndTime(),1l);
		statewise1= eventAttendeeDAO.getStateWiseEventAttendeeInfo("",date.getCurrentDateAndTime(),1l);
		setInviteeInfoForState(statewise,2l,"invitee",1l);
		setInviteeInfoForState(statewise1,2l,"",1l);
		statewise= eventAttendeeDAO.getStateWiseEventAttendeeInfo("invitee",date.getCurrentDateAndTime(),36l);
		statewise1= eventAttendeeDAO.getStateWiseEventAttendeeInfo("",date.getCurrentDateAndTime(),36l);
		setInviteeInfoForState(statewise,2l,"invitee",36l);
		setInviteeInfoForState(statewise1,2l,"",36l);
		    if(events != null && events.size() > 0)
			 eventInfoDAO.deleteEventInfo(3l,events) ;
			 voterDAO.flushAndclearSession();
			
		 
		// list = eventInviteeDAO.getEventInviteesCountByLocationType(IConstants.DISTRICT,date.getCurrentDateAndTime());
		 list1= eventAttendeeDAO.getEventAttendeeInfo(IConstants.DISTRICT,"invitee",date.getCurrentDateAndTime());
		 list2= eventAttendeeDAO.getEventAttendeeInfo(IConstants.DISTRICT,"",date.getCurrentDateAndTime());
		// setDataTotalInviteeEventInfo(list,3l);
	      setInviteeInfo(list1,3l,"invitee");
		  setInviteeInfo(list2,3l,"");
		 events = eventInfoDAO.getEventIds(4l,date.getCurrentDateAndTime());
		 if(events != null && events.size() > 0)
		 eventInfoDAO.deleteEventInfo(4l,events) ;
		 voterDAO.flushAndclearSession();
		
		// list = eventInviteeDAO.getEventInviteesCountByLocationType(IConstants.CONSTITUENCY,date.getCurrentDateAndTime());
		 list1= eventAttendeeDAO.getEventAttendeeInfo(IConstants.CONSTITUENCY,"invitee",date.getCurrentDateAndTime());
		 list2= eventAttendeeDAO.getEventAttendeeInfo(IConstants.CONSTITUENCY,"",date.getCurrentDateAndTime());
		// setDataTotalInviteeEventInfo(list,4l);
		  setInviteeInfo(list1,4l,"invitee");
		  setInviteeInfo(list2,4l,"");
		 
	 }
	 catch(Exception e)
	 {
		 Log.error("Exception rised in insertDataintoEventInfo() while closing write operation",e); 
		 e.printStackTrace();
	 }
	return result;
 }
 
 public void setDataTotalInviteeEventInfo(final List<Object[]> list,final Long reportLevelId)
 {
	 
	 ResultStatus rs = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				ResultStatus rs = new ResultStatus();
	 try{
			
		 if(list != null && list.size() > 0)
		 {
			 for(Object[] params : list)
			 {
				 EventInfo eventInfo = new EventInfo();
				 eventInfo.setEventId((Long)params[0]);
				 eventInfo.setTotalInvitees((Long)params[1]);
				 eventInfo.setReportLevelId(reportLevelId);
				 eventInfo.setLocationValue((Long)params[2]);
				 eventInfoDAO.save(eventInfo);
				
			 }
			 
			 voterDAO.flushAndclearSession();
		 }
		 
		 
	 }
	 catch(Exception e)
	 {
		 Log.error("Exception rised in setDataToEventInfo() while closing write operation",e); 
		 e.printStackTrace();
	 }
		return rs;
			} });
		
	
 }
 
 public void setInviteeInfo(List<Object[]> list,Long reportLevelId,String type)
 {
	 List<Long> locationValues = new ArrayList<Long>();
	 try{
		
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
				 else
				 eventInfo.setNoninvitees((Long)params[1]);
				 eventInfoDAO.save(eventInfo);
				 }
				 else
				 {
					 eventInfo = eventInfoDAO.get(eventActionPlanVO.getId()); 
					 if(type.equalsIgnoreCase("invitee"))
						 eventInfo.setInvitees((Long)params[1]);
					 else
						 eventInfo.setNoninvitees((Long)params[1]);
						 eventInfoDAO.save(eventInfo);
				 }
				 if(!locationValues.contains((Long)params[2]))
					 locationValues.add((Long)params[2]); 
				
			 }
			 voterDAO.flushAndclearSession();
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
				 else
				 eventInfo.setNoninvitees((Long)params[1]);
				 eventInfoDAO.save(eventInfo);
				 }
				 else
				 {
					 eventInfo = eventInfoDAO.get(eventActionPlanVO.getId()); 
					 if(type.equalsIgnoreCase("invitee"))
						 eventInfo.setInvitees((Long)params[1]);
					 else
						 eventInfo.setNoninvitees((Long)params[1]);
						 eventInfoDAO.save(eventInfo);
				 }
				 
			 }
			 voterDAO.flushAndclearSession();
				
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
 
 public List<MahanaduEventVO> getEventInfoByReportType(Long eventId,Long stateId,Long reportLevelId)
 {
	 List<MahanaduEventVO>  resultList = new ArrayList<MahanaduEventVO>();
	 try{
		 String locationType = "";
		 if(reportLevelId == 2l)
			 locationType = "State";
		 else if(reportLevelId == 3l)
			 locationType = "District";
		 else if(reportLevelId == 4l)
			 locationType = "Constituency";
		 DateUtilService date = new DateUtilService();
		  List<Object[]> list = eventInfoDAO.getEventDataByReportLevelId(reportLevelId,eventId,stateId,date.getCurrentDateAndTime());
		  Set<Long> reportLevelValues = new java.util.HashSet<Long>();
		  if(list != null && list.size() > 0)
		  {
			  for(Object[] params : list)
			  {
				  MahanaduEventVO eventVo = new MahanaduEventVO();
				  eventVo.setId((Long)params[1]);
				  if(reportLevelId != 2l){
					  eventVo.setName(getLocationName(reportLevelId,(Long)params[1]));
				  }
				 
				  eventVo.setInvitees(params[3] != null ? (Long)params[3] : 0l);
				  eventVo.setNonInvitees(params[4] != null ? (Long)params[4] : 0l);			 
				  eventVo.setAttendees(eventVo.getInvitees() + eventVo.getNonInvitees());				
				  resultList.add(eventVo);
				  reportLevelValues.add((Long)params[1]);
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
			
			 List<Object[]>  inviteesCnt = eventInviteeDAO.getEventInviteesCountByLocationTypeAndEvent(locationType,date.getCurrentDateAndTime(),eventId);
			 if(inviteesCnt != null && inviteesCnt.size() > 0){
				 for(Object[] obj :inviteesCnt){
					 MahanaduEventVO vo = getMatchedVO(resultList, (Long)obj[1]);
					 if(vo != null){
						 vo.setTotal((Long)obj[0]);
					 }
				 }				 
			 }
		  }else{
			  Long totalCnt = eventInviteeDAO.getEventInviteesCountByState(stateId,date.getCurrentDateAndTime(),eventId);				
						 MahanaduEventVO vo = getMatchedVO(resultList,stateId);
						 if(vo != null){
							 vo.setTotal(totalCnt);
						 }					 
		  }
			  
			  
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
 
 public List<MahanaduEventVO> getSubEventInfo(Long parentId,Long userId)
 {
	 List<MahanaduEventVO> resultList = new ArrayList<MahanaduEventVO>();
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
}
