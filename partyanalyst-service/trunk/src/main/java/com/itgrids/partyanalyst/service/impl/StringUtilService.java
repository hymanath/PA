package com.itgrids.partyanalyst.service.impl;

import com.itgrids.partyanalyst.service.IStringUtilService;

/**
 * 
 * @author Y.Ravi Kiran
 * @date 09-04-2011
 * This Class contains all the method(s) that can be used to handle on Strings.
 * 
 */
public class StringUtilService implements IStringUtilService {

	/**
	 * This method can be used to split a string at a regular interval using a delimiter.
	 * 
	 * @author Y.Ravi Kiran
	 * @param inputString
	 * @param position
	 * @param delimitationConstant
	 * @return
	 */
	
	public String fragmentARegularString(String inputString,int position,String delimitationConstant){
		String requiredDelimitedString = new String();	
		int lengthOfInputStrength = inputString.length();
		if(!inputString.contains(delimitationConstant)){
			for(int i=0;(i+position)<lengthOfInputStrength;i+=position)
				requiredDelimitedString += inputString.substring(i,i+position)+delimitationConstant;						
		}
		requiredDelimitedString +=inputString.substring((lengthOfInputStrength/position)*position,lengthOfInputStrength);
		return requiredDelimitedString;
	}
	
	/*public static void main(String[] args) throws Exception{
		
		String str = "ravikiranravikiranravikiranravikravikiranravikiranravikiranravikravikiranravikiranravikiranravikravi123";
		int index = 3;
		System.out.println(new StringUtilService().fragmentARegularString(str,index,"-"));
	}*/
	
}
