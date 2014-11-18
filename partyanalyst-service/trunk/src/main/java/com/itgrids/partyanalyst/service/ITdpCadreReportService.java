package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.TdpCadreLocationWiseReportVO;

public interface ITdpCadreReportService {
	public List<TdpCadreLocationWiseReportVO> getLocationWiseReportDetailsForExcel(List<Long> constituencyIds);
	public TdpCadreLocationWiseReportVO generateExcelReportForTdpCadre(List<Long> constituencyIds);
}
