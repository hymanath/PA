package com.itgrids.partyanalyst.dao;

import java.util.List;

public interface ITrainingCampBatchDAO {
	public List<Object[]> getBatchesForSchedule(Long scheduleId);
}
