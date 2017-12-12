package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMomAppUserAccessDAO;
import com.itgrids.partyanalyst.model.MomAppUserAccess;

public class MomAppUserAccessDAO extends GenericDaoHibernate<MomAppUserAccess, Long> implements IMomAppUserAccessDAO {

	public MomAppUserAccessDAO() {
		super(MomAppUserAccess.class);
	}
}
