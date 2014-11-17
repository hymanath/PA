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
			
			constituencyList.add(8L);
			constituencyList.add(4L);
			constituencyList.add(295L);
			constituencyList.add(296L);
			constituencyList.add(2L);
			constituencyList.add(5L);
			constituencyList.add(1L);
			constituencyList.add(3L);
			constituencyList.add(7L);
			constituencyList.add(6L);
			constituencyList.add(10L);
			constituencyList.add(13L);
			constituencyList.add(15L);
			constituencyList.add(12L);
			constituencyList.add(18L);
			constituencyList.add(16L);
			constituencyList.add(342L);
			constituencyList.add(343L);
			constituencyList.add(11L);
			constituencyList.add(321L);
			constituencyList.add(23L);
			constituencyList.add(322L);
			constituencyList.add(318L);
			constituencyList.add(26L);
			constituencyList.add(30L);
			constituencyList.add(24L);
			constituencyList.add(20L);
			constituencyList.add(323L);
			constituencyList.add(31L);
			constituencyList.add(319L);
			constituencyList.add(21L);
			constituencyList.add(320L);
			constituencyList.add(40L);
			constituencyList.add(35L);
			constituencyList.add(36L);
			constituencyList.add(32L);
			constituencyList.add(37L);
			constituencyList.add(41L);
			constituencyList.add(39L);
			constituencyList.add(337L);
			constituencyList.add(336L);
			constituencyList.add(34L);
			constituencyList.add(57L);
			constituencyList.add(367L);
			constituencyList.add(345L);
			constituencyList.add(346L);
			constituencyList.add(347L);
			constituencyList.add(56L);
			constituencyList.add(348L);
			constituencyList.add(349L);
			constituencyList.add(351L);
			constituencyList.add(350L);
			constituencyList.add(55L);
			constituencyList.add(58L);
			constituencyList.add(60L);
			constituencyList.add(59L);
			constituencyList.add(50L);
			constituencyList.add(49L);
			constituencyList.add(315L);
			constituencyList.add(47L);
			constituencyList.add(314L);
			constituencyList.add(51L);
			constituencyList.add(316L);
			constituencyList.add(46L);
			constituencyList.add(317L);
			constituencyList.add(44L);
			constituencyList.add(43L);
			constituencyList.add(54L);
			constituencyList.add(313L);
			constituencyList.add(52L);
			constituencyList.add(53L);
			constituencyList.add(66L);
			constituencyList.add(335L);
			constituencyList.add(68L);
			constituencyList.add(64L);
			constituencyList.add(369L);
			constituencyList.add(69L);
			constituencyList.add(73L);
			constituencyList.add(63L);
			constituencyList.add(62L);
			constituencyList.add(70L);
			constituencyList.add(61L);
			constituencyList.add(65L);
			constituencyList.add(71L);
			constituencyList.add(67L);
			constituencyList.add(77L);
			constituencyList.add(338L);
			constituencyList.add(79L);
			constituencyList.add(339L);
			constituencyList.add(78L);
			constituencyList.add(84L);
			constituencyList.add(82L);
			constituencyList.add(80L);
			constituencyList.add(75L);
			constituencyList.add(81L);
			constituencyList.add(85L);
			constituencyList.add(74L);
			constituencyList.add(89L);
			constituencyList.add(87L);
			constituencyList.add(362L);
			constituencyList.add(86L);
			constituencyList.add(91L);
			constituencyList.add(93L);
			constituencyList.add(94L);
			constituencyList.add(363L);
			constituencyList.add(364L);
			constituencyList.add(97L);
			constituencyList.add(365L);
			constituencyList.add(92L);
			constituencyList.add(324L);
			constituencyList.add(107L);
			constituencyList.add(101L);
			constituencyList.add(104L);
			constituencyList.add(103L);
			constituencyList.add(325L);
			constituencyList.add(105L);
			constituencyList.add(102L);
			constituencyList.add(326L);
			constituencyList.add(100L);
			constituencyList.add(111L);
			constituencyList.add(352L);
			constituencyList.add(117L);
			constituencyList.add(114L);
			constituencyList.add(116L);
			constituencyList.add(108L);
			constituencyList.add(109L);
			constituencyList.add(112L);
			constituencyList.add(353L);
			constituencyList.add(113L);
			constituencyList.add(360L);
			constituencyList.add(124L);
			constituencyList.add(125L);
			constituencyList.add(122L);
			constituencyList.add(120L);
			constituencyList.add(121L);
			constituencyList.add(361L);
			constituencyList.add(129L);
			constituencyList.add(127L);
			constituencyList.add(368L);
			constituencyList.add(354L);
			constituencyList.add(355L);
			constituencyList.add(356L);
			constituencyList.add(357L);
			constituencyList.add(358L);
			constituencyList.add(134L);
			constituencyList.add(136L);
			constituencyList.add(359L);
			constituencyList.add(138L);
			constituencyList.add(133L);
			constituencyList.add(141L);
			constituencyList.add(135L);
			constituencyList.add(140L);
			constituencyList.add(137L);
			constituencyList.add(163L);
			constituencyList.add(157L);
			constituencyList.add(156L);
			constituencyList.add(307L);
			constituencyList.add(155L);
			constituencyList.add(147L);
			constituencyList.add(308L);
			constituencyList.add(159L);
			constituencyList.add(153L);
			constituencyList.add(146L);
			constituencyList.add(160L);
			constituencyList.add(310L);
			constituencyList.add(152L);
			constituencyList.add(309L);
			constituencyList.add(303L);
			constituencyList.add(304L);
			constituencyList.add(305L);
			constituencyList.add(149L);
			constituencyList.add(306L);
			constituencyList.add(172L);
			constituencyList.add(366L);
			constituencyList.add(181L);
			constituencyList.add(174L);
			constituencyList.add(173L);
			constituencyList.add(167L);
			constituencyList.add(179L);
			constituencyList.add(178L);
			constituencyList.add(177L);
			constituencyList.add(180L);
			constituencyList.add(169L);
			constituencyList.add(170L);
			constituencyList.add(171L);
			constituencyList.add(176L);
			constituencyList.add(168L);
			constituencyList.add(194L);
			constituencyList.add(193L);
			constituencyList.add(184L);
			constituencyList.add(185L);
			constituencyList.add(187L);
			constituencyList.add(327L);
			constituencyList.add(328L);
			constituencyList.add(182L);
			constituencyList.add(329L);
			constituencyList.add(330L);
			constituencyList.add(196L);
			constituencyList.add(331L);
			constituencyList.add(195L);
			constituencyList.add(191L);
			constituencyList.add(192L);
			constituencyList.add(186L);
			constituencyList.add(210L);
			constituencyList.add(215L);
			constituencyList.add(206L);
			constituencyList.add(211L);
			constituencyList.add(217L);
			constituencyList.add(213L);
			constituencyList.add(216L);
			constituencyList.add(209L);
			constituencyList.add(212L);
			constituencyList.add(312L);
			constituencyList.add(311L);
			constituencyList.add(199L);
			constituencyList.add(208L);
			constituencyList.add(214L);
			constituencyList.add(207L);
			constituencyList.add(203L);
			constituencyList.add(205L);
			constituencyList.add(344L);
			constituencyList.add(221L);
			constituencyList.add(228L);
			constituencyList.add(218L);
			constituencyList.add(219L);
			constituencyList.add(229L);
			constituencyList.add(227L);
			constituencyList.add(223L);
			constituencyList.add(225L);
			constituencyList.add(226L);
			constituencyList.add(222L);
			constituencyList.add(224L);
			constituencyList.add(232L);
			constituencyList.add(241L);
			constituencyList.add(233L);
			constituencyList.add(340L);
			constituencyList.add(341L);
			constituencyList.add(236L);
			constituencyList.add(231L);
			constituencyList.add(237L);
			constituencyList.add(239L);
			constituencyList.add(238L);
			constituencyList.add(242L);
			constituencyList.add(252L);
			constituencyList.add(243L);
			constituencyList.add(246L);
			constituencyList.add(248L);
			constituencyList.add(251L);
			constituencyList.add(245L);
			constituencyList.add(244L);
			constituencyList.add(250L);
			constituencyList.add(249L);
			constituencyList.add(254L);
			constituencyList.add(332L);
			constituencyList.add(261L);
			constituencyList.add(260L);
			constituencyList.add(263L);
			constituencyList.add(262L);
			constituencyList.add(333L);
			constituencyList.add(257L);
			constituencyList.add(264L);
			constituencyList.add(258L);
			constituencyList.add(265L);
			constituencyList.add(334L);
			constituencyList.add(253L);
			constituencyList.add(255L);
			constituencyList.add(276L);
			constituencyList.add(279L);
			constituencyList.add(297L);
			constituencyList.add(278L);
			constituencyList.add(277L);
			constituencyList.add(298L);
			constituencyList.add(272L);
			constituencyList.add(299L);
			constituencyList.add(273L);
			constituencyList.add(270L);
			constituencyList.add(275L);
			constituencyList.add(300L);
			constituencyList.add(267L);
			constituencyList.add(271L);
			constituencyList.add(290L);
			constituencyList.add(285L);
			constituencyList.add(294L);
			constituencyList.add(286L);
			constituencyList.add(280L);
			constituencyList.add(291L);
			constituencyList.add(289L);
			constituencyList.add(288L);
			constituencyList.add(283L);
			constituencyList.add(301L);
			constituencyList.add(281L);
			constituencyList.add(302L);
			constituencyList.add(284L);
			constituencyList.add(282L);

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
		fillDataForCardPrinting(prevDate,"MAX");
		fillDataForCardPrinting(prevDate,"ZEBRA");
	}
	public void fillDataForCardPrinting(String prevDate , String type)
	{
		try
		{
			String tableName = "";
			Long limit = 50000l;
			if(type.equalsIgnoreCase("MAX"))
			{
				tableName = "max_print_details1";
			}
			else if(type.equalsIgnoreCase("ZEBRA"))
			{
				tableName = "zebra_print_details1";
			}
			
			
			
			
			List<Long> constiIds = getConstituencyes();
			
			int totalCount = 0;
			for (Long id : constiIds) 
			{
				if(totalCount >= 50000)
				{
					return ;
				}
				else
				{
					
					//PREPARE DATA FOR RURAL BY USING RURAL CONSTITUECYES
					Integer r1Count =   saveRuralConstituencysDataType1(prevDate,tableName,id,limit);
					totalCount = r1Count;
					if(totalCount >= 50000)
					{
						return ;
					}
					else
					{
						limit = limit - r1Count;
						//PREPARE DATA FOR RURAL BY USING RURAL-URBAN CONSTITUECYES
						Integer r2Count =   saveRuralConstituencysDataType2(prevDate,tableName,id,limit);
						totalCount = totalCount + r2Count;
						if(totalCount >= 50000)
						{
							return ;
						}
						else
						{
							limit = limit - r2Count;
							
							//PREPARE DATA FOR RURAL-URBAN BY USING RURAL-URBAN CONSTITUECYES
							Integer ruCount =   saveRuralUrbanConstituencysDataType(prevDate,tableName,id,limit);
							totalCount = totalCount+ ruCount;
							if(totalCount >= 50000)
							{
								return ;
							}
							else
							{
								limit = limit - ruCount;
								//PREPARE DATA FOR URBAN BY USING URBAN CONSTITUECYES
								Integer uCount =   saveUrbanConstituencysDataType(prevDate,tableName,id,limit);
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
	public Integer saveRuralConstituencysDataType1(String prevDate,String tableName,Long constId,Long limit)
	{
		Integer count = 0;
		try
		{
			count = tdpCadreDAO.saveRuralConstituencyDataType1(prevDate,tableName,constId,limit);
			LOG.error(" R1 TYPE RECORDS INSERTED FOR CARD PRINTING   "+ constId +":" +tableName+":" +count );
		}
		catch (Exception e)
		{
			LOG.error("Exception Raised in saveRuralConstituencysDataType1()",e); 
		}
		return count;
		
	}
	
	public Integer saveRuralConstituencysDataType2(String prevDate,String tableName,Long constId,Long limit)
	{
		Integer count = 0;
		try 
		{
			count = tdpCadreDAO.saveRuralUrbanConstituencyDataType2(prevDate,tableName,constId,limit);
			LOG.error(" R2 TYPE RECORDS INSERTED FOR CARD PRINTING   "+ constId +":" +tableName+":" +count );
		} 
		catch (Exception e) 
		{
			LOG.error("Exception Raised in saveRuralConstituencysDataType2()",e); 
		}
		return count;
		
	}
	
	public Integer saveRuralUrbanConstituencysDataType(String prevDate,String tableName,Long constId,Long limit)
	{
		Integer count = 0;
		try
		{
			count = tdpCadreDAO.saveRuralUrbanConstituencyDataType(prevDate,tableName,constId,limit);
			LOG.error(" RU TYPE RECORDS INSERTED FOR CARD PRINTING   "+ constId +":" +tableName+":" +count);
		} 
		catch (Exception e) 
		{
			LOG.error("Exception Raised in saveRuralUrbanConstituencysDataType()",e); 
		}
		return count;
		
	}
	
	public Integer saveUrbanConstituencysDataType(String prevDate,String tableName,Long constId,Long limit)
	{
		Integer count = 0;
		try 
		{
		    count = tdpCadreDAO.saveUrbanConstituencyDataType1(prevDate,tableName,constId,limit);
			LOG.error(" U TYPE RECORDS INSERTED FOR CARD PRINTING   "+ constId +":" +tableName+":" +count);
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised in saveUrbanConstituencysDataType()",e); 
		}
		return count;
		
	}
	
	
}
