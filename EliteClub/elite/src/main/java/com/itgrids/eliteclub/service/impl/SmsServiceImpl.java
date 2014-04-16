package com.itgrids.eliteclub.service.impl;



import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
					List<String> mobileNos = this.getService().contactDetailsDAO.getMobileNumbersByUser(this.imeiNo,this.userId);
					User  user = this.getService().userDAO.get(this.userId);
					String userName=user.getUserName();
					if(userName==null)
						userName=""+this.imeiNo;
					
					StringBuilder message= new StringBuilder("This Message Reffered By "+userName);
					message.append("");
					sendSms(message.toString(),false,mobileNos);
				}
				else
				{
					List<String> mobileNumbers = this.getService().contactDetailsDAO.getMobileNumbersByUser(imeiNo,userId);
					this.getService().voiceSmsService.sendVoiceSmsThread(this.audioFileId,mobileNumbers,currentThread);
				}
				
			}

		catch (Exception e)
		{
			LOG.error("Exception Occure in run method in SmsServiceImpl service" , e);
		}
	}
	
	
	/**
	 * This Service is used for sending the sms to the selected mobile numbers
	 * @author Prasad Thiragabathina
	 * @param message
	 * @param isEnglish
	 * @param phoneNumbers
	 */
	public void sendSms(String message, boolean isEnglish,
			List<String> phoneNumbers) 
	{

		HttpClient client = null;
		PostMethod post = null;
		
		/*Long count = getRemainingSmsLeftForUser(userId) - phoneNumbers.length;
		
		if(count < 0)
			return (long)ResultCodeMapper.FAILURE;

		 */		
		
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
	    
	    post = new PostMethod("http://sms.partyanalyst.com/WebserviceSMS.aspx");
		
		post.addParameter("User", IConstants.ADMIN_USERNAME_FOR_SMS);
		post.addParameter("passwd", IConstants.ADMIN_PASSWORD_FOR_SMS);
		post.addParameter("sid", IConstants.ADMIN_SENDERID_FOR_SMS);
		
	    post.addParameter("mobilenumber", sb.toString());
		post.addParameter("message", message);
		post.addParameter("mtype", isEnglish ? "N" : "OL");
		post.addParameter("DR", "Y");
		
		LOG.debug(" Query String :" + post.getQueryString());

		/* PUSH the URL */
		try 
		{
			int statusCode = client.executeMethod(post);
			
			LOG.debug(post.getStatusLine().toString()+"***"+statusCode+"*****"+post.getQueryString());
			
			if (statusCode != HttpStatus.SC_OK)
			{
				LOG.error("SmsCountrySmsService.sendSMS failed: "+ post.getStatusLine());
				LOG.debug("SmsCountrySmsService.sendSMS failed: "+ post.getStatusLine());
			}
			
			LOG.debug(post.getResponseBodyAsString());
		}
		catch (Exception e)
		{
			LOG.error("Exception Occure in sendSms " , e);
		} 
		finally
		{
			post.releaseConnection();
		}

	}

	
}
