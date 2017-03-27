package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.CustomReportVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface ICustomReportService {
	public List<CustomReportVO> getTotalExpertedReports(Long customReportProgramId);
	 public ResultStatus saveCustomReportUploadFile(final Map<File,String> mapfiles,final Long userId);

}
