package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.IMailsTemplateService;

public class MailsTemplateService implements IMailsTemplateService{

	public String getHeader(){
		
		
		String header = "<img src='http://www.partyanalyst.com/PartyAnalyst/images/icons/homePage_new/homePage_header_beta2.jpg' height='100px' width='100%'/>";
		
		return header;
	}
	public String getFooter(){
		String footer = "";
		return footer;
	}
}
