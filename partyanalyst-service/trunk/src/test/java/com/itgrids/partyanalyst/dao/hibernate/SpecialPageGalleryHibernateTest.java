package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISpecialPageGalleryDAO;

public class SpecialPageGalleryHibernateTest extends BaseDaoTestCase {
	ISpecialPageGalleryDAO specialPageGalleryDAO;

	public void setSpecialPageGalleryDAO(
			ISpecialPageGalleryDAO specialPageGalleryDAO) {
		this.specialPageGalleryDAO = specialPageGalleryDAO;
	}

	/*public void test() {
		specialPageGalleryDAO.getAll();
	}*/

	public void testgetGallariesBySpecialPageId() {
		List<Object[]> list = specialPageGalleryDAO
				.getGallariesBySpecialPageId(1l, "videoGallary");
		for (Object[] params : list) {
			System.out.println(params[0]);
			System.out.println(params[1].toString());

		}
	}
}
