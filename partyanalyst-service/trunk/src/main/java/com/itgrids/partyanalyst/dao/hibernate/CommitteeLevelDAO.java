package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICommitteeLevelDAO;
import com.itgrids.partyanalyst.model.CommitteeLevel;

public class CommitteeLevelDAO extends GenericDaoHibernate<CommitteeLevel, Long> implements ICommitteeLevelDAO{

	public CommitteeLevelDAO() {
		super(CommitteeLevel.class);
		
	}

}
