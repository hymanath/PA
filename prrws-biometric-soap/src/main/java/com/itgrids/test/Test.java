package com.itgrids.test;

import java.rmi.RemoteException;

import org.tempuri.BioAuthSoapProxy;

public class Test {

	public static void main(String[] args) throws RemoteException {
		
		BioAuthSoapProxy bioAuthSoapObj = new BioAuthSoapProxy();
		String jsonString = bioAuthSoapObj.registeredEmployees("27001701024");
		System.out.println(jsonString);
		
	} 
}
