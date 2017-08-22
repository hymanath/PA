package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominationPostCandidate;

public interface INominationPostCandidateDAO extends GenericDao<NominationPostCandidate, Long>{

	public List<Object[]>  notCadresearch(String searchType,String searchValue);
	public List<Object[]> getNotCadreDetailsById(Long nominatedCandiId);
	public NominationPostCandidate getUserAddressByCandidate(Long postCandidateId);
	public List<Object[]> getNOminatedCadreList(List<Long> cadreIdsLsit);
	public List<Object[]> getCastGroupList();
	public List<Object[]> getLevelName(String levelType,Long tdpCadreId,String searchType,Long nominateCandId);
	public List<Long> getCandidateByVoterId(Long voterId);
	public List<Object[]>  updateCadresearch(String searchType,String searchValue);
	public List<Long> getNominatedPostCondidates(Long tdpCadreId);
	public List<Long> getNominatedPstCandidateIds(List<Long> tdpCadreIds);
}
