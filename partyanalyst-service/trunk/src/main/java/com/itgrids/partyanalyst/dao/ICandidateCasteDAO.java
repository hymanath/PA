package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidateCaste;

public interface ICandidateCasteDAO extends GenericDao<CandidateCaste,Long> {
	
	public List <CandidateCaste> getCandidatesCasteDetails(Long candidateCasteId);
	public List<CandidateCaste> getCandidateCasteDetails1(Long candidateId);
}
