package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SearchEngineIPAddress;

public interface ISearchEngineIPAddressDAO extends GenericDao<SearchEngineIPAddress,Long>{
	
	public List<String> getAllSearchEngineIPAddresses();
	
	public List<String> getAllIPAddress();

}
