package com.itgrids.partyanalyst.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encrypt {
	//NEWS-HUNT
	//Source File Name:MD5Encrypt.java
	// Referenced classes of package com.clovetech.esgtms.util:
	
	/*	@Author SASI 
	 * 	@Since 10th JULY 2014
	 * */


	    public static String MD5(String input)

	    {

	        try

	        {

	            MessageDigest md = MessageDigest.getInstance("MD5");

	            return byteToHexString(md.digest(input.getBytes()));

	        }

	        catch(NoSuchAlgorithmException ex) { }

	        catch(NullPointerException ex) { }

	        return null;

	    }



	    public static String byteToHexString(byte hash[])

	    {

	        StringBuffer buf = new StringBuffer(hash.length * 2);

	        for(int i = 0; i < hash.length; i++)

	        {

	            if((hash[i] & 0xff) < 16)

	                buf.append("0");

	            buf.append(Long.toString(hash[i] & 0xff, 16));

	        }



	        return buf.toString();

	    }



	    public static boolean sameStrings(String str1, String str2)

	    {

	        return str1 != null ? str1.equals(str2) : str2 == null;

	    }



	    public static boolean isEmpty(String str)

	    {

	        return str == null || "".equals(str.trim());

	    }



	    public static String nl2br(String value)

	    {

	        if(value == null)

	            return null;

	        char content[] = new char[value.length()];

	        value.getChars(0, value.length(), content, 0);

	        StringBuffer result = new StringBuffer(content.length + 50);

	        for(int i = 0; i < content.length; i++)

	            if(content[i] == '\n')

	                result.append("<br />\n");

	            else

	                result.append(content[i]);



	        return result.toString();

	    }

	}



