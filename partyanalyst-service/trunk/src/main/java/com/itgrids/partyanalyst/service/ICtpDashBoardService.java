package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BigPictureVO;
import com.itgrids.partyanalyst.dto.BoothWiseSurveyStatusDetailsVO;
import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;

public interface ICtpDashBoardService
{
	public BigPictureVO getBigPictureDetails(Long stateId);
	public BigPictureVO getInternalVerificationSummary(Long stateId);
	public BigPictureVO getQcVerificationSummaryReport(Long stateId);
	public BigPictureVO getTodayTeamDetails(Long stateId);
	public List<BigPictureVO> getBoothWiseTeamDetails(Long stateId , Long constituencyId , Long surveyUserTypeId);
	public List<BigPictureVO> getConstituencyWiseTeamDetails(Long stateId,Long type);
	public List<BigPictureVO> getConstituencyWiseQcVerificationSummary(Long stateId , String type);
	public List<BigPictureVO> getBoothWiseQcVerificationSummary(Long stateId , Long constituencyId,String type);
	public BigPictureVO getTodayTeamCollectedDetails(Long stateId);
	public List<BigPictureVO> getConstituencyWiseTeamCollectedSummary(Long stateId,Long type);
	public List<BigPictureVO> getBoothWiseTeamCollectedDetailsSummary(Long stateId,Long constituencyId , Long surveyUserTypeId);
	public List<SurveyDashBoardVO> getCasteCollectedDetails(Long regionId,Long userTypeId);
	public List<SurveyDashBoardVO> getSurveyDetailsByConstituencyId(
			Long constituencyId, Long userTypeId);
	
	public List<SurveyResponceVO> getBoothWiseCollectedcasteDetails(Long boothId,Long userTypeId);
	public List<BoothWiseSurveyStatusDetailsVO> getAllBoothsStatusDetailsByConstituencyId(Long constituencyId);
	public List<SurveyDashBoardVO> getCountsForDC();


}
