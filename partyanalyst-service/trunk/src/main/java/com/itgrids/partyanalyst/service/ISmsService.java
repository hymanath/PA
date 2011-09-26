package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.SmsTrackVO;

public interface ISmsService {
	
	public Long sendSms(String message, boolean isEnglish,Long userId,String moduleName,String... phoneNumbers);

	public Long getRemainingSmsLeftForUser(Long userId);
	
	public String getCurrentDate();
	
	public SmsTrackVO updateUserMessageCreditDetail(SmsTrackVO smsTrackVO);
		
	public SmsTrackVO getUserMessageCreditDetail(Long userId);
}
