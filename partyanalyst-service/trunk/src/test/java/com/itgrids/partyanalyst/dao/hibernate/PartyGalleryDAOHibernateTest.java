package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IPartyGalleryDAO;


public class PartyGalleryDAOHibernateTest extends BaseDaoTestCase {
	
	private IPartyGalleryDAO partyGalleryDAO;

	public void setPartyGalleryDAO(IPartyGalleryDAO partyGalleryDAO) {
		this.partyGalleryDAO= partyGalleryDAO;
	}

	public void test() {
		partyGalleryDAO.getAll();
	}
}
