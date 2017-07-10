package com.rwss.config;

 
import java.util.ArrayList;
import java.util.List;


public class AutherizationService {
	
	
	public boolean authenticate(String authCredentials) {
		boolean flag = false;
		if (null == authCredentials){
			return flag;
		}else{
			List<String> users = new ArrayList<String>();
			users.add("YWRtaW46YWRtaW5AMTIz");
			users.add("bWlucndzOm1pbnJ3c0AxMjM=");
			users.add("aXRncmlkczpJdGdyaWRzQDEyMw==");
			if(users.contains(authCredentials.split(" ")[1])){
			flag = true;
			}else{
				flag=false;
			}
		}
		return flag;
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		/*final String encodedUserPassword = authCredentials.replaceFirst("Basic"
				+ " ", "");
		
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.decodeBase64(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(
				usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		// we have fixed the userid and password as admin
		// call some UserService/LDAP here
		boolean authenticationStatus = "admin".equals(username)
				&& "admin".equals(password);
		return authenticationStatus;*/
		
	}
}