package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.NewsActivityVO;

public interface IReportService {
	public FileVO getReportData(Long reportId,Long userId,String key);
	public NewsActivityVO getActivitiesReportData(String key);
}
