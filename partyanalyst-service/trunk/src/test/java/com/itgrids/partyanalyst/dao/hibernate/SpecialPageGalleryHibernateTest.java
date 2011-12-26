package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISpecialPageGalleryDAO;

public class SpecialPageGalleryHibernateTest extends BaseDaoTestCase {
	ISpecialPageGalleryDAO specialPageGalleryDAO;
	
	public void setSpecialPageGalleryDAO(
			ISpecialPageGalleryDAO specialPageGalleryDAO) {
		this.specialPageGalleryDAO = specialPageGalleryDAO;
	}
	
	public void test()
	{
		specialPageGalleryDAO.getAll();
	}

}
