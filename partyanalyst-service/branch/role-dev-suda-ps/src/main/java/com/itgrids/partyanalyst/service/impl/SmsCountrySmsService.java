package com.itgrids.partyanalyst.service.impl;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.keys.PropertyKeys;
import com.itgrids.partyanalyst.service.IPartyAnalystPropertyService;
import com.itgrids.partyanalyst.service.ISmsService;

/*
 * Username: dakavaram
 * Password: 19716044
 */
public class SmsCountrySmsService implements ISmsService {

	private static final Logger log = Logger
			.getLogger(SmsCountrySmsService.class);

	private IPartyAnalystPropertyService propertyService;

	public IPartyAnalystPropertyService getPropertyService() {
		return propertyService;
	}

	public void setPropertyService(IPartyAnalystPropertyService propertyService) {
		this.propertyService = propertyService;
	}
	
	public void sendSms(String message, boolean isEnglish,
			String... phoneNumbers) {
		HttpClient client = null;
		PostMethod post = null;
		
		client = new HttpClient(new MultiThreadedHttpConnectionManager());

		/* SETUP PROXY */
		/*if (propertyService.getProperty(PropertyKeys.SERVER_PROXY_HOST) != null && propertyService.getProperty(PropertyKeys.SERVER_PROXY_HOST).trim().length() > 0) {
			int port = 8080;
			try {
				port = Integer.parseInt(propertyService
						.getProperty(PropertyKeys.SERVER_PROXY_PORT));
			} catch (NumberFormatException nfe) {
				log.error(nfe);
			}
			client.getHostConfiguration().setProxy(propertyService.getProperty(PropertyKeys.SERVER_PROXY_HOST), port);
		}*/

		/*client.getHttpConnectionManager().getParams().setConnectionTimeout(
				Integer.parseInt(propertyService.getProperty(
								PropertyKeys.SMS_SMSCOUNTRY_CONNECTION_TIMEOUT,"30000")));*/
		client.getHttpConnectionManager().getParams().setConnectionTimeout(
				Integer.parseInt("30000"));

		/*post = new PostMethod(propertyService
				.getProperty(PropertyKeys.SMS_SMSCOUNTRY_SERVICE_URL));*/
		post = new PostMethod("http://www.smscountry.com/SMSCwebservice.asp");
		// give all in string
		/*post.addParameter("User", propertyService
				.getProperty(PropertyKeys.SMS_SMSCOUNTRY_USER));
		post.addParameter("passwd", propertyService
				.getProperty(PropertyKeys.SMS_SMSCOUNTRY_PASSWORD));*/
		
		post.addParameter("User", "dakavaram");
		post.addParameter("passwd", "nellore");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < phoneNumbers.length; i++) {
			sb.append(phoneNumbers[i]);
			if (i < (phoneNumbers.length-1))
				sb.append(",");
		}
	/*	System.out.println("Using "+propertyService
				.getProperty(PropertyKeys.SMS_SMSCOUNTRY_USER)+propertyService
				.getProperty(PropertyKeys.SMS_SMSCOUNTRY_PASSWORD)+" for "+sb);*/
		post.addParameter("mobilenumber", sb.toString());
		post.addParameter("message", message);/*
		post.addParameter("sid", propertyService
				.getProperty(PropertyKeys.SMS_SMSCOUNTRY_SID));*/
		post.addParameter("sid", "SMSCountry");
		post.addParameter("mtype", isEnglish ? "N" : "OL");
		/*post.addParameter("DR", propertyService
				.getProperty(PropertyKeys.SMS_SMSCOUNTRY_DR));*/
		post.addParameter("DR", "YES");

		/* PUSH the URL */
		try {
			int statusCode = client.executeMethod(post);
			if(log.isInfoEnabled()){
				log.info(post.getStatusLine().toString());
				System.out.println(post.getStatusLine().toString()+"***"+statusCode+"*****"+post.getQueryString());
			}
			if (statusCode != HttpStatus.SC_OK) {
				log.error("SmsCountrySmsService.sendSMS failed: "
						+ post.getStatusLine());
				System.out.println("SmsCountrySmsService.sendSMS failed: "
						+ post.getStatusLine());
			}
			if(log.isInfoEnabled()){
				log.info(post.getResponseBodyAsString());
				System.out.println(post.getResponseBodyAsString());
			}
		} catch (Exception e) {
			log.error(e);
			System.out.println(e);
		} finally {
			post.releaseConnection();
		}

	}
}
