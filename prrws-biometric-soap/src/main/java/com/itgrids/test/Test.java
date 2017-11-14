package com.itgrids.test;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

import org.tempuri.BioAuthSoapProxy;

public class Test {

	public static void main(String[] args) throws RemoteException {
		
		BioAuthSoapProxy bioAuthSoapObj = new BioAuthSoapProxy();
		String jsonString = bioAuthSoapObj.registeredEmployees("27001701024");
		System.out.println(jsonString);
		Set<String> testSet = new HashSet<>();
		System.out.println(Long.valueOf(testSet.size()));
		
		String text="sanjeedt";
		switch(text){
		 case "santosh":
		 System.out.println("HI " + text);
		 break;
		 case "sumit":
			 System.out.println("HI");
			 break;
		 case "sanjeet":
			 System.out.println("HI " + text);
			 break;
		 default:
			 System.out.println("select correct no");
		}
	} 
}
