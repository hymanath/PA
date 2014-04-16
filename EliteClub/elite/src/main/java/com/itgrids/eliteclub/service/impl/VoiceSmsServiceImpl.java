package com.itgrids.eliteclub.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.itgrids.eliteclub.service.VoiceSmsService;
/**
 * This service is used for sending the voice sms for mobile numbers
 * @author Prasad Thiragabathina
 *@version 1.2 by anil 
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
			System.out.println(Thread.currentThread().getName());
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
	 * @version 1.2 by anil 
	 */
	public void sendVoiceSms(List<String> mobileNos,Integer audioIndex)
	{
		try
		{
			
		   // String phoneno="9505485043";	    
		    String voiceid="101";
		    RestTemplate restTemplate = new RestTemplate();
		    for (String string : mobileNos) {
		    	 String calluid=UUID.randomUUID().toString();
		    	 String phoneno=string;
		    	
				 String url="http://103.241.182.18/ConVoxBCT/VoiceApp.php?calluid="+calluid+"&phoneno="+phoneno+"&voiceid="+voiceid;
				System.out.println(url);
				 String page = restTemplate.getForObject(url, String.class);
				 
				 System.out.println(page);
			}
			
			
			LOG.debug("Entered into sendVoiceSms method in SmsServiceImpl service");
			
			
			
		} 
		catch (Exception e)
		{
			
			LOG.error("Exception Occure in sendVoiceSms method in SmsServiceImpl service" , e);
		}
	}

}
