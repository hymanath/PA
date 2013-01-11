package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterFamilyRangeDAO;
import com.itgrids.partyanalyst.model.VoterFamilyRange;

public class VoterFamilyRangeDAO extends GenericDaoHibernate<VoterFamilyRange, Long> implements IVoterFamilyRangeDAO{

	public VoterFamilyRangeDAO()
	{
		super(VoterFamilyRange.class);
	}
	
}
