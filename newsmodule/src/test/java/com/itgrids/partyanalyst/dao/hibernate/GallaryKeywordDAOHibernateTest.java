package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IGallaryKeywordDAO;


public class GallaryKeywordDAOHibernateTest extends BaseDaoTestCase{
	
	
	private IGallaryKeywordDAO gallaryKeywordDAO;


	public void setGallaryKeywordDAO(IGallaryKeywordDAO gallaryKeywordDAO) {
		this.gallaryKeywordDAO = gallaryKeywordDAO;
	}



	/*public void test()
	{
		GallaryKeywordDAO.getAll();
	}*/
	
	/*public void testgetGallaryKeywords()
	{
		List<String> keywordsList = new ArrayList<String>(0);
		keywordsList.add("type1");
		keywordsList.add("type2");
		List<Object[]> keywordList = gallaryKeywordDAO.getGallaryKeywords(keywordsList);
		System.out.println(keywordList.size());
		for(Object[] params:keywordList)
		 System.out.println(params[0]+" "+params[1]);
	}*/
	
	public void test()
	{
		List<Long> gallaryIds = new ArrayList<Long>();
		gallaryIds.add(3805l);
		Integer val = gallaryKeywordDAO.deleteGallaries(17l,1l,gallaryIds);
		System.out.println(val);
	}

}


