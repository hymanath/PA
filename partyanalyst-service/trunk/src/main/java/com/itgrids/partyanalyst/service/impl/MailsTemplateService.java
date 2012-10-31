package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.IMailsTemplateService;

public class MailsTemplateService implements IMailsTemplateService{

	public String getHeader(){
		
		
		String header = "<img src='http://www.partyanalyst.com/images/icons/homePage_new/homePage_header_beta2.jpg' height='100px' width='100%'/>";
		
		return header;
	}
	public String getFooter(){
		String sendMail="mailto:info@partyanalyst.com";
		String footer = "<div style='line-height: 1.8em;margin-left: 45px; margin-top: -26px; margin-right: 3px;'>" +
				"<br>Thanks," +
				"<br>Party Analyst Team<br>" +
				"<a href='http://www.partyanalyst.com/homePage.action'>www.partyanalyst.com</a><br>" +
				"<div><p><b>PS:&nbsp;</b>Please add this email to your address book so that the emails from us don\'t end up in your junk folder.For suggestions or support contact us at <b><a href="+sendMail+">info@partyanalyst.com</a></p></div></div>";
		return footer;
	}
}
