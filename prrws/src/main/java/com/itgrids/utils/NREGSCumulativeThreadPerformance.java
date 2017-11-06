package com.itgrids.utils;

import java.util.Date;
import java.util.List;

import com.itgrids.dto.WebserviceHealthVO;
import com.itgrids.service.integration.external.NregaWebServiceUtilService;
import com.sun.jersey.api.client.ClientResponse;

public class NREGSCumulativeThreadPerformance implements Runnable {

	private String url =null;
	private ClientResponse response =null;
	private String inputVO =null;
	private Long urlId = null;
	private List responseVo=null;
	private DateUtilService dateUtilService = new DateUtilService();
	 

	public NREGSCumulativeThreadPerformance(String url,Long urlId,List<WebserviceHealthVO> responseVo,String input) {
		this.url=url;
		this.responseVo = responseVo;
		this.inputVO=input;
		this.urlId = urlId;
	}

	@Override
	public void run() {

		callWebService();

	}

	private void callWebService() {
		WebserviceHealthVO webserviceHealthVO = new WebserviceHealthVO();
		
		try {
			
					NregaWebServiceUtilService webServiceUtilService = new NregaWebServiceUtilService();
					Date startTime = dateUtilService.getCurrentDateAndTime();
					webserviceHealthVO.setStartTime(startTime);
					webserviceHealthVO.setUrl(url);
				    ClientResponse response = webServiceUtilService.callWebService(url, inputVO);
				    Date endTime = dateUtilService.getCurrentDateAndTime();
				    webserviceHealthVO.setEndDate(endTime);
				    webserviceHealthVO.setUrlId(urlId);
				    webserviceHealthVO.setStatusCode(200L);
				    webserviceHealthVO.setStatus("Success");
				   
				    responseVo.add(webserviceHealthVO) ;
				} catch (Exception e) {
					e.printStackTrace();
					Date endTime = dateUtilService.getCurrentDateAndTime();
					webserviceHealthVO.setUrlId(urlId);
					webserviceHealthVO.setEndDate(endTime);
					webserviceHealthVO.setStatusCode(400L);
				    webserviceHealthVO.setStatus("Fail");
					responseVo.add(webserviceHealthVO);
				}
		}

		@Override
		public String toString(){
			return this.url;
		}

}
