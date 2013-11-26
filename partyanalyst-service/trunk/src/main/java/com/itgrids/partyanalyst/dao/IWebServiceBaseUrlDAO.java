package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.WebServiceBaseUrl;

public interface IWebServiceBaseUrlDAO extends GenericDao<WebServiceBaseUrl,Long>{
	
	public String getBaseURLForAnApp(String appName);
}
