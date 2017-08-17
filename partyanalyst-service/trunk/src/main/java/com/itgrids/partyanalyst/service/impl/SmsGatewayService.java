package com.itgrids.partyanalyst.service.impl;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.service.ISmsGatewayService;
import com.itgrids.partyanalyst.utils.IConstants;

public class SmsGatewayService implements ISmsGatewayService{

	private static final Logger LOG = Logger.getLogger(SmsGatewayService.class);
	
	public String sendSMS(String mobileNo,String message,String username,String password)
	{

		if(!IConstants.DEPLOYED_HOST.equalsIgnoreCase("tdpserver"))
			return "success";
			
		HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
		client.getHttpConnectionManager().getParams().setConnectionTimeout(
			Integer.parseInt("30000"));
	
		boolean isEnglish = true;
		
		PostMethod post = new PostMethod("http://smscountry.com/SMSCwebservice_Bulk.aspx");
		
		post.addParameter("User",username);
		post.addParameter("passwd",password);
		//post.addParameter("sid",IConstants.ADMIN_SENDERID_FOR_SMS);
	    post.addParameter("mobilenumber", mobileNo);
		post.addParameter("message", message);
		post.addParameter("mtype", isEnglish ? "N" : "OL");
		post.addParameter("DR", "Y");
		
		/* PUSH the URL */
		try 
		{
			int statusCode = client.executeMethod(post);
			
			if (statusCode != HttpStatus.SC_OK) {
				LOG.error("SmsCountrySmsService.sendSMS failed: "+ post.getStatusLine());
				return "error";
			}
			else{
				return "success";
			}

		}catch (Exception e) {
				LOG.error("Exception rised in sending sms while cadre registration",e);
				return "exception";
		} finally {
				post.releaseConnection();
		}
	}
}
