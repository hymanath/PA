package com.itgrids.partyanalyst.exceptionalReport.service;

import com.itgrids.partyanalyst.dto.CommitteeDataVO;
import com.itgrids.partyanalyst.dto.InputVO;

public interface ICommitteeExceptionalReportService {

	public CommitteeDataVO getCommiteeOverviewPerformanceDetails(InputVO inputVO);
	public CommitteeDataVO getBoothInchargeCommitteePerformanceDetails(InputVO inputVO);
}
