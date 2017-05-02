package com.itgrids.partyanalyst.service.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.utils.IConstants;

public class GovtSMSAPIService {
	private final static Logger LOG =  Logger.getLogger(GovtSMSAPIService.class);
	
	public ResultStatus senedSMSForGovtAlert(String mobileNos,String message){
		ResultStatus rs = new ResultStatus();
		try {
			if(!IConstants.DEPLOYED_HOST.equalsIgnoreCase("tdpserver"))
				return null;
			
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
	
	public static void main(String[] args)
	{
		try{
			GovtSMSAPIService senedSMSForGovtAlert = new GovtSMSAPIService();
			
			BufferedReader reader = new BufferedReader(new FileReader("E:\\RWS_LOGIN.txt"));
			String str = "";
			int index = 0;
			int index2 = 0;
			
			while((str = reader.readLine()) != null )
			{  
				try{
					index++;
					//System.out.println(index+")"+str);
					String[] arr = str.split("\t");
					
					if(arr != null && arr.length == 3)
					{
						index2++;
						String username = arr[0].trim();
						String password = arr[1].trim();
						String mobile = arr[2].trim();
						
						//System.out.println(index2+") username : "+username+"\tpassword : "+password+"\tmobile : "+mobile);
						
						if(mobile.length() == 10 && index2 == 873)
						{
							System.out.println(index+"-"+index2+"- Sending Mesg for - "+mobile);
							senedSMSForGovtAlert.senedSMSForGovtAlert(mobile,"Your existing login details are changed for " +
									"WWW.MYDEPARTMENTS.IN, Please use the following logins, Username : "+username+", Password : "+password+".");
						}
					}
				}catch(Exception e)
				{
					e.printStackTrace();
				}
		    }  
			reader.close();    
			//senedSMSForGovtAlert.senedSMSForGovtAlert("9966542524","Your existing login details are changed for WWW.MYDEPARTMENTS.IN, please use the following logins, username: kamal,password:kamal. ");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
