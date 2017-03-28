package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

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
	
	public List<Long> getConstituencyIdsAlreadyhasData()
	{
		Query query = getSession().createQuery("select distinct MVMD.constituencyId from MemberVoterMappingDetails MVMD");
		
		return query.list();
		
	}
}
