package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterTagDAO;
import com.itgrids.partyanalyst.model.VoterTag;

public class VoterTagDAO extends GenericDaoHibernate<VoterTag, Long> implements IVoterTagDAO{
	
	public VoterTagDAO()
	{
		super(VoterTag.class);
	}
	
	public VoterTag getVoterTagByVoterIdAndUniqueCode(Long voterId,String uniqueCode)
	{
		Query query = getSession().createQuery("Select model from VoterTag model where model.voter.voterId = :voterId and model.uniqueCode = :uniqueCode");
		query.setParameter("voterId", voterId);
		query.setParameter("uniqueCode", uniqueCode);
		return (VoterTag) query.uniqueResult();
	}
}
