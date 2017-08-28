package com.itgrids.voterdata.service.geo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DistanceCalculator
{
	public static void main (String[] args) throws java.lang.Exception
	{
		//System.out.println(distance(15.5129781,78.4906838,15.46871,78.62626,"M"));
		calculateDistanceFromFile("C:/Users/Administrator/Desktop/TEMP/BD/BD1.txt");
	}

	private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) 
	{
		try{
			double theta = lon1 - lon2;
			double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
			dist = Math.acos(dist);
			dist = rad2deg(dist);
			dist = dist * 60 * 1.1515;
			
			if (unit == "K") {
				dist = dist * 1.609344;
			} 
			else if (unit == "N") {
				dist = dist * 0.8684;
			}
			else if (unit == "M") {
				dist = dist * 1609.344;
			}
			
			return dist;
		}
		catch(Exception e)
		{
			return 0.0;
		}
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
	
	public static void calculateDistanceFromFile(String filePath)
	{
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
			
			String line = null;
			
			while((line = br.readLine()) != null)
			{
				try{
					String[] data = line.split("\t");
					String booth = data[0].trim();
					String lat1 = data[1].trim();
					String long1 = data[2].trim();
					String lat2 = data[3].trim();
					String long2 = data[4].trim();
					
					Double distance = distance(Double.valueOf(lat1),Double.valueOf(long1),Double.valueOf(lat2),Double.valueOf(long2), "M");
					
					Long distance2 = new Double(distance).longValue();
					
					System.out.println(booth+"\t"+distance2);
					
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
	}
}
