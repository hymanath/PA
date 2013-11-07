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
	
	public List<Object[]> getGallaryMapedKeyWords(Long userId,List<Long> keyWords)
	{
		Query query = getSession().createQuery("select model.gallary.gallaryId,model.keyword.keywordId from GallaryKeyword model where model.createdBy = :userId and " +
				"model.keyword.keywordId in(:keyWords)");
		query.setParameter("userId", userId);
		query.setParameterList("keyWords", keyWords);
		
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
	/*public List<Object[]> getUnAssignedKeyWords(Long userId)
	{
		Query query = getSession().createQuery("select distinct k.keywordId,k.type from Keyword k,GallaryKeyword gk where k.createdBy = :userId and " +
				"k.keywordId not in(select gk1.keyword.keywordId from GallaryKeyword gk1 where gk1.createdBy = :userId) and k.createdBy = gk.keyword.createdBy");
		query.setParameter("userId", userId);
		return query.list();
		
		
	}*/
	

}
