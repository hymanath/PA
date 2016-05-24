package com.itgrids.partyanalyst.notification.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.notification.service.ISchedulerService;
import com.itgrids.partyanalyst.service.IMahaNaduService;
import com.itgrids.partyanalyst.service.IMahanaduDashBoardService1;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IMailsSendingService;
import com.itgrids.partyanalyst.service.IMobileService;
import com.itgrids.partyanalyst.service.IPartyCandidateSpecialPageScheduleService;
import com.itgrids.partyanalyst.service.IVoterReportService;
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
    private IMahaNaduService mahaNaduService;
    
    
	public IMahaNaduService getMahaNaduService() {
		return mahaNaduService;
	}

	public void setMahaNaduService(IMahaNaduService mahaNaduService) {
		this.mahaNaduService = mahaNaduService;
	}
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
	
	public void runTheBatchJobForEveryDayMobilesInsert()
	{
		schedulerService.saveDailyWmCorrectedMobileNUmbers(dateUtilService.getCurrentDateAndTime());
	}
	
	
	/*public void runTheBatchJobForEveryDayCardDataInsert()
	{
		schedulerService.prepareDatForCardPrinting(dateUtilService.getYesterdayDateString());
	}
	
	public void runTheBatchJobForEveryDayCardDataInsertForZebra()
	{
		schedulerService.prepareDatForCardPrintingForZebra(dateUtilService.getYesterdayDateString());
	}
	
	public void runTheBatchJobForEveryDayCardDataInsertForMax()
	{
		schedulerService.prepareDatForCardPrintingForMax(dateUtilService.getYesterdayDateString());
	}*/
	
	public void schedularForCardPrintDataFilling()
	{
		schedulerService.schedularForCardPrintDataFilling(dateUtilService.getYesterdayDateString());
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
	
	public void runSriSch()
	{
		try {
			List<Long> districtIds = new ArrayList<Long>();
			districtIds.add(1L);
			districtIds.add(2L);
			districtIds.add(3L);
			districtIds.add(4L);
			districtIds.add(5L);
			schedulerService.runSchuduler(districtIds);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void runSriSch1()
	{
		try {
			List<Long> districtIds = new ArrayList<Long>();
			districtIds.add(6L);
			districtIds.add(7L);
			districtIds.add(8L);
			districtIds.add(9L);
			districtIds.add(10L);
			schedulerService.runSchuduler(districtIds);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void runSriSch2()
	{
		try {
			List<Long> districtIds = new ArrayList<Long>();
			districtIds.add(11L);
			districtIds.add(12L);
			districtIds.add(13L);
			districtIds.add(14L);
			districtIds.add(15L);
			schedulerService.runSchuduler(districtIds);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void runSriSch3()
	{
		try {
			List<Long> districtIds = new ArrayList<Long>();
			districtIds.add(16L);
			districtIds.add(17L);
			districtIds.add(18L);
			districtIds.add(19L);
			districtIds.add(20L);			
			schedulerService.runSchuduler(districtIds);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void runSriSch4()
	{
		try {
			List<Long> districtIds = new ArrayList<Long>();
			districtIds.add(21L);
			districtIds.add(22L);
			districtIds.add(23L);
			schedulerService.runSchuduler(districtIds);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void updateTdpCadreInfoDetails()
	{
		try {
			
			Long cadreInfoCount = schedulerService.updateTdpCadreInfoDetails();
			Long cadreCasteInfoCount = schedulerService.updateTdpCadreCasteInfoDetails();
			Long cadreAgeRangeInfoCount =  schedulerService.updateTdpCadreAgerangeInfoDetails();
			
			String message ="TdpCadreInfo : "+cadreInfoCount+"\n TdpCadreCasteInfo : "+cadreCasteInfoCount+"\nTdpCadreAgeRangeInfo : "+cadreAgeRangeInfoCount+"";
			log.debug(message);
			mobileService.sendSmsToUserForUpdations(message,"9581434970");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultStatus insertMahanaduEventInfo()
	{
		ResultStatus rs = new ResultStatus();
		if(!IConstants.DEPLOYED_HOST.equalsIgnoreCase("tdpserver")){
			return rs;
		}
			
		try{
			 DateUtilService dateUtilService = new DateUtilService();
			 Date currentDate = dateUtilService.getCurrentDateAndTime();
			 
			 rs = mahaNaduService.insertDataintoEventInfo1(currentDate,currentDate,null,null);
		}
		catch(Exception e)
		{
			log.info("\n\n Total insertMahanaduEventInfo Table effected records"); 
		}
		return rs;
	}
	public ResultStatus sendPdfReport()
	{
		ResultStatus rs = new ResultStatus();
		if(!IConstants.DEPLOYED_HOST.equalsIgnoreCase("tdpserver")){
			return rs;
		}
			
		try{
			   schedulerService.sendPdfReport();
		}
		catch(Exception e)
		{
			log.info("\n\n Total sendPdfReport "); 
		}
		return rs;
	}
	public void updateTrainingCampSpeakersDetails()
	  {
	    try{
	      //if(IConstants.DEPLOYED_HOST.equalsIgnoreCase("tdpserver"))
	        schedulerService.updateTrainingCampSpeakersDetails();
	    }
	    catch(Exception e)
	    {
	      log.info("\n\n Total insertMahanaduEventInfo Table effected records"); 
	    }
	  }
	
	public void runTheBatchJobForEveryDayChangeApptStatus()
	{
		schedulerService.changeApptStatusToAttended(dateUtilService.getCurrentDateAndTime(),IConstants.APPOINTMENT_STATUS_ATTENDED,IConstants.APPOINTMENT_STATUS_FIXED);
	}
}
