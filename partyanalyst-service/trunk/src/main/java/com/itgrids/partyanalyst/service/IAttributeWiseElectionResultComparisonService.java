package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.PartyResultsVO;

public interface IAttributeWiseElectionResultComparisonService {
	public PartyResultsVO getElectionResultsByAttributeWise(List<Long> electionIds,List<Long> partyIds,Long userId,Long constituencyId,String type,List<Long> ids,String attributeType,List<Long> attributeIds,Long attrPerc,Long publicationId);
}
