package com.itgrids.partyanalyst.utils;

import java.util.Random;
import java.util.UUID;

import org.apache.log4j.Logger;

public class RandomNumberGeneraion {

	private static final Logger LOG = Logger.getLogger(RandomNumberGeneraion.class);
	/**
	 * This Service is used for generating random number
	 * @param length
	 * @return randomNum
	 */
	
	public static int randomGenerator(int length)
	{
		Random random = new Random();
		Integer randomNum = 0;
		int number = 1;
			try
			{
				for(int i=0;i<length;i++)
				{
					number = number * 10;
				}
			
				for(;;)
				{
					randomNum = random.nextInt(number);
					if(randomNum.toString().length() >= length)
					break;
				}
				return randomNum;
			}catch (Exception e) {
				LOG.error("Exception raised in randomGenerator in RandomNumberGeneraion service", e);
				return -1;
			}
	}
	 public static String randomStringOfLength(int length) {
	     StringBuffer buffer = new StringBuffer();
	     while (buffer.length() < length) {
	         buffer.append(uuidString());
	     }

	     //this part controls the length of the returned string
	     return buffer.substring(0, length);  
	 }


	 private static String uuidString() {
	     return UUID.randomUUID().toString().replaceAll("-", "");
	 }

	 public static void main(String args[])
	  {
		 for(int i=0;i<1000;i++)
		System.out.println(RandomNumberGeneraion.randomStringOfLength(8)); 
		 
	  }
	 public static String getUUIDString(){
			try {
				
				UUID uuid = UUID.randomUUID();
				return uuid.toString();
			    
			} catch (Exception e) {
				LOG.error("Exception Occured in getRandomKeyValue(String) in ZohoAlertService class.",e);
			}
			return null;
		}
}
