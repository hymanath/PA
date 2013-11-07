package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGallaryKeywordDAO;
import com.itgrids.partyanalyst.model.GallaryKeyword;


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

}
