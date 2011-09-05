package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IGroupEntitlementDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserCountryAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserDistrictAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserStateAccessInfoDAO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Country;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.GroupEntitlementRelation;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.UserGroupEntitlement;
import com.itgrids.partyanalyst.model.UserGroupRelation;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.utils.IConstants;

public class LoginService implements ILoginService{
	
	private IRegistrationDAO registrationDAO;
	private IUserCountryAccessInfoDAO userCountryAccessInfoDAO;
	private IUserStateAccessInfoDAO userStateAccessInfoDAO;
	private IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO;
	private IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO;
	private IGroupEntitlementDAO groupEntitlementDAO;
	private IAnanymousUserDAO ananymousUserDAO;
	private ICountryDAO countryDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private ITehsilDAO tehsilDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	
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

	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}
	
	public IUserCountryAccessInfoDAO getUserCountryAccessInfoDAO() {
		return userCountryAccessInfoDAO;
	}

	public void setUserCountryAccessInfoDAO(
			IUserCountryAccessInfoDAO userCountryAccessInfoDAO) {
		this.userCountryAccessInfoDAO = userCountryAccessInfoDAO;
	}

	public IAnanymousUserDAO getAnanymousUserDAO() {
		return ananymousUserDAO;
	}

	public void setAnanymousUserDAO(IAnanymousUserDAO ananymousUserDAO) {
		this.ananymousUserDAO = ananymousUserDAO;
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

	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
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

	public RegistrationVO checkForValidUser(String userName,String password){
		RegistrationVO regVO = new RegistrationVO();
		Registration reg = null;
		Set<UserGroupEntitlement> groupEntitlements = null;
		Set<GroupEntitlementRelation> entitlementsModel = null;
		List<String> entitlements = new ArrayList<String>(0);
		Set<SelectOptionVO> countries = new HashSet<SelectOptionVO>(0);
		Set<SelectOptionVO> states = new HashSet<SelectOptionVO>(0);
		Set<SelectOptionVO> districts = new HashSet<SelectOptionVO>(0);
		Set<SelectOptionVO> assemblies = new HashSet<SelectOptionVO>(0);
		Set<SelectOptionVO> parliaments = new HashSet<SelectOptionVO>(0);
		try {
			List<Registration> registrations = registrationDAO.findByUserNameAndPassword(userName, password);
			
			if(registrations.size() != 1)
				return regVO;		

			reg = registrations.get(0);
			Long userId = reg.getRegistrationId();
			regVO.setRegistrationID(userId);
			regVO.setUserName(reg.getUserName());
			regVO.setEmail(reg.getEmail());
			regVO.setAccessType(reg.getAccessType());
			regVO.setAccessValue(reg.getAccessValue());
			regVO.setFirstName(reg.getFirstName());
			regVO.setLastName(reg.getLastName());
			regVO.setSubscribePartyImpDate(reg.getIncludePartyImpDateStatus());
			regVO.setUserType(reg.getUserType());
			regVO.setUserStatus(IConstants.PARTY_ANALYST_USER);
			regVO.setParentUserId(reg.getParentUser() != null?reg.getParentUser().getRegistrationId():null);
			regVO.setMainAccountId(reg.getMainAccountUser() != null ? reg.getMainAccountUser().getRegistrationId() : null);
						
			if(reg.getParty() != null){
				regVO.setParty(reg.getParty().getPartyId());
				regVO.setPartyShortName(reg.getParty().getShortName());
			}
			
			Set<UserGroupRelation> userGroups = reg.getUserGroupRelations();
			
			for(UserGroupRelation groupRelation:userGroups){
				groupEntitlements = groupRelation.getUserGroup().getUserGroupEntitlement();
				for(UserGroupEntitlement userGroupEntitlement:groupEntitlements){
					entitlementsModel = userGroupEntitlement.getGroupEntitlement().getGroupEntitlementRelations();
					for(GroupEntitlementRelation entitlement:entitlementsModel)
						entitlements.add(entitlement.getEntitlement().getEntitlementType());
				}
			}
			
			getUserAccessInfo(userId, countries, states, districts, assemblies, parliaments);
			
			if(entitlements.contains(IConstants.ADMIN_PAGE))
				regVO.setIsAdmin(IConstants.TRUE);	
			else
				regVO.setIsAdmin(IConstants.FALSE);	
			
			regVO.setEntitlements(entitlements);
			regVO.setCountries(countries);
			regVO.setStates(states);
			regVO.setDistricts(districts);
			regVO.setAssemblies(assemblies);
			regVO.setParliaments(parliaments);
			
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
		List<AnanymousUser> anamymousUser = ananymousUserDAO.checkAnonymousUserLogin(userName, password);
		
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
}
