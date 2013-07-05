package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyGalleryDAO;


public class PartyGalleryDAOHibernateTest extends BaseDaoTestCase {

	private IPartyGalleryDAO partyGalleryDAO;

	public void setPartyGalleryDAO(IPartyGalleryDAO partyGalleryDAO) {
		this.partyGalleryDAO = partyGalleryDAO;
	}

	public void testGetGallariesByPartyId(){
		
		List<Object[]> items =partyGalleryDAO.getGallariesByPartyId(872L,"VideoGallary");
		System.out.println(items.size());
	}
	
}
