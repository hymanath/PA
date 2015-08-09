package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampSchedule;

public interface ITrainingCampScheduleDAO extends GenericDao<TrainingCampSchedule, Long>{
	public List<Object[]> getProgrammesListByCampsList(List<Long> campsList);
	public List<Object[]> getScheduledDetailsByProgrammes(List<Long> programmesList);
	public List<Object[]> getSchedules();
}
