package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreCandidate;


public interface ITdpCadreCandidateDAO extends GenericDao<TdpCadreCandidate, Long>{
	
	public List<Object[]> getPublicRepresentativeDetailsByCadre(Long cadreId);
	public List<Long> getTdpCadreCandidate(Long cadreId);
	public List<Object[]> getTdpCadreCandidateIds(List<Long> finalCadreIDsList);

}
