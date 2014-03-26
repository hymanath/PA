package com.itgrids.partyanalyst.security;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.Util;

public class PBKDF2{
	
	
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
		String md5Value=Util.MD5(Util.MD5("tdp_party")+Util.MD5("tdpP@rty"));
		System.out.println(md5Value);
		String generatedSecuredPasswordHash = generateStorngPasswordHash(md5Value);
        System.out.println(generatedSecuredPasswordHash);
        
    }
    private static String generateStorngPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt().getBytes();
        
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(IConstants.SECKEYFAC);
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }
     
    private static String getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance(IConstants.SECRAN);
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }
     
    private static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
    
  /*  public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
    	
    	
		String md5Value_valid=Util.MD5(Util.MD5("rsk1680@gmail.com")+Util.MD5("adminsasi"));
		System.out.println("Valid - "+md5Value_valid);
    	
        String generatedSecuredPasswordHash = generateStorngPasswordHash(md5Value_valid);
        System.out.println(generatedSecuredPasswordHash);
         
        boolean matched = validatePassword(md5Value_valid, generatedSecuredPasswordHash);
        if(matched){
        	System.out.println("Login Succeed");
        }else{
        	System.out.println("Login Failed");
        }
     
    }*/
     
    @SuppressWarnings("unused")
	private static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);
         
        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(IConstants.SECKEYFAC);
        byte[] testHash = skf.generateSecret(spec).getEncoded();
         
        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        
        System.out.println(diff);
        return diff == 0;
    }
    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
    
    public String generatePassword(String password){
    	String generatedSecuredPasswordHash="";
    	 try {
			generatedSecuredPasswordHash = generateStorngPasswordHash(password);
			} catch (Exception e){
			e.printStackTrace();
			return null;
		}
    	 return generatedSecuredPasswordHash;
    }
    
    public boolean validatePWD(String password, String storedHash,String storedSalt){
    	int diff = 0;
    	try {
			int iterations = 1000;
			byte[] salt = fromHex(storedSalt);
			byte[] hash = fromHex(storedHash);
			
			PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, hash.length * 8);
			SecretKeyFactory skf = SecretKeyFactory.getInstance(IConstants.SECKEYFAC);
			byte[] testHash = skf.generateSecret(spec).getEncoded();
			 
			diff = hash.length ^ testHash.length;
			for(int i = 0; i < hash.length && i < testHash.length; i++)
			{
			    diff |= hash[i] ^ testHash[i];
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			diff = -1;
		}
    	return diff == 0;
    	
    }

}
