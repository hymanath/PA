package com.itgrids.partyanalyst.utils;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
/*
 * Author : Srishailam Pittala
 * mail   : srishailam.itgrids.hyd@gmail.com
 * Date   : 29/04/2016
 * Desc	  : This Class will work for MD5 Algorith Ecryption and Decryption techniques
 * 
 * */
public class MD5Algoritm implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String key = IConstants.MD5_PRIMARY_KEY;
	private static byte[] sharedvector = {
	    0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11
	};
	
	public static void main(String[] args){
		//String text = generateMD5Encrypt("12345678");
		//generateMD5Decrypt(text);
	}
	
	public String generateMD5Decrypt(String inputText){
		String decryptedString = null;
		 String RawText = "";
	     byte[] keyArray = new byte[24];
	     byte[] temporaryKey;
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
            temporaryKey = m.digest(key.getBytes("UTF-8"));           
 
            if(temporaryKey.length < 24) // DESede require 24 byte length key
            {
                int index = 0;
                for(int i=temporaryKey.length;i< 24;i++)                  
                    keyArray[i] =  temporaryKey[index];
            }
            
            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));
            byte[] decrypted = c.doFinal(Base64.decodeBase64(inputText));   
 
            RawText = new String(decrypted, "UTF-8");
            //System.out.println("DecryptText : "+ RawText);
            decryptedString = RawText;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedString;
	}
	
	public String generateMD5Encrypt(String inputText){
		String encryptedString = null;
		try {
	        String EncText = "";
	        byte[] keyArray = new byte[24];
	        byte[] temporaryKey;
	        byte[] toEncryptArray = null;

	            toEncryptArray =  inputText.getBytes("UTF-8");        
	            MessageDigest m = MessageDigest.getInstance("MD5");
	            temporaryKey = m.digest(key.getBytes("UTF-8"));
	 
	            if(temporaryKey.length < 24) // DESede require 24 byte length key
	            {
	                int index = 0;
	                for(int i=temporaryKey.length;i< 24;i++)
	                    keyArray[i] =  temporaryKey[index];
	            }        
	 
	            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");            
	            c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));            
	            byte[] encrypted = c.doFinal(toEncryptArray);            
	            EncText = Base64.encodeBase64String(encrypted);
	 
	        //System.out.println("EncText"+EncText);
	        encryptedString = EncText;
	       
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}
	
}
