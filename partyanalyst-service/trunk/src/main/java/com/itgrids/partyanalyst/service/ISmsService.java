package com.itgrids.partyanalyst.service;

public interface ISmsService {
	
	public void sendSms(String message, boolean isEnglish, String... phoneNumbers);

}
