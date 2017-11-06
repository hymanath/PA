package com.itgrids.prrws.notification.service.impl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.itgrids.service.ILightMonitoring;
import com.itgrids.tpi.rws.service.IRWSNICService;
import com.itgrids.utils.IConstants;

@Configuration
@EnableScheduling
public class Scheduler {
	
	private static final Logger LOG = Logger.getLogger(Scheduler.class);
	@Autowired
	private ILightMonitoring lightMonitoringService;
    @Autowired
    private IRWSNICService rWSNICService;
	
	@Scheduled(cron = "0 30 2,14 * * * ")
	public void runTheSchedulerEveryDay()
	{
		if(IConstants.DEFAULT_SCHEDULER_SEVER.equalsIgnoreCase(IConstants.SERVER))
		{	
			LOG.error("Cron Job For LED Dashboard Started");
			lightMonitoringService.saveRealtimeStatusByVillages();
			LOG.error("Cron Job For LED Dashboard Completed");
		}
		else 
			return;
	}

	@Scheduled(cron ="0 */30 * ? * *")

	public void runTheSchedulerEveryThirthyMinutes()
	{
		if(IConstants.DEFAULT_SCHEDULER_SEVER.equalsIgnoreCase(IConstants.SERVER))
		{	
			LOG.error("Cron Job For webServiceHealth Started");
			rWSNICService.getWebserviceDetails();
			LOG.error("Cron Job For webServiceHealth Completed");
		}
		else 
			return;
	}
	
}
