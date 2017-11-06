package com.itgrids.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.service.ISolidWasteManagementService;
import com.itgrids.utils.CommonMethodsUtilService;


@Service
@Transactional
public class SolidWasteManagementService implements ISolidWasteManagementService {
	

	private static final Logger LOG = Logger.getLogger( SolidWasteManagementService.class);

	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	
	/*@Autowired
	private WebServiceUtilService webServiceUtilService;*/
	
	
}