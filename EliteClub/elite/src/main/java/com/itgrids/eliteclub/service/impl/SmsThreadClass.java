package com.itgrids.eliteclub.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.eliteclub.dto.UserContactsInputVO;
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
	 * @version 1.2 by anil
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
	
	
	public void activeteThread(UserContactsInputVO inputs)
	{
		try
		{
			LOG.debug("Entered into activeteThread  method in SmsThreadClass service");
		
			SmsServiceImpl smsServiceImpl = new SmsServiceImpl(); 
		    smsServiceImpl.setUserId(inputs.getUserId());
			smsServiceImpl.setImeiNo(inputs.getImeiNo());
			//smsServiceImpl.setAudioFileId(audioFileId);
			List<Integer> ids = new ArrayList<Integer>();
			
			for (String  fileId : inputs.getFileIds().replace("[", "").replace("]", "").split(",")) {
				ids.add(Integer.valueOf(fileId.trim()));
			}
			
			smsServiceImpl.setFileIds(ids);
	     	smsServiceImpl.setService((SmsServiceImpl)smsService);
			smsService.start(smsServiceImpl);
		} 
		catch (Exception e)
		{
			LOG.error("Exception Occure in activeteThread method in SmsThreadClass service" , e);
		}
		
	}
	
	/*public void start (SmsServiceImpl objData )
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
    }*/
	
}
