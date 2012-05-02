package com.itgrids.partyanalyst.notification.service.impl;

import com.itgrids.partyanalyst.dto.EmailDetailsVO;
import com.itgrids.partyanalyst.notification.service.ISchedulerService;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.utils.IConstants;

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
