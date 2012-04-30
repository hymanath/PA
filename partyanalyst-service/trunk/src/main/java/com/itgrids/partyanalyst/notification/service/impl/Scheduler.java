package com.itgrids.partyanalyst.notification.service.impl;
import com.itgrids.partyanalyst.notification.service.ISchedulerService;

public class Scheduler {
private ISchedulerService schedulerService;

public ISchedulerService getSchedulerService() {
	return schedulerService;
}

public void setSchedulerService(ISchedulerService schedulerService) {
	this.schedulerService = schedulerService;
}
public void runningBatchJobMethods(){
	schedulerService.sendingMails();
}
}
