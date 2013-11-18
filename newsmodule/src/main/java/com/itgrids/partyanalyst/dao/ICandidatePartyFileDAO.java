package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidatePartyFile;

public interface ICandidatePartyFileDAO extends GenericDao<CandidatePartyFile, Long>{
	public List<String> getCandidateNamesByFileId(Long fileId);
}
