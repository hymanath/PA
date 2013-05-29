package com.itgrids.partyanalyst.utils;

import org.hibernate.mapping.Array;

public class CommonStringUtils {
	
	public static String removeSpecialCharsFromAString(String textValue){
		if(textValue != null){
			textValue += "";
			String[] j=IConstants.SPECIALCHARS;
			for(int i=0;i<j.length;i++){
				textValue=textValue.replace(j[i], "");
			}
		}
		return textValue;
	}

}
