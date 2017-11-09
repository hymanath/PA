package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IJbRoleDAO;
import com.itgrids.partyanalyst.model.JbRole;

public class JbRoleDAO extends GenericDaoHibernate<JbRole, Long> implements IJbRoleDAO {

	public JbRoleDAO() {
		super(JbRole.class);
	}

}
