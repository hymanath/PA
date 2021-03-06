package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.jfree.util.Log;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.itgrids.partyanalyst.dao.IAccessRestrictedSessionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IGroupEntitlementDAO;
import com.itgrids.partyanalyst.dao.IPashiAppUserDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreLoginDetailsDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAccessIpAddressDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserCountryAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserDistrictAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserLoginDetailsDAO;
import com.itgrids.partyanalyst.dao.IUserRolesDAO;
import com.itgrids.partyanalyst.dao.IUserStateAccessInfoDAO;
import com.itgrids.partyanalyst.dto.AmsAppLoginVO;
import com.itgrids.partyanalyst.dto.PeshiAppLoginVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserTrackingVO;
import com.itgrids.partyanalyst.model.AccessRestrictedSession;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Country;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.GroupEntitlementRelation;
import com.itgrids.partyanalyst.model.PashiAppUser;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.UserGroupEntitlement;
import com.itgrids.partyanalyst.model.UserGroupRelation;
import com.itgrids.partyanalyst.model.UserLoginDetails;
import com.itgrids.partyanalyst.model.UserRoles;
import com.itgrids.partyanalyst.security.EncryptDecrypt;
import com.itgrids.partyanalyst.security.PBKDF2;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.IJobConstants;

public class LoginService implements ILoginService{
	
	private IUserCountryAccessInfoDAO userCountryAccessInfoDAO;
	private IUserStateAccessInfoDAO userStateAccessInfoDAO;
	private IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO;
	private IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO;
	private IGroupEntitlementDAO groupEntitlementDAO;
	private ICountryDAO countryDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private ITehsilDAO tehsilDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private IUserLoginDetailsDAO userLoginDetailsDAO;
	private static Logger log = Logger.getLogger(LoginService.class);
	private IUserRolesDAO userRolesDAO;
	private IUserDAO userDAO;
	private IUserAccessIpAddressDAO userAccessIpAddressDAO;
	private VelocityEngine velocityEngine;
	private IAccessRestrictedSessionDAO accessRestrictedSessionDAO;
	private IMailService mailService;
	private TransactionTemplate transactionTemplate = null;
	private IPashiAppUserDAO pashiAppUserDAO; 
	private ITdpCadreLoginDetailsDAO tdpCadreLoginDetailsDAO;
	private AlertManagementSystemService alertManagementSystemService;
	
	
	public void setAlertManagementSystemService(
			AlertManagementSystemService alertManagementSystemService) {
		this.alertManagementSystemService = alertManagementSystemService;
	}

	public ITdpCadreLoginDetailsDAO getTdpCadreLoginDetailsDAO() {
		return tdpCadreLoginDetailsDAO;
	}

	public void setTdpCadreLoginDetailsDAO(
			ITdpCadreLoginDetailsDAO tdpCadreLoginDetailsDAO) {
		this.tdpCadreLoginDetailsDAO = tdpCadreLoginDetailsDAO;
	}

	public IPashiAppUserDAO getPashiAppUserDAO() {
		return pashiAppUserDAO;
	}

	public void setPashiAppUserDAO(IPashiAppUserDAO pashiAppUserDAO) {
		this.pashiAppUserDAO = pashiAppUserDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IAccessRestrictedSessionDAO getAccessRestrictedSessionDAO() {
		return accessRestrictedSessionDAO;
	}

	public void setAccessRestrictedSessionDAO(
			IAccessRestrictedSessionDAO accessRestrictedSessionDAO) {
		this.accessRestrictedSessionDAO = accessRestrictedSessionDAO;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public IMailService getMailService() {
		return mailService;
	}

	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}

	public IUserAccessIpAddressDAO getUserAccessIpAddressDAO() {
		return userAccessIpAddressDAO;
	}

	public void setUserAccessIpAddressDAO(
			IUserAccessIpAddressDAO userAccessIpAddressDAO) {
		this.userAccessIpAddressDAO = userAccessIpAddressDAO;
	}

	public IUserRolesDAO getUserRolesDAO() {
		return userRolesDAO;
	}

	public void setUserRolesDAO(IUserRolesDAO userRolesDAO) {
		this.userRolesDAO = userRolesDAO;
	}

	public IUserLoginDetailsDAO getUserLoginDetailsDAO() {
		return userLoginDetailsDAO;
	}

	public void setUserLoginDetailsDAO(IUserLoginDetailsDAO userLoginDetailsDAO) {
		this.userLoginDetailsDAO = userLoginDetailsDAO;
	}

	public ICountryDAO getCountryDAO() {
		return countryDAO;
	}

	public void setCountryDAO(ICountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IUserCountryAccessInfoDAO getUserCountryAccessInfoDAO() {
		return userCountryAccessInfoDAO;
	}

	public void setUserCountryAccessInfoDAO(
			IUserCountryAccessInfoDAO userCountryAccessInfoDAO) {
		this.userCountryAccessInfoDAO = userCountryAccessInfoDAO;
	}

	public IUserStateAccessInfoDAO getUserStateAccessInfoDAO() {
		return userStateAccessInfoDAO;
	}

	public void setUserStateAccessInfoDAO(
			IUserStateAccessInfoDAO userStateAccessInfoDAO) {
		this.userStateAccessInfoDAO = userStateAccessInfoDAO;
	}

	public IUserDistrictAccessInfoDAO getUserDistrictAccessInfoDAO() {
		return userDistrictAccessInfoDAO;
	}

	public void setUserDistrictAccessInfoDAO(
			IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO) {
		this.userDistrictAccessInfoDAO = userDistrictAccessInfoDAO;
	}

	public IUserConstituencyAccessInfoDAO getUserConstituencyAccessInfoDAO() {
		return userConstituencyAccessInfoDAO;
	}

	public void setUserConstituencyAccessInfoDAO(
			IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO) {
		this.userConstituencyAccessInfoDAO = userConstituencyAccessInfoDAO;
	}

	public IGroupEntitlementDAO getGroupEntitlementDAO() {
		return groupEntitlementDAO;
	}

	public void setGroupEntitlementDAO(IGroupEntitlementDAO groupEntitlementDAO) {
		this.groupEntitlementDAO = groupEntitlementDAO;
	}

	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public RegistrationVO checkForValidUser(String userName,String password)
	{
		RegistrationVO regVO = new RegistrationVO();
		User user = null;
		Set<UserGroupEntitlement> groupEntitlements = null;
		Set<GroupEntitlementRelation> entitlementsModel = null;
		List<String> entitlements = new ArrayList<String>(0);
		Set<SelectOptionVO> countries = new HashSet<SelectOptionVO>(0);
		Set<SelectOptionVO> states = new HashSet<SelectOptionVO>(0);
		Set<SelectOptionVO> districts = new HashSet<SelectOptionVO>(0);
		Set<SelectOptionVO> assemblies = new HashSet<SelectOptionVO>(0);
		Set<SelectOptionVO> parliaments = new HashSet<SelectOptionVO>(0);

		try{
			
			//List<String> secretKeyList = userDAO.getEncryptedKeyByUserName(userName);
			List<User> userObj=userDAO.getModelByUserName(userName);
			
			if(userObj.size()==0){
				return regVO;
			}
			
			/*if(userObj.get(0).getHashKeyTxt() != null && userObj.get(0).getPasswdHashTxt()!=null){
				
				EncryptDecrypt phash = new EncryptDecrypt(userObj.get(0).getHashKeyTxt());
				String encrptdPassword = phash.encryptText(password);
				
				if(encrptdPassword.equalsIgnoreCase(userObj.get(0).getPasswdHashTxt())){
					user=userObj.get(0);
				}
				
				List<User> userList = userDAO.checkUsernameAndEncryptedPasswordForUser(userName,
						encrptdPassword);
				
				if(userList != null  && userList.size() >0)
					user = userList.get(0);
			}*/
			if(userObj.get(0).getPasswordHash() !=null && userObj.get(0).getPasswordSalt()!=null){
				String salt = userObj.get(0).getPasswordSalt();
				String hash = userObj.get(0).getPasswordHash();
				//boolean validated= EncryptDecrypt.validatePassword(password, hash, salt);
				PBKDF2 pb= new PBKDF2();
				boolean validated = pb.validatePWD(password, hash, salt);
				if(validated){
					user=userObj.get(0);
				}
				
			}

			//user = userDAO.findByUserNameAndPassword(userName, password);
			
			if(user == null ||  user.getUserId() <= 0)
				return regVO;
			/*else if(user!=null){
				if(!(user.getPassword().equals(password)))			
				return regVO;
			}*/
			Long userId = user.getUserId();
			regVO.setRegistrationID(userId);
			regVO.setFirstName(user.getFirstName());
			regVO.setLastName(user.getLastName());
			regVO.setUserName(user.getUserName().trim());
			regVO.setLoginRestriction(user.get_loginRestriction());
			List<String> userRoles = userRolesDAO.getUserRolesOfAUser(userId);
			regVO.setUserRoles(userRoles);
			
			if(userRoles.contains(IConstants.FREE_USER))
			{
				regVO.setEmail(user.getEmail());
				regVO.setUserProfilePic(user.getProfileImg());
				regVO.setUserType(IConstants.FREE_USER);
				regVO.setUserStatus(IConstants.FREE_USER);
				regVO.setConstituencyId(user.getConstituencyId());
			}
			
			if(userRoles.contains(IConstants.PARTY_ANALYST_USER))
			{
				regVO.setUserType(user.getUserType());
				regVO.setUserStatus(IConstants.PARTY_ANALYST_USER);
				regVO.setAccessType(user.getAccessType());
				regVO.setAccessValue(user.getAccessValue());
				regVO.setStateName(getStateBasedOnLocation(regVO.getAccessType(),regVO.getAccessValue()));
				regVO.setSubscribePartyImpDate(user.getIncludePartyImpDateStatus());
				regVO.setParentUserId(user.getParentUser() != null?user.getParentUser().getUserId():null);
				regVO.setMainAccountId(user.getMainAccountUser() != null ? user.getMainAccountUser().getUserId() : null);
				if(user.getMultipleAccessRestriction()!=null){
				regVO.setMultipleAccessRestriction(Boolean.valueOf(user.getMultipleAccessRestriction().toString()));
				}
				if(user.getParty() != null){
					regVO.setParty(user.getParty().getPartyId());
					regVO.setPartyShortName(user.getParty().getShortName());
				}
				
				Set<UserGroupRelation> userGroups = user.getUserGroupRelations();
				
				for(UserGroupRelation groupRelation:userGroups){
					groupEntitlements = groupRelation.getUserGroup().getUserGroupEntitlement();
					for(UserGroupEntitlement userGroupEntitlement:groupEntitlements){
						entitlementsModel = userGroupEntitlement.getGroupEntitlement().getGroupEntitlementRelations();
						for(GroupEntitlementRelation entitlement:entitlementsModel)
							entitlements.add(entitlement.getEntitlement().getEntitlementType());
					}
				}
				
				getUserAccessInfo(userId, countries, states, districts, assemblies, parliaments);
				
				//Getting Assigned CadreIds For LoginUserId
				if(entitlements != null && entitlements.size()>0 && entitlements.contains("TDP_CADRE_LOGIN_ENTITLEMENT")){
					List<Long> assignCadreIds = tdpCadreLoginDetailsDAO.getAssignedCadreIdsForLoginUserId(userId);
					regVO.getAssignCadreIds().addAll(assignCadreIds);
				}
				
				regVO.setEntitlements(entitlements);
				regVO.setCountries(countries);
				regVO.setStates(states);
				regVO.setDistricts(districts);
				regVO.setAssemblies(assemblies);
				regVO.setParliaments(parliaments);
				regVO.setPageTracking(user.getPageTracking());
				regVO.setRequestTracking(user.getRequestTracking());
				regVO.setMembershipId(user.getMembershipId() !=null && !user.getMembershipId().trim().isEmpty()  ? user.getMembershipId().trim():"");
				
				if(entitlements.contains(IConstants.ADMIN_PAGE))
					regVO.setIsAdmin(IConstants.TRUE);	
				else
					regVO.setIsAdmin(IConstants.FALSE);	
				
				if(entitlements.contains(IConstants.CADRE_PARLIAMENT_WISE)){
					regVO.setCadreParliamentWise(true);
				}
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return regVO;
	
	}
	
public String getStateBasedOnLocation(String AccessType,String accessValue){
		
		String state = "";
		
		if(AccessType.equalsIgnoreCase("STATE")){
			state = "both";
		}else{
			Long dist = null;
			if(AccessType.equalsIgnoreCase("MLA")){
				List<Long> distIds = constituencyDAO.getDistrictIdByConstituencyId(Long.valueOf(accessValue));
				if(distIds!=null){
					dist = distIds.get(0);
				}
				
			}
			if(AccessType.equalsIgnoreCase("MP")){
				List<Long> distIds = delimitationConstituencyAssemblyDetailsDAO.findDistrictsBYParliament(Long.valueOf(accessValue));
				if(distIds!=null){
					dist = distIds.get(0);
				}
				
			}
			if(AccessType.equalsIgnoreCase("DISTRICT")){
				dist = Long.valueOf(accessValue);
			}
			
			if(dist!=null && dist>10){
				state=  "AP";
			}else{
				state = "TS";
			}
			
			
		}
		
		return state;
	}
	private void getUserAccessInfo(Long userId, Set<SelectOptionVO> countries,
			Set<SelectOptionVO> states, Set<SelectOptionVO> districts,
			Set<SelectOptionVO> assemblies, Set<SelectOptionVO> parliaments){
		
		getListFromRawdata(userCountryAccessInfoDAO.findByUser(userId), countries);
		getListFromRawdata(userStateAccessInfoDAO.findByUser(userId), states);
		getListFromRawdata(userDistrictAccessInfoDAO.findByUser(userId), districts);
		List rawData = userConstituencyAccessInfoDAO.findByUser(userId);
		
		if(rawData.size() > 0)
			for(Object[] values:(List<Object[]>)rawData){
				if(values[2].toString().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
					assemblies.add(new SelectOptionVO(Long.parseLong(values[0].toString()), values[1].toString()));
				else
					parliaments.add(new SelectOptionVO(Long.parseLong(values[0].toString()), values[1].toString()));
			}
	}
	
	/**
	 * This method can be used to get all the default entitlements for a user.
	 * 
	 */
	public List<String> getDefaultEntitlements(String defaultEntitlementsGroup){
		List<String> defaultEntitlements = new ArrayList<String>();
		try{
			List<GroupEntitlementRelation> entitlementRelations = groupEntitlementDAO.getfindGroupEntitlementsByGroupName(defaultEntitlementsGroup);
			for(GroupEntitlementRelation relation:entitlementRelations)
				defaultEntitlements.add(relation.getEntitlement().getEntitlementType());
		}catch(Exception e){
			e.printStackTrace();
		}		
		return defaultEntitlements;
	}
	
	private void getListFromRawdata(List rawData, Set<SelectOptionVO> locationList){
		if(rawData.size() > 0)
			for(Object[] values:(List<Object[]>)rawData)
				locationList.add(new SelectOptionVO(new Long(values[0].toString()), values[1].toString()));
	}

	public RegistrationVO checkForValidNormalUser(String userName,
			String password) {
		RegistrationVO regVO = new RegistrationVO();
		/*List<AnanymousUser> anamymousUser = new ArrayList<AnanymousUser>(0);
		//List<AnanymousUser> anamymousUser = ananymousUserDAO.checkAnonymousUserLogin(userName, password);
		
		if(anamymousUser == null || anamymousUser.size() != 1)
			return regVO;
		
		AnanymousUser user = anamymousUser.get(0);
		if(!user.getPassword().equals(password))
		{
			return regVO;
		}
			
		regVO.setRegistrationID(user.getUserId());
		regVO.setUserName(user.getUsername());
		regVO.setFirstName(user.getName() != null ? user.getName() : "");
		regVO.setLastName(user.getLastName() != null ? user.getLastName() : "");
		regVO.setUserType(IConstants.FREE_USER);
		regVO.setUserStatus(IConstants.FREE_USER);
		regVO.setUserProfilePic(user.getProfileImg());
		regVO.setEmail(user.getEmail());*/
		
		return regVO;
	}

	@SuppressWarnings("unchecked")
	public Boolean checkForRegionToViewReport(RegistrationVO registrationVO, final String regionType, Long regionId){
		
		if(registrationVO == null)
			return false;
		Country country = null;
		State state = null;
		District district = null;
		Tehsil tehsil = null;
		Constituency ac = null;
		Constituency pc = null;
		Constituency constituency = null;
		SelectOptionVO countrySelect = null;
		SelectOptionVO stateSelect = null;
		List<SelectOptionVO> districtSelect = new ArrayList<SelectOptionVO>(0);
		SelectOptionVO acSelect = null;
		SelectOptionVO pcSelect = null;
		
		try{
			if(IConstants.COUNTRY_LEVEL.equalsIgnoreCase(regionType)){
				country = countryDAO.get(regionId);
				countrySelect = new SelectOptionVO(country.getCountryId(), country.getCountryName());
			}else if(IConstants.STATE_LEVEL.equalsIgnoreCase(regionType)){
				state = stateDAO.get(regionId);	
				country = state.getCountry();
				
				countrySelect = new SelectOptionVO(country.getCountryId(), country.getCountryName());
				stateSelect = new SelectOptionVO(state.getStateId(), state.getStateName());
			}else if(IConstants.DISTRICT_LEVEL.equalsIgnoreCase(regionType)){
				district = districtDAO.get(regionId);
				state = district.getState();	
				country = state.getCountry();
				
				countrySelect = new SelectOptionVO(country.getCountryId(), country.getCountryName());
				stateSelect = new SelectOptionVO(state.getStateId(), state.getStateName());
				districtSelect.add(new SelectOptionVO(district.getDistrictId(), district.getDistrictName()));
			}else if(IConstants.CONSTITUENCY_LEVEL.equalsIgnoreCase(regionType)){
				constituency = constituencyDAO.get(regionId);
				state = constituency.getState();				
				country = state.getCountry();
				
				if(IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(constituency.getElectionScope().getElectionType().getElectionType())){
					acSelect = new SelectOptionVO(constituency.getConstituencyId(), constituency.getName());
					district = constituency.getDistrict();
					districtSelect.add(new SelectOptionVO(district.getDistrictId(), district.getDistrictName()));
					List pcs = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(regionId);

					if(pcs.size() > 0){
						Long pcId = (Long)((Object[])delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(regionId).get(0))[0];
						pc = constituencyDAO.get(pcId);
						pcSelect = new SelectOptionVO(pc.getConstituencyId(), pc.getName());
					}
					
				}else
				{
					pcSelect = new SelectOptionVO(constituency.getConstituencyId(), constituency.getName());
					List<Object[]> DisList = delimitationConstituencyAssemblyDetailsDAO.findDistrictsOfParliamentConstituency(constituency.getConstituencyId());
					
					for(Object[] param : DisList)
						districtSelect.add(new SelectOptionVO((Long)param[0],param[1].toString()));
				}
				
				countrySelect = new SelectOptionVO(country.getCountryId(), country.getCountryName());
				stateSelect = new SelectOptionVO(state.getStateId(), state.getStateName());
				
			}else if(IConstants.TEHSIL_LEVEL.equalsIgnoreCase(regionType)){
				tehsil = tehsilDAO.get(regionId);
				district = tehsil.getDistrict();
				state = district.getState();	
				country = state.getCountry();
				Long acId = 0l;
				Long pcId = 0l;
				
				List acs = delimitationConstituencyMandalDAO.getLatestAssemblyConstitueciesOfTehsil(regionId);
				
				if(acs.size() > 0){
					acId = (Long)((Object[])acs.get(0))[4];	
					ac = constituencyDAO.get(acId);
					acSelect = new SelectOptionVO(ac.getConstituencyId(), ac.getName());
				
					List pcs = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(acId);
					
					if(acs.size() > 0){
						pcId = (Long)((Object[])pcs.get(0))[0];
						pc = constituencyDAO.get(pcId);
						pcSelect = new SelectOptionVO(pc.getConstituencyId(), pc.getName());
					}
				}
				
				countrySelect = new SelectOptionVO(country.getCountryId(), country.getCountryName());
				stateSelect = new SelectOptionVO(state.getStateId(), state.getStateName());
				districtSelect.add(new SelectOptionVO(district.getDistrictId(), district.getDistrictName()));
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if((countrySelect != null && registrationVO.getCountries().contains(countrySelect))||
				(stateSelect != null && registrationVO.getStates().contains(stateSelect))||
				(districtSelect != null && checkForDistrict(registrationVO.getDistricts(),districtSelect))||
				(acSelect != null && registrationVO.getAssemblies().contains(acSelect))||
				(pcSelect != null && registrationVO.getParliaments().contains(pcSelect)))
			return true;
		
		return false;	
	}

	public boolean checkForDistrict(Set<SelectOptionVO> distSet, List<SelectOptionVO> distList)
	{
		for(SelectOptionVO optionVO :distList)
			if(distSet.contains(optionVO))
				return true;

		return false;
	}
	
	public String saveUserSessionDetails(UserTrackingVO userTrackingVO)
	{
		try{
			UserLoginDetails userLoginDetails = null;
			if(userTrackingVO.getStatus().equalsIgnoreCase(IConstants.LOGIN))
			{
				userLoginDetails = new UserLoginDetails();
				userLoginDetails.setIpAddress(userTrackingVO.getRemoteAddress());
				userLoginDetails.setLoginTime(dateUtilService.getCurrentDateAndTime());
				userLoginDetails.setSessionId(userTrackingVO.getSessionId());
				userLoginDetails.setUserId(userTrackingVO.getRegistrationId());
			}
			else
			{
				userLoginDetails = userLoginDetailsDAO.getBySessionId(userTrackingVO.getSessionId());
				
				if(userLoginDetails != null)
					userLoginDetails.setLogoutTime(dateUtilService.getCurrentDateAndTime());	
			}
			
			userLoginDetailsDAO.save(userLoginDetails);
			return IConstants.SUCCESS;
			
		}catch(Exception e){
			log.error("Exception Occured in saveUserLoginDetails() in LoginService - "+e);
			return null;
		}
	}
	
	
	/*public ResultStatus changePasswordOfANewUser(String crntpassword,String newpassword,String userName)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			List<User> list = userDAO.getUserByUserName(userName);
			User user = null;
			
			if(list != null && list.size() > 0)
				user = list.get(0);
			
			if(user != null && user.getUserId() > 0 && user.getPassword().equals(crntpassword))
			{
				user.setPassword(newpassword);
				user.setIsPwdChanged(IConstants.TRUE);
				user.setUpdatedDate(dateUtilService.getCurrentDateAndTime());
				user = userDAO.save(user);
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			}
			else
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			
			return resultStatus;
		}catch (Exception e) {
			log.error("Exception Occured During change password of a new User - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}*/
	
	public ResultStatus changePasswordOfANewUser(final String crntpassword,final String newpassword,final String userName)
	{
		
		ResultStatus resultStatus = (ResultStatus) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus status) {
						
				ResultStatus rs = new ResultStatus();
				try{
			List<User> list = userDAO.getUserByUserName(userName);
			
			 User user = null;
			/*String secretKey = null;
			String presentEncryptedPassword = null;
			String savedEncryptedPassword = null;
			*/
			boolean userExist = false;
			if(list != null && list.size() > 0){
				 user = list.get(0);
				 
				 
				 /*
					 * Commented by SASI on 24-03-2014
					 * @author <a href="sasi.itgrids.hyd@gmail.com">sasi.itgrids.hyd@gmail.com</a>
					 * 
				 */
				/*secretKey = user.getHashKeyTxt();
				savedEncryptedPassword = user.getPasswdHashTxt();
				EncryptDecrypt encryptDecrypt = new EncryptDecrypt(secretKey);
				presentEncryptedPassword = encryptDecrypt.encryptText(crntpassword);*/
				 
				 String salt = user.getPasswordSalt();
				 String hash = user.getPasswordHash();
				 //boolean validated= EncryptDecrypt.validatePassword(password, hash, salt);
				 PBKDF2 pb= new PBKDF2();
				 boolean validated = pb.validatePWD(crntpassword, hash, salt);
					if(validated){
						userExist = true;
					}
				
			}
			
					
			if(user != null && user.getUserId() > 0 && userExist )
			{
				/*
				 * Commented by SASI on 24-03-2014
				 * @author <a href="sasi.itgrids.hyd@gmail.com">sasi.itgrids.hyd@gmail.com</a>
				 * 
				 */
				/*// secretKey = user.getHashKeyTxt();
				EncryptDecrypt encryptDecrypt = new EncryptDecrypt(secretKey);
				String encryptedPassword = encryptDecrypt.encryptText(newpassword);				
				user.setPasswdHashTxt(encryptedPassword);*/
				
				PBKDF2 pb=new PBKDF2();
				String storedPwd=pb.generatePassword(newpassword);
				String[] parts = storedPwd.split(":");
		        //int iterations = Integer.parseInt(parts[0]);
		        String passwordSalt=parts[1];
		        String passwordHash=parts[2];
				
				user.setPasswordHash(passwordHash);
				user.setPasswordSalt(passwordSalt);
				
				//user.setPassword(newpassword);
				user.setIsPwdChanged(IConstants.TRUE);
				user.setUpdatedDate(dateUtilService.getCurrentDateAndTime());
				user = userDAO.save(user);
				rs.setResultCode(ResultCodeMapper.SUCCESS);
			}
			else
				rs.setResultCode(ResultCodeMapper.FAILURE);
					} catch (Exception ex) {

						status.setRollbackOnly();
						ex.printStackTrace();
						log.error("Exception Raised :" + ex);
						rs.setExceptionEncountered(ex);
						rs.setExceptionMsg(ex.getMessage());
						rs.setResultCode(ResultCodeMapper.FAILURE);

						return rs;
					}
					return rs;
				}
			});

	return resultStatus;
		
	}
	
	//BY SAMBA
	
	public String checkUserCurrentPassword(String crntpassword,String userName)
	{
		
		boolean userExist = false;
		List<User> userList = userDAO.getUserByUserName(userName);
		
		if(userList!= null && userList.size() >0)
		{
			User user = userList.get(0);
			
			
			/*
			 * Commented by SASI on 24-03-2014
			 * @author <a href="sasi.itgrids.hyd@gmail.com">sasi.itgrids.hyd@gmail.com</a>
			 * 
			 */
			/*String secretKey = user.getHashKeyTxt();
			String savedPassword = user.getPasswdHashTxt();
			
			EncryptDecrypt phash = new EncryptDecrypt(secretKey);
			String encryptedPassword = phash.encryptText(crntpassword);
			
			if(encryptedPassword.equalsIgnoreCase(savedPassword))
				userExist = true;*/
			
			String salt = user.getPasswordSalt();
			String hash = user.getPasswordHash();
			//boolean validated= EncryptDecrypt.validatePassword(password, hash, salt);
			PBKDF2 pb= new PBKDF2();
			boolean validated = pb.validatePWD(crntpassword, hash, salt);
			if(validated){
				userExist = true;
			}
		}
		
		if(userExist == true)
			return IConstants.YesPassword;
		else
			return IConstants.NoPassword;
			
		
		
		/*List<Object> userId = userDAO.getUserIdByUserName(userName);
		Long registrationId = (Long) userId.get(0);
		List chkpwd = userDAO.checkUserPassword(crntpassword,registrationId);
		if(chkpwd.size() == 0)
			return IConstants.NoPassword;
		else 
			return IConstants.YesPassword;*/
		
	}
		
	/*public String checkUserCurrentPassword(String crntpassword,String userName)
	{
		List<Object> userId = userDAO.getUserIdByUserName(userName);
		Long registrationId = (Long) userId.get(0);
		List chkpwd = userDAO.checkUserPassword(crntpassword,registrationId);
		if(chkpwd.size() == 0)
			return IConstants.NoPassword;
		else 
			return IConstants.YesPassword;
		
	}*/
	
	/**
	 * This method will return User's Basic Details
	 * 
	 * @author kamalakar Dandu
	 * @param Long userId
	 * @return List
	 * 
	 */
	public RegistrationVO getUserBasicDetails(Long userId)
	{
		log.debug("Entered into getUserBasicDetails() Method");
		RegistrationVO registrationVO = null;
		
		try{
			User user = userDAO.get(userId);
			if(user != null)
			{
				SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
				registrationVO = new RegistrationVO();
				registrationVO.setRegistrationID(user.getUserId());
				registrationVO.setFirstName(user.getFirstName());
				registrationVO.setLastName(user.getLastName());
				registrationVO.setGender(user.getGender());
				registrationVO.setDateOfBirth(user.getDateOfBirth() != null ? sdf.format(user.getDateOfBirth()) : "");
				registrationVO.setEmail(user.getEmail());
				registrationVO.setMobile(user.getMobile());
				registrationVO.setAddress(user.getAddress());
				registrationVO.setPincode(user.getPincode());
				registrationVO.setAccessType(user.getAccessType());
				registrationVO.setAccessValue(user.getAccessValue());
				registrationVO.setParty(user.getParty() != null ?user.getParty().getPartyId() : null);
				registrationVO.setPartyShortName(user.getParty() != null ?user.getParty().getShortName() : "");
				registrationVO.setUserType(user.getUserType());
				registrationVO.setUserProfilePic(user.getProfileImg());
				
				Set<UserRoles> userRoles = user.getUserRoles();
				List<String> roles = new ArrayList<String>(0);
				
				if(userRoles != null)
					for(UserRoles userRole : userRoles)
						roles.add(userRole.getRole().getRoleType());
				
				registrationVO.setUserRoles(roles);
				
			}
			return registrationVO;
		}catch (Exception e) {
			log.error("Exception Occured in getUserBasicDetails() - "+e);
			return registrationVO;
		}
	}
	
	public SelectOptionVO getUserNameAndPWDByUserId(Long userId)
	{
		SelectOptionVO selectOptionVO = new SelectOptionVO();
		try{
			User user = userDAO.get(userId);
			selectOptionVO.setName(user.getUserName());
			selectOptionVO.setUrl((new EncryptDecrypt(user.getHashKeyTxt())).decryptText(user.getPasswdHashTxt()));
			return selectOptionVO;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getUserNameAndPWDByUserId() Method, Exception - "+e);
			return selectOptionVO;
		}
	}
	
	public Boolean checkForUserAccessIPAddress(Long userId, String ipAddress)
	{
		try{
			Long count = userAccessIpAddressDAO.checkForUserAccessIPAddress(userId, ipAddress);
		if(count != null && count.longValue() > 0)
			return true;
		else
			//sendMailToAdminGroup(registrationVO,IpAddress);
			return false;
		}catch (Exception e) {
			log.error("Exception Occured in checkForUserAccessIPAddress() Method, Exception - "+e);
			return false;
		}
		
	}

	public void sendMailToAdminGroup(final RegistrationVO registrationVO ,final String ipAddress)
	{
		
	       try{
	    	   log.info("sendMailToAdminGroup() Execution started");
				JavaMailSenderImpl javamailsender = new JavaMailSenderImpl();
				javamailsender.setSession(mailService.getSessionObject(IConstants.SERVER));
				MimeMessagePreparator preparator = new MimeMessagePreparator() {
			         public void prepare(MimeMessage mimeMessage) throws Exception {
			        	 SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
			        	 dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));

			        	 //Local time zone   
			        	 SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

			        	 //Time in GMT
			        	String  date= dateFormatGmt.format(new Date());

//	String  date= dateFormatGmt.format(new Date());
			            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
			              message.setTo(IJobConstants.ADMIN_GROUP_IDS);
			           // message.setTo(new String[]{"anil.itgrids.hyd@gmail.com","kamalakardandu@gmail.com"});
			            Map model = new HashMap();
			   		    model.put("myDate", date);
			   		    model.put("registrationVO", registrationVO);
			   		    model.put("ipAddress", ipAddress);
			            String text = VelocityEngineUtils.mergeTemplateIntoString(
			 	               velocityEngine,"userAccessIpViolation.vm", model);
			            message.setText(text, true);
			            message.setSubject("PartyAnalyst Accessed From UnAuthorized IpAdress ");
			            message.setFrom(IConstants.FROMEMAILID);
			         }
			      };
			      javamailsender.send(preparator);  
			      log.info("sendMailToAdmin() Execution completed successfully");
		     }catch(Exception e){
			log.error("Exception Rised in sendMailToAdmin : ", e);
		}
		}
	
	
	public List<String> deActivateAllOtherSimultaneousSessions(Long userId)
	{
	List<String> sessionIds = null;
	try{	
		if(userId != null && userId > 0)
		 sessionIds = userLoginDetailsDAO.getAllActiveUsersSessionIds(userId);
		if(sessionIds != null && sessionIds.size() > 0)
		{
			for(String sessionId : sessionIds)
			{
			AccessRestrictedSession accessRestrictedSession = new AccessRestrictedSession();
			accessRestrictedSession.setSessionId(sessionId);
			accessRestrictedSessionDAO.save(accessRestrictedSession);
			}
		}
	}
	catch (Exception e) {
		log.error("Exception Rised in getActiveUsersSessionIds : ", e);
	}
	return sessionIds;
	}
	public String getLocationNameByIdAndType(String accessType,String accessValue)
	{
		String locationName = "";
		try{
			if(accessType.equalsIgnoreCase(IConstants.STATE))
			{
			State state = stateDAO.get(new Long(accessValue));
			locationName = state.getStateName();
			}
			else if(accessType.equalsIgnoreCase(IConstants.DISTRICT))
			{
			District district = districtDAO.get(new Long(accessValue));
			locationName = district.getDistrictName();
			}
			else if(accessType.equalsIgnoreCase("MP") || accessType.equalsIgnoreCase("MLA") )
			{
			Constituency constituency = constituencyDAO.get(new Long(accessValue));
			locationName = constituency.getName();
			}
		}
		catch (Exception e) {
			log.error("Exception Rised in getLocationName : ", e);
		}
		return locationName;
	}
	
	
	public PeshiAppLoginVO getPeshiAppValidateLoginDetails(String userName,String password)
	{
		try{
			List<PashiAppUser> usersList =pashiAppUserDAO.checkUserPassword(userName, password);
			if(usersList != null && usersList.size()>0){
				PashiAppUser user = usersList.get(0);
				return new PeshiAppLoginVO(user.getPashiAppUserId(),user.getUserName(),"SUCCESS");
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Rised in getPeshiAppValidateLoginDetails : ", e);
		}
		 return new PeshiAppLoginVO("FAILURE");
	}
	public AmsAppLoginVO getAmsAppValidateLoginDetails(String userName,String password)
	{
		AmsAppLoginVO finalVo = new AmsAppLoginVO();
		User user = null;
		try{
			List<User> userObj=userDAO.getModelByUserName(userName.trim());
			
			if(userObj.size()==0){
				return finalVo;
			}
			PBKDF2 pb= new PBKDF2();
			
			//Generate Md5 Password
			String generatedPwd =  pb.generatePasswordByUser(userName.trim(),password.trim());//28b2086c174aad40b56afdaaf32538e2
			
			if(userObj.get(0).getPasswordHash() !=null && userObj.get(0).getPasswordSalt()!=null){
				String salt = userObj.get(0).getPasswordSalt();
				String hash = userObj.get(0).getPasswordHash();
				
				boolean validated = pb.validatePWD(generatedPwd, hash, salt);
				if(validated){
					user=userObj.get(0);
				}				
				if(user !=null){
					finalVo.setUserId(user.getUserId());
					finalVo.setUserName(user.getUserName());
					finalVo.setStatus("SUCCESS");
					
					//AlertManagementSystemService alertManagementSystemService = new AlertManagementSystemService();
					
					String mobileNo = alertManagementSystemService.getOfficerMobilenNo(user.getUserId());
					
					if(mobileNo !=null){
						alertManagementSystemService.generatingAndSavingOTPDetails(mobileNo);
						finalVo.setIsOtp("true");
					}
				}				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			finalVo.setStatus("FAILURE");
			log.error("Exception Rised in getAmsAppValidateLoginDetails(-,-)  in LoginService ", e);
		}
		 return finalVo;
	}
	
	public String getCheckUserNameAvailibility(String userName){
		String checkUserStatus = "";
		try {
		Log.info("Entered into loginService of getCheckUserNameAvailibility")	;
		List<Long> CheckuserIdsLst = userDAO.getCheckUserNameAvailibility(userName.trim());
		if(CheckuserIdsLst != null && CheckuserIdsLst.size()>0){
			checkUserStatus = "fail";
		}else{
			checkUserStatus = "success";
		}
		} catch (Exception e) {
			Log.error("Exception occured into loginService of getCheckUserNameAvailibility",e);
		}
		return checkUserStatus;
	}
}
