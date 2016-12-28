package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPashiAppUserDAO;
import com.itgrids.partyanalyst.model.PashiAppUser;

public class PashiAppUserDAO extends GenericDaoHibernate<PashiAppUser, Long> implements IPashiAppUserDAO {

	public PashiAppUserDAO() {
		super(PashiAppUser.class);
	}
	
}
