package com.itgrids.partyanalyst.notification.service.impl;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.notification.service.ISchedulerService;
import com.itgrids.partyanalyst.service.IMailsSendingService;
import com.itgrids.partyanalyst.service.IPartyCandidateSpecialPageScheduleService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class Scheduler {
	
	private static final Logger log = Logger.getLogger(Scheduler.class);
	private ISchedulerService schedulerService;
	private IMailsSendingService mailsSendingService;
	private DateUtilService dateUtilService = new DateUtilService();
	private IPartyCandidateSpecialPageScheduleService partyCandidateSpecialPageScheduleService;
	private IUserDAO userDAO;
	
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
	
	public IPartyCandidateSpecialPageScheduleService getPartyCandidateSpecialPageScheduleService() {
		return partyCandidateSpecialPageScheduleService;
	}

	public void setPartyCandidateSpecialPageScheduleService(
			IPartyCandidateSpecialPageScheduleService partyCandidateSpecialPageScheduleService) {
		this.partyCandidateSpecialPageScheduleService = partyCandidateSpecialPageScheduleService;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void runTheBatchJobForEveryDay()
	{
		if(!IConstants.DEFAULT_SCHEDULER_SEVER.equalsIgnoreCase(IConstants.SERVER))
			return;
		
		schedulerService.deleteSearchEngineAccessedURLsFromUserTracking(dateUtilService.getCurrentDateAndTime(),dateUtilService.getCurrentDateAndTime());
	}
	
	public void runTheBatchJobForEveryWeek()
	{
		if(!IConstants.DEFAULT_SCHEDULER_SEVER.equalsIgnoreCase(IConstants.SERVER))
			return;
		
		mailsSendingService.sendMailsToPasswordnotUpdatedusers();
	}
	
	public void sendDailyUpdates(){
		log.info("Enter into sendDailyUpdates()  for schedule jobs run ");
	   try{
		 userDAO.getTotalUsersCount();
	   }catch(Exception e){
		   log.error("Exception rised in sendDailyUpdates : ",e);
	   }
		partyCandidateSpecialPageScheduleService.sendUpdates();
	}
}
