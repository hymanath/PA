package com.itgrids.partyanalyst.dao.hibernate;


import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IJbCommitteeRoleDAO;
import com.itgrids.partyanalyst.model.JbCommitteeRole;

public class JbCommitteeRoleDAO extends GenericDaoHibernate<JbCommitteeRole, Long> implements IJbCommitteeRoleDAO {

	public JbCommitteeRoleDAO() {
		super(JbCommitteeRole.class);
		
	}

	

}
