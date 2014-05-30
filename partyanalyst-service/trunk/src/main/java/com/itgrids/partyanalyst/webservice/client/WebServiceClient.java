package com.itgrids.partyanalyst.webservice.client;

import java.util.List;

import javax.xml.ws.WebServiceRef;

import org.apache.log4j.Logger;

import com.itgrids.survey.soa.endpoints.OptionVO;
import com.itgrids.survey.soa.endpoints.impl.SOAPWebServiceEndPoint;
import com.itgrids.survey.soa.endpoints.impl.SOAPWebServiceEndPointImplService;

public class WebServiceClient {
	private static final Logger LOG = Logger.getLogger(WebServiceClient.class);
	//@WebServiceRef(wsdlLocation ="http://74.208.7.129/Survey1/SOAWebService?wsdl")
	private  SOAPWebServiceEndPointImplService service=new SOAPWebServiceEndPointImplService();
	  public List<OptionVO> getTop5CastePeopleOpnionOnParty(Long constituencyId,List<Long> surveyIds){
		  List<OptionVO> result = null;
		  try{
			  SOAPWebServiceEndPoint main=service.getSOAPWebServiceEndPointPort();
				  result = main.getTop5CastePeopleOpnionOnParty(232l, surveyIds);
				System.out.println(result.size());
		/*	SOAPWebServiceEndPointProxy proxy=new SOAPWebServiceEndPointProxy();
			SOAPWebServiceEndPoint  mainobj=proxy.getSOAPWebServiceEndPoint();
			List<Long> surveyIds = new ArrayList<Long>();
			surveyIds.add(124l);
			surveyIds.add(138l);
			surveyIds.add(150l);
			surveyIds.add(158l);
			surveyIds.add(162l);
			surveyIds.add(163l);
			Long[] ids = new Long[surveyIds.size()];
			for(int i =0;i<surveyIds.size();i++){
				ids[i]=surveyIds.get(i);
			}
			
			    result =  mainobj.getTop5CastePeopleOpnionOnParty(constituencyId,ids);
				System.out.println(result.length);
				System.out.println(result[0].getOptionsList());
				for(String caste:result[0].getOptionsList()){
					System.out.println(caste);
				}*/
			}catch(Exception e){
				LOG.error("exception rised in getTop5CastePeopleOpnionOnParty",e);
			}
			return result;
	  }
}
