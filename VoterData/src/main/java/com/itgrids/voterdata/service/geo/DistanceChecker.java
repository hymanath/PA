package com.itgrids.voterdata.service.geo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistanceChecker {

	public static void main(String[] args)
	{
		DistanceChecker checker = new DistanceChecker();
		List<DataVO> data = checker.getData("D:/OneDrive/ITGRIDS/ITDP/geodata.txt");
		Date d1 = new Date();
		Map<String,Integer> map = checker.getPointsBetewenInADistance(15.713531,79.8240546,data,200);
		Date d2 = new Date();
		System.out.println(map.size());
		System.out.println("Time Taken - "+(d2.getTime()-d1.getTime()));
	}
	public Map<String,Integer> getPointsBetewenInADistance(Double latitude,Double longitude,List<DataVO> dataList,int length)
	{
		Map<String,Integer> result = new HashMap<String, Integer>(0);
		
		try{
			for(DataVO data : dataList)
			{
				String houseHoldId = data.getHouseHoldId();
				Double distance = DistanceCalculator.distance(latitude,longitude,Double.valueOf(data.getLatitude()),Double.valueOf(data.getLongitude()),"M");
				
				int length2 =  distance.intValue();
				
				if(length2 < length)
				{
					System.out.println(houseHoldId+"\t"+length2);
					result.put(houseHoldId,length2);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public List<DataVO> getData(String filePath)
	{
		List<DataVO> dataList = new ArrayList<DataVO>(0);
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
			String line = null;
			DataVO dataVO = null;
			
			while((line = br.readLine()) != null)
			{
				try{
					String[] data = line.trim().split("\t");
					String houseHoldId = data[0].trim();
					String latitude = data[1].trim();
					String longitude = data[2].trim();
					dataVO = new DataVO();
					dataVO.setHouseHoldId(houseHoldId);
					dataVO.setLatitude(latitude);
					dataVO.setLongitude(longitude);
					dataList.add(dataVO);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		br.close();	
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return dataList;
	}
	
	
}


