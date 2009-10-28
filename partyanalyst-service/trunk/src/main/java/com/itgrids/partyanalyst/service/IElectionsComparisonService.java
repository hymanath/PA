/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 23, 2009
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ElectionComparisonResultVO;
import com.itgrids.partyanalyst.dto.ElectionsComparisonVO;
import com.itgrids.partyanalyst.dto.PartyResultsPercentageVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IElectionsComparisonService {

	public List<SelectOptionVO> getStatesList();
	
	public List<SelectOptionVO> getPartiesList();
	
	public List<SelectOptionVO> getYearsList();
	
    public boolean IsPartyParticipated(Long electionScopeId,Long partyId,String year);
    
    public List<ElectionComparisonResultVO> getPartyElectionResults(Long electionScopeId,Long partyId,String year);
    
    public ElectionsComparisonVO getPartyElectionComparedResults(Long electionScopeId,Long partyId,String firstYear,String secondYear);
    
    public Long getElectionScopeId(Long electionTypeId,Long stateId);
    
    public PartyResultsPercentageVO getPartyResultsPercentage(Long electionScopeId,Long partyId,String year);
    
}
