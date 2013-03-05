package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.Voter;

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
	
	public List<Object[]> getUserVoterDetailsByVoterIds(List<Long> voterIds,Long userId){
		
		Query query = getSession().createQuery("select model.voter.voterId , model.party.shortName," +
				"model.casteState.caste.casteName from UserVoterDetails model where model.voter.voterId in(:voterIds)" +
				" and model.user.userId = :userId");
		
		query.setParameterList("voterIds", voterIds);
		query.setParameter("userId", userId);
		
		return query.list();
		
		
	}
	
  public List<UserVoterDetails> getUserVoterDtlsVoterIds(List<Long> voterIds,Long userId){
		
		Query query = getSession().createQuery("select model from UserVoterDetails model where model.voter.voterId in(:voterIds)" +
				" and model.user.userId = :userId");
		
		query.setParameterList("voterIds", voterIds);
		query.setParameter("userId", userId);
		
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
	public void updateUserVoterDetails(Long voterId,Long userId,Long partyId,Long castStateId,Long localitityId, Long hamletId){
		Query query = getSession().createQuery("update UserVoterDetails model set model.party.partyId = :partyId,model.casteState.casteStateId = :castStateId,model.locality.localityId = :localityId,model.hamlet.hamletId = :hamletId where model.voter.voterId = :voterId and model.user.userId = :userId");
		query.setParameter("voterId",voterId);
		query.setParameter("userId",userId);
		query.setParameter("partyId",partyId);
		query.setParameter("castStateId",castStateId);
		query.setParameter("localityId",localitityId);
		query.setParameter("hamletId",hamletId);
		
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
	/**
	 * This method is used to get the party name and caste categoery of a voter based on voter id and user id
	 * @author Prasad Thiragabathina
	 * @param Long voterId
	 * @param Long userId
	 * @return List<Object[]>
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartyAndCasteDetails(Long voterId , Long userId) {
		Object[] param = {voterId,userId};
		return getHibernateTemplate().find("select model.casteState.caste.casteId," +
				" model.casteState.caste.casteName,model.party.partyId,model.party.shortName " +
				"from UserVoterDetails model where model.voter.voterId = ? and model.user.userId = ? ",param);
	}
	
	
	/** This Method is used to get VoterIds based on hamletId and UserId  */
	

	  public List<Object> getVoterIdsBasedOnHamletId(Long hamletId, Long userId)
{
	Object[] param = {hamletId,userId};
	return getHibernateTemplate().find("select distinct model.voter.voterId " +
						"from UserVoterDetails model  where  model.hamlet.hamletId = ? and model.user.userId = ? ",param);
}
	
	public List<Object[]> getVotersCountByGenderForHamlet(Long hamletId)
	{
		
		Query query = getSession().createQuery("select model.voter.gender , count(*) from UserVoterDetails model " +
				"where model.hamlet.hamletId = ? group by model.voter.gender ");
		
		query.setParameter(0, hamletId);
		
		return query.list();
		
		
	}
	
	
	public List<Voter> getVoterDetailsByHamletId(Long hamletId , Long userId)
	{
		
		Query query = getSession().createQuery("select model.voter from UserVoterDetails model where" +
				" model.hamlet.hamletId = ? and model.user.userId = ?");
		
		query.setParameter(0, hamletId);
		query.setParameter(1, userId);
		
		
		return query.list();
		
	}
	
	public List<Long> getUserHamletsByPanchayatId(Long userId , Long panchayatId)
	{
		
		
		Query query = getSession().createQuery("select distinct model.hamlet.hamletId from UserVoterDetails model, PanchayatHamlet model1" +
				" where model.user.userId = :userId and model.hamlet.hamletId = model1.hamlet.hamletId and " +
				" model1.panchayat.panchayatId = :panchayatId");
		
		query.setParameter("userId", userId);
		query.setParameter("panchayatId", panchayatId);
		
		return query.list();
	}
}