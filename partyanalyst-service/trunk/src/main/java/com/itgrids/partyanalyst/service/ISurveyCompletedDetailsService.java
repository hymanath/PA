package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.FinalSurveyReportVO;
import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;

public interface ISurveyCompletedDetailsService {
	public SurveyReportVO getBoothsStatusByConstituencyId(Long constituencyId);
	public String saveBoothStatusDetails(Long locationValue,Long statusId,String locationType);
	public List<SurveyReportVO> getSurveyCompletedLocationsDetailsForSurveyStartedConstituencies();

	public SurveyDashBoardVO getCompletdConstituenciesDetails();
	public List<FinalSurveyReportVO> finalDeselectionReport(Long constituencyId);
}
