package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.WebServiceBaseUrl;

public interface IWebServiceBaseUrlDAO extends GenericDao<WebServiceBaseUrl,Long>{
	
	public String getBaseURLForAnApp(String appName);
	
	public WebServiceBaseUrl getBaseUrlDataForAnApp(String appName);
	public List<WebServiceBaseUrl> getBaseUrlsForAnApp(String appName);
}
