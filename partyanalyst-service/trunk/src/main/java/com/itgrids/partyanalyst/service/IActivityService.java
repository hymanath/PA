package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;

public interface IActivityService {
	
	public Map<Long,ActivityVO> getAttributeListByQuestionnaireList(List<Long> questionnairesList);
	public List<ActivityVO> getActivityDayWiseCountsByLocation(SearchAttributeVO searchAttributeVO);
	public ActivityVO getActivityDetailsBySearchCriteria(SearchAttributeVO searchAttributeVO);
	public List<IdNameVO> getActivityLevelsList();
	public BasicVO getActivityTypeList();
}
