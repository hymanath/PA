package com.itgrids.partyanalyst.notification.service.impl;
import com.itgrids.partyanalyst.notification.service.ISchedulerService;
import com.itgrids.partyanalyst.service.IMailsSendingService;

public class Scheduler {
	
	private ISchedulerService schedulerService;
	private IMailsSendingService mailsSendingService;
	
	public IMailsSendingService getMailsSendingService() {
		return mailsSendingService;
	}

	public void setMailsSendingService(IMailsSendingService mailsSendingService) {
		this.mailsSendingService = mailsSendingService;
	}

	public ISchedulerService getSchedulerService() {
		return schedulerService;
	}
	
	public void setSchedulerService(ISchedulerService schedulerService) {
		this.schedulerService = schedulerService;
	}
	
	public void runTheBatchJobForEveryDay()
	{
		schedulerService.deleteSearchEngineAccessedURLsFromUserTracking();
	}
	
	public void runTheBatchJobForEveryWeek()
	{
		mailsSendingService.sendMailsToPasswordnotUpdatedusers();
	}
}
