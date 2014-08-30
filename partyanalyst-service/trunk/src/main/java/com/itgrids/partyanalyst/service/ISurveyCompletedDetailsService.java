package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.DuplicateMobileNumbersVO;
import com.itgrids.partyanalyst.dto.FinalSurveyReportVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;
import com.itgrids.partyanalyst.dto.SurveyReportVO;
import com.itgrids.partyanalyst.dto.SurveyThirdPartyReportVO;

public interface ISurveyCompletedDetailsService {
	public SurveyReportVO getBoothsStatusByConstituencyId(Long constituencyId);
	public String saveBoothStatusDetails(Long locationValue,Long statusId,String locationType);
	public List<SurveyReportVO> getSurveyCompletedLocationsDetailsForSurveyStartedConstituencies();

	public SurveyDashBoardVO getCompletdConstituenciesDetails();
	public List<FinalSurveyReportVO> finalDeselectionReport(Long constituencyId);
	public List<SurveyThirdPartyReportVO> finalReportWithThirdParty(Long constituencyId);
	public SurveyThirdPartyReportVO  getTPCompleteBoothsDetails(Long constituencyId,List<SurveyThirdPartyReportVO> thirdPartyList);
	public String saveSurveyCompletedConstituencyDetails(Long statusId,Long constituencyId,String comment);
	public List<SurveyReportVO> getSurveyCompletedConstituencyDetails();
	public List<SurveyThirdPartyReportVO> thirdPartyReadyForReviewConstBooths();
	public String getConstituencyCompletionStatusByConstituencyId(Long constituencyId);
	public List<DuplicateMobileNumbersVO> getDuplicateMobileNumbersDetails(String startDate,String endDate,List<Long> constituencyIds,Long frequencyCount);
	public List<SelectOptionVO> getSurveyStartedConstituencyDetails();
	public List<SurveyReportVO> getConstituencyWiseReportForDashBoard(List<Long> constituencyIds);
	public SurveyDashBoardVO getCompletedLocationsDetails();
	public List<SurveyReportVO> getBoothWiseDetails(Long constituencyId);
	
	public List<SurveyReportVO> getConstituencysReportByStatus(String constituencyStatus);

}
