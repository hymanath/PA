package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.UserCandidateRelation;

public interface IUserCandidateRelationDAO extends GenericDao<UserCandidateRelation,Long>{
	
	public void deleteUserCandidateRelation(Long userCandidateRelationId);

	public List<Object[]> getUserCandidateRelationDetails(Long userId);
	
	public List<Long> getUserCandidateRelationCount(Long userId,Long candidateId);
	
	public List<Object[]> getCandidatesOfAUser(Long userId);
}
