package com.itgrids.partyanalyst.notification.service.impl;
import com.itgrids.partyanalyst.notification.service.ISchedulerService;
import com.itgrids.partyanalyst.service.IMailsSendingService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class Scheduler {
	
	private ISchedulerService schedulerService;
	private IMailsSendingService mailsSendingService;
	private DateUtilService dateUtilService = new DateUtilService();
	
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
		schedulerService.deleteSearchEngineAccessedURLsFromUserTracking(dateUtilService.getCurrentDateAndTime(),dateUtilService.getCurrentDateAndTime());
	}
	
	public void runTheBatchJobForEveryWeek()
	{
		mailsSendingService.sendMailsToPasswordnotUpdatedusers();
	}
}
