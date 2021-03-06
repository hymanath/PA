package com.itgrids.partyanalyst.service;
import java.io.File;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.CustomReportVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface ICustomReportService {
	public CustomReportVO getTotalExpectedReports(Long customReportProgramId);
	public ResultStatus saveCustomReportUploadFile(final Map<File,String> mapfiles,final Long userId,final String description,final Long reportId,final Long programId);
	public List<CustomReportVO> getCustomReportPrograms(String startDateStr,String endDateStr);
	public List<CustomReportVO> getProgramReportsDetails(Long programId);
	public CustomReportVO getReportFullDetails(Long reportId); 
	public List<CustomReportVO> getCustomReportProgramForreportId(Long programId,String type);
	public ResultStatus deleteCustomReportFileDetails(Long reportId,Long fileId);
	
	
}
