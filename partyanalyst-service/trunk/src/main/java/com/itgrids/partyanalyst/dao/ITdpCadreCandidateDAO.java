package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreCandidate;


public interface ITdpCadreCandidateDAO extends GenericDao<TdpCadreCandidate, Long>{
	
	public Object[] getPublicRepresentativeDetailsByCadre(Long cadreId);

}
