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

import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.dao.IDistrictDAO;
import com.itgrids.dao.IParliamentAssemblyDAO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.PrisDataVo;
import com.itgrids.dto.PrisOverviewVo;
import com.itgrids.service.IPrisSurveyDashBaordService;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

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
	@Autowired
	private IDistrictDAO districtDAO;
	@Autowired
	private IParliamentAssemblyDAO parliamentAssemblyDAO;
	@Autowired
	private IConstituencyDAO constituencyDAO;
	
	
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
			 
			String fromDate = "01-01-2012";
			String toDate = "01-12-2017";
			
		  ClientResponse response = webServiceUtilService.getCallWebService("http://45.114.245.209/survey/api/?getPIRSSurveyInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+fromDate+"&toDate="+toDate);
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
			 	    		PrisDataVo totalVo = new PrisDataVo();
			 	    		JSONObject jObj = (JSONObject) finalArray.get(i);
			 	    		totalVo.setTotalHouseHolds(jObj.getLong("totalHouseHolds"));
			 	    		totalVo.setTargetOverall(jObj.getLong("target"));
			 	    		totalVo.setAchievedOverall(jObj.getLong("achived"));
			 	    		
			 	    		totalList.add(totalVo);
	 	    			}
	 	    		}
	 	    	}
	 	     }
	        ClientResponse response1 = webServiceUtilService.getCallWebService("http://45.114.245.209/survey/api/?getPIRSSurveyInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate());
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output1 = response1.getEntity(String.class);
	 	    	if(output1 != null && !output1.isEmpty()){
	 	    		JSONArray jsonArray = new JSONArray(output1);
	 	    		if(jsonArray!=null && jsonArray.length()>0){
	 	    			for(int i=0;i<jsonArray.length();i++){
			 	    		PrisDataVo subVo = new PrisDataVo();
			 	    		JSONObject jObj = (JSONObject) jsonArray.get(i);
			 	    		subVo.setSubTotal(jObj.getLong("totalHouseHolds"));
			 	    		subVo.setSubTarget(jObj.getLong("target"));
			 	    		subVo.setSubAchieved(jObj.getLong("achived"));
			 	    		
			 	    		subTotalList.add(subVo);
	 	    			}
	 	    		}
	 	    	}
	 	      }
	        
	        if(totalList != null && totalList.size() > 0){
	        	Long total = 0l;
	        	Long target = 0l;
	        	Long achieved = 0l;
	        	for (PrisDataVo returnVo : totalList){
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
		List<PrisOverviewVo> distOverViewLst = new ArrayList<PrisOverviewVo>(0);
		List<PrisOverviewVo> distLst = new ArrayList<PrisOverviewVo>(0);
		try {
			 Long  totalHouseHolds = 0l;
			 Long distId =0l;
			 String distName="";
			 Long constId=0l;
			 String constName="";
			 
			 /*if(inputVO.getLocationType().equalsIgnoreCase("district")){
				 response = webServiceUtilService.getCallWebService("http://45.114.245.209/survey/api/?getPIRSSurveyInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate());
			 }else if(inputVO.getLocationType().equalsIgnoreCase("constituency")){
				 if(inputVO.getSubFilterId() != null && inputVO.getSubFilterId() >0l){
					 response = webServiceUtilService.getCallWebService("http://45.114.245.209/survey/api/?getPIRSSurveyInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate()+"&filterId="+inputVO.getFilterId()+"&filterType="+inputVO.getFilterType()+"&subFilterId="+inputVO.getSubFilterId()+"&subFilterType="+inputVO.getSubFilterType());
				 }else{
					 response = webServiceUtilService.getCallWebService("http://45.114.245.209/survey/api/?getPIRSSurveyInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate()+"&filterId="+inputVO.getFilterId()+"&filterType="+inputVO.getFilterType());
				 }
				 
			 }else if(inputVO.getLocationType().equalsIgnoreCase("assembly")){
				 if(inputVO.getSubFilterId() != null && inputVO.getSubFilterId() >0l){
					 response = webServiceUtilService.getCallWebService("http://45.114.245.209/survey/api/?getPIRSSurveyInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate()+"&filterId="+inputVO.getFilterId()+"&filterType="+inputVO.getFilterType()+"&subFilterId="+inputVO.getSubFilterId()+"&subFilterType="+inputVO.getSubFilterType());
				 }else{
					 response = webServiceUtilService.getCallWebService("http://45.114.245.209/survey/api/?getPIRSSurveyInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate()+"&filterId="+inputVO.getFilterId()+"&filterType="+inputVO.getFilterType());
				 }
				 
			 }else if(inputVO.getLocationType().equalsIgnoreCase("mandal")){
				 if(inputVO.getFilterId() != null && inputVO.getFilterId() >0l){
					 response = webServiceUtilService.getCallWebService("http://45.114.245.209/survey/api/?getPIRSSurveyInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate()+"&filterId="+inputVO.getFilterId()+"&filterType="+inputVO.getFilterType());
				 }else if(inputVO.getSubFilterId() != null && inputVO.getSubFilterId() >0l){
					 response = webServiceUtilService.getCallWebService("http://45.114.245.209/survey/api/?getPIRSSurveyInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate()+"&filterId="+inputVO.getFilterId()+"&filterType="+inputVO.getFilterType()+"&subFilterId="+inputVO.getSubFilterId()+"&subFilterType="+inputVO.getSubFilterType());
				 }else{
					 response = webServiceUtilService.getCallWebService("http://45.114.245.209/survey/api/?getPIRSSurveyInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate());
				 }
				 	
			 }*/
			
			 WebResource webResource = null;

				if(inputVO.getSubFilterType() != null && !inputVO.getSubFilterType().trim().isEmpty())
					webResource = commonMethodsUtilService.getWebResourceObject("http://45.114.245.209/survey/api/?getPIRSSurveyInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&filterType="+inputVO.getFilterType()+"&filterId="+inputVO.getFilterId()+"&subFilterType="+inputVO.getSubFilterType()+"&subFilterId="+inputVO.getSubFilterId()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate());
				else if(inputVO.getFilterType() != null && !inputVO.getFilterType().trim().isEmpty())
					webResource = commonMethodsUtilService.getWebResourceObject("http://45.114.245.209/survey/api/?getPIRSSurveyInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&filterType="+inputVO.getFilterType()+"&filterId="+inputVO.getFilterId()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate());
				else	
					webResource = commonMethodsUtilService.getWebResourceObject("http://45.114.245.209/survey/api/?getPIRSSurveyInfo=true&locationId="+inputVO.getLocationId()+"&locationType="+inputVO.getLocationType()+"&fromDate="+inputVO.getFromDate()+"&toDate="+inputVO.getToDate());
				
				ClientResponse response = webResource.accept("application/json").type("application/json").get(ClientResponse.class);
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
	 	    				if (jObj.isNull("totalHouseHolds")){
	 	    					totalHouseHolds = 0l;
	 	    				} else {
	 	    					totalHouseHolds = jObj.getLong("totalHouseHolds");
	 	    				}
	 	    				
	 	    				if (jObj.isNull("districtId")){
	 	    					distId = 0l;
	 	    				} else {
	 	    					distId = jObj.getLong("districtId");
	 	    				}
	 	    				
	 	    				if (jObj.isNull("districtName")){
	 	    					distName = "";
	 	    				} else {
	 	    					distName = jObj.getString("districtName");
	 	    				}
	 	    				
	 	    				if (jObj.isNull("constituencyId")){
	 	    					constId = 0l;
	 	    				} else {
	 	    					constId = jObj.getLong("constituencyId");
	 	    				}
	 	    				
	 	    				if (jObj.isNull("constituencyName")){
	 	    					constName = "";
	 	    				} else {
	 	    					constName = jObj.getString("constituencyName");
	 	    				}
	 	    				
	 	    				vo.setTotal(totalHouseHolds);
	 	    				vo.setTarget(jObj.getLong("target"));
	 	    				vo.setAchieved(jObj.getLong("achived"));
	 	    				vo.setAchievedPercentage(vo.getTarget() > 0 ? round(((vo.getAchieved()*100.00)/vo.getTarget()),2):0.00);
	 	    				
	 	    				vo.setDistrictId(distId);
	 	    				vo.setDistrictName(distName);
	 	    				vo.setConstituencyId(constId);
	 	    				vo.setConstituencyName(constName);
	 	    				
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
	public List<IdNameVO> getAllDistricts(){
	  List<IdNameVO> idNameVOList= new ArrayList<IdNameVO>(0);
	  try
	 {
		List<Object[]> districtList= districtDAO.getDistrictIdName(1l);
		
		if(districtList!=null && districtList.size()>0){
			for (Object[] objects : districtList){
				IdNameVO idNameVO=new IdNameVO();
				
				idNameVO.setId((Long)objects[0]);
				idNameVO.setName(objects[1].toString());
				idNameVOList.add(idNameVO);
			}
		 }
	 }catch (Exception e){
		LOG.error("Exception raised in getAllDistricts", e);
	 }	
	  	return  idNameVOList;
	}
	public List<IdNameVO> getAllParliaments(){
		  List<IdNameVO> idNameVOList= new ArrayList<IdNameVO>(0);
		  try
		 {
			List<Object[]> objList= parliamentAssemblyDAO.getAllParliaments();
			
			if(objList!=null && objList.size()>0){
				for (Object[] objects : objList){
					IdNameVO idNameVO=new IdNameVO();
					
					idNameVO.setId((Long)objects[0]);
					idNameVO.setName(objects[1].toString());
					idNameVOList.add(idNameVO);
				}
			 }
		 }catch (Exception e){
			LOG.error("Exception raised in getAllDistricts", e);
		 }	
		  	return  idNameVOList;
	}
	public List<IdNameVO> getAllConstituenciesForDistrict(InputVO inputVO){
		  List<IdNameVO> idNameVOList= new ArrayList<IdNameVO>(0);
		  try
		 {
			List<Object[]> objList= constituencyDAO.getConstituencyListByDistrictId(inputVO.getDistrictId());
			
			if(objList!=null && objList.size()>0){
				for (Object[] objects : objList){
					IdNameVO idNameVO=new IdNameVO();
					
					idNameVO.setId((Long)objects[0]);
					idNameVO.setName(objects[1].toString());
					idNameVOList.add(idNameVO);
				}
			 }
		 }catch (Exception e){
			LOG.error("Exception raised in getAllDistricts", e);
		 }	
		  	return  idNameVOList;
	}
	public List<IdNameVO> getAllConstituenciesForParliament(Long parliamentId){
		  List<IdNameVO> idNameVOList= new ArrayList<IdNameVO>(0);
		  try
		 {
			List<Object[]> objList= parliamentAssemblyDAO.getParliamentByConstIdAndName(parliamentId);
			
			if(objList!=null && objList.size()>0){
				for (Object[] objects : objList){
					IdNameVO idNameVO=new IdNameVO();
					
					idNameVO.setId((Long)objects[0]);
					idNameVO.setName(objects[1].toString());
					idNameVOList.add(idNameVO);
				}
			 }
		 }catch (Exception e){
			LOG.error("Exception raised in getAllDistricts", e);
		 }	
		  	return  idNameVOList;
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
