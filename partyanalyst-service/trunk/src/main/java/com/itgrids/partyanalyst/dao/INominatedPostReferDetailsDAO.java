package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPostReferDetails;

public interface INominatedPostReferDetailsDAO extends GenericDao<NominatedPostReferDetails, Long>{

	public List<Object[]> getReferedCountForCandidateList(Set<Long> candidateIds);
	public List<Object[]> getReferedCadreDetailsForCandidate(Long candidateId);
	public List<Object[]> getReferedCandidatesCountForCandidate(List<Long> cadreIds);
}
