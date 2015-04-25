package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PublicRepresentative;

public interface IPublicRepresentativeDAO extends GenericDao<PublicRepresentative, Long> {
	public List<Long> getRepresentativesByPositions(Long representativeLevelId,List<Long> locationValuesList,Long positionId);
	public List<Object[]> getCandidateInfoByCandidateIds(List<Long> candidateIdsList);
}
