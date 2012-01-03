package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.itgrids.partyanalyst.dao.ISpecialPageGalleryDAO;
import com.itgrids.partyanalyst.model.SpecialPageGallery;

public class SpecialPageGalleryDAO extends GenericDaoHibernate<SpecialPageGallery,Long> implements ISpecialPageGalleryDAO{

	public SpecialPageGalleryDAO(){
		super(SpecialPageGallery.class);
	}
	
	public List<Object[]> getGallariesBySpecialPageId(Long specialPageId, String contentType){
		Object[] params = {specialPageId , contentType};
		return getHibernateTemplate().find("select model.gallary.gallaryId , model.gallary.name from SpecialPageGallery model where model.specialPage.specialPageId=? and model.gallary.contentType.contentType=? and model.isDelect='false' order by model.gallary.name asc",params);
	}
	
}
