package com.itgrids.partyanalyst.service;

import java.io.Serializable;
import java.util.List;

import com.itgrids.partyanalyst.dto.ConstituencyElectionResults;
import com.itgrids.partyanalyst.dto.ElectionInfoVO;
import com.itgrids.partyanalyst.dto.PartiesDetailsVO;
import com.itgrids.partyanalyst.dto.PartiesStrenghInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IPartyStrengthService extends Serializable {

	public PartiesStrenghInfoVO segregateAllConstituencies(Long selectedNoOfYears,String electionType,String electionSubType,Long stateId);
	
	public List<Long> getAllElectionYears(String electionType,Long stateId);
	
	public ElectionInfoVO getPartiesData(String electionType,Long stateId,Long electionYearsCount,Long partyId);
	
	public ConstituencyElectionResults getAllElectionData(List<Long> constituencyIds,List result);
	
	public ConstituencyElectionResults setElectionDataInToVo(List<Long> constituencyIds,List result);
	
	public PartiesDetailsVO setDataIntoPartiesDetailsVO(Object[] parms);
	
	public List<SelectOptionVO> getAllStatesHavinElectionData(String electionType);
	
	public List<Long> getCountOfElectionYears(Long stateId,String electionType);
}
