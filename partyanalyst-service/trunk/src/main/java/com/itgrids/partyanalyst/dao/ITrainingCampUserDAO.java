package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampUser;

public interface ITrainingCampUserDAO extends GenericDao<TrainingCampUser, Long> {

	public List<Long> getTrainingCampUserTypeIds(Long typeId);
}
