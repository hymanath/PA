package com.itgrids.eliteclub.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
	
	
	public void sendVoiceSmsThread(Integer audioFileId, List<String> mobileNos,Thread thread,List<?> fileIds)
	{
		try
		{
			LOG.debug("Entered into sendVoiceSmsThread method in SmsServiceImpl service");
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(30000);
			//get voice id for   file if it is  vice file
			
			
			sendVoiceSms(mobileNos,audioFileId,fileIds);
		}
		catch (Exception e)
		{
			LOG.error("Exception Occure in sendVoiceSmsThread method in SmsServiceImpl service" , e);
		}
	}
	
	
	/**
	 * This service is used for sending voice sms to the mobiless
	 * @author Prasad Thiragabathina
	 * @param mobileNos
	 * @param audioIndex
	 * @version 1.2 by anil 
	 * @param fileIds 
	 */
	public void sendVoiceSms(List<String> mobileNos,Integer audioIndex, List<?> fileIds)
	{
		LOG.debug("Entered INTO  sendVoiceSms method ");
		try
		{
			
		   // String phoneno="9505485043";	    
		    String voiceid="101";
		    RestTemplate restTemplate = new RestTemplate();
		    String ids=fileIds.toString().replace("[", "").replace("]", "")
		            .replace(", ", ",");
		    LOG.debug("voice Ids That Have To Send "+ids);
		    for (String string : mobileNos) {
		    	 String calluid=UUID.randomUUID().toString();
		    	 String phoneno=string;
		    	
				 String url="http://103.241.182.18/ConVoxBCT/VoiceApp.php?calluid="+calluid+"&phoneno="+phoneno+"&voiceid="+ids;
			//	System.out.println(url);
				 LOG.debug("voice sms url "+url);

				 String page = restTemplate.getForObject(url, String.class);
				 
				 System.out.println(page);
				 LOG.debug("voice sms response "+page);

			}
			
			
			LOG.debug("Entered into sendVoiceSms method in SmsServiceImpl service");
			
			
			
		} 
		catch (Exception e)
		{
			
			LOG.error("Exception Occure in sendVoiceSms method in SmsServiceImpl service" , e);
		}
	}

}
