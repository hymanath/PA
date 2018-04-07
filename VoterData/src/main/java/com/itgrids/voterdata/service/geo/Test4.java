package com.itgrids.voterdata.service.geo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;




public class Test4 {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
	
		String phoneNumbers[] =new String[2];
		phoneNumbers[0] = "7416308674";
		phoneNumbers[1] = "7416308674";
		String s = "యజమాని పేరు,\n"
		         + "రాష్ట్ర ముఖ్యమంత్రి నారా చంద్రబాబు నాయుడు గారి ఆదేశాల మేరకు ఇంటింటికి తెలుగుదేశం కార్యక్రమంలో భాగంగా మీ ఇంటికి రావడం జరిగింది.\n"
		         + "ఆ సమయంలో మీరు అందుబాటులో లేని కారణంగా మిమ్మల్ని కలవలేకపోవడం అయినది,\n"
		         + "మీకు ఏమైనా సమస్య ఉన్నచో మమ్మల్ని కలవగలరు,\n\n\n"
		         + "ఎమ్మెల్యే పేరు:Santosh Kumar Verma,\n"
		         + "ఫోన్ నెం";
		String name = URLEncoder.encode("Santosh", "UTF-8");
		Long statusCode = senedSMSForGovtAlert("917416308674",s);
		System.out.println(statusCode);
		//boolean b = sendSmsForAssignedLeaderInTelugu("యజమాని పేరు","917416308674");
		//System.out.println(b);
		
		
	}
    public static boolean sendSmsForAssignedLeaderInTelugu(String message,String mobilenumber)  
	{
	    
	    try {
	    	
	    	String postData="";
			String retval = "";
			//give all Parameters In String String User ="User_Name";
			String passwd = "9885410393";
			//String mobilenumber = mobileNo;
			//String message = msg;
			String sid = "16242";
			String userName = "koffeemedia";
			String mtype = "OL";
			String DR = "Y";
			postData += "User=" + URLEncoder.encode(userName,"UTF-8") + "&passwd=" + passwd + "&mobilenumber=" + mobilenumber + "&message=" + URLEncoder.encode(message,"UTF-8") + "&sid=" + sid + "&mtype=" + mtype + "&DR=" + DR;
			URL url = new URL("http://smscountry.com/SMSCwebservice_Bulk.aspx");
			//URL url = new URL("http://smscountry.com/SMSCwebservice_Bulk.aspx");
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("POST");
			urlconnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			urlconnection.setDoOutput(true);
			urlconnection.setDoInput(true);
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
    public static Long senedSMSForGovtAlert(String mobileNos,String message){
		Long statusCode=0l;
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
			statusCode = 1l;
			e.printStackTrace();
		}
		return statusCode;
	}
    /*private ResultStatus sendSmsToMember(String message, boolean isEnglish,String... phoneNumbers){
		ResultStatus resultStatus = new ResultStatus();
		try
		{  
			HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
			client.getHttpConnectionManager().getParams().setConnectionTimeout(Integer.parseInt("30000"));

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < phoneNumbers.length; i++) {
				if(phoneNumbers[i].toString().trim().length()>0)
				{
					sb.append("91");
					sb.append(phoneNumbers[i]);
					if (i < (phoneNumbers.length-1))
						sb.append(",");
				}
			}
			
			//PostMethod post = new PostMethod("http://sms.partyanalyst.com/WebserviceSMS.aspx");
			PostMethod post = new PostMethod("http://smscountry.com/SMSCwebservice_Bulk.aspx");
			
			post.addParameter("User",IConstants.ADMIN_USERNAME_FOR_SMS_FOR_OTP);
			post.addParameter("passwd",IConstants.ADMIN_PASSWORD_FOR_SMS_FOR_OTP);
			//post.addParameter("sid",IConstants.ADMIN_SENDERID_FOR_SMS);
		    post.addParameter("mobilenumber", sb.toString());
			post.addParameter("message", message);
			post.addParameter("mtype", isEnglish ? "N" : "OL");
			post.addParameter("DR", "Y");
			try 
			{
				int statusCode = client.executeMethod(post);
				if (statusCode != HttpStatus.SC_OK) {
					LOG.error("SmsCountrySmsService.sendSMS failed: "+ post.getStatusLine());
				}else{
					resultStatus.setResultCode(2);
				}
					
			}catch (Exception e) {
			    LOG.error(e);
				resultStatus.setResultCode(1);
				resultStatus.setExceptionEncountered(e);
			} finally {
				post.releaseConnection();
			}
			return resultStatus;
		}catch (Exception e) {
			LOG.error("Exception Occured in sendSmsToMember() method - "+e);
			resultStatus.setResultCode(1);
			resultStatus.setExceptionEncountered(e);
			return resultStatus;
		}
	}*/
}
