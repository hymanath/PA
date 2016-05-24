package com.itgrids.partyanalyst.notification.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAppointmentDAO;
import com.itgrids.partyanalyst.dao.IEventDAO;
import com.itgrids.partyanalyst.dao.ISearchEngineIPAddressDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreAgerangeInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCasteInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreInfoDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampBatchAttendeeDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampBatchDAO;
import com.itgrids.partyanalyst.dao.IUserTrackingDAO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.TrainingCampBatchAttendee;
import com.itgrids.partyanalyst.notification.service.ISchedulerService;
import com.itgrids.partyanalyst.service.ICadreSurveyTransactionService;
import com.itgrids.partyanalyst.service.IMahanaduDashBoardService1;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IMobileService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class SchedulerService implements ISchedulerService{
	
	private IMailService mailService;
	private final static Logger LOG = Logger.getLogger(SchedulerService.class);
	
	private ISearchEngineIPAddressDAO searchEngineIPAddressDAO;
	private IUserTrackingDAO userTrackingDAO;
	
	@Autowired
	private ISurveyDetailsInfoDAO surveyDetailsInfoDAO;
	
	@Autowired
	private ITdpCadreDAO tdpCadreDAO;
	private ITdpCadreInfoDAO tdpCadreInfoDAO;
	private ITdpCadreCasteInfoDAO tdpCadreCasteInfoDAO;
	private ITdpCadreAgerangeInfoDAO tdpCadreAgerangeInfoDAO;
	
	private ICadreSurveyTransactionService cadreSurveyTransactionService;
	private TransactionTemplate transactionTemplate;
	private IMobileService mobileService;
	private ITrainingCampAttendanceDAO trainingCampAttendanceDAO;
	private ITrainingCampBatchAttendeeDAO trainingCampBatchAttendeeDAO;
	private ITrainingCampBatchDAO trainingCampBatchDAO;
	
	private IAppointmentDAO appointmentDAO;
	private IEventDAO eventDAO;
	private IMahanaduDashBoardService1 mahanaduDashBoardService1;
	
	public ITrainingCampBatchDAO getTrainingCampBatchDAO() {
		return trainingCampBatchDAO;
	}

	public void setTrainingCampBatchDAO(ITrainingCampBatchDAO trainingCampBatchDAO) {
		this.trainingCampBatchDAO = trainingCampBatchDAO;
	}

	public ITrainingCampBatchAttendeeDAO getTrainingCampBatchAttendeeDAO() {
		return trainingCampBatchAttendeeDAO;
	}

	public void setTrainingCampBatchAttendeeDAO(
			ITrainingCampBatchAttendeeDAO trainingCampBatchAttendeeDAO) {
		this.trainingCampBatchAttendeeDAO = trainingCampBatchAttendeeDAO;
	}

	public ITrainingCampAttendanceDAO getTrainingCampAttendanceDAO() {
		return trainingCampAttendanceDAO;
	}

	public void setTrainingCampAttendanceDAO(
			ITrainingCampAttendanceDAO trainingCampAttendanceDAO) {
		this.trainingCampAttendanceDAO = trainingCampAttendanceDAO;
	}

	public IMobileService getMobileService() {
		return mobileService;
	}

	public void setMobileService(IMobileService mobileService) {
		this.mobileService = mobileService;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public ITdpCadreInfoDAO getTdpCadreInfoDAO() {
		return tdpCadreInfoDAO;
	}

	public ITdpCadreCasteInfoDAO getTdpCadreCasteInfoDAO() {
		return tdpCadreCasteInfoDAO;
	}

	public ITdpCadreAgerangeInfoDAO getTdpCadreAgerangeInfoDAO() {
		return tdpCadreAgerangeInfoDAO;
	}

	public void setTdpCadreInfoDAO(ITdpCadreInfoDAO tdpCadreInfoDAO) {
		this.tdpCadreInfoDAO = tdpCadreInfoDAO;
	}

	public void setTdpCadreCasteInfoDAO(ITdpCadreCasteInfoDAO tdpCadreCasteInfoDAO) {
		this.tdpCadreCasteInfoDAO = tdpCadreCasteInfoDAO;
	}

	public void setTdpCadreAgerangeInfoDAO(
			ITdpCadreAgerangeInfoDAO tdpCadreAgerangeInfoDAO) {
		this.tdpCadreAgerangeInfoDAO = tdpCadreAgerangeInfoDAO;
	}

	public void setCadreSurveyTransactionService(
			ICadreSurveyTransactionService cadreSurveyTransactionService) {
		this.cadreSurveyTransactionService = cadreSurveyTransactionService;
	}

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
	
	public IAppointmentDAO getAppointmentDAO() {
		return appointmentDAO;
	}

	public void setAppointmentDAO(IAppointmentDAO appointmentDAO) {
		this.appointmentDAO = appointmentDAO;
	}
	
	public IEventDAO getEventDAO() {
		return eventDAO;
	}

	public void setEventDAO(IEventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}
	
	public IMahanaduDashBoardService1 getMahanaduDashBoardService1() {
		return mahanaduDashBoardService1;
	}

	public void setMahanaduDashBoardService1(
			IMahanaduDashBoardService1 mahanaduDashBoardService1) {
		this.mahanaduDashBoardService1 = mahanaduDashBoardService1;
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
			Long limit = 100000l;
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
		
			List<Long> thirdList = new ArrayList<Long>();
			
			thirdList.add(6l);
			thirdList.add(8l);
			thirdList.add(9l);
			thirdList.add(10l);
			thirdList.add(17l);


			
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
				if(totalCount >= 100000)
				{
					break ;
				}
				else
				{
					
					//PREPARE DATA FOR RURAL BY USING RURAL CONSTITUECYES
					Integer r1Count =   saveRuralConstituencysDataType1(prevDate,tableName,ids,limit);
					totalCount = totalCount + r1Count;
					if(totalCount >= 100000)
					{
						break;
					}
					else
					{
						limit = limit - r1Count;
						//PREPARE DATA FOR RURAL BY USING RURAL-URBAN CONSTITUECYES
						Integer r2Count =   saveRuralConstituencysDataType2(prevDate,tableName,ids,limit);
						totalCount = totalCount + r2Count;
						if(totalCount >= 100000)
						{
							break;
						}
						else
						{
							limit = limit - r2Count;
							
							//PREPARE DATA FOR RURAL-URBAN BY USING RURAL-URBAN CONSTITUECYES
							Integer ruCount =   saveRuralUrbanConstituencysDataType(prevDate,tableName,ids,limit);
							totalCount = totalCount+ ruCount;
							if(totalCount >= 100000)
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
	
	public void runSchuduler(List<Long> districtIds)
	{
		try {
			cadreSurveyTransactionService.sendTargetBasedSMSforLocationWiseManagers(districtIds);
		} catch (Exception e) {
			LOG.error("Exception Raised in runSchuduler()",e); 
		}
	}
	
	public Long updateTdpCadreInfoDetails()
	{
		LOG.info("\n\n entered updation for TdpCadreInfo Table \n" );
		Long effectedCount = 0L;
		try {
			
			effectedCount = (Long) transactionTemplate.execute(new TransactionCallback() {
				 public Object doInTransaction(TransactionStatus status) {
					
					tdpCadreInfoDAO.deleteTdpCadreInfoTableBeforeUpdate();
					
					
					int effectedCount1 =  tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Registered","District");
					int effectedCount2 = tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Registered","Constituency");
					int effectedCount3 =  tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Registered","Tehsil");
					int effectedCount4 =  tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Registered","Localbody");
					int effectedCount5 =  tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Registered","Ward");
					int effectedCount6 =  tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Registered","Panchayat");
					
				
					int effectedCount7 =  tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Printed","District");
					int effectedCount8 =  tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Printed","Constituency");
					//int effectedCount9 =  tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Printed","Tehsil");
					//int effectedCount10 =  tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Printed","Localbody");
					//int effectedCount11 =  tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Printed","Ward");
					//int effectedCount12 =  tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Printed","Panchayat");
					
					Long effectedCount = 0L;
					
					try {
						String totalCount  = String.valueOf(effectedCount1+effectedCount2+effectedCount3+effectedCount4+effectedCount5+effectedCount6+effectedCount7+effectedCount8);						
						effectedCount = Long.valueOf(totalCount);
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					return effectedCount;
				}
			});
			LOG.info("\n\n Total TdpCadreInfo Table effected records : "+effectedCount+"\n\n" ); 
		} catch (Exception e) {
			effectedCount = 0L;
			LOG.error("Exception Raised in updateTdpCadreInfoDetails()",e); 
			mobileService.sendSmsToUserForUpdations("Exc. in updateTdpCadreInfoDetails() : \n "+e.getMessage(),"9581434970");
		}
		return effectedCount;
	}
	
	public Long updateTdpCadreCasteInfoDetails()
	{
		Long effectedCount = 0L;
		try {
			LOG.info("\n\n entered updation for TdpCadreCasteInfo Table \n" );
			effectedCount = (Long) transactionTemplate.execute(new TransactionCallback() {
				 public Object doInTransaction(TransactionStatus status) {
					tdpCadreCasteInfoDAO.deleteTdpCadreCasteInfoTableBeforeUpdate();
					
					int effectedCount1 = tdpCadreCasteInfoDAO.updateTdpCadreCasteInfoTableByScheduler("Constituency");
					int effectedCount2 = tdpCadreCasteInfoDAO.updateTdpCadreCasteInfoTableByScheduler("District");
					int effectedCount3 = tdpCadreCasteInfoDAO.updateTdpCadreCasteInfoTableByScheduler("Panchayat");
					int effectedCount4 = tdpCadreCasteInfoDAO.updateTdpCadreCasteInfoTableByScheduler("Ward");
					int effectedCount5 = tdpCadreCasteInfoDAO.updateTdpCadreCasteInfoTableByScheduler("Tehsil");
					int effectedCount6 = tdpCadreCasteInfoDAO.updateTdpCadreCasteInfoTableByScheduler(IConstants.LOCAL_BODY_ELECTION);	
					String totalCount  = String.valueOf(effectedCount1 + effectedCount2 + effectedCount3 + effectedCount4 + effectedCount5 + effectedCount6);
					Long effectedCount = Long.valueOf(totalCount);
					
					return effectedCount;
				}
			});
			LOG.info("\n\n Total TdpCadreCasteInfo Table effected records : "+effectedCount+"\n\n" ); 			
		} catch (Exception e) {
			effectedCount = 0L;
			LOG.error("Exception Raised in updateTdpCadreCasteInfoDetails()",e);
			mobileService.sendSmsToUserForUpdations("Exc. in updateTdpCadreCasteInfoDetails() : \n "+e.getMessage(),"9581434970");
			e.printStackTrace();
		}
		return effectedCount;
	}
	
	public Long updateTdpCadreAgerangeInfoDetails()
	{
		Long effectedCount = 0L;
		try {
			LOG.info("\n\n entered updation for TdpCadreAgeRangeInfo Table \n" );
			effectedCount = (Long) transactionTemplate.execute(new TransactionCallback() {
				 public Object doInTransaction(TransactionStatus status) {
					tdpCadreAgerangeInfoDAO.deleteTdpCadreReageRangeInfoTableBeforeUpdate();
					
					int effectedCount1 = tdpCadreAgerangeInfoDAO.updateTdpCadReageRangeInfoTableByScheduler("Constituency");
					int effectedCount2 = tdpCadreAgerangeInfoDAO.updateTdpCadReageRangeInfoTableByScheduler("District");
					int effectedCount3 = tdpCadreAgerangeInfoDAO.updateTdpCadReageRangeInfoTableByScheduler("Panchayat");
					int effectedCount4 = tdpCadreAgerangeInfoDAO.updateTdpCadReageRangeInfoTableByScheduler("Ward");
					int effectedCount5 = tdpCadreAgerangeInfoDAO.updateTdpCadReageRangeInfoTableByScheduler("Tehsil");
					int effectedCount6 = tdpCadreAgerangeInfoDAO.updateTdpCadReageRangeInfoTableByScheduler(IConstants.LOCAL_BODY_ELECTION);	
					String totalCount  = String.valueOf(effectedCount1 + effectedCount2 + effectedCount3 + effectedCount4 + effectedCount5 + effectedCount6);
					Long effectedCount = Long.valueOf(totalCount);
					
					return effectedCount;
				}
			});
			LOG.info("\n\n Total TdpCadreAgeRangeInfo Table effected records : "+effectedCount+"\n\n" ); 
		} catch (Exception e) {
			effectedCount = 0L;
			LOG.error("Exception Raised in updateTdpCadreAgerangeInfoDetails()",e); 
			mobileService.sendSmsToUserForUpdations("Exc. in updateTdpCadreAgerangeInfoDetails() : \n "+e.getMessage(),"9581434970");
			e.printStackTrace();
		}
		return effectedCount;
	}
	
	public  String updateTrainingCampSpeakersDetails()
	  {
	    try {
	      transactionTemplate.execute(new TransactionCallbackWithoutResult() {
	        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
	          try {
	            DateUtilService date = new DateUtilService();
	            Date todayDate = date.getCurrentDateAndTime();
	            List<Object[]> speakersList = trainingCampAttendanceDAO.getTodaySpeakersAttendedDetails(todayDate);
	            List<Long> speakersListInAttendee = trainingCampBatchAttendeeDAO.getTodaySpeakersDetails(todayDate);
	            
	           List<String> existingIdslist = new ArrayList<String>(0);
	           if(speakersListInAttendee!=null && speakersListInAttendee.size()>0){
	        	   for(Long long1 : speakersListInAttendee){
	        		   existingIdslist.add(long1.toString());
		           }
	           }
	           
	           //staff cadreIds
	           List<String> excludeTdpCadreIdsList = trainingCampBatchDAO.getExcudingTdpCadreIdsList();
	           List<String> staffCadreIdsList = new ArrayList<String>(0);
	               
	           if(excludeTdpCadreIdsList != null && excludeTdpCadreIdsList.size()>0)
	           {
	             for (String cadreId : excludeTdpCadreIdsList) {
	            	 staffCadreIdsList.add(cadreId);
	             }
	           }
	            
	            if(speakersList != null && speakersList.size()>0)
	            {
	              for (Object[] speaker : speakersList) {
	            	  if(!existingIdslist.contains(speaker[0].toString()) && !staffCadreIdsList.contains(speaker[0].toString())){
	            		  	Long tdpCadreId =speaker[0] != null ? Long.valueOf(speaker[0].toString()):0L; 
			                Long batchId =speaker[1] != null ? Long.valueOf(speaker[1].toString()):0L;
			                String attendedTimeStr = speaker[2] != null ?speaker[2].toString():"";
			                
			                TrainingCampBatchAttendee trainingCampBatchAttendee = new TrainingCampBatchAttendee();
			                trainingCampBatchAttendee.setTdpCadreId(tdpCadreId);
			                trainingCampBatchAttendee.setTrainingCampBatchId(batchId);
			                trainingCampBatchAttendee.setAttendedTime(new SimpleDateFormat("yy-MM-dd hh:mm:sss").parse(attendedTimeStr));
			                trainingCampBatchAttendee.setInsertedTime(todayDate);
			                trainingCampBatchAttendee.setUpdatedTime(todayDate);
			                trainingCampBatchAttendee.setInsertedBy(1L);
			                trainingCampBatchAttendee.setUpdatedBy(1L);
			                trainingCampBatchAttendee.setIsDeleted("false");
			                trainingCampBatchAttendeeDAO.save(trainingCampBatchAttendee);
	            	  } 
	              }
	            }          
	          } catch (Exception e) {
	        	  System.out.println(e);
	          }
	        }
	      });
	      return "success";
	    } catch (Exception e) {
	      LOG.error("Exception Raised in updateTrainingCampSpeakersDetails()",e); 
	      return "failure";
	    }
	  }
	
	public void changeApptStatusToAttended(Date fromDate,Long attendedStatusId,Long fixedStatusId)
	{
		try {
			Calendar cal = Calendar.getInstance();
	    	cal.getTime();
	    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			LOG.info( sdf.format(cal.getTime()));
			
			List<Long> apptIds = appointmentDAO.getAppointmentIdsByDateByStatus(fromDate,fixedStatusId);
			int updatedApptsCounts = appointmentDAO.updatedAppointmentStatus(apptIds,attendedStatusId,1l,fromDate);
			
			LOG.info(updatedApptsCounts+" No of Appts Changed to Attended Status");
			
			
		} catch (Exception e) {
			LOG.error("Exception Occured in changeApptStatus() Method, Exception is - ",e);
		}
	}
	public void sendPdfReport(){
		
		try{
			
			Long parenteventId = 30l;
			
			 String startDate="24/05/2016";
		     String endDate = "26/05/2016";
			 
		     List<Long> stateIds = new ArrayList<Long>();
			 stateIds.add(1l);
			 stateIds.add(36l);
			 stateIds.add(0l);
			 
			 List<Long> subeventIds = eventDAO.getSubEventsByParentEventId(parenteventId);
			 
			 mahanaduDashBoardService1.getAllImages(parenteventId,subeventIds,startDate,endDate,stateIds);
		}catch(Exception e){
			LOG.error("Exception Occured in sendPdfReport() Method",e);
		}
	}
	
}
