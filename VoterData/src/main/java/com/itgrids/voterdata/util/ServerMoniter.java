package com.itgrids.voterdata.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ServerMoniter {

	public static void main(String[] args) throws Exception
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String file = sdf.format(new Date())+"_Server_Moniter.txt";
		
		BufferedWriter outwriter = new BufferedWriter(new FileWriter("/app/SHS/logs/"+file,true));
		Process p2 = Runtime.getRuntime().exec("free -g");
		p2.waitFor();
		String memStr = output(p2.getInputStream());
		//System.out.println(memStr);
		
		if(memStr != null && memStr.length() > 0)
		{
			String[] memStrArr = memStr.split("\n");
			
			if(memStrArr != null && memStrArr.length > 1)
			{
				String[] arr = memStrArr[1].split(" ");
				List<String> list = new ArrayList<String>(0);
				
				for(String str2 :arr)
				{
					if(str2.trim().length() > 0)
					{
						//System.out.println(str2.trim());
						list.add(str2.trim());
					}
				}
				
				Integer total = Integer.parseInt(list.get(1).trim());
				Integer used = Integer.parseInt(list.get(2).trim());
				Integer usedPer = (used*100)/total;
				
				outwriter.write(getCurrentDateAndTimeInStringFormat()+" - Used RAM - "+used+" G,("+usedPer+" %)\n");
				
				if(usedPer >= 65)
				{
					//System.out.println(usedPer);
					Process p3 = Runtime.getRuntime().exec("/app/SHS/cache_clear.sh");
					p3.waitFor();
					outwriter.write(getCurrentDateAndTimeInStringFormat()+" - Cache Cleared\n");
					//String op = output(p3.getInputStream());
					//System.out.println(op);
					
				}
			}
		}
		
		Process p1 = Runtime.getRuntime().exec("nproc");
		p1.waitFor();
		String coresStr = output(p1.getInputStream());
		//System.out.println(coresStr);
		
		Integer cores = Integer.parseInt(coresStr.trim());
		
		int max = cores * 50;
		
		Process p4 = Runtime.getRuntime().exec("/app/SHS/CPU.sh");
		p4.waitFor();
		String cpuStr = output(p4.getInputStream());
		//System.out.println(cpuStr);
		
		if(cpuStr != null && cpuStr.trim().length() > 0)
		{
			String[] cpuStrArr = cpuStr.split("\n");
			
			if(cpuStrArr != null && cpuStrArr.length > 1)
			{
				String[] arr = cpuStrArr[1].split(" ");
				List<String> list = new ArrayList<String>(0);
				
				for(String str2 :arr)
				{
					if(str2.trim().length() > 0)
					{
						//System.out.println(str2.trim());
						list.add(str2.trim());
					}
				}
				
				int pid = Integer.parseInt(list.get(0).trim());
				Double d = Double.parseDouble(list.get(list.size()-1).trim());
				int usedCPU = d.intValue();
				//System.out.println("usedCPU - "+usedCPU);
				//System.out.println("max - "+max);
				outwriter.write(getCurrentDateAndTimeInStringFormat()+" - Highest CPU Used by Process - "+list.get(1)+", CPU % is - "+usedCPU+"\n");
				if(usedCPU > max)
				{
					Process p5 = Runtime.getRuntime().exec("kill -9 "+pid);
					p5.waitFor();
					outwriter.write(getCurrentDateAndTimeInStringFormat()+" - Process - "+list.get(1)+", is Killed with Process ID - "+pid+"\n");
				}
			}
		}
		outwriter.close();
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
	
	private static String getCurrentDateAndTimeInStringFormat() 
	{
		try {
			Date updatedDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
			return sdf.format(updatedDate);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
