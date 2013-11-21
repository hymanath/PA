package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidatePartyKeywordDAO;
import com.itgrids.partyanalyst.model.CandidatePartyKeyword;

public class CandidatePartyKeywordDAO extends GenericDaoHibernate<CandidatePartyKeyword, Long> implements ICandidatePartyKeywordDAO{

	public CandidatePartyKeywordDAO() {
		super(CandidatePartyKeyword.class);
	}

	@SuppressWarnings("unchecked")
	public List<CandidatePartyKeyword> getCandidatePartyKeywordList(Long keywordId){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select distinct model from CandidatePartyKeyword model where model.keyword.keywordId =:keywordId ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("keywordId", keywordId);		
		return query.list();
	}	
	public CandidatePartyKeyword getCandidateFileDetails(Long candidateFileId,Long newKeywordID){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select distinct model  from CandidatePartyKeyword model where model.candidatePartyFile.candidatePartyFileId = :candidateFileId and model.keyword.keywordId =:newKeywordID ");
		Query query = getSession().createQuery(queryString.toString());
		
		query.setParameter("candidateFileId", candidateFileId);	
		query.setParameter("newKeywordID", newKeywordID);	
		
		return (CandidatePartyKeyword) query.uniqueResult();
	}

	public Long removeDublicateData(Long candidatePartyKeywordId,Long keywordId){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" delete from CandidatePartyKeyword model where model.candidatePartyKeywordId =:candidatePartyKeywordId and model.keyword.keywordId =:keywordId");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("candidatePartyKeywordId", candidatePartyKeywordId);
		query.setParameter("keywordId", keywordId);
		int count = query.executeUpdate();
		//System.out.println(candidatePartyKeywordId + "  candidatePartyKeywordId is deleted : "+count); // returns 1 if deleted otherwise 0
			
		return (Long) query.uniqueResult();
		
	}	

	public Long removeKeywordsList(Long keywordId){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" delete from Keyword model where model.keywordId = :keywordId ");
		Query query = getSession().createQuery(queryString.toString());	
		query.setParameter("keywordId", keywordId);
		int count = query.executeUpdate();
		//System.out.println(keywordId + "  keyword is deleted : "+count); // returns 1 if deleted otherwise 0
			
		return (Long) query.uniqueResult();
		
	}

	@SuppressWarnings("unchecked")
	public List<CandidatePartyKeyword> getCandidatePartyKeywordListByUserwise(Long candidatePartyFileId,Long keywordId){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select distinct model from CandidatePartyKeyword model where model.candidatePartyFile.candidatePartyFileId =:candidatePartyFileId and " +
				"				model.keyword.keywordId =:keywordId ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("candidatePartyFileId", candidatePartyFileId);
		query.setParameter("keywordId", keywordId);
		return query.list();
	}

	public List<Long> getCandidateFileIds(Long keywordId){
		
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select model.candidatePartyFile.candidatePartyFileId from CandidatePartyKeyword model where model.keyword.keywordId =:keywordId ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("keywordId", keywordId);
		return query.list();
		
	}
}
