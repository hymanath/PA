package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.FileVO;

public interface IReportService {
	public FileVO getReportData(Long reportId,Long userId,String key);
}
