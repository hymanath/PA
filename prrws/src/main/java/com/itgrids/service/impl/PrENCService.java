package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.dao.IDistrictDAO;
import com.itgrids.dao.ITehsilDAO;
import com.itgrids.dto.EncTargetsVO;
import com.itgrids.dto.EncVO;
import com.itgrids.dto.EncWorksVO;
import com.itgrids.dto.InputVO;
import com.itgrids.service.IPrENCService;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.sun.jersey.api.client.ClientResponse;

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
	
	
	@Override
	public List<EncVO> getLocationWiseRoadsInformation(InputVO inputVO) {
		List<EncVO> resultList = new ArrayList<EncVO>();
		try {
			List<EncVO> finalList = new ArrayList<EncVO>();
			if(inputVO.getLocationType().trim().equalsIgnoreCase("S")){
				inputVO.setLocationType("D");
			}
			Map<String, Map<Long, EncVO>> locationTypeMap = new HashMap<String, Map<Long, EncVO>>();
			ClientResponse response = webServiceUtilService.callWebService("http://predmis.ap.nic.in/RestWS/PredmisRoadService/wbsMinisterRoadMinMaxMv",null);
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
			ClientResponse response = webServiceUtilService.callWebService("http://predmis.ap.nic.in/RestWS/PredmisRoadService/wbsMinisterRoadMinMaxMv",null);
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

	@Override
	public List<EncWorksVO> getLocationWiseWorksInformation(InputVO inputVO) {
		List<EncWorksVO> finalList= new ArrayList<EncWorksVO>();
				
		try{
			Map<Long,EncWorksVO> locationMap= new HashMap<Long, EncWorksVO>();
			List<Object[]> locationData = null;
			if(inputVO.getLocationType().equalsIgnoreCase("d")){
				locationData= districtDAO.getEncDistricts();
			}else if(inputVO.getLocationType().equalsIgnoreCase("m")){
				locationData= tehsilDAO.getEncMandals();
			}else if(inputVO.getLocationType().equalsIgnoreCase("A")){
				locationData= constituencyDAO.getEncconstituencies();
			}
			for (Object[] objects : locationData) {
				EncWorksVO locationVO = locationMap.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
					if(locationVO == null){
						locationVO = new EncWorksVO();
						locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(objects[1]));
						locationMap.put(commonMethodsUtilService.getLongValueForObject(objects[0]), locationVO);
					}				
			     }
			    ClientResponse response = webServiceUtilService.callWebService("http://predmis.ap.nic.in/RestWS/PredmisRoadService/wbsMinisterRoadMinMaxMv",null);
			
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
						if(inputVO.getLocationType().equalsIgnoreCase("d") || inputVO.getLocationType().equalsIgnoreCase("m")){
							EncWorksVO workVo = locationMap.get(json.getLong("DIST_CODE"));
							if(workVo != null){
								workVo.setAdminSanctionCount(json.getLong("TOT_WORKS"));
								workVo.setTechnicallySanctionedCount(json.getLong("TOT_WORKS_TECHSANC"));
								workVo.setCompletedCount(json.getLong("TOTWORKSCOMPLETED"));
								workVo.setNotGrounded(json.getLong("TOT_WORKS_NOTGROUNDED"));
								workVo.setUnderProcessCount(json.getLong("TOT_WORKS_ONGOING"));
								workVo.setGroundedCount(json.getLong("GROUNDED"));
							}
						}else if(inputVO.getLocationType().equalsIgnoreCase("C")){
							for (Object[] objects : locationData) {						
								EncWorksVO workVo = locationMap.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
								if(workVo != null){
									List<Long> getMandalIds= constituencyDAO.getTehsilIds(commonMethodsUtilService.getLongValueForObject(objects[0]));
									if(getMandalIds.contains(json.getLong("MAND_CODE"))){
										workVo.setAdminSanctionCount(json.getLong("TOT_WORKS"));
										workVo.setTechnicallySanctionedCount(json.getLong("TOT_WORKS_TECHSANC"));
										workVo.setCompletedCount(json.getLong("TOTWORKSCOMPLETED"));
										workVo.setNotGrounded(json.getLong("TOT_WORKS_NOTGROUNDED"));
										workVo.setUnderProcessCount(json.getLong("TOT_WORKS_ONGOING"));
										workVo.setGroundedCount(json.getLong("GROUNDED"));
									}
								}
								
							}	
							
						}else if(inputVO.getLocationType().equalsIgnoreCase("s")){
							EncWorksVO workVo=  new EncWorksVO();
							workVo.setAdminSanctionCount(json.getLong("TOT_WORKS"));
							workVo.setTechnicallySanctionedCount(json.getLong("TOT_WORKS_TECHSANC"));
							workVo.setCompletedCount(json.getLong("TOTWORKSCOMPLETED"));
							workVo.setNotGrounded(json.getLong("TOT_WORKS_NOTGROUNDED"));
							workVo.setUnderProcessCount(json.getLong("TOT_WORKS_ONGOING"));
							workVo.setGroundedCount(json.getLong("GROUNDED"));
							finalList.add(workVo);
						}
						
					}
				}
				
			}
			if(!inputVO.getLocationType().equalsIgnoreCase("s")){
				
				finalList.addAll(locationMap.values());
			}
		}catch(Exception e){
			LOG.error("Exception raised at PrEncService - getLocationWiseWorksInformation  ", e);
		}
			
		return null;
	}

	@Override
	public List<EncTargetsVO> getEncTargetsAchievement(InputVO inputVO) {
		   List<EncTargetsVO> finalList= new ArrayList<EncTargetsVO>();
		try{
			Map<String,EncTargetsVO> locationMap= new HashMap<String, EncTargetsVO>();
			ClientResponse response = webServiceUtilService.callWebService("http://predmis.ap.nic.in/RestWS/PredmisRoadService/MandalTarAchievements",null);
			List<Object[]> locationData = null;
			if(inputVO.getLocationType().equalsIgnoreCase("d")){
				locationData= districtDAO.getEncDistricts();
			}else if(inputVO.getLocationType().equalsIgnoreCase("m")){
				locationData= tehsilDAO.getEncMandals();
			}else if(inputVO.getLocationType().equalsIgnoreCase("A")){
				locationData= constituencyDAO.getEncconstituencies();
			}
			if(!inputVO.getLocationType().equalsIgnoreCase("S")){
				for (Object[] objects : locationData) {
					EncTargetsVO locationVO = locationMap.get(commonMethodsUtilService.getStringValueForObject(objects[1]));
						if(locationVO == null){
							locationVO = new EncTargetsVO();
							locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(objects[0]));
							locationVO.setLocationName(commonMethodsUtilService.getStringValueForObject(objects[1]));
							locationMap.put(commonMethodsUtilService.getStringValueForObject(objects[1]), locationVO);
						}					
				}
				}else{
				EncTargetsVO locationVO = new EncTargetsVO();
				locationVO.setLocationId(1l);
				locationVO.setLocationName("AndraPradesh");
				locationMap.put("AndraPradesh", locationVO);
			 }
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
						if(inputVO.getLocationType().equalsIgnoreCase("d") || inputVO.getLocationType().equalsIgnoreCase("m")){
							EncTargetsVO encTargetsVO = locationMap.get(json.getString("DIST_NAME"));
							if(encTargetsVO != null){
								encTargetsVO.setLocationId(!json.getString("MAND_CODE").equalsIgnoreCase("null") ? json.getLong("MAND_CODE") : 0l);
	        					encTargetsVO.setLocationName(!json.getString("DIST_NAME").trim().equalsIgnoreCase("null") ? json.getString("DIST_NAME") : "");	 
	        					encTargetsVO.setLocationName(!json.getString("MAND_NAME").trim().equalsIgnoreCase("null") ? json.getString("MAND_NAME") : "");	 
	        					encTargetsVO.setTotAchv(!json.getString("TOT_ACHV").equalsIgnoreCase("null") ? json.getLong("TOT_ACHV") : 0l);
	        					encTargetsVO.setTotTarget(!json.getString("TOT_TARGET").equalsIgnoreCase("null") ? json.getLong("TOT_TARGET"):0l);
	        					encTargetsVO.setTotPopu(!json.getString("TOT_POPU").equalsIgnoreCase("null") ? json.getDouble("TOT_POPU") : 0.00);
	        					encTargetsVO.setTotWorks(!json.getString("TOT_WORKS").equalsIgnoreCase("null") ? json.getLong("TOT_WORKS") : 0l);
	        					encTargetsVO.setQ1Achv(!json.getString("Q1_ACHV").equalsIgnoreCase("null") ? json.getLong("Q1_ACHV") : 0l);
	        					encTargetsVO.setTotLength(!json.getString("TOT_LENGTH").equalsIgnoreCase("null") ? json.getDouble("TOT_LENGTH") : 0.00);
	        					encTargetsVO.setTotPer(!json.getString("TOT_PER").equalsIgnoreCase("null") ? json.getLong("TOT_PER") : 0l);
	        					encTargetsVO.setQ1Per(!json.getString("Q1_PER").equalsIgnoreCase("null") ? json.getLong("Q1_PER") : 0l);
	        					encTargetsVO.setQ1Target(!json.getString("Q1_TARGET").equalsIgnoreCase("null") ? json.getLong("Q1_TARGET") : 0l);
	        					encTargetsVO.setQ2Achv(!json.getString("Q2_ACHV").equalsIgnoreCase("null") ? json.getLong("Q2_ACHV") : 0l);
	        					encTargetsVO.setQ2Per(!json.getString("Q2_PER").equalsIgnoreCase("null") ? json.getLong("Q2_PER") : 0l);
	        					encTargetsVO.setQ2Target(!json.getString("Q2_TARGET").equalsIgnoreCase("null") ? json.getLong("Q2_TARGET") : 0l);
	        					encTargetsVO.setQ3Achv(!json.getString("Q3_ACHV").equalsIgnoreCase("null") ? json.getLong("Q3_ACHV") : 0l);
	        					encTargetsVO.setQ3Per(!json.getString("Q3_PER").equalsIgnoreCase("null") ? json.getLong("Q3_PER") : 0l);
	        					encTargetsVO.setQ3Target(!json.getString("Q3_TARGET").equalsIgnoreCase("null") ? json.getLong("Q3_TARGET") : 0l);
	        					encTargetsVO.setQ4Target(!json.getString("Q4_TARGET").equalsIgnoreCase("null") ? json.getLong("Q4_TARGET") : 0l);
	        					encTargetsVO.setQ4Per(!json.getString("Q4_PER").equalsIgnoreCase("null") ? json.getLong("Q4_PER") : 0l);
	        					encTargetsVO.setQ4Achv(!json.getString("Q4_ACHV").equalsIgnoreCase("null") ? json.getLong("Q4_ACHV") : 0l);
	        					encTargetsVO.setSchemeName(!json.getString("SCHEME_NAME").trim().equalsIgnoreCase("null") ? json.getString("SCHEME_NAME") : "");
	        					encTargetsVO.setAgreementAmount(!json.getString("AGREEMENT_AMOUNT").equalsIgnoreCase("null") ? json.getDouble("AGREEMENT_AMOUNT") : 0.00);
	        					encTargetsVO.setScheme(!json.getString("SCHEME").equalsIgnoreCase("null") ? json.getLong("SCHEME") : 0l);    	
							}
						}else if(inputVO.getLocationType().equalsIgnoreCase("C")){
							for (Object[] objects : locationData) {						
								EncTargetsVO encTargetsVO = locationMap.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
								if(encTargetsVO != null){
									List<Long> getMandalIds= constituencyDAO.getTehsilIds(commonMethodsUtilService.getLongValueForObject(objects[0]));
									if(getMandalIds.contains(json.getLong("MAND_CODE"))){
										encTargetsVO.setLocationId(!json.getString("MAND_CODE").equalsIgnoreCase("null") ? json.getLong("MAND_CODE") : 0l);
			        					encTargetsVO.setLocationName(!json.getString("DIST_NAME").trim().equalsIgnoreCase("null") ? json.getString("DIST_NAME") : "");	 
			        					encTargetsVO.setLocationName(!json.getString("MAND_NAME").trim().equalsIgnoreCase("null") ? json.getString("MAND_NAME") : "");	 
			        					encTargetsVO.setTotAchv(!json.getString("TOT_ACHV").equalsIgnoreCase("null") ? json.getLong("TOT_ACHV") : 0l);
			        					encTargetsVO.setTotTarget(!json.getString("TOT_TARGET").equalsIgnoreCase("null") ? json.getLong("TOT_TARGET"):0l);
			        					encTargetsVO.setTotPopu(!json.getString("TOT_POPU").equalsIgnoreCase("null") ? json.getDouble("TOT_POPU") : 0.00);
			        					encTargetsVO.setTotWorks(!json.getString("TOT_WORKS").equalsIgnoreCase("null") ? json.getLong("TOT_WORKS") : 0l);
			        					encTargetsVO.setQ1Achv(!json.getString("Q1_ACHV").equalsIgnoreCase("null") ? json.getLong("Q1_ACHV") : 0l);
			        					encTargetsVO.setTotLength(!json.getString("TOT_LENGTH").equalsIgnoreCase("null") ? json.getDouble("TOT_LENGTH") : 0.00);
			        					encTargetsVO.setTotPer(!json.getString("TOT_PER").equalsIgnoreCase("null") ? json.getLong("TOT_PER") : 0l);
			        					encTargetsVO.setQ1Per(!json.getString("Q1_PER").equalsIgnoreCase("null") ? json.getLong("Q1_PER") : 0l);
			        					encTargetsVO.setQ1Target(!json.getString("Q1_TARGET").equalsIgnoreCase("null") ? json.getLong("Q1_TARGET") : 0l);
			        					encTargetsVO.setQ2Achv(!json.getString("Q2_ACHV").equalsIgnoreCase("null") ? json.getLong("Q2_ACHV") : 0l);
			        					encTargetsVO.setQ2Per(!json.getString("Q2_PER").equalsIgnoreCase("null") ? json.getLong("Q2_PER") : 0l);
			        					encTargetsVO.setQ2Target(!json.getString("Q2_TARGET").equalsIgnoreCase("null") ? json.getLong("Q2_TARGET") : 0l);
			        					encTargetsVO.setQ3Achv(!json.getString("Q3_ACHV").equalsIgnoreCase("null") ? json.getLong("Q3_ACHV") : 0l);
			        					encTargetsVO.setQ3Per(!json.getString("Q3_PER").equalsIgnoreCase("null") ? json.getLong("Q3_PER") : 0l);
			        					encTargetsVO.setQ3Target(!json.getString("Q3_TARGET").equalsIgnoreCase("null") ? json.getLong("Q3_TARGET") : 0l);
			        					encTargetsVO.setQ4Target(!json.getString("Q4_TARGET").equalsIgnoreCase("null") ? json.getLong("Q4_TARGET") : 0l);
			        					encTargetsVO.setQ4Per(!json.getString("Q4_PER").equalsIgnoreCase("null") ? json.getLong("Q4_PER") : 0l);
			        					encTargetsVO.setQ4Achv(!json.getString("Q4_ACHV").equalsIgnoreCase("null") ? json.getLong("Q4_ACHV") : 0l);
			        					encTargetsVO.setSchemeName(!json.getString("SCHEME_NAME").trim().equalsIgnoreCase("null") ? json.getString("SCHEME_NAME") : "");
			        					encTargetsVO.setAgreementAmount(!json.getString("AGREEMENT_AMOUNT").equalsIgnoreCase("null") ? json.getDouble("AGREEMENT_AMOUNT") : 0.00);
			        					encTargetsVO.setScheme(!json.getString("SCHEME").equalsIgnoreCase("null") ? json.getLong("SCHEME") : 0l);    	
									}
								}
								
							}	
						    }else if(inputVO.getLocationType().equalsIgnoreCase("s")){
							 EncTargetsVO	encTargetsVO = locationMap.get("AndraPradesh");
						        if(encTargetsVO != null){
								encTargetsVO.setLocationId(!json.getString("MAND_CODE").equalsIgnoreCase("null") ? json.getLong("MAND_CODE") : 0l);
	        					encTargetsVO.setLocationName(!json.getString("DIST_NAME").trim().equalsIgnoreCase("null") ? json.getString("DIST_NAME") : "");	 
	        					encTargetsVO.setLocationName(!json.getString("MAND_NAME").trim().equalsIgnoreCase("null") ? json.getString("MAND_NAME") : "");	 
	        					encTargetsVO.setTotAchv(!json.getString("TOT_ACHV").equalsIgnoreCase("null") ? json.getLong("TOT_ACHV") : 0l);
	        					encTargetsVO.setTotTarget(!json.getString("TOT_TARGET").equalsIgnoreCase("null") ? json.getLong("TOT_TARGET"):0l);
	        					encTargetsVO.setTotPopu(!json.getString("TOT_POPU").equalsIgnoreCase("null") ? json.getDouble("TOT_POPU") : 0.00);
	        					encTargetsVO.setTotWorks(!json.getString("TOT_WORKS").equalsIgnoreCase("null") ? json.getLong("TOT_WORKS") : 0l);
	        					encTargetsVO.setQ1Achv(!json.getString("Q1_ACHV").equalsIgnoreCase("null") ? json.getLong("Q1_ACHV") : 0l);
	        					encTargetsVO.setTotLength(!json.getString("TOT_LENGTH").equalsIgnoreCase("null") ? json.getDouble("TOT_LENGTH") : 0.00);
	        					encTargetsVO.setTotPer(!json.getString("TOT_PER").equalsIgnoreCase("null") ? json.getLong("TOT_PER") : 0l);
	        					encTargetsVO.setQ1Per(!json.getString("Q1_PER").equalsIgnoreCase("null") ? json.getLong("Q1_PER") : 0l);
	        					encTargetsVO.setQ1Target(!json.getString("Q1_TARGET").equalsIgnoreCase("null") ? json.getLong("Q1_TARGET") : 0l);
	        					encTargetsVO.setQ2Achv(!json.getString("Q2_ACHV").equalsIgnoreCase("null") ? json.getLong("Q2_ACHV") : 0l);
	        					encTargetsVO.setQ2Per(!json.getString("Q2_PER").equalsIgnoreCase("null") ? json.getLong("Q2_PER") : 0l);
	        					encTargetsVO.setQ2Target(!json.getString("Q2_TARGET").equalsIgnoreCase("null") ? json.getLong("Q2_TARGET") : 0l);
	        					encTargetsVO.setQ3Achv(!json.getString("Q3_ACHV").equalsIgnoreCase("null") ? json.getLong("Q3_ACHV") : 0l);
	        					encTargetsVO.setQ3Per(!json.getString("Q3_PER").equalsIgnoreCase("null") ? json.getLong("Q3_PER") : 0l);
	        					encTargetsVO.setQ3Target(!json.getString("Q3_TARGET").equalsIgnoreCase("null") ? json.getLong("Q3_TARGET") : 0l);
	        					encTargetsVO.setQ4Target(!json.getString("Q4_TARGET").equalsIgnoreCase("null") ? json.getLong("Q4_TARGET") : 0l);
	        					encTargetsVO.setQ4Per(!json.getString("Q4_PER").equalsIgnoreCase("null") ? json.getLong("Q4_PER") : 0l);
	        					encTargetsVO.setQ4Achv(!json.getString("Q4_ACHV").equalsIgnoreCase("null") ? json.getLong("Q4_ACHV") : 0l);
	        					encTargetsVO.setSchemeName(!json.getString("SCHEME_NAME").trim().equalsIgnoreCase("null") ? json.getString("SCHEME_NAME") : "");
	        					encTargetsVO.setAgreementAmount(!json.getString("AGREEMENT_AMOUNT").equalsIgnoreCase("null") ? json.getDouble("AGREEMENT_AMOUNT") : 0.00);
	        					encTargetsVO.setScheme(!json.getString("SCHEME").equalsIgnoreCase("null") ? json.getLong("SCHEME") : 0l);    	
							}
						}						
					}
					}
					}
			finalList.addAll(locationMap.values());
			
		 }catch(Exception e){
			LOG.error("Exception raised at PrEncService - getEncTargetsAchievement  ", e);
		 }
			
		return finalList;
	}

}