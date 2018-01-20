package com.itgrids.prrws.notification.service.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.itgrids.dto.InputVO;
import com.itgrids.service.IConstituencyWiseWorkStatusService;
import com.itgrids.service.IItcDashboardService;
import com.itgrids.service.ILightMonitoring;
import com.itgrids.tpi.rws.service.IRWSNICService;
import com.itgrids.tpi.rws.service.IRwsWorksSchedulerService;
import com.itgrids.utils.DateUtilService;
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
    @Autowired
    private IConstituencyWiseWorkStatusService constituencyWiseWorkStatusService;
    
	
	//@Scheduled(cron = "0 30 2,14 * * * ")
    //run scheduler every 15 minutes
	@Scheduled(cron ="0 0/15 * ? * *")
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
		if(IConstants.DEFAULT_SCHEDULER_SEVER.equalsIgnoreCase(IConstants.SERVER))
		{
			LOG.error("Cron Job For worksInsertion Started");
			
			InputVO input= new InputVO();
			List<String> statusList = new ArrayList<String>();
			statusList.add("completed");
			statusList.add("ongoing");
			statusList.add("commissioned");
			input.setFromDateStr("01-01-1977");
			input.setToDateStr("31-12-2027");
			input.setLocationType("state");
			input.setStatusList(statusList);
			
			String result = rwsWorksSchedulerService.getWorksDataInsertion(input);
			if(result.toString().equalsIgnoreCase("success")){
				String dropResult = rwsWorksSchedulerService.getWorksDataDeletion();
				if(dropResult.trim().equalsIgnoreCase("success")){
					LOG.error("Cron Job For worksRemoved Completed");
				}else{
					LOG.error("Cron Job dropResult issue"+dropResult);
				}
			}
			LOG.error("Cron Job For worksInsertion Completed");
		
		}
		else 
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
	
	@Scheduled(cron ="0 0 0/4 * * ?")
	public void runTheSchedulerForENCEvryTwoHrs()
	{
		if(IConstants.DEFAULT_SCHEDULER_SEVER.equalsIgnoreCase(IConstants.SERVER))
		{	
			LOG.error("Cron Job For E&D Started");
			rwsWorksSchedulerService.getEncworkDataInsertion();
			LOG.error("Cron Job For E&D Completed");
		}
		else 
			return;
	}
	
	@Scheduled(cron ="0 6 * ? * *")
	public void runTheSchedulerForEvry24HrsAt6AM()
	{
		if(IConstants.DEFAULT_SCHEDULER_SEVER.equalsIgnoreCase(IConstants.SERVER))
		{	
			LOG.error("Cron Job For ITE&C Meeseva KPI Started");
			String[] districtIdsArr =  {"01","02","03","04","05","06","07","08","09","10","11","12","13"};
			List<String> districtIds = new ArrayList<String>(0);
			if(districtIdsArr != null && districtIdsArr.length > 0){
				for (int i = 0; i < districtIdsArr.length; i++) {
					districtIds.add(districtIdsArr[i]);
				}
			}
			if(districtIds != null && !districtIds.isEmpty()){
				for (String districtIdStr : districtIds) {
					itcDashboardService.saveMeesevaKPIDetails(districtIdStr);
				}
			}
			LOG.error("Cron Job For ITE&C Meeseva KPI Completed");
		}
		else 
			return;
	}
	
	@Scheduled(cron ="0 4 * ? * *")
	public void runTheSchedulerForEveryDayAt4AM()
	{
		if(IConstants.DEFAULT_SCHEDULER_SEVER.equalsIgnoreCase(IConstants.SERVER))
		{	
			LOG.error("Cron Job For Labour Budget Panchayat Vs Expenditure Details Started");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			InputVO inputVO = new InputVO();
			inputVO.setYear("2017");
			inputVO.setFromDate("2017-04-01");
			inputVO.setToDate(sdf.format(new DateUtilService().getCurrentDateInDateFormat()));
			inputVO.setLocationType("state");
			inputVO.setLocationId(0L);
			constituencyWiseWorkStatusService.savingLabourBudgetRangeWiseExpenditureDetailsEveryDay(inputVO);
			LOG.error("Cron Job For Labour Budget Panchayat Vs Expenditure Details Completed");
		}
		else 
			return;
	}
}
