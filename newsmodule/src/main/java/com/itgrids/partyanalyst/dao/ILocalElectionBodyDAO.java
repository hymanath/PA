package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.LocalElectionBody;

public interface ILocalElectionBodyDAO extends GenericDao<LocalElectionBody, Long>{/*

	public List<LocalElectionBody> findByElectionTypeDistrictTehsilAndLEBName(Long electionTypeId, String districtName, String tehsilName,
			String localElecBodyName);
	
	@SuppressWarnings("unchecked")
	
	
	@SuppressWarnings("unchecked")
	public List getLocalELectionTypesInAState(Long stateId);
	*/
	public List findByDistrictId(Long districtId);
	public List findByElectionTypeAndState(Long electionTypeId, Long stateId);
	/*public List<LocalElectionBody> findByLocalElectionBodyIds(List<Long> localElectionBodyIds);
	
	public List getStateToLocalElectionBodyByLEB(String localElectionBodyIds);
	
	@SuppressWarnings("unchecked")
	public List getCountOfLocalBodysForALocalElectionBodyType(Long electionTypeId);
	
	@SuppressWarnings("unchecked")
	public List getLocalElectionBodyIdByNameAndDistrictId(String localBodyName,Long districtId);
	
	public List<String> getLocalElectionBodyNameById(Long localElectionBodyId);
	public List<Long> getMuncipalitiesAndCorporationsForConstituency(List<Long> tehsilIds);
	
	public String getLocationTypeForLocalEleBodyByLocalEleBodyId(Long localEleBodyId);
	
	public List<Object[]> findByLocalElecBodyIds(List<Long> lclElecBodyIds);
	
	public String getMuncipalityNameById(Long muncipalityId);
	
*/}
