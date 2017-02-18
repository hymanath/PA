package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.MobileAppUserVoterVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.SmsHistory;

public interface ISmsSenderService {
	public ResultStatus sendSmsToCadre(MobileAppUserVoterVO inputVO);
	public ResultStatus sendSmsToVoter(MobileAppUserVoterVO inputVO);
	//public ResultStatus sendSmsToCadre(Integer startIndex,Integer maxIndex);
	public String sendSMSForTrainingCampFeedBackMember(Long userId,String moduleName ,boolean isEnglish,String message,String phoneNumbers);
	public SmsHistory sendSMS(Long userId,String moduleName ,boolean isEnglish, String messageStr,String mobileNos);
	public boolean sendSmsForAssignedLeader(String message, boolean isEnglish,String mobilenumber);
}
