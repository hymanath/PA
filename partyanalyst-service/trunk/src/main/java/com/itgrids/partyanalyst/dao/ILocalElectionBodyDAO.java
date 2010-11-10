package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.LocalElectionBody;

public interface ILocalElectionBodyDAO extends GenericDao<LocalElectionBody, Long>{

	public List<LocalElectionBody> findByElectionTypeDistrictTehsilAndLEBName(Long electionTypeId, String districtName, String tehsilName,
			String localElecBodyName);
	
	@SuppressWarnings("unchecked")
	public List findByElectionTypeAndState(Long electionTypeId, Long stateId);
	
	@SuppressWarnings("unchecked")
	public List getLocalELectionTypesInAState(Long stateId);
	
	public List findByDistrictId(Long districtId);
	
	public List<LocalElectionBody> findByLocalElectionBodyIds(List<Long> localElectionBodyIds);
	
	public List getStateToLocalElectionBodyByLEB(String localElectionBodyIds);
	
	
}
