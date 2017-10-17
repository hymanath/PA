package com.itgrids.utils;

import java.util.Map;

import com.itgrids.service.integration.external.NregaWebServiceUtilService;
import com.sun.jersey.api.client.ClientResponse;

public class SwachhBharatMissionIHHLThread implements Runnable {

	private String url = null;
	private String inputVO = null;
	private String type = null;
	private Map<String,ClientResponse> responseMap = null;

	 

	public SwachhBharatMissionIHHLThread(String url,Map<String,ClientResponse> responseMap,String input,String type) {
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
			 
			NregaWebServiceUtilService webServiceUtilService = new NregaWebServiceUtilService();
		    ClientResponse response = webServiceUtilService.callWebService(url, inputVO);
		    responseMap.put(type, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString(){
		return this.url;
	}
}
