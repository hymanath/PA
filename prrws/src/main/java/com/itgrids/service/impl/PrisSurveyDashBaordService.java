package com.itgrids.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.BasicVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.NregsProjectsVO;
import com.itgrids.dto.PrisDataVo;
import com.itgrids.dto.PrisOverviewVo;
import com.itgrids.service.IPrisSurveyDashBaordService;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.sun.jersey.api.client.ClientResponse;

@Service
@Transactional
public class PrisSurveyDashBaordService implements IPrisSurveyDashBaordService{
	private static final Logger LOG = Logger.getLogger(PrisSurveyDashBaordService.class);
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	
	/*
	 * Date : 07/07/2017
	 * Author :Teja
	 * @description : getPrisSurveyBasicData
	 */
	public PrisDataVo getPrisSurveyBasicData(InputVO inputVO){
		PrisDataVo finalVo = new PrisDataVo();
		try {
			 
			ClientResponse response = webServiceUtilService.callWebService("", inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jObj = new JSONObject(output);
	 	    		finalVo.setTotalHouseHolds(jObj.getLong("1023456"));
	 	    		finalVo.setTargetOverall(jObj.getLong("12345"));
	 	    		finalVo.setTargetOverallPercent(new BigDecimal(jObj.getString("20.678686")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    		finalVo.setAchievedOverall(jObj.getLong("34567"));
	 	    		finalVo.setAchievedOverallpercent(new BigDecimal(jObj.getString("30.234324")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    		finalVo.setSubTarget(jObj.getLong("8765"));
	 	    		finalVo.setSubTargetPercentage(new BigDecimal(jObj.getString("40.765676")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    		finalVo.setSubAchieved(jObj.getLong("456789"));
	 	    		finalVo.setSubAchievedPercentage(new BigDecimal(jObj.getString("60.657567")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    		
	 	    	}
	 	      }
		} catch (Exception e){
			LOG.error("Exception raised at getPrisSurveyBasicData - SurveyDashBaordService service", e);
		}
		return finalVo;
	  }
	/*
	 * Date : 07/07/2017
	 * Author :Teja
	 * @description : getPrisLocationWiseOverview
	 */
	public PrisOverviewVo getPrisLocationWiseOverview(InputVO inputVO){
		PrisOverviewVo finalVo = new PrisOverviewVo();
		ClientResponse response = null;
		List<PrisOverviewVo> distOverViewLst = new ArrayList<PrisOverviewVo>();
		List<PrisOverviewVo> consOverViewLst = new ArrayList<PrisOverviewVo>();
		List<PrisOverviewVo> mandalOverViewLst = new ArrayList<PrisOverviewVo>();
		List<PrisOverviewVo> distLst = new ArrayList<PrisOverviewVo>();
		List<PrisOverviewVo> consLst = new ArrayList<PrisOverviewVo>();
		try {
			if(inputVO.getType().equalsIgnoreCase("district")){
				response = webServiceUtilService.callWebService("", inputVO);
			}
			else if(inputVO.getType().equalsIgnoreCase("parliament")){
				response = webServiceUtilService.callWebService("", inputVO);
			}
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 String output1 = response.getEntity(String.class);
	 	    	 String output2 = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				PrisOverviewVo vo = new PrisOverviewVo();
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				vo.setId(jObj.getLong("2"));
	 	    				vo.setName(jObj.getString("nellore"));
	 	    				vo.setTotal(jObj.getLong("34567"));
	 	    				vo.setTarget(jObj.getLong("98765"));
	 	    				vo.setAchieved(jObj.getLong("34567"));
	 	    				vo.setAchievedPercentage(new BigDecimal(jObj.getString("45.4234234")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				
	 	    				distOverViewLst.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	    	if(output1 != null && !output1.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output1);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				PrisOverviewVo vo = new PrisOverviewVo();
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				vo.setId(jObj.getLong("23"));
	 	    				vo.setName(jObj.getString("Allur"));
	 	    				vo.setTotal(jObj.getLong("34567"));
	 	    				vo.setTarget(jObj.getLong("98765"));
	 	    				vo.setAchieved(jObj.getLong("34567"));
	 	    				vo.setAchievedPercentage(new BigDecimal(jObj.getString("45.4234234")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				
	 	    				consOverViewLst.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	    	if(output2 != null && !output2.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output2);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				PrisOverviewVo vo = new PrisOverviewVo();
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				vo.setId(jObj.getLong("231"));
	 	    				vo.setName(jObj.getString("kodavalur"));
	 	    				vo.setTotal(jObj.getLong("34567"));
	 	    				vo.setTarget(jObj.getLong("98765"));
	 	    				vo.setAchieved(jObj.getLong("34567"));
	 	    				vo.setAchievedPercentage(new BigDecimal(jObj.getString("45.4234234")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				
	 	    				mandalOverViewLst.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	      }
	        if(distOverViewLst != null && distOverViewLst.size() > 0){
	        	for (PrisOverviewVo prisOverviewVo : distOverViewLst) {
	        		PrisOverviewVo vo = new PrisOverviewVo();
	        		vo.setId(prisOverviewVo.getId());
	        		vo.setName(prisOverviewVo.getName());
	        		
	        		distLst.add(vo);
				}
	        }
	        if(consOverViewLst != null && consOverViewLst.size() > 0){
	        	for (PrisOverviewVo prisOverviewVo : consOverViewLst) {
	        		PrisOverviewVo vo = new PrisOverviewVo();
	        		vo.setId(prisOverviewVo.getId());
	        		vo.setName(prisOverviewVo.getName());
	        		
	        		consLst.add(vo);
				}
	        }
	        finalVo.setDistOverviewList(distOverViewLst);
	        finalVo.setConsOverviewList(consOverViewLst);
	        finalVo.setMandalOverviewList(mandalOverViewLst);
	        finalVo.setDistList(distLst);
	        finalVo.setConsList(consLst);
		} catch (Exception e) {
			LOG.error("Exception raised at getPrisLocationWiseOverview - SurveyDashBaordService service", e);
		}
		return finalVo;
	}
}
