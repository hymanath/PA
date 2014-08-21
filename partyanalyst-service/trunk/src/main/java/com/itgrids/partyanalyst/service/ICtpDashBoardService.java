package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BigPictureVO;
import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;

public interface ICtpDashBoardService
{
	public BigPictureVO getBigPictureDetails();
	public BigPictureVO getInternalVerificationSummary();
	public BigPictureVO getQcVerificationSummaryReport();
	public BigPictureVO getTodayTeamDetails();
	public List<BigPictureVO> getBoothWiseTeamDetails(Long constituencyId , Long surveyUserTypeId);
	public List<BigPictureVO> getConstituencyWiseTeamDetails(Long type);
	public List<BigPictureVO> getConstituencyWiseQcVerificationSummary(String type);
	public List<BigPictureVO> getBoothWiseQcVerificationSummary(Long constituencyId,String type);
	public BigPictureVO getTodayTeamCollectedDetails();
	public List<BigPictureVO> getConstituencyWiseTeamCollectedSummary(Long type);
	public List<BigPictureVO> getBoothWiseTeamCollectedDetailsSummary(Long constituencyId , Long surveyUserTypeId);
	public List<SurveyDashBoardVO> getCasteCollectedDetails(Long regionId,Long userTypeId);
	public List<SurveyDashBoardVO> getSurveyDetailsByConstituencyId(
			Long constituencyId, Long userTypeId);
	
	public List<SurveyResponceVO> getBoothWiseCollectedcasteDetails(Long boothId,Long userTypeId);
	public List<SurveyDashBoardVO> getCountsForDC();
}
