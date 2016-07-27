package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominationPostCandidate;

public interface INominationPostCandidateDAO extends GenericDao<NominationPostCandidate, Long>{

	public List<Object[]>  notCadresearch(String searchType,String searchValue);
	public List<Object[]> getNotCadreDetailsById(Long nominatedCandiId);
	public NominationPostCandidate getUserAddressByCandidate(Long postCandidateId);
}
