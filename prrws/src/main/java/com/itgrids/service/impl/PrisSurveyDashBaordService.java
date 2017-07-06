package com.itgrids.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.PrisDataVo;
import com.itgrids.dto.PrisOverviewVo;
import com.itgrids.service.IPrisSurveyDashBaordService;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.sun.jersey.api.client.ClientResponse;

@Service
@Transactional
public class PrisSurveyDashBaordService implements IPrisSurveyDashBaordService{
	private static final Logger LOG = Logger.getLogger(PrisSurveyDashBaordService.class);
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	@Autowired
	private DateUtilService dateUtilService;
	
	
	
	/*
	 * Date : 07/07/2017
	 * Author :Teja
	 * @description : getPrisSurveyBasicData
	 */
	public PrisDataVo getPrisSurveyBasicData(InputVO inputVO){
		PrisDataVo finalVo = new PrisDataVo();
		List<PrisDataVo> totalList = new ArrayList<PrisDataVo>();
		List<PrisDataVo> subTotalList = new ArrayList<PrisDataVo>();
		
		try {
			 
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			List<Date> datesList = dateUtilService.getDatesOfCurrentMonth();
			String fromDate = sdf.format(datesList.get(0)); 
			String toDate = sdf.format(datesList.get(datesList.size()-1));
			
			ClientResponse response = webServiceUtilService.callWebService("http://130.211.128.117/survey/api/?getPIRSSurveyInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate(), inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
			 	    		PrisDataVo vo = new PrisDataVo();
			 	    		JSONObject jObj = (JSONObject) finalArray.get(i);
			 	    		vo.setTotalHouseHolds(jObj.getLong("totalHouseHolds"));
			 	    		vo.setTargetOverall(jObj.getLong("target"));
			 	    		vo.setAchievedOverall(jObj.getLong("achived"));
			 	    		
			 	    		totalList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	     }
	        ClientResponse response1 = webServiceUtilService.callWebService("http://130.211.128.117/survey/api/?getPIRSSurveyInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+fromDate+"&toDate="+toDate, inputVO);
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output1 = response1.getEntity(String.class);
	 	    	if(output1 != null && !output1.isEmpty()){
	 	    		JSONArray jsonArray = new JSONArray(output1);
	 	    		if(jsonArray!=null && jsonArray.length()>0){
	 	    			for(int i=0;i<jsonArray.length();i++){
			 	    		PrisDataVo vo = new PrisDataVo();
			 	    		JSONObject jObj = (JSONObject) jsonArray.get(i);
			 	    		vo.setSubTotal(jObj.getLong("totalHouseHolds"));
			 	    		vo.setSubTarget(jObj.getLong("target"));
			 	    		vo.setSubAchieved(jObj.getLong("achived"));
			 	    		
			 	    		subTotalList.add(vo);
	 	    			}
	 	    		}
	 	    	}
	 	      }
	        
	        if(totalList != null && totalList.size() > 0){
	        	Long total = 0l;
	        	Long target = 0l;
	        	Long achieved = 0l;
	        	for (PrisDataVo returnVo : totalList) {
	        		total = total+returnVo.getTotalHouseHolds();
	        		target = target+returnVo.getTargetOverall();
	        		achieved =achieved+returnVo.getAchievedOverall();
				}
	        	finalVo.setTotalHouseHolds(total);
        		finalVo.setTargetOverall(target);
        		finalVo.setAchievedOverall(achieved);
        		finalVo.setTargetOverallPercent(caclPercantage(target,total));
        		finalVo.setAchievedOverallpercent(caclPercantage(achieved,total));
	        }
	        if(subTotalList != null && subTotalList.size() >0){
	        	Long total1 = 0l;
	        	Long target1 = 0l;
	        	Long achieved1 = 0l;
	        	for (PrisDataVo returnVo1 : subTotalList) {
	        		total1 = total1+returnVo1.getSubTotal();
	        		target1 = target1+returnVo1.getSubTarget();
	        		achieved1 =achieved1+returnVo1.getSubAchieved();
				}
	        	finalVo.setSubTotal(total1);
        		finalVo.setSubTarget(target1);
        		finalVo.setSubAchieved(achieved1);
        		finalVo.setSubTargetPercentage(caclPercantage(target1,total1));
        		finalVo.setSubAchievedPercentage(caclPercantage(achieved1,total1));
	        }
		} catch (Exception e){
			LOG.error("Exception raised at getPrisSurveyBasicData - SurveyDashBaordService service", e);
		}
		return finalVo;
		
		
		
	  }
	/*
	 * Date : 07/07/2017
	 * Author :Teja
	 * @description : getPIRSSurveyInfo
	 */
	public PrisOverviewVo getPIRSSurveyInfo(InputVO inputVO){
		PrisOverviewVo finalVo = new PrisOverviewVo();
		ClientResponse response = null;
		List<PrisOverviewVo> distOverViewLst = new ArrayList<PrisOverviewVo>(0);
		List<PrisOverviewVo> distLst = new ArrayList<PrisOverviewVo>(0);
		try {
			response = webServiceUtilService.callWebService("http://130.211.128.117/survey/api/?getPIRSSurveyInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate(), inputVO);
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				PrisOverviewVo vo = new PrisOverviewVo();
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				vo.setId(jObj.getLong("id"));
	 	    				vo.setName(jObj.getString("name"));
	 	    				vo.setTotal(jObj.getLong("totalHouseHolds"));
	 	    				vo.setTarget(jObj.getLong("target"));
	 	    				vo.setAchieved(jObj.getLong("achived"));
	 	    				vo.setAchievedPercentage(new BigDecimal(jObj.getString("percentage")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				
	 	    				distOverViewLst.add(vo);
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
	      
	        finalVo.setSubList(distOverViewLst);
	        finalVo.setVoList(distLst);
		} catch (Exception e) {
			LOG.error("Exception raised at getPIRSSurveyInfo - SurveyDashBaordService service", e);
		}
		return finalVo;
	}
	public Double caclPercantage(Long subCount,Long totalCount){
		Double d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return d;
	}
}
