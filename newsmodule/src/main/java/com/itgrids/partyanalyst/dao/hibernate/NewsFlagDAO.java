package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INewsFlagDAO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;
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

	/**
	 * This Dao Method Is Used For Getting All Flaged News
	 * @param List<Long> contentIds
	 * @return List<FileGallary>
	 * @date 01-04-2013
	 */
	public List<FileGallary> getFlagedMews(List<Long> contentIds)
	{
		Query query = getSession().createQuery("select DISTINCT model.fileGallary from NewsFlag model " +
				"where model.fileGallary.fileGallaryId in (:contentIds)");
		query.setParameterList("contentIds", contentIds);
		return query.list();
	}

	/**
	 * this DAO is used for getting count of all flagged news
	 * @param FileVO 
	 * @param List<Long> 
	 * @return Long
	 * @date 03-04-2013
	 */
	public Long getFlaggedNewsCount(FileVO inputs,List<Long> contentIds) {
		String queryString = ("select count(model.newsFlagId) from NewsFlag model where " +
				"model.fileGallary.fileGallaryId in(:contentIds) and " +
				"date(model.fileGallary.file.fileDate) >= :fromDate and" +
				" date(model.fileGallary.file.fileDate) <= :toDate");	
		Query query = getSession().createQuery(queryString.toString());
		query.setParameterList("contentIds", contentIds);
		query.setDate("fromDate", inputs.getExistingFrom());
		query.setDate("toDate", inputs.getIdentifiedOn());
		return (Long) query.uniqueResult();
	}

}
