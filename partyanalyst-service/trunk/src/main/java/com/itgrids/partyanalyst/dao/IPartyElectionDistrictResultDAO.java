/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 09,2010
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyElectionDistrictResult;

public interface IPartyElectionDistrictResultDAO extends GenericDao<PartyElectionDistrictResult, Long> {

	//public List<PartyElectionDistrictResult> getByPartyIdElectionIdAndDistrict(Long electionId,Long partyId,Long stateId,Long districtId);
	public List<PartyElectionDistrictResult> getByPartyIdElectionIdAndDistrict(Long electionId, Long partyId, Long districtId);
	
	public List getAllParyDetailsForAllElectionYearsForADistrict(String electionId, String partyId, Long districtId);
	
	@SuppressWarnings("unchecked")
	public List getParticipatedPartysCountForAnElection(Long electionId);
	
	public List<PartyElectionDistrictResult> getDistrictWiseAllPartiesResults(Long electionId,String votesPercentMargin);
	
	public List<PartyElectionDistrictResult> getPartyElecResultsInAllDistsForAParty(Long electionId,Long partyId);
	
	public List getPartiesPositionsInDistrictInElection(Long electionId, Long districtId, String votesPercentMargin);
	
	public List getAllElectionResultsInDistrictForElectionType(Long electionTypeId, Long districtId);
	
	public List findDistrictWiseElectionResultsForStatePartyAndElection(Long partyId, Long stateId, Long electionId);

	public List getAllElectionResultsInDistrict(Long electionTypeId, Long districtId);
	
	@SuppressWarnings("unchecked")
	public List findPartyResultsForARegionInState(List<Long> districtIds,Long electionId, List<Long> partyIds);
	public List<PartyElectionDistrictResult> getParticipatedPartiesDetailsInDistricts(Long electionId,List districtIdList);

	public List<Object[]> findDistrictWiseCompleteValidVotesInARegionInState(List<Long> districtIds,Long electionId);
	
	public List<Long> getAllPartispatedPartiesInaAnElection(Long electionId, List<Long> partyIds);
}
