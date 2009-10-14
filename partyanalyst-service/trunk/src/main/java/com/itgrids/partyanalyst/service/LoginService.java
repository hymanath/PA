package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.model.Registration;

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
			
			//name = reg.getFirstName() + " " + reg.getLastName();
		}
		return regVO;
	}

}
