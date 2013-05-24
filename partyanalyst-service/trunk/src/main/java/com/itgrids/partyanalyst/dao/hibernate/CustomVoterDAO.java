package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICustomVoterDAO;
import com.itgrids.partyanalyst.model.CustomVoter;

public class CustomVoterDAO extends GenericDaoHibernate<CustomVoter,Long> implements ICustomVoterDAO {

	public CustomVoterDAO()
	{
		super(CustomVoter.class);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterGroupNamesByVoterIdsList(List<Long> voterIdsList)
	{
		Query query = getSession().createQuery(" select model.voter.voterId, model.customVoterGroup.name from CustomVoter model " +
				" where model.voter.voterId in (:voterIdsList)");
		query.setParameterList("voterIdsList", voterIdsList);
		return query.list();
	}
}
