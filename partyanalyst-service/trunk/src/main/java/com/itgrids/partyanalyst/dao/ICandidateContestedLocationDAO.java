package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidateContestedLocation;

public interface ICandidateContestedLocationDAO extends GenericDao<CandidateContestedLocation, Long>{

	public CandidateContestedLocation getCandidateContestedLocationDetailsByCadre(Long cadreId);
}
