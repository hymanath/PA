package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
	
	

	/*
	 * Author : Srishailam Pittala
	 * Date : 9th Dec, 2014
	 * input : sorted Long Ids
	 * return  string variable 
	 * ex: 1,3,4 to 9 (4,5,6,7,8,9) ,11,12,13 to 15 (13,14,15) ,17,19,20 to 25  (20,21,22,23,24,25) , 26
	 */
	   public String sortedItemsForSMS(Set<Long> sortedItemsSet) // without duplicates sorted Set
	   {
		   String finalStr="";
		   
		   try {
			   if(sortedItemsSet != null && sortedItemsSet.size()>0)
			   {
				   List<Long> buildsItems = new ArrayList<Long>();
				   for (Long item : sortedItemsSet) 
				   {
					   buildsItems.add(item);
				   }
				   
				   if(buildsItems != null && buildsItems.size()>0)
				   {
					   Long tempItem = 0L;		   
					   Boolean isNext = false;
					   Long count = 0L;
					   for (int i=0;i<buildsItems.size();i++)
					   {
						   Long presentItem = buildsItems.get(i);

						   if(i == 0) // first element
						   {				  
							   finalStr = presentItem.toString();
						   }
						   else
						   {
							   if(presentItem != (tempItem+1))
							   {
								   if(isNext)
								   {
									   if(count >1)
									   {
										   finalStr = finalStr+" to "+tempItem.toString()+","+presentItem.toString();
									   }
									   else
									   {
										   finalStr = finalStr+","+tempItem.toString()+","+presentItem.toString();
									   }
								   }
								   else
								   {
									   finalStr = finalStr+","+presentItem.toString();
								   }
								   isNext = false;
								   count = 0L;
							   }
							   else
							   {
								   count = count+1;
								   isNext = true;
								   tempItem = presentItem;
								   continue;
							   }
						   }
						   tempItem = presentItem;
					   }
					   if(isNext)
					   {
						   finalStr = finalStr+" to "+tempItem.toString();
					   }
					   //System.out.println(finalStr);
				   }
			   }
		} catch (Exception e) {
			return null;
		}
		   return finalStr;
	   }
}
