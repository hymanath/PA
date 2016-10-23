package com.itgrids.partyanalyst.service.impl;

import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ISmsHistoryDAO;
import com.itgrids.partyanalyst.dao.ISmsModuleDAO;
import com.itgrids.partyanalyst.dao.ISmsTrackDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SmsTrackVO;
import com.itgrids.partyanalyst.model.SmsHistory;
import com.itgrids.partyanalyst.model.SmsModule;
import com.itgrids.partyanalyst.model.SmsTrack;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.service.IPartyAnalystPropertyService;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class SmsCountrySmsService implements ISmsService {

	private static final Logger log = Logger
			.getLogger(SmsCountrySmsService.class);

	private IPartyAnalystPropertyService propertyService;
	private ISmsTrackDAO smsTrackDAO;
	private ISmsModuleDAO smsModuleDAO;
	private ISmsHistoryDAO smsHistoryDAO;
	private TransactionTemplate transactionTemplate = null;
	private DateUtilService dateUtilService = new DateUtilService();
	private IUserDAO userDAO;
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

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
	
	/**
	 *  This Method will Send SMS to given Mobile Numbers
	 *  @param String message
	 *  @param boolean isEnglish
	 *  @param Long userId
	 *  @param String moduleName
	 *  @param String[] phoneNumbers
	 *  @author Kamalakar Dandu
	 *  @return Long resultCode
	 *  
	 */
	
	public Long sendSms(String message, boolean isEnglish,Long userId,String moduleName,
			String... phoneNumbers) 
	{
		Long smsResultCode;
		HttpClient client = null;
		PostMethod post = null;
		
		Long count = getRemainingSmsLeftForUser(userId) - phoneNumbers.length;
		
		if(count < 0)
			return (long)ResultCodeMapper.FAILURE;
		
		List<Object[]> smsDetails = smsTrackDAO.getUserSmsDetails(userId);
		
		if(smsDetails == null || smsDetails.size() == 0)
			return (long)ResultCodeMapper.FAILURE;
		
		smsResultCode = saveSmsData(message,userId,moduleName,phoneNumbers);
		
		client = new HttpClient(new MultiThreadedHttpConnectionManager());

		client.getHttpConnectionManager().getParams().setConnectionTimeout(
				Integer.parseInt("30000"));

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < phoneNumbers.length; i++) {
			sb.append("91");
			sb.append(phoneNumbers[i]);
			if (i < (phoneNumbers.length-1))
				sb.append(",");
		}
			
	    log.debug("Mobile Nos :" + sb.toString());
	    
	    post = new PostMethod("http://sms.partyanalyst.com/WebserviceSMS.aspx");
		
		post.addParameter("User", smsDetails.get(0)[0].toString());
		post.addParameter("passwd", smsDetails.get(0)[1].toString());
		post.addParameter("sid", smsDetails.get(0)[2].toString());
		
	    post.addParameter("mobilenumber", sb.toString());
		post.addParameter("message", message);
		post.addParameter("mtype", isEnglish ? "N" : "OL");
		post.addParameter("DR", "Y");
		
		log.debug(" Query String :" + post.getQueryString());

		/* PUSH the URL */
		try 
		{
			int statusCode = client.executeMethod(post);
			
			log.debug(post.getStatusLine().toString()+"***"+statusCode+"*****"+post.getQueryString());
			
			if (statusCode != HttpStatus.SC_OK) {
				log.error("SmsCountrySmsService.sendSMS failed: "+ post.getStatusLine());
				log.debug("SmsCountrySmsService.sendSMS failed: "+ post.getStatusLine());
			}
			else
				smsTrackDAO.updateRemainingSmsLeftForUser(userId,count);
			
			log.debug(post.getResponseBodyAsString());
		}catch (Exception e) {
				log.error(e);
				System.out.println(e);
		} finally {
				post.releaseConnection();
		}
		
		
		return smsResultCode;	
	}
	
	/**
	 *  This Method will Send SMS to given Mobile Numbers From Admin
	 *  @param String message
	 *  @param boolean isEnglish
	 *  @param String[] phoneNumbers
	 *  @author Kamalakar Dandu
	 *  @return Long ResultStatus
	 *  
	 */
	public ResultStatus sendSmsFromAdmin(String message, boolean isEnglish,String... phoneNumbers)
	{
		ResultStatus resultStatus = new ResultStatus();
		try
		{
			if(!IConstants.DEPLOYED_HOST.equalsIgnoreCase("tdpserver"))
				return resultStatus;
			
			HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
			client.getHttpConnectionManager().getParams().setConnectionTimeout(
					Integer.parseInt("30000"));

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < phoneNumbers.length; i++) {
				if(phoneNumbers[i].toString().trim().length()>0)
				{
					sb.append("91");
					sb.append(phoneNumbers[i]);
					if (i < (phoneNumbers.length-1))
						sb.append(",");
				}
			}
			
			//PostMethod post = new PostMethod("http://sms.partyanalyst.com/WebserviceSMS.aspx");
			PostMethod post = new PostMethod("http://smscountry.com/SMSCwebservice_Bulk.aspx");
			
			post.addParameter("User",IConstants.ADMIN_USERNAME_FOR_SMS);
			post.addParameter("passwd",IConstants.ADMIN_PASSWORD_FOR_SMS);
			//post.addParameter("sid",IConstants.ADMIN_SENDERID_FOR_SMS);
		    post.addParameter("mobilenumber", sb.toString());
			post.addParameter("message", message);
			post.addParameter("mtype", isEnglish ? "N" : "OL");
			post.addParameter("DR", "Y");
			
			/* PUSH the URL */
			try 
			{
				int statusCode = client.executeMethod(post);
				
				if (statusCode != HttpStatus.SC_OK) {
					log.error("SmsCountrySmsService.sendSMS failed: "+ post.getStatusLine());
				}
				else
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);

			}catch (Exception e) {
					log.error(e);
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					resultStatus.setExceptionEncountered(e);
			} finally {
					post.releaseConnection();
			}
			
			return resultStatus;
		}catch (Exception e) {
			log.error("Exception Occured in sendSmsFromAdmin() method - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
			return resultStatus;
		}
	}
	
	
	
	/**
	 * This method Tracks all the Sms's sent by the user and stores the data into corresponding tables.
	 * @param message
	 * @param userId
	 * @param moduleName
	 * @param phoneNumbers
	 */
	
	public Long  saveSmsData(final String message,final Long userId,final String moduleName,final String... phoneNumbers)
	{
		try{
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
			
			SmsModule smsModule = null;
			
			List<SmsModule> list = smsModuleDAO.findByModuleName(moduleName);
			
			if(list != null && list.size() > 0)
				smsModule = list.get(0);
			
			for(String mobileNo : phoneNumbers)
			{
				SmsHistory smsHistory = new SmsHistory();
				smsHistory.setUserId(userId);
				smsHistory.setMobileNumber(mobileNo);
				smsHistory.setSmsContent(message);
				smsHistory.setSentDate(dateUtilService.getCurrentDateAndTimeInStringFormat());
				smsHistory.setSmsModule(smsModule);
				smsHistoryDAO.save(smsHistory);
			}
			
				}
			});
			return (long)ResultCodeMapper.SUCCESS;
		}catch (Exception e) {
			log.error("Error Occured in Saving SMS Data, Exception is - "+e);
			return (long)ResultCodeMapper.FAILURE;
		}
	}
	
	public void setDefualtCredentialForUser(final Long userId){
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {	
				List<SmsTrack> trackId = smsTrackDAO.findLatestRenewalDate(userId);
				if(trackId.size()==0){		
						SmsTrack track = new SmsTrack();
						track.setUserId(userId);
						track.setRenewalDate(dateUtilService.getCurrentDateAndTimeInStringFormat());
						track.setRenewalSmsCount(10000l);
						track = smsTrackDAO.save(track);
				}	
			}
		});
	}
		
	/**
	 * This method return total sms left for the user.
	 * 
	 * @param userId
	 * @return Long Remaining SMS
	 */
	
	public Long getRemainingSmsLeftForUser(Long userId)
	{
		try{
			//Long count = (Long)smsTrackDAO.getRemainingSMSCountForAUser(userId);
			List<SmsTrack> countList = smsTrackDAO.getUserSmsDetailsByUserIdAndSMSType(userId ,IConstants.TEXT_SMS_TYPE);
			
			Long count =  0L;
			if(countList != null && countList.size() >0)
				count = countList.get(0).getRenewalSmsCount();
				
			return count != null? count : 0L;
		}catch (Exception e) {
			log.error("Error Occured - "+e);
			return 0L;
		}
	}
	
	public SmsTrackVO updateUserMessageCreditDetail(SmsTrackVO smsTrackVO){
		SmsTrackVO SmsTraVO =new SmsTrackVO();
	 try{
		    SmsTrack smsTrack = new SmsTrack();
		if(smsTrackVO.getUserSmsTrackId()!=null && smsTrackVO.getUserSmsTrackId()!=0L)
		    smsTrack = smsTrackDAO.get(smsTrackVO.getUserSmsTrackId());		   
			smsTrack.setSmsUsername(smsTrackVO.getSmsUserName());
		    smsTrack.setUserId(smsTrackVO.getUserId());			
			smsTrack.setSmsPassword(smsTrackVO.getSmsPassword());		
			smsTrack.setSenderId(smsTrackVO.getSenderId());
			smsTrack.setRenewalSmsCount(smsTrackVO.getRenewalSmsCount());
			smsTrack.setRenewalDate(dateUtilService.getCurrentDateAndTimeInStringFormat());
		
		smsTrackDAO.save(smsTrack);
		SmsTraVO.setUpdateStatus(1L);
		return SmsTraVO;
	 }
	 catch(Exception e){
		  e.printStackTrace();
		  SmsTraVO.setUpdateStatus(0L);
		 return SmsTraVO;
	 }
	}
	public SmsTrackVO getUserMessageCreditDetail(Long userId){
		SmsTrackVO smsTrackVO = new SmsTrackVO();
		List<Object[]> records = smsTrackDAO.getUserSmsDetailsByUserId(userId);
		if(records.size()>0){
		  Object[] result = records.get(0);
		  smsTrackVO.setUserSmsTrackId((Long)result[0]);
		  smsTrackVO.setSmsUserName(result[1] != null ? result[1].toString() : "");
		  smsTrackVO.setSmsPassword(result[2] != null ? result[2].toString() : "");
		  smsTrackVO.setSenderId(result[3] != null ? result[3].toString() : "");
		  smsTrackVO.setRenewalSmsCount((Long)result[4]);
		  smsTrackVO.setFirstName(result[5] != null ? result[5].toString() : "");
		  smsTrackVO.setLastName(result[6] != null ? result[6].toString() : "");
		  smsTrackVO.setRecordNotFound(1L);
		return smsTrackVO;
		}
		else
		{   
			User user = userDAO.get(userId);
			
			smsTrackVO.setFirstName(user.getFirstName());
			smsTrackVO.setLastName(user.getLastName());
			smsTrackVO.setUserSmsTrackId(0L);
			smsTrackVO.setSmsUserName(" ");
			smsTrackVO.setSmsPassword(" ");
			smsTrackVO.setSenderId(" ");
			smsTrackVO.setRenewalSmsCount(0L);
			
			return smsTrackVO;
		}
		
		
	}
	
}
