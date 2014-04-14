package com.itgrids.eliteclub.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.itgrids.eliteclub.service.VoiceSmsService;
/**
 * This service is used for sending the voice sms for mobile numbers
 * @author Prasad Thiragabathina
 *
 */
@Service("voiceSmsService")
public class VoiceSmsServiceImpl implements VoiceSmsService
{

	private static final Logger LOG = LogManager.getLogger();
	
	public void sendVoiceSmsThread(Integer audioFileId, List<String> mobileNos,Thread thread)
	{
		try
		{
			LOG.debug("Entered into sendVoiceSmsThread method in SmsServiceImpl service");
			Thread.sleep(30000);
			sendVoiceSms(mobileNos,audioFileId);
		}
		catch (Exception e)
		{
			LOG.error("Exception Occure in sendVoiceSmsThread method in SmsServiceImpl service" , e);
		}
	}
	
	
	/**
	 * This service is used for sending voice sms to the mobiles
	 * @author Prasad Thiragabathina
	 * @param mobileNos
	 * @param audioIndex
	 */
	public void sendVoiceSms(List<String> mobileNos,Integer audioIndex)
	{
		try
		{
			LOG.debug("Entered into sendVoiceSms method in SmsServiceImpl service");
		} 
		catch (Exception e)
		{
			LOG.error("Exception Occure in sendVoiceSms method in SmsServiceImpl service" , e);
		}
	}

}
