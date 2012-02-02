package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.itgrids.partyanalyst.dao.ISpecialPageGalleryDAO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.SpecialPageGallery;

public class SpecialPageGalleryDAO extends GenericDaoHibernate<SpecialPageGallery,Long> implements ISpecialPageGalleryDAO{

	public SpecialPageGalleryDAO(){
		super(SpecialPageGallery.class);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getSpecialPageGallaryDetails(Long specialPageId,String contentType) {
		
		Query query = getSession().createQuery("select model.gallary.gallaryId , model.gallary.name , model.gallary.description " +
				" from SpecialPageGallery model where model.gallary.contentType.contentType =:contentType and model.specialPage.specialPageId =:specialPageId order by model.createdDate desc");
		query.setParameter("specialPageId", specialPageId);
		query.setParameter("contentType", contentType);
		
		return query.list();
	}

	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSpecialPageGallaryDetail(Long specialPageId,
			int firstRecord, int maxRecord, String type) {
		
		Query query = getSession().createQuery("select model.gallary.gallaryId , model.gallary.name,model.gallary.description,model.gallary.createdDate," +
				"model.gallary.updateddate from SpecialPageGallery model where model.gallary.contentType.contentType =:type and model.gallary.isDelete =:isDelete " +
				"and model.gallary.isPrivate =:isPrivate and model.specialPage.specialPageId =:specialPageId order by model.createdDate desc");
		
		query.setParameter("specialPageId", specialPageId);
		query.setParameter("type", type);
		query.setParameter("isDelete", "false");
		query.setParameter("isPrivate", "false");
		query.setFirstResult(firstRecord);
		query.setMaxResults(maxRecord);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<File> getGalleryBasedOnSpecialPageId(Long specialPageId,int firstRecord, int maxRecord, String contentType){
		
		
		
		Query query = getSession().createQuery("select model.file from FileGallary model where model.gallary.gallaryId in (select model1.gallary.gallaryId from SpecialPageGallery model1" +
				" where model1.gallary.contentType.contentType =:contentType and model1.gallary.isDelete =:isDelete " +
				" and model1.gallary.isPrivate =:isPrivate and model1.specialPage.specialPageId =:specialPageId order by model.file.fileDate,model.createdDate desc)");
		
		query.setParameter("specialPageId", specialPageId);
		query.setParameter("contentType", contentType);
		query.setParameter("isDelete", "false");
		query.setParameter("isPrivate", "false");
		query.setFirstResult(firstRecord);
		query.setMaxResults(maxRecord);
		return query.list();
		
	}
	
	
	public List<Object[]> getGallariesBySpecialPageId(Long specialPageId, String contentType){
		Object[] params = {specialPageId , contentType};
		return getHibernateTemplate().find("select model.gallary.gallaryId , model.gallary.name from SpecialPageGallery model where model.specialPage.specialPageId=? and model.gallary.contentType.contentType=? and model.isDelect='false' order by model.gallary.name asc",params);
	}
}
