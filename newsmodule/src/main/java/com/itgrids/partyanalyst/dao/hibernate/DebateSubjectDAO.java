package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateSubjectDAO;
import com.itgrids.partyanalyst.model.DebateSubject;

public class DebateSubjectDAO extends GenericDaoHibernate<DebateSubject, Long> implements IDebateSubjectDAO{

	public DebateSubjectDAO() {
		super(DebateSubject.class);
		// TODO Auto-generated constructor stub
	}

}
