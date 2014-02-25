package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHHLeaderDAO;
import com.itgrids.partyanalyst.model.HHLeader;


public class HHLeaderDAO extends GenericDaoHibernate<HHLeader,Long> implements IHHLeaderDAO {
	
	public HHLeaderDAO() {
		super(HHLeader.class);
	}
	
	public List<String> getAllExistingVoterIds()
	{
		Query query = getSession().createQuery("select distinct model.voterId from HHLeader model");
		
		return query.list();
		
	}
	

}
