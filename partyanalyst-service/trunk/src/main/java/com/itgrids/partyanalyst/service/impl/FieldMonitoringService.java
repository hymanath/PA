package com.itgrids.partyanalyst.service.impl;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IFieldVendorLocationDAO;
import com.itgrids.partyanalyst.dao.IFieldVendorTabUserDAO;
import com.itgrids.partyanalyst.service.IFieldMonitoringService;

public class FieldMonitoringService implements IFieldMonitoringService {
	
	private static final Logger LOG = Logger.getLogger(FieldMonitoringService.class);
	
	//Attributes
	private IFieldVendorLocationDAO fieldVendorLocationDAO;
	private IFieldVendorTabUserDAO  fieldVendorTabUserDAO;
	
	//Setters
	public void setFieldVendorLocationDAO(
			IFieldVendorLocationDAO fieldVendorLocationDAO) {
		this.fieldVendorLocationDAO = fieldVendorLocationDAO;
	}
	public void setFieldVendorTabUserDAO(
			IFieldVendorTabUserDAO fieldVendorTabUserDAO) {
		this.fieldVendorTabUserDAO = fieldVendorTabUserDAO;
	}
	
  
	
}
