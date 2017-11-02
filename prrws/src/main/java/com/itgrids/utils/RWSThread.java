package com.itgrids.utils;

import java.util.Map;

import sun.misc.BASE64Encoder;

import com.itgrids.dto.InputVO;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RWSThread implements Runnable {

	private String url = null;
	private InputVO inputVO = null;
	private String type = null;
	private Map<String,ClientResponse> responseMap = null;

	 

	public RWSThread(String url,Map<String,ClientResponse> responseMap,InputVO input,String type) {
		this.url=url;
		this.responseMap = responseMap;
		this.inputVO=input;
		this.type = type;
	}

	@Override
	public void run() {

		callWebService();

	}

	private void callWebService() {
		try {
			 
			CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
		    WebResource webResource = commonMethodsUtilService.getWebResourceObject(url);	        
			String authStringEnc = getAuthenticationString("itgrids","Itgrids@123");	
			ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class,inputVO);
		    responseMap.put(type, response);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getAuthenticationString(String name,String password){
		try {			
	        String authString = name + ":" + password;
	        return new BASE64Encoder().encode(authString.getBytes());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String toString(){
		return this.url;
	}
}
