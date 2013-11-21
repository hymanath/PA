package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidatePartyKeyword;

public interface ICandidatePartyKeywordDAO extends GenericDao<CandidatePartyKeyword, Long>{
	
	public List<CandidatePartyKeyword> getCandidatePartyKeywordList(Long keywordId);
	
	public CandidatePartyKeyword getCandidateFileDetails(Long candidateFileId,Long newKeywordID);
	
	public Long removeDublicateData(Long candidatePartyKeywordId,Long keywordId);
	
	public Long removeKeywordsList(Long keywordId);
	
	public List<CandidatePartyKeyword> getCandidatePartyKeywordListByUserwise(Long candidatePartyFileId,Long keywordId);
	
	public List<Long> getCandidateFileIds(Long keywordId);
}
