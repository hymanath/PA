package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICustomVoterDAO;
import com.itgrids.partyanalyst.model.CustomVoter;

public class CustomVoterDAO extends GenericDaoHibernate<CustomVoter,Long> implements ICustomVoterDAO {

	public CustomVoterDAO()
	{
		super(CustomVoter.class);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterGroupNamesByVoterIdsList(List<Long> voterIdsList)
	{
		Query query = getSession().createQuery(" select model.voter.voterId, model.customVoterGroup.name from CustomVoter model " +
				" where model.voter.voterId in (:voterIdsList)");
		query.setParameterList("voterIdsList", voterIdsList);
		return query.list();
	}
	
	public void removeCustomVoterDetails(Long customerVoterId)
	{
		Query query = getSession().createQuery("delete from CustomVoter model where model.customVoterId = :customerVoterId");
		
		query.setParameter("customerVoterId", customerVoterId);
		query.executeUpdate();
		
	}
	
	public List<CustomVoter> getCustomVoterByVoterIdAndUserId(Long voterId , Long userId)
	{
		Query query = getSession().createQuery("select model from CustomVoter model where model.voter.voterId = ?" +
				" and model.customVoterGroup.user.userId = ?");
		
		query.setParameter(0, voterId);
		query.setParameter(1, userId);
		
		return query.list();
		
	}
	
	public List<Long> getCustomGroupIdByVoterIdAndUserId(Long voterId , Long userId)
	{
		Query query = getSession().createQuery("select model.customVoterGroup.customVoterGroupId from " +
				"CustomVoter model where customVoterGroup.user.userId = :userId and model.voter.voterId = :voterId");
		
		query.setParameter("userId", userId);
		query.setParameter("voterId", voterId);
		
		return query.list();
		
	}
	
	public List<Long> getCustomVoterIdByVoterIdAndUserId(Long voterId , Long userId)
	{
		Query query = getSession().createQuery("select model.customVoterId from " +
				"CustomVoter model where customVoterGroup.user.userId = :userId and model.voter.voterId = :voterId");
		
		query.setParameter("userId", userId);
		query.setParameter("voterId", voterId);
		
		return query.list();
		
	}
	
	public List<Object[]> getAllVotersGroups(List<Long> voterIds , Long userId)
	{
		Query query = getSession().createQuery("select model.voter.voterId , model.customVoterGroup.customVoterGroupId from " +
				" CustomVoter model where model.customVoterGroup.user.userId = :userId and model.voter.voterId in(:voterIds)");
		
		query.setParameter("userId", userId);
		query.setParameterList("voterIds", voterIds);
		
		return query.list();
		
		
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getVotersInfoBycustomVoterGroupId(Long customVoterGroupId,Long userId)
	{
		Query query = getSession().createQuery("select model.voter.voterId from CustomVoter model where model.customVoterGroup.customVoterGroupId = :customVoterGroupId and " +
				" model.customVoterGroup.user.userId = :userId");
		query.setParameter("customVoterGroupId", customVoterGroupId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCasteWiseCustomVotersCount(Long customVoterGroupId, Long userId)
	{
		Query query = getSession().createQuery(" select count (distinct CV.voter.voterId),CV.voter.gender,UVD.casteState.caste.casteName,UVD.casteState.casteCategoryGroup.casteCategory.categoryName from CustomVoter CV, UserVoterDetails UVD where " +
				" UVD.voter.voterId = CV.voter.voterId and CV.customVoterGroup.customVoterGroupId =:customVoterGroupId and UVD.user.userId =:userId group by UVD.casteState.caste.casteId,CV.voter.gender order by UVD.casteState.caste.casteName ");
		
		query.setParameter("customVoterGroupId", customVoterGroupId);
		query.setParameter("userId", userId);
		
		return query.list();
	}

}
