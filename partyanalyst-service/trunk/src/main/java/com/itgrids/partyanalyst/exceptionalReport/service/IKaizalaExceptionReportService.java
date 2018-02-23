package com.itgrids.partyanalyst.exceptionalReport.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.KaizalaExceptionalReportVO;

public interface IKaizalaExceptionReportService {
	public List<KaizalaExceptionalReportVO> getConstituencyWisePoorPerformance(Long stateId,int size,String location);
	public KaizalaExceptionalReportVO getOverallReport(Long stateId);
}
