package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.apache.commons.lang.StringUtils;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICadreGovtDesignationDAO;
import com.itgrids.partyanalyst.dao.ICadrePartyDesignationDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.ISocialCategoryDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.CadreVo;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreGovtDesignation;
import com.itgrids.partyanalyst.model.CadreLanguageEfficiency;
import com.itgrids.partyanalyst.model.CadreParticipatedTrainingCamps;
import com.itgrids.partyanalyst.model.CadrePartyDesignation;
import com.itgrids.partyanalyst.model.CadreSkills;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.Township;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IMahaNaduService;
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
	private IBoothDAO boothDAO;
	
	
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

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}
	
	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
 
 public List<SelectOptionVO> getBoothsInAConstituency(Long constituencyId,Long publicationID,Long tehsilId,Long localElecBodyId){
	 List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
	 try{
		 List<Object[]> boothsList = boothDAO.getBoothOfAConstituencyByPublication(constituencyId, publicationID,tehsilId,localElecBodyId);
		 for(Object[] boothInfo:boothsList){
			 SelectOptionVO vo = new SelectOptionVO();
			 vo.setId((Long)boothInfo[0]);
			 vo.setName(boothInfo[1].toString());
		 }
	 }catch(Exception e){
		 LOG.error("Exception rised in getBoothsInAConstituency ",e);
	 }
	 return returnList;
 }
 
 public CadreVo searchCadreDetails(Long userId,Long constiId, String searchBy,String searchType,String sort,String sortBy,int startIndex,int maxResult)
 { 
	 CadreVo returnVo = new CadreVo();
	 List<CadreVo> returnList = new ArrayList<CadreVo>();
	 try {
		 String queryStr = null;
		 if(searchType.equalsIgnoreCase("all")){
			 queryStr = " model.firstName like '%"+searchBy+"%' and model.lastName like '%"+searchBy+"%'  and  model.mobile like '"+searchBy+"' ";
		 }
		 else if(searchType.equalsIgnoreCase("firstTwo")){
			 queryStr = " model.firstName like '%"+searchBy+"%' and model.lastName like '%"+searchBy+"%' ";
		 }
		 else if(searchType.equalsIgnoreCase("secondTwo")){
			 queryStr = " model.lastName like '%"+searchBy+"%'  and  model.mobile like '"+searchBy+"' ";
		 }
		 else if(searchType.equalsIgnoreCase("firstLast")){
			 queryStr = " model.firstName like '%"+searchBy+"%'  and  model.mobile like '"+searchBy+"' ";
		 }
		 else if(searchType.equalsIgnoreCase("firstone")){
			 queryStr = " model.firstName like '%"+searchBy+"%' ";
		 }
		 else if(searchType.equalsIgnoreCase("secondone")){
			 queryStr = " model.lastName like '%"+searchBy+"%' ";
		 }
		 else if(searchType.equalsIgnoreCase("thirdone")){
			 queryStr = " model.mobile like '"+searchBy+"' ";
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
	 return returnList;
 }
 
 public List<SelectOptionVO> getPartyDesignations(){
	 List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
	 return returnList;
 }
 
 public List<SelectOptionVO> getgovernmentDesignations(){
	 List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
	 return returnList;
 }


 public ResultStatus saveCadreInfoForMahaNadu(CadreVo CadreVoToSave)
	{
		
		ResultStatus rs = new ResultStatus();
		try{
		Cadre cadreObj = saveCadreDetails(CadreVoToSave);
		if (cadreObj != null)
		{
			LOG.debug("inside cadre obj block");
			
			if(CadreVoToSave.getPartyDesignationList() != null && CadreVoToSave.getPartyDesignationList().size() > 0)
			{
				for(Long partyDesgId : CadreVoToSave.getPartyDesignationList())
				{
				CadrePartyDesignation cadrePartyDesignation = new CadrePartyDesignation();
				cadrePartyDesignation.setCadre(cadreObj);
				cadrePartyDesignation.setCadrePartyDesignationId(partyDesgId);
				cadrePartyDesignationDAO.save(cadrePartyDesignation);
				}
			}
			
			if(CadreVoToSave.getGovtDesignationList() != null && CadreVoToSave.getGovtDesignationList().size() > 0)
			{
				for(Long govtDesgId : CadreVoToSave.getGovtDesignationList())
				{
					CadreGovtDesignation cadreGovtDesignation = new CadreGovtDesignation();
					cadreGovtDesignation.setCadre(cadreObj);
					cadreGovtDesignation.setCadreGovtDesignationId(govtDesgId);
					cadreGovtDesignationDAO.save(cadreGovtDesignation);
				}
			}
			
			
			
		}
		rs.setResultCode(ResultCodeMapper.SUCCESS);
	}catch(Exception e){
		LOG.debug(e);
		rs.setExceptionEncountered(e);
		rs.setExceptionClass(e.getClass().toString());
		rs.setExceptionMsg(getExceptionMessage(e.getClass().toString()));
		rs.setResultCode(ResultCodeMapper.FAILURE);
		rs.setResultPartial(true);
		
	}
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
	public Cadre saveCadreDetails (final CadreVo cadreInfo) 
	{
		Cadre cadreObj = (Cadre) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {

			UserAddress currentAddress = new UserAddress();
			
			Cadre cadre = null;
			if(cadreInfo.getCadreId() != null && cadreInfo.getCadreId() > 0)
				cadre = cadreDAO.get(cadreInfo.getCadreId());
			else
				cadre = new Cadre();
			try {
				
				
				cadre.setFirstName(cadreInfo.getFirstName());
				cadre.setLastName(cadreInfo.getLastName());
				
				cadre.setGender(cadreInfo.getGender());
				if(cadreInfo.getAge() != null && (!StringUtils.isBlank(cadreInfo.getAge().toString())))
				cadre.setAge(cadreInfo.getAge());
				if(cadreInfo.getFatherName() != null && (!StringUtils.isBlank(cadreInfo.getFatherName())))
				cadre.setFatherOrSpouseName(cadreInfo.getFatherName());
				cadre.setBloodGroupId(cadreInfo.getBloodGroupId() != 0 ? cadreInfo.getBloodGroupId()  : null);
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
					
				if(cadreInfo.getProfessionId() != null && cadreInfo.getProfessionId() > 0)
				cadre.setOccupation(occupationDAO.get(cadreInfo.getProfessionId()));
				if(cadreInfo.getCasteCategory() != null && cadreInfo.getCasteCategory() > 0)
				cadre.setCasteCategory(socialCategoryDAO.get(cadreInfo.getCasteCategory()));
				Double annunaIncome = 0d;
				if (cadreInfo.getAnnualIncome() != null && (!StringUtils.isBlank(cadreInfo.getAnnualIncome())))
					annunaIncome = new Double(cadreInfo.getAnnualIncome());

				cadre.setAnnualIncome(annunaIncome);
				Double sourceIncome = 0d;
				if (cadreInfo.getSourceIncome() != null && (!StringUtils.isBlank(cadreInfo.getSourceIncome())))
					sourceIncome = new Double(cadreInfo.getSourceIncome());
				cadre.setSourceIncome(sourceIncome);
				
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
				
				if(cadreInfo.getDistrictId() != null)
					 currentAddress.setDistrict(districtDAO.get(new Long(cadreInfo.getDistrictId())));
				if(cadreInfo.getConstituencyId() != null)
				currentAddress.setConstituency(constituencyDAO.get(cadreInfo.getConstituencyId()));
				if(cadreInfo.getBoothNo() != null && !cadreInfo.getBoothNo().equals("0"))
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
				cadreDAO.save(cadre);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				return cadre;
			} });
		return cadreObj;
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
	currentAddress = cadre.getCurrentAddress();
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
	
	Long  edu= 0L;
	cadreInfo.setEducation(edu = cadre.getEducation() != null ? cadre.getEducation().getEduQualificationId() : null);
	String eduStr = "";
	cadreInfo.setEducationStr(eduStr = cadre.getEducation() != null ? cadre.getEducation().getQualification() : "");
	Long professn = 0L;
	cadreInfo.setProfessionId(professn = cadre.getOccupation() != null ? cadre.getOccupation().getOccupationId() : null);
	String profsnStr = "";
	cadreInfo.setProfessionStr(profsnStr = cadre.getOccupation() != null ? cadre.getOccupation().getOccupation() : "");
	
	if(cadre.getAnnualIncome() != null)
	cadreInfo.setAnnualIncome(new Long(cadre.getAnnualIncome().longValue()).toString());
	if(cadre.getSourceIncome() != null)
		cadreInfo.setSourceIncome(new Long(cadre.getSourceIncome().longValue()).toString());
		
	if(cadre.getCasteCategory() != null){
		cadreInfo.setCasteCategory(cadre.getCasteCategory().getSocialCategoryId());
		cadreInfo.setCasteCategoryName(cadre.getCasteCategory().getCategory());
	}
	
	cadreInfo.setMemberType(cadre.getMemberType());
	cadreInfo.setActiveDateField(cadre.getActiveDateField() != null ? cadre.getActiveDateField().toString() : "");
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
		for(Object[] params1 : partyDesignations)
		{
			if(!govtDesignationList.contains((Long)params1[0]))
			govtDesignationList.add((Long)params1[0]);
		}
	}
	cadreInfo.setPartyDesignationList(partyDesignationList);
	cadreInfo.setGovtDesignationList(govtDesignationList);
	return cadreInfo;
}
}
