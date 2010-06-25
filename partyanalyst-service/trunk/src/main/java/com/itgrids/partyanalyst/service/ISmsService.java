package com.itgrids.partyanalyst.service;

public interface ISmsService {
	
	public Long sendSms(String message, boolean isEnglish,Long userId,String moduleName,String... phoneNumbers);

	public Long getRemainingSmsLeftForUser(Long userId);
	
	public String getCurrentDate();
}
