package com.itgrids.eliteclub.service.impl;



import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.engine.spi.TypedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.itgrids.eliteclub.dao.ContactDetailsDAO;
import com.itgrids.eliteclub.dao.FileDAO;
import com.itgrids.eliteclub.dao.UserDAO;
import com.itgrids.eliteclub.model.User;
import com.itgrids.eliteclub.service.ISmsService;
import com.itgrids.eliteclub.service.VoiceSmsService;
import com.itgrids.eliteclub.util.IConstants;

/**
 * This Service is used for Sending text sms to the user
 * @author Prasad Thiragabathina
 *
 */
@Service("smsService")

public class SmsServiceImpl implements ISmsService,Runnable
{

	private static final Logger LOG = LogManager.getLogger();

	@Autowired 
	ContactDetailsDAO contactDetailsDAO;
	
	@Autowired
	VoiceSmsService voiceSmsService;
	
	@Autowired
	UserDAO userDAO;
	
	private   SmsServiceImpl service;
	
	@Autowired 
	private  FileDAO fileDAO ;
	
	
	


	public SmsServiceImpl getService() {
		return service;
	}

	public void setService(SmsServiceImpl service) {
		this.service = service;
	}

	public SmsServiceImpl()
	{
		 System.out.println("Deafult Constrator"); 
	}
	
	private  String imeiNo;
	private  Integer userId;
	private  Integer audioFileId;
	private List<Integer> fileIds;
	


	public List<Integer> getFileIds() {
		return fileIds;
	}

	public void setFileIds(List<Integer> fileIds) {
		this.fileIds = fileIds;
	}

	public ContactDetailsDAO getContactDetailsDAO() {
		return contactDetailsDAO;
	}

	public void setContactDetailsDAO(ContactDetailsDAO contactDetailsDAO) {
		this.contactDetailsDAO = contactDetailsDAO;
	}

	public VoiceSmsService getVoiceSmsService() {
		return voiceSmsService;
	}

	public void setVoiceSmsService(VoiceSmsService voiceSmsService) {
		this.voiceSmsService = voiceSmsService;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}




	public String getImeiNo() {
		return imeiNo;
	}

	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAudioFileId() {
		return audioFileId;
	}

	public void setAudioFileId(Integer audioFileId) {
		this.audioFileId = audioFileId;
	}
    
	public void start (SmsServiceImpl objData )
    {
		
		Thread textThread = null;
		Thread audioThread = null;
		if (textThread == null)
		{
			String textTread = "textTread";
			LOG.debug("Starting " +  textTread );
			textThread = new Thread (objData, textTread);
			textThread.start ();
		}
		if(audioThread == null)
		{
			String audioTread = "audioTread";
			LOG.debug("Starting " +  audioTread );
			audioThread = new Thread (objData, audioTread);
			audioThread.start ();
		}
    }
 
	public void run()
	{
		try
		{
			LOG.debug("Entered into run method in SmsServiceImpl service");
			
			
				Thread currentThread = Thread.currentThread();
				if(currentThread.getName().equalsIgnoreCase("textTread"))
				{
				/*	List<String> mobileNos = this.getService().contactDetailsDAO.getMobileNumbersByUser(this.imeiNo,this.userId);
					User  user = this.getService().userDAO.get(this.userId);
					String userName=user.getUserName();
					if(userName==null)
						userName=""+this.imeiNo;
					
					StringBuilder message= new StringBuilder("Your Friend  "+userName+" has sent this message to convey that Mr.Chandra Babu Naidu garu will speak with you shortly from this number 14001281999 ");
					message.append("");
					List<String> messages=null;
					if(this.getFileIds()!=null && this.getFileIds().size()>0)
					{
					 messages=(List<String>) this.getService().fileDAO.getSmsTextForFileIds(new HashSet<Integer>(this.getFileIds()));
					}
					// get  list of messages that we have to send 
					sendSms(message.toString(),false,mobileNos,messages);*/
					sendTextMesaagesToContacts();
				}
				else
				{/*
					List<String> mobileNumbers = this.getService().contactDetailsDAO.getMobileNumbersByUser(imeiNo,userId);
					//ge voiceId for fileId
				
					List<?> voiceIds=this.getService().fileDAO.getVoiceIdsForFileIds(new HashSet<Integer>(this.getFileIds()));
					if(voiceIds !=null && voiceIds.size()>0)
					this.getService().voiceSmsService.sendVoiceSmsThread(null,mobileNumbers,currentThread,voiceIds);*/
					sendVoiceMessagesToContacts();
				}
				
			}

		catch (Exception e)
		{
			LOG.error("Exception Occure in run method in SmsServiceImpl service" , e);
		}
	}
	
	//send messages method is here
	/**
	 * @author  Anilkumar Ravula
	 * Apr 25, 2014
	 * 
	 */
	//@Transactional
	public void sendTextMesaagesToContacts()
	{
		List<String> mobileNos = this.getService().contactDetailsDAO.getMobileNumbersByUser(this.imeiNo,this.userId);
		User  user = this.getService().userDAO.get(this.userId);
		String userName=user.getUserName();
		if(userName==null)
			userName=""+this.imeiNo;
		
		StringBuilder message= new StringBuilder("Your Friend  "+userName+" has sent this message to convey that Mr.Chandra Babu Naidu garu will speak with you shortly from this number 14001281999 ");
		message.append("");
		List<String> messages=null;
		if(this.getFileIds()!=null && this.getFileIds().size()>0)
		{
		 messages=(List<String>) this.getService().fileDAO.getSmsTextForFileIds(new HashSet<Integer>(this.getFileIds()));
		}
		// get  list of messages that we have to send 
		sendSms(message.toString(),false,mobileNos,messages);
	}
	
	//send voice messagesTo Contacts	
	//@Transactional
	public void sendVoiceMessagesToContacts()
	{
		
		List<String> mobileNumbers = this.getService().contactDetailsDAO.getMobileNumbersByUser(imeiNo,userId);
		//ge voiceId for fileId
	
		List<?> voiceIds=this.getService().fileDAO.getVoiceIdsForFileIds(new HashSet<Integer>(this.getFileIds()));
		if(voiceIds !=null && voiceIds.size()>0)
		this.getService().voiceSmsService.sendVoiceSmsThread(null,mobileNumbers,null,voiceIds);
		
	}
	
	
	/**
	 * This Service is used for sending the sms to the selected mobile numbers
	 * @author Prasad Thiragabathina
	 * @param message
	 * @param isEnglish
	 * @param phoneNumbers
	 * @param messages 
	 * @param set 
	 * @param set 
	 * @version 1.18 by anil
	 */
	public void sendSms(String message, boolean isEnglish,
			List<String> phoneNumbers, List<String> messages) 
	{

		HttpClient client = null;
		PostMethod post = null;
		
		
		client = new HttpClient(new MultiThreadedHttpConnectionManager());// here we are getting the HttpClient For Sending Sms

		client.getHttpConnectionManager().getParams().setConnectionTimeout(
				Integer.parseInt("30000"));

		StringBuilder sb = new StringBuilder();// to append all the mobile n os with comma seperator
		
		for (int i = 0; i < phoneNumbers.size(); i++)
		{   
			if(phoneNumbers.get(i).trim().length()==10)
			sb.append("91");
			
			sb.append(phoneNumbers.get(i));
			if (i < (phoneNumbers.size()-1))
				sb.append(",");
		}
			
		LOG.debug("Mobile Nos :" + sb.toString());
	    
	 

		/* PUSH the URL */
		try 
		{  
			if(messages !=null && messages.size()>0)
			{
				for (String msgText : messages) {
					
			
				    RestTemplate restTemplate = new RestTemplate();

				    MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
					map.add("User", IConstants.ADMIN_USERNAME_FOR_SMS);
					map.add("passwd", IConstants.ADMIN_PASSWORD_FOR_SMS);
					map.add("mobilenumber", sb.toString());
					map.add("message", msgText);
					map.add("mtype", "N");
					 LOG.debug("URL For Text Message "+"http://api.smscountry.com/SMSCwebservice_bulk.aspx"+map.toString());
					String page = restTemplate.postForObject("http://api.smscountry.com/SMSCwebservice_bulk.aspx", map, String.class);
						 
						
						 LOG.debug("text sms response"+ page);
					
						try {
							Thread.sleep(3000);
						}catch (Exception e) {
							LOG.error("exception "+e);
						}
				}
				
			}else
			{
				
			
				RestTemplate restTemplate = new RestTemplate();

				  MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
					map.add("User", IConstants.ADMIN_USERNAME_FOR_SMS);
					map.add("passwd", IConstants.ADMIN_PASSWORD_FOR_SMS);
					map.add("mobilenumber", sb.toString());
					map.add("message", message);
					map.add("mtype", "N");
					
					String page = restTemplate.postForObject("http://api.smscountry.com/SMSCwebservice_bulk.aspx", map, String.class);
						 
						 System.out.println(page);
						 LOG.debug("text sms response"+page);
			}
		
		}
		catch (Exception e)
		{
			LOG.error("Exception Occure in sendSms " , e);
		} 
		finally
		{   if(post!=null)
			post.releaseConnection();
		}

	}
	
	public void sendSmsByTakingMessage(String message,PostMethod post,HttpClient client)
	{
		
		int statusCode=0;
		try {
		 statusCode = client.executeMethod(post);
		} catch (HttpException e) {
			LOG.error("SmsCountrySmsService.sendSMS failed: "+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error("SmsCountrySmsService.sendSMS failed: "+ e.getMessage());
			e.printStackTrace();
		}
		catch (Exception e) {
			LOG.error("SmsCountrySmsService.sendSMS failed: "+ e.getMessage());
			e.printStackTrace();
		}
		
		LOG.debug(post.getStatusLine().toString()+"***"+statusCode+"*****"+post.getQueryString());
		if (statusCode != HttpStatus.SC_OK)
		{
			LOG.error("SmsCountrySmsService.sendSMS failed: "+ post.getStatusLine());
			LOG.debug("SmsCountrySmsService.sendSMS failed: "+ post.getStatusLine());
		}
	}
	
		
	

	
}
