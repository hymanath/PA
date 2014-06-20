package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMemberVoterMappingDetailsDAO;
import com.itgrids.partyanalyst.model.MemberVoterMappingDetails;

public class MemberVoterMappingDetailsDAO extends GenericDaoHibernate<MemberVoterMappingDetails, Long> implements
		IMemberVoterMappingDetailsDAO {
	
	public MemberVoterMappingDetailsDAO() {
		super(MemberVoterMappingDetails.class);
	}
	
	public void flushSession()
	{
		getSession().flush();
		
	}
}
