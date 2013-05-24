package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICustomVoterDAO;
import com.itgrids.partyanalyst.model.CustomVoter;

public class CustomVoterDAO extends GenericDaoHibernate<CustomVoter,Long> implements ICustomVoterDAO {

	public CustomVoterDAO()
	{
		super(CustomVoter.class);
		
	}
}
