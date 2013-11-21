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
	 
}
