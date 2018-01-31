package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonObject;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.NTRSujalaMotherPlantVO;
import com.itgrids.dto.NTRSujalaRduVO;
import com.itgrids.service.INTRSujalaDashboardService;
import com.itgrids.service.integration.external.ItcWebServiceUtilService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Service
@Transactional
public class NTRSujalaDashboardService implements INTRSujalaDashboardService{

	private static final Logger LOG = Logger.getLogger(NTRSujalaDashboardService.class);
	@Autowired
	private ItcWebServiceUtilService itcWebServiceUtilService;
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	
	
	/**
	 * @author Nandhini.k
	 * @description {This service is get Last 30 Days MothPlantDetails.}
	 * @return NTRSujalaMotherPlantVO
	 * @Date 30-01-2018
	 */
	public NTRSujalaMotherPlantVO getLast30DaysMotherPlantDetails(InputVO inputVO){
		NTRSujalaMotherPlantVO finalVO  = new NTRSujalaMotherPlantVO();
		try {
			 String locationName = null;
			 Long mpId = 0L;
		      String URL = "http://13.126.165.56:9190/wms-api/minister-dashboard/mp30dayov/"+inputVO.getLocationId()+"";
		      WebResource webResource = commonMethodsUtilService.getWebResourceObject(URL);
		      ClientResponse response = webResource.header("Auth_Token","a59381782ca0e6f922e6c65bccc1ba05").get(ClientResponse.class);;
		      if (response.getStatus() != 200) {
		            throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		        } else {
		        String output = response.getEntity(String.class);
		        if(output != null && !output.isEmpty()){
		        locationName = new org.json.JSONObject(output).get("name").toString();
		        mpId = Long.valueOf(new org.json.JSONObject(output).get("motherPlantId").toString());
		          JSONArray finalArray = new JSONArray(new org.json.JSONObject(output).get("data").toString());
		          if (finalArray != null && finalArray.length() > 0) {
		            for (int i = 0; i < finalArray.length(); i++) {
		              JSONObject jObj = (JSONObject) finalArray.get(i);
		              NTRSujalaMotherPlantVO vo = new NTRSujalaMotherPlantVO();
		              	vo.setDate(jObj.get("date").toString() != null ?jObj.get("date").toString():"");
		              	vo.setPlantHealthStatus(jObj.get("plantHealth").toString() != null ?jObj.get("plantHealth").toString():"");
		              	vo.setWaterQuanStatus(jObj.get("waterQality").toString() != null ?jObj.get("waterQality").toString():"");
		              	vo.setTdsCount(Long.valueOf(jObj.get("tds").toString() != null ?jObj.get("tds").toString():"0"));
		              	vo.setPhCount(Long.valueOf(jObj.get("ph").toString() != null ?jObj.get("ph").toString():"0"));
		              	vo.setCustomers(Long.valueOf(jObj.get("customers").toString() != null ?jObj.get("customers").toString():"0"));
		              	vo.setRduCount(Long.valueOf(jObj.get("rdusCount").toString() != null ?jObj.get("rdusCount").toString():"0"));
		              	if(jObj.has("highDispanceRDU")){
		              		JSONObject highDispObj = (JSONObject) jObj.get("highDispanceRDU");
			              	vo.setHighDispanceRDUName(highDispObj.get("name").toString() != null ?highDispObj.get("name").toString():"");
			              	vo.setHighDispanceRDUCount(Long.valueOf(highDispObj.get("dispanceLtrs").toString() != null ?highDispObj.get("dispanceLtrs").toString():"0"));
		              	}
		              	if(jObj.has("lowDispanceRDU")){
		              		JSONObject lowDispObj = (JSONObject) jObj.get("lowDispanceRDU");
			              	vo.setLowDispanceRDUName(lowDispObj.get("name").toString() != null ?lowDispObj.get("name").toString():"");
			              	vo.setLowDispanceRDUCount(Long.valueOf(lowDispObj.get("dispanceLtrs").toString() != null ?lowDispObj.get("dispanceLtrs").toString():"0"));
		              	}
		              	finalVO.getSubList().add(vo);
		            }
		          }
		        }
		      }
		      finalVO.setId(mpId);
		      finalVO.setName(locationName);
		      
		} catch (Exception e) {
			LOG.error("Exception occured at getLast30DaysMotherPlantDetails() in  ItcDashboardService class",e);
		}
		return finalVO;
	}
	
	/**
	 * @author Nandhini.k
	 * @description {This service is get Last 30 Days RDU Details.}
	 * @return NTRSujalaRduVO
	 * @Date 30-01-2018
	 */
	public NTRSujalaRduVO getLast30DaysRDUDetails(InputVO inputVO){
		NTRSujalaRduVO finalVO  = new NTRSujalaRduVO();
		try {
			 String locationName = null;
			 Long rduId = 0L;
			 Long waterTankCap = 0L;
		      String URL = "http://13.126.165.56:9190/wms-api/minister-dashboard/rdu30dayov/"+inputVO.getLocationId()+"";
		      WebResource webResource = commonMethodsUtilService.getWebResourceObject(URL);
		      ClientResponse response = webResource.header("Auth_Token","a59381782ca0e6f922e6c65bccc1ba05").get(ClientResponse.class);;
		      if (response.getStatus() != 200) {
		            throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		        } else {
		        String output = response.getEntity(String.class);
		        if(output != null && !output.isEmpty()){
			        locationName = new org.json.JSONObject(output).get("name").toString();
			        rduId = Long.valueOf(new org.json.JSONObject(output).get("rduId").toString());
			        waterTankCap = Long.valueOf(new org.json.JSONObject(output).get("waterTankCapacity").toString());
			          JSONArray finalArray = new JSONArray(new org.json.JSONObject(output).get("data").toString());
		          if (finalArray != null && finalArray.length() > 0) {
		            for (int i = 0; i < finalArray.length(); i++) {
		              JSONObject jObj = (JSONObject) finalArray.get(i);
		              NTRSujalaRduVO vo = new NTRSujalaRduVO();
		              	vo.setDate(jObj.get("date").toString() != null ?jObj.get("date").toString():"");
		              	vo.setRduHealthStatus(jObj.get("rduHealth").toString() != null ?jObj.get("rduHealth").toString():"");
		              	vo.setOldCustomers(Long.valueOf(jObj.get("oldCustomers").toString() != null ?jObj.get("oldCustomers").toString():"0"));
		              	vo.setNewCustomers(Long.valueOf(jObj.get("newCustomers").toString() != null ?jObj.get("newCustomers").toString():"0"));
		              	vo.setTotalCustomers(vo.getOldCustomers()+vo.getNewCustomers());
		              	vo.setWaterDispence(Long.valueOf(jObj.get("dispenceWaterQuantity").toString() != null ?jObj.get("dispenceWaterQuantity").toString():"0"));
		              	vo.setWaterDispenceSeparation(commonMethodsUtilService.calculateAmountInWordsFrCrore(vo.getWaterDispence()));
		              	finalVO.getSubList().add(vo);
		            }
		          }
		        }
		      }
		      finalVO.setId(rduId);
		      finalVO.setName(locationName);
		      finalVO.setWaterTankCapacity(waterTankCap);
		      
		} catch (Exception e) {
			LOG.error("Exception occured at getLast30DaysMotherPlantDetails() in  ItcDashboardService class",e);
		}
		return finalVO;
	}
}
