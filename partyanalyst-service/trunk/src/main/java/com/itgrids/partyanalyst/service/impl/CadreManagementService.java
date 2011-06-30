package com.itgrids.partyanalyst.service.impl;

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
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssignedProblemProgressDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICadreFamilyMemberInfoDAO;
import com.itgrids.partyanalyst.dao.ICadreLanguageEfficiencyDAO;
import com.itgrids.partyanalyst.dao.ICadreLevelDAO;
import com.itgrids.partyanalyst.dao.ICadreParticipatedTrainingCampsDAO;
import com.itgrids.partyanalyst.dao.ICadreProblemDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreRoleDAO;
import com.itgrids.partyanalyst.dao.ICadreRoleRelationDAO;
import com.itgrids.partyanalyst.dao.ICadreSkillsDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILanguageDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPartyCadreSkillsDAO;
import com.itgrids.partyanalyst.dao.IPartyTrainingCampsDAO;
import com.itgrids.partyanalyst.dao.IPartyWorkingCommitteeDAO;
import com.itgrids.partyanalyst.dao.IPartyWorkingCommitteeDesignationDAO;
import com.itgrids.partyanalyst.dao.IProblemActivityDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.ISocialCategoryDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.IUserRelationDAO;
import com.itgrids.partyanalyst.dao.hibernate.AssignedProblemProgressDAO;
import com.itgrids.partyanalyst.dto.CadreCategoryVO;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.CadreRegionInfoVO;
import com.itgrids.partyanalyst.dto.PartyCadreDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SmsResultVO;
import com.itgrids.partyanalyst.dto.SmsVO;
import com.itgrids.partyanalyst.dto.StateToHamletVO;
import com.itgrids.partyanalyst.dto.UserCadresInfoVO;
import com.itgrids.partyanalyst.model.AssignedProblemProgress;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreFamilyMemberInfo;
import com.itgrids.partyanalyst.model.CadreLanguageEfficiency;
import com.itgrids.partyanalyst.model.CadreLevel;
import com.itgrids.partyanalyst.model.CadreParticipatedTrainingCamps;
import com.itgrids.partyanalyst.model.CadreRole;
import com.itgrids.partyanalyst.model.CadreRoleRelation;
import com.itgrids.partyanalyst.model.CadreSkills;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Country;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.Language;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.PartyCadreSkills;
import com.itgrids.partyanalyst.model.PartyTrainingCamps;
import com.itgrids.partyanalyst.model.PartyWorkingCommittee;
import com.itgrids.partyanalyst.model.PartyWorkingCommitteeDesignation;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.Township;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.model.UserRelation;
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
	private IUserRelationDAO userRelationDAO;
	private SmsCountrySmsService smsCountrySmsService;
	private IHamletDAO hamletDAO;
	private IPartyWorkingCommitteeDAO partyWorkingCommitteeDAO;
	private IPartyWorkingCommitteeDesignationDAO partyWorkingCommitteeDesignationDAO;
	private static final Logger log = Logger
			.getLogger(CadreManagementService.class);
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
	private ICadreLevelDAO cadreLevelDAO;
	private String windowTask;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private ICadreFamilyMemberInfoDAO cadreFamilyMemberInfoDAO;
	private IBoothDAO boothDAO;
	private ICadreProblemDetailsDAO cadreProblemDetailsDAO;
	private ICadreRoleDAO cadreRoleDAO;
	private ICadreRoleRelationDAO cadreRoleRelationDAO;
	private IAssignedProblemProgressDAO assignedProblemProgressDAO;
	private IProblemActivityDAO problemActivityDAO;
	
	public IProblemActivityDAO getProblemActivityDAO() {
		return problemActivityDAO;
	}

	public void setProblemActivityDAO(IProblemActivityDAO problemActivityDAO) {
		this.problemActivityDAO = problemActivityDAO;
	}

	public IAssignedProblemProgressDAO getAssignedProblemProgressDAO() {
		return assignedProblemProgressDAO;
	}

	public void setAssignedProblemProgressDAO(
			IAssignedProblemProgressDAO assignedProblemProgressDAO) {
		this.assignedProblemProgressDAO = assignedProblemProgressDAO;
	}

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

	public void setSmsCountrySmsService(
			SmsCountrySmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}

	public IUserRelationDAO getUserRelationDAO() {
		return userRelationDAO;
	}

	public void setUserRelationDAO(IUserRelationDAO userRelationDAO) {
		this.userRelationDAO = userRelationDAO;
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

	public ICadreRoleRelationDAO getCadreRoleRelationDAO() {
		return cadreRoleRelationDAO;
	}

	public void setCadreRoleRelationDAO(ICadreRoleRelationDAO cadreRoleRelationDAO) {
		this.cadreRoleRelationDAO = cadreRoleRelationDAO;
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

	public ICadreProblemDetailsDAO getCadreProblemDetailsDAO() {
		return cadreProblemDetailsDAO;
	}

	public void setCadreProblemDetailsDAO(
			ICadreProblemDetailsDAO cadreProblemDetailsDAO) {
		this.cadreProblemDetailsDAO = cadreProblemDetailsDAO;
	}

	public void setLanguageDAO(ILanguageDAO languageDAO) {
		this.languageDAO = languageDAO;
	}

	public ICadreFamilyMemberInfoDAO getCadreFamilyMemberInfoDAO() {
		return cadreFamilyMemberInfoDAO;
	}

	public void setCadreFamilyMemberInfoDAO(
			ICadreFamilyMemberInfoDAO cadreFamilyMemberInfoDAO) {
		this.cadreFamilyMemberInfoDAO = cadreFamilyMemberInfoDAO;
	}

	public ICadreLanguageEfficiencyDAO getCadreLanguageEfficiencyDAO() {
		return cadreLanguageEfficiencyDAO;
	}

	public void setCadreLanguageEfficiencyDAO(
			ICadreLanguageEfficiencyDAO cadreLanguageEfficiencyDAO) {
		this.cadreLanguageEfficiencyDAO = cadreLanguageEfficiencyDAO;
	}	
	
	public ICadreLevelDAO getCadreLevelDAO() {
		return cadreLevelDAO;
	}

	public void setCadreLevelDAO(ICadreLevelDAO cadreLevelDAO) {
		this.cadreLevelDAO = cadreLevelDAO;
	}	
	
	public String getWindowTask() {
		return windowTask;
	}

	public void setWindowTask(String windowTask) {
		this.windowTask = windowTask;
	}	

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}	

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	
	public ICadreRoleDAO getCadreRoleDAO() {
		return cadreRoleDAO;
	}

	public void setCadreRoleDAO(ICadreRoleDAO cadreRoleDAO) {
		this.cadreRoleDAO = cadreRoleDAO;
	}

	public ResultStatus saveCader(CadreInfo cadreInfoToSave, List<Long> skills, String task) {
		
		final ResultStatus rs= new ResultStatus();
		if (log.isDebugEnabled()) {
			log.debug("cadrerManagementService.saveCadre() method");
		}
		
		try{
			
			List<String> cadreLang_Eng = cadreInfoToSave.getLanguageOptions_English();
			List<String> cadreLang_Hin = cadreInfoToSave.getLanguageOptions_Hindi();
			
			Cadre cadreObj = saveCadreInTransaction(cadreInfoToSave,task);
			log.debug("cadreObj id"+cadreObj.getCadreId());
			if (cadreObj != null)
			{
				log.debug("inside cadre obj block");
				log.debug(" ... " + cadreInfoToSave.getLanguageOptions_English());
				setLanguageEfficiency(cadreObj,cadreLang_Eng, IConstants.LANGUAGE_ENGLISH, task);
				setLanguageEfficiency(cadreObj,cadreLang_Hin, IConstants.LANGUAGE_HINDI, task);
				
				setCadreRolesInfo(cadreObj, cadreInfoToSave.getCadreRoles());
				
				if (IConstants.USER_TYPE_PARTY.equals(cadreInfoToSave.getUserType()) && IConstants.BJP.equalsIgnoreCase(cadreInfoToSave.getUserPartyName())) {
					log.debug("inside bjp party block");
					if(cadreInfoToSave.getSkills() != null && cadreInfoToSave.getSkills().size() > 0)
						setCadreSkillsInfo(cadreObj, cadreInfoToSave.getSkills(), task);
					if(cadreInfoToSave.getTrainingCamps() != null && cadreInfoToSave.getTrainingCamps().size() > 0)
						setParticipatedTrainingCamps(cadreObj, cadreInfoToSave.getTrainingCamps(), task);
				}
			}
			rs.setResultCode(ResultCodeMapper.SUCCESS);
			rs.setResultState(cadreObj.getCadreId());
		}catch(Exception e){
			log.debug(e);
			rs.setExceptionEncountered(e);
			rs.setExceptionClass(e.getClass().toString());
			rs.setExceptionMsg(getExceptionMessage(e.getClass().toString()));
			rs.setResultCode(ResultCodeMapper.FAILURE);
			rs.setResultPartial(true);
			
		}	
		
		return rs;
	}
		
	public Cadre saveCadreInTransaction (final CadreInfo cadreInfo,final String task) 
	{
		Cadre cadreObj = (Cadre) transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {

			UserAddress currentAddress = new UserAddress();
			UserAddress permanentAddress = new UserAddress();
			Cadre cadre = null;
			//used for current address
			Long assemblyLocalElectionBodyId1 = null;
			//used for official address
			Long assemblyLocalElectionBodyId2 = null;
			// used for cadre level
			Long assemblyLocalElectionBodyId3 = null;
			try {
				
				if(!("0".equals(cadreInfo.getCadreId())) && IConstants.UPDATE_EXISTING.equals(task))
				{								
					cadre = cadreDAO.get(new Long(cadreInfo.getCadreId()));
				} else{
					cadre = new Cadre();
				}							
				cadre.setFirstName(cadreInfo.getFirstName());
				cadre.setMiddleName(cadreInfo.getMiddleName());
				cadre.setLastName(cadreInfo.getLastName());
				cadre.setGender(cadreInfo.getGender());
				//cadre.setState(stateDAO.get(new Long(cadreInfo.getState())));
				//cadre.setDistrict(districtDAO.get(new Long(cadreInfo.getDistrict())));
				//cadre.setConstituency(constituencyDAO.get(new Long(cadreInfo.getConstituencyID())));
				cadre.setFatherOrSpouseName(cadreInfo.getFatherOrSpouseName());
				cadre.setNoOfFamilyMembers(cadreInfo.getNoOfFamilyMembers());
				cadre.setNoOfVoters(cadreInfo.getNoOfVoters());
				
				// setting address
				currentAddress.setHouseNo(cadreInfo.getHouseNo());
				currentAddress.setStreet(cadreInfo.getStreet());
				currentAddress.setPinCode(cadreInfo.getPinCode());
				currentAddress.setState(stateDAO.get(new Long(cadreInfo.getState())));
				if("MP".equals(cadreInfo.getAccessType()))
				{
					currentAddress.setParliamentConstituency(constituencyDAO.get(new Long(cadreInfo.getParliament())));
					currentAddress.setDistrict(null);					
				} else {
					currentAddress.setParliamentConstituency(null);
					currentAddress.setDistrict(districtDAO.get(new Long(cadreInfo.getDistrict())));
				}
				
				currentAddress.setConstituency(constituencyDAO.get(cadreInfo.getConstituencyID()));
				
				if (IConstants.URBAN_TYPE.equals(cadreInfo.getMandal().substring(0,1)))
				{
					assemblyLocalElectionBodyId1 = new Long(cadreInfo.getMandal().substring(1));
					List localElectionBodies = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(assemblyLocalElectionBodyId1);
					Object object = (Object)localElectionBodies.get(0);
					Long localElectionBodyId = (Long)object;
					currentAddress.setLocalElectionBody(localElectionBodyDAO.get(localElectionBodyId));
					currentAddress.setWard(constituencyDAO.get(new Long(cadreInfo.getVillage().substring(1))));
					currentAddress.setTehsil(null);
					currentAddress.setHamlet(null);
					//cadre.setTehsil(null);
				}
				
				if (IConstants.RURAL_TYPE.equals(cadreInfo.getMandal().substring(0,1)))
				{
					currentAddress.setTehsil(tehsilDAO.get(new Long(cadreInfo.getMandal().substring(1))));
					
					Boolean isHamlet = checkForHamlet(new Long(cadreInfo.getMandal().substring(1)),new Long(cadreInfo.getVillage().substring(1)));
					
					//if location details are hamlet
					if(isHamlet)
					    currentAddress.setHamlet(hamletDAO.get(new Long(cadreInfo.getVillage().substring(1))));
					//if location details are township
					else
						currentAddress.setTownship(townshipDAO.get(new Long(cadreInfo.getVillage().substring(1))));
					
					currentAddress.setLocalElectionBody(null);
					currentAddress.setWard(null);
					//cadre.setTehsil(tehsilDAO.get(new Long(cadreInfo.getMandal().substring(1))));
				}
				if(cadreInfo.getBooth() != null && !cadreInfo.getBooth().equals("0"))
				{
					currentAddress.setBooth(boothDAO.get(new Long(cadreInfo.getBooth())));
				}
			
				/*currentAddress.setTehsil(tehsilDAO.get(new Long(cadreInfo.getMandal())));
				String villageFlag = "";
				Long id = 0l;
				villageFlag = cadreInfo.getVillage().substring(0, 1);
				id = new Long(cadreInfo.getVillage().substring(1));
				processAddressValues(villageFlag, id, cadre,currentAddress);*/
				
				if (log.isDebugEnabled())
					log.debug("sameas ca::::::::::::::::::::::"+cadreInfo.getSameAsCA());
				if (cadreInfo.getSameAsCA() == true) {
					permanentAddress = currentAddress;
				} else {
					if (log.isDebugEnabled())
						log.debug("inside permanent address block");
					
					/*villageFlag = cadreInfo.getVillage().substring(0, 1);
					id = new Long(cadreInfo.getVillage().substring(1));*/
					
					permanentAddress.setHouseNo(cadreInfo.getPhouseNo());
					permanentAddress.setStreet(cadreInfo.getPstreet());
					permanentAddress.setPinCode(cadreInfo.getPpinCode());
					permanentAddress.setState(stateDAO.get(new Long(cadreInfo.getPstate())));
					if("MP".equals(cadreInfo.getAccessType()))
					{
						permanentAddress.setParliamentConstituency(constituencyDAO.get(new Long(cadreInfo.getPParliament())));
						permanentAddress.setDistrict(null);					
					} else {
						permanentAddress.setParliamentConstituency(null);
						permanentAddress.setDistrict(districtDAO.get(new Long(cadreInfo.getPdistrict())));
					}					
					permanentAddress.setConstituency(constituencyDAO.get(cadreInfo.getPconstituencyID()));
					
					if (IConstants.URBAN_TYPE.equals(cadreInfo.getPmandal().substring(0,1)))
					{
						assemblyLocalElectionBodyId2 = new Long(cadreInfo.getPmandal().substring(1));
						List localElectionBodyIds = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(assemblyLocalElectionBodyId2);
						Object object = (Object)localElectionBodyIds.get(0);
						Long offLocalElectionBodyId = (Long)object;
						permanentAddress.setLocalElectionBody(localElectionBodyDAO.get(offLocalElectionBodyId));
						permanentAddress.setWard(constituencyDAO.get(new Long(cadreInfo.getPvillage().substring(1))));
						permanentAddress.setTehsil(null);
						permanentAddress.setHamlet(null);
					}
					
					if (IConstants.RURAL_TYPE.equals(cadreInfo.getPmandal().substring(0,1)))
					{
						permanentAddress.setTehsil(tehsilDAO.get(new Long(cadreInfo.getPmandal().substring(1))));
						
						
						Boolean isHamlet = checkForHamlet(new Long(cadreInfo.getMandal().substring(1)),new Long(cadreInfo.getVillage().substring(1)));
						
						//if location details are hamlet
						if(isHamlet)
							permanentAddress.setHamlet(hamletDAO.get(new Long(cadreInfo.getPvillage().substring(1))));
						//if location details are township
						else
							permanentAddress.setTownship(townshipDAO.get(new Long(cadreInfo.getVillage().substring(1))));
						
						permanentAddress.setLocalElectionBody(null);
						permanentAddress.setWard(null);
					}
					if(!cadreInfo.getPBooth().equals("0"))
					{
						permanentAddress.setBooth(boothDAO.get(new Long(cadreInfo.getPBooth())));
					}					
				} 
				cadre.setCurrentAddress(currentAddress);
				cadre.setPermanentAddress(permanentAddress);
				cadre.setMobile(cadreInfo.getMobile());
				cadre.setEmail(cadreInfo.getEmail());
				cadre.setRegistration(registrationDAO.get(cadreInfo.getUserID()));
				SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_PATTERN);
				if (cadreInfo.getDobOption() != null && "Date Of Birth".equals(cadreInfo.getDobOption())) {
					cadre.setDateOfBirth(format.parse(cadreInfo.getDateOfBirth()));
					cadre.setExactDateOfBirth("true");
				} else if (cadreInfo.getDobOption() != null && "Age".equals(cadreInfo.getDobOption())) {
					Calendar cal = Calendar.getInstance();
					Date todaysDate = new Date();
					cal.setTime((todaysDate));
					Integer age = new Integer(cadreInfo.getAge());
					cal.add(Calendar.YEAR, -age);
					cadre.setDateOfBirth(cal.getTime());
					cadre.setExactDateOfBirth("false");
				}
				cadre.setTelephone(cadreInfo.getTelephone());
				if (!cadreInfo.getEducation().equals(new Long(0)))
					cadre.setEducation(educationalQualificationsDAO.get(cadreInfo.getEducation()));
				if (!cadreInfo.getProfession().equals(new Long(0)))
					cadre.setOccupation(occupationDAO.get(cadreInfo.getProfession()));
				if (cadreInfo.getSocialStatus() != null)
					cadre.setCasteCategory(socialCategoryDAO.get(cadreInfo.getSocialStatus()));

				cadre.setMemberType(cadreInfo.getMemberType());
				Double annunaIncome = 0d;
				if (cadreInfo.getIncome() != null && (!StringUtils.isBlank(cadreInfo.getIncome())))
					annunaIncome = new Double(cadreInfo.getIncome());

				cadre.setAnnualIncome(annunaIncome);

				if (IConstants.CADRE_MEMBER_TYPE_ACTIVE.equals(cadreInfo.getMemberType())) 
				{
					CadreLevel level = new CadreLevel();
					level.setCadreLevelID(cadreInfo.getCadreLevel());
					String[] values = { "", "COUNTRY", "STATE","DISTRICT", "CONSTITUENCY", "MANDAL","VILLAGE","MUNICIPAL-CORP-GMC","WARD","BOOTH" };
					level.setLevel(values[cadreInfo.getCadreLevel().intValue()]);
					cadre.setCadreLevel(level);
					//if (!StringUtils.isBlank(cadreInfo.getCadreLevelValue()))
					if("MANDAL".equalsIgnoreCase(level.getLevel()) || "VILLAGE".equalsIgnoreCase(level.getLevel()))
					{
						cadre.setCadreLevelValue(new Long(cadreInfo.getStrCadreLevelValue().substring(1)));
					} else if ("MUNICIPAL-CORP-GMC".equalsIgnoreCase(level.getLevel()))
					{
						assemblyLocalElectionBodyId3 = new Long(cadreInfo.getStrCadreLevelValue().substring(1));
						List localElectionBodyIdsList = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(assemblyLocalElectionBodyId3);
						Object object = (Object)localElectionBodyIdsList.get(0);
						Long cadreLevelLocalElectionBodyId = (Long)object;
						cadre.setCadreLevelValue(cadreLevelLocalElectionBodyId);
					} else{
						cadre.setCadreLevelValue(new Long(cadreInfo.getStrCadreLevelValue()));
					}						
					
					if (IConstants.USER_TYPE_PARTY.equals(cadreInfo.getUserType())&& IConstants.BJP.equalsIgnoreCase(cadreInfo.getUserPartyName())) {
						if (!cadreInfo.getDesignation().equals(0l))
							cadre.setDesignation(partyWorkingCommitteeDesignationDAO.get(new Long(cadreInfo.getDesignation())));
						if (!StringUtils.isBlank(cadreInfo.getEffectiveDate()))
							cadre.setEffectiveDate(format.parse(cadreInfo.getEffectiveDate()));
						if (!StringUtils.isBlank(cadreInfo.getEndingDate()))
							cadre.setEndingDate(format.parse(cadreInfo.getEndingDate()));
					}						
				} else if(IConstants.CADRE_MEMBER_TYPE_NORMAL.equals(cadreInfo.getMemberType()))
				{
					cadre.setCadreLevel(null);
					cadre.setCadreLevelValue(null);
					cadre.setDesignation(null);
					cadre.setEffectiveDate(null);
					cadre.setEndingDate(null);
				}
				

			} catch (Exception e) {
				status.setRollbackOnly();
				log.debug("Exception Raised while Update And Get Problems Under Pending::",e);
				e.printStackTrace();
				}
				
				if(IConstants.UPDATE_EXISTING.equals(task))
					cadre.setImage(cadre.getImage());
				
				cadre = cadreDAO.save(cadre);
					
					try
					{
						SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_PATTERN);
					
						if(cadreInfo.getFirstFamilyMemberName().trim() != null && cadreInfo.getFirstFamilyMemberName().trim().length() >0)
						{
							CadreFamilyMemberInfo cadreFamilyMemberInfo = new CadreFamilyMemberInfo();
							cadreFamilyMemberInfo.setCadre(cadre);
							cadreFamilyMemberInfo.setName(cadreInfo.getFirstFamilyMemberName().trim());
							cadreFamilyMemberInfo.setUserRelation(userRelationDAO.get(new Long(cadreInfo.getFirstFamilyMemberRelationId())));
																		
							if(cadreInfo.getFirstFamilyMemberDOB() != null && cadreInfo.getFirstFamilyMemberDOB().length() != 0)
							cadreFamilyMemberInfo.setDateOfBirth((format.parse(cadreInfo.getFirstFamilyMemberDOB())));
							cadreFamilyMemberInfoDAO.save(cadreFamilyMemberInfo);
						}
						
						if(cadreInfo.getSecondFamilyMemberName().trim() != null && cadreInfo.getSecondFamilyMemberName().trim().length() >0)
						{
							CadreFamilyMemberInfo cadreFamilyMemberInfo = new CadreFamilyMemberInfo();
							cadreFamilyMemberInfo.setCadre(cadre);
							cadreFamilyMemberInfo.setName(cadreInfo.getSecondFamilyMemberName().trim());
							cadreFamilyMemberInfo.setUserRelation(userRelationDAO.get(new Long(cadreInfo.getSecondFamilyMemberRelationId())));
																		
							if(cadreInfo.getSecondFamilyMemberDOB() != null && cadreInfo.getSecondFamilyMemberDOB().length() != 0)
							cadreFamilyMemberInfo.setDateOfBirth((format.parse(cadreInfo.getSecondFamilyMemberDOB())));
							cadreFamilyMemberInfoDAO.save(cadreFamilyMemberInfo);
						}
						
						if(cadreInfo.getThirdFamilyMemberName().trim() != null && cadreInfo.getThirdFamilyMemberName().trim().length() >0)
						{
							CadreFamilyMemberInfo cadreFamilyMemberInfo = new CadreFamilyMemberInfo();
							cadreFamilyMemberInfo.setCadre(cadre);
							cadreFamilyMemberInfo.setName(cadreInfo.getThirdFamilyMemberName().trim());
							cadreFamilyMemberInfo.setUserRelation(userRelationDAO.get(new Long(cadreInfo.getThirdFamilyMemberRelationId())));
																		
							if(cadreInfo.getThirdFamilyMemberDOB()!= null && cadreInfo.getThirdFamilyMemberDOB().length() != 0)
							cadreFamilyMemberInfo.setDateOfBirth((format.parse(cadreInfo.getThirdFamilyMemberDOB())));
							cadreFamilyMemberInfoDAO.save(cadreFamilyMemberInfo);
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				return cadre;
			} });
		return cadreObj;
	}
	
	
	@SuppressWarnings("unchecked")
	private Boolean checkForHamlet(Long tehsilId,Long hamletId){
		
		List hamletData = hamletDAO.findByTehsilIdAndHamletId(hamletId, tehsilId);
		
		if(hamletData != null && hamletData.size() > 0)
			return true;
		
	 return false;
	}
	
	private void setCadreSkillsInfo(final Cadre cadreObj, final List<Long> skills, String task) {
		log.debug("inside cadre skills block");
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
			
				try{
					log.debug("skills inside cadreskills info"+skills.size());
					List<CadreSkills> cadreSkills = cadreSkillsDAO.findByCadreId(cadreObj.getCadreId());
					if(cadreSkills != null && cadreSkills.size()>0)
					{
						log.debug("existing skills for this cadre:"+cadreSkills.size());
						Integer rows = cadreSkillsDAO.deleteSkillsByCadreId(cadreObj.getCadreId());	
						log.debug("No of rows deleted:"+rows);
					}
					
					log.debug("skills " + skills);
					if(skills != null){
						log.debug(" skills Options :" + skills.size());
					}
					if(skills != null && skills.size() >0)
					{	
						for (int i = 0; i < skills.size(); i++) {
							CadreSkills cadreSkill = new CadreSkills();
							PartyCadreSkills partyCadreSkill = partyCadreSkillsDAO.get(skills.get(i));
							cadreSkill.setPartyCadreSkills(partyCadreSkill);
							cadreSkill.setCadre(cadreObj);
							cadreSkillsDAO.save(cadreSkill);
						}
					}
				} catch(Exception e){
					status.setRollbackOnly();
					log.debug(e);
					if(log.isDebugEnabled()){
						log.debug("Exception Raised while setCadreSkillsInfo() method::", e);
					}					
					e.printStackTrace();
				}				
			}
		});					
	}
	
	private void setCadreRolesInfo(final Cadre cadre, final List<Long> cadreRoles) {
		log.debug("inside setCadreRolesInfo method ");
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
			
				try{
					cadreRoleRelationDAO.deleteRolesByCadreId(cadre.getCadreId());
					
					if(cadreRoles != null && cadreRoles.size() > 0)
					{
						for(Long role:cadreRoles)
						{
							CadreRoleRelation cadreRoleRelation = new CadreRoleRelation();
							CadreRole cadreRole = cadreRoleDAO.get(role);
							cadreRoleRelation.setCadreRole(cadreRole);
							cadreRoleRelation.setCadre(cadre);
							cadreRoleRelationDAO.save(cadreRoleRelation);
						}
					}
					
				} catch(Exception e){
					status.setRollbackOnly();
					log.debug(e);
					if(log.isDebugEnabled()){
						log.debug("Exception Raised while setCadreRolesInfo() method::", e);
					}					
					e.printStackTrace();
				}				
			}
		});					
	}
	

	private List<SelectOptionVO> getRolesByCadreId(Long cadreId)
	{
		if(log.isDebugEnabled()){
			log.debug(" Entered into getRolesByCadreId() method with cadre Id-"+cadreId);
		}
		try{
			List<Object[]> rolesList = cadreRoleRelationDAO.getRolesByCadreId(cadreId);
			List<SelectOptionVO> voList = null;
			if(rolesList != null && rolesList.size() > 0)
			{
				voList = new ArrayList<SelectOptionVO>(0);
				SelectOptionVO selectOptionVO = null;
				for(Object[] params:rolesList)
				{
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)params[0]);
					selectOptionVO.setName(params[1].toString());
					voList.add(selectOptionVO);
				}
			}
			return voList;
		}catch(Exception ex)
		{
			log.debug("Exception Raised in getRolesByCadreId() method::", ex);
			return null;
		}
	}

	private void setParticipatedTrainingCamps(final Cadre cadreObj, final List<Long> trainingCamps, String task) {
		log.debug("inside setParticipatedTrainingCamps block");
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
			try{
				List<CadreParticipatedTrainingCamps> trainingCampsList = cadreParticipatedTrainingCampsDAO.findByCadreId(cadreObj.getCadreId());
				if(trainingCampsList != null && trainingCampsList.size()>0)
				{
					log.debug("existing camps for this cadre:"+trainingCampsList.size());
					Integer rows = cadreParticipatedTrainingCampsDAO.deleteCadreTrainingCamps(cadreObj.getCadreId());	
					log.debug("No of rows deleted:"+rows);
				}
				
				log.debug("Options " + trainingCamps);
				if(trainingCamps != null){
					log.debug(" Language Options :" + trainingCamps.size());
				}
				if(trainingCamps != null && trainingCamps.size() > 0)
				{		
					for (int j = 0; j < trainingCamps.size(); j++) {
						CadreParticipatedTrainingCamps cadreParticipatedTrainingCamp = new CadreParticipatedTrainingCamps();
						PartyTrainingCamps partyTrainingCamp = partyTrainingCampsDAO.get(trainingCamps.get(j));
						cadreParticipatedTrainingCamp.setPartyTrainingCamps(partyTrainingCamp);
						cadreParticipatedTrainingCamp.setCadre(cadreObj);
						cadreParticipatedTrainingCampsDAO.save(cadreParticipatedTrainingCamp);
						}
				}
				}catch(Exception e){
					status.setRollbackOnly();
					log.debug(e);
					if(log.isDebugEnabled()){
						log.debug("Exception Raised in setParticipatedTrainingCamps method::", e);
					}					
					e.printStackTrace();
				}
	}
		});	
	}	

	private void setLanguageEfficiency(final Cadre cadreObj, final List<String> options, final String language, String task) {
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
		public void doInTransactionWithoutResult(TransactionStatus status) {
			try{
				List<Language> languages = languageDAO.findByLanguage(language);
				Language languageObj = languages.get(0);
				CadreLanguageEfficiency cadreLanguageEfficiency = new CadreLanguageEfficiency();
				List<CadreLanguageEfficiency> cadreLanguages = cadreLanguageEfficiencyDAO.findByCadreIdandLanguage(cadreObj.getCadreId(), language);
				if(cadreLanguages != null && cadreLanguages.size() == 1)
				{	
					log.debug("languages size:"+cadreLanguages.size());
					Integer rows = cadreLanguageEfficiencyDAO.deleteLanguageDetailsByCadre(cadreObj.getCadreId(), languageObj.getLanguageId());
					log.debug("Rows Affected:"+rows);
				} 
				
				log.debug("Options " + options);
				if(options != null){
					log.debug(" Language Options :" + options.size());
				}
				/*if(options!= null && options.length >0)
				{
					cadreLanguageEfficiency.setCadre(cadreObj);
					cadreLanguageEfficiency.setLanguage(languageObj);
					for (int i = 0; i < options.length; i++) {
					if (IConstants.SPEAK_LANGUAGE.equals(options[i]))
						cadreLanguageEfficiency.setIsAbleToSpeak(IConstants.TRUE);
					if (IConstants.READ_LANGUAGE.equals(options[i]))
						cadreLanguageEfficiency.setIsAbleToRead(IConstants.TRUE);
					if (IConstants.WRITE_LANGUAGE.equals(options[i]))
						cadreLanguageEfficiency.setIsAbleToWrite(IConstants.TRUE);
					}
					cadreLanguageEfficiencyDAO.save(cadreLanguageEfficiency);
				}*/
				if(options != null && options.size() >0)
				{
					log.debug("Setting language options");
					cadreLanguageEfficiency.setCadre(cadreObj);
					cadreLanguageEfficiency.setLanguage(languageObj);
					for (int i = 0; i < options.size(); i++) {
					if (IConstants.SPEAK_LANGUAGE.equals(options.get(i)))
						cadreLanguageEfficiency.setIsAbleToSpeak(IConstants.TRUE);
					if (IConstants.READ_LANGUAGE.equals(options.get(i)))
						cadreLanguageEfficiency.setIsAbleToRead(IConstants.TRUE);
					if (IConstants.WRITE_LANGUAGE.equals(options.get(i)))
						cadreLanguageEfficiency.setIsAbleToWrite(IConstants.TRUE);
					}
					cadreLanguageEfficiencyDAO.save(cadreLanguageEfficiency);
					log.debug("after dao call");
				}
				
			}catch(Exception e){
				//status.setRollbackOnly();
				log.debug(e);
				if(log.isDebugEnabled()){
					log.debug("Exception Raised in setLanguageEfficiencies method::", e);
				}					
				e.printStackTrace();
				}
			}			
	});
				
			}

	public Integer deleteCadre(final Long cadreId, RegistrationVO user) {
		
		Cadre cadre = cadreDAO.get(cadreId);
		if(IConstants.CADRE_MEMBER_TYPE_ACTIVE.equals(cadre.getMemberType()) && IConstants.USER_TYPE_PARTY.equals(user.getUserType()) && IConstants.BJP.equals(user.getPartyShortName()))
		{	
			List<CadreSkills> cadreSkills = cadreSkillsDAO.findByCadreId(cadreId);
			if(cadreSkills != null && cadreSkills.size()>0)
			{
				Integer rows = cadreSkillsDAO.deleteSkillsByCadreId(cadreId);				
			}
			List<CadreParticipatedTrainingCamps> trainingCampsList = cadreParticipatedTrainingCampsDAO.findByCadreId(cadreId);
			if(trainingCampsList != null && trainingCampsList.size()>0)
			{
				Integer rows = cadreParticipatedTrainingCampsDAO.deleteCadreTrainingCamps(cadreId);	
			}
		}
		List<CadreLanguageEfficiency> engLanguagesEff = cadreLanguageEfficiencyDAO.findByCadreIdandLanguage(cadreId, IConstants.LANGUAGE_ENGLISH);
		List<CadreLanguageEfficiency> hinLanguagesEff = cadreLanguageEfficiencyDAO.findByCadreIdandLanguage(cadreId, IConstants.LANGUAGE_HINDI);
		List<Language> engLanguages = languageDAO.findByLanguage(IConstants.LANGUAGE_ENGLISH);
		List<Language> hinLanguages = languageDAO.findByLanguage(IConstants.LANGUAGE_HINDI);
		Language engLanguageObj = engLanguages.get(0);
		Language hinLanguageObj = hinLanguages.get(0);
		if(engLanguagesEff != null && engLanguages.size() == 1)
		{	
			Integer rows = cadreLanguageEfficiencyDAO.deleteLanguageDetailsByCadre(cadreId, engLanguageObj.getLanguageId());			
		}
		if(hinLanguagesEff != null && hinLanguages.size() == 1)
		{	
			Integer rows = cadreLanguageEfficiencyDAO.deleteLanguageDetailsByCadre(cadreId, hinLanguageObj.getLanguageId());			
		}
		List<Object[]> familyMbrs = cadreFamilyMemberInfoDAO.findByCadreId(cadreId);
		if(familyMbrs.size()>0)
		{
			Integer rows = cadreFamilyMemberInfoDAO.deleteFamilyMemberDetailsByCadre(cadreId);
		}	
		cadreProblemDetailsDAO.deleteProblemDetailsByCadre(cadreId);
		cadreRoleRelationDAO.deleteRolesByCadreId(cadreId);
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status)
		{
			try{
				List<AssignedProblemProgress> listAssignd = assignedProblemProgressDAO.getByCadreId(cadreId);
				if(listAssignd != null && listAssignd.size() > 0)
				{
					for(AssignedProblemProgress assignedProblemProgress : listAssignd)
					{
						assignedProblemProgress.setCadre(null);
						assignedProblemProgress.setIsCadreAssigned(null);
						
						long activityId = assignedProblemProgress.getProblemActivity().getProblemActivityId();
						if(activityId >= 6 && activityId <= 10)
							assignedProblemProgress.setProblemActivity(problemActivityDAO.get(13l));
						
						assignedProblemProgressDAO.save(assignedProblemProgress);
					}
				}
			}catch(Exception e){
				log.error("Error occured in cadre delete - "+e);
			}
		}	});
		Integer deletedRow = cadreDAO.deleteByCadreId(cadreId);
		return deletedRow;
	}

	public UserCadresInfoVO getUserCadresInfo(UserCadresInfoVO userCadreInfo) {
		if (log.isDebugEnabled()) {
			log.debug("CadreManagementService.getUserCadresInfo() started");
		}
		ResultStatus resultStatus = new ResultStatus();
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		Long totalUserAccessLevelCaders = 0L;
		Long totalNormalCadres = 0L;
		String model = null;
		String idToCompare = null;
		Long accessLocationValue = new Long(userCadreInfo.getUserAccessValue());
		try {
			if(userCadreInfo.getIsParent())
			{
				totalUserAccessLevelCaders = cadreDAO.findTotalCadresByUserID(userCadreInfo.getUserID(),IConstants.CADRE_MEMBER_TYPE_ACTIVE);
				totalNormalCadres = cadreDAO.findTotalCadresByUserID(userCadreInfo.getUserID(),IConstants.CADRE_MEMBER_TYPE_NORMAL);
			}
			else
			{
				List<String> modelDetails = getSubUserAccessTypeModelAndPrimaryKey(userCadreInfo.getUserAccessType());
				if(modelDetails != null && modelDetails.size() == 2)
				{
					model = modelDetails.get(0);
					idToCompare = modelDetails.get(1);
				}
				
				totalUserAccessLevelCaders = cadreDAO.findTotalCadresByUserIDInALocation(userCadreInfo.getUserID(), IConstants.CADRE_MEMBER_TYPE_ACTIVE, model, idToCompare, accessLocationValue);
				totalNormalCadres = cadreDAO.findTotalCadresByUserIDInALocation(userCadreInfo.getUserID(), IConstants.CADRE_MEMBER_TYPE_NORMAL, model, idToCompare, accessLocationValue);		
			}
			
			//Long nonAssignedToBoothActiveCadres = getAllNonAssignedBoothCadres(userCadreInfo.getUserID(),IConstants.CADRE_MEMBER_TYPE_ACTIVE);
			//Long nonAssignedToBoothNormalCadres = getAllNonAssignedBoothCadres(userCadreInfo.getUserID(),IConstants.CADRE_MEMBER_TYPE_NORMAL);
			userCadreInfo.setTotalCadres(totalUserAccessLevelCaders);
			userCadreInfo.setTotalNormalCadres(totalNormalCadres);
			//userCadreInfo.setTotalNonAssignedToBoothActiveCadres(nonAssignedToBoothActiveCadres);
			//userCadreInfo.setTotalNonAssignedToBoothNormalCadres(nonAssignedToBoothNormalCadres);
			userCadreInfo = getUserAccessRegions(userCadreInfo);
			
			Map<String, Long> cadresByCadreLevel = getCadreLevelCadresCount(userCadreInfo);
			
			userCadreInfo.setRegionLevelCadres(cadresByCadreLevel);
		} catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			resultStatus.setExceptionEncountered(e);
			e.printStackTrace();
		}

		return userCadreInfo;
	}
	
	public List<String> getSubUserAccessTypeModelAndPrimaryKey(String accessType)
	{
		List<String> modelDetails = new ArrayList<String>();
		
		if(accessType.equalsIgnoreCase(IConstants.STATE))
		{
			modelDetails.add("state");
			modelDetails.add("stateId");
		}
		else if(accessType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			modelDetails.add("district");
			modelDetails.add("districtId");
		}
		else if(accessType.equalsIgnoreCase(IConstants.MLA))
		{
			modelDetails.add("constituency");
			modelDetails.add("constituencyId");
		}
		else if(accessType.equalsIgnoreCase(IConstants.MP))
		{
			modelDetails.add("parliamentConstituency");
			modelDetails.add("constituencyId");
		}
		
		return modelDetails;
	}
	/*@SuppressWarnings("unchecked")
	public UserCadresInfoVO getUserAccessRegions(UserCadresInfoVO userCadreInfo) {
		if (log.isDebugEnabled()) {
			log.debug("CadreManagementService.getUserAccessRegions() started");
		}
		Map<Long, String> userAccessStates = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessDistricts = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessConstituencies = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessMandals = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessVillages = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessHamlets = new LinkedHashMap<Long, String>();

		Map<Long, String> zeroCadreStates = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreDistricts = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreConstituencies = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreMandals = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreVillages = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreHamlets = new LinkedHashMap<Long, String>();

		String userAccessType = userCadreInfo.getUserAccessType();
		String accessID = userCadreInfo.getUserAccessValue();
		
		List<SelectOptionVO> regionLevelZeroCadres = userCadreInfo.getRegionLevelZeroCadres();

		boolean downLevelCadresFlag = true;
		if ("MLA".equals(userAccessType)) {
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() if MLA started");
			}
			List<DelimitationConstituency> delimitationConstituency = delimitationConstituencyDAO.findDelimitationConstituencyByConstituencyID(new Long(accessID));
			Long delimitationConstituencyID = delimitationConstituency.get(0).getDelimitationConstituencyID();
			List<Tehsil> tehsils = delimitationConstituencyMandalDAO.getTehsilsByDelimitationConstituencyID(delimitationConstituencyID);
		
			log.debug("CadreManagementService.getUserAccessRegions() mandals::"+ tehsils.size());
			List cadreSizeMandalWise = cadreDAO.findCadreSizeMandalWise(userCadreInfo.getUserID());
			log.debug("CadreManagementService.getUserAccessRegions() cadreSizeMandalWise::"+ cadreSizeMandalWise.size());
			if (cadreSizeMandalWise.size() == 0)
				downLevelCadresFlag = false;
			log.debug("downLevelCadresFlag::" + downLevelCadresFlag);
			long mandalLevelZeroCadres = tehsils.size()	- cadreSizeMandalWise.size();
			StringBuilder sbMandals = getMLAFormatedData(tehsils,userAccessMandals, cadreSizeMandalWise, zeroCadreMandals);
			log.debug("CadreManagementService.getUserAccessRegions() sbMandals::"+ sbMandals);
			userCadreInfo.setUserAccessMandals(userAccessMandals);
			userCadreInfo.setZeroCadreMandals(zeroCadreMandals);

			if (sbMandals != null && sbMandals.length() > 0)
				accessID = sbMandals.substring(0, sbMandals.length() - 1);
			if (log.isDebugEnabled()) {
				log.debug(userCadreInfo.getUserAccessValue()+ "::mandalIDs for MLA constituencyID=" + accessID);
			}
			SelectOptionVO voObject = new SelectOptionVO(mandalLevelZeroCadres,"MANDAL");
			if (mandalLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);
		}
		if ("COUNTRY".equals(userAccessType)) {
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() if COUNTRY started");
			}
			List states = cadreDAO.findStatesByCountryID(accessID);
			List cadreSizeStateWise = cadreDAO.findCadreSizeStateWise(userCadreInfo.getUserID());
			if (cadreSizeStateWise.size() == 0)
				downLevelCadresFlag = false;
			long stateLevelZeroCadres = states.size() - cadreSizeStateWise.size();
			StringBuilder sbStates = getFormatedData(states, userAccessStates, cadreSizeStateWise, zeroCadreStates);
			userCadreInfo.setUserAccessStates(userAccessStates);
			userCadreInfo.setZeroCadreStates(zeroCadreStates);
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() sbStates:"+ sbStates);
			}
			if (sbStates != null && sbStates.length() > 0)
				accessID = sbStates.substring(0, sbStates.length() - 1);

			SelectOptionVO voObject = new SelectOptionVO(stateLevelZeroCadres,"STATE");
			if (stateLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);

		}
		if ((downLevelCadresFlag) && ("COUNTRY".equals(userAccessType) || "STATE".equals(userAccessType))) {
			
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() if STATE started");
			}
			List districts = cadreDAO.findDistrictsByStateID(accessID);
			List cadreSizeDistrictWise = cadreDAO.findCadreSizeDistrictWise(userCadreInfo.getUserID());
			if (cadreSizeDistrictWise.size() == 0)
				downLevelCadresFlag = false;
			long districtLevelZeroCadres = districts.size() - cadreSizeDistrictWise.size();// getZeroSize(cadreSizeZero4District);
			StringBuilder sbDistricts = getFormatedData(districts,userAccessDistricts, cadreSizeDistrictWise, zeroCadreDistricts);
			userCadreInfo.setUserAccessDistricts(userAccessDistricts);
			userCadreInfo.setZeroCadreDistricts(zeroCadreDistricts);
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() sbDistricts:"+ sbDistricts);
			}
			if (sbDistricts != null && sbDistricts.length() > 0)
				accessID = sbDistricts.substring(0, sbDistricts.length() - 1);

			SelectOptionVO voObject = new SelectOptionVO(districtLevelZeroCadres, "DISTRICT");
			if (districtLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);

		}
		if ((downLevelCadresFlag) && ("COUNTRY".equals(userAccessType)	|| "STATE".equals(userAccessType) || "DISTRICT".equals(userAccessType))) {
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() if DISTRICT started");
			}
			List mandals = cadreDAO.findMandalsByDistrictID(accessID);
			List cadreSizeMandalWise = cadreDAO
					.findCadreSizeMandalWise(userCadreInfo.getUserID());
			if (cadreSizeMandalWise.size() == 0)
				downLevelCadresFlag = false;
			long mandalLevelZeroCadres = mandals.size()
					- cadreSizeMandalWise.size();// getZeroSize(cadreSizeZero4Mandal);
			StringBuilder sbMandals = getFormatedData(mandals,
					userAccessMandals, cadreSizeMandalWise, zeroCadreMandals);
			userCadreInfo.setUserAccessMandals(userAccessMandals);
			userCadreInfo.setZeroCadreMandals(zeroCadreMandals);
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() sbMandals:"+ sbMandals);
			}
			if (sbMandals != null && sbMandals.length() > 0)
				accessID = sbMandals.substring(0, sbMandals.length() - 1);

			SelectOptionVO voObject = new SelectOptionVO(mandalLevelZeroCadres,
					"MANDAL");
			if (mandalLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);

		}
		if ((downLevelCadresFlag)
				&& ("COUNTRY".equals(userAccessType)
						|| "STATE".equals(userAccessType)
						|| "DISTRICT".equals(userAccessType)
						|| "MANDAL".equals(userAccessType) || "MLA"
						.equals(userAccessType))) {
			if (log.isDebugEnabled()) {
				log
						.debug("CadreManagementService.getUserAccessRegions() if MANDAL started");
			}
			List villages = cadreDAO.findVillagesByTehsilID(accessID);
			List cadreSizeVillageWise = cadreDAO
					.findCadreSizeVillageWise(userCadreInfo.getUserID());
			long villageLevelZeroCadres = villages.size()
					- cadreSizeVillageWise.size();// getZeroSize(cadreSizeZero4Mandal);

			StringBuilder sbVillages = getFormatedData(villages,
					userAccessVillages, cadreSizeVillageWise, zeroCadreVillages);
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() sbVillages:"+ sbVillages);
			}

			if (sbVillages != null && sbVillages.length() > 0)
				accessID = sbVillages.substring(0, sbVillages.length() - 1);

			userCadreInfo.setUserAccessVillages(userAccessVillages);
			userCadreInfo.setZeroCadreVillages(zeroCadreVillages);
			SelectOptionVO voObject = new SelectOptionVO(villageLevelZeroCadres, "VILLAGE");
			if (villageLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);
			}

		// New Code started here for Hamlet wise zero cadres......
		if ((downLevelCadresFlag)
				&& ("COUNTRY".equals(userAccessType)
						|| "STATE".equals(userAccessType)
						|| "DISTRICT".equals(userAccessType)
						|| "MANDAL".equals(userAccessType)
						|| "VILLAGE".equals(userAccessType) || "MLA"
						.equals(userAccessType))) {
			// narender
			List hamlets = cadreDAO.findHamletsByRVs(accessID);
			List cadreSizeHamletWise = cadreDAO.findCadreSizeHamletWise(userCadreInfo.getUserID());
			long hamletLevelZeroCadres = hamlets.size() - cadreSizeHamletWise.size();
			log.debug("hamletLevelZeroCadres:" + hamletLevelZeroCadres);
			log.debug("hamlets.size():" + hamlets.size());
			log.debug("cadreSizeHamletWise.size():"	+ cadreSizeHamletWise.size());

			StringBuilder sbVillages = getFormatedData(hamlets,
					userAccessHamlets, cadreSizeHamletWise, zeroCadreHamlets);
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() sbVillages:"+ sbVillages);
			}

			userCadreInfo.setUserAccessHamlets(userAccessHamlets);
			userCadreInfo.setZeroCadreHamlets(zeroCadreHamlets);
			SelectOptionVO voObject = new SelectOptionVO(hamletLevelZeroCadres,	"HAMLET");
			if (hamletLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);
		}
		log.debug("End of Service....................");

		for (SelectOptionVO vo : regionLevelZeroCadres) {
			log.debug("Name:" + vo.getName() + " size=" + vo.getId());
		}
		userCadreInfo.setRegionLevelZeroCadres(regionLevelZeroCadres);
		return userCadreInfo;
	}*/
	
	@SuppressWarnings("unchecked")
	public UserCadresInfoVO getUserAccessRegions(UserCadresInfoVO userCadreInfo) {
		if (log.isDebugEnabled()) {
			log.debug("CadreManagementService.getUserAccessRegions() started");
		}
		Map<Long, String> userAccessStates = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessDistricts = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessConstituencies = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessMandals = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessLocalElectionBodies = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessBooths = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessBoothsInMandal = new LinkedHashMap<Long, String>(); 
		Map<Long, String> userAccessVillages = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessHamlets = new LinkedHashMap<Long, String>();
		Map<Long, String> userAccessWards = new LinkedHashMap<Long, String>();
		
		Map<Long, String> zeroCadreStates = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreDistricts = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreConstituencies = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreMandals = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreLocalElectionBodies = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreVillages = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreHamlets = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreWards = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreBooths = new LinkedHashMap<Long, String>();
		Map<Long, String> zeroCadreBoothsInMandal = new LinkedHashMap<Long, String>();

		String userAccessType = userCadreInfo.getUserAccessType();
		String accessID = userCadreInfo.getUserAccessValue();
		String localElectionBodyIds = null;
		String constituencyIds = null;
		String wardIds = null;
		
		List<SelectOptionVO> regionLevelZeroCadres = userCadreInfo.getRegionLevelZeroCadres();

		boolean downLevelCadresFlag = true;
		boolean localElectionBodyCadresFlag = false;
		
		if ("COUNTRY".equals(userAccessType)) {
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() if COUNTRY started");
			}
			List states = cadreDAO.findStatesByCountryID(accessID);
			List cadreSizeStateWise = cadreDAO.findCadreSizeStateWise(userCadreInfo.getUserID());
			if (cadreSizeStateWise.size() == 0)
				downLevelCadresFlag = false;
			long stateLevelZeroCadres = states.size() - cadreSizeStateWise.size();
			StringBuilder sbStates = getFormatedData(states, userAccessStates, cadreSizeStateWise, zeroCadreStates);
			userCadreInfo.setUserAccessStates(userAccessStates);
			userCadreInfo.setZeroCadreStates(zeroCadreStates);
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() sbStates:"+ sbStates);
			}
			if (sbStates != null && sbStates.length() > 0)
				accessID = sbStates.substring(0, sbStates.length() - 1);

			SelectOptionVO voObject = new SelectOptionVO(stateLevelZeroCadres,"STATE");
			if (stateLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);

		}
		if ((downLevelCadresFlag) && ("COUNTRY".equals(userAccessType) || "STATE".equals(userAccessType))) {
			
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() if STATE started");
			}
			List districts = cadreDAO.findDistrictsByStateID(accessID);
			List cadreSizeDistrictWise = cadreDAO.findCadreSizeDistrictWise(userCadreInfo.getUserID());
			if (cadreSizeDistrictWise.size() == 0)
				downLevelCadresFlag = false;
			long districtLevelZeroCadres = districts.size() - cadreSizeDistrictWise.size();// getZeroSize(cadreSizeZero4District);
			StringBuilder sbDistricts = getFormatedData(districts,userAccessDistricts, cadreSizeDistrictWise, zeroCadreDistricts);
			userCadreInfo.setUserAccessDistricts(userAccessDistricts);
			userCadreInfo.setZeroCadreDistricts(zeroCadreDistricts);
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() sbDistricts:"+ sbDistricts);
			}
			if (sbDistricts != null && sbDistricts.length() > 0)
				accessID = sbDistricts.substring(0, sbDistricts.length() - 1);

			SelectOptionVO voObject = new SelectOptionVO(districtLevelZeroCadres, "DISTRICT");
			if (districtLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);

		}
		if ((downLevelCadresFlag) && ("COUNTRY".equals(userAccessType)	|| "STATE".equals(userAccessType) || "DISTRICT".equals(userAccessType) || "MP".equals(userAccessType))) {
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() if DISTRICT started");
			}
			List constituencies = null;
			if("MP".equals(userAccessType))
			{
				constituencies = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituencies(new Long(accessID));
			} else 
			{
				constituencies = delimitationConstituencyDAO.getLatestConstituenciesByDistrictIds(accessID);
			}
			
			List cadreSizeConstituencywise = cadreDAO.findCadreSizeConstituencywise(userCadreInfo.getUserID());
			if (cadreSizeConstituencywise.size() == 0)
					downLevelCadresFlag = false;
			long constituencyLevelZeroCadres = constituencies.size() - cadreSizeConstituencywise.size();// getZeroSize(cadreSizeZero4Constituency);
			StringBuilder sbConstituencies = getFormatedData(constituencies, userAccessConstituencies, cadreSizeConstituencywise, zeroCadreConstituencies);
			userCadreInfo.setUserAccessConstituencies(userAccessConstituencies);
			userCadreInfo.setZeroCadreConstituencies(zeroCadreConstituencies);
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() sbConstituencies:"+ sbConstituencies);
			}
			if (sbConstituencies != null && sbConstituencies.length() > 0)
				accessID = sbConstituencies.substring(0, sbConstituencies.length() - 1);

			SelectOptionVO voObject = new SelectOptionVO(constituencyLevelZeroCadres, "CONSTITUENCY");
			if (constituencyLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);			
		}
		if ((downLevelCadresFlag) && ("COUNTRY".equals(userAccessType)	|| "STATE".equals(userAccessType) || "DISTRICT".equals(userAccessType) || "MLA".equals(userAccessType)) || "MP".equals(userAccessType)) {
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() if DISTRICT started");
			}
			constituencyIds = accessID;
			List cadreSizeMandalWise = cadreDAO.findCadreSizeMandalWise(userCadreInfo.getUserID());
			if (cadreSizeMandalWise.size() == 0)
				downLevelCadresFlag = false;
			if(downLevelCadresFlag)
			{
				List mandals = delimitationConstituencyMandalDAO.getLatestMandalsInConstituencies(accessID);
				long mandalLevelZeroCadres = mandals.size()	- cadreSizeMandalWise.size();// getZeroSize(cadreSizeZero4Mandal);
				StringBuilder sbMandals = getFormatedData(mandals, userAccessMandals, cadreSizeMandalWise, zeroCadreMandals);
				userCadreInfo.setUserAccessMandals(userAccessMandals);
				userCadreInfo.setZeroCadreMandals(zeroCadreMandals);
				if (log.isDebugEnabled()) {
					log.debug("CadreManagementService.getUserAccessRegions() sbMandals:"+ sbMandals);
				}
				if (sbMandals != null && sbMandals.length() > 0)
					accessID = sbMandals.substring(0, sbMandals.length() - 1);

				SelectOptionVO voObject = new SelectOptionVO(mandalLevelZeroCadres, "MANDAL");
				if (mandalLevelZeroCadres > 0)
					regionLevelZeroCadres.add(voObject);

			}
			List localElectionBodies = assemblyLocalElectionBodyDAO.findByConstituencyIds(constituencyIds);
			if(localElectionBodies.size() != 0)
			{
				List cadreSizeLocalElectionBodywise = cadreDAO.findCadreSizeLocalElectionBodywise(userCadreInfo.getUserID());
				if (cadreSizeLocalElectionBodywise.size() > 0)
					localElectionBodyCadresFlag = true;
				log.debug("CadreManagementService.getUserAccessRegions() cadreSizeMandalWise::"+ cadreSizeLocalElectionBodywise.size());
				long localElectionBodyLevelZeroCadres = localElectionBodies.size()	- cadreSizeLocalElectionBodywise.size();
				StringBuilder sbLocalElectionBodies = getFormatedData(localElectionBodies, userAccessLocalElectionBodies, cadreSizeLocalElectionBodywise, zeroCadreLocalElectionBodies);
				userCadreInfo.setUserAccessLocalElectionBodies(userAccessLocalElectionBodies);
				userCadreInfo.setZeroCadreLocalElectionBodies(zeroCadreLocalElectionBodies);
				if (sbLocalElectionBodies != null && sbLocalElectionBodies.length() > 0)
					localElectionBodyIds = sbLocalElectionBodies.substring(0, sbLocalElectionBodies.length() - 1);

				SelectOptionVO voObject = new SelectOptionVO(localElectionBodyLevelZeroCadres, "MUNICIPAL/CORP/GMC");
				if (localElectionBodyLevelZeroCadres > 0)
					regionLevelZeroCadres.add(voObject);
			}			
		} 
		if ((downLevelCadresFlag) && ("COUNTRY".equals(userAccessType)	|| "STATE".equals(userAccessType) || "DISTRICT".equals(userAccessType) || "MLA".equals(userAccessType)) || "MP".equals(userAccessType)) {
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() if DISTRICT started");
			}
			 
			
			List boothsInTehsils = boothDAO.findBoothsInTehsils(accessID, constituencyIds, IConstants.DELIMITATION_YEAR);
			List cadreSizeBoothwiseInMandal = cadreDAO.findCadreSizeBoothwiseInMandal(userCadreInfo.getUserID());
			long boothwiseZeroCadresInMandal = boothsInTehsils.size()	- cadreSizeBoothwiseInMandal.size();// getZeroSize(cadreSizeZero4Mandal);
				StringBuilder sbLocalElectionBodies = getFormatedData(boothsInTehsils, userAccessBoothsInMandal, cadreSizeBoothwiseInMandal, zeroCadreBoothsInMandal);
				userCadreInfo.setUserAccessBoothsInMandal(userAccessBoothsInMandal);
				userCadreInfo.setZeroCadreBoothsInMandal(zeroCadreBoothsInMandal);
				
				
				SelectOptionVO voObject = new SelectOptionVO(boothwiseZeroCadresInMandal, "BOOTHS IN MANDALS");
				if (boothwiseZeroCadresInMandal > 0)
					regionLevelZeroCadres.add(voObject);

		}
		if((localElectionBodyCadresFlag) && ("COUNTRY".equals(userAccessType) || "STATE".equals(userAccessType)	|| "DISTRICT".equals(userAccessType) || "MLA".equals(userAccessType) || "MP".equals(userAccessType)))
		{
			List wards = constituencyDAO.findWardsInLocalElectionBodies(localElectionBodyIds);
			if(wards.size() != 0)
			{
				List cadreSizeWardswise = cadreDAO.findCadreSizeWardswise(userCadreInfo.getUserID());
				long wardLevelZeroCadres = wards.size()	- cadreSizeWardswise.size();
				StringBuilder sbwards = getFormatedData(wards, userAccessWards, cadreSizeWardswise, zeroCadreWards);
				userCadreInfo.setUserAccessWards(userAccessWards);
				userCadreInfo.setZeroCadreWards(zeroCadreWards);
				if (sbwards != null && sbwards.length() > 0)
					wardIds = sbwards.substring(0, sbwards.length() - 1);

				SelectOptionVO voObject = new SelectOptionVO(wardLevelZeroCadres, "WARDS");
				if (wardLevelZeroCadres > 0)
					regionLevelZeroCadres.add(voObject);
			}
		}
		if((localElectionBodyCadresFlag) && ("COUNTRY".equals(userAccessType) || "STATE".equals(userAccessType)	|| "DISTRICT".equals(userAccessType) || "MLA".equals(userAccessType) || "MP".equals(userAccessType)))
		{
			List booths = boothDAO.findBoothsInLocalElectionBodies(localElectionBodyIds, constituencyIds, new Long(IConstants.DELIMITATION_YEAR));
			//List wards = constituencyDAO.findWardsInLocalElectionBodies(localElectionBodyIds);
			
			List cadreSizeBoothwise = cadreDAO.findCadreSizeBoothwise(userCadreInfo.getUserID());
			long wardLevelBoothCadres = booths.size()	- cadreSizeBoothwise.size();
			StringBuilder sbbooths = getFormatedData(booths, userAccessBooths, cadreSizeBoothwise,zeroCadreBooths);
			userCadreInfo.setUserAccessBooths(userAccessBooths);
			userCadreInfo.setZeroCadreBooths(zeroCadreBooths);
			/*if (sbbooths != null && sbbooths.length() > 0)
					localElectionBodyIds = sbwards.substring(0, sbwards.length() - 1);
				*/
			SelectOptionVO voObject = new SelectOptionVO(wardLevelBoothCadres, "BOOTHS In Municipal/Corp/GMC");
			if (wardLevelBoothCadres > 0)
				regionLevelZeroCadres.add(voObject);
			
		}
		if ((downLevelCadresFlag) && ("COUNTRY".equals(userAccessType) || "STATE".equals(userAccessType) || "DISTRICT".equals(userAccessType) || "MLA".equals(userAccessType) || "MP".equals(userAccessType))) {
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() if MANDAL started");
			}
			List hamlets = cadreDAO.findHamletsByTehsilIds(accessID);
			
			List cadreSizeHamletWise = cadreDAO.findCadreSizeHamletWise(userCadreInfo.getUserID());
			long hamletLevelZeroCadres = hamlets.size() - cadreSizeHamletWise.size();// getZeroSize(cadreSizeZero4Mandal);

			StringBuilder sbHamlets = getFormatedData(hamlets, userAccessHamlets, cadreSizeHamletWise, zeroCadreHamlets);
			if (log.isDebugEnabled()) {
				log.debug("CadreManagementService.getUserAccessRegions() sbVillages:"+ sbHamlets);
			}

			if (sbHamlets != null && sbHamlets.length() > 0)
				accessID = sbHamlets.substring(0, sbHamlets.length() - 1);

			userCadreInfo.setUserAccessHamlets(userAccessHamlets);
			userCadreInfo.setZeroCadreHamlets(zeroCadreHamlets);
			SelectOptionVO voObject = new SelectOptionVO(hamletLevelZeroCadres, "VILLAGE");
			if (hamletLevelZeroCadres > 0)
				regionLevelZeroCadres.add(voObject);
			}
		
		log.debug("End of Service....................");

		for (SelectOptionVO vo : regionLevelZeroCadres) {
			log.debug("Name:" + vo.getName() + " size=" + vo.getId());
		}
		userCadreInfo.setRegionLevelZeroCadres(regionLevelZeroCadres);
		return userCadreInfo;
	}
	
	@SuppressWarnings("unchecked")
	public StringBuilder getFormatedData(List regionData,
			Map<Long, String> userAccessRegions, List cadreAvailableRegions,
			Map<Long, String> zeroCadreRegions) {

		StringBuilder sb = new StringBuilder();
		Map<Long, Object> availableCadreRegions = new HashMap<Long, Object>();
		for (int i = 0; i < cadreAvailableRegions.size(); i++) {
			Object[] objInfo = (Object[]) cadreAvailableRegions.get(i);
			if (objInfo[0] == null)
				continue;
			Long key = new Long(objInfo[0].toString());
			availableCadreRegions.put(key, objInfo[0]);
		}

		for (int i = 0; i < regionData.size(); i++) {
			Object[] objInfo = (Object[]) regionData.get(i);
			Long key = new Long(objInfo[0].toString());
			String value = objInfo[1].toString();
			userAccessRegions.put(key, value);
			if (null == availableCadreRegions.get(key)) {
				zeroCadreRegions.put(key, value);
			} else {
				sb.append(key).append(",");
			}
		}

		return sb;
	}

	@SuppressWarnings("unchecked")
	public StringBuilder getMLAFormatedData(List<Tehsil> regionData,
			Map<Long, String> userAccessRegions, List cadreAvailableRegions,
			Map<Long, String> zeroCadreRegions) {

		StringBuilder sb = new StringBuilder();
		Map<Long, Object> availableCadreRegions = new HashMap<Long, Object>();
		for (int i = 0; i < cadreAvailableRegions.size(); i++) {
			Object[] objInfo = (Object[]) cadreAvailableRegions.get(i);
			Long key = new Long(objInfo[0].toString());
			availableCadreRegions.put(key, objInfo[0]);
		}

		for (Tehsil tehsil : regionData) {
			Long key = tehsil.getTehsilId();
			String value = tehsil.getTehsilName();
			userAccessRegions.put(key, value);
			if (null == availableCadreRegions.get(key)) {
				zeroCadreRegions.put(key, value);
			} else {
				sb.append(key).append(",");
			}
		}
		return sb;
	}

	@SuppressWarnings("unchecked")
	public StringBuilder getFormatedData(List regionData,
			Map<Long, String> userAccessRegions) {
		// userAccessRegions = new LinkedHashMap<Long, String>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < regionData.size(); i++) {
			Object[] objInfo = (Object[]) regionData.get(i);
			userAccessRegions.put(new Long(objInfo[0].toString()), objInfo[1]
					.toString());
			sb.append(objInfo[0]).append(",");
		}
		return sb;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Long> getCadreLevelCadresCount(UserCadresInfoVO userCadreInfo)
	{
		List cadresByRegionList = null;
		String model = null;
		String idToCompare = null;
		if(userCadreInfo.getIsParent())
		{
			cadresByRegionList = cadreDAO.findCadresByLevels(userCadreInfo.getUserID(),IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		}
		else
		{
			List<String> modelDetails = getSubUserAccessTypeModelAndPrimaryKey(userCadreInfo.getUserAccessType());
			if(modelDetails != null && modelDetails.size() == 2)
			{
				model = modelDetails.get(0);
				idToCompare = modelDetails.get(1);
			}
			cadresByRegionList = cadreDAO.findTotalCadresByUserIdBasedOnCadreLevel(userCadreInfo.getUserID(), IConstants.CADRE_MEMBER_TYPE_ACTIVE, model, idToCompare, new Long(userCadreInfo.getUserAccessValue()));
		}
		
		Map<String, Long> result = new LinkedHashMap<String, Long>();
		for (int i = 0; i < cadresByRegionList.size(); i++) {
			Object[] objInfo = (Object[]) cadresByRegionList.get(i);
			result.put(objInfo[0].toString(), new Long(objInfo[1].toString()));
		}
		return result;
	}

	/*
	 * @SuppressWarnings("unchecked") public UserCadresInfoVO
	 * getCadreLevelCadresCount12(UserCadresInfoVO userCadreInfo){ List
	 * cadresByRegionList =
	 * cadreDAO.findCadresByLevels(userCadreInfo.getUserID()); Map<String,Long>
	 * tempMap = new LinkedHashMap<String,Long>(); for(int i =0;
	 * i<cadresByRegionList.size(); i++){ Object[] objInfo = (Object[])
	 * cadresByRegionList.get(i); tempMap.put(objInfo[0].toString(), new
	 * Long(objInfo[1].toString())); }
	 * userCadreInfo.setRegionLevelCadres(tempMap); return userCadreInfo; }
	 */

	// Ajax calling methods
	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getCountryAllStatesCadres(Long countryID,
			Long userID) {
		List stateCadres = cadreDAO.findStateCadresByCountry(countryID, userID,
				IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = stateCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for (int i = 0; i < size; i++) {
			Object[] voObject = (Object[]) stateCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("STATE");
			regionInfoVo.setRegionId(new Long(voObject[0].toString()));
			regionInfoVo.setRegionName(voObject[1].toString());
			regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;
	}

	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getStateAllDistrictsCadres(Long stateID,
			Long userID) {
		List districtCadres = cadreDAO.findDistCadresByState(stateID, userID,
				IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = districtCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for (int i = 0; i < size; i++) {
			Object[] voObject = (Object[]) districtCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("DISTRICT");
			regionInfoVo.setRegionId(new Long(voObject[0].toString()));
			regionInfoVo.setRegionName(voObject[1].toString());
			regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;
	}

	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getDistrictAllMandalsCadres(Long districtID,
			Long userID) {
		List mandalCadres = cadreDAO.findMandalCadresByDist(districtID, userID,
				IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = mandalCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for (int i = 0; i < size; i++) {
			Object[] voObject = (Object[]) mandalCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("MANDAL");
			regionInfoVo.setRegionId(new Long(voObject[0].toString()));
			regionInfoVo.setRegionName(voObject[1].toString());
			regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;
	}	

	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getConstituencyAllMandalsCadres(
			Long constituencyID, Long userID) {
		List<DelimitationConstituency> delimitationConstituency = delimitationConstituencyDAO
				.findDelimitationConstituencyByConstituencyID(constituencyID);
		Long delimitationConstituencyID = delimitationConstituency.get(0)
				.getDelimitationConstituencyID();
		List<Tehsil> tehsils = delimitationConstituencyMandalDAO
				.getTehsilsByDelimitationConstituencyID(delimitationConstituencyID);
		StringBuilder mandalIDs = new StringBuilder();
		for (Tehsil tehsil : tehsils) {
			mandalIDs.append(",").append(tehsil.getTehsilId());
		}

		System.out.println("mandalIDs.toString():::" + mandalIDs.toString());
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		String strMandalIDs = new String();
		if (mandalIDs.length() > 0) {
			strMandalIDs = mandalIDs.toString().substring(1);
			List mandalCadres = cadreDAO.findMandalCadresByMandals(
					strMandalIDs, userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
			int size = mandalCadres.size();
			for (int i = 0; i < size; i++) {
				Object[] voObject = (Object[]) mandalCadres.get(i);
				CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("MANDAL");
				regionInfoVo.setRegionId(new Long(IConstants.RURAL_TYPE+voObject[0].toString()));
				regionInfoVo.setRegionName(voObject[1].toString());
				regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
				formattedData.add(regionInfoVo);
			}
		}
		return formattedData;
	}
	
	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getMandalAllVillagesCadres(Long mandalID,
			Long userID) {
		List villageCadres = cadreDAO.findVillageCadresByMandal(mandalID,
				userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = villageCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for (int i = 0; i < size; i++) {
			Object[] voObject = (Object[]) villageCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO(voObject[3]
					.toString());// V or T is the value but not VILLAGE
			regionInfoVo.setRegionId(new Long(voObject[0].toString()));
			regionInfoVo.setRegionName(voObject[1].toString());
			regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;
	}

	public List<CadreInfo> getCadresByVillage(Long villageID, Long userID) {
		List<CadreInfo> formattedData = new ArrayList<CadreInfo>();
		List<Cadre> cadresList = cadreDAO.findCadresByVillage(villageID,
				userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		for (Cadre cadre : cadresList) {
			CadreInfo cadreInfo = convertCadreToCadreInfo(cadre);
			formattedData.add(cadreInfo);
		}
		return formattedData;
	}

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> findStatesByCountryID(String countryID) {
		List states = cadreDAO.findStatesByCountryID(countryID);
		List<SelectOptionVO> stateNames = dataFormatTo_SelectOptionVO(states);
		return stateNames;
	}

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> findDistrictsByState(String stateID) {
		List districts = cadreDAO.findDistrictsByStateID(stateID);
		List<SelectOptionVO> districtNames = dataFormatTo_SelectOptionVO(districts);
		return districtNames;
	}

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> findMandalsByDistrict(String districtID) {
		List mandals = cadreDAO.findMandalsByDistrictID(districtID);
		List<SelectOptionVO> mandalNames = dataFormatTo_SelectOptionVO(mandals);
		return mandalNames;
	}

	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> findVillagesByTehsilID(String mandalID) {
		// List villages = cadreDAO.findVillagesByTehsilID(mandalID);
		List<Township> townships = townshipDAO
				.findByTehsilID(new Long(mandalID));
		List<SelectOptionVO> villageNames = getTownshipHamlets(townships);
		// List<SelectOptionVO> villageNames =
		// dataFormatTo_SelectOptionVO(villages);
		return villageNames;
	}

	public String getConstituencyName(Long constituencyID) {
		Constituency constituency = constituencyDAO.get(new Long(constituencyID));
		String name = "";
		if (constituency != null)
			name = constituency.getName();
		return name;
	}

	public String getStateName(Long stateID) {
		State state = stateDAO.get(stateID);
		String name = "";
		if (state != null)
			name = state.getStateName();
		return name;
	}

	public String getDistrictName(Long districtID) {
		District district = districtDAO.get(districtID);
		String name = "";
		if (district != null)
			name = district.getDistrictName();
		return name;
	}

	public String getMandalName(Long mandalID) {
		Tehsil mandal = tehsilDAO.get(mandalID);
		String name = "";
		if (mandal != null)
			name = mandal.getTehsilName();
		return name;
	}

	public String getCountryName(Long countryID) {
		Country country = countryDAO.get(countryID);
		String name = "";
		if (country != null)
			name = country.getCountryName();
		return name;
	}

	public List<CadreInfo> getCadresByCadreLevel(String cadreLevel, Long userID, Boolean isParent,String accessType,Long acessLocationId)
	{
		List<CadreInfo> cadreInfoList = new ArrayList<CadreInfo>();
		List<Cadre> cadresList = null;
		String model = null;
		String idToCompare = null;
		
		if(isParent)
			cadresList = cadreDAO.findCadresByCadreLevel(cadreLevel,userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		else
		{
			List<String> modelDetails = getSubUserAccessTypeModelAndPrimaryKey(accessType);
			if(modelDetails != null && modelDetails.size() == 2)
			{
				model = modelDetails.get(0);
				idToCompare = modelDetails.get(1);
			}
			cadresList = cadreDAO.findCadresByCadreLevelByUserIDInALocation(cadreLevel, userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE, model, idToCompare, acessLocationId);
		}
		for (Cadre cadre : cadresList) {
			CadreInfo cadreInfo = convertCadreToCadreInfo(cadre);
			cadreInfoList.add(cadreInfo);
		}
		return cadreInfoList;
	}

	@SuppressWarnings("deprecation")
	public CadreInfo convertCadreToCadreInfo(Cadre cadre) {
		
		CadreInfo cadreInfo = new CadreInfo();
		SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
		UserAddress currentAddress;
		UserAddress officialAddress;
		
		cadreInfo.setCadreID(cadre.getCadreId());
		cadreInfo.setFirstName(cadre.getFirstName());
		cadreInfo.setMiddleName(cadre.getMiddleName());
		cadreInfo.setLastName(cadre.getLastName());
		cadreInfo.setFatherOrSpouseName(cadre.getFatherOrSpouseName()!=null?cadre.getFatherOrSpouseName():"");
		cadreInfo.setNoOfFamilyMembers(cadre.getNoOfFamilyMembers()!=null?cadre.getNoOfFamilyMembers():"");
		cadreInfo.setNoOfVoters(cadre.getNoOfVoters());
		Date dob = cadre.getDateOfBirth();
		Date today = new Date();			
		Integer age = today.getYear() - dob.getYear(); 
		cadreInfo.setAge(age.toString());
		cadreInfo.setDateOfBirth(sdf.format(dob));
		if(IConstants.TRUE.equals(cadre.getExactDateOfBirth()))
			cadreInfo.setDobOption("Date Of Birth");
		if(IConstants.FALSE.equals(cadre.getExactDateOfBirth()))
			cadreInfo.setDobOption("Age");		
		cadreInfo.setGender(cadre.getGender());
		cadreInfo.setMobile(cadre.getMobile());
		cadreInfo.setTelephone(cadre.getTelephone());
		cadreInfo.setEmail(cadre.getEmail());
		currentAddress = cadre.getCurrentAddress();
		officialAddress = cadre.getPermanentAddress();

		cadreInfo.setHouseNo(currentAddress.getHouseNo());
		cadreInfo.setStreet(currentAddress.getStreet());
		cadreInfo.setPinCode(currentAddress.getPinCode());
		// retrieving current address(CA) locations
		State stateCA = currentAddress.getState();
		District districtCA = currentAddress.getDistrict();
		Constituency constituencyCA = currentAddress.getConstituency();
		Constituency parlConstituencyCA = currentAddress.getParliamentConstituency();
		Booth boothCA = currentAddress.getBooth();
		Tehsil tehsilCA = currentAddress.getTehsil();
		//Township townshipCA = currentAddress.getTownship();
		Hamlet hamletCA = currentAddress.getHamlet();
		Township townshipCA = currentAddress.getTownship();
		LocalElectionBody localBodyCA = currentAddress.getLocalElectionBody();
		Constituency wardCA = currentAddress.getWard();
		
		cadreInfo.setState(stateCA.getStateId().toString());
		cadreInfo.setStateName(stateCA.getStateName());
		cadreInfo.setImage(cadre.getImage() != null ? cadre.getImage():"human.jpg");
		if(districtCA != null)
		{
			cadreInfo.setDistrict(districtCA.getDistrictId().toString());
			cadreInfo.setDistrictName(districtCA.getDistrictName()+" (Dt.)");
		}
		if(parlConstituencyCA != null)
		{
			cadreInfo.setParliament(parlConstituencyCA.getConstituencyId().toString());
			cadreInfo.setParliamentName(parlConstituencyCA.getName()+" (Parliament)");			
		}
		cadreInfo.setConstituencyID(constituencyCA.getConstituencyId());
		cadreInfo.setConstituencyName(constituencyCA.getName());
		
		if(tehsilCA != null){
			cadreInfo.setMandal(IConstants.RURAL_TYPE+tehsilCA.getTehsilId().toString());
			cadreInfo.setMandalName(tehsilCA.getTehsilName());
			
			if(hamletCA != null){
				cadreInfo.setVillage(IConstants.RURAL_TYPE+hamletCA.getHamletId().toString());
				cadreInfo.setVillageName(hamletCA.getHamletName()+" (V)");
			}
			else if(townshipCA != null){
				cadreInfo.setVillage(IConstants.RURAL_TYPE+townshipCA.getTownshipId().toString());
				cadreInfo.setVillageName(townshipCA.getTownshipName()+ " " + townshipCA.getTownshipType());
			}
			
		}else if(localBodyCA != null){
			Long assemblyLocalId = (Long)assemblyLocalElectionBodyDAO.findAssemblyLocalElectionBodyByLocalBodyAndConstituency(localBodyCA.getLocalElectionBodyId(),constituencyCA.getConstituencyId()).get(0);
			cadreInfo.setMandal(IConstants.URBAN_TYPE+assemblyLocalId);
			cadreInfo.setMandalName(localBodyCA.getName()+" "+ localBodyCA.getElectionType().getElectionType());
			cadreInfo.setVillage(IConstants.URBAN_TYPE+wardCA.getConstituencyId());
			cadreInfo.setVillageName(wardCA.getLocalElectionBodyWard() != null ? wardCA.getLocalElectionBodyWard().getWardName().concat("(").
					concat(wardCA.getName().toUpperCase()).concat(")"):wardCA.getName());					
		}		
		
		if(boothCA != null)
		{
			cadreInfo.setBooth(boothCA.getBoothId().toString());
			cadreInfo.setBoothName("Booth No"+boothCA.getPartNo()+" - "+boothCA.getLocation());
		}
		//check whether the current address is same as official address
		if (currentAddress.getUserAddressId() == officialAddress.getUserAddressId()) {
			cadreInfo.setSameAsCA(true);
			cadreInfo.setPhouseNo(currentAddress.getHouseNo());
			cadreInfo.setPstreet(currentAddress.getStreet());
			cadreInfo.setPpinCode(currentAddress.getPinCode());
			
			cadreInfo.setPstate(cadreInfo.getState());
			cadreInfo.setPdistrict(cadreInfo.getDistrict());
			cadreInfo.setPParliament(cadreInfo.getParliament());
			cadreInfo.setPconstituencyID(cadreInfo.getConstituencyID());
			cadreInfo.setPmandal(cadreInfo.getPvillage());
			cadreInfo.setPvillage(cadreInfo.getVillage());	
			cadreInfo.setPBooth(cadreInfo.getBooth());
			
		} else {
			cadreInfo.setSameAsCA(false);
			cadreInfo.setPhouseNo(officialAddress.getHouseNo());
			cadreInfo.setPstreet(officialAddress.getStreet());
			cadreInfo.setPpinCode(officialAddress.getPinCode());
			// retrieving official address(OA) locations
			State stateOA = officialAddress.getState();
			District districtOA = officialAddress.getDistrict();
			Constituency constituencyOA = officialAddress.getConstituency();
			Constituency parlConstituencyOA = officialAddress.getParliamentConstituency();
			Tehsil tehsilOA = officialAddress.getTehsil();
			//Township townshipOA = officialAddress.getTownship();
			Hamlet hamletOA = officialAddress.getHamlet();
			Township townshipOA = officialAddress.getTownship();
			
			LocalElectionBody localBodyOA = officialAddress.getLocalElectionBody();
			Constituency wardOA = officialAddress.getWard();
			Booth boothOA = officialAddress.getBooth();
			cadreInfo.setPstate(stateOA.getStateId().toString());
			cadreInfo.setPstateName(stateOA.getStateName());
			if(districtOA != null)
			{			
				cadreInfo.setPdistrict(districtOA.getDistrictId().toString());
				cadreInfo.setPdistrictName(districtOA.getDistrictName());
			}
			
			if(parlConstituencyOA != null)
			{
				cadreInfo.setPParliament(parlConstituencyOA.getConstituencyId().toString());
				cadreInfo.setPParliamentName(parlConstituencyOA.getName());			
			}
			cadreInfo.setPconstituencyID(constituencyOA.getConstituencyId());
			cadreInfo.setPconstituencyName(constituencyOA.getName());
			
			if(tehsilOA != null){
				cadreInfo.setPmandal(IConstants.RURAL_TYPE+tehsilOA.getTehsilId().toString());
				cadreInfo.setPmandalName(tehsilOA.getTehsilName()+" Mandal");
				
				if(hamletOA != null){
					cadreInfo.setPvillage(hamletOA.getHamletId().toString());
					cadreInfo.setPvillageName(hamletOA.getHamletName());
				}else if(townshipOA != null){
					cadreInfo.setPvillage(townshipOA.getTownshipId().toString());
					cadreInfo.setPvillageName(townshipOA.getTownshipName() + " " + townshipOA.getTownshipType());
				}
			}else if(localBodyOA != null){
				Long assemblyLocalBodyId = (Long)assemblyLocalElectionBodyDAO.findAssemblyLocalElectionBodyByLocalBodyAndConstituency(localBodyOA.getLocalElectionBodyId(),constituencyOA.getConstituencyId()).get(0); 
				cadreInfo.setPmandal(IConstants.URBAN_TYPE+assemblyLocalBodyId);
				cadreInfo.setPmandalName(localBodyOA.getName()+" "+localBodyOA.getElectionType().getElectionType());
				cadreInfo.setPvillage(IConstants.URBAN_TYPE+wardOA.getConstituencyId().toString());
				cadreInfo.setPvillageName(wardOA.getLocalElectionBodyWard() != null ? wardOA.getLocalElectionBodyWard().getWardName().concat("(").
						concat(wardOA.getName().toUpperCase()).concat(")"):wardOA.getName());
			}
			if(boothOA != null)
			{
				cadreInfo.setPBooth(boothOA.getBoothId().toString());
				cadreInfo.setPBoothName("Booth No"+boothOA.getPartName()+" "+boothOA.getLocation());
			}
		}

		List<Object[]> familyDetails = cadreFamilyMemberInfoDAO.findByCadreId(cadre.getCadreId());
		
		if(familyDetails.size() >= 1)
		{
			Object[] parms = (Object[])familyDetails.get(0);
			cadreInfo.setFirstFamilyMemberName(parms[1]!=null?parms[1].toString():"");
			cadreInfo.setFirstFamilyMemberRelation(parms[3]!=null?parms[3].toString():"");
			cadreInfo.setFirstFamilyMemberRelationId(parms[4]!=null?parms[4].toString():"");
			
			if(parms[2] != null && parms[2].toString().length() > 0)
			{
				Date fmdob = (Date) parms[2];
				cadreInfo.setFirstFamilyMemberDOB(sdf.format(fmdob));
			}
			else
			{
				cadreInfo.setFirstFamilyMemberDOB("");
			}
			cadreInfo.setChildrenFlag(true);
		}
		if(familyDetails.size() >= 2)
		{
			Object[] parms = (Object[])familyDetails.get(1);
			cadreInfo.setSecondFamilyMemberName(parms[1]!=null?parms[1].toString():"");
			cadreInfo.setSecondFamilyMemberRelation(parms[3]!=null?parms[3].toString():"");
			cadreInfo.setSecondFamilyMemberRelationId(parms[4]!=null?parms[4].toString():"");
			
			if(parms[2] != null && parms[2].toString().length() > 0)
			{
				Date fmdob = (Date) parms[2];
				cadreInfo.setSecondFamilyMemberDOB(sdf.format(fmdob));
			}
			else
			{
				cadreInfo.setSecondFamilyMemberDOB("");
			}
		}
		
		if(familyDetails.size() >= 3)
		{
			Object[] parms = (Object[])familyDetails.get(2);
			cadreInfo.setThirdFamilyMemberName(parms[1]!=null?parms[1].toString():"");
			cadreInfo.setThirdFamilyMemberRelation(parms[3]!=null?parms[3].toString():"");
			cadreInfo.setThirdFamilyMemberRelationId(parms[4]!=null?parms[4].toString():"");
			
			if(parms[2] != null && parms[2].toString().length() > 0)
			{
				Date fmdob = (Date) parms[2];
				cadreInfo.setThirdFamilyMemberDOB(sdf.format(fmdob));
			}
			else
			{
				cadreInfo.setThirdFamilyMemberDOB("");
			}
		}
				
		List<CadreLanguageEfficiency> cadreLanguageSkills = cadreLanguageEfficiencyDAO.findByCadreId(cadre.getCadreId());
		List<String> languageOptions_English = new ArrayList<String>();
		List<String> languageOptions_Hindi = new ArrayList<String>();
		if (cadreLanguageSkills != null && cadreLanguageSkills.size() > 0) {

			for (CadreLanguageEfficiency obj : cadreLanguageSkills) {
				int i = -1;

				if (IConstants.LANGUAGE_ENGLISH.equals(obj.getLanguage()
						.getLanguage())) {
					if (obj.getIsAbleToRead()!= null && obj.getIsAbleToRead().equals("true"))
						languageOptions_English.add(IConstants.READ_LANGUAGE);
					if (obj.getIsAbleToSpeak() != null && obj.getIsAbleToSpeak().equals("true"))
						languageOptions_English.add(IConstants.SPEAK_LANGUAGE);
					if (obj.getIsAbleToWrite() != null && obj.getIsAbleToWrite().equals("true"))
						languageOptions_English.add(IConstants.WRITE_LANGUAGE);

				}
				if (IConstants.LANGUAGE_HINDI.equals(obj.getLanguage()
						.getLanguage())) {
					if (obj.getIsAbleToRead() != null && obj.getIsAbleToRead().equals("true"))
						languageOptions_Hindi.add(IConstants.READ_LANGUAGE);
					if (obj.getIsAbleToSpeak() != null && obj.getIsAbleToSpeak().equals("true"))
						languageOptions_Hindi.add(IConstants.SPEAK_LANGUAGE);
					if (obj.getIsAbleToWrite() != null && obj.getIsAbleToWrite().equals("true"))
						languageOptions_Hindi.add(IConstants.WRITE_LANGUAGE);

				}

			}
			cadreInfo.setLanguageOptions_English(languageOptions_English);
			cadreInfo.setLanguageOptions_Hindi(languageOptions_Hindi);

		}
		
		List<SelectOptionVO> rolesList = getRolesByCadreId(cadre.getCadreId());
		if(rolesList != null && rolesList.size() > 0)
		{
			List<Long> rolesIdsList = new ArrayList<Long>(0);
			List<String> rolesStrList = new ArrayList<String>(0);
			for(SelectOptionVO selectOptionVO : rolesList)
			{
				rolesIdsList.add(selectOptionVO.getId());
				rolesStrList.add(selectOptionVO.getName());
			}
			cadreInfo.setCadreRoles(rolesIdsList);
			cadreInfo.setCadreRolesStr(rolesStrList);
		}
		
		Long  edu= 0L;
		cadreInfo.setEducation(edu = cadre.getEducation() != null ? cadre.getEducation().getEduQualificationId() : null);
		String eduStr = "";
		cadreInfo.setEducationStr(eduStr = cadre.getEducation() != null ? cadre.getEducation().getQualification() : "");
		Long professn = 0L;
		cadreInfo.setProfession(professn = cadre.getOccupation() != null ? cadre.getOccupation().getOccupationId() : null);
		String profsnStr = "";
		cadreInfo.setProfessionStr(profsnStr = cadre.getOccupation() != null ? cadre.getOccupation().getOccupation() : "");
		
		if(cadre.getAnnualIncome() != null)
		cadreInfo.setIncome(new Long(cadre.getAnnualIncome().longValue()).toString());
		
		if(cadre.getCasteCategory() != null){
			cadreInfo.setSocialStatus(cadre.getCasteCategory().getSocialCategoryId());
			cadreInfo.setCasteCategoryStr(cadre.getCasteCategory().getCategory());
		}
		
		cadreInfo.setMemberType(cadre.getMemberType());
		if (IConstants.CADRE_MEMBER_TYPE_ACTIVE.equals(cadre.getMemberType())) {
			String level = cadre.getCadreLevel().getLevel();
			cadreInfo.setCadreLevel(cadre.getCadreLevel().getCadreLevelID());
			cadreInfo.setStrCadreLevel(level);
			String levelValue = "";
			String levelValueID = cadre.getCadreLevelValue().toString();
			if ("COUNTRY".equals(level)) {
				levelValue = getCountryName(new Long(levelValueID));
			} else if ("STATE".equals(level)) {
				levelValue = getStateName(new Long(levelValueID));
			} else if ("DISTRICT".equals(level)) {
				levelValue = getDistrictName(new Long(levelValueID));
			} else if ("CONSTITUENCY".equals(level) || "WARD".equals(level) ) {
				levelValueID =levelValueID.substring(1);
				levelValue = getConstituencyName(new Long(levelValueID));
			} else if ("MANDAL".equals(level)) {
				levelValue = getMandalName(new Long(levelValueID));
			} else if ("VILLAGE".equals(level)) {
				log.debug("CadreManagementService.convertCadreToCadreInfo::: levelValueID="+ levelValueID);
				String type = levelValueID.substring(0, 1);
				Long id = new Long(levelValueID.substring(1));
				if (IConstants.HAMLET_TYPE.equals(type)) {
					levelValue = getHamletName(id);
				} else if (IConstants.TOWNSHIP_TYPE.equals(type)) {
					levelValue = getTownshipName(id);
				}
				log.debug("CadreManagementService.convertCadreToCadreInfo::: levelValueID="+ levelValueID);
			} else if ("MUNICIPAL-CORP-GMC".equals(level)) {
				levelValue = getLocalElectionBodyName(new Long(levelValueID));
			}  else if ("BOOTH".equals(level)) {
				levelValue = getBoothDetailsByBoothId(new Long(levelValueID));
			}
			cadreInfo.setCadreLevelValue(cadre.getCadreLevelValue());
			cadreInfo.setStrCadreLevelValue(levelValue);
			if (IConstants.USER_TYPE_PARTY.equals(cadre.getRegistration().getUserType()) && IConstants.BJP.equals(cadre.getRegistration().getParty().getShortName())) {
										
					//set party committee and designation and duration
				    if(cadre.getDesignation() !=  null){
						cadreInfo.setDesignation(cadre.getDesignation().getPartyWkgCommitteeDesignationId());
						cadreInfo.setDesignationStr(cadre.getDesignation().getDesignation());
						cadreInfo.setPartyCommitteeName(cadre.getDesignation().getPartyWorkingCommittee().getCommitteeName());
						cadreInfo.setPartyCommittee(cadre.getDesignation().getPartyWorkingCommittee().getPartyWorkingCommitteeId());
				    }
					if(cadre.getEffectiveDate() != null)
						cadreInfo.setEffectiveDate(sdf.format(cadre.getEffectiveDate()));
					if(cadre.getEndingDate() != null)
						cadreInfo.setEndingDate(sdf.format(cadre.getEndingDate()));
					// set cadre skills
					List<CadreSkills> cadreSkillsList=cadreSkillsDAO.findByCadreId(cadre.getCadreId());
					List<Long> cadreSkillsIds = new ArrayList<Long>();
					String[] cadreSkills = new String[cadreSkillsList.size()];
					if(cadreSkillsList != null && cadreSkillsList.size()>0)
					{	
						log.debug("skills list size:"+cadreSkillsList.size());
						int j=0;
						for(CadreSkills obj: cadreSkillsList){
							cadreSkillsIds.add(obj.getCadreSkillId().longValue());
							cadreSkills[j] = obj.getPartyCadreSkills().getSkill();							
							j++;
						}
					}
					cadreInfo.setSkills(cadreSkillsIds);
					cadreInfo.setCadreSkillsNames(cadreSkills);
					//set cadre Participated training camps
					List<CadreParticipatedTrainingCamps> result=cadreParticipatedTrainingCampsDAO.findByCadreId(cadre.getCadreId());
					List<SelectOptionVO> trainingCamps = new ArrayList<SelectOptionVO>(); 
					List<Long> trainingCampIds = new ArrayList<Long>();
					String[] participatedTrainingCampNames = new String[result.size()];
					
					int k = 0;
					for(CadreParticipatedTrainingCamps obj: result){
						SelectOptionVO optionVO = new SelectOptionVO();
						optionVO.setId(obj.getPartyTrainingCamps().getPartyTrainingCampsId());
						optionVO.setName(obj.getPartyTrainingCamps().getRegionLevel());
						trainingCamps.add(optionVO);
						trainingCampIds.add(obj.getPartyTrainingCamps().getPartyTrainingCampsId());
						participatedTrainingCampNames[k] = obj.getPartyTrainingCamps().getRegionLevel();
						k++;						
					}
					cadreInfo.setSelectedTrainingCamps(trainingCamps);
					cadreInfo.setTrainingCamps(trainingCampIds);
					cadreInfo.setCadreParticipatedCampNames(participatedTrainingCampNames);					
			}
		}	
		return cadreInfo;
	}

	private String getHamletName(Long hamletId) {
		Hamlet hamlet = hamletDAO.get(hamletId);
		return hamlet.getHamletName();
	}

	private String getTownshipName(Long townshipId) {
		Township township = townshipDAO.get(townshipId);
		return township.getTownshipName();
	}
	
	private String getLocalElectionBodyName(Long localElectionBodyId)
	{
		LocalElectionBody localElectionBody = localElectionBodyDAO.get(localElectionBodyId);
		return localElectionBody.getName();
	}
	
	private String getBoothDetailsByBoothId(Long boothId)
	{
		Booth booth = boothDAO.get(boothId);
		String boothDetails = "Booth No "+booth.getPartNo()+booth.getLocation(); 
		return boothDetails;
	}

	public List<SelectOptionVO> getStateDistConstituencyMandalByMandalID(
			Long mandalID) {

		List stateDistConstMandal = delimitationConstituencyMandalDAO
				.getStateDistConstituencyMandalByMandalID(mandalID);
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		SelectOptionVO state = new SelectOptionVO();
		SelectOptionVO district = new SelectOptionVO();
		SelectOptionVO constituency = new SelectOptionVO();
		SelectOptionVO mandal = new SelectOptionVO();
		Object[] objVO = (Object[]) stateDistConstMandal.get(0);

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

	public List<SelectOptionVO> getUserAccessStates(Long userID) {
		List states = cadreDAO.getUserAccessStates(userID);
		List<SelectOptionVO> results = dataFormatTo_SelectOptionVO(states);
		return results;
	}

	public List<SelectOptionVO> getUserAccessDistricts(Long userID) {
		List districts = cadreDAO.getUserAccessDistricts(userID);
		List<SelectOptionVO> results = dataFormatTo_SelectOptionVO(districts);
		return results;
	}

	public List<SelectOptionVO> getUserAccessMLAConstituencies(Long userID) {
		List constituencies = cadreDAO.getUserAccessMLAConstituencies(userID);
		List<SelectOptionVO> results = dataFormatTo_SelectOptionVO(constituencies);
		return results;
	}

	public List<SelectOptionVO> getUserAccessMandals(Long userID) {
		List mandals = cadreDAO.getUserAccessMandals(userID);
		List<SelectOptionVO> results = dataFormatTo_SelectOptionVO(mandals);
		return results;
	}

	public List<SelectOptionVO> getStateDistrictByDistrictID(Long districtID) {
		List<SelectOptionVO> results = new ArrayList<SelectOptionVO>();
		District district = districtDAO.get(districtID);
		State state = district.getState();
		SelectOptionVO stateObj = new SelectOptionVO(state.getStateId(), state
				.getStateName());
		SelectOptionVO districtObj = new SelectOptionVO(district
				.getDistrictId(), district.getDistrictName());
		results.add(stateObj);
		results.add(districtObj);
		return results;
	}

	public List<SelectOptionVO> getStateDistricConstituencytByConstituencyID(
			Long constituencyID) {
		Constituency constituency = constituencyDAO.get(constituencyID);
		List<SelectOptionVO> results = new ArrayList<SelectOptionVO>();

		State state = constituency.getState();
		District district = constituency.getDistrict();

		SelectOptionVO stateObj = new SelectOptionVO(state.getStateId(), state
				.getStateName());
		SelectOptionVO districtObj = new SelectOptionVO(district
				.getDistrictId(), district.getDistrictName());
		SelectOptionVO constituencyObj = new SelectOptionVO(constituency
				.getConstituencyId(), constituency.getName());
		results.add(stateObj);
		results.add(districtObj);
		results.add(constituencyObj);
		return results;
	}

	public SmsResultVO sendSMSMessage(Long userID, String type, Long value,
			String message, String includeCadreName) {
		if (log.isDebugEnabled())
			log.debug("CadreManagementService.sendSMSMEssage():userID:"
					+ userID + ":type:" + type + ":value:" + value
					+ ":message:" + message);
		List<Object> list = new ArrayList<Object>();

		if ("STATE".equals(type)) {
			list = cadreDAO.getMobileNosByState(userID, value);
		} else if ("DISTRICT".equals(type)) {
			list = cadreDAO.getMobileNosByDistrict(userID, value);
		} else if ("CONSTITUENCY".equals(type)) {
			list = cadreDAO.getMobileNosByConstituency(userID, value);
		} else if ("MANDAL".equals(type)) {
			list = cadreDAO.getMobileNosByMandal(userID, value);
		} else if ("VILLAGE".equals(type)) {
			String village = value.toString();

			if (IConstants.TOWNSHIP_TYPE.equals(village.substring(0, 1))) {
				log.debug("TownshipID::::" + new Long(village.substring(1)));
				list = cadreDAO.getMobileNosByVillage(userID, new Long(village
						.substring(1)));
			} else {
				log.debug("HamletID::::" + new Long(village.substring(1)));
				list = cadreDAO.getMobileNosByHamlet(userID, new Long(village
						.substring(1)));
			}
		} else if ("CADRE_LEVEL".equals(type)) {
			list = cadreDAO.getMobileNosByCadreLevel(userID, value);
		}
		Integer mobileNos = 0;
		Long remainingSMS = smsCountrySmsService
				.getRemainingSmsLeftForUser(userID)
				- list.size();

		SmsResultVO resultVo = new SmsResultVO();
		if (remainingSMS < 0) {
			resultVo.setStatus(1l);
			resultVo.setTotalSmsSent(0l);
			resultVo.setRemainingSmsCount(0l);
		} else {
			// PartyAnalystPropertyService propertyService = new
			// PartyAnalystPropertyService();
			// smsCountrySmsService.setPropertyService(propertyService);
			if ("NO".equals(includeCadreName)) {
				String[] cadreMobileNos = new String[list.size()];
				int i = -1;
				for (Object mobileInfo : list) {
					Object[] mobile = (Object[]) mobileInfo;
					cadreMobileNos[++i] = mobile[0].toString();
				}
				if (cadreMobileNos != null && cadreMobileNos.length > 0)
					mobileNos = cadreMobileNos.length;
				smsCountrySmsService.sendSms(message, true, userID,
						IConstants.Cadre_Management, cadreMobileNos);
			} else {
				// to do ICONSTANTS.SMS_DEAR
				for (Object mobiles : list) {
					Object[] mobileInfo = (Object[]) mobiles;
					String mobile = mobileInfo[0].toString();
					StringBuilder cadreMessage = new StringBuilder(
							IConstants.SMS_DEAR);
					cadreMessage.append(mobileInfo[1].toString()).append(
							IConstants.SPACE).append(mobileInfo[2].toString())
							.append(IConstants.SPACE).append(message);
					/*
					 * String[] mobileNOs = new String[1]; mobileNOs[1] =
					 * mobile;
					 */
					smsCountrySmsService.sendSms(cadreMessage.toString(), true,
							userID, IConstants.Cadre_Management, mobile);
					mobileNos = mobileNos + 1;
				}
			}
			resultVo.setStatus(0l);
			resultVo.setTotalSmsSent(Long.parseLong(new Integer(list.size())
					.toString()));
			resultVo.setRemainingSmsCount(remainingSMS);
		}

		return resultVo;
	}

	/**
	 * retrieving the ids and names for the region(District, Tehsil,
	 * Township/Hamlet)
	 * 
	 * @param userID
	 * @param id
	 * @param region
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> findRegionByCadreScope(Long userID, Long id,
			String region) {
		List list = new ArrayList();
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		/*if ("MANDAL".equals(region)) {
			list = cadreDAO.findVillageCadresByMandal(id, userID,
					IConstants.CADRE_MEMBER_TYPE_ACTIVE);
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			*/
			/*int size = list.size();
			StringBuilder revenueVillageIDs = new StringBuilder();
			for (int i = 0; i < size; i++) {
				Object[] obj = (Object[]) list.get(i);
				// Township constists of 2 types: township and revenue village
				if ("T".equals(obj[3].toString())) {
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId(new Long(IConstants.TOWNSHIP_TYPE
							+ obj[0].toString()));
					selectOptionVO.setName(obj[1].toString());
					result.add(selectOptionVO);
				} else {
					revenueVillageIDs.append(",").append(obj[0].toString());
				}
			}
			// retrieving hamlets from the revenue villages
			if (revenueVillageIDs.length() > 0) {
				List hamlets = cadreDAO.getCadreSizeByHamlet(revenueVillageIDs
						.toString().substring(1), userID,
						IConstants.CADRE_MEMBER_TYPE_ACTIVE);
				int hamletSize = hamlets.size();
				for (int i = 0; i < hamletSize; i++) {
					Object[] obj = (Object[]) hamlets.get(i);
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId(new Long(IConstants.HAMLET_TYPE
							+ obj[0].toString()));
					selectOptionVO.setName(obj[1].toString());
					result.add(selectOptionVO);
				}
			}
*/
	 
			SelectOptionVO selectOptionVO = new SelectOptionVO(0L,
					"Select District");
			if ("STATE".equals(region)) {
				list = cadreDAO.findDistCadresByState(id, userID,
						IConstants.CADRE_MEMBER_TYPE_ACTIVE);
			} else if ("DISTRICT".equals(region)) {
				list = cadreDAO.findConstituencyCadresByDist(id, userID,
						IConstants.CADRE_MEMBER_TYPE_ACTIVE);
				selectOptionVO.setName("Select Constituency");
			} else if ("CONSTITUENCY".equals(region)) {
				list = cadreDAO.findMandalCadresByConstituency(id, userID,
						IConstants.CADRE_MEMBER_TYPE_ACTIVE);
				selectOptionVO.setName("Select Mandal");
			}
			result = dataFormatTo_SelectOptionVO(list);
			result.add(0, selectOptionVO);
		return result;
	}

	public List<SelectOptionVO> dataFormatTo_SelectOptionVO(List list) {
		List<SelectOptionVO> formattedData = new ArrayList<SelectOptionVO>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(new Long(obj[0].toString()));
			selectOptionVO.setName(obj[1].toString());
			formattedData.add(selectOptionVO);
		}
		return formattedData;
	}

	private List<SelectOptionVO> getTownshipHamlets(List<Township> townships) {
		log
				.debug("CadreManagementService.getTownshipHamlets() townships.size()="
						+ townships.size());
		List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
		for (Township township : townships) {
			if ("V".equalsIgnoreCase(township.getTownshipType())) {
				Set<Hamlet> hamlets = township.getHamlets();
				for (Hamlet hamlet : hamlets) {
					SelectOptionVO obj = new SelectOptionVO();
					obj.setId(new Long(IConstants.HAMLET_TYPE
							+ hamlet.getHamletId()));
					obj.setName(hamlet.getHamletName());
					result.add(obj);
				}
			} else {
				SelectOptionVO obj = new SelectOptionVO();
				obj.setId(new Long(IConstants.TOWNSHIP_TYPE
						+ township.getTownshipId()));
				obj.setName(township.getTownshipName());
				result.add(obj);
			}
		}
		return result;
	}

	public List<CadreInfo> getCadresByHamlet(String id, Long userID) {
		
		List<CadreInfo> formattedData = new ArrayList<CadreInfo>();
		List<Cadre> cadresList = new ArrayList<Cadre>();
		
		if (IConstants.RURAL_TYPE.equals(id.substring(0,1)))
		{
			cadresList = cadreDAO.findCadresByHamlet(new Long(id.substring(1)), userID,IConstants.CADRE_MEMBER_TYPE_ACTIVE);
			
			if(cadresList == null || cadresList.size() == 0)
				cadresList = cadreDAO.findCadresByTownship(new Long(id.substring(1)), userID,IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		}
		if (IConstants.URBAN_TYPE.equals(id.substring(0,1)))
		{
			cadresList = cadreDAO.getCadresByWard(new Long(id.substring(1)), userID,IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		}
		for (Cadre cadre : cadresList) {
			CadreInfo cadreInfo = convertCadreToCadreInfo(cadre);
			formattedData.add(cadreInfo);
		}
		return formattedData;
	}

	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getCadreSizeByHamlet(Long revenueVillageID,
			Long userID) {
		List hamletCadres = cadreDAO.getCadreSizeByHamlet(revenueVillageID.toString(), userID, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = hamletCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for (int i = 0; i < size; i++) {
			Object[] voObject = (Object[]) hamletCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("HAMLET");
			regionInfoVo.setRegionId(new Long(voObject[0].toString()));
			regionInfoVo.setRegionName(voObject[1].toString());
			regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;
	}

	public List<StateToHamletVO> getStateToHamletByHamlets(String hamletIDs) {
		// 0-stateId, 1-stateName, 2-districtId, 3-districtName, 4-tehsilId,
		// 5-tehsilName, 6-townshipId,7-townshipName,8-hamletName,9-hamletName
		List<StateToHamletVO> result = new ArrayList<StateToHamletVO>();
		List list = hamletDAO.getStateToHamletByHamlets(hamletIDs);
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			SelectOptionVO state = new SelectOptionVO((Long) obj[0], obj[1]
					.toString());
			SelectOptionVO district = new SelectOptionVO((Long) obj[2], obj[3]
					.toString());
			SelectOptionVO mandal = new SelectOptionVO((Long) obj[4], obj[5]
					.toString());
			SelectOptionVO revenueVillage = new SelectOptionVO((Long) obj[6],
					obj[7].toString());
			SelectOptionVO hamlet = new SelectOptionVO((Long) obj[8], obj[9]
					.toString());

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
	
	public List<StateToHamletVO> getVillageToBoothByBooths(String boothIds) {
		// 0-tehsilId, 1-tehsilName, 2-boothId, 3 -partNo, 4-location, 5-village_covered
		List<StateToHamletVO> result = new ArrayList<StateToHamletVO>();
		List list = boothDAO.getVillageToBoothByBooths(boothIds);
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			SelectOptionVO mandal = new SelectOptionVO((Long) obj[0], obj[1].toString());
			SelectOptionVO booth = new SelectOptionVO(Long.parseLong(obj[3].toString()) , obj[4].toString());
			StateToHamletVO vo = new StateToHamletVO();
			vo.setMandal(mandal);
			vo.setBooth(booth);
			result.add(vo);
		}
		return result;
	}
	
	public List<StateToHamletVO> getLocalElectionBodyToBoothByBooths(String ids) {
		// 0-localBodyId, 1-localBodyName, 2-boothId, 3 -partNo, 4-location, 5-village_covered
		List<StateToHamletVO> result = new ArrayList<StateToHamletVO>();
		List list = boothDAO.getLocalElectionBodyToBoothByBooths(ids);
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			SelectOptionVO localElectionBody = new SelectOptionVO((Long) obj[0], obj[1].toString());
			SelectOptionVO booth = new SelectOptionVO(Long.parseLong(obj[3].toString()), obj[4].toString());
			StateToHamletVO vo = new StateToHamletVO();
			vo.setLocalElectionBody(localElectionBody);
			vo.setBooth(booth);
			result.add(vo);
		}
		return result;
	}


	public List<StateToHamletVO> getStateToRevenueVillageByRV(String rvIDs) {
		// 0-stateId, 1-stateName, 2-districtId, 3-districtName, 4-tehsilId,
		// 5-tehsilName, 6-townshipId,7-townshipName
		List<StateToHamletVO> result = new ArrayList<StateToHamletVO>();
		List list = townshipDAO.getStateToRevenueVillageByRV(rvIDs);
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			SelectOptionVO state = new SelectOptionVO((Long) obj[0], obj[1]
					.toString());
			SelectOptionVO district = new SelectOptionVO((Long) obj[2], obj[3]
					.toString());
			SelectOptionVO mandal = new SelectOptionVO((Long) obj[4], obj[5]
					.toString());
			SelectOptionVO revenueVillage = new SelectOptionVO((Long) obj[6],
					obj[7].toString());

			StateToHamletVO vo = new StateToHamletVO();
			vo.setState(state);
			vo.setDistrict(district);
			vo.setMandal(mandal);
			vo.setRevenueVillage(revenueVillage);
			result.add(vo);
		}
		return result;
	}

	public List<StateToHamletVO> getStateToMandalByTehsil(String tehsilIDs) {
		// 0-stateId, 1-stateName, 2-districtId, 3-districtName, 4-tehsilId,
		// 5-tehsilName
		List<StateToHamletVO> result = new ArrayList<StateToHamletVO>();
		List list = tehsilDAO.getStateToMandalByTehsil(tehsilIDs);
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			SelectOptionVO state = new SelectOptionVO((Long) obj[0], obj[1]
					.toString());
			SelectOptionVO district = new SelectOptionVO((Long) obj[2], obj[3]
					.toString());
			SelectOptionVO mandal = new SelectOptionVO((Long) obj[4], obj[5]
					.toString());

			StateToHamletVO vo = new StateToHamletVO();
			vo.setState(state);
			vo.setDistrict(district);
			vo.setMandal(mandal);
			result.add(vo);
		}
		return result;
	}

	public List<StateToHamletVO> getStateToDistrictByDistrict(String districtIDs) {
		// 0-stateId, 1-stateName, 2-districtId, 3-districtName
		List<StateToHamletVO> result = new ArrayList<StateToHamletVO>();
		List list = districtDAO.getStateToDistrictByDistrict(districtIDs);
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			SelectOptionVO state = new SelectOptionVO((Long) obj[0], obj[1]
					.toString());
			SelectOptionVO district = new SelectOptionVO((Long) obj[2], obj[3]
					.toString());

			StateToHamletVO vo = new StateToHamletVO();
			vo.setState(state);
			vo.setDistrict(district);
			result.add(vo);
		}
		return result;
	}
	
	public List<StateToHamletVO> getStateToConstituencyByConstituency(String constituencyIds){
		// 0-stateId, 1-stateName, 2-districtId, 3-districtName, 4-constituencyId, 5-constituencyName
		List<StateToHamletVO> result = new ArrayList<StateToHamletVO>();
		List list = constituencyDAO.getStateToConstituencyByConstituency(constituencyIds);
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			SelectOptionVO state = new SelectOptionVO((Long) obj[0], obj[1].toString());
			SelectOptionVO district = new SelectOptionVO((Long) obj[2], obj[3].toString());
			SelectOptionVO constituency = new SelectOptionVO((Long) obj[4], obj[5].toString());
			
			StateToHamletVO vo = new StateToHamletVO();
			vo.setState(state);
			vo.setDistrict(district);
			vo.setConstituency(constituency);
			result.add(vo);
		}
		return result;		
	}
	
	
	public List<StateToHamletVO> getStateToWardByWard(String wardIds){
		// 0-stateId, 1-stateName, 2-districtId, 3-districtName, 4-localElectionBodyId, 5-localElectionBodyName
		List<StateToHamletVO> result = new ArrayList<StateToHamletVO>();
		List list = constituencyDAO.getStateToWardByWard(wardIds);
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			SelectOptionVO state = new SelectOptionVO((Long) obj[0], obj[1].toString());
			SelectOptionVO district = new SelectOptionVO((Long) obj[2], obj[3].toString());
			SelectOptionVO localElectionBody = new SelectOptionVO((Long) obj[4], obj[5].toString());
			SelectOptionVO ward = new SelectOptionVO((Long) obj[6], obj[7].toString());
			StateToHamletVO vo = new StateToHamletVO();
			vo.setState(state);
			vo.setDistrict(district);
			vo.setLocalElectionBody(localElectionBody);
			vo.setWard(ward);
			result.add(vo);
		}
		return result;		
	}
	
	public List<StateToHamletVO> getStateToLocalElectionBodyByLEB(String localElectionBodyIds)
	{
		// 0-stateId, 1-stateName, 2-districtId, 3-districtName, 4-local election body id, 5-name
		List<StateToHamletVO> result = new ArrayList<StateToHamletVO>();
		List list = localElectionBodyDAO.getStateToLocalElectionBodyByLEB(localElectionBodyIds);
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			SelectOptionVO state = new SelectOptionVO((Long) obj[0], obj[1].toString());
			SelectOptionVO district = new SelectOptionVO((Long) obj[2], obj[3].toString());
			SelectOptionVO localElectionBody = new SelectOptionVO((Long) obj[4], obj[5].toString());
			
			StateToHamletVO vo = new StateToHamletVO();
			vo.setState(state);
			vo.setDistrict(district);
			vo.setLocalElectionBody(localElectionBody);
			result.add(vo);
		}
		return result;
	}

public List<SelectOptionVO> getCommitteesForAParty(Long partyId)
	{
		List<SelectOptionVO> resultsList = new ArrayList<SelectOptionVO>(0);
		List<PartyWorkingCommittee> results =  partyWorkingCommitteeDAO.getWorkingCommitteeForParty(partyId);
		
		if(results != null && results.size() > 0){
			
			resultsList = new ArrayList<SelectOptionVO>();
			
			for(PartyWorkingCommittee partyWorkingCommittee:results)
			{
				SelectOptionVO selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId(partyWorkingCommittee.getPartyWorkingCommitteeId());
				selectOptionVO.setName(partyWorkingCommittee.getCommitteeName());
				resultsList.add(selectOptionVO);
			}
	    }
		return resultsList;
	}

	public List<SelectOptionVO> getDesignationsInCommittee(Long partyWorkingCommitteeId) {
		List<SelectOptionVO> resultsList = new ArrayList<SelectOptionVO>();
		List<PartyWorkingCommitteeDesignation> results = partyWorkingCommitteeDesignationDAO
				.getDesignationsForPartyCommittee(partyWorkingCommitteeId);
		for (PartyWorkingCommitteeDesignation PartyWorkingCommitteeDesignation : results) {
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(PartyWorkingCommitteeDesignation
					.getPartyWkgCommitteeDesignationId());
			selectOptionVO.setName(PartyWorkingCommitteeDesignation
					.getDesignation());
			resultsList.add(selectOptionVO);
		}
		return resultsList;
	}

		
	public List<SelectOptionVO> getPartyCadreSkills(Long partyId)
	{
		List<SelectOptionVO> cadreSkillsList = new ArrayList<SelectOptionVO>(0);
		List<PartyCadreSkills> cadreSkills = partyCadreSkillsDAO.getCadreSkillsPartywise(partyId); 
		if(cadreSkills != null && cadreSkills.size() > 0){
			cadreSkillsList = new ArrayList<SelectOptionVO>();
			for(PartyCadreSkills cadreSkill : cadreSkills)
			{
				SelectOptionVO selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId(cadreSkill.getPartyCadreSkillId());
				selectOptionVO.setName(cadreSkill.getSkill());
				cadreSkillsList.add(selectOptionVO);
			}
		}
		return cadreSkillsList;			
	}
	
	public List<SelectOptionVO> getCadreRoles()
	{
		List<SelectOptionVO> cadreRolesList = new ArrayList<SelectOptionVO>(0);
		List<Object[]> rolesList = cadreRoleDAO.getCadreRoles(); 
		if(rolesList != null && rolesList.size() > 0){
			cadreRolesList = new ArrayList<SelectOptionVO>();
			for(Object[] params : rolesList)
			{
				SelectOptionVO selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId((Long)params[0]);
				selectOptionVO.setName(params[1].toString());
				cadreRolesList.add(selectOptionVO);
			}
		}
		return cadreRolesList;			
	}

		
	public List<SelectOptionVO> getPartyTrainingCamps(Long partyId)
	{
		
		List<SelectOptionVO> trainingCampsList = new ArrayList<SelectOptionVO>(0);
		List<PartyTrainingCamps> trainingCamps = partyTrainingCampsDAO.getTrainingCampsPartywise(partyId);
		if(trainingCamps != null && trainingCamps.size() > 0){
		trainingCampsList = new ArrayList<SelectOptionVO>();
			for(PartyTrainingCamps partyTrainingCamp:trainingCamps)
			{
				SelectOptionVO selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId(partyTrainingCamp.getPartyTrainingCampsId());
				selectOptionVO.setName(partyTrainingCamp.getRegionLevel());
				trainingCampsList.add(selectOptionVO);
			}
		}
		return trainingCampsList;
			
	}
	/*
	 * Method to check wheather a party has commitee or not Returns a Boolean
	 * value
	 */
	public Boolean checkForAPartyCommiteeDetails(Long partyId) {

		Boolean result = false;
		List<PartyWorkingCommittee> commiteeList = partyWorkingCommitteeDAO
				.getWorkingCommitteeForParty(partyId);
		if (commiteeList != null && commiteeList.size() > 0)
			result = true;
		return result;
	}

	/*
	 * Method to get all designations of party working commitee
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getAllDesignationsOfPartyWorkingCommitee() {

		List<SelectOptionVO> resultsList = null;
		List designationsList = partyWorkingCommitteeDesignationDAO
				.getAllDesignationsForPartyCommitee();
		if (designationsList != null && designationsList.size() > 0) {

			resultsList = new ArrayList<SelectOptionVO>();
			Iterator desigIterator = designationsList.listIterator();

			while (desigIterator.hasNext()) {
				Object params = (Object) desigIterator.next();
				SelectOptionVO value = new SelectOptionVO();
				value.setName((String) params);

				resultsList.add(value);
			}

		}
		return resultsList;
	}

	/*
	 * Method to Obtain Cadre details based on a search criteria
	 */
	public List<CadreInfo> getCadreDetailsBySearchCriteria(Long userId,
			PartyCadreDetailsVO cadreInputVO) {

		if (log.isDebugEnabled())
			log.debug("Inside getCadreDetailsBySearchCriteria Method ");

		List<CadreInfo> cadreOutputResultVO = new ArrayList<CadreInfo>();
		ResultStatus resultStatus = new ResultStatus();

		if (userId != null && cadreInputVO != null) {

			try {
				// process for results
				List<Cadre> cadreObjects = getCadreSearchResultsByInputCriteria(
						userId, cadreInputVO);
				if (cadreObjects != null && cadreObjects.size() > 0)
					cadreOutputResultVO = putCadreSearchResultsInVO(cadreObjects);

			} catch (Exception ex) {
				ex.printStackTrace();
				CadreInfo cadreInfo = new CadreInfo();
				resultStatus.setExceptionEncountered(ex);
				resultStatus.setResultPartial(true);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);

				resultStatus.setExceptionClass(ex.getClass().toString());
				resultStatus.setExceptionMsg(getExceptionMessage(ex.getClass()
						.toString()));

				cadreInfo.setResultStatus(resultStatus);
				cadreOutputResultVO.add(cadreInfo);
				return cadreOutputResultVO;
			}

		}

		return cadreOutputResultVO;
    }


/*    
	 * Method to get cadre details by cadre type
	 * 
	 
	@SuppressWarnings("unchecked")
	public List getCadreByCadreType(String type,List<Long> cadreIds){
		
		List cadreTypeObjList = null;
		if(cadreIds != null && cadreIds.size() > 0)
		cadreTypeObjList = cadreDAO.findCadreIdsByMemberTypeAndCadreList(type,cadreIds, );
		
	 return cadreTypeObjList;

		
	}*/

	/*
	 * Method to Obtain Cadre Objects based on a search criteria
	 */
	@SuppressWarnings("unchecked")
	public List<Cadre> getCadreSearchResultsByInputCriteria(Long userId,
			PartyCadreDetailsVO cadreInputVO) throws Exception {

		if (log.isDebugEnabled())
			log.debug("Inside getCadreDetailsBySearchCriteria Method ");

		List<Cadre> cadreObjects = null;
		List<Long> cadreIds = null;
		Map<Long, Long> cadreIdsMap = new HashMap<Long, Long>();
		String query = null;
		if(cadreInputVO.getGenderSearchType().equals("Male"))
			query = "and model.gender = 'Male'";
		if(cadreInputVO.getGenderSearchType().equals("Female"))
			query = "and model.gender = 'Female'";
		if(cadreInputVO.getGenderSearchType().equals("allGenders"))
			query = "";	
		
		if (userId != null && cadreInputVO != null) {

			// Location Based Or Level Based Search
			if (cadreInputVO.getSearchType().equals(IConstants.LOCATION_BASED)) {
				cadreIds = getLocationBasedSearch(userId, cadreInputVO
						.getCadreLevelId(), cadreInputVO.getCadreLocationId(), query);
			} else if (cadreInputVO.getSearchType().equals(
					IConstants.LEVEL_BASED)) {
				cadreIds = getLevelBasedSearch(userId, cadreInputVO
						.getCadreLevelId(), query);
			}

			// search by CadreType
			if (cadreInputVO.getCadreType().equals(
					IConstants.CADRE_MEMBER_TYPE_ACTIVE)
					|| cadreInputVO.getCadreType().equals(
							IConstants.CADRE_MEMBER_TYPE_NORMAL)) {
				List cadreTypeObjList = null;
				if (cadreIds != null && cadreIds.size() > 0)
					cadreTypeObjList = cadreDAO.findCadreIdsByMemberTypeAndCadreList(cadreInputVO
									.getCadreType(), cadreIds, query);

				if (cadreTypeObjList != null)
					cadreIds = getProcessedObjects(cadreTypeObjList);
			}

			// for Or search
			if (cadreInputVO.getIsOrSearch()) {
				if (cadreIds != null && cadreIds.size() > 0)
					cadreIdsMap = setResultCadreIdsToMap(cadreIdsMap, cadreIds);
			}

			// search by social skills
			if (cadreInputVO.getIsSocialStatus()) {

				String paramObject = "";
				String paramField = "";

				// Education
				if (cadreInputVO.getCadreEducationQualification() != null) {
					paramObject = "education";
					paramField = "eduQualificationId";
					List cadreTypeObjList = null;
					List<Long> eduList = new ArrayList<Long>();
					Boolean allCategoriesFlag = false;

					for (CadreCategoryVO eduCategory : cadreInputVO
							.getCadreEducationQualification()) {
						if (eduCategory.getCadreCategoryId()
								.equals(new Long(0))) {
							allCategoriesFlag = true;
							break;
						} else {
							eduList.add(eduCategory.getCadreCategoryId());
						}
					}
					if (!allCategoriesFlag && eduList.size() > 0) {
						if (cadreInputVO.getIsOrSearch()) {
							// cadreTypeObjList =
							// cadreDAO.findCadreByPropertyValueAndUser(userId,
							// paramObject,paramField,cadreInputVO.getCadreEducationQualification().getCadreCategoryId());
							cadreTypeObjList = cadreDAO
									.findCadreByPropertyValueListAndUser(
											userId, paramObject, paramField,
											eduList,query);
							if (cadreTypeObjList != null) {
								List<Long> cadreIDs = getProcessedObjects(cadreTypeObjList);
								cadreIdsMap = setResultCadreIdsToMap(
										cadreIdsMap, cadreIDs);
							}
						} else if (cadreIds != null && cadreIds.size() > 0) {
							// cadreTypeObjList =
							// cadreDAO.findCadreByPropertyValueAndCadreIds(paramObject,paramField,cadreInputVO.getCadreEducationQualification().getCadreCategoryId(),cadreIds);
							cadreTypeObjList = cadreDAO
									.findCadreByPropertyValueListAndCadreIds(
											paramObject, paramField, eduList,
											cadreIds, query);
							if (cadreTypeObjList != null)
								cadreIds = getProcessedObjects(cadreTypeObjList);
						}
					}

				}

				// Caste
				if (cadreInputVO.getCadreCasteCategory() != null) {
					paramObject = "casteCategory";
					paramField = "socialCategoryId";
					List cadreTypeObjList = null;
					List<Long> casList = new ArrayList<Long>();
					Boolean allCategoriesFlag = false;

					for (CadreCategoryVO casCategory : cadreInputVO
							.getCadreCasteCategory()) {
						if (casCategory.getCadreCategoryId()
								.equals(new Long(0))) {
							allCategoriesFlag = true;
							break;
						} else {
							casList.add(casCategory.getCadreCategoryId());
						}
					}

					if (!allCategoriesFlag && casList.size() > 0) {
						if (cadreInputVO.getIsOrSearch()) {
							// cadreTypeObjList =
							// cadreDAO.findCadreByPropertyValueAndUser(userId,
							// paramObject,paramField,cadreInputVO.getCadreCasteCategory().getCadreCategoryId());
							cadreTypeObjList = cadreDAO
									.findCadreByPropertyValueListAndUser(
											userId, paramObject, paramField,
											casList, query);
							if (cadreTypeObjList != null) {
								List<Long> cadreIDs = getProcessedObjects(cadreTypeObjList);
								cadreIdsMap = setResultCadreIdsToMap(
										cadreIdsMap, cadreIDs);
							}
						} else if (cadreIds != null && cadreIds.size() > 0) {
							// cadreTypeObjList =
							// cadreDAO.findCadreByPropertyValueAndCadreIds(paramObject,paramField,cadreInputVO.getCadreCasteCategory().getCadreCategoryId(),cadreIds);
							cadreTypeObjList = cadreDAO
									.findCadreByPropertyValueListAndCadreIds(
											paramObject, paramField, casList,
											cadreIds, query);
							if (cadreTypeObjList != null)
								cadreIds = getProcessedObjects(cadreTypeObjList);
						}
					}
				}

				// Ocupation
				if (cadreInputVO.getCadreOccupation() != null) {
					paramObject = "occupation";
					paramField = "occupationId";
					List cadreTypeObjList = null;
					List<Long> ocupList = new ArrayList<Long>();
					Boolean allCategoriesFlag = false;

					for (CadreCategoryVO ocupCategory : cadreInputVO
							.getCadreOccupation()) {
						if (ocupCategory.getCadreCategoryId().equals(
								new Long(0))) {
							allCategoriesFlag = true;
							break;
						} else {
							ocupList.add(ocupCategory.getCadreCategoryId());
						}
					}

					if (!allCategoriesFlag && ocupList.size() > 0) {
						if (cadreInputVO.getIsOrSearch()) {
							// cadreTypeObjList =
							// cadreDAO.findCadreByPropertyValueAndUser(userId,
							// paramObject,paramField,cadreInputVO.getCadreOccupation().getCadreCategoryId());
							cadreTypeObjList = cadreDAO
									.findCadreByPropertyValueListAndUser(
											userId, paramObject, paramField,
											ocupList, query);
							if (cadreTypeObjList != null) {
								List<Long> cadreIDs = getProcessedObjects(cadreTypeObjList);
								cadreIdsMap = setResultCadreIdsToMap(
										cadreIdsMap, cadreIDs);
							}
						} else if (cadreIds != null && cadreIds.size() > 0) {
							// cadreTypeObjList =
							// cadreDAO.findCadreByPropertyValueAndCadreIds(paramObject,paramField,cadreInputVO.getCadreOccupation().getCadreCategoryId(),cadreIds);
							cadreTypeObjList = cadreDAO
									.findCadreByPropertyValueListAndCadreIds(
											paramObject, paramField, ocupList,
											cadreIds, query);
							if (cadreTypeObjList != null)
								cadreIds = getProcessedObjects(cadreTypeObjList);
						}
					}
				}
			}

			// Search by committee
			if (cadreInputVO.getCadreWorkingCommittee() != null) {
				List cadreTypeObjList = null;
				List<Long> commList = new ArrayList<Long>();
				Boolean allCategoriesFlag = false;

				for (CadreCategoryVO commCategory : cadreInputVO
						.getCadreWorkingCommittee()) {
					if (commCategory.getCadreCategoryId().equals(new Long(0))) {
						allCategoriesFlag = true;
						break;
					} else {
						commList.add(commCategory.getCadreCategoryId());
					}
				}

				if (!allCategoriesFlag && commList.size() > 0) {
					if (cadreInputVO.getIsOrSearch()) {
						// cadreTypeObjList =
						// cadreDAO.findCadreByPartyWorkingCommitteeAndUser(userId,
						// cadreInputVO.getCadreWorkingCommittee().getCadreCategoryId());
						cadreTypeObjList = cadreDAO
								.findCadreByPartyWorkingCommitteeListAndUser(
										userId, commList);
						if (cadreTypeObjList != null) {
							List<Long> cadreIDs = getProcessedObjects(cadreTypeObjList);
							cadreIdsMap = setResultCadreIdsToMap(cadreIdsMap,
									cadreIDs);
						}
					} else if (cadreIds != null && cadreIds.size() > 0) {
						// cadreTypeObjList =
						// cadreDAO.findCadreByPartyWorkingCommitteeAndCadreIds(cadreInputVO.getCadreWorkingCommittee().getCadreCategoryId(),
						// cadreIds);
						cadreTypeObjList = cadreDAO
								.findCadreByPartyWorkingCommitteeListAndCadreIds(
										commList, cadreIds);
						if (cadreTypeObjList != null)
							cadreIds = getProcessedObjects(cadreTypeObjList);
					}
				}
			}

			// search by commitee designation
			if (cadreInputVO.getCadreCommitteeDesignation() != null) {
				List cadreTypeObjList = null;
				List<Long> commDesigList = new ArrayList<Long>();
				Boolean allCategoriesFlag = false;

				for (CadreCategoryVO commDesigCategory : cadreInputVO
						.getCadreWorkingCommittee()) {
					if (commDesigCategory.getCadreCategoryId().equals(
							new Long(0))) {
						allCategoriesFlag = true;
						break;
					} else {
						commDesigList.add(commDesigCategory
								.getCadreCategoryId());
					}
				}

				if (!allCategoriesFlag && commDesigList.size() > 0) {
					if (cadreInputVO.getIsOrSearch()) {
						// cadreTypeObjList =
						// cadreDAO.findCadreByPartyWorkingCommitteeDesignationAndUser(userId,
						// cadreInputVO.getCadreCommitteeDesignation().getCadreCategoryId());
						cadreTypeObjList = cadreDAO
								.findCadreByPartyWorkingCommitteeDesignationListAndUser(
										userId, commDesigList);
						if (cadreTypeObjList != null) {
							List<Long> cadreIDs = getProcessedObjects(cadreTypeObjList);
							cadreIdsMap = setResultCadreIdsToMap(cadreIdsMap,
									cadreIDs);
						}
					} else if (cadreIds != null && cadreIds.size() > 0) {
						// cadreTypeObjList =
						// cadreDAO.findCadreByPartyWorkingCommitteeDesignationAndCadreIds(cadreInputVO.getCadreCommitteeDesignation().getCadreCategoryId(),
						// cadreIds);
						cadreTypeObjList = cadreDAO
								.findCadreByPartyWorkingCommitteeDesignationListAndCadreIds(
										commDesigList, cadreIds);
						if (cadreTypeObjList != null)
							cadreIds = getProcessedObjects(cadreTypeObjList);
					}
				}
			}

			// search by cadre skills
			if (cadreInputVO.getCadreSkillSet() != null) {
				List cadreTypeObjList = null;
				List<Long> skillList = new ArrayList<Long>();
				Boolean allCategoriesFlag = false;

				for (CadreCategoryVO skillCategory : cadreInputVO
						.getCadreWorkingCommittee()) {
					if (skillCategory.getCadreCategoryId().equals(new Long(0))) {
						allCategoriesFlag = true;
						break;
					} else {
						skillList.add(skillCategory.getCadreCategoryId());
					}
				}

				if (!allCategoriesFlag && skillList.size() > 0) {
					if (cadreInputVO.getIsOrSearch()) {
						// cadreTypeObjList =
						// cadreSkillsDAO.getCadreIdsByCadreSkillAndUser(userId,
						// cadreInputVO.getCadreSkillSet().getCadreCategoryId());
						cadreTypeObjList = cadreSkillsDAO
								.getCadreIdsByCadreSkillListAndUser(userId,
										skillList);
						if (cadreTypeObjList != null) {
							List<Long> cadreIDs = getProcessedObjects(cadreTypeObjList);
							cadreIdsMap = setResultCadreIdsToMap(cadreIdsMap,
									cadreIDs);
						}
					} else if (cadreIds != null && cadreIds.size() > 0) {
						// cadreTypeObjList =
						// cadreSkillsDAO.getCadreBySkillAndCadreIds(cadreInputVO.getCadreSkillSet().getCadreCategoryId(),
						// cadreIds);
						cadreTypeObjList = cadreSkillsDAO
								.getCadreBySkillListAndCadreIds(skillList,
										cadreIds);
						if (cadreTypeObjList != null)
							cadreIds = getProcessedObjects(cadreTypeObjList);
					}
				}
			}

			// search by cadre training camps
			if (cadreInputVO.getCadreTrainingCamps() != null) {
				List cadreTypeObjList = null;
				List<Long> campList = new ArrayList<Long>();
				Boolean allCategoriesFlag = false;

				for (CadreCategoryVO campCategory : cadreInputVO
						.getCadreWorkingCommittee()) {
					if (campCategory.getCadreCategoryId().equals(new Long(0))) {
						allCategoriesFlag = true;
						break;
					} else {
						campList.add(campCategory.getCadreCategoryId());
					}
				}

				if (!allCategoriesFlag && campList.size() > 0) {
					if (cadreInputVO.getIsOrSearch()) {
						// cadreTypeObjList =
						// cadreParticipatedTrainingCampsDAO.getCadreIdsByCadreCampsAndUser(userId,
						// cadreInputVO.getCadreTrainingCamps().getCadreCategoryId());
						cadreTypeObjList = cadreParticipatedTrainingCampsDAO
								.getCadreIdsByCadreCampsListAndUser(userId,
										campList);
						if (cadreTypeObjList != null) {
							List<Long> cadreIDs = getProcessedObjects(cadreTypeObjList);
							cadreIdsMap = setResultCadreIdsToMap(cadreIdsMap,
									cadreIDs);
						}
					} else if (cadreIds != null && cadreIds.size() > 0) {
						// cadreTypeObjList =
						// cadreParticipatedTrainingCampsDAO.getCadreByCampsAndCadreIds(cadreInputVO.getCadreTrainingCamps().getCadreCategoryId(),
						// cadreIds);
						cadreTypeObjList = cadreParticipatedTrainingCampsDAO
								.getCadreByCampsListAndCadreIds(campList,
										cadreIds);
						if (cadreTypeObjList != null)
							cadreIds = getProcessedObjects(cadreTypeObjList);
					}
				}

			}

			// process cadre ids and obtain cadre objects
			if (cadreInputVO.getIsOrSearch()) {
				if (!cadreIdsMap.isEmpty() && cadreIdsMap.size() > 0) {
					Set<Long> cadreKeys = cadreIdsMap.keySet();
					cadreObjects = getCadreObjectsFromCadreIdsSet(cadreKeys);
				}
			} else if (cadreIds != null && cadreIds.size() > 0) {
				cadreObjects = getCadreObjectsFromCadreIds(cadreIds);
			}

		}

		return cadreObjects;
	}

	/*
	 * Process cadreIds and set to Map
	 */
	public Map<Long, Long> setResultCadreIdsToMap(Map<Long, Long> cadreMap,
			List<Long> cadreIds) {

		if (cadreMap != null && cadreIds != null && cadreIds.size() > 0) {
			for (Long cadreId : cadreIds) {
				if (cadreMap.isEmpty())
					cadreMap.put(cadreId, cadreId);
				else if (!cadreMap.containsKey(cadreId))
					cadreMap.put(cadreId, cadreId);
			}
		}
		return cadreMap;
	}

	/*
	 * Get Cadre Objects From Cadre Ids
	 */
	public List<Cadre> getCadreObjectsFromCadreIds(List<Long> cadreIds) {

		List<Cadre> cadreObjects = null;
		if (cadreIds != null && cadreIds.size() > 0) {

			cadreObjects = new ArrayList<Cadre>();
			for (Long cadreId : cadreIds) {
				Cadre cadre = cadreDAO.get(cadreId);
				cadreObjects.add(cadre);
			}
		}
		return cadreObjects;
	}

	/*
	 * Get Cadre Objects From Cadre Ids
	 */
	public List<Cadre> getCadreObjectsFromCadreIdsSet(Set<Long> cadreIds) {

		List<Cadre> cadreObjects = null;
		if (cadreIds != null && cadreIds.size() > 0) {

			cadreObjects = new ArrayList<Cadre>();
			for (Long cadreId : cadreIds) {
				Cadre cadre = cadreDAO.get(cadreId);
				cadreObjects.add(cadre);
			}
		}
		return cadreObjects;
	}

	/*
	 * Method To get Location Based Search Results.This method is invoked from getCadreSearchResultsByInputCriteria
	 */
	@SuppressWarnings("unchecked")
	public List<Long> getLocationBasedSearch(Long userId, Long levelId,
			Long locationId, String query) {

		List<Long> cadreIds = null;
		
		if (levelId != null && locationId != null) {
				
			String ObjectOne = "", ObjectTwo = "", field = "", id = "";
			ObjectOne = "currentAddress";

			if (levelId.equals(new Long(1))) {
				ObjectTwo = "country";
				field = "countryId";
			} else if (levelId.equals(new Long(2))) {
				ObjectTwo = "state";
				field = "stateId";
			} else if (levelId.equals(new Long(3))) {
				ObjectTwo = "district";
				field = "districtId";
			} else if (levelId.equals(new Long(4))) {
				ObjectTwo = "constituency";
				field = "constituencyId";
			} else if (levelId.equals(new Long(5))) {
				ObjectTwo = "tehsil";
				field = "tehsilId";
				id = locationId.toString().substring(1);
				locationId = new Long(id);
			} else if (levelId.equals(new Long(6))) {
				ObjectTwo = "hamlet";
				field = "hamletId";
				id = locationId.toString().substring(1);
				locationId = new Long(id);
			} else if (levelId.equals(new Long(7))) {
				ObjectTwo = "localElectionBody";
				field = "localElectionBodyId";
				id = locationId.toString().substring(1);
				List localElectionBodies = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id));
				Object object = (Object)localElectionBodies.get(0);
				locationId  = (Long)object;
			} else if (levelId.equals(new Long(8))) {
				ObjectTwo = "ward";
				field = "constituencyId";
				id = locationId.toString().substring(1);
				locationId = new Long(id);
			} else if (levelId.equals(new Long(9))) {
				ObjectTwo = "booth";
				field = "boothId";
			}
			if (!"".equalsIgnoreCase(ObjectOne)
					&& !"".equalsIgnoreCase(ObjectTwo)
					&& !"".equalsIgnoreCase(field)) {
				List cadreObjList = cadreDAO.findCadreDetailsByLevelAndProperty(userId, ObjectOne,
								ObjectTwo, field, locationId,query);
				if (cadreObjList != null && cadreObjList.size() > 0)
					cadreIds = getProcessedObjects(cadreObjList);
			}
		}
		return cadreIds;
	}

	/*
	 * Level based Cadre Search
	 */
	@SuppressWarnings("unchecked")
	public List<Long> getLevelBasedSearch(Long userId, Long levelId, String query) {
		List<Long> cadreIds = null;
		if (userId != null && levelId != null) {
			List cadreObjList = cadreDAO.findCadreByUserAndCadreLevel(userId,
					levelId, query);
			if (cadreObjList != null && cadreObjList.size() > 0)
				cadreIds = getProcessedObjects(cadreObjList);
		}
		return cadreIds;
	}

	/*
	 * Process The Objects
	 */
	public List<Long> getProcessedObjects(List objects) {

		List<Long> cadreIds = null;
		if (objects != null && objects.size() > 0) {

			cadreIds = new ArrayList<Long>();
			for (int i = 0; i < objects.size(); i++) {
				Object params = (Object) objects.get(i);
				Long cadreId = (Long) params;
				cadreIds.add(cadreId);
			}
		}
		return cadreIds;
	}

	/*
	 * Method to set Cadre Objects to VO
	 */
	public List<CadreInfo> putCadreSearchResultsInVO(List<Cadre> cadreObjects)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("Inside getCadreDetailsBySearchCriteria Method ");
		List<CadreInfo> cadreOutputVO = null;

		if (cadreObjects != null && cadreObjects.size() > 0) {
			cadreOutputVO = new ArrayList<CadreInfo>();
			for (Cadre cadre : cadreObjects) {
				
				CadreInfo cadreInfoVO = convertCadreToCadreInfo(cadre);
				cadreOutputVO.add(cadreInfoVO);
			}
		}
		return cadreOutputVO;
	}

	/*
	 * Method to send message to cadre of selected criteria
	 */
	@SuppressWarnings("finally")
	public SmsResultVO sendSMSToSelectedCadreCriteria(Long userId,
			PartyCadreDetailsVO cadreInputVO, String includeCadreName,
			String message) {

		SmsResultVO smsResultVO = new SmsResultVO();
		ResultStatus resultStatus = new ResultStatus();
		Long smsSentStatus = 0l;
		Long smsRemainingStatus = 0l;

		try {
			// get cadre matching the selection criteria
			List<CadreInfo> cadreDetails = getCadreDetailsBySearchCriteria(
					userId, cadreInputVO);
			
			List<CadreInfo> removeList= new ArrayList<CadreInfo>();
			
			for(CadreInfo removeCadre : cadreDetails)
			{
				if(removeCadre.getMobile().length() < 10)
					removeList.add(removeCadre);
			}
			for(CadreInfo removeCadre : removeList)
			{
				cadreDetails.remove(removeCadre);	
			}

			if (cadreDetails == null || cadreDetails.size() == 0) {
				smsResultVO.setStatus(1l);
				smsResultVO.setTotalSmsSent(0l);
				smsResultVO.setRemainingSmsCount(0l);
			} else if (cadreDetails != null && cadreDetails.size() > 0) {
				Integer mobileNos = 0;
				Long remainingSMS = smsCountrySmsService
						.getRemainingSmsLeftForUser(userId)
						- cadreDetails.size();
				smsRemainingStatus = remainingSMS;
				if (remainingSMS < 0) {
					smsResultVO.setStatus(1l);
					smsResultVO.setTotalSmsSent(0l);
					smsResultVO.setRemainingSmsCount(0l);
				} else {
					if ("NO".equals(includeCadreName)) {
						String[] cadreMobileNos = new String[cadreDetails
								.size()];
						int i = -1;
						for (CadreInfo mobileInfo : cadreDetails) {
							cadreMobileNos[++i] = mobileInfo.getMobile();
						}
						if (cadreMobileNos != null && cadreMobileNos.length > 0)
							mobileNos = cadreMobileNos.length;
						smsSentStatus = smsCountrySmsService.sendSms(message,
								true, userId, IConstants.Cadre_Management,
								cadreMobileNos);
					} else {
						// to do ICONSTANTS.SMS_DEAR
						for (CadreInfo mobiles : cadreDetails) {
							String mobile = mobiles.getMobile();
							StringBuilder cadreMessage = new StringBuilder(
									IConstants.SMS_DEAR);
							cadreMessage.append(IConstants.SPACE).append(
									mobiles.getFirstName()).append(
									IConstants.SPACE).append(message);
							/*
							 * String[] mobileNOs = new String[1]; mobileNOs[1]
							 * = mobile;
							 */
							smsSentStatus = smsCountrySmsService.sendSms(
									cadreMessage.toString(), true, userId,
									IConstants.Cadre_Management, mobile);
							mobileNos = mobileNos + 1;
						}
					}

					// sms sent failure
					if (smsSentStatus.equals(1l)) {
						smsResultVO.setStatus(1l);
						smsResultVO.setTotalSmsSent(0l);
						smsResultVO.setRemainingSmsCount(smsRemainingStatus);
					} else {
						smsResultVO.setStatus(0l);
						smsResultVO.setTotalSmsSent(Long.parseLong(new Integer(
								cadreDetails.size()).toString()));
						smsResultVO.setRemainingSmsCount(remainingSMS);
						smsResultVO.setSmsSentCadreInfo(cadreDetails);
					}
				}

			}

		} catch (Exception ex) {

			ex.printStackTrace();
			smsResultVO.setStatus(1l);
			smsResultVO.setTotalSmsSent(0l);
			smsResultVO.setRemainingSmsCount(0l);
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			resultStatus.setExceptionClass(ex.getClass().toString());
			resultStatus.setExceptionMsg(getExceptionMessage(ex.getClass()
					.toString()));
			smsResultVO.setResultStatus(resultStatus);
			log.debug(ex);
			return smsResultVO;
		}

		return smsResultVO;
	}

	/*
	 * Method to send SMS to selected set of cadre mobile numbers
	 */
	public SmsResultVO sendSMSToSelectedCadre(Long userId,
			String includeCadreName, Boolean isText, String message,
			List<SmsVO> cadreList) {

		if (log.isDebugEnabled())
			log.debug(" Inside sendSMSTOSelectedCadreMobileNos Method ..");
		SmsResultVO smsResult = new SmsResultVO();
		ResultStatus resultStatus = new ResultStatus();

		try {
			Long smsRemainingStatus = 0l;
			Long smsSentStatus = 0l;
			if (userId != null) {
				Long remainingSMS = smsCountrySmsService
						.getRemainingSmsLeftForUser(userId)
						- cadreList.size();
				smsRemainingStatus = remainingSMS;
				if (remainingSMS < 0) {
					smsResult.setStatus(1l);
					smsResult.setTotalSmsSent(0l);
					smsResult.setRemainingSmsCount(0l);
				} else {
					if ("NO".equals(includeCadreName)) {
						String[] cadreMobileNos = new String[cadreList.size()];
						int i = -1;
						for (SmsVO mobileInfo : cadreList) {
							cadreMobileNos[++i] = mobileInfo.getMobileNO();
						}
						if (cadreMobileNos != null && cadreMobileNos.length > 0)
							smsSentStatus = smsCountrySmsService
									.sendSms(message, isText, userId,
											IConstants.Cadre_Management,
											cadreMobileNos);
					} else {
						// to do ICONSTANTS.SMS_DEAR
						for (SmsVO mobiles : cadreList) {
							String mobile = mobiles.getMobileNO();
							StringBuilder cadreMessage = new StringBuilder(
									IConstants.SMS_DEAR);
							cadreMessage.append(IConstants.SPACE).append(
									mobiles.getCadreName()).append(
									IConstants.SPACE).append(message);
							/*
							 * String[] mobileNOs = new String[1]; mobileNOs[1]
							 * = mobile;
							 */
							smsSentStatus = smsCountrySmsService.sendSms(
									cadreMessage.toString(), isText, userId,
									IConstants.Cadre_Management, mobile);
						}
					}

				}

				// sms sent failure
				if (smsSentStatus.equals(1l)) {
					smsResult.setStatus(1l);
					smsResult.setTotalSmsSent(0l);
					smsResult.setRemainingSmsCount(smsRemainingStatus);
				} else {
					smsResult.setStatus(0l);
					smsResult.setTotalSmsSent(Long.parseLong(new Integer(
							cadreList.size()).toString()));
					smsResult.setRemainingSmsCount(remainingSMS);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);

			resultStatus.setExceptionClass(ex.getClass().toString());
			resultStatus.setExceptionMsg(getExceptionMessage(ex.getClass()
					.toString()));
			smsResult.setResultStatus(resultStatus);
		}

		return smsResult;
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
	
	public CadreInfo getCadreCompleteInfo(Long cadreId) {
		
		CadreInfo cadreInfo = new CadreInfo();
		try
		{
			Cadre cadre = cadreDAO.get(cadreId);
			cadreInfo = convertCadreToCadreInfo(cadre);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return cadreInfo;
	}
	
	public List<SelectOptionVO> getAllCadreLevels()
	{
		List<CadreLevel> cadreLevels = cadreLevelDAO.getAll();
		List<SelectOptionVO> levels = new ArrayList<SelectOptionVO>();
		for(CadreLevel cadreLevel: cadreLevels)
		{
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(cadreLevel.getId());
			selectOptionVO.setName(cadreLevel.getLevel());
			levels.add(selectOptionVO);
		}
		return levels;
	}
	
	public List<CadreRegionInfoVO> getParliamentAllConstCadres(Long parliamentId, Long userId)
	{
		List constCadres = cadreDAO.findConstituencyCadresByParliamentConst(parliamentId, userId,IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = constCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for (int i = 0; i < size; i++) {
			Object[] voObject = (Object[]) constCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("CONSTITUENCY");
			regionInfoVo.setRegionId(new Long(voObject[0].toString()));
			regionInfoVo.setRegionName(voObject[1].toString());
			regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;
	}
	
	public List<CadreRegionInfoVO> getDistrictAllConstCadres(Long districtID, Long userID)
	{
		List constCadres = cadreDAO.findConstituencyCadresByDist(districtID, userID,IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = constCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for (int i = 0; i < size; i++) {
			Object[] voObject = (Object[]) constCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("CONSTITUENCY");
			regionInfoVo.setRegionId(new Long(voObject[0].toString()));
			regionInfoVo.setRegionName(voObject[1].toString());
			regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;
		
	}
	/**
	 * To retrieve all cadres by hamlet level in a mandal
	 * @param mandalId
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getMandalAllHamletsCadres(Long mandalId, Long userId)
	{
		List hamletCadres = cadreDAO.findHamletCadresByMandal(mandalId, userId,IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		
		//For non Hamlet regions (Township)
		if(hamletCadres == null || hamletCadres.size() == 0){
			hamletCadres = cadreDAO.findTownshipCadresByMandal(mandalId, userId,IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		}
		
		int size = hamletCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for (int i = 0; i < size; i++) {
			Object[] voObject = (Object[]) hamletCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("VILLAGE");
			regionInfoVo.setRegionId(new Long(IConstants.RURAL_TYPE+voObject[0].toString()));
			regionInfoVo.setRegionName(voObject[1].toString());
			regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;
	}
	
	/**
	 * To retrieve all cadres by booth level in a mandal
	 * @param mandalId
	 * @param userId
	 * @return
	 */
	public List<CadreRegionInfoVO> getMandalAllBoothsCadres(Long mandalId, Long userId)
	{
		List boothCadres = cadreDAO.findBoothCadresByMandal(mandalId, userId,IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = boothCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for (int i = 0; i < size; i++) {
			Object[] voObject = (Object[]) boothCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("BOOTH");
			regionInfoVo.setRegionId(new Long(voObject[0].toString()));
			regionInfoVo.setRegionName("Booth No" + voObject[1]+"("+ voObject[2] + ")");
			regionInfoVo.setCadreCount(new Long(voObject[3].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;
	}
	/**
	 * To retrieve all cadres by booth level in a mandal count
	 * @param mandalId
	 * @param userId
	 * @return
	 */
	public List<CadreRegionInfoVO> getMandalAllBoothsCadresCount(Long mandalId, Long userId)
	{
		List boothCadres = cadreDAO.findHamletCadresByMandal(mandalId, userId,IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = boothCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		Long count = 0l;		
		for (int i = 0; i < size; i++) {
			Object[] voObject = (Object[]) boothCadres.get(i);
			count += Long.parseLong(voObject[2].toString());
			
		}
		CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("CADRES BY BOOTHS");
		regionInfoVo.setRegionId(new Long(IConstants.RURAL_TYPE+mandalId));
		regionInfoVo.setRegionName("Boothwise");
		regionInfoVo.setCadreCount(count);
		formattedData.add(regionInfoVo);
		return formattedData;
	}
	/**
	 * To retrieve all cadres by booth level in a local election body
	 * @param mandalId
	 * @param userId
	 * @return
	 */
	public List<CadreRegionInfoVO> getLocalElectionBodyAllBoothsCadres(Long localElectionBodyId, Long userId)
	{
		List boothCadres = cadreDAO.findBoothCadresByLocalElectionBody(localElectionBodyId, userId,IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = boothCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for (int i = 0; i < size; i++) {
			Object[] voObject = (Object[]) boothCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("BOOTH");
			regionInfoVo.setRegionId(new Long(voObject[0].toString()));
			regionInfoVo.setRegionName("Booth No"+voObject[1]+" ( " + voObject[2] + " ) ");
			regionInfoVo.setCadreCount(new Long(voObject[3].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;
	}
	
	/**
	 * To retrieve all cadres by booth level in a local election body count
	 * @param mandalId
	 * @param userId
	 * @return
	 */
	public List<CadreRegionInfoVO> getLocalElectionBodyAllBoothsCadresCount(Long localElectionBodyId, Long userId)
	{
		List boothCadres = cadreDAO.findCadresByWard(localElectionBodyId, userId,IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = boothCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		Long count = 0l;
		for (int i = 0; i < size; i++) {
			Object[] voObject = (Object[]) boothCadres.get(i);
			count += Long.parseLong(voObject[1].toString());
		}
		CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("CADRES BY BOOTHS");
		regionInfoVo.setRegionId(new Long(IConstants.URBAN_TYPE+localElectionBodyId));
		regionInfoVo.setRegionName("Boothwise");
		regionInfoVo.setCadreCount(count);
		formattedData.add(regionInfoVo);
		return formattedData;
	}
	
	/**
	 * To retrieve all cadres by booth level in a ward count
	 * @param mandalId
	 * @param userId
	 * @return-working
	 */
	public List<CadreRegionInfoVO> getWardAllBoothsCadresCount(Long wardId, Long userId)
	{
		List boothCadres = cadreDAO.findCadresByBoothInWard(wardId, userId,IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = boothCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		Long count = 0l;
		for (int i = 0; i < size; i++) {
			Object[] voObject = (Object[]) boothCadres.get(i);
			count += Long.parseLong(voObject[3].toString());
		}
		CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("CADRES BY BOOTHS IN WARD");
		regionInfoVo.setRegionId(new Long(IConstants.URBAN_TYPE+wardId));
		regionInfoVo.setRegionName("Boothwise");
		regionInfoVo.setCadreCount(count);
		formattedData.add(regionInfoVo);
		return formattedData;
	}
	
	/**
	 * To retrieve all cadres not attached with any booth in their current address based on cadre type and user id 
	 * @param mandalId
	 * @param userId
	 * @return
	 */
	public List<CadreRegionInfoVO> getAllNonAssignedBoothCadres(Long userId, String memberType)
	{
		List cadres = cadreDAO.findCadresNotAssignedToBooth(userId, memberType);
		Long count = Long.parseLong(cadres.get(0).toString());
		return null;
	}
	/**
	 * This method retrieves cadres which are not having booth details in their current address based on tehsil.
	 * @param tehsilId
	 * @param userId
	 * @return
	 */
	public List<CadreRegionInfoVO> getAllNonAssignedBoothCadresByTehsil(Long tehsilId, Long userId){
		List cadres = cadreDAO.findCadresNotAssignedToBoothByTehsil(tehsilId,userId, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		Long count = Long.parseLong(cadres.get(0).toString());
		
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		
		CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("Not Assigned To Any Booth");
		regionInfoVo.setRegionId(new Long(IConstants.RURAL_TYPE+tehsilId));
		regionInfoVo.setRegionName("Cadres with no booth Details");
		regionInfoVo.setCadreCount(count);
		formattedData.add(regionInfoVo);
		return formattedData;
	}
	/**
	 * This method retrieves cadres which are not having booth details in their current address based on localbody. 
	 * @param localBodyId
	 * @param userId
	 * @return
	 */
	public List<CadreRegionInfoVO> getAllNonAssignedBoothCadresByLocalBody(Long localBodyId, Long userId){
		List cadres = cadreDAO.findCadresNotAssignedToBoothLocalElectionBody(localBodyId,userId, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		Long count = Long.parseLong(cadres.get(0).toString());
		
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("Not Assigned To Any Booth");
		regionInfoVo.setRegionId(new Long(IConstants.URBAN_TYPE+localBodyId));
		regionInfoVo.setRegionName("Cadres with no booth Details");
		regionInfoVo.setCadreCount(count);
		formattedData.add(regionInfoVo);
		return formattedData;
	}
	/**
	 * This method retrieves cadres which are not having booth details in their current address based on ward id. 
	 * @param wardId
	 * @param userId
	 * @return
	 */
	public List<CadreRegionInfoVO> getAllNonAssignedBoothCadresByWard(Long wardId, Long userId){
		List cadres = cadreDAO.findCadresNotAssignedToBoothInWard(wardId,userId, IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		Long count = Long.parseLong(cadres.get(0).toString());
		
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("Not Assigned To Any Booth");
		regionInfoVo.setRegionId(new Long(IConstants.URBAN_TYPE+wardId));
		regionInfoVo.setRegionName("Cadres with no booth Details");
		regionInfoVo.setCadreCount(count);
		formattedData.add(regionInfoVo);
		return formattedData;
	}
	/**
	 * To retrieve all cadres in sub regions of a urban/ rural-urban constituencies
	 * @param constituencyId
	 * @param userId
	 * @return
	 */
	public List<CadreRegionInfoVO> getConstituencySubRegionalCadres(Long constituencyId, Long userId)
	{
		List subRegionCadres = cadreDAO.findLocalElectionBodiesCadresByConst(constituencyId, userId,IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = subRegionCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for (int i = 0; i < size; i++) {
			Object[] voObject = (Object[]) subRegionCadres.get(i);
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO(voObject[3].toString());
			regionInfoVo.setRegionId(new Long(IConstants.URBAN_TYPE+voObject[0].toString()));
			regionInfoVo.setRegionName(voObject[1].toString());
			regionInfoVo.setCadreCount(new Long(voObject[2].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;
		
	}
	/**
	 * This method returns the consolidated list(count) of cadres based on local body in the current address
	 * @param localElectionBodyId
	 * @param userId
	 * @return
	 */
	public List<CadreRegionInfoVO> getLocalElectionBodyCadresByWardsCount(Long localElectionBodyId, Long userId)
	{
		List cadresInWards = cadreDAO.findCadresByWard(localElectionBodyId, userId,IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = cadresInWards.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		Long count = 0l;
		for (int i = 0; i < size; i++) {
			Object[] voObject = (Object[]) cadresInWards.get(i);
			count += Long.parseLong(voObject[1].toString());			
		}
		CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("CADRES BY WARDS");
		regionInfoVo.setRegionId(new Long(IConstants.URBAN_TYPE+localElectionBodyId));
		regionInfoVo.setRegionName("Wardswise");
		regionInfoVo.setCadreCount(count);
		formattedData.add(regionInfoVo);
		return formattedData;		
	}
	
	/**
	 * This method retrieves the consolidated list(count) of all the cadres based on village in their current address in a mandal
	 * * @param mandalId
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CadreRegionInfoVO> getMandalAllHamletsCadresCount(Long mandalId, Long userId)
	{
		
		List hamletCadres = cadreDAO.findHamletCadresByMandal(mandalId, userId,IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		
		//For non Hamlet regions (Township)
		if(hamletCadres == null || hamletCadres.size() == 0){
			hamletCadres = cadreDAO.findTownshipCadresByMandal(mandalId, userId,IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		}
		
		int size = hamletCadres.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		Long count = 0l;
		for (int i = 0; i < size; i++) {
			Object[] voObject = (Object[]) hamletCadres.get(i);
			count += Long.parseLong(voObject[2].toString());
		}
		CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("CADRES BY VILLAGES");
		regionInfoVo.setRegionId(new Long(IConstants.RURAL_TYPE+mandalId));
		regionInfoVo.setRegionName("Villagewise");
		regionInfoVo.setCadreCount(count);
		formattedData.add(regionInfoVo);
		return formattedData;			
	}
	/**
	 * This method retrieves all the cadres by wards in a localbody.
	 * @param localElectionBodyId
	 * @param userId
	 * @return
	 */
	public List<CadreRegionInfoVO> getLocalElectionBodyCadresByWards(Long localElectionBodyId, Long userId)
	{
		List cadresInWards = cadreDAO.findCadresByWard(localElectionBodyId, userId,IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		int size = cadresInWards.size();
		List<CadreRegionInfoVO> formattedData = new ArrayList<CadreRegionInfoVO>();
		for (int i = 0; i < size; i++) {
			Object[] voObject = (Object[]) cadresInWards.get(i);
			Constituency constituency = (Constituency)voObject[0];
			CadreRegionInfoVO regionInfoVo = new CadreRegionInfoVO("WARD");
			regionInfoVo.setRegionId(new Long(IConstants.URBAN_TYPE+constituency.getConstituencyId()));
			regionInfoVo.setRegionName(constituency.getLocalElectionBodyWard()!= null?constituency.getLocalElectionBodyWard().getWardName().concat("(").
					concat(constituency.getName()).toUpperCase().concat(")"):constituency.getName());
			regionInfoVo.setCadreCount(new Long(voObject[1].toString()));
			formattedData.add(regionInfoVo);
		}
		return formattedData;
		
	}
	
	public List<SelectOptionVO> getAllRelationships(){
		try{
			List<SelectOptionVO> selectOptionVO = new ArrayList<SelectOptionVO>();
			List<UserRelation>  result = userRelationDAO.getAll();
			
			/*SelectOptionVO selectOptionVo = new SelectOptionVO();
			selectOptionVo.setId(0l);
			selectOptionVo.setName("Select Relation");
			selectOptionVO.add(selectOptionVo); */
			
			for(UserRelation userRelation : result){
				SelectOptionVO selectOption = new SelectOptionVO();
				selectOption.setId(userRelation.getUserRelationId());
				selectOption.setName(userRelation.getRelationship());
				selectOptionVO.add(selectOption);
			}
			return selectOptionVO;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method retrieves cadres which contains a booth details in their current address 
	 * @param id
	 * @param userID
	 * @return
	 */
	public List<CadreInfo> getCadresByBooth(String id, String userID)
	{
		List<CadreInfo> formattedData = new ArrayList<CadreInfo>(0);
		List<Cadre> cadresList = new ArrayList<Cadre>(0);	
		try{
			cadresList = cadreDAO.findCadresByBooth(new Long(id), new Long(userID),IConstants.CADRE_MEMBER_TYPE_ACTIVE);
			
			for (Cadre cadre : cadresList) {
				CadreInfo cadreInfo = convertCadreToCadreInfo(cadre);
				formattedData.add(cadreInfo);
			}
			return formattedData;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			cadresList = null;
			userID = null;
			id = null;
		}
	}
	
	/**
	 * This method retrieves cadre details which does not contain booth details in theire current address. 
	 * @param id
	 * @param userID
	 * @return
	 */
	public List<CadreInfo> getCadresNotAssignedWithBooth(Long id, String userID)
	{
		List<CadreInfo> formattedData = new ArrayList<CadreInfo>(0);
		List<Cadre> cadresList = new ArrayList<Cadre>(0);	
		try{
			if (IConstants.URBAN_TYPE.equals(id.toString().substring(0,1)))
			{
				cadresList = cadreDAO.findCadreDetailsNotAssignedToBoothInLocalBody(new Long(id.toString().substring(1)),new Long(userID),IConstants.CADRE_MEMBER_TYPE_ACTIVE);
			}
			if (IConstants.RURAL_TYPE.equals(id.toString().substring(0,1)))
			{
				cadresList = cadreDAO.findCadreDetailsNotAssignedToBooth(new Long(id.toString().substring(1)),new Long(userID),IConstants.CADRE_MEMBER_TYPE_ACTIVE);
			}
			for (Cadre cadre : cadresList) {
				CadreInfo cadreInfo = convertCadreToCadreInfo(cadre);
				formattedData.add(cadreInfo);
			}
			return formattedData;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			cadresList = null;
			userID = null;		
		}
	}
	
	/**
	 * This mehtod is used to verify whether a local body election type is GMC or not.
	 * @param localBodyId
	 * @return
	 */
	public Boolean getLocalBodyElectionType(Long wardId)
	{
		Boolean flag = false;
		Constituency ward = constituencyDAO.get(wardId);
		Long localBodyId = ward.getLocalElectionBody().getLocalElectionBodyId();
		LocalElectionBody localBody = localElectionBodyDAO.get(localBodyId); 
		if(IConstants.GREATER_ELECTION_TYPE.equals(localBody.getElectionType().getElectionType()))
		{
			flag = true;
		}
		return flag;
	}
	
	/**
	 * This method retrieves cadre details which does not contain booth details in theire current address. 
	 * @param id
	 * @param userID
	 * @return
	 */
	public List<CadreInfo> getCadresNotAssignedWithBoothInWard(Long wardId, String userID)
	{
		List<CadreInfo> formattedData = new ArrayList<CadreInfo>(0);
		List<Cadre> cadresList = new ArrayList<Cadre>(0);	
		try{
			 
		cadresList = cadreDAO.findCadreDetailsNotAssignedToBoothInWard(wardId,new Long(userID),IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		
		for (Cadre cadre : cadresList) {
			CadreInfo cadreInfo = convertCadreToCadreInfo(cadre);
			formattedData.add(cadreInfo);
		}
		return formattedData;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			cadresList = null;
			userID = null;			
			System.gc();
		}
	}
	
	/**
	 * This method retrieves cadre details which does contain booth details in their current address. 
	 * @param id
	 * @param userID
	 * @return
	 */
	public List<CadreInfo> getCadresAssignedWithBoothInWard(Long wardId, String userID)
	{
		List<CadreInfo> formattedData = new ArrayList<CadreInfo>(0);
		List<Cadre> cadresList = new ArrayList<Cadre>(0);	
		try{
			 
		cadresList = cadreDAO.findCadreDetailsAssignedToBoothInWard(wardId,new Long(userID),IConstants.CADRE_MEMBER_TYPE_ACTIVE);
		
		for (Cadre cadre : cadresList) {
			CadreInfo cadreInfo = convertCadreToCadreInfo(cadre);
			formattedData.add(cadreInfo);
		}
		return formattedData;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			cadresList = null;
			userID = null;			
			System.gc();
		}
	}
	
	public List<CadreInfo> getCadreDetailsForSMS(Long userId, PartyCadreDetailsVO cadreInputVO,String windowTask,String sort, String order,Integer startIndex,Integer maxResult)
	{
		List<CadreInfo> cadreInfoList = new ArrayList<CadreInfo>();
		
		if (userId != null && cadreInputVO != null) {
			
			Long cadreLevelId     = cadreInputVO.getCadreLevelId();//country-1,state-2,distric-3,con-4,man-5,vil-6,M-C-G-7,wa-8,bo-9
			Long cadreLocationId  = cadreInputVO.getCadreLocationId();
			String cdType         = cadreInputVO.getCadreType();
			String searchType     = cadreInputVO.getSearchType();
			String name           = cadreInputVO.getCadreName().trim();
			String taskName 	  = cadreInputVO.getTaskName();
			String SearchCriteria = new String();
			String cadreType      = new String();
			String sortOption     = new String();
			
			String socStatus = new String();
			String castStr = new String();
			String eduStr = new String();
			String occStr = new String();
			String andOrStr = new String();
			String genderStr = new String();
			String mobileStr = new String();
			String cadreNameStr = null;
			String roleStr = null;
		
			if(searchType.equalsIgnoreCase(IConstants.LOCATION_BASED))
			{
				if(cadreLevelId == 2)
					SearchCriteria = "and model.currentAddress.state.stateId = "+cadreLocationId.toString();
				else if(cadreLevelId == 3)
					SearchCriteria = "and model.currentAddress.district.districtId = "+cadreLocationId.toString();
				else if(cadreLevelId == 4)
					SearchCriteria = "and model.currentAddress.constituency.constituencyId = "+cadreLocationId.toString();
				else if(cadreLevelId == 5)
					SearchCriteria = "and model.currentAddress.tehsil.tehsilId = "+cadreLocationId.toString().substring(1);
				else if(cadreLevelId == 6)
					SearchCriteria = "and model.currentAddress.hamlet.hamletId = "+cadreLocationId.toString().substring(1);
				else if(cadreLevelId == 7)
				{	
					Long assemblyLocalId = Long.parseLong(cadreLocationId.toString().substring(1));
					List localElectionBodies = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(assemblyLocalId);
					Long localId = (Long)localElectionBodies.get(0);
					SearchCriteria = "and model.currentAddress.localElectionBody.localElectionBodyId = "+localId.toString();
				}
				else if(cadreLevelId == 8)
					SearchCriteria = "and model.currentAddress.ward.constituencyId = "+cadreLocationId.toString().substring(1);
				else if(cadreLevelId == 9)
					SearchCriteria = "and model.currentAddress.booth.boothId = "+cadreLocationId.toString();
			}
			
			if(searchType.equalsIgnoreCase(IConstants.LEVEL_BASED))
				SearchCriteria = "and model.cadreLevel.cadreLevelID = "+cadreLevelId.toString();
				
			if(cdType.equalsIgnoreCase(IConstants.CADRE_MEMBER_TYPE_NORMAL))
			{
				cadreType = " and model.memberType = 'Normal' ";
			}
			else if(cdType.equalsIgnoreCase(IConstants.CADRE_MEMBER_TYPE_ACTIVE))
			{
				cadreType = " and model.memberType = 'Active' ";
			}
			else if(cdType.equalsIgnoreCase(IConstants.ALL))
			{
				cadreType = " ";
			}
			
			if(sort.equalsIgnoreCase("firstName"))
				sortOption = " model.firstName "; 
			else if(sort.equalsIgnoreCase("mobile"))
				sortOption = " model.mobile ";
			else if(sort.equalsIgnoreCase("strCadreLevel"))
				sortOption = " model.cadreLevel.level ";
			else if(sort.equalsIgnoreCase("memberType"))
				sortOption = " model.memberType ";
			else if(sort.equalsIgnoreCase("educationStr"))
				sortOption = " model.education.qualification ";
			else if(sort.equalsIgnoreCase("professionStr"))
				sortOption = " model.occupation.occupation ";
			else if(sort.equalsIgnoreCase("casteCategoryStr"))
				sortOption = " model.casteCategory.category ";
			
			
			if(cadreInputVO.getIsSocialStatus() == true)
			{
				if(cadreInputVO.getIsOrSearch() == true)
					andOrStr = " or ";
				else 
					andOrStr = " and ";
				
				Long castCategory = cadreInputVO.getCadreCasteCategory().get(0).getCadreCategoryId();
				
				if(castCategory == 0)
					castStr = " ";
				else
					castStr =" model.casteCategory.socialCategoryId = "+castCategory+" "+andOrStr+" ";
				
				Long eduCategory = cadreInputVO.getCadreEducationQualification().get(0).getCadreCategoryId();
				
				if(eduCategory == 0)
					eduStr = " ";
				else
					eduStr = " model.education.eduQualificationId = "+eduCategory+" "+andOrStr+" ";
				
				Long occCategory = cadreInputVO.getCadreOccupation().get(0).getCadreCategoryId();
				
				if(occCategory == 0)
					occStr = " ";
				else
					occStr = " model.occupation.occupationId = "+occCategory+" ";
				
				socStatus += " and ("+castStr+" "+eduStr+" "+occStr+")";
				
			}
			
			if(cadreInputVO.getGenderSearchType().equalsIgnoreCase("Male"))
				genderStr = " and model.gender like 'Male'";
			else if(cadreInputVO.getGenderSearchType().equalsIgnoreCase("FeMale"))
				genderStr = " and model.gender like 'FeMale'";
			else 
				genderStr =" ";
			
			if(windowTask.equalsIgnoreCase("Sms"))
				mobileStr=" and length(model.mobile) > 0";
			
			else if(windowTask.equalsIgnoreCase("Search"))
				mobileStr=" ";
			
			if(name != null && name.length() > 0)
			{
				cadreNameStr = "and ( ";
				StringTokenizer st = new StringTokenizer(name);
				
				while(st.hasMoreTokens())
				{
					String names = st.nextToken();
					cadreNameStr += " model.firstName like '"+names+"%' or model.middleName like '"+names+"%' or model.lastName like '"+names+"%' ";
					cadreNameStr += " or ";
				}
				
				cadreNameStr = cadreNameStr.substring(0,cadreNameStr.length()-4);
				cadreNameStr += ")";
				
			}
			else
				cadreNameStr = " ";
			
			if(taskName.equalsIgnoreCase(IConstants.CADRE_SEARCH))
				roleStr = " ";
			else if(taskName.equalsIgnoreCase(IConstants.PROBLEM_ADDING))
				roleStr = " and model.cadreId in (select model1.cadre.cadreId from CadreRoleRelation model1) "; 
			 
			Long totalSearchCount = cadreDAO.findTotalCadreCountForSms(userId,cadreType,SearchCriteria,socStatus,genderStr,mobileStr,cadreNameStr,roleStr).get(0);
			
			if(maxResult < 0)
			{
				List<CadreInfo> cadreInfoListTotal = new ArrayList<CadreInfo>();
				CadreInfo cadreTotal = new CadreInfo();
				cadreTotal.setPinCode(totalSearchCount.toString());
				cadreInfoListTotal.add(cadreTotal);
				return cadreInfoListTotal;
			}
			
			List<Long> cadreIds = cadreDAO.findCadreForSMS(userId,cadreType,SearchCriteria,socStatus,genderStr,mobileStr,cadreNameStr,roleStr,sortOption,order,startIndex,maxResult);
			
			for(Long id:cadreIds)
			{
				Cadre cadre = cadreDAO.get(id);
				CadreInfo cadreInfo = new CadreInfo();
				
				cadreInfo.setCadreId(cadre.getCadreId().toString());
				cadreInfo.setFirstName(cadre.getFirstName());
				cadreInfo.setLastName(cadre.getLastName());
				cadreInfo.setMemberType(cadre.getMemberType());
				cadreInfo.setEducationStr(cadre.getEducation().getQualification());
				cadreInfo.setProfessionStr(cadre.getOccupation().getOccupation());
				cadreInfo.setCasteCategoryStr(cadre.getCasteCategory().getCategory());
				cadreInfo.setMobile(cadre.getMobile()!= null ? cadre.getMobile() :"");
				cadreInfo.setImage(cadre.getImage() != null ? cadre.getImage() : "human.jpg");
				
				if(cadre.getCadreLevelValue() != null)
					cadreInfo.setStrCadreLevel(getCadreLevelValueStr(cadre.getCadreLevel().getLevel(),cadre.getCadreLevelValue()));
				else
					cadreInfo.setStrCadreLevel("N/A");
							
				cadreInfo.setEmail(setAddress(cadre));
				
				if(windowTask.equalsIgnoreCase("Sms") && cadreInfo.getMobile().length() != 0)
					cadreInfoList.add(cadreInfo);
				
				else if(windowTask.equalsIgnoreCase("Search"))
					cadreInfoList.add(cadreInfo);
			}
			
			if(totalSearchCount == 0)
			{
				CadreInfo cadreInfo= new CadreInfo();
				cadreInfoList.add(cadreInfo);
				cadreInfoList.get(0).setPinCode(totalSearchCount.toString());
			}
			else
				cadreInfoList.get(0).setPinCode(totalSearchCount.toString());
		}
		
		
		return cadreInfoList;
	}
	
	String getCadreLevelValueStr(String level,Long Value)
	{
		try{
		String levelStr = new String(level+"-");
		
		if(level.equalsIgnoreCase(IConstants.STATE_LEVEL))
			levelStr += stateDAO.get(Value).getStateName();
		else if(level.equalsIgnoreCase(IConstants.DISTRICT_LEVEL))
			levelStr += districtDAO.get(Value).getDistrictName();
		else if(level.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL) ||
									level.equalsIgnoreCase(IConstants.WARD))
			levelStr += constituencyDAO.get(Value).getName();
		else if(level.equalsIgnoreCase(IConstants.MANDAL_LEVEL))
			levelStr += tehsilDAO.get(Value).getTehsilName();
		else if(level.equalsIgnoreCase(IConstants.CENSUS_VILLAGE_LEVEL))
			levelStr += hamletDAO.get(Value).getHamletName();
		else if(level.equalsIgnoreCase("MUNICIPAL-CORP-GMC"))
			levelStr += localElectionBodyDAO.get(Value).getName();
		else if(level.equalsIgnoreCase(IConstants.BOOTH))
			levelStr += boothDAO.get(Value).getPartName();
		
		return levelStr;
		}catch(Exception ex){
			return "";
		}
	}
	
	String setAddress(Cadre cadre)
	{
		String address = new String();
		if(cadre.getCurrentAddress() == null)
		{
			return "";
		}
		
		if(cadre.getCurrentAddress().getWard() != null)
			address += cadre.getCurrentAddress().getWard().getName()+" ";
		else{
			
			if(cadre.getCurrentAddress().getHamlet() != null)
				address += cadre.getCurrentAddress().getHamlet().getHamletName()+"(V) ";
			else if(cadre.getCurrentAddress().getTownship() != null)
				address += cadre.getCurrentAddress().getTownship().getTownshipName()+"( " + cadre.getCurrentAddress().getTownship().getTownshipType() + " )";
		}
	
		if(cadre.getCurrentAddress().getLocalElectionBody() != null)
			address += cadre.getCurrentAddress().getLocalElectionBody().getName()+" ";
		else
			address += cadre.getCurrentAddress().getTehsil().getTehsilName()+" ";
		
		address += cadre.getCurrentAddress().getDistrict().getDistrictName()+"(Dt)";
		
		return address;
		
	}
	
	public String updateCadreImage(Long cadreId,String imageName)
	{
		try{
			int updated = cadreDAO.updateCadreImage(cadreId,imageName);
			log.debug(updated +" Photo Upadted..");
			return IConstants.SUCCESS;
		}catch(Exception e){
			return null;
		}
	}

}
