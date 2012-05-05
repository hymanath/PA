package com.itgrids.partyanalyst.notification.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ISearchEngineIPAddressDAO;
import com.itgrids.partyanalyst.dao.IUserTrackingDAO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.notification.service.ISchedulerService;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class SchedulerService implements ISchedulerService{
	
	private IMailService mailService;
	private final static Logger log = Logger.getLogger(SchedulerService.class);
	
	private ISearchEngineIPAddressDAO searchEngineIPAddressDAO;
	private IUserTrackingDAO userTrackingDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	
	public IUserTrackingDAO getUserTrackingDAO() {
		return userTrackingDAO;
	}

	public void setUserTrackingDAO(IUserTrackingDAO userTrackingDAO) {
		this.userTrackingDAO = userTrackingDAO;
	}

	public ISearchEngineIPAddressDAO getSearchEngineIPAddressDAO() {
		return searchEngineIPAddressDAO;
	}

	public void setSearchEngineIPAddressDAO(
			ISearchEngineIPAddressDAO searchEngineIPAddressDAO) {
		this.searchEngineIPAddressDAO = searchEngineIPAddressDAO;
	}
	
	public IMailService getMailService() {
		return mailService;
	}

	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}

	public ResultStatus deleteSearchEngineAccessedURLsFromUserTracking()
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			List<String> ipAddressList = getAllSearchEngineIPAddresses();
			
			if(ipAddressList != null && ipAddressList.size() > 0)
			{
				int deletedRecords = userTrackingDAO.deleteSearchEngineAccessedURLsFromUserTracking(ipAddressList,dateUtilService.getCurrentDateAndTime(),dateUtilService.getCurrentDateAndTime());
				log.info(deletedRecords+" No of Records Deleted from UserTracking");
			}
			return resultStatus;
		}catch (Exception e) {
			log.error("Exception Occured in deleteSearchEngineAccessedURLsFromUserTracking() Method, Exception is - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	
	public List<String> getAllSearchEngineIPAddresses()
	{
		log.debug("Entered into getAllSearchEngineIPAddresses() Method");
		try{
			return searchEngineIPAddressDAO.getAllSearchEngineIPAddresses();
		}catch (Exception e) {
			log.error("Exception Occured in getAllSearchEngineIPAddresses() Method, Exception is - "+e);
			return null;
		}
	}
}
