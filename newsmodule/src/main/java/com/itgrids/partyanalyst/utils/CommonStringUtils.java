package com.itgrids.partyanalyst.utils;

public class CommonStringUtils {
	
	public static String removeSpecialCharsFromAString(String textValue){
		if(textValue != null){
			/*String[] j=IConstants.SPECIALCHARS;
			for(int i=0;i<j.length;i++){
				textValue=textValue.replace(j[i], "");
			}*/
			textValue = textValue.replaceAll("\uFFFD", "");
			textValue = textValue.replaceAll("&#65533;", "");
		}
		return textValue;
	}

}