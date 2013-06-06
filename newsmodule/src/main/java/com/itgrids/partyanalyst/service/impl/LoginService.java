package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IGroupEntitlementDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAccessIpAddressDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserCountryAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserDistrictAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserLoginDetailsDAO;
import com.itgrids.partyanalyst.dao.IUserRolesDAO;
import com.itgrids.partyanalyst.dao.IUserStateAccessInfoDAO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Country;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.GroupEntitlementRelation;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

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
	public static Logger LOG = Logger.getLogger(UserService.class); 

	private IMailService mailService;
	
	
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
	
	/*
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
			
			List<String> secretKeyList = userDAO.getEncryptedKeyByUserName(userName);
			String secretKey = null;
			if(secretKeyList != null && secretKeyList.size() > 0){
				
				secretKey = secretKeyList.get(0);
				
				 EncryptDecrypt phash = new EncryptDecrypt(secretKey);
				String encrptdPassword = phash.encryptText(password);;
				List<User> userList = userDAO.checkUsernameAndEncryptedPasswordForUser(userName,
						encrptdPassword);
				
				if(userList != null  && userList.size() >0)
					user = userList.get(0);
			}

			//user = userDAO.findByUserNameAndPassword(userName, password);
			
			if(user == null ||  user.getUserId() <= 0)
				return regVO;
			else if(user!=null){
				if(!(user.getPassword().equals(password)))			
				return regVO;
			}
			Long userId = user.getUserId();
			regVO.setRegistrationID(userId);
			regVO.setFirstName(user.getFirstName());
			regVO.setLastName(user.getLastName());
			regVO.setUserName(user.getUserName());
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
				regVO.setSubscribePartyImpDate(user.getIncludePartyImpDateStatus());
				regVO.setParentUserId(user.getParentUser() != null?user.getParentUser().getUserId():null);
				regVO.setMainAccountId(user.getMainAccountUser() != null ? user.getMainAccountUser().getUserId() : null);
							
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
				
				regVO.setEntitlements(entitlements);
				regVO.setCountries(countries);
				regVO.setStates(states);
				regVO.setDistricts(districts);
				regVO.setAssemblies(assemblies);
				regVO.setParliaments(parliaments);
				
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
	
	
	private void getListFromRawdata(List rawData, Set<SelectOptionVO> locationList){
		if(rawData.size() > 0)
			for(Object[] values:(List<Object[]>)rawData)
				locationList.add(new SelectOptionVO(new Long(values[0].toString()), values[1].toString()));
	}

	public RegistrationVO checkForValidNormalUser(String userName,
			String password) {
		RegistrationVO regVO = new RegistrationVO();
		List<AnanymousUser> anamymousUser = new ArrayList<AnanymousUser>(0);
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
		regVO.setEmail(user.getEmail());
		
		return regVO;
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
	
	
	public ResultStatus changePasswordOfANewUser(String crntpassword,String newpassword,String userName)
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
	}
	
	public ResultStatus changePasswordOfANewUser(String crntpassword,String newpassword,String userName)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			List<User> list = userDAO.getUserByUserName(userName);
			User user = null;
			String secretKey = null;
			String presentEncryptedPassword = null;
			String savedEncryptedPassword = null;
			
			
			if(list != null && list.size() > 0){
				user = list.get(0);
				secretKey = user.getHashKeyTxt();
				 savedEncryptedPassword = user.getPasswdHashTxt();
				EncryptDecrypt encryptDecrypt = new EncryptDecrypt(secretKey);
				presentEncryptedPassword = encryptDecrypt.encryptText(crntpassword);
				
			}
			
			if(user != null && user.getUserId() > 0 && savedEncryptedPassword.equals(presentEncryptedPassword))
			{
				// secretKey = user.getHashKeyTxt();
				EncryptDecrypt encryptDecrypt = new EncryptDecrypt(secretKey);
				String encryptedPassword = encryptDecrypt.encryptText(newpassword);				
				user.setPasswdHashTxt(encryptedPassword);
				
				//user.setPassword(newpassword);
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
	}
	
	//BY SAMBA
	
	public String checkUserCurrentPassword(String crntpassword,String userName)
	{
		
		boolean userExist = false;
		List<User> userList = userDAO.getUserByUserName(userName);
		
		if(userList!= null && userList.size() >0)
		{
			User user = userList.get(0);
			
			String secretKey = user.getHashKeyTxt();
			String savedPassword = user.getPasswdHashTxt();
			
			EncryptDecrypt phash = new EncryptDecrypt(secretKey);
			String encryptedPassword = phash.encryptText(crntpassword);
			
			if(encryptedPassword.equalsIgnoreCase(savedPassword))
				userExist = true;
		}
		
		if(userExist == true)
			return IConstants.YesPassword;
		else
			return IConstants.NoPassword;
			
		
		
		List<Object> userId = userDAO.getUserIdByUserName(userName);
		Long registrationId = (Long) userId.get(0);
		List chkpwd = userDAO.checkUserPassword(crntpassword,registrationId);
		if(chkpwd.size() == 0)
			return IConstants.NoPassword;
		else 
			return IConstants.YesPassword;
		
	}
		
	public String checkUserCurrentPassword(String crntpassword,String userName)
	{
		List<Object> userId = userDAO.getUserIdByUserName(userName);
		Long registrationId = (Long) userId.get(0);
		List chkpwd = userDAO.checkUserPassword(crntpassword,registrationId);
		if(chkpwd.size() == 0)
			return IConstants.NoPassword;
		else 
			return IConstants.YesPassword;
		
	}
	
	*//**
	 * This method will return User's Basic Details
	 * 
	 * @author kamalakar Dandu
	 * @param Long userId
	 * @return List
	 * 
	 *//*
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
	
*/
	public ResultStatus checkForUserNameAndPassword(String username,String password)
	{
		ResultStatus resultStatus = null;
		try {
			LOG.debug("entered into checkForUserNameAndPassword() method in UserService Class");
			Long  userdetails = userDAO.checkForUserNameAndPasswordAvaliablity(username,password);
			if(userdetails == 1)
			{
				resultStatus = new ResultStatus();
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				
			}
			else
			{
				resultStatus = new ResultStatus();
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			}
		} catch (Exception e) {
			LOG.error("exception raised in checkForUserNameAndPassword() method in UserService Class", e);
		}
		
		return resultStatus;
	}
	
	public RegistrationVO getUserByUserNameAndPassword(String username,String password)
	{
		RegistrationVO regVo = null;
		try {
			LOG.debug("entered into getUserByUserNameAndPassword() method in UserService Class");
			User  userdetails = userDAO.getUserByUserNameAndPassword(username,password);
			if(userdetails != null)
			{
				 regVo = new RegistrationVO();
				 if("Admin".equalsIgnoreCase(userdetails.getUserType()) )
				   regVo.setUserType("Admin");
				 else if("subuser".equalsIgnoreCase(userdetails.getUserType()) )
				   regVo.setUserType("subuser");
				 else
					 regVo.setUserType("");
				 regVo.setName("Welcome "+userdetails.getFirstName()+" "+userdetails.getLastName());
				 
			}
		} catch (Exception e) {
			LOG.error("exception raised in getUserByUserNameAndPassword() method in UserService Class", e);
		}
		
		return regVo;
	}
}
