package com.itgrids.partyanalyst.exceptionalReport.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.TrainingCampProgramVO;

public interface ITrainingCampExceptionalReportService {
	public List<TrainingCampProgramVO> getListOfParliamentsWithPoorPerformance(String startDate,String endDate, Long stateId,int size,List<Long> tdpCommitteeLevelIds,List<Long> trainingCampProgramIds,List<Long> enrollmentYearIds,Long locationLevelId, List<Long> locationLevelValues );
	public List<TrainingCampProgramVO> getListOfAssemblyWithPoorPerformance(String startDate,String endDate, Long stateId,int size,List<Long> tdpCommitteeLevelIds,List<Long> trainingCampProgramIds,List<Long> enrollmentYearIds,Long locationLevelId, List<Long> locationLevelValues );

}
