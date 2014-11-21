package com.itgrids.partyanalyst.notification.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.ISearchEngineIPAddressDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.IUserTrackingDAO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.keys.PropertyKeys;
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
	/*public void prepareDatForCardPrinting(String prevDate)
	{
		try 
		{
			//PREPARE DATA FOR RURAL BY USING RURAL CONSTITUECYES
			saveRuralConstituencysDataType1(prevDate,"card_prining_details1",null,null);
			//PREPARE DATA FOR RURAL BY USING RURAL-URBAN CONSTITUECYES
			saveRuralConstituencysDataType2(prevDate,"card_prining_details1",null,null);
			//PREPARE DATA FOR RURAL-URBAN BY USING RURAL-URBAN CONSTITUECYES
			saveRuralUrbanConstituencysDataType(prevDate,"card_prining_details1",null,null);
			//PREPARE DATA FOR URBAN BY USING URBAN CONSTITUECYES
			saveUrbanConstituencysDataType(prevDate,"card_prining_details1",null,null);
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised in prepareDatForCardPrinting()",e); 
		}
	}*/
	
	
	/**
	 * This Service is used for saving Card Print Details For Zebra  from  Cadre registration
	 * @param prevDate
	 * @author Prasad Thiragabathina
	 */
/*	public void prepareDatForCardPrintingForZebra(String prevDate)
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
			saveRuralConstituencysDataType1(prevDate,"zebra_print_details",distIds,null);
			//PREPARE DATA FOR RURAL BY USING RURAL-URBAN CONSTITUECYES
			saveRuralConstituencysDataType2(prevDate,"zebra_print_details",distIds,null);
			//PREPARE DATA FOR RURAL-URBAN BY USING RURAL-URBAN CONSTITUECYES
			saveRuralUrbanConstituencysDataType(prevDate,"zebra_print_details",distIds,null);
			//PREPARE DATA FOR URBAN BY USING URBAN CONSTITUECYES
			saveUrbanConstituencysDataType(prevDate,"zebra_print_details",distIds,null);
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised in prepareDatForCardPrintingForZebra()",e); 
		}
	}*/
	
	
	/**
	 * This Service is used for saving Card Print Details For Max  from  Cadre registration
	 * @param prevDate
	 * @author Prasad Thiragabathina
	 */
	/*public void prepareDatForCardPrintingForMax(String prevDate)
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
			saveRuralConstituencysDataType1(prevDate,"max_print_details",distIds,null);
			//PREPARE DATA FOR RURAL BY USING RURAL-URBAN CONSTITUECYES
			saveRuralConstituencysDataType2(prevDate,"max_print_details",distIds,null);
			//PREPARE DATA FOR RURAL-URBAN BY USING RURAL-URBAN CONSTITUECYES
			saveRuralUrbanConstituencysDataType(prevDate,"max_print_details",distIds,null);
			//PREPARE DATA FOR URBAN BY USING URBAN CONSTITUECYES
			saveUrbanConstituencysDataType(prevDate,"max_print_details",distIds,null);
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised in prepareDatForCardPrintingForMax()",e); 
		}
	}*/
	
	
	
	public List<Long> getConstituencyes()
	{
		List<Long> constituencyList = new ArrayList<Long>();
		try
		{
			
			
			
			

			 /*Properties props = null;

				props = new Properties();
				try{
					props.load(ClassLoader.getSystemResourceAsStream(PropertyKeys.CONSTITUENCYES_LIST));
				}catch(IOException ioe){
					
				}

			Enumeration enuKeys = props.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = props.getProperty(key);
				System.out.println(key + ": " + value);
			
				constituencyList.add(Long.valueOf((String) enuKeys.nextElement()));
			}*/
		} 
		catch (Exception e) 
		{
			LOG.error("Exception Raised in getConstituencyes()",e); 
		}
		
		return constituencyList;
	}
	
	
	public void schedularForCardPrintDataFilling(String prevDate)
	{
		//fillDataForCardPrinting(prevDate,"MAX");
		fillDataForCardPrinting(prevDate,"ZEBRA");
	}
	public void fillDataForCardPrinting(String prevDate , String type)
	{
		try
		{
			String tableName = "";
			Long limit = 50000l;
			Long insertType = 0l ;
			if(type.equalsIgnoreCase("MAX"))
			{
				tableName = "max_print_details";
				insertType = 1l;
			}
			else if(type.equalsIgnoreCase("ZEBRA"))
			{
				tableName = "zebra_print_details";
				insertType = 2l;
			}
			
			
			
			/*List<Long> firstList = new ArrayList<Long>();
			firstList.add(8L);
			firstList.add(4L);
			firstList.add(295L);
			firstList.add(296L);
			firstList.add(2L);
			firstList.add(5L);
			firstList.add(1L);
			firstList.add(3L);
			firstList.add(7L);
			firstList.add(6L);
			firstList.add(10L);
			firstList.add(13L);
			firstList.add(15L);
			firstList.add(12L);
			firstList.add(18L);
			firstList.add(16L);
			firstList.add(342L);
			firstList.add(343L);
			firstList.add(11L);
			firstList.add(321L);
			firstList.add(23L);
			firstList.add(322L);
			firstList.add(318L);
			firstList.add(26L);
			firstList.add(30L);
			firstList.add(24L);
			firstList.add(20L);
			firstList.add(323L);
			firstList.add(31L);
			firstList.add(319L);
			firstList.add(21L);
			firstList.add(320L);
			firstList.add(40L);
			firstList.add(35L);
			
			List<Long> secondList = new ArrayList<Long>();
			secondList.add(36L);
			secondList.add(32L);
			secondList.add(37L);
			secondList.add(41L);
			secondList.add(39L);
			secondList.add(337L);
			secondList.add(336L);
			secondList.add(34L);
			secondList.add(57L);
			secondList.add(367L);
			secondList.add(345L);
			secondList.add(346L);
			secondList.add(347L);
			secondList.add(56L);
			secondList.add(348L);
			secondList.add(349L);
			secondList.add(351L);
			secondList.add(350L);
			secondList.add(55L);
			secondList.add(58L);
			secondList.add(60L);
			secondList.add(59L);
			secondList.add(50L);
			secondList.add(49L);
			secondList.add(315L);
			secondList.add(47L);
			secondList.add(314L);
			secondList.add(51L);
			secondList.add(316L);
			secondList.add(46L);
			secondList.add(317L);
			secondList.add(44L);
			secondList.add(43L);
			secondList.add(54L);
			secondList.add(313L);
			secondList.add(52L);
			secondList.add(53L);
			secondList.add(66L);
			secondList.add(335L);
			secondList.add(68L);
			secondList.add(64L);
			secondList.add(369L);
			secondList.add(69L);
			secondList.add(73L);
			secondList.add(63L);
			secondList.add(62L);
			secondList.add(70L);
			secondList.add(61L);
			secondList.add(65L);
			secondList.add(71L);
			secondList.add(67L);
			secondList.add(77L);
			secondList.add(338L);
			secondList.add(79L);
			secondList.add(339L);
			secondList.add(78L);
			secondList.add(84L);
			secondList.add(82L);
			secondList.add(80L);
			secondList.add(75L);
			secondList.add(81L);
			secondList.add(85L);
			secondList.add(74L);
			secondList.add(89L);*/
			
			List<Long> thirdList = new ArrayList<Long>();
			
			thirdList.add(100l);
			thirdList.add(101l);
			thirdList.add(102l);
			thirdList.add(103l);
			thirdList.add(104l);
			thirdList.add(105l);
			thirdList.add(107l);
			thirdList.add(324l);
			thirdList.add(325l);
			thirdList.add(326l);








			
			Map<Long,List<Long>> detailsMap = new TreeMap<Long, List<Long>>();
			
			//detailsMap.put(1l, firstList);
			//detailsMap.put(2l, secondList);
			detailsMap.put(3l, thirdList);
			//List<Long> constiIds = getConstituencyes();
			
			int count = 0;
			int totalCount = 0;
			for (Long id : detailsMap.keySet()) 
			{
				count = count + 1;
				if(count == 20)
				{
					count = 0;
					tdpCadreDAO.flushAndclearSession();
				}
				List<Long> ids = detailsMap.get(id);
				//List<Long> ids = new ArrayList<Long>();
				//ids.add(id);
				if(totalCount >= 50000)
				{
					break ;
				}
				else
				{
					
					//PREPARE DATA FOR RURAL BY USING RURAL CONSTITUECYES
					Integer r1Count =   saveRuralConstituencysDataType1(prevDate,tableName,ids,limit);
					totalCount = totalCount + r1Count;
					if(totalCount >= 50000)
					{
						break;
					}
					else
					{
						limit = limit - r1Count;
						//PREPARE DATA FOR RURAL BY USING RURAL-URBAN CONSTITUECYES
						Integer r2Count =   saveRuralConstituencysDataType2(prevDate,tableName,ids,limit);
						totalCount = totalCount + r2Count;
						if(totalCount >= 50000)
						{
							break;
						}
						else
						{
							limit = limit - r2Count;
							
							//PREPARE DATA FOR RURAL-URBAN BY USING RURAL-URBAN CONSTITUECYES
							Integer ruCount =   saveRuralUrbanConstituencysDataType(prevDate,tableName,ids,limit);
							totalCount = totalCount+ ruCount;
							if(totalCount >= 50000)
							{
								break;
							}
							else
							{
								limit = limit - ruCount;
								//PREPARE DATA FOR URBAN BY USING URBAN CONSTITUECYES
								Integer uCount =   saveUrbanConstituencysDataType(prevDate,tableName,ids,limit);
								totalCount = totalCount + uCount;
								limit = limit - uCount;
							}
							
						}
						
					}
				}
			}
			
			Integer updatedCount = tdpCadreDAO.insertPrintCompanyType(insertType,tableName);
			
			LOG.error("UPDATED COUNT :" +updatedCount ); 
			
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised in fillDataForCardPrinting()",e); 
		}
	}
	/*public void fillDataForCardPrinting(String prevDate , String type)
	{
		try
		{
			String tableName = "";
			Long limit = 50000l;
			if(type.equalsIgnoreCase("MAX"))
			{
				tableName = "max_print_details";
			}
			else if(type.equalsIgnoreCase("ZEBRA"))
			{
				tableName = "zebra_print_details";
			}
			
			List<Long> firstList = new ArrayList<Long>();
			firstList.add(5l);
			
			List<Long> secondList = new ArrayList<Long>();
			secondList.add(1l);
			secondList.add(2l);
			secondList.add(3l);
			secondList.add(4l);
			secondList.add(6l);
			secondList.add(7l);
			secondList.add(8l);
			secondList.add(9l);
			secondList.add(10l);
			
			List<Long> thirdList = new ArrayList<Long>();
			thirdList.add(11l);
			thirdList.add(12l);
			thirdList.add(13l);
			thirdList.add(14l);
			thirdList.add(15l);
			thirdList.add(16l);
			thirdList.add(17l);
			thirdList.add(18l);
			thirdList.add(19l);
			thirdList.add(20l);
			thirdList.add(21l);
			thirdList.add(22l);
			thirdList.add(23l);
			
			
			Map<Long,List<Long>> detailsMap = new TreeMap<Long, List<Long>>();
			
			detailsMap.put(1l, firstList);
			detailsMap.put(2l, secondList);
			detailsMap.put(3l, thirdList);
			
			int totalCount = 0;
			for (Long id : detailsMap.keySet()) 
			{
				if(totalCount >= 50000)
				{
					return ;
				}
				else
				{
					List<Long> distIds = detailsMap.get(id);
					//PREPARE DATA FOR RURAL BY USING RURAL CONSTITUECYES
					Integer r1Count =   saveRuralConstituencysDataType1(prevDate,tableName,distIds,limit);
					totalCount = r1Count;
					if(totalCount >= 50000)
					{
						return ;
					}
					else
					{
						limit = limit - r1Count;
						//PREPARE DATA FOR RURAL BY USING RURAL-URBAN CONSTITUECYES
						Integer r2Count =   saveRuralConstituencysDataType2(prevDate,tableName,distIds,limit);
						totalCount = totalCount + r2Count;
						if(totalCount >= 50000)
						{
							return ;
						}
						else
						{
							limit = limit - r2Count;
							
							//PREPARE DATA FOR RURAL-URBAN BY USING RURAL-URBAN CONSTITUECYES
							Integer ruCount =   saveRuralUrbanConstituencysDataType(prevDate,tableName,distIds,limit);
							totalCount = totalCount+ ruCount;
							if(totalCount >= 50000)
							{
								return ;
							}
							else
							{
								limit = limit - ruCount;
								//PREPARE DATA FOR URBAN BY USING URBAN CONSTITUECYES
								Integer uCount =   saveUrbanConstituencysDataType(prevDate,tableName,distIds,limit);
								totalCount = totalCount + uCount;
								limit = limit - uCount;
							}
							
						}
						
					}
				}
			}
			
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised in fillDataForCardPrinting()",e); 
		}
	}*/
	public Integer saveRuralConstituencysDataType1(String prevDate,String tableName,List<Long> constIds,Long limit)
	{
		Integer count = 0;
		try
		{
			count = tdpCadreDAO.saveRuralConstituencyDataType1(prevDate,tableName,constIds,limit);
			LOG.error(" R1 TYPE RECORDS INSERTED FOR CARD PRINTING   "+ constIds +":" +tableName+":" +count );
		}
		catch (Exception e)
		{
			LOG.error("Exception Raised in saveRuralConstituencysDataType1()",e); 
		}
		return count;
		
	}
	
	public Integer saveRuralConstituencysDataType2(String prevDate,String tableName,List<Long> constIds,Long limit)
	{
		Integer count = 0;
		try 
		{
			count = tdpCadreDAO.saveRuralUrbanConstituencyDataType2(prevDate,tableName,constIds,limit);
			LOG.error(" R2 TYPE RECORDS INSERTED FOR CARD PRINTING   "+ constIds +":" +tableName+":" +count );
		} 
		catch (Exception e) 
		{
			LOG.error("Exception Raised in saveRuralConstituencysDataType2()",e); 
		}
		return count;
		
	}
	
	public Integer saveRuralUrbanConstituencysDataType(String prevDate,String tableName,List<Long> constIds,Long limit)
	{
		Integer count = 0;
		try
		{
			count = tdpCadreDAO.saveRuralUrbanConstituencyDataType(prevDate,tableName,constIds,limit);
			LOG.error(" RU TYPE RECORDS INSERTED FOR CARD PRINTING   "+ constIds +":" +tableName+":" +count);
		} 
		catch (Exception e) 
		{
			LOG.error("Exception Raised in saveRuralUrbanConstituencysDataType()",e); 
		}
		return count;
		
	}
	
	public Integer saveUrbanConstituencysDataType(String prevDate,String tableName,List<Long> constIds,Long limit)
	{
		Integer count = 0;
		try 
		{
		    count = tdpCadreDAO.saveUrbanConstituencyDataType1(prevDate,tableName,constIds,limit);
			LOG.error(" U TYPE RECORDS INSERTED FOR CARD PRINTING   "+ constIds +":" +tableName+":" +count);
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised in saveUrbanConstituencysDataType()",e); 
		}
		return count;
		
	}
	
	
}
