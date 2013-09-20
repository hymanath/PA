package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface ISendUpdatesService {
	
	public List<RegistrationVO> getAllUsersForSendSms();
	
	public List<RegistrationVO> sendSmsForAllUsersFromAdmin(String message);
	public String sendEmailsFromAdminToUsers(List<String> userId,String message,String subject);
	//public List<RegistrationVO>  getUsersForSendingEmails(Long selectedState,Long selectedDistrict,Long selectedConstituency,Long userType,Long locationScope,String freeUserType);
	public List<RegistrationVO>  getUsersForSendingEmail(RegistrationVO regiVO,String order,String sortBy,int startIndex,int maxIndex);
	public ResultStatus sendSMSToSelectedPeople(Long userId,String scope,Long scopeId,String content,String type);
	public Long getLatestPublicationDateId();
	public List<SelectOptionVO> getBoothsForWardId(Long wardId,Long publicationDateId);
}
