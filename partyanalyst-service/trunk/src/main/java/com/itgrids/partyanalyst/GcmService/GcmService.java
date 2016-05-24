package com.itgrids.partyanalyst.GcmService;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.itgrids.partyanalyst.dto.NotificationDeviceVO;
import com.itgrids.partyanalyst.utils.IConstants;
/*
 * @Srishailm
 * @Swadhin
 */
public class GcmService {
	public NotificationDeviceVO sendNotification(String registeredId,String messageStr){
		// String userMessage = IConstants.GCM_SERVER_STATIC_MESSAGE;
		 String userMessage = messageStr.trim();
		 final String GOOGLE_SERVER_KEY = IConstants.GOOGLE_SERVER_KEY;
		 final String MESSAGE_KEY = "message";	
		 try{
			 Sender sender = new Sender(GOOGLE_SERVER_KEY);
			 Message message = new Message.Builder().timeToLive(60).delayWhileIdle(true).addData(MESSAGE_KEY, userMessage).build();
			 Result result = sender.send(message, registeredId, 1);
			 String resultStatus = result.toString();
			 if(!(resultStatus.equalsIgnoreCase("[ errorCode=NotRegistered ]"))){
				 System.out.println(resultStatus);
				 System.out.println("Notification successfully submitted to GCM Server.");
			 return  new NotificationDeviceVO("SUCCESS");
			 }
		 }catch(Exception e){
			 System.out.println("Notification failed to submit.");
			 e.printStackTrace();
			 return  new NotificationDeviceVO("FAILURE");
		 }
		 return null;
	 }
}
