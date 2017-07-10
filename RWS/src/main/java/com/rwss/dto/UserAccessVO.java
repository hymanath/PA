package com.rwss.dto;

import java.util.ArrayList;
import java.util.List;

public class UserAccessVO{
	
	private static List<String> users = new ArrayList<String>();

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}
	
	public static void populateDefaultUsers(){
		
		List<String> userCredentials = new ArrayList<String>();
		
		userCredentials.add("YWRtaW46YWRtaW5AMTIz");
		userCredentials.add("bWlucndzOm1pbnJ3c0AxMjM=");
		userCredentials.add("aXRncmlkczpJdGdyaWRzQDEyMw==");
		((UserAccessVO) users).setUsers(userCredentials);
	}
	public boolean addUser(String credential){
		return users.add(credential);
	}
	public boolean removeUser(String credential){
		return users.remove(credential);
	}
		
}
