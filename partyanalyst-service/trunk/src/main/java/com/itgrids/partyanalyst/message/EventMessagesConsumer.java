package com.itgrids.partyanalyst.message;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.core.task.TaskExecutor;

import com.itgrids.partyanalyst.dto.UserEventDetailsVO;
import com.itgrids.partyanalyst.service.IWebServiceHandlerService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class EventMessagesConsumer {

	private static final Logger LOG = Logger.getLogger(EventMessagesConsumer.class);
	ConnectionFactory factory = new ConnectionFactory();
	private TaskExecutor taskExecutor;
	private IWebServiceHandlerService webServiceHandlerService;
	
	public void setWebServiceHandlerService(
			IWebServiceHandlerService webServiceHandlerService) {
		this.webServiceHandlerService = webServiceHandlerService;
	}

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public void startConsumeMessages(Integer noOfConnections)
	{
		for(int i=0;i<noOfConnections;i++){
		try{
			LOG.error(i+1+") Thread is Creating...............");
			taskExecutor.execute(new ConsumeMessage());
			}catch(Exception e)
			{
				LOG.error("Exception in Creating a thread");
				LOG.error(e);
			}
		}
	}
	
	private class ConsumeMessage implements Runnable 
	{
		public void run()
		{
		try{
			System.out.println("in Run()");
			factory.setUsername(IConstants.RMQ_USERNAME);
			factory.setPassword(IConstants.RMQ_PASSWORD);
			factory.setVirtualHost(IConstants.RMQ_VIRTUALHOST);
			factory.setHost(IConstants.RMQ_HOST);
			factory.setPort(IConstants.RMQ_PORT);
			
			 Connection conn = factory.newConnection();
		     Channel channel = conn.createChannel();
		     boolean durable = true;
		     
		     channel.exchangeDeclare(IConstants.RMQ_EXCHANGE, "direct", durable);
		     channel.queueDeclare(IConstants.RMQ_QUEUE, durable,false,false,null);
		     channel.queueBind(IConstants.RMQ_QUEUE, IConstants.RMQ_EXCHANGE, IConstants.ROUTING_KEY);
		     boolean noAck = false;
	      
		     QueueingConsumer consumer = new QueueingConsumer(channel);
		     channel.basicConsume(IConstants.RMQ_QUEUE, noAck, consumer);
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
	         
		    		 String msg = new String(delivery.getBody());
		    		 
		    		 boolean result = doWork(msg);
		    		 if(result)
		    			 System.out.println("Success");
		    		 else
		    			 System.out.println("Failure");
		    		 
		    		 channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
		    	 }catch(Exception e)
		    	 {
		    		 e.printStackTrace();
		    	 }
		     }
		     channel.close();
		     conn.close();
			
		}catch(Exception e)
		{
			LOG.error(e);
		}
	}
	
	public boolean doWork(String message)
    {
    	try{
    		LOG.error("Entered into doWork with Message -->"+message);
    		JSONObject dataObj = new JSONObject(message);
    		
    		UserEventDetailsVO inputVO = new UserEventDetailsVO();
    		UserEventDetailsVO resultVO = new UserEventDetailsVO();
    		
    		inputVO.setMemberShipNo(dataObj.getString("memberShipNo"));
    		inputVO.setRFID(dataObj.getString("rfid"));
    		inputVO.setIMEI(dataObj.getString("imei"));
    		inputVO.setId(dataObj.getLong("id"));
    		inputVO.setEventId(dataObj.getLong("eventId"));
    		inputVO.setLoginTimeStamp(dataObj.getString("loginTimeStamp"));
    		inputVO.setStatus(dataObj.getString("status"));
    		inputVO.setTabPrimaryKey(dataObj.getLong("tabPrimaryKey"));
    		inputVO.setLatituede(dataObj.getString("latituede"));
    		inputVO.setLongitude(dataObj.getString("longitude"));
    		
    		resultVO = webServiceHandlerService.insertEventAttendeeInfo(inputVO);
    		
    		if(resultVO != null)
    		{
    			System.out.println("status - "+resultVO.getStatus()+" Tab Primary Kay --> "+resultVO.getTabPrimaryKey());
    		}
    		
    		return true;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		return false;
    	}
    }
   }
}
