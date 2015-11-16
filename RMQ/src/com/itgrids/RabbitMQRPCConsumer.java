package com.itgrids;

import org.json.JSONObject;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
public class RabbitMQRPCConsumer {
    public static void main(String []args) throws Exception 
    {
		 ConnectionFactory factory = new ConnectionFactory();
		 factory.setUsername("guest");
		 factory.setPassword("guest");
		 factory.setVirtualHost("/");
		 factory.setHost("127.0.0.1");
		 factory.setPort(5672);
		 
		 Connection conn = factory.newConnection();
	     Channel channel = conn.createChannel();
	     String exchangeName = "event_attendee_ws";
	     String queueName = "attendee_queue";
	     String routingKey = "event_Ws_1";
	     boolean durable = true;
	     
	     channel.exchangeDeclare(exchangeName, "direct", durable);
	     channel.queueDeclare(queueName, durable,false,false,null);
	     channel.queueBind(queueName, exchangeName, routingKey);
	     boolean noAck = false;
      
	     QueueingConsumer consumer = new QueueingConsumer(channel);
	     channel.basicConsume(queueName, noAck, consumer);
	     boolean runInfinite = true;
      
	     while(runInfinite)
	     {
	    	 try{
	    		 QueueingConsumer.Delivery delivery;
	    		 try {
	    			 delivery = consumer.nextDelivery();
	    		 	} catch (InterruptedException ie){
	    		 		continue;
	    		 	}
	    		 BasicProperties props = delivery.getProperties();
	    		 BasicProperties replyProps = new BasicProperties.Builder().correlationId(props.getCorrelationId()).build();
	    		 
	    		 String msg = new String(delivery.getBody());
	    		 boolean result = doWork(msg);
	    		 System.out.println("Result --> "+result);
	    		 
	    		 String response = "Success";
	    		 
	    		 channel.basicPublish("", props.getReplyTo(), replyProps, response.getBytes());
	    		 
	    		 
	    		 channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
	    	 }catch(Exception e)
	    	 {
	    		 e.printStackTrace();
	    	 }
	     }
	     channel.close();
	     conn.close();
      }
    
    public static boolean doWork(String str)
    {
    	try{
    		JSONObject jobj = new JSONObject(str);
    		String url = jobj.getString("url");
    		String data = jobj.getString("data");
    		
    		Client client = Client.create();
     		WebResource webResource = client.resource(url);
    		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, data);
     
    		System.out.println("Output from Server .... \n");
    		String output = response.getEntity(String.class);
    		System.out.println(output);
    		
    		return true;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		return false;
    	}
    }
}
