package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidateDetails;

public interface ICandidateDetailsDAO extends GenericDao<CandidateDetails, Long>
{

	public List<Object[]> getMptcCandidateDetails(Long electionId);
	public List<Object[]> getZptcCandidateDetails(Long electionId);
	public List<Object[]> getMobileAndPreviousParty(List<Long> candidateIds);
}
