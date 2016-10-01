package com.itgrids.partyanalyst.service.impl;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dto.CadreRegistratedCountVO;
import com.itgrids.partyanalyst.service.ICoreDashboardCadreRegistrationService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class CoreDashboardCadreRegistrationService implements ICoreDashboardCadreRegistrationService {
		
	private final static Logger LOG = Logger.getLogger(CoreDashboardCadreRegistrationService.class);
	
	
	public CadreRegistratedCountVO showCadreRegistreredCount(){
	    CadreRegistratedCountVO regCountVO = null;
	    try {
	      
	      
	         ClientConfig clientConfig = new DefaultClientConfig();
	         
	         clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
	       
	         String webServiceUrl  = IConstants.CADRE_REGISTRATION_URL + "WebService/showCadreRegisteredCount";
	           
	         WebResource webResource = client.resource( webServiceUrl );
	           
	         regCountVO = webResource.accept("application/json").post(CadreRegistratedCountVO.class);
	        
	        
	    } catch (Exception e) {
	      LOG.error("Exception raised at showCadreRegistreredCount", e);
	    }
	    return regCountVO;
	  }
	public Object getRegistrationCountDtls(String location, Long constId){  
	    Object regCountVO = null;
	    try {
	      
	      
	         ClientConfig clientConfig = new DefaultClientConfig();
	         
	         clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
	       
	         String webServiceUrl  = IConstants.CADRE_REGISTRATION_URL + "WebService/getRegistrationCountDtls/"+location+"/"+constId;
	           
	         WebResource webResource = client.resource( webServiceUrl );
	           
	         regCountVO = webResource.accept("application/json").get(Object.class);
	         return regCountVO;
	        
	    } catch (Exception e) {
	      LOG.error("Exception raised at showCadreRegistreredCount", e);
	    }
	    return null;    
	  }
	
	
}
