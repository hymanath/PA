package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.UserGroupEntitlement;
import com.itgrids.partyanalyst.model.UserGroupRelation;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.utils.IConstants;

public class LoginService implements ILoginService{
	
	private IRegistrationDAO registrationDAO;
	
	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}
	
	public RegistrationVO checkForValidUser(String userName,String password){
		RegistrationVO regVO = new RegistrationVO();
		Registration reg = null;
		Set<UserGroupEntitlement> groupEntitlements = null;
		List<String> entitlements = new ArrayList<String>(0);
		List<SelectOptionVO> accessInfo = new ArrayList<SelectOptionVO>(0);
		SelectOptionVO selectOptionVO = null;
		try {
			List<Registration> registrations = registrationDAO.findByUserNameAndPassword(userName, password);
			
			if(registrations.size() != 1)
				return regVO;		
			
			reg = registrations.get(0);
			regVO.setRegistrationID(reg.getRegistrationId());
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
					selectOptionVO = new SelectOptionVO();
					if(IConstants.TRUE.equalsIgnoreCase(groupEntitlement.getIsAccessInfo()) && groupEntitlement.getUserAccessInfo() != null){
						selectOptionVO.setName(groupEntitlement.getGroupEntitlement().getDescription());
						selectOptionVO.setId(groupEntitlement.getUserAccessInfo().getAccessValue());
						accessInfo.add(selectOptionVO);
					}else
						entitlements.add(groupEntitlement.getGroupEntitlement().getDescription());
				}
			}
			
			regVO.setEntitlements(entitlements);
			regVO.setAccessInfo(accessInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return regVO;			
	
	}

}
