package com.itgrids;

import java.util.List;

import com.itgrids.utils.IConstants;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class RabbitMQProducer {

	public static void main(String ... args)
	{
		RabbitMQProducer producer = new RabbitMQProducer();
		producer.sendMessagesWithReplyAck("attendance_kamal","Dandu",null);
	}
	
	public boolean sendMessagesWithReplyAck(String exchangeName,String routingKey,List<String> msgList)
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
		
		int ind = 0;
		  
		/*for(String message : msgList)
		{
			try{
				byte[] messageBodyBytes = message.getBytes();
				channel.basicPublish(exchangeName,routingKey,MessageProperties.PERSISTENT_TEXT_PLAIN,messageBodyBytes);
				result = channel.waitForConfirms();
				System.out.println(++ind+" Is Submitted --> "+result);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}*/
		for(int i=0;i<1;i++)
		{
			String message = "Kamalakar - "+i;
			byte[] messageBodyBytes = message.getBytes();
			channel.basicPublish(exchangeName,routingKey,MessageProperties.PERSISTENT_TEXT_PLAIN,messageBodyBytes);
			result = channel.waitForConfirms();
			System.out.println(++ind+" Is Submitted --> "+result);
		}
		
		channel.close();
		conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
