package com.itgrids.service.integration;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IConvergenceTypeDAO;
import com.itgrids.dao.INregaComponentDAO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.NregaConsolidatedInputVO;
import com.itgrids.service.integration.impl.INREGSConsolidatedService;
import com.itgrids.utils.CommonMethodsUtilService;

@Service
@Transactional
public class NREGSConsolidatedService implements INREGSConsolidatedService{

	private static final Logger LOG = Logger.getLogger(NREGSConsolidatedService.class);
	
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
	//public List<NregaConsolidatedDataVO> getNREGSLevelWiseConsolidatedReport
	
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
