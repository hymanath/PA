package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreParticipatedElection;

public interface ICadreParticipatedElectionDAO extends GenericDao<CadreParticipatedElection, Long>{
	
	public Integer inActiveCadreElectionDetailsById(List<Long> tdpCadreIdList);
	
	public List<Object[]> getPreviousParticipationInfoByTdpCadreId(Long tdpCadreId);
	
}
