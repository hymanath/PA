package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IDynamicReportService {

	public ResultStatus createCadreAndVoterExcelReportsForAConstitueny(Long constituencyId,Long publicationDateId);
}
