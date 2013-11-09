package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGallaryKeywordDAO;
import com.itgrids.partyanalyst.model.GallaryKeyword;
import org.hibernate.Query;

public class GallaryKeywordDAO extends GenericDaoHibernate<GallaryKeyword, Long> implements IGallaryKeywordDAO{
	
	public GallaryKeywordDAO() {
		super(GallaryKeyword.class);
		
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getGallaryKeywords(List<String> keywordsList)
	{
	  Query query = getSession().createQuery(" select distinct model.gallary.gallaryId,model.keyword.type,model.keyword.keywordId from GallaryKeyword model " +
	  		" where model.keyword.type in (:keywordsList) ");
	  
	  query.setParameterList("keywordsList", keywordsList);
	  return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getGallaryMappedKeywordsList(List<String> keywordsList)
	{
	  Query query = getSession().createQuery(" select model.keyword.type from GallaryKeyword model where model.keyword.type in (:keywordsList) ");
	  query.setParameterList("keywordsList", keywordsList);
	  return query.list();
	}

	public List<Object[]> getUnAssignedKeyWords(Long userId)
	{
		Query query = getSession().createQuery("select distinct model.keyword.keywordId,model.keyword.type from GallaryKeyword model where model.createdBy = :userId and " +
				"model.gallary.gallaryId =1 ");
		query.setParameter("userId", userId);
		return query.list();
		
		 
	}
	public Integer DeleteKeyWords(List<Long> keyWords,Long userId)
	{
		 Query query = getSession().createQuery("delete from GallaryKeyword model where model.keyword.keywordId in (:keyWords) and model.gallary.gallaryId = 1 and model.createdBy = :userId ");
		 query.setParameterList("keyWords", keyWords);
		 query.setParameter("userId", userId);
		 return query.executeUpdate();
	 }
	
	public List<Object[]> getGallaryMapedKeyWords(Long userId)
	{
		Query query = getSession().createQuery("select distinct model.keyword.keywordId,model.keyword.type from GallaryKeyword model where model.createdBy = :userId and " +
				"model.gallary.gallaryId !=1 ");
		query.setParameter("userId", userId);
		return query.list();
		
		 
	}
	
	public List<Long> getGallaryMapedKeyWords(Long userId,Long keyWord)
	{
		Query query = getSession().createQuery("select model.gallary.gallaryId from GallaryKeyword model where model.createdBy = :userId and " +
				"model.keyword.keywordId = :keyWord");
		query.setParameter("userId", userId);
		query.setParameter("keyWord", keyWord);
		
		return query.list();
		
		 
	}
	
	public Long getGallaryKeywordId(Long keyWord,Long gallaryId)
	{
		Query query = getSession().createQuery("select model.gallaryKeywordId from GallaryKeyword model where model.gallary.gallaryId=:gallaryId and " +
				"model.keyword.keywordId=:keyWord");
		query.setParameter("keyWord", keyWord);
		query.setParameter("gallaryId", gallaryId);
		
		return (Long) query.uniqueResult();
		
		 
	}
	public Integer deleteGallaries(Long keyWord,Long userId,List<Long> gallaryIds)
	{
		 Query query = getSession().createQuery("delete from GallaryKeyword model where model.gallary.gallaryId in (:gallaryIds) and model.keyword.keywordId =:keyWord and model.createdBy = :userId ");
		 query.setParameterList("gallaryIds", gallaryIds);
		 query.setParameter("userId", userId);
		 query.setParameter("keyWord", keyWord);
		 return query.executeUpdate();
	 }

}
