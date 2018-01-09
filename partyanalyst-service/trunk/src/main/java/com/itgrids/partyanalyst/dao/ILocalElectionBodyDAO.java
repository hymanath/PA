package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

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
	
	@SuppressWarnings("unchecked")
	public List getCountOfLocalBodysForALocalElectionBodyType(Long electionTypeId);
	
	@SuppressWarnings("unchecked")
	public List getLocalElectionBodyIdByNameAndDistrictId(String localBodyName,Long districtId);
	
	public List<String> getLocalElectionBodyNameById(Long localElectionBodyId);
	public List<Long> getMuncipalitiesAndCorporationsForConstituency(List<Long> tehsilIds);
	
	public String getLocationTypeForLocalEleBodyByLocalEleBodyId(Long localEleBodyId);
	
	public List<Object[]> findByLocalElecBodyIds(List<Long> lclElecBodyIds);
	
	public String getMuncipalityNameById(Long muncipalityId);
	
	public List<Object[]>  getLocationTypeForLocalEleBodyByLocalEleBodyId(List<Long> localEleBodyIds);
	
	public List<Object[]> getTehsilsByLocalBody(Long localBodyId);
	
	public String getLocalElectionBodyName(Long localElectionBodyId);
	
	public List<Object[]> getLocationElectionBodyList();
	
	public List<Object[]> getLocalElectionBodyType(Set<Long> localElectionBodyIds);
	
	public String getLocalElectionBodyName1(Long localElectionBodyId);
	
	public List<Object[]> getLocalElectionBodyNames(List<Long> localElectionBodyIds);	
	 public String getLocalElectionBodyNameNew(Long localElectionBodyId);
	 
	 public List<Object[]> getMuncipalitiesAndCorporationsInAConstituency(List<Long> tehsilIds);
	 public List findByElectionTypeAndState1(Long electionTypeId, Long stateId);
	 public LocalElectionBody getLocalElectionBodyDetailsByLevel(Long localElectionBodyId);
	 public List<Object[]> getAllLocalBodysByState(Long stateId);
	 public List getLocalEleForDistrict(Long districtId);
	 public List getCorporationsForDistrict(Long districtId);
	 public List getGMCsForDistrict(Long districtId);
	 public List<Object[]> getAllLocalElectionBodyList(Long stateId);
	 public List<Object[]> getAllLocalElectionBodyListByState();
	 
	 public List<Long> getLocalElectionBodyIdsByDistrictIdsList(List<Long> districtIdsList);
	 public List<LocalElectionBody> getLocalElectionBodyByDistrictId(Long districtId,String localBodyName);
}
