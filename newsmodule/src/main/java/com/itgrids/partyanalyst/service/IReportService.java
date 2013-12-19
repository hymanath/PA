package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IReportService {
	public FileVO getReportData(Long reportId,Long userId,String key);
	public SelectOptionVO getActivitiesReportData(String key);
}
