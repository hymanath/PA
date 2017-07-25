package com.itgrids.utils;

import java.util.List;

import com.itgrids.service.integration.external.NregaWebServiceUtilService;
import com.sun.jersey.api.client.ClientResponse;

public class NREGSCumulativeThread implements Runnable {

	private String url =null;
	private ClientResponse response =null;
	private String inputVO =null;
	private List responseVo=null;

	 

	public NREGSCumulativeThread(String url,List responseVo,String input) {
		this.url=url;
		this.responseVo = responseVo;
		this.inputVO=input;
	}

	@Override
	public void run() {

		callWebService();

	}

	private void callWebService() {
		try {
			 
			NregaWebServiceUtilService webServiceUtilService = new NregaWebServiceUtilService();
		    ClientResponse response = webServiceUtilService.callWebService(url, inputVO);
		    responseVo.add(response) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString(){
		return this.url;
	}
}