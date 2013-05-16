package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterFamilyRangeDAO;
import com.itgrids.partyanalyst.model.VoterFamilyRange;

public class VoterFamilyRangeDAO extends GenericDaoHibernate<VoterFamilyRange, Long> implements IVoterFamilyRangeDAO{

	public VoterFamilyRangeDAO()
	{
		super(VoterFamilyRange.class);
	}
	
	public Long getVoterFamilyRangeIdByFamilyRange(String familyRange)
	{
		Query queryObj = getSession().createQuery("select model.voterFamilyRangeId from VoterFamilyRange model where model.familyRange =:familyRange ");
		queryObj.setParameter("familyRange", familyRange);
		return (Long) queryObj.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getVoterFamilyRangeDetails()
	{
		return getHibernateTemplate().find("select count(model.voterFamilyRangeId) from VoterFamilyRange model");
	}
	
}
