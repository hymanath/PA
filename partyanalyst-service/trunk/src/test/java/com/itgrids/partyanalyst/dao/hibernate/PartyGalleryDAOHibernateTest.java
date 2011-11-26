package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyGalleryDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartyGalleryDAOHibernateTest extends BaseDaoTestCase {

	private IPartyGalleryDAO partyGalleryDAO;

	public void setPartyGalleryDAO(IPartyGalleryDAO partyGalleryDAO) {
		this.partyGalleryDAO = partyGalleryDAO;
	}

	/*
	 * public void test() { partyGalleryDAO.getAll(); }
	 */

	// public List<Object[]> getPartyGallaryDetail(Long partyId,int
	// firstResult,int maxResult,String type)
	public void testGetPartyGallaryDetail() {
		String type = IConstants.PHOTO_GALLARY;
		List<Object[]> result = partyGalleryDAO.getPartyGallaryDetail(163l, 0,
				20, type);
		System.out.println(result.size());
		for (Object[] objects : result) {
        System.out.println((Long)objects[0]);
        System.out.println(objects[1].toString());
		}
	}
}
