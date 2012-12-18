package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISpecialPageHighlightsDAO;

public class SpecialPageHighlightsDAOHibernateTest extends BaseDaoTestCase{
	
	private ISpecialPageHighlightsDAO specialPageHighlightsDAO;

	
	public void setSpecialPageHighlightsDAO(
			ISpecialPageHighlightsDAO specialPageHighlightsDAO) {
		this.specialPageHighlightsDAO = specialPageHighlightsDAO;
	}
	
	public void test(){
		List<Object[]> list = specialPageHighlightsDAO.getSpecialPageHighLightsBySpecailPageId(13l);
		System.out.println(list.size());
		
	}
	

}
