package com.itgrids.partyanalyst.service.impl;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.keys.PropertyKeys;
import com.itgrids.partyanalyst.service.IPartyAnalystPropertyService;

public class PartyAnalystPropertyService implements
		IPartyAnalystPropertyService  {
	
	private static final Logger logger = Logger.getLogger(PartyAnalystPropertyService.class);
	
	private static Properties props = null;
	
	static{
		props = new Properties();
		try{
			props.load(ClassLoader.getSystemResourceAsStream(PropertyKeys.SERVICE_PROPERTY_FILE_NAME));
		}catch(IOException ioe){
			logger.error(ioe);
		}
	}

	public String getProperty(String key) {
		return getProperty(key,null);
	}

	public String getProperty(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}

}
