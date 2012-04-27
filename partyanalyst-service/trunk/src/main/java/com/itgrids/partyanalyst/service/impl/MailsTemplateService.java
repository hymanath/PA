package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.IMailsTemplateService;

public class MailsTemplateService implements IMailsTemplateService{

	public String getHeader(){
		
		
		String header = "<img src='http://www.partyanalyst.com/PartyAnalyst/images/icons/homePage_new/homePage_header_beta2.jpg' width='600px' height='100px'/>";
		
		return header;
	}
	public String getFooter(){
		String footer = "";
		return footer;
	}
}
