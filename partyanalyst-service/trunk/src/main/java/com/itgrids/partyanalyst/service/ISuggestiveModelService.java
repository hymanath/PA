package com.itgrids.partyanalyst.service;


import com.itgrids.partyanalyst.dto.OptionVO;

public interface ISuggestiveModelService {
	
	public OptionVO getPartyPerformanceForSelectedLocation(Long constituencyId,Long electionId,Long partyId,Long locationId,String type);

}
