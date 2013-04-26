package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;

public interface IUserVoterService {
	public List<SelectOptionVO> getUserVoterCategoryList(List<Long> userId);
	
	//public  List<VotersDetailsVO> getAgeRangeByUserVoterCategory(Long userId,List<Long>attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId);
	public List<VotersDetailsVO> getAgeRangeByUserVoterCategory(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId);
}
