package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
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
import com.itgrids.partyanalyst.dto.CadreVo;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreGovtDesignation;
import com.itgrids.partyanalyst.model.CadrePartyDesignation;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IMahaNaduService;
import com.itgrids.partyanalyst.utils.IConstants;

public class MahaNaduService implements IMahaNaduService{
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
 private static final Logger LOG = Logger.getLogger(MahaNaduService.class);
public IBoothDAO getBoothDAO() {
	return boothDAO;
}

public void setBoothDAO(IBoothDAO boothDAO) {
	this.boothDAO = boothDAO;
}
 
 public List<SelectOptionVO> getBoothsInAConstituency(Long constituencyId,Long publicationID){
	 List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
	 try{
		 List<Object[]> boothsList = boothDAO.getBoothOfAConstituencyByPublication(constituencyId, publicationID);
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
			
			Cadre cadre = new Cadre();
			try {
				
				
				cadre.setFirstName(cadreInfo.getFirstName());
				cadre.setLastName(cadreInfo.getLastName());
				cadre.setGender(cadreInfo.getGender());
				cadre.setAge(cadreInfo.getAge());
				cadre.setFatherOrSpouseName(cadreInfo.getFatherName());
				cadre.setBloodGroupId(cadreInfo.getBloodGroupId() != 0 ? cadreInfo.getBloodGroupId()  : null);
				cadre.setNoOfFamilyMembers(cadreInfo.getNoOfFamilyMembers());
				cadre.setNoOfVoters(cadreInfo.getNoOfVoters());
				cadre.setMobile(cadreInfo.getMobileNo());
				cadre.setTelephone(cadreInfo.getLandNo());
				cadre.setEmail(cadreInfo.getEmailId());
				cadre.setIsMahanadu("Y");
				if (!cadreInfo.getEducationId().equals(new Long(0)))
					cadre.setEducation(educationalQualificationsDAO.get(cadreInfo.getEducationId()));
					
				if(cadreInfo.getProfessionId() != null)
				cadre.setOccupation(occupationDAO.get(cadreInfo.getProfessionId()));
				if(cadreInfo.getCasteCategory() != null)
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
				if (cadreInfo.getActiveDateField() != null) {
					cadre.setActiveDateField(format.parse(cadreInfo.getActiveDateField()));
				} 
				cadre.setMemberType(cadreInfo.getMemberType());
				
				// Current Address
				
				currentAddress.setHouseNo(cadreInfo.getHno());
				currentAddress.setStreet(cadreInfo.getStreet());
				currentAddress.setPinCode(cadreInfo.getPinCode());
				
				if(cadreInfo.getDistrictId() != null)
					 currentAddress.setDistrict(districtDAO.get(new Long(cadreInfo.getDistrictId())));
				if(cadreInfo.getConstituencyId() != null)
				currentAddress.setConstituency(constituencyDAO.get(cadreInfo.getConstituencyId()));
				if(cadreInfo.getMandalId()!= null){
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
					
				}
				
				if(cadreInfo.getBoothNo() != null && !cadreInfo.getBoothNo().equals("0"))
				{
					currentAddress.setBooth(boothDAO.get(cadreInfo.getBoothNo()));
				}
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
}
