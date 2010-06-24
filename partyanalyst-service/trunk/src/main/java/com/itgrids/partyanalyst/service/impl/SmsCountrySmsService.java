package com.itgrids.partyanalyst.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.ISmsHistoryDAO;
import com.itgrids.partyanalyst.dao.ISmsModuleDAO;
import com.itgrids.partyanalyst.dao.ISmsTrackDAO;
import com.itgrids.partyanalyst.dao.hibernate.SmsHistoryDAO;
import com.itgrids.partyanalyst.keys.PropertyKeys;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.SmsHistory;
import com.itgrids.partyanalyst.model.SmsModule;
import com.itgrids.partyanalyst.model.SmsTrack;
import com.itgrids.partyanalyst.service.IPartyAnalystPropertyService;
import com.itgrids.partyanalyst.service.ISmsService;

/*
 * Username: dakavaram
 * Password: 19716044
 */
public class SmsCountrySmsService implements ISmsService {

	private static final Logger log = Logger
			.getLogger(SmsCountrySmsService.class);

	private IPartyAnalystPropertyService propertyService;
	private ISmsTrackDAO smsTrackDAO;
	private ISmsModuleDAO smsModuleDAO;
	private ISmsHistoryDAO smsHistoryDAO;
	private IRegistrationDAO registrationDAO;
	private TransactionTemplate transactionTemplate = null;
	
	public ISmsHistoryDAO getSmsHistoryDAO() {
		return smsHistoryDAO;
	}

	public void setSmsHistoryDAO(ISmsHistoryDAO smsHistoryDAO) {
		this.smsHistoryDAO = smsHistoryDAO;
	}

	public ISmsModuleDAO getSmsModuleDAO() {
		return smsModuleDAO;
	}

	public void setSmsModuleDAO(ISmsModuleDAO smsModuleDAO) {
		this.smsModuleDAO = smsModuleDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}

	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}

	public ISmsTrackDAO getSmsTrackDAO() {
		return smsTrackDAO;
	}

	public void setSmsTrackDAO(ISmsTrackDAO smsTrackDAO) {
		this.smsTrackDAO = smsTrackDAO;
	}

	public IPartyAnalystPropertyService getPropertyService() {
		return propertyService;
	}

	public void setPropertyService(IPartyAnalystPropertyService propertyService) {
		this.propertyService = propertyService;
	}
	
	public void sendSms(String message, boolean isEnglish,Long userId,String moduleName,
			String... phoneNumbers) {
		HttpClient client = null;
		PostMethod post = null;
		saveSmsData(message,userId,moduleName,phoneNumbers);
		client = new HttpClient(new MultiThreadedHttpConnectionManager());

		/* SETUP PROXY */
		/*if (propertyService.getProperty(PropertyKeys.SERVER_PROXY_HOST) != null && propertyService.getProperty(PropertyKeys.SERVER_PROXY_HOST).trim().length() > 0) {
			int port = 8080;
			try {
				port = Integer.parseInt(propertyService
						.getProperty(PropertyKeys.SERVER_PROXY_PORT));
			} catch (NumberFormatException nfe) {
				log.error(nfe);
			}
			client.getHostConfiguration().setProxy(propertyService.getProperty(PropertyKeys.SERVER_PROXY_HOST), port);
		}*/

		/*client.getHttpConnectionManager().getParams().setConnectionTimeout(
				Integer.parseInt(propertyService.getProperty(
								PropertyKeys.SMS_SMSCOUNTRY_CONNECTION_TIMEOUT,"30000")));*/
		client.getHttpConnectionManager().getParams().setConnectionTimeout(
				Integer.parseInt("30000"));

		/*post = new PostMethod(propertyService
				.getProperty(PropertyKeys.SMS_SMSCOUNTRY_SERVICE_URL));*/
		post = new PostMethod("http://www.smscountry.com/SMSCwebservice.asp");
		// give all in string
		/*post.addParameter("User", propertyService
				.getProperty(PropertyKeys.SMS_SMSCOUNTRY_USER));
		post.addParameter("passwd", propertyService
				.getProperty(PropertyKeys.SMS_SMSCOUNTRY_PASSWORD));*/
		
		post.addParameter("User", "dakavaram");
		post.addParameter("passwd", "nellore");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < phoneNumbers.length; i++) {
			sb.append(phoneNumbers[i]);
			System.out.println("Phone Numbers--->"+phoneNumbers[i]);
			if (i < (phoneNumbers.length-1))
				sb.append(",");
		}
		
	/*	System.out.println("Using "+propertyService
				.getProperty(PropertyKeys.SMS_SMSCOUNTRY_USER)+propertyService
				.getProperty(PropertyKeys.SMS_SMSCOUNTRY_PASSWORD)+" for "+sb);*/
		post.addParameter("mobilenumber", sb.toString());
		post.addParameter("message", message);/*
		post.addParameter("sid", propertyService
				.getProperty(PropertyKeys.SMS_SMSCOUNTRY_SID));*/
		post.addParameter("sid", "SMSCountry");
		post.addParameter("mtype", isEnglish ? "N" : "OL");
		/*post.addParameter("DR", propertyService
				.getProperty(PropertyKeys.SMS_SMSCOUNTRY_DR));*/
		post.addParameter("DR", "YES");

		/* PUSH the URL */
		try {
			int statusCode = client.executeMethod(post);
			if(log.isInfoEnabled()){
				log.info(post.getStatusLine().toString());
				System.out.println(post.getStatusLine().toString()+"***"+statusCode+"*****"+post.getQueryString());
			}
			if (statusCode != HttpStatus.SC_OK) {
				log.error("SmsCountrySmsService.sendSMS failed: "
						+ post.getStatusLine());
				System.out.println("SmsCountrySmsService.sendSMS failed: "
						+ post.getStatusLine());
			}
			if(log.isInfoEnabled()){
				log.info(post.getResponseBodyAsString());
				System.out.println(post.getResponseBodyAsString());
			}
		} catch (Exception e) {
			log.error(e);
			System.out.println(e);
		} finally {
			post.releaseConnection();
		}
	}
	
	/**
	 * This method Tracks all the Sms's sent by the user and stores the data into corresponding tables.
	 * @param message
	 * @param userId
	 * @param moduleName
	 * @param phoneNumbers
	 */
	public void saveSmsData(final String message,final Long userId,final String moduleName,final String... phoneNumbers){
		try{
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {	
					int smsTrackCount = smsTrackDAO.getAll().size();
					if(smsTrackCount==0){			
						SmsTrack track = new SmsTrack();
						track.setRegistration(registrationDAO.get(userId));
						track.setRenewalDate(getCurrentDate());
						track.setRenewalSmsCount(10000l);
						track = smsTrackDAO.save(track);						
					}
				}
				});
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {	
					SmsHistory history = new SmsHistory();
					Long moduleId=0l;
					int counter = 1;
					Long latestSmsTrackIdForUser = 0l;
					for (int i = 0; i < phoneNumbers.length; i++) {
						history.setMobileNumber(phoneNumbers[i]);
						history.setSmsContent(message);							
						history.setSentDate(getCurrentDate());
						
						
						List<SmsTrack> result = smsTrackDAO.findLatestRenewalDate(7l);
						for(SmsTrack latestDate : result){
							if(counter == 1){
								latestSmsTrackIdForUser = latestDate.getSmsTrackId();
							}
						}						
						history.setSmsTrack(smsTrackDAO.get(latestSmsTrackIdForUser));
						for(SmsModule module : smsModuleDAO.getAll()){
							if(module.getModuleName().equalsIgnoreCase(moduleName)){
								moduleId = module.getSmsModuleId();
							}
						}
						history.setSmsModule(smsModuleDAO.get(moduleId));
						history = smsHistoryDAO.save(history);
					}
				}
			});
		}catch(Exception e){
			e.printStackTrace();
			log.debug("Exception Raised--->"+e);
		}		
	}
	
	/**
	 * This method returns Current Date in yyyy-MM-dd hh:mm:ss Format.
	 * 
	 * @return
	 */
	public String getCurrentDate(){
		String currentDate="";
		try{
			java.util.Date updatedDate = new java.util.Date();
			String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			currentDate = sdf.format(updatedDate); 
			return currentDate;
		}catch(Exception e){
			e.printStackTrace();
		}
		return currentDate;
		
	}
}
