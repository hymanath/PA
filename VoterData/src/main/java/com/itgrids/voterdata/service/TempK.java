package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class TempK {
	
	public static void main(String[] args) {
		try{
			TempK tempK = new TempK();

			File districtFolder = new File(args[0]);
			{
				if(districtFolder.isDirectory())
				{
					for(File file : districtFolder.listFiles())
							tempK.readData(file.getAbsolutePath());
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public int readData(String filePath)
	{
		int count = 0;
		try{
			File file = new File(filePath);
			
			if(!file.isDirectory())
			{
				System.out.println("Reading File -- "+filePath);
				BufferedReader br =  new BufferedReader(new FileReader(file));
				BufferedWriter outwriter = new BufferedWriter(new FileWriter(file.getAbsolutePath()+"_Target"));
				
				String line = null;
				StringBuilder sb = new StringBuilder();
				
				while((line = br.readLine()) != null)
				{
					int index = line.indexOf("|");
					if(index != -1)
					{
						sb.append(line.substring(0,index).trim()+"\n");
					}
				}
				br.close();
				outwriter.write(sb.toString());
				outwriter.close();
			}
			return count;
		}catch(Exception e)
		{
			e.printStackTrace();
			return count;
		}
	}
	
	

}
