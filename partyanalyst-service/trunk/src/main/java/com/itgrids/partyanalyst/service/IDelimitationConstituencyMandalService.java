package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.DelimitationConstituencyMandalResultVO;
import com.itgrids.partyanalyst.dto.MandalInfoVO;

public interface IDelimitationConstituencyMandalService {
	public DelimitationConstituencyMandalResultVO getMandalsForDelConstituency(Long constituencyID);
	public List<MandalInfoVO> getCensusInfoForMandals(String mandalIDs);
}
