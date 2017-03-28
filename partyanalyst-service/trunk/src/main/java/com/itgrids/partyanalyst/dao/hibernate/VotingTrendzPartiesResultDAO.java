package com.itgrids.partyanalyst.dao.hibernate;


import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVotingTrendzPartiesResultDAO;
import com.itgrids.partyanalyst.model.VotingTrendzPartiesResult;


public class VotingTrendzPartiesResultDAO extends GenericDaoHibernate<VotingTrendzPartiesResult,Long> implements IVotingTrendzPartiesResultDAO{

	public VotingTrendzPartiesResultDAO(){
		super(VotingTrendzPartiesResult.class);
	}
	public List<Long> getVotingTrendzIds(Long constituenyId)
	{
		Query query = getSession().createQuery("select distinct model.votingTrendz.votingTrendzId from VotingTrendzPartiesResult model where model.votingTrendz.constituency.constituencyId =:constituenyId");
		query.setParameter("constituenyId", constituenyId);
		return query.list();	
	}
	
	public Integer deletePartyResultByConstituencyId(List<Long> votingTrendzIds)
	{
		Query query = getSession().createQuery("delete from VotingTrendzPartiesResult model where model.votingTrendz.votingTrendzId in(:votingTrendzIds)");
		query.setParameterList("votingTrendzIds", votingTrendzIds);
		return query.executeUpdate();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<VotingTrendzPartiesResult> getVotingTrendzPartiesResultList(Long constituencyId)
	{
		Query query = getSession().createQuery(" from VotingTrendzPartiesResult model where model.votingTrendz.constituency.constituencyId = :constituencyId ");
		query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getpartiesListForVotingTrendz(Long constituencyId)
	{
		return getHibernateTemplate().find("select distinct model.party.partyId,model.party.shortName from VotingTrendzPartiesResult model where model.votingTrendz.constituency.constituencyId = ? order by model.party.shortName ",constituencyId);
	}
}
