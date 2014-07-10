package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;

public interface ISurveyDashBoardService {
	
	public SurveyDashBoardVO getCompletdConstituenciesDetails();
	public String saveSurveyCompletionDetails(Long scopeId,Long locationValue);

}
