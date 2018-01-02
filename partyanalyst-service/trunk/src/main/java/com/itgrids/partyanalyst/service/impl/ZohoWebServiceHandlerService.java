package com.itgrids.partyanalyst.service.impl;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.service.IZohoWebServiceHandlerService;

public class ZohoWebServiceHandlerService implements IZohoWebServiceHandlerService{

	private static final Logger LOG = Logger.getLogger(ZohoWebServiceHandlerService.class);
	
	public String testMethod()
	{
		try{
			return "Success";
			
		}catch(Exception e)
		{
			LOG.error(e);
		}
		return null;
	}
}
