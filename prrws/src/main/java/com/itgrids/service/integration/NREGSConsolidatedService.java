package com.itgrids.service.integration;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.service.integration.impl.INREGSConsolidatedService;

@Service
@Transactional
public class NREGSConsolidatedService implements INREGSConsolidatedService{

	private static final Logger LOG = Logger.getLogger(NREGSConsolidatedService.class);
	
	/*
	 * Date : 21/07/2017
	 * Author :Sravanth
	 * @description : getNREGSLevelWiseConsolidatedReport
	 * 
	 */
	//public List<NregaConsolidatedDataVO> getNREGSLevelWiseConsolidatedReport
}
