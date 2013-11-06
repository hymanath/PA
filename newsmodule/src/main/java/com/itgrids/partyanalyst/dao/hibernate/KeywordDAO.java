package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IKeywordDAO;
import com.itgrids.partyanalyst.model.Keyword;

public class KeywordDAO extends GenericDaoHibernate<Keyword, Long> implements IKeywordDAO{

	public KeywordDAO() {
		super(Keyword.class);
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalKeyWords()
	{
		Query query = getSession().createQuery(" select model.keywordId,model.type from Keyword model order by model.type ");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getKeyWordsList(List<String> keywordList)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select model.keywordId,model.type from Keyword model where model.type in (:keywordList) ");
	  Query query = getSession().createQuery(str.toString());
	  query.setParameterList("keywordList", keywordList);
	  return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getKeyWordIdByName(String keywordName)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select model.keywordId,model.type from Keyword model where model.type =:keywordName ");
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("keywordName", keywordName);
	  return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getExistingKeywordsByKeywordsList(List<String> keywordList)
	{
		Query query = getSession().createQuery(" select model.type from Keyword model where model.type in(:keywordList) ");
		query.setParameterList("keywordList", keywordList);
		return query.list();
	}

}
