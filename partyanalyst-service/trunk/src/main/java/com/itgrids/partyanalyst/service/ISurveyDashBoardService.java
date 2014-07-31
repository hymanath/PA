package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.SurveyCompletionDetailsVO;
import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;

public interface ISurveyDashBoardService {
	
	public SurveyDashBoardVO getCompletdConstituenciesDetails();
	public String saveSurveyCompletionDetails(SurveyCompletionDetailsVO completionDetailsVO);
	public List<SurveyDashBoardVO> getConstituencyWiseCompletionReport();
	public List<SurveyCompletionDetailsVO> getSurveyCompletionDetailsOfConstituency(Long constituencyId);
	public List<SurveyCompletionDetailsVO> getSurveyCompletionDetailsForAllConstituencies();
	public String saveBoothCompletionStatus(Long boothId,Long statusId);
	public List<String> getCasteCollecteddatesByConstituencyId(Long constituencyId);
	public List<SurveyReportVO> getUsersCompleteReportByStartAndEndDates(String startDate,String endDate);
	public List<SurveyReportVO> getVerifiedBoothsDetails(String status,Long constituencyId);
	public List<String> getCasteCollectedDatesByUserId(Long userId);

}
