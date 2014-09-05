package com.itgrids.partyanalyst.notification.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.ISearchEngineIPAddressDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.IUserTrackingDAO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.notification.service.ISchedulerService;
import com.itgrids.partyanalyst.service.IMailService;

public class SchedulerService implements ISchedulerService{
	
	private IMailService mailService;
	private final static Logger LOG = Logger.getLogger(SchedulerService.class);
	
	private ISearchEngineIPAddressDAO searchEngineIPAddressDAO;
	private IUserTrackingDAO userTrackingDAO;
	
	@Autowired
	private ISurveyDetailsInfoDAO surveyDetailsInfoDAO;
	
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

	public ResultStatus deleteSearchEngineAccessedURLsFromUserTracking(Date fromDate,Date toDate)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			List<String> ipAddressList = getAllSearchEngineIPAddresses();
			
			if(ipAddressList != null && ipAddressList.size() > 0)
			{
				int deletedRecords = userTrackingDAO.deleteSearchEngineAccessedURLsFromUserTracking(ipAddressList,fromDate,toDate);
				LOG.info(deletedRecords+" No of Records Deleted from UserTracking");
			}
			return resultStatus;
		}catch (Exception e) {
			LOG.error("Exception Occured in deleteSearchEngineAccessedURLsFromUserTracking() Method, Exception is - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
	
	public List<String> getAllSearchEngineIPAddresses()
	{
		LOG.debug("Entered into getAllSearchEngineIPAddresses() Method");
		try{
			return searchEngineIPAddressDAO.getAllSearchEngineIPAddresses();
		}catch (Exception e) {
			LOG.error("Exception Occured in getAllSearchEngineIPAddresses() Method, Exception is - "+e);
			return null;
		}
	}
	
	/**
	 * This Service is used for saving mobile numbers from SMS and CTP Project Daily
	 * @param fromDate
	 * @author Prasad Thiragabathina
	 */
	public void saveDailyWmCorrectedMobileNUmbers(Date fromDate)
	{
		try {
			Calendar cal = Calendar.getInstance();
	    	cal.getTime();
	    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			LOG.info( sdf.format(cal.getTime()));
			int ctpCount = surveyDetailsInfoDAO.saveDailyCallCenterVerifiedDetails(fromDate);
			int smsCount =  surveyDetailsInfoDAO.saveSMSMobileNumbers(fromDate);
			LOG.info(ctpCount+" No of Records Are Inserted into mobile numbers form CTP");
			LOG.info(smsCount+" No of Records Are Inserted into mobile numbers form SMS");
			
		} catch (Exception e) {
			LOG.error("Exception Occured in saveDailyWmCorrectedMobileNUmbers() Method, Exception is - ",e);
		}
		
		
	}
	
	
}
