package com.itgrids;

import java.util.ArrayList;
import java.util.List;

public class RabbitMQClient2 {
		
   public static void main(String []args) throws Exception 
   {
	   List<String> reqList = new ArrayList<String>(0);
		   try{
			   
			   String reqString = "{\"memberShipNo\" : \"12345678\",\"rfid\" : \"RF1234\",\"imei\" : \"865498026765305\"," +
					   "\"id\" : \"194\",\"eventId\" : \"2\",\"loginTimeStamp\" : \"2015-05-13 03:26:20\"," +
					   "\"status\" : \"kamal-uniq-1234-5678\",\"tabPrimaryKey\" : \"1\",\"latituede\":\"0.0\",\"longitude\":\"0.0\"," +
					   "\"userId\":\"194\"}";
			   reqList.add(reqString);
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	   
	   RabbitMQProducer producer = new RabbitMQProducer();
	   boolean result;
	   
	   result = producer.sendMessagesWithReplyAck("event_attendee_ws","event_Ws_184375", reqList);
		   
		   if(result)
			   System.out.println("The Messages Successfully Added to Queue");
		   else
			   System.out.println("The Messages Adding Failed");
	   }
}