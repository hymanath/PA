package com.itgrids.partyanalyst.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.excel.booth.DataValidationVO;
import com.itgrids.partyanalyst.service.IDataValidationService;

public class DataValidationService implements IDataValidationService{
	private static final Logger LOG = Logger.getLogger(DataValidationService.class);
	
	public List<DataValidationVO> getHamletsAssignedValidation(Long constituencyId)
	{
		List<DataValidationVO> result = null;
		try{
			
			
			return result;
		}catch (Exception e) {
			LOG.error("Exception occured in getHamletsAssignedValidation() method ",e);
			return result;
		}
	}

}
