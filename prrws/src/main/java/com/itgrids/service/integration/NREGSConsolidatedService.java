package com.itgrids.service.integration;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IConvergenceTypeDAO;
import com.itgrids.dao.INregaComponentDAO;
import com.itgrids.dao.INregaComponentServiceDAO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.NregaConsolidatedDataVO;
import com.itgrids.dto.NregaConsolidatedInputVO;
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
									
								}
							}
						}
		 	    	}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getNREGSLevelWiseConsolidatedReport - NREGSConsolidatedService Service", e);
		}
		return returnList;
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
}
