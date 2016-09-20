package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test2 {
	public static void main(String[] args) {
		 try{
			 //CookieHandler.setDefault( new CookieManager( null, CookiePolicy.ACCEPT_ALL) );
			String USER_AGENT = "Mozilla/5.0";
			String url = "http://epaper.andhrabhoomi.net/";
			 
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	 
			// optional default is GET
			con.setRequestMethod("GET");
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
	 
			con.setConnectTimeout(40000);
            con.setReadTimeout(40000);
            
            //con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
            con.setFollowRedirects(true);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setAllowUserInteraction(false);
	 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			String sessionId = con.getHeaderFields().get("Set-Cookie").get(0).replace(" path=/; HttpOnly", "").replace("ASP.NET_SessionId=", "");
			System.out.println(response);
			
			System.out.println(sessionId);
			URL obj1 = new URL(url);
			HttpURLConnection con1 = (HttpURLConnection) obj1.openConnection();
	 
			// optional default is GET
			con1.setRequestMethod("GET");
			con1.setRequestProperty("User-Agent", USER_AGENT);
			con1.setFollowRedirects(true);
			con1.setDoOutput(true);
			con1.setDoInput(true);
			con1.setUseCaches(false);
			con1.setAllowUserInteraction(false);
			con1.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con1.setRequestProperty("Content-Language", "en-US");
			con1.setRequestProperty("Cookie", sessionId);
			con1.setRequestProperty("User-Agent", USER_AGENT);
	
	 
			BufferedReader in1 = new BufferedReader(
			        new InputStreamReader(con1.getInputStream()));
			String inputLine1;
			StringBuffer response1 = new StringBuffer();
	 
			while ((inputLine1 = in1.readLine()) != null) {
				response1.append(inputLine1);
			}
			in1.close();
			System.out.println(response1);
			String sessionId1 = con1.getHeaderFields().get("Set-Cookie").get(0).replace(" path=/; HttpOnly", "");
			System.out.println(sessionId1);
			
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		}

}
