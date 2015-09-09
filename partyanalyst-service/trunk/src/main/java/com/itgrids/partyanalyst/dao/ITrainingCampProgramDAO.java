package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampProgram;

public interface ITrainingCampProgramDAO extends GenericDao<TrainingCampProgram, Long>{
	
	public List<Object[]> getPrograms();
	public List<Object[]> getDistrictsByProgramId(Long programId);
	public List<Object[]> getAllTrainingPrograms();
}
