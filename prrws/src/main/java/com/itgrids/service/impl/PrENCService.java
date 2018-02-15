package com.itgrids.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.dao.IDistrictDAO;
import com.itgrids.dao.IEncWorksDAO;
import com.itgrids.dao.ITehsilDAO;
import com.itgrids.dto.EncTargetsVO;
import com.itgrids.dto.EncVO;
import com.itgrids.dto.EncWorksVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.service.IPrENCService;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Service
@Transactional
public class PrENCService implements IPrENCService {

	
	private static final Logger LOG = Logger.getLogger(PrENCService.class);
	
	@Autowired
	private WebServiceUtilService webServiceUtilService;

	@Autowired
	private IDistrictDAO districtDAO;

	@Autowired
	private ITehsilDAO tehsilDAO;

	@Autowired
	private IConstituencyDAO constituencyDAO;
	
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;

	@Autowired
	private IEncWorksDAO encWorksDAO;
	
	
	@Override
	public List<EncVO> getLocationWiseRoadsInformation(InputVO inputVO) {
		List<EncVO> resultList = new ArrayList<EncVO>();
		try {
			List<EncVO> finalList = new ArrayList<EncVO>();
			if(inputVO.getLocationType().trim().equalsIgnoreCase("S")){
				inputVO.setLocationType("D");
			}
			
			JsonObject object = new JsonObject();
			JsonArray jsonarray = new JsonArray();
			object.addProperty("UNIT_TYPE", "L");
			object.addProperty("UNIT_ID", "-1");
			jsonarray.add(object);
			
			Map<String, Map<Long, EncVO>> locationTypeMap = new HashMap<String, Map<Long, EncVO>>();
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://predmis.ap.nic.in/RestWS/PredmisRoadService/wbsMinisterRoadMinMaxMv");	     
			ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class,jsonarray.toString());
			//ClientResponse response = webServiceUtilService.callWebService("http://predmis.ap.nic.in/RestWS/PredmisRoadService/wbsMinisterRoadMinMaxMv",null);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			} else {
				String output = response.getEntity(String.class);

				if (output != null && !output.isEmpty()) {
					JSONArray resultArray= new JSONArray(output);
					JSONObject Obj = resultArray.getJSONObject(0);
					JSONArray array = Obj.getJSONArray("result");
					for (int i = 0; i < array.length(); i++) {
						JSONObject json = array.getJSONObject(i);
						if(inputVO.getLocationType().trim().equalsIgnoreCase(json.getString("UNIT_TYPE"))){
							Map<Long, EncVO> innermap = locationTypeMap.get(json.getString("UNIT_TYPE"));
							
							if (innermap == null) {
								innermap = new HashMap<Long, EncVO>();
								EncVO mainVo = innermap.get(json.getLong("UNIT_ID"));
								
								if (mainVo == null) {
									mainVo = new EncVO();
									List<EncVO> subList= getSkelton(array,mainVo.getSubList());
									mainVo.setLocationType(json.getString("UNIT_TYPE"));
									mainVo.setLocationId(json.getLong("UNIT_ID"));
									mainVo.setLocationName(json.getString("UNIT_NAME"));
									for (EncVO subVo : subList) {
										if(subVo.getParamName().equalsIgnoreCase(json.getString("PARA"))){
											subVo.setParamValue(json.getDouble("PARA_VALUE"));
										}
									}
									mainVo.setSubList(subList);
									if(!json.getString("PARA").contains("HAB")){
										mainVo.setTotalRoadsLength(mainVo.getTotalRoadsLength()+json.getDouble("PARA_VALUE"));
									}else{
										mainVo.setTotalHabs(mainVo.getTotalHabs()+json.getLong("PARA_VALUE"));
									}
									innermap.put(json.getLong("UNIT_ID"), mainVo);

								}else{
									for (EncVO encVO : mainVo.getSubList()) {
										if(encVO.getParamName().trim().equalsIgnoreCase(json.getString("PARA"))){
											encVO.setParamValue(encVO.getParamValue()+json.getDouble("PARA_VALUE"));
											if(!json.getString("PARA").contains("HAB")){
												mainVo.setTotalRoadsLength(mainVo.getTotalRoadsLength()+json.getDouble("PARA_VALUE"));
											}else{
												mainVo.setTotalHabs(mainVo.getTotalHabs()+json.getLong("PARA_VALUE"));
											}
										}
									}
								}
								locationTypeMap.put(json.getString("UNIT_TYPE"), innermap);
							}else{
								EncVO mainVo = innermap.get(json.getLong("UNIT_ID"));
								
								if (mainVo == null) {
									mainVo = new EncVO();
									List<EncVO> subList= getSkelton(array,mainVo.getSubList());
									mainVo.setLocationType(json.getString("UNIT_TYPE"));
									mainVo.setLocationId(json.getLong("UNIT_ID"));
									mainVo.setLocationName(json.getString("UNIT_NAME"));
									for (EncVO subVo : subList) {
										if(subVo.getParamName().equalsIgnoreCase(json.getString("PARA"))){
											subVo.setParamValue(json.getDouble("PARA_VALUE"));
										}
									}
									mainVo.setSubList(subList);
									if(!json.getString("PARA").contains("HAB")){
										mainVo.setTotalRoadsLength(mainVo.getTotalRoadsLength()+json.getDouble("PARA_VALUE"));
									}else{
										mainVo.setTotalHabs(mainVo.getTotalHabs()+json.getLong("PARA_VALUE"));
									}
									innermap.put(json.getLong("UNIT_ID"), mainVo);

								}else{
									for (EncVO encVO : mainVo.getSubList()) {
										if(encVO.getParamName().trim().equalsIgnoreCase(json.getString("PARA"))){
											encVO.setParamValue(encVO.getParamValue()+json.getDouble("PARA_VALUE"));
											if(!json.getString("PARA").contains("HAB")){
												mainVo.setTotalRoadsLength(mainVo.getTotalRoadsLength()+json.getDouble("PARA_VALUE"));
											}else{
												mainVo.setTotalHabs(mainVo.getTotalHabs()+json.getLong("PARA_VALUE"));
											}
										}
									}
								}
								locationTypeMap.put(json.getString("UNIT_TYPE"), innermap);
							}
						}
						
					}
				}

			}
			for (String Location_type : locationTypeMap.keySet()) {
				EncVO mainVo = new EncVO();	
				Map<Long, EncVO> map = locationTypeMap.get(Location_type);
				mainVo.setLocationType(Location_type);
				for (Long id : map.keySet()) {
					EncVO vo = map.get(id);
					mainVo.getSubList().add(vo);
				}
				finalList.add(mainVo);
			}
			resultList.addAll(finalList.get(0).getSubList());
			
		}catch(Exception e){
			EncVO vo = new EncVO();
			vo.setErrorMessage(e.getLocalizedMessage());
			resultList.add(vo);
			LOG.error("Exception raised at PrENCService - getLocationWiseRoadsInformation", e);
		}
		
		return resultList;
	}
	
	private List<EncVO> getSkelton(JSONArray array, List<EncVO> subList) {
			subList = new ArrayList<EncVO>();
		try{
			for (int i = 0; i < 6; i++) {
				JSONObject json = array.getJSONObject(i);
					EncVO encVO= new EncVO();
					encVO.setParamName(json.getString("PARA"));
					subList.add(encVO);
			}
		}catch(Exception e){
			
		}
		return subList;
	}

	@Override
	public EncVO getStateWiseRoadsInformation(InputVO inputVO) {
		EncVO mainVO= new EncVO();
		try {
			if(inputVO.getLocationType().trim().equalsIgnoreCase("S")){
				inputVO.setLocationType("D");
			}
			JsonObject object = new JsonObject();
			JsonArray jsonarray = new JsonArray();
			object.addProperty("UNIT_TYPE", "L");
			object.addProperty("UNIT_ID", "-1");
			jsonarray.add(object);
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://predmis.ap.nic.in/RestWS/PredmisRoadService/wbsMinisterRoadMinMaxMv");	     
			ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class,jsonarray.toString());

			//ClientResponse response = webServiceUtilService.callWebService("http://predmis.ap.nic.in/RestWS/PredmisRoadService/wbsMinisterRoadMinMaxMv",null);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			} else {
				String output = response.getEntity(String.class);

				if (output != null && !output.isEmpty()) {
					JSONArray resultArray= new JSONArray(output);
					JSONObject Obj = resultArray.getJSONObject(0);
					JSONArray array = Obj.getJSONArray("result");
					
					mainVO.setLocationId(1l);
					mainVO.setLocationName("AndraPrades");
					mainVO.setLocationType("S");
					for (int i = 0; i < array.length(); i++) {
						JSONObject json = array.getJSONObject(i);
						if(inputVO.getLocationType().trim().equalsIgnoreCase(json.getString("UNIT_TYPE"))){
							EncVO Vo =getMatchedVo(mainVO.getSubList(),json.getString("PARA"));
							if(Vo == null){
								Vo = new EncVO();
								Vo.setParamName(json.getString("PARA"));
								Vo.setParamValue(json.getDouble("PARA_VALUE"));
								mainVO.getSubList().add(Vo);
								if(!json.getString("PARA").contains("HAB")){
									mainVO.setTotalRoadsLength(mainVO.getTotalRoadsLength()+json.getDouble("PARA_VALUE"));
								}else{
									mainVO.setTotalHabs(mainVO.getTotalHabs()+json.getLong("PARA_VALUE"));
								}
							}else{
								Vo.setParamValue(Vo.getParamValue()+json.getDouble("PARA_VALUE"));
								if(!json.getString("PARA").contains("HAB")){
									mainVO.setTotalRoadsLength(mainVO.getTotalRoadsLength()+json.getDouble("PARA_VALUE"));
								}else{
									mainVO.setTotalHabs(mainVO.getTotalHabs()+json.getLong("PARA_VALUE"));
								}
							}
							
						}
					}
				}

			}
			
		}catch(Exception e){
			mainVO.setErrorMessage(e.getLocalizedMessage());
			LOG.error("Exception raised at PrENCService - getLocationWiseRoadsInformation", e);
		}
		
		return mainVO;
	}
	private EncVO getMatchedVo(List<EncVO> subList, String name) {
		
		try{
			for (EncVO encVO : subList) {
				if(encVO.getParamName().trim().equalsIgnoreCase(name)){
					return encVO;
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised at getLocationWiseRoadsInformation - getMatchedVo  ", e);
			
		}
		return null;
	}
	
	/***
	 * LocationWiseInfo
	 * @Author: sanjeev
	 * 
	 */
	@Override
	public List<EncWorksVO> getLocationWiseWorksInformation(InputVO inputVO) {
		List<EncWorksVO> finalList= new ArrayList<EncWorksVO>();
				
		try{
			Map<Long,EncWorksVO> locationMap= new HashMap<Long, EncWorksVO>();
			Map<Long,Map<Long,EncWorksVO>> assemblylocationMap= new HashMap<Long, Map<Long,EncWorksVO>>();
			ClientResponse response = webServiceUtilService.callWebService("http://predmis.ap.nic.in/RestWS/PredmisRoadService/MandalWorksOverViewStatus",null);
			
			List<Object[]> locationData = null;
			if(inputVO.getLocationType().equalsIgnoreCase("d")){
				locationData= districtDAO.getEncDistricts();
			}else if(inputVO.getLocationType().equalsIgnoreCase("m")){
				locationData= tehsilDAO.getEncMandals();
			}else if(inputVO.getLocationType().equalsIgnoreCase("A")){
				locationData= constituencyDAO.getEncconstituencies();
			}
			if(!inputVO.getLocationType().equalsIgnoreCase("S") && !inputVO.getLocationType().equalsIgnoreCase("A")){
				for (Object[] objects : locationData) {
					EncWorksVO locationVO = locationMap.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
						if(locationVO == null){
							locationVO = new EncWorksVO();
							locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(objects[0]));
							locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(objects[1]));
							locationMap.put(commonMethodsUtilService.getLongValueForObject(objects[0]), locationVO);
						}
					
				}
			}else if(!inputVO.getLocationType().equalsIgnoreCase("S") && inputVO.getLocationType().equalsIgnoreCase("A")){
				for (Object[] objects : locationData) {
					EncWorksVO locationVO = locationMap.get(commonMethodsUtilService.getLongValueForObject(objects[2]));
					if(locationVO == null){
						locationVO = new EncWorksVO();
						locationVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						locationVO.setConstituencyname(commonMethodsUtilService.getStringValueForObject(objects[1]));
						locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(objects[2]));
						locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(objects[3]));
						locationMap.put(commonMethodsUtilService.getLongValueForObject(objects[2]), locationVO);
					}
				}
			}else if(inputVO.getLocationType().equalsIgnoreCase("S")){
				EncWorksVO locationVO = new EncWorksVO();
				locationVO.setLocationId(1l);
				locationVO.setLocationName("AndraPradesh");
				locationMap.put(1l, locationVO);
			}
			
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
				} else {
					String output = response.getEntity(String.class);
					if (output != null && !output.isEmpty()) {
						JSONArray resultArray= new JSONArray(output);
						JSONObject Obj = resultArray.getJSONObject(0);
						JSONArray array = Obj.getJSONArray("result");
						if(!inputVO.getLocationType().equalsIgnoreCase("A")){
							for (int i = 0; i < array.length(); i++) {
								JSONObject json = array.getJSONObject(i);
								EncWorksVO workVo =null;
								if(inputVO.getLocationType().equalsIgnoreCase("d") || inputVO.getLocationType().equalsIgnoreCase("m")){
									if(inputVO.getLocationType().equalsIgnoreCase("d")){
										workVo = locationMap.get(json.getLong("DIST_CODE"));
									}else{
										workVo = locationMap.get(json.getLong("MAND_CODE"));
									}
									if(workVo != null){
										workVo.setAdminSanctionCount(workVo.getAdminSanctionCount()+json.getLong("TOT_WORKS"));
										workVo.setTechnicallySanctionedCount(workVo.getTechnicallySanctionedCount()+json.getLong("TOT_WORKS_TECHSANC"));
										workVo.setNonTechinicalSanctioned(workVo.getNonTechinicalSanctioned()+(workVo.getAdminSanctionCount()-workVo.getTechnicallySanctionedCount()));
										workVo.setCompletedCount(workVo.getCompletedCount()+json.getLong("TOTWORKSCOMPLETED"));
										workVo.setNotGrounded(workVo.getNotGrounded()+json.getLong("TOT_WORKS_NOTGROUNDED"));
										workVo.setUnderProcessCount(workVo.getUnderProcessCount()+json.getLong("TOT_WORKS_ONGOING"));
										workVo.setGroundedCount(workVo.getGroundedCount()+json.getLong("GROUNDED"));
										workVo.setTotalWorksEntrusted(workVo.getTotalWorksEntrusted()+json.getLong("TOT_WORKS_ENTRUST"));
										workVo.setNotEntrusted(workVo.getNotEntrusted()+(workVo.getTechnicallySanctionedCount()-workVo.getTotalWorksEntrusted()));
									}
								}else if(inputVO.getLocationType().equalsIgnoreCase("s")){
									workVo = locationMap.get(1l);
									if(workVo!=null){
										workVo.setAdminSanctionCount(workVo.getAdminSanctionCount()+json.getLong("TOT_WORKS"));
										workVo.setTechnicallySanctionedCount(workVo.getTechnicallySanctionedCount()+json.getLong("TOT_WORKS_TECHSANC"));
										workVo.setNonTechinicalSanctioned(workVo.getNonTechinicalSanctioned()+(workVo.getAdminSanctionCount()-workVo.getTechnicallySanctionedCount()));
										workVo.setCompletedCount(workVo.getCompletedCount()+json.getLong("TOTWORKSCOMPLETED"));
										workVo.setNotGrounded(workVo.getNotGrounded()+json.getLong("TOT_WORKS_NOTGROUNDED"));
										workVo.setUnderProcessCount(workVo.getUnderProcessCount()+json.getLong("TOT_WORKS_ONGOING"));
										workVo.setGroundedCount(workVo.getGroundedCount()+json.getLong("GROUNDED"));
										workVo.setTotalWorksEntrusted(workVo.getTotalWorksEntrusted()+json.getLong("TOT_WORKS_ENTRUST"));
										workVo.setNotEntrusted(workVo.getNotEntrusted()+(workVo.getTechnicallySanctionedCount()-workVo.getTotalWorksEntrusted()));

									}
								}
								
							}
							finalList.addAll(locationMap.values());
						}else{
							for (int i = 0; i < array.length(); i++) {
								JSONObject json = array.getJSONObject(i);
								EncWorksVO workVo = locationMap.get(json.getLong("MAND_CODE"));
								if(workVo != null){
									workVo.setAdminSanctionCount(workVo.getAdminSanctionCount()+json.getLong("TOT_WORKS"));
									workVo.setTechnicallySanctionedCount(workVo.getTechnicallySanctionedCount()+json.getLong("TOT_WORKS_TECHSANC"));
									workVo.setNonTechinicalSanctioned(workVo.getNonTechinicalSanctioned()+(workVo.getAdminSanctionCount()-workVo.getTechnicallySanctionedCount()));
									workVo.setCompletedCount(workVo.getCompletedCount()+json.getLong("TOTWORKSCOMPLETED"));
									workVo.setNotGrounded(workVo.getNotGrounded()+json.getLong("TOT_WORKS_NOTGROUNDED"));
									workVo.setUnderProcessCount(workVo.getUnderProcessCount()+json.getLong("TOT_WORKS_ONGOING"));
									workVo.setGroundedCount(workVo.getGroundedCount()+json.getLong("GROUNDED"));
									workVo.setTotalWorksEntrusted(workVo.getTotalWorksEntrusted()+json.getLong("TOT_WORKS_ENTRUST"));
									workVo.setNotEntrusted(workVo.getNotEntrusted()+(workVo.getTechnicallySanctionedCount()-workVo.getTotalWorksEntrusted()));

								}
							}
							Map<Long, EncWorksVO> assemblyListMap= new HashMap<Long, EncWorksVO>();
							for (Long mandalId : locationMap.keySet()) {
								EncWorksVO vo = locationMap.get(mandalId);
								if(vo!= null){
									EncWorksVO encVo =assemblyListMap.get(vo.getConstituencyId());
									if(encVo == null){
										encVo = new EncWorksVO();
										encVo.setLocationId(vo.getConstituencyId());
										encVo.setLocationName(vo.getConstituencyname());
										assemblyListMap.put(vo.getConstituencyId(), encVo);
									}
									encVo.setAdminSanctionCount(encVo.getAdminSanctionCount()+vo.getAdminSanctionCount());
									encVo.setTechnicallySanctionedCount(encVo.getTechnicallySanctionedCount()+vo.getTechnicallySanctionedCount());
									encVo.setNonTechinicalSanctioned(encVo.getNonTechinicalSanctioned()+vo.getNonTechinicalSanctioned());
									encVo.setCompletedCount(encVo.getCompletedCount()+vo.getCompletedCount());
									encVo.setNotGrounded(encVo.getNotGrounded()+vo.getNotGrounded());
									encVo.setUnderProcessCount(encVo.getUnderProcessCount()+vo.getUnderProcessCount());
									encVo.setGroundedCount(encVo.getGroundedCount()+vo.getGroundedCount());
									encVo.setTotalWorksEntrusted(encVo.getTotalWorksEntrusted()+vo.getTotalWorksEntrusted());
									encVo.setNotEntrusted(encVo.getNotEntrusted()+vo.getNotEntrusted());
								}
							}
							finalList.addAll(assemblyListMap.values());
						}
					
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised at PrEncService - getLocationWiseWorksInformation  ", e);
		}
			
		return finalList;
		
	}
	/**
	 * getEncTargetsAchievement
	 * Author: sanjay
	 * 
	 */
	@Override
	public List<EncTargetsVO> getEncTargetsAchievement(InputVO inputVO) {
		   List<EncTargetsVO> finalList= new ArrayList<EncTargetsVO>();
		try{
			Map<String,EncTargetsVO> locationMap= new HashMap<String, EncTargetsVO>();
			Map<Long,EncTargetsVO> assemblylocationMap= new HashMap<Long,EncTargetsVO>();
			ClientResponse response = webServiceUtilService.callWebService("http://predmis.ap.nic.in/RestWS/PredmisRoadService/MandalTarAchievements",null);
			List<Object[]> locationData = null;
			if(inputVO.getLocationType().equalsIgnoreCase("d")){
				locationData= districtDAO.getEncDistricts();
			}else if(inputVO.getLocationType().equalsIgnoreCase("m")){
				locationData= tehsilDAO.getEncMandals();
			}else if(inputVO.getLocationType().equalsIgnoreCase("A")){
				locationData= constituencyDAO.getEncconstituencies();
				for (Object[] objects : locationData) {
					EncTargetsVO locationVO = assemblylocationMap.get(commonMethodsUtilService.getLongValueForObject(objects[2]));
					if(locationVO == null){
						locationVO = new EncTargetsVO();
						locationVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						locationVO.setConstituencyname(commonMethodsUtilService.getStringValueForObject(objects[1]));
						locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(objects[2]));
						locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(objects[3]));
						assemblylocationMap.put(commonMethodsUtilService.getLongValueForObject(objects[2]), locationVO);
					}
				}
			}
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			 } else {
				String output = response.getEntity(String.class);
				if (output != null && !output.isEmpty()) {
					JSONArray resultArray= new JSONArray(output);
					JSONObject Obj = resultArray.getJSONObject(0);
					JSONArray array = Obj.getJSONArray("result");
					if(!inputVO.getLocationType().equalsIgnoreCase("A")){
						for (int i = 0; i < array.length(); i++) {
							JSONObject json = array.getJSONObject(i);
							EncTargetsVO encTargetsVO = null;
							if(inputVO.getLocationType().equalsIgnoreCase("d") || inputVO.getLocationType().equalsIgnoreCase("m")){
								if(inputVO.getLocationType().equalsIgnoreCase("d")){
									encTargetsVO = locationMap.get(json.getString("DIST_NAME").trim());
									if(encTargetsVO == null){
										encTargetsVO = new EncTargetsVO();
										encTargetsVO.setLocationId(0l);
										encTargetsVO.setLocationName(json.getString("DIST_NAME").trim());
										locationMap.put(json.getString("DIST_NAME").trim(), encTargetsVO);

									}
								}else{
									encTargetsVO = locationMap.get(json.getString("MAND_NAME").trim());
									if(encTargetsVO == null){
										encTargetsVO = new EncTargetsVO();
										encTargetsVO.setLocationId(json.getLong("MAND_CODE"));
										encTargetsVO.setLocationName(json.getString("MAND_NAME"));
										locationMap.put(json.getString("MAND_NAME").trim(), encTargetsVO);
									}
								}
								encTargetsVO.setTotAchv(encTargetsVO.getTotAchv()+json.getLong("TOT_ACHV"));
	        					encTargetsVO.setTotTarget(encTargetsVO.getTotTarget()+json.getLong("TOT_TARGET"));
	        					encTargetsVO.setTotPopu(encTargetsVO.getTotPopu()+json.getDouble("TOT_POPU"));
	        					encTargetsVO.setTotWorks(encTargetsVO.getTotWorks()+ json.getLong("TOT_WORKS") );
	        					encTargetsVO.setTotLength(encTargetsVO.getTotLength()+json.getDouble("TOT_LENGTH"));
	        					encTargetsVO.setTotPer(encTargetsVO.getTotPer()+json.getDouble("TOT_PER"));
	        					
	        					encTargetsVO.setQ1Achv( encTargetsVO.getQ1Achv()+json.getLong("Q1_ACHV") );
	        					encTargetsVO.setQ1Per(json.getDouble("Q1_PER")+encTargetsVO.getQ1Per());
	        					encTargetsVO.setQ1Target(json.getLong("Q1_TARGET")+encTargetsVO.getQ1Target());
	        					
	        					encTargetsVO.setQ2Achv(json.getLong("Q2_ACHV")+encTargetsVO.getQ2Achv());
	        					encTargetsVO.setQ2Per(json.getDouble("Q2_PER")+encTargetsVO.getQ2Per());
	        					encTargetsVO.setQ2Target( json.getLong("Q2_TARGET")+encTargetsVO.getQ2Target());
	        					
	        					encTargetsVO.setQ3Achv(json.getLong("Q3_ACHV")+encTargetsVO.getQ3Achv());
	        					encTargetsVO.setQ3Per(json.getDouble("Q3_PER")+encTargetsVO.getQ3Per());
	        					encTargetsVO.setQ3Target(json.getLong("Q3_TARGET")+encTargetsVO.getQ3Target());
	        					
	        					encTargetsVO.setQ4Target(json.getLong("Q4_TARGET")+encTargetsVO.getQ4Target());
	        					encTargetsVO.setQ4Per(json.getDouble("Q4_PER")+encTargetsVO.getQ4Per());
	        					encTargetsVO.setQ4Achv(encTargetsVO.getQ4Achv()+json.getLong("Q4_ACHV"));
	        					
	        					encTargetsVO.setAgreementAmount(encTargetsVO.getAgreementAmount()+json.getDouble("AGREEMENT_AMOUNT"));
								
								
							}else if(inputVO.getLocationType().equalsIgnoreCase("s")){
								 	encTargetsVO = locationMap.get("AndraPradesh");
							        if(encTargetsVO == null){
							        	encTargetsVO = new EncTargetsVO();
										encTargetsVO.setLocationId(1l);
										encTargetsVO.setLocationName("AndraPradesh");
										locationMap.put("AndraPradesh", encTargetsVO);
							        }
						        	encTargetsVO.setTotAchv(encTargetsVO.getTotAchv()+json.getLong("TOT_ACHV"));
		        					encTargetsVO.setTotTarget(encTargetsVO.getTotTarget()+json.getLong("TOT_TARGET"));
		        					encTargetsVO.setTotPopu(encTargetsVO.getTotPopu()+json.getDouble("TOT_POPU"));
		        					encTargetsVO.setTotWorks(encTargetsVO.getTotWorks()+ json.getLong("TOT_WORKS") );
		        					encTargetsVO.setTotLength( encTargetsVO.getTotLength()+json.getDouble("TOT_LENGTH"));
		        					encTargetsVO.setTotPer(encTargetsVO.getTotPer()+json.getDouble("TOT_PER"));
		        					
		        					encTargetsVO.setQ1Achv( encTargetsVO.getQ1Achv()+json.getLong("Q1_ACHV") );
		        					encTargetsVO.setQ1Per(json.getDouble("Q1_PER")+encTargetsVO.getQ1Per());
		        					encTargetsVO.setQ1Target(json.getLong("Q1_TARGET")+encTargetsVO.getQ1Target());
		        					
		        					encTargetsVO.setQ2Achv(json.getLong("Q2_ACHV")+encTargetsVO.getQ2Achv());
		        					encTargetsVO.setQ2Per(json.getDouble("Q2_PER")+encTargetsVO.getQ2Per());
		        					encTargetsVO.setQ2Target( json.getLong("Q2_TARGET")+encTargetsVO.getQ2Target());
		        					
		        					encTargetsVO.setQ3Achv(json.getLong("Q3_ACHV")+encTargetsVO.getQ3Achv());
		        					encTargetsVO.setQ3Per(json.getDouble("Q3_PER")+encTargetsVO.getQ3Per());
		        					encTargetsVO.setQ3Target(json.getLong("Q3_TARGET")+encTargetsVO.getQ3Target());
		        					
		        					encTargetsVO.setQ4Target(json.getLong("Q4_TARGET")+encTargetsVO.getQ4Target());
		        					encTargetsVO.setQ4Per(json.getDouble("Q4_PER")+encTargetsVO.getQ4Per());
		        					encTargetsVO.setQ4Achv(encTargetsVO.getQ4Achv()+json.getLong("Q4_ACHV"));
		        					
		        					encTargetsVO.setAgreementAmount(encTargetsVO.getAgreementAmount()+json.getDouble("AGREEMENT_AMOUNT"));
							}
						}
						for (String name: locationMap.keySet()) {
							EncTargetsVO vo = locationMap.get(name);
							if(vo != null){
								
							}
							
						}
						finalList.addAll(locationMap.values());
						
					}else{

						for (int i = 0; i < array.length(); i++) {
							JSONObject json = array.getJSONObject(i);
							EncTargetsVO encTargetsVO = assemblylocationMap.get(json.getLong("MAND_CODE"));
							if(encTargetsVO == null){
								encTargetsVO = new EncTargetsVO();
								encTargetsVO.setLocationId(json.getLong("MAND_CODE"));
								encTargetsVO.setLocationName(json.getString("MAND_NAME"));
								assemblylocationMap.put(json.getLong("MAND_CODE"), encTargetsVO);
							}
								
							encTargetsVO.setTotAchv(encTargetsVO.getTotAchv()+json.getLong("TOT_ACHV"));
        					encTargetsVO.setTotTarget(encTargetsVO.getTotTarget()+json.getLong("TOT_TARGET"));
        					encTargetsVO.setTotPopu(encTargetsVO.getTotPopu()+json.getDouble("TOT_POPU"));
        					encTargetsVO.setTotWorks(encTargetsVO.getTotWorks()+ json.getLong("TOT_WORKS") );
        					encTargetsVO.setTotLength(encTargetsVO.getTotLength()+json.getDouble("TOT_LENGTH"));
        					encTargetsVO.setTotPer(encTargetsVO.getTotPer()+json.getDouble("TOT_PER"));
        					
        					encTargetsVO.setQ1Achv( encTargetsVO.getQ1Achv()+json.getLong("Q1_ACHV") );
        					encTargetsVO.setQ1Per(json.getDouble("Q1_PER")+encTargetsVO.getQ1Per());
        					encTargetsVO.setQ1Target(json.getLong("Q1_TARGET")+encTargetsVO.getQ1Target());
        					
        					encTargetsVO.setQ2Achv(json.getLong("Q2_ACHV")+encTargetsVO.getQ2Achv());
        					encTargetsVO.setQ2Per(json.getDouble("Q2_PER")+encTargetsVO.getQ2Per());
        					encTargetsVO.setQ2Target( json.getLong("Q2_TARGET")+encTargetsVO.getQ2Target());
        					
        					encTargetsVO.setQ3Achv(json.getLong("Q3_ACHV")+encTargetsVO.getQ3Achv());
        					encTargetsVO.setQ3Per(json.getDouble("Q3_PER")+encTargetsVO.getQ3Per());
        					encTargetsVO.setQ3Target(json.getLong("Q3_TARGET")+encTargetsVO.getQ3Target());
        					
        					encTargetsVO.setQ4Target(json.getLong("Q4_TARGET")+encTargetsVO.getQ4Target());
        					encTargetsVO.setQ4Per(json.getDouble("Q4_PER")+encTargetsVO.getQ4Per());
        					encTargetsVO.setQ4Achv(encTargetsVO.getQ4Achv()+json.getLong("Q4_ACHV"));
        					
        					encTargetsVO.setAgreementAmount(encTargetsVO.getAgreementAmount()+json.getDouble("AGREEMENT_AMOUNT"));
							
						}
						Map<Long, EncTargetsVO> assemblyListMap= new HashMap<Long, EncTargetsVO>();
						for (Long mandalId : assemblylocationMap.keySet()) {
							EncTargetsVO vo = assemblylocationMap.get(mandalId);
							if(vo!= null){
								EncTargetsVO encTargetsVO =assemblyListMap.get(vo.getConstituencyId());
								if(encTargetsVO == null){
									encTargetsVO = new EncTargetsVO();
									encTargetsVO.setLocationId(vo.getConstituencyId());
									encTargetsVO.setLocationName(vo.getConstituencyname());
									assemblyListMap.put(vo.getConstituencyId(), encTargetsVO);
								}
								encTargetsVO.setTotAchv(vo.getTotAchv()+encTargetsVO.getTotAchv());
	        					encTargetsVO.setTotTarget(vo.getTotTarget()+encTargetsVO.getTotTarget());
	        					encTargetsVO.setTotPopu(vo.getTotPopu()+encTargetsVO.getTotPopu());
	        					encTargetsVO.setTotWorks(vo.getTotWorks()+encTargetsVO.getTotWorks());
	        					encTargetsVO.setQ1Achv(vo.getQ1Achv()+encTargetsVO.getQ1Achv());
	        					encTargetsVO.setTotLength(vo.getTotLength()+encTargetsVO.getTotLength());
	        					encTargetsVO.setTotPer(vo.getTotPer()+encTargetsVO.getTotPer());
	        					encTargetsVO.setQ1Per(vo.getQ1Per()+encTargetsVO.getQ1Per());
	        					encTargetsVO.setQ1Target(vo.getQ1Target()+encTargetsVO.getQ1Target());
	        					encTargetsVO.setQ2Achv(vo.getQ2Achv()+encTargetsVO.getQ2Achv());
	        					encTargetsVO.setQ2Per(vo.getQ2Per()+encTargetsVO.getQ2Per());
	        					encTargetsVO.setQ2Target(vo.getQ2Target()+encTargetsVO.getQ2Target());
	        					encTargetsVO.setQ3Achv(vo.getQ3Achv()+encTargetsVO.getQ3Achv());
	        					encTargetsVO.setQ3Per(vo.getQ3Per()+encTargetsVO.getQ3Per());
	        					encTargetsVO.setQ3Target(vo.getQ3Target()+encTargetsVO.getQ3Target());
	        					encTargetsVO.setQ4Target(vo.getQ4Target()+encTargetsVO.getQ4Target());
	        					encTargetsVO.setQ4Per(vo.getQ4Per()+encTargetsVO.getQ4Per());
	        					encTargetsVO.setQ4Achv(vo.getQ4Achv()+encTargetsVO.getQ4Achv());
	        					encTargetsVO.setAgreementAmount(vo.getAgreementAmount()+encTargetsVO.getAgreementAmount());
								
							}
						}
						finalList.addAll(assemblyListMap.values());
					
					}
					
				}
			}
			
		 }catch(Exception e){
			LOG.error("Exception raised at PrEncService - getEncTargetsAchievement  ", e);
		 }
			
		return finalList;
	}

	@Override
	public List<IdNameVO> getExceededEncWorks(InputVO inputVO) {
		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
		try{
			Map<String, IdNameVO> workDetailsMap = new HashMap<String, IdNameVO>();
			DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date fromDate=null, toDate= null;
			if(inputVO.getFromDateStr()!= null && inputVO.getToDateStr()!=null && inputVO.getFromDateStr().length()>0 && inputVO.getToDateStr().length()>0){
				fromDate = sdf.parse(inputVO.getFromDateStr());
				toDate= sdf.parse(inputVO.getToDateStr());
			}
			List<Object[]> worksdata = null;
			worksdata = encWorksDAO.getWorksData(fromDate,toDate,"Grounded");
			List<Object[]> worksdatatemp = encWorksDAO.getWorksData(fromDate,toDate,"completed");
			if(commonMethodsUtilService.isListOrSetValid(worksdata)){
				worksdata.addAll(worksdatatemp);
			}else{
				worksdata = new ArrayList<Object[]>();
				worksdata.addAll(worksdatatemp);
			}
			String currentDate = new DateUtilService().getCurrentDateInStringFormatYYYYMMDD();
			
			if(commonMethodsUtilService.isListOrSetValid(worksdata)){
				// 0-workid, 1-workName,2-schemeId,3-schemeName, 4-agrementAmount,5-mandalId,6-mandalName,7-districtId,8-districtName,9-constituencyId,10-constituencyname
				//11-targetDate,12-status,13-groundedDate/completionDate, 15-no of days
				for (Object[] param : worksdata) {
					IdNameVO workDetailsVO = new IdNameVO();
					workDetailsVO.setWrokIdStr(commonMethodsUtilService.getStringValueForObject(param[0]));
					workDetailsVO.setWrokName(commonMethodsUtilService.getStringValueForObject(param[1]));
					workDetailsVO.setAssetType(commonMethodsUtilService.getStringValueForObject(param[3]));
					workDetailsVO.setDistrictCode(commonMethodsUtilService.getStringValueForObject(param[7]));
					workDetailsVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[8]));
					workDetailsVO.setConstituencyCode(commonMethodsUtilService.getStringValueForObject(param[9]));
					workDetailsVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[10]));
					workDetailsVO.setMandalCode(commonMethodsUtilService.getStringValueForObject(param[5]));
					workDetailsVO.setMandalName(commonMethodsUtilService.getStringValueForObject(param[6]));
					workDetailsVO.setSanctionedAmount(commonMethodsUtilService.getDoubleValueForObject(param[4]));
					workDetailsVO.setTargetDate(commonMethodsUtilService.getStringValueForObject(param[11]));
					workDetailsVO.setWorkStatus(commonMethodsUtilService.getStringValueForObject(param[12]));
					workDetailsVO.setGroundedDate(commonMethodsUtilService.getStringValueForObject(param[13]));
					
					if(commonMethodsUtilService.getStringValueForObject(param[12]).trim().equalsIgnoreCase("Grounded")){
						workDetailsVO.setCompletionDate(currentDate);
					}else if(commonMethodsUtilService.getStringValueForObject(param[12]).trim().equalsIgnoreCase("completed") && 
							commonMethodsUtilService.getStringValueForObject(param[14]) != null){
						workDetailsVO.setCompletionDate(commonMethodsUtilService.getStringValueForObject(param[14]));
					}
					
					workDetailsVO.setNoOfDays(commonMethodsUtilService.getLongValueForObject(param[15]));
                    workDetailsVO.setName(getRangeLevelNameBasedOnDays(workDetailsVO.getNoOfDays()));
                    workDetailsMap.put(workDetailsVO.getWrokIdStr(),workDetailsVO);
				}
			}
			Map<String,IdNameVO> resultMap = prepareWrokDtlsLocationWise2(inputVO,workDetailsMap);
			if (resultMap != null && resultMap.size() > 0 ) {
				finalList.addAll(new ArrayList<>(resultMap.values()));
				calculatingPercentage(finalList);
			}
		}catch(Exception e){
			LOG.error("Exception raised at PrEncService - getEncTargetsAchievement", e);
		}
		return finalList;
	}
	
	private String getRangeLevelNameBasedOnDays(Long daysDiff) {
		String rangeLevelName="";
		 try {
			 if (daysDiff != null ) {
				 if(daysDiff >= 1L && daysDiff <= 30L) {
					 rangeLevelName = "1-30 Days";
				 }else if(daysDiff >= 31L && daysDiff <= 60L) {
					 rangeLevelName = "31-60 Days";
				 } else if( daysDiff >= 61L && daysDiff <= 90L) {
					 rangeLevelName = "61-90 Days";
				 } else if(daysDiff >= 91L && daysDiff <= 180L) {
					 rangeLevelName = "91-180 Days";
				 } else if(daysDiff >= 181L && daysDiff <= 365L) {
					 rangeLevelName = "181-365 Days";
				 } else if(daysDiff > 365L) {
					 rangeLevelName = "More Than 1 Year";
				 } else if(daysDiff <= 0l) {
					 rangeLevelName = "In Time";
				 } 
			 }
			
		 } catch (Exception e) {
			 LOG.error("Exception Occured in getRangeLevelNameBasedOnDays() method, Exception - ",e);
		 }
		 return rangeLevelName;
	}
	
	private Map<String, IdNameVO> prepareWrokDtlsLocationWise2(InputVO inputVO,Map<String, IdNameVO> workDtlsMap) {
		Map<String, IdNameVO> locationMap = new HashMap<>();
		try {
			if (workDtlsMap != null && workDtlsMap.size() > 0) {
				for (Entry<String, IdNameVO> entry : workDtlsMap.entrySet()) {
					String locationIdStr = getLocationIdByLocationType(inputVO.getLocationType(), entry.getValue());
					IdNameVO locationVO = locationMap.get(locationIdStr);// getting locationId based on location type
					if (locationVO == null) {
						locationVO = new IdNameVO();
						locationVO.setLocationIdStr(locationIdStr);
						locationVO.setName(getLocationNameByLocationType(inputVO.getLocationType(), entry.getValue()));// getting locationName based on location type
						locationVO.setCount(0l);
						locationVO.setSanctionAmount(0l);
						locationVO.setSubList(getRequiredTemplate());// getting template
						locationMap.put(locationVO.getLocationIdStr(),locationVO);
					}
					for (IdNameVO assetTypeVO : locationVO.getSubList()) {

							IdNameVO matchVO = getMatchedVO(entry.getValue(),assetTypeVO.getName());
							if (matchVO != null) {
									assetTypeVO.setCount(assetTypeVO.getCount() + 1);
									locationVO.setCount(locationVO.getCount()+1);
									locationVO.setSanctionedAmount(locationVO.getSanctionedAmount()+ matchVO.getSanctionedAmount());
									assetTypeVO.setSanctionedAmount(assetTypeVO.getSanctionedAmount()+matchVO.getSanctionedAmount());
									
			                    	if(matchVO.getWorkStatus().trim().equalsIgnoreCase("Grounded")){
			                    		assetTypeVO.setOngoingPWSExceededCount(assetTypeVO.getOngoingPWSExceededCount()+1);
			                    	}else if (matchVO.getWorkStatus().trim().trim().equalsIgnoreCase("completed")){
			                    		assetTypeVO.setCompletedPWSExceededCount(assetTypeVO.getCompletedPWSExceededCount()+1);
			                    	}
							}

					}

				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in prepareWrokDtlsLocationWise() method, Exception - ",e);
		}
		return locationMap;
	}

	private String getLocationIdByLocationType(String locationType,IdNameVO workDtlsVO) {
		String locationIdStr="";
		try {
			if (locationType != null && locationType.trim().equalsIgnoreCase("state")){
				locationIdStr="01";
			} else if (locationType != null && locationType.trim().equalsIgnoreCase("district")){
				locationIdStr = workDtlsVO.getDistrictCode();
			} else if (locationType != null && locationType.trim().equalsIgnoreCase("constituency")){
				locationIdStr = workDtlsVO.getConstituencyCode();
			} else if (locationType != null && locationType.trim().equalsIgnoreCase("mandal")){
				locationIdStr = workDtlsVO.getDistrictCode().concat(workDtlsVO.getMandalCode());
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getLocationIdByLocationType() in RWSNICService class",e);
		}
		return locationIdStr;
	}
	
	private IdNameVO getMatchedVO(IdNameVO vo,String name){
		try {
			if(vo != null){
				if(vo.getName().equalsIgnoreCase(name)) {
					return vo;
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getMatchedVO() method, Exception - ",e);
		}
		return null;
	}
	private String getLocationNameByLocationType(String locationType,IdNameVO workDtlsVO) {
		String locationIdName="";
		try {
			if (locationType != null && locationType.trim().equalsIgnoreCase("state")){
				locationIdName = "Andhra Pradesh";
			} else if (locationType != null && locationType.trim().equalsIgnoreCase("district")){
				locationIdName = workDtlsVO.getDistrictName();
			} else if (locationType != null && locationType.trim().equalsIgnoreCase("constituency")){
				locationIdName = workDtlsVO.getConstituencyName();
			} else if (locationType != null && locationType.trim().equalsIgnoreCase("mandal")){
				locationIdName = workDtlsVO.getMandalName();
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getLocationNameByLocationType() in RWSNICService class",e);
		}
		return locationIdName;
	}
	
	private List<IdNameVO> getRequiredTemplate() {
		List<IdNameVO> resultList = new ArrayList<>(0);
		String[] templateArr = {"In Time","1-30 Days","31-60 Days","61-90 Days","91-180 Days","181-365 Days","More Than 1 Year"};
		try {
			for (int i = 0; i < templateArr.length; i++) {
				IdNameVO subVo = new IdNameVO();
				subVo.setId((long)i+1L);
				subVo.setName(templateArr[i]);
				subVo.setCount(0l);
				subVo.setSanctionAmount(0l);
				subVo.setOngoingPWSExceededCount(0l);
				subVo.setCompletedPWSExceededCount(0l);
				subVo.setCommissionedPWSExceededCount(0l);
				resultList.add(subVo);
		   }
			
		} catch (Exception e) {
			 LOG.error("Exception Occured in getDateInStringFrormat() method, Exception - ",e);
		}
		return resultList;
	}
	
	private void calculatingPercentage(List<IdNameVO> resultList) {
		try {
			if (resultList.size() > 0) {
				for (Iterator<IdNameVO> it = resultList.iterator(); it.hasNext();) {
					IdNameVO vo = it.next();
					if (vo.getCount() == 0l) {
						it.remove();
					} else {
						if (vo.getSubList() != null && vo.getSubList().size() > 0) {
							for (IdNameVO assetTypeVO : vo.getSubList()) {

								if (assetTypeVO.getSubList() != null && assetTypeVO.getSubList().size() > 0) {
									for (IdNameVO rangeVO : assetTypeVO .getSubList()) {
										rangeVO.setPercentage(commonMethodsUtilService.calculatePercantage(rangeVO.getCount(),assetTypeVO.getCount()));
									}
								}

							}
						}
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Exception Occured in calculatingPercentage() method, Exception - ",e);
		}
	}
	
	public EncWorksVO getLocationWiseWorksgraphInformation(InputVO inputVO) {
		EncWorksVO finalVo= new EncWorksVO();
		try{
			EncWorksVO workVo= new EncWorksVO();
			EncWorksVO technicalVo= new EncWorksVO();
			EncWorksVO entrustedVo= new EncWorksVO();
			EncWorksVO ongoingVo= new EncWorksVO();
			EncWorksVO completedVo= new EncWorksVO();
			EncWorksVO notGroundedVo= new EncWorksVO();
			finalVo.setLocationId(1l);
			finalVo.setLocationName("Andra Pradesh");
			ClientResponse response = webServiceUtilService.callWebService("http://predmis.ap.nic.in/RestWS/PredmisRoadService/MandalWorksOverViewStatus",null);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			} else {
				String output = response.getEntity(String.class);
				if (output != null && !output.isEmpty()) {
					JSONArray resultArray= new JSONArray(output);
					JSONObject Obj = resultArray.getJSONObject(0);
					JSONArray array = Obj.getJSONArray("result");
					for (int i = 0; i < array.length(); i++) {
						JSONObject json = array.getJSONObject(i);
						workVo.setAdminSanctionCount(workVo.getAdminSanctionCount()+json.getLong("TOT_WORKS"));
						workVo.setTechnicallySanctionedCount(workVo.getTechnicallySanctionedCount()+json.getLong("TOT_WORKS_TECHSANC"));
						workVo.setNonTechinicalSanctioned(workVo.getNonTechinicalSanctioned()+(workVo.getAdminSanctionCount()-workVo.getTechnicallySanctionedCount()));
						workVo.setCompletedCount(workVo.getCompletedCount()+json.getLong("TOTWORKSCOMPLETED"));
						workVo.setNotGrounded(workVo.getNotGrounded()+json.getLong("TOT_WORKS_NOTGROUNDED"));
						workVo.setUnderProcessCount(workVo.getUnderProcessCount()+json.getLong("TOT_WORKS_ONGOING"));
						workVo.setGroundedCount(workVo.getGroundedCount()+json.getLong("GROUNDED"));
						workVo.setTotalWorksEntrusted(workVo.getTotalWorksEntrusted()+json.getLong("TOT_WORKS_ENTRUST"));
						workVo.setNotEntrusted(workVo.getNotEntrusted()+(workVo.getTechnicallySanctionedCount()-workVo.getTotalWorksEntrusted()));

					}
					Date date = new Date();
					
					finalVo.setAdminSanctionCount(workVo.getAdminSanctionCount());
					technicalVo.setLocationName("technicallSanctioned");
					technicalVo.setNotGrounded(workVo.getTechnicallySanctionedCount());
					technicalVo.setNotGroundedExceededCount(workVo.getAdminSanctionCount()-workVo.getTechnicallySanctionedCount());
					finalVo.getSubList().add(technicalVo);
					
					entrustedVo.setLocationName("entrusted");
					entrustedVo.setNotGrounded(workVo.getTotalWorksEntrusted());
					entrustedVo.setNotGroundedExceededCount(workVo.getTechnicallySanctionedCount()-workVo.getTotalWorksEntrusted());
					finalVo.getSubList().add(entrustedVo);
					
					List<Object[]> statusList = encWorksDAO.getExceedWorksBystatus(date, "ongoing");
					List<Object[]> completedStatusList = encWorksDAO.getExceedWorksBystatus(date, "completed");
					if(commonMethodsUtilService.isListOrSetValid(completedStatusList)){
						statusList.addAll(completedStatusList);
					}
					for (Object[] objects : statusList) {
						if(commonMethodsUtilService.getStringValueForObject(objects[1]).trim().equalsIgnoreCase("Grounded")){
							ongoingVo.setLocationName("Grounded");
							ongoingVo.setNotGrounded(workVo.getGroundedCount());
							ongoingVo.setNotGroundedExceededCount(commonMethodsUtilService.getLongValueForObject(objects[0]));
							finalVo.getSubList().add(ongoingVo);
						}else if(commonMethodsUtilService.getStringValueForObject(objects[1]).trim().equalsIgnoreCase("Completed")){
							completedVo.setLocationName("Completed");
							completedVo.setNotGrounded(workVo.getCompletedCount());
							completedVo.setNotGroundedExceededCount(commonMethodsUtilService.getLongValueForObject(objects[0]));
							finalVo.getSubList().add(completedVo);
						}else if(commonMethodsUtilService.getStringValueForObject(objects[1]).trim().equalsIgnoreCase("Not Grounded")){
							notGroundedVo.setLocationName("Not Grounded");
							notGroundedVo.setNotGrounded(workVo.getNotGrounded());
							notGroundedVo.setNotGroundedExceededCount(commonMethodsUtilService.getLongValueForObject(objects[0]));
							finalVo.getSubList().add(notGroundedVo);
						} 
						
					}
					
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception Occured in getLocationWiseWorksgraphInformation() method, Exception - ",e);
		}
		
		return finalVo;
	}

	@Override
	public List<IdNameVO> getLocationWiseNotGroundedWorks(InputVO inputVO) {
		List<IdNameVO> finalList = new ArrayList<IdNameVO>();
		try{
			Map<String, IdNameVO> workDetailsMap = new HashMap<String, IdNameVO>();
			DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date fromDate=null, toDate= null;
			if(inputVO.getFromDateStr()!= null && inputVO.getToDateStr()!=null && inputVO.getFromDateStr().length()>0 && inputVO.getToDateStr().length()>0){
				fromDate = sdf.parse(inputVO.getFromDateStr());
				toDate= sdf.parse(inputVO.getToDateStr());
			}
			List<Object[]> worksdata = encWorksDAO.getWorksData(fromDate,toDate,"not Grounded");
			
			String currentDate = new DateUtilService().getCurrentDateInStringFormatYYYYMMDD();
			
			if(commonMethodsUtilService.isListOrSetValid(worksdata)){
				// 0-workid, 1-workName,2-schemeId,3-schemeName, 4-agrementAmount,5-mandalId,6-mandalName,7-districtId,8-districtName,9-constituencyId,10-constituencyname
				//11-targetDate,12-status,13-groundedDate/completionDate, 15-no of days
				for (Object[] param : worksdata) {
					IdNameVO workDetailsVO = new IdNameVO();
					workDetailsVO.setWrokIdStr(commonMethodsUtilService.getStringValueForObject(param[0]));
					workDetailsVO.setWrokName(commonMethodsUtilService.getStringValueForObject(param[1]));
					workDetailsVO.setAssetType(commonMethodsUtilService.getStringValueForObject(param[3]));
					workDetailsVO.setDistrictCode(commonMethodsUtilService.getStringValueForObject(param[7]));
					workDetailsVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[8]));
					workDetailsVO.setConstituencyCode(commonMethodsUtilService.getStringValueForObject(param[9]));
					workDetailsVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(param[10]));
					workDetailsVO.setMandalCode(commonMethodsUtilService.getStringValueForObject(param[5]));
					workDetailsVO.setMandalName(commonMethodsUtilService.getStringValueForObject(param[6]));
					workDetailsVO.setSanctionedAmount(commonMethodsUtilService.getDoubleValueForObject(param[4]));
					workDetailsVO.setTargetDate(commonMethodsUtilService.getStringValueForObject(param[11]));
					workDetailsVO.setWorkStatus(commonMethodsUtilService.getStringValueForObject(param[12]));
					workDetailsVO.setGroundedDate(commonMethodsUtilService.getStringValueForObject(param[13]));
					
					if(commonMethodsUtilService.getStringValueForObject(param[12]).trim().equalsIgnoreCase("Grounded")){
						workDetailsVO.setCompletionDate(currentDate);
					}else if(commonMethodsUtilService.getStringValueForObject(param[12]).trim().equalsIgnoreCase("completed") && 
							commonMethodsUtilService.getStringValueForObject(param[14]) != null){
						workDetailsVO.setCompletionDate(commonMethodsUtilService.getStringValueForObject(param[14]));
					}
					
					workDetailsVO.setNoOfDays(commonMethodsUtilService.getLongValueForObject(param[15]));
                    workDetailsVO.setName(getRangeLevelNameBasedOnDays(workDetailsVO.getNoOfDays()));
                    workDetailsMap.put(workDetailsVO.getWrokIdStr(),workDetailsVO);
				}
			}
			Map<String,IdNameVO> resultMap = prepareWrokDtlsLocationWise2(inputVO,workDetailsMap);
			if (resultMap != null && resultMap.size() > 0 ) {
				finalList.addAll(new ArrayList<>(resultMap.values()));
				calculatingPercentage(finalList);
			}
		}catch(Exception e){
			LOG.error("Exception raised at PrEncService - getEncTargetsAchievement", e);
		}
		return finalList;
	}
   
}