package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.JanmabhoomiType;

import com.itgrids.partyanalyst.dao.IJanmabhoomiTypeDAO;

public class JanmabhoomiTypeDAO extends GenericDaoHibernate<JanmabhoomiType, Long> implements IJanmabhoomiTypeDAO{

	public JanmabhoomiTypeDAO() {
		super(JanmabhoomiType.class);
		
	}

}
