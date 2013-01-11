package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.model.VoterAgeRange;

public class VoterAgeRangeDAO extends GenericDaoHibernate<VoterAgeRange, Long> implements IVoterAgeRangeDAO{

	public VoterAgeRangeDAO()
	{
		super(VoterAgeRange.class);
	}
	
	
}
