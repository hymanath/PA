package com.itgrids.cadre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.json.JSONObject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class RMQCadreResponseConsumer{

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/rmq";
	static final String USER = "root";
	static final String PASS = "itg$36593^";
	
	static Connection conn = null;
	static Statement stmt = null;
	
    public static void main(String []args) throws Exception
    {
    	 Class.forName("com.mysql.jdbc.Driver");
    	 conn = DriverManager.getConnection(DB_URL,USER,PASS);
 		 stmt = conn.createStatement();
 		 
		 ConnectionFactory factory = new ConnectionFactory();
		 factory.setUsername("guest");
		 factory.setPassword("guest");
		 factory.setVirtualHost("/");
		 factory.setHost("localhost");
		 factory.setPort(5672);
		 
		 com.rabbitmq.client.Connection conn = factory.newConnection();
	     Channel channel = conn.createChannel();
	     String exchangeName = "cadre_response_exchange";
	     String queueName = "imei0";
	     String routingKey = "imei0";
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
	    		 
	    		  boolean result = saveData(msg);
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
      }
    public static boolean saveData(String msg)
    {
    	boolean result = false;
    	try{
    		JSONObject jobj = new JSONObject(msg);
    		
    		int backupId = jobj.getInt("backupId");
    		String refNo = jobj.getString("refNo");
    		String surveyTime = jobj.getString("surveyTime");
    		String saveStatus = jobj.getString("saveStatus");
    		int tdpCadreId = jobj.getInt("tdpCadreId");
    		int serverPrimaryKey = jobj.getInt("serverPrimaryKey");
    		String uniqueKey = jobj.getString("uniqueKey");
    		String exceptionText = "";
    		String imei = jobj.getString("imei");
    		
    		if(saveStatus.equalsIgnoreCase("Fail"))
    			exceptionText = jobj.getString("exceptionText");
    		
    		String sql = "INSERT INTO tdp_cadre_response(backup_id,ref_no,inserted_time,survey_time,save_status,tdp_cadre_id,"
    				+ "server_primary_key,unique_key,exception_text,imei) VALUES ('"+backupId+"','"+refNo+"',now(),'"+surveyTime+"',"
    				+ "'"+saveStatus+"','"+tdpCadreId+"','"+serverPrimaryKey+"','"+uniqueKey+"','"+exceptionText+"','"+imei+"')";
    		int inserted = stmt.executeUpdate(sql);
    		if(inserted > 0)
    			result = true;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return result;
    }
}
