/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.LocalBodyElectionResultsVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultsInConstituencyVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;

/*
 * Interface for Local Body Elections 
 */
public interface ILocalBodyElectionService {/*

	public LocalBodyElectionResultsVO getLocalBodyElectionResultsByLocalBodyTypeAndYear(Long localBodyId,Long stateId);
	
	public List<PartyElectionResultsInConstituencyVO> getLocalBodyElectionResultsInAnElection(Long localBodyId,Long stateId,Long electionId);
	
	public List<PartyElectionResultsInConstituencyVO> getLocalBodyElectionResultsForAPartyInAnElection(Long localBodyId,Long stateId,Long electionId,Long partyId);
	
	public List<PartyElectionResultsInConstituencyVO> getLocalBodyElectionResultsForAWardInAnElection(Long localBodyId, Long stateId, Long electionId, Long wardId);
	
	public TeshilPartyInfoVO getMuncipalOrCorporationElectionsResultsForAnAssembly(Long electionId, Long constituencyId);
	
	public ConstituencyVO findConstituencywiseGreaterElectionResults(Long electionId, Long constituencyId,Long partyId,Long wardId);
	
	public List<SelectOptionVO> getLocalBodyElectionsList(Object localBody,Long stateId,List<Long> constituencyIds) throws Exception;
	
	public NavigationVO getLatestGHMCElectionIdAndLatestElectionYear(String electionType);
			
	public NavigationVO getLocalBodyElectionIdsForAConstituency(Long constituencyId,String electionType);
	
	public TeshilPartyInfoVO getMuncipalOrCorporationElectionsResultsForAnAssembly(Long electionId, List<Long> constituencyIds);
*/}
