package com.itgrids.partyanalyst.notification.service.impl;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dto.EmailDetailsVO;
import com.itgrids.partyanalyst.dto.ProblemDetailsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.notification.service.ISchedulerService;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IMailsSendingService;
import com.itgrids.partyanalyst.service.IMobileService;
import com.itgrids.partyanalyst.service.IPartyCandidateSpecialPageScheduleService;
import com.itgrids.partyanalyst.service.IVoterReportService;
import com.itgrids.partyanalyst.service.impl.MobileService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class Scheduler {
	
	private static final Logger log = Logger.getLogger(Scheduler.class);
	private ISchedulerService schedulerService;
	private IMailsSendingService mailsSendingService;
	private DateUtilService dateUtilService = new DateUtilService();
	private IPartyCandidateSpecialPageScheduleService partyCandidateSpecialPageScheduleService;
	private IUserDAO userDAO;
	private IVoterReportService voterReportService;
	private IMobileService mobileService;
	private IMailService mailService;
	private ResultStatus rs;
	
	public ResultStatus getRs() {
		return rs;
	}

	public void setRs(ResultStatus rs) {
		this.rs = rs;
	}

	public IMailService getMailService() {
		return mailService;
	}

	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}

	public IMobileService getMobileService() {
		return mobileService;
	}

	public void setMobileService(IMobileService mobileService) {
		this.mobileService = mobileService;
	}

	public IVoterReportService getVoterReportService() {
		return voterReportService;
	}

	public void setVoterReportService(IVoterReportService voterReportService) {
		this.voterReportService = voterReportService;
	}

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
	
	public void runTheBatchJobForInsertCasteForEveryDay()
	{
		try{
			if(!IConstants.DEFAULT_SCHEDULER_SEVER.equalsIgnoreCase(IConstants.SERVER))
				return;
			rs = mailService.sendEmailStatusForJob("Caste data inserting job started");
		voterReportService.getCasteVotersAvailableConstituencyIds();
		rs = mailService.sendEmailStatusForJob("Caste data inserting job end");
		}
		catch(Exception e)
		{
			 log.error("Exception rised in runTheBatchJobForInsertCasteForEveryDay : ",e);
		}
	}
	
	public void runTheBatchJobTosendSmsToMobileAppUser()
	{
		try{
			if(!IConstants.DEFAULT_SCHEDULER_SEVER.equalsIgnoreCase(IConstants.SERVER))
				return;
		mobileService.getMobileAppLastAuthorisedTime();
		}
		catch(Exception e)
		{
			 log.error("Exception rised in runTheBatchJobTosendSmsToMobileAppUser : ",e);
		}
	}
	
	
	public void runTheBatchJobToPopulateVoterData()
	{
		try{
			/*if(!IConstants.DEFAULT_SCHEDULER_SEVER.equalsIgnoreCase(IConstants.SERVER))
				return;*/
			rs = mailService.sendEmailStatusForJob("Populate Voter data job started");
		     mobileService.populateVoterData();
		    rs = mailService.sendEmailStatusForJob("Populate Voter data job end");
		}
		catch(Exception e)
		{
			 log.error("Exception rised in runTheBatchJobTosendSmsToMobileAppUser : ",e);
		}
	}
	
	
}
