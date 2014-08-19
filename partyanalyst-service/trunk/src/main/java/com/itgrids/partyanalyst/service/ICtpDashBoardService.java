package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BigPictureVO;

public interface ICtpDashBoardService
{
	public BigPictureVO getBigPictureDetails();
	public BigPictureVO getInternalVerificationSummary();
	public BigPictureVO getQcVerificationSummaryReport();
	public BigPictureVO getTodayTeamDetails();
	public List<BigPictureVO> getBoothWiseTeamDetails(Long constituencyId , Long surveyUserTypeId);
	public List<BigPictureVO> getConstituencyWiseTeamDetails();
	public List<BigPictureVO> getConstituencyWiseQcVerificationSummary();
}
