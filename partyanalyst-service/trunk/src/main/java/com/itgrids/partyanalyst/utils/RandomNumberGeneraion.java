package com.itgrids.partyanalyst.utils;

import java.util.Random;

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
}
