package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IitdpAppUserDAO;
import com.itgrids.partyanalyst.model.ITdpAppUser;


public class ItdpAppUserDAO extends GenericDaoHibernate<ITdpAppUser, Long> implements IitdpAppUserDAO {

	public ItdpAppUserDAO() {
		super(ITdpAppUser.class);
	}
	
}
