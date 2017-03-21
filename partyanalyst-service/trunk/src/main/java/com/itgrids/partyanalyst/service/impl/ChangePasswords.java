package com.itgrids.partyanalyst.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import com.itgrids.partyanalyst.security.PBKDF2;

public class ChangePasswords {
	
	public static void main(String[] args) throws Exception
	{
		Map<String,String> pwdMap = new HashMap<String, String>(0);
		
		BufferedReader br = new BufferedReader(new FileReader("E:\\pwdChange.txt"));
		
		while (br.readLine() != null){
		    System.out.println(br.readLine());
		}
		
		/*pwdMap.put("FDCS_SKLM_CSM","457849");
		changePassword(pwdMap);*/
		
	}
	
	public static void changePassword(Map<String,String> pwdMap)
	{
		try{
			System.out.println("---------------------");
			MD5Encrypt encrypt = new MD5Encrypt();
			BufferedWriter outWriter = new BufferedWriter(new FileWriter("D:\\pwdChange.txt")); 
					
			for(Map.Entry<String,String> entry : pwdMap.entrySet())
			{
				try{
					String username = entry.getKey().trim();
					String password = entry.getValue().trim();
					
					String newPwd = encrypt.MD5(encrypt.MD5(username.toLowerCase())+encrypt.MD5(password));
					
					PBKDF2 pb1 = new PBKDF2();
					String storedPwd = pb1.generatePassword(newPwd);
					String[] parts = storedPwd.split(":");
				    String passwordSalt = parts[1];
				    String passwordHash = parts[2];
				    
				    System.out.println("UPDATE user  SET Salt_Key = '"+passwordSalt+"', Hash_Key = '"+passwordHash+"' WHERE username = '"+username+"' AND is_enabled = 'Y';");
				    outWriter.write("UPDATE user  SET Salt_Key = '"+passwordSalt+"', Hash_Key = '"+passwordHash+"' WHERE username = '"+username+"' AND is_enabled = 'Y';\n");
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			outWriter.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
