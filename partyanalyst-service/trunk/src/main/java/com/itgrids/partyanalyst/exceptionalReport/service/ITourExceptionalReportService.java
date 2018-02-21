package com.itgrids.partyanalyst.exceptionalReport.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.dto.ToursOverviewDtlsvO;

public interface ITourExceptionalReportService {

	 public ToursOverviewDtlsvO getDesignationWiseTourSubmittedOverviewDtls(InputVO inputVO);
	 public List<ToursOverviewDtlsvO> getNotSubmittedCandidateDetailsByFilter(InputVO inputVO);
}
