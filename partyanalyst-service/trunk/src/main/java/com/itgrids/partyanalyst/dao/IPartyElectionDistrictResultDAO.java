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

	public List<PartyElectionDistrictResult> getByPartyIdElectionIdAndDistrict(Long electionId,Long partyId,Long stateId,Long districtId);
	
	@SuppressWarnings("unchecked")
	public List getParticipatedPartysCountForAnElection(Long electionId);
	
	public List<PartyElectionDistrictResult> getDistrictWiseAllPartiesResults(Long electionId,String votesPercentMargin);
	
	public List<PartyElectionDistrictResult> getPartyElecResultsInAllDistsForAParty(Long electionId,Long partyId);
}
