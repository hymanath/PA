package com.itgrids.partyanalyst.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICadreLanguageEfficiencyDAO;
import com.itgrids.partyanalyst.dao.ICadreParticipatedTrainingCampsDAO;
import com.itgrids.partyanalyst.dao.ICadreSkillsDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILanguageDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPartyCadreSkillsDAO;
import com.itgrids.partyanalyst.dao.IPartyTrainingCampsDAO;
import com.itgrids.partyanalyst.dao.IPartyWorkingCommitteeDAO;
import com.itgrids.partyanalyst.dao.IPartyWorkingCommitteeDesignationDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.ISocialCategoryDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dto.CadreCategoryVO;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.CadreRegionInfoVO;
import com.itgrids.partyanalyst.dto.PartyCadreDetailsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SmsResultVO;
import com.itgrids.partyanalyst.dto.StateToHamletVO;
import com.itgrids.partyanalyst.dto.UserCadresInfoVO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreLanguageEfficiency;
import com.itgrids.partyanalyst.model.CadreLevel;
import com.itgrids.partyanalyst.model.CadreParticipatedTrainingCamps;
import com.itgrids.partyanalyst.model.CadreSkills;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Country;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.Language;
import com.itgrids.partyanalyst.model.PartyCadreSkills;
import com.itgrids.partyanalyst.model.PartyTrainingCamps;
import com.itgrids.partyanalyst.model.PartyWorkingCommittee;
import com.itgrids.partyanalyst.model.PartyWorkingCommitteeDesignation;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.Township;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.utils.IConstants;
/**
 * 
 * @author Narender Akula
 *
 */
public class CadreManagementService {

	private ICadreDAO cadreDAO;
	
	public void setCadreDAO(ICadreDAO cadreDAO) {
		this.cadreDAO = cadreDAO;
	}

	private ICountryDAO countryDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private ITehsilDAO tehsilDAO;
	private ITownshipDAO townshipDAO;
	private IRegistrationDAO registrationDAO;
	private IConstituencyDAO constituencyDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;	 
	private SmsCountrySmsService smsCountrySmsService;
	private IHamletDAO hamletDAO;
	private IPartyWorkingCommitteeDAO partyWorkingCommitteeDAO;
	private IPartyWorkingCommitteeDesignationDAO partyWorkingCommitteeDesignationDAO;
	private static final Logger log = Logger.getLogger(CadreManagementService.class);
	private CadreInfo cadreInfo = null;
	private TransactionTemplate transactionTemplate = null;
	private IPartyCadreSkillsDAO partyCadreSkillsDAO;
	private IPartyTrainingCampsDAO partyTrainingCampsDAO;
	private IEducationalQualificationsDAO educationalQualificationsDAO;
	private IOccupationDAO occupationDAO;
	private ISocialCategoryDAO socialCategoryDAO;
	private ICadreSkillsDAO cadreSkillsDAO;
	private ICadreParticipatedTrainingCampsDAO cadreParticipatedTrainingCampsDAO; 
	private ILanguageDAO languageDAO;
	private ICadreLanguageEfficiencyDAO cadreLanguageEfficiencyDAO;
	
	public void setCountryDAO(ICountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}


	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}


	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}


	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}


	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}

	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}


	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}


	public void setSmsCountrySmsService(SmsCountrySmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}


	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}	

	public IPartyWorkingCommitteeDAO getPartyWorkingCommitteeDAO() {
		return partyWorkingCommitteeDAO;
	}


	public void setPartyWorkingCommitteeDAO(
			IPartyWorkingCommitteeDAO partyWorkingCommitteeDAO) {
		this.partyWorkingCommitteeDAO = partyWorkingCommitteeDAO;
	}	


	public IPartyWorkingCommitteeDesignationDAO getPartyWorkingCommitteeDesignationDAO() {
		return partyWorkingCommitteeDesignationDAO;
	}


	public void setPartyWorkingCommitteeDesignationDAO(
			IPartyWorkingCommitteeDesignationDAO partyWorkingCommitteeDesignationDAO) {
		this.partyWorkingCommitteeDesignationDAO = partyWorkingCommitteeDesignationDAO;
	}
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}


	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}	

	public IPartyCadreSkillsDAO getPartyCadreSkillsDAO() {
		return partyCadreSkillsDAO;
	}


	public void setPartyCadreSkillsDAO(IPartyCadreSkillsDAO partyCadreSkillsDAO) {
		this.partyCadreSkillsDAO = partyCadreSkillsDAO;
	}

	public IPartyTrainingCampsDAO getPartyTrainingCampsDAO() {
		return partyTrainingCampsDAO;
	}


	public void setPartyTrainingCampsDAO(
			IPartyTrainingCampsDAO partyTrainingCampsDAO) {
		this.partyTrainingCampsDAO = partyTrainingCampsDAO;
	}

	public IEducationalQualificationsDAO getEducationalQualificationsDAO() {
		return educationalQualificationsDAO;
	}


	public void setEducationalQualificationsDAO(
			IEducationalQualificationsDAO educationalQualificationsDAO) {
		this.educationalQualificationsDAO = educationalQualificationsDAO;
	}

	public ICadreSkillsDAO getCadreSkillsDAO() {
		return cadreSkillsDAO;
	}


	public void setCadreSkillsDAO(ICadreSkillsDAO cadreSkillsDAO) {
		this.cadreSkillsDAO = cadreSkillsDAO;
	}

	public ICadreParticipatedTrainingCampsDAO getCadreParticipatedTrainingCampsDAO() {
		return cadreParticipatedTrainingCampsDAO;
	}


	public void setCadreParticipatedTrainingCampsDAO(
			ICadreParticipatedTrainingCampsDAO cadreParticipatedTrainingCampsDAO) {
		this.cadreParticipatedTrainingCampsDAO = cadreParticipatedTrainingCampsDAO;
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

	public ILanguageDAO getLanguageDAO() {
		return languageDAO;
	}


	public void setLanguageDAO(ILanguageDAO languageDAO) {
		this.languageDAO = languageDAO;
	}

	public ICadreLanguageEfficiencyDAO getCadreLanguageEfficiencyDAO() {
		return cadreLanguageEfficiencyDAO;
	}


	public void setCadreLanguageEfficiencyDAO(
			ICadreLanguageEfficiencyDAO cadreLanguageEfficiencyDAO) {
		this.cadreLanguageEfficiencyDAO = cadreLanguageEfficiencyDAO;
	}


	public Long saveCader(CadreInfo cadreInfoToSave, String[] skills,String[] trainingCamps){
		this.cadreInfo = cadreInfoToSave;	
		if(log.isDebugEnabled()){
			log.debug("cadrerManagementService.saveCadre():::-constituencyID::"+cadreInfo.getConstituencyID());
		}
			Cadre cadreObj = (Cadre)transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
		
			UserAddress currentAddress = new UserAddress();
			UserAddress permanentAddress =  new UserAddress();
			Cadre cadre = new Cadre();
			
			try{
				cadre.setFirstName(cadreInfo.getFirstName());
				cadre.setMiddleName(cadreInfo.getMiddleName());
				cadre.setLastName(cadreInfo.getLastName());
				cadre.setGender(cadreInfo.getGender());
				cadre.setState(stateDAO.get(new Long(CadreManagementService.this.cadreInfo.getState())));
				cadre.setDistrict(districtDAO.get(new Long(CadreManagementService.this.cadreInfo.getDistrict())));
				cadre.setTehsil(tehsilDAO.get(new Long(cadreInfo.getMandal())));
				cadre.setFatherOrSpouseName(cadreInfo.getFatherOrSpouseName());
				//setting address
				currentAddress.setHouseNo(cadreInfo.getHouseNo());
				currentAddress.setStreet(cadreInfo.getStreet());
				currentAddress.setPinCode(cadreInfo.getPinCode());
				currentAddress.setState(stateDAO.get(new Long(cadreInfo.getState())));
				currentAddress.setDistrict(districtDAO.get(new Long(cadreInfo.getDistrict())));
				currentAddress.setConstituency(constituencyDAO.get(cadreInfo.getConstituencyID()));
				currentAddress.setTehsil(tehsilDAO.get(new Long(cadreInfo.getMandal())));
				String villageFlag = "";
				Long id = 0l;
				villageFlag = cadreInfo.getVillage().substring(0, 1);
				id = new Long(cadreInfo.getVillage().substring(1));
				processAddressValues(villageFlag, id, cadre, currentAddress);
				System.out.println("cadreInfo.getSameAsCA():::::::::::::::"+cadreInfo.getSameAsCA());
				if(cadreInfo.getSameAsCA() == null)
				{
					System.out.println("inside permanent address block");
					villageFlag = cadreInfo.getPvillage().substring(0, 1);
					id = new Long(cadreInfo.getPvillage().substring(1));		
					permanentAddress.setHouseNo(cadreInfo.getPhouseNo());
					permanentAddress.setStreet(cadreInfo.getPstreet());
					permanentAddress.setPinCode(cadreInfo.getPpinCode());
					permanentAddress.setState(stateDAO.get(new Long(cadreInfo.getPstate())));
					permanentAddress.setDistrict(districtDAO.get(new Long(cadreInfo.getPdistrict())));
					permanentAddress.setConstituency(constituencyDAO.get(cadreInfo.getPconstituencyID()));
					permanentAddress.setTehsil(tehsilDAO.get(new Long(cadreInfo.getPmandal())));
					processAddressValues(villageFlag, id, cadre, permanentAddress);
				} else if("on".equals(cadreInfo.getSameAsCA()))
				{		
					permanentAddress = currentAddress;		
				} 
				cadre.setCurrentAddress(currentAddress);
				cadre.setPermanentAddress(permanentAddress);
				cadre.setMobile(cadreInfo.getMobile());
				cadre.setEmail(cadreInfo.getEmail());
				cadre.setConstituency(constituencyDAO.get(cadreInfo.getConstituencyID()));
				cadre.setRegistration(registrationDAO.get(cadreInfo.getUserID()));
				SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_PATTERN);
				if(cadreInfo.getDobOption() != null && "dobOption".equals(cadreInfo.getDobOption()))
					{
						cadre.setDateOfBirth(format.parse(cadreInfo.getDateOfBirth()));
						cadre.setExactDateOfBirth("true");
					}
				else if(cadreInfo.getDobOption() != null && "age".equals(cadreInfo.getDobOption()))
				{
					 Calendar cal = Calendar.getInstance();
					 Date todaysDate = new Date();
					 cal.setTime((todaysDate));
					 Integer age = new Integer(cadreInfo.getAge());
					 cal.add(Calendar.YEAR, -age);
					 cadre.setDateOfBirth(cal.getTime());
					 cadre.setExactDateOfBirth("false");					 
				}				
				cadre.setTelephone(cadreInfo.getLandLineNo());
				if(!cadreInfo.getEducation().equals(new Long(-1)))
				 cadre.setEducation(educationalQualificationsDAO.get(cadreInfo.getEducation()));
				if(!cadreInfo.getOccupation().equals(new Long(-1)))
				 cadre.setOccupation(occupationDAO.get(cadreInfo.getOccupation()));
				if(cadreInfo.getCasteCategory() != null)
				 cadre.setCasteCategory(socialCategoryDAO.get(cadreInfo.getCasteCategory()));
				
				cadre.setMemberType(cadreInfo.getMemberType());
				Double annunaIncome = 0d;
				if(cadreInfo.getAnnualIncome() != null && (!StringUtils.isBlank(cadreInfo.getAnnualIncome())))
					annunaIncome = new Double(cadreInfo.getAnnualIncome()); 
				
				cadre.setAnnualIncome(annunaIncome);
								
				if(IConstants.CADRE_MEMBER_TYPE_ACTIVE.equals(cadreInfo.getMemberType()))
				{	
					CadreLevel level = new CadreLevel();
					level.setCadreLevelID(cadreInfo.getCadreLevel());
					String[] values = {"","COUNTRY","STATE","DISTRICT","CONSTITUENCY","MANDAL","VILLAGE"};
					level.setLevel(values[cadreInfo.getCadreLevel().intValue()]);
					cadre.setCadreLevel(level);
					log.debug("CadreManagementService.saveCadre();;cadreLevelValue::"+cadreInfo.getStrCadreLevelValue());
					cadre.setCadreLevelValue(new Long(cadreInfo.getStrCadreLevelValue()));
					if(IConstants.USER_TYPE_PARTY.equals(cadreInfo.getUserType()) && IConstants.BJP.equalsIgnoreCase(cadreInfo.getUserPartyName()))
					{
						if(!cadreInfo.getDesignation().equals("0"))
							cadre.setDesignation(partyWorkingCommitteeDesignationDAO.get(new Long(cadreInfo.getDesignation())));
						if(!StringUtils.isBlank(cadreInfo.getEffectiveDate()))
						    cadre.setEffectiveDate(format.parse(cadreInfo.getEffectiveDate()));
						if(!StringUtils.isBlank(cadreInfo.getEndingDate()))
							cadre.setEndingDate(format.parse(cadreInfo.getEndingDate()));											
					}
				}
				
			}catch(Exception e){
				status.setRollbackOnly();
				if(log.isDebugEnabled()){
					log.debug("Exception Raised while Update And Get Problems Under Pending::", e);
				}				
				e.printStackTrace();
			}					
				return cadreDAO.save(cadre);
			}		
				
		});
			 if(cadreInfo.getLanguageOptions_English() != null && cadreInfo.getLanguageOptions_English().length > 0)
				setLanguageEfficiency(cadreObj.getCadreId(),cadreInfo.getLanguageOptions_English(),IConstants.LANGUAGE_ENGLISH);
			 if(cadreInfo.getLanguageOptions_Hindi() != null && cadreInfo.getLanguageOptions_Hindi().length > 0)
				 setLanguageEfficiency(cadreObj.getCadreId(),cadreInfo.getLanguageOptions_Hindi(), IConstants.LANGUAGE_HINDI);
			 if(IConstants.USER_TYPE_PARTY.equals(cadreInfo.getUserType()) && IConstants.BJP.equalsIgnoreCase(cadreInfo.getUserPartyName()))	
			 {
				 if(skills != null)
					setCadreSkillsInfo(cadreObj.getCadreId(), skills);
				 if(trainingCamps != null)
					 setParticipatedTrainingCamps(cadreObj.getCadreId(), trainingCamps);
			 } 
			return cadreObj.getCadreId();
	}
	
	private void processAddressValues(String villageFlag, Long id, Cadre cadre, UserAddress address)
	{
		if(IConstants.HAMLET_TYPE.equalsIgnoreCase(villageFlag)){
			Hamlet hamlet = hamletDAO.get(id);
			
			cadre.setHamlet(hamlet);
			cadre.setVillage(hamlet.getTownship());
			address.setHamlet(hamlet);
			address.setTownship(hamlet.getTownship());

		}else{
			cadre.setVillage(townshipDAO.get(id));
			address.setTownship(townshipDAO.get(id));
		}
	}
	
	private void setCadreSkillsInfo(Long cadreId, String[] skills)
	{
		Cadre cadreObj = cadreDAO.get(cadreId);
		for(int i = 0; i< skills.length;i++ )
		{
			CadreSkills cadreSkills = new CadreSkills();
			PartyCadreSkills partyCadreSkill = partyCadreSkillsDAO.get(new Long(skills[i]));
			cadreSkills.setPartyCadreSkills(partyCadreSkill);
			cadreSkills.setCadre(cadreObj);
			cadreSkillsDAO.save(cadreSkills);			
		}		
		
	}
	
	private void setParticipatedTrainingCamps(Long cadreId, String[] trainingCamps)
	{
		Cadre cadreObj = cadreDAO.get(cadreId);
		for(int j = 0; j< trainingCamps.length;j++ )
		{
			CadreParticipatedTrainingCamps cadreParticipatedTrainingCamp = new CadreParticipatedTrainingCamps();
			PartyTrainingCamps partyTrainingCamp = partyTrainingCampsDAO.get(new Long(trainingCamps[j]));
			cadreParticipatedTrainingCamp.setPartyTrainingCamps(partyTrainingCamp);
			cadreParticipatedTrainingCamp.setCadre(cadreObj);
			cadreParticipatedTrainingCampsDAO.save(cadreParticipatedTrainingCamp);
		}
	}
	
	private void setLanguageEfficiency(Long cadreId, String[] options, String language)
	{
		Cadre cadreObj = cadreDAO.get(cadreId);
		List<Language> languages =  languageDAO.findByLanguage(language);
		Language languageObj = languages.get(0);
		CadreLanguageEfficiency cadreLanguageEfficiency = new CadreLanguageEfficiency();
		cadreLanguageEfficiency.setCadre(cadreObj);
		cadreLanguageEfficiency.setLanguage(languageObj);
		for(int i = 0; i<options.length; i++)
		{
			if("speak".equals(options[i]))
				cadreLanguageEfficiency.setIsAbleToSpeak("true");
			if("read".equals(options[i]))
				cadreLanguageEfficiency.setIsAbleToRead("true");
			if("write".equals(options[i]))
				cadreLanguageEfficiency.setIsAbleToWrite("true");
		}
		cadreLanguageEfficiencyDAO.save(cadreLanguageEfficiency);
		
	}
	
	public void deleteCadre(Long cadreID){
		cadreDAO.remove(cadreID);
	}

	
	public UserCadresInfoVO getUserCadresInfo(UserCadresInfoVO userCadreInfo){
		if(log.isDebugEnabled()){
			log.debug("CadreManagementService.getUserCadresInfo() started");
		}
		ResultStatus resultStatus = new ResultStatus();
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		try{
			Long totalUserAccessLevelCaders = cadreDAO.findTotalCadresByUserID(userCadreInfo.getUserID(),IConstants.CADRE_MEMBER_TYPE_ACTIVE);
			userCadreInfo.setTotalCadres(totalUserAccessLevelCaders);
			userCadreInfo = getUserAccessRegions(userCadreInfo);
			Map<String,Long> cadresByCadreLevel = getCadreLevelCadresCount(userCadreInfo.getUserID());
			userCadreInfo.setRegionLevelCadres(cadresByCadreLevel);
		}catch(Exception e){
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			resultStatus.setExceptionEncountered(e);
			e.printStackTrace();
		}
		
		return userCadreInfo;
	}

	
	@SuppressWarnings("unchecked")
	public UserCadresInfoVO getUserAccessRegions(UserCadresInfoVO userCadreInfo){
		if(log.isDebugEnabled()){
			log.debug("CadreManagementService.getUserAccessRegions() started");
		}
		Map<Long, String> userAccessStates = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessDistricts = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessMandals = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessVillages = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessHamlets = new LinkedHashMap<Long, String>();

		Map<Long, String> zeroCadreStates = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreDistricts = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreMandals = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreVillages = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreHamlets = new LinkedHashMap<Long, String>();
		
		String userAccessType = userCadreInfo.getUserAccessType();
		String accessID = userCadreInfo.getUserAccessValue();
		//Long accessID = new Long(userAccessValue);
		List<SelectOptionVO> regionLevelZeroCadres = userCadreInfo.getRegionLevelZeroCadres(); 
		
		boolean downLevelCadresFlag = true;
		if("MLA".equals(userAccessType)){ 
			if(log.isDebugEnabled()){
				log.debug("CadreManagementService.getUserAccessRegions() if MLA started");
			}
			//List mandals = cadreDAO.findMandalsByConstituencyID(accessID);
			
			List<DelimitationConstituency> delimitationConstituency = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(new Long(accessID));
			Long delimitationConstituencyID = delimitationConstituency.get(0).getDelimitationConstituencyID();
			List<Tehsil> tehsils = delimitationConstituencyMandalDAO.getTehsilsByDelimitationConstituencyID(delimitationConstituencyID);
			/*StringBuilder mandalIDs = new StringBuilder();
			for(Tehsil tehsil : tehsils){
				mandalIDs.append(",").append(tehsil.getTehsilId());
			}*/
			
			
			log.debug("CadreManagementService.getUserAccessRegions() mandals::"+tehsils.size());
			List cadreSizeMandalWise = cadreDAO.findCadreSizeMandalWise(userCadreInfo.getUserID());
			log.debug("CadreManagementService.getUserAccessRegions() cadreSizeMandalWise::"+cadreSizeMandalWise.size());
			if(cadreSizeMandalWise.size() == 0)
				downLevelCadresFlag = false;
			log.debug("downLevelCadresFlag::"+downLevelCadresFlag);
			long mandalLevelZeroCadres = tehsils.size() - cadreSizeMandalWise.size();
			StringBuilder sbMandals = getMLAFormatedData(tehsils,userAccessMandals,cadreSizeMandalWise,zeroCadreMandals);
			log.debug("CadreManagementService.getUserAccessRegions() sbMandals::"+sbMandals);
			userCadreInfo.setUserAccessMandals(userAccessMandals);
			userCadreInfo.setZeroCadreMandals(zeroCadreMandals);
			
			if(sbMandals!=null && sbMandals.length()>0)
				accessID = sbMandals.substring(0, sbMandals.length()-1);
			if(log.isDebugEnabled()){
				log.debug(userCadreInfo.getUserAccessValue()+"::mandalIDs for MLA constituencyID="+accessID);
			}
			SelectOptionVO voObject = new SelectOptionVO(mandalLevelZeroCadres,"MANDAL");
			if(mandalLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);
		}		 
		if("COUNTRY".equals(userAccessType)){
			if(log.isDebugEnabled()){
				log.debug("CadreManagementService.getUserAccessRegions() if COUNTRY started");
			}
			List states = cadreDAO.findStatesByCountryID(accessID);
			List cadreSizeStateWise = cadreDAO.findCadreSizeStateWise(userCadreInfo.getUserID());
			if(cadreSizeStateWise.size() == 0)
				downLevelCadresFlag = false;
			long stateLevelZeroCadres = states.size() - cadreSizeStateWise.size();
			StringBuilder sbStates = getFormatedData(states,userAccessStates,cadreSizeStateWise,zeroCadreStates);
			userCadreInfo.setUserAccessStates(userAccessStates);
			userCadreInfo.setZeroCadreStates(zeroCadreStates);
			if(log.isDebugEnabled()){
				log.debug("CadreManagementService.getUserAccessRegions() sbStates:"+sbStates);
			}
			if(sbStates!=null && sbStates.length()>0)
				accessID = sbStates.substring(0, sbStates.length()-1);
			
			SelectOptionVO voObject = new SelectOptionVO(stateLevelZeroCadres,"STATE");
			if(stateLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);
			
		}
		if((downLevelCadresFlag) && ("COUNTRY".equals(userAccessType) || "STATE".equals(userAccessType))){
			if(log.isDebugEnabled()){
				log.debug("CadreManagementService.getUserAccessRegions() if STATE started");
			}
			List districts = cadreDAO.findDistrictsByStateID(accessID);
			List cadreSizeDistrictWise = cadreDAO.findCadreSizeDistrictWise( userCadreInfo.getUserID());
			if(cadreSizeDistrictWise.size() == 0)
				downLevelCadresFlag = false;
			long districtLevelZeroCadres = districts.size() - cadreSizeDistrictWise.size();//getZeroSize(cadreSizeZero4District);
			StringBuilder sbDistricts = getFormatedData(districts,userAccessDistricts,cadreSizeDistrictWise,zeroCadreDistricts);
			userCadreInfo.setUserAccessDistricts(userAccessDistricts);
			userCadreInfo.setZeroCadreDistricts(zeroCadreDistricts);
			if(log.isDebugEnabled()){
				log.debug("CadreManagementService.getUserAccessRegions() sbDistricts:"+sbDistricts);
			}
			if(sbDistricts!=null && sbDistricts.length()>0)
				accessID = sbDistricts.substring(0, sbDistricts.length()-1);
			
			SelectOptionVO voObject = new SelectOptionVO(districtLevelZeroCadres,"DISTRICT");
			if(districtLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);
			
		}
		if((downLevelCadresFlag) && ("COUNTRY".equals(userAccessType) || "STATE".equals(userAccessType) || "DISTRICT".equals(userAccessType))){
			if(log.isDebugEnabled()){
				log.debug("CadreManagementService.getUserAccessRegions() if DISTRICT started");
			}
			List mandals = cadreDAO.findMandalsByDistrictID(accessID);
			List cadreSizeMandalWise = cadreDAO.findCadreSizeMandalWise(userCadreInfo.getUserID());
			if(cadreSizeMandalWise.size() == 0)
				downLevelCadresFlag = false;
			long mandalLevelZeroCadres = mandals.size() - cadreSizeMandalWise.size();//getZeroSize(cadreSizeZero4Mandal);
			StringBuilder sbMandals = getFormatedData(mandals,userAccessMandals, cadreSizeMandalWise, zeroCadreMandals);
			userCadreInfo.setUserAccessMandals(userAccessMandals);
			userCadreInfo.setZeroCadreMandals(zeroCadreMandals);
			if(log.isDebugEnabled()){
				log.debug("CadreManagementService.getUserAccessRegions() sbMandals:"+sbMandals);
			}
			if(sbMandals!=null && sbMandals.length()>0)
				accessID = sbMandals.substring(0, sbMandals.length()-1);
			
			SelectOptionVO voObject = new SelectOptionVO(mandalLevelZeroCadres,"MANDAL");
			if(mandalLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);
			
		}
		if((downLevelCadresFlag) && ("COUNTRY".equals(userAccessType) || "STATE".equals(userAccessType)
				 || "DISTRICT".equals(userAccessType) || "MANDAL".equals(userAccessType)
				 || "MLA".equals(userAccessType))){
			if(log.isDebugEnabled()){
				log.debug("CadreManagementService.getUserAccessRegions() if MANDAL started");
			}
			List villages = cadreDAO.findVillagesByTehsilID(accessID);
			List cadreSizeVillageWise = cadreDAO.findCadreSizeVillageWise(userCadreInfo.getUserID());
			long villageLevelZeroCadres = villages.size() - cadreSizeVillageWise.size();//getZeroSize(cadreSizeZero4Mandal);
			
			StringBuilder sbVillages = getFormatedData(villages,userAccessVillages,cadreSizeVillageWise,zeroCadreVillages);
			if(log.isDebugEnabled()){
				log.debug("CadreManagementService.getUserAccessRegions() sbVillages:"+sbVillages);
			}
			
			if(sbVillages!=null && sbVillages.length()>0)
				accessID = sbVillages.substring(0, sbVillages.length()-1);
			
			
			userCadreInfo.setUserAccessVillages(userAccessVillages);
			userCadreInfo.setZeroCadreVillages(zeroCadreVillages);
			
			/*if(sbVillages!=null && sbVillages.length()>0)
				accessID = sbVillages.substring(0, sbVillages.length()-1);*/
			
			SelectOptionVO voObject = new SelectOptionVO(villageLevelZeroCadres,"VILLAGE");
			if(villageLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);
		}
		
// New Code started here for Hamlet wise zero cadres......
		if((downLevelCadresFlag) && ("COUNTRY".equals(userAccessType) || "STATE".equals(userAccessType)
				 || "DISTRICT".equals(userAccessType) || "MANDAL".equals(userAccessType) || "VILLAGE".equals(userAccessType)
				 || "MLA".equals(userAccessType))){
			//narender
			List hamlets = cadreDAO.findHamletsByRVs(accessID);
			List cadreSizeHamletWise = cadreDAO.findCadreSizeHamletWise(userCadreInfo.getUserID());
			long hamletLevelZeroCadres = hamlets.size() - cadreSizeHamletWise.size();//getZeroSize(cadreSizeZero4Mandal);
			log.debug("hamletLevelZeroCadres:"+hamletLevelZeroCadres);
			log.debug("hamlets.size():"+hamlets.size());
			log.debug("cadreSizeHamletWise.size():"+cadreSizeHamletWise.size());
			
			StringBuilder sbVillages = getFormatedData(hamlets,userAccessHamlets,cadreSizeHamletWise,zeroCadreHamlets);
			if(log.isDebugEnabled()){
				log.debug("CadreManagementService.getUserAccessRegions() sbVillages:"+sbVillages);
			}			
			
			userCadreInfo.setUserAccessHamlets(userAccessHamlets);
			userCadreInfo.setZeroCadreHamlets(zeroCadreHamlets);
			
			/*if(sbVillages!=null && sbVillages.length()>0)
				accessID = sbVillages.substring(0, sbVillages.length()-1);*/
			
			SelectOptionVO voObject = new SelectOptionVO(hamletLevelZeroCadres,"HAMLET");
			if(hamletLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);			
		}
		log.debug("End off Service....................");
		
		for(SelectOptionVO vo: regionLevelZeroCadres){
			log.debug("Name:"+vo.getName()+" size="+vo.getId());
		}
		userCadreInfo.setRegionLevelZeroCadres(regionLevelZeroCadres);
		return userCadreInfo;
	}
	@SuppressWarnings("unchecked")
	public StringBuilder getFormatedData(List regionData, Map<Long, String> userAccessRegions, 
			List cadreAvailableRegions, Map<Long, String> zeroCadreRegions){
		
		StringBuilder sb = new StringBuilder();
		Map<Long, Object> availableCadreRegions = new HashMap<Long, Object>();
		for(int i=0; i<cadreAvailableRegions.size(); i++){
			Object[] objInfo = (Object[]) cadreAvailableRegions.get(i);
			if(objInfo[0]==null)
				continue;
			Long key = new Long(objInfo[0].toString());
			availableCadreRegions.put(key, objInfo[0]);
		}
		
		
		for(int i=0; i<regionData.size(); i++){
			Object[] objInfo = (Object[]) regionData.get(i);
			Long key = new Long(objInfo[0].toString());
			String value = objInfo[1].toString();
			userAccessRegions.put(key, value);
			if(null==availableCadreRegions.get(key)){
				zeroCadreRegions.put(key, value);				
			}else{
				sb.append(key).append(",");
			}
		}
		
		return sb;
	}
	@SuppressWarnings("unchecked")
	public StringBuilder getMLAFormatedData(List<Tehsil> regionData, Map<Long, String> userAccessRegions, 
			List cadreAvailableRegions, Map<Long, String> zeroCadreRegions){
		
		StringBuilder sb = new StringBuilder();
		Map<Long, Object> availableCadreRegions = new HashMap<Long, Object>();
		for(int i=0; i<cadreAvailableRegions.size(); i++){
			Object[] objInfo = (Object[]) cadreAvailableRegions.get(i);
			Long key = new Long(objInfo[0].toString());
			availableCadreRegions.put(key, objInfo[0]);
		}
		
		for(Tehsil tehsil:regionData){
			Long key = tehsil.getTehsilId();
			String value = tehsil.getTehsilName();
			userAccessRegions.put(key, value);
			if(null==availableCadreRegions.get(key)){
				zeroCadreRegions.put(key, value);				
			}else{
				sb.append(key).append(",");
			}
		}
		return sb;
	}
	@SuppressWarnings("unchecked")
	public StringBuilder getFormatedData(List regionData, Map<Long, String> userAccessRegions){
		//userAccessRegions = new LinkedHashMap<Long, String>();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<regionData.size(); i++){
			Object[] objInfo = (Object[]) regionData.get(i);
			userAccessRegions.put(new Long(objInfo[0].toString()), objInfo[1].toString());
			sb.append(objInfo[0]).append(",");
		}
		return sb;
	}

	@SuppressWarnings("unchecked")
	public Map<String,Long> getCadreLevelCadresCount(Long userID){
		List cadresByRegionList = cadreDAO.findCadresByLevels(userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		Map<String,Long> result = new LinkedHashMap<String,Long>();
		for(int i =0; i<cadresByRegionList.size(); i++){
			Object[] objInfo = (Object[]) cadresByRegionList.get(i);
			result.put(objInfo[0].toString(), new Long(objInfo[1].toString()));
		}
		return result;
	}

	/*@SuppressWarnings("unchecked")
	public UserCadresInfoVO getCadreLevelCadresCount12(UserCadresInfoVO userCadreInfo){
		List cadresByRegionList = cadreDAO.findCadresByLevels(userCadreInfo.getUserID());
		Map<String,Long> tempMap = new LinkedHashMap<String,Long>();
		for(int i =0; i<cadresByRegionList.size(); i++){
			Object[] objInfo = (Object[]) cadresByRegionList.get(i);
			tempMap.put(objInfo[0].toString(), new Long(objInfo[1].toString()));
		}
		userCadreInfo.setRegionLevelCadres(tempMap);
		return userCadreInfo;
	}*/
	
	//Ajax calling methods
	
	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getCountryAllStatesCadres(Long countryID, Long userID){
		List stateCadres = cadreDAO.findStateCadresByCountry(countryID, userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = stateCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for(int i=0; i<size;i++){
			Object[] voObject=(Object[]) stateCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("STATE");
			regionInfoVo.setRegionId(new Long(voObject[0].toString()));
			regionInfoVo.setRegionName(voObject[1].toString());
			regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;
	}
	
	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getStateAllDistrictsCadres(Long stateID, Long userID){
		List districtCadres = cadreDAO.findDistCadresByState(stateID, userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = districtCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for(int i=0; i<size;i++){
			Object[] voObject=(Object[]) districtCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("DISTRICT");
			regionInfoVo.setRegionId(new Long(voObject[0].toString()));
			regionInfoVo.setRegionName(voObject[1].toString());
			regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;
	}
	

	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getDistrictAllMandalsCadres(Long districtID, Long userID){
		List mandalCadres = cadreDAO.findMandalCadresByDist(districtID, userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = mandalCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for(int i=0; i<size;i++){
			Object[] voObject=(Object[]) mandalCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("MANDAL");
			regionInfoVo.setRegionId(new Long(voObject[0].toString()));
			regionInfoVo.setRegionName(voObject[1].toString());
			regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;	
	}
	
	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getConstituencyAllMandalsCadres(Long constituencyID, Long userID){
		List<DelimitationConstituency> delimitationConstituency = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(constituencyID);
		Long delimitationConstituencyID = delimitationConstituency.get(0).getDelimitationConstituencyID();
		List<Tehsil> tehsils = delimitationConstituencyMandalDAO.getTehsilsByDelimitationConstituencyID(delimitationConstituencyID);
		StringBuilder mandalIDs = new StringBuilder();
		for(Tehsil tehsil : tehsils){
			mandalIDs.append(",").append(tehsil.getTehsilId());
		}
		
		System.out.println("mandalIDs.toString():::"+mandalIDs.toString());
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		String strMandalIDs = new String();
		if(mandalIDs.length()>0){
			strMandalIDs = mandalIDs.toString().substring(1);
			List mandalCadres = cadreDAO.findMandalCadresByMandals(strMandalIDs, userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
			int size = mandalCadres.size();
			for(int i=0; i<size;i++){
				Object[] voObject=(Object[]) mandalCadres.get(i);
				CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("MANDAL");
				regionInfoVo.setRegionId(new Long(voObject[0].toString()));
				regionInfoVo.setRegionName(voObject[1].toString());
				regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
				formattedData.add(regionInfoVo);
			}
		}
		return formattedData;	
	}
	
	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getMandalAllVillagesCadres(Long mandalID, Long userID){
		List villageCadres = cadreDAO.findVillageCadresByMandal(mandalID, userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = villageCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for(int i=0; i<size;i++){
			Object[] voObject=(Object[]) villageCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO(voObject[3].toString());// V or T is the value but not VILLAGE
			regionInfoVo.setRegionId(new Long(voObject[0].toString()));
			regionInfoVo.setRegionName(voObject[1].toString());
			regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;	
	}
	
	public List<CadreInfo> getCadresByVillage(Long villageID, Long userID){
		List<CadreInfo> formattedData = new ArrayList<CadreInfo>();
		List<Cadre> cadresList = cadreDAO.findCadresByVillage(villageID, userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		for(Cadre cadre:cadresList){
			CadreInfo cadreInfo = convertCadreToCadreInfo(cadre);
			formattedData.add(cadreInfo);
		}
		return formattedData;
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> findStatesByCountryID(String countryID){
		List states = cadreDAO.findStatesByCountryID(countryID);
		List<SelectOptionVO> stateNames = dataFormatTo_SelectOptionVO(states);
		return stateNames;
	}
	

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> findDistrictsByState(String stateID){
		List districts = cadreDAO.findDistrictsByStateID(stateID);
		List<SelectOptionVO> districtNames = dataFormatTo_SelectOptionVO(districts);
		return districtNames;
	}
	


	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> findMandalsByDistrict(String districtID){
		List mandals = cadreDAO.findMandalsByDistrictID(districtID);
		List<SelectOptionVO> mandalNames = dataFormatTo_SelectOptionVO(mandals);		
		return mandalNames;
	}
	

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> findVillagesByTehsilID(String mandalID){
		//List villages = cadreDAO.findVillagesByTehsilID(mandalID);
		List<Township> townships = townshipDAO.findByTehsilID(new Long(mandalID));
		List<SelectOptionVO> villageNames = getTownshipHamlets(townships);
		//List<SelectOptionVO> villageNames = dataFormatTo_SelectOptionVO(villages);		
		return villageNames;
	}
	
	public String getConstituencyName(Long constituencyID){
		Constituency constituency = constituencyDAO.get(constituencyID);
		String name = "";
		if(constituency!=null)
			name=constituency.getName();
		return name;
	}

	public String getStateName(Long stateID){
		State state = stateDAO.get(stateID);
		String name = "";
		if(state!=null)
			name=state.getStateName();
		return name;
	}
	public String getDistrictName(Long districtID){
		District district = districtDAO.get(districtID);
		String name = "";
		if(district!=null)
			name=district.getDistrictName();
		return name;
	}

	public String getMandalName(Long mandalID){
		Tehsil mandal = tehsilDAO.get(mandalID);
		String name = "";
		if(mandal!=null)
			name=mandal.getTehsilName();
		return name;
	}

	public String getCountryName(Long countryID){
		Country country = countryDAO.get(countryID);
		String name = "";
		if(country!=null)
			name=country.getCountryName();
		return name;
	}
	
	public List<CadreInfo> getCadresByCadreLevel(String cadreLevel, Long userID){
		List<CadreInfo> cadreInfoList = new ArrayList<CadreInfo>();
		List<Cadre> cadresList = cadreDAO.findCadresByCadreLevel(cadreLevel,userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		for(Cadre cadre:cadresList){
			CadreInfo cadreInfo = convertCadreToCadreInfo(cadre);
			cadreInfoList.add(cadreInfo);
		}
		return cadreInfoList;
	}
	
	public CadreInfo convertCadreToCadreInfo(Cadre cadre){
		
		CadreInfo cadreInfo = new CadreInfo();
		cadreInfo.setCadreID(cadre.getCadreId());
		cadreInfo.setFirstName(cadre.getFirstName());
		cadreInfo.setMiddleName(cadre.getMiddleName());
		cadreInfo.setLastName(cadre.getLastName());
		cadreInfo.setGender(cadre.getGender());
		cadreInfo.setMobile(cadre.getMobile());
		cadreInfo.setLandLineNo("-");
		cadreInfo.setEmail(cadre.getEmail());
		String level = cadre.getCadreLevel().getLevel();
		cadreInfo.setCadreLevel(cadre.getCadreLevel().getCadreLevelID());
		cadreInfo.setStrCadreLevel(level);
		String levelValue = "";
		String levelValueID = cadre.getCadreLevelValue().toString();
		if("COUNTRY".equals(level)){
			levelValue = getCountryName(new Long(levelValueID));
		}else if("STATE".equals(level)){
			levelValue = getStateName(new Long(levelValueID));
		}else if("DISTRICT".equals(level)){
			levelValue = getDistrictName(new Long(levelValueID));
		}else if("CONSTITUENCY".equals(level)){
			levelValue = getConstituencyName(new Long(levelValueID));
		}else if("MANDAL".equals(level)){
			levelValue = getMandalName(new Long(levelValueID));
		}else if("VILLAGE".equals(level)){
			log.debug("CadreManagementService.convertCadreToCadreInfo::: levelValueID="+levelValueID);
			String type = levelValueID.substring(0, 1);
			Long id = new Long(levelValueID.substring(1));
			if(IConstants.HAMLET_TYPE.equals(type)){
				levelValue = getHamletName(id);
			}else if(IConstants.TOWNSHIP_TYPE.equals(type)){
				levelValue = getTownshipName(id);
			}
			log.debug("CadreManagementService.convertCadreToCadreInfo::: levelValueID="+levelValueID);
		}
		cadreInfo.setCadreLevelValue(cadre.getCadreLevelValue());
		cadreInfo.setStrCadreLevelValue(levelValue);
		
		return cadreInfo;
	}

	private String getHamletName(Long hamletId){
		Hamlet hamlet = hamletDAO.get(hamletId);
		return hamlet.getHamletName();
	}
	private String getTownshipName(Long townshipId){
		Township township = townshipDAO.get(townshipId);
		return township.getTownshipName();
	}
	public List<SelectOptionVO> getStateDistConstituencyMandalByMandalID(Long mandalID){

		List stateDistConstMandal = delimitationConstituencyMandalDAO.getStateDistConstituencyMandalByMandalID(mandalID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		SelectOptionVO state = new SelectOptionVO();
		SelectOptionVO district = new SelectOptionVO();
		SelectOptionVO constituency = new SelectOptionVO();
		SelectOptionVO mandal = new SelectOptionVO();
		Object[] objVO = (Object[])stateDistConstMandal.get(0);
		
		
		state.setId(new Long(objVO[0].toString()));
		state.setName(objVO[1].toString());

		district.setId(new Long(objVO[2].toString()));
		district.setName(objVO[3].toString());
		
		constituency.setId(new Long(objVO[4].toString()));
		constituency.setName(objVO[5].toString());
		
		mandal.setId(mandalID);
		district.setName(objVO[6].toString());

		result.add(state);
		result.add(district);
		result.add(constituency);
		result.add(mandal);
		
		return result;
	}
	
	public List<SelectOptionVO> getUserAccessStates(Long userID){
		List states = cadreDAO.getUserAccessStates(userID);
		List<SelectOptionVO> results = dataFormatTo_SelectOptionVO(states);
		return results;
	}
	

	public List<SelectOptionVO> getUserAccessDistricts(Long userID){
		List districts = cadreDAO.getUserAccessDistricts(userID);
		List<SelectOptionVO> results =dataFormatTo_SelectOptionVO(districts);
		return results;
	}
	public List<SelectOptionVO> getUserAccessMLAConstituencies(Long userID){
		List constituencies = cadreDAO.getUserAccessMLAConstituencies(userID);
		List<SelectOptionVO> results = dataFormatTo_SelectOptionVO(constituencies);
		return results;
	}
	public List<SelectOptionVO> getUserAccessMandals(Long userID){
		List mandals = cadreDAO.getUserAccessMandals(userID);
		List<SelectOptionVO> results = dataFormatTo_SelectOptionVO(mandals);
		return results;
	}
	
	public List<SelectOptionVO> getStateDistrictByDistrictID(Long districtID){
		List<SelectOptionVO> results = new ArrayList<SelectOptionVO>();
		District district = districtDAO.get(districtID);
		State state = district.getState();
		SelectOptionVO stateObj = new SelectOptionVO(state.getStateId(),state.getStateName());
		SelectOptionVO districtObj = new SelectOptionVO(district.getDistrictId(),district.getDistrictName());
		results.add(stateObj);
		results.add(districtObj);
		return results;
	}
	

	public List<SelectOptionVO> getStateDistricConstituencytByConstituencyID(Long constituencyID){
		Constituency constituency = constituencyDAO.get(constituencyID);
		List<SelectOptionVO> results = new ArrayList<SelectOptionVO>();
		
		State state = constituency.getState();
		District district = constituency.getDistrict(); 
		
		SelectOptionVO stateObj = new SelectOptionVO(state.getStateId(),state.getStateName());
		SelectOptionVO districtObj = new SelectOptionVO(district.getDistrictId(),district.getDistrictName());
		SelectOptionVO constituencyObj = new SelectOptionVO(constituency.getConstituencyId(),constituency.getName());
		results.add(stateObj);
		results.add(districtObj);
		results.add(constituencyObj);
		return results;
	}
	
	
	public SmsResultVO sendSMSMessage(Long userID,String type, Long value, String message, String includeCadreName){
		if(log.isDebugEnabled())
			log.debug("CadreManagementService.sendSMSMEssage():userID:"+userID+":type:"+type+":value:"+value+":message:"+message);
		List<Object> list = new ArrayList<Object>();
	
		if("STATE".equals(type)){
			list = cadreDAO.getMobileNosByState(userID, value); 
		}else if("DISTRICT".equals(type)){
			list = cadreDAO.getMobileNosByDistrict(userID, value);
		}else if("CONSTITUENCY".equals(type)){
			list = cadreDAO.getMobileNosByConstituency(userID, value);
		}else if("MANDAL".equals(type)){
			list = cadreDAO.getMobileNosByMandal(userID, value);
		}else if("VILLAGE".equals(type)){
			String village = value.toString();
			
			if(IConstants.TOWNSHIP_TYPE.equals(village.substring(0, 1))){
				log.debug("TownshipID::::"+new Long(village.substring(1)));
				list = cadreDAO.getMobileNosByVillage(userID, new Long(village.substring(1)));
			} else {
				log.debug("HamletID::::"+new Long(village.substring(1)));
				list = cadreDAO.getMobileNosByHamlet(userID, new Long(village.substring(1)));
			}
		}else if("CADRE_LEVEL".equals(type)){
			list = cadreDAO.getMobileNosByCadreLevel(userID, value);
		}
		Integer mobileNos = 0;
		Long remainingSMS = smsCountrySmsService.getRemainingSmsLeftForUser(userID)-list.size();
		
		SmsResultVO resultVo = new SmsResultVO();
		if(remainingSMS<0){
			resultVo.setStatus(1l);
			resultVo.setTotalSmsSent(0l);
			resultVo.setRemainingSmsCount(0l);
		}else{
			//PartyAnalystPropertyService propertyService = new PartyAnalystPropertyService();
			//smsCountrySmsService.setPropertyService(propertyService);
			if("NO".equals(includeCadreName)){
				String[] cadreMobileNos = new String[list.size()];
				int i=-1;
				for (Object mobileInfo : list) {
					Object[] mobile = (Object[]) mobileInfo;
					cadreMobileNos[++i] = mobile[0].toString();
				}
				if(cadreMobileNos!=null && cadreMobileNos.length>0)
					mobileNos = cadreMobileNos.length;
				smsCountrySmsService.sendSms(message, true,userID,IConstants.Cadre_Management,cadreMobileNos);
			}else{
				// to do ICONSTANTS.SMS_DEAR
				for (Object mobiles : list) {
					Object[] mobileInfo = (Object[]) mobiles;
					String mobile =  mobileInfo[0].toString();
					StringBuilder cadreMessage =  new StringBuilder(IConstants.SMS_DEAR);
					cadreMessage.append(mobileInfo[1].toString()).append(IConstants.SPACE).append(mobileInfo[2].toString()).append(IConstants.SPACE).append(message);

					smsCountrySmsService.sendSms(cadreMessage.toString(), true,userID,IConstants.Cadre_Management,mobile);
					mobileNos = mobileNos + 1;
				}
			}
			resultVo.setStatus(0l);
			resultVo.setTotalSmsSent(Long.parseLong(new Integer(list.size()).toString()));
			resultVo.setRemainingSmsCount(remainingSMS);
		}
		

		return resultVo;
	}
	/**
	 *  retrieving the ids and names for the region(District, Tehsil, Township/Hamlet)
	 * @param userID
	 * @param id
	 * @param region
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> findRegionByCadreScope(Long userID,Long id, String region){
		List list = new ArrayList();
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		if("MANDAL".equals(region)){
			list = cadreDAO.findVillageCadresByMandal(id,userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
			int size = list.size();
			StringBuilder revenueVillageIDs = new StringBuilder();
			for(int i = 0; i<size; i++){
				Object[] obj = (Object[]) list.get(i);
				//Township constists of 2 types: township and revenue village
				if("T".equals(obj[3].toString())){
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId(new Long(IConstants.TOWNSHIP_TYPE+obj[0].toString()));
					selectOptionVO.setName(obj[1].toString());
					result.add(selectOptionVO);
				}else{
					revenueVillageIDs.append(",").append(obj[0].toString());
				}
			}
			// retrieving hamlets from the revenue villages
			if(revenueVillageIDs.length()>0){
				List hamlets = cadreDAO.getCadreSizeByHamlet(revenueVillageIDs.toString().substring(1), userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
				int hamletSize = hamlets.size();
				for(int i = 0; i<hamletSize; i++){
					Object[] obj = (Object[]) hamlets.get(i);
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId(new Long(IConstants.HAMLET_TYPE+obj[0].toString()));
					selectOptionVO.setName(obj[1].toString());
					result.add(selectOptionVO);
				}
			}
			
		} else {
			SelectOptionVO selectOptionVO = new SelectOptionVO(0L,"select District");
			if("STATE".equals(region)){
				list = cadreDAO.findDistCadresByState(id,userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
			}else if("DISTRICT".equals(region)){
				list = cadreDAO.findConstituencyCadresByDist(id,userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
				selectOptionVO.setName("select Constituency");
			}else if("CONSTITUENCY".equals(region)){
				list = cadreDAO.findMandalCadresByConstituency(id,userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
				selectOptionVO.setName("select Tehsil");
			}
			result = dataFormatTo_SelectOptionVO(list);
			result.add(0, selectOptionVO);
		}
		return result;
	}
	
	public List<SelectOptionVO> dataFormatTo_SelectOptionVO(List list){
		List<SelectOptionVO> formattedData = new ArrayList<SelectOptionVO>();
		for(int i=0; i<list.size(); i++){
			Object[] obj = (Object[]) list.get(i);
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(new Long(obj[0].toString()));
			selectOptionVO.setName(obj[1].toString());
			formattedData.add(selectOptionVO);
		}
		return formattedData;
	}
	
	private List<SelectOptionVO> getTownshipHamlets(List<Township> townships){
		log.debug("CadreManagementService.getTownshipHamlets() townships.size()="+townships.size());
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		for(Township township : townships){
			if("V".equalsIgnoreCase(township.getTownshipType())){
				Set<Hamlet> hamlets = township.getHamlets();
				for(Hamlet hamlet : hamlets){
					SelectOptionVO obj = new SelectOptionVO();
					obj.setId(new Long(IConstants.HAMLET_TYPE+hamlet.getHamletId()));
					obj.setName(hamlet.getHamletName());
					result.add(obj);
				}
			}else{
				SelectOptionVO obj = new SelectOptionVO();
				obj.setId(new Long(IConstants.TOWNSHIP_TYPE+township.getTownshipId()));
				obj.setName(township.getTownshipName());
				result.add(obj);
			}
		}
		return result;
	}
	
	public List<CadreInfo> getCadresByHamlet(Long hamletID, Long userID){
		List<CadreInfo> formattedData = new ArrayList<CadreInfo>();
		List<Cadre> cadresList = cadreDAO.findCadresByHamlet(hamletID, userID,IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		for(Cadre cadre:cadresList){
			CadreInfo cadreInfo = convertCadreToCadreInfo(cadre);
			formattedData.add(cadreInfo);
		}
		return formattedData;
	}
	

	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getCadreSizeByHamlet(Long revenueVillageID, Long userID){
		List hamletCadres = cadreDAO.getCadreSizeByHamlet(revenueVillageID.toString(), userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = hamletCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for(int i=0; i<size;i++){
			Object[] voObject=(Object[]) hamletCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("HAMLET");
			regionInfoVo.setRegionId(new Long(voObject[0].toString()));
			regionInfoVo.setRegionName(voObject[1].toString());
			regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;	
	}
	
	public List<StateToHamletVO> getStateToHamletByHamlets(String hamletIDs){
		//0-stateId, 1-stateName, 2-districtId, 3-districtName, 4-tehsilId, 
		//5-tehsilName, 6-townshipId,7-townshipName,8-hamletName,9-hamletName
		List<StateToHamletVO> result = new ArrayList<StateToHamletVO>();
		List list = hamletDAO.getStateToHamletByHamlets(hamletIDs);
		for(int i=0; i<list.size();i++){
			Object[] obj = (Object[])list.get(i);
			SelectOptionVO state 			= new SelectOptionVO((Long)obj[0], obj[1].toString());
			SelectOptionVO district 		= new SelectOptionVO((Long)obj[2], obj[3].toString());
			SelectOptionVO mandal 			= new SelectOptionVO((Long)obj[4], obj[5].toString());
			SelectOptionVO revenueVillage 	= new SelectOptionVO((Long)obj[6], obj[7].toString());
			SelectOptionVO hamlet 			= new SelectOptionVO((Long)obj[8], obj[9].toString());
			
			StateToHamletVO vo = new StateToHamletVO();
			vo.setState(state);
			vo.setDistrict(district);
			vo.setMandal(mandal);
			vo.setRevenueVillage(revenueVillage);
			vo.setHamlet(hamlet);
			result.add(vo);
		}
		return result;
	}
	
	public List<StateToHamletVO> getStateToRevenueVillageByRV(String rvIDs){
		//0-stateId, 1-stateName, 2-districtId, 3-districtName, 4-tehsilId, 
		//5-tehsilName, 6-townshipId,7-townshipName
		List<StateToHamletVO> result = new ArrayList<StateToHamletVO>();
		List list = townshipDAO.getStateToRevenueVillageByRV(rvIDs);
		for(int i=0; i<list.size();i++){
			Object[] obj = (Object[])list.get(i);
			SelectOptionVO state 			= new SelectOptionVO((Long)obj[0], obj[1].toString());
			SelectOptionVO district 		= new SelectOptionVO((Long)obj[2], obj[3].toString());
			SelectOptionVO mandal 			= new SelectOptionVO((Long)obj[4], obj[5].toString());
			SelectOptionVO revenueVillage 	= new SelectOptionVO((Long)obj[6], obj[7].toString());
			
			StateToHamletVO vo = new StateToHamletVO();
			vo.setState(state);
			vo.setDistrict(district);
			vo.setMandal(mandal);
			vo.setRevenueVillage(revenueVillage);
			result.add(vo);
		}
		return result;
	}
	
	public List<StateToHamletVO> getStateToMandalByTehsil(String tehsilIDs){
		//0-stateId, 1-stateName, 2-districtId, 3-districtName, 4-tehsilId, 
		//5-tehsilName
		List<StateToHamletVO> result = new ArrayList<StateToHamletVO>();
		List list = tehsilDAO.getStateToMandalByTehsil(tehsilIDs);
		for(int i=0; i<list.size();i++){
			Object[] obj = (Object[])list.get(i);
			SelectOptionVO state 			= new SelectOptionVO((Long)obj[0], obj[1].toString());
			SelectOptionVO district 		= new SelectOptionVO((Long)obj[2], obj[3].toString());
			SelectOptionVO mandal 			= new SelectOptionVO((Long)obj[4], obj[5].toString());
			
			StateToHamletVO vo = new StateToHamletVO();
			vo.setState(state);
			vo.setDistrict(district);
			vo.setMandal(mandal);
			result.add(vo);
		}
		return result;
	}
	
	public List<StateToHamletVO> getStateToDistrictByDistrict(String districtIDs){
		//0-stateId, 1-stateName, 2-districtId, 3-districtName
		List<StateToHamletVO> result = new ArrayList<StateToHamletVO>();
		List list = districtDAO.getStateToDistrictByDistrict(districtIDs);
		for(int i=0; i<list.size();i++){
			Object[] obj = (Object[])list.get(i);
			SelectOptionVO state 			= new SelectOptionVO((Long)obj[0], obj[1].toString());
			SelectOptionVO district 		= new SelectOptionVO((Long)obj[2], obj[3].toString());
			
			StateToHamletVO vo = new StateToHamletVO();
			vo.setState(state);
			vo.setDistrict(district);
			result.add(vo);
		}
		return result;
	}
	
	public List<SelectOptionVO> getCommitteesForAParty(Long partyId)
	{
		List<SelectOptionVO> resultsList = new ArrayList<SelectOptionVO>();
		List<PartyWorkingCommittee> results =  partyWorkingCommitteeDAO.getWorkingCommitteeForParty(partyId);
		for(PartyWorkingCommittee partyWorkingCommittee:results)
		{
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(partyWorkingCommittee.getPartyWorkingCommitteeId());
			selectOptionVO.setName(partyWorkingCommittee.getCommitteeName());
			resultsList.add(selectOptionVO);
		}
		return resultsList;
	}
	
	public List<SelectOptionVO> getDesignationsInCommittee(Long partyId, Long partyWorkingCommitteeId)
	{
		List<SelectOptionVO> resultsList = new ArrayList<SelectOptionVO>();
		List<PartyWorkingCommitteeDesignation> results =  partyWorkingCommitteeDesignationDAO.getDesignationsForPartyCommittee(partyId, partyWorkingCommitteeId);
		for(PartyWorkingCommitteeDesignation PartyWorkingCommitteeDesignation:results)
		{
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(PartyWorkingCommitteeDesignation.getPartyWkgCommitteeDesignationId());
			selectOptionVO.setName(PartyWorkingCommitteeDesignation.getDesignation());
			resultsList.add(selectOptionVO);
		}
		return resultsList;
	}
	
	public List<SelectOptionVO> getPartyCadreSkills(Long partyId)
	{
		List<PartyCadreSkills> cadreSkills = partyCadreSkillsDAO.getCadreSkillsPartywise(partyId); 
		List<SelectOptionVO> cadreSkillsList = new ArrayList<SelectOptionVO>();
		for(PartyCadreSkills cadreSkill : cadreSkills)
		{
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(cadreSkill.getPartyCadreSkillId());
			selectOptionVO.setName(cadreSkill.getSkill());
			cadreSkillsList.add(selectOptionVO);
		}
		return cadreSkillsList;			
	}
	
	public List<SelectOptionVO> getPartyTrainingCamps(Long partyId)
	{
		List<PartyTrainingCamps> trainingCamps = partyTrainingCampsDAO.getTrainingCampsPartywise(partyId);
		List<SelectOptionVO> trainingCampsList = new ArrayList<SelectOptionVO>();
		for(PartyTrainingCamps partyTrainingCamp:trainingCamps)
		{
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(partyTrainingCamp.getPartyTrainingCampsId());
			selectOptionVO.setName(partyTrainingCamp.getRegionLevel());
			trainingCampsList.add(selectOptionVO);
		}
		return trainingCampsList;
			
	}
	
	/*
	 * Method to check wheather a party has commitee or not
	 * Returns a Boolean value
	 */
	public Boolean checkForAPartyCommiteeDetails(Long partyId){
		
		Boolean result = false;
		List<PartyWorkingCommittee> commiteeList = partyWorkingCommitteeDAO.getWorkingCommitteeForParty(partyId);
		if(commiteeList != null && commiteeList.size() > 0)
			result = true;
	 return result;
	}
	
	/*
	 * Method to get all designations of party working commitee
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getAllDesignationsOfPartyWorkingCommitee(){
		
		List<SelectOptionVO> resultsList = null;
		List designationsList = partyWorkingCommitteeDesignationDAO.getAllDesignationsForPartyCommitee();
		if(designationsList != null && designationsList.size() > 0){
			
			resultsList = new ArrayList<SelectOptionVO>();
			Iterator desigIterator = designationsList.listIterator();
			
			while(desigIterator.hasNext()){
			 Object params = (Object)desigIterator.next();
			 SelectOptionVO value = new SelectOptionVO();
			 value.setName((String)params);
			 
			 resultsList.add(value);
			}
			
		}
	 return resultsList;
	}
	
	/*
	 * Method to Obtain Cadre details based on a search criteria
	 */
	public List<CadreInfo> getCadreDetailsBySearchCriteria(Long userId,PartyCadreDetailsVO cadreInputVO){
		
		if(log.isDebugEnabled())
		log.debug("Inside getCadreDetailsBySearchCriteria Method ");
		
		List<CadreInfo> cadreOutputResultVO = new ArrayList<CadreInfo>();
		ResultStatus resultStatus = new ResultStatus();
		
		if(userId != null && cadreInputVO != null){
			
			try{
				//process for results
				List<Cadre> cadreObjects = getCadreSearchResultsByInputCriteria(userId,cadreInputVO);
				if(cadreObjects != null && cadreObjects.size() > 0)
				cadreOutputResultVO = putCadreSearchResultsInVO(cadreObjects);
				
			}catch(Exception ex){
				ex.printStackTrace();
				CadreInfo cadreInfo = new CadreInfo();
				resultStatus.setExceptionEncountered(ex);
				resultStatus.setResultPartial(true);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				
				cadreInfo.setResultStatus(resultStatus);
				cadreOutputResultVO.add(cadreInfo);
				return cadreOutputResultVO;
			}
			
		}
		
	 return cadreOutputResultVO;	
	}
	
	/*
	 * Method to Obtain Cadre Objects based on a search criteria
	 */
	@SuppressWarnings("unchecked")
	public List<Cadre> getCadreSearchResultsByInputCriteria(Long userId,PartyCadreDetailsVO cadreInputVO) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug("Inside getCadreDetailsBySearchCriteria Method ");
		
		List<Cadre> cadreObjects = null;
		List<Long> cadreIds = null;
		Map<Long,Long> cadreIdsMap = new HashMap<Long,Long>();
		
		if(userId != null && cadreInputVO != null){
			
			//Location Based Or Level Based Search
			if(cadreInputVO.getSearchType().equals(IConstants.LOCATION_BASED)){
				cadreIds = getLocationBasedSearch(userId,cadreInputVO.getCadreLevelId(),cadreInputVO.getCadreLocationId());
			}else if(cadreInputVO.getSearchType().equals(IConstants.LEVEL_BASED)){
				cadreIds = getLevelBasedSearch(userId,cadreInputVO.getCadreLevelId());
			}
			
			//search by CadreType
			if(cadreInputVO.getCadreType().equals(IConstants.CADRE_MEMBER_TYPE_ACTIVE) || cadreInputVO.getCadreType().equals(IConstants.CADRE_MEMBER_TYPE_NORMAL)){
				
				List cadreTypeObjList = null;
				if(cadreIds != null && cadreIds.size() > 0)
				cadreTypeObjList = cadreDAO.findCadreIdsByMemberTypeAndCadreList(cadreInputVO.getCadreType(),cadreIds);
				
				if(cadreTypeObjList != null)
					cadreIds = getProcessedObjects(cadreTypeObjList);
			}
			
			//for Or search
			if(cadreInputVO.getIsOrSearch()){
				if(cadreIds != null && cadreIds.size() > 0)
				cadreIdsMap = setResultCadreIdsToMap(cadreIdsMap,cadreIds);
			}
			
			//search by social skills
			if(cadreInputVO.getIsSocialStatus()){
				
				String paramObject="";
				String paramField="";
				
				//Education
				if(cadreInputVO.getCadreEducationQualification() != null){
					paramObject="education";
					paramField="eduQualificationId";
					List cadreTypeObjList = null;
					List<Long> eduList = new ArrayList<Long>();
					Boolean allCategoriesFlag = false;
					
					for(CadreCategoryVO eduCategory:cadreInputVO.getCadreEducationQualification()){
						if(eduCategory.getCadreCategoryId().equals(new Long(0))){
							allCategoriesFlag = true;
							break;
						}else{
							eduList.add(eduCategory.getCadreCategoryId());
						}
					}
					if(!allCategoriesFlag && eduList.size() > 0){
						if(cadreInputVO.getIsOrSearch()){
							//cadreTypeObjList = cadreDAO.findCadreByPropertyValueAndUser(userId, paramObject,paramField,cadreInputVO.getCadreEducationQualification().getCadreCategoryId());
							cadreTypeObjList = cadreDAO.findCadreByPropertyValueListAndUser(userId, paramObject, paramField, eduList);
							if(cadreTypeObjList != null){
							    List<Long> cadreIDs = getProcessedObjects(cadreTypeObjList);
							    cadreIdsMap = setResultCadreIdsToMap(cadreIdsMap,cadreIDs);
							}
						}
						else if(cadreIds != null && cadreIds.size() > 0){
							//cadreTypeObjList = cadreDAO.findCadreByPropertyValueAndCadreIds(paramObject,paramField,cadreInputVO.getCadreEducationQualification().getCadreCategoryId(),cadreIds);
							cadreTypeObjList = cadreDAO.findCadreByPropertyValueListAndCadreIds(paramObject, paramField, eduList, cadreIds);
							if(cadreTypeObjList != null)
								cadreIds = getProcessedObjects(cadreTypeObjList);
						}
					}
					
				}
				
				//Caste
				if(cadreInputVO.getCadreCasteCategory() != null){
					paramObject="casteCategory";
					paramField="socialCategoryId";
					List cadreTypeObjList = null;
					List<Long> casList = new ArrayList<Long>();
					Boolean allCategoriesFlag = false;
					
					for(CadreCategoryVO casCategory:cadreInputVO.getCadreCasteCategory()){
						if(casCategory.getCadreCategoryId().equals(new Long(0))){
							allCategoriesFlag = true;
							break;
						}else{
							casList.add(casCategory.getCadreCategoryId());
						}
					}
					
					if(!allCategoriesFlag && casList.size() > 0){
						if(cadreInputVO.getIsOrSearch()){
							//cadreTypeObjList = cadreDAO.findCadreByPropertyValueAndUser(userId, paramObject,paramField,cadreInputVO.getCadreCasteCategory().getCadreCategoryId());
							cadreTypeObjList = cadreDAO.findCadreByPropertyValueListAndUser(userId, paramObject, paramField, casList);
							if(cadreTypeObjList != null){
							    List<Long> cadreIDs = getProcessedObjects(cadreTypeObjList);
							    cadreIdsMap = setResultCadreIdsToMap(cadreIdsMap,cadreIDs);
							}
						}
						else if(cadreIds != null && cadreIds.size() > 0){
							//cadreTypeObjList = cadreDAO.findCadreByPropertyValueAndCadreIds(paramObject,paramField,cadreInputVO.getCadreCasteCategory().getCadreCategoryId(),cadreIds);
							cadreTypeObjList = cadreDAO.findCadreByPropertyValueListAndCadreIds(paramObject, paramField, casList, cadreIds);
						if(cadreTypeObjList != null)
							cadreIds = getProcessedObjects(cadreTypeObjList);
						}
					}
				}
				
				//Ocupation
				if(cadreInputVO.getCadreOccupation() != null){
					paramObject="occupation";
					paramField="occupationId";
					List cadreTypeObjList = null;
					List<Long> ocupList = new ArrayList<Long>();
					Boolean allCategoriesFlag = false;
					
					for(CadreCategoryVO ocupCategory:cadreInputVO.getCadreOccupation()){
						if(ocupCategory.getCadreCategoryId().equals(new Long(0))){
							allCategoriesFlag = true;
							break;
						}else{
							ocupList.add(ocupCategory.getCadreCategoryId());
						}
					}
					
					if(!allCategoriesFlag && ocupList.size() > 0){
						if(cadreInputVO.getIsOrSearch()){
							//cadreTypeObjList = cadreDAO.findCadreByPropertyValueAndUser(userId, paramObject,paramField,cadreInputVO.getCadreOccupation().getCadreCategoryId());
							cadreTypeObjList = cadreDAO.findCadreByPropertyValueListAndUser(userId, paramObject, paramField, ocupList);
							if(cadreTypeObjList != null){
							    List<Long> cadreIDs = getProcessedObjects(cadreTypeObjList);
							    cadreIdsMap = setResultCadreIdsToMap(cadreIdsMap,cadreIDs);
							}
						}
						else if(cadreIds != null && cadreIds.size() > 0){
							//cadreTypeObjList = cadreDAO.findCadreByPropertyValueAndCadreIds(paramObject,paramField,cadreInputVO.getCadreOccupation().getCadreCategoryId(),cadreIds);
							cadreTypeObjList = cadreDAO.findCadreByPropertyValueListAndCadreIds(paramObject, paramField, ocupList, cadreIds);
						if(cadreTypeObjList != null)
							cadreIds = getProcessedObjects(cadreTypeObjList);
						}
					}
				}
			}
			
			//Search by committee
			if(cadreInputVO.getCadreWorkingCommittee() != null){
				List cadreTypeObjList = null;
				List<Long> commList = new ArrayList<Long>();
				Boolean allCategoriesFlag = false;
				
				for(CadreCategoryVO commCategory:cadreInputVO.getCadreWorkingCommittee()){
					if(commCategory.getCadreCategoryId().equals(new Long(0))){
						allCategoriesFlag = true;
						break;
					}else{
						commList.add(commCategory.getCadreCategoryId());
					}
				}
				
				if(!allCategoriesFlag && commList.size() > 0){
					if(cadreInputVO.getIsOrSearch()){
						//cadreTypeObjList = cadreDAO.findCadreByPartyWorkingCommitteeAndUser(userId, cadreInputVO.getCadreWorkingCommittee().getCadreCategoryId());
						cadreTypeObjList = cadreDAO.findCadreByPartyWorkingCommitteeListAndUser(userId, commList);
						if(cadreTypeObjList != null){
						    List<Long> cadreIDs = getProcessedObjects(cadreTypeObjList);
						    cadreIdsMap = setResultCadreIdsToMap(cadreIdsMap,cadreIDs);
						}
					}
					else if(cadreIds != null && cadreIds.size() > 0){
						//cadreTypeObjList = cadreDAO.findCadreByPartyWorkingCommitteeAndCadreIds(cadreInputVO.getCadreWorkingCommittee().getCadreCategoryId(), cadreIds);
						cadreTypeObjList = cadreDAO.findCadreByPartyWorkingCommitteeListAndCadreIds(commList, cadreIds);
					if(cadreTypeObjList != null)
						cadreIds = getProcessedObjects(cadreTypeObjList);
				    }
				}
			}
			
			//search by commitee designation
			if(cadreInputVO.getCadreCommitteeDesignation() != null){
				List cadreTypeObjList = null;
				List<Long> commDesigList = new ArrayList<Long>();
				Boolean allCategoriesFlag = false;
				
				for(CadreCategoryVO commDesigCategory:cadreInputVO.getCadreWorkingCommittee()){
					if(commDesigCategory.getCadreCategoryId().equals(new Long(0))){
						allCategoriesFlag = true;
						break;
					}else{
						commDesigList.add(commDesigCategory.getCadreCategoryId());
					}
				}
				
				if(!allCategoriesFlag && commDesigList.size() > 0){
					if(cadreInputVO.getIsOrSearch()){
						//cadreTypeObjList = cadreDAO.findCadreByPartyWorkingCommitteeDesignationAndUser(userId, cadreInputVO.getCadreCommitteeDesignation().getCadreCategoryId());
						cadreTypeObjList = cadreDAO.findCadreByPartyWorkingCommitteeDesignationListAndUser(userId, commDesigList);
						if(cadreTypeObjList != null){
						    List<Long> cadreIDs = getProcessedObjects(cadreTypeObjList);
						    cadreIdsMap = setResultCadreIdsToMap(cadreIdsMap,cadreIDs);
						}
					}
					else if(cadreIds != null && cadreIds.size() > 0){
						//cadreTypeObjList = cadreDAO.findCadreByPartyWorkingCommitteeDesignationAndCadreIds(cadreInputVO.getCadreCommitteeDesignation().getCadreCategoryId(), cadreIds);
						cadreTypeObjList = cadreDAO.findCadreByPartyWorkingCommitteeDesignationListAndCadreIds(commDesigList, cadreIds);
					if(cadreTypeObjList != null)
						cadreIds = getProcessedObjects(cadreTypeObjList);
					}
				}
			}
			
			//search by cadre skills
			if(cadreInputVO.getCadreSkillSet() != null){
				List cadreTypeObjList = null;
				List<Long> skillList = new ArrayList<Long>();
				Boolean allCategoriesFlag = false;
				
				for(CadreCategoryVO  skillCategory:cadreInputVO.getCadreWorkingCommittee()){
					if(skillCategory.getCadreCategoryId().equals(new Long(0))){
						allCategoriesFlag = true;
						break;
					}else{
						skillList.add(skillCategory.getCadreCategoryId());
					}
				}
				
				if(!allCategoriesFlag && skillList.size() > 0){
					if(cadreInputVO.getIsOrSearch()){
						//cadreTypeObjList = cadreSkillsDAO.getCadreIdsByCadreSkillAndUser(userId, cadreInputVO.getCadreSkillSet().getCadreCategoryId());
						cadreTypeObjList = cadreSkillsDAO.getCadreIdsByCadreSkillListAndUser(userId, skillList);
						if(cadreTypeObjList != null){
						    List<Long> cadreIDs = getProcessedObjects(cadreTypeObjList);
						    cadreIdsMap = setResultCadreIdsToMap(cadreIdsMap,cadreIDs);
						}
					}
					else if(cadreIds != null && cadreIds.size() > 0){
						//cadreTypeObjList = cadreSkillsDAO.getCadreBySkillAndCadreIds(cadreInputVO.getCadreSkillSet().getCadreCategoryId(), cadreIds);
						cadreTypeObjList = cadreSkillsDAO.getCadreBySkillListAndCadreIds(skillList, cadreIds);
					if(cadreTypeObjList != null)
						cadreIds = getProcessedObjects(cadreTypeObjList);
					}
				}
			}
			
			//search by cadre training camps
			if(cadreInputVO.getCadreTrainingCamps() != null){
				List cadreTypeObjList = null;
				List<Long> campList = new ArrayList<Long>();
				Boolean allCategoriesFlag = false;
				
				for(CadreCategoryVO  campCategory:cadreInputVO.getCadreWorkingCommittee()){
					if(campCategory.getCadreCategoryId().equals(new Long(0))){
						allCategoriesFlag = true;
						break;
					}else{
						campList.add(campCategory.getCadreCategoryId());
					}
				}
				
				if(!allCategoriesFlag && campList.size() > 0){
					if(cadreInputVO.getIsOrSearch()){
						//cadreTypeObjList = cadreParticipatedTrainingCampsDAO.getCadreIdsByCadreCampsAndUser(userId, cadreInputVO.getCadreTrainingCamps().getCadreCategoryId());
						cadreTypeObjList = cadreParticipatedTrainingCampsDAO.getCadreIdsByCadreCampsListAndUser(userId, campList);
						if(cadreTypeObjList != null){
						    List<Long> cadreIDs = getProcessedObjects(cadreTypeObjList);
						    cadreIdsMap = setResultCadreIdsToMap(cadreIdsMap,cadreIDs);
						}
					}
					else if(cadreIds != null && cadreIds.size() > 0){
						//cadreTypeObjList = cadreParticipatedTrainingCampsDAO.getCadreByCampsAndCadreIds(cadreInputVO.getCadreTrainingCamps().getCadreCategoryId(), cadreIds);
						cadreTypeObjList = cadreParticipatedTrainingCampsDAO.getCadreByCampsListAndCadreIds(campList, cadreIds);
					if(cadreTypeObjList != null)
						cadreIds = getProcessedObjects(cadreTypeObjList);
					}
				}
				
			}
			
			//process cadre ids and obtain cadre objects
			if(cadreInputVO.getIsOrSearch()){
				if(!cadreIdsMap.isEmpty() && cadreIdsMap.size() > 0){
					Set<Long> cadreKeys = cadreIdsMap.keySet();
					cadreObjects = getCadreObjectsFromCadreIdsSet(cadreKeys);
				}
			}
			else if(cadreIds != null && cadreIds.size() > 0){
				cadreObjects = getCadreObjectsFromCadreIds(cadreIds);
			}
				
		}
		
	 return cadreObjects;
	}
	
	/*
	 * Process cadreIds and set to Map
	 */
	public Map<Long,Long> setResultCadreIdsToMap(Map<Long,Long> cadreMap,List<Long> cadreIds){
		
		if(cadreMap != null && cadreIds != null && cadreIds.size() > 0){
			for(Long cadreId:cadreIds){
				if(cadreMap.isEmpty())
					cadreMap.put(cadreId, cadreId);
				else if(!cadreMap.containsKey(cadreId))
					cadreMap.put(cadreId, cadreId);
			}
		}
	 return cadreMap;
	}
	
	/*
	 * Get Cadre Objects From Cadre Ids
	 */
	public List<Cadre> getCadreObjectsFromCadreIds(List<Long> cadreIds){
		
		List<Cadre> cadreObjects = null;
		if(cadreIds != null && cadreIds.size() > 0){
			
			cadreObjects = new ArrayList<Cadre>();
			for(Long cadreId:cadreIds){
				Cadre cadre = cadreDAO.get(cadreId);
				cadreObjects.add(cadre);
			}
		}
	 return cadreObjects;	
	}
	
	/*
	 * Get Cadre Objects From Cadre Ids
	 */
	public List<Cadre> getCadreObjectsFromCadreIdsSet(Set<Long> cadreIds){
		
		List<Cadre> cadreObjects = null;
		if(cadreIds != null && cadreIds.size() > 0){
			
			cadreObjects = new ArrayList<Cadre>();
			for(Long cadreId:cadreIds){
				Cadre cadre = cadreDAO.get(cadreId);
				cadreObjects.add(cadre);
			}
		}
	 return cadreObjects;	
	}
	
	/*
	 * Method To get Location Based Search Results
	 */
	@SuppressWarnings("unchecked")
	public List<Long> getLocationBasedSearch(Long userId,Long levelId,Long locationId){
		
		List<Long> cadreIds = null;
		if(levelId != null && locationId != null){
			
			String ObjectOne="",ObjectTwo="",field="";
			ObjectOne = "currentAddress";
			
			if(levelId.equals(new Long(1))){
				ObjectTwo = "country";
				field = "countryId";
			}else if(levelId.equals(new Long(2))){
				ObjectTwo = "state";
				field = "stateId";
			}else if(levelId.equals(new Long(3))){
				ObjectTwo = "district";
				field = "districtId";
			}else if(levelId.equals(new Long(4))){
				ObjectTwo = "constituency";
				field = "constituencyId";
			}else if(levelId.equals(new Long(5))){
				ObjectTwo = "tehsil";
				field = "tehsilId";
			}else if(levelId.equals(new Long(6))){
				ObjectTwo = "township";
				field = "townshipId";
			}
			
			if(!"".equalsIgnoreCase(ObjectOne) && !"".equalsIgnoreCase(ObjectTwo) && !"".equalsIgnoreCase(field)){
				List cadreObjList = cadreDAO.findCadreDetailsByLevelAndProperty(userId,ObjectOne,ObjectTwo,field,locationId);
				if(cadreObjList != null && cadreObjList.size() > 0)
				cadreIds = getProcessedObjects(cadreObjList);
			}
		}
	 return cadreIds;
	}
	
	/*
	 * Level based Cadre Search
	 */
	@SuppressWarnings("unchecked")
	public List<Long> getLevelBasedSearch(Long userId,Long levelId){
		List<Long> cadreIds = null;
		if(userId != null && levelId != null){
			List cadreObjList = cadreDAO.findCadreByUserAndCadreLevel(userId, levelId);
			if(cadreObjList != null && cadreObjList.size() > 0)
				cadreIds = getProcessedObjects(cadreObjList);
		}
	 return cadreIds;
	}
	
	/*
	 * Process The Objects
	 */
	public List<Long> getProcessedObjects(List objects){
		
		List<Long> cadreIds = null;
		if(objects != null && objects.size() > 0){
			
			cadreIds = new ArrayList<Long>();
			for(int i=0;i<objects.size();i++){
				Object params = (Object)objects.get(i);
				Long cadreId  =(Long)params;
				cadreIds.add(cadreId);
			}
		}
	 return cadreIds;
	}
	
	/*
	 * Method to set Cadre Objects to VO
	 */
	public List<CadreInfo> putCadreSearchResultsInVO(List<Cadre> cadreObjects) throws Exception{
		if(log.isDebugEnabled())
			log.debug("Inside getCadreDetailsBySearchCriteria Method ");
		List<CadreInfo> cadreOutputVO = null;
		
		if(cadreObjects != null && cadreObjects.size() > 0){
			cadreOutputVO = new ArrayList<CadreInfo>();
			for(Cadre cadre:cadreObjects){
				CadreInfo cadreInfoVO = new CadreInfo();
				cadreInfoVO.setCadreID(cadre.getCadreId());
				cadreInfoVO.setEmail(cadre.getEmail());
				cadreInfoVO.setFirstName(cadre.getFirstName());
				cadreInfoVO.setMiddleName(cadre.getMiddleName());
				cadreInfoVO.setLastName(cadre.getLastName());
				cadreInfoVO.setGender(cadre.getGender());
				cadreInfoVO.setMobile(cadre.getMobile());
				cadreInfoVO.setLandLineNo(cadre.getTelephone());
				
				cadreOutputVO.add(cadreInfoVO);
			}
		}
	 return cadreOutputVO;
	}
	
	
	/*
	 * Method to send message to cadre of selected criteria
	 */
	@SuppressWarnings("finally")
	public SmsResultVO sendSMSToSelectedCadreCriteria(Long userId,PartyCadreDetailsVO cadreInputVO,String includeCadreName,String message){
		
		SmsResultVO smsResultVO = new SmsResultVO();
		ResultStatus resultStatus = new ResultStatus();
		Long smsSentStatus = 0l;
		Long smsRemainingStatus = 0l;
		
		try{
		//get cadre matching the selection criteria
		List<CadreInfo> cadreDetails = getCadreDetailsBySearchCriteria(userId,cadreInputVO);
		
		if(cadreDetails == null || cadreDetails.size() == 0){
			smsResultVO.setStatus(1l);
			smsResultVO.setTotalSmsSent(0l);
			smsResultVO.setRemainingSmsCount(0l);
		}
		else if(cadreDetails != null && cadreDetails.size() > 0){
			Integer mobileNos = 0;
			Long remainingSMS = smsCountrySmsService.getRemainingSmsLeftForUser(userId)-cadreDetails.size();
			smsRemainingStatus = remainingSMS;
			if(remainingSMS<0){
				smsResultVO.setStatus(1l);
				smsResultVO.setTotalSmsSent(0l);
				smsResultVO.setRemainingSmsCount(0l);
			}else{
				if("NO".equals(includeCadreName)){
					String[] cadreMobileNos = new String[cadreDetails.size()];
					int i=-1;
					for (CadreInfo mobileInfo : cadreDetails) {
						cadreMobileNos[++i] = mobileInfo.getMobile();
					}
					if(cadreMobileNos!=null && cadreMobileNos.length>0)
						mobileNos = cadreMobileNos.length;
					smsSentStatus = smsCountrySmsService.sendSms(message, true,userId,IConstants.Cadre_Management,cadreMobileNos);
				}else{
					// to do ICONSTANTS.SMS_DEAR
					for (CadreInfo mobiles : cadreDetails) {
						String mobile =  mobiles.getMobile();
						StringBuilder cadreMessage =  new StringBuilder(IConstants.SMS_DEAR);
						cadreMessage.append(IConstants.SPACE).append(mobiles.getFirstName()).append(IConstants.SPACE).append(message);

						smsSentStatus = smsCountrySmsService.sendSms(cadreMessage.toString(), true,userId,IConstants.Cadre_Management,mobile);
						mobileNos = mobileNos + 1;
					}
				}
								
				//sms sent failure
				if(smsSentStatus.equals(1l)){
					smsResultVO.setStatus(1l);
					smsResultVO.setTotalSmsSent(0l);
					smsResultVO.setRemainingSmsCount(smsRemainingStatus);
				}else{
					smsResultVO.setStatus(0l);
					smsResultVO.setTotalSmsSent(Long.parseLong(new Integer(cadreDetails.size()).toString()));
					smsResultVO.setRemainingSmsCount(remainingSMS);
					smsResultVO.setSmsSentCadreInfo(cadreDetails);
				}
			}
			
		}
		
		}catch(Exception ex){
			
			ex.printStackTrace();
			smsResultVO.setStatus(1l);
			smsResultVO.setTotalSmsSent(0l);
			smsResultVO.setRemainingSmsCount(0l);
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			smsResultVO.setResultStatus(resultStatus);
			log.error(ex);
		 return smsResultVO;
		}
		
	 return smsResultVO;	
	}
}
