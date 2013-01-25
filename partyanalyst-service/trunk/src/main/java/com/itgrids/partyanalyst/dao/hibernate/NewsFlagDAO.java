package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INewsFlagDAO;
import com.itgrids.partyanalyst.model.NewsFlag;

public class NewsFlagDAO extends GenericDaoHibernate<NewsFlag, Long> implements INewsFlagDAO {

	public NewsFlagDAO(){
		super(NewsFlag.class);
	}
	
	
	public List<Object[]> getCountForFlagByFileGallaryId(List<Long> contentIds){
		
		Query query = getSession().createQuery("select model.fileGallary.fileGallaryId,model.newsFlagId from NewsFlag model where " +
				"model.fileGallary.fileGallaryId in (:contentIds)");
		
		query.setParameterList("contentIds", contentIds);
		
		return query.list();
		
		
	}
	
	
	public void removeFlagForNews(Long contentId){
		
		Query query = getSession().createQuery("delete from NewsFlag model where model.fileGallary.fileGallaryId = ?");
		
		query.setParameter(0, contentId);
		
		query.executeUpdate();
		
		
	}
	
	public List<Long> getCountForFlagByContentId(Long contentId){
		
		Query query = getSession().createQuery("select count(model.newsFlagId) from NewsFlag model " +
				"where model.fileGallary.fileGallaryId = ?");
		
		query.setParameter(0, contentId);
		
		return query.list();
		
	}

}
