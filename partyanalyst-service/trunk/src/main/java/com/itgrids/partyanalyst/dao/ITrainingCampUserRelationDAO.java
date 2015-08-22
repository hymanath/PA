package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampUserRelation;

public interface ITrainingCampUserRelationDAO extends GenericDao<TrainingCampUserRelation, Long>{
	
	public List<Object[]> getAgentsByCampCallerAdminId(Long campCallerAdminId,boolean isAdmin);

}
