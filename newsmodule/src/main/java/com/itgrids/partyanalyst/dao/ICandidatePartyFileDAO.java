package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidatePartyFile;
import com.itgrids.partyanalyst.model.File;

public interface ICandidatePartyFileDAO extends GenericDao<CandidatePartyFile, Long>{
	
	public List<Object[]> getCandidatesNewsCount();
	
	public List<String> getCandidateNamesByFileId(Long fileId);
	
	public List<Object[]> getCandidateNamesByFileIds(Set<Long> fileIds);
	
	public List<Object[]> getSourceCandidates();
	
	public List<Object[]> getDestinationCandidates();
	
	public List<File> getCandidateFileListByCandidateId(Long candidateId,Integer firstResult,Integer maxResult,String queryType, Date fromDate, Date toDate);
	
	public List<Long> getFilesIdsByCandidateFileId(List<Long> candidateFileId);
	 
	public List<Object[]> getKeywordsCountByFileIds(Set<Long> fileIds);
	
	public List<Object[]> getSourcePartyCommentsOnly(Long partyId,Long candidateId,Date fromDate,Date toDate);
	
	public List<Object[]> getSourcePartyCandidateComments(Long partyId,Long candidateId,Date fromDate,Date toDate);
	
	public List<Object[]> getSourcePartyComments(Long partyId,Long candidateId,Date fromDate,Date toDate);
	
	public List<Object[]> getDestinationPartyCommentsOnly(Long partyId,Long candidateId,Date fromDate,Date toDate);
	
	public List<Object[]> getDestinationPartyComments(Long partyId,Long candidateId,Date fromDate,Date toDate);
	
	public List<Object[]> getDestinationPartyCandidateComments(Long partyId,Long candidateId,Date fromDate,Date toDate);
	
	public List<Object[]> getSelectedNewsBySearchCriteria(String queryStr ,Date fromDate,Date toDate, Integer startIndex , Integer maxIndex);
	
	public Long getSelectedNewsCountBySearchCriteria(String queryStr,Date fromDate,Date toDate);

	public List<CandidatePartyFile> getInvolvedCandidatesInANews(Long fileId);
	
	//public int deleteCandidatePartyFileById(String type,Long candidatePArtyFileId);
	
	public List<CandidatePartyFile> getdetailsBySourceCandiIdForFile(Long fileId,Long sourceCandId,String type);
	
	public List<Long> getCandidatePartyFileIds(Long fileId);
	
	public void deleteCandidatePartyFiles(Long fileId);

}
