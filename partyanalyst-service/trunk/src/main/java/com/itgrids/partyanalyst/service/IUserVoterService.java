package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.MandalInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.dto.VoterDataVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;

public interface IUserVoterService {
	public List<SelectOptionVO> getUserVoterCategoryList(List<Long> userId);
	
	//public  List<VotersDetailsVO> getAgeRangeByUserVoterCategory(Long userId,List<Long>attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId);
	public List<VotersDetailsVO> getAgeRangeByUserVoterCategory(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId);	
	
	public List<VoterVO> getCategoryWiseVoterData(VoterDataVO voterDataVO , Long userId , List<Long> categories);
	
	public String getCategoryNameByCategoryId(Long userVoterCategoryId);
	
	public List<MandalInfoVO> getCensusDetailsInALocation(String locationType, Long locationValue, Long constituencyId);
	
}
