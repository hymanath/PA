package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IOptionTypeDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ISurveyAnalysisService;

public class SurveyAnalysisService implements ISurveyAnalysisService {
	private static final Logger log = Logger.getLogger(SurveyAnalysisService.class);
	private IOptionTypeDAO optionTypeDAO;
	
	public IOptionTypeDAO getOptionTypeDAO() {
		return optionTypeDAO;
	}
	public void setOptionTypeDAO(IOptionTypeDAO optionTypeDAO) {
		this.optionTypeDAO = optionTypeDAO;
	}
	
	public List<SelectOptionVO>  getOptionTypes(){
		List<SelectOptionVO>  optionTypes = new ArrayList<SelectOptionVO>();
		SelectOptionVO valuesVo = null;
		List<Object[]> list = null;
		try{
		log.debug("Enterd into getOptionTypes Method in SurveyAnalysis service");
		list =  optionTypeDAO.getOptionTypes();
		if(list != null){
			for(Object[] params : list){
				valuesVo = new SelectOptionVO();
				valuesVo.setId((Long)params[0]);
				valuesVo.setName(params[1].toString());
				
				optionTypes.add(valuesVo);
			}
		}
		
		}
		catch (Exception e) {
			log.error("Exception in getOptionTypes Method in SurveyAnalysis service",e);
			
		}
		
		return optionTypes;
	}
  
}
