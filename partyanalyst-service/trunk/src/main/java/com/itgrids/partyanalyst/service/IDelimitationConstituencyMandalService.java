package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CastWiseElectionVotersVO;
import com.itgrids.partyanalyst.dto.DelimitationConstituencyMandalResultVO;
import com.itgrids.partyanalyst.dto.GenderAgeWiseVotersVO;
import com.itgrids.partyanalyst.dto.MandalInfoVO;
import com.itgrids.partyanalyst.dto.PartyElectionVotersHeaderDataVO;
import com.itgrids.partyanalyst.dto.VillageDetailsVO;

public interface IDelimitationConstituencyMandalService {
	public DelimitationConstituencyMandalResultVO getMandalsForDelConstituency(Long constituencyID);
	public List<MandalInfoVO> getCensusInfoForMandals(String mandalIDs);
	public VillageDetailsVO getVillagesFormMandal(Long mandalID);
	public PartyElectionVotersHeaderDataVO getPartyElectionVotersForMandal(Long mandalID, String typeFlag);
	public CastWiseElectionVotersVO findCastWiseVotersForMandal(Long mandalID);
	public GenderAgeWiseVotersVO findGenderAgeWiseVotersForMandal(Long mandalID);
}
