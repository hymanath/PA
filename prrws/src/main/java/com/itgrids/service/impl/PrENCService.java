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
										workVo.setCompletedCount(workVo.getCompletedCount()+json.getLong("TOTWORKSCOMPLETED"));
										workVo.setNotGrounded(workVo.getNotGrounded()+json.getLong("TOT_WORKS_NOTGROUNDED"));
										workVo.setUnderProcessCount(workVo.getUnderProcessCount()+json.getLong("TOT_WORKS_ONGOING"));
										workVo.setGroundedCount(workVo.getGroundedCount()+json.getLong("GROUNDED"));
										workVo.setTotalWorksEntrusted(workVo.getTotalWorksEntrusted()+json.getLong("TOT_WORKS_ENTRUST"));
									}
								}else if(inputVO.getLocationType().equalsIgnoreCase("s")){
									workVo = locationMap.get(1l);
									if(workVo!=null){
										workVo.setAdminSanctionCount(workVo.getAdminSanctionCount()+json.getLong("TOT_WORKS"));
										workVo.setTechnicallySanctionedCount(workVo.getTechnicallySanctionedCount()+json.getLong("TOT_WORKS_TECHSANC"));
										workVo.setCompletedCount(workVo.getCompletedCount()+json.getLong("TOTWORKSCOMPLETED"));
										workVo.setNotGrounded(workVo.getNotGrounded()+json.getLong("TOT_WORKS_NOTGROUNDED"));
										workVo.setUnderProcessCount(workVo.getUnderProcessCount()+json.getLong("TOT_WORKS_ONGOING"));
										workVo.setGroundedCount(workVo.getGroundedCount()+json.getLong("GROUNDED"));
										workVo.setTotalWorksEntrusted(workVo.getTotalWorksEntrusted()+json.getLong("TOT_WORKS_ENTRUST"));
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
									workVo.setCompletedCount(workVo.getCompletedCount()+json.getLong("TOTWORKSCOMPLETED"));
									workVo.setNotGrounded(workVo.getNotGrounded()+json.getLong("TOT_WORKS_NOTGROUNDED"));
									workVo.setUnderProcessCount(workVo.getUnderProcessCount()+json.getLong("TOT_WORKS_ONGOING"));
									workVo.setGroundedCount(workVo.getGroundedCount()+json.getLong("GROUNDED"));
									workVo.setTotalWorksEntrusted(workVo.getTotalWorksEntrusted()+json.getLong("TOT_WORKS_ENTRUST"));
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
									encVo.setCompletedCount(encVo.getCompletedCount()+vo.getCompletedCount());
									encVo.setNotGrounded(encVo.getNotGrounded()+vo.getNotGrounded());
									encVo.setUnderProcessCount(encVo.getUnderProcessCount()+vo.getUnderProcessCount());
									encVo.setGroundedCount(encVo.getGroundedCount()+vo.getGroundedCount());
									encVo.setTotalWorksEntrusted(encVo.getTotalWorksEntrusted()+vo.getTotalWorksEntrusted());
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
	        					encTargetsVO.setTotPer(encTargetsVO.getTotPer()+json.getLong("TOT_PER"));
	        					
	        					encTargetsVO.setQ1Achv( encTargetsVO.getQ1Achv()+json.getLong("Q1_ACHV") );
	        					encTargetsVO.setQ1Per(json.getLong("Q1_PER")+encTargetsVO.getQ1Per());
	        					encTargetsVO.setQ1Target(json.getLong("Q1_TARGET")+encTargetsVO.getQ1Target());
	        					
	        					encTargetsVO.setQ2Achv(json.getLong("Q2_ACHV")+encTargetsVO.getQ2Achv());
	        					encTargetsVO.setQ2Per(json.getLong("Q2_PER")+encTargetsVO.getQ2Per());
	        					encTargetsVO.setQ2Target( json.getLong("Q2_TARGET")+encTargetsVO.getQ2Target());
	        					
	        					encTargetsVO.setQ3Achv(json.getLong("Q3_ACHV")+encTargetsVO.getQ3Achv());
	        					encTargetsVO.setQ3Per(json.getLong("Q3_PER")+encTargetsVO.getQ3Per());
	        					encTargetsVO.setQ3Target(json.getLong("Q3_TARGET")+encTargetsVO.getQ3Target());
	        					
	        					encTargetsVO.setQ4Target(json.getLong("Q4_TARGET")+encTargetsVO.getQ4Target());
	        					encTargetsVO.setQ4Per(json.getLong("Q4_PER")+encTargetsVO.getQ4Per());
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
		        					encTargetsVO.setTotPopu((double) Math.round(encTargetsVO.getTotPopu()+json.getDouble("TOT_POPU")));
		        					encTargetsVO.setTotWorks(encTargetsVO.getTotWorks()+ json.getLong("TOT_WORKS") );
		        					encTargetsVO.setTotLength((double) Math.round(encTargetsVO.getTotLength()+json.getDouble("TOT_LENGTH")));
		        					encTargetsVO.setTotPer(encTargetsVO.getTotPer()+json.getLong("TOT_PER"));
		        					
		        					encTargetsVO.setQ1Achv( encTargetsVO.getQ1Achv()+json.getLong("Q1_ACHV") );
		        					encTargetsVO.setQ1Per(json.getLong("Q1_PER")+encTargetsVO.getQ1Per());
		        					encTargetsVO.setQ1Target(json.getLong("Q1_TARGET")+encTargetsVO.getQ1Target());
		        					
		        					encTargetsVO.setQ2Achv(json.getLong("Q2_ACHV")+encTargetsVO.getQ2Achv());
		        					encTargetsVO.setQ2Per(json.getLong("Q2_PER")+encTargetsVO.getQ2Per());
		        					encTargetsVO.setQ2Target( json.getLong("Q2_TARGET")+encTargetsVO.getQ2Target());
		        					
		        					encTargetsVO.setQ3Achv(json.getLong("Q3_ACHV")+encTargetsVO.getQ3Achv());
		        					encTargetsVO.setQ3Per(json.getLong("Q3_PER")+encTargetsVO.getQ3Per());
		        					encTargetsVO.setQ3Target(json.getLong("Q3_TARGET")+encTargetsVO.getQ3Target());
		        					
		        					encTargetsVO.setQ4Target(json.getLong("Q4_TARGET")+encTargetsVO.getQ4Target());
		        					encTargetsVO.setQ4Per(json.getLong("Q4_PER")+encTargetsVO.getQ4Per());
		        					encTargetsVO.setQ4Achv(encTargetsVO.getQ4Achv()+json.getLong("Q4_ACHV"));
		        					
		        					encTargetsVO.setAgreementAmount((double) Math.round(encTargetsVO.getAgreementAmount()+json.getDouble("AGREEMENT_AMOUNT")));
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
        					encTargetsVO.setTotPer(encTargetsVO.getTotPer()+json.getLong("TOT_PER"));
        					
        					encTargetsVO.setQ1Achv( encTargetsVO.getQ1Achv()+json.getLong("Q1_ACHV") );
        					encTargetsVO.setQ1Per(json.getLong("Q1_PER")+encTargetsVO.getQ1Per());
        					encTargetsVO.setQ1Target(json.getLong("Q1_TARGET")+encTargetsVO.getQ1Target());
        					
        					encTargetsVO.setQ2Achv(json.getLong("Q2_ACHV")+encTargetsVO.getQ2Achv());
        					encTargetsVO.setQ2Per(json.getLong("Q2_PER")+encTargetsVO.getQ2Per());
        					encTargetsVO.setQ2Target( json.getLong("Q2_TARGET")+encTargetsVO.getQ2Target());
        					
        					encTargetsVO.setQ3Achv(json.getLong("Q3_ACHV")+encTargetsVO.getQ3Achv());
        					encTargetsVO.setQ3Per(json.getLong("Q3_PER")+encTargetsVO.getQ3Per());
        					encTargetsVO.setQ3Target(json.getLong("Q3_TARGET")+encTargetsVO.getQ3Target());
        					
        					encTargetsVO.setQ4Target(json.getLong("Q4_TARGET")+encTargetsVO.getQ4Target());
        					encTargetsVO.setQ4Per(json.getLong("Q4_PER")+encTargetsVO.getQ4Per());
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

}