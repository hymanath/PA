package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	 try{
		 //CookieHandler.setDefault( new CookieManager( null, CookiePolicy.ACCEPT_ALL) );
		String USER_AGENT = "Mozilla/5.0";
		String url = "http://epaper.andhrabhoomi.net/";
		 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
		con.setConnectTimeout(100000);
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		/* String key;
		for (int i = 1; (key = con.getHeaderFieldKey(i)) != null; i++) {
            System.out.println(key + ":" + con.getHeaderField(i));
        }*/
		String sessionId = con.getHeaderFields().get("Set-Cookie").get(0).replace(" path=/; HttpOnly", "");
		//
		String __VIEWSTATE ="";
		String __EVENTVALIDATION ="";
		
		String str = response.toString();
		System.out.println(sessionId);
	    Pattern pattern = Pattern.compile("<input type=\"hidden\" name=\"__VIEWSTATE\" id=\"__VIEWSTATE\" value=\"(.*?)\" />");
	    Matcher matcher = pattern.matcher(str);
	    while (matcher.find()) {
	    	__VIEWSTATE = matcher.group(1);
	    }
	    

	    Pattern pattern1 = Pattern.compile("<input type=\"hidden\" name=\"__EVENTVALIDATION\" id=\"__EVENTVALIDATION\" value=\"(.*?)\" />");
	    Matcher matcher1 = pattern1.matcher(str);
	    while (matcher1.find()) {
	    	__EVENTVALIDATION = matcher1.group(1);
	    }
	    Thread.sleep(10000);
	  String url1 = "http://epaper.andhrabhoomi.net/";
		 
	  URL obj1 = new URL(url1);
	    
	    HttpURLConnection con1 = (HttpURLConnection) obj1.openConnection();
	    
		//add reuqest header
	    con1.setDoInput(true);
	    con1.setDoOutput(true);
		con1.setRequestMethod("POST");
		con1.setUseCaches(false);
		con1.setAllowUserInteraction(false);
		con1.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		con1.setRequestProperty("Accept-Encoding","gzip, deflate");
		con1.setRequestProperty("Accept-Language","en-US,en;q=0.5");
		con1.setRequestProperty("Cache-Control","no-cache");
		con1.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
		//con1.setRequestProperty("Set-Cookie",sessionId);
		con1.setRequestProperty("Cookie",sessionId);
		//con1.setRequestProperty("Content-Length","3826");
		con1.setRequestProperty("Host","epaper.andhrabhoomi.net");
		con1.setRequestProperty("Referer","http://epaper.andhrabhoomi.net/");
		con1.setRequestProperty("User-Agent","Mozilla/5.0");
		con1.setRequestProperty("X-MicrosoftAjax","Delta=true");
		con1.setRequestProperty("X-Requested-With","XMLHttpRequest");
 
		String urlParameters = "ToolkitScriptManager1=UpdatePanel4|NextLinkButton_top&ToolkitScriptManager1_HiddenField=&page_txt=1&ddl_ecode=HYD&ddl_date=2014-07-15&__EVENTTARGET=NextLinkButton_top&__EVENTARGUMENT=&__LASTFOCUS=&__VIEWSTATE="+__VIEWSTATE+"&__EVENTVALIDATION="+__EVENTVALIDATION+"&__ASYNCPOST=true&";
		
        
		DataOutputStream wr = new DataOutputStream(con1.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode1 = con1.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url1);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode1);
 
		BufferedReader in1 = new BufferedReader(
		        new InputStreamReader(con1.getInputStream()));
		String inputLine1;
		StringBuffer response1 = new StringBuffer();
 
		while ((inputLine1 = in1.readLine()) != null) {
			response1.append(inputLine1);
		}
		in1.close();
		Map<String,List<String>> header = con1.getHeaderFields();
		/*List<String> sessionId1 = con1.getHeaderFields().get("Set-Cookie");
		System.out.println(sessionId1);*/
		System.out.println(response1.toString());
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	}

}
