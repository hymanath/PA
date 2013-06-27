package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserCandidateRelationDAO;
import com.itgrids.partyanalyst.model.UserCandidateRelation;

public class UserCandidateRelationDAO extends GenericDaoHibernate<UserCandidateRelation,Long> implements IUserCandidateRelationDAO{

	public UserCandidateRelationDAO()
	{
		super(UserCandidateRelation.class);
	}
	/*
	public void deleteUserCandidateRelation(Long userCandidateRelationId)
	{
		StringBuilder query = new StringBuilder();
		query.append("delete from UserCandidateRelation model where model.userCandidateRelationId=:userCandidateRelationId");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setLong("userCandidateRelationId", userCandidateRelationId);
		queryObject.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getUserCandidateRelationDetails(Long userId)
	{
		return getHibernateTemplate().find("select model.userCandidateRelationId,model.candidate.candidateId," +
				" model.candidate.lastname from UserCandidateRelation model where model.user.userId=?",userId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getUserCandidateRelationCount(Long userId,Long candidateId)
	{
		Object[] parameters ={userId,candidateId};
		return getHibernateTemplate().find("select count(*) from UserCandidateRelation model where model.user.userId=? " +
				" and model.candidate.candidateId=?",parameters);
	}
	*/
	@SuppressWarnings("unchecked")
	public List<Object[]> getCandidatesOfAUser(Long userId)
	{
		return getHibernateTemplate().find("select model.candidate.candidateId,model.candidate.lastname from UserCandidateRelation model where model.user.userId = ? order by lastname",userId);
	}
}

