package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampSchedule;

public interface ITrainingCampScheduleDAO extends GenericDao<TrainingCampSchedule, Long>{
	public List<Object[]> getProgrammesListByCampsList(List<Long> campsList);
	public List<Object[]> getScheduledDetailsByProgrammes(List<Long> programmesList);
	public List<Object[]> getSchedules(Long campId);
	public List<Object[]> getCampsForProgram(Long programId);
	public List<Long> getAllUpcomingTrainingCampSchedules(Date fromDate,Date toDate,String type,Date todayDate);
	public List<Long> getSchedulesByProgramAndCenter(Long programId, Long centerId,Long enrollmentYearId);
	public List<TrainingCampSchedule> getAllRecordsByScheduleId(Long scheduleId);
	public List<Long> getTrainingCampProgramIds(Long enrollementYearId);
}
