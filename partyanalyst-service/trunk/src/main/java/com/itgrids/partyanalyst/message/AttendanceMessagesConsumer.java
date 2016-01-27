package com.itgrids.partyanalyst.message;

import org.apache.log4j.Logger;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.core.task.TaskExecutor;

import com.itgrids.partyanalyst.dto.AttendanceVO;
import com.itgrids.partyanalyst.service.IAttendanceService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class AttendanceMessagesConsumer {

	private static final Logger LOG = Logger.getLogger(AttendanceMessagesConsumer.class);
	ConnectionFactory factory = new ConnectionFactory();
	private TaskExecutor taskExecutor;
	private IAttendanceService attendanceService;
	
	public void setAttendanceService(IAttendanceService attendanceService) {
		this.attendanceService = attendanceService;
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
			LOG.info("in Run()");
			factory.setUsername(IConstants.RMQ_USERNAME);
			factory.setPassword(IConstants.RMQ_PASSWORD);
			factory.setVirtualHost(IConstants.RMQ_VIRTUALHOST);
			factory.setHost(IConstants.RMQ_HOST);
			factory.setPort(IConstants.RMQ_PORT);
			
			 Connection conn = factory.newConnection();
		     Channel channel = conn.createChannel();
		     boolean durable = true;
		     
		     channel.exchangeDeclare(IConstants.ATTENDANCE_RMQ_EXCHANGE, "direct", durable);
		     channel.queueDeclare(IConstants.ATTENDANCE_RMQ_QUEUE, durable,false,false,null);
		     channel.queueBind(IConstants.ATTENDANCE_RMQ_QUEUE, IConstants.ATTENDANCE_RMQ_EXCHANGE, IConstants.ATTENDANCE_ROUTING_KEY);
		     boolean noAck = false;
	      
		     QueueingConsumer consumer = new QueueingConsumer(channel);
		     channel.basicConsume(IConstants.ATTENDANCE_RMQ_QUEUE, noAck, consumer);
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
    		
    		AttendanceVO inputVO = new AttendanceVO();
    		AttendanceVO resultVO = new AttendanceVO();
    		
    		inputVO.setTrainingCampScheduleId(dataObj.getLong("trainingCampScheduleId"));
    		inputVO.setTrainingCampBatchId(dataObj.getLong("trainingCampBatchId"));
    		inputVO.setTrainingCampTopicId(dataObj.getLong("trainingCampTopicId"));
    		inputVO.setType(dataObj.getString("type"));
    		inputVO.setPartyMeetingId(dataObj.getLong("partyMeetingId"));
    		inputVO.setMembershipId(dataObj.getString("membershipId"));
    		inputVO.setAttendedTime(dataObj.getString("attendedTime"));
    		inputVO.setRfid(dataObj.getString("rfid"));
    		inputVO.setImei(dataObj.getString("imei"));
    		inputVO.setUniqueKey(dataObj.getString("uniqueKey"));
    		inputVO.setLatitude(dataObj.getString("latitude"));
    		inputVO.setLongitude(dataObj.getString("longitude"));
    		inputVO.setTabUserId(dataObj.getLong("tabUserId"));
    		inputVO.setCurrentTabUserId(dataObj.getLong("currentTabUserId"));
    		inputVO.setSyncSource("RMQ");
    		
    		resultVO = attendanceService.saveAttendance(inputVO);
    		
    		if(resultVO != null)
    		{
    			System.out.println("status - "+resultVO.getStatus()+" Unique Key --> "+resultVO.getUniqueKey());
    		}
    		return true;
    	}catch(Exception e)
    	{
    		LOG.error("Exception Occured in doWork Mathod - ",e);
    		return false;
    	}
    }
   }
	
}
