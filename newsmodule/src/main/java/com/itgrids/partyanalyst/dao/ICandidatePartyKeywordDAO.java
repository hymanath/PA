package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

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
	
	public List<File> getAllTheNewsForAUserBasedByUserIdForALocation(String userType,Long userId,Date fromDate,Date toDate,Long regionValue,Long location,List<Long> locationIds,List<Long> keywordIds,Integer startIndex,Integer maxIndex);
	
	public Long getAllTheNewsCountForAUserBasedByUserId(String userType,Long userId,Date fromDate,Date toDate,Long importanceId,Long regionValue,List<Long> keywordIds);
	
	public Long getAllTheNewsForAUserBasedByUserIdForALocationCount(String userType,Long userId,Date fromDate,Date toDate,Long regionValue,Long location,List<Long> locationIds,List<Long> keywordIds);
}
