package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.model.Registration;

public class LoginService implements ILoginService{
	
	private IRegistrationDAO registrationDAO;
	private String name = null; 
	
	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}
	

	public String checkForValidUser(String userName,String password){
		
		List<Registration> registrations = registrationDAO.findByUserNameAndPassword(userName, password);
		
		if(registrations.size() == 0)
			name = " ";
		else{
			Registration reg = registrations.get(0);
			name = reg.getFirstName() + " " + reg.getLastName();
		}
		return name;
	}

}
