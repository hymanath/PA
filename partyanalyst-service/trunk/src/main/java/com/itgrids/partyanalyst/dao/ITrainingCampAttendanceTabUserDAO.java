package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.TrainingCampAttendanceTabUser;
import com.itgrids.partyanalyst.model.TrainingCampSchedule;

public interface ITrainingCampAttendanceTabUserDAO extends GenericDao<TrainingCampAttendanceTabUser,Long>{

	public List<TrainingCampSchedule> getTrainingCampSchedulesOfAttendanceTabUser(Long attendanceTabUserId);
	
	public List<Object[]> getTrainingCampBatchesOfScheduleForTabUser(Long trainingCampScheduleId,Long attendanceTabUserId);
}
