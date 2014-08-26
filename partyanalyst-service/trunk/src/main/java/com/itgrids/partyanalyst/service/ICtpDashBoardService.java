package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.BigPictureVO;
import com.itgrids.partyanalyst.dto.BoothWiseSurveyStatusDetailsVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;

public interface ICtpDashBoardService
{
	public BigPictureVO getBigPictureDetails(Long stateId);
	public BigPictureVO getInternalVerificationSummary(Long stateId,String fromDate ,String toDate);
	public BigPictureVO getQcVerificationSummaryReport(Long stateId,String fromDate ,String toDate);
	public BigPictureVO getTodayTeamDetails(Long stateId,String fromDate , String toDate);
	public List<BigPictureVO> getBoothWiseTeamDetails(Long stateId , Long constituencyId , Long surveyUserTypeId,String fromDate , String toDate);
	public List<BigPictureVO> getConstituencyWiseTeamDetails(Long stateId,Long type,String fromDate , String toDate);
	public List<BigPictureVO> getConstituencyWiseQcVerificationSummary(Long stateId , String type,String fromDate , String toDate);
	public List<BigPictureVO> getBoothWiseQcVerificationSummary(Long stateId , Long constituencyId,String type,String fromDate , String toDate);
	public BigPictureVO getTodayTeamCollectedDetails(Long stateId,String fromdate,String toDate);
	public List<BigPictureVO> getConstituencyWiseTeamCollectedSummary(Long stateId,Long type,String fromdate,String toDate);
	public List<BigPictureVO> getBoothWiseTeamCollectedDetailsSummary(Long stateId,Long constituencyId , Long surveyUserTypeId,String fromdate,String toDate);
	public List<SurveyDashBoardVO> getCasteCollectedDetails(Long regionId,Long userTypeId,String startDate,String endDate);
	public List<SurveyDashBoardVO> getSurveyDetailsByConstituencyId(
			Long constituencyId, Long userTypeId);
	
	public List<SurveyResponceVO> getBoothWiseCollectedcasteDetails(Long boothId,Long userTypeId);
	public List<BoothWiseSurveyStatusDetailsVO> getAllBoothsStatusDetailsByConstituencyId(Long constituencyId);
	public List<SurveyDashBoardVO> getCountsForDC();
	
	public List<BigPictureVO> buildConstituencyWiseSummaryReport();
	
	public List<GenericVO> getUserWiseCollecetionDetails(Long boothId , Long surveyUserUd , String fromDate , String toDate,String type);


}
