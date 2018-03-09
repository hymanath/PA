package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.AlertVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.JalavaniAlertsInputVO;
import com.itgrids.dto.NregsFmsWorksVO;
import com.itgrids.service.IJalavaniDashboardService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Service
@Transactional
public class JalavaniDashboardService implements IJalavaniDashboardService{

	private static final Logger LOG = Logger.getLogger(JalavaniDashboardService.class);
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	
	public AlertVO getJalavaniDashBoardOverview(JalavaniAlertsInputVO inputVO){
		AlertVO finalVO = new AlertVO();
		try {
			   WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://mytdp.com/WebService/getJalavaniDashBoardViewInfo");
			 //WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.146:8080/PartyAnalyst/WebService/getJalavaniDashBoardViewInfo");
		     ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
		     
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
				String output = response.getEntity(String.class);
				if(output != null && !output.isEmpty()){
					if(output != null && !output.isEmpty()){
	    				JSONObject jObj = new JSONObject(output);
	    				finalVO.setCategoryCount(jObj.getLong("count"));//callCenterCalls TotalCount 
	    				finalVO.setLocationCnt(jObj.getLong("locationCnt"));//Month Wise total Month Count 
	    				
		    				JSONArray categoriesArr = jObj.getJSONArray("subList1");
	 	    				if(categoriesArr != null && categoriesArr.length() > 0){
	 	    					for (int j = 0; j < categoriesArr.length(); j++) {
	 	    						AlertVO categoryVO = new AlertVO();
									JSONObject jobj = (JSONObject) categoriesArr.get(j);
									categoryVO.setId(jobj.getLong("id"));
									categoryVO.setName(jobj.getString("name"));
									categoryVO.setAlertCnt(jobj.getLong("alertCnt")); //(categoryWise alert count)
									
									finalVO.getSubList1().add(categoryVO);
								}
	 	    				}
	 	    			
	 	    				JSONArray monthsArr = jObj.getJSONArray("subList2");
	 	    				if(monthsArr != null && monthsArr.length() > 0){
	 	    					for (int j = 0; j < monthsArr.length(); j++) {
	 	    						AlertVO monthVo = new AlertVO();
									JSONObject jobj1 = (JSONObject) monthsArr.get(j);
									monthVo.setMonthId(jobj1.getLong("smTypeId"));
									monthVo.setMonthName(jobj1.getString("smType"));
									monthVo.setPercentage(jobj1.getDouble("percentage"));
									
									finalVO.getSubList2().add(monthVo);
								}
	 	    				}
	 	    				
	 	    				JSONArray statusArr = jObj.getJSONArray("list");
	 	    				if(statusArr != null && statusArr.length() > 0){
	 	    					for (int j = 0; j < statusArr.length(); j++) {
	 	    						AlertVO statusVo = new AlertVO();
									JSONObject jobj2 = (JSONObject) statusArr.get(j);
									statusVo.setStatusId(jobj2.getLong("statusId"));
									statusVo.setStatus(jobj2.getString("status"));
									statusVo.setColor(jobj2.getString("color"));
									statusVo.setStatusCount(jobj2.getLong("statusCount"));
									
									finalVO.getList().add(statusVo);
								}
	 	    				}
	 	    			}
	 	    		}
	 	      }
	           WebResource webResource1 = commonMethodsUtilService.getWebResourceObject("http://www.mytdp.com/CommunityNewsPortal/webservice/getjavalavaniOverViewDashBoardForPrintAndElectronicInfo");
			  //WebResource webResource1 = commonMethodsUtilService.getWebResourceObject("http://192.168.11.146:8080/CommunityNewsPortal/webservice/getjavalavaniOverViewDashBoardForPrintAndElectronicInfo");
			 ClientResponse response1 = webResource1.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
			   
	          if(response1.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response1.getStatus());
	 	      }else{
				String output1 = response1.getEntity(String.class);
				if(output1 != null && !output1.isEmpty()){
					JSONObject jObj = new JSONObject(output1);
    				finalVO.setPrintCount(jObj.getLong("count"));//print count
    				finalVO.setElectCount(jObj.getLong("count1"));//EN count
				}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception raised at getJalavaniDashBoardOverview - JalavaniDashboardService service", e);
		}
		return finalVO;
	}
	public AlertVO getJalavaniCategoryWiseDetailsInfo(JalavaniAlertsInputVO inputVO){
		AlertVO finalVO = new AlertVO();
		try {
			 WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://mytdp.com/WebService/getJalavaniCategoryWiseDetailsInfo");
			 //WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.146:8080/PartyAnalyst/WebService/getJalavaniCategoryWiseDetailsInfo");
		     ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
		     
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
				String output = response.getEntity(String.class);
				if(output != null && !output.isEmpty()){
					if(output != null && !output.isEmpty()){
	    				JSONObject jObj = new JSONObject(output);
	    				finalVO.setCategoryCount(jObj.getLong("count"));//total AlertsCount By serach category
	    				finalVO.setLocationCnt(jObj.getLong("locationCnt"));
	    				
	 	    				JSONArray monthsArr = jObj.getJSONArray("subList2");
	 	    				if(monthsArr != null && monthsArr.length() > 0){
	 	    					for (int j = 0; j < monthsArr.length(); j++) {
	 	    						AlertVO monthVo = new AlertVO();
									JSONObject jobj1 = (JSONObject) monthsArr.get(j);
									monthVo.setMonthId(jobj1.getLong("smTypeId"));
									monthVo.setMonthName(jobj1.getString("smType"));
									monthVo.setLocationCnt(jobj1.getLong("locationCnt"));
									monthVo.setPercentage(jobj1.getDouble("percentage"));
									
									finalVO.getSubList2().add(monthVo);
								}
	 	    				}
	 	    				
	 	    				JSONArray statusArr = jObj.getJSONArray("list");
	 	    				if(statusArr != null && statusArr.length() > 0){
	 	    					for (int j = 0; j < statusArr.length(); j++) {
	 	    						AlertVO statusVo = new AlertVO();
									JSONObject jobj2 = (JSONObject) statusArr.get(j);
									statusVo.setStatusId(jobj2.getLong("statusId"));
									statusVo.setStatus(jobj2.getString("status"));
									statusVo.setColor(jobj2.getString("color"));
									statusVo.setStatusCount(jobj2.getLong("statusCount"));
									
									finalVO.getList().add(statusVo);
								}
	 	    				}
	 	    				
	 	    				if(inputVO.getSearchType() !=null && inputVO.getSearchType().equalsIgnoreCase("print") || inputVO.getSearchType().equalsIgnoreCase("electronic")){
	 	    					 WebResource webResource1 = commonMethodsUtilService.getWebResourceObject("http://www.mytdp.com/CommunityNewsPortal/webservice/getjavalavaniPrintAndElectrincDetailsInfoBySearchType");
	 	    					// WebResource webResource1 = commonMethodsUtilService.getWebResourceObject("http://192.168.11.146:8080/CommunityNewsPortal/webservice/getjavalavaniPrintAndElectrincDetailsInfoBySearchType");
	 	    					 ClientResponse response1 = webResource1.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
	 	    					 
	 	    					if(response1.getStatus() != 200){
	 	    			 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response1.getStatus());
	 	    			 	    }else{
	 	    						String output1 = response1.getEntity(String.class);
	 	    						if(output1 != null && !output1.isEmpty()){
	 	    							if(output1 != null && !output1.isEmpty()){
	 	    								JSONObject newsJobJ = new JSONObject(output1);
	 	    			    				finalVO.setPosCount(newsJobJ.getLong("posCount"));//positiveCount
	 	    			    				finalVO.setNegCount(newsJobJ.getLong("negCount"));
	 	    			    				finalVO.setTotalNewsCnt(newsJobJ.getLong("posCount")+newsJobJ.getLong("negCount"));
	 	    								
	 	    			    				JSONArray articleMonthArr = newsJobJ.getJSONArray("idNameVoList");
	 	    		 	    				if(articleMonthArr != null && articleMonthArr.length() > 0){
	 	    		 	    					for (int j = 0; j < articleMonthArr.length(); j++) {
	 	    		 	    						AlertVO monthVo = new AlertVO();
	 	    										JSONObject newsJson = (JSONObject) articleMonthArr.get(j);
	 	    										monthVo.setId(newsJson.getLong("id"));
	 	    										monthVo.setName(newsJson.getString("name"));
	 	    										monthVo.setPosCount(newsJson.getLong("posCount"));
	 	    										monthVo.setNegCount(newsJson.getLong("negCount"));
	 	    										
	 	    										finalVO.getSubList1().add(monthVo);
	 	    									}
	 	    		 	    				}
	 	    							}
	 	    						}
	 	    			 	    }
	 	    				}else if(inputVO.getSearchType() !=null && inputVO.getSearchType().equalsIgnoreCase("callcenter")){
	 	    					JSONArray ivrArr = jObj.getJSONArray("subList1");
		 	    				if(ivrArr != null && ivrArr.length() > 0){
		 	    					for (int j = 0; j < ivrArr.length(); j++){
		 	    						AlertVO ivrVo = new AlertVO();
										JSONObject jobj = (JSONObject) ivrArr.get(j);
										
										ivrVo.setComment(jobj.getString("comment"));
										ivrVo.setFeedbackCount(jobj.getLong("notifiedCount"));
										
										finalVO.getSubList1().add(ivrVo);
									}
		 	    				}
	 	    				}
	 	    			}
	 	    		}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception raised at getJalavaniCategoryWiseDetailsInfo - JalavaniDashboardService service", e);
		}
		return finalVO;
	}
	public  List<AlertVO> getArticlesMonthlyOverviewInfoBySearchType(JalavaniAlertsInputVO inputVO){
    	List<AlertVO> returnList = new ArrayList<AlertVO>();
   	 try {
   		 if(inputVO.getSearchType() !=null && inputVO.getSearchType().equalsIgnoreCase("news")){
   			 WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://www.mytdp.com/CommunityNewsPortal/webservice/getArticlesMonthlyOverviewInfoBySearchType");
			// WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.146:8080/CommunityNewsPortal/webservice/getArticlesMonthlyOverviewInfoBySearchType");
			 ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
		        if (response.getStatus() != 200) {
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	     } else {
		 	    	String output = response.getEntity(String.class);
		 	    	if(output != null && !output.isEmpty()){
		 	    		JSONArray finalArray = new JSONArray(output);
		 	    		if(finalArray!=null && finalArray.length()>0){
		 	    			for(int i=0;i<finalArray.length();i++){
		 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				AlertVO vo = new AlertVO();
		 	    				vo.setId(jObj.getLong("id"));
		 	    				vo.setName(jObj.getString("name"));
		 	    				vo.setPosCount(jObj.getLong("posCount"));
		 	    				vo.setNegCount(jObj.getLong("negCount"));
		 	    				vo.setTotalNewsCnt(jObj.getLong("count"));
		 	    				vo.setPosPerc(jObj.getDouble("posPercent"));
		 	    				vo.setNegPerc(jObj.getDouble("negPercent"));
		 	    				
		 	    				returnList.add(vo);
		 	    			}
		 	    		}
		 	    	}
		 	    } 
   		 }else if(inputVO.getSearchType() !=null && inputVO.getSearchType().equalsIgnoreCase("alerts")){
   			 WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://mytdp.com/WebService/getAlertsMonthlyOverviewInfoBySearchType");
   			// WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.146:8080/PartyAnalyst/WebService/getAlertsMonthlyOverviewInfoBySearchType");
   		     ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
   		  if (response.getStatus() != 200) {
 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	     }else{
 	    	String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				AlertVO vo = new AlertVO();
	 	    				
	 	    				vo.setMonthId(jObj.getLong("smTypeId"));
	 	    				vo.setMonthName(jObj.getString("smType"));
	 	    				vo.setCount(jObj.getLong("locationCnt"));
	 	    				vo.setPercentage(jObj.getDouble("statusPercent"));
	 	    				
	 	    				returnList.add(vo);
	 	    			}
	 	    		}
	 	    	}
 	     	} 
   		 }
   		 	
   	 } catch (Exception e ){
   		 LOG.error(" Exception occured at getArticlesMonthlyOverviewInfoBySearchType() in JalavaniDashboardService class ", e);
   	 }
   	 return returnList;
   }
	public  List<AlertVO> getJalavanilocationAndStatusDetailsInfo(JalavaniAlertsInputVO inputVO){
    	List<AlertVO> returnList = new ArrayList<AlertVO>();
   	 try {
   		 WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://mytdp.com/WebService/getJalavanilocationAndStatusDetailsInfo");
		// WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.146:8080/PartyAnalyst/WebService/getJalavanilocationAndStatusDetailsInfo");
	     ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
	     if (response.getStatus() != 200) {
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	     }else{
		 	    	String output = response.getEntity(String.class);
		 	    	if(output != null && !output.isEmpty()){
		 	    		JSONArray finalArray = new JSONArray(output);
		 	    		if(inputVO.getSearchType() !=null && inputVO.getSearchType().equalsIgnoreCase("Alert")){
			 	    		if(finalArray!=null && finalArray.length()>0){
			 	    			for(int i=0;i<finalArray.length();i++){
			 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
			 	    				AlertVO resultVo = new AlertVO();
			 	    				
			 	    				resultVo.setLocationId(jObj.getLong("id"));//locationId
			 	    				resultVo.setLocationName(jObj.getString("name"));
			 	    				resultVo.setCount(jObj.getLong("count"));
			 	    				resultVo.setPercentage(jObj.getDouble("percentage"));
			 	  
			 	    				
			 	    				returnList.add(resultVo);
			 	    			}
			 	    		}
			 	    		if(inputVO.getNewsType() !=null && inputVO.getNewsType().equalsIgnoreCase("PrintMedia") || inputVO.getNewsType() !=null && inputVO.getNewsType().equalsIgnoreCase("ElectronicMedia")){
			 	    			WebResource webResource2 = commonMethodsUtilService.getWebResourceObject("http://www.mytdp.com/CommunityNewsPortal/webservice/getjavalavaniLocationWiseInfo");
			 	    			//WebResource webResource2 = commonMethodsUtilService.getWebResourceObject("http://192.168.11.146:8080/CommunityNewsPortal/webservice/getjavalavaniLocationWiseInfo");
			 	    			ClientResponse response2 = webResource2.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
			 	  		        if(response2.getStatus() != 200){
			 	  		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response2.getStatus());
			 	  		 	     }else{
			 	  		 	    	String output2 = response2.getEntity(String.class);
			 	  		 	    	if(output2 != null && !output2.isEmpty()){
			 	  		 	    		JSONArray newsArray = new JSONArray(output2);
			 	  		 	    		if(finalArray!=null && newsArray.length()>0){
			 	  		 	    			for(int i=0;i<newsArray.length();i++){
			 	  		 	    				JSONObject jObj = (JSONObject) newsArray.get(i);
				 	  		 	    				if(returnList !=null && returnList.size() >0){
				 	  		 	    					for (AlertVO finalVo : returnList){
															if(finalVo.getLocationId() == jObj.getLong("id")){
																AlertVO newsVO = new AlertVO();
							 	  		 	    				
							 	  		 	    				newsVO.setAlertTypeName(jObj.getString("name"));
						 	  		 	    					newsVO.setPosCount(jObj.getLong("posCount"));
						 	  		 	    					newsVO.setNegCount(jObj.getLong("negCount"));
						 	  		 	    					newsVO.setCount(jObj.getLong("count"));
						 	  		 	    					newsVO.setPosPerc(jObj.getDouble("posPercent"));
						 	  		 	    					newsVO.setNegPerc(jObj.getDouble("negPercent"));
						 	  		 	    					
						 	  		 	    				finalVo.getSubList1().add(newsVO);	
															}
														}
				 	  		 	    				}
			 	  		 	    				
			 	  		 	    				}
			 	  		 	    			}
			 	  		 	    		}
			 	  		 	    	}
			 	    		}
				 	  	 }else if(inputVO.getSearchType() !=null && inputVO.getSearchType().equalsIgnoreCase("Status")){
				 	    		if(finalArray!=null && finalArray.length()>0){
				 	    			for(int i=0;i<finalArray.length();i++){
				 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
				 	    				AlertVO resultVo = new AlertVO();
				 	    				resultVo.setLocationId(jObj.getLong("statusId"));//locationId
				 	    				resultVo.setLocationName(jObj.getString("status"));//locationName
				 	    				
				 	    				
				 	    				JSONArray statusArr = jObj.getJSONArray("subList1");
		 		 	    				if(statusArr != null && statusArr.length() > 0){
		 		 	    					for (int j = 0; j < statusArr.length(); j++) {
		 		 	    						AlertVO statusVo = new AlertVO();
		 										JSONObject newsJson = (JSONObject) statusArr.get(j);
		 										
		 										statusVo.setStatusId(newsJson.getLong("statusId"));//statusId
		 										statusVo.setStatus(newsJson.getString("status"));//Status
		 										statusVo.setColor(newsJson.getString("color"));
		 										statusVo.setAlertCnt(newsJson.getLong("alertCnt"));
		 										statusVo.setPercentage(newsJson.getDouble("percentage"));
				 										
				 								resultVo.getSubList1().add(statusVo);
		 		 	    					}
		 		 	    					returnList.add(resultVo);
		 		 		 	    		}
				 	    			}
				 	    		}
						}
		 	    		
		 	    	}
		 	     }
   	 } catch (Exception e ){
   		 LOG.error(" Exception occured at getJalavanilocationAndStatusDetailsInfo() in JalavaniDashboardService class ", e);
   	 }
   	 return returnList;
   }
}
