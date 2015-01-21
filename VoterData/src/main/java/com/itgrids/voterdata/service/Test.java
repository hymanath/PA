package com.itgrids.voterdata.service;

public class Test {

	public static void main(String[] args)
	{
		Test test = new Test();
		System.out.println(test.kamal());
	}
	
	public String kamal()
	{
		try{
			String str = null;
			System.out.println(str.trim());
			System.out.println("Kamal");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
}
