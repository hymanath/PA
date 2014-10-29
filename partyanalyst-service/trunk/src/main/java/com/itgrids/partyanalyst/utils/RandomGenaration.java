package com.itgrids.partyanalyst.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public class RandomGenaration {
	public int randomGenerator(int length)
	{
		Random random = new Random();
		Integer randomNum = 0;
		int number = 1;
		try{
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
			e.printStackTrace();
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
		/* for(int i=0;i<1000;i++)
		System.out.println(RandomGenaration.randomStringOfLength(8)); */
		 for(int i=0;i<1000;i++)
				System.out.println(RandomGenaration.randomString(8));
	  }


static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
static Random rnd = new Random();

public static String randomString( int len ) 
{
   StringBuilder sb = new StringBuilder( len );
   for( int i = 0; i < len; i++ ) 
   {
      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
   }
   return sb.toString();
}


}
