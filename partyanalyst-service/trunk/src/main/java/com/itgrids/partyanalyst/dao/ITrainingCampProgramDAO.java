package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampProgram;

public interface ITrainingCampProgramDAO extends GenericDao<TrainingCampProgram, Long>{
	
	public List<Object[]> getPrograms();
	public List<Object[]> getDistrictsByProgramId(List<Long> programIds,List<Long> enrollmentYrIds);
	public List<Object[]> getAllTrainingPrograms();
	public List<TrainingCampProgram> getAllRecordsByProgramId(Long programId);
	public List<Object[]> getConstsByProgramId(List<Long> programIds,List<Long> enrollmentYrIds);
	public List<Object[]> getTrainingProgramDetailsByProgramIds(List<Long> programIds);
	public List<Long> getProgramIdsList();
}
