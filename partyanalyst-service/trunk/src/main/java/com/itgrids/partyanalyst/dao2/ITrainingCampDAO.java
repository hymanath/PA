package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCamp;

public interface ITrainingCampDAO extends GenericDao<TrainingCamp, Long>{
	
	public List<Object[]> getAllCamps();
	public List<Object[]> getCampDistrictsByCampId(Long campId);
	public List<Object[]> getAllTrainingCamps();
	public List<TrainingCamp> getAllRecordsByCampId(Long campId);
	public List<Object[]> getCampConstsByCampId(Long campId);
}
