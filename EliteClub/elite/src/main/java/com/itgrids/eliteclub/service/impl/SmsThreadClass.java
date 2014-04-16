package com.itgrids.eliteclub.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgrids.eliteclub.service.ISmsService;

/**
 * 
 * This Service is used for activating the thread call for sendig both text and voice sms to the users
 * @author Prasad Thiragabathina
 *
 */
@Service("smsThreadClass")
public class SmsThreadClass
{

	private static final Logger LOG = LogManager.getLogger();
	@Autowired 
	ISmsService smsService;
	
	/**
	 * This Service is used for activating the sms thread
	 * @author Prasad Thiragabthina
	 */
	
	public void activeteThread(String imeiNo,Integer userId,Integer audioFileId)
	{
		try
		{
			LOG.debug("Entered into activeteThread  method in SmsThreadClass service");
			((SmsServiceImpl)smsService).setUserId(userId);
			((SmsServiceImpl)smsService).setImeiNo(imeiNo);
			((SmsServiceImpl)smsService).setAudioFileId(audioFileId);
			SmsServiceImpl smsServiceImpl = new SmsServiceImpl(); 
		    smsServiceImpl.setUserId(userId);
			smsServiceImpl.setImeiNo(imeiNo);
			smsServiceImpl.setAudioFileId(audioFileId);
			smsServiceImpl.setService((SmsServiceImpl)smsService);
			smsService.start(smsServiceImpl);
		} 
		catch (Exception e)
		{
			LOG.error("Exception Occure in activeteThread method in SmsThreadClass service" , e);
		}
		
	}
	
}
