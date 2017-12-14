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
import com.itgrids.dto.EncVO;
import com.itgrids.dto.EncWorksVO;
import com.itgrids.dto.InputVO;
import com.itgrids.service.IPrENCService;
import com.itgrids.service.integration.external.WebServiceUtilService;
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
									EncVO subVo = new EncVO();
									mainVo.setLocationType(json.getString("UNIT_TYPE"));
									mainVo.setLocationId(json.getLong("UNIT_ID"));
									mainVo.setLocationName(json.getString("UNIT_NAME"));
									subVo.setParamName(json.getString("PARA"));
									subVo.setParamValue(json.getDouble("PARA_VALUE"));
									mainVo.getSubList().add(subVo);
									innermap.put(json.getLong("UNIT_ID"), mainVo);

								}else{
									for (EncVO encVO : mainVo.getSubList()) {
										if(encVO.getParamName().trim().equalsIgnoreCase(json.getString("PARA"))){
											encVO.setParamName(encVO.getParamValue()+json.getString("PARA"));
										}
									}
									
									EncVO encVO = new EncVO();
									encVO.setParamName(json.getString("PARA"));
									encVO.setParamValue(json.getDouble("PARA_VALUE"));
									mainVo.getSubList().add(encVO);
								}
								locationTypeMap.put(json.getString("UNIT_TYPE"), innermap);
							}else{
								EncVO mainVo = innermap.get(json.getLong("UNIT_ID"));
								
								if (mainVo == null) {
									mainVo = new EncVO();
									EncVO subVo = new EncVO();
									mainVo.setLocationType(json.getString("UNIT_TYPE"));
									mainVo.setLocationId(json.getLong("UNIT_ID"));
									mainVo.setLocationName(json.getString("UNIT_NAME"));
									subVo.setParamName(json.getString("PARA"));
									subVo.setParamValue(json.getDouble("PARA_VALUE"));
									mainVo.getSubList().add(subVo);
									innermap.put(json.getLong("UNIT_ID"), mainVo);

								}else{
									for (EncVO encVO : mainVo.getSubList()) {
										if(encVO.getParamName().trim().equalsIgnoreCase(json.getString("PARA"))){
											encVO.setParamName(encVO.getParamValue()+json.getString("PARA"));
										}
									}
									
									EncVO encVO = new EncVO();
									encVO.setParamName(json.getString("PARA"));
									encVO.setParamValue(json.getDouble("PARA_VALUE"));
									mainVo.getSubList().add(encVO);
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
						if(inputVO.getLocationType().trim().equalsIgnoreCase(json.getString("UNIT_TYPE")) && !json.getString("PARA").contains("HAB")){
							EncVO Vo =getMatchedVo(mainVO.getSubList(),json.getString("PARA"));
							if(Vo == null){
								Vo = new EncVO();
								Vo.setParamName(json.getString("PARA"));
								Vo.setParamValue(json.getDouble("PARA_VALUE"));
								mainVO.getSubList().add(Vo);
							}else{
								Vo.setParamValue(Vo.getParamValue()+json.getDouble("PARA_VALUE"));
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
		try{
			Map<Long,EncWorksVO> locationMap= new HashMap<Long, EncWorksVO>();
			ClientResponse response = webServiceUtilService.callWebService("http://predmis.ap.nic.in/RestWS/PredmisRoadService/wbsMinisterRoadMinMaxMv",null);
			
			if(inputVO.getLocationType().equalsIgnoreCase("d")){
				List<Object[]> districtdata= districtDAO.getEncDistricts();
			
			}else if(inputVO.getLocationType().equalsIgnoreCase("m")){
				List<Object[]> mandalData= tehsilDAO.getEncMandals();
				//EncWorksVO workVo = locationMap.get(json.getLong("DIST_CODE"));
			}else if(inputVO.getLocationType().equalsIgnoreCase("A")){
				List<Object[]> constituencyData= constituencyDAO.getEncconstituencies();
				//EncWorksVO workVo = locationMap.get(json.getLong("DIST_CODE"));
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
						if(inputVO.getLocationType().equalsIgnoreCase("d")){
							EncWorksVO workVo = locationMap.get(json.getLong("DIST_CODE"));
							if(workVo == null){
								workVo = new EncWorksVO();
								workVo.setLocationId(json.getLong("DIST_CODE"));
								workVo.setLocationName(json.getString(""));
							}else{
								
							}
						}else if(inputVO.getLocationType().equalsIgnoreCase("m")){
							locationMap.get(json.getLong("MAND_CODE"));
						}
						
					}
				}
				
			}
		}catch(Exception e){
			LOG.error("Exception raised at PrEncService - getLocationWiseWorksInformation  ", e);
		}
			
		return null;
	}


}
