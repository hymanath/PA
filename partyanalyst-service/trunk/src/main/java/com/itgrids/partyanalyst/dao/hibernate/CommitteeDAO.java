package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICommitteeDAO;
import com.itgrids.partyanalyst.model.Committee;

public class CommitteeDAO extends GenericDaoHibernate<Committee, Long> implements ICommitteeDAO{

	public CommitteeDAO() {
		super(Committee.class);
		
	}

}
