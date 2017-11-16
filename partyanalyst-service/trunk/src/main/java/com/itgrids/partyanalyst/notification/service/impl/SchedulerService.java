package com.itgrids.partyanalyst.notification.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgrids.partyanalyst.dao.IAppointmentDAO;
import com.itgrids.partyanalyst.dao.IEmployeeDepartmentDAO;
import com.itgrids.partyanalyst.dao.IEmployeeWorkLocationDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dao.IEventDAO;
import com.itgrids.partyanalyst.dao.IPartyOfficeDAO;
import com.itgrids.partyanalyst.dao.IReportEmailDAO;
import com.itgrids.partyanalyst.dao.ISearchEngineIPAddressDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreAgerangeInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCasteInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDataSourceCountInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDataSourceCountInfoTempDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentYearDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreInfoDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampBatchAttendeeDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampBatchDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.IUserEmailDAO;
import com.itgrids.partyanalyst.dao.IUserTrackingDAO;
import com.itgrids.partyanalyst.dto.DataSourceVO;
import com.itgrids.partyanalyst.dto.DepartmentVO;
import com.itgrids.partyanalyst.dto.EmailAttributesVO;
import com.itgrids.partyanalyst.dto.OfficeMemberVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Event;
import com.itgrids.partyanalyst.model.TrainingCampBatchAttendee;
import com.itgrids.partyanalyst.notification.service.ISchedulerService;
import com.itgrids.partyanalyst.service.IAlertService;
import com.itgrids.partyanalyst.service.ICadreSurveyTransactionService;
import com.itgrids.partyanalyst.service.ICoreDashboardPartyMeetingService;
import com.itgrids.partyanalyst.service.IMahanaduDashBoardService1;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IMobileService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;

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
	private IEmployeeWorkLocationDAO employeeWorkLocationDAO;
	private IEmployeeDepartmentDAO employeeDepartmentDAO;
	private IReportEmailDAO reportEmailDAO;
	private IUserEmailDAO userEmailDAO;
	private IPartyOfficeDAO partyOfficeDAO;
	private IEventAttendeeDAO eventAttendeeDAO;
	private ICoreDashboardPartyMeetingService coreDashboardPartyMeetingService;
	private ITdpCadreDataSourceCountInfoDAO tdpCadreDataSourceCountInfoDAO;
	private ITdpCadreDataSourceCountInfoTempDAO tdpCadreDataSourceCountInfoTempDAO;
	private ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO;
	private ITrainingCampDetailsInfoDAO trainingCampDetailsInfoDAO;
	
	private IAlertService alertService;
	
	
	public void setAlertService(IAlertService alertService) {
		this.alertService = alertService;
	}

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
	public IEmployeeWorkLocationDAO getEmployeeWorkLocationDAO() {
		return employeeWorkLocationDAO;
	}

	public void setEmployeeWorkLocationDAO(IEmployeeWorkLocationDAO employeeWorkLocationDAO) {
		this.employeeWorkLocationDAO = employeeWorkLocationDAO;
	}
	public IEmployeeDepartmentDAO getEmployeeDepartmentDAO() {
		return employeeDepartmentDAO;
	}

	public void setEmployeeDepartmentDAO(
			IEmployeeDepartmentDAO employeeDepartmentDAO) {
		this.employeeDepartmentDAO = employeeDepartmentDAO;
	}
	public IReportEmailDAO getReportEmailDAO() {
		return reportEmailDAO;
	}

	public void setReportEmailDAO(IReportEmailDAO reportEmailDAO) {
		this.reportEmailDAO = reportEmailDAO;
	}
	

	public IUserEmailDAO getUserEmailDAO() {
		return userEmailDAO;
	}

	public void setUserEmailDAO(IUserEmailDAO userEmailDAO) {
		this.userEmailDAO = userEmailDAO;
	}
	public IPartyOfficeDAO getPartyOfficeDAO() {
		return partyOfficeDAO;
	}

	public void setPartyOfficeDAO(IPartyOfficeDAO partyOfficeDAO) {
		this.partyOfficeDAO = partyOfficeDAO;
	}
	public IEventAttendeeDAO getEventAttendeeDAO() {
		return eventAttendeeDAO;
	}

	public void setEventAttendeeDAO(IEventAttendeeDAO eventAttendeeDAO) {
		this.eventAttendeeDAO = eventAttendeeDAO;
	}
	
	public void setCoreDashboardPartyMeetingService(
			ICoreDashboardPartyMeetingService coreDashboardPartyMeetingService) {
		this.coreDashboardPartyMeetingService = coreDashboardPartyMeetingService;
	}
	public void setTdpCadreDataSourceCountInfoDAO(
			ITdpCadreDataSourceCountInfoDAO tdpCadreDataSourceCountInfoDAO) {
		this.tdpCadreDataSourceCountInfoDAO = tdpCadreDataSourceCountInfoDAO;
	}

	public void setTdpCadreDataSourceCountInfoTempDAO(
			ITdpCadreDataSourceCountInfoTempDAO tdpCadreDataSourceCountInfoTempDAO) {
		this.tdpCadreDataSourceCountInfoTempDAO = tdpCadreDataSourceCountInfoTempDAO;
	}
	public void setTdpCadreEnrollmentYearDAO(
			ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO) {
		this.tdpCadreEnrollmentYearDAO = tdpCadreEnrollmentYearDAO;
	}
   public void setTrainingCampDetailsInfoDAO(
			ITrainingCampDetailsInfoDAO trainingCampDetailsInfoDAO) {
		this.trainingCampDetailsInfoDAO = trainingCampDetailsInfoDAO;
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
			if(!IConstants.DEPLOYED_HOST.equalsIgnoreCase("tdpserver"))
				return;
			
			Calendar cal = Calendar.getInstance();
	    	cal.getTime();
	    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			LOG.info( sdf.format(cal.getTime()));
			int ctpCount = 0;//surveyDetailsInfoDAO.saveDailyCallCenterVerifiedDetails(fromDate);
			int smsCount =  0;//surveyDetailsInfoDAO.saveSMSMobileNumbers(fromDate);
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
	
	public Long updateTdpCadreInfoDetails(final Long enrollmentYearId)
	{
		LOG.info("\n\n entered updation for TdpCadreInfo Table \n" );
		Long effectedCount = 0L;
		try {
			
			effectedCount = (Long) transactionTemplate.execute(new TransactionCallback() {
				 public Object doInTransaction(TransactionStatus status) {
					
					tdpCadreInfoDAO.deleteTdpCadreInfoTableBeforeUpdate(enrollmentYearId);
					
					
					int effectedCount1 =  tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Registered","District",enrollmentYearId);
					int effectedCount2 = tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Registered","Constituency",enrollmentYearId);
					int effectedCount3 =  tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Registered","Tehsil",enrollmentYearId);
					int effectedCount4 =  tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Registered","Localbody",enrollmentYearId);
					int effectedCount5 =  tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Registered","Ward",enrollmentYearId);
					int effectedCount6 =  tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Registered","Panchayat",enrollmentYearId);
					
				
					int effectedCount7 =  tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Printed","District",enrollmentYearId);
					int effectedCount8 =  tdpCadreInfoDAO.updateTdpCadreInfoTableByScheduler("Printed","Constituency",enrollmentYearId);
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
	
	public Long updateTdpCadreCasteInfoDetails(final Long enrollmentYearId)
	{
		Long effectedCount = 0L;
		try {
			LOG.info("\n\n entered updation for TdpCadreCasteInfo Table \n" );
			effectedCount = (Long) transactionTemplate.execute(new TransactionCallback() {
				 public Object doInTransaction(TransactionStatus status) {
					tdpCadreCasteInfoDAO.deleteTdpCadreCasteInfoTableBeforeUpdate(enrollmentYearId);
					
					int effectedCount1 = tdpCadreCasteInfoDAO.updateTdpCadreCasteInfoTableByScheduler("Constituency",enrollmentYearId);
					int effectedCount2 = tdpCadreCasteInfoDAO.updateTdpCadreCasteInfoTableByScheduler("District",enrollmentYearId);
					int effectedCount3 = tdpCadreCasteInfoDAO.updateTdpCadreCasteInfoTableByScheduler("Panchayat",enrollmentYearId);
					int effectedCount4 = tdpCadreCasteInfoDAO.updateTdpCadreCasteInfoTableByScheduler("Ward",enrollmentYearId);
					int effectedCount5 = tdpCadreCasteInfoDAO.updateTdpCadreCasteInfoTableByScheduler("Tehsil",enrollmentYearId);
					int effectedCount6 = tdpCadreCasteInfoDAO.updateTdpCadreCasteInfoTableByScheduler(IConstants.LOCAL_BODY_ELECTION,enrollmentYearId);	
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
	
	public Long updateTdpCadreAgerangeInfoDetails(final Long enrollmentYearId)
	{
		Long effectedCount = 0L;
		try {
			LOG.info("\n\n entered updation for TdpCadreAgeRangeInfo Table \n" );
			effectedCount = (Long) transactionTemplate.execute(new TransactionCallback() {
				 public Object doInTransaction(TransactionStatus status) {
					tdpCadreAgerangeInfoDAO.deleteTdpCadreReageRangeInfoTableBeforeUpdate(enrollmentYearId);
					
					int effectedCount1 = tdpCadreAgerangeInfoDAO.updateTdpCadReageRangeInfoTableByScheduler("Constituency",enrollmentYearId);
					int effectedCount2 = tdpCadreAgerangeInfoDAO.updateTdpCadReageRangeInfoTableByScheduler("District",enrollmentYearId);
					int effectedCount3 = tdpCadreAgerangeInfoDAO.updateTdpCadReageRangeInfoTableByScheduler("Panchayat",enrollmentYearId);
					int effectedCount4 = tdpCadreAgerangeInfoDAO.updateTdpCadReageRangeInfoTableByScheduler("Ward",enrollmentYearId);
					int effectedCount5 = tdpCadreAgerangeInfoDAO.updateTdpCadReageRangeInfoTableByScheduler("Tehsil",enrollmentYearId);
					int effectedCount6 = tdpCadreAgerangeInfoDAO.updateTdpCadReageRangeInfoTableByScheduler(IConstants.LOCAL_BODY_ELECTION,enrollmentYearId);	
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
	            List<Object[]> speakersList = trainingCampAttendanceDAO.getTodaySpeakersAttendedDetails(todayDate,null,null);
	            List<Long> speakersListInAttendee = trainingCampBatchAttendeeDAO.getTodaySpeakersDetails(todayDate,null,null);
	            
	            if(speakersList != null && speakersList.size()>0)
	            {
	            	
	               List<String> existingIdslist = new ArrayList<String>(0);
	  	           if(speakersListInAttendee!=null && speakersListInAttendee.size()>0){
	  	        	   for(Long long1 : speakersListInAttendee){
	  	        		   existingIdslist.add(long1.toString());
	  		           }
	  	           }
	  	           
	  	           //staff cadreIds
	  	           List<String> excludeTdpCadreIdsList = trainingCampBatchDAO.getExcudingTdpCadreIdsList(null,null);
	  	           List<String> staffCadreIdsList = new ArrayList<String>(0);
	  	               
	  	           if(excludeTdpCadreIdsList != null && excludeTdpCadreIdsList.size()>0)
	  	           {
	  	             for (String cadreId : excludeTdpCadreIdsList) {
	  	            	 staffCadreIdsList.add(cadreId);
	  	             }
	  	           }
		  	           
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
		
		LOG.info("\n\n entered in to sendPdfReport() method in scheduler service \n" );
		LOG.info("\n\n "+new DateUtilService().getCurrentDateAndTime()+"\n");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			
			Long parenteventId = 51l;
			DateUtilService dateUtilService = new DateUtilService();
		     
		     Object[] dates = eventDAO.getEventDates(parenteventId);
		     String startDate = sdf.format((Date)dates[0]);
		     String endDate   = sdf.format((Date)dates[1]);
		     
		     Event event = eventDAO.get(parenteventId);
		     
		    Date presentTime = dateUtilService.getCurrentDateAndTime();
		    Date startTime = dateUtilService.getDateAndTime(dateUtilService.getCurrentDateInStringFormatYYYYMMDD()+" 07:00:00");
		    Date endTime = dateUtilService.getDateAndTime(dateUtilService.getCurrentDateInStringFormatYYYYMMDD()+" 22:00:00");
			
		    if(!(startTime.getTime() <= presentTime.getTime() && presentTime.getTime() <= endTime.getTime()))
		    	return;    
		    	
		     List<Long> stateIds = new ArrayList<Long>();
			 stateIds.add(1l);
			 stateIds.add(36l);
			 
			 List<Long> subeventIds = eventDAO.getSubEventsByParentEventId(parenteventId);
			 
			 mahanaduDashBoardService1.getAllImages(parenteventId,subeventIds,startDate,endDate,stateIds);
		}catch(Exception e){
			LOG.error("Exception Occured in sendPdfReport() Method",e);
		}
	}
	/*
	 * author-Swadhin Lenka [ItGrids]
	 */
	public void runTheJobForEveryDayToSendEmployeeAttendance(){    
		
	}
	public void runTheJobForEveryDayToSendEmpAttendanceDeptWise(){
		LOG.info("\n\n entered in to runTheJobForEveryDayToSendEmployeeAttendance() method in SchedulerService \n" );
		LOG.info("\n\n "+new DateUtilService().getCurrentDateAndTime()+"\n");
		try{
			String area = "dept";  
			DateUtilService dateUtilService = new DateUtilService();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
			Date currentDateAndTime = dateUtilService.getCurrentDateAndTime();
			EmailAttributesVO emailVo = new EmailAttributesVO();
			Date fromDate = dateUtilService.getCurrentDateAndTime();
			Date toDate = dateUtilService.getCurrentDateAndTime();
			String currentDateStr = sdf.format(currentDateAndTime);
			emailVo.setDate(currentDateStr);
			emailVo.setSubject("Party Office Emplyees Attendance Information");  
			//get list of attended cadre id from event_attendee
			List<Long> presentedCaderIdList = new ArrayList<Long>();
			Map<Long,String> employeeIdLocationMap = new HashMap<Long,String>();
			List<Object[]> eventIdAndPresentedCadreIdList = eventAttendeeDAO.getEventIdAndPresentedCadreIdList(fromDate, toDate);
			if(eventIdAndPresentedCadreIdList != null && eventIdAndPresentedCadreIdList.size() > 0){
				for(Object[] eventIdAndPresentedCadreId : eventIdAndPresentedCadreIdList){
					presentedCaderIdList.add(eventIdAndPresentedCadreId[2] != null ? (Long)eventIdAndPresentedCadreId[2] : 0l);
					employeeIdLocationMap.put(eventIdAndPresentedCadreId[2] != null ? (Long)eventIdAndPresentedCadreId[2] : 0l, eventIdAndPresentedCadreId[3] != null ? eventIdAndPresentedCadreId[3].toString() : "");
				}
			}    
			List<Object[]> listOfDeptPerEmailId = reportEmailDAO.getDeptList(1l);
			Map<Long,List<Long>> emailIdAndDeptIdListMap = new HashMap<Long,List<Long>>();
			prepareEmailIdAndDeptIdListMap(listOfDeptPerEmailId, emailIdAndDeptIdListMap);
			//System.out.println(emailIdAndDeptIdListMap);
			Set<Long> emailIdList = emailIdAndDeptIdListMap.keySet();
			//Collection<List<Long>> listOflistOfDeptId = emailIdAndDeptIdListMap.values();
			List<Long> deptList = emailIdAndDeptIdListMap.get(1l);    
			Long officeId = 1l;
			List<Object[]> specificOfficeTotalEmployeeList = null;
			List<Object[]> specificOfficeTotalAttendedEmployee = null;
			List<Object[]> departmentWiseTotalEmployeeList1 = null;
			List<Object[]> departmenWiseTotalAttendedEmployee1 = null;
			List<Object[]> departmentWiseThenOfficeWiseTotalAttendedEmployee1 = null;
			List<Object[]> specificOfficeTotalNonAttendedEmployeeDetailsList = null;
			List<Object[]> specificOfficeTotalAttendedEmployeeDetailsList = null;
			ResultStatus resultStatus1 = null;
			specificOfficeTotalEmployeeList = employeeWorkLocationDAO.getSpecificOfficeTotalEmployeeListFilter(deptList,officeId);
			specificOfficeTotalAttendedEmployee = employeeWorkLocationDAO.getSpecificOfficeTotalAttendedEmployeeFilter(deptList, presentedCaderIdList, officeId);
			departmentWiseTotalEmployeeList1 = employeeDepartmentDAO.getDepartmentWiseTotalEmployeeListFilterForOffice(deptList, officeId);
			departmenWiseTotalAttendedEmployee1 = employeeDepartmentDAO.getDepartmentWiseTotalAttendedEmployeeFilterForOffice(deptList, presentedCaderIdList, officeId);
			departmentWiseThenOfficeWiseTotalAttendedEmployee1 = employeeDepartmentDAO.getDepartmentWiseThenOfficeWiseTotalAttendedEmployeeFilterForOffice(deptList, presentedCaderIdList, officeId);
			specificOfficeTotalNonAttendedEmployeeDetailsList = employeeWorkLocationDAO.getspecificOfficeTotalNonAttendedEmployeeDetailsFilter(deptList, presentedCaderIdList, officeId);
			specificOfficeTotalAttendedEmployeeDetailsList = employeeWorkLocationDAO.getSpecificOfficeTotalAttendedEmployeeDetailsFilter(fromDate, toDate, deptList, presentedCaderIdList, officeId);
			
			generatePdfReport("office", emailVo,	specificOfficeTotalEmployeeList, 
												specificOfficeTotalAttendedEmployee, 
												departmentWiseTotalEmployeeList1, 
												departmenWiseTotalAttendedEmployee1, 
												departmentWiseThenOfficeWiseTotalAttendedEmployee1, 
												specificOfficeTotalNonAttendedEmployeeDetailsList,   
												specificOfficeTotalAttendedEmployeeDetailsList );  
			
			resultStatus1 = sendEmailWithPdfAttachment("office", emailVo,officeId); 
			
			//for Guntur party office
			officeId = 2l;
			specificOfficeTotalEmployeeList = employeeWorkLocationDAO.getSpecificOfficeTotalEmployeeListFilter(deptList,officeId);
			specificOfficeTotalAttendedEmployee = employeeWorkLocationDAO.getSpecificOfficeTotalAttendedEmployeeFilter(deptList, presentedCaderIdList, officeId);
			departmentWiseTotalEmployeeList1 = employeeDepartmentDAO.getDepartmentWiseTotalEmployeeListFilterForOffice(deptList, officeId);
			departmenWiseTotalAttendedEmployee1 = employeeDepartmentDAO.getDepartmentWiseTotalAttendedEmployeeFilterForOffice(deptList, presentedCaderIdList, officeId);
			departmentWiseThenOfficeWiseTotalAttendedEmployee1 = employeeDepartmentDAO.getDepartmentWiseThenOfficeWiseTotalAttendedEmployeeFilterForOffice(deptList, presentedCaderIdList, officeId);
			specificOfficeTotalNonAttendedEmployeeDetailsList = employeeWorkLocationDAO.getspecificOfficeTotalNonAttendedEmployeeDetailsFilter(deptList, presentedCaderIdList, officeId);
			specificOfficeTotalAttendedEmployeeDetailsList = employeeWorkLocationDAO.getSpecificOfficeTotalAttendedEmployeeDetailsFilter(fromDate, toDate, deptList, presentedCaderIdList, officeId);
			
			generatePdfReport("office", emailVo,	specificOfficeTotalEmployeeList, 
												specificOfficeTotalAttendedEmployee, 
												departmentWiseTotalEmployeeList1, 
												departmenWiseTotalAttendedEmployee1, 
												departmentWiseThenOfficeWiseTotalAttendedEmployee1, 
												specificOfficeTotalNonAttendedEmployeeDetailsList,   
												specificOfficeTotalAttendedEmployeeDetailsList ); 

			resultStatus1 = sendEmailWithPdfAttachment("office", emailVo,officeId); 
			
			
			for(Long userEmailId : emailIdList){    
				emailVo.setEmailId(userEmailId);
				deptList = emailIdAndDeptIdListMap.get(userEmailId);  
				List<Object[]> officeWiseTotalEmployeeList = employeeWorkLocationDAO.getOfficeWiseTotalEmployeeListFilter(deptList);
				List<Object[]> officeWiseTotalAttendedEmployee = employeeWorkLocationDAO.getOfficeWiseTotalAttendedEmployeeFilter(deptList, presentedCaderIdList); 
				List<Object[]> departmentWiseTotalEmployeeList = employeeDepartmentDAO.getDepartmentWiseTotalEmployeeListFilter(deptList);
				List<Object[]> departmenWiseTotalAttendedEmployee = employeeDepartmentDAO.getDepartmentWiseTotalAttendedEmployeeFilter(deptList, presentedCaderIdList); 
				List<Object[]> departmentWiseThenOfficeWiseTotalAttendedEmployee = employeeDepartmentDAO.getDepartmentWiseThenOfficeWiseTotalAttendedEmployeeFilter(deptList, presentedCaderIdList);
				List<Object[]> officeWiseTotalNonAttendedEmployeeDetailsList = employeeWorkLocationDAO.getOfficeWiseTotalNonAttendedEmployeeDetailsFilter(deptList, presentedCaderIdList);
				List<Object[]> officeWiseTotalAttendedEmployeeDetailsList = employeeWorkLocationDAO.getOfficeWiseTotalAttendedEmployeeDetailsFilter(fromDate, toDate, deptList, presentedCaderIdList);
				
				if(officeWiseTotalAttendedEmployeeDetailsList != null && officeWiseTotalAttendedEmployeeDetailsList.size() > 0){  
					for(Object[] obj : officeWiseTotalAttendedEmployeeDetailsList){  
						Long cadreId = (Long)obj[6];
						String actualLocation = employeeIdLocationMap.get(cadreId);  
						String homeLocation = obj[1].toString();
						if(!(actualLocation.equalsIgnoreCase(homeLocation))){
							String locName = actualLocation.substring(0, 3);
							obj[5] = obj[5]+"["+locName+"]";        
						}
					}
				}
				generatePdfReport(area, emailVo,	officeWiseTotalEmployeeList, 
													officeWiseTotalAttendedEmployee, 
													departmentWiseTotalEmployeeList, 
													departmenWiseTotalAttendedEmployee, 
													departmentWiseThenOfficeWiseTotalAttendedEmployee, 
													officeWiseTotalNonAttendedEmployeeDetailsList,   
													officeWiseTotalAttendedEmployeeDetailsList );
				ResultStatus resultStatus = sendEmailWithPdfAttachment(area, emailVo,null);      
				System.out.println("Hi");       
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in runTheJobForEveryDayToSendEmployeeAttendance() Method",e);
			
			
		}
	}
	
	public void prepareEmailIdAndDeptIdListMap(List<Object[]> listOfDeptPerEmailId, Map<Long,List<Long>> emailIdAndDeptIdListMap){
		if(listOfDeptPerEmailId.size() > 0){
			List<Long> deptIdList = null;
			@SuppressWarnings("unused")
			List<Long> tempDeptIdList = new ArrayList<Long>(0);
			for(Object[] emailIdAndDeptId : listOfDeptPerEmailId){
				Long emailId = (Long)emailIdAndDeptId[1];
				deptIdList = emailIdAndDeptIdListMap.get(emailId);
				if(deptIdList == null){
					deptIdList = new ArrayList<Long>(0);
					deptIdList.add((Long)emailIdAndDeptId[3]);
					emailIdAndDeptIdListMap.put(emailId, deptIdList);
				}else{
					deptIdList.add((Long)emailIdAndDeptId[3]);
					emailIdAndDeptIdListMap.put(emailId, deptIdList);
				}
			}
		}
		
	}
	
	/*
	 * author-Swadhin Lenka [ItGrids]  
	 */
	public void generatePdfReport(String area, EmailAttributesVO emailVo,	List<Object[]> officeWiseTotalEmployeeList, 
																			List<Object[]> officeWiseTotalAttendedEmployeeList, 
																			List<Object[]> departmentWiseTotalEmployeeList, 
																			List<Object[]> departmenWiseTotalAttendedEmployee, 
																			List<Object[]> departmentWiseThenOfficeWiseTotalAttendedEmployee,
																			List<Object[]> officeWiseTotalNonAttendedEmployeeDetailsList,
																			List<Object[]> officeWiseTotalAttendedEmployeeDetailsList ){
		try{
			DateUtilService dateUtilService = new DateUtilService();
			CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm a");   
			//SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			TimeZone timeZone=TimeZone.getTimeZone("Asia/Calcutta");
			sdf1.setTimeZone(timeZone);
			String dt = sdf1.format(new Date());  
			//String dt1 = sdf2.format(new Date());
			String staticPath = IConstants.STATIC_CONTENT_FOLDER_URL;
			@SuppressWarnings("unused")
			String folderCreation = commonMethodsUtilService.createFolder(staticPath);
			staticPath = staticPath + "reports";
			@SuppressWarnings("unused")
			String folderCreation1 = commonMethodsUtilService.createFolder(staticPath);
			staticPath = staticPath + "/" + emailVo.getDate();
			@SuppressWarnings("unused")
			String folderCreation2 = commonMethodsUtilService.createFolder(staticPath);
			
			Date currentDate = dateUtilService.getCurrentDateAndTime();
			String currentDateString = sdf.format(currentDate);
			
			String pdfName = currentDateString+"_"+RandomNumberGeneraion.randomGenerator(5)+".pdf";
			String pdfFilePath = staticPath+"/"+pdfName;
			emailVo.setFileName(pdfName);
			emailVo.setFilePath(staticPath);    
			emailVo.setTime(dt);
			FileOutputStream file = new FileOutputStream(new File(pdfFilePath));
			
			Rectangle pageSize = new Rectangle(PageSize.A4);
		    pageSize.setBackgroundColor(new BaseColor(255,225,0));  
			Document  document=new Document(pageSize);  
		     
		    PdfWriter writer=PdfWriter.getInstance(document,file);
		    writer.setPageEvent(new PdfPageEventHelper(){
		    	//Font ffont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, GrayColor.RED);
		        Font ffont2 = new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.BOLD);
		        public void onEndPage(PdfWriter writer, Document document) {
		            PdfContentByte cb = writer.getDirectContent();
		            //Phrase header = new Phrase("EMPLOYEE ATTENDANCE", ffont);
		            Phrase footer = new Phrase("www.mytdp.com", ffont2);
		            //ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, header, (document.right() - document.left()) / 2 + document.leftMargin(), document.top() + 10, 0);
		            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
		                    footer,
		                    (document.right() - document.left()) / 2 + document.leftMargin(),
		                    document.bottom() - 10, 0);
		        }
		    });
	        PdfPTable table = new PdfPTable(4); //  columns.
	        table.setWidthPercentage(100); //Width 100%
	        table.setSpacingBefore(10f); //Space before table
	        table.setSpacingAfter(10f); //Space after table
	        
	        //Set Column widths
	        float[] columnWidths = {6f, 3f, 3f, 3f};
	        table.setWidths(columnWidths);
	        String[] headings1 = new String[4];
	        headings1[0] = "PARTY OFFICE";
	        headings1[1] = "TOTAL MEMBER";
	        headings1[2] = "PRESENT";
	        headings1[3] = "ABSENT";
	        
	        for(int i=0; i < 4; i++){
	        	PdfPCell cell = new PdfPCell(new Paragraph(headings1[i]));
	        	cell.setBorderColor(BaseColor.DARK_GRAY);
	        	cell.setBackgroundColor(BaseColor.GRAY);
		        cell.setPaddingLeft(10);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table.addCell(cell);
	        }
	      
	        List<Long> officeIdList = new ArrayList<Long>();
	        int absent = 0;  
	        boolean flag = true;
	        for(Object[] officeWiseTotalEmployee  : officeWiseTotalEmployeeList){
	        	flag = true;
		        PdfPCell officeNameCell=new PdfPCell(new Phrase(officeWiseTotalEmployee[1] != null ? officeWiseTotalEmployee[1].toString() : "" ));
			        officeNameCell.setBorderColor(BaseColor.DARK_GRAY);
			        officeNameCell.setBackgroundColor(BaseColor.WHITE);
			        officeNameCell.setPaddingLeft(10);
			        officeNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			        officeNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        table.addCell(officeNameCell);
		 	 
		        PdfPCell totalMemberCell=new PdfPCell(new Phrase(officeWiseTotalEmployee[2].toString()));
			        totalMemberCell.setBorderColor(BaseColor.DARK_GRAY);
			        totalMemberCell.setBackgroundColor(BaseColor.WHITE);
			        totalMemberCell.setPaddingLeft(10);
			        totalMemberCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			        totalMemberCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				 	table.addCell(totalMemberCell);
				for(Object[] officeWiseTotalAttendedEmployee : officeWiseTotalAttendedEmployeeList){
					if(((Long)officeWiseTotalEmployee[0]).equals((Long)officeWiseTotalAttendedEmployee[0])){
						PdfPCell presentCell=new PdfPCell(new Phrase(officeWiseTotalAttendedEmployee[2].toString()));
					 	   	presentCell.setBorderColor(BaseColor.DARK_GRAY);
					 	    presentCell.setBackgroundColor(BaseColor.WHITE);
					 	   	presentCell.setPaddingLeft(10);
					 	   	presentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					 	   	presentCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 	    table.addCell(presentCell);
					 	absent = Integer.parseInt(officeWiseTotalEmployee[2].toString()) - Integer.parseInt(officeWiseTotalAttendedEmployee[2].toString());   
				 	    PdfPCell absentCell=new PdfPCell(new Phrase(Integer.toString(absent)));
						 	absentCell.setBorderColor(BaseColor.DARK_GRAY);
						 	absentCell.setBackgroundColor(BaseColor.WHITE);
						 	absentCell.setPaddingLeft(10);
						 	absentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
						 	absentCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 	    table.addCell(absentCell);
				 	    flag = false;
				 	    break;
					}
				}
				if(flag){
		 	    PdfPCell presentCell=new PdfPCell(new Phrase("0"));
			 	   	presentCell.setBorderColor(BaseColor.DARK_GRAY);
			 	    presentCell.setBackgroundColor(BaseColor.WHITE);
			 	   	presentCell.setPaddingLeft(10);
			 	   	presentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 	   	presentCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			 	    table.addCell(presentCell);
			 	PdfPCell absentCell=new PdfPCell(new Phrase(officeWiseTotalEmployee[2].toString()));
				 	absentCell.setBorderColor(BaseColor.DARK_GRAY);
				 	absentCell.setBackgroundColor(BaseColor.WHITE);
				 	absentCell.setPaddingLeft(10);
				 	absentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 	absentCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			 	    table.addCell(absentCell);
				}
				officeIdList.add((Long)officeWiseTotalEmployee[0]);
		    }
	        
	        //Department's Employee Attendance Information
	        int length = officeWiseTotalEmployeeList.size();
	        length = length + 4;
	        
	        PdfPTable tbl = new PdfPTable(length);
	        tbl.setWidthPercentage(100); //Width 100%
	        tbl.setSpacingBefore(10f); //Space before table
	        tbl.setSpacingAfter(10f); //Space after table
	        String[] headings = new String[length];
	        headings[0] = "DEPT NAME";
	        headings[1] = "TOTAL MEMBER";
	        headings[2] = "PRESENT";
	        headings[3] = "ABSENT";
	        int index = 4;
	        for(Object[] officeWiseTotalEmployee : officeWiseTotalEmployeeList){
	        	headings[index] = officeWiseTotalEmployee[1].toString();
	        	index++;
	        }
	        for(int i=0; i < length; i++){
	        	PdfPCell cell = new PdfPCell(new Paragraph(headings[i]));
	        	cell.setBorderColor(BaseColor.DARK_GRAY);
	        	cell.setBackgroundColor(BaseColor.GRAY);
		        cell.setPaddingLeft(10);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        tbl.addCell(cell);
	        }
	        
	        List<DepartmentVO> departmentVoList = new ArrayList<DepartmentVO>();
	        buildDepartmentVoList(departmentVoList,departmentWiseThenOfficeWiseTotalAttendedEmployee);
	        Map<Long,OfficeMemberVO> officeIdMemberMap = new HashMap<Long, OfficeMemberVO>();
	        int absent1 = 0;  
	        boolean flag1 = true;
	        boolean flag2 = true;
	        for(Object[] departmentWiseTotalEmployee  : departmentWiseTotalEmployeeList){
	        	flag1 = true;
	        	flag2 = true;
	        	PdfPCell deptNameCell=new PdfPCell(new Phrase(departmentWiseTotalEmployee[1] != null ? departmentWiseTotalEmployee[1].toString() : "" ));
	        	deptNameCell.setBorderColor(BaseColor.DARK_GRAY);
	        	deptNameCell.setBackgroundColor(BaseColor.WHITE);
	        	deptNameCell.setPaddingLeft(10);
	        	deptNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	deptNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        tbl.addCell(deptNameCell);
	 	 
	        PdfPCell totalMemberCell=new PdfPCell(new Phrase(departmentWiseTotalEmployee[2].toString()));
		        totalMemberCell.setBorderColor(BaseColor.DARK_GRAY);
		        totalMemberCell.setBackgroundColor(BaseColor.WHITE);
		        totalMemberCell.setPaddingLeft(10);
		        totalMemberCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        totalMemberCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        tbl.addCell(totalMemberCell);
			 	@SuppressWarnings("unused")
				int count = 0;
			 	for(Object[] deptmenWiseTotalAttendedEmployee : departmenWiseTotalAttendedEmployee){
			 		count++;
					if(((Long)departmentWiseTotalEmployee[0]).equals((Long)deptmenWiseTotalAttendedEmployee[0])){
						
							PdfPCell presentCell=new PdfPCell(new Phrase(deptmenWiseTotalAttendedEmployee[2].toString()));
					 	   	presentCell.setBorderColor(BaseColor.DARK_GRAY);
					 	   	presentCell.setBackgroundColor(BaseColor.WHITE);
					 	   	presentCell.setPaddingLeft(10);
					 	   	presentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					 	   	presentCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 	    tbl.addCell(presentCell);
					 	    absent1 = Integer.parseInt(departmentWiseTotalEmployee[2].toString()) - Integer.parseInt(deptmenWiseTotalAttendedEmployee[2].toString());
					 		PdfPCell absentCell=new PdfPCell(new Phrase(Integer.toString(absent1)));
						 	absentCell.setBorderColor(BaseColor.DARK_GRAY);
						 	absentCell.setBackgroundColor(BaseColor.WHITE);
						 	absentCell.setPaddingLeft(10);
						 	absentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
						 	absentCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						 	tbl.addCell(absentCell);
						 	flag1 = false;
				 	    break;
					}
				}
			 	if(flag1){
			 	    PdfPCell presentCell=new PdfPCell(new Phrase("0"));
				 	   	presentCell.setBorderColor(BaseColor.DARK_GRAY);
				 	   	presentCell.setBackgroundColor(BaseColor.WHITE);
				 	   	presentCell.setPaddingLeft(10);
				 	   	presentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 	   	presentCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				 	    tbl.addCell(presentCell);
			 	    PdfPCell absentCell=new PdfPCell(new Phrase(departmentWiseTotalEmployee[2].toString()));
					 	absentCell.setBorderColor(BaseColor.DARK_GRAY);
					 	absentCell.setBackgroundColor(BaseColor.WHITE);
					 	absentCell.setPaddingLeft(10);
					 	absentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					 	absentCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 	tbl.addCell(absentCell);
					}
			 	officeIdMemberMap.clear();
			 	for(DepartmentVO departmentVO :departmentVoList){
			 		if(departmentVO.getDepartmentId().equals((Long)departmentWiseTotalEmployee[0])){
			 			List<OfficeMemberVO> officeMemberList = departmentVO.getOfficeMemberList();
			 			for(OfficeMemberVO memberVO : officeMemberList){
			 				officeIdMemberMap.put(memberVO.getOfficeId(), memberVO);
			 			}
			 			for(Long officeId : officeIdList){
			 				
			 				OfficeMemberVO officeMemberVO = officeIdMemberMap.get(officeId);
			 				if(officeMemberVO!=null){
			 					PdfPCell absentCell=new PdfPCell(new Phrase(officeMemberVO.getPresentMember().toString()));
							 	absentCell.setBorderColor(BaseColor.DARK_GRAY);
							 	absentCell.setBackgroundColor(BaseColor.WHITE);
							 	absentCell.setPaddingLeft(10);
							 	absentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
							 	absentCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							 	tbl.addCell(absentCell);
			 				}else{
			 					PdfPCell absentCell=new PdfPCell(new Phrase("0"));
							 	absentCell.setBorderColor(BaseColor.DARK_GRAY);
							 	absentCell.setBackgroundColor(BaseColor.WHITE);
							 	absentCell.setPaddingLeft(10);
							 	absentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
							 	absentCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							 	tbl.addCell(absentCell);
			 				}
			 			}
			 			flag2 = false;
			 			break; 
			 		}
			 	}
			 	if(flag2){
			 		for(int i=0 ; i < officeIdList.size(); i++){
			 		 PdfPCell presentCell=new PdfPCell(new Phrase("0"));
				 	   	presentCell.setBorderColor(BaseColor.DARK_GRAY);
				 	   	presentCell.setBackgroundColor(BaseColor.WHITE);
				 	   	presentCell.setPaddingLeft(10);
				 	   	presentCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 	   	presentCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				 	   	tbl.addCell(presentCell);
			 		}
			 	}
	        }
	        // attended employees details
	        PdfPTable table2 = new PdfPTable(5); //  columns.
	        table2.setWidthPercentage(100); //Width 100%
	        table2.setSpacingBefore(10f); //Space before table
	        table2.setSpacingAfter(10f); //Space after table
	        
	        //Set Column widths
	        float[] columnWidths1 = {4f, 4f, 4f, 3f, 2f};
	        table2.setWidths(columnWidths1);
	        String[] headings2 = new String[6];
	        headings2[0] = "PARTY OFFICE";
	        headings2[1] = "DEPT NAME";
	        headings2[2] = "EMPLOYEE NAME";
	        headings2[3] = "MOBILE NO";
	        headings2[4] = "ATTENDED TIME";
	        
	        for(int i=0; i < 5; i++){
	        	PdfPCell cell = new PdfPCell(new Paragraph(headings2[i]));
	        	cell.setBorderColor(BaseColor.DARK_GRAY);
	        	cell.setBackgroundColor(BaseColor.GRAY);
		        cell.setPaddingLeft(10);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table2.addCell(cell);
	        }
	        table2.setHeaderRows(1);
	        if(officeWiseTotalAttendedEmployeeDetailsList != null && officeWiseTotalAttendedEmployeeDetailsList.size()>0){
		        for(Object[] officeWiseTotalAttendedEmployeeDetails  : officeWiseTotalAttendedEmployeeDetailsList){
			        PdfPCell officeNameCell=new PdfPCell(new Phrase(officeWiseTotalAttendedEmployeeDetails[1] != null ? officeWiseTotalAttendedEmployeeDetails[1].toString() : "" ));
				        officeNameCell.setBorderColor(BaseColor.DARK_GRAY);
				        officeNameCell.setBackgroundColor(BaseColor.WHITE);
				        officeNameCell.setPaddingLeft(10);
				        officeNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				        officeNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				        table2.addCell(officeNameCell);
				     PdfPCell employeeDeptCell=new PdfPCell(new Phrase(officeWiseTotalAttendedEmployeeDetails[5] != null ? officeWiseTotalAttendedEmployeeDetails[5].toString() : ""));
				     	 if(officeWiseTotalAttendedEmployeeDetails[5].toString().endsWith("]")){
				     		employeeDeptCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				     	 }else{
				     		 employeeDeptCell.setBackgroundColor(BaseColor.WHITE);
				     	 }  
				     	 employeeDeptCell.setBorderColor(BaseColor.DARK_GRAY); 
					     employeeDeptCell.setPaddingLeft(10);
					     employeeDeptCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					     employeeDeptCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 	table2.addCell(employeeDeptCell);
			        PdfPCell employeeNameCell=new PdfPCell(new Phrase(officeWiseTotalAttendedEmployeeDetails[2] != null ? officeWiseTotalAttendedEmployeeDetails[2].toString().toUpperCase() : ""));
				        employeeNameCell.setBorderColor(BaseColor.DARK_GRAY);
				        employeeNameCell.setBackgroundColor(BaseColor.WHITE);
				        employeeNameCell.setPaddingLeft(10);
				        employeeNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				        employeeNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 	table2.addCell(employeeNameCell);
					PdfPCell employeeMobileCell=new PdfPCell(new Phrase(officeWiseTotalAttendedEmployeeDetails[3] != null ? officeWiseTotalAttendedEmployeeDetails[3].toString() : ""));
						employeeMobileCell.setBorderColor(BaseColor.DARK_GRAY);
						employeeMobileCell.setBackgroundColor(BaseColor.WHITE);
						employeeMobileCell.setPaddingLeft(10);
						employeeMobileCell.setHorizontalAlignment(Element.ALIGN_CENTER);
						employeeMobileCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 	table2.addCell(employeeMobileCell);
					PdfPCell attendedTimeCell=new PdfPCell(new Phrase(officeWiseTotalAttendedEmployeeDetails[4] != null ?  officeWiseTotalAttendedEmployeeDetails[4].toString().split(" ")[1].substring(0, 8) : ""));
						attendedTimeCell.setBorderColor(BaseColor.DARK_GRAY);
						attendedTimeCell.setBackgroundColor(BaseColor.WHITE);
						attendedTimeCell.setPaddingLeft(10);
						attendedTimeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
						attendedTimeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 	table2.addCell(attendedTimeCell);
			    }
	        }else{
			    	PdfPCell cell = new PdfPCell(new Phrase("No Employee Is Present"));
			        cell.setColspan(5);
			        cell.setBackgroundColor(BaseColor.WHITE);
			        cell.setPaddingLeft(10);
			        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        table2.addCell(cell); 
			}
	        
	       
	        // absent employees details
	        PdfPTable table3 = new PdfPTable(4); //  columns.
	        table3.setWidthPercentage(100); //Width 100%
	        table3.setSpacingBefore(10f); //Space before table
	        table3.setSpacingAfter(10f); //Space after table
	        
	        //Set Column widths
	        float[] columnWidths2 = {5f, 6f, 5F, 4f};
	        table3.setWidths(columnWidths2);
	        String[] headings3 = new String[4];
	        headings3[0] = "PARTY OFFICE";
	        headings3[1] = "DEPT NAME";
	        headings3[2] = "EMPLOYEE NAME";
	        headings3[3] = "MOBILE NO";
	        //cell.setBackgroundColor(new BaseColor(255, 0, 0));
	        for(int i=0; i < 4; i++){
	        	PdfPCell cell = new PdfPCell(new Paragraph(headings3[i]));
	        	cell.setBorderColor(BaseColor.DARK_GRAY);
	        	cell.setBackgroundColor(BaseColor.GRAY);
		        cell.setPaddingLeft(10);
		        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table3.addCell(cell);
	        }
	        table3.setHeaderRows(1);
	        
	        if(officeWiseTotalNonAttendedEmployeeDetailsList != null && officeWiseTotalNonAttendedEmployeeDetailsList.size() > 0){
		        for(Object[] officeWiseTotalNonAttendedEmployeeDetails  : officeWiseTotalNonAttendedEmployeeDetailsList){
			        PdfPCell officeNameCell=new PdfPCell(new Phrase(officeWiseTotalNonAttendedEmployeeDetails[1] != null ? officeWiseTotalNonAttendedEmployeeDetails[1].toString() : "" ));
				        officeNameCell.setBorderColor(BaseColor.DARK_GRAY);
				        officeNameCell.setBackgroundColor(BaseColor.WHITE);
				        officeNameCell.setPaddingLeft(10);
				        officeNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				        officeNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				        table3.addCell(officeNameCell);
				     PdfPCell employeeDeptCell=new PdfPCell(new Phrase(officeWiseTotalNonAttendedEmployeeDetails[4] != null ? officeWiseTotalNonAttendedEmployeeDetails[4].toString() : ""));
					     employeeDeptCell.setBorderColor(BaseColor.DARK_GRAY);
					     employeeDeptCell.setBackgroundColor(BaseColor.WHITE);
					     employeeDeptCell.setPaddingLeft(10);
					     employeeDeptCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					     employeeDeptCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 	table3.addCell(employeeDeptCell);
			        PdfPCell employeeNameCell=new PdfPCell(new Phrase(officeWiseTotalNonAttendedEmployeeDetails[2] != null ? officeWiseTotalNonAttendedEmployeeDetails[2].toString().toUpperCase() : ""));
				        employeeNameCell.setBorderColor(BaseColor.DARK_GRAY);
				        employeeNameCell.setBackgroundColor(BaseColor.WHITE);
				        employeeNameCell.setPaddingLeft(10);
				        employeeNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				        employeeNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 	table3.addCell(employeeNameCell);
					PdfPCell employeeMobileCell=new PdfPCell(new Phrase(officeWiseTotalNonAttendedEmployeeDetails[3] != null ? officeWiseTotalNonAttendedEmployeeDetails[3].toString() : ""));
						employeeMobileCell.setBorderColor(BaseColor.DARK_GRAY);
						employeeMobileCell.setBackgroundColor(BaseColor.WHITE);
						employeeMobileCell.setPaddingLeft(10);
						employeeMobileCell.setHorizontalAlignment(Element.ALIGN_CENTER);
						employeeMobileCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 	table3.addCell(employeeMobileCell);
			    }
	        }
	        else{
	        	PdfPCell cell = new PdfPCell(new Phrase("No Employee Is Absent"));
		        cell.setColspan(4);
		        cell.setBackgroundColor(BaseColor.WHITE);
		        cell.setPaddingLeft(10);
		        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        table3.addCell(cell); 
	        }
	        document.open();
	        
	        Font f = new Font(FontFamily.TIMES_ROMAN, 16.0f, Font.BOLD, new BaseColor(0,0,128));
		    Chunk c = new Chunk("EMPLOYEES ATTENDANCE", f);
	        Paragraph paragraph5=new Paragraph(c);
	        //paragraph.add("PARTY OFFICE EMPLOYEES ATTENDANCE INFORMATION::DATE:"+dt);
	        paragraph5.setAlignment(Element.ALIGN_CENTER);
	        paragraph5.add(Chunk.NEWLINE);
	        
	        document.add(paragraph5);  
	        
	        f = new Font(FontFamily.TIMES_ROMAN, 14.0f, Font.BOLD, new BaseColor(0,0,128));
		    c = new Chunk("PARTY OFFICE EMPLOYEES ATTENDANCE INFORMATION", f);
		    //c.setBackground(BaseColor.RED);
	        Paragraph paragraph=new Paragraph(c);
	        paragraph.setAlignment(Element.ALIGN_CENTER);
	        paragraph.add(Chunk.NEWLINE);
	        //paragraph.add("PARTY OFFICE EMPLOYEES ATTENDANCE INFORMATION::DATE:"+dt);
	        
	        f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD, new BaseColor(0,0,0));
		    c = new Chunk("      DATE:"+dt, f);
		    Paragraph paragraph4=new Paragraph(c);
		    paragraph4.setAlignment(Element.ALIGN_CENTER);
		    paragraph4.add(Chunk.NEWLINE);
		    
	        
	        /*paragraph.setAlignment(Element.ALIGN_CENTER);
	        Paragraph paragraph4=new Paragraph();
	        paragraph4.add("      DATE:"+dt);
	        paragraph4.setAlignment(Element.ALIGN_CENTER);
	        paragraph4.add(Chunk.NEWLINE);*/
	        
	        document.add(paragraph);
	    	document.add(paragraph4);
	    	document.add(table);
	    	
	    	f = new Font(FontFamily.TIMES_ROMAN, 14.0f, Font.BOLD, new BaseColor(0,0,128));
	    	c = new Chunk("DEPARTMENT'S EMPLOYEE ATTENDANCE INFORMATION", f);
	    	//c.setBackground(BaseColor.RED);
	        Paragraph paragraph1=new Paragraph(c);
	        //paragraph1.add("DEPARTMENT'S EMPLOYEE ATTENDANCE INFORMATION");
	        paragraph1.setAlignment(Element.ALIGN_CENTER);
	        paragraph1.add(Chunk.NEWLINE);
	        
	        document.add(paragraph1);  
	    	document.add(tbl);
	    	
	    	f = new Font(FontFamily.TIMES_ROMAN, 14.0f, Font.BOLD, new BaseColor(0,0,128));
	    	if(area.equals("dept")){
	    		c = new Chunk("DEPARTMENT WISE ATTENDED EMPLOYEE'S DETAILS", f);
	    	}
	    	else if(area.equals("office"))
	    	{
	    		c = new Chunk("OFFICE WISE ATTENDED EMPLOYEE'S DETAILS", f);
	    	}
	    	
	    	//c.setBackground(BaseColor.RED);
	        Paragraph paragraph2=new Paragraph(c);
	        //paragraph2.add("OFFICE WISE ATTENDED EMPLOYEE'S DETAILS");
	        paragraph2.setAlignment(Element.ALIGN_CENTER);
	        paragraph2.add(Chunk.NEWLINE);
	        
	        document.add(paragraph2);
	    	document.add(table2);
	    	
	    	f = new Font(FontFamily.TIMES_ROMAN, 14.0f, Font.BOLD, new BaseColor(0,0,128));
	    	if(area.equals("dept")){
	    		c = new Chunk("DEPARTMENT WISE NOT ATTENDED EMPLOYEE'S DETAILS", f);
	    	}
	    	else if(area.equals("office"))
	    	{
	    		c = new Chunk("OFFICE WISE NOT ATTENDED EMPLOYEE'S DETAILS", f);   
	    	}
	    	//c.setBackground(BaseColor.RED);
	        Paragraph paragraph3=new Paragraph(c);
	        //paragraph3.add("OFFICE WISE ABSENT EMPLOYEE'S DETAILS");
	        paragraph3.setAlignment(Element.ALIGN_CENTER);
	        paragraph3.add(Chunk.NEWLINE);
	        document.add(paragraph3);
	    	document.add(table3);
	        
	    	document.addAuthor("Swadhin Lenka");
	    	document.addCreationDate();
	    	document.addCreator("ITGRIDS PVT.LTD");
	    	document.addTitle("Party Office Employees Attendance Information");
	    	document.addSubject("TEST");
	       	        
	        document.close();
	        writer.close();
	        System.out.println("PDF SUCCESSFULLY CREATED.");
			
		}catch(Exception e){
			LOG.error("Exception Occured in generatePdfReport() Method",e);
		}
		
	}
	public void  buildDepartmentVoList(List<DepartmentVO> departmentVoList,List<Object[]> DepartmentWiseThenOfficeWiseTotalAttendedEmployee){
		try{
			DepartmentVO departmentVO = null;
			OfficeMemberVO officeMemberVO = null;
			if(DepartmentWiseThenOfficeWiseTotalAttendedEmployee!=null && DepartmentWiseThenOfficeWiseTotalAttendedEmployee.size()>0){
				departmentVO = new DepartmentVO();
				officeMemberVO = new OfficeMemberVO();
				Long newDepartmentId = Long.valueOf(DepartmentWiseThenOfficeWiseTotalAttendedEmployee.get(0)[0].toString());
				for(Object[] departmentDetails : DepartmentWiseThenOfficeWiseTotalAttendedEmployee){
					if(!(newDepartmentId.equals(Long.valueOf(departmentDetails[0].toString())))){
						newDepartmentId = Long.valueOf(departmentDetails[0].toString());
						departmentVoList.add(departmentVO);
						departmentVO = new DepartmentVO();
						departmentVO.setDepartmentId((Long)departmentDetails[0]);
						departmentVO.setDepartmentName(departmentDetails[1] != null ? departmentDetails[1].toString() : "");
						officeMemberVO = new OfficeMemberVO();
						officeMemberVO.setOfficeId((Long)departmentDetails[2]);
						officeMemberVO.setOfficeName(departmentDetails[3] != null ? departmentDetails[3].toString() : "");
						officeMemberVO.setPresentMember((Long)departmentDetails[4]);
						departmentVO.getOfficeMemberList().add(officeMemberVO);
					}else{
						departmentVO.setDepartmentId((Long)departmentDetails[0]);
						departmentVO.setDepartmentName(departmentDetails[1] != null ? departmentDetails[1].toString() : "");
						officeMemberVO = new OfficeMemberVO();
						officeMemberVO.setOfficeId((Long)departmentDetails[2]);
						officeMemberVO.setOfficeName(departmentDetails[3] != null ? departmentDetails[3].toString() : "");
						officeMemberVO.setPresentMember((Long)departmentDetails[4]);
						departmentVO.getOfficeMemberList().add(officeMemberVO);
					}
				}
				departmentVoList.add(departmentVO);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public ResultStatus sendEmailWithPdfAttachment(String area, EmailAttributesVO emailAttributesVO,Long officeId){
		ResultStatus resultStatus = new ResultStatus();
		try{
			DateUtilService dateUtilService = new DateUtilService();
			
			String host = IConstants.DEFAULT_MAIL_SERVER;
			Session session = mailService.getNewSessionObject(host);
			if(session == null)
			{
				LOG.error("MimeMessage Object is Not Created");  
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
			//sending mail to client  
			try{				
			MimeMessage message = new MimeMessage(session);    
			
			message.setFrom(new InternetAddress(IConstants.EMAIL_USERNAME));  
			if(area.equalsIgnoreCase("office")){
				if(officeId != null && officeId.longValue() == 1L){
					message.addRecipient(Message.RecipientType.TO, new InternetAddress("accounts@telugudesam.org")); 
					message.addRecipient(Message.RecipientType.TO, new InternetAddress("a.dakavaram@itgrids.com"));  
					message.addRecipient(Message.RecipientType.TO, new InternetAddress("swadhin.lenka@itgrids.com"));    
				}else{
					message.addRecipient(Message.RecipientType.TO, new InternetAddress("ramana.ayala@gmail.com")); 
					message.addRecipient(Message.RecipientType.TO, new InternetAddress("a.dakavaram@itgrids.com"));  
					message.addRecipient(Message.RecipientType.TO, new InternetAddress("swadhin.lenka@itgrids.com"));    
				}
			}else if(area.equalsIgnoreCase("dept")){
				List<Object[]> emailList = userEmailDAO.getEmailList(emailAttributesVO.getEmailId());
				for(Object[] emailArr : emailList){ 
					if(emailArr[1]==null)
						continue; 
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailArr[1].toString()));  
				}
			}
			
			message.setHeader("Return-Path", IConstants.EMAIL_USERNAME);
			message.setSentDate(dateUtilService.getCurrentDateAndTime());
			message.setSubject(emailAttributesVO.getSubject());
			Multipart multipart = new MimeMultipart();
    		 
        	BodyPart htmlPart = new MimeBodyPart();
        	StringBuilder msgText= new StringBuilder();    
        	if(area.equalsIgnoreCase("office")){
        		msgText.append("Good Morning Sir,<br><br> Please find the attached  pdf document for Party Office Employee Attendance details for the date:"+emailAttributesVO.getDate()) ;
        	}else if(area.equalsIgnoreCase("dept")){
        		msgText.append("Good Morning Sir,<br><br> Please find the attached  pdf document for Party Office Employee Attendance details department wise for the date:"+emailAttributesVO.getDate()) ;
        	}
        	
        	msgText.append("<br><br>Thanks");
        	msgText.append("<br>IT TEAM");
        	htmlPart.setContent(msgText.toString(),"text/html");
        	multipart.addBodyPart(htmlPart);
        	String pdfFileName = emailAttributesVO.getFileName();
        	String staticPath = IConstants.STATIC_CONTENT_FOLDER_URL + "reports" + "/" + emailAttributesVO.getDate();
    		String pdfPath = staticPath + "/"+ pdfFileName;
    		DataSource source = new FileDataSource(pdfPath);
        	BodyPart  attachment  = new MimeBodyPart();
        	attachment.setDataHandler(new DataHandler(source));
        	attachment.setFileName(emailAttributesVO.getFileName());
        	multipart.addBodyPart(attachment);
        	 
        	message.setContent(multipart);
        	 
        	if(host.equalsIgnoreCase(IConstants.LOCALHOST))  
        	{
			   	Transport transport = session.getTransport("smtp");
		        transport.connect(IConstants.HOST,IConstants.EMAIL_USERNAME,IConstants.EMAIL_PASSWORD);
		        transport.sendMessage(message, message.getAllRecipients());
		        transport.close();
			}
			else{
			    	Transport.send(message);
			}
        	resultStatus.setMessage(IConstants.SUCCESS);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS); 
			}catch(Exception e){
				LOG.error("Exception in sending mail : ",e);
				resultStatus.setMessage(IConstants.FAILURE);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			}
			return resultStatus;
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			resultStatus.setExceptionMsg(e.getMessage());
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
    
	
	public ResultStatus pushDataToPartyMeetingStatusTable(){
		ResultStatus rs = null;
		try{
			
		    rs = coreDashboardPartyMeetingService.insertDataInToPartyMeetingStatusTable();
			
		}catch (Exception e) {
			Log.error("Exception Occurred in pushDataToPartyMeetingStatusTable() of scheduler Service", e);
		}
		return rs;
	}
	public ResultStatus pushSourceOfRegistrationIntoIntermediateTable(){  
		   
		final ResultStatus rs = new ResultStatus();
		final DateUtilService dateUtilService = new DateUtilService();
				
		try {  
					
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(TransactionStatus arg0) {
					List<DataSourceVO> dataSourceVOs = new ArrayList<DataSourceVO>();
					getSourceOfRegDtls(dataSourceVOs);
					int deletedRecords = tdpCadreDataSourceCountInfoTempDAO.deleteAllRecords();
				    int count = tdpCadreDataSourceCountInfoTempDAO.setPrimaryKeyAutoIncrementToOne();
				    
				    
				    Date currentTime = dateUtilService.getCurrentDateAndTime();
				    
				    
				    
				    
					rs.setResultCode(1);
					rs.setMessage("success");
				}
			});
					
		} catch (Exception e) {
			LOG.error("Exception raised at CadreRegistrationServiceNew", e);
			rs.setResultCode(0);
			rs.setMessage("failure");
		}
		return rs;  
	}
	public void getSourceOfRegDtls(List<DataSourceVO> cadreRegistratedCountVOs){
		try{
			DataSourceVO dataSourceVO = null;  
			CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
			Map<Long,Map<String,Long>> locIdAndSourceTypeAndCountMap = new HashMap<Long,Map<String,Long>>();
			Map<String,Long> sourceTypeAndCountMap = new HashMap<String,Long>();
			//get dist wise total count
			List<Object[]> distWiseCountList = tdpCadreEnrollmentYearDAO.getDistWiseCountList();
			if(distWiseCountList != null && distWiseCountList.size() > 0){
				for(Object[] param : distWiseCountList){
					sourceTypeAndCountMap = locIdAndSourceTypeAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(sourceTypeAndCountMap != null){
						sourceTypeAndCountMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
					}else{
						sourceTypeAndCountMap = new HashMap<String,Long>();
						sourceTypeAndCountMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
						locIdAndSourceTypeAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), sourceTypeAndCountMap);
					} 
				}
			}
			
			//get dist wise renewal count
			List<Object[]> distWiseRenewCountList = tdpCadreEnrollmentYearDAO.getDistWiseRenewCountList();
			Map<Long,Map<String,Long>> locIdAndSourceTypeAndRenewCountMap = new HashMap<Long,Map<String,Long>>();
			Map<String,Long> sourceTypeAndRenewCountMap = new HashMap<String,Long>();
			if(distWiseRenewCountList != null && distWiseRenewCountList.size() > 0){
				for(Object[] param : distWiseRenewCountList){
					sourceTypeAndRenewCountMap = locIdAndSourceTypeAndRenewCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(sourceTypeAndRenewCountMap != null){
						sourceTypeAndRenewCountMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
					}else{
						sourceTypeAndRenewCountMap = new HashMap<String,Long>();
						sourceTypeAndRenewCountMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
						locIdAndSourceTypeAndRenewCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), sourceTypeAndRenewCountMap);
					} 
				}
			} 
			
			if(locIdAndSourceTypeAndCountMap.size() > 0){
				for(Entry<Long, Map<String, Long>> enter1 : locIdAndSourceTypeAndCountMap.entrySet()){
					
					sourceTypeAndCountMap = enter1.getValue();
					for(Entry<String,Long> entry2 : sourceTypeAndCountMap.entrySet()){
						dataSourceVO = new DataSourceVO();
						dataSourceVO.setRegionScopeId(3l);
						dataSourceVO.setLocationValue(enter1.getKey());
						dataSourceVO.setTotalCount(entry2.getValue());
						dataSourceVO.setRenewalCount(locIdAndSourceTypeAndRenewCountMap.get(enter1.getKey()) != null ? locIdAndSourceTypeAndRenewCountMap.get(enter1.getKey()).get(entry2.getKey()) != null ? locIdAndSourceTypeAndRenewCountMap.get(entry2.getKey()).get(enter1.getKey()) : 0l  : 0l);
						dataSourceVO.setNewCount(dataSourceVO.getTotalCount() - dataSourceVO.getRenewalCount());
					}  
					
				}
			}
			//get const wise total count
			List<Object[]> constWiseCountList = tdpCadreEnrollmentYearDAO.getConstWiseCountList();
			Map<Long,Map<String,Long>> constIdAndSourceTypeAndCountMap = new HashMap<Long,Map<String,Long>>();
			if(constWiseCountList != null && constWiseCountList.size() > 0){
				for(Object[] param : constWiseCountList){
					sourceTypeAndRenewCountMap = constIdAndSourceTypeAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(sourceTypeAndRenewCountMap != null){
						sourceTypeAndRenewCountMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
					}else{
						sourceTypeAndRenewCountMap = new HashMap<String,Long>();
						sourceTypeAndRenewCountMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
						constIdAndSourceTypeAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), sourceTypeAndRenewCountMap);
					}
				}
			}
			//get const wise renew count
			List<Object[]> constWiseRenewCountList = tdpCadreEnrollmentYearDAO.getConstWiseRenewCountList();
			Map<Long,Map<String,Long>> constIdAndSourceTypeAndRenewCountMap = new HashMap<Long,Map<String,Long>>();
			if(constWiseRenewCountList != null && constWiseRenewCountList.size() > 0){
				for(Object[] param : constWiseRenewCountList){
					sourceTypeAndRenewCountMap = constIdAndSourceTypeAndRenewCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(sourceTypeAndRenewCountMap != null){
						sourceTypeAndRenewCountMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
					}else{
						sourceTypeAndRenewCountMap = new HashMap<String,Long>();
						sourceTypeAndRenewCountMap.put(commonMethodsUtilService.getStringValueForObject(param[1]), commonMethodsUtilService.getLongValueForObject(param[2]));
						constIdAndSourceTypeAndRenewCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), sourceTypeAndRenewCountMap);
					}  
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised at getSourceOfRegDtls in SchedulerService{}", e);
		}
	}
	/**
	  * @return ResultStatus
	  * @author Santosh 
	  * @Description :This Service Method is user for pushing training camp data into intermediate table. 
	  * @since 11-JULY-2017
	  */
   public ResultStatus pushTrainginCampDataLocationWiseByCommitteeLevel(){
		ResultStatus rs = new ResultStatus();
		try {
			System.out.println("Trigger Occured in training program scheduler");
			int count = trainingCampDetailsInfoDAO.pushTrainginCampDataLocationWiseByCommitteeLevel();
			rs.setMessage("Success");
		} catch (Exception e) {
			LOG.error("Exception raised at pushTrainginCampDataLocationWiseByCommitteeLevel() in CadreRegistrationServiceNew Class", e);
			rs.setMessage("Failure");
		}
		return rs;    
	 }
   
   public ResultStatus sendSmsDetailsOfAlert(){
	   ResultStatus rs = new ResultStatus();
	   try {
		
		   ResultStatus result = alertService.getSmsTdpCadreDetails();
		   if(result !=null && result.getResultCode() ==0){
			   rs.setExceptionMsg("success");
		   }else{
			   rs.setExceptionMsg("failure");
		   }
		  
		} catch (Exception e) {
			LOG.error("Exception raised at sendSmsDetailsOfAlert() in SchedulerService Class", e);
			rs.setMessage("Failure");
		}
	    return rs;
   }
}
