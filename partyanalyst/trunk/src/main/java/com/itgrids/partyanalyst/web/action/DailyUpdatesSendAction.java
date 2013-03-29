package com.itgrids.partyanalyst.web.action;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.service.IPartyCandidateSpecialPageScheduleService;

public class DailyUpdatesSendAction {
	
	IPartyCandidateSpecialPageScheduleService partyCandidateSpecialPageScheduleService;
	private static final Logger LOG = Logger.getLogger(DailyUpdatesSendAction.class);
	
   public IPartyCandidateSpecialPageScheduleService getPartyCandidateSpecialPageScheduleService() {
		return partyCandidateSpecialPageScheduleService;
	}


	public void setPartyCandidateSpecialPageScheduleService(
			IPartyCandidateSpecialPageScheduleService partyCandidateSpecialPageScheduleService) {
		this.partyCandidateSpecialPageScheduleService = partyCandidateSpecialPageScheduleService;
	}


	public void sendDailyUpdates(){
		LOG.info(" Enter into sendDailyUpdates ");
		try{
			LOG.info(" Schedule job running started ");
			partyCandidateSpecialPageScheduleService.sendUpdates();
			LOG.info(" Schedule job running completed successfully ");
		}catch(Exception e){
			LOG.error("Exception rised in sendDailyUpdates ", e);
		}
	 }
}
