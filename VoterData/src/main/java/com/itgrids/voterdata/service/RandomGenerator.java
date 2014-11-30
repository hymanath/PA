package com.itgrids.voterdata.service;

import java.util.Random;

public class RandomGenerator {

	public static void main(String[] args) {
		
		RandomGenerator rg = new RandomGenerator();
		
		for(int i = 0; i<1650;i++)
		{
			System.out.println(rg.randomGenerator(8));
		}

	}
	
	public String randomStringGenerator(int len)
	{
		final String AB = "abcdefghijklmnpqrstuvwxyz@#$";
		Random rnd = new Random();
		
		StringBuilder sb = new StringBuilder(len);
		
		for( int i = 0; i < len; i++ ) 
	      sb.append(AB.charAt(rnd.nextInt(AB.length())));
	   
		return sb.toString();
	}
	
	public int randomGenerator(int length)
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
				return -1;
			}
	}

}
