package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.SurveyCompletionDetailsVO;
import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;

public interface ISurveyDashBoardService {
	
	public SurveyDashBoardVO getCompletdConstituenciesDetails();
	public String saveSurveyCompletionDetails(SurveyCompletionDetailsVO completionDetailsVO);
	public List<SurveyDashBoardVO> getConstituencyWiseCompletionReport();
	public List<SurveyCompletionDetailsVO> getSurveyCompletionDetailsOfConstituency(Long constituencyId);
	public List<SurveyCompletionDetailsVO> getSurveyCompletionDetailsForAllConstituencies();

}
