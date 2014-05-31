package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.webservice.client.WebServiceClient;
import com.itgrids.survey.soa.endpoints.OptionVO;

public class WebServiceClientTest  extends BaseDaoTestCase{
	WebServiceClient webServiceClient;
	
	public WebServiceClient getWebServiceClient() {
		return webServiceClient;
	}

	public void setWebServiceClient(WebServiceClient webServiceClient) {
		this.webServiceClient = webServiceClient;
	}

	public void testGetResults(){
		List<Long> surveyIds = new ArrayList<Long>();
		surveyIds.add(124l);
		surveyIds.add(138l);
		surveyIds.add(150l);
		surveyIds.add(158l);
		surveyIds.add(162l);
		surveyIds.add(163l);
		List<OptionVO> results = webServiceClient.getTop5CastePeopleOpnionOnParty(232l, surveyIds);
		System.out.println(results.size());
	}
}
