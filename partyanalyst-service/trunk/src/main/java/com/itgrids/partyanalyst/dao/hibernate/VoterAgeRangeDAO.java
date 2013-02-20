package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.model.VoterAgeRange;

public class VoterAgeRangeDAO extends GenericDaoHibernate<VoterAgeRange, Long> implements IVoterAgeRangeDAO{

	public VoterAgeRangeDAO()
	{
		super(VoterAgeRange.class);
	}
	
	public Long getVoterAgeRangeIdByType(String ageRange)
	{
		Query queryObj = getSession().createQuery("select model.voterAgeRangeId from VoterAgeRange model " +
				" where model.ageRange = :ageRange ");
		queryObj.setParameter("ageRange", ageRange);
		return (Long) queryObj.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getAllVoterAgeRanges()
	{
		return getHibernateTemplate().find("select model.ageRange from VoterAgeRange model");
	}
	
}
