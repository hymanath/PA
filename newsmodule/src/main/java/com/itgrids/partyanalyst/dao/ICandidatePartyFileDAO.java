package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidatePartyFile;

public interface ICandidatePartyFileDAO extends GenericDao<CandidatePartyFile, Long>{
	public List<Object[]> getCandidatesNewsCount();
	public List<String> getCandidateNamesByFileId(Long fileId);
	public List<Object[]> getCandidateNamesByFileIds(Set<Long> fileIds);
}
