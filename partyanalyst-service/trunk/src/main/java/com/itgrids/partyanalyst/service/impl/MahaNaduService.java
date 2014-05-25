package com.itgrids.partyanalyst.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICadreGovtDesignationDAO;
import com.itgrids.partyanalyst.dao.ICadrePartyDesignationDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IGovtDesignationDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPartyDesignationDAO;
import com.itgrids.partyanalyst.dao.ISocialCategoryDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dto.CadreVo;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreGovtDesignation;
import com.itgrids.partyanalyst.model.CadrePartyDesignation;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
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
	private ICasteStateDAO casteStateDAO;
	private IPartyDesignationDAO partyDesignationDAO;
	private IGovtDesignationDAO govtDesignationDAO;
	private CadreManagementService cadreManagementService;
	private IUserDAO userDAO;
	
	
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
				 queryStr = " model.firstName like '%"+searchBy[0].toString()+"%' and model.lastName like '%"+searchBy[1].toString()+"%'  and  model.mobile like '"+searchBy[2].toString()+"' ";
			 }
			 else if(searchType.equalsIgnoreCase("firstTwo")){
				 queryStr = " model.firstName like '%"+searchBy[0].toString()+"%' and model.lastName like '%"+searchBy[1].toString()+"%' ";
			 }
			 else if(searchType.equalsIgnoreCase("secondTwo")){
				 queryStr = " model.lastName like '%"+searchBy[0].toString()+"%'  and  model.mobile like '"+searchBy[1].toString()+"' ";
			 }
			 else if(searchType.equalsIgnoreCase("firstLast")){
				 queryStr = " model.firstName like '%"+searchBy[0].toString()+"%'  and  model.mobile like '"+searchBy[1].toString()+"' ";
			 }
			 else if(searchType.equalsIgnoreCase("firstone")){
				 queryStr = " model.firstName like '%"+searchBy[0].toString()+"%' ";
			 }
			 else if(searchType.equalsIgnoreCase("secondone")){
				 queryStr = " model.lastName like '%"+searchBy[0].toString()+"%' ";
			 }
			 else if(searchType.equalsIgnoreCase("thirdone")){
				 queryStr = " model.mobile like '"+searchBy[0].toString()+"' ";
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
				cadre.setUser(userDAO.get(cadreInfo.getCadreId()));
			}
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
				cadre.setCasteState(casteStateDAO.get(cadreInfo.getCasteCategory()));
				Double annunaIncome = null;
				if (cadreInfo.getAnnualIncome() != null && (!StringUtils.isBlank(cadreInfo.getAnnualIncome())))
					annunaIncome = new Double(cadreInfo.getAnnualIncome().trim());

				cadre.setAnnualIncome(annunaIncome);
				Double sourceIncome = null;
				if (cadreInfo.getSourceIncome() != null && (!StringUtils.isBlank(cadreInfo.getSourceIncome())))
					
				cadre.setIncomeSource(sourceIncome);
				
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
		int month = c.get(Calendar.MONTH)+1;
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
