package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.RegistrationVO;

public interface ILoginService {
	
	public RegistrationVO checkForValidUser(String userName,String password);
	
}
