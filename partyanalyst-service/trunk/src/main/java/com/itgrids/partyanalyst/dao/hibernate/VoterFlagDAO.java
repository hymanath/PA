package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterFlagDAO;
import com.itgrids.partyanalyst.model.VoterFlag;

public class VoterFlagDAO extends GenericDaoHibernate<VoterFlag, Long> implements IVoterFlagDAO{

	public VoterFlagDAO() {
		super(VoterFlag.class);
		
	}
	public Integer deleteVoterFlag(Long flagId)
	{
		Query query = getSession().createQuery("delete from VoterFlag model where model.flag.flagId = :flagId");
		query.setParameter("flagId", flagId);
		return query.executeUpdate();
	}

}
