package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBasicCommitteeDAO;
import com.itgrids.partyanalyst.model.BasicCommittee;

public class BasicCommitteeDAO extends GenericDaoHibernate<BasicCommittee, Long> implements IBasicCommitteeDAO{

	public BasicCommitteeDAO() {
		super(BasicCommittee.class);
		
	}

}
