package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReadFromBinLog {

	public static void main(String... args)
	{
		try{
			File binlog = new File("C:\\Users\\Kamalakar\\Desktop\\MB\\BINLOG\\disk6.txt");
			File result = new File("C:\\Users\\Kamalakar\\Desktop\\MB\\BINLOG\\disk6_result.txt");
			
			BufferedReader br = new BufferedReader(new FileReader(binlog));
			BufferedWriter outwriter = new BufferedWriter(new FileWriter(result));
			
			StringBuilder sb = new StringBuilder();
			
			String line = null;
			Map<Integer,String> map = new LinkedHashMap<Integer, String>(0);
			
			int index = 0;
			while((line = br.readLine()) != null)
			{
				map.put(++index,line);
			}
			br.close();
			
			List<Integer> indexList = new ArrayList<Integer>();
			
			for(Map.Entry<Integer,String> entry : map.entrySet())
			{
				if(entry.getValue().equalsIgnoreCase("insert into retailer ("))
					indexList.add(entry.getKey()-4);
			}
			
			for(Integer sindex : indexList)
			{
				try{
					for(int inIndex = sindex;inIndex==0;sindex++)
					{
						if(map.get(inIndex).equalsIgnoreCase("COMMIT/*!*/;"))
							inIndex = 0;
						sb.append(map.get(inIndex)+"\n");
					}
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			outwriter.write(sb.toString());
			outwriter.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}


