package com.itgrids.cardprint.util;

import java.security.MessageDigest;

public class SHA512 
{
      public static void main(String args[]) throws Exception 
      {
    	  	String uname = "";
	    	String password = "";
	  	    String shapwd = hashText(password);
	  	    String shapwd1 = hashText(uname);
	  	    //System.out.println(shapwd);
	        String query = "update user set hash_key = '"+shapwd+"',username_hash_key = '"+shapwd1+"' where  username ='"+uname+"';" ;
	        System.out.println(query);
	      
	        //System.out.println(shapwd1);
      }
      
    public static String convertByteToHex(byte data[])
    {
        StringBuffer hexData = new StringBuffer();
        for (int byteIndex = 0; byteIndex < data.length; byteIndex++)
            hexData.append(Integer.toString((data[byteIndex] & 0xff) + 0x100, 16).substring(1));
        
        return hexData.toString();
    }
    
    public static String hashText(String textToHash) throws Exception
    {
        final MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
        sha512.update(textToHash.getBytes());
        return convertByteToHex(sha512.digest());
    }
}

