package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidateRealatedNews;
import com.itgrids.partyanalyst.model.FileGallary;

public interface ICandidateRelatedNewsDAO extends GenericDao<CandidateRealatedNews, Long>{
	public List<Object[]> getAllfileGallariesOfCandidate(Long candidateId  ,Date fromDate  ,Date toDate);
	public List<Object[]> getCandidates();
	public List<Object[]> getCandidatesContainsNews();
	
	public List<FileGallary> getFileGallaryListByCandidateId(Long candidateId,Integer firstResult,Integer maxResult,String queryType, Date fromDate, Date toDate,List<Long> gallaryIdsList,List<Long> categoryIdsList);
	
	public List<Object[]> getCandidatesWithCount();

}
