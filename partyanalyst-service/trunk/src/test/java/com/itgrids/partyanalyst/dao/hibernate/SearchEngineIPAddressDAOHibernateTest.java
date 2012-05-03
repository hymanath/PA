package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISearchEngineIPAddressDAO;

public class SearchEngineIPAddressDAOHibernateTest extends BaseDaoTestCase{
	
	private ISearchEngineIPAddressDAO searchEngineIPAddressDAO;

	public void setSearchEngineIPAddressDAO(
			ISearchEngineIPAddressDAO searchEngineIPAddressDAO) {
		this.searchEngineIPAddressDAO = searchEngineIPAddressDAO;
	}
	
	/*public void test()
	{
		searchEngineIPAddressDAO.getAll();
	}*/
	
	public void testGetAllSearchEngineIPAddresses()
	{
		List<String> list = searchEngineIPAddressDAO.getAllSearchEngineIPAddresses();
		
		System.out.println(list.size());
		
		for(String str : list)
			System.out.println(str);
	}

}
