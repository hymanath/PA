package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.UserRelation;

public interface IUserRelationDAO extends GenericDao<UserRelation, Long>{

	public List<UserRelation> findByRelationType(String relation);
	
	public List<Long> getRelationId(String relationName);
}
