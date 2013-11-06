package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyGalleryDAO;


public class PartyGalleryDAOHibernateTest extends BaseDaoTestCase {

	private IPartyGalleryDAO partyGalleryDAO;

	public void setPartyGalleryDAO(IPartyGalleryDAO partyGalleryDAO) {
		this.partyGalleryDAO = partyGalleryDAO;
	}

	/*public void testGetGallariesByPartyId(){
		
		List<Object[]> items =partyGalleryDAO.getGallariesByPartyId(872L,"VideoGallary");
		System.out.println(items.size());
		
		List<Object[]> items1 = partyGalleryDAO.getAllNewsDetailsForState(872l, 0, 100, "", 1l, 2l);
	}*/
	
	/*public void testGallerisCount(){
		List<Long> gallaryIds=new ArrayList<Long>();
		gallaryIds.add(1l);
		gallaryIds.add(24l);
		gallaryIds.add(25l);
		gallaryIds.add(26l);
		gallaryIds.add(31l);
		
		List<Object[]> items =partyGalleryDAO.getRespondedFilesCount(gallaryIds,3l);
				System.out.println(items);
		
	}*/
	
	/*public void testgetTotalCategories()
	{
		List<Object[]> list = partyGalleryDAO.getTotalCategories(872l, "");
		System.out.println(list.size());
		for(Object[] params :list)
		 System.out.println(params[0]+" "+params[1]+" "+params[2]);
	}*/
	
	public void testgetTotalKeywordsForKeywordSearch()
	{
		List<String> keywordsList = partyGalleryDAO.getTotalKeywordsForKeywordSearch(872L, "");
		System.out.println(keywordsList.size());
		for(String keyword :keywordsList)
		 System.out.println(keyword);
	}
	
}
