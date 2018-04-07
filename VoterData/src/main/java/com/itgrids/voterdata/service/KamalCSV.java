package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class KamalCSV {

	public static void main(String[] args)
	{
		try{BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Releases\\WARS\\Portal\\NOV10\\2.txt"));
		BufferedReader br = new BufferedReader(new FileReader("D:\\Releases\\WARS\\Portal\\NOV10\\1.txt"));
		
		String line = null;
		int index = 0;
		while ((line = br.readLine()) != null) {
			try{
				if(line != null)
				{
					int si = line.indexOf("\"");
					int ei = line.indexOf("\"",si+1);
					
					if(si > 0 && ei > 0)
					{
						String str = line.substring(si,ei+1);
						String str2 = str.replaceAll(",","-");
						str2 = str2.replaceAll("\"","");
						String str3 = line.replace(str, str2);
						writer.write(str3+"\n");
						++index;
						
						if(index % 100000 == 0)
						{
							System.out.println(index);
						}
					}
					else
					{
						System.out.println(line);
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		writer.close();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
