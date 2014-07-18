package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICommitteeRoleDAO;
import com.itgrids.partyanalyst.model.CommitteeRole;



public class CommitteeRoleDAO  extends GenericDaoHibernate<CommitteeRole, Long> implements ICommitteeRoleDAO{

	public CommitteeRoleDAO() {
		super(CommitteeRole.class);
		// TODO Auto-generated constructor stub
	}

}
