package com.itgrids.partyanalyst.GcmService;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.itgrids.partyanalyst.dto.NotificationDeviceVO;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
/*
 * @Srishailm
 * @Swadhin
 */
public class GcmService {
	
	private static final Logger LOG = Logger.getLogger(GcmService.class);
	
	public NotificationDeviceVO sendNotification(String registeredKey,String messageStr,List<String> registeredKeysList){
		
		// String userMessage = IConstants.GCM_SERVER_STATIC_MESSAGE;
		 String userMessage = messageStr.trim();
		 final String GOOGLE_SERVER_KEY = IConstants.GOOGLE_SERVER_KEY;
		 final String MESSAGE_KEY = "message";
		 CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
		 try{
			 if(commonMethodsUtilService.isListOrSetValid(registeredKeysList)){
				 for (String registeredId : registeredKeysList) {
					 try {
						 Sender sender = new Sender(GOOGLE_SERVER_KEY);
						 Message message = new Message.Builder().timeToLive(60).delayWhileIdle(true).addData(MESSAGE_KEY, userMessage).build();
						 Result result = sender.send(message, registeredId, 1);
						 String resultStatus = result.toString();
						 if(!(resultStatus.equalsIgnoreCase("[ errorCode=NotRegistered ]"))){
							 System.out.println(resultStatus);
							 System.out.println("Notification successfully submitted to GCM Server.\n with key  "+registeredId+"");
							 LOG.fatal("Notification successfully submitted to GCM Server to :    "+registeredId+"");
						 }
						 else
							 LOG.fatal(" failure block:   Error Occured while Notification submitting to GCM Server to :    "+registeredId+"");
					} catch (Exception e) {
						LOG.fatal("  catche block : Error Occured while Notification submitting to GCM Server to :    "+registeredId+"");
					}
				 }
				 return  new NotificationDeviceVO("SUCCESS");
			 }
			 else
			 {
				 Sender sender = new Sender(GOOGLE_SERVER_KEY);
				 Message message = new Message.Builder().timeToLive(60).delayWhileIdle(true).addData(MESSAGE_KEY, userMessage).build();
				 Result result = sender.send(message, registeredKey, 1);
				 String resultStatus = result.toString();
				 if(!(resultStatus.equalsIgnoreCase("[ errorCode=NotRegistered ]"))){
					 System.out.println(resultStatus);
					 System.out.println("Notification successfully submitted to GCM Server.");
					 return  new NotificationDeviceVO("SUCCESS");
				 }
			 }
		 }catch(Exception e){
			 System.out.println("Notification failed to submit.");
			 e.printStackTrace();
			 return  new NotificationDeviceVO("FAILURE");
		 }
		 return null;
	 }
}
