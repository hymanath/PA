package com.itgrids;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.QueueingConsumer;

public class RabbitMQRPCProducer {
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
			   
			   String reqString = "{\"url\":\"http://mytdp.com/WebService/insertEventAttendeeInfo/\"," +
					   "\"data\":{\"memberShipNo\" : \""+memberShipNo+"\",\"rfid\" : \""+rfid+"\",\"imei\" : \""+imei+"\"," +
					   "\"id\" : \""+id+"\",\"eventId\" : \""+eventId+"\",\"loginTimeStamp\" : \"2015-05-13 03:26:20\"," +
					   "\"status\" : \""+status+"\",\"tabPrimaryKey\" : \""+tabPrimaryKey+"\"}}";
	   		
			   reqList.add(reqString);
		   }catch(Exception e){
			   System.out.println("1) Exception in Event - "+event);
			   e.printStackTrace();
		   }
	   }
	   
	   ConnectionFactory factory = new ConnectionFactory();
	   factory.setUsername("guest");
	   factory.setPassword("guest");
	   factory.setVirtualHost("/");
	   factory.setHost("127.0.0.1");
	   factory.setPort(5672);
	  	   
	   Connection conn = factory.newConnection();
	   Channel channel = conn.createChannel();
	   String exchangeName = "event_attendee_ws";
	   String routingKey = "event_Ws_1";
	   
	   String replyQueue = channel.queueDeclare().getQueue(); 
	   QueueingConsumer consumer = new QueueingConsumer(channel);
	   channel.basicConsume(replyQueue, true, consumer);
	   
	   /*String message = "{\"url\":\"http://mytdp.com/WebService/insertEventAttendeeInfo/\"," +
	   		"\"data\":{\"memberShipNo\" : \"12345678\",\"rfid\" : \"592C8902\",\"imei\" : \"860070026264285\"," +
 				"\"id\" : \"1\",\"eventId\" : \"8\",\"loginTimeStamp\" : \"2015-05-13 03:26:20\"," +
 				"\"status\" : \"8e4321ba-9d57-4332-83d8-be7b46bfa52f\",\"tabPrimaryKey\" : \"10001\"}}";*/
	   
	  /* for(String message : reqList)
	   {
		   try{
			   byte[] messageBodyBytes = message.getBytes();
			   channel.basicPublish(exchangeName,routingKey,MessageProperties.PERSISTENT_TEXT_PLAIN,messageBodyBytes);
			   
		   }catch(Exception e)
		   {
			   System.out.println("2) Exception in Event - "+event);
			   e.printStackTrace();
		   }
	   }*/
	   System.out.println("List size -- > "+reqList.size());
	   int index = 0;
	   
	   for(String message : reqList)
	   {
		   try{
			   System.out.println("Now at --> Client "+args[3]+" At index --> "+ ++index);
			   byte[] messageBodyBytes = message.getBytes();
			   String response = null;
			   String corrId = java.util.UUID.randomUUID().toString();
			   BasicProperties props = new BasicProperties.Builder().correlationId(corrId).replyTo(replyQueue).build();

			   //channel.basicPublish(exchangeName,routingKey,MessageProperties.PERSISTENT_TEXT_PLAIN,messageBodyBytes);
			   channel.basicPublish(exchangeName,routingKey,props,messageBodyBytes);
			   
			   while (true) {
			        QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			        if (delivery.getProperties().getCorrelationId().equals(corrId)) {
			            response = new String(delivery.getBody());
			            break;
			        }
			    }
			   System.out.println(response);
		   }catch(Exception e)
		   {
			   System.out.println("2) Exception in Event - "+event);
			   e.printStackTrace();
		   }
	   }
	   channel.close();
	   conn.close();
	 }
}