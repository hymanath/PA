package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.UserVoterDetails;

public class UserVoterDetailsDAO extends GenericDaoHibernate<UserVoterDetails, Long> implements 
IUserVoterDetailsDAO{

	public UserVoterDetailsDAO() {
		super(UserVoterDetails.class);
			}

	@SuppressWarnings("unchecked")
	public List<UserVoterDetails> getUserVoterDetails(Long voterId,Long userId){
		//List<UserVoterDetails> list = getHibernateTemplate().find("from UserVoterDetails as model where model.voter.voterId = ? and model.user.userId=?",voterId,userId);
		Query query = getSession().createQuery("from UserVoterDetails model where model.voter.voterId = ? and model.user.userId=?");
		query.setParameter(0,voterId);
		query.setParameter(1,userId);
		
		return query.list();
		
	}

	/*public Candidate findCandidateByLastName(String lastName){
		Candidate candidate = null;
		List<Candidate> list = getHibernateTemplate().find("from Candidate as model where model.lastname=?", lastName);
		if(list!=null && list.size()>0){
			candidate = list.get(0);
		}
		return candidate;
	}*/
	
	public void updateUserVoterDetails(Long voterId,Long userId,Long partyId,Long castStateId){
		Query query = getSession().createQuery("update UserVoterDetails model set model.party.partyId = :partyId,model.casteState.casteStateId = :castStateId where model.voter.voterId = :voterId and model.user.userId = :userId");
		query.setParameter("voterId",voterId);
		query.setParameter("userId",userId);
		query.setParameter("partyId",partyId);
		query.setParameter("castStateId",castStateId);
		
		query.executeUpdate();
		
	}
	
	public void updateUserVoterPartyDetails(Long voterId,Long userId,Long partyId,Long castStateId){
		Query query = getSession().createQuery("update UserVoterDetails model set model.party.partyId = :partyId where model.voter.voterId = :voterId and model.user.userId = :userId");
		query.setParameter("voterId",voterId);
		query.setParameter("userId",userId);
		query.setParameter("partyId",partyId);
		
		query.executeUpdate();
		
	}
	
	public void updateUserVoterCastDetails(Long voterId,Long userId,Long partyId,Long castStateId){
		Query query = getSession().createQuery("update UserVoterDetails model set model.casteState.casteStateId = :castStateId where model.voter.voterId = :voterId and model.user.userId = :userId");
		query.setParameter("voterId",voterId);
		query.setParameter("userId",userId);
		query.setParameter("castStateId",castStateId);
		
		query.executeUpdate();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCasteByVoterId(Long userId,List<Long> voterId)
	{
		Query query =getSession().createQuery("select model.voter.voterId,model.casteState.caste.casteName from UserVoterDetails model where model.user.userId = :userId and model.voter.voterId in (:voterId)");
		query.setParameter("userId", userId);
		query.setParameterList("voterId", voterId);
		return query.list();
	
	}
	
	public List<UserVoterDetails> getAllUserVoterDetails(List<Long> voterIds,Long userId){
		//List<UserVoterDetails> list = getHibernateTemplate().find("from UserVoterDetails as model where model.voter.voterId = ? and model.user.userId=?",voterId,userId);
		Query query = getSession().createQuery("from UserVoterDetails model where model.voter.voterId in (:voterIds) and model.user.userId= :userId");
		query.setParameterList("voterIds",voterIds);
		query.setParameter("userId",userId);
		
		return query.list();
		
	}
}