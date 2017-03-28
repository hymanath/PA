package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidateProfileDescription;

public interface ICandidateProfileDescriptionDAO extends
		GenericDao<CandidateProfileDescription, Long> {

	public List<Object> getCandidateProfileDescription(Long candidateId);
	
	public List<Object> getMaxOrderNo(Long candidateId);
	
	public List<Object[]> getCandidateProfileInfo(Long candidateId);
	
	public Integer deleteCandidateProfileDescriptionById(Long profDescId) ;
}
