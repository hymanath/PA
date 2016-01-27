package com.itgrids.partyanalyst.service;

public interface ISmsGatewayService {
	
	public String sendSMS(String mobileNo,String message,String username,String password);

}
