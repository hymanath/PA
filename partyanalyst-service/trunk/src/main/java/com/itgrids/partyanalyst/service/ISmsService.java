package com.itgrids.partyanalyst.service;

public interface ISmsService {
	
	public void sendSms(String message, boolean isEnglish,Long userId,String moduleName,String... phoneNumbers);

}
