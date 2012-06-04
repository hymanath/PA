package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.RegistrationVO;

public interface ISendUpdatesService {
	
	public List<RegistrationVO> getAllUsersForSendSms();
	
	public List<RegistrationVO> sendSmsForAllUsersFromAdmin(String message);

}
