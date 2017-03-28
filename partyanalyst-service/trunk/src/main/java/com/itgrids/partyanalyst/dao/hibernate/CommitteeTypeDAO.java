package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICommitteeTypeDAO;
import com.itgrids.partyanalyst.model.CommitteeType;

public class CommitteeTypeDAO extends GenericDaoHibernate<CommitteeType, Long> implements ICommitteeTypeDAO{

	public CommitteeTypeDAO() {
		super(CommitteeType.class);
		
	}

}
