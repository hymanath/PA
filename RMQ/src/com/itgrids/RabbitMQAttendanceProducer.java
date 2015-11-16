package com.itgrids;

import com.itgrids.utils.IConstants;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class RabbitMQAttendanceProducer {
	
	 public static void main(String []args) throws Exception 
	 {
		try{
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUsername(IConstants.RMQ_USERNAME);
			factory.setPassword(IConstants.RMQ_PASSWORD);
			factory.setVirtualHost(IConstants.VIRTUALHOST);
			factory.setHost(IConstants.HOST);
			factory.setPort(IConstants.PORT);
			
			Connection conn = factory.newConnection();
			Channel channel = conn.createChannel();
			channel.confirmSelect();
			boolean result = false;
			
			String message = "{\"partyMeetingId\":\"1\",\"trainingCampScheduleId\":\"1\",\"trainingCampBatchId\":\"1\"," +
			 		"\"trainingCampTopicId\":\"1\",\"type\":\"Meeting\",\"membershipId\":\"12345678\",\"attendedTime\":\"2015-08-120 2:30:10\"," +
			 		"\"rfid\":\"123456RF\",\"imei\":\"1234567890\",\"uniqueKey\":\"kamal-dandu-1234-test\",\"latitude\":\"0.00\",\"longitude\":\"0.01\"," +
			 		"\"tabUserId\":\"1\",\"currentTabUserId\":\"1\"}";
			 
			byte[] messageBodyBytes = message.getBytes();
			channel.basicPublish("attendance_ws","attendacen_Ws_747294",MessageProperties.PERSISTENT_TEXT_PLAIN,messageBodyBytes);
			result = channel.waitForConfirms();
			System.out.println("Submitted --> "+result);
			channel.close();
			conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
}
