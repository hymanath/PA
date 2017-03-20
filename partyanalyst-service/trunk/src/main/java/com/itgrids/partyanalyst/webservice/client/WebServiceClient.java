package com.itgrids.partyanalyst.webservice.client;

import java.util.List;

import org.apache.log4j.Logger;
import com.itgrids.survey.soa.endpoints.GenericVO;
import com.itgrids.survey.soa.endpoints.ElectionComparisonVO;
import com.itgrids.survey.soa.endpoints.OptionVO;
import com.itgrids.survey.soa.endpoints.impl.SOAPWebServiceEndPoint;
import com.itgrids.survey.soa.endpoints.impl.SOAPWebServiceEndPointImplService;

public class WebServiceClient {
	private static final Logger LOG = Logger.getLogger(WebServiceClient.class);
	//@WebServiceRef(wsdlLocation ="http://74.208.7.129/Survey1/SOAWebService?wsdl")
	private  SOAPWebServiceEndPointImplService service=new SOAPWebServiceEndPointImplService();
	  public List<OptionVO> getTop5CastePeopleOpnionOnParty(Long constituencyId,List<Long> surveyIds,Long totalVoters){
		  List<OptionVO> result = null;
		  try{
			 /* SOAPWebServiceEndPoint main=service.getSOAPWebServiceEndPointPort();
				  result = main.getTop5CastePeopleOpnionOnParty(constituencyId, surveyIds,totalVoters);
				System.out.println(result.size());*/
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
	  public List<OptionVO> getTop5CastePeopleOpnionOnPartyStateWide(Long stateType,List<Long> surveyIds){
		  List<OptionVO> result = null;
		  try{
			  SOAPWebServiceEndPoint main=service.getSOAPWebServiceEndPointPort();
				  result = main.getTop5CastePeopleOpnionOnPartyStateWide(stateType, surveyIds);
				  for(OptionVO constituency:result){
					  for(int i = 0;i<constituency.getSubOptionList().size();i++){
						  result.get(0).getSubOptionList().get(i).setBadBoothCount(result.get(0).getSubOptionList().get(i).getBadBoothCount()+constituency.getSubOptionList().get(i).getGoodBoothCount());
						  result.get(0).getSubOptionList().get(i).setVeryBadBoothCount(result.get(0).getSubOptionList().get(i).getVeryBadBoothCount()+constituency.getSubOptionList().get(i).getVeryGoodBoothCount());
					  }
				  }
				System.out.println(result.size());
			}catch(Exception e){
				LOG.error("exception rised in getTop5CastePeopleOpnionOnPartyStateWide",e);
			}
			return result;
	  }
	  public List<GenericVO> buildGenderWiseDetails(Long partyId,Long constituencyId,List<Long> surveyIds)
	  {
		  List<GenericVO> returnList = null;
		  try
		  {
			    SOAPWebServiceEndPoint main=service.getSOAPWebServiceEndPointPort();
			    returnList = main.getGenderWiseReportForSurveyAnalysis(partyId, constituencyId, surveyIds);
			    System.out.println(returnList.size());
		  } 
		  catch (Exception e)
		  {
			  LOG.error("exception rised in buildGenderWiseDetails",e);
		  }
		  
		  return returnList;
	  }
	  
	  
	  
	  public ElectionComparisonVO getOptionWiseCountDetailsForSelectedSurveysByConstituencyId(Long constituencyId,List<Long> surveyIds){
		  ElectionComparisonVO result = null;
		  try{
			  SOAPWebServiceEndPoint main=service.getSOAPWebServiceEndPointPort();
			  
			  result =   main.getPartyWiseCountDetailsForSelectedSurveys(surveyIds, constituencyId);
				  
				System.out.println(result);
	
			}catch(Exception e){
				LOG.error("exception rised in getOptionWiseCountDetailsForSelectedSurveysByConstituencyId",e);
			}
			return result;
	  }
}
