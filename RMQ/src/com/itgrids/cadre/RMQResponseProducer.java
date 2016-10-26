package com.itgrids.cadre;

import com.itgrids.utils.IConstants;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class RMQResponseProducer {

	public static boolean sendMessagesWithReplyAck(String exchangeName,String routingKey,String response)
	{
		boolean result = false;
		
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
		
		byte[] messageBodyBytes = response.getBytes();
		channel.basicPublish(exchangeName,routingKey,MessageProperties.PERSISTENT_TEXT_PLAIN,messageBodyBytes);
		result = channel.waitForConfirms();
		System.out.println(" Submitted --> "+result);
			
		channel.close();
		conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
