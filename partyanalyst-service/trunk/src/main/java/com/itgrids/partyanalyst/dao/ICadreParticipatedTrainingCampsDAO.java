package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreParticipatedTrainingCamps;


public interface ICadreParticipatedTrainingCampsDAO extends GenericDao<CadreParticipatedTrainingCamps, Long> {

	@SuppressWarnings("unchecked")
	public List getCadreIdsByCadreCampsAndUser(Long userId,Long campId);
	
	@SuppressWarnings("unchecked")
	public List getCadreByCampsAndCadreIds(Long campId,List<Long> cadreIds);
}
