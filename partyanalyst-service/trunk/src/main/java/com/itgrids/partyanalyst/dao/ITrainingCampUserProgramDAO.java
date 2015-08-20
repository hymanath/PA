package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampUserProgram;

public interface ITrainingCampUserProgramDAO extends GenericDao<TrainingCampUserProgram, Long>{
	
	public List<Object[]> getProgramsByUser(Long userId);
	public List<Object[]> getCampsByProgramAndUser(Long campProgramId,Long userId);
}
