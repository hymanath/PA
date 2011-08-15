/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 24,2010
 */
package com.itgrids.partyanalyst.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICadreFamilyMemberInfoDAO;
import com.itgrids.partyanalyst.dao.ICadreLevelDAO;
import com.itgrids.partyanalyst.dao.ICadreRoleDAO;
import com.itgrids.partyanalyst.dao.ICadreRoleRelationDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.ISocialCategoryDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserRelationDAO;
import com.itgrids.partyanalyst.dao.IWardDAO;
import com.itgrids.partyanalyst.dto.GenericUploadDataVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreFamilyMemberInfo;
import com.itgrids.partyanalyst.model.CadreLevel;
import com.itgrids.partyanalyst.model.CadreRole;
import com.itgrids.partyanalyst.model.CadreRoleRelation;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Country;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.EducationalQualifications;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Occupation;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.SocialCategory;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.Township;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.model.UserRelation;
import com.itgrids.partyanalyst.service.IGenericUploadDataService;
import com.itgrids.partyanalyst.utils.IConstants;

/**
 * @author Sai Krishna
 *
 */
public class GenericUploadDataService implements IGenericUploadDataService {

	
	private IWardDAO wardDAO;
	private IBoothDAO boothDAO;
	private IStateDAO stateDAO;
	private IHamletDAO hamletDAO;
	private ITehsilDAO tehsilDAO;
	private ICountryDAO countryDAO;
	private IDistrictDAO districtDAO;
	private ITownshipDAO townshipDAO;
	private IConstituencyDAO constituencyDAO;
	private ILocalElectionBodyDAO localBodyDAO;

	private TransactionTemplate transactionTemplate;
	
	private ICadreDAO cadreDAO;
	private ICadreRoleDAO cadreRoleDAO;
	private ICadreLevelDAO cadreLevelDAO;
	private IOccupationDAO occupationDAO;
	private IUserAddressDAO userAddressDAO;
	private IRegistrationDAO registrationDAO;
	private IUserRelationDAO userRelationDAO;
	private ISocialCategoryDAO socialCategoryDAO;
	private ICadreRoleRelationDAO cadreRoleRelationDAO;
	private ICadreFamilyMemberInfoDAO cadreFamilyMemberInfoDAO;
	private IEducationalQualificationsDAO educationalQualificationsDAO;
		
	
	private static Logger log = Logger.getLogger(GenericUploadDataService.class);
	
	
	/**
	 * @return the wardDAO
	 */
	public IWardDAO getWardDAO() {
		return wardDAO;
	}


	/**
	 * @param wardDAO the wardDAO to set
	 */
	public void setWardDAO(IWardDAO wardDAO) {
		this.wardDAO = wardDAO;
	}


	/**
	 * @return the boothDAO
	 */
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}


	/**
	 * @param boothDAO the boothDAO to set
	 */
	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}


	/**
	 * @return the stateDAO
	 */
	public IStateDAO getStateDAO() {
		return stateDAO;
	}


	/**
	 * @param stateDAO the stateDAO to set
	 */
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}


	/**
	 * @return the hamletDAO
	 */
	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}


	/**
	 * @param hamletDAO the hamletDAO to set
	 */
	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}


	/**
	 * @return the tehsilDAO
	 */
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}


	/**
	 * @param tehsilDAO the tehsilDAO to set
	 */
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}


	/**
	 * @return the countryDAO
	 */
	public ICountryDAO getCountryDAO() {
		return countryDAO;
	}


	/**
	 * @param countryDAO the countryDAO to set
	 */
	public void setCountryDAO(ICountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}


	/**
	 * @return the districtDAO
	 */
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}


	/**
	 * @param districtDAO the districtDAO to set
	 */
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}


	/**
	 * @return the townshipDAO
	 */
	public ITownshipDAO getTownshipDAO() {
		return townshipDAO;
	}


	/**
	 * @param townshipDAO the townshipDAO to set
	 */
	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}


	/**
	 * @return the constituencyDAO
	 */
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}


	/**
	 * @param constituencyDAO the constituencyDAO to set
	 */
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}


	/**
	 * @return the localBodyDAO
	 */
	public ILocalElectionBodyDAO getLocalBodyDAO() {
		return localBodyDAO;
	}


	/**
	 * @param localBodyDAO the localBodyDAO to set
	 */
	public void setLocalBodyDAO(ILocalElectionBodyDAO localBodyDAO) {
		this.localBodyDAO = localBodyDAO;
	}


	/**
	 * @return the cadreDAO
	 */
	public ICadreDAO getCadreDAO() {
		return cadreDAO;
	}


	/**
	 * @param cadreDAO the cadreDAO to set
	 */
	public void setCadreDAO(ICadreDAO cadreDAO) {
		this.cadreDAO = cadreDAO;
	}


	/* (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IGenericUploadDataService#saveGenericUploadData(com.itgrids.partyanalyst.dto.GenericUploadDataVO, java.lang.String)
	 */
	/**
	 * @return the transactionTemplate
	 */
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}


	/**
	 * @param transactionTemplate the transactionTemplate to set
	 */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}



	/**
	 * @return the cadreLevelDAO
	 */
	public ICadreLevelDAO getCadreLevelDAO() {
		return cadreLevelDAO;
	}


	/**
	 * @param cadreLevelDAO the cadreLevelDAO to set
	 */
	public void setCadreLevelDAO(ICadreLevelDAO cadreLevelDAO) {
		this.cadreLevelDAO = cadreLevelDAO;
	}


	/**
	 * @return the occupationDAO
	 */
	public IOccupationDAO getOccupationDAO() {
		return occupationDAO;
	}


	/**
	 * @param occupationDAO the occupationDAO to set
	 */
	public void setOccupationDAO(IOccupationDAO occupationDAO) {
		this.occupationDAO = occupationDAO;
	}


	/**
	 * @return the userAddressDAO
	 */
	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}


	/**
	 * @param userAddressDAO the userAddressDAO to set
	 */
	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}


	/**
	 * @return the registrationDAO
	 */
	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}


	/**
	 * @param registrationDAO the registrationDAO to set
	 */
	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}


	/**
	 * @return the socialCategoryDAO
	 */
	public ISocialCategoryDAO getSocialCategoryDAO() {
		return socialCategoryDAO;
	}


	/**
	 * @param socialCategoryDAO the socialCategoryDAO to set
	 */
	public void setSocialCategoryDAO(ISocialCategoryDAO socialCategoryDAO) {
		this.socialCategoryDAO = socialCategoryDAO;
	}


	/**
	 * @return the educationalQualificationsDAO
	 */
	public IEducationalQualificationsDAO getEducationalQualificationsDAO() {
		return educationalQualificationsDAO;
	}


	/**
	 * @param educationalQualificationsDAO the educationalQualificationsDAO to set
	 */
	public void setEducationalQualificationsDAO(
			IEducationalQualificationsDAO educationalQualificationsDAO) {
		this.educationalQualificationsDAO = educationalQualificationsDAO;
	}


	/**
	 * Method to save generic modules 
	 */
	public ResultStatus saveGenericUploadData(Long userId,GenericUploadDataVO uploadResultsVO, String module) {
	
		ResultStatus resultStatus = new ResultStatus();
		if(log.isDebugEnabled())
			log.debug("Started Saving The Data To DB ..");
		
		//saving cadre data to DB.
		if(module.equalsIgnoreCase(IConstants.CADRE))
			resultStatus = saveCadreUploadData(userId,uploadResultsVO);
		
	 return resultStatus;
	}

	/**
	 * Saving Cadre Details
	 */
	public ResultStatus saveCadreUploadData(final Long userId,final GenericUploadDataVO uploadResultsVO) {
		
		if(log.isDebugEnabled())
			log.debug("Started Saving Cadre Details ..");
		
		ResultStatus resultStatus = (ResultStatus)transactionTemplate.execute(new TransactionCallback(){

			public Object doInTransaction(TransactionStatus txStatus) {
				
				ResultStatus rs = new ResultStatus();
				try{
					
					Cadre cadre = saveCadreBasicInfo(userId,uploadResultsVO);
					saveCadreAssignProblemsDetails(cadre,uploadResultsVO.getAssignProblems());
					if(cadre != null && cadre.getCadreId() != null){
						log.debug("Cadre Saved Upto Basic Level ..");
					
						//save cadre family members
						saveCadreFamilyMembersDetails(cadre,uploadResultsVO);
					}
					
					rs.setResultCode(ResultCodeMapper.SUCCESS);
					rs.setResultPartial(false);
					
				}catch(Exception ex){
					ex.printStackTrace();
					log.error("Exception Raised :" + ex);
					txStatus.setRollbackOnly();
					rs.setExceptionEncountered(ex);
					rs.setExceptionMsg(ex.getMessage());
					rs.setResultCode(ResultCodeMapper.FAILURE);
				}
			 return rs;
			}
			
		});
		
	 return resultStatus;
	}
	
	/**
	 * check and save whether cadre need to be assigned problems
	 * @param cadre
	 * @param assignProblems
	 */
	private void saveCadreAssignProblemsDetails(Cadre cadre,String assignProblems){
		
		log.debug("This Cadre status to be assigned with problems is :" + assignProblems);
		
		if(assignProblems.equalsIgnoreCase(IConstants.YES)){
			
			List<CadreRole> cadreRoleLst = cadreRoleDAO.findByRoleDesc(IConstants.CADRE_ROLE_ASSIGN_PROBLEMS);
			if(cadreRoleLst != null && cadreRoleLst.size() > 0){
				
				CadreRole cadreRole = cadreRoleLst.get(0);
				CadreRoleRelation cadreRoleRelation = new CadreRoleRelation();
				cadreRoleRelation.setCadre(cadre);
				cadreRoleRelation.setCadreRole(cadreRole);
				
				cadreRoleRelationDAO.save(cadreRoleRelation);
				
			}
		}
	}
	
	private Date getExactDateOfBirthFromAge(Long age){
		
		Date date = null;
		
		Calendar cal = Calendar.getInstance();
		Date todaysDate = new Date();
		cal.setTime((todaysDate));
		Integer calcAge = new Integer(age.intValue());
		cal.add(Calendar.YEAR, -calcAge);
		
		date = cal.getTime();
		
	 return date;
	}
	
	/**
	 * Saving Cadre Family Member Details
	 * @param cadre
	 * @param uploadResultsVO
	 */
	private void saveCadreFamilyMembersDetails(Cadre cadre,GenericUploadDataVO uploadResultsVO) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Started Saving Cadre Family Members Details ..");
		
		try{
		Date dob = new Date();
		String name = "";
		
		//saving spouse or father details
		if(uploadResultsVO.getSpouse() != null && !"".equalsIgnoreCase(uploadResultsVO.getSpouse()) || 
				uploadResultsVO.getFather() != null && !"".equalsIgnoreCase(uploadResultsVO.getFather())){
			
			name = 	uploadResultsVO.getSpouse();
			if(uploadResultsVO.getFatherOrSpouseDob() != null)
				dob = uploadResultsVO.getFatherOrSpouseDob();
			else if(uploadResultsVO.getFatherOrSpouseAge() != null && uploadResultsVO.getFatherOrSpouseAge() > 0){
				
				/*Calendar cal = Calendar.getInstance();
				Date todaysDate = new Date();
				cal.setTime((todaysDate));
				Integer age = new Integer(uploadResultsVO.getFatherOrSpouseAge().intValue());
				cal.add(Calendar.YEAR, -age);*/
				
				dob = getExactDateOfBirthFromAge(uploadResultsVO.getFatherOrSpouseAge());
			}
			
			if(uploadResultsVO.getSpouse() != null && !"".equalsIgnoreCase(uploadResultsVO.getSpouse()))
				saveCadreFamilyData(cadre,name,"Spouse",dob);
			else if(uploadResultsVO.getFather() != null && !"".equalsIgnoreCase(uploadResultsVO.getFather()))
					saveCadreFamilyData(cadre,name,"Father",dob);
		}
		
		//saving child details child 1
		if(uploadResultsVO.getFirstChild() != null && !"".equalsIgnoreCase(uploadResultsVO.getFirstChild())){
			
			name = uploadResultsVO.getFirstChild();
			
			if(uploadResultsVO.getFirstChildDob() != null)
				dob = uploadResultsVO.getFirstChildDob();
			else if(uploadResultsVO.getFirstChildAge() != null && uploadResultsVO.getFirstChildAge() > 0)
				dob = getExactDateOfBirthFromAge(uploadResultsVO.getFirstChildAge());
				
			saveCadreFamilyData(cadre,name,"Child",dob);
		}
		
		//saving child details child 2
		if(uploadResultsVO.getSecondChild() != null && !"".equalsIgnoreCase(uploadResultsVO.getSecondChild())){
			
			name = uploadResultsVO.getSecondChild();
			if(uploadResultsVO.getSecondChildDob() != null)
				dob = uploadResultsVO.getSecondChildDob();
			else if(uploadResultsVO.getSecondChildAge() != null && uploadResultsVO.getSecondChildAge() > 0)
				dob = getExactDateOfBirthFromAge(uploadResultsVO.getSecondChildAge());
			
			saveCadreFamilyData(cadre,name,"Child",dob);
		}
		
		//saving child details child 3
		if(uploadResultsVO.getThirdChild() != null && !"".equalsIgnoreCase(uploadResultsVO.getThirdChild())){
			
			name = uploadResultsVO.getThirdChild();
			if(uploadResultsVO.getThirdChildDob() != null)
				dob = uploadResultsVO.getThirdChildDob();
			else if(uploadResultsVO.getThirdChildAge() != null && uploadResultsVO.getThirdChildAge() > 0)
				dob = getExactDateOfBirthFromAge(uploadResultsVO.getThirdChildAge());
			
			saveCadreFamilyData(cadre,name,"Child",dob);
		}
		}catch(Exception ex){
			throw ex;
		}
		
	}
	
	/**
	 * 
	 * @param cadre
	 * @param memberName
	 * @param type
	 * @param dob
	 */
	private void saveCadreFamilyData(Cadre cadre,String memberName,String type,Date dob) throws Exception{
		
		try{
			CadreFamilyMemberInfo cadreFamilyMembers = new CadreFamilyMemberInfo();
			List<UserRelation> userRelationList = null;
			
			userRelationList = userRelationDAO.findByRelationType(type);
			if(userRelationList == null || userRelationList.size() == 0)
				userRelationList = userRelationDAO.findByRelationType("Others");
			
			if(userRelationList != null && userRelationList.size() > 0){
				
				cadreFamilyMembers.setCadre(cadre);
				cadreFamilyMembers.setName(memberName);
				cadreFamilyMembers.setDateOfBirth(dob);
				cadreFamilyMembers.setUserRelation(userRelationList.get(0));
				
				cadreFamilyMemberInfoDAO.save(cadreFamilyMembers);
			}
		}catch(Exception ex){
			throw ex;
		}
	}
	
	/**
	 * Saving cadre Basic Info
	 * @param userId
	 * @param uploadResultsVO
	 * @return
	 * @throws Exception
	 */
	private Cadre saveCadreBasicInfo(Long userId,GenericUploadDataVO uploadResultsVO) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Saving Cadre Basic Details ..");
		
		Cadre cadre = new Cadre();
		
		try{
		//Get User
		Registration user = registrationDAO.get(userId);
		cadre.setRegistration(user);
		
		//Get Educational Details
		List<EducationalQualifications> educationList = null;
		if(uploadResultsVO.getEducation() != null && !"".equalsIgnoreCase(uploadResultsVO.getEducation()))
			educationList = educationalQualificationsDAO.getEducationalQualificationsByQualificationType(uploadResultsVO.getEducation());
		else
			educationList = educationalQualificationsDAO.getEducationalQualificationsByQualificationType("Not Applicable");
		if(educationList != null && educationList.size() > 0)
			cadre.setEducation(educationList.get(0));
		
		//Get Occupation Details
		List<Occupation> occupationList = null;
		if(uploadResultsVO.getProfession() != null && !"".equalsIgnoreCase(uploadResultsVO.getProfession()))
			occupationList = occupationDAO.getOccupationDetailsByOccupationType(uploadResultsVO.getProfession());
		else
			occupationList = occupationDAO.getOccupationDetailsByOccupationType("Others");
		if(occupationList != null && occupationList.size() > 0)
			cadre.setOccupation(occupationList.get(0));
		
		//Get SocialCategory Details
		List<SocialCategory> socialCatList = null;
		if(uploadResultsVO.getCaste() != null && !"".equalsIgnoreCase(uploadResultsVO.getCaste()))
			socialCatList = socialCategoryDAO.getSocialCategoryDetailsByCategoryType(uploadResultsVO.getCaste());
		else 
			socialCatList = socialCategoryDAO.getSocialCategoryDetailsByCategoryType("N/A");
		if(socialCatList != null && socialCatList.size() > 0)
			cadre.setCasteCategory(socialCatList.get(0));
		
		//Get Cadre Level Details
		if(uploadResultsVO.getCadreLevel() != null && !"".equalsIgnoreCase(uploadResultsVO.getCadreLevel())){
			
			Long cadreLevel = getCadreLevelId(uploadResultsVO.getCadreLevel());
			if(!cadreLevel.equals(0L)){
				CadreLevel cadreLevelObj = cadreLevelDAO.get(cadreLevel);
				if(cadreLevelObj != null)
					cadre.setCadreLevel(cadreLevelObj);
			}
		}
		
		//Get Cadre Current Address
		UserAddress currentAddress = getCadreCurrentAddress(uploadResultsVO);
		cadre.setCurrentAddress(currentAddress);
		cadre.setPermanentAddress(currentAddress);
		
		//cadre basic details
		if(uploadResultsVO.getFirstName() == null && uploadResultsVO.getName() == null && uploadResultsVO.getMiddleName() == null && uploadResultsVO.getLastName() == null && 
				uploadResultsVO.getHeadOfFamily() == null){
			throw new Exception("No Cadre Name Data Exists ..");
		}
		
		String firstName =  uploadResultsVO.getFirstName() != null ? uploadResultsVO.getFirstName() : uploadResultsVO.getName();
		if(uploadResultsVO.getHeadOfFamily() != null && !"".equalsIgnoreCase(uploadResultsVO.getHeadOfFamily()))
			cadre.setFirstName(uploadResultsVO.getHeadOfFamily());
		else
			cadre.setFirstName(firstName.trim());
		String middleName = uploadResultsVO.getMiddleName() != null ? uploadResultsVO.getMiddleName():"";
		cadre.setMiddleName(middleName.trim());
		String lastName = uploadResultsVO.getLastName() != null ? uploadResultsVO.getLastName():"";
		cadre.setLastName(lastName.trim());
		String memberType = uploadResultsVO.getMemberType() != null ? uploadResultsVO.getMemberType() : "Active";
		cadre.setMemberType(memberType.trim());
		String mobile  = uploadResultsVO.getMobile() != null ? uploadResultsVO.getMobile() : "";
		cadre.setMobile(mobile.trim());
		String email = uploadResultsVO.getEmail() != null ? uploadResultsVO.getEmail() : "";
		cadre.setEmail(email.trim());
		String gender = uploadResultsVO.getGender() != null ? uploadResultsVO.getGender() : "Male";
		cadre.setGender(gender.trim());
		String fatherOrSpouse = uploadResultsVO.getFather() != null ? uploadResultsVO.getFather() : uploadResultsVO.getSpouse();
		if(fatherOrSpouse != null)
		cadre.setFatherOrSpouseName(fatherOrSpouse.trim());
		String memOfPartySince = uploadResultsVO.getMemberOfPartySince() != null ? uploadResultsVO.getMemberOfPartySince() : null;
		cadre.setMemberOfPartySince(memOfPartySince);
		String presentRespInParty = uploadResultsVO.getPresentResponsibilityInParty() != null ? uploadResultsVO.getPresentResponsibilityInParty() : null;
		cadre.setPresentRespInParty(presentRespInParty);
				
		
		//Cadre Date Of Birth
		if(uploadResultsVO.getDateOfBirth() != null){
			
			cadre.setDateOfBirth(uploadResultsVO.getDateOfBirth());
			cadre.setExactDateOfBirth("true");
			
		}else if(uploadResultsVO.getAge() != null && uploadResultsVO.getAge() > 0L){
			
			Calendar cal = Calendar.getInstance();
			Date todaysDate = new Date();
			cal.setTime((todaysDate));
			Integer age = new Integer(uploadResultsVO.getAge().intValue());
			cal.add(Calendar.YEAR, -age);
			cadre.setDateOfBirth(cal.getTime());
			cadre.setExactDateOfBirth("false");
		}
		
		//set cadre level value
		cadre.setCadreLevelValue(getCadreLevelValue(uploadResultsVO));
		
		cadre = cadreDAO.save(cadre);
		}catch(Exception ex){
			throw ex;
		}
	 return cadre;
	}
	
	private Long getCadreLevelId(String cadreLevel) throws Exception{
		
		
		Long level = 0L;
		try{
		List<CadreLevel> levelList = cadreLevelDAO.findByCadreLevel(cadreLevel);
		if(levelList != null && levelList.size() > 0)
			level = levelList.get(0).getCadreLevelID();
		}catch(Exception ex){
			throw ex;
		}
		
	 return level;
	}
	
	/**
	 * Getting Cadre Address Object
	 * @param uploadResultsVO
	 * @return
	 * @throws Exception
	 */
	private UserAddress getCadreCurrentAddress(GenericUploadDataVO uploadResultsVO) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Saving Cadre Address Details ..");
		
		UserAddress userAddress = new UserAddress();
		
		try{
		//For India
		Country country = countryDAO.get(1L);
		userAddress.setCountry(country);
		
        State state       = stateDAO.get(uploadResultsVO.getStateId());
        District district = districtDAO.get(uploadResultsVO.getDistrictId());
        Constituency constituency = constituencyDAO.get(uploadResultsVO.getConstituencyId());
        
        userAddress.setState(state);
        userAddress.setDistrict(district);
        userAddress.setConstituency(constituency);
        
        if(uploadResultsVO.getMandalId() != null && !uploadResultsVO.getMandalId().equals(0L)){
        	
        	Tehsil tehsil = tehsilDAO.get(uploadResultsVO.getMandalId());
        	userAddress.setTehsil(tehsil);
        	
        	if(uploadResultsVO.getVillageId() == null || uploadResultsVO.getVillageId().equals(0L))
        		throw new Exception("Village Details Not Available ..");
        		
        	//village details
        	if(uploadResultsVO.getVillageId() != null && !uploadResultsVO.getVillageId().equals(0L)){
            	
            	Township village = townshipDAO.get(uploadResultsVO.getVillageId());
            	userAddress.setTownship(village);
            }
        }
        
        if(uploadResultsVO.getLocalBodyId() != null && !uploadResultsVO.getLocalBodyId().equals(0L)){
        	
        	LocalElectionBody localBody = localBodyDAO.get(uploadResultsVO.getLocalBodyId());
        	userAddress.setLocalElectionBody(localBody);
        	
        	if(uploadResultsVO.getWardId() == null || uploadResultsVO.getWardId().equals(0L))
        		throw new Exception("Ward Details Not Available ..");
        	
        	//ward details
        	if(uploadResultsVO.getWardId() != null && !uploadResultsVO.getWardId().equals(0L)){
            	
            	Constituency ward = constituencyDAO.get(uploadResultsVO.getWardId());
            	userAddress.setWard(ward);
            }
        }
        
        
        
        
        
        if(uploadResultsVO.getBoothId() != null && !uploadResultsVO.getBoothId().equals(0L)){
        	
        	Booth booth = boothDAO.get(uploadResultsVO.getBoothId());
        	userAddress.setBooth(booth);
        }
        
        if(uploadResultsVO.getHamletId() != null && !uploadResultsVO.getHamletId().equals(0L)){
        	
        	Hamlet hamlet = hamletDAO.get(uploadResultsVO.getHamletId());
        	userAddress.setHamlet(hamlet);
        }
        
        if(uploadResultsVO.getParliamentConstiId() != null && !uploadResultsVO.getParliamentConstiId().equals(0L)){
        	
        	Constituency parliamentConsti = constituencyDAO.get(uploadResultsVO.getParliamentConstiId());
        	userAddress.setParliamentConstituency(parliamentConsti);
        	
        }
        
        if(uploadResultsVO.getHouseNo() != null && !"".equalsIgnoreCase(uploadResultsVO.getHouseNo()))
        	userAddress.setHouseNo(uploadResultsVO.getHouseNo());
        if(uploadResultsVO.getStreet() != null && !"".equalsIgnoreCase(uploadResultsVO.getStreet()))
        	userAddress.setStreet(uploadResultsVO.getStreet());
        if(uploadResultsVO.getPincode() != null && !"".equalsIgnoreCase(uploadResultsVO.getPincode()))
        	userAddress.setPinCode(uploadResultsVO.getPincode());
		}catch(Exception ex){
			throw ex;
		}
      		
	 return userAddress;
	}
	
	/**
	 * Getting cadre Level Details
	 * @param uploadResultsVO
	 * @return
	 * @throws Exception
	 */
	private Long getCadreLevelValue(GenericUploadDataVO uploadResultsVO) throws Exception{
		
		Long levelValue = 0L;
		
		try{
		if(uploadResultsVO.getCadreLevel() != null && !"".equalsIgnoreCase(uploadResultsVO.getCadreLevel())){
			
			if(uploadResultsVO.getCadreLevel().equalsIgnoreCase(IConstants.STATE))
				return uploadResultsVO.getStateId();
			
			else if(uploadResultsVO.getCadreLevel().equalsIgnoreCase(IConstants.DISTRICT))
				return uploadResultsVO.getDistrictId();
			
			else if(uploadResultsVO.getCadreLevel().equalsIgnoreCase(IConstants.CONSTITUENCY))
				return uploadResultsVO.getConstituencyId();
			
			else if(uploadResultsVO.getCadreLevel().equalsIgnoreCase(IConstants.TEHSIL) || uploadResultsVO.getCadreLevel().equalsIgnoreCase(IConstants.MANDAL))
				return uploadResultsVO.getMandalId();
			
			else if(uploadResultsVO.getCadreLevel().equalsIgnoreCase(IConstants.VILLAGE) || uploadResultsVO.getCadreLevel().equalsIgnoreCase(IConstants.TOWNSHIP))
				return uploadResultsVO.getVillageId();
			
			else if(uploadResultsVO.getCadreLevel().equalsIgnoreCase(IConstants.WARD))
				return uploadResultsVO.getWardId();
			
			else if(uploadResultsVO.getCadreLevel().equalsIgnoreCase(IConstants.BOOTH))
				return uploadResultsVO.getBoothId();
			
			else if(uploadResultsVO.getCadreLevel().equalsIgnoreCase("MUNCIPAL-CORP-GMC") ||
					uploadResultsVO.getCadreLevel().equalsIgnoreCase("MUNCIPALITY/CORPORATION"))
				return uploadResultsVO.getLocalBodyId();
		}
		}catch(Exception ex){
			throw ex;
		}
	 return levelValue;
	}


	/**
	 * @return the userRelationDAO
	 */
	public IUserRelationDAO getUserRelationDAO() {
		return userRelationDAO;
	}


	/**
	 * @param userRelationDAO the userRelationDAO to set
	 */
	public void setUserRelationDAO(IUserRelationDAO userRelationDAO) {
		this.userRelationDAO = userRelationDAO;
	}


	/**
	 * @return the cadreFamilyMemberInfoDAO
	 */
	public ICadreFamilyMemberInfoDAO getCadreFamilyMemberInfoDAO() {
		return cadreFamilyMemberInfoDAO;
	}


	/**
	 * @param cadreFamilyMemberInfoDAO the cadreFamilyMemberInfoDAO to set
	 */
	public void setCadreFamilyMemberInfoDAO(
			ICadreFamilyMemberInfoDAO cadreFamilyMemberInfoDAO) {
		this.cadreFamilyMemberInfoDAO = cadreFamilyMemberInfoDAO;
	}


	public ICadreRoleDAO getCadreRoleDAO() {
		return cadreRoleDAO;
	}


	public void setCadreRoleDAO(ICadreRoleDAO cadreRoleDAO) {
		this.cadreRoleDAO = cadreRoleDAO;
	}


	public ICadreRoleRelationDAO getCadreRoleRelationDAO() {
		return cadreRoleRelationDAO;
	}


	public void setCadreRoleRelationDAO(ICadreRoleRelationDAO cadreRoleRelationDAO) {
		this.cadreRoleRelationDAO = cadreRoleRelationDAO;
	}

}
