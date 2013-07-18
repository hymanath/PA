package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVotingTrendzDAO;
import com.itgrids.partyanalyst.model.VotingTrendz;

public class VotingTrendzDAO extends GenericDaoHibernate<VotingTrendz,Long> implements IVotingTrendzDAO{

	public VotingTrendzDAO(){
		super(VotingTrendz.class);
	}
	
	
	public Integer deleteVotingTrendzByConstituencyId(Long constituenyId)
	{
		Query query = getSession().createQuery("delete from VotingTrendz model where model.constituency.constituencyId =:constituenyId");
		query.setParameter("constituenyId", constituenyId);
		return query.executeUpdate();
		
	}
}
