package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateDAO;
import com.itgrids.partyanalyst.model.Debate;

public class DebateDAO  extends GenericDaoHibernate<Debate, Long> implements IDebateDAO{

	public DebateDAO() {
		super(Debate.class);
		
	}

}
