/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Jun 10, 2010
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BiElectionDistrictVO;
import com.itgrids.partyanalyst.dto.BiElectionResultsVO;
import com.itgrids.partyanalyst.dto.ElectionResultsForMandalVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionResultsVO;
import com.itgrids.partyanalyst.dto.MandalElectionResultVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IBiElectionPageService {

	public MandalAllElectionResultsVO getAllPartiesElectionResultsInAMandal(Long mandalId);
	
	public List<BiElectionDistrictVO> getBiElectionConstituenciesDistrictWise();
	
	public List<MandalAllElectionResultsVO> getAllPartysElectionResultsInAConstituencyMandalWise(Long constituencyId);
	
	public MandalElectionResultVO getElectionResultForPartyInMandal(Long mandalId,String electionType,String electionYear,List<SelectOptionVO> parties);
	
	public ElectionResultsForMandalVO getElectionResultsForMandalsInAConstituencyForAElection(List<Long> mandalIds,String electionType,String electionYear,List<SelectOptionVO> parties);
	
	public List<BiElectionResultsVO> getMandalWiseResultsForAConstituency(Long constituencyId);
}
