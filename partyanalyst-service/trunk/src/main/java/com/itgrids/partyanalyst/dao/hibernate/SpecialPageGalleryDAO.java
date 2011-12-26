package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISpecialPageGalleryDAO;
import com.itgrids.partyanalyst.model.SpecialPageGallery;

public class SpecialPageGalleryDAO extends GenericDaoHibernate<SpecialPageGallery,Long> implements ISpecialPageGalleryDAO{

	public SpecialPageGalleryDAO(){
		super(SpecialPageGallery.class);
	}
	
}
