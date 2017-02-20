package com.itgrids.partyanalyst.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserVoterDAO;
import com.itgrids.partyanalyst.dao.ISmsHistoryDAO;
import com.itgrids.partyanalyst.dao.ISmsHistoryMobileNoDAO;
import com.itgrids.partyanalyst.dao.ISmsModuleDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dto.CadreVoterVO;
import com.itgrids.partyanalyst.dto.MobileAppUserVoterVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.MobileAppUserVoter;
import com.itgrids.partyanalyst.model.SmsHistory;
import com.itgrids.partyanalyst.model.SmsHistoryMobileNo;
import com.itgrids.partyanalyst.model.SmsModule;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ISmsGatewayService;
import com.itgrids.partyanalyst.service.ISmsSenderService;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;


public class SmsSenderService implements ISmsSenderService{
	
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private static final Logger LOG = Logger.getLogger(SmsSenderService.class);
	private TaskExecutor taskExecutor;
	private ICadreRegistrationService cadreRegistrationService;
	private ISmsGatewayService smsGatewayService;
	private ISmsService smsCountrySmsService;
	private IMobileAppUserVoterDAO mobileAppUserVoterDAO;
	private ISmsModuleDAO smsModuleDAO;
	private ISmsHistoryDAO smsHistoryDAO;
	private TransactionTemplate transactionTemplate = null;
	private DateUtilService dateUtilService = new DateUtilService();
	private IUserDAO userDAO;
	private ISmsHistoryMobileNoDAO smsHistoryMobileNoDAO;

	
	public ISmsHistoryMobileNoDAO getSmsHistoryMobileNoDAO() {
		return smsHistoryMobileNoDAO;
	}

	public void setSmsHistoryMobileNoDAO(
			ISmsHistoryMobileNoDAO smsHistoryMobileNoDAO) {
		this.smsHistoryMobileNoDAO = smsHistoryMobileNoDAO;
	}

	public ISmsModuleDAO getSmsModuleDAO() {
		return smsModuleDAO;
	}

	public void setSmsModuleDAO(ISmsModuleDAO smsModuleDAO) {
		this.smsModuleDAO = smsModuleDAO;
	}

	public ISmsHistoryDAO getSmsHistoryDAO() {
		return smsHistoryDAO;
	}

	public void setSmsHistoryDAO(ISmsHistoryDAO smsHistoryDAO) {
		this.smsHistoryDAO = smsHistoryDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public ISmsService getSmsCountrySmsService() {
		return smsCountrySmsService;
	}

	public void setSmsCountrySmsService(ISmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}

	public IMobileAppUserVoterDAO getMobileAppUserVoterDAO() {
		return mobileAppUserVoterDAO;
	}

	public void setMobileAppUserVoterDAO(
			IMobileAppUserVoterDAO mobileAppUserVoterDAO) {
		this.mobileAppUserVoterDAO = mobileAppUserVoterDAO;
	}

	public ISmsGatewayService getSmsGatewayService() {
		return smsGatewayService;
	}

	public void setSmsGatewayService(ISmsGatewayService smsGatewayService) {
		this.smsGatewayService = smsGatewayService;
	}

	public ICadreRegistrationService getCadreRegistrationService() {
		return cadreRegistrationService;
	}

	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}

	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public ResultStatus sendSmsToCadre(MobileAppUserVoterVO inputVO)	{
		
		LOG.info("Entered in to sendSmsToCadre() method ");
		ResultStatus result = new ResultStatus();
		try{
				String token = inputVO.getVersionNo();
			    LOG.fatal("Messaging Started -- at --> "+new Date());
			    cadreRegistrationService.sendSMS("9966542524","Messaging started At - "+new Date()+" Token - "+token);
			    
				List<CadreVoterVO> resultList = new ArrayList<CadreVoterVO>();
				List<Object[]> list = boothPublicationVoterDAO.getCadreVoterInfo(inputVO.getWardId(),inputVO.getFirstResult(),inputVO.getMaxResults());
				
				LOG.fatal("Total Mobile Nos are --> "+list.size());
				cadreRegistrationService.sendSMS("9966542524","Total Mobile Nos are --> "+list.size()+" Token - "+token);
				
				if(list != null && list.size() > 0)
				{
					 for(Object[] params : list)
					 {
						 CadreVoterVO vo = new CadreVoterVO();
						 vo.setPartNo(params[0] != null ? params[0].toString() : "");
						 vo.setSerialNo(params[1] != null ? params[1].toString() : "");
						 vo.setLocation(params[2] != null ? params[2].toString() : "");
						 vo.setName(params[3] != null ? params[3].toString() : "");
						 vo.setRelativeName(params[4] != null ? params[4].toString() : "");
						 vo.setVoterIdCardNo(params[5] != null ? params[5].toString() : "");
						 vo.setWardNo(params[6] != null ? params[6].toString() : "");
						 vo.setMobileNum(params[7] != null ? params[7].toString() : "");
						 vo.setRelationShipType(params[8] != null ? params[8].toString() : "");
						 vo.setGender(params[9] != null ? params[9].toString() : "");
						 vo.setLatitude(params[10] != null ? params[10].toString() : "");
						 vo.setLongitude(params[11] != null ? params[11].toString() : "");
						 resultList.add(vo);
					}
				}
					 /*for(;;)
						{
							 if(startIndex >= resultList.size()-1)
								 break;
							 if(maxIndex >= resultList.size())
								 maxIndex = resultList.size() - 1;
							 startConsumeMessages(resultList.subList(startIndex, maxIndex));
							 startIndex = maxIndex;
							 maxIndex = maxIndex + maxIndex;
						}*/
					startConsumeMessages(resultList,token);
					result.setMessage("success");
				}
		catch (Exception e) {
			result.setMessage("fail");
			LOG.error("Exception Occured in sendSmsToCadre() method"+e);
			
		}
		return result;
		
	}
	
	
	public ResultStatus sendSmsToVoter(MobileAppUserVoterVO inputVO)	{
		
		LOG.info("Entered in to SendSmsToVoter() method ");
		ResultStatus result = new ResultStatus();
		try{
			Integer startIndex =0;
			Integer maxIndex = 5000;
				List<CadreVoterVO> resultList = new ArrayList<CadreVoterVO>();
				if(inputVO.getReqType().equalsIgnoreCase("call"))
				{
					List<Long> mobileAppUserVoterIds = mobileAppUserVoterDAO.mobileAppUserVoterIds(inputVO.getMobileNums());
					if(mobileAppUserVoterIds != null && mobileAppUserVoterIds.size() > 0)
					{
						for(Long id : mobileAppUserVoterIds)
						{
							MobileAppUserVoter voter = mobileAppUserVoterDAO.get(id);
							voter.setIsCalled("Y");
							mobileAppUserVoterDAO.save(voter);
						}
					}
					result.setMessage("success");
					return result;
				}
				else
				{
				List<Object[]> list = boothPublicationVoterDAO.getVoterInfo(inputVO.getMobileNums());
					 if(list != null && list.size() > 0)
					 {
							 for(Object[] params : list)
							 {
								 CadreVoterVO vo = new CadreVoterVO();
								 vo.setPartNo(params[0] != null ? params[0].toString() : "");
								 vo.setSerialNo(params[1] != null ? params[1].toString() : "");
								 vo.setLocation(params[2] != null ? params[2].toString() : "");
								 vo.setName(params[3] != null ? params[3].toString() : "");
								 vo.setRelativeName(params[4] != null ? params[4].toString() : "");
								 vo.setVoterIdCardNo(params[5] != null ? params[5].toString() : "");
								 vo.setWardNo(params[6] != null ? params[6].toString() : "");
								 vo.setMobileNum(params[7] != null ? params[7].toString() : "");
								 vo.setRelationShipType(params[8] != null ? params[8].toString() : "");
								 vo.setGender(params[9] != null ? params[9].toString() : "");
								 resultList.add(vo);
							 }
					 }
					 
					 /*for(;;)
						{
							 if(startIndex >= resultList.size()-1)
								 break;
							 if(maxIndex >= resultList.size())
								 maxIndex = resultList.size() - 1;
							 startConsumeMessages(resultList.subList(startIndex, maxIndex));
							 startIndex = maxIndex;
							 maxIndex = maxIndex + maxIndex;
						 
					  }*/
					 
					 startConsumeMessages(resultList,"");
					 result.setMessage("success");
				}
		}
		catch (Exception e) {
			result.setMessage("fail");
			LOG.error("Exception Occured in sendSmsToCadre() method"+e);
			
		}
		return result;
		
	}
	
	public void startConsumeMessages(List<CadreVoterVO> resultList,String token)
	{
		int count = 0;
		for(CadreVoterVO vo : resultList){
		try{
			   StringBuilder sb = new StringBuilder();
			   
			   String voterName = vo.getName() != null ? vo.getName().toString() : "";
			   String voterIdCardNo = vo.getVoterIdCardNo() != null ? vo.getVoterIdCardNo().toString() : null;
			   String relativeName = vo.getRelativeName() != null ? vo.getRelativeName().toString() : null;
			   String relation = vo.getRelationShipType() != null ? vo.getRelationShipType().toString() : "";
			   String gender = vo.getGender();
			   String serialNo = vo.getSerialNo() != null ? vo.getSerialNo().toString() : null;
			   String latitude = (vo.getLatitude() != null && vo.getLatitude().trim().length() > 0) ? vo.getLatitude() .toString() : null;
			   String longitude = (vo.getLongitude() != null && vo.getLongitude().trim().length() > 0) ? vo.getLongitude().toString() : null;
			   String location = vo.getLocation()!= null ? vo.getLocation().toString() : null;
			   String relationStr = "C/O";
					   
			   sb.append("Name : "+voterName+"\n");
			   
			   if(relation.equalsIgnoreCase("Father") || relation.equalsIgnoreCase("Mother"))
			   {
				   if(gender.equalsIgnoreCase("M"))
					   relationStr = "S/O";
				   else
					   relationStr = "D/O";
			   }
			   else if(relation.equalsIgnoreCase("Husband") && gender.equalsIgnoreCase("F"))
				   relationStr = "W/O";
			   
			   if(relativeName != null)
				   sb.append(relationStr+" : "+relativeName+"\n");
			   
			   if(voterIdCardNo != null)
				   sb.append("EPIC ID : "+voterIdCardNo+"\n");
			   
			   if(serialNo != null)
				   sb.append("Serial No : "+serialNo+"\n");
			   
			   sb.append("Booth No : "+vo.getPartNo().toString()+"\n");
			   
			   if(location != null)
			   sb.append("Location : "+location+"\n");
			   sb.append("Vote on 02-FEB-2016 07:00 AM - 05:00 PM.\n");
			   sb.append("\nVote For TDP\n\n");
			   
			   if(latitude != null && longitude != null)
			   {
				   String url = "http://maps.google.com/maps?saddr=Current+Location&daddr="+latitude+","+longitude;
				   sb.append("Route to Polling Station\n");
				   sb.append(url);
			   }
			   //String result = smsGatewayService.sendSMS(vo.getMobileNum(),sb.toString(),IConstants.ADMIN_USERNAME_FOR_SMS,IConstants.ADMIN_PASSWORD_FOR_SMS);
			   String result = smsGatewayService.sendSMS(vo.getMobileNum(),sb.toString(),IConstants.ADMIN_USERNAME_FOR_SMS,IConstants.ADMIN_PASSWORD_FOR_SMS);
			   count++;
			   LOG.fatal(count+")Mobile Number - for Ward "+token+" "+vo.getMobileNum()+" Status - "+result+" at -"+new Date());
			   
			   if(count%10000 == 0)
				   cadreRegistrationService.sendSMS("9966542524","Out of "+resultList.size()+" Messages "+count+" are delivered @"+new Date()+" Token - "+token);
				   
			}catch(Exception e)
			{
				LOG.error("Exception in Creating a thread");
				LOG.error(e);
			}
		}
		cadreRegistrationService.sendSMS("9966542524","Message Sending Completed for Ward - "+token+", Total are - "+resultList.size()+" Messages "+count+" are delivered @"+new Date());
	}
	public String sendSMSForCader(String message,String phoneNumbers)
	{
		try{
			//cadreRegistrationService.sendSMS(phoneNumbers,message);
			return smsGatewayService.sendSMS(phoneNumbers,message,IConstants.ADMIN_USERNAME_FOR_SMS,IConstants.ADMIN_PASSWORD_FOR_SMS);		 
			
		}catch (Exception e) {
			LOG.error(e);
			return "Failure";
		}
	}
	
	/**
	   *  This Method will Send SMS to given Mobile Numbers From Admin
	   *  @param String message
	   *  @param String moduleName
	   *  @param String[] phoneNumbers
	   *  @author Srishailam Pittala
	   *  @return Long ResultStatus
	   *  
	   */
	
	public String sendSMSForTrainingCampFeedBackMember(Long userId,String moduleName ,boolean isEnglish, String messageStr,String mobileNos)
	{
	    
	    try {
	     String[] mobileNosStr = mobileNos.split(",");
	      String mobilenumber = "";
	      if(mobileNosStr != null && mobileNosStr.length >0){
	        for (int i = 0; i < mobileNosStr.length; i++) {
	          if(i>0)
	            mobilenumber = mobilenumber+","+mobileNosStr[i];
	          else
	            mobilenumber = mobileNosStr[i];
	        }
	      }
	      
	     saveSmsHistoryDetails(messageStr,userId,moduleName,mobilenumber);
	      String message = messageStr;
	  	  
			HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
			client.getHttpConnectionManager().getParams().setConnectionTimeout(
				Integer.parseInt("30000"));
			
			PostMethod post = new PostMethod("http://smscountry.com/SMSCwebservice_Bulk.aspx");
			
			post.addParameter("User",IConstants.ADMIN_USERNAME_FOR_SMS);
			post.addParameter("passwd",IConstants.ADMIN_PASSWORD_FOR_SMS);
			post.addParameter("sid",IConstants.ADMIN_SENDERID_FOR_SMS);
		    post.addParameter("mobilenumber", mobilenumber);
			post.addParameter("message", message);
			post.addParameter("mtype", isEnglish ? "N" : "OL");
			post.addParameter("DR", "Y");
			
			/* PUSH the URL */
			int statusCode = client.executeMethod(post);
			
			if (statusCode != HttpStatus.SC_OK) {
				LOG.error("SmsCountrySmsService.sendSMS failed: "+ post.getStatusLine());
			     return IConstants.FAILURE;
			}
			else
				return IConstants.SUCCESS;
	    } catch (Exception e) {
	    	 return IConstants.FAILURE;
	    }
	    
	  }
	
	public SmsHistory sendSMS(Long userId,String moduleName ,boolean isEnglish, String message,String mobilenumber)
	{
	    
	    try {
	    	 if(mobilenumber.trim().length() > 0)
	    	 {
	    		 if(mobilenumber.endsWith(","))
	    			 mobilenumber = mobilenumber.substring(0,mobilenumber.length()-1);
	    		 
	    		 SmsHistory smsHistory = saveInSmsHistoryDetails(message,userId,moduleName,mobilenumber.split(","));
	    		 
	    		 HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
	    		 client.getHttpConnectionManager().getParams().setConnectionTimeout(
	    				 Integer.parseInt("30000"));
				
	    		 PostMethod post = new PostMethod("http://smscountry.com/SMSCwebservice_Bulk.aspx");
	    		 
	    		 post.addParameter("User",IConstants.ADMIN_USERNAME_FOR_SMS);
	    		 post.addParameter("passwd",IConstants.ADMIN_PASSWORD_FOR_SMS);
	    		 post.addParameter("sid",IConstants.ADMIN_SENDERID_FOR_SMS);
	    		 post.addParameter("mobilenumber", mobilenumber);
	    		 post.addParameter("message", message);
	    		 post.addParameter("mtype", isEnglish ? "N" : "OL");
	    		 post.addParameter("DR", "Y");
				
				/* PUSH the URL */
				int statusCode = client.executeMethod(post);
				
				if (statusCode != HttpStatus.SC_OK) {
					LOG.error("SmsCountrySmsService.sendSMS failed: "+ post.getStatusLine());
				     return null;
				}
				else
					return smsHistory;
	    	}
	    	 else
	    		 return null;
	    } catch (Exception e) {
	    	 return null;
	    }
	  }
	
	public SmsHistory saveInSmsHistoryDetails(final String message,final Long userId,final String moduleName,final String... phoneNumbers)
	{
		try{
			
			SmsModule smsModule = null;
			
			List<SmsModule> list = smsModuleDAO.findByModuleName(moduleName);
			
			if(list != null && list.size() > 0)
				smsModule = list.get(0);
			
			SmsHistory smsHistory = new SmsHistory();
			smsHistory.setUserId(userId);
			smsHistory.setSentDate(dateUtilService.getCurrentDateAndTimeInStringFormat());
			smsHistory.setSmsModule(smsModule);
			smsHistory.setSmsContent(message);
			smsHistory = smsHistoryDAO.save(smsHistory);
			
			for(String mobileNo : phoneNumbers)
			{
				SmsHistoryMobileNo smsHistoryMobileNo = new SmsHistoryMobileNo();
				smsHistoryMobileNo.setSmsHistory(smsHistory);
				smsHistoryMobileNo.setMobileNumber(mobileNo.trim());
				smsHistoryMobileNo.setInsertedTime(dateUtilService.getCurrentDateAndTimeInStringFormat());
				smsHistoryMobileNo = smsHistoryMobileNoDAO.save(smsHistoryMobileNo);
				smsHistory = smsHistoryMobileNo.getSmsHistory();
			}
			return smsHistory;
			
		}catch (Exception e) {
			LOG.error("Error Occured in Saving SMS Data, Exception is - "+e);
			return null;
		}
	}
	
	public Long  saveSmsHistoryDetails(final String message,final Long userId,final String moduleName,final String... phoneNumbers)
	{
		try{
			
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
			
			SmsModule smsModule = null;
			
			List<SmsModule> list = smsModuleDAO.findByModuleName(moduleName);
			
			if(list != null && list.size() > 0)
				smsModule = list.get(0);
			
			SmsHistory smsHistory = new SmsHistory();
			smsHistory.setUserId(userId);
			//smsHistory.setMobileNumber(mobileNo);
			//smsHistory.setSmsContent(message);
			smsHistory.setSentDate(dateUtilService.getCurrentDateAndTimeInStringFormat());
			smsHistory.setSmsModule(smsModule);
			SmsHistory smsHistoryObj = smsHistoryDAO.save(smsHistory);
			
			for(String mobileNo : phoneNumbers)
			{
				SmsHistoryMobileNo smsHistoryMobileNo = new SmsHistoryMobileNo();
				smsHistoryMobileNo.setSmsHistory(smsHistoryObj);
				smsHistoryMobileNo.setMobileNumber(mobileNo.trim());
				smsHistoryMobileNo.setInsertedTime(dateUtilService.getCurrentDateAndTimeInStringFormat());
				smsHistoryMobileNoDAO.save(smsHistoryMobileNo);
			}
			
				}
			});
			return (long)ResultCodeMapper.SUCCESS;
		}catch (Exception e) {
			LOG.error("Error Occured in Saving SMS Data, Exception is - "+e);
			return (long)ResultCodeMapper.FAILURE;
		}
	}
	public boolean sendSmsForAssignedLeaderInTelugu(String message, boolean isEnglish,String mobilenumber)  
	{
	    
	    try {
	    	
	    	if(!IConstants.DEPLOYED_HOST.equalsIgnoreCase("tdpserver"))
	    		return false;
	    	
	    	String postData="";
			String retval = "";
			//give all Parameters In String String User ="User_Name";
			String passwd = IConstants.ADMIN_PASSWORD_FOR_SMS;
			//String mobilenumber = mobileNo;
			//String message = msg;
			String sid = IConstants.ADMIN_SENDERID_FOR_SMS;
			String mtype = "OL";
			String DR = "Y";
			postData += "User=" + URLEncoder.encode(IConstants.ADMIN_USERNAME_FOR_SMS,"UTF-8") + "&passwd=" + passwd + "&mobilenumber=" + mobilenumber + "&message=" + URLEncoder.encode(message,"UTF-8") + "&sid=" + sid + "&mtype=" + mtype + "&DR=" + DR;
			URL url = new URL("http://smscountry.com/SMSCwebservice_Bulk.aspx");
			//URL url = new URL("http://smscountry.com/SMSCwebservice_Bulk.aspx");
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("POST");
			urlconnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			urlconnection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(urlconnection.getOutputStream());
			out.write(postData);
			out.close();
			BufferedReader in = new BufferedReader( new InputStreamReader(urlconnection.getInputStream()));
			String decodedString;
			while ((decodedString = in.readLine()) != null) {
			retval += decodedString;
			}
			in.close();
			System.out.println(retval);
			return true;
			
	    } catch (Exception e) {
	    	 return false;
	    }
	  }
}
