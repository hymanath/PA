package com.itgrids.partyanalyst.exceptionalReport.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.TrainingCampProgramVO;

public interface ITrainingCampExceptionalReportService {
	public TrainingCampProgramVO getOverallTrainingCampReport(String startDate,String endDate, Long stateId,int size);
	public List<TrainingCampProgramVO> getListOfParliamentsWithPoorPerformance(String startDate,String endDate, Long stateId,int size);
	public List<TrainingCampProgramVO> getListOfAssemblyWithPoorPerformance(String startDate,String endDate, Long stateId,int size);

}
