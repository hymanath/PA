package com.itgrids;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RabbitMQClient {
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://localhost:3306/dakavara_pa";
		static final String USER = "root";
		static final String PASS = "root";
	
		static java.sql.Connection dbConn = null;
		static Statement stmt = null;
		
   public static void main(String []args) throws Exception 
   {
	   Class.forName("com.mysql.jdbc.Driver");
	   dbConn = DriverManager.getConnection(DB_URL,USER,PASS);
	   stmt = dbConn.createStatement();
	   
	   String event = args[0];
	   String startIndex = args[1];
	   String maxResults = args[2];
	   List<String> reqList = new ArrayList<String>(0);
	   
	   ResultSet rs = stmt.executeQuery("select memberShipNo,rfid,imei,id,eventId,loginTimeStamp,status,tabPrimaryKey" +
	   		" from event_data where eventId = "+event+" limit "+startIndex+","+maxResults+"");
	   
	   while(rs.next())
	   {
		   try{
			   Integer memberShipNo = rs.getInt(1);
			   String rfid = rs.getString(2);
			   String imei = rs.getString(3);
			   Integer id = rs.getInt(4);
			   Integer eventId = rs.getInt(5);
			   String status = rs.getString(7);
			   Integer tabPrimaryKey = rs.getInt(8);
			   String memberShipNoStr = memberShipNo.toString();
			   
			   if(memberShipNoStr.length() == 7)
				   memberShipNoStr = "0"+memberShipNoStr;
			   
			   String reqString = "{\"memberShipNo\" : \""+memberShipNoStr+"\",\"rfid\" : \""+rfid+"\",\"imei\" : \""+imei+"\"," +
					   "\"id\" : \""+id+"\",\"eventId\" : \""+eventId+"\",\"loginTimeStamp\" : \"2015-05-13 03:26:20\"," +
					   "\"status\" : \""+status+"\",\"tabPrimaryKey\" : \""+tabPrimaryKey+"\"}";
	   		
			   reqList.add(reqString);
		   }catch(Exception e){
			   System.out.println("1) Exception in Event - "+event);
			   e.printStackTrace();
		   }
	   }
	   
	   RabbitMQProducer producer = new RabbitMQProducer();
	   int limit = 1000;
	   boolean result;
	   
	   if(limit >= reqList.size())
	   {
		   result = producer.sendMessagesWithReplyAck("event_attendee_ws","event_Ws_184375", reqList);
		   
		   if(result)
			   System.out.println("The Messages Successfully Added to Queue");
		   else
			   System.out.println("The Messages Adding Failed");
	   }
	   
	   else
	   {
		   List<String> tmpList = new ArrayList<String>(0);
		   int index = 0;
		   for(String msg : reqList)
		   {
			   System.out.println("We are at --> "+ ++index);
			   tmpList.add(msg);
			   if(tmpList.size() == limit)
			   {
				   result = producer.sendMessagesWithReplyAck("event_attendee_ws","event_Ws_1", tmpList);
				   
				   if(result)
					   System.out.println("The Messages Successfully Added to Queue");
				   else
					   System.out.println("The Messages Adding Failed");
				   
				   tmpList = new ArrayList<String>(0);
			   }
		   }
		   if(tmpList.size() > 0)
		   {   
			   result = producer.sendMessagesWithReplyAck("event_attendee_ws","event_Ws_1", tmpList);
		   
			   if(result)
				   System.out.println("The Messages Successfully Added to Queue");
			   else
				   System.out.println("The Messages Adding Failed");
		   }
	   }
		   
	 }
}