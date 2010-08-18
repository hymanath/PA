package com.itgrids.partyanalyst.service.impl;

import java.util.List;

import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.service.ILoginService;

public class LoginService implements ILoginService{
	
	private IRegistrationDAO registrationDAO;
	private String name = null; 
	
	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}
	
	public RegistrationVO checkForValidUser(String userName,String password){
		
		List<Registration> registrations = registrationDAO.findByUserNameAndPassword(userName, password);

		RegistrationVO regVO = new RegistrationVO();
		if(registrations.size() == 0)
			name = " ";
		else{
			Registration reg = registrations.get(0);
			regVO.setRegistrationID(reg.getRegistrationId());
			regVO.setUserName(reg.getUserName());
			regVO.setAccessType(reg.getAccessType());
			regVO.setAccessValue(reg.getAccessValue());
			regVO.setFirstName(reg.getFirstName());
			regVO.setLastName(reg.getLastName());
			regVO.setSubscribePartyImpDate(reg.getIncludePartyImpDateStatus());
			if(reg.getParty() != null){
				regVO.setParty(reg.getParty().getPartyId());
			}
			regVO.setUserType(reg.getUserType());
			
			
			//name = reg.getFirstName() + " " + reg.getLastName();
		}
		return regVO;
	}

}
