package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.RegistrationVO;

public interface ISendUpdatesService {
	
	public List<RegistrationVO> getAllUsersForSendSms();
	
	public List<RegistrationVO> sendSmsForAllUsersFromAdmin(String message);
	public String sendEmailsFromAdminToUsers(List<String> userId,String message,String subject);
	public List<RegistrationVO>  getUsersForSendingEmails(Long selectedState,Long selectedDistrict,Long selectedConstituency,Long userType,Long locationScope);


}
