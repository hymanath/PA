package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterTempDAO;
import com.itgrids.partyanalyst.model.VoterTemp;

public class VoterTempDAO extends GenericDaoHibernate<VoterTemp,Long> implements IVoterTempDAO{

	public VoterTempDAO()
	{
		super(VoterTemp.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<VoterTemp> getVotersInAConstituency(Long constituencyId,Integer startIndex, Integer maxResults)
	{
		Query query = getSession().createQuery("select model from VoterTemp model where model.constituencyId = ? ");
		query.setParameter(0,constituencyId);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxResults);
		
		return query.list();
	}
}
