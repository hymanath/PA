package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.NTRSujalaMotherPlantVO;
import com.itgrids.dto.NTRSujalaOverviewVO;
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
		      ClientResponse response = webResource.header("Auth_Token","a59381782ca0e6f922e6c65bccc1ba05").get(ClientResponse.class);
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
		              	vo.setMpWaterDispenced(jObj.get("dispenceWaterQuantity").toString() != null ?jObj.get("dispenceWaterQuantity").toString():"");
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
			LOG.error("Exception occured at getLast30DaysMotherPlantDetails() in  NTRSujalaDashboardService class",e);
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
		      ClientResponse response = webResource.header("Auth_Token","a59381782ca0e6f922e6c65bccc1ba05").get(ClientResponse.class);
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
			LOG.error("Exception occured at getLast30DaysMotherPlantDetails() in  NTRSujalaDashboardService class",e);
		}
		return finalVO;
	}
	
	/**
	 * @author SRAVANTH
	 * @description {This service is get Overview Details.}
	 * @return NTRSujalaOverviewVO
	 * @Date 31-01-2018
	 */
	public NTRSujalaOverviewVO getNtrSujalaOverview(){
		NTRSujalaOverviewVO returnvo = new NTRSujalaOverviewVO();
		try {
			String URL = "http://13.126.165.56:9190/wms-api/minister-dashboard/mpsoverview";
		    WebResource webResource = commonMethodsUtilService.getWebResourceObject(URL);
		    ClientResponse response = webResource.header("Auth_Token","a59381782ca0e6f922e6c65bccc1ba05").header("organizationId", "1").get(ClientResponse.class);
		    if (response.getStatus() != 200) {
	            throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	        } else {
	        	String output = response.getEntity(String.class);
		        if(output != null && !output.isEmpty()){
		        	JSONObject Obj = new JSONObject(output);
		        	if(Obj != null){
		        		returnvo.setTotalMotherPlants(Obj.getLong("totalMotherPlants"));
		        		returnvo.setActiveMotherPlants(Obj.getLong("activeMotherPlants"));
		        		returnvo.setInActiveMotherPlants(Obj.getLong("inActiveMotherPlants"));
		        		returnvo.setMpGoodWaterQuality(Obj.getLong("mpGoodWaterQuality"));
		        		returnvo.setMpBadWaterQuality(Obj.getLong("mpBadWaterQuality"));
		        		returnvo.setMpSafeWaterDispenced(Obj.getString("mpSafeWaterDispenced"));
		        		returnvo.setTotalRDUs(Obj.getLong("totalRDUs"));
		        		returnvo.setActiveRDUs(Obj.getLong("activeRDUs"));
		        		returnvo.setInActiveRDUs(Obj.getLong("inActiveRDUs"));
		        		returnvo.setRduGoodWaterQuality(Obj.getLong("rduGoodWaterQuality"));
		        		returnvo.setRduBadWaterQuality(Obj.getLong("rduBadWaterQuality"));
		        		returnvo.setRduSellWater(commonMethodsUtilService.seperateNumberByComma(Long.valueOf(Obj.getString("rduSellWater"))));
		        		if(Obj.has("highDispanceRDU")){
		        			JSONObject highDspncObj = Obj.getJSONObject("highDispanceRDU");
		        			returnvo.setHighDispanceRDUName(highDspncObj.getString("name"));
		        			returnvo.setHighRDUDispanceLtrs(commonMethodsUtilService.seperateNumberByComma(Long.valueOf(highDspncObj.getString("dispanceLtrs"))));
		        		}
		        		if(Obj.has("lowDispanceRDU")){
		        			JSONObject lowDspncObj = Obj.getJSONObject("lowDispanceRDU");
		        			returnvo.setLowDispanceRDUName(lowDspncObj.getString("name"));
		        			returnvo.setLowRDUDispanceLtrs(commonMethodsUtilService.seperateNumberByComma(Long.valueOf(lowDspncObj.getString("dispanceLtrs"))));
		        		}
		        		if(Obj.has("customers")){
		        			JSONObject customersObj = Obj.getJSONObject("customers");
		        			returnvo.setActiveCustomers(customersObj.getLong("active"));
		        			returnvo.setInActiveCustomers(customersObj.getLong("inActive"));
		        			returnvo.setTotalCustomers(returnvo.getActiveCustomers()+returnvo.getInActiveCustomers());
		        		}
		        		JSONArray mpArr = new JSONArray(Obj.get("motherPlants").toString());
		        		returnvo.setDistrictList(getDistrictWiseMotherPlants(mpArr));
		        		returnvo.setMotherPlantsList(getMotherPlantsList(mpArr));
		        		returnvo.setRdusList(getTotalRDUSList(mpArr));
		        	}
		        }
	        }
		} catch (Exception e) {
			LOG.error("Exception occured at getNtrSujalaOverview() in  NTRSujalaDashboardService class",e);
		}
		return returnvo;
	}
	
	public List<NTRSujalaOverviewVO> getTotalRDUSList(JSONArray finalArr){
		List<NTRSujalaOverviewVO> returnList = new ArrayList<NTRSujalaOverviewVO>();
		try {
			if(finalArr != null && finalArr.length() > 0){
				for (int i = 0; i < finalArr.length(); i++) {
					JSONObject obj = new JSONObject(finalArr.get(i).toString());
					if(obj.has("rdus")){
	        			JSONArray rdusArr = new JSONArray(obj.get("rdus").toString());
	        			if(rdusArr != null && rdusArr.length() > 0){
	        				for (int j = 0; j < rdusArr.length(); j++) {
								JSONObject rduObj = new JSONObject(rdusArr.get(j).toString());
								if(rduObj != null && rduObj.length() > 0){
									NTRSujalaOverviewVO rduvo = new NTRSujalaOverviewVO();
									rduvo.setMpId(obj.getLong("motherPlantId"));
									rduvo.setMpName(obj.getString("name"));
									rduvo.setMandal(obj.has("mandal") ? obj.getString("mandal") : "");
									rduvo.setDistrict(obj.has("district") ? obj.getString("district") : "");
									
									rduvo.setId(rduObj.getLong("rduId"));
									rduvo.setName(rduObj.getString("name"));
									rduvo.setLocation(rduObj.getString("location"));
									rduvo.setLatitude(rduObj.getString("latitude"));
									rduvo.setLongitude(rduObj.getString("longitude"));
									rduvo.setWaterTankCapacity(rduObj.getLong("waterTankCapacity"));
									rduvo.setMpSafeWaterDispenced(commonMethodsUtilService.seperateNumberByComma(Long.valueOf(rduObj.getString("waterDispencePerDay"))));
									rduvo.setOldCustomers(rduObj.getLong("oldCustomers"));
									rduvo.setNewCustomers(rduObj.getLong("newCustomers"));
									rduvo.setTotalCustomers(rduvo.getOldCustomers()+rduvo.getNewCustomers());
									returnList.add(rduvo);
								}
							}
	        			}
	        		}
	        	}
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getTotalRDUSList() in  NTRSujalaDashboardService class",e);
		}
		return returnList;
	}
	
	public List<NTRSujalaOverviewVO> getDistrictWiseMotherPlants(JSONArray finalArr){
		List<NTRSujalaOverviewVO> returnList = new ArrayList<NTRSujalaOverviewVO>();
		try {
			Map<String,NTRSujalaOverviewVO> districtMap = new LinkedHashMap<String,NTRSujalaOverviewVO>();
			if(finalArr != null && finalArr.length() > 0){
				for (int i = 0; i < finalArr.length(); i++) {
					JSONObject obj = new JSONObject(finalArr.get(i).toString());
					//if(obj.get("motherPlantId") != null && Long.valueOf(obj.get("motherPlantId").toString()) > 1L){
						String districtName = obj.getString("district").toUpperCase();
						NTRSujalaOverviewVO vo = districtMap.get(districtName);
						if(vo == null){
							vo = new NTRSujalaOverviewVO();
							vo.setName(districtName);
							NTRSujalaOverviewVO mpvo = new NTRSujalaOverviewVO();
							mpvo.setId(obj.getLong("motherPlantId"));
							mpvo.setName(obj.getString("name"));
							mpvo.setHealth(obj.getString("health"));
							mpvo.setWaterQuality(obj.getString("waterQuality"));
							mpvo.setMpSafeWaterDispenced(obj.getString("safeWaterDispence"));
							mpvo.setActiveRDUs(obj.getLong("activeRDUs"));
							mpvo.setInActiveRDUs(obj.getLong("inActiveRDUs"));
							mpvo.setTotalRDUs(mpvo.getActiveRDUs()+mpvo.getInActiveRDUs());
							mpvo.setLocation(obj.has("localtion") ? obj.getString("localtion") : "");
							mpvo.setMobileNo(obj.has("contactNo") ? obj.getString("contactNo") : "");
							mpvo.setTds(obj.has("tds") ? obj.getString("tds") : "");
							mpvo.setPh(obj.has("ph") ? obj.getString("ph") : "");
							mpvo.setMandal(obj.has("mandal") ? obj.getString("mandal") : "");
							mpvo.setDistrict(obj.has("district") ? obj.getString("district") : "");
							mpvo.setLatitude(obj.has("latitude") ? obj.getString("latitude") : "");
							mpvo.setLongitude(obj.has("longitude") ? obj.getString("longitude") : "");
							
							if(obj.has("highDispanceRDU")){
			        			JSONObject highDspncObj = obj.getJSONObject("highDispanceRDU");
			        			mpvo.setHighDispanceRDUName(highDspncObj.getString("name"));
			        			mpvo.setHighRDUDispanceLtrs(commonMethodsUtilService.seperateNumberByComma(Long.valueOf(highDspncObj.getString("dispanceLtrs"))));
			        		}
			        		if(obj.has("lowDispanceRDU")){
			        			JSONObject lowDspncObj = obj.getJSONObject("lowDispanceRDU");
			        			mpvo.setLowDispanceRDUName(lowDspncObj.getString("name"));
			        			mpvo.setLowRDUDispanceLtrs(commonMethodsUtilService.seperateNumberByComma(Long.valueOf(lowDspncObj.getString("dispanceLtrs"))));
			        		}
			        		if(obj.has("customers")){
			        			JSONObject customersObj = obj.getJSONObject("customers");
			        			mpvo.setActiveCustomers(customersObj.getLong("active"));
			        			mpvo.setInActiveCustomers(customersObj.getLong("inActive"));
			        			mpvo.setTotalCustomers(mpvo.getActiveCustomers()+mpvo.getInActiveCustomers());
			        		}
			        		if(obj.has("rdus")){
			        			JSONArray rdusArr = new JSONArray(obj.get("rdus").toString());
			        			if(rdusArr != null && rdusArr.length() > 0){
			        				for (int j = 0; j < rdusArr.length(); j++) {
										JSONObject rduObj = new JSONObject(rdusArr.get(j).toString());
										if(rduObj != null && rduObj.length() > 0){
											NTRSujalaOverviewVO rduvo = new NTRSujalaOverviewVO();
											rduvo.setId(rduObj.getLong("rduId"));
											rduvo.setName(rduObj.getString("name"));
											rduvo.setLocation(rduObj.getString("location"));
											rduvo.setLatitude(rduObj.getString("latitude"));
											rduvo.setLongitude(rduObj.getString("longitude"));
											rduvo.setWaterTankCapacity(rduObj.getLong("waterTankCapacity"));
											rduvo.setMpSafeWaterDispenced(rduObj.getString("waterDispencePerDay"));
											rduvo.setOldCustomers(rduObj.getLong("oldCustomers"));
											rduvo.setNewCustomers(rduObj.getLong("newCustomers"));
											rduvo.setTotalCustomers(rduvo.getOldCustomers()+rduvo.getNewCustomers());
											mpvo.getSubList().add(rduvo);
										}
									}
			        			}
			        		}
			        		vo.getSubList().add(mpvo);
			        		districtMap.put(districtName, vo);
						}else{
							NTRSujalaOverviewVO mpvo = new NTRSujalaOverviewVO();
							mpvo.setId(obj.getLong("motherPlantId"));
							mpvo.setName(obj.getString("name"));
							mpvo.setHealth(obj.getString("health"));
							mpvo.setWaterQuality(obj.getString("waterQuality"));
							mpvo.setMpSafeWaterDispenced(obj.getString("safeWaterDispence"));
							mpvo.setActiveRDUs(obj.getLong("activeRDUs"));
							mpvo.setInActiveRDUs(obj.getLong("inActiveRDUs"));
							mpvo.setTotalRDUs(mpvo.getActiveRDUs()+mpvo.getInActiveRDUs());
							mpvo.setLocation(obj.has("localtion") ? obj.getString("localtion") : "");
							mpvo.setMobileNo(obj.has("contactNo") ? obj.getString("contactNo") : "");
							mpvo.setTds(obj.has("tds") ? obj.getString("tds") : "");
							mpvo.setPh(obj.has("ph") ? obj.getString("ph") : "");
							mpvo.setMandal(obj.has("mandal") ? obj.getString("mandal") : "");
							mpvo.setDistrict(obj.has("district") ? obj.getString("district") : "");
							mpvo.setLatitude(obj.has("latitude") ? obj.getString("latitude") : "");
							mpvo.setLongitude(obj.has("longitude") ? obj.getString("longitude") : "");
							
							if(obj.has("highDispanceRDU")){
			        			JSONObject highDspncObj = obj.getJSONObject("highDispanceRDU");
			        			mpvo.setHighDispanceRDUName(highDspncObj.getString("name"));
			        			mpvo.setHighRDUDispanceLtrs(commonMethodsUtilService.seperateNumberByComma(Long.valueOf(highDspncObj.getString("dispanceLtrs"))));
			        		}
			        		if(obj.has("lowDispanceRDU")){
			        			JSONObject lowDspncObj = obj.getJSONObject("lowDispanceRDU");
			        			mpvo.setLowDispanceRDUName(lowDspncObj.getString("name"));
			        			mpvo.setLowRDUDispanceLtrs(commonMethodsUtilService.seperateNumberByComma(Long.valueOf(lowDspncObj.getString("dispanceLtrs"))));
			        		}
			        		if(obj.has("customers")){
			        			JSONObject customersObj = obj.getJSONObject("customers");
			        			mpvo.setActiveCustomers(customersObj.getLong("active"));
			        			mpvo.setInActiveCustomers(customersObj.getLong("inActive"));
			        			mpvo.setTotalCustomers(mpvo.getActiveCustomers()+mpvo.getInActiveCustomers());
			        		}
			        		if(obj.has("rdus")){
			        			JSONArray rdusArr = new JSONArray(obj.get("rdus").toString());
			        			if(rdusArr != null && rdusArr.length() > 0){
			        				for (int j = 0; j < rdusArr.length(); j++) {
										JSONObject rduObj = new JSONObject(rdusArr.get(j).toString());
										if(rduObj != null && rduObj.length() > 0){
											NTRSujalaOverviewVO rduvo = new NTRSujalaOverviewVO();
											rduvo.setId(rduObj.getLong("rduId"));
											rduvo.setName(rduObj.getString("name"));
											rduvo.setLocation(rduObj.getString("location"));
											rduvo.setLatitude(rduObj.getString("latitude"));
											rduvo.setLongitude(rduObj.getString("longitude"));
											rduvo.setWaterTankCapacity(rduObj.getLong("waterTankCapacity"));
											rduvo.setMpSafeWaterDispenced(commonMethodsUtilService.seperateNumberByComma(Long.valueOf(rduObj.getString("waterDispencePerDay"))));
											rduvo.setOldCustomers(rduObj.getLong("oldCustomers"));
											rduvo.setNewCustomers(rduObj.getLong("newCustomers"));
											rduvo.setTotalCustomers(rduvo.getOldCustomers()+rduvo.getNewCustomers());
											mpvo.getSubList().add(rduvo);
										}
									}
			        			}
			        		}
			        		vo.getSubList().add(mpvo);
						}
					//}
				}
			}
			
			if(districtMap != null)
				returnList = new ArrayList<NTRSujalaOverviewVO>(districtMap.values());
		} catch (Exception e) {
			LOG.error("Exception occured at getDistrictWiseMotherPlants() in  NTRSujalaDashboardService class",e);
		}
		return returnList;
	}
	
	public List<NTRSujalaOverviewVO> getMotherPlantsList(JSONArray finalArr){
		List<NTRSujalaOverviewVO> returnList = new ArrayList<NTRSujalaOverviewVO>();
		try {
			if(finalArr != null && finalArr.length() > 0){
				for (int i = 0; i < finalArr.length(); i++) {
					JSONObject obj = new JSONObject(finalArr.get(i).toString());
					NTRSujalaOverviewVO mpvo = new NTRSujalaOverviewVO();
					mpvo.setId(obj.getLong("motherPlantId"));
					mpvo.setName(obj.getString("name"));
					mpvo.setHealth(obj.getString("health"));
					mpvo.setWaterQuality(obj.getString("waterQuality"));
					mpvo.setMpSafeWaterDispenced(obj.getString("safeWaterDispence"));
					mpvo.setActiveRDUs(obj.getLong("activeRDUs"));
					mpvo.setInActiveRDUs(obj.getLong("inActiveRDUs"));
					mpvo.setTotalRDUs(mpvo.getActiveRDUs()+mpvo.getInActiveRDUs());
					mpvo.setLocation(obj.has("localtion") ? obj.getString("localtion") : "");
					mpvo.setMobileNo(obj.has("contactNo") ? obj.getString("contactNo") : "");
					mpvo.setTds(obj.has("tds") ? obj.getString("tds") : "");
					mpvo.setPh(obj.has("ph") ? obj.getString("ph") : "");
					mpvo.setMandal(obj.has("mandal") ? obj.getString("mandal") : "");
					mpvo.setDistrict(obj.has("district") ? obj.getString("district") : "");
					mpvo.setLatitude(obj.has("latitude") ? obj.getString("latitude") : "");
					mpvo.setLongitude(obj.has("longitude") ? obj.getString("longitude") : "");
					
					if(obj.has("highDispanceRDU")){
	        			JSONObject highDspncObj = obj.getJSONObject("highDispanceRDU");
	        			mpvo.setHighDispanceRDUName(highDspncObj.getString("name"));
	        			mpvo.setHighRDUDispanceLtrs(commonMethodsUtilService.seperateNumberByComma(Long.valueOf(highDspncObj.getString("dispanceLtrs"))));
	        		}
	        		if(obj.has("lowDispanceRDU")){
	        			JSONObject lowDspncObj = obj.getJSONObject("lowDispanceRDU");
	        			mpvo.setLowDispanceRDUName(lowDspncObj.getString("name"));
	        			mpvo.setLowRDUDispanceLtrs(commonMethodsUtilService.seperateNumberByComma(Long.valueOf(lowDspncObj.getString("dispanceLtrs"))));
	        		}
	        		if(obj.has("customers")){
	        			JSONObject customersObj = obj.getJSONObject("customers");
	        			mpvo.setActiveCustomers(customersObj.getLong("active"));
	        			mpvo.setInActiveCustomers(customersObj.getLong("inActive"));
	        			mpvo.setTotalCustomers(mpvo.getActiveCustomers()+mpvo.getInActiveCustomers());
	        		}
	        		if(obj.has("rdus")){
	        			JSONArray rdusArr = new JSONArray(obj.get("rdus").toString());
	        			if(rdusArr != null && rdusArr.length() > 0){
	        				for (int j = 0; j < rdusArr.length(); j++) {
								JSONObject rduObj = new JSONObject(rdusArr.get(j).toString());
								if(rduObj != null && rduObj.length() > 0){
									NTRSujalaOverviewVO rduvo = new NTRSujalaOverviewVO();
									rduvo.setId(rduObj.getLong("rduId"));
									rduvo.setName(rduObj.getString("name"));
									rduvo.setLocation(rduObj.getString("location"));
									rduvo.setLatitude(rduObj.getString("latitude"));
									rduvo.setLongitude(rduObj.getString("longitude"));
									rduvo.setWaterTankCapacity(rduObj.getLong("waterTankCapacity"));
									rduvo.setMpSafeWaterDispenced(commonMethodsUtilService.seperateNumberByComma(Long.valueOf(rduObj.getString("waterDispencePerDay"))));
									rduvo.setOldCustomers(rduObj.getLong("oldCustomers"));
									rduvo.setNewCustomers(rduObj.getLong("newCustomers"));
									rduvo.setTotalCustomers(rduvo.getOldCustomers()+rduvo.getNewCustomers());
									mpvo.getSubList().add(rduvo);
								}
							}
	        			}
	        		}
	        		returnList.add(mpvo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getMotherPlantsList() in  NTRSujalaDashboardService class",e);
		}
		return returnList;
	}
}
