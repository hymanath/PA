package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;

public interface ISendUpdatesService {
	
	public List<RegistrationVO> getAllUsersForSendSms();
	
	public List<RegistrationVO> sendSmsForAllUsersFromAdmin(String message);
	public String sendEmailsFromAdminToUsers(List<String> userId,String message,String subject);
	public List<RegistrationVO>  getUsersForSendingEmails(Long selectedState,Long selectedDistrict,Long selectedConstituency,Long userType,Long locationScope);
	public ResultStatus sendSMSToSelectedPeople(Long userId,String scope,Long scopeId,String content,String type);
	public Long getLatestPublicationDateId();
	public List<SelectOptionVO> getBoothsForWardId(Long wardId,Long publicationDateId);
}
