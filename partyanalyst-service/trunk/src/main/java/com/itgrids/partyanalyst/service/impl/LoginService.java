package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserCountryAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserDistrictAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserStateAccessInfoDAO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Entitlement;
import com.itgrids.partyanalyst.model.Registration;
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

	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}

	public RegistrationVO checkForValidUser(String userName,String password){
		RegistrationVO regVO = new RegistrationVO();
		Registration reg = null;
		Set<UserGroupEntitlement> groupEntitlements = null;
		Set<Entitlement> entitlemetnsModel = null;
		List<String> entitlements = new ArrayList<String>(0);
		List<SelectOptionVO> countries = new ArrayList<SelectOptionVO>(0);
		List<SelectOptionVO> states = new ArrayList<SelectOptionVO>(0);
		List<SelectOptionVO> districts = new ArrayList<SelectOptionVO>(0);
		List<SelectOptionVO> assemblies = new ArrayList<SelectOptionVO>(0);
		List<SelectOptionVO> parliaments = new ArrayList<SelectOptionVO>(0);
		try {
			List<Registration> registrations = registrationDAO.findByUserNameAndPassword(userName, password);
			
			if(registrations.size() != 1)
				return regVO;		
			

			reg = registrations.get(0);
			Long userId = reg.getRegistrationId();
			regVO.setRegistrationID(userId);
			regVO.setUserName(reg.getUserName());
			regVO.setAccessType(reg.getAccessType());
			regVO.setAccessValue(reg.getAccessValue());
			regVO.setFirstName(reg.getFirstName());
			regVO.setLastName(reg.getLastName());
			regVO.setSubscribePartyImpDate(reg.getIncludePartyImpDateStatus());
			regVO.setUserType(reg.getUserType());
			
			if(reg.getParty() != null){
				regVO.setParty(reg.getParty().getPartyId());
				regVO.setPartyShortName(reg.getParty().getShortName());
			}
			
			Set<UserGroupRelation> userGroups = reg.getUserGroupRelations();
			
			for(UserGroupRelation groupRelation:userGroups){
				groupEntitlements = groupRelation.getUserGroup().getUserGroupEntitlement();
				for(UserGroupEntitlement groupEntitlement:groupEntitlements){
					entitlemetnsModel = groupEntitlement.getGroupEntitlement().getEntitlements();
					for(Entitlement entitlement:entitlemetnsModel)
						entitlements.add(entitlement.getEntitlementType());
				}
			}
			
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
	
	private void getListFromRawdata(List rawData, List<SelectOptionVO> locationList){
		if(rawData.size() > 0)
			for(Object[] values:(List<Object[]>)rawData)
				locationList.add(new SelectOptionVO(new Long(values[0].toString()), values[1].toString()));
	}

}
