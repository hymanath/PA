package com.itgrids.utils;

import java.util.List;

import com.itgrids.dao.WebServiceUtilServiceTest;
import com.itgrids.dto.InputVO;
import com.sun.jersey.api.client.ClientResponse;

public class NREGSCumulativeThread implements Runnable {

	private String url =null;
	private ClientResponse response =null;
	private InputVO inputVO =null;
	private List responseVo=null;

	 

	public NREGSCumulativeThread(String url,List responseVo,InputVO inputVO) {
		this.url=url;
		this.responseVo = responseVo;
		this.inputVO=inputVO;
	}

	@Override
	public void run() {

		callWebService();

	}

	private void callWebService() {
		try {
			 
			WebServiceUtilServiceTest webServiceUtilService = new WebServiceUtilServiceTest();
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