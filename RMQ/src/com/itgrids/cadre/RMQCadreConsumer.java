package com.itgrids.cadre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.json.JSONObject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RMQCadreConsumer implements Runnable{

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/rmq";
	static final String USER = "root";
	static final String PASS = "itg$36593^";
	
	static Connection conn = null;
	static Statement stmt = null;
	
    public void run()
    {
    	try{
		 ConnectionFactory factory = new ConnectionFactory();
		 factory.setUsername("guest");
		 factory.setPassword("guest");
		 factory.setVirtualHost("/");
		 factory.setHost("localhost");
		 factory.setPort(5672);
		 
		 com.rabbitmq.client.Connection conn = factory.newConnection();
	     Channel channel = conn.createChannel();
	     String exchangeName = "cadre_exchange";
	     String queueName = "cadre_queue";
	     String routingKey = "Dandu";
	     boolean durable = true;
	     
	     channel.exchangeDeclare(exchangeName, "topic", durable);
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
	    		 
	    		 //Thread.sleep(100);
	    		 String msg = new String(delivery.getBody());
	    		 
	    		 boolean result = doWork(msg);
	    		 //boolean result = true;
	    		  
	    		 if(result)
	    			 System.out.println("Success");
	    		 else
	    			 System.out.println("Failure");
	    		 
	    		 if(result)
	    		 {
	    			channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
	    			System.out.println("Saved and Remove from Queue");
	    		 }
	    		 else
	    			 System.out.println("Not Saved and Send back to Queue");
	    		 
	    	 }catch(Exception e)
	    	 {
	    		 e.printStackTrace();
	    	 }
	     }
	     channel.close();
	     conn.close();
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
      }
    
    public static boolean doWork(String str)
    {
    	try{
    		
    		Client client = Client.create();
     		WebResource webResource = client.resource("http://192.168.11.143:8090/CadreReg-1.4.0.RELEASE/WebService/saveFieldDataForCadre/");
    		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,str);
    		
    		int responseCode = response.getStatus();
    		String output = response.getEntity(String.class);
    		System.out.println("Output from Server ---> "+output);
    		
    		JSONObject reqObj = new JSONObject(str);
    		int requestId = reqObj.getInt("cadreId");
    		
    		String sql = "INSERT INTO response_status(request_id,status_code) VALUES ("+requestId+","+responseCode+")";
    		stmt.executeUpdate(sql);
    		
    		JSONObject jobj = new JSONObject(output);
    		String imei = jobj.getString("imei");
    		RMQResponseProducer.sendMessagesWithReplyAck("cadre_response_exchange",imei,output);
    		
    		return true;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public static void main(String[] args)throws Exception
    {
    	Class.forName("com.mysql.jdbc.Driver");
   	 	conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = conn.createStatement();
		
    	for(int i=0;i<5;i++)
    	{
    		RMQCadreConsumer consumer = new RMQCadreConsumer();
    		Thread thread = new Thread(consumer);
    		thread.start();
    	}
    }
}
