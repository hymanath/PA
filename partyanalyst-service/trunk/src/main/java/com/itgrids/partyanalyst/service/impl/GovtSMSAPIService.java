package com.itgrids.partyanalyst.service.impl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dto.ResultStatus;

public class GovtSMSAPIService {
	private final static Logger LOG =  Logger.getLogger(GovtSMSAPIService.class);
	
	public ResultStatus senedSMSForGovtAlert(String mobileNos,String message){
		ResultStatus rs = new ResultStatus();
		try {
			String username = "APGOVT";
			String password = "esd@123";
			String senderid = "GOVTAP";
			HttpURLConnection connection = null;
			
			URL url = new URL("http://msdgweb.mgov.gov.in/esms/sendsmsrequest");
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setFollowRedirects(true);
			
			String finalmessage = "";
			String sss = "";
			char ch = 0;

			for(int i = 0 ; i<message.length();i++){
				 ch = message.charAt(i);
				int j = (int) ch;
				sss = "&#"+j+";";
				finalmessage = finalmessage+sss;
			}
			System.out.println("ddd"+finalmessage);

			message=finalmessage;
			System.out.println("unicoded message=="+message);
			
			String smsservicetype = "unicodemsg"; // For bulk msg
			String query = "username=" + URLEncoder.encode(username)
				+ "&password=" + URLEncoder.encode(password)
				+ "&smsservicetype=" + URLEncoder.encode(smsservicetype)
				+ "&content=" + URLEncoder.encode(message) 
				+ "&bulkmobno=" + URLEncoder.encode(mobileNos, "UTF-8") 
				+ "&senderid=" + URLEncoder.encode(senderid);

			connection.setRequestProperty("Content-length", String.valueOf(query.length()));
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			connection.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

			// open up the output stream of the connection
			DataOutputStream output = new DataOutputStream(connection.getOutputStream());

			// write out the data
			int queryLength = query.length();
			output.writeBytes(query);

			// get ready to read the response from the cgi script
			DataInputStream input = new DataInputStream(connection.getInputStream());

			// read in each character until end-of-stream is detected
			for (int c = input.read(); c != -1; c = input.read())
				System.out.print((char) c);
			input.close();
			
		} catch (Exception e) {
			LOG.error("Exception raised while sending message", e);
		}
		return rs;
	}
}
