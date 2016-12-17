package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISessionTypeDAO;
import com.itgrids.partyanalyst.model.AcceptanceStatus;
import com.itgrids.partyanalyst.model.SessionType;

public class SessionTypeDAO extends GenericDaoHibernate<SessionType, Long> implements ISessionTypeDAO{

	public SessionTypeDAO() {
		super(SessionType.class);
		
	}

}
