package com.itgrids.partyanalyst.security;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;



public class EncryptDecrypt {
	
	String secretKeyText=null;
    SecureRandom random = null;
    
    
    public  EncryptDecrypt(String secretKeyText){

        this.secretKeyText = secretKeyText;
        try {
            SecureRandom random = SecureRandom.getInstance("PartyAnalyst") ;
            this.random = random;
        } catch (NoSuchAlgorithmException e) {
          
        }
    }


     public static String getSecretKey() {
         String keyText = null;

         try {
        	 
        	// Generates a KeyGenerator object for the specified algorithm.
             KeyGenerator keyGen = KeyGenerator.getInstance("DES");
             
            //Generates a secret key.
             Key secretKey = keyGen.generateKey();
             
             
            //Returns the key in its primary encoding format, or null if this key does not support encoding.
             byte[] keyBytes = secretKey.getEncoded();
             
           //Encodes binary data using the base64 algorithm but does not chunk the output.
             byte[] encodedKeyBytes=Base64.encodeBase64(keyBytes);
             
             
            // System.out.println("encodedKeyBytes:"+encodedKeyBytes);
             
             keyText = new String(encodedKeyBytes);
            
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();

         } 

         return keyText;
     }
     
   
    public String encryptText(String clearText)  {
         String encryptedText = null;
         Key secretKey = null;
         try {
        	
         	byte[] secretKeyTextByteArr=secretKeyText.getBytes(); 
         	
         	//Decodes Base64 data into octets
         	byte[] secKeyBytes=Base64.decodeBase64(secretKeyTextByteArr);
         	
         	//Constructs a secret key from the given byte array.
         	secretKey = new SecretKeySpec(secKeyBytes,"DES");
         	
         	//Generates a Cipher object that implements the specified transformation.
             Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
             
            // Initializes this cipher with the public key from the given certificate and a source of randomness.
             cipher.init(Cipher.ENCRYPT_MODE, secretKey,random);
             
             //Finishes a multiple-part encryption or decryption operation, depending on how this cipher was initialized.
             byte[] encryptedBytes = cipher.doFinal(clearText.getBytes());
             
             // Encodes binary data using the base64 algorithm but does not chunk the output.
             encryptedText =  new String(Base64.encodeBase64(encryptedBytes));  
         } catch (Exception e) {

              e.printStackTrace();
         }
         return encryptedText;
     }
    
     public String decryptText(String encryptText) {
         String decryptedText = null;
         Key secretKey = null;
         try {
         	byte[] secretKeyTextByteArr=secretKeyText.getBytes();
         	byte[] secKeyBytes=Base64.decodeBase64(secretKeyTextByteArr); 
         	
         	//Constructs a secret key from the given byte array.
         	secretKey = new SecretKeySpec(secKeyBytes,"DES");
         	
         	//Generates a Cipher object that implements the specified transformation.
             Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
             cipher.init(Cipher.DECRYPT_MODE, secretKey,random);
             
             byte[] encBytes=Base64.decodeBase64(encryptText.getBytes()); 
             
             byte[] decryptedBytes = cipher.doFinal(encBytes);
             decryptedText = new String(decryptedBytes);

         } catch (Exception e) {

               e.printStackTrace();

         }
         return decryptedText;
     }  
     
     
     public static void main(String args[]){

    	 String hashKey = EncryptDecrypt.getSecretKey();

    	 EncryptDecrypt phash = new EncryptDecrypt("iW6XKSnQc5I=");

    	 //String encryptedText = phash.encryptText("1643609");
    	 String encryptedText = phash.decryptText("Io1+S9sfMywZ+IHrcJuMIQ==");

    	 System.out.println(encryptedText);

    	 //System.out.println(phash.decryptText(encryptedText));

     }
     

}
