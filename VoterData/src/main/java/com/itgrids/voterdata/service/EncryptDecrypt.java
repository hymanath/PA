package com.itgrids.voterdata.service;

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
     
   public static void main(String args[])
   {
	   List<String> list = new ArrayList<String>(10);
	   
	   list.add("213302");
	   list.add("783934");
	   list.add("299293");
	   list.add("855454");
	   list.add("946865");
	   list.add("513513");
	   list.add("857308");
	   list.add("433035");
	   list.add("700591");
	   list.add("725997");
	   list.add("865584");
	   list.add("445043");
	   list.add("458001");
	   list.add("397570");
	   list.add("917027");
	   list.add("412882");
	   list.add("449691");
	   list.add("499737");
	   list.add("647494");
	   list.add("807969");
	   list.add("571965");
	   list.add("286161");
	   list.add("348655");
	   list.add("443107");
	   list.add("485754");
	   list.add("988743");
	   list.add("581537");
	   list.add("857538");
	   list.add("957514");
	   list.add("260516");
	   list.add("935052");
	   list.add("640087");
	   list.add("527016");
	   list.add("294581");
	   list.add("367161");
	   list.add("508660");
	   list.add("731610");
	   list.add("433393");
	   list.add("868155");
	   list.add("109717");
	   list.add("325756");
	   list.add("999976");
	   list.add("688863");
	   list.add("957568");
	   list.add("628908");
	   list.add("224324");
	   list.add("680557");
	   list.add("414656");
	   list.add("414178");
	   list.add("175657");
	   list.add("949157");
	   list.add("190492");
	   list.add("149797");
	   list.add("547216");
	   list.add("830690");
	   list.add("691086");
	   list.add("794384");
	   list.add("242017");
	   list.add("614053");
	   list.add("118286");
	   list.add("980800");
	   list.add("386371");
	   list.add("199995");
	   list.add("583232");
	   list.add("962617");
	   list.add("502107");
	   list.add("387500");
	   list.add("841551");
	   list.add("919422");
	   list.add("387335");
	   list.add("338727");
	   list.add("366014");
	   list.add("871577");
	   list.add("173305");
	   list.add("354386");
	   list.add("770134");
	   list.add("749640");
	   list.add("716184");
	   list.add("717405");
	   list.add("640565");
	   list.add("547920");
	   list.add("362854");
	   list.add("813951");
	   list.add("598669");
	   list.add("743840");
	   list.add("207258");
	   list.add("665036");
	   list.add("469289");
	   list.add("281141");
	   list.add("442164");
	   list.add("295331");
	   list.add("921475");
	   list.add("126536");
	   list.add("695854");
	   list.add("110621");
	   list.add("537148");
	   list.add("391878");
	   list.add("112648");
	   list.add("278135");
	   list.add("464023");
	   list.add("457364");
	   list.add("415963");
	   list.add("710808");
	   list.add("263075");
	   list.add("767011");
	   list.add("435492");
	   list.add("626084");
	   list.add("712522");
	   list.add("280143");
	   list.add("231605");
	   list.add("129879");
	   list.add("656812");
	   list.add("177019");
	   list.add("284062");
	   list.add("300376");
	   list.add("152685");
	   list.add("722797");
	   list.add("918472");
	   list.add("152586");
	   list.add("610927");
	   list.add("586264");
	   list.add("872612");
	   list.add("786319");
	   list.add("727975");
	   list.add("304262");
	   list.add("688071");
	   list.add("939737");
	   list.add("454691");
	   list.add("476550");
	   list.add("124162");
	   list.add("405788");
	   list.add("942423");
	   list.add("953364");
	   list.add("591195");
	   list.add("230792");
	   list.add("171666");
	   list.add("309612");
	   list.add("331948");
	   list.add("656581");
	   list.add("156520");
	   list.add("598104");
	   list.add("155923");
	   list.add("493175");
	   list.add("806151");
	   list.add("966332");
	   list.add("883465");
	   list.add("818527");
	   list.add("974735");
	   list.add("623094");
	   list.add("455990");
	   list.add("417133");
	   list.add("674875");
	   list.add("187331");
	   list.add("753763");
	   list.add("383903");
	   list.add("198812");
	   list.add("468614");
	   list.add("877593");
	   list.add("944713");
	   list.add("690389");
	   list.add("560026");
	   list.add("349479");
	   list.add("297599");
	   list.add("954664");
	   list.add("133314");
	   list.add("712733");
	   list.add("326916");
	   list.add("122358");
	   list.add("243715");
	   list.add("947804");
	   list.add("913148");
	   list.add("678943");
	   list.add("551160");
	   list.add("177706");
	   list.add("786232");
	   list.add("161058");
	   list.add("618657");
	   list.add("809006");
	   list.add("704668");
	   list.add("991832");
	   list.add("424466");
	   list.add("175635");
	   list.add("853672");
	   list.add("706960");
	   list.add("828282");
	   list.add("932519");
	   list.add("622981");
	   list.add("417390");
	   list.add("401168");
	   list.add("871207");
	   list.add("405676");
	   list.add("963355");
	   list.add("754542");
	   list.add("223026");
	   list.add("564523");
	   list.add("910266");
	   list.add("260641");
	   list.add("751451");
	   list.add("523757");
	   list.add("845887");
	   list.add("831917");
	   list.add("902802");
	   list.add("669752");
	   list.add("559864");
	   list.add("799815");
	   list.add("697615");
	   list.add("698859");
	   list.add("708334");
	   list.add("943401");
	   list.add("909647");
	   list.add("433185");
	   list.add("263216");
	   list.add("355554");
	   list.add("137613");
	   list.add("824432");
	   list.add("803680");
	   list.add("186941");
	   list.add("654056");
	   list.add("549059");
	   list.add("602017");
	   list.add("813697");
	   list.add("636007");
	   list.add("159034");
	   list.add("131049");
	   list.add("159074");
	   list.add("849620");
	   list.add("766276");
	   list.add("457797");
	   list.add("352099");
	   list.add("161235");
	   list.add("829528");
	   list.add("841661");
	   list.add("559574");
	   list.add("523302");
	   list.add("640614");
	   list.add("388553");
	   list.add("955503");
	   list.add("271357");
	   list.add("253495");
	   list.add("162687");
	   list.add("696873");
	   list.add("552218");
	   list.add("440571");
	   list.add("282345");
	   list.add("502264");
	   list.add("248360");
	   list.add("248250");
	   list.add("234866");
	   list.add("204473");
	   list.add("103903");
	   list.add("273524");
	   list.add("637460");
	   list.add("900817");
	   list.add("362318");
	   list.add("842182");
	   list.add("495493");
	   list.add("970006");
	   list.add("173331");
	   list.add("628861");
	   list.add("524678");
	   list.add("725357");
	   list.add("351712");
	   list.add("925452");
	   list.add("805451");
	   list.add("883833");
	   list.add("155828");
	   list.add("267370");
	   list.add("548384");
	   list.add("709999");
	   list.add("597568");
	   list.add("917793");
	   list.add("495067");
	   list.add("710201");
	   list.add("280088");
	   list.add("356982");
	   list.add("413127");
	   list.add("492741");
	   list.add("871622");
	   list.add("938934");
	   list.add("888053");
	   list.add("722227");
	   list.add("610646");
	   list.add("147905");
	   list.add("320743");
	   list.add("787485");
	   list.add("367797");
	   list.add("424979");
	   list.add("747412");
	   list.add("873488");
	   list.add("613767");
	   list.add("272288");
	   list.add("301768");
	   list.add("449333");
	   list.add("832188");
	   list.add("581269");
	   list.add("219789");
	   list.add("838758");
	   list.add("185424");
	   list.add("554202");
	   list.add("111936");
	   list.add("806808");
	   list.add("404588");
	   list.add("501885");
	   list.add("165531");
	   list.add("721679");
	   list.add("633905");
	   list.add("777700");
	   list.add("200078");
	   list.add("725992");
	   list.add("557981");
	   list.add("994745");
	   list.add("230931");
	   list.add("209986");
	   list.add("818063");
	   list.add("640669");
	   list.add("779641");
	   
	   for(String str : list)
	   {
		   String secretKey = EncryptDecrypt.getSecretKey();
		   EncryptDecrypt encryptDecrypt = new EncryptDecrypt(secretKey);
		   String pwd = encryptDecrypt.encryptText(str);
		   System.out.println(str+"\t"+secretKey+"\t"+pwd);
	   }
   }
     

}
