package com.itgrids.cardprint.security;

import java.security.Key;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

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
            SecureRandom random = SecureRandom.getInstance("CardPrint") ;
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
     
    /* public static void main(String args[])
     {
    	 List<String> list1 = new ArrayList<String>();

    	 list1.add("HyNYbg3IRWMgMaiOCmNI0Q==");
    	 list1.add("X+Tju405SGZRXpLZpsSJSA==");
    	 list1.add("ReEirD1MJ4dxRmOrDbJtQg==");
    	 list1.add("K7v1XQVGLovCIVqe6QxvNw==");
    	 list1.add("gWv3Kf9wxmwnm+PY6IE2nw==");
    	 list1.add("5RZk2rBYXz9dyD/La0jDaQ==");
    	 list1.add("E097173X5Vh7hZSVc4g9Sg==");
    	 list1.add("TaiKpJvb90Ql+kVoapjKXQ==");
    	 list1.add("h2huhSv+rVdpihQlUStLdA==");
    	 list1.add("nWBWpiZ4FryeVf9hN62+/Q==");
    	 list1.add("zB0mMOTr6GNCppqDOcPBGQ==");
    	 list1.add("R7g5eUOMVdbh2zAHxHB/ag==");
    	 list1.add("Rwh8D9JBWYmB42bdBSpE+g==");
    	 list1.add("lra5Fc7Fiud0BR5sm28zew==");
    	 list1.add("aBuJQUT+Cjj07JQwEVoeIw==");
    	 list1.add("+Jr3hjW+PVV7VJJF+axH9Q==");
    	 list1.add("lgBb/KNdl5RIWMB9I2nKPQ==");
    	 list1.add("8NgCC3Oc5VRy2IGN2VXMrg==");
    	 list1.add("/SXFqLQV/mNq6GTJjK5ERA==");
    	 list1.add("K1hYa0/fX/Omk2ECmUatWw==");
    	 list1.add("KdbLwjGxWYrG2pWFAbX8wQ==");
    	 list1.add("wy+oXWhc8GQYsLNR+5EeVg==");
    	 list1.add("jjEb1575YrRITlJmb24ZgA==");
    	 list1.add("Rxwumr6KK+w/Ib6Tc6I35w==");
    	 list1.add("SY0teq+S8/3GzA8IHrPvrg==");
    	 list1.add("qBclpaoj8Jo3s6MKnATmDg==");
    	 list1.add("T9prOmp6KubV8lrBs0PLPw==");
    	 list1.add("f1E1c3LccRLU1JZzpyp6lA==");
    	 list1.add("3KD9w5tE6fvP5dO5ByK5ow==");
    	 list1.add("Ash3zq2jfDZrNrMullGFOA==");
    	 list1.add("WXS/ennIoR4yo8iHF35Y1g==");
    	 list1.add("v23NepD4/GgLpQLXqTDccQ==");
    	 list1.add("5xWs/mB0ucwYf95Gu52pJQ==");
    	 list1.add("3IyQxJH2gdMRDYJd7/X0Fw==");
    	 list1.add("fkkzBNonJP+A5Rw7oAj3SA==");
    	 list1.add("hJAFYksLXe0zxyLwTKbXjw==");
    	 list1.add("4pG/AcBPo3r4XED+dpVVoA==");
    	 list1.add("iL8ZxO0gOpfdQZIEYWrPjQ==");
    	 list1.add("/NVh+zOXizyd/lzvqbFq1A==");
    	 list1.add("fjrP8b4SZFlOBIGuCEo7dQ==");
    	 list1.add("kXTXNWR7ehd1K0c/xqO9hQ==");
    	 list1.add("7T63oqBA5EehWSP4isXzZA==");
    	 list1.add("gNSxyygRwkwOskX1s1Vrgw==");
    	 list1.add("ccGYEHSGz7pIWjzPgBwi9w==");
    	 list1.add("Pbe4Dd9wC9zH1ssg1aSZ2A==");
    	 list1.add("dpvhQJpSQpd43fTi1XHhWg==");
    	 list1.add("YzvJ3d7+Hy2VcloQIyLhPQ==");
    	 list1.add("tYlDdWKqCWZF8hwNrCC2Vg==");
    	 list1.add("smRrPn0MNPzss6EPMCv8KA==");
    	 list1.add("NPcOECk2iheX4IjBQk+Tzw==");
    	 list1.add("nvRpOconGPtowNYfveAOrQ==");
    	 
    	 List<String> list2 = new ArrayList<String>();

    	 
    	 list2.add("HCAgCwG8NGQ=");
    	 list2.add("FvGo6Y8IFpI=");
    	 list2.add("q9AWogg+DR8=");
    	 list2.add("kSWMhiywPkk=");
    	 list2.add("gGjTXrPQTOw=");
    	 list2.add("vz2eMRyJhq0=");
    	 list2.add("nlSrYT49rdw=");
    	 list2.add("cIpu8ZhUW3o=");
    	 list2.add("SmRzBInvHwI=");
    	 list2.add("PvFb0C/OL3A=");
    	 list2.add("sEmFPtUWRvE=");
    	 list2.add("Jul20DRoCHU=");
    	 list2.add("Mv3+O0PHtbM=");
    	 list2.add("x4lu9wJ5+IU=");
    	 list2.add("HHwHruqeZDE=");
    	 list2.add("c/e6+8R5uko=");
    	 list2.add("bkYp7++r1a4=");
    	 list2.add("FSmKV989bSk=");
    	 list2.add("uRYLLJudzps=");
    	 list2.add("HECb1rrlW54=");
    	 list2.add("NGEWPSyUTCo=");
    	 list2.add("p/ENuTQcl8g=");
    	 list2.add("rThSQF0cx/g=");
    	 list2.add("JWd200Ux49k=");
    	 list2.add("O83qT/tnilI=");
    	 list2.add("ICph0JJeGhk=");
    	 list2.add("USw9Fv1YuhM=");
    	 list2.add("szgxs0ofNEo=");
    	 list2.add("McFt1nwBaJ4=");
    	 list2.add("AiwHpClGVJ4=");
    	 list2.add("c7rcFRY+PZ0=");
    	 list2.add("45LfHFSo90o=");
    	 list2.add("vK49y/vf30k=");
    	 list2.add("QJKSMepR3Ds=");
    	 list2.add("L9N1GUZSMpQ=");
    	 list2.add("L4BPdsRPy0U=");
    	 list2.add("aMfBrQIpyNU=");
    	 list2.add("ASahCyaSud8=");
    	 list2.add("p1IlnatiKqc=");
    	 list2.add("s2vTlBxFT3A=");
    	 list2.add("E6jgUjIjmzQ=");
    	 list2.add("gAi5ibBeFhw=");
    	 list2.add("UY/xwoWwPQs=");
    	 list2.add("PVJGrme8B3Y=");
    	 list2.add("/W3qFWL0q7Y=");
    	 list2.add("DmJr9xU4f64=");
    	 list2.add("jC/yodr9O84=");
    	 list2.add("1VuzGp4vC8g=");
    	 list2.add("FrBtMdw9knk=");
    	 list2.add("inMsxMQHeQQ=");
    	 list2.add("p67TdfILKYk=");
    	 
    	 for(int i=0;i<list1.size();i++)
  		{
  			
  			//String hashKey = EncryptDecrypt.getSecretKey();
    		 
    		 String hashKey = list2.get(i);
  			
  			EncryptDecrypt encryptDecrypt = new EncryptDecrypt(hashKey);
  			
  			String encryPtedPassword = encryptDecrypt.decryptText(list1.get(i));
  			
  			
  			String query = "INSERT INTO `survey`.`user` (`firstname`, `username`, `mobile`, `passwd_hash_txt`, `hash_key_txt`, `user_type_id`) VALUES ('"
  					+ list1.get(i)
  					+ "', '"
  					+ list1.get(i)
  					+ "', '9999999999', '"+encryPtedPassword+"', '"+hashKey+"', '2');";
  			 			
  			System.out.println(encryPtedPassword);
  			
  		}


    	 
     }*/
     
     
     public static void main(String args[]){

    	// String hashKey = EncryptDecrypt.getSecretKey();
    	 
    	 List<String> list1 = new ArrayList<String>();
    	 

    	 list1.add("sauser882");
    	 list1.add("sauser883");
    	 list1.add("sauser884");
    	 list1.add("sauser885");
    	 list1.add("sauser886");
    	 list1.add("sauser887");
    	 list1.add("sauser888");
    	 list1.add("sauser889");
    	 list1.add("sauser890");
    	

    	 List<String> list2 = new ArrayList<String>();
    	 
    	 //list2.add("sauser879@uvy");
    	 list2.add("sauser882@bhy");
    	 list2.add("sauser883@qwe");
    	 list2.add("sauser884@bht");
    	 list2.add("sauser885@wgt");
    	 list2.add("sauser886@lki");
    	 list2.add("sauser887@rfe");
    	 list2.add("sauser888@ftr");
    	 list2.add("sauser889@rth");
    	 list2.add("sauser890@uhy");
    	
    	 
    	 for(int i=0;i<list1.size();i++)
 		{
 			
 			String hashKey = EncryptDecrypt.getSecretKey();
 			
 			EncryptDecrypt encryptDecrypt = new EncryptDecrypt(hashKey);
 			
 			String encryPtedPassword = encryptDecrypt.encryptText(list2.get(i));
 			
 			
 			String query = "INSERT INTO `survey`.`user` (`firstname`, `username`, `mobile`, `passwd_hash_txt`, `hash_key_txt`, `user_type_id`) VALUES ('"
 					+ list1.get(i)
 					+ "', '"
 					+ list1.get(i)
 					+ "', '9999999999', '"+encryPtedPassword+"', '"+hashKey+"', '2');";
 			 			
 			System.out.println(query);
 			
 		}



   /* 	// EncryptDecrypt phash = new EncryptDecrypt("ZwECO7MflDI=");
    	EncryptDecrypt phash = new EncryptDecrypt("QLCRnlclyHU=");
    	

    	String encryptedText = phash.decryptText("eu7TZ5P1RS8=");
    	 
    	// String encryptedText = phash.encryptText("adminItg");
    	 
    	 System.out.println(encryptedText);*/
    	// String encryptedText = phash.decryptText("xV8+EyqRkc0=");
    	 
    	 

    	 //System.out.println(encryptedText);

    	 //System.out.println(phash.decryptText(encryptedText));

     
    	 
    	/* for(int i=0;i<=175;i++)
    	 {
    		 
    		 
    		 Random r = new Random();
    		 String[] s= new String[3];
    		 String s1 ="";
    		 
    		 for(int j=0;j<=2;j++)
    		 {
    			 char c = (char)(r.nextInt(26) + 'a');
    			s1 = s1+c;
    		 }
    		 
    		 System.out.println(s1);
    		 
    	 }
    	 */
     }
     

}
