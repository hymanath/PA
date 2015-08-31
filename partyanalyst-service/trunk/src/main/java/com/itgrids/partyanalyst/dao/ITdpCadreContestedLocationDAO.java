package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreContestedLocation;

public interface ITdpCadreContestedLocationDAO extends GenericDao<TdpCadreContestedLocation, Long>{
	
	public List<Object[]> getParticipatedCandidateConstituency(Long tdpCadreId);

}
