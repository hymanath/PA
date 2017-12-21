package com.itgrids.prrws.notification.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.itgrids.dto.InputVO;
import com.itgrids.service.IItcDashboardService;
import com.itgrids.service.ILightMonitoring;
import com.itgrids.tpi.rws.service.IRWSNICService;
import com.itgrids.tpi.rws.service.IRwsWorksSchedulerService;
import com.itgrids.utils.IConstants;

@Configuration
@EnableScheduling
public class Scheduler {
	
	private static final Logger LOG = Logger.getLogger(Scheduler.class);
	@Autowired
	private ILightMonitoring lightMonitoringService;
    @Autowired
    private IRWSNICService rWSNICService;
    @Autowired
	private IRwsWorksSchedulerService rwsWorksSchedulerService;
    @Autowired
  	private IItcDashboardService itcDashboardService;
	
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
	
	@Scheduled(cron ="0 0 0/4 * * ?")
	//@Scheduled(cron ="0 0/20 * * * ?")
	public void runTheExceededWorksForEveryOneHour()
	{
		
			LOG.error("Cron Job For worksInsertion Started");
			
			InputVO input= new InputVO();
			List<String> statusList = new ArrayList<String>();
			statusList.add("completed");
			statusList.add("ongoing");
			statusList.add("not grounded");
			statusList.add("commissioned");
			input.setFromDateStr("01-01-1977");
			input.setToDateStr("31-12-2027");
			input.setLocationType("state");
			input.setStatusList(statusList);
			
			rwsWorksSchedulerService.getWorksDataInsertion(input);
			LOG.error("Cron Job For worksInsertion Completed");
		
			return;
	}
	
	@Scheduled(cron ="0 0 0/2 * * ?")

	public void runTheSchedulerForEvryTwoHrs()
	{
		if(IConstants.DEFAULT_SCHEDULER_SEVER.equalsIgnoreCase(IConstants.SERVER))
		{	
			LOG.error("Cron Job For ITE&C E-Ofc Started");
			itcDashboardService.savingEofcDataDetails();
			LOG.error("Cron Job For ITE&C E-Ofc Completed");
		}
		else 
			return;
	}
	
}
