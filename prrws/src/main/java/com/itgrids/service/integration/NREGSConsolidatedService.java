package com.itgrids.service.integration;

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

import com.itgrids.dao.IConvergenceTypeDAO;
import com.itgrids.dao.INregaComponentDAO;
import com.itgrids.dao.INregaComponentServiceDAO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.NregaConsolidatedDataVO;
import com.itgrids.dto.NregaConsolidatedInputVO;
import com.itgrids.dto.NregsDataVO;
import com.itgrids.dto.NregsProjectsVO;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.service.integration.impl.INREGSConsolidatedService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.sun.jersey.api.client.ClientResponse;

@Service
@Transactional
public class NREGSConsolidatedService implements INREGSConsolidatedService{

	private static final Logger LOG = Logger.getLogger(NREGSConsolidatedService.class);
	
	@Autowired
	private INregaComponentServiceDAO nregaComponentServiceDAO;
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private IConvergenceTypeDAO convergenceTypeDAO;
	@Autowired
	private INregaComponentDAO nregaComponentDAO;
	
	/*
	 * Date : 21/07/2017
	 * Author :Sravanth
	 * @description : getNREGSLevelWiseConsolidatedReport
	 * 
	 */
	public List<NregaConsolidatedDataVO> getNREGSLevelWiseConsolidatedReport(NregaConsolidatedInputVO inputVO){
		List<NregaConsolidatedDataVO> returnList = new ArrayList<NregaConsolidatedDataVO>(0);
		try {
			Map<String,NregaConsolidatedDataVO> locationMap = new LinkedHashMap<String,NregaConsolidatedDataVO>();
			List<NregaConsolidatedInputVO> urlsList = new ArrayList<NregaConsolidatedInputVO>(0);
			
			if(inputVO != null && inputVO.getComponentIds() != null && !inputVO.getComponentIds().isEmpty()){
				List<Object[]> list = nregaComponentServiceDAO.getComponentUrlsByComponentIds(inputVO.getComponentIds(), "DATA");
				if(list != null && !list.isEmpty()){
					for (Object[] obj : list) {
						NregaConsolidatedInputVO vo = new NregaConsolidatedInputVO();
						vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						vo.setUrl(obj[2] != null ? obj[2].toString():"");
						vo.setComponentName(obj[1] != null ? obj[1].toString():"");
						urlsList.add(vo);
					}
				}
			}
			
			String str = convertingInputVOToString(inputVO);
			if(urlsList != null && !urlsList.isEmpty()){
				for (NregaConsolidatedInputVO urlvo : urlsList) {
					ClientResponse response = webServiceUtilService.callWebService(urlvo.getUrl().toString(), str);
					if(response.getStatus() != 200){
						throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
					}else{
						String output = response.getEntity(String.class);
						if(output != null && !output.isEmpty()){
							JSONArray finalArray = new JSONArray(output);
							if(finalArray != null && finalArray.length() > 0){
								for(int i=0;i<finalArray.length();i++){
									JSONObject jObj = (JSONObject) finalArray.get(i);
									if(inputVO.getLocationType().trim().equalsIgnoreCase("state") && inputVO.getSubLocationType().trim().equalsIgnoreCase("state")){
										NregaConsolidatedDataVO levelvo = locationMap.get("state");
										if(levelvo == null){
											levelvo = new NregaConsolidatedDataVO();
												
												NregaConsolidatedDataVO componentvo = new NregaConsolidatedDataVO();
												componentvo.setName("state");
												componentvo.setComponent(urlvo.getComponentName());
												if(urlvo.getComponentName() != null && urlvo.getComponentName().trim().equalsIgnoreCase("Labour Budget")){
													componentvo.setPercentage(jObj.getString("PER_APP_LB"));
												}
												else if(urlvo.getComponentName() != null && 
														(urlvo.getComponentName().trim().equalsIgnoreCase("Horticulture") || urlvo.getComponentName().trim().equalsIgnoreCase("Avenue"))){
													componentvo.setPercentage(jObj.getString("PERCENTAGEOFPLANTING"));
												}
												else{
													componentvo.setPercentage(jObj.getString("PERCENTAGE"));
												}
												levelvo.getSubList().add(componentvo);
												
											locationMap.put("state", levelvo);
										}else{
											NregaConsolidatedDataVO componentvo = getMatchedVOByString(levelvo.getSubList(), urlvo.getComponentName());
											if(componentvo == null){
												componentvo = new NregaConsolidatedDataVO();
												componentvo.setName("state");
												componentvo.setComponent(urlvo.getComponentName());
												if(urlvo.getComponentName() != null && urlvo.getComponentName().trim().equalsIgnoreCase("Labour Budget")){
													componentvo.setPercentage(jObj.getString("PER_APP_LB"));
												}
												else if(urlvo.getComponentName() != null && 
														(urlvo.getComponentName().trim().equalsIgnoreCase("Horticulture") || urlvo.getComponentName().trim().equalsIgnoreCase("Avenue"))){
													componentvo.setPercentage(jObj.getString("PERCENTAGEOFPLANTING"));
												}
												else{
													componentvo.setPercentage(jObj.getString("PERCENTAGE"));
												}
												levelvo.getSubList().add(componentvo);
											}
										}
									}else{
										NregaConsolidatedDataVO levelvo = null;
										if(inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
											levelvo = locationMap.get(jObj.getString("DISTRICT").trim());
										else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
											levelvo = locationMap.get(jObj.getString("CONSTITUENCY").trim());
										else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal"))
											levelvo = locationMap.get(jObj.getString("MANDAL").trim());
										else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("panchayat"))
											levelvo = locationMap.get(jObj.getString("PANCHAYAT").trim());
										
										if(levelvo == null){
											levelvo = new NregaConsolidatedDataVO();
												
												NregaConsolidatedDataVO componentvo = new NregaConsolidatedDataVO();
												
												if(inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
													componentvo.setName(jObj.getString("DISTRICT").trim());
												else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
													componentvo.setName(jObj.getString("CONSTITUENCY").trim());
												else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal"))
													componentvo.setName(jObj.getString("MANDAL").trim());
												else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("panchayat"))
													componentvo.setName(jObj.getString("PANCHAYAT").trim());
												componentvo.setComponent(urlvo.getComponentName());
												if(urlvo.getComponentName() != null && urlvo.getComponentName().trim().equalsIgnoreCase("Labour Budget")){
													componentvo.setPercentage(jObj.getString("PER_APP_LB"));
												}
												else if(urlvo.getComponentName() != null && 
														(urlvo.getComponentName().trim().equalsIgnoreCase("Horticulture") || urlvo.getComponentName().trim().equalsIgnoreCase("Avenue"))){
													componentvo.setPercentage(jObj.getString("PERCENTAGEOFPLANTING"));
												}
												else{
													componentvo.setPercentage(jObj.getString("PERCENTAGE"));
												}
												levelvo.getSubList().add(componentvo);
												
											if(inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
												locationMap.put(jObj.getString("DISTRICT").trim(), levelvo);
											else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
												locationMap.put(jObj.getString("CONSTITUENCY").trim(), levelvo);
											else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal"))
												locationMap.put(jObj.getString("MANDAL").trim(), levelvo);
											else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("panchayat"))
												locationMap.put(jObj.getString("PANCHAYAT").trim(), levelvo);
										}else{
											NregaConsolidatedDataVO componentvo = getMatchedVOByString(levelvo.getSubList(), urlvo.getComponentName());
											if(componentvo == null){
												componentvo = new NregaConsolidatedDataVO();
												
												if(inputVO.getSubLocationType().trim().equalsIgnoreCase("district"))
													componentvo.setName(jObj.getString("DISTRICT").trim());
												else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("constituency"))
													componentvo.setName(jObj.getString("CONSTITUENCY").trim());
												else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("mandal"))
													componentvo.setName(jObj.getString("MANDAL").trim());
												else if(inputVO.getSubLocationType().trim().equalsIgnoreCase("panchayat"))
													componentvo.setName(jObj.getString("PANCHAYAT").trim());
												componentvo.setComponent(urlvo.getComponentName());
												if(urlvo.getComponentName() != null && urlvo.getComponentName().trim().equalsIgnoreCase("Labour Budget")){
													componentvo.setPercentage(jObj.getString("PER_APP_LB"));
												}
												else if(urlvo.getComponentName() != null && 
														(urlvo.getComponentName().trim().equalsIgnoreCase("Horticulture") || urlvo.getComponentName().trim().equalsIgnoreCase("Avenue"))){
													componentvo.setPercentage(jObj.getString("PERCENTAGEOFPLANTING"));
												}
												else{
													componentvo.setPercentage(jObj.getString("PERCENTAGE"));
												}
												levelvo.getSubList().add(componentvo);
											}
										}
									}
								}
							}
						}
		 	    	}
				}
			}
			
			if(locationMap != null)
				returnList = new ArrayList<NregaConsolidatedDataVO>(locationMap.values());
			
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSLevelWiseConsolidatedReport - NREGSConsolidatedService Service", e);
		}
		return returnList;
	}
	
	/*
	 * Date : 21/07/2017
	 * Author :Sravanth
	 * @description : getMatchedVOByString
	 * 
	 */
	public NregaConsolidatedDataVO getMatchedVOByString(List<NregaConsolidatedDataVO> list,String component){
		try{
			if(list != null && !list.isEmpty()){
				for (NregaConsolidatedDataVO nregaConsolidatedDataVO : list) {
					if(nregaConsolidatedDataVO.getComponent().trim().equalsIgnoreCase(component)){
						return nregaConsolidatedDataVO;
					}
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at getMatchedVOByString - NREGSConsolidatedService service", e);
		}
		return null;
	}
	
	/*
	 * Date : 21/07/2017
	 * Author :Sravanth
	 * @description : convertingInputVOToString
	 * 
	 */
	public String convertingInputVOToString(NregaConsolidatedInputVO inputVO){
		String str = "";
		try {
			/*if(inputVO.getLocationId() != null)
				if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
					if(inputVO.getLocationId().longValue() > 0l && inputVO.getLocationId().longValue() <= 9l)
						inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
				}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
					if(inputVO.getLocationId().longValue() > 0l)
						inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
				}*/
				
			str = "{";
			
			if(inputVO.getFromDate() != null )
				str += "\"fromDate\" : \""+inputVO.getFromDate()+"\",";
			if(inputVO.getToDate() != null)
				str += "\"toDate\" : \""+inputVO.getToDate()+"\",";
			if(inputVO.getYear() != null)
				str += "\"year\" : \""+inputVO.getYear()+"\",";
			if(inputVO.getLocationType() != null)
				str += "\"locationType\" : \""+inputVO.getLocationType()+"\",";
			if(inputVO.getLocationIdStr() != null)
				str += "\"locationId\" : \""+inputVO.getLocationIdStr()+"\",";
			if(inputVO.getSubLocationType() != null)
				str += "\"SublocationType\" : \""+inputVO.getSubLocationType()+"\",";
			if(inputVO.getComponentName() != null)
				str += "\"type\" : \""+inputVO.getComponentName()+"\",";
			
			if(str.length() > 1)
				str = str.substring(0,str.length()-1);
			
			str += "}";
			
		} catch (Exception e) {
			LOG.error("Exception raised at convertingInputVOToString - NREGSConsolidatedService service", e);
		}
		return str;
	}
	
	/*
	 * Date : 21/7/17
	 * Author : Nandhini.k
	 * @description : getting All Convergence Types
	 */
	public List<IdNameVO> getAllConvergenceTypes(){
		List<IdNameVO> finalList = new ArrayList<IdNameVO>(0);
		try{
			List<Object[]> convergenceList = convergenceTypeDAO.getAllConvergenceTypes();
			if(commonMethodsUtilService.isListOrSetValid(convergenceList)){
				for (Object[] objects : convergenceList) {
					IdNameVO vo = new IdNameVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					finalList.add(vo);
				}
			}
		}catch (Exception e){
			LOG.error("Exception raised at getAllConvergenceTypes - NREGSConsolidatedService", e);
		}
		return finalList;
	}
	
	/*
	 * Date : 21/7/17
	 * Author : Nandhini.k
	 * @description : getting Component(IHHL OR Labor Budget ETC) By Passing ConvergenceTypeId 
	 */
	public List<IdNameVO> getComponentByConvergType(NregaConsolidatedInputVO nregaConsolidatedInputVO){
		List<IdNameVO> finalList = new ArrayList<IdNameVO>(0);
		try{
			List<Object[]> componentList = nregaComponentDAO.getComponentByConvergType(nregaConsolidatedInputVO.getConvergenceTypeId());
			if(commonMethodsUtilService.isListOrSetValid(componentList)){
				for (Object[] objects : componentList) {
					IdNameVO vo = new IdNameVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					finalList.add(vo);
				}
			}
		}catch (Exception e){
			LOG.error("Exception raised at getComponentByConvergType - NREGSConsolidatedService", e);
		}
		return finalList;
	}
	
	/*
	 * Date : 21/07/2017
	 * Author :Nandhini.k
	 * @description : getNREGSConsolidatedAbstrct
	 * 
	 */
	public List<NregsProjectsVO> getNREGSConsolidatedAbstrct(NregaConsolidatedInputVO inputVO){
		List<NregsProjectsVO> returnList = new ArrayList<NregsProjectsVO>();
		try {
			List<NregsProjectsVO> voList = null;
			List<NregaConsolidatedInputVO> urlsList = new ArrayList<NregaConsolidatedInputVO>(0);
			if(inputVO != null && inputVO.getComponentIds() != null && !inputVO.getComponentIds().isEmpty()){
				List<Object[]> list = nregaComponentServiceDAO.getComponentUrlsByComponentIds(inputVO.getComponentIds(), "ABSTRACT");
				if(list != null && !list.isEmpty()){
					for (Object[] obj : list) {
						NregaConsolidatedInputVO vo = new NregaConsolidatedInputVO();
						vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						vo.setUrl(obj[2] != null ? obj[2].toString():"");
						vo.setComponentName(obj[1] != null ? obj[1].toString():"");
						urlsList.add(vo);
					}
				}
			}
			
			String projectType = null;
			//String str = convertingInputVOToString(inputVO);
			if(urlsList != null && !urlsList.isEmpty()){
				for (NregaConsolidatedInputVO urlvo : urlsList) {
					NregsProjectsVO finalVO =  new NregsProjectsVO();
					inputVO.setComponentName(urlvo.getComponentName());
					String str = convertingInputVOToString(inputVO);
					ClientResponse response = webServiceUtilService.callWebService(urlvo.getUrl().toString(), str);
					if(response.getStatus() != 200){
						throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
					}else{
						if(inputVO.getComponentName() != null && inputVO.getComponentName().trim().equalsIgnoreCase("Burial Grounds"))
			 	    		  inputVO.setComponentName("Burial Ground");
			 	    	  else if(inputVO.getComponentName() != null && inputVO.getComponentName().trim().equalsIgnoreCase("Anganwadi Buildings"))
			 	    		  inputVO.setComponentName("Anganwadi");
			 	    	  else if(inputVO.getComponentName() != null && inputVO.getComponentName().trim().equalsIgnoreCase("GP Buildings"))
			 	    		  inputVO.setComponentName("Gram Panchayat Buildings");
			 	    	 else if(inputVO.getComponentName() != null && inputVO.getComponentName().trim().equalsIgnoreCase("Silk Worms"))
			 	    		  inputVO.setComponentName("Silk worm");
			 	    	 else if(inputVO.getComponentName() != null && inputVO.getComponentName().trim().equalsIgnoreCase("Cattle Drinking Water Troughs"))
			 	    		  inputVO.setComponentName("Cattle drinking water trough");
			 	    	 else if(inputVO.getComponentName() != null && inputVO.getComponentName().trim().equalsIgnoreCase("Raising of Perinnial Fodders"))
				 	    	  inputVO.setComponentName("Raising of Perinnial Fodder");
						
						String output = response.getEntity(String.class);
						if(output != null && !output.isEmpty()){
							JSONArray finalArray = new JSONArray(output);
							if(finalArray != null && finalArray.length() > 0){
								for(int i=0;i<finalArray.length();i++){
									if(inputVO.getComponentName().trim().equalsIgnoreCase("Labour Budget") || inputVO.getComponentName().trim().equalsIgnoreCase("Farm Ponds") ||
									   inputVO.getComponentName().trim().equalsIgnoreCase("IHHL") || inputVO.getComponentName().trim().equalsIgnoreCase("Vermi Compost") || 
									   inputVO.getComponentName().trim().equalsIgnoreCase("Agriculture Activities") || inputVO.getComponentName().trim().equalsIgnoreCase("Average Wage") ||
									   inputVO.getComponentName().trim().equalsIgnoreCase("Average Days of Employment") || inputVO.getComponentName().trim().equalsIgnoreCase("HH Completed 100 Days") || 
									   inputVO.getComponentName().trim().equalsIgnoreCase("Timely Payment") || inputVO.getComponentName().trim().equalsIgnoreCase("Horticulture") ||
									   inputVO.getComponentName().trim().equalsIgnoreCase("Avenue") || inputVO.getComponentName().trim().equalsIgnoreCase("Nurseries")){
										
										NregsProjectsVO vo = new NregsProjectsVO();
				 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
				 	    				
				 	    				if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("Nurseries") && (inputVO.getLocationType().toString().trim().equalsIgnoreCase("state") || inputVO.getLocationType().toString().trim().equalsIgnoreCase("constituency")))
				 	    					vo.setParameter(jObj.getString("'NURSERIES'"));
				 	    				else
				 	    					vo.setParameter(jObj.getString("PARAMETER"));
				 	    				
				 	    				if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("Average Wage")){
				 	    					vo.setTarget(jObj.getString("MAX(T.AVG_WAGE_TARGET)"));
					 	    				vo.setCompleted(jObj.getString("AVG_WAGE"));
				 	    				}
				 	    				else if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("Average Days of Employment")){
				 	    					vo.setTarget(jObj.getString("MAX(T.AVG_DAYS_TARGET)"));
					 	    				vo.setCompleted(jObj.getString("AVG_DAYS"));
				 	    				}
				 	    				else if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("HH Completed 100 Days")){
				 	    					vo.setTarget(jObj.getString("ROUND(NVL(SUM(HH_WORKING)*0.2,0),0)"));
					 	    				vo.setCompleted(jObj.getString("COMP_100"));
				 	    				}
				 	    				else if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("Timely Payment")){
				 	    					vo.setTarget(jObj.getString("MAX(T.UPLOAD_5_TARGET)"));
					 	    				vo.setCompleted(jObj.getString("UPLOAD_5"));
				 	    				}
				 	    				else if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("Horticulture") || inputVO.getComponentName().toString().trim().equalsIgnoreCase("Avenue")){
				 	    					vo.setTarget(jObj.getString("TARGET"));
					 	    				vo.setCompleted(jObj.getString("ACHIVEMENT"));
				 	    				}
				 	    				else if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("Payments")){
				 	    					vo.setFTONOTGENCNT(jObj.getString("FTONOTGENCNT"));
				 	    					vo.setFTONOTUPLOADCNT(jObj.getString("FTONOTUPLOADCNT"));
				 	    					vo.setFTONOTSENTCNT(jObj.getString("FTONOTSENTCNT"));
				 	    					vo.setREJECTCNT(jObj.getString("REJECTCNT"));
				 	    				}
				 	    				else{
				 	    					vo.setTarget(jObj.getString("TARGET"));
					 	    				vo.setCompleted(jObj.getString("COMPLETED"));
				 	    				}
				 	    				
				 	    				if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("IHHL") || inputVO.getComponentName().toString().trim().equalsIgnoreCase("Vermi Compost")
				 	    						|| inputVO.getComponentName().toString().trim().equalsIgnoreCase("Agriculture Activities")
				 	    						|| inputVO.getComponentName().toString().trim().equalsIgnoreCase("Farm Ponds"))
				 	    					vo.setPercentage(jObj.getString("PERC"));
				 	    				else if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("Average Wage")
				 	    						|| inputVO.getComponentName().toString().trim().equalsIgnoreCase("Average Days of Employment")
				 	    						|| inputVO.getComponentName().toString().trim().equalsIgnoreCase("HH Completed 100 Days")
				 	    						|| inputVO.getComponentName().toString().trim().equalsIgnoreCase("Timely Payment")
				 	    						|| inputVO.getComponentName().toString().trim().equalsIgnoreCase("Horticulture") || inputVO.getComponentName().toString().trim().equalsIgnoreCase("Avenue"))
				 	    					vo.setPercentage(jObj.getString("PER"));
				 	    				else if(inputVO.getComponentName().toString().trim().equalsIgnoreCase("Payments"))
				 	    					vo.setPENDINGRESPONSECNT(jObj.getString("PENDINGRESPONSECNT"));
				 	    				else
				 	    					vo.setPercentage(jObj.getString("PERCENTAGE"));
				 	    				
				 	    				if(inputVO.getLocationType().trim().equalsIgnoreCase("state"))
				 	    					vo.setType("STATE");
				 	    				else if(vo.getParameter().contains("state") || vo.getParameter().contains("State") || vo.getParameter().contains("STATE"))
				 	    					vo.setType("STATE");
				 	    				else if(vo.getParameter().contains("district") || vo.getParameter().contains("District") || vo.getParameter().contains("DISTRICT"))
				 	    					vo.setType("DISTRICT");
				 	    				else if(inputVO.getLocationType().trim().equalsIgnoreCase("constituency"))
				 	    					vo.setType("CONSTITUENCY");
				 	    				finalVO.getSubList().add(vo);
				 	    				
									}else{
										NregsProjectsVO vo = new NregsProjectsVO();
				 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
				 	    				if(inputVO.getLocationType().trim().equalsIgnoreCase("district"))
				 	    					projectType = jObj.getString("CAT_NAME||'_DISTRICT'");
				 	    				else if(inputVO.getLocationType().trim().equalsIgnoreCase("state"))
				 	    					projectType = jObj.getString("CAT_NAME");
				 	    				
				 	    				String[]  projectTypeArr = projectType.split("_");
				 	    				if(inputVO.getComponentName() != null && inputVO.getComponentName().trim().equalsIgnoreCase(projectTypeArr[0])){
				 	    					if(inputVO.getLocationType().trim().equalsIgnoreCase("district"))
				 	    						vo.setParameter(jObj.getString("CAT_NAME||'_DISTRICT'"));
				 	    					else if(inputVO.getLocationType().trim().equalsIgnoreCase("state"))
				 	    						vo.setParameter(jObj.getString("CAT_NAME"));
				 	    					else
				 	    						vo.setParameter(jObj.getString("CAT_NAME"));
				 	    					
				 	    					if(inputVO.getLocationType().trim().equalsIgnoreCase("district"))
				 	    						vo.setType(projectTypeArr[1]);
				 	    					else if(inputVO.getLocationType().trim().equalsIgnoreCase("state"))
				 	    						vo.setType("STATE");
				 	    					else
				 	    						vo.setType("CONSTITUENCY");
				 	    					
				 	    					vo.setTarget(jObj.getString("TARGET"));
					 	    				vo.setCompleted(jObj.getString("COMPLETED"));
					 	    				vo.setPercentage(jObj.getString("PERC"));
					 	    				finalVO.getSubList().add(vo);
				 	    				}
									}
								}
							}
						}
		 	    	}
					finalVO.setComponentType(inputVO.getComponentName());
					returnList.add(finalVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSLevelWiseConsolidatedReport - NREGSConsolidatedService Service", e);
		}
		return returnList;
	}
}
