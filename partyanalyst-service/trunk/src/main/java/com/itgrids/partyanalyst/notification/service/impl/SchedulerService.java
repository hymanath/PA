package com.itgrids.partyanalyst.notification.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.ISearchEngineIPAddressDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
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
	
	@Autowired
	private ITdpCadreDAO tdpCadreDAO;
	
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
	
	/**
	 * This Service is used for saving Card Print Details  from  Cadre registration
	 * @param prevDate
	 * @author Prasad Thiragabathina
	 */
	public void prepareDatForCardPrinting(String prevDate)
	{
		try 
		{
			//PREPARE DATA FOR RURAL BY USING RURAL CONSTITUECYES
			saveRuralConstituencysDataType1(prevDate,"card_prining_details1",null);
			//PREPARE DATA FOR RURAL BY USING RURAL-URBAN CONSTITUECYES
			saveRuralConstituencysDataType2(prevDate,"card_prining_details1",null);
			//PREPARE DATA FOR RURAL-URBAN BY USING RURAL-URBAN CONSTITUECYES
			saveRuralUrbanConstituencysDataType(prevDate,"card_prining_details1",null);
			//PREPARE DATA FOR URBAN BY USING URBAN CONSTITUECYES
			saveUrbanConstituencysDataType(prevDate,"card_prining_details1",null);
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised in prepareDatForCardPrinting()",e); 
		}
	}
	
	
	/**
	 * This Service is used for saving Card Print Details For Zebra  from  Cadre registration
	 * @param prevDate
	 * @author Prasad Thiragabathina
	 */
	public void prepareDatForCardPrintingForZebra(String prevDate)
	{
		try 
		{
			List<Long> distIds = new ArrayList<Long>();
			
			distIds.add(11l);
			distIds.add(12l);
			distIds.add(13l);
			distIds.add(14l);
			distIds.add(15l);
			distIds.add(16l);
			distIds.add(17l);
			distIds.add(18l);
			distIds.add(19l);
			distIds.add(20l);
			distIds.add(21l);
			distIds.add(22l);
			distIds.add(23l);
			//PREPARE DATA FOR RURAL BY USING RURAL CONSTITUECYES
			saveRuralConstituencysDataType1(prevDate,"zebra_print_details",distIds);
			//PREPARE DATA FOR RURAL BY USING RURAL-URBAN CONSTITUECYES
			saveRuralConstituencysDataType2(prevDate,"zebra_print_details",distIds);
			//PREPARE DATA FOR RURAL-URBAN BY USING RURAL-URBAN CONSTITUECYES
			saveRuralUrbanConstituencysDataType(prevDate,"zebra_print_details",distIds);
			//PREPARE DATA FOR URBAN BY USING URBAN CONSTITUECYES
			saveUrbanConstituencysDataType(prevDate,"zebra_print_details",distIds);
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised in prepareDatForCardPrintingForZebra()",e); 
		}
	}
	
	
	/**
	 * This Service is used for saving Card Print Details For Max  from  Cadre registration
	 * @param prevDate
	 * @author Prasad Thiragabathina
	 */
	public void prepareDatForCardPrintingForMax(String prevDate)
	{
		try 
		{
			List<Long> distIds = new ArrayList<Long>();
			distIds.add(1l);
			distIds.add(2l);
			distIds.add(3l);
			distIds.add(4l);
			distIds.add(5l);
			distIds.add(6l);
			distIds.add(7l);
			distIds.add(8l);
			distIds.add(9l);
			distIds.add(10l);
			//PREPARE DATA FOR RURAL BY USING RURAL CONSTITUECYES
			saveRuralConstituencysDataType1(prevDate,"max_print_details",distIds);
			//PREPARE DATA FOR RURAL BY USING RURAL-URBAN CONSTITUECYES
			saveRuralConstituencysDataType2(prevDate,"max_print_details",distIds);
			//PREPARE DATA FOR RURAL-URBAN BY USING RURAL-URBAN CONSTITUECYES
			saveRuralUrbanConstituencysDataType(prevDate,"max_print_details",distIds);
			//PREPARE DATA FOR URBAN BY USING URBAN CONSTITUECYES
			saveUrbanConstituencysDataType(prevDate,"max_print_details",distIds);
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised in prepareDatForCardPrintingForMax()",e); 
		}
	}
	
	public void saveRuralConstituencysDataType1(String prevDate,String tableName,List<Long> distIds)
	{
		try
		{
			Integer count = tdpCadreDAO.saveRuralConstituencyDataType1(prevDate,tableName,distIds);
			LOG.error(" R1 TYPE RECORDS INSERTED FOR CARD PRINTING   "+ prevDate +":" +tableName+":" +count );
		}
		catch (Exception e)
		{
			LOG.error("Exception Raised in saveRuralConstituencysDataType1()",e); 
		}
		
	}
	
	public void saveRuralConstituencysDataType2(String prevDate,String tableName,List<Long> distIds)
	{
		try 
		{
			Integer count = tdpCadreDAO.saveRuralUrbanConstituencyDataType2(prevDate,tableName,distIds);
			LOG.error(" R2 TYPE RECORDS INSERTED FOR CARD PRINTING   "+ prevDate +":" +tableName+":" +count );
		} 
		catch (Exception e) 
		{
			LOG.error("Exception Raised in saveRuralConstituencysDataType2()",e); 
		}
		
	}
	
	public void saveRuralUrbanConstituencysDataType(String prevDate,String tableName,List<Long> distIds)
	{
		try
		{
			Integer count = tdpCadreDAO.saveRuralUrbanConstituencyDataType(prevDate,tableName,distIds);
			LOG.error(" RU TYPE RECORDS INSERTED FOR CARD PRINTING   "+ prevDate +":" +tableName+":" +count);
		} 
		catch (Exception e) 
		{
			LOG.error("Exception Raised in saveRuralUrbanConstituencysDataType()",e); 
		}
		
	}
	
	public void saveUrbanConstituencysDataType(String prevDate,String tableName,List<Long> distIds)
	{
		try 
		{
			Integer count = tdpCadreDAO.saveUrbanConstituencyDataType1(prevDate,tableName,distIds);
			LOG.error(" U TYPE RECORDS INSERTED FOR CARD PRINTING   "+ prevDate +":" +tableName+":" +count);
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised in saveUrbanConstituencysDataType()",e); 
		}
		
	}
	
	
}
