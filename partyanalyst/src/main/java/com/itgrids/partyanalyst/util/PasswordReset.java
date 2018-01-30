package com.itgrids.partyanalyst.util;

import com.itgrids.partyanalyst.security.EncryptDecrypt;
import com.itgrids.partyanalyst.security.PBKDF2;
import com.itgrids.partyanalyst.service.impl.MD5Encrypt;

public class PasswordReset {

	public static void main(String[] args)
	{
		PasswordReset reset = new PasswordReset();
		//reset.generatePassword("itgrids","!TgRid$@963");
		System.out.println(reset.validatePassword("4dff49c6faad5014686033630bb62961",
				"def12e6c101cdb39596b5f8250638a433862e57395681c01d885844e7a45ff6f1114a71aa88831928e370cf6ea0fc987e78200591da22f27bd0a3a7ee694337a",
				"5b42403765363465666630"));
	}
	
	public void generatePassword(String username,String password)
	{
		try{
			MD5Encrypt encrypt = new MD5Encrypt();
			String enKey = encrypt.MD5(username.toLowerCase())+encrypt.MD5(password);
			String md5Key = encrypt.MD5(enKey);
			String secretKey = EncryptDecrypt.getSecretKey();
			
			PBKDF2 pb = new PBKDF2();
			String storedPwd=pb.generatePassword(secretKey);
			String[] parts = storedPwd.split(":");
			String passwordSalt=parts[1];
	        String passwordHash=parts[2];
	        
	        System.out.println(passwordSalt+"\t"+passwordHash);
	        
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean validatePassword(String password,String hash,String salt)
	{
		try{
			PBKDF2 pb = new PBKDF2();
			return pb.validatePWD(password,hash,salt);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
