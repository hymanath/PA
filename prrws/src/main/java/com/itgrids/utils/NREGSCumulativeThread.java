package com.itgrids.utils;

import java.util.List;

import com.itgrids.dto.WebserviceVO;
import com.itgrids.service.IWebserviceHandlerService;

public class NREGSCumulativeThread implements Runnable {

	private String url;
	private String input;
	private List<WebserviceVO> responseList;
	private IWebserviceHandlerService webserviceHandlerService;

	public NREGSCumulativeThread(IWebserviceHandlerService webserviceHandlerService,String url,List<WebserviceVO> responseList,String input) 
	{
		this.webserviceHandlerService = webserviceHandlerService;
		this.url = url;
		this.responseList = responseList;
		this.input = input;
	}

	@Override
	public void run() {
		
		callWebService();

	}

	private void callWebService() {
		try {
			WebserviceVO webserviceVO = webserviceHandlerService.callWebServiceForThread(url, input,IConstants.REQUEST_METHOD_POST);
			responseList.add(webserviceVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString(){
		return this.url;
	}
}