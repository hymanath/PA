package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidateRealatedNews;

public interface ICandidateRelatedNewsDAO extends GenericDao<CandidateRealatedNews, Long>{
	public List<Object[]> getAllfileGallariesOfCandidate(Long candidateId  ,Date fromDate  ,Date toDate);
	public List<Object[]> getCandidates();
	public List<Object[]> getCandidatesContainsNews();

}
