package com.itgrids.partyanalyst.notification.service.impl;

import com.itgrids.partyanalyst.notification.service.ISchedulerService;
import com.itgrids.partyanalyst.service.IMailService;

public class SchedulerService implements ISchedulerService{
	
	private IMailService mailService;
	
	public IMailService getMailService() {
		return mailService;
	}

	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}

	public void sendingMails()
	{
		
	}
}
