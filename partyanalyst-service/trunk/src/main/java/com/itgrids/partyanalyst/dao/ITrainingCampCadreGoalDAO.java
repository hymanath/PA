package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampCadreGoal;

public interface ITrainingCampCadreGoalDAO extends GenericDao<TrainingCampCadreGoal, Long>{
    
	public List<Object[]> getGoalsDetailsforCadre(Long tdpCadreId,Long batchId);
}
