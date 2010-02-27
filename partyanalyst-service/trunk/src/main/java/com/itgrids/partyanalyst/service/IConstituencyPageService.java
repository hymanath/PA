/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 24, 2009
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.HamletAndBoothVO;
import com.itgrids.partyanalyst.dto.MandalTownshipWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.ResultWithExceptionVO;
import com.itgrids.partyanalyst.dto.TownshipPartyResultsVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.dto.VotersWithDelimitationInfoVO;

public interface IConstituencyPageService {

	public List<ConstituencyElectionResultsVO> getConstituencyElectionResults(Long constituencyId);
	  
	public ConstituencyInfoVO getConstituencyDetails(Long constituencyId);
	
	public ResultWithExceptionVO getAllMandalLevelLeaders(Long tehsilId);
	
	public ResultWithExceptionVO getTownshipWiseBoothDetailsForTehsil(Long tehsilId);
	
	public List<VotersWithDelimitationInfoVO> getVotersInfoInMandalsForConstituency(Long constituencyId);
	
	public ResultWithExceptionVO saveAndUpdateHamletAndBoothInfo(HamletAndBoothVO hamletWithBoothIds);
	
	public ResultWithExceptionVO deleteVillageBoothElectionRecord(HamletAndBoothVO villageBoothElectionId);
	
	public MandalTownshipWiseBoothDetailsVO getTownshipVotesByTehsil(Long electionID, Long tehsilID, Long partyID);
	
	public List<TownshipPartyResultsVO> getTownshipPartyResults(Long townshipID, Long electionID);
}
