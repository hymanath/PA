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
	public List<BigPictureVO> getConstituencyWiseTeamDetails();
	public List<BigPictureVO> getConstituencyWiseQcVerificationSummary();
	public List<BigPictureVO> getBoothWiseQcVerificationSummary(Long constituencyId);
	public List<SurveyDashBoardVO> getCasteCollectedDetails(Long regionId,Long userTypeId);
	public List<SurveyDashBoardVO> getSurveyDetailsByConstituencyId(
			Long constituencyId, Long userTypeId);
	
	public List<SurveyResponceVO> getBoothWiseCollectedcasteDetails(Long boothId,Long userTypeId);

}
