package com.itgrids.partyanalyst.service;

import java.util.List;


import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CasteWiseResultVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IAcPcWiseElectionResultService 
{

	public List<BasicVO> getPartyWiseComperassionResult(Long stateId,Long electionId,List<Long> partyIds,Long electionScopeId,String scope);
	
	public List<SelectOptionVO> cbnOrModiEffect(Long electionId,Long stateid,List<Long> partyId,Long electionScopeId);
	
	public List<GenericVO> cbnEffectCalucation();
	
	public List<BasicVO> filterToGetPartyWiseComperassionResult(Long stateId,Long electionId,List<Long> partyIds,Long electionScopeId,String scope,List<Long> subRegionId);
	
	public List<BasicVO> searchPartyWiseComparissionResult(Long stateId,Long electionId,List<Long> partyIds,Long electionScopeId,String scope,List<Long> subRegionId,String searchName);

	public List<CasteWiseResultVO> getCasteWiseDataForElection(Long electionId);
	
	public List<com.itgrids.survey.soa.endpoints.GenericVO> getGenderWiseSurveyReport(Long partyId,Long constituencyId,List<Long> surveyIds);
}
