package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SurveyCompletionDetailsVO;
import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
import com.itgrids.partyanalyst.dto.ThirdPartyCompressionVO;

public interface ISurveyDashBoardService
{
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
	public List<String> getCasteCollectedDates();
	public ResultStatus saveThirdPartyDetails(Long bootId);
	public  List<SurveyResponceVO> getThirdPartyFinalDetails(Long boothId);
	public List<GenericVO> getConstituencyListForThirdPartyReport();
	public List<ThirdPartyCompressionVO> getCompressionReportForThirdParty(Long boothId,Long surveyUserId);
	public ResultStatus updateThirdPartyStatus(Long voterId,Long statusId);
}
