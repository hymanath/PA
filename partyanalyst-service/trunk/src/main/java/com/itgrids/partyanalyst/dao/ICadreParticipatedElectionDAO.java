package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreParticipatedElection;

public interface ICadreParticipatedElectionDAO extends GenericDao<CadreParticipatedElection, Long>{
	
	public Integer inActiveCadreElectionDetailsById(Long tdpCadreId);
	
	public List<Object[]> getPreviousParticipationInfoByTdpCadreId(Long tdpCadreId);
	
}
