package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidatePartyKeyword;
import com.itgrids.partyanalyst.model.File;

public interface ICandidatePartyKeywordDAO extends GenericDao<CandidatePartyKeyword, Long>{
	
	public List<CandidatePartyKeyword> getCandidatePartyKeywordList(Long keywordId);
	
	public CandidatePartyKeyword getCandidateFileDetails(Long candidateFileId,Long newKeywordID);
	
	public Long removeDublicateData(Long candidatePartyKeywordId,Long keywordId);
	
	public Long removeKeywordsList(Long keywordId);
	
	public List<CandidatePartyKeyword> getCandidatePartyKeywordListByUserwise(Long candidatePartyFileId,Long keywordId);
	
	public List<Long> getCandidateFileIds(Long keywordId);
	
	public List<File> getAllTheNewsForAUserBasedByUserId(String userType,Long userId,Date fromDate,Date toDate,Long importanceId,Long regionValue,List<Long> keywordIds,Integer startIndex,Integer maxIndex);
	
	public List<File> getAllTheNewsForAUserBasedByUserIdForALocation(String userType,Long userId,Date fromDate,Date toDate,Long regionValue,Long location,List<Long> locationIds,List<Long> keywordIds,Long importanceId,Integer startIndex,Integer maxIndex);
	
	public Long getAllTheNewsCountForAUserBasedByUserId(String userType,Long userId,Date fromDate,Date toDate,Long importanceId,Long regionValue,List<Long> keywordIds);
	
	public Long getAllTheNewsForAUserBasedByUserIdForALocationCount(String userType,Long userId,Date fromDate,Date toDate,Long regionValue,Long location,List<Long> locationIds,List<Long> keywordIds,Long importanceId);
	
	public List<String> getCandidatePartyKeywordByFileId(Long fileId);

	public List<Object[]> getTotalKeyWordsCount();
	
	public List<Object[]> getKeyWords(List<Long> fileIds);
	
	public List<File> getFileListByCandidateId(Long candidateId,Integer firstResult,Integer maxResult,String queryType, Date fromDate, Date toDate,List<Long> keywordIdsList);
	
	public List<Object[]> getCandidateRelatedkeywords(Long candidateId,Date fromDate, Date toDate,String newsType);
	
	public List<Object[]> getCandidatePartyKeywordsByFileIds(Long candidatePartyFileId);
	
	public void deleteCandidatePartyKeywords(List<Long> candidatePartyFileIds);
	
	public List<Object[]> getKeywordsByFileIds(Set<Long> fileIds);
	
	public List<String> getExistingKeywords(List<String> keywords,List<Long> candidatePartyFileIds);
	
	public List<Object[]> getAllExistingKeywords(List<String> keywords,List<Long> candidatePartyFileIds);
}
