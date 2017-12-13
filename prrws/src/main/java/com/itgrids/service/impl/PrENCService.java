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

import com.itgrids.dto.EncVO;
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
	
	
	@Override
	public List<EncVO> getLocationWiseRoadsInformation(InputVO inputVO) {
		List<EncVO> resultList = new ArrayList<EncVO>();
		try {
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
						Map<Long, EncVO> innermap = locationTypeMap.get(json.getString("UNIT_TYPE"));
						if (innermap == null) {
							innermap = new HashMap<Long, EncVO>();
							EncVO mainVo = innermap.get(json.getLong("UNIT_ID"));
							
							if (mainVo == null) {
								mainVo = new EncVO();
								EncVO subVo = new EncVO();
								mainVo.setLocationType(json.getString("UNIT_TYPE"));
								mainVo.setLocationId(json.getLong("UNIT_ID"));
								mainVo.setLocationName("UNIT_NAME");
								subVo.setParamName(json.getString("PARA"));
								subVo.setParamValue(json.getLong("PARA_VALUE"));
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
								encVO.setParamValue(json.getLong("PARA_VALUE"));
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
								mainVo.setLocationName("UNIT_NAME");
								subVo.setParamName(json.getString("PARA"));
								subVo.setParamValue(json.getLong("PARA_VALUE"));
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
								encVO.setParamValue(json.getLong("PARA_VALUE"));
								mainVo.getSubList().add(encVO);
							}
							locationTypeMap.put(json.getString("UNIT_TYPE"), innermap);
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
				resultList.add(mainVo);
			}

		}catch(Exception e){
			EncVO vo = new EncVO();
			vo.setErrorMessage(e.getLocalizedMessage());
			resultList.add(vo);
			LOG.error("Exception raised at PrENCService - getLocationWiseRoadsInformation", e);
		}
		
		return resultList;
	}



}
