package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.NewsActivityVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IReportService {
	public FileVO getReportData(Long reportId,Long userId,String key);
	public NewsActivityVO getActivitiesReportData(String key);
	public String getLocationDetails1(Long id,Long districtId);
	public ResultStatus deleteReportNews(Long reportFilesId,Long userId);
}
