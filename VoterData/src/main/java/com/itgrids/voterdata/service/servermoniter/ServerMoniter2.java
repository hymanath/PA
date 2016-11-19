package com.itgrids.voterdata.service.servermoniter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ServerMoniter2 {

	public static void main(String[] args) throws Exception
	{
		Process p1 = Runtime.getRuntime().exec("sudo service openvpn status");
		p1.waitFor();
		String p1Str = output(p1.getInputStream());
		System.out.println(p1Str);  
		
		if(!p1Str.contains("VPN 'client' is running"))
		{
			Process p1r = Runtime.getRuntime().exec("sudo service openvpn start");
			p1r.waitFor();
			System.out.println(output(p1r.getInputStream()));
			
			Process p5 = Runtime.getRuntime().exec("sudo service openvpn status");
			p5.waitFor();
			String p5Str = output(p5.getInputStream());
			System.out.println(p5Str); 
			
			Process p4r = Runtime.getRuntime().exec("sudo service tomcat7 restart");
			p4r.waitFor();
			System.out.println(output(p4r.getInputStream()));
		}

		Process p2 = Runtime.getRuntime().exec("sudo service tomcat7 status");
		p2.waitFor();
		String p2Str = output(p2.getInputStream());
		System.out.println(p2Str);  
		
		if(!p2Str.contains("Tomcat servlet engine is running with pid"))
		{
			Process p2r = Runtime.getRuntime().exec("sudo service tomcat7 start");
			p2r.waitFor();
		}
		
		Process p3 = Runtime.getRuntime().exec("sudo service nginx status");
		p3.waitFor();
		String p3Str = output(p3.getInputStream());
		System.out.println(p3Str);  
		
		if(!p3Str.contains("nginx is running"))
		{
			Process p3r = Runtime.getRuntime().exec("sudo service nginx start");
			p3r.waitFor();
		}
	}
	
	private static String output(InputStream inputStream) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		 try {
			 br = new BufferedReader(new InputStreamReader(inputStream));
			 String line = null;
			 while ((line = br.readLine()) != null) {
				 sb.append(line + System.getProperty("line.separator"));
			 }
		 } finally {
			 br.close();
		 }
		 return sb.toString().trim();
	}
}
