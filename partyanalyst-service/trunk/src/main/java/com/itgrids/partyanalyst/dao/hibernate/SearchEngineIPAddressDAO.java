package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISearchEngineIPAddressDAO;
import com.itgrids.partyanalyst.model.SearchEngineIPAddress;

public class SearchEngineIPAddressDAO extends GenericDaoHibernate<SearchEngineIPAddress,Long> implements ISearchEngineIPAddressDAO{

	public SearchEngineIPAddressDAO()
	{
		super(SearchEngineIPAddress.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getAllSearchEngineIPAddresses()
	{
		return getHibernateTemplate().find("select distinct model.ipAddress from SearchEngineIPAddress model");
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getAllIPAddress()
	{
		return getHibernateTemplate().find("select distinct model.ipAddress from UserTracking model");
	}
}
